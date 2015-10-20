<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro"%>
<%@ page import="gcom.micromedicao.Rota"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

function limparLocalidade() {
	var form = document.forms[0];
	form.idLocalidade.value = "";
	form.nomeLocalidade.value = "";	
	form.codigoSetorComercial.value = "";
	form.nomeSetorComercial.value = "";
	form.idQuadra.value = "";
	form.rota.value = "-1";
	form.rota.disabled = true;
}

function limparMunicipio(){
	var form = document.forms[0];
	form.idMunicipio.value = "";
	form.nomeMunicipio.value = "";
}

function limparSetorComercial() {
	var form = document.forms[0];
	form.codigoSetorComercial.value = "";
	form.nomeSetorComercial.value = "";
	form.rota.value = "-1";
	form.rota.disabled = true;	
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
   
     if (tipoConsulta == 'localidade') {     
	     if (form.tipoPesquisa.value == 'inicial') {
		     form.idLocalidade.value = codigoRegistro;
		     form.nomeLocalidade.value = descricaoRegistro;
		     form.nomeLocalidade.style.color = "#000000";
		     form.idLocalidade.value = codigoRegistro;
		     form.nomeLocalidade.value = descricaoRegistro;
		     form.nomeLocalidade.style.color = "#000000";
		     form.idLocalidade.focus();
	    } else {
		 	  form.idLocalidade.value = codigoRegistro;
		      form.nomeLocalidade.value = descricaoRegistro;
		      form.nomeLocalidade.style.color = "#000000";      
	    }    
	}
    
    if (tipoConsulta == 'setorComercial') {    	
		form.nomeSetorComercial.style.color = "#000000";
		form.nomeSetorComercial.value = descricaoRegistro;
		form.codigoSetorComercial.value = codigoRegistro;
		form.action = "exibirGerarRelatorioEnquadramentoDesenquadramentoGrandeClienteAction.do"
 	  	form.submit();				
    }

    if(tipoConsulta == 'municipio'){
		form.nomeMunicipio.style.color = "#000000";
		form.nomeMunicipio.value = descricaoRegistro;
		form.idMunicipio.value = codigoRegistro; 
    }      
}

function replicaDados(campoOrigem, campoDestino){
	campoOrigem.value = campoDestino.value;
}

function chamarPesquisaLocalidade() {
	var form = document.forms[0];	
	abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);
}

function chamarPesquisaMunicipio(){
	var form = document.forms[0];	
	abrirPopup('exibirPesquisarMunicipioAction.do?tipo=imovelLocalidade', 400, 800);	
}

function chamarPesquisaSetorComercial() {
	var form = document.forms[0];	
	abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+form.idLocalidade.value+'&tipo=setorComercial&indicadorUsoTodos=1',form.idLocalidade.value,'Localidade', 400, 800);
	
}

function validarForm(form){
	if(validateGerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm(form)){
		submitForm(form);
	}		   
}

</script>
</head>
<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
	<html:form  action="/gerarRelatorioEnquadramentoDesenquadramentoGrandeClienteAction" method="post" 
				name="GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm"
				type="gcom.gui.relatorio.cadastro.imovel.GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm"
				onsubmit="return validateGerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm(this);">
		
		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>
		
		<input type="hidden" name="tipoPesquisa" />		
		<html:hidden property="tipoRelatorioEscolhido"/>
	
		<table width="770" border="0" cellspacing="5" cellpadding="0">	
			<tr>
				<td width="150" valign="top" class="leftcoltext">
					<div align="center">
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>			
						<%@ include file="/jsp/util/informacoes_usuario.jsp"%>			
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>			
						<%@ include file="/jsp/util/mensagens.jsp"%>			
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
					</div>
				</td>
				<td width="625" valign="top" class="centercoltext">
					<table height="100%">
						<tr><td></td></tr>
					</table>			
				<table>
					<tr><td></td></tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Relatório de Acompanhamento de Grandes Cliente/Corporativo</td>
						<td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif" /></td>
					</tr>	
				</table>				
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr><td colspan="2">Para gerar Relatório, informe os dados gerais abaixo:</td></tr>					
					<tr>
						<td width="200"><strong>Municipio:</strong></td>
						<td colspan="3">
							<strong> 
								<html:text property="idMunicipio" size="5" maxlength="4" tabindex="1"
								onkeypress= "validaEnterComMensagem(event, 'exibirGerarRelatorioEnquadramentoDesenquadramentoGrandeClienteAction.do', 'idMunicipio', 'Municipio');"/>
								 
								<a href="javascript:chamarPesquisaMunicipio();"><img border="0" src="imagens/pesquisa.gif" height="21" width="23"></a>
							
								<logic:present name="MunicipioInexistente" scope="session">
									<html:text property="nomeMunicipio" readonly="true" style="background-color:#EFEFEF; border:0;" size="40" maxlength="40" />
								</logic:present>							
								<logic:notPresent name="MunicipioInexistente" scope="session">
									<html:text property="nomeMunicipio" readonly="true" style="background-color:#EFEFEF; border:0; color:#FF0000" size="40" maxlength="40" />
								</logic:notPresent>						
								<a href="javascript:limparMunicipio();"> 
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
								</a> 
							</strong>
						</td>
					</tr>														
					<tr>
						<td width="200"><strong>Gerência Regional:</strong></td>
						<td colspan="3">
							<html:select property="gerenciaRegional" tabindex="2" style="width:200px;">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>"> &nbsp;</html:option>
								<html:options collection="colecaoGerenciaRegional" property="id" labelProperty="nome" />
							</html:select>
						</td>
					</tr>					
					<tr>
						<td width="200"><strong>Unidade de Negócio:</strong></td>
						<td colspan="3">
							<html:select property="unidadeNegocio" tabindex="3" style="width:200px;">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>"> &nbsp; </html:option>
								<html:options collection="colecaoUnidadeNegocio" property="id" labelProperty="nome" />
							</html:select>
						</td>
					</tr>					
					<tr>
						<td width="200"><strong>Localidade:</strong></td>
						<td colspan="3">
							<strong> 
								<html:text property="idLocalidade" size="5" maxlength="3" tabindex="4"
								onkeypress= "validaEnterComMensagem(event, 'exibirGerarRelatorioEnquadramentoDesenquadramentoGrandeClienteAction.do', 'idLocalidade', 'Localidade');"/>
								 
								<a href="javascript:chamarPesquisaLocalidade();"><img border="0" src="imagens/pesquisa.gif" height="21" width="23"></a>
							
								<logic:present name="localidadeFinalInexistente" scope="session">
									<html:text property="nomeLocalidade" readonly="true" style="background-color:#EFEFEF; border:0;" size="40" maxlength="40" />
								</logic:present>							
								<logic:notPresent name="localidadeFinalInexistente" scope="session">
									<html:text property="nomeLocalidade" readonly="true" style="background-color:#EFEFEF; border:0; color:#FF0000" size="40" maxlength="40" />
								</logic:notPresent>						
								<a href="javascript:limparLocalidade();"> 
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
								</a> 
							</strong>
						</td>
					</tr>
					<tr>
						<td width="200"><strong>Setor Comercial:</strong></td>
						<td colspan="3">
							<strong> 
								<html:text property="codigoSetorComercial" size="5" maxlength="3" tabindex="5"
									onkeypress="return validaEnterDependencia(event, 'exibirGerarRelatorioEnquadramentoDesenquadramentoGrandeClienteAction.do', this, document.forms[0].codigoSetorComercial.value, 'setorComercial');" />								
								<a href="javascript:chamarPesquisaSetorComercial();">
									<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" /></a>								
								<logic:present name="setorComercialInexistente" scope="session">
									<html:text property="nomeSetorComercial" readonly="true" style="background-color:#EFEFEF; border:0;" size="40" maxlength="40" />
								</logic:present>							
								<logic:notPresent name="setorComercialInexistente" scope="session">
									<html:text property="nomeSetorComercial" readonly="true" style="background-color:#EFEFEF; border:0; color:#FF0000"  size="40" maxlength="40" />
								</logic:notPresent>							
								<a href="javascript:limparSetorComercial();">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
								</a>							
							</strong>
						</td>
					</tr>
					<tr>
						<td><strong>Quadra:</strong></td>
						<td colspan="3">
							<html:text property="idQuadra" size="5" tabindex="6" maxlength="4" onkeyup="somente_numero_zero_a_nove(this);" 
								onkeypress= "validaEnterComMensagem(event, 'exibirGerarRelatorioEnquadramentoDesenquadramentoGrandeClienteAction.do', 'idQuadra', 'Quadra');"/>&nbsp;							
						</td>												
					</tr>
					<logic:present name="colecaoRota" scope="session">
						<tr>
							<td width="200"><strong>Rota:</strong></td>
							<td colspan="3">
								<html:select property="rota" tabindex="7" style="width:200px;">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>"> &nbsp; </html:option>									
									<html:options collection="colecaoRota" property="id" labelProperty="codigo" />																		
								</html:select>
							</td>
						</tr>
					</logic:present>
					<logic:notPresent name="colecaoRota" scope="session">
						<tr>
							<td width="200"><strong>Rota:</strong></td>
							<td colspan="3">
								<html:select property="rota" tabindex="8" style="width:200px;" disabled="true">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>"> &nbsp; </html:option>									
								</html:select>
							</td>
						</tr>
					</logic:notPresent>					
					<tr>
						<td width="200"><strong>Perfil do Imóvel Origem:</strong></td>
						<td colspan="3">
							<html:select property="imovelPerfilOrigem" tabindex="15" style="width:200px;">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>"> &nbsp;</html:option>
								<html:options collection="colecaoImovelPerfil" property="id" 
								labelProperty="descricao"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<td width="200"><strong>Perfil do Imóvel Destino:</strong></td>
						<td colspan="3">
							<html:select property="imovelPerfilDestino" tabindex="15" style="width:200px;">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>"> &nbsp;</html:option>
								<html:options collection="colecaoImovelPerfil" property="id" 
								labelProperty="descricao"/>
							</html:select>
						</td>
					</tr>
					
					<tr>
						<td width="200"><strong>Tipo do Enquadramento</strong></td>
						<td colspan="3">
							<html:select property="enquadramentoTipo" tabindex="15" style="width:200px;">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>"> &nbsp;</html:option>
								<html:option value="1">MANUAL</html:option>
								<html:option value="1">AUTOMÁTICO</html:option>								
							</html:select>
						</td>					
					</tr>									
					<tr>
						<td width="100"><strong>Data Enquadramento no Perfil:</strong></td>
						<td colspan="6"><span class="style2"><strong>
							<html:text property="enquadramentoDataInicial" size="11" maxlength="10" onkeyup="mascaraData(this, event);replicaDados(document.forms[0].enquadramentoDataFinal, document.forms[0].enquadramentoDataInicial);"
								onkeypress="return isCampoNumerico(event);" />
							<a href="javascript:abrirCalendarioReplicando('GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm', 'enquadramentoDataInicial','enquadramentoDataFinal');">
								<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" /></a>
							&nbsp;a&nbsp;
							<html:text property="enquadramentoDataFinal" size="11" maxlength="10" onkeyup="mascaraData(this, event)" onkeypress="return isCampoNumerico(event);"/>
							<a href="javascript:abrirCalendario('GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm', 'enquadramentoDataFinal');">
								<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" /></a>
							</strong>(dd/mm/aaaa)<strong></strong></span>
						</td>
					</tr>					
					<tr>
						<td><strong>Data Desenquadramento no Perfil:</strong></td>
						<td colspan="6"><span class="style2"><strong>
							<html:text property="desenquadramentoDataInicial" size="11" maxlength="10" onkeyup="mascaraData(this, event);replicaDados(document.forms[0].desenquadramentoDataFinal, document.forms[0].desenquadramentoDataInicial);" 
							onkeypress="return isCampoNumerico(event);"/>
							<a href="javascript:abrirCalendarioReplicando('GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm', 'desenquadramentoDataInicial','desenquadramentoDataFinal');">
								<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" /></a>
							&nbsp;a&nbsp;
							<html:text property="desenquadramentoDataFinal" size="11" maxlength="10" onkeyup="mascaraData(this, event)" onkeypress="return isCampoNumerico(event);"/>
							<a href="javascript:abrirCalendario('GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm', 'desenquadramentoDataFinal');">
								<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" /></a>
							</strong>(dd/mm/aaaa)<strong></strong></span>
						</td>
					</tr>					
					<tr>
						<td width="200"><strong>Categoria:</strong></td>
						<td colspan="3">
							<html:select property="categoria" multiple="true" style="width: 316px;">
							<logic:notEmpty name="colecaoCategoria">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoCategoria" labelProperty="descricao" property="id" />
							</logic:notEmpty>
							</html:select>
						</td>
					</tr>									
					<tr>
						<td width="200"><strong>Subcategoria:</strong></td>
						<td colspan="3">
							<html:select property="subcategoria" multiple="true" style="width: 316px;">
							<logic:notEmpty name="colecaoSubcategoria">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoSubcategoria" labelProperty="descricao" property="id" />
							</logic:notEmpty>
							</html:select>
						</td>
					</tr>														
					<tr>
						<td width="200"><strong>Situação da Ligação de Água:</strong></td>
						<td colspan="3">
							<html:select property="situacaoLigacaoAgua" tabindex="15" style="width:200px;">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>"> &nbsp;</html:option>
								<html:options collection="colecaoSitucaoLigacaoAgua" property="id" 
								labelProperty="descricao"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td width="200"><strong>Situação da Ligação de Esgoto:</strong></td>
						<td colspan="3">
							<html:select property="situacaoLigacaoEsgoto" tabindex="16" style="width:200px;">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>"> &nbsp;</html:option>
								<html:options collection="colecaoSitucaoLigacaoEsgoto" property="id" 
								labelProperty="descricao"/>
							</html:select>
						</td>
					</tr>								
					<tr>
						<td><strong>Intervalo Valor Faturamento:</strong></td>					
						<td colsan="3">						
							<html:text maxlength="11" tabindex="1" property="valorFaturamentoIntervaloInicial" size="12" 
								onkeyup="javascript:formataValorMonetario(this, 11);"/>
							<strong>a</strong>
							<html:text maxlength="11" tabindex="1" property="valorFaturamentoIntervaloFinal" size="12" 
								onkeyup="javascript:formataValorMonetario(this, 11);"/>
						</td>
					</tr>					
					<tr>
						<td width="200"><strong>Esfera de Poder:</strong></td>
						<td colspan="3">
							<html:select property="esferaPoder" tabindex="20" style="width:200px;">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoEsferaPoder" property="id" 
								labelProperty="descricao"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<td width="200"><strong>Capacidade do Hidrômetro:</strong></td>
						<td colspan="3">
							<html:select property="hidrometroCapacidade" multiple="true" style="width: 316px;">
							<logic:notEmpty name="colecaoHidrometroCapacidade">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoHidrometroCapacidade" labelProperty="descricao" property="id" />
							</logic:notEmpty>
							</html:select>
						</td>
					</tr>					
					<tr>
						<td width="200"><strong>Tarifa do Consumo:</strong></td>
						<td colspan="3">
							<html:select property="consumoTarifa" multiple="true" style="width: 316px;">
							<logic:notEmpty name="colecaoConsumoTarifa">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoConsumoTarifa" labelProperty="descricao" property="id" />
							</logic:notEmpty>
							</html:select>
						</td>
					</tr>													
					<tr><td><p>&nbsp;</p></td></tr>				
					<tr>
						<td>
							<input name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left"  
								onclick="javascript:window.location.href='/gsan/telaPrincipal.do'" tabindex="21">							
							<input name="Button" type="button" class="bottonRightCol" value="Limpar" align="left"
								onclick="window.location.href='/gsan/exibirGerarRelatorioEnquadramentoDesenquadramentoGrandeClienteAction.do?menu=sim'" tabindex="22">
						</td>
						<td align="right">							
							<gsan:controleAcessoBotao name="button" value="Gerar" onclick="javascript:validarForm(document.forms[0]);" url="gerarRelatorioEnquadramentoDesenquadramentoGrandeClienteAction.do"/>
						</td>						
					</tr>
				</table>
				<p>&nbsp;</p>
				</td>	
			</tr>
		</table>
		<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioEnquadramentoDesenquadramentoGrandeClienteAction.do" />
		<tr><td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%></tr>		
	</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>


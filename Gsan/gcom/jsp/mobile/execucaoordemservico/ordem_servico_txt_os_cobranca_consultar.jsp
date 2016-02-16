<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.micromedicao.SituacaoTransmissaoLeitura"%>
<%@ page import="gcom.gui.micromedicao.DadosLeiturista" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"  href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>


<html:javascript staticJavascript="true" formName="ConsultarArquivoTextoOSCobrancaSmartphoneActionForm" />


<script type="text/javascript">

	function validaForm() {		
		form = document.forms[0];
		form.action = 'consultarArquivoTextoOSCobrancaSmartphoneAction.do';
		form.submit();
	}

	function limparForm() {
		var form = document.forms[0];
		form.action = 'exibirConsultarDadosArquivoTextoOSCobrancaSmartphoneAction.do?menu=sim';
		form.submit();
	}

	function naoLiberar(form) {
		if (CheckboxNaoVazio()) {
			form.action = 'validarArquivoTextoOSCobrancaSmartphoneAction.do?liberar=0';
			form.submit();
		}
	}

	function liberar(form) {
		if (CheckboxNaoVazio()) {
			form.action = 'validarArquivoTextoOSCobrancaSmartphoneAction.do?liberar=1';
			form.submit();
		}
	}

	function emCampo(form) {
		if (CheckboxNaoVazio()) {
			form.action = 'validarArquivoTextoOSCobrancaSmartphoneAction.do?liberar=2';
			form.submit();
		}
	}

	function finalizar(form) {
		if (CheckboxNaoVazio()) {
			form.action = 'validarArquivoTextoOSCobrancaSmartphoneAction.do?liberar=3';
			form.submit();
		}
	}

	function gerarArquivo(imei,idSituacaoArquivo,idArquivo) {
		var form = document.forms[0];
		form.action = 'transmitirAquivoTxtOSCobrancaSmartphoneAction.do?imei=' + imei + '&idSituacaoArquivo=' + idSituacaoArquivo + '&idArquivo=' + idArquivo;
		form.submit();
	}
	
	function leiturista(form) {
		if (CheckboxNaoVazio()) {
			abrirPopup('exibirAtualizarLeituristaOSCobrancaSmartphoneAction.do', '210', '580');
		}
	}

	function CheckboxNaoVazio() {
		form = document.forms[0];
		retorno = false;

		for (indice = 0; indice < form.elements.length; indice++) {
			if (form.elements[indice].type == "checkbox"  && form.elements[indice].checked == true) {
				retorno = true;
				break;
			}
		}

		if (!retorno) {
			alert('Informe o(s) arquivo(s) desejado(s).');
		}

		return retorno;
	}

	function extendeTabela(display) {
		var form = document.forms[0];

		if (display) {
			eval('layerHideDadosArquivos').style.display = 'none';
			eval('layerShowDadosArquivos').style.display = 'block';
		} else {
			eval('layerHideDadosArquivos').style.display = 'block';
			eval('layerShowDadosArquivos').style.display = 'none';
		}
	}

	function verificaTabela(achou) {
		if (achou == '2') {
			eval('layerHideDadosArquivos').style.display = 'block';
			eval('layerShowDadosArquivos').style.display = 'none';
		} else if (achou == '1') {
			eval('layerHideDadosArquivos').style.display = 'none';
			eval('layerShowDadosArquivos').style.display = 'block';
		}
	}

	
	function replicaDataCobrancaEventual() {
		var form = document.forms[0];                
		form.dataCobrancaEventualFinal.value = form.dataCobrancaEventualInicial.value;
	}
	
	function excluir(form) {
		if (CheckboxNaoVazio()) {
			form.action = 'excluirArquivoTextoOSCobrancaSmartphoneAction.do';
			form.submit();
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		
		if (tipoConsulta == 'localidade') {
			form.idLocalidade.value = codigoRegistro;
			form.descricaoLocalidade.value = descricaoRegistro;
			form.descricaoLocalidade.style.color = "#000000";
		} else if (tipoConsulta == 'setorComercial') {
			if (origemDestino == 'origem') {
				form.idSetorComercialInicial.value = codigoRegistro;
				form.descricaoSetorComercialInicial.value = descricaoRegistro;
				form.descricaoSetorComercialInicial.style.color = "#000000";
				form.idSetorComercialFinal.value = codigoRegistro;
				form.descricaoSetorComercialFinal.value = descricaoRegistro;
				form.descricaoSetorComercialFinal.style.color = "#000000";
				validarPreencherQuadra();
			} else {
				form.idSetorComercialFinal.value = codigoRegistro;
				form.descricaoSetorComercialFinal.value = descricaoRegistro;
				form.descricaoSetorComercialFinal.style.color = "#000000";
				validarPreencherQuadra();
			}

		} else {
			form.action = 'validarArquivoTextoOSCobrancaSmartphoneAction.do?liberar=4&idNovoLeiturista='
					+ codigoRegistro;
			form.submit();
		}
	}
</script>


</head>

<body leftmargin="5" topmargin="5">
<html:form action="/consultarArquivoTextoOSCobrancaSmartphoneAction"
	name="ConsultarArquivoTextoOSCobrancaSmartphoneActionForm"
	type="gcom.gui.mobile.execucaoordemservico.ConsultarArquivoTextoOSCobrancaSmartphoneActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="130" valign="top" class="leftcoltext">
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
			
			<td width="600" valign="top" class="centercoltext">
			
			<table height="100%"><tr><td></td></tr></table>

			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11">
						<img src="<bean:message key="caminho.imagens"/>parahead_left.gif" border="0" />
					</td>

					<td class="parabg">Consultar Arquivo Texto das Ordens de Serviço para Dispositivo Móvel</td>

					<td width="11" valign="top">
						<img src="<bean:message key="caminho.imagens"/>parahead_right.gif" border="0" />
					</td>
				</tr>
			</table>
							
			<table width="100%" border="0">
			
				<tr>
					<td colspan="3">Para consultar os arquivos textos das ordens de serviço, informe os dados abaixo:</td>
				</tr>

			   <tr><td>&nbsp;</td></tr>	
			   
			   <tr>
				  <td width="30%">
					  <strong>Empresa:<font color="#FF0000">*</font></strong>
				  </td>
				  <td>
					  <logic:present name="colecaoEmpresa"> 
					      <html:select property="idEmpresa"  style="width:300px;" tabindex="9">
						 	<html:option value="-1">&nbsp;</html:option>
						 	<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id"/>
					  	  </html:select>
					  </logic:present>
							
					  <logic:notPresent name="colecaoEmpresa"> 							
					  	  <html:hidden property="idEmpresaidEmpresa"/>
					      <html:text property="descricaoEmpresa" size="41" maxlength="40" readonly="true" 
						 		     style="background-color:#EFEFEF; border:0; color: #000000" />
					  </logic:notPresent>
				  </td>
				</tr>
				
				<tr>
					<td>
						<strong>Tipo da Ordem de Serviço:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:hidden property="idTipoOS"/>
						<html:text property="descricaoTipoOS" size="41"
								   maxlength="40" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #000000" />
					</td>
				</tr>

			    <tr>
		           <td width="30%">
						<strong>Tipo de Serviço:<font color="#FF0000">*</font></strong>
				   </td>
				   <td>
						<html:select property="idServicoTipo" tabindex="2" style="width: 300px;">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoServicoTipo" labelProperty="descricao" property="id"/>
						</html:select>
					</td>
			    </tr>
			    
			   <tr>
					<td>
						<strong>Tipo de Filtro:<font color="#FF0000">*</font></strong>
					</td>
					<td colspan="2">
						<html:radio styleId="tipoFiltroGrupo" property="tipoFiltro" value="1"/>
						<label for="tipoFiltroGrupo"><strong>Grupo de Cobrança</strong></label>

						<html:radio styleId="tipoFiltroComando" property="tipoFiltro" value="2"/>
						<label for="tipoFiltroComando"><strong>Cobrança Eventual</strong></label>
					</td>
				</tr>

                <tr><td>&nbsp;</td></tr>
                
                <c:choose>
					<c:when test="${ConsultarArquivoTextoOSCobrancaSmartphoneActionForm.tipoFiltro eq 1}">					
							<tr bgcolor="#99CCFF" >
								<td height="18" colspan="3">
									<div align="left">
										<strong>
											<span class="style2"> Filtro por Grupo de Cobrança</span>
										</strong>
									</div>
								</td>
							</tr>
			
							<tr>
								<td>
									<strong>Mês/Ano do Cronograma:<font color="#FF0000">*</font></strong>
								</td>
								<td>
									<html:text property="mesAnoCronograma" maxlength="7" size="8" tabindex="3"
										styleId="mesAnoCronograma" onkeyup="mascaraAnoMes(this, event);"
										onkeypress="return isCampoNumerico(event); mascaraAnoMes(this, event);"/> mm/aaaa
								</td>
							</tr>
			
			                <tr>
							  <td width="30%">
								<strong>Grupo Cobrança:<font color="#FF0000">*</font></strong>
							  </td>
							  <td>
							  	<logic:present name="colecaoGrupoCobranca">
							  		<html:select property="idGrupoCobranca" tabindex="4" style="width: 300px;">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoGrupoCobranca" labelProperty="descricao" property="id"/>
									</html:select>
								</logic:present>
								
								<logic:notPresent name="colecaoGrupoCobranca">
							  		<html:select property="idGrupoCobranca" tabindex="4" style="width: 300px;">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									</html:select>
								</logic:notPresent>	
	  						 </td>
							</tr>
							
							<tr>
								<td>
									<strong>Localidade:</strong>
								</td>
								
								<td>
									<logic:present name="colecaoLocalidade">
									<html:select property="idsLocalidade" tabindex="5" style="width: 300px;" multiple="true">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoLocalidade" labelProperty="descricao" property="id"/>
									</html:select>
								</logic:present>
								<logic:notPresent name="colecaoLocalidade">
									<html:select property="idsLocalidade" tabindex="5" style="width: 300px;" disabled="true">
									</html:select>
								</logic:notPresent>
								</td>
						   </tr>
						   	<html:hidden property="dataCobrancaEventualInicial" value=""/>
 							<html:hidden property="dataCobrancaEventualFinal" value=""/>
					</c:when>
					<c:when test="${ConsultarArquivoTextoOSCobrancaSmartphoneActionForm.tipoFiltro eq 2}">	
						<tr bgcolor="#99CCFF" >
							<td height="18" colspan="3">
								<div align="left">
									<strong>
										<span class="style2"> Filtro por Cobrança Eventual</span>
									</strong>
								</div>
							</td>
						</tr>
						
						<tr>
			                <td><strong>Período de Realização:<font color="#FF0000">*</font></strong></td>
			                				<td>
								<strong>
									<span class="style2">
										<html:text property="dataCobrancaEventualInicial" size="11" maxlength="10" tabindex="6"
												onkeyup="mascaraData(this, event);replicaDataCobrancaEventual();" onkeypress="return isCampoNumerico(event);"/>
										<a href="javascript:abrirCalendarioReplicando('ConsultarArquivoTextoOSCobrancaSmartphoneActionForm', 'dataCobrancaEventualInicial', 'dataCobrancaEventualFinal');">
										<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" title="Exibir Calendário" tabindex="7"/></a>
									</span>
									<span>&nbsp;a&nbsp;</span>
									<span class="style2">
										<html:text property="dataCobrancaEventualFinal" size="11" maxlength="10" tabindex="8"
													onkeypress="return isCampoNumerico(event);" onkeyup="mascaraData(this, event)"/>
										<a href="javascript:abrirCalendario('ConsultarArquivoTextoOSCobrancaSmartphoneActionForm', 'dataCobrancaEventualFinal');">
										<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" title="Exibir Calendário" tabindex="9"/></a>
									</span>
								</strong>
							</td>
		
							<td align="right">
								<input type="button" class="bottonRightCol" id="consultarComandos" value="Consultar Comandos" tabindex="13"/>
							</td>				    
						</tr>
		
						<tr>
							<td>
								<strong>Cobrança Eventual:<font color="#FF0000">*</font></strong>
							</td>
							<td>
								<logic:present name="colecaoComandosEventuais">
									<html:select property="idComando" tabindex="10" style="width: 300px;">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoComandosEventuais" labelProperty="descricaoTitulo" property="id"/>
									</html:select>
								</logic:present>
								<logic:notPresent name="colecaoComandosEventuais">
									<html:select property="idComando" tabindex="10" style="width: 300px;" disabled="true">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									</html:select>
								</logic:notPresent>
							</td>
						</tr>
						<html:hidden property="mesAnoCronograma" value="" />
				    </c:when>
				    <c:otherwise>
						<html:hidden property="mesAnoCronograma" value="" />
						<html:hidden property="dataCobrancaEventualInicial" value=""/>
						<html:hidden property="dataCobrancaEventualFinal" value=""/>
					</c:otherwise>
				</c:choose>
				
				<tr><td>&nbsp;</td></tr>	
				<tr bgcolor="#99CCFF" >
					<td height="18" colspan="3">
						<div align="left">
							<strong>
								<span class="style2"> Filtro para Geração do Arquivo TXT </span>
							</strong>
						</div>
					</td>
				</tr>
				
				<tr>
					<td width="30%">
						<strong>Agente Comercial:</strong>
					</td>
					<td>
						<html:select property="idAgenteComercial"  style="width: 300px;" tabindex="9">
							<html:option value="-1">&nbsp;</html:option>	
							<logic:present name="colecaoAgenteComercial"> 
								<html:options collection="colecaoAgenteComercial"  labelProperty="nome" property="id" /> 
							</logic:present>
						</html:select>
					</td>
				</tr>
				
				<tr>
		            <td width="30%">
						<strong>Situação Arquivo Texto:</strong>
					</td>
					<td>				
						<html:select property="situacaoTextoLeitura"  style="width: 200px;" tabindex="9">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoSituacaoArquivoTexto">
								<html:options collection="colecaoSituacaoArquivoTexto" labelProperty="descricaoSituacao" property="id"/>
							</logic:present>	
						</html:select>
					</td>
			     </tr>

				<tr>
					<td></td>
					<td>
						<br/>
						<font color="#FF0000">*</font>Campos Obrigat&oacute;rios
					</td>
					<td align="right">
						<input type="button" class="bottonRightCol" id="selecionar" value="Selecionar" tabindex="11"/>
					</td>
				</tr>		

				<tr>
					<td colspan="3">
						<hr/>
					</td>
				</tr>
				
				<tr>
					<td colspan="6">
						<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Arquivos Textos:</strong></font>
					</td>
				</tr>	
					
				<tr>
					<td colspan="6" height="23"> 
						 <gsan:controleAcessoBotao name="Botao1" value="Liberar" onclick="liberar(document.forms[0]);"
												   url="validarArquivoTextoOSCobrancaSmartphoneAction.do" tabindex="13" />
					     <gsan:controleAcessoBotao name="Botao2" value="Não Liberar" onclick="naoLiberar(document.forms[0]);"
												   url="validarArquivoTextoOSCobrancaSmartphoneAction.do" tabindex="13" />
						 <gsan:controleAcessoBotao name="Botao4" value="Finalizar" 
												   onclick="finalizar(document.forms[0]);"
												   url="validarArquivoTextoOSCobrancaSmartphoneAction.do" tabindex="13" />						
						 <gsan:controleAcessoBotao name="Botao5" value="Informar Agente Comercial" 
										    	   onclick="leiturista(document.forms[0]);"
												   url="validarArquivoTextoOSCobrancaSmartphoneAction.do" tabindex="13" />	
						<input name="Button" type="button" value="Excluir Arquivo" align="left"	onclick="excluir( document.forms[0] )" class="bottonRightCol">
					</td>
				</tr>
				
				<tr>
					<td colspan="5" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>

				<logic:present name="colecaoArquivoTextoOSCobrancaSmartphone">
								
				<tr>
					<td colspan="4">
					   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					   		<tr bgcolor="#99CCFF"  align="center">
		                   		<td>
		             				<b>Dados dos Arquivos</b>
		               			</td>
		              		</tr>
		              		<tr>
		            		   <td>
									<table width="100%" bgcolor="#99CCFF">
									    <tr bgcolor="#99CCFF">
											<td align="center" width="50"><a href="javascript:facilitador('idsRegistros');"><strong>Todos</strong></a> </td>
											<td align="center" width="70"><strong>Localidade</strong></td>		
											<td align="center" width="70"><strong>Setor Comercial</strong></td>	
											<td align="center" width="30"><strong>Rota</strong></td>
											<td align="center" width="45"><strong>Quadra</strong></td>
											<td align="center" width="70"><strong>Quantidade</strong></td>		
											<td align="center" width="70"><strong>Agente Comercial</strong></td>	
											<td align="center" width="80"><strong>Situação</strong></td>
											<td align="center" width="80"><strong>Liberação</strong></td>									
										</tr>
									 </table>
								</td>
		              		</tr>
		              		<tr>
								<td>
									<table width="100%" bgcolor="#99CCFF">
										 <logic:present name="colecaoArquivoTextoOSCobrancaSmartphone">
										   <%int count = 0;%>
		 								   <logic:iterate name="colecaoArquivoTextoOSCobrancaSmartphone"  id="helper">
											 <tr bgcolor="${count % 2 == 0 ? '#FFFFFF' : '#cbe5fe'}">	
													<td align="center" width="50">
														<html:multibox property="idsRegistros" value="${helper.idArquivo}" disabled="false" />
													</td>
													
													<c:choose>
														<c:when test="${fn:length(helper.idsLocalidade) > 1}">
														    <!-- O valor de this.T_WIDTH igual a -100 é para indicar que a largura do hint é adaptado de acordo com o tamanho do texto.
															     Sendo que a largura maxima da adaptação é igual a 100px -->
															<td align="center" width="70"  onmouseover="this.T_BGCOLOR='whitesmoke';this.T_WIDTH=-100;this.T_LEFT=false;return escape( '${helper.idLocalidadeHint}' );">
																 <c:choose>
																	<c:when test='${helper.idSituacao == 2}'>
																		<a href="javascript:gerarArquivo(${helper.imei},${helper.idSituacao},${helper.idArquivo})" >Várias</a> 
																	</c:when>
																
																	<c:otherwise>
																		Várias
																	</c:otherwise>
																</c:choose>
														   </td>	
														</c:when>
														<c:otherwise>
															<td align="center" width="70">
																 <c:choose>
						 											<c:when test='${helper.idSituacao == 2}'>
																		<a href="javascript:gerarArquivo(${helper.imei},${helper.idSituacao},${helper.idArquivo})" >${helper.idLocalidade}</a> 
																	</c:when>
																
																	<c:otherwise>
																		${helper.idLocalidade}
																	</c:otherwise>
																</c:choose>
														    </td>
														</c:otherwise>
													</c:choose>
													
												
														
													<c:choose>
														<c:when test="${fn:length(helper.idsSetor) > 1}">
															<!-- O valor de this.T_WIDTH igual a -100 é para indicar que a largura do hint é adaptado de acordo com o tamanho do texto.
															     Sendo que a largura maxima da adaptação é igual a 100px -->
															<td width="70" align="center"  onmouseover="this.T_BGCOLOR='whitesmoke';this.T_WIDTH=-100;this.T_LEFT=false;return escape( '${helper.idSetorHint}' );"> 
																<c:out value="Vários"/>
													   		</td>													   		
														</c:when>														
														<c:otherwise>
															<td width="70" align="center"> 
																<c:out value="${helper.idSetor}"/>
													   		</td>												 	
														</c:otherwise>
													</c:choose>	
													
													<c:choose>
														<c:when test="${fn:length(helper.idsRota) > 1}">
			    											<!-- O valor de this.T_WIDTH igual a -100 é para indicar que a largura do hint é adaptado de acordo com o tamanho do texto.
															     Sendo que a largura maxima da adaptação é igual a 100px -->
															<td width="30" align="center"  onmouseover="this.T_BGCOLOR='whitesmoke';this.T_WIDTH=-100;this.T_LEFT=true;return escape( '${helper.idRotaHint}' );"> 
																<c:out value="Várias"/>
													   		</td>													   		
														</c:when>														
														<c:otherwise>
															<td width="30" align="center"> 
																<c:out value="${helper.idRota}"/>
													   		</td>												 	
														</c:otherwise>
													</c:choose>		
													
													<c:choose>
														<c:when test="${fn:length(helper.nnQuadras) >1}"> 
			    											<!-- O valor de this.T_WIDTH igual a -100 é para indicar que a largura do hint é adaptado de acordo com o tamanho do texto.
															     Sendo que a largura maxima da adaptação é igual a 100px -->
															<td width="45" align="center"  onmouseover="this.T_BGCOLOR='whitesmoke';this.T_WIDTH=-100;this.T_LEFT=true;return escape( '${helper.nnQuadraHint}' );"> 
																<c:out value="Várias"/>
													   		</td>													   		
														</c:when>														
														<c:otherwise>
															<td width="45" align="center"> 
																<c:out value="${helper.nnQuadra}"/>
													   		</td>												 	
														</c:otherwise>
													</c:choose>			
															
													
													<td align="center" width="70"><c:out value="${helper.qtdOrdemServico}"/></td>		
													<td align="center" width="70"> 
														 <a href="/gsan/exibirConsultarOrdemServicoCobrancaSmartphoneAction.do?arquivoTexto=${helper.idArquivo}&idTipoOrdemServico=1"><c:out value="${not empty helper.nomeFuncionario ? helper.nomeFuncionario : helper.nomeCliente}"/> </a> 
													</td>	
													<td align="center" width="80"><c:out value="${helper.situacao}"/></td>
													<td align="center" width="80"><c:out value="${helper.dataLiberacao}"/></td>
													<c:set var="count" value="${count+1}"/>
										 		</tr>
											 </logic:iterate>			
										 </logic:present>	
									  				
									</table>
								</td>
						    </tr>				
					   </table>	
				    </td>
				</tr>
				</logic:present>	
				<tr>
					<td colspan="2">
						<table border="0" width="100%">
							<tr>
								<td width="70">
									<input name="Button" type="button" class="bottonRightCol"
									value="Desfazer"
									onclick="window.location.href='<html:rewrite page="/exibirConsultarDadosArquivoTextoOSCobrancaSmartphoneAction.do?menu=sim"/>'">
							    </td>

							    <td>
									<input type="button" class="bottonRightCol" value="Cancelar" onclick="window.location.href='/gsan/telaPrincipal.do'" />
							  </td>
							</tr>
						</table>
					</td>
					<td align="right">
						 <input type="button" class="bottonRightCol" value="Gerar Relat&oacute;rio" id="botaoGerarRelatorio" tabindex="15"/>
					</td>
				</tr>	
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>                                                                 
	                                                                        
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioConsultarArquivoTextoOrdensServicoSmartphoneAction.do"/>														
	<%@ include file="/jsp/util/tooltip.jsp"%>	
	<%@ include file="/jsp/util/rodape.jsp"%>	
	<%@ include file="/jsp/util/telaespera.jsp"%>	
</html:form>
<script>

var statusCheckbox = {};
function facilitador(name) {
	if(statusCheckbox[name]) {
		$('input[name='+name+']').removeAttr('checked');
		statusCheckbox[name] = false;
	} else {
		$('input[name='+name+']').attr('checked', true);
		statusCheckbox[name] = true;
	}
}

$(function(){
	var $grupoCobranca = $('select[name=idGrupoCobranca]');
	var $dataCobrancaEventualInicial = $('input[name=dataCobrancaEventualInicial]');
	var $dataCobrancaEventualFinal = $('input[name=dataCobrancaEventualFinal]');
	var $mesAnoCronograma = $('#mesAnoCronograma');
	var $localidade = $('select[name=idLocalidade]');
	var $btnDataInicial = $('#btnDataInicial');
	var $comando = $('select[name=idComando]');
	var $botaoConsultarComandos = $('#consultarComandos');
	var $empresa = $('select[name=idEmpresa]');
	var $tipoServico = $('select[name=idServicoTipo]'); 
	
	$grupoCobranca.change(function() {
		var form = document.ConsultarArquivoTextoOSCobrancaSmartphoneActionForm;	
		form.action = "/gsan/exibirConsultarDadosArquivoTextoOSCobrancaSmartphoneAction.do?tipoPesquisa=consultarLocalidade";
		form.submit();
	});
	
	$empresa.change(function(){
		var form = document.ConsultarArquivoTextoOSCobrancaSmartphoneActionForm;
		form.action = "/gsan/exibirConsultarDadosArquivoTextoOSCobrancaSmartphoneAction.do?tipoPesquisa=selecionarEmpresa";
		form.submit();
	});
	
	$botaoConsultarComandos.click(function() {
		var dataCobrancaEventualInicial = $dataCobrancaEventualInicial.val();
		var dataCobrancaEventualFinal = $dataCobrancaEventualFinal.val();
	
		if (!dataCobrancaEventualInicial || !dataCobrancaEventualFinal) {
			alert("Informar o período de realização");
			return;
		}
		
		if (!validateConsultarArquivoTextoOSCobrancaSmartphoneActionForm(document.forms[0])){
			return;
		}
			
		var form = document.ConsultarArquivoTextoOSCobrancaSmartphoneActionForm;
		form.action =  "/gsan/exibirConsultarDadosArquivoTextoOSCobrancaSmartphoneAction.do?tipoPesquisa=consultarComandos";
		form.submit();
	});
	
	$('#selecionar').click(function() {
		var mesAnoCronograma = $mesAnoCronograma.val();
		var grupoCobranca = $grupoCobranca.val();
		var dataCobrancaEventualInicial = $dataCobrancaEventualInicial.val();
		var dataCobrancaEventualFinal = $dataCobrancaEventualFinal.val();
		var comando = $comando.val();

		if (!validateConsultarArquivoTextoOSCobrancaSmartphoneActionForm(document.forms[0])){
			return;
		}
		
		grupoCobranca = grupoCobranca == -1 ? 0 : grupoCobranca;
		comando = comando == -1 ? 0 : comando;
		

		if ((mesAnoCronograma && grupoCobranca) || (dataCobrancaEventualInicial && dataCobrancaEventualFinal && comando)) {
			form = document.forms[0];
			form.action = 'consultarArquivoTextoOSCobrancaSmartphoneAction.do';
			form.submit();
		} else {
			alert("Informar campos obrigatórios do filtro.")
		}
	});
	
	 $tipoServico.change(function(){
		 var form = document.ConsultarArquivoTextoOSCobrancaSmartphoneActionForm;	
		 form.action = 'exibirConsultarDadosArquivoTextoOSCobrancaSmartphoneAction.do?tipoPesquisa=tipoServico';
	     form.submit();
	 });
	 
	$('#botaoGerarRelatorio').click(function() {
			var mesAnoCronograma = $mesAnoCronograma.val();
			var grupoCobranca = $grupoCobranca.val();
			var dataCobrancaEventualInicial = $dataCobrancaEventualInicial.val();
			var dataCobrancaEventualFinal = $dataCobrancaEventualFinal.val();
			var comando = $comando.val();

			grupoCobranca = grupoCobranca == -1 ? 0 : grupoCobranca;
			comando = comando == -1 ? 0 : comando;
			
			if ((mesAnoCronograma && grupoCobranca) || (dataCobrancaEventualInicial && dataCobrancaEventualFinal && comando)) {
				toggleBox('demodiv',1);
			} else {
				alert("Informar campos obrigatórios do filtro.");
			}
	 });
	
	$('input[name=tipoFiltro]').click(function() {
		 if (validateConsultarArquivoTextoOSCobrancaSmartphoneActionForm(document.forms[0])){
			 var form = document.ConsultarArquivoTextoOSCobrancaSmartphoneActionForm;	
			 form.action = 'exibirConsultarDadosArquivoTextoOSCobrancaSmartphoneAction.do?tipoPesquisa=tipoFiltro';
		     form.submit();
		 } else {
			$('input[name=tipoFiltro]').attr('checked', '');
		 }		
	});
	facilitador()
});
</script>
</body>

</html:html>
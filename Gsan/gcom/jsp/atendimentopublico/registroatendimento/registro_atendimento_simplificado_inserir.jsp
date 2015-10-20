<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.atendimentopublico.bean.OcorrenciaOperacionalHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" 
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js">
</script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script type="text/javascript" 
	src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<script type="text/javascript" 
	src="<bean:message key="caminho.jquery"/>jquery_util.js"></script>
 
<script language="JavaScript">
	function validarForm() {
		var form = document.forms[0];
		
		form.action = 'inserirRegistroAtendimentoSimplificadoAction.do';
		form.submit();
	}

	function selecionarTipo(tipo){
		var form = document.forms[0];
		if(tipo==1){
			form.tipoAtendimentoSelecionado.value = 1;
			document.getElementById('tipoOperacional').style.display = 'block';
			document.getElementById('tipoReiteracao').style.display = 'none';
			document.getElementById('tipoInformacao').style.display = 'none';
		}else if(tipo==2){
			form.tipoAtendimentoSelecionado.value = 2;
			document.getElementById('tipoOperacional').style.display = 'none';
			document.getElementById('tipoReiteracao').style.display = 'none';
			document.getElementById('tipoInformacao').style.display = 'none';
		}else if(tipo==3){
			form.tipoAtendimentoSelecionado.value = 3;
			document.getElementById('tipoOperacional').style.display = 'none';
			document.getElementById('tipoReiteracao').style.display = 'block';
			document.getElementById('tipoInformacao').style.display = 'none';
		}else if(tipo==4){
			form.tipoAtendimentoSelecionado.value = 4;
			document.getElementById('tipoOperacional').style.display = 'none';
			document.getElementById('tipoReiteracao').style.display = 'none';
			document.getElementById('tipoInformacao').style.display = 'block';
		}
	}

	function verificaSelecao(){
		var form = document.forms[0];
		if(form.tipoAtendimentoSelecionado.value==1){
			document.getElementById('tipoOperacional').style.display = 'block';
			document.getElementById('tipoReiteracao').style.display = 'none';
			document.getElementById('tipoInformacao').style.display = 'none';
		}else if(form.tipoAtendimentoSelecionado.value==2){
			document.getElementById('tipoOperacional').style.display = 'none';
			document.getElementById('tipoReiteracao').style.display = 'none';
			document.getElementById('tipoInformacao').style.display = 'none';
		}else if(form.tipoAtendimentoSelecionado.value==3){
			document.getElementById('tipoOperacional').style.display = 'none';
			document.getElementById('tipoReiteracao').style.display = 'block';
			document.getElementById('tipoInformacao').style.display = 'none';
		}else if(form.tipoAtendimentoSelecionado.value==4){
			document.getElementById('tipoOperacional').style.display = 'none';
			document.getElementById('tipoReiteracao').style.display = 'none';
			document.getElementById('tipoInformacao').style.display = 'block';
		}
	}

	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		} else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			} else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];

	    if (tipoConsulta == 'registroAtendimento') {
	      if(!form.numeroRA.disabled){
		      form.numeroRA.value = codigoRegistro;
		      form.descricaoRA.value = descricaoRegistro;
		      form.descricaoRA.style.color = "#000000";
	      }
	    } else if (tipoConsulta == 'municipio') {
		    form.idMunicipioRA.value = codigoRegistro;
		    form.nomeMunicipioRA.value = descricaoRegistro;
  			form.nomeMunicipioRA.style.color = "#000000";

  			form.action = 'exibirInserirRegistroAtendimentoSimplificadoAction.do?pesquisarMunicipio=sim';
  			form.submit();
    	} 
	    
	}
	
    function limparMunicipio() {
        var form = document.forms[0];
    	
		form.action = 'exibirInserirRegistroAtendimentoSimplificadoAction.do?limparMunicipio=sim';
		form.submit();
    }

	function limparRA() {
    	var form = document.forms[0];

    	form.numeroRA.value = "";
    	form.descricaoRA.value = "";
  	}

  	function limparDescricaoRA() {
    	var form = document.forms[0];

    	if (form.numeroRA == null || form.numeroRA.value == '') {
        	form.descricaoRA.value = "";
        }
  	}

  	function consultarOcorrencias() {
    	var form = document.forms[0];

		form.action = 'exibirInserirRegistroAtendimentoSimplificadoAction.do?consultarOcorrencias=sim';
		form.submit();
  	}

  	function desabilitarCampoRA(){
    	var form = document.forms[0];

  	  	if (form.numeroProtocolo != null 
  	    	  	&& form.numeroProtocolo.value != '') {
	    	  	
  	  	  	form.numeroRA.value = '';
			form.numeroRA.readOnly = true;
			form.numeroRA.style.backgroundColor = '#EFEFEF';
			
  	  	} 

  	  	if (form.numeroRA != null 
  	    	  	&& form.numeroRA.value != '') {
	    	  	
  	  	  	form.numeroProtocolo.value = '';
			form.numeroProtocolo.readOnly = true;
			form.numeroProtocolo.style.backgroundColor = '#EFEFEF';
			
  	  	} 

  	  	if ((form.numeroRA == null || form.numeroRA.value == '')
  	    	  	&& (form.numeroProtocolo == null || form.numeroProtocolo.value == '')) {
			form.numeroRA.readOnly = false;
			form.numeroRA.style.backgroundColor = '';
			form.numeroProtocolo.readOnly = false;
			form.numeroProtocolo.style.backgroundColor = '';
  	  	}
  	}

  	function limparOcorrencias() {
    	var form = document.forms[0];

		form.action = 'exibirInserirRegistroAtendimentoSimplificadoAction.do?limparOcorrencias=sim';
		form.submit();
  	}

  	function desabilitarMunicipio() {
  		var form = document.forms[0];
  		
  		if (form.idMunicipioRA.value != null 
  		  		&& form.nomeMunicipioRA.value != null 
  		  		&& form.nomeMunicipioRA.value != "" 
  	  		  	&& form.nomeMunicipioRA.value != "MUNICÍPIO INEXISTENTE"){
  		
			form.idMunicipioRA.readOnly = true;
			form.idMunicipioRA.style.backgroundColor = '#EFEFEF';
  		} else {
			form.idMunicipioRA.readOnly = false;
			form.idMunicipioRA.style.backgroundColor = '';
  		}
  	}

  	function pesquisarMunicipio() {
  		var form = document.forms[0];
  	 	
  	 	if (form.idMunicipioRA.readOnly) {
  	 		alert("Para realizar uma pesquisa de município é necessário apagar o município atual.");
  		} else {
  			chamarPopup('exibirPesquisarMunicipioAction.do', 'municipio', null, null, 275, 480, '', document.forms[0].municipio);
  		}
  	}
  	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="verificaSelecao();desabilitarCampoRA();desabilitarMunicipio();">

<html:form action="/inserirRegistroAtendimentoSimplificadoAction"
	name="InserirRegistroAtendimentoSimplificadoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.InserirRegistroAtendimentoSimplificadoActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>


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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			
			<html:hidden property="tipoAtendimentoSelecionado"/>
			
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Registro de Atendimento Simplificado</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
				<table width="100%" border="0">
					 <tr>
				      	<td colspan="2" HEIGHT="40" align="center">
				      		<span style="font-size: 18px;font-weight: bold;">
				      		Nº Protocolo: ${sessionScope.protocoloAtendimento}</span>
				      	</td>
			        </tr>
					<tr>
						<td colspan="2">Para inserir o registro de atendimento simplificado, informe os dados gerais abaixo:</td>
					</tr>
					
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					
			        <tr>
				      	<td><strong>Data do Atendimento:<font color="#FF0000">*</font></strong></td>
				        <td>
							<html:text property="dataAtendimento" 
								size="11" maxlength="10" tabindex="1" 
								onkeyup="mascaraData(this, event);" 
								onkeypress="return isCampoNumerico(event);" 
								readonly="true"
								style="border: 0pt none ; background-color:#EFEFEF;"/>
							<strong>&nbsp;(dd/mm/aaaa)</strong>
						</td>
				    </tr>
				    <tr>
				      	<td><strong>Hora do Atendimento:<font color="#FF0000">*</font></strong></td>
				        <td>
							<html:text property="horaAtendimento" 
								size="10" maxlength="5" tabindex="2" 
								onkeyup="mascaraHoraSemMensagem(this, event)" 
								onkeypress="return isCampoNumerico(event);"
								readonly="true"
								style="border: 0pt none ; background-color:#EFEFEF;"/>
							<strong>&nbsp;(hh:mm)</strong>
						</td>
				    </tr>
				    <tr>
				      	<td><strong>Nome Solicitante:<font color="#FF0000">*</font></strong></td>
				        <td>
							<html:text property="nome" 
								styleClass="tipoAlfaNumerico"
								size="42" 
								maxlength="60" 
								tabindex="3" 
							/>
						</td>
				    </tr>
				    
			        <tr>
				      	<td><strong>Tipo de Atendimento:<font color="#FF0000">*</font></strong></td>
				        <td>
				        	<strong>
								<html:radio property="tipoAtendimento" value="1" tabindex="4" onclick="selecionarTipo(this.value);"/>Operacional
								<html:radio property="tipoAtendimento" value="2" tabindex="5" onclick="selecionarTipo(this.value);"/>Comercial
								<html:radio property="tipoAtendimento" value="3" tabindex="6" onclick="selecionarTipo(this.value);"/>Reiteração
								<html:radio property="tipoAtendimento" value="4" tabindex="7" onclick="selecionarTipo(this.value);"/>Informação
							</strong>
						</td>
			        </tr>
			        
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					
					<tr>
						<td>
							<strong>Documento:</strong>
						</td>
						<td>
							<strong>CPF:&nbsp;</strong>
							<html:text property="numeroCpf" 
								styleClass="tipoInteiro"
								size="13" 
								maxlength="11" 
								tabindex="8"
								onkeypress="return isCampoNumerico(event);"/>
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td>
							<strong>RG:&nbsp;&nbsp;&nbsp;</strong>
							<html:text property="numeroRg" 
								styleClass="tipoInteiro"
								size="13" 
								maxlength="13" 
								tabindex="9"
								onkeypress="return isCampoNumerico(event);" />
							<strong>&nbsp;&nbsp;&nbsp;Órg. Exp.:&nbsp;</strong>
							<strong> 
							<html:select property="idOrgaoExpedidor" style="width: 70px;">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>
						
								<logic:present name="colecaoOrgaoExpedidor" scope="session">
									<html:options collection="colecaoOrgaoExpedidor"
										labelProperty="descricaoAbreviada" 
										property="id" />
								</logic:present>
							</html:select> 														
							</strong>
							<strong>&nbsp;&nbsp;&nbsp;UF:&nbsp;</strong>
							<strong> 
							<html:select property="idUnidadeFederacao" style="width: 55px;">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>
						
								<logic:present name="colecaoUnidadeFederacao" scope="session">
									<html:options collection="colecaoUnidadeFederacao"
										labelProperty="sigla" 
										property="id" />
								</logic:present>
							</html:select> 														
							</strong>
						</td>
					</tr>
					
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
				
				<div id="tipoOperacional" style="display:none;">
					<table width="100%" border="0">
						<tr>
							<td>
								<strong>Município:<font color="#FF0000">*</font></strong>
							</td>
							<td>
								<html:text maxlength="5" tabindex="10" property="idMunicipioRA" size="5"
									styleClass="tipoInteiro"
									onkeypress="validaEnterComMensagem(event, 'exibirInserirRegistroAtendimentoSimplificadoAction.do?pesquisarMunicipio=sim','idMunicipioRA','Município');"
									/> 
									
									<a href="javascript:pesquisarMunicipio();">
		
										<img width="23" 
											height="21" 
											border="0"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Município" /></a> 
		
								<logic:present name="municipioInexistente" scope="session">
									<html:text property="nomeMunicipioRA" 
										size="30"
										maxlength="30" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: red" />
								</logic:present> 
		
								<logic:notPresent name="municipioInexistente" scope="session">
									<html:text property="nomeMunicipioRA" 
										size="30"
										maxlength="30" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notPresent>
		
								<a href="javascript:limparMunicipio();">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" 
										title="Apagar Município" /></a>
							
							</td>
						</tr>
						<tr>
							<td>
								<strong>Localidade:</strong>
							</td>
							<td>
								<strong> 
								<html:select property="idLocalidadeRA" 
										style="width: 230px;"
										onchange="limparOcorrencias();">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
							
									<logic:present name="colecaoLocalidade" scope="session">
										<html:options collection="colecaoLocalidade"
											labelProperty="descricao" 
											property="id" />
									</logic:present>
								</html:select> 														
								</strong>
							</td>
						</tr>
						<tr>
							<td>
								<strong>Bairro:</strong>
							</td>
							<td>
								<strong> 
								<html:select property="idBairro" 
										style="width: 230px;"
										onchange="limparOcorrencias();">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
							
									<logic:present name="colecaoBairro" scope="session">
										<html:options collection="colecaoBairro"
											labelProperty="nome" 
											property="id" />
									</logic:present>
								</html:select> 														
								</strong>
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td>
								<strong>Tipo de Ocorrência:</strong>
							</td>
							<td>
								<strong> 
								<html:select property="idTipoOcorrencia" 
										style="width: 230px;"
										onchange="limparOcorrencias();">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
							
									<logic:present name="colecaoOcorrenciaTipo" scope="session">
										<html:options collection="colecaoOcorrenciaTipo"
											labelProperty="descricao" 
											property="id" />
									</logic:present>
								</html:select> 														
								</strong>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="button" name="consultar"
									class="bottonRightCol" value="Consultar"
									onClick="javascript:consultarOcorrencias();" />
							</td>
						</tr>
					</table>
					<logic:present name="colecaoOcorrenciaOperacionalHelper" scope="session">
						<table width="100%" bgcolor="#79bbfd" >
							<tr>
								<td width="5%" bgcolor="#90c7fc" align="center" rowspan="3">
									<strong>Selecionar</strong>
								</td>
								<td width="95%" bgcolor="#90c7fc"	align="center" colspan="4" >
									<strong>Ocorrência</strong>
								</td>
							</tr>
							<tr>
								<td width="35%" bgcolor="#90c7fc" align="center" >
									<strong>Data/Hora</strong>
								</td>
								<td width="30%" bgcolor="#90c7fc" align="center" >
									<strong>Previsão</strong>
								</td>
								<td width="30%" bgcolor="#90c7fc" align="center">
									<strong>Reprogramação</strong>
								</td>
							</tr>
							<tr>
								<td width="95%" bgcolor="#90c7fc" align="center" colspan="4">
									<strong>Áreas Afetadas</strong>
								</td>
							</tr>
						</table>
						<table width="100%" bgcolor="#90c7fc">
							<%int cont = 0;%>
							<logic:iterate name="colecaoOcorrenciaOperacionalHelper"
									id="ocorrenciaOperacionalHelper"
									type="OcorrenciaOperacionalHelper"
									scope="session">
								<%cont = cont + 1;
								if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">
									<%} else {%>
								</tr>
								<tr bgcolor="#FFFFFF">
									<%}%>
									<td width="11%" rowspan="3">
										<div align="center"><html:radio property="idOcorrenciaOperacional"
											value="${ocorrenciaOperacionalHelper.idOcorrencia}" 
											onmousedown="selecionarRadio(this, event);" 
											onclick="evitarEvento(event);"  
											/>
										</div>
									</td>
									<td align="center" width="89%" colspan="4">
										<bean:write name="ocorrenciaOperacionalHelper" property="ocorrencia" />
									</td>
								</tr>
								<%if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">
									<%} else {%>
								</tr>
								<tr bgcolor="#FFFFFF">
									<%}%>
									<td align="center" width="29%">
										&nbsp;
										<bean:write name="ocorrenciaOperacionalHelper" property="dataHora" />
										&nbsp;
									</td>
									<td align="center" width="30%">
										&nbsp;
										<bean:write name="ocorrenciaOperacionalHelper" property="previsao" />
										&nbsp;
									</td>
									<td align="center" width="30%">
										&nbsp;
										<bean:write name="ocorrenciaOperacionalHelper" property="reprogramacao" />
										&nbsp;
									</td>
								</tr>
								<%if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">
									<%} else {%>
								</tr>
								<tr bgcolor="#FFFFFF">
									<%}%>
									<td align="center" width="89%" colspan="4">
										<bean:write name="ocorrenciaOperacionalHelper" property="areaAfetada" />
									</td>
								</tr>
							</logic:iterate>
						</table>
					</logic:present>
				</div>
				
				<div id="tipoReiteracao" style="display:none;">
					<table width="100%" border="0">
						<tr>
							<td width="25%"><strong>N&uacute;mero do Protocolo:</strong></td>
			                <td>
			                  <html:text property="numeroProtocolo" 
									styleClass="tipoInteiro"
									size="15" 
				                  	maxlength="14"
									tabindex="10" 
									onkeypress="return isCampoNumerico(event);desabilitarCampoRA();"
									onblur="desabilitarCampoRA();"
								/>
			                </td>
						</tr>
								
						<tr>
							<td width="25%"><strong>N&uacute;mero do Registro de Atendimento:</strong></td>
							
							<td>
								
								<html:text maxlength="9" 
									styleClass="tipoInteiro"
									tabindex="1"
									property="numeroRA" 
									size="9"
									onkeypress="desabilitarCampoRA();validaEnterComMensagem(event, 'exibirInserirRegistroAtendimentoSimplificadoAction.do?pesquisarRA=sim','numeroRA','Numero do Registro de Atendimento');return isCampoNumerico(event);"
									onblur="desabilitarCampoRA();"
									onkeyup="limparDescricaoRA();"
									/>
									
									<a href="javascript:chamarPopup('exibirPesquisarRegistroAtendimentoAction.do', 'registroAtendimento', null, null, 600, 730, '', document.forms[0].numeroRA);">
										
										<img width="23" 
											height="21" 
											border="0"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar RA" /></a> 
		
									<logic:present name="numeroRAInexistente" scope="session">
										<html:text property="descricaoRA" 
											size="40"
											maxlength="45" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: red" />
									</logic:present> 
		
									<logic:notPresent name="numeroRAInexistente" scope="session">
										<html:text property="descricaoRA" 
											size="40"
											maxlength="45" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notPresent>
		
									<a href="javascript:limparRA();desabilitarCampoRA();"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar RA" /></a>
								</td>
						</tr>
						<tr>
					      	<td width="25%"><strong>Observação:<font color="#FF0000">*</font></strong></td>
					        <td>
								<html:textarea property="observacao" cols="40" rows="4" 
									onkeypress="return limitarTextArea(document.forms[0].observacao, 365, document.getElementById('utilizadoObservacao'), document.getElementById('limiteObservacao'),event);"/><br>
								<strong><span id="utilizadoObservacao">0</span>/<span id="limiteObservacao">365</span></strong>
							</td>
				      	</tr>
					</table>
				</div>
				
				<div id="tipoInformacao" style="display:none;">
					<table width="100%" border="0">
						<tr>
							<td>
								<strong>Localidade:<font color="#FF0000">*</font></strong>
							</td>
							<td>
								<strong> 
								<html:select property="idLocalidadeInfo" style="width: 230px;">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
							
									<logic:present name="colecaoLocalidadeInfo" scope="session">
										<html:options collection="colecaoLocalidadeInfo"
											labelProperty="descricao" 
											property="id" />
									</logic:present>
								</html:select> 														
								</strong>
							</td>
						</tr>
						<tr>
					      	<td><strong>Descrição da Informação:<font color="#FF0000">*</font></strong></td>
					        <td>
								<html:textarea property="descricaoInformacao" cols="40" rows="4" 
									onkeypress="return limitarTextArea(document.forms[0].descricaoInformacao, 400, document.getElementById('utilizadoDescricaoInformacao'), document.getElementById('limiteDescricaoInformacao'),event);"/><br>
								<strong><span id="utilizadoDescricaoInformacao">0</span>/<span id="limiteDescricaoInformacao">400</span></strong>
							</td>
				      	</tr>
					</table>
				</div>
				
				<table width="100%" border="0">
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
	
					<tr>
						<td colspan="2" align="center">
							<strong><font color="#FF0000">*</font></strong>
							Campos obrigat&oacute;rios
						</td>
					</tr>
	
					<tr>
						<td>
							<input name="Button" type="button" class="bottonRightCol"
								value="Limpar" align="left"
								onclick="window.location.href='<html:rewrite page="/exibirInserirRegistroAtendimentoSimplificadoAction.do?menu=sim"/>'">
							<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td align="right">
							<input type="button" name="concluir"
								class="bottonRightCol" value="Concluir"
								onClick="javascript:validarForm();" />
						</td>
					</tr>
				</table>
			</td>
			</tr>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%> 
	</html:form>
</body>
</html:html>
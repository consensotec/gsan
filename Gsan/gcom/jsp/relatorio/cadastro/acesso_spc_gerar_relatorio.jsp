<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript">
	
	function replicaDataParcelamento() {
	  var form = document.forms[0];
	  form.dataReferenciaFinal.value = form.dataReferenciaInicial.value;
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg, fieldName){
		var campo = document.forms['GerarRelatorioAcessoSPCActionForm'].elements[fieldName]
		if(campo.disabled == false) {
		
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else {
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else {
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.GerarRelatorioAcessoSPCActionForm;
		if (tipoConsulta == 'usuario') {
			  form.loginUsuarioResponsavel.value = codigoRegistro;
			  form.nomeUsuarioResponsavel.value = descricaoRegistro;
			  form.nomeUsuarioResponsavel.style.color = "#000000";
	    }
	    else if(tipoConsulta == 'unidadeOrganizacional'){
	    	form.idUnidadeOrganizacional.value = codigoRegistro;
			form.descricaoUnidadeOrganizacional.value = descricaoRegistro;
			form.descricaoUnidadeOrganizacional.style.color = "#000000";
			
	    } 	
	}
	
	function abrirCalendarioVerificandoBloqueio(formName, fieldName) {
		var campo = document.forms[formName].elements[fieldName]
		if(campo.disabled == false) {
		    nomeForm = formName;
		    nomeCampo = fieldName;
			centerpopup('./jsp/util/calendario.jsp','calendario',225,268);
		}
	}
	
	function abrirPopupDependenciaVerificandoBloqueio(url, idDependencia, nomeMSG, altura, largura, fieldName){
		var campo = document.forms['GerarRelatorioAcessoSPCActionForm'].elements[fieldName]
		if(campo.disabled == false) {
			if (idDependencia.length < 1 || isNaN(idDependencia) || ((idDependencia * 1) == 0)){
				alert("Informe " + nomeMSG);
			}
			else{
				abrirPopup(url , altura, largura);
			}
		}
	}
	
	function chamarPopup(url, tipo,altura, largura, objetoRelacionado,nomeDependencia,valorDependencia){
		if(objetoRelacionado.disabled != true){
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + nomeDependencia + "=" + valorDependencia, altura, largura);
		}
	}
	
	function limparForm(){
		limparUnidadeOrganizacional();
		limparUsuarioResponsavel();
		limparDatasReferencia();
	}
	
	function limparUnidadeOrganizacional() {
		var form = document.GerarRelatorioAcessoSPCActionForm;
		form.idUnidadeOrganizacional.value = "";
		form.descricaoUnidadeOrganizacional.value = "";	
	}
	
	function limparUsuarioResponsavel() {
		var form = document.GerarRelatorioAcessoSPCActionForm;
		form.loginUsuarioResponsavel.value = "";
		form.nomeUsuarioResponsavel.value = "";	
	}
	
	function limparDatasReferencia(){
		var form = document.GerarRelatorioAcessoSPCActionForm;
		form.dataReferenciaInicial.value = "";
		form.dataReferenciaFinal.value = "";
	}
	
	function validarForm(dataHoraServidor){
		var form = document.forms[0]; 

		var dataIni = validaDataSemMensagem(form.dataReferenciaInicial);
		var dataFim = validaDataSemMensagem(form.dataReferenciaFinal);
		
		if(!dataIni){
			if(form.dataReferenciaInicial == null || form.dataReferenciaInicial.value == ""){
				if(form.dataReferenciaFinal == null || form.dataReferenciaFinal.value == ""){
					alert("Informe a Data Inicial do Período\nInforme a Data Final do Período");	
				}else{
					alert("Informe a Data Inicial do Período");	
				}
			}else{
				alert("Data inicial inválida");	
			}
		}else{
			if(!dataFim){
				if(form.dataReferenciaFinal == null || form.dataReferenciaFinal.value == ""){
					alert("Informe a Data Final do Período");	
				}else{
					alert("Data final inválida");	
				}
			}else{
				if(comparaData(form.dataReferenciaFinal.value, "<", form.dataReferenciaInicial.value)){
					alert("Data inicial informada é maior que data final");
				}else{
					
					if(comparaData(dataHoraServidor, "<", form.dataReferenciaInicial.value)){
						alert("Data inicial informado maior que a data corrente");	
					}else{
						if(comparaData(dataHoraServidor, "<", form.dataReferenciaFinal.value)){
							alert("Data final informado maior que a data corrente");	
						}else{
							toggleBox('demodiv',1);
						}
					}
				}	
			}
		}

	}
</script>
	
</head>

<html:javascript staticJavascript="false" formName="GerarRelatorioAcessoSPCActionForm" />

<body leftmargin="5" topmargin="5" onload="">

<div id="formDiv">
	<html:form action="/exibirGerarRelatorioAcessoSPCAction.do"
		name="GerarRelatorioAcessoSPCActionForm"
		type="gcom.gui.relatorio.cadastro.GerarRelatorioAcessoSPCActionForm"
		method="post">
	
		
		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp" %>
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
		
		         <%@ include file="/jsp/util/mensagens.jsp"%>
		
		        <p align="left">&nbsp;</p>
		        <p align="left">&nbsp;</p>
		        <p align="left">&nbsp;</p>
		        <p align="left">&nbsp;</p>
		        <p align="left">&nbsp;</p>
		        <p align="left">&nbsp;</p>
		        <p align="left">&nbsp;</p>
		      </div></td>
		    <td width="625" valign="top" class="centercoltext">
		      <table height="100%">
		        <tr>
		          <td></td>
		        </tr>
		      </table>
	
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Gerar Relatório de Acessos SPC</td>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
	
			<p>&nbsp;</p>
	
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o relatório do SPC, informa os dados abaixo:</td>
				</tr>
					
				<tr>
					<td width="27%"><strong>Período de Referência de Acessos:</strong><font color="#FF0000">*</font></td>
					<td><strong> <html:text property="dataReferenciaInicial" size="10" maxlength="10" onkeypress="return isCampoNumerico(event);"
						onkeyup="mascaraData(this,event);replicaDataParcelamento();" tabindex="1"/>
						<a href="javascript:abrirCalendarioVerificandoBloqueio('GerarRelatorioAcessoSPCActionForm', 'dataReferenciaInicial')"><img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" border="0" align="middle" alt="Exibir Calendário" /></a>&nbsp;a&nbsp;</strong><html:text property="dataReferenciaFinal" size="10" maxlength="10" onkeypress="return isCampoNumerico(event);"  
							onkeyup="mascaraData(this,event)" tabindex="2"/> 
						<a href="javascript:abrirCalendarioVerificandoBloqueio('GerarRelatorioAcessoSPCActionForm', 'dataReferenciaFinal')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
						(dd/mm/aaaa)
					</td>
				</tr>	
							
				<tr>
					<td width="27%"><strong>Unidade Organizacional: </strong></td>
					<td><strong> 
							<html:text property="idUnidadeOrganizacional" size="12" maxlength="5" tabindex="3"
									   onkeyup="javascript:validaEnterComMensagem(event, 'exibirGerarRelatorioAcessoSPCAction.do?pesquisarUnidade=sim', 'idUnidadeOrganizacional', 'Unidade Organizacional');" />
							<a 	href="javascript:chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 'unidadeEmpresa', 495, 300, '',document.forms[0].idUnidadeOrganizacional,'','');">
								<img border="0" title="Pesquisar Unidade Organizacional" src="imagens/pesquisa.gif" height="21" width="23"></a>
						
							<logic:present name="unidadeOrganizacionalInexistente" scope="request">
								<html:text property="descricaoUnidadeOrganizacional" style="background-color:#EFEFEF; border:0; color: #ff0000" 
									readonly="true" size="34" maxlength="40" />
							</logic:present> 
	
							<logic:notPresent name="unidadeOrganizacionalInexistente" scope="request">
								<html:text property="descricaoUnidadeOrganizacional" style="background-color:#EFEFEF; border:0"  
									readonly="true" size="34" maxlength="40" />
							</logic:notPresent> 
	
							<a href="javascript:limparUnidadeOrganizacional();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
							</a> 
						</strong>
					</td>
				</tr>
				
				<tr>
					<td width="27%"><strong>Usuário:</strong></td>
					<td><strong> 
							<html:text maxlength="11" name="GerarRelatorioAcessoSPCActionForm" property="loginUsuarioResponsavel" size="12"
								onkeypress="javascript: validaEnterStringSemUpperCase(event, 'exibirGerarRelatorioAcessoSPCAction.do?pesquisarUsuarioResponsavel=sim', 'loginUsuarioResponsavel');" style="text-transform: none;"/>
					
							<a href="javascript:abrirPopup('exibirUsuarioPesquisar.do?mostrarLogin=s', 250, 495);">
								<img border="0" title="Pesquisar Usuário Responsável" src="imagens/pesquisa.gif" height="21" width="23"></a>
							<logic:present name="usuarioResponsavelInexistente" scope="request"><html:text property="nomeUsuarioResponsavel" style="background-color:#EFEFEF; border:0; color: #ff0000" 
									readonly="true" size="34" maxlength="40" /></logic:present>							
							<logic:notPresent name="usuarioResponsavelInexistente" scope="request">
								<html:text property="nomeUsuarioResponsavel" style="background-color:#EFEFEF; border:0"
									readonly="true" size="34" maxlength="40" />
							</logic:notPresent> 
							
							<a href="javascript:limparUsuarioResponsavel();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
							</a> 
						</strong>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="right">
					<div align="left"><strong> </strong></div>
					</td>
				</tr>
	
				<tr>
					<td>
						<div align="left">
							<input type="button" name="limpar" class="bottonRightCol" value="Limpar" onclick="javascript:limparForm();"
								tabindex="5">
							<input type="button" name="adicionar" class="bottonRightCol" value="Cancelar" 
				                onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</div>
					</td>
					
					<td>
						<div align="right">
							<input type="button" name="botaoConcluir" class="bottonRightCol" 
								value="Gerar" onclick="javascript:validarForm('<c:out value="${dataHoraServidor}"></c:out>');">
						</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
</div>

<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioAcessoSPCAction.do" />

</html:form>
</html:html>
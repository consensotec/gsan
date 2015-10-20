<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">

	function atualizar(){
		var form = document.forms[0];
		form.action = 'atualizarIndicadorCpfCnpjClientePopupAction.do';
		form.submit();
	}

</script>
</head>

<logic:notPresent name="fecharPopup">
	<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(400, 220);">
</logic:notPresent>
<logic:present name="fecharPopup">
	<logic:equal name="aba" value="1">
		<body leftmargin="0" topmargin="0" onload="chamarSubmitComUrl('consultarImovelWizardAction.do?destino=1&action=exibirConsultarImovelDadosCadastraisAction');window.close();">
	</logic:equal>
	<logic:notEqual name="aba" value="1">
		<body leftmargin="0" topmargin="0" onload="chamarSubmitComUrl('consultarImovelWizardAction.do?destino=5&action=exibirConsultarImovelDebitosAction');window.close();">
	</logic:notEqual>
</logic:present>

<html:form action="/atualizarIndicadorCpfCnpjClientePopupAction" 
	name="AtualizarIndicadorCpfCnpjClientePopupActionForm"
	type="gcom.gui.cadastro.cliente.AtualizarIndicadorCpfCnpjClientePopupActionForm"
	method="post">
	
	<html:hidden property="idCliente" />
	<html:hidden property="aba" />

	<table width="100%" border="0" cellspacing="5" cellpadding="0">

		<tr>

			<td width="100%" valign="top" class="centercoltext">

				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
	
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Validar CPF/CNPJ do Cliente</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<p>&nbsp;</p>

				<table width="100%">
 					<tr>
						<td><strong>Validar o CPF/CNPJ?:</strong></td>
						<td>
							<html:radio property="indicadorValidarCpfCnpj"
								value="<%=ConstantesSistema.SIM.toString()%>" tabindex="8" />
							<strong>Sim</strong> <html:radio property="indicadorValidarCpfCnpj"
								value="<%=ConstantesSistema.NAO.toString()%>" tabindex="8" />
							<strong>Não</strong>
						</td>
					</tr>
				</table>
				
				<p>&nbsp;</p>
				
				<table width="100%">
					<tr>
						<td align="left">
							<input type="button" name="ButtonFechar" class="bottonRightCol"
							value="Fechar" tabindex="8"	onclick="window.close();" />
						</td>
						<td align="right">
							<logic:present name="bloquearAtualizar">
								<input type="button" name="ButtonAtualizar" class="bottonRightCol"
									disabled="disabled" value="Atualizar" tabindex="8" onclick="atualizar();" />
							</logic:present>
							<logic:notPresent name="bloquearAtualizar">
								<input type="button" name="ButtonAtualizar" class="bottonRightCol"
									value="Atualizar" tabindex="8" onclick="atualizar();" />
							</logic:notPresent>
						</td>
					</tr>
				</table>		
				
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
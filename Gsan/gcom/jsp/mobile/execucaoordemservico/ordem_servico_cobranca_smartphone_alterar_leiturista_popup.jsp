<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ConsultarArquivoTextoOSCobrancaSmartphoneActionForm" />

<script language="JavaScript">
	function validarForm(form){
		if(document.forms[0].idAgenteComercial.value == "-1"){
			alert('Selecione um Agente Comercial');
		}else{
			enviarDados(document.forms[0].idAgenteComercial.value, "", "");
		}
	}
</script>

</head>

<html:form action="/validarArquivoTextoOSCobrancaSmartphoneAction"
	name="ConsultarArquivoTextoOSCobrancaSmartphoneActionForm"
	type="gcom.gui.mobile.ConsultarArquivoTextoOSCobrancaSmartphoneActionForm"
	method="post"
	onsubmit="">
	<table width="570" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="520" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">
					
						Alterar Agente Comercial
					
					</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td><strong>Agente Comercial<strong><font color="#FF0000">*</font></strong>:</strong></td>
					<td colspan="2" align="left">
						<html:select property="idAgenteComercial" tabindex="4"  style="width: 300px;" >
						  <html:option value="-1">&nbsp;</html:option>
						  <logic:present name="colecaoAgenteComercial">
							  <html:options collection="colecaoAgenteComercial" labelProperty="nome" property="id" />
						  </logic:present>
						</html:select>
					</td>

				</tr>

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td height="0">&nbsp;</td>
				</tr>

				<tr>
					<td><input type="button" name="Button" class="bottonRightCol"
						value="Fechar" tabindex="4" onClick="window.close()" /></td>
					<td align="right">
						<input type="button" name="Button"
						class="bottonRightCol" value="Atualizar" tabindex="3"
						onClick="validarForm(document.forms[0]);" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>

</html:html>

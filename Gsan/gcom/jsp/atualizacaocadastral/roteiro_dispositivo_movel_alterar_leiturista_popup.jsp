<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ConsultarRoteiroDispositivoMovelActionForm" />
<script language="JavaScript">


<!-- Begin

  
	function validarForm(form){
		if(document.forms[0].leituristaID.value == "-1")
			alert('Selecione um Cadastrador');
		else
			enviarDados(document.forms[0].leituristaID.value, "", "");
	}


-->
</script>

</head>

<html:form action="/validarAtualizacaoCadastralArquivoTextoAction"
	name="ConsultarRoteiroDispositivoMovelActionForm"
	type="gcom.gui.atualizacaocadastral.ConsultarRoteiroDispositivoMovelActionForm"
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
					
						Alterar Cadastrador
					
					</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td><strong>Cadastrador<strong><font color="#FF0000">*</font></strong>:</strong></td>
					<td colspan="2" align="left"><html:select property="leituristaID"
						tabindex="4">
						<html:option value="-1">&nbsp;</html:option>

							<c:if test="${not empty colecaoLeiturista}">
									<c:forEach items="${colecaoLeiturista}" var="leiturista">
										<c:if test="${not empty leiturista.usuario}">
											<option value="${leiturista.id}">${leiturista.usuario.nomeUsuario}</option>
										</c:if>
									</c:forEach>
								</c:if>

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

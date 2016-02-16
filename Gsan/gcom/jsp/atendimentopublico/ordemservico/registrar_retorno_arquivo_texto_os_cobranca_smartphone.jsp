<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript">
	var bCancel = false; 

	function validarForm() {
		var form = document.forms[0];
		var arquivo = form.formFile.value;

		if (!arquivo) {
			alert("Informe o nome do Arquivo");
			return false;
		}

		var extensao = (arquivo.substring(arquivo.lastIndexOf("."))).toLowerCase();
		if (extensao !== '.zip') {
			alert("Arquivo inválido, só é permitido enviar arquivos com extensões .zip ");
			return false;
		}

		return true;
	}
</script>

</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
	
<html:form action="/registrarArquivoTextoOSCobrancaSmartphoneAction.do"
	name="RegistrarArquivoTextoOSCobrancaSmartphoneActionForm"
	type="gcom.gui.mobile.RegistrarArquivoTextoOSCobrancaSmartphoneActionForm"
	method="post" enctype="multipart/form-data">
	
<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp"%>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="115" valign="top" class="leftcoltext">
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
		<p align="left">&nbsp;</p>
		</div>
		</td>

		<td valign="top" class="centercoltext">
		<table><tr><td></td></tr></table>
	
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Registrar Retorno Arquivo Texto das OS de cobranca</td>
				<td width="11" valign="top"><img border="0"
					src="imagens/parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>

		<table width="100%" border="0">
			<tr>
				<td colspan="2">Para registrar o retorno arquivo texto das OS de Cobran&ccedil;a do Smartphone, informe os dados abaixo:</td>
			</tr>

			<tr><td>&nbsp;</td></tr>
			
			<tr>
				<td class="style1" width="20%">
					<strong>Nome do Arquivo:<font color="#FF0000">*</font></strong>
				</td>
				<td class="style1">
					<html:file property="formFile" size="35"/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td></td>
				<td class="style1" colspan="1">
					<font color="#FF0000">*</font> Campos obrigat&oacute;rios
				</td>
			</tr>
		</table>

		<p>&nbsp;</p>
		
		<table width="100%" border="0">
			<tr>
				<td class="style1" align="left">
					<input type="button" name="ButtonDesfazer" class="bottonRightCol" value="Desfazer"
						onclick="window.location.href='<html:rewrite page="/exibirRegistrarArquivoTextoOSCobrancaSmartphoneAction.do?menu=sim"/>'">
					&nbsp;
					<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
				</td>
				<td class="style1" align="right">
					<input name="Atualizar" type="submit" class="bottonRightCol" id="Atualizar" value="Atualizar" onclick="return validarForm();">
				</td>
			</tr>

		</table>
		</td>
	</tr>

</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>

</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gsan.cadastro.geografico.BairroArea"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">

function validarForm(){
	var form = document.forms[0];
	if(form.formFile.value!=""){
		//form.submit();
		//botaoAvancarTelaEspera('/gsan/uploadDadosImoveisTabletOfflineAction.do');
		submitForm(form);
	}else{
		alert("Informe o arquivo");
	}
}
</script>
</head>
<div id="formDiv">
<body leftmargin="5" topmargin="5">
<html:form action="/uploadDadosImoveisTabletOfflineAction.do" name="RecepcionarDadosImoveisTabletOfflineActionForm"
	type="gsan.gui.atualizacaocadastral.RecepcionarDadosImoveisTabletOfflineActionForm" method="post" enctype="multipart/form-data">

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

			</div>
			</td>


			<td valign="top" class="centercoltext">

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
					<td class="parabg">Recepcionar Dados Imoveis Tablet Offline</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
			
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para recepcionar dados imoveis Tablet offline, informe os dados abaixo:</td>
					</tr>
					<tr>
						<td><strong>Nome Arquivo:<font color="#FF0000">*</font></strong></td>
						<td><html:file property="formFile" size="35"/></td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
						<td align="left"><font color="#FF0000">*</font> Campo
						Obrigat&oacute;rio</td>
					</tr>
				</table>
		
				<table width="100%">
					<tr>
						<td>&nbsp;</td>
					</tr>
		
					<tr>
						<td>&nbsp;</td>
					</tr>
		
					
					<tr>
						<td align="left"><input name="Button" type="button"
							class="bottonRightCol" value="Desfazer" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirRecepcionarDadosImoveisTabletOfflineAction.do?menu=sim"/>'">
							
							&nbsp;
							
							<input type="button" name="ButtonCancelar"
								class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
						
						<td align="right">
							<input  type="button" name="Button" class="bottonRightCol"
									value="Atualizar" onclick="validarForm();" />
						</td>
					</tr>
					
				</table>
			
			</table>

			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>
<%@ include file="/jsp/util/telaespera.jsp"%>
</html:html>

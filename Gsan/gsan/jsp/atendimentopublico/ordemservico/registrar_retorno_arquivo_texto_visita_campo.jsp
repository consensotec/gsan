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
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript">
<!--

	var bCancel = false; 
	
	function validarForm(){
	   var form = document.forms[0];

	   if(form.documento.value != null && form.documento.value != ""){
			if(comprova_extensao() == true){
				return true;
			}else{
				return false;
			}
	   }else{
		   	alert("Informe o nome do Arquivo");
			return false;
		}

	   return false;
	}

	function desfazer(){
		var form = document.forms[0];

		form.documento.value = "";
	
	}

	function comprova_extensao() {
		var form = document.forms[0];
		var arquivo = form.documento.value;
		var meuerro = ""; 

	   var   extensao = (arquivo.substring(arquivo.lastIndexOf("."))).toLowerCase(); 

	   var permitida = false; 

        if (extensao == ".txt" || extensao == ".TXT") { 
	         permitida = true; 
        }else{
        	 alert ("Arquivo inválido. Só é permitido enviar arquivos com extensões .txt ");
        } 

	   return permitida; 
	} 

-->
</script>

</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">

<form action="/gsan/registrarArquivoTextoVisitaCampoAction.do"
	method="post" enctype="multipart/form-data"><%@ include
	file="/jsp/util/cabecalho.jsp"%> <%@ include
	file="/jsp/util/menu.jsp"%>

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
		<table>
			<tr>
				<td></td>
			</tr>
		</table>


		<!--Início Tabela Reference a Páginação da Tela de Processo-->
		<table>
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
				<td class="parabg">Registrar Retorno Arquivo Texto da Visita de Campo</td>
				<td width="11" valign="top"><img border="0"
					src="imagens/parahead_right.gif" /></td>
			</tr>
		</table>
		<!--Fim Tabela Reference a Páginação da Tela de Processo-->
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td colspan="2">Para registrar o retorno arquivo texto da visita de campo, informe
				os dados abaixo:</td>
			</tr>

			<tr>
				<td class="style1"><strong>Nome do Arquivo:<strong><font
					color="#FF0000">*</font></strong></td>
				<td class="style1"><input type="file" style="" name="documento"
					size="50" /></td>
			</tr>
			<tr>
				<td class="style1">
				<p>&nbsp;</p>
				</td>
				<td class="style1">
						<font color="#FF0000">*</font>
							Campos obrigat&oacute;rios
				</td>
			</tr>

		</table>
		<br>
		<br>
		<br>
		<table width="100%" border="0">
		
			<tr>
				<td class="style1" align="left">
					<input type="button" name="ButtonDesfazer"
						class="bottonRightCol" value="Desfazer" onClick="desfazer();">
					&nbsp;<input type="button" name="ButtonCancelar"
						class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
				</td>
				<td class="style1" align="right">
					<input name="Atualizar"
							type="submit" class="bottonRightCol" id="Atualizar"
							value="Atualizar" onclick="return validarForm();">
				</td>
			</tr>

		</table>
		</td>
	</tr>

</table>
<%@ include file="/jsp/util/rodape.jsp"%></form>

</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>

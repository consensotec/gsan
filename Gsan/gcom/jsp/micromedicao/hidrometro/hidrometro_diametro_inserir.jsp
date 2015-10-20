<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirHidrometroDiametroActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function validarForm(){
		var form = document.forms[0];
		if (validateInserirHidrometroDiametroActionForm(form)){
			if(validateShort(form)){
				submeterFormPadrao(form);
	   		}
	   }
	}
	
	function ShortValidations () {
		this.aa = new Array("numeroOrdem", "O campo Número da Ordem deve conter apenas números.", new Function ("varName", " return this[varName];"));
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	  var form = document.InserirHidrometroDiametroActionForm;
      form.idDebito.value = codigoRegistro;
      form.descricaoDebito.value = descricaoRegistro;
      form.descricaoDebito.style.color = "#000000";
	}

	function limparDebitoTipo(){
	 var form = document.InserirHidrometroDiametroActionForm;
     form.idDebito.value = "";
     form.descricaoDebito.value = "";
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');">
<html:form action="/inserirHidrometroDiametroAction.do"
	name="InserirHidrometroDiametroActionForm"
	type="gcom.gui.micromedicao.hidrometro.InserirHidrometroDiametroActionForm"
	method="post"
	onsubmit="return validateInserirHidrometroDiametroActionForm(this);">

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
					<td width="11"><img border="0" src="imagens/parahead_left.gif" />
					</td>
					<td class="parabg">Inserir Di&acirc;metro do Hidr&ocirc;metro</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table>
				<tr>
					<td> Para inserir o di&acirc;metro do hidr&ocirc;metro, informe os
					dados abaixo:</td>
				</tr>
			</table>

			<table>				
				<tr>
					<td><strong>Descri&ccedil;&atilde;o: <font
						color="#FF0000">*</font></strong></td>
					<td><html:text property="descricao" maxlength="20"
						size="20"/>
					</td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada: <font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:text property="descricaoAbreviada" size="5"
						maxlength="5"/> </strong></td>
				</tr>
				<tr>
					<td><strong>N&uacute;mero da Ordem: <font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:text property="numeroOrdem" size="5"
						maxlength="4"/> </strong></td>
				</tr>
				
				<tr>
					<td width="30%"><strong>Valor a ser cobrado por depreciação: </strong></td>
					<td><strong> <html:text property="valorCobradoDepreciacao" size="6"
						style="text-align: right;"
						maxlength="6" tabindex="9" 
						onkeypress="javascript:return isCampoNumerico(event);"
						onkeyup="formataValorMonetario(this,6);"/> </strong></td>
				</tr>
				
				<tr>
					<td>
						<strong>Tipo do D&eacute;bito:</strong>
					</td>
					
					<td colspan="3">
						
						<html:text property="idDebito" 
							size="4" 
							maxlength="4"
							tabindex="2"
							onkeypress="javascript:return isCampoNumerico(event);"
							onkeydown="validaEnter(event, 'exibirInserirHidrometroDiametroAction.do', 'idDebito');document.getElementById('descricaoDebito').value = '';"
							styleId="idTipoDebito" />
						
						<a href="javascript:abrirPopup('exibirPesquisarTipoDebitoAction.do', 'tipoDebito', null, null, 400, 800, '',document.getElementById('idTipoDebito'));">
							<img width="23" 
								height="21"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								alt="Pesquisar" 
								border="0" /></a>
						
						
						<logic:present name="debitoTipoEncontrado" scope="session">
							<html:text property="descricaoDebito" 
								size="40" 
								maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								styleId="descricaoTipoDebito" />
						</logic:present> 
						
						<logic:notPresent name="debitoTipoEncontrado" scope="session">
							<html:text property="descricaoDebito" 
								size="40" 
								maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								styleId="descricaoTipoDebito" />
						</logic:notPresent> 
												
						<a href="javascript:limparDebitoTipo();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" />
						</a>

					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campos Obrigat&oacute;rios</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirHidrometroDiametroAction.do?menu=sim"/>'"> <input name="Button"
						type="button" class="bottonRightCol" value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td width="53" height="24" align="right"><input type="button"
						name="Button2" class="bottonRightCol" value="Inserir"
						onClick="javascript:validarForm();" /></td>
					<td width="12">&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>

</html:form>

</body>
</html:html>

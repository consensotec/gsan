<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gsan.util.ConstantesSistema"%>
<%@ page import="gsan.atendimentopublico.contratoadesao.ContratoAdesao" %>

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
	formName="InserirContratoAdesaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">


		function validateAtualizarContratoAdesaoActionForm(form) {
			return true;
		}
		
		function validarForm(){
			var form = document.forms[0];
			if (validateAtualizarContratoAdesaoActionForm(form)){
					submeterFormPadrao(form);
			}	
		}


	function limpar() { 
	var form = document.forms[0];
	form.descricao.value = '';
	form.descricaoAbreviada.value = '';
	form.indicadorUso.value = '';	
	form.arquivo.value ='';
	}

	function validaArquivo(){
		var form = document.forms[0];
		var erros = "";		
		
		if(form.arquivo.value == "" ){
			erros += "\nInforme um Arquivo";
		}

		if(erros != "" ){
			alert(erros);
			return false;
		}else{
			form.action = "exibirAtualizarContratoAdesao.do?adicionarArquivo=S";
			form.submit();
		}		
	}
	

</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');">
				   
<html:form action="/atualizarContratoAdesaoAction.do"
	name="AtualizarContratoAdesaoActionForm"
	type="gsan.gui.atendimentopublico.contratoadesao.AtualizarContratoAdesaoActionForm"
	method="post"
	enctype="multipart/form-data"
	onsubmit="return validateAtualizarContratoAdesaoActionForm(this);">
					
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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
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
					<td class="parabg">Atualizar Contrato de Ades&atilde;o</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para atualizar o  contrato, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td width="230"><strong>Descrição: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="descricao" maxlength="30"
						size="30" style=" width : 287px;"/>
						<br>
					</td>
				</tr>
			
				<tr>
					<td HEIGHT="30"><strong>Descrição Abreviada: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="descricaoAbreviada" maxlength="10"
						size="10" style=" width : 176px;"/>
						<br>
					</td>
				</tr>
				
				<tr>
					<td HEIGHT="30"><strong>Contrato de Adesão (.PDF): <font color="#FF0000">*</font></strong></td>
					<td><input type="file" style="textbox" name="arquivo" size="20"  /></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de uso: </strong></td>
					<td><html:radio property="indicadorUso" value="1" tabindex="5" /><strong>Ativo
					<html:radio property="indicadorUso" value="2" tabindex="6" />Inativo</strong>
					</td>
				</tr>
			
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				<tr>
					<td  align="left" style=" width : 384px;"> 
					
						
						<input name="button" type="button" class="bottonRightCol"
						tabindex="2" value="Voltar"
						onclick="window.history.go(-1)">
						
						 <input type="button"
						name="ButtonReset" class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()">
						
					</td>
					<td align="right"><input type="button"
						onClick="javascript:validarForm(document.forms[0]);"
						name="botaoAtualizar" class="bottonRightCol" value="Atualizar"></td>
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

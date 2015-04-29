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

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarTipoCreditoActionForm" />

<script language="JavaScript">
  
	function validaForm() {
	  	var form = document.forms[0];
	  	form.action = "/gsan/atualizarTipoCreditoAction.do";
		if(validateAtualizarTipoCreditoActionForm(form)) {
	     		  		
				submeterFormPadrao(form);   		  
   	      	
   	    }
	}
 
	function limparForm() {
		var form = document.AtualizarTipoCreditoActionForm;
		form.descricao.value = "";
		form.abreviatura.value = "";
	    form.tipoLancamentoContabil.value = "";
	    form.indicadorgeracaoAutomaica.value = "";
	    form.valorLimiteCredito.value = "";
	    form.indicativoUso.value = "";
			
	}  
	
	function reload() {
		var form = document.AtualizarTipoCreditoActionForm;
		form.action = "/gsan/exibirAtualizarTipoCreditoAction.do";
		form.submit();
	}  
	

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="setarIndicadorTrocaServico('${requestScope.deferimento}', '${servicoTipoReferencia.indicadorExistenciaOsReferencia}');">

<html:form action="/atualizarTipoCreditoAction"
	name="AtualizarTipoCreditoActionForm"
	type="gsan.gui.faturamento.credito.AtualizarTipoCreditoActionForm"
	method="post"
	onsubmit="return validateAtualizarTipoCreditoActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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

			<!-- centercoltext -->

			<td width="625" valign="top" class="centercoltext">

			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Tipo de Cr�dito</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a P�gina��o da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar um tipo de cr�dito, informe os dados
					abaixo:</td>
				</tr>
				<tr>
					<td><strong>Descri��o do Tipo de Cr�dito:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="27" maxlength="20" /> </span></td>
				</tr>

				<tr>
					<td><strong>Descri��o do Tipo de Cr�dito Abreviada:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="abreviatura" size="20" maxlength="20" /> </span></td>
				</tr>


				<!-- Banco -->

				<tr>
					<td><strong>Tipo do Lan�amento do Item Cont�bil:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select
						property="tipoLancamentoContabil">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoTipoLancamentoContabil"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>


				<!-- Conta Banc�ria -->

				<!-- Conta Cont�bil -->

				<tr>
					<td><strong>Indicador de Gera��o Autom�tica do Cr�dito:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorgeracaoAutomaica"
						value="1" /> <strong>Sim <html:radio
						property="indicadorgeracaoAutomaica" value="2" /> N&atilde;o</strong>
					</strong></td>

				</tr>

				<tr>
					<td><strong>Valor Limite do Cr�dito:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text property="valorLimiteCredito" size="15"
						maxlength="15"
						onkeyup="javascript:formataValorMonetario(this,13);"
						style="text-transform: none;" /> </strong></td>
				</tr>

				<tr>
					<td><strong>Indicador de Uso:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicativoUso" value="1" /> <strong>Ativo
					<html:radio property="indicativoUso" value="2" /> Inativo</strong>
					</strong></td>

				</tr>

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>



				<!-- Bot�es -->

				<tr>
					<td colspan="2"><font color="#FF0000"> <logic:present
						name="inserir" scope="request">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirFiltrarAnormalidadeLeitura.do?menu=sim'">
					</logic:present><logic:notPresent name="inserir" scope="request">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar" onClick="javascript:history.back();">
					</logic:notPresent><input type="button" name="ButtonReset"
						class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</font></td>
					<td colspan="2" align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Atualizar" onclick="validaForm();"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</html:html>

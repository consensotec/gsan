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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirParametrosSistemaActionForm"
	dynamicJavascript="false" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--
var bCancel = false;

function validateInserirParametrosSistemaActionForm(form) {
	if (bCancel)
		return true;
	else
		return  validateRequired(form)
		&& validateInteger(form);
		//validateCaracterEspecial(form) 
		//&& validateRequired(form)
		//&& validateInteger(form);
}
function IntegerValidations () {
	this.aa = new Array("diasMaximoReativarRA", "Dias M�ximo para Reativar RA deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("diasMaximoAlterarOS", "Dias M�ximo para Alterar Dados da OS deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("ultimoIDGeracaoRA", "�ltimo ID Utilizado para Gera��o do RA Manual deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("diasMaximoExpirarAcesso", "Dias M�ximo para Expirar o Acesso deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	this.ae = new Array("diasMensagemExpiracaoSenha", "Dias para Come�ar Aparecer a	Mensagem de Expira��o de Senha deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	this.af = new Array("numeroMaximoTentativasAcesso", "N�mero M�ximo de Tentativas de	Acesso deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	this.ag = new Array("numeroMaximoFavoritosMenu", "N�mero M�ximo de Favoritos no	Menu do Sistema deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
}



/*function caracteresespeciais () {
	this.aa = new Array("nomeEstado", "Nome do Estado possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("nomeEmpresa", "Nome da Empresa possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("abreviaturaEmpresa", "Abreviatura da Empresa possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("cnpj", "CNPJ possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}*/

function required () {
	this.aa = new Array("diasMaximoAlterarOS", "Informe Dias M�ximo para Alterar Dados da OS.", new Function ("varName", " return this[varName];"));
	
}


-->
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirParametrosSistemaWizardAction" method="post"
	onsubmit="return validateInserirParametrosSistemaActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=5" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="numeroPagina" value="5" />
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
			<td width="655" valign="top" class="centercoltext">
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
					<td class="parabg">Inserir Par�metros do Sistema</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>
					<td>Para inserir par�metros do sistema, informe os dados abaixo:
					<td align="right"><a
						href="javascript: abrirPopup('/gsan/help/help.jsp?mapIDHelpSet=clienteInserirAbaNomeTipo', 500, 700);"><span
						style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
			</table>

			<table width="100%" border="0">


				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><strong>Par�metros para Atendimento
					ao P�blico:</strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="40%"><strong>Indicador de Sugest�o de Tr�mite:</strong></td>
					<td><strong> <html:radio property="indicadorSugestaoTramite"
						value="1" /> Sim <html:radio property="indicadorSugestaoTramite"
						value="2" /> N&atilde;o </strong></td>

				</tr>


				<tr>
					<td width="40%" align="left"><strong>Dias M�ximo para Reativar RA:</strong></td>
					<td width="87%"><html:text property="diasMaximoReativarRA" size="2"
						maxlength="2" /> <font size="1"> &nbsp; </font></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Dias M�ximo para Alterar Dados
					da OS:<font color="#FF0000">*</font></strong></td>
					<td width="87%"><html:text property="diasMaximoAlterarOS" size="2"
						maxlength="2" /> <font size="1"> &nbsp; </font></td>
				</tr>



				<tr>
					<td width="40%" align="left"><strong>�ltimo ID Utilizado para
					Gera��o do RA Manual:</strong></td>
					<td width="87%"><html:text property="ultimoIDGeracaoRA" size="5"
						maxlength="5" /> <font size="1"> &nbsp; </font></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><strong>Par�metros para Seguran�a:</strong></td>

				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Dias M�ximo para Expirar o
					Acesso:</strong></td>
					<td><html:text maxlength="2" property="diasMaximoExpirarAcesso"
						size="2" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Dias para Come�ar Aparecer a
					Mensagem de Expira��o de Senha:</strong></td>
					<td><html:text maxlength="2" property="diasMensagemExpiracaoSenha"
						size="2" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>N�mero M�ximo de Tentativas de
					Acesso:</strong></td>
					<td><html:text maxlength="2"
						property="numeroMaximoTentativasAcesso" size="2" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>N�mero M�ximo de Favoritos no
					Menu do Sistema:</strong></td>
					<td><html:text maxlength="2" property="numeroMaximoFavoritosMenu"
						size="2" /></td>
				</tr>

				<tr>
					<td><strong></strong></td>
					<td><strong><font color="#FF0000">*</font></strong>Campo
					obrigat&oacute;rio</td>
				</tr>
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=5" /></div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>

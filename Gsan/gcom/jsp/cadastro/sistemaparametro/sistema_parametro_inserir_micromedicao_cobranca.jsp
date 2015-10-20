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
	this.aa = new Array("incrementoMaximoConsumo", "Incremento M�ximo de Consumo por economia em Rateio deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("decrementoMaximoConsumo", "Decremento M�ximo de Consumo por economia em Rateio deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("diasVencimentoCobranca", "N�mero de Dias entre o Vencimento e o In�cio da Cobran�a deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
}



/*function caracteresespeciais () {
	this.aa = new Array("nomeEstado", "Nome do Estado possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("nomeEmpresa", "Nome da Empresa possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("abreviaturaEmpresa", "Abreviatura da Empresa possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("cnpj", "CNPJ possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}*/

function required () {
	this.aa = new Array("incrementoMaximoConsumo", "Informe Incremento M�ximo de Consumo por economia em Rateio.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("decrementoMaximoConsumo", "Informe Decremento M�ximo de Consumo por economia em Rateio.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("diasVencimentoCobranca", "Informe N�mero de Dias entre o Vencimento e o In�cio da Cobran�a.", new Function ("varName", " return this[varName];"));
	
}


-->
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirParametrosSistemaWizardAction" method="post"
	onsubmit="return validateInserirParametrosSistemaActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=4" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="numeroPagina" value="4" />
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
					<td colspan="2" align="center"><strong>Par�metros para
					Micromedi��o:</strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>C�digo da Menor Capacidade de
					Hidr�metro para ser Grande Usu�rio:</strong></td>
					<td><html:select property="codigoMenorCapacidade">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroCapacidade"
							labelProperty="id" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td width="40%"><strong>Indicador de Gera��o de Faixa Falsa:</strong></td>
					<td><strong> <html:radio property="indicadorGeracaoFaixaFalsa"
						value="1" /> Sim <html:radio
						property="indicadorGeracaoFaixaFalsa" value="2" /> N&atilde;o <html:radio
						property="indicadorGeracaoFaixaFalsa" value="3" /> De acordo com
					a Rota</strong></td>

				</tr>

				<tr>
					<td width="40%"><strong>Indicador do Percentual para Gera��o de
					Faixa Falsa:</strong></td>
					<td><strong> <html:radio
						property="indicadorPercentualGeracaoFaixaFalsa" value="1" />
					Percentual Par�metro <html:radio
						property="indicadorPercentualGeracaoFaixaFalsa" value="2" />
					Percentual da Rota </strong></td>

				</tr>

				<tr>
					<td width="40%" align="left"><strong> Percentual de Gera��o de
					Faixa Falsa:</strong></td>
					<td width="87%"><html:text property="percentualGeracaoFaixaFalsa"
						size="5" maxlength="5"
						onkeyup="javascript:formataValorMonetario(this, 5);" /> <font
						size="1"> &nbsp; </font></td>
				</tr>

				<tr>
					<td width="40%"><strong>Indicador de Gera��o de Fiscaliza��o de
					Leitura :</strong></td>
					<td><strong> <html:radio
						property="indicadorGeracaoFiscalizacaoLeitura" value="1" /> Sim <html:radio
						property="indicadorGeracaoFiscalizacaoLeitura" value="2" /> N�o <html:radio
						property="indicadorGeracaoFiscalizacaoLeitura" value="3" /> De
					acordo com a Rota </strong></td>

				</tr>

				<tr>
					<td width="40%"><strong>Indicador do Percentual Gera��o de
					Fiscaliza��o de Leitura :</strong></td>
					<td><strong> <html:radio
						property="indicadorPercentualGeracaoFiscalizacaoLeitura" value="1" />
					Percentual Par�metro <html:radio
						property="indicadorPercentualGeracaoFiscalizacaoLeitura" value="2" />
					Percentual da Rota </strong></td>

				</tr>

				<tr>
					<td width="40%" align="left"><strong>Percentual de Gera��o de
					Fiscaliza��o de Leitura:</strong></td>
					<td width="87%"><html:text
						property="percentualGeracaoFiscalizacaoLeitura" size="7"
						maxlength="7" onkeyup="javascript:formataValorMonetario(this, 7);" />
					<font size="1"> &nbsp; </font></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Incremento M�ximo de Consumo
					por economia em Rateio: <font color="#FF0000">*</font></strong></td>
					<td width="87%"><html:text property="incrementoMaximoConsumo"
						size="9" maxlength="9" /> <font size="1"> &nbsp; </font></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Decremento M�ximo de Consumo
					por economia em Rateio: <font color="#FF0000">*</font></strong></td>
					<td width="87%"><html:text property="decrementoMaximoConsumo"
						size="9" maxlength="9" /> <font size="1"> &nbsp; </font></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Percentual de Toler�ncia para
					o Rateio do Consumo: </strong></td>
					<td width="87%"><html:text
						property="percentualToleranciaRateioConsumo" size="7"
						maxlength="7" onkeyup="javascript:formataValorMonetario(this, 5);" />
					<font size="1"> &nbsp; </font></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><strong>Par�metros para Cobran�a:</strong></td>

				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>




				<tr>
					<td width="40%" align="left"><strong>N�mero de Dias entre o
					Vencimento e o In�cio da Cobran�a:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="2" property="diasVencimentoCobranca"
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
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=4" /></div>
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

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<%@ page import="gcom.util.ConstantesSistema"%>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirSubCategoriaConsumoTarifaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script>
	function redirecionaForm(){
		var formRed = "/gsan/inserirConsumoTarifaSubCategoriaAction.do";
			redirecionarSubmit(formRed);
	}
	function validarForm(form){
		form.submit();          
	}
	
	function preencherSubCategoria(){
		var form = document.InserirSubCategoriaConsumoTarifaActionForm;		
			redirecionarSubmit('exibirInserirSubCategoriaConsumoTarifaAction.do?pesquisarSubCategoria=1&categoria='+form.slcCategoria.value);
	}
	
	function validaForm(){
		var form = document.InserirSubCategoriaConsumoTarifaActionForm;
		
		if (form.slcCategoria.value == "-1"){
			if (form.consumoMinimo.value == ""){
				if (form.valorTarifaMinima.value == ""){
				  alert("Informe Categoria.\nInforme Consumo M�nimo.\nInforme Valor da Tarifa M�nima.");
				}else{
					alert("Informe Categoria.\nInforme Consumo M�nimo.");
				}
			}else{
				if (form.valorTarifaMinima.value == ""){
					alert("Informe Categoria.\nInforme Valor da Tarifa M�nima.");
				} else{
					alert("Informe Categoria.");
				}
			}
		}else{
			if (form.consumoMinimo.value == ""){
				if (form.valorTarifaMinima.value == ""){
				  alert("Informe Consumo M�nimo.\nInforme Valor da Tarifa M�nima.");
				}else{
				  alert("Informe Consumo M�nimo.");
				}
			} else {
				if (form.valorTarifaMinima.value == ""){
				  alert("Informe Valor da Tarifa M�nima.");
				} else{
					if (validateLong(form) && testarCampoValorZero(form.consumoMinimo, 'Consumo M�nimo') && testarCampoValorZeroDecimal(form.valorTarifaMinima, 'Valor da Tarifa M�nima')){
						window.location.href='/gsan/exibirInserirSubCategoriaFaixaConsumoTarifaAction.do?limpaFaixa=1&parametro1='+document.forms[0].slcCategoria.value+'&parametro2='+document.forms[0].consumoMinimo.value+'&parametro3='+document.forms[0].valorTarifaMinima.value+'&parametro4='+document.forms[0].slcSubCategoria.value;	
					}
				}
			}
		}
	}
</script>
</head>

<logic:equal name="testeInserir" value="false" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="resizePageSemLink(640,400); setarFoco('${requestScope.slcCategoria}');">
</logic:equal>
<logic:equal name="testeInserir" value="true" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="resizePageSemLink(640,400); chamarReload('exibirInserirConsumoTarifaSubCategoriaAction.do');window.close();">
</logic:equal>
<body leftmargin="0" topmargin="0"
	onload="resizePageSemLink(640,400); setarFoco('${requestScope.slcCategoria}');">

<html:form action="/inserirSubCategoriaConsumoTarifaAction"
	name="InserirSubCategoriaConsumoTarifaActionForm"
	onsubmit="return validateInserirSubCategoriaConsumoTarifaActionForm(this);"
	type="gcom.gui.faturamento.consumotarifa.InserirSubCategoriaConsumoTarifaActionForm"
	method="post">

	<table width="600" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Informar Categoria</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Preencha os campos para inserir uma categoria na
					tarifa de consumo</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=faturamentoTarifaConsumo-InformarCategoria', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>											
				</tr>
			</table>	
			<table width="100%" border="0">	
				<tr>
					<td width="27%" height="24"><strong>Categoria:<font color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:select property="slcCategoria"
						value="${sessionScope.categoriaSelected}" style="width: 230px;" onchange="preencherSubCategoria();">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoCategoria"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
				<td width="27%" height="24"><strong>SubCategoria:<font color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:select property="slcSubCategoria"
						value="${sessionScope.subCategoriaSelected}" style="width: 230px;">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoSubCategoria" scope="session">
						<html:options collection="colecaoSubCategoria"
							labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></td>
				</tr>
				<tr>
					<td height="24"><strong>Consumo M&iacute;nimo:<font color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:text maxlength="6" property="consumoMinimo" value="${sessionScope.consumoMinimo}"
						size="6" /></td>
				</tr>
				<tr>
					<td height="24"><strong>Valor da Tarifa M&iacute;nima:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:text style="text-align:right;" maxlength="17"
						property="valorTarifaMinima" size="17" value="${sessionScope.valorMinimo}"
						onkeyup="formataValorMonetario(this, 14)" /></td>
				</tr>
				<tr>
					<td height="25" colspan="3"><strong>Faixas de Consumo:</strong></td>

					<td align="right" width="13%" height="25"><input type="button"
						name="adicionar2" class="bottonRightCol" value="Adicionar"
						onClick="validaForm();"></td>
				</tr>
				<tr>
					<td height="24" colspan="4">
					<table width="100%" bgcolor="#99CCFF">
						<tr>
							<td>
							<div align="center" class="style9"><strong>Remover</strong></div>
							</td>
							<td>
							<div align="center" class="style9"><strong>Limite Superior</strong></div>
							</td>
							<td>
							<div align="center" class="style9"><strong>Valor da Tarifa na
							Faixa</strong></div>
							</td>
						</tr>
						<%String cor = "#FFFFFF";%>
						<logic:present name="colecaoFaixa" scope="session">
							<logic:iterate indexId="posicao" name="colecaoFaixa" id="faixa">
								<%if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
								cor = "#FFFFFF";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td>
									<div align="center" class="style9"><img
										src="<bean:message key='caminho.imagens'/>Error.gif"
										width="14" height="14" style="cursor:hand;"
										onclick="redirecionarSubmit('excluirSubCategoriaFaixaConsumoTarifaAction.do?posicao=<bean:write name="faixa" property="ultimaAlteracao.time" />');"></div>
									</td>
									<td>
									<div align="center" class="style9"><bean:write name="faixa"
										property="numeroConsumoFaixaFim" /></div>
									</td>
									<td>
									<div align="center" class="style9"><input type="text"
										style="text-align:right;" maxlength="17" size="17"
										name="valorConsumoTarifa<bean:write name="faixa" property="ultimaAlteracao.time" />"
										value="<bean:write name="faixa" property="valorConsumoTarifa" formatKey="money.format"/>"
										onkeyup="formataValorMonetario(this, 14)"></div>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
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
					<td height="27" colspan="4">
					<div align="right"><input type="hidden" name="testeInserir"> <input
						name="Button" type="submit" class="bottonRightCol" value="Inserir"
						onClick="document.forms[0].testeInserir.value='true';validarForm();">
					<input name="Button2" type="button" class="bottonRightCol"
						value="Fechar" onClick="javascript:window.close();"></div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>

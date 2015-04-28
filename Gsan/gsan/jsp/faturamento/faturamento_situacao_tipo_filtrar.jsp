<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gsan.util.ConstantesSistema"%>

<html:html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarFaturamentoSituacaoTipoActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(formulario){	
			if(validateFiltrarFaturamentoSituacaoTipoActionForm(formulario)){    		
				desabilitarCampos();
	    		if(validateInteger(formulario)){	    				     
		  			submeterFormPadrao(formulario);
		  		}
		}
	}	

	function IntegerValidations () {
		this.aa = new Array("id", "O campo Código deve conter apenas números.", new Function ("varName", " return this[varName];"));
	}

    function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}	
	
	function limparForm() {
		var form = document.AtualizarFaturamentoSituacaoTipoActionForm;
		form.descricao.value = "";
	}


	//[UC0097] Manter Tipo de Situação de faturamento
	//[FS0006] - Verificar indicador de Paralisação de faturamento
	//Autor: Diogo Luiz
	//Data: 02/10/2013
	function verIndicadorParalisarFat(){
		var form = document.forms[0];

		if(form.indicadorParalisacaoFaturamento[0].checked){			
				form.indicadorParalisaFatAgua[0].checked = true;
				form.indicadorParalisaFatAgua[0].disabled = true;
				form.indicadorParalisaFatAgua[1].disabled = true;
				form.indicadorParalisaFatAgua[2].disabled = true;
				form.indicadorParalisaFatEsgoto[0].checked = true;				
				form.indicadorParalisaFatEsgoto[0].disabled = true;				
				form.indicadorParalisaFatEsgoto[1].disabled = true;
				form.indicadorParalisaFatEsgoto[2].disabled = true;	
				VerificarIndParalisacaoFatAgua();	
				VerificarIndParalisacaoFatEsgoto();	
				verificarValidoAgua();
				verificarValidoEsgoto();
		}else if(form.indicadorParalisacaoFaturamento[1].checked){
				form.indicadorParalisaFatAgua[1].checked = true;
				form.indicadorParalisaFatAgua[0].disabled = false;
				form.indicadorParalisaFatAgua[1].disabled = false;
				form.indicadorParalisaFatAgua[2].disabled = false;
				form.indicadorParalisaFatEsgoto[1].checked = true;			
				form.indicadorParalisaFatEsgoto[0].disabled = false;			
				form.indicadorParalisaFatEsgoto[1].disabled = false;
				form.indicadorParalisaFatEsgoto[2].disabled = false;
				VerificarIndParalisacaoFatAgua();	
				VerificarIndParalisacaoFatEsgoto();	
				verificarValidoAgua();
				verificarValidoEsgoto();
		}else{
				form.indicadorParalisaFatEsgoto[2].checked = true;
				form.indicadorParalisaFatEsgoto[0].disabled = false;
				form.indicadorParalisaFatEsgoto[1].disabled = false;
				form.indicadorParalisaFatEsgoto[2].disabled = false;
				form.indicadorParalisaFatAgua[2].checked = true;
				form.indicadorParalisaFatAgua[0].disabled = false;
				form.indicadorParalisaFatAgua[1].disabled = false;
				form.indicadorParalisaFatAgua[2].disabled = false;
				form.indicadorValidoAgua[2].checked = true;
				form.indicadorValidoAgua[0].disabled = false;
				form.indicadorValidoAgua[1].disabled = false;
				form.indicadorValidoAgua[2].disabled = false;
				form.indicadorValidoEsgoto[2].checked = true;
				form.indicadorValidoEsgoto[0].disabled = false;
				form.indicadorValidoEsgoto[1].disabled = false;
				form.indicadorValidoEsgoto[2].disabled = false;	
				form.indicadorInformarConsumo[2].checked = true;
				form.indicadorInformarConsumo[0].disabled = false;
				form.indicadorInformarConsumo[1].disabled = false;
				form.indicadorInformarConsumo[2].disabled = false;	
				form.indicadorInformarVolume[2].checked = true;
				form.indicadorInformarVolume[0].disabled = false;
				form.indicadorInformarVolume[1].disabled = false;
				form.indicadorInformarVolume[2].disabled = false;							
			}
	}

	//[UC0097] Manter Tipo de Situação de faturamento
	//[FS0007] - Verificar indicador de Paralisação de faturamento de Água
	//Autor: Diogo Luiz
	//Data: 02/10/2013
	function VerificarIndParalisacaoFatAgua(){
		var form = document.forms[0];

		if(form.indicadorParalisaFatAgua[0].checked){
				form.indicadorValidoAgua[0].checked = true;
				form.indicadorValidoAgua[0].disabled = true;
				form.indicadorValidoAgua[1].disabled = true;
				form.indicadorValidoAgua[2].disabled = true;
				form.indicadorInformarConsumo[1].checked = true;
				form.indicadorInformarConsumo[0].disabled = true;
				form.indicadorInformarConsumo[1].disabled = true;
				form.indicadorInformarConsumo[2].disabled = true;
				verificarValidoAgua();
			}else{
					form.indicadorValidoAgua[2].checked = true;
					form.indicadorValidoAgua[0].disabled = false;
					form.indicadorValidoAgua[1].disabled = false;
					form.indicadorValidoAgua[2].disabled = false;
					form.indicadorInformarConsumo[2].checked = true;
					form.indicadorInformarConsumo[0].disabled = false;
					form.indicadorInformarConsumo[1].disabled = false;
					form.indicadorInformarConsumo[2].disabled = false;
					verificarValidoAgua();
				}	
		}

	//[UC0097] Manter Tipo de Situação de faturamento
	//[FS0008] - Verificar indicador de Paralisação de faturamento de Esgoto
	//Autor: Diogo Luiz
	//Data: 02/10/2013
	function VerificarIndParalisacaoFatEsgoto(){
		var form = document.forms[0];

		if(form.indicadorParalisaFatEsgoto[0].checked){
				form.indicadorValidoEsgoto[0].checked = true;
				form.indicadorValidoEsgoto[0].disabled = true;
				form.indicadorValidoEsgoto[1].disabled = true;
				form.indicadorValidoEsgoto[2].disabled = true;
				form.indicadorInformarVolume[1].checked = true;
				form.indicadorInformarVolume[0].disabled = true;
				form.indicadorInformarVolume[1].disabled = true;
				form.indicadorInformarVolume[2].disabled = true;
				verificarValidoEsgoto();
			}else{
					form.indicadorValidoEsgoto[2].checked = true;
					form.indicadorValidoEsgoto[0].disabled = false;
					form.indicadorValidoEsgoto[1].disabled = false;
					form.indicadorValidoEsgoto[2].disabled = false;
					form.indicadorInformarVolume[2].checked = true;
					form.indicadorInformarVolume[0].disabled = false;
					form.indicadorInformarVolume[1].disabled = false;
					form.indicadorInformarVolume[2].disabled = false;
					verificarValidoEsgoto();
				}
		}

	function desabilitarCampos(){
		var form = document.forms[0];
			form.indicadorParalisaFatAgua[0].disabled = false;
			form.indicadorParalisaFatAgua[1].disabled = false;
			form.indicadorParalisaFatEsgoto[0].disabled = false;
			form.indicadorParalisaFatEsgoto[1].disabled = false;
			form.indicadorValidoEsgoto[0].disabled = false;
			form.indicadorValidoEsgoto[1].disabled = false;
			form.indicadorInformarVolume[0].disabled = false;
			form.indicadorInformarVolume[1].disabled = false;
			form.indicadorValidoAgua[0].disabled = false;
			form.indicadorValidoAgua[1].disabled = false;
			form.indicadorInformarConsumo[0].disabled = false;
			form.indicadorInformarConsumo[1].disabled = false;
		}

	//[UC0096] Inserir Tipo de Situação de Faturamento
	//[FS0009] - Verificar indicador de válido para água
	//Autor: Diogo Luiz
	//Data: 11/10/2013
	function verificarValidoAgua(){
		var form = document.forms[0];		

		if(form.indicadorValidoAgua[0].checked && form.indicadorParalisaFatAgua[1].checked){
			form.indicadorInformarConsumo[0].disabled = false;
			form.indicadorInformarConsumo[1].disabled = false;	
			form.indicadorInformarConsumo[2].disabled = false;				
		}else{
			form.indicadorInformarConsumo[1].checked = true;
			form.indicadorInformarConsumo[0].disabled = true;
			form.indicadorInformarConsumo[1].disabled = true;
			form.indicadorInformarConsumo[2].disabled = true;
			}	
		}

	//[UC0096] Inserir Tipo de Situação de Faturamento
	//[FS0010] - Verificar indicador de válido para Esgoto
	//Autor: Diogo Luiz
	//Data: 11/10/2013
	function verificarValidoEsgoto(){
		var form = document.forms[0];

		if(form.indicadorValidoEsgoto[0].checked && form.indicadorParalisaFatEsgoto[1].checked){
			form.indicadorInformarVolume[0].disabled = false;
			form.indicadorInformarVolume[1].disabled = false;
			form.indicadorInformarVolume[2].disabled = false;			
		}else{
				form.indicadorInformarVolume[1].checked = true;
				form.indicadorInformarVolume[0].disabled = true;
				form.indicadorInformarVolume[1].disabled = true;
				form.indicadorInformarVolume[2].disabled = true;
			}
		}
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:verIndicadorParalisarFat();">
<html:form action="/filtrarFaturamentoSituacaoTipoAction"
	name="FiltrarFaturamentoSituacaoTipoActionForm"
	type="gsan.gui.faturamento.FiltrarFaturamentoSituacaoTipoActionForm"
	method="post"
	onsubmit="return validateFiltrarFaturamentoSituacaoTipoActionForm(this);">


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
			<td width="615" valign="top" class="centercoltext">
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
					<td class="parabg">Filtrar Tipo de Situação de Faturamento</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para filtrar o(s) tipo(s) da situação de
					faturamento, informe o dado abaixo:</td>
					<td width="100" align="left" colspan="2"><html:checkbox
						property="indicadorAtualizar" value="1" /><strong>Atualizar</strong></td>
				</tr>

				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:text property="id" size="5" maxlength="5"  onkeypress="return isCampoNumerico(event);"/><font
						size="1">&nbsp;(somente números)</font> <br>
					<font color="red"><html:errors property="id" /></font></td>
				</tr>

				<tr>
					<td width="25%"><strong>Descri&ccedil;&atilde;o:</strong></td>
					<td colspan="2"><span class="style2"> 
						<html:text property="descricao" 
							size="50" 
							maxlength="51" /> </span>
					</td>
					<td width="15%">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<html:radio property="tipoPesquisa" 
							tabindex="5"
							value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
							Iniciando pelo texto 
							
						<html:radio property="tipoPesquisa"
							tabindex="6"
							value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
							Contendo o texto
					</td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td><strong>Paralisa Faturamento?</strong></td>
					<td><html:radio property="indicadorParalisacaoFaturamento"
						tabindex="5" value="1" 
						onclick="javascript:verIndicadorParalisarFat();"/><strong>Sim</strong> <html:radio
						property="indicadorParalisacaoFaturamento" tabindex="7" value="2" 
						onclick="javascript:verIndicadorParalisarFat();"/><strong>Não</strong>
					<html:radio property="indicadorParalisacaoFaturamento" tabindex="8"
						value=""  onclick="javascript:verIndicadorParalisarFat(); "/><strong>Todos</strong></td>
				</tr>
				
				
				<tr>
					<td><strong>Paralisa Faturamento de Água?:<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorParalisaFatAgua" tabindex="5" value="1"
					onclick="javascript: VerificarIndParalisacaoFatAgua();" /><strong>Sim</strong>
					<html:radio property="indicadorParalisaFatAgua" tabindex="7" value="2"
					onclick="javascript: VerificarIndParalisacaoFatAgua();" /><strong>Não</strong>
					<html:radio property="indicadorParalisaFatAgua" tabindex="8" value=""
					onclick="javascript: VerificarIndParalisacaoFatAgua();" /><strong>Todos</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Paralisa Faturamento de Esgoto?:<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorParalisaFatEsgoto" tabindex="5" value="1"
					onclick="javascript: VerificarIndParalisacaoFatEsgoto();" /><strong>Sim</strong>
					<html:radio property="indicadorParalisaFatEsgoto" tabindex="7" value="2" 
					onclick="javascript: VerificarIndParalisacaoFatEsgoto();"/><strong>Não</strong>
					<html:radio property="indicadorParalisaFatEsgoto" tabindex="8" value=""
					onclick="javascript: VerificarIndParalisacaoFatEsgoto();" /><strong>Todos</strong>
					</td>
					<td>&nbsp;</td>
				</tr>	
				
				

				<tr>
					<td><strong>Paralisa Leitura?</strong></td>
					<td><html:radio property="indicadorParalisacaoLeitura" tabindex="5"
						value="1" /><strong>Sim</strong> <html:radio
						property="indicadorParalisacaoLeitura" tabindex="7" value="2" /><strong>Não</strong>
					<html:radio property="indicadorParalisacaoLeitura" tabindex="8"
						value="" /><strong>Todos</strong></td>
				</tr>

				<tr>
					<td><strong>Valido para Água?</strong></td>
					<td><html:radio property="indicadorValidoAgua" tabindex="5"
						value="1" onclick="javascript: verificarValidoAgua();"/><strong>Sim</strong> <html:radio
						property="indicadorValidoAgua" tabindex="7" value="2" 
						onclick="javascript: verificarValidoAgua();"/><strong>Não</strong>
					<html:radio property="indicadorValidoAgua" tabindex="8" value="" /><strong>Todos</strong>
					</td>
				</tr>

				<tr>
					<td><strong>Valido para Esgoto?</strong></td>
					<td><html:radio property="indicadorValidoEsgoto" tabindex="5"
						value="1" onclick="javascript: verificarValidoEsgoto();" /><strong>Sim</strong> <html:radio
						property="indicadorValidoEsgoto" tabindex="7" value="2" onclick="javascript:verificarValidoEsgoto();" /><strong>Não</strong>
					<html:radio property="indicadorValidoEsgoto" tabindex="8" value="" /><strong>Todos</strong>
					</td>
				</tr>

				<tr>
					<td><strong>Informar Consumo Fixo?</strong></td>
					<td><html:radio property="indicadorInformarConsumo" tabindex="5"
						value="1" /><strong>Sim</strong> <html:radio
						property="indicadorInformarConsumo" tabindex="7" value="2" /><strong>Não</strong>
					<html:radio property="indicadorInformarConsumo" tabindex="8"
						value="" /><strong>Todos</strong></td>
				</tr>

				<tr>
					<td><strong>Informar Volume Fixo?</strong></td>
					<td><html:radio property="indicadorInformarVolume" tabindex="5"
						value="1" /><strong>Sim</strong> <html:radio
						property="indicadorInformarVolume" tabindex="7" value="2" /><strong>Não</strong>
					<html:radio property="indicadorInformarVolume" tabindex="8"
						value="" /><strong>Todos</strong></td>
				</tr>

				<tr>
					<td><strong>Anormalidade de Consumo Cobrar Sem Leitura:</strong></td>
					<td><html:select property="leituraAnormalidadeConsumoSemLeitura">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoAnormalidadeConsumoSemLeitura"
							labelProperty="descricaoConsumoACobrar" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td><strong>Anormalidade de Consumo Cobrar Com Leitura:</strong></td>
					<td><html:select property="leituraAnormalidadeConsumoComLeitura">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoAnormalidadeConsumoComLeitura"
							labelProperty="descricaoConsumoACobrar" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td><strong>Anormalidade de Leitura Faturar Sem Leitura:</strong></td>
					<td><html:select property="leituraAnormalidadeLeituraSemLeitura">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options
							collection="colecaoAnormalidadeLeituraFaturarSemLeitura"
							labelProperty="descricaoFaturamento" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td><strong>Anormalidade de Leitura Faturar Com Leitura:</strong></td>
					<td><html:select property="leituraAnormalidadeLeituraComLeitura">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options
							collection="colecaoAnormalidadeLeituraFaturarComLeitura"
							labelProperty="descricaoFaturamento" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td><html:radio property="indicadorUso" tabindex="5" value="1" /><strong>Ativo</strong>
					<html:radio property="indicadorUso" tabindex="7" value="2" /><strong>Inativo</strong>
					<html:radio property="indicadorUso" tabindex="8" value="" /><strong>Todos</strong>
					</td>
				</tr>

				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarFaturamentoSituacaoTipoAction.do?menu=sim'"
						tabindex="8">
						
						<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td width="65" align="right">
						<input name="Button2" 
							type="button"
							class="bottonRightCol" 
							value="Filtrar" 
							align="right"
							onClick="javascript:validarForm(document.forms[0]);" 
							tabindex="9" />
					</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>

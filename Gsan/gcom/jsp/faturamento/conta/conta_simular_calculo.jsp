<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.cadastro.imovel.Categoria" %>
<%@ page import="gcom.cadastro.imovel.Subcategoria" %>
<%@ page import="gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper" %>
<%@ page import="gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao" %>
<%@ page import="gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao" %>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>


<%-- Colocado por Raphael Rossiter em 14/03/2007
	 Objetivo: Manipula��o dos objetos que ser�o exibidos no formul�rio de acordo com a empresa --%>
								
<%-- ================================================================================= --%>
							
<logic:notEqual name="indicadorTarifaCategoria" value="<%= "" + SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA %>">

<script language="JavaScript">
<!--

function removerCategoria(idCategoria){
	var form = document.forms[0];
 	
 	form.action = "/gsan/removerSelecaoCategoriaAction.do?idCategoria=" + idCategoria + "&mapeamento=exibirSimularCalculoConta";
 	
 	if (confirm("Confirma remo��o?")){
		form.submit();
	}
}


function validarCamposDinamicos(form){
 	var camposValidos = true;
 	var quantidadeEconomia = 0;
 	for (i=0; i < form.elements.length; i++) {
    	if (form.elements[i].type == "text" && form.elements[i].id.length > 1){

			switch (form.elements[i].id){
				case "categoria":
					if (form.elements[i].value.length < 1){
						alert("Informe Quantidade de Economias");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (isNaN(form.elements[i].value) || form.elements[i].value.indexOf('.') != -1){
						alert("Quantidade de Economias deve somente conter n�meros positivos.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (!testarCampoValorZero(form.elements[i], "Quantidade de Categorias")){
						form.elements[i].focus();
						camposValidos = false;
					}
					if (form.elements[i].value > 0){
						
					}
					break;
				default:
					break;
			}	
    	}
    	
    	if (!camposValidos){
    		break;
    	}
    }
    
    return camposValidos;
}

//-->
</script>

</logic:notEqual>
							
<%-- ================================================================================= --%>
<%-- ================================================================================= --%>



<%-- Colocado por Raphael Rossiter em 14/03/2007
	 Objetivo: Manipula��o dos objetos que ser�o exibidos no formul�rio de acordo com a empresa --%>
								
<%-- ================================================================================= --%>
							
<logic:equal name="indicadorTarifaCategoria" value="<%= "" + SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA %>">

<script language="JavaScript">
<!--

function removerSubcategoria(idSubcategoria){
	var form = document.forms[0];
 	
 	form.action = "/gsan/removerSelecaoCategoriaAction.do?idSubcategoria=" + idSubcategoria + "&mapeamento=exibirSimularCalculoConta";
 	
 	if (confirm("Confirma remo��o?")){
		form.submit();
	}
}


function validarCamposDinamicos(form){
 	var camposValidos = true;
 	var quantidadeEconomia = 0;
 	for (i=0; i < form.elements.length; i++) {
    	if (form.elements[i].type == "text" && form.elements[i].id.length > 1){

			switch (form.elements[i].id){
				case "subcategoria":
					if (form.elements[i].value.length < 1){
						alert("Informe Quantidade de Economias");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (isNaN(form.elements[i].value) || form.elements[i].value.indexOf('.') != -1){
						alert("Quantidade de Economias deve somente conter n�meros positivos.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (!testarCampoValorZero(form.elements[i], "Quantidade de Subcategorias")){
						form.elements[i].focus();
						camposValidos = false;
					}
					if (form.elements[i].value > 0){
						
					}
					break;
				default:
					break;
			}	
    	}
    	
    	if (!camposValidos){
    		break;
    	}
    }
    
    return camposValidos;
}

//-->
</script>

</logic:equal>
							
<%-- ================================================================================= --%>
<%-- ================================================================================= --%>



<script language="JavaScript">
<!--

function habilitacaoCamposAgua(listBoxAgua, consumoFaturadoAgua){
 	
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = listBoxAgua.options[listBoxAgua.options.selectedIndex].id;
 	
 	if (indicadorFaturamento.length > 0 && indicadorFaturamento != ATIVO){
 		consumoFaturadoAgua.value = "";
 		consumoFaturadoAgua.disabled = true;
 	}
 	else{
 		consumoFaturadoAgua.disabled = false;
 	}
}


function habilitacaoCamposEsgoto(listBoxEsgoto, consumoFaturadoEsgoto, percentualEsgoto, consumoFaturadoPoco){
 
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;
 	
 	if (indicadorFaturamento.length > 0 && indicadorFaturamento != ATIVO){

		consumoFaturadoEsgoto.value = "";
 		consumoFaturadoEsgoto.disabled = true;
 		
 		percentualEsgoto.value = "";
 		percentualEsgoto.disabled = true;
 		
 		consumoFaturadoPoco.value = "";
 		consumoFaturadoPoco.readOnly = true;

 	}
 	else{
 		consumoFaturadoEsgoto.disabled = false;
 		percentualEsgoto.disabled = false;
 		
 		if(consumoFaturadoEsgoto.value != ''){
 			consumoFaturadoPoco.readOnly = true;
 		}else{
 			consumoFaturadoPoco.readOnly = false;
 		}
 		
 	}
}


function copiarDadosLigacaoEsgoto(listBoxEsgoto, consumoFaturadoEsgoto, percentualEsgoto){
 	
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;
 	
 	if (indicadorFaturamento.length > 0 && indicadorFaturamento == ATIVO){
 		
 		var form = document.forms[0];
 		
 		form.consumoFaturadoEsgoto.value = form.consumoFaturadoAgua.value;
		form.percentualEsgoto.value = document.getElementById("PERCENTUAL_ESGOTO").value;
 	}
}


function validarForm(form,listBoxAgua,listBoxEsgoto,consumoFaturadoAgua,consumoFaturadoEsgoto,percentualEsgoto,percetualColeta) {
	
	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamentoAgua = listBoxAgua.options[listBoxAgua.options.selectedIndex].id;
 	var indicadorFaturamentoEsgoto = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;
 	
	var valido = true;

	if (validateSimularCalculoContaActionForm(form)) {
	
		var ANO_LIMITE = document.getElementById("ANO_LIMITE").value;
	
		if (!verificaAnoMesMensagemPersonalizada(form.mesAnoReferencia,'M�s e Ano da Conta inv�lidos.')){
			valido = false;
		}
		else if ((form.mesAnoReferencia.value.substring(3, 7) * 1) < (ANO_LIMITE * 1)){
			valido = false;
			alert("Ano da Conta n�o deve ser menor que " + ANO_LIMITE+".");
			form.mesAnoConta.focus();
		}
		else if ((indicadorFaturamentoAgua.length > 0 && indicadorFaturamentoAgua == ATIVO)
			&& consumoFaturadoAgua.value.length < 1){
				valido = false;
				alert("Informe Consumo de �gua.");
				consumoFaturadoAgua.focus();
		}
		else if (consumoFaturadoAgua.value.length > 0 && consumoFaturadoAgua.value != 0 && 
				consumoFaturadoAgua.value <= listBoxAgua.options[listBoxAgua.selectedIndex].name){
				valido = false;
				alert("Consumo informado menor que consumo m�nimo para situa��o da liga��o de �gua, valor tem que ser maior que " + listBoxAgua.options[listBoxAgua.selectedIndex].name);
				consumoFaturadoAgua.focus();
		}
		else if ((indicadorFaturamentoEsgoto.length > 0 && indicadorFaturamentoEsgoto == ATIVO) 
			&& consumoFaturadoEsgoto.value.length < 1 && percetualColeta.value.length < 1){
				valido = false;
				alert("Informe Consumo de Esgoto ou Percentual de Coleta.");
				consumoFaturadoEsgoto.focus();
		}
		else if (consumoFaturadoEsgoto.value.length > 0 && consumoFaturadoEsgoto.value != 0 && 
				consumoFaturadoEsgoto.value <= listBoxEsgoto.options[listBoxEsgoto.selectedIndex].name){
				valido = false;
				alert("Volume informado menor que volume m�nimo para situa��o da liga��o de esgoto, valor tem que ser maior que " + listBoxesgoto.name);
				consumoFaturadoEsgoto.focus();
		}
		else if ((indicadorFaturamentoEsgoto.length > 0 && indicadorFaturamentoEsgoto == ATIVO) 
			&& percentualEsgoto.value.length < 1){
				valido = false;
				alert("Informe Percentual de Esgoto.");
				percentualEsgoto.focus();
		}
		else if (obterQuantidadeInteiro(percentualEsgoto.value) > 3 ||
				 obterQuantidadeFracao(percentualEsgoto.value) > 2){
				valido = false;
				alert("Percentual de Esgoto inv�lido.");
				percentualEsgoto.focus();
		}
		else if( form.existeColecao.value == 1){
				valido = false;
				alert("Informe Categorias e Economias.");
		}
		else if((indicadorFaturamentoEsgoto.length > 0 && indicadorFaturamentoEsgoto == ATIVO) 
			&& form.consumoFaturadoEsgoto.value.length < 1){
			
			valido = false;
			alert("Informe o Volume de Esgoto.");
			consumoFaturadoEsgoto.focus();
			
		}
		else if(!validarCamposDinamicos(form)){
			valido = false;
		}
		if (valido){
			//converteVirgula(percentualEsgoto);
    		submeterFormPadrao(form);
    	}
	}
}

function habilitaVolumeEsgoto(){
	var form = document.forms[0];

	if(form.consumoFaturadoPoco.value != '' && form.percentualColeta.value != ''){
		habilitacaoConsumoEsgoto(form.ligacaoEsgotoSituacaoID, form.consumoFaturadoEsgoto, form.percentualColeta, form.consumoFaturadoAgua, form.consumoFaturadoPoco);
	}

	if((form.consumoFaturadoPoco.value != '' && form.consumoFaturadoPoco.readOnly == false) || (form.percentualColeta.value != '' && form.percentualColeta.readOnly == false)){
		
		form.consumoFaturadoEsgoto.readOnly = true;
	}else{
		form.consumoFaturadoEsgoto.readOnly = false;
	}
}


function habilitacaoConsumoEsgoto(listBoxEsgoto, consumoFaturadoEsgoto, percentualColeta, consumoAgua, consumoPoco){
 	var form = document.forms[0];
 	
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;
 	var consumo = 0;

 	if (indicadorFaturamento.length > 0 && indicadorFaturamento == ATIVO){
 		
 		if(percentualColeta.value != ""){
	 	
 			if(consumoAgua.value != ""){
 			
				consumo = consumo + parseInt(consumoAgua.value);
 			}
 			
 			if(consumoPoco.value != ""){
 			
				consumo = consumo + parseInt(consumoPoco.value);
 			}
 			
 			if(consumoAgua.value != "" || consumoPoco.value != ""){
 				var percentual = percentualColeta.value;
				percentual = percentual.replace(/\./g, '');
				percentual = percentual.replace(/,/g, '.');
				consumoFaturadoEsgoto.value = Math.round(consumo * (percentual/100));
 			}
 			
 			if(((percentualColeta.value != '' && percentualColeta.readOnly == false) || (consumoPoco.value != '' && consumoPoco.readOnly == false)) && consumoFaturadoEsgoto.value !=''){
 				consumoFaturadoEsgoto.readOnly = true;
 			}
 		}else{
	 		if (consumoFaturadoEsgoto.readOnly == false && consumoFaturadoEsgoto.value != ""){
				percentualColeta.readOnly = true;
				
			}
			if((consumoPoco.value != '' && consumoPoco.readOnly == false) || (percentualColeta.value != '' && percentualColeta.readOnly == false)){
 				consumoFaturadoEsgoto.readOnly = true;
 			}else{
 				consumoFaturadoEsgoto.readOnly = false;
 			}
 		}

 	}else{
 		
 		if((consumoPoco.value != '' && consumoPoco.readOnly == false) || (percentualColeta.value != '' && percentualColeta.readOnly == false)){
 			consumoFaturadoEsgoto.readOnly = true;
 		}else{
 			consumoFaturadoEsgoto.readOnly = false;
 		}
 	}
 	
 	if(consumoPoco.value == '' && percentualColeta.value == ''){
 		consumoFaturadoEsgoto.readOnly = false;
 	}
}

function habilitacaoConsumoPoco(listBoxEsgoto, consumoFaturadoEsgoto, consumoPoco){
 	var form = document.forms[0];
 	
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;
 	
 	if (indicadorFaturamento.length > 0 && indicadorFaturamento == ATIVO){

 		if(form.consumoFaturadoEsgoto.value != ''){
 			form.consumoFaturadoPoco.readOnly = true;
 			form.percentualColeta.readOnly = true;
 		}else{
 			form.consumoFaturadoPoco.readOnly = false;
 			form.percentualColeta.readOnly = false;
 			
 			if(form.consumoFaturadoPoco.value != '' || form.percentualColeta.value != ''){
				form.consumoFaturadoEsgoto.readOnly = true;
			}
 		}

 	}else{
 		if(form.consumoFaturadoEsgoto.value != ''){
 			form.consumoFaturadoPoco.readOnly = true;
 			form.percentualColeta.readOnly = true;
 		}else{
 			form.consumoFaturadoPoco.readOnly = false;
 			form.percentualColeta.readOnly = false;
 			
 			if(form.consumoFaturadoPoco.value != '' || form.percentualColeta.value != ''){
				form.consumoFaturadoEsgoto.readOnly = true;
			}
 		}
 	}
}

function habilitacaoPercentualColeta(listBoxEsgoto, consumoFaturadoEsgoto, percentualColeta){
 	var form = document.forms[0];
 	
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;
 	
 	if (indicadorFaturamento.length > 0 && indicadorFaturamento == ATIVO){
 		
 		if(consumoFaturadoEsgoto.value != ''){
 			percentualColeta.readOnly = true;
 			form.consumoFaturadoPoco.readOnly = true;
 		}else{
 			if(form.consumoFaturadoPoco.value != '' || form.percentualColeta.value != ''){
	 			form.consumoFaturadoEsgoto.readOnly = true;
	 		}
 			form.percentualColeta.readOnly = false;
 			form.consumoFaturadoPoco.readOnly = false;
 		}

 	}else{
 		if(consumoFaturadoEsgoto.value != ''){
 			percentualColeta.readOnly = true;
 			form.consumoFaturadoPoco.readOnly = true;
 		}else{
 			percentualColeta.readOnly = false;
 			form.consumoFaturadoPoco.readOnly = false;
 			
 			if(form.consumoFaturadoPoco.value != '' || form.percentualColeta.value != ''){
				form.consumoFaturadoEsgoto.readOnly = true;
			}
 		}
 	}
}

function desabilita(){
	var form = document.forms[0];
	
	if(form.consumoFaturadoEsgoto.value == ''){
		
		form.consumoFaturadoPoco.readOnly = false;
		form.percentualColeta.readOnly = false;
	}
	
}


function habilitacaoConsumoEsgotoEPercentualColeta(){
 	var form = document.forms[0];
 	
 	var listBoxEsgoto = document.forms[0].ligacaoEsgotoSituacaoID;
	var consumoFaturadoEsgoto = document.forms[0].consumoFaturadoEsgoto;
	var percentualColeta = document.forms[0].percentualColeta;
	var consumoAgua = document.forms[0].consumoFaturadoAgua;
	var consumoPoco = document.forms[0].consumoFaturadoPoco;
	
	var consumo = 0;
 
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;

 	if (indicadorFaturamento.length > 0 ){
 	 	if(indicadorFaturamento == ATIVO){
	 	
	 	 	if(percentualColeta.value != "" ){
		 	
	 			if(consumoAgua.value != ""){

					consumo = consumo + parseInt(consumoAgua.value);
	 			}
	 			
	 			if(consumoPoco.value != ""){
					
					consumo = consumo + parseInt(consumoPoco.value);
	 			}
	 			
	 			if(consumoAgua.value != "" || consumoPoco.value != ""){
	 				
	 				var percentual = percentualColeta.value;
					percentual = percentual.replace(/\./g, '');
					percentual = percentual.replace(/,/g, '.');
	 				
	 				consumoFaturadoEsgoto.value = Math.round(consumo * (percentual/100));
	 			}
	 			
	 			if(form.consumoFaturadoEsgoto.value != ''){
	 				form.consumoFaturadoPoco.readOnly = true;
	 				form.percentualColeta.readOnly = true;
	 			}else{
	 				form.consumoFaturadoEsgoto.readOnly = true;
	 				form.percentualColeta.readOnly = true;
	 				
	 				if(form.consumoFaturadoPoco.value != '' || form.percentualColeta.value != ''){
	 					form.consumoFaturadoEsgoto.readOnly = true;
	 				}
	 			}
	 			
	 		}else if (consumoFaturadoEsgoto.value != ''){
				form.percentualColeta.readOnly = true;
				form.consumoFaturadoPoco.readOnly = true;
			}
	 	}else{
	 		
	 		form.percentualColeta.readOnly = true;
			form.consumoFaturadoPoco.readOnly = true;
	 	}
 	}
}

//-->
</script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="SimularCalculoContaActionForm"/>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');habilitacaoConsumoEsgotoEPercentualColeta();">
<html:form action="/simularCalculoContaAction" method="post">

<INPUT TYPE="hidden" ID="ATIVO" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO%>"/>

<INPUT TYPE="hidden" ID="ANO_LIMITE" value="${requestScope.anoLimite}"/>

<INPUT TYPE="hidden" ID="PERCENTUAL_ESGOTO" value="${requestScope.percentualEsgotoDefault}"/>


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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

		<td width="630" valign="top" class="centercoltext">
			<table height="100%">
				<tr><td></td></tr>
			</table>

			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
					<td class="parabg">Simular C�lculo da Conta</td>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
				</tr>
			</table>
			<p>&nbsp;</p>

<!-- ============================================================================================================================== -->

			<table width="100%" border="0" >
		    	<tr>
		        	<td width="100%">
		         		<table width="100%" border="0" >
							<tr> 
								<td colspan="3">
									Para simular o c&aacute;lculo da conta, informe  os dados abaixo:
								</td>
								<logic:present scope="application" name="urlHelp">
									<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoContaCalculoSimular', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
								</logic:present>
								<logic:notPresent scope="application" name="urlHelp">
									<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
								</logic:notPresent>
							</tr>
						</table>
						
						<table width="100%" border="0" >
							<tr> 
								<td class="style1" height="10" width="165">
									<strong>M&ecirc;s e Ano da Conta:<font color="#FF0000">*</font></strong> 
								</td>
								<td colspan="2" class="style1">
									<html:text property="mesAnoReferencia" size="7" maxlength="7" tabindex="1" 
										onkeyup="mascaraAnoMes(this, event)" onkeypress="javascript:return isCampoNumerico(event);" />mm/aaaa
								</td>
							</tr>
							<tr>
							 	<td colspan="4" height="10"></td>
		        		    </tr>
				            <tr>
					    		<td colspan="4">
									<table width="100%" align="center" bgcolor="#99CCFF">
		   				      			<tr>
		    								<td align="center"><strong>Dados de �gua</strong></td>
			    			  			</tr>
						      			<tr bgcolor="#cbe5fe">
							     			<td width="100%" align="center">
					                			<table width="100%" border="0">
													<tr> 
									      				<td height="10" width="148">
									      					<strong>Situa��o de �gua:<font color="#FF0000">*</font></strong>
									      				</td>
						           					  	<td>
															<html:select property="ligacaoAguaSituacaoID" style="width: 230px;" tabindex="2" 
																onchange="habilitacaoCamposAgua(this, document.forms[0].consumoFaturadoAgua);">
													       		<logic:present name="colecaoLigacaoAguaSituacao">
															   		<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
															    	
													      			<logic:iterate name="colecaoLigacaoAguaSituacao" id="ligacaoAguaSituacao" type="LigacaoAguaSituacao">
													      				
													      				<logic:equal name="SimularCalculoContaActionForm" property="ligacaoAguaSituacaoID" value="<%="" + ligacaoAguaSituacao.getId() %>">
													      					<option SELECTED value="<%="" + ligacaoAguaSituacao.getId() %>" id="<%="" + ligacaoAguaSituacao.getIndicadorFaturamentoSituacao() %>" name="<%="" + ligacaoAguaSituacao.getConsumoMinimoFaturamento() %>"><%="" + ligacaoAguaSituacao.getDescricao() %></option>
													      				</logic:equal>
													      				
													      				<logic:notEqual name="SimularCalculoContaActionForm" property="ligacaoAguaSituacaoID" value="<%="" + ligacaoAguaSituacao.getId() %>">
													      					<option value="<%="" + ligacaoAguaSituacao.getId() %>" id="<%="" + ligacaoAguaSituacao.getIndicadorFaturamentoSituacao() %>" name="<%="" + ligacaoAguaSituacao.getConsumoMinimoFaturamento() %>"><%="" + ligacaoAguaSituacao.getDescricao() %></option>
													      				</logic:notEqual>
													      			
													      			</logic:iterate>
													      			
													      		</logic:present>
		   												    </html:select>
														</td>
														<td colspan="2"></td>
													</tr>
													<tr> 
		   							      				<td height="10"><strong>Consumo de �gua:</strong></td>
														<td>
															<html:text property="consumoFaturadoAgua" size="10" maxlength="6" tabindex="3" style="text-align: right;" onkeypress="javascript:return isCampoNumerico(event);"/>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table> 
								</td>
							</tr>	
							<tr>
						      	<td colspan="4" height="10"></td>
						   	</tr>
						 	<tr>
							    <td colspan="4">
									<table width="100%" align="center" bgcolor="#99CCFF">
		   					    		<tr>
		    								<td align="center"><strong>Dados de Esgoto</strong></td>
			    			  			</tr>
						      			<tr bgcolor="#cbe5fe">
									    	<td width="100%" align="center">
					                			<table width="100%" border="0">
					                				<tr> 
									      				<td height="10" width="150">
									      					<strong>Situa��o de Esgoto:<font color="#FF0000">*</font></strong>
									      				</td>
					            					  	<td>
										     				<html:select property="ligacaoEsgotoSituacaoID" style="width: 230px;" tabindex="4" 
										     					onchange="habilitacaoCamposEsgoto(this, document.forms[0].consumoFaturadoEsgoto, document.forms[0].percentualEsgoto, document.forms[0].consumoFaturadoPoco);
																copiarDadosLigacaoEsgoto(this, document.forms[0].consumoFaturadoEsgoto, document.forms[0].percentualEsgoto); habilitacaoConsumoPoco(document.forms[0].ligacaoEsgotoSituacaoID, this, document.forms[0].consumoFaturadoPoco); ">
														       <logic:present name="colecaoLigacaoAguaSituacao">
															   		
														      		<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
															    	
													      			<logic:iterate name="colecaoLigacaoEsgotoSituacao" id="ligacaoEsgotoSituacao" type="LigacaoEsgotoSituacao">
													      				
													      				<logic:equal name="SimularCalculoContaActionForm" property="ligacaoEsgotoSituacaoID" value="<%="" + ligacaoEsgotoSituacao.getId() %>">
													      					<option SELECTED value="<%="" + ligacaoEsgotoSituacao.getId() %>" id="<%="" + ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao() %>" name="<%="" + ligacaoEsgotoSituacao.getVolumeMinimoFaturamento() %>"><%="" + ligacaoEsgotoSituacao.getDescricao() %></option>
													      				</logic:equal>
													      			
													      				<logic:notEqual name="SimularCalculoContaActionForm" property="ligacaoEsgotoSituacaoID" value="<%="" + ligacaoEsgotoSituacao.getId() %>">
													      					<option value="<%="" + ligacaoEsgotoSituacao.getId() %>" id="<%="" + ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao() %>" name="<%="" + ligacaoEsgotoSituacao.getVolumeMinimoFaturamento() %>"><%="" + ligacaoEsgotoSituacao.getDescricao() %></option>
													      				</logic:notEqual>
													      				
													      			</logic:iterate>
													      			
														      	</logic:present>
						   								    </html:select>
														</td>
														<td colspan="2"></td>
													</tr>
													<tr> 
														<td height="10"><strong>Volume de Esgoto:</strong></td>
														<td>
															<html:text property="consumoFaturadoEsgoto" size="10" maxlength="6" tabindex="5" style="text-align: right;"
															onkeyup = "desabilita();"
															onchange = "habilitacaoPercentualColeta(document.forms[0].ligacaoEsgotoSituacaoID, this, document.forms[0].percentualColeta);habilitacaoConsumoPoco(document.forms[0].ligacaoEsgotoSituacaoID, this, document.forms[0].consumoFaturadoPoco); "
															onkeypress = "return isCampoNumerico(event);"
															/>
														</td>
													</tr>
													<tr> 
														<td height="10"><strong>Percentual de Esgoto:</strong></td>
														<td>
															<input type="text" 
																name="percentualEsgoto" 
																value="<bean:write name="SimularCalculoContaActionForm" property="percentualEsgoto"/>" 
																size="10" 
																maxlength="6" 
																tabindex="6" 
																style="text-align: right;" 
																onKeyup="formataValorMonetario(this, 5);"
																/>%
														</td>
													</tr>
													
													<tr> 
		   							      				<td height="10"><strong>Consumo do Po�o:</strong></td>
														<td>
															<html:text property="consumoFaturadoPoco" 
																	   size="10" 
																	   maxlength="6" 
																	   tabindex="6" 
																	   style="text-align: right;" 
																	   onkeypress="javascript:return isCampoNumerico(event);"
																	   onkeyup="javascript:habilitaVolumeEsgoto();"/>
														</td>
													</tr>
													
													<tr> 
														<td height="10"><strong>Percentual de Coleta:</strong></td>
														<td>
															<input type="text" 
																name="percentualColeta" 
																value="<bean:write name="SimularCalculoContaActionForm" property="percentualColeta"/>" 
																size="10" 
																maxlength="6" 
																tabindex="6" 
																style="text-align: right;" 
																onKeyup="formataValorMonetario(this, 5);habilitacaoConsumoEsgoto(document.forms[0].ligacaoEsgotoSituacaoID, document.forms[0].consumoFaturadoEsgoto,this,document.forms[0].consumoFaturadoAgua, document.forms[0].consumoFaturadoPoco);"
																/>%
														</td>
													</tr>
											    </table>
										     </td>
										</tr>
									</table> 
								</td>
							</tr>
							<tr>
								<td colspan="4" height="10"></td>
							</tr>
							<tr> 
								<td class="style1"><strong>Tarifa:<font color="#FF0000">*</font></strong></td>
								<td colspan="2">
									<html:select property="consumoTarifaID" style="width: 330px;" tabindex="7">
										<logic:present name="colecaoConsumoTarifa">
											<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										    <html:options collection="colecaoConsumoTarifa" labelProperty="descricao" property="id"/>
										</logic:present>
									</html:select>
								</td>
							</tr>
							<tr> 
								<td class="style1"><strong>Grupo de Faturamento:</strong></td>
								<td colspan="2">
									<html:select property="faturamentoGrupoID" style="width: 230px;" tabindex="8">
										<logic:present name="colecaoFaturamentoGrupo">
											<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<html:options collection="colecaoFaturamentoGrupo" labelProperty="descricao" property="id"/>
										</logic:present>
									</html:select>
								</td>
							</tr>
							<tr> 
								<td height="10"><strong>�rea do Im�vel:</strong></td>
								<td><html:text property="area" size="10" tabindex="9" style="text-align: right;" 
											   onkeyup="formataValorMonetario(this, 7);"/>
								</td>
							</tr>
							<tr> 
			      				<td height="10"><strong>N�mero de Moradores:</strong></td>
								<td>
									<html:text property="numeroMoradores" size="8" tabindex="10"
											   style="text-align: right;" 
											   onkeypress="formataValorMonetario(this, 5)"/>
								</td>
							</tr>
							<tr> 
			      				<td height="10"><strong>Pontos de Utiliza��o:</strong></td>
								<td>
									<html:text property="pontosUtilizacao" size="8" tabindex="11"
											   style="text-align: right;" 
											   onkeypress="formataValorMonetario(this, 5)"/>
								</td>
							</tr>
							<tr> 
								<td height="23" colspan="3" class="style1"> </td>
							</tr>
							
							
							<% int cont = 0; %>
							
							
							<%-- Colocado por Raphael Rossiter em 14/03/2007
								Objetivo: Manipula��o dos objetos que ser�o exibidos no formul�rio de acordo com a empresa --%>
								
							<%-- ================================================================================= --%>
							
						<logic:notEqual name="indicadorTarifaCategoria" value="<%= "" + SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA %>">
							
							
							<tr> 
								<td class="style1"><strong>Categorias e Economias:</strong><strong><font color="#FF0000">*</font></strong></td>
								<td colspan="2" class="style1"><div align="right"> 
									<input type="button" class="bottonRightCol" value="Adicionar" tabindex="12" style="width: 80px" 
										onclick="javascript:abrirPopup('exibirAdicionarCategoriaContaAction.do', 295, 450);"></div>
								</td>
							</tr>
							<tr> 
								<td colspan="4">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr> 
											<td> 
												<table width="100%" bgcolor="#99CCFF">
													<tr bgcolor="#99CCFF"> 
														<td align="center" width="10%"><strong>Remover</strong></td>
														<td align="center" width="60%"><strong>Categoria</strong></td>
														<td align="center" width="30%"><strong>Quantidade de Economias</strong></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td> 
												<div style="width: 100%; height=150; overflow: auto;">
					   						    <%
					   						    cont = 0;
					   						    if(session.getAttribute("existeColecao") != null){
					   						    %>
													<input type="hidden" name="existeColecao" value="1">
												<%}
					   						    else{
												%>
													<input type="hidden" name="existeColecao" value="0">
												<%}%>
				                                <logic:present name="colecaoCategoria">
		        		                        <table width="100%" align="center" bgcolor="#99CCFF">
											    <logic:iterate name="colecaoCategoria" id="categoria" type="Categoria">
													<%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr bgcolor="#cbe5fe">
													<%} else {%>
													<tr bgcolor="#FFFFFF">
													<%}%>
													    <td align="center" width="10%" valign="middle">
														   <a href="javascript:removerCategoria(<%="" + categoria.getId().intValue()%>)">
															  <img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
														   </a>
														</td>
														<td width="60%">
															<bean:write name="categoria" property="descricao"/>
														</td>
														<td width="30%" align="center">
													      <input type="text" name="categoria<%="" + categoria.getId().intValue()%>" size="6" maxlength="4" id="categoria" value="<%="" + categoria.getQuantidadeEconomiasCategoria()%>" style="text-align: right;"/>
														</td>
													 </tr>
												</logic:iterate>
												</table>
												</logic:present>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="4" height="10"></td>
							</tr>
							
							
							</logic:notEqual>
							
							<%-- ================================================================================= --%>
							<%-- ================================================================================= --%>
							
							
							
							
							
							<%-- Colocado por Raphael Rossiter em 14/03/2007
								Objetivo: Manipula��o dos objetos que ser�o exibidos no formul�rio de acordo com a empresa --%>
								
							<%-- ================================================================================= --%>
							
							<logic:equal name="indicadorTarifaCategoria" value="<%= "" + SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA %>">
							
							<tr> 
								<td class="style1"><strong>Subcategorias e Economias:</strong><strong><font color="#FF0000">*</font></strong></td>
								<td colspan="2" class="style1"><div align="right"> 
									<input type="button" class="bottonRightCol" value="Adicionar" tabindex="13" style="width: 80px" 
										onclick="javascript:abrirPopup('exibirAdicionarCategoriaContaAction.do', 295, 450);"></div>
								</td>
							</tr>
							<tr> 
								<td colspan="4">
									<table width="100%" cellpadding="0" cellspacing="0">
										
										<tr> 
											<td> 
												
												<div style="width: 100%; height=150; overflow: auto;">
					   						    <%
					   						    cont = 0;
					   						    if(session.getAttribute("existeColecao") != null){
					   						    %>
													<input type="hidden" name="existeColecao" value="1">
												<%}
					   						    else{
												%>
													<input type="hidden" name="existeColecao" value="0">
												<%}%>
				                                
				                                <logic:present name="colecaoSubcategoria">
				                                
				                                <% Integer idCategoria = null; %>
		        		                        
		        		                        <table width="100%" align="center" bgcolor="#99CCFF">
											    
											    <logic:iterate name="colecaoSubcategoria" id="subcategoria" type="Subcategoria">
											    
											    	<% if (idCategoria == null || 
											    		   idCategoria.intValue() != subcategoria.getCategoria().getId().intValue()){ %>
											    	
											    		<tr bgcolor="#79bbfd"> 
															<td colspan="3"><strong><bean:write name="subcategoria" property="categoria.descricao"/></strong></td>
														</tr>
											    	
											    		<tr bgcolor="#99CCFF"> 
															<td align="center" width="10%"><strong>Remover</strong></td>
															<td align="center" width="60%"><strong>Subcategoria</strong></td>
															<td align="center" width="30%"><strong>Quantidade de Economias</strong></td>
														</tr>
														
														<% idCategoria = subcategoria.getCategoria().getId(); 
															cont = 0; %>
													
													<%} %>
												
													<%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr bgcolor="#cbe5fe">
													<%} else {%>
													<tr bgcolor="#FFFFFF">
													<%}%>
													    <td align="center" width="10%" valign="middle">
														   <a href="javascript:removerSubcategoria(<%="" + subcategoria.getId().intValue()%>)">
															  <img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
														   </a>
														</td>
														<td width="60%">
															<bean:write name="subcategoria" property="descricao"/>
														</td>
														<td width="30%" align="center">
													      <input type="text" name="subcategoria<%="" + subcategoria.getId().intValue()%>" size="6" maxlength="4" id="subcategoria" value="<%="" + subcategoria.getQuantidadeEconomias() %>" style="text-align: right;"/>
														</td>
													 </tr>
												
												</logic:iterate>
												
												</table>
												
												</logic:present>
												
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="4" height="10"></td>
							</tr>
							
							</logic:equal>
							
							<%-- ================================================================================= --%>
							<%-- ================================================================================= --%>
							
							
							
							
							
							<tr> 
								<td class="style1">&nbsp;</td>
								<td width="221" class="style1"><font color="#FF0000">*</font> 
								Campos Obrigat&oacute;rios</td>
								<td width="226" class="style1" align="right">
									<input type="button" name="adicionar22" class="bottonRightCol" value="Calcular" 
										onclick="validarForm(document.forms[0],document.forms[0].ligacaoAguaSituacaoID,document.forms[0].ligacaoEsgotoSituacaoID,document.forms[0].consumoFaturadoAgua,document.forms[0].consumoFaturadoEsgoto,document.forms[0].percentualEsgoto,document.forms[0].percentualColeta)">
		   				        </td>
				            </tr>
							<tr> 
								<td colspan="3" class="style1"></td>
							</tr>
							
							
							
							
							<%-- Colocado por Raphael Rossiter em 14/03/2007
								Objetivo: Manipula��o dos objetos que ser�o exibidos no formul�rio de acordo com a empresa --%>
								
							<%-- ================================================================================= --%>
							
							<logic:notEqual name="indicadorTarifaCategoria" value="<%= "" + SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA %>">
							
							<tr> 
								<td class="style1" colspan=3><strong>Valores Calculados de &Aacute;gua e Esgoto por Categoria</strong></td>
							</tr>
							<tr>
								<td colspan="3" class="style1">
									<table width="100%" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF"> 
											<td align="center" width="31%"><strong>Categoria</strong></td>
											<td align="center" width="24%"><strong>Valor de &Aacute;gua</strong></td>
											<td align="center" width="24%"><strong>Valor de Esgoto</strong></td>
											<td align="center" width="21%"><strong>Valor Total</strong></td>
										</tr>
									</table>
									
							</logic:notEqual>
							
							<%-- ================================================================================= --%>
							<%-- ================================================================================= --%>
							
							
							
							<%-- Colocado por Raphael Rossiter em 14/03/2007
								Objetivo: Manipula��o dos objetos que ser�o exibidos no formul�rio de acordo com a empresa --%>
								
							<%-- ================================================================================= --%>
							
							<logic:equal name="indicadorTarifaCategoria" value="<%= "" + SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA %>">
							
							<tr> 
								<td class="style1" colspan=3><strong>Valores Calculados de &Aacute;gua e Esgoto por Subcategoria</strong></td>
							</tr>
							<tr>
								<td colspan="3" class="style1">
									<table width="100%" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF"> 
											<td align="center" width="31%"><strong>Subcategoria</strong></td>
											<td align="center" width="24%"><strong>Valor de &Aacute;gua</strong></td>
											<td align="center" width="24%"><strong>Valor de Esgoto</strong></td>
											<td align="center" width="21%"><strong>Valor Total</strong></td>
										</tr>
									</table>
									
							</logic:equal>
							
							<%-- ================================================================================= --%>
							<%-- ================================================================================= --%>
									
									
									<logic:present name="colecaoCalcularValoresAguaEsgotoHelper">
									<% cont = 0;%>
									<table width="100%" bgcolor="#99CCFF">
										<logic:iterate name="colecaoCalcularValoresAguaEsgotoHelper" 
											id="calcularValoresAguaEsgotoHelper" type="CalcularValoresAguaEsgotoHelper">
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe" height="18">
										<%} else {%>
										<tr bgcolor="#FFFFFF" height="18">
										<%}%>
											<td width="31%" align="center">
												<bean:write name="calcularValoresAguaEsgotoHelper" property="descricaoCategoria"/>
											</td>
											<td width="24%" align="right">
												<bean:write name="calcularValoresAguaEsgotoHelper" property="valorFaturadoAguaCategoria" formatKey="money.format"/>
											</td>
											<td width="24%" align="right">
												<bean:write name="calcularValoresAguaEsgotoHelper" property="valorFaturadoEsgotoCategoria" formatKey="money.format"/>
											</td>
											<td width="21%" align="right">
												<bean:write name="calcularValoresAguaEsgotoHelper" property="valorTotalCategoria" formatKey="money.format"/>
											</td>
										</tr>
								   		</logic:iterate>
								   	</table>
								   	<br>
								   	<table width="100%" bgcolor="#99CCFF">
										<tr bgcolor="#cbe5fe" height="18"> 
											<td width="31%" align="right"><strong>Total:&nbsp;</strong></td>
											<td width="24%" bgcolor="#FFFFFF" align="right">
												<bean:write name="valorTotalAgua" formatKey="money.format"/>
											</td>
											<td width="24%" bgcolor="#FFFFFF" align="right">
												<bean:write name="valorTotalEsgoto" formatKey="money.format"/>
											</td>
											<td width="21%" bgcolor="#FFFFFF" align="right">
												<bean:write name="valorTotalCategorias" formatKey="money.format"/>
											</td>
										</tr>
									</table>
									</logic:present>
								</td>
							</tr>
							<tr>
								<td colspan="4" height="10"></td>
							</tr>
							
							
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" name="Button" class="bottonRightCol" value="Desfazer"
							onClick="javascript:window.location.href='/gsan/exibirSimularCalculoContaAction.do?menu=sim'">
						<input type="button" name="Button" class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"/>
					</td>
				</tr>	
				
<!-- ============================================================================================================================== -->

			</table> 
			<p>&nbsp;</p>
		</td>
	</tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

<script language="JavaScript">
<!--
habilitacaoCamposAgua(document.forms[0].ligacaoAguaSituacaoID, document.forms[0].consumoFaturadoAgua);
habilitacaoCamposEsgoto(document.forms[0].ligacaoEsgotoSituacaoID, document.forms[0].consumoFaturadoEsgoto, document.forms[0].percentualEsgoto, document.forms[0].consumoFaturadoPoco);
//-->
</script>
</html:form>
</body>
</html:html>
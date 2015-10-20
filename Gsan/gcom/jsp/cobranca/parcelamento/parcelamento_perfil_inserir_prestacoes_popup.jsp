<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.cobranca.parcelamento.ParcelamentoQuantidadePrestacaoHelper" %>
<%@ page import="gcom.cobranca.parcelamento.ParcelamentoFaixaValor" %>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="true"  dynamicJavascript="false" formName="InserirPrestacoesParcelamentoPerfilActionForm" />

<script language="JavaScript">
<!-- Begin

	function abrirPopupComSubmitLink(form,altura, largura,qtdeMaximaPrestacao){
		redirecionarSubmit('exibirConsultarPercentualFaixaValorPopupAction.do?primeiraVez=S&qtdeMaximaPrestacao='+ qtdeMaximaPrestacao );
	}

	function adicionarPercentualFaixaValor (form){
		abrirPopupComSubmitBotao(form,'','');
	}

	function abrirPopupComSubmitBotao(form,altura,largura){
		redirecionarSubmit('exibirAdicionarPercentualFaixaValorPopupAction.do?primeiraVez=S');
	}

	function desabilitaPercentualTarifaMax () {
		var form = document.forms[0];
		if (form.percentualMinimoEntrada.value == ''){
			form.percentualTarifaMinimaImovel.disabled = false;
			//desabilita bt�o Adicionar Percentual Por Faixa de Valor
			form.adicionarPercentual.disabled = false;
			
			form.indicadorMediaValorContas[1].checked = true;
			form.indicadorValorUltimaContaEmAtraso[1].checked = true;
		}else{
			form.percentualTarifaMinimaImovel.disabled = true;
			//habilita bt�o Adicionar Percentual Por Faixa de Valor
			form.adicionarPercentual.disabled = true;
			form.indicadorMediaValorContas[1].checked = true;
			form.indicadorValorUltimaContaEmAtraso[1].checked = true;
		}
	}
	
	function desabilitaPercentualValoDebito () {
		var form = document.forms[0];
		if (form.percentualTarifaMinimaImovel.value == ''){
			form.percentualMinimoEntrada.disabled = false;
			//desabilita bt�o Adicionar Percentual Por Faixa de Valor
			form.adicionarPercentual.disabled = false;
			
			form.indicadorMediaValorContas[1].checked = true;
			form.indicadorValorUltimaContaEmAtraso[1].checked = true;
		}else{
			form.percentualMinimoEntrada.disabled = true;
			//habilita bt�o Adicionar Percentual Por Faixa de Valor
			form.adicionarPercentual.disabled = true;
			
			form.indicadorMediaValorContas[1].checked = true;
			form.indicadorValorUltimaContaEmAtraso[1].checked = true;
		}
	}
	
	function desabilitaPercentualTarifaMinimaImovelTabela (indicadorMediaValoresConta, 
	percentualMinimoEntrada,percentualTarifaMinimaImovel) {
		var form = document.forms[0];
		
		if (percentualMinimoEntrada.value == ''){
			percentualTarifaMinimaImovel.disabled = false;
			indicadorMediaValoresConta[1].checked = true;
			indicadorValorUltimaContaEmAtraso[1].checked = true;
		}else{
			percentualTarifaMinimaImovel.disabled = true;
			indicadorMediaValoresConta[1].checked = true;
			indicadorValorUltimaContaEmAtraso[1].checked = true;
		}
	}
	
	function desabilitaPercentualValoDebitoTabela (indicadorMediaValoresConta, 
	percentualTarifaMinimaImovel,percentualMinimoEntrada) {
		var form = document.forms[0];
		if (percentualTarifaMinimaImovel.value == ''){
			percentualMinimoEntrada.disabled = false;
			indicadorMediaValoresConta[1].checked = true;
			indicadorValorUltimaContaEmAtraso[1].checked = true;
		}else{
			percentualMinimoEntrada.disabled = true;
			indicadorMediaValoresConta[1].checked = true;
			indicadorValorUltimaContaEmAtraso[1].checked = true;
		}
		
	}
	
	function desabilitaPercentuaisTabela(indicadorMediaValoresConta,
	percentualMinimoEntrada,percentualTarifaMinimaImovel,indicadorValorUltimaContaEmAtraso, percentualVlReparcelado){
		
		if (indicadorMediaValoresConta == "1"){
			percentualMinimoEntrada.disabled = true;
			percentualMinimoEntrada.value = "";
			
			percentualTarifaMinimaImovel.disabled = true;
			percentualTarifaMinimaImovel.value = "";

			percentualVlReparcelado.disabled = true;
			percentualVlReparcelado.value = "";
			
			indicadorValorUltimaContaEmAtraso[1].checked = true;
			indicadorValorUltimaContaEmAtraso[0].disabled = true;
			indicadorValorUltimaContaEmAtraso[1].disabled = true;
		}
		else{
			percentualMinimoEntrada.disabled = false;
			percentualTarifaMinimaImovel.disabled = false;
			percentualVlReparcelado.disabled = false;
			indicadorValorUltimaContaEmAtraso[0].disabled = false;
			indicadorValorUltimaContaEmAtraso[1].disabled = false;
		}
	}

	/*function habilitarSituacaoLigacaoAgua(){
		var form = document.forms[0];
		form.action = "exibirInserirPrestacoesParcelamentoPerfilAction.do";
	    submeterFormPadrao(form);
	}*/

	function desabilitaPercentuais(indicadorMediaValoresConta){
		
		var form = document.forms[0];
		
		if (indicadorMediaValoresConta == "1"){
			
			form.percentualMinimoEntrada.disabled = true;
			form.adicionarPercentual.disabled = true;
			form.percentualMinimoEntrada.value = "";
			
			form.percentualTarifaMinimaImovel.disabled = true;
			form.adicionarPercentual.disabled = true;
			form.percentualTarifaMinimaImovel.value = "";

			form.percentualValorReparcelado.disabled = true;
			form.adicionarPercentual.disabled = true;
			form.percentualValorReparcelado.value = "";
			
			form.indicadorValorUltimaContaEmAtraso[1].checked = true;
			form.indicadorValorUltimaContaEmAtraso[0].disabled = true;
			form.indicadorValorUltimaContaEmAtraso[1].disabled = true;
		}
		else{
			
			form.percentualMinimoEntrada.disabled = false;
			form.adicionarPercentual.disabled = false;
			
			form.percentualTarifaMinimaImovel.disabled = false;
			form.adicionarPercentual.disabled = false;

			form.percentualValorReparcelado.disabled = false;
			form.adicionarPercentual.disabled = false;

			form.indicadorValorUltimaContaEmAtraso[0].disabled = false;
			form.indicadorValorUltimaContaEmAtraso[1].disabled = false;
		}
	}

	function validarForm(form){
		if(validateInserirPrestacoesParcelamentoPerfilActionForm(form)){
			document.forms[0].target='';
	    	form.submit();
		}
	}
	
	function caracteresespeciais () { 
     this.aa = new Array("quantidadeMaximaPrestacao", "Quantidade M�xima de Presta��es possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("quantidadeMaxPrestacaoEspecial", "Quantidade M�xima de Presta��es p/ Parcelamento com Permiss�o Especial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	 this.ac = new Array("fatorQuantidadePrestacoes", "Fator C�lculo Qtd. Presta��es Parc. possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	}

    function DecimalZeroPositivoValidations() {
        this.an = new Array("percentualMinimoEntrada", "Percentual Valor D�bito deve somente conter n�meros decimais positivos ou zero.", new Function ("varName", " return this[varName];"));
        this.am = new Array("percentualTarifaMinimaImovel", "Percentual Tarifa M�nima deve somente conter n�meros decimais positivos ou zero.", new Function ("varName", " return this[varName];"));
        this.am = new Array("percentualValorReparcelado", "Percentual Valor Reparcelado deve somente conter n�meros decimais positivos ou zero.", new Function ("varName", " return this[varName];"));    
    }

    function IntegerValidations () {
     this.aa = new Array("quantidadeMaximaPrestacao", "Quantidade M�xima de Presta��es deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("quantidadeMaxPrestacaoEspecial", "Quantidade M�xima de Presta��es p/ Parcelamento com Permiss�o Especial deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("fatorQuantidadePrestacoes", "Fator C�lculo Qtd. Presta��es Parc. deve ser num�rico(a).", new Function ("varName", " return this[varName];"));
    }

 var bCancel = false; 
 function validateInserirPrestacoesParcelamentoPerfilActionForm(form) {                                                                   
			
      if (bCancel) {
      	return true; 
      } else {
       	return  validateCaracterEspecial(form) && validateLong(form) && validateDecimalZeroPositivo(form);
       	}
   	  
   } 


   function adicionarPrestacao (form){
   	var PERCENTUAL_ENTRADA_MINIMA = document.getElementById("PERCENTUAL_ENTRADA_MINIMA").value;
   	
   
    	if (validaRequiredAdicionarPrestacao() && validateInserirPrestacoesParcelamentoPerfilActionForm(form) ){
    	
    		if(isNaN(form.quantidadeMaximaPrestacao.value)){	
	 			alert('Quantidade M�xima de Presta��es possui caracteres especiais.'); 
	 			form.quantidadeMaximaPrestacao.focus();	
	 		}else if (isNaN(form.quantidadeMaxPrestacaoEspecial.value)){	
	 			alert('Quantidade M�xima de Presta��es p/ Parcelamento com Permiss�o Especial possui caracteres especiais.'); 
	 			form.quantidadeMaxPrestacaoEspecial.focus();	
			}else if(validaCampoZeroAdicionarPrestacao()){
				document.forms[0].target='';
			    form.action = "exibirInserirPrestacoesParcelamentoPerfilAction.do?adicionarPrestacao=S";
			    submeterFormPadrao(form);
 			}   	
		}
	}  
	
   function fechar (form){
   document.forms[0].target='';
	   form.action = "exibirInserirPrestacoesParcelamentoPerfilAction.do?fechar=S";
	   submeterFormPadrao(form);	   
   }
   
	function validaRequiredAdicionarPrestacao () {
		var form = document.forms[0];
		var msg = '';
		var PERCENTUAL_VALOR_REPARCELAMENTO_READ_ONLY = document.getElementById("PERCENTUAL_VALOR_REPARCELAMENTO_READ_ONLY").value;
		
		if( form.quantidadeMaximaPrestacao.value  == '' ) {
			msg = msg + 'Informe Quantidade M�xima de Presta��es.\n';
		}
		//if( form.fatorQuantidadePrestacoes.value  == '' ) {
		//	msg = msg + 'Informe Fator C�lculo Qtd. Presta��es Parc.\n';
		//}
		if( form.taxaJuros.value  == '' ) {
			msg = msg + 'Informe Taxa de Juros a.m..\n';
		}
		
		var indicadorValorUltimaContaEmAtraso = obterValorRadioMarcadoPorNome('indicadorValorUltimaContaEmAtraso');
   	    var indicadorMediaValorContas = obterValorRadioMarcadoPorNome('indicadorMediaValorContas');
		
		if (indicadorValorUltimaContaEmAtraso == "2" && indicadorMediaValorContas == "2" &&
		form.percentualMinimoEntrada.value == '' && form.percentualTarifaMinimaImovel.value == '' &&
		(<%=session.getAttribute("collectionParcelamentoFaixaValorVazia")%> == "1" ||
		<%=session.getAttribute("collectionParcelamentoFaixaValorVazia")%> == null)){
			msg = msg + 'Informe  Percentual Valor D�bito ou Percentual Tarifa M�nima ou Percentual Por Faixa de Valor.';
		}
		

		if( msg != '' ){
			alert(msg);
			return false;
		}else{
			return true;
		}
	}
	
	function validaCampoZeroAdicionarPrestacao () {
		var form = document.forms[0];
		var msg = '';
		if( !testarValorZero(form.quantidadeMaximaPrestacao)) {
			msg = msg + 'Quantidade M�xima de Presta��es deve somente conter n�meros positivos.';
		}
		if( !testarValorZero(form.quantidadeMaxPrestacaoEspecial)) {
			msg = msg + 'Quantidade M�xima de Presta��es p/ Parcelamento com Permiss�o Especial deve somente conter n�meros positivos.';
		}
		if( msg != '' ){
			alert(msg);
			return false;
		}else{
			return true;
		}
	}
	


	//Testa se o campo foi digitado somente com zeros
	function testarValorZero(valor) {
		var retorno = true;
		var conteudo = valor.value.replace(",","");
		var conteudo = conteudo.replace(".","");
		
		if (trim(valor.value).length > 0){
			if (isNaN(valor.value)) {
				for (x= 0; x < conteudo.length; x++){
					if (conteudo.substr(x, 1) != "0"){
						retorno = true;
						break;
					}
					else{
						retorno = false;	
					}
				}
			}
			else {
				var intValorCampo = valor.value * 1;
				if (intValorCampo == 0) {
	        		retorno =  false;
				}
			}
		}
		return retorno;
	}

	function desabilitaPercentuaisEIndicador(indicadorValorUltimaContaEmAtraso){
		var form = document.forms[0];

		if (indicadorValorUltimaContaEmAtraso == "1"){
			form.percentualMinimoEntrada.disabled = true;
			form.adicionarPercentual.disabled = true;
			form.percentualMinimoEntrada.value = "";
			
			form.percentualTarifaMinimaImovel.disabled = true;
			form.adicionarPercentual.disabled = true;
			form.percentualTarifaMinimaImovel.value = "";

			form.percentualValorReparcelado.disabled = true;
			form.adicionarPercentual.disabled = true;
			form.percentualValorReparcelado.value = "";
			
			form.indicadorMediaValorContas[1].checked = true;
			form.indicadorMediaValorContas[0].disabled = true;
			form.indicadorMediaValorContas[1].disabled = true;

			var idsRegistros = form.idsRegistros;
			for(var i = 0; i < idsRegistros.length; i++ ){
				var temp = idsRegistros[i];
				temp.disabled = false;
			}
		}
		else{
			form.percentualMinimoEntrada.disabled = false;
			form.adicionarPercentual.disabled = false;
			
			form.percentualTarifaMinimaImovel.disabled = false;
			form.adicionarPercentual.disabled = false;

			form.percentualValorReparcelado.disabled = false;
			form.adicionarPercentual.disabled = false;

			form.indicadorMediaValorContas[0].disabled = false;			
			form.indicadorMediaValorContas[1].disabled = false;

			var idsRegistros = form.idsRegistros;
			for(var i = 0; i < idsRegistros.length; i++ ){
				var temp = idsRegistros[i];
				temp.disabled = true;

			}
		}
	}

	function desabilitaPercentuaisTabelaEIndicadorMediaValoresConta(indicadorValorUltimaContaEmAtraso,
	percentualMinimoEntrada,percentualTarifaMinimaImovel,indicadorMediaValoresConta, percentualVlReparcelado){

		if (indicadorValorUltimaContaEmAtraso == "1"){
			percentualMinimoEntrada.disabled = true;
			percentualMinimoEntrada.value = "";
			
			percentualTarifaMinimaImovel.disabled = true;
			percentualTarifaMinimaImovel.value = "";

			percentualVlReparcelado.disabled = true;
			percentualVlReparcelado.value = "";
			
			indicadorMediaValoresConta[1].checked = true;
			indicadorMediaValoresConta[0].disabled = true;
			indicadorMediaValoresConta[1].disabled = true;
		}
		else{
			percentualMinimoEntrada.disabled = false;
			percentualTarifaMinimaImovel.disabled = false;
			percentualVlReparcelado.disabled = false;
			indicadorMediaValoresConta[0].disabled = false;
			indicadorMediaValoresConta[1].disabled = false;
		}
	}

-->
</script>

</head>

<logic:present name="reloadPage">
	
	<logic:equal name="reloadPage" value="INSERIRPERFIL">
	
		<body leftmargin="5" topmargin="5" onload="window.opener.location.href='/gsan/exibirInserirPerfilParcelamentoAction.do?reload=S';window.close();">
	</logic:equal>
	
	<logic:equal name="reloadPage" value="ATUALIZARPERFIL">
		<body leftmargin="5" topmargin="5" onload="window.opener.location.href='/gsan/exibirAtualizarPerfilParcelamentoAction.do?reload=S';window.close();">
	</logic:equal>
	
	<logic:equal name="reloadPage" value="FECHARINSERIR">
		<body leftmargin="5" topmargin="5" onload="window.opener.location.href='/gsan/exibirInserirPerfilParcelamentoAction.do?reload=S';window.close();">
	</logic:equal>
	
	<logic:equal name="reloadPage" value="FECHARATUALIZAR">
		<body leftmargin="5" topmargin="5" onload="window.opener.location.href='/gsan/exibirAtualizarPerfilParcelamentoAction.do?reload=S';window.close();">
	</logic:equal>
	
	
</logic:present>

<logic:notPresent name="reloadPage">
	<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');resizePageSemLink(820, 846)">
</logic:notPresent>



<html:form action="/inserirPrestacoesParcelamentoPerfilAction"
	name="InserirPrestacoesParcelamentoPerfilActionForm"
	type="gcom.gui.cobranca.parcelamento.InserirPrestacoesParcelamentoPerfilActionForm"
	method="post"
	onsubmit="return validateInserirPrestacoesParcelamentoPerfilActionForm(this);">
	
	<input type="hidden" id="PERCENTUAL_ENTRADA_MINIMA" value="${requestScope.percentualFinanciamentoEntradaMinima}"/>
	<input type="hidden" id="PERCENTUAL_VALOR_REPARCELAMENTO_READ_ONLY" value="${requestScope.percentualValorReparceladoReadOnly}"/>
	
	<table width="740" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="740" valign="top" class="centercoltext">
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
					<td class="parabg">Adicionar Informa��es do Parcelamento por Quantidade de Reparcelamentos </td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="6">
					Preencha os campos para inserir as informa��es do parcelamento por quantidade de reparcelamentos:
					</td>
				</tr>
				<tr>
					<td colspan="6"><p>&nbsp;</p></td>
				</tr>
				
				<tr>
					<td width="25%"><strong> Qtd. M�x. Presta��es:<font color="#FF0000">*</font></strong></td>
					<logic:equal name="readOnly" value="true">
						<td><html:text property="quantidadeMaximaPrestacao" size="2"
							maxlength="2" tabindex="1" readonly="true"/>
						</td>
					</logic:equal>
					<logic:notEqual name="readOnly" value="true">
						<td> <html:text property="quantidadeMaximaPrestacao" size="2"
							maxlength="2" tabindex="1" onkeypress="return isCampoNumerico(event);" />
						</td>
					</logic:notEqual>
					
					<td width="25%"><strong> Qtd. M�x. Presta��es p/ Parc. com Permiss�o Especial:</strong></td>
					<logic:equal name="readOnly" value="true">
						<td colspan="2"><html:text property="quantidadeMaxPrestacaoEspecial" size="2"
							maxlength="2" tabindex="2" readonly="true"/>
						</td>
					</logic:equal>
					<logic:notEqual name="readOnly" value="true">
						<td colspan="2"><html:text property="quantidadeMaxPrestacaoEspecial" size="2"
							maxlength="2" tabindex="2" onkeypress="return isCampoNumerico(event);" />
						</td>
					</logic:notEqual>
				</tr>
				
				<tr>
					<td width="25%"><strong>Fator C�lculo Qtd. Presta��es Parc.:</strong></td>
					<logic:equal name="readOnly" value="true">
						<td><html:text property="fatorQuantidadePrestacoes" size="4"
							maxlength="3" tabindex="3" readonly="true"/>
						</td>
					</logic:equal>
					<logic:notEqual name="readOnly" value="true">
						<td><html:text property="fatorQuantidadePrestacoes" size="4"
							maxlength="3" tabindex="3" onkeypress="return isCampoNumerico(event);" />
						</td>
					</logic:notEqual>
					
					<td width="25%"><strong>Entrada com Guia de Pagamento apenas para quem possui Permiss�o Especial:</strong></td>
					<logic:equal name="readOnly" value="true">
						<td colspan="2">
							<html:radio property="indicadorGuiaPagamentoPermissaoEspecial" value="1" disabled="true" />SIM
							<html:radio property="indicadorGuiaPagamentoPermissaoEspecial" value="2" disabled="true"/>N�O
						</td>
					</logic:equal>
					<logic:notEqual name="readOnly" value="true">
						<td colspan="2">
							<html:radio property="indicadorGuiaPagamentoPermissaoEspecial" value="1" disabled="false"/>SIM
							<html:radio property="indicadorGuiaPagamentoPermissaoEspecial" value="2" disabled="false"/>N�O
						</td>
					</logic:notEqual>
				</tr>
				
				<tr>
					<td width="25%"><strong> Taxa de Juros a.m.:<font color="#FF0000">*</font></strong></td>
					<logic:equal name="readOnly" value="true">
						<td colspan="5"><html:text property="taxaJuros" size="6"
							maxlength="6" tabindex="4" 
							onkeyup="formataValorMonetario(this, 6)" 
							style="text-align:right;" readonly="true"/>
						</td>
					</logic:equal>
					<logic:notEqual name="readOnly" value="true">
						<td colspan="5"><html:text property="taxaJuros" size="6"
							maxlength="6" tabindex="4" onkeyup="formataValorMonetario(this, 6)" 
							style="text-align:right;" onkeypress="return isCampoNumerico(event);" />
						</td>
					</logic:notEqual>
				</tr>
				
				<tr>
					<td height="5" colspan="6">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="6">
						
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td align="center"><strong>Entrada</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
							<tr>
								<td><strong>M�dia Vl. Contas Atualizados:</strong></td>
								
								<logic:notEmpty name="collectionParcelamentoFaixaValor">
									<logic:equal name="readOnly" value="true">
										<td colspan="2">
											<html:radio property="indicadorMediaValorContas" value="1" disabled="true"/>SIM
											<html:radio property="indicadorMediaValorContas" value="2" disabled="true"/>N�O
										</td>
									</logic:equal>
									<logic:notEqual name="readOnly" value="true">
										<td colspan="2">
											<html:radio property="indicadorMediaValorContas" value="1" disabled="true"/>SIM
											<html:radio property="indicadorMediaValorContas" value="2" disabled="true"/>N�O
										</td>
									</logic:notEqual>
								</logic:notEmpty>
								
								<logic:empty name="collectionParcelamentoFaixaValor">
									<logic:equal name="readOnly" value="true">
										<td colspan="2">
											<html:radio property="indicadorMediaValorContas" value="1" disabled="true"/>SIM
											<html:radio property="indicadorMediaValorContas" value="2" disabled="true"/>N�O
										</td>
									</logic:equal>
									<logic:notEqual name="readOnly" value="true">
										<td colspan="2">
											<html:radio property="indicadorMediaValorContas" value="1" onclick="desabilitaPercentuais(this.value);"/>SIM
											<html:radio property="indicadorMediaValorContas" value="2" onclick="desabilitaPercentuais(this.value);"/>N�O
										</td>
									</logic:notEqual>
								</logic:empty>
								
								<td width="25%"><strong>Vlr da entrada pode ser Inferior � soma das presta��es Parc Anteriores que foram cobradas e ainda n�o est�o quitadas?</strong></td>
								<logic:equal name="readOnly" value="true">
									<td colspan="2">
										<html:radio property="indicadorEntradaMenorPrestacaoParcelamento" value="1" disabled="true" />SIM
										<html:radio property="indicadorEntradaMenorPrestacaoParcelamento" value="2" disabled="true"/>N�O
									</td>
								</logic:equal>
								<logic:notEqual name="readOnly" value="true">
									<td colspan="2">
										<html:radio property="indicadorEntradaMenorPrestacaoParcelamento" value="1" disabled="false"/>SIM
										<html:radio property="indicadorEntradaMenorPrestacaoParcelamento" value="2" disabled="false"/>N�O
									</td>
								</logic:notEqual>
							</tr>
							
							<tr>
								<td><strong> Valor da �ltima Conta em Atraso:</strong></td>
								
								<logic:notEmpty name="collectionParcelamentoFaixaValor">
									<logic:equal name="readOnly" value="true">
										<td colspan="2">
											<html:radio property="indicadorValorUltimaContaEmAtraso" value="1" disabled="true"/>SIM
											<html:radio property="indicadorValorUltimaContaEmAtraso" value="2" disabled="true"/>N�O
										</td>
									</logic:equal>
									<logic:notEqual name="readOnly" value="true">
										<td colspan="2">
											<html:radio property="indicadorValorUltimaContaEmAtraso" value="1" disabled="true"/>SIM
											<html:radio property="indicadorValorUltimaContaEmAtraso" value="2" disabled="true"/>N�O
										</td>
									</logic:notEqual>
								</logic:notEmpty>
								
								<logic:empty name="collectionParcelamentoFaixaValor">
									<logic:equal name="readOnly" value="true">
										<td colspan="2">
											<html:radio property="indicadorValorUltimaContaEmAtraso" value="1" disabled="true"/>SIM
											<html:radio property="indicadorValorUltimaContaEmAtraso" value="2" disabled="true"/>N�O
										</td>
									</logic:equal>
									<logic:notEqual name="readOnly" value="true">
										<td colspan="2">
											<html:radio property="indicadorValorUltimaContaEmAtraso" value="1" onclick="desabilitaPercentuaisEIndicador(this.value);"/>SIM
											<html:radio property="indicadorValorUltimaContaEmAtraso" value="2" onclick="desabilitaPercentuaisEIndicador(this.value);"/>N�O
										</td>
									</logic:notEqual>
								</logic:empty>
							</tr>
							
							<tr>
								<td colspan="6">
									<table width="100%" border="0">
										<tr>
											<td width="195px;"><strong>Situa��o da Liga��o de �gua</strong></td>
											<logic:present name="colLigacaoAguaSituacao">
												<logic:iterate name="colLigacaoAguaSituacao" id="ligacaoAguaSituacao">
													<td>
														<div align="center">																																																																		
															<html:checkbox property="idsRegistros" disabled="true" value="${ligacaoAguaSituacao.id}" />														
														</div>
													</td>
													<td>
														<div align="center">${ligacaoAguaSituacao.descricaoAbreviado}</div>
													</td>
												</logic:iterate>
											</logic:present>
										</tr>
									</table>
								</td>
							</tr>
							
							<tr>
								<td><strong> Perc. Vl. D�bito:</strong></td>
								
								<logic:notEmpty name="collectionParcelamentoFaixaValor">
									<logic:equal name="readOnly" value="true">
										<td>
											<html:text property="percentualMinimoEntrada" size="6" 
											maxlength="6" tabindex="5" style="text-align:right;" readonly="true"/>
										</td>
									</logic:equal>
									<logic:notEqual name="readOnly" value="true">
										<td>
											<html:text property="percentualMinimoEntrada" size="6" 
											maxlength="6" tabindex="5" style="text-align:right;"
											disabled="true"
											readonly="true"/>
										</td>
									</logic:notEqual>
								</logic:notEmpty>
								
								<logic:empty name="collectionParcelamentoFaixaValor">
									<logic:equal name="readOnly" value="true">
										<td>
											<html:text property="percentualMinimoEntrada" size="6" 
											maxlength="6" tabindex="5" style="text-align:right;" readonly="true"/>
										</td>
									</logic:equal>
									<logic:notEqual name="readOnly" value="true">
										<td>
											<html:text property="percentualMinimoEntrada" size="6" 
											maxlength="6" tabindex="5" style="text-align:right;"
											onkeyup="formataValorMonetario(this, 6);desabilitaPercentualTarifaMax();"
											 onkeypress="return isCampoNumerico(event);" />
										</td>
									</logic:notEqual>
								</logic:empty>
								
			
								<td><strong> Perc. Tarifa M�nima:</strong></td>
								<logic:notEmpty name="collectionParcelamentoFaixaValor">
								
								
									<logic:equal name="readOnly" value="true">
										<td>
											<html:text property="percentualTarifaMinimaImovel" size="6"
											maxlength="6" tabindex="6"  style="text-align:right;" readonly="true"
											onkeyup="formataValorMonetario(this, 6);desabilitaPercentualValoDebito();"/>
										</td>		
									</logic:equal>
									<logic:notEqual name="readOnly" value="true">
										<td>
											<html:text property="percentualTarifaMinimaImovel" size="6"
											maxlength="6" tabindex="6"  style="text-align:right;"
											disabled="true"/>
										</td>		
									</logic:notEqual>
								</logic:notEmpty>
								
								<logic:empty name="collectionParcelamentoFaixaValor">
									<logic:equal name="readOnly" value="true">
										<td>
											<html:text property="percentualTarifaMinimaImovel" size="6"
											maxlength="6" tabindex="6"  style="text-align:right;" readonly="true"
											onkeyup="formataValorMonetario(this, 6);desabilitaPercentualValoDebito();"/>
										</td>		
									</logic:equal>
									<logic:notEqual name="readOnly" value="true">
										<td>
											<html:text property="percentualTarifaMinimaImovel" size="6"
											maxlength="6" tabindex="6"  style="text-align:right;"
											onkeyup="formataValorMonetario(this, 6);desabilitaPercentualValoDebito();"
											 onkeypress="return isCampoNumerico(event);" />
										</td>		
									</logic:notEqual>
								</logic:empty>
								
								
								<td><strong>Perc. Vl. Reparcelado:</strong></td>
								<logic:equal name="readOnly" value="true">
									<td>
										<html:text property="percentualValorReparcelado" size="6"
										maxlength="6" tabindex="7"  style="text-align:right;" readonly="true"
										onkeyup="formataValorMonetario(this, 6);"/>
									</td>		
								</logic:equal>
								<logic:notEqual name="readOnly" value="true">
									<logic:equal name="percentualValorReparceladoReadOnly" value="true">
										<td>
											<html:text property="percentualValorReparcelado" size="6"
											maxlength="6" tabindex="7"  style="text-align:right;"
											onkeyup="formataValorMonetario(this, 6);" disabled="true"/>
										</td>
									</logic:equal>			
									<logic:notEqual name="percentualValorReparceladoReadOnly" value="true">
										<td>
											<html:text property="percentualValorReparcelado" size="6"
											maxlength="6" tabindex="7"  style="text-align:right;"
											onkeyup="formataValorMonetario(this, 6);" onkeypress="return isCampoNumerico(event);" />
										</td>
									</logic:notEqual>	
									
								</logic:notEqual>
								
							</tr>
							</table>	
						
							<table width="100%" border="0">
							<tr>
								<td><strong> Percentual Por Faixa de Valor: </strong>
								</td>
								<logic:equal name="readOnly" value="true">
									<td align="right" colspan="5"><input name="adicionarPercentual" type="button"
										class="bottonRightCol" value="Adicionar" align="right"
										readonly="true" tabindex="8"/>
									</td>
								</logic:equal>
								<logic:notEqual name="readOnly" value="true">
									<td align="right" colspan="5"><input name="adicionarPercentual" type="button"
										class="bottonRightCol" value="Adicionar" align="right" tabindex="8"
										onclick="document.forms[0].target='';adicionarPercentualFaixaValor(document.forms[0]);" 
										/>
									</td>
								</logic:notEqual>
							</tr>
							
							<!-- in�cio da tabela de Percentual Por Faixa de Valor -->
							
							
							<tr >
								<td colspan= "6">
								<%-- <div style="width: 100%; height: 90; overflow: auto;">--%>
									<table width="100%" border="0" bgcolor="#90c7fc">
									
									<logic:empty name="collectionParcelamentoFaixaValor" scope="session">
										<tr bgcolor="#90c7fc" height="18">
											<td align="center" width="10%"><strong>Remover</strong></td>
											<td width="45%" align="center"><strong>Valor M�ximo</strong></td>
											<td width="45%" align="center"><strong>Percentual</strong></td>
										</tr>
									</logic:empty>
									
									<logic:notEmpty name="collectionParcelamentoFaixaValor" scope="session">
									
									<%if (((Collection) session.getAttribute("collectionParcelamentoFaixaValor")).size() 
												<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PERFIL_PARCELAMENTO) {%>
									
										<tr bgcolor="#90c7fc" height="18">
											<td align="center" width="10%"><strong>Remover</strong></td>
											<td width="45%" align="center"><strong>Valor M�ximo</strong></td>
											<td width="45%" align="center"><strong>Percentual</strong></td>
										</tr>
										<logic:present name="collectionParcelamentoFaixaValor">
											<%int cont = 1;%>
											<logic:iterate name="collectionParcelamentoFaixaValor" 
											id="parcelamentoFaixaValor"
											type="ParcelamentoFaixaValor">
													<%cont = cont + 1;
														if (cont % 2 == 0) {%>
													<tr bgcolor="#FFFFFF">
														<%} else {
			
														%>
													<tr bgcolor="#cbe5fe">
														<%}%>
														
														<td width="10%">
															<logic:equal name="readOnly" value="true">
																<div align="center"><font color="#333333"> <img width="14"
													             height="14" border="0"
													             src="<bean:message key="caminho.imagens"/>Error.gif" />
												            </font></div>
															</logic:equal>
															<logic:notEqual name="readOnly" value="true">
																<div align="center"><font color="#333333"> <img width="14"
													             height="14" border="0"
													             src="<bean:message key="caminho.imagens"/>Error.gif"
					 								             onclick="javascript:document.forms[0].target='';if(confirm('Confirma remo��o?')){redirecionarSubmit('removerPercentualFaixaValorAction.do?valorMaximo=<bean:write name="parcelamentoFaixaValor" property="valorFaixa"/>');}" />
													            </font></div>
															</logic:notEqual>
												       </td>	
												       								       
												       <td width="45%" align="center">
															<div><bean:write	name="parcelamentoFaixaValor" property="valorFaixa" formatKey="money.format" /> &nbsp;</div>
														</td>
				
														<td width="45%" align="center">
															<logic:equal name="readOnly" value="true">
																<input type="text" style="text-align: right;font-size: xx-small;" 
																size="6" maxlength="6" align="center"
																name="perc<bean:write name="parcelamentoFaixaValor" property="ultimaAlteracao.time"/>" 
																value="<%="" + Util.formatarMoedaReal(parcelamentoFaixaValor.getPercentualFaixa())%>" 
																onkeyup="formataValorMonetario(this, 6)" readonly="true"
																/>
															</logic:equal>
															<logic:notEqual name="readOnly" value="true">
																<input type="text" style="text-align: right;font-size: xx-small;" 
																size="6" maxlength="6" align="center"
																name="perc<bean:write name="parcelamentoFaixaValor" property="ultimaAlteracao.time"/>" 
																value="<%="" + Util.formatarMoedaReal(parcelamentoFaixaValor.getPercentualFaixa())%>" 
																onkeyup="formataValorMonetario(this, 6)" 
																/>
															</logic:notEqual>
														</td>
			
													</tr>
											</logic:iterate>
										</logic:present>
												
									<%} else {%>			
									
									
										<tr bgcolor="#90c7fc" height="18">
											<td align="center" width="10%"><strong>Remover</strong></td>
											<td width="45%" align="center"><strong>Valor M�ximo</strong></td>
											<td width="45%" align="center"><strong>Percentual</strong></td>
										</tr>
										
										<tr>
											<td height="100" colspan="6">
												<div style="width: 100%; height: 100%; overflow: auto;">
													<table width="100%">
													
														<logic:present name="collectionParcelamentoFaixaValor">
															<%int cont = 1;%>
															<logic:iterate name="collectionParcelamentoFaixaValor" 
															id="parcelamentoFaixaValor"
															type="ParcelamentoFaixaValor">
																	<%cont = cont + 1;
																		if (cont % 2 == 0) {%>
																	<tr bgcolor="#FFFFFF">
																		<%} else {
							
																		%>
																	<tr bgcolor="#cbe5fe">
																		<%}%>
																		
																		<td width="10%">
																			<logic:equal name="readOnly" value="true">
																				<div align="center"><font color="#333333"> <img width="14"
																	             height="14" border="0"
																	             src="<bean:message key="caminho.imagens"/>Error.gif" />
																            </font></div>
																			</logic:equal>
																			<logic:notEqual name="readOnly" value="true">
																				<div align="center"><font color="#333333"> <img width="14"
																	             height="14" border="0"
																	             src="<bean:message key="caminho.imagens"/>Error.gif"
									 								             onclick="javascript:document.forms[0].target='';if(confirm('Confirma remo��o?')){redirecionarSubmit('removerPercentualFaixaValorAction.do?valorMaximo=<bean:write name="parcelamentoFaixaValor" property="valorFaixa"/>');}" />
																	            </font></div>
																			</logic:notEqual>
																       </td>	
																       								       
																       <td width="45%" align="center">
																			<div>${parcelamentoFaixaValor.valorFaixa} &nbsp;</div>
																		</td>
								
																		<td width="45%" align="center">
																			<logic:equal name="readOnly" value="true">
																				<input type="text" style="text-align: right;font-size: xx-small;" 
																				size="6" maxlength="6" align="center"
																				name="perc<bean:write name="parcelamentoFaixaValor" property="ultimaAlteracao.time"/>" 
																				value="<%="" + Util.formatarMoedaReal(parcelamentoFaixaValor.getPercentualFaixa())%>" 
																				onkeyup="formataValorMonetario(this, 6)" readonly="true"
																				/>
																			</logic:equal>
																			<logic:notEqual name="readOnly" value="true">
																				<input type="text" style="text-align: right;font-size: xx-small;" 
																				size="6" maxlength="6" align="center"
																				name="perc<bean:write name="parcelamentoFaixaValor" property="ultimaAlteracao.time"/>" 
																				value="<%="" + Util.formatarMoedaReal(parcelamentoFaixaValor.getPercentualFaixa())%>" 
																				onkeyup="formataValorMonetario(this, 6)" 
																				/>
																			</logic:notEqual>
																		</td>
							
																	</tr>
															</logic:iterate>
														</logic:present>
													</table>
												</div>	
											</td>		
										</tr>		
									<%}%>
									</logic:notEmpty>
									
									</table>
								</td>
							</tr>
							</table>
							
							<!-- final da tabela de Percentual Por Faixa de Valor -->
							
							</td>
						</tr>
					</table>
					
					</td>
				</tr>
				</table>
				
				
				<table width="100%" border="0">
				<tr>
					<td height="5" colspan="8">&nbsp;</td>
				</tr>

				<tr>
					<td colspan="7"><strong> Informa��es por Quantidade M�xima de Presta��es </strong>
					</td>
					<logic:equal name="readOnly" value="true">
						<td align="right"><input name="Button" type="button"
							class="bottonRightCol" value="Adicionar" align="right"
							readonly="true" tabindex="9" />
						</td>
					</logic:equal>
					<logic:notEqual name="readOnly" value="true">
						<td align="right"><input name="Button" type="button"
							class="bottonRightCol" value="Adicionar" align="right"
							onclick="adicionarPrestacao(document.forms[0]);" 
							tabindex="9"/>
						</td>
					</logic:notEqual>
				</tr>
				
				<!-- in�cio da tabela de Informa��es por qtde de presta��es -->
				<tr >
					<td colspan= "9">
					<div style="width: 100%; height: 180; overflow: auto;">
						<table width="100%" border="0" bgcolor="#90c7fc">
							<tr bgcolor="#90c7fc" height="18">
								<td align="center" width="6%"><strong>Remover</strong></td>
								<td width="10%" align="center"><strong>Qtde. M�xima Presta��es</strong></td>
								<td width="12%" align="center"><strong>Qtde. M�x. Prest. p/ Parcel. Permiss�o Especial</strong></td>
								<td width="9%" align="center"><strong>Fator C�lculo Qtd. Presta��es Parc.</strong></td>
								<td width="9%" align="center"><strong>Taxa de Juros a.m.</strong></td>
								<td width="10%" align="center"><strong>Entrada Inf prest Parc Ant</strong></td>
								<td width="10%" align="center"><strong>Entrada com Guia de Pag. apenas Permiss�o Esp.</strong></td>
								<td width="10%" align="center"><strong>M�dia Vl. Contas Atualizados</strong></td>
								<td width="8%" align="center"><strong>Vl.�ltima Conta Atraso</strong></td>
								<td width="7%" align="center"><strong>% Valor D�bito</strong></td>
								<td width="7%" align="center"><strong>% Tarifa M�nima</strong></td>
								<td width="12%" align="center"><strong>% Valor Reparcelado</strong></td>
								<td width="10%" align="center"> <strong>% Faixa Valor </strong> </td>
							</tr>

							<%--Esquema de pagina��o--%>
							<logic:present name="collectionParcelamentoQuantidadePrestacaoHelper">
								<%int cont1 = 1;%>
								<logic:iterate name="collectionParcelamentoQuantidadePrestacaoHelper" 
								id="parcelamentoQuantidadePrestacaoHelper"
								type="ParcelamentoQuantidadePrestacaoHelper">
										<%cont1 = cont1 + 1;
											if (cont1 % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											
										<td width="6%">
												<logic:equal name="readOnly" value="true">
													<div align="center"><font color="#333333"> <img width="14"
										             height="14" border="0"
										             src="<bean:message key="caminho.imagens"/>Error.gif" />
									            </font></div>
												</logic:equal>
												<logic:notEqual name="readOnly" value="true">
													<div align="center"><font color="#333333"> <img width="14"
										             height="14" border="0"
										             src="<bean:message key="caminho.imagens"/>Error.gif"
		 								             onclick="javascript:if(confirm('Confirma remo��o?')){redirecionarSubmit('removerParcelamentoQuantidadePrestacaoAction.do?qtdeMaxPrestacao=<%="" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getQuantidadeMaximaPrestacao()%> ');}" />
										            </font></div>
												</logic:notEqual>
									       </td>	
									  							       
									       <td width="10%" align="center">
												<div>${parcelamentoQuantidadePrestacaoHelper.parcelamentoQuantidadePrestacao.quantidadeMaximaPrestacao} &nbsp;</div>
											</td>
											
																       
									       <td width="12%" align="center">
												<div>${parcelamentoQuantidadePrestacaoHelper.parcelamentoQuantidadePrestacao.quantidadeMaxPrestacaoEspecial} &nbsp;</div>
											</td>
											
											<td width="9%" align="center">
												<logic:equal name="readOnly" value="true">
													<input type="text" style="font-size: xx-small;" 
													size="4" maxlength="3" align="center"
													name=<%="fatQtdPrest" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
													value="" 
													readonly="true"/>
													<bean:write  name="parcelamentoQuantidadePrestacaoHelper" property="parcelamentoQuantidadePrestacao.fatorQuantidadePrestacoes" />
												</logic:equal>
												<logic:notEqual name="readOnly" value="true">
													<input type="text" style="font-size: xx-small;" 
													size="4" maxlength="3" align="center"
													name=<%="fatQtdPrest" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
													value="<bean:write  name="parcelamentoQuantidadePrestacaoHelper" property="parcelamentoQuantidadePrestacao.fatorQuantidadePrestacoes" />" 
													onkeypress="return isCampoNumerico(event);" />
								
												</logic:notEqual>
											</td>
											
											<td width="9%" align="center">
												<logic:equal name="readOnly" value="true">
													<input type="text" style="text-align: right;font-size: xx-small;" 
													size="6" maxlength="6" align="center"
													name=<%="txJuros" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
													value="<%="" + Util.formatarMoedaReal(parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getTaxaJuros())%>" 
													onkeyup="formataValorMonetario(this, 6)" readonly="true"
													/>
												</logic:equal>
												<logic:notEqual name="readOnly" value="true">
													<input type="text" style="text-align: right;font-size: xx-small;" 
													size="6" maxlength="6" align="center"
													name=<%="txJuros" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
													value="<%="" + Util.formatarMoedaReal(parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getTaxaJuros())%>" 
													onkeyup="formataValorMonetario(this, 6)" 
													 onkeypress="return isCampoNumerico(event);" />
												</logic:notEqual>
											</td>
											
											
											
											<td width="10%" align="center">
												<logic:equal name="readOnly" value="true">
													
													<%if (parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao()
														.getIndicadorEntradaMenorPrestacaoParcelamento() == ConstantesSistema.SIM.shortValue()){ %>
														
														<input type="radio" align="center" 
														name=<%="indEntMenPreParc" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														value="1" disabled="true" checked="true"/>SIM&nbsp;&nbsp;
														
														<input type="radio" align="center" 
														name=<%="indEntMenPreParc" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														value="2" disabled="true"/>N�O
														
													<%}else{ %>
														<input type="radio" align="center" 
														name=<%="indEntMenPreParc" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														value="1" disabled="true" />SIM&nbsp;&nbsp;
														
														<input type="radio" align="center" 
														name=<%="indEntMenPreParc" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														value="2" disabled="true" checked="true"/>N�O
													<%}%>
												</logic:equal>
												
												<logic:notEqual name="readOnly" value="true">
													
													<%if (parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao()
														.getIndicadorEntradaMenorPrestacaoParcelamento() == ConstantesSistema.SIM.shortValue()){ %>
														
														<input type="radio" align="center" 
															name=<%="indEntMenPreParc" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															onclick="" value="1" checked="true"
															onkeyup=""/>SIM&nbsp;&nbsp;
															
															<input type="radio" align="center" 
															name=<%="indEntMenPreParc" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															onclick="" value="2"
															onkeyup=""/>N�O
													<%}else{ %>
													
														<input type="radio" align="center" 
															name=<%="indEntMenPreParc" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															onclick="" value="1" 
															onkeyup=""/>SIM&nbsp;&nbsp;
															
															<input type="radio" align="center" 
															name=<%="indEntMenPreParc" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															onclick="" value="2" checked="true"
															onkeyup=""/>N�O
													<%}%>
												</logic:notEqual>
											</td>
											
											<td width="10%" align="center">
												<logic:equal name="readOnly" value="true">
													
													<%if (parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao()
														.getIndicadorGuiaPagamentoEntradaEspecial() == ConstantesSistema.SIM.shortValue()){ %>
														
														<input type="radio" align="center" 
														name=<%="indGuiaPagPerEsp" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														value="1" disabled="true" checked="true"/>SIM&nbsp;&nbsp;
														
														<input type="radio" align="center" 
														name=<%="indGuiaPagPerEsp" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														value="2" disabled="true"/>N�O
														
													<%}else{ %>
														<input type="radio" align="center" 
														name=<%="indGuiaPagPerEsp" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														value="1" disabled="true" />SIM&nbsp;&nbsp;
														
														<input type="radio" align="center" 
														name=<%="indGuiaPagPerEsp" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														value="2" disabled="true" checked="true"/>N�O
													<%}%>
												</logic:equal>
												
												<logic:notEqual name="readOnly" value="true">
													
													<%if (parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao()
														.getIndicadorGuiaPagamentoEntradaEspecial() == ConstantesSistema.SIM.shortValue()){ %>
														
														<input type="radio" align="center" 
															name=<%="indGuiaPagPerEsp" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															onclick="" value="1" checked="true"
															onkeyup=""/>SIM&nbsp;&nbsp;
															
															<input type="radio" align="center" 
															name=<%="indGuiaPagPerEsp" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															onclick="" value="2"
															onkeyup=""/>N�O
													<%}else{ %>
													
														<input type="radio" align="center" 
															name=<%="indGuiaPagPerEsp" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															onclick="" value="1" 
															onkeyup=""/>SIM&nbsp;&nbsp;
															
															<input type="radio" align="center" 
															name=<%="indGuiaPagPerEsp" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															onclick="" value="2" checked="true"
															onkeyup=""/>N�O
													<%}%>
												</logic:notEqual>
											</td>
											
											
											<td width="10%" align="center">
												<logic:equal name="readOnly" value="true">
												
													<%if (parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao()
														.getIndicadorMediaValorContas() == ConstantesSistema.SIM.shortValue()){ %>
													
													<input type="radio" align="center" 
													name=<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
													value="1" disabled="true" checked="true"/>SIM&nbsp;&nbsp;
													
													<input type="radio" align="center" 
													name=<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
													value="2" disabled="true"/>N�O
													
													<%}else{ %>
													
													<input type="radio" align="center" 
													name=<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
													value="1" disabled="true"/>SIM&nbsp;&nbsp;
													
													<input type="radio" align="center" 
													name=<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
													value="2" disabled="true" checked="true"/>N�O
													
													<%}%>
												</logic:equal>
												
												<logic:notEqual name="readOnly" value="true">
													
													<%if (parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao()
														.getIndicadorMediaValorContas() == ConstantesSistema.SIM.shortValue()){ %>
													
													<input type="radio" align="center" 
													name=<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
													onclick="desabilitaPercentuaisTabela(this.value, <%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, <%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="indValorUltConta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);" value="1" checked="true"
													onkeyup="desabilitaPercentuaisTabela(this.value, <%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, <%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="indValorUltConta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);"/>SIM&nbsp;&nbsp;
													
													<input type="radio" align="center" 
													name=<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
													onclick="desabilitaPercentuaisTabela(this.value, <%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, <%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="indValorUltConta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);" value="2"
													onkeyup="desabilitaPercentuaisTabela(this.value, <%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, <%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="indValorUltConta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);"/>N�O
													
													<%}else{ %>
													
													<input type="radio" align="center" 
													name=<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
													onclick="desabilitaPercentuaisTabela(this.value, <%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, <%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="indValorUltConta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);" value="1"
													onkeyup="desabilitaPercentuaisTabela(this.value, <%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, <%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="indValorUltConta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);"/>SIM&nbsp;&nbsp;
													
													<input type="radio" align="center"
													name=<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
													onclick="desabilitaPercentuaisTabela(this.value, <%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, <%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="indValorUltConta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);" value="2" checked="true"
													onkeyup="desabilitaPercentuaisTabela(this.value, <%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, <%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="indValorUltConta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);"/>N�O
													
													<%}%>
												</logic:notEqual>
											</td>
											
											<td width="10%" align="center">
												<div onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${parcelamentoQuantidadePrestacaoHelper.ligacoesAgua}' ); " >
													<logic:equal name="readOnly" value="true">
														
														<%if (parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao()
															.getIndicadorValorUltimaContaEmAtraso().shortValue() == ConstantesSistema.SIM.shortValue()){ %>
														
														<input type="radio" align="center"
														name=<%="indValorUltConta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														value="1" disabled="true" checked="true"/>SIM&nbsp;&nbsp;
														
														<input type="radio" align="center"
														name=<%="indValorUltConta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														value="2" disabled="true"/>N�O
														
														<%}else{ %>
														
														<input type="radio" align="center"
														name=<%="indValorUltConta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														value="1" disabled="true"/>SIM&nbsp;&nbsp;
														
														<input type="radio" align="center"
														name=<%="indValorUltConta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														value="2" disabled="true" checked="true"/>N�O
														
														<%}%>
														
														<!-- <div id='div' style="display: block;"></div>-->
													</logic:equal>
													
													<logic:notEqual name="readOnly" value="true">
														
														<%if (parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao()
															.getIndicadorValorUltimaContaEmAtraso().shortValue() == ConstantesSistema.SIM.shortValue()){ %>
														
														<input type="radio" align="center"
														name=<%="indValorUltConta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														onclick="desabilitaPercentuaisTabelaEIndicadorMediaValoresConta(this.value, <%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, <%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);" value="1" checked="true"
														onkeyup="desabilitaPercentuaisTabelaEIndicadorMediaValoresConta(this.value, <%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, <%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);"/>SIM&nbsp;&nbsp;
														
														<input type="radio" align="center"
														name=<%="indValorUltConta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														onclick="desabilitaPercentuaisTabelaEIndicadorMediaValoresConta(this.value, <%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, <%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);" value="2"
														onkeyup="desabilitaPercentuaisTabelaEIndicadorMediaValoresConta(this.value, <%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, <%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);"/>N�O
														
														<%}else{ %>
														
														<input type="radio" align="center"
														name=<%="indValorUltConta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														onclick="desabilitaPercentuaisTabelaEIndicadorMediaValoresConta(this.value, <%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, <%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);" value="1"
														onkeyup="desabilitaPercentuaisTabelaEIndicadorMediaValoresConta(this.value, <%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, <%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);"/>SIM&nbsp;&nbsp;
														
														
														<input type="radio" align="center"
														name=<%="indValorUltConta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														onclick="desabilitaPercentuaisTabelaEIndicadorMediaValoresConta(this.value, <%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, <%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);" value="2" checked="true"
														onkeyup="desabilitaPercentuaisTabelaEIndicadorMediaValoresConta(this.value, <%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, <%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>,<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);" />N�O
														
														<%}%>
														
													</logic:notEqual>
												</div>
												<%if (parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao()
														.getIndicadorValorUltimaContaEmAtraso().shortValue() == ConstantesSistema.SIM.shortValue()){ %>
													<div id="div" style="display: none; position: absolute; background: none repeat scroll 0% 0% white;border: 1px solid rgb(144, 199, 252); width: 80px;" align="center">
														${parcelamentoQuantidadePrestacaoHelper.ligacoesAgua} &nbsp;
													</div>
												<% } %>
											</td>
											
											<%if ((parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualMinimoEntrada() != null  
													&& parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualMinimoEntrada().intValue() == 0 )
													&&(parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualTarifaMinimaImovel() != null  
													&& parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualTarifaMinimaImovel().intValue() == 0)) {%>
												
												<% if((parcelamentoQuantidadePrestacaoHelper.getHabilitado().equals("1"))){%>
													<td width="7%" align="center">
														<logic:equal name="readOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center" disabled="true"
															name=<%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="" style="text-align:right;" readonly="true"/>
														</logic:equal>
														<logic:notEqual name="readOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center" disabled="true"
															name=<%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="" 
															onkeyup="formataValorMonetario(this, 6);
															desabilitaPercentualTarifaMinimaImovelTabela(<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, this,<%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);" 
															style="text-align:right;"
															 onkeypress="return isCampoNumerico(event);" />
														</logic:notEqual>
													</td>
												<%}else { %>
													<td width="7%" align="center">
														<logic:equal name="readOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="" style="text-align:right;" readonly="true"/>
														</logic:equal>
														<logic:notEqual name="readOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="" 
															onkeyup="formataValorMonetario(this, 6);
															desabilitaPercentualTarifaMinimaImovelTabela(<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, this,<%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);" 
															style="text-align:right;"
															 onkeypress="return isCampoNumerico(event);" />
														</logic:notEqual>
													</td>
												<%} %>
												
												<% if((parcelamentoQuantidadePrestacaoHelper.getHabilitado().equals("1"))){%>
													<td width="7%" align="center">
														<logic:equal name="readOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center" disabled="true"
															name=<%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="" style="text-align:right;" readonly="true"
															/>
														</logic:equal>
														<logic:notEqual name="readOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center" disabled="true"
															name=<%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="" 
															onkeyup="formataValorMonetario(this, 6);
															desabilitaPercentualValoDebitoTabela(<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, this,<%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>)" 
															style="text-align:right;"
															 onkeypress="return isCampoNumerico(event);" />		
														</logic:notEqual>
													</td>
												<%}else { %>
													<td width="7%" align="center">
														<logic:equal name="readOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center" 
															name=<%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="" style="text-align:right;" readonly="true"
															/>
														</logic:equal>
														<logic:notEqual name="readOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center" 
															name=<%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="" 
															onkeyup="formataValorMonetario(this, 6);
															desabilitaPercentualValoDebitoTabela(<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, this,<%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>)" 
															style="text-align:right;"
															 onkeypress="return isCampoNumerico(event);" />		
														</logic:notEqual>
													</td>
												<% } %>
												
												<td width="12%" align="center">
												
												<%if ((parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualValorReparcelado() != null  
													&& parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualValorReparcelado().intValue() == 0 )) {%>
													
													<logic:equal name="readOnly" value="true">
														<input type="text" style="text-align: right;font-size: xx-small;" 
														size="6" maxlength="6" align="center"
														name=<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														value="" style="text-align:right;" readonly="true"
														/>
													</logic:equal>
													
													<logic:notEqual name="readOnly" value="true">
														<logic:equal name="percentualValorReparceladoReadOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="" style="text-align:right;" disabled="disabled"
															/>
														</logic:equal>
														<logic:notEqual name="percentualValorReparceladoReadOnly" value="true">
															<% if((parcelamentoQuantidadePrestacaoHelper.getHabilitado().equals("1"))){%>
																<input type="text" style="text-align: right;font-size: xx-small;" 
																	size="6" maxlength="6" align="center"
																	name=<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
																	value="" disabled="true"
																	onkeyup="formataValorMonetario(this, 6);"
																	style="text-align:right;"
																	onkeypress="return isCampoNumerico(event);"/>
															<% }else{ %>
																<input type="text" style="text-align: right;font-size: xx-small;" 
																	size="6" maxlength="6" align="center"
																	name=<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
																	value="" 
																	onkeyup="formataValorMonetario(this, 6);"
																	style="text-align:right;"
																	onkeypress="return isCampoNumerico(event);"/>
															<% } %>
														</logic:notEqual>
													</logic:notEqual>
													
												<%}else{ %>
													<logic:equal name="readOnly" value="true">
														<input type="text" style="text-align: right;font-size: xx-small;" 
														size="6" maxlength="6" align="center"
														name=<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														value="<%="" + Util.formatarMoedaReal(parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualValorReparcelado())%>" 
														 style="text-align:right;" readonly="true" />
													</logic:equal>
													
													<logic:notEqual name="readOnly" value="true">
														<logic:equal name="percentualValorReparceladoReadOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="<%="" + Util.formatarMoedaReal(parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualValorReparcelado())%>" 
															 style="text-align:right;" disabled="disabled" />
														</logic:equal>
														<logic:notEqual name="percentualValorReparceladoReadOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="<%="" + Util.formatarMoedaReal(parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualValorReparcelado())%>"  
															onkeyup="formataValorMonetario(this, 6);" style="text-align:right;" onkeypress="return isCampoNumerico(event);" />	
														</logic:notEqual>
													</logic:notEqual>
												<%} %>
												
												</td>
												
												<td width="10%" align="center">
													<logic:equal name="readOnly" value="true">
														
														<div align="center">
															<logic:notEmpty name="parcelamentoQuantidadePrestacaoHelper" property="collectionParcelamentoFaixaValor" >
																<a
																href="javascript:abrirPopupComSubmitLink(document.forms[0],'','',
																<%="" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getQuantidadeMaximaPrestacao()%>);"
																>Consultar</a>
															</logic:notEmpty>
															&nbsp;
														</div> 
														/>
													</logic:equal>
													
													<logic:notEqual name="readOnly" value="true">
														<div align="center">
															<logic:notEmpty name="parcelamentoQuantidadePrestacaoHelper" property="collectionParcelamentoFaixaValor" >
																<a
																href="javascript:abrirPopupComSubmitLink(document.forms[0],'','',
																<%="" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getQuantidadeMaximaPrestacao()%>);"
																>Consultar</a>
															</logic:notEmpty>
															&nbsp;
														</div> 

													</logic:notEqual>
												</td>
												
											<%}else{%>
												<logic:present name="${parcelamentoQuantidadePrestacaoHelper.parcelamentoQuantidadePrestacao}" property="percentualTarifaMinimaImovel">
													<td width="7%" align="center">
														<logic:equal name="readOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="" 
															onkeyup="formataValorMonetario(this, 6);
															desabilitaPercentualTarifaMinimaImovelTabela(<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, this,<%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);" 
															style="text-align:right;"
															readonly="true"
															/>																
														</logic:equal>
														<logic:notEqual name="readOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="" 
															onkeyup="formataValorMonetario(this, 6);
															desabilitaPercentualTarifaMinimaImovelTabela(<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, this,<%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);" 
															style="text-align:right;"
															disabled="disabled"
															/>																
														</logic:notEqual>													
													</td>
												</logic:present>
												<logic:notPresent name="${parcelamentoQuantidadePrestacaoHelper.parcelamentoQuantidadePrestacao}" property="percentualTarifaMinimaImovel">
													<td width="7%" align="center">
														<logic:equal name="readOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="<%="" + Util.formatarMoedaReal(parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualMinimoEntrada())%>" 
															style="text-align:right;" readonly="true"
															/>
														</logic:equal>
														<logic:notEqual name="readOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="<%="" + Util.formatarMoedaReal(parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualMinimoEntrada())%>" 
															onkeyup="formataValorMonetario(this, 6);
															desabilitaPercentualTarifaMinimaImovelTabela(<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, this,<%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>);" 
															style="text-align:right;"
															/>
														</logic:notEqual>																										
													</td>
												</logic:notPresent>

												<logic:present name="${parcelamentoQuantidadePrestacaoHelper.parcelamentoQuantidadePrestacao}" property="percentualMinimoEntrada">
													<td width="7%" align="center">
														<logic:equal name="readOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="" 
															onkeyup="formataValorMonetario(this, 6);
															desabilitaPercentualValoDebitoTabela(<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, this,<%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>)" 
															style="text-align:right;"
															readonly="true"
															/>			
														</logic:equal>
														<logic:notEqual name="readOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="" 
															onkeyup="formataValorMonetario(this, 6);
															desabilitaPercentualValoDebitoTabela(<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, this,<%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>)" 
															style="text-align:right;"
															disabled="disabled"
															/>															
														</logic:notEqual>													

													</td>
												</logic:present>
												<logic:notPresent name="${parcelamentoQuantidadePrestacaoHelper.parcelamentoQuantidadePrestacao}" property="percentualMinimoEntrada">
													<td width="7%" align="center">
														<logic:equal name="readOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="<%="" + Util.formatarMoedaReal(parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualTarifaMinimaImovel())%>" 
															style="text-align:right;" readonly="true"
															/>							
														</logic:equal>
														<logic:notEqual name="readOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percTarMinImovel" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="<%="" + Util.formatarMoedaReal(parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualTarifaMinimaImovel())%>" 
															onkeyup="formataValorMonetario(this, 6);
															desabilitaPercentualValoDebitoTabela(<%="indMedVlCnta" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>, this,<%="percMinEntrada" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>)" 
															style="text-align:right;"
															/>						
														</logic:notEqual>																
													</td>
												</logic:notPresent>
												
												<td width="12%" align="center">
												<%if ((parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualValorReparcelado() != null  
													&& parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualValorReparcelado().intValue() == 0 )) {%>
													<logic:equal name="readOnly" value="true">
														<input type="text" style="text-align: right;font-size: xx-small;" 
														size="6" maxlength="6" align="center"
														name=<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														value="" style="text-align:right;" readonly="true"
														/>
													</logic:equal>
													
													<logic:notEqual name="readOnly" value="true">
														<logic:equal name="percentualValorReparceladoReadOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="" style="text-align:right;" disabled="disabled"
															/>
														</logic:equal>
														<logic:notEqual name="percentualValorReparceladoReadOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="" 
															onkeyup="formataValorMonetario(this, 6);"
															style="text-align:right;"
															/>	
														</logic:notEqual>
													</logic:notEqual>
												<%}else{ %>
													<logic:equal name="readOnly" value="true">
														<input type="text" style="text-align: right;font-size: xx-small;" 
														size="6" maxlength="6" align="center"
														name=<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
														value="<%="" + Util.formatarMoedaReal(parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualValorReparcelado())%>" 
														 style="text-align:right;" readonly="true"
														/>
													</logic:equal>
													
													<logic:notEqual name="readOnly" value="true">
														<logic:equal name="percentualValorReparceladoReadOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="<%="" + Util.formatarMoedaReal(parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualValorReparcelado())%>" 
															 style="text-align:right;" disabled="disabled"
															/>
														</logic:equal>
														<logic:notEqual name="percentualValorReparceladoReadOnly" value="true">
															<input type="text" style="text-align: right;font-size: xx-small;" 
															size="6" maxlength="6" align="center"
															name=<%="percVlReparcelado" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getUltimaAlteracao().getTime()%>
															value="<%="" + Util.formatarMoedaReal(parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getPercentualValorReparcelado())%>"  
															onkeyup="formataValorMonetario(this, 6);"
															style="text-align:right;"
															/>	
														</logic:notEqual>
													</logic:notEqual>
												<%} %>
												
												</td>
												
												<td width="10%" align="center">
													<logic:equal name="readOnly" value="true">
														<div align="center">
															<logic:notEmpty name="parcelamentoQuantidadePrestacaoHelper" property="collectionParcelamentoFaixaValor" >
																<a
																href="javascript:abrirPopupComSubmitLink(document.forms[0],'','',
																<%="" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getQuantidadeMaximaPrestacao()%>);"
																>Consultar</a>
															</logic:notEmpty>
															&nbsp;
														</div> 

													</logic:equal>
													
													<logic:notEqual name="readOnly" value="true">
														<div align="center">
															<logic:notEmpty name="parcelamentoQuantidadePrestacaoHelper" property="collectionParcelamentoFaixaValor" >
																<a
																href="javascript:abrirPopupComSubmitLink(document.forms[0],'','',
																<%="" + parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getQuantidadeMaximaPrestacao()%>);"
																>Consultar</a>
															</logic:notEmpty>
															&nbsp;
														</div>
													</logic:notEqual>
												</td>
												
												
												
											<%} %>
											
										</tr>
								</logic:iterate>
							</logic:present>
							
							</table>
						</div>
					</td>
				</tr>
              	<!-- final da tabela de Informa��es por qtde de presta��es -->

				
				<tr>
					<td colspan="7">
						<logic:equal name="readOnly" value="true">
							<input type="button" name="Button" class="bottonRightCol" value="Fechar" tabindex="11"
							onclick="window.close();" />
						</logic:equal>
						<logic:notEqual name="readOnly" value="true">
							<input type="button" name="Button" class="bottonRightCol" value="Fechar" tabindex="11"
							onclick="fechar(document.forms[0]);" />
						</logic:notEqual>
                    </td>
                    <td align="right">
						<logic:equal name="readOnly" value="true">
							<input type="button" name="Button" class="bottonRightCol" value="Inserir" tabindex="10"
						 readonly="true"/>		
						</logic:equal>
						<logic:notEqual name="readOnly" value="true">
							<input type="button" name="Button" class="bottonRightCol" value="Inserir" tabindex="10"
							onClick="validarForm(document.forms[0]);" />							
						</logic:notEqual>                    
                    </td>
				</tr>
				
				
			</table>
			<p>&nbsp;</p>

	</table>
	<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form>

</html:html>

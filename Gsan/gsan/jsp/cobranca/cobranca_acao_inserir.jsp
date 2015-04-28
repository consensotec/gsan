<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ include file="/jsp/util/telaespera.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<style type="text/css"> 
	.radioNao{margin-left:100px;}
	.radioSim{margin-left:30px;}
	.labelSubcategoraia{margin-left: 30px;}
	.avisoDadosTexto{font-weight:bold;}
	.subtitle{font-size: 10px; font-stretch: condensed; display:inline;}
	#dadosTexto{width: 375px;}
</style>


<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>	
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AcaoCobrancaActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>

<logic:present scope="request" name="exibirTextoPersonalizado">

	<script language="JavaScript">
	
	 var bCancel = false;
	
	    function validateAcaoCobrancaActionForm(form) {
	        if (bCancel)
	      return true;
	        else
	      return validateRequired(form) && validateCaracterEspecial(form) && validateLong(form) 
			 && validaTodosRadioButton() && validarNumeroDiasEntreAcoes(form); 
	   }
	    
	     function IntegerValidations () {
	     this.aa = new Array("numeroDiasValidade", "Número de Dias de Validade da Ação deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ab = new Array("numeroDiasEntreAcoes", "Número de Dias entre a Ação e sua Predecessora deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ac = new Array("ordemCronograma", "Ordem no Cronograma deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ad = new Array("numeroDiasVencimento", "Número de Dias de Vencimento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ae = new Array("idServicoTipo", "Tipo de Serviço da Ordem de Serviço a ser Gerada deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.af = new Array("idCobrancaCriterio", "Critério de Cobrança deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ag = new Array("numeroDiasRemuneracaoTerceiro", "Limite de Dias para Remuneração de Terceiros deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ah = new Array("numeroDiasMinimoCobranca", "Quantidade de Dias Mínimo de Cobrança deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ai = new Array("numeroDiasMaximoCobranca", "Quantidade de Dias Máximo de Cobrança deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     
	    }
	
	    function required () { 
			this.aa = new Array("descricaoAcao", "Informe Descrição da Ação de Cobrança.", new Function ("varName", " return this[varName];"));
			this.ab = new Array("numeroDiasValidade", "Informe Número de Dias de Validade da Ação.", new Function ("varName", " return this[varName];"));
			this.ac = new Array("idTipoDocumentoGerado", "Informe Tipo de Documento a ser Gerado.", new Function ("varName", " return this[varName];"));
			this.ad = new Array("textoPersonalizado", "Informe Texto personalizado.", new Function ("varName", " return this[varName];"));
			this.ae = new Array("idCobrancaCriterio", "Informe Critério de Cobrança.", new Function ("varName", " return this[varName];"));
			if (document.forms[0].icCompoeCronograma[0].checked) {
				this.af = new Array("ordemCronograma", "Informe Ordem no Cronograma.", new Function ("varName", " return this[varName];"));
			}
		}
		
		function caracteresespeciais () {
	     this.aa = new Array("descricaoAcao", "Descrição da Ação de Cobrança possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	     this.ab = new Array("idCobrancaCriterio", "Critério de Cobrança possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	     this.ac = new Array("idServicoTipo", "Tipo de Serviço da Ordem de Serviço a ser Gerada possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    }

	
	</script> 

</logic:present>

<logic:notPresent scope="request" name="exibirTextoPersonalizado">

	<script language="JavaScript">
	
	 var bCancel = false;
	
	    function validateAcaoCobrancaActionForm(form) {
	        if (bCancel)
	      return true;
	        else
	      return validateRequired(form) && validateCaracterEspecial(form) && validateLong(form) 
			 && validaTodosRadioButton() && validarNumeroDiasEntreAcoes(form); 
	   }
	    
	     function IntegerValidations () {
	     this.aa = new Array("numeroDiasValidade", "Número de Dias de Validade da Ação deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ab = new Array("numeroDiasEntreAcoes", "Número de Dias entre a Ação e sua Predecessora deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ac = new Array("ordemCronograma", "Ordem no Cronograma deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ad = new Array("numeroDiasVencimento", "Número de Dias de Vencimento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ae = new Array("idServicoTipo", "Tipo de Serviço da Ordem de Serviço a ser Gerada deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.af = new Array("idCobrancaCriterio", "Critério de Cobrança deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ag = new Array("numeroDiasRemuneracaoTerceiro", "Limite de Dias para Remuneração de Terceiros deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.af = new Array("numeroDiasMinimoCobranca", "Quantidade de Dias Mínimo de Cobrança deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ag = new Array("numeroDiasMaximoCobranca", "Quantidade de Dias Máximo de Cobrança deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	    }
	    
	    function required () {
			this.aa = new Array("descricaoAcao", "Informe Descrição da Ação de Cobrança.", new Function ("varName", " return this[varName];"));
			this.ab = new Array("numeroDiasValidade", "Informe Número de Dias de Validade da Ação.", new Function ("varName", " return this[varName];"));
			this.ac = new Array("idTipoDocumentoGerado", "Informe Tipo de Documento a ser Gerado.", new Function ("varName", " return this[varName];"));
			this.af = new Array("idCobrancaCriterio", "Informe Critério de Cobrança.", new Function ("varName", " return this[varName];"));
			if (document.forms[0].icCompoeCronograma[0].checked) {
				this.ad = new Array("ordemCronograma", "Informe Ordem no Cronograma.", new Function ("varName", " return this[varName];"));
			}
		}
		
		function caracteresespeciais () {
	     this.aa = new Array("descricaoAcao", "Descrição da Ação de Cobrança possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	     this.ab = new Array("idCobrancaCriterio", "Critério de Cobrança possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	     this.ac = new Array("idServicoTipo", "Tipo de Serviço da Ordem de Serviço a ser Gerada possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    }
	
	</script> 

</logic:notPresent>
<script language="JavaScript">

$(document).ready(function() {
	$('input[name=icCompoeCronograma]').bind('change', function() {
		if ($(this).val() == '1') {
			$('input[name=ordemCronograma]').removeAttr('disabled').focus();
		} else {
			$('input[name=ordemCronograma]').attr('disabled','true').val('');
		}
	});

	$('input[name=icCompoeCronograma]:checked').each(function(){
		$(this).trigger('change');
	});

	habilitarDesabilitarSMS();
	habilitarDesabilitarEmail();
	verificarindicadorEnviarSmsEmail();
	$('[name=textoSMS]').attr('maxlength', '300');
	
	$('#botaoSMS').bind("click",function(){
		var theForm = $("form[name=AcaoCobrancaActionForm]");
		var params = theForm.serialize();
		var actionURL = 'exibirInserirAcaoCobrancaAction.do?action=validarTextoSMS';
		$.ajax({
		    type:"POST",
			url:actionURL,
			data:params,
			success: sucessoSMS,
			error: erroSMS
		});
	});

	$('#botaoEmail').bind("click",function(){

		var textoAInserir = $('[name=dadosTexto]').val();
		if(textoAInserir != null && textoAInserir != -1){
			var theForm = $("form[name=AcaoCobrancaActionForm]");
			var params = theForm.serialize();
			var actionURL = 'exibirInserirAcaoCobrancaAction.do?action=validarTextoEmail';
			$.ajax({
			    type:"POST",
				url:actionURL,
				data:params,
				success: sucessoEmail,
				error: null
			});	
		}
		else{
			alert('Selecione antes o dado que será incluído no texto');
			
		}
	});
	
	$('[name=indicadorMensagemSMS]').bind("change",habilitarDesabilitarSMS);
	$('[name=indicadorMensagemEmail]').bind("change",habilitarDesabilitarEmail);
	

	$('[name=textoSMS]').keydown(function(e){
	    $(this).data('prevVal', $(this).val());   
	}).keyup(function(e){
	    if(e.keyCode === 8) {
	        var ele = $(this); 
	        var val = ele.data('prevVal');
	        var newVal = ele.val(); 
	        var caretPos = getCaret($('[name=textoSMS]').get(0));
	        var charDeleted = val.charAt(caretPos);
	        verificarEApagarTag(caretPos,$('[name=textoSMS]').get(0),charDeleted);
	    }
	}); 


$('[name=textoEmail]').keydown(function(e){
	    $(this).data('prevVal', $(this).val());   
	}).keyup(function(e){
	    if(e.keyCode === 8) {
	        var ele = $(this); 
	        var val = ele.data('prevVal'); 
	        var newVal = ele.val(); 
	        var caretPos = getCaret($('[name=textoEmail]').get(0));
	        var charDeleted = val.charAt(caretPos);
	        verificarEApagarTag(caretPos,$('[name=textoEmail]').get(0),charDeleted);
	    }
	}); 
});

function habilitarDesabilitarSMS(){
	if($('[name=indicadorMensagemSMS]:checked').val() == '1'){
		$('[name=textoSMS]').attr("disabled",false);
		$('#botaoSMS').attr("disabled",false);
	}
	else{
		$('[name=textoSMS]').attr("disabled",true);
		$('[name=textoSMS]').val("");
		$('#botaoSMS').attr("disabled",true);
	}
}

function habilitarDesabilitarEmail(){
	if($('[name=indicadorMensagemEmail]:checked').val() == '1'){
		$('[name=textoEmail]').attr("disabled",false);
		$('#botaoEmail').attr("disabled",false);
	}
	else{
		$('[name=textoEmail]').attr("disabled",true);
		$('[name=textoEmail]').val("");
		$('#botaoEmail').attr("disabled",true);
	}
}

function sucessoSMS(data){

	var caretPos =  getCaret($('[name=textoSMS]').get(0));
	var textAreaTxt = $('[name=textoSMS]').val();
	$('[name=textoSMS]').val(textAreaTxt.substring(0, caretPos)+ data + textAreaTxt.substring(caretPos));	
	setarCursor($('[name=textoSMS]').get(0),caretPos+ data.length);
}


function sucessoEmail(data){
		var caretPos =  getCaret($('[name=textoEmail]').get(0));
		var textAreaTxt = $('[name=textoEmail]').val();
		$('[name=textoEmail]').val(textAreaTxt.substring(0, caretPos)+ data + textAreaTxt.substring(caretPos));	
		setarCursor($('[name=textoEmail]').get(0),caretPos+ data.length);
}

function erroSMS(data){
	alert(data.responseText);
}


function verificarEApagarTag(posicao, elm, charDeleted){
	var texto = elm.value;
	var txtAntes = texto.substring(0,posicao);
	var txtDepois = texto.substring(posicao);
	var boolAntes = false;
	var posAntes = 0;
	var contador = 1;


	if(charDeleted == '{'){
		boolAntes = true;
		posAntes = posicao;
	}
	else{
		for(i = txtAntes.length -1; i >= 0 ;i--){
			var carac = txtAntes.charAt(i);
			if(carac == '{'){
				boolAntes = true;
				posAntes = posicao - contador;
				break;	
			}
			else if (carac == '}')
				break;
	
			contador++;			
		}
	}	

	var boolDepois = false;
	var posDepois = 0;
	
	if(charDeleted == '}'){
		boolDepois = true;
		posDepois = posicao - 1;	
	}
	else{
		for(i = 0; i < txtDepois.length ;i++){
			var carac = txtDepois.charAt(i);
			if(carac == '{'){
				break;	
			}
			else if (carac == '}'){
				boolDepois = true;
				posDepois = posicao + i;
				break;		
			}
		}
	}

	if(boolAntes && boolDepois){
		elm.value = texto.substring(0,posAntes) + texto.substring(posDepois + 1);
	}
}


function setarCursor(ctrl, pos){
	if(ctrl.setSelectionRange)
	{
		ctrl.focus();
		ctrl.setSelectionRange(pos,pos);
	}
	else if (ctrl.createTextRange) {
		var range = ctrl.createTextRange();
		range.collapse(true);
		range.moveEnd('character', pos);
		range.moveStart('character', pos);
		range.select();
	}
}

function validaRadioButton(nomeCampo,mensagem){
	var alerta = "";
	var int = 0;
	if(!nomeCampo[0].checked && !nomeCampo[1].checked){
		alerta =  "Informe " + mensagem +".";
	}
	return alerta;
}
function validaTodosRadioButton(){
	var form = document.forms[0];
	var mensagem = "";
	if(validaRadioButton(form.icCompoeCronograma,"Compõe o Cronograma") != ""){
			mensagem = mensagem + validaRadioButton(form.icCompoeCronograma,"Compõe o Cronograma")+"\n";
	}
	if(validaRadioButton(form.icAcaoObrigatoria,"Ação Obrigatória") != ""){
		mensagem = mensagem + validaRadioButton(form.icAcaoObrigatoria,"Ação Obrigatória")+"\n";
	}
	if(validaRadioButton(form.icRepetidaCiclo,"Pode ser Repetida no Ciclo") != ""){
		mensagem = mensagem + validaRadioButton(form.icRepetidaCiclo,"Pode ser Repetida no Ciclo")+"\n";
	}
	if(validaRadioButton(form.icSuspensaoAbastecimento,"Provoca Suspensão de Abastecimento") != ""){
		mensagem = mensagem + validaRadioButton(form.icSuspensaoAbastecimento,"Provoca Suspensão de Abastecimento")+"\n";
	}
	if(validaRadioButton(form.icDebitosACobrar,"Considera Débitos a Cobrar") != ""){
		mensagem = mensagem + validaRadioButton(form.icDebitosACobrar,"Considera Débitos a Cobrar")+"\n";
	}
	if(validaRadioButton(form.icAcrescimosImpontualidade,"Considera Acréscimos por Impontualidade") != ""){
		mensagem = mensagem + validaRadioButton(form.icAcrescimosImpontualidade,"Considera Acréscimos por Impontualidade")+"\n";
	}
	if(validaRadioButton(form.icGeraTaxa,"Gera Taxa") != ""){
		mensagem = mensagem + validaRadioButton(form.icGeraTaxa,"Gera Taxa")+"\n";
	}
	if(validaRadioButton(form.icEmitirBoletimCadastro,"Pode Emitir Boletins de Cadastro") != ""){
		mensagem = mensagem + validaRadioButton(form.icEmitirBoletimCadastro,"Pode Emitir Boletins de Cadastro")+"\n";
	}
	if(validaRadioButton(form.icImoveisSemDebitos,"Pode ser Executada para Imóveis sem Débito") != ""){
		mensagem = mensagem + validaRadioButton(form.icImoveisSemDebitos,"Pode ser Executada para Imóveis sem Débito")+"\n";
	}
	if(validaRadioButton(form.icMetasCronograma,"Usa Metas no Cronograma") != ""){
		mensagem = mensagem + validaRadioButton(form.icMetasCronograma,"Usa Metas no Cronograma")+"\n";
	}
	if(validaRadioButton(form.icOrdenamentoCronograma,"Usa Ordenamento: No Cronograma") != ""){
		mensagem = mensagem + validaRadioButton(form.icMetasCronograma,"Usa Ordenamento: No Cronograma")+"\n";
	}
	if(validaRadioButton(form.icOrdenamentoEventual,"Usa Ordenamento: Nas Eventuais") != ""){
		mensagem = mensagem + validaRadioButton(form.icOrdenamentoEventual,"Usa Ordenamento: Nas Evetuais")+"\n";
	}
	if(validaRadioButton(form.icDebitoInterfereAcao,"Situação do Débito Interfere na Situação da Ação") != ""){
		mensagem = mensagem + validaRadioButton(form.icDebitoInterfereAcao,"Situação do Débito Interfere na Situação da Ação")+"\n";
	}
	if(validaRadioButton(form.icOrdenarMaiorValor,"Seleção ordenando por valor decrescente") != ""){
		mensagem = mensagem + validaRadioButton(form.icOrdenarMaiorValor,"Seleção ordenando por valor decrescente")+"\n";
	}
	if(validaRadioButton(form.icValidarItem,"Validar por item cobrado") != ""){
		mensagem = mensagem + validaRadioButton(form.icValidarItem,"Validar por item cobrado")+"\n";
	}
	if(validaRadioButton(form.icEfetuarAcaoCpfCnpjValido,"Efetuar Ação para CPF/CNPJ Válido") != ""){
		mensagem = mensagem + validaRadioButton(form.icEfetuarAcaoCpfCnpjValido,"Efetuar Ação para CPF/CNPJ Válido")+"\n";
	}
    if(validaRadioButton(form.indicadorExibeEventual,"Exibir Cobrança Eventual") != ""){
        mensagem = mensagem + validaRadioButton(form.icEfetuarAcaoCpfCnpjValido,"Exibir Cobrança Eventual")+"\n";
    }
	 
	if(mensagem != ""){
		alert(mensagem);
		return false;
	}else{
		return true;
	}
}


function verificarindicadorEnviarSmsEmail(){
	
	
	if($('input[name=indicadorMensagemSMS][value=1]').is(':checked') || $('input[name=indicadorMensagemEmail][value=1]').is(':checked')){
		$('input[name=numeroMaximoTentativoEnvio]').attr('readonly',false);			
	}else{
		$('input[name=numeroMaximoTentativoEnvio]').attr('readonly',true);	
		$('input[name=numeroMaximoTentativoEnvio]').val("");
	}
}

 
function validarForm(form){
	if( $('input[name=indicadorMensagemSMS][value=1]').is(':checked')){

		if($('[name=textoSMS]').val().trim() == ''){
			alert('Informe Texto SMS');
			return false;
		}	
	}

	if( $('input[name=indicadorMensagemEmail][value=1]').is(':checked')){

		if($('[name=numeroMaximoTentativoEnvio]').val().trim() == ''){
			alert('Informe número máximo de tentativas de envio');
			return false;
		}

		if($('[name=textoEmail]').val().trim() == ''){
			alert('Informe Texto Email');
			return false;
		}	
	}		
	if(validateAcaoCobrancaActionForm(form)){
	    // submeterFormPadrao(form);
	    botaoAvancarTelaEspera('/gsan/inserirAcaoCobrancaAction.do');
	}
}

 function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'criterioCobranca') {
       form.idCobrancaCriterio.value = codigoRegistro;
       form.descricaoCobrancaCriterio.value = descricaoRegistro;
        form.descricaoCobrancaCriterio.style.color = "#000000";
    }
    
    if (tipoConsulta == 'tipoServico') {
      	form.idServicoTipo.value = codigoRegistro;
 		form.descricaoServicoTipo.value = descricaoRegistro;
        form.descricaoServicoTipo.style.color = "#000000";
    }
    
    
  }

function habilitaNumeroDiasAcaoPredecessora(){
 var form = document.forms[0];
 if(form.idAcaoPredecessora.value != ""){
   form.numeroDiasEntreAcoes.disabled = false;
 }else{
  form.numeroDiasEntreAcoes.value ='';
  form.numeroDiasEntreAcoes.disabled = true;
 }
}

function validarNumeroDiasEntreAcoes(form){
   var retorno = true;
	if(form.idAcaoPredecessora.value != ''){
	 if(form.numeroDiasEntreAcoes.value == ''){
	  alert('Informe Número de Dias entre a Ação e sua Predecessora');
	  retorno = false;
	 }
	}
	return retorno;
}

function limparCriterio(){
 var form = document.forms[0];
 form.idCobrancaCriterio.value = '';
 form.descricaoCobrancaCriterio.value = '';
}


function limparServicoTipo(){
 var form = document.forms[0];
  form.idServicoTipo.value = '';
 form.descricaoServicoTipo.value = '';
}

function habilitarCamposValidarItem(){
	 var form = document.forms[0];
	 	 
	 if(form.icValidarItem[0].checked == true){		 
	   form.numeroDiasMinimoCobranca.disabled = false;
	   form.numeroDiasMaximoCobranca.disabled = false;	   
	 }else{		 
		 form.numeroDiasMinimoCobranca.value = '';
		 form.numeroDiasMinimoCobranca.disabled = true;
		 form.numeroDiasMaximoCobranca.value = '';
		 form.numeroDiasMaximoCobranca.disabled = true;		 	 
	}
}
</script>

</head>

<logic:present scope="request" name="exibirTextoPersonalizado">

	<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');habilitaNumeroDiasAcaoPredecessora();
	limitTextArea(document.forms[0].textoPersonalizado, 500, document.getElementById('utilizado'), document.getElementById('limite'));">
	
</logic:present>

<logic:notPresent scope="request" name="exibirTextoPersonalizado">

	<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');habilitaNumeroDiasAcaoPredecessora();">
	
</logic:notPresent>
<div id="formDiv">
<html:form action="/inserirAcaoCobrancaAction"
	name="AcaoCobrancaActionForm"
	type="gsan.gui.cobranca.AcaoCobrancaActionForm"
	method="post">

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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Inserir Ação de Cobrança</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<p>Para adicionar a ação de cobrança, informe os dados abaixo:</p>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td width="25%"><strong>Descrição da Ação de Cobrança:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="descricaoAcao"	size="30" maxlength="30" tabindex="1"/></td>
				</tr>
				<tr> 
                    <td><strong>Número de Dias de Validade da Ação:<font color="#FF0000">*</font></strong></td>
				     <td width="25%"><html:text maxlength="4" property="numeroDiasValidade" size="4" tabindex="2"/>
				     </td>
              </tr>
              <tr> 
                <td><strong>Ação Predecessora</strong></td>
                <td width="25%">
                    <html:select property="idAcaoPredecessora" tabindex="3" onchange="habilitaNumeroDiasAcaoPredecessora();">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoAcaoPredecessora" labelProperty="descricaoCobrancaAcao" property="id" />
					</html:select>
                </td>
              </tr>
              <tr> 
	                  <td><strong>Número de Dias entre a Ação e sua Predecessora:</strong></td>
					 <td>
	                  		<html:text property="numeroDiasEntreAcoes" size="4"
							maxlength="" tabindex="4"/>
				  	  </td>
             </tr>
             
               <tr> 
	                  <td><strong>Número de Dias de Vencimento:</strong></td>
					 <td>
	                  		<html:text property="numeroDiasVencimento" size="4"
							maxlength="" tabindex="6"/>
				  	  </td>
             </tr>
             
             <tr> 
                <td><strong>Tipo de Documento a ser Gerado:<font color="#FF0000">*</font></strong></td>
                <td width="25%">
                    <html:select property="idTipoDocumentoGerado" tabindex="7" onchange="redirecionarSubmit('exibirInserirAcaoCobrancaAction.do?action=pesquisarDocumentoTipo');">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoDocumentoTipo" labelProperty="descricaoDocumentoTipo" property="id"/>
					</html:select>
                </td>
              </tr>
              
              <logic:present scope="request" name="exibirTextoPersonalizado">
	              
	              <tr> 
	                <td><strong>Texto personalizado:<font color="#FF0000">*</font></strong></td>
	                <td width="25%">
	                    <html:textarea property="textoPersonalizado" cols="60" rows="5" onkeyup="limitTextArea(document.forms[0].textoPersonalizado, 500, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
						<strong><span id="utilizado">0</span>/<span id="limite">500</span></strong>
	                </td>
	              </tr>
	              
	          </logic:present>
              
              <tr> 
                <td><strong>Situação da Ligação de Água dos Imóveis</strong></td>
                <td width="25%">
                    <html:select property="idSituacaoLigacaoAgua" tabindex="8">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoLigacaoAguaSituacao" labelProperty="descricao" property="id" />
					</html:select>
                </td>
              </tr>
              <tr> 
                <td><strong>Situação da Ligação de Esgoto dos Imóveis</strong></td>
                <td width="25%">
                    <html:select property="idSituacaoLigacaoEsgoto" tabindex="9">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoLigacaoEsgotoSituacao" labelProperty="descricao" property="id" />
					</html:select>
                </td>
              </tr>  
              <tr> 
	            <td><strong>Critério de Cobrança:<font color="#FF0000">*</font></strong></td>
	            <td>
               		<html:text maxlength="4" 
               			property="idCobrancaCriterio" 
               			size="4" 
               			tabindex="10"
					 	onkeypress="javascript:validaEnterComMensagem(event, 'exibirInserirAcaoCobrancaAction.do?action=pesquisarCriterioCobranca', 'idCobrancaCriterio', 'Critério de Cobrança');document.forms[0].descricaoCobrancaCriterio.value = '';" />
						
					<a href="javascript:abrirPopup('exibirPesquisarCriterioCobrancaAction.do',400,600);">
						<img width="23" 
							height="21" 
							border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Critério de Cobrança" /></a>
								
					<logic:notEmpty name="AcaoCobrancaActionForm" property="idCobrancaCriterio">
						<html:text property="descricaoCobrancaCriterio" 
							size="25" 
							maxlength="30" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notEmpty>
						
					<logic:empty name="AcaoCobrancaActionForm" property="idCobrancaCriterio">
						<html:text property="descricaoCobrancaCriterio" 
							size="25" 
							maxlength="30" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:empty> 

					<a href="javascript:limparCriterio();"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" 
							title="Apagar" /></a>							
							
					</td>
              </tr>
              <tr> 
	            <td><strong>Tipo de Serviço da Ordem de Serviço a ser Gerada:</strong></td>
	            <td>
	               <html:text maxlength="4" property="idServicoTipo" size="4" tabindex="11"
					 onkeypress="javascript:validaEnterComMensagem(event, 'exibirInserirAcaoCobrancaAction.do?action=pesquisarTipoServico', 'idServicoTipo', 'Tipo de Serviço');document.forms[0].descricaoServicoTipo.value = '';" />
						<a href="javascript:abrirPopup('exibirPesquisarTipoServicoAction.do',400,600);">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Tipo de Serviço" /></a>
						<logic:notEmpty name="AcaoCobrancaActionForm" property="idServicoTipo">
							<html:text property="descricaoServicoTipo" size="25" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
						<logic:empty name="AcaoCobrancaActionForm" property="idServicoTipo">
						 <html:text property="descricaoServicoTipo" size="25" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty> 
						<a href="javascript:limparServicoTipo();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
				</td>
              </tr>
                 
               <tr> 
			   		<td height="24" colspan="2"><hr></td>
			   </tr>    
           
              
                 <tr>
                 	<td width="45%"><strong>Compõe o Cronograma:<font color="#FF0000">*</font></strong></td>
	                 <td>
		                 <html:radio property="icCompoeCronograma" value="1" tabindex="12" styleClass="radioSim"><strong>Sim</strong></html:radio>
		                 <html:radio property="icCompoeCronograma" value="2" tabindex="13" styleClass="radioNao"><strong>Não</strong></html:radio>
	                 </td>
                 </tr>
                 <tr>
                 	<td><strong>Ordem no Cronograma:<font color="#FF0000">*</font></strong></td>
                 	<td>
                 		<html:text styleClass="radioSim" property="ordemCronograma" size="4" maxlength="4" tabindex="4" disabled="true"/>
                 	</td>
             	 </tr>
                 <tr> 
	                 <td width="45%"><strong>Ação Obrigatória:<font color="#FF0000"></font><font color="#FF0000">*</font></strong></td>
	                 <td>
	                  	<html:radio property="icAcaoObrigatoria" value="1" tabindex="14" styleClass="radioSim"/><strong>Sim</strong>
	                  	<html:radio property="icAcaoObrigatoria" value="2" tabindex="15" styleClass="radioNao"/><strong>N&atilde;o</strong>
	                 </td>
                 </tr>
                 <tr> 
	                 <td width="45%"><strong>Pode ser Repetida no Ciclo:<font color="#FF0000"></font><font color="#FF0000">*</font></strong></td>
	                 <td>
	                  	<html:radio property="icRepetidaCiclo" value="1" tabindex="16" styleClass="radioSim"/><strong>Sim</strong>
	                    <html:radio property="icRepetidaCiclo" value="2" tabindex="17" styleClass="radioNao"/><strong>N&atilde;o</strong>
	                 </td>
                 </tr>
                 <tr> 
	                 <td width="45%"><strong>Provoca Suspensão de Abastecimento:<font color="#FF0000"></font><font color="#FF0000">*</font></strong></td>
	                 <td>
	                  	<html:radio property="icSuspensaoAbastecimento" value="1" tabindex="18" styleClass="radioSim"/><strong>Sim</strong>
	                    <html:radio property="icSuspensaoAbastecimento" value="2" tabindex="19" styleClass="radioNao"/><strong>N&atilde;o</strong>
	                 </td>
	             </tr>
                 <tr> 
	                 <td width="45%"><strong>Considera Débitos a Cobrar:<font color="#FF0000">*</font></strong></td>
	                 <td>
	                  	<html:radio property="icDebitosACobrar" value="1" tabindex="20" styleClass="radioSim"/><strong>Sim</strong>
	                    <html:radio property="icDebitosACobrar" value="2" tabindex="21" styleClass="radioNao"/><strong>N&atilde;o</strong>
	                 </td>
                 </tr>
                 <tr> 
	                 <td width="45%"><strong>Considera Créditos a Realizar:<font color="#FF0000">*</font></strong></td>
	                 <td>
	                  	<html:radio property="icCreditosARealizar" value="1" tabindex="22" styleClass="radioSim"/><strong>Sim</strong>
	                    <html:radio property="icCreditosARealizar" value="2" tabindex="23" styleClass="radioNao"/><strong>N&atilde;o</strong>
	                 </td>
                 </tr>
                 <tr> 
	                 <td width="45%"><strong>Considera Notas Promissória:<font color="#FF0000">*</font></strong></td>
	                 <td>
		                 <html:radio property="icNotasPromissoria" value="1" tabindex="24" styleClass="radioSim"/><strong>Sim</strong>
		                 <html:radio property="icNotasPromissoria" value="2" tabindex="25" styleClass="radioNao" /><strong>N&atilde;o</strong>
	                 </td>
                 </tr>         
                 <tr> 
	                 <td width="45%"><strong>Considera Acréscimos por Impontualidade:<font color="#FF0000">*</font></strong></td>
	                 <td>
		                 <html:radio property="icAcrescimosImpontualidade" value="1" tabindex="26" styleClass="radioSim"/><strong>Sim</strong>
		                 <html:radio property="icAcrescimosImpontualidade" value="2" tabindex="27" styleClass="radioNao"/><strong>N&atilde;o</strong>
	                 </td>
                 </tr>
                 <tr> 
	                 <td width="45%"><strong>Gera Taxa:<font color="#FF0000">*</font></strong></td>
	                 <td>
		                 <html:radio property="icGeraTaxa" value="1" tabindex="28" styleClass="radioSim"/><strong>Sim</strong>
		                 <html:radio property="icGeraTaxa" value="2" tabindex="29" styleClass="radioNao"/><strong>N&atilde;o</strong>
	                 </td>
                 </tr>
                 <tr> 
	                 <td width="45%"><strong>Pode Emitir Boletins de Cadastro:<font color="#FF0000">*</font></strong></td>
	                 <td>
		                 <html:radio property="icEmitirBoletimCadastro" value="1" tabindex="30" styleClass="radioSim"/><strong>Sim</strong>           
		                 <html:radio property="icEmitirBoletimCadastro" value="2" tabindex="31" styleClass="radioNao"/><strong>N&atilde;o</strong>
	                 </td>
                 </tr>
                 <tr> 
	                 <td width="45%"><strong>Pode ser Executada para Imóveis sem D&eacute;bito:<font color="#FF0000">*</font></strong></td>
	                 <td>
		                 <html:radio property="icImoveisSemDebitos" value="1" tabindex="32" styleClass="radioSim"/><strong>Sim</strong>
		                 <html:radio property="icImoveisSemDebitos" value="2" tabindex="33" styleClass="radioNao"/><strong>N&atilde;o</strong>
	                 </td>
                 </tr>
                 <tr>
		             <td width="45%"><strong>Usa Metas no Cronograma:<font color="#FF0000">*</font></strong></td>
	                 <td>
		                 <html:radio property="icMetasCronograma" value="1" tabindex="34" styleClass="radioSim"/><strong>Sim</strong>
		                 <html:radio property="icMetasCronograma" value="2" tabindex="35" styleClass="radioNao"/><strong>N&atilde;o</strong>
	                 </td>
                 </tr>
                 <tr>
                 	  <td><strong>Usa Ordenamento:</strong></td>
                 </tr>
	             <tr>            	
	                <td><span class="labelSubcategoraia"><strong>No Cronograma:<font color="#FF0000">*</font></strong></span></td>
					<td>
						<html:radio property="icOrdenamentoCronograma" value="1" tabindex="36"  styleClass="radioSim"/><strong>Sim</strong>
						<html:radio property="icOrdenamentoCronograma" value="2" tabindex="37" styleClass="radioNao"/><strong>N&atilde;o</strong>
					</td>
		        </tr>
		        <tr>	      	
	            	<td><span class="labelSubcategoraia"><strong>Nas Eventuais:<font color="#FF0000">*</font></strong></span></td>
	                <td>
		                <html:radio property="icOrdenamentoEventual" value="1" tabindex="38"  styleClass="radioSim"/><strong>Sim</strong>
		                <html:radio property="icOrdenamentoEventual" value="2" tabindex="39" styleClass="radioNao"/><strong>N&atilde;o</strong>
	                </td>
		        </tr>
                <tr>
	                 <td width="50%"><strong>Situação do Débito Interfere na Situação da Ação:<font color="#FF0000">*</font></strong></td>
	                 <td>
		                 <html:radio property="icDebitoInterfereAcao" value="1" tabindex="40" styleClass="radioSim"/><strong>Sim</strong>
		                 <html:radio property="icDebitoInterfereAcao" value="2" tabindex="41" styleClass="radioNao"/><strong>N&atilde;o</strong>
	                 </td>
                 </tr>
                 <tr> 
	                 <td><strong>Limite de Dias para Remuneração de Terceiros:</strong></td>
					 <td>
	                  		<html:text property="numeroDiasRemuneracaoTerceiro" size="4"
								maxlength="8" tabindex="41" styleClass="radioSim"/>
				  	  </td>
             	</tr>
                <tr>
	                 <td width="45%"><strong>Seleção ordenando por valor decrescente:<font color="#FF0000">*</font></strong></td>
	
	                  <td>
		                  <html:radio property="icOrdenarMaiorValor" value="1" tabindex="40" styleClass="radioSim"/><strong>Sim</strong>
		                  <html:radio property="icOrdenarMaiorValor" value="2" tabindex="41" styleClass="radioNao"/><strong>N&atilde;o</strong>
	                  </td>
                </tr>
                <tr>
	                 <td width="45%"><strong>Validar por item cobrado:<font color="#FF0000">*</font></strong></td>
	                 <td>
		                 <html:radio property="icValidarItem" value="1" tabindex="40" styleClass="radioSim" onclick="javascript:habilitarCamposValidarItem();"/><strong>Sim</strong>
		                 <html:radio property="icValidarItem" value="2" tabindex="41" styleClass="radioNao" onclick="javascript:habilitarCamposValidarItem();"/><strong>N&atilde;o</strong>
	                 </td>
                 </tr>
                 <tr>
	                 <td><span class="labelSubcategoraia"><strong>Quantidade de Dias Mínimo de Cobrança:<font color="#FF0000">*</font></strong></span></td>
	                 <td>
		                 <html:text property="numeroDiasMinimoCobranca" size="6"
							maxlength="6" tabindex="43" disabled="true"/>
	                 </td>
                 </tr>
                 <tr>
	                 <td><span class="labelSubcategoraia"><strong>Quantidade de Dias Máximo de Cobrança:<font color="#FF0000">*</font></strong></span></td>
	                 <td>
	                 	<html:text property="numeroDiasMaximoCobranca" size="6"
							maxlength="6" tabindex="44" disabled="true"/>
		             </td>
                 </tr>                 
                 
	             <tr>
	             	<td><strong>Ação Predecessora Alternativa</strong></td>
	             	<td width="25%">
	             		<html:select property="idAcaoPredecessoraAlternativa" tabindex="5">
	             			<html:option value="">&nbsp;</html:option>
	             			<html:options collection="colecaoAcaoPredecessora" labelProperty="descricaoCobrancaAcao" property="id" />
	           			</html:select>             		
	             	</td>             
	             </tr>              
                 
                 <tr>
	                 <td><strong>Efetuar Ação para CPF/CNPJ Válido:<font color="#FF0000">*</font></strong></td>
	                 <td>
		                 <html:radio property="icEfetuarAcaoCpfCnpjValido" value="1" tabindex="39" styleClass="radioSim"/><strong>Sim</strong>
		                 <html:radio property="icEfetuarAcaoCpfCnpjValido" value="2" tabindex="40" styleClass="radioNao"/><strong>N&atilde;o</strong>
	                 </td>
                 </tr>
                  <tr>
	                 <td><strong>Exibir Cobrança Eventual:<font color="#FF0000">*</font></strong></td>
	                 <td>
		                 <html:radio property="indicadorExibeEventual" value="1" tabindex="39" styleClass="radioSim"/><strong>Sim</strong>
		                 <html:radio property="indicadorExibeEventual" value="2" tabindex="40" styleClass="radioNao"/><strong>N&atilde;o</strong>
	                 </td>
                 </tr>
                 
                 <!-- ====================================================== ENVIO DE EMAIL E SMS ====================================================== -->
                 <tr>
                 	  <td><strong>Enviar Mensagem:</strong></td>
                 </tr>
                 <tr>
			         <td width="45%"><span class="labelSubcategoraia"><strong>Por SMS:<font color="#FF0000">*</font></strong></span></td>
					 <td>
						 <html:radio property="indicadorMensagemSMS" value="1" tabindex="39" styleClass="radioSim" onclick="javascript:verificarindicadorEnviarSmsEmail();"><strong>Sim</strong></html:radio>
						 <html:radio property="indicadorMensagemSMS" value="2" tabindex="40" styleClass="radioNao" onclick="javascript:verificarindicadorEnviarSmsEmail();"><strong>Não</strong></html:radio>
					 </td>
	           	 </tr>
		         <tr>
				     <td width="45%"><span class="labelSubcategoraia"><strong>Por Email:<font color="#FF0000">*</font></strong></span></td>
					 <td>
						 <html:radio property="indicadorMensagemEmail" value="1" tabindex="39" styleClass="radioSim" onclick="javascript:verificarindicadorEnviarSmsEmail();"><strong>Sim</strong></html:radio>
						 <html:radio property="indicadorMensagemEmail" value="2" tabindex="40" styleClass="radioNao" onclick="javascript:verificarindicadorEnviarSmsEmail();"><strong>Não</strong></html:radio>
					 </td>
		         </tr>	 
		         
		         
		         <tr>
		         	<td colspan="1">
		         		<strong>Número máximo de tentativas de envio para EMAIL: </strong>
		         	</td>
		         	<td>
		         		<html:text property="numeroMaximoTentativoEnvio" size="2" maxlength="2" styleClass="radioSim" onkeypress="return isCampoNumerico(event);"/> 
		         	</td>
		         </tr>
		         
		             	      	       
		 	   	<tr> 
			   		<td height="24" colspan="2"><hr></td>
			   	</tr>
			   	<tr>
			   		<td colspan="2">
				   		<table width="100%">
					   		<tr>
				            	<td width="40%" align="left" valign="top"><strong>Selecione os dados para o Texto:<font color="#FF0000">*</font></strong>
				            	<br/>
				            	<span class="subtitle">Obs.: Será acrescentado o código de barras ao final do texto (57 posições).</span>
				            	</td>
					            <td>
						            <html:select property="dadosTexto" size="7" styleId="dadosTexto">
							            <html:option value="-1">&nbsp;</html:option>
							            <logic:notEmpty name="colecaoDadosTexto" scope="session">
							            	<logic:iterate name="colecaoDadosTexto" id="dados">
									        	<html:option value="${dados.id}">${dados.descricaoColuna} - ${dados.tamanhoColuna} posições - ${dados.nomeColuna}</html:option>
									        </logic:iterate>
									    </logic:notEmpty>
						            </html:select>
						        </td>          
				            </tr>
				             <tr>
						        <td align="right" colspan="2">
							        <input type="button" class="bottonRightCol" id="botaoSMS" value="SMS" /> 
							        <input type="button" class="bottonRightCol" id="botaoEmail" value="E-MAIL" /> 
					            </td>
			               	</tr>			      
				            <tr> 
					            <td width="40%"><strong>Texto SMS:</strong></td>				       
					            <td>				       
					            	<html:textarea property="textoSMS" cols="44" rows="5" /><br>											            
					            </td>
				            </tr> 
				            
				            <tr> 
					            <td width="40%"><strong>Texto E-MAIL:</strong></td>
					            <td>
						            <html:textarea property="textoEmail" cols="44" rows="5" /><br>
							    </td>
				            </tr>  
			               	
				   		</table>
			   		</td>
			   	</tr>                                            	                  
               <!-- ================================================================================================================================== -->
                 
			   <tr> 
					<td height="24" colspan="2"></td>
               </tr>
               <tr>
				<td colspan="2">
					<table width="100%" border="0">
						<tr>
							<td valign="top">
								<input name="button" type="button" class="bottonRightCol" value="Desfazer" onclick="window.location.href='/gsan/exibirInserirAcaoCobrancaAction.do?menu=sim'">&nbsp;
								<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar" onClick="window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td valign="top">
							  <div align="right">
							    <gsan:controleAcessoBotao name="botaoInserir" value="Inserir" onclick="validarForm(document.forms[0]);" url="inserirAcaoCobrancaAction.do" tabindex="38"/>
							    <%-- <input name="botaoInserir" type="button" class="bottonRightCol" value="Inserir" onclick="validarForm(document.forms[0]);" tabindex="17">--%>
							  </div>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
					</table>
			</td>
		</tr>			
	</table>
   </td>
  </tr>
 </table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>
</html:html>

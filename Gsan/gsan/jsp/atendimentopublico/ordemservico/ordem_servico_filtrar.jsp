<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gsan.util.ConstantesSistema"%>
<%@ page import="gsan.atendimentopublico.ordemservico.OrdemServico"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarOrdemServicoActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>

<script language="JavaScript"><!--

	/*var disponivel = 'disponivel';
   document.getElementById(disponivel).getElementsByTagName('div')[0].style.width = document.getElementById(disponivel).getElementsByTagName('select')[0].offsetWidth+'px';
   document.getElementById(disponivel).getElementsByTagName('select')[0].style.width = document.getElementById(disponivel).offsetWidth+'px';
   document.getElementById(disponivel).onscroll = function scrollEvent() { this.getElementsByTagName('select')[0].style.width = parseInt(this.offsetWidth+this.scrollLeft)+'px'; }
   
   var selecao = 'selecao';
   document.getElementById(selecao).getElementsByTagName('div')[0].style.width = document.getElementById(selecao).getElementsByTagName('select')[0].offsetWidth+'px';
   document.getElementById(selecao).getElementsByTagName('select')[0].style.width = document.getElementById(selecao).offsetWidth+'px';
   document.getElementById(selecao).onscroll = function scrollEvent() { this.getElementsByTagName('select')[0].style.width = parseInt(this.offsetWidth+this.scrollLeft)+'px'; }*/

	function validarForm(){
   		var form = document.forms[0];
   		
   		var unidadeAtual = trim(form.unidadeAtual.value);
    	var unidadeSuperior = trim(form.unidadeSuperior.value);
    	if (unidadeAtual != '' && unidadeSuperior != '') {
    		alert('Informe somente Unidade Atual OU Unidade Superior');
    		return false;
    	}
    	
    	if(validateFiltrarOrdemServicoActionForm(form)){
			if(validaTodosPeriodos()){
				enviarSelectMultiplo('FiltrarOrdemServicoActionForm','tipoServicoSelecionados');

				form.action = 'filtrarOrdemServicoAction.do';
	   			//form.submit();
	   			submitForm(form);
			}
	  	}
    }
    function validaTodosPeriodos() {

		var form = document.forms[0];
		
    	if (comparaData(form.periodoAtendimentoInicial.value, '>', form.periodoAtendimentoFinal.value)){

			alert('Data Final do Per�odo de Atendimento � anterior � Data Inicial do Per�odo de Atendimento');
			return false;

		} else if (comparaData(form.periodoGeracaoInicial.value, '>', form.periodoGeracaoFinal.value)){

			alert('Data Final do Per�odo da Gera��o � anterior � Data Inicial do Per�odo da Gera��o');
			return false;

		} else if (comparaData(form.periodoEncerramentoInicial.value, '>', form.periodoEncerramentoFinal.value)){

			alert('Data Final do Per�odo de Encerramento � anterior � Data Inicial do Per�odo de Encerramento');
			return false;

		} else if (comparaData(form.periodoProgramacaoInicial.value, '>', form.periodoProgramacaoFinal.value)){

			alert('Data Final do Per�odo de Programa��o � anterior � Data Inicial do Per�odo de Programa��o');
			return false;

		} else if(form.codigoBairro.value != '' && form.municipio.value == '')   { 

			alert('Informe o Munic�pio');
			return false;
		}


		return true;
    }

	function limparForm(){

		var form = document.forms[0];

		form.numeroOS.value="";
		form.periodoAtendimentoInicial.value="";
		form.periodoAtendimentoFinal.value="";
		form.periodoGeracaoInicial.value="";
		form.periodoGeracaoFinal.value="";

		limparPesquisaRA();
		limparDocumentoCobranca();
		limparImovel();
		limparCliente();
		limparUnidadeGeracao();
		limparUnidadeAtual();
		limparUnidadeSuperior();
		limparPeriodoEncerramento();
		limparPeriodoProgramacao();
		limparMunicipio();
		limparBairro();
		limparLogradouro();
		
		form.origemOrdemServico[0].checked = true;
		origemOrdemServicoInformado();
		
		form.situacaoOrdemServico.selectedIndex = 0;
		form.areaBairro.selectedIndex = 0;

		//form.situacaoProgramacao[0].checked = true;

		MoverTodosDadosSelectMenu2PARAMenu1('FiltrarOrdemServicoActionForm', 'tipoServico', 'tipoServicoSelecionados');
	}
	
	function limparUnidadeSuperior(){

    	var form = document.forms[0];

    	form.unidadeSuperior.value = "";
    	form.descricaoUnidadeSuperior.value = "";
    	
  	}
	

	function limparPesquisaRA() {

    	var form = document.forms[0];

    	form.numeroRA.value = "";
    	form.descricaoRA.value = "";
    	
    	form.documentoCobranca.disabled = false;
  		form.descricaoDocumentoCobranca.disabled = false;
  		form.numeroOS.disabled = false;
    	
  	}

	function limparDocumentoCobranca() {

    	var form = document.forms[0];

    	form.documentoCobranca.value = "";
    	form.descricaoDocumentoCobranca.value = "";

    	form.numeroRA.disabled = false;
  		form.descricaoRA.disabled = false;
   		form.numeroOS.disabled = false;

  	}

	function limparImovel() {

    	var form = document.forms[0];

    	form.matriculaImovel.value = "";
    	form.inscricaoImovel.value = "";
  	}

	function limparCliente() {

    	var form = document.forms[0];

    	form.codigoCliente.value = "";
    	form.nomeCliente.value = "";
  	}

    function limparUnidadeGeracao() {
        var form = document.forms[0];

        form.unidadeGeracao.value = "";
    	form.descricaoUnidadeGeracao.value = "";
    }

    function limparUnidadeAtual() {
        var form = document.forms[0];

        form.unidadeAtual.value = "";
    	form.descricaoUnidadeAtual.value = "";
    }

    function limparMunicipio() {
        var form = document.forms[0];

        form.municipio.value = "";
    	form.descricaoMunicipio.value = "";
    	limparBairro();
    	
    }

    function limparBairro() {
        var form = document.forms[0];

        form.codigoBairro.value = "";
    	form.descricaoBairro.value = "";
    	limparAreaBairro();
    }
    
    function limparLogradouro() {
        var form = document.forms[0];

        form.logradouro.value = "";
    	form.descricaoLogradouro.value = "";
    }

    function limparPeriodoEncerramento() {

        var form = document.forms[0];
        
		form.periodoEncerramentoInicial.disabled = false;
		form.periodoEncerramentoFinal.disabled = false;
		
		form.periodoEncerramentoInicial.value="";
		form.periodoEncerramentoFinal.value="";
  	}
    
    function limparPeriodoProgramacao() {

        var form = document.forms[0];
        
		form.periodoProgramacaoInicial.disabled = false;
		form.periodoProgramacaoFinal.disabled = false;
		
		form.periodoProgramacaoInicial.value="";
		form.periodoProgramacaoFinal.value="";
  	}
    
    
    function limparPeriodoGeracao() {
        var form = document.forms[0];
        
        if (form.periodoGeracaoInicial.value != ''){
			form.periodoGeracaoInicial.value="";
		}
		
		if (form.periodoGeracaoFinal.value != ''){
			form.periodoGeracaoFinal.value="";
		}			
  	}
    
    
	//Replica Data de Atendimento
	function replicaDataAtendimento() {
		var form = document.forms[0];
		form.periodoAtendimentoFinal.value = form.periodoAtendimentoInicial.value;
	}

	//Replica Data de Geracao
	function replicaDataGeracao() {
		var form = document.forms[0];
		form.periodoGeracaoFinal.value = form.periodoGeracaoInicial.value;
	}
    
	//Replica Data de Programa��o
	function replicaDataProgramacao() {
		var form = document.forms[0];
		form.periodoProgramacaoFinal.value = form.periodoProgramacaoInicial.value;
	}

	//Replica Data de Encerramento
	function replicaDataEncerramento() {
		var form = document.forms[0];
		form.periodoEncerramentoFinal.value = form.periodoEncerramentoInicial.value;
	}

	function validaForm(){
		var form = document.forms[0];
		
		origemOrdemServicoInformado();
		
		if(form.numeroOS.value != null && form.numeroOS.value != ''){
		
			limparDocumentoCobranca();
   	    	form.documentoCobranca.disabled = true;
    		form.descricaoDocumentoCobranca.disabled = true;
    		
			limparPesquisaRA();
   	    	form.numeroRA.disabled = true;
    		form.descricaoRA.disabled = true;
		
		}else if(form.numeroRA.value != null && form.numeroRA.value != ''){

			limparDocumentoCobranca();

   	    	form.documentoCobranca.disabled = true;
    		form.descricaoDocumentoCobranca.disabled = true;
    		form.numeroOS.value = "";
    		form.numeroOS.disabled = true;

		}else if(form.documentoCobranca.value != null && form.documentoCobranca.value != ''){

			limparPesquisaRA();
			
   	    	form.numeroRA.disabled = true;
    		form.descricaoRA.disabled = true;
    		form.numeroOS.value = "";
    		form.numeroOS.disabled = true;
   		
		}
		
		if(	form.situacaoOrdemServico.value == '1' || 
			form.situacaoOrdemServico.value == '3' ||
			form.situacaoOrdemServico.value == '4' ){

			limparPeriodoEncerramento();
			
			form.periodoEncerramentoInicial.disabled = true;
			form.periodoEncerramentoFinal.disabled = true;
			
			form.colecaoAtendimentoMotivoEncerramento.selectedIndex = -1;
			form.colecaoAtendimentoMotivoEncerramento.disabled = true;
		
		}else{

			form.periodoEncerramentoInicial.disabled = false;
			form.periodoEncerramentoFinal.disabled = false;
			form.colecaoAtendimentoMotivoEncerramento.disabled = false;
		}

		/*if(form.situacaoProgramacao[2].checked){
			
			limparPeriodoProgramacao();
			
			form.periodoProgramacaoInicial.disabled = true;
			form.periodoProgramacaoFinal.disabled = true;
		}else{

			form.periodoProgramacaoInicial.disabled = false;
			form.periodoProgramacaoFinal.disabled = false;
		}*/
		
		
	}
	
	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}

	function chamarPopup2(url, tipo, objeto, codigoObjeto, altura, largura, msg){

		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
			}
		}
	}

	
	var unidade = 0;
	
	function setUnidade(tipo) {
		unidade = tipo;
	}
	/* Recuperar Popup */
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	    
	    if (tipoConsulta == 'registroAtendimento') {

	      if(!form.numeroRA.disabled){
		      form.numeroRA.value = codigoRegistro;
		      form.descricaoRA.value = descricaoRegistro;
		      form.descricaoRA.style.color = "#000000";
		      reload();
	      }
	    
	    } else if (tipoConsulta == 'documentoCobranca') {

	      if(!form.documentoCobranca.disabled){
		      form.documentoCobranca.value = codigoRegistro;
		      form.descricaoDocumentoCobranca.value = descricaoRegistro;
		      form.descricaoDocumentoCobranca.style.color = "#000000";
		      reload();
	      }
	    
	    } else if (tipoConsulta == 'imovel') {

	      form.matriculaImovel.value = codigoRegistro;
	      form.inscricaoImovel.value = descricaoRegistro;
	      form.inscricaoImovel.style.color = "#000000";
	    
	    } else if (tipoConsulta == 'cliente') {

		    form.codigoCliente.value = codigoRegistro;
		    form.nomeCliente.value = descricaoRegistro;
	      	form.nomeCliente.style.color = "#000000";
	    
   	    } else if (tipoConsulta == 'unidadeSuperior') {

	      	form.unidadeSuperior.value = codigoRegistro;
	      	form.descricaoUnidadeSuperior.value = descricaoRegistro;
			form.descricaoUnidadeSuperior.style.color = "#000000";

	    } else if (tipoConsulta == 'unidadeOrganizacional') {
	      
	   		if (unidade == 1) {

		    	form.unidadeGeracao.value = codigoRegistro;
		      	form.descricaoUnidadeGeracao.value = descricaoRegistro;
	      		form.descricaoUnidadeGeracao.style.color = "#000000";

	      	} else if (unidade == 2) {

		      	form.unidadeAtual.value = codigoRegistro;
		      	form.descricaoUnidadeAtual.value = descricaoRegistro;
      			form.descricaoUnidadeAtual.style.color = "#000000";

	      	}
	      	unidade = 0;
	    
	    } else if (tipoConsulta == 'municipio') {
		    form.municipio.value = codigoRegistro;
		    form.descricaoMunicipio.value = descricaoRegistro;
   			form.descricaoMunicipio.style.color = "#000000";
	    
	    } else if (tipoConsulta == 'bairro') {
		    form.codigoBairro.value = codigoRegistro;
		    form.descricaoBairro.value = descricaoRegistro;
   			form.descricaoBairro.style.color = "#000000";
	    
	    } else if (tipoConsulta == 'logradouro') {
  	      	form.logradouro.value = codigoRegistro;
	      	form.descricaoLogradouro.value = descricaoRegistro;
   			form.descricaoLogradouro.style.color = "#000000";
	    }

	    else if (tipoConsulta == 'localidade'){
	    	form.idLocalidade.value = codigoRegistro;
	      	form.descricaoLocalidade.value = descricaoRegistro;
   			form.descricaoLocalidade.style.color = "#000000";
   			bloquearDesbloquearSetorComercial();
   			verificarLocalidadeRota();
	    }

	    else if (tipoConsulta == 'setorComercial'){
	    	form.idSetorComercial.value = codigoRegistro;
	      	form.descricaoSetorComercial.value = descricaoRegistro;
   			form.descricaoSetorComercial.style.color = "#000000";
   			bloquearDesbloquearQuadra();
   			verificarLocalidadeRota();
	    }

	    else if (tipoConsulta == 'rota'){
	    	form.codigoRota.value = codigoRegistro;
	      	form.descricaoRota.value = descricaoRegistro;
   			form.descricaoRota.style.color = "#000000";
   			verificarLocalidadeRota();
	    }
	}
	
	
	function reload() {
		var form = document.forms[0];
		form.action = "/gsan/exibirFiltrarOrdemServicoAction.do";
		form.submit();
	}
	
	//So chama a fun��o abrirCalendario caso o campo esteja habilitado
	function chamarCalendario(fieldNameOrigem,objetoRelacionado,fieldNameDestino){
		
		if(objetoRelacionado.disabled != true){
			if(fieldNameDestino != ''){
				abrirCalendarioReplicando('FiltrarOrdemServicoActionForm', fieldNameOrigem,fieldNameDestino);
			}else{
				abrirCalendario('FiltrarOrdemServicoActionForm', fieldNameOrigem);
			}
		}
	}
	
	
	function MoveSelectedDataFromMenu1TO2(form, object, selectedObject){
		var form = document.forms[0];
		
		if (form.tipoServico.value != '-1') {
			MoverDadosSelectMenu1PARAMenu2(form, object, selectedObject);
		}
	}
	
	function MoveSelectedDataFromMenu2TO1(form, object, selectedObject){
		var form = document.forms[0];
		
		if (form.tipoServico.value != '-1') {
			MoverDadosSelectMenu2PARAMenu1(form, object, selectedObject);
		}
	}

	/* Clear �rea Bairro */
	function limparAreaBairro() {
		var form = document.forms[0];
		for(i=form.areaBairro.length-1; i>0; i--) {
			form.areaBairro.options[i] = null;
		}
	}
	
	function validarBairro(){
		var form = document.forms[0];
		if(form.municipio.value == '')   { 
			alert('Informe o Munic�pio');
		}else{
			abrirPopup('exibirPesquisarBairroAction.do?idMunicipio='+document.forms[0].municipio.value+'&indicadorUsoTodos=1', 400, 800);
		}
	}
	
    function limparPeriodos() {
        var form = document.forms[0];
        
        if (form.periodoAtendimentoInicial.value != ''){
			form.periodoAtendimentoInicial.value="";
		}
		if (form.periodoAtendimentoFinal.value != ''){
			form.periodoAtendimentoFinal.value="";
		}			

        if (form.periodoGeracaoInicial.value != ''){
			form.periodoGeracaoInicial.value="";
		}
		
		if (form.periodoGeracaoFinal.value != ''){
			form.periodoGeracaoFinal.value="";
		}			

        if (form.periodoProgramacaoInicial.value != ''){
			form.periodoProgramacaoInicial.value="";
		}
		if (form.periodoProgramacaoFinal.value != ''){
			form.periodoProgramacaoFinal.value="";
		}			

        if (form.periodoEncerramentoInicial.value != ''){
			form.periodoEncerramentoInicial.value="";
		}
		if (form.periodoEncerramentoFinal.value != ''){
			form.periodoEncerramentoFinal.value="";
		}			
  	}

    function numeroOSInformado() {
        var form = document.forms[0];
	
		if(form.numeroOS.value != null && form.numeroOS.value != ''){
			
			limparDocumentoCobranca();
			limparPesquisaRA();
			
   	    	form.documentoCobranca.readOnly = true;
    		form.descricaoDocumentoCobranca.readOnly = true;
   	    	form.numeroRA.readOnly = true;
    		form.descricaoRA.readOnly = true;
	
		}else{
		
			form.documentoCobranca.readOnly = false;
    		form.descricaoDocumentoCobranca.readOnly = false;
    		
   	    	form.numeroRA.readOnly = false;
    		form.descricaoRA.readOnly = false;
		}
	}
	
	 
	
	function habilitaRA(){
	  	var form = document.forms[0];
	
		form.numeroRA.readOnly = false;
   		form.descricaoRA.readOnly = false;
	}
	
	function desabilitaRA(){
	  	var form = document.forms[0];
	
		form.numeroRA.value = "";
    	form.descricaoRA.value = "";
		form.numeroRA.readOnly = true;
   		form.descricaoRA.readOnly = true;
	}
	
	function habilitaDocumentoCobranca(){
	  	var form = document.forms[0];
	
		form.documentoCobranca.readOnly = false;
   		form.descricaoDocumentoCobranca.readOnly = false;
	}
	
	function desabilitaDocumentoCobranca(){
	  	var form = document.forms[0];
	
		form.documentoCobranca.value = "";
    	form.descricaoDocumentoCobranca.value = "";
		form.documentoCobranca.readOnly = true;
   		form.descricaoDocumentoCobranca.readOnly = true;
	}
	
	function habilitaPeriodoAtendimento(){
	  	var form = document.forms[0];
	
		form.periodoAtendimentoInicial.readOnly = false;
		form.periodoAtendimentoFinal.readOnly = false;
	}
	
	function desabilitaPeriodoAtendimento(){
	  	var form = document.forms[0];
	
		form.periodoAtendimentoInicial.value = "";
    	form.periodoAtendimentoFinal.value = "";
//		form.periodoAtendimentoInicial.disabled = true;
	//	form.periodoAtendimentoFinal.disabled = true;
		form.periodoAtendimentoInicial.readOnly = true;
		form.periodoAtendimentoFinal.readOnly= true;

	}
	
	function habilitaAreaBairro(){
	  	var form = document.forms[0];
	
		form.areaBairro.readOnly = false;
	}
	
	function desabilitaAreaBairro(){
	  	var form = document.forms[0];
	  	
		form.areaBairro.selectedIndex = 0;
		form.areaBairro.readOnly = true;
	}
	
	function origemOrdemServicoInformado(){
	 	var form = document.forms[0];
	 	
		if (form.origemOrdemServico[0].checked){
			habilitaRA();
			desabilitaDocumentoCobranca();
			habilitaPeriodoAtendimento();
			habilitaAreaBairro();
		}else if (form.origemOrdemServico[1].checked){
			desabilitaRA();
			habilitaDocumentoCobranca();
			desabilitaPeriodoAtendimento();
			desabilitaAreaBairro();
		}else if (form.origemOrdemServico[2].checked){
			desabilitaRA();
			desabilitaDocumentoCobranca();
			desabilitaPeriodoAtendimento();
			desabilitaAreaBairro();
		}else if (form.origemOrdemServico[3].checked){
			habilitaRA();
			habilitaDocumentoCobranca();
			desabilitaPeriodoAtendimento();
			desabilitaAreaBairro();
		}else{
			form.origemOrdemServico[0].checked = true;
			habilitaRA();
			desabilitaDocumentoCobranca();
			habilitaPeriodoAtendimento();
			habilitaAreaBairro();
		}
	}

	function receberRota(idRota, descricao, codigoRota,destinoRota){
		var form = document.forms[0];
		form.codigoRota.value = codigoRota;
		verificarLocalidadeRota();
	}


	function limparPesquisaRota(){
		document.forms[0].codigoRota.value = "";
	}

	function limparPesquisaLocalidade(){
		limparPesquisaSetorComercial();
		document.forms[0].idLocalidade.value = "";		
		document.forms[0].descricaoLocalidade.value = "";
		document.forms[0].numeroQuadra.value = "";
		
	}

	function limparPesquisaSetorComercial(){
		document.forms[0].idSetorComercial.value = "";		
		document.forms[0].descricaoSetorComercial.value = "";
	}

	function bloquearDesbloquearSetorComercial(){
		var form = document.forms[0];
		if(form.idLocalidade.value != ""){
			form.idSetorComercial.readOnly = false;
			document.getElementById("pesquisaSetorComercial").href = "javascript:chamarPopup2('exibirPesquisarSetorComercialAction.do', 'idSetorComercial','idLocalidade', document.forms[0].idLocalidade.value, 275, 480, 'Informe Localidade','')";
		}
		else{
			limparPesquisaSetorComercial();
			form.idSetorComercial.readOnly = true;
			document.getElementById("pesquisaSetorComercial").href = "javascript:void(0)";
			bloquearDesbloquearQuadra();
		}
		
	}

	function bloquearDesbloquearQuadra(){
		var form = document.forms[0];
		if(form.idSetorComercial.value != ""){
			form.numeroQuadra.readOnly = false;
			form.numeroQuadra.value = "";
		}
		else{
			form.numeroQuadra.readOnly = true;
		}
	}
	
	function verificarLocalidadeRota(){
		var form = document.forms[0];
		if(form.idLocalidade.value != "" || form.idSetorComercial.value != "" || form.numeroQuadra.value != ""){
			document.getElementById("pesquisaRota").href = "javascript:void(0)";
			limparPesquisaRota();
		}	
		else if(form.codigoRota.value != ""){
			limparPesquisaLocalidade();
			limparPesquisaSetorComercial();
			form.idLocalidade.readOnly = true;
			form.idSetorComercial.readOnly = true;
			form.numeroQuadra.readOnly = true;
			document.getElementById("pesquisaLocalidade").href = "javascript:void(0)";	
			document.getElementById("pesquisaSetorComercial").href = "javascript:void(0)";	
		}

		else{
			form.idLocalidade.readOnly = false;
			form.idSetorComercial.readOnly = true;
			form.numeroQuadra.readOnly = true;
			document.getElementById("pesquisaLocalidade").href = "javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 600, 730, '', document.forms[0].idLocalidade)";	
			document.getElementById("pesquisaSetorComercial").href = "javascript:void(0)";
			document.getElementById("pesquisaRota").href = "javascript:chamarPopup('exibirPesquisarInformarRotaLeituraAction.do?limparForm=sim', 'rota', null, null, 600, 730, '', document.forms[0].codigoRota)";								
		}
	}
	
--></script>

</head>

<body leftmargin="5" topmargin="5" onload="validaForm();window.focus();javascript:setarFoco('${requestScope.numeroOS}');
OnDivScroll(document.forms[0].tipoServico, 6); OnDivScroll(document.forms[0].tipoServicoSelecionados, 6);">

<div id="formDiv">
<html:form action="/filtrarOrdemServicoAction" method="post">


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
					<td class="parabg">Filtrar Ordem de Servi&ccedil;o</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Preencha os campos para filtrar ordens de servi&ccedil;o:</td>
				</tr>
				
				
				<tr>
		          <td>
		            <strong>Origem da OS:</strong>
		          </td>
		          <td>
		            <html:radio property="origemOrdemServico" value="<%= OrdemServico.SOLICITADAS %>" onclick ="origemOrdemServicoInformado();"><strong>Solicitada</strong></html:radio>
		            <html:radio property="origemOrdemServico" value="<%= OrdemServico.SELETIVAS_COBRANCA %>" onclick ="origemOrdemServicoInformado();"><strong>Seletiva de Cobran�a</strong></html:radio>
		            <html:radio property="origemOrdemServico" value="<%= OrdemServico.SELETIVAS_HIDROMETRO %>" onclick ="origemOrdemServicoInformado();"><strong>Seletiva de Hidr�metro</strong></html:radio>
		            <html:radio property="origemOrdemServico" value="<%= OrdemServico.TODAS %>" onclick ="origemOrdemServicoInformado();"><strong>Todas</strong></html:radio>
		          </td>
		        </tr>
				
				
              	<tr> 
                	<td><strong>N&uacute;mero da OS:</strong></td>
                	<td colspan="6">
                		<strong>
                		    <!-- limparPeriodoGeracao() -->
                  			<html:text property="numeroOS" size="9" maxlength="9" 
                  						onkeyup="limparPeriodos();numeroOSInformado();"
                  						onkeypress="return isCampoNumerico(event);"/>
                  		</strong>
                  	</td>
              	</tr>

				<tr>
					<td><strong>N&uacute;mero do RA:</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="numeroRA" 
							size="9"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarOrdemServicoAction.do?objetoConsulta=1','numeroRA','Numero RA');return isCampoNumerico(event);"
							onkeyup="limparPeriodos();"
							/>
							
							<a href="javascript:chamarPopup('exibirPesquisarRegistroAtendimentoAction.do', 'registroAtendimento', null, null, 600, 730, '', document.forms[0].numeroRA);">
								
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar RA" /></a> 

							<logic:present name="numeroRAEncontrada" scope="request">
								
								<html:text property="descricaoRA" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="numeroRAEncontrada" scope="request">
								
								<html:text property="descricaoRA" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
									
							</logic:notPresent>

							<a href="javascript:limparPesquisaRA();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" /></a>
						</td>
				</tr>

				<tr>
					<td><strong>Documento de Cobran&ccedil;a:</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="documentoCobranca" 
							size="9"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarOrdemServicoAction.do?objetoConsulta=2','documentoCobranca','Documento de Cobran�a');return isCampoNumerico(event);"
							onkeyup="limparPeriodoGeracao();"
							/>
							
							<a href="javascript:abrirPopup('exibirFiltrarDocumentosCobrancaAction.do?ehPopup=true',500,800);">

								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Documento Cobran�a" /></a> 

							<logic:present name="documentoCobrancaEncontrada" scope="request">
								<html:text property="descricaoDocumentoCobranca" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="documentoCobrancaEncontrada" scope="request">
								<html:text property="descricaoDocumentoCobranca" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>

							<a href="javascript:limparDocumentoCobranca();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" /></a>

						</td>
				</tr>

           		<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

				<tr>

					<td>
						<strong>Situa&ccedil;&atilde;o da Ordem de Servi&ccedil;o:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="situacaoOrdemServico" style="width: 300px;" onchange="javascript:validaForm();">

							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>

							<html:option
								value="<%=""+OrdemServico.SITUACAO_PENDENTE%>">PENDENTES
							</html:option>

							<html:option
								value="<%=""+OrdemServico.SITUACAO_ENCERRADO%>">ENCERRADOS
							</html:option>

							<html:option
								value="<%=""+OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO%>">EXECU&Ccedil;&Atilde;O EM ANDAMENTO
							</html:option>

							<html:option
								value="<%=""+OrdemServico.SITUACAO_AGUARDANDO_LIBERACAO%>">AGUARDANDO LIBERA&Ccedil;&Atilde;O PARA EXECU&Ccedil;&Atilde;O
							</html:option>
						
						</html:select> 														
						</strong>
					</td>
				</tr>

				<%--  
				<tr>
					<td>
						<strong>Situa&ccedil;&atilde;o da Programa&ccedil;&atilde;o:</strong>
					</td>
					<td>
						<html:radio property="situacaoProgramacao" 
							value="<%//="0"%>" 
							onclick="javascrpi:validaForm();" />
						<strong>Todas</strong> 
						
						<html:radio property="situacaoProgramacao" 
							value="<%//=""+ConstantesSistema.SIM%>" 
							onclick="javascrpi:validaForm();"/>
						<strong>Programadas</strong>
						
						<html:radio property="situacaoProgramacao" 
							value="<%//=""+ConstantesSistema.NAO%>" 
							onclick="javascrpi:validaForm();"/>
						<strong>N&atilde;o Programadas</strong>
					</td>
				</tr> --%>

				<tr>
					<td width="120">
						<strong>Tipo de Servi&ccedil;o:</strong>
					</td>
					<td colspan="2">
					<table width="100%" border=0 cellpadding=0 cellspacing=0 align="left">
						<tr>
							<td width="175">
							
								<div align="left"><strong>Dispon�veis</strong></div>

								<div id='disponiveis' style="OVERFLOW: auto;WIDTH: 190px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].tipoServico, 6);" >
								
									<html:select property="tipoServico" size="6" multiple="true">
									
										<html:options collection="colecaoTipoServico" 
											labelProperty="descricao" 
											property="id"/>
									</html:select>
									
								</div>
							</td>
							
							
					

							<td width="5" valign="center"><br>
								<table width="50" align="center">
									<tr>
										<td align="center">
											<input type="button" 
												class="bottonRightCol"
												onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('FiltrarOrdemServicoActionForm', 'tipoServico', 'tipoServicoSelecionados');
												OnDivScroll(document.forms[0].tipoServico, 6); OnDivScroll(document.forms[0].tipoServicoSelecionados, 6);"
												value=" &gt;&gt; ">
										</td>
									</tr>
	
									<tr>
										<td align="center">
											<input type="button" 
												class="bottonRightCol"
												onclick="javascript:MoverDadosSelectMenu1PARAMenu2('FiltrarOrdemServicoActionForm', 'tipoServico', 'tipoServicoSelecionados');
												OnDivScroll(document.forms[0].tipoServico, 6); OnDivScroll(document.forms[0].tipoServicoSelecionados, 6);"
												value=" &nbsp;&gt;  ">
										</td>
									</tr>
	
									<tr>
										<td align="center">
											<input type="button" 
												class="bottonRightCol"
												onclick="javascript:MoverDadosSelectMenu2PARAMenu1('FiltrarOrdemServicoActionForm', 'tipoServico', 'tipoServicoSelecionados');
												OnDivScroll(document.forms[0].tipoServico, 6); OnDivScroll(document.forms[0].tipoServicoSelecionados, 6);"
												value=" &nbsp;&lt;  ">
										</td>
									</tr>
	
									<tr>
										<td align="center">
											<input type="button" 
												class="bottonRightCol"
												onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('FiltrarOrdemServicoActionForm', 'tipoServico', 'tipoServicoSelecionados');
												OnDivScroll(document.forms[0].tipoServico, 6); OnDivScroll(document.forms[0].tipoServicoSelecionados, 6);"
												value=" &lt;&lt; ">
										</td>
									</tr>
								</table>
							</td>

							<td>
								<div align="left">
									<strong>Selecionados</strong>
								</div>
								
								<div id='selecionados' style="OVERFLOW: auto;WIDTH: 190px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].tipoServicoSelecionados, 6);" >
								
									<html:select property="tipoServicoSelecionados" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('selecionados'), 6);">
									
									</html:select>
								
								</div>
								
							</td>
						</tr>
					</table>
					</td>
				</tr>


 				<tr>
		          <td>
		            <strong>Indicadores do Tipo de Servi�o:</strong>
		          </td>
		          <td>
		            <html:radio property="indicadorTipoServico" value="terceirizado"><strong>Terceirizado</strong></html:radio>
		            <html:radio property="indicadorTipoServico" value="pavimento"><strong>Pavimento</strong></html:radio>
		            <html:radio property="indicadorTipoServico" value="vistoria"><strong>Vistoria</strong></html:radio>
		            <%--<html:radio property="indicadorTipoServico" value="fiscalizacao"><strong>Fiscaliza��o</strong></html:radio> --%>
		          </td>
		        </tr>

				<logic:present name="colecaoProjeto">
				
					<tr>
		          		<td>
		            		<strong>Projeto:</strong>
		          		</td>
		          		<td>
		            
		            		<html:select property="projeto" style="width: 230px;">
							
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>
					
								<html:options collection="colecaoProjeto" labelProperty="nome"  property="id" />
							
							</html:select> 														
						
		          		</td>
		        	</tr>
		        
				</logic:present>


           		<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

				<tr>
					<td><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="matriculaImovel" 
							size="9"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarOrdemServicoAction.do?objetoConsulta=3','matriculaImovel','Matricula Im�vel');return isCampoNumerico(event);"
							onkeyup="limparPeriodoGeracao();"
							/>
							
							<a href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '', document.forms[0].matriculaImovel);">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Matricula Im�vel" /></a> 

							<logic:present name="matriculaImovelEncontrada" scope="request">
								<html:text property="inscricaoImovel" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="matriculaImovelEncontrada" scope="request">
								<html:text property="inscricaoImovel" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>

							
							<a href="javascript:limparImovel();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" /></a>
						</td>
				</tr>
				
				<tr>
					<td><strong>C&oacute;digo do Cliente:</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="codigoCliente" 
							size="9"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarOrdemServicoAction.do?objetoConsulta=4','codigoCliente','C�digo Cliente');return isCampoNumerico(event);"
							onkeyup="limparPeriodoGeracao();"
							/>
							
							<a href="javascript:chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '', document.forms[0].codigoCliente);">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Matricula Im�vel" /></a> 

							<logic:present name="codigoClienteEncontrada" scope="request">
								<html:text property="nomeCliente" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="codigoClienteEncontrada" scope="request">
								<html:text property="nomeCliente" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>

							
							<a href="javascript:limparCliente();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
							</a>					
						</td>
				</tr>

           		<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

				<tr>
					<td><strong>Unidade de Gera&ccedil;&atilde;o:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="unidadeGeracao" 
							size="4"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarOrdemServicoAction.do?objetoConsulta=5','unidadeGeracao','Unidade Gera��o');return isCampoNumerico(event);"
							/> 
			
							<a href="javascript:setUnidade(1); chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do?tipoUnidade=unidadeGeracao', 'unidadeGeracao', null, null, 275, 480, '', document.forms[0].unidadeGeracao);">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Unidade Gera��o" /></a> 

						<logic:present name="unidadeGeracaoEncontrada" scope="request">
							
							<html:text property="descricaoUnidadeGeracao" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
							
						</logic:present> 

						<logic:notPresent name="unidadeGeracaoEncontrada" scope="request">
							
							<html:text property="descricaoUnidadeGeracao" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />

						</logic:notPresent>

						<a href="javascript:limparUnidadeGeracao();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" />
						</a>
					
					</td>
				</tr>

				<tr>
					<td><strong>Unidade Atual:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="unidadeAtual" 
							size="4"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarOrdemServicoAction.do?objetoConsulta=6','unidadeAtual','Unidade Atual');return isCampoNumerico(event);"/> 
							
							<a href="javascript:setUnidade(2); chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do?tipoUnidade=unidadeAtual', 'unidadeAtual', null, null, 275, 480, '', document.forms[0].unidadeAtual);">

								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Unidade Atual" /></a> 

						<logic:present name="unidadeAtualEncontrada" scope="request">
							
							<html:text property="descricaoUnidadeAtual" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
							
						</logic:present> 

						<logic:notPresent name="unidadeAtualEncontrada" scope="request">
							
							<html:text property="descricaoUnidadeAtual" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />

						</logic:notPresent>

						<a href="javascript:limparUnidadeAtual();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					
					</td>
				</tr>

				<tr>
					<td><strong>Unidade Superior:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="unidadeSuperior" 
							size="4"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarOrdemServicoAction.do?objetoConsulta=7','unidadeSuperior','Unidade Superior');return isCampoNumerico(event);"
							/> 
							
							<a href="javascript:setUnidade(3); chamarPopup('exibirPesquisarUnidadeSuperiorAction.do?tipoUnidade=unidadeSuperior', 'unidadeSuperior', null, null, 275, 480, '', document.forms[0].unidadeSuperior);">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Unidade Superior" /></a> 

						<logic:present name="unidadeSuperiorEncontrada" scope="request">
							<html:text property="descricaoUnidadeSuperior" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="unidadeSuperiorEncontrada" scope="request">
							
							<html:text property="descricaoUnidadeSuperior" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />

						</logic:notPresent>

						<a href="javascript:limparUnidadeSuperior();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" />
						</a>
					
					</td>
				</tr>
				
           		<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>
           		
           		<tr> 
                  <td><strong>Perfil do Im�vel:</strong></td>
                  <td colspan="6"><span class="style2"><strong>
					  <html:select property="colecaoPerfilImovel" style="width: 350px; height:100px;" multiple="true">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoPerfilImovel" scope="session">
							<html:options collection="colecaoPerfilImovel" labelProperty="descricao" property="id" />
						</logic:present>
					  </html:select>
                   </strong></span></td>
                 </tr>
           		
           		<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>
              
              <tr> 
                <td><strong>Motivo de Encerramento:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
                
					<html:select property="colecaoAtendimentoMotivoEncerramento" style="width: 350px; height:100px;" multiple="true">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoAtendimentoMotivoEncerramento" scope="session">
							<html:options collection="colecaoAtendimentoMotivoEncerramento" labelProperty="descricao" property="id" />
						</logic:present>
					</html:select>                
					
                  </strong></span></td>
              </tr>

           		<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

              	<tr>
	                <td>
	                	<strong>Per&iacute;odo de Atendimento:</strong>
	                </td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	
	                	<strong> 
							
							<html:text property="periodoAtendimentoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataAtendimento();"
								onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:abrirCalendarioReplicando('FiltrarOrdemServicoActionForm', 'periodoAtendimentoInicial','periodoAtendimentoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" alt="Exibir Calend�rio" 
									tabindex="4"/></a>
							a 
							
							<html:text property="periodoAtendimentoFinal" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)"
								onkeypress="return isCampoNumerico(event);"/>
								
							<a href="javascript:abrirCalendario('FiltrarOrdemServicoActionForm', 'periodoAtendimentoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calend�rio" 
									tabindex="4"/></a>
							
							</strong>(dd/mm/aaaa)<strong> 
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>

              	<tr>
	                <td>
	                	<strong>Per&iacute;odo de Gera&ccedil;&atilde;o:</strong>
	                </td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	
	                	<strong> 
							
							<html:text property="periodoGeracaoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataGeracao();"
								onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:abrirCalendarioReplicando('FiltrarOrdemServicoActionForm', 'periodoGeracaoInicial','periodoGeracaoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calend�rio" 
									tabindex="4"/></a>
							a 
							
							<html:text property="periodoGeracaoFinal" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)"
								onkeypress="return isCampoNumerico(event);"/>
								
							<a href="javascript:abrirCalendario('FiltrarOrdemServicoActionForm', 'periodoGeracaoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calend�rio" 
									tabindex="4"/></a>
							
							</strong>(dd/mm/aaaa)<strong> 
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>

              	<tr>
	                <td>
	                	<strong>Per&iacute;odo de Programa&ccedil;&atilde;o:</strong>
	                </td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	
	                	<strong> 
							
							<html:text property="periodoProgramacaoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataProgramacao();"
								onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:chamarCalendario('periodoProgramacaoInicial',document.forms[0].periodoProgramacaoInicial,'periodoProgramacaoFinal');">
								<img border="0" 
									src="<bean:message 
									key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calend�rio" 
									tabindex="4"/></a>
							a 
							
							<html:text property="periodoProgramacaoFinal" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)"
								onkeypress="return isCampoNumerico(event);"/>

							<a href="javascript:chamarCalendario('periodoProgramacaoFinal',document.forms[0].periodoProgramacaoFinal,'');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calend�rio" 
									tabindex="4"/></a>
							
							</strong>(dd/mm/aaaa)<strong> 
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>

              	<tr>
	                <td>
	                	<strong>Per&iacute;odo de Encerramento:</strong>
	                </td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	
	                	<strong> 
							
							<html:text property="periodoEncerramentoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataEncerramento();"
								onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:chamarCalendario('periodoEncerramentoInicial',document.forms[0].periodoEncerramentoInicial,'periodoEncerramentoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calend�rio" 
									tabindex="4"/></a>
							a 
							
							<html:text property="periodoEncerramentoFinal" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)"
								onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:chamarCalendario('periodoEncerramentoFinal',document.forms[0].periodoEncerramentoFinal,'');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calend�rio" 
									tabindex="4"/></a>
							
							</strong>(dd/mm/aaaa)<strong> 
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>

           		<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>
           		
           		<tr>
					<td><strong>Localidade:</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="idLocalidade" 
							size="9"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarOrdemServicoAction.do?objetoConsulta=11','idLocalidade','Localidade');return isCampoNumerico(event);"
							/>
							
							<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 600, 730, '', document.forms[0].idLocalidade);"
							id="pesquisaLocalidade">
								
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Localidade" /></a> 

							<logic:present name="localidadeEncontrada" scope="request">
								
								<html:text property="descricaoLocalidade" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="localidadeEncontrada" scope="request">
								
								<html:text property="descricaoLocalidade" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
									
							</logic:notPresent>

							<a href="javascript:limparPesquisaLocalidade();verificarLocalidadeRota();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" /></a>
						</td>
				</tr>
				
				<tr>
					<td><strong>Setor Comercial:</strong></td>
					
					<td>
						
						<html:text maxlength="4" 
							tabindex="1"
							property="idSetorComercial" 
							size="4"
							onkeypress="validaEnterDependenciaComMensagem(event, 'exibirFiltrarOrdemServicoAction.do?objetoConsulta=12', document.forms[0].idSetorComercial,document.forms[0].idLocalidade.value,'Localidade','Setor Comercial');return isCampoNumerico(event);"
							onblur="bloquearDesbloquearQuadra()"
							/>
							
							<a href="javascript:chamarPopup2('exibirPesquisarSetorComercialAction.do', 'idSetorComercial','idLocalidade', document.forms[0].idLocalidade.value, 275, 480, 'Informe Localidade','')"
							   id="pesquisaSetorComercial"	>
								
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Setor Comercial" /></a> 

							<logic:present name="setorComercialEncontrado" scope="request">
								
								<html:text property="descricaoSetorComercial" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="setorComercialEncontrado" scope="request">
								
								<html:text property="descricaoSetorComercial" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
									
							</logic:notPresent>

							<a href="javascript:limparPesquisaSetorComercial();verificarLocalidadeRota();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" /></a>
						</td>
				</tr>
				
				<tr>
					<td><strong>Quadra:</strong></td>
					
					<td>
						<html:text maxlength="5" 
							tabindex="1"
							property="numeroQuadra" 
							size="5"
							onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>
           		
           		<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>
           		
           		<tr>
					<td><strong>C�digo da Rota:</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="codigoRota" 
							size="9"
							readonly="true"
							/>
							<a href="javascript:chamarPopup('exibirPesquisarInformarRotaLeituraAction.do?limparForm=sim', 'rota', null, null, 600, 730, '', document.forms[0].codigoRota)"
							   id = "pesquisaRota"
							>
								
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Rota" 
									/></a> 					

							<a href="javascript:limparPesquisaRota();verificarLocalidadeRota();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" /></a>
						</td>
				</tr>
           		
           		<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

				<tr>
					<td><strong>Munic&iacute;pio:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="municipio" 
							size="4"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarOrdemServicoAction.do?objetoConsulta=8','municipio','Munic�pio');return isCampoNumerico(event);"
							/> 
							
							<a href="javascript:chamarPopup('exibirPesquisarMunicipioAction.do', 'municipio', null, null, 275, 480, '', document.forms[0].municipio);">

								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Munic�pio" /></a> 

						<logic:present name="municipioEncontrada" scope="request">
							<html:text property="descricaoMunicipio" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="municipioEncontrada" scope="request">
							<html:text property="descricaoMunicipio" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						<a href="javascript:limparMunicipio();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					
					</td>
				</tr>

				<tr>
					<td><strong>Bairro:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="codigoBairro" 
							size="4"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarOrdemServicoAction.do?objetoConsulta=9','codigoBairro','Bairro');return isCampoNumerico(event);"
							/> 
							
						<a href="javascript:validarBairro();">
							<img width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Bairro" /></a> 

						<logic:present name="bairroEncontrada" scope="request">
							<html:text property="descricaoBairro" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="bairroEncontrada" scope="request">
							<html:text property="descricaoBairro" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						<a href="javascript:limparBairro();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					
					</td>
				</tr>
		        
				<tr>
					<td>
						<strong>&Aacute;rea do Bairro:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="areaBairro" style="width: 230px;">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoAreaBairro" scope="request">
								<html:options collection="colecaoAreaBairro"
									labelProperty="nome" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>

				<tr>
					<td><strong>Logradouro:</strong></td>
					<td>
						<html:text maxlength="9" 
							tabindex="1"
							property="logradouro" 
							size="9"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarOrdemServicoAction.do?objetoConsulta=10','logradouro','Logradouro'); return isCampoNumerico(event);"
							/> 
							
							<a href="javascript:chamarPopup('exibirPesquisarLogradouroAction.do', 'logradouro', null, null, 275, 480, '', document.forms[0].logradouro);">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Logradouro" /></a> 

						<logic:present name="logradouroEncontrado" scope="request">
							<html:text property="descricaoLogradouro" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="logradouroEncontrado" scope="request">
							<html:text property="descricaoLogradouro" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						<a href="javascript:limparLogradouro();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>

					</td>
				</tr>


				<tr>
					<td height="24" >
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar" 
			          		onclick="window.location.href='/gsan/exibirFiltrarOrdemServicoAction.do?menu=sim'"/>
					</td>
					
					<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol" 
							value="Filtrar" 
							onClick="javascript:validarForm()" />
					</td>
					
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form>
</div>
<script>
verificarLocalidadeRota();
bloquearDesbloquearSetorComercial();
bloquearDesbloquearQuadra();
</script>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>

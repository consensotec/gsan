<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@page
	import="gsan.atendimentopublico.ordemservico.ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper"%>
<%@page import="gsan.util.Util" isELIgnored="false"%>


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
	formName="ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript">

	
	function limparEmpresa() {
		var form = document.forms[0];
		
		form.idEmpresa.value = "";
		form.nomeEmpresa.value = "";	
		form.periodoExecucaoInicial.value = '';
		form.periodoExecucaoFinal.value = '';
				
		form.action = 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?limpar=sim';
		
		submeterFormPadrao(form);
	}
	
	function limparEmpresaTecla() {
		var form = document.forms[0];
		form.nomeEmpresa.value = "";	
	}
	
	function pesquisarEmpresa() {
		var form = document.forms[0];

		abrirPopup('exibirPesquisarEmpresaAction.do?limpaForm=S', 495, 300);
	}

	function limparPeriodoFinal() {
		var form = document.forms[0];
		
		form.periodoExecucaoFinal.value = '';
		
	}
	
	function limparComandos() {
		var form = document.forms[0];								
		form.action = 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?limpar=sim';		
		submeterFormPadrao(form);		
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
   
	    if (tipoConsulta == 'empresa') {
			form.idEmpresa.value = codigoRegistro;
			form.nomeEmpresa.value = descricaoRegistro;
			form.nomeEmpresa.style.color = "#000000";
			form.action = 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?limpar=sim';
    		submeterFormPadrao(form);
    	}

	    if (tipoConsulta == 'localidade'){
	    	if (form.tipoPesquisa.value == 'localidade'){
	      		form.localidade.value = codigoRegistro;
		  		form.nomeLocalidade.value = descricaoRegistro;	     
		  		form.nomeLocalidade.style.color = "#000000";
		  		form.localidadeFinal.value = codigoRegistro;
		  		form.nomeLocalidadeFinal.value = descricaoRegistro;	     
		  		form.nomeLocalidadeFinal.style.color = "#000000";
		  		verificaIgualdadeLocalidade();
		  		bloqueiaImovelOrdem();
	    	}
	    	else if (form.tipoPesquisa.value == 'localidadeFinal'){
	      		form.localidadeFinal.value = codigoRegistro;
		  		form.nomeLocalidadeFinal.value = descricaoRegistro;	     
		  		form.nomeLocalidadeFinal.style.color = "#000000";
		  		verificaIgualdadeLocalidade();
		  		bloqueiaImovelOrdem();
	    	}
		}
	    else if (tipoConsulta == 'setorComercial'){
			if (form.tipoPesquisa.value == 'origem'){
	    			form.codigoSetorComercialOrigem.value = codigoRegistro;
					form.descricaoSetorComercialOrigem.value = descricaoRegistro;
					form.descricaoSetorComercialOrigem.style.color = "#000000";
					form.codigoSetorComercialDestino.value = codigoRegistro;
					form.descricaoSetorComercialDestino.value = descricaoRegistro;
					form.descricaoSetorComercialDestino.style.color = "#000000";
					bloqueiaImovelOrdem();										
				} else if(form.tipoPesquisa.value == 'destino'){
					form.codigoSetorComercialDestino.value = codigoRegistro;
					form.descricaoSetorComercialDestino.value = descricaoRegistro;
					form.descricaoSetorComercialDestino.style.color = "#000000";
					bloqueiaImovelOrdem();					
				}
		}
	    else if (tipoConsulta == 'imovel') {
	    	 form.idImovel.value = codigoRegistro;
	         form.inscricaoImovel.value = descricaoRegistro;
	         bloqueiaLocalidadeSetor();
		}
	    else if (tipoConsulta == 'ordemServico') {
	    	form.ordemServico.value=codigoRegistro;
			form.descricaoOrdemServico.value=descricaoRegistro;
			bloqueiaTudo();
		}
    
    }
	
	function validaForm() {
		var form =  document.forms[0];
		if(CheckboxNaoVazio(document.forms[0].idRegistros)){
			if (validateGerarArquivoTextoContasCobrancaEmpresaActionForm(form)){
					submeterFormPadrao(form);
				}
			
		}
	
	}
	
	function selecionarComandos(){
		var form =  document.forms[0];
		if(form.idImovel.value != "" &&	form.ordemServico.value != ""){
			form.idImovel.value = "";
			form.ordemServico.value = "";
			form.localidade.readOnly = false;
			form.localidade.style.backgroundColor = '';
			form.localidade.style.color = "#000000";
			form.localidadeFinal.readOnly = false;
			form.localidadeFinal.style.backgroundColor = '';
			form.localidadeFinal.style.color = "#000000";
			form.codigoSetorComercialOrigem.readOnly = false;
			form.codigoSetorComercialOrigem.style.backgroundColor = '';
			form.codigoSetorComercialOrigem.style.color = "#000000";
			form.codigoSetorComercialDestino.readOnly = false;		
			form.codigoSetorComercialDestino.style.backgroundColor = '';
			form.codigoSetorComercialDestino.style.color = "#000000";	
			alert('Informe apenas a Matricula do Imovel ou a Ordem de Serviço');
		}
		else{
			form.action = 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?selecionarComandos=sim';
	    	form.submit();
		}
		
	}
	
	function CheckboxNaoVazio(campo){
	  form = document.forms[0];
	  retorno = false;
		
	  for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno = true;
			break;
		}
	  }
		
	  if (!retorno){
		alert('Informe o(s) comando(s) desejado(s).');
	  }
		
	  return retorno;
	} 
	
	function facilitador(objeto){
		if (objeto.id == "0" || objeto.id == undefined){
			objeto.id = "1";
			marcarTodos();
		}
		else{
			objeto.id = "0";
			desmarcarTodos();
		}
	}
	
	function movimentarOS() {
		form = document.forms[0];
	  	
	  	if (selecionouComando()) {
	  		form.action = 'exibirMovimentarOSSeletivaInspecaoAnormalidadeAction.do?limparTotalizacao=SIM&comando=' + comandoSelecionado();
	    	form.submit();
	  	}
	}
	
	function encerrarComando() {
	  	form = document.forms[0];
	  	
	  	if (selecionouComando()) {
	  	
		  	if(confirm("Confirma o encerramento do comando?")){
		  		form.action = 'encerrarComandoOSSeletivaInspecaoAnormalidadeAction.do?acao=encerrarComando';
		    	form.submit();
	    	}
	  	}
	}
	
	
	function gerarTxtComando() {
	  	form = document.forms[0];
	  	
	  	if (selecionouComando()) {
	  		form.action = 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?comando=' + comandoSelecionado()+ '&acao=gerarTxtComando';
	    	form.submit();
	  	}
	}
	
	function gerarTxtVisitaCampo(){
		var form = document.forms[0];
	    var selecionados = form.elements['idRegistro'];
		var retorno = false;

		if (selecionouComando()) {
	  		form.action = 'exibirGerarArquivosVisitaCampoAction.do?comando='+comandoSelecionado();	  		
	    	form.submit();
	  	}
	}
	
	function selecionouComando(){

		var form = document.forms[0];
	    var selecionados = form.elements['idRegistro'];
		var retorno = false;
		
		if (selecionados == undefined) {
			
			retorno = false;
			
		} else if (selecionados.value != null && selecionados.value != ''
			&& selecionados.checked) {
			
			retorno = true;
			
		} else {
		
			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].checked){
					retorno = true;
				}
			}
			
		}

		if(retorno == false){
			alert('Selecione um Comando.');
		}
		
		return retorno;
	}
	
	function comandoSelecionado(){

		var form = document.forms[0];
	    var selecionados = form.elements['idRegistro'];
		var retorno = false;
		
		if (selecionados.value != null && selecionados.value != '') {
			
			return selecionados.value;
			
		} else {
			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].checked){
					return selecionados[i].value;
				}
			}
		}
	}

	function pesquisarLocalidade(){
		var form = document.forms[0];
		if (form.localidade.readOnly == false)  {
			form.tipoPesquisa.value = 'localidade'			
			abrirPopup('exibirPesquisarLocalidadeAction.do', 275, 480);
		}
		else{
			alert('pesquisa bloqueada');
		}	
	}
	function pesquisarLocalidadeFinal(){
		var form = document.forms[0];
		if (form.localidade.readOnly == false)  {
			form.tipoPesquisa.value = 'localidadeFinal'			
			abrirPopupDependencia('exibirPesquisarLocalidadeAction.do', form.localidade.value, 'Localidade', 275, 480);
		}
		else{
			alert('pesquisa bloqueada');
		}	
	}
	function pesquisarSetorComercialOrigem(){
		var form = document.forms[0];

		if (form.codigoSetorComercialOrigem.readOnly == false) {
			form.tipoPesquisa.value = 'origem'						
			abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+form.localidade.value, form.localidade.value, 'Localidade', 275, 480);
		}
		else{
			alert('pesquisa bloqueada');
		}	
	}
	function pesquisarSetorComercialDestino(){
		var form = document.forms[0];
		if(form.codigoSetorComercialDestino.readOnly == false){
			form.tipoPesquisa.value = 'destino'		
			abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+form.localidade.value, form.localidade.value, 'Localidade', 275, 480);
		}
		else{
			alert('pesquisa bloqueada');
		}	
		
	}
	function limparLocalidade(){
		var form = document.forms[0];
		form.localidade.value = "";
		form.nomeLocalidade.value = "";
		limparLocalidadeFinal();
		limparSetorComercialOrigem();
		manterBloqueio('localidade');	
				
	}
	function limparLocalidadeFinal(){
		var form = document.forms[0];
		form.localidadeFinal.value = "";
		form.nomeLocalidadeFinal.value = "";
		manterBloqueio('localidade');
						
	}
	function limparLocalidadeFinalTecla(){
		var form = document.forms[0];
		form.nomeLocalidadeFinal.value = "";
		manterBloqueio('localidade');		
	}
	function limparSetorComercialOrigem(){
		var form = document.forms[0];
		form.codigoSetorComercialOrigem.value = "";
		form.descricaoSetorComercialOrigem.value = "";
		form.codigoSetorComercialDestino.value = "";
		form.descricaoSetorComercialDestino.value = "";	
		manterBloqueio('localidade');	
	}
	
	function limparSetorComercialOrigemTecla(){
		var form = document.forms[0];
		form.descricaoSetorComercialOrigem.value = "";
		form.descricaoSetorComercialDestino.value = "";	
		manterBloqueio('localidade');	
	}
	function limparSetorComercialDestino(){
		var form = document.forms[0];
		form.codigoSetorComercialDestino.value = "";
		form.descricaoSetorComercialDestino.value = "";
		manterBloqueio('localidade');
	}
	
	function limparSetorComercialDestinoTecla(){
		var form = document.forms[0];
		form.descricaoSetorComercialDestino.value = "";	
		manterBloqueio('localidade');	
	}
	function limparImovel() {
		var form = document.forms[0];		
	
        form.idImovel.value = "";
        form.inscricaoImovel.value = "";
        manterBloqueio('matricula');    
    }
	function limparImovelTecla(){
		var form = document.forms[0];
		form.inscricaoImovel.value = "";
		manterBloqueio('matricula');		
	}
	function limparPesquisaOs(){
		var form = document.forms[0];
		form.ordemServico.value='';
		form.descricaoOrdemServico.value='';
		manterBloqueio('ordem');
	}
	function limparPesquisaOsTecla(){
		var form = document.forms[0];
		form.descricaoOrdemServico.value='';
		manterBloqueio('ordem');		
	}
	function limparTudo(){
		var form = document.forms[0];
		form.nomeLocalidade.value = "";
		form.codigoSetorComercialOrigem.value = "";
		form.descricaoSetorComercialOrigem.value = "";
		form.codigoSetorComercialDestino.value = "";
		form.descricaoSetorComercialDestino.value = "";
		manterBloqueio('localidade');			
	}
	function habilitarPesquisa(objeto,action) {
		if (objeto.readOnly == false) {
			abrirPopup(action, 600, 500);
		}
		else{
			alert('pesquisa bloqueada');
		}	
	}
	function bloqueiaTudo(){
		var form = document.forms[0];
		if((form.localidade.readOnly == false && form.localidade.value == "") && 
		   (form.idImovel.readOnly == false && form.idImovel.value =="")){
			form.localidade.readOnly = true;
			form.localidade.style.color = "#000000";
			form.localidade.style.backgroundColor = '#EFEFEF';
			form.localidadeFinal.readOnly = true;
			form.localidadeFinal.style.color = "#000000";
			form.localidadeFinal.style.backgroundColor = '#EFEFEF';
			form.codigoSetorComercialOrigem.readOnly = true;
			form.codigoSetorComercialOrigem.style.color = "#000000";
			form.codigoSetorComercialOrigem.style.backgroundColor = '#EFEFEF';
			form.codigoSetorComercialDestino.readOnly = true;
			form.codigoSetorComercialDestino.style.color = "#000000";
			form.codigoSetorComercialDestino.style.backgroundColor = '#EFEFEF';
			form.idImovel.readOnly = true;
			form.idImovel.style.color = "#000000";
			form.idImovel.style.backgroundColor = '#EFEFEF';
			form.idImovel.value = "";
			form.inscricaoImovel.value = "";
			form.localidade.value = "";	
			form.nomeLocalidade.value = "";
			form.codigoSetorComercialOrigem.value = "";
			form.descricaoSetorComercialOrigem.value = "";
			form.codigoSetorComercialDestino.value = "";
			form.descricaoSetorComercialDestino.value = "";
		}
	}
	function desbloqueiaTudo(){
		var form = document.forms[0];
		if(form.localidade.value == "" && form.localidadeFinal.value == "" && 
		   form.codigoSetorComercialOrigem.value == "" && 
		   form.codigoSetorComercialDestino.value == "" &&
		   form.idImovel.value == ""){
			form.localidade.readOnly = false;
			form.localidade.style.backgroundColor = '';
			form.localidade.style.color = "#000000";	
			form.localidadeFinal.readOnly = false;
			form.localidadeFinal.style.backgroundColor = '';
			form.localidadeFinal.style.color = "#000000";	
			form.codigoSetorComercialOrigem.readOnly = false;
			form.codigoSetorComercialOrigem.style.backgroundColor = '';
			form.codigoSetorComercialOrigem.style.color = "#000000";	
			form.codigoSetorComercialDestino.readOnly = false;
			form.codigoSetorComercialDestino.style.backgroundColor = '';
			form.codigoSetorComercialDestino.style.color = "#000000";	
			form.idImovel.readOnly = false;	
			form.idImovel.style.backgroundColor = '';
			form.idImovel.style.color = "#000000";	
		}
	}
	function bloqueiaLocalidadeSetor(){
		var form = document.forms[0];
		if((form.idImovel.readOnly == false && form.idImovel.value !="") || 
		   (form.ordemServico.readOnly == false && form.ordemServico.value != "")){	
			form.localidade.readOnly = true;
			form.localidade.style.color = "#000000";
			form.localidade.style.backgroundColor = '#EFEFEF';
			form.localidadeFinal.readOnly = true;
			form.localidadeFinal.style.color = "#000000";
			form.localidadeFinal.style.backgroundColor = '#EFEFEF';
			form.codigoSetorComercialOrigem.readOnly = true;
			form.codigoSetorComercialOrigem.style.color = "#000000";
			form.codigoSetorComercialOrigem.style.backgroundColor = '#EFEFEF';
			form.codigoSetorComercialDestino.readOnly = true;
			form.codigoSetorComercialDestino.style.color = "#000000";
			form.codigoSetorComercialDestino.style.backgroundColor = '#EFEFEF';
			form.localidade.value = "";		
			limparTudo();
		}
	}		
	function desbloqueiaLocalidadeSetor(){
		var form = document.forms[0];
		if(form.idImovel.value == "" && 
		   form.ordemServico.value == ""){	
			form.localidade.readOnly = false;
			form.localidade.style.backgroundColor = '';
			form.localidade.style.color = "#000000";	
			form.localidadeFinal.readOnly = false;
			form.localidadeFinal.style.backgroundColor = '';
			form.localidadeFinal.style.color = "#000000";	
			verificaIgualdadeLocalidade();
			/*form.codigoSetorComercialOrigem.readOnly = false;
			form.codigoSetorComercialDestino.readOnly = false;*/
		}		
	}
	function bloqueiaImovelOrdem(){
		var form = document.forms[0];
		if((form.localidade.readOnly == false && form.localidade.value !="") || 
		   (form.localidadeFinal.readOnly == false && form.localidadeFinal.value !="") ||
		   (form.codigoSetorComercialOrigem.readOnly == false && form.codigoSetorComercialOrigem.value !="") ||
	       (form.codigoSetorComercialDestino.readOnly == false && form.codigoSetorComercialDestino.value !="")){
			form.idImovel.readOnly = true;
			form.idImovel.style.color = "#000000";
			form.idImovel.style.backgroundColor = '#EFEFEF';
			form.ordemServico.readOnly = true;
			form.ordemServico.style.color = "#000000";
			form.ordemServico.style.backgroundColor = '#EFEFEF';
			form.idImovel.value = "";
			form.inscricaoImovel.value = "";
			form.ordemServico.value = "";
			form.descricaoOrdemServico.value='';
		}		
	}
	function desbloqueiaImovelOrdem(){
		var form = document.forms[0];
		if(form.idImovel.value == "" && form.ordemServico.value ==""){	
			form.idImovel.readOnly = false;
			form.idImovel.style.backgroundColor = '';
			form.idImovel.style.color = "#000000";
			form.ordemServico.readOnly = false;
			form.ordemServico.style.backgroundColor = '';
			form.ordemServico.style.color = "#000000";
		}		
	}
	function bloqueiaOrdem(){
		var form = document.forms[0];	
		if((form.localidade.readOnly == false && form.localidade.value != "") || 
		   (form.idImovel.readOnly == false && form.idImovel.value != "") && 
		   form.ordemServico.readOnly == false){
			form.ordemServico.value = '';
			form.ordemServico.readOnly = true;
			form.ordemServico.style.color = "#000000";
			form.ordemServico.style.backgroundColor = '#EFEFEF';
		}
	}
	function desbloqueiaOrdem(){
		var form = document.forms[0];
		if(form.localidade.readOnly == true && 
		   form.idImovel.readOnly == true){
			form.ordemServico.readOnly = false;
			form.ordemServico.style.backgroundColor = '';
			form.ordemServico.style.color = "#000000";
		}
	}	
	function manterBloqueio(id){
		var form = document.forms[0];
		if(id == 'matricula'){
			matricula = form.idImovel;			
			if(matricula.value ==""){
				desbloqueiaLocalidadeSetor();
				desbloqueiaOrdem();			
			}
		}
		if(id == 'ordem'){		
			ordem = form.ordemServico;
			if(ordem.value == ""){
				desbloqueiaTudo();							
			}
		}
		else{
			localidade = form.localidade;
			localidadeFinal = form.localidadeFinal;
			setIni = form.codigoSetorComercialOrigem;
			setFin = form.codigoSetorComercialDestino;
			if(localidade.value == "" && localidadeFinal.value == "" && 
			   setIni.value == "" && setFin.value == ""){
				desbloqueiaImovelOrdem();
			}
		}
	}
	function verificaIgualdadeLocalidade(){
		var form = document.forms[0];
		var codSetOri = form.localidade;
		var codSetDest = form.localidadeFinal;
		if(codSetOri.value != codSetDest.value){
			form.codigoSetorComercialOrigem.value='';
			form.codigoSetorComercialDestino.value='';
			form.descricaoSetorComercialOrigem.value = "";
			form.descricaoSetorComercialDestino.value = "";
			form.codigoSetorComercialOrigem.readOnly = true;
			form.codigoSetorComercialOrigem.style.color = "#000000";
			form.codigoSetorComercialOrigem.style.backgroundColor = '#EFEFEF';
			form.codigoSetorComercialDestino.readOnly = true;	
			form.codigoSetorComercialDestino.style.color = "#000000";
			form.codigoSetorComercialDestino.style.backgroundColor = '#EFEFEF';		
		}
		else{
			if(form.localidade.readOnly == false &&	form.localidadeFinal.readOnly == false){
				form.codigoSetorComercialOrigem.readOnly = false;
				form.codigoSetorComercialOrigem.style.backgroundColor = '';
				form.codigoSetorComercialOrigem.style.color = "#000000";
				form.codigoSetorComercialDestino.readOnly = false;
				form.codigoSetorComercialDestino.style.backgroundColor = '';
				form.codigoSetorComercialDestino.style.color = "#000000";
			}
		}
	}
	function verificaPreenchimento(){
		var form = document.forms[0];				
		matricula = form.idImovel;
		ordem = form.ordemServico;
		localidade = form.localidade;
		localidadeFinal = form.localidadeFinal;

		if(matricula.value !=""){
			bloqueiaLocalidadeSetor();
			bloqueiaOrdem();
		}
		if(ordem.value !=""){
			bloqueiaTudo();			
		}
		if(localidade.value !="" || 
		   localidadeFinal.value !=""){			
			bloqueiaImovelOrdem();
		}
		
	}
	
	function validarCampoDataComMensagemLimpandoCampo1(campo,nomeCampo){
		if( !isBrancoOuNulo(campo.value)){
			var filtroNumero = /^[0-9\(/)]*$/;
			if(!filtroNumero.test(campo.value)){
				campo.value = '';
				alert(nomeCampo + ' deve conter apenas números.');				
				campo.focus();
				limparComandos();
				return false;
			}
		}
		
		return true;
	}
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:verificaIgualdadeLocalidade(); verificaPreenchimento();">
<%-- setarFoco('${requestScope.nomeCampo}'); --%>
<html:form action="/encerrarComandoOSSeletivaInspecaoAnormalidadeAction"
	name="ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm"
	type="gsan.gui.atendimentopublico.ordemservico.ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="tipoPesquisa" />
	<table width="770" border="0" cellspacing="5" cellpadding="0" >
	<%
		SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yy");
	%>
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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Consultar Comandos de OS Seletiva de Inspeção de Anormalidade</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0" >
				<tr>
					<td colspan="2">Para selecionar os comandos, informar os dados abaixo:</td>
				</tr>
				<tr>
					<td width="25%"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="9" property="idEmpresa" size="9"
						tabindex="14" 
						onkeypress="validaEnterComMensagem(event, 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do', 'idEmpresa', 'Empresa');
						limparEmpresaTecla();
						return isCampoNumerico(event);"
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].idEmpresa, 'Empresa');
						limparComandos();" />
					<a href="javascript:pesquisarEmpresa();"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" title="Pesquisar Empresa" border="0"></a> <logic:present
						name="empresaInexistente" scope="request">
						<html:text property="nomeEmpresa" size="40" maxlength="40"
							readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent name="empresaInexistente"
						scope="request">
						<html:text property="nomeEmpresa" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparEmpresa();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Apagar Empresa"> </a>
					</td>
				</tr>
				<tr>
				<!--  -->
					<td><strong>Período de Execução do Comando:</strong></td>
					<td><strong> <html:text maxlength="10"
						property="periodoExecucaoInicial" size="10" tabindex="10"
						onkeyup="replicarCampo(document.forms[0].periodoExecucaoFinal, this);return mascaraDataNova(this, event);"						
						onchange="validarCampoDataComMensagemLimpandoCampo1(document.forms[0].periodoExecucaoInicial, 'Período Execução Inicial');"
						 />
					<a
						href="javascript:abrirCalendarioReplicando('ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm', 'periodoExecucaoInicial', 'periodoExecucaoFinal');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					</strong> <html:text maxlength="10" property="periodoExecucaoFinal"
						tabindex="11" size="10" onkeyup="return mascaraDataNova(this, event);"
						onchange="validarCampoDataComMensagemLimpandoCampo1(document.forms[0].periodoExecucaoFinal, 'Período Execução Final');
						limparComandos();" /> <a
						href="javascript:abrirCalendario('ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm', 'periodoExecucaoFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)</td>
				</tr>
				<tr>
					<td>
						<strong>Localidade Inicial:</strong>
					</td>
					<td>
						<html:text maxlength="3" property="localidade"
						size="3"
						onkeypress="replicarCampo(form.localidadeFinal, form.localidade);
						bloqueiaImovelOrdem();
						verificaIgualdadeLocalidade();
						limparLocalidadeFinalTecla();
						validaEnterComMensagem(event, 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do', 'localidade', 'Localidade');
						return isCampoNumerico(event);"	
						onkeyup="replicarCampo(form.localidadeFinal, form.localidade);
						bloqueiaImovelOrdem();
						limparLocalidadeFinalTecla();
						verificaIgualdadeLocalidade();
						limparTudo();"					
						onchange="replicarCampo(form.localidadeFinal, form.localidade);
						verificaIgualdadeLocalidade();
						limparLocalidadeFinalTecla();
						validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].localidade, 'Localidade');
						manterBloqueio('localidade');
						limparComandos();"
						onblur="bloqueiaImovelOrdem();manterBloqueio('localidade');"						
						tabindex="6" /> 
						<a href="javascript:pesquisarLocalidade();"> 
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Localidade" /></a>
						<logic:present name="localidadeInexistente" scope="request">
							<html:text property="nomeLocalidade" size="40" maxlength="40" readonly="true" 
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present> 
						<logic:notPresent name="localidadeInexistente" scope="request">
							<html:text property="nomeLocalidade" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> 
						<a href="javascript:limparLocalidade();verificaIgualdadeLocalidade();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Localidade" />
						</a>
					
					</td>
				</tr>
				<tr>
					<td>
						<strong>Localidade Final:</strong>
					</td>
					<td>
					<!-- validaEnterComMensagem(event, 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do', 'localidadeFinal', 'Localidade Final'); -->
						<html:text maxlength="3" property="localidadeFinal"
						size="3"
						onkeypress="bloqueiaImovelOrdem();
						limparLocalidadeFinalTecla();
						verificaIgualdadeLocalidade();						
						validaEnterDependenciaComMensagemVerificandoDesabilitado(event, 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do', document.forms[0].localidadeFinal, document.forms[0].localidade.value, 'Localidade', 'Localidade Final');
						return isCampoNumerico(event);"
						onkeyup="bloqueiaImovelOrdem();
						limparLocalidadeFinalTecla();
						verificaIgualdadeLocalidade();
						return isCampoNumerico(event);"
						onchange="manterBloqueio('localidade');
						limparLocalidadeFinalTecla();
						verificaIgualdadeLocalidade();
						validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].localidadeFinal, 'LocalidadeFinal');
						limparComandos();"
						onblur="bloqueiaImovelOrdem();manterBloqueio('localidade');"				
						tabindex="6" /> 
						<a href="javascript:pesquisarLocalidadeFinal();"> 
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Localidade" /></a>
						<logic:present name="localidadeFinalInexistente" scope="request">
							<html:text property="nomeLocalidadeFinal" size="40" maxlength="40" readonly="true" 
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present> 
						<logic:notPresent name="localidadeFinalInexistente" scope="request">
							<html:text property="nomeLocalidadeFinal" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> 
						<a href="javascript:limparLocalidadeFinal();verificaIgualdadeLocalidade();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Localidade" />
						</a>
					
					</td>
				</tr>
				
				<tr>
					<td><strong>Setor Comercial Inicial:</strong></td>
					<td>					
					<html:text maxlength="3" property="codigoSetorComercialOrigem" size="3"
						onkeypress="
						validaEnterDependenciaComMensagemVerificandoDesabilitado(event, 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do', document.forms[0].codigoSetorComercialOrigem, document.forms[0].localidade.value, 'Localidade', 'Setor Comercial Inicial');
						replicarCampo(form.codigoSetorComercialDestino, form.codigoSetorComercialOrigem);						
						return isCampoNumerico(event);"
						onblur="bloqueiaImovelOrdem();"
						onchange="
						validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivosVisitaCampoAction.do?tipoPesquisa=setorComercial&tipoSetor=1', document.forms[0].codigoSetorComercialOrigem, 'Setor Comercial Inicial', document.forms[0].localidade.value, 'Localidade');
						replicarCampo(form.codigoSetorComercialDestino, form.codigoSetorComercialOrigem);
						limparComandos();"
						tabindex="5" onkeyup="javascript:replicarCampo(form.codigoSetorComercialDestino, form.codigoSetorComercialOrigem);limparSetorComercialOrigemTecla();"
						/>
					<a href="javascript:pesquisarSetorComercialOrigem();"> 
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Setor Comercial Inicial" /></a>
					<logic:present
						name="setorComercialOrigemInexistente" scope="request">
						<html:text property="descricaoSetorComercialOrigem" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> 
					<logic:notPresent
						name="setorComercialOrigemInexistente" scope="request">
						<html:text property="descricaoSetorComercialOrigem" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a
						href="javascript:limparSetorComercialOrigem();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Setor Comercial Inicial" /></a></td>
				</tr>
				<tr>
					<td><strong>Setor Comercial Final:</strong></td>
					<td><html:text maxlength="3" property="codigoSetorComercialDestino"
						size="3"
						onblur="bloqueiaImovelOrdem();"
						onkeypress="
						validaEnterDependenciaComMensagemVerificandoDesabilitado(event, 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do', document.forms[0].codigoSetorComercialDestino, document.forms[0].localidade.value, 'Localidade', 'Setor Comercial Final'); 
						return isCampoNumerico(event);"
						onchange="
						validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivosVisitaCampoAction.do?tipoPesquisa=setorComercial&tipoSetor=2', document.forms[0].codigoSetorComercialDestino, 'Setor Comercial Final', document.forms[0].localidade.value, 'Localidade ');
						limparComandos();"						
						tabindex="7" onkeyup="limparSetorComercialDestinoTecla();" /> <a
						href="javascript:pesquisarSetorComercialDestino();"> <img
						width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Setor Comercial Final" /></a> <logic:present
						name="setorComercialDestinoInexistente" scope="request">
						<html:text property="descricaoSetorComercialDestino" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="setorComercialDestinoInexistente" scope="request">
						<html:text property="descricaoSetorComercialDestino" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a
						href="javascript:limparSetorComercialDestino();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Setor Comercial Final" /></a></td>
				</tr>
				<tr>
					<td height="10" width="110">
						<strong>Matrícula do Imóvel:</strong>
					</td>
					
					<td align="left">
						<html:text property="idImovel" 
							maxlength="9"
							size="9"							
							onkeyup="bloqueiaOrdem();bloqueiaLocalidadeSetor();limparImovelTecla();return isCampoNumerico(event);"
							onkeypress="bloqueiaOrdem();bloqueiaLocalidadeSetor();
							validaEnterComMensagem(event, 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do', 'idImovel', 'Matrícula do Imóvel');
							return isCampoNumerico(event);"							
							onchange="manterBloqueio('matricula');validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].idImovel, 'Matrícula do Imóvel');
							limparComandos();"
							onblur="bloqueiaOrdem();bloqueiaLocalidadeSetor();manterBloqueio('matricula');return isCampoNumerico(event);"  />
							
						<a href="javascript:habilitarPesquisa(document.forms[0].idImovel,'exibirPesquisarImovelAction.do');"> 
							<img width="23" 
								height="21"
								src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
								border="0" title="Pesquisar Matrícula"/></a>
						<logic:present name="imovelInexistente" scope="request">
							<html:text property="inscricaoImovel"
										readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"
										size="30" maxlength="20" />
						</logic:present>
						<logic:notPresent name="imovelInexistente" scope="request">
							<html:text property="inscricaoImovel"
										readonly="true"
										title="Localidade.Setor.Quadra.Lote.Sublote"
										 style="background-color:#EFEFEF; border:0; color: #000000"
										size="20" maxlength="20" />
						</logic:notPresent>
						<a href="javascript:limparImovel();">
							<img border="0" 
								src="<bean:message key='caminho.imagens' />limparcampo.gif"
								style="cursor: hand;" title="Apagar Matrícula"/></a>
					</td>
				</tr>
				<tr>
					<td width="180"><strong>Ordem de Servi&ccedil;o:</strong></td>

					<td colspan="3" align="right">
						
						<div align="left">
					
						<html:text maxlength="9" 
							tabindex="1"
							property="ordemServico" 
							size="9"
							onkeyup="bloqueiaTudo(); limparPesquisaOsTecla();"							
							onchange="manterBloqueio('ordem');
							validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].ordemServico, 'Ordem de Serviço');
							limparComandos();"
							onblur="bloqueiaTudo();manterBloqueio('ordem');return isCampoNumerico(event);"							
							onkeypress="bloqueiaTudo();
							validaEnterComMensagem(event, 'exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do', 'ordemServico', 'Ordem de Serviço');														
							return isCampoNumerico(event);"/>
							
							<a href="javascript:javascript:habilitarPesquisa(document.forms[0].ordemServico,'exibirPesquisarOrdemServicoAction.do');">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Ordem Serviço" /></a> 

							<logic:present name="ordemServicoNaoEncontrada" scope="request">
								<html:text property="descricaoOrdemServico" 
									size="43"
									maxlength="43" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:present> 

							<logic:notPresent name="ordemServicoNaoEncontrada" scope="request">
								<html:text property="descricaoOrdemServico" 
									size="43"
									maxlength="43" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notPresent>

							<a href="javascript:limparPesquisaOs();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar Ordem Serviço" /></a>
						</div>
					</td>
				</tr>

				<tr>
					<td colspan="2" align="right"><input type="button"
						name="selecionar" class="bottonRightCol"
						value="Selecionar Comandos"
						onClick="javascript:selecionarComandos();" /></td>
				</tr>

				<tr>
					<td colspan="2" align="left"><strong>Comandos de OS Seletiva:</strong></td>
				</tr>


				<tr>
					<td colspan="2">
									<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="4" bgcolor="#000000" height="2"></td>
					</tr>
					<tr>
						<td>
						<table width="100%" bgcolor="#99CCFF" border="0">
							<tr bordercolor="#000000">
								<td width="5%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Marca</strong></td>
								<td width="15%" bordercolor="#000000" bgcolor="#90c7fc" align="center" rowspan="2">
								<strong>Comando</strong></td>
								<td width="5%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Loca. Inicial</strong></td>
								<td width="5%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Loca. Final</strong></td>
								<td width="5%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Setor Inicial</strong></td>
								<td width="5%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Setor Final</strong></td>
								<td width="5%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Imóvel</strong></td>								
								<td width="5%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Data Exec.</strong></td>
								<td width="5%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Situ.</strong></td>
								<td width="5%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Data Ence.</strong></td>
								
				
						</table>
						</td>
					</tr>
					<tr>
						<td>
						<table width="100%" bgcolor="#99CCFF">
							<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
								export="currentPageNumber=pageNumber;pageOffset"
								maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="pg" />
								<pg:param name="q" />
								<logic:present
									name="colecaoConsultarComandosOSHelper">
									<%int cont = 0;%>
									<logic:iterate
										name="colecaoConsultarComandosOSHelper"
										id="helper"
										type="ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper"
										scope="session">
										<pg:item>
											<%cont = cont + 1;
									if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
												<%} else {%>
											</tr>
											<tr bgcolor="#FFFFFF">
												<%}%>
												<td width="5%" >
												<div align="center"><html:radio property="idRegistro"
													value="${helper.idComando}" />
												</div>
												</td>

												<td align="center" width="28%"><a
													href="javascript:abrirPopup('exibirConsultarComandosOSSeletivaInspecaoAnormalidadePopupAction.do?pesquisa=nao&idComando=<%= helper.getIdComando()%>', 600, 700);">
													<%=helper.getDescComando()%> </a>
												</td>
												<td align="right" width="7%">
													<%=helper.getLocalidadeInicial() %>
												</td>
												<td align="right" width="7%">
													<%=helper.getLocalidadeFinal() %>
												</td>
												<td align="right" width="8%">
													<%=helper.getSetorInicial() %>
												</td>
												<td align="right" width="7%">
													<%=helper.getSetorFinal() %>
												</td>
												<td align="center" width="12%">
													<%=helper.getMatiruclaImovel() %>
												</td>

												<td align="center" width="7%">
													<%=formata.format(helper.getDataExecucao())%>
												</td>
												
												<td align="center" width="7%">
													<%=helper.getSituacao()%>
												</td>
												
												<td align="center" width="7%">
													<logic:notEmpty name="helper" property="dataEncerramento">
														<%=formata.format(helper.getDataEncerramento())%>
													</logic:notEmpty> 
													<logic:empty name="helper" property="dataEncerramento">
														<font color="red"><%=formata.format(helper.getDataEncerramentoPrevista())%></font>
													</logic:empty> 
												</td>

											</tr>
										</pg:item>
									</logic:iterate>
								</logic:present>
						</table>
						</td>
					</tr>
				</table>
					</td>
				</tr>



				
				
				<table width="100%" border="0">
					<tr>
						<td colspan="2">
						<div align="center"><strong><%@ include
							file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
						</td>
					</tr>
					</pg:pager>

					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>

					<tr>
						<td><strong> <font color="#FF0000"></font></strong></td>
						<td align="right">
						<div align="left"><strong><font color="#FF0000">*</font></strong>
						Campos obrigat&oacute;rios</div>
						</td>
					</tr>

					<tr>
						<td>
							<input name="Button" type="button" class="bottonRightCol"
								value="Desfazer" align="left"
								onclick="window.location.href='<html:rewrite page="/exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?menu=sim"/>'">
							<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td align="right">
							<input type="button" name="encerrar"
								class="bottonRightCol" value="Gerar Txt Comando"
								onClick="javascript:gerarTxtComando();" />
							<input type="button" name="encerrar"
								class="bottonRightCol" value="Encerrar Comando"
								onClick="javascript:encerrarComando();" />
			
							<input type="button" name="movimentar"
								class="bottonRightCol" value="Movimentar OS"
								onClick="javascript:movimentarOS();" />
						</td>
					</tr>
					<tr>
					<td>&nbsp;</td>
						<td align="right">		
							<input type="button" name="encerrar"
								class="bottonRightCol" value="Gerar Txt Visita Campo"
								onClick="javascript:gerarTxtVisitaCampo();" />
								
						</td>
					</tr>

				</table>

			</table>

			<%@ include file="/jsp/util/rodape.jsp"%> </html:form>
</body>
</html:html>

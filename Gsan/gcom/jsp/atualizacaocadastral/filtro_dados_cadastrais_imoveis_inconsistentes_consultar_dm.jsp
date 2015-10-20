<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro"%>

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
	formName="FiltrarDadosCadastraisImoveisInconsistentesDMActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>

<script language="JavaScript">
<!--

function validarForm(){
	var form = document.forms[0];
	if(validateFiltrarDadosCadastraisImoveisInconsistentesDMActionForm(form)){
		pesquisar(form);
	}
}

function pesquisar(form) {
	if(form.idImovel.value != null && form.idImovel.value != "") {
		form.action = "filtrarDadosCadastraisImoveisInconsistentesDMAction.do?objetoConsultaFiltro=imovel";
		form.submit();
	}else if(form.codigoCliente.value != null && form.codigoCliente.value != "") {
		form.action = "filtrarDadosCadastraisImoveisInconsistentesDMAction.do?objetoConsultaFiltro=cliente";
		form.submit();
	}else if(form.numeroDocumento.value != null && form.numeroDocumento.value != "") {
		form.action = "filtrarDadosCadastraisImoveisInconsistentesDMAction.do?objetoConsultaFiltro=documento";
		form.submit();
	}else {
		if(form.idEmpresa.value != null && form.idEmpresa.value != "-1"){
			if(validateDate(form)){
				enviarSelectMultiplo('FiltrarDadosCadastraisImoveisInconsistentesDMActionForm','idQuadra');
				enviarSelectMultiplo('FiltrarDadosCadastraisImoveisInconsistentesDMActionForm','idQuadraSelecionados');
				form.action = "filtrarDadosCadastraisImoveisInconsistentesDMAction.do?objetoConsultaFiltro=movimento";
				form.submit();
			}
		}else{
			alert("Nenhum parâmetro foi informado.");
		}
	}
}

function voltar(form) {
	if((form.idImovel.value != null && form.idImovel.value != "") || (form.codigoCliente.value != null && form.codigoCliente.value != "" ) ||
			(form.numeroDocumento.value != null && form.numeroDocumento.value != "" )){
		form.action = "exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do";
	} else {
		form.action = "exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=movimento";
	}
	form.submit();
}

function DateValidations () {
	this.aa = new Array("periodoMovimentoInicial", "Data de Movimento Inicial inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	this.ab = new Array("periodoMovimentoFinal", "Data de Movimento Final inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
}

function limparFormulario(form) {
	form.action = "exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=limpar";
	form.submit();
}

function replicarData() {
	var form =  document.forms[0];
	form.periodoMovimentoFinal.value = form.periodoMovimentoInicial.value;
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
	if(!campo.disabled){
  		if (objeto == null || codigoObjeto == null){
     		if(tipo == "" ){
      			abrirPopup(url,altura, largura);
     		}else{
	  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	 		}
 		}else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
			}
		}
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'localidadeOrigem') {

  		form.idLocalidade.value = codigoRegistro;
  		form.descricaoLocalidade.value = descricaoRegistro;
  		
	}else if (tipoConsulta == 'quadra') {

		if (form.tipoPesquisa.value == 'origem' && form.idQuadraInicial.disabled == false) {
			form.idQuadraInicial.value = codigoRegistro;
			form.descricaoQuadraInicial.value = descricaoRegistro;
			form.descricaoQuadraInicial.style.color = "#000000";
			form.idQuadraFinal.value = codigoRegistro;
			form.descricaoQuadraFinal.value = descricaoRegistro;
			form.descricaoQuadraFinal.style.color = "#000000";
			form.idQuadraFinal.disabled = false;
		} else if (form.tipoPesquisa.value == 'destino' && form.idQuadraInicial.disabled == false) {

			if (parseInt(form.idQuadraInicial.value) > parseInt(codigoRegistro) ) {
				alert("Quadra inicial deve ser menor ou igual a quadra final.")
			} else {
				form.idQuadraFinal.value = codigoRegistro;
				form.descricaoQuadraFinal.value = descricaoRegistro;
				form.descricaoQuadraFinal.style.color = "#000000";
			}
		}
	} else if (tipoConsulta == 'idImovel') {

  		form.idImovel.value = codigoRegistro;
  		form.inscricaoImovel.value = descricaoRegistro;
  		
	}  else if (tipoConsulta == 'cliente') {

  		form.codigoCliente.value = codigoRegistro;
  		form.nomeCliente.value = descricaoRegistro;
  		
	}

	habilitaDesabilitaCampos();
	
}

function limpar(objetoConsulta) {
	var form = document.forms[0];
	if(objetoConsulta == "1"){
		form.idLocalidade.value = "";
		form.descricaoLocalidade.value = "";
		form.idSetorComercial.value = "";
		form.descricaoSetorComercial.value = "";
		form.idQuadraInicial.value = "";
		form.descricaoQuadraInicial.value = "";
		form.idQuadraFinal.value = "";
		form.descricaoQuadraFinal.value = "";
	}else if(objetoConsulta == "2"){
		form.idSetorComercial.value = "";
	  	form.descricaoSetorComercial.value = "";
		form.idQuadraInicial.value = "";
		form.descricaoQuadraInicial.value = "";
		form.idQuadraFinal.value = "";
		form.descricaoQuadraFinal.value = "";
	}else if(objetoConsulta == "3"){
		form.idQuadraInicial.value = "";
		form.descricaoQuadraInicial.value = "";
		form.idQuadraFinal.value = "";
		form.descricaoQuadraFinal.value = "";
	}else if(objetoConsulta == "4"){
		form.idQuadraFinal.value = "";
		form.descricaoQuadraFinal.value = "";
	}else if(objetoConsulta == "5"){
		form.idImovel.value = "";
		form.inscricaoImovel.value = "";
	}else if(objetoConsulta == "6"){
		form.codigoCliente.value = "";
		form.nomeCliente.value = "";
	}
	habilitaDesabilitaCampos();
}




function carregarCadastrador() {
	var form = document.forms[0];
	if(form.idEmpresa.value != null){
		form.action = "exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do";
		form.submit();
	} else {
		form.idCadastrador.value = "-1";
		form.idCadastrador.disabled = true;
	}
}

function habilitaDesabilitaTipoInconsistencia(form) {
	var form = document.forms[0];
	if(form.indicadorSituacaoMovimento[1].checked ) {
		form.idTipoInconsistencia.value = "-1";
		form.idTipoInconsistencia.disabled = true;
	} else {
		form.idTipoInconsistencia.disabled = false;
	}

	form.action = "exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=8";
	form.submit();
}

function habilitaDesabilitaCampos() {
	
	var form = document.forms[0];
	var limparCampo = "1";
	if ( form.idEmpresa.value != null && form.idEmpresa.value != "-1" ) {
		bloqueiaImovel(form, limparCampo);
		bloqueiaCliente(form, limparCampo);
		bloqueiaDocumento(form, limparCampo);
		habilitaMovimento(form, limparCampo);
		
	} else if ( form.idImovel.value != null && form.idImovel.value != "" ) {
		bloqueiaEmpresa(form, limparCampo);
		bloqueiaCliente(form, limparCampo);
		bloqueiaDocumento(form, limparCampo);

	} else if ( form.codigoCliente.value != null && form.codigoCliente.value != "" ) {
		bloqueiaEmpresa(form, limparCampo);
		bloqueiaImovel(form, limparCampo);
		bloqueiaDocumento(form, limparCampo);

	} else if ( form.numeroDocumento.value != null && form.numeroDocumento.value != "" ) {
		bloqueiaEmpresa(form, limparCampo);	
		bloqueiaImovel(form, limparCampo);
		bloqueiaCliente(form, limparCampo);

	} else {
		habilitaDocumento(form, limparCampo);
		habilitaImovel(form, limparCampo);
		habilitaCliente(form, limparCampo) ;
		habilitaEmpresa(form, limparCampo); 
		habilitaMovimento(form, limparCampo);
	}

	if(form.indicadorSituacaoMovimento[1].checked==true){
		form.idTipoInconsistencia.disabled  = true;
	}
}

function bloqueiaMovimento(form, limparCampo) {
	
	form.periodoMovimentoInicial.disabled = true;
	form.periodoMovimentoFinal.disabled = true;
	form.idLocalidade.disabled = true;
	form.descricaoLocalidade.disabled = true;
	form.idSetorComercial.disabled = true;
	form.descricaoSetorComercial.disabled = true;
	form.idQuadra.disabled = true;
	form.idQuadraSelecionados.disabled = true;
	form.idCadastrador.disabled = true;
	form.indicadorSituacaoMovimento[0].disabled = true;
	form.indicadorSituacaoMovimento[1].disabled = true;
	form.indicadorSituacaoMovimento[2].disabled = true;
	form.idTipoInconsistencia.disabled = true;

	if (limparCampo == "1") {
		form.periodoMovimentoInicial.value = "";
		form.periodoMovimentoFinal.value = "";
		form.idLocalidade.value = "";
		form.descricaoLocalidade.value = "";
		form.idSetorComercial.value = "";
		form.descricaoSetorComercial.value = "";
		limparQuadras(form);
		form.idCadastrador.value = "";
		form.idTipoInconsistencia.value = "";
	}
}

function habilitaMovimento(form) {

	if(form.idEmpresa.value != null && form.idEmpresa.value != "-1" ) {
		form.periodoMovimentoInicial.disabled = false;
		form.periodoMovimentoFinal.disabled = false;
		form.idLocalidade.disabled = false;
		form.descricaoLocalidade.disabled = false;
		form.idCadastrador.disabled = false;
		form.indicadorSituacaoMovimento.disabled = false;
		form.idTipoInconsistencia.disabled = false;
				
	} else {
		form.periodoMovimentoInicial.disabled = true;
		form.periodoMovimentoFinal.disabled = true;
		form.idLocalidade.disabled = true;
		form.descricaoLocalidade.disabled = true;
		form.idCadastrador.disabled = true;
		form.idTipoInconsistencia.disabled = true;
		form.indicadorSituacaoMovimento[0].disabled = true;
		form.indicadorSituacaoMovimento[1].disabled = true;
		form.indicadorSituacaoMovimento[2].disabled = true;
		
		form.idLocalidade.value = "";
		form.descricaoLocalidade.value = "";
		form.periodoMovimentoInicial.value = "";
		form.periodoMovimentoFinal.value = "";
		form.idCadastrador.value = "";
		form.indicadorSituacaoMovimento.value = "";
		form.idTipoInconsistencia.value = "";
	}
	
	if ( form.idLocalidade.value != null && form.idLocalidade.value != "" ) {
		form.idSetorComercial.disabled = false;
		form.descricaoSetorComercial.disabled = false;
	} else {
		form.idSetorComercial.disabled = true;
		form.descricaoSetorComercial.disabled = true;
		form.idSetorComercial.value = "";
		form.descricaoSetorComercial.value = "";
		limparQuadras(form);
	} 

	if(form.idSetorComercial.value != null && form.idSetorComercial.value != "" ) {
		form.idQuadra.disabled = false;
		form.idQuadraSelecionados.disabled = false;
	} else {
		limparQuadras(form);
		form.idQuadra.disabled = true;
		form.idQuadraSelecionados.disabled = true;
	}
}

function habilitaEmpresa(form) {
	form.idEmpresa.disabled = false;
	habilitaMovimento(form);
}

function bloqueiaEmpresa(form, limparCampo) {

	form.idEmpresa.disabled = true;
	if ( limparCampo == "1" ) {
		form.idEmpresa.value = "-1";
	}
	bloqueiaMovimento(form);
}

function habilitaImovel(form) {
	form.idImovel.disabled = false;
}

function bloqueiaImovel(form, limparCampo) {

	form.idImovel.disabled = true;
	if ( limparCampo == "1" ){
		form.idImovel.value = "";
		form.inscricaoImovel.value = "";
	}
	
}

function habilitaCliente(form) {
	form.codigoCliente.disabled = false;
}

function bloqueiaCliente(form, limparCampo) {
	form.codigoCliente.disabled = true;
	if ( limparCampo == "1" ) {
		form.codigoCliente.value = "";
		form.nomeCliente.value = "";
	}
}

function habilitaDocumento(form) {
	form.numeroDocumento.disabled = false;
}

function bloqueiaDocumento(form, limparCampo) {
	form.numeroDocumento.disabled = true;
	if ( limparCampo == "1") {
		form.numeroDocumento.value = "";
	}
}

function recuperarDadosQuatroParametros(idRegistro, 
		descricaoRegistro, codigoRegistro, tipoConsulta) {
	var form = document.forms[0];

	if (tipoConsulta == 'setorComercialOrigem') {
	  	form.idSetorComercial.value = codigoRegistro;
	  	form.descricaoSetorComercial.value = descricaoRegistro;
	  	form.descricaoSetorComercial.style.color = "#000000"; 
		form.action = '/gsan/exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=4';
		form.submit();
	}
}	

function limparLocalidade(){
	var form = document.forms[0];
	form.idLocalidade.value = "";
	form.descricaoLocalidade.value = "";
	limparSetorComercial();
	form.idSetorComercial.disabled = true;
}

function limparQuadras(form){
	for(var posicao = 0;posicao!=form.idQuadra.length;){
		form.idQuadra.remove(posicao);
	}
	form.idQuadra.size = 0;
	
	for(var posicao = 0;posicao!=form.idQuadraSelecionados.length;){
		form.idQuadraSelecionados.remove(posicao);
	}
	form.idQuadraSelecionados.size = 0;
}

 function limparSetorComercial(){
	var form = document.forms[0];
	form.idSetorComercial.value = "";
  	form.descricaoSetorComercial.value = "";
  	limparQuadras(form);	
}

function abrirCalendarioPeriodoMovimentoInicial(){
	var form = document.forms[0];
	if(form.periodoMovimentoInicial.disabled==false){
		abrirCalendarioReplicando('FiltrarDadosCadastraisImoveisInconsistentesDMActionForm', 'periodoMovimentoInicial', 'periodoMovimentoFinal' );
	}
}

function abrirCalendarioPeriodoMovimentoFinal(){
	var form = document.forms[0];
	if(form.periodoMovimentoFinal.disabled==false){
		abrirCalendario('FiltrarDadosCadastraisImoveisInconsistentesDMActionForm', 'periodoMovimentoFinal');
	}
}

function persistirQuadras(){
	enviarSelectMultiplo('FiltrarDadosCadastraisImoveisInconsistentesDMActionForm','idQuadra');
	enviarSelectMultiplo('FiltrarDadosCadastraisImoveisInconsistentesDMActionForm','idQuadraSelecionados');
	var form = document.forms[0];
	form.action = "exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=4";
	form.submit();
}
-->
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}'); habilitaDesabilitaCampos();">

<div id="formDiv">
<html:form action="/emitirOrdemFiscalizacaoAction" 
	name="FiltrarDadosCadastraisImoveisInconsistentesDMActionForm" 
	type="gcom.gui.atualizacaocadastral.FiltrarDadosCadastraisImoveisInconsistentesDMActionForm"
	method="post">

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
			
			<input type="hidden" name="tipoPesquisa" />
		
			</div>
			</td>
			
			<td width="600" valign="top" class="centercoltext">
			
				<table width="100%" 
					border="0" 
					align="center" 
					cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Consultar Movimento de Atualizações Cadastrais</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
				
				<p>&nbsp;</p>
			
				<table bordercolor="#000000" width="100%" cellspacing="0">
					<tr>
						<td>
							<p>Para consultar os movimentos de atualizações cadastrais, informe os dados abaixo:</p>
						</td>
					</tr>
	        	</table>
	        	<p>&nbsp;</p>
	        	
	        	<table bordercolor="#000000" width="100%" cellspacing="0">
					<tr>
						<td>
						<table width="100%" border="0">
							<tr>
								<td>
									<strong>Empresa:<font color="#FF0000">*</font></strong>
								</td>
								
								<td align="left">
									<html:select property="idEmpresa" 
										onchange="carregarCadastrador();habilitaDesabilitaCampos();">
										
										<html:option value="-1">&nbsp;</html:option>
										<logic:present name="colecaoEmpresa" scope="request">					
											<html:options collection="colecaoEmpresa" 
											  labelProperty="descricao" 
											  property="id"/>
										</logic:present>
									</html:select>
								</td>
							</tr>

							<tr>
								<td>
									<strong>Per&iacute;odo do Movimento:</strong>
								</td>

             				    <td>
									<html:text property="periodoMovimentoInicial" 
										size="11" 
										maxlength="10" 
										tabindex="2" 
										onkeyup="mascaraData(this, event);replicarData();" 
										onkeypress="return isCampoNumerico(event);"/>
											
									<a href="javascript:abrirCalendarioPeriodoMovimentoInicial();">
										<img border="0" 
											src="<bean:message key='caminho.imagens'/>calendario.gif" 
											width="16" 
											height="15" 
											border="0" 
											alt="Exibir Calendário" /></a> a 
										
									<html:text property="periodoMovimentoFinal" 
										size="11" 
										maxlength="10" 
										tabindex="3" 
										onkeyup="mascaraData(this, event)"  
										onkeypress="return isCampoNumerico(event);"/>
										
									<a href="javascript:abrirCalendarioPeriodoMovimentoFinal();">
										<img border="0" 
											src="<bean:message key='caminho.imagens'/>calendario.gif" 
											width="16" 
											height="15" 
											border="0" 
											alt="Exibir Calendário" /></a>
								</td>
							</tr>
		              	
							<tr>
								<td><strong>Localidade:</strong></td>
								
								<td>
									<html:text  maxlength="3" 
										tabindex="4"
										property="idLocalidade" 
										size="3"
										onblur="habilitaDesabilitaCampos();"
										onkeyup="habilitaDesabilitaCampos();"
										onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=2', 'idLocalidade','Localidade');return isCampoNumerico(event);"/>
										
									<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].idLocalidade);">
										<img width="23" 
											height="21" 
											border="0" 
											style="cursor:hand;"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Localidade" /></a>
			
									<logic:present name="localidadeEncontrada" scope="request">
										<html:text property="descricaoLocalidade"  
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" 
											size="30" 
											maxlength="30" />
									</logic:present> 
				
									<logic:notPresent name="localidadeEncontrada" scope="request">
										<html:text property="descricaoLocalidade" 
											size="30"
											maxlength="30" 
											readonly="true"
											style="background-color:#EFEFEF;border:0;"/>
									</logic:notPresent>
									
									<a href="javascript:limparLocalidade();"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a>
								</td>
							</tr>
							
							<tr>
								<td><strong>Setor Comercial:</strong></td>
								
								<td>
									<html:text maxlength="3" 
										tabindex="5"
										property="idSetorComercial" 
										size="3"
										onblur="habilitaDesabilitaCampos();"
										onkeyup="habilitaDesabilitaCampos();"
										onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=3', 'idSetorComercial','Setor Comercial');return isCampoNumerico(event);"/>
										
									<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.forms[0].idLocalidade.value , 275, 480, 'Informe Localidade Inicial',document.forms[0].idSetorComercial);">
										<img width="23" 
											height="21" 
											border="0" 
											style="cursor:hand;"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Setor Comercial" /></a>
											
									<logic:present name="setorEncontrado" scope="request">
										<html:text property="descricaoSetorComercial"  
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" 
											size="30" 
											maxlength="30" />
									</logic:present> 
				
									<logic:notPresent name="setorEncontrado" scope="request">
										<html:text property="descricaoSetorComercial" 
											size="30"
											maxlength="30" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" />
									</logic:notPresent>
									
									<a href="javascript:limparSetorComercial();"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a>
								</td>
							</tr>
							
							<tr>
								<td width="120">
									<strong>Quadras:</strong>
								</td>
								<td colspan="2">
								<table width="100%" border=0 cellpadding=0 cellspacing=0 align="left">
									<tr>
										<td width="110">
										
											<div align="left"><strong>Disponíveis</strong></div>
			
											<div id='disponiveis' style="OVERFLOW: auto;WIDTH: 70px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].idQuadra, 6);" >
											
												<html:select property="idQuadra" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('disponiveis'), 6);">
													<logic:present name="colecaoQuadras" scope="session">	
														<html:options collection="colecaoQuadras" 
															labelProperty="numeroQuadra" 
															property="numeroQuadra"/>
													</logic:present>
												</html:select>
												
												
											</div>
										</td>
			
										<td width="5" valign="center"><br>
											<table width="50" align="center">
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('FiltrarDadosCadastraisImoveisInconsistentesDMActionForm', 'idQuadra', 'idQuadraSelecionados');
															OnDivScroll(document.forms[0].idQuadra, 6); OnDivScroll(document.forms[0].idQuadraSelecionados, 6);persistirQuadras();"
															value=" &gt;&gt; ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverDadosSelectMenu1PARAMenu2('FiltrarDadosCadastraisImoveisInconsistentesDMActionForm', 'idQuadra', 'idQuadraSelecionados');
															OnDivScroll(document.forms[0].idQuadra, 6); OnDivScroll(document.forms[0].idQuadraSelecionados, 6);persistirQuadras();"
															value=" &nbsp;&gt;  ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverDadosSelectMenu2PARAMenu1('FiltrarDadosCadastraisImoveisInconsistentesDMActionForm', 'idQuadra', 'idQuadraSelecionados');
															OnDivScroll(document.forms[0].idQuadra, 6); OnDivScroll(document.forms[0].idQuadraSelecionados, 6);persistirQuadras();"
															value=" &nbsp;&lt;  ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('FiltrarDadosCadastraisImoveisInconsistentesDMActionForm', 'idQuadra', 'idQuadraSelecionados');
															OnDivScroll(document.forms[0].idQuadra, 6); OnDivScroll(document.forms[0].idQuadraSelecionados, 6);persistirQuadras();"
															value=" &lt;&lt; ">
													</td>
												</tr>
											</table>
										</td>
			
										<td>
											<div align="left">
												<strong>Selecionados</strong>
											</div>
											
											<div id='selecionados' style="OVERFLOW: auto;WIDTH: 70px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].idQuadraSelecionados, 6);" >
											
												<html:select property="idQuadraSelecionados" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('selecionados'), 6);">
													<logic:present name="colecaoQuadrasSelecionadas" scope="session">	
														<html:options collection="colecaoQuadrasSelecionadas" 
															labelProperty="numeroQuadra" 
															property="numeroQuadra"/>
													</logic:present>
												</html:select>
											
											</div>
											
										</td>
									</tr>
								</table>
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>Cadastrador:</strong>
								</td>
								<td align="left">
									<html:select property="idCadastrador" tabindex="8" >
										<html:option value="-1">&nbsp;</html:option>
											<logic:present name="colecaoCadastrador" scope="request">					
												<html:options collection="colecaoCadastrador" 
															  labelProperty="nomeUsuario" 
															  property="id" />
											</logic:present>
									</html:select>
								</td>
							</tr>
						
							<tr>
								<td><strong>Situação Movimento:</strong></td>
								<td align="left">
									<html:radio styleId="radioInconsistente"
												property="indicadorSituacaoMovimento" 
												tabindex="9"
												value="1" 
												onclick="habilitaDesabilitaTipoInconsistencia(document.forms[0]);"/>
										<label for="radioInconsistente"><strong>Pendente</strong></label>	
									<html:radio styleId="radioAtualizado"
												property="indicadorSituacaoMovimento"
												tabindex="10" 
												value="2" 
												onclick="habilitaDesabilitaTipoInconsistencia(document.forms[0]);"/>
										<label for="radioAtualizado"><strong>Atualizado</strong></label>	 
									<html:radio styleId="radioTodos"
												property="indicadorSituacaoMovimento"
												tabindex="11" 
												value="3" 
												onclick="habilitaDesabilitaTipoInconsistencia(document.forms[0]);"/>
										<label for="radioTodos"><strong>Todos</strong></label>	 
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>Tipo de Inconsistência:</strong>
								</td>
								<td align="left">
									<html:select property="idTipoInconsistencia" tabindex="12" style="width: 370px ; font-size: 11px;" >
										<html:option value="-1">&nbsp;</html:option >
											<logic:present name="colecaoMensagem" scope="request" >					
												<html:options collection="colecaoMensagem"  
															  labelProperty="mensagem" 
															  property="id" />
											</logic:present>
									</html:select>
								</td>
							</tr>
							
							<tr> 
				                <td colspan="2" height="24" bordercolor="#000000" class="style1"> 
				                	<hr>
				                </td>
				            </tr>
							
							<tr>
								<td><strong>Dados do Imóvel/Cliente:</strong></td>
							</tr>			
							
							<tr>
								<td><strong>Matrícula do Imóvel:</strong></td>
								
								<td>
									<html:text  maxlength="10" 
										tabindex="5"
										property="idImovel" 
										size="10"
										onblur="habilitaDesabilitaCampos();"
										onkeyup="habilitaDesabilitaCampos();"
										onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=6', 'idImovel','Imovel');return isCampoNumerico(event);"/>
										
									<a href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'idImovel', null, null, 275, 480, '',document.forms[0].idImovel);">
										<img width="23" 
											height="21" 
											border="0" 
											style="cursor:hand;"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Imovel" /></a>
			
									<logic:present name="imovelEncontrada" scope="request">
										
										<html:text property="inscricaoImovel"  readonly="true"
												   style="background-color:#EFEFEF; border:0; color: #ff0000" size="30" maxlength="30" />
									</logic:present> 
				
									<logic:notPresent name="imovelEncontrada" scope="request">
										<html:text property="inscricaoImovel" 
															size="30"
															maxlength="30" 
															readonly="true"
															style="background-color:#EFEFEF;border:0;"
															 />
									</logic:notPresent>
									
									<a href="javascript:limpar(5);"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a>
								</td>
							</tr>
							
							<tr>
								<td><strong>Código do Cliente:</strong></td>
								
								<td>
									<html:text  maxlength="10" 
												tabindex="5"
												property="codigoCliente" 
												size="10"
												onblur="habilitaDesabilitaCampos();"
												onkeyup="habilitaDesabilitaCampos();"
												onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=7', 'codigoCliente','Imovel');return isCampoNumerico(event);"/>
										
									<a href="javascript:chamarPopup('exibirPesquisarClienteAction.do', 'codigoCliente', null, null, 275, 480, '',document.forms[0].codigoCliente);">
										<img width="23" 
											height="21" 
											border="0" 
											style="cursor:hand;"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Imovel" /></a>
			
									<logic:present name="clienteEncontrada" scope="request">
										
										<html:text property="nomeCliente"  readonly="true"
												   style="background-color:#EFEFEF; border:0; color: #ff0000" size="30" maxlength="30" />
									</logic:present> 
				
									<logic:notPresent name="clienteEncontrada" scope="request">
										<html:text property="nomeCliente" 
															size="30"
															maxlength="30" 
															readonly="true"
															style="background-color:#EFEFEF;border:0;"
															 />
									</logic:notPresent>
									
									<a href="javascript:limpar(6);"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a>
								</td>
							</tr>
							
							<tr>
								<td><strong>CPF/CNPJ:</strong></td>
								<td>
									<html:text  maxlength="15" 
												tabindex="5"
												property="numeroDocumento" 
												size="15"
												onblur="habilitaDesabilitaCampos();"
												onkeyup="habilitaDesabilitaCampos();"
												onkeypress="return isCampoNumerico(event);"/>
								</td>
								
							</tr>
						</table>
						</td>
					</tr>
					<tr> 
				       <td colspan="2" height="24" bordercolor="#000000" class="style1"> 
				    	   	<hr>
				       </td>
				    </tr>
				</table>
				
				<table width="100%" border="0">
					<tr>
						<td valign="top">
						<div align="left">
							<input type="button"
								name="ButtonCancelar" 
								class="bottonRightCol" 
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
								
							<input type="button"
								name="ButtonLimpar" 
								class="bottonRightCol" 
								value="Limpar"
								onClick="javascript:limparFormulario(document.forms[0]);">
						</div>
						</td>
						<td colspan="2" align="right">
							<input name="ButtonFiltrar" 
								   type="button"
								   class="bottonRightCol" 
								   value="Filtrar" 
								   align="right"
								   onClick="javascript:validarForm(document.forms[0]);"/>
						</td>
					</tr>	
				</table>
			</td>
		</tr>
	</table>

<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp"%>

</html:form>
</div>
</body>
</html:html>
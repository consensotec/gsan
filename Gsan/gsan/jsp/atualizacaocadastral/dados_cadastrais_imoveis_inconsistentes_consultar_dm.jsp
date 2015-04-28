<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gsan.util.ConstantesSistema"%>
<%@ page import="gsan.cadastro.sistemaparametro.SistemaParametro"%>

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
	formName="FiltrarDadosCadastraisImoveisInconsistentesActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>

<script language="JavaScript">
<!--


function pesquisar(form) {

	if ( form.idImovel.value != null && form.idImovel.value != "" ) {
		form.action = "exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=imovel";
		form.submit();
	} else if ( form.codigoCliente.value != null && form.codigoCliente.value != "" ) {
		form.action = "exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=cliente";
		form.submit();
	} else if ( form.numeroDocumento.value != null && form.numeroDocumento.value != "" ) {
		form.action = "exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=documento";
		form.submit();
	} else {
		if ( form.idEmpresa.value != null && form.idEmpresa.value != "-1" ) {
			form.action = "exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=movimento";
			form.submit();
		} else {
			alert("Nenhum parâmetro foi informado.");
		}
	}
	
}

function voltar(form) {

	if ( ( form.idImovel.value != null && form.idImovel.value != "") || (form.codigoCliente.value != null && form.codigoCliente.value != "" ) ||
			(form.numeroDocumento.value != null && form.numeroDocumento.value != "" ) ) {
		form.action = "exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do";

	} else {
		form.action = "exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=movimento";
	}
	form.submit();
}

var bCancel = false; 

function validateFiltrarDadosCadastraisImoveisInconsistentesActionForm(form) {                                                                   
	if (bCancel) 
    	return true; 
   	else 
    	return validateDate(form); 
} 

function DateValidations () {
	this.aa = new Array("periodoCorteInicial", "Data de Corte Inicial inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	this.ab = new Array("periodoCorteFinal", "Data de Corte Final inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
}

function gerarOS(form) {
	
}

function limparFormulario(form) {
	form.action = "exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=limpar";
	form.submit();
}

function validarCampos(form) {
 
}

function replicarData() {
	var form =  document.forms[0];

	form.periodoMovimentoFinal.value = form.periodoMovimentoInicial.value;
}


function facilitador(objeto){
	
	if (objeto.value == "0" || objeto.value == undefined ){
		objeto.value = "1";
		marcarTodos();
	}
	else{
		objeto.value = "0";
		desmarcarTodos();
	}
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

function pesquisarUnidadeNegocioDependente() {
	var form = document.forms[0];
	form.action = "exibirConsultarImovelCortadoAction.do?unidadeNegocio=pesquisar";
	form.submit();
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


function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'setorComercialOrigem') {
	  	form.idSetorComercial.value = codigoRegistro;
	  	form.descricaoSetorComercial.value = descricaoRegistro;
	  	form.descricaoSetorComercial.style.color = "#000000"; 

		habilitaDesabilitaCampos();
	}
}	

function limpar(objetoConsulta) {
	var form = document.forms[0];
	if ( form.indicadorBloqueiaTela.value != "1" ) {
		if ( objetoConsulta == "1" ) {
			
			form.idLocalidade.value = "";
			form.descricaoLocalidade.value = "";
			form.idSetorComercial.value = "";
		  	form.descricaoSetorComercial.value = "";
			form.idQuadraInicial.value = "";
			form.descricaoQuadraInicial.value = "";
			form.idQuadraFinal.value = "";
			form.descricaoQuadraFinal.value = "";
			
		} else if ( objetoConsulta == "2" ) {
			form.idSetorComercial.value = "";
		  	form.descricaoSetorComercial.value = "";
			form.idQuadraInicial.value = "";
			form.descricaoQuadraInicial.value = "";
			form.idQuadraFinal.value = "";
			form.descricaoQuadraFinal.value = "";
		} else if ( objetoConsulta == "3" ) {
			form.idQuadraInicial.value = "";
			form.descricaoQuadraInicial.value = "";
			form.idQuadraFinal.value = "";
			form.descricaoQuadraFinal.value = "";
		} else if ( objetoConsulta == "4" ) {
			form.idQuadraFinal.value = "";
			form.descricaoQuadraFinal.value = "";
		} else if ( objetoConsulta == "5" ) {
			form.idImovel.value = "";
			form.inscricaoImovel.value = "";
			
		} else if ( objetoConsulta == "6" ) {
			form.codigoCliente.value = "";
			form.nomeCliente.value = "";
		}
		
		 habilitaDesabilitaCampos();
	}	
}



function carregarCadastrador() {
	var form = document.forms[0];

	if ( form.idEmpresa.value != null ) {

		form.action = "exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=1";
		form.submit();
	} else {
		form.idCadastrador.value = "-1";
		form.idCadastrador.disabled = true;
	}

	
	
}

function habilitaDesabilitaTipoInconsistencia(form) {
	if ( form.indicadorSituacaoMovimento[1].checked ) {
		
		form.idTipoInconsistencia.value = "-1";
		form.idTipoInconsistencia.disabled = true;
	} else {
		form.idTipoInconsistencia.disabled = false;
	}
}

function pesquisarQuadraInicial() {
	var form = document.forms[0];

	if (form.idQuadraInicial.disabled == false) {
		form.tipoPesquisa.value = 'origem';
		abrirPopupDependencia('exibirPesquisarQuadraAction.do?codigoSetorComercial='+document.forms[0].idSetorComercial.value+'&idLocalidade='+document.forms[0].idLocalidade.value, form.idSetorComercial.value, 'Setor Comercial', 275, 480);
	}
}

function pesquisarQuadraFinal() {
	var form = document.forms[0];

	if (form.idQuadraFinal.disabled == false) {
		form.tipoPesquisa.value = 'destino';
		abrirPopupDependencia('exibirPesquisarQuadraAction.do?codigoSetorComercial='+document.forms[0].idSetorComercial.value+'&idLocalidade='+document.forms[0].idLocalidade.value+'&quadraInicial='+document.forms[0].idQuadraInicial.value, form.idSetorComercial.value, 'Setor Comercial', 275, 480);
		 
	}
}

function bloqueiaTela() {
	var form = document.forms[0];	
	if ( form.indicadorBloqueiaTela.value != null && form.indicadorBloqueiaTela.value == "1" ) {
		var limparCampo = "2";
		form.ButtonFiltrar.disabled = true;
		bloqueiaImovel(form, limparCampo);
		bloqueiaCliente(form, limparCampo);
		bloqueiaDocumento(form, limparCampo);
		bloqueiaEmpresa(form, limparCampo);
		bloqueiaMovimento(form, limparCampo);
	} else {

		form.ButtonFiltrar.disabled = false;
	}
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
}

function bloqueiaMovimento(form, limparCampo) {

	form.periodoMovimentoInicial.disabled = true;
	form.periodoMovimentoFinal.disabled = true;
	form.idLocalidade.disabled = true;
	form.descricaoLocalidade.disabled = true;
	form.idSetorComercial.disabled = true;
	form.descricaoSetorComercial.disabled = true;
	form.idQuadraInicial.disabled = true;
	form.descricaoQuadraInicial.disabled = true;
	form.idQuadraFinal.disabled = true;
	form.descricaoQuadraFinal.disabled = true;
	form.idCadastrador.disabled = true;
	form.indicadorSituacaoMovimento[0].disabled = true;
	form.indicadorSituacaoMovimento[1].disabled = true;
	form.indicadorSituacaoMovimento[2].disabled = true;
	form.idTipoInconsistencia.disabled = true;

	if ( limparCampo == "1" ) {
		form.periodoMovimentoInicial.value = "";
		form.periodoMovimentoFinal.value = "";
		form.idLocalidade.value = "";
		form.descricaoLocalidade.value = "";
		form.idSetorComercial.value = "";
		form.descricaoSetorComercial.value = "";
		form.idQuadraInicial.value = "";
		form.descricaoQuadraInicial.value = "";
		form.idQuadraFinal.value = "";
		form.descricaoQuadraFinal.value = "";
		form.idCadastrador.value = "";
		form.idTipoInconsistencia.value = "";
	}
	
}

function habilitaMovimento(form) {

	if ( form.idEmpresa.value != null && form.idEmpresa.value != "-1" ) {
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
		form.idQuadraInicial.disabled = true;
		form.descricaoQuadraInicial.disabled = true;
		form.idQuadraFinal.disabled = true;
		form.descricaoQuadraFinal.disabled = true;

		form.idSetorComercial.value = "";
		form.descricaoSetorComercial.value = "";
		form.idQuadraInicial.value = "";
		form.descricaoQuadraInicial.value = "";
		form.idQuadraFinal.value = "";
		form.descricaoQuadraFinal.value = "";
	} 

	if ( form.idSetorComercial.value != null && form.idSetorComercial.value != "" ) {
		form.idQuadraInicial.disabled = false;
		form.descricaoQuadraInicial.disabled = false;
	} else {
		form.idQuadraInicial.disabled = true;
		form.descricaoQuadraInicial.disabled = true;
		form.idQuadraFinal.disabled = true;
		form.descricaoQuadraFinal.disabled = true;

		form.idQuadraInicial.value = "";
		form.descricaoQuadraInicial.value = "";
		form.idQuadraFinal.value = "";
		form.descricaoQuadraFinal.value = "";
	}

	if ( form.idQuadraInicial.value != null && form.idQuadraInicial.value != "" ) {
		form.idQuadraFinal.disabled = false;
		form.descricaoQuadraFinal.disabled = false;
	} else {
		form.idQuadraFinal.disabled = true;
		form.descricaoQuadraFinal.disabled = true;
		form.idQuadraFinal.value = "";
		form.descricaoQuadraFinal.value = "";
		
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

function pesquisarImoveisQuantidadeTotal() {
	var form = document.forms[0];

		abrirPopupDependencia('exibirPesquisarQuadraAction.do?codigoSetorComercial='+document.forms[0].idSetorComercial.value+'&idLocalidade='+document.forms[0].idLocalidade.value, form.idSetorComercial.value, 'Setor Comercial', 275, 480);
}

function gerarRelatorio(form) {
	form.action = "gerarRelatorioImovelInconsistenteAction.do";
	form.submit();
}



-->
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}'); habilitaDesabilitaCampos();bloqueiaTela();">

<div id="formDiv">
<html:form action="/emitirOrdemFiscalizacaoAction" 
	name="FiltrarDadosCadastraisImoveisInconsistentesDMActionForm" 
	type="gsan.gui.atualizacaocadastral.FiltrarDadosCadastraisImoveisInconsistentesDMActionForm"
	method="post"
	onsubmit="return validateFiltrarDadosCadastraisImoveisInconsistentesActionForm(this);">

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
			<html:hidden property="indicadorBloqueiaTela" />	

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
											
									<a href="javascript:abrirCalendarioReplicando('FiltrarDadosCadastraisImoveisInconsistentesActionForm', 'periodoMovimentoInicial', 'periodoMovimentoFinal' );">
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
										
									<a href="javascript:abrirCalendario('FiltrarDadosCadastraisImoveisInconsistentesActionForm', 'periodoMovimentoFinal');">
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
										onblur="validaNumero(this);habilitaDesabilitaCampos();"
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
									
									<a href="javascript:limpar(1);"> 
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
										onblur="validaNumero(this);habilitaDesabilitaCampos();"
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
									
									<a href="javascript:limpar(2);"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a>
								</td>
							</tr>
							
							<tr>
								<td><strong>Quadra Inicial:</strong></td>
								
								<td>
									<html:text maxlength="3" 
										tabindex="6"
										property="idQuadraInicial" 
										size="3"
										onblur="validaNumero(this);habilitaDesabilitaCampos();"
										onkeyup="habilitaDesabilitaCampos();"
										onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=4', 'idQuadraInicial','Quadra Inicial');return isCampoNumerico(event);"/>
										
									<a href="javascript:pesquisarQuadraInicial();">
										<img width="23" 
											height="21" 
											border="0" 
											style="cursor:hand;"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Quadra" /></a>
											
									<logic:present name="quadraEncontrado" scope="request">
										<html:text property="descricaoQuadraInicial"  
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" 
											size="30" 
											maxlength="30" />
									</logic:present> 
				
									<logic:notPresent name="quadraEncontrado" scope="request">
										<html:text property="descricaoQuadraInicial" 
											size="30"
											maxlength="30" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" />
									</logic:notPresent>
									<a href="javascript:limpar(3);"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a>
								</td>
							</tr>
								
							<tr>
								<td><strong>Quadra Final:</strong></td>
								
								<td>
									
									<html:text maxlength="3" 
										tabindex="7"
										property="idQuadraFinal" 
										size="3"
										onblur="validaNumero(this);habilitaDesabilitaCampos();"
										onkeyup="habilitaDesabilitaCampos();"
										onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do?objetoConsulta=5', 'idQuadraFinal','Quadra Final');return isCampoNumerico(event);"/>
										
									<a href="javascript:pesquisarQuadraFinal();">
										<img width="23" 
											height="21" 
											border="0" 
											style="cursor:hand;"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Quadra" /></a>
											
									<logic:present name="quadraFinalEncontrado" scope="request">
										<html:text property="descricaoQuadraFinal"  readonly="true"
												   style="background-color:#EFEFEF; border:0; color: #ff0000" size="30" maxlength="30" />
									</logic:present> 
				
									<logic:notPresent name="quadraFinalEncontrado" scope="request">
										<html:text property="descricaoQuadraFinal" 
											size="30"
											maxlength="30" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" />
									</logic:notPresent>
									<a href="javascript:limpar(4);"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a>
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
									<html:radio property="indicadorSituacaoMovimento" 
												tabindex="9"
												value="1" 
												onclick="habilitaDesabilitaTipoInconsistencia(document.forms[0]);"/>
										<strong>Inconsistente</strong> 
									<html:radio property="indicadorSituacaoMovimento"
												tabindex="10" 
												value="2" 
												onclick="habilitaDesabilitaTipoInconsistencia(document.forms[0]);"/>
										<strong>Atualizado</strong>
									<html:radio property="indicadorSituacaoMovimento"
												tabindex="11" 
												value="3" 
												onclick="habilitaDesabilitaTipoInconsistencia(document.forms[0]);"/>
										<strong>Todos</strong>	 
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
										onblur="validaNumero(this);habilitaDesabilitaCampos();"
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
												onblur="validaNumero(this);habilitaDesabilitaCampos();"
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
								<td><strong>Número de Documento:</strong></td>
								<td>
									<html:text  maxlength="15" 
												tabindex="5"
												property="numeroDocumento" 
												size="15"
												onblur="validaNumero(this);habilitaDesabilitaCampos();"
												onkeyup="habilitaDesabilitaCampos();"/>
								</td>
								
							</tr>
							<tr>
								<td colspan="2" align="right">
								<input name="ButtonFiltrar" 
									   type="button"
									   class="bottonRightCol" 
									   value="Filtrar" 
									   align="right"
									   onClick="javascript:pesquisar(document.forms[0]);"/>
								</td>
							</tr>
						</table>
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
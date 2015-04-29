<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gsan.atendimentopublico.ordemservico.bean.SituacaoEncontradaHelper"%>
<%@ page import="gsan.util.ConstantesSistema" %>
<%@ page import="gsan.util.Util" %>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gsan.atendimentopublico.ordemservico.FiscalizacaoSituacao" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InformarRetornoOSFiscalizacaoActionForm" />

<script language="JavaScript">
  	function validaForm(){
    	var form = document.forms[0];
	  	
	  	if(validateInformarRetornoOSFiscalizacaoActionForm(form) && validarAutoInfracao(form) && validarSituacaoLigacao(form)){
			if(validaTodosRadioButton()){		     
				if(form.situacaoLigacao != null && form.situacaoLigacao.value == 3){
					if(validarDadosLigacaoEsgoto(form)){
						botaoAvancarTelaEspera('/gsan/informarRetornoOSFiscalizacaoAction.do');
					}
				}else{
					botaoAvancarTelaEspera('/gsan/informarRetornoOSFiscalizacaoAction.do');
				}
   	    	}
   	  	}
  	}
  	
  	function validarSituacaoLigacao(form){
  		if(form.situacaoLigacao != null && form.situacaoLigacao.value == -1){
  			alert('Informe Situa��o da Liga��o do Esgoto.');
  			return false;
  		}else{
  			return true;
  		}
  	}
  	
  	function desabilitaCombo(valor){
  		var form = document.forms[0];

  		if(valor == '2'){

			form.atendimentoMotivoEncerramento.value = "-1";
			form.atendimentoMotivoEncerramento.disabled = true;

			form.parecerEncerramento.value = "";
			form.parecerEncerramento.disabled = true;
			
		}else{
			form.atendimentoMotivoEncerramento.disabled = false;
			form.parecerEncerramento.disabled = false;
		}
  	}
  	
  	function validaRadioButton(nomeCampo,mensagem){
		
		var alerta = "";
		if(!nomeCampo[0].checked && !nomeCampo[1].checked){
			alerta = "Informe " + mensagem +".";
		}
		return alerta;
   	}
   	
   	 function validaRadioButtonTres(nomeCampo,mensagem){
		
		var alerta = "";
		if(!nomeCampo[0].checked && !nomeCampo[1].checked && !nomeCampo[2].checked && !nomeCampo[3].checked){
			alerta = "Informe " + mensagem +".";
		}
		return alerta;
   	}
   
  	function validaTodosRadioButton(){
		
		var form = document.forms[0];
		var mensagem = "";
		
		if(validaRadioButtonTres(form.indicadorDocumentoEntregue,"Documento Entregue") != ""){
			mensagem = mensagem + validaRadioButtonTres(form.indicadorDocumentoEntregue,"Documento Entregue")+"\n";
		}
		
		if (form.indicadorGeracaoDebito != undefined){
			if(validaRadioButton(form.indicadorGeracaoDebito,"Gera��o do D�bito") != ""){
				mensagem = mensagem + validaRadioButton(form.indicadorGeracaoDebito,"Gera��o do D�bito")+"\n";
			}
		}
		
		
		if(mensagem != ""){
			alert(mensagem);
			return false;
		}else{
			return true;
		}
   	}
	 
	function limparForm() {
	
		var form = document.InformarRetornoOSFiscalizacaoActionForm;
	
		form.idOrdemServico.focus();
		form.idOrdemServico.value = "";
		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.ocorrencia.value = "";
		form.situacao.value = "";
		form.observacaoArquivo.value = "";

		form.indicadorDocumentoEntregue[0].checked = false;
		form.indicadorDocumentoEntregue[1].checked = false;
		form.indicadorDocumentoEntregue[2].checked = false;
		form.indicadorDocumentoEntregue[3].checked = false;
		
		if (form.indicadorGeracaoDebito != undefined){
			form.indicadorGeracaoDebito[0].checked = false;
			form.indicadorGeracaoDebito[1].checked = false;	
		}
		
		extendeTabela();
		
		form.action='exibirInformarRetornoOSFiscalizacaoAction.do?menu=sim';
      	form.submit();

	 }
	 
	 function limparDecricaoEfetuar() {
	
		var form = document.InformarRetornoOSFiscalizacaoActionForm;
	
		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.ocorrencia.value = "";
		
	 }
	 
	 
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   		var form = document.forms[0];

    	if (tipoConsulta == 'ordemServico') {

	    	form.idOrdemServico.value = codigoRegistro;
	      	form.action='exibirInformarRetornoOSFiscalizacaoAction.do';
	      	form.submit();

	    }else if (tipoConsulta == 'funcionario') {
	    
	      	form.idFuncionarioResponsavel.value = codigoRegistro;
	      	form.nomeFuncionarioResponsavel.value = descricaoRegistro;
	      	form.nomeFuncionarioResponsavel.style.color = "#000000";
	    }
	    
  	}
  	
  	
  	function exibirTipoMedicao(opcaoMedicao){
  		
  		var form = document.forms[0];

	  	if (opcaoMedicao == "1"){
	  	
		  	if(document.getElementById("tipoMedicao") != undefined){
		  		document.getElementById("tipoMedicao").style.display = 'block';
		  	}

	  	}else{
		  	if(document.getElementById("tipoMedicao") != undefined){
	  			document.getElementById("tipoMedicao").style.display = 'none';
	  		}
	  	}
	}
	
	
	function exibirGeracaoDebito(){
  		
  		var form = document.forms[0];
		var indicarGeracaoDebitoAutoInfracao = document.getElementById('indicarGeracaoDebitoAutoInfracao').value;
		var habilitaGeracaoDebito = document.getElementById('habilitaGeracaoDebito').value;

		if(habilitaGeracaoDebito == "1"){
		
	  		document.getElementById("geracaoDebito").style.display = 'block';
	  		
	  		if(indicarGeracaoDebitoAutoInfracao != undefined){
		  		if(indicarGeracaoDebitoAutoInfracao == "1"){
		  			form.indicadorGeracaoDebito[0].checked = true;
		  			form.indicadorGeracaoDebito[1].checked = false;
		  		}else if (indicarGeracaoDebitoAutoInfracao== "2") {
			  		form.indicadorGeracaoDebito[0].checked = false;
			  		form.indicadorGeracaoDebito[1].checked = true;
		  		}else{
			  		form.indicadorGeracaoDebito[0].checked = true;
			  		form.indicadorGeracaoDebito[1].checked = false;
		  		}
	  		}else{
		  		form.indicadorGeracaoDebito[0].checked = true;
		  		form.indicadorGeracaoDebito[1].checked = false;
	  		}
	  	}else{
	  		document.getElementById("geracaoDebito").style.display = 'none';
		  	
	  		form.indicadorGeracaoDebito[0].checked = false;
		  	form.indicadorGeracaoDebito[1].checked = true;
	  	}
	}
	
	function limparFuncionario(situacao){
	
		var form = document.forms[0];
		switch (situacao){
	       
	        case 1:
		       form.nomeFuncionarioResponsavel.value = "";
			   
			   break;
		  	case 2:
			   form.idFuncionarioResponsavel.value = "";
			   form.nomeFuncionarioResponsavel.value = "";
			   
			   form.idFuncionarioResponsavel.focus();
			   
			   break;
		  default:
	          break;
		}
	}

	function extendeTabela(){
		var form = document.forms[0];
		var icControlaAutosInfracao = document.getElementById('icControlaAutosInfracao').value;
		
		var indicadorDocumentoEntregue = valorRadioMarcado();
		
		var fiscalizacaoSituacao = form.situacao.options[form.situacao.selectedIndex];
		var indicadorAtualizacaoAutosInfracao = document.getElementById('indicadorAtualizacaoAutosInfracao').value;
		
		if(icControlaAutosInfracao == "1" && indicadorDocumentoEntregue == "2"){
			if (indicadorAtualizacaoAutosInfracao != "" && indicadorAtualizacaoAutosInfracao == "1"){
	 			document.getElementById("autoInfracao").style.display = 'block';
	 			form.nomeFuncionarioResponsavel.style.color = "#000000";
		 	}
		 	else{
		 		desabilitarAutoInfracao(form);
		 	}
		}
		else{
			desabilitarAutoInfracao(form);
		}
	}

function desabilitarAutoInfracao(form){
	document.getElementById("autoInfracao").style.display = 'none';
		
	form.nomeFuncionarioResponsavel.style.color = "#000000";
	    
	form.dataEmissao.value = "";
	if(form.dataInicioRecurso != undefined){
		form.dataInicioRecurso.value = "";
	}
	if(form.dataTerminoRecurso != undefined){
		form.dataTerminoRecurso.value = "";
	}
	form.observacao.value = "";
	    
	limitTextArea(document.forms[0].observacao, 100, document.getElementById('utilizado'), document.getElementById('limite'));
}

function validarAutoInfracao(form){
	
	var retorno = true;
	var msg = "";
	
	var icControlaAutosInfracao = document.getElementById('icControlaAutosInfracao').value;
	var indicadorDocumentoEntregue = form.indicadorDocumentoEntregue[1].checked;
	
	if(icControlaAutosInfracao == "1" && indicadorDocumentoEntregue){
	
		var idResponsavel = form.idFuncionarioResponsavel;
		var idSituacao = form.idAutoInfracaoSituacao;
		var dataInicioRecurso = form.dataInicioRecurso;
		var dataTerminoRecurso = form.dataTerminoRecurso;
		var qtdParcelas = form.quantidadeParcelas;
		
		if (idResponsavel.value.length < 1){
			msg = msg + "Informe Respons�vel \n";
			retorno = false;
		}
		
		//if (idSituacao.value == "-1"){
		//	msg = msg + "Informe Situa��o \n";
		//	retorno = false;
		//}
		
		if (qtdParcelas.value.length < 1){
			msg = msg + "Informe Quantidade de Parcelas D�bito \n";
			retorno = false;
		}
		
		if(form.dataInicioRecurso != undefined && form.dataTerminoRecurso  != undefined){
			if (dataInicioRecurso.value.length < 1 && dataTerminoRecurso.value.length > 0){
				msg = msg + "Informe Data In�cio Recurso \n";
				retorno = false;
			}
		}
		if(form.dataInicioRecurso != undefined && form.dataTerminoRecurso != undefined){
			if (dataInicioRecurso.value.length > 0 && dataTerminoRecurso.value.length < 1){
				msg = msg + "Informe Data T�rm. Recurso \n";
				retorno = false;
			}
		}
		if (msg != ""){
			alert(msg);
		}
	}
	
	return retorno;
}

	function valorRadioMarcado(){
	
		var form = document.forms[0];
		var retorno = "";
		
		if (form.indicadorDocumentoEntregue[0].checked){
			retorno = form.indicadorDocumentoEntregue[0].value;
		}
		else if (form.indicadorDocumentoEntregue[1].checked){
			retorno = form.indicadorDocumentoEntregue[1].value;
		}
		else{
			retorno = form.indicadorDocumentoEntregue[2].value;
		}
		
		return retorno;
	}

	//Chama Popup
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
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
	}
	
	function desabilitaCampos(desabilita){
		var form = document.forms[0];
		
		if(desabilita == 'autoInfracao'){

			form.indicadorDocumentoEntregue[0].disabled = false;
			form.indicadorDocumentoEntregue[1].disabled = true;
			form.indicadorDocumentoEntregue[2].disabled = false;
			form.indicadorDocumentoEntregue[0].checked = false;
			form.indicadorDocumentoEntregue[1].checked = false;
			form.indicadorDocumentoEntregue[2].checked = false;
		}else if(desabilita == 'outros'){

			form.indicadorDocumentoEntregue[0].disabled = false;
			form.indicadorDocumentoEntregue[1].disabled = false;
			form.indicadorDocumentoEntregue[2].disabled = false;
			form.indicadorDocumentoEntregue[2].checked = true;
			form.indicadorDocumentoEntregue[3].disabled = true;
			form.indicadorDocumentoEntregue[3].checked = false;
			extendeTabela();
		}else if(desabilita == 'notificacaoEsgoto'){
			form.indicadorDocumentoEntregue[0].disabled = true;
			form.indicadorDocumentoEntregue[1].disabled = true;
			form.indicadorDocumentoEntregue[2].disabled = true;
			form.indicadorDocumentoEntregue[3].checked = true;
			form.indicadorEncerramentoOS[0].checked = true;
			form.indicadorEncerramentoOS[0].disabled = true;
			form.indicadorEncerramentoOS[1].disabled = true;
		}else if(desabilita == 'apenasNotificacaoEsgoto'){
			form.indicadorDocumentoEntregue[3].checked = false;
			form.indicadorDocumentoEntregue[3].disabled = true;
		}else{
			form.indicadorDocumentoEntregue[0].disabled = false;
			form.indicadorDocumentoEntregue[1].disabled = false;
			form.indicadorDocumentoEntregue[2].disabled = false;
		}
		//Sempre vai desabilitar o campo de notifica��o de esgoto.
		//form.indicadorDocumentoEntregue[3].disabled = true;			
		
		desabilitaCombo(form.indicadorEncerramentoOS.value);
	
	}
	
	function reload(){
		var form = document.forms[0];
		form.action='exibirInformarRetornoOSFiscalizacaoAction.do?pesquisarFuncionario=OK';
		form.submit();
	
	}
	
	function adicionarSituacaoEncontrada (form){
		document.forms[0].target='';
	    form.action = "exibirInformarRetornoOSFiscalizacaoAction.do?adicionarSituacaoEncontrada=S";
	    submeterFormPadrao(form);
    } 
	
	function changeSituacaoLigacao(){
		var form = document.forms[0];
		var tabelaDisponivel = document.getElementById("tabelaDisponivel");
		if(form.situacaoLigacao != null && form.situacaoLigacao.value == 3){
			tabelaDisponivel.style.display = "block";
		}else{
			limparDadosLigacaoEsgoto(form);
			tabelaDisponivel.style.display = "none";						
		}
	}
	
	function limparDadosLigacaoEsgoto(form){
		form.dataLigacaoEsgoto.value = "";
		form.indicadorLigacaoEsgoto.value = "";
		form.diametroLigacaoEsgoto.value = -1;
		form.materialLigacaoEsgoto.value = -1;
		form.perfilLigacaoEsgoto.value = -1;
		form.percentualColeta.value = "";
	}
	
	function validarDadosLigacaoEsgoto(form){
		if(form.dataLigacaoEsgoto.value == "" || (!form.indicadorLigacaoEsgoto[0].checked && !form.indicadorLigacaoEsgoto[1].checked) ||
			form.diametroLigacaoEsgoto.value == -1 || form.materialLigacaoEsgoto.value == -1 ||
			form.perfilLigacaoEsgoto.value == -1 || form.percentualColeta.value == ""){
			alert('Informe dados liga��o esgoto.');
			return false;
		}else{
			if(!validaData(form.dataLigacaoEsgoto)){
				return false;
			}
			return true;
		}
	}
	

	function validaArquivo(){
		var form = document.forms[0];
		var erros = "";
		
		if(form.arquivoTipo.value=="-1" || form.arquivoTipo.value==""){
			erros = "Informe o Tipo da foto";
		}
		if(form.arquivo.value == "" ){
			erros += "\nInforme um Arquivo";
		}

		
		if(erros != "" ){
			alert(erros);
			return false;
		}else{
			form.action = "exibirInformarRetornoOSFiscalizacaoAction.do?adicionarArquivo=S";
			form.submit();
		}		
	}
	
	function remover(objeto){
		var form = document.forms[0];
		if (confirm ("Confirma remo��o?")) {
			redirecionarSubmit('exibirInformarRetornoOSFiscalizacaoAction.do?removerArquivo=S&idArquivoRemover='+objeto);
		}
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5" 
onload="setarFoco('${requestScope.nomeCampo}');desabilitaCampos('${requestScope.desabilita}');exibirGeracaoDebito();">

<div id="formDiv"><html:form action="/informarRetornoOSFiscalizacaoAction.do"
	name="InformarRetornoOSFiscalizacaoActionForm"
	type="gsan.gui.atendimentopublico.ordemservico.InformarRetornoOSFiscalizacaoActionForm"
	method="post" enctype="multipart/form-data">
	
	<INPUT TYPE="hidden" ID="icControlaAutosInfracao" value="${requestScope.icControlaAutosInfracao}">
	<INPUT TYPE="hidden" ID="indicarGeracaoDebitoAutoInfracao" value="${requestScope.indicarGeracaoDebitoAutoInfracao}">
	<INPUT TYPE="hidden" ID="indicadorAtualizacaoAutosInfracao" value="${requestScope.indicadorAtualizacaoAutosInfracao}">
	<INPUT TYPE="hidden" ID="habilitaGeracaoDebito" value="${requestScope.habilitaGeracaoDebito}">
	<INPUT TYPE="hidden" ID="bloqueiaMotivoEncerramento" value="${requestScope.bloqueiaMotivoEncerramento}">
	
	

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

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Informar Retorno Ordem de Fiscaliza��o</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr>
							<td height="10" colspan="2">Para informar o retorno da
							fiscaliza��o , informe os dados abaixo:</td>
						</tr>
						<tr>
							<td height="10"><strong>Ordem de Servi&ccedil;o:<strong><font
								color="#FF0000">*</font></strong></strong></td>
							<td>
							<html:text
								property="idOrdemServico" 
								size="10" 
								maxlength="9"
								onkeypress="validaEnterComMensagem(event, 'exibirInformarRetornoOSFiscalizacaoAction.do', 'idOrdemServico','Ordem de Servi�o');return isCampoNumerico(event);"
								onkeyup="javascript:limparDecricaoEfetuar()" /> 
								
								<a href="javascript:chamarPopup('exibirPesquisarOrdemServicoAction.do', 'ordemServico', null, null, 600, 500, '',document.forms[0].idOrdemServico);">
									
									<img src="/gsan/imagens/pesquisa.gif" 
										alt="Pesquisar" 
										border="0"
										height="21" 
										width="23"
										title="Pesquisar Ordem de Servi�o"></a> 
								
								<logic:present name="ordemServicoEncontrado">
									<logic:equal name="ordemServicoEncontrado" value="exception">
										<html:text property="nomeOrdemServico" size="45" maxlength="40"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:equal>
									<logic:notEqual name="ordemServicoEncontrado" value="exception">
										<html:text property="nomeOrdemServico" size="45" maxlength="40"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notEqual>
								</logic:present> 
								
								<logic:notPresent name="ordemServicoEncontrado">
									<logic:empty name="InformarRetornoOSFiscalizacaoActionForm"
										property="idOrdemServico">
										<html:text property="nomeOrdemServico" size="45" maxlength="40"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:empty>
									<logic:notEmpty name="InformarRetornoOSFiscalizacaoActionForm"
										property="idOrdemServico">
										<html:text property="nomeOrdemServico" size="45" maxlength="40"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notEmpty>
								</logic:notPresent> 
								
								<a href="javascript:limparForm()"> 
									<img border="0" 
										title="Apagar"
										src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
							</td>
						</tr>
						
						<tr bgcolor="#cbe5fe">
							<td align="center" colspan="2">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
									<div align="center"><strong>Dados do Im�vel </strong></div>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
										<tr>
											<td width="37%" height="10"><strong>Matr&iacute;cula do
											Im&oacute;vel:<strong></strong></strong></td>
											<td width="58%"><html:text property="matriculaImovel"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="15" maxlength="20" /> <html:text
												property="inscricaoImovel" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="21" maxlength="20" /></td>
										</tr>
										<tr>
											<td><strong> Cliente Usu&aacute;rio:</strong></td>
											<td><html:text property="clienteUsuario" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="50" maxlength="50" /></td>
										</tr>
										<tr>
											<td><strong>CPF ou CNPJ:</strong></td>
											<td><html:text property="cpfCnpjCliente" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="20" maxlength="20" /></td>
										</tr>
										<tr>
											<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o
											de &Aacute;gua:</strong></td>
											<td><html:text property="situacaoLigacaoAgua" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="20" maxlength="20" /></td>
										</tr>
										<tr>
											<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o
											de Esgoto:</strong></td>

											<td><html:text property="situacaoLigacaoEsgoto"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="20" maxlength="20" /></td>
										</tr>
										<tr>
											<td><strong>Ocorr�ncia:</strong></td>

											<td><html:text property="ocorrencia" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="35" maxlength="35" /></td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr bgcolor="#cbe5fe">
							<td align="center" colspan="2">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">

									<td height="18" colspan="2">
									<div align="center"><strong>Dados do Retorno</strong></div>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
										<tr>
											<td width="25%" class="style3"><strong>Situa��o Encontrada:<font
												color="#FF0000">*</font></strong></td>

											<td width="75%"><!-- O atributo id est� se referindo ao campo indicadorOpcaoMedicao que ser� utilizado para
								            obrigar ou n�o o usu�rio de informar o tipo de medi��o -->
											
											<bean:define name="InformarRetornoOSFiscalizacaoActionForm" property="situacao" id="situacao"/>
											
											<select name="situacao" tabindex="2" style="width: 360px;">
												<!-- onchange="exibirTipoMedicao(this.options[this.selectedIndex].id); exibirGeracaoDebito();extendeTabela();reload();"--> 
												<option id="" value="">&nbsp;</option>

												<logic:iterate name="colecaoFiscalizacaoSituacao"
													id="situacaoEncontradaHelper" type="SituacaoEncontradaHelper">
													<option <%=(situacao.toString().equalsIgnoreCase(((SituacaoEncontradaHelper)situacaoEncontradaHelper).getFiscalizacaoSituacao().getId().toString()))?" selected ": "" %>
														name="<%=""+ situacaoEncontradaHelper.getGeracaoDebito() %>"
														id="<%=""+ situacaoEncontradaHelper.getFiscalizacaoSituacao().getIndicadorOpcaoMedicao() %>"
														style="<%="padding-left: " + situacaoEncontradaHelper.getGeracaoDebito() + ";height: "+ situacaoEncontradaHelper.getFiscalizacaoSituacao().getIndicadorAtualizacaoAutosInfracao() %>"
														value="<%=""+ situacaoEncontradaHelper.getFiscalizacaoSituacao().getId() %>"><%=""
														+ situacaoEncontradaHelper.getFiscalizacaoSituacao().getDescricaoFiscalizacaoSituacao()%></option>
												</logic:iterate>

											</select></td>
											
											 <td align="right">
												  <input name="Button" type="button" class="bottonRightCol" value="Adicionar" align="right" 
												  onclick="adicionarSituacaoEncontrada(document.forms[0])"  />
											 </td>
										</tr>
										
										
									
										
										
										
										<%-- in�cio da tabela de Situa��o da Fiscaliza��o Selecionada--%>

							           	<%int cont5 = 0;%>
										<tr>
											<td colspan="4">
											<table width="100%" border="0" bgcolor="90c7fc">
												<logic:empty name="colecaoFiscalizacaoSituacaoSelecionada" scope="session">
													<tr bgcolor="#90c7fc">
														<td width="10%" align="center"><strong>Remover</strong></td>
														<td width="60%" align="center"><strong> Situa��o da Fiscaliza��o Selecionada</strong></td>
														<td width="30%" align="center"><strong>Data da Fiscaliza��o</strong></td>
													</tr>
												</logic:empty>
												
												<logic:notEmpty name="colecaoFiscalizacaoSituacaoSelecionada" scope="session">
													
													<%if (((Collection) session.getAttribute("colecaoFiscalizacaoSituacaoSelecionada")).size() <= 4) {%>
														<tr bgcolor="#90c7fc">
															<td width="10%" align="center"><strong>Remover</strong></td>
															<td width="60%" align="center"><strong> Situa��o da Fiscaliza��o Selecionada</strong></td>
															<td width="30%" align="center"><strong>Data da Fiscaliza��o</strong></td>
														</tr>
																				
														<logic:iterate name="colecaoFiscalizacaoSituacaoSelecionada" 
															id="helper"
															type="SituacaoEncontradaHelper">
															
															<%cont5 = cont5 + 1;
															if (cont5 % 2 == 0) {%>
															<tr bgcolor="#cbe5fe">
															<%} else {%>
															<tr bgcolor="#FFFFFF">
															<%}%>
						
																<td width="10%">
														            <div align="center"><font color="#333333"> <img width="14"
														             height="14" border="0"
														             src="<bean:message key="caminho.imagens"/>Error.gif"
															             onclick="javascript:document.forms[0].target='';if(confirm('Confirma remo��o?')){redirecionarSubmit('exibirInformarRetornoOSFiscalizacaoAction.do?removerSituacaoSelecionada=S&idFiscalizacaoSituacaoRemover=<bean:write name="helper" property="fiscalizacaoSituacao.id"/>');}"
															              />
														            </font></div>
														       </td>	
														       <td width="60%" align="center">
																	<bean:write name="helper" property="fiscalizacaoSituacao.descricaoFiscalizacaoSituacao" />
																</td>
																
																<td width="30%" align="center">
																	<bean:write name="helper" property="dataFiscalizacao" formatKey="date.format"/>
																</td>
													
															</tr>
														</logic:iterate>
														
													<%} else {%>
													
														<tr bgcolor="#90c7fc">
															<td width="10%" align="center"><strong>Remover</strong></td>
															<td width="60%" align="center"><strong> Situa��o da Fiscaliza��o Selecionada</strong></td>
															<td width="30%" align="center"><strong>Data da Fiscaliza��o</strong></td>
														</tr>
												
														<tr>
															<td height="100" colspan="6">
															<div style="width: 100%; height: 100%; overflow: auto;">
															<table width="100%">
																
																<logic:iterate name="colecaoFiscalizacaoSituacaoSelecionada" 
																id="helper" type="SituacaoEncontradaHelper">
															
																	<%cont5 = cont5 + 1;
																	if (cont5 % 2 == 0) {%>
																	<tr bgcolor="#cbe5fe">
																	<%} else {%>
																	<tr bgcolor="#FFFFFF">
																	<%}%>
								
																		<td width="10%">
																            <div align="center"><font color="#333333"> <img width="14"
																             height="14" border="0"
																             src="<bean:message key="caminho.imagens"/>Error.gif"
																	             onclick="javascript:document.forms[0].target='';if(confirm('Confirma remo��o?')){redirecionarSubmit('exibirInformarRetornoOSFiscalizacaoAction.do?removerSituacaoSelecionada=S&idFiscalizacaoSituacaoRemover=<bean:write name="helper" property="fiscalizacaoSituacao.id"/>');}"
																	              />
																            </font></div>
																       </td>	
																       <td width="60%" align="center">
																			<bean:write name="helper" property="fiscalizacaoSituacao.descricaoFiscalizacaoSituacao" />
																		</td>
																		
																		<td width="30%" align="center">
																			<bean:write name="helper" property="dataFiscalizacao" formatKey="date.format"/>
																		</td>
															
																	</tr>
																</logic:iterate>
																
															</table>
															</div>
															</td>
														</tr>
													<%}%>
												</logic:notEmpty>
											</table>
											</td>
										</tr>
							          		
							           	<%-- final da tabela de Situa��o da Fiscaliza��o Selecionada--%>	
																
										
										
										
										
										
										
										
										
										
										
										
										
										
									</table>

									<logic:present name="exibirAtualizarSituacaoLigacaoAguaEsgoto" scope="request">
										<logic:equal name="exibirAtualizarSituacaoLigacaoAguaEsgoto" value="1">
											<table border="0" width="100%">
												<tr>
													<td width="150"><strong>Atualizar Situa��o da Liga��o de �gua/Esgoto:</strong></td>
													<td><html:radio property="indicadorAtualizarSituacaoLigacaoAguaEsgoto" value="1" />	<strong> Sim </strong> 
														<html:radio	property="indicadorAtualizarSituacaoLigacaoAguaEsgoto" value="2" /> <strong> N�o</strong>
													</td>
												</tr>
											</table>
										</logic:equal>
									</logic:present>

									



									<!-- Caso a situa��o encontrada obrigue informar a situa��o de medi��o  -->

									<logic:equal name="habilitaTipoMedicao" value="1">
										<table border="0" width="100%">
											<tr>
												<td width="150"><strong>Tipo de Medi��o:</strong></td>
												<td>
													<html:radio property="indicadorTipoMedicao" value="1" /><strong> Liga��o de Agua </strong> 
													<html:radio	property="indicadorTipoMedicao" value="2" /> <strong> Po�o</strong>
												</td>
											</tr>
										</table>
									</logic:equal>

									<table border="0" width="100%">
										<tr>
											<td width="13%"><strong>Documento Entregue:<font
												color="#FF0000">*</font></strong></td>
											
											<logic:present name="icControlaAutosInfracao" scope="request">
									
												<logic:equal name="indicadorAtualizacaoAutosInfracao" value="1">
													
													<td>
														<html:radio property="indicadorDocumentoEntregue" value="1" disabled="true" onclick="extendeTabela();"/> <strong> Sol. Comparecimento </strong> 
														<html:radio	property="indicadorDocumentoEntregue" value="2" disabled="true" onclick="extendeTabela();"/> <strong> Auto de Infra��o</strong> 
														<html:radio property="indicadorDocumentoEntregue" value="4" disabled="true" onclick="extendeTabela();"/> <strong> Nenhum</strong>
														<html:radio property="indicadorDocumentoEntregue" value="3" onclick="extendeTabela();"/> <strong> Notifica��o de Esgoto</strong>
													</td>
												</logic:equal>
												
												<logic:notEqual name="indicadorAtualizacaoAutosInfracao" value="1">
													<td>
														<html:radio property="indicadorDocumentoEntregue" value="1" onclick="extendeTabela();"/> <strong> Sol. Comparecimento </strong> 
														<html:radio	property="indicadorDocumentoEntregue" value="2" onclick="extendeTabela();"/> <strong> Auto de Infra��o</strong> 
														<html:radio property="indicadorDocumentoEntregue" value="4" onclick="extendeTabela();"/> <strong> Nenhum</strong>
														<html:radio property="indicadorDocumentoEntregue" value="3" onclick="extendeTabela();"/> <strong> Notifica��o de Esgoto</strong>
													</td>
													
												</logic:notEqual>
											
											</logic:present>
											
											<logic:notPresent name="icControlaAutosInfracao" scope="request">
												<td>
													<logic:notPresent name="notificacaoEsgoto" scope="request">
														<html:radio property="indicadorDocumentoEntregue" value="1" onclick="extendeTabela();"/> <strong> Sol. Comparecimento </strong> 
														<html:radio	property="indicadorDocumentoEntregue" value="2" onclick="extendeTabela();"/> <strong> Auto de Infra��o</strong> 
														<html:radio property="indicadorDocumentoEntregue" value="4" onclick="extendeTabela();"/> <strong> Nenhum</strong>
														<html:radio property="indicadorDocumentoEntregue" value="3" onclick="extendeTabela();"/> <strong> Notifica��o de Esgoto</strong>
													</logic:notPresent>
													
													<logic:present name="notificacaoEsgoto" scope="request">
														<html:radio property="indicadorDocumentoEntregue" value="1" onclick="extendeTabela();"/> <strong> Sol. Comparecimento </strong>
														<html:radio property="indicadorDocumentoEntregue" value="2" onclick="extendeTabela();"/> <strong> Auto de Infra��o</strong> 
														<html:radio property="indicadorDocumentoEntregue" value="4" onclick="extendeTabela();"/> <strong> Nenhum</strong>
														<html:radio property="indicadorDocumentoEntregue" value="3" onclick="extendeTabela();"/> <strong> Notifica��o de Esgoto</strong>	
													</logic:present>													
												</td>
											
											</logic:notPresent>
											
										</tr>
									</table>
									
									




									<div id="geracaoDebito" style="display:block">
									
										<logic:present name="icControlaAutosInfracao" scope="request">
									     	<logic:equal name="indicarGeracaoDebitoAutoInfracao" value="1">
									 			<table border="0" width="100%">
													<tr>
														<td width="150"><strong> Gera��o do D�bito:<font
															color="#FF0000">*</font></strong></td>
														<td><html:radio property="indicadorGeracaoDebito" value="1" />
														<strong> Sim </strong> <html:radio
															property="indicadorGeracaoDebito" disabled="true" value="2" /> <strong>N�o</strong>
														</td>
													</tr>
												</table>
											</logic:equal>
											<logic:equal name="indicarGeracaoDebitoAutoInfracao" value="2">
									 			<table border="0" width="100%">
													<tr>
														<td width="150"><strong> Gera��o do D�bito:<font
															color="#FF0000">*</font></strong></td>
														<td><html:radio property="indicadorGeracaoDebito" value="1" disabled="true" />
														<strong> Sim </strong> <html:radio
															property="indicadorGeracaoDebito" value="2" /> <strong>N�o</strong>
														</td>
													</tr>
												</table>
											</logic:equal>
											<logic:notPresent name="indicarGeracaoDebitoAutoInfracao" scope="request">
												<logic:present name="disponibilizarNaoGeracaoDebito" scope="request">
												<table border="0" width="100%">
													<tr>
														<td width="150"><strong> Gera��o do D�bito:<font
															color="#FF0000">*</font></strong></td>
														<td><html:radio property="indicadorGeracaoDebito" value="1"/>
														<strong> Sim </strong> <html:radio
															property="indicadorGeracaoDebito" value="2" /> <strong>N�o</strong>
														</td>
													</tr>
												</table>
												</logic:present>
											</logic:notPresent>		
										</logic:present>
										<logic:notPresent name="icControlaAutosInfracao" scope="request">		
												<logic:present name="disponibilizarNaoGeracaoDebito" scope="request">	
													<table border="0" width="100%">
														<tr>
															<td width="150"><strong> Gera��o do D�bito:<font
																color="#FF0000">*</font></strong></td>
															<td><html:radio property="indicadorGeracaoDebito" value="1" />
															<strong> Sim </strong> <html:radio
																property="indicadorGeracaoDebito" value="2" /> <strong>N�o</strong>
															</td>
														</tr>
													</table>
												</logic:present>
										</logic:notPresent>									
									</div>

									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<logic:present name="notificacaoEsgoto" scope="request">
					<tr bgcolor="#cbe5fe">
						<td align="center" colspan="2">
						<table width="100%" border="0" bgcolor="#99CCFF">
							<tr bgcolor="#99CCFF">
								<td colspan="2">
									<div align="center">
										<strong>Dados da Liga��o do Esgoto</strong>
									</div>
								</td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td>
								<table border="0" width="100%">
									
									<tr>
										<td width="130px">
											<strong>Situa��o da Liga��o:<font color="#FF0000">*</font></strong>
										</td>
										<td>
											<html:select onchange="javascript:changeSituacaoLigacao();" property="situacaoLigacao">
												<html:option value="-1">&nbsp;</html:option>
												<html:option value="2">Fact�vel</html:option>
												<html:option value="3">Ligado</html:option>
												<html:option value="1">Potencial</html:option>
											</html:select> <font size="1">&nbsp; </font>
										</td>
									</tr>

									<tr>
										<td colspan="2">
									
									<table border="0" width="100%" style="display: none;" id="tabelaDisponivel">
									
									
										<tr>
											<td>
												<strong>Data da Liga��o:<font color="#FF0000">*</font></strong>
											</td>
											<td>
												<html:text property="dataLigacaoEsgoto" size="10" maxlength="10" 
													onkeypress="mascaraData(this, event); return isCampoNumerico(event);" />(dd/mm/aaaa) 
												
											</td>
										</tr>
										
										<tr>
											<td>
												<strong>Liga��o:<font color="#FF0000">*</font></strong>
											</td>
											<td>
												<html:radio property="indicadorLigacaoEsgoto" value="2" /><strong>Dispon�vel</strong> 
												<html:radio property="indicadorLigacaoEsgoto" value="1" /> <strong>Efetivado</strong>
											</td>
										</tr>
										
										<tr>
											<td>
												<strong>Di�metro da Liga��o:<font color="#FF0000">*</font></strong>
											</td>
											<td>
												<html:select property="diametroLigacaoEsgoto">
													<html:option value="-1">&nbsp;</html:option>
													<html:options collection="colecaoLigacaoEsgotoDiametro" labelProperty="descricao" property="id" />
												</html:select> <font size="1">&nbsp; </font>
											</td>
										</tr>
										
										<tr>
											<td>
												<strong>Material da Liga��o:<font color="#FF0000">*</font></strong>
											</td>
											<td>
												<html:select property="materialLigacaoEsgoto">
													<html:option value="-1">&nbsp;</html:option>
													<html:options collection="colecaoMaterialEsgoto" labelProperty="descricao" property="id" />
												</html:select> <font size="1">&nbsp; </font>
											</td>
										</tr>
										
										<tr>
											<td>
												<strong>Perfil da Liga��o:<font color="#FF0000">*</font></strong>
											</td>
											<td>
												<html:select property="perfilLigacaoEsgoto">
													<html:option value="-1">&nbsp;</html:option>
													<html:options collection="colecaoPerfilLigacaoEsgoto" labelProperty="descricao" property="id" />
												</html:select> <font size="1">&nbsp; </font>
											</td>
										</tr>
										
										<tr>
											<td>
												<strong>Percentual de Coleta:<font color="#FF0000">*</font></strong>
											</td>
											<td>
												<html:text property="percentualColeta" maxlength="6" size="6"
													onkeypress="return isCampoNumerico(event); formataValorMonetario(this, 6);" 
													onkeyup="formataValorMonetario(this, 6);"/>%
											</td>
										</tr>
										</table>
										</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</logic:present>				
				
				<tr>
					
					<td><div id="autoInfracao" style="display:none">
					<table width="100%" border="0" align="center">
						<tr bgcolor="#cbe5fe">
							<td align="center" colspan="2">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">

									<td height="18" colspan="2">
									<div align="center"><strong>Dados do Auto de Infra��o</strong></div>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td>
									
									<table border="0" width="100%">
										<tr>
											<td width="20%" class="style3"><strong>Respons�vel:<font
												color="#FF0000">*</font></strong></td>

											<td width="80%">
												
									        	<html:text property="idFuncionarioResponsavel" size="10" maxlength="9" onkeypress="limparFuncionario(1);validaEnterComMensagem(event, 'exibirInformarRetornoOSFiscalizacaoAction.do?pesquisarFuncionario=OK', 'idFuncionarioResponsavel', 'Funcion�rio Respons�vel'); return isCampoNumerico(event);"/>
												
												<a href="javascript:javascript:abrirPopup('exibirFuncionarioPesquisar.do', 330, 600);">
												<img width="23" height="21" border="0"
												src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												title="Pesquisar Funcion�rio" /></a>
												
												
												<logic:present name="corFuncionario">
													<logic:equal name="corFuncionario" value="exception">
														<html:text property="nomeFuncionarioResponsavel" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
													</logic:equal>
													<logic:notEqual name="corFuncionario" value="exception">
														<html:text property="nomeFuncionarioResponsavel" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
													</logic:notEqual>
												</logic:present>
										
												<logic:notPresent name="corFuncionario">
													<logic:empty name="InformarRetornoOSFiscalizacaoActionForm" property="idFuncionarioResponsavel">
														<html:text property="nomeFuncionarioResponsavel" size="45" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
													</logic:empty>
													<logic:notEmpty name="InformarRetornoOSFiscalizacaoActionForm" property="idFuncionarioResponsavel">
														<html:text property="nomeFuncionarioResponsavel" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
													</logic:notEmpty>
												</logic:notPresent>
														
												<a href="javascript:limparFuncionario(2);">
												<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
												border="0" title="Apagar" /></a> 
												
											</td>
										</tr>
									</table>
									
									<table border="0" width="100%">
										<tr>
											<td width="20%" class="style3"><strong>Situa��o:<font
												color="#FF0000">*</font></strong></td>
												
												<logic:equal name="existeDebitoACobrarAutoInfracao" value="1"> 
													<td width="80%">
														<html:select property="idAutoInfracaoSituacao" style="width: 250px;" disabled="true">
															<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
															
															<logic:present name="colecaoAutoInfracaoSituacao">
																<html:options collection="colecaoAutoInfracaoSituacao" labelProperty="descricao" property="id"/>
															</logic:present>
														
														</html:select>
													</td>
												</logic:equal>
												
												<logic:notEqual name="existeDebitoACobrarAutoInfracao" value="1"> 
													<td width="80%">
														<html:select property="idAutoInfracaoSituacao" style="width: 250px;" onchange="javascript:reload();">
															<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
															
															<logic:present name="colecaoAutoInfracaoSituacao">
																<html:options collection="colecaoAutoInfracaoSituacao" labelProperty="descricao" property="id"/>
															</logic:present>
														
														</html:select>
													</td>
												</logic:notEqual>
									
										</tr>
									</table>

									<table border="0" width="100%">
										<tr>
											<td width="20%" class="style3"><strong>Quantidade Parcelas D�bito:<font color="#FF0000">*</font></strong></td>												
											<logic:equal name="existeDebitoACobrarAutoInfracao" value="1"> 
												<td width="80%"><html:text property="quantidadeParcelas" size="7" maxlength="7" disabled="true" />
											</logic:equal>
											<logic:notEqual name="existeDebitoACobrarAutoInfracao" value="1"> 
												<td width="80%"><html:text property="quantidadeParcelas" size="7" maxlength="7" onkeypress="return isCampoNumerico(event);" />
											</logic:notEqual>
											
										</tr>
										<tr>
											<td width="20%" class="style3"><strong>Data de Emiss�o:<font color="#FF0000">*</font></strong></td>
											<td width="80%">
											
												<html:text property="dataEmissao" size="11" maxlength="10" tabindex="4" onkeyup="mascaraData(this, event);" onkeypress="return isCampoNumerico(event);"  />
												<a href="javascript:abrirCalendario('InformarRetornoOSFiscalizacaoActionForm', 'dataEmissao');">
												<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" alt="Exibir Calend�rio"/></a> (dd/mm/aaaa)
												
											</td>
										</tr>
									</table>
									
									<logic:present name="dataInicialRecurso" scope="session">
										<table border="0" width="100%">
											<tr>
												<td width="20%" class="style3"><strong>Data In�cio Recurso:</strong></td>
												<td width="80%">
												
													<html:text property="dataInicioRecurso" size="11" maxlength="10" tabindex="4" onkeyup="mascaraData(this, event);" onkeypress="return isCampoNumerico(event);" />
													<a href="javascript:abrirCalendario('InformarRetornoOSFiscalizacaoActionForm', 'dataInicioRecurso');">
													<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" alt="Exibir Calend�rio"/></a> (dd/mm/aaaa)
												</td>
											</tr>
										</table>
									</logic:present>
									
									<logic:present name="dataTerminoRecurso" scope="session">
										<table border="0" width="100%">
											<tr>
												<td width="20%" class="style3"><strong>Data T�rm. Recurso:</strong></td>
												<td width="80%">
												
													<html:text property="dataTerminoRecurso" size="11" maxlength="10" tabindex="4" onkeyup="mascaraData(this, event);" onkeypress="return isCampoNumerico(event);" />
													<a href="javascript:abrirCalendario('InformarRetornoOSFiscalizacaoActionForm', 'dataTerminoRecurso');">
													<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" alt="Exibir Calend�rio"/></a> (dd/mm/aaaa)
													
												</td>
											</tr>
										</table>
									</logic:present>
									
									<table border="0" width="100%">
										<tr>
											<td width="20%" class="style3"><strong>Observa��o:</strong></td>
											<td width="80%">
											
												<html:textarea property="observacao" cols="40" rows="4" onkeyup="limitTextArea(document.forms[0].observacao, 100, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
												<strong><span id="utilizado">0</span>/<span id="limite">100</span></strong>
												
											</td>
										</tr>
									</table>

									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table></div>
					</td>
				</tr>


				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr bgcolor="#cbe5fe">
							<td align="center" colspan="2">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">

									<td height="18" colspan="2">
									<div align="center"><strong>Dados Encerramento Ordem de Servi�o</strong></div>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

										<tr>
											<td width="25%" class="style3"><strong>Encerrar OS:<font
													color="#FF0000">*</font></strong></td>
											<td>
												<html:radio property="indicadorEncerramentoOS" 
													value="1" onclick="desabilitaCombo('1');" /> <strong>Sim</strong> 
												<html:radio property="indicadorEncerramentoOS" 
													value="2" onclick="desabilitaCombo('2');" /> <strong>N�o</strong>
											</td>
										</tr>

										<tr>
											<td><strong>Motivo Encerramento:</strong></td>
											<td><html:select property="atendimentoMotivoEncerramento">
												<html:option value="-1">&nbsp;</html:option>
												<html:options
													collection="colecaoAtendimentoMotivoEncerramento"
													labelProperty="descricao" property="id" />
											</html:select> <font size="1">&nbsp; </font></td>
										</tr>
										
										<tr>
											<td><strong>Parecer do Encerramento:</strong></td>
											<td>
												<html:textarea property="parecerEncerramento" 
													cols="50" 
													onkeyup="validarTamanhoMaximoTextArea(this,400);"/>
											</td>
										</tr>
									</table>

									<!-- Caso a situa��o encontrada obrigue informar a situa��o de medi��o  -->
								
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				
				
				
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr bgcolor="#cbe5fe">
							<td align="center" colspan="2">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">

									<td height="18" colspan="2">
									<div align="center"><strong>Cadastramento das fotos</strong></div>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

										<tr>
											<td colspan="2" width="25%" class="style3">Para anexar um ou v�rios arquivos, informe os dados abaixo:</td>
										</tr>

										<tr>
											<td><strong>Tipo da foto:</strong></td>
											<td><html:select property="arquivoTipo">
												<html:option value="-1">&nbsp;</html:option>
												<html:options
													collection="colecaoFotoTipo"
													labelProperty="descricao" property="id" />
											</html:select> <font size="1">&nbsp; </font></td>
										</tr>
										
										<tr>
											<td><strong>Arquivo:</strong></td>
											<td><input type="file" style="textbox" name="arquivo"
												size="20" /></td>
										</tr>
										
										<tr>
											<td><strong>Observa��o:</strong></td>
											<td>
												<html:textarea property="observacaoArquivo" 
													cols="50" 
													style="width: 395px; height: 112px;"
													onkeyup="validarTamanhoMaximoTextArea(this,200);"/>
											</td>
										</tr>
										
										<tr>
											<td width="183"></td>
											<td width="432" align="right"><input type="button" tabindex="6"
												class="bottonRightCol" value="Adicionar" name="botaoAdicionar"
												onclick="javascript:validaArquivo();" />
											
										</tr>
										
					<tr>
					<td colspan="3">
					<logic:present name="colecaoArquivos">
					<table width="100%" border="0">
						<tr>
							<logic:notEmpty name="colecaoArquivos">
								<td width="183"><strong>Arquivos Informados </strong></td>
								<td></td>
							</logic:notEmpty>
							
						</tr>
						<tr>
							<td colspan="2">
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td height="0">
										<logic:notEmpty name="colecaoArquivos">
										<table width="100%" bgcolor="#99CCFF">
											<!--header da tabela interna -->
											<tr bgcolor="#90c7fc">
												<td width="10%" align="center">
													<strong>Remover</strong>
												</td>
												<td width="10%" align="center">
													<strong>Arquivo</strong>
												</td>
												<td width="80%" align="center">
													<strong>Observa��o</strong>
												</td>
												
											</tr>
										</table>
										</logic:notEmpty>
									</td>
								</tr>
								<tr>
									<td>
										<div style="width: 100%; height: 100%; overflow: auto;">
										<table width="100%" align="center" bgcolor="#99CCFF">
											
												<%int cont = 0;%>
												<%--Campo que vai guardar o valor do telefone a ser removido--%>
												
												<logic:iterate name="colecaoArquivos" id="arquivo" scope="session">
													<input type="hidden" name="idRegistrosRemocao" value="" />
													<%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr bgcolor="#cbe5fe">
													<%} else {%>
													<tr bgcolor="#FFFFFF">
													<%}%>
														<td width="10%" align="center">
															<strong> 
															<img src="<bean:message key='caminho.imagens'/>Error.gif" title="Remover"
																width="14" height="14" style="cursor:pointer;" alt="Remover"
																onclick="javascript:remover('<%=cont%>');">
															</strong>
														</td>
														
														<td width="10%" align="center">
															<logic:equal name="arquivo" property="extensao" value="DOC">
																<img src="<bean:message key='caminho.imagens'/>DOC.gif" 
																width="25" height="25" style="cursor:pointer;" alt="DOC" title="DOC">
															</logic:equal>
															
															<logic:equal name="arquivo" property="extensao" value="JPG">
																<img src="<bean:message key='caminho.imagens'/>JPG.gif" 
																width="25" height="25" style="cursor:pointer;" alt="JPG" title="JPG">
															</logic:equal>
															
															<logic:equal name="arquivo" property="extensao" value="PDF">
																<img src="<bean:message key='caminho.imagens'/>PDF.gif" 
																width="25" height="25" style="cursor:pointer;" alt="PDF" title="PDF">
															</logic:equal>
															
														</td>
														<td width="80%" align="center">
															<bean:write	name="arquivo" property="descricao" />
														
														</td>
														
													</tr>
												</logic:iterate>
											
										</table>
										</div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</logic:present>
					</td>
				</tr>
										
									</table>

									<!-- Caso a situa��o encontrada obrigue informar a situa��o de medi��o  -->
								
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				




				<tr>
					<td colspan="2">
					<table width="100%">

						<tr>
							<td colspan="2"><input name="Button" type="button"
								class="bottonRightCol" value="Limpar" 
								onclick="window.location.href='/gsan/exibirInformarRetornoOSFiscalizacaoAction.do?menu=sim'">
								<input name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">				
								</td>
							<td align="right"><input name="Button" type="button"
								class="bottonRightCol" value="Atualizar" onclick="validaForm();">
							</td>
						</tr>
					</table>
					</td>

				</tr>
				<!--</tr></table></td>-->
			</table>
	</table>
	<!-- Fim do Corpo-->

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
<script language="JavaScript">

limitTextArea(document.forms[0].observacao, 100, document.getElementById('utilizado'), document.getElementById('limite'));
extendeTabela();
exibirTipoMedicao(document.forms[0].situacao.options[document.forms[0].situacao.selectedIndex].id); 
exibirGeracaoDebito();

</script>

</html:html>

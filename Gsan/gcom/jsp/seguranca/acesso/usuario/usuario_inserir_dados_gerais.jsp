<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page
	import="gcom.seguranca.acesso.usuario.UsuarioTipo,gcom.cadastro.empresa.Empresa"%>

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
	formName="InserirUsuarioDadosGeraisActionForm"
	dynamicJavascript="false" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
	//window.onmousemove = verificarAcesso;
	
	function limparLotacao() {
		 	document.forms[0].idLotacao.value = '';
		 	document.forms[0].nomeLotacao.value = '';
	}

	function limparFuncionario() {
		 	document.forms[0].idFuncionario.value = '';
		 	document.forms[0].nomeFuncionario.value = '';
		 	document.forms[0].idLotacao.value = '';
		 	document.forms[0].nomeLotacao.value = '';
		 	document.forms[0].nome.value = '';
		 	document.forms[0].login.value = '';
		 	document.forms[0].cpf.value = '';
		 	document.forms[0].dataNascimento.value = '';
		 	
		 	document.forms[0].indicadorUsuarioBatch.checked = false;
		 	document.forms[0].indicadorUsuarioUsuario.checked = false;
		 	
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		if ('funcionario' == tipoConsulta) {

			document.forms[0].idLotacao.value = '';
		 	document.forms[0].nomeLotacao.value = '';
		 	document.forms[0].nome.value = '';
		 	document.forms[0].nomeFuncionario.value = '';
		 	document.forms[0].idFuncionario.value = codigoRegistro;

		 	document.forms[0].action = 'exibirInserirUsuarioDadosGeraisAction.do';
		 	submeterFormPadrao(document.forms[0]);
		 	
	 	} else if ('unidadeOrganizacional' == tipoConsulta) { 
		 	
		 	document.forms[0].idLotacao.value = codigoRegistro;
		 	document.forms[0].nomeLotacao.value = descricaoRegistro;
		 	document.forms[0].action = 'exibirInserirUsuarioDadosGeraisAction.do';
		 	
		 	submeterFormPadrao(document.forms[0]);
	 	}
	}
	

	var bCancel = false; 

    function validateInserirUsuarioDadosGeraisActionForm(form) {

    	if (bCancel) {

      		return true; 
        } else {
	       		return validaFuncionario() &&
       			validateRequired(form) && 
       			validateLong(form) && 
       			validateCaracterEspecial(form) && 
       			validateDate(form) &&
       			validarIdadeUsuario() && 
       			validateEmail(form) && 
       			enviar(form.usuarioTipo.value) && 
       			testarCampoValorZero(form.idFuncionario, 'Matr�cula do Funcion�rio') && 
       			testarCampoValorZero(form.idLotacao, 'Unidade Organizacional') && 
       			validarEmail() &&
       			validateCpf(form);
       	}
   	}
   	
   	function validaFuncionario() {

   		var form = document.forms[0];
   		var retorno = true;

   		if(form.indicadorUsuarioInternet[0].checked == false && form.indicadorUsuarioBatch[0].checked == false && (form.cpf.value == null || form.cpf.value == '')){
   		 	alert("Informe o CPF");
   		 	retorno = false;
   		}

   		if(form.indicadorUsuarioInternet[0].checked == false && form.indicadorUsuarioBatch[0].checked == false && (form.dataNascimento.value == null || form.dataNascimento.value == '')){
   		 	alert("Informe a Data de Nascimento ");
   		 	retorno = false;
   		}
   		
   		if(form.indicadorUsuarioBatch[0].checked == false && form.indicadorUsuarioBatch[1].checked == false){
   			alert("Selecione o indicador da rotina batch");
   		 	retorno = false;
   		}
   		
   		if(form.indicadorUsuarioInternet[0].checked == false && form.indicadorUsuarioInternet[1].checked == false){
   			alert("Selecione o indicador da internet");
   		 	retorno = false;
   		}
   		
   		
   		return retorno;
   	}

	function required () { 
    	this.aa = new Array("usuarioTipo", "Informe Tipo de Usu�rio.", new Function ("varName", " return this[varName];"));
     	this.ab = new Array("empresa", "Informe Empresa.", new Function ("varName", " return this[varName];"));
     	this.ac = new Array("nome", "Informe Nome Usu�rio.", new Function ("varName", " return this[varName];"));
     	this.ae = new Array("login", "Informe Login.", new Function ("varName", " return this[varName];"));
     	this.af = new Array("email", "Informe E-mail.", new Function ("varName", " return this[varName];"));
     	this.ag = new Array("confirmacaoEmail","Informe Confirma��o E-Mail.", new Function ("varName", " return this[varName];"));
     	this.ah = new Array("idLotacao","Informe Unidade organizacional.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
    	this.aa = new Array("usuarioTipo", "Tipo de Usu�rio deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.ab = new Array("empresa", "Empresa deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.ac = new Array("idLotacao", "Unidade organizacional deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
    } 

    function caracteresespeciais () { 
     	this.aa = new Array("nome", "Nome possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ab = new Array("login", "Login possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function email () { 
     	this.aa = new Array("email", "E-mail � um endere�o inv�lido.", new Function ("varName", " return this[varName];"));
     	this.ab = new Array("confirmacaoEmail", "Confirma��o E-mail � um endere�o inv�lido.", new Function ("varName", " return this[varName];"));
    } 
    
    function DateValidations () { 
     	this.aa = new Array("dataInicial", "Data de Cadastramento Inicial n�o � uma data v�lida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     	this.aa = new Array("dataFinal", "Data de Cadastramento Inicial n�o � uma data v�lida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    } 
    
    function cpf () {
		this.aa = new Array("cpf", "CPF inv�lido.", new Function ("varName", " return this[varName];"));
	}

	function desfazer(){
    	document.forms[0].reset();
    	verificarAcesso();
	}

	function enviar(idTipoUsuario) {
   		var form = document.forms[0];
	   	var achou = true;
   		
   		if(trim(idTipoUsuario) == ''){
   			alert('Informe Tipo de Usu�rio.');
   			achou = false;
   		}else{
    		
    		if(idTipoUsuario != form.administrador.value){
	    	
	    		//caso o o indicador de funcion�rio seja 0(n�o � funcion�rio)
	    		if(form.indicadorFuncionario.value != form.indicadorFuncionarioSIM.value){
		  			if(trim(form.empresa.value) == ''){
		       			alert('Informe Empresa.');
			   			achou = false;
		  			}else{
		        		if (trim(form.idLotacao.value) == '') {
			       			alert('Informe Unidade Organizacional.');
			       			achou = false;
		        		}else{
		          			if(trim(form.dataInicial.value) == '' || trim(form.dataFinal.value) == '') {
			       				alert('Informe Per�odo de Cadastramento.');
			       				achou = false;
		          			}
		        		}
		  			}
		 //caso o tipo de usuario seja funcion�rio
				}else{
		 			if(form.indicadorFuncionario.value == form.indicadorFuncionarioSIM.value){
		   				if (trim(form.idFuncionario.value) == '') {
			 				alert('Informe Matr�cula Funcion�rio.');
			 				achou = false;
		   				}
		   				else if (trim(form.dataFinal.value) == '' && trim(form.dataInicial.value) != ''){
		   					alert('Informe Per�odo de Cadastramento Final.');
			       			achou = false;
		   				}
		   				else if (trim(form.dataInicial.value) == '' && trim(form.dataFinal.value) != ''){
		   					alert('Informe Per�odo de Cadastramento Inicial.');
			       			achou = false;
		   				}
		 			}
	   			}
			}
  		}
	
		return achou; 
	}

	function trocaTipUsuario() {
		var form = document.forms[0];
		var parmsUsuarioTipo = form.parmsUsuarioTipo.value.split(';');

		form.usuarioTipo.value = parmsUsuarioTipo[0];
		form.indicadorFuncionario.value = parmsUsuarioTipo[1];
		
		if(form.usuarioTipo.value != ''){
	 		form.nome.value = '';
		 	form.cpf.value = '';
		 	form.dataNascimento.value = '';
		 	form.idFuncionario.value = '';
		 	form.nomeFuncionario.value = '';
		 	form.idLotacao.value = '';
		 	form.dataInicial.value = '';
		 	form.dataFinal.value = '';
		 	form.empresa.value = '';
		 	form.nomeLotacao.value = '';
		 	form.login.value = '';
		 	form.email.value = '';
		 	form.confirmacaoEmail.value = '';
 		}
 		verificarAcesso();
	}

	function verificarAcesso(){
		var form = document.forms[0];
		var radioBatch = form.indicadorUsuarioBatch[0].checked;
		var radioInternet = form.indicadorUsuarioInternet[0].checked;
	
			if(form.usuarioTipo.value == form.administrador.value){
		    	
		    	form.nome.disabled = false;
		    	form.cpf.disabled = false;
		 		form.dataNascimento.disabled = false;
		    	form.idFuncionario.disabled = true;
		    	form.idLotacao.disabled = false;
		    	form.empresa.disabled = false;
		    	form.idFuncionario.value = '';
		    	form.nomeFuncionario.value = '';
		 	}else{
				if(form.indicadorFuncionario.value == form.indicadorFuncionarioSIM.value){
					form.nome.disabled = true;
		 		 	form.cpf.disabled = true;
		 		 	form.dataNascimento.disabled = true;
				 	form.idFuncionario.disabled = false;
				 	form.idLotacao.disabled = false;
				 	form.empresa.value = form.idEmpresaFuncionario.value;
				 	form.empresa.disabled = true;
			 	}else{
			   		if(form.indicadorFuncionario.value != form.indicadorFuncionarioSIM.value){
				    	
				    	form.nome.disabled = false;
				    	form.cpf.disabled = false;
		 				form.dataNascimento.disabled = false;
				    	form.idFuncionario.disabled = true;
				    	form.idLotacao.disabled = false;
				    	form.empresa.disabled = false;
				    	form.idFuncionario.value = '';
				    	form.nomeFuncionario.value = '';
			  		}
		 		}
		 	}
		
		
		if(radioBatch == true || radioInternet == true){
			form.cpf.value = '';
		    form.cpf.disabled = true;
		    form.dataNascimento.value = '';
		    form.dataNascimento.disabled = true;
			document.getElementById('dataNascimento').style.display = "none";
		}
	}

function chamarPopup(url,altura, largura,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			abrirPopup(url, altura, largura);
		}
			
   }
function limparCamposFuncionario(){
var form = document.forms[0];
 
  form.nomeFuncionario.value = '';
  form.nome.value = '';
  form.cpf.value = '';
  form.dataNascimento.value = '';
  form.idLotacao.value = '';
  form.nomeLotacao.value = '';
  form.dataInicial.value = '';
  form.dataFinal.value = '';
  form.login.value = '';
 form.email.value = '';
 form.confirmacaoEmail.value = '';
}
function limparNomeLotacao(){
var form = document.forms[0];
 if(form.idLotacao == ''){
  form.nomeLotacao.value = '';
 }
}

function limpaDataFinal(){
var form = document.forms[0];
  if(form.dataInicial.value == ''){
   form.dataFinal.value = '';
  }

}

function validarEmail(){
var form = document.forms[0];

	if(form.confirmacaoEmail.value == ''){
		alert('Informe Confirma��o Email');
	}else if(form.email.value != form.confirmacaoEmail.value){
		alert('Informa��es de email incompat�veis');
	}else{
		return true;
	}
}

function verificarData(){
	var form = document.forms[0];
	
	if(form.dataNascimento.disabled == false){
		
		abrirCalendario('InserirUsuarioDadosGeraisActionForm', 'dataNascimento');
	}
}

function verificarUsuarioTipo(event){
	var form = document.forms[0];

	if(form.parmsUsuarioTipo.value == "" && form.idFuncionario.value != null){
		alert("Selecione o Tipo de Usu�rio.");
		return false;
	} else {
		validaEnter(event, 'exibirInserirUsuarioDadosGeraisAction.do', 'idFuncionario');
	}
}

function validarIdadeUsuario(){
	
	var retorno = true;
	var form = document.forms[0];
		
	if (form.dataNascimento.disabled == false && form.dataNascimento.value.length > 0){
			
		var idadeMinimaFuncionario = document.getElementById("IDADE_MINIMA_FUNCIONARIO").value;
		var idadeMinimaUsuario = document.getElementById("IDADE_MINIMA_USUARIO").value;			
			
		var dataAtual = document.getElementById("DATA_ATUAL").value;
		var idadeUsuario = anosEntreDatas(form.dataNascimento.value, dataAtual);
			
		if (parseInt(idadeUsuario) < parseInt(idadeMinimaUsuario)){
				
			alert("O usu�rio ter� que possuir, no m�nimo, " + idadeMinimaUsuario + " anos de idade");
			form.dataNascimento.focus();
			retorno = false;
		}
		else if (parseInt(idadeUsuario) >= parseInt(idadeMinimaUsuario) &&
				parseInt(idadeUsuario) < parseInt(idadeMinimaFuncionario)){
			
			if(!confirm('Confirma inclus�o de usu�rio com idade inferior a 18 anos de idade?')){
				form.dataNascimento.focus();
				retorno = false;
			}
		}
	}
		
	return retorno;
}
function desabilitaCPFeNasc(){
	var form = document.forms[0];
	var radioBatch = form.indicadorUsuarioBatch[0].checked;
	var radioInternet = form.indicadorUsuarioInternet[0].checked;
	if(radioBatch == true || radioInternet == true){
		
		form.cpf.value = '';
	    form.cpf.disabled = true;
	    form.dataNascimento.value = '';
	    form.dataNascimento.disabled = true;
		document.getElementById('dataNascimento').style.display = "none";		
		
	}else{
		form.cpf.disabled = false;
	    form.dataNascimento.disabled = false;
	    document.getElementById('dataNascimento').style.display = "";	
	}
	
}
</script>
</head>

<body leftmargin="5" topmargin="5" onload="verificarAcesso();">
<html:form action="/inserirUsuarioWizardAction" method="post"
	onsubmit="return validateInserirUsuarioDadosGeraisActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=1" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	<input type="hidden" name="numeroPagina" value="1" />

	<INPUT TYPE="hidden" ID="DATA_ATUAL" value="${requestScope.dataAtual}" />
	<INPUT TYPE="hidden" ID="IDADE_MINIMA_USUARIO"
		value="${requestScope.idadeMinimaUsuario}" />
	<INPUT TYPE="hidden" ID="IDADE_MINIMA_FUNCIONARIO"
		value="${requestScope.idadeMinimaFuncionario}" />

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
			<td width="625" valign="top" class="centercoltext">
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
					<td class="parabg">Inserir Usu�rio - Dados Gerais</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table border="0" width="100%">
				<tr>
					<td colspan="2">Para adicionar o usu�rio, informe os dados abaixo:
					<input type="hidden" name="indicadorFuncionarioSIM"
						value="<%=UsuarioTipo.INDICADOR_FUNCIONARIO%>" /> <input
						type="hidden" name="administrador"
						value="<%=UsuarioTipo.USUARIO_TIPO_ADMINISTRADOR%>" /> <input
						type="hidden" name="idEmpresaFuncionario"
						value="<%=Empresa.INDICADOR_EMPRESA_PRINCIPAL%>" /> <html:hidden
						property="indicadorFuncionario" /> <html:hidden
						property="usuarioTipo" /></td>
				</tr>
				
				
				<tr>
					<td width="26%"><strong>Tipo de Usu�rio:<font color="#ff0000">*</font></strong></td>
					<td width="74%"><html:select property="parmsUsuarioTipo"
						onchange="javascript:trocaTipUsuario()">

						<bean:define name="InserirUsuarioDadosGeraisActionForm"
							property="parmsUsuarioTipo" id="parmsUsu" />

						<option value=""></option>

						<logic:notEmpty name="collUsuarioTipo">
							<logic:iterate name="collUsuarioTipo" id="usuarioTipo">
								<option
									<%=(parmsUsu.toString().equalsIgnoreCase(((UsuarioTipo)usuarioTipo).getId() + ";" + ((UsuarioTipo)usuarioTipo).getIndicadorFuncionario()))?" selected ": "" %>
									value="<bean:write name="usuarioTipo" property="id"/>;<bean:write name="usuarioTipo" property="indicadorFuncionario"/>">
								<bean:write name="usuarioTipo" property="descricao" /></option>
							</logic:iterate>
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td width="26%"><strong>Empresa:<font color="#ff0000">*</font></strong></td>
					<td width="74%"><html:select property="empresa">
						<option value=""></option>
						<html:options name="request" collection="collEmpresa"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td width="26%"><strong>Matr�cula do Funcion�rio:</strong></td>
					<td width="74%"><html:text maxlength="9" property="idFuncionario"
						size="9"
						onkeypress="javascript:document.forms[0].idLotacao.value='';return verificarUsuarioTipo(event);"
						onkeyup="limparCamposFuncionario();" /> <a
						href="javascript:chamarPopup('exibirFuncionarioPesquisar.do?limpaForm=S', 495, 300,document.forms[0].idFuncionario);"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" border="0"></a> <logic:equal
						property="funcionarioNaoEncontrada"
						name="InserirUsuarioDadosGeraisActionForm" value="true">
						<html:text property="nomeFuncionario" size="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:equal> <logic:equal property="funcionarioNaoEncontrada"
						name="InserirUsuarioDadosGeraisActionForm" value="false">
						<html:text property="nomeFuncionario" size="40" maxlength="40"
							readonly="true"
							style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
					</logic:equal> <a href="javascript:limparFuncionario();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23"> </a></td>
				</tr>
				<tr>
					<td width="26%"><strong>Nome do Usu�rio:<font color="#ff0000">*</font></strong></td>
					<td width="74%"><html:text property="nome" size="50" maxlength="50" /></td>
				</tr>
				<tr>
					<td width="26%"><strong>N�mero do CPF:<font color="#ff0000">*</font></strong></td>
					<td width="74%"><html:text property="cpf" size="12" maxlength="11" /></td>
				</tr>
				<tr>
					<td height="10"><strong>Data de Nascimento:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="dataNascimento" size="11" maxlength="10"
						tabindex="4" onkeyup="mascaraData(this, event);" /> <a
						id="dataNascimento" href="javascript:verificarData()"> <img
						border="0"
						src="<bean:message 
							key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calend�rio" /></a>
					dd/mm/aaaa</td>
				</tr>
				<tr>
					<td width="26%"><strong>Unidade Organizacional:<font color="#ff0000">*</font></strong></td>
					<td width="74%"><html:text maxlength="9" property="idLotacao"
						size="9"
						onkeypress="javascript:return validaEnterSemUpperCase(event, 'exibirInserirUsuarioDadosGeraisAction.do', 'idLotacao'); "
						onkeyup="limparNomeLotacao()" /> <a
						href="javascript:chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do?limpaForm=S', 495, 300,document.forms[0].idLotacao);"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" border="0"></a> <logic:equal
						property="lotacaoNaoEncontrada"
						name="InserirUsuarioDadosGeraisActionForm" value="false">
						<html:text name="InserirUsuarioDadosGeraisActionForm"
							property="nomeLotacao" size="40" maxlength="40" readonly="true"
							style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
					</logic:equal> <logic:equal property="lotacaoNaoEncontrada"
						name="InserirUsuarioDadosGeraisActionForm" value="true">
						<html:text name="InserirUsuarioDadosGeraisActionForm"
							property="nomeLotacao" size="40" maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:equal> <a href="javascript:limparLotacao();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23"> </a></td>
				</tr>

				<tr>
					<td width="26%"><strong>Per�odo de Cadastramento:<font
						color="#ff0000">*</font></strong></td>
					<td width="74%"><html:text
						onkeyup="mascaraData(this, event);limpaDataFinal();"
						property="dataInicial" size="10" maxlength="10" /> <a
						href="javascript:abrirCalendario('InserirUsuarioDadosGeraisActionForm', 'dataInicial')">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calend�rio" /></a>

					&nbsp; <html:text onkeyup="mascaraData(this, event);"
						property="dataFinal" size="10" maxlength="10" /> <a
						href="javascript:abrirCalendario('InserirUsuarioDadosGeraisActionForm', 'dataFinal')"><img
						border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calend�rio" /></a>
					dd/mm/aaaa</td>
				</tr>

				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="26%"><strong>Login:<font color="#ff0000">*</font></strong></td>
					<td width="74%"><html:text property="login" size="11"
						maxlength="11" style="text-transform: none;" />(matr�cula do
					funcion�rio ou cpf do prestador de servi�o)</td>
				</tr>
				<tr>
					<td width="26%"><strong>E-Mail:<font color="#ff0000">*</font></strong></td>
					<td width="74%"><html:text property="email" size="40"
						maxlength="40" style="text-transform: none;" /></td>
				</tr>
				<tr>
					<td width="26%"><strong>Confirma��o E-Mail:<font color="#ff0000">*</font></strong></td>
					<td width="74%"><html:text property="confirmacaoEmail" size="40"
						maxlength="40" style="text-transform: none;" /></td>
				</tr>

				<tr>
					<td width="26%"><strong>Indicador de usu�rio para rotina batch:<font
						color="#ff0000">*</font></strong></td>
					<td width="74%">Sim <html:radio property="indicadorUsuarioBatch"
						value="1" onchange="javascript:desabilitaCPFeNasc();" /> N�o <html:radio
						property="indicadorUsuarioBatch" value="2"
						onchange="javascript:desabilitaCPFeNasc();" /></td>
				</tr>

				<tr>
					<td width="26%"><strong>Indicador de usu�rio para internet:<font
						color="#ff0000">*</font></strong></td>
					<td width="74%">Sim <html:radio property="indicadorUsuarioInternet"
						value="1" onchange="javascript:desabilitaCPFeNasc();" /> N�o <html:radio
						property="indicadorUsuarioInternet" value="2"
						onchange="javascript:desabilitaCPFeNasc();" /></td>
				</tr>

				<tr>
					<td colspan="2">
					<table border="0" width="100%">
						<tr>
							<td>
							<div align="right"><jsp:include
								page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=1" /></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>

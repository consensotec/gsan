<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GCOM - Sistema de Gest&atilde;o Comercial</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gsan.util.ConstantesSistema"%>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="ExibirInserirSolicitacaoAcessoActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
<script language="JavaScript">
$(document).ready(function(){
	verificarAcessoAbrangencia();
	var form = document.forms[0];
	if (form.icSuperintendente.value==1){
		form.idFuncionarioSuperior.disabled = true;
	}
});
function limpaCampoPesquisa(campoDescricao){
	 campoDescricao.value = '';
}
function verificarAcessoAbrangencia(){
	var form = document.forms[0];
	var select = form.abrangencia.selectedIndex;
	var codigo = form.abrangencia[select].value;
	
	if (codigo==2){
		form.gerenciaRegional.disabled = false;
		form.unidadeNegocio.disabled = true;
		form.unidadeNegocio.value='';
		form.idElo.disabled = true;
		form.idElo.value='';
		form.nomeElo.value='';
		form.idLocalidade.disabled = true;
		form.idLocalidade.value='';
		form.nomeLocalidade.value='';
		limparElo();
	} else if (codigo==3){
		form.gerenciaRegional.disabled = true;
		form.gerenciaRegional.value='';
		form.unidadeNegocio.disabled = true;
		form.unidadeNegocio.value='';
		form.idElo.disabled = false;
		form.idLocalidade.disabled = true;
		form.idLocalidade.value='';
		form.nomeLocalidade.value='';
	} else if (codigo==4){
		form.gerenciaRegional.disabled = true;
		form.gerenciaRegional.value='';
		form.unidadeNegocio.disabled = true;
		form.unidadeNegocio.value='';
		form.idElo.disabled = true;
		form.idElo.value='';
		form.nomeElo.value='';
		form.idLocalidade.disabled = false;
		limparElo();
	} else if (codigo==5){
		form.gerenciaRegional.disabled = true;
		form.gerenciaRegional.value='';
		form.unidadeNegocio.disabled = false;
		form.idElo.disabled = true;
		form.idElo.value='';
		form.nomeElo.value='';
		form.idLocalidade.disabled = true;
		form.idLocalidade.value='';
		form.nomeLocalidade.value='';
		limparElo();
	} else {
		form.gerenciaRegional.disabled = true;
		form.gerenciaRegional.value='';
		form.unidadeNegocio.disabled = true;
		form.unidadeNegocio.value='';
		form.idElo.disabled = true;
		form.idElo.value='';
		form.nomeElo.value='';
		form.idLocalidade.disabled = true;
		form.idLocalidade.value='';
		form.nomeLocalidade.value='';
		limparElo();
	}
}
function limparLocalidade() {
 	document.forms[0].idLocalidade.value = '';
 	document.forms[0].nomeLocalidade.value = '';
}

function validarForm() {
		var form = document.forms[0];
		
		if (validateExibirInserirSolicitacaoAcessoActionForm(form)) {

			if (form.dataInicial.value != '' && form.dataFinal.value != '') {

				if (validaData(form.dataInicial) && validaData(form.dataFinal)) {

					if (comparaData(form.dataInicial.value, "<",
							form.dataFinal.value)
							|| comparaData(form.dataInicial.value, "=",
									form.dataInicial.value)) {

						if (validarIdadeUsuario() && validarAcessos()) {
							if (verificarUsuarioTipo()) {
								if (form.idsGrupo.selectedIndex > 0) {
									submeterFormPadrao(form);
								} else {
									alert('Informe o Grupo.');
								}
							}
						}
					} else {
						alert('Data final do período é anterior à data inicial.');
					}
				}
			}
		}
	}

function validarAcessos(){
	var form = document.forms[0];
	var select = form.abrangencia.selectedIndex;
	var codigo = form.abrangencia[select].value;
	
	if (codigo==2 && form.gerenciaRegional.selectedIndex ==0){
		alert('Informe Gerencial Regional.');
		return false;
	} else if (codigo==3 && form.idElo.value==''){
		alert('Informe Elo.');
		return false;
	} else if (codigo==4 && form.idLocalidade.value==''){
		alert('Informe Localidade.');
		return false;
	} else if (codigo==5 && form.unidadeNegocio.selectedIndex ==0){
		alert('Informe Unidade de Negócio.');
		return false;
	} else if (codigo==0){
		alert('Informe Abrangência de Acesso.');
		return false;
	}
	if (verificarEmail()){
		return true;
	} else {
		return false;
	}
}
function verificarEmail(){
	var form = document.forms[0];
	if(form.idTipoUsuario.value==2){
		var mailSplip1 = form.email.value.split('@');
		var mailSplip2 = mailSplip1[1].split('.');
		var mail = "@"+mailSplip2[0];
		if (form.dominioEmail.value==mail || form.dominioEmail.value==mail.toUpperCase()){
			return true;
		} else {
			alert('E-mail inválido. Necessário informar o e-mail corporativo da empresa.');
			return false;			
		}
	} else {
		return true;
	}
}
	function limparPesquisaFuncionario(tipo) {
		var form = document.forms[0];

		switch (tipo) {
		case 1: // Funcionario Superior

			form.idFuncionarioSuperior.value = "";
			form.nomeFuncionarioSuperior.value = "";
			break;

		case 2: // Funcionario

			form.idFuncionario.value = "";
			form.nomeFuncionario.value = "";
			break;
		}
	}

	function limparLotacao() {
		var form = document.forms[0];

		form.idLotacao.value = "";
		form.nomeLotacao.value = "";
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro,
			tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'funcionario') {

			form.idFuncionario.value = codigoRegistro;
			form.nomeFuncionario.value = descricaoRegistro;
			form.nomeFuncionario.style.color = "#000000";
			form.action = 'exibirInserirSolicitacaoAcessoAction.do';
			form.submit();
		} else if ('unidadeOrganizacional' == tipoConsulta) {

			form.idLotacao.value = codigoRegistro;
			form.nomeLotacao.value = descricaoRegistro;
			form.action = 'exibirInserirSolicitacaoAcessoAction.do';
			form.submit();
		} else if (tipoConsulta == 'idFuncionarioSuperior') {

			form.idFuncionarioSuperior.value = codigoRegistro;
			form.nomeFuncionarioSuperior.value = descricaoRegistro;
			form.nomeFuncionarioSuperior.style.color = "#000000";
			form.action = 'exibirInserirSolicitacaoAcessoAction.do';
			form.submit();
		} else if ('unidadeEmpresa' == tipoConsulta) { 
		 	document.forms[0].idLotacao.value = codigoRegistro;
		 	document.forms[0].action = 'exibirFiltrarUsuarioAction.do';
		 	submeterFormPadrao(document.forms[0]);
	 	} else if ('elo' == tipoConsulta) {
		 	document.forms[0].idElo.value = codigoRegistro;
		 	document.forms[0].nomeElo.value = descricaoRegistro;
	 	} else if ('localidadeElo' == tipoConsulta) { 
		 	document.forms[0].idElo.value = codigoRegistro;
		 	document.forms[0].nomeElo.value = descricaoRegistro;
	 	} else if ('localidadeDestino' == tipoConsulta) { 
		 	document.forms[0].idLocalidade.value = codigoRegistro;
		 	document.forms[0].nomeLocalidade.value = descricaoRegistro;
	 	}
	}

	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg, campo) {
		if (!campo.disabled) {
			if (objeto == null || codigoObjeto == null) {
				if (tipo == "") {
					abrirPopup(url, altura, largura);
				} else {
					abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
				}
			} else {
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
					alert(msg);
				} else {
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "="
							+ codigoObjeto, altura, largura);
				}
			}
		}
	}
	function chamarPopupElo(url, tipo,altura, largura, objetoRelacionado,nomeDependencia,valorDependencia){
		if(objetoRelacionado.disabled != true){
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + nomeDependencia + "=" + valorDependencia, altura, largura);
		}
			
	}

	function limpaDataFinal() {
		var form = document.forms[0];

		if (form.dataInicial.value == '') {
			form.dataFinal.value = '';
		}
	}

	function validaFuncionario() {

		var form = document.forms[0];
		var retorno = true;

		if (form.cpf.value == null || form.cpf.value == '') {
			alert("Informe o CPF");
			retorno = false;
		}

		if (form.dataNascimento.value == null
				|| form.dataNascimento.value == '') {
			alert("Informe a Data de Nascimento ");
			retorno = false;
		}

		return retorno;
	}

	function desabilitaCampos() {
		var form = document.forms[0];

		var tipoUsuario = returnObject(form, "idTipoUsuario");
		var indicadorPrestadorServico = tipoUsuario.options[tipoUsuario.options.selectedIndex].value;

		if (indicadorPrestadorServico.length > 0
				&& indicadorPrestadorServico == '8') {

			form.idFuncionario.readonly = true;
			form.nomeFuncionario.disabled = true;
			form.idFuncionario.value = '';
			form.nomeFuncionario.value = '';
			
			form.idEmpresa.disabled = false;
			form.nomeUsuario.disabled = false;
		} else if (indicadorPrestadorServico > 0) {
			form.idFuncionario.readonly = false;
			form.idEmpresa.disabled = true;
			form.idEmpresa.value = '-1';
			form.nomeUsuario.disabled = true;
			form.nomeUsuario.value='';
		} else {
			form.idFuncionario.readonly = false;
			form.idEmpresa.disabled = false;
			form.nomeUsuario.disabled = false;
			form.nomeUsuario.value = '';
		}
		
	}

	function limparCampos(){
		var form = document.forms[0];
		
		form.idFuncionario.value = '';
		form.nomeFuncionario.value = '';
		
		form.idLotacao.value = '';
		form.nomeLotacao.value = '';
		form.login.value = '';	
		form.idEmpresa.value = '-1';
		form.dataInicial.value = '';
		form.dataFinal.value = '';
		form.email.value = '';
		
	}
	function verificarUsuarioTipo() {
		var form = document.forms[0];
		var retorno = true;

		var tipoUsuario = returnObject(form, "idTipoUsuario");
		var indicadorPrestadorServico = tipoUsuario.options[tipoUsuario.options.selectedIndex].value;

		var empresa = returnObject(form, "idEmpresa");
		var indicadorEmpresa = empresa.options[empresa.options.selectedIndex].value;

		if (indicadorPrestadorServico.length > 0
				&& indicadorPrestadorServico == '8') {

			if (indicadorEmpresa < 0 || indicadorEmpresa == '') {

				alert("Informe a Empresa.");
				form.idEmpresa.focus();
				retorno = false;
			}

			if (form.nomeUsuario.value == '') {

				alert("Informe o Nome do Usuário.");
				form.nomeUsuario.focus();
				retorno = false;
			}
		} else if (indicadorPrestadorServico > 0) {

			if (form.idFuncionario.value == '') {

				alert("Informe a Matrícula do Funcionário.");
				form.idFuncionario.focus();
				retorno = false;
			}
		} else {

			alert("Informe o Tipo de Usuário.");
			form.idTipoUsuario.focus();
			retorno = false;
		}

		return retorno;
	}

	function replicarLogin() {
		var form = document.forms[0];

		var tipoUsuario = returnObject(form, "idTipoUsuario");
		var indicadorPrestadorServico = tipoUsuario.options[tipoUsuario.options.selectedIndex].value;

		if (indicadorPrestadorServico.length > 0
				&& indicadorPrestadorServico == '8') {
			form.login.value = form.cpf.value;
		} else {
			form.login.value = form.idFuncionario.value;
		}
	}

	function validarIdadeUsuario() {
		var form = document.forms[0];
		var retorno = true;

		if (form.dataNascimento.disabled == false
				&& form.dataNascimento.value.length > 0) {

			var dataAtual = document.getElementById("DATA_ATUAL").value;
			var idadeMinimaUsuario = document
					.getElementById("IDADE_MINIMA_USUARIO").value;

			var idadeUsuario = anosEntreDatas(form.dataNascimento.value,
					dataAtual);

			if (parseInt(idadeUsuario) < parseInt(idadeMinimaUsuario)) {

				alert("O usuário terá que possuir, no mínimo, 18 anos de idade.");
				form.dataNascimento.focus();
				retorno = false;
			}
		}

		return retorno;
	}

	function reloadTipoUsuario() {
		limparCampos();
		var form = document.forms[0];
		if (form.idTipoUsuario != null || form.idTipoUsuario.value != '') {

			form.action = "/gsan/exibirInserirSolicitacaoAcessoAction.do?usuarioTipo="
					+ form.idTipoUsuario.value;
			form.submit();
		}
	}

	function preencherDadosFuncionario() {
		var form = document.forms[0];

		if (form.idFuncionario.value != '') {

			if (form.login.value == '') {
				form.login.value = form.idFuncionario.value;
			}
		}
}
function limparElo() {
	 	document.forms[0].idElo.value = '';
	 	document.forms[0].nomeElo.value = '';
}
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="desabilitaCampos();preencherDadosFuncionario();">

	<div id="formDiv">
		<html:form action="/inserirSolicitacaoAcessoAction.do"
			name="ExibirInserirSolicitacaoAcessoActionForm"
			type="gsan.gui.seguranca.acesso.usuario.ExibirInserirSolicitacaoAcessoActionForm"
			method="post">

			<%@ include file="/jsp/util/cabecalho.jsp"%>
			<%@ include file="/jsp/util/menu.jsp"%>

			<INPUT TYPE="hidden" ID="DATA_ATUAL"
				value="${requestScope.dataAtual}" />
			<INPUT TYPE="hidden" ID="IDADE_MINIMA_USUARIO"
				value="${requestScope.idadeMinimaUsuario}" />
			<html:hidden property="icSuperintendente" />

			<html:hidden property="dominioEmail" />
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
									src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
								</td>
								<td class="parabg">Inserir Solicitação de Acesso</td>
								<td width="11"><img border="0"
									src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
						</table>
						<p>&nbsp;</p>
						<table width="100%" border="0">

							<tr>
								<td colspan="5">Funcionário Solicitante</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Matrícula do
										Funcionário:<font color="#FF0000">*</font> </strong>
								</td>

								<td width="74%" height="24" colspan="3"><html:text
										maxlength="8" property="idFuncionarioSolicitante" size="8"
										tabindex="1"
										style="background-color:#EFEFEF; border:0; color: #000000" />

									<html:text property="nomeFuncionarioSolicitante" size="40"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</td>
							</tr>

							<tr>
								<td colspan="5"><hr></td>
							</tr>

							<tr>
								<td colspan="5">Responsável pela Autorização - Superior
									Hierárquico</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Matrícula do
										Funcionário:<font color="#FF0000">*</font> </strong>
								</td>
								<td width="74%" height="24" colspan="3"><html:text
										maxlength="8" property="idFuncionarioSuperior" size="8"
										tabindex="1"
										onkeypress="javascript:validaEnter(event, 'exibirInserirSolicitacaoAcessoAction.do?objetoConsulta=1', 'idFuncionarioSuperior'); return isCampoNumerico(event);" />
									<a
									href="javascript:chamarPopup('exibirFuncionarioPesquisar.do', 'idFuncionarioSuperior', null, null, 495, 300, '',document.forms[0].idFuncionarioSuperior);">
										<img border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Funcionário" /> </a> <logic:present
										name="funcionarioInexistente1" scope="request">
										<input type="text" name="nomeFuncionarioSuperior" size="40"
											readonly="true"
											style="background-color: #EFEFEF; border: 0; color: #ff0000"
											value="<bean:message key="pesquisa.funcionario.inexistente"/>" />
									</logic:present> <logic:notPresent name="funcionarioInexistente1"
										scope="request">
										<html:text property="nomeFuncionarioSuperior" size="40"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notPresent> <a
									href="javascript:limparPesquisaFuncionario(1);document.forms[0].idFuncionarioSuperior.focus();">
										<img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /> </a>
								</td>
							</tr>

							<tr>
								<td colspan="2"><strong>Notificar Responsável por
										E-mail:<font color="#FF0000"></font> </strong></td>
								<td colspan="3"><span class="style2"> <strong>
											<html:radio property="icNotificar" value="0" onclick="" />
											Sim <html:radio property="icNotificar" value="1" onclick="" />
											Não </strong> </span>
								</td>
							</tr>

							<tr>
								<td colspan="5"><hr></td>
							</tr>

							<tr>
								<td colspan="5">Dados do Funcionário</td>
							</tr>

							<tr>
								<td width="22%" colspan="2"><strong>Tipo de
										Usuário:<font color="#FF0000">*</font> </strong></td>
								<td colspan="3"><html:select property="idTipoUsuario"
										style="width: 230px;" size="1" tabindex="1"
										onchange="desabilitaCampos();reloadTipoUsuario();">

										<logic:notEmpty name="colecaoUsuarioTipo" scope="session">
											<html:option
												value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<html:options collection="colecaoUsuarioTipo"
												labelProperty="descricao" property="id" />
										</logic:notEmpty>

										<font size="1">&nbsp; </font>
									</html:select>
								</td>
							</tr>

							<tr>
								<td width="22%" colspan="2"><strong>Empresa:<font
										color="#FF0000"></font> </strong></td>
								<td colspan="3"><html:select property="idEmpresa"
										style="width: 230px;" size="1" tabindex="1">

										<logic:notEmpty name="colecaoEmpresa" scope="session">
											<html:option
												value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<html:options collection="colecaoEmpresa"
												labelProperty="descricao" property="id" />
										</logic:notEmpty>

										<font size="1">&nbsp; </font>
									</html:select>
								</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Matrícula do
										Funcionário:<font color="#FF0000"></font> </strong>
								</td>
								<td width="74%" height="24" colspan="3"><html:text
										maxlength="8" property="idFuncionario" size="8" tabindex="1"
										onkeypress="javascript:validaEnter(event, 'exibirInserirSolicitacaoAcessoAction.do?objetoConsulta=2', 'idFuncionario'); return isCampoNumerico(event); reloadTipoUsuario(); " />
									<a
									href="javascript:javascript:chamarPopup('exibirFuncionarioPesquisar.do?', 'idFuncionario', null, null, 495, 300, '',document.forms[0].idFuncionario); reloadTipoUsuario(); ">
										<img border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Funcionário" /> </a> <logic:present
										name="funcionarioInexistente" scope="request">
										<input type="text" name="nomeFuncionario" size="40"
											readonly="true"
											style="background-color: #EFEFEF; border: 0; color: #ff0000"
											value="<bean:message key="pesquisa.funcionario.inexistente"/>" />
									</logic:present> <logic:notPresent name="funcionarioInexistente"
										scope="request">
										<html:text property="nomeFuncionario" size="40"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notPresent> <a
									href="javascript:limparPesquisaFuncionario(2);document.forms[0].idFuncionario.focus();">
										<img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /> </a>
								</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Nome do
										Usuario:<font color="#FF0000"></font> </strong></td>
								<td width="74%" height="24" colspan="3"><html:text
										property="nomeUsuario" size="50" maxlength="50" />
								</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Número do CPF:<font
										color="#ff0000">*</font> </strong></td>
								<td width="74%" colspan="3"><html:text property="cpf"
										size="12" maxlength="11"
										onkeypress="return isCampoNumerico(event);"
										onkeyup="javascript:replicarLogin();"
										onclick="javascript:replicarLogin();" />
								</td>
							</tr>

							<tr>
								<td height="10" colspan="2"><strong>Data de
										Nascimento:<font color="#FF0000">*</font> </strong></td>
								<td colspan="3"><html:text property="dataNascimento"
										size="11" maxlength="10" tabindex="4"
										onkeyup="mascaraData(this, event);" /> <a
									href="javascript:abrirCalendario('ExibirInserirSolicitacaoAcessoActionForm', 'dataNascimento')">
										<img border="0"
										src="<bean:message 
							 key='caminho.imagens'/>calendario.gif"
										width="20" border="0" align="absmiddle"
										alt="Exibir Calendário" /> </a> dd/mm/aaaa</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Unidade de
										Lotação:<font color="#FF0000">*</font> </strong>
								</td>
								<td width="74%" height="24" colspan="3"><html:text
										maxlength="4" property="idLotacao" size="9" tabindex="1"
										onkeypress="javascript:validaEnter(event, 'exibirInserirSolicitacaoAcessoAction.do', 'idLotacao'); return isCampoNumerico(event);" />
									<a
									href="javascript:chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do?limpaForm=S', null, null, null, 495, 300, '', document.forms[0].nomeLotacao);">
										<img border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Lotação" /> </a> <logic:present
										name="lotacaoInexistente" scope="request">
										<input type="text" name="nomeLotacao" size="40"
											readonly="true"
											style="background-color: #EFEFEF; border: 0; color: #ff0000"
											value="<bean:message key="pesquisa.lotacao.inexistente"/>" />
									</logic:present> <logic:notPresent name="lotacaoInexistente" scope="request">
										<html:text property="nomeLotacao" size="40" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notPresent> <a
									href="javascript:limparLotacao();document.forms[0].idLotacao.focus();">
										<img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /> </a>
								</td>
							</tr>

							<tr>
								<td colspan="2"><strong>Login:<font
										color="#ff0000">*</font> </strong></td>
								<td colspan="1"><html:text property="login" size="11"
										maxlength="11" style="text-transform: none;" />
								</td>
								<td colspan="1"><strong>E-Mail:<font
										color="#ff0000">*</font> </strong></td>
								<td colspan="2"><html:text property="email" size="40"
										maxlength="70" style="text-transform: none;" />
								</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Período de
										Cadastramento:<font color="#ff0000">*</font> </strong></td>
								<td width="74%" colspan="3"><html:text
										property="dataInicial" size="10" maxlength="10"
										onkeyup="mascaraData(this, event);limpaDataFinal();" /> <a
									href="javascript:abrirCalendario('ExibirInserirSolicitacaoAcessoActionForm', 'dataInicial')">
										<img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="20" border="0" align="middle" alt="Exibir Calendário" />
								</a>&nbsp; <html:text property="dataFinal" size="10" maxlength="10"
										onkeyup="mascaraData(this, event);" /> <a
									href="javascript:abrirCalendario('ExibirInserirSolicitacaoAcessoActionForm', 'dataFinal')">
										<img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="20" border="0" align="middle" alt="Exibir Calendário" />
								</a> dd/mm/aaaa</td>
							</tr>



							<tr>
								<td colspan="5"><hr></td>
							</tr>

							<!-- RM 3892 Implantar norma de senhas no GSAN  -->
							<tr>
								<td colspan="5">Acesso do Usuário</td>
							</tr>

							<tr>
								<td width="22%" colspan="2"><strong>Abrangência do
										Acesso:<font color="#FF0000">*</font>
								</strong>
								</td>
								<td colspan="3"><html:select property="abrangencia"
										style="width: 230px;" size="1" tabindex="1"
										onchange="javascript:verificarAcessoAbrangencia();">
										<option value="">&nbsp;</option>
										<html:options name="request"
											collection="colecaoUsuarioAbrangencia"
											labelProperty="descricao" property="id" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="22%" colspan="2"><strong>Gerência
										Regional:</strong>
								</td>
								<td colspan="3"><html:select property="gerenciaRegional"
										style="width: 230px;" size="1" tabindex="1"
										onmousedown="teste1(document.forms[0].gerenciaRegional)"
										disabled="true">
										<option value="">&nbsp;</option>
										<html:options name="request"
											collection="colecaoGerenciaRegional" labelProperty="nome"
											property="id" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="22%" colspan="2"><strong>Unidade
										Negócio:</strong>
								</td>
								<td colspan="3"><html:select disabled="true"
										property="unidadeNegocio" style="width: 230px;" size="1"
										tabindex="1">
										<option value="">&nbsp;</option>
										<html:options name="request"
											collection="colecaoUnidadeNegocio" labelProperty="nome"
											property="id" />
									</html:select>
								</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Elo:</strong>
								</td>
								<td width="74%" height="24" colspan="3">
								<html:text
										maxlength="9" tabindex="1" disabled="true"
										name="ExibirInserirSolicitacaoAcessoActionForm"
										property="idElo" size="9"
										onkeypress="javascript:limparLocalidade();validaEnterComMensagem(event, 'exibirInserirSolicitacaoAcessoAction.do', 'idElo','Elo Pólo');"
										onkeyup="limpaCampoPesquisa(document.forms[0].nomeElo);" /> <a
									href="javascript:chamarPopupElo('exibirPesquisarEloAction.do', 'elo',  400, 800, document.forms[0].idElo,'','');">
										<img width="23" border="0" height="21"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										style="cursor: hand;" alt="Pesquisar" />
								</a> <logic:equal name="ExibirInserirSolicitacaoAcessoActionForm"
										property="eloNaoEncontrada" value="false">
										<html:text property="nomeElo" size="40" readonly="true"
											style="background-color:#EFEFEF; border:0" />
									</logic:equal> <logic:equal name="ExibirInserirSolicitacaoAcessoActionForm"
										property="eloNaoEncontrada" value="true">
										<html:text property="nomeElo" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="40" maxlength="40" />
									</logic:equal> <a href="javascript:limparElo();"> <img border="0"
										src="imagens/limparcampo.gif" height="21" width="23"> </a>
								</td>
							</tr>

							<tr>
								<td width="26%" colspan="2"><strong>Localidade:</strong>
								</td>
								<td width="74%" height="24" colspan="3">
								<html:text property="idLocalidade" maxlength="9" size="9" tabindex="1" 
								disabled="true" name="ExibirInserirSolicitacaoAcessoActionForm"
								onkeypress="validaEnterComMensagem(event, 'exibirInserirSolicitacaoAcessoAction.do', 'idLocalidade', 'Localidade');"
								onkeyup="limpaCampoPesquisa(document.forms[0].nomeLocalidade);" />
								<a
									href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].idLocalidade);">
										<img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										style="cursor: hand;" alt="Pesquisar" />
								</a> <logic:equal name="ExibirInserirSolicitacaoAcessoActionForm"
										property="localidadeNaoEncontrada" value="false">
										<html:text property="nomeLocalidade" size="40" readonly="true"
											style="background-color:#EFEFEF; border:0" />
									</logic:equal> <logic:equal name="ExibirInserirSolicitacaoAcessoActionForm"
										property="localidadeNaoEncontrada" value="true">
										<html:text property="nomeLocalidade" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="40" maxlength="40" />
									</logic:equal> <a href="javascript:limparLocalidade();"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" />
								</a>
								</td>
							</tr>

							<tr>
								<td width="22%" colspan="1"><strong>Grupo: <font
										color="#FF0000">*</font> </strong></td>
								<td colspan="4"><html:select property="idsGrupo"
										size="1" tabindex="2" style="width: 320px;">

										<logic:notEmpty name="colecaoGrupo">
											<html:option
												value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<html:options collection="colecaoGrupo"
												labelProperty="descricao" property="id" />
										</logic:notEmpty>

									</html:select>
								</td>
							</tr>

							<tr>
								<td colspan="5"><hr></td>
							</tr>

							<tr>
								<td>&nbsp;</td>
							</tr>

							<tr>
								<td colspan="2">&nbsp;</td>
								<td align="left" colspan="3"><font color="#FF0000">*</font>
									Campo Obrigatório</td>
							</tr>

							<tr>
								<td>&nbsp;</td>
							</tr>

							<tr>
								<td align="left" colspan="4"><input type="button"
									class="bottonRightCol" value="Limpar"
									onClick="window.location.href='/gsan/exibirInserirSolicitacaoAcessoAction.do?menu=sim'" />

									<input type="button" name="ButtonCancelar"
									class="bottonRightCol" value="Cancelar"
									onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
								</td>

								<td align="right"><input type="button" name="Button"
									class="bottonRightCol" value="Concluir"
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
</body>
</html:html>

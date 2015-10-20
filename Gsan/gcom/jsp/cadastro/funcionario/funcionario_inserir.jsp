<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="InserirFuncionarioActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(form){
	
		if(testarCampoValorZero(document.InserirFuncionarioActionForm.nome, 'Nome') && 
		testarCampoValorZero(document.InserirFuncionarioActionForm.matricula, 'Matr�cula') && 
		testarCampoValorZero(document.InserirFuncionarioActionForm.empresa, 'Empresa') && 
		testarCampoValorZero(document.InserirFuncionarioActionForm.idUnidade, 'Unidade') &&
		validateDate(form) &&
		validarIdadeFuncionario() &&
		validateCpf(form) &&
		validateLong(form) &&
		validateNome(form)
		) {
	
			if(validateInserirFuncionarioActionForm(form)){
					submeterFormPadrao(form);
			}
		}
	}

	function validateNome(form) {
		var bValid = true;
		var focusField = null;
		var i = 0;
		var fields = new Array();
		oNome = new nome();
		for (x in oNome) {
			if ((form[oNome[x][0]].type == 'text'
					|| form[oNome[x][0]].type == 'textarea' || form[oNome[x][0]].type == 'password')
					&& (form[oNome[x][0]].value.length > 0)) {
				if (validacaoNome(form[oNome[x][0]].value)) {
					if (i == 0) {
						focusField = form[oNome[x][0]];
					}
					fields[i++] = oNome[x][1];
					bValid = false;
				}
			}
		}
		if (fields.length > 0) {
			focusField.focus();
			alert(fields.join('\n'));
		}
		return bValid;
	}

	function validacaoNome(c) {
		var achou = false;

		var indesejaveis = "~{}^%$[]@|`\\<�\#?!;*>\"\'+()&��=���������������/�1234567890�:�";

		for ( var i = 0; i < indesejaveis.length; i++) {
			if ((c.indexOf(indesejaveis.charAt(i))) != -1) {
				achou = true;
			}
		}

		return achou;
	}

	function nome() {
		this.aa = new Array("nome","Nome possui caracteres especiais ou n�meros.", new Function("varName", " return this[varName];"));
	}

	function IntegerValidations() {
		this.aa = new Array("idUnidade","Unidade deve somente conter n�meros positivos.", new Function(	"varName", " return this[varName];"));
		this.ab = new Array("matricula","Matr�cula deve somente conter n�meros positivos.", new Function(	"varName", " return this[varName];"));
	}

	function cpf() {
		this.aa = new Array("numeroCpf","D�gito verificador do CPF n�o confere.", new Function("varName", " return this[varName];"));
	}

	function DateValidations() {
		this.aa = new Array("dataNascimento","Data de Nascimento inv�lida.",new Function("varName",	"this.datePattern='dd/MM/yyyy';  return this[varName];"));
	}

	function limparUnidade() {
		var form = document.InserirFuncionarioActionForm;
		form.idUnidade.value = "";
		form.nomeUnidade.value = "";
	}

	/* Chama Popup */
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,	objetoRelacionado) {
		if (objetoRelacionado.disabled != true) {
			if (objeto == null || codigoObjeto == null) {
				abrirPopup(url + "&" + "tipo=" + tipo, altura, largura);
			} else {
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
					alert(msg);
				} else {
					abrirPopup(url + "&" + "tipo=" + tipo + "&" + objeto + "="+ codigoObjeto + "&caminhoRetornoTelaPesquisa="+ tipo, altura, largura);
				}
			}
		}
	}

	/* Recupera Dados Popup */
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro,
			tipoConsulta) {
		var form = document.forms[0];

		form.action = 'exibirInserirFuncionarioAction.do';
		if (tipoConsulta == 'unidadeOrganizacional') {
			form.idUnidade.value = codigoRegistro;
			form.nomeUnidade.value = descricaoRegistro;
			form.nomeUnidade.style.color = "#000000";
		}
		submeterFormPadrao(form);
	}

	function validarIdadeFuncionario() {

		var retorno = true;
		var form = document.forms[0];

		if (form.dataNascimento.value.length > 0) {

			var idadeMinimaFuncionario = document
					.getElementById("IDADE_MINIMA_FUNCIONARIO").value;

			var dataAtual = document.getElementById("DATA_ATUAL").value;
			var idadeFuncionario = anosEntreDatas(form.dataNascimento.value,
					dataAtual);

			if (parseInt(idadeFuncionario) < parseInt(idadeMinimaFuncionario)) {

				alert("O funcion�rio ter� que possuir, no m�nimo, "
						+ idadeMinimaFuncionario + " anos de idade");
				form.dataNascimento.focus();
				retorno = false;
			}
		}

		return retorno;
	}
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirFuncionarioAction.do" 
	method="post"
	name="InserirFuncionarioActionForm"
	type="gcom.gui.cadastro.funcionario.InserirFuncionarioActionForm"
	onsubmit="return validateInserirFuncionarioActionForm(this);">
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<INPUT TYPE="hidden" ID="DATA_ATUAL" value="${requestScope.dataAtual}" />
	<INPUT TYPE="hidden" ID="IDADE_MINIMA_FUNCIONARIO" value="${requestScope.idadeMinimaFuncionario}" />

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
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Inserir Funcion&aacute;rio</td>
						<td width="11" valign="top">
							<img border="0"
								src="imagens/parahead_right.gif" /></td>
					</tr>
	
				</table>

			<p>&nbsp;</p>
			
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para inserir um funcion&aacute;rio, informe os dados
					abaixo:</td>
				</tr>
				
				<tr>
					<td><strong>Matr�cula:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="matricula" 
							size="9" 
							maxlength="9"
							onkeyup="javascript:verificaNumeroInteiro(this);"/> 
					</td>
				</tr>
				
				<tr>
					<td><strong>Nome:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="nome" 
							size="60" 
							maxlength="70"/></td>
				</tr>
				
				<tr>
					<td>
						<strong>CPF:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:text property="numeroCpf" 
							size="11" 
							maxlength="11" 
							tabindex="1" />
					</td>
				</tr>
				
				<tr>
					<td><strong>Data de Nascimento:</strong></td>
					<td>
						<html:text property="dataNascimento" 
							size="10" 
							maxlength="10"
							tabindex="6" 
							onkeyup="javascript:mascaraData(this, event);"/>
							
							<a href="javascript:abrirCalendario('InserirFuncionarioActionForm', 'dataNascimento')">
								<img border="0" 
									src="<bean:message key="caminho.imagens"/>calendario.gif"
									width="20" 
									border="0" 
									align="absmiddle" 
									alt="Exibir Calend�rio" /></a>
							<font size="2">dd/mm/aaaa</font>
					</td>
				</tr>
				
				<tr>
					<td><strong>Cargo:<font color="#ff0000">*</font></strong></td>
					<td>
						<html:select property="funcionarioCargo">
							<option value="-1"></option>
							<html:options name="request" 
								collection="colecaoFuncionarioCargo"
								labelProperty="descricao" 
								property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Empresa:<font color="#ff0000">*</font></strong></td>
					<td>
						<html:select property="empresa">
							<option value="-1"></option>
							<html:options name="request" 
								collection="colecaoEmpresa"
								labelProperty="descricao" 
								property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Unidade Organizacional: <font color="#FF0000">*</font></strong></td>
					<td>
						<html:text maxlength="4" 
							property="idUnidade"
							size="4" 
							onkeyup="javascript:limparUnidadeTecla();" 
							onkeyup="javascript:verificaNumeroInteiro(this);" 
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirInserirFuncionarioAction.do', 'idUnidade', 'Unidade Organizacional');" />
						
						<a href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do');">
							<img width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Unidade Organizacional" /></a> 
								
						<logic:present name="idUnidadeNaoEncontrado" scope="request">
							<html:text maxlength="30" 
								property="nomeUnidade" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" />
						</logic:present> 

						<logic:notPresent name="idUnidadeNaoEncontrado" scope="request">
							<html:text maxlength="30" 
								property="nomeUnidade" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="40" />
						</logic:notPresent> 
						
						<a href="javascript:limparUnidade();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td></td>
					<td align="right">
						<div align="left">
							<strong><font color="#FF0000">*</font></strong>
							Campos obrigat&oacute;rios
						</div>
					</td>
				</tr>
				
				<tr>
					<td>
						<input name="Button" 
							type="button" 
							class="bottonRightCol"
							value="Desfazer" 
							align="left"
							onclick="window.location.href='<html:rewrite page="/exibirInserirFuncionarioAction.do?desfazer=S"/>'">
						
						<input name="Button" 
							type="button" 
							class="bottonRightCol"
							value="Cancelar" 
							align="left"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					
					<td align="right">
						<input name="Button" 
							type="button"
							class="bottonRightCol" 
							value="Inserir" 
							align="right"
							onClick="javascript:validarForm(document.forms[0]);">
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>
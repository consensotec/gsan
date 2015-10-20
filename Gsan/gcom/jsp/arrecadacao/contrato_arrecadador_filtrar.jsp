<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de
	Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<html:html>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarContratoArrecadadorActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--
	function Limpar() {
		document.forms[0].reset();
	}

	//Recupera Dados Popup
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro,
			tipoConsulta) {
		var form = document.forms[0];
		if (tipoConsulta == 'cliente') {
			form.idCliente.value = codigoRegistro;
			form.nomeCliente.value = descricaoRegistro;
			form.nomeCliente.style.color = "#000000";
		}
		if (tipoConsulta == 'imovel') {
			form.idImovel.value = codigoRegistro;
			form.inscricaoImovel.value = descricaoRegistro;
		}
		if (tipoConsulta == 'imovel') {
			form.idImovel.value = codigoRegistro;
			form.inscricaoImovel.value = descricaoRegistro;
		}

	}

	function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1,
			descricaoRegistro2, descricaoRegistro3, tipoConsulta) {
		var form = document.forms[0];
		if (tipoContaBancaria == 'arrecadacao') {
			form.idContaBancariaArrecadador.value = codigoRegistro;
			form.bcoArrecadadorConta.value = descricaoRegistro1;
			form.agArrecadadorConta.value = descricaoRegistro2;
			form.numeroArrecadadorConta.value = descricaoRegistro3;
		} else {
			if (tipoContaBancaria == 'tarifa') {
				form.idContaBancariaTarifa.value = codigoRegistro;
				form.bcoTarifaConta.value = descricaoRegistro1;
				form.agTarifaConta.value = descricaoRegistro2;
				form.numeroTarifaConta.value = descricaoRegistro3;
			}
			abrirPopup('contaBancariaPesquisarAction.do?tipo=contaBancaria');
		}
	}

	function pesquisaTipoConta(tipo) {
		tipoContaBancaria = tipo;
		abrirPopup('contaBancariaPesquisarAction.do?tipo=contaBancaria');
	}

	// Chama Popup
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,
			objetoRelacionado) {
		if (objetoRelacionado.disabled != true) {
			if (objeto == null || codigoObjeto == null) {
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
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

	function limpaDataFinal() {
		var form = document.forms[0];
		if (form.dtFimContrato.value == '') {
			form.dtFimContrato.value = '';
		}

	}

	function replicarRetorno() {
		var form = document.forms[0];

		form.dtFimContrato.value = form.dtInicioContrato.value;
	}

	function limparForm(tipo) {
		var form = document.forms[0];
		if (tipo == 'imovel') {
			form.idImovel.value = "";
			form.inscricaoImovel.value = "";
		}
		if (tipo == 'cliente') {
			form.idCliente.value = "";
			form.nomeCliente.value = "";
		}
	}

	function limparContaBancaria(form, contaBancaria) {
		if (contaBancaria == 'arrecadacao') {
			form.idContaBancariaArrecadador.value = "";
			form.bcoArrecadadorConta.value = "";
			form.agArrecadadorConta.value = "";
			form.numeroArrecadadorConta.value = "";
		} else if (contaBancaria == 'tarifa') {
			form.idContaBancariaTarifa.value = "";
			form.bcoTarifaConta.value = "";
			form.agTarifaConta.value = "";
			form.numeroTarifaConta.value = "";
		}
	}

	function validarForm(formulario) {
		if(validateFiltrarContratoArrecadadorActionForm(form)){
			if(validaData(form)){
				if (validateInteger(formulario) && validateEmail(form)) {
					submeterFormPadrao(formulario);
				}
			}
		}
	}

	 function validaData(){

    	 var form = document.forms[0];	
			if (comparaData(form.dtInicioContrato.value, ">", form.dtFimContrato.value)) {
				alert('Data de Fim do Contrato tem que ser superior a Data de In�cio');
			} else {

    			return true;
	    	}
		
     }

	function email() {
		this.aa = new Array("emailCliente", "E-Mail inv�lido.", new Function(
				"varName", " return this[varName];"));
	}

	function IntegerValidations() {
		this.aa = new Array("idArrecadador",
				"O campo C�digo do Arrecadador deve conter apenas n�meros.",
				new Function("varName", " return this[varName];"));
		this.ac = new Array("idCliente",
				"O campo Cliente deve conter apenas n�meros.", new Function(
						"varName", " return this[varName];"));
	}

	function verificarChecado(valor) {
		form = document.forms[0];
		if (valor == "1") {
			form.indicadorAtualizar.checked = true;
		} else {
			form.indicadorAtualizar.checked = false;
		}
	}
//-->
</SCRIPT>
</head>

<body leftmargin="5" topmargin="5"
	onLoad="verificarChecado('${indicadorAtualizar}');">

	<html:form action="/filtrarContratoArrecadadorAction"
		name="FiltrarContratoArrecadadorActionForm"
		type="gcom.gui.arrecadacao.FiltrarContratoArrecadadorActionForm"
		method="post" onsubmit="return validateFiltrarContratoArrecadadorActionForm(this);">

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
					</div></td>

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
								src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
							</td>
							<td class="parabg">Filtrar Contrato de Arrecadador</td>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>

					<table width="100%" border="0">

						<tr>
							<td width="100%" colspan="3">
								<table width="100%">
									<tr>
										<td width="80%">Para filtar o(s) Contratos de
											Arrecadador, informe os dados abaixo:</td>
										<td align="right"><input type="checkbox"
											name="indicadorAtualizar" value="1" onclick="" /> <strong>Atualizar</strong>
										</td>
									</tr>
								</table></td>
						</tr>
					</table>

					<table width="100%" border="0">
						<tr>
							<td width="139" height="22"><strong>C�digo do
									Arrecadador:<font color="#FF0000"></font> </strong></td>
							<td width="406"><strong> <html:text
										property="idArrecadador" size="4" maxlength="3"
										onkeypress="return isCampoNumerico(event);" /> </strong></td>
						</tr>

						<tr>
							<td width="25%"><strong>N�mero Contrato:</strong>
							</td>
							<td><html:text property="numeroContrato" size="9"
									maxlength="10" tabindex="2" />
							</td>
						</tr>

						<tr>
							<td width="32%"><strong>Conta Dep�sito Arrecada��o:
							</strong>
							</td>
							<html:hidden property="idContaBancariaArrecadador" />
							<td width="68%"><html:text maxlength="4"
									property="bcoArrecadadorConta" size="4" readonly="true"
									style="background-color:#EFEFEF; border:0; font-color: #000000" />
								<html:text maxlength="9" property="agArrecadadorConta" size="9"
									readonly="true"
									style="background-color:#EFEFEF; border:0; font-color: #000000" />
								<html:text maxlength="20" property="numeroArrecadadorConta"
									size="20" readonly="true"
									style="background-color:#EFEFEF; border:0; font-color: #000000" />
								<a href="javascript:pesquisaTipoConta('arrecadacao');"> <img
									width="23" height="21"
									src="<bean:message key='caminho.imagens'/>pesquisa.gif"
									border="0" title="Pesquisar Conta Dep�sito Arrecada��o" /> </a> <a
								href="javascript:limparContaBancaria(document.forms[0],'arrecadacao');">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Limpar Conta Dep�sito Arrecada��o" /> </a></td>
						</tr>

						<tr>
							<td width="32%"><strong>Conta Dep�sito Tarifa: </strong>
							</td>
							<html:hidden property="idContaBancariaTarifa" />
							<td width="68%"><html:text maxlength="4"
									property="bcoTarifaConta" size="4" readonly="true"
									style="background-color:#EFEFEF; border:0; font-color: #000000" />
								<html:text maxlength="9" property="agTarifaConta" size="9"
									readonly="true"
									style="background-color:#EFEFEF; border:0; font-color: #000000" />
								<html:text maxlength="20" property="numeroTarifaConta" size="20"
									readonly="true"
									style="background-color:#EFEFEF; border:0; font-color: #000000" />
								<a href="javascript:pesquisaTipoConta('tarifa');"> <img
									width="23" height="21"
									src="<bean:message key='caminho.imagens'/>pesquisa.gif"
									border="0" title="Pesquisar Conta Dep�sito Tarifa" /> </a> <a
								href="javascript:limparContaBancaria(document.forms[0],'tarifa');">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Limpar Conta Dep�sito Tarifa" /> </a></td>
						</tr>

						<tr>
							<td><strong>Cliente: </strong>
							</td>
							<td colspan="3"><strong> <html:text
										property="idCliente" size="10" maxlength="10"
										onkeypress="javascript:validaEnter(event, 'exibirFiltrarContratoArrecadadorAction.do?objetoConsulta=1', 'idCliente');return isCampoNumerico(event);" />
							</strong> <a
								href="javascript:chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',document.forms[0].idCliente.value);"
								alt="Pesquisar Cliente"> <img width="23" height="21"
									src="<bean:message key='caminho.imagens'/>pesquisa.gif"
									border="0" title="Pesquisar Cliente" /> </a> <logic:present
									name="existeCliente">
									<logic:equal name="existeCliente" value="exception">
										<html:text property="nomeCliente" size="35" maxlength="35"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:equal>
									<logic:notEqual name="existeCliente" value="exception">
										<html:text property="nomeCliente" size="35" maxlength="35"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notEqual>
								</logic:present> <logic:notPresent name="existeCliente">
									<logic:empty name="FiltrarContratoArrecadadorActionForm"
										property="idCliente">
										<html:text property="nomeCliente" size="35" value=""
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:empty>
									<logic:notEmpty name="FiltrarContratoArrecadadorActionForm"
										property="idCliente">
										<html:text property="nomeCliente" size="35" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notEmpty>
								</logic:notPresent> <a href="javascript:limparForm('cliente');"> <img
									border="0"
									src="<bean:message key='caminho.imagens'/>limparcampo.gif"
									style="cursor: hand;" title="Apagar" /> </a></td>
						</tr>

						<tr>
							<td width="32%" class="style3"><strong>C�digo do
									Conv�nio:</strong></td>
							<td width="68%" colspan="2"><strong><b><span
										class="style2"> <html:text property="idConvenio"
												size="28" maxlength="20" tabindex="4" /> </span> </b> </strong></td>
						</tr>

						<tr>
							<td width="32%"><strong>Indicador de Cobran�a de
									ISS:</strong>
							</td>
							<td width="68%"><strong> <span class="style1"><strong>
											<html:radio property="indicadorCobranca" value="1"
												tabindex="5" /> <strong>Cobra ISS <html:radio
													property="indicadorCobranca" value="2" tabindex="6" />N�o
												Cobra ISS </strong> </strong> </span> </strong>
							</td>
						</tr>
						<tr>
					<td><strong>Data de Inicio do Contrato:</strong></td>
					<td><strong> <html:text property="dtInicioContrato" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);"/> </strong> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calend�rio"
						onclick="javascript:abrirCalendario('FiltrarContratoArrecadadorActionForm', 'dtInicioContrato')" />
					dd/mm/aaaa </td>
				  </tr>
				  
				  <tr>
					<td><strong>Data de Fim do Contrato:</strong></td>
					<td><strong> <html:text property="dtFimContrato" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> </strong> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calend�rio"
						onclick="javascript:abrirCalendario('FiltrarContratoArrecadadorActionForm', 'dtFimContrato')" />
					dd/mm/aaaa</td>
				</tr>
						<tr>
							<td><strong>E-mail:</strong>
							</td>
							<td width="68%" colspan="2"><strong><b><span
										class="style2"> <html:text property="emailCliente"
												size="45" maxlength="40" tabindex="2" /> </span> </b> </strong></td>
						</tr>
						<tr>
							<td><input name="Button" type="button"
								class="bottonRightCol" value="Limpar" align="left"
								onclick="window.location.href='/gsan/exibirFiltrarContratoArrecadadorAction.do?menu=sim'"
								tabindex="8"></td>
							<td align="right" colspan="2">&nbsp;</td>
							<td width="65" align="right"><input name="Button2"
								type="button" class="bottonRightCol" value="Filtrar"
								align="right"
								onClick="javascript:validarForm(document.forms[0]);"
								tabindex="9"></td>
						</tr>
					</table>
					<p>&nbsp;</p></td>
			</tr>
		</table>

		<%@ include file="/jsp/util/rodape.jsp"%>

	</html:form>


</body>
</html:html>


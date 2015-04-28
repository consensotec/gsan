<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gsan.util.ConstantesSistema"%>
<%@ page import="gsan.micromedicao.consumo.LigacaoTipo"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="FiltrarHistoricoMedicaoIndividualizadaActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
//<!-- Begin
	var bCancel = false;

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'imovel') {
			form.idImovel.value = codigoRegistro;
			form.descricaoImovel.value = descricaoRegistro;
			form.descricaoImovel.style.color = "#000000";
			redirecionarSubmit("exibirFiltrarHistoricoMedicaoIndividualizadaAction.do?limparColecoes=OK");
		}

		if (tipoConsulta == 'localidadeOrigem') {
			form.localidadeInicial.value = codigoRegistro;
			form.nomeLocalidadeInicial.value = descricaoRegistro;

			form.localidadeFinal.value = codigoRegistro;
			form.nomeLocalidadeFinal.value = descricaoRegistro;

			form.nomeLocalidadeInicial.style.color = "#000000";
			form.nomeLocalidadeFinal.style.color = "#000000";

			form.action = 'exibirFiltrarHistoricoMedicaoIndividualizadaAction.do';
			form.submit();

			form.setorComercialInicial.focus();
		}

		if (tipoConsulta == 'localidadeDestino') {
			form.localidadeFinal.value = codigoRegistro;
			form.nomeLocalidadeFinal.value = descricaoRegistro;
			form.nomeLocalidadeFinal.style.color = "#000000";

			form.action = 'exibirFiltrarHistoricoMedicaoIndividualizadaAction.do';
			form.submit();

			form.setorComercialFinal.focus();
		}
	}

	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'setorComercialOrigem') {
		  	form.setorComercialInicial.value = codigoRegistro;
		  	form.nomeSetorComercialInicial.value = descricaoRegistro;
		  	form.nomeSetorComercialInicial.style.color = "#000000"; 

		  	form.setorComercialFinal.value = codigoRegistro;
		  	form.nomeSetorComercialFinal.value = descricaoRegistro;
		  	form.nomeSetorComercialFinal.style.color = "#000000";

		  	form.action='exibirFiltrarHistoricoMedicaoIndividualizadaAction.do';
	   		form.submit();
		}

		if (tipoConsulta == 'setorComercialDestino') {
		  	form.setorComercialFinal.value = codigoRegistro;
		  	form.nomeSetorComercialFinal.value = descricaoRegistro;
		  	form.nomeSetorComercialFinal.style.color = "#000000";

		  	form.action='exibirFiltrarHistoricoMedicaoIndividualizadaAction.do';
	   		form.submit();
		}
	}

	function limpar() {
		var form = document.forms[0];

		form.action = 'exibirFiltrarHistoricoMedicaoIndividualizadaAction.do?limparForm=OK';
		form.submit();
	}

	function limparForm() {
		var form = document.forms[0];

		form.idImovel.value = "";
		form.descricaoImovel.value = "";

		form.action = 'exibirFiltrarHistoricoMedicaoIndividualizadaAction.do?limparImovel=OK';
		form.submit();
	}

	function validateFiltrarHistoricoMedicaoIndividualizadaActionForm(form) {
		if (bCancel)
			return true;
		else {
			return validateRequired(form) && validateCaracterEspecial(form) && validarCampos(form);
		}
	}

	function validarCampos(form) {
		var bValid = true;
		oCampoParaValidar = new camposParaValidar();

		for (x in oCampoParaValidar) {
			if ((form[oCampoParaValidar[x][0]].value != "" || form[oCampoParaValidar[x][0]].value.length > 0) 
					&& (form[oCampoParaValidar[x][1]].value == "" || form[oCampoParaValidar[x][1]].value.length <= 0)) {
				alert("Informe " + oCampoParaValidar[x][2] + " Final");
				bValid = false;
			} else if ((form[oCampoParaValidar[x][1]].value != "" || form[oCampoParaValidar[x][1]].value.length > 0) 
					&& (form[oCampoParaValidar[x][0]].value == "" || form[oCampoParaValidar[x][0]].value.length <= 0)) {
				alert("Informe " + oCampoParaValidar[x][2] + " Inicial");
				bValid = false;
			} else if (parseInt(form[oCampoParaValidar[x][1]].value) < parseInt(form[oCampoParaValidar[x][0]].value)) {
				alert(oCampoParaValidar[x][2] + " Final é menor que " + oCampoParaValidar[x][2] + " Inicial");
				bValid = false;
			}
        }

		return bValid;
	}

	function camposParaValidar(){
		this.aa = new Array("localidadeInicial", "localidadeFinal", "Localidade");
		this.ab = new Array("setorComercialInicial", "setorComercialFinal", "Setor Comercial");
		this.ac = new Array("rotaInicial", "rotaFinal", "Rota");
		this.ad = new Array("sequencialRotaInicial", "sequencialRotaFinal", "Sequencial Rota");
	}

	function required() {
		this.aa = new Array("mesAnoFaturamento", "Informe Mês/Ano do Faturamento.", new Function("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	}

	function caracteresespeciais() {
		this.aa = new Array("mesAnoFaturamento", "Data de Mês/Ano do Faturamento possui caracteres especiais.", new Function("varName", " return this[varName];"));
		this.ab = new Array("idImovel", "Matícula do Imóvel Condomínio possui caracteres especiais.", new Function("varName", " return this[varName];"));
		this.ac = new Array("localidadeInicial", "Localidade Inicial possui caracteres especiais.", new Function("varName", " return this[varName];"));
		this.ad = new Array("setorComercialInicial", "Setor Comercial Inicial possui caracteres especiais.", new Function("varName", " return this[varName];"));
		this.ae = new Array("rotaInicial", "Rota Inicial possui caracteres especiais.", new Function("varName", " return this[varName];"));
		this.af = new Array("sequencialRotaInicial", "Sequencial Inicial da Rota possui caracteres especiais.", new Function("varName", " return this[varName];"));
		this.ag = new Array("localidadeFinal", "Localidade Final possui caracteres especiais.", new Function("varName", " return this[varName];"));
		this.ah = new Array("setorComercialFinal", "Setor Comercial Final possui caracteres especiais.", new Function("varName", " return this[varName];"));
		this.ai = new Array("rotaFinal", "Rota Final possui caracteres especiais.", new Function("varName", " return this[varName];"));
		this.aj = new Array("sequencialRotaFinal", "Sequencial Final da Rota possui caracteres especiais.", new Function("varName", " return this[varName];"));
		this.al = new Array("faixaInicial", "Faixa Inicial possui caracteres especiais.", new Function("varName", " return this[varName];"));
		this.am = new Array("faixaFinal", "Faixa Final possui caracteres especiais.", new Function("varName", " return this[varName];"));
		this.an = new Array("imoveisVinculadosInicial", "Números de imóveis vinculados Inicial possui caracteres especiais.", new Function("varName", " return this[varName];"));
		this.ao = new Array("imoveisVinculadosFinal", "Números de imóveis vinculados Final possui caracteres especiais.", new Function("varName", " return this[varName];"));
	}

	function validarForm() {
		var form = document.forms[0];

		if (validateFiltrarHistoricoMedicaoIndividualizadaActionForm(form)
				&& verificaAnoMesMensagemPersonalizada(form.mesAnoFaturamento, "Mês/Ano do Faturamento inválido.") && validarTipoLigacao(form.tipoLigacao)) {
			form.submit();
		}
	}

	function validarTipoLigacao(tipoLigacao){
		if(tipoLigacao.value!="-1"){
			return true;
		}else{
			alert("Informe o Tipo  de Ligação");
			return false;
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
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}

	function pesquisarUnidadeNegocio() {
		var form = document.forms[0];

		form.action = "exibirFiltrarHistoricoMedicaoIndividualizadaAction.do";
		form.submit();
	}

	function replicarLocalidade(){
		var form = document.forms[0];

		form.localidadeFinal.value = form.localidadeInicial.value;
	}

	function replicarSetorComercial(){
		var form = document.forms[0]; 

		form.setorComercialFinal.value = form.setorComercialInicial.value;
	} 

	function replicarRota(){
		var form = document.forms[0]; 

		form.rotaFinal.value = form.rotaInicial.value;
	} 

	function replicarSequencialRota(){
		var form = document.forms[0];

		form.sequencialRotaFinal.value = form.sequencialRotaInicial.value;
	}

	function limparBorrachaOrigem(tipo) {
		var form = document.forms[0];

		switch (tipo) {
		case 1: //De localidara pra baixo
			form.localidadeInicial.value = "";
			form.nomeLocalidadeInicial.value = "";
			form.localidadeFinal.value = "";
			form.nomeLocalidadeFinal.value = "";
			form.setorComercialInicial.value = "";
			form.nomeSetorComercialInicial.value = "";
			form.setorComercialFinal.value = "";
			form.nomeSetorComercialFinal.value = "";
			break;
		case 2: //De setor para baixo
			form.setorComercialInicial.value = "";
			form.nomeSetorComercialInicial.value = "";
			form.setorComercialFinal.value = "";
			form.nomeSetorComercialFinal.value = "";
			break;
		}
	}

	function limparBorrachaDestino(tipo) {
		var form = document.forms[0];

		switch (tipo) {
		case 1: //De localidade pra baixo
			form.localidadeFinal.value = "";
			form.nomeLocalidadeFinal.value = "";
			form.setorComercialFinal.value = "";
			break;
		case 2: //De setor para baixo		   
			form.setorComercialFinal.value = "";
			form.nomeSetorComercialFinal.value = "";
			break;
		}
	}

	function validarRateio() {
		var form = document.forms[0];

		if (form.rateio.selectedIndex == 2 || form.rateio.selectedIndex == 3) {
			form.faixaInicial.disabled = false;
			form.faixaFinal.disabled = false;
		} else {
			form.faixaInicial.value = "";
			form.faixaFinal.value = "";

			form.faixaInicial.disabled = true;
			form.faixaFinal.disabled = true;
		}
	}

	function validarRota(campo, objetoConsulta) {
		var form = document.forms[0];

		if (campo.value != "" && campo.value.length > 0) {
			form.action = "exibirFiltrarHistoricoMedicaoIndividualizadaAction.do?objetoConsulta="+objetoConsulta;
			form.submit();
		}
	}
//End-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:validarRateio();">
	<html:form action="/exibirManterHistoricoMedicaoIndividualizadaAction"
		name="FiltrarHistoricoMedicaoIndividualizadaActionForm"
		type="gsan.gui.micromedicao.FiltrarHistoricoMedicaoIndividualizadaActionForm" method="post"
		onsubmit="return validateFiltrarHistoricoMedicaoIndividualizadaActionForm(this);">

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
				<td valign="top" class="centercoltext">
					<table height="100%">
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
							<td class="parabg">Consultar Hist&oacute;rico de Medi&ccedil;&atilde;o Individualizada</td>
							<td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif" /></td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table width="598" border="0">
						<tr>
							<td>Para filtrar o hist&oacute;rico de medi&ccedil;&atilde;o individualizada, informe os dados abaixo:</td>
							<td align="right">
								<span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span>
							</td>
						</tr>
					</table>
					<table width="598" border="0">
						<tr>
							<td width="220" class="style1">
								<strong>M&ecirc;s/Ano do Faturamento:</strong><strong><font color="#FF0000">*</font></strong>
							</td>
							<td width="310" class="style1">
								<html:text property="mesAnoFaturamento" size="10" maxlength="7" onkeypress="return isCampoNumerico(event);" onkeyup="javascript:mascaraAnoMes(this, event);" />&nbsp;mm/aaaa
							</td>
						</tr>
						<tr>
							<td>
								<strong>Tipo de Ligação:</strong><strong><font color="#FF0000">*</font></strong>
							</td>
							<td>
								<html:select property="tipoLigacao">
									<html:option value="<%="" + ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoLigacaoTipo" labelProperty="descricao" property="id" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="style1">
								<strong>Matr&iacute;cula do Im&oacute;vel Condom&iacute;nio:</strong></strong>
							</td>
							<td class="style1" colspan="2">
								<div align="left">
									<html:text property="idImovel" maxlength="9" size="9" onkeypress="validaEnter(event, 'exibirFiltrarHistoricoMedicaoIndividualizadaAction.do?pesquisarImovel=OK', 'idImovel');return isCampoNumerico(event);" />
									<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do?pesquisarImovelCondominio=OK', 400, 800);">
										<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
									<logic:present name="matriculaInexistente" scope="request">
										<html:text property="descricaoImovel" size="25" maxlength="25" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:present>
									<logic:notPresent name="matriculaInexistente" scope="request">
										<html:text property="descricaoImovel" size="25" maxlength="25" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notPresent>
									<a href="javascript:limparForm();">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<strong>Ger&ecirc;ncia Regional:</strong>
							</td>
							<td>
								<html:select property="gerenciaRegional" style="width: 230px;" onchange="javascript:pesquisarUnidadeNegocio();" disabled="${desabilitarCampos}">
									<html:option value="<%="" + ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<logic:present name="colecaoGerenciaRegional" scope="request">
										<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td>
								<strong>Unidade de Neg&oacute;cio:</strong>
							</td>
							<td>
								<html:select property="unidadeNegocio" style="width: 230px;" disabled="${desabilitarCampos}">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<logic:present name="colecaoUnidadeNegocio" scope="request">
										<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td>
								<strong>Localidade Inicial:</strong>
							</td>
							<td>
								<html:text maxlength="3" property="localidadeInicial" size="3" onkeyup="javascript:replicarLocalidade();" onblur="javascript:replicarLocalidade();" onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarHistoricoMedicaoIndividualizadaAction.do?objetoConsulta=1','localidadeInicial','Localidade Inicial');" disabled="${desabilitarCampos}" />
								<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].localidadeInicial);">
									<img width="23" height="21" border="0" style="cursor: hand;" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Localidade" /></a>
								<logic:present name="localidadeInicialEncontrada" scope="request">
									<html:text property="nomeLocalidadeInicial" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:present>
								<logic:notPresent name="localidadeInicialEncontrada" scope="request">
									<html:text property="nomeLocalidadeInicial" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
								</logic:notPresent>
								<a href="javascript:limparBorrachaOrigem(1);">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
							</td>
						</tr>
						<tr>
							<td>
								<strong>Setor Comercial Inicial:</strong>
							</td>
							<td>
								<%-- <html:text maxlength="3" property="setorComercialInicial" size="3" onkeyup="javascript:replicarSetorComercial();" onblur="javascript:replicarSetorComercial();" onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarHistoricoMedicaoIndividualizadaAction.do?objetoConsulta=2','setorComercialInicial','Setor Comercial Inicial');" disabled="${desabilitarCampos}" /> --%>
								<html:text maxlength="3" property="setorComercialInicial" size="3" onkeyup="javascript:replicarSetorComercial();" onblur="javascript:replicarSetorComercial();" onkeypress="javascript:validaEnterDependencia(event, 'exibirFiltrarHistoricoMedicaoIndividualizadaAction.do?objetoConsulta=2', this, document.forms[0].localidadeInicial.value, 'Localidade Inicial');" disabled="${desabilitarCampos}" />
								<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.forms[0].localidadeInicial.value , 275, 480, 'Informe Localidade Inicial',document.forms[0].setorComercialInicial);">
									<img width="23" height="21" border="0" style="cursor: hand;" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Setor Comercial" /></a>
								<logic:present name="setorComercialInicialEncontrado" scope="request">
									<html:text property="nomeSetorComercialInicial" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:present>
								<logic:notPresent name="setorComercialInicialEncontrado" scope="request">
									<html:text property="nomeSetorComercialInicial" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
								</logic:notPresent>
								<a href="javascript:limparBorrachaOrigem(2);">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
							</td>
						</tr>
						<tr>
							<td>
								<strong>Rota Inicial:</strong>
							</td>
							<td>
								<html:text maxlength="5" property="rotaInicial" onkeyup="javascript:replicarRota();" onblur="javascript:replicarRota();" onkeypress="javascript:mascara(this, mascaraInteiro);" size="5" disabled="${desabilitarCampos}" />
								<!-- onblur="javascript:validarRota(this, 5);" -->
							</td>
						</tr>
						<tr>
							<td>
								<strong>Sequencial Inicial da Rota:</strong>
							</td>
							<td>
								<html:text maxlength="5" property="sequencialRotaInicial" onkeyup="javascript:replicarSequencialRota();" onblur="javascript:replicarSequencialRota();" size="5" disabled="${desabilitarCampos}" />
							</td>
						</tr>
						<tr>
							<td>
								<strong>Localidade Final:</strong>
							</td>
							<td>
								<html:text maxlength="3" property="localidadeFinal" size="3" onkeypress="validaEnterComMensagem(event, 'exibirFiltrarHistoricoMedicaoIndividualizadaAction.do?objetoConsulta=3','localidadeFinal','Localidade Final');" disabled="${desabilitarCampos}" />
								<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].localidadeFinal);">
									<img width="23" height="21" border="0" style="cursor: hand;" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Localidade" /></a>
								<logic:present name="localidadeFinalEncontrada" scope="request">
									<html:text property="nomeLocalidadeFinal" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:present>
								<logic:notPresent name="localidadeFinalEncontrada" scope="request">
									<html:text property="nomeLocalidadeFinal" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
								</logic:notPresent>
								<a href="javascript:limparBorrachaDestino(1);">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
							</td>
						</tr>
						<tr>
							<td>
								<strong>Setor Comercial Final:</strong>
							</td>
							<td>
								<%-- <html:text maxlength="3" property="setorComercialFinal" size="3" onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarHistoricoMedicaoIndividualizadaAction.do?objetoConsulta=4', 'setorComercialFinal', 'Setor Comercial Final');" disabled="${desabilitarCampos}" /> --%>
								<html:text maxlength="3" property="setorComercialFinal" size="3" onkeypress="javascript:validaEnterDependencia(event, 'exibirFiltrarHistoricoMedicaoIndividualizadaAction.do?objetoConsulta=4', this, document.forms[0].localidadeFinal.value, 'Localidade Final');" disabled="${desabilitarCampos}" />
								<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.forms[0].localidadeFinal.value, 275, 480, 'Informe Localidade Final',document.forms[0].setorComercialFinal);">
									<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Setor Comercial" /></a>
								<logic:present name="setorComercialFinalEncontrado" scope="request">
									<html:text property="nomeSetorComercialFinal" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:present>
								<logic:notPresent name="setorComercialFinalEncontrado" scope="request">
									<html:text property="nomeSetorComercialFinal" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
								</logic:notPresent>
								<a href="javascript:limparBorrachaDestino(2);">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
							</td>
						</tr>
						<tr>
							<td>
								<strong>Rota Final:</strong>
							</td>
							<td>
								<html:text maxlength="5" property="rotaFinal" size="5" onkeypress="javascript:mascara(this, mascaraInteiro);" disabled="${desabilitarCampos}" />
								<!-- onblur="javascript:validarRota(this, 6);" -->
							</td>
						</tr>
						<tr>
							<td>
								<strong>Sequencial Final da Rota:</strong>
							</td>
							<td>
								<html:text maxlength="5" property="sequencialRotaFinal" size="5" disabled="${desabilitarCampos}" />
							</td>
						</tr>
						<tr>
							<td>
								<strong>Rateio:</strong>
							</td>
							<td>
								<html:select property="rateio" disabled="${desabilitarCampos}" onchange="javascript:validarRateio();">
									<html:option value="<%="" + ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoRateio" labelProperty="descricao" property="id" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td>
								<strong>Faixa:</strong>
							</td>
							<td>
								<html:text maxlength="7" property="faixaInicial" size="7" disabled="${desabilitarCampos}" onkeypress="javascript:mascara(this, mascaraInteiro);" />
								&nbsp;&nbsp;
								a
								&nbsp;&nbsp;
								<html:text maxlength="7" property="faixaFinal" size="7" disabled="${desabilitarCampos}" onkeypress="javascript:mascara(this, mascaraInteiro);" />
							</td>
						</tr>
						<tr>
							<td>
								<strong>Números de imóveis vinculados:</strong>
							</td>
							<td>
								<html:text maxlength="5" property="imoveisVinculadosInicial" size="5" disabled="${desabilitarCampos}" onkeypress="javascript:mascara(this, mascaraInteiro);" />
								&nbsp;&nbsp;
								a
								&nbsp;&nbsp;
								<html:text maxlength="5" property="imoveisVinculadosFinal" size="5" disabled="${desabilitarCampos}" onkeypress="javascript:mascara(this, mascaraInteiro);" />
							</td>
						</tr>
						<tr>
							<td>
								<strong>Anormalidade de Leitura</strong>
							</td>
							<td>
								<html:select property="anormalidadeLeitura" disabled="${desabilitarCampos}">
									<html:option value="<%="" + ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoAnormalidadeLeitura" labelProperty="descricao" property="id" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td>
								<strong>Anormalidade de Consumo</strong>
							</td>
							<td>
								<html:select property="anormalidadeConsumo" disabled="${desabilitarCampos}">
									<html:option value="<%="" + ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoAnormalidadeConsumo" labelProperty="descricao" property="id" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td><strong> <font color="#FF0000"></font></strong></td>
							<td align="right">
								<div align="left">
									<strong><font color="#FF0000">*</font></strong>&nbsp;Campos Obrigat&oacute;rios
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" class="bottonRightCol" value="Cancelar" onclick="window.location.href='/gsan/telaPrincipal.do'" />
								<input type="button" class="bottonRightCol" value="Desfazer" onclick="javascript:limpar();" />
							</td>
							<td align="right">
								<input type="button" class="bottonRightCol" value="Filtrar" onclick="javascript:validarForm()" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%>
	</html:form>
</body>
<script language="JavaScript">
	document.forms[0].mesAnoFaturamento.focus();
</script>
</html:html>

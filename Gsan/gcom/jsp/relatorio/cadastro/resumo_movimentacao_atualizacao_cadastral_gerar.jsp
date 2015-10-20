<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro"%>
<%@ page import="gcom.cadastro.atualizacaocadastral.bean.DadosResumoMovimentoAtualizacaoCadastralHelper" isELIgnored="false"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="GerarResumoMovimentacaoAtualizacaoCadastralActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery_util.js"></script>

<script language="JavaScript">
//<!--
	var bCancel = false;

	function validateGerarResumoMovimentacaoAtualizacaoCadastralActionForm(form) {
		if (bCancel)
			return true;
		else
			return validateCaracterEspecial(form) && validateRequired(form) && validateDate(form) && validateInteger(form);
	}

	function caracteresespeciais() {
		this.aa = new Array("periodoAtualizacaoInicial", "Período de Atendimento Inicial possui caracteres especiais.", new Function("varName", " return this[varName];"));
		this.ab = new Array("periodoAtualizacaoFinal", "Período de Atendimento Final possui caracteres especiais.", new Function("varName", " return this[varName];"));
		this.ac = new Array("localidadeInicial", "Localidade possui caracteres especiais.", new Function("varName", " return this[varName];"));
		this.ae = new Array("setorComercialInicial", "Setor Comercial possui caracteres especiais.", new Function("varName", " return this[varName];"));
	}

	function required() {
		this.aa = new Array("idEmpresa", "Informe Empresa.", new Function("varName", " return this[varName];"));
	}

	function DateValidations() {
		this.aa = new Array("periodoAtualizacaoInicial", "Data de Início inválida.", new Function("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
		this.ab = new Array( "periodoAtualizacaoFinal", "Data Final inválida.", new Function("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	}

	function IntegerValidations() {
		this.aa = new Array("localidadeInicial", "Localidade deve ser númerico.", new Function( "varName", " return this[varName];"));
		this.ac = new Array("setorComercialInicial", "Setor Comercial deve ser númerico.", new Function("varName", " return this[varName];"));
	}

	function validarForm() {
		var form = document.forms[0];
		if (form.quadraSelecionados !== undefined && form.quadraSelecionados.value !== null && form.quadraSelecionados.options.length > 0) {
			enviarSelectMultiplo('GerarResumoMovimentacaoAtualizacaoCadastralActionForm','quadraSelecionados');
		}

		if (validateGerarResumoMovimentacaoAtualizacaoCadastralActionForm(form) && validarCampos()) {
			botaoAvancarTelaEspera('/gsan/gerarResumoMovimentacaoAtualizacaoCadastralAction.do');
		}
	}

	function validarCampos() {
		var form = document.forms[0];

		if (comparaData(form.periodoAtualizacaoInicial.value, ">", form.periodoAtualizacaoFinal.value )) {
			alert('Data incial maior que Data final');
			return false;
		}

		return true;
	}

	function limpar() {
		var form = document.forms[0];
		form.action = 'exibirGerarResumoMovimentacaoAtualizacaoCadastralAction.do?menu=sim';
		form.submit();
	}

	function carregarCadastrador() {
		var form = document.forms[0];
		form.action = 'exibirGerarResumoMovimentacaoAtualizacaoCadastralAction.do';
		form.submit();
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

	function replicarData() {
		var form = document.forms[0];
		form.periodoAtualizacaoFinal.value = form.periodoAtualizacaoInicial.value;
	}

	function limparOrigem(tipo) {
		var form = document.forms[0];

		switch (tipo) {
			case 1: //De localidade pra baixo
				form.nomeLocalidadeInicial.value = "";
				form.setorComercialInicial.value = "";
				form.idSetorComercial.value = "";
				form.nomeSetorComercialInicial.value = "";
				break;
			case 2: //De setor para baixo
				form.idSetorComercial.value = "";
				form.nomeSetorComercialInicial.value = "";
				break;
		}
	}

	function limparBorrachaOrigem(tipo) {
		var form = document.forms[0];

		switch (tipo) {
			case 1: //De localidade pra baixo
				form.localidadeInicial.value = "";
				form.nomeLocalidadeInicial.value = "";
				form.setorComercialInicial.value = "";
				form.idSetorComercial.value = "";
				form.nomeSetorComercialInicial.value = "";
				form.quadra.length = 0;
				form.quadraSelecionados.length = 0;
				break;
			case 2: //De setor para baixo
				form.setorComercialInicial.value = "";
				form.idSetorComercial.value = "";
				form.nomeSetorComercialInicial.value = "";
				form.quadra.length = 0;
				form.quadraSelecionados.length = 0;
				break;
		}
		
		form.action = 'exibirGerarResumoMovimentacaoAtualizacaoCadastralAction.do';
		form.submit();
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'localidadeOrigem') {
			form.localidadeInicial.value = codigoRegistro;
			form.nomeLocalidadeInicial.value = descricaoRegistro;
			form.nomeLocalidadeInicial.style.color = "#000000";
			form.setorComercialInicial.focus();
		}
	}

	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'setorComercialOrigem') {
			form.idSetorComercial.value = idRegistro;
			form.setorComercialInicial.value = codigoRegistro;
			form.nomeSetorComercialInicial.value = descricaoRegistro;
			form.nomeSetorComercialInicial.style.color = "#000000";
		}
		
		form.action = 'exibirGerarResumoMovimentacaoAtualizacaoCadastralAction.do';
		form.submit();
	}

	function carregarUnidadeNegocio() {
		var form = document.forms[0];
		
		form.action = 'exibirGerarResumoMovimentacaoAtualizacaoCadastralAction.do';
		form.submit();
	}

//-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

	<div id="formDiv">
		<html:form action="/gerarResumoMovimentacaoAtualizacaoCadastralAction"
			name="GerarResumoMovimentacaoAtualizacaoCadastralActionForm"
			type="gcom.gui.relatorio.cadastro.GerarResumoMovimentacaoAtualizacaoCadastralActionForm"
			method="post" onsubmit="return validateGerarResumoMovimentacaoAtualizacaoCadastralActionForm(this);">

			<%@ include file="/jsp/util/cabecalho.jsp"%>
			<%@ include file="/jsp/util/menu.jsp"%>

			<input type="hidden" name="tipoPesquisa" />

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
						</div>
					</td>

					<td width="620" valign="top" class="centercoltext">
						<table height="100%">
							<tr>
								<td></td>
							</tr>
						</table>

						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
								<td class="parabg">Resumo da Movimentação de Atualização Cadastral</td>
								<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
							</tr>
						</table>
						<p>&nbsp;</p>
						<table bordercolor="#000000" width="100%" cellspacing="0">
							<tr>
								<td colspan="2">
									<p>Para gerar o resumo da movimentação da atualização cadastral, informe os dados abaixo:</p>
								</td>
							</tr>
						</table>
						<table width="100%" border="0">
							<tr>
								<td height="10" colspan="3">
									<hr>
								</td>
							</tr>
						</table>
						<table bordercolor="#000000" width="100%" cellspacing="0">
							<tr>
								<td colspan="3">
									<table width="100%" border="0">
										<tr>
											<td style="width: 100px;">
												<strong>Empresa:<font color="#FF0000">*</font></strong>
											</td>
											<td align="left"><html:select property="idEmpresa" tabindex="1" onchange="carregarCadastrador();">
													<html:option value="-1">&nbsp;</html:option>
													<logic:present name="colecaoEmpresa" scope="request">
														<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
													</logic:present>
												</html:select>
											</td>
										</tr>
										<tr>
											<td height="10" colspan="3">
												<hr>
											</td>
										</tr>
										<tr>
											<td colspan="3">
												<table width="100%" border="0">
													<tr>
														<td><strong>Per&iacute;odo da Atualizaç&atilde;o:</strong></td>
														<td colspan="6">
															<html:text property="periodoAtualizacaoInicial" size="11"
																maxlength="10" tabindex="2"
																onkeyup="mascaraData(this, event);replicarData();"
																onkeypress="return isCampoNumerico(event);" />
															<a href="javascript:abrirCalendarioReplicando('GerarResumoMovimentacaoAtualizacaoCadastralActionForm', 'periodoAtualizacaoInicial', 'periodoAtualizacaoFinal' );">
																<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" title="Exibir Calendário" /></a>
															&nbsp;a&nbsp;
															<html:text property="periodoAtualizacaoFinal" size="11"
																maxlength="10" tabindex="3"
																onkeyup="mascaraData(this, event)"
																onkeypress="return isCampoNumerico(event);" />
															<a href="javascript:abrirCalendario('GerarResumoMovimentacaoAtualizacaoCadastralActionForm', 'periodoAtualizacaoFinal');">
																<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" title="Exibir Calendário" /></a>
															&nbsp;dd/mm/aaaa
														</td>
													</tr>
													<tr>
														<td>
															<strong>Ger&ecirc;ncia Regional:</strong>
														</td>
														<td>
															<html:select property="gerenciaRegional" style="width: 230px;" onchange="javascript:carregarUnidadeNegocio();">
																<html:option value="<%="" + ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
																<logic:present name="colecaoGerenciaRegional" scope="request">
																	<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
																</logic:present>
															</html:select>
														</td>
													</tr>
													<tr>
														<td><strong>Unidade de Neg&oacute;cio:</strong></td>
														<td>
															<strong>
																<html:select property="unidadeNegocio" style="width: 230px;">
																	<html:option value="<%="" + ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
																	<logic:present name="colecaoUnidadeNegocio" scope="request">
																		<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
																	</logic:present>
																</html:select>
															</strong>
														</td>
													</tr>
													<tr>
														<td height="10" colspan="3">
															<hr>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<strong>Informe os dados da inscriç&atilde;o</strong>
											</td>
										</tr>
										<tr>
											<td><strong>Localidade:</strong></td>
											<td>
												<html:text maxlength="3" tabindex="1"
													property="localidadeInicial" size="3"
													onkeyup="javascript:limparOrigem(1);"
													styleClass="tipoInteiro"
													onkeypress="javascript:limparOrigem(1);validaEnterComMensagem(event, 'exibirGerarResumoMovimentacaoAtualizacaoCadastralAction.do?objetoConsulta=1','localidadeInicial','Localidade');" />
												<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].localidadeInicial);limparOrigem(1);">
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
												<strong>Setor Comercial:</strong>
											</td>
											<td>
												<html:hidden property="idSetorComercial"/>
												<html:text maxlength="3" tabindex="1"
													property="setorComercialInicial" size="3"
													onkeyup="limparOrigem(2);"
													onkeypress="javascript:limparOrigem(2);validaEnterDependencia(event, 'exibirGerarResumoMovimentacaoAtualizacaoCadastralAction.do?objetoConsulta=2', document.forms[0].setorComercialInicial, document.forms[0].localidadeInicial.value, 'Localidade');return isCampoNumerico(event);"
													styleClass="tipoInteiro" />
												<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.forms[0].localidadeInicial.value , 275, 480, 'Informe Localidade',document.forms[0].setorComercialInicial);limparOrigem(2);">
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
											<td width="120">
												<strong>Quadra:</strong>
											</td>
											<td colspan="2">
											<table width="120" border=0 cellpadding=0 cellspacing=0 align="left">
												<tr>
													<td>
														<div align="left"><strong>Disponíveis</strong></div>
														<div id='disponiveis' style="overflow: auto; height: 120px;" onscroll="OnDivScroll(document.forms[0].quadra, 6);" >
															<html:select  styleId="quadra" property="quadra" style="width: 100px; height: 100px;" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('disponiveis'), 6);">
																<logic:present name="colecaoQuadra" scope="request">
																	<html:options collection="colecaoQuadra" labelProperty="setorQuadraFormatado" property="id"/>
																</logic:present>
															</html:select>
														</div>
													</td>
													<td width="5" valign="middle">
														<table width="50" align="center">
															<tr>
																<td align="center">
																	<input type="button" 
																		class="bottonRightCol"
																		onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('GerarResumoMovimentacaoAtualizacaoCadastralActionForm', 'quadra', 'quadraSelecionados');"
																		value=" &gt;&gt; ">
																</td>
															</tr>
															<tr>
																<td align="center">
																	<input type="button" 
																		class="bottonRightCol"
																		onclick="javascript:MoverDadosSelectMenu1PARAMenu2('GerarResumoMovimentacaoAtualizacaoCadastralActionForm', 'quadra', 'quadraSelecionados');"
																		value=" &nbsp;&gt;  ">
																</td>
															</tr>
															<tr>
																<td align="center">
																	<input type="button" 
																		class="bottonRightCol"
																		onclick="javascript:MoverDadosSelectMenu2PARAMenu1('GerarResumoMovimentacaoAtualizacaoCadastralActionForm', 'quadra', 'quadraSelecionados');"
																		value=" &nbsp;&lt;  ">
																</td>
															</tr>
															<tr>
																<td align="center">
																	<input type="button" 
																		class="bottonRightCol"
																		onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('GerarResumoMovimentacaoAtualizacaoCadastralActionForm', 'quadra', 'quadraSelecionados');"
																		value=" &lt;&lt; ">
																</td>
															</tr>
														</table>
													</td>			
													<td>
														<div align="left">
															<strong>Selecionados</strong>
														</div>
														<div id='selecionadas' style="overflow: auto; height: 120px;" onscroll="OnDivScroll(document.forms[0].quadraSelecionados, 6);" >
															<html:select styleId="quadraSelecionados" style="width: 100px; height: 100px;" property="quadraSelecionados" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('selecionadas'), 6);">
																<logic:present name="colecaoQuadraSelecionadas" scope="request">
																	<html:options collection="colecaoQuadraSelecionadas" labelProperty="setorQuadraFormatado" property="id"/>
																</logic:present>
															</html:select>
														</div>
													</td>
												</tr>
											</table>
											</td>
										</tr>
										<tr>
											<td height="10" colspan="3">
												<hr>
											</td>
										</tr>
										<tr>
											<td><strong>Cadastrador:</strong></td>
											<td>
												<html:select property="cadastrador" style="width: 95%;">
													<html:option value="<%="" + ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
													<logic:present name="colecaoCadastrador" scope="request">
														<html:options collection="colecaoCadastrador" labelProperty="nomeUsuario" property="id" />
													</logic:present>
												</html:select>
											</td>
										</tr>
										<tr>
											<td><strong>Analista:</strong></td>
											<td>
												<html:select property="analista" style="width: 95%;">
													<html:option value="<%="" + ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
													<logic:present name="colecaoUsuario" scope="request">
														<html:options collection="colecaoUsuario" labelProperty="nomeUsuario" property="id" />
													</logic:present>
												</html:select>
											</td>
										</tr>
										<tr>
											<td><strong>Inconsistência:</strong></td>
											<td>
												<html:select property="tipoInconsistencia" style="font-size:10px; width: 95%;">
													<html:option value="<%="" + ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
													<logic:present name="colecaoMensagem" scope="request">
														<html:options collection="colecaoMensagem" labelProperty="mensagem" property="id" />
													</logic:present>
												</html:select>
											</td>
										</tr>
										<tr>
											<td height="10" colspan="3">
												<hr>
											</td>
										</tr>
									</table>
									<logic:present name="colecaoDadosResumoMovimentoAtualizacaoCadastralHelper" scope="request">
										<table width="100%" cellpadding="0" cellspacing="0">
											<tr>
												<td colspan="2" bgcolor="#000000" height="2"></td>
											</tr>
											<tr>
												<td>
													<table width="100%" bgcolor="#99CCFF" border="0">
														<tr bordercolor="#000000">
															<td width="20" bgcolor="#90c7fc" align="center">
																<strong>Selecione<font color="#FF0000">*</font></strong>
															</td>
															<td width="300" bordercolor="#000000" bgcolor="#90c7fc" align="center">
																<div align="center">
																	<strong>RELATÓRIOS</strong>
																</div>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td height="120">
													<div style="width: 100%; height: 100%;">
														<table width="100%" bgcolor="#99CCFF">
															<%
																int cont = 0;
															%>
															<logic:iterate name="colecaoDadosResumoMovimentoAtualizacaoCadastralHelper" id="dadosMovimento" scope="request" type="DadosResumoMovimentoAtualizacaoCadastralHelper">
																<%
																	cont = cont + 1;
																		if (cont % 2 == 0) {
																%>
																<tr bgcolor="#cbe5fe">
																	<%
																		} else {
																	%>
																</tr>
																<tr bgcolor="#FFFFFF">
																	<%
																		}
																	%>
																	<td width="17%" align="center">
																		<logic:equal name="IdsRegistroSelecionado" value="${dadosMovimento.id}" scope="request">
																			<input type="radio" name="idsRegistro" checked="checked" value="${dadosMovimento.id}" />
																		</logic:equal>
																		<logic:notEqual name="IdsRegistroSelecionado" value="${dadosMovimento.id}" scope="request">
																			<input type="radio" name="idsRegistro" value="${dadosMovimento.id}" />
																		</logic:notEqual>
																	</td>
																	<td width="83%" align="left">
																		<bean:write name="dadosMovimento" property="nomeRelatorio" />
																	</td>
																</tr>
															</logic:iterate>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</logic:present>
									<table>
										<tr>
											<td height="10" colspan="3">
												<hr>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="19"><strong> <font color="#FF0000"></font></strong></td>
								<td align="right">
									<div align="left">
										<strong><font color="#FF0000">*</font></strong>&nbsp;Campos obrigat&oacute;rios
									</div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="left" colspan="2"><input type="button"
									class="bottonRightCol" value="Limpar"
									onclick="javascript:limpar();" /> <input type="button"
									name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
								</td>
								<td align="right"><input type="button" name="Button"
									class="bottonRightCol" value="Gerar"
									onClick="javascript:validarForm();" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<%@ include file="/jsp/util/rodape.jsp"%>
		</html:form>
	</div>
</body>
</html:html>
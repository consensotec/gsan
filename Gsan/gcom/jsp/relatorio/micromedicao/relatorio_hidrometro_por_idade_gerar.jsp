<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.util.Util"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
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
<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
<html:javascript staticJavascript="false"
	formName="GerarRelatorioHidrometroPorIdadeActionForm" />
	
<style>
	#tabelaFaixasIdade tbody tr:nth-child(odd) {
	   background-color: #FFF;
	   text-align: center;
	}

	#tabelaFaixasIdade tbody tr:nth-child(even) {
	   background-color: #cbe5fe;
	    text-align: center;
	}
</style>

<script>


	function replicarCampoEconomia(campo) {
		var form = document.forms[0];
		form.quantidadeEconomiaFinal.value = campo.value;
	}

	function replicarCampoConsumoMes(campo) {
		var form = document.forms[0];
		form.faixaConsumoMesFinal.value = campo.value;
	}

	function replicarCampoConsumoMedio(campo) {
		var form = document.forms[0];
		form.faixaConsumoMedioFinal.value = campo.value;
	}
	
	function recuperarDadosPopupAdicionarFaixa(descricao,inicio,fim) {
		console.log(descricao);
		console.log(inicio);
		console.log(fim);

		var row = "<tr>";
		row += "<td> <img src=/gsan/imagens/Error.gif /></td>";
		row += "<td>" + descricao +"</td>";
		row += "<td>" + inicio +"</td>";
		row += "<td>" + fim +"</td>";
		row += "</tr>"
		$('#tabelaFaixasIdade tbody').append(row);
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
	    var form = document.GerarRelatorioHidrometroPorIdadeActionForm;
	
	    if (tipoConsulta == 'localidade') {
	      form.codigoLocalidade.value = codigoRegistro;
	      form.descricaoLocalidade.value = descricaoRegistro;
	      form.descricaoLocalidade.style.color = "#000000";
	    }

	    if (tipoConsulta == 'setorComercial') {
	      form.idSetorComercial.value = codigoRegistro;
	      form.descricaoSetorComercial.value = descricaoRegistro;
	      form.descricaoSetorComercial.style.color = "#000000";
		}
	
	 }

	function reloadForm(){
  		var form = document.forms[0];
  	
  		form.action='exibirGerarRelatorioHidrometroPorIdadeAction.do';
	    form.submit();
  	}

	function limparLocalidade() {
	    var form = document.GerarRelatorioHidrometroPorIdadeActionForm;
	    
	      form.codigoLocalidade.value = '';
	      form.descricaoLocalidade.value = '';
	}

	function limparSetorComercial() {
	    var form = document.GerarRelatorioHidrometroPorIdadeActionForm;
	    
	      form.idSetorComercial.value = '';
	      form.descricaoSetorComercial.value = '';
	}

	function limparCampos(opcaoTotalizacao){
		var form = document.forms[0];
		
		if (opcaoTotalizacao.value != 'localidade'){
			limparLocalidade() ;
		}
		
		if (opcaoTotalizacao.value != 'gerenciaRegional'){
			form.gerenciaRegionalId.value = "-1";			
		}
				
		if (opcaoTotalizacao.value != 'unidadeNegocio'){
			form.unidadeNegocioId.value = "-1" ;			
		}	
	
	}

	function validateGerarRelatorioHidrometroPorIdadeActionForm(form) {
		return true;
	}

	
	function validarForm() {
		var form = document.GerarRelatorioHidrometroPorIdadeActionForm;

		if (validateGerarRelatorioHidrometroPorIdadeActionForm(form)) {

			var opcao = '';

			for (i = 0; i < form.opcaoTotalizacao.length; i++) {
				if (form.opcaoTotalizacao[i].checked) {
					opcao = form.opcaoTotalizacao[i].value;
				}
			}

			if (opcao == 'localidade') {
				if (form.codigoLocalidade.value == '') {
					alert('Preencha o código da localidade');
					return false;
				}
			}else if (opcao == '') {
				alert('Preencha a opção de totalização');
				return false;
			} 
			if (form.opcaoRelatorio.value == '') {
				alert('Preencha a opção de relatório');
			}else{
				submeterFormPadrao(form);
			}	
				
		}
	}

	function habilitaQuadra(){
		var form = document.forms[0];
				
		if (form.opcaoTotalizacao.value == 'localidade') {
			if (form.idRota.value !== '') {
				form.idQuadra.disabled = true;
				form.idRota.disabled = false;
			} else if (form.idQuadra.value !== '') {
				form.idRota.disabled = true;
				form.idQuadra.disabled = false;
			} else {
				form.idRota.disabled = false;
				form.idQuadra.disabled = false;
			}
		}else{
			form.idRota.disabled = true;
			form.idQuadra.disabled = true;
		}
	}

	function habilitaDesabilitaFormulario(limpaSessao) {
		var form = document.forms[0];

		var opcaoTotalizacao = form.opcaoTotalizacao.value;
		
		form.unidadeNegocioId.disabled = true;
		form.gerenciaRegionalId.disabled = true;

		form.idRota.disabled = true;
		form.idQuadra.disabled = true;
		
		if (opcaoTotalizacao == 'localidade') {
			habilitaDesabilitaLocalidade(false);
			habilitaDesabilitaSetorComercial(false);
			form.idRota.disabled = false;
			form.idQuadra.disabled = false;
			form.opcaoRelatorio[0].disabled = false;	
			form.opcaoRelatorio[1].checked = false;		
			form.opcaoRelatorio[0].checked = false;	
		}else if (opcaoTotalizacao == 'unidadeNegocio'){
			habilitaDesabilitaLocalidade(true);
			habilitaDesabilitaSetorComercial(true);
			form.unidadeNegocioId.disabled = false;
			form.gerenciaRegionalId.disabled = true;
			form.idRota.value ='';
			form.idQuadra.value ='';
			form.codigoLocalidade.value ='';
			form.idSetorComercial.value ='';
			form.descricaoSetorComercial.value ='';
			form.opcaoRelatorio[0].disabled = true;
		}else if (opcaoTotalizacao == 'gerenciaRegional'){
			habilitaDesabilitaLocalidade(true);
			habilitaDesabilitaSetorComercial(true);
			form.unidadeNegocioId.disabled = true;
			form.gerenciaRegionalId.disabled = false;
			form.idRota.value ='';
			form.idQuadra.value ='';
			form.codigoLocalidade.value ='';
			form.idSetorComercial.value ='';
			form.descricaoSetorComercial.value ='';
			form.opcaoRelatorio[0].disabled = true;
		}else {
			habilitaDesabilitaLocalidade(true);
			habilitaDesabilitaSetorComercial(true);
			form.idRota.disabled = true;
			form.idQuadra.disabled = true;
			form.opcaoRelatorio[0].disabled = true;
			form.opcaoRelatorio[1].checked = false;
		}
	}

	function limparSemOpcaoTotalizacao() {
		var form = document.forms[0];

		limparLocalidade();
		limparSetorComercial();

		form.opcaoTotalizacao = "";

		form.action = 'exibirGerarRelatorioHidrometroPorIdadeAction.do?menu=sim';
		form.submit();

	}

	function limparLocalidade() {
		var form = document.forms[0];

		form.codigoLocalidade.value = "";
		form.descricaoLocalidade.value = "";
	}

	function limparSetorComercial() {
		var form = document.forms[0];

		form.idSetorComercial.value = "";
		form.descricaoSetorComercial.value = "";
	}

	function habilitaDesabilitaLocalidade(valor) {
		var form = document.forms[0];
		if (valor) {
			form.codigoLocalidade.value = "";
			form.descricaoLocalidade.value = "";
		}
		form.codigoLocalidade.disabled = valor;

	}

	function habilitaDesabilitaSetorComercial(valor) {
		var form = document.forms[0];
		if (valor) {
			form.idSetorComercial.value = "";
			form.descricaoSetorComercial.value = "";
			form.idRota.value = "";
			form.idQuadra.value = "";
		}
		form.idSetorComercial.disabled = valor;
		form.idRota.disabled = valor;
		form.idQuadra.disabled = valor;

	}

	/* Chama Popup */
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

	function chamarPesquisaSetorComercialInicial() {
		abrirPopupDependencia(
				'exibirPesquisarSetorComercialAction.do?idLocalidade='
						+ document.forms[0].codigoLocalidade.value
						+ '&tipo=idSetorComercial&indicadorUsoTodos=1',
				document.forms[0].codigoLocalidade.value, 'Localidade', 400,
				800);
	}

	function chamarPopupAdicionar(url, tipo, objeto, codigoObjeto, altura,
			largura, msg, objetoRelacionado) {

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

	function verificarFaixa(aux) {
		var form = document.forms[0];
		if (aux != undefined) {
			if (aux.toUpperCase() == 'SIM') {
				form.icInformouFaixa.value = 'SIM';
			}
		}
	}
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:habilitaDesabilitaFormulario(false);habilitaQuadra();">
	<html:form action="/gerarRelatorioHidrometroPorIdadeAction.do"
		name="GerarRelatorioHidrometroPorIdadeActionForm"
		type="gcom.gui.relatorio.micromedicao.GerarRelatorioHidrometroPorIdadeActionForm"
		method="post">

		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>

		<table width="770" border="0" cellpadding="0" cellspacing="5">
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
					</div></td>
				<td valign="top" class="centercoltext">
					<table height="100%">
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img
								src="<bean:message key="caminho.imagens"/>parahead_left.gif"
								border="0" />
							</td>
							<td class="parabg">Relátorio de Hidrômetros por Faixa de Idade</td>
							<td width="11" valign="top"><img
								src="<bean:message key="caminho.imagens"/>parahead_right.gif"
								border="0" />
							</td>
						</tr>
					</table>
					<table width="100%" border="0">
						<tr>
							<td colspan="3">Para gerar o relat&oacute;rio de hidrometros
								por idade, informe os dados abaixo:</td>
						</tr>

						<tr>
							<td><strong>Opção de Totalização:<font
									color="#FF0000">*</font> </strong>
							</td>
							<td colspan="2" align="left"><html:radio
									property="opcaoTotalizacao" 
									value="estado"
									onclick="limparCampos(this);"
									onchange="habilitaDesabilitaFormulario(true);" /> <strong>Estado</strong>
							</td>
						</tr>

						<tr>
							<td><strong> <font color="#FF0000"></font> </strong>
							</td>
							<td colspan="2" align="left"><strong> <html:radio
										property="opcaoTotalizacao" 
										value="estadoGerencia"
										onclick="limparCampos(this);"
										onchange="habilitaDesabilitaFormulario(true);" />Estado
									por Gerência Regional</strong></td>
						</tr>

						<tr>
							<td><strong> <font color="#FF0000"></font> </strong>
							</td>
							<td colspan="2" align="left"><strong> <html:radio
										property="opcaoTotalizacao" 
										value="estadoUnidadeNegocio"
										onclick="limparCampos(this);"
										onchange="habilitaDesabilitaFormulario(true);" />Estado
									por Unidade de Negócio</strong></td>
						</tr>

						<tr>
							<td><strong> <font color="#FF0000"></font> </strong>
							</td>
							<td colspan="2" align="left"><strong><html:radio
										property="opcaoTotalizacao" 
										value="estadoLocalidade"
										onclick="limparCampos(this);"
										onchange="habilitaDesabilitaFormulario(true);" />Estado
									por Localidade</strong></td>
						</tr>
						<tr>
							<td><strong> <font color="#FF0000"></font> </strong>
							</td>
							<td width="36%" align="left"><html:radio
									property="opcaoTotalizacao" 
									value="gerenciaRegional"
									onclick="limparCampos(this);"
									onchange="habilitaDesabilitaFormulario(true);" /> <strong>Ger&ecirc;ncia
									Regional</strong>
							</td>
							<td width="38%" align="left"><strong><strong>
										<html:select property="gerenciaRegionalId">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="colecaoGerenciaRegional"
												labelProperty="nome" 
												property="id" />
										</html:select> </strong>
							</td>
						</tr>
						<tr>
							<td><strong> <font color="#FF0000"></font> </strong>
							</td>
							<td width="36%" align="left"><html:radio
									property="opcaoTotalizacao" 
									value="unidadeNegocio"
									onclick="limparCampos(this);"
									onchange="habilitaDesabilitaFormulario(true);" /> <strong>Unidade
									de Negócio</strong>
							</td>
							<td width="38%" align="left"><strong><strong>
										<html:select property="unidadeNegocioId">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="colecaoUnidadeNegocio"
												labelProperty="nome" 
												property="id" />
										</html:select> </strong>
							</td>
						</tr>


						<tr>
							<td><strong> <font color="#FF0000"></font> </strong></td>
							<td align="left"><html:radio property="opcaoTotalizacao"
									value="localidade" 
									onclick="limparCampos(this);"
									onchange="habilitaDesabilitaFormulario(true);" /><strong>Localidade</strong>
							</td>
							<td align="left"><html:text property="codigoLocalidade"
									size="4" 
									maxlength="3"
									onkeydown="validaEnter(event,'exibirGerarRelatorioHidrometroPorIdadeAction.do?pesquisarLocalidade=OK', 'codigoLocalidade');" 
									onkeypress="javascript:return isCampoNumerico(event);"/>
									
								<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								width="23"
								height="21"
								border="0"
								onclick="chamarPopup('exibirPesquisarLocalidadeAction.do','codigoLocalidade', null, null,800, 600, '', document.forms[0].codigoLocalidade);">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								width="23" 
								height="21" 
								onclick="javascript:limparLocalidade();">
							</td>
						</tr>

						<tr>
							<td><strong> <font color="#FF0000"></font> </strong></td>
							<td align="left"><strong> </strong></td>
							<td align="left"><logic:present name="localidadeEncontrada"
									scope="session">
									<html:text property="descricaoLocalidade" 
									size="40"
									maxlength="40" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:present> 
								<logic:notPresent name="localidadeEncontrada" scope="session">
									<html:text property="descricaoLocalidade" size="40"
										maxlength="40"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: red" />
								</logic:notPresent>
							</td>
						</tr>

						<tr>
							<td><strong> <font color="#FF0000"></font> </strong>
							</td>
							<td align="right"><strong>Setor Comercial:</strong>
							</td>
							<td align="left"><html:text property="idSetorComercial"
									size="4"
									maxlength="3"
									onkeydown="validaEnter(event, 'exibirGerarRelatorioHidrometroPorIdadeAction.do?pesquisarSetorComercial=OK', 'idSetorComercial');" 
									onkeypress="javascript:return isCampoNumerico(event);"/>
								<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								width="23"
								height="21"
								border="0"															
								onclick="javascript:chamarPesquisaSetorComercialInicial();">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								width="23"
								height="21"
								onclick="javascript:limparSetorComercial();">
							</td>
						</tr>

						<tr>
							<td><strong> <font color="#FF0000"></font> </strong>
							</td>
							<td align="left"><strong> </strong>
							</td>
							<td align="left"><logic:present
									name="setorComercialEncontrado" scope="session">
									<html:text property="descricaoSetorComercial" size="40"
										maxlength="40"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:present> 
								<logic:notPresent name="setorComercialEncontrado"
									scope="session">
									<html:text property="descricaoSetorComercial"
									size="40"		
									maxlength="40"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
								</logic:notPresent>
							</td>
						</tr>

						<tr>
							<td><strong> <font color="#FF0000"></font> </strong>
							</td>
							<td align="right"><strong>Rota:</strong>
							</td>
							<td><html:text maxlength="4" 
									property="idRota" 
									size="4"
									onkeypress="javascript:return isCampoNumerico(event);" 
									onblur="habilitaQuadra();"/>
							</td>
						</tr>

						<tr>
							<td><strong> <font color="#FF0000"></font> </strong>
							</td>
							<td align="right"><strong>Quadra:</strong>
							</td>
							<td><html:text maxlength="4" property="idQuadra" size="4"
									onkeypress="javascript:return isCampoNumerico(event);"
									onblur="habilitaQuadra();" />
							</td>
						</tr>

						<tr>
							<td align="left" ><strong>Opção de
									Relatório:<font	color="#FF0000">*</font></strong>
							</td>
							<td><html:radio property="opcaoRelatorio"
										value="1" /><strong>Analítico</strong> <html:radio
										property="opcaoRelatorio" value="2" /><strong>Sintético</strong>
							</td>
						</tr>

						<tr>
							<td height="10" colspan="3">
								<div align="right">
									<hr>
								</div>
								<div align="right"></div>
							</td>
						</tr>

						<tr>
							<td colspan="2"><strong> Tempo de Instalação do Hidrometro:<font
									color="#FF0000">*</font>
							</strong>
							</td>
							<td  colspan="3" align="right"><input name="Button" type="button"
								class="bottonRightCol" value="Adicionar" align="right"
								onclick="javascript:abrirPopup('exibirAdicionarFaixaIdadeAction.do?limpar=sim');" />
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<table width="100%" bgcolor="#99CCFF" id="tabelaFaixasIdade">
									<tr bordercolor="#000000">
										<td align="center" width="10%" bgcolor="#90c7fc" rowspan="2"><strong>Remover</strong>
										</td>
										<td align="center" width="40%" bgcolor="#90c7fc" rowspan="2"><strong>Descrição
												da Faixa</strong>
										</td>
										<td align="center" width="50%" bgcolor="#90c7fc" colspan="2"><strong>Valores
												da Faixa</strong>
										</td>
									</tr>
									<tr bordercolor="#000000">
										<td align="center" width="25%" bgcolor="#90c7fc"><strong>Inicial</strong>
										</td>
										<td align="center" width="25%" bgcolor="#90c7fc"><strong>Final</strong>
										</td>
									</tr>
									<%--Esquema de paginação--%>

									<logic:present name="colecaoHidrometroFaixaIdade"
										scope="session">

										<logic:iterate id="faixaHidrometro"
											name="colecaoHidrometroFaixaIdade">

											<tr>
												<td width="10%">
													<div align="center">
														<font color="#333333"> <img width="14" height="14"
															border="0"
															src="<bean:message key="caminho.imagens"/>Error.gif"
															onclick="javascript:if(confirm('Confirma remoção?')){redirecionarSubmitSemUpperCase('removerHidrometroFaixaAction.do?descricao=<bean:write name="faixaHidrometro" property="descricao"/>&inicial=<bean:write name="faixaHidrometro" property="valorInicial"/>&final=<bean:write name="faixaHidrometro" property="valorFinal"/>');}" />
															
																													
														</font>
													</div></td>
												<td width="40%">
													<div align="center">
														<bean:write name="faixaHidrometro" property="descricao" />
													</div></td>
												<td width="25%">
													<div align="center">
														<bean:write name="faixaHidrometro" property="valorInicial" />
													</div></td>
												<td width="25%">
													<div align="center">
														<bean:write name="faixaHidrometro" property="valorFinal" />
													</div></td>
											</tr>
										</logic:iterate>
									</logic:present>

								</table></td>
						</tr>
						<tr>
							<td height="10" colspan="3">
								<div align="right">
									<hr>
								</div>
								<div align="right"></div></td>
						</tr>

						<tr>
							<td width="20%"><strong>Perfil do Imóvel:</strong></td>
							<td colspan="2" align="left"><html:select
									property="imovelPerfil" multiple="true" size="5" tabindex="15"
									style="width:300px;">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoImovelPerfil" property="id"
										labelProperty="descricao" />
								</html:select></td>
						</tr>

						<tr>
							<td width="20%"><strong>Categoria:</strong></td>
							<td colspan="2" align="left"><html:select
									property="categoria" multiple="true" size="5" tabindex="15"
									style="width:300px;" onchange="reloadForm();">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoCategoria" property="id"
										labelProperty="descricao" />
								</html:select></td>
						</tr>

						<tr>
							<td width="20%"><strong>Subcategoria:</strong></td>
							<td colspan="2" align="left"><html:select
									property="subCategoria" multiple="true" size="5" tabindex="15"
									style="width:406px;">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoSubCategoria" property="id"
										labelProperty="descricao" />
								</html:select></td>
						</tr>

						<tr>
							<td width="20%"><strong>Intervalo de Quantidade de
									Economias </strong></td>
							<td colspan="2" align="left"><html:text maxlength="4"
									property="quantidadeEconomiaInicial" size="4"
									onkeypress="javascript:return isCampoNumerico(event);"
									onkeyup="replicarCampoEconomia(this);" /><strong>&nbsp;a&nbsp;</strong>
								<html:text maxlength="4" property="quantidadeEconomiaFinal"
									size="4" onkeypress="javascript:return isCampoNumerico(event);" />
							</td>
						</tr>

						<tr>
							<td height="10" colspan="3">
								<div align="right">
									<hr>
								</div>
								<div align="right"></div>
							</td>
						</tr>

						<tr>
							<td width="20%"><strong>Capacidade:</strong></td>
							<td colspan="2" align="left"><html:select
									property="capacidade" multiple="true" size="5" tabindex="15"
									style="width:300px;">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoCapacidade" property="id"
										labelProperty="descricao" />
								</html:select></td>
						</tr>

						<tr>
							<td width="20%"><strong>Marca:</strong></td>
							<td colspan="2" align="left"><html:select property="marca"
									multiple="true" size="5" tabindex="15" style="width:300px;">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoMarca" property="id"
										labelProperty="descricao" />
								</html:select></td>
						</tr>

						<tr>
							<td width="20%"><strong>Anormalidade:</strong></td>
							<td colspan="2" align="left"><html:select
									property="anormalidade" multiple="true" size="5" tabindex="15"
									style="width:300px;">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoAnormalidade" property="id"
										labelProperty="descricao" />
								</html:select></td>
						</tr>

						<tr>
							<td width="20%"><strong>Leitura Mínima:</strong></td>
							<td colspan="2" align="left"><html:text maxlength="8"
									property="leituraMinima" size="8"
									onkeypress="javascript:return isCampoNumerico(event);" /></td>
						</tr>

						<tr>
							<td width="20%"><strong>Faixa de Consumo Mês:</strong></td>
							<td colspan="2" align="left"><html:text maxlength="8"
									property="faixaConsumoMesInical" size="8"
									onkeypress="javascript:return isCampoNumerico(event);"
									onkeyup="replicarCampoConsumoMes(this);"/><strong>&nbsp;a&nbsp;</strong>
								<html:text maxlength="8" property="faixaConsumoMesFinal"
									size="8" onkeypress="javascript:return isCampoNumerico(event);" />
							</td>
						</tr>

						<tr>
							<td width="20%"><strong>Faixa de Consumo Médio:</strong></td>
							<td colspan="2" align="left"><html:text maxlength="8"
									property="faixaConsumoMedioInicial" size="8"
									onkeypress="javascript:return isCampoNumerico(event);"
									onkeyup="replicarCampoConsumoMedio(this);" /><strong>&nbsp;a&nbsp;</strong>
								<html:text maxlength="8" property="faixaConsumoMedioFinal"
									size="8" onkeypress="javascript:return isCampoNumerico(event);" />
							</td>
						</tr>

						<tr>
							<td>&nbsp;</td>
							<td colspan="2" align="left"><font color="#FF0000">*</font>
								Campo Obrigat&oacute;rio</td>
						</tr>

						<tr>
							<td height="10" colspan="3">
								<div align="right">
									<hr>
								</div>
								<div align="right"></div>
							</td>
						</tr>

						<tr>
							<td height="24" colspan="2"><input type="button"
								class="bottonRightCol" value="Desfazer"
								onclick="javascript:window.location.href='/gsan/exibirGerarRelatorioHidrometroPorIdadeAction.do?menu=sim'" /> <font color="#FF0000">
									<input type="button" name="ButtonCancelar"
									class="bottonRightCol" value="Cancelar"
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</font></td>

							<td align="right"><input type="button" name="Button"
								class="bottonRightCol" value="Gerar Relatório"
								onclick="validarForm();" /></td>

						</tr>

						<tr>
							<td><p>&nbsp;</p></td>
							<td colspan="2"></td>
						</tr>
					</table>
					<p>&nbsp;</p>
				</td>
			</tr>
		</table>
		<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
		</tr>
	</html:form>
</body>
</html:html>

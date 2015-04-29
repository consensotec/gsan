<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gsan.cobranca.CobrancaSituacaoHistorico"%>
<%@ page import="gsan.faturamento.FaturamentoSituacaoHistorico"%>
<%@ page import="gsan.cadastro.imovel.ImovelRamoAtividade"%>
<%@ page import="gsan.util.Util"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gsan.seguranca.acesso.OperacaoEfetuada"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<style>/* O z-index do div#mask deve ser menor que do div#boxes e do div.window */
#mask {
	position: absolute;
	z-index: 9000;
	/*background-color:#000;*/
	display: none;
}

#boxes .window {
	position: absolute;
	width: 440px;
	height: 200px;
	display: none;
	z-index: 9999;
	padding: 20px;
}

/* Personalize a janela modal aqui. Voc� pode adicionar uma imagem de fundo. */
#boxes #dialog {
	width: 375px;
	height: 150px;
	/*background-color:#CBE5FE;*/
}

#boxes #dialog1 {
	width: 375px;
	height: 150px;
	/*background-color:#CBE5FE;*/
}

#boxes {
	background-color: #CBE5FE;
}
/* posiciona o link para fechar a janela */
.close {
	display: block;
	text-align: right;
}
</style>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<html:javascript staticJavascript="false" formName="ConsultarImovelActionForm" />
<script type="text/javascript">
	var janelaAberta = false;
	$(document).ready(function() {

		//seleciona os elementos a com atributo name="modal"
		$('a[name=modal]').click(function(e) {
			if (janelaAberta == false) {
				janelaAberta = true;
				//cancela o comportamento padr�o do link
				e.preventDefault();
				//armazena o atributo href do link
				var id = $(this).attr('href');

				//armazena a largura e a altura da tela
				var maskHeight = $(document).height();
				var maskWidth = $(window).width();

				//Define largura e altura do div#mask iguais �s dimens�oes da tela
				$('#mask').css({
					'width' : maskWidth,
					'height' : maskHeight
				});

				//efeito de transi��o
				$('#mask').fadeIn(1000);
				$('#mask').fadeTo("slow", 0.8);

				//armazena a largura e a altura da janela
				var winH = $(window).height();
				var winW = $(window).width();
				//centraliza na tela a janela popup
				$(id).css('top', winH / 2 - $(id).height() / 2);
				$(id).css('left', winW / 2 - $(id).width() / 2);
				//efeito de transi��o
				$(id).fadeIn(2000);
			} else {
				alert('Apenas uma janela pode ser aberta');
			}
		});
		//se o bot�o esc for pressionado
		$(window).keydown(function(g) {
			if (g.keyCode == 27) {
				$('#mask, .window').hide();
				janelaAberta = false;
			}
		});

		//se o bot�o fechar for clicado
		$('.window .close').click(function(e) {
			janelaAberta = false;
			//cancela o comportamento padr�o do link
			e.preventDefault();
			$('#mask, .window').hide();
		});

		//se div#mask for clicado
		$('#mask').click(function() {
			$(this).hide();
			$('.window').hide();
		});
		$('a').click(function(e) {
			var nome = $(this).attr('name');
			if (janelaAberta == true && nome != 'modal') {
				e.preventDefault();
				alert('Apenas uma janela pode ser aberta');
			}
		});
		$('#boxes').click(function() {
			if (janelaAberta == true) {
				janelaAberta = false;
				$('#mask, .window').hide();
			}
		});
		/*$('input[type=button]').click(function(){				
			$('#mask, .window').hide();
			janelaAberta = false;
		});	*/
	});
</script>
<script language="JavaScript">
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		consultarImovelAjax();
		if (tipoConsulta == 'imovel') {
			form.idImovelDadosComplementares.value = codigoRegistro;
			form.matriculaImovelDadosComplementares.value = descricaoRegistro;
			form.matriculaImovelDadosComplementares.style.color = "#000000";
			form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelDadosComplementaresAction&indicadorNovo=OK'
			form.submit();
		}
	}

	function limparForm() {
		var form = document.forms[0];
		form.idImovelDadosComplementares.value = "";
		consultarImovelAjax();

		form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelDadosComplementaresAction&limparForm=OK'
		form.submit();
	}

	function pesquisarHistoricoImovel(){
		var form = document.forms[0];
	   	if (form.idImovelDadosComplementares.value == "") {
			alert("Informe o Im�vel");
			return false;
		}

		form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelDadosComplementaresAction&pesquisarHistorico=OK'
		form.submit();
	}

	function teste2(id) {
		alert('teste');
		abrirPopup('exibirFotoOcorrenciaCadastroConsultarImovelAction.do?id='
				+ id, 600, 800);
	}

	function consultarMatricula(matricula) {
		var form = document.forms[0];
		form.action = 'consultarImovelWizardAction.do?destino=5&action=exibirConsultarImovelDebitosAction&matriculaAssociada='
				+ matricula;
		form.submit();
	}

	function verificaExibicaoRelatorioDadosComplementares() {
		var form = document.forms[0];

		if (form.idImovelDadosComplementares.value.length > 0
				&& form.matriculaImovelDadosComplementares.value.length > 0) {
			toggleBoxCaminho('demodiv', 1,
					'gerarRelatorioDadosComplementaresImovelAction.do');
		} else {
			alert('Informe Im�vel');
		}

	}

	function habilitaMatricula() {
		var form = document.forms[0];

		if (form.idImovelDadosComplementares.value != null
				&& form.matriculaImovelDadosComplementares.value != null
				&& form.matriculaImovelDadosComplementares.value != ""
				&& form.matriculaImovelDadosComplementares.value != "IM�VEL INEXISTENTE") {

			form.idImovelDadosComplementares.disabled = true;
		} else {
			form.idImovelDadosComplementares.disabled = false;
		}
	}

	function pesquisarImovel() {
		var form = document.forms[0];

		if (form.idImovelDadosComplementares.disabled) {
			alert("Para realizar uma pesquisa de im�vel � necess�rio apagar o im�vel atual.")
		} else {
			abrirPopup('exibirPesquisarImovelAction.do', 400, 800)
		}
	}

	function consultarImovelAjax() {
		var form = document.forms[0];

		if (form.idImovelDadosComplementares != null
				&& form.idImovelDadosComplementares.value != "") {
			$.ajax({
				type : "POST",
				url : "consultarImovelAjaxAction.do?idImovel="
						+ form.idImovelDadosComplementares.value,
				data : "",
				success : function(msg) {
					if (msg != null && msg != "") {
						alert(msg);
						msg = "";
					}
				}
			});
		} else {
			$.ajax({
				type : "POST",
				url : "consultarImovelAjaxAction.do?desfazer=sim",
				data : "",
				success : function(msg) {

				}
			});

		}

	}
</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:habilitaMatricula();setarFoco('idImovelDadosComplementares');consultarImovelAjax();">

	<html:form action="/exibirConsultarImovelAction.do"
		name="ConsultarImovelActionForm"
		type="gsan.gui.cadastro.imovel.ConsultarImovelActionForm"
		method="post" onsubmit="return validateConsultarImovelActionForm(this);">

		<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_consulta.jsp?numeroPagina=2" />
		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>

		<html:hidden property="indicadorValidaCPFCNPJ" />
		<html:hidden property="indicadorClienteCPFCNPJValidado" />

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
				<!--In�cio Tabela Reference a P�gina��o da Tela de Processo-->
				<td class="centercoltext" valign="top">
					<p>&nbsp;</p>
					<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="11"><img src="imagens/parahead_left.gif" border="0"></td>
							<td class="parabg">&nbsp;</td>
							<td valign="top" width="11"><img src="imagens/parahead_right.gif" border="0"></td>
						</tr>
					</table> <!--Fim Tabela Reference a P�gina��o da Tela de Processo-->
					<p>&nbsp;</p>
					<table border="0" width="100%">
						<tr>
							<td colspan="4">
								<table align="center" bgcolor="#99ccff" border="0" width="100%">
									<tr>
										<td>
											<table width="100%" align="center" bgcolor="#99CCFF" border="0">
												<tr>
													<td align="left" width="4%">
														<logic:equal name="ConsultarImovelActionForm" property="indicadorValidaCPFCNPJ" value="1">
															<logic:equal name="ConsultarImovelActionForm" property="indicadorClienteCPFCNPJValidado" value="1">
																<img border="0" width="25" height="25" src="<bean:message key="caminho.imagens"/>informacao.gif" onmouseover="this.T_BGCOLOR='whitesmoke';this.T_FONTCOLOR='green';this.T_LEFT=true;return escape( '${ConsultarImovelActionForm.hint2}' ); " />
															</logic:equal>
															<logic:equal name="ConsultarImovelActionForm" property="indicadorClienteCPFCNPJValidado" value="2">
																<img border="0" width="25" height="25" src="<bean:message key="caminho.imagens"/>informacao.gif" onmouseover="this.T_BGCOLOR='whitesmoke';this.T_FONTCOLOR='red';this.T_LEFT=true;return escape( '${ConsultarImovelActionForm.hint2}' ); " />
															</logic:equal>
														</logic:equal>
														<logic:equal name="ConsultarImovelActionForm" property="indicadorValidaCPFCNPJ" value="2">
															<img border="0" width="25" height="25" src="<bean:message key="caminho.imagens"/>informacao.gif" onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${ConsultarImovelActionForm.hint2}' ); " />
														</logic:equal>
													</td>
													<td align="center" width="96%">
														<strong>
															Dados do Im�vel<logic:present name="imovelExcluido" scope="request"> <font color="#ff0000">&nbsp;(Exclu�do)</font> </logic:present>
														</strong>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr bgcolor="#cbe5fe">
										<td align="center" width="100%">
											<table border="0" width="100%">
												<tr>
													<td bordercolor="#000000" width="25%"><strong>Im&oacute;vel: <font color="#FF0000">*</font>
													</strong></td>
													<td width="75%" colspan="3">
														<html:text property="idImovelDadosComplementares" maxlength="9" size="9" onkeypress="validaEnterComMensagem(event, 'consultarImovelWizardAction.do?action=exibirConsultarImovelDadosComplementaresAction&indicadorNovo=OK','idImovelDadosComplementares','Im&oacute;vel');return isCampoNumerico(event);"></html:text>
															<a href="javascript:pesquisarImovel();">
															<img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Im�vel" /></a>
															<logic:present name="idImovelDadosComplementaresNaoEncontrado" scope="request">
															<html:text property="matriculaImovelDadosComplementares" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" title="Localidade.Setor.Quadra.Lote.Sublote" />
														</logic:present>
														<logic:notPresent name="idImovelDadosComplementaresNaoEncontrado" scope="request">
															<logic:present name="valorMatriculaImovelDadosComplementares" scope="request">
																<html:text property="matriculaImovelDadosComplementares" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" title="Localidade.Setor.Quadra.Lote.Sublote" />
															</logic:present>
															<logic:notPresent name="valorMatriculaImovelDadosComplementares" scope="request">
																<html:text property="matriculaImovelDadosComplementares" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" title="Localidade.Setor.Quadra.Lote.Sublote" />
															</logic:notPresent>
														</logic:notPresent>
														<a href="javascript:limparForm();">
															<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
													</td>
												</tr>
												<tr>
													<td height="10">
														<div class="style9">
															<strong>Situa&ccedil;&atilde;o de &Aacute;gua:</strong>
														</div>
													</td>
													<td>
														<html:text property="situacaoAguaDadosComplementares" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" size="15" maxlength="15" />
													</td>
													<td width="96"><strong>Situa��o de Esgoto:</strong></td>
													<td width="120">
														<html:text property="situacaoEsgotoDadosComplementares" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" size="15" maxlength="15" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td align="left" height="10" width="28%">
								<div class="style9">
									<strong>Tarifa de Consumo:</strong>
								</div>
							</td>
							<td align="left" width="22%">
								<div class="style9">
									<html:text property="tarifaConsumoDadosComplementares"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="20" maxlength="20" />
								</div>
							</td>
							<td align="left" width="32%"><strong>Quantidade de Retifica&ccedil;&otilde;es:</strong></td>

							<td align="left" width="18%">
								<html:text property="quantidadeRetificacoesDadosComplementares" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" size="20" maxlength="20" />
							</td>
						</tr>
						<tr>
							<td align="left"><strong>Qtd. Parcelamentos:</strong></td>
							<td align="left">
								<html:text property="quantidadeParcelamentosDadosComplementares" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" size="20" maxlength="20" />
							</td>
							<td align="left"><strong>Qtd.Reparcelamentos:</strong></td>
							<td align="left">
								<html:text property="quantidadeReparcelamentoDadosComplementares" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" size="20" maxlength="20" />
							</td>
						</tr>
						<tr>
							<td colspan="2" height="10">
								<div class="style9">
									<strong>Qtd. Reparcelamentos Consecutivos:</strong>
								</div>
							</td>
							<td>
								<html:text property="quantidadeReparcelamentoConsecutivosDadosComplementares" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" size="20" maxlength="20" />
							</td>
							<td>&nbsp;</td>
						</tr>
						<!-- <tr>
							<td align="left"><strong>Situa&ccedil;&atilde;o de Cobran&ccedil;a:</strong></td>
							<td colspan="3" align="left"><html:text
								property="situacaoCobrancaDadosComplementares" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="50" maxlength="50" /></td>
						</tr> -->
						<tr>
							<td align="left"><strong>Funcion�rio Resp.:</strong></td>
							<td colspan="3">
								<html:text property="idFuncionario" readonly="true" style="background-color:#EFEFEF; border:0;" size="4" maxlength="4" />
								<html:text property="nomeFuncionario" readonly="true" style="background-color:#EFEFEF; border:0;" size="60" maxlength="40" />
							</td>
						</tr>
						<tr>
							<td align="left"><strong>Informa��es Complementares:</strong></td>
							<td colspan="3">
								<html:textarea property="informacoesComplementares" readonly="true" style="background-color:#EFEFEF; border:0;" cols="50" rows="4" />
							</td>
						</tr>
						<!-- Inicio da tabela de Situa��o de Cobranca - Vivianne Sousa -->
						<tr>
							<td colspan="7">
								<table width="100%" align="center" bgcolor="#90c7fc" border="0">
									<tr bordercolor="#79bbfd">
										<td colspan="7" bgcolor="#79bbfd" align="center"><strong>Situa��es de Cobran�a</strong></td>
									</tr>
									<tr bordercolor="#000000">
										<td width="20%" bgcolor="#90c7fc">
											<div align="left" class="style9">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Descri��o</strong>
												</font>
											</div>
										</td>
										<td width="10%" bgcolor="#90c7fc">
											<div align="left" class="style9">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Refer�ncia</strong>
												</font>
											</div>
										</td>
										<td width="10%" bgcolor="#90c7fc">
											<div align="left" class="style9">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Data de Implanta��o</strong>
												</font>
											</div>
										</td>
										<td width="10%" bgcolor="#90c7fc">
											<div align="left" class="style9">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Data da Retirada</strong>
												</font>
											</div>
										</td>
										<td width="10%" bgcolor="#90c7fc">
											<div align="left" class="style9">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Cliente Alvo</strong>
												</font>
											</div>
										</td>
										<td width="20%" bgcolor="#90c7fc">
											<div align="left" class="style9">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Escrit�rio Cobran�a</strong>
												</font>
											</div>
										</td>
										<td width="20%" bgcolor="#90c7fc">
											<div align="left" class="style9">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Advog. Resp. Cobran�a</strong>
												</font>
											</div>
										</td>
									</tr>

									<logic:notEmpty name="colecaoDadosImovelCobrancaSituacao"
										scope="session">
										<%
											if (((Collection) session.getAttribute("colecaoDadosImovelCobrancaSituacao")).size() <= 3) {
												String cor = "#cbe5fe";
										%>
										<logic:present name="colecaoDadosImovelCobrancaSituacao">
											<logic:iterate name="colecaoDadosImovelCobrancaSituacao" id="imovelCobrancaSituacaoHelper">
												<%
													if (cor.equalsIgnoreCase("#cbe5fe")) {
														cor = "#FFFFFF";
												%>
												<tr bgcolor="#FFFFFF">
												<%
													} else {
														cor = "#cbe5fe";
												%>
												<tr bgcolor="#cbe5fe">
												<%
													}
												%>
													<td width="20%">
														<div align="left" class="style9">
															<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																<bean:write name="imovelCobrancaSituacaoHelper" property="descricaoSituacaoCobranca" />
															</font>
														</div>
													</td>
													<td width="10%">
														<div align="left" class="style9">
															<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																<logic:notEqual name="imovelCobrancaSituacaoHelper" property="anoMesReferenciaInicio" value="0">
																	<bean:write name="imovelCobrancaSituacaoHelper" property="anoMesReferenciaInicio" />
																	&nbsp;a&nbsp;
																	<bean:write name="imovelCobrancaSituacaoHelper" property="anoMesReferenciaFinal" />
																</logic:notEqual>
															</font>
														</div>
													</td>
													<td width="10%">
														<div align="left" class="style9">
															<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif"> <!-- #dialog � o id do DIV definido como mostrado a seguir  -->
																<logic:notEmpty name="imovelCobrancaSituacaoHelper" property="contasEnviadasCobrancaHelper">
																	<a href="#dialog" name="modal">
																		<bean:write name="imovelCobrancaSituacaoHelper" property="dataImplantacaoCobranca" formatKey="date.format" />
																	</a>
																	<div id="boxes">
																		<div id="dialog" class="window">
																			<a href="#" class="close"><b>Fechar [X]</b></a><br />
																			<table width="100%" align="center" bgcolor="#90c7fc" border="0">
																				<tr bordercolor="#79bbfd">
																					<td colspan="7" bgcolor="#79bbfd" align="center">
																						<b>Contas Enviadas Para Cobran�a</b>
																					</td>
																				</tr>
																				<tr bordercolor="#000000">
																					<td bgcolor="#90c7fc"><b>M�s/Ano</b></td>
																					<td bgcolor="#90c7fc"><b>Vl.Conta</b></td>
																					<td bgcolor="#90c7fc"><b>Situa��o Atual</b></td>
																				</tr>
																				<%
																					String colorContasEnviadas = "#cbe5fe";
																				%>
																				<logic:iterate id="helperContasEnviadas" name="imovelCobrancaSituacaoHelper" property="contasEnviadasCobrancaHelper">
																					<%
																						if (colorContasEnviadas.equalsIgnoreCase("#cbe5fe")) {
																							colorContasEnviadas = "#FFFFFF";
																					%>
																					<tr bgcolor="#FFFFFF">
																					<%
																						} else {
																							colorContasEnviadas = "#cbe5fe";
																					%>
																					<tr bgcolor="#cbe5fe">
																					<%
																						}
																					%>
																						<td><bean:write name="helperContasEnviadas" property="mesAno" /></td>
																						<td><bean:write name="helperContasEnviadas" property="valorConta" /></td>
																						<td><bean:write name="helperContasEnviadas" property="situacao" /></td>
																					</tr>
																				</logic:iterate>
																			</table>
																		</div>
																		<!-- N�o remova o div#mask, pois ele � necess�rio para preencher toda a janela -->
																		<div id="mask"></div>
																	</div>
																</logic:notEmpty>
																<logic:empty name="imovelCobrancaSituacaoHelper" property="contasEnviadasCobrancaHelper">
																	<bean:write name="imovelCobrancaSituacaoHelper" property="dataImplantacaoCobranca" formatKey="date.format" />
																</logic:empty>
															</font>
														</div>
													</td>
													<td width="10%">
														<div align="left" class="style9">
															<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																<logic:notEmpty name="imovelCobrancaSituacaoHelper" property="contasPagasCobrancaHelper">
																	<a href="#dialog1" name="modal">
																		<bean:write name="imovelCobrancaSituacaoHelper" property="dataRetiradaCobranca" formatKey="date.format" />
																	</a>
																	<div id="boxes">
																		<div id="dialog1" class="window">
																			<a href="#" class="close"><b>Fechar [X]</b></a><br />
																			<table width="100%" align="center" bgcolor="#90c7fc" border="0">
																				<tr bordercolor="#79bbfd">
																					<td colspan="7" bgcolor="#79bbfd" align="center">
																						<b>Contas Pagas Em Cobran�a</b>
																					</td>
																				</tr>
																				<tr bordercolor="#000000">
																					<td bgcolor="#90c7fc"><b>M�s/Ano</b></td>
																					<td bgcolor="#90c7fc"><b>Vl.Pago</b></td>
																					<td bgcolor="#90c7fc"><b>Situa��o Atual</b></td>
																				</tr>
																				<%
																					String colorContasPagas = "#cbe5fe";
																				%>
																				<logic:iterate id="helperContasPagas" name="imovelCobrancaSituacaoHelper" property="contasPagasCobrancaHelper">
																					<%
																						if (colorContasPagas.equalsIgnoreCase("#cbe5fe")) {
																							colorContasPagas = "#FFFFFF";
																					%>
																						<tr bgcolor="#FFFFFF">
																					<%
																						} else {
																							colorContasPagas = "#cbe5fe";
																					%>
																						<tr bgcolor="#cbe5fe">
																					<%
																						}
																					%>
																						<td><bean:write name="helperContasPagas" property="mesAno" /></td>
																						<td><bean:write name="helperContasPagas" property="valorConta" /></td>
																						<td><bean:write name="helperContasPagas" property="situacao" /></td>
																					</tr>
																				</logic:iterate>

																			</table>
																		</div>
																		<!-- N�o remova o div#mask, pois ele � necess�rio para preencher toda a janela -->
																		<div id="mask"></div>
																	</div>
																</logic:notEmpty>
																	<logic:empty name="imovelCobrancaSituacaoHelper" property="contasPagasCobrancaHelper">
																	<bean:write name="imovelCobrancaSituacaoHelper" property="dataRetiradaCobranca" formatKey="date.format" />
																</logic:empty>
															</font>
														</div>
													</td>
													<td width="10%">
														<div align="left" class="style9">
															<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																<a href="javascript:abrirPopup('exibirConsultarClienteAction.do?desabilitarPesquisaCliente=SIM&codigoCliente='+<bean:write name="imovelCobrancaSituacaoHelper" property="idClienteAlvo" />, 500, 800);">
																	<bean:write name="imovelCobrancaSituacaoHelper" property="idClienteAlvo" />
																</a>
															</font>
														</div>
													</td>
													<td width="20%">
														<div align="left" class="style9">
															<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																<bean:write name="imovelCobrancaSituacaoHelper" property="escritorioCobranca" />
															</font>
														</div>
													</td>
													<td width="20%">
														<div align="left" class="style9">
															<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																<bean:write name="imovelCobrancaSituacaoHelper" property="advogadoResponsavelCobranca" />
															</font>
														</div>
													</td>
												</tr>
											</logic:iterate>
										</logic:present>
										<%
											} else {
										%>
										<tr>
											<td height="75" colspan="7">
												<div style="width: 100%; height: 100%; overflow: auto;">
													<table width="100%">
														<%
															String cor = "#cbe5fe";
														%>
														<logic:present name="colecaoDadosImovelCobrancaSituacao">
															<logic:iterate name="colecaoDadosImovelCobrancaSituacao" id="imovelCobrancaSituacaoHelper">
																<%
																	if (cor.equalsIgnoreCase("#cbe5fe")) {
																		cor = "#FFFFFF";
																%>
																<tr bgcolor="#FFFFFF">
																<%
																	} else {
																								cor = "#cbe5fe";
																%>
																<tr bgcolor="#cbe5fe">
																<%
																	}
																%>
																	<td width="20%">
																		<div align="left" class="style9">
																			<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																				<bean:write name="imovelCobrancaSituacaoHelper" property="descricaoSituacaoCobranca" />
																			</font>
																		</div>
																	</td>
																	<td width="10%">
																		<div align="left" class="style9">
																			<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																				<logic:notEqual name="imovelCobrancaSituacaoHelper" property="anoMesReferenciaInicio" value="0">
																					<bean:write name="imovelCobrancaSituacaoHelper" property="anoMesReferenciaInicio" />
																					&sbnp;a&sbnp;
																					<bean:write name="imovelCobrancaSituacaoHelper" property="anoMesReferenciaFinal" />
																				</logic:notEqual>
																			</font>
																		</div>
																	</td>
																	<td width="10%">
																		<div align="left" class="style9">
																			<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																				<!-- #dialog � o id do DIV definido como mostrado a seguir  -->
																				<logic:notEmpty name="imovelCobrancaSituacaoHelper" property="contasEnviadasCobrancaHelper">
																					<a href="#dialog" name="modal">
																						<bean:write name="imovelCobrancaSituacaoHelper" property="dataImplantacaoCobranca" formatKey="date.format" />
																					</a>
																					<div id="boxes">
																						<div id="dialog" class="window">
																							<a href="#" class="close"><b>Fechar [X]</b></a> <br />
																							<table width="100%" align="center" bgcolor="#90c7fc" border="0">
																								<tr bordercolor="#79bbfd">
																									<td colspan="7" bgcolor="#79bbfd" align="center"><b>Contas Enviadas Para Cobran�a</b></td>
																								</tr>
																								<tr bordercolor="#000000">
																									<td bgcolor="#90c7fc"><b>M�s/Ano</b></td>
																									<td bgcolor="#90c7fc"><b>Vl.Conta</b></td>
																									<td bgcolor="#90c7fc"><b>Situa��o Atual</b></td>
																								</tr>
																								<%
																									String colorContasEnviadas = "#cbe5fe";
																								%>
																								<logic:iterate id="helperContasEnviadas" name="imovelCobrancaSituacaoHelper" property="contasEnviadasCobrancaHelper">
																									<%
																										if (colorContasEnviadas.equalsIgnoreCase("#cbe5fe")) {
																											colorContasEnviadas = "#FFFFFF";
																									%>
																									<tr bgcolor="#FFFFFF">
																									<%
																										} else {
																											colorContasEnviadas = "#cbe5fe";
																									%>
																									<tr bgcolor="#cbe5fe">
																									<%
																										}
																									%>
																										<td><bean:write name="helperContasEnviadas" property="mesAno" /></td>
																										<td><bean:write name="helperContasEnviadas" property="valorConta" /></td>
																										<td><bean:write name="helperContasEnviadas" property="situacao" /></td>
																									</tr>
																								</logic:iterate>
																							</table>
																						</div>
																						<!-- N�o remova o div#mask, pois ele � necess�rio para preencher toda a janela -->
																						<div id="mask"></div>
																					</div>
																				</logic:notEmpty>
																				<logic:empty name="imovelCobrancaSituacaoHelper" property="contasEnviadasCobrancaHelper">
																					<bean:write name="imovelCobrancaSituacaoHelper" property="dataImplantacaoCobranca" formatKey="date.format" />
																				</logic:empty>
																			</font>
																		</div>
																	</td>
																	<td width="10%">
																		<div align="left" class="style9">
																			<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																				<logic:notEmpty name="imovelCobrancaSituacaoHelper" property="contasPagasCobrancaHelper">
																					<a href="#dialog1" name="modal">
																						<bean:write name="imovelCobrancaSituacaoHelper" property="dataRetiradaCobranca" formatKey="date.format" />
																					</a>
																					<div id="boxes">
																						<div id="dialog1" class="window">
																							<a href="#" class="close"><b>Fechar [X]</b></a> <br />
																							<table width="100%" align="center" bgcolor="#90c7fc" border="0">
																								<tr bordercolor="#79bbfd">
																									<td colspan="7" bgcolor="#79bbfd" align="center"><b>Contas Pagas Em Cobran�a</b></td>
																								</tr>
																								<tr bordercolor="#000000">
																									<td bgcolor="#90c7fc"><b>M�s/Ano</b></td>
																									<td bgcolor="#90c7fc"><b>Vl.Pago</b></td>
																									<td bgcolor="#90c7fc"><b>Situa��o Atual</b></td>
																								</tr>
																								<%
																									String colorContasPagas = "#cbe5fe";
																								%>
																								<logic:iterate id="helperContasPagas" name="imovelCobrancaSituacaoHelper" property="contasPagasCobrancaHelper">
																									<%
																										if (colorContasPagas.equalsIgnoreCase("#cbe5fe")) {
																											colorContasPagas = "#FFFFFF";
																									%>
																									<tr bgcolor="#FFFFFF">
																									<%
																										} else {
																											colorContasPagas = "#cbe5fe";
																									%>
																									<tr bgcolor="#cbe5fe">
																									<%
																										}
																									%>
																										<td><bean:write name="helperContasPagas" property="mesAno" /></td>
																										<td><bean:write name="helperContasPagas" property="valorConta" /></td>
																										<td><bean:write name="helperContasPagas" property="situacao" /></td>
																									</tr>
																								</logic:iterate>
																							</table>
																						</div>
																						<!-- N�o remova o div#mask, pois ele � necess�rio para preencher toda a janela -->
																						<div id="mask"></div>
																					</div>
																				</logic:notEmpty>
																				<logic:empty name="imovelCobrancaSituacaoHelper" property="contasPagasCobrancaHelper">
																					<bean:write name="imovelCobrancaSituacaoHelper" property="dataRetiradaCobranca" formatKey="date.format" />
																				</logic:empty>
																			</font>
																		</div>
																	</td>
																	<td width="10%">
																		<div align="left" class="style9">
																			<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																				<a href="javascript:abrirPopup('exibirConsultarClienteAction.do?desabilitarPesquisaCliente=SIM&codigoCliente='+<bean:write name="imovelCobrancaSituacaoHelper" property="idClienteAlvo" />, 500, 800);">
																					<bean:write name="imovelCobrancaSituacaoHelper" property="idClienteAlvo" />
																				</a>
																			</font>
																		</div>
																	</td>
																	<td width="20%">
																		<div align="left" class="style9">
																			<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																				<bean:write name="imovelCobrancaSituacaoHelper" property="escritorioCobranca" />
																			</font>
																		</div>
																	</td>
																	<td width="20%">
																		<div align="left" class="style9">
																			<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																				<bean:write name="imovelCobrancaSituacaoHelper" property="advogadoResponsavelCobranca" />
																			</font>
																		</div>
																	</td>
																</tr>
															</logic:iterate>
														</logic:present>
													</table>
												</div>
											</td>
										</tr>
										<%
											}
										%>
									</logic:notEmpty>
								</table>
							</td>
						</tr>
						<!-- Fim da tabela de Situa��o de Cobranca - Vivianne Sousa -->

						<!-- Inicio da tabela de Negativa��es - Vivianne Sousa -->
						<tr>
							<td colspan="7">
								<table width="100%" align="center" bgcolor="#90c7fc" border="0">
									<tr bordercolor="#79bbfd">
										<td colspan="4" bgcolor="#79bbfd" align="center">
											<strong>Negativa��es</strong>
										</td>
									</tr>
									<tr bordercolor="#000000">
										<td width="40%" rowspan="2" bgcolor="#90c7fc">
											<div align="left" class="style9">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Negativador</strong>
												</font>
											</div>
										</td>

										<td>
										<td width="60%" colspan="3" bgcolor="#90c7fc">
											<div align="left" class="style9">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Inclus�es Aceitas</strong>
												</font>
											</div>
										</td>
									</tr>
									<tr bordercolor="#FFFFFF" bgcolor="#cbe5fe">
										<td width="20%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
											<div align="center">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Total</strong>
												</font>
											</div>
										</td>

										<td width="20%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
											<div align="center">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>N�o Confirmadas</strong>
												</font>
											</div>
										</td>


										<td width="20%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
											<div align="center">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Confirmadas</strong>
												</font>
											</div>
										</td>
									</tr>
									<logic:notEmpty name="colecaoDadosNegativadorMovimentoReg" scope="session">
										<%
											String cor = "#cbe5fe";
										%>
										<logic:present name="colecaoDadosNegativadorMovimentoReg">
											<logic:iterate name="colecaoDadosNegativadorMovimentoReg" id="negaticacaoAceitaHelper">
												<%
													if (cor.equalsIgnoreCase("#cbe5fe")) {
														cor = "#FFFFFF";
												%>
												<tr bgcolor="#FFFFFF">
												<%
													} else {
														cor = "#cbe5fe";
												%>
												<tr bgcolor="#cbe5fe">
													<%
														}
													%>
													<td width="40%">
														<div align="left" class="style9">
															<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																<bean:write name="negaticacaoAceitaHelper" property="nomeNegativador" />
															</font>
														</div>
													</td>
													<td width="20%">
														<div align="center" class="style9">
															<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																<bean:write name="negaticacaoAceitaHelper" property="totalInclusoes" />
															</font>
														</div>
													</td>
													<td width="20%">
														<div align="center" class="style9">
															<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																<bean:write name="negaticacaoAceitaHelper" property="inclusoesNaoConfirmadas" />
															</font>
														</div>
													</td>
													<td width="20%">
														<div align="center" class="style9">
															<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
																<bean:write name="negaticacaoAceitaHelper" property="inclusoesConfirmadas" />
															</font>
														</div>
													</td>
												</tr>
											</logic:iterate>
										</logic:present>
									</logic:notEmpty>
								</table>
							</td>
						</tr>
						<!-- Fim da tabela de Negativa��es - Vivianne Sousa -->

						<tr>
							<td colspan="4" align="left">
								<table bgcolor="#90c7fc" width="100%" border="0">
									<tr>
										<td colspan="6" align="center" bgcolor="#79bbfd"><strong>Vencimentos Alternativos</strong></td>
									</tr>
									<tr bgcolor="#90c7fc">
										<td colspan="2" align="center" bgcolor="#90c7fc">
											<div class="style9">
												<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Dia do Vencimento</strong>
												</font>
											</div>
										</td>
										<td colspan="2" align="center" bgcolor="#90c7fc">
											<div class="style9">
												<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Data Implanta&ccedil;&atilde;o</strong>
												</font>
											</div>
										</td>
										<td colspan="1" align="center" bgcolor="#90c7fc">
											<div class="style9">
												<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>M&ecirc;s Seguinte</strong>
												</font>
											</div>
										</td>
										<td colspan="1" align="center" bgcolor="#90c7fc">
											<div class="style9">
												<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Data Exclus&atilde;o</strong>
												</font>
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="6" width="100%">
											<div style="width: 100%; height: 100%; overflow: auto;">
												<!--corpo da segunda tabela-->
												<%
													int cont = 0;
												%>
												<logic:notEmpty name="colecaoVencimentosAlternativos" scope="session">
													<table width="100%" align="left" border="0">
														<logic:iterate name="colecaoVencimentosAlternativos" id="vencimentosAlternativos">
															<%
																cont = cont + 1;
																if (cont % 2 == 0) {
															%>
															<tr bgcolor="#cbe5fe">
															<%
																} else {
															%>
															<tr bgcolor="#FFFFFF">
															<%
																}
															%>
																<td colspan="2" align="center" width="29%">
																	<div class="style9">
																		<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																			<bean:write name="vencimentosAlternativos" property="dateVencimento" />
																		</font>
																	</div>
																</td>
																<td colspan="2" align="center" width="28%">
																	<div class="style9">
																		<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																			<bean:write name="vencimentosAlternativos" property="dataImplantacao" formatKey="date.format" />
																		</font>
																	</div>
																</td>
																<td colspan="1" align="center" width="20%">
																	<div class="style9">
																		<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																			<logic:present name="vencimentosAlternativos" property="imovel">
																				<bean:define name="vencimentosAlternativos" property="imovel" id="imovel"></bean:define>
																				<logic:equal name="imovel" property="indicadorVencimentoMesSeguinte" value="1">SIM</logic:equal>
																				<logic:equal name="imovel" property="indicadorVencimentoMesSeguinte" value="2">N&Atilde;O</logic:equal>
																			</logic:present>
																		</font>
																	</div>
																</td>
																<td colspan="1" align="center" width="29%">
																	<div class="style9">
																		<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																			<bean:write name="vencimentosAlternativos" property="dateExclusao" formatKey="date.format" />
																		</font>
																	</div>
																</td>
															</tr>
														</logic:iterate>
													</table>
												</logic:notEmpty>
											</div>
										</td>
									</tr>
								</table> <br>
								<table bgcolor="#90c7fc" width="100%" border="0">
									<tr>
										<td colspan="6" bordercolor="#79bbfd" align="center" bgcolor="#79bbfd"><strong>D&eacute;bito Autom&aacute;tico</strong></td>
									</tr>
									<tr bgcolor="#90c7fc">
										<td align="center" bgcolor="#90c7fc" width="26%">
											<div class="style9">
												<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Banco</strong>
												</font>
											</div>
										</td>
										<td align="center" bgcolor="#90c7fc" width="8%"><font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><strong>Ag&ecirc;ncia</strong></font></td>
										<td align="center" bgcolor="#90c7fc" width="22%">
											<div class="style9">
												<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Ident.Cliente no Banco</strong>
												</font>
											</div>
										</td>
										<td align="center" bgcolor="#90c7fc" width="12%">
											<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<strong>Data Op&ccedil;&atilde;o</strong>
											</font>
										</td>
										<td align="center" bgcolor="#90c7fc" width="18%">
											<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<strong>Data Implanta&ccedil;&atilde;o</strong>
											</font>
										</td>
										<td align="center" bgcolor="#90c7fc">
											<div class="style9">
												<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Data Exclus&atilde;o</strong>
												</font>
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="6">
											<div style="width: 100%; height: 100%; overflow: auto;">
												<table width="100%" align="left" bgcolor="#99CCFF">
													<!--corpo da segunda tabela-->
													<%
														cont = 0;
													%>
													<logic:notEmpty name="colecaoDebitosAutomaticos" scope="session">
														<logic:iterate name="colecaoDebitosAutomaticos" id="debitosAutomaticos">
															<%
																cont = cont + 1;
																	if (cont % 2 == 0) {
															%>
															<tr bgcolor="#cbe5fe">
																<%
																	} else {
																%>
															<tr bgcolor="#FFFFFF">
																<%
																	}
																%>
																<td align="center" width="26%">
																	<div class="style9">
																		<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																			<logic:present name="debitosAutomaticos" property="agencia">
																				<bean:define name="debitosAutomaticos" property="agencia" id="agencia" />
																				<logic:present name="agencia" property="banco">
																					<bean:define name="agencia" property="banco" id="banco" />
																					<bean:write name="banco" property="descricaoAbreviada" />
																				</logic:present>
																			</logic:present>
																		</font>
																	</div>
																</td>
																<td align="center" width="8%">
																	<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																		<logic:present name="debitosAutomaticos" property="agencia">
																			<bean:write name="debitosAutomaticos" property="agencia.codigoAgencia" />
																		</logic:present>
																	</font>
																</td>
																<td align="center" width="22%">
																	<div class="style9">
																		<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																			<bean:write name="debitosAutomaticos" property="identificacaoClienteBanco" />
																		</font>
																	</div>
																</td>
																<td align="center" width="12%">
																	<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																		<bean:write name="debitosAutomaticos" property="dataOpcaoDebitoContaCorrente" formatKey="date.format" />
																	</font>
																</td>
																<td align="center" width="18%">
																	<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																		<bean:write name="debitosAutomaticos" property="dataInclusaoNovoDebitoAutomatico" formatKey="date.format" />
																	</font>
																</td>
																<td align="center">
																	<div class="style9">
																		<font style="font-size: 9px;" color="#000000"
																			face="Verdana, Arial, Helvetica, sans-serif">
																			<bean:write name="debitosAutomaticos" property="dataExclusao" formatKey="date.format" />
																		</font>
																	</div>
																</td>
															</tr>
														</logic:iterate>
													</logic:notEmpty>
												</table>
											</div>
										</td>
									</tr>
								</table><br />
								<table width="100%" align="center" bgcolor="#90c7fc" border="0">
									<tr bordercolor="#79bbfd">
										<td colspan="3" align="center" bgcolor="#79bbfd"><strong>Ocorr&ecirc;ncias de Cadastro</strong></td>
									</tr>
									<tr bordercolor="#000000">
										<td width="65%" bgcolor="#90c7fc" align="center">
											<div class="style9">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Ocorr&ecirc;ncia</strong></font>
											</div>
										</td>
										<td width="17%" align="center" bgcolor="#90c7fc">
											<div class="style9">
												<font color="#000000" style="font-size: 9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data
												</strong></font>
											</div>
											<div class="style9">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif"></font>
											</div>
										</td>
										<td width="18%" align="center" bgcolor="#90c7fc">
											<span class="style9">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Foto</strong>
												</font>
											</span>
										</td>
									</tr>
									<%
										String cor1 = "#FFFFFF";
									%>
									<logic:present name="colecaoImovelCadastroOcorrencia"
										scope="session">
										<logic:iterate name="colecaoImovelCadastroOcorrencia"
											id="imovelCadastroOcorrencia">
											<%
												if (cor1.equalsIgnoreCase("#FFFFFF")) {
													cor1 = "#cbe5fe";
											%>
											<tr bgcolor="#FFFFFF">
											<%
												} else {
													cor1 = "#FFFFFF";
											%>
											<tr bgcolor="#cbe5fe">
											<%
												}
											%>
												<td bordercolor="#90c7fc" align="left">
													<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:write name="imovelCadastroOcorrencia" property="cadastroOcorrencia.descricao" />
													</font>
												</td>
												<td align="center">
													<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:write name="imovelCadastroOcorrencia" property="dataOcorrencia" formatKey="date.format" />
													</font>
												</td>
												<td align="center">
													<logic:notEmpty name="imovelCadastroOcorrencia" property="fotoOcorrencia">
														<a href="javascript:abrirPopup('exibirFotoOcorrenciaCadastroConsultarImovelAction.do?id=<bean:write name="imovelCadastroOcorrencia" property="id"/>', 400, 800);">
															<img width="18" height="18" src="<bean:message key="caminho.imagens"/>imgfolder.gif" border="0" />
														</a>
													</logic:notEmpty>
												</td>
											</tr>
										</logic:iterate>
									</logic:present>
								</table> <br>
								<table width="100%" align="center" bgcolor="#90c7fc" border="0">
									<tr bordercolor="#79bbfd">
										<td colspan="3" align="center" bgcolor="#79bbfd">
											<strong>Anormalidades de Localidade P�lo:</strong>
										</td>
									</tr>
									<tr bordercolor="#000000">
										<td width="65%" bgcolor="#90c7fc" align="center">
											<div class="style9">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Anormalidade</strong>
												</font>
											</div>
										</td>

										<td width="17%" bgcolor="#90c7fc" align="center">
											<div class="style9">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Data</strong>
												</font>
											</div>
										</td>
										<td width="18%" bgcolor="#90c7fc" align="center">
											<span class="style9">
												<font color="#000000" style="font-size: 9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Foto</strong>
												</font>
											</span>
										</td>
									</tr>
									<%
										String cor2 = "#FFFFFF";
									%>
									<logic:present name="colecaoImovelEloAnormalidade" scope="session">
										<logic:iterate name="colecaoImovelEloAnormalidade" id="imovelEloAnormalidade">
											<%
												if (cor2.equalsIgnoreCase("#FFFFFF")) {
													cor2 = "#cbe5fe";
											%>
											<tr bgcolor="#FFFFFF">
											<%
												} else {
													cor2 = "#FFFFFF";
											%>
											<tr bgcolor="#cbe5fe">
											<%
												}
											%>
												<td align="left">
													<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:write name="imovelEloAnormalidade" property="eloAnormalidade.descricao" />
													</font>
												</td>
												<td align="center">
													<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:write name="imovelEloAnormalidade" property="dataAnormalidade" formatKey="date.format" />
													</font>
												</td>
												<td align="center">
													<logic:notEmpty name="imovelEloAnormalidade" property="fotoAnormalidade">
														<a href="javascript:abrirPopup('exibirFotoAnormalidadeEloAction.do?id=<bean:write name="imovelEloAnormalidade" property="id"/>', 600, 800);">
															<img width="18" height="18" src="<bean:message key="caminho.imagens"/>imgfolder.gif" border="0" /></a>
													</logic:notEmpty>
												</td>
											</tr>
										</logic:iterate>
									</logic:present>
								</table> <br>
								<table bgcolor="#90c7fc" width="100%" border="0">
									<tr>
										<td colspan="6" bordercolor="#79bbfd" align="center" bgcolor="#79bbfd">
											<strong>Situa&ccedil;&otilde;es Especiais de Faturamento </strong>
										</td>
									</tr>
									<tr bgcolor="#90c7fc">
										<td align="center" bgcolor="#90c7fc" width="26%">
											<div class="style9">
												<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Tipo</strong>
												</font>
											</div>
										</td>
										<td align="center" bgcolor="#90c7fc" width="18%">
											<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<strong>Motivo</strong>
											</font>
										</td>
										<td align="center" bgcolor="#90c7fc" width="12%">
											<div class="style9">
												<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>M&ecirc;s/Ano In&iacute;cio&nbsp;</strong>
												</font>
											</div>
										</td>
										<td align="center" bgcolor="#90c7fc" width="12%">
											<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<strong>M&ecirc;s/Ano Fim</strong>
											</font>
										</td>
										<td align="center" bgcolor="#90c7fc" width="18%">
											<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<strong>M&ecirc;s/Ano Retirada</strong>
											</font>
										</td>
										<td align="center" bgcolor="#90c7fc">
											<div class="style9">
												<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Usu&aacute;rio</strong>
												</font>
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="6">
											<div style="width: 100%; height: 100%; overflow: auto;">
												<table width="100%" align="left" bgcolor="#99CCFF">
													<!--corpo da segunda tabela-->
													<%
														cont = 0;
													%>
													<logic:notEmpty name="colecaoFaturamentosSituacaoHistorico" scope="session">
														<logic:iterate name="colecaoFaturamentosSituacaoHistorico" id="faturamentosSituacaoHistorico" type="FaturamentoSituacaoHistorico">
															<%
																cont = cont + 1;
																	if (cont % 2 == 0) {
															%>
															<tr bgcolor="#cbe5fe">
															<%
																} else {
															%>
															<tr bgcolor="#FFFFFF">
															<%
																}
															%>
																<td align="left" width="26%">
																	<div class="style9">
																		<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																			<logic:present name="faturamentosSituacaoHistorico" property="faturamentoSituacaoTipo">
																				<a href="javascript:abrirPopup('exibirConsultarSituacaoEspecialFaturamentoPopupAction.do?idFaturamentoSituacaoHistorico=<bean:write name="faturamentosSituacaoHistorico" property="id"/>', 600, 800)">
																					<bean:write name="faturamentosSituacaoHistorico" property="faturamentoSituacaoTipo.descricao" /></a>
																			</logic:present>
																		</font>
																	</div>
																</td>
																<td align="center" width="18%">
																	<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																		<logic:present name="faturamentosSituacaoHistorico" property="faturamentoSituacaoMotivo">
																			<%
																				if (cont % 2 == 0) {
																			%>
																			<bean:write name="faturamentosSituacaoHistorico" property="faturamentoSituacaoMotivo.descricao" />
																			<%
																				} else {
																			%>
																			<bean:write name="faturamentosSituacaoHistorico" property="faturamentoSituacaoMotivo.descricao" />
																			<%
																				}
																			%>
																		</logic:present>
																	</font>
																</td>
																<td align="center" width="12%">
																	<div class="style9">
																		<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																			<%="" + Util.formatarMesAnoReferencia(faturamentosSituacaoHistorico.getAnoMesFaturamentoSituacaoInicio())%>
																		</font>
																	</div>
																</td>
																<td align="center" width="12%">
																	<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																		<%="" + Util.formatarMesAnoReferencia(faturamentosSituacaoHistorico.getAnoMesFaturamentoSituacaoFim())%>
																	</font>
																</td>
																<td align="center" width="18%">
																	<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																		<%
																			if (faturamentosSituacaoHistorico.getAnoMesFaturamentoRetirada() != null) {
																		%>
																		<%="" + Util.formatarMesAnoReferencia(faturamentosSituacaoHistorico.getAnoMesFaturamentoRetirada())%>
																		<%
																			}
																		%>
																	</font>
																</td>
																<td align="center">
																	<div class="style9">
																		<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																			<logic:present name="faturamentosSituacaoHistorico" property="usuario">
																				<bean:write name="faturamentosSituacaoHistorico" property="usuario.nomeUsuario" />
																			</logic:present>
																		</font>
																	</div>
																</td>
															</tr>
														</logic:iterate>
													</logic:notEmpty>
												</table>
											</div>
										</td>
									</tr>
								</table> <br>
								<table bgcolor="#90c7fc" width="100%" border="0">
									<tr>
										<td colspan="6" align="center" bgcolor="#79bbfd">
											<strong>Situa&ccedil;&otilde;es Especiais de Cobran&ccedil;a</strong>
										</td>
									</tr>
									<tr bordercolor="#000000" bgcolor="#90c7fc">
										<td align="center" bgcolor="#90c7fc" width="26%">
											<div class="style9">
												<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Tipo</strong>
												</font>
											</div>
										</td>
										<td align="center" bgcolor="#90c7fc" width="18%">
											<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<strong>Motivo</strong>
											</font>
										</td>
										<td align="center" bgcolor="#90c7fc" width="12%">
											<div class="style9">
												<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>M&ecirc;s/Ano In&iacute;cio&nbsp;</strong>
												</font>
											</div>
										</td>
										<td align="center" bgcolor="#90c7fc" width="12%">
											<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<strong>M&ecirc;s/Ano Fim</strong>
											</font>
										</td>
										<td align="center" bgcolor="#90c7fc" width="18%">
											<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
												<strong>M&ecirc;s/Ano Retirada</strong>
											</font>
										</td>
										<td align="center" bgcolor="#90c7fc">
											<div class="style9">
												<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>Usu&aacute;rio</strong>
												</font>
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="6">
											<div style="width: 100%; height: 100%; overflow: auto;">
												<table width="100%" align="left" bgcolor="#99CCFF">
													<!--corpo da segunda tabela-->
													<%
														cont = 0;
													%>
													<logic:notEmpty name="colecaoCobrancasSituacaoHistorico" scope="session">
														<logic:iterate name="colecaoCobrancasSituacaoHistorico" id="cobrancasSituacaoHistorico" type="CobrancaSituacaoHistorico">
															<%
																cont = cont + 1;
																	if (cont % 2 == 0) {
															%>
															<tr bgcolor="#cbe5fe">
															<%
																} else {
															%>
															<tr bgcolor="#FFFFFF">
															<%
																}
															%>
																<td align="center" width="26%">
																	<div class="style9">
																		<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																			<logic:present name="cobrancasSituacaoHistorico" property="cobrancaSituacaoTipo">
																				<a href="javascript:abrirPopup('exibirConsultarSituacaoEspecialCobrancaPopupAction.do?idCobrancaSituacaoHistorico=<bean:write name="cobrancasSituacaoHistorico" property="id"/>', 600, 800)">
																					<bean:write name="cobrancasSituacaoHistorico" property="cobrancaSituacaoTipo.descricao" />
																				</a>
																			</logic:present>
																		</font>
																	</div>
																</td>
																<td align="center" width="18%">
																	<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																		<logic:present name="cobrancasSituacaoHistorico" property="cobrancaSituacaoMotivo">
																			<bean:write name="cobrancasSituacaoHistorico" property="cobrancaSituacaoMotivo.descricao" />
																		</logic:present>
																	</font>
																</td>
																<td align="center" width="12%">
																	<div class="style9">
																		<font style="font-size: 9px;" color="#000000"
																			face="Verdana, Arial, Helvetica, sans-serif">
																			<%="" + Util.formatarMesAnoReferencia(cobrancasSituacaoHistorico.getAnoMesCobrancaSituacaoInicio())%>
																		</font>
																	</div>
																</td>
																<td align="center" width="12%">
																	<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																		<%="" + Util.formatarMesAnoReferencia(cobrancasSituacaoHistorico.getAnoMesCobrancaSituacaoFim())%>
																	</font>
																</td>
																<td align="center" width="18%">
																	<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																		<% 
																			if (cobrancasSituacaoHistorico.getAnoMesCobrancaRetirada() != null) {
 																		%>
																		<%="" + Util.formatarMesAnoReferencia(cobrancasSituacaoHistorico.getAnoMesCobrancaRetirada())%>
																		<%
																		 	}
																		 %>
																	</font>
																</td>
																<td align="center">
																	<div class="style9">
																		<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																			<logic:present name="cobrancasSituacaoHistorico" property="usuario">
																				<bean:write name="cobrancasSituacaoHistorico" property="usuario.nomeUsuario" />
																			</logic:present>
																		</font>
																	</div>
																</td>
															</tr>
														</logic:iterate>
													</logic:notEmpty>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table bgcolor="#90c7fc" width="100%" border="0">
						<tr>
							<td colspan="6" align="center" bgcolor="#79bbfd">
								<strong>Ramos de Atividades do Im�vel</strong>
							</td>
						</tr>
						<tr bordercolor="#000000" bgcolor="#90c7fc">
							<td align="center" bgcolor="#90c7fc" width="15%">
								<div class="style9">
									<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
										<strong>C�digo</strong>
									</font>
								</div>
							</td>
							<td align="center" bgcolor="#90c7fc">
								<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
									<strong>Descri��o</strong>
								</font>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%" align="left" bgcolor="#99CCFF">
										<!--corpo da segunda tabela-->
										<%
											int contx = 0;
										%>
										<logic:notEmpty name="colecaoImovelRamosAtividade" scope="session">
											<logic:iterate name="colecaoImovelRamosAtividade" id="idRamoAtividade" type="ImovelRamoAtividade">
												<%
													contx = contx + 1;
														if (contx % 2 == 0) {
												%>
												<tr bgcolor="#cbe5fe">
												<%
													} else {
												%>
												<tr bgcolor="#FFFFFF">
												<%
													}
												%>
													<td align="center" width="15%">
														<div class="style9">
															<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
																<bean:write name="idRamoAtividade" property="comp_id.ramo_atividade.id" />
															</font>
														</div>
													</td>
													<td align="left">
														<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
															<bean:write name="idRamoAtividade" property="comp_id.ramo_atividade.descricao" />
														</font>
													</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</table>
								</div>
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table bgcolor="#90c7fc" width="100%" border="0">
						<tr>
							<td colspan="6" align="center" bgcolor="#79bbfd">
								<strong>Dados do Contrato</strong>
							</td>
						</tr>
						<tr bordercolor="#000000" bgcolor="#90c7fc">
							<td align="center" bgcolor="#90c7fc" width="20%">
								<div class="style9">
									<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
										<strong>N�mero do Contrato</strong>
									</font>
								</div>
							</td>
							<td align="center" bgcolor="#90c7fc" width="20%">
								<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
									<strong>Data In�cio</strong>
								</font>
							</td>
							<td align="center" bgcolor="#90c7fc" width="20%">
								<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
									<strong>Data t�rmino</strong>
								</font>
							</td>
							<td align="center" bgcolor="#90c7fc" width="20%">
								<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
									<strong>Valor da Tarifa</strong>
								</font>
							</td>
							<td align="center" bgcolor="#90c7fc" width="20%">
								<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
									<strong>Consumo Contratado</strong>
								</font>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%" align="left" bgcolor="#99CCFF">
										<!--corpo da segunda tabela-->
										<logic:notEmpty name="contratoDemandaHelper" scope="session">
											<tr bgcolor="#FFFFFF">
												<td align="center" width="20%">
													<div class="style9">
														<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
															<bean:write name="contratoDemandaHelper" property="contratoDemanda.numeroContrato" />
														</font>
													</div>
												</td>
												<td align="center" width="20%">
													<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:write name="contratoDemandaHelper" property="contratoDemanda.dataContratoInicio" formatKey="date.format" />
													</font>
												</td>
												<td align="center" width="20%">
													<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:write name="contratoDemandaHelper" property="contratoDemanda.dataContratoFim" formatKey="date.format" />
													</font>
												</td>
												<td align="center" width="20%">
													<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:write name="contratoDemandaHelper" property="valorTarifa" />
													</font>
												</td>
												<td align="center" width="20%">
													<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:write name="contratoDemandaHelper" property="consumoContratado" />
													</font>
												</td>
											</tr>
										</logic:notEmpty>
									</table>
								</div>
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table bgcolor="#90c7fc" width="100%" border="0">
						<tr>
							<td colspan="6" align="center" bgcolor="#79bbfd">
								<strong>Matr�culas Associadas</strong>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%" align="left" bgcolor="#99CCFF">
										<!--corpo da segunda tabela-->
										<%int contx2 = 0;%>
										<logic:notEmpty name="colecaoMatriculasAssociadas" scope="session">
											<tr bgcolor="#FFFFFF">
												<logic:iterate name="colecaoMatriculasAssociadas" id="matriculasAssociadas" type="Integer">
													<%
														contx2 = contx2 + 1;
															if (contx2 % 6 == 0) {
													%>
													</tr>
													<tr bgcolor="#99CCFF">
													<%
														}
													%>
													<td align="center">
														<font style="font-size: 9px;" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
															<a href="javascript:consultarMatricula(<bean:write name="matriculasAssociadas" />);">
																<bean:write name="matriculasAssociadas" />
															</a>
														</font>
													</td>
												</logic:iterate>
											</tr>
										</logic:notEmpty>
									</table>
								</div>
							</td>
						</tr>

					</table>
					<p>&nbsp;</p> <!-- Inicio da Tabela  Historico de alteracao dados do Imovel -->
					<table bgcolor="#90c7fc" width="100%" border="0">
						<tr>
							<td colspan="6">
								<table width="100%" align="left" bgcolor="#90c7fc" border="0">
									<tr bordercolor="#79bbfd">
										<td colspan="6" bgcolor="#79bbfd" align="center">
											<strong>Hist�rico de Altera&ccedil;&atilde;o do Im�vel</strong>
										</td>
										<td align="center" bgcolor="#79bbfd">
											<input type="button" name="button" class="bottonRightCol" value="Pesquisar Hist�rico" onClick="javascript:pesquisarHistoricoImovel()">
										</td>
									</tr>
								</table>
								<table>
									<tr bordercolor="#000000">
										<td width="20%" bgcolor="#90c7fc" align="left">
											<div align="left">
												<strong>Data da Realiza&ccedil;&atilde;o</strong>
											</div>
										</td>
										<td width="20%" bgcolor="#90c7fc" align="left">
											<div align="left">
												<strong>Nome da Opera&ccedil;&atilde;o</strong>
											</div>
										</td>
										<td width="20%" bgcolor="#90c7fc" align="left">
											<div align="left">
												<strong>Usuario que Realizou a Opera&ccedil;&atilde;o</strong>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="6">
								<logic:notEmpty name="colecaoAlteracaoImovel">
									<div style="width: 100%; height: auto; overflow: auto;">
										<table width="100%" align="left" bgcolor="#99CCFF" border="0">
											<%int countt = 0;%>
											<logic:notEmpty name="colecaoAlteracaoImovel" scope="request">
												<logic:iterate name="colecaoAlteracaoImovel" id="alteracaoImovel" type="OperacaoEfetuada">
													<%
														countt = countt + 1;
														if (countt % 2 == 0) {
													%>
													<tr bgcolor="#cbe5fe">
													<%
														} else {
													%>
													<tr bgcolor="#FFFFFF">
													<%
														}
													%>
														<td width="15%" align="left">
															<div align="left">
																<bean:write name="alteracaoImovel" property="ultimaAlteracaoFormatada" /> 
															</div>
														</td>
														<td width="20%" align="left">
															<div align="left">
																<a href="javascript:abrirPopup('ConsultarOperacaoEfetuadaAction.do?idOperacaoEfetuada=<bean:write name="alteracaoImovel" property="id" />&tipoPesquisa=imovel', 500, 800);">
																	<bean:write name="alteracaoImovel" property="operacao.descricao" />
																</a>
															</div>
														</td>
														<td width="20%" align="left">
															<div align="left">
																<bean:define name="alteracaoImovel" property="usuarioAlteracoes" id="usuarioAlteracoes" />
																<logic:iterate name="usuarioAlteracoes" id="usuarioAlteracao">
																	<bean:write name="usuarioAlteracao" property="usuario.nomeUsuario" />
																</logic:iterate>
															</div>
														</td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</table>
									</div>
								</logic:notEmpty>
							</td>
						</tr>
           			</table>
					<table width="100%" border="0">
						<tr>
							<td align="left" width="100%" colspan="2">
								  <div align="right">
									   <a href="javascript:verificaExibicaoRelatorioDadosComplementares();">
											<img border="0" src="<bean:message key="caminho.imagens"/>print.gif" title="Imprimir Dados Adicionais" /></a>
								  </div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="right">
									<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_consulta.jsp?numeroPagina=2" />
								</div>
							</td>
						</tr>
					</table>	
				</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioDadosComplementaresImovelAction.do" />
		<%@ include file="/jsp/util/rodape.jsp"%>
		<%@ include file="/jsp/util/tooltip.jsp"%>
	</html:form>
</body>
</html:html>

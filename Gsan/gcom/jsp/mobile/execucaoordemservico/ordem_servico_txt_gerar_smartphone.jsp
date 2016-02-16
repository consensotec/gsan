<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions' prefix='fn' %>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>

<html:javascript staticJavascript="true" formName="GerarArquivoTextoOrdensServicoSmartphoneActionForm" />

<style type="text/css">
	input[name=tipoFiltro] {
		vertical-align: bottom;
	}
</style>

</head>
<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form method="post"
	action="/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do"
	name="GerarArquivoTextoOrdensServicoSmartphoneActionForm"
	type="gcom.gui.cobranca.execucaoordemservico.GerarArquivoTextoOrdensServicoSmartphoneActionForm">

	<input type="hidden" name="tipoPesquisa" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="120" valign="top" class="leftcoltext">
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

			<table height="100%"><tr><td></td></tr></table>

			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11">
						<img src="<bean:message key="caminho.imagens"/>parahead_left.gif" border="0" />
					</td>

					<td class="parabg">Gerar Arquivo Texto Ordem Servico para Dispositivo Movel</td>

					<td width="11" valign="top">
						<img src="<bean:message key="caminho.imagens"/>parahead_right.gif" border="0" />
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar o arquivo texto de ordens de serviço para dispositivo móvel, informe os dados abaixo:</td>
				</tr>

				<tr><td>&nbsp;</td></tr>

				<tr>
					<td width="28%">
						<strong>Empresa:<font color="#FF0000">*</font></strong>
					</td>
					<td colspan="2">
						<logic:present name="colecaoEmpresa">
							<html:select property="idEmpresa" style="width: 300px;" tabindex="1">
								<html:option value="">&nbsp;</html:option>
								<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id"/>
							</html:select>
						</logic:present>

						<logic:notPresent name="colecaoEmpresa">
							<html:hidden property="idEmpresa"/>
							<html:text property="descricaoEmpresa" size="41"
								maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Tipo da Ordem de Serviço:<font color="#FF0000">*</font></strong>
					</td>
					<td colspan="2">
						<html:hidden property="idTipoOS"/>
						<html:text property="descricaoTipoOS" size="41"
								maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
					</td>
				</tr>

				<tr>
					<td>
						<strong>Tipo de Serviço:<font color="#FF0000">*</font></strong>
					</td>
					<td colspan="2">
						<html:select property="idServicoTipo" tabindex="2" style="width: 300px;">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoServicoTipo" labelProperty="descricao" property="id"/>
						</html:select>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Tipo de Filtro:<font color="#FF0000">*</font></strong>
					</td>
					<td colspan="2">
						<html:radio styleId="tipoFiltroGrupo" property="tipoFiltro" value="1"/>
						<label for="tipoFiltroGrupo"><strong>Grupo de Cobrança</strong></label>

						<html:radio styleId="tipoFiltroComando" property="tipoFiltro" value="2"/>
						<label for="tipoFiltroComando"><strong>Cobrança Eventual</strong></label>
					</td>
				</tr>

				<tr><td>&nbsp;</td></tr>

				<c:choose>
					<c:when test="${GerarArquivoTextoOrdensServicoSmartphoneActionForm.tipoFiltro eq 1}">
						<tr>
							<td>
								<strong>Mês/Ano do Cronograma:<font color="#FF0000">*</font></strong>
							</td>

							<td colspan="2">
								<html:text property="mesAnoCronograma" maxlength="7" size="8" tabindex="3"
									styleId="mesAnoCronograma" onkeyup="mascaraAnoMes(this, event);"
									onkeypress="return isCampoNumerico(event); mascaraAnoMes(this, event);"/> mm/aaaa
							</td>
						</tr>

						<tr>
							<td>
								<strong>Grupo de Cobrança:<font color="#FF0000">*</font></strong>
							</td>

							<td colspan="2">
								<html:select property="idGrupoCobranca" tabindex="4" style="width: 300px;">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>

									<logic:present name="colecaoGrupoCobranca">
										<html:options collection="colecaoGrupoCobranca" labelProperty="descricao" property="id"/>
									</logic:present>
								</html:select>
							</td>
						</tr>

						<tr>
							<td>
								<strong>Localidade:</strong>
							</td>

							<td colspan="2">
								<logic:present name="colecaoLocalidade">
									<html:select property="idsLocalidade" tabindex="5" style="width: 300px;" multiple="true">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoLocalidade" labelProperty="descricao" property="id"/>
									</html:select>
								</logic:present>
								<logic:notPresent name="colecaoLocalidade">
									<html:select property="idsLocalidade" tabindex="5" style="width: 300px;" disabled="true">
									</html:select>
								</logic:notPresent>
							</td>
						</tr>
						<html:hidden property="dataCobrancaEventualInicial" value=""/>
						<html:hidden property="dataCobrancaEventualFinal" value=""/>
					</c:when>
					<c:when test="${GerarArquivoTextoOrdensServicoSmartphoneActionForm.tipoFiltro eq 2}">
						<tr>
							<td><strong>Período de Realização:<font color="#FF0000">*</font></strong></td>
							<td>
								<strong>
									<span class="style2">
										<html:text property="dataCobrancaEventualInicial" size="11" maxlength="10" tabindex="6"
												onkeyup="mascaraData(this, event);replicaDataCobrancaEventual();" onkeypress="return isCampoNumerico(event);"/>
										<a href="javascript:abrirCalendarioReplicando('GerarArquivoTextoOrdensServicoSmartphoneActionForm', 'dataCobrancaEventualInicial', 'dataCobrancaEventualFinal');">
										<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" title="Exibir Calendário" tabindex="7"/></a>
									</span>
									<span>&nbsp;a&nbsp;</span>
									<span class="style2">
										<html:text property="dataCobrancaEventualFinal" size="11" maxlength="10" tabindex="8"
													onkeypress="return isCampoNumerico(event);" onkeyup="mascaraData(this, event)"/>
										<a href="javascript:abrirCalendario('GerarArquivoTextoOrdensServicoSmartphoneActionForm', 'dataCobrancaEventualFinal');">
										<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" title="Exibir Calendário" tabindex="9"/></a>
									</span>
								</strong>
							</td>

							<td align="right">
								<input type="button" class="bottonRightCol" id="consultarComandos" value="Consultar Comandos" tabindex="13"/>
							</td>
						</tr>

						<tr>
							<td>
								<strong>Comando:<font color="#FF0000">*</font></strong>
							</td>
							<td colspan="2">
								<logic:present name="colecaoComandosEventuais">
									<html:select property="idComando" tabindex="10" style="width: 300px;">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoComandosEventuais" labelProperty="descricaoTitulo" property="id"/>
									</html:select>
								</logic:present>
								<logic:notPresent name="colecaoComandosEventuais">
									<html:select property="idComando" tabindex="10" style="width: 300px;" disabled="true">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									</html:select>
								</logic:notPresent>
							</td>
						</tr>

						<html:hidden property="mesAnoCronograma" value="" />
					</c:when>
					<c:otherwise>
						<html:hidden property="mesAnoCronograma" value="" />
						<html:hidden property="dataCobrancaEventualInicial" value=""/>
						<html:hidden property="dataCobrancaEventualFinal" value=""/>
					</c:otherwise>
				</c:choose>

				<tr>
					<td></td>
					<td>
						<br/>
						<font color="#FF0000">*</font>Campos Obrigat&oacute;rios
					</td>
					<td align="right">
						<input type="button" class="bottonRightCol" id="selecionar" value="Selecionar" tabindex="11"/>
					</td>
				</tr>

				<tr>
					<td colspan="3">
						<hr/>
					</td>
				</tr>
			</table>

			<table>
				<tr>
					<td width="100%" colspan="5">
						<logic:present name="colecaoHelperAgrupado">
								<c:set var="colecaoTamanho" value="${fn:length(colecaoHelperAgrupado)}"/>

								<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td colspan="3">
											<table id=header width="100%" border="0" bgcolor="#90c7fc">
												<col width=50>
												<col width=245>
												<col width=55>
												<col width=55>
												<col width=70>
												<col width=100>
												<c:if test="${colecaoTamanho > 7}">
													<col width=15>
												</c:if>

												<tr bgcolor="#90c7fc">
													<th align="center"><a href="javascript:facilitador('idsRota');">Todos</a></th>
													<th align="center">Localidade</th>
													<th align="center">Setor</th>
													<th align="center">Rota</th>
													<th align="center">Quantidade</th>
													<th align="center">Data de Geração</th>
												</tr>
											</table>

										<c:if test="${colecaoTamanho > 7}">
											<div style="overflow: auto; width: 100%; height: 140; padding:0px; margin: 0px ">
										</c:if>
												<table width="100%" border="0" bgcolor="#90c7fc">
													<c:set var="count" value="0"/>
													<col width=50>
													<col width=245>
													<col width=55>
													<col width=55>
													<col width=70>
													<col width=100>

													<logic:iterate name="colecaoHelperAgrupado" id="helper">
														<tr bgcolor="${count % 2 == 0 ? '#FFFFFF' : '#cbe5fe'}">
															<td align="center">
																<html:hidden property="" styleId="${helper.idRota}" value="${helper.qtd}"/>
																<html:multibox property="idsRota" value="${helper.idRota}" onchange="javascript:contarCheckboxMarcadosRota();" />
															</td>
															<td align="center">${helper.descricaoLocalidade}</td>
															<td align="center">${helper.cdSetor}</td>
															<td align="center">${helper.cdRota}</td>
															<td align="center">${helper.qtd}</td>
															<td align="center">${helper.dataGeracaoFormatada}</td>
														</tr>
														<c:set var="count" value="${count+1}"/>
													</logic:iterate>
												</table>

										<c:if test="${colecaoTamanho > 7}">
											</div>
										</c:if>
										</td>
									</tr>
									<tr bgcolor="#90c7fc">
										<td>
											<table width="98%">
												<tr>
													<td colspan="3" align="right">
														<strong>Total OS's Selecionadas: <span id="spanTotalRotaId">0</span></strong>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>

								<table width="100%">
									<tr>
										<td align="right">
											<input type="button" class="bottonRightCol" id="consultarQuadras" value="Consultar Quadras" tabindex="13"/>
										</td>
									</tr>
								</table>
						</logic:present>
					</td>
				</tr>
			</table>

			<c:if test="${GerarArquivoTextoOrdensServicoSmartphoneActionForm.colecaoHelper != null
					&& not empty GerarArquivoTextoOrdensServicoSmartphoneActionForm.colecaoHelper }">
				<table width="100%">

					<tr>
						<td colspan="3" height="24" bordercolor="#000000" class="style1">
							<hr>
						</td>
					</tr>

					<tr>
						<td width="100%" colspan="5">
							<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0">
								<c:set var="colecaoTamanho" value="${fn:length(GerarArquivoTextoOrdensServicoSmartphoneActionForm.colecaoHelper)}"/>

								<tr>
									<td colspan="3">
										<table id=header width="100%" border="0" bgcolor="#90c7fc">
											<col width="10%">
											<col width="${colecaoTamanho > 7 ? '48': '50'}%">
											<col width="10%">
											<col width="10%">
											<col width="${colecaoTamanho > 7 ? '9': '10'}%">
											<col width="10%">
											<col width="${colecaoTamanho > 7 ? '2': '0'}%">

											<tr bgcolor="#90c7fc">
												<th align="center"><a href="javascript:facilitador('idsOS');">Todas</a></th>
												<th align="center">Localidade</th>
												<th align="center">Setor</th>
												<th align="center">Rota</th>
												<th align="center">Quadra</th>
												<th align="center">Qtd. OS</th>
											</tr>
										</table>
										<bean:define name="GerarArquivoTextoOrdensServicoSmartphoneActionForm" property="colecaoHelper" id="colecaoHelper" />

										<c:if test="${colecaoTamanho > 7}">
											<div style="overflow: auto; width: 100%; height: 140; padding:0px; margin: 0px ">
										</c:if>
											<table border="0" width="100%" bgcolor="#90c7fc">
												<c:set var="count" value="0"/>
												<col width="10%">
												<col width="50%">
												<col width="10%">
												<col width="10%">
												<col width="10%">
												<col width="10%">

												<logic:iterate name="colecaoHelper" id="helper">
													<tr bgcolor="${count % 2 == 0 ? '#FFFFFF' : '#cbe5fe'}">
														<td align="center">
															<html:hidden property="" value="${helper.qtdOS}"/>
															<html:multibox property="idsOS"
																value="${helper.idLocalidade}-${helper.cdSetor}-${helper.cdRota}-${helper.nnQuadra}"
																onchange="javascript:contarCheckboxMarcadosOS();" />
														</td>
														<td align="center">${helper.descricaoLocalidade}</td>
														<td align="center">${helper.cdSetor}</td>
														<td align="center">${helper.cdRota}</td>
														<td align="center">${helper.nnQuadra}</td>
														<td align="center">${helper.qtdOS}</td>
													</tr>
													<c:set var="count" value="${count+1}"/>
												</logic:iterate>
											</table>
										<c:if test="${colecaoTamanho > 7}">
											</div>
										</c:if>
									</td>
								</tr>
								<tr bgcolor="#90c7fc">
									<td>
										<table width="98%">
											<tr>
												<td colspan="3" align="right">
													<strong>Total OS's Selecionadas: <span id="spanTotalOSId">0</span></strong>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</c:if>

			<logic:present name="colecaoHelperAgrupado">
				<table width="100%">
					<tr><td colspan="2"><p>&nbsp;</p></td></tr>
					<tr>
						<td>
							<strong>Agente Comercial:<font color="#FF0000">*</font></strong>

							<logic:present name="colecaoAgenteComercial" scope="session">
								<html:select property="idAgenteComercial" tabindex="10" style="width: 150px;">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoAgenteComercial" labelProperty="nome" property="id"/>
								</html:select>
							</logic:present>
							<logic:notPresent name="colecaoAgenteComercial" scope="session">
								<html:select property="idAgenteComercial" tabindex="10" style="width: 150px;" disabled="true">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								</html:select>
							</logic:notPresent>
						</td>
						<td align="right">
							<strong>Qtd M&aacute;x. OS:</strong>
							<strong>${GerarArquivoTextoOrdensServicoSmartphoneActionForm.qtdMaxOS}</strong>
						</td>
					</tr>
				</table>
			</logic:present>

			<table width="100%">
				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<input name="Button" type="button" class="bottonRightCol" value="Desfazer"
								onclick="window.location.href='<html:rewrite page="/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?idTipoOrdemServico=1&menu=sim"/>'">
							</td>

							<td>
								<input type="button" class="bottonRightCol" value="Cancelar" onclick="window.location.href='/gsan/telaPrincipal.do'" />
							</td>

							<td width="200px">&nbsp;</td>

							<td>
								<input type="button" class="bottonRightCol" id="botaoGerarRelatorio" value="Gerar Relat&oacute;rio" tabindex="14"/>
							</td>

							<td>
								<input type="button" class="bottonRightCol" id="botaoGerarArquivoTxt" value="Gerar Arquivo TXT" tabindex="15"/>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>

			</td>
		</tr>
	</table>

	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioArquivoTextoOrdensServicoSmartphoneAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>

<script>
	function replicaDataCobrancaEventual() {
		var form = document.forms[0];
		form.dataCobrancaEventualFinal.value = form.dataCobrancaEventualInicial.value;
	}

	function contarCheckboxMarcadosRota() {
		var qtd = 0;
		$('input[name=idsRota]:checked').siblings('input[type=hidden]').each(function() {
			qtd += parseInt($(this).val(), 10) || 0;
		});

		$('#spanTotalRotaId').html(qtd || '0');
	}

	function contarCheckboxMarcadosOS() {
		var qtd = 0;
		$('input[name=idsOS]:checked').siblings('input[type=hidden]').each(function() {
			qtd += parseInt($(this).val(), 10) || 0;
		});

		$('#spanTotalOSId').html(qtd || '0');
	}

	var statusCheckbox = {};
	function facilitador(name) {
		if(statusCheckbox[name]) {
			$('input[name='+name+']').removeAttr('checked');
			statusCheckbox[name] = false;
		} else {
			$('input[name='+name+']').attr('checked', true);
			statusCheckbox[name] = true;
		}

		contarCheckboxMarcadosRota();
		contarCheckboxMarcadosOS();
	}

	$(function() {
		var $mesAnoCronograma = $('#mesAnoCronograma');
		var $grupoCobranca = $('select[name=idGrupoCobranca]');
		var $dataCobrancaEventualInicial = $('input[name=dataCobrancaEventualInicial]');
		var $dataCobrancaEventualFinal = $('input[name=dataCobrancaEventualFinal]');
		var $comando = $('select[name=idComando]');
		var $botaoConsultarComandos = $('#consultarComandos');
		var $empresa = $('select[name=idEmpresa]');
		var $servicoTipo = $('select[name=idServicoTipo]');

		$('#selecionar').click(function() {
			var mesAnoCronograma = $mesAnoCronograma.val();
			var grupoCobranca = $grupoCobranca.val();
			var dataCobrancaEventualInicial = $dataCobrancaEventualInicial.val();
			var dataCobrancaEventualFinal = $dataCobrancaEventualFinal.val();
			var comando = $comando.val();

			grupoCobranca = grupoCobranca == -1 ? 0 : grupoCobranca;
			comando = comando == -1 ? 0 : comando;

			if ((mesAnoCronograma && grupoCobranca) || (dataCobrancaEventualInicial && dataCobrancaEventualFinal && comando)) {
				botaoAvancarTelaEspera('/gsan/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=selecionar');
			} else {
				alert("Informar campos obrigatórios do filtro.");
			}
		});

		$('#consultarQuadras').click(function() {
			var qtd = 0;
			$('input[name=idsRota]:checked').siblings('input[type=hidden]').each(function() {
				qtd += parseInt($(this).val(), 10) || 0;
			});

			if(!qtd) {
				alert("Selecionar pelo menos uma rota.");
				return;
			}

			var qtdMaxConsultar = ${GerarArquivoTextoOrdensServicoSmartphoneActionForm.qtdMaxOS} * 2;	
			if(qtd > qtdMaxConsultar) {
				alert("A quantidade selecionada para consulta excede o limite de " + qtdMaxConsultar + " ordens de serviço.");
				return;
			}

			botaoAvancarTelaEspera('/gsan/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=consultarQuadras');
		});

		$botaoConsultarComandos.click(function() {
			var dataCobrancaEventualInicial = $dataCobrancaEventualInicial.val();
			var dataCobrancaEventualFinal = $dataCobrancaEventualFinal.val();

			if (!dataCobrancaEventualInicial || !dataCobrancaEventualFinal) {
				alert("Informar o período de realização");
				return;
			}

			botaoAvancarTelaEspera('/gsan/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=consultarComandos');
		});

		$grupoCobranca.change(function() {
			botaoAvancarTelaEspera('/gsan/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=consultarLocalidade');
		});

		$empresa.change(function() {
			var form = document.GerarArquivoTextoOrdensServicoSmartphoneActionForm;
			form.action = "/gsan/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=selecionarEmpresa";
			form.submit();
		});

		$comando.change(function() {
			var form = document.GerarArquivoTextoOrdensServicoSmartphoneActionForm;
			form.action = "/gsan/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=selecionarComando";
			form.submit();
		});

		$servicoTipo.change(function() {
			var form = document.GerarArquivoTextoOrdensServicoSmartphoneActionForm;
			form.action = "/gsan/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=selecionarServicoTipo";
			form.submit();
		});

		$('#botaoGerarRelatorio').click(function() {
			var mesAnoCronograma = $mesAnoCronograma.val();
			var grupoCobranca = $grupoCobranca.val();
			var dataCobrancaEventualInicial = $dataCobrancaEventualInicial.val();
			var dataCobrancaEventualFinal = $dataCobrancaEventualFinal.val();
			var comando = $comando.val();

			grupoCobranca = grupoCobranca == -1 ? 0 : grupoCobranca;
			comando = comando == -1 ? 0 : comando;

			if ((mesAnoCronograma && grupoCobranca) || (dataCobrancaEventualInicial && dataCobrancaEventualFinal && comando)) {
				toggleBox('demodiv',1);
			} else {
				alert("Informar campos obrigatórios do filtro.");
			}
		});

		$('#botaoGerarArquivoTxt').click(function() {
			if (!validateGerarArquivoTextoOrdensServicoSmartphoneActionForm(document.forms[0])) {
				return;
			}

			var qtdIdsOS = $('input[name=idsOS]:checked').length;
			var qtdIdsRota = $('input[name=idsRota]:checked').length;

			if (qtdIdsOS == 0 && qtdIdsRota == 0) {
				alert("Selecionar pelo menos uma Quadra ou Rota.");
				return;
			}

			var agenteComercial = $('select[name=idAgenteComercial]').val();
			agenteComercial = agenteComercial == -1 ? 0 : agenteComercial;
			if (!agenteComercial) {
				alert("Selecionar o Agente Comercial.");
				return;
			}

			botaoAvancarTelaEspera('/gsan/gerarArquivoTextoOrdensServicoSmartphoneAction.do');
		});

		$('input[name=tipoFiltro]').click(function() {
			if (validateGerarArquivoTextoOrdensServicoSmartphoneActionForm(document.forms[0])) {
				botaoAvancarTelaEspera('/gsan/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=tipoFiltro');
			} else {
				$('input[name=tipoFiltro]').attr('checked', '');
			}
		});

		facilitador('idsOS');
	});
</script>

</html:form>
</div>

</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>


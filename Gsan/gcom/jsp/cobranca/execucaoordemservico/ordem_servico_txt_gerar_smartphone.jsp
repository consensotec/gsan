<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
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

<script type="text/javascript">

	function limparTudo(){
		var form = document.forms[0];
		form.qtdOsSeletiva.value='';
	}

	function limparQtdOsSeletiva(){
		var form = document.forms[0];
		form.qtdOsSeletiva.value='';
	}

	function validarForm(id){
		var form = document.forms[0];
		var codSetOri = form.codigoSetorComercialOrigem;
		var codSetDest = form.codigoSetorComercialDestino;
		var qdrIni = form.codigoQuadraInicial;
		var qdrFin = form.codigoQuadraFinal;
		var loc = form.localidade;
		var comando = form.comando;

		if (validateGerarArquivoTextoOrdensServicoSmartphoneActionForm(form)){
			if(id == 1) {
				botaoAvancarTelaEspera('/gsan/gerarArquivoTextoOrdensServicoSmartphoneAction.do');
			} else if(id == 2) {
				botaoAvancarTelaEspera('/gsan/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=consultarQdt');
			} else if(id == 3) {
				toggleBox('demodiv',1);
			} else if(id == 4) {
				botaoAvancarTelaEspera('/gsan/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=consultarOS');
			}
		}
	}

	function gerarRelatorio(){
		var form = document.forms[0];

		toggleBox('demodiv',1);
	}

	function replicaDataCobrancaoEventual() {
		var form = document.forms[0];
		form.dataCobrancaoEventualFinal.value = form.dataCobrancaoEventualInicial.value;
	}
</script>

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

					<td class="parabg">Gerar Arquivo Texto de Ordens de Serviço para Dispositivo Móvel</td>

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
					<td>
						<logic:present name="colecaoEmpresa">
							<html:select property="empresa" style="width: 300px;" tabindex="1">
								<html:option value="">&nbsp;</html:option>
								<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id"/>
							</html:select>
						</logic:present>

						<logic:notPresent name="colecaoEmpresa">
							<html:hidden property="empresa"/>
							<html:text property="descricaoEmpresa" size="41"
								maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Tipo da Ordem de Serviço:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:hidden property="idTipoOS"/>
						<html:text property="descricaoTipoOS" size="41"
								maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
					</td>
				</tr>

				<tr>
					<td>
						<strong>Tipo de Serviço:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:select property="idServicoTipo" tabindex="2" style="width: 300px;">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoServicoTipo" labelProperty="descricao" property="id"/>
						</html:select>
					</td>
				</tr>

				<tr><td>&nbsp;</td></tr>

				<tr bgcolor="#99CCFF" >
					<td height="18" colspan="2">
						<div align="left">
							<strong>
								<span class="style2"> Filtro para Geração do Arquivo TXT</span>
							</strong>
						</div>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Mês/Ano do Cronograma:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:text property="mesAnoCronograma" maxlength="7" size="8" tabindex="3"
							styleId="mesAnoCronograma"
							onkeypress="return isCampoNumerico(event); mascaraAnoMes(this, event);" 
							onkeyup="mascaraAnoMes(this, event);" /> mm/aaaa
					</td>
				</tr>

				<tr>
					<td>
						<strong>Grupo de Cobrança:<font color="#FF0000">*</font></strong>
					</td>

					<td>
						<html:select property="idGrupoCobranca" tabindex="4" style="width: 300px;">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoGrupoCobranca" labelProperty="descricao" property="id"/>
						</html:select>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Localidade:<font color="#FF0000">*</font></strong>
					</td>
					
					<td>
						<logic:present name="colecaoLocalidade">
							<html:select property="idLocalidade" tabindex="5" style="width: 300px;">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoLocalidade" labelProperty="descricao" property="id"/>
							</html:select>
						</logic:present>
						<logic:notPresent name="colecaoLocalidade">
							<html:select property="idLocalidade" tabindex="5" style="width: 300px;" disabled="true">
							</html:select>
						</logic:notPresent>
					</td>
				</tr>

				<tr> 
	                <td colspan="2" height="24" bordercolor="#000000" class="style1"> 
	                	<hr>
	                </td>
	            </tr>

				<tr>
	                <td><strong>Data Inicial:</strong></td>
	                <td colspan="6">
	                	<span class="style2"><strong> 
							<html:text property="dataCobrancaoEventualInicial" size="11" maxlength="10" tabindex="6"
									onkeyup="mascaraData(this, event);replicaDataCobrancaoEventual();" onkeypress="return isCampoNumerico(event);"/>
							<a href="javascript:abrirCalendarioReplicando('GerarArquivoTextoOrdensServicoSmartphoneActionForm', 'dataCobrancaoEventualInicial', 'dataCobrancaoEventualFinal');">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" title="Exibir Calendário" tabindex="7"/></a>
						</strong>(dd/mm/aaaa)</span>
					</td>
				</tr>

				<tr>
	                <td><strong>Data Final:</strong></td>
	                <td colspan="6">
	                	<span class="style2"><strong> 
							<html:text property="dataCobrancaoEventualFinal" size="11" maxlength="10" tabindex="8"
										onkeypress="return isCampoNumerico(event);" onkeyup="mascaraData(this, event)"/>
							<a href="javascript:abrirCalendario('GerarArquivoTextoOrdensServicoSmartphoneActionForm', 'dataCobrancaoEventualFinal');">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" title="Exibir Calendário" tabindex="9"/></a>
						</strong>(dd/mm/aaaa)</span>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Cobrança Eventual:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<logic:present name="colecaoComandosEventuais">
							<html:select property="idGrupoCobranca" tabindex="10" style="width: 300px;">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoComandosEventuais" labelProperty="descricaoTitulo" property="id"/>
							</html:select>
						</logic:present>
						<logic:notPresent name="colecaoComandosEventuais">
							<html:select property="idGrupoCobranca" tabindex="10" style="width: 300px;" disabled="true">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							</html:select>
						</logic:notPresent>
					</td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2">
						<br/>
						<font color="#FF0000">*</font>Campos Obrigat&oacute;rios
					</td>
				</tr>
				
				<tr align="right">
					<td colspan="2">
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
								
								<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0">
									<tr bgcolor="#99CCFF">
				                   		<td align="left">
				           					<span class="style2">        		
				             						<b>Ordens de Serviço</b>          				
				           					</span>
				               			</td>
				              		</tr>

									<tr>
										<td colspan="3">
											<table id=header width="100%" border="0" bgcolor="#90c7fc">
												<col width=50 align="center">
												<col width=135 align="center">
												<col width=80 align="center">
												<col width=100 align="center">
												<col width=296 align="center">
												<tr>
													<th bgcolor="#90c7fc" align="center">
													<a href="javascript:facilitador(this);">Todos</a></th>
													<th bgcolor="#90c7fc" align="center">Localidade</th>
													<th bgcolor="#90c7fc" align="center">Setor</th>
													<th bgcolor="#90c7fc" align="center">Rota</th>
													<th bgcolor="#90c7fc" align="center">Quantidade</th>
													<th bgcolor="#90c7fc" align="center">Data de Geração</th>
												</tr>
											</table>

												<table border="0" width="100%" bgcolor="#90c7fc">
													<c:set var="count" value="0"/>
													<col width=25 align="center">
													<col width=75 align="center">
													<col width=78 align="center">
													<col width=123 align="center">
													<col width=407 align="center">

													<logic:iterate name="colecaoHelperAgrupado" id="helper">
														<c:choose>
															<c:when test="${count % 2 == 0 }">
																<tr bgcolor="#FFFFFF">		
															</c:when>
															<c:otherwise>
																<tr bgcolor="#cbe5fe">
															</c:otherwise>
														</c:choose>
															<td width=68 align="center">
																<html:multibox property="idsRegistros" value="${helper.idRota}" disabled="false" onchange="javascript:contarCheckboxMarcados();" />
															</td>
															<td align="center"> <c:out value="${helper.descricaoLocalidade}"/> </td>
															<td align="center"> <c:out value="${helper.cdSetor}"/> </td>
															<td align="center"> <c:out value="${helper.cdRota}"/> </td>
															<td align="center"> <c:out value="${helper.cdRota}"/> </td>
															<td align="center"> <c:out value="${helper.qtd}"/> </td>
														</tr>	
														<c:set var="count" value="${count+1}"/>
													</logic:iterate>
												</table>
										</td>
									</tr>
									<tr bgcolor="#90c7fc">
										<td>
											<table width="98%">
												<tr>
													<td colspan="3" align="right">
														<strong>Total Selecionado: <span id="spanTotalId">0</span></strong>
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

			<c:if test="${GerarArquivoTextoOrdensServicoSmartphoneActionForm.colecaoOS != null 
								&& not empty GerarArquivoTextoOrdensServicoSmartphoneActionForm.colecaoOS }">
			<div id="divColecaoOS">
				<table>
						<tr>
							<td>
								<p>&nbsp;</p>
							</td>
						</tr>									
															
						<tr>
							<td width="100%" colspan="5">
								<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0">
									<tr bgcolor="#99CCFF">
				                   		<td align="left">
				           					<span class="style2">        		
				             						<b>Ordens de Serviço</b>          				
				           					</span>
				               			</td>
				              		</tr>
				              		
									<tr>
										<td colspan="3">
											<table id=header width="100%" border="0" bgcolor="#90c7fc">
												<col width=50 align="center">
												<col width=135 align="center">
												<col width=80 align="center">
												<col width=100 align="center">
												<col width=296 align="center">
												<tr>
													<th bgcolor="#90c7fc" align="center">
													<a href="javascript:facilitador(this);">Marcar</a></th>
													<th bgcolor="#90c7fc" align="center">Inscrição</th>
													<th bgcolor="#90c7fc" align="center">Matrícula</th>
													<th bgcolor="#90c7fc" align="center">Ordem de Serviço</th>
													<th bgcolor="#90c7fc" align="center">Tipo de Serviço</th>
												</tr>
											</table>
											<bean:define name="GerarArquivoTextoOrdensServicoSmartphoneActionForm" property="colecaoOS" id="colecaoOS" />

									<c:if test="${colecaoOS.size > 7}">
											<div style="overflow: auto; width: 100%; height: 140; padding:0px; margin: 0px ">
									</c:if>																 
												<table border="0" width="100%" bgcolor="#90c7fc">
													<c:set var="count" value="0"/>
													<col width=25 align="center">
													<col width=75 align="center">
													<col width=78 align="center">
													<col width=123 align="center">
													<col width=407 align="center">

													<logic:iterate name="colecaoOS" id="helper">
														<c:choose>
															<c:when test="${count % 2 == 0 }">
																<tr bgcolor="#FFFFFF">		
															</c:when>
															<c:otherwise>
																<tr bgcolor="#cbe5fe">
															</c:otherwise>
														</c:choose>
															<td width=68 align="center">
																<html:multibox property="idsRegistros" value="${helper.idOs}" disabled="false" onchange="javascript:contarCheckboxMarcados();" />
															</td>
															<td align="center">
																<c:out value="${helper.inscricaoImovel}"/>
															</td>
															 <td align="center">
																<c:out value="${helper.idImovel}"/>
															</td>
															<td align="center">
																<c:out value="${helper.idOs}"/>
															</td>
															<td align="center">
																<c:out value="${helper.tipoServico}"/>
															</td>
														</tr>	
														<c:set var="count" value="${count+1}"/>
													</logic:iterate>
												</table>
											<c:if test="${colecaoOS.size > 7}">
												</div>
											</c:if>																 
										</td>
									</tr>
									<tr bgcolor="#90c7fc">
										<td>
											<table width="98%">
												<tr>
													<td colspan="3" align="right">
														<strong>Total Selecionado: <span id="spanTotalId">0</span></strong>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>					
						    </td>
					    </tr>
				</table>

				<table width="100%" border="0">
				<tr>
					<td colspan="2">
						<html:text property="qtdOsSeletiva" size="3" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"/>

						<strong>Qtd M&aacute;x. OS:</strong>
						<strong><span style="font-size:20px;"><bean:write name="GerarArquivoTextoOrdensServicoSmartphoneActionForm" property="qtdMaxOS"/></span></strong>

						<!-- <html:text property="qtdMaxOS" size="3"
						readonly="true" style="background-color:#FF0000; border:0; color: #000000;margin-left:auto;margin-right:auto;"/> -->
					</td>

					<td align="right">							
						<input type="button" class="bottonRightCol" onclick="javascript:validarForm(4)" value="Consultar OS" tabindex="13"/>							
					</td>	
				</tr>

				<tr> 
	                <td colspan="3" height="24" bordercolor="#000000" class="style1"> 
	                	<hr>
	                </td>
	            </tr>
			</table>
			</div>
			</c:if>

			<table width="100%">
				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="70">
								<input name="Button" type="button" class="bottonRightCol"
								value="Desfazer"
								onclick="window.location.href='<html:rewrite page="/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?idTipoOrdemServico=1&menu=sim"/>'">
							</td>

							<td>
								<input type="button" class="bottonRightCol" value="Cancelar" onclick="window.location.href='/gsan/telaPrincipal.do'" />
							</td>

							<td width="200">&nbsp;</td>

							<td>
								<input type="button" class="bottonRightCol" onclick="javascript:validarForm(3)" value="Gerar Relat&oacute;rio" tabindex="14"/>
							</td>

							<td>
								<input type="button" class="bottonRightCol" onclick="javascript:validarForm(1);" value="Gerar Arquivo TXT" tabindex="15"/>
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

	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioOrdensServicoSmartphoneAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>

<script>
	$(function(){
		$('#selecionar').click(function(){
			botaoAvancarTelaEspera('/gsan/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=selecionar');
		})

		$('select[name=idGrupoCobranca]').change(function(){
			botaoAvancarTelaEspera('/gsan/exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=consultarLocalidade');
		});
	});
</script>

</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>


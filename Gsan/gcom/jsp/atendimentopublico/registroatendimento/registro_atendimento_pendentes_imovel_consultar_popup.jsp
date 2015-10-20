<%@page import="gcom.faturamento.debito.DebitoACobrar"%>
<%@page import="gcom.util.Util"%>
<%@page import="java.util.Collection"%>
<%@page import="gcom.util.ConstantesSistema"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page	import="gcom.atendimentopublico.registroatendimento.RegistroAtendimento"%>
<%@page import="gcom.arrecadacao.pagamento.Pagamento"%>
<%@page import="gcom.arrecadacao.pagamento.PagamentoHistorico"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<!--

function fechar(){
		window.close();
-->

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="ConsultarRegistroAtendimentoPendentesImovelActionForm" />



<script language="JavaScript">
	function redirecionaSubmit(caminhoAction) {
   	var form = document.forms[0];
   	form.action = caminhoAction;
   	form.submit();
   	return true;
 }
	function validarForm(form){
	urlRedirect = "/gsan/consultarRegistroAtendimentoPendentesImovelAction.do"
	if((document.ConsultarRegistroAtendimentoPendentesImovelActionForm.solicitacaoTipo, 'Especificação') && 
	testarCampoValorZero(document.ConsultarRegistroAtendimentoPendentesImovelActionForm.numeroRa, 'Numero do RA')&& 
	testarCampoValorZero(document.ConsultarRegistroAtendimentoPendentesImovelActionForm.matriculaImovel, 'Matrícula do imóvel')&&
	testarCampoValorZero(document.ConsultarRegistroAtendimentoPendentesImovelActionForm.inscricaoImovel, 'Inscrição do Imóvel')&&
	testarCampoValorZero(document.ConsultarRegistroAtendimentoPendentesImovelActionForm.clienteUsuario, 'Usuário')&&
	testarCampoValorZero(document.ConsultarRegistroAtendimentoPendentesImovelActionForm.solicitacaoTipoEspecificacao, 'Tipo de Solicitação')&&
	testarCampoValorZero(document.ConsultarRegistroAtendimentoPendentesImovelActionForm.situacaoLigacaoAgua, 'Situação da Ligacao Agua')&&	
	testarCampoValorZero(document.ConsultarRegistroAtendimentoPendentesImovelActionForm.dataRegistroAtendimento, 'Data do Atendimento')&&	
	testarCampoValorZero(document.ConsultarRegistroAtendimentoPendentesImovelActionForm.situacaoLigacaoEsgoto, 'Situação da Ligacao Esgoto')) {

		if(validateConsultarRegistroAtendimentoPendentesImovelActionForm(form)){
    		redirecionaSubmit(urlRedirect);
		}
		
	}
	
}

	function limparForm() {
		var form = document.ConsultarRegistroAtendimentoPendentesImovelActionForm;
		form.idOrdemServico.value = "";
		form.solicitacaoTipo.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.solicitacaoTipoEspecificacao.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.dataRegistroAtendimento.value = "";
		form.numeroRa.value = "";
	 }
	 
	function limparOrdemServico() {
		var form = document.ConsultarRegistroAtendimentoPendentesImovelActionForm;
		form.idOrdemServico.value = "";
		form.solicitacaoTipo.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.solicitacaoTipoEspecificacao.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.dataRegistroAtendimento.value = "";
		form.numeroRa.value = "";
	}
	
	function limparOrdemServicoTecla() {
		var form = document.ConsultarRegistroAtendimentoPendentesImovelActionForm;
		form.solicitacaoTipo.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.solicitacaoTipoEspecificacao.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.dataRegistroAtendimento.value = "";
		form.numeroRa.value = "";
	}
	
	function extendeTabela(tabela,display){
		var form = document.forms[0];

		if(display){
 			eval('layerHide'+tabela).style.display = 'none';
 			eval('layerShow'+tabela).style.display = 'block';
		}else{
			eval('layerHide'+tabela).style.display = 'block';
 			eval('layerShow'+tabela).style.display = 'none';
		}
	}
	
</script>


</head>
<body leftmargin="5" topmargin="5" onload="resizePageSemLink(690, 400);">


<logic:present name="identificadorPesquisa" scope="request">
	<body leftmargin="5" topmargin="5">
</logic:present>

<logic:notPresent name="identificadorPesquisa" scope="request">
	<body leftmargin="5" topmargin="5"
		onload="javascript:setarFoco('${requestScope.nomeCampo}');">
</logic:notPresent>

<html:form
	action="/exibirConsultarRegistroAtendimentoPendentesImovelAction.do"
	name="ConsultarRegistroAtendimentoPendentesImovelActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ConsultarRegistroAtendimentoPendentesImovelActionForm"
	method="post"
	onsubmit="return validateConsultarRegistroAtendimentoPendentesImovelActionForm(this);">

	<table width="635" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="635" valign="top" class="centercoltext">
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
					<td class="parabg">Registro Atendimento Pendentes para o Imóvel</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
						
				</tr>
			</table>
			<table width="100%">
					<tr>
						<td width="90%" colspan="2">&nbsp;</td>
						<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroImovelConsultar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
						</logic:present>
						<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
						</logic:notPresent>
					</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td align="center" colspan="2">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
									<div align="center"><strong>Dados do Imóvel</strong></div>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
										<tr>
											<td width="37%" height="10"><strong>Matrícula do Imóvel:</strong></td>
											<td width="58%"><html:text property="matriculaImovel"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="15" maxlength="20" /></td>
										</tr>
										<tr>
											<td><strong>Inscrição do Imóvel:</strong></td>
											<td><html:text property="inscricaoImovel" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="40" maxlength="40" /></td>
										</tr>


										<tr>
											<td><strong>Situação da Ligação de Água:</strong></td>
											<td><html:text property="situacaoLigacaoAgua" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="40" maxlength="40" /></td>
										</tr>
										<tr>
											<td><strong>Situação da Ligação de Esgoto:</strong></td>

											<td><html:text property="situacaoLigacaoEsgoto"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="40" maxlength="40" /></td>
										</tr>
										<tr>
											<td colspan="2">
											
											<table width="100%" cellpadding="0" cellspacing="0">
												<tr>
													<td>
														<table width="100%" border="0" bgcolor="#90c7fc">
														<tr height="18">
															<td align="center"><strong>Endereço do Imóvel</strong></td>
														</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td>
														<table width="100%" align="center" bgcolor="#99CCFF">
														<tr bgcolor="#FFFFFF" height="18">
															<td width="10%" align="center">
																<bean:write name="enderecoImovel"/>
															</td>
														</tr>
														</table>
												  	</td>
												</tr>
												</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>

						</tr>
					</table>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>

				<tr bgcolor="#cbe5fe">
					<td align="center" colspan="2">
					<div id="layerHideRA" style="display:block">
						<table width="100%" border="0" bgcolor="#99CCFF">
							<tr bgcolor="#99CCFF">
								<td height="18" colspan="5" align="center">
						            <a href="javascript:extendeTabela('RA',true);" >
		                  				<b>Dados Gerais do Registros de Atendimento</b>
		                  			</a>
									
								</td>
							</tr>
						</table>
					</div>
					
					<div id="layerShowRA" style="display:none">
						<table width="100%" border="0" bgcolor="#99CCFF">
							<tr bgcolor="#99CCFF">
								<td height="18" colspan="5" align="center">
						            <a href="javascript:extendeTabela('RA',false);" >
		                  				<b>Dados Gerais do Registros de Atendimento</b>
		                  			</a>
									
								</td>
							</tr>
							
							<tr bgcolor="#cbe5fe">
							<tr>
							<tr bordercolor="#000000">
								<td width="16%" bgcolor="#90c7fc">
								<div align="center"><strong>Número do RA</strong></div>
								</td>
	
								<td width="20%" bgcolor="#90c7fc">
								<div align="center"><strong>Tipo da Solicitação</strong></div>
								</td>
								<td width="30%" bgcolor="#90c7fc">
								<div align="center"><strong>Especificação</strong></div>
								</td>
								<td width="20%" bgcolor="#90c7fc">
								<div align="center"><strong>Data de Atendimento</strong></div>
								</td>
								<td width="14%" bgcolor="#90c7fc">
								<div align="center"><strong>Situação</strong></div>
								</td>
	
							</tr>
							<tr bordercolor="#90c7fc">
								<td colspan="5" height="200">
								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%" align="left" bgcolor="#90c7fc">
								<logic:present
									name="colecaoConsultarImovelRegistroAtendimentoHelper">
									<%int cont = 0;%>
									<logic:iterate
										name="colecaoConsultarImovelRegistroAtendimentoHelper"
										id="consultarImovelRegistroAtendimentoHelper">
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="16%" align="center"><a
												href="javascript:abrirPopup('exibirConsultarRegistroAtendimentoPopupAction.do?caminhoTelaPesquisaRetorno=exibirConsultarRegistroAtendimentoPendentesImovelAction&numeroRA='+${consultarImovelRegistroAtendimentoHelper.idRegistroAtendimento}, 400, 800);">
											${consultarImovelRegistroAtendimentoHelper.idRegistroAtendimento}</a></td>
											<td width="30%" align="center">${consultarImovelRegistroAtendimentoHelper.tipoSolicitacao}</td>
											<td width="33%" align="left">${consultarImovelRegistroAtendimentoHelper.especificacao}</td>
											<td width="21%" align="center">${consultarImovelRegistroAtendimentoHelper.dataAtendimento}</td>
											<td width="21%" align="center">${consultarImovelRegistroAtendimentoHelper.situacao}</td>
										</tr>
									</logic:iterate>
								</logic:present>
								</table>
								</div>
								</td>
							</tr>
						</table>
					</div>
					</td>
				</tr>
				<!-- Debitos -->
				<!-- Contas -->
				<tr bgcolor="#cbe5fe">
					<td align="center" colspan="2">
					<div id="layerHideDebitos" style="display:block">
						<table width="100%" border="0" bgcolor="#99CCFF">
							<tr bgcolor="#99CCFF">
								<td height="18" colspan="5" align="center">
						            <a href="javascript:extendeTabela('Debitos',true);" >
		                  				<b>Débitos</b>
		                  			</a>
									
								</td>
							</tr>
						</table>
					</div>
					<div id="layerShowDebitos" style="display:none">
						<table width="100%" border="0" bgcolor="#99CCFF">
							<tr bgcolor="#99CCFF">
								<td height="18" colspan="5" align="center">
						            <a href="javascript:extendeTabela('Debitos',false);" >
		                  				<b>Débitos</b>
		                  			</a>						
								</td>
							</tr>
						</table>
						<!-- Grid de Contas, Situacao de Cobranca, Historico de Retorno de Negativacoes, Debito a Cobrar e Guia de Pagamento -->
						<jsp:include page="/jsp/atendimentopublico/registroatendimento/imovel_debitos.jsp" />						
					</div>
				</td>
			</tr>
				
				<!-- Pagamentos -->
				<!-- Pagamento das Contas -->
				<tr bgcolor="#cbe5fe">
					<td align="center" colspan="2">
					<div id="layerHidePag" style="display:block">
						<table width="100%" border="0" bgcolor="#99CCFF">
							<tr bgcolor="#99CCFF">
								<td height="18" colspan="5" align="center">
						            <a href="javascript:extendeTabela('Pag',true);" >
		                  				<b>Pagamentos</b>
		                  			</a>
									
								</td>
							</tr>
						</table>
					</div>
					<div id="layerShowPag" style="display:none">
						<table width="100%" border="0" bgcolor="#99CCFF">
							<tr bgcolor="#99CCFF">
								<td height="18" colspan="5" align="center">
						            <a href="javascript:extendeTabela('Pag',false);" >
		                  				<b>Pagamentos</b>
		                  			</a>						
								</td>
							</tr>
						</table>
						<table width="100%" align="center" bgcolor="#99CCFF" border="0"
						cellpadding="0" cellspacing="0" class="fontePequena">

						<tr bgcolor="#79bbfd" height="20">
							<td align="center"><strong>Pagamentos das Contas</strong></td>
						</tr>
						<%int cont1 = 1;%>

						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">

									<td bgcolor="#90c7fc" width="12%" align="center" rowspan="2"><strong>Mês/Ano
									Conta</strong></td>
									<td bgcolor="#90c7fc" width="14%" align="center" rowspan="2"><strong>Valor
									da Conta</strong></td>
									<td bgcolor="#90c7fc" width="14%" align="center" rowspan="2"><strong>Valor
									do Pag.</strong></td>
									<td bgcolor="#90c7fc" width="14%" align="center" rowspan="2"><strong>Data
									do Pag.</strong></td>
									<td bgcolor="#90c7fc" width="14%" align="center" rowspan="2"><strong>Arrecadador</strong>
									</td>
									<td bgcolor="#90c7fc" width="32%" align="center" colspan="2"><strong>Situação</strong>
									</td>
								</tr>
								<tr>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong></td>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong></td>
								</tr>
								<%if( (session.getAttribute("qtdePagContas") == null)) {%>
										<!--  Para carregar a tabela vazia -->

								<%}else if( (session.getAttribute("qtdePagContas") != null) && ((Integer) session.getAttribute("qtdePagContas") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO)) {%>

								<!-- Pagamento com conta  -->
								<logic:present name="colecaoPagamentosImovelConta"
									scope="session">
									<logic:notEmpty name="colecaoPagamentosImovelConta"
										scope="session">
										<logic:iterate name="colecaoPagamentosImovelConta"
											id="pagamento" type="Pagamento">
											<%cont1 = cont1 + 1;
											if (cont1 % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {%>
											<tr bgcolor="#cbe5fe">
												<%}%>

												<td width="12%" align="center"> 
 												   <logic:notEmpty	name="pagamento" property="contaGeral">
													   <logic:notEmpty name="pagamento" property="contaGeral.conta">
													   		<logic:notEmpty name="pagamento" property="contaGeral.conta.id">
														   <logic:notEmpty name="pagamento" property="contaGeral.conta.referencia">
															   <a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaSemCodigoBarras=1&contaID=<%="" + pagamento.getContaGeral().getId() %>' , 600, 800);">${pagamento.contaGeral.conta.formatarAnoMesParaMesAno}</a>
  														   </logic:notEmpty>
														   <logic:empty name="pagamento" property="contaGeral.conta.referencia">
																<font color="#ff0000">${pagamento.formatarAnoMesPagamentoParaMesAno}</font>															
														   </logic:empty>
														    </logic:notEmpty>	
													   </logic:notEmpty>
												       <logic:notEmpty name="pagamento" property="contaGeral.contaHistorico">
													         <font color="#000000">${pagamento.formatarAnoMesPagamentoParaMesAno}</font>
												       </logic:notEmpty>														
												  </logic:notEmpty>
												  <logic:empty name="pagamento" property="contaGeral">
													<font color="#ff0000">${pagamento.formatarAnoMesPagamentoParaMesAno}</font>
												  </logic:empty>
												</td>
												<td width="14%" align="right"><logic:notEmpty
													name="pagamento" property="contaGeral">
													<logic:notEmpty name="pagamento"
														property="contaGeral.conta">
													<logic:notEmpty name="pagamento"
														property="contaGeral.conta.valorTotal">
														<bean:write name="pagamento" property="contaGeral.conta.valorTotal"
															formatKey="money.format" />&nbsp;
													</logic:notEmpty>
													</logic:notEmpty>
													<logic:notEmpty name="pagamento"
														property="contaGeral.contaHistorico">
														<logic:notEmpty name="pagamento"
														property="contaGeral.contaHistorico.valorTotal">
														<bean:write name="pagamento" property="contaGeral.contaHistorico.valorTotal"
															formatKey="money.format" />&nbsp;
													</logic:notEmpty>
													</logic:notEmpty>
												</logic:notEmpty></td>
												<td width="14%" align="right"><bean:write name="pagamento"
													property="valorPagamento" formatKey="money.format" />&nbsp;
												</td>
												<td width="14%" align="center">
													<a href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?caminhoRetornoTelaPagamento=exibirConsultarRegistroAtendimentoPendentesImovelAction&idPagamento=<%=""+ pagamento.getId()%>' ,800,600);">
														<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
													</a>
												</td>
												<td width="14%" align="center">
												${pagamento.avisoBancario.arrecadador.cliente.nome}&nbsp;
												</td>
												<td width="16%">
												${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
												</td>
												<td width="16%">
												${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</logic:present>

								<!--  Pagamento com conta em historico -->
								<logic:present name="colecaoPagamentosImovelContaHistorico"
									scope="session">
									<logic:notEmpty name="colecaoPagamentosImovelContaHistorico"
										scope="session">
										<logic:iterate name="colecaoPagamentosImovelContaHistorico"
											id="pagamento" type="Pagamento">
											<%cont1 = cont1 + 1;
											if (cont1 % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {

											%>
											<tr bgcolor="#cbe5fe">
												<%}%>

												<td width="12%" align="center">
												<logic:notEmpty name="pagamento" property="contaGeral">
												<logic:notEmpty name="pagamento" property="contaGeral.conta">
														<logic:notEmpty name="pagamento" property="contaGeral.conta.id">
															<logic:notEmpty name="pagamento" property="contaGeral.conta.anoMesReferenciaPagamento">
																<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamento.getContaGeral().getContaHistorico().getId() %>' , 600, 800);"><font color="#ff0000" > ${pagamento.contaHistorico.formatarAnoMesParaMesAno}</font></a>
															</logic:notEmpty>
														</logic:notEmpty>
												</logic:notEmpty>
												</logic:notEmpty>
												 <logic:present
													name="pagamento"
													property="anoMesReferenciaPagamento">
													<font color="#ff0000">${pagamento.formatarAnoMesPagamentoParaMesAno}</font>
												</logic:present></td>
												<td width="14%" align="right"><logic:notEmpty
													name="pagamento" property="contaGeral">
													<logic:notEmpty
													name="pagamento" property="contaGeral.contaHistorico">
													<logic:notEmpty name="pagamento"
														property="contaGeral.contaHistorico.valorTotal">
														<font color="#ff0000"> <bean:write
															name="pagamento" property="contaGeral.contaHistorico.valorTotal"
															formatKey="money.format" /> </font>&nbsp;
													</logic:notEmpty>
													</logic:notEmpty>
												</logic:notEmpty></td>
												<td width="14%" align="right"><font color="#ff0000"> <bean:write
													name="pagamento" property="valorPagamento"
													formatKey="money.format" /> </font> &nbsp;</td>
												<td width="14%" align="center"><font color="#ff0000"> 
													<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?caminhoRetornoTelaPagamento=exibirConsultarRegistroAtendimentoPendentesImovelAction&pagamento=OK&idPagamento=<%=""+ pagamento.getId()%>' ,800,600);">
														<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
													</a>
												</td>
													
												<td width="14%" align="center">
													<font color="#ff0000">${pagamento.avisoBancario.arrecadador.cliente.nome}</font>&nbsp;
												</td>
												<td width="16%"><font color="#ff0000">
												${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}
												</font> &nbsp;</td>
												<td width="16%"><font color="#ff0000">
												${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}
												</font> &nbsp;</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</logic:present>


								<!-- Pagamento Historico com conta em historico -->

								<logic:present name="colecaoPagamentosHistoricoImovelConta"
									scope="session">
									<logic:notEmpty name="colecaoPagamentosHistoricoImovelConta"
										scope="session">
										<logic:iterate name="colecaoPagamentosHistoricoImovelConta"
											id="pagamentoHistorico" type="PagamentoHistorico">
											<%cont1 = cont1 + 1;
											if (cont1 % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {

												%>
											<tr bgcolor="#cbe5fe">
												<%}%>

												<td width="12%" align="center"><logic:present
													name="pagamentoHistorico"
													property="anoMesReferenciaPagamento">
													<font color="#ff0000">${pagamentoHistorico.formatarAnoMesReferenciaPagamento}</font>
												</logic:present></td>
												<td width="14%" align="right"><logic:notEmpty
													name="pagamentoHistorico" property="contaGeral">
													<logic:notEmpty
													name="pagamentoHistorico" property="contaGeral.contaHistorico">
													<logic:notEmpty name="pagamentoHistorico"
														property="contaGeral.contaHistorico.valorTotal">
														<font color="#ff0000"> <bean:write
															name="pagamentoHistorico" property="contaGeral.contaHistorico.valorTotal"
															formatKey="money.format" /> </font>&nbsp;
													</logic:notEmpty>
													</logic:notEmpty>
												</logic:notEmpty></td>
												<td width="14%" align="right"><font color="#ff0000"> <bean:write
													name="pagamentoHistorico" property="valorPagamento"
													formatKey="money.format" /> </font> &nbsp;</td>
												<td width="14%" align="center"><font color="#ff0000"> 
													<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?caminhoRetornoTelaPagamento=exibirConsultarRegistroAtendimentoPendentesImovelAction&pagamentoHistorico=OK&idPagamento=<%=""+ pagamentoHistorico.getId()%>' ,800,600);">
														<bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" />
													</a>
												</td>
													
												<td width="14%" align="center">
													<font color="#ff0000">${pagamentoHistorico.avisoBancario.arrecadador.cliente.nome}</font>&nbsp;
												</td>
												<td width="16%"><font color="#ff0000">
												${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
												</font> &nbsp;</td>
												<td width="16%"><font color="#ff0000">
												${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
												</font> &nbsp;</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</logic:present>
								
								<%}else if( (session.getAttribute("qtdePagContas") != null) && ((Integer) session.getAttribute("qtdePagContas") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO)) {%>
																<tr>
									<td height="190" colspan="7">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%">


										<logic:present name="colecaoPagamentosImovelConta"
											scope="session">
											<logic:notEmpty name="colecaoPagamentosImovelConta"
												scope="session">
												<logic:iterate name="colecaoPagamentosImovelConta"
													id="pagamento" type="Pagamento">
													<%cont1 = cont1 + 1;
													if (cont1 % 2 == 0) {%>
													<tr bgcolor="#FFFFFF">
														<%} else {%>
													<tr bgcolor="#cbe5fe">
														<%}%>

														<td width="12%" align="center"> <logic:notEmpty
															name="pagamento" property="contaGeral">
															<logic:notEmpty name="pagamento" property="contaGeral.conta">
															<logic:notEmpty name="pagamento" property="contaGeral.conta.id">
																<logic:notEmpty name="pagamento"
																	property="contaGeral.conta.referencia">
																	<a
																		href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaSemCodigoBarras=1&contaID=<%="" + pagamento.getContaGeral().getId() %>' , 600, 800);">${pagamento.contaGeral.conta.formatarAnoMesParaMesAno}</a>
																</logic:notEmpty>
														<logic:empty name="pagamento"
															property="contaGeral.conta.referencia">
																${pagamento.formatarAnoMesPagamentoParaMesAno}															
														</logic:empty>	
																
															</logic:notEmpty>
												<logic:empty
													name="pagamento" property="contaGeral.conta.id">
													${pagamento.formatarAnoMesPagamentoParaMesAno}
												</logic:empty>																
														</logic:notEmpty>
												<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico">
													         ${pagamento.formatarAnoMesPagamentoParaMesAno}
												</logic:notEmpty>
													</logic:notEmpty>
													<logic:empty
													name="pagamento" property="contaGeral">
													${pagamento.formatarAnoMesPagamentoParaMesAno}
													</logic:empty>
														</td>
														<td width="13%" align="right"><logic:notEmpty
															name="pagamento" property="contaGeral">
															<logic:notEmpty name="pagamento" property="contaGeral.conta">
															<logic:notEmpty name="pagamento"
																property="contaGeral.conta.valorTotal">
																<bean:write name="pagamento" property="contaGeral.conta.valorTotal"
																	formatKey="money.format" />&nbsp;
													</logic:notEmpty>
														</logic:notEmpty>
														<logic:notEmpty
															name="pagamento" property="contaGeral.contaHistorico">
															<logic:notEmpty name="pagamento"
																property="contaGeral.contaHistorico.valorTotal">
																<font color="#000000"> <bean:write
																	name="pagamento" property="contaGeral.contaHistorico.valorTotal"
																	formatKey="money.format" /> </font>&nbsp;
													</logic:notEmpty>
													</logic:notEmpty></logic:notEmpty></td>
														<td width="16%" align="right"><bean:write name="pagamento"
															property="valorPagamento" formatKey="money.format" />&nbsp;
														</td>
														<td width="14%" align="center">
															<a href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?caminhoRetornoTelaPagamento=exibirConsultarRegistroAtendimentoPendentesImovelAction&idPagamento=<%=""+ pagamento.getId()%>' ,800,600);">
																<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
															</a>
														</td>
														<td width="15%" align="center">
														${pagamento.avisoBancario.arrecadador.cliente.nome}&nbsp;
														</td>
														<td width="17%">
														${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
														</td>
														<td width="13.5%">
														${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
														</td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</logic:present>
										
										<!--  Pagamento com conta em historico  -->
										<logic:present name="colecaoPagamentosImovelContaHistorico"
											scope="session">
											<logic:notEmpty name="colecaoPagamentosImovelContaHistorico"
												scope="session">
												<logic:iterate name="colecaoPagamentosImovelContaHistorico"
													id="pagamento" type="Pagamento">
													<%cont1 = cont1 + 1;
													if (cont1 % 2 == 0) {%>
													<tr bgcolor="#FFFFFF">
														<%} else {%>
													<tr bgcolor="#cbe5fe">
														<%}%>

														<td width="12%" align="center"><logic:present
															name="pagamento"
															property="anoMesReferenciaPagamento">
															<font color="#ff0000">${pagamento.formatarAnoMesPagamentoParaMesAno}</font>
														</logic:present></td>
														<td width="13%" align="right"><logic:notEmpty
															name="pagamento" property="contaGeral">
															<logic:notEmpty
															name="pagamento" property="contaGeral.contaHistorico">
															<logic:notEmpty name="pagamento"
																property="contaGeral.contaHistorico.valorTotal">
																<font color="#ff0000"> <bean:write
																	name="pagamento" property="contaGeral.contaHistorico.valorTotal"
																	formatKey="money.format" /> </font>&nbsp;
													</logic:notEmpty>
													</logic:notEmpty>
														</logic:notEmpty></td>
														<td width="16%" align="right"><font color="#ff0000"> <bean:write
															name="pagamento" property="valorPagamento"
															formatKey="money.format" /> </font> &nbsp;</td>
														<td width="14%" align="center"> 
															<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?caminhoRetornoTelaPagamento=exibirConsultarRegistroAtendimentoPendentesImovelAction&pagamento=OK&idPagamento=<%=""+ pagamento.getId()%>' ,800,600);">
																<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
															</a>
														</td>
														<td width="15%" align="center">
														<font color="#ff0000">${pagamento.avisoBancario.arrecadador.cliente.nome}</font>&nbsp;
														</td>
														<td width="17%"><font color="#ff0000">
														${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}
														</font> &nbsp;</td>
														<td width="13.5%"><font color="#ff0000">
														${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}
														</font> &nbsp;</td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</logic:present>


										<!-- Pagamento Historico com Conta Historico-->

										<logic:present name="colecaoPagamentosHistoricoImovelConta"
											scope="session">
											<logic:notEmpty name="colecaoPagamentosHistoricoImovelConta"
												scope="session">
												<logic:iterate name="colecaoPagamentosHistoricoImovelConta"
													id="pagamentoHistorico" type="PagamentoHistorico">
													<%cont1 = cont1 + 1;
													if (cont1 % 2 == 0) {%>
													<tr bgcolor="#FFFFFF">
														<%} else {%>
													<tr bgcolor="#cbe5fe">
														<%}%>

														<td width="12%" align="center"><logic:present
															name="pagamentoHistorico"
															property="anoMesReferenciaPagamento">
															<font color="#ff0000">${pagamentoHistorico.formatarAnoMesReferenciaPagamento}</font>
														</logic:present></td>
														<td width="13%" align="right"><logic:notEmpty
															name="pagamentoHistorico" property="contaGeral">
															<logic:notEmpty
															name="pagamentoHistorico" property="contaGeral.contaHistorico">
															<logic:notEmpty name="pagamentoHistorico"
																property="contaGeral.contaHistorico.valorTotal">
																<font color="#ff0000"> <bean:write
																	name="pagamentoHistorico" property="contaGeral.contaHistorico.valorTotal"
																	formatKey="money.format" /> </font>&nbsp;
													</logic:notEmpty>
													</logic:notEmpty>
														</logic:notEmpty></td>
														<td width="16%" align="right"><font color="#ff0000"> <bean:write
															name="pagamentoHistorico" property="valorPagamento"
															formatKey="money.format" /> </font> &nbsp;</td>
														<td width="14%" align="center"> 
															<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?caminhoRetornoTelaPagamento=exibirConsultarRegistroAtendimentoPendentesImovelAction&pagamentoHistorico=OK&idPagamento=<%=""+ pagamentoHistorico.getId()%>' ,800,600);">
																<bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" />
															</a>
														</td>
														<td width="15%" align="center">
														<font color="#ff0000">${pagamentoHistorico.avisoBancario.arrecadador.cliente.nome}</font>&nbsp;
														</td>
														<td width="17%"><font color="#ff0000">
														${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
														</font> &nbsp;</td>
														<td width="13.5%"><font color="#ff0000">
														${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
														</font> &nbsp;</td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</logic:present>

									</table>
									</div>
									</td>
								</tr>
								<%}%>

							</table>
							</td>
						</tr>
					</table>
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					<!-- Pagamentos das Guias de Pagamentos -->
					<table width="100%" align="center" bgcolor="#99CCFF" border="0"
						cellpadding="0" cellspacing="0" class="fontePequena">

						<tr bgcolor="#79bbfd" height="20">
							<td align="center"><strong>Pagamentos das Guias de Pagamento</strong>
							</td>
						</tr>
						<%int contad = 1;%>

						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">

									<td width="12%" align="center" rowspan="2"><strong>Cliente</strong>
									</td>
									<td width="11%" align="center" rowspan="2"><strong>Tipo do
									Débito</strong></td>
									<td width="11%" align="center" rowspan="2"><strong>Valor da
									Guia de Pagto.</strong></td>
									<td width="11%" align="center" rowspan="2"><strong>Valor do
									Pag.</strong></td>
									<td width="11%" align="center" rowspan="2"><strong>Data do Pag.</strong>
									</td>
									<td  width="14%" align="center" rowspan="2"><strong>Arrecadador</strong>
									</td>
									<td width="30%" align="center" colspan="2"><strong>Situação</strong>
									</td>
								</tr>
								<tr>
									<td width="15%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong></td>
									<td width="15%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong></td>
								</tr>
								<%if((session.getAttribute("qtdePagGuiaPagamento") == null)) {%>
									<!-- Para carregar a tabela vazia -->

								<%}else if((session.getAttribute("qtdePagGuiaPagamento") != null) && ((Integer) session.getAttribute("qtdePagGuiaPagamento") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO)) {%>

								<logic:present name="colecaoPagamentosImovelGuiaPagamento"
									scope="session">
									<logic:notEmpty name="colecaoPagamentosImovelGuiaPagamento"
										scope="session">
										<logic:iterate name="colecaoPagamentosImovelGuiaPagamento"
											id="pagamento" type="Pagamento">
											<%contad = contad + 1;
											if (contad % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {%>
											<tr bgcolor="#cbe5fe">
												<%}%>

												<td width="12%" align="center">
												${pagamento.cliente.id}&nbsp;</td>
												<td width="11%" align="center">
													
													<logic:present name="pagamento" property="guiaPagamento">
														
														<logic:present name="pagamento" property="guiaPagamento.valorDebito">
															<a 
																href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + pagamento.getGuiaPagamento().getId() %>')">${pagamento.debitoTipo.descricao}
															</a>&nbsp;
														</logic:present>
														
														<logic:notPresent name="pagamento" property="guiaPagamento.valorDebito">
															<a 
																href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoHistoricoId=<%="" + pagamento.getGuiaPagamento().getId() %>')">${pagamento.debitoTipo.descricao}
															</a>&nbsp;
														</logic:notPresent>
														
													</logic:present>
													
													<logic:notPresent name="pagamento" property="guiaPagamento">
														${pagamento.debitoTipo.descricao}													
													</logic:notPresent>			
														
												</td>
												<td width="11%" align="right">
													<logic:present name="pagamento" property="guiaPagamento">
														<logic:present name="pagamento" property="guiaPagamento.valorDebito">
															<bean:write name="pagamento" property="guiaPagamento.valorDebito" formatKey="money.format" />&nbsp;
														</logic:present>
													</logic:present>
												</td>
												<td width="11%" align="right">
													<bean:write name="pagamento" property="valorPagamento" formatKey="money.format" />&nbsp;
												</td>
												<td width="11%" align="center">
													<a  href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?caminhoRetornoTelaPagamento=exibirConsultarRegistroAtendimentoPendentesImovelAction&idPagamento=<%=""+ pagamento.getId()%>' ,800,600);">
														<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
													</a>
												</td>	
													
												<td width="14%" align="center">
													${pagamento.avisoBancario.arrecadador.cliente.nome}&nbsp;
												</td>
												<td width="15%">
													${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
												</td>
												<td width="15%">
													${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</logic:present>


								<!--  Historico -->

								<logic:present
									name="colecaoPagamentosHistoricoImovelGuiaPagamento"
									scope="session">
									<logic:notEmpty
										name="colecaoPagamentosHistoricoImovelGuiaPagamento"
										scope="session">
										<logic:iterate
											name="colecaoPagamentosHistoricoImovelGuiaPagamento"
											id="pagamentoHistorico" type="PagamentoHistorico">
											<%contad = contad + 1;
											if (contad % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {%>
											<tr bgcolor="#cbe5fe">
												<%}%>

												<td width="12%" align="center">
												${pagamento.cliente.id}&nbsp;</td>
												
												<td width="11%" align="center">
													<logic:present name="pagamentoHistorico" property="guiaPagamentoGeral">
														<logic:present name="pagamentoHistorico" property="guiaPagamentoGeral.guiaPagamentoHistorico">
															<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoHistoricoId=<%="" 
															+ pagamentoHistorico.getGuiaPagamentoGeral().getGuiaPagamentoHistorico().getId() %>')">
															<font color="#ff0000">${pagamentoHistorico.debitoTipo.descricao}</font></a>&nbsp;
														</logic:present>
														<logic:notPresent name="pagamentoHistorico" property="guiaPagamentoGeral.guiaPagamentoHistorico">
															<font color="#ff0000">	${pagamentoHistorico.debitoTipo.descricao}</font>
														</logic:notPresent>												
													</logic:present>		
													<logic:notPresent name="pagamentoHistorico" property="guiaPagamentoGeral">
														<font color="#ff0000">	${pagamentoHistorico.debitoTipo.descricao}</font>													
													</logic:notPresent>	
												</td>
												
												<td width="11%" align="right">
													<logic:present name="pagamentoHistorico" property="guiaPagamentoGeral">
														<logic:present name="pagamentoHistorico" property="guiaPagamentoGeral.guiaPagamentoHistorico">
															<font color="#ff0000"> 
																<bean:write
																name="pagamentoHistorico"
																property="guiaPagamentoGeral.guiaPagamentoHistorico.valorDebito"
																formatKey="money.format" /> </font>
																		&nbsp;
														</logic:present>
													</logic:present>		
												</td>
												
												<td width="11%" align="right"><font color="#ff0000"> <bean:write
													name="pagamentoHistorico" property="valorPagamento"
													formatKey="money.format" /> </font> &nbsp;</td>
												
												<td width="11%" align="center">
													<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?caminhoRetornoTelaPagamento=exibirConsultarRegistroAtendimentoPendentesImovelAction&pagamentoHistorico=OK&idPagamento=<%=""+ pagamentoHistorico.getId()%>' ,800,600);">
														<bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" />
													</a>&nbsp;
												</td>
												<td width="14%" align="center">
													<font color="#ff0000">${pagamentoHistorico.avisoBancario.arrecadador.cliente.nome}</font>&nbsp;
												</td>
												
												<td width="15%"><font color="#ff0000">
												${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
												</font> &nbsp;</td>
												<td width="15%"><font color="#ff0000">
												${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
												</font> &nbsp;</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</logic:present>

								<%}else if((session.getAttribute("qtdePagGuiaPagamento") != null) && ((Integer) session.getAttribute("qtdePagGuiaPagamento") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO)) {%>

								<tr>
									<td height="190" colspan="7">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%">

										<logic:present name="colecaoPagamentosImovelGuiaPagamento"
											scope="session">
											<logic:notEmpty name="colecaoPagamentosImovelGuiaPagamento"
												scope="session">
												<logic:iterate name="colecaoPagamentosImovelGuiaPagamento"
													id="pagamento" type="Pagamento">
													<%contad = contad + 1;
													if (contad % 2 == 0) {%>
													<tr bgcolor="#FFFFFF">
														<%} else {%>
													<tr bgcolor="#cbe5fe">
														<%}%>

														<td width="12%" align="center">
														${pagamento.cliente.id}&nbsp;</td>
														<td width="11%" align="center"><logic:present
															name="pagamento" property="guiaPagamento">
															<a
																href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + pagamento.getGuiaPagamento().getId() %>')">${pagamento.debitoTipo.descricao}</a>&nbsp;
														</logic:present>
																										<logic:notPresent
													name="pagamento" property="guiaPagamento">
													${pagamento.debitoTipo.descricao}													
												</logic:notPresent>			
														</td>
														<td width="11%" align="right"><logic:notEmpty
															name="pagamento" property="guiaPagamento">
															<logic:notEmpty name="pagamento"
																property="guiaPagamento.valorDebito">
																<bean:write name="pagamento"
																	property="guiaPagamento.valorDebito"
																	formatKey="money.format" />&nbsp;
													</logic:notEmpty>
														</logic:notEmpty></td>
														<td width="11%" align="right"><bean:write name="pagamento"
															property="valorPagamento" formatKey="money.format" />&nbsp;
														</td>
														<td width="11%" align="center">
															<a  href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?caminhoRetornoTelaPagamento=exibirConsultarRegistroAtendimentoPendentesImovelAction&idPagamento=<%=""+ pagamento.getId()%>' ,800,600);">
																<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
															</a>&nbsp;
														</td>
														<td width="14%" align="center">
															${pagamento.avisoBancario.arrecadador.cliente.nome}&nbsp;
														</td>	
														
														<td width="15.5%">
														${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
														</td>
														<td width="13.5%">
														${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
														</td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</logic:present>


										<!--  Historico -->

										<logic:present
											name="colecaoPagamentosHistoricoImovelGuiaPagamento"
											scope="session">
											<logic:notEmpty
												name="colecaoPagamentosHistoricoImovelGuiaPagamento"
												scope="session">
												<logic:iterate
													name="colecaoPagamentosHistoricoImovelGuiaPagamento"
													id="pagamentoHistorico" type="PagamentoHistorico">
													<%contad = contad + 1;
													if (contad % 2 == 0) {%>
													<tr bgcolor="#FFFFFF">
														<%} else {%>
													<tr bgcolor="#cbe5fe">
														<%}%>

														<td width="12%" align="center">
														${pagamento.cliente.id}&nbsp;</td>
														
														<td width="11%" align="center">
														
															<logic:present name="pagamentoHistorico" property="guiaPagamentoGeral">
															
																<logic:present name="pagamentoHistorico" property="guiaPagamentoGeral.guiaPagamentoHistorico">
																	
																	<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoHistoricoId=<%="" 
																	+ pagamentoHistorico.getGuiaPagamentoGeral().getGuiaPagamentoHistorico().getId() %>')"><font
																	color="#ff0000">${pagamentoHistorico.debitoTipo.descricao}</font></a>&nbsp;
																
																</logic:present>
																
																<logic:notPresent name="pagamentoHistorico" property="guiaPagamentoGeral.guiaPagamentoHistorico">
																	<font color="#ff0000">	${pagamentoHistorico.debitoTipo.descricao}</font>													
																</logic:notPresent>
															
															</logic:present>
															
															<logic:notPresent name="pagamentoHistorico" property="guiaPagamentoGeral">
																<font color="#ff0000">	${pagamentoHistorico.debitoTipo.descricao}</font>													
															</logic:notPresent>			
														
														</td>
														
														<td width="11%" align="right">
														
															<logic:present name="pagamentoHistorico" property="guiaPagamentoGeral">
															
																<logic:present name="pagamentoHistorico" property="guiaPagamentoGeral.guiaPagamentoHistorico">
																
																	<font color="#ff0000"> <bean:write
																		name="pagamentoHistorico"
																		property="guiaPagamentoGeral.guiaPagamentoHistorico.valorDebito"
																		formatKey="money.format" /> </font>
																		&nbsp;
																</logic:present>
														
															</logic:present>
														
														</td>
														
														<td width="11%" align="right"><font color="#ff0000"> <bean:write
															name="pagamentoHistorico" property="valorPagamento"
															formatKey="money.format" /> </font> &nbsp;</td>
														
														<td width="11%" align="center">
															<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?caminhoRetornoTelaPagamento=exibirConsultarRegistroAtendimentoPendentesImovelAction&pagamentoHistorico=OK&idPagamento=<%=""+ pagamentoHistorico.getId()%>' ,800,600);">
																<bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" />
															</a>
														</td>
															
														<td width="14%" align="center">
															<font color="#ff0000">${pagamentoHistorico.avisoBancario.arrecadador.cliente.nome}</font>&nbsp;
														</td>
														<td width="15.5%"><font color="#ff0000">
														${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
														</font> &nbsp;</td>
														<td width="13.5%"><font color="#ff0000">
														${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
														</font> &nbsp;</td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</logic:present>
									</table>
									</div>
									</td>
								</tr>
								<%}%>
							</table>
							</td>
						</tr>
					</table>
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					<!-- Pagamentos dos Debitos a Cobrar -->
					<table width="100%" align="center" bgcolor="#99CCFF" border="0"
						cellpadding="0" cellspacing="0" class="fontePequena">

						<tr bgcolor="#79bbfd" height="20">
							<td align="center"><strong>Pagamentos dos Débitos a Cobrar</strong>
							</td>
						</tr>
						<%int contador = 1;%>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">
									<td width="18%" align="center" rowspan="2"><strong>Tipo do
									Débito</strong></td>
									<td width="16%" align="center" rowspan="2"><strong>Valor a Ser
									Cobrado</strong></td>
									<td width="14%" align="center" rowspan="2"><strong>Valor do
									Pag.</strong></td>
									<td width="14%" align="center" rowspan="2"><strong>Data do Pag.</strong>
									</td>
									<td  width="14%" align="center" rowspan="2"><strong>Arrecadador</strong>
									</td>
									<td width="24%" align="center" colspan="2"><strong>Situação</strong>
									</td>
								</tr>
								<tr>
									<td width="12%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong>
									</td>
									<td width="12%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong>
									</td>
								</tr>

								<%--Esquema de paginação--%>
								<%if((session.getAttribute("qtdePagDebitoACobrar") == null)) {%>
									<!-- Para carregar a tabela vazia -->

								<%}else if((session.getAttribute("qtdePagDebitoACobrar") != null) && ((Integer) session.getAttribute("qtdePagDebitoACobrar") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO)) {%>

								<logic:present name="colecaoPagamentosImovelDebitoACobrar"
									scope="session">
									<logic:iterate name="colecaoPagamentosImovelDebitoACobrar"
										id="pagamento" type="Pagamento">
										<%contador = contador + 1;
										if (contador % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td width="18%" align="center">
											
											<logic:notEmpty
															name="pagamento" property="debitoACobrarGeral">
												<logic:notEmpty
																name="pagamento" property="debitoACobrarGeral.debitoACobrar">
													<a
														href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="pagamento" property="debitoACobrarGeral.id" />', 560, 660);">
													${pagamento.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}&nbsp;</a>
												</logic:notEmpty>
												
												
												<logic:notEmpty
																name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico">
													<a
														href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.imovel.id" />&debitoID=<bean:write name="pagamento" property="debitoACobrarGeral.id" />', 560, 660);">
													${pagamento.debitoACobrarGeral.debitoACobrarHistorico.debitoTipo.descricao}&nbsp;</a>
												</logic:notEmpty>
											
											</logic:notEmpty>
											
											
											<logic:empty name="pagamento" property="debitoACobrarGeral">
												
													${pagamento.debitoACobrarGeral.debitoTipo.descricao}&nbsp;
												
												
											</logic:empty>		
											</td>
											<td width="16%" align="right">
												<logic:notEmpty name="pagamento" property="debitoACobrarGeral">
													<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrar">
														<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrar.valorDebito">
															<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrar.numeroPrestacaoDebitoMenosBonus">
																<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrar.numeroPrestacaoCobradas">
																	<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrar.valorTotalComBonus" formatKey="money.format" />&nbsp;
																</logic:notEmpty>
															</logic:notEmpty>
														</logic:notEmpty>
													</logic:notEmpty>
													
													
													<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico">
														<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.valorDebito">
															<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.numeroPrestacaoDebitoMenosBonus">
																<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.prestacaoCobradas">
																	<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus" formatKey="money.format" />&nbsp;
																</logic:notEmpty>
															</logic:notEmpty>
														</logic:notEmpty>
													</logic:notEmpty>
													
												</logic:notEmpty>
											</td>
											<td width="14%" align="right">
												<a  href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?caminhoRetornoTelaPagamento=exibirConsultarRegistroAtendimentoPendentesImovelAction&idPagamento=<%=""+ pagamento.getId()%>' ,800,600);">
														<bean:write name="pagamento" property="valorPagamento" formatKey="money.format" />
												</a>&nbsp;
											</td>
											<td width="14%" align="center"><bean:write name="pagamento"
												property="dataPagamento" formatKey="date.format" />&nbsp;
											</td>
											<td width="14%" align="center">
												${pagamento.avisoBancario.arrecadador.cliente.nome}&nbsp;
											</td>
											<td width="12%">
											${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
											</td>
											<td width="12%">
											${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
											</td>
										</tr>
									</logic:iterate>
								</logic:present>

								<!-- Historico -->

								<logic:present
									name="colecaoPagamentosHistoricoImovelDebitoACobrar"
									scope="session">
									<logic:iterate
										name="colecaoPagamentosHistoricoImovelDebitoACobrar"
										id="pagamentoHistorico" type="PagamentoHistorico">

										<%contador = contador + 1;
										if (contador % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td width="18%" align="center">
											
											
											<logic:notEmpty	name="pagamentoHistorico" property="debitoACobrarGeral">	
													<logic:notEmpty	name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico">
													<a
														href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.imovel.id" />&debitoID=<bean:write name="pagamentoHistorico" property="debitoACobrarGeral.id" />', 560, 660);">
													<font color="#ff0000">
													${pagamentoHistorico.debitoACobrarGeral.debitoACobrarHistorico.debitoTipo.descricao} </font>
													&nbsp;</a>
													</logic:notEmpty> 
											</logic:notEmpty> 

											<logic:empty name="pagamentoHistorico" property="debitoACobrarGeral">
												<font color="#ff0000">${pagamentoHistorico.debitoTipo.descricao}&nbsp;</font>
											</logic:empty>		
											
											</td>
											<td width="16%" align="right">
												<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral">
													<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico">
															<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.valorDebito">
																<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.numeroPrestacaoDebitoMenosBonus">
																	<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.prestacaoCobradas">
																		<font color="#ff0000"> 
																			<bean:write name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus" formatKey="money.format" /> 
																		</font>		
																				&nbsp;
																	</logic:notEmpty>
																</logic:notEmpty>
															</logic:notEmpty>
													</logic:notEmpty>
												</logic:notEmpty></td>
											<td width="14%" align="right"><font color="#ff0000"> <bean:write
												name="pagamentoHistorico" property="valorPagamento"
												formatKey="money.format" /> </font> &nbsp;</td>
											<td width="14%" align="center">
												<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?caminhoRetornoTelaPagamento=exibirConsultarRegistroAtendimentoPendentesImovelAction&pagamentoHistorico=OK&idPagamento=<%=""+ pagamentoHistorico.getId()%>' ,800,600);">
													<bean:write name="pagamentoHistorico" property="dataPagamento"	formatKey="date.format" />
												</a>
											</td>
											<td width="14%" align="center">
												<font color="#ff0000">${pagamentoHistorico.avisoBancario.arrecadador.cliente.nome}</font>&nbsp;
											</td>
											<td width="12%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
											</font> &nbsp;</td>
											<td width="12%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
											</font> &nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>

								<%}else if((session.getAttribute("qtdePagDebitoACobrar") != null) && ((Integer) session.getAttribute("qtdePagDebitoACobrar") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO)) {%>
								<tr>
									<td height="190" colspan="6">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%">

										<logic:present name="colecaoPagamentosImovelDebitoACobrar"
											scope="session">
											<logic:iterate name="colecaoPagamentosImovelDebitoACobrar"
												id="pagamento" type="Pagamento">
												<%contador = contador + 1;
												if (contador % 2 == 0) {%>
												<tr bgcolor="#FFFFFF">
													<%} else {%>
												<tr bgcolor="#cbe5fe">
													<%}%>

													<td width="17%" align="center">
													<logic:notEmpty
															name="pagamento" property="debitoACobrarGeral">
													<a
														href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="pagamento" property="debitoACobrarGeral.id" />', 560, 660);">
													${pagamento.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}&nbsp;</a>
													</logic:notEmpty>
													
											<logic:empty
															name="pagamento" property="debitoACobrarGeral">
															${pagamento.debitoACobrarGeral.debitoTipo.descricao}&nbsp;
											</logic:empty>				
													
													</td>
													<td width="15%" align="right">
														<logic:notEmpty name="pagamento" property="debitoACobrarGeral">
															<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrar">
																<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrar.valorDebito">
																	<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrar.numeroPrestacaoDebitoMenosBonus">
																		<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrar.numeroPrestacaoCobradas">
																			<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrar.valorTotalComBonus" formatKey="money.format" />&nbsp;
																		</logic:notEmpty>
																	</logic:notEmpty>
																</logic:notEmpty>
															</logic:notEmpty>
															<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico">
																<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.valorDebito">
																	<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.numeroPrestacaoDebitoMenosBonus">
																		<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.numeroPrestacaoCobradas">
																			<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus" formatKey="money.format" />&nbsp;
																		</logic:notEmpty>
																	</logic:notEmpty>
																</logic:notEmpty>
															</logic:notEmpty>
														</logic:notEmpty>														
													</td>
													<td width="14%" align="right"><bean:write name="pagamento"
														property="valorPagamento" formatKey="money.format" />&nbsp;
													</td>
													<td width="14%" align="center">
														<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?caminhoRetornoTelaPagamento=exibirConsultarRegistroAtendimentoPendentesImovelAction&idPagamento=<%=""+ pagamento.getId()%>' ,800,600);">
															<bean:write name="pagamento" property="dataPagamento"	formatKey="date.format" />
														</a>
													</td>
													<td width="14%" align="center">
														${pagamento.avisoBancario.arrecadador.cliente.nome}&nbsp;
													</td>
													<td width="16.5%">
													${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
													</td>
													<td width="13.5%">
													${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
													</td>
												</tr>
											</logic:iterate>
										</logic:present>


										<!-- Historico -->

										<logic:present
											name="colecaoPagamentosHistoricoImovelDebitoACobrar"
											scope="session">
											<logic:iterate
												name="colecaoPagamentosHistoricoImovelDebitoACobrar"
												id="pagamentoHistorico" type="PagamentoHistorico">

												<%contador = contador + 1;
												if (contador % 2 == 0) {%>
												<tr bgcolor="#FFFFFF">
													<%} else {%>
												<tr bgcolor="#cbe5fe">
													<%}%>

													<td width="17%" align="center">
													<logic:notEmpty
																	name="pagamentoHistorico" property="debitoACobrarGeral">
													
														<logic:notEmpty
																	name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico">
													
														
														<a
															href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.imovel.id" />&debitoID=<bean:write name="pagamentoHistorico" property="debitoACobrarGeral.id" />', 560, 660);">
														<font color="#ff0000">
														${pagamentoHistorico.debitoACobrarGeral.debitoACobrarHistorico.debitoTipo.descricao} </font>
														&nbsp;</a>
														</logic:notEmpty>
													</logic:notEmpty>
													
													<logic:empty
															name="pagamentoHistorico" property="debitoACobrarGeral">
															<font color="#ff0000">${pagamentoHistorico.debitoACobrarGeral.debitoTipo.descricao}&nbsp;</font>
													</logic:empty>		
													</td>
													<td width="15%" align="right">
														<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral">
															<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico">
																<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.valorDebito">
																	<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.numeroPrestacaoDebitoMenosBonus">
																		<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.prestacaoCobradas">
																			<font color="#ff0000"> 
																			<bean:write name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus" formatKey="money.format" /> </font>	&nbsp;
																		</logic:notEmpty>
																	</logic:notEmpty>
																</logic:notEmpty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
													<td width="14%" align="right"><font color="#ff0000"> <bean:write
														name="pagamentoHistorico" property="valorPagamento"
														formatKey="money.format" /> </font> &nbsp;</td>
																											
													<td width="14%" align="center">
														<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?caminhoRetornoTelaPagamento=exibirConsultarRegistroAtendimentoPendentesImovelAction&pagamentoHistorico=OK&idPagamento=<%=""+ pagamentoHistorico.getId()%>' ,800,600);">
															<bean:write name="pagamentoHistorico" property="dataPagamento"	formatKey="date.format" />
														</a>
													</td>
													<td width="14%" align="center">
														<font color="#ff0000">${pagamentoHistorico.avisoBancario.arrecadador.cliente.nome}</font>&nbsp;
													</td>
													<td width="12%"><font color="#ff0000">
													${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
													</font> &nbsp;</td>
													<td width="10.5%"><font color="#ff0000">
													${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
													</font> &nbsp;</td>
												</tr>
											</logic:iterate>
										</logic:present>
									</table>
									</div>
									</td>
								</tr>
								<%}%>
							</table>
							</td>
						</tr>
					</table>	
						
					</div>
					</td>
				</tr>	
						

				<tr>
					<td align="right"><input name="button" type="button" class="bottonRightCol"
						value="Fechar"
						onclick="window.close();"
						align="right"></td>

				</tr>
			</table>
			</td>
		</tr>
	</table>

	<!-- Fim do Corpo - Thiago Tenório-->
</html:form>
</body>
</html:html>

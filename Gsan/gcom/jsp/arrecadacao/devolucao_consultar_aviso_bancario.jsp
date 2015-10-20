<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.arrecadacao.Devolucao"%>
<%@page import="gcom.arrecadacao.DevolucaoHistorico"%>
<%@page import="gcom.util.Util" isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>

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
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!--

	
-->
</script>

<style>
.fontePequena {
font-size: 11px;
face: Verdana, Arial, Helvetica, sans-serif;
}

</style>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirConsultarDevolucaoAction" method="post">

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
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Consultar Devolu��es do Aviso Banc�rio</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" class="fontePequena">

			<tr>
				<td>
					<table width="100%">
						<tr>
							<td width="25%"><strong>Arrecadador:</strong></td>
							
							<td colspan="3" align="left">
								<div align="left">
									<html:text property="idArrecadador"
										readonly="true"	style="background-color:#EFEFEF; border:0; font-size:9px;"
										size="3" maxlength="3" />
										&nbsp; 
									<html:text property="descricaoArrecadador" readonly="true"
										style="background-color:#EFEFEF; border:0; font-size:9px;"
										size="40" maxlength="40" />
								</div>
							</td>
						</tr>
			
						<tr>
							<td><strong>Data Lan�amento:</strong></td>
			
							<td colspan="3" align="left">
								<html:text property="dataLancamento"
								readonly="true" style="background-color:#EFEFEF; border:0; font-size:9px;"
								size="10" maxlength="10" />
							</td>
						</tr>
						<tr>
							<td><strong>Sequencial do Aviso:</strong></td>
							<td align="right">
							<div align="left"><html:text property="sequencialAviso"
								readonly="true"
								style="background-color:#EFEFEF; border:0; font-size:9px;"
								size="3" maxlength="3" /></div>
							</td>
						</tr>
						<tr>
							<td><strong> Tipo do Aviso:</strong></td>
							<td>
							<div align="left"><html:text property="tipoAviso" readonly="true"
								style="background-color:#EFEFEF; border:0; font-size:9px;"
								size="10" maxlength="10" /></div>
							</td>
						</tr>
						
					</table>
				</td>
			</tr>		

						
								
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0" class="fontePequena">
						<tr bgcolor="#79bbfd" height="20">
							<td align="center">
								<strong>Devolu��es das Contas</strong>
							</td>
						</tr>
						<%int cont = 1;%>
						
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">
									<td width="13%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Im�vel</strong></td>
									<td width="12%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>M�s/Ano
									Conta</strong></td>
									<td width="15%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor
									da Conta</strong></td>
									<td width="15%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor
									da Devol.</strong></td>
									<td width="13%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Data
									da Devol.</strong></td>
									<td width="32%" bgcolor="#90c7fc" align="center" colspan="2"><strong>Situa��o</strong></td>
								</tr>
								<tr>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong></td>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong></td>
								</tr>
								<%if ((Integer) session.getAttribute("qtdeDevContas")
									<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_DEVOLUCAO) {%>
				
								<!-- Devolu��es -->
								<logic:present name="colecaoDevolucaoAvisoBancarioConta"
									scope="request">
									
									<logic:iterate name="colecaoDevolucaoAvisoBancarioConta"
										id="devolucao" type="Devolucao">
										<%cont = cont + 1;
											if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="13%" align="center">${devolucao.imovel.id}&nbsp;</td>
											<td width="12%" align="center"><a
												href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + devolucao.getGuiaDevolucao().getConta().getId() %>' , 600, 800);">${devolucao.guiaDevolucao.conta.formatarAnoMesParaMesAno}&nbsp;</a></td>
											<td width="15%" align="right">${devolucao.guiaDevolucao.conta.valorTotal}&nbsp;</td>
											<td width="15%" align="right">${devolucao.valorDevolucao}&nbsp;</td>
											<td width="13%" align="center"><bean:write name="devolucao"
												property="dataDevolucao" formatKey="date.format" />&nbsp;</td>
											<td width="16%">${devolucao.devolucaoSituacaoAnterior.descricaoAbreviado}&nbsp;</td>
											<td width="16%">${devolucao.devolucaoSituacaoAtual.descricaoAbreviado}&nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>
								
								<!-- Devolu��es Hist�rico-->
								<logic:present name="colecaoDevolucaoHistoricoAvisoBancarioConta"
									scope="request">
									
									<logic:iterate name="colecaoDevolucaoHistoricoAvisoBancarioConta"
										id="devolucaoHistorico" type="DevolucaoHistorico">
										<%cont = cont + 1;
											if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											 <td width="13%" align="center"><font color="#ff0000" >${devolucaoHistorico.imovel.id}</font>&nbsp;</td>
											<td width="12%" align="center">
												<logic:present name="devolucaoHistorico" property="anoMesReferenciaArrecadacao">
														<font color="#ff0000" >${devolucaoHistorico.formatarAnoMesParaMesAnoArrecadacao}</font>
												</logic:present>
											 &nbsp;
											 </td>
											<td width="15%" align="right"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.conta.valorTotal}</font>&nbsp;</td>
											<td width="15%" align="right"><font color="#ff0000" >${devolucaoHistorico.valorDevolucao}</font>&nbsp;</td>
											<td width="13%" align="center"><font color="#ff0000" ><bean:write name="devolucaoHistorico"
												property="dataDevolucao" formatKey="date.format" /></font>&nbsp;</td> 
											<td width="16%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAnterior.descricaoAbreviado}</font>&nbsp;</td>
											<td width="16%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAtual.descricaoAbreviado}</font>&nbsp;</td>
											
										</tr>
									</logic:iterate>
								</logic:present>
								
								<%} else {%>
									<tr>
										<td height="190" colspan="7">
											<div style="width: 100%; height: 100%; overflow: auto;">
												<table width="100%">
													<!-- Devolu��es -->
													<logic:present name="colecaoDevolucaoAvisoBancarioConta"
														scope="request">
														
														<logic:iterate name="colecaoDevolucaoAvisoBancarioConta"
															id="devolucao" type="Devolucao">
															<%cont = cont + 1;
																if (cont % 2 == 0) {%>
															<tr bgcolor="#FFFFFF">
																<%} else {
					
																%>
															<tr bgcolor="#cbe5fe">
																<%}%>
																<td width="13%" align="center">${devolucao.imovel.id}&nbsp;</td>
																<td width="12%" align="center"><a
																	href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + devolucao.getGuiaDevolucao().getConta().getId() %>' , 600, 800);">${devolucao.guiaDevolucao.conta.formatarAnoMesParaMesAno}&nbsp;</a></td>
																<td width="15%" align="right">${devolucao.guiaDevolucao.conta.valorTotal}&nbsp;</td>
																<td width="15%" align="right">${devolucao.valorDevolucao}&nbsp;</td>
																<td width="14.3%" align="center"><bean:write name="devolucao"
																	property="dataDevolucao" formatKey="date.format" />&nbsp;</td>
																<td width="17%">${devolucao.devolucaoSituacaoAnterior.descricaoAbreviado}&nbsp;</td>
																<td width="13.7%">${devolucao.devolucaoSituacaoAtual.descricaoAbreviado}&nbsp;</td>
															</tr>
														</logic:iterate>
													</logic:present>
													
													<!-- Devolu��es Hist�rico-->
													<logic:present name="colecaoDevolucaoHistoricoAvisoBancarioConta"
														scope="request">
														
														<logic:iterate name="colecaoDevolucaoHistoricoAvisoBancarioConta"
															id="devolucaoHistorico" type="DevolucaoHistorico">
															<%cont = cont + 1;
																if (cont % 2 == 0) {%>
															<tr bgcolor="#FFFFFF">
																<%} else {
					
																%>
															<tr bgcolor="#cbe5fe">
																<%}%>
																 <td width="13%" align="center"><font color="#ff0000" >${devolucaoHistorico.imovel.id}</font>&nbsp;</td>
																<td width="12%" align="center">
																	<logic:present name="devolucaoHistorico" property="anoMesReferenciaArrecadacao">
																			<font color="#ff0000" >${devolucaoHistorico.formatarAnoMesParaMesAnoArrecadacao}</font>
																	</logic:present>
																 &nbsp;
																 </td>
																<td width="15%" align="right"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.conta.valorTotal}</font>&nbsp;</td>
																<td width="15%" align="right"><font color="#ff0000" >${devolucaoHistorico.valorDevolucao}</font>&nbsp;</td>
																<td width="14.3%" align="center"><font color="#ff0000" ><bean:write name="devolucaoHistorico"
																	property="dataDevolucao" formatKey="date.format" /></font>&nbsp;</td> 
																<td width="17%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAnterior.descricaoAbreviado}</font>&nbsp;</td>
																<td width="13.7%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAtual.descricaoAbreviado}</font>&nbsp;</td>
																
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
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0" class="fontePequena">
						<tr bgcolor="#79bbfd" height="20">
							<td align="center">
								<strong>Devolu��es das Guias de Pagamento</strong>
							</td>
						</tr>
						<%int contad = 1;%>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">
									<td width="10%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Im�vel</strong></td>
									<td width="10%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Cliente</strong></td>
									<td width="14%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Tipo
									do D�bito</strong></td>
									<td width="12%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor
									da Guia de Pagto.</strong></td>
									<td width="12%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor
									da Devol.</strong></td>
									<td width="10%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Data
									da Devol.</strong></td>
									<td width="32%" bgcolor="#90c7fc" align="center" colspan="2"><strong>Situa��o</strong></td>
								</tr>
								<tr>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong></td>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong></td>
								</tr>
								
								<%if ((Integer) session.getAttribute("qtdeDevGuiaPagamento")
									<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_DEVOLUCAO) {%>
									
								<!-- Devolu��es -->
								<logic:present name="colecaoDevolucaoAvisoBancarioGuiaPagamento"
									scope="request">
									
									<logic:iterate
										name="colecaoDevolucaoAvisoBancarioGuiaPagamento"
										id="devolucao" type="Devolucao">
										<%contad = contad + 1;
											if (contad % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

										%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="10%" align="center">${devolucao.imovel.id}&nbsp;</td>
											<td width="10%" align="center">${devolucao.cliente.id}&nbsp;</td>
											<td width="14%" align="center"><a
												href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + devolucao.getGuiaDevolucao().getGuiaPagamento().getId() %>')">${devolucao.debitoTipo.descricao}</a>&nbsp;</td>
											<td width="12%" align="right">${devolucao.guiaDevolucao.guiaPagamento.valorDebito}&nbsp;</td>
											<td width="12%" align="right">${devolucao.valorDevolucao}&nbsp;</td>
											<td width="10%" align="center"><bean:write name="devolucao"
												property="dataDevolucao" formatKey="date.format" />&nbsp;</td>
											<td width="16%">${devolucao.devolucaoSituacaoAnterior.descricaoAbreviado}&nbsp;</td>
											<td width="16%">${devolucao.devolucaoSituacaoAtual.descricaoAbreviado}&nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>
								
								<!-- Devolu��es Hist�rico -->
								<logic:present name="colecaoDevolucaoHistoricoAvisoBancarioGuiaPagamento"
									scope="request">
									
									<logic:iterate
										name="colecaoDevolucaoHistoricoAvisoBancarioGuiaPagamento"
										id="devolucaoHistorico" type="DevolucaoHistorico">
										<%contad = contad + 1;
											if (contad % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

										%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="10%" align="center"><font color="#ff0000" >${devolucaoHistorico.imovel.id}</font>&nbsp;</td>
											<td width="10%" align="center"><font color="#ff0000" >${devolucaoHistorico.cliente.id}</font>&nbsp;</td>
											<td width="14%" align="center">
											<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + devolucaoHistorico.getGuiaDevolucao().getGuiaPagamento().getId() %>')"><font color="#ff0000">${devolucaoHistorico.debitoTipo.descricao}</font></a>&nbsp;</td>
											<td width="12%" align="right"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.guiaPagamento.valorDebito}</font>&nbsp;</td>
											<td width="12%" align="right"><font color="#ff0000" >${devolucaoHistorico.valorDevolucao}</font>&nbsp;</td>
											<td width="10%" align="center"><font color="#ff0000" ><bean:write name="devolucaoHistorico"
												property="dataDevolucao" formatKey="date.format" /></font>&nbsp;</td>
											<td width="16%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAnterior.descricaoAbreviado}</font>&nbsp;</td>
											<td width="16%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAtual.descricaoAbreviado}</font>&nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>
								
									<%} else {%>
										<tr>
											<td height="190" colspan="8">
												<div style="width: 100%; height: 100%; overflow: auto;">
													<table width="100%">
														<!-- Devolu��es -->
														<logic:present name="colecaoDevolucaoAvisoBancarioGuiaPagamento"
															scope="request">
															
															<logic:iterate
																name="colecaoDevolucaoAvisoBancarioGuiaPagamento"
																id="devolucao" type="Devolucao">
																<%contad = contad + 1;
																	if (contad % 2 == 0) {%>
																<tr bgcolor="#FFFFFF">
																	<%} else {
						
																%>
																<tr bgcolor="#cbe5fe">
																	<%}%>
																	<td width="10%" align="center">${devolucao.imovel.id}&nbsp;</td>
																	<td width="10%" align="center">${devolucao.cliente.id}&nbsp;</td>
																	<td width="14%" align="center"><a
																		href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + devolucao.getGuiaDevolucao().getGuiaPagamento().getId() %>')">${devolucao.debitoTipo.descricao}</a>&nbsp;</td>
																	<td width="12%" align="right">${devolucao.guiaDevolucao.guiaPagamento.valorDebito}&nbsp;</td>
																	<td width="12%" align="right">${devolucao.valorDevolucao}&nbsp;</td>
																	<td width="11.4%" align="center"><bean:write name="devolucao"
																		property="dataDevolucao" formatKey="date.format" />&nbsp;</td>
																	<td width="17.1%">${devolucao.devolucaoSituacaoAnterior.descricaoAbreviado}&nbsp;</td>
																	<td width="13.5%">${devolucao.devolucaoSituacaoAtual.descricaoAbreviado}&nbsp;</td>
																</tr>
															</logic:iterate>
														</logic:present>
														
														<!-- Devolu��es Hist�rico -->
														<logic:present name="colecaoDevolucaoHistoricoAvisoBancarioGuiaPagamento"
															scope="request">
															
															<logic:iterate
																name="colecaoDevolucaoHistoricoAvisoBancarioGuiaPagamento"
																id="devolucaoHistorico" type="DevolucaoHistorico">
																<%contad = contad + 1;
																	if (contad % 2 == 0) {%>
																<tr bgcolor="#FFFFFF">
																	<%} else {
						
																%>
																<tr bgcolor="#cbe5fe">
																	<%}%>
																	<td width="10%" align="center"><font color="#ff0000" >${devolucaoHistorico.imovel.id}</font>&nbsp;</td>
																	<td width="10%" align="center"><font color="#ff0000" >${devolucaoHistorico.cliente.id}</font>&nbsp;</td>
																	<td width="14%" align="center">
																	<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + devolucaoHistorico.getGuiaDevolucao().getGuiaPagamento().getId() %>')"><font color="#ff0000" >${devolucaoHistorico.debitoTipo.descricao}</font></a>	
																		&nbsp;</td>
																	<td width="12%" align="right"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.guiaPagamento.valorDebito}</font>&nbsp;</td>
																	<td width="12%" align="right"><font color="#ff0000" >${devolucaoHistorico.valorDevolucao}</font>&nbsp;</td>
																	<td width="11.4%" align="center"><font color="#ff0000" ><bean:write name="devolucaoHistorico"
																		property="dataDevolucao" formatKey="date.format" /></font>&nbsp;</td>
																	<td width="17.1%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAnterior.descricaoAbreviado}</font>&nbsp;</td>
																	<td width="13.5%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAtual.descricaoAbreviado}</font>&nbsp;</td>
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
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0" class="fontePequena">
						<tr bgcolor="#79bbfd" height="20">
							<td align="center">
								<strong>Devolu��es dos D�bitos a Cobrar</strong>
							</td>
						</tr>
						<%int contador = 1;%>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">
									<td width="11%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Im�vel</strong></td>
									<td width="18%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Tipo
									do D�bito</strong></td>
									<td width="14%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor
									a Ser Cobrado</strong></td>
									<td width="14%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor
									da Devol.</strong></td>
									<td width="11%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Data
									da Devol.</strong></td>
									<td width="32%" bgcolor="#90c7fc" align="center" colspan="2"><strong>Situa��o</strong></td>
								</tr>
								<tr>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong></td>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong></td>
								</tr>
								
								<%if ((Integer) session.getAttribute("qtdeDevDebitoACobrar")
									<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_DEVOLUCAO) {%>
				
								<!-- Devolu��es -->
								<logic:present name="colecaoDevolucaoAvisoBancarioDebitoACobrar"
									scope="request">
									
									<logic:iterate
										name="colecaoDevolucaoAvisoBancarioDebitoACobrar"
										id="devolucao" type="Devolucao">
										<%contador = contador + 1;
											if (contador % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

										%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="11%" align="center">${devolucao.imovel.id}&nbsp;</td>
											<td width="18%" align="center"><a
												href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="devolucao" property="guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="devolucao" property="guiaDevolucao.debitoACobrarGeral.id" />', 560,660);">${devolucao.guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}&nbsp;</a></td>
											<td width="14%" align="right">${devolucao.guiaDevolucao.debitoACobrarGeral.debitoACobrar.valorTotalComBonus}&nbsp;</td>
											<td width="14%" align="right">${devolucao.valorDevolucao}&nbsp;</td>
											<td width="11%" align="center"><bean:write name="devolucao"
												property="dataDevolucao" formatKey="date.format" />&nbsp;</td>
											<td width="16%">${devolucao.devolucaoSituacaoAnterior.descricaoAbreviado}&nbsp;</td>
											<td width="16%">${devolucao.devolucaoSituacaoAtual.descricaoAbreviado}&nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>
								
								<!-- Devolu��es Hist�rico -->
								<logic:present name="colecaoDevolucaoHistoricoAvisoBancarioDebitoACobrar"
									scope="request">
									
									<logic:iterate
										name="colecaoDevolucaoHistoricoAvisoBancarioDebitoACobrar"
										id="devolucaoHistorico" type="DevolucaoHistorico">
										<%contador = contador + 1;
											if (contador % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

										%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="11%" align="center"><font color="#ff0000" >${devolucaoHistorico.imovel.id}</font>&nbsp;</td>
											<td width="18%" align="center"><a
												href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="devolucaoHistorico" property="guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="devolucaoHistorico" property="guiaDevolucao.debitoACobrarGeral.debitoACobrar.id" />', 560,660);"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}</font>&nbsp;</a></td>
											<td width="14%" align="right"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.debitoACobrarGeral.debitoACobrar.valorTotalComBonus}</font>&nbsp;</td>
											<td width="14%" align="right"><font color="#ff0000" >${devolucaoHistorico.valorDevolucao}</font>&nbsp;</td>
											<td width="11%" align="center"><font color="#ff0000" ><bean:write name="devolucaoHistorico"
												property="dataDevolucao" formatKey="date.format" /></font>&nbsp;</td>
											<td width="16%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAnterior.descricaoAbreviado}</font>&nbsp;</td>
											<td width="16%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAtual.descricaoAbreviado}</font>&nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>
								
								<%} else {%>
									<tr>
										<td height="190" colspan="7">
											<div style="width: 100%; height: 100%; overflow: auto;">
												<table width="100%">

													<!-- Devolu��es -->
													<logic:present name="colecaoDevolucaoAvisoBancarioDebitoACobrar"
														scope="request">
														
														<logic:iterate
															name="colecaoDevolucaoAvisoBancarioDebitoACobrar"
															id="devolucao" type="Devolucao">
															<%contador = contador + 1;
																if (contador % 2 == 0) {%>
															<tr bgcolor="#FFFFFF">
																<%} else {
					
															%>
															<tr bgcolor="#cbe5fe">
																<%}%>
																<td width="11%" align="center">${devolucao.imovel.id}&nbsp;</td>
																<td width="18%" align="center"><a
																	href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="devolucao" property="guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="devolucao" property="guiaDevolucao.debitoACobrarGeral.id" />', 560,660);">${devolucao.guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}&nbsp;</a></td>
																<td width="14%" align="right">${devolucao.guiaDevolucao.debitoACobrarGeral.debitoACobrar.valorTotalComBonus}&nbsp;</td>
																<td width="14%" align="right">${devolucao.valorDevolucao}&nbsp;</td>
																<td width="12.3%" align="center"><bean:write name="devolucao"
																	property="dataDevolucao" formatKey="date.format" />&nbsp;</td>
																<td width="17%">${devolucao.devolucaoSituacaoAnterior.descricaoAbreviado}&nbsp;</td>
																<td width="13.7%">${devolucao.devolucaoSituacaoAtual.descricaoAbreviado}&nbsp;</td>
															</tr>
														</logic:iterate>
													</logic:present>
													
													<!-- Devolu��es Hist�rico -->
													<logic:present name="colecaoDevolucaoHistoricoAvisoBancarioDebitoACobrar"
														scope="request">
														
														<logic:iterate
															name="colecaoDevolucaoHistoricoAvisoBancarioDebitoACobrar"
															id="devolucaoHistorico" type="DevolucaoHistorico">
															<%contador = contador + 1;
																if (contador % 2 == 0) {%>
															<tr bgcolor="#FFFFFF">
																<%} else {
					
															%>
															<tr bgcolor="#cbe5fe">
																<%}%>
																<td width="11%" align="center"><font color="#ff0000" >${devolucaoHistorico.imovel.id}</font>&nbsp;</td>
																<td width="18%" align="center"><a
																	href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="devolucaoHistorico" property="guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="devolucaoHistorico" property="guiaDevolucao.debitoACobrarGeral.id" />', 560,660);"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}</font>&nbsp;</a></td>
																<td width="14%" align="right"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.debitoACobrarGeral.debitoACobrar.valorTotalComBonus}</font>&nbsp;</td>
																<td width="14%" align="right"><font color="#ff0000" >${devolucaoHistorico.valorDevolucao}</font>&nbsp;</td>
																<td width="12.3%" align="center"><font color="#ff0000" ><bean:write name="devolucaoHistorico"
																	property="dataDevolucao" formatKey="date.format" /></font>&nbsp;</td>
																<td width="17%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAnterior.descricaoAbreviado}</font>&nbsp;</td>
																<td width="13.7%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAtual.descricaoAbreviado}</font>&nbsp;</td>
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
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0" class="fontePequena">
						<tr bgcolor="#79bbfd" height="20">
							<td align="center">
								<strong>Devolu��es de Valores</strong>
							</td>
						</tr>
						<%int contado = 1;%>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">
									<td width="12%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Im�vel</strong></td>
									<td width="13%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Cliente</strong></td>
									<td width="15%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Tipo
									do D�bito</strong></td>
									<td width="10%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor
									da Guia de Devol.</strong></td>
									<td width="10%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor
									da Devol.</strong></td>
									<td width="10%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Data
									da Devol.</strong></td>
									<td width="30%" bgcolor="#90c7fc" align="center" colspan="2"><strong>Situa��o</strong></td>
								</tr>
								<tr>
									<td width="15%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong></td>
									<td width="15%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong></td>
								</tr>
								
								<%if ((Integer) session.getAttribute("qtdeDevDevolucaoValores")
									<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_DEVOLUCAO) {%>
													
								<!-- Devolu��es -->
								<logic:present
									name="colecaoDevolucaoAvisoBancarioDevolucaoValor"
									scope="request">
									
									<logic:iterate
										name="colecaoDevolucaoAvisoBancarioDevolucaoValor"
										id="devolucao" type="Devolucao">
										<%contado = contado + 1;
											if (contado % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">

											<%}%>
											<td width="12%" align="center">${devolucao.imovel.id}&nbsp;</td>
											<td width="13%" align="center">${devolucao.cliente.id}&nbsp;</td>
											<td width="15%">${devolucao.debitoTipo.descricao}&nbsp;</td>
											<td width="10%" align="right">${devolucao.guiaDevolucao.valorDevolucao}&nbsp;</td>
											<td width="10%" align="right">${devolucao.valorDevolucao}&nbsp;</td>
											<td width="10%" align="center"><bean:write name="devolucao"
												property="dataDevolucao" formatKey="date.format" />&nbsp;</td>
											<td width="15%">${devolucao.devolucaoSituacaoAnterior.descricaoAbreviado}&nbsp;</td>
											<td width="15%">${devolucao.devolucaoSituacaoAtual.descricaoAbreviado}&nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>
								
								<!-- Devolu��es Hist�rico -->
								<logic:present
									name="colecaoDevolucaoHistoricoAvisoBancarioDevolucaoValor"
									scope="request">
									
									<logic:iterate
										name="colecaoDevolucaoHistoricoAvisoBancarioDevolucaoValor"
										id="devolucaoHistorico" type="DevolucaoHistorico">
										<%contado = contado + 1;
											if (contado % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">

											<%}%>
											<td width="12%" align="center"><font color="#ff0000" >${devolucaoHistorico.imovel.id}</font>&nbsp;</td>
											<td width="13%" align="center"><font color="#ff0000" >${devolucaoHistorico.cliente.id}</font>&nbsp;</td>
											<td width="15%"><font color="#ff0000" >${devolucaoHistorico.debitoTipo.descricao}</font>&nbsp;</td>
											<td width="10%" align="right"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.valorDevolucao}</font>&nbsp;</td>
											<td width="10%" align="right"><font color="#ff0000" >${devolucaoHistorico.valorDevolucao}</font>&nbsp;</td>
											<td width="10%" align="center"><font color="#ff0000" ><bean:write name="devolucaoHistorico"
												property="dataDevolucao" formatKey="date.format" /></font>&nbsp;</td>
											<td width="15%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAnterior.descricaoAbreviado}</font>&nbsp;</td>
											<td width="15%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAtual.descricaoAbreviado}</font>&nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>
								
								<%} else {%>
									<tr>
										<td height="190" colspan="8">
																		
											<div style="width: 100%; height: 100%; overflow: auto;">
												<table width="100%">
													<!-- Devolu��es -->
													<logic:present
														name="colecaoDevolucaoAvisoBancarioDevolucaoValor"
														scope="request">
														
														<logic:iterate
															name="colecaoDevolucaoAvisoBancarioDevolucaoValor"
															id="devolucao" type="Devolucao">
															<%contado = contado + 1;
																if (contado % 2 == 0) {%>
															<tr bgcolor="#FFFFFF">
																<%} else {
					
																%>
															<tr bgcolor="#cbe5fe">
					
																<%}%>
																<td width="12%" align="center">${devolucao.imovel.id}&nbsp;</td>
																<td width="13%" align="center">${devolucao.cliente.id}&nbsp;</td>
																<td width="15%">${devolucao.debitoTipo.descricao}&nbsp;</td>
																<td width="10%" align="right">${devolucao.guiaDevolucao.valorDevolucao}&nbsp;</td>
																<td width="10%" align="right">${devolucao.valorDevolucao}&nbsp;</td>
																<td width="11.5%" align="center"><bean:write name="devolucao"
																	property="dataDevolucao" formatKey="date.format" />&nbsp;</td>
																<td width="16%">${devolucao.devolucaoSituacaoAnterior.descricaoAbreviado}&nbsp;</td>
																<td width="12.5%">${devolucao.devolucaoSituacaoAtual.descricaoAbreviado}&nbsp;</td>
															</tr>
														</logic:iterate>
													</logic:present>
													
													<!-- Devolu��es Hist�rico -->
													<logic:present
														name="colecaoDevolucaoHistoricoAvisoBancarioDevolucaoValor"
														scope="request">
														
														<logic:iterate
															name="colecaoDevolucaoHistoricoAvisoBancarioDevolucaoValor"
															id="devolucaoHistorico" type="DevolucaoHistorico">
															<%contado = contado + 1;
																if (contado % 2 == 0) {%>
															<tr bgcolor="#FFFFFF">
																<%} else {
					
																%>
															<tr bgcolor="#cbe5fe">
					
																<%}%>
																<td width="12%" align="center"><font color="#ff0000" >${devolucaoHistorico.imovel.id}</font>&nbsp;</td>
																<td width="13%" align="center"><font color="#ff0000" >${devolucaoHistorico.cliente.id}</font>&nbsp;</td>
																<td width="15%"><font color="#ff0000" >${devolucaoHistorico.debitoTipo.descricao}</font>&nbsp;</td>
																<td width="10%" align="right"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.valorDevolucao}</font>&nbsp;</td>
																<td width="10%" align="right"><font color="#ff0000" >${devolucaoHistorico.valorDevolucao}</font>&nbsp;</td>
																<td width="11.5%" align="center"><font color="#ff0000" ><bean:write name="devolucaoHistorico"
																	property="dataDevolucao" formatKey="date.format" /></font>&nbsp;</td>
																<td width="16%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAnterior.descricaoAbreviado}</font>&nbsp;</td>
																<td width="12.5%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAtual.descricaoAbreviado}</font>&nbsp;</td>
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
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="2"><font color="#FF0000"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					<input type="button" name="ButtonReset" class="bottonRightCol"
						value="Voltar Filtro"
						onClick="javascript:window.location.href='/gsan/exibirFiltrarDevolucaoAction.do'">
					</font></td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>

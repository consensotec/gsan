<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.micromedicao.TelemetriaLog"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
</head>
<body leftmargin="5" topmargin="5">
	<html:form action="/exibirConsultarDadosRejeitadosTelemetriaAction" method="post">

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
					</div></td>

				<td valign="top" class="centercoltext">
					<br>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
							</td>
							<td class="parabg">Consultar Dados Rejeitados da Telemetria
							</td>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>

					<table width="590" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td colspan="4" height="23"><font color="#000000"
								style="font-size: 10px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Dados 
								Encontrado(s):</strong> </font></td>
							<td align="right"></td>
						</tr>
					</table>
					
					<table width="590" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td bgcolor="#000000" height="2"></td>
						</tr>
						<tr>
							<td>
								<table width="590" bgcolor="#99CCFF">
									<tr bgcolor="#99CCFF">
										<td align="center" width="15%"><FONT COLOR="#000000"><strong>Data
													do Envio </strong>
										</FONT></td>
										<td align="left" width="70%"><FONT COLOR="#000000"><strong>Início
													dos Dados Enviados</strong>
										</FONT></td>
										<td align="center" width="15%"><FONT COLOR="#000000"><strong>Rejeição</strong>
										</FONT></td>
									</tr>
								</table></td>
						</tr>

						<tr>
							<td>
								<table width="590" bgcolor="#99CCFF">
									<%String cor = "#cbe5fe";%>
									<%--Esquema de paginação--%>
									<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
									export="currentPageNumber=pageNumber;pageOffset"
									maxPageItems="10" items="${sessionScope.totalRegistros}">
										
										<pg:param name="pg" />
										<pg:param name="q" />

										<logic:iterate name="colTelemetriaLog" id="telemetriaLog"
											type="TelemetriaLog">
											<pg:item>
												<%if (cor.equalsIgnoreCase("#cbe5fe")) {
													cor = "#FFFFFF";
												%>
												<tr bgcolor="#FFFFFF" height="18">
													<%} else {
														cor = "#cbe5fe";
													%>
												
												<tr bgcolor="#cbe5fe" height="18">
													<%
														}
													%>
													
													<td width="15%" align="center">
														<html:link page="/exibirConsultarDadosRejeitadosDetalhadoTelemetriaAction.do"
															paramName="telemetriaLog" paramProperty="id"
															paramId="idRegistroConsultar">
															<bean:write name="telemetriaLog" property="recepcao" formatKey="date.format" />
														</html:link>
													</td>

													<td width="70%" align="left"><bean:write name="telemetriaLog" property="conteudoStringEnviadaTruncada"/> </td>

													<td width="15%" align="center">
														<logic:equal name="telemetriaLog" property="indicadorRejeicaoParcial" value="2">TOTAL</logic:equal>
														<logic:equal name="telemetriaLog" property="indicadorRejeicaoParcial" value="1">PARCIAL</logic:equal>
													</td>
												</tr>
											</pg:item>
										</logic:iterate>
								</table></td>
						</tr>
					</table>


					<table width="100%" border="0">

						<tr>
							<td>
							<div align="center"><strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong></div>	
							</td>

				
							<%-- Fim do esquema de paginação --%>
						</tr>
					</pg:pager>
					</table>					
					<table>					
						<tr>						
							<td width="500"> <input
									name="button" type="button" class="bottonRightCol" tabindex="2"
									value="Voltar Filtro"
									onclick="javascript:window.location.href='<html:rewrite page="/exibirFiltrarDadosRejeitadosTelemetriaAction.do"/>'">
									<p>&nbsp;</p>
							</td>
						</tr>					
					</table>
				</td>
			</tr>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%>

	</html:form>
</body>
</html:html>
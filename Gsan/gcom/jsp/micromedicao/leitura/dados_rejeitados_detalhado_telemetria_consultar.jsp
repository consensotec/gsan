<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.micromedicao.TelemetriaLog"%>
<%@ page import="gcom.micromedicao.TelemetriaLogErro"%>

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

</head>

<body leftmargin="5" topmargin="5">
	<html:form
		action="/exibirConsultarDadosRejeitadosDetalhadoTelemetriaAction"
		method="post">

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

				<td valign="top" class="centercoltext"><br>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
							</td>
							<td class="parabg">Dados Rejeitados da Telemetria</td>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<p align="left">&nbsp;</p>

						<tr>
							<td><strong>Data do Envio: </strong></td>
							<td><html:text property="dataEnvio" size="10" maxlength="12" readonly="true"	
							style="background-color:#EFEFEF; border:0;"  />
							</td>
						</tr>
						<tr>
							<td><strong>In&iacute;cio dos Dados Enviados: </strong></td>
							<td><html:text property="inicioDadosEnviados" size="55" maxlength="50" readonly="true"	
							style="background-color:#EFEFEF; border:0;"  />
							</td>
						</tr>

						<tr>
							<td><strong>Rejeição: </strong></td>
							<td><html:text property="motivoRejeicao" size="20" maxlength="20" readonly="true"	
							style="background-color:#EFEFEF; border:0;"  /><br>
							</td>
						</tr>
					</table>

					<table table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
																
						<tr>
							<td>
							<br>
								<table width="100%" bgcolor="#99CCFF">									
									<tr bgcolor="#99CCFF">
										<td align="center" width="30%"><FONT COLOR="#000000"><strong>Conteúdo
											</strong> </FONT></td>
										<td align="center" width="70%"><FONT COLOR="#000000"><strong>Motivo
													Rejeição</strong> </FONT></td>
									</tr>
								</table></td>
						</tr>
					
						<tr>
							<td>	
							<div style="width: 100%; height:100px; overflow-y: auto;">							
								<table width="100%" bgcolor="#99CCFF">
									<%String cor = "#cbe5fe";%>
									<%--Esquema de paginação--%>
									<logic:iterate name="colecaoDadosRejeitadosDetalhado"
											id="dadosRejeitados" type="TelemetriaLogErro">
											
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


													<td width="30%" align="center"><bean:write
															name="dadosRejeitados" property="conteudoDadoErrado" />
													</td>

													<td width="70%" align="center"><bean:write
															name="dadosRejeitados"
															property="telemetriaRetMot.descricaoRetorno" />
													</td>

												</tr>
											
										</logic:iterate>
								</table>	
								</div>							
							</td>
						</tr>
					</table>

					

					<table>
						<tr>
							<td width="500"><font color="#FF0000"> <input
									name="button" type="button" class="bottonRightCol" tabindex="2"
									value="Voltar" onclick="javascript:window.history.back();">

									<input type="button" name="ButtonCancelar"
									class="bottonRightCol" value="Cancelar"
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
									
									<p>&nbsp;</p>
							</td>

						</tr>
					</table></td>
			</tr>
		</table>

		<%@ include file="/jsp/util/rodape.jsp"%>

	</html:form>
</body>
</html:html>
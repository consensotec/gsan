<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="gcom.util.ConstantesSistema,java.util.Collection"%>
<%@ page import="gcom.arrecadacao.GuiaDevolucao"%>

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

</head>



<body leftmargin="0" topmargin="0" onload="javascript:resizePageSemLink(770, 640);">

<html:form action="/pesquisarGuiaDevolucaoAction"
	name="PesquisarGuiaDevolucaoActionForm"
	type="gcom.gui.arrecadacao.PesquisarGuiaDevolucaoActionForm"
	method="post">

	<table width="740" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="740" valign="top" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table align="center" border="0" cellpadding="0" cellspacing="0"
				width="100%">
				<tr>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Pesquisa de Guias de Devolu��o do Cliente</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" bgcolor="#90c7fc">
					<tr>
						<td colspan="7" align="center"><strong>Dados do Cliente</strong></td>
					</tr>
					<tr bgcolor="#cbe5fe">
						<td width="100%" align="center" colspan="7">
							<table width="100%" border="0">
								<tr>
									<td height="10" width="150"><strong>C�digo do Cliente:</strong></td>
									<td><html:text name="PesquisarGuiaDevolucaoActionForm"
										property="codigoCliente" size="25" readonly="true"
										style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								<tr>
									<td height="10"><strong>Nome do Cliente:</strong></td>
									<td><html:text name="PesquisarGuiaDevolucaoActionForm"
										property="nomeCliente" size="45" readonly="true"
										style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								<tr>
									<td height="10"><strong>CPF/CNPJ:</strong></td>
									<td><html:text name="PesquisarGuiaDevolucaoActionForm"
										property="cpfCnpj" size="25" readonly="true"
										style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								<tr>
									<td height="10"><strong>Profiss�o:</strong></td>
									<td><html:text name="PesquisarGuiaDevolucaoActionForm"
										property="profissao" size="25" readonly="true"
										style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								<tr>
									<td height="10"><strong>Ramo da Atividade:</strong></td>
									<td><html:text name="PesquisarGuiaDevolucaoActionForm"
										property="ramoAtividade" size="25" readonly="true"
										style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								<tr>
									<td height="10">
										<strong>Telefone para Contato:</strong>
									</td>
									<td>
										<html:text name="PesquisarGuiaDevolucaoActionForm" property="clienteFone" size="15" readonly="true"
										style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="7" align="center">
							<strong>Endere&ccedil;o de Correspond&ecirc;ncia</strong>
						</td>
					</tr>
					<logic:notEmpty name="PesquisarGuiaDevolucaoActionForm" property="enderecoCliente"> 
						<tr>
							<td height="22" colspan="7" bgcolor="#FFFFFF">
								<div align="center">
									<bean:write	name="PesquisarGuiaDevolucaoActionForm"	property="enderecoCliente" />
								</div>
							</td>
						</tr>
					</logic:notEmpty>
				</table>
				<br>
				<table width="100%" border="0" bgcolor="#90c7fc">
					<tr>
						<td bgcolor="#79bbfd" colspan="7" align="center" height="20"><strong>Guias de Devolu��o do Cliente</strong></td>
					</tr>
					<tr align="left" bgcolor="#90c7fc">
						<td rowspan="2" width="20%">
						<div align="center"><strong>Tipo de Cr�dito</strong></div>
						</td>
						<td rowspan="2" width="17%">
						<div align="center"><strong>Tipo do Documento</strong></div>
						</td>
						<td rowspan="2" width="11%">
						<div align="center">
						<strong>Data de Emiss�o</strong>
						</div>
						</td>
						<td rowspan="2" width="11%">
						<div align="center"><strong>Data de Vencimento</strong></div>
						</td>
						<td rowspan="2" width="17%">
						<div align="center"><strong>Valor da Guia de Devolu��o</strong></div>
						</td>
						<td colspan="2" width="24%">
						<div align="center"><strong>Situa��o</strong></div>
						</td>
					</tr>
					<tr align="left" bgcolor="#90c7fc">
						<td width="12%" bgcolor="#cbe5fe">
						<div align="center"><strong>Anterior</strong></div>
						</td>
						<td width="12%" bgcolor="#cbe5fe">
						<div align="center"><strong>Atual</strong></div>
						</td>
					</tr>
					<%String cor = "#cbe5fe";%>
					<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
				       export="currentPageNumber=pageNumber;pageOffset" maxPageItems="10" items="${sessionScope.totalRegistros}">
						<pg:param name="pg" />
						<pg:param name="q" />
						<logic:present name="colecaoGuiaDevolucao" scope="session">
							<logic:iterate name="colecaoGuiaDevolucao" id="guiaDevolucao">
								<pg:item>
									<%if (cor.equalsIgnoreCase("#cbe5fe")) {
					cor = "#FFFFFF";%>
									<tr bgcolor="#FFFFFF">
									<%} else {
					cor = "#cbe5fe";%>
									<tr bgcolor="#cbe5fe">
										<%}%>
 										<td>
											<div align="left">
												<logic:notEmpty name="guiaDevolucao" property="creditoTipo">
													<a href="javascript:enviarDados('<bean:write name="guiaDevolucao" property="id"/>', '<bean:write name="guiaDevolucao" property="creditoTipo.descricao"/>', 'guiaDevolucao');">
														<bean:write name="guiaDevolucao" property="creditoTipo.descricao" />
												  	</a>
												</logic:notEmpty>
											</div>
										</td>
										<td>
											<logic:notEmpty name="guiaDevolucao" property="documentoTipo">
												<div align="left">
													<bean:write name="guiaDevolucao" property="documentoTipo.descricaoDocumentoTipo" />
												</div>
											</logic:notEmpty>
										</td>
										<td>
											<div align="center">
												<bean:write name="guiaDevolucao" property="dataEmissao" format="dd/MM/yyyy" />
											</div>
										</td>
										<td>
											<div align="center">
												<bean:write name="guiaDevolucao" property="dataValidade" format="dd/MM/yyyy" />
											</div>
										</td>
										<td>
											<div align="right">
												<bean:write name="guiaDevolucao" property="valorDevolucao" formatKey="money.format" />
											</div>
										</td>
										<td>
											<logic:notEmpty name="guiaDevolucao" property="debitoCreditoSituacaoAnterior">
												<div align="left">
													<bean:write name="guiaDevolucao" property="debitoCreditoSituacaoAnterior.descricaoDebitoCreditoSituacao" />
												</div>
											</logic:notEmpty>
										</td>
										<td>
											<logic:notEmpty name="guiaDevolucao" property="debitoCreditoSituacaoAtual">
												<div align="left">
													<bean:write name="guiaDevolucao" property="debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao" />
												</div>
											</logic:notEmpty>
										</td>
									</tr>
 								</pg:item>
							</logic:iterate>
						</logic:present>
				</table>
				<table width="100%" border="0">
				<tr>
					<td>
						<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
						</td>
					</tr>
					<tr>
						<td height="24"><input type="button" class="bottonRightCol"
							value="Voltar Pesquisa"
							onclick="window.location.href='<html:rewrite page="/exibirPesquisarGuiaDevolucaoAction.do?voltarPesquisa=1"/>'" /></td>
					</tr>
				</table>
			 </pg:pager><%-- Fim do esquema de pagina��o --%>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>

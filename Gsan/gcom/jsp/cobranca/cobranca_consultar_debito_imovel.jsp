<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper"%>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper"
	isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gcom.faturamento.conta.Conta"%>
<%@ page import="gcom.faturamento.debito.DebitoACobrar"%>
<%@ page import="gcom.cadastro.imovel.ImovelCobrancaSituacao"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ConsultarDebitoImovelActionForm" dynamicJavascript="true" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!--
function voltar(){
    var form = document.forms[0];
  	form.action = 'exibirConsultarDebitoAction.do';
    form.submit();		
}
 
//-->

function gerarExtratoDebito() {
	var form = document.forms[0];
	form.action = 'gerarRelatorioExtratoDebitoAction.do?consultarDebito=1';
	form.submit();
}
</script>
</head>

<logic:present name="ehPopup">
	<body leftmargin="5" topmargin="5"
		onload="resizePageSemLink(630, 500);">
</logic:present>

<logic:notPresent name="ehPopup">
	<body leftmargin="5" topmargin="5">
</logic:notPresent>

<html:form action="/exibirConsultarDebitoImovelAction"
	name="ConsultarDebitoImovelActionForm" method="post"
	type="gcom.gui.cobranca.ConsultarDebitoImovelActionForm">

	<logic:notPresent name="ehPopup">

		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>

		<table width="770" border="0" cellspacing="5" cellpadding="0">
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

				<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
				<p>&nbsp;</p>
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Consultar D&eacute;bitos do Imóvel</td>
						<td width="11" valign="top"><img border="0"
							src="imagens/parahead_right.gif" /></td>
					</tr>
				</table>
				</logic:notPresent> <logic:present name="ehPopup">
					<table width="600" border="0" cellspacing="5" cellpadding="0">
						<tr>
							<td width="600" valign="top" class="centercoltext">

							<table height="100%">
								<tr>
									<td></td>
								</tr>
							</table>

							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
									<td class="parabg">Consultar D&eacute;bitos do Imóvel</td>
									<td width="11" valign="top"><img border="0"
										src="imagens/parahead_right.gif" /></td>
								</tr>
							</table>

							</logic:present> <!--Fim Tabela Reference a Páginação da Tela de Processo-->
							<p>&nbsp;</p>
							<table width="100%" border="0">
								<tr>
									<td colspan="4">
									<table width="100%" align="center" bgcolor="#99CCFF" border="0">
										<tr>
											<td align="center"><strong>Dados do Imóvel</strong></td>
										</tr>
										<tr bgcolor="#cbe5fe">
											<td width="100%" align="center">
											<table width="100%" border="0">
												<tr>
													<td align="left" height="10" width="200">
													<div class="style9"><strong>Matrícula do Imóvel:</strong></div>
													</td>
													<td align="left">
													<div class="style9"><html:text
														name="ConsultarDebitoImovelActionForm"
														property="codigoImovel" size="9" readonly="true"
														style="background-color:#EFEFEF; border:0; font-size:9px" />
													</div>
													</td>
												</tr>
												<tr>
													<td align="left" height="10" width="200">
													<div class="style9"><strong>Per&iacute;odo de Referência do
													D&eacute;bito:</strong></div>
													</td>
													<td align="left"><html:text
														name="ConsultarDebitoImovelActionForm"
														property="referenciaInicial" size="7" readonly="true"
														style="background-color:#EFEFEF; border:0; font-size:9px" />
													<font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong> a</strong>
													</font> <html:text name="ConsultarDebitoImovelActionForm"
														property="referenciaFinal" size="7" readonly="true"
														style="background-color:#EFEFEF; border:0; font-size:9px" />
													</td>
												</tr>
												<tr>
													<td align="left" height="10" width="200">
													<div class="style9"><strong>Per&iacute;odo de Vencimento do
													D&eacute;bito:</strong></div>
													</td>
													<td align="left"><html:text
														name="ConsultarDebitoImovelActionForm"
														property="dataVencimentoInicial" size="10" readonly="true"
														style="background-color:#EFEFEF; border:0; font-size:9px" />
													<font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong> a</strong>
													</font> <html:text name="ConsultarDebitoImovelActionForm"
														property="dataVencimentoFinal" size="10" readonly="true"
														style="background-color:#EFEFEF; border:0; font-size:9px" /></td>
												</tr>
												<tr>
													<td height="10" width="200">
													<div class="style9"><strong>Inscrição:</strong></div>
													</td>
													<td><html:text name="ConsultarDebitoImovelActionForm"
														property="inscricao" size="20" readonly="true"
														style="background-color:#EFEFEF; border:0; font-size:9px" /></td>
												</tr>
												<tr>
													<td height="10">
													<div class="style9"><strong>Situação de Água:</strong></div>
													</td>
													<td><html:text name="ConsultarDebitoImovelActionForm"
														property="ligacaoAgua" size="45" readonly="true"
														style="background-color:#EFEFEF; border:0; font-size:9px" />
													</td>
												</tr>
												<tr>
													<td height="10">
													<div class="style9"><strong>Situação de Esgoto:</strong></div>
													</td>
													<td><html:text name="ConsultarDebitoImovelActionForm"
														property="ligacaoEsgoto" size="45" readonly="true"
														style="background-color:#EFEFEF; border:0; font-size:9px" />
													</td>
												</tr>
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
									<table width="100%" align="center" bgcolor="#99CCFF" border="0">
										<tr>
											<td align="center">
											<div class="style9"><strong>Endere&ccedil;o </strong></div>
											</td>
										</tr>
										<tr bgcolor="#ffffff">
											<td align="center">
											<div class="style9">${requestScope.enderecoFormatado} &nbsp;
											</div>
											</td>
										</tr>
									</table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
									<table width="100%" border="0">
										<tr>
											<td colspan="4">
											<table width="100%" align="center" bgcolor="#90c7fc"
												border="0">
												<tr bordercolor="#79bbfd">
													<td colspan="4" align="center" bgcolor="#79bbfd"><strong>Clientes</strong></td>
												</tr>
												<tr bordercolor="#000000">
													<td width="28%" bgcolor="#90c7fc" align="center">
													<div class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong>Nome
													do Cliente</strong> </font></div>
													</td>
													<td width="17%" bgcolor="#90c7fc" align="center">
													<div class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo
													da Rela&ccedil;&atilde;o</strong> </font></div>
													</td>
													<td width="27%" bgcolor="#90c7fc" align="center">
													<div class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong>CPF</strong>
													</font></div>
													</td>
													<td width="28%" bgcolor="#90c7fc" align="center">
													<div class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong>CNPJ</strong>
													</font></div>
													</td>
												</tr>
												<%String cor = "#cbe5fe";%>
												<logic:present name="colecaoClienteImovel">
													<logic:iterate name="colecaoClienteImovel"
														id="clienteImovel">
														<%if (cor.equalsIgnoreCase("#cbe5fe")) {
								cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">
															<%} else {
								cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">
															<%}%>
															<td bordercolor="#90c7fc" align="left">
															<div class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																name="clienteImovel" property="cliente">
																<bean:write name="clienteImovel" property="cliente.nome" />
															</logic:notEmpty> </font></div>
															</td>
															<td align="left">
															<div class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																name="clienteImovel" property="clienteRelacaoTipo">
																<bean:write name="clienteImovel"
																	property="clienteRelacaoTipo.descricao" />
															</logic:notEmpty> </font></div>
															</td>
															<td align="right">
															<div class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																name="clienteImovel" property="cliente.cpf">
																<bean:write name="clienteImovel"
																	property="cliente.cpfFormatado" />
															</logic:notEmpty> </font></div>
															</td>
															<td align="right">
															<div class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																name="clienteImovel" property="cliente.cnpj">
																<bean:write name="clienteImovel"
																	property="cliente.cnpjFormatado" />
															</logic:notEmpty> </font></div>
															</td>
														</tr>
													</logic:iterate>
												</logic:present>
											</table>
											</td>
										</tr>
										<tr>
											<td colspan="4">
											<table width="100%" align="center" bgcolor="#90c7fc"
												border="0">
												<%cor = "#cbe5fe";%>
												<tr bordercolor="#79bbfd">
													<td colspan="9" align="center" bgcolor="#79bbfd"><strong>Contas</strong></td>
												</tr>
												<logic:notEmpty name="colecaoContaValores" scope="request">
													<%if (((Collection) request
								.getAttribute("colecaoContaValores")).size() <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
													<tr bordercolor="#000000">
														<td width="20%" bgcolor="#90c7fc" align="center">
														<div class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano</strong>
														</font></div>
														</td>
														<td width="7%" bgcolor="#90c7fc" align="center">
														<div class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>Vencimento</strong>
														</font></div>
														</td>
														<td width="10%" bgcolor="#90c7fc" align="center">
														<div class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
														de &Aacute;gua </strong> </font></div>
														</td>
														<td width="9%" bgcolor="#90c7fc" align="center">
														<div class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
														de Esgoto</strong> </font></div>
														</td>
														<td width="8%" bgcolor="#90c7fc" align="center">
														<div class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
														dos D&eacute;bitos</strong> </font></div>
														</td>
														<td width="8%" bgcolor="#90c7fc" align="center">
														<div class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
														dos Creditos</strong> </font></div>
														</td>
														
														
														
														
														<td width="8%" bgcolor="#90c7fc" align="center">
														   <div class="style9">
														     <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														       <strong>Valor dos Impostos</strong> 
														     </font>
														   </div>
														</td>
														
														
														
														
														
														<td width="10%" bgcolor="#90c7fc" align="center">
														<div class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
														da Conta</strong> </font></div>
														</td>
														<td width="10%" bgcolor="#90c7fc" align="center">
														<div class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>Acr&eacute;sc.
														Impont.</strong><strong></strong> </font></div>
														</td>
														<td width="10%" bgcolor="#90c7fc" align="center">
														<div class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>Sit.</strong>
														</font></div>
														</td>
													</tr>
													<logic:present name="colecaoContaValores">
														<logic:iterate name="colecaoContaValores"
															id="contavaloreshelper">
															<%if (cor.equalsIgnoreCase("#cbe5fe")) {
										cor = "#FFFFFF";%>
															<tr bgcolor="#FFFFFF">
																<%} else {
										cor = "#cbe5fe";%>
															<tr bgcolor="#cbe5fe">
																<%}%>
																<td align="left"><logic:notEmpty
																	name="contavaloreshelper" property="conta">
																	<logic:notPresent name="ehPopup">
																		<div align="center"><font color="#000000"
																			style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <a
																			href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																		<bean:define name="contavaloreshelper"
																			property="conta" id="conta" /> <logic:equal
																			name="conta" property="contaMotivoRevisao" value="">
																			<bean:write name="conta"
																				property="formatarAnoMesParaMesAno" />
																		</logic:equal> <logic:notEqual name="conta"
																			property="contaMotivoRevisao" value="">
																			<font color="#CC0000"> <bean:write name="conta"
																				property="formatarAnoMesParaMesAno" /> </font>
																		</logic:notEqual></a> </font></div>
																	</logic:notPresent>
																	<logic:present name="ehPopup">
																		<div align="center"><font color="#000000"
																			style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"><a
																			href="javascript:abrirPopup('exibirConsultarContaAction.do?caminhoRetornoTelaConsultaConta=exibirConsultarContaAction&contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																			<bean:define
																			name="contavaloreshelper" property="conta" id="conta" />
																		<logic:equal name="conta"
																			property="contaMotivoRevisao" value="">
																			<bean:write name="conta"
																				property="formatarAnoMesParaMesAno" />
																		</logic:equal> <logic:notEqual name="conta"
																			property="contaMotivoRevisao" value="">
																			<font color="#CC0000"> <bean:write name="conta"
																				property="formatarAnoMesParaMesAno" /> </font>
																		</logic:notEqual>
																		</a>
																		</font>
																		</div>
																	</logic:present>
																</logic:notEmpty></td>
																<td align="left"><logic:notEmpty
																	name="contavaloreshelper" property="conta">
																	<div align="center"><font color="#000000"
																		style="font-size:9px"
																		face="Verdana, Arial, Helvetica, sans-serif"> <bean:define
																		name="contavaloreshelper" property="conta" id="conta" />
																	<logic:equal name="conta" property="contaMotivoRevisao"
																		value="">
																		<bean:write name="conta"
																			property="dataVencimentoConta"
																			formatKey="date.format" />
																	</logic:equal> <logic:notEqual name="conta"
																		property="contaMotivoRevisao" value="">
																		<font color="#CC0000"> <bean:write name="conta"
																			property="dataVencimentoConta"
																			formatKey="date.format" /> </font>
																	</logic:notEqual> </font></div>
																</logic:notEmpty></td>
																<td align="right"><logic:notEmpty
																	name="contavaloreshelper" property="conta">
																	<div align="right"><font color="#000000"
																		style="font-size:9px"
																		face="Verdana, Arial, Helvetica, sans-serif"> <bean:define
																		name="contavaloreshelper" property="conta" id="conta" />
																	<logic:equal name="conta" property="contaMotivoRevisao"
																		value="">
																		<bean:write name="conta" property="valorAgua"
																			formatKey="money.format" />
																	</logic:equal> <logic:notEqual name="conta"
																		property="contaMotivoRevisao" value="">
																		<font color="#CC0000"> <bean:write name="conta"
																			property="valorAgua" formatKey="money.format" /> </font>
																	</logic:notEqual> </font></div>
																</logic:notEmpty></td>
																<td align="rigth">
																<div align="right"><font color="#000000"
																	style="font-size:9px"
																	face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																	name="contavaloreshelper" property="conta">
																	<bean:define name="contavaloreshelper" property="conta"
																		id="conta" />
																	<logic:equal name="conta" property="contaMotivoRevisao"
																		value="">
																		<bean:write name="conta" property="valorEsgoto"
																			formatKey="money.format" />
																	</logic:equal>
																	<logic:notEqual name="conta"
																		property="contaMotivoRevisao" value="">
																		<font color="#CC0000"> <bean:write name="conta"
																			property="valorEsgoto" formatKey="money.format" /> </font>
																	</logic:notEqual>
																</logic:notEmpty> </font></div>
																</td>
																<td align="right"><logic:notEmpty
																	name="contavaloreshelper" property="conta">
																	<div align="right"><font color="#000000"
																		style="font-size:9px"
																		face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEqual
																		name="contavaloreshelper" property="conta.debitos"
																		value="0">
																		<logic:notPresent name="ehPopup">
																			<a
																				href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																		</logic:notPresent>
																		<logic:present name="ehPopup">
																			<a
																				href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?caminhoRetornoTelaConsultaDebitos=exibirConsultarContaAction&contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																		</logic:present>
																		<logic:equal name="conta"
																			property="contaMotivoRevisao" value="">
																			<bean:write name="contavaloreshelper"
																				property="conta.debitos" formatKey="money.format" />
																		</logic:equal>
																		<logic:notEqual name="conta"
																			property="contaMotivoRevisao" value="">
																			<font color="#CC0000"> <bean:write
																				name="contavaloreshelper" property="conta.debitos"
																				formatKey="money.format" /> </font>
																		</logic:notEqual>
																		
																	</logic:notEqual> <logic:equal
																		name="contavaloreshelper" property="conta.debitos"
																		value="0">
																		<logic:equal name="conta"
																			property="contaMotivoRevisao" value="">
																			<bean:write name="contavaloreshelper"
																				property="conta.debitos" formatKey="money.format" />
																		</logic:equal>
																		<logic:notEqual name="conta"
																			property="contaMotivoRevisao" value="">
																			<font color="#CC0000"> <bean:write
																				name="contavaloreshelper" property="conta.debitos"
																				formatKey="money.format" /> </font>
																		</logic:notEqual>
																	</logic:equal> </font></div>
																</logic:notEmpty></td>
																<td align="right"><logic:notEmpty
																	name="contavaloreshelper" property="conta">
																	<div align="right"><font color="#000000"
																		style="font-size:9px"
																		face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEqual
																		name="contavaloreshelper"
																		property="conta.valorCreditos" value="0">
																		<a
																			href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?contaID=<bean:define name="contavaloreshelper"	property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																		<logic:equal name="conta"
																			property="contaMotivoRevisao" value="">
																			<bean:write name="contavaloreshelper"
																				property="conta.valorCreditos"
																				formatKey="money.format" />
																		</logic:equal> <logic:notEqual name="conta"
																			property="contaMotivoRevisao" value="">
																			<font color="#CC0000"> <bean:write
																				name="contavaloreshelper"
																				property="conta.valorCreditos"
																				formatKey="money.format" /> </font>
																		</logic:notEqual> </a>
																	</logic:notEqual> <logic:equal
																		name="contavaloreshelper"
																		property="conta.valorCreditos" value="0">
																		<logic:equal name="conta"
																			property="contaMotivoRevisao" value="">
																			<bean:write name="contavaloreshelper"
																				property="conta.valorCreditos"
																				formatKey="money.format" />
																		</logic:equal>
																		<logic:notEqual name="conta"
																			property="contaMotivoRevisao" value="">
																			<font color="#CC0000"> <bean:write
																				name="contavaloreshelper"
																				property="conta.valorCreditos"
																				formatKey="money.format" /> </font>
																		</logic:notEqual>
																	</logic:equal> </font></div>
																</logic:notEmpty></td>
																
																
																
																
																
																
																<td align="right">
																  <logic:notEmpty name="contavaloreshelper" property="conta">
																	<div align="right">
																	  <font color="#000000"	style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																	    <bean:define name="contavaloreshelper" property="conta" id="conta" />
																	    <logic:equal name="conta" property="contaMotivoRevisao" value="">
																		  <bean:write name="conta" property="valorImposto" formatKey="money.format" />
																	    </logic:equal> 
																	    <logic:notEqual name="conta" property="contaMotivoRevisao" value="">
																		  <font color="#CC0000"> 
																		    <bean:write name="conta"property="valorImposto" formatKey="money.format" /> 
																		  </font>
																	    </logic:notEqual> 
																	  </font>
																	</div>
																  </logic:notEmpty>
																</td>
																
																
																
																
																
																
																
																
																
																
																
																
																<td align="right"><logic:notEmpty
																	name="contavaloreshelper" property="conta">
																	<div align="right"><font color="#000000"
																		style="font-size:9px"
																		face="Verdana, Arial, Helvetica, sans-serif"> <bean:define
																		name="contavaloreshelper" property="conta" id="conta" />
																	<logic:equal name="conta" property="contaMotivoRevisao"
																		value="">
																		<bean:write name="conta" property="valorTotal"
																			formatKey="money.format" />
																	</logic:equal> <logic:notEqual name="conta"
																		property="contaMotivoRevisao" value="">
																		<font color="#CC0000"> <bean:write name="conta"
																			property="valorTotal" formatKey="money.format" /> </font>
																	</logic:notEqual> </font></div>
																</logic:notEmpty></td>
																<td align="right"><logic:notEmpty
																	name="contavaloreshelper" property="conta">
																	<div align="right" class="style9"><font color="#000000"
																		style="font-size:9px"
																		face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEqual
																		name="contavaloreshelper"
																		property="valorTotalContaValores" value="0">
																		<a
																			href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
																		<logic:equal name="conta"
																			property="contaMotivoRevisao" value="">
																			<bean:write name="contavaloreshelper"
																				property="valorTotalContaValores"
																				formatKey="money.format" />
																		</logic:equal> <logic:notEqual name="conta"
																			property="contaMotivoRevisao" value="">
																			<font color="#CC0000"> <bean:write
																				name="contavaloreshelper"
																				property="valorTotalContaValores"
																				formatKey="money.format" /> </font>
																		</logic:notEqual> </a>
																	</logic:notEqual> <logic:equal
																		name="contavaloreshelper"
																		property="valorTotalContaValores" value="0">
																		<logic:equal name="conta"
																			property="contaMotivoRevisao" value="">
																			<bean:write name="contavaloreshelper"
																				property="valorTotalContaValores"
																				formatKey="money.format" />
																		</logic:equal>
																		<logic:notEqual name="conta"
																			property="contaMotivoRevisao" value="">
																			<font color="#CC0000"> <bean:write
																				name="contavaloreshelper"
																				property="valorTotalContaValores"
																				formatKey="money.format" /> </font>
																		</logic:notEqual>
																	</logic:equal> </font></div>
																</logic:notEmpty></td>
																<td align="left"><logic:notEmpty
																	name="contavaloreshelper" property="conta">
																	<div align="left"><font color="#000000"
																		style="font-size:9px"
																		face="Verdana, Arial, Helvetica, sans-serif"> <bean:define
																		name="contavaloreshelper" property="conta" id="conta" />
																	<bean:define name="conta"
																		property="debitoCreditoSituacaoAtual"
																		id="debitoCreditoSituacaoAtual" /> <logic:equal
																		name="conta" property="contaMotivoRevisao" value="">
																		<bean:write name="debitoCreditoSituacaoAtual"
																			property="descricaoAbreviada" />
																	</logic:equal> <logic:notEqual name="conta"
																		property="contaMotivoRevisao" value="">
																		<font color="#CC0000"> <bean:write
																			name="debitoCreditoSituacaoAtual"
																			property="descricaoAbreviada" /> </font>
																	</logic:notEqual> </font></div>
																</logic:notEmpty></td>
															</tr>
														</logic:iterate>
														<logic:notEmpty name="colecaoContaValores">
															<%if (cor.equalsIgnoreCase("#cbe5fe")) {
										cor = "#FFFFFF";%>
															<tr bgcolor="#FFFFFF">
																<%} else {
										cor = "#cbe5fe";%>
															<tr bgcolor="#cbe5fe">
																<%}%>
																<td bgcolor="#90c7fc" align="center">
																<div class="style9" align="center"><font color="#000000"
																	style="font-size:9px"
																	face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
																</font></div>
																</td>
																<td align="left">&nbsp;</td>
																<td align="right">
																<div align="right"><font color="#000000"
																	style="font-size:9px"
																	face="Verdana, Arial, Helvetica, sans-serif"> <%=request
															.getAttribute("valorAgua")%> </font></div>
																</td>
																<td align="rigth">
																<div align="right"><font color="#000000"
																	style="font-size:9px"
																	face="Verdana, Arial, Helvetica, sans-serif"> <%=request
															.getAttribute("valorEsgoto")%> </font></div>
																</td>
																<td align="right">
																<div align="right"><font color="#000000"
																	style="font-size:9px"
																	face="Verdana, Arial, Helvetica, sans-serif"> <%=request
															.getAttribute("valorDebito")%> </font></div>
																</td>
																<td align="right">
																<div align="right"><font color="#000000"
																	style="font-size:9px"
																	face="Verdana, Arial, Helvetica, sans-serif"> <%=request
															.getAttribute("valorCredito")%> </font></div>
																</td>
																
																
																
																<td align="right">
																  <div align="right">
																    <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																       <%=request.getAttribute("valorImposto")%> 
																    </font>
																  </div>
																</td>
																
																
																
																
																<td align="right">
																<div align="right"><font color="#000000"
																	style="font-size:9px"
																	face="Verdana, Arial, Helvetica, sans-serif"> <%=request
															.getAttribute("valorConta")%> </font></div>
																</td>
																<td align="right">
																<div align="right" class="style9"><font color="#000000"
																	style="font-size:9px"
																	face="Verdana, Arial, Helvetica, sans-serif"> <%=request
															.getAttribute("valorAcrescimo")%> </font></div>
																</td>
																<td align="left">
																<div align="left"><font color="#000000"
																	style="font-size:9px"
																	face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
																</td>
															</tr>
														</logic:notEmpty>
													</logic:present>
													<%} else {%>
													<tr bordercolor="#000000">
														<td width="9%" bgcolor="#90c7fc">
														<div align="center" class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano</strong>
														</font></div>
														</td>
														<td width="12%" bgcolor="#90c7fc">
														<div align="center" class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>Vencimento</strong>
														</font></div>
														</td>
														<td width="10%" bgcolor="#90c7fc">
														<div align="center" class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
														de &Aacute;gua </strong> </font></div>
														</td>
														<td width="10%" bgcolor="#90c7fc">
														<div align="center" class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
														de Esgoto</strong> </font></div>
														</td>
														<td width="10%" bgcolor="#90c7fc">
														<div align="center" class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
														dos <br>
														D&eacute;bitos</strong> </font></div>
														</td>
														<td width="10%" bgcolor="#90c7fc">
														<div align="center" class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
														dos Creditos</strong> </font></div>
														</td>
														
														
														
														<td width="10%" bgcolor="#90c7fc">
														  <div align="center" class="style9">
														    <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
														      <strong>Valor dos Impostos</strong> 
														    </font>
														  </div>
														</td>
														
														
														
														
														
														<td width="10%" bgcolor="#90c7fc">
														<div align="center" class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
														da Conta</strong> </font></div>
														</td>
														<td width="8%" bgcolor="#90c7fc">
														<div align="center" class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>Acr&eacute;sc.
														Impont.</strong><strong></strong> </font></div>
														</td>
														<td width="8%" bgcolor="#90c7fc">
														<div align="center" class="style9"><font color="#000000"
															style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <strong>Sit.</strong>
														</font></div>
														</td>
													</tr>
													<tr>
														<td height="100" colspan="10">
														<div style="width: 100%; height: 100%; overflow: auto;">
														<table width="100%">
															<logic:present name="colecaoContaValores">
																<logic:iterate name="colecaoContaValores"
																	id="contavaloreshelper">
																	<%if (cor.equalsIgnoreCase("#cbe5fe")) {
										cor = "#FFFFFF";%>
																	<tr bgcolor="#FFFFFF">
																		<%} else {
										cor = "#cbe5fe";%>
																	<tr bgcolor="#cbe5fe">
																		<%}%>
																		<td width="8%" align="left">
																		<div align="left" class="style9"><font color="#000000"
																			style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																			name="contavaloreshelper" property="conta">
																			<logic:notPresent name="ehPopup">
																			<a
																				href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																			</logic:notPresent>
																			<logic:present name="ehPopup">
																			<a href="javascript:abrirPopup('exibirConsultarContaAction.do?caminhoRetornoTelaConsultaConta=exibirConsultarContaAction&contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																			</logic:present>
																			<bean:define name="contavaloreshelper"
																				property="conta" id="conta" /> <logic:equal
																				name="conta" property="contaMotivoRevisao" value="">
																				<bean:write name="conta"
																					property="formatarAnoMesParaMesAno" />
																			</logic:equal> <logic:notEqual name="conta"
																				property="contaMotivoRevisao" value="">
																				<font color="#CC0000"> <bean:write name="conta"
																					property="formatarAnoMesParaMesAno" /> </font>
																			</logic:notEqual>
																		</logic:notEmpty> </font></div>
																		</td>
																		<td width="12%" align="left">
																		<div align="left" class="style9"><font color="#000000"
																			style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																			name="contavaloreshelper" property="conta">
																			<bean:define name="contavaloreshelper"
																				property="conta" id="conta" />
																			<logic:equal name="conta"
																				property="contaMotivoRevisao" value="">
																				<bean:write name="conta"
																					property="dataVencimentoConta"
																					formatKey="date.format" />
																			</logic:equal>
																			<logic:notEqual name="conta"
																				property="contaMotivoRevisao" value="">
																				<font color="#CC0000"> <bean:write name="conta"
																					property="dataVencimentoConta"
																					formatKey="date.format" /> </font>
																			</logic:notEqual>
																		</logic:notEmpty> </font></div>
																		</td>
																		<td width="9%" align="right">
																		<div align="right" class="style9"><font
																			color="#000000" style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																			name="contavaloreshelper" property="conta">
																			<bean:define name="contavaloreshelper"
																				property="conta" id="conta" />
																			<logic:equal name="conta"
																				property="contaMotivoRevisao" value="">
																				<bean:write name="conta" property="valorAgua"
																					formatKey="money.format" />
																			</logic:equal>
																			<logic:notEqual name="conta"
																				property="contaMotivoRevisao" value="">
																				<font color="#CC0000"> <bean:write name="conta"
																					property="valorAgua" formatKey="money.format" /> </font>
																			</logic:notEqual>
																		</logic:notEmpty> </font></div>
																		</td>
																		<td width="10%" align="right">
																		<div align="right" class="style9"><font
																			color="#000000" style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																			name="contavaloreshelper" property="conta">
																			<bean:define name="contavaloreshelper"
																				property="conta" id="conta" />
																			<logic:equal name="conta"
																				property="contaMotivoRevisao" value="">
																				<bean:write name="conta" property="valorEsgoto"
																					formatKey="money.format" />
																			</logic:equal>
																			<logic:notEqual name="conta"
																				property="contaMotivoRevisao" value="">
																				<font color="#CC0000"> <bean:write name="conta"
																					property="valorEsgoto" formatKey="money.format" />
																				</font>
																			</logic:notEqual>
																		</logic:notEmpty> </font></div>
																		</td>
																		<td width="10%" align="right">
																		<div align="right" class="style9"><font
																			color="#000000" style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																			name="contavaloreshelper" property="conta">
																			<logic:notEqual name="contavaloreshelper"
																				property="conta.debitos" value="0">
																				<logic:notPresent name="ehPopup">
																				<a
																					href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																				</logic:notPresent>
																				<logic:present name="ehPopup">
																				<a href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?caminhoRetornoTelaConsultaDebitos=exibirConsultarContaAction&contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																				</logic:present>
																				<logic:equal name="conta"
																					property="contaMotivoRevisao" value="">
																					<bean:write name="contavaloreshelper"
																						property="conta.debitos" formatKey="money.format" />
																				</logic:equal> <logic:notEqual name="conta"
																					property="contaMotivoRevisao" value="">
																					<font color="#CC0000"> <bean:write
																						name="contavaloreshelper" property="conta.debitos"
																						formatKey="money.format" /> </font>
																				</logic:notEqual>
																			</logic:notEqual>
																			<logic:equal name="contavaloreshelper"
																				property="conta.debitos" value="0">
																				<logic:equal name="conta"
																					property="contaMotivoRevisao" value="">
																					<bean:write name="contavaloreshelper"
																						property="conta.debitos" formatKey="money.format" />
																				</logic:equal>
																				<logic:notEqual name="conta"
																					property="contaMotivoRevisao" value="">
																					<font color="#CC0000"> <bean:write
																						name="contavaloreshelper" property="conta.debitos"
																						formatKey="money.format" /> </font>
																				</logic:notEqual>
																			</logic:equal>
																		</logic:notEmpty> </font></div>
																		</td>
																		<td width="10%" align="right">
																		<div align="right" class="style9"><font
																			color="#000000" style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																			name="contavaloreshelper" property="conta">
																			<logic:notEqual name="contavaloreshelper"
																				property="conta.valorCreditos" value="0">
																				<logic:notPresent name="ehPopup">
																				<a
																					href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?contaID=<bean:define name="contavaloreshelper"	property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																				</logic:notPresent>
																				<logic:present name="ehPopup">
																				<a href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?caminhoRetornoTelaConsultaCreditoRealizado=exibirConsultarContaAction&contaID=<bean:define name="contavaloreshelper"	property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																				</logic:present>
																				<logic:equal name="conta"
																					property="contaMotivoRevisao" value="">
																					<bean:write name="contavaloreshelper"
																						property="conta.valorCreditos"
																						formatKey="money.format" />
																				</logic:equal> <logic:notEqual name="conta"
																					property="contaMotivoRevisao" value="">
																					<font color="#CC0000"> <bean:write
																						name="contavaloreshelper"
																						property="conta.valorCreditos"
																						formatKey="money.format" /> </font>
																				</logic:notEqual> 
																			</logic:notEqual>
																			<logic:equal name="contavaloreshelper"
																				property="conta.valorCreditos" value="0">
																				<logic:equal name="conta"
																					property="contaMotivoRevisao" value="">
																					<bean:write name="contavaloreshelper"
																						property="conta.valorCreditos"
																						formatKey="money.format" />
																				</logic:equal>
																				<logic:notEqual name="conta"
																					property="contaMotivoRevisao" value="">
																					<font color="#CC0000"> <bean:write
																						name="contavaloreshelper"
																						property="conta.valorCreditos"
																						formatKey="money.format" /> </font>
																				</logic:notEqual>
																			</logic:equal>
																		</logic:notEmpty> </font></div>
																		</td>
																		
																		
																		
																		<td width="10%" align="right">
																		  <div align="right" class="style9">
																		    <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																		      <logic:notEmpty name="contavaloreshelper" property="conta">
																		        <bean:define name="contavaloreshelper" property="conta" id="conta" />
																		        <logic:equal name="conta" property="contaMotivoRevisao" value="">
																		          <bean:write name="conta" property="valorImposto" formatKey="money.format" />
																		        </logic:equal>
																		        <logic:notEqual name="conta" property="contaMotivoRevisao" value="">
																		          <font color="#CC0000"> 
																		            <bean:write name="conta" property="valorImposto" formatKey="money.format" /> 
																		          </font>
																		        </logic:notEqual>
																		      </logic:notEmpty> 
																		    </font>
																		  </div>
																		</td>
																		
																		
																		
																		
																		
																		
																		
																		<td width="10%" align="right">
																		<div align="right" class="style9"><font
																			color="#000000" style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																			name="contavaloreshelper" property="conta">
																			<bean:define name="contavaloreshelper"
																				property="conta" id="conta" />
																			<logic:equal name="conta"
																				property="contaMotivoRevisao" value="">
																				<bean:write name="conta" property="valorTotal"
																					formatKey="money.format" />
																			</logic:equal>
																			<logic:notEqual name="conta"
																				property="contaMotivoRevisao" value="">
																				<font color="#CC0000"> <bean:write name="conta"
																					property="valorTotal" formatKey="money.format" />
																				</font>
																			</logic:notEqual>
																		</logic:notEmpty> </font></div>
																		</td>
																		<td width="9%" align="right">
																		<div align="right" class="style9"><font
																			color="#000000" style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEqual
																			name="contavaloreshelper"
																			property="valorTotalContaValores" value="0">
																			<logic:notPresent name="ehPopup">
																			<a
																				href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
																			</logic:notPresent>
																			<logic:present name="ehPopup">
																			<a href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?caminhoRetornoTelaConsultaAcrescimos=exibirConsultarContaAction&multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
																			</logic:present>
																			<logic:equal name="conta"
																				property="contaMotivoRevisao" value="">
																				<bean:write name="contavaloreshelper"
																					property="valorTotalContaValores"
																					formatKey="money.format" />
																			</logic:equal> <logic:notEqual name="conta"
																				property="contaMotivoRevisao" value="">
																				<font color="#CC0000"> <bean:write
																					name="contavaloreshelper"
																					property="valorTotalContaValores"
																					formatKey="money.format" /> </font>
																			</logic:notEqual> 
																		</logic:notEqual> <logic:equal
																			name="contavaloreshelper"
																			property="valorTotalContaValores" value="0">
																			<logic:equal name="conta"
																				property="contaMotivoRevisao" value="">
																				<bean:write name="contavaloreshelper"
																					property="valorTotalContaValores"
																					formatKey="money.format" />
																			</logic:equal>
																			<logic:notEqual name="conta"
																				property="contaMotivoRevisao" value="">
																				<font color="#CC0000"> <bean:write
																					name="contavaloreshelper"
																					property="valorTotalContaValores"
																					formatKey="money.format" /> </font>
																			</logic:notEqual>
																		</logic:equal> </font></div>
																		</td>
																		<td width="6%" align="left">
																		<div align="left" class="style9"><font color="#000000"
																			style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																			name="contavaloreshelper" property="conta">
																			<bean:define name="contavaloreshelper"
																				property="conta" id="conta" />
																			<bean:define name="conta"
																				property="debitoCreditoSituacaoAtual"
																				id="debitoCreditoSituacaoAtual" />
																			<logic:equal name="conta"
																				property="contaMotivoRevisao" value="">
																				<bean:write name="debitoCreditoSituacaoAtual"
																					property="descricaoAbreviada" />
																			</logic:equal>
																			<logic:notEqual name="conta"
																				property="contaMotivoRevisao" value="">
																				<font color="#CC0000"> <bean:write
																					name="debitoCreditoSituacaoAtual"
																					property="descricaoAbreviada" /> </font>
																			</logic:notEqual>
																		</logic:notEmpty> </font></div>
																		</td>
																	</tr>
																</logic:iterate>
																<logic:notEmpty name="colecaoContaValores">
																	<%if (cor.equalsIgnoreCase("#cbe5fe")) {
										cor = "#FFFFFF";%>
																	<tr bgcolor="#FFFFFF">
																		<%} else {
										cor = "#cbe5fe";%>
																	<tr bgcolor="#cbe5fe">
																		<%}%>
																		<td bgcolor="#90c7fc" align="center">
																		<div class="style9" align="center"><font
																			color="#000000" style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
																		</font></div>
																		</td>
																		<td align="left">&nbsp;</td>
																		<td align="right">
																		<div align="right"><font color="#000000"
																			style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <%=request
															.getAttribute("valorAgua")%> </font></div>
																		</td>
																		<td align="rigth">
																		<div align="right"><font color="#000000"
																			style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <%=request
															.getAttribute("valorEsgoto")%> </font></div>
																		</td>
																		<td align="right">
																		<div align="right"><font color="#000000"
																			style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <%=request
															.getAttribute("valorDebito")%> </font></div>
																		</td>
																		<td align="right">
																		<div align="right"><font color="#000000"
																			style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <%=request
															.getAttribute("valorCredito")%> </font></div>
																		</td>
																		
																		
																		<td align="right">
																		  <div align="right">
																		    <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																		      <%=request.getAttribute("valorImposto")%> 
																		    </font>
																		  </div>
																		</td>
																		
																		
																		
																		<td align="right">
																		<div align="right"><font color="#000000"
																			style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <%=request
															.getAttribute("valorConta")%> </font></div>
																		</td>
																		<td align="right">
																		<div align="right" class="style9"><font
																			color="#000000" style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <%=request
															.getAttribute("valorAcrescimo")%> </font></div>
																		</td>
																		<td align="left">
																		<div align="left"><font color="#000000"
																			style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> </font>
																		</div>
																		</td>
																	</tr>
																</logic:notEmpty>
															</logic:present>
														</table>
														</div>
														</td>
													</tr>
													<%}

					%>
												</logic:notEmpty>
											</table>
											</td>
										</tr>
										
										
										<tr>
											<td colspan="4">
											<table width="100%" align="center" bgcolor="#90c7fc"
												border="0">
												<tr bordercolor="#79bbfd">
													<td colspan="5" bgcolor="#79bbfd" align="center"><strong>D&eacute;bitos
													A Cobrar</strong></td>
												</tr>
												<tr bordercolor="#000000">
													<td width="53%" bgcolor="#90c7fc">
													<div align="center" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo
													do D&eacute;bito</strong> </font></div>
													</td>
													<td width="10%" bgcolor="#90c7fc">
													<div align="center" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
													Refer&ecirc;ncia</strong> </font></div>
													</td>
													<td width="10%" bgcolor="#90c7fc">
													<div align="center" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
													Cobran&ccedil;a</strong> </font></div>
													</td>
													<td width="10%" bgcolor="#90c7fc">
													<div align="center" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong>Parcelas
													a cobrar</strong> </font></div>
													</td>
													<td width="17%" bgcolor="#90c7fc" height="20">
													<div align="center" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
													a cobrar</strong> </font></div>
													</td>
												</tr>
												<%cor = "#cbe5fe";%>
												<logic:present name="colecaoDebitoACobrar">
													<logic:iterate name="colecaoDebitoACobrar"
														id="debitoacobrar">
														<%if (cor.equalsIgnoreCase("#cbe5fe")) {
								cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">
															<%} else {
								cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">
															<%}%>
															<td>
															<div align="left" class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																name="debitoacobrar" property="imovel">
																<logic:notPresent name="ehPopup">
																<a
																	href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:define name="debitoacobrar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&debitoID=<bean:write name="debitoacobrar" property="id" />', 570, 720);">
																</logic:notPresent>
																<logic:present name="ehPopup">
																<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?caminhoRetornoTelaConsultaDebitoACobrar=exibirConsultarContaAction&imovelID=<bean:define name="debitoacobrar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&debitoID=<bean:write name="debitoacobrar" property="id" />', 570, 720);">
																</logic:present>
																<bean:define name="debitoacobrar" property="debitoTipo"
																	id="debitoTipo" /> <logic:notEmpty name="debitoTipo"
																	property="descricao">
																	<bean:write name="debitoTipo" property="descricao" />
																</logic:notEmpty> 
															</logic:notEmpty> </font></div>
															</td>
															<td>
															<div align="center" class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> 						                	<logic:notEmpty
										name="debitoacobrar" property="anoMesReferenciaDebito">
										<%= Util.formatarAnoMesParaMesAno(((DebitoACobrar)debitoacobrar).getAnoMesReferenciaDebito().toString()) %>
									</logic:notEmpty>&nbsp; </font></div>
															</td>
															<td>
															<div align="center" class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="debitoacobrar" property="anoMesCobrancaDebito">
										<%= Util.formatarAnoMesParaMesAno(((DebitoACobrar)debitoacobrar).getAnoMesCobrancaDebito().toString()) %>
									</logic:notEmpty> &nbsp; </font></div>
															</td>
															<td>
															<div align="center" class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																name="debitoacobrar" property="parcelasRestanteComBonus">
																<bean:write name="debitoacobrar"
																	property="parcelasRestanteComBonus" />
															</logic:notEmpty> </font></div>
															</td>
															<td>
															<div align="right" class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																name="debitoacobrar" property="valorTotalComBonus">
																<bean:write name="debitoacobrar" property="valorTotalComBonus"
																	formatKey="money.format" />
															</logic:notEmpty> </font></div>
															</td>
														</tr>
													</logic:iterate>
													<logic:notEmpty name="colecaoDebitoACobrar">
														<%if (cor.equalsIgnoreCase("#cbe5fe")) {
								cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">
															<%} else {
								cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">
															<%}%>
															<td bgcolor="#90c7fc">
															<div align="center" class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
															</font></div>
															</td>
															<td>&nbsp;</td>
															<td>&nbsp;</td>
															<td>&nbsp;</td>
															<td>
															<div align="right" class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <%=request
													.getAttribute("valorDebitoACobrar")%> </font></div>
															</td>
														</tr>
													</logic:notEmpty>
												</logic:present>
											</table>
											</td>
										</tr>
										<tr>
											<td colspan="4">
											<table width="100%" align="center" bgcolor="#90c7fc"
												border="0">
												<tr bordercolor="#79bbfd">
													<td colspan="5" bgcolor="#79bbfd" align="center"><strong>Cr&eacute;ditos
													A Realizar</strong></td>
												</tr>
												<tr bordercolor="#000000">
													<td width="53%" bgcolor="#90c7fc" height="20">
													<div align="center" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo
													do Cr&eacute;dito</strong> </font></div>
													</td>
													<td width="10%" bgcolor="#90c7fc">
													<div align="center" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
													Refer&ecirc;ncia</strong> </font></div>
													</td>
													<td width="10%" bgcolor="#90c7fc">
													<div align="center" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
													Cobran&ccedil;a</strong> </font></div>
													</td>
													<td width="10%" bgcolor="#90c7fc">
													<div align="center" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong>Parcelas
													a creditar</strong> </font></div>
													</td>
													<td width="17%" bgcolor="#90c7fc" height="20">
													<div align="center" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
													a creditar</strong> </font></div>
													</td>
												</tr>
												<%cor = "#cbe5fe";%>
												<logic:present name="colecaoCreditoARealizar">
													<logic:iterate name="colecaoCreditoARealizar"
														id="creditoarealizar">
														<%if (cor.equalsIgnoreCase("#cbe5fe")) {
								cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">
															<%} else {
								cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">
															<%}%>
															<td><logic:notEmpty name="creditoarealizar"
																property="creditoTipo">
																<div align="left" class="style9"><font color="#000000"
																	style="font-size:9px"
																	face="Verdana, Arial, Helvetica, sans-serif"> 
																	<logic:notPresent name="ehPopup">
																	<a
																	href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=<bean:define name="creditoarealizar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&creditoID=<bean:write name="creditoarealizar" property="id" />', 570, 720);">
																</logic:notPresent>
																<logic:present name="ehPopup">
																<a href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?caminhoRetornoTelaConsultaCreditoARealizar=exibirConsultarContaAction&imovelID=<bean:define name="creditoarealizar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&creditoID=<bean:write name="creditoarealizar" property="id" />', 570, 720);">
																</logic:present>
																<bean:define name="creditoarealizar"
																	property="creditoTipo" id="creditoTipo" /> <logic:notEmpty
																	name="creditoTipo" property="descricao">
																	<bean:write name="creditoTipo" property="descricao" />
																</logic:notEmpty>  </font></div>
															</logic:notEmpty></td>
															<td>
															<div align="center" class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																name="creditoarealizar"
																property="anoMesReferenciaCredito">
																<bean:write name="creditoarealizar"
																	property="formatarAnoMesReferenciaCredito" />
															</logic:notEmpty> </font></div>
															</td>
															<td>
															<div align="center" class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																name="creditoarealizar" property="anoMesCobrancaCredito">
																<bean:write name="creditoarealizar"
																	property="formatarAnoMesCobrancaCredito" />
															</logic:notEmpty> </font></div>
															</td>
															<td>
															<div align="center" class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																name="creditoarealizar" property="parcelasRestanteComBonus">
																<bean:write name="creditoarealizar"
																	property="parcelasRestanteComBonus" />
															</logic:notEmpty> </font></div>
															</td>
															<td>
															<div align="right" class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
																name="creditoarealizar" property="valorTotalComBonus">
																<bean:write name="creditoarealizar"
																	property="valorTotalComBonus" formatKey="money.format" />
															</logic:notEmpty> </font></div>
															</td>
														</tr>
													</logic:iterate>
													<logic:notEmpty name="colecaoCreditoARealizar">
														<%if (cor.equalsIgnoreCase("#cbe5fe")) {
								cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">
															<%} else {
								cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">
															<%}%>
															<td bgcolor="#90c7fc">
															<div align="center" class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
															</font></div>
															</td>
															<td>&nbsp;</td>
															<td>&nbsp;</td>
															<td>&nbsp;</td>
															<td>
															<div align="right" class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <%=request
													.getAttribute("valorCreditoARealizar")%> </font></div>
															</td>
														</tr>
													</logic:notEmpty>
												</logic:present>
											</table>
											</td>
										</tr>
										<tr>
											<td colspan="4">
											<table width="100%" align="center" bgcolor="#90c7fc"
												border="0">
												<tr bordercolor="#79bbfd">
													<td colspan="4" bgcolor="#79bbfd" align="center"><strong>Guias de Pagamento</strong></td>
												</tr>
												<tr bordercolor="#000000">
													<td width="36%" bgcolor="#90c7fc">
													<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<strong>Tipo do D&eacute;bito</strong> </font></div>
													</td>
													<td width="16%" bgcolor="#90c7fc">
													<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<strong>Data de Emiss&atilde;o</strong> </font></div>
													</td>
													<td width="19%" bgcolor="#90c7fc">
													<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<strong>Data de Vencimento</strong> </font></div>
													</td>
													<td width="29%" bgcolor="#90c7fc">
													<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<strong>Valor da Guia de Pagamento</strong> </font></div>
													</td>
												</tr>
												<%cor = "#cbe5fe";%>
												<logic:present name="colecaoGuiaPagamentoValores">
													<logic:iterate name="colecaoGuiaPagamentoValores"
														id="guiapagamentohelper">
														<%if (cor.equalsIgnoreCase("#cbe5fe")) {
															cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">
															<%} else {
															cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">
															<%}%>
															<td>
																<div align="left" class="style9">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																	<logic:notPresent name="ehPopup">
																		 <a
																		href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<bean:define name="guiapagamentohelper" property="guiaPagamento" id="guiaPagamento" /><bean:write name="guiaPagamento" property="id" />', 600, 800);">
																	</logic:notPresent>
																	<logic:present name="ehPopup">
																		a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?caminhoRetornoTelaConsultaGuiaPagamento=exibirConsultarContaAction&guiaPagamentoId=<bean:define name="guiapagamentohelper" property="guiaPagamento" id="guiaPagamento" /><bean:write name="guiaPagamento" property="id" />', 600, 800);">
																	</logic:present>
																	<bean:define name="guiaPagamento" property="debitoTipo"	id="debitoTipo" /> 
																	<logic:notEmpty name="debitoTipo" property="descricao">
																		<bean:write name="debitoTipo" property="descricao" />
																	</logic:notEmpty>  
																</font>
																</div>
															</td>
															<td>
																<div align="right" class="style9">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																	<logic:notEmpty	name="guiapagamentohelper" property="guiaPagamento">
																		<bean:define name="guiapagamentohelper"	property="guiaPagamento" id="guiaPagamento" />
																		<bean:write name="guiaPagamento" property="dataEmissao" formatKey="date.format" />
																	</logic:notEmpty> 
																</font></div>
															</td>
															<td>
																<div align="right" class="style9">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="guiapagamentohelper" property="guiaPagamento">
																	<bean:define name="guiapagamentohelper" property="guiaPagamento" id="guiaPagamento" />
																	<bean:write name="guiaPagamento" property="dataVencimento" formatKey="date.format" />
																</logic:notEmpty> 
																</font></div>
															</td>
															<td>
																<div align="right" class="style9">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																	<logic:notEmpty	name="guiapagamentohelper" property="guiaPagamento">
																		<bean:define name="guiapagamentohelper"property="guiaPagamento" id="guiaPagamento" />
																		<bean:write name="guiaPagamento" property="valorDebito" formatKey="money.format" />
																	</logic:notEmpty> 
																</font></div>
															</td>
														</tr>
													</logic:iterate>
													<logic:notEmpty name="colecaoGuiaPagamentoValores">
														<%if (cor.equalsIgnoreCase("#cbe5fe")) {
								cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">
															<%} else {
								cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">
															<%}%>
															<td bgcolor="#90c7fc">
															<div align="center" class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
															</font></div>
															</td>
															<td>&nbsp;</td>
															<td>&nbsp;</td>
															<td>
															<div align="right" class="style9"><font color="#000000"
																style="font-size:9px"
																face="Verdana, Arial, Helvetica, sans-serif"> <%=request
													.getAttribute("valorGuiaPagamento")%> </font></div>
															</td>
														</tr>
													</logic:notEmpty>
												</logic:present>
											</table>
											</td>
										</tr>
										<tr>
											<td colspan="4">&nbsp;</td>
										</tr>
										<tr>
											<td colspan="4">
											<table width="100%" align="center" bgcolor="#90c7fc" border="0">
												<tr bordercolor="#000000">
													<td width="25%" bgcolor="#90c7fc">
														<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<strong>Valor Total dos Débitos</strong> </font></div>
													</td>
													<td width="25%" bgcolor="#90c7fc">
														<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<strong>Valor Total dos Débitos Atualizado</strong> </font></div>
													</td>
													<td width="25%" bgcolor="#90c7fc">
														<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<strong>Valor do Desconto para Pagamento à Vista</strong> </font></div>
													</td>
													<td width="25%" bgcolor="#90c7fc">
														<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<strong>Valor do Pagamento à Vista</strong> </font></div>
													</td>
												</tr>
												<tr bgcolor="#FFFFFF">
													<td>
													<div align="right" class="style9"><font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <%=request.getAttribute("valorTotalSemAcrescimo")%>
													</font></div>
													</td>
													<td>
													<div align="right" class="style9"><font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <%=request.getAttribute("valorTotalComAcrescimo")%>
													</font></div>
													</td>
													
													<td>
													<div align="right" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <%=request.getAttribute("valorTotalDescontoPagamentoAVista")!=null?request.getAttribute("valorTotalDescontoPagamentoAVista"):""%>
													</font></div>
													</td>
													
													<td>
													<div align="right" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <%=request.getAttribute("valorPagamentoAVista")!=null?request.getAttribute("valorPagamentoAVista"):""%>
													</font></div>
													</td>
													
													
												</tr>
											</table>
											</td>
										</tr>
										<tr>
											<td height="17" colspan="4">&nbsp;</td>
										</tr>
										
											<tr>
												<td height="23">
													<table width="100%">
														<tr>  
															<logic:empty name="colecaoContaValores">
																<td align="right">
																	<input type="button" name="" value="Imprimir Extrato de Débito" class="bottonRightCol" disabled="true"/>
																</td>
															</logic:empty>
															<logic:notEmpty name="colecaoContaValores">
																<logic:equal name="ConsultarDebitoImovelActionForm" property="indicadorEmissaoExtratoNaConsulta" value="<%=ConstantesSistema.SIM.toString()%>" >
																	<td align="right">
																		<input type="button" name="" value="Imprimir Extrato de Débito" class="bottonRightCol" onclick="gerarExtratoDebito()" >
																	</td>
																</logic:equal>
																
																<logic:notEqual name="ConsultarDebitoImovelActionForm" property="indicadorEmissaoExtratoNaConsulta" value="<%=ConstantesSistema.SIM.toString()%>" >
																	<td align="right">
																		<input type="button" name="" value="Imprimir Extrato de Débito" class="bottonRightCol" disabled="true" >
																	</td>
																</logic:notEqual>
															</logic:notEmpty>
														</tr>
													</table>
												</td>			
											</tr>
											
										<tr>
											<td height="17" colspan="4">&nbsp;</td>
										</tr>
										
										<tr>
											<td>
												<table width="100%" border="0">
												<tr>
													<logic:notPresent name="ehPopup">
														<td align="left"><input name="Button"
															type="button" class="bottonRightCol" value="Voltar"
															onClick="javascript:voltar();">&nbsp; <input
															name="adicionar" class="bottonRightCol" value="Cancelar"
															type="button"
															onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
														</td>
													</logic:notPresent>
													
													<logic:present name="ehPopup">
														<td height="24"><logic:present 
														name="caminhoRetornoTelaConsultaDebito"	scope="request">
															<input type="button" class="bottonRightCol" value="Voltar"
																style="width: 70px;" onclick="javascript:history.back();" />
														</logic:present>
																				
													
														<td align="right" colspan="4"><input name="Button"
															type="button" class="bottonRightCol" value="Fechar"
															onClick="javascript:window.close();"></td>
													</logic:present>
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
					<logic:notPresent name="ehPopup">
						<%@ include file="/jsp/util/rodape.jsp"%>
					</logic:notPresent>

					<p>&nbsp;</p>
					<!-- Fim do Corpo - Fernanda Paiva -->
					</html:form>
					</body>
					</html:html>
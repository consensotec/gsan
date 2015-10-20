<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page
	import="gcom.financeiro.bean.ParametrosPerdasFiscaisHelper"%>
<%@ page
	import="gcom.financeiro.bean.ParametrosPerdasFiscaisItensHelper"%>
<%@page import="gcom.util.Util"%>
<%@page import="gcom.util.ConstantesSistema"%>

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
<html:javascript staticJavascript="false"
	formName="ParametrosPerdasFiscaisActionForm" />

<script language="JavaScript">
	function validarForm() {
		var form = document.forms[0];
		if (validateParametrosPerdasFiscaisActionForm(form)) {
			submeterFormPadrao(form);
		}
	}
	function adicionarParametroEncontrado(form) {
		document.forms[0].target = '';
		if (validateParametrosPerdasFiscaisActionForm(form)) {
			form.action = "exibirInserirParametrosPerdasFiscaisAction.do?adicionarParametroEncontrado=S";
			submeterFormPadrao(form);
		}
	}
</script>
</head>

<body leftmargin="5" topmargin="5">

	<html:form action="inserirParametrosPerdasFiscaisAction"
		name="ParametrosPerdasFiscaisActionForm"
		type="gcom.gui.financeiro.ParametrosPerdasFiscaisActionForm"
		method="post" onsubmit="return validateParametrosPerdasFiscaisActionForm(this);">

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
					<table>
						<tr>
							<td></td>
						</tr>
					</table> <!--Início Tabela Reference a Páginação da Tela de Processo-->

					<table>
						<tr>
							<td></td>
						</tr>
					</table>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0"
								src="imagens/parahead_left.gif" /></td>
							<td class="parabg">Inserir Parâmetros de Perdas Fiscais</td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" /></td>
						</tr>
					</table> <!--Fim Tabela Reference a Páginação da Tela de Processo-->

					<table width="100%" border="0">
						<tr>
							<td colspan="3">Para adicionar parâmetros de perdas fiscais,
								informe os dados abaixo:</td>
						</tr>

						<tr>
							<td>Vigência a partir da referência contábil:<font
								color="#FF0000">*</font></td>

							<td colspan="2"><html:text maxlength="7"
									property="anoMesReferenciaContabil"
									onkeypress="return isCampoNumerico(event);" 
									 size="5"
									tabindex="8" onkeyup="mascaraAnoMes(this, event);" /> <strong>mm/aaaa</strong>
							</td>
						</tr>

						<tr>
							<td height="30" width="30%">Valor máximo das perdas fiscais:
								<font color="#FF0000">*</font>
							</td>
							<td colspan="2"><html:text property="valorABaixar"
									onkeyup="formataValorMonetario(this, 13);"
									onkeypress="return verificaVirgula(event);"
									onblur="formataValorMonetario(this, 13);" 
									maxlength="13"
									size="15" />
						</tr>

						<tr>
							<td valign="baseline" bgcolor="#000000" height="2" colspan="5"></td>
						</tr>

						<tr>
							<td colspan="3"><strong>Limite de valor por número
									de meses vencidos:</strong></td>
						</tr>

						<tr>
							<td height="30" width="30%"><strong>Valor: <font
									color="#FF0000">*</font>
							</strong></td>
							<td colspan="2"><html:text property="valorLimite"
									maxlength="13" size="15"
									onkeyup="formataValorMonetario(this, 13);"
									onkeypress="return verificaVirgula(event);"
									onblur="formataValorMonetario(this, 13);"  /></td>
						</tr>

						<tr>
							<td height="30" width="30%"><strong>Número de
									Meses: <font color="#FF0000">*</font>
							</strong></td>
							<td colspan="2"><html:text property="numeroMeses"
									onkeypress="javascript:return isCampoNumerico(event);"
									onchange="javascript:verificaNumeroInteiro(this);"
									maxlength="4" size="10" /></td>
						</tr>

						<tr>
							<td><strong>Situação de Cobrança:</strong></td>
							<td><html:select property="situacaoCobranca" tabindex="2">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionCobrancaSituacao"
										labelProperty="descricao" property="id" />
								</html:select> <font size="1">&nbsp; </font></td>
						</tr>

						<tr>
							<td><p>&nbsp;</p></td>
							<td align="right">
								<div align="right">
									<strong><font color="#FF0000">*</font></strong> Campos
									obrigat&oacute;rios
								</div>
							</td>
						</tr>

						<tr>
							<td colspan="3" align="right"><input type="button"
								name="Button" class="bottonRightCol" value="Adicionar"
								align="right"
								onClick="adicionarParametroEncontrado(document.forms[0])" /></td>
						</tr>

						<%-- início da tabela de Situação da Fiscalização Selecionada--%>

						<%
							int cont = 0;
						%>

						<tr>
							<td colspan="4">
								<table width="100%" border="0" bgcolor="90c7fc">
									<logic:notPresent name="parametrosPerdasFiscaisHelper">
										<tr bgcolor="#90c7fc">
											<td width="10%" align="center"><strong>Remover</strong></td>
											<td width="30%" align="center"><strong>Valor</strong></td>
											<td width="10%" align="center"><strong>Número
													de Meses</strong></td>
											<td width="50%" align="center"><strong>Situação
													de Cobrança</strong></td>
										</tr>
									</logic:notPresent>

									<logic:present name="parametrosPerdasFiscaisHelper">

										<tr bgcolor="#90c7fc">
											<td width="10%" align="center"><strong>Remover</strong></td>
											<td width="30%" align="center"><strong>Valor</strong></td>
											<td width="10%" align="center"><strong>Número
													de Meses</strong></td>
											<td width="50%" align="center"><strong>Situação
													de Cobrança</strong></td>
										</tr>

										<logic:iterate name="parametrosPerdasFiscaisHelper"
											property="collParametrosPerdasFiscaisItensHelper"
											id="parametrosPerdasFiscaisItensHelper"
											type="ParametrosPerdasFiscaisItensHelper">

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

												<td width="10%">
													<div align="center">
														<font color="#333333"> <img width="14" height="14"
															border="0"
															src="<bean:message key="caminho.imagens"/>Error.gif"
															onclick="javascript:document.forms[0].target='';if(confirm('Confirma remoção?')){redirecionarSubmit('exibirInserirParametrosPerdasFiscaisAction.do?removerSituacaoSelecionada=S&idParametroRemover=<bean:write name="parametrosPerdasFiscaisItensHelper" property="valorLimite"/>');}" />
														</font>
													</div>
												</td>

												<td width="30%" align="center"><logic:present
														name="parametrosPerdasFiscaisItensHelper"
														property="valorLimite">
														<font style="font-size: 9px"
															face="Verdana, Arial, Helvetica, sans-serif"><%=""
										+ Util.formatarMoedaReal(parametrosPerdasFiscaisItensHelper
												.getValorLimite())%></font>
													</logic:present> <logic:notPresent
														name="parametrosPerdasFiscaisItensHelper"
														property="valorLimite">
										&nbsp;
								</logic:notPresent></td>
												<td width="10%" align="center"><bean:write
														name="parametrosPerdasFiscaisItensHelper"
														property="numeroMeses" /></td>

												<td width="50%" align="center"><bean:write
														name="parametrosPerdasFiscaisItensHelper"
														property="descricaoSituacaoCobranca" /></td>

											</tr>
										</logic:iterate>

									</logic:present>
								</table>
							</td>
						</tr>

						<tr>
							<td valign="baseline" bgcolor="#000000" height="2" colspan="5"></td>
						</tr>

						<tr>
							<td colspan="2"><input name="Button" type="button"
								class="bottonRightCol" value="Desfazer" align="left"
								onclick="window.location.href='<html:rewrite page="/exibirInserirParametrosPerdasFiscaisAction.do?menu=sim"/>'">
								<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onclick="window.location.href='/gsan/telaPrincipal.do'">
							</td>

							<td align="right" height="24"><input type="button"
								name="Button" class="bottonRightCol" value="Inserir"
								onClick="validarForm()">
							</td>
						</tr>

					</table>

					<p>&nbsp;</p>
					<p>&nbsp;</p>
					<p>&nbsp;</p>
			</tr>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%>
	</html:form>
</body>
</html:html>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirVigenciaSimulacaoTarifaConsumoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<%@ page import="gcom.faturamento.bean.ConsumoTarifaHelper" %>
<%@ page import="gcom.cadastro.imovel.Categoria" %>

<script language="JavaScript">

	function validarForm(form){
		//botaoAvancarTelaEspera('/gsan/inserirVigenciaSimulacaoTarifaConsumoAction.do');
		form.action = 'inserirVigenciaSimulacaoTarifaConsumoAction.do';
		form.submit();
	}

	function facilitador(objeto){
	
		if (objeto.id == "0" || objeto.id == undefined){
			objeto.id = "1";
			marcarTodos();
		}
		else{
			objeto.id = "0";
			desmarcarTodos();
		}
	}

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
<html:form action="/inserirVigenciaSimulacaoTarifaConsumoAction" method="post"
	name="InserirVigenciaSimulacaoTarifaConsumoActionForm"
	type="gcom.gui.faturamento.consumotarifa.InserirVigenciaSimulacaoTarifaConsumoActionForm"
	onsubmit="return validateInserirVigenciaSimulacaoTarifaConsumoActionForm(this);" >
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="780" border="0" cellspacing="5" cellpadding="0">

		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="615" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Gerar Tarifa de Consumo para Simulação de Reajuste</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Selecione as tarifas de consumo que serão atualizadas</td>
				</tr>
				
				<tr>
					<td>
						<table width="590" bgcolor="#99CCFF" border="0">
							<tr bgcolor="#99CCFF">
								<td width="40" bgcolor="#90c7fc">
								<div align="center"><strong><a
									href="javascript:facilitador(this);">Todos</a></strong></div>
								</td>
								<td width="150" bgcolor="#90c7fc">
								<div align="center"><strong>Descrição da Tarifa de Consumo</strong></div>
								</td>
								<td width="90" bgcolor="#90c7fc">
								<div align="center"><strong>Data de Vigência</strong></div>
								</td>
							</tr>
							<%int cont = 0;%>
							<logic:present name="colecaoConsumoTarifaHelper" scope="session">
								<tr>
									<td width="590" colspan="3">
										<div style="width: 100%; height: 200; overflow: auto;">
											<table width="100%" align="center" bgcolor="#99CCFF" border="0">
												<logic:iterate name="colecaoConsumoTarifaHelper" id="consumoTarifaHelper"
													type="ConsumoTarifaHelper">
													<%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr bgcolor="#cbe5fe">
													<%} else {%>
													<tr bgcolor="#FFFFFF">
													<%}%>
												        <td width="40" align="center">
															<input type="checkbox" name="registros" id="registros"
 																value="<bean:write name="consumoTarifaHelper" property="id"/>" />
														</td>
														<td width="150">
															<div align="left">&nbsp;<bean:write name="consumoTarifaHelper" property="descricao" /></div>
														</td>
														<td width="90">
															<div align="center"><bean:write name="consumoTarifaHelper" property="dataVigencia" /></div>
														</td>
													</tr>
												</logic:iterate>
											</table>
										</div>
									</td>
								</tr>
							</logic:present>
						</table>
					</td>
				</tr>
				
				<tr>
					<td><strong>Percentual de Reajuste por Categoria:</strong></td>
				</tr>
				
				<tr>
					<td>
						<table width="590" bgcolor="#99CCFF" border="0">
							<tr bgcolor="#99CCFF">
								<td width="150" bgcolor="#90c7fc">
									<div align="center"><strong>Categoria</strong></div>
								</td>
								<td width="90" bgcolor="#90c7fc">
									<div align="center"><strong>Percentual de Reajuste</strong></div>
								</td>
							</tr>
							<%int contador = 0;%>
							<logic:present name="colecaoCategoria" >
								<tr>
									<td width="590" colspan="3">
										<div style="width: 100%; height: 120; overflow: auto;">
											<table width="100%" align="center" bgcolor="#99CCFF" border="0">			
												<logic:iterate name="colecaoCategoria" id="categoria"
													type="Categoria">
													<%contador = contador + 1;
													if (contador % 2 == 0) {%>
													<tr bgcolor="#cbe5fe">
													<%} else {%>
													<tr bgcolor="#FFFFFF">
													<%}%>
												        <td width="150" >
															<div align="center"><bean:write name="categoria" property="descricao" /></div>
														</td>
														<td width="90" > 
							                            	<div align="center"><input name="percentual_<%= categoria.getId() %>" 
							                            		style="text-align:right;" onkeypress="javascript:return isCampoNumerico(event);" 
							                            		size="6" maxlength="6" type="text" onKeyup="formataValorMonetario(this, 5);" >
							                            		<strong>%</strong>
							                            	</div> 
							                            </td>
													</tr>
												</logic:iterate>	
											</table>
										</div>
									</td>
								</tr>
							</logic:present>
						</table>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				<tr>
					<td>
						<table width="100%" border="0">
							<tr>
								<td colspan="2">
									<input name="limpar" type="button" class="bottonRightCol"
										value="Limpar" align="left"
										onclick="window.location.href='/gsan/exibirInserirVigenciaSimulacaoTarifaConsumoAction.do?menu=sim'">
									<input name="Button" type="button" class="bottonRightCol"
										value="Cancelar" align="left"
										onclick="window.location.href='/gsan/telaPrincipal.do'">
								</td>
								<td align="right"><input type="button" name="gerar"
									class="bottonRightCol" value="Gerar Simulação"
									onClick="javascript:validarForm(document.forms[0])" /> 
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>


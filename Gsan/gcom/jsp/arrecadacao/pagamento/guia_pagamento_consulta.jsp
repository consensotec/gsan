<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="gcom.cobranca.parcelamento.ParcelamentoItem"
	isELIgnored="false"%>

<%@ page import="gcom.faturamento.debito.DebitoTipo, gcom.arrecadacao.pagamento.GuiaPagamentoItem, gcom.arrecadacao.pagamento.GuiaPagamentoItemHistorico"%>

<html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
	
	<script>
	
	function gerarGuia(){
	
		var idGuiaPagamento = document.getElementById("idGuiaPagamento").value;
		window.location.href = '/gsan/gerarRelatorioEmitirGuiaPagamentoActionParcelamento.do?idGuiaPagamento=' + idGuiaPagamento;
	}

	</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:resizePageSemLink(600,600);">
<table width="570" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="570" valign="top" class="centercoltext">
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img
					src="<bean:message key="caminho.imagens"/>parahead_left.gif"
					border="0" /></td>
				<td class="parabg">Consultar Guia de Pagamento</td>
				<td width="11"><img
					src="<bean:message key="caminho.imagens"/>parahead_right.gif"
					border="0" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td colspan="2">Dados da guia de pagamento:</td>
			</tr>
			
			
			<!-- Inicio Guia Pagamento  -->
			<logic:present name="guiaPagamento" scope="request">
			
				<tr>
					<td width="194"><strong> Localidade:</strong></td>
					<td width="395" align="right">
					<div align="left"><input name="codigoLocalidade" type="text"
						readonly style="background-color:#EFEFEF; border:0" size="3"
						maxlength="3" value="${requestScope.guiaPagamento.localidade.id}">&nbsp;<input
						name="nomeLocalidade" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="30" maxlength="30"
						value="${requestScope.guiaPagamento.localidade.descricao}">
						
						<input name="idGuiaPagamento" type="hidden" id="idGuiaPagamento"
						value="${requestScope.guiaPagamento.id}"/>
						
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
	
					<td class="style1"><span class="style1"> <input
						name="matriculaImovel" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="9" maxlength="9"
						value="${requestScope.guiaPagamento.imovel.id}"> </span> <span
						class="style1"> <input name="inscricaoImovel" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="20" maxlength="20"
						value="${requestScope.guiaPagamento.imovel.inscricaoFormatada}"> </span></td>
	
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
	
							<td align="center"><strong>Endere&ccedil;o </strong></td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td>
							<div align="center">${requestScope.guiaPagamento.imovel.enderecoFormatado}&nbsp;</div>
							</td>
						</tr>
	
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>C&oacute;digo do Cliente:</strong></td>
					<td class="style1"><span class="style1"> <input name="codigoCliente"
						type="text" readonly style="background-color:#EFEFEF; border:0"
						size="9" maxlength="9"
						value="${requestScope.guiaPagamento.cliente.id}"> </span></td>
				</tr>
				<tr>
					<td class="style1"><strong>CPF/CNPJ:</strong></td>
					<c:if
						test="${requestScope.guiaPagamento.cliente.clienteTipo.indicadorPessoaFisicaJuridica eq 1}"
						var="ehPessoaFisica">
						<c:set var="valorCpfCnpj" scope="page"
							value="${requestScope.guiaPagamento.cliente.cpf}" />
					</c:if>
					<c:if test="${!ehPessoaFisica}">
						<c:set var="valorCpfCnpj" scope="page"
							value="${requestScope.guiaPagamento.cliente.cnpj}" />
					</c:if>
	
	
					<td class="style1"><span class="style1"> <input
						name="cpfCnpjCliente" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="17" maxlength="17"
						value="${valorCpfCnpj}"> </span></td>
				</tr>
	
				<tr>
					<td class="style1"><strong>Nome do Cliente:</strong></td>
					<td class="style1"><span class="style1"> <input name="nomeCliente"
						type="text" readonly style="background-color:#EFEFEF; border:0"
						value="${requestScope.guiaPagamento.cliente.nome}" size="50"
						maxlength="45"> </span></td>
				</tr>
				
				<tr>
					<td class="style1"><strong>Profissão:</strong></td>
					<td class="style1"><span class="style1"> <input name="profissaoCliente" type="text" readonly="readonly" 
						style="background-color:#EFEFEF; border:0" value="${requestScope.guiaPagamento.cliente.profissao.descricao}" size="50" maxlength="45"></span>
					</td>
				</tr>
				
				<tr>
					<td class="style1"><strong>Ramo da Atividade:</strong></td>
					<td class="style1"><span class="style1"> <input name="ramoAtividade" type="text" readonly="readonly" 
						style="background-color:#EFEFEF; border:0" value="${requestScope.guiaPagamento.cliente.ramoAtividade}" size="50" maxlength="45"></span>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				
				<tr>
					<td><strong>Prestação:</strong></td>
					<td align="right">
					<div align="left"><input name="tipoDebito" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="10" maxlength="10"
						value="${requestScope.guiaPagamento.prestacaoFormatada}"></div>
					</td>
	
				</tr>
				
				<tr>
					<td><strong>Situa&ccedil;&atilde;o da Guia de Pagamento:</strong></td>
					<td align="right">
					<div align="left"><input name="situacaoGuiaPagamento" type="text"
						readonly style="background-color:#EFEFEF; border:0" size="30"
						maxlength="30"
						value="${requestScope.guiaPagamento.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}"></div>
					</td>
				</tr>
				
				<tr>
						<td width="183" height="25"><strong>Usuário:</strong></td>
						<td colspan="3">
						<logic:present name="guiaPagamento" property="usuario">
							<html:text name="guiaPagamento"
								property="usuario.nomeUsuario"
								size="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present>		
						<logic:notPresent name="guiaPagamento" property="usuario">
							<input type="text" 
									name="usuario" 
									size="30"
									readonly="true" 
									style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
						</td>
					</tr>
				
				<tr>
					<td><strong>Data de Emiss&atilde;o:</strong></td>
					<td align="right">
					<div align="left"><input name="dataEmissao" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="10" maxlength="10"
						value="<bean:write name="guiaPagamento" property="dataEmissao" formatKey="date.format" scope="request"/>"></div>
					<div align="left"><strong></strong></div>
	
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Data de Vencimento:</strong></td>
					<td align="right">
					<div align="left"><input name="dataVencimento" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="10" maxlength="10"
						value="<bean:write name="guiaPagamento" property="dataVencimento" formatKey="date.format" scope="request"/>"></div>
					<div align="left"><strong></strong></div>
	
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Valor da Guia de Pagamento:<font color="#FF0000"> </font></strong></td>
					<td align="right">
					<div align="left"><input name="valorGuiaPagamento" type="text"
						readonly
						style="background-color:#EFEFEF; border:0; text-align:right;"
						size="17" maxlength="17"
						value="<bean:write name="guiaPagamento" property="valorDebito" formatKey="money.format"/>"></div>
					<div align="left"><strong></strong></div>
	
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Registro de Atendimento:</strong></td>
					<td align="right">
					<div align="left"><input name="registroAtendimentoId" type="text"
						readonly style="background-color:#EFEFEF; border:0" size="9"
						maxlength="9"
						value="${requestScope.guiaPagamento.registroAtendimento.id}"> <%-- Por enquanto este campo ficará em branco até que o campo DESCRICAO DO TIPO DE SOLICITACAO entrar na tabela --%>
					<input name="inscricaoImovel22232" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="40" maxlength="40"></div>
	
	
					<div align="left"><strong></strong></div>
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Ordem de Servi&ccedil;o:<strong></strong><font
						color="#FF0000"> </font></strong></td>
					<td align="right">
					<div align="left"><input name="ordemServicoId" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="9" maxlength="9"
						value="${requestScope.guiaPagamento.ordemServico.id}"> <%-- Por enquanto este campo ficará em branco até que o campo DESCRICAO DO SERVIÇO entrar na tabela --%>
					<input name="inscricaoImovel22232" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="40" maxlength="40"></div>
					<div align="left"><strong></strong></div>
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Tipo do Financiamento:</strong></td>
					<td align="right">
					<div align="left"><input name="tipoFinanciamentoDescricao"
						type="text" readonly style="background-color:#EFEFEF; border:0"
						size="40" maxlength="40"
						value="${requestScope.guiaPagamento.financiamentoTipo.descricao}"></div>
					</td>
				</tr>
				
				<tr>
		      		<td><strong>Observação:</strong></td>
			        <td>
			        	<textarea name="observacao" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; text-align:justify; overflow:hidden;" 
								cols="43" rows="6" wrap=virtual>${requestScope.guiaPagamento.observacao}</textarea>
					</td>
			    </tr>
			    <tr>
					<td><strong>Emitir Observação?</strong></td>
					<td><strong> 
					<logic:equal name="guiaPagamento" property="indicadorEmitirObservacao" value="1">
						<input type="text" property="indicadorEmitirObservacao"
							value="SIM" readonly style="background-color:#EFEFEF; border:0" />
					</logic:equal>
					<logic:notEqual name="guiaPagamento" property="indicadorEmitirObservacao" value="1">
						<input type="text" property="indicadorEmitirObservacao"
							value="NÃO" readonly style="background-color:#EFEFEF; border:0" />
					</logic:notEqual>
					</td>
				</tr>
				
				<tr>
					<td><strong>Matrícula do Imóvel Origem:</strong></td>
					<td colspan="2">
										
						<logic:present name="guiaPagamento" property="origem" scope="request">
							<input type="text" name="imovelOrigem" size="12" maxlength="10"
							readonly="true" style="background-color:#EFEFEF; border:0" 
							value="${requestScope.guiaPagamento.origem.guiaPagamento.imovel.id}"/>
						</logic:present>
											
						<logic:notPresent name="guiaPagamento" property="origem" scope="request">
							<input type="text" name="imovelOrigem" size="12" maxlength="10"
							readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>	
										
					</td>
				</tr>
				
				<logic:present name="contaPaga" scope="session">
					<tr>
						<td colspan="2">
						<hr>
						</td>
					</tr>
					
					<tr>
						<td colspan="2">Dados do Pagamento:</td>
					</tr>
					
					<tr>
						<td><strong>Nosso Número:</strong></td>
						<td align="right">
						<div align="left"><input name="nossoNumero" type="text" readonly
							style="background-color:#EFEFEF; border:0" size="18" maxlength="18"
							value="${requestScope.nossoNumero}"></div>
						</td>
					</tr>
					
					<logic:notEmpty name="arrecadador" scope="request">
						<tr>
							<td><strong>NSA do Movimento:</strong></td>
							<td align="right">
							<div align="left"><input name="nsaMovimento" type="text" readonly
								style="background-color:#EFEFEF; border:0" size="10" maxlength="10"
								value="${requestScope.arrecadador.numeroSequencialArquivo}"></div>
							</td>
						</tr>
					</logic:notEmpty>
					
					<logic:notEmpty name="pagamento" scope="request">
						<tr>
							<td><strong>Valor do Movimento:</strong></td>
							<td align="right">
							<div align="left"><input name="valorMovimento" type="text" readonly
								style="background-color:#EFEFEF; border:0" size="12" maxlength="12"
								value="${requestScope.pagamento.arrecadadorMovimentoItem.arrecadadorMovimento.valorTotalMovimento}"></div>
							</td>
						</tr>
					</logic:notEmpty>
					
					<tr>
						<td><strong>Data Pagamento:</strong></td>
						<td align="right">
						<div align="left"><input name="dataPagamento" type="text" readonly
							style="background-color:#EFEFEF; border:0" size="10" maxlength="10"
							value="<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" scope="request"/>"></div>
						</td>
					</tr>
					
					<logic:present name="indicadorAceitacao" scope="request">
						<tr>
							<td><strong>Pagamento Aceito:</strong></td>
							<td align="right">
								<logic:equal name="indicadorAceitacao" value="1" scope="request">
									<div align="left"><input name="pagamentoAceito" type="text" readonly
										style="background-color:#EFEFEF; border:0" size="3" maxlength="3"
										value="SIM"></div>
								</logic:equal>
								<logic:notEqual name="indicadorAceitacao" value="1">
									<div align="left"><input name="pagamentoAceito" type="text" readonly
										style="background-color:#EFEFEF; border:0" size="3" maxlength="3"
										value="NÃO"></div>
								</logic:notEqual>
							</td>
						</tr>
					</logic:present>
					
					<tr>
						<td colspan="2">
						<hr>
						</td>
					</tr>
				
				</logic:present>
				
				<!-- Parte nova de debito tipo -->
			<tr>
				<td colspan="4">

				<table width="100%" align="center" bgcolor="#79bbfd">
					<tr>
						<td colspan="3"><strong>Débito Tipo:</strong></td>
					</tr>
					<tr bgcolor="#99CCFF">
						<td td width="10%"><strong>Código</strong></td>
						<td td width="60%"><strong>Descrição</strong></td>
						<td td width="20%"><strong>Valor</strong></td>
					</tr>
					<tr bgcolor="#cbe5fe">
						<td width="100%" align="center" colspan="3">

						<table width="100%" border="0">
						   <%--Esquema de paginação--%>
 							   <pg:pager isOffset="true" index="half-full" maxIndexPages="10"
								export="currentPageNumber=pageNumber;pageOffset"
								maxPageItems="25" items="${sessionScope.totalRegistros}">
							  <pg:param name="pg" />
							  <pg:param name="q" />
							    <logic:present name="colecaoGuiaDebitoTipoConsulta">
								  <%int cont = 1;%>
									<logic:iterate name="colecaoGuiaDebitoTipoConsulta" id="guiaPagamentoItem" type="GuiaPagamentoItem">
									  <pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
										<%} else {
										%>
										<tr bgcolor="#cbe5fe">
										<%}%>
										 <td width="10%" align="center">
										   <div>
										      ${guiaPagamentoItem.debitoTipo.id}
											</div> 
										  </td>
										  <td width="60%">
										   <div>
										      ${guiaPagamentoItem.debitoTipo.descricao}
											</div> 
										  </td>
										  <td width="20%" align="right">
										   <div>
										      <bean:write name="guiaPagamentoItem" property="valorDebito" formatKey="money.format"/>
											</div> 
										  </td>
										</tr>
									</pg:item>
								  </logic:iterate>
							  </logic:present>
							</pg:pager>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
				
			<!-- Parte nova de credito tipo -->
			<tr>
				<td colspan="4">

				<table width="100%" align="center" bgcolor="#79bbfd">
					<tr>
						<td colspan="3"><strong>Crédito Tipo:</strong></td>
					</tr>
					<tr bgcolor="#99CCFF">
						<td td width="10%"><strong>Código</strong></td>
						<td td width="60%"><strong>Descrição</strong></td>
						<td td width="20%"><strong>Valor</strong></td>
					</tr>
					<tr bgcolor="#cbe5fe">
						<td width="100%" align="center" colspan="3">

						<table width="100%" border="0">
						   <%--Esquema de paginação--%>
 							   <pg:pager isOffset="true" index="half-full" maxIndexPages="10"
								export="currentPageNumber=pageNumber;pageOffset"
								maxPageItems="25" items="${sessionScope.totalRegistros}">
							  <pg:param name="pg" />
							  <pg:param name="q" />
							    <logic:present name="colecaoGuiaCreditoTipoConsulta">
								  <%int cont = 1;%>
									<logic:iterate name="colecaoGuiaCreditoTipoConsulta" id="guiaPagamentoItem" type="GuiaPagamentoItem">
									  <pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
										<%} else {
										%>
										<tr bgcolor="#cbe5fe">
										<%}%>
										 <td width="10%" align="center">
										   <div>
										      ${guiaPagamentoItem.creditoTipo.id}
											</div> 
										  </td>
										  <td width="60%">
										   <div>
										      ${guiaPagamentoItem.creditoTipo.descricao}
											</div> 
										  </td>
										  <td width="20%" align="right">
										   <div>
										      <bean:write name="guiaPagamentoItem" property="valorDebito" formatKey="money.format"/>
											</div> 
										  </td>
										</tr>
									</pg:item>
								  </logic:iterate>
							  </logic:present>
							</pg:pager>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
				
				
				
						
				
			</logic:present>
			<!-- final Guia Pagamento -->
			
			
			<!-- Inicio Guia Pagamento Historico -->
			
			<logic:present name="guiaPagamentoHistorico" scope="request">
			
				<tr>
					<td width="194"><strong> Localidade:</strong></td>
					<td width="395" align="right">
					<div align="left"><input name="codigoLocalidade" type="text"
						readonly style="background-color:#EFEFEF; border:0" size="3"
						maxlength="3" value="${requestScope.guiaPagamentoHistorico.localidade.id}">&nbsp;<input
						name="nomeLocalidade" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="30" maxlength="30"
						value="${requestScope.guiaPagamentoHistorico.localidade.descricao}">
						
						<input name="idGuiaPagamento" type="hidden" id="idGuiaPagamento"
						value="${requestScope.guiaPagamentoHistorico.id}"/></div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
	
					<td class="style1"><span class="style1"> <input
						name="matriculaImovel" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="9" maxlength="9"
						value="${requestScope.guiaPagamentoHistorico.imovel.id}"> </span> <span
						class="style1"> <input name="inscricaoImovel" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="20" maxlength="20"
						value="${requestScope.guiaPagamentoHistorico.imovel.inscricaoFormatada}"> </span></td>
	
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
	
							<td align="center"><strong>Endere&ccedil;o </strong></td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td>
							<div align="center">${requestScope.guiaPagamentoHistorico.imovel.enderecoFormatado}&nbsp;</div>
							</td>
						</tr>
	
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>C&oacute;digo do Cliente:</strong></td>
					<td class="style1"><span class="style1"> <input name="codigoCliente"
						type="text" readonly style="background-color:#EFEFEF; border:0"
						size="9" maxlength="9"
						value="${requestScope.guiaPagamentoHistorico.cliente.id}"> </span></td>
				</tr>
				<tr>
					<td class="style1"><strong>CPF/CNPJ:</strong></td>
					<c:if
						test="${requestScope.guiaPagamentoHistorico.cliente.clienteTipo.indicadorPessoaFisicaJuridica eq 1}"
						var="ehPessoaFisica">
						<c:set var="valorCpfCnpj" scope="page"
							value="${requestScope.guiaPagamentoHistorico.cliente.cpf}" />
					</c:if>
					<c:if test="${!ehPessoaFisica}">
						<c:set var="valorCpfCnpj" scope="page"
							value="${requestScope.guiaPagamentoHistorico.cliente.cnpj}" />
					</c:if>
	
	
					<td class="style1"><span class="style1"> <input
						name="cpfCnpjCliente" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="17" maxlength="17"
						value="${valorCpfCnpj}"> </span></td>
				</tr>
	
				<tr>
					<td class="style1"><strong>Nome do Cliente:</strong></td>
					<td class="style1"><span class="style1"> <input name="nomeCliente"
						type="text" readonly style="background-color:#EFEFEF; border:0"
						value="${requestScope.guiaPagamentoHistorico.cliente.nome}" size="50"
						maxlength="45"> </span></td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				
				<tr>
					<td><strong>Prestação:</strong></td>
					<td align="right">
					<div align="left"><input name="tipoDebito" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="10" maxlength="10"
						value="${requestScope.guiaPagamentoHistorico.prestacaoFormatada}"></div>
					</td>
	
				</tr>
				
				<tr>
					<td><strong>Situa&ccedil;&atilde;o da Guia de Pagamento:</strong></td>
					<td align="right">
					<div align="left"><input name="situacaoGuiaPagamento" type="text"
						readonly style="background-color:#EFEFEF; border:0" size="30"
						maxlength="30"
						value="${requestScope.guiaPagamentoHistorico.debitoCreditoSituacaoByDcstIdatual.descricaoDebitoCreditoSituacao}"></div>
					</td>
				</tr>
				
				<tr>
						<td width="183" height="25"><strong>Usuário:</strong></td>
						<td colspan="3">
						<logic:present name="guiaPagamentoHistorico" property="usuario">
							<html:text name="guiaPagamentoHistorico"
								property="usuario.nomeUsuario"
								size="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present>		
						<logic:notPresent name="guiaPagamentoHistorico" property="usuario">
							<input type="text" 
									name="usuario" 
									size="30"
									readonly="true" 
									style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
						</td>
					</tr>
				
				<tr>
					<td><strong>Data de Emiss&atilde;o:</strong></td>
					<td align="right">
					<div align="left"><input name="dataEmissao" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="10" maxlength="10"
						value="<bean:write name="guiaPagamentoHistorico" property="dataEmissao" formatKey="date.format" scope="request"/>"></div>
					<div align="left"><strong></strong></div>
	
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Data de Vencimento:</strong></td>
					<td align="right">
					<div align="left"><input name="dataVencimento" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="10" maxlength="10"
						value="<bean:write name="guiaPagamentoHistorico" property="dataVencimento" formatKey="date.format" scope="request"/>"></div>
					<div align="left"><strong></strong></div>
	
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Valor da Guia de Pagamento:<font color="#FF0000"> </font></strong></td>
					<td align="right">
					<div align="left"><input name="valorGuiaPagamento" type="text"
						readonly
						style="background-color:#EFEFEF; border:0; text-align:right;"
						size="17" maxlength="17"
						value="<bean:write name="guiaPagamentoHistorico" property="valorDebito" formatKey="money.format"/>"></div>
					<div align="left"><strong></strong></div>
	
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Registro de Atendimento:</strong></td>
					<td align="right">
					<div align="left"><input name="registroAtendimentoId" type="text"
						readonly style="background-color:#EFEFEF; border:0" size="9"
						maxlength="9"
						value="${requestScope.guiaPagamentoHistorico.registroAtendimento.id}"> <%-- Por enquanto este campo ficará em branco até que o campo DESCRICAO DO TIPO DE SOLICITACAO entrar na tabela --%>
					<input name="inscricaoImovel22232" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="40" maxlength="40"></div>
	
	
					<div align="left"><strong></strong></div>
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Ordem de Servi&ccedil;o:<strong></strong><font
						color="#FF0000"> </font></strong></td>
					<td align="right">
					<div align="left"><input name="ordemServicoId" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="9" maxlength="9"
						value="${requestScope.guiaPagamentoHistorico.ordemServico.id}"> <%-- Por enquanto este campo ficará em branco até que o campo DESCRICAO DO SERVIÇO entrar na tabela --%>
					<input name="inscricaoImovel22232" type="text" readonly
						style="background-color:#EFEFEF; border:0" size="40" maxlength="40"></div>
					<div align="left"><strong></strong></div>
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Tipo do Financiamento:</strong></td>
					<td align="right">
					<div align="left"><input name="tipoFinanciamentoDescricao"
						type="text" readonly style="background-color:#EFEFEF; border:0"
						size="40" maxlength="40"
						value="${requestScope.guiaPagamentoHistorico.financiamentoTipo.descricao}"></div>
					</td>
				</tr>
				
				<tr>
		      		<td><strong>Observação:</strong></td>
			        <td>
			        	<textarea name="observacao"
								readonly="true"
								style="background-color:#EFEFEF; border:0; overflow:hidden;" 
								cols="43" rows="6" wrap=virtual>
							${requestScope.guiaPagamentoHistorico.observacao}
						</textarea>
					</td>
			    </tr>
			    <tr>
					<td><strong>Emitir Observação?</strong></td>
					<td><strong> 
					<logic:equal name="guiaPagamentoHistorico" property="indicadorEmitirObservacao" value="1">
						<input type="text" property="indicadorEmitirObservacao"
							value="SIM" readonly style="background-color:#EFEFEF; border:0" />
					</logic:equal>
					<logic:notEqual name="guiaPagamentoHistorico" property="indicadorEmitirObservacao" value="1">
						<input type="text" property="indicadorEmitirObservacao"
							value="NÃO" readonly style="background-color:#EFEFEF; border:0" />
					</logic:notEqual>
					</td>
				</tr>
				
				<tr>
					<td><strong>Matrícula do Imóvel Origem:</strong></td>
					<td colspan="2">
										
						<logic:present name="guiaPagamentoHistorico" property="origem" scope="request">
							<input type="text" name="imovelOrigem" size="12" maxlength="10"
							readonly="true" style="background-color:#EFEFEF; border:0" 
							value="${requestScope.guiaPagamentoHistorico.origem.guiaPagamentoHistorico.imovel.id}"/>
						</logic:present>
											
						<logic:notPresent name="guiaPagamento" property="origem" scope="request">
							<input type="text" name="imovelOrigem" size="12" maxlength="10"
							readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>	
										
					</td>
				</tr>
				
				<logic:present name="contaPaga" scope="session">
					<tr>
						<td colspan="2">
						<hr>
						</td>
					</tr>
					
					<tr>
						<td colspan="2">Dados do Pagamento:</td>
					</tr>
					
					<tr>
						<td><strong>Nosso Número:</strong></td>
						<td align="right">
						<div align="left"><input name="nossoNumero" type="text" readonly
							style="background-color:#EFEFEF; border:0" size="18" maxlength="18"
							value="${requestScope.nossoNumero}"></div>
						</td>
					</tr>
					
					<logic:notEmpty name="arrecadador" scope="request">
						<tr>
							<td><strong>NSA do Movimento:</strong></td>
							<td align="right">
							<div align="left"><input name="nsaMovimento" type="text" readonly
								style="background-color:#EFEFEF; border:0" size="10" maxlength="10"
								value="${requestScope.arrecadador.numeroSequencialArquivo}"></div>
							</td>
						</tr>
						
						<tr>
							<td><strong>Valor do Movimento:</strong></td>
							<td align="right">
							<div align="left"><input name="valorMovimento" type="text" readonly
								style="background-color:#EFEFEF; border:0" size="12" maxlength="12"
								value="${requestScope.arrecadador.valorTotalMovimento}"></div>
							</td>
						</tr>
					</logic:notEmpty>
					
					<tr>
						<td><strong>Data Pagamento:</strong></td>
						<td align="right">
						<div align="left"><input name="dataPagamento" type="text" readonly
							style="background-color:#EFEFEF; border:0" size="10" maxlength="10"
							value="<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" scope="request"/>"></div>
						</td>
					</tr>
					
					<logic:present name="indicadorAceitacao" scope="request">
						<tr>
							<td><strong>Pagamento Aceito:</strong></td>
							<td align="right">
								<logic:equal name="indicadorAceitacao" value="1" scope="request">
									<div align="left"><input name="pagamentoAceito" type="text" readonly
										style="background-color:#EFEFEF; border:0" size="3" maxlength="3"
										value="SIM"></div>
								</logic:equal>
								<logic:notEqual name="indicadorAceitacao" value="1">
									<div align="left"><input name="pagamentoAceito" type="text" readonly
										style="background-color:#EFEFEF; border:0" size="3" maxlength="3"
										value="NÃO"></div>
								</logic:notEqual>
							</td>
						</tr>
					</logic:present>
				
				</logic:present>
				
							
			
			<!-- Parte nova de debito tipo -->
			<tr>
				<td colspan="4">

				<table width="100%" align="center" bgcolor="#79bbfd">
					<tr>
						<td colspan="3"><strong>Débito Tipo:</strong></td>
					</tr>
					<tr bgcolor="#99CCFF">
						<td td width="10%"><strong>Código</strong></td>
						<td td width="60%"><strong>Descrição</strong></td>
						<td td width="20%"><strong>Valor</strong></td>
					</tr>
					<tr bgcolor="#cbe5fe">
						<td width="100%" align="center" colspan="3">

						<table width="100%" border="0">
						   <%--Esquema de paginação--%>
 							   <pg:pager isOffset="true" index="half-full" maxIndexPages="10"
								export="currentPageNumber=pageNumber;pageOffset"
								maxPageItems="25" items="${sessionScope.totalRegistros}">
							  <pg:param name="pg" />
							  <pg:param name="q" />
							    <logic:present name="colecaoGuiaDebitoHistoricoTipoConsulta">
								  <%int cont = 1;%>
									<logic:iterate name="colecaoGuiaDebitoHistoricoTipoConsulta" id="guiaPagamentoItemHistorico" type="GuiaPagamentoItemHistorico">
									  <pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
										<%} else {
										%>
										<tr bgcolor="#cbe5fe">
										<%}%>
										 <td width="10%" align="center">
										   <div>
										      ${guiaPagamentoItemHistorico.debitoTipo.id}
											</div> 
										  </td>
										  <td width="60%">
										   <div>
										      ${guiaPagamentoItemHistorico.debitoTipo.descricao}
											</div> 
										  </td>
										  <td width="20%" align="right">
										   <div>
										   		<bean:write name="guiaPagamentoItemHistorico" property="valorDebito" formatKey="money.format"/>
											</div> 
										  </td>
										</tr>
									</pg:item>
								  </logic:iterate>
							  </logic:present>
							</pg:pager>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			
				<!-- Parte nova de credito tipo -->
			<tr>
				<td colspan="4">

				<table width="100%" align="center" bgcolor="#79bbfd">
					<tr>
						<td colspan="3"><strong>Crédito Tipo:</strong></td>
					</tr>
					<tr bgcolor="#99CCFF">
						<td td width="10%"><strong>Código</strong></td>
						<td td width="60%"><strong>Descrição</strong></td>
						<td td width="20%"><strong>Valor</strong></td>
					</tr>
					<tr bgcolor="#cbe5fe">
						<td width="100%" align="center" colspan="3">

						<table width="100%" border="0">
						   <%--Esquema de paginação--%>
 							   <pg:pager isOffset="true" index="half-full" maxIndexPages="10"
								export="currentPageNumber=pageNumber;pageOffset"
								maxPageItems="25" items="${sessionScope.totalRegistros}">
							  <pg:param name="pg" />
							  <pg:param name="q" />
							    <logic:present name="colecaoGuiaCreditoHistoricoTipoConsulta">
								  <%int cont = 1;%>
									<logic:iterate name="colecaoGuiaCreditoHistoricoTipoConsulta" id="guiaPagamentoItemHistorico" type="GuiaPagamentoItemHistorico">
									  <pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
										<%} else {
										%>
										<tr bgcolor="#cbe5fe">
										<%}%>
										 <td width="10%" align="center">
										   <div>
										      ${guiaPagamentoItemHistorico.creditoTipo.id}
											</div> 
										  </td>
										  <td width="60%">
										   <div>
										      ${guiaPagamentoItemHistorico.creditoTipo.descricao}
											</div> 
										  </td>
										  <td width="20%" align="right">
										   <div>
										      <bean:write name="guiaPagamentoItemHistorico" property="valorDebito" formatKey="money.format"/>
											</div> 
										  </td>
										</tr>
									</pg:item>
								  </logic:iterate>
							  </logic:present>
							</pg:pager>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			
			<!-- fim da parte nova -->
				
			</logic:present>
			
			<!-- final Guia Pagamento Historico -->
			
			<tr>
				<td colspan="2">
				<hr>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<table width="100%" border="0">
						<tr>
							<td height="24">
								<logic:present name="caminhoRetornoTelaConsultaGuiaPagamento">
									<input type="button" class="bottonRightCol" value="Voltar"
										style="width: 70px;" onclick="javascript:history.back();" />
								</logic:present>
							</td>
							<td align="right">
							
							<input type="button" name="" value="Imprimir Guia de Pagamento" class="bottonRightCol" 
							onclick="gerarGuia();"
							style="width:200px"
							/>
							
							<input name="Button" type="button"
								class="bottonRightCol" value="Fechar"
								onClick="javascript:window.close();"></td>
						</tr>
					</table>
				</td>
			</tr>
			
			
		</table>
	</tr>
</table>

</body>
</html>

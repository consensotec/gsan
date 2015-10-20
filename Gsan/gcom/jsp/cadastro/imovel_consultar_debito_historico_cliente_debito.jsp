<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper" isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gcom.faturamento.conta.Conta"%>
<%@ page import="gcom.cadastro.cliente.ClienteImovel"%>
<%@ page import="java.math.BigDecimal" %> 
<%@ page import="gcom.cobranca.HistoricoDebitosClienteImovelHelper"%>
<%@ page import="gcom.cobranca.bean.GuiaPagamentoValoresHelper"%>

<script language="JavaScript">
function extendeTabela(id){
	var form = document.forms[0];
	var display = eval('layer'+id).style.display;
	
	if(display == "none"){
  	    eval('layer'+id).style.display = 'block';
  	    eval('layerGuia'+id).style.display = 'block';
	}else{
	    eval('layer'+id).style.display = 'none';
	    eval('layerGuia'+id).style.display = 'none';
	}

}

function facilitadorConta(objeto, checkboxID){

	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";

		marcarTodos(checkboxID);
		
	}
	else{
		objeto.id = "0";
		desmarcarTodos(checkboxID);
	}
}

function facilitadorGuia(objeto, checkboxID){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos(checkboxID);
	}
	else{
		objeto.id = "0";
		desmarcarTodos(checkboxID);
	}
}

//Ativa todos os checkboxs
function marcarTodos(checkboxID){
	
	for (var i=0;i < document.forms[0].elements.length;i++){
		var elemento = document.forms[0].elements[i];
		
		if (elemento.type == "checkbox" && elemento.disabled == false && elemento.id == checkboxID){
			elemento.checked = true;
		}
	}
}

//Desativa todos os checkboxs
function desmarcarTodos(checkboxID) {
	
	for (var i=0;i < document.forms[0].elements.length;i++){
		var elemento = document.forms[0].elements[i];
		if (elemento.type == "checkbox" && elemento.id == checkboxID){
			elemento.checked = false;
		}
	}
}


</script>

<table width="100%" align="center" bgcolor="#90c7fc" border="0">
	<tr bordercolor="#79bbfd">
		<td colspan="7" align="center" bgcolor="#79bbfd"><strong>Histórico de Clientes com Débito</strong></td>
	</tr>
	<tr bordercolor="#000000">
		<td width="25%" bgcolor="#90c7fc" align="center">
			<div class="style9">
				<font color="#000000" style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<strong>Nome do	Cliente</strong> 
				</font>
			</div>
		</td>
		<td width="17%" bgcolor="#90c7fc" align="center">
			<div class="style9">
				<font color="#000000" style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<strong>Tipo da	Rela&ccedil;&atilde;o</strong> 
				</font>
			</div>
		</td>
		<td width="17%" bgcolor="#90c7fc" align="center">
			<div class="style9">
				<font color="#000000" style="font-size:9px"
				face="Verdana, Arial, Helvetica, sans-serif"> 
					<strong>Data In&iacute;cio Rela&ccedil;&atilde;o</strong>
				</font>
			</div>
		</td>
		<td width="17%" bgcolor="#90c7fc" align="center">
			<div class="style9">
				<font color="#000000" style="font-size:9px"
				face="Verdana, Arial, Helvetica, sans-serif"> 
					<strong>Data Fim Rela&ccedil;&atilde;o</strong>
				</font>
			</div>
		</td>
		<td width="17%" bgcolor="#90c7fc" align="center">
			<font
				color="#000000" style="font-size:9px"
				face="Verdana, Arial, Helvetica, sans-serif">
				<strong>CPF/CNPJ</strong>
			</font>
		</td>
		<td width="7%" bgcolor="#90c7fc" align="center">
			<div class="style9">
				<font color="#000000" style="font-size:9px"
				face="Verdana, Arial, Helvetica, sans-serif"> 
				<strong>Total Doc.</strong>
				</font>
			</div>
		</td>
		<td width="11%" bgcolor="#90c7fc" align="center">
			<div class="style9">
				<font color="#000000" style="font-size:9px"
				face="Verdana, Arial, Helvetica, sans-serif"> 
				<strong>Valor Débito</strong>
				</font>
			</div>
		</td>
	</tr>
	<tr>
		<td width="100%" colspan="7">
			<div style="width: 100%; height: 100%; overflow: auto;">
				<table width="100%" align="left" bgcolor="#99CCFF">
					<%int contCliente = 0;%>
					<logic:notEmpty name="colecaoHistoricoDebitosClienteImovel" scope="session">
						<logic:iterate name="colecaoHistoricoDebitosClienteImovel" id="historicoDebitosClienteImovel" 
										type="HistoricoDebitosClienteImovelHelper">
							<%contCliente = contCliente + 1;
							if (contCliente % 2 == 0) {%>
							<tr bgcolor="#cbe5fe">
								<%} else {%>
							<tr bgcolor="#FFFFFF">
								<%}%>
								<td width="25%" align="left" style="font-size:9px">
									<div align="left">
										<logic:present	name="historicoDebitosClienteImovel" property="clienteImovel">
											<logic:present name="historicoDebitosClienteImovel" property="clienteImovel.cliente">
												<a	href="javascript:abrirPopup('exibirConsultarClienteAction.do?desabilitarPesquisaCliente=SIM&codigoCliente='+<bean:write name="historicoDebitosClienteImovel" property="clienteImovel.cliente.id" />, 500, 800);">
													<bean:write name="historicoDebitosClienteImovel"
																property="clienteImovel.cliente.nome" />
												</a>
											</logic:present>
										</logic:present> 
									</div>
								</td>														
								<td width="17%" align="left" style="font-size:9px">
									<div align="left">
										<logic:present	name="historicoDebitosClienteImovel" property="clienteImovel">
											<logic:present name="historicoDebitosClienteImovel" property="clienteImovel.clienteRelacaoTipo">
												<bean:write name="historicoDebitosClienteImovel"
														property="clienteImovel.clienteRelacaoTipo.descricao" />
											</logic:present>	
										</logic:present> 
									</div>
								</td>
								<td width="17%" align="center" style="font-size:9px">
									<div align="center">
										<logic:present	name="historicoDebitosClienteImovel" property="clienteImovel">
											<logic:present name="historicoDebitosClienteImovel" property="clienteImovel.dataInicioRelacao">
												<%=""+ Util.formatarData(historicoDebitosClienteImovel.getClienteImovel().getDataInicioRelacao()) %>
											</logic:present> 
										</logic:present>	  		
									</div>
								</td>														
								<td width="17%" align="center" style="font-size:9px">
									<div align="center">
										<logic:present	name="historicoDebitosClienteImovel" property="clienteImovel">
											<logic:present name="historicoDebitosClienteImovel" property="clienteImovel.dataFimRelacao">
												<%=""+ Util.formatarData(historicoDebitosClienteImovel.getClienteImovel().getDataFimRelacao()) %>
											</logic:present> 
										</logic:present> 
									</div>
								</td>
								<td width="17%" align="right" style="font-size:9px">
									<div align="right">
									<logic:notEmpty	name="historicoDebitosClienteImovel" property="clienteImovel">
										<logic:notEmpty	name="historicoDebitosClienteImovel" property="clienteImovel.cliente">

											<bean:write name="historicoDebitosClienteImovel"
															property="clienteImovel.cliente.cpfFormatado" />
													
										</logic:notEmpty>
									</logic:notEmpty> 
									<logic:notEmpty name="historicoDebitosClienteImovel" property="clienteImovel.cliente">
										<logic:notEmpty name="historicoDebitosClienteImovel" property="clienteImovel.cliente.cnpj">
											<bean:write name="historicoDebitosClienteImovel"
													property="clienteImovel.cliente.cnpjFormatado" />
										</logic:notEmpty>
									</logic:notEmpty>
									</div>									
								</td>	
								<td width="7%" align="right" style="font-size:9px">
									<div align="right">
										<logic:present	name="historicoDebitosClienteImovel" property="totalDocumento">
											<bean:write name="historicoDebitosClienteImovel"
													property="totalDocumento" />
										</logic:present> 
									</div>
								</td>
								<td width="11%" align="center" style="font-size:9px">
									<div align="center">
										<logic:present	name="historicoDebitosClienteImovel" property="valorTotalDebitos">
											<c:choose>
												<c:when test='${historicoDebitosClienteImovel.sizeCollectionGuia > 0 || historicoDebitosClienteImovel.sizeCollectionConta > 0}'>
													<a href="javascript:extendeTabela(${historicoDebitosClienteImovel.clienteImovel.id});">
														<bean:write name="historicoDebitosClienteImovel"
																	property="valorTotalDebitos" formatKey="money.format"/>
													</a>
												</c:when>
												<c:otherwise>
													<bean:write name="historicoDebitosClienteImovel"
																property="valorTotalDebitos" formatKey="money.format"/>
												</c:otherwise>
											</c:choose>
										</logic:present> 
									</div>
								</td>															
							</tr>
							
							<!-- Tabela Contas do Cliente -->
	
								<tr>
									<td colspan="11">
									<div id="layer${historicoDebitosClienteImovel.clienteImovel.id}" style="display:none">
										<hr color="#ffffff">
										<table width="100%" align="center" bgcolor="#90c7fc" border="0">
											<logic:notEmpty name="historicoDebitosClienteImovel"  property="contasClienteImovelHelper" >
											<tr bordercolor="#79bbfd">
												<td colspan="11" align="center" bgcolor="#79bbfd">
												<strong>Contas do Cliente</strong>
												</td>
											</tr>
											
											<tr bordercolor="#000000">
												<td width="5%" bgcolor="#90c7fc" align="center">
													<div class="style9">
														<font color="#000000" style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> 
															<a href="javascript:facilitadorConta(this, 'conta${historicoDebitosClienteImovel.clienteImovel.id}');" id="0">
																<strong>Todos</strong>
															</a>
														</font>
													</div>
												</td>
												<td width="9%" bgcolor="#90c7fc" align="center">
													<div class="style9">
														<font color="#000000" style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> 
															<strong>M&ecirc;s/Ano</strong>
														</font>
													</div>
												</td>
												<td width="10%" bgcolor="#90c7fc" align="center">
													<div class="style9">
														<font color="#000000" style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> 
															<strong>Vencimento</strong>
														</font>
													</div>
												</td>
												<td width="10%" bgcolor="#90c7fc" align="center">
													<div class="style9">
														<font color="#000000" style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> 
															<strong>Valor de &Aacute;gua </strong> 
														</font>
													</div>
												</td>
												<td width="9%" bgcolor="#90c7fc" align="center">
													<div class="style9">
														<font color="#000000" style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> 
															<strong>Valor de Esgoto</strong> 
														</font>
													</div>
												</td>
												<td width="8%" bgcolor="#90c7fc" align="center">
													<div class="style9">
														<font color="#000000" style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> 
															<strong>Valor dos D&eacute;bitos</strong> 
														</font>
													</div>
												</td>
												<td width="8%" bgcolor="#90c7fc" align="center">
													<div class="style9">
														<font color="#000000" style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> 
															<strong>Valor dos Creditos</strong> 
														</font>
													</div>
												</td>										
												<td width="10%" bgcolor="#90c7fc" align="center">
												  <div class="style9">
												    <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
												      <strong>Valor dos Impostos</strong> 
												    </font>
												  </div>
												</td>											
												<td width="10%" bgcolor="#90c7fc" align="center">
													<div class="style9">
														<font color="#000000" style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> 
															<strong>Valor da Conta</strong> 
														</font>
													</div>
												</td>
												<td width="9%" bgcolor="#90c7fc" align="left">
													<div class="style9">
														<font color="#000000" style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> 
															<strong>Acr&eacute;sc. Impont.</strong>
														</font>
													</div>
												</td>
												<td width="7%" bgcolor="#90c7fc" align="left">
													<div class="style9">
														<font color="#000000" style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> 
															<strong>Sit.</strong>
														</font>
													</div>
												</td>
											</tr>
											</logic:notEmpty>
											<tr>
												<td width="100%" colspan="11">
													<div style="width: 100%; height: 90; overflow: auto;">
														<table width="100%" align="left" >
															<logic:notEmpty name="historicoDebitosClienteImovel"  property="contasClienteImovelHelper" >
																<%String corConta = "#cbe5fe";%>
																<%corConta = "#cbe5fe";%>
																<logic:iterate name="historicoDebitosClienteImovel" property="contasClienteImovelHelper" 
																				id="contavaloreshelper" type="ContaValoresHelper">
																		<%if (corConta.equalsIgnoreCase("#cbe5fe")) {
																			corConta = "#FFFFFF";%>
																		<tr bgcolor="#FFFFFF">
																			<%} else {
																				corConta = "#cbe5fe";%>
																		<tr bgcolor="#cbe5fe">
																			<%}%>
																		
																		<td align="center" width="5%" valign="middle">
																				<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<input id="conta${historicoDebitosClienteImovel.clienteImovel.id}"  type="checkbox" name="contavaloreshelper" value="c-<%="" + contavaloreshelper.getConta().getId().intValue()%>-<%="" + historicoDebitosClienteImovel.getClienteImovel().getCliente().getId().intValue()%>">
																				</logic:empty>
								
																				<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<input id="conta${historicoDebitosClienteImovel.clienteImovel.id}"  type="checkbox" name="contavaloreshelper" value="c-<%="" + contavaloreshelper.getConta().getId().intValue()%>-<%="" + historicoDebitosClienteImovel.getClienteImovel().getCliente().getId().intValue()%>" disabled>
																				</logic:notEmpty>
																			
																		</td>
																		<td align="left" width="9%">
																			<logic:notEmpty name="contavaloreshelper" property="conta">
																				<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<div align="center">
																						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																							<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																							<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
																							<bean:write name="conta" property="formatarAnoMesParaMesAno" /></a> 
																						</font>
																					</div>
																				</logic:empty>
								
																				<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<div align="center">
																						<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																							<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																							<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
																							<font color="#ff0000"><bean:write name="conta" property="formatarAnoMesParaMesAno" /></font></a> 
																						</font>
																					</div>
																				</logic:notEmpty>																								
																			</logic:notEmpty>
																		</td>
																		<td align="left" width="10%">
																			<logic:notEmpty name="contavaloreshelper" property="conta">
																				<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<div align="center">
																						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																							<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
																							<bean:write name="conta" property="dataVencimentoConta" formatKey="date.format" /> 
																						</font>
																					</div>
																				</logic:empty>
								
																				<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<div align="center">
																						<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																							<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
																							<bean:write name="conta" property="dataVencimentoConta" formatKey="date.format" /> 
																						</font>
																					</div>
																				</logic:notEmpty>
																			
																			</logic:notEmpty>
																		</td>
																		<td align="right" width="10%">
																			<logic:notEmpty name="contavaloreshelper" property="conta">
																															
																				<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<div align="right">
																						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																							<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
																							<bean:write name="conta" property="valorAgua" formatKey="money.format" />
																						</font>
																					</div>
																				</logic:empty>
								
																				<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<div align="right">
																						<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																							<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
																							<bean:write name="conta" property="valorAgua" formatKey="money.format" />
																						</font>
																					</div>
																				</logic:notEmpty>
																			
																				
																			</logic:notEmpty>
																		</td>
																		<td align="right" width="9%">
																			<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																				<div align="right">
																					<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																						<logic:notEmpty name="contavaloreshelper" property="conta">
																							<bean:define name="contavaloreshelper" property="conta" id="conta" />
																							<bean:write name="conta" property="valorEsgoto" formatKey="money.format" />
																						</logic:notEmpty> 
																					</font>
																				</div>
																			</logic:empty>
								
																			<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																				<div align="right">
																					<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																						<logic:notEmpty name="contavaloreshelper" property="conta">
																							<bean:define name="contavaloreshelper" property="conta" id="conta" />
																							<bean:write name="conta" property="valorEsgoto" formatKey="money.format" />
																						</logic:notEmpty> 
																					</font>
																				</div>	
																			</logic:notEmpty>
																		</td>
																		<td align="right" width="8%">
																			<logic:notEmpty name="contavaloreshelper" property="conta">
																																
																				<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<div align="right">
																						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																							<logic:notEqual name="contavaloreshelper" property="conta.debitos" value="0">
																								<a href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																								<bean:write name="contavaloreshelper" property="conta.debitos" formatKey="money.format" /> </a>
																							</logic:notEqual> 
																							<logic:equal name="contavaloreshelper" property="conta.debitos" value="0">
																								<bean:write name="contavaloreshelper" property="conta.debitos" formatKey="money.format" />
																							</logic:equal> 
																						</font>
																					</div>
																				</logic:empty>
								
																				<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<div align="right">
																						<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																							<logic:notEqual name="contavaloreshelper" property="conta.debitos" value="0">
																								<a href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																								<font color="#ff0000"><bean:write name="contavaloreshelper" property="conta.debitos" formatKey="money.format" /></font> </a>
																							</logic:notEqual> 
																							<logic:equal name="contavaloreshelper" property="conta.debitos" value="0">
																								<font color="#ff0000"><bean:write name="contavaloreshelper" property="conta.debitos" formatKey="money.format" /></font>
																							</logic:equal> 
																						</font>
																					</div>
																				</logic:notEmpty>
																				
																				
																			</logic:notEmpty>
																		</td>
																		
																		<td align="right" width="8%">
																			<logic:notEmpty name="contavaloreshelper" property="conta">
																				<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<div align="right">
																						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																							<logic:notEqual name="contavaloreshelper" property="conta.valorCreditos" value="0">	
																								<a href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?contaID=<bean:define name="contavaloreshelper"	property="conta" id="conta" />
																									<bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																									<bean:write name="contavaloreshelper" property="conta.valorCreditos" formatKey="money.format" />
																								</a>
																							</logic:notEqual> 
																							<logic:equal name="contavaloreshelper" property="conta.valorCreditos" value="0">
																								<bean:write name="contavaloreshelper" property="conta.valorCreditos" formatKey="money.format" />
																							</logic:equal> 
																						</font>
																					</div>
																				</logic:empty>
								
																				<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<div align="right">
																						<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																							<logic:notEqual name="contavaloreshelper" property="conta.valorCreditos" value="0">	
																								<a href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?contaID=<bean:define name="contavaloreshelper"	property="conta" id="conta" />
																									<bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																									<font color="#ff0000"><bean:write name="contavaloreshelper" property="conta.valorCreditos" formatKey="money.format" /></font>
																								</a>
																							</logic:notEqual> 
																							<logic:equal name="contavaloreshelper" property="conta.valorCreditos" value="0">
																								<font color="#ff0000"><bean:write name="contavaloreshelper" property="conta.valorCreditos" formatKey="money.format" /></font>
																							</logic:equal> 
																						</font>
																					</div>
																				</logic:notEmpty>
																			</logic:notEmpty>
																		</td>	
																		<td align="right" width="10%">
																			<logic:notEmpty name="contavaloreshelper" property="conta">
																															
																				<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<div align="right">
																						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																							<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
																							<bean:write name="conta" property="valorImposto" formatKey="money.format" />
																						</font>
																					</div>
																				</logic:empty> 
								
																				<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<div align="right">
																						<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																							<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
																							<bean:write name="conta" property="valorImposto" formatKey="money.format" />
																						</font>
																					</div>
																				</logic:notEmpty>
																				
																			</logic:notEmpty>
																		</td>
																		<td width="10%">
																			<logic:notEmpty name="contavaloreshelper" property="conta">
																			<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																				<div align="right">
																					<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																						<bean:write name="contavaloreshelper" property="conta.valorTotal" formatKey="money.format" />
																					</font>
																				</div>
																			</logic:empty>
							
																			<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																				<div align="right">
																					<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																						<bean:write name="contavaloreshelper" property="conta.valorTotal" formatKey="money.format" />
																					</font>
																				</div>
																			</logic:notEmpty>
																			</logic:notEmpty>
																		</td>
																		<td align="right" width="9%">
																			<logic:notEmpty name="contavaloreshelper" property="conta">
																				<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<div align="right" class="style9">
																						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																							<logic:notEqual name="contavaloreshelper" property="valorTotalContaValores" value="0">
																								<a href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
																									<bean:write name="contavaloreshelper" property="valorTotalContaValores" formatKey="money.format" />
																								</a>
																							</logic:notEqual> 
																							<logic:equal name="contavaloreshelper" property="valorTotalContaValores" value="0">
																								<bean:write name="contavaloreshelper" property="valorTotalContaValores" formatKey="money.format" />
																							</logic:equal> 
																						</font>
																					</div>
																				</logic:empty>
								
																				<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<div align="right" class="style9">
																						<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																							<logic:notEqual name="contavaloreshelper" property="valorTotalContaValores" value="0">
																								<a href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
																									<font color="#ff0000"><bean:write name="contavaloreshelper" property="valorTotalContaValores" formatKey="money.format" /></font>
																								</a>
																							</logic:notEqual> 
																							<logic:equal name="contavaloreshelper" property="valorTotalContaValores" value="0">
																								<bean:write name="contavaloreshelper" property="valorTotalContaValores" formatKey="money.format" />
																							</logic:equal> 
																						</font>
																					</div>
																				</logic:notEmpty>
																			</logic:notEmpty>
																		</td>
																		<td align="left" width="7%">
																			<logic:notEmpty name="contavaloreshelper" property="conta">
																				<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<div align="left" title="${contavaloreshelper.conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}">
																					<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																						<bean:write name="contavaloreshelper" property="conta.debitoCreditoSituacaoAtual.descricaoAbreviada" /> 
																					</font>
																				</div>
																				</logic:empty>
								
																				<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
																					<div align="left" title="${contavaloreshelper.conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}" >
																					<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																						<bean:write name="contavaloreshelper" property="conta.debitoCreditoSituacaoAtual.descricaoAbreviada" /> 
																					</font>
																				</div>
																				</logic:notEmpty>
																			</logic:notEmpty>
																		</td>
																</tr>
																</logic:iterate>
																		
																<%if (corConta.equalsIgnoreCase("#cbe5fe")) {
																	corConta = "#FFFFFF";%>
																<tr bgcolor="#FFFFFF">
																	<%} else {
																		corConta = "#cbe5fe";%>
																<tr bgcolor="#cbe5fe">
																	<%}%>
																<logic:notEmpty name="historicoDebitosClienteImovel"  property="contasClienteImovelHelper" >																									
																		<td bgcolor="#90c7fc" align="center" colspan="2">																									
																			<div class="style9" align="center">
																				<font color="#000000" style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
																		</font></div>
																		</td>
																		
																		<td align="left">
																			<bean:write name="historicoDebitosClienteImovel" property="quantidadeContas" />
																				&nbsp;
																				doc(s)
																		</td>
																		<td align="right">
																		<div align="right"><font color="#000000" style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> 
																			<bean:write name="historicoDebitosClienteImovel" property="valorTotalAgua" formatKey="money.format"/>
																		</font>
																		</div>
																		</td>
																		<td align="right">
																		<div align="right"><font color="#000000" style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> 
																			<bean:write name="historicoDebitosClienteImovel" property="valorTotalEsgoto" formatKey="money.format"/>
																		</font></div>
																		</td>
																		<td align="right">
																		<div align="right"><font color="#000000" style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> 
																			<bean:write name="historicoDebitosClienteImovel" property="valorTotalDebito" formatKey="money.format"/>
																		</font></div>
																		</td>
																		<td align="right">
																		<div align="right"><font color="#000000" style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> 
																			<bean:write name="historicoDebitosClienteImovel" property="valorTotalCredito" formatKey="money.format"/>
																		</font></div>
																		</td>
																		<td align="right">
																		<div align="right"><font color="#000000" style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> 
																			<bean:write name="historicoDebitosClienteImovel" property="valorTotalImposto" formatKey="money.format"/>
																		</font></div>
																		</td>
																		<td align="right">
																		<div align="right"><font color="#000000" style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> 
																			<bean:write name="historicoDebitosClienteImovel" property="valorTotalConta" formatKey="money.format"/>
																		</font></div>
																		</td>
																		<td align="right">
																		<div align="right" class="style9"><font color="#000000"
																			style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> 
																			<bean:write name="historicoDebitosClienteImovel" property="valorTotalAcrescimo" formatKey="money.format"/>
																		</font></div>
																		</td>
																		<td align="left">
																		<div align="left"><font color="#000000" style="font-size:9px"
																			face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
																		</td>														
																</logic:notEmpty>
																</tr>
															</logic:notEmpty>
														</table>
													</div>
												</td>
											</tr>
										</table>
								  	</div>
								</td>
							</tr>
							<!-- FIM da Tabela Contas do Cliente -->
							
							<!-- Tabela Guia de Pagamento do Cliente -->
							
							<tr>
								<td colspan="7">
								<div id="layerGuia${historicoDebitosClienteImovel.clienteImovel.id}" style="display:none">
								<table width="100%" align="center" bgcolor="#90c7fc" border="0">
									<logic:notEmpty name="historicoDebitosClienteImovel"  property="guiasPagamentoClienteImovel" >
									<tr bordercolor="#79bbfd" >
										<td colspan="6" bgcolor="#79bbfd" align="center"><strong>Guias de Pagamento</strong></td>
									</tr>
									<tr bordercolor="#000000">
										<td width="5%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><a href="javascript:facilitadorGuia(this, 'guia${historicoDebitosClienteImovel.clienteImovel.id}');" id="0"> <strong>Todos</strong></a> </font></div></td>
										<td width="36%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo do D&eacute;bito</strong> </font></div></td>
										<td width="11%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Prestação</strong> </font></div></td>
										<td width="13%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data de Emiss&atilde;o</strong> </font></div></td>
										<td width="13%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data de Vencimento</strong> </font></div></td>
										<td width="27%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor da Guia de Pagamento</strong> </font></div></td>
									</tr>
											<%String corGuia = "#cbe5fe";%>
											<%corGuia = "#cbe5fe";%>
											<logic:iterate name="historicoDebitosClienteImovel"  property="guiasPagamentoClienteImovel"
															id="guiapagamentohelper" type="GuiaPagamentoValoresHelper">
												<%if (corGuia.equalsIgnoreCase("#cbe5fe")) {
													corGuia = "#FFFFFF";
												%>
												<tr bgcolor="#FFFFFF">
													<%} else {
														corGuia = "#cbe5fe";%>
												<tr bgcolor="#cbe5fe">
													<%}%>
													
													<td align="center" width="5%" valign="middle">
														<input id="guia${historicoDebitosClienteImovel.clienteImovel.id}" type="checkbox" name="guiapagamentohelper" value="g-<%="" + guiapagamentohelper.getGuiaPagamento().getId().intValue()%>-<%="" + historicoDebitosClienteImovel.getClienteImovel().getCliente().getId().intValue()%>">
													</td>
													<td>
													<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <a
														href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<bean:define name="guiapagamentohelper" property="guiaPagamento" id="guiaPagamento" /><bean:write name="guiaPagamento" property="id" />', 600, 800);"><bean:define name="guiaPagamento" property="debitoTipo"	id="debitoTipo" /> <logic:notEmpty name="debitoTipo" property="descricao"><bean:write name="debitoTipo" property="descricao" /></logic:notEmpty> </a> </font></div>
													</td>
													<td>
													<div align="center" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
														name="guiapagamentohelper" property="guiaPagamento">
														<bean:define name="guiapagamentohelper"
															property="guiaPagamento" id="guiaPagamento" />
														<bean:write name="guiaPagamento" property="prestacaoFormatada"/>
													</logic:notEmpty> </font></div>
													</td>
													
													
													<td>
													<div align="center" class="style9"><font color="#000000"	style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<logic:notEmpty	name="guiapagamentohelper" property="guiaPagamento"> 
														<bean:define name="guiapagamentohelper" property="guiaPagamento" id="guiaPagamento" />
														<bean:write name="guiaPagamento" property="dataEmissao"	formatKey="date.format" />
													</logic:notEmpty> </font></div>
													</td>
													<td>
													<div align="center" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
														name="guiapagamentohelper" property="guiaPagamento">
														<bean:define name="guiapagamentohelper"
															property="guiaPagamento" id="guiaPagamento" />
														<bean:write name="guiaPagamento" property="dataVencimento"
															formatKey="date.format" />
													</logic:notEmpty> </font></div>
													</td>
													<td>
													<div align="right" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
														name="guiapagamentohelper" property="guiaPagamento">
														<bean:define name="guiapagamentohelper"
															property="guiaPagamento" id="guiaPagamento" />
														<bean:write name="guiaPagamento" property="valorDebito"
															formatKey="money.format" />
													</logic:notEmpty> </font></div>
													</td>
												</tr>
											</logic:iterate>
											<logic:notEmpty name="historicoDebitosClienteImovel"  property="guiasPagamentoClienteImovel" >
												<%if (corGuia.equalsIgnoreCase("#cbe5fe")) {
													corGuia = "#FFFFFF";%>
												<tr bgcolor="#FFFFFF">
													<%} else {
													corGuia = "#cbe5fe";%>
												<tr bgcolor="#cbe5fe">
													<%}%>
													<td bgcolor="#90c7fc" colspan="2">
													<div align="center" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
													</font></div>
													</td>
													<td>
													<bean:write name="historicoDebitosClienteImovel" property="quantidadeGuias" />
													&nbsp;
													doc(s)</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													<div align="right" class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="historicoDebitosClienteImovel" property="valorTotalGuia" formatKey="money.format"/>
													</font></div>
													</td>
												</tr>
											</logic:notEmpty>
										</logic:notEmpty>
									</table>
									<hr color="#ffffff">
									<br />
									</div>
								</td>
							</tr>
							<!-- FIM da Tabela Guia de Pagamento do Cliente -->
							
						</logic:iterate>
						
						<logic:notEmpty name="historicoDebitosClienteImovel" >
						<%	if (contCliente == 0) {	%>
							<tr bgcolor="#FFFFFF">
						<%	} else {	%>
							<tr bgcolor="#cbe5fe">
						<%	}	%>																									
								<td bgcolor="#90c7fc" align="center" colspan="4">																									
									<div class="style9" align="center">
										<font color="#000000" 
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
										</font>
									</div>
								</td>
								<td align="right" colspan="1">
									<%=session.getAttribute("totalDocumentosGeral")%>
									&nbsp;
									doc(s)
								</td>
								<td align="right" colspan="2">
									<%=Util.formatarMoedaReal( (BigDecimal) session.getAttribute("totalValorDocumentosGeral"))%>
								</td>
							</tr>
						</logic:notEmpty>
													
					</logic:notEmpty>
				</table>
			</div>
		</td>
	</tr>
</table>
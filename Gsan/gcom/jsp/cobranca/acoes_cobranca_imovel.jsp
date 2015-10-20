<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.cobranca.bean.AcoesCobrancaImovelHelper"%>
<%@page import="gcom.cobranca.bean.AcoesCobrancaTipoDebitoHelper"%>
<%@page import="gcom.util.Util"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="ExibirConsultarAcoesCobrancaImovelActionForm" />	
	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">	
	
	function voltar(){
		var form = document.forms[0];
		
		form.action = "exibirConsultarAcoesCobrancaImovelAction.do";
		form.submit();
	}
	
	function extendeTabela(display, cont){
		var form = document.forms[0];
		
		if(display){
		  	eval('layerHideDadosAcaoCobranca' + cont).style.display = 'none';
 			eval('layerShowDadosAcaoCobranca' + cont).style.display = 'block';
		}else{
		  	eval('layerHideDadosAcaoCobranca' + cont).style.display = 'block';
 			eval('layerShowDadosAcaoCobranca' + cont).style.display = 'none';
		}
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5">
	
	<html:form action="/consultarAcoesCobrancaImovelAction"
	    name="ExibirConsultarAcoesCobrancaImovelActionForm"
	    type="gcom.gui.cobranca.ExibirConsultarAcoesCobrancaImovelActionForm"
		method="post">
		
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
				
				<td width="610" valign="top" class="centercoltext">
					<table height="100%">
						<tr>
							<td></td>
						</tr>
					</table>
					<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="11"><img src="imagens/parahead_left.gif" border="0"></td>
							<td class="parabg">Ações de Cobrança do Imóvel</td>
							<td valign="top" width="11"><img src="imagens/parahead_right.gif"
								border="0"></td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table border="0" width="100%">
						<tr>
							<td>
								<table border="0" width="100%">
									<tr>
										<td ><strong>Matrícula do Imóvel:</strong></td>
										<td><html:text maxlength="10"
											property="idImovel" size="10"
											readonly="true" />
										</td>
									</tr>
									<tr>
										<td><strong>Período de Execução das Ações:</strong></td>
										<td><html:text
											property="periodoInicialAcao"
											size="10" maxlength="10"
											readonly="true" />
										 <strong> a </strong> 
										<html:text
											property="periodoFinalAcao"
											size="10" maxlength="10"
											readonly="true" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						
						<tr>
							<td colspan="4">
								<hr>
							</td>
						</tr>
						
						<tr>
							<td>
								<table border="0" width="100%">
									<tr>
										<td ><strong>Inscrição:</strong></td>
										<td ><html:text
											property="inscricaoImovel"
											readonly="true"
											size="18" maxlength="18" />
										</td>
										<td><strong>Grupo Atual:</strong></td>
										<td><html:text
											property="grupoCobrancaAtual"
											readonly="true"
											size="3" maxlength="3" />
										</td>
									</tr>
									
									<tr>
										<td><strong>Sit. Água:</strong></td>
										<td ><html:text
											property="descricaoSituacaoLigacaoAgua"
											size="18" maxlength="18"
											readonly="true" />
										</td>
										<td><strong>Sit. Esgoto:</strong></td>
										<td ><html:text
											property="descricaoSituacaoLigacaoEsgoto"
											size="18" maxlength="18"
											readonly="true" />
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
									<tr bgcolor="#FFFFFF">
										<td align="center">
										<div class="style9"><bean:write name="ExibirConsultarAcoesCobrancaImovelActionForm" property="enderecoImovel" /></div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						
						<tr>
							<td colspan="4">
								<hr>
							</td>
						</tr>
						
						<%int cont = 0;%>
						<logic:present name="colecaoHelper" scope="request" >
							<tr>
								<td align="center">
									<b><strong>Ações de Cobrança</strong></b>
								</td>
							</tr>
							
							<logic:iterate name="colecaoHelper" type="AcoesCobrancaImovelHelper" id="helper">
								<% int count = 1; %>
								<% cont = cont + 1; %>
								<tr>
									<td colspan="4" width="100%">
									 	<div id="layerHideDadosAcaoCobranca<%= cont %>" style="display:block">
				               				<table width="100%" border="0" bgcolor="#99CCFF">
						    					<tr bgcolor="#99CCFF">
				                      				<td align="center">
				                     					<span class="style2">
				                     					<a href="javascript:extendeTabela(true, <%=cont %>);" >
				                      						<b>
				                      							<% if(helper.getGrupoCobranca() != null && helper.getMesAnoCronograma() != null) { %>
				                      								<%= helper.getDescricaoAcaoCobranca().toUpperCase() + " - " +
						                     							"GRUPO " + helper.getGrupoCobranca() + " - " + 
						                      							helper.getMesAnoCronograma() %>
						                      					<% }else { %>
						                      						<%= helper.getDescricaoAcaoCobranca().toUpperCase() %>
						                      					<% } %>
				                      						</b>
				                      					</a>
				                     					</span>
				                      				</td>
				                     			</tr>
				                    		</table>
					           			</div>
									</td>
								</tr>
							
								<tr>
									<td width="100%" colspan="4">
										<div id="layerShowDadosAcaoCobranca<%= cont %>" style="display:none; overflow: auto;" >
											<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0">
												<tr bgcolor="#99CCFF">
							                   		<td align="center">
							           					<span class="style2">
							             					<a href="javascript:extendeTabela(false, <%=cont %>);" >
							             						<b>
							             							<% if(helper.getGrupoCobranca() != null && helper.getMesAnoCronograma() != null) { %>
							             								<%= helper.getDescricaoAcaoCobranca().toUpperCase() + " - " +
							                     							"GRUPO " + helper.getGrupoCobranca() + " - " + 
							                      							helper.getMesAnoCronograma() %>
							                      					<% } else { %>
							                      						<%= helper.getDescricaoAcaoCobranca().toUpperCase() %>
						                      						<% } %>
					                      						</b>
							             					</a>
							           					</span>
							               			</td>
							              		</tr>
							              	</table>
							              	
							              	<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
							              		<tr>
							              			<td>&nbsp;</td>
							              		</tr>
							              	</table>
							              	
							              	<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
							              		<tr bgcolor="#cbe5fe" >
							              			<td width="100%" align="center">
							              				<div style="height:100px; overflow: auto;">
							              					<table width="100%" bgcolor="#99CCFF">
							              						<tr bgcolor="#99CCFF">
							              							<td align="center" colspan="5" ><strong>Documento de Cobrança</strong></td>
							              						</tr>
							              						<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
							              							<td width="20%" bgcolor="#90c7fc">
																		<div align="center"><strong>Ação Cobrança</strong></div>
																	</td>
							              							<td width="20%" bgcolor="#90c7fc">
																		<div align="center"><strong>Valor</strong></div>
																	</td>
																	<td width="20%" bgcolor="#90c7fc">
																		<div align="center"><strong>Situa&ccedil;&atilde;o</strong></div>
																	</td>
																	<td width="20%" bgcolor="#90c7fc">
																		<div align="center"><strong>Data Vencimento</strong></div>
																	</td>
																	<td width="20%" bgcolor="#90c7fc">
																		<div align="center"><strong>Data Pagamento/Parcelamento/Cancelamento</strong></div>
																	</td>
							              						</tr>
							              						<%if (count % 2 == 0) {%>
							              						<tr bgcolor="#cbe5fe" class="styleFontePequena" >
																<%} else {%>
																<tr bgcolor="#FFFFFF" class="styleFontePequena">
																<%}%>
																	<td width="20%" align="center">	
											                     		<span class="style2">
											                     			<b>${helper.nomeAcaoCobranca}</b>
											                      		</span>
																	</td>
																	<td width="20%" align="center">	
											                     		<span class="style2">
											                     			<b>${helper.valorDocumento}</b>
											                      		</span>
																	</td>
																	<td width="20%" align="center">	
											                     		<span class="style2">
											                     			<b>${helper.descricaoSituacaoDebito}</b>
											                      		</span>
																	</td>
																	<td width="20%" align="center">	
											                     		<span class="style2">
											                     			<b>${helper.dataVencimento}</b>
											                      		</span>
																	</td>
																	<td width="20%" align="center">	
											                     		<span class="style2">
											                     			<b>${helper.dataSituacao}</b>
											                      		</span>
																	</td>
																</tr>
															</table>
							              				</div>
							              			</td>
							              		</tr>
							              	</table>
							              	
							              	<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
							              		<% if(helper.getDataExecucao() != null && !helper.getDataExecucao().equals("")) { %>
													<tr>
														<td>
															<span class="style2">
								                     			<b>Data de Execução: ${helper.dataExecucao}</b>
								                      		</span>	
														</td>
													</tr>
												<% } %>
																
												<% if(helper.getValorTaxaSucesso() != null && !helper.getValorTaxaSucesso().toString().equals("0")) { %>
													<tr>
														<td>
															<span class="style2">
								                     			<b>Valor da Taxa de Sucesso: R$ ${helper.valorTaxaSucesso}</b>
								                      		</span>	
														</td>
													</tr>
												<% } %>
												<tr>
													<td>&nbsp;</td>
												</tr>
											</table>
							              	
							              	<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
							              		<tr bgcolor="#cbe5fe" >
							              			<td width="100%" align="center">
							              				<div style="height:100px; overflow: auto;">
							              					<table width="100%" bgcolor="#99CCFF">
							              						<tr bgcolor="#99CCFF">
							              							<td align="center" colspan="5" ><strong>Ordem de Serviço</strong></td>
							              						</tr>
							              						<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
							              							<td width="10%" bgcolor="#90c7fc">
																		<div align="center"><strong>Número OS</strong></div>
																	</td>
																	<td width="10%" bgcolor="#90c7fc">
																		<div align="center"><strong>Situa&ccedil;&atilde;o</strong></div>
																	</td>
																	<td width="20%" bgcolor="#90c7fc">
																		<div align="center"><strong>Motivo Encerramento</strong></div>
																	</td>
																	<td width="25%" bgcolor="#90c7fc">
																		<div align="center"><strong>Empresa/Usuário Encerramento</strong></div>
																	</td>
																	<td width="35%" bgcolor="#90c7fc">
																		<div align="center"><strong>Observações</strong></div>
																	</td>
							              						</tr>
							              						<%if (count % 2 == 0) {%>
							              						<tr bgcolor="#cbe5fe" class="styleFontePequena" >
																<%} else {%>
																<tr bgcolor="#FFFFFF" class="styleFontePequena">
																<%}%>
																	<td width="10%" align="center">	
											                     		<span class="style2">
											                     			<b>${helper.numeroOS}</b>
											                      		</span>
																	</td>
																	<td width="10%" align="center">	
											                     		<span class="style2">
											                     			<b>${helper.situacaoOS}</b>
											                      		</span>
																	</td>
																	<td width="20%" align="center">	
											                     		<span class="style2">
											                     			<b>${helper.motivoEncerramentoOS}</b>
											                      		</span>
																	</td>
																	<td width="25%" align="center">	
											                     		<span class="style2">
											                     			<b>${helper.empresaEncerramentoOS} - ${helper.usuarioEncerramentoOS}</b>
											                      		</span>
																	</td>
																	<td width="35%" align="center">	
											                     		<span class="style2">
											                     			<b>${helper.observacoesOS}</b>
											                      		</span>
																	</td>
																</tr>
															</table>
							              				</div>
							              			</td>
							              		</tr>
							              	</table>
							              	
							              	<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
							              		<% if(helper.getFiscalizacaoOS() != null && !helper.getFiscalizacaoOS().equals("")) { %>
							              			<tr>
							              				<td>
															<span class="style2">
								                     			<b>${helper.fiscalizacaoOS}</b>
								                      		</span>	
														</td>
							              			</tr>
							              		<% } %>
							              		
							              		<% if(helper.getDescricaoFiscalizacaoSituacao() != null && !helper.getDescricaoFiscalizacaoSituacao().equals("")) { %>
								              		<tr>
								              			<td>
															<span class="style2">
								                     			<b>Retorno de Fiscalização: ${helper.descricaoFiscalizacaoSituacao}</b>
								                      		</span>	
														</td>
													</tr>
												<% } %>
												
												<% if(!Util.isVazioOrNulo(helper.getColTipoDebito())) { %>
													<bean:define name="helper" property="colTipoDebito" id="tipoDebito"></bean:define>	
													
													<logic:iterate name="tipoDebito" type="AcoesCobrancaTipoDebitoHelper" id="tipoDebitoHelper">
														<% if(tipoDebitoHelper.getValorDebito() != null) { %>				
															<tr>
																<td>
																	<span class="style2">
										                     			<b>${tipoDebitoHelper.tipoDebito}: R$ ${tipoDebitoHelper.valorDebito}</b>
										                      		</span>	
																</td>
															</tr>
														<% } %>
													</logic:iterate>
												<% } %>
												
												<% if(helper.getExibirPenalidade() != null && !helper.getExibirPenalidade().equals("")) { %>
													<tr>
														<td>
															<span class="style2">
								                     			<b>${helper.exibirPenalidade}</b>
								                      		</span>	
														</td>
													</tr>
												<% } %>
												
												<% if(helper.getValorTaxaServico() != null && !helper.getValorTaxaServico().toString().equals("0")) { %>
													<tr>
														<td>
															<span class="style2">
								                     			<b>Valor Pago do Serviço: R$ ${helper.valorTaxaServico}</b>
								                      		</span>	
														</td>
													</tr>
												<% } %>
												
												<tr>
													<td>&nbsp;</td>
												</tr>
											</table>
							            </div>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
						
						<tr>
							<td>
								<input name="Button" type="button" class="bottonRightCol"
									value="Voltar" align="left"
									onclick="javascript:voltar();">
							</td>
						</tr>
					</table>
				<p class="style1">&nbsp;</p>
				</td>
			</tr>
		</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	</html:form>
</body>

</html:html>
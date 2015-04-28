<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gsan.cobranca.CobrancaDocumentoItem" %>
<%@ page import="gsan.util.Util" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">


<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>


</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirApresentarItensDocumentoCobrancaAction" method="post"
	type="gsan.gui.cadastro.imovel.DocumentoCobrancaItemActionForm"
	name="DocumentoCobrancaItemActionForm">


<table width="610" border="0" cellspacing="5" cellpadding="0">
  <tr>

	<td width="610" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Consultar Itens do Documento de Cobrança</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

<!-- ============================================================================================================================== -->

      <table width="100%" border="0">
      <tr> 
          <td colspan="7">
				
			<table width="100%" align="center" bgcolor="#99CCFF" border="0">
				<tr>
					<td>
					<strong>Dados do Imóvel:</strong>

					</td>
				</tr>
				<tr bgcolor="#cbe5fe">
					<td width="100%" align="center" colspan="2">
			
						<table width="100%" border="0">
							<tr> 
								<td height="10" width="150"><strong>Matrícula:</strong></td>
								<td>
									<html:text property="matriculaImovel" size="25" readonly="true" style="background-color:#EFEFEF; border:0"/>
								</td>
							</tr>
							<tr> 
								<td height="10" width="150"><strong>Inscrição:</strong></td>
								<td>
									<html:text property="inscricaoImovel" size="25" readonly="true" style="background-color:#EFEFEF; border:0"/>
								</td>
							</tr>
							<tr> 
								<td height="10"><strong>Situação de Água:</strong></td>
								<td>
									<html:text property="situacaoAguaImovel" size="45" readonly="true" style="background-color:#EFEFEF; border:0"/>
								</td>
							</tr>
							<tr> 
								<td height="10"><strong>Situação de Esgoto:</strong></td>
								<td>
									<html:text property="situacaoEsgotoImovel" size="45" readonly="true" style="background-color:#EFEFEF; border:0"/>
								</td>
							</tr>
							<logic:notEmpty name="cobrancaDocumentoHelper" property="cobrancaDocumento.imovel">
								<tr> 
									<td height="10" colspan="3">
									
										<table width="100%" bgcolor="#99CCFF" border="0">
		                                 <tr bgcolor="#79BBFD">
		                                   	<td align="center"><strong>Endere&ccedil;o</strong></td>
		                                 </tr>
		                                 <tr>
		                                	<td bgcolor="#FFFFFF">
		                                		<div align="center"><bean:write name="cobrancaDocumentoHelper" property="cobrancaDocumento.imovel.enderecoFormatado" scope="request"/></div>
											</td>
										</tr>
										</table>
										
									</td>
								</tr>
							</logic:notEmpty>
						</table>

					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="100%" align="center" bgcolor="#99CCFF" border="0">
				<tr>
					<td>
						<strong>Dados do Cliente:</strong>
					</td>
				</tr>
				<tr bgcolor="#cbe5fe">
					<td width="100%" align="center" colspan="2">
			
						<table width="100%" border="0">
							<tr> 
								<td height="10" width="150"><strong>Código:</strong></td>
								<td>
									<html:text property="codigoCliente" size="10" readonly="true" style="background-color:#EFEFEF; border:0"/>
								</td>
							</tr>
							<tr> 
								<td height="10" width="150"><strong>Nome:</strong></td>
								<td>
									<html:text property="nomeCliente" size="60" readonly="true" style="background-color:#EFEFEF; border:0"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		  </td>
      </tr>
      <tr>
      	<td colspan="5" height="10"></td>
      </tr>
      <tr>
      	<td colspan="5">
      		<table width="100%" border="0">
			<tr> 
				<td height="10" width="155"><strong>Sequencial Documento:</strong></td>
		        <td>
					<html:text property="sequencial" size="10" readonly="true" style="background-color:#EFEFEF; border:0;"/>
		        </td>
		        <td colspan="3" width="380"></td>
			</tr>
			<tr>
				<td height="10"><strong>Vl. Documento:</strong></td>
		        <td>
					<html:text property="valorDocumento" size="10" readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;"/>
		        </td>
		        <td height="10"><strong>Vl. Desconto:</strong></td>
		        <td>
					<html:text property="valorDesconto" size="10" readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;"/>
		        </td>
			</tr>
			<tr> 
				<td height="10" width="155"><strong>Mot. Não Entrega do Documento:</strong></td>
		        <td>
					<html:text property="motivoNaoEntregaDocumento" size="25" readonly="true" style="background-color:#EFEFEF; border:0;"/>
		        </td>
		        <td colspan="3" width="380"></td>
			</tr>
			<tr> 
				<td height="10" width="155"><strong>Qtde. Itens:</strong></td>
		        <td>
					<html:text property="qtdItens" size="10" readonly="true" style="background-color:#EFEFEF; border:0;"/>
		        </td>
		        <td colspan="3" width="380"></td>
			</tr>
			</table>
      	</td>
      </tr>
      
	  <logic:present name="contaPaga" scope="session">
		<tr>
			<td colspan="2"><hr></td>
		</tr>
			
		<table width="100%" border="0" >
			<tr>
				<td colspan="2"><strong>Dados do Pagamento:</strong></td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td style="width:120px;"><strong>Nosso Número:</strong></td>
				<td>
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
				<td colspan="2">&nbsp;</td>
			</tr>
		</table>
	  </logic:present>
      
      <tr> 
          <td colspan="5">
				
				<table width="100%" align="center" bgcolor="#99CCFF" border="0">
				<tr>
					<td><strong>Emissão:</strong></td>
				</tr>
				<tr bgcolor="#cbe5fe">
					<td width="100%" align="center">
			
						<table width="100%" border="0">
						<tr> 
							<td height="10" width="150"><strong>Forma:</strong></td>
					        <td width="150">
								<html:text property="formaEmissao" size="25" readonly="true" style="background-color:#EFEFEF; border:0;"/>
					        </td>
					        <td height="10" width="110"><strong>Data/Hora:</strong></td>
					        <td>
								<html:text property="dataHoraEmissao" size="17" readonly="true" style="background-color:#EFEFEF; border:0;"/>
					        </td>
						</tr>
						<tr>
							<td height="10" width="150"><strong>Responsável:</strong></td>
					        <td colspan="3">
								<html:text property="nomeUsuario" size="60" readonly="true" style="background-color:#EFEFEF; border:0;"/>
					        </td>
						</tr>						
						</table>

					</td>
				</tr>
				</table>
		  </td>
      </tr>
      
      <%String cor = "#FFFFFF";%>
      
      <tr>
      	<td colspan="5" height="10"></td>
      </tr>
      <tr>
      	<td colspan="5">
      	
      		<table width="100%" border="0">
	  		<tr> 
          		<td height="17" colspan="5"><strong>Contas:</strong></td>
          	</tr>
      		<tr> 
          		<td colspan="5">
		  
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr> 
                		<td> 
					
							<table width="100%" bgcolor="#99CCFF" border="0">
                    		<tr bgcolor="#99CCFF"> 

								<td align="center" width="10%"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Mês/Ano</strong></font></td>
								<td width="15%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Vencimento</strong></font></div></td>
								<td width="10%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Água</strong></font></div></td>
								<td width="10%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Esgoto</strong></font></div></td>
								<td width="10%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Débitos</strong></font></div></td>
								<td width="10%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Créditos</strong></font></div></td>
								<td width="10%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Impostos</strong></font></div></td>
								<td width="10%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Vl. Conta</strong></font></div></td>
								<td width="15%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Situação</strong></font></div></td>
								<td width="10%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Vl. Item Cobrado</strong></font></div></td>
							</tr>
                    		</table>
					
						
            		</tr>
            		
            		<logic:notEmpty name="cobrancaDocumentoHelper" property="colecaoCobrancaDocumentoItemConta">
            
            		<tr> 
						<td> 
					
							<div style="width: 100%; height: auto; overflow: auto;">	
							
							<%cor = "#FFFFFF";%>
							
							<table width="100%" align="center" bgcolor="#99CCFF">

								<logic:iterate name="cobrancaDocumentoHelper" property="colecaoCobrancaDocumentoItemConta" id="cobrancaDocumentoItemConta" type="CobrancaDocumentoItem">
                            
									
									
									<%	if (cor.equalsIgnoreCase("#FFFFFF")){
											cor = "#cbe5fe";%>
											<tr bgcolor="#FFFFFF">
									<%} else{
											cor = "#FFFFFF";%>
											<tr bgcolor="#cbe5fe">
									<%}%>
										<td width="10%">
										<div align="center">

											<logic:present name="cobrancaDocumentoItemConta" property="contaGeral">	
												<bean:define name="cobrancaDocumentoItemConta" property="contaGeral" id="contaGeral"/>
													<logic:present name="contaGeral" property="conta">
														<bean:define name="contaGeral" property="conta" id="conta"/>
															<logic:present name="conta" property="referencia">	
																	<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + cobrancaDocumentoItemConta.getContaGeral().getConta().getId()%>' , 600, 800);" title="<%="" + Util.formatarMesAnoReferencia(cobrancaDocumentoItemConta.getContaGeral().getConta().getReferencia())%>"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><%=""+ Util.formatarMesAnoReferencia(cobrancaDocumentoItemConta.getContaGeral().getConta().getReferencia())%></font></a>
															</logic:present>

													</logic:present>	
													<logic:present name="contaGeral" property="contaHistorico">
															<bean:define name="contaGeral" property="contaHistorico" id="contaHistorico"/>
															<logic:present name="contaHistorico" property="anoMesReferenciaConta">	
																<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + cobrancaDocumentoItemConta.getContaGeral().getContaHistorico().getId()%>' , 600, 800);" title="<%="" + Util.formatarMesAnoReferencia(cobrancaDocumentoItemConta.getContaGeral().getContaHistorico().getAnoMesReferenciaConta())%>"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><%=""+ Util.formatarMesAnoReferencia(cobrancaDocumentoItemConta.getContaGeral().getContaHistorico().getAnoMesReferenciaConta())%></font></a>
															</logic:present>
													</logic:present>
											</logic:present>
										</div>
										</td>
										<td width="15%">
										<div align="center">

											<logic:present name="cobrancaDocumentoItemConta" property="contaGeral">	
												<bean:define name="cobrancaDocumentoItemConta" property="contaGeral" id="contaGeral"/>
													<logic:present name="contaGeral" property="conta">
														<bean:define name="contaGeral" property="conta" id="conta"/>
															<logic:present name="conta" property="dataVencimentoConta">	
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="conta" property="dataVencimentoConta" formatKey="date.format" /></font>
															</logic:present>
													</logic:present>	
													
													<logic:present name="contaGeral" property="contaHistorico">
															<bean:define name="contaGeral" property="contaHistorico" id="contaHistorico"/>
															<logic:present name="contaHistorico" property="dataVencimentoConta">	
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="contaHistorico" property="dataVencimentoConta" formatKey="date.format" /></font>
															</logic:present>
													</logic:present>	
											</logic:present>
										</div>
										</td>
										<td width="10%">
										<div align="right">
											<logic:present name="cobrancaDocumentoItemConta" property="contaGeral">	
												<bean:define name="cobrancaDocumentoItemConta" property="contaGeral" id="contaGeral"/>
													<logic:present name="contaGeral" property="conta">
														<bean:define name="contaGeral" property="conta" id="conta"/>
															<logic:present name="conta" property="valorAgua">	
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="conta" property="valorAgua" formatKey="money.format" /></font>
															</logic:present>

													</logic:present>	
													<logic:present name="contaGeral" property="contaHistorico">
															<bean:define name="contaGeral" property="contaHistorico" id="contaHistorico"/>
															<logic:present name="contaHistorico" property="valorAgua">	
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="contaHistorico" property="valorAgua" formatKey="money.format" /></font>
															</logic:present>
													</logic:present>	
											</logic:present>
										</div>
										</td>
										<td width="10%">
										<div align="right">
										
											<logic:present name="cobrancaDocumentoItemConta" property="contaGeral">	
												<bean:define name="cobrancaDocumentoItemConta" property="contaGeral" id="contaGeral"/>
													<logic:present name="contaGeral" property="conta">
														<bean:define name="contaGeral" property="conta" id="conta"/>
															<logic:present name="conta" property="valorEsgoto">	
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="conta" property="valorEsgoto" formatKey="money.format" /></font>
															</logic:present>

													</logic:present>	
													<logic:present name="contaGeral" property="contaHistorico">
															<bean:define name="contaGeral" property="contaHistorico" id="contaHistorico"/>
															<logic:present name="contaHistorico" property="valorEsgoto">	
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="contaHistorico" property="valorEsgoto" formatKey="money.format" /></font>
															</logic:present>
													</logic:present>	
											</logic:present>
											
										</div>
										</td>
										<td width="10%">
										<div align="right">

											<logic:present name="cobrancaDocumentoItemConta" property="contaGeral">	
												<bean:define name="cobrancaDocumentoItemConta" property="contaGeral" id="contaGeral"/>
													<logic:present name="contaGeral" property="conta">
														<bean:define name="contaGeral" property="conta" id="conta"/>
															<logic:notEqual name="conta" property="debitos" value="0">
																<a href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?tipoConsulta=conta&caminhoRetornoTelaConsultaDebitos=exibirDocumentoCobrancaItemAction&contaID=<%="" + cobrancaDocumentoItemConta.getContaGeral().getConta().getId()%>' , 500, 800);" title="<%="" + Util.formatarMoedaReal(cobrancaDocumentoItemConta.getContaGeral().getConta().getDebitos())%>"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="conta" property="debitos" formatKey="money.format" /></font></a>
															</logic:notEqual>
															
															<logic:equal name="conta" property="debitos" value="0">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="conta" property="debitos" formatKey="money.format" /></font>
															</logic:equal>	

													</logic:present>

													<logic:present name="contaGeral" property="contaHistorico">
															<bean:define name="contaGeral" property="contaHistorico" id="contaHistorico"/>

															<logic:notEqual name="contaHistorico" property="valorDebitos" value="0">
																<a href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?tipoConsulta=contaHistorico&caminhoRetornoTelaConsultaDebitos=exibirDocumentoCobrancaItemAction&contaID=<%="" + cobrancaDocumentoItemConta.getContaGeral().getContaHistorico().getId()%>' , 500, 800);" title="<%="" + Util.formatarMoedaReal(cobrancaDocumentoItemConta.getContaGeral().getContaHistorico().getValorDebitos())%>"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="contaHistorico" property="valorDebitos" formatKey="money.format" /></font></a>
															</logic:notEqual>
															
															<logic:equal name="contaHistorico" property="valorDebitos" value="0">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="contaHistorico" property="valorDebitos" formatKey="money.format" /></font>
															</logic:equal>	
															
													</logic:present>	
											</logic:present>
											
										</div>
										</td>
										<td width="10%">
										<div align="right">
											
											<logic:present name="cobrancaDocumentoItemConta" property="contaGeral">	
												<bean:define name="cobrancaDocumentoItemConta" property="contaGeral" id="contaGeral"/>
													<logic:present name="contaGeral" property="conta">
														<bean:define name="contaGeral" property="conta" id="conta"/>
															<logic:notEqual name="conta" property="valorCreditos" value="0">
																<a href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?tipoConsulta=conta&contaID=<%="" + cobrancaDocumentoItemConta.getContaGeral().getConta().getId()%>' , 500, 800);" title="<%="" + Util.formatarMoedaReal(cobrancaDocumentoItemConta.getContaGeral().getConta().getValorCreditos())%>"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="conta" property="valorCreditos" formatKey="money.format" /></font></a>
															</logic:notEqual>
															
															<logic:equal name="conta" property="valorCreditos" value="0">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="conta" property="valorCreditos" formatKey="money.format" /></font>
															</logic:equal>

													</logic:present>

													<logic:present name="contaGeral" property="contaHistorico">
															<bean:define name="contaGeral" property="contaHistorico" id="contaHistorico"/>

															<logic:notEqual name="contaHistorico" property="valorCreditos" value="0">
																<a href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?tipoConsulta=contaHistorico&contaID=<%="" + cobrancaDocumentoItemConta.getContaGeral().getContaHistorico().getId()%>' , 500, 800);" title="<%="" + Util.formatarMoedaReal(cobrancaDocumentoItemConta.getContaGeral().getContaHistorico().getValorCreditos())%>"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="contaHistorico" property="valorCreditos" formatKey="money.format" /></font></a>
															</logic:notEqual>
															
															<logic:equal name="contaHistorico" property="valorCreditos" value="0">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="contaHistorico" property="valorCreditos" formatKey="money.format" /></font>
															</logic:equal>

													</logic:present>
											</logic:present>
										
											
										</div>
										</td>
										
										<td width="10%">
										<div align="right">
										
											<logic:present name="cobrancaDocumentoItemConta" property="contaGeral">	
												<bean:define name="cobrancaDocumentoItemConta" property="contaGeral" id="contaGeral"/>
													<logic:present name="contaGeral" property="conta">
														<bean:define name="contaGeral" property="conta" id="conta"/>
															<logic:present name="conta" property="valorImposto">	
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																<bean:write name="conta" property="valorImposto" formatKey="money.format" /></font>
															</logic:present>

													</logic:present>	
													<logic:present name="contaGeral" property="contaHistorico">
															<bean:define name="contaGeral" property="contaHistorico" id="contaHistorico"/>
															<logic:present name="contaHistorico" property="valorImposto">	
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																<bean:write name="contaHistorico" property="valorImposto" formatKey="money.format" /></font>
															</logic:present>
													</logic:present>	
											</logic:present>
											
										</div>
										</td>
										
										
										<td width="10%">
										<div align="right">

											<logic:present name="cobrancaDocumentoItemConta" property="contaGeral">	
												<bean:define name="cobrancaDocumentoItemConta" property="contaGeral" id="contaGeral"/>
													<logic:present name="contaGeral" property="conta">
														<bean:define name="contaGeral" property="conta" id="conta"/>
															<logic:present name="conta" property="valorTotal">	
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="conta" property="valorTotal" formatKey="money.format" /></font>
															</logic:present>

													</logic:present>	

													<logic:present name="contaGeral" property="contaHistorico">
															<bean:define name="contaGeral" property="contaHistorico" id="contaHistorico"/>
																<logic:present name="contaHistorico" property="valorTotal">	
																	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="contaHistorico" property="valorTotal" formatKey="money.format" /></font>
																</logic:present>
													</logic:present>
											</logic:present>
										</div>
										</td>
										<td width="15%">
										<div align="center">
										
											<logic:present name="cobrancaDocumentoItemConta" property="contaGeral">	
												<bean:define name="cobrancaDocumentoItemConta" property="contaGeral" id="contaGeral"/>
													<logic:present name="contaGeral" property="conta">
														<bean:define name="contaGeral" property="conta" id="conta"/>
															<logic:present name="conta" property="debitoCreditoSituacaoAtual">	
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="conta" property="debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao"/></font>
															</logic:present>

													</logic:present>
													<logic:present name="contaGeral" property="contaHistorico">
															<bean:define name="contaGeral" property="contaHistorico" id="contaHistorico"/>
															<logic:present name="contaHistorico" property="debitoCreditoSituacaoAtual">	
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="contaHistorico" property="debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao"/></font>
															</logic:present>
													
													</logic:present>
											</logic:present>											
										</div>
										</td>
										<td width="10%">
										<div align="right">

											<logic:present name="cobrancaDocumentoItemConta" property="valorItemCobrado">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="cobrancaDocumentoItemConta" property="valorItemCobrado" formatKey="money.format" /></font>
											</logic:present>
										
											<logic:notPresent name="cobrancaDocumentoItemConta" property="valorItemCobrado">
												&nbsp;
											</logic:notPresent>
											
										</div>
										</td>
									 
									 
									 
									</tr>
									

								</logic:iterate>
								
								

								
								
								</table>

							</div>
						</td>
            		</tr>
            		
            		</logic:notEmpty>
            		
            		</table>
				</td>
			</tr>
			
			
			
      	</td>
      </tr>


      <tr>
      	<td colspan="5" height="5"></td>
      </tr>
     
      <tr>
      	<td colspan="5">
      	
      		<table width="100%" border="0">
	  		<tr> 
          		<td height="17" colspan="5"><strong>Débitos A Cobrar:</strong></td>
          	</tr>
      		<tr> 
          		<td colspan="5">
		  
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr> 
                		<td> 
					
							<table width="100%" bgcolor="#99CCFF" border="0">
                    		<tr bgcolor="#99CCFF"> 

								<td align="center" width="30%"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipo do Débito</strong></font></td>
								<td width="10%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Mês/Ano Referência</strong></font></div></td>
								<td width="10%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Mês/Ano Cobrança</strong></font></div></td>
								<td width="10%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Parcela</strong></font></div></td>
								<td width="20%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Vl. Parcela</strong></font></div></td>
								<td width="20%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Vl. Item Cobrado</strong></font></div></td>
							</tr>
                    		</table>
					
						
            		</tr>
            		
            		<logic:notEmpty name="cobrancaDocumentoHelper" property="colecaoCobrancaDocumentoItemDebitoACobrar">
            
            		<tr> 
						<td> 
					
							<div style="width: 100%; height: auto; overflow: auto;">	
							
							<%cor = "#FFFFFF";%>
							
							<table width="100%" align="center" bgcolor="#99CCFF">

								<logic:iterate name="cobrancaDocumentoHelper" property="colecaoCobrancaDocumentoItemDebitoACobrar" id="cobrancaDocumentoItemDebitoACobrar" type="CobrancaDocumentoItem">
                            
									
									
									<%	if (cor.equalsIgnoreCase("#FFFFFF")){
											cor = "#cbe5fe";%>
											<tr bgcolor="#FFFFFF">
									<%} else{
											cor = "#FFFFFF";%>
											<tr bgcolor="#cbe5fe">
									<%}%>
									 
										<td width="30%">
										<div align="center">
											<logic:present name="cobrancaDocumentoItemDebitoACobrar" property="debitoACobrarGeral">	
											
												<bean:define name="cobrancaDocumentoItemDebitoACobrar" property="debitoACobrarGeral" id="debitoACobrarGeral"/>
													<logic:present name="debitoACobrarGeral" property="debitoACobrar">
													
														<bean:define name="debitoACobrarGeral" property="debitoACobrar" id="debitoACobrar"/>
															<logic:present name="debitoACobrar" property="debitoTipo">	
																<logic:notEmpty name="cobrancaDocumentoHelper" property="cobrancaDocumento.imovel">
																	<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=${requestScope.imovel}&debitoID=<%="" + cobrancaDocumentoItemDebitoACobrar.getDebitoACobrarGeral().getDebitoACobrar().getId() %>' , 500, 800);" title="<%="" + cobrancaDocumentoItemDebitoACobrar.getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo().getDescricaoAbreviada() %>"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="debitoACobrar" property="debitoTipo.descricaoAbreviada"/></font></a>
																</logic:notEmpty>
															</logic:present>
														</logic:present>		

													<logic:present name="debitoACobrarGeral" property="debitoACobrarHistorico">
														<bean:define name="debitoACobrarGeral" property="debitoACobrarHistorico" id="debitoACobrarHistorico"/>
															<logic:present name="debitoACobrarHistorico" property="debitoTipo">	
																<logic:notEmpty name="cobrancaDocumentoHelper" property="cobrancaDocumento.imovel">
																	<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=${requestScope.imovel}&debitoID=<%="" + cobrancaDocumentoItemDebitoACobrar.getDebitoACobrarGeral().getDebitoACobrarHistorico().getId() %>' , 500, 800);" title="<%="" + cobrancaDocumentoItemDebitoACobrar.getDebitoACobrarGeral().getDebitoACobrarHistorico().getDebitoTipo().getDescricaoAbreviada() %>"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="debitoACobrarHistorico" property="debitoTipo.descricaoAbreviada"/></font></a>
																</logic:notEmpty>
															</logic:present>
													</logic:present>
											</logic:present>
										</div>
										</td>
										<td width="10%">
										<div align="center">

											<logic:present name="cobrancaDocumentoItemDebitoACobrar" property="debitoACobrarGeral">	
												<bean:define name="cobrancaDocumentoItemDebitoACobrar" property="debitoACobrarGeral" id="debitoACobrarGeral"/>
													<logic:present name="debitoACobrarGeral" property="debitoACobrar">
														<bean:define name="debitoACobrarGeral" property="debitoACobrar" id="debitoACobrar"/>
															<logic:present name="debitoACobrar" property="anoMesReferenciaDebito">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><%=""+ Util.formatarMesAnoReferencia(cobrancaDocumentoItemDebitoACobrar.getDebitoACobrarGeral().getDebitoACobrar().getAnoMesReferenciaDebito())%></font>
															</logic:present>
														
															<logic:notPresent name="debitoACobrar" property="anoMesReferenciaDebito">
																&nbsp;
															</logic:notPresent>
													</logic:present>												

													<logic:present name="debitoACobrarGeral" property="debitoACobrarHistorico">
														<bean:define name="debitoACobrarGeral" property="debitoACobrarHistorico" id="debitoACobrarHistorico"/>
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><%=""+ Util.formatarMesAnoReferencia(cobrancaDocumentoItemDebitoACobrar.getDebitoACobrarGeral().getDebitoACobrarHistorico().getAnoMesReferenciaDebito())%></font>
													</logic:present>
											</logic:present>
												
										</div>
										</td>
										<td width="10%">
										<div align="center">

											<logic:present name="cobrancaDocumentoItemDebitoACobrar" property="debitoACobrarGeral">	
												<bean:define name="cobrancaDocumentoItemDebitoACobrar" property="debitoACobrarGeral" id="debitoACobrarGeral"/>
													<logic:present name="debitoACobrarGeral" property="debitoACobrar">
														<bean:define name="debitoACobrarGeral" property="debitoACobrar" id="debitoACobrar"/>
															<logic:present name="debitoACobrar" property="anoMesCobrancaDebito">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><%=""+ Util.formatarMesAnoReferencia(cobrancaDocumentoItemDebitoACobrar.getDebitoACobrarGeral().getDebitoACobrar().getAnoMesCobrancaDebito())%></font>
															</logic:present>
														
															<logic:notPresent name="debitoACobrar" property="anoMesCobrancaDebito">
																&nbsp;
															</logic:notPresent>

													</logic:present>		

													<logic:present name="debitoACobrarGeral" property="debitoACobrarHistorico">
														<bean:define name="debitoACobrarGeral" property="debitoACobrarHistorico" id="debitoACobrarHistorico"/>

															<logic:present name="debitoACobrarHistorico" property="anoMesCobrancaDebito">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><%=""+ Util.formatarMesAnoReferencia(cobrancaDocumentoItemDebitoACobrar.getDebitoACobrarGeral().getDebitoACobrarHistorico().getAnoMesCobrancaDebito())%></font>
															</logic:present>
														
															<logic:notPresent name="debitoACobrarHistorico" property="anoMesCobrancaDebito">
																&nbsp;
															</logic:notPresent>


													</logic:present>
											</logic:present>
										</div>
										</td>
										<td width="10%">
										<div align="center">

											<logic:present name="cobrancaDocumentoItemDebitoACobrar" property="debitoACobrarGeral">	
												<bean:define name="cobrancaDocumentoItemDebitoACobrar" property="debitoACobrarGeral" id="debitoACobrarGeral"/>
													<logic:present name="debitoACobrarGeral" property="debitoACobrar">
														<bean:define name="debitoACobrarGeral" property="debitoACobrar" id="debitoACobrar"/>

															<logic:present name="debitoACobrar" property="numeroPrestacaoCobradas">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="debitoACobrar" property="numeroPrestacaoCobradas"/></font>
															</logic:present>
															
															/
															
															<logic:present name="debitoACobrar" property="debitoACobrar.numeroPrestacaoDebitoMenosBonus">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="debitoACobrar" property="numeroPrestacaoDebitoMenosBonus"/></font>
															</logic:present>
													</logic:present>		

													<logic:present name="debitoACobrarGeral" property="debitoACobrarHistorico">
														<bean:define name="debitoACobrarGeral" property="debitoACobrarHistorico" id="debitoACobrarHistorico"/>

															<logic:present name="debitoACobrarHistorico" property="numeroPrestacaoCobradas">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="debitoACobrarHistorico" property="numeroPrestacaoCobradas"/></font>
															</logic:present>
															
															/
															
															<logic:present name="debitoACobrarHistorico" property="debitoACobrar.numeroPrestacaoDebitoMenosBonus">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="debitoACobrarHistorico" property="numeroPrestacaoDebitoMenosBonus"/></font>
															</logic:present>

													</logic:present>
											</logic:present>
											
										</div>
										</td>
										<td width="20%">
										<div align="right">
										
											<logic:present name="cobrancaDocumentoItemDebitoACobrar" property="debitoACobrarGeral">	
												<bean:define name="cobrancaDocumentoItemDebitoACobrar" property="debitoACobrarGeral" id="debitoACobrarGeral"/>
													<logic:present name="debitoACobrarGeral" property="debitoACobrar">
														<bean:define name="debitoACobrarGeral" property="debitoACobrar" id="debitoACobrar"/>

															<logic:present name="debitoACobrar" property="valorTotalComBonus">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="debitoACobrar" property="valorTotalComBonus" formatKey="money.format" /></font>
															</logic:present>
														
															<logic:notPresent name="debitoACobrar" property="valorTotalComBonus">
																&nbsp;
															</logic:notPresent>
													</logic:present>		

													<logic:present name="debitoACobrarGeral" property="debitoACobrarHistorico">
														<bean:define name="debitoACobrarGeral" property="debitoACobrarHistorico" id="debitoACobrarHistorico"/>


															<logic:present name="debitoACobrarHistorico" property="valorTotalComBonus">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="debitoACobrarHistorico" property="valorTotalComBonus" formatKey="money.format" /></font>
															</logic:present>
														
															<logic:notPresent name="debitoACobrarHistorico" property="valorTotalComBonus">
																&nbsp;
															</logic:notPresent>

													</logic:present>
											</logic:present>
											
											
										</div>
										</td>
										<td width="20%">
										<div align="right">
										
											<logic:present name="cobrancaDocumentoItemDebitoACobrar" property="valorItemCobrado">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="cobrancaDocumentoItemDebitoACobrar" property="valorItemCobrado" formatKey="money.format" /></font>
											</logic:present>
										
											<logic:notPresent name="cobrancaDocumentoItemConta" property="valorItemCobrado">
												&nbsp;
											</logic:notPresent>
											
										</div>
										</td>
									</tr>
									

								</logic:iterate>
								
								</table>

							</div>
						</td>
            		</tr>
            		
            		</logic:notEmpty>
            		
            		</table>
				</td>
			</tr>
			
      <tr>
      	<td colspan="5" height="5"></td>
      </tr>
      <tr>
      	<td colspan="5">
      	
      		<table width="100%" border="0">
	  		<tr> 
          		<td height="17" colspan="5"><strong>Guias de Pagamento:</strong></td>
          	</tr>
      		<tr> 
          		<td colspan="5">
		  
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr> 
                		<td> 
					
							<table width="100%" bgcolor="#99CCFF" border="0">
                    		<tr bgcolor="#99CCFF"> 

								<td align="center" width="30%"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipo do Débito</strong></font></td>
								<td width="15%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Emissão</strong></font></div></td>
								<td width="15%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Vencimento</strong></font></div></td>
								<td width="20%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Vl. Guia de Pagamento</strong></font></div></td>
								<td width="20%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Vl. Item Cobrado</strong></font></div></td>
							</tr>
                    		</table>
					
						
            		</tr>
            		
            		<logic:notEmpty name="cobrancaDocumentoHelper" property="colecaoCobrancaDocumentoItemGuiaPagamento">
            
            		<tr> 
						<td> 
					
							<div style="width: 100%; height: auto; overflow: auto;">	
							
							<%cor = "#FFFFFF";%>
							
							<table width="100%" align="center" bgcolor="#99CCFF">

								<logic:iterate name="cobrancaDocumentoHelper" property="colecaoCobrancaDocumentoItemGuiaPagamento" id="cobrancaDocumentoItemGuiaPagamento" type="CobrancaDocumentoItem">
                            
									
									
									<%	if (cor.equalsIgnoreCase("#FFFFFF")){
											cor = "#cbe5fe";%>
											<tr bgcolor="#FFFFFF">
									<%} else{
											cor = "#FFFFFF";%>
											<tr bgcolor="#cbe5fe">
									<%}%>
									 
										<td width="30%">
										<div align="center">

											<logic:present name="cobrancaDocumentoItemConta" property="guiaPagamentoGeral">	
												<bean:define name="cobrancaDocumentoItemConta" property="guiaPagamentoGeral" id="guiaPagamentoGeral"/>
													<logic:present name="guiaPagamentoGeral" property="guiaPagamento">											
														<bean:define name="guiaPagamentoGeral" property="guiaPagamento" id="guiaPagamento"/>
															<logic:present name="guiaPagamento" property="debitoTipo">	
																<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + cobrancaDocumentoItemGuiaPagamento.getGuiaPagamentoGeral().getGuiaPagamento().getId() %>')" title="<%="" + cobrancaDocumentoItemGuiaPagamento.getGuiaPagamentoGeral().getGuiaPagamento().getDebitoTipo().getDescricao() %>"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="guiaPagamento" property="debitoTipo.descricao"/></font></a>
															</logic:present>
													</logic:present>		
													
													<logic:present name="guiaPagamentoGeral" property="guiaPagamentoHistorico">
														<bean:define name="guiaPagamentoGeral" property="guiaPagamentoHistorico" id="guiaPagamentoHistorico"/>
															<logic:present name="guiaPagamentoHistorico" property="debitoTipo">	
																<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + cobrancaDocumentoItemGuiaPagamento.getGuiaPagamentoGeral().getGuiaPagamentoHistorico().getId() %>')" title="<%="" + cobrancaDocumentoItemGuiaPagamento.getGuiaPagamentoGeral().getGuiaPagamentoHistorico().getDebitoTipo().getDescricao() %>"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="guiaPagamentoHistorico" property="debitoTipo.descricao"/></font></a>
															</logic:present>

													</logic:present>
											</logic:present>
										
										</div>
										</td>
										<td width="15%">
										<div align="center">
					
											<logic:present name="cobrancaDocumentoItemConta" property="guiaPagamentoGeral">	
												<bean:define name="cobrancaDocumentoItemConta" property="guiaPagamentoGeral" id="guiaPagamentoGeral"/>
													<logic:present name="guiaPagamentoGeral" property="guiaPagamento">											
														<bean:define name="guiaPagamentoGeral" property="guiaPagamento" id="guiaPagamento"/>
															<logic:present name="guiaPagamento" property="dataEmissao">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="guiaPagamento" property="dataEmissao" formatKey="date.format"/></font>
															</logic:present>
													</logic:present>		

													<logic:present name="guiaPagamentoGeral" property="guiaPagamentoHistorico">
														<bean:define name="guiaPagamentoGeral" property="guiaPagamentoHistorico" id="guiaPagamentoHistorico"/>
															<logic:present name="guiaPagamentoHistorico" property="dataEmissao">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="guiaPagamentoHistorico" property="dataEmissao" formatKey="date.format"/></font>
															</logic:present>
													</logic:present>
											</logic:present>
											
										</div>
										</td>
										<td width="15%">
										<div align="center">
											<logic:present name="cobrancaDocumentoItemConta" property="guiaPagamentoGeral">	
												<bean:define name="cobrancaDocumentoItemConta" property="guiaPagamentoGeral" id="guiaPagamentoGeral"/>
													<logic:present name="guiaPagamentoGeral" property="guiaPagamento">											
														<bean:define name="guiaPagamentoGeral" property="guiaPagamento" id="guiaPagamento"/>
															<logic:present name="guiaPagamento" property="dataVencimento">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="guiaPagamento" property="dataVencimento" formatKey="date.format"/></font>
															</logic:present>

													</logic:present>		

													<logic:present name="guiaPagamentoGeral" property="guiaPagamentoHistorico">
														<bean:define name="guiaPagamentoGeral" property="guiaPagamentoHistorico" id="guiaPagamentoHistorico"/>
															<logic:present name="guiaPagamentoHistorico" property="dataVencimento">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="guiaPagamentoHistorico" property="dataVencimento" formatKey="date.format"/></font>
															</logic:present>
													</logic:present>
											</logic:present>
											
										</div>
										</td>
										<td width="20%">
										<div align="right">

											<logic:present name="cobrancaDocumentoItemConta" property="guiaPagamentoGeral">	
												<bean:define name="cobrancaDocumentoItemConta" property="guiaPagamentoGeral" id="guiaPagamentoGeral"/>
													<logic:present name="guiaPagamentoGeral" property="guiaPagamento">											
														<bean:define name="guiaPagamentoGeral" property="guiaPagamento" id="guiaPagamento"/>
										
														<logic:present name="guiaPagamento" property="valorDebito">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="guiaPagamento" property="valorDebito" formatKey="money.format" /></font>
														</logic:present>
										
													</logic:present>		

													<logic:present name="guiaPagamentoGeral" property="guiaPagamentoHistorico">
														<bean:define name="guiaPagamentoGeral" property="guiaPagamentoHistorico" id="guiaPagamentoHistorico"/>
															<logic:present name="guiaPagamentoHistorico" property="valorDebito">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="guiaPagamentoHistorico" property="valorDebito" formatKey="money.format" /></font>
															</logic:present>
													</logic:present>
											</logic:present>
											
										</div>
										</td>
										<td width="20%">
										<div align="right">

											<logic:present name="cobrancaDocumentoItemGuiaPagamento" property="valorItemCobrado">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="cobrancaDocumentoItemGuiaPagamento" property="valorItemCobrado" formatKey="money.format" /></font>
											</logic:present>

											<logic:notPresent name="cobrancaDocumentoItemGuiaPagamento" property="valorItemCobrado">
												&nbsp;
											</logic:notPresent>
											
											
										</div>
										</td>
									</tr>
									

								</logic:iterate>
								
								</table>

							</div>
						</td>
            		</tr>
            		
            		</logic:notEmpty>
            		
            		</table>
				</td>
			</tr>
			
			<tr>
      	<td colspan="5">
      	
      		<table width="100%" border="0">
	  		<tr> 
          		<td height="17" colspan="5"><strong>Créditos A Realizar:</strong></td>
          	</tr>
      		<tr> 
          		<td colspan="5">
		  
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr> 
                		<td> 
					
							<table width="100%" bgcolor="#99CCFF" border="0">
                    		<tr bgcolor="#99CCFF"> 

								<td align="center" width="30%"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipo do Crédito</strong></font></td>
								<td width="10%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Mês/Ano Referência</strong></font></div></td>
								<td width="10%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Mês/Ano Cobrança</strong></font></div></td>
								<td width="10%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Parcela</strong></font></div></td>
								<td width="20%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Vl. Parcela</strong></font></div></td>
								<td width="20%"><div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Vl. Item Cobrado</strong></font></div></td>
							</tr>
                    		</table>
					
						
            		</tr>
            		
            		<logic:notEmpty name="cobrancaDocumentoHelper" property="colecaoCobrancaDocumentoItemCreditoARealizar">
            
            		<tr> 
						<td> 
					
							<div style="width: 100%; height: auto; overflow: auto;">	
							
							<%cor = "#FFFFFF";%>
							
							<table width="100%" align="center" bgcolor="#99CCFF">

								<logic:iterate name="cobrancaDocumentoHelper" property="colecaoCobrancaDocumentoItemCreditoARealizar" id="cobrancaDocumentoItemCreditoARealizar" type="CobrancaDocumentoItem">
                            
									
									
									<%	if (cor.equalsIgnoreCase("#FFFFFF")){
											cor = "#cbe5fe";%>
											<tr bgcolor="#FFFFFF">
									<%} else{
											cor = "#FFFFFF";%>
											<tr bgcolor="#cbe5fe">
									<%}%>
									 
										<td width="30%">
										<div align="center">
											<logic:present name="cobrancaDocumentoItemCreditoARealizar" property="creditoARealizarGeral">	
											
												<bean:define name="cobrancaDocumentoItemCreditoARealizar" property="creditoARealizarGeral" id="creditoARealizarGeral"/>
													<logic:present name="creditoARealizarGeral" property="creditoARealizar">
													
														<bean:define name="creditoARealizarGeral" property="creditoARealizar" id="creditoARealizar"/>
															<logic:present name="creditoARealizar" property="creditoTipo">	
																<logic:notEmpty name="cobrancaDocumentoHelper" property="cobrancaDocumento.imovel">
																	<a href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=${requestScope.imovel}&creditoID=<%="" + cobrancaDocumentoItemCreditoARealizar.getCreditoARealizarGeral().getCreditoARealizar().getId() %>' , 500, 800);" title="<%="" + cobrancaDocumentoItemCreditoARealizar.getCreditoARealizarGeral().getCreditoARealizar().getCreditoTipo().getDescricaoAbreviada() %>"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="creditoARealizar" property="creditoTipo.descricaoAbreviada"/></font></a>
																</logic:notEmpty>
															</logic:present>
														</logic:present>		

													<logic:present name="creditoARealizarGeral" property="creditoARealizarHistorico">
														<bean:define name="creditoARealizarGeral" property="creditoARealizarHistorico" id="creditoARealizarHistorico"/>
															<logic:present name="creditoARealizarHistorico" property="creditoTipo">	
																<logic:notEmpty name="cobrancaDocumentoHelper" property="cobrancaDocumento.imovel">
																	<a href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=${requestScope.imovel}&creditoHistoricoID=<%="" + cobrancaDocumentoItemCreditoARealizar.getCreditoARealizarGeral().getCreditoARealizarHistorico().getId() %>' , 500, 800);" title="<%="" + cobrancaDocumentoItemCreditoARealizar.getCreditoARealizarGeral().getCreditoARealizarHistorico().getCreditoTipo().getDescricaoAbreviada() %>"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="creditoARealizarHistorico" property="creditoTipo.descricaoAbreviada"/></font></a>
																</logic:notEmpty>
															</logic:present>
													</logic:present>
											</logic:present>
										</div>
										</td>
										<td width="10%">
										<div align="center">

											<logic:present name="cobrancaDocumentoItemCreditoARealizar" property="creditoARealizarGeral">	
											
												<bean:define name="cobrancaDocumentoItemCreditoARealizar" property="creditoARealizarGeral" id="creditoARealizarGeral"/>
													<logic:present name="creditoARealizarGeral" property="creditoARealizar">
														<bean:define name="creditoARealizarGeral" property="creditoARealizar" id="creditoARealizar"/>
															<logic:present name="creditoARealizar" property="anoMesReferenciaCredito">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><%=""+ Util.formatarMesAnoReferencia(cobrancaDocumentoItemCreditoARealizar.getCreditoARealizarGeral().getCreditoARealizar().getAnoMesReferenciaCredito())%></font>
															</logic:present>
													</logic:present>												

													<logic:present name="creditoARealizarGeral" property="creditoARealizarHistorico">
														<bean:define name="creditoARealizarGeral" property="creditoARealizarHistorico" id="creditoARealizarHistorico"/>
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><%=""+ Util.formatarMesAnoReferencia(cobrancaDocumentoItemCreditoARealizar.getCreditoARealizarGeral().getCreditoARealizarHistorico().getAnoMesReferenciaCredito())%></font>
													</logic:present>
											</logic:present>
												
										</div>
										</td>
										<td width="10%">
										<div align="center">

											<logic:present name="cobrancaDocumentoItemCreditoARealizar" property="creditoARealizarGeral">	
												<bean:define name="cobrancaDocumentoItemCreditoARealizar" property="creditoARealizarGeral" id="creditoARealizarGeral"/>
													<logic:present name="creditoARealizarGeral" property="creditoARealizar">
														<bean:define name="creditoARealizarGeral" property="creditoARealizar" id="creditoARealizar"/>
															<logic:present name="creditoARealizar" property="anoMesCobrancaCredito">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><%=""+ Util.formatarMesAnoReferencia(cobrancaDocumentoItemCreditoARealizar.getCreditoARealizarGeral().getCreditoARealizar().getAnoMesCobrancaCredito())%></font>
															</logic:present>
													</logic:present>		

													<logic:present name="creditoARealizarGeral" property="creditoARealizarHistorico">
														<bean:define name="creditoARealizarGeral" property="creditoARealizarHistorico" id="creditoARealizarHistorico"/>

															<logic:present name="creditoARealizarHistorico" property="anoMesCobrancaCredito">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><%=""+ Util.formatarMesAnoReferencia(cobrancaDocumentoItemCreditoARealizar.getCreditoARealizarGeral().getCreditoARealizarHistorico().getAnoMesCobrancaCredito())%></font>
															</logic:present>
													</logic:present>
											</logic:present>
										</div>
										</td>
										<td width="10%">
										<div align="center">

											<logic:present name="cobrancaDocumentoItemCreditoARealizar" property="creditoARealizarGeral">	
												<bean:define name="cobrancaDocumentoItemCreditoARealizar" property="creditoARealizarGeral" id="creditoARealizarGeral"/>
													<logic:present name="creditoARealizarGeral" property="creditoARealizar">
														<bean:define name="creditoARealizarGeral" property="creditoARealizar" id="creditoARealizar"/>

															<logic:present name="creditoARealizar" property="numeroPrestacaoRealizada">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="creditoARealizar" property="numeroPrestacaoRealizada"/></font>
															</logic:present>
															
															/
															
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="creditoARealizar" property="numeroPrestacaoCreditoMenosBonus"/></font>
													</logic:present>		

													<logic:present name="creditoARealizarGeral" property="creditoARealizarHistorico">
														<bean:define name="creditoARealizarGeral" property="creditoARealizarHistorico" id="creditoARealizarHistorico"/>

															<logic:present name="creditoARealizarHistorico" property="numeroPrestacaoRealizada">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="creditoARealizarHistorico" property="numeroPrestacaoRealizada"/></font>
															</logic:present>
															
															/
															
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="creditoARealizarHistorico" property="numeroPrestacaoCreditoMenosBonus"/></font>

													</logic:present>
											</logic:present>
											
										</div>
										</td>
										<td width="20%">
										<div align="right">
										
											<logic:present name="cobrancaDocumentoItemCreditoARealizar" property="creditoARealizarGeral">	
												<bean:define name="cobrancaDocumentoItemCreditoARealizar" property="creditoARealizarGeral" id="creditoARealizarGeral"/>
													<logic:present name="creditoARealizarGeral" property="creditoARealizar">
														<bean:define name="creditoARealizarGeral" property="creditoARealizar" id="creditoARealizar"/>

															<logic:present name="creditoARealizar" property="valorTotalComBonus">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="creditoARealizar" property="valorTotalComBonus" formatKey="money.format" /></font>
															</logic:present>
													</logic:present>		

													<logic:present name="creditoARealizarGeral" property="creditoARealizarHistorico">
														<bean:define name="creditoARealizarGeral" property="creditoARealizarHistorico" id="creditoARealizarHistorico"/>
															<logic:present name="creditoARealizarHistorico" property="valorTotalComBonus">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="creditoARealizarHistorico" property="valorTotalComBonus" formatKey="money.format" /></font>
															</logic:present>
													</logic:present>
											</logic:present>
											
											
										</div>
										</td>
										<td width="20%">
										<div align="right">
											<logic:present name="cobrancaDocumentoItemCreditoARealizar" property="valorItemCobrado">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="cobrancaDocumentoItemCreditoARealizar" property="valorItemCobrado" formatKey="money.format" /></font>
											</logic:present>
										</div>
										</td>
									</tr>
									

								</logic:iterate>
								
								</table>

							</div>
						</td>
            		</tr>
            		
            		</logic:notEmpty>
            		
            		</table>
				</td>
			</tr>
			
			</table>
			
      	</td>
      </tr>			
			
			</table>
			
      	</td>
      </tr>
      
      
      
      <tr>      
      <td align="right"> 
	  <logic:notEmpty name="cobrancaDocumentoHelper" property="idOrdemServico">
		<input type="button" onclick="window.location.href='exibirConsultarDadosOrdemServicoPopupAction.do?voltar=s&numeroOS=${cobrancaDocumentoHelper.idOrdemServico}'"
		class="bottonRightCol" value="Ordem de Serviço">
 	  </logic:notEmpty>

      <input name="Submit23" class="bottonRightCol" value="Fechar"
						type="button"
						onclick="javascript:window.close();">
      </td>
      </tr>
	  <!-- ============================================================================================================================== -->
	  </table>
	   </td>
  		</tr>
      </table>
      
      <p>&nbsp;</p>
	<!-- </td>
  </tr>-->
<!--</table>-->

</html:form>

</body>
</html:html>

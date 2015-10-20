<%@page import="gcom.faturamento.debito.DebitoACobrar"%>
<%@page import="gcom.util.Util"%>
<%@page import="java.util.Collection"%>
<%@page import="gcom.util.ConstantesSistema"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<table width="100%" align="center" bgcolor="#90c7fc" border="0">
		<%String cor = "#cbe5fe";%>
		<%cor = "#cbe5fe";%>
		<tr bordercolor="#79bbfd">
			<td colspan="9" align="center" bgcolor="#79bbfd">
			<strong>Contas</strong>
			</td>
		</tr>
		<logic:notEmpty name="colecaoContaValores" scope="session">
			<%if (((Collection) session.getAttribute("colecaoContaValores"))
			.size() <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
			<tr bordercolor="#000000">
				<td width="20%" bgcolor="#90c7fc" align="center">
				<div class="style9"><font color="#000000" style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano</strong>
				</font></div>
				</td>
				<td width="7%" bgcolor="#90c7fc" align="center">
				<div class="style9"><font color="#000000" style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <strong>Vencimento</strong>
				</font></div>
				</td>
				<td width="10%" bgcolor="#90c7fc" align="center">
				<div class="style9"><font color="#000000" style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor de
				&Aacute;gua </strong> </font></div>
				</td>
				<td width="9%" bgcolor="#90c7fc" align="center">
				<div class="style9"><font color="#000000" style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor de
				Esgoto</strong> </font></div>
				</td>
				<td width="8%" bgcolor="#90c7fc" align="center">
				<div class="style9"><font color="#000000" style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor dos
				D&eacute;bitos</strong> </font></div>
				</td>
				<td width="8%" bgcolor="#90c7fc" align="center">
				<div class="style9"><font color="#000000" style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor dos
				Creditos</strong> </font></div>
				</td>
				
				<td width="10%" bgcolor="#90c7fc" align="center">
				  <div class="style9">
				    <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
				      <strong>Valor dos Impostos</strong> 
				    </font>
				  </div>
				</td>	
				<td width="10%" bgcolor="#90c7fc" align="center">
				<div class="style9"><font color="#000000" style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor da
				Conta</strong> </font></div>
				</td>
				<td width="10%" bgcolor="#90c7fc" align="center">
				<div class="style9"><font color="#000000" style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <strong>Acr&eacute;sc.
				Impont.</strong><strong></strong> </font></div>
				</td>
				<td width="10%" bgcolor="#90c7fc" align="center">
				<div class="style9"><font color="#000000" style="font-size:9px"
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
						
						<td align="left">
							<logic:notEmpty name="contavaloreshelper" property="conta">											
								<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="center">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<a href="javascript:abrirPopup('exibirConsultarContaAction.do?caminhoRetornoTelaConsulta=exibirConsultarRegistroAtendimentoPendentesImovelAction&contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
											<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
											<bean:write name="conta" property="formatarAnoMesParaMesAno" /></a> 
										</font>
									</div>
								</logic:empty>

								<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="center">
										<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<a href="javascript:abrirPopup('exibirConsultarContaAction.do?caminhoRetornoTelaConsulta=exibirConsultarRegistroAtendimentoPendentesImovelAction&contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
											<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
											<font color="#ff0000"><bean:write name="conta" property="formatarAnoMesParaMesAno" /></font></a> 
										</font>
									</div>
								</logic:notEmpty>
							</logic:notEmpty>
						</td>
						<td align="left">
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
						<td align="right">
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
						<td align="right">
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
						<td align="right">
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
						
						<td align="right">
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
						<td align="right">
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
						<td align="right">
							<logic:notEmpty name="contavaloreshelper" property="conta">
								<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="right">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
											<bean:write name="conta" property="valorTotal" formatKey="money.format" />
										</font>
									</div>
								</logic:empty>

								<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="right">
										<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
											<bean:write name="conta" property="valorTotal" formatKey="money.format" />
										</font>
									</div>
								</logic:notEmpty>
							</logic:notEmpty>
						</td>
						<td align="right">
							<logic:notEmpty name="contavaloreshelper" property="conta">
								<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="right" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<logic:notEqual name="contavaloreshelper" property="valorTotalContaValores" value="0">
												<a href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?caminhoRetornoTelaConsultaAcrescimos=exibirConsultarRegistroAtendimentoPendentesImovelAction&multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
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
												<a href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?caminhoRetornoTelaConsultaAcrescimos=exibirConsultarRegistroAtendimentoPendentesImovelAction&multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
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
						<td align="left">
							<logic:notEmpty name="contavaloreshelper" property="conta">
								<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="left" title="${contavaloreshelper.conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}">
									<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
										<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
										<bean:define name="conta" property="debitoCreditoSituacaoAtual" id="debitoCreditoSituacaoAtual" /> 
										<bean:write name="debitoCreditoSituacaoAtual" property="descricaoAbreviada" /> 
									</font>
								</div>
								</logic:empty>

								<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="left" title="${contavaloreshelper.conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}" >
									<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
										<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
										<bean:define name="conta" property="debitoCreditoSituacaoAtual" id="debitoCreditoSituacaoAtual" /> 
										<bean:write name="debitoCreditoSituacaoAtual" property="descricaoAbreviada" /> 
									</font>
								</div>
								</logic:notEmpty>
							</logic:notEmpty>
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
							<div class="style9" align="center">
								<font color="#000000" style="font-size:9px"
							face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
						</font></div>
						</td>
						<td align="left">
						
							<%=((Collection) session.getAttribute("colecaoContaValores")).size()%>
								&nbsp;
								doc(s)
						</td>
						<td align="right">
						<div align="right"><font color="#000000" style="font-size:9px"
							face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorAgua")%>
						</font></div>
						</td>
						<td align="right">
						<div align="right"><font color="#000000" style="font-size:9px"
							face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorEsgoto")%>
						</font></div>
						</td>
						<td align="right">
						<div align="right"><font color="#000000" style="font-size:9px"
							face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorDebito")%>
						</font></div>
						</td>
						<td align="right">
						<div align="right"><font color="#000000" style="font-size:9px"
							face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorCredito")%>
						</font></div>
						</td>
						<td align="right">
						<div align="right"><font color="#000000" style="font-size:9px"
							face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorImposto")%>
						</font></div>
						</td>
						<td align="right">
						<div align="right"><font color="#000000" style="font-size:9px"
							face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorConta")%>
						</font></div>
						</td>
						<td align="right">
						<div align="right" class="style9"><font color="#000000"
							style="font-size:9px"
							face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorAcrescimo")%>
						</font></div>
						</td>
						<td align="left">
						<div align="left"><font color="#000000" style="font-size:9px"
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
				<td width="8%" bgcolor="#90c7fc">
				<div align="center" class="style9"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor de
				&Aacute;gua </strong> </font></div>
				</td>
				<td width="8%" bgcolor="#90c7fc">
				<div align="center" class="style9"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor de
				Esgoto</strong> </font></div>
				</td>
				<td width="8%" bgcolor="#90c7fc">
				<div align="center" class="style9"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor dos
				<br>
				D&eacute;bitos</strong> </font></div>
				</td>
				<td width="10%" bgcolor="#90c7fc">
				<div align="center" class="style9"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor dos
				Creditos</strong> </font></div>
				</td>
				<td width="10%" bgcolor="#90c7fc">
				  <div align="center" class="style9">
				    <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
				      <strong>Valor dos	Impostos</strong> 
				    </font>
				  </div>
				</td>
				<td width="8%" bgcolor="#90c7fc">
				<div align="center" class="style9"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor da
				Conta</strong> </font></div>
				</td>
				<td width="7%" bgcolor="#90c7fc">
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
								<td width="9%" align="left">
									<div align="left" class="style9">
									
									<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<font color="#000000"
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> 
										<logic:notEmpty
											name="contavaloreshelper" property="conta">
											<a
												href="javascript:abrirPopup('exibirConsultarContaAction.do?caminhoRetornoTelaConsulta=exibirConsultarRegistroAtendimentoPendentesImovelAction&contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
											<bean:define name="contavaloreshelper" property="conta"
												id="conta" /> <bean:write name="conta"
												property="formatarAnoMesParaMesAno" /></a>
										</logic:notEmpty> </font>
									</logic:empty>

									<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<font color="#ff0000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif">
										 <logic:notEmpty name="contavaloreshelper" property="conta">
										<a
											href="javascript:abrirPopup('exibirConsultarContaAction.do?caminhoRetornoTelaConsulta=exibirConsultarRegistroAtendimentoPendentesImovelAction&contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
										<bean:define name="contavaloreshelper" property="conta"
											id="conta" /> <font color="#ff0000"><bean:write name="conta"
											property="formatarAnoMesParaMesAno" /></font> </a>
										</logic:notEmpty> </font>
									</logic:notEmpty>
									</div>
								</td>
								
								<td width="12%" align="left">
									<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="left" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="contavaloreshelper" property="conta">
										<bean:define name="contavaloreshelper" property="conta"
											id="conta" />
										<bean:write name="conta" property="dataVencimentoConta"
											formatKey="date.format" />
									</logic:notEmpty> </font></div>
									</logic:empty>

									<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="left" class="style9"><font color="#ff0000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="contavaloreshelper" property="conta">
										<bean:define name="contavaloreshelper" property="conta"
											id="conta" />
										<bean:write name="conta" property="dataVencimentoConta"
											formatKey="date.format" />
										</logic:notEmpty> </font></div>
									</logic:notEmpty>
								</td>
								
								<td width="8%" align="right">
																				
									<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="right" class="style9"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
												name="contavaloreshelper" property="conta">
												<bean:define name="contavaloreshelper" property="conta"
													id="conta" />
												<bean:write name="conta" property="valorAgua"
													formatKey="money.format" />
											</logic:notEmpty> </font></div>
									</logic:empty>

									<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="right" class="style9"><font color="#ff0000"
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
											name="contavaloreshelper" property="conta">
											<bean:define name="contavaloreshelper" property="conta"
												id="conta" />
											<bean:write name="conta" property="valorAgua"
												formatKey="money.format" />
										</logic:notEmpty> </font></div>
									</logic:notEmpty>
								</td>
								
								<td width="8%" align="right">
									<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="contavaloreshelper" property="conta">
										<bean:define name="contavaloreshelper" property="conta"
											id="conta" />
										<bean:write name="conta" property="valorEsgoto"
											formatKey="money.format" />
									</logic:notEmpty> </font></div>
									</logic:empty>

									<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="right" class="style9"><font color="#ff0000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="contavaloreshelper" property="conta">
										<bean:define name="contavaloreshelper" property="conta"
											id="conta" />
										<bean:write name="conta" property="valorEsgoto"
											formatKey="money.format" />
									</logic:notEmpty> </font></div>
									</logic:notEmpty>
								</td>
								
								<td width="8%" align="right">
																				
								<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="contavaloreshelper" property="conta">
										<logic:notEqual name="contavaloreshelper"
											property="conta.debitos" value="0">
											<a
												href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
											<bean:write name="contavaloreshelper"
												property="conta.debitos" formatKey="money.format" /> </a>
										</logic:notEqual>
										<logic:equal name="contavaloreshelper"
											property="conta.debitos" value="0">
											<bean:write name="contavaloreshelper"
												property="conta.debitos" formatKey="money.format" />
										</logic:equal>
									</logic:notEmpty> </font></div>
								</logic:empty>

								<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="right" class="style9"><font color="#ff0000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="contavaloreshelper" property="conta">
										<logic:notEqual name="contavaloreshelper"
											property="conta.debitos" value="0">
											<a
												href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
											<font color="#ff0000"><bean:write name="contavaloreshelper"
												property="conta.debitos" formatKey="money.format" /> </font></a>
										</logic:notEqual>
										<logic:equal name="contavaloreshelper"
											property="conta.debitos" value="0">
											<bean:write name="contavaloreshelper"
												property="conta.debitos" formatKey="money.format" />
										</logic:equal>
									</logic:notEmpty> </font></div>
								</logic:notEmpty>
								</td>
								<td width="10%" align="right">
																				
								<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
								<div align="right" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
									name="contavaloreshelper" property="conta">
									<logic:notEqual name="contavaloreshelper"
										property="conta.valorCreditos" value="0">
										<a
											href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?contaID=<bean:define name="contavaloreshelper"	property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
										<bean:write name="contavaloreshelper"
											property="conta.valorCreditos" formatKey="money.format" />
										</a>
									</logic:notEqual>
									<logic:equal name="contavaloreshelper"
										property="conta.valorCreditos" value="0">
										<bean:write name="contavaloreshelper"
											property="conta.valorCreditos" formatKey="money.format" />
									</logic:equal>
								</logic:notEmpty> </font></div>
								</logic:empty>

								<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="right" class="style9"><font color="#ff0000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
									name="contavaloreshelper" property="conta">
									<logic:notEqual name="contavaloreshelper"
										property="conta.valorCreditos" value="0">
										<a
											href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?contaID=<bean:define name="contavaloreshelper"	property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
										<font color="#ff0000"><bean:write name="contavaloreshelper"
											property="conta.valorCreditos" formatKey="money.format" /></font>
										</a>
									</logic:notEqual>
									<logic:equal name="contavaloreshelper"
										property="conta.valorCreditos" value="0">
										<bean:write name="contavaloreshelper"
											property="conta.valorCreditos" formatKey="money.format" />
									</logic:equal>
								</logic:notEmpty> </font></div>
								</logic:notEmpty>
								</td>
								<td width="10%" align="right">
																				
									<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="right" class="style9">
										  <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
										    <logic:notEmpty	name="contavaloreshelper" property="conta">
												<bean:define name="contavaloreshelper" property="conta"	id="conta" />
												<bean:write name="conta" property="valorImposto" formatKey="money.format" />
											</logic:notEmpty> 
										  </font>
										</div>
									</logic:empty>

									<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="right" class="style9">
										  <font color="#ff0000"	style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
										    <logic:notEmpty	name="contavaloreshelper" property="conta">
											  <bean:define name="contavaloreshelper" property="conta"	id="conta" />
											  <bean:write name="conta" property="valorImposto" formatKey="money.format" />
										    </logic:notEmpty> 
										  </font>
										</div>
									</logic:notEmpty>
								</td>
								<td width="8%" align="right">
																				
									<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="contavaloreshelper" property="conta">
										<bean:define name="contavaloreshelper" property="conta"
											id="conta" />
										<bean:write name="conta" property="valorTotal"
											formatKey="money.format" />
									</logic:notEmpty> </font></div>
									</logic:empty>

									<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
										<div align="right" class="style9"><font color="#ff0000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="contavaloreshelper" property="conta">
										<bean:define name="contavaloreshelper" property="conta"
											id="conta" />
										<bean:write name="conta" property="valorTotal"
											formatKey="money.format" />
									</logic:notEmpty> </font></div>
									</logic:notEmpty>
								</td>
								
								<td width="8%" align="right">
								
																				
								<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="right" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEqual
									name="contavaloreshelper" property="valorTotalContaValores"
									value="0">
									<a
										href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?caminhoRetornoTelaConsultaAcrescimos=exibirConsultarRegistroAtendimentoPendentesImovelAction&multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
									<bean:write name="contavaloreshelper"
										property="valorTotalContaValores" formatKey="money.format" />
									</a>
								</logic:notEqual> <logic:equal name="contavaloreshelper"
									property="valorTotalContaValores" value="0">
									<bean:write name="contavaloreshelper"
										property="valorTotalContaValores" formatKey="money.format" />
								</logic:equal> </font></div>
								</logic:empty>

								<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="right" class="style9"><font color="#ff0000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEqual
									name="contavaloreshelper" property="valorTotalContaValores"
									value="0">
									<a
										href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?caminhoRetornoTelaConsultaAcrescimos=exibirConsultarRegistroAtendimentoPendentesImovelAction&multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
									<font color="#ff0000"><bean:write name="contavaloreshelper"
										property="valorTotalContaValores" formatKey="money.format" /></font>
									</a>
								</logic:notEqual> <logic:equal name="contavaloreshelper"
									property="valorTotalContaValores" value="0">
									<bean:write name="contavaloreshelper"
										property="valorTotalContaValores" formatKey="money.format" />
								</logic:equal> </font></div>
								</logic:notEmpty>							
								</td>
								<td width="7%" align="left">
																				
								<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="left" class="style9" title="${contavaloreshelper.conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
									name="contavaloreshelper" property="conta">
									<bean:define name="contavaloreshelper" property="conta"
										id="conta" />
									<bean:define name="conta"
										property="debitoCreditoSituacaoAtual"
										id="debitoCreditoSituacaoAtual" />
									<bean:write name="debitoCreditoSituacaoAtual"
										property="descricaoAbreviada" />
								</logic:notEmpty> </font></div>
								</logic:empty>

								<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
									<div align="left" class="style9"><font color="#ff0000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif" title="${contavaloreshelper.conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}" > <logic:notEmpty
									name="contavaloreshelper" property="conta">
									<bean:define name="contavaloreshelper" property="conta"
										id="conta" />
									<bean:define name="conta"
										property="debitoCreditoSituacaoAtual"
										id="debitoCreditoSituacaoAtual" />
									<bean:write name="debitoCreditoSituacaoAtual"
										property="descricaoAbreviada" />
								</logic:notEmpty> </font></div>
								</logic:notEmpty>
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
								<div class="style9" align="center"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
								</font></div>
								</td>
								<td align="left">
								<%=((Collection) session.getAttribute("colecaoContaValores")).size()%>
								&nbsp;
								doc(s)
								</td>
								<td align="right">
								<div align="right"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorAgua")%>
								</font></div>
								</td>
								<td align="right">
								<div align="right"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorEsgoto")%>
								</font></div>
								</td>
								<td align="right">
								<div align="right"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorDebito")%>
								</font></div>
								</td>
								<td align="right">
								<div align="right"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorCredito")%>
								</font></div>
								</td>
								<td align="right">
								  <div align="right">
								    <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								      <%=session.getAttribute("valorImposto")%>
								    </font>
								  </div>
								</td>
								<td align="right">
								<div align="right"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorConta")%>
								</font></div>
								</td>
								<td align="right">
								<div align="right" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorAcrescimo")%>
								</font></div>
								</td>
								<td align="left">
								<div align="left"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
								</td>
							</tr>
						</logic:notEmpty>
					</logic:present>
				</table>
				</div>
				</td>
			</tr>
			<%}%>
		</logic:notEmpty>
	</table>	
	
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			

<!-- Situacao de Cobranca -->
	<table width="100%" align="center" bgcolor="#90c7fc" border="0">
	
		<tr bordercolor="#79bbfd">
			<td colspan="4" bgcolor="#79bbfd" align="center"><strong>Situações de Cobrança </strong></td>
		</tr>
		<tr bordercolor="#000000">
			<td width="15%" bgcolor="#90c7fc">
			<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
				<strong>Código</strong> </font></div>
			</td>
			<td width="60%" bgcolor="#90c7fc">
			<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
				<strong>Descrição</strong> </font></div>
			</td>
			<td width="25%" bgcolor="#90c7fc">
			<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
				<strong>Data de Implantação</strong> </font></div>
			
		</tr>
		
		<logic:notEmpty name="colecaoCobrancaSituacao" scope="session">
		<%if (((Collection) session.getAttribute("colecaoCobrancaSituacao")).size() <= 3) {%>
	
		<%cor = "#cbe5fe";%>
		<logic:present name="colecaoCobrancaSituacao">
			<logic:iterate name="colecaoCobrancaSituacao" id="imovelCobrancaSituacao">
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
							<logic:notEmpty	name="imovelCobrancaSituacao" property="cobrancaSituacao">
								<bean:define name="imovelCobrancaSituacao"	property="cobrancaSituacao" id="cobrancaSituacao" />
								<bean:write name="cobrancaSituacao" property="id" />
							</logic:notEmpty> 
						</font></div>
					</td>
					<td>
						<div align="left" class="style9">
						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<logic:notEmpty	name="imovelCobrancaSituacao" property="cobrancaSituacao">
								<bean:define name="imovelCobrancaSituacao"	property="cobrancaSituacao" id="cobrancaSituacao" />
								<bean:write name="cobrancaSituacao" property="descricao" />
							</logic:notEmpty> 
						</font></div>
					</td>
					<td>
						<div align="left'''" class="style9">
						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:write name="imovelCobrancaSituacao" property="dataImplantacaoCobranca" formatKey="date.format" />
						</font></div>
					</td>
				</tr>
			</logic:iterate>
		</logic:present>
		<%} else {%>
		<tr>
			<td height="45" colspan="6">
				<div style="width: 100%; height: 100%; overflow: auto;">
					<table width="100%">
								
						<%cor = "#cbe5fe";%>
						<logic:present name="colecaoCobrancaSituacao">
							<logic:iterate name="colecaoCobrancaSituacao" id="imovelCobrancaSituacao">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
									cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
									cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>

									<td width="15%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
											<logic:notEmpty	name="imovelCobrancaSituacao" property="cobrancaSituacao">
												<bean:define name="imovelCobrancaSituacao"	property="cobrancaSituacao" id="cobrancaSituacao" />
												<bean:write name="cobrancaSituacao" property="id" />
											</logic:notEmpty> 
										</font></div>
									</td>
									<td width="61%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<logic:notEmpty	name="imovelCobrancaSituacao" property="cobrancaSituacao">
												<bean:define name="imovelCobrancaSituacao"	property="cobrancaSituacao" id="cobrancaSituacao" />
												<bean:write name="cobrancaSituacao" property="descricao" />
											</logic:notEmpty> 
										</font></div>
									</td>
									<td width="24%">
										<div align="left'''" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
												<bean:write name="imovelCobrancaSituacao" property="dataImplantacaoCobranca" formatKey="date.format" />
										</font></div>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>		 							


					</table>
				</div>
			</td>						
		</tr>						
		<%}%>						
		</logic:notEmpty>							
	</table>
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<!-- Historico de Retorno de Negativacoes -->
	<table width="100%" align="center" bgcolor="#90c7fc" border="0">
	
		<tr bordercolor="#79bbfd">
			<td colspan="4" bgcolor="#79bbfd" align="center"><strong>Histórico de Retorno de Negativações</strong></td>
		</tr>
		<tr bordercolor="#000000">
			<td width="10%" bgcolor="#90c7fc">
			<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
				<strong>Data</strong> </font></div>
			</td>
			<td width="40%" bgcolor="#90c7fc">
			<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
				<strong>Motivo</strong> </font></div>
			</td>
			<td width="35%" bgcolor="#90c7fc">
			<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
				<strong>Negativador</strong> </font></div>
			</td>
			<td width="15%" bgcolor="#90c7fc">
			<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
				<strong>Situação</strong> </font></div>
			</td>
		</tr>
		
		<logic:notEmpty name="colecaoDadosNegativacaoRetorno" scope="session">
		<%if (((Collection) session.getAttribute("colecaoDadosNegativacaoRetorno")).size() <= 3) {%>
	
		<%cor = "#cbe5fe";%>
		<logic:present name="colecaoDadosNegativacaoRetorno">
			<logic:iterate name="colecaoDadosNegativacaoRetorno" id="dadosNegativacaoRetornoHelper">
				<%if (cor.equalsIgnoreCase("#cbe5fe")) {
					cor = "#FFFFFF";%>
				<tr bgcolor="#FFFFFF">
					<%} else {
					cor = "#cbe5fe";%>
				<tr bgcolor="#cbe5fe">
					<%}%>

					<td width="10%">
						<div align="left" class="style9">
						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
							<logic:notEmpty	name="dadosNegativacaoRetornoHelper" property="dataRetorno">
								<bean:write name="dadosNegativacaoRetornoHelper" property="dataRetorno" formatKey="date.format" />
							</logic:notEmpty> 
						</font></div>
					</td>
					<td width="40%">
						<div align="left" class="style9">
						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<logic:notEmpty	name="dadosNegativacaoRetornoHelper" property="descricaoRetornocodigo">
								<bean:write name="dadosNegativacaoRetornoHelper" property="descricaoRetornocodigo" />
							</logic:notEmpty>
						</font></div>
					</td>
					
					<td width="35%">
						<div align="left" class="style9">
						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<logic:notEmpty	name="dadosNegativacaoRetornoHelper" property="nomeCliente">
								<bean:write name="dadosNegativacaoRetornoHelper" property="nomeCliente" />
							</logic:notEmpty>
						</font></div>
					</td>
					<td width="15%">
						<div align="left" class="style9">
						<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:write name="dadosNegativacaoRetornoHelper" property="indicadorCorrecaoFormatado" />
						</font></div>
					</td>
				</tr>
			</logic:iterate>
		</logic:present>
		
		
		<%} else {%>
		<tr>
			<td height="60" colspan="6">
				<div style="width: 100%; height: 100%; overflow: auto;">
					<table width="100%">
								
						<%cor = "#cbe5fe";%>
						<logic:present name="colecaoDadosNegativacaoRetorno">
							<logic:iterate name="colecaoDadosNegativacaoRetorno" id="dadosNegativacaoRetornoHelper">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
									cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
									cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>

									<td width="10%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
											<logic:notEmpty	name="dadosNegativacaoRetornoHelper" property="dataRetorno">
												<bean:write name="dadosNegativacaoRetornoHelper" property="dataRetorno" formatKey="date.format" />
											</logic:notEmpty> 
										</font></div>
									</td>
									<td width="40%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<logic:notEmpty	name="dadosNegativacaoRetornoHelper" property="descricaoRetornocodigo">
												<bean:write name="dadosNegativacaoRetornoHelper" property="descricaoRetornocodigo" />
											</logic:notEmpty>
										</font></div>
									</td>
									
									<td width="35%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<logic:notEmpty	name="dadosNegativacaoRetornoHelper" property="nomeCliente">
												<bean:write name="dadosNegativacaoRetornoHelper" property="nomeCliente" />
											</logic:notEmpty>
										</font></div>
									</td>
									<td width="15%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:write name="dadosNegativacaoRetornoHelper" property="indicadorCorrecaoFormatado" />
										</font></div>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>		 							


					</table>
				</div>
			</td>						
		</tr>						
		<%}%>
		
		</logic:notEmpty>
			
	</table>
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<!-- Debito a Cobrar -->				
	<table width="100%" align="center" bgcolor="#90c7fc" border="0">
		<tr bordercolor="#79bbfd">
			<td colspan="5" bgcolor="#79bbfd" align="center"><strong>D&eacute;bitos
			A Cobrar</strong></td>
		</tr>
		<tr bordercolor="#000000">
			<td width="50%" bgcolor="#90c7fc">
			<div align="center" class="style9"><font color="#000000"
				style="font-size:9px"
				face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo do
			D&eacute;bito</strong> </font></div>
			</td>
			<td width="13%" bgcolor="#90c7fc">
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
				face="Verdana, Arial, Helvetica, sans-serif"> <strong>Parcelas a
			cobrar</strong> </font></div>
			</td>
			<td width="17%" bgcolor="#90c7fc" height="20">
			<div align="center" class="style9"><font color="#000000"
				style="font-size:9px"
				face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor a
			cobrar</strong> </font></div>
			</td>
		</tr>
		<%cor = "#cbe5fe";%>
		<logic:present name="colecaoDebitoACobrar">
			<logic:iterate name="colecaoDebitoACobrar" id="debitoacobrar">
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
						<a
							href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:define name="debitoacobrar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&debitoID=<bean:write name="debitoacobrar" property="id" />', 570, 720);">
						<bean:define name="debitoacobrar" property="debitoTipo"
							id="debitoTipo" /> <logic:notEmpty name="debitoTipo"
							property="descricao">
							<bean:write name="debitoTipo" property="descricao" />
						</logic:notEmpty> </a>
					</logic:notEmpty> </font></div>
					</td>
					<td>
					<div align="center" class="style9"><font color="#000000"
						style="font-size:9px"
						face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
						name="debitoacobrar" property="anoMesReferenciaDebito">
						<%= Util.formatarAnoMesParaMesAno(((DebitoACobrar)debitoacobrar).getAnoMesReferenciaDebito().toString()) %>
					</logic:notEmpty> </font></div>
					</td>
					<td>
					<div align="center" class="style9"><font color="#000000"
						style="font-size:9px"
						face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
						name="debitoacobrar" property="anoMesCobrancaDebito">
						<%= Util.formatarAnoMesParaMesAno(((DebitoACobrar)debitoacobrar).getAnoMesCobrancaDebito().toString()) %>
					</logic:notEmpty> </font></div>
					</td>
					<td>
					<div align="center" class="style9"><font color="#000000"
						style="font-size:9px"
						face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
						name="debitoacobrar" property="parcelasRestanteComBonus">
						<bean:write name="debitoacobrar" property="parcelasRestanteComBonus" />
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
					<td>
					<%=((Collection) session.getAttribute("colecaoDebitoACobrar")).size()%>
					&nbsp;
					doc(s)
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>
					<div align="right" class="style9"><font color="#000000"
						style="font-size:9px"
						face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorDebitoACobrar")%>
					</font></div>
					</td>
				</tr>
			</logic:notEmpty>
		</logic:present>
	</table>
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	<!-- Creditos a Realizar -->

	<table width="100%" align="center" bgcolor="#90c7fc" border="0">
		<tr bordercolor="#79bbfd">
			<td colspan="5" bgcolor="#79bbfd" align="center"><strong>Cr&eacute;ditos
			A Realizar</strong></td>
		</tr>
		<tr bordercolor="#000000">
			<td width="53%" bgcolor="#90c7fc" height="20">
			<div align="center" class="style9"><font color="#000000"
				style="font-size:9px"
				face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo do
			Cr&eacute;dito</strong> </font></div>
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
				face="Verdana, Arial, Helvetica, sans-serif"> <strong>Parcelas a
			creditar</strong> </font></div>
			</td>
			<td width="17%" bgcolor="#90c7fc" height="20">
			<div align="center" class="style9"><font color="#000000"
				style="font-size:9px"
				face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor a
			creditar</strong> </font></div>
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
							face="Verdana, Arial, Helvetica, sans-serif"> <a
							href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=<bean:define name="creditoarealizar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&creditoID=<bean:write name="creditoarealizar" property="id" />', 570, 720);">
						<bean:define name="creditoarealizar" property="creditoTipo"
							id="creditoTipo" /> <logic:notEmpty name="creditoTipo"
							property="descricao">
							<bean:write name="creditoTipo" property="descricao" />
						</logic:notEmpty> </a> </font></div>
					</logic:notEmpty></td>
					<td>
					<div align="center" class="style9"><font color="#000000"
						style="font-size:9px"
						face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
						name="creditoarealizar" property="anoMesReferenciaCredito">
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
						<bean:write name="creditoarealizar" property="valorTotalComBonus"
							formatKey="money.format" />
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
					<td>
					<%=((Collection) session.getAttribute("colecaoCreditoARealizar")).size()%>
					&nbsp;
					doc(s)
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>
					<div align="right" class="style9"><font color="#000000"
						style="font-size:9px"
						face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorCreditoARealizar")%>
					</font></div>
					</td>
				</tr>
			</logic:notEmpty>
		</logic:present>
	</table>
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	<!-- Guia de Pagamento -->
	<table width="100%" align="center" bgcolor="#90c7fc" border="0">
		<tr bordercolor="#79bbfd">
			<td colspan="5" bgcolor="#79bbfd" align="center"><strong>Guias de Pagamento</strong></td>
		</tr>
		<tr bordercolor="#000000">
			<td width="36%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo do D&eacute;bito</strong> </font></div></td>
			<td width="11%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Prestação</strong> </font></div></td>
			<td width="13%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data de Emiss&atilde;o</strong> </font></div></td>
			<td width="13%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data de Vencimento</strong> </font></div></td>
			<td width="27%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor da Guia de Pagamento</strong> </font></div></td>
		</tr>
		<%cor = "#cbe5fe";%>
		<logic:present name="colecaoGuiaPagamentoValores">
			<logic:iterate name="colecaoGuiaPagamentoValores"
				id="guiapagamentohelper">
				<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";
					%>
				<tr bgcolor="#FFFFFF">
					<%} else {
				cor = "#cbe5fe";%>
				<tr bgcolor="#cbe5fe">
					<%}%>
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
					<td><%=((Collection) session.getAttribute("colecaoGuiaPagamentoValores")).size()%>
					&nbsp;
					doc(s)</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>
					<div align="right" class="style9"><font color="#000000"
						style="font-size:9px"
						face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorGuiaPagamento")%>
					</font></div>
					</td>
				</tr>
			</logic:notEmpty>
		</logic:present>
	</table>

	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

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
		<%if((session.getAttribute("valorTotalSemAcrescimo")!= null) || (session.getAttribute("valorTotalComAcrescimo") != null)){ %>
			<tr bgcolor="#FFFFFF">
				<td>
				<div align="right" class="style9"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorTotalSemAcrescimo")!= null?session.getAttribute("valorTotalSemAcrescimo"):""%>
				</font></div>
				</td>
				<td>
				<div align="right" class="style9"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorTotalComAcrescimo")!=null?session.getAttribute("valorTotalComAcrescimo"):""%>
				</font></div>
				</td>
				
				<td>
				<div align="right" class="style9"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorTotalDescontoPagamentoAVista")!=null?session.getAttribute("valorTotalDescontoPagamentoAVista"):""%>
				</font></div>
				</td>
				
				<td>
				<div align="right" class="style9"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorPagamentoAVista")!=null?session.getAttribute("valorPagamentoAVista"):""%>
				</font></div>
				</td>
			</tr>
		<%} %>
</table>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="gsan.gui.atualizacaocadastral.FiltrarDadosCadastraisImoveisInconsistentesDMActionForm"%>
<%@page import="gsan.atualizacaocadastral.bean.AtualizacoesPorInconsistenciaHelper"%>
<%@page import="gsan.atualizacaocadastral.bean.DadosMovimentoAtualizacaoCadastralDMHelper"%>
<%@ page import="gsan.atualizacaocadastral.bean.ImoveisRoteiroDispositivoMovelDMHelper"%>
<%@ page import="gsan.util.ConstantesSistema" %>
<%@ page import="gsan.cadastro.sistemaparametro.SistemaParametro"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>

<script language="JavaScript">
	function telaAnterior(){
		window.history.back();
	}
</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
<html:form action="/exibirGerarRoteiroDispositivoMovelAction"
	name="GerarRoteiroDispositivoMovelActionForm"
	type="gsan.gui.atualizacaocadastral.GerarRoteiroDispositivoMovelActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">
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
			
			<td width="620" valign="top" bgcolor="#003399" class="centercoltext">
				
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				
				<p>&nbsp;</p>

				<!--  ----------------------------  -->
				
				<logic:present name="colecaoImoveisInconsistentesHelper" scope="session">
			
					<table width="100%" 
						border="0" 
						align="center" 
						cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11">
								<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
							</td>
							<td class="parabg">Consultar Dados do Imóvel para Atualizacao Cadastral</td>
							<td width="11">
								<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>
					
					<p>&nbsp;</p>
			
					<table bordercolor="#000000" width="100%" cellspacing="0">
						<tr>
							<td>
								<p>Consultar Dados do Imóvel para Atualizacao Cadastral:</p>
								<hr>
							</td>
						</tr>
			        </table>
				
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td height="350" >
							<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%">
		             	<%	int cont = 0;	%>
		                	<logic:iterate name="colecaoImoveisInconsistentesHelper" 
			                	id="dadosAtualizar" 
			                	scope="session" 
			                	type="DadosMovimentoAtualizacaoCadastralDMHelper">
								
								<tr>
									<td colspan="3">
										<strong>Matricula do Imóvel:</strong>
										<bean:write name="dadosAtualizar" property="matricula"/>
									</td>
									
									<td>
										<strong>Setor:</strong>
										<bean:write name="dadosAtualizar" property="setor"/>
									</td>
									
									<td>
										<strong>Quadra:</strong>
										<bean:write name="dadosAtualizar" property="quadra"/>
									
										<strong>Lote:</strong>
										<bean:write name="dadosAtualizar" property="lote"/>
									</td>
									
								</tr>
							
								<tr>
									<td colspan="3">
										<strong>Numero de Visitas ao Imóvel:</strong>
										<bean:write name="dadosAtualizar" property="visitas"/>
									</td>
										
									<td>
										<input type="button" 
											name="ButtonPopUp" 
											class="bottonRightCol" 
											value="Consultar Imovel"
											onClick="javascript:redirecionarSubmit('exibirConsultarImovelAction.do?objetoConsulta=consultarImovel&idImovel=${dadosAtualizar.matricula}&objetoConsultaFiltro=${objetoConsultaFiltro}&id=${id}&objetoConsultaFiltroAnterior=${objetoConsultaFiltroAnterior}')" >
									</td>
									
									<td>
										<input type="button" 
											name="ButtonPopUp" 
											class="bottonRightCol" 
											value="Consultar Imovel Movimento"
											onClick="javascript:abrirPopup('exibirConsultarDadosImovelAmbienteVirtualPopupAction.do?idImovelAtualizacaoCadastral=${dadosAtualizar.imacUltimoRetorno}',800, 600);" > 
									</td>
								</tr>
							
								<tr>
									<td colspan="3"><strong>Retornar para campo:</strong>
										<input type="checkbox" name="retornarCampo<%="" + dadosAtualizar.getIdImovelAtlzCadastral()%>" 
											<c:choose>
												<c:when test="${dadosAtualizar.retornarCampo}">checked style="margin-left: 5px;" disabled="disabled"</c:when>
												<c:otherwise>style="margin-left: 5px;"</c:otherwise>
											</c:choose>
										/>
									</td>
								</tr>
								
							
								<tr>
									<td colspan="5">
									<table width="100%" bgcolor="#99CCFF" border="0">
										<tr>
											<td bgcolor="#79bbfd" align="center" colspan="5">
												<strong>ATUALIZAÇÕES POR INCONSISTÊNCIA<br></strong>
												<strong>Data de retorno: <%= new SimpleDateFormat("dd/MM/yyyy").format(dadosAtualizar.getColecaoAtualizacoesHelper().iterator().next().getDataRecebimentoMovimento()) %></strong>
											</td>
										</tr>
										
										<tr bordercolor="#000000">
											<td width="95px" style="max-width:95px;" bgcolor="#90c7fc" align="center">
												<strong>Alterar</strong>
											</td>
											<td width="100px" style="max-width:100px;" bgcolor="#90c7fc" align="center">
												<strong>Campo</strong>
										   </td>
											<td width="130px" style="max-width:130px;" bgcolor="#90c7fc" align="left">
												<strong>GSAN</strong>
										   </td>
										   <td width="130px" style="max-width:130px;" bgcolor="#90c7fc" align="left">
												<strong>Recad.</strong>
										   </td>
										   <td width="140px" style="max-width:140px;" bgcolor="#90c7fc" align="left" >
												<strong>Mensagem</strong>
										   </td>
										  
										</tr>							

										<bean:define name="dadosAtualizar" 
											property="colecaoAtualizacoesHelper" 
											id="helperAtualizar" >
										</bean:define>
										 
										<% Date dataInicial = dadosAtualizar.getColecaoAtualizacoesHelper().iterator().next().getDataRecebimentoMovimento(); %>
										<% Integer idInicial = dadosAtualizar.getColecaoAtualizacoesHelper().iterator().next().getIdImovel(); %> 
										 
										<logic:iterate name="helperAtualizar" 
											id="dados" 
											type="AtualizacoesPorInconsistenciaHelper">
					                         
					                         <% if(!dataInicial.equals(dados.getDataRecebimentoMovimento()) && idInicial.equals(dados.getIdImovel())){ %>
					                        	 
					                        	 <tr>
													<td bgcolor="#79bbfd" align="center" colspan="5">
														<!--  <strong>ATUALIZAÇÕES POR INCONSISTÊNCIA<br></strong> --> 
														<strong>Data de retorno: <%= new SimpleDateFormat("dd/MM/yyyy").format(dados.getDataRecebimentoMovimento()) %></strong>
													</td>
												</tr>
												
												<tr bordercolor="#000000">
													<td width="95px" style="max-width:100px;" bgcolor="#90c7fc" align="center">
														<strong>Alterar</strong>
													</td>
													<td width="100px" style="max-width:100px;" bgcolor="#90c7fc" align="center">
														<strong>Campo</strong>
												   </td>
													<td width="130px" style="max-width:130px;" bgcolor="#90c7fc" align="left">
														<strong>GSAN</strong>
												   </td>
												   <td width="130px" style="max-width:130px;" bgcolor="#90c7fc" align="left">
														<strong>Recad.</strong>
												   </td>
												   <td width="140px" style="max-width:140px;" bgcolor="#90c7fc" align="left" >
														<strong>Mensagem</strong>
												   </td>
												  
												</tr>
												  
												</tr>	
					                        	 
					                         <% } %>
					                      
					                      <% dataInicial = dados.getDataRecebimentoMovimento();
					                      	 idInicial = dados.getIdImovel(); %>   
					                         
		                         		<%	cont = cont + 1;
										if (cont % 2 == 0) {	%>
					               		<tr bgcolor="#cbe5fe">
						             	<%	} else {	%>
						                  	<tr bgcolor="#FFFFFF">
						             	<%	}	%>
				                       		
				                       		<td width="95px" style="max-width:100px;" align="center">
												<select style="width:95px;" id="alteracao" name='alteracao<c:out value="${dados.idRetornoAtlzCadastral}"></c:out>'>
													<c:choose>
														<c:when test="${dados.codigoAlteracao eq 1}">
															<option value="1" selected="selected">Aprovado</option>
														</c:when>
														<c:when test="${dados.codigoAlteracao eq 3}">
															<option value="3" selected="selected">Reprovado</option>
														</c:when>
														<c:otherwise>
															<option value="" selected="selected">&nbsp;</option>	
															<option value="3">Reprovado</option>	
															<c:if test="${dados.permiteAtualizarInconsistencia eq 1}">
																<option value="1">Aprovado</option>	
															</c:if>	
														</c:otherwise>
													</c:choose>
												</select>
				                       		</td>
				                            
				                            <td width="100px" style="max-width:100px;" align="center"> 
				                            	<bean:write name="dados" property="atributo"/>
				                            </td>
		
				                            <td nowrap="nowrap" width="130px" style="max-width:130px; word-wrap: break-word;" align="left" >
				                            	<bean:write name="dados" property="valorAtributoGSAN" />
											</td>
											
											<td nowrap="nowrap" width="130px" style="max-width:130px; word-wrap: break-word;" align="left" >
												<bean:write name="dados" property="valorAtributoRecadastramento" />
											</td>
											<td  width="140px" style="max-width:140px;" align="left">
												<bean:write name="dados" property="mensagem" />
											</td>
				                      	</tr>
			                   			</logic:iterate>
			                   		</table>
			                   		</td>
			                	
			                	</tr>

		                  		<tr> 
					                <td colspan="5" height="24" bordercolor="#000000" class="style1"> 
					                	<hr>
					                </td>
					            </tr>

		              		</logic:iterate>
	                  		
	                  	
	                  	</table>
	                  	</div>
	                  	</td>
						</tr>
					</table>
			
					<table width="100%" border="0">
						
						<tr>
							<td valign="top">
								<div align="left">
									<input type="button"
										name="ButtonCancelar" 
										class="bottonRightCol" 
										value="Voltar"
										onClick="javascript:redirecionarSubmit('filtrarDadosCadastraisImoveisInconsistentesDMAction.do?objetoConsultaFiltro=${objetoConsultaFiltro}&id=${id}&objetoConsultaFiltroAnterior=${objetoConsultaFiltroAnterior}');">
								</div>
							</td>
							
							<td align="right">
								<input name="Button2" 
									   type="button"
									   class="bottonRightCol" 
									   value="Confirmar Atualização" 
									   align="right"
									    onClick="javascript:redirecionarSubmit('atualizarDadosCadastraisImoveisInconsistentesAction.do');" /></td>
							
						</tr>
					</table>
				
				</logic:present>				
				
			</td>
				
		</tr>	
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%> 
</html:form>
</div>
	<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>

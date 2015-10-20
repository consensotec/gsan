<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cobranca.bean.ContaValoresHelper" isELIgnored="false"%>
<%@ page import="gcom.cobranca.bean.GuiaPagamentoValoresHelper" isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gcom.cobranca.bean.OpcoesParcelamentoHelper" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>CAEMA | Servi�os</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>internal2.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>jquery.theme.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		
		<logic:present name="exibirDetalhesDebito" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$('table').each(function(){
						$(this).children('tbody').children('tr:last').addClass('last-tr');
					});
				});
			</script>
		</logic:present>
		
		<!-- [if lt IE 9]>
			<style type="text/css">
				#form-matricula input.campo-text {height:28px!important; padding-top:5px!important}
			</style>
		<![endif]-->
		
		<logic:present name="clienteInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('O seu CPF n�o esta vinculado a matr�cula do im�vel, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195.');
				});
			</script>
		</logic:present>
		
		<logic:present name="entradaInformadaMenorMinima" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('Valor da entrada deve ser maior ou igual a entrada min�ma do parcelamento R$<%= request.getAttribute("entradaMinima") %>.');
				});
			</script>
		</logic:present>
		
		<logic:present name="recalcularOpcaoParcelamento" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('� necess�rio calcular quando o valor da entrada for alterado.');
				});
			</script>
		</logic:present>
		
		<logic:present name="numeroReparcelamentos" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('N�o existe a condi��o por quantidade de reparcelamentos para o perfil do parcelamento.');
				});
			</script>
		</logic:present>
		
		<logic:present name="opcaoParcelamentoNaoInformada" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('Informe op��o de parcelamento.');
				});
			</script>
		</logic:present>
		
		<logic:present name="parcelamentoJaEfetuado" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('Parcelamento j� efetuado.');
				});
			</script>
		</logic:present>
		
		<logic:present name="mensagemReativacao" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessageReativacao('Para reativa��o do ramal de �gua, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195.');
				});
			</script>
		</logic:present>
		
		<logic:present name="valorMinimoInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('Valor da entrada informado menor que o valor poss�vel para quantidade de parcelas selecionadas.');
				});
			</script>
		</logic:present>
		
		<logic:present name="valorEntradaInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('N�o � permitido efetuar o parcelamento com o valor da entrada, tente pagar � vista ou v� a um dos postos de atendimento da Compesa.');
				});
			</script>
		</logic:present>
		
		<logic:present name="valorEntradaNaoDigitado" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('Digite o valor da entrada.');
				});
			</script>
		</logic:present>
		
		<logic:present name="cpfInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('O seu CPF n�o esta vinculado a matr�cula do im�vel, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195.');
				});
			</script>
		</logic:present>
		
		<script type="text/javascript">
			$(document).ready(function(){
				if('<%= request.getParameter("calcularParcelas") %>' == 'SIM'){
					$('[name=valorEntradaInformado]').focus();					
				}
				
				$('#botaoConfirmar').click(function(){
					$.blockUI({
						message : $('#alertConfirmacao'),
						theme : true,
						title : 'Confirmar'
					});
					
					$('#botaoSim').click(function(){
						efetuarParcelamento();
						$.unblockUI();
					});
					$('#botaoNao').click(function(){
						$.unblockUI();
					});
					
				});
				
				$('#btn-a-vista').click(function(){
					$.blockUI({
						message : $('#alertConfirmacaoAVista'),
						theme : true,
						title : 'Confirmar'
					});
					
					$('#botaoSimAVista').click(function(){
						if(parseInt($('[name=indicadorRestabelecimento]:checked').val()) == 1){
							showMessageReativacaoAVista('Para reativa��o do ramal de �gua, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195.');
						}else{
							gerarDocumentoExtrato();
							$.unblockUI();
						}
					});
					$('#botaoNaoAVista').click(function(){
						$.unblockUI();
					});
				});
			});
		</script>
		
		<script type="text/javascript">
			function showMessage(message){
				$('#message h3').text(message);
				$.blockUI({
					message : $('#message'),
					theme : true,
					title : 'Aviso'
				});
				
				$('.confirm').live('click', function(){
					$.unblockUI();
				});
			}
			
			function showMessageReativacao(message){
				$('#alertReativacaoRamal h3').text(message);
				$.blockUI({
					message : $('#alertReativacaoRamal'),
					theme : true,
					title : 'Aviso'
				});
				
				$('#botaoOkReativacao').live('click', function(){
					gerarDocumentosParcelamento();
					$.unblockUI();
				});
			}
			
			function showMessageReativacaoAVista(message){
				$('#alertReativacaoRamal h3').text(message);
				$.blockUI({
					message : $('#alertReativacaoRamal'),
					theme : true,
					title : 'Aviso'
				});
				
				$('#botaoOkReativacao').live('click', function(){
					gerarDocumentoExtrato();
					$.unblockUI();
				});
			}
			
			function caracteresespeciais () { 
				this.aa = new Array("matricula", "Matr�cula possui caracteres especiais.", new Function ("varName", " return this[varName];"));
			    this.ac = new Array("cpfCliente", "CPF do Cliente possui caracteres especiais.", new Function ("varName", " return this[varName];"));
			} 
			
			function IntegerValidations () { 
				this.aa = new Array("matricula", "Matr�cula deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
			    this.ac = new Array("cpfCliente", "CPF do Cliente deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
			} 
		
			function limparForm(form){
				limparConfirmarCpfCliente();
			}
			
			function recalcularParcelmamento(){
				var form = document.forms[0];
				if(form.cpfCliente.value.length != 0){
					form.action = "exibirEfetuarParcelamentoDebitosPortalAction.do?method=pesquisarCliente&mudancaIndicadorRestabelecimento=SIM";
					form.submit();
				}
			}
			
			function calcularOpcaoParcelamento(){
				var form = document.forms[0];
				form.action = "exibirEfetuarParcelamentoDebitosPortalAction.do?calcularParcelas=SIM";
				form.submit();
			}
			
			function efetuarParcelamento(){
				var form = document.forms[0];
				form.submit();
			}
			
			function gerarDocumentoExtrato(){
				window.location.href='<html:rewrite page="/gerarRelatorioExtratoDebitoAction.do?parcelamentoPortal=SIM&parcelamento=1&indicadorContasRevisao=1&RD="/><bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="resolucaoDiretoria"/>'
			}
			
			function gerarDocumentosParcelamento(){
				window.location.href='gerarRelatorioDocumentosParcelamentoPortalCaemaAction.do'
			}

			function exibirEfetuarParcelamento(){
				var form = document.forms[0];
				form.action = "exibirEfetuarParcelamentoDebitosPortalCaemaAction.do?method=pesquisarCliente";
				form.submit();
			}
		</script>
	</head>
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/caema/cabecalho.jsp"%>

			<!-- Content - Start -->
			<div id="content">
				<%@ include file="/jsp/portal/caema/cabecalhoImovel.jsp"%>
				
				<div id="parc-debito" class="serv-int">
	            	<h3>Parcelamento de d�bitos<span>&nbsp;</span></h3>
	                <p>Endere�o do im�vel: <em id="enderecoImovel">${EfetuarParcelamentoDebitosPortalActionForm.enderecoImovel}</em></p>
	                <p class="info-2">Para efetuar o parcelamento informe o CPF do cliente respons�vel pelo d�bito.</p>

	            	<html:form action="/efetuarParcelamentoDebitosPortalCaemaAction.do"
						name="EfetuarParcelamentoDebitosPortalCaemaActionForm"
						type="gcom.gui.portal.caema.EfetuarParcelamentoDebitosPortalCaemaAction" method="post">

						<fieldset>
	                    	<legend>CPF do solicitante</legend>
	                    	
							<div>
								<span class="cmp-text-2">
									<label for="cpfCliente">CPF do solicitante:</label>
									<html:text property="cpfCliente" size="15" maxlength="14" tabindex="1" onkeypress="return isCampoNumerico(event);" />
								</span>
								<input type="button" value="" class="btn-pesquisar" tabindex="2" 
									onclick="javascript:exibirEfetuarParcelamento();"/>
									
								<logic:present name="exibirDetalhesDebito" scope="request">
									<p class="nome-cliente">${EfetuarParcelamentoDebitosPortalCaemaActionForm.nomeCliente}</p>
								</logic:present>
								<logic:present name="reativacaoLigacaoAgua" scope="request">
									<span class="cmp-text-2" style="width: 90%;">
										<label for="cpfCliente">Com reativa��o de liga��o de �gua?</label>
										<div style="display: inline-block; width: 80px;">
											<html:radio property="indicadorRestabelecimento" value="1" onchange="javascript:recalcularParcelmamento()" style="width:auto;" styleId="sim"/>
											<label for="sim" style="float: left; padding-top: 6px; padding-left: 8px;">Sim</label>
										</div>
										<div style="display: inline-block; width: 80px;">
											<html:radio property="indicadorRestabelecimento" value="2" onchange="javascript:recalcularParcelmamento()" style="width:auto;" styleId="nao"/>
											<label style="float: left; padding-top: 6px; padding-left: 8px;" for="nao">N�o</label>
										</div>
									</span>
								</logic:present>
							
							</div>
							
							
							<logic:present name="exibirDetalhesDebito" scope="request">
								<!-- In�cio do detalhamento das contas -->
								<table summary="Tabela de contas">
									<%int cont = 0;%>
									<tr>
										<td colspan="4">
										<table summary="Tabela de contas">
											<logic:empty name="colecaoContaValores" scope="session">
												<thead>
							                    	<tr>
							                        	<th width="205">M�s / Ano</th>
							                            <th width="125">Vencimento</th>
							                            <th width="117">Valor (R$)</th>
							                            <th width="170">Acr�scimo por impontualidade (R$)</th>
							                        </tr>
							                    </thead>
											</logic:empty>
											<logic:notEmpty name="colecaoContaValores" scope="session">
												<%if (((Collection<gcom.cobranca.bean.ContaValoresHelper>) session.getAttribute("colecaoContaValores")).size() 
														<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
												<thead>
							                    	<tr>
							                        	<th width="205">M�s / Ano</th>
							                            <th width="125">Vencimento</th>
							                            <th width="117">Valor (R$)</th>
							                            <th width="170">Acr�scimo por impontualidade (R$)</th>
							                        </tr>
							                    </thead>
												
												<tbody>
													<logic:iterate name="colecaoContaValores"
													type="ContaValoresHelper" id="contaValores">
													
														<c:if test="${contaValores.revisao eq 2}">
														
														<%cont = cont + 1;
														if (cont % 2 == 0) {%>
														<tr class="tr-2">
														<%} else {%>
														<tr class="tr-1">
														<%}%>
															<td width="205">
																<logic:notEmpty name="contaValores" property="conta">
																	<logic:notEmpty name="contaValores" property="conta.dataRevisao">
															           <bean:write name="contaValores" property="conta.formatarAnoMesParaMesAno"/>
															        </logic:notEmpty>  
																	<logic:empty name="contaValores" property="conta.dataRevisao">
															           <bean:write name="contaValores" property="conta.formatarAnoMesParaMesAno" />
																	</logic:empty>
														         </logic:notEmpty>
														         <logic:empty name="contaValores" property="conta">
														              <bean:write name="contaValores" property="conta.formatarAnoMesParaMesAno" />
													             </logic:empty> 
															</td>
															<td width="125">
																<bean:write name="contaValores"
																	property="conta.dataVencimentoConta" formatKey="date.format" />
															</td>
															<td width="117"><bean:write name="contaValores" property="conta.valorTotal"
																formatKey="money.format" />
															</td>
															<td width="170">
																<bean:write name="contaValores"
																	property="valorTotalContaValoresParcelamento" formatKey="money.format" />
															</td>
														</tr>
														</c:if>
													</logic:iterate>
												</tbody>
												
												<%} else {%>
												
												<thead>
							                    	<tr>
							                        	<th width="205">M�s / Ano</th>
							                            <th width="125">Vencimento</th>
							                            <th width="117">Valor (R$)</th>
							                            <th width="170">Acr�scimo por impontualidade (R$)</th>
							                        </tr>
							                    </thead>
							                    
							                    <tbody>
													<tr>
														<td height="100" colspan="6">
														<div style="width: 100%; height: 100%; overflow-x: hidden;">
														<table width="100%" style="margin-top: 0">
															
															<logic:iterate name="colecaoContaValores" type="ContaValoresHelper" id="contaValores">
																
																<c:if test="${contaValores.revisao eq 2}">
																<%cont = cont + 1;
																if (cont % 2 == 0) {%>
																<tr class="tr-2">
																<%} else {%>
																<tr class="tr-1">
																<%}%>
																	<td width="205">
																		<logic:notEmpty name="contaValores" property="conta">
																           <bean:write name="contaValores" property="conta.formatarAnoMesParaMesAno" />
																         </logic:notEmpty>
																         <logic:empty name="contaValores" property="conta">
															              <bean:write name="contaValores"
																	        property="conta.formatarAnoMesParaMesAno" />
															             </logic:empty> 
																	</td>
																	<td width="125">
																		<bean:write name="contaValores" property="conta.dataVencimentoConta"
																			formatKey="date.format" />
																	</td>
																	<td width="117">
																		<bean:write name="contaValores" property="conta.valorTotal"
																			formatKey="money.format" />
																	</td>
																	<td width="170">
																		<bean:write name="contaValores" property="valorTotalContaValores" 
																			formatKey="money.format" />
																	</td>
																</tr>
																</c:if>
															</logic:iterate>
														</table>
														</div>
														</td>
													</tr>
												</tbody>
												<%}%>
											</logic:notEmpty>
										</table>
										</td>
									</tr>
								</table>
								<!-- Fim do detalhamento das contas -->

								<!-- In�cio Resumo dos d�bitos -->								
								<h4 class="resumo">Resumo do d�bito<span>&nbsp;</span></h4>
								<ul class="lista-resumo">
									<li>
				                		<h6>Contas:</h6>
				                		R$<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="valorTotalContaValores" />
				                	</li>
				                	<li>
				                		<h6>Guias de pagamento:</h6>
				                		R$<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="valorGuiasPagamento" />
				                	</li>
		                			<li>
				                		<h6>Acr�scimos por impontualidade:</h6>
				                		<logic:notEqual name="EfetuarParcelamentoDebitosPortalCaemaActionForm" 
											property="valorAcrescimosImpontualidade" value="0,00">
											R$<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm"
												property="valorAcrescimosImpontualidade" formatKey="money.format" />
										</logic:notEqual>
										<logic:equal name="EfetuarParcelamentoDebitosPortalCaemaActionForm"
											property="valorAcrescimosImpontualidade" value="0,00">
											R$<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm"
												property="valorAcrescimosImpontualidade" formatKey="money.format" />
										</logic:equal>
				                	</li>
				                	<li>
				                		<h6>D�bitos a cobrar:</h6>
				                		<span>Servi�os:</span>
				                		<logic:notEqual name="EfetuarParcelamentoDebitosPortalCaemaActionForm" 
											property="valorDebitoACobrarServico" value="0,00">
											R$<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="valorDebitoACobrarServico"  formatKey="money.format"/>
										</logic:notEqual>
										<logic:equal name="EfetuarParcelamentoDebitosPortalCaemaActionForm" 
											property="valorDebitoACobrarServico" value="0,00">
											R$<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="valorDebitoACobrarServico" formatKey="money.format" />
										</logic:equal>
				                		<br />
				                		<span>Parcelamento:</span>
				                		<logic:notEqual name="EfetuarParcelamentoDebitosPortalCaemaActionForm" 
											property="valorDebitoACobrarParcelamento" value="0,00">
											<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=
												<bean:define name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="matriculaImovel" id="imovel" />
												<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="matriculaImovel" />&parcelamentoID=', 600, 800);">
												R$<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="valorDebitoACobrarParcelamento"  formatKey="money.format"/>
											</a>
										</logic:notEqual>
										<logic:equal name="EfetuarParcelamentoDebitosPortalCaemaActionForm" 
											property="valorDebitoACobrarParcelamento" value="0,00">
											R$<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="valorDebitoACobrarParcelamento" formatKey="money.format" />
										</logic:equal>
				                	</li>
				                	<li>
				                		<h6>Cr�ditos a realizar:</h6>
				                		<logic:notEqual name="EfetuarParcelamentoDebitosPortalCaemaActionForm" 
											property="valorCreditoARealizar" value="0,00">
											<a href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=<bean:define name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="matriculaImovel" id="imovel" />
												<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="matriculaImovel" />&parcelamentoID=', 600, 800);">
												R$<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="valorCreditoARealizar" formatKey="money.format"/>
											</a>
										</logic:notEqual>
										<logic:equal name="EfetuarParcelamentoDebitosPortalCaemaActionForm" 
											property="valorCreditoARealizar" value="0,00">
											R$<bean:write	name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="valorCreditoARealizar" formatKey="money.format" />
										</logic:equal>
										<br />&nbsp;
				                	</li>
				                	<li>
				                		<h6>D�bito total atualizado:</h6>
				                		R$<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="valorDebitoTotalAtualizado" />
				                		<br />&nbsp;
				                	</li>
				                	<li>
				                		
				                	</li>
								</ul>	
								<!-- Fim Resumo dos d�bitos -->	
								
								<!-- In�cio Forma de pagamento a vista -->
								<div id="pagto">
									<h5>Pagamento � vista:</h5>
									<ul class="lista-resumo">
										<li>
					                		<h6>Valor atualizado:</h6>
											R$<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="valorDebitoTotalAtualizado"  
												formatKey="money.format"/>
					                		<br />
										</li>
										<li>
					                		<h6>Valor dos impostos:</h6>
											R$<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="valorTotalImpostos" 
												formatKey="money.format"/>
											<br />
										</li>
										<li>
					                		<h6>Valor do desconto:</h6>
											R$<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="valorDescontoPagamentoAVista" 
												formatKey="money.format"/>
											<br />
										</li>
										<li>
					                		<h6>Valor pagamento � vista:</h6>
											R$<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="valorPagamentoAVista" 
												formatKey="money.format"/>
										</li>
									</ul>
				                	<table>
				                		<tr>
						                	<td width="20"><input type="button" value="" class="btn-confirmar" id="btn-a-vista"
						                		style="margin: 2px 10px 0 0px;"/></td>
						                	<td style="text-align: left;"><em style="padding:28px 0px 0px 0px; clear: none; text-align: left;">Gerar Extrato Pagamento � Vista</em></td>
					                	</tr>
				                	</table>
								</div>
								<!-- Fim Forma de pagamento a vista -->
								
								
								
								<!-- In�cio Forma de pagamento parcelado -->
								<div id="pagto">
									<h5>Pagamento parcelado</h5>
			                		<span class="cmp-text-2" style="width: 190px; font-weight: 700">
			                            <label for="vlr-entrada" style="color: #008FD6; font-size: 12px;">Valor total a ser parcelado:</label> 
			                            R$<bean:write name="EfetuarParcelamentoDebitosPortalCaemaActionForm" property="valorDesconto"/>
			                            <label for="vlr-entrada" style="color: #008FD6; font-size: 12px;">Valor de entrada (R$):</label>
			                            <html:text property="valorEntradaInformado" size="15" maxlength="14" tabindex="3"
			                            	onkeyup="formataValorMonetario(this,20)" 
			                            	onkeypress="return isCampoNumerico(event);" />
			                        </span>
		                            <input type="button" value="" class="btn-calcular" tabindex="4" style="margin-top: 63px" 
										onclick="javascript:calcularOpcaoParcelamento()"/>
										
									<%int count = 0;%>
									<%int cor = 0;%>	
									<table summary="Tabela de contas">
					                    <logic:notEmpty name="colecaoOpcoesParcelamento" scope="session">
					                    	<%if (((Collection<OpcoesParcelamentoHelper>) session.getAttribute("colecaoOpcoesParcelamento")).size() <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_OPCAO_PARCELAMENTO) {%>
					                    	<thead>
						                    	<tr>
						                        	<th width="86">Parcelas</th>
						                            <th width="115">Valor da entrada(R$)</th>
						                            <th width="110">Valor da parcela(R$)</th>
						                            <th width="">Taxa de juros(%)</th>
						                        </tr>
						                    </thead>
						                    <logic:iterate name="colecaoOpcoesParcelamento" type="OpcoesParcelamentoHelper" id="opcoesParcelamento">
						                    <%count = count + 1;
											if (count % 2 == 0) {%>
											<tr class="tr-3">
											<%} else {%>
											<tr class="tr-4">
											<%}%>
												<td class="first-td">
													<% if ((((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()) != null 
															&& ((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()
																.equals(((OpcoesParcelamentoHelper)opcoesParcelamento).getQuantidadePrestacao().toString())){ %>
														<input type="radio" name="indicadorQuantidadeParcelas" value="${opcoesParcelamento.quantidadePrestacao}" checked="true"/>
													<% } else { %>
														<input type="radio" name="indicadorQuantidadeParcelas" value="${opcoesParcelamento.quantidadePrestacao}"/>
													<% } %>
													<bean:write name="opcoesParcelamento" property="quantidadePrestacao"/>
												</td>
												<td>
													<bean:write name="opcoesParcelamento" property="valorEntradaMinima" formatKey="money.format"/>
												</td>
												<td>
													<bean:write name="opcoesParcelamento" property="valorPrestacao" formatKey="money.format"/>	
												</td>
												<td>
													<bean:write name="opcoesParcelamento" property="taxaJuros" formatKey="money.format"/>	
												</td>
						                    </logic:iterate>
						                    <% }else{ %>
						                    <thead>
						                    	<tr>
						                        	<th width="86">Parcelas</th>
						                            <th width="115">Valor da entrada(R$)</th>
						                            <th width="110">Valor da parcela(R$)</th>
						                            <th width="">Taxa de juros(%)</th>
						                        </tr>
						                    </thead>
											
											<tbody>
												<tr>
													<td height="190" colspan="6">
														<div style="width: 100%; height: 100%; overflow-x: hidden;">
														<table width="100%" style="margin-top: 0">
																
														<logic:iterate name="colecaoOpcoesParcelamento" type="OpcoesParcelamentoHelper" id="opcoesParcelamento">
														<%count = count + 1;
														if (count % 2 == 0) {%>
															<tr class="tr-3">
														<%} else {%>
															<tr class="tr-4">
														<%}%>
															
																<logic:equal name="opcoesParcelamento" property="indicadorValorPrestacaoMaiorValorLimite" value="2">
																	<td width="86" class="first-td">
																		<% if ((((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()) != null 
																			&& ((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()
																				.equals(((OpcoesParcelamentoHelper)opcoesParcelamento).getQuantidadePrestacao())){ %>
																			<input type="radio" name="indicadorQuantidadeParcelas" 
																				value="${opcoesParcelamento.quantidadePrestacao}" checked="true"/>
																		<% } else { %>
																			<input type="radio" name="indicadorQuantidadeParcelas" 
																				value="${opcoesParcelamento.quantidadePrestacao}"/>
																		<% } %>
																		<bean:write name="opcoesParcelamento" property="quantidadePrestacao"/>
																	</td>
																	<td width="115">
																		<bean:write name="opcoesParcelamento" property="valorEntradaMinima" formatKey="money.format"/>
																	</td>
																	<td width="110">
																		<bean:write name="opcoesParcelamento" property="valorPrestacao" formatKey="money.format"/>	
																	</td>
																	<td width="">
																		<bean:write name="opcoesParcelamento" property="taxaJuros" formatKey="money.format"/>	
																	</td>
																</logic:equal>
																
																
																<logic:equal name="opcoesParcelamento" property="indicadorValorPrestacaoMaiorValorLimite" value="1">
																	<%cor = 1;%>		
																	<td width="86" class="first-td">
																		<% if ((((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()) != null 
																			&& ((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()
																				.equals(((OpcoesParcelamentoHelper)opcoesParcelamento).getQuantidadePrestacao())){ %>
																			<input type="radio" name="indicadorQuantidadeParcelas" 
																				value="${opcoesParcelamento.quantidadePrestacao}" checked="true"/>
																		<% } else { %>
																			<input type="radio" name="indicadorQuantidadeParcelas" 
																				value="${opcoesParcelamento.quantidadePrestacao}"/>
																		<% } %>
																			<bean:write name="opcoesParcelamento" property="quantidadePrestacao"/>
																	</td>
																	<td width="115">
																		<bean:write name="opcoesParcelamento" property="valorEntradaMinima" formatKey="money.format"/>
																	</td>
																	<td width="110">
																		<bean:write name="opcoesParcelamento" property="valorPrestacao" formatKey="money.format"/>	
																	</td>
																	<td width="">
																		<bean:write name="opcoesParcelamento" property="taxaJuros" formatKey="money.format"/>	
																	</td>
																</logic:equal>
															</tr>
														</logic:iterate>
							           					</table>
							           					</div>
						           					</td>
					           					</tr>
					           				</tbody>
											<% } %>
					                    </logic:notEmpty>
					                </table>
					                <input type="button" value="" class="btn-confirmar" id="botaoConfirmar"/>
									<!-- Fim da forma de pagamento parcelado -->	
								</div>	
							</logic:present>
	                    </fieldset>
					</html:form>
	            </div>
	        </div>
	        
			<%@ include file="/jsp/portal/rodape.jsp"%>
		</div><!-- Content - End -->
		
		<div id="message" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"></h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
		
		<div id="alertConfirmacao" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Deseja confirmar parcelamento dos d�bitos?</h3> 
			<a href="javascript:void(0);" id="botaoSim" class="ui-corner-all button">Sim</a>&nbsp;
			<a href="javascript:void(0);" id="botaoNao" class="ui-corner-all button">N�o</a>
		</div>
		
		<div id="alertConfirmacaoAVista" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Deseja gerar extrato para pagamento � vista?</h3> 
			<a href="javascript:void(0);" id="botaoSimAVista" class="ui-corner-all button">Sim</a>&nbsp;
			<a href="javascript:void(0);" id="botaoNaoAVista" class="ui-corner-all button">N�o</a>
		</div>
		
		<div id="alertReativacaoRamal" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"></h3> 
			<a href="javascript:void(0);" id="botaoOkReativacao" class="ui-corner-all button">OK</a>
		</div>
	</body>
</html:html>
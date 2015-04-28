<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gsan.gui.portal.caema.ConsultarEstruturaTarifariaPortalCaemaHelper" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>

		<title>CAERN | Informa&ccedil;&otilde;es</title>

		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>internal.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>jquery.theme.css" type="text/css">
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
		
		
		<logic:present name="cpfInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('O CPF do responsável pelo parcelamento deve constar no nosso cadastro.');
				});
			</script>
		</logic:present>
		
			<logic:present name="arquivoNaoEncontrado" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('O arquivo não foi encontrado.');
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
			
		</script>
	</head>
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/caern/cabecalho.jsp"%>

			<!-- Content - Start -->
			<div id="content">
				<%@ include file="/jsp/portal/caern/cabecalhoInformacoes.jsp"%>
				
				<div id="parc-debito" class="serv-int">
	            	<h3>Estrutura Tarifária<span>&nbsp;</span></h3>

	            	<form>
						
						<!-- Início dos links para download 
						<div id="links" class="subTopicos">
							<div id="atualizacao" class="pontilhada">
								<a href="http://www.caern.rn.gov.br/contentproducao/aplicacao/caern/servicos/gerados/tabelatarifaria.asp" target="_blank" title="Estrutura Tarifária">
									<img src="/gsan/imagens/portal/general/botao_pdf.gif" style="float: left"/>
								</a>
								<em style="height: 34px; width: 300px;">
									Estrutura Tarifária
								</em>
							</div>
						</div>-->
						<!-- Fim dos links para download -->						
						
						<!-- Início dos consumidores medidos -->
						<div id="negociadebitos" class="serv-int" style="height: 300px; width: 97%; overflow: auto;">
						
							<p><b>
								Residencial:
							</b></p>
							
							<p class="paragrafo"><em>Quando o fornecimento de água e coleta de esgotos sanitários forem usados para fins 
								domésticos em economia de uso exclusivamente residencial.</em></p>							
							
							<!-- Início da estrutura tarifária residencial -->
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%int cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperResidencial" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Faixa de Consumo (m³/Eco/mês)</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperResidencial" type="ConsultarEstruturaTarifariaPortalCaemaHelper" 
													id="estruturaTarifariaResidencial">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaResidencial" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaResidencial" property="valorReal" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
							<!-- Fim da estrutura tarifária residencial -->
							
							<p><b>Residencial Popular:</b></p>
							
							<!-- Início da estrutura tarifária residencial -->
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperResidencialPopular" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Faixa de Consumo (m³/Eco/mês)</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperResidencialPopular" type="ConsultarEstruturaTarifariaPortalCaemaHelper" 
													id="estruturaTarifariaResidencialPopular">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaResidencialPopular" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaResidencialPopular" property="valorReal" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
							
<%-- 							<p><b>Residencial Popular Rural:</b></p>
							
							<!-- Início da estrutura tarifária residencial popular rural -->
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperResidencialPopularRural" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Faixa de Consumo (m³/Eco/mês)</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperResidencialPopularRural" type="ConsultarEstruturaTarifariaPortalCaemaHelper" 
													id="estruturaTarifariaResidencialPopularRural">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaResidencialPopularRural" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaResidencialPopularRural" property="valorReal" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
 --%>							
<%--							<p><b>Residencial Rural:</b></p>
							
							<!-- Início da estrutura tarifária residencial rural -->
 							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperResidencialRural" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Faixa de Consumo (m³/Eco/mês)</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperResidencialRural" type="ConsultarEstruturaTarifariaPortalCaemaHelper" 
													id="estruturaTarifariaResidencialRural">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaResidencialRural" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaResidencialRural" property="valorReal" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table> --%>
							
							<p><b>Residencial Social:</b></p>
							
							<!-- Início da estrutura tarifária residencial social rural -->
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperResidencialSocialRural" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Faixa de Consumo (m³/Eco/mês)</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperResidencialSocialRural" type="ConsultarEstruturaTarifariaPortalCaemaHelper" 
													id="estruturaTarifariaResidencialSocialRural">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaResidencialSocialRural" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaResidencialSocialRural" property="valorReal" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>							
							
							<p><b>Comercial:</b></p>
							<p class="paragrafo"><em>Quando o fornecimento de água e coleta de esgotos sanitários forem usados em estabelecimentos comerciais.</em></p>	

							
							<!-- Início da estrutura tarifária comercial -->
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperComercial" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Faixa de Consumo (m³/Eco/mês)</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperComercial" type="ConsultarEstruturaTarifariaPortalCaemaHelper" 
													id="estruturaTarifariaComercial">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaComercial" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaComercial" property="valorReal" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
							<!-- Fim da estrutura tarifária comercial -->
							
							
<%-- 							<p><b>Comercial Rural:</b></p>
							
							<!-- Início da estrutura tarifária comercial rural-->
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperComercialRural" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Faixa de Consumo (m³/Eco/mês)</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperComercialRural" type="ConsultarEstruturaTarifariaPortalCaemaHelper" 
													id="estruturaTarifariaComercialRural">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaComercialRural" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaComercialRural" property="valorReal" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>	 --%>						
							
							
							<!-- Início da estrutura tarifária industrial -->
							<p><b>Industrial:</b></p>
							<p class="paragrafo"><em>Quando o fornecimento de água e coleta de esgotos sanitários forem usados em estabelecimentos industriais.</em></p>	
							
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperIndustrial" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Faixa de Consumo (m³/Eco/mês)</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperIndustrial" type="ConsultarEstruturaTarifariaPortalCaemaHelper" 
													id="estruturaTarifariaIndustrial">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaIndustrial" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaIndustrial" property="valorReal" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
							<!-- Fim da estrutura tarifária industrial -->
							
							<!-- Início da estrutura tarifária industrial rural-->
<%-- 							<p><b>Industrial Rural:</b></p>
							
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperIndustrialRural" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Faixa de Consumo (m³/Eco/mês)</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperIndustrialRural" type="ConsultarEstruturaTarifariaPortalCaemaHelper" 
													id="estruturaTarifariaIndustrialRural">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaIndustrialRural" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaIndustrialRural" property="valorReal" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table> --%>							
							
							<!-- Início da estrutura tarifária publica -->
							<p><b>Pública:</b></p>
							<p class="paragrafo"><em>Quando o fornecimento de água e coleta de esgotos sanitários forem usados em estabelecimentos públicos.</em></p>
							
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperPublico" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Faixa de Consumo (m³/Eco/mês)</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperPublico" type="ConsultarEstruturaTarifariaPortalCaemaHelper" 
													id="estruturaTarifariaPublica">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaPublica" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaPublica" property="valorReal" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
							
							
<%-- 							<p><b>Público Federal Rural:</b></p>
							
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperPublicoFederalRural" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Faixa de Consumo (m³/Eco/mês)</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperPublicoFederalRural" type="ConsultarEstruturaTarifariaPortalCaemaHelper" 
													id="estruturaTarifariaPublicoFederalRural">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaPublicoFederalRural" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaPublicoFederalRural" property="valorReal" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>							
							
							<p><b>Público Estadual Rural:</b></p>
							
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperPublicoEstadualRural" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Faixa de Consumo (m³/Eco/mês)</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperPublicoEstadualRural" type="ConsultarEstruturaTarifariaPortalCaemaHelper" 
													id="estruturaTarifariaPublicoEstadualRural">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaPublicoEstadualRural" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaPublicoEstadualRural" property="valorReal" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
							
							<p><b>Público Municipal Rural:</b></p>
							
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperPublicoMunicipalRural" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Faixa de Consumo (m³/Eco/mês)</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperPublicoMunicipalRural" type="ConsultarEstruturaTarifariaPortalCaemaHelper" 
													id="estruturaTarifariaPublicoMunicipalRural">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaPublicoMunicipalRural" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaPublicoMunicipalRural" property="valorReal" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table> --%>


						<!-- Início do esgotamento sanitário -->
							<p><b>Esgotamento Sanitário:</b></p>
							
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<thead>
					                    	<tr>
					                        	<th width="30%">Tipo</th>
					                            <th width="70%">Valor</th>
					                        </tr>
					                    </thead>
											<tbody>
												<tr class="tr-1" id="tr-1-2">
													<td width="30%" style="border-right: 1px solid #C8C8C8;">Sistema Convencional</td>
													<td width="70%">Ligação Convencional - 70% da tarifa de água</td>
												</tr>
												
												<tr class="tr-2" id="tr-2-2">
													<td width="30%" style="border-right: 1px solid #C8C8C8;"></td>
													<td width="70%">Ligação Condominial - 35% da tarifa de água</td>
												</tr>
												
												<tr class="tr-1" id="tr-1-2">
													<td width="30%" style="border-right: 1px solid #C8C8C8;"></td>
													<td width="70%">Poço Tubular - 100% da tarifa de água</td>
												</tr>
												
											</tbody>										
									</table>
									</td>
								</tr>
							</table>							
						<!-- Fim do esgotamento sanitário -->						
							<!-- Fim da estrutura tarifária publica -->
						</div>
						<!-- Fim dos consumidores medidos -->
						

						
						
						
					</form>
	            </div>
	        </div>
	        
			<%@ include file="/jsp/portal/caern/rodape.jsp"%>
		</div><!-- Content - End -->
		
		<div id="message" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"></h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</body>
</html:html>
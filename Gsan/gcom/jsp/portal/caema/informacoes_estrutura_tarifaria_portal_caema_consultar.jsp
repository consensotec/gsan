<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.gui.portal.caema.ConsultarEstruturaTarifariaPortalCaemaHelper" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>

		<title>Caema | Serviços</title>

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
	    	<%@ include file="/jsp/portal/caema/cabecalho.jsp"%>

			<!-- Content - Start -->
			<div id="content">
				<%@ include file="/jsp/portal/caema/cabecalhoInformacoes.jsp"%>
				
				<div id="parc-debito" class="serv-int">
	            	<h3>Estrutura Tarifária<span>&nbsp;</span></h3>

	            	<form>
						<!-- Início dos consumidores medidos -->
						<div id="negociadebitos" class="serv-int">
						
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
															<bean:write name="estruturaTarifariaResidencial" property="valor" />
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
								<%int count = 0;%>
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
													 <%count = count + 1;
													if (count % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaResidencialPopular" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaResidencialPopular" property="valor" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>							
							
							<p><b>Entidades Filantrópicas:</b></p>
							
							<!-- Início da estrutura tarifária residencial -->
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%int countEntidade = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperEntidadesFilantropicas" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Faixa de Consumo (m³/Eco/mês)</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperEntidadesFilantropicas" type="ConsultarEstruturaTarifariaPortalCaemaHelper" 
													id="estruturaTarifariaEntidadeFilantropica">
													<%	countEntidade = countEntidade + 1;
													if (countEntidade % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
												<%	} else {	%>
													<tr class="tr-1" id="tr-1-2">
												<%	}	%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaEntidadeFilantropica" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaEntidadeFilantropica" property="valor" />
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
															<bean:write name="estruturaTarifariaComercial" property="valor" />
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
							
							<p><b>Comercial Pequenos Negócios:</b></p>
							
							<!-- Início da estrutura tarifária comercial pequenos negocios -->
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperComercialPequenosNegocios" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Faixa de Consumo (m³/Eco/mês)</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperComercialPequenosNegocios" type="ConsultarEstruturaTarifariaPortalCaemaHelper" 
													id="estruturaTarifariaComercialPequenosNegocios">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifariaComercialPequenosNegocios" property="quantidade" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifariaComercialPequenosNegocios" property="valor" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
							<!-- Fim da estrutura tarifária comercial Pequenos negocios-->
							
							
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
															<bean:write name="estruturaTarifariaIndustrial" property="valor" />
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
							
							<!-- Início da estrutura tarifária publica -->
							<p><b>Pública:</b></p>
							
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="helperPublica" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Faixa de Consumo (m³/Eco/mês)</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperPublica" type="ConsultarEstruturaTarifariaPortalCaemaHelper" 
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
															<bean:write name="estruturaTarifariaPublica" property="valor" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
							
							
							<!-- Fim da estrutura tarifária publica -->
						</div>
						<!-- Fim dos consumidores medidos -->
						
						
						
						
					</form>
	            </div>
	        </div>
	        
			<%@ include file="/jsp/portal/caema/rodape.jsp"%>
		</div><!-- Content - End -->
		
		<div id="message" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"></h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</body>
</html:html>
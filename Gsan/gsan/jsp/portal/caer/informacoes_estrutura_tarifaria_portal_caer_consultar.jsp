<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gsan.gui.portal.caer.ConsultarEstruturaTarifariaPortalCaerHelper" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>

		<title>CAER | Serviços</title>

		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaer.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaer.css"/>internal.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaer.css"/>jquery.theme.css" type="text/css">
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
	    	<%@ include file="/jsp/portal/caer/cabecalho.jsp"%>

			<!-- Content - Start -->
			<div id="content">
				<%@ include file="/jsp/portal/caer/cabecalhoInformacoes.jsp"%>
				
				<div id="parc-debito" class="serv-int">
	            	<h3>Estrutura Tarifária<span>&nbsp;</span></h3>

	            	<form>
						<!-- Início dos consumidores medidos -->
						<div id="negociadebitos" class="serv-int" style="height: 300px; width: 97%; overflow: auto;">
							
							<p><b>
								RESOLUÇÃO Nº 04/2008:
							</b></p>
							
							<!-- Início da estrutura da tabela -->
							<table summary="Tabela do consumos" style="margin-top: 0px">
								<tr>
									<td colspan="4">
									<table summary="Tabela de consumo" >
										<tbody>
											<tr class="tr-1" id="tr-1-2">
												<td width="50%" style="border-right: 1px solid #C8C8C8;">
										        	A - Todas as Categorias 
										        </td>
										    	<td width="50%" align="center">
													D - Todas as Categorias 
												</td>
											</tr>
											<tr class="tr-1" id="tr-1-2">
										        <td width="50%" style="border-right: 1px solid #C8C8C8;">
										        	Consumo até 10m³ ou Consumidores Taxados
										         </td>
										         <td width="50%" align="center">
													Consumo de 31m³ até 100m³
												 </td>
											</tr>
											<tr class="tr-1" id="tr-1-2">
												<td width="50%" style="border-right: 1px solid #C8C8C8;">
										        	V = NI
												</td>
												<td width="50%" align="center">
													V = NI(7x² + 1200x)/10.000	
												</td>
											</tr>
											
											<tr class="tr-2" id="tr-2-2">
												<td width="50%" style="border-right: 1px solid #C8C8C8;">
										        	B - Todas as Categorias
										        </td>
										    	<td width="50%" align="center">
													E - Todas as Categorias
												</td>
										    </tr>
										    <tr class="tr-2" id="tr-2-2">
										        <td width="50%" style="border-right: 1px solid #C8C8C8;">
										        	Consumo de 11m³ até 19m³ 
										        </td>
										        <td width="50%" align="center">
													Consumo de 101m³ até 200m³
												</td>
										    </tr>
										    <tr class="tr-2" id="tr-2-2">
										    	<td width="50%" style="border-right: 1px solid #C8C8C8;">
										        	V = NI(7x² + 995x)/10.000 
												</td>
												<td width="50%" align="center">
													V = NI(7x² + 1210x)/10.000	
												</td>
											</tr>
											
											<tr class="tr-1" id="tr-1-2">		
												<td width="50%" style="border-right: 1px solid #C8C8C8;">
										        	C - Todas as Categorias
										        </td>
										   		<td width="50%" align="center">
										   			F - Todas as Categorias
										   		</td>
										   	</tr>
										   	<tr class="tr-1" id="tr-1-2">
										   		<td width="50%" style="border-right: 1px solid #C8C8C8;">
										        	Consumo de 20m³ até 30m³
										        </td>
										        <td width="50%" align="center">
													Consumo Superior a 200m³
												</td>
											</tr>
											<tr class="tr-1" id="tr-1-2">
												<td width="50%" style="border-right: 1px solid #C8C8C8;">
										        	V = NI(7x² + 1130x)/10.000
												</td>
												<td width="50%" align="center">
													V = NI(0,32x - 11,2)		
												</td>
											</tr>
										</tbody>										
									</table>
									</td>
								</tr>
							</table>
							<!-- Fim da estrutura da tabela -->
						
							<p><b>
								Sendo:
							</b></p>
							
							<!-- Início da estrutura da legenda -->
							<table summary="Tabela de legendas" style="margin-top: 0px">
								<tr>
									<td colspan="4">
									<table summary="Tabela de legenda">
										<tbody>
											<tr class="tr-1" id="tr-1-2">
												<td width="5%" style="border-right: 1px solid #C8C8C8;">
										        	V 
												</td>
												<td width="50%" align="center">
													Valor da Conta de Água em R$	
												</td>
											</tr>
											<tr class="tr-2" id="tr-2-2">
												<td width="5%" style="border-right: 1px solid #C8C8C8;">
										        	NI 
												</td>
												<td width="50%" align="center">
													Tarifa Mínima da Categoria do Consumidor	
												</td>
											</tr>
											<tr class="tr-1" id="tr-1-2">		
												<td width="5%" style="border-right: 1px solid #C8C8C8;">
										        	X 
												</td>
												<td width="50%" align="center">
													Consumo em m³	
												</td>
											</tr>
										</tbody>										
									</table>
									</td>
								</tr>
							</table>
							<!-- Fim da estrutura da legenda -->
							
							<p><b>
								Vigência 01.09.11:
							</b></p>
							
							<!-- Início da estrutura tarifária residencial -->
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%int cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de conta">
										<logic:notEmpty name="helper" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="55%">Categoria</th>
						                            <th width="25%">Tarifa Mínima - NI (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helper" type="ConsultarEstruturaTarifariaPortalCaerHelper" 
													id="estruturaTarifaria">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="estruturaTarifaria" property="descricaoCategoria" />
														</td>
														<td width="25%">
															<bean:write name="estruturaTarifaria" property="valor" />
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
							
							<logic:notEmpty name="helperResidencial" scope="request">
								<p><b>
									Exemplo:
								</b></p>
								
								<p class="paragrafo"><em><b>Como calcular o valor em R$ para um consumo de 15m³ de água na categoria residencial.</b></em></p>
								
								<logic:iterate name="helperResidencial" type="ConsultarEstruturaTarifariaPortalCaerHelper" id="tarifaResidencial">
									<p class="paragrafo"><em><b>
										V = NI (7x² + 995x) /10000 <br/>
										V = <bean:write name="tarifaResidencial" property="valor" /> (7.15² + 995.15) /10000 <br/>
										V = <bean:write name="tarifaResidencial" property="valor" /> (7.225 + 14925) /10000 <br/>
										V = <bean:write name="tarifaResidencial" property="valor" /> (1575 + 14925) /10000 <br/>
										V = <bean:write name="tarifaResidencial" property="valor" /> (16500) /10000 <br/>
										V = <bean:write name="tarifaResidencial" property="valorExemplo" /> <br/>
									</b></em></p>
								</logic:iterate>
							</logic:notEmpty>
							
							<logic:notEmpty name="helperTarifaSocial" scope="request">
								<logic:iterate name="helperTarifaSocial" type="ConsultarEstruturaTarifariaPortalCaerHelper" id="tarifaSocial">
									<p><b>Tarifa Social:</b></p>
									
									<p class="paragrafo"><em><b>
										Criada pelo Conselho de Administra&ccedil;&atilde;o em 08/11/05, com um valor de R$ <bean:write name="tarifaSocial" property="valor" />, 
										tem como objetivo levar o saneamento às pessoas menos favorecidas, garantindo sa&uacute;de 
										e qualidade de vida às fam&iacute;lias roraimenses que n&atilde;o tem condi&ccedil;&otilde;es 
										de pagar pelos servi&ccedil;os.
									</b></em></p>
									
									<p><b>Cr&iacute;terios:</b></p>
									
									<p class="paragrafo"><em><b>
										Im&oacute;veis: Somente devem ser cadastrados os im&oacute;veis com &aacute;rea constru&iacute;da de at&eacute; 70 m² (setenta metros quadrados), para fins residenciais. <br/>
										Consumo de &aacute;gua: at&eacute; 10 m³. <br/>
										Renda: A renda da fam&iacute;lia residente no im&oacute;vel ser&aacute; de at&eacute; um sal&aacute;rio m&iacute;nimo por pessoa. <br/>
										Consumo de energia: at&eacute; 80 kW.<br/>
									</b></em></p>
									
								</logic:iterate>
							</logic:notEmpty>
							
							<logic:notEmpty name="helperResidencial" scope="request">
								<p><b>Esgoto:</b></p>
								
								<p class="paragrafo"><em><b>
									Incidir&aacute; o percentual de 80% sobre o valor da conta de &aacute;gua, no caso de haver 
									rede de esgoto dispon&iacute;vel no logradouro do usu&aacute;rio.
								</b></em></p>
								
								<p><b>
									Exemplo:
								</b></p>
								
								<p class="paragrafo"><em><b>Como calcular o valor da Tarifa de esgoto para um consumode 10m³ de água que custa, na tarifa residencial. 
									R$ <bean:write name="tarifaResidencial" property="valor" /></b></em></p>
								
								<logic:iterate name="helperResidencial" type="ConsultarEstruturaTarifariaPortalCaerHelper" id="tarifaResidencial">
									<p class="paragrafo"><em><b>
										Esgoto = 80% sobre o valor de R$ <bean:write name="tarifaResidencial" property="valor" /> <br/>
										Esgoto = R$ <bean:write name="tarifaResidencial" property="valorEsgoto" /> <br/>
									</b></em></p>
								</logic:iterate>
								
								<p class="paragrafo"><em><b>Somando o valor de Tarifa de Água mais o valor de Tarifa de Esgoto, teremos o valor total da conta de &aacute;gua: <br />
									R$ (<bean:write name="tarifaResidencial" property="valorEsgoto" /> + <bean:write name="tarifaResidencial" property="valor" /> ) 
									= <bean:write name="tarifaResidencial" property="valorTotalConta" />
								</b></em></p>
								
							</logic:notEmpty>
							
						</div>
						<!-- Fim dos consumidores medidos -->
					
					</form>
	            </div>
	        </div>
	        
			<%@ include file="/jsp/portal/caer/rodape.jsp"%>
		</div><!-- Content - End -->
		
		<div id="message" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"></h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</body>
</html:html>
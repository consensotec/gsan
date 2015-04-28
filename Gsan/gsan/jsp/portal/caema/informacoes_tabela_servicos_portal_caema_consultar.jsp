<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gsan.gui.portal.caema.ConsultarTabelaServicosPortalCaemaHelper" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>CAEMA | Serviços</title>
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
	            	<h3>Tabela de Serviços<span>&nbsp;</span></h3>

	            	<form>
						<!-- Início dos consumidores medidos -->
						<div id="consumidoresMedidos" class="subTopicos">
							
							<!-- Início da estrutura tarifária residencial -->
							<table summary="Tabela de contas" style="margin-top: 0px">
								<%int cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Tabela de contas">
										<logic:notEmpty name="colecaoServicoTipoHelper" scope="request">
											<thead>
						                    	<tr>
						                    		<th width="20%">Codigo</th>
						                        	<th width="55%">Descrição do Serviço</th>
						                            <th width="25%">Valor (R$)</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="colecaoServicoTipoHelper" type="ConsultarTabelaServicosPortalCaemaHelper" 
													id="helper">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="20%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="helper" property="codigo" />
														</td>
														<td width="55%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="helper" property="descricao" />
														</td>
														<td width="25%">
															<bean:write name="helper" property="valor" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
							
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
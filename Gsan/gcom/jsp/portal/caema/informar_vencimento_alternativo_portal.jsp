<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>CAEMA | Serviços</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>jquery.theme.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>

		<script type="text/javascript">
			$(document).ready(function(){
			
			
				$('.btn-enviar').click(function(){
					var msg = '';
					if($.trim($('[name=novoDiaVencimento] option:selected').val()) <= 0){
						msg += 'Informar o novo dia de vencimento. \n';
					}

					if(msg.length > 0){
						$('#novoDiaVencimento h3').html(msg);
						
						$.blockUI({
							message : $('#novoDiaVencimento'),
							theme : true,
							title : 'Aviso',
							onBlock : function() {
								$('.ui-widget-overlay').removeClass('ui-widget-overlay');
							}
						});
					} else {
						$('form').submit();
					}
					return false;
				});
				
				$('.confirm').click(function(){
					$.unblockUI();
					$('#novoDiaVencimento').focus().select();
				});
			});
		</script>
		<logic:present name="VencimentoAlteradoComSucesso" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#VencimentoAlteradoComSucesso'),
						theme : true,
						title : 'Aviso'
					});
					
					$('#voltar').click(function(){
						window.location.href = '/gsan/exibirServicosPortalCaemaAction.do?method=voltarServico';
					});
				});
			</script>
		</logic:present>
		<logic:present name="numeroMesesMinimoVencimentoAlternativoSuperiorPermitido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#numeroMesesMinimoVencimentoAlternativoSuperiorPermitido'),
						theme : true,
						title : 'Aviso' 
					});
					
					$('#voltar').click(function(){
						window.location.href = '/gsan/exibirServicosPortalCaemaAction.do?method=voltarServico';
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="exception" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#exception'),
						theme : true,
						title : 'Aviso',
						onBlock : function() {
							$('.ui-widget-overlay').removeClass('ui-widget-overlay');
						}
					});
					
					$('.confirm').click(function() {
						$.unblockUI();
					});
				});
			</script>
		</logic:present>
	</head>
	
	<body>
		
		<div id="container">
	    	<%@ include file="/jsp/portal/caema/cabecalho.jsp"%>

			<!-- Content - Start -->
			<div id="content">
			
				<%@ include file="/jsp/portal/caema/cabecalhoImovel.jsp"%>
				
				<div class="serv-int" id="vencimento-alt">
	            	<h3>Alterar vencimento da conta<span>&nbsp;</span></h3>
	            	<br>

	            	<html:form  action="/inserirDiaVencimentoAlternativoAction.do" 
	            				name="InformarVencimentoAlternativoActionForm"
								type="gcom.gui.faturamento.conta.InformarVencimentoAlternativoActionForm" method="post">
						<html:text property="matriculaImovel" style="display:none;" value="${ExibirServicosPortalCaemaActionForm.matricula}" />
	                	<fieldset>
	                		
							<br>
	                		
	                		<span>Dia de vencimento atual: <strong><bean:write name="vencimentoAtual" scope="session" /></strong></span>
	                		
	                		
	                    	<span>Selecione o novo dia do vencimento:
	                    	
	                        	<html:select property="novoDiaVencimento" style="width: 60px">
									<html:option value="-1">&nbsp;</html:option>
									<logic:present name="colecaoNovoDiaVencimento" scope="session">
										<c:forEach items="${sessionScope.colecaoNovoDiaVencimento}" var="diaVencimento">
											<option value="${diaVencimento}">${diaVencimento}</option>
										</c:forEach>
									</logic:present>
								</html:select>
                      		</span>
                      		<span>
                      		<input type="submit" class="btn-enviar" value="" style="margin-left: 2"/>
                      		</span>
	                    </fieldset>
					</html:form>
	            </div>
	        </div>
			
			<%@ include file="/jsp/portal/caema/rodape.jsp"%>
		</div><!-- Content - End -->
	</body>
	
	<div id="novoDiaVencimento" style="display:none; cursor: default"> 
        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"></h3> 
        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a> 
	</div>
	
	<logic:present name="exception" scope="request">
		<div id="exception" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
	        	<bean:write name="exception" scope="request" />
	        </h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
	
	<logic:present name="VencimentoAlteradoComSucesso" scope="request">
		<div id="VencimentoAlteradoComSucesso" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
	        	Vencimento Alterado com Sucesso.<br />
	        </h3>
	    	<font style="font-weight:normal; text-align:left;">
	    		
	    	</font>
	        <a href="javascript:void(0);" class="ui-corner-all button" id="voltar">OK</a>
		</div>
	</logic:present>
	
	<logic:present name="numeroMesesMinimoVencimentoAlternativoSuperiorPermitido" scope="request">
		<div id="numeroMesesMinimoVencimentoAlternativoSuperiorPermitido" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
	        	Imóvel com vencimento alterado há menos de <bean:write name="numeroMesesMinimo" scope="request" /> meses.<br />
	        </h3>
	    	<font style="font-weight:normal; text-align:left;">
	    		
	    	</font>
	        <a href="javascript:void(0);" class="ui-corner-all button" id="voltar">OK</a>
		</div>
	</logic:present>
	
</html:html>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cadastro.imovel.bean.ConsultarImovelRegistroAtendimentoHelper" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>CAERN | Servi&ccedil;os</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaern.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaern.css"/>internal2.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaern.css"/>jquery.theme.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		<style type="text/css">
			.serv-int table tbody tr.tr-21 {
				background:url(/gsan/imagens/portal/caern/general/tr2-1.png) no-repeat 0 0 transparent;			
			}
			.serv-int table tbody tr.tr-22 {
				background:url(/gsan/imagens/portal/caern/general/tr2-2.png) no-repeat 0 0 transparent;				
			}
		</style>
		<logic:present name="erroSistema" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#erroSistema'),
						theme : true,
						title : 'Erro.',
						onBlock : function() {
							$('.ui-widget-overlay').removeClass('ui-widget-overlay');
						}
					});
					
					$('.ok').click(function(){
						$.unblockUI();
						return false;
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
				});
			</script>
		</logic:present>
		<script type="text/javascript">
			$(document).ready(function(){
				$('table').each(function(){
					$(this).children('tbody').children('tr:last').addClass('last-tr');
				});
			});
		</script>
		
	</head>
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/caern/cabecalho.jsp"%>

			<!-- Content - Start -->
			<div id="content">
				<%@ include file="/jsp/portal/caern/cabecalhoImovel.jsp"%>
				<div id="acomp-ra" class="serv-int">
					<h3>Acompanhar Registro de Atendimento<span>&nbsp;</span></h3>
					
					<br />
					<br />
					<logic:empty name="colecaoRAAcompanhadas" scope="session">
						<strong>N&atilde;o h&aacute; registros de atendimentos para serem acompanhados</strong>
					</logic:empty>					
					<logic:notEmpty name="colecaoRAAcompanhadas" scope="session">
					<table><tr><td>
						<div style="overflow-y:scroll; height: 160px; width: 660px;">
						<table width="100%" style="margin-top: 0">
							<thead>
								<tr>
									<th width="50px">Protocolo</th>									
									<th width="50px">Especificação</th>									 
									<th width="50px">Data Atendimento</th>
									<th width="50px">Data Encerramento</th>
									<th width="50px">Situação</th>
									<th width="50px">Motivo Encerramento</th>
								</tr>
							</thead>							
							<tbody>
							
								<%! int i = 2;%>
								<logic:iterate name="colecaoRAAcompanhadas" type="ConsultarImovelRegistroAtendimentoHelper" id="ra">							
									<tr class="tr-<%=(i%2) + 1%>" id="tr-<%=(i%2) + 1%>-2">		
										<td width="130px">
											<bean:write name="ra" property="numeroProtocolo" />											
										</td>
										<td width="130px">
											<span style="font-size: 8px;">
												<bean:write name="ra" property="especificacao" />
											</span>											 
										</td>										 												
										<td>
											<span style="font-size: 10px;">
												<bean:write name="ra" property="dataAtendimento" />
											</span>
										</td>
										<td>											
											<span style="font-size: 10px;">										
												<bean:write name="ra" property="dataEncerramento" />
											</span>											
										</td>
										<td>
											<span style="font-size: 10px;">
												<bean:write name="ra" property="situacao" />
											</span>
										</td>
										<td>
											<span style="font-size: 10px;">
												<bean:write name="ra" property="motivoEncerramento" />
											</span>
										</td>																												
									</tr>
										
									<%i++;%>
								</logic:iterate>
								<%i = 2;%>
								
							</tbody>							
						</table>	
						</div>					
						</td></tr></table>
												
					</logic:notEmpty>
					
				</div>
			</div>
			<%@ include file="/jsp/portal/caern/rodape.jsp"%>
		</div>
		<div id="erroSistema" style="display:none; cursor: default"> 
        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Erro do sistema.</h3> 
        <input type="button" style="float:right;" class="ok" value="Ok" /> 
	</div>
	
	<logic:present name="exception" scope="request">
		<div id="exception" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
	        	<bean:write name="exception" scope="request" />
	        </h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
	</body>
</html:html>
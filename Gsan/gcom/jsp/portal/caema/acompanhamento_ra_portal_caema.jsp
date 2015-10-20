<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cadastro.imovel.bean.ConsultarImovelRegistroAtendimentoHelper" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Caema | Serviços</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>internal2.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>jquery.theme.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		<style type="text/css">
			.botao {
				background:url("/gsan/imagens/portal/caema/general/tit-consultar.png") no-repeat scroll 0px 0px;
	 			cursor: pointer;
	 			height: 21px;
	 			width: 60px;
	 			margin-right: 5px;
	 			border: 5px;
			}

			.tabela {
				width: 100% !important;
			}

			.serv-int thead tr {
				background: url("/gsan/imagens/portal/caema/general/thead-4.png") no-repeat scroll 0px 0px !important;
				background-size: 100% 100% !important;
			}			   

			.serv-int table tbody tr.tr-1-1{
				background:url(/gsan/imagens/portal/caema/general/tr2-1.png) no-repeat  0px 0px;
				background-size: 100% !important;
			}

			.serv-int table tbody tr.tr-22{
				background:url(/gsan/imagens/portal/caema/general/tr2-2.png) no-repeat  0px 0px;
				background-size: 100% !important;								
			}

			#tr-1-2 {
    			background: url("/gsan/imagens/portal/caema/general/tr-1.png") no-repeat scroll 0px 0px;
    			background-size: 100% !important; 				
		   	}
		   
		   	#tr-2-2 {
    			background: url("/gsan/imagens/portal/caema/general/tr-2(2).png") no-repeat scroll 0px 0px;
    			background-size: 100% !important;
   		   	}

   		   	.serv-int span {
   		   		width: auto !important;
   		   	}

   		   	.tr-even {
	   		   	background: #DADADA;
   		   	}

   		   	.tr-odd {
	   		   	background: #FFF;
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
		
		<script>			
			 function abrirConsultarTramite(numeroRA){
				    var form =  document.forms[0];
				    form.action = "/gsan/exibirConsultarTramitePortalCaemaAction.do?numeroRA="+numeroRA;
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
				<div id="acomp-ra" class="serv-int" style="width: 865px;">
					<h3>Acompanhar Registro de Atendimento<span>&nbsp;</span></h3>
					
					<br />
					<br />
					<logic:empty name="colecaoRAAcompanhadas" scope="session">
						<strong>N&atilde;o h&aacute; registros de atendimentos para serem acompanhados</strong>
					</logic:empty>					
					<logic:notEmpty name="colecaoRAAcompanhadas" scope="session">
						<table><tr><td>
						<div style="overflow-y:scroll; height: 200px; width: 880px;">
						<table class="tabela">
							<form method="post">
							<thead>
								<tr>
									<th width="15%">Protocolo</th>									
									<th width="20%">Especificação</th>									 
									<th width="13%">Data Atendimento</th>
									<th width="13%">Data Encerramento</th>
									<th width="10%">Situação</th>
									<th width="19%">Motivo Encerramento</th>
									<th width="10%">Trâmite</th>
								</tr>
							</thead>							
							<tbody>
								<% int i = 0;%>
								<logic:iterate name="colecaoRAAcompanhadas" type="ConsultarImovelRegistroAtendimentoHelper" id="ra">							
									<tr class="<%=(i % 2 == 0 ? "tr-even" : "tr-odd")%>" style="font-size: 10px;">		
										<td width="15%">
											<span style="font-size: 10px;">
												<bean:write name="ra" property="numeroProtocolo" />	
											</span>											
										</td>
										<td width="20%">
											<bean:write name="ra" property="especificacao" />
										</td>										 												
										<td width="13%">
											<bean:write name="ra" property="dataAtendimento" />
										</td>
										<td width="13%">											
											<bean:write name="ra" property="dataEncerramento" />
										</td>
										<td  width="10%">
											<bean:write name="ra" property="situacao" />
										</td>
										<td  width="19%">
											<bean:write name="ra" property="motivoEncerramento" />
										</td>
										<td width="10%">
											<button class="botao"
											onclick="abrirConsultarTramite(<bean:write name="ra" property="idRegistroAtendimento"/>);">
											</button>									
										</td>																												
									</tr>
										
									<%i++;%>
								</logic:iterate>
								</tbody>
							</form>						
						</table>			
						</div>
						</td></tr></table>
					</logic:notEmpty>
					
				</div>
			</div>
			<%@ include file="/jsp/portal/caema/rodape.jsp"%>
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
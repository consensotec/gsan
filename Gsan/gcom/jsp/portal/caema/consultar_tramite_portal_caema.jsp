<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.gui.portal.caema.ConsultarTramiteHelper" %>
<%@ page import="gcom.cadastro.unidade.UnidadeOrganizacional" %>

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
		
			.serv-int table {
				width: 100% !important;				
			}
		
			.serv-int table td {
    			text-align: left;
			}
		
			.serv-int font{ 
			 	color: #1E90FF;
			}
			
			.serv-int td.td-tramite {
				text-align: center;	
			}
			.serv-int td.td-tramite2 {
				text-align: left;	
			}
			
			.serv-int thead tr {
				background: url("/gsan/imagens/portal/caema/general/thead-4.png") no-repeat scroll 0px 0px !important;
				background-size: 100% 100% !important;
			}
			
			.serv-int th.th-tramite{
				text-align: left;
				
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
	    	<%@ include file="/jsp/portal/caema/cabecalho.jsp"%>

			<!-- Content - Start -->
			<div id="content">
				<%@ include file="/jsp/portal/caema/cabecalhoConsultarTramite.jsp"%>
				<div id="acomp-ra" class="serv-int">
					<h3>Acompanhar Registro de Atendimento<span>&nbsp;</span></h3>				
					<table  align="left" width="10px">
						 <tr>
					          <td><strong><font>Protocolo: </font></strong><strong></strong></td>
					          <td>
					          	<html:text property="protocolo" size="15" maxlength="15" readonly="true" value="${consultarTramiteHelper.protocolo}" style="background-color:#EFEFEF; border:0;" />
					          </td> 
					          
					          <td width="150"><strong><font>Situação do RA:</font></strong><strong></strong></td>
					          <td>
					          	<p><html:text property="raSituacao" size="15" maxlength="15" readonly="true" value="${consultarTramiteHelper.raSituacao}" style="background-color:#EFEFEF; border:0;" />
					          </td>
	       				 </tr>
	       				 
	       				<tr>
				          <td><strong><font>Tipo de Solicitação:</font></strong></td>
				          <td colspan="3">            
				            <html:text property="idTipoSolicitacao" size="9" maxlength="9" readonly="true" value="${consultarTramiteHelper.idTipoSolicitacao}" style="background-color:#EFEFEF; border:0;" />
				            <html:text property="descricaoTipoSolicitacao" size="90"  readonly="true"	value="${consultarTramiteHelper.descricaoTipoSolicitacao}" style="background-color:#EFEFEF; border:0;" />
				          </td>
				        </tr>  
				        
				        <tr>
				          <td><strong><font>Especificação:</font></strong></td>
				          <td colspan="3">            
				            <html:text property="idEspecificacao" size="9" maxlength="9" readonly="true" value="${consultarTramiteHelper.idEspecificacao}" style="background-color:#EFEFEF; border:0;" />
				            <html:text property="descricaoEspecificacao" size="90"  readonly="true" value="${consultarTramiteHelper.descricaoEspecificacao}"	style="background-color:#EFEFEF; border:0;" />
				          </td>
				        </tr>
	       				
	       				<tr>
				          <td><strong><font>Unidade de Atendimento:</font></strong></td>
				          <td colspan="3">            
				            <html:text property="idUnidadeAtendimento" size="9" maxlength="9" readonly="true" value="${consultarTramiteHelper.idUnidadeAtendimento}"	style="background-color:#EFEFEF; border:0;" />
				            <html:text property="descricaoUnidadeAtendimento" size="90" readonly="true" value="${consultarTramiteHelper.descricaoUnidadeAtendimento}"	style="background-color:#EFEFEF; border:0;" />
				          </td>
				        </tr>
				        
				        <tr>
				          <td><strong><font>Unidade Atual:</font></strong></td>
				          <td colspan="3">            
				            <html:text property="idUnidadeAtual" size="9" maxlength="9" readonly="true"	value="${consultarTramiteHelper.idUnidadeAtual}" style="background-color:#EFEFEF; border:0;" />
				            <html:text property="descricaoUnidadeAtual" size="90"  readonly="true" 	value="${consultarTramiteHelper.descricaoUnidadeAtual}"  style="background-color:#EFEFEF; border:0;" />
				          </td>
				        </tr>
				        
				         <tr>
					           <td><strong><font> Data Atendimento:</font></strong><strong></strong></td>
					           <td>
					           <html:text property="dataAtendimento" size="10" maxlength="10" readonly="true" value="${consultarTramiteHelper.dataAtendimento}" style="background-color:#EFEFEF; border:0;" />
					           </td>
					          	
					           <td><strong><font> Data Encerramento:</font></strong><strong></strong></td>
					           <td>
					           <html:text property="dataEncerramento" size="10" maxlength="10" readonly="true" value="${consultarTramiteHelper.dataEncerramento}"	style="background-color:#EFEFEF; border:0;" />
					           </td>
	       				 </tr>
			        
			        </table>
			        
			        
			        <logic:empty name="colecaoDadosTramite" scope="session">
						<strong> &nbsp;&nbsp;N&atilde;o h&aacute; trâmites para serem consultados</strong>
					</logic:empty>	
					<logic:present name="colecaoDadosTramite" scope="session">				
						<logic:notEmpty name="colecaoDadosTramite" scope="session">
						<table><tr><td>
							<div style="overflow-y:scroll; height: 160px; width :100%;">
							<table width="100%" style="margin-top: 0">
								<thead>
									<tr>
										<th width="50px">Data/Hora</th>									
										<th class="th-tramite" width="130px"> &nbsp;Unidade Destino</th>	
									</tr>
								</thead>							
								<tbody>
								
									<%! int i = 2;%>
									<logic:iterate name="colecaoDadosTramite" type="ConsultarTramiteHelper" id="tramite">							
										<tr class="tr-<%=(i%2) + 1%>" id="tr-<%=(i%2) + 1%>-2">											
											<td class="td-tramite">
												<span style="font-size: 10px;">
													<bean:write name="tramite" property="dataHoraTramite" />
												</span>											 
											</td>										 												
											<td class="td-tramite2">
												<span style="font-size: 10px;">
													<bean:write name="tramite" property="unidadeDestino" />
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
					</logic:present>					  				        				
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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.faturamento.MovimentoContaPrefaturada" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<style type="text/css">
  html { height: 100% }
  body { height: 100%; margin: 0px; padding: 0px }
  #map_canvas { height: 100% }
</style>

<script type="text/javascript"
    src="https://maps.google.com/maps/api/js?sensor=true">
</script>

<script language="JavaScript">

	function fechar(){
		window.close();
	}
	
	function initialize(x, y) {
		var mapOptions = {
		zoom: 10,
		//center: new google.maps.LatLng(-8.035668618572505, -34.88823287577019),
		center: new google.maps.LatLng(x, y), 
		mapTypeId: google.maps.MapTypeId.ROADMAP
		};
		var map = new google.maps.Map(document.getElementById('map_canvas'),
		mapOptions);
		var marker = new google.maps.Marker({
		position: map.getCenter(),
		map: map,
		title: 'Imóvel: Clique para zoom'
		});
		google.maps.event.addListener(map, 'center_changed', function() {
		// 3 seconds after the center of the map has changed, pan back to the
		// marker.
		window.setTimeout(function() {
		map.panTo(marker.getPosition());
		}, 3000);
		});
		google.maps.event.addListener(marker, 'click', function() {
		map.setZoom(16);
		map.setCenter(marker.getPosition());
		});
		
		// Display code in second div tag
	  	document.getElementById('map_canvas').style.display = 'block';
		
	}
		
	//google.maps.event.addDomListener(window, 'load', initialize); 
	
</script>

</head>
<body leftmargin="0" topmargin="0">
<html:form action="/exibirVisualizarCoordenadasImpressaoSimultaneaPopupAction"
	name="ConsultarImovelActionForm"
	type="gcom.gui.cadastro.imovel.ConsultarImovelActionForm"
	method="post">

	<table width="600" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Visualizar Coordenadas Impressão Simultânea</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td width="100%" colspan="3">
						<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%" align="left" bgcolor="#99CCFF">
								<tr bordercolor="#000000">
									<td width="30%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font face="Verdana, Arial, Helvetica, sans-serif" color="#000000" style="font-size:9px"> <strong>Mês/Ano Referência</strong> </font></div>
									</td>
									<td width="35%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font face="Verdana, Arial, Helvetica, sans-serif" color="#000000" style="font-size:9px"> <strong>Coordenada X</strong> </font></div>
									</td>
									<td width="35%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font face="Verdana, Arial, Helvetica, sans-serif" color="#000000" style="font-size:9px"> <strong>Coordenada Y</strong></font></div>
									</td>
								</tr>
								
								<!--corpo da segunda tabela-->
								<%int cont = 0;%>
								<logic:notEmpty name="colecaoMovimento" scope="request">
									<logic:iterate name="colecaoMovimento" id="movimento"
										type="MovimentoContaPrefaturada">
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="30%" align="center"><a href="javascript:initialize(
												<bean:write name="movimento" property="numeroCoordenadaX" />, 
												<bean:write name="movimento" property="numeroCoordenadaY" /> );" >
												<div class="style9"><font color="#000000"
													style="font-size:11px"
													face="Verdana, Arial, Helvetica, sans-serif">
													<bean:write name="movimento"
														property="anoMesReferenciaFormatado" />
												</font></div>
											</a></td>
											<td width="35%" align="center">
											<div class="style9"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
											<bean:write name="movimento"
												property="numeroCoordenadaX" /></td>
		
											<td width="35%" align="center">
											<div class="style9"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
											<bean:write name="movimento"
												property="numeroCoordenadaY" /></td>
										</tr>
									</logic:iterate>
								</logic:notEmpty>
							</table>
						</div>
					</td>
				</tr>
				
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				
				<tr>
					<td>
						<table width="100%" border="0">
							<tr>
								<td align="right"><input name="Button" type="button"
									class="bottonRightCol" value="Fechar"
									onClick="javascript:fechar();">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	</html:form>
<div id="map_canvas" style="width:100%; height:100%; display: none;" ></div>	
	
</body>
</html:html>

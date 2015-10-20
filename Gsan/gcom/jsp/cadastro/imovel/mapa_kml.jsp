<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>Visualizar Mapa</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>OpenLayers.css" type="text/css">
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>OpenLayers.js"></script>

	<style>
		.bloqueado {
			background-color: #888 !important;
			color: #000 !important;
		}
	</style>
<script type="text/javascript">

//variáveis globais
var map;
var marker = null;
var markers;
var XYponto;
var XYmapa;

var longitude;
var latitude;
var longitudeInicial;
var latitudeInicial;
var maxzoom = 18;

function getLonLat(lon, lat){
	var epsg4326 = new OpenLayers.Projection("EPSG:4326");
	var epseg900913 = new OpenLayers.Projection("EPSG:900913");
	if(lon==""||lon==null||lat==""||lat==null)return null;
	return new OpenLayers.LonLat(lon, lat).
	transform(epsg4326, epseg900913);	
} 

function adicionarCamadaMapa(zoomMapa) {
	//definindo centro e zoom inicial
	map = new OpenLayers.Map("mapDiv");

	var layer = new OpenLayers.Layer.OSM("OpenStreetMap");
	layer.wrapDateLine = false;
	map.addLayer(layer);

	if(XYponto) {
		map.setCenter(XYponto, maxzoom);
	} else {
		map.setCenter(XYmapa, zoomMapa);
	}
}

function adicionarCamadaKML(idImovel, paramsMapa){
	var urlMapa = "exibirObterMapaKMLAction.do";
	var kml = new OpenLayers.Layer.Vector("KML", {
        projection: new OpenLayers.Projection("EPSG:4326"),
        strategies: [new OpenLayers.Strategy.Fixed({ preload: false })],
        protocol: new OpenLayers.Protocol.HTTP({
            url: urlMapa,
            params: {'idImovel': idImovel,
                	 'paramsMapa': paramsMapa},
            format: new OpenLayers.Format.KML({
                extractStyles: true,
                extractAttributes: true,
                maxDepth: 2
            })
        }),
        renderers: ["SVG", "VML"]
    });
	map.addLayer(kml);
}

function adicionarCamadaLabels(json){
	var labelLayer = new OpenLayers.Layer.Vector("Labels", { 
        styleMap: labelStyle(),
        renderers: ["SVG", "VML"]
    });
    
	var dados = json;
	for(var i in dados)	{
	     var name = dados[i].name;
	     var lat = dados[i].lat;
	     var lon = dados[i].lon;
	     var XYLabel = getLonLat(lon, lat);
	     labelLayer.addFeatures([criarFeatureLabel(XYLabel, name)]);
	}
	map.addLayer(labelLayer);
}

function labelStyle(){
    var template = {
        label : "\${atributo}",
        fontColor: "black",
        fontSize: "10px",
        fontFamily: "Arial",
        fontWeight: "bold",
        labelAlign: "lt",
        labelOutlineColor: "white",
        labelOutlineWidth: 3
    };
    return(new OpenLayers.StyleMap(new OpenLayers.Style(template)));
}

function criarFeatureLabel(ll, txt){
    var point = new OpenLayers.Geometry.Point(ll.lon, ll.lat);
    var pointFeature = new OpenLayers.Feature.Vector(point,null,null);
    pointFeature.attributes = {};
    pointFeature.attributes["atributo"] = txt;
    return(pointFeature);
}

function adicionarEventosMudancaPosicao(){
	map.events.register("click", map, function (e) {
	  	var ponto = map.getLonLatFromPixel( this.events.getMousePosition(e));
	  	var opx = map.getLayerPxFromViewPortPx(e.xy);
		XYponto = new OpenLayers.LonLat(ponto.lon,ponto.lat).
		transform(new OpenLayers.Projection("EPSG:900913"),new OpenLayers.Projection("EPSG:4326"));
		if(marker) {
			marker.map = map;
			marker.moveTo(opx);
		} else {
			marker = new OpenLayers.Marker(XYponto);
		    markers.addMarker(marker);
		    marker.map = map;
			marker.moveTo(opx);
		}
		latitude = XYponto.lat.toString().replace(".",",");
		longitude = XYponto.lon.toString().replace(".",",");
		opener.redirecionarCoordenadasMapa(latitude, longitude);
	});
}

function adicionarCamadaMarkers(){
	markers = new OpenLayers.Layer.Markers( "Markers" );
    map.addLayer(markers);
    if(XYponto){
	    marker = new OpenLayers.Marker(XYponto);
	    markers.addMarker(marker);
    }
}

function inicializarMapa(idImovel, paramsMapa, zoomMapa, urlServidor,atualizar, lonMapa, latMapa, lonImov, latImov){
	var jsonData =  function () {
		var urlJson = urlServidor+"/exibirObterMapaJSONAction.do?idImovel="+idImovel+"&paramsMapa="+ paramsMapa;
		var tmp = null;
		$.ajax({ 
			async : false,
		    type: 'GET', 
		    url: urlJson, 
		    data: { idImovel: idImovel }, 
		    dataType: 'json',
		    success: function (data) { 
		    	tmp = data;
		    }
		});
		return tmp;
	}();

	latitudeInicial = latImov;
	longitudeInicial = lonImov;
	
	XYponto = getLonLat(lonImov, latImov);
	XYmapa = getLonLat(lonMapa, latMapa);
	
	//desabilitando botão direito do mouse
	document.oncontextmenu = function(){return false};

	//adicionando camadas de visualização do mapa
	adicionarCamadaMapa(zoomMapa);
	adicionarCamadaKML(idImovel, paramsMapa);
	adicionarCamadaLabels(jsonData);
	adicionarCamadaMarkers();
	
	//caso seja atualizacao
	if(atualizar){
		adicionarEventosMudancaPosicao();
	}

	map.events.register('zoomend', map, function (e) {
		bloquearLiberarBotoes();
	});

	bloquearLiberarBotoes();
}

function bloquearLiberarBotoes() {
	var zoomIn = $('.olControlZoomIn');
	var zoomOut = $('.olControlZoomOut');

	if(map.zoom === maxzoom) {
		zoomIn.addClass('bloqueado');
		zoomOut.removeClass('bloqueado');
	} else if(map.zoom === 0) {
		zoomIn.removeClass('bloqueado');
		zoomOut.addClass('bloqueado');
	} else {
		zoomIn.removeClass('bloqueado');
		zoomOut.removeClass('bloqueado');
	}
}

function concluir(){
	self.close();
}

function cancelar(){
	var atualizar = '' + '${atualizar}';
	if(atualizar=='true'){
		opener.redirecionarCoordenadasMapa(latitudeInicial, longitudeInicial);
		window.close();
	}else{
		self.close();
	}
}

</script>
</head>       

<body onload='inicializarMapa("<c:out value="${idImovel}"></c:out>",
							  "<c:out value="${paramsMapa}"></c:out>",
							  "<c:out value="${zoomMapa}"></c:out>",
							  "<c:out value="${urlServidor}"></c:out>",
							  "<c:out value="${atualizar}"></c:out>",
							  "<c:out value="${lonMapa}"></c:out>",
							  "<c:out value="${latMapa}"></c:out>",
							  "<c:out value="${lonImov}"></c:out>",
							  "<c:out value="${latImov}"></c:out>");'>
							  
	<table width="600" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Visualizar Coordenadas</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<div class="olMap" id="mapDiv" style="width:600px; height:500px;"></div>
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td align="left">
				<logic:present name="voltar" scope="request">
					<input type="button" value="Voltar" class="bottonRightCol" onclick="window.history.back();" >
				</logic:present>
	
				<logic:notPresent name="voltar" scope="request">
					<input type="button" value="Cancelar" class="bottonRightCol" onclick="cancelar();" >
				</logic:notPresent>
			</td>

			<td align="right">
				<logic:present name="atualizar" scope="request">
					<input type="button" value="Concluir" class="bottonRightCol" onclick="concluir();">
				</logic:present>
			</td>
		</tr>
	</table>
</body>
</html>
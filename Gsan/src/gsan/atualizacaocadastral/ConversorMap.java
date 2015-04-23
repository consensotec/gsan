package gsan.atualizacaocadastral;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.map.writer.MapFileWriter;
import org.mapsforge.map.writer.RAMTileBasedDataProcessor;
import org.mapsforge.map.writer.model.MapWriterConfiguration;
import org.mapsforge.map.writer.model.ZoomIntervalConfiguration;
import org.mapsforge.map.writer.util.Constants;
import org.openstreetmap.osmosis.core.domain.v0_6.CommonEntityData;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.OsmUser;
import org.openstreetmap.osmosis.core.domain.v0_6.Tag;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;
import org.openstreetmap.osmosis.core.domain.v0_6.WayNode;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Realiza a conversão do formato KMZ para MAP(mapsforge).<br/>
 * @author André Miranda
 */
public class ConversorMap {
	private static final String STYLE_LOTE = "footway";
	private static final String STYLE_EDIFICACAO = "pedestrian";
	private static final String STYLE_PISCINA = "path";
	private static final String STYLE_NUMEROLOTE = "service";
	
	private long id = 1;
	private double[] bounds = {90, 90, -90, -90};
	private final Date date = new Date();
	private final OsmUser user = new OsmUser(1, "");
	private RAMTileBasedDataProcessor dataProcessor = null;

	public double[] converter(InputStream fileKmz, File mapFile, File kmlFile, StringBuilder json) throws Exception {
		String expression;
		NodeList nodeList;
		double[] retorno = {0d, 0d};
		XPath xPath = XPathFactory.newInstance().newXPath();

		XPathExpression expName = xPath.compile(".//name");
		XPathExpression expColor = xPath.compile(".//color");
		XPathExpression expCoordinates = xPath.compile(".//coordinates");

		Map<String, String> cache = new HashMap<String, String>();
		Map<String, String> cores =  new HashMap<String, String>();
		cores.put("fffe0000", STYLE_LOTE); // Lote: fffe0000 -> ff 0000fe
		cores.put("fffe00fe", STYLE_EDIFICACAO); // Edificação: fffe00fe -> ff fe00fe
		cores.put("fffefe00", STYLE_PISCINA); // Piscina: fffefe00 -> ff 00fefe
		cores.put("fffefefe", STYLE_NUMEROLOTE);// Número Lote: fffefefe -> ff fefefe

		boolean arquivoEncontrado = obterArquivoKml(fileKmz, kmlFile);
 		if(!arquivoEncontrado) {
 			throw new IllegalStateException("Arquivo KML não encontrado no KMZ");
 		}

		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(kmlFile);
		doc.getDocumentElement().normalize();

		// Determinar os limites do setor
		expression = "*//coordinates";
		nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

		for (int i = 0; i < nodeList.getLength(); i++) {
			String coords = nodeList.item(i).getFirstChild().getNodeValue().trim();
			computeBounds(bounds, parseCoords(coords));
		}
		BoundingBox bb = new BoundingBox(bounds[0], bounds[1], bounds[2], bounds[3]);
		retorno[0] = bb.getCenterPoint().latitude; 
		retorno[1] = bb.getCenterPoint().longitude; 

		// Configurar a escrita do mapa
		MapWriterConfiguration config = new MapWriterConfiguration();
		config.setOutputFile(mapFile);
		config.setWriterVersion("");
		config.setFileSpecificationVersion(3);

		config.loadTagMappingFile(null);
		config.setDebugStrings(false);
		config.setPolygonClipping(true);
		config.setWayClipping(false);
		config.setLabelPosition(false);
		config.setSkipInvalidRelations(true);

		config.setSimplification(Constants.DEFAULT_SIMPLIFICATION_FACTOR);
		config.setDataProcessorType(Constants.DEFAULT_PARAM_TYPE);
		config.setBboxEnlargement(Constants.DEFAULT_PARAM_BBOX_ENLARGEMENT);
		config.addEncodingChoice(Constants.DEFAULT_PARAM_ENCODING);

		config.setZoomIntervalConfiguration(ZoomIntervalConfiguration.getStandardConfiguration());
		config.setBboxConfiguration(new BoundingBox(bounds[0], bounds[1], bounds[2], bounds[3]));

		dataProcessor = RAMTileBasedDataProcessor.newInstance(config);

		// Processar as linhas
		expression = "//Folder[contains(name, 'Polyline') or contains(name, 'Line')]/Placemark";
		nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			org.w3c.dom.Node placemark = nodeList.item(i);

			String color = ((String) expColor.evaluate(placemark, XPathConstants.STRING)).trim();
			String style = getStyle(color, cores, cache);
			NodeList coordList = (NodeList) expCoordinates.evaluate(placemark, XPathConstants.NODESET);
			for (int j = 0; j < coordList.getLength(); j++) {
				String coords = coordList.item(j).getFirstChild().getNodeValue().trim();
				addNodes(parseCoords(coords), dataProcessor, style);
			}
		}
		
		// Processar os pontos (números dos lotes)
		json.append("[");
		expression = "//Folder[contains(name, 'Text')]/Placemark";
		nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			org.w3c.dom.Node placemark = nodeList.item(i);

			String coords = ((String) expCoordinates.evaluate(placemark, XPathConstants.STRING)).trim();
			String name = ((String) expName.evaluate(placemark, XPathConstants.STRING)).trim();

			Coordinate coord = parseCoords(coords).get(0);

			Node node = new Node(new CommonEntityData(id++, 1, date, user, 1), coord.latitude, coord.longitude);
			node.getTags().add(new Tag("name", name));
			node.getTags().add(new Tag("amenity", "pub"));
			dataProcessor.addNode(node);
			
			json.append(String.format("{\"name\":\"%s\",\"lat\":%s,\"lon\":%s},",
					name, coord.latitude, coord.longitude));
		}

		// Remover última vírgula
		if(json.length() > 1) json.deleteCharAt(json.length() - 1);

		// Finalizar JSON
		json.append("]");

		// Limpar arquivo de saída
		if (config.getOutputFile().exists()) {
			config.getOutputFile().delete();
		}

		// Realizar o processamento
		dataProcessor.complete();

		CustomMapWriter.prepare();
		CustomMapWriter.writeFile(config, dataProcessor);
		CustomMapWriter.release();

		return retorno;
	}

	/**
	 * Obtem a partir do arquivo KMZ, o arquivo KML
	 * 
	 * @param kmzFile
	 *            Formfile referente ao Arquivo KMZ informado pelo usuário
	 * @param kmlFile
	 *            File instanciado, onde será gravado o conteúdo do KML
	 * 
	 * @return Indica se foi encontrado o arquivo KML dentro do KMZ
	 * @throws Exception
	 */
	private boolean obterArquivoKml(InputStream kmzFile, File kmlFile) throws Exception {
		int length;
		byte[] bytes = new byte[1024];
		ZipInputStream stream = null;
		FileOutputStream output = null;

		try {
			stream = new ZipInputStream(kmzFile);
			ZipEntry entry = stream.getNextEntry();

			while (entry != null) {
				String filename = entry.getName();

				if(filename != null && filename.toLowerCase().endsWith(".kml")) {
					output = new FileOutputStream(kmlFile);
					while ((length = stream.read(bytes)) >= 0) {
						output.write(bytes, 0, length);
					}

					return true;
				}

				stream.closeEntry();
				entry = stream.getNextEntry();
			}
		} finally {
			try { stream.close(); } catch (Exception ignorar) {}
			try { output.close(); } catch (Exception ignorar) {}
		}

		return false;
	}

	/**
	 * Adiciona os <code>Nodes</code> e o seu respectivo <code>Way</code> no
	 * <code>DataProcessor</code>.
	 * 
	 * @param coords
	 *            Lista com coordenadas
	 * @param dataProcessor
	 *            DataProcessor já configurado
	 * @param style
	 *            Estilo a ser usado por este <code>Way</code>
	 */
	private void addNodes(List<Coordinate> coords, RAMTileBasedDataProcessor dataProcessor, String style) {
		Way way = new Way(new CommonEntityData(id++, 1, date, user, 1));
		way.getTags().add(new Tag("highway", style));

		// Caso seja um Node para Lote, indicar layer
		// fazendo com que fique acima dos outros elementos
		if(style.equals(STYLE_LOTE)) {
			way.getTags().add(new Tag("layer", "5"));
		}

		for (Coordinate c: coords) {
			Node node = new Node(new CommonEntityData(id++, 1, date, user, 1), c.latitude, c.longitude);
			way.getWayNodes().add(new WayNode(node.getId()));
			dataProcessor.addNode(node);
		}
		dataProcessor.addWay(way);
	}

	/**
	 * Transforma as coordenadas presentes numa string em um lista.
	 * 
	 * @param coords
	 *            String que contém multiplos pares(ou trincas) de coordenadas
	 *            separadas por vírgula
	 * @return Lista com coordenadas
	 */
	private List<Coordinate> parseCoords(String coords) {
		List<Coordinate> list = new ArrayList<Coordinate>();

		String[] c = coords.trim().replaceAll("\\s+", " ").split(" ");
		for (String s : c) {
			String[] c2 = s.split(",");
			if(c2 == null || c2.length < 3) {
				continue;
			}

			double lat = Double.parseDouble(c2[1].trim());
			double lon = Double.parseDouble(c2[0].trim());

			if(lat != 0d || lon != 0d) {
				list.add(new Coordinate(lat, lon));
			}
		}

		return list;
	}

	/**
	 * Calcula o limite de uma área com base nas coordenadas recebidas
	 * sempre mantendo as máximas e as mínimas.
	 * 
	 * @param bounds Array onde será guardado o limite de uma área.
	 * @param coords Coordenadas a serem processadas
	 */
	private void computeBounds(double[] bounds, List<Coordinate> coords) {
		if (coords == null) {
			return;
		}

		for (int i = 1; i < coords.size(); i++) {
			double lat = coords.get(i).latitude;
			double lon = coords.get(i).longitude;

			bounds[0] = lat < bounds[0] ? lat : bounds[0]; // latMin
			bounds[1] = lon < bounds[1] ? lon : bounds[1]; // lonMin
			bounds[2] = lat > bounds[2] ? lat : bounds[2]; // latMax
			bounds[3] = lon > bounds[3] ? lon : bounds[3]; // lonMax
		}
	}

	/**
	 * Realiza aproximação das cores com base em <code>cores</code> para
	 * tentar determinar o estilo.
	 * 
	 * @param cor Cor a ser realizada a aproximação
	 * @param cores Mapa com as cores e estilos previstos
	 * @param cache Cache das cores já aproximadas e com estilos determinados
	 * @return
	 */
	private String getStyle(String cor, Map<String, String> cores, Map<String, String> cache) {
		String style = cache.get(cor);
		if(style != null) return style;

		for (String corElemento : cores.keySet()) {
			if(comparaCor(corElemento, cor, 10)) {
				style = cores.get(corElemento);
				break;
			}
		}

		if(style == null) {
			style = "unclassified";
		}

		cache.put(cor, style);

		return style;
	}

	/**
	 * Comparas duas cores com mediante uma <code>tolerância</code>.</br> As
	 * cores devem estar no formato [AA]BBGGRR.
	 * 
	 * @param cor1
	 * @param cor2
	 * @param tolerancia
	 *            Quanto cada canal pode variar entre 0-255
	 * @return
	 */
	private boolean comparaCor(String cor1, String cor2, int tolerancia) {
		int c1, c2, rdiff, gdiff, bdiff;

		try {
			if (cor1 == null || cor2 == null) {
				return false;
			}

			cor1 = cor1.toUpperCase().replaceAll("[^0-9ABCDEF]", "");
			cor2 = cor2.toUpperCase().replaceAll("[^0-9ABCDEF]", "");

			// Remover alpha
			if (cor1.length() == 8) cor1 = cor1.substring(2);
			if (cor2.length() == 8) cor2 = cor2.substring(2);

			if (cor1.length() != 6 || cor2.length() != 6) {
				return false;
			}

			c1 = Integer.parseInt(cor1.substring(0, 2), 16);
			c2 = Integer.parseInt(cor2.substring(0, 2), 16);
			bdiff = Math.abs(c1 - c2);

			c1 = Integer.parseInt(cor1.substring(2, 4), 16);
			c2 = Integer.parseInt(cor2.substring(2, 4), 16);
			gdiff = Math.abs(c1 - c2);

			c1 = Integer.parseInt(cor1.substring(4, 6), 16);
			c2 = Integer.parseInt(cor2.substring(4, 6), 16);
			rdiff = Math.abs(c1 - c2);

			return rdiff <= tolerancia && gdiff <= tolerancia && bdiff <= tolerancia;
		} catch (Exception ignorar) { }

		return false;
	}

	/**
	 * Classe utilizada apenas como um helper para a conversão.
	 */
	private static class Coordinate {
		public double latitude;
		public double longitude;

		public Coordinate(double latitude, double longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
		}
	}

	/**
	 * Modificação do writer do mapsforge que permite a liberação dos recursos alocados 
	 */
	private static class CustomMapWriter extends MapFileWriter {
		/**
		 * Cleans up thread pool. Must only be called at the end of processing.
		 */
		public static void release() {
			EXECUTOR_SERVICE.shutdown();
		}

		/**
		 * If the writer needs to be used again after release, the executor needs to be recreated.
		 */
		public static void prepare() {
			if(EXECUTOR_SERVICE.isShutdown() || EXECUTOR_SERVICE.isTerminated()) {
				EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime()
						.availableProcessors());
			}
		}
	}
}

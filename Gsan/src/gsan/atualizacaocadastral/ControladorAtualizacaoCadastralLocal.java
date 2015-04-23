package gsan.atualizacaocadastral;

import gsan.util.ControladorException;

import java.io.File;
import java.io.InputStream;

/**
 * @author Bruno Sá Barreto
 * @date 24/02/2015
 */
public interface ControladorAtualizacaoCadastralLocal extends javax.ejb.EJBLocalObject{

	/**
	 * [UC1647] - Exibir Mapa com Marcação da Coordenada
	 * Recebe o idImovel, verifica a localidade, quadra e setor 
	 * comercial e filtra o MapaAtualizacaoCadastralDM
	 * 
	 * @author Bruno Sá Barreto
	 * @date 25/02/2015
	 *
	 * @param idImovel
	 * @return MapaAtualizacaoCadastralDM
	 */
	public MapaAtualizacaoCadastralDM obterArquivoMapaQuadraImovel(Integer idImovel) throws ControladorException;

	/**
	 * Realiza a conversão do mapa no formato para KMZ para o formato MAP utilizado pelo dispositivo móvel.<br>
	 * Também gera um JSON com informações dos pontos(números dos lotes).
	 * 
	 * @param kmzFile
	 * @param mapFile
	 * @param kmlFile
	 * @param json
	 * @return Latitude(0) e longitude(1) do centro do mapa
	 * @throws ControladorException
	 */
	public double[] converterArquivoMap(InputStream kmzFile, File mapFile, File kmlFile, StringBuilder json)
			throws ControladorException;
	/**
	 * [UC1647] - Exibir Mapa com Marcação da Coordenada
	 * Este método recebe uma string, que são os parametros
	 * utilizados para pesquisar o mapa caso não exista
	 * id do imovel para pesquisar o mesmo. 
	 * 
	 * @author Bruno Sá Barreto
	 * @date 25/02/2015
	 *
	 * @param paramsMapa - formato:(LOCA_ID,CD_SETOR,N_QUADRA)
	 * @return MapaAtualizacaoCadastralDM
	 */
	public MapaAtualizacaoCadastralDM obterArquivoMapaQuadraPorParametros(
			String paramsMapa) throws ControladorException;
	
	/**
	 * [UC1392] - Consultar Roteiro Dispositivo Móvel Atualização Cadastral
	 * [IT0008] - Baixar Arquivo Texto e Mapas
	 * Utilizando o arquivoTexto que foi passado como parametro, são consultados
	 * os mapas das quadras do setor e localidade utilizando os dados dentro de
	 * ParametroTabelaAtlzCadastralDM contido no mesmo e montado o arquivo com
	 * os mapas das quadras no formato .map juntamente do .txt do roteiro
	 *
	 * @author Bruno Sá Barreto
	 * @date 27/02/2015
	 *
	 * @param arquivoTexto
	 * @return byte[] - arquivo zip com o txt e os .maps das quadras
	 */
	public byte[] montarArquivoZipComMapasQuadras(
			ArquivoTextoAtualizacaoCadastralDM arquivoTexto) throws ControladorException;
	
	/**
	 * [UC1391] - Gerar Roteiro Dispositivo Móvel Atualização Cadastral
	 * [FE0003] - Verificar Falta de Mapa Para Imóvel Selecionado
	 * Este método pesquisa os numeros das quadras dos imoveis que não 
	 * possuem mapas.
	 * 
	 * @param idsRegistros - ids dos imóveis que serão verificados.
	 * @return String - numeros das quadras separados por virgula. 
	 */
	public String verificarQuadrasSemMapaPorImoveis(String[] idsRegistros) throws ControladorException;
}

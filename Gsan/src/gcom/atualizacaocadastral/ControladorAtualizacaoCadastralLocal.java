package gcom.atualizacaocadastral;

import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

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
	 * [FE0003] - Verificar Falta de Mapa Para Quadra Selecionada
	 * Este método pesquisa os as quadras que não possuem mapas.
	 * 
	 * @param idsQuadras - conjunto das quadras a serem pesquisadas.
	 * @return String - numeros das quadras separados por virgula.
	 */
	public String verificarQuadrasSemMapaPorImoveis(Collection<Integer> idsQuadras) throws ControladorException;

	/**
	 * Método responsável pela pesquisa de imóveis que possuem fotos na atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 22/06/2015
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<ImovelAtualizacaoCadastralDM> pesquisarImovelComFotoAtualizacaoCadastralDM(Integer idImovel)
			throws ControladorException;

	/**
	 * Método responsável pela geração dos dados financeiros da atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 10/07/2015
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarDadosFinanceirosAtualizacaoCadastral(Integer idFuncionalidadeIniciada, Integer comando)
			throws ControladorException;

	/**
	 * Método responsável pela geração do resumo dos dados financeiros da atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 10/07/2015
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoDadosFinanceirosAtualizacaoCadastral(Integer idFuncionalidadeIniciada, Integer comando)
			throws ControladorException;
	
	/**
	 * [UC0000] - Gerar Dados Financeiros da Atualização Cadastral 
	 * @author Vivianne Sousa
	 * @date 23/07/2015
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarParametroTabelaAtualizacaoCadastralNaoFinalizado() throws ControladorException; 
}

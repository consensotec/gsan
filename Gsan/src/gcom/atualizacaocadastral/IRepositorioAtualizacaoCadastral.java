package gcom.atualizacaocadastral;

import gcom.atualizacaocadastral.bean.MapaQuadraHelper;
import gcom.util.ErroRepositorioException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Bruno Sá Barreto
 * @date 24/02/2015
 */
public interface IRepositorioAtualizacaoCadastral {
	/**
	 * [UC1392] - Consultar Roteiro Dispositivo Móvel Atualização Cadastral
	 * [IT0008] - Baixar Arquivo Texto e Mapas
	 * Retorna uma coleção de tuplas com nome e arquivo map da quadra
	 *
	 * @author Bruno Sá Barreto
	 * @date 27/02/2015
	 *
	 * @param idLocalidade
	 * @param codigoSetorComercial
	 * @return ArrayList<MapaQuadraHelper> - tuplas com nome e arquivo map da quadra
	 */
	public ArrayList<MapaQuadraHelper> pesquisarMapasQuadrasPorLocalidadeSetor(
		Integer idLocalidade, Integer codigoSetorComercial) throws ErroRepositorioException;

	/**
	 * [UC1391] - Gerar Roteiro Dispositivo Móvel Atualização Cadastral
	 * [FE0003] - Verificar Falta de Mapa Para Quadra Selecionada
	 * Este método pesquisa os as quadras que não possuem mapas.
	 * 
	 * @param idsQuadras - conjunto das quadras a serem pesquisadas.
	 * @return String - numeros das quadras separados por virgula.
	 */
	public List<String> verificarQuadrasSemMapaPorImoveis(Collection<Integer> idsQuadras) throws Exception;

	/**
	 * Método responsável pela pesquisa de imóveis que possuem fotos na atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 22/06/2015
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<ImovelAtualizacaoCadastralDM> pesquisarImovelComFotoAtualizacaoCadastralDM(Integer idImovel) throws ErroRepositorioException;

	/**
	 * Método responsável pela pesquisa dos dados financeiros da atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 10/07/2015
	 * @param comando
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<DadosFinanceirosAtualizacaoCadastralDM> pesquisarDadosFinanceirosAtualizacaoCadastralDM(
		Integer comando) throws ErroRepositorioException;

	/**
	 * Método responsável por pesquisar o ID da dimensão localização na base do pentaho.
	 * 
	 * @author André Miranda
	 * @since 21/07/2015
	 * @param idLocalidade
	 * @param cdSetorComercial
	 * @param nnQuadra
	 * @return ID da dimensão localização
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLocalizacao(Integer idLocalidade, Integer cdSetorComercial, Integer nnQuadra)
		throws ErroRepositorioException;

	/**
	 * Método responsável pela pesquisa do resumo dados financeiros da atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 22/07/2015
	 * @param comando
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<ResumoDadosFinanceirosAtualizacaoCadastralDM> pesquisarResumoDadosFinanceirosAtualizacaoCadastralDM(
		Integer comando)	throws ErroRepositorioException;
	
	
	/**
	 * [UC0000] - Gerar Dados Financeiros da Atualização Cadastral 
	 * @author Vivianne Sousa
	 * @date 23/07/2015
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarParametroTabelaAtualizacaoCadastralNaoFinalizado() throws ErroRepositorioException;
	
	/**
	 * Método responsável por excluir os dados do comando na base do pentaho.
	 * @author Vivianne Sousa
	 * @since 23/07/2015
	 * @param comando
	 * @throws ErroRepositorioException
	 */
	public void excluirDadosComando(Integer comando) throws ErroRepositorioException;

	/**
	 * Método responsável por excluir os resumos do comando na base do pentaho.
	 * @author Vivianne Sousa
	 * @since 23/07/2015
	 * @param comando
	 * @throws ErroRepositorioException
	 */
	public void excluirResumoDadosComando(Integer comando) throws ErroRepositorioException;
	
	/**
	 * Método responsável por pesquisar o ID da dimensão situação de água na base do pentaho.
	 * @author Vivianne Sousa
	 * @since 23/07/2015
	 * @param idSituacaoLigacaoAgua
	 * @return ID da dimensão situação de água
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdSituacaoAgua(Integer idSituacaoLigacaoAgua) throws ErroRepositorioException; 
	
	/**
	 * Método responsável por pesquisar o ID da dimensão situação de esgoto na base do pentaho.
	 * 
	 * @author Vivianne Sousa
	 * @since 23/07/2015
	 * @param idSituacaoLigacaoEsgoto
	 * @return ID da dimensão situação de esgoto
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdSituacaoEsgoto(Integer idSituacaoLigacaoEsgoto) throws ErroRepositorioException;
	
	/**
	 * Método responsável por pesquisar o ID da dimensão tempo na base do pentaho.
	 * 
	 * @author Vivianne Sousa
	 * @since 24/07/2015
	 * @param dataGeracao
	 * @return ID da dimensão tempo
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdTempo(Date dataGeracao) throws ErroRepositorioException;
	
	/**
	 * Método responsável por pesquisar o ID da dimensão usuário na base do pentaho.
	 * 
	 * @author Vivianne Sousa
	 * @since 24/07/2015
	 * @param idUsuario
	 * @return ID da dimensão usuário
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdUsuario(Integer idUsuario) throws ErroRepositorioException;
	
	/**
	 * Método responsável por verificar se comando esta finalizado.
	 * 
	 * @author Vivianne Sousa
	 * @since 24/07/2015
	 * @param comando
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarComandoFinalizado(Integer comando) throws ErroRepositorioException;
	
	/**
	 * Método responsável por atualizar indicador de comando finalizado.
	 * 
	 * @author Vivianne Sousa
	 * @since 24/07/2015
	 * @param comando
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorComandoFinalizado(Integer comando) throws ErroRepositorioException;
	
	/**
	 * Método responsável por pesquisar o ID da dimensão tarifa na base do pentaho.
	 * 
	 * @author Vivianne Sousa
	 * @since 31/07/2015
	 * @param idTarifa
	 * @return ID da dimensão tarifa
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdTarifa(Integer idTarifa)throws ErroRepositorioException;
	
	/**
	 * Método responsável por pesquisar somatório dos dados financeiro da atualização cadastral  
	 * 
	 * @author Vivianne Sousa
	 * @since 26/08/2015
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosFinanceirosAtuCadastral(ResumoDadosFinanceirosAtualizacaoCadastralDM resumo, 
			Map<Integer, Integer> cacheSitAgua, Map<Integer, Integer> cacheSitEsgoto)throws ErroRepositorioException;

}

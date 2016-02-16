package gcom.atualizacaocadastral;

import gcom.atualizacaocadastral.bean.MapaQuadraHelper;
import gcom.util.ErroRepositorioException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Bruno S� Barreto
 * @date 24/02/2015
 */
public interface IRepositorioAtualizacaoCadastral {
	/**
	 * [UC1392] - Consultar Roteiro Dispositivo M�vel Atualiza��o Cadastral
	 * [IT0008] - Baixar Arquivo Texto e Mapas
	 * Retorna uma cole��o de tuplas com nome e arquivo map da quadra
	 *
	 * @author Bruno S� Barreto
	 * @date 27/02/2015
	 *
	 * @param idLocalidade
	 * @param codigoSetorComercial
	 * @return ArrayList<MapaQuadraHelper> - tuplas com nome e arquivo map da quadra
	 */
	public ArrayList<MapaQuadraHelper> pesquisarMapasQuadrasPorLocalidadeSetor(
		Integer idLocalidade, Integer codigoSetorComercial) throws ErroRepositorioException;

	/**
	 * [UC1391] - Gerar Roteiro Dispositivo M�vel Atualiza��o Cadastral
	 * [FE0003] - Verificar Falta de Mapa Para Quadra Selecionada
	 * Este m�todo pesquisa os as quadras que n�o possuem mapas.
	 * 
	 * @param idsQuadras - conjunto das quadras a serem pesquisadas.
	 * @return String - numeros das quadras separados por virgula.
	 */
	public List<String> verificarQuadrasSemMapaPorImoveis(Collection<Integer> idsQuadras) throws Exception;

	/**
	 * M�todo respons�vel pela pesquisa de im�veis que possuem fotos na atualiza��o cadastral.
	 * 
	 * @author Andr� Miranda
	 * @since 22/06/2015
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<ImovelAtualizacaoCadastralDM> pesquisarImovelComFotoAtualizacaoCadastralDM(Integer idImovel) throws ErroRepositorioException;

	/**
	 * M�todo respons�vel pela pesquisa dos dados financeiros da atualiza��o cadastral.
	 * 
	 * @author Andr� Miranda
	 * @since 10/07/2015
	 * @param comando
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<DadosFinanceirosAtualizacaoCadastralDM> pesquisarDadosFinanceirosAtualizacaoCadastralDM(
		Integer comando) throws ErroRepositorioException;

	/**
	 * M�todo respons�vel por pesquisar o ID da dimens�o localiza��o na base do pentaho.
	 * 
	 * @author Andr� Miranda
	 * @since 21/07/2015
	 * @param idLocalidade
	 * @param cdSetorComercial
	 * @param nnQuadra
	 * @return ID da dimens�o localiza��o
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLocalizacao(Integer idLocalidade, Integer cdSetorComercial, Integer nnQuadra)
		throws ErroRepositorioException;

	/**
	 * M�todo respons�vel pela pesquisa do resumo dados financeiros da atualiza��o cadastral.
	 * 
	 * @author Andr� Miranda
	 * @since 22/07/2015
	 * @param comando
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<ResumoDadosFinanceirosAtualizacaoCadastralDM> pesquisarResumoDadosFinanceirosAtualizacaoCadastralDM(
		Integer comando)	throws ErroRepositorioException;
	
	
	/**
	 * [UC0000] - Gerar Dados Financeiros da Atualiza��o Cadastral 
	 * @author Vivianne Sousa
	 * @date 23/07/2015
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarParametroTabelaAtualizacaoCadastralNaoFinalizado() throws ErroRepositorioException;
	
	/**
	 * M�todo respons�vel por excluir os dados do comando na base do pentaho.
	 * @author Vivianne Sousa
	 * @since 23/07/2015
	 * @param comando
	 * @throws ErroRepositorioException
	 */
	public void excluirDadosComando(Integer comando) throws ErroRepositorioException;

	/**
	 * M�todo respons�vel por excluir os resumos do comando na base do pentaho.
	 * @author Vivianne Sousa
	 * @since 23/07/2015
	 * @param comando
	 * @throws ErroRepositorioException
	 */
	public void excluirResumoDadosComando(Integer comando) throws ErroRepositorioException;
	
	/**
	 * M�todo respons�vel por pesquisar o ID da dimens�o situa��o de �gua na base do pentaho.
	 * @author Vivianne Sousa
	 * @since 23/07/2015
	 * @param idSituacaoLigacaoAgua
	 * @return ID da dimens�o situa��o de �gua
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdSituacaoAgua(Integer idSituacaoLigacaoAgua) throws ErroRepositorioException; 
	
	/**
	 * M�todo respons�vel por pesquisar o ID da dimens�o situa��o de esgoto na base do pentaho.
	 * 
	 * @author Vivianne Sousa
	 * @since 23/07/2015
	 * @param idSituacaoLigacaoEsgoto
	 * @return ID da dimens�o situa��o de esgoto
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdSituacaoEsgoto(Integer idSituacaoLigacaoEsgoto) throws ErroRepositorioException;
	
	/**
	 * M�todo respons�vel por pesquisar o ID da dimens�o tempo na base do pentaho.
	 * 
	 * @author Vivianne Sousa
	 * @since 24/07/2015
	 * @param dataGeracao
	 * @return ID da dimens�o tempo
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdTempo(Date dataGeracao) throws ErroRepositorioException;
	
	/**
	 * M�todo respons�vel por pesquisar o ID da dimens�o usu�rio na base do pentaho.
	 * 
	 * @author Vivianne Sousa
	 * @since 24/07/2015
	 * @param idUsuario
	 * @return ID da dimens�o usu�rio
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdUsuario(Integer idUsuario) throws ErroRepositorioException;
	
	/**
	 * M�todo respons�vel por verificar se comando esta finalizado.
	 * 
	 * @author Vivianne Sousa
	 * @since 24/07/2015
	 * @param comando
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarComandoFinalizado(Integer comando) throws ErroRepositorioException;
	
	/**
	 * M�todo respons�vel por atualizar indicador de comando finalizado.
	 * 
	 * @author Vivianne Sousa
	 * @since 24/07/2015
	 * @param comando
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorComandoFinalizado(Integer comando) throws ErroRepositorioException;
	
	/**
	 * M�todo respons�vel por pesquisar o ID da dimens�o tarifa na base do pentaho.
	 * 
	 * @author Vivianne Sousa
	 * @since 31/07/2015
	 * @param idTarifa
	 * @return ID da dimens�o tarifa
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdTarifa(Integer idTarifa)throws ErroRepositorioException;
	
	/**
	 * M�todo respons�vel por pesquisar somat�rio dos dados financeiro da atualiza��o cadastral  
	 * 
	 * @author Vivianne Sousa
	 * @since 26/08/2015
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosFinanceirosAtuCadastral(ResumoDadosFinanceirosAtualizacaoCadastralDM resumo, 
			Map<Integer, Integer> cacheSitAgua, Map<Integer, Integer> cacheSitEsgoto)throws ErroRepositorioException;

}

package gcom.atualizacaocadastral;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.ControladorLigacaoEsgotoLocal;
import gcom.atendimentopublico.ligacaoesgoto.ControladorLigacaoEsgotoLocalHome;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atualizacaocadastral.bean.MapaQuadraHelper;
import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.RepositorioCadastroHBM;
import gcom.cadastro.cliente.IRepositorioCliente;
import gcom.cadastro.cliente.RepositorioClienteHBM;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.RepositorioImovelHBM;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.IRepositorioUtil;
import gcom.util.RepositorioUtilHBM;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * @author Bruno Sá Barreto
 * @date 24/02/2015
 */
public class ControladorAtualizacaoCadastralSEJB implements SessionBean {

	private static final long serialVersionUID = 1L;
	SessionContext sessionContext;

	//repositorios
	private IRepositorioAtualizacaoCadastral repositorioAtualizacaoCadastral;
	private IRepositorioImovel repositorioImovel;
	private IRepositorioCliente repositorioCliente;
	private IRepositorioCadastro repositorioCadastro;
	private IRepositorioUtil repositorioUtil;
	
	/**
	 * Inicializa os repositorios que são referenciados no controlador
	 * @exception CreateException
	 */
	public void ejbCreate() throws CreateException {
		repositorioImovel = RepositorioImovelHBM.getInstancia();
		repositorioCadastro = RepositorioCadastroHBM.getInstancia();
		repositorioCliente = RepositorioClienteHBM.getInstancia();
		repositorioAtualizacaoCadastral = RepositorioAtualizacaoCadastralHBM.getInstancia();
		repositorioUtil = RepositorioUtilHBM.getInstancia();
	}
	
	public void ejbRemove() {}
	public void ejbActivate() {}
	public void ejbPassivate() {}

	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
	
	/**
	 * Retorna o valor de controladorUtil
	 * @return O valor de controladorUtil
	 */
	protected ControladorUtilLocal getControladorUtil() {
		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			local = localHome.create();
			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna o valor de controladorBatch
	 * @return O valor de controladorBatch
	 */
	protected ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorBatchLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Retorna o valor de controladorFaturamento
	 * @return O valor de controladorFaturamento
	 */
	protected ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Retorna o valor de controladorImovel
	 * @return O valor de controladorImovel
	 */
	protected ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna o valor de controladorMicromedicao
	 * @return O valor de controladorMicromedicao
	 */
	protected ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorMicromedicaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna o valor de controladorMicromedicao
	 * @return O valor de controladorMicromedicao
	 */
	protected ControladorLigacaoEsgotoLocal getControladorLigacaoEsgoto() {
		ControladorLigacaoEsgotoLocalHome localHome = null;
		ControladorLigacaoEsgotoLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorLigacaoEsgotoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_LIGACAO_ESGOTO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna o valor de controladorMicromedicao
	 * @return O valor de controladorMicromedicao
	 */
	protected ControladorCadastroLocal getControladorCadastro() {
		ControladorCadastroLocalHome localHome = null;
		ControladorCadastroLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCadastroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

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
	@SuppressWarnings("unchecked")
	public MapaAtualizacaoCadastralDM obterArquivoMapaQuadraImovel(Integer idImovel) throws ControladorException{
		try {
			Imovel imovel = repositorioImovel.pesquisarImovel(idImovel);
			if (imovel == null)
				return null;

			FiltroMapaAtualizacaoCadastralDM filtro = new FiltroMapaAtualizacaoCadastralDM();
			filtro.adicionarParametro(new ParametroSimples(FiltroMapaAtualizacaoCadastralDM.ID_LOCALIDADE, imovel.getLocalidade().getId()));
			filtro.adicionarParametro(new ParametroSimples(FiltroMapaAtualizacaoCadastralDM.ID_SETOR_COMERCIAL, imovel.getSetorComercial().getId()));
			filtro.adicionarParametro(new ParametroSimples(FiltroMapaAtualizacaoCadastralDM.ID_QUADRA, imovel.getQuadra().getId()));
			Collection<MapaAtualizacaoCadastralDM> result = getControladorUtil().pesquisar(filtro, MapaAtualizacaoCadastralDM.class.getName()); 

			return (MapaAtualizacaoCadastralDM) Util.retonarObjetoDeColecao(result);
		} catch (ErroRepositorioException e) {
			throw new ControladorException(e.getMessage());
		} 
	}

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
			throws ControladorException {
		try {
			ConversorMap conversor = new ConversorMap();
			return conversor.converter(kmzFile, mapFile, kmlFile, json);
		} catch (Exception e) {
			throw new ControladorException(e.getMessage());
		}
	}

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
	@SuppressWarnings("unchecked")
	public MapaAtualizacaoCadastralDM obterArquivoMapaQuadraPorParametros(
			String paramsMapa) throws ControladorException{
		try {
			String[] parametros = paramsMapa.split(",");
			FiltroMapaAtualizacaoCadastralDM filtro = new FiltroMapaAtualizacaoCadastralDM();
			filtro.adicionarParametro(new ParametroSimples(FiltroMapaAtualizacaoCadastralDM.ID_LOCALIDADE, Integer.parseInt(parametros[0])));
			filtro.adicionarParametro(new ParametroSimples(FiltroMapaAtualizacaoCadastralDM.COD_SETOR_COMERCIAL, Integer.parseInt(parametros[1])));
			filtro.adicionarParametro(new ParametroSimples(FiltroMapaAtualizacaoCadastralDM.NUMERO_QUADRA, Integer.parseInt(parametros[2])));
			Collection<MapaAtualizacaoCadastralDM> result = getControladorUtil().pesquisar(filtro, MapaAtualizacaoCadastralDM.class.getName()); 
			
			return (MapaAtualizacaoCadastralDM) Util.retonarObjetoDeColecao(result);
					
		} catch (Exception e) {
			throw new ControladorException(e.getMessage());
		}
		
	}
	
	/**
	 * [UC1391] - Gerar Roteiro Dispositivo Móvel Atualização Cadastral
	 * [FE0003] - Verificar Falta de Mapa Para Quadra Selecionada
	 * Este método pesquisa os as quadras que não possuem mapas.
	 * 
	 * @param idsQuadras - conjunto das quadras a serem pesquisadas.
	 * @return String - numeros das quadras separados por virgula.
	 */
	public String verificarQuadrasSemMapaPorImoveis(Collection<Integer> idsQuadras) throws ControladorException {
		try {
			if (idsQuadras == null || idsQuadras.isEmpty())
				return "";
			
			List<String> result = repositorioAtualizacaoCadastral.verificarQuadrasSemMapaPorImoveis(idsQuadras);
			StringBuilder sb = new StringBuilder("");
			
			if(!result.isEmpty()){
				int contador = 1;
				
				for(String id : result){
					sb.append(id);
					if(contador < 5 && result.size() > 1 && contador < result.size()){
						sb.append(", ");
					}else{
						break;
					}
					contador++;
				}
				if(result.size()>5){
					sb.append(" e outras");
				}
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControladorException(e.getMessage());
		}
		
	}

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
			ArquivoTextoAtualizacaoCadastralDM arquivoTexto) throws ControladorException{
		ParametroTabelaAtualizacaoCadastralDM ptac = arquivoTexto.getParametroTabelaAtualizacaoCadastralDM();
		Integer codigoSetorComercial = ptac.getCodigoSetorComercial();
		Integer idLocalidade = ptac.getLocalidade().getId();
		
		ArrayList<MapaQuadraHelper> mapas;
		try {
			mapas = repositorioAtualizacaoCadastral.pesquisarMapasQuadrasPorLocalidadeSetor(idLocalidade, codigoSetorComercial);
		} catch (ErroRepositorioException e1) {
			throw new ControladorException(e1.getMessage());
		}

		ByteArrayOutputStream baos;
		ZipOutputStream zos;

		try {
			baos = new ByteArrayOutputStream();
			zos = new ZipOutputStream(baos);
			ZipEntry entryArquivoTexto = new ZipEntry(arquivoTexto.getDescricaoArquivo());
			entryArquivoTexto.setSize(arquivoTexto.getArquivoTexto().length);
			zos.putNextEntry(entryArquivoTexto);
			zos.write(arquivoTexto.getArquivoTexto());
			zos.closeEntry();

			if(mapas!=null){
				for(MapaQuadraHelper mapa : mapas){
					ZipEntry entryArquivoMapa = new ZipEntry(mapa.getnQuadra()+".map");
					entryArquivoMapa.setSize(mapa.getArquivoQuadra().length);
					zos.putNextEntry(entryArquivoMapa);
					zos.write(mapa.getArquivoQuadra());
					zos.closeEntry();
				}
			}

			zos.close();
		} catch (IOException e) {
			throw new ControladorException(e.getMessage());
		}

		return baos.toByteArray();
	}

	/**
	 * Método responsável pela pesquisa de imóveis que possuem fotos na atualização cadastral.
	 * 
	 * @author André Miranda
	 * @date 22/06/2015
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public List<ImovelAtualizacaoCadastralDM> pesquisarImovelComFotoAtualizacaoCadastralDM(Integer idImovel)
			throws ControladorException {
		try {
			return repositorioAtualizacaoCadastral.pesquisarImovelComFotoAtualizacaoCadastralDM(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1689] - Gerar Dados Financeiros da Atualização Cadastral
	 * Método responsável pela geração dos dados financeiros da atualização cadastral.
	 * 
	 * @author André Miranda
	 * @date 10/07/2015
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarDadosFinanceirosAtualizacaoCadastral(Integer idFuncionalidadeIniciada, Integer comando)
			throws ControladorException {
		int idUnidadeIniciada = 0;

		try {
			Map<String, Integer> cacheLocalizacao = new HashMap<String, Integer>();
			Map<Integer, Integer> cacheSitAgua = new HashMap<Integer, Integer>();
			Map<Integer, Integer> cacheSitEsgoto = new HashMap<Integer, Integer>();
			Map<String, Integer> cacheTempo = new HashMap<String, Integer>();
			Map<Integer, Integer> cacheUsuario = new HashMap<Integer, Integer>();
			Map<Integer, Integer> cacheTarifa = new HashMap<Integer, Integer>();
			Map<Integer, String> cacheDescLigAgua = new HashMap<Integer, String>();
			Map<Integer, String> cacheDescLigEsgoto = new HashMap<Integer, String>();
			SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
			boolean inTarifaCategoria = sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_CATEGORIA);
			int referencia = sistemaParametro.getAnoMesFaturamento();

			// Registrar o início do processamento da Unidade de Processamento do Batch
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(
					idFuncionalidadeIniciada, UnidadeProcessamento.FUNCIONALIDADE, comando);

			System.out.println("COMANDO " + comando);

			repositorioAtualizacaoCadastral.excluirDadosComando(comando);

			List<DadosFinanceirosAtualizacaoCadastralDM> dados =
					repositorioAtualizacaoCadastral.pesquisarDadosFinanceirosAtualizacaoCadastralDM(comando);

			for (DadosFinanceirosAtualizacaoCadastralDM helper : dados) {
				Integer idLocalizacaoAntes = null;
				Integer idLocalizacaoDepois = null;
				Integer idSitAguaAntes = null;
				Integer idSitAguaDepois = null;
				Integer idSitEsgotoAntes = null;
				Integer idSitEsgotoDepois = null;
				Integer idTempo = null;
				Integer idUsuario = null;
				String descLigAguaAntes = null;
				String descLigAguaDepois = null;
				String descLigEsgotoAntes = null;
				String descLigEsgotoDepois = null;
				Imovel imovel = new Imovel(helper.getMatricula());

				calcularValorAguaEsgotoAntesDepois(referencia, imovel, inTarifaCategoria, helper, cacheTarifa);

				if(helper.getCodSetorAntes() != null && helper.getNumQuadraAntes() != null) {
					idLocalizacaoAntes = pesquisarIdLocalizacao(helper.getIdLocalidade(), helper.getCodSetorAntes(), helper.getNumQuadraAntes(), cacheLocalizacao);
				}
				idLocalizacaoDepois= pesquisarIdLocalizacao(helper.getIdLocalidade(), helper.getCodSetorDepois(), helper.getNumQuadraDepois(), cacheLocalizacao);
				helper.setIdLocalizacaoAntes(idLocalizacaoAntes);
				helper.setIdLocalizacaoDepois(idLocalizacaoDepois);

				if (helper.getSitAguaAntes() != null) {
					idSitAguaAntes = pesquisarIdSituacaoAgua(helper.getSitAguaAntes(), cacheSitAgua);
					descLigAguaAntes = pesquisarDescSituacaoLigacaoAgua(helper.getSitAguaAntes(), cacheDescLigAgua);
				}
				idSitAguaDepois = pesquisarIdSituacaoAgua(helper.getSitAguaDepois(), cacheSitAgua);
				descLigAguaDepois = pesquisarDescSituacaoLigacaoAgua(helper.getSitAguaDepois(), cacheDescLigAgua);
				helper.setIdSitAguaAntes(idSitAguaAntes);
				helper.setIdSitAguaDepois(idSitAguaDepois);
				helper.setDescLigAguaAntes(descLigAguaAntes);
				helper.setDescLigAguaDepois(descLigAguaDepois);

				if(helper.getSitEsgotoAntes() != null){
					idSitEsgotoAntes = pesquisarIdSituacaoEsgoto(helper.getSitEsgotoAntes(), cacheSitEsgoto);
					descLigEsgotoAntes = pesquisarDescSituacaoLigacaoEsgoto(helper.getSitEsgotoAntes(), cacheDescLigEsgoto);
				}
				idSitEsgotoDepois = pesquisarIdSituacaoEsgoto(helper.getSitEsgotoDepois(), cacheSitEsgoto);
				descLigEsgotoDepois = pesquisarDescSituacaoLigacaoEsgoto(helper.getSitEsgotoDepois(), cacheDescLigEsgoto);
				helper.setIdSitEsgotoAntes(idSitEsgotoAntes);
				helper.setIdSitEsgotoDepois(idSitEsgotoDepois);
				helper.setDescLigEsgotoAntes(descLigEsgotoAntes);
				helper.setDescLigEsgotoDepois(descLigEsgotoDepois);

				idTempo = pesquisarIdTempo(helper.getDataGeracao(), cacheTempo);
				helper.setIdTempo(idTempo);

				idUsuario = pesquisarIdUsuario(helper.getCadastrador(), cacheUsuario);
				helper.setIdUsuario(idUsuario);

				helper.setComando(comando);

				repositorioUtil.inserir(helper, HibernateUtil.getSessionPentaho());
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception e) {
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1689] - Gerar Dados Financeiros da Atualização Cadastral
	 * @param sistemaParametro
	 * @param imovel
	 * @param colecaoQuantidadeEconomias
	 * @return
	 * @throws ControladorException
	 */
	protected void calcularValorAguaEsgotoAntesDepois(int anoMesFaturamento, Imovel imovel,
			boolean inTarifaCategoria, DadosFinanceirosAtualizacaoCadastralDM helper,
			Map<Integer, Integer> cacheTarifa) throws ControladorException {
		BigDecimal valorTotalAguaAntes = BigDecimal.ZERO;
		BigDecimal valorTotalEsgotoAntes = BigDecimal.ZERO;
		BigDecimal valorTotalAguaDepois = BigDecimal.ZERO;
		BigDecimal valorTotalEsgotoDepois = BigDecimal.ZERO;

		boolean imovelNovo = helper.getCodSetorAntes() == null;

		Integer consumoAgua = getControladorMicromedicao().obterUltimoConsumoImovel(imovel.getId(), LigacaoTipo.LIGACAO_AGUA);
		Integer consumoEsgoto = getControladorMicromedicao().obterUltimoConsumoImovel(imovel.getId(), LigacaoTipo.LIGACAO_ESGOTO);

		ConsumoTarifa tarifa = getControladorImovel().pesquisarConsumoTarifaImovel(imovel.getId());
		imovel.setConsumoTarifa(tarifa);

		int mesConta = Util.obterMes(anoMesFaturamento);
		int anoConta = Util.obterAno(anoMesFaturamento);
		int ultimoDiaMes = new Integer(Util.obterUltimoDiaMes(mesConta, anoConta));

		Date dataLeituraAtualFaturamento = Util.criarData(ultimoDiaMes, mesConta, anoConta);
		Date dataLeituraAnteriorFaturamento = Util.criarData(1, mesConta, anoConta);

		BigDecimal percentualEsgoto = getControladorLigacaoEsgoto().recuperarPercentualEsgoto(imovel.getId());
		percentualEsgoto = percentualEsgoto == null ? new BigDecimal("100.00") : percentualEsgoto;

		//Variavel utilizada para decidir se o calculo do valor de água e de esgoto será feito apenas uma vez.
		//Será feito apenas uma vez se não houver alteração de situação de ligação de água, situação de ligação de esgoto, qtde de economias, categoria ou subcategoria.
		boolean calcularApenasDepois = false;

		if(imovelNovo){
			calcularApenasDepois = true;
			//pega a situação de ligação atual do imóvel
			//para imóvel novo sempre considera o indicador de rede da quadra e não o q o cadastrador colocou
			helper.setSitAguaDepois(getControladorImovel().pesquisarIdLigacaoAguaSituacao(imovel.getId()));
			helper.setSitEsgotoDepois(getControladorImovel().pesquisarIdLigacaoEsgotoSituacao(imovel.getId()));
		}else{
			if(helper.getSitAguaAntes() == null || helper.getSitAguaAntes().equals(helper.getSitAguaDepois())
			&& (helper.getSitEsgotoAntes() == null || helper.getSitEsgotoAntes().equals(helper.getSitEsgotoDepois()))){
				//Se for imóvel novo ou se não houve alteração de ligação de água/esgoto
				calcularApenasDepois = true;
			}
			if(helper.getAlteracaoCategoria().equals(ConstantesSistema.SIM) ||
			   helper.getAlteracaoSubcategoria().equals(ConstantesSistema.SIM) || 
			   helper.getAlteracaoEconomia().equals(ConstantesSistema.SIM)){
				calcularApenasDepois = false;
			}
		}

		Short indicadorFaturamentoAguaDepois = obterIndicadorfaturamentoAgua(helper.getSitAguaDepois());
		Short indicadorFaturamentoEsgotoDepois = obterIndicadorfaturamentoEsgoto(helper.getSitEsgotoDepois());

		Collection colecaoQuantidadeEconomiasDepois;
		if (inTarifaCategoria) {
			colecaoQuantidadeEconomiasDepois = getControladorCadastro().obterQtdeEconomiaPorCategoriaAtlzCadastral(helper.getImacDepois());
		} else {
			colecaoQuantidadeEconomiasDepois = getControladorCadastro().obterQtdeEconomiaPorSubcategoriaAtlzCadastral(helper.getImacDepois());
		}

		int consumoMinimoLigacaoDepois = getControladorMicromedicao().obterConsumoMinimoLigacao(imovel, colecaoQuantidadeEconomiasDepois);
		if(consumoAgua == null){
			consumoAgua = consumoMinimoLigacaoDepois;
		}
		if(consumoEsgoto == null){
			consumoEsgoto = consumoMinimoLigacaoDepois;
		}

		// [UC0120] - Calcular Valores de Água e/ou Esgoto 
		Collection<CalcularValoresAguaEsgotoHelper> colecaoDepois = getControladorFaturamento().calcularValoresAguaEsgoto(
				anoMesFaturamento, helper.getSitAguaDepois(), helper.getSitEsgotoDepois(),
				indicadorFaturamentoAguaDepois, indicadorFaturamentoEsgotoDepois, colecaoQuantidadeEconomiasDepois,
				consumoAgua, consumoEsgoto, consumoMinimoLigacaoDepois, dataLeituraAnteriorFaturamento,
				dataLeituraAtualFaturamento, percentualEsgoto, imovel.getConsumoTarifa().getId(), null, null);

		for (CalcularValoresAguaEsgotoHelper calcularHelper : colecaoDepois) {
			if (calcularHelper.getValorFaturadoAguaCategoria() != null) {
				valorTotalAguaDepois = valorTotalAguaDepois.add(calcularHelper.getValorFaturadoAguaCategoria());
			}
			if (calcularHelper.getValorFaturadoEsgotoCategoria() != null) {
				valorTotalEsgotoDepois = valorTotalEsgotoDepois.add(calcularHelper.getValorFaturadoEsgotoCategoria());
			}
		}

		if (calcularApenasDepois) {
			//Se for imóvel novo ou se não houve alteração, não precisa calcular 2 vezes.
			//O valor de água e de esgoto de antes é igual ao de depois.
			if (!imovelNovo) {
				valorTotalAguaAntes = valorTotalAguaDepois;
				valorTotalEsgotoAntes = valorTotalEsgotoDepois;
			}
		} else {
			//Calcular o valor de água e de esgoto de antes
			Short indicadorFaturamentoAguaAntes = obterIndicadorfaturamentoAgua(helper.getSitAguaAntes());
			Short indicadorFaturamentoEsgotoAntes = obterIndicadorfaturamentoEsgoto(helper.getSitEsgotoAntes());

			Collection colecaoQuantidadeEconomiasAntes;
			if (inTarifaCategoria) {
				colecaoQuantidadeEconomiasAntes = getControladorCadastro().obterQtdeEconomiaPorCategoriaAtlzCadastral(helper.getImacAntes());
			} else {
				colecaoQuantidadeEconomiasAntes = getControladorCadastro().obterQtdeEconomiaPorSubcategoriaAtlzCadastral(helper.getImacAntes());
			}

			int consumoMinimoLigacaoAntes = getControladorMicromedicao().obterConsumoMinimoLigacao(imovel, colecaoQuantidadeEconomiasDepois);

			// [UC0120] - Calcular Valores de Água e/ou Esgoto
			Collection<CalcularValoresAguaEsgotoHelper> colecaoAntes = getControladorFaturamento().calcularValoresAguaEsgoto(
					anoMesFaturamento, helper.getSitAguaAntes(), helper.getSitEsgotoAntes(),
					indicadorFaturamentoAguaAntes, indicadorFaturamentoEsgotoAntes, colecaoQuantidadeEconomiasAntes,
					consumoAgua, consumoEsgoto, consumoMinimoLigacaoAntes, dataLeituraAnteriorFaturamento,
					dataLeituraAtualFaturamento, percentualEsgoto, imovel.getConsumoTarifa().getId(), null, null);

			for (CalcularValoresAguaEsgotoHelper calcularHelper : colecaoAntes) {
				if (calcularHelper.getValorFaturadoAguaCategoria() != null) {
					valorTotalAguaAntes = valorTotalAguaAntes.add(calcularHelper.getValorFaturadoAguaCategoria());
				}
				if (calcularHelper.getValorFaturadoEsgotoCategoria() != null) {
					valorTotalEsgotoAntes = valorTotalEsgotoAntes.add(calcularHelper.getValorFaturadoEsgotoCategoria());
				}
			}
		}

		// Atribuir valores ao helper
		helper.setValorAguaAntes(valorTotalAguaAntes);
		helper.setValorEsgotoAntes(valorTotalEsgotoAntes);
		helper.setValorAguaDepois(valorTotalAguaDepois);
		helper.setValorEsgotoDepois(valorTotalEsgotoDepois);
		helper.setConsumoAgua(consumoAgua);
		helper.setConsumoEsgoto(consumoEsgoto);
		Integer idTarifa = pesquisarIdTarifa(tarifa.getId(), cacheTarifa);
		helper.setIdTarifa(idTarifa);
		if(tarifa != null) {
			helper.setDescTarifa(tarifa.getDescricao());
		}
	}

	/**
	 * [UC1689] - Gerar Dados Financeiros da Atualização Cadastral
	 * @author Vivianne Sousa
	 * @date 22/07/2015
	 * @throws ControladorException
	 */
	private Short obterIndicadorfaturamentoEsgoto(Integer situacaoLigacaoEsgoto) throws ControladorException {
		Short indicadorFaturamentoEsgoto = ConstantesSistema.NAO;
		FiltroLigacaoEsgotoSituacao filtro = new FiltroLigacaoEsgotoSituacao();
		filtro.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, situacaoLigacaoEsgoto));

		Collection<MapaAtualizacaoCadastralDM> colecaoLigacaoEsgotoSituacao = getControladorUtil().pesquisar(filtro, LigacaoEsgotoSituacao.class.getName());

		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);
		if(ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao().equals(ConstantesSistema.SIM)){
			indicadorFaturamentoEsgoto = ConstantesSistema.SIM;
		}
		return indicadorFaturamentoEsgoto;
	}

	/**
	 * [UC1689] - Gerar Dados Financeiros da Atualização Cadastral
	 * @author Vivianne Sousa
	 * @date 22/07/2015
	 * @throws ControladorException
	 */
	private Short obterIndicadorfaturamentoAgua(Integer situacaoLigacaoAgua) throws ControladorException {
		Short indicadorFaturamentoAgua = ConstantesSistema.NAO;
		FiltroLigacaoAguaSituacao filtro = new FiltroLigacaoAguaSituacao();
		filtro.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, situacaoLigacaoAgua));

		Collection<MapaAtualizacaoCadastralDM> colecaoLigacaoAguaSituacao = getControladorUtil().pesquisar(filtro, LigacaoAguaSituacao.class.getName());

		LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
		if(ligacaoAguaSituacao.getIndicadorFaturamentoSituacao().equals(ConstantesSistema.SIM) 
		   && ligacaoAguaSituacao.getIndicadorConsumoReal().equals(ConstantesSistema.NAO)){
			indicadorFaturamentoAgua = ConstantesSistema.SIM;
		}
		return indicadorFaturamentoAgua;
	}

	/**
	 * [UC1689] - Gerar Dados Financeiros da Atualização Cadastral
	 * @author Vivianne Sousa
	 * @date 22/07/2015
	 * @throws ControladorException
	 */
	protected Integer pesquisarIdLocalizacao(Integer idLocalidade, Integer cdSetorComercial,
			Integer nnQuadra, Map<String, Integer> cache) throws ControladorException {
		try{
			String chave = String.format("%d%d%d", idLocalidade, cdSetorComercial, nnQuadra);
			Integer idLocalizacao = cache.get(chave);
			if(idLocalizacao == null) {
				idLocalizacao = repositorioAtualizacaoCadastral.pesquisarIdLocalizacao(idLocalidade, cdSetorComercial, nnQuadra);
				cache.put(chave, idLocalizacao);
			}
	
			return idLocalizacao;
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1690] Gerar Dados Resumo da Atualização Cadastral
	 * Método responsável pela geração do resumo dos dados financeiros da atualização cadastral.
	 * 
	 * @author André Miranda
	 * @date 10/07/2015
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoDadosFinanceirosAtualizacaoCadastral(Integer idFuncionalidadeIniciada, Integer comando)
			throws ControladorException {
		int idUnidadeIniciada = 0;
		Map<String, Integer> cacheLocalizacao = new HashMap<String, Integer>();
		Map<String, Integer> cacheTempo = new HashMap<String, Integer>();
		Map<Integer, Integer> cacheUsuario = new HashMap<Integer, Integer>();
		Map<Integer, Integer> cacheSitAgua = pesquisarDimensaoLigacaoAguaSituacao();
		Map<Integer, Integer> cacheSitEsgoto = pesquisarDimensaoLigacaoEsgotoSituacao();
		
		try {
			// Registrar o início do processamento da Unidade de Processamento do Batch
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(
					idFuncionalidadeIniciada, UnidadeProcessamento.FUNCIONALIDADE, comando);

			repositorioAtualizacaoCadastral.excluirResumoDadosComando(comando);

			List<ResumoDadosFinanceirosAtualizacaoCadastralDM> resumos =
					repositorioAtualizacaoCadastral.pesquisarResumoDadosFinanceirosAtualizacaoCadastralDM(comando);

			for (ResumoDadosFinanceirosAtualizacaoCadastralDM resumo : resumos) {
				Integer idLocalizacaoAntes = null;
				Integer idLocalizacaoDepois = null;
				Integer idTempo = null;
				Integer idUsuario = null;

				if(resumo.getCodSetorAntes() != null && resumo.getNumQuadraAntes() != null) {
					idLocalizacaoAntes = pesquisarIdLocalizacao(resumo.getIdLocalidade(), resumo.getCodSetorAntes(),
							resumo.getNumQuadraAntes(), cacheLocalizacao);
				}
				idLocalizacaoDepois= pesquisarIdLocalizacao(resumo.getIdLocalidade(), resumo.getCodSetorDepois(),
						resumo.getNumQuadraDepois(), cacheLocalizacao);
				resumo.setIdLocalizacaoAntes(idLocalizacaoAntes);
				resumo.setIdLocalizacaoDepois(idLocalizacaoDepois);

				idTempo = pesquisarIdTempo(resumo.getDataGeracao(), cacheTempo);
				resumo.setIdTempo(idTempo);
				
				idUsuario = pesquisarIdUsuario(resumo.getCadastrador(), cacheUsuario);
				resumo.setIdUsuario(idUsuario);
				
				pesquisarDadosFinanceirosAtuCadastral(resumo, cacheSitAgua, cacheSitEsgoto);
				
				repositorioUtil.inserir(resumo, HibernateUtil.getSessionPentaho());
			}
			
			atualizarComandoFinalizado(comando);
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception e) {
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1690] Gerar Dados Resumo da Atualização Cadastral
	 * @author Vivianne Sousa
	 * @date 27/08/2015
	 * @return
	 * @throws ControladorException
	 */
	private Map<Integer, Integer> pesquisarDimensaoLigacaoEsgotoSituacao() throws ControladorException {
		Map<Integer, Integer> cacheSitEsgoto = new HashMap<Integer, Integer>();
		pesquisarIdSituacaoEsgoto(LigacaoEsgotoSituacao.POTENCIAL, cacheSitEsgoto);
		pesquisarIdSituacaoEsgoto(LigacaoEsgotoSituacao.FACTIVEL, cacheSitEsgoto);
		pesquisarIdSituacaoEsgoto(LigacaoEsgotoSituacao.LIGADO, cacheSitEsgoto);
		pesquisarIdSituacaoEsgoto(LigacaoEsgotoSituacao.SUPRIMIDO, cacheSitEsgoto);
		return cacheSitEsgoto;
	}

	/**
	 * [UC1690] Gerar Dados Resumo da Atualização Cadastral
	 * @author Vivianne Sousa
	 * @date 27/08/2015
	 * @return
	 * @throws ControladorException
	 */
	private Map<Integer, Integer> pesquisarDimensaoLigacaoAguaSituacao() throws ControladorException {
		Map<Integer, Integer> cacheSitAgua = new HashMap<Integer, Integer>();
		pesquisarIdSituacaoAgua(LigacaoAguaSituacao.POTENCIAL, cacheSitAgua);
		pesquisarIdSituacaoAgua(LigacaoAguaSituacao.FACTIVEL, cacheSitAgua);
		pesquisarIdSituacaoAgua(LigacaoAguaSituacao.LIGADO, cacheSitAgua);
		pesquisarIdSituacaoAgua(LigacaoAguaSituacao.CORTADO, cacheSitAgua);
		pesquisarIdSituacaoAgua(LigacaoAguaSituacao.SUPRIMIDO, cacheSitAgua);
		return cacheSitAgua;
	}

	/**
	 * [UC1689] - Gerar Dados Financeiros da Atualização Cadastral 
	 * @author Vivianne Sousa
	 * @date 23/07/2015
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarParametroTabelaAtualizacaoCadastralNaoFinalizado() throws ControladorException {
		try{
			return this.repositorioAtualizacaoCadastral.pesquisarParametroTabelaAtualizacaoCadastralNaoFinalizado();
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1689] - Gerar Dados Financeiros da Atualização Cadastral 
	 * Método responsável por pesquisar o ID da dimensão situação de água na base do pentaho.
	 * @author Vivianne Sousa
	 * @date 23/07/2015
	 */
	protected Integer pesquisarIdSituacaoAgua(Integer idSituacaoLigacaoAgua, Map<Integer, Integer> cache) throws ControladorException {
		try{
			Integer chave = idSituacaoLigacaoAgua;
			Integer idSituacaoAgua = cache.get(chave);
			if(idSituacaoAgua == null) {
				idSituacaoAgua = repositorioAtualizacaoCadastral.pesquisarIdSituacaoAgua(idSituacaoLigacaoAgua);
				cache.put(chave, idSituacaoAgua);
			}
			return idSituacaoAgua;
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1689] - Gerar Dados Financeiros da Atualização Cadastral 
	 * Método responsável por pesquisar o ID da dimensão situação de esgoto na base do pentaho.
	 * @author Vivianne Sousa
	 * @date 23/07/2015
	 */
	protected Integer pesquisarIdSituacaoEsgoto(Integer idSituacaoLigacaoEsgoto, Map<Integer, Integer> cache) throws ControladorException{
		try{
			Integer chave = idSituacaoLigacaoEsgoto;
			Integer idSituacaoEsgoto = cache.get(chave);
			if(idSituacaoEsgoto == null) {
				idSituacaoEsgoto = repositorioAtualizacaoCadastral.pesquisarIdSituacaoEsgoto(idSituacaoLigacaoEsgoto);
				cache.put(chave, idSituacaoEsgoto);
			}
			return idSituacaoEsgoto;
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1689] - Gerar Dados Financeiros da Atualização Cadastral 
	 * Método responsável por pesquisar o ID da dimensão tempo na base do pentaho.
	 * @author Vivianne Sousa
	 * @date 24/07/2015
	 */
	public Integer pesquisarIdTempo(Date dataGeracao, Map<String, Integer> cache) throws ControladorException{
		try{
			String chave = dataGeracao.toString();
			Integer idTempo = cache.get(chave);
			if(idTempo == null) {
				idTempo = repositorioAtualizacaoCadastral.pesquisarIdTempo(dataGeracao);
				cache.put(chave, idTempo);
			}
			return idTempo;
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1689] - Gerar Dados Financeiros da Atualização Cadastral 
	 * Método responsável por pesquisar o ID da dimensão usuário na base do pentaho.
	 * @author Vivianne Sousa
	 * @date 24/07/2015
	 */
	protected Integer pesquisarIdUsuario(Integer usuario, Map<Integer, Integer> cache) throws ControladorException{
		try{
			Integer chave = usuario;
			Integer idUsuario = cache.get(chave);
			if(idUsuario == null) {
				idUsuario = repositorioAtualizacaoCadastral.pesquisarIdUsuario(usuario);
				cache.put(chave, idUsuario);
			}
			return idUsuario;
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Método responsável por verificar se comando esta finalizado.
	 * 
	 * @author Vivianne Sousa
	 * @date 24/07/2015
	 * @param comando
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarComandoFinalizado(Integer comando) throws ControladorException{
		try{
			if (repositorioAtualizacaoCadastral.verificarComandoFinalizado(comando)){
				repositorioAtualizacaoCadastral.atualizarIndicadorComandoFinalizado(comando);
			}
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1689] - Gerar Dados Financeiros da Atualização Cadastral 
	 * Método responsável por pesquisar o ID da dimensão tarifa na base do pentaho.
	 * @author Vivianne Sousa
	 * @date 31/07/2015
	 */
	protected Integer pesquisarIdTarifa(Integer idTarifaConsumo, Map<Integer, Integer> cache) throws ControladorException {
		try{
			Integer chave = idTarifaConsumo;
			Integer idTarifa = cache.get(chave);
			if(idTarifa == null) {
				idTarifa = repositorioAtualizacaoCadastral.pesquisarIdTarifa(idTarifaConsumo);
				cache.put(chave, idTarifa);
			}
			return idTarifa;
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1689] - Gerar Dados Financeiros da Atualização Cadastral 
	 * Método responsável por pesquisar a descrição abreviada da situação de ligação de água.
	 * @author André Miranda
	 * @date 10/08/2015
	 */
	protected String pesquisarDescSituacaoLigacaoAgua(Integer idSituacao, Map<Integer, String> cache) throws ControladorException {
		String desc = cache.get(idSituacao);

		if(desc == null) {
			FiltroLigacaoAguaSituacao filtro = new FiltroLigacaoAguaSituacao();
			filtro.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, idSituacao));
			Collection<MapaAtualizacaoCadastralDM> colecao = getControladorUtil().pesquisar(filtro, LigacaoAguaSituacao.class.getName());
			LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecao);

			desc = ligacaoAguaSituacao.getDescricaoAbreviado();
			cache.put(idSituacao, desc);
		}

		return desc;
	}

	/**
	 * [UC1689] - Gerar Dados Financeiros da Atualização Cadastral 
	 * Método responsável por pesquisar a descrição abreviada da situação de ligação de esgoto.
	 * @author André Miranda
	 * @date 10/08/2015
	 */
	protected String pesquisarDescSituacaoLigacaoEsgoto(Integer idSituacao, Map<Integer, String> cache) throws ControladorException {
		String desc = cache.get(idSituacao);

		if(desc == null) {
			FiltroLigacaoEsgotoSituacao filtro = new FiltroLigacaoEsgotoSituacao();
			filtro.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, idSituacao));
			Collection<MapaAtualizacaoCadastralDM> colecao = getControladorUtil().pesquisar(filtro, LigacaoEsgotoSituacao.class.getName());
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(colecao);

			desc = ligacaoEsgotoSituacao.getDescricaoAbreviado();
			cache.put(idSituacao, desc);
		}

		return desc;
	}
	
	/**
	 * [UC1690] Gerar Dados Resumo da Atualização Cadastral
	 * Método responsável por pesquisar somatório dos dados financeiro da atualização cadastral  
	 * 
	 * @author Vivianne Sousa
	 * @since 26/08/2015
	 * @throws ErroRepositorioException
	 */
	public ResumoDadosFinanceirosAtualizacaoCadastralDM pesquisarDadosFinanceirosAtuCadastral(ResumoDadosFinanceirosAtualizacaoCadastralDM resumo, 
			Map<Integer, Integer> cacheSitAgua, Map<Integer, Integer> cacheSitEsgoto)throws ControladorException{
		try{
			
			Object[] dadosFinanceiros = repositorioAtualizacaoCadastral.pesquisarDadosFinanceirosAtuCadastral(resumo, cacheSitAgua, cacheSitEsgoto);
			
			resumo.setQtdeImovelPotAguaAntes(dadosFinanceiros[0] == null? 0 : (Integer)dadosFinanceiros[0]);
			resumo.setQtdeImovelPotAguaDepois(dadosFinanceiros[1] == null? 0 : (Integer)dadosFinanceiros[1]);
			resumo.setQtdeImovelFacAguaAntes(dadosFinanceiros[2] == null? 0 : (Integer)dadosFinanceiros[2]);
			resumo.setQtdeImovelFacAguaDepois(dadosFinanceiros[3] == null? 0 : (Integer)dadosFinanceiros[3]);
			resumo.setQtdeImovelLigAguaAntes(dadosFinanceiros[4] == null? 0 : (Integer)dadosFinanceiros[4]);
			resumo.setQtdeImovelLigAguaDepois(dadosFinanceiros[5] == null? 0 : (Integer)dadosFinanceiros[5]);
			resumo.setQtdeImovelCorAguaAntes(dadosFinanceiros[6] == null? 0 : (Integer)dadosFinanceiros[6]);
			resumo.setQtdeImovelCorAguaDepois(dadosFinanceiros[7] == null? 0 : (Integer)dadosFinanceiros[7]);
			resumo.setQtdeImovelSupAguaAntes(dadosFinanceiros[8] == null? 0 : (Integer)dadosFinanceiros[8]);
			resumo.setQtdeImovelSupAguaDepois(dadosFinanceiros[9] == null? 0 : (Integer)dadosFinanceiros[9]);
			resumo.setQtdeImovelPotEsgotoAntes(dadosFinanceiros[10] == null? 0 : (Integer)dadosFinanceiros[10]);
			resumo.setQtdeImovelPotEsgotoDepois(dadosFinanceiros[11] == null? 0 : (Integer)dadosFinanceiros[11]);
			resumo.setQtdeImovelFacEsgotoAntes(dadosFinanceiros[12] == null? 0 : (Integer)dadosFinanceiros[12]);
			resumo.setQtdeImovelFacEsgotoDepois(dadosFinanceiros[13] == null? 0 : (Integer)dadosFinanceiros[13]);
			resumo.setQtdeImovelLigEsgotoAntes(dadosFinanceiros[14] == null? 0 : (Integer)dadosFinanceiros[14]);
			resumo.setQtdeImovelLigEsgotoDepois(dadosFinanceiros[15] == null? 0 : (Integer)dadosFinanceiros[15]);
			resumo.setQtdeImovelSupEsgotoAntes(dadosFinanceiros[16] == null? 0 : (Integer)dadosFinanceiros[16]);
			resumo.setQtdeImovelSupEsgotoDepois(dadosFinanceiros[17] == null? 0 : (Integer)dadosFinanceiros[17]);
			resumo.setQtdeImovelResAntes(dadosFinanceiros[18] == null? 0 : (Integer)dadosFinanceiros[18]);
			resumo.setQtdeImovelResDepois(dadosFinanceiros[19] == null? 0 : (Integer)dadosFinanceiros[19]);
			resumo.setQtdeImovelComAntes(dadosFinanceiros[20] == null? 0 : (Integer)dadosFinanceiros[20]);
			resumo.setQtdeImovelComDepois(dadosFinanceiros[21] == null? 0 : (Integer)dadosFinanceiros[21]);
			resumo.setQtdeImovelIndAntes(dadosFinanceiros[22] == null? 0 : (Integer)dadosFinanceiros[22]);
			resumo.setQtdeImovelIndDepois(dadosFinanceiros[23] == null? 0 : (Integer)dadosFinanceiros[23]);
			resumo.setQtdeImovelPubAntes(dadosFinanceiros[24] == null? 0 : (Integer)dadosFinanceiros[24]);
			resumo.setQtdeImovelPubDepois(dadosFinanceiros[25] == null? 0 : (Integer)dadosFinanceiros[25]);
			resumo.setQtdeEconomiaResAntes(dadosFinanceiros[26] == null? 0 : (Integer)dadosFinanceiros[26]);
			resumo.setQtdeEconomiaResDepois(dadosFinanceiros[27] == null? 0 : (Integer)dadosFinanceiros[27]);
			resumo.setQtdeEconomiaComAntes(dadosFinanceiros[28] == null? 0 : (Integer)dadosFinanceiros[28]);
			resumo.setQtdeEconomiaComDepois(dadosFinanceiros[29] == null? 0 : (Integer)dadosFinanceiros[29]);
			resumo.setQtdeEconomiaIndAntes(dadosFinanceiros[30] == null? 0 : (Integer)dadosFinanceiros[30]);
			resumo.setQtdeEconomiaIndDepois(dadosFinanceiros[31] == null? 0 : (Integer)dadosFinanceiros[31]);
			resumo.setQtdeEconomiaPubAntes(dadosFinanceiros[32] == null? 0 : (Integer)dadosFinanceiros[32]);
			resumo.setQtdeEconomiaPubDepois(dadosFinanceiros[33] == null? 0 : (Integer)dadosFinanceiros[33]);
			resumo.setValorAguaAntes(dadosFinanceiros[34] == null? BigDecimal.ZERO : (BigDecimal)dadosFinanceiros[34]);
			resumo.setValorAguaDepois(dadosFinanceiros[35] == null? BigDecimal.ZERO : (BigDecimal)dadosFinanceiros[35]);
			resumo.setValorEsgotoAntes(dadosFinanceiros[36] == null? BigDecimal.ZERO : (BigDecimal)dadosFinanceiros[36]);
			resumo.setValorEsgotoDepois(dadosFinanceiros[37] == null? BigDecimal.ZERO : (BigDecimal)dadosFinanceiros[37]);
			resumo.setValorMulta(dadosFinanceiros[38] == null? BigDecimal.ZERO : (BigDecimal)dadosFinanceiros[38]);
			resumo.setValorConsumoFraudado(dadosFinanceiros[39] == null? BigDecimal.ZERO : (BigDecimal)dadosFinanceiros[39]);
			resumo.setQtdeAlteracaoNome(dadosFinanceiros[40] == null? 0 : (Integer)dadosFinanceiros[40]);
			resumo.setQtdeAlteracaoEndereco(dadosFinanceiros[41] == null? 0 : (Integer)dadosFinanceiros[41]);
			resumo.setQtdeAlteracaoCategoria(dadosFinanceiros[42] == null? 0 : (Integer)dadosFinanceiros[42]);
			resumo.setQtdeAlteracaoSubcategoria(dadosFinanceiros[43] == null? 0 : (Integer)dadosFinanceiros[43]);
			resumo.setQtdeAlteracaoEconomia(dadosFinanceiros[44] == null? 0 : (Integer)dadosFinanceiros[44]);
			resumo.setQtdeAlteracaoSituacaoAgua(dadosFinanceiros[45] == null? 0 : (Integer)dadosFinanceiros[45]);
			resumo.setQtdeAlteracaoSituacaoEsgoto(dadosFinanceiros[46] == null? 0 : (Integer)dadosFinanceiros[46]);
			resumo.setQtdeAlteracaoHidrometro(dadosFinanceiros[47] == null? 0 : (Integer)dadosFinanceiros[47]);
			resumo.setQtdeAlteracaoRg(dadosFinanceiros[48] == null? 0 : (Integer)dadosFinanceiros[48]);
			resumo.setQtdeInclusaoRg(dadosFinanceiros[49] == null? 0 : (Integer)dadosFinanceiros[49]);
			resumo.setQtdeAlteracaoCpfCnpj(dadosFinanceiros[50] == null? 0 : (Integer)dadosFinanceiros[50]);
			resumo.setQtdeInclusaoCpfCnpj(dadosFinanceiros[51] == null? 0 : (Integer)dadosFinanceiros[51]);
			resumo.setQtdeExclusaoCpfCnpj(dadosFinanceiros[52] == null? 0 : (Integer)dadosFinanceiros[52]);
			resumo.setQtdeImovelComAlterecao(dadosFinanceiros[53] == null? 0 : (Integer)dadosFinanceiros[53]);
			resumo.setQtdeImovelSemAlteracao(dadosFinanceiros[54] == null? 0 : (Integer)dadosFinanceiros[54]);
			
			return resumo;
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
}

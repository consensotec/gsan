package gsan.atualizacaocadastral;

import edu.emory.mathcs.backport.java.util.Collections;
import gsan.atualizacaocadastral.bean.MapaQuadraHelper;
import gsan.cadastro.IRepositorioCadastro;
import gsan.cadastro.RepositorioCadastroHBM;
import gsan.cadastro.cliente.IRepositorioCliente;
import gsan.cadastro.cliente.RepositorioClienteHBM;
import gsan.cadastro.imovel.IRepositorioImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.RepositorioImovelHBM;
import gsan.util.ConstantesJNDI;
import gsan.util.ControladorException;
import gsan.util.ControladorUtilLocal;
import gsan.util.ControladorUtilLocalHome;
import gsan.util.ErroRepositorioException;
import gsan.util.ServiceLocator;
import gsan.util.ServiceLocatorException;
import gsan.util.SistemaException;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;


/**
 * @author Bruno Sá Barreto
 * @date 24/02/2015
 */
public class ControladorAtualizacaoCadastralSEJB implements SessionBean{

	private static final long serialVersionUID = 1L;
	SessionContext sessionContext;

	//repositorios
	private IRepositorioAtualizacaoCadastral repositorioAtualizacaoCadastral;
	private IRepositorioImovel repositorioImovel;
	private IRepositorioCliente repositorioCliente;
	private IRepositorioCadastro repositorioCadastro;
	
	/**
	 * Inicializa os repositorios que são referenciados no controlador
	 * @exception CreateException
	 */
	public void ejbCreate() throws CreateException {
		repositorioImovel = RepositorioImovelHBM.getInstancia();
		repositorioCadastro = RepositorioCadastroHBM.getInstancia();
		repositorioCliente = RepositorioClienteHBM.getInstancia();
		repositorioAtualizacaoCadastral = RepositorioAtualizacaoCadastralHBM.getInstancia();		
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
	private ControladorUtilLocal getControladorUtil() {
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
	 * [FE0003] - Verificar Falta de Mapa Para Imóvel Selecionado
	 * Este método pesquisa os numeros das quadras dos imoveis que não 
	 * possuem mapas.
	 * 
	 * @param idsRegistros - ids dos imóveis que serão verificados.
	 * @return String - numeros das quadras separados por virgula. 
	 */
	public String verificarQuadrasSemMapaPorImoveis(String[] idsRegistros) throws ControladorException {
		try {
			
			if(idsRegistros==null)return "";
			
			List<String> result = repositorioAtualizacaoCadastral.verificarQuadrasSemMapaPorImoveis(idsRegistros);
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
}

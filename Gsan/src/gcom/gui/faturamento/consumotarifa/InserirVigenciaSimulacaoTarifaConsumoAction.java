package gcom.gui.faturamento.consumotarifa;

import gcom.cadastro.imovel.Categoria;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.ConsumoTarifaHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifaFaixa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifaVigencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1441] - Inserir Vigência de Simulação para Tarifa de Consumo
 * 
 * @author Davi Menezes
 * @date 20/12/2013
 *
 */
public class InserirVigenciaSimulacaoTarifaConsumoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		//Form
		InserirVigenciaSimulacaoTarifaConsumoActionForm form = (InserirVigenciaSimulacaoTarifaConsumoActionForm) actionForm;
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Coleção de Categorias
		Collection<?> colecaoCategoria = (Collection<?>) sessao.getAttribute("colecaoCategoria");
		
		//Validar Campos do Reajuste
		this.validarCamposPercentualReajuste(httpServletRequest, colecaoCategoria, form.getRegistros());
			
		//Deletar os Registros com data de 01/01/3000
		this.deletarRegistros(fachada);
		
		//Montar os campos para fazer o reajuste 
		HashMap<ConsumoTarifaVigencia, Map<ConsumoTarifaCategoria, BigDecimal>> map = this.montarCampos(form.getRegistros(), 
				httpServletRequest, fachada);
		
		fachada.reajustarTarifaConsumo(map,false);
		
		montarPaginaSucesso(httpServletRequest, "Vigências de Simulação atualizadas com sucesso", 
				"Realizar outra Vigência de Simulação da Tarifa de Consumo", 
				"exibirInserirVigenciaSimulacaoTarifaConsumoAction.do?menu=sim");
		
		return retorno;
	}
	
	/**
	 * Validar os Campos do Percentual de Reajuste
	 * 
	 * @author Davi Menezes
	 * @date 21/02/2013
	 */
	private void validarCamposPercentualReajuste(HttpServletRequest request, Collection colecaoCategoria, String[] registros){
		try{
			Map<String, String[]> requestMap = request.getParameterMap();
			boolean valido = false;
			
			Iterator<?> it = colecaoCategoria.iterator();
			
			while(it.hasNext()){
				Categoria categoria = (Categoria) it.next();
				String percentual = (requestMap.get("percentual_" + categoria.getId()))[0];
				
				if(percentual != null && !percentual.equals("")){
					percentual = percentual.replaceAll(",", ".");
					BigDecimal perc = new BigDecimal(percentual);
					
					if(perc.compareTo(new BigDecimal(0)) > 0){
						valido = true;
						break;
					}
				}
			}
			
			if(!valido){
				if(registros != null && registros.length > 0){
					throw new ActionServletException("atencao.percentual_reajuste_invalido");
				}
			}else{
				if(registros == null || registros.length < 1){
					throw new ActionServletException("atencao.percentual_reajuste_informado");
				}
			}
		}catch(NumberFormatException e){
			throw new ActionServletException("atencao.percentual_informado_invalido");
		}
	}
	
	/**
	 * Montar Campos para Ajustar a Tarifa de Consumo
	 * 
	 * @author Davi Menezes
	 * @date 21/02/2013
	 * 
	 */
	private HashMap<ConsumoTarifaVigencia, Map<ConsumoTarifaCategoria, BigDecimal>> montarCampos(String[] idsRegistros, HttpServletRequest request,
			Fachada fachada){
		
		HashMap<ConsumoTarifaVigencia, Map<ConsumoTarifaCategoria, BigDecimal>> map = 
				new HashMap<ConsumoTarifaVigencia, Map<ConsumoTarifaCategoria,BigDecimal>>();
		
		Map<ConsumoTarifaCategoria, BigDecimal> mapa = new HashMap<ConsumoTarifaCategoria, BigDecimal>();
		
		HttpSession sessao = request.getSession(false);
		
		Map<String, String[]> requestMap = request.getParameterMap();
		
		Collection<?> colecaoCategoria = (Collection<?>) sessao.getAttribute("colecaoCategoria");
		
		Collection<?> colecaoConsumoTarifaHelper = (Collection<?>) sessao.getAttribute("colecaoConsumoTarifaHelper");
		
		Set colecaoConsumoTarifaCategoria = new HashSet();
		
		Collection colecaoConsumoTarifaCategoriaAux = null;
		
		Iterator<?> it = null;
		Iterator<?> itConsumoTarifaCategoria = null;
		
		FiltroConsumoTarifaCategoria filtro = null;
		
		Categoria categoria = new Categoria();
		ConsumoTarifaHelper consumoTarifaHelper = new ConsumoTarifaHelper();
		ConsumoTarifa consumoTarifa = new ConsumoTarifa();
		ConsumoTarifaVigencia consumoTarifaVigencia = new ConsumoTarifaVigencia();
		ConsumoTarifaCategoria consumoTarifaCategoria = new ConsumoTarifaCategoria();
		
		String dataVigencia = null;
		String percentual = "";
		BigDecimal percentualFormatado = null;
		
		String idConsumoTarifa = null;
		if(idsRegistros != null && idsRegistros.length > 0){
			for(int i = 0; i < idsRegistros.length; i++){
				idConsumoTarifa = idsRegistros[i];
				
				consumoTarifa = new ConsumoTarifa();
				consumoTarifa.setId(Integer.parseInt(idConsumoTarifa));
				
				dataVigencia = fachada.pesquisarMaiorDataConsumoTarifaVigencia(consumoTarifa);
				
				consumoTarifaHelper = new ConsumoTarifaHelper();
				consumoTarifaHelper.setId(idConsumoTarifa);
				
				consumoTarifaVigencia = new ConsumoTarifaVigencia();
				consumoTarifaVigencia.setDataVigencia(Util.criarData(1, 1, 3000));
				consumoTarifaVigencia.setConsumoTarifa(consumoTarifa);
				
				mapa = new HashMap<ConsumoTarifaCategoria, BigDecimal>();
				colecaoConsumoTarifaCategoria = new HashSet();
				
				it = colecaoCategoria.iterator();
				while(it.hasNext()){
					categoria = (Categoria) it.next();
					
					try{
						percentual = (requestMap.get("percentual_" + categoria.getId()))[0];
						if(percentual == null || percentual.equals("")){
							percentual = "0";
						}
					}catch(Exception e){
						percentual = "0";
					}
					
					percentual = percentual.replaceAll(",", ".");
					if(validarPercentualMaximo(percentual)){
						percentualFormatado = new BigDecimal(percentual);
					}else{
						throw new ActionServletException("atencao.percentual_maior_percentual_maximo");
					}
					
					filtro = new FiltroConsumoTarifaCategoria();
					filtro.adicionarParametro(new ParametroSimples(FiltroConsumoTarifaCategoria.CONSUMO_VIGENCIA_CONSUMO_TARIFA_ID, idConsumoTarifa));
					filtro.adicionarParametro(new ParametroSimples(FiltroConsumoTarifaCategoria.CONSUMO_VIGENCIA_DATA_VIGENCIA, Util.converteStringParaDate(dataVigencia)));
					filtro.adicionarParametro(new ParametroSimples(FiltroConsumoTarifaCategoria.CATEGORIA_ID, categoria.getId()));
					filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoTarifaCategoria.CATEGORIA);
					filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoTarifaCategoria.SUBCATEGORIA);
					
					colecaoConsumoTarifaCategoriaAux = fachada.pesquisar(filtro, ConsumoTarifaCategoria.class.getName());
					if(!Util.isVazioOrNulo(colecaoConsumoTarifaCategoriaAux)){
						itConsumoTarifaCategoria = colecaoConsumoTarifaCategoriaAux.iterator();
						while(itConsumoTarifaCategoria.hasNext()){
							consumoTarifaCategoria = (ConsumoTarifaCategoria) itConsumoTarifaCategoria.next();
							consumoTarifaCategoria.setConsumoTarifaVigencia(consumoTarifaVigencia);
							
							mapa.put(consumoTarifaCategoria, percentualFormatado);
							colecaoConsumoTarifaCategoria.add(consumoTarifaCategoria);
						}
					}
				}
				
				consumoTarifaVigencia.setConsumoTarifaCategorias(colecaoConsumoTarifaCategoria);
				map.put(consumoTarifaVigencia, mapa);
			
				colecaoConsumoTarifaHelper.remove(consumoTarifaHelper);
			}
		}
		
		if(!Util.isVazioOrNulo(colecaoConsumoTarifaHelper)){
			Iterator<?> itHelper = colecaoConsumoTarifaHelper.iterator();
			while(itHelper.hasNext()){
				consumoTarifaHelper = (ConsumoTarifaHelper) itHelper.next();
				idConsumoTarifa = consumoTarifaHelper.getId();
				
				consumoTarifa = new ConsumoTarifa();
				consumoTarifa.setId(Integer.parseInt(idConsumoTarifa));
				
				dataVigencia = fachada.pesquisarMaiorDataConsumoTarifaVigencia(consumoTarifa);
				
				consumoTarifaVigencia = new ConsumoTarifaVigencia();
				consumoTarifaVigencia.setDataVigencia(Util.criarData(1, 1, 3000));
				consumoTarifaVigencia.setConsumoTarifa(consumoTarifa);
				
				mapa = new HashMap<ConsumoTarifaCategoria, BigDecimal>();
				colecaoConsumoTarifaCategoria = new HashSet();
				
				it = colecaoCategoria.iterator();
				while(it.hasNext()){
					categoria = (Categoria) it.next();
					
					percentualFormatado = new BigDecimal("0");
					
					filtro = new FiltroConsumoTarifaCategoria();
					filtro.adicionarParametro(new ParametroSimples(FiltroConsumoTarifaCategoria.CONSUMO_VIGENCIA_CONSUMO_TARIFA_ID, idConsumoTarifa));
					filtro.adicionarParametro(new ParametroSimples(FiltroConsumoTarifaCategoria.CONSUMO_VIGENCIA_DATA_VIGENCIA, Util.converteStringParaDate(dataVigencia)));
					filtro.adicionarParametro(new ParametroSimples(FiltroConsumoTarifaCategoria.CATEGORIA_ID, categoria.getId()));
					filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoTarifaCategoria.CATEGORIA);
					filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoTarifaCategoria.SUBCATEGORIA);
					
					colecaoConsumoTarifaCategoriaAux = fachada.pesquisar(filtro, ConsumoTarifaCategoria.class.getName());
					if(!Util.isVazioOrNulo(colecaoConsumoTarifaCategoriaAux)){
						itConsumoTarifaCategoria = colecaoConsumoTarifaCategoriaAux.iterator();
						while(itConsumoTarifaCategoria.hasNext()){
							consumoTarifaCategoria = (ConsumoTarifaCategoria) itConsumoTarifaCategoria.next();
							consumoTarifaCategoria.setConsumoTarifaVigencia(consumoTarifaVigencia);
							
							mapa.put(consumoTarifaCategoria, percentualFormatado);
							colecaoConsumoTarifaCategoria.add(consumoTarifaCategoria);
						}
					}
				}
			
				consumoTarifaVigencia.setConsumoTarifaCategorias(colecaoConsumoTarifaCategoria);
				map.put(consumoTarifaVigencia, mapa);
			}
		}
		
		return map;
	}
	
	/**
	 * Validar Percentual Máximo
	 * 
	 * @author Davi Menezes
	 * @date 21/02/2013
	 */
	private boolean validarPercentualMaximo(String percentual){
		boolean valido = true;
		
		try{
			BigDecimal percentualFormatado = new BigDecimal(percentual);
			
			if(percentualFormatado.compareTo(new BigDecimal(100)) > 0){
				valido = false;
			}else{
				valido = true;
			}
			
		}catch(Exception e){
			throw new ActionServletException("atencao.percentual_invalido");
		}
		
		return valido;
	}
	
	/**
	 * Método responsável por deletar todos os registros do ano 3000 ao gerar a tarifa de consumo
	 * 
	 * @author Davi Menezes
	 * @date 25/02/2013
	 */
	private void deletarRegistros(Fachada fachada) {
		FiltroConsumoTarifaVigencia filtroConsumoTarifaVigencia = new FiltroConsumoTarifaVigencia();
		filtroConsumoTarifaVigencia.adicionarParametro(new ParametroSimples(FiltroConsumoTarifaVigencia.DATA_VIGENCIA, Util.criarData(1, 1, 3000)));
		filtroConsumoTarifaVigencia.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoTarifaVigencia.CONSUMO_TARIFA);
		
		ConsumoTarifaVigencia consumoTarifaVigencia = null;
		ConsumoTarifaCategoria consumoTarifaCategoria = null;
		ConsumoTarifaFaixa consumoTarifaFaixa = null;
		
		FiltroConsumoTarifaCategoria filtroConsumoTarifaCategoria = null;
		FiltroConsumoTarifaFaixa filtroConsumoTarifaFaixa = null;
		
		Collection colecaoConsumoTarifaVigencia = fachada.pesquisar(filtroConsumoTarifaVigencia, ConsumoTarifaVigencia.class.getName());
		if(!Util.isVazioOrNulo(colecaoConsumoTarifaVigencia)){
			Collection colecaoConsumoTarifaCategoria = null;
			Collection colecaoConsumoTarifaFaixa = null;
			
			Iterator<?> itConsumoTarifaVigencia  = colecaoConsumoTarifaVigencia.iterator();
			Iterator<?> itConsumoTarifaCategoria = null;
			Iterator<?> itConsumoTarifaFaixa = null;
			
			while(itConsumoTarifaVigencia.hasNext()){
				consumoTarifaVigencia = (ConsumoTarifaVigencia) itConsumoTarifaVigencia.next();
				
				filtroConsumoTarifaCategoria = new FiltroConsumoTarifaCategoria();
				filtroConsumoTarifaCategoria.adicionarParametro(new ParametroSimples(
						FiltroConsumoTarifaCategoria.CONSUMO_VIGENCIA_ID, consumoTarifaVigencia.getId()));
				
				colecaoConsumoTarifaCategoria = fachada.pesquisar(filtroConsumoTarifaCategoria, ConsumoTarifaCategoria.class.getName());
				if(!Util.isVazioOrNulo(colecaoConsumoTarifaCategoria)){
					itConsumoTarifaCategoria = colecaoConsumoTarifaCategoria.iterator();
					while(itConsumoTarifaCategoria.hasNext()){
						consumoTarifaCategoria = (ConsumoTarifaCategoria) itConsumoTarifaCategoria.next();
						
						filtroConsumoTarifaFaixa = new FiltroConsumoTarifaFaixa();
						filtroConsumoTarifaFaixa.adicionarParametro(new ParametroSimples(
								FiltroConsumoTarifaFaixa.CONSUMO_TARIFA_CATEGORIA_ID, consumoTarifaCategoria.getId()));
						
						colecaoConsumoTarifaFaixa = fachada.pesquisar(filtroConsumoTarifaFaixa, ConsumoTarifaFaixa.class.getName());
						if(!Util.isVazioOrNulo(colecaoConsumoTarifaFaixa)){
							itConsumoTarifaFaixa = colecaoConsumoTarifaFaixa.iterator();
							while(itConsumoTarifaFaixa.hasNext()){
								consumoTarifaFaixa = (ConsumoTarifaFaixa) itConsumoTarifaFaixa.next();
								
								fachada.remover(consumoTarifaFaixa);
							}
						}
					
						fachada.remover(consumoTarifaCategoria);
					}
				}
				
				fachada.remover(consumoTarifaVigencia);
			}
		}
	}
	
	
}

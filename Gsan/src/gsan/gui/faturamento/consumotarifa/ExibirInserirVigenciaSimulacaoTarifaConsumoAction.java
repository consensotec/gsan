package gsan.gui.faturamento.consumotarifa;

import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.FiltroCategoria;
import gsan.fachada.Fachada;
import gsan.faturamento.bean.ConsumoTarifaHelper;
import gsan.faturamento.consumotarifa.ConsumoTarifa;
import gsan.faturamento.consumotarifa.FiltroConsumoTarifa;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
public class ExibirInserirVigenciaSimulacaoTarifaConsumoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("vigenciaSimulacaoTarifaConsumoInserir");
		
		InserirVigenciaSimulacaoTarifaConsumoActionForm form = 
				(InserirVigenciaSimulacaoTarifaConsumoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		String menu = (String) httpServletRequest.getParameter("menu");
		if(menu != null && menu.equals("sim")){
			form.setRegistros(null);
		}
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa(FiltroConsumoTarifa.DESCRICAO);
		filtroConsumoTarifa.adicionarParametro(new ParametroSimples(FiltroConsumoTarifa.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colConsumoTarifa = (Collection<?>) fachada.pesquisar(filtroConsumoTarifa, ConsumoTarifa.class.getName());
		if(!Util.isVazioOrNulo(colConsumoTarifa)){
			Collection<ConsumoTarifaHelper> colecaoConsumoTarifaHelper = new ArrayList<ConsumoTarifaHelper>();
			String dataVigencia = null;
			ConsumoTarifaHelper consumoTarifaHelper = null;
			
			Iterator<?> it = colConsumoTarifa.iterator();
			while(it.hasNext()){
				consumoTarifaHelper = new ConsumoTarifaHelper();
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) it.next();
				
				consumoTarifaHelper.setId(String.valueOf(consumoTarifa.getId()));
				consumoTarifaHelper.setDescricao(consumoTarifa.getDescricao());
				
				dataVigencia = fachada.pesquisarMaiorDataConsumoTarifaVigencia(consumoTarifa);
				consumoTarifaHelper.setDataVigencia(dataVigencia);
				
				colecaoConsumoTarifaHelper.add(consumoTarifaHelper);
			}
			sessao.setAttribute("colecaoConsumoTarifaHelper", colecaoConsumoTarifaHelper);
		}else{
			sessao.removeAttribute("colecaoConsumoTarifaHelper");
		}
		
		FiltroCategoria filtroCategoria = new FiltroCategoria(FiltroCategoria.CODIGO);
		filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colCategoria = (Collection<?>) fachada.pesquisar(filtroCategoria, Categoria.class.getName());
		if(!Util.isVazioOrNulo(colCategoria)){
			sessao.setAttribute("colecaoCategoria", colCategoria);
		}else{
			sessao.removeAttribute("colecaoCategoria");
		}
		
		return retorno;
	}
	
}

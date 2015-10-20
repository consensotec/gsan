package gcom.gui.cobranca;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC 1587] - Gerar Dívida Ativa
 * 
 * @author Davi Menezes
 * @date 13/02/2014
 *
 */
public class ExibirInserirCriterioDividaAtivaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta a ação de retorno
		ActionForward retorno = actionMapping.findForward("inserirCriterioDividaAtiva");
		
		//Formulário
		InserirCriterioDividaAtivaActionForm form = (InserirCriterioDividaAtivaActionForm) actionForm;
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		String menu = httpServletRequest.getParameter("menu");
		if(menu != null && menu.equals("sim")){
			form.reset();
		}
		
		this.pesquisarEsferaPoder(fachada, httpServletRequest);
		this.pesquisarClienteTipo(fachada, httpServletRequest);
		
		return retorno;
	}
	
	/**
	 * Método Responsável por pesquisar as esferas de poder
	 * 
	 * @param fachada
	 * @param request
	 */
	public void pesquisarEsferaPoder(Fachada fachada, HttpServletRequest request){
		FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder(FiltroEsferaPoder.DESCRICAO);
		filtroEsferaPoder.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
		if(!Util.isVazioOrNulo(colecaoEsferaPoder)){
			request.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
		}else{
			request.removeAttribute("colecaoEsferaPoder");
		}
	}
	
	/**
	 * Método Responsável por pesquisar os tipos de clientes
	 * 
	 * @param fachada
	 * @param request
	 */
	public void pesquisarClienteTipo(Fachada fachada, HttpServletRequest request){
		FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo(FiltroClienteTipo.DESCRICAO);
		filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colecaoClienteTipo = fachada.pesquisar(filtroClienteTipo, ClienteTipo.class.getName());
		if(!Util.isVazioOrNulo(colecaoClienteTipo)){
			request.setAttribute("colecaoClienteTipo", colecaoClienteTipo);
		}else{
			request.removeAttribute("colecaoClienteTipo");
		}
	}
	
}

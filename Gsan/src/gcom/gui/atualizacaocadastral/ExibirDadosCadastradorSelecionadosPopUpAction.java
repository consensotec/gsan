package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.bean.DadosCadastradorHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1392] - Consultar Roteiro Dispositivo Móvel Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 05/09/2013
 *
 */
public class ExibirDadosCadastradorSelecionadosPopUpAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirDadosCadastradorSelecionadosPopUpAction");

		Fachada fachada = Fachada.getInstancia();
		
		String idParametro = httpServletRequest.getParameter("idParametro");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String total = (String) httpServletRequest.getParameter("total");
		
		sessao.setAttribute("total", total);
		
		
		//pesquisar imoveis pelo id do parametro
		if(idParametro != null && !idParametro.trim().equals("")){
			Collection<DadosCadastradorHelper> colecaoDadosCadastrador = fachada.pesquisarDadosCadastrador(Integer.parseInt(idParametro));
			
			if (!Util.isVazioOrNulo(colecaoDadosCadastrador)) {
				httpServletRequest.setAttribute("colecaoDadosCadastrador", colecaoDadosCadastrador);
			} else {
				throw new ActionServletException("atencao.sem_dados_selecionados_cadastrador");
			}
		}
		
		return retorno;
	}
}
package gsan.gui.faturamento.contratodemanda;

import gsan.gui.GcomAction;
import gsan.util.Util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1229] - Manter Contrato de Demanda Condomínios Residenciais
 * 
 * @author Diogo Peixoto
 * @since 23/09/2011
 *
 */
public class ExibirManterContratoDemandaCondominiosResidenciaisAction extends GcomAction {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward retorno = mapping.findForward("exibirContratoDemandaCondominiosResidenciais");

		String desfazer = request.getParameter("desfazerManter");
		HttpSession sessao = request.getSession();
		//Caso tenha voltado do desfazer do atualizar, não faz nada
		if(!(Util.verificarNaoVazio(desfazer) && desfazer.equalsIgnoreCase("sim"))){
			List<ContratoDemandaCondominiosResidenciaisHelper> contratosHelper = (List<ContratoDemandaCondominiosResidenciaisHelper>) request.getAttribute("colecaoHelper");
			sessao.setAttribute("contratosHelper", contratosHelper);
		}else{
			Boolean voltarFiltrar = (Boolean) sessao.getAttribute("voltarFiltrar") ;
			if(voltarFiltrar != null && voltarFiltrar){
				sessao.removeAttribute("voltarFiltrar");
				retorno = mapping.findForward("exibirFiltrarContratoDemandaCondominiosResidenciais");
			}else{
				List<ContratoDemandaCondominiosResidenciaisHelper> contratosHelper = (List<ContratoDemandaCondominiosResidenciaisHelper>) request.getAttribute("colecaoHelper");
				if(contratosHelper == null){
					retorno = mapping.findForward("exibirFiltrarContratoDemandaCondominiosResidenciais");
				}
			}
		}
		return retorno;
	}
}

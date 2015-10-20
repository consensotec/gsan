package gcom.gui.atualizacaocadastral;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ControladorException;

public class ExibirImoveisPorLogradouroPopUpAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
			ActionForward retorno = actionMapping.findForward("exibirImoveisPorLogradouroPopUpAction");
	
			Fachada fachada = Fachada.getInstancia();
			
			String idLogradouro = httpServletRequest.getParameter("idLogradouro");
		
		try {	
			//pesquisar imoveis pelo id do logradouro
			if(idLogradouro != null && !idLogradouro.trim().equals("")){
				ArrayList<Integer> colecaoImoveis = (ArrayList<Integer>) fachada.pesquisarImovelAtualizacaoCadastralPorLogradouro(Integer.parseInt(idLogradouro));
				if ((colecaoImoveis != null) && (!colecaoImoveis.isEmpty())) {
					httpServletRequest.setAttribute("colecaoImoveis", colecaoImoveis);
				} else {
					httpServletRequest.removeAttribute("colecaoImoveis");
				}
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
}
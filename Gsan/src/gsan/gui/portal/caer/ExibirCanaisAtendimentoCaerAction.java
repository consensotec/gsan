package gsan.gui.portal.caer;

import gsan.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável por exibir Canais de Atendimento
 * 
 * @author Erivan Sousa
 * @date 28/06/2011
 */
public class ExibirCanaisAtendimentoCaerAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		String retorno = "exibirCanaisAtendimentoCaerAction";
		
		String method = httpServletRequest.getParameter("method");
		
		if(method.equalsIgnoreCase("teleatendimento")){
			retorno = "exibirTeleAtendimentoCaerAction";
		}		
		
		if(method.equalsIgnoreCase("ouvidoria")){
			retorno = "exibirOuvidoriaCaerAction";
		}	
		
		return actionMapping.findForward(retorno);
	}	
}
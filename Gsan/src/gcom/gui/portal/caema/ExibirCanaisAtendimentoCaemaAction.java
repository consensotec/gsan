package gcom.gui.portal.caema;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Respons�vel por exibir Canais de Atendimento
 * 
 * @author Erivan Sousa
 * @date 28/06/2011
 */
public class ExibirCanaisAtendimentoCaemaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		String retorno = "exibirCanaisAtendimentoCaemaAction";
		
		String ip = httpServletRequest.getRemoteAddr();
		
		String method = httpServletRequest.getParameter("method");
		
		if(method.equalsIgnoreCase("teleatendimento")){
			retorno = "exibirTeleAtendimentoCaemaAction";
			
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.TELE_ATENDIMENTO, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
		}		
		
		if(method.equalsIgnoreCase("ouvidoria")){
			retorno = "exibirOuvidoriaCaemaAction";
			
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.OUVIDORIA, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
		}	
		
		return actionMapping.findForward(retorno);
	}	
}
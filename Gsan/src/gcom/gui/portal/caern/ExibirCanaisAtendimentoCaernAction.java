package gcom.gui.portal.caern;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável por exibir Canais de Atendimento
 * 
 * @author Rafael Pinto
 * @date 09/08/2013
 */
public class ExibirCanaisAtendimentoCaernAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		String retorno = "exibirCanaisAtendimentoCaernAction";
		
		String method = httpServletRequest.getParameter("method");
		String ip = httpServletRequest.getRemoteAddr();
		
		if(method.equalsIgnoreCase("teleatendimento")){
			retorno = "exibirTeleAtendimentoCaernAction";
			
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.TELE_ATENDIMENTO, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO); 
		}		
		
		if(method.equalsIgnoreCase("ouvidoria")){			 
			retorno = "exibirOuvidoriaCaernAction";
			
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.OUVIDORIA, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
		}	
		
		return actionMapping.findForward(retorno);
	}	
}
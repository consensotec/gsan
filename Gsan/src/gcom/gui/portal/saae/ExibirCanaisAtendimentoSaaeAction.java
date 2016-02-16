package gcom.gui.portal.saae;

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
 * @author Cesar Medeiros
 * @date 16/09/2015
 */
public class ExibirCanaisAtendimentoSaaeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		String retorno = "exibirCanaisAtendimentoSaaeAction";
		
		String ip = httpServletRequest.getRemoteAddr();
		
		String method = httpServletRequest.getParameter("method");
		
		if(method.equalsIgnoreCase("teleatendimento")){
			retorno = "exibirTeleAtendimentoSaaeAction";
			
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.TELE_ATENDIMENTO, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
		}		
		
		if(method.equalsIgnoreCase("ouvidoria")){
			retorno = "exibirOuvidoriaSaaeAction";
			
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.OUVIDORIA, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
		}	
		
		return actionMapping.findForward(retorno);
	}	
}
package gsan.gui.atendimentopublico.ordemservico;

import gsan.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
 * 
 * SB0001 ? Consultar/Atualizar Dados da Ordem de Serviço
 * 
 * 1ª Aba - "Anormalidade"
 * 
 * @author Mariana Victor
 * @date 23/09/2011
 */
public class ExibirConsultarDadosOrdemServicoVisitaAnormalidadeAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
        //seta o mapeamento de retorno para a página da primeira aba
        ActionForward retorno = actionMapping.findForward("consultarDadosOrdemServicoVisitaAnormalidade");
        
		if (httpServletRequest.getParameter("confirmado") != null
				&& httpServletRequest.getParameter("confirmado").equalsIgnoreCase("ok")) {
			
			httpServletRequest.setAttribute("confirmacao", "true");
			
			retorno = actionMapping.findForward("consultarDadosOSVisitaAnormalidade");
			
			return retorno;
			
		}
		
        return retorno;
    }
    
}

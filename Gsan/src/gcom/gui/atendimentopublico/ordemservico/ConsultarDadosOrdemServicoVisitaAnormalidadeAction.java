package gcom.gui.atendimentopublico.ordemservico;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
 * 
 * SB0001 ? Consultar/Atualizar Dados da Ordem de Servi�o
 * 
 * 1� Aba - "Anormalidade"
 * 
 * @author Mariana Victor
 * @date 23/09/2011
 */
public class ConsultarDadosOrdemServicoVisitaAnormalidadeAction extends GcomAction {

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

    	//cria a vari�vel que vai armazenar o retorno
    	ActionForward retorno = null;

        ConsultarDadosOrdemServicoVisitaActionForm form = (ConsultarDadosOrdemServicoVisitaActionForm) actionForm;
        
        //Cria uma inst�ncia da sess�o
        HttpSession sessao = httpServletRequest.getSession(false);
    	
    	String[] idsAcoes = form.getIdsAcoes();
        String ordemServicoConferida = form.getOrdemServicoConferida();
        
    	if ((httpServletRequest.getAttribute("confirmacao") != null
				&& (httpServletRequest.getAttribute("confirmacao")).toString().equalsIgnoreCase("true"))
			|| (httpServletRequest.getAttribute("confirmado") != null
				&& (httpServletRequest.getAttribute("confirmado")).toString().equalsIgnoreCase("ok"))) {
    		
    		retorno = actionMapping
        			.findForward("consultarDadosOrdemServicoVisitaAction");
    		
			httpServletRequest.removeAttribute("confirmacao");
			
			httpServletRequest.setAttribute("concluir", "true");
        			
		} else if ((idsAcoes == null || idsAcoes.length <= 0) 
				&& (ordemServicoConferida == null || ordemServicoConferida.trim().equals(""))
				&& httpServletRequest.getParameter("concluir") != null
				&& !httpServletRequest.getParameter("concluir").trim().equals("")) {
		
			httpServletRequest.setAttribute("destino", "1");
			sessao.setAttribute("destino", "1");
			
			// Monta a p�gina de confirma��o do wizard para perguntar se
			// o usu�rio quer confirmar o encerramento do comando 
			// mesmo sem ter sido enviado para a empresa contratada
			return montarPaginaConfirmacaoWizard(
					"atencao.nenhuma.acao.selecionada.confirma",
						httpServletRequest, actionMapping);
		}
    	
        //retorna o mapeamento contido na vari�vel retorno
        return retorno;
        
    }

}

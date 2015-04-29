package gsan.gui.atendimentopublico.ordemservico;

import java.util.ArrayList;

import gsan.atendimentopublico.ordemservico.OrdemServicoFoto;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.Util;

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
 * 2� Aba - "Fotos"
 * 
 * @author Mariana Victor
 * @date 23/09/2011
 */
public class ExibirConsultarDadosOrdemServicoVisitaFotosAction extends GcomAction {

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
    	
        //seta o mapeamento de retorno para a p�gina da primeira aba
        ActionForward retorno = actionMapping.findForward("consultarDadosOrdemServicoVisitaFotos");
        
        ConsultarDadosOrdemServicoVisitaActionForm form = (ConsultarDadosOrdemServicoVisitaActionForm) actionForm;

        //cria uma inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //cria uma inst�ncia da sess�o
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Integer idOS = Integer.valueOf(form.getOrdemServico());
		
		ArrayList<OrdemServicoFoto> fotos = (ArrayList<OrdemServicoFoto>) fachada.pesquisarFotosOrdemServico(idOS, true);
		if (!Util.isVazioOrNulo(fotos)){
			sessao.setAttribute("colecaoFotoOS", fotos);
			sessao.setAttribute("numeroFotos", fotos.size());
			sessao.setAttribute("idFoto", fotos.get(0).getId().intValue());
		} else {
			sessao.removeAttribute("colecaoFotoOS");
			sessao.removeAttribute("numeroFotos");
			sessao.removeAttribute("idFoto");
		}
		
        //retorna o mapeamento contido na vari�vel retorno
        return retorno;
    }
    
}

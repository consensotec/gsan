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
 * 2ª Aba - "Fotos"
 * 
 * @author Mariana Victor
 * @date 23/09/2011
 */
public class ConsultarDadosOrdemServicoVisitaFotosAction extends GcomAction {

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

    	//cria a variável que vai armazenar o retorno
    	ActionForward retorno = null;

    	//retorna o mapeamento contido na variável retorno
        return retorno;
    }

}

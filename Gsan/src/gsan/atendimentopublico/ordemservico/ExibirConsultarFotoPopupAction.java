package gsan.atendimentopublico.ordemservico;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;

/**
 * Action que define o pré-processamento da página de exibir consultar Foto OS Popup
 * 
 * @author Vivianne Sousa
 * @created 21/06/2013
 */
public class ExibirConsultarFotoPopupAction extends GcomAction{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("consultarFotoPopup");
		Fachada fachada = Fachada.getInstancia();				
		OrdemServico os = fachada.pesquisarOS(new Integer(httpServletRequest.getParameter("idOs")));
		httpServletRequest.setAttribute("os", os);
		
		Collection<OrdemServicoFoto> fotos = fachada.buscarFotosOs(os.getId());
		if(fotos !=null && !fotos.isEmpty())
			httpServletRequest.setAttribute("fotos", fotos);
		else
			throw new ActionServletException("atencao.msg_personalizada", "Não existe foto anexada para Ordem de Serviço "+os.getId());
		return retorno;
	}
}

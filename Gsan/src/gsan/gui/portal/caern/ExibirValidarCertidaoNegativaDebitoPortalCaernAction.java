package gsan.gui.portal.caern;

import gsan.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * [UC1300] Verificar Autenticidade da Certidão Negativa de Débito
 * 
 * @author Rafael Pinto
 * @date 18/07/2013
 */
public class ExibirValidarCertidaoNegativaDebitoPortalCaernAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirValidarCertidaoNegativaDebitoPortalCaernAction");
		
		httpServletRequest.setAttribute("voltarInformacoes", true);
		
		ValidarCertidaoNegativaDebitoPortalCaernActionForm 
			form = (ValidarCertidaoNegativaDebitoPortalCaernActionForm) actionForm;
		
		form.setMatriculaImovel("");
		form.setNumeroAutenticacao("");
		
		return retorno;
	}
}

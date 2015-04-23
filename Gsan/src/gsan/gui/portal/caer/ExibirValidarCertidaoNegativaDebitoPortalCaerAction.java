package gsan.gui.portal.caer;

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
 * @author Mariana Victor
 * @date 15/03/2012
 */
public class ExibirValidarCertidaoNegativaDebitoPortalCaerAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirValidarCertidaoNegativaDebitoPortalCaerAction");
		
		httpServletRequest.setAttribute("voltarInformacoes", true);
		
		ValidarCertidaoNegativaDebitoPortalCaerActionForm 
			form = (ValidarCertidaoNegativaDebitoPortalCaerActionForm) actionForm;
		
		form.setMatriculaImovel("");
		form.setNumeroAutenticacao("");
		
		return retorno;
	}
}

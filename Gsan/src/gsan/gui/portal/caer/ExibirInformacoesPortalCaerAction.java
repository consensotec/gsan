package gsan.gui.portal.caer;

import gsan.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável por exibir Canais de Atendimento da loja virtual da CAER
 * 
 * @author Davi Menezes 
 * @date 10/09/2012
 */
public class ExibirInformacoesPortalCaerAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		String retorno = "exibirInformacoesPortalCaerAction";

		String method = httpServletRequest.getParameter("method");

		if (method == null) {
			method = "";
		}
		
		if (method.equalsIgnoreCase("parcelamentoDebito")) {
			retorno = "exibirInformacoesParcelamentoDebitoPortalCaerAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);
		}else if (method.equalsIgnoreCase("validarCertidaoNegativaDebito")){
			retorno = "exibirValidarCertidaoNegativaDebitoPortalCaerAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);
		}
		
		return actionMapping.findForward(retorno);
	}
	
}
package gsan.gui.portal.caema;

import gsan.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Classe Responsável por exibir o formulário de cadastro para solicitação de
 * contas por e-mail na Loja Virtual da Compesa
 * </p>
 * 
 * @author Magno Gouveia
 * @date 18/05/2011
 */
public class ExibirInserirCadastroEmailClientePortalCaemaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInserirCadastroEmailClientePortalCaemaAction");
		
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		httpServletRequest.setAttribute("voltarServicos", true);
		
		InserirCadastroEmailClientePortalCaemaActionForm 
			form = (InserirCadastroEmailClientePortalCaemaActionForm) actionForm;
		
		form.setMatricula(String.valueOf(sessao.getAttribute("matricula")));
		
		if ((httpServletRequest.getParameter("voltar") != null && 
			httpServletRequest.getParameter("voltar").equals("sim")) || 
			(httpServletRequest.getParameter("ok") == null || 
			!httpServletRequest.getParameter("ok").equals("sim"))) {
			
			form.setCpfSolicitante("");
			form.setEmail("");
			form.setIdCliente(null);
			form.setMatricula("");
			form.setNomeSolicitante("");
			form.setTelefoneContato("");
			
		}
		
		return retorno;
	}
	
}
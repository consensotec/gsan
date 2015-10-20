package gcom.gui.portal.caern;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Classe Responsável por exibir o formulário de cadastro para solicitação de
 * contas por e-mail na Loja Virtual da Caern
 * </p>
 * 
 * @author Rafael Pinto
 * @date 22/07/2013
 */
public class ExibirInserirCadastroEmailClientePortalCaernAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInserirCadastroEmailClientePortalCaernAction");
		
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		httpServletRequest.setAttribute("voltarServicos", true);
		
		InserirCadastroEmailClientePortalCaernActionForm 
			form = (InserirCadastroEmailClientePortalCaernActionForm) actionForm;
		
		String ip = httpServletRequest.getRemoteAddr(); 
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.RECEBIMENTO_FATURA_EMAIL, AcessoLojaVirtual.INDICADOR_SERVICO_NAO_EXECUTADO); 

		
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
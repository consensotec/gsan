package gcom.gui.cadastro.cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

/**
 * @author Davi Menezes
 * @date 16/08/2013
 *
 */
public class ExibirAtualizarIndicadorCpfCnpjClientePopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarIndicadorCpfCnpjCliente");
		
		//Sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Form
		AtualizarIndicadorCpfCnpjClientePopupActionForm form = (AtualizarIndicadorCpfCnpjClientePopupActionForm) actionForm;
		
		//Usuário Logado
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		String aba = httpServletRequest.getParameter("aba");
		if(aba != null){
			form.setAba(aba);
		}
		
		String idCliente = httpServletRequest.getParameter("idCliente");
		if(idCliente != null){
			form.setIdCliente(idCliente);
		}
		
		String indicadorCpfCnpj = httpServletRequest.getParameter("indicadorCpfCnpj");
		if(indicadorCpfCnpj != null){
			form.setIndicadorValidarCpfCnpj(indicadorCpfCnpj);
			
			if(indicadorCpfCnpj.equals(ConstantesSistema.SIM.toString())){
				boolean temPermissaoEspecialAlterar = fachada.verificarPermissaoEspecialAtiva(PermissaoEspecial.MODIFICAR_VALIDACAO_CPF_E_CNPJ, usuario);
				
				if(!temPermissaoEspecialAlterar){
					httpServletRequest.setAttribute("bloquearAtualizar", true);
				}else{
					httpServletRequest.removeAttribute("bloquearAtualizar");
				}
			}
		}
		
		return retorno;
	}
}

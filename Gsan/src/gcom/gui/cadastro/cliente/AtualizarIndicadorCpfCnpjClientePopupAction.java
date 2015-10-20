package gcom.gui.cadastro.cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.cliente.Cliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;

/**
 * @author Davi Menezes
 * @date 16/08/2013
 *
 */
public class AtualizarIndicadorCpfCnpjClientePopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("atualizarIndicadorCpfCnpjCliente");
		
		//Sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Form
		AtualizarIndicadorCpfCnpjClientePopupActionForm form = (AtualizarIndicadorCpfCnpjClientePopupActionForm) actionForm;
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		Cliente cliente = fachada.pesquisarClienteDigitado(Integer.parseInt(form.getIdCliente()));
		
		//Usuário Logado
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		if(cliente == null){
			throw new ActionServletException("atencao.cliente.inexistente");
		}
		
		if(form.getIndicadorValidarCpfCnpj() != null && form.getIndicadorValidarCpfCnpj().equals(ConstantesSistema.NAO.toString())){
			boolean temPermissaoEspecialAlterar = fachada.verificarPermissaoEspecialAtiva(PermissaoEspecial.MODIFICAR_VALIDACAO_CPF_E_CNPJ, usuario);
			
			if(!temPermissaoEspecialAlterar){
				throw new ActionServletException("atencao.usuario_sem_permissao_indicador_valida_cpfCnpj");
			}
		}
		
		cliente.setIndicadorValidaCpfCnpj(Integer.parseInt(form.getIndicadorValidarCpfCnpj()));
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
						Operacao.OPERACAO_ATUALIZAR_INDICADOR_CPF_CNPJ_POPUP, cliente.getId(), cliente.getId(), 
						new UsuarioAcaoUsuarioHelper(usuario,
								UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		
		registradorOperacao.registrarOperacao(cliente);		
				
		fachada.atualizar(cliente);
		
		httpServletRequest.setAttribute("fecharPopup", true);
		
		httpServletRequest.setAttribute("aba", form.getAba());
		
		sessao.removeAttribute("imovelDadosCadastrais");
		
		return retorno;
	}
}

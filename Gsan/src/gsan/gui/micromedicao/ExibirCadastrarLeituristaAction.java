package gsan.gui.micromedicao;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.cadastro.funcionario.FiltroFuncionario;
import gsan.cadastro.funcionario.Funcionario;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirCadastrarLeituristaAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("cadastrarLeiturista");
		
		CadastrarLeituristaActionForm form = (CadastrarLeituristaActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if (form.getFuncionarioID() != null
				&& (!form.getFuncionarioID().trim().equals(""))) {
			this.pesquisarFuncionario(form, fachada, httpServletRequest);
		}
		
		if (form.getClienteID() != null
				&& !form.getClienteID().toString().trim().equalsIgnoreCase("")) {
			
			this.pesquisarCliente(form,fachada,httpServletRequest);
			
		}
		
//		 Parte que passa as coleções da Empresa necessárias no jsp
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.ID);
		Collection colecaoEmpresa = fachada.pesquisar(
				filtroEmpresa, Empresa.class.getName());

		if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Empresa");
		}
		
		form.setFuncionarioNaoEncontrada("false");
			
		return retorno;
	}
	
	/**
	 * Pesquisar Clientes
	 * 
	 * @param filtroCliente
	 * @param idCliente
	 * @param clientes
	 * @param filtrarImovelFiltrarActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarCliente( CadastrarLeituristaActionForm cadastrarLeituristaActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.ID, 
				cadastrarLeituristaActionForm.getClienteID()));
		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
				Cliente.class.getName());

		if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
			// O municipio foi encontrado
			cadastrarLeituristaActionForm
					.setClienteID(""
							+ ((Cliente) ((List) clienteEncontrado).get(0))
									.getId());
			cadastrarLeituristaActionForm
					.setNomeCliente(
							((Cliente) ((List) clienteEncontrado).get(0))
									.getNome());
			httpServletRequest.setAttribute("idClienteNaoEncontrado",
					"true");
			
			httpServletRequest.setAttribute("nomeCampo", "ddd");
			

		} else {
			cadastrarLeituristaActionForm.setClienteID("");
			httpServletRequest.setAttribute("idClienteNaoEncontrado",
					"exception");
			cadastrarLeituristaActionForm.setNomeCliente("Cliente inexistente");

			httpServletRequest.setAttribute("nomeCampo", "clienteID");

		}
	}
	
	public void pesquisarFuncionario(CadastrarLeituristaActionForm form, Fachada fachada,
			HttpServletRequest httpServletRequest){
		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
		filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.ID, form.getFuncionarioID()));

		filtroFuncionario
				.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_ORGANIZACIONAL);
		Collection coll = fachada.pesquisar(
				filtroFuncionario, Funcionario.class.getSimpleName());
		if (coll != null && !coll.isEmpty()) {
			Funcionario f = (Funcionario) coll.iterator().next();
			form.setFuncionarioID(f.getId().toString());
			form.setNomeFuncionario(f.getNome());
			form.setFuncionarioNaoEncontrada("false");
			httpServletRequest.setAttribute("nomeCampo", "ddd");
		} else {
			form.setFuncionarioID("");
			form.setNomeFuncionario("Funcionario inexistente.");
			form.setFuncionarioNaoEncontrada("true");
			httpServletRequest.setAttribute("nomeCampo", "funcionarioID");
			
		}
	}

}

/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.micromedicao.leitura;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * [UC0588]Inserir Leiturista
 * 
 * @author Thiago Ten�rio
 * @date 22/07/2007
 */

public class ExibirInserirLeituristaAction extends GcomAction {

	private ServletRequest httpServletRequest;
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirInserirLeiturista");

		Fachada fachada = Fachada.getInstancia();

		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		sessao.setAttribute("mostrarLogin", true);
		
		InserirLeituristaActionForm form = (InserirLeituristaActionForm) actionForm;
		
		//Limpar Funcionario
		if (httpServletRequest.getParameter("limparFuncionario") != null && httpServletRequest.getParameter("limparFuncionario").equals("sim")){
			form.setEmpresaID("");
			sessao.removeAttribute("bloquearEmpresa");
		}
		
		//Usu�rio
		if (form.getLoginUsuario() != null &&
			!form.getLoginUsuario().equals("")) {
			
			getUsuario(form, fachada,
					form.getLoginUsuario(), sessao);
		}
		
		//Unidade Organizacional
		if(form.getUnidadeOrganizacionalId() != null &&
			!form.getUnidadeOrganizacionalId().equals("")){
			getUnidadeOrganizacional(form, fachada, sessao);
		}

		if (httpServletRequest.getParameter("limparForm") != null && httpServletRequest
				.getParameter("limparForm").equalsIgnoreCase("S")){

			// -------------- bt LIMPAR ---------------

			// Limpando o formulario
			form.setIdFuncionario("");
			form.setDescricaoFuncionario("");
			form.setEmpresaID("");
			form.setIdCliente("");
			form.setDescricaoCliente("");
			form.setDdd("");
			form.setTelefone("");
			form.setNumeroImei("");	
			form.setIndicadorAgenteComercial("2");
			form.setLoginUsuario("");
			form.setNomeUsuario("");
			form.setUnidadeOrganizacionalId("");
			form.setUnidadeOrganizacionalDescricao("");
			form.setIndicadorAtualizacaoCadastral("2");
			sessao.removeAttribute("bloquearEmpresa");
		}
		
		// Bloquear o campo Empresa quando a empresa do usuario nao seja a empresa do sistema
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		if (usuario.getEmpresa() == null) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Empresa do usu�rio logado.");
		}
		if (!usuario.getEmpresa().getDescricao().equals(sistemaParametro.getNomeAbreviadoEmpresa())) {
			sessao.setAttribute("bloquearEmpresa", true);
			form.setEmpresaID(usuario.getEmpresa().getId().toString());
		}	

		// Verificar Exist�ncia do Leiturista Respons�vel(Funcionario)

		if (form.getIdFuncionario() != null && !form.getIdFuncionario()
				.equals("")) {

			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, form.getIdFuncionario()));
			
			filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("empresa");

			Collection colecaoFuncionario = fachada.pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

				Funcionario funcionario = (Funcionario) colecaoFuncionario
						.iterator().next();
				form.setDescricaoFuncionario(funcionario.getNome());
				
				sessao.setAttribute("bloquearEmpresa", true);
				form.setEmpresaID(funcionario.getEmpresa().getId().toString());

			} else{
				httpServletRequest.setAttribute("funcionarioEncontrado",
						"exception");
				form.setIdFuncionario("");
				form.setDescricaoFuncionario("FUNCIONARIO INEXISTENTE");
			}
			
			
		}
	
			
		if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").equals("")) {
			httpServletRequest.setAttribute("nomeCampo", "idFuncionario");
			form.setIndicadorAgenteComercial("2");
			form.setIndicadorAtualizacaoCadastral("2");
		}

		// Verificar Exist�ncia do Leiturista Respons�vel(Cliente)

		if (form.getIdCliente() != null && !form.getIdCliente().equals("")) {

			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, form.getIdCliente()));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

				Cliente cliente = (Cliente) colecaoCliente.iterator().next();
				form.setDescricaoCliente(cliente.getNome());

			} else {
				httpServletRequest.setAttribute("clienteEncontrado",
						"exception");
				form.setIdCliente("");
				form.setDescricaoCliente("CLIENTE INEXISTENTE");
			}

		}

		// Empresa

		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);

		filtroEmpresa.adicionarParametro(new ParametroSimples(
				FiltroEmpresa.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoPesquisa = null;

		// Retorna empresa
		colecaoPesquisa = fachada.pesquisar(filtroEmpresa, Empresa.class
				.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
			// Nenhum registro na tabela localidade_porte foi encontrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhum_registro_tabela", null, "Empresa");
		} else {
			sessao.setAttribute("colecaoEmpresa", colecaoPesquisa);
		}

		// Constr�i filtro para pesquisa da Empresa
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.ID);
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		sessao.setAttribute("colecaoEmpresa", fachada.pesquisar(filtroEmpresa,
				Empresa.class.getName(), "EMPRESA"));

		httpServletRequest.setAttribute("colecaoEmpresa", colecaoPesquisa);

		if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").equals("")) {
			httpServletRequest.setAttribute("nomeCampo", "idCliente");
		}

		return retorno;
	}
	
	/**
	 * Recupera o Usu�rio
	 *
	 * @author Bruno Barros
	 * @date 11/12/2006
	 *
	 * @param inserirLeituristaActionForm
	 * @param fachada
	 * @param idUsuario
	 * @return Descri��o da Unidade Filtrada
	 */
	private void getUsuario(InserirLeituristaActionForm inserirLeituristaActionForm, 
			Fachada fachada, String idUsuario, HttpSession sessao) {
		
		// Filtra Usuario
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		//filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, idUsuario));
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, idUsuario));
		
		// Recupera Usu�rio
		Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
			Usuario usuario = colecaoUsuario.iterator().next();
			
			sessao.setAttribute("usuarioEncontrado","true");
			inserirLeituristaActionForm.setNomeUsuario(usuario.getNomeUsuario());
		} else {
			
			sessao.removeAttribute("usuarioEncontrado");
			inserirLeituristaActionForm.setLoginUsuario("");
			inserirLeituristaActionForm.setNomeUsuario("Usu�rio Inexistente");
		}
	}
	
	
	/** 
	 * Recupera a Unidade Organizacional do Leiturista
	 * 
	 * @author Nathalia Santos
	 * @date 19/01/2012
	 * 
	 * @param inserirLeituristaActionForm
	 * @param fachada 
	 */
	private void getUnidadeOrganizacional(InserirLeituristaActionForm form, Fachada fachada, HttpSession sessao){
		
		//Filtra a Unidade Organizacional
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, form.getUnidadeOrganizacionalId()));
		
		//Recupera a Unidade Organizacional
		Collection<UnidadeOrganizacional> colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
			UnidadeOrganizacional unidadeOrganizacional = colecaoUnidade.iterator().next();
			
			sessao.setAttribute("unidadeOrganizacionalIdEncontrada","true");
			form.setUnidadeOrganizacionalDescricao(unidadeOrganizacional.getDescricao());
			
		}  else {

			sessao.removeAttribute("unidadeOrganizacionalIdEncontrada");
			form.setUnidadeOrganizacionalId("");
			form.setUnidadeOrganizacionalDescricao("Unidade Organizacional Inexistente");
		}
	}
}
	
	
	
	
	/** 
	 * Recupera a Unidade Organizacional do Leiturista
	 * 
	 * @author Davi
	 * @date 03/11/2011
	 * 
	 * @param inserirLeituristaActionForm
	 * @param fachada 
	 */
//	private void getUnidadeOrganizacional(InserirLeituristaActionForm form, Fachada fachada, HttpSession sessao){
//		
//		//Filtra a Unidade Organizacional
//		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
//		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, form.getUnidadeOrganizacionalId()));
//		
//		//Recupera a Unidade Organizacional
//		Collection<UnidadeOrganizacional> colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
//		
//		if(!Util.isVazioOrNulo(colecaoUnidade)){
//		UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);
//		//if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
//			//UnidadeOrganizacional unidadeOrganizacional = colecaoUnidade.iterator().next();
//				
//			sessao.setAttribute("unidadeOrganizacionalIdEncontrada","true");
//			form.setUnidadeOrganizacionalDescricao(unidadeOrganizacional.getDescricao());
//		}else{
//			sessao.removeAttribute("unidadeOrganizacionalIdEncontrada");
//			form.setUnidadeOrganizacionalId("");
//			form.setUnidadeOrganizacionalDescricao("Unidade Organizacional Inexistente");
//		}
//	}
//}

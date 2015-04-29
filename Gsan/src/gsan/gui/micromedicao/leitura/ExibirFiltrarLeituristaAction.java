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
package gsan.gui.micromedicao.leitura;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.cadastro.funcionario.FiltroFuncionario;
import gsan.cadastro.funcionario.Funcionario;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.gui.micromedicao.leitura.FiltrarLeituristaActionForm;
import gsan.seguranca.acesso.usuario.FiltroUsuario;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ComparacaoTexto;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pr�-processamento da p�gina de pesquisa de leiturista
 * 
 * Alterado em 11/06/2008
 * 
 * @author Thiago Ten�rio e Thiago Nascimento
 * @created 22 de Julho de 2007
 * 
 */
public class ExibirFiltrarLeituristaAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarLeiturista");

		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarLeituristaActionForm filtrarLeituristaActionForm = (FiltrarLeituristaActionForm) actionForm;

		filtrarLeituristaActionForm.setAtualizar("1");
		
		sessao.removeAttribute("bloquearEmpresa");
		
		// Bloquear o campo Empresa quando a empresa do usuario nao seja a empresa do sistema
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		if (usuario.getEmpresa() == null) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Empresa do usu�rio logado.");
		}
		if (!usuario.getEmpresa().getDescricao().equals(sistemaParametro.getNomeAbreviadoEmpresa())) {
			sessao.setAttribute("bloquearEmpresa", true);
			filtrarLeituristaActionForm.setEmpresaID(usuario.getEmpresa().getId().toString());
		}

		if (httpServletRequest.getParameter("menu") != null) {
			filtrarLeituristaActionForm.setIdFuncionario("");
			filtrarLeituristaActionForm.setDescricaoFuncionario("");
			filtrarLeituristaActionForm.setIdCliente("");
			filtrarLeituristaActionForm.setDescricaoCliente("");
			filtrarLeituristaActionForm.setDdd("");
			filtrarLeituristaActionForm.setTelefone("");
			filtrarLeituristaActionForm.setIndicadorUso("");
			filtrarLeituristaActionForm.setIndicadorAtualizar("1");
			filtrarLeituristaActionForm.setLoginUsuario("");
			filtrarLeituristaActionForm.setNomeUsuario("");
			filtrarLeituristaActionForm.setIndicadorAtualizacaoCadastral("3");
		}
		
		//Empresa
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		
		Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,Empresa.class.getName());
	
		
		if(colecaoEmpresa != null && !colecaoEmpresa.isEmpty()){
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}

		// Verificar Exist�ncia do Leiturista Respons�vel(Funcionario)

		if ((filtrarLeituristaActionForm.getIdFuncionario() != null && !filtrarLeituristaActionForm
				.getIdFuncionario().equals(""))) {

			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, filtrarLeituristaActionForm
							.getIdFuncionario()));

			Collection colecaoFuncionario = fachada.pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

				Funcionario funcionario = (Funcionario) colecaoFuncionario
						.iterator().next();
				filtrarLeituristaActionForm.setDescricaoFuncionario(funcionario
						.getNome());

			} else {
				httpServletRequest.setAttribute("funcionarioEncontrado",
						"exception");
				filtrarLeituristaActionForm.setIdFuncionario("");
				filtrarLeituristaActionForm
						.setDescricaoFuncionario("FUNCION�RIO INEXISTENTE");
			}

		}
		if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").equals("")) {
			httpServletRequest.setAttribute("nomeCampo", "idFuncionario");
		}

		// Verificar Exist�ncia do Leiturista Respons�vel(Cliente)

		if ((filtrarLeituristaActionForm.getIdCliente() != null && !filtrarLeituristaActionForm
				.getIdCliente().equals(""))) {

			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, filtrarLeituristaActionForm
							.getIdCliente()));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

				Cliente cliente = (Cliente) colecaoCliente.iterator().next();
				filtrarLeituristaActionForm.setDescricaoCliente(cliente
						.getNome());

			} else {
				httpServletRequest.setAttribute("clienteEncontrado",
						"exception");
				filtrarLeituristaActionForm.setIdCliente("");
				filtrarLeituristaActionForm
						.setDescricaoCliente("CLIENTE INEXISTENTE");
			}

		}

		filtrarLeituristaActionForm.setIndicadorAtualizar("1");

		if (filtrarLeituristaActionForm.getTipoPesquisa() == null
				|| filtrarLeituristaActionForm.getTipoPesquisa()
						.equalsIgnoreCase("")) {
			filtrarLeituristaActionForm
					.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
							.toString());
		}
		
//		//Usu�rio
//		if (filtrarLeituristaActionForm.getLoginUsuario() != null &&
//			!filtrarLeituristaActionForm.getLoginUsuario().equals("")) {
//			
//			getUsuario(filtrarLeituristaActionForm, fachada,
//					filtrarLeituristaActionForm.getLoginUsuario(), sessao);
//		}
		
		if ((filtrarLeituristaActionForm.getLoginUsuario() != null && !filtrarLeituristaActionForm.getLoginUsuario().equals(""))) {

			FiltroUsuario filtroUsuario = new FiltroUsuario();

			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.LOGIN, filtrarLeituristaActionForm.getLoginUsuario()));

			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (usuario != null && !colecaoUsuario.isEmpty()) {

				Usuario usuarioPesquisar = (Usuario) colecaoUsuario.iterator().next();
				filtrarLeituristaActionForm.setNomeUsuario(usuarioPesquisar.getNomeUsuario());

			} else {
				httpServletRequest.setAttribute("usuarioEncontrado",
						"exception");
				filtrarLeituristaActionForm.setLoginUsuario("");
				filtrarLeituristaActionForm.setNomeUsuario("USU�RIO INEXISTENTE");
			}
		}
	

		// c�digo para checar ou naum o Atualizar
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarLeituristaActionForm.setIndicadorUso("3");
			filtrarLeituristaActionForm
					.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
							.toString());
		}
		// se voltou da tela de Atualizar Leiturista
		if (sessao.getAttribute("voltar") != null
				&& sessao.getAttribute("voltar").equals("filtrar")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			if (sessao.getAttribute("tipoPesquisa") != null) {
				filtrarLeituristaActionForm.setTipoPesquisa(sessao
						.getAttribute("tipoPesquisa").toString());
			}
		}
		sessao.removeAttribute("voltar");
		sessao.removeAttribute("idRegistroAtualizacao");
		sessao.removeAttribute("tipoPesquisa");

		return retorno;
		}
	
//	/**
//	 * Recupera o Usu�rio
//	 *
//	 * @author Bruno Barros
//	 * @date 11/12/2006
//	 *
//	 * @param filtrarRegistroAtendimentoActionForm
//	 * @param fachada
//	 * @param idUsuario
//	 * @return Descri��o da Unidade Filtrada
//	 */
//	private void getUsuario(FiltrarLeituristaActionForm filtrarRegistroAtendimentoActionForm, 
//			Fachada fachada, String idUsuario, HttpSession sessao) {
//		
//		// Filtra Usuario
//		FiltroUsuario filtroUsuario = new FiltroUsuario();
//		filtroUsuario.adicionarParametro(new ComparacaoTexto(FiltroUsuario.LOGIN, idUsuario));
//		
//		
//		// Recupera Usu�rio
//		Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
//		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
//			Usuario usuario = colecaoUsuario.iterator().next();
//			
//			sessao.setAttribute("usuarioEncontrado","true");
//			filtrarRegistroAtendimentoActionForm.setNomeUsuario(usuario.getNomeUsuario());
//			filtrarRegistroAtendimentoActionForm.setLoginUsuario(usuario.getLogin());
//		} else {
//			
//			sessao.removeAttribute("usuarioEncontrado");
//			filtrarRegistroAtendimentoActionForm.setLoginUsuario("");
//			filtrarRegistroAtendimentoActionForm.setNomeUsuario("Usu�rio Inexistente");
//		}
//	}
		
	}
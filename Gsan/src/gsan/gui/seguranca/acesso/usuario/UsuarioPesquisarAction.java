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
package gsan.gui.seguranca.acesso.usuario;

import gsan.cadastro.funcionario.FiltroFuncionario;
import gsan.cadastro.funcionario.Funcionario;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioTipo;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pr�-processamento da p�gina de pesquisa de usu�rio
 * 
 * @author Vivianne Sousa
 * @created 24/02/2006
 */

public class UsuarioPesquisarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Inicializando Variaveis
		ActionForward retorno = actionMapping.findForward("usuarioPesquisar");
		PesquisarUsuarioActionForm pesquisarUsuarioActionForm = (PesquisarUsuarioActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// Inicializando Variaveis

		// Pesquisando usuarios tipo
		FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();
		Collection<Usuario> collectionUsuarioTipo = fachada.pesquisar(
				filtroUsuarioTipo, UsuarioTipo.class.getName());
		httpServletRequest.setAttribute("collectionUsuarioTipo",
				collectionUsuarioTipo);
		// Fim de pesquisando usuarios tipo

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			pesquisarUsuarioActionForm.setTipoUsuario(null);
			pesquisarUsuarioActionForm.setNome("");
			pesquisarUsuarioActionForm.setTipoPesquisa("");
			pesquisarUsuarioActionForm.setMatriculaFuncionario("");
			pesquisarUsuarioActionForm.setNomeFuncionario("");
			pesquisarUsuarioActionForm.setLogin("");
			pesquisarUsuarioActionForm.setIdUnidadeOrganizacional("");
			pesquisarUsuarioActionForm.setNomeUnidadeOrganizacional("");
			sessao.removeAttribute("caminhoRetornoTelaPesquisa");

		}

		if (httpServletRequest.getParameter("mostrarLogin") != null) {
			sessao.setAttribute("mostrarLogin", "S");
		}

		// -------Parte que trata do c�digo quando o usu�rio tecla enter
		String idDigitadoEnterFuncionario = pesquisarUsuarioActionForm
				.getMatriculaFuncionario();

		if (idDigitadoEnterFuncionario != null
				&& !idDigitadoEnterFuncionario.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterFuncionario) > 0) {
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, idDigitadoEnterFuncionario));

			Collection<Funcionario> funcionarioEncontrado = fachada.pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (funcionarioEncontrado != null
					&& !funcionarioEncontrado.isEmpty()) {
				// O funcionario foi encontrado
				pesquisarUsuarioActionForm.setMatriculaFuncionario(""
						+ ((Funcionario) ((List) funcionarioEncontrado).get(0))
								.getId());
				pesquisarUsuarioActionForm
						.setNomeFuncionario(((Funcionario) ((List) funcionarioEncontrado)
								.get(0)).getNome());
				httpServletRequest.setAttribute("idFuncionarioNaoEncontrado",
						"true");

			} else {

				pesquisarUsuarioActionForm.setMatriculaFuncionario("");
				httpServletRequest.setAttribute("idFuncionarioNaoEncontrado",
						"exception");
				pesquisarUsuarioActionForm
						.setNomeFuncionario("FUNCION�RIO INEXISTENTE");

			}

		}
		
		String idUnidadeOrganizacional = pesquisarUsuarioActionForm
				.getIdUnidadeOrganizacional();

		if (idUnidadeOrganizacional != null
				&& !idUnidadeOrganizacional.trim().equals("")
				&& Integer.parseInt(idUnidadeOrganizacional) > 0) {
			
			// Faz a consulta de Unidade Organizacional
			pesquisarUnidadeOrganizacional(httpServletRequest, retorno,
					pesquisarUsuarioActionForm);
		}

		// -------Fim de parte que trata do c�digo quando o usu�rio tecla enter

		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			// se for os parametros de enviarDadosParametros ser�o mandados para
			// a pagina usuario_pesquisar.jsp
			pesquisarUsuarioActionForm
					.setMatriculaFuncionario(httpServletRequest
							.getParameter("idCampoEnviarDados"));
			pesquisarUsuarioActionForm.setNomeFuncionario(httpServletRequest
					.getParameter("descricaoCampoEnviarDados"));
		}

		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaUsuario") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaUsuario",
					httpServletRequest
							.getParameter("caminhoRetornoTelaPesquisaUsuario"));

		}
		sessao.removeAttribute("caminhoRetornoTelaPesquisaFuncionario");

		if (httpServletRequest.getParameter("limpaForm") != null) {
			pesquisarUsuarioActionForm.setMatriculaFuncionario("");
			pesquisarUsuarioActionForm.setNome("");
			pesquisarUsuarioActionForm.setTipoPesquisa("");
			pesquisarUsuarioActionForm.setNomeFuncionario("");
			pesquisarUsuarioActionForm.setLogin("");
			pesquisarUsuarioActionForm.setIdUnidadeOrganizacional("");
			pesquisarUsuarioActionForm.setNomeUnidadeOrganizacional("");
			pesquisarUsuarioActionForm.setTipoUsuario((new Integer(
					ConstantesSistema.NUMERO_NAO_INFORMADO)).toString());
			sessao.removeAttribute("bloquearUnidadeOrganizacional");
		}
		pesquisarUsuarioActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
	        
	        if(httpServletRequest.getParameter("tipo") != null){
				sessao.setAttribute("tipo", httpServletRequest.getParameter("tipo"));
			}

		if (httpServletRequest.getParameter("idUnidadeOrganizacional") != null
				&& !httpServletRequest.getParameter("idUnidadeOrganizacional")
						.trim().equals("")) {

			sessao.setAttribute("bloquearUnidadeOrganizacional", true);

			pesquisarUsuarioActionForm
					.setIdUnidadeOrganizacional(httpServletRequest
							.getParameter("idUnidadeOrganizacional").trim());

			// Faz a consulta de Unidade Organizacional
			pesquisarUnidadeOrganizacional(httpServletRequest, retorno,
					pesquisarUsuarioActionForm);

		}
		
		String popup = (String) sessao.getAttribute("popup");
		if (popup != null && popup.equals("2")) {
			sessao.setAttribute("popup", popup);
		} else {
			sessao.removeAttribute("popup");
		}

		return retorno;

	}

	private void pesquisarUnidadeOrganizacional(
			HttpServletRequest httpServletRequest, ActionForward retorno,
			PesquisarUsuarioActionForm pesquisarUsuarioActionForm) {
		// Filtro para obter o local de armazenagem ativo de id informado
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID,
				new Integer(pesquisarUsuarioActionForm
						.getIdUnidadeOrganizacional())));
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoUnidadeOrganizacional = Fachada.getInstancia()
				.pesquisar(filtroUnidadeOrganizacional,
						UnidadeOrganizacional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoUnidadeOrganizacional != null
				&& !colecaoUnidadeOrganizacional.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util
					.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);

			// Exibe o c�digo e a descri��o pesquisa na p�gina
			pesquisarUsuarioActionForm
					.setIdUnidadeOrganizacional(unidadeOrganizacional.getId()
							.toString());
			pesquisarUsuarioActionForm
					.setNomeUnidadeOrganizacional(unidadeOrganizacional
							.getDescricao());

		} else {

			// Exibe mensagem de c�digo inexiste e limpa o campo de c�digo
			httpServletRequest.setAttribute("unidadeOrganizacionalInexistente",
					true);
			pesquisarUsuarioActionForm.setIdUnidadeOrganizacional("");
			pesquisarUsuarioActionForm
					.setNomeUnidadeOrganizacional("Unidade inexistente");

		}

	}
	

}
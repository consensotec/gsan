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
package gcom.gui.seguranca.acesso.usuario;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que exibe o menu
 * 
 * @author S�vio Luiz
 * @date 02/05/2006
 */
public class ExibirInserirUsuarioAcessosUsuarioAction extends GcomAction {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("inserirUsuarioAcessoUsuario");

		InserirUsuarioDadosGeraisActionForm form = (InserirUsuarioDadosGeraisActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		String[] grupo = (String[]) sessao.getAttribute("grupo");
		if (grupo != null) {
			form.setGrupo(grupo);
		}
		// Usuario que vai ser cadastrado no sistema, usado s� nessa
		// funcionalidade
		Usuario usuarioCadastrar = (Usuario) sessao
				.getAttribute("usuarioCadastrar");
		
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
//		if(usuarioCadastrar.getIndicadorUsuarioBatch() != Short.parseShort("1") && usuarioCadastrar.getIndicadorUsuarioInternet() != Short.parseShort("1") ){
//				throw new ActionServletException(
//						"atencao.required", null,
//						"Indicador para internet");
//		}
		
		if (usuarioCadastrar != null) {

			if (form.getAbrangencia() == null
					|| form.getAbrangencia().equals("")) {
				if (usuarioCadastrar.getUsuarioAbrangencia() != null
						&& usuarioCadastrar.getUsuarioAbrangencia().getId() != null)
					form.setAbrangencia(usuarioCadastrar
							.getUsuarioAbrangencia().getId().toString());
			}
			if (form.getGerenciaRegional() == null
					|| form.getGerenciaRegional().equals("")) {
				if (usuarioCadastrar.getGerenciaRegional() != null
						&& usuarioCadastrar.getGerenciaRegional().getId() != null)
					form.setGerenciaRegional(usuarioCadastrar
							.getGerenciaRegional().getId().toString());
			}

			if (form.getIdElo() != null
					&& !form.getIdElo().equals("")
					&& (usuarioCadastrar.getLocalidadeElo() == null || (usuarioCadastrar
							.getLocalidadeElo() != null
							&& usuarioCadastrar.getLocalidadeElo().getId() != null && !form
							.getIdElo().equals(
									usuarioCadastrar.getLocalidadeElo().getId()
											.toString())))) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, form.getIdElo()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroLocalidade, Localidade.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					Localidade l = (Localidade) coll.iterator().next();
					if (l.getLocalidade() != null
							&& !l.getId().equals(l.getLocalidade().getId())) {
						throw new ActionServletException(
								"atencao.localidade.nao.elo");
					}
					usuarioCadastrar.setLocalidadeElo(l);
					form.setIdElo(l.getId().toString());
					form.setNomeElo(l.getDescricao());
					form.setEloNaoEncontrada("false");
				} else {
					usuarioCadastrar.setLocalidadeElo(null);
					form.setIdElo("");
					form.setNomeElo("Elo inexistente.");
					form.setEloNaoEncontrada("true");
				}
			} else {
				if (form.getIdElo() != null && !form.getIdElo().equals("")) {
					form.setIdElo(usuarioCadastrar.getLocalidadeElo().getId()
							.toString());
					form.setNomeElo(usuarioCadastrar.getLocalidadeElo()
							.getDescricao());
					form.setEloNaoEncontrada("false");
				}
			}

			if (form.getIdLocalidade() != null
					&& !form.getIdLocalidade().equals("")
					&& (usuarioCadastrar.getLocalidade() == null || (usuarioCadastrar
							.getLocalidade() != null
							&& usuarioCadastrar.getLocalidade().getId() != null && !form
							.getIdLocalidade().equals(
									usuarioCadastrar.getLocalidade().getId()
											.toString())))) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, form.getIdLocalidade()));

				Collection coll = Fachada.getInstancia().pesquisar(
						filtroLocalidade, Localidade.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					Localidade l = (Localidade) coll.iterator().next();
					usuarioCadastrar.setLocalidade(l);
					form.setIdLocalidade(l.getId().toString());
					// form.setIdElo("" + l.getLocalidade().getId());
					// form.setIdElo(l.getLocalidade().getDescricao());
					form.setNomeLocalidade(l.getDescricao());
					form.setLocalidadeNaoEncontrada("false");
				} else {
					usuarioCadastrar.setLocalidade(null);
					form.setIdLocalidade("");
					form.setNomeLocalidade(" Localidade inexistente.");
					form.setLocalidadeNaoEncontrada("true");
				}
			} else {
				if (form.getIdLocalidade() != null
						&& !form.getIdLocalidade().equals("")) {
					form.setLocalidadeNaoEncontrada("false");
					form.setIdLocalidade(""
							+ usuarioCadastrar.getLocalidade().getId());
					form.setNomeLocalidade(usuarioCadastrar.getLocalidade()
							.getDescricao());
				}
			}
		}

		if (sessao.getAttribute("collUsuarioAbrangencia") == null
				|| sessao.getAttribute("collUsuarioAbrangencia").equals("")) {

			Collection colecaoUsuarioAbrangencia = null;

			// caso o usu�rio que esteja efetuando a inser��o n�o
			// seja
			// do grupo de administradores
			FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();
			
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_ID, usuario.getId()));
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.GRUPO_ID, Grupo.ADMINISTRADOR));
			Collection colecaoUsuarioGrupo = Fachada.getInstancia().pesquisar(
					filtroUsuarioGrupo, UsuarioGrupo.class.getName());
			if (colecaoUsuarioGrupo != null && !colecaoUsuarioGrupo.isEmpty()) {

				colecaoUsuarioAbrangencia = Fachada.getInstancia().pesquisar(
						new FiltroUsuarioAbrangencia(),
						UsuarioAbrangencia.class.getSimpleName());
			} else {
				Integer idUsuarioAbrangencia = usuario.getUsuarioAbrangencia()
						.getId();
				FiltroUsuarioAbrangencia filtroUsuarioAbrangencia = new FiltroUsuarioAbrangencia();
				filtroUsuarioAbrangencia
						.adicionarParametro(new ParametroSimples(
								FiltroUsuarioAbrangencia.ID,
								idUsuarioAbrangencia));
				colecaoUsuarioAbrangencia = Fachada.getInstancia().pesquisar(
						filtroUsuarioAbrangencia,
						UsuarioAbrangencia.class.getName());
				// caso exista mais de uma abrang�ncia na pesquisa passando o id
				// da
				// abrang�ncia como superior
				boolean mesmoNivel = true;
				Collection usuarioAbrangenciaParaPesquisa = new ArrayList();
				usuarioAbrangenciaParaPesquisa
						.addAll(colecaoUsuarioAbrangencia);
				while (mesmoNivel) {
					Collection colecaoUsuarioAbrangenciaPorSuperior = Fachada
							.getInstancia()
							.pesquisarUsuarioAbrangenciaPorSuperior(
									usuarioAbrangenciaParaPesquisa,
									idUsuarioAbrangencia);
					if (colecaoUsuarioAbrangenciaPorSuperior != null
							&& !colecaoUsuarioAbrangenciaPorSuperior.isEmpty()) {
						colecaoUsuarioAbrangencia
								.addAll(colecaoUsuarioAbrangenciaPorSuperior);
						usuarioAbrangenciaParaPesquisa = new ArrayList();
						usuarioAbrangenciaParaPesquisa
								.addAll(colecaoUsuarioAbrangenciaPorSuperior);
					} else {
						mesmoNivel = false;
					}
				}
			}
			// caso exista mais de uma abrang�ncia na pesquisa passando o id da
			// abrang�ncia como superior

			sessao.setAttribute("collUsuarioAbrangencia",
					colecaoUsuarioAbrangencia);

			Collection colecaoGrupo = null;

			// caso o usu�rio que esteja efetuando a inser��o n�o
			// seja
			// do grupo de administradores
			if (colecaoUsuarioGrupo != null && !colecaoUsuarioGrupo.isEmpty()) {
				FiltroGrupo filtroGrupo = new FiltroGrupo();
				filtroGrupo.setCampoOrderBy(FiltroGrupo.DESCRICAO);
				filtroGrupo.adicionarParametro(new ParametroSimples(
						FiltroGrupo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				colecaoGrupo = Fachada.getInstancia().pesquisar(
						filtroGrupo, Grupo.class.getSimpleName());
			} else {
				Collection colecaoGrupoUsuario = (Collection)sessao.getAttribute("colecaoGruposUsuario");
				colecaoGrupo = Fachada.getInstancia().pesquisarGruposUsuarioAcesso(
						colecaoGrupoUsuario);
			}
			sessao.setAttribute("collGrupo", colecaoGrupo);

		}

		if (sessao.getAttribute("collGerenciaRegional") == null
				|| sessao.getAttribute("collGerenciaRegional").equals("")) {

			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoGerenciaRegional = Fachada.getInstancia()
					.pesquisar(filtroGerenciaRegional,
							GerenciaRegional.class.getSimpleName());
			sessao.setAttribute("collGerenciaRegional",
					colecaoGerenciaRegional);
		}

		if (sessao.getAttribute("collUnidadeNegocio") == null
				|| sessao.getAttribute("collUnidadeNegocio").equals("")) {

			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUnidadeNegocio = Fachada.getInstancia()
					.pesquisar(filtroUnidadeNegocio,
							UnidadeNegocio.class.getSimpleName());
			sessao.setAttribute("collUnidadeNegocio",
					colecaoUnidadeNegocio);
		}

		return retorno;
	}
}
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

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroPemissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela pre-exibi��o das permiss�es especiais do usu�rio.
 * 
 * @author S�vio Luiz
 * @date 12/07/2006
 */
public class ExibirControlarPermissoesEspeciaisUsuarioAction extends GcomAction {

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
				.findForward("controlarPermissoesEspeciaisUsuario");

		ControlarAcessoUsuarioActionForm controlarAcessoUsuarioActionForm = (ControlarAcessoUsuarioActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// Recupera os acessos do grupo da sess�o
		//Collection grupoFuncionalidades = (Collection) sessao
		//		.getAttribute("grupoFuncionalidades");

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Usuario que vai ser cadastrado no sistema, usado s� nessa
		// funcionalidade
		Usuario usuarioParaAtualizar = (Usuario) sessao
				.getAttribute("usuarioParaAtualizar");

		Collection colecaoPermissaoEspecial = null;

		Collection colecaoPermissaoEspecialDesalibitado = null;
		
		String permissoesCheckBoxVazias = controlarAcessoUsuarioActionForm
		.getPermissoesCheckBoxVazias(); 

		String[] permissaoEspecial = controlarAcessoUsuarioActionForm
				.getPermissoesEspeciais();

		if (permissaoEspecial == null && permissoesCheckBoxVazias == null) {

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

				FiltroPemissaoEspecial filtroPemissaoEspecial = new FiltroPemissaoEspecial(FiltroPemissaoEspecial.DESCRICAO);
				filtroPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroPemissaoEspecial.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO)); 

				colecaoPermissaoEspecial = Fachada.getInstancia().pesquisar(
						filtroPemissaoEspecial,
						PermissaoEspecial.class.getName());

				FiltroUsuarioPemissaoEspecial filtroUsuarioPermissaoEspecialAtualizar = new FiltroUsuarioPemissaoEspecial();
				filtroUsuarioPermissaoEspecialAtualizar
						.adicionarCaminhoParaCarregamentoEntidade("permissaoEspecial");
				filtroUsuarioPermissaoEspecialAtualizar
						.adicionarParametro(new ParametroSimples(
								FiltroUsuarioPemissaoEspecial.USUARIO_COMP_ID,
								usuarioParaAtualizar.getId()));
				// colecao de usu�rio permiss�o especial
				Collection colecaoUsuarioPermissaoEspecialParaAtualizar = Fachada
						.getInstancia().pesquisar(
								filtroUsuarioPermissaoEspecialAtualizar,
								UsuarioPermissaoEspecial.class.getName());
				if (colecaoUsuarioPermissaoEspecialParaAtualizar != null
						&& !colecaoUsuarioPermissaoEspecialParaAtualizar
								.isEmpty()) {
					
					Collection colecaoPermissaoEspecialAux = new ArrayList();
					
					Iterator ite = colecaoUsuarioPermissaoEspecialParaAtualizar.iterator();
					while(ite.hasNext()){
						UsuarioPermissaoEspecial usuarioPermissaoEspecial = (UsuarioPermissaoEspecial)ite.next();
						colecaoPermissaoEspecialAux.add(usuarioPermissaoEspecial.getPermissaoEspecial());		
					}
					// seta os campos de permiss�o especial no form para
					// aparecer no
					// jsp como checado
					permissaoEspecial = Fachada
							.getInstancia()
							.retornarPermissoesMarcadas(
									colecaoPermissaoEspecialAux);

				}
			} else {
				Object[] permissoesEspeciais = Fachada.getInstancia()
						.pesquisarPermissoesEspeciaisUsuarioEUsuarioLogado(
								usuarioParaAtualizar, usuario);
				colecaoPermissaoEspecial = (Collection) permissoesEspeciais[0];
				colecaoPermissaoEspecialDesalibitado = (Collection) permissoesEspeciais[1];
				permissaoEspecial = (String[]) permissoesEspeciais[2];
				/*
				 * // caso o usu�rio n�o seja do grupos de administradores
				 * FiltroUsuarioPemissaoEspecial
				 * filtroUsuarioPemissaoEspecialLogado = new
				 * FiltroUsuarioPemissaoEspecial();
				 * filtroUsuarioPemissaoEspecialLogado .adicionarParametro(new
				 * ParametroSimples(
				 * FiltroUsuarioPemissaoEspecial.USUARIO_COMP_ID,
				 * usuario.getId())); filtroUsuarioPemissaoEspecialLogado
				 * .adicionarCaminhoParaCarregamentoEntidade("permissaoEspecial"); //
				 * recupera as permiss�es do usuario logado Collection
				 * colecaoUsuarioLogadoPermissaoEspecial =
				 * Fachada.getInstancia()
				 * .pesquisar(filtroUsuarioPemissaoEspecialLogado,
				 * UsuarioPermissaoEspecial.class.getName());
				 * 
				 * FiltroUsuarioPemissaoEspecial filtroUsuarioPermissaoEspecial =
				 * new FiltroUsuarioPemissaoEspecial();
				 * filtroUsuarioPermissaoEspecial .adicionarParametro(new
				 * ParametroSimples(
				 * FiltroUsuarioPemissaoEspecial.USUARIO_COMP_ID,
				 * usuarioParaAtualizar.getId()));
				 * filtroUsuarioPermissaoEspecial
				 * .adicionarCaminhoParaCarregamentoEntidade("usuario");
				 * filtroUsuarioPemissaoEspecialLogado
				 * .adicionarCaminhoParaCarregamentoEntidade("permissaoEspecial"); //
				 * colecao de usu�rio permiss�o especial Collection
				 * colecaoUsuarioPermissaoEspecial =
				 * Fachada.getInstancia().pesquisar(
				 * filtroUsuarioPermissaoEspecial,
				 * UsuarioPermissaoEspecial.class.getName()); if
				 * (colecaoUsuarioPermissaoEspecial != null &&
				 * !colecaoUsuarioPermissaoEspecial.isEmpty()) { // remove os
				 * usuario permiss�o especial da cole��o de // usu�rio // logado
				 * com permiss�o especial que tenha na cole��o de // usu�rio //
				 * permiss�o usu�rio que est� sendo atualizado Iterator
				 * iteratorUsuarioPermissaoEspecialLogado =
				 * colecaoUsuarioLogadoPermissaoEspecial.iterator(); // seta os
				 * campos de permiss�o especial no form para // aparecer no jsp
				 * como checado Iterator iteratorUsuarioPermissaoEspecial =
				 * colecaoPermissaoEspecial .iterator(); int i = 0;
				 * permissaoEspecial = new String[colecaoPermissaoEspecial
				 * .size()];
				 * 
				 * while (iteratorUsuarioPermissaoEspecial.hasNext()) {
				 * UsuarioPermissaoEspecial usuarioPermissaoEspecialObject =
				 * (UsuarioPermissaoEspecial) iteratorUsuarioPermissaoEspecial
				 * .next(); permissaoEspecial[i] = "" +
				 * usuarioPermissaoEspecialObject.getComp_id()
				 * .getPermissaoEspecialId(); } }
				 */
			}
			// seta os campos que v�o aparecer como checado
			controlarAcessoUsuarioActionForm
					.setPermissoesEspeciais(permissaoEspecial);
			sessao.setAttribute("colecaoPermissaoEspecial",
					colecaoPermissaoEspecial);
			sessao.setAttribute("colecaoPermissaoEspecialDesalibitado",
					colecaoPermissaoEspecialDesalibitado);
		}

		return retorno;
	}
}
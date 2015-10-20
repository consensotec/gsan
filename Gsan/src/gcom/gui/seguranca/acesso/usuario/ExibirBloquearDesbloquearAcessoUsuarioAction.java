/**
 * 
 */
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
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.util.ControladorException;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 08/06/2006
 */
public class ExibirBloquearDesbloquearAcessoUsuarioAction extends GcomAction {

	/**
	 * Este caso de uso permite bloquear ou desbloquear o acesso do usu�rio ao
	 * sistema.
	 * 
	 * [UC0291] Bloquear/Desbloquear Acesso
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 08/06/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws ControladorException
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
			throws ControladorException {

		ActionForward retorno = actionMapping
				.findForward("bloquearDesbloquearAcessoUsuario");

		Fachada fachada = Fachada.getInstancia();

		BloquearDesbloquearAcessoUsuarioActionForm bloquearDesbloquearAcessoUsuarioActionForm = (BloquearDesbloquearAcessoUsuarioActionForm) actionForm;

		FiltroUsuarioSituacao filtroUsuarioSituacao = new FiltroUsuarioSituacao();
		
		filtroUsuarioSituacao.adicionarParametro(new ParametroSimples(FiltroUsuarioSituacao.INDICADOR_DE_USO_SISTEMA, "2"));

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<UsuarioSituacao> colecaoUsuarioSituacao = fachada.pesquisar(
				filtroUsuarioSituacao, UsuarioSituacao.class.getName());

		if (colecaoUsuarioSituacao == null || colecaoUsuarioSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Usu�rio Situa��o");
		}

		httpServletRequest.setAttribute("colecaoUsuarioSituacao",
				colecaoUsuarioSituacao);

		Usuario usuario = new Usuario();

		
		
		String login =  bloquearDesbloquearAcessoUsuarioActionForm
		.getLogin();

		if (login != null && !login.equals("")) {
			

			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ComparacaoTexto(
					FiltroUsuario.LOGIN, login));

			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			
			if (colecaoUsuario == null || colecaoUsuario.isEmpty()) {
				throw new ActionServletException("atencao.login_nao_existente",
						null, "" + login + "");
			} else {
				Iterator colecaoUsuarioIterator = colecaoUsuario.iterator(); 
					while( colecaoUsuarioIterator.hasNext() ){
						usuario = (Usuario) colecaoUsuarioIterator.next();
					
						if ( usuario.getLogin().equalsIgnoreCase(login) ) {
							
							if (usuario.getUsuarioSituacao() != null &&
									usuario.getUsuarioSituacao().getId().equals(UsuarioSituacao.INATIVO) || 
										usuario.getDataCadastroFim().compareTo(new Date()) < 0){
								
								bloquearDesbloquearAcessoUsuarioActionForm.setLogin("");
									throw new ActionServletException("atencao.usuario_invalido", "" + login + "");
							}
							
							bloquearDesbloquearAcessoUsuarioActionForm
								.setUsuarioSituacao(usuario.getUsuarioSituacao()
										.getId().toString());
						}
					}
				}
			}

		return retorno;

	}

}

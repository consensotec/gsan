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
package gcom.gui.cobranca;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rafael Corr�a
 * @since 22/08/2008
 */
public class ExibirConsultarTransferenciasDebitoAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarTransferenciasDebito");
		
		ConsultarTransferenciasDebitoActionForm consultarTransferenciasDebitoActionForm = (ConsultarTransferenciasDebitoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		sessao.removeAttribute("colecaoContasTransferidas");
		sessao.removeAttribute("colecaoDebitosACobrarTransferidos");
		sessao.removeAttribute("colecaoGuiasPagamentoTransferidas");
		sessao.removeAttribute("colecaoCreditosARealizarTransferidos");
		sessao.removeAttribute("consultarTransferenciasDebitoHelper");
		
		String idImovelOrigem = consultarTransferenciasDebitoActionForm.getIdImovelOrigem();
		String idImovelDestino = consultarTransferenciasDebitoActionForm.getIdImovelDestino();
		String idUsuario = consultarTransferenciasDebitoActionForm.getIdUsuario();
		String loginUsuario = consultarTransferenciasDebitoActionForm.getLoginUsuario();
		
		// Pesquisa Im�vel Origem
		if (idImovelOrigem != null && !idImovelOrigem.trim().equals("")) {
			
			String inscricao = fachada.pesquisarInscricaoImovel(new Integer(idImovelOrigem));
			
			if (inscricao != null && !inscricao.trim().equals("")) {
				consultarTransferenciasDebitoActionForm.setInscricaoImovelOrigem(inscricao);
				httpServletRequest.setAttribute("nomeCampo", "idImovelDestino");
			} else {
				consultarTransferenciasDebitoActionForm.setIdImovelOrigem("");
				consultarTransferenciasDebitoActionForm.setInscricaoImovelOrigem("IM�VEL INEXISTENTE");
				
				httpServletRequest.setAttribute("imovelOrigemInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idImovelOrigem");
			}
			
		} else {
			consultarTransferenciasDebitoActionForm.setInscricaoImovelOrigem("");
		}
		
		// Pesquisa Im�vel Destino
		if (idImovelDestino != null && !idImovelDestino.trim().equals("")) {
			
			String inscricao = fachada.pesquisarInscricaoImovel(new Integer(idImovelDestino));
			
			if (inscricao != null && !inscricao.trim().equals("")) {
				consultarTransferenciasDebitoActionForm.setInscricaoImovelDestino(inscricao);
				httpServletRequest.setAttribute("nomeCampo", "dataInicio");
			} else {
				consultarTransferenciasDebitoActionForm.setIdImovelDestino("");
				consultarTransferenciasDebitoActionForm.setInscricaoImovelDestino("IM�VEL INEXISTENTE");
				
				httpServletRequest.setAttribute("imovelDestinoInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idImovelDestino");
			}
			
		} else {
			consultarTransferenciasDebitoActionForm.setInscricaoImovelDestino("");
		}
		
		// Pesquisa o usu�rio
		if ((loginUsuario != null && !loginUsuario.trim().equals("")) || (idUsuario != null && !idUsuario.trim().equals(""))) {
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			
			// Verifica se a pesquisa foi pelo enter
			if (loginUsuario != null && !loginUsuario.trim().equals("")) {
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, loginUsuario));
			} 
			// Verifica se a pesquisa foi feita pela lupa
			else {
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, idUsuario));
			}
			
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				consultarTransferenciasDebitoActionForm.setIdUsuario(usuario.getId().toString());
				consultarTransferenciasDebitoActionForm.setLoginUsuario(usuario.getLogin());
				consultarTransferenciasDebitoActionForm.setNomeUsuario(usuario.getNomeUsuario());
			} else {
				consultarTransferenciasDebitoActionForm.setIdUsuario("");
				consultarTransferenciasDebitoActionForm.setLoginUsuario("");
				consultarTransferenciasDebitoActionForm.setNomeUsuario("USU�RIO INEXISTENTE");
				
				httpServletRequest.setAttribute("usuarioInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "loginUsuario");
			}
			
		} else {
			consultarTransferenciasDebitoActionForm.setNomeUsuario("");
		}

		return retorno;

	}

}

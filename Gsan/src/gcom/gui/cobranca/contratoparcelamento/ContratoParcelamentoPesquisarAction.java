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
package gcom.gui.cobranca.contratoparcelamento;

import gcom.cadastro.cliente.Cliente;
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
 * Action que define o pr�-processamento da p�gina de pesquisa de contrato parcelamento
 * 
 * @author Paulo Diniz
 * @created 24/05/2011
 */

public class ContratoParcelamentoPesquisarAction extends GcomAction {

	Fachada fachada = Fachada.getInstancia();
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Inicializando Variaveis
		ActionForward retorno = actionMapping.findForward("contratoParcelamentoPesquisar");
		PesquisarContratoParcelamentoActionForm pesquisarContratoParcelamentoActionForm = (PesquisarContratoParcelamentoActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(httpServletRequest.getParameter("indicadorPesquisaApenasContEncerrados") != null){
			sessao.setAttribute("indicadorPesquisaApenasContEncerrados", httpServletRequest.getParameter("indicadorPesquisaApenasContEncerrados"));
		}
		
		if (httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S")) {

			pesquisarContratoParcelamentoActionForm.setDataContrato("");
			pesquisarContratoParcelamentoActionForm.setIndicadorSituacao("");
			pesquisarContratoParcelamentoActionForm.setNumeroContrato("");
			pesquisarContratoParcelamentoActionForm.setLoginUsuario("");
			pesquisarContratoParcelamentoActionForm.setNomeUsuario("");
			pesquisarContratoParcelamentoActionForm.setAutocompleteCliente("");
			sessao.removeAttribute("caminhoRetornoTelaPesquisa");
			sessao.removeAttribute("usuarioResponsavel");
			sessao.removeAttribute("cliente");

		}

		if (httpServletRequest.getParameter("limparForm") != null) {
			pesquisarContratoParcelamentoActionForm.setDataContrato("");
			pesquisarContratoParcelamentoActionForm.setIndicadorSituacao("");
			pesquisarContratoParcelamentoActionForm.setNumeroContrato("");
			pesquisarContratoParcelamentoActionForm.setLoginUsuario("");
			pesquisarContratoParcelamentoActionForm.setNomeUsuario("");
			pesquisarContratoParcelamentoActionForm.setAutocompleteCliente("");
			sessao.removeAttribute("usuarioResponsavel");
			sessao.removeAttribute("cliente");
		}
		
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaUsuario") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaUsuario",
					httpServletRequest
							.getParameter("caminhoRetornoTelaPesquisaUsuario"));

		}
		
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaCliente") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaCliente",
					httpServletRequest
							.getParameter("caminhoRetornoTelaPesquisaCliente"));

		}
		if (httpServletRequest.getParameter("consulta") != null
				&& httpServletRequest.getParameter("consulta").toString().trim().equalsIgnoreCase("usuario")) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, 
					pesquisarContratoParcelamentoActionForm.getLoginUsuario()));
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			// [FS0009] - Verificar exist�ncia do usu�rio
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				
				pesquisarContratoParcelamentoActionForm.setNomeUsuario(usuario.getNomeUsuario());
				pesquisarContratoParcelamentoActionForm.setLoginUsuario(usuario.getLogin());

				sessao.setAttribute("usuarioResponsavel", usuario);
				sessao.setAttribute("usuarioEncontrado","true");
			} else {
				pesquisarContratoParcelamentoActionForm.setLoginUsuario("");
				pesquisarContratoParcelamentoActionForm.setNomeUsuario("Usu�rio Inexistente");

				sessao.setAttribute("usuarioResponsavel", null);
				sessao.removeAttribute("usuarioEncontrado");
			}
			sessao.setAttribute("etapa", "primeira");
			
		} 
		
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("") 
				&& httpServletRequest.getParameter("tipoConsulta").equals("usuario")) {
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, 
					httpServletRequest.getParameter("idCampoEnviarDados")));
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);

				pesquisarContratoParcelamentoActionForm.setLoginUsuario(usuario.getLogin());
				pesquisarContratoParcelamentoActionForm.setNomeUsuario(usuario.getNomeUsuario());
				
				sessao.setAttribute("usuarioResponsavel", usuario);
				sessao.setAttribute("usuarioEncontrado","true");
			} else {
				pesquisarContratoParcelamentoActionForm.setLoginUsuario("");
				pesquisarContratoParcelamentoActionForm.setNomeUsuario("Usu�rio Inexistente");

				sessao.setAttribute("usuarioResponsavel", null);
				sessao.removeAttribute("usuarioEncontrado");
			}
			sessao.removeAttribute("caminhoRetornoTelaPesquisaUsuario");
			
		}
		
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("") 
				&& httpServletRequest.getParameter("tipoConsulta").equals("cliente")) {
			int id = Integer.parseInt(httpServletRequest.getParameter("idCampoEnviarDados"));
			Cliente cliente = fachada.consultarCliente(id);
			cliente.setId(id);
			sessao.removeAttribute("caminhoRetornoTelaPesquisaCliente");
			sessao.setAttribute("cliente", cliente);
		}
		

		String popup = (String) sessao.getAttribute("popup");
		if (popup != null && popup.equals("2")) {
			sessao.setAttribute("popup", popup);
		} else {
			sessao.removeAttribute("popup");
		}

		return retorno;

	}

}
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
 * R�mulo Aur�lio de Melo Souza Filho
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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
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
 * [UC0???]Manter Unidade Negocio
 * 
 * @author R�mulo Aur�lio
 * @date 30/09/2008
 */

public class ExibirAtualizarUnidadeNegocioAction extends GcomAction {

	/**
	 * M�todo responsavel por responder a requisicao
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
				.findForward("unidadeNegocioAtualizar");

		AtualizarUnidadeNegocioActionForm form = (AtualizarUnidadeNegocioActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		String id = null;

		String idCliente = form.getIdCliente();
		
		if (idCliente == null ) {
			idCliente = "";
		}

		String idGerenciaRegional = form.getIdGerenciaRegional();
		if (idGerenciaRegional == null ) {
			idGerenciaRegional = "";
		}
		
		if (idCliente != null && !idCliente.trim().equals("")) {
			// Pesquisa o cliente na base
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
				Cliente cliente = (Cliente) colecaoCliente.iterator().next();

				form.setIdCliente(cliente.getId().toString());

				form.setNomeCliente(cliente.getNome());
			} else {
				form.setIdCliente("");
				form.setNomeCliente("Cliente inexistente");

				httpServletRequest.setAttribute("corCliente", "exception");
				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			}
		}

		if (idGerenciaRegional != null && !idGerenciaRegional.trim().equals("")) {
			// Pesquisa o cliente na base
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, idGerenciaRegional));

			Collection colecaoGerenciaRegional = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoGerenciaRegional != null
					&& !colecaoGerenciaRegional.isEmpty()) {

				GerenciaRegional gerenciaRegional = (GerenciaRegional) colecaoGerenciaRegional
						.iterator().next();

				form.setIdGerenciaRegional(gerenciaRegional.getId().toString());

				form.setNomeGerenciaRegional(gerenciaRegional.getNome());
			} else {
				form.setIdGerenciaRegional("");
				form.setNomeGerenciaRegional("Ger�ncia Regional inexistente");

				httpServletRequest.setAttribute("corGerenciaRegional",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"idGerenciaRegional");
			}

		}

		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
			UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
			unidadeNegocio.setId(new Integer(id));
			sessao.setAttribute("unidadeNegocio", unidadeNegocio);
		} else {
			id = ((UnidadeNegocio) sessao.getAttribute("unidadeNegocio"))
					.getId().toString();
		}

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
		
		
		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.ID, id));
			filtroUnidadeNegocio
					.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeNegocio.CLIENTE);
			filtroUnidadeNegocio
					.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeNegocio.GERENCIA);
			Collection colecaoUnidadeNegocio = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			if (colecaoUnidadeNegocio != null
					&& !colecaoUnidadeNegocio.isEmpty()) {
				unidadeNegocio = (UnidadeNegocio) Util
						.retonarObjetoDeColecao(colecaoUnidadeNegocio);
			}
			
			
			if ( idCliente.equals("") || unidadeNegocio.getCliente() != null  && idCliente == ""+ unidadeNegocio.getCliente().getId()) {
				
				if (  idGerenciaRegional.equals("") || unidadeNegocio.getCliente() != null && idGerenciaRegional == ""+ unidadeNegocio.getGerenciaRegional().getId()) {
			
					if (id == null || id.trim().equals("")) {
		
						form.setId(unidadeNegocio.getId().toString());
		
						form.setNome(unidadeNegocio.getNome());
		
						form.setNomeAbreviado(unidadeNegocio.getNomeAbreviado());
		
						if (unidadeNegocio.getCliente() != null) {
		
							form.setIdCliente(unidadeNegocio.getCliente().getId()
									.toString());
		
							form.setNomeCliente(unidadeNegocio.getCliente().getNome());
		
						}
		
						form.setIdGerenciaRegional(unidadeNegocio.getGerenciaRegional()
								.getId().toString());
		
						form.setNomeGerenciaRegional(unidadeNegocio
								.getGerenciaRegional().getNome());
		
						form.setIndicadorUso(unidadeNegocio.getIndicadorUso()
								.toString());
		
						if (unidadeNegocio.getCnpj() != null) {
							form.setNumeroCnpj(unidadeNegocio.getCnpj());
						} else {
							form.setNumeroCnpj("");
						}
		
					}
		
					form.setId(unidadeNegocio.getId().toString());
		
					form.setNome(unidadeNegocio.getNome());
		
					form.setNomeAbreviado(unidadeNegocio.getNomeAbreviado());
		
					if (unidadeNegocio.getCliente() != null) {
						form.setIdCliente(unidadeNegocio.getCliente().getId()
								.toString());
		
						form.setNomeCliente(unidadeNegocio.getCliente().getNome());
					}
		
					form.setIdGerenciaRegional(unidadeNegocio.getGerenciaRegional()
							.getId().toString());
		
					form.setNomeGerenciaRegional(unidadeNegocio.getGerenciaRegional()
							.getNome());
		
					form.setIndicadorUso(unidadeNegocio.getIndicadorUso().toString());
					
					if (unidadeNegocio.getCnpj() != null) {
						form.setNumeroCnpj(unidadeNegocio.getCnpj());
					} else {
						form.setNumeroCnpj("");
					}
					sessao.setAttribute("atualizarUnidadeNegocio", unidadeNegocio);
		
					if (sessao.getAttribute("colecaoUnidadeNegocio") != null) {
						sessao.setAttribute("caminhoRetornoVoltar",
								"/gsan/filtrarUnidadeNegocioAction.do");
					} else {
						sessao.setAttribute("caminhoRetornoVoltar",
								"/gsan/exibirFiltrarUnidadeNegocioAction.do");
					}
				}
			}
		}
		
		
		if ( httpServletRequest.getParameter("desfazer") != null &&
        		httpServletRequest.getParameter("desfazer").equals("S") ) {

			form.setId(unidadeNegocio.getId().toString());

			form.setNome(unidadeNegocio.getNome());

			form.setNomeAbreviado(unidadeNegocio.getNomeAbreviado());

			if (unidadeNegocio.getCliente() != null) {
				form.setIdCliente(unidadeNegocio.getCliente().getId()
						.toString());

				form.setNomeCliente(unidadeNegocio.getCliente().getNome());
			}

			form.setIdGerenciaRegional(unidadeNegocio.getGerenciaRegional()
					.getId().toString());

			form.setNomeGerenciaRegional(unidadeNegocio.getGerenciaRegional()
					.getNome());

			form.setIndicadorUso(unidadeNegocio.getIndicadorUso().toString());
			
			if (unidadeNegocio.getCnpj() != null) {
				form.setNumeroCnpj(unidadeNegocio.getCnpj());
			} else {
				form.setNumeroCnpj("");
			}
		}

		return retorno;
	}
		

}

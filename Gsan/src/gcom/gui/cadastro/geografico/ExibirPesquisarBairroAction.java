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
package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os
 * par�metros para realiza��o da pesquisa dos bairros
 * 
 * @author Raphael Rossiter
 * @date 28/06/2006
 */
public class ExibirPesquisarBairroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("pesquisarBairro");

		Fachada fachada = Fachada.getInstancia();

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// Verifica se o pesquisar bairro foi chamado a partir do inserir bairro
		// e em caso afirmativo recebe o par�metro e manda-o pela sess�o para
		// ser verificado no bairro_resultado_pesquisa e depois retirado da
		// sess�o no ExibirFiltrarBairroAction
		if (httpServletRequest.getParameter("consulta") != null) {
			String consulta = httpServletRequest.getParameter("consulta");
			sessao.setAttribute("consulta", consulta);
		} else {
			if (httpServletRequest.getParameter("limparForm") == null
					&& httpServletRequest.getParameter("novaPesquisa") == null
					&& httpServletRequest.getParameter("pesquisarMunicipio") == null) {
				sessao.removeAttribute("consulta");
			}
		}

		/*
		 * Caso o par�metro "Munic�pio" seja previamente definido pelo caso de
		 * uso que chama est� funcionalidade, o mesmo dever� ser mantido para
		 * realiza��o da filtragem dos bairros
		 */
		if (httpServletRequest.getParameter("objetoConsulta") == null
				&& httpServletRequest.getParameter("tipoConsulta") == null) {
			//pesquisarActionForm.set("idMunicipio", "");
			pesquisarActionForm.set("nomeMunicipio", "");
			pesquisarActionForm.set("nomeBairro", "");

		}

		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {

			// Verifica se o tipo da consulta de imovel � de municipio
			// se for os parametros de enviarDadosParametros ser�o mandados para
			// a pagina logradouro_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"municipio")) {
				pesquisarActionForm.set("idMunicipio", httpServletRequest
						.getParameter("idCampoEnviarDados"));
				pesquisarActionForm.set("descricaoMunicipio",
						httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));
			}
		}

		String idMunicipio = null;
		if (httpServletRequest.getParameter("idMunicipio") != null
				&& !httpServletRequest.getParameter("idMunicipio").trim()
						.equalsIgnoreCase("")) {
			idMunicipio = (String) httpServletRequest
					.getParameter("idMunicipio");
		} else {
			if (httpServletRequest.getAttribute("idMunicipio") != null
					&& !httpServletRequest.getAttribute("idMunicipio").equals(
							"")) {
				idMunicipio = (String) httpServletRequest
						.getAttribute("idMunicipio");
			}
		}

		if (idMunicipio != null
				&& !idMunicipio.trim().equalsIgnoreCase("")
				&& httpServletRequest.getParameter("pesquisarMunicipio") == null
				&& httpServletRequest.getParameter("limparForm") == null) {

			sessao.setAttribute("municipioRecebido", idMunicipio);
		}

		if (idMunicipio != null && !idMunicipio.equals("")
				&& httpServletRequest.getParameter("limparForm") == null) {

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idMunicipio));

			Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

				Municipio municipio = (Municipio) Util
						.retonarObjetoDeColecao(colecaoMunicipio);

				pesquisarActionForm.set("idMunicipio", municipio.getId()
						.toString());
				pesquisarActionForm.set("descricaoMunicipio", municipio
						.getNome());

				httpServletRequest.setAttribute("nomeCampo", "nomeBairro");
			} else {

				pesquisarActionForm.set("idMunicipio", "");
				pesquisarActionForm.set("descricaoMunicipio",
						"MUNIC�PIO INEXISTENTE");

				httpServletRequest.setAttribute("nomeCampo", "idMunicipio");
				httpServletRequest.setAttribute("idMunicipioNaoEncontrado",
						"exception");
			}
		}

		if (httpServletRequest.getParameter("limparForm") != null
				&& sessao.getAttribute("municipioRecebido") == null
				&& httpServletRequest.getParameter("objetoConsulta") == null) {

			pesquisarActionForm.set("idMunicipio", "");
			pesquisarActionForm.set("descricaoMunicipio", "");
			pesquisarActionForm.set("nomeBairro", "");

			httpServletRequest.setAttribute("nomeCampo", "idMunicipio");
		} else if (httpServletRequest.getParameter("limparForm") != null
				&& sessao.getAttribute("municipioRecebido") != null
				&& httpServletRequest.getParameter("objetoConsulta") == null) {

			pesquisarActionForm.set("nomeBairro", "");

			httpServletRequest.setAttribute("nomeCampo", "nomeBairro");
		}

		/*
		 * Nova Pesquisa (Limpa o formul�rio caso o usu�rio clique no bot�o de
		 * "Nova Pesquisa" no resulta da consulta realizada)
		 */
		if (httpServletRequest.getParameter("novaPesquisa") != null
				&& sessao.getAttribute("municipioRecebido") == null
				&& httpServletRequest.getParameter("objetoConsulta") == null) {

			pesquisarActionForm.set("idMunicipio", "");
			pesquisarActionForm.set("descricaoMunicipio", "");
			pesquisarActionForm.set("nomeBairro", "");

			httpServletRequest.setAttribute("nomeCampo", "idMunicipio");
		} else if (httpServletRequest.getParameter("novaPesquisa") != null
				&& sessao.getAttribute("municipioRecebido") != null
				&& httpServletRequest.getParameter("objetoConsulta") == null) {

			pesquisarActionForm.set("nomeBairro", "");

			httpServletRequest.setAttribute("nomeCampo", "nomeBairro");
		}

		/*
		 * Envia uma flag que ser� verificada no bairro_resultado_pesquisa.jsp
		 * para saber se ir� usar o enviar dados ou o enviar dados parametros
		 */
		if (httpServletRequest.getParameter("tipo") != null) {
			sessao
					.setAttribute("tipo", httpServletRequest
							.getParameter("tipo"));
		}

		/*
		 * Define o retorno da pesquisa
		 */

		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaBairro") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaBairro",
					httpServletRequest
							.getParameter("caminhoRetornoTelaPesquisaBairro"));
		}
		/*
		 * else { sessao.removeAttribute("caminhoRetornoTelaPesquisaBairro");
		 * sessao.setAttribute("tipo", ""); }
		 */

		if (httpServletRequest.getParameter("indicadorUsoTodos") != null) {
			sessao.setAttribute("indicadorUsoTodos", httpServletRequest
					.getParameter("indicadorUsoTodos"));
		}

		if (pesquisarActionForm.get("tipoPesquisa") == null
				|| pesquisarActionForm.get("tipoPesquisa").equals("")) {

			pesquisarActionForm.set("tipoPesquisa",
					ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

		}

		return retorno;
	}
}
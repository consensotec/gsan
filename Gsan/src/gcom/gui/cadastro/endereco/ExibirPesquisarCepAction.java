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
package gcom.gui.cadastro.endereco;

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

/**
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os
 * par�metros para realiza��o da pesquisa de CEPs
 * 
 * @author Raphael Rossiter
 * @date 05/05/2006
 */
public class ExibirPesquisarCepAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirPesquisarCep");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		PesquisarCepActionForm pesquisarCepActionForm = (PesquisarCepActionForm) actionForm;

		if (httpServletRequest.getParameter("voltaFiltro") == null
				&& httpServletRequest.getParameter("pesquisaMunicipio") == null) {
			pesquisarCepActionForm.setIdMunicipio("");
			pesquisarCepActionForm.setNomeMunicipio("");
			pesquisarCepActionForm.setNomeLogradouro("");
		}

		/*
		 * Caso o par�metro "Munic�pio" seja previamente definido pelo caso de
		 * uso que chama est� funcionalidade, o mesmo dever� ser mantido para
		 * realiza��o da pesquisa dos CEPs
		 */
		String idMunicipio = null;
		if(httpServletRequest
				.getParameter("idMunicipioDefinido") != null){
		idMunicipio = httpServletRequest
				.getParameter("idMunicipioDefinido");
		}else{
			idMunicipio = (String)httpServletRequest
			.getAttribute("idMunicipioDefinido");
		}

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		Collection colecaoMunicipio = null;
		Municipio municipio = null;
		if (httpServletRequest.getParameter("indicadorUsoTodos") != null) {
			sessao.setAttribute("indicadorUsoTodos", httpServletRequest
					.getParameter("indicadorUsoTodos"));
		}
		if (idMunicipio != null && !idMunicipio.equals("")) {

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idMunicipio));
			if (sessao.getAttribute("indicadorUsoTodos") == null) {
				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				sessao.removeAttribute("indicadorUsoTodos");
			}

			colecaoMunicipio = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

				sessao.setAttribute("municipioInformado", idMunicipio);

				municipio = (Municipio) Util
						.retonarObjetoDeColecao(colecaoMunicipio);

				pesquisarCepActionForm.setIdMunicipio(String.valueOf(municipio
						.getId()));
				pesquisarCepActionForm.setNomeMunicipio(municipio.getNome());
			}

		} else if (pesquisarCepActionForm.getIdMunicipio() != null
				&& !pesquisarCepActionForm.getIdMunicipio().equals("")) {

			filtroMunicipio
					.adicionarParametro(new ParametroSimples(
							FiltroMunicipio.ID, pesquisarCepActionForm
									.getIdMunicipio()));

			if (sessao.getAttribute("indicadorUsoTodos") == null) {
				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				sessao.removeAttribute("indicadorUsoTodos");
			}

			colecaoMunicipio = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

				municipio = (Municipio) Util
						.retonarObjetoDeColecao(colecaoMunicipio);

				pesquisarCepActionForm.setIdMunicipio(String.valueOf(municipio
						.getId()));
				pesquisarCepActionForm.setNomeMunicipio(municipio.getNome());

				httpServletRequest.setAttribute("nomeCampo", "nomeLogradouro");
			} else {

				pesquisarCepActionForm.setIdMunicipio("");
				pesquisarCepActionForm
						.setNomeMunicipio("MUNIC�PIO INEXISTENTE");

				httpServletRequest.setAttribute("idMunicipioNaoEncontrado",
						"OK");
				httpServletRequest.setAttribute("nomeCampo", "idMunicipio");
			}

		} else {
			sessao.removeAttribute("municipioInformado");
		}

		// Retorno para tela de informar endereco
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaInformarEndereco") != null) {

			sessao
					.setAttribute(
							"caminhoRetornoTelaInformarEndereco",
							httpServletRequest
									.getParameter("caminhoRetornoTelaInformarEndereco"));

		}

		// Verifica se o tipoConsulta � diferente de nulo ou vazio esse tipo
		// consulta vem do
		// municipio_resultado_pesquisa.jsp ou do bairro_resultado_pesquisa.jsp.
		// � feita essa verifica��o pois pode ser que ainda n�o tenha
		// feito a pesquisa de municipio ou bairro.
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			// Verifica se o tipo da consulta de cliente � de municipio
			// se for os parametros de enviarDadosParametros ser�o mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"municipio")) {

				pesquisarCepActionForm.setIdMunicipio(httpServletRequest
						.getParameter("idCampoEnviarDados"));

				pesquisarCepActionForm.setNomeMunicipio(httpServletRequest
						.getParameter("descricaoCampoEnviarDados"));
			}
		}
		if (httpServletRequest.getParameter("tipoPesquisaLogradouro") == null
				|| httpServletRequest.getParameter("tipoPesquisaLogradouro").equals("")) {

			pesquisarCepActionForm.setTipoPesquisaLogradouro(
					ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

		}
		if (httpServletRequest.getParameter("idCepInicial") != null && !httpServletRequest.getParameter("idCepInicial").equals("")){
			pesquisarCepActionForm.setIdCepInicial(Long.parseLong(httpServletRequest.getParameter("idCepInicial")));
		}
		if (httpServletRequest.getParameter("idCepFinal") != null && !httpServletRequest.getParameter("idCepFinal").equals("")){
			pesquisarCepActionForm.setIdCepFinal(Long.parseLong(httpServletRequest.getParameter("idCepFinal")));
		}

		return retorno;
	}

}

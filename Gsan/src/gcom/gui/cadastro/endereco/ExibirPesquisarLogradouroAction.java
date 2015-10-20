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

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.FiltroLogradouroTitulo;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pr�-processamento da p�gina de pesquisa de Logradouro
 * 
 * @author Administrador
 */

public class ExibirPesquisarLogradouroAction extends GcomAction {

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

		// Seta o retorno
		ActionForward retorno = null;

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		// Verifica se o pesquisar logradouro foi chamado a partir do inserir
		// logradouro
		// e em caso afirmativo recebe o par�metro e manda-o pela sess�o para
		// ser verificado no logradouro_resultado_pesquisa e depois retirado da
		// sess�o no ExibirFiltrarLogradouroAction
		if (httpServletRequest.getParameter("consultaLogradouro") != null) {
			String consulta = httpServletRequest
					.getParameter("consultaLogradouro");
			sessao.setAttribute("consultaLogradouro", consulta);
		}

		ExibirPesquisarLogradouroActionForm exibirPesquisarLogradouroActionForm = (ExibirPesquisarLogradouroActionForm) actionForm;
		// Parte da pesquisa da lupa que � rotornada do logradouro_pesquisar.jsp
		// para ser redirecionado para a pesquisa que foi chamado
		if (httpServletRequest.getParameter("chamarPesquisa") != null) {
			retorno = actionMapping.findForward(httpServletRequest
					.getParameter("chamarPesquisa"));
			if (httpServletRequest.getParameter("chamarPesquisa").equals(
					"exibirPesquisarBairro")) {
				httpServletRequest.setAttribute("idMunicipio",
						exibirPesquisarLogradouroActionForm
								.getCodigoMunicipio());
			}
			if (httpServletRequest.getParameter("chamarPesquisa").equals(
					"exibirPesquisarCep")) {
				httpServletRequest.setAttribute("idMunicipioDefinido",
						exibirPesquisarLogradouroActionForm
								.getCodigoMunicipio());
			}
			return retorno;
		} else {
			retorno = actionMapping
					.findForward("exibirPesquisarLogradouroAction");
		}
		if (httpServletRequest.getParameter("objetoConsulta") == null
				&& httpServletRequest.getParameter("tipoConsulta") == null
				&& httpServletRequest.getParameter("chamarPesquisa") == null) {
			exibirPesquisarLogradouroActionForm.setCodigoMunicipio("");
			exibirPesquisarLogradouroActionForm.setNome("");
			exibirPesquisarLogradouroActionForm.setNomePopular("");
			exibirPesquisarLogradouroActionForm.setNomeMunicipio("");
			exibirPesquisarLogradouroActionForm.setCep("");
			exibirPesquisarLogradouroActionForm.setDescricaoCep("");
			exibirPesquisarLogradouroActionForm.setCodigoBairro("");
			exibirPesquisarLogradouroActionForm.setNomeBairro("");
			exibirPesquisarLogradouroActionForm.setTipoLogradouro(String
					.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
			exibirPesquisarLogradouroActionForm.setTitulo(String
					.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
			exibirPesquisarLogradouroActionForm
					.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
							.toString());
			exibirPesquisarLogradouroActionForm
					.setTipoPesquisaPopular(ConstantesSistema.TIPO_PESQUISA_INICIAL
							.toString());
		}

		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {

			// Verifica se o tipo da consulta de imovel � de municipio
			// se for os parametros de enviarDadosParametros ser�o mandados para
			// a pagina logradouro_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"municipio")) {
				exibirPesquisarLogradouroActionForm
						.setCodigoMunicipio(httpServletRequest
								.getParameter("idCampoEnviarDados"));
				exibirPesquisarLogradouroActionForm
						.setNomeMunicipio(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

			}
			if (httpServletRequest.getParameter("tipoConsulta")
					.equals("bairro")) {
				exibirPesquisarLogradouroActionForm
						.setCodigoBairro(httpServletRequest
								.getParameter("idCampoEnviarDados"));
				exibirPesquisarLogradouroActionForm
						.setNomeBairro(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));
			}
			if (httpServletRequest.getParameter("tipoConsulta").equals("cep")) {
				exibirPesquisarLogradouroActionForm.setCep(httpServletRequest
						.getParameter("idCampoEnviarDados"));
				exibirPesquisarLogradouroActionForm
						.setDescricaoCep(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

			}

		}

		// Filtro para pesquisa do municipio
		String codigoMunicipio = null;
		if(httpServletRequest.getParameter("codigoMunicipio") != null){
			codigoMunicipio = httpServletRequest.getParameter("codigoMunicipio");
		}
		
		if(httpServletRequest.getAttribute("codigoMunicipio") != null){
			codigoMunicipio = (String)httpServletRequest.getAttribute("codigoMunicipio");
		}
		
		String codigoBairro = null;
		if(httpServletRequest.getParameter("codigoBairro") != null){
			codigoBairro = httpServletRequest.getParameter("codigoBairro");
		}
		
		if(httpServletRequest.getAttribute("codigoBairro") != null){
			codigoBairro = (String)httpServletRequest.getAttribute("codigoBairro");
		}

		if ((httpServletRequest.getParameter("primeriaVez") != null
				&& httpServletRequest.getParameter("primeriaVez")
						.equalsIgnoreCase("1")) || (httpServletRequest.getAttribute("primeriaVez") != null
								&& httpServletRequest.getAttribute("primeriaVez")
								.equals("1"))) {
			sessao.removeAttribute("bloquearMunicipio");
			sessao.removeAttribute("bloquearBairro");
			if (codigoMunicipio != null && !codigoMunicipio.trim().equals("")) {
				sessao.setAttribute("bloquearMunicipio", "S");
			}
			if (codigoBairro != null && !codigoBairro.trim().equals("")) {
				sessao.setAttribute("bloquearBairro", "S");
			}
		}

		if (codigoMunicipio != null
				&& !codigoMunicipio.trim().equalsIgnoreCase("")) {
			Collection colecaoMunicipio = null;
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, codigoMunicipio));
			if (httpServletRequest.getParameter("indicadorUsoTodos") == null
					&& sessao.getAttribute("indicadorUso") == null) {

				sessao.removeAttribute("indicadorUsoTodos");
				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			}
			colecaoMunicipio = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (!colecaoMunicipio.isEmpty()) {
				exibirPesquisarLogradouroActionForm
						.setCodigoMunicipio(((Municipio) ((List) colecaoMunicipio)
								.get(0)).getId().toString());
				exibirPesquisarLogradouroActionForm
						.setNomeMunicipio(((Municipio) ((List) colecaoMunicipio)
								.get(0)).getNome());
				httpServletRequest.setAttribute("nomeCampo", "codigoBairro");
			} else {
				exibirPesquisarLogradouroActionForm.setCodigoMunicipio("");
				exibirPesquisarLogradouroActionForm
						.setNomeMunicipio("Munic�pio inexistente");
				httpServletRequest.setAttribute("codigoMunicipioNaoEncontrada",
						"true");
				httpServletRequest.setAttribute("nomeCampo", "codigoMunicipio");
			}
		}

		// Filtro para pesquisa do municipio

		if (codigoBairro != null && !codigoBairro.trim().equalsIgnoreCase("")) {
			Collection colecaoBairro = null;
			FiltroBairro filtroBairro = new FiltroBairro();

			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, codigoBairro));
			if (codigoMunicipio != null && !codigoMunicipio.trim().equals("")) {
				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.MUNICIPIO_ID, codigoMunicipio));
			}
			if (httpServletRequest.getParameter("indicadorUsoTodos") == null
					&& sessao.getAttribute("indicadorUso") == null) {

				sessao.removeAttribute("indicadorUsoTodos");
				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			}
			colecaoBairro = fachada.pesquisar(filtroBairro, Bairro.class
					.getName());

			if (!colecaoBairro.isEmpty()) {
				exibirPesquisarLogradouroActionForm
						.setCodigoBairro(((Bairro) ((List) colecaoBairro)
								.get(0)).getCodigo()
								+ "");
				exibirPesquisarLogradouroActionForm
						.setNomeBairro(((Bairro) ((List) colecaoBairro).get(0))
								.getNome());
				httpServletRequest.setAttribute("nomeCampo", "tipo");
			} else {
				exibirPesquisarLogradouroActionForm.setCodigoBairro("");
				exibirPesquisarLogradouroActionForm
						.setNomeBairro("Bairro inexistente");
				httpServletRequest.setAttribute("codigoBairroNaoEncontrada",
						"true");
				httpServletRequest.setAttribute("nomeCampo", "codigoBairro");
			}
		}

		// Filtro para pesquisa do cep
		String cep = httpServletRequest.getParameter("cep");

		if (cep != null && !cep.trim().equalsIgnoreCase("")) {
			Collection colecaoCep = null;
			FiltroCep filtroCep = new FiltroCep();

			filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CODIGO,
					cep));
			if (httpServletRequest.getParameter("indicadorUsoTodos") == null
					&& sessao.getAttribute("indicadorUso") == null) {
				sessao.removeAttribute("indicadorUsoTodos");
				filtroCep.adicionarParametro(new ParametroSimples(
						FiltroCep.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			}

			colecaoCep = fachada.pesquisar(filtroCep, Cep.class.getName());

			if (!colecaoCep.isEmpty()) {
				exibirPesquisarLogradouroActionForm
						.setCep(((Cep) ((List) colecaoCep).get(0)).getCodigo()
								.toString());
				exibirPesquisarLogradouroActionForm
						.setDescricaoCep(((Cep) ((List) colecaoCep).get(0))
								.getDescricaoLogradouroFormatada());

			} else {
				exibirPesquisarLogradouroActionForm.setCep("");
				exibirPesquisarLogradouroActionForm
						.setDescricaoCep("Cep inexistente");
				httpServletRequest.setAttribute("cepNaoEncontrada", "true");
				httpServletRequest.setAttribute("nomeCampo", "cep");
			}
		}

		if (httpServletRequest.getParameter("objetoConsulta") == null
				|| !httpServletRequest.getParameter("objetoConsulta")
						.equalsIgnoreCase("1")) {
			Collection colecaoTipo = null;
			Collection colecaoTitulo = null;
			FiltroLogradouroTipo filtroLogradouroTipo = null;
			FiltroLogradouroTitulo filtroLogradouroTitulo = null;

			// Filtro para pequisa do tipo do logradouro
			filtroLogradouroTipo = new FiltroLogradouroTipo(
					FiltroLogradouroTipo.DESCRICAO);

			filtroLogradouroTipo.setConsultaSemLimites(true);

			if (httpServletRequest.getParameter("indicadorUsoTodos") == null
					&& sessao.getAttribute("indicadorUso") == null) {
				sessao.removeAttribute("indicadorUsoTodos");

				filtroLogradouroTipo.adicionarParametro(new ParametroSimples(
						FiltroLogradouroTipo.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			}

			// Filtro para pequisa do titulo do logradouro
			filtroLogradouroTitulo = new FiltroLogradouroTitulo(
					FiltroLogradouroTitulo.DESCRICAO);

			filtroLogradouroTitulo.setConsultaSemLimites(true);

			if (httpServletRequest.getParameter("indicadorUsoTodos") == null
					&& sessao.getAttribute("indicadorUso") == null) {
				sessao.removeAttribute("indicadorUsoTodos");

				filtroLogradouroTitulo.adicionarParametro(new ParametroSimples(
						FiltroLogradouroTitulo.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			}

			// Retorna todos os tipos de logradouro
			colecaoTipo = fachada.pesquisar(filtroLogradouroTipo,
					LogradouroTipo.class.getName());

			if (colecaoTipo == null || colecaoTipo.isEmpty()) {
				// Nenhum tipo de logradouro cadastrado
				throw new ActionServletException(
						"erro.pesquisa.nenhumresultado");
			} else {
				sessao.setAttribute("tiposLogradouro", colecaoTipo);
			}

			// Retorna todos os titulos de logradouro
			colecaoTitulo = fachada.pesquisar(filtroLogradouroTitulo,
					LogradouroTitulo.class.getName());

			if (colecaoTitulo == null || colecaoTitulo.isEmpty()) {
				// Nenhum titulo de logradouro cadastrado
				throw new ActionServletException(
						"erro.pesquisa.nenhumresultado");
			} else {
				sessao.setAttribute("titulosLogradouro", colecaoTitulo);
			}

			if (httpServletRequest
					.getParameter("caminhoRetornoTelaPesquisaLogradouro") != null) {
				sessao
						.setAttribute(
								"caminhoRetornoTelaPesquisaLogradouro",
								httpServletRequest
										.getParameter("caminhoRetornoTelaPesquisaLogradouro"));

			}
		}
		if (httpServletRequest.getParameter("indicadorUsoTodos") != null) {
			sessao.setAttribute("indicadorUsoTodos", httpServletRequest
					.getParameter("indicadorUsoTodos"));
			sessao.setAttribute("indicadorUso", httpServletRequest
					.getParameter("indicadorUsoTodos"));
		}
		// devolve o mapeamento de retorno
		return retorno;
	}

}

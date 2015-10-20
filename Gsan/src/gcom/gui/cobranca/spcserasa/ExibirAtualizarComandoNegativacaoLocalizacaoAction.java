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
package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
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
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os
 * par�metros para realiza��o da inser��o de um Comando de Negativa��o (Aba n�
 * 04 - Localiza��o)
 * 
 * @author Ana Maria
 * @date 06/11/2007
 */
public class ExibirAtualizarComandoNegativacaoLocalizacaoAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("atualizarComandoNegativacaoLocalizacao");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		AtualizarComandoNegativacaoPorCriterioActionForm form = (AtualizarComandoNegativacaoPorCriterioActionForm) actionForm;

		// Caso informe o cliente, os campos da Aba 5 - Dados da Localiza��o
		// devem ser desabilitados
		// Caso informe o id da simula��o, os campos da Aba 5 - Dados da
		// Localiza��o devem ser desabilitados
		if ((form.getIdCliente() != null && !form.getIdCliente().equals(""))
				|| (form.getIdComandoSimulado() != null && !form
						.getIdComandoSimulado().equals(""))) {
			form.setCobrancaGrupo(null);
			form.setGerenciaRegional(null);
			form.setUnidadeNegocio(null);
			form.setEloPolo(null);
			form.setIdLocalidadeInicial(null);
			form.setLocalidadeDescricaoInicial(null);
			form.setCodigoSetorComercialInicial(null);
			form.setSetorComercialDescricaoInicial(null);
			form.setIdLocalidadeFinal(null);
			form.setLocalidadeDescricaoFinal(null);
			form.setCodigoSetorComercialFinal(null);
			form.setSetorComercialDescricaoFinal(null);
			httpServletRequest.setAttribute("desabilitar", "ok");
		}

		// Pesquisando grupo de cobran�a
		if (sessao.getAttribute("colcaoCobrancaGrupo") == null) {
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
			filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
			Collection colcaoCobrancaGrupo = fachada.pesquisar(
					filtroCobrancaGrupo, CobrancaGrupo.class.getName());
			if (colcaoCobrancaGrupo != null && !colcaoCobrancaGrupo.isEmpty()) {
				sessao.setAttribute("colcaoCobrancaGrupo", colcaoCobrancaGrupo);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Grupo Cobran�a");
			}
		}

		// Pesquisando ger�ncia regional
		if (sessao.getAttribute("colecaoGerenciaRegional") == null) {
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
			Collection colecaoGerenciaRegional = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());
			if (colecaoGerenciaRegional != null
					&& !colecaoGerenciaRegional.isEmpty()) {
				sessao.setAttribute("colecaoGerenciaRegional",
						colecaoGerenciaRegional);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Ger�ncia Regional");
			}
		}

		// Pesquisando unidade de neg�cio
		if (sessao.getAttribute("colecaoUnidadeNegocio") == null) {
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
			Collection colecaoUnidadeNegocio = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			if (colecaoUnidadeNegocio != null
					&& !colecaoUnidadeNegocio.isEmpty()) {
				sessao.setAttribute("colecaoUnidadeNegocio",
						colecaoUnidadeNegocio);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Unidade Neg�cio");
			}
		}

		// Pesquisando Elo P�lo
		if (sessao.getAttribute("colecaoEloPolo") == null) {

			FiltroLocalidade filtro = new FiltroLocalidade();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.setCampoOrderBy(FiltroLocalidade.DESCRICAO);
			Collection colecaoEloPolo = fachada.pesquisar(filtro,
					Localidade.class.getName());

			if (colecaoEloPolo != null && !colecaoEloPolo.isEmpty()) {
				sessao.setAttribute("colecaoEloPolo", colecaoEloPolo);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Elo P�lo");
			}
		}

		// Pesquisa Localidade Incial
		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		if (idLocalidadeInicial != null && !idLocalidadeInicial.equals("")) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeInicial));

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				httpServletRequest.setAttribute("idLocalidadeNaoEncontrada",
						"valor");
				httpServletRequest.setAttribute(
						"idLocalidadeFinalNaoEncontrada", "valor");

				form.setIdLocalidadeInicial(""
						+ ((Localidade) ((List) colecaoLocalidade).get(0))
								.getId());
				form.setLocalidadeDescricaoInicial(""
						+ ((Localidade) ((List) colecaoLocalidade).get(0))
								.getDescricao());
				form.setLocalidadeDescricaoFinal(""
						+ ((Localidade) ((List) colecaoLocalidade).get(0))
								.getDescricao());
			} else {
				httpServletRequest.setAttribute("idLocalidadeNaoEncontrada",
						"exception");
				form.setIdLocalidadeInicial(null);
				form.setIdLocalidadeFinal(null);
				httpServletRequest.setAttribute(
						"idLocalidadeFinalNaoEncontrada", "exception");
				form.setLocalidadeDescricaoInicial("Localidade Inexistente");
				form.setLocalidadeDescricaoFinal("Localidade Inexistente");
			}
		}

		// Pesquisa Localidade Final
		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		if (idLocalidadeFinal != null && !idLocalidadeFinal.equals("")) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeFinal));

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				httpServletRequest.setAttribute(
						"idLocalidadeFinalNaoEncontrada", "valor");

				form.setIdLocalidadeFinal(""
						+ ((Localidade) ((List) colecaoLocalidade).get(0))
								.getId());
				form.setLocalidadeDescricaoFinal(""
						+ ((Localidade) ((List) colecaoLocalidade).get(0))
								.getDescricao());
			} else {
				httpServletRequest.setAttribute(
						"idLocalidadeFinalNaoEncontrada", "exception");
				form.setIdLocalidadeFinal(null);
				form.setLocalidadeDescricaoFinal("Localidade Inexistente");
			}
		}

		// Pesquisar Setor Comercial Inicial
		String codigoSetorComercialInicial = form
				.getCodigoSetorComercialInicial();
		verificaExistenciaCodSetorComercialInicial(idLocalidadeInicial,
				codigoSetorComercialInicial, form, httpServletRequest, fachada,
				sessao);

		// Pesquisar Setor Comercial Final
		String codigoSetorComercialFinal = form.getCodigoSetorComercialFinal();
		verificaExistenciaCodSetorComercialFinal(idLocalidadeFinal,
				codigoSetorComercialFinal, form, httpServletRequest, fachada,
				sessao);

		return retorno;
	}

	private void verificaExistenciaCodSetorComercialInicial(
			String idLocalidadeInicial, String codigoSetorComercialInicial,
			AtualizarComandoNegativacaoPorCriterioActionForm form,
			HttpServletRequest httpServletRequest, Fachada fachada,
			HttpSession sessao) {

		// Verifica se o c�digo do Setor Comercial foi digitado
		if ((codigoSetorComercialInicial != null && !codigoSetorComercialInicial
				.toString().trim().equalsIgnoreCase(""))
				&& (idLocalidadeInicial != null && !idLocalidadeInicial
						.toString().trim().equalsIgnoreCase(""))) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			if (idLocalidadeInicial != null
					&& !idLocalidadeInicial.toString().trim()
							.equalsIgnoreCase("")) {

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, new Integer(
								idLocalidadeInicial)));
			}

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
							codigoSetorComercialInicial)));

			Collection<SetorComercial> setorComerciais = fachada.pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());

			if (setorComerciais != null && !setorComerciais.isEmpty()) {
				// o setor comercial foi encontrado
				SetorComercial setorComercialEncontrado = (SetorComercial) Util
						.retonarObjetoDeColecao(setorComerciais);
				form.setCodigoSetorComercialInicial(""
						+ (setorComercialEncontrado.getCodigo()));
				form.setSetorComercialDescricaoInicial(setorComercialEncontrado
						.getDescricao());
				httpServletRequest.setAttribute(
						"idSetorComercialNaoEncontrada", "true");

			} else {
				// o setor comercial n�o foi encontrado
				form.setCodigoSetorComercialInicial("");
				httpServletRequest.setAttribute(
						"idSetorComercialNaoEncontrada", "exception");
				form.setSetorComercialDescricaoInicial("SETOR COMERCIAL INEXISTENTE");
			}
		}
	}

	private void verificaExistenciaCodSetorComercialFinal(
			String idLocalidadeFinal, String codigoSetorComercialFinal,
			AtualizarComandoNegativacaoPorCriterioActionForm form,
			HttpServletRequest httpServletRequest, Fachada fachada,
			HttpSession sessao) {

		// Verifica se o c�digo do Setor Comercial foi digitado
		if ((codigoSetorComercialFinal != null && !codigoSetorComercialFinal
				.toString().trim().equalsIgnoreCase(""))
				&& (idLocalidadeFinal != null && !idLocalidadeFinal.toString()
						.trim().equalsIgnoreCase(""))) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			if (idLocalidadeFinal != null
					&& !idLocalidadeFinal.toString().trim()
							.equalsIgnoreCase("")) {

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, new Integer(
								idLocalidadeFinal)));
			}

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
							codigoSetorComercialFinal)));

			Collection<SetorComercial> setorComerciais = fachada.pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());

			if (setorComerciais != null && !setorComerciais.isEmpty()) {
				// o setor comercial foi encontrado
				SetorComercial setorComercialEncontrado = (SetorComercial) Util
						.retonarObjetoDeColecao(setorComerciais);
				form.setCodigoSetorComercialFinal(""
						+ (setorComercialEncontrado.getCodigo()));
				form.setSetorComercialDescricaoFinal(setorComercialEncontrado
						.getDescricao());
				httpServletRequest.setAttribute(
						"idSetorComercialNaoEncontrada", "true");

			} else {
				// o setor comercial n�o foi encontrado
				form.setCodigoSetorComercialFinal("");
				httpServletRequest.setAttribute(
						"idSetorComercialNaoEncontrada", "exception");
				form.setSetorComercialDescricaoFinal("SETOR COMERCIAL INEXISTENTE");
			}
		}
	}

}

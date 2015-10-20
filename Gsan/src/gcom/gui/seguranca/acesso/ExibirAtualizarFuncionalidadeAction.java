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
package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroFuncionalidadeCategoria;
import gcom.seguranca.acesso.FiltroFuncionalidadeDependencia;
import gcom.seguranca.acesso.FiltroModulo;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.seguranca.acesso.FuncionalidadeDependencia;
import gcom.seguranca.acesso.Modulo;

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
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 15/05/2006
 */
public class ExibirAtualizarFuncionalidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarFuncionalidade");

		AtualizarFuncionalidadeActionForm atualizarFuncionalidadeActionForm = (AtualizarFuncionalidadeActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		String id = null;

		HttpSession sessao = httpServletRequest.getSession(false);

		if (httpServletRequest.getParameter("idFuncionalidade") != null
				&& !httpServletRequest.getParameter("idFuncionalidade").equals(
						"")) {

			if (sessao.getAttribute("adicionar") != null) {

				sessao.removeAttribute("objetoFuncionalidade");
				sessao.removeAttribute("adicionar");

			}/*
				 * else { sessao.removeAttribute("objetoFuncionalidade");
				 * sessao.removeAttribute("colecaoFuncionalidadeTela"); }
				 */

		}

		this.pesquisarCampoEnter(httpServletRequest,
				atualizarFuncionalidadeActionForm, fachada);

		// Verifica se veio do filtrar ou do manter

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		// Verifica se a funcionalidade j� est� na sess�o, em caso afirmativo
		// significa que o usu�rio j� entrou na tela e apenas selecionou algum
		// item que deu um reload na tela e em caso negativo significa que ele
		// est� entrando pela primeira vez

		if (sessao.getAttribute("colecaoFuncionalidadeTela") == null) {

			if (sessao.getAttribute("objetoFuncionalidade") != null) {

				Funcionalidade funcionalidade = (Funcionalidade) sessao
						.getAttribute("objetoFuncionalidade");
				sessao.setAttribute("idFuncionalidade", funcionalidade.getId()
						.toString());

				atualizarFuncionalidadeActionForm
						.setIdFuncionalidade(funcionalidade.getId().toString());
				atualizarFuncionalidadeActionForm.setDescricao(funcionalidade
						.getDescricao());
				atualizarFuncionalidadeActionForm
						.setDescricaoAbreviada(funcionalidade
								.getDescricaoAbreviada());
				atualizarFuncionalidadeActionForm.setCaminhoMenu(funcionalidade
						.getCaminhoMenu());
				atualizarFuncionalidadeActionForm.setCaminhoUrl(funcionalidade
						.getCaminhoUrl());
				atualizarFuncionalidadeActionForm.setModulo(funcionalidade
						.getModulo().getId().toString());
				atualizarFuncionalidadeActionForm.setIndicadorPontoEntrada(""
						+ funcionalidade.getIndicadorPontoEntrada());

				atualizarFuncionalidadeActionForm.setIndicadorNovaJanela(""
						+ funcionalidade.getIndicadorNovaJanela());

				atualizarFuncionalidadeActionForm.setIndicadorOlap(""
						+ funcionalidade.getIndicadorOlap());

				atualizarFuncionalidadeActionForm
						.setNumeroOrdemMenu(funcionalidade.getNumeroOrdemMenu()
								.toString());

				if (funcionalidade.getFuncionalidadeCategoria() != null) {
					atualizarFuncionalidadeActionForm
							.setIdFuncionalidadeCategoria(funcionalidade
									.getFuncionalidadeCategoria().getId()
									.toString());

					atualizarFuncionalidadeActionForm
							.setNomeFuncionalidadeCategoria(funcionalidade
									.getFuncionalidadeCategoria().getNome());
				}
				if (funcionalidade.getIndicadorPontoEntrada().toString()
						.equalsIgnoreCase(
								ConstantesSistema.INDICADOR_USO_ATIVO
										.toString())) {
					httpServletRequest.setAttribute("funcionalidadeCategoria",
							"sim");

				} else {
					httpServletRequest
							.removeAttribute("funcionalidadeCategoria");
					atualizarFuncionalidadeActionForm
							.setIdFuncionalidadeCategoria("");

					atualizarFuncionalidadeActionForm
							.setNomeFuncionalidadeCategoria("");
				}

				id = funcionalidade.getId().toString();

				// Parte do adicionar
				// recupera a cole��o de funcionalidade dependencia

				FiltroFuncionalidadeDependencia filtroFuncionalidadeDependencia = new FiltroFuncionalidadeDependencia();
				filtroFuncionalidadeDependencia
						.adicionarParametro(new ParametroSimples(
								FiltroFuncionalidadeDependencia.FUNCIONALIDADE_ID,
								funcionalidade.getId()));
				filtroFuncionalidadeDependencia
						.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeDependencia");

				Collection colecaoFuncionalidadeTela = fachada.pesquisar(
						filtroFuncionalidadeDependencia,
						FuncionalidadeDependencia.class.getName());

				if (colecaoFuncionalidadeTela != null
						&& !colecaoFuncionalidadeTela.isEmpty()) {
					sessao.setAttribute("colecaoFuncionalidadeTela",
							colecaoFuncionalidadeTela);
				}

				sessao.setAttribute("funcionalidadeAtualizar", funcionalidade);
				sessao.removeAttribute("objetoFuncionalidade");

			} else if (httpServletRequest.getAttribute("remover") == null
					&& httpServletRequest.getAttribute("desfazer") == null
					&& httpServletRequest.getParameter("enter") == null
					&& httpServletRequest.getParameter("reload") == null) {

				String funcionalidadeID = null;

				if (httpServletRequest.getParameter("idFuncionalidade") != null
						&& !httpServletRequest.getParameter("idFuncionalidade")
								.equals("")) {
					funcionalidadeID = (String) httpServletRequest
							.getParameter("idFuncionalidade");

					httpServletRequest.setAttribute("idFuncionalidade",
							funcionalidadeID);

					id = funcionalidadeID;
					FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
					filtroFuncionalidade
							.adicionarCaminhoParaCarregamentoEntidade("modulo");
					filtroFuncionalidade
							.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeCategoria");

					filtroFuncionalidade
							.adicionarParametro(new ParametroSimples(
									FiltroFuncionalidade.ID, funcionalidadeID));

					Collection<Funcionalidade> colecaoFuncionalidade = fachada
							.pesquisar(filtroFuncionalidade,
									Funcionalidade.class.getName());

					if (colecaoFuncionalidade == null
							|| colecaoFuncionalidade.isEmpty()) {
						throw new ActionServletException(
								"atencao.atualizacao.timestamp");
					}

					httpServletRequest.setAttribute("colecaoFuncionalidade",
							colecaoFuncionalidade);

					Funcionalidade funcionalidade = (Funcionalidade) colecaoFuncionalidade
							.iterator().next();

					atualizarFuncionalidadeActionForm
							.setIdFuncionalidade(funcionalidade.getId()
									.toString());

					atualizarFuncionalidadeActionForm
							.setDescricao(funcionalidade.getDescricao());
					atualizarFuncionalidadeActionForm
							.setDescricaoAbreviada(funcionalidade
									.getDescricaoAbreviada());
					atualizarFuncionalidadeActionForm
							.setCaminhoMenu(funcionalidade.getCaminhoMenu());
					atualizarFuncionalidadeActionForm
							.setCaminhoUrl(funcionalidade.getCaminhoUrl());
					atualizarFuncionalidadeActionForm.setModulo(funcionalidade
							.getModulo().getId().toString());
					atualizarFuncionalidadeActionForm
							.setIndicadorPontoEntrada(""
									+ funcionalidade.getIndicadorPontoEntrada());

					atualizarFuncionalidadeActionForm.setNumeroOrdemMenu(""
							+ funcionalidade.getNumeroOrdemMenu());

					atualizarFuncionalidadeActionForm.setIndicadorNovaJanela(""
							+ funcionalidade.getIndicadorNovaJanela());

					atualizarFuncionalidadeActionForm.setIndicadorOlap(""
							+ funcionalidade.getIndicadorOlap());

					if (funcionalidade.getFuncionalidadeCategoria() != null) {
						atualizarFuncionalidadeActionForm
								.setIdFuncionalidadeCategoria(funcionalidade
										.getFuncionalidadeCategoria().getId()
										.toString());

						atualizarFuncionalidadeActionForm
								.setNomeFuncionalidadeCategoria(funcionalidade
										.getFuncionalidadeCategoria().getNome());
					}
					if (funcionalidade.getIndicadorPontoEntrada().toString()
							.equalsIgnoreCase(
									ConstantesSistema.INDICADOR_USO_ATIVO
											.toString())) {
						httpServletRequest.setAttribute(
								"funcionalidadeCategoria", "sim");

					} else {
						httpServletRequest
								.removeAttribute("funcionalidadeCategoria");
						atualizarFuncionalidadeActionForm
								.setIdFuncionalidadeCategoria("");

						atualizarFuncionalidadeActionForm
								.setNomeFuncionalidadeCategoria("");

					}

					// sessao.setAttribute("colecaoFuncionalidade",
					// colecaoFuncionalidade);

					// Parte do adicionar

					// recupera a cole��o de funcionalidade dependencia

					FiltroFuncionalidadeDependencia filtroFuncionalidadeDependencia = new FiltroFuncionalidadeDependencia();
					filtroFuncionalidadeDependencia
							.adicionarParametro(new ParametroSimples(
									FiltroFuncionalidadeDependencia.FUNCIONALIDADE_ID,
									funcionalidade.getId()));
					filtroFuncionalidadeDependencia
							.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeDependencia");

					Collection colecaoFuncionalidadeTela = fachada.pesquisar(
							filtroFuncionalidadeDependencia,
							FuncionalidadeDependencia.class.getName());

					if (colecaoFuncionalidadeTela != null
							&& !colecaoFuncionalidadeTela.isEmpty()) {
						// httpServletRequest.setAttribute("colecaoFuncionalidadeTela",colecaoFuncionalidadeTela);
						sessao.setAttribute("colecaoFuncionalidadeTela",
								colecaoFuncionalidadeTela);
					}

					sessao.setAttribute("funcionalidadeAtualizar",
							funcionalidade);
				}
				// sessao.removeAttribute("colecaoFuncionalidadeTela");
			}
		}

		// -------------- bt DESFAZER ---------------

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			sessao.removeAttribute("colecaoFuncionalidadeTela");

			String funcionalidadeID = null;

			if (httpServletRequest.getParameter("idFuncionalidade") == null
					|| httpServletRequest.getParameter("idFuncionalidade")
							.equals("")) {
				funcionalidadeID = (String) sessao
						.getAttribute("idFuncionalidade");
			} else {
				funcionalidadeID = (String) httpServletRequest
						.getParameter("idFuncionalidade");
				sessao.setAttribute("idFuncionalidade", funcionalidadeID);
			}

			if (funcionalidadeID.equalsIgnoreCase("")) {
				funcionalidadeID = null;
			}

			if ((funcionalidadeID == null) && (id == null)) {

				Funcionalidade funcionalidade = (Funcionalidade) sessao
						.getAttribute("objetoFuncionalidade");

				atualizarFuncionalidadeActionForm
						.setIdFuncionalidade(funcionalidade.getId().toString());

				atualizarFuncionalidadeActionForm.setDescricao(funcionalidade
						.getDescricao());
				atualizarFuncionalidadeActionForm
						.setDescricaoAbreviada(funcionalidade
								.getDescricaoAbreviada());
				atualizarFuncionalidadeActionForm.setCaminhoMenu(funcionalidade
						.getCaminhoMenu());
				atualizarFuncionalidadeActionForm.setCaminhoUrl(funcionalidade
						.getCaminhoUrl());
				atualizarFuncionalidadeActionForm.setModulo(funcionalidade
						.getModulo().getId().toString());
				atualizarFuncionalidadeActionForm.setIndicadorPontoEntrada(""
						+ funcionalidade.getIndicadorPontoEntrada());

				atualizarFuncionalidadeActionForm.setNumeroOrdemMenu(""
						+ funcionalidade.getNumeroOrdemMenu());

				atualizarFuncionalidadeActionForm.setIndicadorNovaJanela(""
						+ funcionalidade.getIndicadorNovaJanela());

				atualizarFuncionalidadeActionForm.setIndicadorOlap(""
						+ funcionalidade.getIndicadorOlap());

				atualizarFuncionalidadeActionForm
						.setIdFuncionalidadeCategoria(funcionalidade
								.getFuncionalidadeCategoria().getId()
								.toString());

				atualizarFuncionalidadeActionForm
						.setNomeFuncionalidadeCategoria(funcionalidade
								.getFuncionalidadeCategoria().getNome());

				if (funcionalidade.getIndicadorPontoEntrada().toString()
						.equalsIgnoreCase(
								ConstantesSistema.INDICADOR_USO_ATIVO
										.toString())) {
					httpServletRequest.setAttribute("funcionalidadeCategoria",
							"sim");

				} else {
					httpServletRequest
							.removeAttribute("funcionalidadeCategoria");

				}

				// Parte do adicionar
				// recupera a cole��o de funcionalidade dependencia

				FiltroFuncionalidadeDependencia filtroFuncionalidadeDependencia = new FiltroFuncionalidadeDependencia();
				filtroFuncionalidadeDependencia
						.adicionarParametro(new ParametroSimples(
								FiltroFuncionalidadeDependencia.FUNCIONALIDADE_ID,
								funcionalidade.getId()));
				filtroFuncionalidadeDependencia
						.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeDependencia");

				Collection colecaoFuncionalidadeTela = fachada.pesquisar(
						filtroFuncionalidadeDependencia,
						FuncionalidadeDependencia.class.getName());

				if (colecaoFuncionalidadeTela != null
						&& !colecaoFuncionalidadeTela.isEmpty()) {
					sessao.setAttribute("colecaoFuncionalidadeTela",
							colecaoFuncionalidadeTela);
				}

				sessao.setAttribute("funcionalidadeAtualizar", funcionalidade);
				sessao.removeAttribute("funcionalidade");
			}

			if ((funcionalidadeID == null) && (id != null)) {

				funcionalidadeID = id;
			}

			if (funcionalidadeID != null) {

				FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
				filtroFuncionalidade
						.adicionarCaminhoParaCarregamentoEntidade("modulo");
				filtroFuncionalidade
						.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeCategoria");
				filtroFuncionalidade.adicionarParametro(new ParametroSimples(
						FiltroFuncionalidade.ID, funcionalidadeID));

				Collection<Funcionalidade> colecaoFuncionalidade = fachada
						.pesquisar(filtroFuncionalidade, Funcionalidade.class
								.getName());

				if (colecaoFuncionalidade == null
						|| colecaoFuncionalidade.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				httpServletRequest.setAttribute("colecaoFuncionalidade",
						colecaoFuncionalidade);

				Funcionalidade funcionalidade = (Funcionalidade) colecaoFuncionalidade
						.iterator().next();

				atualizarFuncionalidadeActionForm
						.setIdFuncionalidade(funcionalidade.getId().toString());

				atualizarFuncionalidadeActionForm.setDescricao(funcionalidade
						.getDescricao());
				atualizarFuncionalidadeActionForm
						.setDescricaoAbreviada(funcionalidade
								.getDescricaoAbreviada());
				atualizarFuncionalidadeActionForm.setCaminhoMenu(funcionalidade
						.getCaminhoMenu());
				atualizarFuncionalidadeActionForm.setCaminhoUrl(funcionalidade
						.getCaminhoUrl());
				atualizarFuncionalidadeActionForm.setModulo(funcionalidade
						.getModulo().getId().toString());
				atualizarFuncionalidadeActionForm.setIndicadorPontoEntrada(""
						+ funcionalidade.getIndicadorPontoEntrada());

				atualizarFuncionalidadeActionForm.setIndicadorNovaJanela(""
						+ funcionalidade.getIndicadorNovaJanela());

				atualizarFuncionalidadeActionForm.setIndicadorOlap(""
						+ funcionalidade.getIndicadorOlap());

				atualizarFuncionalidadeActionForm.setNumeroOrdemMenu(""
						+ funcionalidade.getNumeroOrdemMenu());

				if (funcionalidade.getFuncionalidadeCategoria() != null) {

					atualizarFuncionalidadeActionForm
							.setIdFuncionalidadeCategoria(funcionalidade
									.getFuncionalidadeCategoria().getId()
									.toString());

					atualizarFuncionalidadeActionForm
							.setNomeFuncionalidadeCategoria(funcionalidade
									.getFuncionalidadeCategoria().getNome());
				}

				if (funcionalidade.getIndicadorPontoEntrada().toString()
						.equalsIgnoreCase(
								ConstantesSistema.INDICADOR_USO_ATIVO
										.toString())) {
					httpServletRequest.setAttribute("funcionalidadeCategoria",
							"sim");

				} else {
					httpServletRequest
							.removeAttribute("funcionalidadeCategoria");

				}
				// Parte do adicionar

				// recupera a cole��o de funcionalidade dependencia

				FiltroFuncionalidadeDependencia filtroFuncionalidadeDependencia = new FiltroFuncionalidadeDependencia();
				filtroFuncionalidadeDependencia
						.adicionarParametro(new ParametroSimples(
								FiltroFuncionalidadeDependencia.FUNCIONALIDADE_ID,
								funcionalidade.getId()));
				filtroFuncionalidadeDependencia
						.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeDependencia");

				Collection colecaoFuncionalidadeTela = fachada.pesquisar(
						filtroFuncionalidadeDependencia,
						FuncionalidadeDependencia.class.getName());

				if (colecaoFuncionalidadeTela != null
						&& !colecaoFuncionalidadeTela.isEmpty()) {
					sessao.setAttribute("colecaoFuncionalidadeTela",
							colecaoFuncionalidadeTela);
				}
				httpServletRequest.setAttribute("funcionalidadeID",
						funcionalidadeID);
				sessao.setAttribute("funcionalidadeAtualizar", funcionalidade);
			}

		}

		// -------------- bt DESFAZER ---------------

		httpServletRequest.setAttribute("colecaoFuncionalidadeTela", sessao
				.getAttribute("colecaoFuncionalidadeTela"));

		return retorno;

	}

	private void pesquisarCampoEnter(HttpServletRequest httpServletRequest,
			AtualizarFuncionalidadeActionForm form, Fachada fachada) {

		String idFuncionalidadeCategoria = form.getIdFuncionalidadeCategoria();

		// Pesquisa a empresa
		if (idFuncionalidadeCategoria != null
				&& !idFuncionalidadeCategoria.trim().equals("")) {

			FiltroFuncionalidadeCategoria filtroFuncionalidadeCategoria = new FiltroFuncionalidadeCategoria();
			filtroFuncionalidadeCategoria
					.adicionarParametro(new ParametroSimples(
							FiltroFuncionalidadeCategoria.ID,
							idFuncionalidadeCategoria));

			Collection colecaoFuncionalidadeCategoria = fachada.pesquisar(
					filtroFuncionalidadeCategoria,
					FuncionalidadeCategoria.class.getName());

			if (colecaoFuncionalidadeCategoria != null
					&& !colecaoFuncionalidadeCategoria.isEmpty()) {
				FuncionalidadeCategoria funcionalidadeCategoria = (FuncionalidadeCategoria) Util
						.retonarObjetoDeColecao(colecaoFuncionalidadeCategoria);
				form.setIdFuncionalidadeCategoria(funcionalidadeCategoria
						.getId().toString());
				form.setNomeFuncionalidadeCategoria(funcionalidadeCategoria
						.getNome());
				httpServletRequest.setAttribute("nomeCampo",
						"idFuncionalidadeCategoria");
			} else {
				form.setIdFuncionalidadeCategoria("");
				form
						.setNomeFuncionalidadeCategoria("FUNCIONALIDADE CATEGORIA INEXISTENTE");

				httpServletRequest.setAttribute(
						"funcionalidadeCategoriaInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
			}

		} else {
			form.setNomeFuncionalidadeCategoria("");
		}

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao
		FiltroModulo filtroModulo = new FiltroModulo();

		filtroModulo.setCampoOrderBy(FiltroModulo.DESCRICAO_MODULO);
		Collection<Modulo> colecaoModulo = fachada.pesquisar(filtroModulo,
				Modulo.class.getName());

		if (colecaoModulo == null || colecaoModulo.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Modulo");
		}

		httpServletRequest.setAttribute("colecaoModulo", colecaoModulo);

		if (form.getIndicadorPontoEntrada() != null
				&& form.getIndicadorPontoEntrada().toString().equalsIgnoreCase(
						ConstantesSistema.INDICADOR_USO_ATIVO.toString())) {
			httpServletRequest.setAttribute("funcionalidadeCategoria", "sim");

		} else {
			httpServletRequest.removeAttribute("funcionalidadeCategoria");

		}

	}

}

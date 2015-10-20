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

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAcaoCronograma;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAcaoCronograma;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.cobranca.bean.CobrancaCronogramaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAdicionarAcaoCobrancaCronogramaPopupAction extends
		GcomAction {
	/**
	 * [UC00313] Manter Cronograma Cobran�a
	 * 
	 * @author Fl�vio Cordeiro
	 * 
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("adicionarAcaoCobrancaCronogramaPopup");

		CobrancaActionForm cobrancaActionForm = (CobrancaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		if (httpServletRequest.getParameter("limpar") != null) {
			sessao.removeAttribute("cobrancaAcaoEscolhida");
			sessao.removeAttribute("colecaoCobrancaAtiviade");
			cobrancaActionForm.setIdCobrancaAcao("-1");
		}

		Collection colecaoAcaoCobrancaNovo = null;
		Collection colecaoAcaoCronogramaNaBase = new ArrayList();
		Collection colecaoAcaoCobrancaEscolhida = null;

		CobrancaAcao cobrancaAcaoEscolhida = null;

		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();

		if (sessao.getAttribute("acoesCobranca") != null) {
			colecaoAcaoCronogramaNaBase = (Collection) sessao
					.getAttribute("acoesCobranca");
		} else if (sessao.getAttribute("colecaoCobrancaCronogramaHelper") != null) {
			colecaoAcaoCronogramaNaBase = (Collection) sessao
					.getAttribute("colecaoCobrancaCronogramaHelper");
		} else {
			FiltroCobrancaAcaoCronograma filtroCobrancaAcaoCronograma = new FiltroCobrancaAcaoCronograma();

			filtroCobrancaAcaoCronograma
					.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao");
			filtroCobrancaAcaoCronograma
					.adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupoCronogramaMes");
			filtroCobrancaAcaoCronograma
					.adicionarParametro(new ParametroSimples(
							FiltroCobrancaAcaoCronograma.ID_COBRANCA_GRUPO_CRONOGRAMA_MES,
							cobrancaActionForm
									.getIdCobrancaGrupoCronogramaMes()));

			colecaoAcaoCronogramaNaBase = fachada.pesquisar(
					filtroCobrancaAcaoCronograma, CobrancaAcaoCronograma.class
							.getName());
		}

		Iterator iterator = colecaoAcaoCronogramaNaBase.iterator();

		filtroCobrancaAcao
				.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora");

		CobrancaAcaoCronograma cobrancaAcaoCronograma = null;
		CobrancaAcao cobrancaAcaoAux = null;
		while (iterator.hasNext()) {
			if (sessao.getAttribute("colecaoCobrancaCronogramaHelper") == null) {
				cobrancaAcaoCronograma = (CobrancaAcaoCronograma) iterator
						.next();
			} else {
				if (sessao.getAttribute("acoesCobranca") != null) {
					cobrancaAcaoAux = (CobrancaAcao) iterator.next();
				} else {
					cobrancaAcaoCronograma = ((CobrancaCronogramaHelper) iterator
							.next()).getCobrancaAcaoCronograma();
				}
			}

			if (cobrancaAcaoAux != null) {
				filtroCobrancaAcao
				.adicionarParametro(new ParametroSimplesDiferenteDe(
						FiltroCobrancaAcao.ID, cobrancaAcaoAux.getId()));
			} else {
				filtroCobrancaAcao
						.adicionarParametro(new ParametroSimplesDiferenteDe(
								FiltroCobrancaAcao.ID, cobrancaAcaoCronograma
										.getCobrancaAcao().getId()));
			}
		}

		if (cobrancaActionForm.getIdCobrancaAcao() != null
				&& !cobrancaActionForm.getIdCobrancaAcao().trim().equals("-1")) {
			filtroCobrancaAcao
					.adicionarParametro(new ParametroSimplesDiferenteDe(
							FiltroCobrancaAcao.ID, cobrancaActionForm
									.getIdCobrancaAcao()));
		}

		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAcao.INDICADOR_CRONOGRAMA,
				CobrancaAcao.INDICADOR_CRONOGRAMA_ATIVO));

		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAcao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		colecaoAcaoCobrancaNovo = fachada.pesquisar(filtroCobrancaAcao,
				CobrancaAcao.class.getName());

		if ((colecaoAcaoCobrancaNovo.isEmpty() && httpServletRequest
				.getParameter("reload") == null)
				|| (colecaoAcaoCobrancaNovo.isEmpty() && !httpServletRequest
						.getParameter("reload").equalsIgnoreCase("N"))) {
			throw new ActionServletException(
					"atencao.dependencias.nenhuma_acao_adicionar");
		}

		FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAtividade.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroCobrancaAtividade
				.setCampoOrderBy(FiltroCobrancaAtividade.ORDEM_REALIZACAO);
		filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAtividade.INDICADOR_CRONOGRAMA,
				CobrancaAtividade.ATIVO_CRONOGRAMA));

		Collection colecaoCobrancaAtividade = fachada.pesquisar(
				filtroCobrancaAtividade, CobrancaAtividade.class.getName());
		sessao
				.setAttribute("colecaoCobrancaAtiviade",
						colecaoCobrancaAtividade);

		if (cobrancaActionForm.getIdCobrancaAcao() != null
				&& !cobrancaActionForm.getIdCobrancaAcao().trim().equals("-1")) {
			filtroCobrancaAcao.limparListaParametros();
			filtroCobrancaAcao
					.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora");
			filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaAcao.ID, cobrancaActionForm
							.getIdCobrancaAcao()));

			colecaoAcaoCobrancaEscolhida = fachada.pesquisar(
					filtroCobrancaAcao, CobrancaAcao.class.getName());

			cobrancaAcaoEscolhida = (CobrancaAcao) colecaoAcaoCobrancaEscolhida
					.iterator().next();

			sessao.setAttribute("cobrancaAcaoEscolhida", cobrancaAcaoEscolhida);
		}

		if (httpServletRequest.getParameter("adicionar") != null) {

			Collection atividadesCobrancaObrigatoriedadeAtivo = (Collection) sessao
					.getAttribute("atividadesCobrancaObrigatoriedadeAtivo");

			httpServletRequest.setAttribute("adicionar", "adicionar");
			CobrancaCronogramaHelper cobrancaCronogramaHelper = new CobrancaCronogramaHelper();

			if (cobrancaAcaoEscolhida == null) {
				cobrancaAcaoEscolhida = (CobrancaAcao) sessao
						.getAttribute("cobrancaAcaoEscolhida");
			}

			if (cobrancaAcaoEscolhida != null) {
				Iterator iteratorColecaoNova = colecaoAcaoCobrancaNovo
						.iterator();
				CobrancaAcao cobrancaNovoTesteRemover = null;
				Collection colecaoCobrancaAcaoReporNova = new ArrayList();
				if (!colecaoAcaoCobrancaNovo.isEmpty()) {
					while (iteratorColecaoNova.hasNext()) {
						cobrancaNovoTesteRemover = (CobrancaAcao) iteratorColecaoNova
								.next();
						if (cobrancaAcaoEscolhida != null
								&& !cobrancaAcaoEscolhida.getId().equals(
										cobrancaNovoTesteRemover.getId())) {
							colecaoCobrancaAcaoReporNova
									.add(cobrancaNovoTesteRemover);
						}
					}
				}
				colecaoAcaoCobrancaNovo = colecaoCobrancaAcaoReporNova;

				CobrancaAtividade cobrancaAtividade = null;
				CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = null;
				Collection cobrancasAtividadesParaInsercao = new ArrayList();

				CobrancaAcaoCronograma cobrancaAcaoCronogramaNovo = new CobrancaAcaoCronograma();

				cobrancaAcaoCronogramaNovo
						.setCobrancaAcao(cobrancaAcaoEscolhida);

				cobrancaCronogramaHelper
						.setCobrancaAcaoCronograma(cobrancaAcaoCronogramaNovo);
				Collection colecaoAtividades = (Collection) sessao
						.getAttribute("colecaoCobrancaAtiviade");
				Collection colecaoCobrancaHelperNaBase = (Collection) sessao
						.getAttribute("colecaoCobrancaCronogramaHelper");

				Iterator iteratorAtividades = colecaoAtividades.iterator();
				Iterator iteratorTesteSucessora = colecaoCobrancaHelperNaBase
						.iterator();

				String idAcaoCobranca = "";
				String dataPrevista = "";
				// String anoMes = "";
				// String mesAno = "";
				int verificaDataPreenchida = 0;

				int contadorAtividades = 0;

				/**
				 * 
				 * 
				 */
				CobrancaCronogramaHelper cobrancaCronogramaHelperTesteSucessora = null;
				boolean predecessoraNaColecao = false;
				if (cobrancaAcaoEscolhida != null
						&& cobrancaAcaoEscolhida.getCobrancaAcaoPredecessora() != null) {
					// Integer idPredecessora = null;
					while (iteratorTesteSucessora.hasNext()) {
						cobrancaCronogramaHelperTesteSucessora = (CobrancaCronogramaHelper) iteratorTesteSucessora
								.next();

						if (cobrancaAcaoEscolhida.getCobrancaAcaoPredecessora()
								.getId().equals(
										cobrancaCronogramaHelperTesteSucessora
												.getCobrancaAcaoCronograma()
												.getCobrancaAcao().getId())) {
							predecessoraNaColecao = true;
						} else {
							// idPredecessora =
							// cobrancaCronogramaHelperTesteSucessora.getCobrancaAcaoCronograma()
							// .getCobrancaAcao().getId();
						}
					}
					if (!predecessoraNaColecao) {
						FiltroCobrancaAcao filtroCobrancaAcaoErro = new FiltroCobrancaAcao();
						filtroCobrancaAcaoErro
								.adicionarParametro(new ParametroSimples(
										FiltroCobrancaAcao.ID,
										cobrancaAcaoEscolhida
												.getCobrancaAcaoPredecessora()
												.getId()));
						Collection colecaoCobrancaAcaroErro = fachada
								.pesquisar(filtroCobrancaAcaoErro,
										CobrancaAcao.class.getName());
						CobrancaAcao cobrancaAcao = null;
						if (!colecaoCobrancaAcaroErro.isEmpty()) {
							cobrancaAcao = (CobrancaAcao) Util
									.retonarObjetoDeColecao(colecaoCobrancaAcaroErro);
							throw new ActionServletException(
									"atencao.dependencias.adionar_predecessora",
									null, cobrancaAcao
											.getDescricaoCobrancaAcao());
						} else {
							throw new ActionServletException(
									"atencao.dependencias.adionar_predecessora");
						}

					}
				}

				if (cobrancaAcaoEscolhida.getIndicadorObrigatoriedade()
						.intValue() == 1) {
					while (iteratorAtividades.hasNext()) {
						contadorAtividades += 1;

						cobrancaAtividade = (CobrancaAtividade) iteratorAtividades
								.next();
						// --------pega o valor de comandar.Ex: comandar2
						idAcaoCobranca = (String) httpServletRequest
								.getParameter("comandar"
										+ cobrancaAcaoEscolhida.getId()
												.toString() + "atividade"
										+ cobrancaAtividade.getId().toString());
						// -------- separa o id da string comandar

						dataPrevista = "";
						dataPrevista = (String) httpServletRequest
								.getParameter("a"
										+ cobrancaAcaoEscolhida.getId()
												.toString() + "n"
										+ cobrancaAtividade.getId().toString());

						if (dataPrevista.trim().equals("")
								&& cobrancaAtividade
										.getIndicadorObrigatoriedade() == 1) {
							throw new ActionServletException(
									"atencao.cobranca.data_prevista_acao_obrigatoria");
						} else {
							// ----seta os valores no objeto
							// CobrancaAcaoAtividadeCronograma
							cobrancaAcaoAtividadeCronograma = new CobrancaAcaoAtividadeCronograma();
							cobrancaAcaoAtividadeCronograma
									.setCobrancaAtividade(cobrancaAtividade);
							cobrancaAcaoAtividadeCronograma
									.setCobrancaAcaoCronograma(cobrancaAcaoCronogramaNovo);

							cobrancaAcaoAtividadeCronograma
									.setDataPrevista(Util
											.converteStringParaDate(dataPrevista));
							if (idAcaoCobranca != null
									&& idAcaoCobranca.trim().equals("1")) {
								cobrancaAcaoAtividadeCronograma
										.setComando(Util
												.converteStringParaDateHora(dataPrevista
														+ " "
														+ Util
																.formatarHoraSemData(new Date())));
							} else {
								cobrancaAcaoAtividadeCronograma
										.setComando(null);
							}

							cobrancasAtividadesParaInsercao
									.add(cobrancaAcaoAtividadeCronograma);

						}
					}
					cobrancaCronogramaHelper
							.setCobrancasAtividadesParaInsercao(cobrancasAtividadesParaInsercao);

					/**
					 * 
					 */
					colecaoCobrancaHelperNaBase.add(cobrancaCronogramaHelper);
				} else {
					verificaDataPreenchida = 0;
					while (iteratorAtividades.hasNext()) {
						contadorAtividades += 1;

						cobrancaAtividade = (CobrancaAtividade) iteratorAtividades
								.next();

						// --------pega o valor de comandar.Ex: comandar2
						idAcaoCobranca = (String) httpServletRequest
								.getParameter("comandar"
										+ cobrancaAcaoEscolhida.getId()
												.toString() + "atividade"
										+ cobrancaAtividade.getId().toString());
						// -------- separa o id da string comandar

						dataPrevista = "";
						dataPrevista = (String) httpServletRequest
								.getParameter("a"
										+ cobrancaAcaoEscolhida.getId()
												.toString() + "n"
										+ cobrancaAtividade.getId().toString());

						cobrancaAcaoAtividadeCronograma = new CobrancaAcaoAtividadeCronograma();
						cobrancaAcaoAtividadeCronograma
								.setCobrancaAtividade(cobrancaAtividade);
						cobrancaAcaoAtividadeCronograma
								.setCobrancaAcaoCronograma(cobrancaAcaoCronogramaNovo);

						if (!dataPrevista.trim().equals("")
								|| cobrancaAtividade
										.getIndicadorObrigatoriedade()
										.equals(
												CobrancaAtividade.INDICADOR_OBRIGATORIEDADE_ATIVO)) {
							verificaDataPreenchida += 1;
							// ----seta os valores no objeto
							// CobrancaAcaoAtividadeCronograma
							cobrancaAcaoAtividadeCronograma
									.setDataPrevista(Util
											.converteStringParaDate(dataPrevista));
							if (idAcaoCobranca != null
									&& idAcaoCobranca.trim().equals("1")) {
								cobrancaAcaoAtividadeCronograma
										.setComando(Util
												.converteStringParaDateHora(dataPrevista
														+ " "
														+ Util
																.formatarHoraSemData(new Date())));
							} else {
								cobrancaAcaoAtividadeCronograma
										.setComando(null);
							}
						} else {
							cobrancaAcaoAtividadeCronograma
									.setDataPrevista(null);
							cobrancaAcaoAtividadeCronograma.setComando(null);
						}
						cobrancasAtividadesParaInsercao
								.add(cobrancaAcaoAtividadeCronograma);
					}
					/**
					 * Caso o usuario informe a data prevista somente para
					 * algumas atividades da acao, exibir a mensagem "�
					 * necess�rio informar a data prevista para todas as
					 * atividades da a��o."
					 */
					if ((verificaDataPreenchida > 0)
							&& (verificaDataPreenchida < atividadesCobrancaObrigatoriedadeAtivo
									.size())) {
						throw new ActionServletException(
								"atencao.cobranca.data_prevista_algumas_atividades");
					}

					cobrancaCronogramaHelper
							.setCobrancasAtividadesParaInsercao(cobrancasAtividadesParaInsercao);

					/**
					 * 
					 */
					colecaoCobrancaHelperNaBase.add(cobrancaCronogramaHelper);

				}

				sessao.removeAttribute("colecaoCobrancaCronogramaHelper");

				// Organizar a cole��o
				Collections.sort((List) colecaoCobrancaHelperNaBase,
						new Comparator() {
							public int compare(Object a, Object b) {
								Short posicao1 = ((CobrancaCronogramaHelper) a)
										.getCobrancaAcaoCronograma()
										.getCobrancaAcao().getOrdemRealizacao();
								Short posicao2 = ((CobrancaCronogramaHelper) b)
										.getCobrancaAcaoCronograma()
										.getCobrancaAcao().getOrdemRealizacao();

								return posicao1.compareTo(posicao2);
							}
						});

				sessao.setAttribute("colecaoCobrancaCronogramaHelper",
						colecaoCobrancaHelperNaBase);
			}
		}

		if (httpServletRequest.getParameter("adicionar") != null) {
			sessao.removeAttribute("cobrancaAcaoEscolhida");
		}
		sessao.setAttribute("colecaoCobrancaAcaoNovo", colecaoAcaoCobrancaNovo);
		return retorno;
	}

}
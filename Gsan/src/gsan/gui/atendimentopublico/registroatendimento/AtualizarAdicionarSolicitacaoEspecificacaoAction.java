/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
 * GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araújo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cláudio de Andrade Lira
 * Denys Guimarães Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabíola Gomes de Araújo
 * Flávio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Júnior
 * Homero Sampaio Cavalcanti
 * Ivan Sérgio da Silva Júnior
 * José Edmar de Siqueira
 * José Thiago Tenório Lopes
 * Kássia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Márcio Roberto Batista da Silva
 * Maria de Fátima Sampaio Leite
 * Micaela Maria Coelho de Araújo
 * Nelson Mendonça de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrêa Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araújo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos de Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; versão 2 da
 * Licença.
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gsan.gui.atendimentopublico.registroatendimento;

import gsan.atendimentopublico.ordemservico.FiltroServicoTipo;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.atendimentopublico.registroatendimento.ArquivoProcedimentoOperacionalPadrao;
import gsan.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.bean.SolicitacaoEspecificacaoHelper;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.faturamento.debito.DebitoTipo;
import gsan.faturamento.debito.FiltroDebitoTipo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 13/11/2006
 */
public class AtualizarAdicionarSolicitacaoEspecificacaoAction extends
		GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarAdicionarSolicitacaoEspecificacao");

		Collection colecaoSolicitacaoTipoEspecificacao = null;
		if (sessao.getAttribute("colecaoSolicitacaoTipoEspecificacao") == null) {
			colecaoSolicitacaoTipoEspecificacao = new ArrayList();
		} else {
			colecaoSolicitacaoTipoEspecificacao = (Collection) sessao
					.getAttribute("colecaoSolicitacaoTipoEspecificacao");
		}

		// String idSolicitacaoTipo = sessao.getAttribute("idTipoSolicitacao",
		// idSolicitacaoTipo);

		AtualizarAdicionarSolicitacaoEspecificacaoActionForm atualizarAdicionarSolicitacaoEspecificacaoActionForm = (AtualizarAdicionarSolicitacaoEspecificacaoActionForm) actionForm;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		if (sessao.getAttribute("atualizar") != null) {

			// Obs: Se remover tudo da coleção, qdo a coleçao tiver mais de um
			// elemento vai dar pau. Entao, terei que Instaciar um objeto pra
			// receber os dados da colecao que esta na sessao, para depois
			// comparar com a que esta sendo alterada, pra depois fazer
			// alteração.

			Integer posicaoComponente = (Integer) sessao
					.getAttribute("posicaoComponente");

			int index = 0;

			Iterator colecaoSolicitacaoTipoEspecificacaoIterator = colecaoSolicitacaoTipoEspecificacao
					.iterator();

			while (colecaoSolicitacaoTipoEspecificacaoIterator.hasNext()) {

				index++;

				SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) colecaoSolicitacaoTipoEspecificacaoIterator
						.next();

				if (index == posicaoComponente) {

					solicitacaoTipoEspecificacao
							.setIndicadorSolicitante(new Short("1"));

					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getPrazoPrevisaoAtendimento() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getPrazoPrevisaoAtendimento().equals("")) {
						// Prazo de previsão de atendimento
						solicitacaoTipoEspecificacao.setDiasPrazo(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getPrazoPrevisaoAtendimento()));
					}
					// Descrição da especificação
					solicitacaoTipoEspecificacao
							.setDescricao(atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getDescricaoSolicitacao());

					// Pavimento de calçada obrigatório
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPavimentoCalcada() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorPavimentoCalcada().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorPavimentoCalcada(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorPavimentoCalcada()));
					}
					// Pavimento de rua obrigatório
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPavimentoRua() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorPavimentoRua().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorPavimentoRua(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorPavimentoRua()));
					}

					// refere-se a ligação de agua
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorLigacaoAgua() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorLigacaoAgua().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorLigacaoAgua(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorLigacaoAgua()));
					}
					// refere-se a ligação de Poço
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPoco() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorPoco().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorPoco(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorPoco()));
					}
					// refere-se a ligação de Esgoto
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorLigacaoEsgoto() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorLigacaoEsgoto().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorLigacaoEsgoto(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorLigacaoEsgoto()));
					}
					// Cobrança de material
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCobrancaMaterial() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorCobrancaMaterial().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorCobrancaMaterial(new Integer(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorCobrancaMaterial()));
					}
					// Matricula do imóvel obrigatório
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorMatriculaImovel() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorMatriculaImovel().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorMatricula(new Integer(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorMatriculaImovel()));
					}
					// indicador Urgência
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorUrgencia() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorUrgencia().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorUrgencia(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorUrgencia()));
					}
					// Parecer de encerramento obrigatório
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorParecerEncerramento() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorParecerEncerramento().equals(
											"")) {
						solicitacaoTipoEspecificacao
								.setIndicadorParecerEncerramento(new Integer(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorParecerEncerramento()));
					}
					// Gera débito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGerarDebito() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorGerarDebito().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorGeracaoDebito(new Integer(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorGerarDebito()));
					}
					// Gera Crédito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGerarCredito() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorGerarCredito().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorGeracaoCredito(new Integer(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorGerarCredito()));
					}
					// hora e data correntes
					solicitacaoTipoEspecificacao.setUltimaAlteracao(new Date());

					// Unidade inicial tramitação
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdUnidadeTramitacao() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdUnidadeTramitacao().equals("")) {
						// Verifica se o código foi modificado
						if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getDescricaoUnidadeTramitacao() == null
								|| atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getDescricaoUnidadeTramitacao().trim()
										.equals("")) {

							FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

							filtroUnidadeOrganizacional
									.adicionarParametro(new ParametroSimples(
											FiltroUnidadeOrganizacional.ID,
											atualizarAdicionarSolicitacaoEspecificacaoActionForm
													.getIdUnidadeTramitacao()));

							filtroUnidadeOrganizacional
									.adicionarParametro(new ParametroSimples(
											FiltroUnidadeOrganizacional.INDICADOR_USO,
											ConstantesSistema.INDICADOR_USO_ATIVO));

							Collection unidadeOrganizacionalEncontrado = fachada
									.pesquisar(filtroUnidadeOrganizacional,
											UnidadeOrganizacional.class
													.getName());

							if (unidadeOrganizacionalEncontrado != null
									&& !unidadeOrganizacionalEncontrado
											.isEmpty()) {
								UnidadeOrganizacional uinidadeOrganizacional = (UnidadeOrganizacional) ((List) unidadeOrganizacionalEncontrado)
										.get(0);
								solicitacaoTipoEspecificacao
										.setUnidadeOrganizacional(uinidadeOrganizacional);

							} else {
								throw new ActionServletException(
										"atencao.pesquisa_inexistente", null,
										"Unidade Organizacional");
							}

						} else {
							UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
							unidadeOrganizacional.setId(new Integer(
									atualizarAdicionarSolicitacaoEspecificacaoActionForm
											.getIdUnidadeTramitacao()));
							solicitacaoTipoEspecificacao
									.setUnidadeOrganizacional(unidadeOrganizacional);
						}
					} else {
						solicitacaoTipoEspecificacao
								.setUnidadeOrganizacional(null);
					}

					// id do tipo da solicitação gerada
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdServicoOS() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdServicoOS().equals("")) {

						// Verifica se o código foi modificado
						if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getDescricaoServicoOS() == null
								|| atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getDescricaoServicoOS().trim().equals(
												"")) {

							FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

							filtroServicoTipo
									.adicionarParametro(new ParametroSimples(
											FiltroServicoTipo.ID,
											atualizarAdicionarSolicitacaoEspecificacaoActionForm
													.getIdServicoOS()));

							Collection servicoTipoEncontrado = fachada
									.pesquisar(filtroServicoTipo,
											ServicoTipo.class.getName());

							if (servicoTipoEncontrado != null
									&& !servicoTipoEncontrado.isEmpty()) {
								// [SF0003] - Validar Tipo Serviço
								fachada
										.verificarServicoTipoReferencia(new Integer(
												atualizarAdicionarSolicitacaoEspecificacaoActionForm
														.getIdServicoOS()));

								ServicoTipo servicoTipo = (ServicoTipo) ((List) servicoTipoEncontrado)
										.get(0);
								solicitacaoTipoEspecificacao
										.setServicoTipo(servicoTipo);
							} else {
								throw new ActionServletException(
										"atencao.pesquisa_inexistente", null,
										"Serviço Tipo");
							}
						} else {
							ServicoTipo servicoTipo = new ServicoTipo();
							servicoTipo.setId(new Integer(
									atualizarAdicionarSolicitacaoEspecificacaoActionForm
											.getIdServicoOS()));

							servicoTipo
									.setDescricao(atualizarAdicionarSolicitacaoEspecificacaoActionForm
											.getDescricaoServicoOS());

							solicitacaoTipoEspecificacao
									.setServicoTipo(servicoTipo);
						}
						
					} else{
						ServicoTipo servicoTipo = new ServicoTipo();

						solicitacaoTipoEspecificacao
								.setServicoTipo(servicoTipo);

					}

					// Gera ordem de serviço
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGeraOrdemServico() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorGeraOrdemServico().equals("")) {

						solicitacaoTipoEspecificacao
								.setIndicadorGeracaoOrdemServico(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorGeraOrdemServico()));
					}
					// Cliente Obrigatório
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCliente() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorCliente().equals("")) {

						solicitacaoTipoEspecificacao
								.setIndicadorCliente(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorCliente()));
					}

					// Indicador verfificcação de débito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorVerificarDebito() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorVerificarDebito().equals("")) {

						solicitacaoTipoEspecificacao
								.setIndicadorVerificarDebito(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorVerificarDebito()));
					}

					// Indicador encerramento automático
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorEncerramentoAutomatico() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorEncerramentoAutomatico()
									.equals("")) {

						solicitacaoTipoEspecificacao
								.setIndicadorEncerramentoAutomatico(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorEncerramentoAutomatico()));
					}
					
					//Indicador loja virtual
					if(Util.verificarNaoVazio(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorLojaVirtual())){
						solicitacaoTipoEspecificacao.setIndicadorLojaVirtual(new Short(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorLojaVirtual()));
					}

					//RM 5759 - Amélia Pessoa - 05/12/2011
					//indicador Permite Cancelar Debito
					if(Util.verificarNaoVazio(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorPermiteCancelarDebito())){
						solicitacaoTipoEspecificacao.setIndicadorPermiteCancelarDebito(new Short(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorPermiteCancelarDebito()));
					}
					
					/*RM 7642 - Flavio Ferreira - 20/11/203
					indicador Tipo de Especificação Listado Consultar Imovel */
					if(Util.verificarNaoVazio(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorTipoEspecificacaoListadoPopupConsultarImovel())){
						solicitacaoTipoEspecificacao.setIndicadorTipoEspecificacaoListadoPopupConsultarImovel(new Short(
							atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorTipoEspecificacaoListadoPopupConsultarImovel()));
					}
					
					//RM 5924 - Amélia Pessoa 15/12/2011
					//Adicionar arquivo de procedimentos operacionais padrões POPs
					Collection<ArquivoProcedimentoOperacionalPadrao> colecaoArquivos = (ArrayList<ArquivoProcedimentoOperacionalPadrao>) sessao.getAttribute("colecaoArquivos");
					Collection<SolicitacaoEspecificacaoHelper> colecaoHelpers = (Collection<SolicitacaoEspecificacaoHelper>) sessao.getAttribute("colecaoHelpers");
					SolicitacaoEspecificacaoHelper helperRemove=null;
					if (colecaoHelpers!=null){
						for (SolicitacaoEspecificacaoHelper seh : colecaoHelpers){
							if (seh.getIdHelper()==solicitacaoTipoEspecificacao.getId()){
								helperRemove = seh;
							}
						}
					}
					if (helperRemove!=null){
						colecaoHelpers.remove(helperRemove);
					}
					if (colecaoHelpers==null){
						colecaoHelpers = new ArrayList<SolicitacaoEspecificacaoHelper>();
					}
					colecaoHelpers.add(new SolicitacaoEspecificacaoHelper(solicitacaoTipoEspecificacao.getId(), colecaoArquivos));
					//Atualizar dados da sessao
					//sessao.removeAttribute("colecaoArquivos");
					sessao.removeAttribute("colecaoHelpers");
					sessao.setAttribute("colecaoHelpers", colecaoHelpers);
					solicitacaoTipoEspecificacao.setColecaoArquivosPOP(colecaoArquivos);
					
					// Situação imovel
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdSituacaoImovel() != null) {
						
						EspecificacaoImovelSituacao especificacaoImovelSituacao = null;
						if(!atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIdSituacaoImovel().equals("")){
							
							especificacaoImovelSituacao = new EspecificacaoImovelSituacao();
							especificacaoImovelSituacao.setId(new Integer(
									atualizarAdicionarSolicitacaoEspecificacaoActionForm
											.getIdSituacaoImovel()));
						}
						solicitacaoTipoEspecificacao
							.setEspecificacaoImovelSituacao(especificacaoImovelSituacao);
					}
					Collection colecaoEspecificacaoServicoTipo = (Collection) sessao
							.getAttribute("colecaoEspecificacaoServicoTipo");
					// recupera a coleção de especificacao servico tipo
					if (colecaoEspecificacaoServicoTipo != null
							&& !colecaoEspecificacaoServicoTipo.isEmpty()) {

						Collection colecao = new ArrayList();
						colecao.addAll(colecaoEspecificacaoServicoTipo);
						// [SF0004] - Validar Valor Ordem de Serviço 2ª parte
						fachada.verificarOrdemExecucaoForaOrdem(colecao);
						solicitacaoTipoEspecificacao
								.setEspecificacaoServicoTipos(new HashSet(
										colecaoEspecificacaoServicoTipo));
						sessao
								.removeAttribute("colecaoEspecificacaoServicoTipo");
					}

					// Colocado por Raphael Rossiter em 25/02/2008
					// Tipo de Débito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdDebitoTipo() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdDebitoTipo().equals("")) {

						FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

						filtroDebitoTipo
								.adicionarParametro(new ParametroSimples(
										FiltroDebitoTipo.ID,
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIdDebitoTipo()));

						Collection debitoTipoEncontrado = fachada.pesquisar(
								filtroDebitoTipo, DebitoTipo.class.getName());

						if (debitoTipoEncontrado != null
								&& !debitoTipoEncontrado.isEmpty()) {

							DebitoTipo debitoTipo = (DebitoTipo) Util
									.retonarObjetoDeColecao(debitoTipoEncontrado);

							solicitacaoTipoEspecificacao
									.setDebitoTipo(debitoTipo);
						} else {

							// [FS0007] - Validar Tipo de débito
							throw new ActionServletException(
									"atencao.pesquisa_inexistente", null,
									"Tipo de Débito");
						}

					} else {
						solicitacaoTipoEspecificacao.setDebitoTipo(null);
					}

					// Valor do Débito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getValorDebito() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getValorDebito().equals("")) {

						solicitacaoTipoEspecificacao
								.setValorDebito(Util
										.formatarMoedaRealparaBigDecimal(atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getValorDebito()));
					} else {
						solicitacaoTipoEspecificacao.setValorDebito(null);
					}

					// Alterar Valor do débito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPermiteAlterarValor() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorPermiteAlterarValor().equals(
											"")) {
						solicitacaoTipoEspecificacao
								.setIndicadorPermiteAlterarValor(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorPermiteAlterarValor()));
					}

					// Cobrar Juros
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCobrarJuros() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorCobrarJuros().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorCobrarJuros(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorCobrarJuros()));
					}
					
					
					//Indicador Documento Obrigatório
					if(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorDocumentoObrigatorio() != null 
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorDocumentoObrigatorio().equals("")){
						
						if(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorDocumentoObrigatorio().equals(ConstantesSistema.SIM.toString())){
							
							solicitacaoTipoEspecificacao.setIndicadorDocumentoObrigatorio(ConstantesSistema.SIM);
							
						}else{
							
							solicitacaoTipoEspecificacao.setIndicadorDocumentoObrigatorio(ConstantesSistema.NAO);
							
						}						
					}
						

					// indicador de uso ativo
					solicitacaoTipoEspecificacao.setIndicadorUso(new Short(
							ConstantesSistema.INDICADOR_USO_ATIVO));

					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdEspecificacao() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdEspecificacao().equals("")) {
						SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoNovoRA = new SolicitacaoTipoEspecificacao();
						solicitacaoTipoEspecificacaoNovoRA
								.setId(Integer
										.parseInt(atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIdEspecificacao()));

						SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
						solicitacaoTipo
								.setId(Integer
										.parseInt(atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIdTipoSolicitacao()));
						solicitacaoTipoEspecificacaoNovoRA
								.setSolicitacaoTipo(solicitacaoTipo);

						solicitacaoTipoEspecificacao
								.setSolicitacaoTipoEspecificacaoNovoRA(solicitacaoTipoEspecificacaoNovoRA);
					} else {
						
						solicitacaoTipoEspecificacao.setSolicitacaoTipoEspecificacaoNovoRA(null);
					}

					// Informar conta no Registro de Atendimento
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorInformarContaRA() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorInformarContaRA().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorInformarContaRA(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorInformarContaRA()));
					}

					// Informar conta no Registro de Atendimento
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorInformarPagamentoDP() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorInformarPagamentoDP().equals(
											"")) {
						solicitacaoTipoEspecificacao
								.setIndicadorInformarPagamentoDuplicidade(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorInformarPagamentoDP()));
					}

					// Informar alteração no vencimento alternativo
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorAlterarVencimentoAlternativo() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorAlterarVencimentoAlternativo()
									.equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorAlterarVencimentoAlternativo(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorAlterarVencimentoAlternativo()));
					}
					
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm.getObservacao() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm.getObservacao().equals("")) {
								solicitacaoTipoEspecificacao.setObservacao
									(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getObservacao());
					}
						
				}
			}

		} else {

			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();

			solicitacaoTipoEspecificacao
					.setIndicadorSolicitante(new Short("1"));

			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getPrazoPrevisaoAtendimento() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getPrazoPrevisaoAtendimento().equals("")) {
				// Prazo de previsão de atendimento
				solicitacaoTipoEspecificacao.setDiasPrazo(new Integer(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getPrazoPrevisaoAtendimento()));
			}
			// Descrição da especificação
			solicitacaoTipoEspecificacao
					.setDescricao(atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getDescricaoSolicitacao());

			// Pavimento de calçada obrigatório
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorPavimentoCalcada() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPavimentoCalcada().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorPavimentoCalcada(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorPavimentoCalcada()));
			}
			// Pavimento de rua obrigatório
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorPavimentoRua() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPavimentoRua().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorPavimentoRua(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorPavimentoRua()));
			}

			// refere-se a ligação de agua
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorLigacaoAgua() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorLigacaoAgua().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorLigacaoAgua(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorLigacaoAgua()));
			}
			// refere-se a ligação de Poco
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorPoco() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPoco().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorPoco(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorPoco()));
			}
			// refere-se a ligação de Esgoto
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorLigacaoEsgoto() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorLigacaoEsgoto().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorLigacaoEsgoto(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorLigacaoEsgoto()));
			}
			
			// Cobrança de material
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorCobrancaMaterial() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCobrancaMaterial().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorCobrancaMaterial(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorCobrancaMaterial()));
			}
			// Matricula do imóvel obrigatório
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorMatriculaImovel() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorMatriculaImovel().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorMatricula(new Integer(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorMatriculaImovel()));
			}
			// Indicador Urgência
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorUrgencia() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorUrgencia().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorUrgencia(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorUrgencia()));
			}
			// Parecer de encerramento obrigatório
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorParecerEncerramento() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorParecerEncerramento().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorParecerEncerramento(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorParecerEncerramento()));
			}
			// Gera débito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorGerarDebito() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGerarDebito().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorGeracaoDebito(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorGerarDebito()));
			}
			// Gera Crédito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorGerarCredito() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGerarCredito().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorGeracaoCredito(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorGerarCredito()));
			}

			// Encerramento Automático
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorEncerramentoAutomatico() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorEncerramentoAutomatico().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorEncerramentoAutomatico(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorEncerramentoAutomatico()));
			}
			
			//Indicador loja virtual
			if(Util.verificarNaoVazio(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorLojaVirtual())){
				solicitacaoTipoEspecificacao.setIndicadorLojaVirtual(new Short(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorLojaVirtual()));
			}

			// hora e data correntes
			solicitacaoTipoEspecificacao.setUltimaAlteracao(new Date());

			// Unidade inicial tramitação
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdUnidadeTramitacao() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdUnidadeTramitacao().equals("")) {
				// Verifica se o código foi modificado
				if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
						.getDescricaoUnidadeTramitacao() == null
						|| atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getDescricaoUnidadeTramitacao().trim().equals(
										"")) {

					FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

					filtroUnidadeOrganizacional
							.adicionarParametro(new ParametroSimples(
									FiltroUnidadeOrganizacional.ID,
									atualizarAdicionarSolicitacaoEspecificacaoActionForm
											.getIdUnidadeTramitacao()));

					filtroUnidadeOrganizacional
							.adicionarParametro(new ParametroSimples(
									FiltroUnidadeOrganizacional.INDICADOR_USO,
									ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection unidadeOrganizacionalEncontrado = fachada
							.pesquisar(filtroUnidadeOrganizacional,
									UnidadeOrganizacional.class.getName());

					if (unidadeOrganizacionalEncontrado != null
							&& !unidadeOrganizacionalEncontrado.isEmpty()) {
						UnidadeOrganizacional uinidadeOrganizacional = (UnidadeOrganizacional) ((List) unidadeOrganizacionalEncontrado)
								.get(0);
						solicitacaoTipoEspecificacao
								.setUnidadeOrganizacional(uinidadeOrganizacional);

					} else {
						throw new ActionServletException(
								"atencao.pesquisa_inexistente", null,
								"Unidade Organizacional");
					}

				} else {
					UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
					unidadeOrganizacional.setId(new Integer(
							atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdUnidadeTramitacao()));
					solicitacaoTipoEspecificacao
							.setUnidadeOrganizacional(unidadeOrganizacional);
				}
			}

			// id do tipo da solicitação gerada
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdServicoOS() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdServicoOS().equals("")) {

				// Verifica se o código foi modificado
				if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
						.getDescricaoServicoOS() == null
						|| atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getDescricaoServicoOS().trim().equals("")) {

					FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

					filtroServicoTipo.adicionarParametro(new ParametroSimples(
							FiltroServicoTipo.ID,
							atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdServicoOS()));

					Collection servicoTipoEncontrado = fachada.pesquisar(
							filtroServicoTipo, ServicoTipo.class.getName());

					if (servicoTipoEncontrado != null
							&& !servicoTipoEncontrado.isEmpty()) {
						// [SF0003] - Validar Tipo Serviço
						fachada.verificarServicoTipoReferencia(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIdServicoOS()));

						ServicoTipo servicoTipo = (ServicoTipo) ((List) servicoTipoEncontrado)
								.get(0);
						solicitacaoTipoEspecificacao
								.setServicoTipo(servicoTipo);
					} else {
						throw new ActionServletException(
								"atencao.pesquisa_inexistente", null,
								"Serviço Tipo");
					}
				} else {
					ServicoTipo servicoTipo = new ServicoTipo();
					servicoTipo.setId(new Integer(
							atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdServicoOS()));
					solicitacaoTipoEspecificacao.setServicoTipo(servicoTipo);
				}

			}

			// Gera ordem de serviço
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorGeraOrdemServico() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGeraOrdemServico().equals("")) {

				solicitacaoTipoEspecificacao
						.setIndicadorGeracaoOrdemServico(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorGeraOrdemServico()));
			}
			// Cliente Obrigatório
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorCliente() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCliente().equals("")) {

				solicitacaoTipoEspecificacao.setIndicadorCliente(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorCliente()));
			}

			// Indicador verfificcação de débito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorVerificarDebito() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorVerificarDebito().equals("")) {

				solicitacaoTipoEspecificacao
						.setIndicadorVerificarDebito(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorVerificarDebito()));
			}

			// Situação imovel
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdSituacaoImovel() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdSituacaoImovel().equals("")) {
				EspecificacaoImovelSituacao especificacaoImovelSituacao = new EspecificacaoImovelSituacao();
				especificacaoImovelSituacao.setId(new Integer(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIdSituacaoImovel()));
				solicitacaoTipoEspecificacao
						.setEspecificacaoImovelSituacao(especificacaoImovelSituacao);
			}
			Collection colecaoEspecificacaoServicoTipo = (Collection) sessao
					.getAttribute("colecaoEspecificacaoServicoTipo");
			// recupera a coleção de especificacao servico tipo
			if (colecaoEspecificacaoServicoTipo != null
					&& !colecaoEspecificacaoServicoTipo.isEmpty()) {

				Collection colecao = new ArrayList();
				colecao.addAll(colecaoEspecificacaoServicoTipo);

				// [SF0004] - Validar Valor Ordem de Serviço 2ª parte
				fachada.verificarOrdemExecucaoForaOrdem(colecao);
				solicitacaoTipoEspecificacao
						.setEspecificacaoServicoTipos(new HashSet(
								colecaoEspecificacaoServicoTipo));
				sessao.removeAttribute("colecaoEspecificacaoServicoTipo");
			}

			// Colocado por Raphael Rossiter em 25/02/2008
			// Tipo de Débito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdDebitoTipo() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdDebitoTipo().equals("")) {

				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

				filtroDebitoTipo.adicionarParametro(new ParametroSimples(
						FiltroDebitoTipo.ID,
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIdDebitoTipo()));

				Collection debitoTipoEncontrado = fachada.pesquisar(
						filtroDebitoTipo, DebitoTipo.class.getName());

				if (debitoTipoEncontrado != null
						&& !debitoTipoEncontrado.isEmpty()) {

					DebitoTipo debitoTipo = (DebitoTipo) Util
							.retonarObjetoDeColecao(debitoTipoEncontrado);

					solicitacaoTipoEspecificacao.setDebitoTipo(debitoTipo);
				} else {

					// [FS0007] - Validar Tipo de débito
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tipo de Débito");
				}

			}

			// Valor do Débito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getValorDebito() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getValorDebito().equals("")) {

				solicitacaoTipoEspecificacao
						.setValorDebito(Util
								.formatarMoedaRealparaBigDecimal(atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getValorDebito()));
			}

			// Alterar Valor do débito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorPermiteAlterarValor() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPermiteAlterarValor().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorPermiteAlterarValor(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorPermiteAlterarValor()));
			}

			// Cobrar Juros
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorCobrarJuros() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCobrarJuros().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorCobrarJuros(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorCobrarJuros()));
			}

			// indicador de uso ativo
			solicitacaoTipoEspecificacao.setIndicadorUso(new Short(
					ConstantesSistema.INDICADOR_USO_ATIVO));

			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdEspecificacao() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdEspecificacao().equals("")) {
				SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoNovoRA = new SolicitacaoTipoEspecificacao();
				solicitacaoTipoEspecificacaoNovoRA
						.setId(Integer
								.parseInt(atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIdEspecificacao()));

				SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
				solicitacaoTipo
						.setId(Integer
								.parseInt(atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIdTipoSolicitacao()));
				solicitacaoTipoEspecificacaoNovoRA
						.setSolicitacaoTipo(solicitacaoTipo);

				solicitacaoTipoEspecificacao
						.setSolicitacaoTipoEspecificacaoNovoRA(solicitacaoTipoEspecificacaoNovoRA);
			} else {
				
				solicitacaoTipoEspecificacao.setSolicitacaoTipoEspecificacaoNovoRA(null);
			}

			// Informar conta no Registro de Atendimento
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorInformarContaRA() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorInformarContaRA().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorInformarContaRA(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorInformarContaRA()));
			}

			// Informar Pagamento em duplicidade
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorInformarPagamentoDP() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorInformarPagamentoDP().equals("")) {

				solicitacaoTipoEspecificacao
						.setIndicadorInformarPagamentoDuplicidade(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorInformarPagamentoDP()));
			}

			// Informar Alteração no Vencimento Alternativo
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorAlterarVencimentoAlternativo() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorAlterarVencimentoAlternativo().equals("")) {

				solicitacaoTipoEspecificacao
						.setIndicadorAlterarVencimentoAlternativo(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorAlterarVencimentoAlternativo()));
			}
			if (!colecaoSolicitacaoTipoEspecificacao
					.contains(solicitacaoTipoEspecificacao)) {
				colecaoSolicitacaoTipoEspecificacao
						.add(solicitacaoTipoEspecificacao);
			}
			
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm.getObservacao() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm.getObservacao().equals("")) {
						solicitacaoTipoEspecificacao.setObservacao
							(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getObservacao());
			}
			
		}

		sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
				colecaoSolicitacaoTipoEspecificacao);

		// manda um parametro para fechar o popup
		httpServletRequest.setAttribute("fecharPopup", 1);

		return retorno;
	}
}

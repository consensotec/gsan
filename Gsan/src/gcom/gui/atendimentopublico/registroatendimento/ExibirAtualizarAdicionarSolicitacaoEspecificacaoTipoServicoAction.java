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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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
 * @date 22/11/2006
 */
public class ExibirAtualizarAdicionarSolicitacaoEspecificacaoTipoServicoAction
		extends GcomAction {
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

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarAdicionarSolicitacaoEspecificacaoTipoServico");

		AtualizarAdicionarSolicitacaoEspecificacaoActionForm atualizarAdicionarSolicitacaoEspecificacaoActionForm = (AtualizarAdicionarSolicitacaoEspecificacaoActionForm) actionForm;

		sessao.setAttribute(
				"atualizarAdicionarSolicitacaoEspecificacaoActionForm",
				atualizarAdicionarSolicitacaoEspecificacaoActionForm);

		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// pega os dados do form para nao ter perdas qdo votarmos a tela
		// anterior

		if (sessao.getAttribute("solicitacaoTipoEspecificacao") == null) {

			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();

			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getPrazoPrevisaoAtendimento() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getPrazoPrevisaoAtendimento().equals("")) {
				// Prazo de previs�o de atendimento
				solicitacaoTipoEspecificacao.setDiasPrazo(new Integer(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getPrazoPrevisaoAtendimento()));
			}
			// Descri��o da especifica��o
			solicitacaoTipoEspecificacao
					.setDescricao(atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getDescricaoSolicitacao());

			// Pavimento de cal�ada obrigat�rio
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorPavimentoCalcada() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPavimentoCalcada().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorPavimentoCalcada(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorPavimentoCalcada()));
			}
			// Pavimento de rua obrigat�rio
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorPavimentoRua() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPavimentoRua().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorPavimentoRua(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorPavimentoRua()));
			}

			// refere-se a liga��o de agua
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorLigacaoAgua() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorLigacaoAgua().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorLigacaoAgua(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorLigacaoAgua()));
			}
			// Cobran�a de material
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorCobrancaMaterial() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCobrancaMaterial().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorCobrancaMaterial(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorCobrancaMaterial()));
			}
			// Matricula do im�vel obrigat�rio
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorMatriculaImovel() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorMatriculaImovel().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorMatricula(new Integer(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorMatriculaImovel()));
			}
			// Parecer de encerramento obrigat�rio
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorParecerEncerramento() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorParecerEncerramento().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorParecerEncerramento(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorParecerEncerramento()));
			}
			// Gera d�bito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorGerarDebito() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGerarDebito().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorGeracaoDebito(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorGerarDebito()));
			}
			// Gera Cr�dito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorGerarCredito() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGerarCredito().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorGeracaoCredito(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorGerarCredito()));
			}

			// Unidade inicial tramita��o
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdUnidadeTramitacao() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdUnidadeTramitacao().equals("")) {
				// Verifica se o c�digo foi modificado
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

			// id do tipo da solicita��o gerada
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdServicoOS() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdServicoOS().equals("")) {

				// Verifica se o c�digo foi modificado
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
						// [SF0003] - Validar Tipo Servi�o
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
								"Servi�o Tipo");
					}
				} else {
					ServicoTipo servicoTipo = new ServicoTipo();
					servicoTipo.setId(new Integer(
							atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdServicoOS()));
					solicitacaoTipoEspecificacao.setServicoTipo(servicoTipo);
				}

			}

			// Gera ordem de servi�o
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorGeraOrdemServico() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGeraOrdemServico().equals("")) {

				solicitacaoTipoEspecificacao
						.setIndicadorGeracaoOrdemServico(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorGeraOrdemServico()));
			}
			// Cliente Obrigat�rio
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorCliente() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCliente().equals("")) {

				solicitacaoTipoEspecificacao.setIndicadorCliente(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorCliente()));
			}

			// Situa��o imovel
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
			// recupera a cole��o de especificacao servico tipo
			if (colecaoEspecificacaoServicoTipo != null
					&& !colecaoEspecificacaoServicoTipo.isEmpty()) {

				Collection colecao = new ArrayList();
				colecao.addAll(colecaoEspecificacaoServicoTipo);
				// [SF0004] - Validar Valor Ordem de Servi�o 2� parte
				fachada.verificarOrdemExecucaoForaOrdem(colecao);
				solicitacaoTipoEspecificacao
						.setEspecificacaoServicoTipos(new HashSet(
								colecaoEspecificacaoServicoTipo));
				// sessao.removeAttribute("colecaoEspecificacaoServicoTipo");
			}

			// indicador de uso ativo
			solicitacaoTipoEspecificacao.setIndicadorUso(new Short(
					ConstantesSistema.INDICADOR_USO_ATIVO));

			SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();

			solicitacaoTipo = (SolicitacaoTipo) sessao
					.getAttribute("solicitacaoTipoAtualizar");

			solicitacaoTipoEspecificacao.setSolicitacaoTipo(solicitacaoTipo);

			sessao.setAttribute("solicitacaoTipoEspecificacao",
					solicitacaoTipoEspecificacao);
		}

		// caso exista o parametro ent�o limpa a sess�o e o form
		if (httpServletRequest.getParameter("limpaSessao") != null
				&& !httpServletRequest.getParameter("limpaSessao").equals("")) {

			atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.setDescricaoTipoServico("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.setIdTipoServico("");
			atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.setOrdemExecucao("");

		}

		// recupera o caminho de retorno passado como parametro no jsp que chama
		// essa funcionalidade
		if (httpServletRequest.getParameter("retornarTelaPopup") != null) {
			sessao.setAttribute("retornarTelaPopup", httpServletRequest
					.getParameter("retornarTelaPopup"));
		}

		// Verifica se o tipoConsulta � diferente de nulo ou vazio.Nesse caso �
		// quando um o retorno da consulta vem para o action ao inves de ir
		// direto para o jsp
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			// verifica se retornou da pesquisa de tipo de servi�o
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"tipoServico")) {

				atualizarAdicionarSolicitacaoEspecificacaoActionForm
						.setIdTipoServico(httpServletRequest
								.getParameter("idCampoEnviarDados"));

				atualizarAdicionarSolicitacaoEspecificacaoActionForm
						.setDescricaoTipoServico(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

			}
		}

		// -------Parte que trata do c�digo quando o usu�rio tecla enter
		String idTipoServico = (String) atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.getIdTipoServico();
		String descricaoServico = atualizarAdicionarSolicitacaoEspecificacaoActionForm
				.getDescricaoTipoServico();

		// Verifica se o c�digo foi digitado pela primeira vez ou se foi
		// modificado
		if (idTipoServico != null
				&& !idTipoServico.trim().equals("")
				&& (descricaoServico == null || descricaoServico.trim().equals(
						""))) {

			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.ID, idTipoServico));

			Collection servicoTipoEncontrado = fachada.pesquisar(
					filtroServicoTipo, ServicoTipo.class.getName());

			if (servicoTipoEncontrado != null
					&& !servicoTipoEncontrado.isEmpty()) {
				atualizarAdicionarSolicitacaoEspecificacaoActionForm
						.setIdTipoServico(""
								+ ((ServicoTipo) ((List) servicoTipoEncontrado)
										.get(0)).getId());
				atualizarAdicionarSolicitacaoEspecificacaoActionForm
						.setDescricaoTipoServico(((ServicoTipo) ((List) servicoTipoEncontrado)
								.get(0)).getDescricao());
				httpServletRequest.setAttribute("idTipoServicoNaoEncontrado",
						"true");

				httpServletRequest.setAttribute("nomeCampo", "ordemExecucao");

			} else {

				atualizarAdicionarSolicitacaoEspecificacaoActionForm
						.setIdTipoServico("");
				httpServletRequest.setAttribute("nomeCampo", "idServicoOS");
				httpServletRequest.setAttribute("idTipoServicoNaoEncontrado",
						"exception");
				atualizarAdicionarSolicitacaoEspecificacaoActionForm
						.setDescricaoTipoServico("Tipo Servi�o Inexistente");

			}

		}

		sessao.removeAttribute("caminhoRetornoTelaPesquisaServicoTipo");

		// -------Fim da Parte que trata do c�digo quando o usu�rio tecla enter

		return retorno;
	}
}

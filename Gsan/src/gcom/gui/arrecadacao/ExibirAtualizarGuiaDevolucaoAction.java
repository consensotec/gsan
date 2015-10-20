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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.FiltroGuiaDevolucao;
import gcom.arrecadacao.GuiaDevolucao;
import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarGuiaDevolucaoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarGuiaDevolucao");

		AtualizarGuiaDevolucaoActionForm atualizarGuiaDevolucaoActionForm = (AtualizarGuiaDevolucaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Faz um filtro de tipo de documento, setando par�metros para retornar
		// apenas os tipos de documentos requisitados pelo caso de uso, para ser
		// exibida a cole��o na tela
		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.CONTA,
				ConectorOr.CONECTOR_OR, 4));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.GUIA_PAGAMENTO,
				ConectorOr.CONECTOR_OR));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.DEBITO_A_COBRAR,
				ConectorOr.CONECTOR_OR));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.DEVOLUCAO_VALOR,
				ConectorOr.CONECTOR_OR));
		filtroDocumentoTipo.setCampoOrderBy(FiltroDocumentoTipo.DESCRICAO);

		Collection colecaoDocumentoTipo = fachada.pesquisar(
				filtroDocumentoTipo, DocumentoTipo.class.getName());

		if (colecaoDocumentoTipo != null && !colecaoDocumentoTipo.isEmpty()) {
			sessao.setAttribute("colecaoDocumentoTipo", colecaoDocumentoTipo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Tipo de Documento");
		}

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		GuiaDevolucao guiaDevolucao = null;

		// Verifica se o usu�rio clicou no bot�o desfazer e remova a guia de
		// devolu��o da sess�o para entrar no restante do action como se fosse
		// pela primeira vez
		if (httpServletRequest.getParameter("desfazer") != null) {
			sessao.removeAttribute("guiaDevolucaoAtualizar");
		}

		// Verifica se a guia de devolu��o j� est� na sess�o, em caso afirmativo
		// significa que o usu�rio j� entrou na tela e apenas selecionou algum
		// item que deu um reload na tela e em caso negativo significa que ele
		// est� entrando pela primeira vez
		if (sessao.getAttribute("guiaDevolucaoAtualizar") == null) {

			// Verifica se a guia de devolu��o est� na sess�o, em caso
			// afirmativo significa que o usu�rio selecionou o check box de
			// atualizar e a pesquisa retornou apenas uma guia de devolu��o,
			// assim, action que chamou foi o FiltrarGuiaDevolucaoAction. E em
			// caso negativo significa que o usu�rio selecionou o link da p�gina
			// do Manter Guia de Devolu��o, assim, faz-se a necessidade de criar
			// um
			// filtro para retornar o objeto completo para ser exibidos seus
			// dados na tela de Atualizar Guia de Devolu��o
			if (sessao.getAttribute("guiaDevolucao") != null) {

				guiaDevolucao = (GuiaDevolucao) sessao
						.getAttribute("guiaDevolucao");

			} else {

				String idGuiaDevolucao = null;

				if (httpServletRequest.getParameter("guiaDevolucaoID") != null) {

					idGuiaDevolucao = httpServletRequest
							.getParameter("guiaDevolucaoID");
				} else {
					idGuiaDevolucao = (String) sessao
							.getAttribute("idGuiaDevolucao");
				}

				sessao.setAttribute("idGuiaDevolucao", idGuiaDevolucao);

				FiltroGuiaDevolucao filtroGuiaDevolucao = new FiltroGuiaDevolucao();
				filtroGuiaDevolucao.adicionarParametro(new ParametroSimples(
						FiltroGuiaDevolucao.ID, idGuiaDevolucao));

				// Faz os carregamentos necess�rios para a exii��o na tela e
				// para a atualiza��o futura
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.localidade");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.setorComercial");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.quadra");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.cliente");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("ordemServico.servicoTipo.debitoTipo");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("cliente");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("conta");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("guiaPagamento.debitoTipo");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.debitoTipo");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAnterior");
				filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");

				Collection colecaoGuiaDevolucao = fachada.pesquisar(
						filtroGuiaDevolucao, GuiaDevolucao.class.getName());

				if (colecaoGuiaDevolucao == null
						|| colecaoGuiaDevolucao.isEmpty()) {
					throw new ActionServletException("atencao.colecao_vazia");
				}

				guiaDevolucao = (GuiaDevolucao) colecaoGuiaDevolucao.iterator()
						.next();

			}

			// Seta na tela os dados da guia de devolu��o selecionada pelo o
			// usu�rio e caso algum dos dados esteja como nulo, limpa esses
			// campos para evitar sujeira na tela
			atualizarGuiaDevolucaoActionForm.setIdGuiaDevolucao(guiaDevolucao
					.getId().toString());
			atualizarGuiaDevolucaoActionForm
					.setIdRegistroAtendimento(guiaDevolucao
							.getRegistroAtendimento().getId().toString());
			atualizarGuiaDevolucaoActionForm
					.setNomeRegistroAtendimento("FUNC. IMPLEMENTADA PARCIALMENTE");

			if (guiaDevolucao.getOrdemServico() != null) {
				atualizarGuiaDevolucaoActionForm
						.setIdOrdemServico(guiaDevolucao.getOrdemServico()
								.getId().toString());
				atualizarGuiaDevolucaoActionForm
						.setNomeOrdemServico("FUNC. IMPLEMENTADA PARCIALMENTE");
				if (guiaDevolucao.getOrdemServico().getServicoTipo() != null
						&& guiaDevolucao.getOrdemServico().getServicoTipo()
								.getDebitoTipo() != null) {
					httpServletRequest.setAttribute("bloqueioDebitoTipo", true);
					httpServletRequest.setAttribute("bloqueioDocumentoTipo",
							true);
				}
			} else {
				atualizarGuiaDevolucaoActionForm.setIdOrdemServico("");
				atualizarGuiaDevolucaoActionForm.setNomeOrdemServico("");
			}

			atualizarGuiaDevolucaoActionForm.setDocumentoTipo(guiaDevolucao
					.getDocumentoTipo().getId().toString());
			atualizarGuiaDevolucaoActionForm
					.setDocumentoTipoHidden(guiaDevolucao.getDocumentoTipo()
							.getId().toString());

			if (guiaDevolucao.getLocalidade() != null) {
				atualizarGuiaDevolucaoActionForm.setIdLocalidade(guiaDevolucao
						.getLocalidade().getId().toString());
				atualizarGuiaDevolucaoActionForm
						.setIdLocalidadeHidden(guiaDevolucao.getLocalidade()
								.getId().toString());
				atualizarGuiaDevolucaoActionForm
						.setDescricaoLocalidade(guiaDevolucao.getLocalidade()
								.getDescricao());
			} else {
				atualizarGuiaDevolucaoActionForm.setIdLocalidade("");
				atualizarGuiaDevolucaoActionForm.setIdLocalidadeHidden("");
				atualizarGuiaDevolucaoActionForm.setDescricaoLocalidade("");
			}

			if (guiaDevolucao.getImovel() != null) {
				atualizarGuiaDevolucaoActionForm.setIdImovel(guiaDevolucao
						.getImovel().getId().toString());
				atualizarGuiaDevolucaoActionForm
						.setDescricaoImovel(guiaDevolucao.getImovel()
								.getInscricaoFormatada());
				atualizarGuiaDevolucaoActionForm.setCodigoCliente("");
				atualizarGuiaDevolucaoActionForm.setNomeCliente("");
				httpServletRequest.setAttribute("bloqueioLocalidade", true);
			} else {
				atualizarGuiaDevolucaoActionForm.setCodigoCliente(guiaDevolucao
						.getCliente().getId().toString());
				atualizarGuiaDevolucaoActionForm.setNomeCliente(guiaDevolucao
						.getCliente().getNome());
				atualizarGuiaDevolucaoActionForm.setIdImovel("");
				atualizarGuiaDevolucaoActionForm.setDescricaoImovel("");
			}

			if (guiaDevolucao.getDebitoTipo() != null) {
				atualizarGuiaDevolucaoActionForm.setIdTipoDebito(guiaDevolucao
						.getDebitoTipo().getId().toString());
				atualizarGuiaDevolucaoActionForm
						.setIdTipoDebitoHidden(guiaDevolucao.getDebitoTipo()
								.getId().toString());
				atualizarGuiaDevolucaoActionForm
						.setDescricaoTipoDebito(guiaDevolucao.getDebitoTipo()
								.getDescricao());
			} else {
				atualizarGuiaDevolucaoActionForm.setIdTipoDebito("");
				atualizarGuiaDevolucaoActionForm.setIdTipoDebitoHidden("");
				atualizarGuiaDevolucaoActionForm.setDescricaoTipoDebito("");
			}

			// Seta os campos de conta, d�bito a cobrar, guia pagamento ou tipo
			// de d�bito e localidade, dependendo do tipo de documento
			// selecionado na guia de devolu��o.
			// Limpa os campos que n�o foram selecionados para evitar
			// "sujeiras" na tela. E faz os bloqueios necess�rios para deixar os
			// campos que n�o v�o poder ser selecionados pelo o usu�rio
			// desabilitados.

			if (guiaDevolucao.getDocumentoTipo().getId().equals(
					DocumentoTipo.CONTA)) {

				// Seta os campos da conta
				atualizarGuiaDevolucaoActionForm.setReferenciaConta(Util
						.formatarMesAnoReferencia(guiaDevolucao.getConta()
								.getReferencia()));
				atualizarGuiaDevolucaoActionForm
						.setDescricaoConta(guiaDevolucao.getConta()
								.getValorTotalConta());

				// Limpa os campos dos outros tipos de documento
				atualizarGuiaDevolucaoActionForm.setIdGuiaPagamento("");
				atualizarGuiaDevolucaoActionForm.setDescricaoGuiaPagamento("");
				atualizarGuiaDevolucaoActionForm.setValorGuiaPagamento("");
				atualizarGuiaDevolucaoActionForm.setIdDebitoACobrar("");
				atualizarGuiaDevolucaoActionForm.setDescricaoDebitoACobrar("");
				atualizarGuiaDevolucaoActionForm.setValorDebitoACobrar("");

				// Faz os bloqueios necess�rios
				httpServletRequest.setAttribute("bloqueioLocalidade", true);
				httpServletRequest.setAttribute("bloqueioDebitoTipo", true);
				httpServletRequest.setAttribute("bloqueioGuia", true);
				httpServletRequest.setAttribute("bloqueioDebitoACobrar", true);
			} else if (guiaDevolucao.getDocumentoTipo().getId().equals(
					DocumentoTipo.GUIA_PAGAMENTO)) {

				// Seta os campos da guia de pagamento
				atualizarGuiaDevolucaoActionForm
						.setIdGuiaPagamento(guiaDevolucao.getGuiaPagamento()
								.getId().toString());
				atualizarGuiaDevolucaoActionForm
						.setDescricaoGuiaPagamento(guiaDevolucao
								.getGuiaPagamento().getDebitoTipo()
								.getDescricao());
				atualizarGuiaDevolucaoActionForm.setValorGuiaPagamento(Util
						.formatarMoedaReal(guiaDevolucao.getGuiaPagamento()
								.getValorDebito()));

				// Limpa os campos dos outros tipos de documento
				atualizarGuiaDevolucaoActionForm.setReferenciaConta("");
				atualizarGuiaDevolucaoActionForm.setDescricaoConta("");
				atualizarGuiaDevolucaoActionForm.setIdDebitoACobrar("");
				atualizarGuiaDevolucaoActionForm.setDescricaoDebitoACobrar("");
				atualizarGuiaDevolucaoActionForm.setValorDebitoACobrar("");

				// Faz os bloqueios necess�rios
				httpServletRequest.setAttribute("bloqueioLocalidade", true);
				httpServletRequest.setAttribute("bloqueioDebitoTipo", true);
				httpServletRequest.setAttribute("bloqueioConta", true);
				httpServletRequest.setAttribute("bloqueioDebitoACobrar", true);
			} else if (guiaDevolucao.getDocumentoTipo().getId().equals(
					DocumentoTipo.DEBITO_A_COBRAR)) {

				// Seta os campos de d�bito a cobrar
				atualizarGuiaDevolucaoActionForm.setIdDebitoACobrar(
					guiaDevolucao.getDebitoACobrarGeral().getDebitoACobrar().getId().toString());
				atualizarGuiaDevolucaoActionForm.setDescricaoDebitoACobrar(
					guiaDevolucao.getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo().getDescricao());
				atualizarGuiaDevolucaoActionForm.setValorDebitoACobrar(
					Util.formatarMoedaReal(guiaDevolucao.getDebitoACobrarGeral().
						getDebitoACobrar().getValorTotalComBonus()));

				// Limpa os campos dos outros tipos de documento
				atualizarGuiaDevolucaoActionForm.setReferenciaConta("");
				atualizarGuiaDevolucaoActionForm.setDescricaoConta("");
				atualizarGuiaDevolucaoActionForm.setIdGuiaPagamento("");
				atualizarGuiaDevolucaoActionForm.setDescricaoGuiaPagamento("");
				atualizarGuiaDevolucaoActionForm.setValorGuiaPagamento("");

				// Faz os bloqueios necess�rios
				httpServletRequest.setAttribute("bloqueioLocalidade", true);
				httpServletRequest.setAttribute("bloqueioDebitoTipo", true);
				httpServletRequest.setAttribute("bloqueioConta", true);
				httpServletRequest.setAttribute("bloqueioGuia", true);
			} else if (guiaDevolucao.getDocumentoTipo().getId().equals(
					DocumentoTipo.DEVOLUCAO_VALOR)) {

				// Seta os campos do tipo de d�bito e de localidade
				atualizarGuiaDevolucaoActionForm.setIdLocalidade(guiaDevolucao
						.getLocalidade().getId().toString());
				atualizarGuiaDevolucaoActionForm
						.setIdLocalidadeHidden(guiaDevolucao.getLocalidade()
								.getId().toString());
				atualizarGuiaDevolucaoActionForm
						.setDescricaoLocalidade(guiaDevolucao.getLocalidade()
								.getDescricao());
				atualizarGuiaDevolucaoActionForm.setIdTipoDebito(guiaDevolucao
						.getDebitoTipo().getId().toString());
				atualizarGuiaDevolucaoActionForm
						.setIdTipoDebitoHidden(guiaDevolucao.getDebitoTipo()
								.getId().toString());
				atualizarGuiaDevolucaoActionForm
						.setDescricaoTipoDebito(guiaDevolucao.getDebitoTipo()
								.getDescricao());

				// Limpa os campos dos outros tipos de d�bito
				atualizarGuiaDevolucaoActionForm.setReferenciaConta("");
				atualizarGuiaDevolucaoActionForm.setDescricaoConta("");
				atualizarGuiaDevolucaoActionForm.setIdGuiaPagamento("");
				atualizarGuiaDevolucaoActionForm.setDescricaoGuiaPagamento("");
				atualizarGuiaDevolucaoActionForm.setValorGuiaPagamento("");
				atualizarGuiaDevolucaoActionForm.setIdDebitoACobrar("");
				atualizarGuiaDevolucaoActionForm.setDescricaoDebitoACobrar("");
				atualizarGuiaDevolucaoActionForm.setValorDebitoACobrar("");

				// Faz os bloqueios necess�rios
				httpServletRequest.setAttribute("bloqueioGuia", true);
				httpServletRequest.setAttribute("bloqueioConta", true);
				httpServletRequest.setAttribute("bloqueioDebitoACobrar", true);
			}

			atualizarGuiaDevolucaoActionForm.setDataValidade(Util
					.formatarData(guiaDevolucao.getDataValidade()));
			atualizarGuiaDevolucaoActionForm.setValorDevolucao(Util
					.formatarMoedaReal(guiaDevolucao.getValorDevolucao()));

			// Seta a guia de devolu��o que ser� atualizada na sess�o para poder
			// ser recuperada posteriormente no AtualizarGuiaDevolucaoAction
			sessao.setAttribute("guiaDevolucaoAtualizar", guiaDevolucao);

			// Remove a guia de devolu��o
			sessao.removeAttribute("guiaDevolucao");

		} else {
			// Parte que trata do c�digo quando o usu�rio tecla enter

			String idRegistroAtendimento = atualizarGuiaDevolucaoActionForm
					.getIdRegistroAtendimento();
			String idOrdemServico = atualizarGuiaDevolucaoActionForm
					.getIdOrdemServico();
			String idLocalidade = atualizarGuiaDevolucaoActionForm
					.getIdLocalidadeHidden();
			String referenciaConta = atualizarGuiaDevolucaoActionForm
					.getReferenciaConta();
			String idGuiaPagamento = atualizarGuiaDevolucaoActionForm
					.getIdGuiaPagamento();
			String idDebitoACobrar = atualizarGuiaDevolucaoActionForm
					.getIdDebitoACobrar();
			String idDebitoTipo = atualizarGuiaDevolucaoActionForm
					.getIdTipoDebitoHidden();
			String idImovel = atualizarGuiaDevolucaoActionForm.getIdImovel();
			String idCliente = atualizarGuiaDevolucaoActionForm
					.getCodigoCliente();

			RegistroAtendimento registroAtendimento = null;
			OrdemServico ordemServico = null;

			if (idRegistroAtendimento != null
					&& !idRegistroAtendimento.trim().equals("")) {
				FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
				filtroRegistroAtendimento
						.adicionarParametro(new ParametroSimples(
								FiltroRegistroAtendimento.ID,
								idRegistroAtendimento));
				filtroRegistroAtendimento
						.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
				filtroRegistroAtendimento
						.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
				filtroRegistroAtendimento
						.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
				filtroRegistroAtendimento
						.adicionarCaminhoParaCarregamentoEntidade("cliente");

				Collection colecaoRegistrosAtendimentos = fachada.pesquisar(
						filtroRegistroAtendimento, RegistroAtendimento.class
								.getName());

				if (colecaoRegistrosAtendimentos != null
						&& !colecaoRegistrosAtendimentos.isEmpty()) {
					registroAtendimento = (RegistroAtendimento) colecaoRegistrosAtendimentos
							.iterator().next();
					atualizarGuiaDevolucaoActionForm
							.setNomeRegistroAtendimento("FUNC. IMPLEMENTADA PARCIALMENTE");
					if (registroAtendimento.getImovel() != null) {

						atualizarGuiaDevolucaoActionForm
								.setIdImovel(registroAtendimento.getImovel()
										.getId().toString());
						atualizarGuiaDevolucaoActionForm
								.setDescricaoImovel(registroAtendimento
										.getImovel().getInscricaoFormatada());

						httpServletRequest.setAttribute("bloqueioLocalidade",
								true);

						if (registroAtendimento.getImovel().getLocalidade() != null) {
							idLocalidade = registroAtendimento.getImovel()
									.getLocalidade().getId().toString();
						}

						atualizarGuiaDevolucaoActionForm.setCodigoCliente("");
						atualizarGuiaDevolucaoActionForm.setNomeCliente("");

					}  else {
						throw new ActionServletException(
								"atencao.registro.atendimento.sem.imovel.cliente",
								null, idRegistroAtendimento);
					}

				} else {
					atualizarGuiaDevolucaoActionForm
							.setIdRegistroAtendimento("");
					atualizarGuiaDevolucaoActionForm
							.setNomeRegistroAtendimento("REGISTRO ATENDIMENTO INEXISTENTE");
				}

			} else {
				atualizarGuiaDevolucaoActionForm.setNomeRegistroAtendimento("");
			}

			if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {
				FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
				filtroOrdemServico.adicionarParametro(new ParametroSimples(
						FiltroOrdemServico.ID, idOrdemServico));
				filtroOrdemServico
						.adicionarCaminhoParaCarregamentoEntidade("servicoTipo.debitoTipo");

				Collection colecaoOrdemServico = fachada.pesquisar(
						filtroOrdemServico, OrdemServico.class.getName());

				if (colecaoOrdemServico != null
						&& !colecaoOrdemServico.isEmpty()) {
					ordemServico = (OrdemServico) colecaoOrdemServico
							.iterator().next();

					atualizarGuiaDevolucaoActionForm
							.setNomeOrdemServico("FUNC. IMPLEMENTADA PARCIALMENTE");

					if (ordemServico.getServicoTipo() != null
							&& ordemServico.getServicoTipo().getDebitoTipo() != null) {
						idDebitoTipo = ordemServico.getServicoTipo()
								.getDebitoTipo().getId().toString();
						httpServletRequest.setAttribute("bloqueioDebitoTipo",
								true);
						atualizarGuiaDevolucaoActionForm.setDocumentoTipo(""
								+ DocumentoTipo.DEVOLUCAO_VALOR);
						atualizarGuiaDevolucaoActionForm
								.setDocumentoTipoHidden(""
										+ DocumentoTipo.DEVOLUCAO_VALOR);
						httpServletRequest.setAttribute(
								"bloqueioDocumentoTipo", true);
					}
				} else {
					atualizarGuiaDevolucaoActionForm.setIdOrdemServico("");
					atualizarGuiaDevolucaoActionForm
							.setNomeOrdemServico("ORDEM DE SERVI�O INEXISTENTE");
				}

			} else {
				atualizarGuiaDevolucaoActionForm.setNomeOrdemServico("");
			}

			if (ordemServico != null && registroAtendimento != null) {
				if (ordemServico.getRegistroAtendimento() != null
						&& !ordemServico.getRegistroAtendimento().getId()
								.equals(registroAtendimento.getId())) {
					throw new ActionServletException(
							"atencao.ordem.servico.diferente.registro.atendimento",
							null, idRegistroAtendimento);
				}
			}

			if (idLocalidade != null && !idLocalidade.trim().equals("")) {
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, idLocalidade));

				Collection colecaoLocalidade = fachada.pesquisar(
						filtroLocalidade, Localidade.class.getName());

				if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
					Localidade localidade = (Localidade) colecaoLocalidade
							.iterator().next();

					atualizarGuiaDevolucaoActionForm
							.setIdLocalidade(idLocalidade);
					atualizarGuiaDevolucaoActionForm
							.setIdLocalidadeHidden(idLocalidade);
					atualizarGuiaDevolucaoActionForm
							.setDescricaoLocalidade(localidade.getDescricao());

				} else {
					atualizarGuiaDevolucaoActionForm.setIdLocalidade("");
					atualizarGuiaDevolucaoActionForm.setIdLocalidadeHidden("");
					atualizarGuiaDevolucaoActionForm
							.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
					httpServletRequest.setAttribute("localidadeInexistente",
							true);
				}
			} else {
				atualizarGuiaDevolucaoActionForm.setDescricaoLocalidade("");
			}

			// [FS0010] - Verificar exist�ncia da guia de pagamento
			if (idGuiaPagamento != null && !idGuiaPagamento.trim().equals("")) {
				// Caso o usu�rio n�o tenha informado a matr�cula do im�vel
				// Levanta uma exce��o para o usu�rio indicado que ele n�o
				// informou
				// a matr�cula do im�vel
				if (idImovel == null || idImovel.trim().equalsIgnoreCase("")) {
					if (idCliente == null
							|| idCliente.trim().equalsIgnoreCase("")) {
						throw new ActionServletException(
								"atencao.naoinformado", null,
								"Im�vel ou Cliente");
					}
				}
				// Pesquisa a guia de pagamento para o im�vel ou o cliente
				// informado
				GuiaPagamento guiaPagamento = this
						.pesquisarGuiaPagamentoDigitada(idImovel, idCliente,
								idGuiaPagamento);
				// Caso a guia de pagamento esteja cadastrada no sistema
				// Seta os dados da guai de pagamento no form
				// Caso contr�rio seta os dados da guia para nulo e informa ao
				// usu�rio que n�o existe guia de pagamento cadastrada no
				// sistema
				if (guiaPagamento != null) {

					// [FS0011] - Verificar im�vel ou cliente da guia de
					// pagamento
					if (idImovel != null && !idImovel.equals("")) {
						if (guiaPagamento.getImovel() == null) {
							throw new ActionServletException(
									"atencao.guia.pagamento.imovel.inexistente");
						} else {
							// Verifica se o id do im�vel do Registro de
							// Atendimento
							// � diferente do id do imovel da Guia de Pagamento
							if (!(idImovel.equals(guiaPagamento.getImovel()
									.getId().toString()))) {
								throw new ActionServletException(
										"atencao.imovel.guia.pagamento.diferente.registro.atendimento.imovel",
										guiaPagamento.getImovel().getId().toString(), idImovel);
							} else {
								// Verifica se o id do localidade do im�vel do
								// Registro de Atendimento
								// � diferente do id da localidade da Guia de
								// Pagamento
								if (!(idLocalidade.equals(guiaPagamento
										.getLocalidade().getId().toString()))) {
									throw new ActionServletException(
											"atencao.imovel.localidade.diferente.guia.pagamento.localidade",
											guiaPagamento.getLocalidade().getId().toString(),
											idLocalidade, idImovel);
								}
							}
						}
					} else if (idCliente != null && !idCliente.equals("")) {
						if (guiaPagamento.getCliente() == null) {
							throw new ActionServletException(
									"atencao.guia.pagamento.cliente.inexistente");
						} else {
							// Verifica se o id do cliente do Registro de
							// Atendimento
							// � diferente do id do cliente da Guia de Pagamento
							if (!(idCliente.equals(guiaPagamento.getCliente()
									.getId().toString()))) {
								throw new ActionServletException(
										"atencao.cliente.guia.pagamento.diferente.registro.atendimento.cliente",
										guiaPagamento.getCliente().getId().toString(), idCliente);
							} else {
								// Seta o campo localidade com a localidade da
								// Guia
								// de Pagamento
								atualizarGuiaDevolucaoActionForm
										.setIdLocalidade(guiaPagamento
												.getLocalidade().getId()
												.toString());
								atualizarGuiaDevolucaoActionForm
										.setDescricaoLocalidade(guiaPagamento
												.getLocalidade().getDescricao());
							}
						}
					}

					if (ordemServico != null) {
						if (ordemServico.getServicoTipo().getDebitoTipo() != null) {
							if (!(guiaPagamento.getDebitoTipo().getId()
									.equals(ordemServico.getServicoTipo()
											.getDebitoTipo().getId()))) {
								throw new ActionServletException(
										"atencao.debito.tipo.guia.pagamento.diferente.ordem.servico.debito.tipo",
										guiaPagamento.getDebitoTipo()
												.getDescricao(), ordemServico
												.getServicoTipo()
												.getDebitoTipo().getDescricao());
							}
						}
					}

					if (httpServletRequest
							.getAttribute("RegistroAtendimentoSemImovel") == null) {

						BigDecimal valorDevolucao = guiaPagamento
								.getValorDebito();

						if (valorDevolucao != null) {

							atualizarGuiaDevolucaoActionForm
									.setValorDevolucao(Util
											.formatarMoedaReal(valorDevolucao));

							atualizarGuiaDevolucaoActionForm
									.setValorGuiaPagamento(""
											+ Util
													.formatarMoedaReal(valorDevolucao));
						}
					}

					atualizarGuiaDevolucaoActionForm
							.setDescricaoGuiaPagamento(guiaPagamento
									.getDebitoTipo().getDescricao());

					httpServletRequest.setAttribute("guiaPagamentoInexistente",
							false);

				} else {
					atualizarGuiaDevolucaoActionForm.setIdGuiaPagamento("");
					atualizarGuiaDevolucaoActionForm
							.setDescricaoGuiaPagamento("GUIA PAG. INEXISTENTE");
					httpServletRequest.setAttribute("guiaPagamentoInexistente",
							true);
				}
			} else {
				atualizarGuiaDevolucaoActionForm.setDescricaoGuiaPagamento("");
				atualizarGuiaDevolucaoActionForm.setValorGuiaPagamento("");
			}

			// [FS0017] - Verificar exist�ncia do tipo de d�bito
			if (idDebitoTipo != null && !idDebitoTipo.equals("")) {
				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
				filtroDebitoTipo.adicionarParametro(new ParametroSimples(
						FiltroDebitoTipo.ID, idDebitoTipo));

				Collection colecaoDebitoTipo = fachada.pesquisar(
						filtroDebitoTipo, DebitoTipo.class.getName());

				if (colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty()) {
					DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo
							.iterator().next();
					atualizarGuiaDevolucaoActionForm
							.setIdTipoDebito(idDebitoTipo);
					atualizarGuiaDevolucaoActionForm
							.setIdTipoDebitoHidden(idDebitoTipo);
					atualizarGuiaDevolucaoActionForm
							.setDescricaoTipoDebito(debitoTipo.getDescricao());
				} else {
					atualizarGuiaDevolucaoActionForm.setIdTipoDebito("");
					atualizarGuiaDevolucaoActionForm.setIdTipoDebitoHidden("");
					atualizarGuiaDevolucaoActionForm
							.setDescricaoTipoDebito("TIPO DE D�BITO INEXISTENTE");
					httpServletRequest.setAttribute("tipoDebitoInexistente",
							true);
				}
			} else {
				atualizarGuiaDevolucaoActionForm.setDescricaoTipoDebito("");
			}

			// [FS0008] - Verificar exist�ncia da conta

			// Caso o usu�rio tenha informado a refer�ncia da conta
			if (referenciaConta != null
					&& !referenciaConta.trim().equalsIgnoreCase("")) {

				// Caso o usu�rio n�o tenha informado a matr�cula do im�vel
				// Levanta uma exce��o para o usu�rio indicado que ele n�o
				// informou
				// a matr�cula do im�vel
				if (registroAtendimento.getImovel() == null) {
					throw new ActionServletException("atencao.naoinformado",
							null, "Im�vel");
				}

				// Recupera a conta do im�vel com a refer�ncia informada
				// Cria o filtro de conta e seta todos os par�metros para
				// pesquisar a
				// conta do im�vel
				FiltroConta filtroConta = new FiltroConta();
				filtroConta.adicionarParametro(new ParametroSimples(
						FiltroConta.IMOVEL_ID, registroAtendimento.getImovel()
								.getId().toString()));
				filtroConta
						.adicionarParametro(new ParametroSimples(
								FiltroConta.REFERENCIA,
								Util
										.formatarMesAnoParaAnoMesSemBarra(referenciaConta)));
				filtroConta
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroConta.adicionarCaminhoParaCarregamentoEntidade("imovel");

				Collection colecaoConta = fachada.pesquisar(filtroConta,
						Conta.class.getName());

				// Caso a conta esteja cadastrada no sistema
				// Seta todas as informa��es da conta no form
				// Caso contr�rio seta as informa��es da conta para nulo
				// e indica ao usu�rio que n�o existe conta para o im�el
				// informadocom a refer�ncia indicada
				if (colecaoConta != null && !colecaoConta.isEmpty()) {

					// Recupera a conta do im�vel com a refer�ncia informada
					Conta conta = (Conta) colecaoConta.iterator().next();

					// [FS0009] - Verificar localidade da conta
					if (!idLocalidade.equals(conta.getLocalidade().getId()
							.toString())) {
						throw new ActionServletException(
								"atencao.imovel.localidade.diferente.conta.localidade",
								conta.getLocalidade().getId().toString(),
								idLocalidade, idImovel);
					} else {
						atualizarGuiaDevolucaoActionForm
								.setDescricaoConta(""
										+ Util.formatarMoedaReal(conta
												.getValorTotal()));
						atualizarGuiaDevolucaoActionForm.setValorDevolucao(Util
								.formatarMoedaReal(conta.getValorTotal()));
						httpServletRequest.setAttribute("contaInexistente",
								false);
					}
				} else {
					atualizarGuiaDevolucaoActionForm.setReferenciaConta("");
					atualizarGuiaDevolucaoActionForm
							.setDescricaoConta("CONTA INEXISTENTE");
					httpServletRequest.setAttribute("contaInexistente", true);
				}
			} else {
				atualizarGuiaDevolucaoActionForm.setDescricaoConta("");
			}

			// [FS0013] - Verificar exist�ncia do d�bito a cobrar

			// Caso o usu�rio tenha informado o c�digo do d�bito a cobrar
			if (idDebitoACobrar != null
					&& !idDebitoACobrar.trim().equalsIgnoreCase("")) {

				// Caso o usu�rio n�o tenha informado a matr�cula do im�vel
				// Levanta uma exce��o para o usu�rio indicado que ele n�o
				// informou
				// a matr�cula do im�vel
				if (registroAtendimento.getImovel() == null) {
					throw new ActionServletException("atencao.naoinformado",
							null, "Im�vel");
				}

				// Pesquisa o d�bito a cobrar para o im�vel informado
				DebitoACobrar debitoACobrar = this
						.pesquisarDebitoACobrarDigitado(idImovel,
								idDebitoACobrar);

				if (debitoACobrar != null) {
					if (!(idImovel.equals(debitoACobrar.getImovel().getId()
							.toString()))) {
						throw new ActionServletException(
								"atencao.imovel.debito.a.cobrar.diferente.registro.atendimento.imovel",
								debitoACobrar.getImovel().getId()
										.toString(), idImovel);
					} else {
						// [FS0015] - Verificar localidade do d�bito a cobrar
						if (!idLocalidade.equals(debitoACobrar.getLocalidade()
								.getId().toString())) {
							throw new ActionServletException(
									"atencao.imovel.localidade.diferente.debito.a.cobrar.localidade",
									debitoACobrar.getLocalidade().getId()
											.toString(), idLocalidade, idImovel);
						}
					}

					if (ordemServico != null) {
						if (ordemServico.getServicoTipo().getDebitoTipo() != null) {
							if (!(debitoACobrar.getDebitoTipo().getId()
									.equals(ordemServico.getServicoTipo()
											.getDebitoTipo().getId()))) {
								throw new ActionServletException(
										"atencao.debito.tipo.debito.a.cobrar.diferente.ordem.servico.debito.tipo",
										debitoACobrar.getDebitoTipo()
												.getDescricao(), ordemServico
												.getServicoTipo()
												.getDebitoTipo().getDescricao());
							}
						}
					}

					BigDecimal valorDevolucao = debitoACobrar.getValorTotalComBonus();
					if (valorDevolucao != null) {
						atualizarGuiaDevolucaoActionForm.setValorDevolucao(Util
								.formatarMoedaReal(valorDevolucao));
						atualizarGuiaDevolucaoActionForm
								.setValorDebitoACobrar(Util
										.formatarMoedaReal(valorDevolucao));
					}

					atualizarGuiaDevolucaoActionForm
							.setDescricaoDebitoACobrar(debitoACobrar
									.getDebitoTipo().getDescricao());

				} else {
					atualizarGuiaDevolucaoActionForm.setIdDebitoACobrar("");
					atualizarGuiaDevolucaoActionForm
							.setDescricaoDebitoACobrar("DEB. A COB. INEXISTENTE");
					httpServletRequest.setAttribute("debitoACobrarInexistente",
							true);
				}
			} else {
				atualizarGuiaDevolucaoActionForm.setDescricaoDebitoACobrar("");
				atualizarGuiaDevolucaoActionForm.setValorDebitoACobrar("");
			}

			// [SB0003] - Habilitar/Desabilitar Campos

			// Parte que faz as verifica��es para habilitar e desabilitar alguns
			// campos.

			if (atualizarGuiaDevolucaoActionForm.getDocumentoTipo() != null
					&& !atualizarGuiaDevolucaoActionForm
							.getDocumentoTipo()
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				if (atualizarGuiaDevolucaoActionForm.getDocumentoTipo().equals(
						DocumentoTipo.CONTA.toString())) {
					atualizarGuiaDevolucaoActionForm.setIdGuiaPagamento("");
					atualizarGuiaDevolucaoActionForm
							.setDescricaoGuiaPagamento("");
					atualizarGuiaDevolucaoActionForm.setValorGuiaPagamento("");
					atualizarGuiaDevolucaoActionForm.setIdDebitoACobrar("");
					atualizarGuiaDevolucaoActionForm
							.setDescricaoDebitoACobrar("");
					atualizarGuiaDevolucaoActionForm.setValorDebitoACobrar("");

					if (referenciaConta == null
							|| referenciaConta.trim().equals("")) {

						atualizarGuiaDevolucaoActionForm.setValorDevolucao("");

					}

					if (ordemServico == null
							|| ordemServico.getServicoTipo() == null
							|| ordemServico.getServicoTipo().getDebitoTipo() == null) {
						atualizarGuiaDevolucaoActionForm.setIdTipoDebito("");
						atualizarGuiaDevolucaoActionForm
								.setIdTipoDebitoHidden("");
						atualizarGuiaDevolucaoActionForm
								.setDescricaoTipoDebito("");
					}
					httpServletRequest.setAttribute("bloqueioLocalidade", true);
					httpServletRequest.setAttribute("bloqueioGuia", true);
					httpServletRequest.setAttribute("bloqueioDebitoACobrar",
							true);
					httpServletRequest.setAttribute("bloqueioDebitoTipo", true);
				} else if (atualizarGuiaDevolucaoActionForm.getDocumentoTipo()
						.equals(DocumentoTipo.GUIA_PAGAMENTO.toString())) {
					atualizarGuiaDevolucaoActionForm.setReferenciaConta("");
					atualizarGuiaDevolucaoActionForm.setDescricaoConta("");
					atualizarGuiaDevolucaoActionForm.setIdDebitoACobrar("");
					atualizarGuiaDevolucaoActionForm
							.setDescricaoDebitoACobrar("");
					atualizarGuiaDevolucaoActionForm.setValorDebitoACobrar("");

					if (idGuiaPagamento == null
							|| idGuiaPagamento.trim().equals("")) {
						atualizarGuiaDevolucaoActionForm.setValorDevolucao("");
					}

					if (ordemServico == null
							|| ordemServico.getServicoTipo() == null
							|| ordemServico.getServicoTipo().getDebitoTipo() == null) {
						atualizarGuiaDevolucaoActionForm.setIdTipoDebito("");
						atualizarGuiaDevolucaoActionForm
								.setIdTipoDebitoHidden("");
						atualizarGuiaDevolucaoActionForm
								.setDescricaoTipoDebito("");
					}

					httpServletRequest.setAttribute("bloqueioLocalidade", true);
					httpServletRequest.setAttribute("bloqueioDebitoACobrar",
							true);
					httpServletRequest.setAttribute("bloqueioConta", true);
					httpServletRequest.setAttribute("bloqueioDebitoTipo", true);
				} else if (atualizarGuiaDevolucaoActionForm.getDocumentoTipo()
						.equals(DocumentoTipo.DEBITO_A_COBRAR.toString())) {
					atualizarGuiaDevolucaoActionForm.setReferenciaConta("");
					atualizarGuiaDevolucaoActionForm.setDescricaoConta("");
					atualizarGuiaDevolucaoActionForm.setIdGuiaPagamento("");
					atualizarGuiaDevolucaoActionForm
							.setDescricaoGuiaPagamento("");
					atualizarGuiaDevolucaoActionForm.setValorGuiaPagamento("");
					if (idDebitoACobrar == null
							|| idDebitoACobrar.trim().equals("")) {
						atualizarGuiaDevolucaoActionForm.setValorDevolucao("");
					}

					if (ordemServico == null
							|| ordemServico.getServicoTipo() == null
							|| ordemServico.getServicoTipo().getDebitoTipo() == null) {
						atualizarGuiaDevolucaoActionForm.setIdTipoDebito("");
						atualizarGuiaDevolucaoActionForm
								.setIdTipoDebitoHidden("");
						atualizarGuiaDevolucaoActionForm
								.setDescricaoTipoDebito("");
					}

					httpServletRequest.setAttribute("bloqueioLocalidade", true);
					httpServletRequest.setAttribute("bloqueioGuia", true);
					httpServletRequest.setAttribute("bloqueioConta", true);
					httpServletRequest.setAttribute("bloqueioDebitoTipo", true);
				} else if (atualizarGuiaDevolucaoActionForm.getDocumentoTipo()
						.equals(DocumentoTipo.DEVOLUCAO_VALOR.toString())) {
					atualizarGuiaDevolucaoActionForm.setReferenciaConta("");
					atualizarGuiaDevolucaoActionForm.setDescricaoConta("");
					atualizarGuiaDevolucaoActionForm.setIdGuiaPagamento("");
					atualizarGuiaDevolucaoActionForm
							.setDescricaoGuiaPagamento("");
					atualizarGuiaDevolucaoActionForm.setValorGuiaPagamento("");
					atualizarGuiaDevolucaoActionForm.setIdDebitoACobrar("");
					atualizarGuiaDevolucaoActionForm
							.setDescricaoDebitoACobrar("");
					atualizarGuiaDevolucaoActionForm.setValorDebitoACobrar("");
					atualizarGuiaDevolucaoActionForm.setValorDevolucao("");

					httpServletRequest.setAttribute("bloqueioDebitoACobrar",
							true);
					httpServletRequest.setAttribute("bloqueioGuia", true);
					httpServletRequest.setAttribute("bloqueioConta", true);
				}
			} else {
				atualizarGuiaDevolucaoActionForm.setReferenciaConta("");
				atualizarGuiaDevolucaoActionForm.setDescricaoConta("");
				atualizarGuiaDevolucaoActionForm.setIdGuiaPagamento("");
				atualizarGuiaDevolucaoActionForm.setDescricaoGuiaPagamento("");
				atualizarGuiaDevolucaoActionForm.setIdDebitoACobrar("");
				atualizarGuiaDevolucaoActionForm.setDescricaoDebitoACobrar("");
				atualizarGuiaDevolucaoActionForm.setValorDevolucao("");

				if (ordemServico == null
						|| ordemServico.getServicoTipo() == null
						|| ordemServico.getServicoTipo().getDebitoTipo() == null) {
					atualizarGuiaDevolucaoActionForm.setIdTipoDebito("");
					atualizarGuiaDevolucaoActionForm.setIdTipoDebitoHidden("");
					atualizarGuiaDevolucaoActionForm.setDescricaoTipoDebito("");
				}

				httpServletRequest.setAttribute("bloqueioLocalidade", true);
				httpServletRequest.setAttribute("bloqueioGuia", true);
				httpServletRequest.setAttribute("bloqueioConta", true);
				httpServletRequest.setAttribute("bloqueioDebitoTipo", true);
				httpServletRequest.setAttribute("bloqueioDebitoACobrar", true);

			}
		}

		return retorno;

	}

	private GuiaPagamento pesquisarGuiaPagamentoDigitada(String idImovel,
			String idCliente, String idGuiaPagamento) {

		Fachada fachada = Fachada.getInstancia();
		// Cria a vari�vel que vai armazenar a guia de pagamento pequisada
		GuiaPagamento guiaPagamentoDigitada = null;

		// Cria o filtro de guia de pagamento
		FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();

		// Caso o usu�rio tenha informado o im�vel, seta o c�digo do im�vel no
		// filtro
		// Caso contr�rio, o usu�rio tenha informado o cliente seta o c�digo do
		// cliente no filtro
		// Caso o usu�rio n�o tenha informado nem o im�vel nem o cliente levanta
		// uma exce��o
		// para o usu�rio informando que tem de informar o cliente ou o im�vel
		if ((idImovel == null || idImovel.trim().equalsIgnoreCase(""))
				&& (idCliente == null || idCliente.trim().equalsIgnoreCase(""))) {
			throw new ActionServletException("atencao.naoinformado", null,
					"Im�vel ou Cliente");
		}

		// Pesquisa a guia de pagamento de acordo com os par�metros no filtro
		filtroGuiaPagamento.adicionarParametro(new ParametroSimples(
				FiltroGuiaPagamento.ID, idGuiaPagamento));

		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoGuiaPagamento = fachada.pesquisar(
				filtroGuiaPagamento, GuiaPagamento.class.getName());

		// Caso exista a guia de pagamento para o im�vel ou o cliente informado
		// cadastrado no sistema
		// Retorna para o usu�rio a guia de pagamento retornada pela pesquisa
		// Caso contr�rio retorna um objeto nulo
		if (colecaoGuiaPagamento != null && !colecaoGuiaPagamento.isEmpty()) {
			guiaPagamentoDigitada = (GuiaPagamento) Util
					.retonarObjetoDeColecao(colecaoGuiaPagamento);
		}

		// Retorna a guia de pagamento encontrada ou nulo se n�o existir aa guia
		// de pagamento
		return guiaPagamentoDigitada;
	}

	private DebitoACobrar pesquisarDebitoACobrarDigitado(String idImovel,
			String idDebitoACobrar) {

		Fachada fachada = Fachada.getInstancia();

		// Cria a vari�vel que vai armazenar o d�bito a cobrar pesquisado
		DebitoACobrar debitoACobrarDigitado = null;

		// Cria o filtro de d�bito a cobrar e seta todos os par�metros para
		// pesquisar o d�bito a cobrar do im�vel
		FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();
		filtroDebitoACobrar.adicionarParametro(new ParametroSimples(
				FiltroDebitoACobrar.ID, idDebitoACobrar));
		filtroDebitoACobrar
				.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroDebitoACobrar
				.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("imovel");
		Collection colecaoDebitoACobrar = fachada.pesquisar(
				filtroDebitoACobrar, DebitoACobrar.class.getName());

		// Caso exista o d�bito a cobrar para o im�vel informado cadastrado no
		// sistema
		// Retorna para o usu�rio o d�bito a cobrar retornado pela pesquisa
		// Caso contr�rio retorna um objeto nulo
		if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
			debitoACobrarDigitado = (DebitoACobrar) Util
					.retonarObjetoDeColecao(colecaoDebitoACobrar);
		}

		// Retorna o d�bito a cobrar encontrado ou nulo se n�o existir o d�bito
		// a cobrar
		return debitoACobrarDigitado;
	}
}
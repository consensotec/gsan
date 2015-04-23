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
package gsan.gui.cobranca.spcserasa;

import gsan.cadastro.cliente.ClienteTipo;
import gsan.cobranca.NegativacaoCriterio;
import gsan.cobranca.bean.ParametrosComandoNegativacaoHelper;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.gui.StatusWizard;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0652] Manter Comando de Negativação<br>
 * [SB0004] Exibir Atualizar Comando de Negativação por Guia de Pagamento<br>
 * 
 * @author André Miranda
 * @date 23/03/2015
 */
public class ExibirAtualizarComandoNegativacaoPorGuiaPagamentoAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param request
	 *            Descrição do parâmetro
	 * @param response
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping
				.findForward("atualizarComandoNegativacaoPorGuiaPagamento");
		HttpSession sessao = request.getSession(false);
		Fachada fachada = Fachada.getInstancia();

		String idComandoNegativacao = null;
		StatusWizard statusWizard = null;

		if (request.getParameter("desfazer") != null) {
			statusWizard = (StatusWizard) sessao.getAttribute("statusWizard");
			idComandoNegativacao = statusWizard.getId();
		} else {
			if (request.getParameter("idComandoNegativacao") != null) {
				idComandoNegativacao = (String) request.getParameter("idComandoNegativacao");
			} else if (request.getAttribute("idComandoNegativacao") != null) {
				idComandoNegativacao = (String) request.getAttribute("idComandoNegativacao");
			}

			// Verifica se chegou no atualizar cliente atraves da tela de
			// filtrar devido a um unico registro
			// ou atraves da lista de imoveis no manter cliente
			if (sessao.getAttribute("atualizar") != null || request.getParameter("sucesso") != null) {
				statusWizard = new StatusWizard(
						"atualizarComandoNegativacaoPorGuiaPagamentoWizardAction",
						"atualizarComandoNegativacaoPorGuiaPagamentoAction",
						"cancelarAtualizarComandoNegativacaoAction",
						"exibirFiltrarComandoNegativacaoPorCriterioAction",
						"exibirAtualizarComandoNegativacaoPorGuiaPagamentoAction.do",
						idComandoNegativacao);
				sessao.removeAttribute("atualizar");
			} else {
				statusWizard = new StatusWizard(
						"atualizarComandoNegativacaoPorGuiaPagamentoWizardAction",
						"atualizarComandoNegativacaoPorGuiaPagamentoAction",
						"cancelarAtualizarComandoNegativacaoAction",
						"exibirManterComandoNegativacaoAction",
						"exibirAtualizarComandoNegativacaoPorGuiaPagamentoAction.do",
						idComandoNegativacao);
			}

			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							1, "DadosGeraisPrimeiraAbaA.gif", "DadosGeraisPrimeiraAbaD.gif",
							"exibirAtualizarComandoNegativacaoPorGuiaPagamentoDadosGeraisAction",
							"atualizarComandoNegativacaoPorGuiaPagamentoDadosGeraisAction"));

			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							2, "ClienteA.gif", "ClienteD.gif",
							"exibirAtualizarComandoNegativacaoPorGuiaPagamentoDadosClienteAction",
							"atualizarComandoNegativacaoPorGuiaPagamentoDadosClienteAction"));

			// manda o statusWizard para a sessao
			sessao.setAttribute("statusWizard", statusWizard);
		}

		// Obtém o action form
		AtualizarComandoNegativacaoPorCriterioActionForm form = (AtualizarComandoNegativacaoPorCriterioActionForm) actionForm;

		// Verifica se os objetos estão na sessão
		if (Util.verificarNaoVazio(idComandoNegativacao)) {
			ParametrosComandoNegativacaoHelper helper = fachada.pesquisarParametrosComandoNegativacao(Integer
					.parseInt(idComandoNegativacao));

			form.reset();
			// Removendo todos os objetos da sessão
			sessao.removeAttribute("colecaoTipoCliente");

			form.setIdNegativacaoComando(idComandoNegativacao);
			form.setUltimaAlteracaoNegComando(helper.getUltimaAlteracaoNegComando());
			form.setIdNegativacaoCriterio(helper.getIdNagativacaoCriterio().toString());

			// Dados Gerais
			setDadosGerais(sessao, form, helper, fachada);

			// Dados Débitos
			setDadosCliente(form, helper);
		}

		sessao.removeAttribute("idComandoNegativacao");

		// Manda o form para a primeira página do processo para que depois
		// ela seja colocada na sessão
		request.setAttribute("AtualizarComandoNegativacaoPorCriterioActionForm", form);

		return retorno;
	}

	private void setDadosGerais(HttpSession sessao, AtualizarComandoNegativacaoPorCriterioActionForm form,
			ParametrosComandoNegativacaoHelper helper, Fachada fachada) {
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Negativador
		if (helper.getIdNegativador() != null) {
			form.setNegativadorId(helper.getIdNegativador().toString());
			form.setNomeNegativador(helper.getNegativador());
		}

		// Titulo Comando
		if (helper.getTituloComando() != null) {
			form.setTitulo(helper.getTituloComando());
		}

		// Descricao da Solicitacao
		if (helper.getDescricaoSolicitacao() != null) {
			form.setSolicitacao(helper.getDescricaoSolicitacao());
		}

		// Simular Negativacao
		if (helper.getSimularNegativacao() != null) {
			if (helper.getSimularNegativacao().equals(ConstantesSistema.SIM)) {
				form.setSimular(ConstantesSistema.SIM.toString());
			} else {
				form.setSimular(ConstantesSistema.NAO.toString());
			}
		}

		if (helper.getIdComandoNegativacaoSimulado() != null) {
			Integer idComandoNegativacao = helper.getIdComandoNegativacaoSimulado();

			NegativacaoCriterio negativacaoCriterio = fachada.pesquisarComandoNegativacaoSimulado(idComandoNegativacao);

			form.setExecutarSimulacao(ConstantesSistema.SIM.toString());
			form.setIdComandoSimulado(idComandoNegativacao.toString());
			form.setDescricaoComandoSimulado(negativacaoCriterio.getDescricaoTitulo());
		} else {
			form.setExecutarSimulacao(ConstantesSistema.NAO.toString());
		}

		// Data Prevista p/ Execucao
		if (helper.getDataExecucao() != null) {
			form.setDataPrevista(Util.formatarData(helper.getDataExecucao()));
		}

		// Usuario Responsavel
		if (helper.getIdUsuario() != null) {
			form.setUsuario(helper.getIdUsuario().toString());
			form.setNomeUsuario(helper.getUsuarioResponsavel());
		}

		// Quantidade Maxima Inclusoes
		if (helper.getQtdMaxInclusoes() != null) {
			form.setQtdMaximaInclusao(helper.getQtdMaxInclusoes().toString());
		}

		// [FS0020] Verificar permissão especial de alteração de valor
		boolean alterarSoCPFCNPJValidos = Fachada.getInstancia().verificarPermissaoEspecial(
				PermissaoEspecial.ALTERAR_SO_CPF_CNPJ_VALIDOS, usuarioLogado);

		sessao.setAttribute("alterarSoCPFCNPJValidos", alterarSoCPFCNPJValidos);
		if (form.getIndicadorCpfCnpjValido() == null && !alterarSoCPFCNPJValidos) {
			form.setIndicadorCpfCnpjValido(ConstantesSistema.CONFIRMADA);
		} else {
			form.setIndicadorCpfCnpjValido(helper.getIndicadorCpfCnpjValido().toString());
		}
	}

	private void setDadosCliente(AtualizarComandoNegativacaoPorCriterioActionForm form,
			ParametrosComandoNegativacaoHelper helper) {
		// Periodo de referencia Inicial
		if (helper.getReferenciaInicial() != null) {
			form.setReferenciaInicial(Util.formatarAnoMesParaMesAno(helper.getReferenciaInicial()));
		}

		// Periodo de referencia Final
		if (helper.getReferenciaFinal() != null) {
			form.setReferenciaFinal(Util.formatarAnoMesParaMesAno(helper.getReferenciaFinal()));
		}

		// Periodo de Vencimento Debito inicial
		if (helper.getVencimentoInicial() != null) {
			form.setDataVencimentoInicial(Util.formatarData(helper.getVencimentoInicial()));
		}

		// Periodo de Vencimento Debito Final
		if (helper.getVencimentoFinal() != null) {
			form.setDataVencimentoFinal(Util.formatarData(helper.getVencimentoFinal()));
		}

		// Valor Minimo do Debito
		if (helper.getValoMinimoDebito() != null) {
			form.setValorDebitoInicial(Util.formatarMoedaReal(helper.getValoMinimoDebito()));
		}

		// Valor Maximo do Debito
		if (helper.getValoMaximoDebito() != null) {
			form.setValorDebitoFinal(Util.formatarMoedaReal(helper.getValoMaximoDebito()));
		}

		// Id Cliente
		if (helper.getIdCliente() != null) {
			form.setIdCliente(helper.getIdCliente().toString());
		}

		// Nome Cliente
		if (helper.getNomeCliente() != null) {
			form.setDescricaoCliente(helper.getNomeCliente());
		}

		// Lista de Tipo de Cliente
		if (helper.getColecaoTipoCliente() == null) {
			form.setTipoCliente(null);
		} else {
			String[] idsTipoCliente = new String[helper.getColecaoTipoCliente().size()];
			Iterator colecaoTipoCliente = helper.getColecaoTipoCliente().iterator();
			int qtdTipoCliente = 0;
			while (colecaoTipoCliente.hasNext()) {
				ClienteTipo clienteTipo = (ClienteTipo) colecaoTipoCliente.next();
				idsTipoCliente[qtdTipoCliente] = clienteTipo.getId().toString();
				qtdTipoCliente++;
			}
			form.setTipoCliente(idsTipoCliente);
		}
	}
}

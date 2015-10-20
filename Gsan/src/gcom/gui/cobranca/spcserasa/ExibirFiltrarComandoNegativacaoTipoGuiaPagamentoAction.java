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
package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
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
 * Action que inicializa o pré-processamento da pagina de exibição do filtro de
 * pesquisa de comandos de negativação com o tipo de comando "por guia de pagamento"
 * selecionado
 * 
 * @author: Fábio Aguiar
 * @date: 17/03/2015
 */
public class ExibirFiltrarComandoNegativacaoTipoGuiaPagamentoAction extends
		GcomAction {

	/**
	 * Método de execução principal do action
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

 		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarComandoNegativacaoTipoGuiaPagamento");
		FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm form = (FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = getSessao(httpServletRequest);

		form.setLocalidadeInicialIncompativel("false");


		if (httpServletRequest.getParameter("primeiraVez") != null) {
			limparForm(form);
		}

		// carregar coleção de negativadores para select do form de filtro
		Collection colecaoNegativador = (Collection) sessao
				.getAttribute("colecaoNegativador");

		if (colecaoNegativador == null) {
			FiltroNegativador filtroNegativador = new FiltroNegativador();
			filtroNegativador.setCampoOrderBy(FiltroNegativador.CLIENTE);
			filtroNegativador
					.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroNegativador.adicionarParametro(new ParametroSimples(
					FiltroNegativador.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroNegativador.setConsultaSemLimites(true);

			colecaoNegativador = fachada.pesquisar(filtroNegativador,
					Negativador.class.getName());

			if (colecaoNegativador == null || colecaoNegativador.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"NEGATIVADOR");
			} else {
				sessao.setAttribute("colecaoNegativador", colecaoNegativador);
			}
		}

		if (form.getTipoPesquisaTitulo() == null
				|| form.getTipoPesquisaTitulo().equals("")) {
			form.setTipoPesquisaTitulo(ConstantesSistema.TIPO_PESQUISA_INICIAL
					.toString());
		}

		if (form.getComandoSimulado() == null
				|| form.getComandoSimulado().equals("")) {
			form.setComandoSimulado(ConstantesSistema.COMANDO_SIMULADO_TODOS);
		}

		// carrega cliente
		String codigoClienteDigitado = form.getCodigoCliente();
		// verifica se o codigo do cliente foi digitado
		if (codigoClienteDigitado != null
				&& !codigoClienteDigitado.trim().equals("")
				&& Integer.parseInt(codigoClienteDigitado) > 0) {

			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, codigoClienteDigitado));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				// O Cliente foi encontrado
				if (((Cliente) ((List) clienteEncontrado).get(0))
						.getIndicadorUso().equals(
								ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.cliente.inativo",
							null,
							""
									+ ((Cliente) ((List) clienteEncontrado)
											.get(0)).getId());
				}

				form.setCodigoCliente(((Cliente) ((List) clienteEncontrado)
						.get(0)).getId().toString());
				form.setNomeCliente(((Cliente) ((List) clienteEncontrado)
						.get(0)).getNome());
			} else {
				httpServletRequest.setAttribute("corCliente", "exception");
				form.setNomeCliente(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);
				form.setCodigoCliente("");
			}
		}

		// carregar coleção de tipo de relação para select do form de filtro
		Collection colecaoTipoRelacao = (Collection) sessao
				.getAttribute("colecaoTipoRelacao");

		if (colecaoTipoRelacao == null) {

			FiltroClienteRelacaoTipo filtro = new FiltroClienteRelacaoTipo();
			filtro.setConsultaSemLimites(true);

			colecaoTipoRelacao = fachada.pesquisar(filtro,
					ClienteRelacaoTipo.class.getName());

			if (colecaoTipoRelacao == null || colecaoTipoRelacao.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"CLIENTE_RELACAO_TIPO");
			} else {
				sessao.setAttribute("colecaoTipoRelacao", colecaoTipoRelacao);
			}
		}

		// carregar coleção de grupos de cobrança para select do form de filtro
		Collection colecaoGrupoCobranca = (Collection) sessao
				.getAttribute("colecaoGrupoCobranca");

		if (colecaoGrupoCobranca == null) {

			FiltroCobrancaGrupo filtro = new FiltroCobrancaGrupo();
			filtro.setConsultaSemLimites(true);
			filtro.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);

			colecaoGrupoCobranca = fachada.pesquisar(filtro,
					CobrancaGrupo.class.getName());

			if (colecaoGrupoCobranca == null || colecaoGrupoCobranca.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"GRUPO_COBRANCA");
			} else {
				sessao.setAttribute("colecaoGrupoCobranca",
						colecaoGrupoCobranca);
			}
		}

		// carregar coleção de gerência regional para select do form de filtro
		Collection colecaoGerenciaRegional = (Collection) sessao
				.getAttribute("colecaoGerenciaRegional");

		if (colecaoGerenciaRegional == null) {

			FiltroGerenciaRegional filtro = new FiltroGerenciaRegional();
			filtro.setConsultaSemLimites(true);
			filtro.setCampoOrderBy(FiltroGerenciaRegional.NOME);

			colecaoGerenciaRegional = fachada.pesquisar(filtro,
					GerenciaRegional.class.getName());

			if (colecaoGerenciaRegional == null
					|| colecaoGerenciaRegional.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"GERENCIA_REGIONAL");
			} else {
				sessao.setAttribute("colecaoGerenciaRegional",
						colecaoGerenciaRegional);
			}
		}

		// carregar coleção de unidade de negócio para select do form de filtro
		Collection colecaoUnidadeNegocio = (Collection) sessao
				.getAttribute("colecaoUnidadeNegocio");

		if (colecaoUnidadeNegocio == null) {

			FiltroUnidadeNegocio filtro = new FiltroUnidadeNegocio();
			filtro.setConsultaSemLimites(true);
			filtro.setCampoOrderBy(FiltroUnidadeNegocio.NOME);

			colecaoUnidadeNegocio = fachada.pesquisar(filtro,
					UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocio == null
					|| colecaoUnidadeNegocio.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"UNIDADE_NEGOCIO");
			} else {
				sessao.setAttribute("colecaoUnidadeNegocio",
						colecaoUnidadeNegocio);
			}
		}


		if (form.getSituacaoComando() == null
				|| form.getSituacaoComando().equals("")) {
			form.setSituacaoComando(ConstantesSistema.TODOS.toString());
		}

		if (form.getTipoPesquisaTitulo() == null
				|| form.getTipoPesquisaTitulo().equals("")) {
			form.setTipoPesquisaTitulo(ConstantesSistema.TIPO_PESQUISA_INICIAL
					.toString());
		}

		if (form.getComandoSimulado() == null
				|| form.getComandoSimulado().equals("")) {
			form.setComandoSimulado(ConstantesSistema.TODOS.toString());
		}

		if (form.getIndicadorContaNomeCliente() == null
				|| form.getIndicadorContaNomeCliente().equals("")) {
			form.setIndicadorContaNomeCliente(ConstantesSistema.TODOS.toString());
		}
		
		return retorno;
	}

	public void limparForm(FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm form) {

		form.setIdNegativador("");
		form.setTitulo("");
		form.setTipoPesquisaTitulo("");
		form.setComandoSimulado("");
		form.setNomeCliente("");
		form.setCodigoCliente("");
		form.setGeracaoComandoDataInicial("");
		form.setGeracaoComandoDataFinal("");
		form.setExecucaoComandoDataInicial("");
		form.setExecucaoComandoDataFinal("");
		form.setReferenciaDebitoDataInicial("");
		form.setReferenciaDebitoDataFinal("");
		form.setVencimentoDebitoDataInicial("");
		form.setVencimentoDebitoDataFinal("");
		form.setValorDebitoInicial("");
		form.setValorDebitoFinal("");
		form.setSituacaoComando("");
		form.setOkCliente("");
		form.setLocalidadeInicialIncompativel("");
		form.setIndicadorContaNomeCliente(ConstantesSistema.TODOS.toString());

	}
}

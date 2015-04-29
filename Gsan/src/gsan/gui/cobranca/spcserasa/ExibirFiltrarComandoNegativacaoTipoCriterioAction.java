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
package gsan.gui.cobranca.spcserasa;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.cliente.FiltroClienteRelacaoTipo;
import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cobranca.CobrancaGrupo;
import gsan.cobranca.FiltroCobrancaGrupo;
import gsan.cobranca.Negativador;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.spcserasa.FiltroNegativador;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que inicializa o pr�-processamento da pagina de exibi��o do filtro de
 * pesquisa de comandos de negativa��o com o tipo de comando "por crit�rio"
 * selecionado
 * 
 * @author: Thiago Vieira
 * @date: 10/01/2007
 */
public class ExibirFiltrarComandoNegativacaoTipoCriterioAction extends
		GcomAction {

	/**
	 * M�todo de execu��o principal do action
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
				.findForward("exibirFiltrarComandoNegativacaoTipoCriterio");
		FiltrarComandoNegativacaoTipoCriterioActionForm form = (FiltrarComandoNegativacaoTipoCriterioActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = getSessao(httpServletRequest);

		form.setLocalidadeInicialIncompativel("false");

		// form.setIdGrupoCobranca("-1");
		// form.setIdGerenciaRegional("-1");
		// form.setIdUnidadeNegocio("-1");
		// form.setIdEloPolo("-1");

		if (httpServletRequest.getParameter("primeiraVez") != null) {
			limparForm(form);
		}

		// carregar cole��o de negativadores para select do form de filtro
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

		// carregar cole��o de tipo de rela��o para select do form de filtro
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

		// carregar cole��o de grupos de cobran�a para select do form de filtro
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

		// carregar cole��o de ger�ncia regional para select do form de filtro
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

		// carregar cole��o de unidade de neg�cio para select do form de filtro
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

		// carregar cole��o de elo polo para select do form de filtro
		Collection colecaoEloPolo = new ArrayList();
		Collection colecaoEloPoloFinal = (Collection) sessao
				.getAttribute("colecaoEloPoloFinal");
		if (colecaoEloPoloFinal == null) {

			colecaoEloPoloFinal = new ArrayList();
			FiltroLocalidade filtro = new FiltroLocalidade();
			filtro.setConsultaSemLimites(true);
			filtro.setCampoOrderBy(FiltroLocalidade.DESCRICAO);
			filtro.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtro.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			colecaoEloPolo = fachada.pesquisar(filtro,
					Localidade.class.getName());

			if (colecaoEloPolo == null || colecaoEloPolo.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"ELO_POLO");
			} else {
				Iterator i = colecaoEloPolo.iterator();
				while (i.hasNext()) {
					Localidade localidade = (Localidade) i.next();
					// LOCA_ID OCORRENDO NA COLUNA LOCA_CDELO
					if (localidade.getLocalidade().getId() == localidade
							.getId()) {
						colecaoEloPoloFinal.add(localidade);
					}
				}
				if (colecaoEloPoloFinal == null
						|| colecaoEloPoloFinal.isEmpty()) {
					throw new ActionServletException(
							"atencao.entidade_sem_dados_para_selecao", null,
							"ELO_POLO");
				} else {
					sessao.setAttribute("colecaoEloPoloFinal",
							colecaoEloPoloFinal);
				}
			}
		}

		// carrega localidade inicial
		String codigoLocalidadeInicial = form.getCodigoLocalidadeInicial();
		// verifica se o codigo do cliente foi digitado
		if (codigoLocalidadeInicial != null
				&& !codigoLocalidadeInicial.trim().equals("")
				&& Integer.parseInt(codigoLocalidadeInicial) > 0) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, codigoLocalidadeInicial));
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection localidadeInicialEncontrada = fachada.pesquisar(
					filtroLocalidade, Localidade.class.getName());

			if (localidadeInicialEncontrada != null
					&& !localidadeInicialEncontrada.isEmpty()) {
				// A localidade foi encontrada
				if (((Localidade) ((List) localidadeInicialEncontrada).get(0))
						.getIndicadorUso().equals(
								ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException(
							"atencao.localidade_inativa",
							null,
							""
									+ ((Localidade) ((List) localidadeInicialEncontrada)
											.get(0)).getId());
				}

				form.setCodigoLocalidadeInicial(((Localidade) ((List) localidadeInicialEncontrada)
						.get(0)).getId().toString());
				form.setDescricaoLocalidadeInicial(((Localidade) ((List) localidadeInicialEncontrada)
						.get(0)).getDescricao());
			} else {
				httpServletRequest.setAttribute("corLocalidadeInicial",
						"exception");
				form.setDescricaoLocalidadeInicial(ConstantesSistema.CODIGO_LOCALIDADE_INEXISTENTE);
				form.setCodigoLocalidadeInicial("");
			}
		}

		// carrega localidade final
		String codigoLocalidadeFinal = form.getCodigoLocalidadeFinal();
		// verifica se o codigo do cliente foi digitado
		if (codigoLocalidadeFinal != null
				&& !codigoLocalidadeFinal.trim().equals("")
				&& Integer.parseInt(codigoLocalidadeFinal) > 0) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, codigoLocalidadeFinal));
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection localidadeFinalEncontrada = fachada.pesquisar(
					filtroLocalidade, Localidade.class.getName());

			if (localidadeFinalEncontrada != null
					&& !localidadeFinalEncontrada.isEmpty()) {
				// A localidade foi encontrada
				if (((Localidade) ((List) localidadeFinalEncontrada).get(0))
						.getIndicadorUso().equals(
								ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException(
							"atencao.localidade_inativa",
							null,
							""
									+ ((Localidade) ((List) localidadeFinalEncontrada)
											.get(0)).getId());
				}

				form.setCodigoLocalidadeFinal(((Localidade) ((List) localidadeFinalEncontrada)
						.get(0)).getId().toString());
				form.setDescricaoLocalidadeFinal(((Localidade) ((List) localidadeFinalEncontrada)
						.get(0)).getDescricao());
			} else {
				httpServletRequest.setAttribute("corLocalidadeFinal",
						"exception");
				form.setDescricaoLocalidadeFinal(ConstantesSistema.CODIGO_LOCALIDADE_INEXISTENTE);
				form.setCodigoLocalidadeFinal("");
			}
		}

		// carrega setor comercial inicial
		String codigoSetorComercialInicial = form
				.getCodigoSetorComercialInicial();
		// verifica se o codigo do setor comercial inicial foi digitado
		if (codigoSetorComercialInicial != null
				&& !codigoSetorComercialInicial.trim().equals("")
				&& Integer.parseInt(codigoSetorComercialInicial) > 0) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
					codigoSetorComercialInicial));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.LOCALIDADE, codigoLocalidadeInicial));

			Collection setorComercialInicialEncontrada = fachada.pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());

			if (setorComercialInicialEncontrada != null
					&& !setorComercialInicialEncontrada.isEmpty()) {
				// o Setor Comercial foi encontrado
				if (((SetorComercial) ((List) setorComercialInicialEncontrada)
						.get(0)).getIndicadorUso().equals(
						ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException(
							"atencao.setor_comercial_inativo",
							null,
							""
									+ ((SetorComercial) ((List) setorComercialInicialEncontrada)
											.get(0)).getId());
				}

				form.setCodigoSetorComercialInicial(new Integer(
						((SetorComercial) ((List) setorComercialInicialEncontrada)
								.get(0)).getCodigo()).toString());
				form.setDescricaoSetorComercialInicial(((SetorComercial) ((List) setorComercialInicialEncontrada)
						.get(0)).getDescricao());

			} else {
				httpServletRequest.setAttribute("corSetorComercialInicial",
						"exception");
				form.setDescricaoSetorComercialInicial(ConstantesSistema.CODIGO_SETOR_COMERCIAL_INEXISTENTE);
				form.setCodigoSetorComercialInicial("");
			}
		}

		// carrega setor comercial Final
		String codigoSetorComercialFinal = form.getCodigoSetorComercialFinal();
		// verifica se o codigo do setor comercial Final foi digitado
		if (codigoSetorComercialFinal != null
				&& !codigoSetorComercialFinal.trim().equals("")
				&& Integer.parseInt(codigoSetorComercialFinal) > 0) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
					codigoSetorComercialFinal));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.LOCALIDADE, codigoLocalidadeInicial));

			Collection setorComercialFinalEncontrada = fachada.pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());

			if (setorComercialFinalEncontrada != null
					&& !setorComercialFinalEncontrada.isEmpty()) {
				// o Setor Comercial foi encontrado
				if (((SetorComercial) ((List) setorComercialFinalEncontrada)
						.get(0)).getIndicadorUso().equals(
						ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException(
							"atencao.setor_comercial_inativo",
							null,
							""
									+ ((SetorComercial) ((List) setorComercialFinalEncontrada)
											.get(0)).getId());
				}

				form.setCodigoSetorComercialFinal(new Integer(
						((SetorComercial) ((List) setorComercialFinalEncontrada)
								.get(0)).getCodigo()).toString());
				form.setDescricaoSetorComercialFinal(((SetorComercial) ((List) setorComercialFinalEncontrada)
						.get(0)).getDescricao());
			} else {
				httpServletRequest.setAttribute("corSetorComercialFinal",
						"exception");
				form.setDescricaoSetorComercialFinal(ConstantesSistema.CODIGO_SETOR_COMERCIAL_INEXISTENTE);
				form.setCodigoSetorComercialFinal("");
			}
		}

		if (form.getCartaParcelamentoAtraso() == null
				|| form.getCartaParcelamentoAtraso().equals("")) {
			form.setCartaParcelamentoAtraso(ConstantesSistema.NAO.toString());
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

	public void limparForm(FiltrarComandoNegativacaoTipoCriterioActionForm form) {

		form.setIdNegativador("");
		form.setTitulo("");
		form.setTipoPesquisaTitulo("");
		form.setComandoSimulado("");
		form.setNomeCliente("");
		form.setCodigoCliente("");

		form.setIdGrupoCobranca("-1");
		form.setIdGerenciaRegional("-1");
		form.setIdUnidadeNegocio("-1");
		form.setIdEloPolo("-1");
		form.setIdTipoRelacao("");
		// form.setIdGrupoCobranca("");
		// form.setIdGerenciaRegional("");
		// form.setIdUnidadeNegocio("");
		// form.setIdEloPolo("");
		form.setCodigoLocalidadeInicial("");
		form.setDescricaoLocalidadeInicial("");
		form.setCodigoSetorComercialInicial("");
		form.setDescricaoSetorComercialInicial("");
		form.setCodigoLocalidadeFinal("");
		form.setDescricaoLocalidadeFinal("");
		form.setCodigoSetorComercialFinal("");
		form.setDescricaoSetorComercialFinal("");
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
		form.setNumeroContasInicial("");
		form.setNumeroContasFinal("");
		form.setCartaParcelamentoAtraso("");
		form.setSituacaoComando("");

		form.setOkCliente("");
		form.setLocalidadeInicialIncompativel("");
		form.setIndicadorContaNomeCliente(ConstantesSistema.TODOS.toString());

	}
}

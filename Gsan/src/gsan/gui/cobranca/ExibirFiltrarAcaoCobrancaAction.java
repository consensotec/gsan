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
 * Anderson Italo Felinto de Lima
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
package gsan.gui.cobranca;

import java.util.Collection;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.atendimentopublico.ordemservico.FiltroServicoTipo;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.cobranca.CobrancaAcao;
import gsan.cobranca.CobrancaCriterio;
import gsan.cobranca.DocumentoTipo;
import gsan.cobranca.FiltroCobrancaAcao;
import gsan.cobranca.FiltroCobrancaCriterio;
import gsan.cobranca.FiltroDocumentoTipo;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para filtrar a a��o de cobran�a
 * 
 * @author S�vio Luiz
 * @date 10/10/2007
 */
public class ExibirFiltrarAcaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarAcaoCobranca");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// caso venha da tela atualizar sem ter passado pelo filtro
		// (no caso vim da tela de sucesso do inserir)
		if (sessao.getAttribute("indicadorAtualizar") == null) {
			sessao.setAttribute("indicadorAtualizar", "1");
		}

		AcaoCobrancaFiltrarActionForm acaoCobrancaFiltrarActionForm = (AcaoCobrancaFiltrarActionForm) actionForm;
		if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").equals("")) {
			acaoCobrancaFiltrarActionForm.setDescricaoAcao("");
			acaoCobrancaFiltrarActionForm.setDescricaoCobrancaCriterio("");
			acaoCobrancaFiltrarActionForm.setDescricaoServicoTipo("");
			acaoCobrancaFiltrarActionForm.setIcAcaoObrigatoria("");
			acaoCobrancaFiltrarActionForm.setIcAcrescimosImpontualidade("");
			acaoCobrancaFiltrarActionForm.setIcCompoeCronograma("");
			acaoCobrancaFiltrarActionForm.setIcDebitosACobrar("");
			acaoCobrancaFiltrarActionForm.setIcEmitirBoletimCadastro("");
			acaoCobrancaFiltrarActionForm.setIcGeraTaxa("");
			acaoCobrancaFiltrarActionForm.setIcImoveisSemDebitos("");
			acaoCobrancaFiltrarActionForm.setIcRepetidaCiclo("");
			acaoCobrancaFiltrarActionForm.setIcSuspensaoAbastecimento("");
			acaoCobrancaFiltrarActionForm.setIdAcaoPredecessora("");
			acaoCobrancaFiltrarActionForm.setIdCobrancaCriterio("");
			acaoCobrancaFiltrarActionForm.setIdServicoTipo("");
			acaoCobrancaFiltrarActionForm.setIdSituacaoLigacaoAgua("");
			acaoCobrancaFiltrarActionForm.setIdSituacaoLigacaoEsgoto("");
			acaoCobrancaFiltrarActionForm.setIdTipoDocumentoGerado("");
			acaoCobrancaFiltrarActionForm.setNumeroDiasEntreAcoes("");
			acaoCobrancaFiltrarActionForm.setNumeroDiasValidade("");
			acaoCobrancaFiltrarActionForm.setOrdemCronograma("");
			acaoCobrancaFiltrarActionForm.setIcMetasCronograma("");
			acaoCobrancaFiltrarActionForm.setIcOrdenamentoCronograma("");
			acaoCobrancaFiltrarActionForm.setIcOrdenamentoEventual("");
			acaoCobrancaFiltrarActionForm.setIcDebitoInterfereAcao("");
			acaoCobrancaFiltrarActionForm.setNumeroDiasRemuneracaoTerceiro("");
			sessao.setAttribute("indicadorAtualizar", "1");
			acaoCobrancaFiltrarActionForm.setIcUso("3");
			acaoCobrancaFiltrarActionForm.setIcNotasPromissoria("");
			acaoCobrancaFiltrarActionForm.setIcCreditosARealizar("");
			acaoCobrancaFiltrarActionForm.setIcOrdenarMaiorValor("");
			acaoCobrancaFiltrarActionForm.setIcValidarItem("");
			acaoCobrancaFiltrarActionForm.setIndicadorMensagemSMS("");
			acaoCobrancaFiltrarActionForm.setIndicadorMensagemEmail("");
			acaoCobrancaFiltrarActionForm.setIcEfetuarAcaoCpfCnpjValido("");
			// faz as pesquisas obrigat�rias
			pesquisasObrigatorias(fachada, sessao);

		}

		// pesquisa os dados do enter
		pesquisarEnter(acaoCobrancaFiltrarActionForm, httpServletRequest,
				fachada);

		return retorno;
	}

	private void pesquisarEnter(
			AcaoCobrancaFiltrarActionForm acaoCobrancaFiltrarActionForm,
			HttpServletRequest httpServletRequest, Fachada fachada) {

		// pesquisa enter de crit�rio de cobran�a
		if (acaoCobrancaFiltrarActionForm.getIdCobrancaCriterio() != null
				&& !acaoCobrancaFiltrarActionForm.getIdCobrancaCriterio()
						.equals("")
				&& (acaoCobrancaFiltrarActionForm
						.getDescricaoCobrancaCriterio() == null || acaoCobrancaFiltrarActionForm
						.getDescricaoCobrancaCriterio().equals(""))) {

			FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
			try {
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.ID, new Integer(
								acaoCobrancaFiltrarActionForm
										.getIdCobrancaCriterio())));
			} catch (NumberFormatException ex) {
				throw new ActionServletException(
						"atencao.campo_texto.numero_obrigatorio", null,
						"Crit�rio de Cobran�a");
			}
			filtroCobrancaCriterio
					.setCampoOrderBy(FiltroCobrancaCriterio.DESCRICAO_COBRANCA_CRITERIO);
			Collection colecaoCobrancaCriterio = fachada.pesquisar(
					filtroCobrancaCriterio, CobrancaCriterio.class.getName());

			if (colecaoCobrancaCriterio != null
					&& !colecaoCobrancaCriterio.isEmpty()) {
				CobrancaCriterio cobrancaCriterio = (CobrancaCriterio) Util
						.retonarObjetoDeColecao(colecaoCobrancaCriterio);
				acaoCobrancaFiltrarActionForm
						.setDescricaoCobrancaCriterio(cobrancaCriterio
								.getDescricaoCobrancaCriterio());
			} else {
				acaoCobrancaFiltrarActionForm.setIdCobrancaCriterio("");
				acaoCobrancaFiltrarActionForm
						.setDescricaoCobrancaCriterio("COBRAN�A CRIT�RIO INEXISTENTE");
			}

		}

		// pesquisa enter de tipo de servi�o
		if (acaoCobrancaFiltrarActionForm.getIdServicoTipo() != null
				&& !acaoCobrancaFiltrarActionForm.getIdServicoTipo().equals("")
				&& (acaoCobrancaFiltrarActionForm.getDescricaoServicoTipo() == null || acaoCobrancaFiltrarActionForm
						.getDescricaoServicoTipo().equals(""))) {

			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			try {
				filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.ID, new Integer(
								acaoCobrancaFiltrarActionForm
										.getIdServicoTipo())));
			} catch (NumberFormatException ex) {
				throw new ActionServletException(
						"atencao.campo_texto.numero_obrigatorio", null,
						"Servi�o Tipo");
			}
			filtroServicoTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);
			Collection colecaoServicoTipo = fachada.pesquisar(
					filtroServicoTipo, ServicoTipo.class.getName());

			if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {
				ServicoTipo servicoTipo = (ServicoTipo) Util
						.retonarObjetoDeColecao(colecaoServicoTipo);
				acaoCobrancaFiltrarActionForm
						.setDescricaoServicoTipo(servicoTipo.getDescricao());
			} else {
				acaoCobrancaFiltrarActionForm.setIdServicoTipo("");
				acaoCobrancaFiltrarActionForm
						.setDescricaoServicoTipo("TIPO DE SERVI�O INEXISTENTE");
			}

		}
	}

	private void pesquisasObrigatorias(Fachada fachada, HttpSession sessao) {
		// pesquisa as a��es predecessoras
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAcao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoAcaoPredecessora = fachada.pesquisar(
				filtroCobrancaAcao, CobrancaAcao.class.getName());
		if (colecaoAcaoPredecessora == null
				|| colecaoAcaoPredecessora.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Cobran�a A��o");
		} else {
			sessao.setAttribute("colecaoAcaoPredecessora",
					colecaoAcaoPredecessora);
		}

		// pesquisa os tipos de documentos
		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
		filtroDocumentoTipo.setCampoOrderBy(FiltroDocumentoTipo.DESCRICAO);
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoDocumentoTipo = fachada.pesquisar(
				filtroDocumentoTipo, DocumentoTipo.class.getName());
		if (colecaoDocumentoTipo == null || colecaoDocumentoTipo.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Documento Tipo");
		} else {
			sessao.setAttribute("colecaoDocumentoTipo", colecaoDocumentoTipo);
		}

		// pesquisa as situa��es de liga��es de agua
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
		filtroDocumentoTipo
				.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAguaSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoLigacaoAguaSituacao = fachada.pesquisar(
				filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
		if (colecaoLigacaoAguaSituacao == null
				|| colecaoLigacaoAguaSituacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Liga��o Agua Situa��o");
		} else {
			sessao.setAttribute("colecaoLigacaoAguaSituacao",
					colecaoLigacaoAguaSituacao);
		}

		// pesquisa as situa��es de liga��es de agua
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
		filtroDocumentoTipo
				.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoLigacaoEsgotoSituacao = fachada.pesquisar(
				filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class
						.getName());
		if (colecaoLigacaoEsgotoSituacao == null
				|| colecaoLigacaoEsgotoSituacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Liga��o Esgoto Situa��o");
		} else {
			sessao.setAttribute("colecaoLigacaoEsgotoSituacao",
					colecaoLigacaoEsgotoSituacao);
		}
	}
}

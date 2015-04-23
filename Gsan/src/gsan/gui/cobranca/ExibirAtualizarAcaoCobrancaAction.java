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
 * Anderson Italo Felinto de Lima
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
package gsan.gui.cobranca;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.atendimentopublico.ordemservico.FiltroServicoTipo;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaAcao;
import gsan.cobranca.CobrancaCriterio;
import gsan.cobranca.ColunasTextoSMSEmail;
import gsan.cobranca.DocumentoTipo;
import gsan.cobranca.FiltroCobrancaAcao;
import gsan.cobranca.FiltroCobrancaCriterio;
import gsan.cobranca.FiltroColunasTextoSMSEmail;
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
 * Pre- processamento para atualiza o criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 06/11/2006
 */
public class ExibirAtualizarAcaoCobrancaAction extends GcomAction {

	public ActionForward unspecified(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarAcaoCobranca");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		AcaoCobrancaAtualizarActionForm acaoCobrancaAtualizarActionForm = (AcaoCobrancaAtualizarActionForm) actionForm;
		if ((httpServletRequest.getParameter("idRegistroAtualizar") != null && !httpServletRequest
				.getParameter("idRegistroAtualizar").equals(""))
				|| (sessao.getAttribute("cobrancaAcao") != null && !sessao
						.getAttribute("cobrancaAcao").equals(""))) {

			if (httpServletRequest.getParameter("objetoConsulta") == null) {

				CobrancaAcao cobrancaAcao = null;
				if (httpServletRequest.getParameter("idRegistroAtualizar") != null
						&& !httpServletRequest.getParameter("idRegistroAtualizar").equals("")) {

					String idAcaoCobranca = httpServletRequest.getParameter("idRegistroAtualizar");

					FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();

					filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.ID, idAcaoCobranca));
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterio");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessoraAlternativa");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.documentoTipo");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.ligacaoAguaSituacao");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.ligacaoEsgotoSituacao");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.cobrancaCriterio");					

					Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());

					if (colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()) {
						cobrancaAcao = (CobrancaAcao) Util.retonarObjetoDeColecao(colecaoCobrancaAcao);
					}
					
					// [RM11858] – Ajustes no voltar do "Atualizar Ação de Cobrança" após o "Inserir Ação de Cobrança"
					if (httpServletRequest.getParameter("retornoFiltrar") != null) {
						sessao.setAttribute("voltar", "filtrar");
					} else {
						sessao.setAttribute("voltar", "manter");
					}
				} else {
					cobrancaAcao = (CobrancaAcao) sessao.getAttribute("cobrancaAcao");
					sessao.setAttribute("voltar", "filtrar");
				}

				if (cobrancaAcao != null && !cobrancaAcao.equals("")) {

					acaoCobrancaAtualizarActionForm.setId(cobrancaAcao.getId().toString());

					if (cobrancaAcao.getDescricaoCobrancaAcao() != null) {
						acaoCobrancaAtualizarActionForm.setDescricaoAcao(cobrancaAcao.getDescricaoCobrancaAcao());
					} else {
						acaoCobrancaAtualizarActionForm.setDescricaoAcao("");
					}
				
					if (cobrancaAcao.getCobrancaCriterio() != null) {
						acaoCobrancaAtualizarActionForm
								.setIdCobrancaCriterio(""
										+ cobrancaAcao.getCobrancaCriterio()
												.getId());
						acaoCobrancaAtualizarActionForm
								.setDescricaoCobrancaCriterio(cobrancaAcao
										.getCobrancaCriterio()
										.getDescricaoCobrancaCriterio());
					} else {
						acaoCobrancaAtualizarActionForm
							.setIdCobrancaCriterio("");
					
						acaoCobrancaAtualizarActionForm
							.setDescricaoCobrancaCriterio("");
					}
				
					if (cobrancaAcao.getServicoTipo() != null) {
						acaoCobrancaAtualizarActionForm.setIdServicoTipo(""
								+ cobrancaAcao.getServicoTipo().getId());
						acaoCobrancaAtualizarActionForm
								.setDescricaoServicoTipo(cobrancaAcao
										.getServicoTipo().getDescricao());
					} else {
						acaoCobrancaAtualizarActionForm.setIdServicoTipo("");
						acaoCobrancaAtualizarActionForm.setDescricaoServicoTipo("");
					}
				
					if (cobrancaAcao.getIndicadorObrigatoriedade() != null) {
						acaoCobrancaAtualizarActionForm.setIcAcaoObrigatoria(""
								+ cobrancaAcao.getIndicadorObrigatoriedade());
					} else {
						acaoCobrancaAtualizarActionForm.setIcAcaoObrigatoria("");
					}
				
					if (cobrancaAcao.getIndicadorAcrescimoImpontualidade() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcAcrescimosImpontualidade(""
										+ cobrancaAcao
												.getIndicadorAcrescimoImpontualidade());
					} else {
						acaoCobrancaAtualizarActionForm.setIcAcrescimosImpontualidade("");
					}
				
					if (cobrancaAcao.getIndicadorCronograma() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcCompoeCronograma(""
										+ cobrancaAcao.getIndicadorCronograma());
					} else {
						acaoCobrancaAtualizarActionForm
						.setIcCompoeCronograma("");
					}
					
					if (cobrancaAcao.getIndicadorCobrancaDebACobrar() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcDebitosACobrar(""
										+ cobrancaAcao
												.getIndicadorCobrancaDebACobrar());
					} else {
						acaoCobrancaAtualizarActionForm.setIcDebitosACobrar("");
					}
					
					if (cobrancaAcao.getIndicadorBoletim() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcEmitirBoletimCadastro(""
										+ cobrancaAcao.getIndicadorBoletim());
					} else {
						acaoCobrancaAtualizarActionForm.setIcEmitirBoletimCadastro("");
					}
					
					if (cobrancaAcao.getIndicadorGeracaoTaxa() != null) {
						acaoCobrancaAtualizarActionForm.setIcGeraTaxa(""
								+ cobrancaAcao.getIndicadorGeracaoTaxa());
					} else {
						acaoCobrancaAtualizarActionForm.setIcGeraTaxa("");
					}
					
					if (cobrancaAcao.getIndicadorDebito() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcImoveisSemDebitos(""
										+ cobrancaAcao.getIndicadorDebito());
					} else {
						acaoCobrancaAtualizarActionForm.setIcImoveisSemDebitos("");
					}
					
					if (cobrancaAcao.getIndicadorRepeticao() != null) {
						acaoCobrancaAtualizarActionForm.setIcRepetidaCiclo(""
								+ cobrancaAcao.getIndicadorRepeticao());
					} else {
						acaoCobrancaAtualizarActionForm.setIcRepetidaCiclo("");
					}
					
					if (cobrancaAcao.getIndicadorSuspensaoAbastecimento() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcSuspensaoAbastecimento(""
										+ cobrancaAcao
												.getIndicadorSuspensaoAbastecimento());
					} else {
						acaoCobrancaAtualizarActionForm.setIcSuspensaoAbastecimento("");
					}
					
					if (cobrancaAcao.getCobrancaAcaoPredecessora() != null && 
							!cobrancaAcao.getCobrancaAcaoPredecessora().equals("")) {
						acaoCobrancaAtualizarActionForm
								.setIdAcaoPredecessora(""
										+ cobrancaAcao
												.getCobrancaAcaoPredecessora()
												.getId());
					} else {
						acaoCobrancaAtualizarActionForm.setIdAcaoPredecessora("");
					}
					
					
					//Verificar Ação Predecessor Alternativa
					acaoCobrancaAtualizarActionForm.setIdAcaoPredecessoraAlternativa("");
					if(cobrancaAcao.getCobrancaAcaoPredecessoraAlternativa() != null &&
							!cobrancaAcao.getCobrancaAcaoPredecessoraAlternativa().equals("")){
						acaoCobrancaAtualizarActionForm.setIdAcaoPredecessoraAlternativa(
							cobrancaAcao.getCobrancaAcaoPredecessoraAlternativa().getId().toString());
					}					
					

					if (cobrancaAcao.getLigacaoAguaSituacao() != null) {
						acaoCobrancaAtualizarActionForm
								.setIdSituacaoLigacaoAgua(""
										+ cobrancaAcao.getLigacaoAguaSituacao()
												.getId());
					} else {
						acaoCobrancaAtualizarActionForm.setIdSituacaoLigacaoAgua("");
					}
					
					if (cobrancaAcao.getLigacaoEsgotoSituacao() != null) {
						acaoCobrancaAtualizarActionForm
								.setIdSituacaoLigacaoEsgoto(""
										+ cobrancaAcao
												.getLigacaoEsgotoSituacao()
												.getId());
					} else {
						acaoCobrancaAtualizarActionForm.setIdSituacaoLigacaoEsgoto("");
					}
					
					if (cobrancaAcao.getDocumentoTipo() != null) {
						acaoCobrancaAtualizarActionForm
								.setIdTipoDocumentoGerado(""
										+ cobrancaAcao.getDocumentoTipo()
												.getId());
					} else {
						acaoCobrancaAtualizarActionForm.setIdTipoDocumentoGerado("");
					}
					
					if (cobrancaAcao.getNumeroDiasMinimoAcaoPrecedente() != null) {
						acaoCobrancaAtualizarActionForm
								.setNumeroDiasEntreAcoes(""
										+ cobrancaAcao
												.getNumeroDiasMinimoAcaoPrecedente());
					} else {
						acaoCobrancaAtualizarActionForm.setNumeroDiasEntreAcoes("");
					}
					
					if (cobrancaAcao.getNumeroDiasValidade() != null) {
						acaoCobrancaAtualizarActionForm
								.setNumeroDiasValidade(""
										+ cobrancaAcao.getNumeroDiasValidade());
					} else {
						acaoCobrancaAtualizarActionForm.setNumeroDiasValidade("");
					}
					
					if (cobrancaAcao.getOrdemRealizacao() != null && !cobrancaAcao.getOrdemRealizacao().equals(new Short("0"))) {
						acaoCobrancaAtualizarActionForm.setOrdemCronograma(""
								+ cobrancaAcao.getOrdemRealizacao());
					} else {
						acaoCobrancaAtualizarActionForm.setOrdemCronograma("");
					}
					
					if (cobrancaAcao.getNumeroDiasVencimento() != null) {
						acaoCobrancaAtualizarActionForm.setNumeroDiasVencimento(""
								+ cobrancaAcao.getNumeroDiasVencimento());
					} else {
						acaoCobrancaAtualizarActionForm.setNumeroDiasVencimento("");
					}
					
					if (cobrancaAcao.getIndicadorMetasCronograma() != null) {
						acaoCobrancaAtualizarActionForm.setIcMetasCronograma(""
								+ cobrancaAcao.getIndicadorMetasCronograma());
					} else {
						acaoCobrancaAtualizarActionForm.setIcMetasCronograma("");
					}
					
					if (cobrancaAcao.getIndicadorOrdenamentoCronograma() != null) {
						acaoCobrancaAtualizarActionForm.setIcOrdenamentoCronograma(""
								+ cobrancaAcao.getIndicadorOrdenamentoCronograma());
					} else {
						acaoCobrancaAtualizarActionForm.setIcOrdenamentoCronograma("");
					}
					
					if (cobrancaAcao.getIndicadorOrdenamentoEventual() != null) {
						acaoCobrancaAtualizarActionForm.setIcOrdenamentoEventual(""
								+ cobrancaAcao.getIndicadorOrdenamentoEventual());
					} else {
						acaoCobrancaAtualizarActionForm.setIcOrdenamentoEventual("");
					}
					
					if (cobrancaAcao.getIndicadorDebitoInterfereAcao() != null) {
						acaoCobrancaAtualizarActionForm.setIcDebitoInterfereAcao(""
								+ cobrancaAcao.getIndicadorDebitoInterfereAcao());
					} else { 
						acaoCobrancaAtualizarActionForm.setIcDebitoInterfereAcao("");
					}
					
					if (cobrancaAcao.getNumeroDiasRemuneracaoTerceiro() != null) {
						acaoCobrancaAtualizarActionForm.setNumeroDiasRemuneracaoTerceiro(""
								+ cobrancaAcao.getNumeroDiasRemuneracaoTerceiro());
					} else {
						acaoCobrancaAtualizarActionForm.setNumeroDiasRemuneracaoTerceiro("");
					}
					if(cobrancaAcao.getIndicadorCreditosARealizar()!=null){
						acaoCobrancaAtualizarActionForm.setIcCreditosARealizar(
								cobrancaAcao.getIndicadorCreditosARealizar().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setIcCreditosARealizar("");
					}
					if(cobrancaAcao.getIndicadorNotasPromissoria()!=null){
						acaoCobrancaAtualizarActionForm.setIcNotasPromissoria(
								cobrancaAcao.getIndicadorNotasPromissoria().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setIcNotasPromissoria("");
					}
					if(cobrancaAcao.getIndicadorOrdenarMaiorValor()!=null){
						acaoCobrancaAtualizarActionForm.setIcOrdenarMaiorValor(
								cobrancaAcao.getIndicadorOrdenarMaiorValor().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setIcOrdenarMaiorValor("");
					}
					if(cobrancaAcao.getIndicadorValidarItem()!=null){
						acaoCobrancaAtualizarActionForm.setIcValidarItem(
								cobrancaAcao.getIndicadorValidarItem().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setIcValidarItem("");
					}
					
					if(cobrancaAcao.getTextoPersonalizado() !=null &&
						!cobrancaAcao.getTextoPersonalizado().equals("")){
						
						//Pesquisando texto personalizado
						acaoCobrancaAtualizarActionForm.setTextoPersonalizado(fachada
						.buscarTextoPersonalizadoAcaoCobranca(cobrancaAcao.getId()));
						
					}else{
						acaoCobrancaAtualizarActionForm.setTextoPersonalizado("");
					}
					if(cobrancaAcao.getIndicadorEfetuarAcaoCpfCnpjValido()!=null){
						acaoCobrancaAtualizarActionForm.setIcEfetuarAcaoCpfCnpjValido(cobrancaAcao.getIndicadorEfetuarAcaoCpfCnpjValido().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setIcEfetuarAcaoCpfCnpjValido("");
					}
					
					acaoCobrancaAtualizarActionForm.setIndicadorMensagemSMS(cobrancaAcao.getIndicadorEnviarSMS().toString());
					acaoCobrancaAtualizarActionForm.setIndicadorMensagemEmail(cobrancaAcao.getIndicadorEnviarEmail().toString());
					
					if(Util.verificarNaoVazio(cobrancaAcao.getTextoSMS())){
						acaoCobrancaAtualizarActionForm.setTextoSMS(cobrancaAcao.getTextoSMS());
					}else{
						acaoCobrancaAtualizarActionForm.setTextoSMS("");
					}
					
					if(Util.verificarNaoVazio(cobrancaAcao.getTextoEmail())){
						acaoCobrancaAtualizarActionForm.setTextoEmail(cobrancaAcao.getTextoEmail());
					}else{
						acaoCobrancaAtualizarActionForm.setTextoEmail("");
					}
					
					if(cobrancaAcao.getNumeroMaximoTentativoEnvio() != null &&
							Util.verificarNaoVazio(cobrancaAcao.getNumeroMaximoTentativoEnvio().toString())){
						acaoCobrancaAtualizarActionForm.setNumeroMaximoTentativoEnvio(
							cobrancaAcao.getNumeroMaximoTentativoEnvio().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setNumeroMaximoTentativoEnvio("");
					}
					if (cobrancaAcao.getIndicadorExibeEventual() != null) {
						acaoCobrancaAtualizarActionForm
								.setIndicadorExibeEventual(""
										+ cobrancaAcao
												.getIndicadorExibeEventual());
					} else {
						acaoCobrancaAtualizarActionForm.setIndicadorExibeEventual("");
					}					
					
					if(cobrancaAcao.getNumeroDiasMinimoCobranca() != null &&
							Util.verificarNaoVazio(cobrancaAcao.getNumeroDiasMinimoCobranca().toString())){
						acaoCobrancaAtualizarActionForm.setNumeroDiasMinimoCobranca(
							cobrancaAcao.getNumeroDiasMinimoCobranca().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setNumeroDiasMinimoCobranca("");
					}
					
					if(cobrancaAcao.getNumeroDiasMaximoCobranca() != null &&
							Util.verificarNaoVazio(cobrancaAcao.getNumeroDiasMaximoCobranca().toString())){
						acaoCobrancaAtualizarActionForm.setNumeroDiasMaximoCobranca(
							cobrancaAcao.getNumeroDiasMaximoCobranca().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setNumeroDiasMaximoCobranca("");
					}
					
					
					
				}
				// faz as pesquisas obrigatórias
				pesquisasObrigatorias(fachada, sessao);

				// seta o objeto na sessão para ser atualizado
				sessao.setAttribute("cobrancaAcao", cobrancaAcao);

			}
		}

		return retorno;
	}

	
	
	@SuppressWarnings("unchecked")
	public ActionForward pesquisarCriterioCobranca(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("atualizarAcaoCobranca");
		
		AcaoCobrancaAtualizarActionForm acaoCobrancaActionForm = (AcaoCobrancaAtualizarActionForm) actionForm;
		FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
		Fachada fachada = this.getFachada();
		
		try {
			filtroCobrancaCriterio
					.adicionarParametro(new ParametroSimples(
							FiltroCobrancaCriterio.ID, new Integer(
									acaoCobrancaActionForm
											.getIdCobrancaCriterio())));
		} catch (NumberFormatException ex) {
			throw new ActionServletException(
					"atencao.campo_texto.numero_obrigatorio", null,
					"Critério de Cobrança");
		}
		filtroCobrancaCriterio
				.setCampoOrderBy(FiltroCobrancaCriterio.DESCRICAO_COBRANCA_CRITERIO);
		Collection<CobrancaCriterio> colecaoCobrancaCriterio = fachada.pesquisar(
				filtroCobrancaCriterio, CobrancaCriterio.class.getName());

		if (colecaoCobrancaCriterio != null
				&& !colecaoCobrancaCriterio.isEmpty()) {
			CobrancaCriterio cobrancaCriterio = (CobrancaCriterio) Util
					.retonarObjetoDeColecao(colecaoCobrancaCriterio);
			acaoCobrancaActionForm
					.setDescricaoCobrancaCriterio(cobrancaCriterio
							.getDescricaoCobrancaCriterio());
		} else {
			acaoCobrancaActionForm.setIdCobrancaCriterio("");
			acaoCobrancaActionForm
					.setDescricaoCobrancaCriterio("COBRANÇA CRITÉRIO INEXISTENTE");
		}
		
		return retorno;
		
	}

	@SuppressWarnings("unchecked")
	public ActionForward pesquisarTipoServico(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("atualizarAcaoCobranca");
		
		AcaoCobrancaAtualizarActionForm acaoCobrancaActionForm = (AcaoCobrancaAtualizarActionForm) actionForm;
		Fachada fachada = this.getFachada();
		
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
		try {
			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.ID, new Integer(
							acaoCobrancaActionForm.getIdServicoTipo())));
		} catch (NumberFormatException ex) {
			throw new ActionServletException(
					"atencao.campo_texto.numero_obrigatorio", null,
					"Serviço Tipo");
		}
		filtroServicoTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);
		Collection<ServicoTipo> colecaoServicoTipo = fachada.pesquisar(
				filtroServicoTipo, ServicoTipo.class.getName());

		if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {
			ServicoTipo servicoTipo = (ServicoTipo) Util
					.retonarObjetoDeColecao(colecaoServicoTipo);
			acaoCobrancaActionForm.setDescricaoServicoTipo(servicoTipo
					.getDescricao());
		} else {
			acaoCobrancaActionForm.setIdServicoTipo("");
			acaoCobrancaActionForm
					.setDescricaoServicoTipo("TIPO DE SERVIÇ‡O INEXISTENTE");
		}
		
		return retorno;
		
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward pesquisarDocumentoTipo(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("atualizarAcaoCobranca");
		
		AcaoCobrancaAtualizarActionForm acaoCobrancaActionForm = (AcaoCobrancaAtualizarActionForm) actionForm;
		Fachada fachada = this.getFachada();
		
		if(Util.verificarIdNaoVazio(acaoCobrancaActionForm.getIdTipoDocumentoGerado())){
			FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
			filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
			FiltroDocumentoTipo.ID, Integer.valueOf(acaoCobrancaActionForm.getIdTipoDocumentoGerado())));
			
			Collection<DocumentoTipo> colecaoDocumentoTipo = fachada.pesquisar(filtroDocumentoTipo, DocumentoTipo.class.getName());
			
			if (colecaoDocumentoTipo == null || colecaoDocumentoTipo.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Documento Tipo");
			}
	
			DocumentoTipo documentoTipo = (DocumentoTipo) Util.retonarObjetoDeColecao(colecaoDocumentoTipo);
			
			if (documentoTipo.getIndicadorTextoPersonalizado() != null &&
				documentoTipo.getIndicadorTextoPersonalizado().equals(ConstantesSistema.SIM)){
				
				httpServletRequest.setAttribute("exibirTextoPersonalizado", "OK");
			}
		}
		
		
		return retorno;
	}

	private void pesquisasObrigatorias(Fachada fachada, HttpSession sessao) {
		// pesquisa as ações predecessoras
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
					null, "Cobrança Ação");
		} else {
			sessao.setAttribute("colecaoAcaoPredecessora",
					colecaoAcaoPredecessora);
		}
		filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAcao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<CobrancaAcao> colecaoCobrancaAcaoValidacaoItem = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());		
		if (colecaoCobrancaAcaoValidacaoItem == null
				|| colecaoCobrancaAcaoValidacaoItem.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Cobrança Ação");
		} else {
			sessao.setAttribute("colecaoCobrancaAcaoValidacaoItem",
					colecaoCobrancaAcaoValidacaoItem);
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

		// pesquisa as situações de ligações de agua
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
					null, "Ligação Agua Situação");
		} else {
			sessao.setAttribute("colecaoLigacaoAguaSituacao",
					colecaoLigacaoAguaSituacao);
		}

		// pesquisa as situações de ligações de agua
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
					null, "Ligação Esgoto Situação");
		} else {
			sessao.setAttribute("colecaoLigacaoEsgotoSituacao",
					colecaoLigacaoEsgotoSituacao);
		}
		
		FiltroColunasTextoSMSEmail filtroColunasTXTSMS = new FiltroColunasTextoSMSEmail();
		filtroColunasTXTSMS.adicionarParametro(new ParametroSimples(FiltroColunasTextoSMSEmail.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroColunasTXTSMS.adicionarParametro(new ParametroSimples(FiltroColunasTextoSMSEmail.INDICADOR_COBRANCA,ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroColunasTXTSMS.setCampoOrderBy(FiltroColunasTextoSMSEmail.DESC_COLUNA);
		Collection<ColunasTextoSMSEmail> colecaoDadosTexto = fachada.pesquisar(filtroColunasTXTSMS, ColunasTextoSMSEmail.class.getName());
		sessao.setAttribute("colecaoDadosTexto", colecaoDadosTexto);
	}
	
	
	@SuppressWarnings("unchecked")
	public ActionForward validarTextoSMS(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		AcaoCobrancaAtualizarActionForm form = (AcaoCobrancaAtualizarActionForm) actionForm;
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		Collection<ColunasTextoSMSEmail> colecaoColunas = (Collection<ColunasTextoSMSEmail>)sessao.getAttribute("colecaoDadosTexto");
		String idSelecionado = form.getDadosTexto();
		String textoSMS = form.getTextoSMS();
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		
		//[FS0011] - Verificar seleção de dados
		if(!Util.verificarIdNaoVazio(idSelecionado)){
			try {
				httpServletResponse.getWriter().write("Selecione antes o dado que será incluído no texto");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			httpServletResponse.setStatus(666);
			return null;
		}
		
		//[FS0012] - Validar quantidade de colunas digitadas/selecionadas	
		//Calculando o total das tags inseridas
		int tamanho = 0;
		Iterator<ColunasTextoSMSEmail> it = colecaoColunas.iterator();
		Integer tamanhoSelecionado = 0;
		String tagSelecionada = "";
		while(it.hasNext()){
			ColunasTextoSMSEmail col = it.next();
			if(col.getId().toString().equals(idSelecionado)){
				tamanhoSelecionado = col.getTamanhoColuna();
				tagSelecionada = col.getNomeColuna();
			}
			while(textoSMS.contains(col.getNomeColuna())){
				tamanho += col.getTamanhoColuna();
				int index = textoSMS.indexOf(col.getNomeColuna());
				textoSMS = textoSMS.substring(0, index) + textoSMS.substring(index + col.getNomeColuna().length());
			}
		}
		
		tamanho += tamanhoSelecionado + textoSMS.length() + 57;
		//Caso a contagem de caracteres digitados mais o número de dígitos do campo adicionado
		//for maior que o número máximo de colunas da mensagem SMS
		
		if(tamanho > sistemaParametro.getTamanhoMaximoMensagemSms().intValue()){
			try {
				httpServletResponse.getWriter().write("Número de colunas digitadas/selecionadas somada às 57 posições do código de barras é maior que o máximo permitido ("+sistemaParametro.getTamanhoMaximoMensagemSms()+")");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			httpServletResponse.setStatus(666);
			return null;
		}
		else{
			try {
				httpServletResponse.getWriter().write(tagSelecionada);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public ActionForward validarTextoEmail(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		AcaoCobrancaAtualizarActionForm form = (AcaoCobrancaAtualizarActionForm) actionForm;
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		Collection<ColunasTextoSMSEmail> colecaoColunas = (Collection<ColunasTextoSMSEmail>)sessao.getAttribute("colecaoDadosTexto");
		String idSelecionado = form.getDadosTexto();
		
		Iterator<ColunasTextoSMSEmail> it = colecaoColunas.iterator();
		while(it.hasNext()){
			ColunasTextoSMSEmail col = it.next();
			if(col.getId().toString().equals(idSelecionado)){
				try {
					httpServletResponse.getWriter().write(col.getNomeColuna());
					return null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		}
		return null;
	}
	
}

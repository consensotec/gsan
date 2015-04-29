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
package gsan.gui.cobranca;

import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.cobranca.CobrancaCriterio;
import gsan.cobranca.CobrancaSituacao;
import gsan.cobranca.CriterioSituacaoCobranca;
import gsan.cobranca.CriterioSituacaoCobrancaPK;
import gsan.cobranca.CriterioSituacaoLigacaoAgua;
import gsan.cobranca.CriterioSituacaoLigacaoAguaPK;
import gsan.cobranca.CriterioSituacaoLigacaoEsgoto;
import gsan.cobranca.CriterioSituacaoLigacaoEsgotoPK;
import gsan.cobranca.FiltroCobrancaCriterio;
import gsan.cobranca.FiltroResolucaoDiretoria;
import gsan.cobranca.ResolucaoDiretoria;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para atualizar o crit�rio da cobran�a e as linhas do criterio
 * da cobran�a
 * 
 * @author S�vio Luiz
 * @date 11/05/2006
 */
public class AtualizarCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		Collection criteriosSituacaoCobranca = new ArrayList();			
		Collection criteriosSituacaoLigacaoAgua = new ArrayList();
		Collection criteriosSituacaoLigacaoEsgoto = new ArrayList();			

		// cria o objeto criterio cobran�a para ser inserido
		CobrancaCriterio cobrancaCriterio = (CobrancaCriterio) sessao
				.getAttribute("cobrancaCriterio");

		String campoDesabilitado = (String) sessao.getAttribute("desabilita");

		Collection colecaoCobrancaCriterioLinha = (Collection) sessao
				.getAttribute("colecaoCobrancaCriterioLinha");

		Collection colecaoCobrancaCriterioLinhaRemovidas = (Collection) sessao
				.getAttribute("colecaoCobrancaCriterioLinhaRemovidas");

		if (criterioCobrancaActionForm.getDescricaoCriterio() != null
				&& !criterioCobrancaActionForm.getDescricaoCriterio()
						.equals("")) {
			cobrancaCriterio
					.setDescricaoCobrancaCriterio(criterioCobrancaActionForm
							.getDescricaoCriterio());
		} else {
			throw new ActionServletException("atencao.required", null,
					"Descri��o do Crit�rio de Cobran�a");
		}

		// caso campoDesabilitado esteja nulo ent�o todos os campos podem de
		// crit�rio
		// cobran�a podem ser atualizados,caso seja diferente de nulo s� o campo
		// descri��o e
		// indicador de uso pode ser alterados
		if (campoDesabilitado == null || campoDesabilitado.equals("")) {
			Date dataInicio = null;
			if (criterioCobrancaActionForm.getDataInicioVigencia() != null
					&& !criterioCobrancaActionForm.getDataInicioVigencia()
							.equals("")) {
				String dataInicioVigencia = criterioCobrancaActionForm
						.getDataInicioVigencia();
				if (Util.validarDiaMesAno(dataInicioVigencia)) {
					throw new ActionServletException(
							"atencao.data.inicio.Vigencia.invalida");
				}

				dataInicio = Util.converteStringParaDate(dataInicioVigencia);
				if (!cobrancaCriterio.getDataInicioVigencia()
						.equals(dataInicio)) {
					Date dataAtualSemHora = Util
							.formatarDataSemHora(new Date());
					if (dataInicio.before(dataAtualSemHora)) {
						String dataAtual = Util.formatarData(new Date());
						throw new ActionServletException(
								"atencao.data.inicio.nao.superior.data.corrente",
								null, dataAtual);
					}
				}
			} else {
				throw new ActionServletException("atencao.required", null,
						"Data de In�cio de Vig�ncia do Crit�rio");
			}
			cobrancaCriterio.setDataInicioVigencia(dataInicio);
			if (criterioCobrancaActionForm.getNumeroAnoContaAntiga() != null
					&& !criterioCobrancaActionForm.getNumeroAnoContaAntiga()
							.equals("")) {
				boolean valorNaoNumerico = Util
						.validarValorNaoNumerico(criterioCobrancaActionForm
								.getNumeroAnoContaAntiga());
				if (!valorNaoNumerico) {
					cobrancaCriterio.setNumeroContaAntiga(new Short(
							criterioCobrancaActionForm
									.getNumeroAnoContaAntiga()));
				} else {
					throw new ActionServletException("atencao.integer", null,
							"N�mero de Anos para Determinar Conta Antiga");
				}
			} else {
				throw new ActionServletException("atencao.required", null,
						"N�mero de Anos para Determinar Conta Antiga");
			}
			
			if (criterioCobrancaActionForm.getValorLimitePrioridade() != null &&
					!criterioCobrancaActionForm.getValorLimitePrioridade().equals("")	){
				BigDecimal valorLimitePrioridade = 
					new BigDecimal(criterioCobrancaActionForm.getValorLimitePrioridade()
							.replace(".", "").replace(",", "."));
				cobrancaCriterio.setValorLimitePrioridade(valorLimitePrioridade);
			}else{
				throw new ActionServletException("atencao.required", null,
				"Valor Limite para Prioridade");
			}

			
			if (criterioCobrancaActionForm.getPercentualValorMinimoPagoParceladoCancelado() != null &&
					!criterioCobrancaActionForm.getPercentualValorMinimoPagoParceladoCancelado().equals("")	){
				BigDecimal percentualValorMinimoPagoParceladoCancelado = 
					new BigDecimal(criterioCobrancaActionForm.getPercentualValorMinimoPagoParceladoCancelado()
							.replace(".", "").replace(",", "."));
				cobrancaCriterio.setPercentualValorMinimoPagoParceladoCancelado(percentualValorMinimoPagoParceladoCancelado);
			}else{
				throw new ActionServletException("atencao.required", null,
				"Percentual Valor");
			}
			
			if (criterioCobrancaActionForm.getPercentualQuantidadeMinimoPagoParceladoCancelado() != null &&
					!criterioCobrancaActionForm.getPercentualQuantidadeMinimoPagoParceladoCancelado().equals("")	){
				BigDecimal percentualQuantidadeMinimoPagoParceladoCancelado = 
					new BigDecimal(criterioCobrancaActionForm.getPercentualQuantidadeMinimoPagoParceladoCancelado()
							.replace(".", "").replace(",", "."));
				cobrancaCriterio.setPercentualQuantidadeMinimoPagoParceladoCancelado(percentualQuantidadeMinimoPagoParceladoCancelado);
			}else{
				throw new ActionServletException("atencao.required", null,
				"Percentual Quantidade de Itens");
			}
			
			if (criterioCobrancaActionForm.getOpcaoAcaoImovelSitEspecial() != null
					&& !criterioCobrancaActionForm
							.getOpcaoAcaoImovelSitEspecial().equals("")) {
				cobrancaCriterio
						.setIndicadorEmissaoImovelParalisacao(new Short(
								criterioCobrancaActionForm
										.getOpcaoAcaoImovelSitEspecial()));
			} else {
				throw new ActionServletException("atencao.required", null,
						"Emiss�o da A��o para Im�vel com Sit. Especial de Cobran�a");
			}
			if (criterioCobrancaActionForm.getOpcaoAcaoImovelSit() != null
					&& !criterioCobrancaActionForm.getOpcaoAcaoImovelSit()
							.equals("")) {
				cobrancaCriterio
						.setIndicadorEmissaoImovelSituacaoCobranca(new Short(
								criterioCobrancaActionForm
										.getOpcaoAcaoImovelSit()));
			} else {
				throw new ActionServletException("atencao.required", null,
						"Emiss�o da A��o para Im�vel com Sit. de Cobran�a");
			}
			if (criterioCobrancaActionForm.getOpcaoContasRevisao() != null
					&& !criterioCobrancaActionForm.getOpcaoContasRevisao()
							.equals("")) {
				cobrancaCriterio.setIndicadorEmissaoContaRevisao(new Short(
						criterioCobrancaActionForm
								.getOpcaoAcaoImovelSitEspecial()));
			} else {
				throw new ActionServletException("atencao.required", null,
						"Considerar Contas em Revis�o");
			}
			if (criterioCobrancaActionForm.getOpcaoAcaoImovelDebitoMesConta() != null
					&& !criterioCobrancaActionForm
							.getOpcaoAcaoImovelDebitoMesConta().equals("")) {
				cobrancaCriterio.setIndicadorEmissaoDebitoContaMes(new Short(
						criterioCobrancaActionForm
								.getOpcaoAcaoImovelDebitoMesConta()));
			} else {
				throw new ActionServletException("atencao.required", null,
						"Emiss�o da A��o para Im�vel com D�bito s� da Conta do M�s");
			}
			if (criterioCobrancaActionForm
					.getOpcaoAcaoInquilinoDebitoMesConta() != null
					&& !criterioCobrancaActionForm
							.getOpcaoAcaoInquilinoDebitoMesConta().equals("")) {
				cobrancaCriterio
						.setIndicadorEmissaoInquilinoDebitoContaMes(new Short(
								criterioCobrancaActionForm
										.getOpcaoAcaoInquilinoDebitoMesConta()));
			} else {
				throw new ActionServletException(
						"atencao.required",
						null,
						"Emiss�o da A��o para Inquilino Com D�bito s� da Conta do M�s Independentemente do Valor da Conta");
			}
			if (criterioCobrancaActionForm
					.getOpcaoAcaoImovelDebitoContasAntigas() != null
					&& !criterioCobrancaActionForm
							.getOpcaoAcaoImovelDebitoContasAntigas().equals("")) {
				cobrancaCriterio
						.setIndicadorEmissaoDebitoContaAntiga(new Short(
								criterioCobrancaActionForm
										.getOpcaoAcaoImovelDebitoContasAntigas()));
			} else {
				throw new ActionServletException("atencao.required", null,
						"Emiss�o da A��o para Im�vel com D�bito s� de Contas Antigas");
			}
			
			if (criterioCobrancaActionForm.getDescricaoCriterio() != null
					&& !criterioCobrancaActionForm.getDescricaoCriterio()
							.equals("")) {
				cobrancaCriterio
						.setIndicadorImovelComSituacaoCobranca(new Short(criterioCobrancaActionForm
								.getIndicadorImovelComSitCob()));
			} else {
				throw new ActionServletException("atencao.required", null,
						"Indicador de Im�vel com Situa��o Cobran�a");
			}
			
			if (criterioCobrancaActionForm.getNumeroDiasAposVencimento() != null 
					&& !criterioCobrancaActionForm.getNumeroDiasAposVencimento().trim().equals("")) {
				boolean valorNaoNumerico = Util.validarValorNaoNumerico(criterioCobrancaActionForm.getNumeroDiasAposVencimento());
				if (!valorNaoNumerico) {
					cobrancaCriterio.setNumeroDiasAposVencimento(new Short(criterioCobrancaActionForm.getNumeroDiasAposVencimento()));
				} else {
					throw new ActionServletException("atencao.integer", null, "N�mero de Dias Ap�s o Vencimento");
				}
			}
			else{
				cobrancaCriterio.setNumeroDiasAposVencimento(null);
			}
			
			
			if (colecaoCobrancaCriterioLinha == null
					|| colecaoCobrancaCriterioLinha.isEmpty()) {

				throw new ActionServletException(
						"atencao.informar.linha.criterio.cobranca");
			}		

			// Verificando se houve situacoes de cobranca escolhidas para o criterio
			if (criterioCobrancaActionForm.getOpcaoAcaoImovelSit() != null &&
					criterioCobrancaActionForm.getOpcaoAcaoImovelSit().equals("1") &&
					criterioCobrancaActionForm.getIdsCobrancaSituacao() != null && 
					criterioCobrancaActionForm.getIdsCobrancaSituacao().length > 0){
								
				for (int i = 0; i < criterioCobrancaActionForm.getIdsCobrancaSituacao().length; i++) {
					CriterioSituacaoCobranca csc = new CriterioSituacaoCobranca();
					CriterioSituacaoCobrancaPK cscPK = new CriterioSituacaoCobrancaPK();
					CobrancaSituacao cobSit = new CobrancaSituacao();
					cobSit.setId(new Integer(criterioCobrancaActionForm.getIdsCobrancaSituacao()[i]));
					cscPK.setCobrancaSituacao(cobSit);
					cscPK.setCobrancaCriterio(cobrancaCriterio);
					csc.setComp_id(cscPK);
					criteriosSituacaoCobranca.add(csc);				
				}
			}
			
			// verificando se houveram situacoes de ligacao de agua para este criterio
			if (criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua() != null && 
					criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua().length > 0){
								
				for (int i = 0; i < criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua().length; i++) {
					CriterioSituacaoLigacaoAgua csla = new CriterioSituacaoLigacaoAgua();
					CriterioSituacaoLigacaoAguaPK cslaPK = new CriterioSituacaoLigacaoAguaPK();
					LigacaoAguaSituacao ligAguaSit = new LigacaoAguaSituacao();
					ligAguaSit.setId(new Integer(criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua()[i]));
					cslaPK.setLigacaoAguaSituacao(ligAguaSit);
					cslaPK.setCobrancaCriterio(cobrancaCriterio);
					csla.setComp_id(cslaPK);
					criteriosSituacaoLigacaoAgua.add(csla);				
				}
			}

//			 verificando se houveram situacoes de ligacao de esgoto para este criterio
			if (criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto() != null && 
					criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto().length > 0){
				
				for (int i = 0; i < criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto().length; i++) {
					CriterioSituacaoLigacaoEsgoto csle = new CriterioSituacaoLigacaoEsgoto();
					CriterioSituacaoLigacaoEsgotoPK cslePK = new CriterioSituacaoLigacaoEsgotoPK();
					LigacaoEsgotoSituacao ligEsgotoSit = new LigacaoEsgotoSituacao();
					ligEsgotoSit.setId(new Integer(criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto()[i]));
					cslePK.setLigacaoEsgotoSituacao(ligEsgotoSit);
					cslePK.setCobrancaCriterio(cobrancaCriterio);
					csle.setComp_id(cslePK);
					criteriosSituacaoLigacaoEsgoto.add(csle);				
				}
			}
			
		}
		if(!criterioCobrancaActionForm.getIdResolucaoDiretoria().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"") ){
			FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.CODIGO, 
					criterioCobrancaActionForm.getIdResolucaoDiretoria()));
			Collection colecaoResolucaoDiretoria = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());
			
			if(colecaoResolucaoDiretoria != null && !colecaoResolucaoDiretoria.isEmpty()){
				cobrancaCriterio.setResolucaoDiretoria(
						(ResolucaoDiretoria)colecaoResolucaoDiretoria.iterator().next());
			}
		}else{
			cobrancaCriterio.setResolucaoDiretoria(null);
		}
		
		cobrancaCriterio.setIndicadorUso(new Short(criterioCobrancaActionForm
				.getIndicadorUso()));

		FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
		filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
				FiltroCobrancaCriterio.DESCRICAO_COBRANCA_CRITERIO, cobrancaCriterio.getDescricaoCobrancaCriterio()));
		Collection<CobrancaCriterio> collectionCobrancaCriterio = fachada.pesquisar(filtroCobrancaCriterio, CobrancaCriterio.class.getName());
		if(collectionCobrancaCriterio !=null && !collectionCobrancaCriterio.isEmpty()){
			CobrancaCriterio cobrancaCriterioPes = (CobrancaCriterio) Util.retonarObjetoDeColecao(collectionCobrancaCriterio);
			if(cobrancaCriterioPes.getId().compareTo(cobrancaCriterio.getId()) != 0)
				throw new ActionServletException("atencao.msg_personalizada", "Descri��o de Crit�rio de Cobran�a j� existente em outro Crit�rio de Cobran�a");
		}
		fachada.atualizarCobrancaCriterio(cobrancaCriterio,
				colecaoCobrancaCriterioLinha,
				colecaoCobrancaCriterioLinhaRemovidas,
				criteriosSituacaoCobranca, criteriosSituacaoLigacaoAgua,
				criteriosSituacaoLigacaoEsgoto,
				this.getUsuarioLogado(httpServletRequest));

		sessao.removeAttribute("colecaoCobrancaCriterioLinha");
		sessao.removeAttribute("colecaoCobrancaCriterioLinhaRemovidas");
		sessao.removeAttribute("cobrancaCriterio");
		sessao.getAttribute("desabilita");
		sessao.removeAttribute("indicadorAtualizar");
		sessao.removeAttribute("voltar");

		montarPaginaSucesso(httpServletRequest, "Crit�rio de Cobran�a "
				+ cobrancaCriterio.getId() + " atualizado com sucesso.",
				"Realizar outra Manuten��o de Crit�rio de Cobran�a",
				"exibirFiltrarCriterioCobrancaAction.do?menu=sim");

		return retorno;
	}

}

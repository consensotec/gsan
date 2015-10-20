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

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.CriterioSituacaoCobranca;
import gcom.cobranca.CriterioSituacaoCobrancaPK;
import gcom.cobranca.CriterioSituacaoLigacaoAgua;
import gcom.cobranca.CriterioSituacaoLigacaoAguaPK;
import gcom.cobranca.CriterioSituacaoLigacaoEsgoto;
import gcom.cobranca.CriterioSituacaoLigacaoEsgotoPK;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para inserir o crit�rio da cobran�a e as linhas do criterio da
 * cobran�a
 * 
 * @author S�vio Luiz
 * @date 03/05/2006
 */
public class InserirCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		Integer idCobrancaCriterio = null;

		// cria o objeto criterio cobran�a para ser inserido
		CobrancaCriterio cobrancaCriterio = new CobrancaCriterio();

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
		Date dataInicio = null;
		if (criterioCobrancaActionForm.getDataInicioVigencia() != null
				&& !criterioCobrancaActionForm.getDataInicioVigencia().equals(
						"")) {
			String dataInicioVigencia = criterioCobrancaActionForm
					.getDataInicioVigencia();
			if (Util.validarDiaMesAno(dataInicioVigencia)) {
				throw new ActionServletException(
						"atencao.data.inicio.Vigencia.invalida");
			}

			dataInicio = Util.converteStringParaDate(dataInicioVigencia);
			Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
			if (dataInicio.before(dataAtualSemHora)) {
				String dataAtual = Util.formatarData(new Date());
				throw new ActionServletException(
						"atencao.data.inicio.nao.superior.data.corrente", null,
						dataAtual);
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
						criterioCobrancaActionForm.getNumeroAnoContaAntiga()));
			} else {
				throw new ActionServletException("atencao.integer", null,
						"N�mero de Anos para Determinar Conta Antiga");
			}
		} else {
			throw new ActionServletException("atencao.required", null,
					"N�mero de Anos para Determinar Conta Antiga");
		}

		if (criterioCobrancaActionForm.getValorLimitePrioridade() != null
				&& !criterioCobrancaActionForm.getValorLimitePrioridade()
						.equals("")) {
			BigDecimal valorLimitePrioridade = new BigDecimal(
					criterioCobrancaActionForm.getValorLimitePrioridade().replace(".", "")
							.replace(",", "."));
			cobrancaCriterio.setValorLimitePrioridade(valorLimitePrioridade);
		} else {
			throw new ActionServletException("atencao.required", null,
					"Valor Limite para Prioridade");
		}

		if (criterioCobrancaActionForm
				.getPercentualValorMinimoPagoParceladoCancelado() != null
				&& !criterioCobrancaActionForm
						.getPercentualValorMinimoPagoParceladoCancelado()
						.equals("")) {
			BigDecimal percentualValorMinimoPagoParceladoCancelado = new BigDecimal(
					criterioCobrancaActionForm
							.getPercentualValorMinimoPagoParceladoCancelado()
							.replace(".", "").replace(",", "."));
			cobrancaCriterio
					.setPercentualValorMinimoPagoParceladoCancelado(percentualValorMinimoPagoParceladoCancelado);
		} else {
			throw new ActionServletException("atencao.required", null,
					"Percentual Valor");
		}

		if (criterioCobrancaActionForm
				.getPercentualQuantidadeMinimoPagoParceladoCancelado() != null
				&& !criterioCobrancaActionForm
						.getPercentualQuantidadeMinimoPagoParceladoCancelado()
						.equals("")) {
			BigDecimal percentualQuantidadeMinimoPagoParceladoCancelado = new BigDecimal(
					criterioCobrancaActionForm
							.getPercentualQuantidadeMinimoPagoParceladoCancelado().replace(".", "")
							.replace(",", "."));
			cobrancaCriterio
					.setPercentualQuantidadeMinimoPagoParceladoCancelado(percentualQuantidadeMinimoPagoParceladoCancelado);
		} else {
			throw new ActionServletException("atencao.required", null,
					"Percentual Quantidade de Itens");
		}

		if (criterioCobrancaActionForm.getOpcaoAcaoImovelSitEspecial() != null
				&& !criterioCobrancaActionForm.getOpcaoAcaoImovelSitEspecial()
						.equals("")) {
			cobrancaCriterio
					.setIndicadorEmissaoImovelParalisacao(new Short(
							criterioCobrancaActionForm
									.getOpcaoAcaoImovelSitEspecial()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Emiss�o da A��o para Im�vel com Sit. Especial de Cobran�a");
		}
		if (criterioCobrancaActionForm.getOpcaoAcaoImovelSit() != null
				&& !criterioCobrancaActionForm.getOpcaoAcaoImovelSit().equals(
						"")) {
			cobrancaCriterio
					.setIndicadorEmissaoImovelSituacaoCobranca(new Short(
							criterioCobrancaActionForm.getOpcaoAcaoImovelSit()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Emiss�o da A��o para Im�vel com Sit. de Cobran�a");
		}
		if (criterioCobrancaActionForm.getOpcaoContasRevisao() != null
				&& !criterioCobrancaActionForm.getOpcaoContasRevisao().equals(
						"")) {
			cobrancaCriterio
					.setIndicadorEmissaoContaRevisao(new Short(
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
		if (criterioCobrancaActionForm.getOpcaoAcaoInquilinoDebitoMesConta() != null
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
		if (criterioCobrancaActionForm.getOpcaoAcaoImovelDebitoContasAntigas() != null
				&& !criterioCobrancaActionForm
						.getOpcaoAcaoImovelDebitoContasAntigas().equals("")) {
			cobrancaCriterio.setIndicadorEmissaoDebitoContaAntiga(new Short(
					criterioCobrancaActionForm
							.getOpcaoAcaoImovelDebitoContasAntigas()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Emiss�o da A��o para Im�vel com D�bito s� de Contas Antigas");
		}
		
		if (criterioCobrancaActionForm.getIndicadorImovelComSitCob() != null
				&& !criterioCobrancaActionForm.getIndicadorImovelComSitCob()
						.equals("")) {
			cobrancaCriterio
					.setIndicadorImovelComSituacaoCobranca(new Short(criterioCobrancaActionForm
							.getIndicadorImovelComSitCob()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Im�vel com Situa��o de Cobran�a");
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
				
		// Verificando se houve situacoes de cobranca escolhidas para o criterio
		if (criterioCobrancaActionForm.getIdsCobrancaSituacao() != null && 
				criterioCobrancaActionForm.getIdsCobrancaSituacao().length > 0){
			
			Collection criteriosSituacaoCobranca = new ArrayList();
			
			for (int i = 0; i < criterioCobrancaActionForm.getIdsCobrancaSituacao().length; i++) {
				CriterioSituacaoCobranca csc = new CriterioSituacaoCobranca();
				CriterioSituacaoCobrancaPK cscPK = new CriterioSituacaoCobrancaPK();
				CobrancaSituacao cobSit = new CobrancaSituacao();
				cobSit.setId(new Integer(criterioCobrancaActionForm.getIdsCobrancaSituacao()[i]));
				cscPK.setCobrancaSituacao(cobSit);
				csc.setComp_id(cscPK);
				criteriosSituacaoCobranca.add(csc);				
			}
			cobrancaCriterio.setCriteriosSituacaoCobranca(new HashSet(criteriosSituacaoCobranca));
		}
		
		// verificando se houveram situacoes de ligacao de agua para este criterio
		if (criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua() != null && 
				criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua().length > 0){
			
			Collection criteriosSituacaoLigacaoAgua = new ArrayList();
			
			for (int i = 0; i < criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua().length; i++) {
				CriterioSituacaoLigacaoAgua csla = new CriterioSituacaoLigacaoAgua();
				CriterioSituacaoLigacaoAguaPK cslaPK = new CriterioSituacaoLigacaoAguaPK();
				LigacaoAguaSituacao ligAguaSit = new LigacaoAguaSituacao();
				ligAguaSit.setId(new Integer(criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua()[i]));
				cslaPK.setLigacaoAguaSituacao(ligAguaSit);
				csla.setComp_id(cslaPK);
				criteriosSituacaoLigacaoAgua.add(csla);				
			}
			cobrancaCriterio.setCriteriosSituacaoLigacaoAgua(new HashSet(criteriosSituacaoLigacaoAgua));
		}

//		 verificando se houveram situacoes de ligacao de esgoto para este criterio
		if (criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto() != null && 
				criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto().length > 0){
			
			Collection criteriosSituacaoLigacaoEsgoto = new ArrayList();
			
			for (int i = 0; i < criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto().length; i++) {
				CriterioSituacaoLigacaoEsgoto csle = new CriterioSituacaoLigacaoEsgoto();
				CriterioSituacaoLigacaoEsgotoPK cslePK = new CriterioSituacaoLigacaoEsgotoPK();
				LigacaoEsgotoSituacao ligEsgotoSit = new LigacaoEsgotoSituacao();
				ligEsgotoSit.setId(new Integer(criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto()[i]));
				cslePK.setLigacaoEsgotoSituacao(ligEsgotoSit);
				csle.setComp_id(cslePK);
				criteriosSituacaoLigacaoEsgoto.add(csle);				
			}
			cobrancaCriterio.setCriteriosSituacaoLigacaoEsgoto(new HashSet(criteriosSituacaoLigacaoEsgoto));
		}
		
		if(criterioCobrancaActionForm.getIdResolucaoDiretoria() != null && 
				!criterioCobrancaActionForm.getIdResolucaoDiretoria().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
			
			cobrancaCriterio.setResolucaoDiretoria(
					buscarResolucaoDiretoriaPorId(criterioCobrancaActionForm.getIdResolucaoDiretoria()));
		}
		
		cobrancaCriterio.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		cobrancaCriterio.setUltimaAlteracao(new Date());
		Collection colecaoCobrancaCriterioLinha = (Collection) sessao
				.getAttribute("colecaoCobrancaCriterioLinha");

		if (colecaoCobrancaCriterioLinha != null && !colecaoCobrancaCriterioLinha.isEmpty()) {
			
			cobrancaCriterio.setCobrancaCriterioLinhas(new HashSet(colecaoCobrancaCriterioLinha));
			
			FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.DESCRICAO_COBRANCA_CRITERIO, cobrancaCriterio.getDescricaoCobrancaCriterio()));
			Collection<CobrancaCriterio> collectionCobrancaCriterio = fachada.pesquisar(filtroCobrancaCriterio, CobrancaCriterio.class.getName());
			if(collectionCobrancaCriterio !=null && !collectionCobrancaCriterio.isEmpty())
				throw new ActionServletException("atencao.msg_personalizada", "Descri��o de Crit�rio de Cobran�a j� existente");
			else
				idCobrancaCriterio = fachada.inserirCobrancaCriterio(cobrancaCriterio,this.getUsuarioLogado(httpServletRequest));
		} else {
			throw new ActionServletException("atencao.informar.linha.criterio.cobranca");
		}
		sessao.removeAttribute("colecaoCobrancaCriterioLinha");

		montarPaginaSucesso(httpServletRequest, "Crit�rio de Cobran�a "
				+ idCobrancaCriterio + " inserido com sucesso.",
				"Inserir outro Crit�rio de Cobran�a",
				"exibirInserirCriterioCobrancaAction.do?menu=sim",
				"exibirAtualizarCriterioCobrancaAction.do?idRegistroAtualizacao="
						+ idCobrancaCriterio + "&retornoFiltrar=1",
				"Atualizar Crit�rio de Cobran�a inserido");
		return retorno;
	}
	
	private ResolucaoDiretoria buscarResolucaoDiretoriaPorId(String id){
		ResolucaoDiretoria  resolucaoDiretoria = null;
		
		FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
		
		filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.CODIGO, id));
		Collection colecaoResolucaoDiretoria = Fachada.getInstancia().pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());
		
		if(colecaoResolucaoDiretoria != null && !colecaoResolucaoDiretoria.isEmpty()){
			resolucaoDiretoria = (ResolucaoDiretoria)colecaoResolucaoDiretoria.iterator().next();
		}
		
		return resolucaoDiretoria;
	}

}

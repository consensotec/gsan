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
package gsan.gui.cobranca;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.cobranca.CobrancaCriterio;
import gsan.cobranca.CobrancaCriterioLinha;
import gsan.cobranca.CobrancaDocumento;
import gsan.cobranca.CobrancaSituacao;
import gsan.cobranca.CriterioSituacaoCobranca;
import gsan.cobranca.CriterioSituacaoLigacaoAgua;
import gsan.cobranca.CriterioSituacaoLigacaoEsgoto;
import gsan.cobranca.FiltroCobrancaCriterio;
import gsan.cobranca.FiltroCobrancaCriterioLinha;
import gsan.cobranca.FiltroCobrancaDocumento;
import gsan.cobranca.FiltroCobrancaSituacao;
import gsan.cobranca.FiltroResolucaoDiretoria;
import gsan.cobranca.ResolucaoDiretoria;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.MaiorQue;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para atualizar o criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 08/05/2006
 */
public class ExibirAtualizarCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarCriterioCobranca");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String reload = httpServletRequest.getParameter("chamarReload");
		
		//caso seja a primeira vez 
		if(httpServletRequest.getParameter("menu") != null){
		 sessao.removeAttribute("voltar");
		}

		// caso venha do adicionar só direciona a pagina
		// caso o reload seja nulo então processa
		if (reload == null || reload.equals("")) {

			CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;
			// Cria a variável que vai armazenar o criterio cobrança para ser
			// atualizada
			CobrancaCriterio cobrancaCriterio = null;

			if (httpServletRequest.getParameter("limpaSessao") != null) {
				sessao.removeAttribute("colecaoCobrancaCriterioLinha");
			}

			if (sessao.getAttribute("colecaoCobrancaCriterioLinha") == null
					|| sessao.getAttribute("colecaoCobrancaCriterioLinha")
							.equals("")) {

				String idCriterioCobranca = null;

				if (httpServletRequest.getParameter("limpaSessao") == null) {

					if (httpServletRequest
							.getParameter("idRegistroAtualizacao") == null) {
						idCriterioCobranca = (String) sessao
								.getAttribute("idRegistroAtualizacao");
						// Definindo a volta do botão Voltar p Filtrar critério
						// cobrança
						sessao.setAttribute("voltar", "filtrar");
					} else {
						idCriterioCobranca = httpServletRequest
								.getParameter("idRegistroAtualizacao");
						// Definindo a volta do botão Voltar para Manter
						// critério
						// cobrança caso o retornoFiltrar seja igual a nulo
						// Caso o retornoFiltrar seja diferente de nulo (no caso
						// da tela
						// de sucesso de inserir, chamar o atalizar) então o
						// botão voltar
						// retorna para o Filtro critério cobrança
						if (httpServletRequest.getParameter("retornoFiltrar") != null) {
							sessao.setAttribute("voltar", "filtrar");
						} else {
							sessao.setAttribute("voltar", "manter");
						}
						sessao.setAttribute("idRegistroAtualizacao",
								idCriterioCobranca);
					}
				} else {
					idCriterioCobranca = (String) sessao
							.getAttribute("idRegistroAtualizacao");
				}
				FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.ID, idCriterioCobranca));
				filtroCobrancaCriterio.adicionarCaminhoParaCarregamentoEntidade(
						FiltroCobrancaCriterio.CRITERIOS_SITUACAO_COBRANCA);
				filtroCobrancaCriterio.adicionarCaminhoParaCarregamentoEntidade(
						FiltroCobrancaCriterio.CRITERIOS_SITUACAO_LIGACAO_AGUA);
				filtroCobrancaCriterio.adicionarCaminhoParaCarregamentoEntidade(
						FiltroCobrancaCriterio.CRITERIOS_SITUACAO_LIGACAO_ESGOTO);
				
				Collection<CobrancaCriterio> collectionCobrancaCriterio = fachada
						.pesquisar(filtroCobrancaCriterio,
								CobrancaCriterio.class.getName());

				// Caso a pesquisa tenha retornado o critério de cobrança
				if (collectionCobrancaCriterio != null
						&& !collectionCobrancaCriterio.isEmpty()) {

					// Recupera da coleção a rota que vai ser atualizada
					cobrancaCriterio = (CobrancaCriterio) Util
							.retonarObjetoDeColecao(collectionCobrancaCriterio);

					// verifica se existe a cobranca critério documento para a
					// cobranca critério escolhida
					FiltroCobrancaDocumento filtroCobrancaDocumento = new FiltroCobrancaDocumento();
					filtroCobrancaDocumento
							.adicionarParametro(new ParametroSimples(
									FiltroCobrancaDocumento.ID_COBRANCA_CRITERIO,
									cobrancaCriterio.getId()));
					Integer qtdCobrancaDocumento = fachada
							.totalRegistrosPesquisa(filtroCobrancaDocumento,
									CobrancaDocumento.class.getName());
					// caso exista alguma cobranca documento então desabilita
					// alguns
					// campos
					if (qtdCobrancaDocumento != null &&
							qtdCobrancaDocumento != 0) {
						sessao.setAttribute("desabilita", "1");
					}

					// Seta no form os dados do critério cobrança
					criterioCobrancaActionForm.setDescricaoCriterio(""
							+ cobrancaCriterio.getDescricaoCobrancaCriterio());
					criterioCobrancaActionForm.setDataInicioVigencia(Util
							.formatarData(cobrancaCriterio
									.getDataInicioVigencia()));
					criterioCobrancaActionForm.setNumeroAnoContaAntiga(""
							+ cobrancaCriterio.getNumeroContaAntiga());
					criterioCobrancaActionForm.setOpcaoAcaoImovelSitEspecial(""
							+ cobrancaCriterio
									.getIndicadorEmissaoImovelParalisacao());
					criterioCobrancaActionForm
							.setOpcaoAcaoImovelSit(""
									+ cobrancaCriterio
											.getIndicadorEmissaoImovelSituacaoCobranca());
					criterioCobrancaActionForm.setOpcaoContasRevisao(""
							+ cobrancaCriterio
									.getIndicadorEmissaoContaRevisao());
					criterioCobrancaActionForm
							.setOpcaoAcaoImovelDebitoMesConta(""
									+ cobrancaCriterio
											.getIndicadorEmissaoDebitoContaMes());
					criterioCobrancaActionForm
							.setOpcaoAcaoInquilinoDebitoMesConta(""
									+ cobrancaCriterio
											.getIndicadorEmissaoInquilinoDebitoContaMes());
					criterioCobrancaActionForm
							.setOpcaoAcaoImovelDebitoContasAntigas(""
									+ cobrancaCriterio
											.getIndicadorEmissaoDebitoContaAntiga());
					criterioCobrancaActionForm.setIndicadorUso(""
							+ cobrancaCriterio.getIndicadorUso());
					
					criterioCobrancaActionForm
							.setPercentualValorMinimoPagoParceladoCancelado(
									Util.formatarMoedaReal(
									cobrancaCriterio.getPercentualValorMinimoPagoParceladoCancelado()));
					
					
					criterioCobrancaActionForm
					.setPercentualQuantidadeMinimoPagoParceladoCancelado(
							Util.formatarMoedaReal(
							cobrancaCriterio.getPercentualQuantidadeMinimoPagoParceladoCancelado()));

					
					criterioCobrancaActionForm
					.setValorLimitePrioridade(
							Util.formatarMoedaReal(
							cobrancaCriterio.getValorLimitePrioridade()));
					
					criterioCobrancaActionForm
					.setIndicadorImovelComSitCob(
							cobrancaCriterio.getIndicadorImovelComSituacaoCobranca().toString());
					
					criterioCobrancaActionForm.setNumeroDiasAposVencimento("");
					if(cobrancaCriterio.getNumeroDiasAposVencimento() !=null)
						criterioCobrancaActionForm.setNumeroDiasAposVencimento(cobrancaCriterio.getNumeroDiasAposVencimento().toString());
					

					// recupera a coleção de cobrança critério linha
					FiltroCobrancaCriterioLinha filtroCobrancaCriterioLinha = new FiltroCobrancaCriterioLinha();
					filtroCobrancaCriterioLinha
							.adicionarParametro(new ParametroSimples(
									FiltroCobrancaCriterioLinha.COBRANCA_CRITERIO_ID,
									cobrancaCriterio.getId()));
					filtroCobrancaCriterioLinha
							.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
					filtroCobrancaCriterioLinha
							.adicionarCaminhoParaCarregamentoEntidade("categoria");

					Collection colecaoCobrancaCriterioLinha = fachada
							.pesquisar(filtroCobrancaCriterioLinha,
									CobrancaCriterioLinha.class.getName());
					if (colecaoCobrancaCriterioLinha != null
							&& !colecaoCobrancaCriterioLinha.isEmpty()) {
						sessao.setAttribute("colecaoCobrancaCriterioLinha",
								colecaoCobrancaCriterioLinha);
					}
					
					// consultar as situacoes de cobranca
			        FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
			        
			        filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			        filtroCobrancaSituacao.setCampoOrderBy(FiltroCobrancaSituacao.DESCRICAO);
			        
			        Collection colecaoCobrancaSituacao = fachada.pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class.getName());
			        
			        sessao.setAttribute("colecaoCobrancaSituacao", colecaoCobrancaSituacao);
			        
			        // preencher situacoes de cobranca setados
			        String[] idsSituacaoCobranca = new String[0];
			        if (cobrancaCriterio.getCriteriosSituacaoCobranca() != null &&
			        		!cobrancaCriterio.getCriteriosSituacaoCobranca().isEmpty()){
			        	idsSituacaoCobranca = new String[cobrancaCriterio.getCriteriosSituacaoCobranca().size()];
			        	int i = 0;
				        for (Iterator iter = cobrancaCriterio.getCriteriosSituacaoCobranca().iterator(); 
				        		iter.hasNext();) {
				        	CriterioSituacaoCobranca critSitCob = (CriterioSituacaoCobranca) iter.next();
				        	idsSituacaoCobranca[i++] = critSitCob.getComp_id().getCobrancaSituacao().getId() + "";					
				        }
			        }
			        criterioCobrancaActionForm.setIdsCobrancaSituacao(idsSituacaoCobranca);

			        // consultar as situacoes de ligacao de agua
			        FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			        
			        filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			        filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
			        
			        Collection colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
			        
			        sessao.setAttribute("colecaoSituacaoLigacaoAgua", colecaoLigacaoAguaSituacao);

			        // preencher situacoes de ligacao agua setados
			        String[] idsSituacaoLigacaoAgua = new String[0];
			        if (cobrancaCriterio.getCriteriosSituacaoLigacaoAgua() != null &&
			        		!cobrancaCriterio.getCriteriosSituacaoLigacaoAgua().isEmpty()){
			        	idsSituacaoLigacaoAgua = new String[cobrancaCriterio
			        	    .getCriteriosSituacaoLigacaoAgua().size()];
			        	int i = 0;
				        for (Iterator iter = cobrancaCriterio.getCriteriosSituacaoLigacaoAgua().iterator(); 
				        		iter.hasNext();) {
				        	CriterioSituacaoLigacaoAgua critSitLigAgua = (CriterioSituacaoLigacaoAgua) iter.next();
				        	idsSituacaoLigacaoAgua[i++] = critSitLigAgua.getComp_id().getLigacaoAguaSituacao().getId() + "";					
				        }
			        }
			        criterioCobrancaActionForm.setIdsSituacaoLigacaoAgua(idsSituacaoLigacaoAgua);
			        
			        // consultar as situacoes de ligacao de agua
			        FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			        
			        filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			        filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
			        
			        Collection colecaoLigacaoEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
			        
			        sessao.setAttribute("colecaoSituacaoLigacaoEsgoto", colecaoLigacaoEsgotoSituacao);
			        
			        // preencher situacoes de ligacao agua setados
			        String[] idsSituacaoLigacaoEsgoto = new String[0];
			        if (cobrancaCriterio.getCriteriosSituacaoLigacaoEsgoto() != null &&
			        		!cobrancaCriterio.getCriteriosSituacaoLigacaoEsgoto().isEmpty()){
			        	idsSituacaoLigacaoEsgoto = new String[cobrancaCriterio
			        	    .getCriteriosSituacaoLigacaoEsgoto().size()];
			        	int i = 0;
				        for (Iterator iter = cobrancaCriterio.getCriteriosSituacaoLigacaoEsgoto().iterator(); 
				        		iter.hasNext();) {
				        	CriterioSituacaoLigacaoEsgoto critSitLigEsgoto = (CriterioSituacaoLigacaoEsgoto) iter.next();
				        	idsSituacaoLigacaoEsgoto[i++] = critSitLigEsgoto.getComp_id().getLigacaoEsgotoSituacao().getId() + "";					
				        }
			        }
			        criterioCobrancaActionForm.setIdsSituacaoLigacaoEsgoto(idsSituacaoLigacaoEsgoto);
			        
			        Collection colecaoResolucaoDiretoria = this.getResolucaoDiretoria();
			        sessao.setAttribute("colecaoResolucaoDiretoria", colecaoResolucaoDiretoria);
			        //Seta a resolucao diretoria
			        if(cobrancaCriterio.getResolucaoDiretoria() != null){
			        	
			        	criterioCobrancaActionForm.setIdResolucaoDiretoria(
			        			cobrancaCriterio.getResolucaoDiretoria().getId().toString());
			        }
			        
				}
			}
			sessao.setAttribute("cobrancaCriterio", cobrancaCriterio);
		}

		return retorno;
	}
	/**
	 * Retorna uma colecao com as resoluções de dirtoria  com indicador de 
	 * utilização livre igual a 1 e data de fim de vigencia nullo ou maior que a data atual;
	 * 
	 * @author Erivan
	 * @return Collection
	 * */
	private Collection getResolucaoDiretoria(){
		Collection colecaoResolucaoDiretoria = new ArrayList();
        
        FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
        filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_UTILIZACAO_LIVRE, 1));
        filtroResolucaoDiretoria.adicionarParametro(new ParametroNulo(FiltroResolucaoDiretoria.DATA_VIGENCIA_FIM));
        colecaoResolucaoDiretoria = Fachada.getInstancia().pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());
        
        if(colecaoResolucaoDiretoria == null ){
        	colecaoResolucaoDiretoria = new ArrayList();
        }
        filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
        filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_UTILIZACAO_LIVRE, 1));
        filtroResolucaoDiretoria.adicionarParametro(new MaiorQue(FiltroResolucaoDiretoria.DATA_VIGENCIA_FIM, new Date()));
        Collection colecaoRD = Fachada.getInstancia().pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());
        
        if(colecaoRD != null && !colecaoRD.isEmpty()){
        	colecaoResolucaoDiretoria.addAll(colecaoRD);
        }
        
        return colecaoResolucaoDiretoria;
	}
}

package gsan.gui.atendimentopublico.ordemservico;

import gsan.atendimentopublico.registroatendimento.AtendimentoMotivoEncAcaoCobranca;
import gsan.atendimentopublico.registroatendimento.AtendimentoMotivoEncAcaoCobrancaPK;
import gsan.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gsan.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gsan.cobranca.CobrancaAcao;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1246] Informar Motivo de Encerramento dos Atendimentos
 * 
 * Action responsável pelo processamento dos dados informados na tela de Informar Motivo de Encerramento dos Atendimentos
 * 
 * @author Nathalia Santos
 * @date 26/10/2011
 */

public class InformarMotivoEncerramentoAtendimentoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		// Seta Retorno (Forward = Sucesso)
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// Fachada
		Fachada fachada = Fachada.getInstancia();
		// Sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		List<AtendimentoMotivoEncAcaoCobranca> colecaoAtendimentoMotivoEncAcaoCobranca = new ArrayList();
		List<AtendimentoMotivoEncAcaoCobranca> colecaoAtendimentoMotivoEncAcaoCobrancaRemover = new ArrayList();
		
		if(sessao.getAttribute("colecaoAtendimentoMotivoEncAcaoCobranca") != null
				&& !sessao.getAttribute("colecaoAtendimentoMotivoEncAcaoCobranca").equals("")){
			
			colecaoAtendimentoMotivoEncAcaoCobranca = (ArrayList) 
    			sessao.getAttribute("colecaoAtendimentoMotivoEncAcaoCobranca");
		
    	}
		
		if(sessao.getAttribute("colecaoAtendimentoMotivoEncAcaoCobrancaRemover") != null
				&& !sessao.getAttribute("colecaoAtendimentoMotivoEncAcaoCobrancaRemover").equals("")){
			
			colecaoAtendimentoMotivoEncAcaoCobrancaRemover = (ArrayList) 
    			sessao.getAttribute("colecaoAtendimentoMotivoEncAcaoCobrancaRemover");
			
    	}
		
		//remover os registros
				if (colecaoAtendimentoMotivoEncAcaoCobrancaRemover != null
						&& !colecaoAtendimentoMotivoEncAcaoCobrancaRemover.isEmpty()) {
					Iterator iterator = colecaoAtendimentoMotivoEncAcaoCobrancaRemover.iterator();
					
					while (iterator.hasNext()) {
						AtendimentoMotivoEncAcaoCobranca atendimentoMotivoEncAcaoCobranca = (AtendimentoMotivoEncAcaoCobranca) iterator.next();
						fachada.remover(atendimentoMotivoEncAcaoCobranca);
					}
				}
				
				//inserir os novos registros
				if (colecaoAtendimentoMotivoEncAcaoCobrancaRemover != null
						&& !colecaoAtendimentoMotivoEncAcaoCobrancaRemover.isEmpty()) {
					Iterator iterator = colecaoAtendimentoMotivoEncAcaoCobrancaRemover.iterator();
					
					while (iterator.hasNext()) {
						AtendimentoMotivoEncAcaoCobranca atendimentoMotivoEncAcaoCobranca = (AtendimentoMotivoEncAcaoCobranca) iterator.next();
						
						if (atendimentoMotivoEncAcaoCobranca.getUltimaAlteracao() == null) {
							atendimentoMotivoEncAcaoCobranca.setUltimaAlteracao(new Date());
							fachada.inserir(atendimentoMotivoEncAcaoCobranca);
						}
						
					}
				}
				
				// Form
				InformarMotivoEncerramentoAtendimentoActionForm informarMotivoEncerramentoAtendimentoActionForm = (InformarMotivoEncerramentoAtendimentoActionForm) actionForm;
				
				FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
				filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(
						FiltroAtendimentoMotivoEncerramento.ID, informarMotivoEncerramentoAtendimentoActionForm.getIdMotivoEncerramento()));

				Collection<AtendimentoMotivoEncerramento> colecaoAtendimentoMotivoEncerramento = 
					this.getFachada().pesquisar(
							filtroAtendimentoMotivoEncerramento, 
							AtendimentoMotivoEncerramento.class.getName());

				AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento)Util.retonarObjetoDeColecao(colecaoAtendimentoMotivoEncerramento);
			
				String descricaoAbreviada = null;
				if (informarMotivoEncerramentoAtendimentoActionForm.getDescricaoAbreviada() != null
					&& !informarMotivoEncerramentoAtendimentoActionForm.getDescricaoAbreviada().trim().equals("")){
					descricaoAbreviada = informarMotivoEncerramentoAtendimentoActionForm.getDescricaoAbreviada();
				}
				
				Short indicadorExecucao = null;
				if (informarMotivoEncerramentoAtendimentoActionForm.getIndicadorExecucao() != null
						&& !informarMotivoEncerramentoAtendimentoActionForm.getIndicadorExecucao().equals("")){
					indicadorExecucao = informarMotivoEncerramentoAtendimentoActionForm.getIndicadorExecucao();
				}
				
				Short indicadorDuplicidade = null;
				if (informarMotivoEncerramentoAtendimentoActionForm.getIndicadorDuplicidade() != null
						&& !informarMotivoEncerramentoAtendimentoActionForm.getIndicadorDuplicidade().equals("")){
					indicadorDuplicidade = informarMotivoEncerramentoAtendimentoActionForm.getIndicadorDuplicidade();
				}
				
				Short indicadorFiscalizacao = null;
				if (informarMotivoEncerramentoAtendimentoActionForm.getIndicadorFiscalizacao() != null
						&& !informarMotivoEncerramentoAtendimentoActionForm.getIndicadorFiscalizacao().equals("")){
					indicadorFiscalizacao = informarMotivoEncerramentoAtendimentoActionForm.getIndicadorFiscalizacao();
				}
				
				Short indicadorVisitaRealizada= null;
				if (informarMotivoEncerramentoAtendimentoActionForm.getIndicadorVisitaRealizada() != null
						&& !informarMotivoEncerramentoAtendimentoActionForm.getIndicadorVisitaRealizada().equals("")){
					indicadorVisitaRealizada = informarMotivoEncerramentoAtendimentoActionForm.getIndicadorVisitaRealizada();
				}
		
				
				Integer qtdeDiasAditivoPrazo = null;
				if (informarMotivoEncerramentoAtendimentoActionForm.getQtdeDiasAditivoPrazo() != null
						&& !informarMotivoEncerramentoAtendimentoActionForm.getQtdeDiasAditivoPrazo().equals("")){
					qtdeDiasAditivoPrazo = new Integer (informarMotivoEncerramentoAtendimentoActionForm.getQtdeDiasAditivoPrazo());
				}
				
				atendimentoMotivoEncerramento.setDescricaoAbreviada(descricaoAbreviada);
				atendimentoMotivoEncerramento.setIndicadorExecucao(indicadorExecucao);
				atendimentoMotivoEncerramento.setIndicadorDuplicidade(indicadorDuplicidade);
				atendimentoMotivoEncerramento.setIndicadorFiscalizacao(indicadorFiscalizacao);
				atendimentoMotivoEncerramento.setIndicadorVisitaRealizada(indicadorVisitaRealizada);
				atendimentoMotivoEncerramento.setQtdeDiasAditivoPrazo(qtdeDiasAditivoPrazo);
				
				atendimentoMotivoEncerramento.setUltimaAlteracao(new Date());
				
				fachada.atualizarAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento, colecaoAtendimentoMotivoEncAcaoCobranca);
				
		montarPaginaSucesso(httpServletRequest,
				"Motivo de encerramento dos atendimentos informado com sucesso",
				"Informar outro Motivo de Encerramento dos Atendimentos",
				"exibirInformarMotivoEncerramentoAtendimentoAction.do?menu=sim");
		return retorno;
	}
}
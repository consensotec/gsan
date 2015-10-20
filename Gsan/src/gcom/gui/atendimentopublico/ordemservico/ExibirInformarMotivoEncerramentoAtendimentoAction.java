package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncAcaoCobranca;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncAcaoCobranca;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInformarMotivoEncerramentoAtendimentoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("informarMotivoEncerramentoAtendimento");

		Fachada fachada = Fachada.getInstancia();
		
		InformarMotivoEncerramentoAtendimentoActionForm informarMotivoEncerramentoAtendimentoActionForm = 
				(InformarMotivoEncerramentoAtendimentoActionForm) actionForm;
		
	
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
		
		
		if (httpServletRequest.getParameter("menu") != null && 
				!httpServletRequest.getParameter("menu").trim().equals("")) {
			
			// Coleção Motivo Encerramento
			FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
			filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(
					FiltroAtendimentoMotivoEncerramento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroAtendimentoMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);

			Collection<AtendimentoMotivoEncerramento> colecaoAtendimentoMotivoEncerramento = 
				this.getFachada().pesquisar(
						filtroAtendimentoMotivoEncerramento, 
						AtendimentoMotivoEncerramento.class.getName());

			sessao.setAttribute("colecaoAtendimentoMotivoEncerramento", colecaoAtendimentoMotivoEncerramento);
			
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento)Util.retonarObjetoDeColecao(colecaoAtendimentoMotivoEncerramento);
			
			
			informarMotivoEncerramentoAtendimentoActionForm.setIdMotivoEncerramento("");
		
			informarMotivoEncerramentoAtendimentoActionForm.setDescricaoAbreviada(atendimentoMotivoEncerramento.getDescricaoAbreviada());
			informarMotivoEncerramentoAtendimentoActionForm.setIndicadorExecucao(atendimentoMotivoEncerramento.getIndicadorExecucao());
			informarMotivoEncerramentoAtendimentoActionForm.setIndicadorDuplicidade(atendimentoMotivoEncerramento.getIndicadorDuplicidade());
			informarMotivoEncerramentoAtendimentoActionForm.setIndicadorFiscalizacao(atendimentoMotivoEncerramento.getIndicadorFiscalizacao());
			informarMotivoEncerramentoAtendimentoActionForm.setIndicadorVisitaRealizada(atendimentoMotivoEncerramento.getIndicadorVisitaRealizada());
			if (atendimentoMotivoEncerramento.getQtdeDiasAditivoPrazo() !=  null){
				informarMotivoEncerramentoAtendimentoActionForm.setQtdeDiasAditivoPrazo(atendimentoMotivoEncerramento.getQtdeDiasAditivoPrazo().toString());
			}
		}
		
		// Consultar os dados das associaoes de uma Situação de Cobrança armazenadas na tabela EspecificacaoUnidadeCobranca
		if (httpServletRequest.getParameter("recuperarAssociacoes") != null
				&& httpServletRequest.getParameter("recuperarAssociacoes").trim().equalsIgnoreCase("SIM")
				&& informarMotivoEncerramentoAtendimentoActionForm.getIdMotivoEncerramento() != null
				&& !informarMotivoEncerramentoAtendimentoActionForm.getIdMotivoEncerramento().trim().equals("")
				&& !informarMotivoEncerramentoAtendimentoActionForm.getIdMotivoEncerramento().trim().equals("-1")) {
		
			FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
			filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(
					FiltroAtendimentoMotivoEncerramento.ID, informarMotivoEncerramentoAtendimentoActionForm.getIdMotivoEncerramento()));

			Collection<AtendimentoMotivoEncerramento> colecaoAtendimentoMotivoEncerramento = 
				this.getFachada().pesquisar(
						filtroAtendimentoMotivoEncerramento, 
						AtendimentoMotivoEncerramento.class.getName());
			AtendimentoMotivoEncerramento  atendimentoMotivoEncerramento  = (AtendimentoMotivoEncerramento) 
					Util.retonarObjetoDeColecao(colecaoAtendimentoMotivoEncerramento);

			informarMotivoEncerramentoAtendimentoActionForm.setDescricaoAbreviada(atendimentoMotivoEncerramento.getDescricaoAbreviada());
			informarMotivoEncerramentoAtendimentoActionForm.setIndicadorExecucao(atendimentoMotivoEncerramento.getIndicadorExecucao());
			informarMotivoEncerramentoAtendimentoActionForm.setIndicadorDuplicidade(atendimentoMotivoEncerramento.getIndicadorDuplicidade());
			informarMotivoEncerramentoAtendimentoActionForm.setIndicadorFiscalizacao(atendimentoMotivoEncerramento.getIndicadorFiscalizacao());
			informarMotivoEncerramentoAtendimentoActionForm.setIndicadorVisitaRealizada(atendimentoMotivoEncerramento.getIndicadorVisitaRealizada());
			if (atendimentoMotivoEncerramento.getQtdeDiasAditivoPrazo() !=  null){
				informarMotivoEncerramentoAtendimentoActionForm.setQtdeDiasAditivoPrazo(atendimentoMotivoEncerramento.getQtdeDiasAditivoPrazo().toString());
			}else {
				informarMotivoEncerramentoAtendimentoActionForm.setQtdeDiasAditivoPrazo(null);
			}
			
			FiltroAtendimentoMotivoEncAcaoCobranca filtroAtendimentoMotivoEncAcaoCobranca = new FiltroAtendimentoMotivoEncAcaoCobranca();
			filtroAtendimentoMotivoEncAcaoCobranca.adicionarParametro(new ParametroSimples(
					FiltroAtendimentoMotivoEncAcaoCobranca.COMP_ID_ATENDIMENTO_MOTIVO_ENCERRAMENTO_ID, informarMotivoEncerramentoAtendimentoActionForm.getIdMotivoEncerramento()));
			filtroAtendimentoMotivoEncAcaoCobranca.adicionarCaminhoParaCarregamentoEntidade(
					FiltroAtendimentoMotivoEncAcaoCobranca.COBRANCA_ACAO);
			

			Collection<AtendimentoMotivoEncAcaoCobranca> colecao = fachada.pesquisar(filtroAtendimentoMotivoEncAcaoCobranca, AtendimentoMotivoEncAcaoCobranca.class.getName());
			
			if (colecao != null && !colecao.isEmpty()) {
				colecaoAtendimentoMotivoEncAcaoCobranca = (List) colecao;
				sessao.setAttribute("colecaoAtendimentoMotivoEncAcaoCobranca", colecaoAtendimentoMotivoEncAcaoCobranca);
			} else {
				sessao.removeAttribute("colecaoAtendimentoMotivoEncAcaoCobranca");
			}


			sessao.setAttribute("exibirInformacoesAtendimentoMotivoEnc", true);
			
		}else if ((httpServletRequest.getParameter("retornarPopup") != null
					&& httpServletRequest.getParameter("retornarPopup").trim().equalsIgnoreCase("SIM"))
					 || (httpServletRequest.getParameter("removerAssociacao") != null
								&& !httpServletRequest.getParameter("removerAssociacao").equals(""))){
			sessao.setAttribute("exibirInformacoesAtendimentoMotivoEnc", true);
			
		} else {
			sessao.removeAttribute("exibirInformacoesAtendimentoMotivoEnc");
			
			if (httpServletRequest.getParameter("recuperarAssociacoes") != null
					&& httpServletRequest.getParameter("recuperarAssociacoes").trim().equalsIgnoreCase("SIM")
					&& informarMotivoEncerramentoAtendimentoActionForm.getIdMotivoEncerramento() != null
					&& informarMotivoEncerramentoAtendimentoActionForm.getIdMotivoEncerramento().trim().equals("-1")) {

				sessao.removeAttribute("colecaoAtendimentoMotivoEncAcaoCobranca");
			}
			
		}

		// Remover Atendimento Motivo Encerramento Acao Cobranca
				if (httpServletRequest.getParameter("removerAssociacao") != null
						&& !httpServletRequest.getParameter("removerAssociacao").equals("")) {
					
					Integer indice = new Integer(httpServletRequest.getParameter("removerAssociacao"));
		        	
					if (colecaoAtendimentoMotivoEncAcaoCobranca != null
		        			&& !colecaoAtendimentoMotivoEncAcaoCobranca.isEmpty()
		        			&& colecaoAtendimentoMotivoEncAcaoCobranca.size() >= indice) {
						AtendimentoMotivoEncAcaoCobranca atendimentoMotivoEncAcaoCobranca = colecaoAtendimentoMotivoEncAcaoCobranca.get(indice - 1);
		        		if (atendimentoMotivoEncAcaoCobranca.getUltimaAlteracao() != null) {
		        			colecaoAtendimentoMotivoEncAcaoCobrancaRemover.add(atendimentoMotivoEncAcaoCobranca);
		    				sessao.setAttribute("colecaoAtendimentoMotivoEncAcaoCobrancaRemover", colecaoAtendimentoMotivoEncAcaoCobrancaRemover);
		        		}
		        		
		        		colecaoAtendimentoMotivoEncAcaoCobranca.remove(indice-1);
		        		if (colecaoAtendimentoMotivoEncAcaoCobranca != null
		        				&& !colecaoAtendimentoMotivoEncAcaoCobranca.isEmpty()) {
		        			sessao.setAttribute("colecaoAtendimentoMotivoEncAcaoCobranca", colecaoAtendimentoMotivoEncAcaoCobranca);
		        		} else {
		        			sessao.removeAttribute("colecaoAtendimentoMotivoEncAcaoCobranca");
		        		}
		        	}
		        	
				}


		
		return retorno;
	}
}

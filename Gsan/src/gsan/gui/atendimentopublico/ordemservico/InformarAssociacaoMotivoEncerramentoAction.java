package gsan.gui.atendimentopublico.ordemservico;

import gsan.atendimentopublico.registroatendimento.AtendimentoMotivoEncAcaoCobranca;
import gsan.atendimentopublico.registroatendimento.AtendimentoMotivoEncAcaoCobrancaPK;
import gsan.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gsan.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncAcaoCobranca;
import gsan.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gsan.cobranca.CobrancaAcao;
import gsan.cobranca.FiltroCobrancaAcao;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;
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

public class InformarAssociacaoMotivoEncerramentoAction  extends GcomAction{
	

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ActionForward retorno = actionMapping
				.findForward("exibirInformarAssociacaoMotivoEncerramentoAction");
			
		InformarAssociacaoMotivoEncerramentoActionForm informarAssociacaoMotivoEncerramentoActionForm = (InformarAssociacaoMotivoEncerramentoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		Integer idMotivoEncerramento = null;
		Integer cobrancaAcaoId =  null;
		
		if(informarAssociacaoMotivoEncerramentoActionForm.getCobrancaAcaoId() != null 
				&& !informarAssociacaoMotivoEncerramentoActionForm.getCobrancaAcaoId().trim().equals("")
				&& !informarAssociacaoMotivoEncerramentoActionForm.getCobrancaAcaoId().equals("-1")){
			cobrancaAcaoId = new Integer (informarAssociacaoMotivoEncerramentoActionForm.getCobrancaAcaoId());
		}
		Short indicadorGeraPagamento = null;
		if(informarAssociacaoMotivoEncerramentoActionForm.getIndicadorGeraPagamento() != null
				&& !informarAssociacaoMotivoEncerramentoActionForm.getIndicadorGeraPagamento().trim().equals("")){
			indicadorGeraPagamento = new Short (informarAssociacaoMotivoEncerramentoActionForm.getIndicadorGeraPagamento());
		}
		
		Short indicadorGeraSucessor = null;
		if(informarAssociacaoMotivoEncerramentoActionForm.getIndicadorGeraSucessor() != null
				&& !informarAssociacaoMotivoEncerramentoActionForm.getIndicadorGeraSucessor().trim().equals("")){
			indicadorGeraSucessor = new Short (informarAssociacaoMotivoEncerramentoActionForm.getIndicadorGeraSucessor());
		}
		
		Short indicadorExibeDocumento = null;
		if(informarAssociacaoMotivoEncerramentoActionForm.getIndicadorExibeDocumento() != null
				&& !informarAssociacaoMotivoEncerramentoActionForm.getIndicadorExibeDocumento().trim().equals("")){
			indicadorExibeDocumento = new Short (informarAssociacaoMotivoEncerramentoActionForm.getIndicadorExibeDocumento());
		}
		
		List<AtendimentoMotivoEncAcaoCobranca> colecaoAtendimentoMotivoEncAcaoCobranca = new ArrayList();
		if(sessao.getAttribute("colecaoAtendimentoMotivoEncAcaoCobranca") != null
				&& !sessao.getAttribute("colecaoAtendimentoMotivoEncAcaoCobranca").equals("")){
			
			colecaoAtendimentoMotivoEncAcaoCobranca = (ArrayList) 
    			sessao.getAttribute("colecaoAtendimentoMotivoEncAcaoCobranca");
    	}
		if (informarAssociacaoMotivoEncerramentoActionForm.getIdMotivoEncerramento() != null
				&& !informarAssociacaoMotivoEncerramentoActionForm.getIdMotivoEncerramento().trim().equals("")) {
			idMotivoEncerramento = new Integer(informarAssociacaoMotivoEncerramentoActionForm.getIdMotivoEncerramento());
		} else {
			throw new ActionServletException(
					"atencao.informe.motivo_encerramento", null, "Motivo Encerramento");
		}
		
		if (informarAssociacaoMotivoEncerramentoActionForm.getCobrancaAcaoId() == null
				|| informarAssociacaoMotivoEncerramentoActionForm.getCobrancaAcaoId().trim().equals("")
				|| informarAssociacaoMotivoEncerramentoActionForm.getCobrancaAcaoId().trim().equals("-1")) {
			throw new ActionServletException(
					"atencao.informe.acao_cobranca", null, "Ação Cobrança");
		}
		if (informarAssociacaoMotivoEncerramentoActionForm.getIndicadorGeraPagamento() == null
				|| informarAssociacaoMotivoEncerramentoActionForm.getIndicadorGeraPagamento().trim().equals("")) {
			throw new ActionServletException(
					"atencao.informe.gera_pagamento", null, "Gera Pagamento");
		}
		
		if (informarAssociacaoMotivoEncerramentoActionForm.getIndicadorGeraSucessor() == null
			|| informarAssociacaoMotivoEncerramentoActionForm.getIndicadorGeraSucessor().trim().equals("")) {
			throw new ActionServletException(
					"atencao.informe.gera_sucessor", null, "Gera Sucessor");
		}
		
		if (informarAssociacaoMotivoEncerramentoActionForm.getIndicadorExibeDocumento() == null
				|| informarAssociacaoMotivoEncerramentoActionForm.getIndicadorExibeDocumento().trim().equals("")) {
			throw new ActionServletException(
					"atencao.informe.exibe_documento", null, "Exibe Documento");
		}
		
		AtendimentoMotivoEncAcaoCobranca atendimentoMotivoEncAcaoCobranca = new AtendimentoMotivoEncAcaoCobranca();
		AtendimentoMotivoEncAcaoCobrancaPK comp = new AtendimentoMotivoEncAcaoCobrancaPK();
		comp.setAtendimentoMotivoEncerramentoId(idMotivoEncerramento);
		CobrancaAcao coAcao = new CobrancaAcao();
		coAcao.setId(cobrancaAcaoId);
		comp.setCobrancaAcaoId(cobrancaAcaoId);
		atendimentoMotivoEncAcaoCobranca.setCobrancaAcao(coAcao);
		atendimentoMotivoEncAcaoCobranca.setIndicadorGeraPagamento(indicadorGeraPagamento);
		atendimentoMotivoEncAcaoCobranca.setIndicadorGeraSucessor(indicadorGeraSucessor);
		atendimentoMotivoEncAcaoCobranca.setIndicadorExibeDocumento(indicadorExibeDocumento);
		
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(
				FiltroAtendimentoMotivoEncerramento.ID, idMotivoEncerramento));
		Collection colecaoAtendimentoMotivoEncerramento = fachada.pesquisar(filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());
		
		if (colecaoAtendimentoMotivoEncerramento != null && !colecaoAtendimentoMotivoEncerramento.isEmpty()) {
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento) Util.retonarObjetoDeColecao(colecaoAtendimentoMotivoEncerramento);
			atendimentoMotivoEncAcaoCobranca.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
			comp.setAtendimentoMotivoEncerramentoId(atendimentoMotivoEncerramento.getId());
		} else {
			throw new ActionServletException(
					"atencao.motivo_encerramento.inexistente", null, "Motivo Encerramento");
		}
		
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAcao.ID, cobrancaAcaoId));
		Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
		if (colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()) {
			CobrancaAcao cobrancaAcao = (CobrancaAcao) Util.retonarObjetoDeColecao(colecaoCobrancaAcao);
			atendimentoMotivoEncAcaoCobranca.setCobrancaAcao(cobrancaAcao);
		} else {
			throw new ActionServletException(
					"atencao.informe.acao_cobranca", null, "Cobrança Ação");
		}
		
		if (associacaoExistente(colecaoAtendimentoMotivoEncAcaoCobranca, atendimentoMotivoEncAcaoCobranca)) {
			
			throw new ActionServletException(
					"atencao.associacao.cobranca_acao.ja_existente",
					new String[]{
							atendimentoMotivoEncAcaoCobranca.getAtendimentoMotivoEncerramento().getDescricao(),
							atendimentoMotivoEncAcaoCobranca.getCobrancaAcao().getDescricaoCobrancaAcao()							
							});
			
		} else {
			
			FiltroAtendimentoMotivoEncAcaoCobranca filtroAtendimentoMotivoEncAcaoCobranca = new FiltroAtendimentoMotivoEncAcaoCobranca();
			filtroAtendimentoMotivoEncAcaoCobranca.adicionarParametro(new ParametroSimples(
					FiltroAtendimentoMotivoEncAcaoCobranca.ATENDIMENTO_MOTIVO_ENCERRAMENTO, idMotivoEncerramento));
			filtroAtendimentoMotivoEncAcaoCobranca.adicionarParametro(new ParametroSimples(
					FiltroAtendimentoMotivoEncAcaoCobranca.ID_COBRANCA_ACAO, cobrancaAcaoId));
			filtroAtendimentoMotivoEncAcaoCobranca.adicionarParametro(new ParametroSimples(
					FiltroAtendimentoMotivoEncAcaoCobranca.INDICADOR_GERAR_PAGAMENTO, indicadorGeraPagamento));
			filtroAtendimentoMotivoEncAcaoCobranca.adicionarParametro(new ParametroSimples(
					FiltroAtendimentoMotivoEncAcaoCobranca.INDICADOR_GERAR_SUCESSOR, indicadorGeraSucessor));
			filtroAtendimentoMotivoEncAcaoCobranca.adicionarParametro(new ParametroSimples(
					FiltroAtendimentoMotivoEncAcaoCobranca.INDICADOR_EXIBE_DOCUMENTO, indicadorExibeDocumento));
		
			Collection colecao = fachada.pesquisar(filtroAtendimentoMotivoEncAcaoCobranca, AtendimentoMotivoEncAcaoCobranca.class.getName());
			
			if (colecao != null && !colecao.isEmpty()) {
				
				AtendimentoMotivoEncAcaoCobranca atendimentoMotEncAcaoCobranca = (AtendimentoMotivoEncAcaoCobranca)Util.retonarObjetoDeColecao(colecao);
				atendimentoMotEncAcaoCobranca.setUltimaAlteracao(null);
				colecaoAtendimentoMotivoEncAcaoCobranca.add(atendimentoMotEncAcaoCobranca);
				
			} else {
				atendimentoMotivoEncAcaoCobranca.setComp_id(comp);
				colecaoAtendimentoMotivoEncAcaoCobranca.add(atendimentoMotivoEncAcaoCobranca);
			}
			
			sessao.setAttribute("colecaoAtendimentoMotivoEncAcaoCobranca",colecaoAtendimentoMotivoEncAcaoCobranca);
		
		}
		
		sessao.removeAttribute("colecaoCobrancaAcao");
		informarAssociacaoMotivoEncerramentoActionForm.setIdMotivoEncerramento("");
		informarAssociacaoMotivoEncerramentoActionForm.setCobrancaAcaoId("");
		informarAssociacaoMotivoEncerramentoActionForm.setIndicadorGeraPagamento("");
		informarAssociacaoMotivoEncerramentoActionForm.setIndicadorGeraSucessor("");
		informarAssociacaoMotivoEncerramentoActionForm.setIndicadorExibeDocumento("");
		sessao.setAttribute("fecharPopup", "OK");
		
		return retorno;
		 
	 }
	
	private boolean associacaoExistente(Collection colecaoAtendimentoMotivoEncAcaoCobranca, AtendimentoMotivoEncAcaoCobranca atendimentoMotivoEncAcaoCobranca) {
    	boolean existe = false;
    	
    	if (atendimentoMotivoEncAcaoCobranca.getAtendimentoMotivoEncerramento() != null && atendimentoMotivoEncAcaoCobranca.getAtendimentoMotivoEncerramento().getId() != null
				&& atendimentoMotivoEncAcaoCobranca.getCobrancaAcao() != null && atendimentoMotivoEncAcaoCobranca.getCobrancaAcao().getId() != null
				&& colecaoAtendimentoMotivoEncAcaoCobranca != null && !colecaoAtendimentoMotivoEncAcaoCobranca.isEmpty()) {
    		Iterator iterator = colecaoAtendimentoMotivoEncAcaoCobranca.iterator();
    		
    		while (iterator.hasNext()) {
    			AtendimentoMotivoEncAcaoCobranca atendimentoMotivoEncAcaoCobrancaCol = (AtendimentoMotivoEncAcaoCobranca) iterator.next();
    			
    			if (atendimentoMotivoEncAcaoCobranca.getAtendimentoMotivoEncerramento().getId().equals(atendimentoMotivoEncAcaoCobrancaCol.getAtendimentoMotivoEncerramento().getId())
    					&& atendimentoMotivoEncAcaoCobranca.getCobrancaAcao().getId().equals(atendimentoMotivoEncAcaoCobrancaCol.getCobrancaAcao().getId())) {
    				return true;
    			}
    			
    		}
    	}
    	
    	return existe;
    }

}

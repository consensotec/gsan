package gcom.gui.faturamento;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.LeituraConsumoActionForm;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0153] Apresentar Dados Para An�lise da Medi��o e Consumo
 * [UC0472] Consultar Im�vel
 * 
 * [SB0002] - Atualizar Observa��o
 * Autor: Arthur Carvalho
 * 
 * Data: 10/11/2011
 */
public class AtualizarObservacaoRegistroAtendimentoOrdemServicoAjaxAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
        LeituraConsumoActionForm leituraConsumoActionForm = (LeituraConsumoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		PrintWriter print;
		String observacao  = httpServletRequest.getParameter("observacaoAjax");
		
		try {
			
			if ( observacao != null && !observacao.trim().equals("") ) {
				
				if (existeRegistroAtendimento(leituraConsumoActionForm)) {
	
					//Atualiza o registro de atendimento
					FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
					filtroRegistroAtendimento.adicionarParametro( new ParametroSimples(FiltroRegistroAtendimento.ID, leituraConsumoActionForm.getIdRegistroAtendimento()));
					
					Collection<RegistroAtendimento> colecaoRegistroAtendimento = fachada.pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName());
					
					RegistroAtendimento atendimento = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
					atendimento.setObservacao(observacao);
					
					fachada.atualizar(atendimento);
					
					//Atualiza Ordem de Servico
					FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
					filtroOrdemServico.adicionarParametro( new ParametroSimples(FiltroOrdemServico.ID, leituraConsumoActionForm.getIdOrdemServico()));
					
					Collection<OrdemServico> colecaoOrdemServico = fachada.pesquisar(filtroOrdemServico, OrdemServico.class.getName());
					
					OrdemServico ordemServico = (OrdemServico) Util.retonarObjetoDeColecao(colecaoOrdemServico);
					ordemServico.setObservacao(observacao);
					
					fachada.atualizar(ordemServico);
					
					
					print = httpServletResponse.getWriter();
					print.print("Observa��o atualizada com sucesso!");
				} else {
					print = httpServletResponse.getWriter();
					print.print("Im�vel sem Registro de Atendimento.");
				}
			} else {
				if (existeRegistroAtendimento(leituraConsumoActionForm)) {
					print = httpServletResponse.getWriter();
					print.print("Campo Observa��o � Obrigatorio.");	
				} else {
					print = httpServletResponse.getWriter();
					print.print("Im�vel sem Registro de Atendimento.");
				}
				
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	  return null;
	}
	
	
	private boolean existeRegistroAtendimento(LeituraConsumoActionForm leituraConsumoActionForm){
		
		boolean retorno = false; 
		if (leituraConsumoActionForm.getIdRegistroAtendimento() != null && !leituraConsumoActionForm.getIdRegistroAtendimento().equals("")) {
			retorno = true;
		}
		
		return retorno;
	}
}

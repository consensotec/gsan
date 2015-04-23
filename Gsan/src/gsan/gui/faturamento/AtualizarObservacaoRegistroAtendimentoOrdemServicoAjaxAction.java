package gsan.gui.faturamento;

import gsan.atendimentopublico.ordemservico.FiltroOrdemServico;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.gui.micromedicao.LeituraConsumoActionForm;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0153] Apresentar Dados Para Análise da Medição e Consumo
 * [UC0472] Consultar Imóvel
 * 
 * [SB0002] - Atualizar Observação
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
					print.print("Observação atualizada com sucesso!");
				} else {
					print = httpServletResponse.getWriter();
					print.print("Imóvel sem Registro de Atendimento.");
				}
			} else {
				if (existeRegistroAtendimento(leituraConsumoActionForm)) {
					print = httpServletResponse.getWriter();
					print.print("Campo Observação é Obrigatorio.");	
				} else {
					print = httpServletResponse.getWriter();
					print.print("Imóvel sem Registro de Atendimento.");
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

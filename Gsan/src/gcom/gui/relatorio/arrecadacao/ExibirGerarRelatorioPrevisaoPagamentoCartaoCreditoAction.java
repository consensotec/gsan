package gcom.gui.relatorio.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.cadastro.cliente.Cliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * Gerar Relatório de Previsao/Pagamento do Cartao de Credito
 * [UC1518]
 * 
 * @author Joao Pedro Medeiros
 * @created 13/10/2015
 */
public class ExibirGerarRelatorioPrevisaoPagamentoCartaoCreditoAction extends GcomAction {
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioPrevisaoPagamentoCartaoCreditoAction");		
		
		GerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm gerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm = 
				(GerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm) actionForm;
		
		gerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm.getTipoDeRelatorio();
		
		String dataVencimentoInicial = gerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm.getDataVencimentoInicial();
		String dataVencimentoFinal = gerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm.getDataVencimentoFinal();
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<Cliente> clientesArrecadadoresEncontrados =  fachada.pesquisarCartoes(ArrecadacaoForma.CARTAO_CREDITO);
        sessao.setAttribute("arrecadador", clientesArrecadadoresEncontrados);
		
		if(dataVencimentoFinal != null && !dataVencimentoFinal.equals("")
				&& dataVencimentoInicial != null && !dataVencimentoInicial.equals("")){
			
			Date dtInicial = Util.converteStringParaDate(dataVencimentoInicial);
			Date dtFinal = Util.converteStringParaDate(dataVencimentoFinal);
			
			if(Util.compararData(dtInicial, dtFinal) == 1){				
				throw new ActionServletException("atencao.data.intervalo.invalido", null ,"Data Invalida" );				
			}			
		}		
		
		return retorno;
	}	
}
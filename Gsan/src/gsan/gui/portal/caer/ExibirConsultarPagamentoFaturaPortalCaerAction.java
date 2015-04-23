package gsan.gui.portal.caer;

import gsan.arrecadacao.ArrecadadorContrato;
import gsan.arrecadacao.FiltroArrecadadorContrato;
import gsan.gui.GcomAction;
import gsan.util.filtro.ParametroNaoNulo;
import gsan.util.filtro.ParametroSimplesNotIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Classe responsável por configurar as coleções de pagamento Fatura
 *  para serem exibidas na tela 
 * informacoes_pagar_fatura_portal_caer_consultar.jsp
 * 
 * @author Nathalia Santos
 * @since 24/01/2012
 *
 */
public class ExibirConsultarPagamentoFaturaPortalCaerAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirConsultarPagamentoFaturaPortalCaerAction");
		request.setAttribute("voltarInformacoes", true);
	
		Collection<Integer> colecao =  new ArrayList<Integer>();
		colecao.add(new Integer(0));

		FiltroArrecadadorContrato filtroArrecadadorContrato = new FiltroArrecadadorContrato();
		filtroArrecadadorContrato.adicionarParametro( new ParametroNaoNulo(FiltroArrecadadorContrato.NUMERO_SEQUENCIAL_ARQUIVO_RETORNO_DEBITO_AUTOMATICO));
		filtroArrecadadorContrato.adicionarParametro( new ParametroSimplesNotIn(
				FiltroArrecadadorContrato.NUMERO_SEQUENCIAL_ARQUIVO_RETORNO_DEBITO_AUTOMATICO, colecao));
		filtroArrecadadorContrato.adicionarCaminhoParaCarregamentoEntidade(FiltroArrecadadorContrato.ARRECADADOR);
		filtroArrecadadorContrato.adicionarCaminhoParaCarregamentoEntidade(FiltroArrecadadorContrato.ARRECADADOR_CLIENTE);
		filtroArrecadadorContrato.setCampoOrderBy(FiltroArrecadadorContrato.ARRECADADOR_CODIGO_AGENTE);

		Collection<ArrecadadorContrato> colecaoArrecadadorContrato =  this.getFachada().pesquisar(filtroArrecadadorContrato , 
				ArrecadadorContrato.class.getName());
		
		if (colecaoArrecadadorContrato != null && !colecaoArrecadadorContrato.isEmpty() ) {
			
			Collection<ConsultarPagamentoFaturaPortalCaerHelper> colecaoHelper =   new ArrayList<ConsultarPagamentoFaturaPortalCaerHelper>();
			Iterator iteratorColecao =  colecaoArrecadadorContrato.iterator();
			
			ConsultarPagamentoFaturaPortalCaerHelper helper  = null;
			while(iteratorColecao.hasNext()) {
				
				ArrecadadorContrato arrecadadorContrato = (ArrecadadorContrato) iteratorColecao.next();
				
				helper  = new ConsultarPagamentoFaturaPortalCaerHelper();
				
				helper.setCodigo(arrecadadorContrato.getArrecadador().getCodigoAgente().toString());
				helper.setNome(arrecadadorContrato.getArrecadador().getCliente().getNome());
				
				if(!colecaoHelper.contains(helper)){
					colecaoHelper.add(helper);	
				}
			}
			
			helper  = new ConsultarPagamentoFaturaPortalCaerHelper();
			
			helper.setCodigo("");
			helper.setNome("CORREIOS");
			colecaoHelper.add(helper);
			
			helper  = new ConsultarPagamentoFaturaPortalCaerHelper();
			
			helper.setCodigo("");
			helper.setNome("CASAS LOTÉRICAS");
			colecaoHelper.add(helper);
			
			request.setAttribute("helperPagamentoFatura", colecaoHelper);
		}
		
		
		return retorno;
	}
}
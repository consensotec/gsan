package gcom.gui.portal.caern;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.cliente.Cliente;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0153] Acompanhar Registro Atendimento Loja Virtual
 * 
 * @author Maxwel Moreira
 * @date 18/07/2013
 * 
 */
public class ExibirConsultarImovelPagamentosPortalCaernAction extends GcomAction {
	
	/**
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirConsultarImovelPagamentosPortalCaernAction");
      
        //Obtendo uma instancia da Request
        HttpSession sessao = httpServletRequest.getSession(false);
        
        httpServletRequest.setAttribute("voltarServicos", true);

        Integer idImovelPagamentos = (Integer)  sessao.getAttribute("matricula");

     // 1. O sistema seleciona os pagamentos do imovel 
		//(a partir da tabela PAGAMENTO com IMOV_ID = Id do imovel informado 
		//e demais parametros de selecao informada)

		//pesquisa utilizada pelo do Consultar Pagamento
		Collection<Pagamento> colecaoImoveisPagamentos = 
			this.getFachada().pesquisarPagamentoImovel(idImovelPagamentos.toString().trim(), 
			null, null, null,
			null, null, null,
			null, null, null, 
			null, null, null,
			null, null, null, 
			null, null, null);

		Collection<PagamentoHistorico> colecaoImoveisPagamentosHistorico = 
			this.getFachada().pesquisarPagamentoHistoricoImovel(idImovelPagamentos.toString().trim(), 
				null, null, null,
				null, null, null,
				null, null, null, 
				null, null, null,
				null, null, null, null);

		// Imóvel
		Collection<Pagamento> colecaoPagamentosImovelConta = new ArrayList<Pagamento>();
		
		// Consultar Pagamentos do Imóvel
		if (colecaoImoveisPagamentos != null && !colecaoImoveisPagamentos.isEmpty()) {

			Iterator<Pagamento> colecaoPagamentoIterator = colecaoImoveisPagamentos.iterator();

			// Divide os pagamentos do imóvel pelo tipo de pagamento
			while (colecaoPagamentoIterator.hasNext()) {
				
				Pagamento pagamento = (Pagamento) colecaoPagamentoIterator.next();

				//Caso o pagamento possua uma conta que já foi para historico, 
				//Pesquisa a conta na tabela de conta historico
				if (!pagamento.getDocumentoTipo().getId().equals(DocumentoTipo.DEBITO_A_COBRAR) &&   
					!pagamento.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
					
					colecaoPagamentosImovelConta.add(pagamento);
				}
					
			}

			// Organizar a coleção de Conta
			if (colecaoPagamentosImovelConta != null && !colecaoPagamentosImovelConta.isEmpty()) {
				Collections.sort((List) colecaoPagamentosImovelConta,
						new Comparator() {
							public int compare(Object a, Object b) {
								Integer anoMesReferencia1 = ((Pagamento) a).getAnoMesReferencia();
								Integer anoMesReferencia2 = ((Pagamento) b).getAnoMesReferencia();

								return anoMesReferencia1.compareTo(anoMesReferencia2);
							}
					});
				
				
				httpServletRequest.setAttribute("colecaoPagamentosImovelConta",colecaoPagamentosImovelConta);
			}else{
				httpServletRequest.removeAttribute("colecaoPagamentosImovelConta");
			}
			
		}else{
			sessao.removeAttribute("colecaoPagamentosImovelConta");
		}

	
		// Imovel - Pagamento Historico
		Collection<PagamentoHistorico> colecaoPagamentosHistoricoImovelConta = new ArrayList<PagamentoHistorico> ();
		
		// Consultar Pagamentos do Imovel
		if (colecaoImoveisPagamentosHistorico != null && !colecaoImoveisPagamentosHistorico.isEmpty()) {

			Iterator<PagamentoHistorico> colecaoPagamentoHistoricoIterator = colecaoImoveisPagamentosHistorico.iterator();
			
			// Divide os pagamentos do imovel pelo tipo de pagamento
			while (colecaoPagamentoHistoricoIterator.hasNext()) {
				PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) colecaoPagamentoHistoricoIterator.next();
				
				if (pagamentoHistorico.getAvisoBancario() == null) {
					AvisoBancario avisoBancario = new AvisoBancario();
					Arrecadador arrecadador = new Arrecadador();
					Cliente cliente = new Cliente();
					
					String nomeCliente = this.getFachada().pesquisarNomeAgenteArrecadador(pagamentoHistorico.getId());
					
					if (nomeCliente != null){
						
						cliente.setNome(nomeCliente);
						arrecadador.setCliente(cliente);
						avisoBancario.setArrecadador(arrecadador);
						pagamentoHistorico.setAvisoBancario(avisoBancario);
					}
				}
				if ( !pagamentoHistorico.getDocumentoTipo().getId().equals(DocumentoTipo.DEBITO_A_COBRAR) && 
					!pagamentoHistorico.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
						
					colecaoPagamentosHistoricoImovelConta.add(pagamentoHistorico);
				}
			}
		}

		// Organizar a coleção de Conta
		if (colecaoPagamentosHistoricoImovelConta != null
				&& !colecaoPagamentosHistoricoImovelConta.isEmpty()) {
			
			httpServletRequest.setAttribute("colecaoPagamentosHistoricoImovelConta",colecaoPagamentosHistoricoImovelConta);
		} else{
			httpServletRequest.removeAttribute("colecaoPagamentosHistoricoImovelConta");
        }
		
		if(colecaoImoveisPagamentos == null || colecaoImoveisPagamentos.isEmpty() &&
			colecaoPagamentosHistoricoImovelConta == null || colecaoPagamentosHistoricoImovelConta.isEmpty()){
			httpServletRequest.removeAttribute("voltarServicos");
			httpServletRequest.setAttribute("imovelSemPagamento", true);
			retorno = actionMapping.findForward("imovelSemPagamento");
		}
		
		String ip = httpServletRequest.getRemoteAddr(); 
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.CONSULTAR_PAGAMENTOS, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
		return retorno;
		
	}

}

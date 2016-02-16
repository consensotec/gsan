package gcom.arrecadacao;

import gcom.arrecadacao.bean.GuiaPagamentoHelper;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.arrecadacao.pagamento.ConfirmarPagamentoCartaoCreditoActionForm;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirPagamentoCartaoCreditoAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		 ActionForward retorno = mapping.findForward("telaSucesso");
		 
		 String confirmado = request.getParameter("confirmado");
		
	     ConfirmarPagamentoCartaoCreditoActionForm form = (ConfirmarPagamentoCartaoCreditoActionForm) actionForm;
	     Fachada fachada = Fachada.getInstancia();

	     Integer idClienteArrecadador = Util.converterStringParaInteger(form.getIdClienteArrecadador());
	     Date dataVencimento = Util.converteStringParaDate(form.getDataVencimento());
	     BigDecimal valorCredito = Util.formatarMoedaRealparaBigDecimal(form.getValorCredito());
	     BigDecimal diferenca = Util.formatarMoedaRealparaBigDecimal(form.getDiferencaValorGuiaValorCredito());
	     BigDecimal valorTotalGuias = Util.formatarMoedaRealparaBigDecimal(form.getSomaValorGuias());    
	     BigDecimal valorTarifa =  Util.formatarMoedaRealparaBigDecimal(form.getValorTarifa()); 
	          
	     if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
	        request.setAttribute("caminhoActionConclusao","/gsan/inserirPagamentoCartaoCreditoAction.do");
	        
	        if (valorTotalGuias.compareTo(valorCredito) == 0){
		    	return montarPaginaConfirmacao("atencao.confirmar_inclusao",request, mapping);
    	    }else{
		    	return montarPaginaConfirmacao("atencao.valor_total_guias_difente_valor_informado",request, mapping);
		    }
	     }else{
	    	 confirmado = null;
	     }
	     
	     HttpSession sessao = request.getSession(false);	
	     
	     Collection<GuiaPagamentoHelper> guias = (Collection<GuiaPagamentoHelper>) sessao.getAttribute("colecaoGuiaPagamento"); 
	     Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
	     
	     //Inclui um aviso bancário para o pagamento
	     Integer idAvisoBancario = fachada.inserirAvisoBancario(dataVencimento, valorCredito, valorTotalGuias, valorTarifa, idClienteArrecadador,ArrecadacaoForma.CARTAO_CREDITO);
	    	     
	     StringBuffer pagamentoSucesso = new StringBuffer();
	     
		 for (GuiaPagamentoHelper guiaPagamento : guias) {
			  if (diferenca.compareTo(BigDecimal.ZERO) != 0){
				    // Para cada guia de pagamento inclui uma guia de devolução na tabela GUIA_DEVOLUÇÃO 
					Integer idGuiaDevolucaoInserida = fachada.inserirGuiaDevolucao(guiaPagamento.getGuiaPagamento(), idClienteArrecadador, valorTarifa, usuarioLogado);

					//Para cada guia de devolução é inserido uma devolução na tabela DEVOLUCAO
					Date dataDevolucao = new Date();
					fachada.inserirDevolucaoParaGuiaDevolucao(dataDevolucao, valorTarifa, idAvisoBancario, idGuiaDevolucaoInserida, idClienteArrecadador, guiaPagamento.getGuiaPagamento());
			  }
			  //Realiza o pagamento da guia de pagamento
			  Integer idPagamento = fachada.inserirPagamentoGuiaPagamento(guiaPagamento.getGuiaPagamento(), idAvisoBancario, idClienteArrecadador);
			  
			  pagamentoSucesso.append("Pagamento " + idPagamento + " inserido com sucesso.");
			  pagamentoSucesso.append("<br/>");
			  
		 }
		 
		montarPaginaSucesso(request,pagamentoSucesso.toString(),
									 "Realizar outra  Confirmação Pagamento Cartão de Credito",
									 "exibirConfirmarPagamentoCartaoCreditoAction.do?menu=sim");		 
		 
	    return retorno;
	}
}

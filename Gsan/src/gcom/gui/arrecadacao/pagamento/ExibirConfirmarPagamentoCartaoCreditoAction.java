package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.bean.GuiaPagamentoHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
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
 * Action responsável pela confirmação do pagamento do carão de crédito
 * 
 * @author 	Jean Varela
 * @created	22/09/2015
 */
public class ExibirConfirmarPagamentoCartaoCreditoAction extends GcomAction {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			                     HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward retorno = mapping.findForward("exibirConfirmarPagamentoCartaoCredito");
        ConfirmarPagamentoCartaoCreditoActionForm form = (ConfirmarPagamentoCartaoCreditoActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = request.getSession(false);	
        
		Collection<Cliente> clientesArrecadadoresEncontrados =  fachada.pesquisarCartoes(ArrecadacaoForma.CARTAO_CREDITO);
        sessao.setAttribute("clientesArrecadador", clientesArrecadadoresEncontrados);		     
        String acao =  request.getParameter("acao");
        BigDecimal valorTotalGuias = BigDecimal.ZERO;
        
    	sessao.removeAttribute("existeAvisoBancario");
    	sessao.removeAttribute("colecaoGuiaPagamento");
        
        if (acao != null && acao.equals("Listar")){
        	
        	validarCamposInformados(form);
        	Integer idClienteArrecadador = new Integer(form.getIdClienteArrecadador());
        	Date dataVencimento =  Util.converteStringParaDate(form.getDataVencimento());
        	String valorCredito = form.getValorCredito();
        	Arrecadador arrecadador = fachada.pesquisarArrecadadorCartao(idClienteArrecadador, ArrecadacaoForma.CARTAO_CREDITO);
	        AvisoBancario avisoBancario = fachada.pesquisarAvisoBancario(arrecadador.getId(), ArrecadacaoForma.CARTAO_CREDITO, dataVencimento);
	        
	        if (avisoBancario != null){
	           	Integer quantidadeGuiasPagas = fachada.pesquisarQuantidadeGuiasPagamentoPagas(idClienteArrecadador, dataVencimento);
	        	form.setQuantidadeGuiasPagas(quantidadeGuiasPagas);
	        	form.setValorArrecadacaoCalculado(Util.formatarMoedaReal(avisoBancario.getValorArrecadacaoCalculado()));
	        	form.setValorArrecadacaoInformado(Util.formatarMoedaReal(avisoBancario.getValorArrecadacaoInformado()));
	        	form.setDataLancamento(Util.formatarData(avisoBancario.getDataLancamento()));
		        form.setSomaValorGuias(null);
		        form.setDiferencaValorGuiaValorCredito(null);
	        	form.setPercentualArrecadador(null);
	        	form.setValorTarifa(null);
	        	
	        	//O campo AVBC_VLARRECADACAOCALCULADO da tabela arrecadacao.aviso_bancario
	        	//corresponde ao somatório de todas as guias de pagamento relacionadas ao aviso bancário
	        	valorTotalGuias = avisoBancario.getValorArrecadacaoCalculado();
	        	
	        	sessao.setAttribute("existeAvisoBancario",true);

	        }else{
	        	//Recupera as guias de pagamento do cliente arrecadador com situação atual normal. 
	        	Collection<GuiaPagamentoHelper> guiasPendentes = fachada.pesquisarGuiasPagamentoPendentes(idClienteArrecadador,dataVencimento,DebitoCreditoSituacao.NORMAL);
	        	
		        if (guiasPendentes != null){
		        	valorTotalGuias = getValorTotalGuias(guiasPendentes);
		        	sessao.setAttribute("colecaoGuiaPagamento", guiasPendentes);
		        }else{
			        throw new ActionServletException("atencao.arrecadador_nao_possui_guia_pagamento", null ,idClienteArrecadador.toString());
		        }  
		        
			       //Percentual cobrado pelo cliente arrecadador (ACTF_PCTARIFA da tabela ARRECADADOR_CONTRATO_TAR)
		        BigDecimal percentual = fachada.pesquisarPercentualTarifaArrecadador(arrecadador.getId(),ArrecadacaoForma.CARTAO_CREDITO);	        	
		        BigDecimal valorTarifa = getValorTarifa(valorTotalGuias, percentual);
		        BigDecimal diferenca = getValorDiferenca(Util.formatarMoedaRealparaBigDecimal(valorCredito), valorTotalGuias, valorTarifa);
		        
		        form.setSomaValorGuias(Util.formatarMoedaReal(valorTotalGuias));
		        form.setDiferencaValorGuiaValorCredito(Util.formatarMoedaReal(diferenca));
		        	
		        if (percentual != null){
		        	form.setPercentualArrecadador(Util.formatarMoedaReal(percentual.multiply(new BigDecimal("100"))));
		        	form.setValorTarifa(Util.formatarMoedaReal(valorTarifa));
		        }
	        }
	   }    
		return retorno;
	}


	private void validarCamposInformados(ConfirmarPagamentoCartaoCreditoActionForm form) {
		if(form.getIdClienteArrecadador() == null || form.getIdClienteArrecadador().trim().equals("")){
			throw new ActionServletException("atencao.campo.informado", null ,"Arrecadador ");	
		}
		
		if(form.getDataVencimento() == null || form.getDataVencimento().trim().equals("")){
			throw new ActionServletException("atencao.campo.informado", null ,"Data de Vencimento");	
		}
		
		if(!Util.validarDataValida(form.getDataVencimento(), "dd/MM/yyyy")){
			throw new ActionServletException("atencao.data_vencimento_invalida");
		}
		 
		//Valida se data do vencimento é posterior à data corrente,
		if (Util.compararData(Util.converteStringParaDate(form.getDataVencimento()),new Date()) == 1){
			 throw new ActionServletException("atencao.data_vencimento_posterior_data_atual",Util.formatarData(new Date()));
		 }
		
		if(form.getValorCredito() == null || form.getValorCredito().trim().equals("")){
			throw new ActionServletException("atencao.campo.informado", null ,"Valor do Crédito");	
		}
	}
	
	/*
	 * @author Jean Varela
	 * @date 28/09/2015
	 * 
	 * @param colecaoGuias
	 * @return A soma total das guias de pagamento. 
	 */
	private BigDecimal getValorTotalGuias(Collection<GuiaPagamentoHelper> colecaoGuias){
		
		BigDecimal valorTotalGuias = new BigDecimal(0);
		if (!Util.isVazioOrNulo(colecaoGuias)) {
			Iterator iterator = colecaoGuias.iterator();
			while (iterator.hasNext()) {
				GuiaPagamentoHelper guia = (GuiaPagamentoHelper)iterator.next();
				valorTotalGuias = Util.somaBigDecimal(valorTotalGuias,guia.getValorDebito());
			}
		}
		return valorTotalGuias;
	}
	
	/*
	 * @author Jean Varela
	 * @date 28/09/2015
	 * 
	 * @param valorTotalGuias, percentual
	 * @return O valor da tarifa cobrado pelo serviço.
	 */
    private BigDecimal getValorTarifa(BigDecimal valorTotalGuias, BigDecimal percentual){
    	return (valorTotalGuias.multiply(percentual)).setScale(2,BigDecimal.ROUND_DOWN);
    }
    
    private BigDecimal getValorDiferenca(BigDecimal valorCreditoInformado, BigDecimal valorTotalGuias, BigDecimal valorTarifa){
    	BigDecimal diferencaTotalValorTarifa = valorTotalGuias.subtract(valorTarifa);
    	return valorCreditoInformado.subtract(diferencaTotalValorTarifa);
    }
}

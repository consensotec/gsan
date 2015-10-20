package gcom.cobranca.bean;

import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;

import java.io.Serializable;
import java.util.Collection;

/**
 * Encapsula as informações necessárias para confirmar pagamento(s) por cartão de crédito ou débito
 * 
 * @author Raphael Rossiter
 * @date 08/01/2010
 */
public class IncluirConfirmacaoParcelamentoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Parcelamento parcelamento;
	
	private Collection<ParcelamentoPagamentoCartaoCredito> colecaoParcelamentoPagamentoCartaoCredito;
	
	private Collection<DebitoACobrar> colecaoDebitoACobrar;
	
	private Collection<CreditoARealizar> colecaoCreditoARealizar;
	
	public IncluirConfirmacaoParcelamentoHelper(){}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public Collection<ParcelamentoPagamentoCartaoCredito> getColecaoParcelamentoPagamentoCartaoCredito() {
		return colecaoParcelamentoPagamentoCartaoCredito;
	}

	public void setColecaoParcelamentoPagamentoCartaoCredito(
			Collection<ParcelamentoPagamentoCartaoCredito> colecaoParcelamentoPagamentoCartaoCredito) {
		this.colecaoParcelamentoPagamentoCartaoCredito = colecaoParcelamentoPagamentoCartaoCredito;
	}

	public Collection<DebitoACobrar> getColecaoDebitoACobrar() {
		return colecaoDebitoACobrar;
	}

	public void setColecaoDebitoACobrar(
			Collection<DebitoACobrar> colecaoDebitoACobrar) {
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
	}

	public Collection<CreditoARealizar> getColecaoCreditoARealizar() {
		return colecaoCreditoARealizar;
	}

	public void setColecaoCreditoARealizar(
			Collection<CreditoARealizar> colecaoCreditoARealizar) {
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
	}

}

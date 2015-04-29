package gsan.cobranca.bean;

import gsan.cobranca.parcelamento.Parcelamento;

import java.io.Serializable;
import java.util.Collection;

/**
 * Encapsula as informa��es necess�rias para confirmar pagamento(s) por cart�o de cr�dito ou d�bito
 * 
 * @author Raphael Rossiter
 * @date 08/01/2010
 */
public class IncluirConfirmacaoParcelamentoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Parcelamento parcelamento;
	
	private Collection colecaoParcelamentoPagamentoCartaoCredito;
	
	private Collection colecaoDebitoACobrar;
	
	private Collection colecaoCreditoARealizar;
	
	public IncluirConfirmacaoParcelamentoHelper(){}

	public Collection getColecaoCreditoARealizar() {
		return colecaoCreditoARealizar;
	}

	public void setColecaoCreditoARealizar(Collection colecaoCreditoARealizar) {
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
	}

	public Collection getColecaoDebitoACobrar() {
		return colecaoDebitoACobrar;
	}

	public void setColecaoDebitoACobrar(Collection colecaoDebitoACobrar) {
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
	}

	public Collection getColecaoParcelamentoPagamentoCartaoCredito() {
		return colecaoParcelamentoPagamentoCartaoCredito;
	}

	public void setColecaoParcelamentoPagamentoCartaoCredito(
			Collection colecaoParcelamentoPagamentoCartaoCredito) {
		this.colecaoParcelamentoPagamentoCartaoCredito = colecaoParcelamentoPagamentoCartaoCredito;
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}
}

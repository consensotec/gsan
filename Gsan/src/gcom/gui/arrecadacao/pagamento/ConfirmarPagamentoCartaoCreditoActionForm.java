package gcom.gui.arrecadacao.pagamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 *  
 * @author Jean Varela
 * @date   22/09/2015 
 */
public class ConfirmarPagamentoCartaoCreditoActionForm extends ValidatorActionForm {

	
	private static final long serialVersionUID = 1L;
	private String dataVencimento;
	private String nomeClienteArrecadador;
	private String valorCredito;
	private String idClienteArrecadador;
	
	private String somaValorGuias;
	private String diferencaValorGuiaValorCredito;
	
	private String percentualArrecadador;
	private String valorTarifa;
	
	private Integer quantidadeGuiasPagas;
	private String dataLancamento;
	private String valorArrecadacaoCalculado;
	private String valorArrecadacaoInformado;
	
	
	public String getDataVencimento() {
		return dataVencimento;
	}
	
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public String getNomeClienteArrecadador() {
		return nomeClienteArrecadador;
	}
	
	public void setNomeClienteArrecadador(String nomeClienteArrecadador) {
		this.nomeClienteArrecadador = nomeClienteArrecadador;
	}
	
	public String getValorCredito() {
		return valorCredito;
	}
	
	public void setValorCredito(String valorCredito) {
		this.valorCredito = valorCredito;
	}

	public String getIdClienteArrecadador() {
		return idClienteArrecadador;
	}

	public void setIdClienteArrecadador(String idClienteArrecadador) {
		this.idClienteArrecadador = idClienteArrecadador;
	}

	public String getSomaValorGuias() {
		return somaValorGuias;
	}

	public void setSomaValorGuias(String somaValorGuias) {
		this.somaValorGuias = somaValorGuias;
	}

	public String getDiferencaValorGuiaValorCredito() {
		return diferencaValorGuiaValorCredito;
	}

	public void setDiferencaValorGuiaValorCredito(
			String diferencaValorGuiaValorCredito) {
		this.diferencaValorGuiaValorCredito = diferencaValorGuiaValorCredito;
	}

	public String getPercentualArrecadador() {
		return percentualArrecadador;
	}

	public void setPercentualArrecadador(String percentualArrecadador) {
		this.percentualArrecadador = percentualArrecadador;
	}

	public String getValorTarifa() {
		return valorTarifa;
	}

	public void setValorTarifa(String valorTarifa) {
		this.valorTarifa = valorTarifa;
	}

	public Integer getQuantidadeGuiasPagas() {
		return quantidadeGuiasPagas;
	}

	public void setQuantidadeGuiasPagas(Integer quantidadeGuiasPagas) {
		this.quantidadeGuiasPagas = quantidadeGuiasPagas;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getValorArrecadacaoCalculado() {
		return valorArrecadacaoCalculado;
	}

	public void setValorArrecadacaoCalculado(String valorArrecadacaoCalculado) {
		this.valorArrecadacaoCalculado = valorArrecadacaoCalculado;
	}

	public String getValorArrecadacaoInformado() {
		return valorArrecadacaoInformado;
	}

	public void setValorArrecadacaoInformado(String valorArrecadacaoInformado) {
		this.valorArrecadacaoInformado = valorArrecadacaoInformado;
	}		
}

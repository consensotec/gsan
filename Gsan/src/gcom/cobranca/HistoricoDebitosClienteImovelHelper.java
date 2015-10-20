package gcom.cobranca;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class HistoricoDebitosClienteImovelHelper {
	
	private ClienteImovel clienteImovel = new ClienteImovel();
	private Collection<ContaValoresHelper> contasClienteImovelHelper = new ArrayList<ContaValoresHelper>();
	private Collection<GuiaPagamentoValoresHelper> guiasPagamentoClienteImovel = new ArrayList<GuiaPagamentoValoresHelper>();
	private Integer totalDocumento = new Integer(0);
	private BigDecimal valorTotalDebitos = new BigDecimal("0.00");;
	
	//Totalização das contas
	private BigDecimal valorTotalConta     = new BigDecimal("0.00");
	private BigDecimal valorTotalAcrescimo = new BigDecimal("0.00");
	private BigDecimal valorTotalAgua      = new BigDecimal("0.00");
	private BigDecimal valorTotalEsgoto    = new BigDecimal("0.00");
	private BigDecimal valorTotalDebito    = new BigDecimal("0.00");
	private BigDecimal valorTotalCredito   = new BigDecimal("0.00");
	private BigDecimal valorTotalImposto   = new BigDecimal("0.00");
	private Integer quantidadeContas	   = new Integer(0);
	
	//Totalização das guias
	private BigDecimal valorTotalGuia     = new BigDecimal("0.00");
	private Integer quantidadeGuias	  = new Integer(0);
	
	public ClienteImovel getClienteImovel() {
		return clienteImovel;
	}
	public void setClienteImovel(ClienteImovel clienteImovel) {
		this.clienteImovel = clienteImovel;
	}
	public Collection<ContaValoresHelper> getContasClienteImovelHelper() {
		return contasClienteImovelHelper;
	}
	public void setContasClienteImovelHelper(Collection<ContaValoresHelper> contasClienteImovelHelper) {
		this.contasClienteImovelHelper = contasClienteImovelHelper;
	}
	public Collection<GuiaPagamentoValoresHelper> getGuiasPagamentoClienteImovel() {
		return guiasPagamentoClienteImovel;
	}
	public void setGuiasPagamentoClienteImovel(
			Collection<GuiaPagamentoValoresHelper> guiasPagamentoClienteImovel) {
		this.guiasPagamentoClienteImovel = guiasPagamentoClienteImovel;
	}
	public Integer getTotalDocumento() {
		return totalDocumento;
	}
	public void setTotalDocumento(Integer totalDocumento) {
		this.totalDocumento = totalDocumento;
	}
	public BigDecimal getValorTotalDebitos() {
		return valorTotalDebitos;
	}
	public void setValorTotalDebitos(BigDecimal valorTotalDebitos) {
		this.valorTotalDebitos = valorTotalDebitos;
	}
	public BigDecimal getValorTotalConta() {
		return valorTotalConta;
	}
	public void setValorTotalConta(BigDecimal valorTotalConta) {
		this.valorTotalConta = valorTotalConta;
	}
	public BigDecimal getValorTotalAcrescimo() {
		return valorTotalAcrescimo;
	}
	public void setValorTotalAcrescimo(BigDecimal valorTotalAcrescimo) {
		this.valorTotalAcrescimo = valorTotalAcrescimo;
	}
	public BigDecimal getValorTotalAgua() {
		return valorTotalAgua;
	}
	public void setValorTotalAgua(BigDecimal valorTotalAgua) {
		this.valorTotalAgua = valorTotalAgua;
	}
	public BigDecimal getValorTotalEsgoto() {
		return valorTotalEsgoto;
	}
	public void setValorTotalEsgoto(BigDecimal valorTotalEsgoto) {
		this.valorTotalEsgoto = valorTotalEsgoto;
	}
	public BigDecimal getValorTotalDebito() {
		return valorTotalDebito;
	}
	public void setValorTotalDebito(BigDecimal valorTotalDebito) {
		this.valorTotalDebito = valorTotalDebito;
	}
	public BigDecimal getValorTotalCredito() {
		return valorTotalCredito;
	}
	public void setValorTotalCredito(BigDecimal valorTotalCredito) {
		this.valorTotalCredito = valorTotalCredito;
	}
	public BigDecimal getValorTotalImposto() {
		return valorTotalImposto;
	}
	public void setValorTotalImposto(BigDecimal valorTotalImposto) {
		this.valorTotalImposto = valorTotalImposto;
	}
	
	public int getSizeCollectionConta(){
		return contasClienteImovelHelper.size();
	}
	
	public int getSizeCollectionGuia(){
		return guiasPagamentoClienteImovel.size();
	}
	public BigDecimal getValorTotalGuia() {
		return valorTotalGuia;
	}
	public void setValorTotalGuia(BigDecimal valorTotalGuia) {
		this.valorTotalGuia = valorTotalGuia;
	}
	public Integer getQuantidadeContas() {
		return quantidadeContas;
	}
	public void setQuantidadeContas(Integer quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}
	public Integer getQuantidadeGuias() {
		return quantidadeGuias;
	}
	public void setQuantidadeGuias(Integer quantidadeGuias) {
		this.quantidadeGuias = quantidadeGuias;
	} 
	
	
	
	
}

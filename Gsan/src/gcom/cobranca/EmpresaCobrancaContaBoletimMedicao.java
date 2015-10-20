package gcom.cobranca;

import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EmpresaCobrancaContaBoletimMedicao implements Serializable{

	/**
	 * @author Raimundo Martins
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;
	private Integer anoMesReferenciaConta;
	private Localidade localidade;
	private Imovel imovel;
	private Integer qtdContasCanceladas;
	private BigDecimal valorTotalContasCanceladas;
	private Integer qtdContasPagasAVista;
	private BigDecimal valorTotalContasPagasAVista;
	private Integer qtdContasParcelado;
	private BigDecimal valorTotalContasParcelado;
	private Date ultimaAlteracao;
	private EmpresaCobrancaFaixa empresaCobrancaFaixa;
	private BigDecimal valorDesconto;
			
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}
	public void setComandoEmpresaCobrancaConta(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}
	public Integer getAnoMesReferenciaConta() {
		return anoMesReferenciaConta;
	}
	public void setAnoMesReferenciaConta(Integer anoMesReferenciaConta) {
		this.anoMesReferenciaConta = anoMesReferenciaConta;
	}
	public Localidade getLocalidade() {
		return localidade;
	}
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public Integer getQtdContasCanceladas() {
		return qtdContasCanceladas;
	}
	public void setQtdContasCanceladas(Integer qtdContasCanceladas) {
		this.qtdContasCanceladas = qtdContasCanceladas;
	}
	public BigDecimal getValorTotalContasCanceladas() {
		return valorTotalContasCanceladas;
	}
	public void setValorTotalContasCanceladas(BigDecimal valorTotalContasCanceladas) {
		this.valorTotalContasCanceladas = valorTotalContasCanceladas;
	}
	public Integer getQtdContasPagasAVista() {
		return qtdContasPagasAVista;
	}
	public void setQtdContasPagasAVista(Integer qtdContasPagasAVista) {
		this.qtdContasPagasAVista = qtdContasPagasAVista;
	}
	public BigDecimal getValorTotalContasPagasAVista() {
		return valorTotalContasPagasAVista;
	}
	public void setValorTotalContasPagasAVista(BigDecimal valorTotalContasPagasAVista) {
		this.valorTotalContasPagasAVista = valorTotalContasPagasAVista;
	}
	public Integer getQtdContasParcelado() {
		return qtdContasParcelado;
	}
	public void setQtdContasParcelado(Integer qtdContasParcelado) {
		this.qtdContasParcelado = qtdContasParcelado;
	}
	public BigDecimal getValorTotalContasParcelado() {
		return valorTotalContasParcelado;
	}
	public void setValorTotalContasParcelado(BigDecimal valorTotalContasParcelado) {
		this.valorTotalContasParcelado = valorTotalContasParcelado;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public EmpresaCobrancaFaixa getEmpresaCobrancaFaixa() {
		return empresaCobrancaFaixa;
	}
	public void setEmpresaCobrancaFaixa(EmpresaCobrancaFaixa empresaCobrancaFaixa) {
		this.empresaCobrancaFaixa = empresaCobrancaFaixa;
	}
	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}	
}

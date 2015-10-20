package gcom.cobranca;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.conta.ContaMotivoCancelamento;

/**
 * Classe de entidade da tabela
 * cobranca.empr_conta_cobr_canc
 * 
 * @author Raimundo Martins
 * */
public class EmpresaContaCobrancaCancelada implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Integer id;	
	private EmpresaCobrancaConta empresaCobrancaConta;
	private Date dataCancelamento;
	private Integer amRefArrecadacao;
	private ContaMotivoCancelamento contaMotivoCancelamento;
	private BigDecimal valorCancelamento;
	private Date dataUltimaAlteracao;
	private Imovel imovel;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public EmpresaCobrancaConta getEmpresaCobrancaConta() {
		return empresaCobrancaConta;
	}
	public void setEmpresaCobrancaConta(EmpresaCobrancaConta empresaCobrancaConta) {
		this.empresaCobrancaConta = empresaCobrancaConta;
	}
	public Date getDataCancelamento() {
		return dataCancelamento;
	}
	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}
	public ContaMotivoCancelamento getContaMotivoCancelamento() {
		return contaMotivoCancelamento;
	}
	public void setContaMotivoCancelamento(ContaMotivoCancelamento contaMotivoCancelamento) {
		this.contaMotivoCancelamento = contaMotivoCancelamento;
	}
	public BigDecimal getValorCancelamento() {
		return valorCancelamento;
	}
	public void setValorCancelamento(BigDecimal valorCancelamento) {
		this.valorCancelamento = valorCancelamento;
	}
	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}
	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public Integer getAmRefArrecadacao() {
		return amRefArrecadacao;
	}
	public void setAmRefArrecadacao(Integer amRefArrecadacao) {
		this.amRefArrecadacao = amRefArrecadacao;
	}
	
}

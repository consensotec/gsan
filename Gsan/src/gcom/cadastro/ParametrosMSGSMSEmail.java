package gcom.cadastro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ParametrosMSGSMSEmail implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer anoMesReferencia;
	private BigDecimal valorMinimoConta;
	private Short indicadorResidencial;
	private Short indicadorComercial;
	private Short indicadorIndustrial;
	private Short indicadorPublico;
	private String descricaoTextoSMS;
	private String descricaoTextoEmail;
	private Date dataRetirada;
	private Date ultimaAlteracao;
	private Integer quantidadeDiasAntesVencimento;
	private Integer quantidadeTentativasEnvio;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	
	public BigDecimal getValorMinimoConta() {
		return valorMinimoConta;
	}
	public void setValorMinimoConta(BigDecimal valorMinimoConta) {
		this.valorMinimoConta = valorMinimoConta;
	}
	
	public Short getIndicadorResidencial() {
		return indicadorResidencial;
	}
	public void setIndicadorResidencial(Short indicadorResidencial) {
		this.indicadorResidencial = indicadorResidencial;
	}
	
	public Short getIndicadorComercial() {
		return indicadorComercial;
	}
	public void setIndicadorComercial(Short indicadorComercial) {
		this.indicadorComercial = indicadorComercial;
	}
	
	public Short getIndicadorIndustrial() {
		return indicadorIndustrial;
	}
	public void setIndicadorIndustrial(Short indicadorIndustrial) {
		this.indicadorIndustrial = indicadorIndustrial;
	}
	
	public Short getIndicadorPublico() {
		return indicadorPublico;
	}
	public void setIndicadorPublico(Short indicadorPublico) {
		this.indicadorPublico = indicadorPublico;
	}
	
	public String getDescricaoTextoSMS() {
		return descricaoTextoSMS;
	}
	public void setDescricaoTextoSMS(String descricaoTextoSMS) {
		this.descricaoTextoSMS = descricaoTextoSMS;
	}
	
	public String getDescricaoTextoEmail() {
		return descricaoTextoEmail;
	}
	public void setDescricaoTextoEmail(String descricaoTextoEmail) {
		this.descricaoTextoEmail = descricaoTextoEmail;
	}
	
	public Date getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public Integer getQuantidadeDiasAntesVencimento() {
		return quantidadeDiasAntesVencimento;
	}
	public void setQuantidadeDiasAntesVencimento(
			Integer quantidadeDiasAntesVencimento) {
		this.quantidadeDiasAntesVencimento = quantidadeDiasAntesVencimento;
	}
	
	public Integer getQuantidadeTentativasEnvio() {
		return quantidadeTentativasEnvio;
	}
	public void setQuantidadeTentativasEnvio(Integer quantidadeTentativasEnvio) {
		this.quantidadeTentativasEnvio = quantidadeTentativasEnvio;
	}
}
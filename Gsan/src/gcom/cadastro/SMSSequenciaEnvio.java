package gcom.cadastro;

import java.io.Serializable;
import java.util.Date;

public class SMSSequenciaEnvio implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer quantidadeInvalidado;
	private Integer quantidadeErroEnvio;
	private Integer quantidadeEnvio;
	private Date ultimaAlteracao;
	private Date dataEnvio;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantidadeInvalidado() {
		return quantidadeInvalidado;
	}
	public void setQuantidadeInvalidado(Integer quantidadeInvalidado) {
		this.quantidadeInvalidado = quantidadeInvalidado;
	}
	public Integer getQuantidadeErroEnvio() {
		return quantidadeErroEnvio;
	}
	public void setQuantidadeErroEnvio(Integer quantidadeErroEnvio) {
		this.quantidadeErroEnvio = quantidadeErroEnvio;
	}
	public Integer getQuantidadeEnvio() {
		return quantidadeEnvio;
	}
	public void setQuantidadeEnvio(Integer quantidadeEnvio) {
		this.quantidadeEnvio = quantidadeEnvio;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public Date getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
}

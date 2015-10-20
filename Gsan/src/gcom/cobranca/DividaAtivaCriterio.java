package gcom.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * [UC 1587] - Gerar Divida Ativa
 * 
 * @author Davi Menezes
 * @date 13/02/2014
 *
 */
public class DividaAtivaCriterio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Date dataMaximaVencimentoConta;
	
	private BigDecimal valorMinimoDebito;
	
	private BigDecimal valorMaximoDebito;
	
	private Integer anoMesArrecadacao;
	
	private Date dataInscricao;
	
	private Short indicadorProcessado;
	
	private Date ultimaAlteracao;
	
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataMaximaVencimentoConta() {
		return dataMaximaVencimentoConta;
	}

	public void setDataMaximaVencimentoConta(Date dataMaximaVencimentoConta) {
		this.dataMaximaVencimentoConta = dataMaximaVencimentoConta;
	}

	public BigDecimal getValorMinimoDebito() {
		return valorMinimoDebito;
	}

	public void setValorMinimoDebito(BigDecimal valorMinimoDebito) {
		this.valorMinimoDebito = valorMinimoDebito;
	}

	public BigDecimal getValorMaximoDebito() {
		return valorMaximoDebito;
	}

	public void setValorMaximoDebito(BigDecimal valorMaximoDebito) {
		this.valorMaximoDebito = valorMaximoDebito;
	}

	public Integer getAnoMesArrecadacao() {
		return anoMesArrecadacao;
	}

	public void setAnoMesArrecadacao(Integer anoMesArrecadacao) {
		this.anoMesArrecadacao = anoMesArrecadacao;
	}

	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public Short getIndicadorProcessado() {
		return indicadorProcessado;
	}

	public void setIndicadorProcessado(Short indicadorProcessado) {
		this.indicadorProcessado = indicadorProcessado;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}

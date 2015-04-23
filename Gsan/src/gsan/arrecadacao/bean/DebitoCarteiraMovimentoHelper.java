package gsan.arrecadacao.bean;

import java.math.BigDecimal;
import java.util.Collection;

public class DebitoCarteiraMovimentoHelper {

	private Integer banco;	
	private String nomeBanco;
	private Integer qtdRegistrosConta;
	private String valorTotalDebito;	
	private Collection colecaoGrupoFaturamento;
	private Integer anoMesReferencia;
	
	public Integer getBanco() {
		return banco;
	}
	
	public void setBanco(Integer banco) {
		this.banco = banco;
	}
	
	public String getNomeBanco() {
		return nomeBanco;
	}
	
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}
	
	public Integer getQtdRegistrosConta() {
		return qtdRegistrosConta;
	}
	
	public void setQtdRegistrosConta(Integer qtdRegistrosConta) {
		this.qtdRegistrosConta = qtdRegistrosConta;
	}
	
	public String getValorTotalDebito() {
		return valorTotalDebito;
	}
	
	public void setValorTotalDebito(String valorTotalDebito) {
		this.valorTotalDebito = valorTotalDebito;
	}

	public Collection getColecaoGrupoFaturamento() {
		return colecaoGrupoFaturamento;
	}

	public void setColecaoGrupoFaturamento(Collection colecaoGrupoFaturamento) {
		this.colecaoGrupoFaturamento = colecaoGrupoFaturamento;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	
}

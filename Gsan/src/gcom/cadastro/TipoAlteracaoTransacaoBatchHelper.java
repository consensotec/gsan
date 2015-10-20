package gcom.cadastro;

import gcom.interceptor.ControleAlteracao;

import java.util.Collection;

@ControleAlteracao()
public class TipoAlteracaoTransacaoBatchHelper {
	
	private Collection<ParametrosTransacaoBatchHelper> colecaoParametrosTransacao;

	private Integer idTabela;
	
	private Integer tipoAlteracao;
	
	public Collection<ParametrosTransacaoBatchHelper> getColecaoParametrosTransacao() {
		return colecaoParametrosTransacao;
	}
	public void setColecaoParametrosTransacao(
			Collection<ParametrosTransacaoBatchHelper> colecaoParametrosTransacao) {
		this.colecaoParametrosTransacao = colecaoParametrosTransacao;
	}
	public Integer getIdTabela() {
		return idTabela;
	}
	public void setIdTabela(Integer idTabela) {
		this.idTabela = idTabela;
	}
	public Integer getTipoAlteracao() {
		return tipoAlteracao;
	}
	public void setTipoAlteracao(Integer tipoAlteracao) {
		this.tipoAlteracao = tipoAlteracao;
	}
}

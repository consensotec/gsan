package gsan.atendimentopublico.ordemservico;

import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.Filtro;

import java.util.Date;

public class FotoSituacaoOrdemServico extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;

	public static final int ENCERRAMENTO_OS = 8;
	
	private Integer id;
	private String descricao;
	private Date ultimaAlteracao;

	/**
	 * Full Constructor
	 * 
	 * @param id - ID da situa��o da foto da ordem de servi�o
	 * @param descricao - Descri��o da situa��o da foto da ordem de servi�o
	 * @param alteracao - �ltima altera��o realizada na situa��o da foto da ordem de servi�o
	 */
	public FotoSituacaoOrdemServico(Integer id, String descricao, Date alteracao){
		this.id = id;
		this.descricao = descricao;
		this.ultimaAlteracao = alteracao;
	}
	
	/**
	 * Minimal Constructor
	 * 
	 * @param descricao - Descri��o da situa��o da foto da ordem de servi�o
	 */
	public FotoSituacaoOrdemServico(String descricao){
		this.descricao = descricao;
	}
	
	/**
	 * Minimal Constructor
	 * 
	 * @param descricao - Descri��o da situa��o da foto da ordem de servi�o
	 */
	public FotoSituacaoOrdemServico(){
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
}
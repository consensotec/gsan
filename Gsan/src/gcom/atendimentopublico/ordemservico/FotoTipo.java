package gcom.atendimentopublico.ordemservico;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class FotoTipo extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private Date ultimaAlteracao;

	public FotoTipo() {
		super();
	}
	
	
	public FotoTipo(Integer id, String descricao, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.ultimaAlteracao = ultimaAlteracao;
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
		String[] retorno = {"fotp_id"};
		return retorno;
	}

}

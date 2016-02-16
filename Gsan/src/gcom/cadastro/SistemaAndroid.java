package gcom.cadastro;

import java.io.Serializable;
import java.util.Date;

public class SistemaAndroid implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int GSANAS = 1;
	public static final int IMPRESSAO_SIMULTANEA = 2;
	public static final int GSANEOS = 4;
	public static final int ATUALIZACAO_CADASTRAL = 5;

	private Integer id;
	private String descricaoSistema;
	private Short indicadorUso;
	private Date ultimaAlteracao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoSistema() {
		return descricaoSistema;
	}

	public void setDescricaoSistema(String descricaoSistema) {
		this.descricaoSistema = descricaoSistema;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
}

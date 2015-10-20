package gcom.gui.micromedicao;

/**
 * 
 * @author Fábio Silva
 * @date 15/01/2015
 */
public enum TipoRateio {
	SEM_RATEIO(1, "Sem Rateio"),
	RATEIO_POSITIVO(2, "Rateio Positivo"),
	RATEIO_NEGATIVO(3, "Rateio Negativo");

	private Integer id;
	private String descricao;

	private TipoRateio(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
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
}

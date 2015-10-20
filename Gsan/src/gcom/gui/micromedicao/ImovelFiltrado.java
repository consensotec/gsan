package gcom.gui.micromedicao;

public class ImovelFiltrado {
	private Integer matricula;
	private String cliente;
	private String endereco;
	private Short idTipoLigacao;

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Short getIdTipoLigacao() {
		return idTipoLigacao;
	}

	public void setIdTipoLigacao(Short idTipoLigacao) {
		this.idTipoLigacao = idTipoLigacao;
	}
}

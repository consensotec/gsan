package gsan.cadastro.endereco.bean;

import gsan.cadastro.endereco.LogradouroBairro;

import java.io.Serializable;

public class ManterLogradouroBairroHelper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LogradouroBairro logradouroBairro;
	private int grauImportancia;
	private String bairro;

	public int getGrauImportancia() {
		return grauImportancia;
	}

	public void setGrauImportancia(int grauImportancia) {
		this.grauImportancia = grauImportancia;
	}

	public LogradouroBairro getLogradouro() {
		return logradouroBairro;
	}

	public void setLogradouro(LogradouroBairro logradouroBairro) {
		this.logradouroBairro = logradouroBairro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
}

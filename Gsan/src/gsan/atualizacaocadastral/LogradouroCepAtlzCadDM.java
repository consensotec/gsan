package gsan.atualizacaocadastral;

import java.util.Date;

import gsan.interceptor.ObjetoGcom;

public class LogradouroCepAtlzCadDM extends ObjetoGcom{

private static final long serialVersionUID = 1L;
	
	private Integer id;
	private CepAtlzCadDM cepAtlzCad;
	private LogradouroAtlzCadDM logradouroAtlzCad;
	private Date ultimaAlteracao;

	public LogradouroCepAtlzCadDM() {
		super();
	}

	public LogradouroCepAtlzCadDM(Integer id, CepAtlzCadDM cepAtlzCad,
			LogradouroAtlzCadDM logradouroAtlzCad, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.cepAtlzCad = cepAtlzCad;
		this.logradouroAtlzCad = logradouroAtlzCad;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CepAtlzCadDM getCepAtlzCad() {
		return cepAtlzCad;
	}

	public void setCepAtlzCad(CepAtlzCadDM cepAtlzCad) {
		this.cepAtlzCad = cepAtlzCad;
	}

	public LogradouroAtlzCadDM getLogradouroAtlzCad() {
		return logradouroAtlzCad;
	}

	public void setLogradouroAtlzCad(LogradouroAtlzCadDM logradouroAtlzCad) {
		this.logradouroAtlzCad = logradouroAtlzCad;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
}

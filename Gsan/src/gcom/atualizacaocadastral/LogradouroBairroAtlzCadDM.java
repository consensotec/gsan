package gcom.atualizacaocadastral;

import java.util.Date;

import gcom.cadastro.geografico.Bairro;
import gcom.interceptor.ObjetoGcom;

public class LogradouroBairroAtlzCadDM extends ObjetoGcom {
	private static final long serialVersionUID = 1L;

    private Integer id;
    private Bairro bairro;
    private LogradouroAtlzCadDM logradouroAtlzCadDM;
    private Date ultimaAlteracao;
      
	public LogradouroBairroAtlzCadDM() {
		super();
	}

	public LogradouroBairroAtlzCadDM(Integer id, Bairro bairro,
			LogradouroAtlzCadDM logradouroAtlzCadDM, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.bairro = bairro;
		this.logradouroAtlzCadDM = logradouroAtlzCadDM;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public LogradouroAtlzCadDM getLogradouroAtlzCadDM() {
		return logradouroAtlzCadDM;
	}

	public void setLogradouroAtlzCadDM(LogradouroAtlzCadDM logradouroAtlzCadDM) {
		this.logradouroAtlzCadDM = logradouroAtlzCadDM;
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

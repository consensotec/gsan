package gcom.atualizacaocadastral;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;

public class CepAtlzCadDM extends ObjetoGcom{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private Integer codigoCep;
	private Date ultimaAlteracao;
	
	
	public CepAtlzCadDM() {
		super();
	}
	
	public CepAtlzCadDM(String id, Integer codigoCep, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.codigoCep = codigoCep;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCodigoCep() {
		return codigoCep;
	}

	public void setCodigoCep(Integer codigoCep) {
		this.codigoCep = codigoCep;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	@Override
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	@Override
	public boolean equals(Object other) {
		if(this == other){
			return true;
		}
		if(!(other instanceof CepAtlzCadDM)){
			return false;
		}
		
		CepAtlzCadDM castOther = (CepAtlzCadDM) other;
		
		boolean ehIgual = (this.getId() == null && castOther.getId() == null) || (this.getId() != null && this.getId().equals(castOther.getId()));
				
				
		return ehIgual;
	}
}

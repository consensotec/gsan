package gcom.atualizacaocadastral.bean;

import gcom.atualizacaocadastral.LogradouroAtlzCadDM;
import gcom.atualizacaocadastral.LogradouroBairroAtlzCadDM;
import gcom.atualizacaocadastral.LogradouroCepAtlzCadDM;

import java.io.Serializable;
import java.util.ArrayList;

public class AtualizacaoCadastralLogradouroHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String idSubstituirLogra;
	private LogradouroAtlzCadDM logradouroAtlzCad;
	private String bairros;
	private ArrayList<LogradouroBairroAtlzCadDM> colecaoLogardouroBairroAtlzCad;
	private ArrayList<LogradouroCepAtlzCadDM> colecaoLogradouroCepAtlzCad;
	
	public String getIdSubstituirLogra() {
		return idSubstituirLogra;
	}
	public void setIdSubstituirLogra(String idSubstituirLogra) {
		this.idSubstituirLogra = idSubstituirLogra;
	}
	public LogradouroAtlzCadDM getLogradouroAtlzCad() {
		return logradouroAtlzCad;
	}
	public void setLogradouroAtlzCad(LogradouroAtlzCadDM logradouroAtlzCad) {
		this.logradouroAtlzCad = logradouroAtlzCad;
	}
	public String getBairros() {
		return bairros;
	}
	public void setBairros(String bairros) {
		this.bairros = bairros;
	}
	public ArrayList<LogradouroBairroAtlzCadDM> getColecaoLogardouroBairroAtlzCad() {
		return colecaoLogardouroBairroAtlzCad;
	}
	public void setColecaoLogardouroBairroAtlzCad(
			ArrayList<LogradouroBairroAtlzCadDM> colecaoLogardouroBairroAtlzCad) {
		this.colecaoLogardouroBairroAtlzCad = colecaoLogardouroBairroAtlzCad;
	}
	public ArrayList<LogradouroCepAtlzCadDM> getColecaoLogradouroCepAtlzCad() {
		return colecaoLogradouroCepAtlzCad;
	}
	public void setColecaoLogradouroCepAtlzCad(
			ArrayList<LogradouroCepAtlzCadDM> colecaoLogradouroCepAtlzCad) {
		this.colecaoLogradouroCepAtlzCad = colecaoLogradouroCepAtlzCad;
	}
}

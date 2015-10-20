package gcom.cadastro;

import java.io.Serializable;
import java.util.Date;
import gcom.cadastro.SistemaAndroid;

public class VersaoSistemasAndroid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String VERSAO_ATUAL = "1.1.7.0";
	
	private Integer id;
	private byte[] arquivoApk; 
	private SistemaAndroid sistemaAndroid;
	private String numeroVersao;
	private Date dataEnvio;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public byte[] getArquivoApk() {
		return arquivoApk;
	}
	public void setArquivoApk(byte[] arquivoApk) {
		this.arquivoApk = arquivoApk;
	}
	public SistemaAndroid getSistemaAndroid() {
		return sistemaAndroid;
	}
	public void setSistemaAndroid(SistemaAndroid sistemaAndroid) {
		this.sistemaAndroid = sistemaAndroid;
	}
	public String getNumeroVersao() {
		return numeroVersao;
	}
	public void setNumeroVersao(String numeroVersao) {
		this.numeroVersao = numeroVersao;
	}
	public Date getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

}

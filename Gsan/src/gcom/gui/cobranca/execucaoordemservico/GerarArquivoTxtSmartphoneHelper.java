package gcom.gui.cobranca.execucaoordemservico;

public class GerarArquivoTxtSmartphoneHelper {
	private Object[] os;
	private String idOs;
	private String idImovel;
	private String inscricaoImovel;
	private String tipoServico;

	public GerarArquivoTxtSmartphoneHelper(Object[] os, String idOs, String idImovel,
			String inscricaoImovel, String tipoServico) {
		super();
		this.os = os;
		this.idOs = idOs;
		this.idImovel = idImovel;
		this.inscricaoImovel = inscricaoImovel;
		this.tipoServico = tipoServico;
	}

	public GerarArquivoTxtSmartphoneHelper() {
	}

	public Object[] getOs() {
		return os;
	}

	public void setOs(Object[] os) {
		this.os = os;
	}

	public String getIdOs() {
		return idOs;
	}

	public void setIdOs(String idOs) {
		this.idOs = idOs;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
}

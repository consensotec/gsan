package gsan.relatorio.cobranca;

import java.io.Serializable;

public class BoletimMedicaoContratoDadosHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String anoMesReferencia;

	private Integer idBoletim;

	private String descricaoContrato;

	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Integer getIdBoletim() {
		return idBoletim;
	}

	public void setIdBoletim(Integer idBoletim) {
		this.idBoletim = idBoletim;
	}

	public String getDescricaoContrato() {
		return descricaoContrato;
	}

	public void setDescricaoContrato(String descricaoContrato) {
		this.descricaoContrato = descricaoContrato;
	}

}

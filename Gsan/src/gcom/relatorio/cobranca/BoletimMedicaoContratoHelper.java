package gcom.relatorio.cobranca;

import java.io.Serializable;

public class BoletimMedicaoContratoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String anoMesReferencia;

	private Integer idBoletim;

	private String descricaoGrupoCobranca;

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

	public String getDescricaoGrupoCobranca() {
		return descricaoGrupoCobranca;
	}

	public void setDescricaoGrupoCobranca(String descricaoGrupoCobranca) {
		this.descricaoGrupoCobranca = descricaoGrupoCobranca;
	}

}

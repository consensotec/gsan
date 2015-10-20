package gcom.relatorio.cobranca;

import java.io.Serializable;

public class RelatorioBoletimPenalidadeHelper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String acao;
	private String justificativa;
	
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
}

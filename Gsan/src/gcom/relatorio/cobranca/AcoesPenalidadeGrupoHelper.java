package gcom.relatorio.cobranca;

import java.io.Serializable;

/**
 * [UC1153] Solicitar Gera��o/Emiss�o Boletim de Medi��o de Cobran�a
 * 
 * @author Raimundo Martins
 *
 * @date 10/11/2011
 * */
public class AcoesPenalidadeGrupoHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idAcao;
	private String acao;
	private String justificativa;
	
	public Integer getIdAcao() {
		return idAcao;
	}
	public void setIdAcao(Integer idAcao) {
		this.idAcao = idAcao;
	}
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

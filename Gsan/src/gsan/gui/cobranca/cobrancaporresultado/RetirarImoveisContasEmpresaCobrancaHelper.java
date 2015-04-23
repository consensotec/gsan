package gsan.gui.cobranca.cobrancaporresultado;

import java.io.Serializable;
import java.util.Date;

public class RetirarImoveisContasEmpresaCobrancaHelper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idComando;
	private String descricaoComando;
	private Date dataExecucao;
	private String imoveisCobranca;
	private String contasCobranca;
	private String idRegistro;
	
	public String getIdComando() {
		return idComando;
	}
	public void setIdComando(String idComando) {
		this.idComando = idComando;
	}
	public String getDescricaoComando() {
		return descricaoComando;
	}
	public void setDescricaoComando(String descricaoComando) {
		this.descricaoComando = descricaoComando;
	}
	public Date getDataExecucao() {
		return dataExecucao;
	}
	public void setDataExecucao(Date dataExcucao) {
		this.dataExecucao = dataExcucao;
	}
	public String getImoveisCobranca() {
		return imoveisCobranca;
	}
	public void setImoveisCobranca(String imoveisCobranca) {
		this.imoveisCobranca = imoveisCobranca;
	}
	public String getContasCobranca() {
		return contasCobranca;
	}
	public void setContasCobranca(String contasCobranca) {
		this.contasCobranca = contasCobranca;
	}
	public String getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}
	
}

package gcom.gerencial.faturamento.bean;

import java.io.Serializable;


/** 
 *
 * @author Carlos Chaves
 * @date 04/01/2013
 */
public class ResumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer imovelId;
	private String endereco;
	
	public ResumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper(
			Integer imovelId, String endereco) {
		super();
		this.imovelId = imovelId;
		this.endereco = endereco;
	}
	
	public Integer getImovelId() {
		return imovelId;
	}
	public void setImovelId(Integer imovelId) {
		this.imovelId = imovelId;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
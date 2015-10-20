package gcom.micromedicao.bean;

import java.io.Serializable;

/**
 * Helper para auxiliar as exibições das fotos na funcionalidade 
 * Consultar Arquivo Texto Roteiro Empresa
 * 
 * @author Davi Menezes
 * @date 31/08/2012
 *
 */
public class ArquivoTextoRoteiroEmpresaFotoHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idFoto;
	
	private Integer matricula;
	
	private Integer sequencial;
	
	private String endereco;

	public Integer getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(Integer idFoto) {
		this.idFoto = idFoto;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}

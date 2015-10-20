package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Davi Menezes
 *
 */
public class ArquivoTextoRetornoClienteFoneVisitaCampo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String dddFone;
	
	private String numeroFone;
	
	private String ramalFone;
	
	private Short indicadorAtualizado;
	
	private Date dataUltimaAlteracao;
	
	private ArquivoTextoRetornoClienteVisitaCampo arquivoTextoRetornoClienteVisitaCampo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDddFone() {
		return dddFone;
	}

	public void setDddFone(String dddFone) {
		this.dddFone = dddFone;
	}

	public String getNumeroFone() {
		return numeroFone;
	}

	public void setNumeroFone(String numeroFone) {
		this.numeroFone = numeroFone;
	}

	public String getRamalFone() {
		return ramalFone;
	}

	public void setRamalFone(String ramalFone) {
		this.ramalFone = ramalFone;
	}

	public Short getIndicadorAtualizado() {
		return indicadorAtualizado;
	}

	public void setIndicadorAtualizado(Short indicadorAtualizado) {
		this.indicadorAtualizado = indicadorAtualizado;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public ArquivoTextoRetornoClienteVisitaCampo getArquivoTextoRetornoClienteVisitaCampo() {
		return arquivoTextoRetornoClienteVisitaCampo;
	}

	public void setArquivoTextoRetornoClienteVisitaCampo(
			ArquivoTextoRetornoClienteVisitaCampo arquivoTextoRetornoClienteVisitaCampo) {
		this.arquivoTextoRetornoClienteVisitaCampo = arquivoTextoRetornoClienteVisitaCampo;
	}
}

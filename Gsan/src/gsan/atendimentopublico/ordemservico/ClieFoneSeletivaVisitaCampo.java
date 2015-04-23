/**
 * 
 */
package gsan.atendimentopublico.ordemservico;

import java.io.Serializable;

/**
 * @author Davi Menezes
 *
 */
public class ClieFoneSeletivaVisitaCampo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String dddFone;
	
	private String numeroFone;
	
	private String ramalFone;
	
	private ClieOsSeletivaVisitaCampo clieOsSeletivaVisitaCampo;

	public ClieOsSeletivaVisitaCampo getClieOsSeletivaVisitaCampo() {
		return clieOsSeletivaVisitaCampo;
	}

	public void setClieOsSeletivaVisitaCampo(
			ClieOsSeletivaVisitaCampo clieOsSeletivaVisitaCampo) {
		this.clieOsSeletivaVisitaCampo = clieOsSeletivaVisitaCampo;
	}

	public String getDddFone() {
		return dddFone;
	}

	public void setDddFone(String dddFone) {
		this.dddFone = dddFone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
}

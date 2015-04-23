/**
 * 
 */
package gsan.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Davi Menezes
 *
 */
public class ArquivoTextoRetornoAcaoVisitaCampo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Short indicadorOsGeradas;
	
	private Date dataUltimaAlteracao;
	
	private ArquivoTextoRetornoVisitaCampo arquivoTextoRetornoVisitaCampo;
	
	private ServicoTipo servicoTipo;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorOsGeradas() {
		return indicadorOsGeradas;
	}

	public void setIndicadorOsGeradas(Short indicadorOsGeradas) {
		this.indicadorOsGeradas = indicadorOsGeradas;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public ArquivoTextoRetornoVisitaCampo getArquivoTextoRetornoVisitaCampo() {
		return arquivoTextoRetornoVisitaCampo;
	}

	public void setArquivoTextoRetornoVisitaCampo(ArquivoTextoRetornoVisitaCampo arquivoTextoRetornoVisitaCampo) {
		this.arquivoTextoRetornoVisitaCampo = arquivoTextoRetornoVisitaCampo;
	}

	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}
}

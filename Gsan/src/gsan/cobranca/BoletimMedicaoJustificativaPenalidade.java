package gsan.cobranca;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe de entidade da tabela
 * cobranca.bol_med_ac_pen_just
 * 
 * @author Raimundo Martins
 * */
public class BoletimMedicaoJustificativaPenalidade  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private CobrancaAcaoCronograma cobrancaAcaoCronograma;
	private String justificativa;
	private Date ultimaAlteracao;
	private CobrancaBoletimMedicao cobrancaBoletimMedicao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public CobrancaAcaoCronograma getCobrancaAcaoCronograma() {
		return cobrancaAcaoCronograma;
	}
	public void setCobrancaAcaoCronograma(CobrancaAcaoCronograma cobrancaAcaoCronograma) {
		this.cobrancaAcaoCronograma = cobrancaAcaoCronograma;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public CobrancaBoletimMedicao getCobrancaBoletimMedicao() {
		return cobrancaBoletimMedicao;
	}
	public void setCobrancaBoletimMedicao(CobrancaBoletimMedicao cobrancaBoletimMedicao) {
		this.cobrancaBoletimMedicao = cobrancaBoletimMedicao;
	}	
}

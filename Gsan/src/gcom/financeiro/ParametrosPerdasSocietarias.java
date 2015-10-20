
package gcom.financeiro;

import java.io.Serializable;
import java.util.Date;

public class ParametrosPerdasSocietarias implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer anoMesReferenciaContabil;
	
	private Integer anoMesReferenciaBaixaInicial;
	
	private Integer anoMesReferenciaBaixaFinal;
	
	private Integer numeroMesesReferenciaInferior;
	
	private Short indicadorGeracaoReal;
	
	private Short indicadorCategoriaResidencial;
	
	private Short indicadorCategoriaComercial;
	
	private Short indicadorCategoriaIndustrial;
	
	private Short indicadorCategoriaPublica;
	
	private Short indicadorEsferaParticular;
	
	private Short indicadorEsferaMunicipal;
	
	private Short indicadorEsferaEstadual;
	
	private Short indicadorEsferaFederal;
	
	private Date ultimaAlteracao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoMesReferenciaContabil() {
		return anoMesReferenciaContabil;
	}

	public void setAnoMesReferenciaContabil(Integer anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}

	public Integer getAnoMesReferenciaBaixaInicial() {
		return anoMesReferenciaBaixaInicial;
	}

	public void setAnoMesReferenciaBaixaInicial(Integer anoMesReferenciaBaixaInicial) {
		this.anoMesReferenciaBaixaInicial = anoMesReferenciaBaixaInicial;
	}

	public Integer getAnoMesReferenciaBaixaFinal() {
		return anoMesReferenciaBaixaFinal;
	}

	public void setAnoMesReferenciaBaixaFinal(Integer anoMesReferenciaBaixaFinal) {
		this.anoMesReferenciaBaixaFinal = anoMesReferenciaBaixaFinal;
	}

	public Integer getNumeroMesesReferenciaInferior() {
		return numeroMesesReferenciaInferior;
	}

	public void setNumeroMesesReferenciaInferior(
			Integer numeroMesesReferenciaInferior) {
		this.numeroMesesReferenciaInferior = numeroMesesReferenciaInferior;
	}

	public Short getIndicadorGeracaoReal() {
		return indicadorGeracaoReal;
	}

	public void setIndicadorGeracaoReal(Short indicadorGeracaoReal) {
		this.indicadorGeracaoReal = indicadorGeracaoReal;
	}

	public Short getIndicadorCategoriaResidencial() {
		return indicadorCategoriaResidencial;
	}

	public void setIndicadorCategoriaResidencial(Short indicadorCategoriaResidencial) {
		this.indicadorCategoriaResidencial = indicadorCategoriaResidencial;
	}

	public Short getIndicadorCategoriaComercial() {
		return indicadorCategoriaComercial;
	}

	public void setIndicadorCategoriaComercial(Short indicadorCategoriaComercial) {
		this.indicadorCategoriaComercial = indicadorCategoriaComercial;
	}

	public Short getIndicadorCategoriaIndustrial() {
		return indicadorCategoriaIndustrial;
	}

	public void setIndicadorCategoriaIndustrial(Short indicadorCategoriaIndustrial) {
		this.indicadorCategoriaIndustrial = indicadorCategoriaIndustrial;
	}

	public Short getIndicadorCategoriaPublica() {
		return indicadorCategoriaPublica;
	}

	public void setIndicadorCategoriaPublica(Short indicadorCategoriaPublica) {
		this.indicadorCategoriaPublica = indicadorCategoriaPublica;
	}

	public Short getIndicadorEsferaParticular() {
		return indicadorEsferaParticular;
	}

	public void setIndicadorEsferaParticular(Short indicadorEsferaParticular) {
		this.indicadorEsferaParticular = indicadorEsferaParticular;
	}

	public Short getIndicadorEsferaMunicipal() {
		return indicadorEsferaMunicipal;
	}

	public void setIndicadorEsferaMunicipal(Short indicadorEsferaMunicipal) {
		this.indicadorEsferaMunicipal = indicadorEsferaMunicipal;
	}

	public Short getIndicadorEsferaEstadual() {
		return indicadorEsferaEstadual;
	}

	public void setIndicadorEsferaEstadual(Short indicadorEsferaEstadual) {
		this.indicadorEsferaEstadual = indicadorEsferaEstadual;
	}

	public Short getIndicadorEsferaFederal() {
		return indicadorEsferaFederal;
	}

	public void setIndicadorEsferaFederal(Short indicadorEsferaFederal) {
		this.indicadorEsferaFederal = indicadorEsferaFederal;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
}

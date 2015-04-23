package gsan.faturamento.conta;

import java.io.Serializable;

public class ContaComunicadoHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String idComunicado;
	private String comunicado;
	private String[] grupoFaturamento;
	private String localidade;
	private String localidadeDescricao;
	private String[] setorComercial;
	private String setorComercialDescricao;
	
	private String gerenciaRegional;
	private String gerenciaRegionalDescricao;
	private String gerenciaRegionalDescricaoAbreviada;
	
	private String tipoPesquisaTitulo;
	
	public String getTipoPesquisaTitulo() {
		return tipoPesquisaTitulo;
	}
	public void setTipoPesquisaTitulo(String tipoPesquisaTitulo) {
		this.tipoPesquisaTitulo = tipoPesquisaTitulo;
	}
	
	private String referencia;
	private String referenciaFinal;
	private String referenciaInicial;
	
	private String[] rota;
	private String[] quadra;
	private String titulo;
	private String abrangencia;
	
	private String icUso;

	public String getComunicado() {
		return comunicado;
	}
	public void setComunicado(String comunicado) {
		this.comunicado = comunicado;
	}
	public String[] getGrupoFaturamento() {
		return grupoFaturamento;
	}
	public void setGrupoFaturamento(String[] grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}
	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}
	public String[] getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(String[] setorComercial) {
		this.setorComercial = setorComercial;
	}
	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}
	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}
	public String[] getRota() {
		return rota;
	}
	public void setRota(String[] rota) {
		this.rota = rota;
	}
	public String[] getQuadra() {
		return quadra;
	}
	public void setQuadra(String[] quadra) {
		this.quadra = quadra;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getIcUso() {
		return icUso;
	}
	public void setIcUso(String icUso) {
		this.icUso = icUso;
	}
	public String getAbrangencia() {
		return abrangencia;
	}
	public void setAbrangencia(String abrangencia) {
		this.abrangencia = abrangencia;
	}
	public String getReferenciaFinal() {
		return referenciaFinal;
	}
	public void setReferenciaFinal(String referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}
	public String getReferenciaInicial() {
		return referenciaInicial;
	}
	public void setReferenciaInicial(String referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getGerenciaRegionalDescricao() {
		return gerenciaRegionalDescricao;
	}
	public void setGerenciaRegionalDescricao(String gerenciaRegionalDescricao) {
		this.gerenciaRegionalDescricao = gerenciaRegionalDescricao;
	}
	public String getGerenciaRegionalDescricaoAbreviada() {
		return gerenciaRegionalDescricaoAbreviada;
	}
	public void setGerenciaRegionalDescricaoAbreviada(String gerenciaRegionalDescricaoAbreviada) {
		this.gerenciaRegionalDescricaoAbreviada = gerenciaRegionalDescricaoAbreviada;
	}
	public String getIdComunicado() {
		return idComunicado;
	}
	public void setIdComunicado(String idComunicado) {
		this.idComunicado = idComunicado;
	}
}

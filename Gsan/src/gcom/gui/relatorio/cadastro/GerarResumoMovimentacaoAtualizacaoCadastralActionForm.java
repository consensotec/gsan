package gcom.gui.relatorio.cadastro;

import org.apache.struts.action.ActionForm;

public class GerarResumoMovimentacaoAtualizacaoCadastralActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String tipoPesquisa;
	private String idEmpresa;
	private String periodoAtualizacaoInicial;
	private String periodoAtualizacaoFinal;
	private String gerenciaRegional;
	private String unidadeNegocio;
	private String localidadeInicial;
	private String nomeLocalidadeInicial;
	private String setorComercialInicial;
	private String nomeSetorComercialInicial;
	private String idSetorComercial;
	private String quadraInicial;
	private String nomeQuadraInicial;
	private String cadastrador;
	private String analista;
	private String tipoInconsistencia;
	
	private Integer[] quadra;
	private Integer[] quadraSelecionados;
	
	private String [] idsRegistro;
	
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getPeriodoAtualizacaoInicial() {
		return periodoAtualizacaoInicial;
	}
	public void setPeriodoAtualizacaoInicial(String periodoAtualizacaoInicial) {
		this.periodoAtualizacaoInicial = periodoAtualizacaoInicial;
	}
	public String getPeriodoAtualizacaoFinal() {
		return periodoAtualizacaoFinal;
	}
	public void setPeriodoAtualizacaoFinal(String periodoAtualizacaoFinal) {
		this.periodoAtualizacaoFinal = periodoAtualizacaoFinal;
	}
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public String getLocalidadeInicial() {
		return localidadeInicial;
	}
	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}
	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}
	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}
	public String getSetorComercialInicial() {
		return setorComercialInicial;
	}
	public void setSetorComercialInicial(String setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}
	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}
	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public String getQuadraInicial() {
		return quadraInicial;
	}
	public void setQuadraInicial(String quadraInicial) {
		this.quadraInicial = quadraInicial;
	}
	public String getCadastrador() {
		return cadastrador;
	}
	public void setCadastrador(String cadastrador) {
		this.cadastrador = cadastrador;
	}
	public String getAnalista() {
		return analista;
	}
	public void setAnalista(String analista) {
		this.analista = analista;
	}
	public String getTipoInconsistencia() {
		return tipoInconsistencia;
	}
	public void setTipoInconsistencia(String tipoInconsistencia) {
		this.tipoInconsistencia = tipoInconsistencia;
	}
	public String[] getIdsRegistro() {
		return idsRegistro;
	}
	public void setIdsRegistro(String[] idsRegistro) {
		this.idsRegistro = idsRegistro;
	}
	public String getNomeQuadraInicial() {
		return nomeQuadraInicial;
	}
	public void setNomeQuadraInicial(String nomeQuadraInicial) {
		this.nomeQuadraInicial = nomeQuadraInicial;
	}
	public Integer[] getQuadra() {
		return quadra;
	}
	public void setQuadra(Integer[] quadra) {
		this.quadra = quadra;
	}
	public Integer[] getQuadraSelecionados() {
		return quadraSelecionados;
	}
	public void setQuadraSelecionados(Integer[] quadraSelecionados) {
		this.quadraSelecionados = quadraSelecionados;
	}
	
	public void reset() {
		this.tipoPesquisa = "";
		this.idEmpresa = "-1";
		this.periodoAtualizacaoInicial = "";
		this.periodoAtualizacaoFinal = "";
		this.gerenciaRegional = "";
		this.localidadeInicial = "";
		this.nomeLocalidadeInicial = "";
		this.setorComercialInicial = "";
		this.nomeSetorComercialInicial = "";
		this.quadraInicial = "";
		this.nomeQuadraInicial = "";
		this.cadastrador = "-1";
		this.analista = "-1";
		this.tipoInconsistencia = "-1";
		this.unidadeNegocio = "-1";
		this.idsRegistro = null;
		this.quadra = null;
		this.quadraSelecionados = null;
		this.idSetorComercial = null;
	}
	
}
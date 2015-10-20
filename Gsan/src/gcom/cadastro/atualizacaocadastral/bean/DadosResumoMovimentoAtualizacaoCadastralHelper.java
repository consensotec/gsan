package gcom.cadastro.atualizacaocadastral.bean;

import java.io.Serializable;

public class DadosResumoMovimentoAtualizacaoCadastralHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nomeRelatorio;
	
	private String idEmpresa;
	private String periodoInicial;
	private String periodoFinal;
	private String idGerenciaRegional;
	private String idUnidade;
	private String idLocalidadeInicial;
	private String idSetorComercialInicial;	
	private String numerosQuadras;
	private String idCadastrador;
	private String idAnalista;
	private String mensagem;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNomeRelatorio() {
		return nomeRelatorio;
	}
	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getPeriodoInicial() {
		return periodoInicial;
	}
	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}
	public String getPeriodoFinal() {
		return periodoFinal;
	}
	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public String getIdUnidade() {
		return idUnidade;
	}
	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}
	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}
	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public String getNumerosQuadras() {
		return numerosQuadras;
	}
	public void setNumerosQuadras(String numerosQuadras) {
		this.numerosQuadras = numerosQuadras;
	}
	public String getIdCadastrador() {
		return idCadastrador;
	}
	public void setIdCadastrador(String idCadastrador) {
		this.idCadastrador = idCadastrador;
	}
	public String getIdAnalista() {
		return idAnalista;
	}
	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getIdSetorComercialInicial() {
		return idSetorComercialInicial;
	}
	public void setIdSetorComercialInicial(String idSetorComercialInicial) {
		this.idSetorComercialInicial = idSetorComercialInicial;
	}
	
	
	
}

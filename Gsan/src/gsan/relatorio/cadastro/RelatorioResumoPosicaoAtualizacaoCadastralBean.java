package gsan.relatorio.cadastro;

import gsan.relatorio.RelatorioBean;

public class RelatorioResumoPosicaoAtualizacaoCadastralBean implements RelatorioBean {
	
	private Integer idLocalidade;
	private String descricaoLocalidade;
	private String codigoSetor;
	private String nomeUsuario;
	private Integer quantidadeImoveisRoteiro;
	private Integer quantidadePreGsan;
	private Integer quantidadeAmbienteII;
	private Integer quantidadeAtualizadosGSAN;
	private Integer quantidadePreGsanAtualizados;
	private Integer quantidadePreGsanIncluidos;
	private Integer quantidadeComInconsistencia;
	private Integer quantidadeSemInconsistencia;
	private Integer quantidadeRetornoCampo;
	private Integer quantidadeRemovido;
	
	private Integer quantidadeImoveisAtualizados;
	private Integer quantidadeImoveisIncluidos;
	private Integer quantidadeImoveisNaLocalidade;
	
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public Integer getQuantidadeImoveisRoteiro() {
		return quantidadeImoveisRoteiro;
	}
	public void setQuantidadeImoveisRoteiro(Integer quantidadeImoveisRoteiro) {
		this.quantidadeImoveisRoteiro = quantidadeImoveisRoteiro;
	}
	public Integer getQuantidadePreGsan() {
		return quantidadePreGsan;
	}
	public void setQuantidadePreGsan(Integer quantidadePreGsan) {
		this.quantidadePreGsan = quantidadePreGsan;
	}
	public Integer getQuantidadeAmbienteII() {
		return quantidadeAmbienteII;
	}
	public void setQuantidadeAmbienteII(Integer quantidadeAmbienteII) {
		this.quantidadeAmbienteII = quantidadeAmbienteII;
	}
	public Integer getQuantidadeAtualizadosGSAN() {
		return quantidadeAtualizadosGSAN;
	}
	public void setQuantidadeAtualizadosGSAN(Integer quantidadeAtualizadosGSAN) {
		this.quantidadeAtualizadosGSAN = quantidadeAtualizadosGSAN;
	}
	public Integer getQuantidadeImoveisAtualizados() {
		return quantidadeImoveisAtualizados;
	}
	public void setQuantidadeImoveisAtualizados(Integer quantidadeImoveisAtualizados) {
		this.quantidadeImoveisAtualizados = quantidadeImoveisAtualizados;
	}
	public Integer getQuantidadeImoveisIncluidos() {
		return quantidadeImoveisIncluidos;
	}
	public void setQuantidadeImoveisIncluidos(Integer quantidadeImoveisIncluidos) {
		this.quantidadeImoveisIncluidos = quantidadeImoveisIncluidos;
	}
	public Integer getQuantidadeImoveisRetorno() {
		return (quantidadeImoveisIncluidos + quantidadeImoveisAtualizados);
	}
	public Integer getQuantidadeImoveisNaLocalidade() {
		return quantidadeImoveisNaLocalidade;
	}
	public void setQuantidadeImoveisNaLocalidade(Integer quantidadeImoveisNaLocalidade) {
		this.quantidadeImoveisNaLocalidade = quantidadeImoveisNaLocalidade;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getCodigoSetor() {
		return codigoSetor;
	}
	public void setCodigoSetor(String codigoSetor) {
		this.codigoSetor = codigoSetor;
	}
	public Integer getQuantidadePreGsanAtualizados() {
		return quantidadePreGsanAtualizados;
	}
	public void setQuantidadePreGsanAtualizados(Integer quantidadePreGsanAtualizados) {
		this.quantidadePreGsanAtualizados = quantidadePreGsanAtualizados;
	}
	public Integer getQuantidadePreGsanIncluidos() {
		return quantidadePreGsanIncluidos;
	}
	public void setQuantidadePreGsanIncluidos(Integer quantidadePreGsanIncluidos) {
		this.quantidadePreGsanIncluidos = quantidadePreGsanIncluidos;
	}
	public Integer getQuantidadeComInconsistencia() {
		return quantidadeComInconsistencia;
	}
	public void setQuantidadeComInconsistencia(Integer quantidadeComInconsistencia) {
		this.quantidadeComInconsistencia = quantidadeComInconsistencia;
	}
	public Integer getQuantidadeSemInconsistencia() {
		return quantidadeSemInconsistencia;
	}
	public void setQuantidadeSemInconsistencia(Integer quantidadeSemInconsistencia) {
		this.quantidadeSemInconsistencia = quantidadeSemInconsistencia;
	}
	public Integer getQuantidadeRetornoCampo() {
		return quantidadeRetornoCampo;
	}
	public void setQuantidadeRetornoCampo(Integer quantidadeRetornoCampo) {
		this.quantidadeRetornoCampo = quantidadeRetornoCampo;
	}
	public Integer getQuantidadeRemovido() {
		return quantidadeRemovido;
	}
	public void setQuantidadeRemovido(Integer quantidadeRemovido) {
		this.quantidadeRemovido = quantidadeRemovido;
	}
	
	
	
	
}
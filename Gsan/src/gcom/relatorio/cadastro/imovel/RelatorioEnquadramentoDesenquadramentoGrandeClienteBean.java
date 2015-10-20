package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

public class RelatorioEnquadramentoDesenquadramentoGrandeClienteBean implements RelatorioBean {
	
	private String inscricao;
	private String matricula;
	private String clienteUsuario;
	private String clienteResponsavel;
	private String rota;
	private String situacaoAguaLigacao;
	private String situacaoEsgotoLigacao;
	private String categoria;
	private String numeroEconomia;
	private String ultimoConsumoAgua;	
	private String ultimaLeitura;
	private String dataInstalacaoHidrometro;
	private String capacidadeHidrometro;
	private String qtdContasAberto;
	private BigDecimal valorDebito;
	private String imovelPerfil;
	private String dataEnquadramento;
	private String dataDesenquadramento;
	private String tipo;
	private String data;
	private String perfilAnterior;
	private String perfilPosterior;
	private String motivo;
	private String gerenciaRegional;
	private String localidade;
	private String unidadeNegocio;
	private String setorComercial;
	private String endereco;
	private String idSetorComercial;
	
	public RelatorioEnquadramentoDesenquadramentoGrandeClienteBean(){
		super();
	}
	
	public RelatorioEnquadramentoDesenquadramentoGrandeClienteBean(String inscricao, String matricula, String clienteUsuario,
				String clienteResponsavel, String rota, String situacaoAguaLigacao, String categoria, String numeroEconomia,
				String ultimoConsumoAgua, String ultimaLeitura, String dataInstalacaoHidrometro, String capacidadeHidrometro,
				String qtdContasAberto, BigDecimal valorDebito, String imovelPerfil, String dataEnquadramento, String dataDesenquadramento){
		
		this.inscricao = inscricao;
		this.matricula = matricula;
		this.clienteUsuario = clienteUsuario;
		this.clienteResponsavel = clienteResponsavel;
		this.rota = rota;
		this.situacaoAguaLigacao = situacaoAguaLigacao;
		this.categoria = categoria;
		this.numeroEconomia = numeroEconomia;
		this.ultimoConsumoAgua = ultimoConsumoAgua;
		this.ultimaLeitura = ultimaLeitura;
		this.dataInstalacaoHidrometro = dataInstalacaoHidrometro;
		this.capacidadeHidrometro = capacidadeHidrometro;
		this.qtdContasAberto = qtdContasAberto;
		this.valorDebito = valorDebito;
		this.imovelPerfil = imovelPerfil;
		this.dataEnquadramento = dataEnquadramento;
		this.dataDesenquadramento = dataDesenquadramento;		
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getClienteUsuario() {
		return clienteUsuario;
	}

	public void setClienteUsuario(String clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
	}

	public String getClienteResponsavel() {
		return clienteResponsavel;
	}

	public void setClienteResponsavel(String clienteResponsavel) {
		this.clienteResponsavel = clienteResponsavel;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getSituacaoAguaLigacao() {
		return situacaoAguaLigacao;
	}

	public void setSituacaoAguaLigacao(String situacaoAguaLigacao) {
		this.situacaoAguaLigacao = situacaoAguaLigacao;
	}

	public String getSituacaoEsgotoLigacao() {
		return situacaoEsgotoLigacao;
	}

	public void setSituacaoEsgotoLigacao(String situacaoEsgotoLigacao) {
		this.situacaoEsgotoLigacao = situacaoEsgotoLigacao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNumeroEconomia() {
		return numeroEconomia;
	}

	public void setNumeroEconomia(String numeroEconomia) {
		this.numeroEconomia = numeroEconomia;
	}

	public String getUltimoConsumoAgua() {
		return ultimoConsumoAgua;
	}

	public void setUltimoConsumoAgua(String ultimoConsumoAgua) {
		this.ultimoConsumoAgua = ultimoConsumoAgua;
	}

	public String getUltimaLeitura() {
		return ultimaLeitura;
	}

	public void setUltimaLeitura(String ultimaLeitura) {
		this.ultimaLeitura = ultimaLeitura;
	}

	public String getDataInstalacaoHidrometro() {
		return dataInstalacaoHidrometro;
	}

	public void setDataInstalacaoHidrometro(String dataInstalacaoHidrometro) {
		this.dataInstalacaoHidrometro = dataInstalacaoHidrometro;
	}

	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}

	public void setCapacidadeHidrometro(String capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}

	public String getQtdContasAberto() {
		return qtdContasAberto;
	}

	public void setQtdContasAberto(String qtdContasAberto) {
		this.qtdContasAberto = qtdContasAberto;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(String imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public String getDataEnquadramento() {
		return dataEnquadramento;
	}

	public void setDataEnquadramento(String dataEnquadramento) {
		this.dataEnquadramento = dataEnquadramento;
	}

	public String getDataDesenquadramento() {
		return dataDesenquadramento;
	}

	public void setDataDesenquadramento(String dataDesenquadramento) {
		this.dataDesenquadramento = dataDesenquadramento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPerfilAnterior() {
		return perfilAnterior;
	}

	public void setPerfilAnterior(String perfilAnterior) {
		this.perfilAnterior = perfilAnterior;
	}

	public String getPerfilPosterior() {
		return perfilPosterior;
	}

	public void setPerfilPosterior(String perfilPosterior) {
		this.perfilPosterior = perfilPosterior;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
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

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
}
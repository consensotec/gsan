package gcom.gui.relatorio.cadastro.imovel;

import org.apache.struts.action.ActionForm;

public class GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String tipoPesquisa;
	private String tipoRelatorioEscolhido;
	private String idMunicipio;
	private String nomeMunicipio;
	private String gerenciaRegional;
	private String unidadeNegocio;
	private String idLocalidade;
	private String nomeLocalidade;
	private String codigoSetorComercial;
	private String nomeSetorComercial;
	private String idQuadra;
	private String nomeQuadra;
	private String rota;
	private String imovelPerfil;
	private String imovelPerfilOrigem;
	private String imovelPerfilDestino;
	private String enquadramentoTipo;
	private String enquadramentoDataInicial;
	private String enquadramentoDataFinal;
	private String desenquadramentoDataInicial;
	private String desenquadramentoDataFinal;
	private String[] categoria;	
	private String[] subcategoria;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String consumoTipo;
	private String consumoAguaIntervaloInicial;
	private String consumoAguaIntervaloFinal;
	private String valorFaturamentoIntervaloInicial;
	private String valorFaturamentoIntervaloFinal;
	private String esferaPoder;
	private String[] hidrometroCapacidade;
	private String[] consumoTarifa;
	//private Integer idFuncionalidadeIniciada;
	
	public String getTipoRelatorioEscolhido() {
		return tipoRelatorioEscolhido;
	}
	public void setTipoRelatorioEscolhido(String tipoRelatorioEscolhido) {
		this.tipoRelatorioEscolhido = tipoRelatorioEscolhido;
	}
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	public String getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
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
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}
	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}
	public String getIdQuadra() {
		return idQuadra;
	}
	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}
	public String getNomeQuadra() {
		return nomeQuadra;
	}
	public void setNomeQuadra(String nomeQuadra) {
		this.nomeQuadra = nomeQuadra;
	}
	public String getRota() {
		return rota;
	}
	public void setRota(String rota) {
		this.rota = rota;
	}	
	public String getImovelPerfil() {
		return imovelPerfil;
	}
	public void setImovelPerfil(String imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}
	public String getImovelPerfilOrigem() {
		return imovelPerfilOrigem;
	}
	public void setImovelPerfilOrigem(String imovelPerfilOrigem) {
		this.imovelPerfilOrigem = imovelPerfilOrigem;
	}
	public String getImovelPerfilDestino() {
		return imovelPerfilDestino;
	}
	public void setImovelPerfilDestino(String imovelPerfilDestino) {
		this.imovelPerfilDestino = imovelPerfilDestino;
	}
	public String getEnquadramentoTipo() {
		return enquadramentoTipo;
	}
	public void setEnquadramentoTipo(String enquadramentoTipo) {
		this.enquadramentoTipo = enquadramentoTipo;
	}
	public String getEnquadramentoDataInicial() {
		return enquadramentoDataInicial;
	}
	public void setEnquadramentoDataInicial(String enquadramentoDataInicial) {
		this.enquadramentoDataInicial = enquadramentoDataInicial;
	}
	public String getEnquadramentoDataFinal() {
		return enquadramentoDataFinal;
	}
	public void setEnquadramentoDataFinal(String enquadramentoDataFinal) {
		this.enquadramentoDataFinal = enquadramentoDataFinal;
	}
	public String getDesenquadramentoDataInicial() {
		return desenquadramentoDataInicial;
	}
	public void setDesenquadramentoDataInicial(String desenquadramentoDataInicial) {
		this.desenquadramentoDataInicial = desenquadramentoDataInicial;
	}
	public String getDesenquadramentoDataFinal() {
		return desenquadramentoDataFinal;
	}
	public void setDesenquadramentoDataFinal(String desenquadramentoDataFinal) {
		this.desenquadramentoDataFinal = desenquadramentoDataFinal;
	}
	public String[] getCategoria() {
		return categoria;
	}
	public void setCategoria(String[] categoria) {
		this.categoria = categoria;
	}	
	public String[] getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(String[] subcategoria) {
		this.subcategoria = subcategoria;
	}
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	public String getConsumoTipo() {
		return consumoTipo;
	}
	public void setConsumoTipo(String consumoTipo) {
		this.consumoTipo = consumoTipo;
	}
	public String getConsumoAguaIntervaloInicial() {
		return consumoAguaIntervaloInicial;
	}
	public void setConsumoAguaIntervaloInicial(String consumoAguaIntervaloInicial) {
		this.consumoAguaIntervaloInicial = consumoAguaIntervaloInicial;
	}
	public String getConsumoAguaIntervaloFinal() {
		return consumoAguaIntervaloFinal;
	}
	public void setConsumoAguaIntervaloFinal(String consumoAguaIntervaloFinal) {
		this.consumoAguaIntervaloFinal = consumoAguaIntervaloFinal;
	}	
	public String getValorFaturamentoIntervaloInicial() {
		return valorFaturamentoIntervaloInicial;
	}
	public void setValorFaturamentoIntervaloInicial(String valorFaturamentoIntervaloInicial) {
		this.valorFaturamentoIntervaloInicial = valorFaturamentoIntervaloInicial;
	}
	public String getValorFaturamentoIntervaloFinal() {
		return valorFaturamentoIntervaloFinal;
	}
	public void setValorFaturamentoIntervaloFinal(String valorFaturamentoIntervaloFinal) {
		this.valorFaturamentoIntervaloFinal = valorFaturamentoIntervaloFinal;
	}
	public String getEsferaPoder() {
		return esferaPoder;
	}
	public void setEsferaPoder(String esferaPoder) {
		this.esferaPoder = esferaPoder;
	}
	public String[] getHidrometroCapacidade() {
		return hidrometroCapacidade;
	}
	public void setHidrometroCapacidade(String[] hidrometroCapacidade) {
		this.hidrometroCapacidade = hidrometroCapacidade;
	}
	public String[] getConsumoTarifa() {
		return consumoTarifa;
	}
	public void setConsumoTarifa(String[] consumoTarifa) {
		this.consumoTarifa = consumoTarifa;
	}	
	

	public void limparForm(){
		this.idMunicipio = "";
		this.nomeMunicipio = "";
		this.gerenciaRegional = "";
		this.unidadeNegocio = "";
		this.idLocalidade = "";
		this.nomeLocalidade = "";
		this.codigoSetorComercial = "";
		this.nomeSetorComercial = "";
		this.idQuadra = "";
		this.nomeQuadra = "";
		this.rota = null;
		this.imovelPerfil = null;
		this.enquadramentoTipo = null;
		this.enquadramentoDataInicial = "";
		this.enquadramentoDataFinal = "";
		this.desenquadramentoDataInicial = "";
		this.desenquadramentoDataFinal = "";
		this.categoria = null;
		this.subcategoria = null;
		this.situacaoLigacaoAgua = "";
		this.situacaoLigacaoEsgoto = "";
		this.consumoTipo = null;
		this.consumoAguaIntervaloInicial = "";
		this.consumoAguaIntervaloFinal = "";
		this.valorFaturamentoIntervaloInicial = "";
		this.valorFaturamentoIntervaloFinal = "";
		this.esferaPoder = "";
		this.hidrometroCapacidade = null;
		this.consumoTarifa = null;
	}
}

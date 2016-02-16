package gcom.gui.mobile.execucaoordemservico;

import gcom.cobranca.CobrancaGrupo;
import gcom.mobile.execucaoordemservico.bean.GerarArquivoTxtSmartphoneHelper;

import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
 *
 * @author Jean Varela
 * @throws ErroRepositorioException 
 * @date   16/11/2015	
 */
public class ConsultarArquivoTextoOSCobrancaSmartphoneActionForm  extends ValidatorActionForm {
	 

	private static final long serialVersionUID = 1L;
	
	private String situacaoTextoLeitura;
	private String grupoCobranca;
	private String descricaoEmpresa;
	private String empresa;
	private String servicoTipo;
	private String[] idsRegistros;
	private String motivoFinalizacao;
	
	private Integer idEmpresa;

	private String idTipoOS;

	private String descricaoTipoOS;

	private Integer[] idsLocalidade;

	private Integer idServicoTipo;

	private String qtdOsSeletiva;

	private String qtdMaxOS;

	private String mesAnoCronograma;

	private Integer idGrupoCobranca;

	private Integer[] idsRota;
	
	private Collection<CobrancaGrupo> colecaoGrupoCobranca;

	private Collection<GerarArquivoTxtSmartphoneHelper> colecaoOS;

	private Integer idComando;

	private String descricaoComandoCorrecaoAnormalidade;
	
	private String dataCobrancaEventualInicial;

	private String dataCobrancaEventualFinal;
	
	private Integer idAgenteComercial;
	
	private Integer tipoFiltro;
	
	public String getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(String servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public String[] getIdsRegistros() {
		return idsRegistros;
	}

	public void setIdsRegistros(String[] idsRegistros) {
		this.idsRegistros = idsRegistros;
	}
	
	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}

	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getSituacaoTextoLeitura() {
		return situacaoTextoLeitura;
	}

	public void setSituacaoTextoLeitura(String situacaoTextoLeitura) {
		this.situacaoTextoLeitura = situacaoTextoLeitura;
	}

	public String getGrupoCobranca() {
		return grupoCobranca;
	}

	public void setGrupoCobranca(String grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}

	public String getMotivoFinalizacao() {
		return motivoFinalizacao;
	}

	public void setMotivoFinalizacao(String motivoFinalizacao) {
		this.motivoFinalizacao = motivoFinalizacao;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdTipoOS() {
		return idTipoOS;
	}

	public void setIdTipoOS(String idTipoOS) {
		this.idTipoOS = idTipoOS;
	}

	public String getDescricaoTipoOS() {
		return descricaoTipoOS;
	}

	public void setDescricaoTipoOS(String descricaoTipoOS) {
		this.descricaoTipoOS = descricaoTipoOS;
	}

	public Integer[] getIdsLocalidade() {
		return idsLocalidade;
	}

	public void setIdsLocalidade(Integer[] idsLocalidade) {
		this.idsLocalidade = idsLocalidade;
	}

	public Integer getIdServicoTipo() {
		return idServicoTipo;
	}

	public void setIdServicoTipo(Integer idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	public String getQtdOsSeletiva() {
		return qtdOsSeletiva;
	}

	public void setQtdOsSeletiva(String qtdOsSeletiva) {
		this.qtdOsSeletiva = qtdOsSeletiva;
	}

	public String getQtdMaxOS() {
		return qtdMaxOS;
	}

	public void setQtdMaxOS(String qtdMaxOS) {
		this.qtdMaxOS = qtdMaxOS;
	}

	public String getMesAnoCronograma() {
		return mesAnoCronograma;
	}

	public void setMesAnoCronograma(String mesAnoCronograma) {
		this.mesAnoCronograma = mesAnoCronograma;
	}

	public Integer getIdGrupoCobranca() {
		return idGrupoCobranca;
	}

	public void setIdGrupoCobranca(Integer idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}

	public Integer[] getIdsRota() {
		return idsRota;
	}

	public void setIdsRota(Integer[] idsRota) {
		this.idsRota = idsRota;
	}

	public Collection<CobrancaGrupo> getColecaoGrupoCobranca() {
		return colecaoGrupoCobranca;
	}

	public void setColecaoGrupoCobranca(Collection<CobrancaGrupo> colecaoGrupoCobranca) {
		this.colecaoGrupoCobranca = colecaoGrupoCobranca;
	}

	public Collection<GerarArquivoTxtSmartphoneHelper> getColecaoOS() {
		return colecaoOS;
	}

	public void setColecaoOS(Collection<GerarArquivoTxtSmartphoneHelper> colecaoOS) {
		this.colecaoOS = colecaoOS;
	}

	public Integer getIdComando() {
		return idComando;
	}

	public void setIdComando(Integer idComando) {
		this.idComando = idComando;
	}

	public String getDescricaoComandoCorrecaoAnormalidade() {
		return descricaoComandoCorrecaoAnormalidade;
	}

	public void setDescricaoComandoCorrecaoAnormalidade(String descricaoComandoCorrecaoAnormalidade) {
		this.descricaoComandoCorrecaoAnormalidade = descricaoComandoCorrecaoAnormalidade;
	}

	public String getDataCobrancaEventualInicial() {
		return dataCobrancaEventualInicial;
	}

	public void setDataCobrancaEventualInicial(String dataCobrancaEventualInicial) {
		this.dataCobrancaEventualInicial = dataCobrancaEventualInicial;
	}

	public String getDataCobrancaEventualFinal() {
		return dataCobrancaEventualFinal;
	}

	public void setDataCobrancaEventualFinal(String dataCobrancaEventualFinal) {
		this.dataCobrancaEventualFinal = dataCobrancaEventualFinal;
	}

	public Integer getIdAgenteComercial() {
		return idAgenteComercial;
	}

	public void setIdAgenteComercial(Integer idAgenteComercial) {
		this.idAgenteComercial = idAgenteComercial;
	}

	public Integer getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(Integer tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}
}
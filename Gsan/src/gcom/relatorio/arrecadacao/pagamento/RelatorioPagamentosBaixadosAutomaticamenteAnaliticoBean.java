package gcom.relatorio.arrecadacao.pagamento;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * [UC 1518] - Gerar Relatório dos Pagamentos Baixados Automaticamente
 * 
 * @author Davi Menezes
 * @date 11/07/2013
 *
 */
public class RelatorioPagamentosBaixadosAutomaticamenteAnaliticoBean implements RelatorioBean {

	private String idImovel;
	
	private String inscricaoImovel;
	
	private String arrecadador;
	
	private String dataPagamento;
	
	private String anoMesReferencia;
	
	private BigDecimal valorDocumento;
	
	private BigDecimal valorPagamento;
	
	private BigDecimal valorDiferenca;
	
	private String tipoDebitoCredito;
	
	private String idGerenciaRegional;
	
	private String idUnidadeNegocio;
	
	private String idLocalidade;
	
	private String codigoSetorComercial;
	
	public RelatorioPagamentosBaixadosAutomaticamenteAnaliticoBean(){
		
	}
	
	public RelatorioPagamentosBaixadosAutomaticamenteAnaliticoBean(String idImovel, String inscricaoImovel, String arrecadador, String dataPagamento, 
			String anoMesReferencia, BigDecimal valorDocumento, BigDecimal valorPagamento, BigDecimal valorDiferenca, String tipoDebitoCredito,
			String idGerenciaRegional, String idUnidadeNegocio, String idLocalidade, String codigoSetorComercial){
		this.idImovel = idImovel;
		this.inscricaoImovel = inscricaoImovel;
		this.arrecadador = arrecadador;
		this.dataPagamento = dataPagamento;
		this.anoMesReferencia = anoMesReferencia;
		this.valorDocumento = valorDocumento;
		this.valorPagamento = valorPagamento;
		this.valorDiferenca = valorDiferenca;
		this.tipoDebitoCredito = tipoDebitoCredito;
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idLocalidade = idLocalidade;
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getArrecadador() {
		return arrecadador;
	}

	public void setArrecadador(String arrecadador) {
		this.arrecadador = arrecadador;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public BigDecimal getValorDiferenca() {
		return valorDiferenca;
	}

	public void setValorDiferenca(BigDecimal valorDiferenca) {
		this.valorDiferenca = valorDiferenca;
	}

	public String getTipoDebitoCredito() {
		return tipoDebitoCredito;
	}

	public void setTipoDebitoCredito(String tipoDebitoCredito) {
		this.tipoDebitoCredito = tipoDebitoCredito;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	
}

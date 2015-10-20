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
public class RelatorioPagamentosBaixadosAutomaticamenteSinteticoBean implements RelatorioBean {

	private String idGerenciaRegional;
	
	private String descricaoGerenciaRegional;
	
	private String idUnidadeNegocio;
	
	private String descricaoUnidadeNegocio;
	
	private String idLocalidade;
	
	private String descricaoLocalidade;
	
	private String idSetorComercial;
	
	private String descricaoSetorComercial;
	
	private BigDecimal valorDocumento;
	
	private BigDecimal valorPagamento;
	
	private BigDecimal valorDiferenca;
	
	private String tipoDebitoCredito;
	
	public RelatorioPagamentosBaixadosAutomaticamenteSinteticoBean(){
		
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getDescricaoGerenciaRegional() {
		return descricaoGerenciaRegional;
	}

	public void setDescricaoGerenciaRegional(String descricaoGerenciaRegional) {
		this.descricaoGerenciaRegional = descricaoGerenciaRegional;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getDescricaoUnidadeNegocio() {
		return descricaoUnidadeNegocio;
	}

	public void setDescricaoUnidadeNegocio(String descricaoUnidadeNegocio) {
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}

	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
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
	
}

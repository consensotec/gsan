package gcom.relatorio.atendimentopublico;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * [UC1178] Gerar Relat�rio de Acompanhamento dos Boletins de Medi��o
 * 
 * Helper que vai auxiliar na montagem do relat�rio
 * de acompanhamento do boletim de medi��o.
 * 
 * @author Diogo Peixoto
 * @since 2707/2011
 *
 */
public class RelatorioAcompanhamentoBoletimMedicaoHelper {

	private Collection<RelatorioAcompanhamentoBoletimMedicaoBean> beans;
	private String tipoRelatorio;
	
	private BigDecimal taxaSucesso;
	private BigDecimal penalidadeOS;
	private BigDecimal penalidadeFiscalizacao;

	//Relat�rio Definitivo
	private BigDecimal penalidadeContratoNaoExecucao;
	private BigDecimal penalidadeCorteSupressaoIndevida;
	private BigDecimal penalidadeNaoRealizacaoServico;
	private BigDecimal valorDesconto;
	
	/**
	 * Construtor para a gera��o do relat�rio de acompanhamento de boletim de medi��o
	 * (Simula��o)
	 * 
	 * @param beans
	 * @param tipoRelatorio
	 * @param taxaSucesso
	 * @param penalidadeOS
	 * @param penalidadeFiscalizacao
	 */
	public RelatorioAcompanhamentoBoletimMedicaoHelper(Collection<RelatorioAcompanhamentoBoletimMedicaoBean> beans, String tipoRelatorio,
			BigDecimal taxaSucesso, BigDecimal penalidadeOS, BigDecimal penalidadeFiscalizacao){
		this.beans = beans;
		this.tipoRelatorio = tipoRelatorio;
		this.taxaSucesso = taxaSucesso;
		this.penalidadeOS = penalidadeOS;
		this.penalidadeFiscalizacao = penalidadeFiscalizacao;
	}

	/**
	 * 
	 * Construtor para a gera��o do relat�rio de acompanhamento de boletim de medi��o
	 * (Simula��o)
	 * 
	 * @param beans
	 * @param tipoRelatorio
	 * @param taxaSucesso
	 * @param penalidadeOS
	 * @param penalidadeFiscalizacao
	 * @param penalidadeCorteSupressaoIndevida
	 * @param penalidadeNaoRealizacaoServico
	 */
	public RelatorioAcompanhamentoBoletimMedicaoHelper(Collection<RelatorioAcompanhamentoBoletimMedicaoBean> beans, String tipoRelatorio,
			BigDecimal taxaSucesso, BigDecimal penalidadeOS, BigDecimal penalidadeFiscalizacao,
			BigDecimal penalidadeContratoNaoExecucao, BigDecimal penalidadeCorteSupressaoIndevida, 
			BigDecimal penalidadeNaoRealizacaoServico, BigDecimal valorDesconto){
		this.beans = beans;
		this.tipoRelatorio = tipoRelatorio;
		this.taxaSucesso = taxaSucesso;
		this.penalidadeOS = penalidadeOS;
		this.penalidadeFiscalizacao = penalidadeFiscalizacao;
		this.penalidadeContratoNaoExecucao = penalidadeContratoNaoExecucao;
		this.penalidadeCorteSupressaoIndevida = penalidadeCorteSupressaoIndevida;
		this.penalidadeNaoRealizacaoServico = penalidadeNaoRealizacaoServico;
		this.valorDesconto = valorDesconto;
	}
	
	public Collection<RelatorioAcompanhamentoBoletimMedicaoBean> getBeans() {
		return beans;
	}

	public void setBeans(Collection<RelatorioAcompanhamentoBoletimMedicaoBean> beans) {
		this.beans = beans;
	}

	public String getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public BigDecimal getPenalidadeOS() {
		return penalidadeOS;
	}

	public void setPenalidadeOS(BigDecimal penalidadeOS) {
		this.penalidadeOS = penalidadeOS;
	}

	public BigDecimal getPenalidadeFiscalizacao() {
		return penalidadeFiscalizacao;
	}

	public void setPenalidadeFiscalizacao(BigDecimal penalidadeFiscalizacao) {
		this.penalidadeFiscalizacao = penalidadeFiscalizacao;
	}

	public BigDecimal getPenalidadeCorteSupressaoIndevida() {
		return penalidadeCorteSupressaoIndevida;
	}

	public void setPenalidadeCorteSupressaoIndevida(
			BigDecimal penalidadeCorteSupressaoIndevida) {
		this.penalidadeCorteSupressaoIndevida = penalidadeCorteSupressaoIndevida;
	}

	public BigDecimal getPenalidadeNaoRealizacaoServico() {
		return penalidadeNaoRealizacaoServico;
	}

	public void setPenalidadeNaoRealizacaoServico(
			BigDecimal penalidadeNaoRealizacaoServico) {
		this.penalidadeNaoRealizacaoServico = penalidadeNaoRealizacaoServico;
	}

	public BigDecimal getPenalidadeContratoNaoExecucao() {
		return penalidadeContratoNaoExecucao;
	}

	public void setPenalidadeContratoNaoExecucao(
			BigDecimal penalidadeContratoNaoExecucao) {
		this.penalidadeContratoNaoExecucao = penalidadeContratoNaoExecucao;
	}

	public BigDecimal getTaxaSucesso() {
		return taxaSucesso;
	}

	public void setTaxaSucesso(BigDecimal taxaSucesso) {
		this.taxaSucesso = taxaSucesso;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
}
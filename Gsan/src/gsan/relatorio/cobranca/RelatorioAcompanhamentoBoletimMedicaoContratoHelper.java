package gsan.relatorio.cobranca;

import java.math.BigDecimal;
import java.util.Collection;

public class RelatorioAcompanhamentoBoletimMedicaoContratoHelper {

	private Collection<RelatorioAcompanhamentoBoletimMedicaoContratoBean> beans;
	
	private BigDecimal taxaSucesso;
	
	private BigDecimal penalidadeOS;
	
	private BigDecimal penalidadeFiscalizacao;

	private BigDecimal penalidadeContratoNaoExecucao;
	
	private BigDecimal penalidadeCorteSupressaoIndevida;
	
	private BigDecimal penalidadeNaoRealizacaoServico;
	
	private BigDecimal valorDesconto;
	
	public RelatorioAcompanhamentoBoletimMedicaoContratoHelper(Collection<RelatorioAcompanhamentoBoletimMedicaoContratoBean> beans,
			BigDecimal taxaSucesso, BigDecimal penalidadeOS, BigDecimal penalidadeFiscalizacao,
			BigDecimal penalidadeContratoNaoExecucao, BigDecimal penalidadeCorteSupressaoIndevida, 
			BigDecimal penalidadeNaoRealizacaoServico, BigDecimal valorDesconto){
		this.beans = beans;
		this.taxaSucesso = taxaSucesso;
		this.penalidadeOS = penalidadeOS;
		this.penalidadeFiscalizacao = penalidadeFiscalizacao;
		this.penalidadeContratoNaoExecucao = penalidadeContratoNaoExecucao;
		this.penalidadeCorteSupressaoIndevida = penalidadeCorteSupressaoIndevida;
		this.penalidadeNaoRealizacaoServico = penalidadeNaoRealizacaoServico;
		this.valorDesconto = valorDesconto;
	}

	public Collection<RelatorioAcompanhamentoBoletimMedicaoContratoBean> getBeans() {
		return beans;
	}

	public void setBeans(Collection<RelatorioAcompanhamentoBoletimMedicaoContratoBean> beans) {
		this.beans = beans;
	}

	public BigDecimal getTaxaSucesso() {
		return taxaSucesso;
	}

	public void setTaxaSucesso(BigDecimal taxaSucesso) {
		this.taxaSucesso = taxaSucesso;
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

	public BigDecimal getPenalidadeContratoNaoExecucao() {
		return penalidadeContratoNaoExecucao;
	}

	public void setPenalidadeContratoNaoExecucao(BigDecimal penalidadeContratoNaoExecucao) {
		this.penalidadeContratoNaoExecucao = penalidadeContratoNaoExecucao;
	}

	public BigDecimal getPenalidadeCorteSupressaoIndevida() {
		return penalidadeCorteSupressaoIndevida;
	}

	public void setPenalidadeCorteSupressaoIndevida(BigDecimal penalidadeCorteSupressaoIndevida) {
		this.penalidadeCorteSupressaoIndevida = penalidadeCorteSupressaoIndevida;
	}

	public BigDecimal getPenalidadeNaoRealizacaoServico() {
		return penalidadeNaoRealizacaoServico;
	}

	public void setPenalidadeNaoRealizacaoServico(BigDecimal penalidadeNaoRealizacaoServico) {
		this.penalidadeNaoRealizacaoServico = penalidadeNaoRealizacaoServico;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	

}

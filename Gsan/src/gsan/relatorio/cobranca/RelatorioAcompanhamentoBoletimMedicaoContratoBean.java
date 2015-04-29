package gsan.relatorio.cobranca;

import gsan.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * [UC1250] Solicitar Gera��o/Emiss�o Boletim de Medi��o de Contratos
 * 
 * [SB0002] - Emitir Boletim de Contrato
 * 
 * @author Mariana Victor
 *
 * @date 22/11/2011
 */
public class RelatorioAcompanhamentoBoletimMedicaoContratoBean implements RelatorioBean {

	private Integer idItemServico;
	
	private String descricaoServico;
	
	private BigDecimal quantidadeOrcada;
	
	private BigDecimal valorUnitario;
	
	private Integer quantidadeItem;
	
	private Integer quantidadeItemAcumulada;
	
	private BigDecimal valorItem;
	
	private String unidadeItem;
	
	private BigDecimal valorOrcado;
	
	private BigDecimal valorMedido;
	
	private BigDecimal valorAcumuladoPeriodo;
	
	private Integer cdItemServico;
	
	public RelatorioAcompanhamentoBoletimMedicaoContratoBean(Integer idItem, String descricaoServico, BigDecimal qtdeOrcada,
			BigDecimal valorUnitario, Integer quantidadeItem, BigDecimal valorItem, String unidadeItem, BigDecimal valorOrcado, 
			BigDecimal valorMedido, Integer quantidadeItemAcumulada, BigDecimal valorAcumuladoPeriodo){
		this.idItemServico = idItem;
		this.descricaoServico = descricaoServico;
		this.quantidadeOrcada = qtdeOrcada;
		this.valorUnitario = valorUnitario;
		this.quantidadeItem = quantidadeItem;
		this.valorItem = valorItem;
		this.unidadeItem = unidadeItem;
		this.valorOrcado = valorOrcado;
		this.valorMedido = valorMedido;
		this.quantidadeItemAcumulada = quantidadeItemAcumulada;
		this.valorAcumuladoPeriodo = valorAcumuladoPeriodo;
	}
	
	public Integer getIdItemServico() {
		return idItemServico;
	}

	public void setIdItemServico(Integer idItemServico) {
		this.idItemServico = idItemServico;
	}

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public BigDecimal getQuantidadeOrcada() {
		return quantidadeOrcada;
	}

	public void setQuantidadeOrcada(BigDecimal quantidadeOrcada) {
		this.quantidadeOrcada = quantidadeOrcada;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getQuantidadeItem() {
		return quantidadeItem;
	}

	public void setQuantidadeItem(Integer quantidadeItem) {
		this.quantidadeItem = quantidadeItem;
	}

	public Integer getQuantidadeItemAcumulada() {
		return quantidadeItemAcumulada;
	}

	public void setQuantidadeItemAcumulada(Integer quantidadeItemAcumulada) {
		this.quantidadeItemAcumulada = quantidadeItemAcumulada;
	}

	public BigDecimal getValorItem() {
		return valorItem;
	}

	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}

	public String getUnidadeItem() {
		return unidadeItem;
	}

	public void setUnidadeItem(String unidadeItem) {
		this.unidadeItem = unidadeItem;
	}

	public BigDecimal getValorOrcado() {
		return valorOrcado;
	}

	public void setValorOrcado(BigDecimal valorOrcado) {
		this.valorOrcado = valorOrcado;
	}

	public BigDecimal getValorMedido() {
		return valorMedido;
	}

	public void setValorMedido(BigDecimal valorMedido) {
		this.valorMedido = valorMedido;
	}

	public BigDecimal getValorAcumuladoPeriodo() {
		return valorAcumuladoPeriodo;
	}

	public void setValorAcumuladoPeriodo(BigDecimal valorAcumuladoPeriodo) {
		this.valorAcumuladoPeriodo = valorAcumuladoPeriodo;
	}

	public Integer getCdItemServico() {
		return cdItemServico;
	}

	public void setCdItemServico(Integer cdItemServico) {
		this.cdItemServico = cdItemServico;
	}

}

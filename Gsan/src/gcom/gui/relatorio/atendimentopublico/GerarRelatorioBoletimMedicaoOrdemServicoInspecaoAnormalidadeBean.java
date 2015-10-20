package gcom.gui.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * [UC1221] – Gerar Boletim de Medição Ordem de Serviço Inspeção Anormalidade
 * 
 * @since 19/08/2011
 * @author Diogo Peixoto
 *
 */
public class GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeBean implements RelatorioBean{

	private String descricaoComando;
	private Date dataGeracaoArquivo;
	private String nomeEmpresa;
	private String numeroContrato;
	private String nomeGerencia;
	private Integer idLocalidade;
	private String nomeLocalidade;
	private Integer idItem;
	private String descricaoItem;
	private Integer quantidadeOS;
	private BigDecimal custoUnitarioItem;
	private BigDecimal valorTotalItem;
	
	/**
	 * [UC1221 - Gerar Boletim de Medição Ordem de Serviço Inspeção Anormalidade]
	 * 
	 * Construtor para gerar o arquivo txt
	 * 
	 * @author Diogo Peixoto
	 * @since 14/09/2011
	 * 
	 * @param descricaoComando
	 * @param dataGeracao
	 * @param nomeEmpresa
	 * @param numeroContrato
	 * @param nomeGerencia
	 * @param idLocalidade
	 * @param nomeLocalidade
	 * @param idItem
	 * @param descricaoItem
	 * @param quantidadeOS
	 * @param custoUnitario
	 * @param valorTotal
	 */
	public GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeBean(String descricaoComando, Date dataGeracao,
			String nomeEmpresa, String numeroContrato, String nomeGerencia, Integer idLocalidade, String nomeLocalidade,
			Integer idItem, String descricaoItem, Integer quantidadeOS, BigDecimal custoUnitario, BigDecimal valorTotal){
		this.descricaoComando = descricaoComando;
		this.dataGeracaoArquivo = dataGeracao;
		this.nomeEmpresa = nomeEmpresa;
		this.numeroContrato = numeroContrato;
		this.nomeGerencia = nomeGerencia;
		this.idLocalidade = idLocalidade;
		this.nomeLocalidade = nomeLocalidade;
		this.idItem = idItem;
		this.descricaoItem = descricaoItem;
		this.quantidadeOS = quantidadeOS;
		this.custoUnitarioItem = custoUnitario;
		this.valorTotalItem = valorTotal;
	}
	
	public String getDescricaoComando() {
		return descricaoComando;
	}

	public void setDescricaoComando(String descricaoComando) {
		this.descricaoComando = descricaoComando;
	}

	public Date getDataGeracaoArquivo() {
		return dataGeracaoArquivo;
	}

	public void setDataGeracaoArquivo(Date dataGeracaoArquivo) {
		this.dataGeracaoArquivo = dataGeracaoArquivo;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getNomeGerencia() {
		return nomeGerencia;
	}

	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getDescricaoItem() {
		return descricaoItem;
	}

	public void setDescricaoItem(String descricaoItem) {
		this.descricaoItem = descricaoItem;
	}

	public Integer getQuantidadeOS() {
		return quantidadeOS;
	}

	public void setQuantidadeOS(Integer quantidadeOS) {
		this.quantidadeOS = quantidadeOS;
	}

	public BigDecimal getCustoUnitarioItem() {
		return custoUnitarioItem;
	}

	public void setCustoUnitarioItem(BigDecimal custoUnitarioItem) {
		this.custoUnitarioItem = custoUnitarioItem;
	}

	public BigDecimal getValorTotalItem() {
		return valorTotalItem;
	}

	public void setValorTotalItem(BigDecimal valorTotalItem) {
		this.valorTotalItem = valorTotalItem;
	}

	public Integer getIdItem() {
		return idItem;
	}
	
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	
}
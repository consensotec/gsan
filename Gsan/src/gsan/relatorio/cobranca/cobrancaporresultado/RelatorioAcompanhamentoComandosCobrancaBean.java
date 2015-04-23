package gsan.relatorio.cobranca.cobrancaporresultado;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import gsan.relatorio.RelatorioBean;

public class RelatorioAcompanhamentoComandosCobrancaBean implements RelatorioBean {	

	private String comando;
	
	private Date dataExecucao;
	
	private Date dataInicio;
	
	private Date dataFinal;
	
	private String faixa;
	
	private Integer quantidadeContas;
	
	private Integer quantidadeClientes;
	
	private BigDecimal valorTotal;
	
	private JRBeanCollectionDataSource arrayJRTotalizacaoFaixasBean;
	private ArrayList arrayRelatorioAcompanhamentoComandosCobrancaSubBean;

	public String getComando() {
		return comando;
	}

	public void setComando(String comando) {
		this.comando = comando;
	}

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getFaixa() {
		return faixa;
	}

	public void setFaixa(String faixa) {
		this.faixa = faixa;
	}

	public Integer getQuantidadeContas() {
		return quantidadeContas;
	}

	public void setQuantidadeContas(Integer quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}

	public Integer getQuantidadeClientes() {
		return quantidadeClientes;
	}

	public void setQuantidadeClientes(Integer quantidadeClientes) {
		this.quantidadeClientes = quantidadeClientes;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public JRBeanCollectionDataSource getArrayJRTotalizacaoFaixasBean() {
		return arrayJRTotalizacaoFaixasBean;
	}

	public void setArrayJRTotalizacaoFaixasBean(JRBeanCollectionDataSource arrayJRTotalizacaoFaixasBean) {
		this.arrayJRTotalizacaoFaixasBean = arrayJRTotalizacaoFaixasBean;
	}

	public ArrayList getArrayRelatorioAcompanhamentoComandosCobrancaSubBean() {
		return arrayRelatorioAcompanhamentoComandosCobrancaSubBean;
	}

	public void setArrayRelatorioAcompanhamentoComandosCobrancaSubBean(
			ArrayList arrayRelatorioAcompanhamentoComandosCobrancaSubBean) {
		this.arrayRelatorioAcompanhamentoComandosCobrancaSubBean = arrayRelatorioAcompanhamentoComandosCobrancaSubBean;
	}

	public void setRelatorioAcompanhamentoComandosCobrancaSubBean(Collection<RelatorioAcompanhamentoComandosCobrancaSubBean> colecaoRelatorioAcompanhamentoComandosCobrancaSubBean) {
		this.arrayRelatorioAcompanhamentoComandosCobrancaSubBean = new ArrayList();
		this.arrayRelatorioAcompanhamentoComandosCobrancaSubBean.addAll(colecaoRelatorioAcompanhamentoComandosCobrancaSubBean);
		this.arrayJRTotalizacaoFaixasBean = new JRBeanCollectionDataSource(this.arrayRelatorioAcompanhamentoComandosCobrancaSubBean);
	}

}

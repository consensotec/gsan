package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioDeclaracaoAnualQuitacaoDebitosPaginaBean implements
		RelatorioBean {
	private String cliente;
	private String endereco;
	private String matriculaFormatada;
	private String ano;
	private String sequencial;
	
	private String cnpjInscricao;
	private String nomeCompleto;
	private String nomeAbreviado;
	private String dataEmissao;
	private String inscricao;
	
	private JRBeanCollectionDataSource colecaoExtratoQuitacaoItem;
	private ArrayList arrayExtratoQuitacaoItem;

	public RelatorioDeclaracaoAnualQuitacaoDebitosPaginaBean(String cliente,
			String endereco, String matriculaFormatada) {
		setCliente(cliente);
		setEndereco(endereco);
		setMatriculaFormatada(matriculaFormatada);
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getMatriculaFormatada() {
		return matriculaFormatada;
	}

	public void setMatriculaFormatada(String matriculaFormatada) {
		this.matriculaFormatada = matriculaFormatada;
	}

	public JRBeanCollectionDataSource getColecaoExtratoQuitacaoItem() {
		return colecaoExtratoQuitacaoItem;
	}

	public void setColecaoExtratoQuitacaoItem(Collection arrayExtratoQuitacaoItem) {
		this.arrayExtratoQuitacaoItem = new ArrayList();
		this.arrayExtratoQuitacaoItem.addAll(arrayExtratoQuitacaoItem);
		this.colecaoExtratoQuitacaoItem = new JRBeanCollectionDataSource(this.arrayExtratoQuitacaoItem);
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getSequencial() {
		return sequencial;
	}

	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}

	public String getCnpjInscricao() {
		return cnpjInscricao;
	}

	public void setCnpjInscricao(String cnpjInscricao) {
		this.cnpjInscricao = cnpjInscricao;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	
	
}

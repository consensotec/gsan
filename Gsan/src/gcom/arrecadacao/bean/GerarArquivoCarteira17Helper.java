package gcom.arrecadacao.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * RN2013108067 – Mudança dos boletos bancarios: da carteira 18 para carteira 17
 * [UC1575] - Gerar de Arquivo Carteira 17 
 * 
 * @author Diogo Luiz
 * @Date 15/11/2013
 *
 */
public class GerarArquivoCarteira17Helper {

	private Integer conta;
	
	private Integer matricula;
	
	private Date dataVencimento;
	
	private BigDecimal valorDebito;
	
	private Date processamento;
	
	private String cliente;
	
	private String cpf;
	
	private String cnpj;
	
	private String nomeCliente;
	
	private String logradouro;
	
	private String descricaoLogradouroTipo;
	
	private String descricaoLogradouroTitulo;
	
	private String bairro;
	
	private String cep;
	
	private String municipio;
	
	private String unidadeFederacao;
	
	private String nossoNumero;

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public Date getProcessamento() {
		return processamento;
	}

	public void setProcessamento(Date processamento) {
		this.processamento = processamento;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getDescricaoLogradouroTipo() {
		return descricaoLogradouroTipo;
	}

	public void setDescricaoLogradouroTipo(String descricaoLogradouroTipo) {
		this.descricaoLogradouroTipo = descricaoLogradouroTipo;
	}

	public String getDescricaoLogradouroTitulo() {
		return descricaoLogradouroTitulo;
	}

	public void setDescricaoLogradouroTitulo(String descricaoLogradouroTitulo) {
		this.descricaoLogradouroTitulo = descricaoLogradouroTitulo;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUnidadeFederacao() {
		return unidadeFederacao;
	}

	public void setUnidadeFederacao(String unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}
	
	
	
}

package gcom.cobranca.bean;

import gcom.relatorio.RelatorioBean;

public class RelatorioConsultarArquivoRetornoCobrancaBean implements RelatorioBean {

	private String imovel;
	private String cpf;
	private String cnpj;
	private String nome;
	private String numIdentidade;
	private String orgaoExp;
	private String unidadeFed;
	private String numDdd;
	private String numFone;
	private String numRamal;
	private String idCliente;

	public RelatorioConsultarArquivoRetornoCobrancaBean() {
		//construtor vazio
	}

	public RelatorioConsultarArquivoRetornoCobrancaBean(
			String imovel, String cpf, String cnpj, String nome, String numIdentidade, String orgaoExp, String unidadeFed,
			String numDdd, String numFone, String numRamal, String idCliente) {
		super();
		this.imovel = imovel;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.nome = nome;
		this.numIdentidade = numIdentidade;
		this.orgaoExp = orgaoExp;
		this.unidadeFed = unidadeFed;
		this.numDdd = numDdd;
		this.numFone = numFone;
		this.numRamal = numRamal;
		this.idCliente = idCliente;
	}

	public String getImovel() {
		return imovel;
	}

	public void setImovel(String imovel) {
		this.imovel = imovel;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumIdentidade() {
		return numIdentidade;
	}

	public void setNumIdentidade(String numIdentidade) {
		this.numIdentidade = numIdentidade;
	}

	public String getOrgaoExp() {
		return orgaoExp;
	}

	public void setOrgaoExp(String orgaoExp) {
		this.orgaoExp = orgaoExp;
	}

	public String getUnidadeFed() {
		return unidadeFed;
	}

	public void setUnidadeFed(String unidadeFed) {
		this.unidadeFed = unidadeFed;
	}

	public String getNumDdd() {
		return numDdd;
	}

	public void setNumDdd(String numDdd) {
		this.numDdd = numDdd;
	}

	public String getNumFone() {
		return numFone;
	}

	public void setNumFone(String numFone) {
		this.numFone = numFone;
	}

	public String getNumRamal() {
		return numRamal;
	}

	public void setNumRamal(String numRamal) {
		this.numRamal = numRamal;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

}

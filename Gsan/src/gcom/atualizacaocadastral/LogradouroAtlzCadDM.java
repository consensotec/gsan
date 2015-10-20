package gcom.atualizacaocadastral;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.Localidade;
import gcom.interceptor.ObjetoGcom;

import java.util.Date;

public class LogradouroAtlzCadDM extends ObjetoGcom{

	private static final long serialVersionUID = 1L;

    private Integer id;
    private String codigo;
    private String nome;
    private String nomePopular;
    private String nomeLoteamento;
    private Municipio municipio;
    private LogradouroTitulo logradouroTitulo;
    private LogradouroTipo logradouroTipo;
    private Localidade localidade;
    private Empresa empresa;
    private Short indicadorAtualizado;
    private Date ultimaAlteracao;
    private Logradouro logradouro;

	public LogradouroAtlzCadDM() {
		super();
	}

	public LogradouroAtlzCadDM(Integer id, String codigo, String nome,
			String nomePopular, String nomeLoteamento, Municipio municipio,
			LogradouroTitulo logradouroTitulo, LogradouroTipo logradouroTipo,
			Localidade localidade, Empresa empresa, Short indicadorAtualizado,
			Date ultimaAlteracao) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.nomePopular = nomePopular;
		this.nomeLoteamento = nomeLoteamento;
		this.municipio = municipio;
		this.logradouroTitulo = logradouroTitulo;
		this.logradouroTipo = logradouroTipo;
		this.localidade = localidade;
		this.empresa = empresa;
		this.indicadorAtualizado = indicadorAtualizado;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomePopular() {
		return nomePopular;
	}

	public void setNomePopular(String nomePopular) {
		this.nomePopular = nomePopular;
	}

	public String getNomeLoteamento() {
		return nomeLoteamento;
	}

	public void setNomeLoteamento(String nomeLoteamento) {
		this.nomeLoteamento = nomeLoteamento;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public LogradouroTitulo getLogradouroTitulo() {
		return logradouroTitulo;
	}

	public void setLogradouroTitulo(LogradouroTitulo logradouroTitulo) {
		this.logradouroTitulo = logradouroTitulo;
	}

	public LogradouroTipo getLogradouroTipo() {
		return logradouroTipo;
	}

	public void setLogradouroTipo(LogradouroTipo logradouroTipo) {
		this.logradouroTipo = logradouroTipo;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Short getIndicadorAtualizado() {
		return indicadorAtualizado;
	}

	public void setIndicadorAtualizado(Short indicadorAtualizado) {
		this.indicadorAtualizado = indicadorAtualizado;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

    /**
     * Descrição completa do logradouro (Tipo + Titulo + Nome)
     * 
     * @author André Miranda
     * @since 26/12/2014
     * @return Descrição completa ou ""
     */
    public String getDescricaoFormatada(){
    	String retorno = "";

    	try {
    		retorno = logradouroTipo.getDescricao() + " ";
    	} catch(NullPointerException e) {}

    	try {
    		retorno += logradouroTitulo.getDescricao() + " ";
    	} catch(NullPointerException e) {}

    	try {
    		retorno += nome;
    	} catch(NullPointerException e) {}

    	return retorno.trim();
    }
}
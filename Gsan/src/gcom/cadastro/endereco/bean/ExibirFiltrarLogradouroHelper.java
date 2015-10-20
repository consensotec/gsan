package gcom.cadastro.endereco.bean;

import java.io.Serializable;

/**
 * [UC0032] Inserir Logradouro - [UC0033] Manter Logradouro
 * 
 * Proposta: 05/10/2011 - Tiago Moreno - PE2011065447 - Verificar existência de Logradouro com mesmo nome
 *
 * [FS0012] - Verificar existência de Logradouro com mesmo nome
 * 
 * @author Thúlio Araújo
 * @since 10/10/2011
 */
public class ExibirFiltrarLogradouroHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	private String bairro;
	private String municipio;
	private String cep;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getMunicipio() {
		return municipio;
	}
	
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
}

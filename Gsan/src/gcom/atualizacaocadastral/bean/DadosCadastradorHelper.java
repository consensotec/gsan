package gcom.atualizacaocadastral.bean;

import java.io.Serializable;

/**
 * [UC 1392] - Consultar Roteiro Dispositivo Móvel Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 05/09/2013
 *
 */
public class DadosCadastradorHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String cpf;
	
	private Integer quantidadeImoveis;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getQuantidadeImoveis() {
		return quantidadeImoveis;
	}

	public void setQuantidadeImoveis(Integer quantidadeImoveis) {
		this.quantidadeImoveis = quantidadeImoveis;
	}

}

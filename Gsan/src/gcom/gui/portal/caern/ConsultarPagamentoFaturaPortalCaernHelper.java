package gcom.gui.portal.caern;

import java.io.Serializable;

/**
 * 
 * [RM5976][UC1532] Consultar Arrecadadores.
 * 
 * @author Maxwell Moreira 
 * @since 22/07/2013
 *
 */

public class ConsultarPagamentoFaturaPortalCaernHelper implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private String nome;
	private String codigo;
	
	public ConsultarPagamentoFaturaPortalCaernHelper(String nome, String codigo){
		this.nome = nome;
		this.codigo = codigo;
	}

	public ConsultarPagamentoFaturaPortalCaernHelper(){
		
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
			this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public boolean equals(Object obj) {
		ConsultarPagamentoFaturaPortalCaernHelper castOther = 
			(ConsultarPagamentoFaturaPortalCaernHelper) obj;

		return this.getCodigo().equals(castOther.getCodigo()) && this.getNome().equals(castOther.getNome());
	}

}

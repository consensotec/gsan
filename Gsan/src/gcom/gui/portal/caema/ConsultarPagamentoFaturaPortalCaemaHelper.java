package gcom.gui.portal.caema;

import java.io.Serializable;

/**
 * 
 * Helper responsável pela exibição dos bancos credenciados 
 * da tela informacoes_pagar_fatura_portal_caema.jsp
 * 
 * @author Nathalia Santos 
 * @since 20/01/2012
 *
 */

public class ConsultarPagamentoFaturaPortalCaemaHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String codigo;
	


	public ConsultarPagamentoFaturaPortalCaemaHelper(String nome, String codigo){
		this.nome = nome;
		this.codigo = codigo;
	}

	public ConsultarPagamentoFaturaPortalCaemaHelper(){
		
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
		
		ConsultarPagamentoFaturaPortalCaemaHelper castOther = 
			(ConsultarPagamentoFaturaPortalCaemaHelper) obj;

		return this.getCodigo().equals(castOther.getCodigo()) && 
				this.getNome().equals(castOther.getNome());

	}
	
	
	
}

	
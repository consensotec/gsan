package gsan.gui.portal.caer;

import java.io.Serializable;

/**
 * 
 * Helper responsável pela exibição dos bancos credenciados 
 * da tela informacoes_pagar_fatura_portal_caer.jsp
 * 
 * @author Nathalia Santos 
 * @since 20/01/2012
 *
 */

public class ConsultarPagamentoFaturaPortalCaerHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String codigo;
	


	public ConsultarPagamentoFaturaPortalCaerHelper(String nome, String codigo){
		this.nome = nome;
		this.codigo = codigo;
	}

	public ConsultarPagamentoFaturaPortalCaerHelper(){
		
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
		
		ConsultarPagamentoFaturaPortalCaerHelper castOther = 
			(ConsultarPagamentoFaturaPortalCaerHelper) obj;

		return (this.getCodigo().equals(castOther.getCodigo()) && 
				this.getNome().equals(castOther.getNome()));

	}
	
	
	
}

	
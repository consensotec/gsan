package gcom.gui.relatorio.micromedicao;

import java.io.Serializable;

public class AdicionarHidrometroFaixaIdadeHelper implements Serializable{
	
	/** 
	 * @author Cesar Medeiros
	 * @created 09/06/2015
	 */
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	private Integer valorInicial;
	private Integer valorFinal;

	public AdicionarHidrometroFaixaIdadeHelper(String descricao, Integer valorInicial,
			Integer valorFinal) {
		this.descricao = descricao;
		this.valorInicial = valorInicial;
		this.valorFinal = valorFinal;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(Integer valorInicial) {
		this.valorInicial = valorInicial;
	}
	public Integer getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(Integer valorFinal) {
		this.valorFinal = valorFinal;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		boolean retorno = false;
		
		if (obj instanceof AdicionarHidrometroFaixaIdadeHelper) {
			AdicionarHidrometroFaixaIdadeHelper faixa = (AdicionarHidrometroFaixaIdadeHelper) obj;
			
			if(faixa.getDescricao().equals(this.descricao)
					&& faixa.getValorInicial().compareTo(this.valorInicial)==0
						&& faixa.getValorFinal().compareTo(this.valorFinal)==0){
				
				retorno = true;
				
			}
		}
		return retorno;
	}
	
}	

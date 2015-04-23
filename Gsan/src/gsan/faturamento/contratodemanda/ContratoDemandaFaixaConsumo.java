package gsan.faturamento.contratodemanda;

import java.util.Date;

/**
 * [UC1226] Inserir Contrato de Demanda Condomínios Residenciais
 * 
 * @author Diogo Peixoto
 * @since 21/09/2011
 *
 */
public class ContratoDemandaFaixaConsumo {

	private Integer id;
	private Integer faixaInicial;
	private Integer faixaFinal;
	private Integer percentualDesconto;
	private Short indicadorUsadaPrimeiraVez;
	private Date ultimaAlteracao;
	
	public ContratoDemandaFaixaConsumo(){
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFaixaInicial() {
		return faixaInicial;
	}
	public void setFaixaInicial(Integer faixaInicial) {
		this.faixaInicial = faixaInicial;
	}
	public Integer getFaixaFinal() {
		return faixaFinal;
	}
	public void setFaixaFinal(Integer faixaFinal) {
		this.faixaFinal = faixaFinal;
	}
	public Integer getPercentualDesconto() {
		return percentualDesconto;
	}
	public void setPercentualDesconto(Integer percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}
	public Short getIndicadorUsadaPrimeiraVez() {
		return indicadorUsadaPrimeiraVez;
	}
	public void setIndicadorUsadaPrimeiraVez(Short indicadorUsadaPrimeiraVez) {
		this.indicadorUsadaPrimeiraVez = indicadorUsadaPrimeiraVez;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
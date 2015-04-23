package gsan.cadastro;

public class ParametrosTransacaoBatchHelper  {

	private String valorAnterior;
	
	private String valorNovo;
	
	private String idTabelaColuna;
	
	public ParametrosTransacaoBatchHelper(
			String valorAnterior, String valorNovo, String idTabelaColuna) {
		super();
		this.valorAnterior = valorAnterior;
		this.valorNovo = valorNovo;
		this.idTabelaColuna = idTabelaColuna;
	}
	public String getValorAnterior() {
		return valorAnterior;
	}
	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}
	public String getValorNovo() {
		return valorNovo;
	}
	public void setValorNovo(String valorNovo) {
		this.valorNovo = valorNovo;
	}
	public String getIdTabelaColuna() {
		return idTabelaColuna;
	}
	public void setIdTabelaColuna(String idTabelaColuna) {
		this.idTabelaColuna = idTabelaColuna;
	}
}

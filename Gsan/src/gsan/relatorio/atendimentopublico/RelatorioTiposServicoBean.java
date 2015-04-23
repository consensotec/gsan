package gsan.relatorio.atendimentopublico;

import gsan.relatorio.RelatorioBean;

/**
 * [UC1254] Relatório Ordem de Serviço com valores de cobrança
 * @author Amélia Pessoa
 * @date 24/11/2011
 */

public class RelatorioTiposServicoBean implements RelatorioBean{

	private String codigo;
	private String tipoServico;
	private String perfilImovel;
	private String categoriaSubcategoria;
	private String descricao;
	private String economiasInicial;
	private String economiasFinal;
	private String valor;
	
			
	//Construtor inicializando todas as variáveis de instância.
	public RelatorioTiposServicoBean(String codigo, String tipoServico, String perfilImovel,
		String categoriaSubcategoria, String descricao, String economiasInicial, String economiasFinal, String valor){
		this.setCodigo(codigo);
		this.setTipoServico(tipoServico);
		this.setPerfilImovel(perfilImovel);
		this.setCategoriaSubcategoria(categoriaSubcategoria);
		this.setDescricao(descricao);
		this.setEconomiasInicial(economiasInicial);
		this.setEconomiasFinal(economiasFinal);
		this.setValor(valor);
	}
	
	//Construtor vazio
	public RelatorioTiposServicoBean() {}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getPerfilImovel() {
		if (perfilImovel==null){
			return " ";
		}
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getCategoriaSubcategoria() {
		return categoriaSubcategoria;
	}

	public void setCategoriaSubcategoria(String categoriaSubcategoria) {
		this.categoriaSubcategoria = categoriaSubcategoria;
	}

	public String getDescricao() {
		if (descricao==null){
			return " ";
		}
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEconomiasInicial() {
		return economiasInicial;
	}

	public void setEconomiasInicial(String economiasInicial) {
		this.economiasInicial = economiasInicial;
	}

	public String getEconomiasFinal() {
		return economiasFinal;
	}

	public void setEconomiasFinal(String economiasFinal) {
		this.economiasFinal = economiasFinal;
	}

	public String getValor() {
		if (valor==null){
			return " ";
		} 
		return "R$ "+valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

		
}
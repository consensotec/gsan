package gsan.relatorio.atendimentopublico;

import gsan.relatorio.RelatorioBean;
import gsan.util.ErroRepositorioException;

/**
 * [UC1275] Gerar Relatório Quantidade de Acessos Loja Virtual
 * 
 * @author Flávio Ferreira
 * @date 01/10/2013
 * 
 */

public class RelatorioAcessoLojaVirtualBean implements RelatorioBean{
	
	private String tipoAtendimento;
	private String quantidade;
	private String qtdServicosExecutados;
	
	public RelatorioAcessoLojaVirtualBean(String tipoAtendimento, String quantidade, String qtdServicosExecutados){
		this.tipoAtendimento = tipoAtendimento;
		this.quantidade = quantidade;
		this.qtdServicosExecutados = qtdServicosExecutados;
		
	}

	public String getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getQtdServicosExecutados() {
		return qtdServicosExecutados;
	}

	public void setQtdServicosExecutados(String qtdServicosExecutados) {
		this.qtdServicosExecutados = qtdServicosExecutados;
	}
	
	

}

package gsan.cadastro;

import gsan.interceptor.ControleAlteracao;
import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.Filtro;

import java.util.Collection;
import java.util.Date;

@ControleAlteracao()
public class DadosCadastraisTransacaoBatchHelper extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	private Collection<TipoAlteracaoTransacaoBatchHelper> colecaoTipoAlteracaoTransacao;

	private Integer idOperacao;
	
	private Integer idArgumentoValor;
	
	@Override
	public Date getUltimaAlteracao() {
		return null;
	}
	
	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
	}
	
	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		return null; 
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		return null;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		return null;		
	}
	
	public Collection<TipoAlteracaoTransacaoBatchHelper> getColecaoTipoAlteracaoTransacao() {
		return colecaoTipoAlteracaoTransacao;
	}

	public void setColecaoTipoAlteracaoTransacao(
			Collection<TipoAlteracaoTransacaoBatchHelper> colecaoTipoAlteracaoTransacao) {
		this.colecaoTipoAlteracaoTransacao = colecaoTipoAlteracaoTransacao;
	}
	
	public Integer getIdArgumentoValor() {
		return idArgumentoValor;
	}
	public void setIdArgumentoValor(Integer idArgumentoValor) {
		this.idArgumentoValor = idArgumentoValor;
	}

	public Integer getIdOperacao() {
		return idOperacao;
	}

	public void setIdOperacao(Integer idOperacao) {
		this.idOperacao = idOperacao;
	}
}

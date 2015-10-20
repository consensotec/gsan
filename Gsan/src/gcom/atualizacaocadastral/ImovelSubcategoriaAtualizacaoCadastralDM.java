package gcom.atualizacaocadastral;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.interceptor.ObjetoGcom;

import java.util.Date;

/**
 * 
 * @author André Miranda
 * @date 25/08/2014
 *
 */
public class ImovelSubcategoriaAtualizacaoCadastralDM extends ObjetoGcom {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Short quantidadeEconomias;
	private Date ultimaAlteracao;
	private Categoria categoria;
	private Subcategoria subcategoria;
	private ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM;
	private String descricaoCategoria;
	private String descricaoSubcategoria;
		
	public ImovelSubcategoriaAtualizacaoCadastralDM(Integer id, Short quantidadeEconomias, Date ultimaAlteracao, Categoria categoria, Subcategoria subcategoria, ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM) {
		super();
		this.id = id;
		this.quantidadeEconomias = quantidadeEconomias;
		this.ultimaAlteracao = ultimaAlteracao;
		this.categoria = categoria;
		this.subcategoria = subcategoria;
		this.imovelAtualizacaoCadastralDM = imovelAtualizacaoCadastralDM;
	}

	public ImovelSubcategoriaAtualizacaoCadastralDM() {
	}

	public Short getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(Short quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public ImovelAtualizacaoCadastralDM getImovelAtualizacaoCadastralDM() {
		return imovelAtualizacaoCadastralDM;
	}

	public void setImovelAtualizacaoCadastralDM(ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM) {
		this.imovelAtualizacaoCadastralDM = imovelAtualizacaoCadastralDM;
	}

	public String getDescricaoCategoria() {
		if(categoria!=null)descricaoCategoria = categoria.getDescricao();
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getDescricaoSubcategoria() {
		if(subcategoria!=null)descricaoSubcategoria = subcategoria.getDescricao();
		return descricaoSubcategoria;
	}

	public void setDescricaoSubcategoria(String descricaoSubcategoria) {
		this.descricaoSubcategoria = descricaoSubcategoria;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
}

package gsan.cadastro.empresa;

import java.math.BigDecimal;
import java.util.Date;

import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

public class EmpresaCobrancaFaixa extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private BigDecimal percentualFaixa;
	
	private Integer numeroMinimoContasFaixa;

	private Date ultimaAlteracao;
	
	private String descricao;
	
	private EmpresaContratoCobranca empresaContratoCobranca;

	public EmpresaCobrancaFaixa() {
		super();
	}

	public EmpresaCobrancaFaixa(Integer id, BigDecimal percentualFaixa, Integer numeroMinimoContasFaixa, Date ultimaAlteracao, EmpresaContratoCobranca empresaContratoCobranca) {
		super();
		this.id = id;
		this.percentualFaixa = percentualFaixa;
		this.numeroMinimoContasFaixa = numeroMinimoContasFaixa;
		this.ultimaAlteracao = ultimaAlteracao;
		this.empresaContratoCobranca = empresaContratoCobranca;
	}

	public EmpresaContratoCobranca getEmpresaContratoCobranca() {
		return empresaContratoCobranca;
	}

	public void setEmpresaContratoCobranca(
			EmpresaContratoCobranca empresaContratoCobranca) {
		this.empresaContratoCobranca = empresaContratoCobranca;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroMinimoContasFaixa() {
		return numeroMinimoContasFaixa;
	}

	public void setNumeroMinimoContasFaixa(Integer numeroMinimoContasFaixa) {
		this.numeroMinimoContasFaixa = numeroMinimoContasFaixa;
	}

	public BigDecimal getPercentualFaixa() {
		return percentualFaixa;
	}

	public void setPercentualFaixa(BigDecimal percentualFaixa) {
		this.percentualFaixa = percentualFaixa;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroEmpresaCobrancaFaixa filtroEmpresaCobrancaFaixa = new FiltroEmpresaCobrancaFaixa();

		filtroEmpresaCobrancaFaixa.adicionarParametro(new ParametroSimples(
				FiltroEmpresaCobrancaFaixa.ID, this.getId()));
		return filtroEmpresaCobrancaFaixa;
	}
	
}

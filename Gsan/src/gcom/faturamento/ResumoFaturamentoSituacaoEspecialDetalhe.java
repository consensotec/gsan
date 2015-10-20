package gcom.faturamento;

import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class ResumoFaturamentoSituacaoEspecialDetalhe extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private ResumoFaturamentoSituacaoEspecial resumoFaturamentoSituacaoEspecial;
	private Imovel imovel;
	private Date ultimaAlteracao;
	
	public ResumoFaturamentoSituacaoEspecialDetalhe(){
		super();
	}

	public ResumoFaturamentoSituacaoEspecialDetalhe(
			Integer id,
			ResumoFaturamentoSituacaoEspecial resumoFaturamentoSituacaoEspecial,
			Imovel imovel, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.resumoFaturamentoSituacaoEspecial = resumoFaturamentoSituacaoEspecial;
		this.imovel = imovel;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ResumoFaturamentoSituacaoEspecial getResumoFaturamentoSituacaoEspecial() {
		return resumoFaturamentoSituacaoEspecial;
	}

	public void setResumoFaturamentoSituacaoEspecial(
			ResumoFaturamentoSituacaoEspecial resumoFaturamentoSituacaoEspecial) {
		this.resumoFaturamentoSituacaoEspecial = resumoFaturamentoSituacaoEspecial;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
	
	@Override
	public Filtro retornaFiltro() {
		return null;
	}

}
package gcom.atualizacaocadastral;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.interceptor.ObjetoGcom;
import gcom.micromedicao.consumo.LigacaoTipo;

import java.util.Date;

public class OrdemServicoAtualizacaoCadastralDM extends ObjetoGcom {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date ultimaAlteracao;
	private ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM;
	private OrdemServico ordemServico;
	private LigacaoTipo ligacaoTipo;
	
	public OrdemServicoAtualizacaoCadastralDM() {
		super();
	}

	public OrdemServicoAtualizacaoCadastralDM(Integer id, Date ultimaAlteracao,
			ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM, OrdemServico ordemServico,
			LigacaoTipo ligacaoTipo) {
		super();
		this.id = id;
		this.ultimaAlteracao = ultimaAlteracao;
		this.imovelAtualizacaoCadastralDM = imovelAtualizacaoCadastralDM;
		this.ordemServico = ordemServico;
		this.ligacaoTipo = ligacaoTipo;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ImovelAtualizacaoCadastralDM getImovelAtualizacaoCadastralDM() {
		return imovelAtualizacaoCadastralDM;
	}

	public void setImovelAtualizacaoCadastralDM(ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM) {
		this.imovelAtualizacaoCadastralDM = imovelAtualizacaoCadastralDM;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public LigacaoTipo getLigacaoTipo() {
		return ligacaoTipo;
	}

	public void setLigacaoTipo(LigacaoTipo ligacaoTipo) {
		this.ligacaoTipo = ligacaoTipo;
	}
	
}

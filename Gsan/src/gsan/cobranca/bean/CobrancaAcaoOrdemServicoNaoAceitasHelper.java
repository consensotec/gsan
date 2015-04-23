package gsan.cobranca.bean;

import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.cobranca.CobrancaAcaoOrdemServicoNaoAceitas;

import java.io.Serializable;

public class CobrancaAcaoOrdemServicoNaoAceitasHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private OrdemServico ordemServico;
	
	private CobrancaAcaoOrdemServicoNaoAceitas cobrancaAcaoOrdemServicoNaoAceitas;

	public CobrancaAcaoOrdemServicoNaoAceitasHelper(OrdemServico ordemServico,
			CobrancaAcaoOrdemServicoNaoAceitas cobrancaAcaoOrdemServicoNaoAceitas) {
		super();
		this.ordemServico = ordemServico;
		this.cobrancaAcaoOrdemServicoNaoAceitas = cobrancaAcaoOrdemServicoNaoAceitas;
	}

	public CobrancaAcaoOrdemServicoNaoAceitasHelper() {
		super();
	}

	public CobrancaAcaoOrdemServicoNaoAceitas getCobrancaAcaoOrdemServicoNaoAceitas() {
		return cobrancaAcaoOrdemServicoNaoAceitas;
	}

	public void setCobrancaAcaoOrdemServicoNaoAceitas(
			CobrancaAcaoOrdemServicoNaoAceitas cobrancaAcaoOrdemServicoNaoAceitas) {
		this.cobrancaAcaoOrdemServicoNaoAceitas = cobrancaAcaoOrdemServicoNaoAceitas;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

}

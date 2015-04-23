package gsan.atendimentopublico.registroatendimento;

import gsan.interceptor.ObjetoGcom;

public class AtendimentoMotivoEncAcaoCobrancaPK extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

    private Integer atendimentoMotivoEncerramentoId;

    private Integer cobrancaAcaoId;

	public AtendimentoMotivoEncAcaoCobrancaPK(
			Integer atendimentoMotivoEncerramentoId, Integer cobrancaAcaoId) {
		super();
		this.atendimentoMotivoEncerramentoId = atendimentoMotivoEncerramentoId;
		this.cobrancaAcaoId = cobrancaAcaoId;
	}

	public AtendimentoMotivoEncAcaoCobrancaPK() {
		super();
	}

	public Integer getAtendimentoMotivoEncerramentoId() {
		return atendimentoMotivoEncerramentoId;
	}

	public void setAtendimentoMotivoEncerramentoId(Integer atendimentoMotivoEncerramentoId) {
		this.atendimentoMotivoEncerramentoId = atendimentoMotivoEncerramentoId;
	}

	public Integer getCobrancaAcaoId() {
		return cobrancaAcaoId;
	}

	public void setCobrancaAcaoId(Integer cobrancaAcaoId) {
		this.cobrancaAcaoId = cobrancaAcaoId;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "atendimentoMotivoEncerramentoId";
		retorno[1] = "cobrancaAcaoId";
		return retorno;
	} 

}

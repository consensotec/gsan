package gsan.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

public class InformarAssociacaoMotivoEncerramentoActionForm extends ValidatorActionForm {
	
	private String idMotivoEncerramento;
	
	private String descricaoMotivoEncerramento;
	
	private String descricao;

	private String cobrancaAcaoId;
	
	private String indicadorGeraPagamento;
	
	private String indicadorGeraSucessor;
	
	private String indicadorExibeDocumento;
	
	private String cobrancaAcao;

	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}

	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCobrancaAcaoId() {
		return cobrancaAcaoId;
	}

	public void setCobrancaAcaoId(String cobrancaAcaoId) {
		this.cobrancaAcaoId = cobrancaAcaoId;
	}

	public String getIndicadorGeraPagamento() {
		return indicadorGeraPagamento;
	}

	public void setIndicadorGeraPagamento(String indicadorGeraPagamento) {
		this.indicadorGeraPagamento = indicadorGeraPagamento;
	}

	public String getIndicadorGeraSucessor() {
		return indicadorGeraSucessor;
	}

	public void setIndicadorGeraSucessor(String indicadorGeraSucessor) {
		this.indicadorGeraSucessor = indicadorGeraSucessor;
	}

	public String getIndicadorExibeDocumento() {
		return indicadorExibeDocumento;
	}

	public void setIndicadorExibeDocumento(String indicadorExibeDocumento) {
		this.indicadorExibeDocumento = indicadorExibeDocumento;
	}

	public String getCobrancaAcao() {
		return cobrancaAcao;
	}

	public void setCobrancaAcao(String cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	public String getDescricaoMotivoEncerramento() {
		return descricaoMotivoEncerramento;
	}

	public void setDescricaoMotivoEncerramento(String descricaoMotivoEncerramento) {
		this.descricaoMotivoEncerramento = descricaoMotivoEncerramento;
	}

}

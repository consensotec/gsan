package gsan.relatorio.cobranca;

import gsan.relatorio.RelatorioBean;

public class RelatorioDocumentoCobrancaMotivosEncerramentoBean implements RelatorioBean {
	
	private String motivoEncerramento;
	
	private String motivoEncerramento2;

	public RelatorioDocumentoCobrancaMotivosEncerramentoBean() {
		super();
	}

	public RelatorioDocumentoCobrancaMotivosEncerramentoBean(
			String motivoEncerramento, String motivoEncerramento2) {
		super();
		this.motivoEncerramento = motivoEncerramento;
		this.motivoEncerramento2 = motivoEncerramento2;
	}

	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}

	public String getMotivoEncerramento2() {
		return motivoEncerramento2;
	}

	public void setMotivoEncerramento2(String motivoEncerramento2) {
		this.motivoEncerramento2 = motivoEncerramento2;
	}

}

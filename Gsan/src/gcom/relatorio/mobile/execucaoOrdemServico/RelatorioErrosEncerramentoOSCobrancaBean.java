package gcom.relatorio.mobile.execucaoOrdemServico;

import gcom.relatorio.RelatorioBean;

public class RelatorioErrosEncerramentoOSCobrancaBean implements RelatorioBean {
	
	private String numeroOS;
	private String erro;
	
	public String getNumeroOS() {
		return numeroOS;
	}
	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}
	public String getErro() {
		return erro;
	}
	public void setErro(String erro) {
		this.erro = erro;
	}
	
	
	
}

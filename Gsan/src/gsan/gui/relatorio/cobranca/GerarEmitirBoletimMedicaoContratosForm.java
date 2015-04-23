package gsan.gui.relatorio.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * [UC1250] Solicitar Geração/Emissão Boletim de Medição de Contratos
 * 
 * @author Mariana Victor
 * @date 21/11/2011
 * */
public class GerarEmitirBoletimMedicaoContratosForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idEmpresa;
	
	private String idContrato;
	
	private String mesAnoReferencia;
	
	private String formaGeracao;
	
	private String tipoOperacao;
	
	private String[] idsBoletim;
	
	private String idBoletimContrato;

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String getFormaGeracao() {
		return formaGeracao;
	}

	public void setFormaGeracao(String formaGeracao) {
		this.formaGeracao = formaGeracao;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public String[] getIdsBoletim() {
		return idsBoletim;
	}

	public void setIdsBoletim(String[] idsBoletim) {
		this.idsBoletim = idsBoletim;
	}

	public String getIdBoletimContrato() {
		return idBoletimContrato;
	}

	public void setIdBoletimContrato(String idBoletimContrato) {
		this.idBoletimContrato = idBoletimContrato;
	}

}

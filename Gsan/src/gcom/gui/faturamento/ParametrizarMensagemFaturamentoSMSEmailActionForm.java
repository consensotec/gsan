package gcom.gui.faturamento;

import org.apache.struts.action.ActionForm;

public class ParametrizarMensagemFaturamentoSMSEmailActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String anoMesFaturamento;
	private String valorMinimoConta;
	private String[] categorias;
	private String selecionarDadosParaTexto;
	private String descricaoTextoSMS;
	private String descricaoTextoEmail;
	private String quantidadeDiasAntesVencimento;
	private String quantidadeTentativaEnvio;
	private String indicadorComercial;
	private String indicadorIndustrial;
	private String indicadorPublico;
	private String indicadorResidencial;
	private String maximoSMSSP;
	
	public ParametrizarMensagemFaturamentoSMSEmailActionForm(){}

	public String getAnoMesFaturamento() {
		return anoMesFaturamento;
	}
	public void setAnoMesFaturamento(String anoMesFaturamento) {
		this.anoMesFaturamento = anoMesFaturamento;
	}

	public String getValorMinimoConta() {
		return valorMinimoConta;
	}
	public void setValorMinimoConta(String valorMinimoConta) {
		this.valorMinimoConta = valorMinimoConta;
	}

	public String[] getCategorias() {
		return categorias;
	}
	public void setCategorias(String[] categorias) {
		this.categorias = categorias;
	}

	public String getSelecionarDadosParaTexto() {
		return selecionarDadosParaTexto;
	}
	public void setSelecionarDadosParaTexto(String selecionarDadosParaTexto) {
		this.selecionarDadosParaTexto = selecionarDadosParaTexto;
	}

	public String getDescricaoTextoSMS() {
		return descricaoTextoSMS;
	}
	public void setDescricaoTextoSMS(String descricaoTextoSMS) {
		this.descricaoTextoSMS = descricaoTextoSMS;
	}

	public String getDescricaoTextoEmail() {
		return descricaoTextoEmail;
	}
	public void setDescricaoTextoEmail(String descricaoTextoEmail) {
		this.descricaoTextoEmail = descricaoTextoEmail;
	}

	public String getQuantidadeDiasAntesVencimento() {
		return quantidadeDiasAntesVencimento;
	}
	public void setQuantidadeDiasAntesVencimento(
			String quantidadeDiasAntesVencimento) {
		this.quantidadeDiasAntesVencimento = quantidadeDiasAntesVencimento;
	}

	public String getQuantidadeTentativaEnvio() {
		return quantidadeTentativaEnvio;
	}
	public void setQuantidadeTentativaEnvio(String quantidadeTentativaEnvio) {
		this.quantidadeTentativaEnvio = quantidadeTentativaEnvio;
	}

	public String getIndicadorComercial() {
		return indicadorComercial;
	}

	public void setIndicadorComercial(String indicadorComercial) {
		this.indicadorComercial = indicadorComercial;
	}

	public String getIndicadorIndustrial() {
		return indicadorIndustrial;
	}

	public void setIndicadorIndustrial(String indicadorIndustrial) {
		this.indicadorIndustrial = indicadorIndustrial;
	}

	public String getIndicadorPublico() {
		return indicadorPublico;
	}

	public void setIndicadorPublico(String indicadorPublico) {
		this.indicadorPublico = indicadorPublico;
	}

	public String getIndicadorResidencial() {
		return indicadorResidencial;
	}

	public void setIndicadorResidencial(String indicadorResidencial) {
		this.indicadorResidencial = indicadorResidencial;
	}

	public String getMaximoSMSSP() {
		return maximoSMSSP;
	}

	public void setMaximoSMSSP(String maximoSMSSP) {
		this.maximoSMSSP = maximoSMSSP;
	}
}
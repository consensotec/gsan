package gcom.gui.relatorio.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * [UC1153] Solicitar Geração/Emissão Boletim de Medição de Cobrança
 * 
 * @author Mariana Victor
 * @date 18/03/2011
 * 
 * */
public class GerarRelatorioBoletimMedicaoCobrancaForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String grupoCobranca;
	
	private String mesAnoReferencia;
	
	private String formaGeracao;
	
	private String tipoOperacao;
	
	private String empresa;
	
	private String idNumeroContrato;
	
	public String getIdNumeroContrato() {
		return idNumeroContrato;
	}

	public void setIdNumeroContrato(String idNumeroContrato) {
		this.idNumeroContrato = idNumeroContrato;
	}

	// Operação do tipo "Emitir"
	private String gerenciaRegional;
	private String unidadeNegocio;
    private String idLocalidadeInicial;
    private String idLocalidadeFinal;
    private String descricaoLocalidadeInicial;
    private String descricaoLocalidadeFinal;
	

	public void reset(){
		this.grupoCobranca = null;
		this.mesAnoReferencia = null;
		this.formaGeracao = "1";
	}
	
	public GerarRelatorioBoletimMedicaoCobrancaForm(){
		this.formaGeracao = "1";
	}

	public String getGrupoCobranca() {
		return grupoCobranca;
	}

	public void setGrupoCobranca(String grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
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

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getDescricaoLocalidadeFinal() {
		return descricaoLocalidadeFinal;
	}

	public void setDescricaoLocalidadeFinal(String descricaoLocalidadeFinal) {
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
	}

	public String getDescricaoLocalidadeInicial() {
		return descricaoLocalidadeInicial;
	}

	public void setDescricaoLocalidadeInicial(String descricaoLocalidadeInicial) {
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
	}

	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

}

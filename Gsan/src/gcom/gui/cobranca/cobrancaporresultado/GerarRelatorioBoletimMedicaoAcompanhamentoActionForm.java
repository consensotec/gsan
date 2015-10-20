package gcom.gui.cobranca.cobrancaporresultado;

import org.apache.struts.action.ActionForm;

/**
 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
 * 
 * @author Hugo Azevedo
 * @date 13/10/2011
 */
public class GerarRelatorioBoletimMedicaoAcompanhamentoActionForm extends ActionForm{
    

	private static final long serialVersionUID = 1L;
	
	
	private String idEmpresaContratada;
	private String periodoApuracao;
	private String[] idsGerenciaRegional;
	private String[] idsUnidadeNegocio;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String[] idsRegiao;
	private String[] idsMicroregiao;
	private String[] idsMunicipio;
	private String opcaoBoletim;
	private String opcaoRelatorio;
	private String encerramentoArrecadacao;
	
	public String getIdEmpresaContratada() {
		return idEmpresaContratada;
	}
	public void setIdEmpresaContratada(String idEmpresaContratada) {
		this.idEmpresaContratada = idEmpresaContratada;
	}
	public String getPeriodoApuracao() {
		return periodoApuracao;
	}
	public void setPeriodoApuracao(String periodoApuracao) {
		this.periodoApuracao = periodoApuracao;
	}
	public String[] getIdsGerenciaRegional() {
		return idsGerenciaRegional;
	}
	public void setIdsGerenciaRegional(String[] idsGerenciaRegional) {
		this.idsGerenciaRegional = idsGerenciaRegional;
	}
	public String[] getIdsUnidadeNegocio() {
		return idsUnidadeNegocio;
	}
	public void setIdsUnidadeNegocio(String[] idsUnidadeNegocio) {
		this.idsUnidadeNegocio = idsUnidadeNegocio;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String[] getIdsRegiao() {
		return idsRegiao;
	}
	public void setIdsRegiao(String[] idsRegiao) {
		this.idsRegiao = idsRegiao;
	}
	public String[] getIdsMicroregiao() {
		return idsMicroregiao;
	}
	public void setIdsMicroregiao(String[] idsMicroregiao) {
		this.idsMicroregiao = idsMicroregiao;
	}
	public String[] getIdsMunicipio() {
		return idsMunicipio;
	}
	public void setIdsMunicipio(String[] idsMunicipio) {
		this.idsMunicipio = idsMunicipio;
	}
	public String getOpcaoBoletim() {
		return opcaoBoletim;
	}
	public void setOpcaoBoletim(String opcaoBoletim) {
		this.opcaoBoletim = opcaoBoletim;
	}
	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}
	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}
	public String getEncerramentoArrecadacao() {
		return encerramentoArrecadacao;
	}
	public void setEncerramentoArrecadacao(String encerramentoArrecadacao) {
		this.encerramentoArrecadacao = encerramentoArrecadacao;
	}
	
	
    
}

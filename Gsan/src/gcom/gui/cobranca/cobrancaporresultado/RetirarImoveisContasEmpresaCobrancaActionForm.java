package gcom.gui.cobranca.cobrancaporresultado;

import org.apache.struts.action.ActionForm;

/**
 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
 * 
 * @author Raimundo Martins
 * @date 13/12/2011
 * */
public class RetirarImoveisContasEmpresaCobrancaActionForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	private String idEmpresa;

	private String nomeEmpresa;

	private String periodoComandoInicial;

	private String periodoComandoFinal;
	
	private Integer[] idRegistros;

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getPeriodoComandoInicial() {
		return periodoComandoInicial;
	}

	public void setPeriodoComandoInicial(String periodoComandoInicial) {
		this.periodoComandoInicial = periodoComandoInicial;
	}

	public String getPeriodoComandoFinal() {
		return periodoComandoFinal;
	}

	public void setPeriodoComandoFinal(String periodoComandoFinal) {
		this.periodoComandoFinal = periodoComandoFinal;
	}

	public Integer[] getIdRegistros() {
		return idRegistros;
	}

	public void setIdRegistros(Integer[] idRegistros) {
		this.idRegistros = idRegistros;
	}
		
}

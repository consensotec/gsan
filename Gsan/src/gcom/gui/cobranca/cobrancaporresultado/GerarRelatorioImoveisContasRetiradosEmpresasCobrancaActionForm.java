package gcom.gui.cobranca.cobrancaporresultado;

import org.apache.struts.action.ActionForm;

/**
 * [UC1257] - Gerar Relatório dos Imóveis e Contas Retirados das Empresas de Cobrança
 * 
 * @author Hugo Azevedo
 * @date 16/12/2011
 */
public class GerarRelatorioImoveisContasRetiradosEmpresasCobrancaActionForm extends ActionForm{
    

	private static final long serialVersionUID = 1L;
	
	private String amReferenciaInicial;
	private String amReferenciaFinal;
	private String idEmpresa;
	private String nomeEmpresa;
	
	public String getAmReferenciaInicial() {
		return amReferenciaInicial;
	}
	public void setAmReferenciaInicial(String amReferenciaInicial) {
		this.amReferenciaInicial = amReferenciaInicial;
	}
	public String getAmReferenciaFinal() {
		return amReferenciaFinal;
	}
	public void setAmReferenciaFinal(String amReferenciaFinal) {
		this.amReferenciaFinal = amReferenciaFinal;
	}
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
	
	
}

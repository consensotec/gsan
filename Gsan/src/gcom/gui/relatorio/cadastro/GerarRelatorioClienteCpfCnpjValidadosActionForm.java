package gcom.gui.relatorio.cadastro;

import org.apache.struts.action.ActionForm;

public class GerarRelatorioClienteCpfCnpjValidadosActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	private String idEmpresa;
    
    private String nomeEmpresa;
    
    private String idLeiturista;
    
    private String nomeLeiturista;
    
    private String idArquivo;
    
    private String descricaoArquivo;
    
    private String periodoInicial;
    
    private String periodoFinal;

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

	public String getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}

	public String getNomeLeiturista() {
		return nomeLeiturista;
	}

	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}

	public String getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(String idArquivo) {
		this.idArquivo = idArquivo;
	}

	public String getDescricaoArquivo() {
		return descricaoArquivo;
	}

	public void setDescricaoArquivo(String descricaoArquivo) {
		this.descricaoArquivo = descricaoArquivo;
	}

	public String getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public String getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}
    
	

}

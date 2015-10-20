package gcom.gui.atualizacaocadastral;

import org.apache.struts.action.ActionForm;

public class GerarRoteiroDispositivoMovelActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String idEmpresa;
	
	private String idLocalidade;
	
	private String codigoSetorComercial;
	
	private Integer[] quadra;
	private Integer[] quadrasSelecionadas;
	
	private String cadastrador;
	
	private String clienteUsuario;
	
	private String totalMatriculas;
	
	private String[] ligacaoAguaSituacao;
	
	private String [] idsRegistros;
	
	private String[] indicadorSituacaoImovel;
	
	private String indicadorGerarRoteiroPeloGEO;

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer[] getQuadra() {
		return quadra;
	}

	public void setQuadra(Integer[] quadra) {
		this.quadra = quadra;
	}

	public Integer[] getQuadrasSelecionadas() {
		return quadrasSelecionadas;
	}

	public void setQuadrasSelecionadas(Integer[] quadrasSelecionadas) {
		this.quadrasSelecionadas = quadrasSelecionadas;
	}

	public String getCadastrador() {
		return cadastrador;
	}

	public void setCadastrador(String cadastrador) {
		this.cadastrador = cadastrador;
	}

	public String getClienteUsuario() {
		return clienteUsuario;
	}

	public void setClienteUsuario(String clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
	}

	public String getTotalMatriculas() {
		return totalMatriculas;
	}

	public void setTotalMatriculas(String totalMatriculas) {
		this.totalMatriculas = totalMatriculas;
	}

	public String[] getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(String[] ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public String[] getIdsRegistros() {
		return idsRegistros;
	}

	public void setIdsRegistros(String[] idsRegistros) {
		this.idsRegistros = idsRegistros;
	}

	public String[] getIndicadorSituacaoImovel() {
		return indicadorSituacaoImovel;
	}

	public void setIndicadorSituacaoImovel(String[] indicadorSituacaoImovel) {
		this.indicadorSituacaoImovel = indicadorSituacaoImovel;
	}

	public String getIndicadorGerarRoteiroPeloGEO() {
		return indicadorGerarRoteiroPeloGEO;
	}

	public void setIndicadorGerarRoteiroPeloGEO(String indicadorGerarRoteiroPeloGEO) {
		this.indicadorGerarRoteiroPeloGEO = indicadorGerarRoteiroPeloGEO;
	}
	
	public void reset(){
		this.idEmpresa = "-1";
		this.idLocalidade = "-1";
		this.codigoSetorComercial = "-1";
		this.quadra = null;
		this.quadrasSelecionadas = null;
		this.cadastrador = "-1";
		this.clienteUsuario = "3";
		this.totalMatriculas = "0";
		this.ligacaoAguaSituacao = null;
		this.idsRegistros = null;
	}
	
	

}

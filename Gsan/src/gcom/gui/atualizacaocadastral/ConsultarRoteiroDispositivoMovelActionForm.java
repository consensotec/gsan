package gcom.gui.atualizacaocadastral;

import org.apache.struts.action.ActionForm;

/**
 * [UC 1392] - Consultar Roteiro Dispositivo Móvel Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 26/11/2012
 *
 */
public class ConsultarRoteiroDispositivoMovelActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String idLocalidade;
	
	private String descricaoLocalidade;
	
	private String codigoSetorComercial;
	
	private String descricaoSetorComercial;
	
	private String numeroQuadra;
	
	private String descricaoQuadra;
	
	private String cadastrador;
	
	private String situacaoArquivo;
	
	private String [] idsRegistros;
	
	private String leituristaID;

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

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}

	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getDescricaoQuadra() {
		return descricaoQuadra;
	}

	public void setDescricaoQuadra(String descricaoQuadra) {
		this.descricaoQuadra = descricaoQuadra;
	}

	public String getCadastrador() {
		return cadastrador;
	}

	public void setCadastrador(String cadastrador) {
		this.cadastrador = cadastrador;
	}

	public String getSituacaoArquivo() {
		return situacaoArquivo;
	}

	public void setSituacaoArquivo(String situacaoArquivo) {
		this.situacaoArquivo = situacaoArquivo;
	}
	
	public String getLeituristaID() {
		return leituristaID;
	}

	public void setLeituristaID(String leituristaID) {
		this.leituristaID = leituristaID;
	}

	public String[] getIdsRegistros() {
		return idsRegistros;
	}

	public void setIdsRegistros(String[] idsRegistros) {
		this.idsRegistros = idsRegistros;
	}

	public void reset(){
		this.idLocalidade = "";
		this.descricaoLocalidade = "";
		this.codigoSetorComercial = "";
		this.descricaoSetorComercial = "";
		this.numeroQuadra = "";
		this.descricaoQuadra = "";
		this.cadastrador = "-1";
		this.situacaoArquivo = "";
		this.leituristaID = "-1";
		this.idsRegistros = null; 
	}
	
}
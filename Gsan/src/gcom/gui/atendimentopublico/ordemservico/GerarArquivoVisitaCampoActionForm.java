package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.action.ActionForm;


/**
 * [UC1220] Gerar Arquivo Texto para as Ordens de Serviço de Visita
 * 
 * @author Raimundo Martins
 * @since 09/09/2011
 */
public class GerarArquivoVisitaCampoActionForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String localidade;
	private String nomeLocalidade;
	private String idSetorComercialOrigem;
	private String codigoSetorComercialOrigem;
	private String descricaoSetorComercialOrigem;
	private String codigoQuadraInicial;
	private String descricaoQuadraInicial;
	private String idSetorComercialDestino;
	private String codigoSetorComercialDestino;
	private String descricaoSetorComercialDestino;
	private String codigoQuadraFinal;
	private String descricaoQuadraFinal;
	private String comando;
	private String comandoDesc;
	private String qtdOsSeletiva;

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getCodigoSetorComercialOrigem() {
		return codigoSetorComercialOrigem;
	}

	public void setCodigoSetorComercialOrigem(String codigoSetorComercialOrigem) {
		this.codigoSetorComercialOrigem = codigoSetorComercialOrigem;
	}

	public String getCodigoQuadraFinal() {
		return codigoQuadraFinal;
	}

	public void setCodigoQuadraFinal(String codigoQuadraFinal) {
		this.codigoQuadraFinal = codigoQuadraFinal;
	}

	public String getCodigoQuadraInicial() {
		return codigoQuadraInicial;
	}

	public void setCodigoQuadraInicial(String codigoQuadraInicial) {
		this.codigoQuadraInicial = codigoQuadraInicial;
	}

	public String getCodigoSetorComercialDestino() {
		return codigoSetorComercialDestino;
	}

	public void setCodigoSetorComercialDestino(String codigoSetorComercialDestino) {
		this.codigoSetorComercialDestino = codigoSetorComercialDestino;
	}

	public String getDescricaoQuadraFinal() {
		return descricaoQuadraFinal;
	}

	public void setDescricaoQuadraFinal(String descricaoQuadraFinal) {
		this.descricaoQuadraFinal = descricaoQuadraFinal;
	}

	public String getDescricaoQuadraInicial() {
		return descricaoQuadraInicial;
	}

	public void setDescricaoQuadraInicial(String descricaoQuadraInicial) {
		this.descricaoQuadraInicial = descricaoQuadraInicial;
	}

	public String getDescricaoSetorComercialDestino() {
		return descricaoSetorComercialDestino;
	}

	public void setDescricaoSetorComercialDestino(
			String descricaoSetorComercialDestino) {
		this.descricaoSetorComercialDestino = descricaoSetorComercialDestino;
	}

	public String getDescricaoSetorComercialOrigem() {
		return descricaoSetorComercialOrigem;
	}

	public void setDescricaoSetorComercialOrigem(
			String descricaoSetorComercialOrigem) {
		this.descricaoSetorComercialOrigem = descricaoSetorComercialOrigem;
	}

	public String getIdSetorComercialDestino() {
		return idSetorComercialDestino;
	}

	public void setIdSetorComercialDestino(String idSetorComercialDestino) {
		this.idSetorComercialDestino = idSetorComercialDestino;
	}

	public String getIdSetorComercialOrigem() {
		return idSetorComercialOrigem;
	}

	public void setIdSetorComercialOrigem(String idSetorComercialOrigem) {
		this.idSetorComercialOrigem = idSetorComercialOrigem;
	}

	public String getComando() {
		return comando;
	}

	public void setComando(String comando) {
		this.comando = comando;
	}

	public String getQtdOsSeletiva() {
		return qtdOsSeletiva;
	}

	public void setQtdOsSeletiva(String qtdOsSeletiva) {
		this.qtdOsSeletiva = qtdOsSeletiva;
	}

	public String getComandoDesc() {
		return comandoDesc;
	}

	public void setComandoDesc(String comandoDesc) {
		this.comandoDesc = comandoDesc;
	}
	
}

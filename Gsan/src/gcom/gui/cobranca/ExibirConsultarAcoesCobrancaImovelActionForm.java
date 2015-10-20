package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * [UC 1370] Consultar Ações de Cobrança por Imóvel
 * 
 * @author Davi Menezes
 * @date 15/08/2012
 *
 */
public class ExibirConsultarAcoesCobrancaImovelActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String idImovel;
	
	private String inscricaoImovel;
	
	private String periodoInicialAcao;
	
	private String periodoFinalAcao;
	
	private String grupoCobrancaAtual;
	
	private String descricaoSituacaoLigacaoAgua;
	
	private String descricaoSituacaoLigacaoEsgoto;
	
	private String enderecoImovel;

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getPeriodoInicialAcao() {
		return periodoInicialAcao;
	}

	public void setPeriodoInicialAcao(String periodoInicialAcao) {
		this.periodoInicialAcao = periodoInicialAcao;
	}

	public String getPeriodoFinalAcao() {
		return periodoFinalAcao;
	}

	public void setPeriodoFinalAcao(String periodoFinalAcao) {
		this.periodoFinalAcao = periodoFinalAcao;
	}

	public String getGrupoCobrancaAtual() {
		return grupoCobrancaAtual;
	}

	public void setGrupoCobrancaAtual(String grupoCobrancaAtual) {
		this.grupoCobrancaAtual = grupoCobrancaAtual;
	}

	public String getDescricaoSituacaoLigacaoAgua() {
		return descricaoSituacaoLigacaoAgua;
	}

	public void setDescricaoSituacaoLigacaoAgua(String descricaoSituacaoLigacaoAgua) {
		this.descricaoSituacaoLigacaoAgua = descricaoSituacaoLigacaoAgua;
	}

	public String getDescricaoSituacaoLigacaoEsgoto() {
		return descricaoSituacaoLigacaoEsgoto;
	}

	public void setDescricaoSituacaoLigacaoEsgoto(String descricaoSituacaoLigacaoEsgoto) {
		this.descricaoSituacaoLigacaoEsgoto = descricaoSituacaoLigacaoEsgoto;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}
	
}

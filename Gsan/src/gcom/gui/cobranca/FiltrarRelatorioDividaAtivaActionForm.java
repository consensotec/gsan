package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * UC0000 - Filtrar Relatório Divida Ativa 
 * 
 * @author Anderson Cabral
 * @since 12/02/2014
 */
public class FiltrarRelatorioDividaAtivaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String indicadorTipoRelatorio;
	private String indicadorIntra;
	private String periodoInscricaoInicial;
	private String periodoInscricaoFinal;
	private String periodoAtualizacaoInicial;
	private String periodoAtualizacaoFinal;
	private String idImovel;
	private String inscricaoImovel;
	private String indicadorRelatorioSinteticoAnalitico;
	
	public String getIndicadorTipoRelatorio() {
		return indicadorTipoRelatorio;
	}
	public void setIndicadorTipoRelatorio(String indicadorTipoRelatorio) {
		this.indicadorTipoRelatorio = indicadorTipoRelatorio;
	}
	public String getIndicadorIntra() {
		return indicadorIntra;
	}
	public void setIndicadorIntra(String indicadorIntra) {
		this.indicadorIntra = indicadorIntra;
	}
	public String getPeriodoInscricaoInicial() {
		return periodoInscricaoInicial;
	}
	public void setPeriodoInscricaoInicial(String periodoInscricaoInicial) {
		this.periodoInscricaoInicial = periodoInscricaoInicial;
	}
	public String getPeriodoInscricaoFinal() {
		return periodoInscricaoFinal;
	}
	public void setPeriodoInscricaoFinal(String periodoInscricaoFinal) {
		this.periodoInscricaoFinal = periodoInscricaoFinal;
	}
	public String getPeriodoAtualizacaoInicial() {
		return periodoAtualizacaoInicial;
	}
	public void setPeriodoAtualizacaoInicial(String periodoAtualizacaoInicial) {
		this.periodoAtualizacaoInicial = periodoAtualizacaoInicial;
	}
	public String getPeriodoAtualizacaoFinal() {
		return periodoAtualizacaoFinal;
	}
	public void setPeriodoAtualizacaoFinal(String periodoAtualizacaoFinal) {
		this.periodoAtualizacaoFinal = periodoAtualizacaoFinal;
	}
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
	public String getIndicadorRelatorioSinteticoAnalitico() {
		return indicadorRelatorioSinteticoAnalitico;
	}
	public void setIndicadorRelatorioSinteticoAnalitico(String indicadorRelatorioSinteticoAnalitico) {
		this.indicadorRelatorioSinteticoAnalitico = indicadorRelatorioSinteticoAnalitico;
	}
}

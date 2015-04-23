package gsan.gui.relatorio.atendimentopublico;

import java.io.Serializable;

/**
 * [UC1254] Relatório Ordem de Serviço com valores de cobrança
 * @author Amélia Pessoa
 * @date 24/11/2011
 */

public class FiltrarRelatorioDebitosCobrancaImovelHelper implements Serializable{

	private static final long serialVersionUID = 1L;

	private String idImovel;
	private String inscricaoImovel;
	
	private String tipoServico;
	private String nomeTipoServico;
	
	private String tipoMedicao;
	private String dataInicial;
	private String dataFinal;
		
	public FiltrarRelatorioDebitosCobrancaImovelHelper(){}
	
	public FiltrarRelatorioDebitosCobrancaImovelHelper(String idImovel, String inscricaoImovel, 
			String tipoServico, String nomeTipoServico, String tipoMedicao, String dataInicial, String dataFinal){
		this.idImovel = idImovel;
		this.inscricaoImovel = inscricaoImovel;
		this.tipoServico = tipoServico;
		this.nomeTipoServico = nomeTipoServico;
		this.tipoMedicao = tipoMedicao;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}
	

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getNomeTipoServico() {
		return nomeTipoServico;
	}

	public void setNomeTipoServico(String nomeTipoServico) {
		this.nomeTipoServico = nomeTipoServico;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
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

	public String getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	
}
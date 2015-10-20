package gcom.atualizacaocadastral.bean;

import java.io.Serializable;

public class ImoveisRoteiroDispositivoMovelHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
private String idImovel;
	
	private String insricaoImovel;
	
	private String setor;
	
	private String quadra;
	
	private String lote;
	
	private String subLote;
	
	private String rota;
	
	private String indicadorResetorizacao;
	
	private String matriculaFormatada;

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInsricaoImovel() {
		return insricaoImovel;
	}

	public void setInsricaoImovel(String insricaoImovel) {
		this.insricaoImovel = insricaoImovel;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getQuadra() {
		return quadra;
	}

	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getSubLote() {
		return subLote;
	}

	public void setSubLote(String subLote) {
		this.subLote = subLote;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getIndicadorResetorizacao() {
		return indicadorResetorizacao;
	}

	public void setIndicadorResetorizacao(String indicadorResetorizacao) {
		this.indicadorResetorizacao = indicadorResetorizacao;
	}
	
	public String getIdImovelFormatado(){
		if(idImovel != null && indicadorResetorizacao != null){
			return idImovel + "/" + indicadorResetorizacao;
		}else{
			return "";
		}
	}
	
	public String getMatriculaFormatada() {

		String matriculaImovelFormatada = this.idImovel;
		
		int quantidadeCaracteresImovel = this.idImovel.length();
			matriculaImovelFormatada = matriculaImovelFormatada.substring(0,
					quantidadeCaracteresImovel - 1)
					+ "."
					+ matriculaImovelFormatada.substring(
							quantidadeCaracteresImovel - 1,
							quantidadeCaracteresImovel);

		return matriculaImovelFormatada;
	}

	public void setMatriculaFormatada(String matriculaFormatada) {
		this.matriculaFormatada = matriculaFormatada;
	}
}
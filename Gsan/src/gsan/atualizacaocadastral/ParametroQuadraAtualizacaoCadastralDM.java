package gsan.atualizacaocadastral;

import java.util.Date;

import gsan.interceptor.ObjetoGcom;

public class ParametroQuadraAtualizacaoCadastralDM extends ObjetoGcom {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private int numeroQuadra;
	private ParametroTabelaAtualizacaoCadastralDM parametroTabelaAtualizacaoCadastralDM;
	private Date ultimaAlteracao;
	
	public ParametroQuadraAtualizacaoCadastralDM(){
		super();
	}
	
	public ParametroQuadraAtualizacaoCadastralDM(int numeroQuadra, ParametroTabelaAtualizacaoCadastralDM 
			parametroTabelaAtualizacaoCadastralDM, Date ultimaAlteracao){
		
		this.numeroQuadra = numeroQuadra;
		this.parametroTabelaAtualizacaoCadastralDM = parametroTabelaAtualizacaoCadastralDM;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public ParametroTabelaAtualizacaoCadastralDM getParametroTabelaAtualizacaoCadastralDM() {
		return parametroTabelaAtualizacaoCadastralDM;
	}

	public void setParametroTabelaAtualizacaoCadastralDM(ParametroTabelaAtualizacaoCadastralDM parametroTabelaAtualizacaoCadastralDM) {
		this.parametroTabelaAtualizacaoCadastralDM = parametroTabelaAtualizacaoCadastralDM;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		// TODO Auto-generated method stub
		return null;
	}
}
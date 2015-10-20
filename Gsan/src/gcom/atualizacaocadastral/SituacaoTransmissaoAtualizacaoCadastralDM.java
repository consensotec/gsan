package gcom.atualizacaocadastral;

import gcom.interceptor.ObjetoGcom;

import java.io.Serializable;
import java.util.Date;

public class SituacaoTransmissaoAtualizacaoCadastralDM extends ObjetoGcom implements Serializable{
	private static final long serialVersionUID = 1L;
	
    //Constantes situação transmissao leitura >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
    public final static Integer DISPONIVEL = new Integer(1);
    public final static Integer LIBERADO = new Integer(2);
    public final static Integer EM_CAMPO = new Integer(3);
    public final static Integer FINALIZADO = new Integer(4);
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	

	private Integer id;

    private String descricaoSituacao;

    private Short indicadorUso;

    private Date ultimaAlteracao;

	public SituacaoTransmissaoAtualizacaoCadastralDM() {
		super();
	}

    public SituacaoTransmissaoAtualizacaoCadastralDM(Integer id) {
		this.id = id;
	}

	public SituacaoTransmissaoAtualizacaoCadastralDM(
			Integer id, String descricaoSituacao, Short indicadorUso, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.descricaoSituacao = descricaoSituacao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoSituacao() {
		return descricaoSituacao;
	}

	public void setDescricaoSituacao(String descricaoSituacao) {
		this.descricaoSituacao = descricaoSituacao;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
}
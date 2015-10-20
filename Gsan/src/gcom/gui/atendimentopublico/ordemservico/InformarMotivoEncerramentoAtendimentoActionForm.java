package gcom.gui.atendimentopublico.ordemservico;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [U1246] Informar Motivo Encerramento Atendimento
 * 
 * @author Nathalia Santos
 * @date 26/10/2011
 */

public class InformarMotivoEncerramentoAtendimentoActionForm extends
	ValidatorActionForm{

	private String descricao;
	
	private String idMotivoEncerramento;
	
	private String descricaoAbreviada;
	
	private short indicadorUso;
	
	private Date ultimaAlteracao;
	
	private short indicadorExecucao;
	
	private short indicadorDuplicidade;
	
	private String qtdeDiasAditivoPrazo;
	
	private Short indicadorExibirFormOrdemSeletiva;
	
	private Short indicadorFiscalizacao;

	public String getDescricao() {
		return descricao;
	}

	private Short indicadorVisitaRealizada;

	public Short getIndicadorVisitaRealizada() {
		return indicadorVisitaRealizada;
	}

	public void setIndicadorVisitaRealizada(Short indicadorVisitaRealizada) {
		this.indicadorVisitaRealizada = indicadorVisitaRealizada;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}

	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}

	public Short getIndicadorExecucao() {
		return indicadorExecucao;
	}

	public void setIndicadorExecucao(Short indicadorExecucao) {
		this.indicadorExecucao = indicadorExecucao;
	}

	public Short getIndicadorDuplicidade() {
		return indicadorDuplicidade;
	}

	public void setIndicadorDuplicidade(Short indicadorDuplicidade) {
		this.indicadorDuplicidade = indicadorDuplicidade;
	}

	public String getQtdeDiasAditivoPrazo() {
		return qtdeDiasAditivoPrazo;
	}

	public void setQtdeDiasAditivoPrazo(String qtdeDiasAditivoPrazo) {
		this.qtdeDiasAditivoPrazo = qtdeDiasAditivoPrazo;
	}

	public Short getIndicadorExibirFormOrdemSeletiva() {
		return indicadorExibirFormOrdemSeletiva;
	}

	public void setIndicadorExibirFormOrdemSeletiva(
			Short indicadorExibirFormOrdemSeletiva) {
		this.indicadorExibirFormOrdemSeletiva = indicadorExibirFormOrdemSeletiva;
	}

	public Short getIndicadorFiscalizacao() {
		return indicadorFiscalizacao;
	}

	public void setIndicadorFiscalizacao(Short indicadorFiscalizacao) {
		this.indicadorFiscalizacao = indicadorFiscalizacao;
	}

	
}

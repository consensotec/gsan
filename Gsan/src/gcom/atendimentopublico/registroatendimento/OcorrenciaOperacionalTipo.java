package gcom.atendimentopublico.registroatendimento;

import java.io.Serializable;
import java.util.Date;
/**
 * [UC1524] Inserir Motivo da Ocorrência Operacional
 * 
 * @author Jonathan Marcos
 * @date 09/07/2013
 * 
 */
public class OcorrenciaOperacionalTipo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private String descricaoAbreviada;
	private short indicadorUso;
	private Date ultimaAlteracao;
	
	public OcorrenciaOperacionalTipo(){};
	public OcorrenciaOperacionalTipo(Integer id,String descricao,String descricaoAbreviada,
			short indicadorUso,Date ultimaAlteracao){
		this.id = id;
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
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
}

package gsan.faturamento.contratodemanda;

import java.util.Date;

/**
 * [UC1226] Inserir Contrato de Demanda Condomínios Residenciais
 * 
 * @author Diogo Peixoto
 * @since 21/09/2011
 *
 */
public class ContratoDemandaSituacao {

	private Integer id;
	private String descricao;
	private Short indicadorUso;
	private Date ultimaAlteracao;
	
	public final static Integer ATIVO = new Integer(1);
	public final static Integer INATIVO = new Integer(2);
	
	public ContratoDemandaSituacao(){
		
	}

	public ContratoDemandaSituacao(Integer id){
		this.id = id;
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
}
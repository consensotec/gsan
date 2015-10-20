package gcom.cadastro.imovel;

import java.util.Date;

public class PerfilAlteracaoMotivo {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	public static final Integer INCLUSAO_CADASTRO = new Integer(1);
	public static final Integer ALTERACAO_CADASTRO = new Integer(2);
	public static final Integer INSTALACAO_HIDROMETRO = new Integer(3);
	public static final Integer ALTERACAO_CAPACIDADE_HIDROMETRO = new Integer(4);
	public static final Integer MEDIA_CONSUMO = new Integer(5);
	public static final Integer CONSUMO_REAL_CONSECUTIVO = new Integer(6);
	
	private Integer id;
	private String descricao;
	private String descricaoAbreviada;
	private Short indicadorUso;
	private String constante;
	private Date ultimaAlteracao;
	
	public PerfilAlteracaoMotivo(){}
	
	public PerfilAlteracaoMotivo(String descricao, String descricaoAbreviada, Short indicadorUso,
			String constante, Date ultimaAlteracao){
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public PerfilAlteracaoMotivo(Integer id){
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
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}
	public Short getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getConstante() {
		return constante;
	}
	public void setConstante(String constante) {
		this.constante = constante;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}

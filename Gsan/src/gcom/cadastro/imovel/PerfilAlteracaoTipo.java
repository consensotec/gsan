package gcom.cadastro.imovel;

import gcom.util.ConstantesSistema;

import java.util.Date;

public class PerfilAlteracaoTipo {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	public final static Integer ENQUADRAMENTO_CORPORATIVO = new Integer(1);
	public final static Integer ENQUADRAMENTO_GRANDE_CLIENTE = new Integer(2);
	public final static Integer DESENQUADRAMENTO_CORPORATIVO = new Integer(3);
	public final static Integer DESENQUADRAMENTO_GRANDE_CLIENTE = new Integer(4);
	public final static Short MANUAL = ConstantesSistema.SIM;
	public final static Short AUTOMATICO = ConstantesSistema.NAO;
	
	private Integer id;
	private String descricao;
	private String descricaoAbreviada;
	private Short indicadorUso;
	private Date ultimaAlteracao;
	private String constante;
	
	public PerfilAlteracaoTipo(){}
	
	public PerfilAlteracaoTipo(Integer id){
		this.id = id;
	}
	
	public PerfilAlteracaoTipo(String descricao, String descricaoAbreviada, 
			   Short indicadorUso, Date ultimaAlteracao, String constante){
		
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.constante = constante;
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
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public String getConstante() {
		return constante;
	}
	public void setConstante(String constante) {
		this.constante = constante;
	}
}

package gsan.gui.seguranca.parametrosistema;

import org.apache.struts.validator.ValidatorActionForm;

public class AtualizarParametroSistemaActionForm extends ValidatorActionForm {
	
	
	private static final long serialVersionUID = 1L;	
	
	private String id;
	
	private String  nome;
	
	private String codigoTipo;
	
	private String descricaoTipo;
	
	private String descricao;
	
	private String codigoConstante;
	
	private String valorParametro;
	
	private String indicadorParametroRestrito;
	
	private String moduloId;
	
	private String descricaoModulo;
	
	private String indicadorUso;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoTipo() {
		return codigoTipo;
	}

	public void setCodigoTipo(String codigoTipo) {
		this.codigoTipo = codigoTipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigoConstante() {
		return codigoConstante;
	}

	public void setCodigoConstante(String codigoConstante) {
		this.codigoConstante = codigoConstante;
	}

	public String getValorParametro() {
		return valorParametro;
	}

	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

	public String getIndicadorParametroRestrito() {
		return indicadorParametroRestrito;
	}

	public void setIndicadorParametroRestrito(String indicadorParametroRestrito) {
		this.indicadorParametroRestrito = indicadorParametroRestrito;
	}

	public String getModuloId() {
		return moduloId;
	}

	public void setModuloId(String moduloId) {
		this.moduloId = moduloId;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getDescricaoTipo() {
		return descricaoTipo;
	}

	public void setDescricaoTipo(String descricaoTipo) {
		this.descricaoTipo = descricaoTipo;
	}

	public String getDescricaoModulo() {
		return descricaoModulo;
	}

	public void setDescricaoModulo(String descricaoModulo) {
		this.descricaoModulo = descricaoModulo;
	}
	
	
	
	
	

}

package gcom.cadastro.imovel;

import java.io.Serializable;

public class FiltroImovelPerfilHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricao;
	
	private Short indicadorUso;
	
	private Short indicadorGeracaoAutomatica;
	
	private Short indicadorInserirManterPerfil;
	
	private Short indicadorGerarDadosLeitura;
	
	private Short indicadorBloquearRetificacao;

	private Short indicadorGrandeConsumidor;
	
	private Short indicadorBloquearDadosSocial;
	
	private Short indicadorGeraDebitoSegundaViaConta;
	
	private Short indicadorNegativacaoDoCliente;
	
	private Short indicadorCorporativo;
	
	private Short indicadorPerfilTelemedido;
	
	private Integer consumoMinimo;
	
	private String[] hidrometroCapacidades;
	
	private String tipoPesquisa;
	
	public FiltroImovelPerfilHelper(){}

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

	public Short getIndicadorGeracaoAutomatica() {
		return indicadorGeracaoAutomatica;
	}

	public void setIndicadorGeracaoAutomatica(Short indicadorGeracaoAutomatica) {
		this.indicadorGeracaoAutomatica = indicadorGeracaoAutomatica;
	}

	public Short getIndicadorInserirManterPerfil() {
		return indicadorInserirManterPerfil;
	}

	public void setIndicadorInserirManterPerfil(Short indicadorInserirManterPerfil) {
		this.indicadorInserirManterPerfil = indicadorInserirManterPerfil;
	}

	public Short getIndicadorGerarDadosLeitura() {
		return indicadorGerarDadosLeitura;
	}

	public void setIndicadorGerarDadosLeitura(Short indicadorGerarDadosLeitura) {
		this.indicadorGerarDadosLeitura = indicadorGerarDadosLeitura;
	}

	public Short getIndicadorBloquearRetificacao() {
		return indicadorBloquearRetificacao;
	}

	public void setIndicadorBloquearRetificacao(Short indicadorBloquearRetificacao) {
		this.indicadorBloquearRetificacao = indicadorBloquearRetificacao;
	}

	public Short getIndicadorGrandeConsumidor() {
		return indicadorGrandeConsumidor;
	}

	public void setIndicadorGrandeConsumidor(Short indicadorGrandeConsumidor) {
		this.indicadorGrandeConsumidor = indicadorGrandeConsumidor;
	}

	public Short getIndicadorBloquearDadosSocial() {
		return indicadorBloquearDadosSocial;
	}

	public void setIndicadorBloquearDadosSocial(Short indicadorBloquearDadosSocial) {
		this.indicadorBloquearDadosSocial = indicadorBloquearDadosSocial;
	}

	public Short getIndicadorGeraDebitoSegundaViaConta() {
		return indicadorGeraDebitoSegundaViaConta;
	}

	public void setIndicadorGeraDebitoSegundaViaConta(
			Short indicadorGeraDebitoSegundaViaConta) {
		this.indicadorGeraDebitoSegundaViaConta = indicadorGeraDebitoSegundaViaConta;
	}

	public Short getIndicadorNegativacaoDoCliente() {
		return indicadorNegativacaoDoCliente;
	}

	public void setIndicadorNegativacaoDoCliente(Short indicadorNegativacaoDoCliente) {
		this.indicadorNegativacaoDoCliente = indicadorNegativacaoDoCliente;
	}

	public Short getIndicadorCorporativo() {
		return indicadorCorporativo;
	}

	public void setIndicadorCorporativo(Short indicadorCorporativo) {
		this.indicadorCorporativo = indicadorCorporativo;
	}

	public Short getIndicadorPerfilTelemedido() {
		return indicadorPerfilTelemedido;
	}

	public void setIndicadorPerfilTelemedido(Short indicadorPerfilTelemedido) {
		this.indicadorPerfilTelemedido = indicadorPerfilTelemedido;
	}

	public Integer getConsumoMinimo() {
		return consumoMinimo;
	}

	public void setConsumoMinimo(Integer consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public String[] getHidrometroCapacidades() {
		return hidrometroCapacidades;
	}

	public void setHidrometroCapacidades(String[] hidrometroCapacidades) {
		this.hidrometroCapacidades = hidrometroCapacidades;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
}
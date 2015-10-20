package gcom.gui.cadastro.imovel;

import gcom.micromedicao.hidrometro.HidrometroCapacidade;

import java.util.Collection;

import org.apache.struts.validator.ValidatorForm;

public class FiltrarImovelPerfilActionForm extends ValidatorForm{

	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String descricao;
	
	private String indicadorUso;
	
	private String indicadorGeracaoAutomatica;
	
	private String indicadorInserirManterPerfil;
	
	private String indicadorGerarDadosLeitura;
	
	private String indicadorBloquearRetificacao;
	
	private String indicadorGrandeConsumidor;
	
	private String indicadorBloquearDadosSocial;
	
	private String indicadorGeraDebitoSegundaViaConta;
	
	private String ultimaAlteracao;
	
	private String indicadorAtualizar;
	
	private String tipoPesquisa;
	
	private String indicadorNegativacaoDoCliente;
	
	private String indicadorCorporativo;
	
	private String indicadorPerfilTelemedido;
	
	private String consumoMinimo;
	
	private String[] hidrometroCapacidades;
	
	private String hidrometroCapacidadesHidden;
	
	private Collection<HidrometroCapacidade> colecaoHidrometroCapacidade;
	
	public String getDescricao(){
		return descricao;
	}
	
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getIndicadorGeracaoAutomatica() {
		return indicadorGeracaoAutomatica;
	}

	public void setIndicadorGeracaoAutomatica(String indicadorGeracaoAutomatica) {
		this.indicadorGeracaoAutomatica = indicadorGeracaoAutomatica;
	}

	public String getIndicadorInserirManterPerfil() {
		return indicadorInserirManterPerfil;
	}

	public void setIndicadorInserirManterPerfil(String indicadorInserirManterPerfil) {
		this.indicadorInserirManterPerfil = indicadorInserirManterPerfil;
	}

	public String getIndicadorGerarDadosLeitura() {
		return indicadorGerarDadosLeitura;
	}

	public void setIndicadorGerarDadosLeitura(String indicadorGerarDadosLeitura) {
		this.indicadorGerarDadosLeitura = indicadorGerarDadosLeitura;
	}

	public String getIndicadorBloquearRetificacao() {
		return indicadorBloquearRetificacao;
	}

	public void setIndicadorBloquearRetificacao(String indicadorBloquearRetificacao) {
		this.indicadorBloquearRetificacao = indicadorBloquearRetificacao;
	}

	public String getIndicadorGrandeConsumidor() {
		return indicadorGrandeConsumidor;
	}

	public void setIndicadorGrandeConsumidor(String indicadorGrandeConsumidor) {
		this.indicadorGrandeConsumidor = indicadorGrandeConsumidor;
	}

	public String getIndicadorBloquearDadosSocial() {
		return indicadorBloquearDadosSocial;
	}

	public void setIndicadorBloquearDadosSocial(String indicadorBloquearDadosSocial) {
		this.indicadorBloquearDadosSocial = indicadorBloquearDadosSocial;
	}

	public String getIndicadorGeraDebitoSegundaViaConta() {
		return indicadorGeraDebitoSegundaViaConta;
	}

	public void setIndicadorGeraDebitoSegundaViaConta(String indicadorGeraDebitoSegundaViaConta) {
		this.indicadorGeraDebitoSegundaViaConta = indicadorGeraDebitoSegundaViaConta;
	}
	
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getIndicadorNegativacaoDoCliente() {
		return indicadorNegativacaoDoCliente;
	}

	public void setIndicadorNegativacaoDoCliente(String indicadorNegativacaoDoCliente) {
		this.indicadorNegativacaoDoCliente = indicadorNegativacaoDoCliente;
	}

	public String getIndicadorCorporativo() {
		return indicadorCorporativo;
	}

	public void setIndicadorCorporativo(String indicadorCorporativo) {
		this.indicadorCorporativo = indicadorCorporativo;
	}

	public String getIndicadorPerfilTelemedido() {
		return indicadorPerfilTelemedido;
	}

	public void setIndicadorPerfilTelemedido(String indicadorPerfilTelemedido) {
		this.indicadorPerfilTelemedido = indicadorPerfilTelemedido;
	}

	public String getConsumoMinimo() {
		return consumoMinimo;
	}

	public void setConsumoMinimo(String consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public String[] getHidrometroCapacidades() {
		return hidrometroCapacidades;
	}

	public void setHidrometroCapacidades(String[] hidrometroCapacidades) {
		this.hidrometroCapacidades = hidrometroCapacidades;
	}

	public Collection<HidrometroCapacidade> getColecaoHidrometroCapacidade() {
		return colecaoHidrometroCapacidade;
	}

	public void setColecaoHidrometroCapacidade(
			Collection<HidrometroCapacidade> colecaoHidrometroCapacidade) {
		this.colecaoHidrometroCapacidade = colecaoHidrometroCapacidade;
	}

	public String getHidrometroCapacidadesHidden() {
		return hidrometroCapacidadesHidden;
	}

	public void setHidrometroCapacidadesHidden(String hidrometroCapacidadesHidden) {
		this.hidrometroCapacidadesHidden = hidrometroCapacidadesHidden;
	}
}
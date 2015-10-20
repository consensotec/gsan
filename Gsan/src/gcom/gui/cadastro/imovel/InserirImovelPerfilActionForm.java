package gcom.gui.cadastro.imovel;

import gcom.micromedicao.hidrometro.HidrometroCapacidade;

import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

public class InserirImovelPerfilActionForm extends ValidatorActionForm{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String descricao;
	
	private Short indicadorUso;
	
	private Short indicadorGeracaoAutomatica;
	
	private Short indicadorInserirManterPerfil;
	
	private Short indicadorGerarDadosLeitura;
	
	private Short indicadorBloquearRetificacao;
	
	private Short indicadorGrandeConsumidor;
	
	private Short indicadorBloqueaDadosSocial;
	
	private Short indicadorGeraDebitoSegundaViaConta;

	private Short indicadorAcrescimosImpontualidade;
	
	private Short indicadorNegativacaoDoCliente;
	
	private Short indicadorCorporativo;
	
	private Short indicadorPerfilTelemedido;
	
	private String consumoMinimo;
	
	private String[] hidrometroCapacidades;
	
	private String hidrometroCapacidadesHidden;
	
	private String idPermissaoEspecial;
	
	private String descricaoPermissaoEspecial;
	
	private Collection<HidrometroCapacidade> colecaoHidrometroCapacidade;

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

	public void setIndicadorGeracaoAutomatica(Short indicadorGeracaoAuto) {
		this.indicadorGeracaoAutomatica = indicadorGeracaoAuto;
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

	public Short getIndicadorBloqueaDadosSocial() {
		return indicadorBloqueaDadosSocial;
	}

	public void setIndicadorBloqueaDadosSocial(Short indicadorBloquearDadosSocial) {
		this.indicadorBloqueaDadosSocial = indicadorBloquearDadosSocial;
	}

	public Short getIndicadorGeraDebitoSegundaViaConta() {
		return indicadorGeraDebitoSegundaViaConta;
	}

	public void setIndicadorGeraDebitoSegundaViaConta(Short indicadorGeraDebitoSegundaViaConta) {
		this.indicadorGeraDebitoSegundaViaConta = indicadorGeraDebitoSegundaViaConta;
	}

	public Short getIndicadorAcrescimosImpontualidade() {
		return indicadorAcrescimosImpontualidade;
	}

	public void setIndicadorAcrescimosImpontualidade(Short indicadorAcrescimosImpontualidade) {
		this.indicadorAcrescimosImpontualidade = indicadorAcrescimosImpontualidade;
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

	public String getIdPermissaoEspecial() {
		return idPermissaoEspecial;
	}

	public void setIdPermissaoEspecial(String idPermissaoEspecial) {
		this.idPermissaoEspecial = idPermissaoEspecial;
	}

	public String getDescricaoPermissaoEspecial() {
		return descricaoPermissaoEspecial;
	}

	public void setDescricaoPermissaoEspecial(String descricaoPermissaoEspecial) {
		this.descricaoPermissaoEspecial = descricaoPermissaoEspecial;
	}

	public String getHidrometroCapacidadesHidden() {
		return hidrometroCapacidadesHidden;
	}

	public void setHidrometroCapacidadesHidden(String hidrometroCapacidadesHidden) {
		this.hidrometroCapacidadesHidden = hidrometroCapacidadesHidden;
	}
}
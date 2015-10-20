package gcom.gui.cobranca.contratoparcelamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * Form que Atualiza uma ContratoParcelamentoPorCliente
 * 
 * @author Paulo Ot�vio
 * @date 04/04/2011
 */
public class AtualizarContratoParcelamentoPorClienteActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idContrato = "";
	
	private String numeroContrato = "";
	
	private String numeroContratoAnt = "";
	
	private String relacaoAnterior = "";
	
	private String dataContrato = "";
	
	private String loginUsuario = "";
	
	private String nomeUsuario = "";
	
	private String autocompleteClienteSuperior = "";
	
	private String autocompleteCliente = "";
	
	private String relacaoCliente = "";
	
	private String indicadorResponsavel = "";
	
	private String anoMesDebitoInicio = "";
	private String anoMesDebitoFinal = "";
	
	private String dataVencimentoInicio = "";
	private String dataVencimentoFinal = "";
	
	private String observacao = "";
	
	private String[] contasSelecao;
	
	private String resolucaoDiretoria;
	
	private String indicadorDebitoAcresc = "";
	
	private String indicadorParcelJuros = "";
	
	private String indicadorInfoVlParcel = "";
	
	private String formaPgto = "";
	
	private String numeroEntreVencParcelas = "";
	
	private String dataVencimentoPrimParcela = "";
	
	private String pacerlaAdd = "";
	
	private String taxaJurosAdd = "";
	
	private String parcelaSelecao = "";
	
	private String numeroParcelasPopUp = "";
	
	private String valorParceladoPopUp = "";
	
	private String contasSelecionadas;
	
	private String debitosACobrarSelecionados;
	
	private String[] debitosSelecao;
	
	private String selecionouDebitosACobrar;
	
	private String selecionouContas;
	
	private String valorDaParcela;
	
	private String formaPagamentoRD;
	
	public AtualizarContratoParcelamentoPorClienteActionForm(){}

	public String getAnoMesDebitoFinal() {
		return anoMesDebitoFinal;
	}

	public void setAnoMesDebitoFinal(String anoMesDebitoFinal) {
		this.anoMesDebitoFinal = anoMesDebitoFinal;
	}

	public String getAnoMesDebitoInicio() {
		return anoMesDebitoInicio;
	}

	public void setAnoMesDebitoInicio(String anoMesDebitoInicio) {
		this.anoMesDebitoInicio = anoMesDebitoInicio;
	}

	public String getDataContrato() {
		return dataContrato;
	}

	public void setDataContrato(String dataContrato) {
		this.dataContrato = dataContrato;
	}

	public String getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	public void setDataVencimentoFinal(String dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	public String getDataVencimentoInicio() {
		return dataVencimentoInicio;
	}

	public void setDataVencimentoInicio(String dataVencimentoInicio) {
		this.dataVencimentoInicio = dataVencimentoInicio;
	}

	public String getIndicadorResponsavel() {
		return indicadorResponsavel;
	}

	public void setIndicadorResponsavel(String indicadorResponsavel) {
		this.indicadorResponsavel = indicadorResponsavel;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getNumeroContratoAnt() {
		return numeroContratoAnt;
	}

	public void setNumeroContratoAnt(String numeroContratoAnt) {
		this.numeroContratoAnt = numeroContratoAnt;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getRelacaoAnterior() {
		return relacaoAnterior;
	}

	public void setRelacaoAnterior(String relacaoAnterior) {
		this.relacaoAnterior = relacaoAnterior;
	}

	public String getRelacaoCliente() {
		return relacaoCliente;
	}

	public void setRelacaoCliente(String relacaoCliente) {
		this.relacaoCliente = relacaoCliente;
	}

	public String getAutocompleteCliente() {
		return autocompleteCliente;
	}

	public void setAutocompleteCliente(String autocompleteCliente) {
		this.autocompleteCliente = autocompleteCliente;
	}

	public String getAutocompleteClienteSuperior() {
		return autocompleteClienteSuperior;
	}

	public void setAutocompleteClienteSuperior(String autocompleteClienteSuperior) {
		this.autocompleteClienteSuperior = autocompleteClienteSuperior;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String[] getContasSelecao() {
		return contasSelecao;
	}

	public void setContasSelecao(String[] contasSelecao) {
		this.contasSelecao = contasSelecao;
	}

	public String getResolucaoDiretoria() {
		return resolucaoDiretoria;
	}

	public void setResolucaoDiretoria(String resolucaoDiretoria) {
		this.resolucaoDiretoria = resolucaoDiretoria;
	}
	
	public boolean verificaContaSelecionada(int idConta){
		boolean retorno = false;
		
		if (contasSelecao != null && contasSelecao.length > 0) {
			for (String conta : contasSelecao) {
				
				if(idConta == Integer.parseInt(conta)){
					return true;
				}
			}
		}
		
		return retorno;
	}
	
	public boolean verificaDebitoACobrarSelecionada(int idDebitoACobrar){
		boolean retorno = false;

		if (debitosSelecao != null && debitosSelecao.length > 0) {
			for (String debito : debitosSelecao) {
				
				if(idDebitoACobrar == Integer.parseInt(debito)){
					return true;
				}
			}
		}
		
		return retorno;
	}

	public String getIndicadorDebitoAcresc() {
		return indicadorDebitoAcresc;
	}

	public void setIndicadorDebitoAcresc(String indicadorDebitoAcresc) {
		this.indicadorDebitoAcresc = indicadorDebitoAcresc;
	}

	public String getIndicadorParcelJuros() {
		return indicadorParcelJuros;
	}

	public void setIndicadorParcelJuros(String indicadorParcelJuros) {
		this.indicadorParcelJuros = indicadorParcelJuros;
	}

	public String getIndicadorInfoVlParcel() {
		return indicadorInfoVlParcel;
	}

	public void setIndicadorInfoVlParcel(String indicadorInfoVlParcel) {
		this.indicadorInfoVlParcel = indicadorInfoVlParcel;
	}

	public String getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(String formaPgto) {
		this.formaPgto = formaPgto;
	}

	public String getNumeroEntreVencParcelas() {
		return numeroEntreVencParcelas;
	}

	public void setNumeroEntreVencParcelas(String numeroEntreVencParcelas) {
		this.numeroEntreVencParcelas = numeroEntreVencParcelas;
	}

	public String getDataVencimentoPrimParcela() {
		return dataVencimentoPrimParcela;
	}

	public void setDataVencimentoPrimParcela(String dataVencimentoPrimParcela) {
		this.dataVencimentoPrimParcela = dataVencimentoPrimParcela;
	}

	public String getPacerlaAdd() {
		return pacerlaAdd;
	}

	public void setPacerlaAdd(String pacerlaAdd) {
		this.pacerlaAdd = pacerlaAdd;
	}

	public String getTaxaJurosAdd() {
		return taxaJurosAdd;
	}

	public void setTaxaJurosAdd(String taxaJurosAdd) {
		this.taxaJurosAdd = taxaJurosAdd;
	}

	public String getParcelaSelecao() {
		return parcelaSelecao;
	}

	public void setParcelaSelecao(String parcelaSelecao) {
		this.parcelaSelecao = parcelaSelecao;
	}

	public String getNumeroParcelasPopUp() {
		return numeroParcelasPopUp;
	}

	public void setNumeroParcelasPopUp(String numeroParcelasPopUp) {
		this.numeroParcelasPopUp = numeroParcelasPopUp;
	}

	public String getValorParceladoPopUp() {
		return valorParceladoPopUp;
	}

	public void setValorParceladoPopUp(String valorParceladoPopUp) {
		this.valorParceladoPopUp = valorParceladoPopUp;
	}

	public String getContasSelecionadas() {
		return contasSelecionadas;
	}

	public void setContasSelecionadas(String contasSelecionadas) {
		this.contasSelecionadas = contasSelecionadas;
	}

	public String getDebitosACobrarSelecionados() {
		return debitosACobrarSelecionados;
	}

	public void setDebitosACobrarSelecionados(String debitosACobrarSelecionados) {
		this.debitosACobrarSelecionados = debitosACobrarSelecionados;
	}

	public String[] getDebitosSelecao() {
		return debitosSelecao;
	}

	public void setDebitosSelecao(String[] debitosSelecao) {
		this.debitosSelecao = debitosSelecao;
	}

	public String getSelecionouContas() {
		return selecionouContas;
	}

	public void setSelecionouContas(String selecionouContas) {
		this.selecionouContas = selecionouContas;
	}

	public String getSelecionouDebitosACobrar() {
		return selecionouDebitosACobrar;
	}

	public void setSelecionouDebitosACobrar(String selecionouDebitosACobrar) {
		this.selecionouDebitosACobrar = selecionouDebitosACobrar;
	}

	public String getValorDaParcela() {
		return valorDaParcela;
	}

	public void setValorDaParcela(String valorDaParcela) {
		this.valorDaParcela = valorDaParcela;
	}

	public String getFormaPagamentoRD() {
		return formaPagamentoRD;
	}

	public void setFormaPagamentoRD(String formaPagamentoRD) {
		this.formaPagamentoRD = formaPagamentoRD;
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

}

package gsan.cobranca;

import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.cadastro.MotivoRetiradaCobranca;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.imovel.Imovel;
import gsan.faturamento.conta.ContaGeral;
import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

public class EmpresaCobrancaConta extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private Empresa empresa;
	
	/** nullable persistent field */
	private ContaGeral contaGeral;
	
	/** nullable persistent field */
	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;

	/** nullable persistent field */
	private BigDecimal valorOriginalConta;
	
	/** nullable persistent field */
	private BigDecimal percentualEmpresaConta;
	
	/** nullable persistent field */
	private Short indicadorPagamentoValido;

	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	/** nullable persistent field */
	private ComandoEmpresaCobrancaContaExtensao comandoEmpresaCobrancaContaExtensao;
	
	private Imovel imovel;
	
	private OrdemServico ordemServico;
	
	private int anoMesReferenciaConta;
	
	private Date dataEnvioConta;
	
	private Date dataRetiradaConta;	
	
	private Short cdIncluido;
	
	private MotivoRetiradaCobranca motivoRetirada;
	
	/**
	* Description of the Field
	*/
	public final static Short INDICADOR_VALIDO_PAGAMENTO_SIM = new Short("1");

	/**
	* Description of the Field
	*/
	public final static Short INDICADOR_VALIDO_PAGAMENTO_NAO = new Short("2");
	
	
	public EmpresaCobrancaConta(Integer id, Empresa empresa, ContaGeral contaGeral,
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, BigDecimal valorOriginalConta, 
			BigDecimal percentualEmpresaConta, Short indicadorPagamentoValido, Date ultimaAlteracao) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.empresa = empresa;
		this.contaGeral = contaGeral;
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
		this.valorOriginalConta = valorOriginalConta;
		this.percentualEmpresaConta = percentualEmpresaConta;
		this.indicadorPagamentoValido = indicadorPagamentoValido;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public EmpresaCobrancaConta() {

	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroEmpresaCobrancaConta filtroEmpresaCobrancaConta = new FiltroEmpresaCobrancaConta();

		filtroEmpresaCobrancaConta.adicionarParametro(new ParametroSimples(
				FiltroEmpresaCobrancaConta.ID, this.getId()));
		return filtroEmpresaCobrancaConta;
	}

	/**
	 * @return Retorna o campo contaGeral.
	 */
	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	/**
	 * @param contaGeral O contaGeral a ser setado.
	 */
	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	/**
	 * @return Retorna o campo empresa.
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa O empresa a ser setado.
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo percentualEmpresaConta.
	 */
	public BigDecimal getPercentualEmpresaConta() {
		return percentualEmpresaConta;
	}

	/**
	 * @param percentualEmpresaConta O percentualEmpresaConta a ser setado.
	 */
	public void setPercentualEmpresaConta(BigDecimal percentualEmpresaConta) {
		this.percentualEmpresaConta = percentualEmpresaConta;
	}

	/**
	 * @return Retorna o campo valorOriginalConta.
	 */
	public BigDecimal getValorOriginalConta() {
		return valorOriginalConta;
	}

	/**
	 * @param valorOriginalConta O valorOriginalConta a ser setado.
	 */
	public void setValorOriginalConta(BigDecimal valorOriginalConta) {
		this.valorOriginalConta = valorOriginalConta;
	}

	@Override
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo comandoEmpresaCobrancaConta.
	 */
	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	/**
	 * @param comandoEmpresaCobrancaConta O comandoEmpresaCobrancaConta a ser setado.
	 */
	public void setComandoEmpresaCobrancaConta(
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	/**
	 * @return Retorna o campo indicadorPagamentoValido.
	 */
	public Short getIndicadorPagamentoValido() {
		return indicadorPagamentoValido;
	}

	/**
	 * @param indicadorPagamentoValido O indicadorPagamentoValido a ser setado.
	 */
	public void setIndicadorPagamentoValido(Short indicadorPagamentoValido) {
		this.indicadorPagamentoValido = indicadorPagamentoValido;
	}

	public ComandoEmpresaCobrancaContaExtensao getComandoEmpresaCobrancaContaExtensao() {
		return comandoEmpresaCobrancaContaExtensao;
	}

	public void setComandoEmpresaCobrancaContaExtensao(
			ComandoEmpresaCobrancaContaExtensao comandoEmpresaCobrancaContaExtensao) {
		this.comandoEmpresaCobrancaContaExtensao = comandoEmpresaCobrancaContaExtensao;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public int getAnoMesReferenciaConta() {
		return anoMesReferenciaConta;
	}

	public void setAnoMesReferenciaConta(int anoMesReferenciaConta) {
		this.anoMesReferenciaConta = anoMesReferenciaConta;
	}

	public Date getDataEnvioConta() {
		return dataEnvioConta;
	}

	public void setDataEnvioConta(Date dataEnvioConta) {
		this.dataEnvioConta = dataEnvioConta;
	}
	public Date getDataRetiradaConta() {		
		return dataRetiradaConta;	
	}	
	public void setDataRetiradaConta(Date dataRetiradaConta) {		
		this.dataRetiradaConta = dataRetiradaConta;	
	}

	public Short getCdIncluido() {
		return cdIncluido;
	}

	public void setCdIncluido(Short cdConcluido) {
		this.cdIncluido = cdConcluido;
	}

	public MotivoRetiradaCobranca getMotivoRetirada() {
		return motivoRetirada;
	}

	public void setMotivoRetirada(MotivoRetiradaCobranca motivoRetirada) {
		this.motivoRetirada = motivoRetirada;
	}
	
}

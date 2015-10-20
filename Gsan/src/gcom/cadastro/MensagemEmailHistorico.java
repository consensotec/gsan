package gcom.cadastro;

import gcom.cadastro.cliente.Cliente;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaDocumento;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.conta.Conta;

import java.io.Serializable;
import java.util.Date;

public class MensagemEmailHistorico implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
    private String descricaoMensagem;
    private String emailDestino;
    private Date dataPrevisaoEnvio;
    private Date dataLimiteEnvio;
    private Date dataEnvio;
    private Integer quantidadeTentativasEnvio;
    private Date ultimaAlteracao;
    private Integer anoMesReferenciaConta;
    
    private Cliente cliente;
    private ParametrosMSGSMSEmail parametroMensagemSMSEmail;
    private CobrancaAcao acaoCobranca;
    private Conta conta;
    private FaturamentoGrupo grupoFaturamento;
    private CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;
    private CobrancaDocumento cobrancaDocumento;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricaoMensagem() {
		return descricaoMensagem;
	}
	public void setDescricaoMensagem(String descricaoMensagem) {
		this.descricaoMensagem = descricaoMensagem;
	}
	public String getEmailDestino() {
		return emailDestino;
	}
	public void setEmailDestino(String emailDestino) {
		this.emailDestino = emailDestino;
	}
	public Date getDataPrevisaoEnvio() {
		return dataPrevisaoEnvio;
	}
	public void setDataPrevisaoEnvio(Date dataPrevisaoEnvio) {
		this.dataPrevisaoEnvio = dataPrevisaoEnvio;
	}
	public Date getDataLimiteEnvio() {
		return dataLimiteEnvio;
	}
	public void setDataLimiteEnvio(Date dataLimiteEnvio) {
		this.dataLimiteEnvio = dataLimiteEnvio;
	}
	public Date getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	public Integer getQuantidadeTentativasEnvio() {
		return quantidadeTentativasEnvio;
	}
	public void setQuantidadeTentativasEnvio(Integer quantidadeTentativasEnvio) {
		this.quantidadeTentativasEnvio = quantidadeTentativasEnvio;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public Integer getAnoMesReferenciaConta() {
		return anoMesReferenciaConta;
	}
	public void setAnoMesReferenciaConta(Integer anoMesReferenciaConta) {
		this.anoMesReferenciaConta = anoMesReferenciaConta;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ParametrosMSGSMSEmail getParametroMensagemSMSEmail() {
		return parametroMensagemSMSEmail;
	}
	public void setParametroMensagemSMSEmail(
			ParametrosMSGSMSEmail parametroMensagemSMSEmail) {
		this.parametroMensagemSMSEmail = parametroMensagemSMSEmail;
	}
	public CobrancaAcao getAcaoCobranca() {
		return acaoCobranca;
	}
	public void setAcaoCobranca(CobrancaAcao acaoCobranca) {
		this.acaoCobranca = acaoCobranca;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public FaturamentoGrupo getGrupoFaturamento() {
		return grupoFaturamento;
	}
	public void setGrupoFaturamento(FaturamentoGrupo grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}
	public CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
		return cobrancaAcaoAtividadeComando;
	}
	public void setCobrancaAcaoAtividadeComando(
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
	}
	public CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}
	public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}
}
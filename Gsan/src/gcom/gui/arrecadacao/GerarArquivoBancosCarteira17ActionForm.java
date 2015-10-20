package gcom.gui.arrecadacao;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;
/**
 * RN2013108067 – Mudança dos boletos bancarios: da carteira 18 para carteira 17
 * [UC1574] - Solicitar Geracao de Arquivo Carteira 17 
 * 
 * @author Diogo Luiz
 * @Date 07/11/2013
 *
 */
public class GerarArquivoBancosCarteira17ActionForm extends
	ValidatorActionForm {

	private static final long serialVersionUID = 1L;	
	
	private String bancoID;
	
	private String bancoDescricao;	
	
	private String opcaoMovimentoDebitoAutomatico;

	private String idGrupoFaturamento;
	
	private String idGrupoFaturamentoSelecionados;

	private String mesAnoFaturamento;

	private String opcaoEnvioParaBanco;
	
	private String idArrecadadorMovimento;

	private String codigoBancoMovimento;

	private String codigoRemessaMovimento;

	private String identificacaoServicoMovimento;

	private String numeroSequencialMovimento;
	
	private String[] idsCodigosBancos;
	
	private Date dataAtual;
	
	public String getBancoID() {
		return bancoID;
	}

	public void setBancoID(String bancoID) {
		this.bancoID = bancoID;
	}

	public String getBancoDescricao() {
		return bancoDescricao;
	}

	public void setBancoDescricao(String bancoDescricao) {
		this.bancoDescricao = bancoDescricao;
	}

	public String getOpcaoMovimentoDebitoAutomatico() {
		return opcaoMovimentoDebitoAutomatico;
	}

	public void setOpcaoMovimentoDebitoAutomatico(String opcaoMovimentoDebitoAutomatico) {
		this.opcaoMovimentoDebitoAutomatico = opcaoMovimentoDebitoAutomatico;
	}

	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getIdGrupoFaturamentoSelecionados() {
		return idGrupoFaturamentoSelecionados;
	}

	public void setIdGrupoFaturamentoSelecionados(String idGrupoFaturamentoSelecionados) {
		this.idGrupoFaturamentoSelecionados = idGrupoFaturamentoSelecionados;
	}

	public String getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}

	public void setMesAnoFaturamento(String mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}

	public String getOpcaoEnvioParaBanco() {
		return opcaoEnvioParaBanco;
	}

	public void setOpcaoEnvioParaBanco(String opcaoEnvioParaBanco) {
		this.opcaoEnvioParaBanco = opcaoEnvioParaBanco;
	}

	public String getIdArrecadadorMovimento() {
		return idArrecadadorMovimento;
	}

	public void setIdArrecadadorMovimento(String idArrecadadorMovimento) {
		this.idArrecadadorMovimento = idArrecadadorMovimento;
	}

	public String getCodigoBancoMovimento() {
		return codigoBancoMovimento;
	}

	public void setCodigoBancoMovimento(String codigoBancoMovimento) {
		this.codigoBancoMovimento = codigoBancoMovimento;
	}

	public String getCodigoRemessaMovimento() {
		return codigoRemessaMovimento;
	}

	public void setCodigoRemessaMovimento(String codigoRemessaMovimento) {
		this.codigoRemessaMovimento = codigoRemessaMovimento;
	}

	public String getIdentificacaoServicoMovimento() {
		return identificacaoServicoMovimento;
	}

	public void setIdentificacaoServicoMovimento(String identificacaoServicoMovimento) {
		this.identificacaoServicoMovimento = identificacaoServicoMovimento;
	}

	public String getNumeroSequencialMovimento() {
		return numeroSequencialMovimento;
	}

	public void setNumeroSequencialMovimento(String numeroSequencialMovimento) {
		this.numeroSequencialMovimento = numeroSequencialMovimento;
	}

	public String[] getIdsCodigosBancos() {
		return idsCodigosBancos;
	}

	public void setIdsCodigosBancos(String[] idsCodigosBancos) {
		this.idsCodigosBancos = idsCodigosBancos;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}
}
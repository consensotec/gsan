package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorForm;

public class EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String dataInstalSubstRet;
	private String idOrdemServico;
	private String descricaoOrdemServico;
	private String matriculaImovel;
	private String numeroHidrometroInstalado;
	private String numeroHidrometroAtual;
	private String leituraInstalacao;
	private String leituraRetirada;
	private String operacao;

	private String[] idRegistrosRemocao;
	
	
	public String[] getIdRegistrosRemocao() {
		return idRegistrosRemocao;
	}

	public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
		this.idRegistrosRemocao = idRegistrosRemocao;
	}

	public EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm() {
		super();
	}

	public String getDataInstalSubstRet() {
		return dataInstalSubstRet;
	}

	public void setDataInstalSubstRet(String dataInstalSubstRet) {
		this.dataInstalSubstRet = dataInstalSubstRet;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getDescricaoOrdemServico() {
		return descricaoOrdemServico;
	}

	public void setDescricaoOrdemServico(String descricaoOrdemServico) {
		this.descricaoOrdemServico = descricaoOrdemServico;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getNumeroHidrometroInstalado() {
		return numeroHidrometroInstalado;
	}

	public void setNumeroHidrometroInstalado(String numeroHidrometroInstalado) {
		this.numeroHidrometroInstalado = numeroHidrometroInstalado;
	}

	public String getNumeroHidrometroAtual() {
		return numeroHidrometroAtual;
	}

	public void setNumeroHidrometroAtual(String numeroHidrometroAtual) {
		this.numeroHidrometroAtual = numeroHidrometroAtual;
	}

	public String getLeituraInstalacao() {
		return leituraInstalacao;
	}

	public void setLeituraInstalacao(String leituraInstalacao) {
		this.leituraInstalacao = leituraInstalacao;
	}

	public String getLeituraRetirada() {
		return leituraRetirada;
	}

	public void setLeituraRetirada(String leituraRetirada) {
		this.leituraRetirada = leituraRetirada;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	public void limparForm(){
		this.dataInstalSubstRet = "";
		this.idOrdemServico = "";
		this.descricaoOrdemServico = "";
		this.matriculaImovel = "";
		this.numeroHidrometroInstalado = "";
		this.numeroHidrometroAtual = "";
		this.leituraInstalacao = "";
		this.leituraRetirada = "";
		this.operacao = "";
	}
	
	
	
}

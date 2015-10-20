package gcom.atendimentopublico.ordemservico.bean;

import java.util.Date;

public class ArquivoTxtOSVisitaHelper {

	private Integer idArquivo;
	private Integer idLocalidade;
	private Integer codSetorComercialInicial;
	private Integer codSetorComercialFinal;
	private Integer numeroQuadraInicial;
	private Integer numeroQuadraFinal;
	private String qtdOrdemServico;
	private Date dataUltimaAlteracao;
	private Integer idSituacao;
	private String descricaoSituacao;
	private String nomeCliente;
	private String nomeFuncionario;
	private Integer idLeiturista;
	private long imei;
	private String qtdOSEncerradas;
	
	
	public long getImei() {
		return imei;
	}

	public void setImei(long imei) {
		this.imei = imei;
	}

	public ArquivoTxtOSVisitaHelper(){}
	
	public Integer getIdArquivo() {
		return idArquivo;
	}
	public void setIdArquivo(Integer idArquivo) {
		this.idArquivo = idArquivo;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public Integer getCodSetorComercialInicial() {
		return codSetorComercialInicial;
	}
	public void setCodSetorComercialInicial(Integer codSetorComercialInicial) {
		this.codSetorComercialInicial = codSetorComercialInicial;
	}
	public Integer getCodSetorComercialFinal() {
		return codSetorComercialFinal;
	}
	public void setCodSetorComercialFinal(Integer codSetorComercialFinal) {
		this.codSetorComercialFinal = codSetorComercialFinal;
	}
	public Integer getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}
	public void setNumeroQuadraInicial(Integer numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}
	public Integer getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}
	public void setNumeroQuadraFinal(Integer numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}
	public String getQtdOrdemServico() {
		return qtdOrdemServico;
	}
	public void setQtdOrdemServico(String qtdOrdemServico) {
		this.qtdOrdemServico = qtdOrdemServico;
	}
	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}
	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	public Integer getIdSituacao() {
		return idSituacao;
	}
	public void setIdSituacao(Integer idSituacao) {
		this.idSituacao = idSituacao;
	}
	public String getDescricaoSituacao() {
		return descricaoSituacao;
	}
	public void setDescricaoSituacao(String descricaoSituacao) {
		this.descricaoSituacao = descricaoSituacao;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public Integer getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(Integer idLeiturista) {
		this.idLeiturista = idLeiturista;
	}

	public String getQtdOSEncerradas() {
		return qtdOSEncerradas;
	}

	public void setQtdOSEncerradas(String qtdOSEncerradas) {
		this.qtdOSEncerradas = qtdOSEncerradas;
	}
	
	
		
}

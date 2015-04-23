package gsan.gui.atualizacaocadastral;

import gsan.relatorio.RelatorioBean;


public class ImovelInconsistenteBean implements RelatorioBean {
	
	private String matricula;
	private String setor;
	private String quadra;
	private String numeroVisitas;
	private String descricaoCadastroOcorrencia;
	private String situacao;
	private String loginCadastrador;
	private String nomeCadastrador;
	private String idCadastroOcorrencia;
	private String qtdImoveis;
	
	//Novos
	private String numeroLote;
	private String cpfCnpj;
	private String enderecoFormatado;
	private String matriculaGsan;
	
	private Integer contadorImovelPorCadastrador;
	private Integer contadorImovelGeral;
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getQuadra() {
		return quadra;
	}
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	public String getNumeroVisitas() {
		return numeroVisitas;
	}
	public void setNumeroVisitas(String numeroVisitas) {
		this.numeroVisitas = numeroVisitas;
	}
	public String getDescricaoCadastroOcorrencia() {
		return descricaoCadastroOcorrencia;
	}
	public void setDescricaoCadastroOcorrencia(String descricaoCadastroOcorrencia) {
		this.descricaoCadastroOcorrencia = descricaoCadastroOcorrencia;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getLoginCadastrador() {
		return loginCadastrador;
	}
	public void setLoginCadastrador(String loginCadastrador) {
		this.loginCadastrador = loginCadastrador;
	}
	public String getNomeCadastrador() {
		return nomeCadastrador;
	}
	public void setNomeCadastrador(String nomeCadastrador) {
		this.nomeCadastrador = nomeCadastrador;
	}
	public String getIdCadastroOcorrencia() {
		return idCadastroOcorrencia;
	}
	public void setIdCadastroOcorrencia(String idCadastroOcorrencia) {
		this.idCadastroOcorrencia = idCadastroOcorrencia;
	}
	public String getQtdImoveis() {
		return qtdImoveis;
	}
	public void setQtdImoveis(String qtdImoveis) {
		this.qtdImoveis = qtdImoveis;
	}
	public String getNumeroLote() {
		return numeroLote;
	}
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getEnderecoFormatado() {
		return enderecoFormatado;
	}
	public void setEnderecoFormatado(String enderecoFormatado) {
		this.enderecoFormatado = enderecoFormatado;
	}
	public String getMatriculaGsan() {
		return matriculaGsan;
	}
	public void setMatriculaGsan(String matriculaGsan) {
		this.matriculaGsan = matriculaGsan;
	}
	public Integer getContadorImovelPorCadastrador() {
		return contadorImovelPorCadastrador;
	}
	public void setContadorImovelPorCadastrador(Integer contadorImovelPorCadastrador) {
		this.contadorImovelPorCadastrador = contadorImovelPorCadastrador;
	}
	public Integer getContadorImovelGeral() {
		return contadorImovelGeral;
	}
	public void setContadorImovelGeral(Integer contadorImovelGeral) {
		this.contadorImovelGeral = contadorImovelGeral;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loginCadastrador == null) ? 0 : loginCadastrador.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImovelInconsistenteBean other = (ImovelInconsistenteBean) obj;
		if (loginCadastrador == null) {
			if (other.loginCadastrador != null)
				return false;
		} else if (!loginCadastrador.equals(other.loginCadastrador))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}	
	
}

package gcom.gui.atualizacaocadastral;

import gcom.relatorio.RelatorioBean;

/**
 * [UC 1297] - Relatório dos Imóveis Inconsistentes - Atualização Cadastral 
 * 
 * @author Davi Menezes
 * @date 23/03/2012
 *
 */
public class RelatorioImoveisInconsistentesMovimentoBean implements RelatorioBean {

	private Integer idImovel;
	private String dataMovimento;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String codigoSetor;
	private String quadra;
	private String matricula;
	private String idCliente;
	private String CPFCNPJ;
	private String situacaoCadastral;
	private String cadastrador;
	private String dadoInconsistente;
	private String tipoInconsistencia;
	private Integer total;
	
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getDataMovimento() {
		return dataMovimento;
	}
	public void setDataMovimento(String dataMovimento) {
		this.dataMovimento = dataMovimento;
	}
	public String getCodigoSetor() {
		return codigoSetor;
	}
	public void setCodigoSetor(String codigoSetor) {
		this.codigoSetor = codigoSetor;
	}
	public String getQuadra() {
		return quadra;
	}
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getCPFCNPJ() {
		return CPFCNPJ;
	}
	public void setCPFCNPJ(String CPFCNPJ) {
		this.CPFCNPJ = CPFCNPJ;
	}
	public String getSituacaoCadastral() {
		return situacaoCadastral;
	}
	public void setSituacaoCadastral(String situacaoCadastral) {
		this.situacaoCadastral = situacaoCadastral;
	}
	public String getCadastrador() {
		return cadastrador;
	}
	public void setCadastrador(String cadastrador) {
		this.cadastrador = cadastrador;
	}
	public String getDadoInconsistente() {
		return dadoInconsistente;
	}
	public void setDadoInconsistente(String dadoInconsistente) {
		this.dadoInconsistente = dadoInconsistente;
	}
	public String getTipoInconsistencia() {
		return tipoInconsistencia;
	}
	public void setTipoInconsistencia(String tipoInconsistencia) {
		this.tipoInconsistencia = tipoInconsistencia;
	}
	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
  public boolean equals(Object other) {
    	if (this == other) {
			return true;
		}

        if (!(other instanceof RelatorioImoveisInconsistentesMovimentoBean)) {
			return false;
		}

		boolean retorno = true;

		RelatorioImoveisInconsistentesMovimentoBean castOther = (RelatorioImoveisInconsistentesMovimentoBean) other;

		if (this.getIdImovel() != null) {
			if (!this.getIdImovel().equals(castOther.getIdImovel())) {
				retorno = false;
			}
		}

		if (this.getDataMovimento() != null && castOther.getDataMovimento() != null) {
			if (!this.getDataMovimento().equals(castOther.getDataMovimento())) {
				retorno = false;
			}
		}

        return retorno;
    }

	
}

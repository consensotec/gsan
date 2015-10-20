package gcom.relatorio.cadastro;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

public class RelatorioResumoSituacaoImoveisAnalistaCadastradorBean implements RelatorioBean {
	
	private Integer idLocalidade;
	private String descricaoLocalidade;
	
	private String tipoCadastradorAnalista;
	
	private String descTipo;
	private String descQuantidade1;
	private String descPercentagem1;
	private String descQuantidade2;
	private String descPercentagem2;
	private String descQuantidade3;
	private String descPercentagem3;
	private String descTotalImoveis;
	
	private Integer idAnalistaCadastrador;
	private String nomeAnalistaCadastrador;
	private Integer qtdImoveis1;
	private BigDecimal percentualImoveis1;
	private Integer qtdImoveis2;
	private BigDecimal percentualImoveis2;
	private Integer qtdImoveis3;
	private BigDecimal percentualImoveis3;
	private Integer totalImoveis;
	
	private BigDecimal percentualTotalImoveis1;
	private BigDecimal percentualTotalImoveis2;
	private BigDecimal percentualTotalImoveis3;
	
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getTipoCadastradorAnalista() {
		return tipoCadastradorAnalista;
	}
	public void setTipoCadastradorAnalista(String tipoCadastradorAnalista) {
		this.tipoCadastradorAnalista = tipoCadastradorAnalista;
	}
	public Integer getIdAnalistaCadastrador() {
		return idAnalistaCadastrador;
	}
	public void setIdAnalistaCadastrador(Integer idAnalistaCadastrador) {
		this.idAnalistaCadastrador = idAnalistaCadastrador;
	}
	public String getNomeAnalistaCadastrador() {
		return nomeAnalistaCadastrador;
	}
	public void setNomeAnalistaCadastrador(String nomeAnalistaCadastrador) {
		this.nomeAnalistaCadastrador = nomeAnalistaCadastrador;
	}
	public Integer getQtdImoveis1() {
		return qtdImoveis1;
	}
	public void setQtdImoveis1(Integer qtdImoveis1) {
		this.qtdImoveis1 = qtdImoveis1;
	}
	public BigDecimal getPercentualImoveis1() {
		return percentualImoveis1;
	}
	public void setPercentualImoveis1(BigDecimal percentualImoveis1) {
		this.percentualImoveis1 = percentualImoveis1;
	}
	public Integer getQtdImoveis2() {
		return qtdImoveis2;
	}
	public void setQtdImoveis2(Integer qtdImoveis2) {
		this.qtdImoveis2 = qtdImoveis2;
	}
	public BigDecimal getPercentualImoveis2() {
		return percentualImoveis2;
	}
	public void setPercentualImoveis2(BigDecimal percentualImoveis2) {
		this.percentualImoveis2 = percentualImoveis2;
	}
	public Integer getQtdImoveis3() {
		return qtdImoveis3;
	}
	public void setQtdImoveis3(Integer qtdImoveis3) {
		this.qtdImoveis3 = qtdImoveis3;
	}
	public BigDecimal getPercentualImoveis3() {
		return percentualImoveis3;
	}
	public void setPercentualImoveis3(BigDecimal percentualImoveis3) {
		this.percentualImoveis3 = percentualImoveis3;
	}
	public Integer getTotalImoveis() {
		return totalImoveis;
	}
	public void setTotalImoveis(Integer totalImoveis) {
		this.totalImoveis = totalImoveis;
	}
	public String getDescTipo() {
		return descTipo;
	}
	public void setDescTipo(String descTipo) {
		this.descTipo = descTipo;
	}
	public String getDescQuantidade1() {
		return descQuantidade1;
	}
	public void setDescQuantidade1(String descQuantidade1) {
		this.descQuantidade1 = descQuantidade1;
	}
	public String getDescPercentagem1() {
		return descPercentagem1;
	}
	public void setDescPercentagem1(String descPercentagem1) {
		this.descPercentagem1 = descPercentagem1;
	}
	public String getDescQuantidade2() {
		return descQuantidade2;
	}
	public void setDescQuantidade2(String descQuantidade2) {
		this.descQuantidade2 = descQuantidade2;
	}
	public String getDescPercentagem2() {
		return descPercentagem2;
	}
	public void setDescPercentagem2(String descPercentagem2) {
		this.descPercentagem2 = descPercentagem2;
	}
	public String getDescQuantidade3() {
		return descQuantidade3;
	}
	public void setDescQuantidade3(String descQuantidade3) {
		this.descQuantidade3 = descQuantidade3;
	}
	public String getDescPercentagem3() {
		return descPercentagem3;
	}
	public void setDescPercentagem3(String descPercentagem3) {
		this.descPercentagem3 = descPercentagem3;
	}
	public String getDescTotalImoveis() {
		return descTotalImoveis;
	}
	public void setDescTotalImoveis(String descTotalImoveis) {
		this.descTotalImoveis = descTotalImoveis;
	}
	public BigDecimal getPercentualTotalImoveis1() {
		return percentualTotalImoveis1;
	}
	public void setPercentualTotalImoveis1(BigDecimal percentualTotalImoveis1) {
		this.percentualTotalImoveis1 = percentualTotalImoveis1;
	}
	public BigDecimal getPercentualTotalImoveis2() {
		return percentualTotalImoveis2;
	}
	public void setPercentualTotalImoveis2(BigDecimal percentualTotalImoveis2) {
		this.percentualTotalImoveis2 = percentualTotalImoveis2;
	}
	public BigDecimal getPercentualTotalImoveis3() {
		return percentualTotalImoveis3;
	}
	public void setPercentualTotalImoveis3(BigDecimal percentualTotalImoveis3) {
		this.percentualTotalImoveis3 = percentualTotalImoveis3;
	}

	
}

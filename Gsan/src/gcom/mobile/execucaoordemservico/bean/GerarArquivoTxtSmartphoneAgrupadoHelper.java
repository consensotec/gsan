package gcom.mobile.execucaoordemservico.bean;

import java.util.Date;

public class GerarArquivoTxtSmartphoneAgrupadoHelper {
	private Integer idLocalidade;
	private String descricaoLocalidade;

	private Integer idSetor;
	private Integer cdSetor;

	private Integer idRota;
	private Integer cdRota;

	private Integer qtd;
	private Date dataGeracao;

	public GerarArquivoTxtSmartphoneAgrupadoHelper(Integer idLocalidade, String descricaoLocalidade, Integer idSetor,
			Integer cdSetor, Integer idRota, Integer cdRota, Integer qtd, Date dataGeracao) {
		super();
		this.idLocalidade = idLocalidade;
		this.descricaoLocalidade = descricaoLocalidade;
		this.idSetor = idSetor;
		this.cdSetor = cdSetor;
		this.idRota = idRota;
		this.cdRota = cdRota;
		this.qtd = qtd;
		this.dataGeracao = dataGeracao;
	}

	public GerarArquivoTxtSmartphoneAgrupadoHelper() {
	}

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

	public Integer getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(Integer idSetor) {
		this.idSetor = idSetor;
	}

	public Integer getCdSetor() {
		return cdSetor;
	}

	public void setCdSetor(Integer cdSetor) {
		this.cdSetor = cdSetor;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	public Integer getCdRota() {
		return cdRota;
	}

	public void setCdRota(Integer cdRota) {
		this.cdRota = cdRota;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
}

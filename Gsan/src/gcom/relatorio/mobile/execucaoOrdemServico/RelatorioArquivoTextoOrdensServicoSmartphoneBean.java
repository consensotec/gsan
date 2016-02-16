package gcom.relatorio.mobile.execucaoOrdemServico;

import gcom.mobile.execucaoordemservico.bean.GerarArquivoTxtSmartphoneHelper;
import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

import java.util.Date;

public class RelatorioArquivoTextoOrdensServicoSmartphoneBean implements RelatorioBean {
	private Integer idLocalidade;
	private String descricaoLocalidade;

	private Integer idSetor;
	private Integer cdSetor;

	private Integer idRota;
	private Integer cdRota;

	private Integer qtd;
	private Date dataGeracao;

	public RelatorioArquivoTextoOrdensServicoSmartphoneBean(GerarArquivoTxtSmartphoneHelper helper){
		this.idLocalidade = helper.getIdLocalidade();
		this.descricaoLocalidade = helper.getDescricaoLocalidade();
		this.idSetor = helper.getIdSetor();
		this.cdSetor = helper.getCdSetor();
		this.idRota = helper.getIdRota();
		this.cdRota = helper.getCdRota();
		this.qtd = helper.getQtd();
		this.dataGeracao = helper.getDataGeracao();
	}

	public RelatorioArquivoTextoOrdensServicoSmartphoneBean(Integer idLocalidade, String descricaoLocalidade,
			Integer idSetor, Integer cdSetor, Integer idRota, Integer cdRota, Integer qtd, Date dataGeracao) {
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

	public RelatorioArquivoTextoOrdensServicoSmartphoneBean() {
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

	public String getDataGeracaoFormatada() {
		return Util.formatarData(dataGeracao);
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
}

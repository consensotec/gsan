package gcom.mobile.execucaoordemservico.bean;

import gcom.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GerarArquivoTxtSmartphoneHelper {
	private Integer idLocalidade;
	private String descricaoLocalidade;

	private Integer idSetor;
	private Integer cdSetor;

	private Integer idRota;
	private Integer cdRota;

	private Integer idQuadra;
	private Integer nnQuadra;
	
	private Integer qtd;
	private Date dataGeracao;

	private List<Integer> listaOS = new ArrayList<Integer>();

	public GerarArquivoTxtSmartphoneHelper(Integer idLocalidade, String descricaoLocalidade, Integer idSetor,
			Integer cdSetor, Integer idRota, Integer cdRota, Integer qtd, Date dataGeracao,
			Integer idQuadra, Integer nnQuadra) {
		super();
		this.idLocalidade = idLocalidade;
		this.descricaoLocalidade = descricaoLocalidade;
		this.idSetor = idSetor;
		this.cdSetor = cdSetor;
		this.idRota = idRota;
		this.cdRota = cdRota;
		this.qtd = qtd;
		this.dataGeracao = dataGeracao;
		this.idQuadra = idQuadra;
		this.nnQuadra = nnQuadra;
	}

	public GerarArquivoTxtSmartphoneHelper() {
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

	public Integer getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}

	public Integer getNnQuadra() {
		return nnQuadra;
	}

	public void setNnQuadra(Integer nnQuadra) {
		this.nnQuadra = nnQuadra;
	}

	public int getQtdOS() {
		return listaOS.size();
	}

	public List<Integer> getListaOS() {
		return listaOS;
	}

	public void adicionarOS(Integer idOS) {
		listaOS.add(idOS);
	}

	public String getKey() {
		return String.format("%s-%s-%s-%s", idLocalidade, cdSetor, cdRota, nnQuadra);
	}
}

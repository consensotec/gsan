package gcom.atualizacaocadastral.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * [UC 1392] - Consultar Roteiro Dispositivo Móvel Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 27/11/2012
 *
 */
public class AtualizacaoCadastralArquivoTextoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String idLocalidade;
	
	private String codigoSetorComercial;
	
	private ArrayList<Integer> colecaoQuadras;
	
	private String quantidadeEnviada;
	
	private String quantidadeRecebida;
	
	private String nomeUsuario;
	
	private Integer idSituacaoArquivo;
	
	private String situacaoArquivo;
	
	private String dataLiberacao;
	
	private Integer idParametro;
	
	private String dataFinalizacaoArquivo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public ArrayList<Integer> getColecaoQuadras() {
		return colecaoQuadras;
	}

	public void setColecaoQuadras(ArrayList<Integer> colecaoQuadras) {
		this.colecaoQuadras = colecaoQuadras;
	}

	public String getQuantidadeEnviada() {
		return quantidadeEnviada;
	}

	public void setQuantidadeEnviada(String quantidadeEnviada) {
		this.quantidadeEnviada = quantidadeEnviada;
	}

	public String getQuantidadeRecebida() {
		return quantidadeRecebida;
	}

	public void setQuantidadeRecebida(String quantidadeRecebida) {
		this.quantidadeRecebida = quantidadeRecebida;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Integer getIdSituacaoArquivo() {
		return idSituacaoArquivo;
	}

	public void setIdSituacaoArquivo(Integer idSituacaoArquivo) {
		this.idSituacaoArquivo = idSituacaoArquivo;
	}

	public String getSituacaoArquivo() {
		return situacaoArquivo;
	}

	public void setSituacaoArquivo(String situacaoArquivo) {
		this.situacaoArquivo = situacaoArquivo;
	}

	public String getDataLiberacao() {
		return dataLiberacao;
	}

	public void setDataLiberacao(String dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}

	public Integer getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}

	public String getDataFinalizacaoArquivo() {
		return dataFinalizacaoArquivo;
	}

	public void setDataFinalizacaoArquivo(String dataFinalizacaoArquivo) {
		this.dataFinalizacaoArquivo = dataFinalizacaoArquivo;
	}
}
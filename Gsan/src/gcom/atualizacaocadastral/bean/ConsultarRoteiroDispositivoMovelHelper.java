package gcom.atualizacaocadastral.bean;

import java.io.Serializable;

/**
 * [UC 1392] - Consultar Roteiro Dispositivo Móvel Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 27/11/2012
 *
 */
public class ConsultarRoteiroDispositivoMovelHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idLocalidade;
	
	private Integer codigoSetorComercial;
	
	private Integer numeroQuadra;
	
	private Integer idCadastrador;
	
	private Integer situacaoArquivoTexto;

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Integer getIdCadastrador() {
		return idCadastrador;
	}

	public void setIdCadastrador(Integer idCadastrador) {
		this.idCadastrador = idCadastrador;
	}

	public Integer getSituacaoArquivoTexto() {
		return situacaoArquivoTexto;
	}

	public void setSituacaoArquivoTexto(Integer situacaoArquivoTexto) {
		this.situacaoArquivoTexto = situacaoArquivoTexto;
	}
	
}

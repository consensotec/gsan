package gcom.cobranca;

import gcom.cadastro.cliente.EsferaPoder;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC 1587] - Gerar Divida Ativa
 * 
 * @author Davi Menezes
 * @date 13/02/2014
 *
 */
public class DividaAtivaCriterioEsferaPoder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private DividaAtivaCriterio dividaAtivaCriterio;
	
	private EsferaPoder esferaPoder;
	
	private Date ultimaAlteracao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DividaAtivaCriterio getDividaAtivaCriterio() {
		return dividaAtivaCriterio;
	}

	public void setDividaAtivaCriterio(DividaAtivaCriterio dividaAtivaCriterio) {
		this.dividaAtivaCriterio = dividaAtivaCriterio;
	}

	public EsferaPoder getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(EsferaPoder esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
}

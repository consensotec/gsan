package gcom.cobranca;

import gcom.cadastro.cliente.ClienteTipo;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC 1587] - Gerar Divida Ativa
 * 
 * @author Davi Menezes
 * @date 13/02/2014
 *
 */
public class DividaAtivaCriterioClienteTipo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private DividaAtivaCriterio dividaAtivaCriterio;
	
	private ClienteTipo clienteTipo;
	
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

	public ClienteTipo getClienteTipo() {
		return clienteTipo;
	}

	public void setClienteTipo(ClienteTipo clienteTipo) {
		this.clienteTipo = clienteTipo;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
}

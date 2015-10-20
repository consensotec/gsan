package gcom.cobranca.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * [UC 1370] Consultar Ações de Cobrança por Imóvel
 * 
 * @author Davi Menezes
 * @date 15/08/2012
 *
 */
public class AcoesCobrancaTipoDebitoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String tipoDebito;
	
	private BigDecimal valorDebito;

	public String getTipoDebito() {
		return tipoDebito;
	}

	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}
	
}

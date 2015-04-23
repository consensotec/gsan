package gsan.cobranca.cobrancaporresultado;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * [UC1264] Incluir Contas em Cobrança
 * 
 * @author Mariana Victor
 * @since 03/05/2011
 */
public class IncluirContaEmCobrancaHelper implements
		Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idComando;

	private Integer idEmpresa;

	private BigDecimal percentual;
	
	private Integer idOrdemServico;
	
	private Integer idComandoExtensao;

	public Integer getIdComando() {
		return idComando;
	}

	public void setIdComando(Integer idComando) {
		this.idComando = idComando;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public BigDecimal getPercentual() {
		return percentual;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public Integer getIdComandoExtensao() {
		return idComandoExtensao;
	}

	public void setIdComandoExtensao(Integer idComandoExtensao) {
		this.idComandoExtensao = idComandoExtensao;
	}
	

}

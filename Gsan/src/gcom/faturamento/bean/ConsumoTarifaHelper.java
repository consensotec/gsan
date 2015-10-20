package gcom.faturamento.bean;

/**
 * [UC 1441] - Inserir Vigência de Simulação para Tarifa de Consumo
 * 
 * @author Davi Menezes
 * @date 20/02/2013
 *
 */
public class ConsumoTarifaHelper {

	private String id;
	
	private String descricao;
	
	private String dataVigencia;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataVigencia() {
		return dataVigencia;
	}

	public void setDataVigencia(String dataVigencia) {
		this.dataVigencia = dataVigencia;
	}

	public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        
        if (!(other instanceof ConsumoTarifaHelper)) {
            return false;
        }
        
        ConsumoTarifaHelper castOther = (ConsumoTarifaHelper) other;

        return this.getId().equals(castOther.getId());
    }
	
}

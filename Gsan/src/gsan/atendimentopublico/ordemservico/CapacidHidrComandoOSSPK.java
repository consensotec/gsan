package gsan.atendimentopublico.ordemservico;

import gsan.interceptor.ObjetoGcom;

public class CapacidHidrComandoOSSPK extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
	
	private Integer comandoOrdemSeletivaId;
	
	private Integer hidrometroCapacidadeId;


	public CapacidHidrComandoOSSPK(Integer comandoOrdemSeletivaId, Integer hidrometroCapacidadeId) {
		super();
		// TODO Auto-generated constructor stub
		this.comandoOrdemSeletivaId = comandoOrdemSeletivaId;
		this.hidrometroCapacidadeId = hidrometroCapacidadeId;
	}

	public CapacidHidrComandoOSSPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getComandoOrdemSeletivaId() {
		return comandoOrdemSeletivaId;
	}

	public void setComandoOrdemSeletivaId(Integer comandoOrdemSeletivaId) {
		this.comandoOrdemSeletivaId = comandoOrdemSeletivaId;
	}

	public Integer getHidrometroCapacidadeId() {
		return hidrometroCapacidadeId;
	}

	public void setHidrometroCapacidadeId(Integer hidrometroCapacidadeId) {
		this.hidrometroCapacidadeId = hidrometroCapacidadeId;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
 		String[] retorno = new String[2];
 		retorno[0] = "comandoOrdemSeletivaId";
 		retorno[1] = "hidrometroCapacidadeId";
 		return retorno;
	}
}

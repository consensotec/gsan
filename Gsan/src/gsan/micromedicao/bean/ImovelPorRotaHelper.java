package gsan.micromedicao.bean;

import gsan.cadastro.imovel.Imovel;
import gsan.micromedicao.MovimentoRoteiroEmpresa;

import java.io.Serializable;

public class ImovelPorRotaHelper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Imovel imovel;
	private MovimentoRoteiroEmpresa movimentoRoteiroEmpresa;

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public MovimentoRoteiroEmpresa getMovimentoRoteiroEmpresa() {
		return movimentoRoteiroEmpresa;
	}

	public void setMovimentoRoteiroEmpresa(
			MovimentoRoteiroEmpresa movimentoRoteiroEmpresa) {
		this.movimentoRoteiroEmpresa = movimentoRoteiroEmpresa;
	}
}

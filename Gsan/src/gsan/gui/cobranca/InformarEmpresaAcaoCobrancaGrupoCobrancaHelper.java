package gsan.gui.cobranca;

import gsan.cobranca.CobrancaAcaoGrupoContrato;

public class InformarEmpresaAcaoCobrancaGrupoCobrancaHelper {

	private CobrancaAcaoGrupoContrato cobrancaAcaoGrupoContrato;
	private Short indicadorRemovido;
	
	public InformarEmpresaAcaoCobrancaGrupoCobrancaHelper(){}

	public CobrancaAcaoGrupoContrato getCobrancaAcaoGrupoContrato() {
		return cobrancaAcaoGrupoContrato;
	}
	public void setCobrancaAcaoGrupoContrato(CobrancaAcaoGrupoContrato cobrancaAcaoGrupoContrato) {
		this.cobrancaAcaoGrupoContrato = cobrancaAcaoGrupoContrato;
	}

	public Short getIndicadorRemovido() {
		return indicadorRemovido;
	}
	public void setIndicadorRemovido(Short indicadorRemovido) {
		this.indicadorRemovido = indicadorRemovido;
	}
}
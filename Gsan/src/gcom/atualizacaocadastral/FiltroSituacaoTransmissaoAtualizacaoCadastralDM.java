package gcom.atualizacaocadastral;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroSituacaoTransmissaoAtualizacaoCadastralDM extends Filtro implements
		Serializable {
	private static final long serialVersionUID = 1L;

	public FiltroSituacaoTransmissaoAtualizacaoCadastralDM(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroSituacaoTransmissaoAtualizacaoCadastralDM() {
	}

	public final static String ID = "id";

	public final static String INDICADOR_USO = "indicadorUso";

	public final static String DESCRICAO = "descricaoSituacao";
}

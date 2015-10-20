package gcom.faturamento.contratodemanda;

import gcom.util.filtro.Filtro;

/**
 * [UC1226] Inserir Contrato de Demanda Condomínios Residenciais
 * 
 * Filtro do Contrato Demanda Situação
 * 
 * @author Diogo Peixoto
 * @date 22/09/2011
 * 
 */
public class FiltroContratoDemandaSituacao extends Filtro {

	private static final long serialVersionUID = 1L;

	public final static String ID = "id";
	public final static String DESCRICAO = "descricao";
	public final static String INDICADOR_USO = "indicadorUso";

	public FiltroContratoDemandaSituacao() {
		
	}

	public FiltroContratoDemandaSituacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
}
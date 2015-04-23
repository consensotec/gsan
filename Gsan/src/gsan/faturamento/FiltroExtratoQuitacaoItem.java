package gsan.faturamento;

import gsan.util.filtro.Filtro;

public class FiltroExtratoQuitacaoItem extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroExtratoQuitacaoItem(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroQuadra
     */
    public FiltroExtratoQuitacaoItem() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    /**
     * Description of the Field
     */
    public final static String ID_EXTRATO_QUITACAO = "extratoQuitacao.id";
    

}

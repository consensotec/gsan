package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class FiltroContaMotivoRevisao extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroContaMotivoRevisao
     */
    public FiltroContaMotivoRevisao() {
    }
    
    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricaoMotivoRevisaoConta";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
    /**
     * Description of the Field
     */
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";
    
    /**
     * Description of the Field
     */
    public final static String PERMISSAO_ESPECIAL = "permissaoEspecial";
    
    /**
     * Description of the Field
     */
    public final static String ID_PERMISSAO_ESPECIAL = "permissaoEspecial.id";
    
    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descri��o do par�metro
     */
    public FiltroContaMotivoRevisao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}


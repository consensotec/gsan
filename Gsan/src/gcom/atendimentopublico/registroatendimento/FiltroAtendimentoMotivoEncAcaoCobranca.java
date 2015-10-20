package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroAtendimentoMotivoEncAcaoCobranca extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroAtendimentoMotivoEncAcaoCobranca(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroAtendimentoMotivoEncAcaoCobranca() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String ATENDIMENTO_MOTIVO_ENCERRAMENTO = "atendimentoMotivoEncerramento";
    
    public final static String ID_ATENDIMENTO_MOTIVO_ENCERRAMENTO = "atendimentoMotivoEncerramento.id";
    
    public final static String ID_COBRANCA_ACAO = "cobrancaAcao.id";
    
    public final static String COBRANCA_ACAO = "cobrancaAcao";

    public final static String COMP_ID_ATENDIMENTO_MOTIVO_ENCERRAMENTO_ID = "comp_id.atendimentoMotivoEncerramentoId";
    
    public final static String COMP_ID_COBRANCA_ACAO_ID = "comp_id.cobrancaAcaoId";
    
    public final static String INDICADOR_GERAR_PAGAMENTO= "indicadorGeraPagamento";
    
    public final static String INDICADOR_GERAR_SUCESSOR = "indicadorGeraSucessor";
    
    public final static String INDICADOR_EXIBE_DOCUMENTO = "indicadorExibeDocumento";
    
    

}

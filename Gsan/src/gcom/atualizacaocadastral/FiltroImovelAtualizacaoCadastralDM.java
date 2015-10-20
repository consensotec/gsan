package gcom.atualizacaocadastral;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroImovelAtualizacaoCadastralDM extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroCliente object
	 */
	public FiltroImovelAtualizacaoCadastralDM() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroImovelAtualizacaoCadastralDM(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	/**
	 * Description of the Field
	 */
	public final static String ID_IMOVEL = "idImovel";
	
	/**
	 * Description of the Field
	 */
	public final static String IMOVEL = "imovel";
	
	/**
	 * Description of the Field
	 */
	public final static String ID_SITUACAO_ATUALIZACAO_CADASTRAL = "idSituacaoAtualizacaoCadastral";
	
	/**
	 * Description of the Field
	 */
	public final static String ID_EMPRESA = "idEmpresa";
	
	/**
	 * Description of the Field
	 */
	public final static String ID_LOCALIDADE = "idLocalidade";
	
	/**
	 * Description of the Field
	 */
	public final static String CODIGO_SETOR_COMERCIAL = "codigoSetorComercial";
	
	/**
	 * Description of the Field
	 */
	public final static String NUMERO_QUADRA = "numeroQuadra";
		
	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_DADOS_RETORNO = "indicadorDadosRetorno";
	
	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_ATUALIZADO = "indicadorAtualizado";
	
	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_PENDENTE = "indicadorPendente";
	
	/**
	 * Description of the Field
	 */
	public final static String DATA_RECEBIMENTO_MOVIMENTO = "dataRecebimentoMovimento";
	
	/**
	 * Description of the Field
	 */
	public final static String LOTE = "lote";
		
	/**
	 * Description of the Field
	 */
	public final static String ID_LOGRADOURO = "idLogradouro";
		
	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_RESETORIZADO = "indicadorResetorizado";
	
	/**
	 * Description of the Field
	 */
	public final static String ID_PARAMETRO_TABELA_ATUALIZACAO_CADASTRAL_DM = "parametroTabelaAtualizacaoCadastralDM.id";
	
	/**
	 * Description of the Field
	 */
	public final static String PARAMETRO_TABELA_ATUALIZACAO_CADASTRAL_DM = "parametroTabelaAtualizacaoCadastralDM";
	
	/**
	 * Description of the Field
	 */
	public final static String CODIGO = "codigoImovel";
	
	/**
	 * Description of the Field
	 */
	public final static String CODIGO_SITUACAO = "codigoSituacao";
	
	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_IMOVEL_NOVO = "indicadorImovelNovo";
	
	public final static String MUNICIPIO = "municipio";
	
	public final static String LIGACAO_AGUA_SITUACAO = "ligacaoAguaSituacao";
	
	
}
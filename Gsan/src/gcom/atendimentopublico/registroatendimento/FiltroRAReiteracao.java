package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroRAReiteracao extends Filtro implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroRAReiteracao(){
		
	}
	
	public FiltroRAReiteracao(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	
	public final static String PONTO_REFERENCIA = "pontoReferencia";
	
	public final static String COMPLEMENTO_ENDERECO = "complementoEndereco";
	
	public final static String INDICADOR_ENDERECO_CORRESPONDENCIA = "indicadorEnderecoCorrespondencia";
	
	public final static String NUMERO_IMOVEL = "numeroImovel";
	
	public final static String SOLICITANTE = "solicitante";
	
	public final static String NUMERO_PROTOCOLO_ATENDIMENTO = "numeroProtocoloAtendimento";
	
	public final static String LOGRADOURO_BAIRRO = "logradouroBairro";
	
	public final static String LOGRADOURO_BAIRRO_ID = "logradouroBairro.id";
	
	public final static String CLIENTE = "cliente";
	
	public final static String LOGRADOURO_CEP = "logradouroCep";
	
	public final static String LOGRADOURO_CEP_ID = "logradouroCep.id";
	
	public final static String REGISTRO_ATENDIMENTO = "registroAtendimento";
	
	public final static String REGISTRO_ATENDIMENTO_ID = "registroAtendimento.id";
	
	public final static String UNIDADE_ORGANIZACIONAL = "unidadeOrganizacional";
	
	public final static String UNIDADE_ORGANIZACIONAL_ID = "unidadeOrganizacional.id";
	
	
	
	
}

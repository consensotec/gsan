package gcom.atualizacaocadastral;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;

public class MensagemAtualizacaoCadastralDM extends ObjetoGcom {

	public static final Integer DOCUMENTACAO_DO_CLIENTE_NAO_APRESENTADA = new Integer(1);
    public static final Integer ALTERACAO_CATEGORIA_NAO_AUTORIZADA = new Integer(2);
    public static final Integer ALTERACAO_LIGACAO_AGUA_NAO_AUTORIZADA = new Integer(3);
    public static final Integer IMOVEL_COM_EXCLUSAO_SUBSTITUICAO_DADOS_HIDROMETRO = new Integer(4);
    public static final Integer ALTERACAO_NUMERO_ECONOMIAS_NAO_AUTORIZADA = new Integer(5);
    public static final Integer IMOVEL_CATEGORIA_COM_PUB_IND_ASSOCIADO_CPF = new Integer(6);
    public static final Integer IMOVEL_CATEGORIA_RES_ASSOCIADO_CNPJ = new Integer(7);
    public static final Integer IMOVEL_EXCLUIDO_APOS_ENVIO_CONTRATADA = new Integer(8);
    public static final Integer ATUALIZACAO_PENDENTE_POR_INSCRICAO = new Integer(9);
    public static final Integer ATUALIZACAO_COM_SUCESSO = new Integer(10);
    public static final Integer IMOVEL_POSSUI_MAIS_DE_UMA_CATEGORIA = new Integer(11);
    public static final Integer HIDROMETRO_INEXISTENTE_ESTOQUE = new Integer(12);
    public static final Integer HIDROMETRO_JA_INSTALADO_OUTRO_IMOVEL = new Integer(13);
    public static final Integer HIDROMETRO_NAO_DISPONIVEL = new Integer(14);
    public static final Integer SITUACAO_LIGACAO_AGUA_INVALIDA_PARA_INST_HIDROMETRO = new Integer(16);
    public static final Integer LIGACAO_AGUA_POSSUI_HIDROMETRO = new Integer(17);
    public static final Integer SITUACAO_LIGACAO_ESGOTO_INVALIDA = new Integer(18);
    public static final Integer INFORMACAO_OBRIGATORIA = new Integer(19);
    public static final Integer HIDROMETRO_INSTALADO_POCO = new Integer(20);
    public static final Integer ATUALIZACAO_PENDENTE_POR_LOGRADOURO = new Integer(23);
    public static final Integer CPF_CNPJ_ASSOCIADO_RA = new Integer(24);
    public static final Integer CLIENTE_USUARIO_OBRIGATORIO = new Integer(30);
    public static final Integer NUMERO_CPF_CNPJ_INVALIDO = new Integer(32);
    public static final Integer CLIENTE_IMOVEL_PUBLICO_NAO_PODE_SER_ATUALIZADO = new Integer(35);
    public static final Integer CPF_CNPJ_NAO_INFORMADO = new Integer(36);
    public static final Integer CLIENTE_INATIVO_NAO_PODE_SER_ATUALIZADO = new Integer(37);
    public static final Integer IMOVEL_INSCRICAO_EM_DUPLICIDADE = new Integer(38);    
    public static final Integer ALTERACAO_LIGACAO_ESGOTO_NAO_AUTORIZADA = new Integer(41);
    public static final Integer CLIENTE_EM_PROCESSO_NEGATIVACAO = new Integer(44);
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String mensagem;
	private Date ultimaAlteracao;
	private Short indicadorOpcaoAprovado;
	
	public MensagemAtualizacaoCadastralDM() {}
	
	public MensagemAtualizacaoCadastralDM(Integer id, String mensagem,
			Date ultimaAlteracao, Short indicadorOpcaoAprovado) {
		super();
		this.id = id;
		this.mensagem = mensagem;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorOpcaoAprovado = indicadorOpcaoAprovado;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public Short getIndicadorOpcaoAprovado() {
		return indicadorOpcaoAprovado;
	}
	public void setIndicadorOpcaoAprovado(Short indicadorOpcaoAprovado) {
		this.indicadorOpcaoAprovado = indicadorOpcaoAprovado;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}
	
}

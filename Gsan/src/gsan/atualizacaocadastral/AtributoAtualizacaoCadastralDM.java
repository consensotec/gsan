package gsan.atualizacaocadastral;

import gsan.interceptor.ObjetoGcom;

import java.util.Date;

public class AtributoAtualizacaoCadastralDM extends ObjetoGcom {

	public static final Integer CPF_CNPJ = new Integer(1);
    public static final Integer CATEGORIA_SUBCATEGORIA = new Integer(2);
    public static final Integer SITUACAO_LIGACAO_AGUA = new Integer(3);
    public static final Integer ECONOMIA = new Integer(4);
    public static final Integer SITUACAO_HIDROMETRO = new Integer(5);
    public static final Integer IMOVEL = new Integer(6);
    public static final Integer INSCRICAO = new Integer(7);
    public static final Integer LOGRADOURO = new Integer(8);
    public static final Integer SITUACAO_LIGACAO_ESGOTO = new Integer(9);
    public static final Integer CLIENTE = new Integer(10);
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nomeAtributo;
	private Date ultimaAlteracao;

	public AtributoAtualizacaoCadastralDM() {}

	public AtributoAtualizacaoCadastralDM(Integer id, String nomeAtributo,
			Date ultimaAlteracao) {
		super();
		this.id = id;
		this.nomeAtributo = nomeAtributo;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeAtributo() {
		return nomeAtributo;
	}

	public void setNomeAtributo(String nomeAtributo) {
		this.nomeAtributo = nomeAtributo;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		// TODO Auto-generated method stub
		return null;
	}

}

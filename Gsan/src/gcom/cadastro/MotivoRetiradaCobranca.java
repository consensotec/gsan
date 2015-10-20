package gcom.cadastro;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe de entidade da tabela
 * cadastro.motivo_retirada_cobranca
 *  
 * @author Raimundo Martins
 * */
public class MotivoRetiradaCobranca implements Serializable{

	
	private static final long serialVersionUID = 1L;
	public static final int CONTA_VENCIMENTO_MAIOR_NUMERO_DIAS_VALIDADE_COMANDO = 1;
	public static final int CONTA_PAGA = 2;
	public static final int CONTA_PARCELADA_VENCIMENTO_MAIOR_NUMERO_DIAS_VALIDADE_COMANDO = 3;
	public static final int CONTAS_PAGAS_E_OU_PARCELADAS = 4;
	public static final int CONTA_EXCEDEU_PRAZO_COBRANCA = 5;
	public static final int CONTAS_EXCEDERAM_PRAZO_COBRANCA = 6;
	
	private Integer id;
	private String descricao;
	private Date ultimaAlteracao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	

}

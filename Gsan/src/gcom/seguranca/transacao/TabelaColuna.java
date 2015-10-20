/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 *
 * This file is part of GSAN, an integrated service management system for Sanitation
 *
 * GSAN is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * GSAN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 * Copyright (C) <2007>
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Ara�jo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cl�udio de Andrade Lira
 * Denys Guimar�es Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fab�ola Gomes de Ara�jo
 * Fl�vio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento J�nior
 * Homero Sampaio Cavalcanti
 * Ivan S�rgio da Silva J�nior
 * Jos� Edmar de Siqueira
 * Jos� Thiago Ten�rio Lopes
 * K�ssia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * M�rcio Roberto Batista da Silva
 * Maria de F�tima Sampaio Leite
 * Micaela Maria Coelho de Ara�jo
 * Nelson Mendon�a de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corr�a Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Ara�jo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * S�vio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa � software livre; voc� pode redistribu�-lo e/ou
 * modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
 * publicada pela Free Software Foundation; vers�o 2 da
 * Licen�a.
 * Este programa � distribu�do na expectativa de ser �til, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
 * COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
 * PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
 * detalhes.
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
 * junto com este programa; se n�o, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gcom.seguranca.transacao;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class TabelaColuna extends ObjetoGcom {
	private static final long serialVersionUID = 1L;

	public static final int DATA_VENCIMENTO_CONTA = 419;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String coluna;

	/** nullable persistent field */
	private String descricaoColuna;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private Short indicadorPrimaryKey = new Short((short) 2);

	/** nullable persistent field */
	private String nomeAbreviado;

	/** persistent field */
	private gcom.seguranca.transacao.Tabela tabela;

	private Integer indicadorPodeRetificarConta;

	private Short indicadorAtualizacaoCadastral;

	// Constantes
	// Nome do campo + tabela correspondente
	public final static Integer CPF_CNPJ_CLIENTE_ATU_CADASTRAL = 23829;

	// public final static Integer CNPJ_CLIENTE_ATU_CADASTRAL = 275;
	public final static Integer NOME_CLIENTE_ATU_CADASTRAL = 23820;

	public final static Integer PROFISSAO_CLIENTE_ATU_CADASTRAL = 23827;

	public final static Integer RAMO_ATIVIDADE_CLIENTE_ATU_CADASTRAL = 35022;

	public final static Integer CLIENTE_TIPO_CLIENTE_ATU_CADASTRAL = 23821;

	public final static Integer CLIENTE_RELACAO_TIPO_CLIENTE_IMOVEL_ATU_CADASTRAL = 23843;

	public final static Integer NUMERO_IMOVEL_CLIENTE_ENDERECO_ATU_CADASTRAL = 23840;

	public final static Integer COMPLEMENTO_CLIENTE_ENDERECO_ATU_CADASTRAL = 23841;
	
	public final static Integer SEXO_CLIENTE_ATUALIZACAO_CADASTRAL = 23828;

	public final static Integer NUMERO_MORADORES_IMOVEL_ATU_CADASTRAL = 23792;

	public final static Integer CODIGO_PERFIL_IMOVEL_ATU_CADASTRAL = 115087;

	public final static Integer NUMERO_IMOVEL_ATU_CADASTRAL = 35021;

	public final static Integer COMPLEMENTO_ENDERECO_IMOVEL_ATU_CADASTRAL = 23779;
	
	public final static Integer CODIGO_CADASTRO_OCORRENCIA_IMOVEL_ATU_CADASTRAL = 35024;
	
	public final static Integer ID_SITUACAO_LIGACAO_AGUA_IMOVEL_ATU_CADASTRAL = 23786;
	
	public final static Integer ID_SITUACAO_LIGACAO_ESGOTO_IMOVEL_ATU_CADASTRAL = 23787;

	public final static Integer CODIGO_SUBCATEGORIA_IMOVEL_SUBCATEGORIA_ATU_CADASTRAL = 35027;

	public final static Integer QUANTIDADES_ECONOMIAS_IMOVEL_SUBCATEGORIA_ATU_CADASTRAL = 23852;

	public final static Integer CODIGO_CATEGORIA_IMOVEL_SUBCATEGORIA_ATU_CADASTRAL = 23854;
	
	public final static Integer TIPO_FONE_CLIENTE_TELEFONE = 23848;
	
	public final static Integer DDD_CLIENTE_TELEFONE = 23849;
	
	public final static Integer NUMERO_FONE_CLIENTE_TELEFONE = 23850;
	
	public final static Integer INDICADOR_FONE_PADRAO_CLIENTE_TELEFONE = 115095;

	public final static Integer ID_IMOVEL = 278;

	public final static Integer ID_CLIENTE_ATU_CADASTRAL = 115096;
	
	public final static Integer CODIGO_CLIENTE_ATU_CADASTRAL = 115097;

	public final static Integer ID_CLIENTE_TELEFONE_ATU_CADASTRAL = 115092;
	
	public final static String CATEGORIA_TABELA_IMOVEL = "115280";

	public final static String SUBCATEGORIA_TABELA_IMOVEL = "115281";

	public final static String QUANTIDADE_ECONOMIA_TABELA_IMOVEL = "295";
	
	public final static String LIGACAO_AGUA_SITUACAO_ID_TABELA_IMOVEL = "398";
	
	public final static String LIGACAO_ESGOTO_SITUACAO_ID_TABELA_IMOVEL = "399";

	public final static String NUMERO_IMOVEL_TABELA_IMOVEL = "2643";

	public final static String LOCALIDADE_TABELA_IMOVEL = "279";

	public final static String SETOR_COMERCIAL_TABELA_IMOVEL = "280";

	public final static String QUADRA_TABELA_IMOVEL = "281";

	public final static String LOTE_TABELA_IMOVEL = "284";

	public final static String SUBLOTE_TABELA_IMOVEL = "285";
	
	public final static String BAIRRO_TABELA_IMOVEL = "2641";
	public final static String CEP_TABELA_IMOVEL = "2633";
	public final static String LOGRADOURO_TABELA_IMOVEL = "2638";
	
	public final static String IDCLIENTE_TABELA_CLIENTE = "269";
	public final static String NOME_CLIENTE_TABELA_CLIENTE = "272";
	public final static String CPF_CLIENTE_TABELA_CLIENTE = "271";
	public final static String CNPJ_CLIENTE_TABELA_CLIENTE = "275";
	public final static String RG_TABELA_CLIENTE = "274";
	public final static String RG_DATAEMISSAO_TABELA_CLIENTE = "827";
	public final static String UNIDADE_FEDERECAO_TABELA_CLIENTE = "2497";
	public final static String ORGAO_EXPEDIDOR_TABELA_CLIENTE = "2496";
	
	public final static String USUARIO_ID = "444";

	/** full constructor */
	public TabelaColuna(String coluna, String descricaoColuna,
			Date ultimaAlteracao, gcom.seguranca.transacao.Tabela tabela,
			Short indicadorPrimaryKey, String nomeAbreviado) {
		this.coluna = coluna;
		this.descricaoColuna = descricaoColuna;
		this.ultimaAlteracao = ultimaAlteracao;
		this.tabela = tabela;
		this.indicadorPrimaryKey = indicadorPrimaryKey;
		this.nomeAbreviado = nomeAbreviado;
	}

	/** default constructor */
	public TabelaColuna() {
	}

	/** minimal constructor */
	public TabelaColuna(gcom.seguranca.transacao.Tabela tabela) {
		this.tabela = tabela;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColuna() {
		return this.coluna;
	}

	public void setColuna(String coluna) {
		this.coluna = coluna;
	}

	public String getDescricaoColuna() {
		return this.descricaoColuna;
	}

	public void setDescricaoColuna(String descricaoColuna) {
		this.descricaoColuna = descricaoColuna;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.seguranca.transacao.Tabela getTabela() {
		return this.tabela;
	}

	public void setTabela(gcom.seguranca.transacao.Tabela tabela) {
		this.tabela = tabela;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Retorna o campo indicadorPrimaryKey.
	 */
	public Short getIndicadorPrimaryKey() {
		return indicadorPrimaryKey;
	}

	/**
	 * @param indicadorPrimaryKey
	 *            O indicadorPrimaryKey a ser setado.
	 */
	public void setIndicadorPrimaryKey(Short indicadorPrimaryKey) {
		this.indicadorPrimaryKey = indicadorPrimaryKey;
	}

	/**
	 * @return Retorna o campo nomeAbreviado.
	 */
	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	/**
	 * @param nomeAbreviado
	 *            O nomeAbreviado a ser setado.
	 */
	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public Integer getIndicadorPodeRetificarConta() {
		return indicadorPodeRetificarConta;
	}

	public void setIndicadorPodeRetificarConta(
			Integer indicadorPodeRetificarConta) {
		this.indicadorPodeRetificarConta = indicadorPodeRetificarConta;
	}

	public Short getIndicadorAtualizacaoCadastral() {
		return indicadorAtualizacaoCadastral;
	}

	public void setIndicadorAtualizacaoCadastral(
			Short indicadorAtualizacaoCadastral) {
		this.indicadorAtualizacaoCadastral = indicadorAtualizacaoCadastral;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
}

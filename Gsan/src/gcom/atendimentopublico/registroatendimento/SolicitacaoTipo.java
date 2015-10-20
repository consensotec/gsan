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
* Anderson Italo felinto de Lima
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
package gcom.atendimentopublico.registroatendimento;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class SolicitacaoTipo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR = 280; //Operacao.OPERACAO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR
	public static final int ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER = 577; //Operacao.OPERACAO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
	public static final int ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR = 664;//Operacao.OPERACAO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	/** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
	private String descricao;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
	private short indicadorFaltaAgua;

	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
	private short indicadorTarifaSocial;

	/** persistent field */
	@ControleAlteracao(value=FiltroSolicitacaoTipo.SOLICITACAO_TIPO_GRUPO,funcionalidade={ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
	private gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo solicitacaoTipoGrupo;

	/** persistent field */
	private short indicadorUso;
	
	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER,ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
	private short indicadorUsoSistema;
	
	/**
	 * Description of the Field
	 */
	public final static Short INDICADOR_USO_ATIVO = new Short("1");
	public final static Short INDICADOR_FALTA_AGUA_SIM = new Short("1");
	public final static Short INDICADOR_USO_SISTEMA_SIM = new Short("1");
	public final static Short INDICADOR_USO_SISTEMA_NAO = new Short("2");
	
	public final static Integer FISCALIZACAO = new Integer("42894");

	/** full constructor */
	public SolicitacaoTipo(
			String descricao,
			Date ultimaAlteracao,
			short indicadorFaltaAgua,
			short indicadorTarifaSocial,
			gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo solicitacaoTipoGrupo,
			short indicadorUso) {
		this.descricao = descricao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorFaltaAgua = indicadorFaltaAgua;
		this.indicadorTarifaSocial = indicadorTarifaSocial;
		this.solicitacaoTipoGrupo = solicitacaoTipoGrupo;
		this.indicadorUso = indicadorUso;
	}

	/** default constructor */
	public SolicitacaoTipo() {
	}

	/** minimal constructor */
	public SolicitacaoTipo(
			Date ultimaAlteracao,
			short indicadorFaltaAgua,
			gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo solicitacaoTipoGrupo) {
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorFaltaAgua = indicadorFaltaAgua;
		this.solicitacaoTipoGrupo = solicitacaoTipoGrupo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public short getIndicadorFaltaAgua() {
		return this.indicadorFaltaAgua;
	}

	public void setIndicadorFaltaAgua(short indicadorFaltaAgua) {
		this.indicadorFaltaAgua = indicadorFaltaAgua;
	}

	public gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo getSolicitacaoTipoGrupo() {
		return this.solicitacaoTipoGrupo;
	}

	public void setSolicitacaoTipoGrupo(
			gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo solicitacaoTipoGrupo) {
		this.solicitacaoTipoGrupo = solicitacaoTipoGrupo;
	}

	public short getIndicadorTarifaSocial() {
		return indicadorTarifaSocial;
	}

	public void setIndicadorTarifaSocial(short indicadorTarifaSocial) {
		this.indicadorTarifaSocial = indicadorTarifaSocial;
	}

	public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Retorna o campo indicadorUsoSistema.
	 */
	public short getIndicadorUsoSistema() {
		return indicadorUsoSistema;
	}

	/**
	 * @param indicadorUsoSistema O indicadorUsoSistema a ser setado.
	 */
	public void setIndicadorUsoSistema(short indicadorUsoSistema) {
		this.indicadorUsoSistema = indicadorUsoSistema;
	}
	
	
	public Filtro retornaFiltro() {
		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
		filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.ID,
				this.getId()));
		filtroSolicitacaoTipo.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoGrupo");
		return filtroSolicitacaoTipo;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"descricao"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Descricao do Tipo de Solicitacao"};
		return labels;		
	}

}

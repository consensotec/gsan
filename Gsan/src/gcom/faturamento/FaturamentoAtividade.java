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
package gcom.faturamento;

import gcom.batch.Processo;
import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class FaturamentoAtividade extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Short indicadorObrigatoriedadeAtividade;

	/** nullable persistent field */
	private Short indicadorPossibilidadeRepeticaoAtividade;

	/** nullable persistent field */
	private Short indicadorPossibilidadeComandoAtividade;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private gcom.faturamento.FaturamentoAtividade faturamentoAtividadePrecedente;

	private Processo processo;

	/** persistent field */
	private Short ordemRealizacao;
	
	/** persistent field */
    private gcom.cobranca.CobrancaAcao cobrancaAcao;

	// --CONSTANTES
	/**
	 * Description of the Field
	 */
	public final static Integer GERAR_ARQUIVO_LEITURA = new Integer(1);

	public final static Integer EFETUAR_LEITURA = new Integer(2);

	public final static Integer REGISTRAR_LEITURA_ANORMALIDADE = new Integer(3);

	public final static Integer GERAR_FISCALIZACAO = new Integer(4);

	public final static Integer TRANSMITIR_ARQUIVO = new Integer(7);

	public final static Integer DISTRIBUIR_CONTAS = new Integer(6);

	public final static Integer FATURAR_GRUPO = new Integer(5);

	public final static Integer SIMULAR_FATURAMENTO = new Integer(8);
	
	public final static Integer PRE_FATURAR_GRUPO = new Integer(0);

	public final static Integer CONSISTIR_LEITURAS_E_CALCULAR_CONSUMOS = new Integer(
			9);

	public final static Short ATIVIDADE_POSSIVEL_COMANDO = new Short("1");

	public final static Short ATIVIDADE_POSSIVEL_REPETICAO = new Short("1");

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	/** full constructor */
	public FaturamentoAtividade(
			String descricao,
			Short indicadorUso,
			Short indicadorObrigatoriedadeAtividade,
			Short indicadorPossibilidadeRepeticaoAtividade,
			Short indicadorPossibilidadeComandoAtividade,
			Date ultimaAlteracao,
			gcom.faturamento.FaturamentoAtividade faturamentoAtividadePrecedente,
			Short ordemRealizacao) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.indicadorObrigatoriedadeAtividade = indicadorObrigatoriedadeAtividade;
		this.indicadorPossibilidadeRepeticaoAtividade = indicadorPossibilidadeRepeticaoAtividade;
		this.indicadorPossibilidadeComandoAtividade = indicadorPossibilidadeComandoAtividade;
		this.ultimaAlteracao = ultimaAlteracao;
		this.faturamentoAtividadePrecedente = faturamentoAtividadePrecedente;
		this.ordemRealizacao = ordemRealizacao;
	}

	/** default constructor */
	public FaturamentoAtividade() {
	}

	/** minimal constructor */
	public FaturamentoAtividade(
			gcom.faturamento.FaturamentoAtividade faturamentoAtividadePrecedente) {
		this.faturamentoAtividadePrecedente = faturamentoAtividadePrecedente;
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

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Short getIndicadorObrigatoriedadeAtividade() {
		return this.indicadorObrigatoriedadeAtividade;
	}

	public void setIndicadorObrigatoriedadeAtividade(
			Short indicadorObrigatoriedadeAtividade) {
		this.indicadorObrigatoriedadeAtividade = indicadorObrigatoriedadeAtividade;
	}

	public Short getIndicadorPossibilidadeRepeticaoAtividade() {
		return this.indicadorPossibilidadeRepeticaoAtividade;
	}

	public void setIndicadorPossibilidadeRepeticaoAtividade(
			Short indicadorPossibilidadeRepeticaoAtividade) {
		this.indicadorPossibilidadeRepeticaoAtividade = indicadorPossibilidadeRepeticaoAtividade;
	}

	public Short getIndicadorPossibilidadeComandoAtividade() {
		return this.indicadorPossibilidadeComandoAtividade;
	}

	public void setIndicadorPossibilidadeComandoAtividade(
			Short indicadorPossibilidadeComandoAtividade) {
		this.indicadorPossibilidadeComandoAtividade = indicadorPossibilidadeComandoAtividade;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.faturamento.FaturamentoAtividade getFaturamentoAtividadePrecedente() {
		return this.faturamentoAtividadePrecedente;
	}

	public void setFaturamentoAtividadePrecedente(
			gcom.faturamento.FaturamentoAtividade faturamentoAtividadePrecedente) {
		this.faturamentoAtividadePrecedente = faturamentoAtividadePrecedente;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Returns the ordemRealizacao.
	 */
	public Short getOrdemRealizacao() {
		return ordemRealizacao;
	}

	/**
	 * @param ordemRealizacao
	 *            The ordemRealizacao to set.
	 */
	public void setOrdemRealizacao(Short ordemRealizacao) {
		this.ordemRealizacao = ordemRealizacao;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public gcom.cobranca.CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}

	public void setCobrancaAcao(gcom.cobranca.CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}
}

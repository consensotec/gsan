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
package gcom.cobranca;

import gcom.batch.Processo;
import gcom.faturamento.conta.FiltroContaMensagem;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CobrancaAtividade extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	// Constantes de atividades de cobran�a >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public final static Integer SIMULAR = new Integer(1);
	public final static Integer EMITIR = new Integer(2);
	public final static Integer ENCERRAR = new Integer(3);
	public final static Integer ENCERRAR_OS = new Integer(5);
	public final static Integer ATIVO_CRONOGRAMA = new Integer(1);
	public final static Short INDICADOR_OBRIGATORIEDADE_ATIVO = 1;
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricaoCobrancaAtividade;

	/** nullable persistent field */
	private Short indicadorComando;

	/** nullable persistent field */
	private Short indicadorExecucao;

	/** nullable persistent field */
	private Short indicadorCronograma;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private Short ordemRealizacao;

	private Processo processo;
	
	private Short indicadorObrigatoriedade;

	/** persistent field */
	private gcom.cobranca.CobrancaAtividade cobrancaAtividadePredecessora;

	private Integer numeroDiasExecucao;
	
	private CobrancaAcao cobrancaAcao;
	
	//criado para exibi��o na tela de Inserir Cronograma de Cobran�a
	private Date dataPrevista;
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	/** full constructor */
	public CobrancaAtividade(String descricaoCobrancaAtividade,
			Short indicadorComando, Short indicadorExecucao,
			Short indicadorUso, Date ultimaAlteracao,
			gcom.cobranca.CobrancaAtividade cobrancaAtividadePredecessora) {
		this.descricaoCobrancaAtividade = descricaoCobrancaAtividade;
		this.indicadorComando = indicadorComando;
		this.indicadorExecucao = indicadorExecucao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.cobrancaAtividadePredecessora = cobrancaAtividadePredecessora;
	}

	/** default constructor */
	public CobrancaAtividade() {
	}

	/** minimal constructor */
	public CobrancaAtividade(
			gcom.cobranca.CobrancaAtividade cobrancaAtividadePredecessora) {
		this.cobrancaAtividadePredecessora = cobrancaAtividadePredecessora;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoCobrancaAtividade() {
		return this.descricaoCobrancaAtividade;
	}

	public void setDescricaoCobrancaAtividade(String descricaoCobrancaAtividade) {
		this.descricaoCobrancaAtividade = descricaoCobrancaAtividade;
	}

	public Short getIndicadorComando() {
		return this.indicadorComando;
	}

	public void setIndicadorComando(Short indicadorComando) {
		this.indicadorComando = indicadorComando;
	}

	public Short getIndicadorExecucao() {
		return this.indicadorExecucao;
	}

	public void setIndicadorExecucao(Short indicadorExecucao) {
		this.indicadorExecucao = indicadorExecucao;
	}

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.cobranca.CobrancaAtividade getCobrancaAtividadePredecessora() {
		return this.cobrancaAtividadePredecessora;
	}

	public void setCobrancaAtividadePredecessora(
			gcom.cobranca.CobrancaAtividade cobrancaAtividadePredecessora) {
		this.cobrancaAtividadePredecessora = cobrancaAtividadePredecessora;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Short getOrdemRealizacao() {
		return ordemRealizacao;
	}

	public void setOrdemRealizacao(Short ordemRealizacao) {
		this.ordemRealizacao = ordemRealizacao;
	}

	public Short getIndicadorCronograma() {
		return indicadorCronograma;
	}

	public void setIndicadorCronograma(Short indicadorCronograma) {
		this.indicadorCronograma = indicadorCronograma;
	}

	/**
	 * @return Retorna o campo processo.
	 */
	public Processo getProcesso() {
		return processo;
	}

	/**
	 * @param processo
	 *            O processo a ser setado.
	 */
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Short getIndicadorObrigatoriedade() {
		return indicadorObrigatoriedade;
	}

	public void setIndicadorObrigatoriedade(Short indicadorObrigatoriedade) {
		this.indicadorObrigatoriedade = indicadorObrigatoriedade;
	}
	
	public Filtro retornaFiltro() {
		
		FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
		filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroContaMensagem.ID,this.getId()));
		filtroCobrancaAtividade.adicionarCaminhoParaCarregamentoEntidade("processo");
		
		
		return filtroCobrancaAtividade;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}

	public void setCobrancaAcao(CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	public Integer getNumeroDiasExecucao() {
		return numeroDiasExecucao;
	}

	public void setNumeroDiasExecucao(Integer numeroDiasExecucao) {
		this.numeroDiasExecucao = numeroDiasExecucao;
	}

	public String getDataPrevistaFormatada() {
		
		String retorno = "";
		
		if(dataPrevista != null){
			retorno = Util.formatarData(dataPrevista);
		}
		
		return retorno;
	}
}

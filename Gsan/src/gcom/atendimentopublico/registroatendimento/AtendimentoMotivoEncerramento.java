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
package gcom.atendimentopublico.registroatendimento;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AtendimentoMotivoEncerramento extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** persistent field */
	private short indicadorUso;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private short indicadorExecucao;

	/** persistent field */
	private short indicadorDuplicidade;
	
	private Integer qtdeDiasAditivoPrazo;
	
	private Short indicadorExibirFormOrdemSeletiva;
	
	private Short indicadorFiscalizacao;
	
	private Short indicadorVisitaRealizada;

	public Short getIndicadorVisitaRealizada() {
		return indicadorVisitaRealizada;
	}

	public void setIndicadorVisitaRealizada(Short indicadorVisitaRealizada) {
		this.indicadorVisitaRealizada = indicadorVisitaRealizada;
	}

	public final static Short INDICADOR_DUPLICIDADE_ATIVO = new Short("1");

	public final static Short INDICADOR_DUPLICIDADE_INATIVO = new Short("2");

	public final static short INDICADOR_EXECUCAO_SIM = 1;

	public final static short INDICADOR_EXECUCAO_NAO = 2;
	
	public final static short CANCELADO_POR_DERCURSO_DE_PRAZO = 32;
	
	public final static short SUSPENSA_PAG_PARC_CANC_DO_DEBITO = 68; 
	
	public final static Integer CONCLUSAO_SERVICO = 2;
	
	public final static Integer SERVICO_NAO_EXECUTADO = 69;
	
	public final static Integer DEBITO_PAGO = 3;
    
    public final static Integer TARIFA_SOCIAL_ALTERADA = 53;
    public final static Integer TARIFA_SOCIAL_EXCLUIDA = 54;
    public final static Integer TARIFA_SOCIAL_IMPLANTADA = 15;
    public final static Integer ATUALIZAR_EXCLUIR_RECADASTRAR_TARIFA_SOCIAL = 55;

    public final static short DESISTENCIA_DO_USUARIO = 4;

	public final static Integer ATUALIZACAO_CADASTRAL = new Integer(90);
    
	/** full constructor */
	public AtendimentoMotivoEncerramento(String descricao,
			String descricaoAbreviada, short indicadorUso,
			Date ultimaAlteracao, short indicadorExecucao,
			short indicadorDuplicidade) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorExecucao = indicadorExecucao;
		this.indicadorDuplicidade = indicadorDuplicidade;
	}

	/** default constructor */
	public AtendimentoMotivoEncerramento() {
	}

	/** minimal constructor */
	public AtendimentoMotivoEncerramento(short indicadorUso,
			Date ultimaAlteracao, short indicadorExecucao) {
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorExecucao = indicadorExecucao;
	}

	public short getIndicadorDuplicidade() {
		return indicadorDuplicidade;
	}

	public void setIndicadorDuplicidade(short indicadorDuplicidade) {
		this.indicadorDuplicidade = indicadorDuplicidade;
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

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public short getIndicadorExecucao() {
		return this.indicadorExecucao;
	}

	public void setIndicadorExecucao(short indicadorExecucao) {
		this.indicadorExecucao = indicadorExecucao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}    

	public Filtro retornaFiltro() {
		FiltroAtendimentoMotivoEncerramento filtro = new FiltroAtendimentoMotivoEncerramento();

		filtro.adicionarParametro(new ParametroSimples(FiltroAtendimentoMotivoEncerramento.ID, this.getId()));
		return filtro;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}

	public Integer getQtdeDiasAditivoPrazo() {
		return qtdeDiasAditivoPrazo;
	}

	public void setQtdeDiasAditivoPrazo(Integer qtdeDiasAditivoPrazo) {
		this.qtdeDiasAditivoPrazo = qtdeDiasAditivoPrazo;
	}

	public Short getIndicadorExibirFormOrdemSeletiva() {
		return indicadorExibirFormOrdemSeletiva;
	}

	public void setIndicadorExibirFormOrdemSeletiva(
			Short indicadorExibirFormOrdemSeletiva) {
		this.indicadorExibirFormOrdemSeletiva = indicadorExibirFormOrdemSeletiva;
	}

	public Short getIndicadorFiscalizacao() {
		return indicadorFiscalizacao;
	}

	public void setIndicadorFiscalizacao(Short indicadorFiscalizacao) {
		this.indicadorFiscalizacao = indicadorFiscalizacao;
	}
	
}

/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;



/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Nathalia Santos 
 * @created 11 de julho de 2012
 */
public class RelatorioCobrancaAtividadeManterBean implements RelatorioBean {

	private String descricaoCobrancaAtividade;
	
	private String indicadorComando;
	
	private String idAtividadePredecessora;
	
	private String idProcessoAssociado;
	
	private String opcaoCompoeCronograma;
	
	private String opcaoPodeSerComandada;
	
	private String opcaoAtividadeObrigatoria;
	
	private String opcaoPodeSerExecutada;
	
	private String quantidadeDiasExecucao;
	
	private String cobrancaAcao;
	
	private String ordemCronograma;
	

	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioCobrancaAtividadeManterBean(String descricaoCobrancaAtividade,
			String idAtividadePredecessora, String idProcessoAssociado, 
			String opcaoCompoeCronograma, String opcaoAtividadeObrigatoria, String opcaoPodeSerComandada, 
			String opcaoPodeSerExecutada, String quantidadeDiasExecucao, String cobrancaAcao, String ordemCronograma) {
		this.descricaoCobrancaAtividade = descricaoCobrancaAtividade;
		this.idAtividadePredecessora = idAtividadePredecessora;
		this.idProcessoAssociado = idProcessoAssociado;
		this.opcaoCompoeCronograma = opcaoCompoeCronograma;
		this.opcaoAtividadeObrigatoria = opcaoAtividadeObrigatoria;
		this.opcaoPodeSerComandada = opcaoPodeSerComandada;
		this.opcaoPodeSerExecutada = opcaoPodeSerExecutada;
		this.quantidadeDiasExecucao = quantidadeDiasExecucao;
		this.cobrancaAcao = cobrancaAcao;
		this.ordemCronograma = ordemCronograma;
		

	}
	
	public String getDescricaoCobrancaAtividade() {
		return descricaoCobrancaAtividade;
	}


	public void setDescricaoCobrancaAtividade(String descricaoCobrancaAtividade) {
		this.descricaoCobrancaAtividade = descricaoCobrancaAtividade;
	}


	public String getIndicadorComando() {
		return indicadorComando;
	}


	public void setIndicadorComando(String indicadorComando) {
		this.indicadorComando = indicadorComando;
	}


	public String getIdAtividadePredecessora() {
		return idAtividadePredecessora;
	}


	public void setIdAtividadePredecessora(String idAtividadePredecessora) {
		this.idAtividadePredecessora = idAtividadePredecessora;
	}

	public String getIdProcessoAssociado() {
		return idProcessoAssociado;
	}


	public void setIdProcessoAssociado(String idProcessoAssociado) {
		this.idProcessoAssociado = idProcessoAssociado;
	}


	public String getOpcaoCompoeCronograma() {
		return opcaoCompoeCronograma;
	}


	public void setOpcaoCompoeCronograma(String opcaoCompoeCronograma) {
		this.opcaoCompoeCronograma = opcaoCompoeCronograma;
	}


	public String getOpcaoPodeSerComandada() {
		return opcaoPodeSerComandada;
	}


	public void setOpcaoPodeSerComandada(String opcaoPodeSerComandada) {
		this.opcaoPodeSerComandada = opcaoPodeSerComandada;
	}


	public String getOpcaoAtividadeObrigatoria() {
		return opcaoAtividadeObrigatoria;
	}


	public void setOpcaoAtividadeObrigatoria(String opcaoAtividadeObrigatoria) {
		this.opcaoAtividadeObrigatoria = opcaoAtividadeObrigatoria;
	}


	public String getOpcaoPodeSerExecutada() {
		return opcaoPodeSerExecutada;
	}


	public void setOpcaoPodeSerExecutada(String opcaoPodeSerExecutada) {
		this.opcaoPodeSerExecutada = opcaoPodeSerExecutada;
	}


	public String getQuantidadeDiasExecucao() {
		return quantidadeDiasExecucao;
	}


	public void setQuantidadeDiasExecucao(String quantidadeDiasExecucao) {
		this.quantidadeDiasExecucao = quantidadeDiasExecucao;
	}


	public String getCobrancaAcao() {
		return cobrancaAcao;
	}


	public void setCobrancaAcao(String cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	public String getOrdemCronograma() {
		return ordemCronograma;
	}

	public void setOrdemCronograma(String ordemCronograma) {
		this.ordemCronograma = ordemCronograma;
	}

	
}

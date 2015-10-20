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
* Thiago Vieira de Melo
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
package gcom.gui.cobranca.spcserasa;

import org.apache.struts.action.ActionForm;

/**
 * Classe que representa o form da pagina de exibição do filtro 
 * para pesquisa de comandos de negativação por critério
 * 
 * @author: Fábio Aguiar
 * @date: 18/03/2015
 */

public class FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idNegativador;
	private String titulo;
	private String tipoPesquisaTitulo;
	private String comandoSimulado;
	private String nomeCliente;
	private String codigoCliente;
	private String geracaoComandoDataInicial;
	private String geracaoComandoDataFinal;
	private String execucaoComandoDataInicial;
	private String execucaoComandoDataFinal;
	private String referenciaDebitoDataInicial;
	private String referenciaDebitoDataFinal;
	private String vencimentoDebitoDataInicial;
	private String vencimentoDebitoDataFinal;
	private String valorDebitoInicial;
	private String valorDebitoFinal;
	private String situacaoComando;
	
	private String localidadeInicialIncompativel;
	private String okCliente;
    private String indicadorContaNomeCliente;
	
	/**
	 * @return Retorna o campo codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente O codigoCliente a ser setado.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return Retorna o campo comandoSimulado.
	 */
	public String getComandoSimulado() {
		return comandoSimulado;
	}

	/**
	 * @param comandoSimulado O comandoSimulado a ser setado.
	 */
	public void setComandoSimulado(String comandoSimulado) {
		this.comandoSimulado = comandoSimulado;
	}

	/**
	 * @return Retorna o campo geracaoComandoDataFinal.
	 */
	public String getGeracaoComandoDataFinal() {
		return geracaoComandoDataFinal;
	}

	/**
	 * @param geracaoComandoDataFinal O geracaoComandoDataFinal a ser setado.
	 */
	public void setGeracaoComandoDataFinal(String geracaoComandoDataFinal) {
		this.geracaoComandoDataFinal = geracaoComandoDataFinal;
	}

	/**
	 * @return Retorna o campo geracaoComandoDataInicial.
	 */
	public String getGeracaoComandoDataInicial() {
		return geracaoComandoDataInicial;
	}

	/**
	 * @param geracaoComandoDataInicial O geracaoComandoDataInicial a ser setado.
	 */
	public void setGeracaoComandoDataInicial(String geracaoComandoDataInicial) {
		this.geracaoComandoDataInicial = geracaoComandoDataInicial;
	}

	
	/**
	 * @return Retorna o campo execucaoComandoDataFinal.
	 */
	public String getExecucaoComandoDataFinal() {
		return execucaoComandoDataFinal;
	}

	/**
	 * @param execucaoComandoDataFinal O execucaoComandoDataFinal a ser setado.
	 */
	public void setExecucaoComandoDataFinal(String execucaoComandoDataFinal) {
		this.execucaoComandoDataFinal = execucaoComandoDataFinal;
	}

	/**
	 * @return Retorna o campo execucaoComandoDataInicial.
	 */
	public String getExecucaoComandoDataInicial() {
		return execucaoComandoDataInicial;
	}

	/**
	 * @param execucaoComandoDataInicial O execucaoComandoDataInicial a ser setado.
	 */
	public void setExecucaoComandoDataInicial(String execucaoComandoDataInicial) {
		this.execucaoComandoDataInicial = execucaoComandoDataInicial;
	}


	/**
	 * @return Retorna o campo idNegativador.
	 */
	public String getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador O idNegativador a ser setado.
	 */
	public void setIdNegativador(String idNegativador) {
		this.idNegativador = idNegativador;
	}


	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Retorna o campo referenciaDebitoDataFinal.
	 */
	public String getReferenciaDebitoDataFinal() {
		return referenciaDebitoDataFinal;
	}

	/**
	 * @param referenciaDebitoDataFinal O referenciaDebitoDataFinal a ser setado.
	 */
	public void setReferenciaDebitoDataFinal(String referenciaDebitoDataFinal) {
		this.referenciaDebitoDataFinal = referenciaDebitoDataFinal;
	}

	/**
	 * @return Retorna o campo referenciaDebitoDataInicial.
	 */
	public String getReferenciaDebitoDataInicial() {
		return referenciaDebitoDataInicial;
	}

	/**
	 * @param referenciaDebitoDataInicial O referenciaDebitoDataInicial a ser setado.
	 */
	public void setReferenciaDebitoDataInicial(String referenciaDebitoDataInicial) {
		this.referenciaDebitoDataInicial = referenciaDebitoDataInicial;
	}

	/**
	 * @return Retorna o campo situacaoComando.
	 */
	public String getSituacaoComando() {
		return situacaoComando;
	}

	/**
	 * @param situacaoComando O situacaoComando a ser setado.
	 */
	public void setSituacaoComando(String situacaoComando) {
		this.situacaoComando = situacaoComando;
	}

	/**
	 * @return Retorna o campo tipoPesquisaTitulo.
	 */
	public String getTipoPesquisaTitulo() {
		return tipoPesquisaTitulo;
	}

	/**
	 * @param tipoPesquisaTitulo O tipoPesquisaTitulo a ser setado.
	 */
	public void setTipoPesquisaTitulo(String tipoPesquisaTitulo) {
		this.tipoPesquisaTitulo = tipoPesquisaTitulo;
	}

	/**
	 * @return Retorna o campo titulo.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo O titulo a ser setado.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return Retorna o campo valorDebitoFinal.
	 */
	public String getValorDebitoFinal() {
		return valorDebitoFinal;
	}

	/**
	 * @param valorDebitoFinal O valorDebitoFinal a ser setado.
	 */
	public void setValorDebitoFinal(String valorDebitoFinal) {
		this.valorDebitoFinal = valorDebitoFinal;
	}

	/**
	 * @return Retorna o campo valorDebitoInicial.
	 */
	public String getValorDebitoInicial() {
		return valorDebitoInicial;
	}

	/**
	 * @param valorDebitoInicial O valorDebitoInicial a ser setado.
	 */
	public void setValorDebitoInicial(String valorDebitoInicial) {
		this.valorDebitoInicial = valorDebitoInicial;
	}

	/**
	 * @return Retorna o campo vencimentoDebitoDataFinal.
	 */
	public String getVencimentoDebitoDataFinal() {
		return vencimentoDebitoDataFinal;
	}

	/**
	 * @param vencimentoDebitoDataFinal O vencimentoDebitoDataFinal a ser setado.
	 */
	public void setVencimentoDebitoDataFinal(String vencimentoDebitoDataFinal) {
		this.vencimentoDebitoDataFinal = vencimentoDebitoDataFinal;
	}

	/**
	 * @return Retorna o campo vencimentoDebitoDataInicial.
	 */
	public String getVencimentoDebitoDataInicial() {
		return vencimentoDebitoDataInicial;
	}

	/**
	 * @param vencimentoDebitoDataInicial O vencimentoDebitoDataInicial a ser setado.
	 */
	public void setVencimentoDebitoDataInicial(String vencimentoDebitoDataInicial) {
		this.vencimentoDebitoDataInicial = vencimentoDebitoDataInicial;
	}

	/**
	 * @return Retorna o campo okCliente.
	 */
	public String getOkCliente() {
		return okCliente;
	}

	/**
	 * @param okCliente O okCliente a ser setado.
	 */
	public void setOkCliente(String okCliente) {
		this.okCliente = okCliente;
	}

	/**
	 * @return Retorna o campo localidadeInicialIncompativel.
	 */
	public String getLocalidadeInicialIncompativel() {
		return localidadeInicialIncompativel;
	}

	/**
	 * @param localidadeInicialIncompativel O localidadeInicialIncompativel a ser setado.
	 */
	public void setLocalidadeInicialIncompativel(
			String localidadeInicialIncompativel) {
		this.localidadeInicialIncompativel = localidadeInicialIncompativel;
	}

	public String getIndicadorContaNomeCliente() {
		return indicadorContaNomeCliente;
	}

	public void setIndicadorContaNomeCliente(String indicadorContaNomeCliente) {
		this.indicadorContaNomeCliente = indicadorContaNomeCliente;
	}

	
	
		
}
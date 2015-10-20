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
* Thiago Vieira de Melo
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
package gcom.gui.cobranca.spcserasa;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ConsultarNegativacaoComandoGuiaPagamentoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idSelecionado;
	
	private String negativador;
	
	private String tituloComando;
	
	private String descricaoSolicitacao;
	
	private String simularNegativacao;
	
	private String dataPrevistaExecucao;
	
	private String usuarioResponsavel;
	
	private String quantidadeMaximaInclusoes;
	
	private String periodoReferenciaDebitoInicial;
	
	private String periodoReferenciaDebitoFinal;
	
	private String periodoVencimentoDebitoInicial;
	
	private String periodoVencimentoDebitoFinal;
	
	private String valorDebitoInicial;
	
	private String valorDebitoFinal;
		
	private String cliente;
	private String idCliente;
	
	public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	
    	this.idSelecionado = "";
    	this.negativador = "";
    	this.tituloComando = "";
    	this.descricaoSolicitacao = "";
    	this.simularNegativacao = "";
    	this.dataPrevistaExecucao = "";
    	this.usuarioResponsavel = "";
    	this.quantidadeMaximaInclusoes = "";
    	this.periodoReferenciaDebitoInicial = "";
    	this.periodoReferenciaDebitoFinal = "";
    	this.periodoVencimentoDebitoInicial = "";
    	this.periodoVencimentoDebitoFinal = "";
    	this.valorDebitoInicial = "";
    	this.valorDebitoFinal = "";
    	
    	this.cliente = "";
    	this.idCliente = "";
    	
    	
    }

	public String getIdSelecionado() {
		return idSelecionado;
	}

	public void setIdSelecionado(String idSelecionado) {
		this.idSelecionado = idSelecionado;
	}

	public String getNegativador() {
		return negativador;
	}

	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}

	public String getTituloComando() {
		return tituloComando;
	}

	public void setTituloComando(String tituloComando) {
		this.tituloComando = tituloComando;
	}

	public String getDescricaoSolicitacao() {
		return descricaoSolicitacao;
	}

	public void setDescricaoSolicitacao(String descricaoSolicitacao) {
		this.descricaoSolicitacao = descricaoSolicitacao;
	}

	public String getSimularNegativacao() {
		return simularNegativacao;
	}

	public void setSimularNegativacao(String simularNegativacao) {
		this.simularNegativacao = simularNegativacao;
	}

	public String getDataPrevistaExecucao() {
		return dataPrevistaExecucao;
	}

	public void setDataPrevistaExecucao(String dataPrevistaExecucao) {
		this.dataPrevistaExecucao = dataPrevistaExecucao;
	}

	public String getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(String usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	public String getQuantidadeMaximaInclusoes() {
		return quantidadeMaximaInclusoes;
	}

	public void setQuantidadeMaximaInclusoes(String quantidadeMaximaInclusoes) {
		this.quantidadeMaximaInclusoes = quantidadeMaximaInclusoes;
	}

	public String getPeriodoReferenciaDebitoInicial() {
		return periodoReferenciaDebitoInicial;
	}

	public void setPeriodoReferenciaDebitoInicial(String periodoReferenciaDebitoInicial) {
		this.periodoReferenciaDebitoInicial = periodoReferenciaDebitoInicial;
	}

	public String getPeriodoReferenciaDebitoFinal() {
		return periodoReferenciaDebitoFinal;
	}

	public void setPeriodoReferenciaDebitoFinal(String periodoReferenciaDebitoFinal) {
		this.periodoReferenciaDebitoFinal = periodoReferenciaDebitoFinal;
	}

	public String getPeriodoVencimentoDebitoInicial() {
		return periodoVencimentoDebitoInicial;
	}

	public void setPeriodoVencimentoDebitoInicial(String periodoVencimentoDebitoInicial) {
		this.periodoVencimentoDebitoInicial = periodoVencimentoDebitoInicial;
	}

	public String getPeriodoVencimentoDebitoFinal() {
		return periodoVencimentoDebitoFinal;
	}

	public void setPeriodoVencimentoDebitoFinal(String periodoVencimentoDebitoFinal) {
		this.periodoVencimentoDebitoFinal = periodoVencimentoDebitoFinal;
	}

	public String getValorDebitoInicial() {
		return valorDebitoInicial;
	}

	public void setValorDebitoInicial(String valorDebitoInicial) {
		this.valorDebitoInicial = valorDebitoInicial;
	}

	public String getValorDebitoFinal() {
		return valorDebitoFinal;
	}

	public void setValorDebitoFinal(String valorDebitoFinal) {
		this.valorDebitoFinal = valorDebitoFinal;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}


}
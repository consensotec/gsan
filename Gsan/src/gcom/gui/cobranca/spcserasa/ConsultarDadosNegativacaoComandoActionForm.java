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

public class ConsultarDadosNegativacaoComandoActionForm extends ActionForm {
	
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
	
	private String numeroContasInicial;
	
	private String numeroContasFinal;
	
	private String considerarContasRevisao;
	
	private String considerarGuiasPagamento;
	
	private String parcelamentoAtraso;
	
	private String diasAtrasoParcelamento;
	
	private String recebeuCartaParcelamentoAtraso;
	
	private String diasAtrasoAposRecebimentoCarta;
	
	private String cliente;
	private String idCliente;
	
	private String tipoRelacaoCliente;
	
	private String imovelSitEspecialCobranca;
	
	private String imovelSitCobranca;
	
	private String localidadeInicial;
	
	private String localidadeFinal;
	
	private String setorComercialInicial;
	
	private String setorComercialFinal;
	
	private String idGrupoCobranca;
	
	private String idGerenciaRegional;
	
	private String idUnidadeNegocio;
	
	private String idEloPolo;
	
	private String indicadorContaNomeCliente;
	
	
	
    public String getIndicadorContaNomeCliente() {
		return indicadorContaNomeCliente;
	}

	public void setIndicadorContaNomeCliente(String indicadorContaNomeCliente) {
		this.indicadorContaNomeCliente = indicadorContaNomeCliente;
	}

	/**
	 * @return Retorna o campo idGrupoCobranca.
	 */
	public String getIdGrupoCobranca() {
		return idGrupoCobranca;
	}

	/**
	 * @param idGrupoCobranca O idGrupoCobranca a ser setado.
	 */
	public void setIdGrupoCobranca(String idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}

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
    	this.numeroContasInicial = "";
    	this.numeroContasFinal = "";
    	this.considerarContasRevisao = "";
    	this.considerarGuiasPagamento = "";
    	this.parcelamentoAtraso = "";
    	this.diasAtrasoParcelamento = "";
    	this.recebeuCartaParcelamentoAtraso = "";
    	this.diasAtrasoAposRecebimentoCarta = "";
    	this.cliente = "";
    	this.idCliente = "";
    	this.tipoRelacaoCliente = "";
    	this.imovelSitEspecialCobranca = "";
    	this.imovelSitCobranca = "";
    	this.localidadeInicial = "";
    	this.localidadeFinal = "";
    	this.setorComercialInicial = "";
    	this.setorComercialFinal = "";
    	
    	this.idGrupoCobranca = "";
    	this.idGerenciaRegional = "";
    	this.idUnidadeNegocio = "";
    	this.idEloPolo = "";
    	
    }

	/**
	 * @return Retorna o campo idSelecionado.
	 */
	public String getIdSelecionado() {
		return idSelecionado;
	}

	/**
	 * @param idSelecionado O idSelecionado a ser setado.
	 */
	public void setIdSelecionado(String idSelecionado) {
		this.idSelecionado = idSelecionado;
	}

	/**
	 * @return Retorna o campo negativador.
	 */
	public String getNegativador() {
		return negativador;
	}

	/**
	 * @param negativador O negativador a ser setado.
	 */
	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}

	/**
	 * @return Retorna o campo tituloComando.
	 */
	public String getTituloComando() {
		return tituloComando;
	}

	/**
	 * @param tituloComando O tituloComando a ser setado.
	 */
	public void setTituloComando(String tituloComando) {
		this.tituloComando = tituloComando;
	}

	/**
	 * @return Retorna o campo dataPrevistaExecucao.
	 */
	public String getDataPrevistaExecucao() {
		return dataPrevistaExecucao;
	}

	/**
	 * @param dataPrevistaExecucao O dataPrevistaExecucao a ser setado.
	 */
	public void setDataPrevistaExecucao(String dataPrevistaExecucao) {
		this.dataPrevistaExecucao = dataPrevistaExecucao;
	}

	/**
	 * @return Retorna o campo descricaoSolicitacao.
	 */
	public String getDescricaoSolicitacao() {
		return descricaoSolicitacao;
	}

	/**
	 * @param descricaoSolicitacao O descricaoSolicitacao a ser setado.
	 */
	public void setDescricaoSolicitacao(String descricaoSolicitacao) {
		this.descricaoSolicitacao = descricaoSolicitacao;
	}

	/**
	 * @return Retorna o campo quantidadeMaximaInclusoes.
	 */
	public String getQuantidadeMaximaInclusoes() {
		return quantidadeMaximaInclusoes;
	}

	/**
	 * @param quantidadeMaximaInclusoes O quantidadeMaximaInclusoes a ser setado.
	 */
	public void setQuantidadeMaximaInclusoes(String quantidadeMaximaInclusoes) {
		this.quantidadeMaximaInclusoes = quantidadeMaximaInclusoes;
	}

	/**
	 * @return Retorna o campo simularNegativacao.
	 */
	public String getSimularNegativacao() {
		return simularNegativacao;
	}

	/**
	 * @param simularNegativacao O simularNegativacao a ser setado.
	 */
	public void setSimularNegativacao(String simularNegativacao) {
		this.simularNegativacao = simularNegativacao;
	}

	/**
	 * @return Retorna o campo usuarioResponsavel.
	 */
	public String getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	/**
	 * @param usuarioResponsavel O usuarioResponsavel a ser setado.
	 */
	public void setUsuarioResponsavel(String usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	/**
	 * @return Retorna o campo considerarContasRevisao.
	 */
	public String getConsiderarContasRevisao() {
		return considerarContasRevisao;
	}

	/**
	 * @param considerarContasRevisao O considerarContasRevisao a ser setado.
	 */
	public void setConsiderarContasRevisao(String considerarContasRevisao) {
		this.considerarContasRevisao = considerarContasRevisao;
	}

	/**
	 * @return Retorna o campo considerarGuiasPagamento.
	 */
	public String getConsiderarGuiasPagamento() {
		return considerarGuiasPagamento;
	}

	/**
	 * @param considerarGuiasPagamento O considerarGuiasPagamento a ser setado.
	 */
	public void setConsiderarGuiasPagamento(String considerarGuiasPagamento) {
		this.considerarGuiasPagamento = considerarGuiasPagamento;
	}

	/**
	 * @return Retorna o campo diasAtrasoAposRecebimentoCarta.
	 */
	public String getDiasAtrasoAposRecebimentoCarta() {
		return diasAtrasoAposRecebimentoCarta;
	}

	/**
	 * @param diasAtrasoAposRecebimentoCarta O diasAtrasoAposRecebimentoCarta a ser setado.
	 */
	public void setDiasAtrasoAposRecebimentoCarta(
			String diasAtrasoAposRecebimentoCarta) {
		this.diasAtrasoAposRecebimentoCarta = diasAtrasoAposRecebimentoCarta;
	}

	/**
	 * @return Retorna o campo diasAtrasoParcelamento.
	 */
	public String getDiasAtrasoParcelamento() {
		return diasAtrasoParcelamento;
	}

	/**
	 * @param diasAtrasoParcelamento O diasAtrasoParcelamento a ser setado.
	 */
	public void setDiasAtrasoParcelamento(String diasAtrasoParcelamento) {
		this.diasAtrasoParcelamento = diasAtrasoParcelamento;
	}

	/**
	 * @return Retorna o campo numeroContasFinal.
	 */
	public String getNumeroContasFinal() {
		return numeroContasFinal;
	}

	/**
	 * @param numeroContasFinal O numeroContasFinal a ser setado.
	 */
	public void setNumeroContasFinal(String numeroContasFinal) {
		this.numeroContasFinal = numeroContasFinal;
	}

	/**
	 * @return Retorna o campo numeroContasInicial.
	 */
	public String getNumeroContasInicial() {
		return numeroContasInicial;
	}

	/**
	 * @param numeroContasInicial O numeroContasInicial a ser setado.
	 */
	public void setNumeroContasInicial(String numeroContasInicial) {
		this.numeroContasInicial = numeroContasInicial;
	}

	/**
	 * @return Retorna o campo parcelamentoAtraso.
	 */
	public String getParcelamentoAtraso() {
		return parcelamentoAtraso;
	}

	/**
	 * @param parcelamentoAtraso O parcelamentoAtraso a ser setado.
	 */
	public void setParcelamentoAtraso(String parcelamentoAtraso) {
		this.parcelamentoAtraso = parcelamentoAtraso;
	}

	/**
	 * @return Retorna o campo periodoReferenciaDebitoFinal.
	 */
	public String getPeriodoReferenciaDebitoFinal() {
		return periodoReferenciaDebitoFinal;
	}

	/**
	 * @param periodoReferenciaDebitoFinal O periodoReferenciaDebitoFinal a ser setado.
	 */
	public void setPeriodoReferenciaDebitoFinal(String periodoReferenciaDebitoFinal) {
		this.periodoReferenciaDebitoFinal = periodoReferenciaDebitoFinal;
	}

	/**
	 * @return Retorna o campo periodoReferenciaDebitoInicial.
	 */
	public String getPeriodoReferenciaDebitoInicial() {
		return periodoReferenciaDebitoInicial;
	}

	/**
	 * @param periodoReferenciaDebitoInicial O periodoReferenciaDebitoInicial a ser setado.
	 */
	public void setPeriodoReferenciaDebitoInicial(
			String periodoReferenciaDebitoInicial) {
		this.periodoReferenciaDebitoInicial = periodoReferenciaDebitoInicial;
	}

	/**
	 * @return Retorna o campo periodoVencimentoDebitoFinal.
	 */
	public String getPeriodoVencimentoDebitoFinal() {
		return periodoVencimentoDebitoFinal;
	}

	/**
	 * @param periodoVencimentoDebitoFinal O periodoVencimentoDebitoFinal a ser setado.
	 */
	public void setPeriodoVencimentoDebitoFinal(String periodoVencimentoDebitoFinal) {
		this.periodoVencimentoDebitoFinal = periodoVencimentoDebitoFinal;
	}

	/**
	 * @return Retorna o campo periodoVencimentoDebitoInicial.
	 */
	public String getPeriodoVencimentoDebitoInicial() {
		return periodoVencimentoDebitoInicial;
	}

	/**
	 * @param periodoVencimentoDebitoInicial O periodoVencimentoDebitoInicial a ser setado.
	 */
	public void setPeriodoVencimentoDebitoInicial(
			String periodoVencimentoDebitoInicial) {
		this.periodoVencimentoDebitoInicial = periodoVencimentoDebitoInicial;
	}

	/**
	 * @return Retorna o campo recebeuCartaParcelamentoAtraso.
	 */
	public String getRecebeuCartaParcelamentoAtraso() {
		return recebeuCartaParcelamentoAtraso;
	}

	/**
	 * @param recebeuCartaParcelamentoAtraso O recebeuCartaParcelamentoAtraso a ser setado.
	 */
	public void setRecebeuCartaParcelamentoAtraso(
			String recebeuCartaParcelamentoAtraso) {
		this.recebeuCartaParcelamentoAtraso = recebeuCartaParcelamentoAtraso;
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
	 * @return Retorna o campo cliente.
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @param cliente O cliente a ser setado.
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	
	
	/**
	 * @return Retorna o campo idCliente.
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente O idCliente a ser setado.
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return Retorna o campo imovelSitCobranca.
	 */
	public String getImovelSitCobranca() {
		return imovelSitCobranca;
	}

	/**
	 * @param imovelSitCobranca O imovelSitCobranca a ser setado.
	 */
	public void setImovelSitCobranca(String imovelSitCobranca) {
		this.imovelSitCobranca = imovelSitCobranca;
	}

	/**
	 * @return Retorna o campo imovelSitEspecialCobranca.
	 */
	public String getImovelSitEspecialCobranca() {
		return imovelSitEspecialCobranca;
	}

	/**
	 * @param imovelSitEspecialCobranca O imovelSitEspecialCobranca a ser setado.
	 */
	public void setImovelSitEspecialCobranca(String imovelSitEspecialCobranca) {
		this.imovelSitEspecialCobranca = imovelSitEspecialCobranca;
	}

	/**
	 * @return Retorna o campo tipoRelacaoCliente.
	 */
	public String getTipoRelacaoCliente() {
		return tipoRelacaoCliente;
	}

	/**
	 * @param tipoRelacaoCliente O tipoRelacaoCliente a ser setado.
	 */
	public void setTipoRelacaoCliente(String tipoRelacaoCliente) {
		this.tipoRelacaoCliente = tipoRelacaoCliente;
	}

	/**
	 * @return Retorna o campo localidadeFinal.
	 */
	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	/**
	 * @param localidadeFinal O localidadeFinal a ser setado.
	 */
	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	/**
	 * @return Retorna o campo localidadeInicial.
	 */
	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	/**
	 * @param localidadeInicial O localidadeInicial a ser setado.
	 */
	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	/**
	 * @return Retorna o campo setorComercialFinal.
	 */
	public String getSetorComercialFinal() {
		return setorComercialFinal;
	}

	/**
	 * @param setorComercialFinal O setorComercialFinal a ser setado.
	 */
	public void setSetorComercialFinal(String setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	/**
	 * @return Retorna o campo setorComercialInicial.
	 */
	public String getSetorComercialInicial() {
		return setorComercialInicial;
	}

	/**
	 * @param setorComercialInicial O setorComercialInicial a ser setado.
	 */
	public void setSetorComercialInicial(String setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	/**
	 * @return Retorna o campo idEloPolo.
	 */
	public String getIdEloPolo() {
		return idEloPolo;
	}

	/**
	 * @param idEloPolo O idEloPolo a ser setado.
	 */
	public void setIdEloPolo(String idEloPolo) {
		this.idEloPolo = idEloPolo;
	}

	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * @return Retorna o campo idunidadeNegocio.
	 */
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	/**
	 * @param idunidadeNegocio O idunidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	

}
/*
 * 
 */
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
package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0325] Consultar Comandos de A��o de Cobran�a
 * @author Rafael Santos
 * @since 11/05/2006
 */
public class ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm  extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idCobrancaAcaoAtividadeComando;

	private String acaoCobranca;
	
	private String atividadeCobranca;
	
	private String criterioUtilizado;
	
	private String criterio;
	
	private String grupoCobranca;
	
	private String gerenciaRegional;
	
	private String unidadeNegocio;
	
	private String localidadeInicial;
	
	private String localidadeFinal;
	
	private String descricaoLocalidadeFinal;
	
	private String descricaoLocalidadeInicial;
	
	private String setorComercialInicial;
	
	private String descricaoSetorComercialInicial;
	
	private String setorComercialFinal;
	
	private String descricaoSetorComercialFinal;
	
	private String rotaInicial;
	
	private String rotaFinal;
	
	private String cliente;
	
	private String tipoRelacaoCliente;
	
	private String dataComando;
	
	private String horaComando;
	
	private String horaRealizacao;
	
	private String dataRealizacao;
	
	private String periodoReferenciaContasInicial;
	
	private String periodoReferenciaContasFinal;
	
	private String periodoVencimentoContasInicial;
	
	private String periodoVencimentoContasFinal;
	
	private String valorDocumentos;
	
	private String quantidadeDocumentos;
	
	private String quantidadeItensDocumentos;
	
	private String situacaoComando;

	private String consumoMedioInicial;
	
	private String consumoMedioFinal;
	
	private String tipoConsumo;
	
	private String periodoInicialFiscalizacao;

	private String periodoFinalFiscalizacao;
	
	private String[] situacaoFiscalizacao;
	
	private String idImovel;
	
	private String quantidadeDiasVencimento;
	
	private String indicadorSelecionarImoveisSitLigAlteradaDebito;
	
	private String nomeArquivoRelacaoImoveis;
	
	private String quantidadeSmsGerados;
	private String quantidadeSmsEnviados;
	private String quantidadeEmailGerados;
	private String quantidadeEmailEnviados;
	
	public String getNomeArquivoRelacaoImoveis() {
		return nomeArquivoRelacaoImoveis;
	}

	public void setNomeArquivoRelacaoImoveis(String nomeArquivoRelacaoImoveis) {
		this.nomeArquivoRelacaoImoveis = nomeArquivoRelacaoImoveis;
	}

	public String getIndicadorSelecionarImoveisSitLigAlteradaDebito() {
		return indicadorSelecionarImoveisSitLigAlteradaDebito;
	}

	public void setIndicadorSelecionarImoveisSitLigAlteradaDebito(String indicadorSelecionarImoveisSitLigAlteradaDebito) {
		this.indicadorSelecionarImoveisSitLigAlteradaDebito = indicadorSelecionarImoveisSitLigAlteradaDebito;
	}

	public String getQuantidadeDiasVencimento() {
		return quantidadeDiasVencimento;
	}

	public void setQuantidadeDiasVencimento(String quantidadeDiasVencimento) {
		this.quantidadeDiasVencimento = quantidadeDiasVencimento;
	}

	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

	/**
	 * @return Retorna o campo acaoCobranca.
	 */
	public String getAcaoCobranca() {
		return acaoCobranca;
	}

	/**
	 * @param acaoCobranca O acaoCobranca a ser setado.
	 */
	public void setAcaoCobranca(String acaoCobranca) {
		this.acaoCobranca = acaoCobranca;
	}

	/**
	 * @return Retorna o campo atividadeCobranca.
	 */
	public String getAtividadeCobranca() {
		return atividadeCobranca;
	}

	/**
	 * @param atividadeCobranca O atividadeCobranca a ser setado.
	 */
	public void setAtividadeCobranca(String atividadeCobranca) {
		this.atividadeCobranca = atividadeCobranca;
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
	 * @return Retorna o campo criterio.
	 */
	public String getCriterio() {
		return criterio;
	}

	/**
	 * @param criterio O criterio a ser setado.
	 */
	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	/**
	 * @return Retorna o campo criterioUtilizado.
	 */
	public String getCriterioUtilizado() {
		return criterioUtilizado;
	}

	/**
	 * @param criterioUtilizado O criterioUtilizado a ser setado.
	 */
	public void setCriterioUtilizado(String criterioUtilizado) {
		this.criterioUtilizado = criterioUtilizado;
	}

	/**
	 * @return Retorna o campo dataComando.
	 */
	public String getDataComando() {
		return dataComando;
	}

	/**
	 * @param dataComando O dataComando a ser setado.
	 */
	public void setDataComando(String dataComando) {
		this.dataComando = dataComando;
	}

	/**
	 * @return Retorna o campo dataRealizacao.
	 */
	public String getDataRealizacao() {
		return dataRealizacao;
	}

	/**
	 * @param dataRealizacao O dataRealizacao a ser setado.
	 */
	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	/**
	 * @return Retorna o campo descricaoLocalidadeFinal.
	 */
	public String getDescricaoLocalidadeFinal() {
		return descricaoLocalidadeFinal;
	}

	/**
	 * @param descricaoLocalidadeFinal O descricaoLocalidadeFinal a ser setado.
	 */
	public void setDescricaoLocalidadeFinal(String descricaoLocalidadeFinal) {
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
	}

	/**
	 * @return Retorna o campo descricaoLocalidadeInicial.
	 */
	public String getDescricaoLocalidadeInicial() {
		return descricaoLocalidadeInicial;
	}

	/**
	 * @param descricaoLocalidadeInicial O descricaoLocalidadeInicial a ser setado.
	 */
	public void setDescricaoLocalidadeInicial(String descricaoLocalidadeInicial) {
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
	}

	/**
	 * @return Retorna o campo descricaoSetorComercialFinal.
	 */
	public String getDescricaoSetorComercialFinal() {
		return descricaoSetorComercialFinal;
	}

	/**
	 * @param descricaoSetorComercialFinal O descricaoSetorComercialFinal a ser setado.
	 */
	public void setDescricaoSetorComercialFinal(String descricaoSetorComercialFinal) {
		this.descricaoSetorComercialFinal = descricaoSetorComercialFinal;
	}

	/**
	 * @return Retorna o campo descricaoSetorComercialInicial.
	 */
	public String getDescricaoSetorComercialInicial() {
		return descricaoSetorComercialInicial;
	}

	/**
	 * @param descricaoSetorComercialInicial O descricaoSetorComercialInicial a ser setado.
	 */
	public void setDescricaoSetorComercialInicial(
			String descricaoSetorComercialInicial) {
		this.descricaoSetorComercialInicial = descricaoSetorComercialInicial;
	}

	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * @return Retorna o campo grupoCobranca.
	 */
	public String getGrupoCobranca() {
		return grupoCobranca;
	}

	/**
	 * @param grupoCobranca O grupoCobranca a ser setado.
	 */
	public void setGrupoCobranca(String grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}

	/**
	 * @return Retorna o campo horaComando.
	 */
	public String getHoraComando() {
		return horaComando;
	}

	/**
	 * @param horaComando O horaComando a ser setado.
	 */
	public void setHoraComando(String horaComando) {
		this.horaComando = horaComando;
	}

	/**
	 * @return Retorna o campo horaRealizcao.
	 */
	public String getHoraRealizacao() {
		return horaRealizacao;
	}

	/**
	 * @param horaRealizcao O horaRealizcao a ser setado.
	 */
	public void setHoraRealizacao(String horaRealizacao) {
		this.horaRealizacao = horaRealizacao;
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
	 * @return Retorna o campo periodoReferenciaContasFinal.
	 */
	public String getPeriodoReferenciaContasFinal() {
		return periodoReferenciaContasFinal;
	}

	/**
	 * @param periodoReferenciaContasFinal O periodoReferenciaContasFinal a ser setado.
	 */
	public void setPeriodoReferenciaContasFinal(String periodoReferenciaContasFinal) {
		this.periodoReferenciaContasFinal = periodoReferenciaContasFinal;
	}

	/**
	 * @return Retorna o campo periodoReferenciaContasInicial.
	 */
	public String getPeriodoReferenciaContasInicial() {
		return periodoReferenciaContasInicial;
	}

	/**
	 * @param periodoReferenciaContasInicial O periodoReferenciaContasInicial a ser setado.
	 */
	public void setPeriodoReferenciaContasInicial(
			String periodoReferenciaContasInicial) {
		this.periodoReferenciaContasInicial = periodoReferenciaContasInicial;
	}

	/**
	 * @return Retorna o campo periodoVencimentoContasFinal.
	 */
	public String getPeriodoVencimentoContasFinal() {
		return periodoVencimentoContasFinal;
	}

	/**
	 * @param periodoVencimentoContasFinal O periodoVencimentoContasFinal a ser setado.
	 */
	public void setPeriodoVencimentoContasFinal(String periodoVencimentoContasFinal) {
		this.periodoVencimentoContasFinal = periodoVencimentoContasFinal;
	}

	/**
	 * @return Retorna o campo periodoVencimentoContasInicial.
	 */
	public String getPeriodoVencimentoContasInicial() {
		return periodoVencimentoContasInicial;
	}

	/**
	 * @param periodoVencimentoContasInicial O periodoVencimentoContasInicial a ser setado.
	 */
	public void setPeriodoVencimentoContasInicial(
			String periodoVencimentoContasInicial) {
		this.periodoVencimentoContasInicial = periodoVencimentoContasInicial;
	}

	/**
	 * @return Retorna o campo quantidadeDocumentos.
	 */
	public String getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}

	/**
	 * @param quantidadeDocumentos O quantidadeDocumentos a ser setado.
	 */
	public void setQuantidadeDocumentos(String quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}

	/**
	 * @return Retorna o campo quantidedeItensDocumentos.
	 */
	public String getQuantidadeItensDocumentos() {
		return quantidadeItensDocumentos;
	}

	/**
	 * @param quantidadeItensDocumentos O quantidadeItensDocumentos a ser setado.
	 */
	public void setQuantidadeItensDocumentos(String quantidadeItensDocumentos) {
		this.quantidadeItensDocumentos = quantidadeItensDocumentos;
	}

	/**
	 * @return Retorna o campo rotaFinal.
	 */
	public String getRotaFinal() {
		return rotaFinal;
	}

	/**
	 * @param rotaFinal O rotaFinal a ser setado.
	 */
	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	/**
	 * @return Retorna o campo rotaInicial.
	 */
	public String getRotaInicial() {
		return rotaInicial;
	}

	/**
	 * @param rotaInicial O rotaInicial a ser setado.
	 */
	public void setRotaInicial(String rotaInicial) {
		this.rotaInicial = rotaInicial;
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
	 * @return Retorna o campo valorDocumentos.
	 */
	public String getValorDocumentos() {
		return valorDocumentos;
	}

	/**
	 * @param valorDocumentos O valorDocumentos a ser setado.
	 */
	public void setValorDocumentos(String valorDocumentos) {
		this.valorDocumentos = valorDocumentos;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	/**
	 * @return Retorna o campo idCobrancaAcaoAtividadeComando.
	 */
	public String getIdCobrancaAcaoAtividadeComando() {
		return idCobrancaAcaoAtividadeComando;
	}

	/**
	 * @param idCobrancaAcaoAtividadeComando O idCobrancaAcaoAtividadeComando a ser setado.
	 */
	public void setIdCobrancaAcaoAtividadeComando(
			String idCobrancaAcaoAtividadeComando) {
		this.idCobrancaAcaoAtividadeComando = idCobrancaAcaoAtividadeComando;
	}

	public String getConsumoMedioInicial() {
		return consumoMedioInicial;
	}

	public void setConsumoMedioInicial(String consumoMedioInicial) {
		this.consumoMedioInicial = consumoMedioInicial;
	}

	public String getConsumoMedioFinal() {
		return consumoMedioFinal;
	}

	public void setConsumoMedioFinal(String consumoMedioFinal) {
		this.consumoMedioFinal = consumoMedioFinal;
	}

	public String getTipoConsumo() {
		return tipoConsumo;
	}

	public void setTipoConsumo(String tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}

	public String getPeriodoInicialFiscalizacao() {
		return periodoInicialFiscalizacao;
	}

	public void setPeriodoInicialFiscalizacao(String periodoInicialFiscalizacao) {
		this.periodoInicialFiscalizacao = periodoInicialFiscalizacao;
	}

	public String getPeriodoFinalFiscalizacao() {
		return periodoFinalFiscalizacao;
	}

	public void setPeriodoFinalFiscalizacao(String periodoFinalFiscalizacao) {
		this.periodoFinalFiscalizacao = periodoFinalFiscalizacao;
	}

	public String[] getSituacaoFiscalizacao() {
		return situacaoFiscalizacao;
	}

	public void setSituacaoFiscalizacao(String[] situacaoFiscalizacao) {
		this.situacaoFiscalizacao = situacaoFiscalizacao;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getQuantidadeSmsGerados() {
		return quantidadeSmsGerados;
	}

	public void setQuantidadeSmsGerados(String quantidadeSmsGerados) {
		this.quantidadeSmsGerados = quantidadeSmsGerados;
	}

	public String getQuantidadeSmsEnviados() {
		return quantidadeSmsEnviados;
	}

	public void setQuantidadeSmsEnviados(String quantidadeSmsEnviados) {
		this.quantidadeSmsEnviados = quantidadeSmsEnviados;
	}

	public String getQuantidadeEmailGerados() {
		return quantidadeEmailGerados;
	}

	public void setQuantidadeEmailGerados(String quantidadeEmailGerados) {
		this.quantidadeEmailGerados = quantidadeEmailGerados;
	}

	public String getQuantidadeEmailEnviados() {
		return quantidadeEmailEnviados;
	}

	public void setQuantidadeEmailEnviados(String quantidadeEmailEnviados) {
		this.quantidadeEmailEnviados = quantidadeEmailEnviados;
	}

}


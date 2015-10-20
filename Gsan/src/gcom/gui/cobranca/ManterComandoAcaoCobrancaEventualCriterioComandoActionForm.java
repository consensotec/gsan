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
 * [UC0244] Manter Comando de A��o de Cobran�a - Tipo de Comando Eventual - Criterio Comando
 * Criterio Comando
 * 
 * @author Rafael Santos
 * @since 24/04/2006
 */
public class ManterComandoAcaoCobrancaEventualCriterioComandoActionForm extends
		ActionForm {
	private static final long serialVersionUID = 1L;
	private String criterioAcaoCobranca;

	private String descricaoAcaoCobranca;

	private String gerenciaRegional;

	private String localidadeOrigemID;

	private String localidadeDestinoID;

	private String inscricaoTipo;

	private String nomeLocalidadeOrigem;

	private String nomeLocalidadeDestino;

	private String setorComercialOrigemCD;

	private String setorComercialOrigemID;

	private String nomeSetorComercialOrigem;

	private String setorComercialDestinoCD;

	private String setorComercialDestinoID;

	private String nomeSetorComercialDestino;

	private String rotaInicial;

	private String rotaFinal;

	private String idCliente;

	private String nomeCliente;

	private String clienteRelacaoTipo;

	private String periodoInicialConta;

	private String periodoFinalConta;

	private String periodoVencimentoContaInicial;

	private String periodoVencimentoContaFinal;

	private String cobrancaAcao;

	private String cobrancaAtividade;

	private String cobrancaGrupo;

	private String indicador;
	
	private String dataRealizacao;

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {

		criterioAcaoCobranca = null;

		descricaoAcaoCobranca = null;

		gerenciaRegional = null;

		localidadeOrigemID = null;

		localidadeDestinoID = null;

		inscricaoTipo = null;

		nomeLocalidadeOrigem = null;

		nomeLocalidadeDestino = null;

		setorComercialOrigemCD = null;

		setorComercialOrigemID = null;

		nomeSetorComercialOrigem = null;

		setorComercialDestinoCD = null;

		setorComercialDestinoID = null;

		nomeSetorComercialDestino = null;

		rotaInicial = null;

		rotaFinal = null;

		idCliente = null;

		nomeCliente = null;

		clienteRelacaoTipo = null;

		periodoInicialConta = null;

		periodoFinalConta = null;

		periodoVencimentoContaInicial = null;

		periodoVencimentoContaFinal = null;

		cobrancaAcao = null;

		cobrancaAtividade = null;

		cobrancaGrupo = null;

		indicador = null;

	}

	/**
	 * @return Returns the criterioAcaoCobranca.
	 */
	public String getCriterioAcaoCobranca() {
		return criterioAcaoCobranca;
	}

	/**
	 * @param criterioAcaoCobranca
	 *            The criterioAcaoCobranca to set.
	 */
	public void setCriterioAcaoCobranca(String criterioAcaoCobranca) {
		this.criterioAcaoCobranca = criterioAcaoCobranca;
	}

	/**
	 * @return Returns the descricaoAcaoCobranca.
	 */
	public String getDescricaoAcaoCobranca() {
		return descricaoAcaoCobranca;
	}

	/**
	 * @param descricaoAcaoCobranca
	 *            The descricaoAcaoCobranca to set.
	 */
	public void setDescricaoAcaoCobranca(String descricaoAcaoCobranca) {
		this.descricaoAcaoCobranca = descricaoAcaoCobranca;
	}

	/**
	 * @return Returns the gerenciaRegional.
	 */
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional
	 *            The gerenciaRegional to set.
	 */
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * @return Returns the localidadeOrigemID.
	 */
	public String getLocalidadeOrigemID() {
		return localidadeOrigemID;
	}

	/**
	 * @param localidadeOrigemID
	 *            The localidadeOrigemID to set.
	 */
	public void setLocalidadeOrigemID(String localidadeOrigemID) {
		this.localidadeOrigemID = localidadeOrigemID;
	}

	/**
	 * @return Returns the inscricaoTipo.
	 */
	public String getInscricaoTipo() {
		return inscricaoTipo;
	}

	/**
	 * @param inscricaoTipo
	 *            The inscricaoTipo to set.
	 */
	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
	}

	/**
	 * @return Returns the nomeLocalidadeOrigem.
	 */
	public String getNomeLocalidadeOrigem() {
		return nomeLocalidadeOrigem;
	}

	/**
	 * @param nomeLocalidadeOrigem
	 *            The nomeLocalidadeOrigem to set.
	 */
	public void setNomeLocalidadeOrigem(String nomeLocalidadeOrigem) {
		this.nomeLocalidadeOrigem = nomeLocalidadeOrigem;
	}

	/**
	 * @return Returns the localidadeDestinoID.
	 */
	public String getLocalidadeDestinoID() {
		return localidadeDestinoID;
	}

	/**
	 * @param localidadeDestinoID
	 *            The localidadeDestinoID to set.
	 */
	public void setLocalidadeDestinoID(String localidadeDestinoID) {
		this.localidadeDestinoID = localidadeDestinoID;
	}

	/**
	 * @return Returns the nomeLocalidadeDestino.
	 */
	public String getNomeLocalidadeDestino() {
		return nomeLocalidadeDestino;
	}

	/**
	 * @param nomeLocalidadeDestino
	 *            The nomeLocalidadeDestino to set.
	 */
	public void setNomeLocalidadeDestino(String nomeLocalidadeDestino) {
		this.nomeLocalidadeDestino = nomeLocalidadeDestino;
	}

	/**
	 * @return Returns the setorComercialOrigemCD.
	 */
	public String getSetorComercialOrigemCD() {
		return setorComercialOrigemCD;
	}

	/**
	 * @param setorComercialOrigemCD
	 *            The setorComercialOrigemCD to set.
	 */
	public void setSetorComercialOrigemCD(String setorComercialOrigemCD) {
		this.setorComercialOrigemCD = setorComercialOrigemCD;
	}

	/**
	 * @return Returns the setorComercialOrigemID.
	 */
	public String getSetorComercialOrigemID() {
		return setorComercialOrigemID;
	}

	/**
	 * @param setorComercialOrigemID
	 *            The setorComercialOrigemID to set.
	 */
	public void setSetorComercialOrigemID(String setorComercialOrigemID) {
		this.setorComercialOrigemID = setorComercialOrigemID;
	}

	/**
	 * @return Returns the nomeSetorComercialOrigem.
	 */
	public String getNomeSetorComercialOrigem() {
		return nomeSetorComercialOrigem;
	}

	/**
	 * @param nomeSetorComercialOrigem
	 *            The nomeSetorComercialOrigem to set.
	 */
	public void setNomeSetorComercialOrigem(String nomeSetorComercialOrigem) {
		this.nomeSetorComercialOrigem = nomeSetorComercialOrigem;
	}

	/**
	 * @return Returns the nomeSetorComercialDestino.
	 */
	public String getNomeSetorComercialDestino() {
		return nomeSetorComercialDestino;
	}

	/**
	 * @param nomeSetorComercialDestino
	 *            The nomeSetorComercialDestino to set.
	 */
	public void setNomeSetorComercialDestino(String nomeSetorComercialDestino) {
		this.nomeSetorComercialDestino = nomeSetorComercialDestino;
	}

	/**
	 * @return Returns the setorComercialDestinoCD.
	 */
	public String getSetorComercialDestinoCD() {
		return setorComercialDestinoCD;
	}

	/**
	 * @param setorComercialDestinoCD
	 *            The setorComercialDestinoCD to set.
	 */
	public void setSetorComercialDestinoCD(String setorComercialDestinoCD) {
		this.setorComercialDestinoCD = setorComercialDestinoCD;
	}

	/**
	 * @return Returns the setorComercialDestinoID.
	 */
	public String getSetorComercialDestinoID() {
		return setorComercialDestinoID;
	}

	/**
	 * @param setorComercialDestinoID
	 *            The setorComercialDestinoID to set.
	 */
	public void setSetorComercialDestinoID(String setorComercialDestinoID) {
		this.setorComercialDestinoID = setorComercialDestinoID;
	}

	/**
	 * @return Returns the rota.
	 */
	public String getRotaInicial() {
		return rotaInicial;
	}

	/**
	 * @param rota
	 *            The rota to set.
	 */
	public void setRotaInicial(String rota) {
		this.rotaInicial = rota;
	}

	/**
	 * @return Returns the rotaFinal.
	 */
	public String getRotaFinal() {
		return rotaFinal;
	}

	/**
	 * @param rotaFinal
	 *            The rotaFinal to set.
	 */
	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	/**
	 * @return Returns the idCliente.
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente
	 *            The idCliente to set.
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return Returns the nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente
	 *            The nomeCliente to set.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Returns the clienteRelacaoTipo.
	 */
	public String getClienteRelacaoTipo() {
		return clienteRelacaoTipo;
	}

	/**
	 * @param clienteRelacaoTipo
	 *            The clienteRelacaoTipo to set.
	 */
	public void setClienteRelacaoTipo(String clienteRelacaoTipo) {
		this.clienteRelacaoTipo = clienteRelacaoTipo;
	}

	/**
	 * @return Returns the periodoFinalConta.
	 */
	public String getPeriodoFinalConta() {
		return periodoFinalConta;
	}

	/**
	 * @param periodoFinalConta
	 *            The periodoFinalConta to set.
	 */
	public void setPeriodoFinalConta(String periodoFinalConta) {
		this.periodoFinalConta = periodoFinalConta;
	}

	/**
	 * @return Returns the periodoInicialConta.
	 */
	public String getPeriodoInicialConta() {
		return periodoInicialConta;
	}

	/**
	 * @param periodoInicialConta
	 *            The periodoInicialConta to set.
	 */
	public void setPeriodoInicialConta(String periodoInicialConta) {
		this.periodoInicialConta = periodoInicialConta;
	}

	/**
	 * @return Returns the periodoVencimentoContaFinal.
	 */
	public String getPeriodoVencimentoContaFinal() {
		return periodoVencimentoContaFinal;
	}

	/**
	 * @param periodoVencimentoContaFinal
	 *            The periodoVencimentoContaFinal to set.
	 */
	public void setPeriodoVencimentoContaFinal(
			String periodoVencimentoContaFinal) {
		this.periodoVencimentoContaFinal = periodoVencimentoContaFinal;
	}

	/**
	 * @return Returns the periodoVencimentoContaInicial.
	 */
	public String getPeriodoVencimentoContaInicial() {
		return periodoVencimentoContaInicial;
	}

	/**
	 * @param periodoVencimentoContaInicial
	 *            The periodoVencimentoContaInicial to set.
	 */
	public void setPeriodoVencimentoContaInicial(
			String periodoVencimentoContaInicial) {
		this.periodoVencimentoContaInicial = periodoVencimentoContaInicial;
	}

	/**
	 * @return Returns the cobrancaAcao.
	 */
	public String getCobrancaAcao() {
		return cobrancaAcao;
	}

	/**
	 * @param cobrancaAcao
	 *            The cobrancaAcao to set.
	 */
	public void setCobrancaAcao(String cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	/**
	 * @return Returns the cobrancaAtividade.
	 */
	public String getCobrancaAtividade() {
		return cobrancaAtividade;
	}

	/**
	 * @param cobrancaAtividade
	 *            The cobrancaAtividade to set.
	 */
	public void setCobrancaAtividade(String cobrancaAtividade) {
		this.cobrancaAtividade = cobrancaAtividade;
	}

	/**
	 * @return Returns the cobrancaGrupo.
	 */
	public String getCobrancaGrupo() {
		return cobrancaGrupo;
	}

	/**
	 * @param cobrancaGrupo
	 *            The cobrancaGrupo to set.
	 */
	public void setCobrancaGrupo(String cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}

	/**
	 * @return Returns the indicador.
	 */
	public String getIndicador() {
		return indicador;
	}

	/**
	 * @param indicador
	 *            The indicador to set.
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public String getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	
	

}

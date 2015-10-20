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
package gcom.gui.arrecadacao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class FiltrarGuiaDevolucaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String atualizar;
	
	private String idImovel;
	
	private String idImovelHidden;

	private String descricaoImovel;

	private String idCliente;
	
	private String idClienteHidden;

	private String nomeCliente;

	private String clienteRelacaoTipo;
	
	private String clienteRelacaoTipoHidden;

	private String[] idTipoDebito;

	private String[] idTipoDebitoSelecionados;

	private String[] creditoTipo;

	private String dataEmissaoInicio;

	private String dataEmissaoFim;

	private String dataValidadeInicio;

	private String dataValidadeFim;

	private String periodoArrecadacaoInicio;

	private String periodoArrecadacaoFim;

	private String periodoGuiaInicio;

	private String periodoGuiaFim;

	private String[] documentoTipo;

	public String getAtualizar() {
		return atualizar;
	}

	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}

	public String getClienteRelacaoTipo() {
		return clienteRelacaoTipo;
	}

	public void setClienteRelacaoTipo(String clienteRelacaoTipo) {
		this.clienteRelacaoTipo = clienteRelacaoTipo;
	}

	public String[] getCreditoTipo() {
		return creditoTipo;
	}

	public void setCreditoTipo(String[] creditoTipo) {
		this.creditoTipo = creditoTipo;
	}

	public String getDataEmissaoFim() {
		return dataEmissaoFim;
	}

	public void setDataEmissaoFim(String dataEmissaoFim) {
		this.dataEmissaoFim = dataEmissaoFim;
	}

	public String getDataEmissaoInicio() {
		return dataEmissaoInicio;
	}

	public void setDataEmissaoInicio(String dataEmissaoInicio) {
		this.dataEmissaoInicio = dataEmissaoInicio;
	}

	public String getDataValidadeFim() {
		return dataValidadeFim;
	}

	public void setDataValidadeFim(String dataValidadeFim) {
		this.dataValidadeFim = dataValidadeFim;
	}

	public String getDataValidadeInicio() {
		return dataValidadeInicio;
	}

	public void setDataValidadeInicio(String dataValidadeInicio) {
		this.dataValidadeInicio = dataValidadeInicio;
	}

	public String getDescricaoImovel() {
		return descricaoImovel;
	}

	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}

	public String[] getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(String[] documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String[] getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(String[] idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String[] getIdTipoDebitoSelecionados() {
		return idTipoDebitoSelecionados;
	}

	public void setIdTipoDebitoSelecionados(String[] idTipoDebitoSelecionados) {
		this.idTipoDebitoSelecionados = idTipoDebitoSelecionados;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getPeriodoArrecadacaoFim() {
		return periodoArrecadacaoFim;
	}

	public void setPeriodoArrecadacaoFim(String periodoArrecadacaoFim) {
		this.periodoArrecadacaoFim = periodoArrecadacaoFim;
	}

	public String getPeriodoArrecadacaoInicio() {
		return periodoArrecadacaoInicio;
	}

	public void setPeriodoArrecadacaoInicio(String periodoArrecadacaoInicio) {
		this.periodoArrecadacaoInicio = periodoArrecadacaoInicio;
	}

	public String getPeriodoGuiaFim() {
		return periodoGuiaFim;
	}

	public void setPeriodoGuiaFim(String periodoGuiaFim) {
		this.periodoGuiaFim = periodoGuiaFim;
	}

	public String getPeriodoGuiaInicio() {
		return periodoGuiaInicio;
	}

	public void setPeriodoGuiaInicio(String periodoGuiaInicio) {
		this.periodoGuiaInicio = periodoGuiaInicio;
	}
	
//	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
//		
//	}

	public String getClienteRelacaoTipoHidden() {
		return clienteRelacaoTipoHidden;
	}

	public void setClienteRelacaoTipoHidden(String clienteRelacaoTipoHidden) {
		this.clienteRelacaoTipoHidden = clienteRelacaoTipoHidden;
	}

	public String getIdClienteHidden() {
		return idClienteHidden;
	}

	public void setIdClienteHidden(String idClienteHidden) {
		this.idClienteHidden = idClienteHidden;
	}

	public String getIdImovelHidden() {
		return idImovelHidden;
	}

	public void setIdImovelHidden(String idImovelHidden) {
		this.idImovelHidden = idImovelHidden;
	}

}

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
package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author Vivianne Sousa
 * @date 10/05/2011
 */
public class ReiterarRegistroAtendimentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String numeroRA;
	private String dataPrevista;
	private String idTipoSolicitacao;
	private String descTipoSolicitacao;
	private String idEspecificacao;
	private String descEspecificacao;
	private String idUnidadeAtual;
	private String descUnidadeAtual;
	private String nomeSolicitante;
	private String idClienteSolicitante;
	private String idUnidadeSolicitante;	
	private String pontoReferencia;
	private String observacao;
	
	private String clienteEnderecoSelected;
	private String clienteFoneSelected;
	
	public String getClienteFoneSelected() {
		return clienteFoneSelected;
	}
	public void setClienteFoneSelected(String clienteFoneSelected) {
		this.clienteFoneSelected = clienteFoneSelected;
	}
	public String getClienteEnderecoSelected() {
		return clienteEnderecoSelected;
	}
	public void setClienteEnderecoSelected(String clienteEnderecoSelected) {
		this.clienteEnderecoSelected = clienteEnderecoSelected;
	}
	
	public ReiterarRegistroAtendimentoActionForm(String numeroRA, String dataPrevista, String idTipoSolicitacao, String descTipoSolicitacao, String idEspecificacao, String descEspecificacao, String idUnidadeAtual, String descUnidadeAtual, String nomeSolicitante, String idClienteSolicitante, String idUnidadeSolicitante, String pontoReferencia, String observacao, String clienteEnderecoSelected, String clienteFoneSelected) {
		super();
		this.numeroRA = numeroRA;
		this.dataPrevista = dataPrevista;
		this.idTipoSolicitacao = idTipoSolicitacao;
		this.descTipoSolicitacao = descTipoSolicitacao;
		this.idEspecificacao = idEspecificacao;
		this.descEspecificacao = descEspecificacao;
		this.idUnidadeAtual = idUnidadeAtual;
		this.descUnidadeAtual = descUnidadeAtual;
		this.nomeSolicitante = nomeSolicitante;
		this.idClienteSolicitante = idClienteSolicitante;
		this.idUnidadeSolicitante = idUnidadeSolicitante;
		this.pontoReferencia = pontoReferencia;
		this.observacao = observacao;
		this.clienteEnderecoSelected = clienteEnderecoSelected;
		this.clienteFoneSelected = clienteFoneSelected;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public ReiterarRegistroAtendimentoActionForm() {
		super();
	}
	public String getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public String getDescEspecificacao() {
		return descEspecificacao;
	}
	public void setDescEspecificacao(String descEspecificacao) {
		this.descEspecificacao = descEspecificacao;
	}
	public String getDescTipoSolicitacao() {
		return descTipoSolicitacao;
	}
	public void setDescTipoSolicitacao(String descTipoSolicitacao) {
		this.descTipoSolicitacao = descTipoSolicitacao;
	}
	public String getDescUnidadeAtual() {
		return descUnidadeAtual;
	}
	public void setDescUnidadeAtual(String descUnidadeAtual) {
		this.descUnidadeAtual = descUnidadeAtual;
	}
	public String getIdClienteSolicitante() {
		return idClienteSolicitante;
	}
	public void setIdClienteSolicitante(String idClienteSolicitante) {
		this.idClienteSolicitante = idClienteSolicitante;
	}
	public String getIdEspecificacao() {
		return idEspecificacao;
	}
	public void setIdEspecificacao(String idEspecificacao) {
		this.idEspecificacao = idEspecificacao;
	}
	public String getIdTipoSolicitacao() {
		return idTipoSolicitacao;
	}
	public void setIdTipoSolicitacao(String idTipoSolicitacao) {
		this.idTipoSolicitacao = idTipoSolicitacao;
	}
	public String getIdUnidadeAtual() {
		return idUnidadeAtual;
	}
	public void setIdUnidadeAtual(String idUnidadeAtual) {
		this.idUnidadeAtual = idUnidadeAtual;
	}
	public String getIdUnidadeSolicitante() {
		return idUnidadeSolicitante;
	}
	public void setIdUnidadeSolicitante(String idUnidadeSolicitante) {
		this.idUnidadeSolicitante = idUnidadeSolicitante;
	}
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}
	public String getNumeroRA() {
		return numeroRA;
	}
	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}
	public String getPontoReferencia() {
		return pontoReferencia;
	}
	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}
	
	
	
	
	
}

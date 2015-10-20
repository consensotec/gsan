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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Fernanda Paiva
 */
public class PesquisarGuiaDevolucaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoCliente;
	
	private String codigoImovel;
	
	private String inscricaoImovel;
	
	private String dataEmissaoGuiaInicio;
	
	private String dataEmissaoGuiaFim;
	
	private String dataValidadeGuiaInicio;
	
	private String dataValidadeGuiaFim;
	
	private String[] situacaoGuia;
	
	private String[] tipoCredito;
	
	private String[] tipoDocumento;
	
	private String cpfCnpj;
	
	private String nomeCliente;
	
	private String inscricao;
	
	private String situacaoAgua;
	
	private String profissao;
	
	private String ramoAtividade;
	
	private String situacaoEsgoto;
	
	private String enderecoCliente;
	
	private String clienteFone;
	
	   /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	// comentado para nao interferir qdo volta para exibir a colecao do controle de paginacao
    	/*codigoCliente = null;
    	
    	codigoImovel = null;
    	
    	inscricaoImovel = null;
    	
    	dataEmissaoGuiaInicio = null;
    	
    	dataEmissaoGuiaFim = null;
    	
    	dataValidadeGuiaInicio = null;
    	
    	dataValidadeGuiaFim = null;
    	
    	situacaoGuia = null;
    	
    	tipoCredito = null;
    	
    	tipoDocumento = null;
    	
    	cpfCnpj = null;
    	
    	nomeCliente = null;
    	
    	inscricao = null;
    	
    	situacaoAgua = null;
    	
    	profissao = null;
    	
    	ramoAtividade = null;
    	
    	situacaoEsgoto = null;*/     
    }	
	
	public String getDataEmissaoGuiaFim() {
		return dataEmissaoGuiaFim;
	}
	public void setDataEmissaoGuiaFim(String dataEmissaoGuiaFim) {
		this.dataEmissaoGuiaFim = dataEmissaoGuiaFim;
	}
	public String getDataEmissaoGuiaInicio() {
		return dataEmissaoGuiaInicio;
	}
	public void setDataEmissaoGuiaInicio(String dataEmissaoGuiaInicio) {
		this.dataEmissaoGuiaInicio = dataEmissaoGuiaInicio;
	}
	public String getDataValidadeGuiaFim() {
		return dataValidadeGuiaFim;
	}
	public void setDataValidadeGuiaFim(String dataValidadeGuiaFim) {
		this.dataValidadeGuiaFim = dataValidadeGuiaFim;
	}
	public String getDataValidadeGuiaInicio() {
		return dataValidadeGuiaInicio;
	}
	public void setDataValidadeGuiaInicio(String dataValidadeGuiaInicio) {
		this.dataValidadeGuiaInicio = dataValidadeGuiaInicio;
	}
	public String[] getSituacaoGuia() {
		return situacaoGuia;
	}
	public void setSituacaoGuia(String[] situacaoGuia) {
		this.situacaoGuia = situacaoGuia;
	}
	public String[] getTipoCredito() {
		return tipoCredito;
	}
	public void setTipoCredito(String[] tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
	public String[] getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String[] tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getCodigoImovel() {
		return codigoImovel;
	}
	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getSituacaoAgua() {
		return situacaoAgua;
	}
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getRamoAtividade() {
		return ramoAtividade;
	}
	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public String getClienteFone() {
		return clienteFone;
	}

	public void setClienteFone(String clienteFone) {
		this.clienteFone = clienteFone;
	}
}

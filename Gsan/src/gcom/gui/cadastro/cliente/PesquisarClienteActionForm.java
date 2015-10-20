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
package gcom.gui.cadastro.cliente;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0000]Filtrar Cliente
 *
 * @author Roberta Costa
 * @date 12/07/2006
 */
public class PesquisarClienteActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	private String idTipoClientePesquisa;
	private String nomeClientePesquisa;
	private String cpfClientePesquisa;
	private String rgClientePesquisa;
	private String cnpjClientePesquisa;
	private String cepClientePesquisa;
	private String municipioClientePesquisa;
	private String descricaoMunicipioClientePesquisa;
	private String bairroClientePesquisa;
	private String descricaoBairroClientePesquisa;
	private String logradouroClientePesquisa;
	private String descricaoLogradouroClientePesquisa;
	private String tipoPesquisa;
	
	/**
	 * @return Retorna o campo bairroClientePesquisa.
	 */
	public String getBairroClientePesquisa() {
		return bairroClientePesquisa;
	}
	/**
	 * @param bairroClientePesquisa O bairroClientePesquisa a ser setado.
	 */
	public void setBairroClientePesquisa(String bairroClientePesquisa) {
		this.bairroClientePesquisa = bairroClientePesquisa;
	}
	/**
	 * @return Retorna o campo cepClientePesquisa.
	 */
	public String getCepClientePesquisa() {
		return cepClientePesquisa;
	}
	/**
	 * @param cepClientePesquisa O cepClientePesquisa a ser setado.
	 */
	public void setCepClientePesquisa(String cepClientePesquisa) {
		this.cepClientePesquisa = cepClientePesquisa;
	}
	/**
	 * @return Retorna o campo cnpjClientePesquisa.
	 */
	public String getCnpjClientePesquisa() {
		return cnpjClientePesquisa;
	}
	/**
	 * @param cnpjClientePesquisa O cnpjClientePesquisa a ser setado.
	 */
	public void setCnpjClientePesquisa(String cnpjClientePesquisa) {
		this.cnpjClientePesquisa = cnpjClientePesquisa;
	}
	/**
	 * @return Retorna o campo cpfClientePesquisa.
	 */
	public String getCpfClientePesquisa() {
		return cpfClientePesquisa;
	}
	/**
	 * @param cpfClientePesquisa O cpfClientePesquisa a ser setado.
	 */
	public void setCpfClientePesquisa(String cpfClientePesquisa) {
		this.cpfClientePesquisa = cpfClientePesquisa;
	}
	/**
	 * @return Retorna o campo descricaoBairroClientePesquisa.
	 */
	public String getDescricaoBairroClientePesquisa() {
		return descricaoBairroClientePesquisa;
	}
	/**
	 * @param descricaoBairroClientePesquisa O descricaoBairroClientePesquisa a ser setado.
	 */
	public void setDescricaoBairroClientePesquisa(
			String descricaoBairroClientePesquisa) {
		this.descricaoBairroClientePesquisa = descricaoBairroClientePesquisa;
	}
	/**
	 * @return Retorna o campo descricaoLogradouroClientePesquisa.
	 */
	public String getDescricaoLogradouroClientePesquisa() {
		return descricaoLogradouroClientePesquisa;
	}
	/**
	 * @param descricaoLogradouroClientePesquisa O descricaoLogradouroClientePesquisa a ser setado.
	 */
	public void setDescricaoLogradouroClientePesquisa(
			String descricaoLogradouroClientePesquisa) {
		this.descricaoLogradouroClientePesquisa = descricaoLogradouroClientePesquisa;
	}
	/**
	 * @return Retorna o campo descricaoMunicipioClientePesquisa.
	 */
	public String getDescricaoMunicipioClientePesquisa() {
		return descricaoMunicipioClientePesquisa;
	}
	/**
	 * @param descricaoMunicipioClientePesquisa O descricaoMunicipioClientePesquisa a ser setado.
	 */
	public void setDescricaoMunicipioClientePesquisa(
			String descricaoMunicipioClientePesquisa) {
		this.descricaoMunicipioClientePesquisa = descricaoMunicipioClientePesquisa;
	}
	/**
	 * @return Retorna o campo idTipoCliente.
	 */
	public String getIdTipoClientePesquisa() {
		return idTipoClientePesquisa;
	}
	/**
	 * @param idTipoCliente O idTipoCliente a ser setado.
	 */
	public void setIdTipoClientePesquisa(String idTipoClientePesquisa) {
		this.idTipoClientePesquisa = idTipoClientePesquisa;
	}
	/**
	 * @return Retorna o campo logradouroClientePesquisa.
	 */
	public String getLogradouroClientePesquisa() {
		return logradouroClientePesquisa;
	}
	/**
	 * @param logradouroClientePesquisa O logradouroClientePesquisa a ser setado.
	 */
	public void setLogradouroClientePesquisa(String logradouroClientePesquisa) {
		this.logradouroClientePesquisa = logradouroClientePesquisa;
	}
	/**
	 * @return Retorna o campo municipioClientePesquisa.
	 */
	public String getMunicipioClientePesquisa() {
		return municipioClientePesquisa;
	}
	/**
	 * @param municipioClientePesquisa O municipioClientePesquisa a ser setado.
	 */
	public void setMunicipioClientePesquisa(String municipioClientePesquisa) {
		this.municipioClientePesquisa = municipioClientePesquisa;
	}
	/**
	 * @return Retorna o campo nomeClientePesquisa.
	 */
	public String getNomeClientePesquisa() {
		return nomeClientePesquisa;
	}
	/**
	 * @param nomeClientePesquisa O nomeClientePesquisa a ser setado.
	 */
	public void setNomeClientePesquisa(String nomeClientePesquisa) {
		this.nomeClientePesquisa = nomeClientePesquisa;
	}
	/**
	 * @return Retorna o campo rgClientePesquisa.
	 */
	public String getRgClientePesquisa() {
		return rgClientePesquisa;
	}
	/**
	 * @param rgClientePesquisa O rgClientePesquisa a ser setado.
	 */
	public void setRgClientePesquisa(String rgClientePesquisa) {
		this.rgClientePesquisa = rgClientePesquisa;
	}
	/**
	 * @return Retorna o campo tipoPesquisa.
	 */
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	/**
	 * @param tipoPesquisa O tipoPesquisa a ser setado.
	 */
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
}

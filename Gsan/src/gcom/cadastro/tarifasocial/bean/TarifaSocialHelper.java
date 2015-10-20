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
package gcom.cadastro.tarifasocial.bean;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
public class TarifaSocialHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public TarifaSocialHelper(){}
	
	private ClienteImovel clienteImovel;
	
	private ClienteImovelEconomia clienteImovelEconomia;
	
	private TarifaSocialDadoEconomia tarifaSocialDadoEconomia;
	
	private Collection colecaoClientesInseridos;
	
	private Collection colecaoClientesEconomiaInseridos;
	
	private Collection colecaoClientesRemovidos;
	
	private Collection colecaoClientesEconomiaRemovidos;
	
	private Collection colecaoClientesImoveis;
	
	private Collection colecaoClientesImoveisEconomias;
	
	private Date dataComparecimentoCarta;

	/**
	 * @return Retorna o campo clienteImovel.
	 */
	public ClienteImovel getClienteImovel() {
		return clienteImovel;
	}

	/**
	 * @param clienteImovel O clienteImovel a ser setado.
	 */
	public void setClienteImovel(ClienteImovel clienteImovel) {
		this.clienteImovel = clienteImovel;
	}

	/**
	 * @return Retorna o campo tarifaSocialDadoEconomia.
	 */
	public TarifaSocialDadoEconomia getTarifaSocialDadoEconomia() {
		return tarifaSocialDadoEconomia;
	}

	/**
	 * @param tarifaSocialDadoEconomia O tarifaSocialDadoEconomia a ser setado.
	 */
	public void setTarifaSocialDadoEconomia(
			TarifaSocialDadoEconomia tarifaSocialDadoEconomia) {
		this.tarifaSocialDadoEconomia = tarifaSocialDadoEconomia;
	}

	/**
	 * @return Retorna o campo colecaoClientesInseridos.
	 */
	public Collection getColecaoClientesInseridos() {
		return colecaoClientesInseridos;
	}

	/**
	 * @param colecaoClientesInseridos O colecaoClientesInseridos a ser setado.
	 */
	public void setColecaoClientesInseridos(Collection colecaoClientesInseridos) {
		this.colecaoClientesInseridos = colecaoClientesInseridos;
	}

	/**
	 * @return Retorna o campo colecaoClientesRemovidos.
	 */
	public Collection getColecaoClientesRemovidos() {
		return colecaoClientesRemovidos;
	}

	/**
	 * @param colecaoClientesRemovidos O colecaoClientesRemovidos a ser setado.
	 */
	public void setColecaoClientesRemovidos(Collection colecaoClientesRemovidos) {
		this.colecaoClientesRemovidos = colecaoClientesRemovidos;
	}

	/**
	 * @return Retorna o campo clienteImovelEconomia.
	 */
	public ClienteImovelEconomia getClienteImovelEconomia() {
		return clienteImovelEconomia;
	}

	/**
	 * @param clienteImovelEconomia O clienteImovelEconomia a ser setado.
	 */
	public void setClienteImovelEconomia(ClienteImovelEconomia clienteImovelEconomia) {
		this.clienteImovelEconomia = clienteImovelEconomia;
	}

	/**
	 * @return Retorna o campo colecaoClientesImoveis.
	 */
	public Collection getColecaoClientesImoveis() {
		return colecaoClientesImoveis;
	}

	/**
	 * @param colecaoClientesImoveis O colecaoClientesImoveis a ser setado.
	 */
	public void setColecaoClientesImoveis(Collection colecaoClientesImoveis) {
		this.colecaoClientesImoveis = colecaoClientesImoveis;
	}

	/**
	 * @return Retorna o campo colecaoClientesImoveisEconomias.
	 */
	public Collection getColecaoClientesImoveisEconomias() {
		return colecaoClientesImoveisEconomias;
	}

	/**
	 * @param colecaoClientesImoveisEconomias O colecaoClientesImoveisEconomias a ser setado.
	 */
	public void setColecaoClientesImoveisEconomias(
			Collection colecaoClientesImoveisEconomias) {
		this.colecaoClientesImoveisEconomias = colecaoClientesImoveisEconomias;
	}

	/**
	 * @return Retorna o campo colecaoClientesEconomiaInseridos.
	 */
	public Collection getColecaoClientesEconomiaInseridos() {
		return colecaoClientesEconomiaInseridos;
	}

	/**
	 * @param colecaoClientesEconomiaInseridos O colecaoClientesEconomiaInseridos a ser setado.
	 */
	public void setColecaoClientesEconomiaInseridos(
			Collection colecaoClientesEconomiaInseridos) {
		this.colecaoClientesEconomiaInseridos = colecaoClientesEconomiaInseridos;
	}

	/**
	 * @return Retorna o campo colecaoClientesEconomiaRemovidos.
	 */
	public Collection getColecaoClientesEconomiaRemovidos() {
		return colecaoClientesEconomiaRemovidos;
	}

	/**
	 * @param colecaoClientesEconomiaRemovidos O colecaoClientesEconomiaRemovidos a ser setado.
	 */
	public void setColecaoClientesEconomiaRemovidos(
			Collection colecaoClientesEconomiaRemovidos) {
		this.colecaoClientesEconomiaRemovidos = colecaoClientesEconomiaRemovidos;
	}

	public Date getDataComparecimentoCarta() {
		return dataComparecimentoCarta;
	}

	public void setDataComparecimentoCarta(Date dataComparecimentoCarta) {
		this.dataComparecimentoCarta = dataComparecimentoCarta;
	}
	
	
}

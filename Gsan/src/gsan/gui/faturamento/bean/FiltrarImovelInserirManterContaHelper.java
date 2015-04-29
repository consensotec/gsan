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
package gsan.gui.faturamento.bean;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Classe que ir� auxiliar no formato de entrada da pesquisa 
 * de registro de atendimento
 *
 * @author Rafael Pinto
 * @date 30/04/2007
 */
public class FiltrarImovelInserirManterContaHelper {
	
	private Integer localidadeOrigemID = null;
	private Integer setorComercialOrigemID = null;
	private Integer quadraOrigemID = null; 
	private Short loteOrigem = null;
	private Short subLoteOrigem = null;
	private Integer localidadeDestinoID = null;
	private Integer setorComercialDestinoID = null;
	private Integer quadraDestinoID = null;	
	private Short loteDestino = null;
	private Short subLoteDestino = null; 
	private String[] quadras = null;
	
	private Integer codigoRotaOrigem = null;
	private Integer codigoRotaDestino = null;
	private Integer sequencialRotaOrigem = null;
	private Integer sequencialRotaDestino = null;
	
	private Collection colecaoQuadraSelecionada = null;
	
	private Integer idGrupoFaturamento;
	
	private Boolean verificarImovelPerfilBloqueado = false; 
	
	/**
	 * @return Retorna o campo verificarImovelPerfilBloqueado.
	 */
	public Boolean getVerificarImovelPerfilBloqueado() {
		return verificarImovelPerfilBloqueado;
	}

	/**
	 * @param verificarImovelPerfilBloqueado O verificarImovelPerfilBloqueado a ser setado.
	 */
	public void setVerificarImovelPerfilBloqueado(
			Boolean verificarImovelPerfilBloqueado) {
		this.verificarImovelPerfilBloqueado = verificarImovelPerfilBloqueado;
	}

	public Collection getColecaoQuadraSelecionada() {
		
		if(this.quadras != null && !this.quadras.equals("")){
			
			colecaoQuadraSelecionada = new ArrayList();
			
			for (int i = 0; i < this.quadras.length; i++) {

				int idQuadra = Integer.parseInt(this.quadras[i]);
				
				colecaoQuadraSelecionada.add(idQuadra);
			}
		}		
		
		return colecaoQuadraSelecionada;	
	}

	public void setColecaoQuadraSelecionada(Collection colecaoQuadraSelecionada) {
		this.colecaoQuadraSelecionada = colecaoQuadraSelecionada;
	}

	public FiltrarImovelInserirManterContaHelper() { }
	
	public Integer getLocalidadeDestinoID() {
		return localidadeDestinoID;
	}

	public void setLocalidadeDestinoID(Integer localidadeDestinoID) {
		this.localidadeDestinoID = localidadeDestinoID;
	}

	public Integer getLocalidadeOrigemID() {
		return localidadeOrigemID;
	}

	public void setLocalidadeOrigemID(Integer localidadeOrigemID) {
		this.localidadeOrigemID = localidadeOrigemID;
	}

	public Short getLoteDestino() {
		return loteDestino;
	}

	public void setLoteDestino(Short loteDestino) {
		this.loteDestino = loteDestino;
	}

	public Short getLoteOrigem() {
		return loteOrigem;
	}

	public void setLoteOrigem(Short loteOrigem) {
		this.loteOrigem = loteOrigem;
	}

	public Integer getQuadraDestinoID() {
		return quadraDestinoID;
	}

	public void setQuadraDestinoID(Integer quadraDestinoID) {
		this.quadraDestinoID = quadraDestinoID;
	}

	public Integer getQuadraOrigemID() {
		return quadraOrigemID;
	}

	public void setQuadraOrigemID(Integer quadraOrigemID) {
		this.quadraOrigemID = quadraOrigemID;
	}

	public String[] getQuadras() {
		return quadras;
	}

	public void setQuadras(String[] quadras) {
		this.quadras = quadras;
	}

	public Integer getSetorComercialDestinoID() {
		return setorComercialDestinoID;
	}

	public void setSetorComercialDestinoID(Integer setorComercialDestinoID) {
		this.setorComercialDestinoID = setorComercialDestinoID;
	}

	public Integer getSetorComercialOrigemID() {
		return setorComercialOrigemID;
	}

	public void setSetorComercialOrigemID(Integer setorComercialOrigemID) {
		this.setorComercialOrigemID = setorComercialOrigemID;
	}

	public Short getSubLoteDestino() {
		return subLoteDestino;
	}

	public void setSubLoteDestino(Short subLoteDestino) {
		this.subLoteDestino = subLoteDestino;
	}

	public Short getSubLoteOrigem() {
		return subLoteOrigem;
	}

	public void setSubLoteOrigem(Short subLoteOrigem) {
		this.subLoteOrigem = subLoteOrigem;
	}

	public Integer getCodigoRotaDestino() {
		return codigoRotaDestino;
	}

	public void setCodigoRotaDestino(Integer codigoRotaDestino) {
		this.codigoRotaDestino = codigoRotaDestino;
	}

	public Integer getCodigoRotaOrigem() {
		return codigoRotaOrigem;
	}

	public void setCodigoRotaOrigem(Integer codigoRotaOrigem) {
		this.codigoRotaOrigem = codigoRotaOrigem;
	}

	public Integer getSequencialRotaDestino() {
		return sequencialRotaDestino;
	}

	public void setSequencialRotaDestino(Integer sequencialRotaDestino) {
		this.sequencialRotaDestino = sequencialRotaDestino;
	}

	public Integer getSequencialRotaOrigem() {
		return sequencialRotaOrigem;
	}

	public void setSequencialRotaOrigem(Integer sequencialRotaOrigem) {
		this.sequencialRotaOrigem = sequencialRotaOrigem;
	}

	public Integer getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(Integer idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}
	
	

}
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
package gcom.cadastro.imovel.bean;

import gcom.cadastro.imovel.ImovelSubcategoria;

import java.io.Serializable;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
public class ImovelSubcategoriaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public ImovelSubcategoriaHelper(){}
	
	private String subcategoria;
	
	private String categoria;
	
	private short quantidadeEconomias;
	
	private Collection colecaoImovelEconomia;

	private JRBeanCollectionDataSource colecaoClienteImovelEconomiaHelper;

	public Collection getColecaoImovelEconomia() {
		return colecaoImovelEconomia;
	}

	private ImovelSubcategoria imovelSubcategoria;
	
	public void setColecaoImovelEconomia(Collection colecaoImovelEconomia) {
		this.colecaoImovelEconomia = colecaoImovelEconomia;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public short getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(short quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	public JRBeanCollectionDataSource getColecaoClienteImovelEconomiaHelper() {
		return colecaoClienteImovelEconomiaHelper;
	}

	public void setColecaoClienteImovelEconomiaHelper(
			JRBeanCollectionDataSource colecaoImovelEconomiaHelper) {
		this.colecaoClienteImovelEconomiaHelper = colecaoImovelEconomiaHelper;
	}

	public ImovelSubcategoria getImovelSubcategoria() {
		return imovelSubcategoria;
	}

	public void setImovelSubcategoria(ImovelSubcategoria imovelSubcategoria) {
		this.imovelSubcategoria = imovelSubcategoria;
	}

	public String getCategoriaAPartirImovelSubcategoria() {
		if(this.imovelSubcategoria!=null && this.imovelSubcategoria.getComp_id()!=null){

			if(this.imovelSubcategoria.getComp_id().getSubcategoria()!=null
					&& this.imovelSubcategoria.getComp_id().getSubcategoria().getCategoria()!=null){
				
				return this.imovelSubcategoria.getComp_id().getSubcategoria().getCategoria().getDescricao();
			}
		}
		return "";
	}

	public String getSubcategoriaAPartirImovelSubcategoria() {
		if(this.imovelSubcategoria!=null && this.imovelSubcategoria.getComp_id()!=null){

			if(this.imovelSubcategoria.getComp_id().getSubcategoria()!=null){
				
				return this.imovelSubcategoria.getComp_id().getSubcategoria().getDescricao();
			}
		}
		return "";
	}

	public short getQuantidadeEconomiasAPartirImovelSubcategoria() {
		if(this.imovelSubcategoria!=null ){
			
			return this.imovelSubcategoria.getQuantidadeEconomias();
		}
		
		return 0;
	}

	
}

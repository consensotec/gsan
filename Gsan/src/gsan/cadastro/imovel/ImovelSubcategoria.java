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
package gsan.cadastro.imovel;

import gsan.interceptor.ControleAlteracao;
import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 * @created 2 de Junho de 2004
 */
@ControleAlteracao()
public class ImovelSubcategoria extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	/**
	 * identifier field
	 */
	private gsan.cadastro.imovel.ImovelSubcategoriaPK comp_id;

	/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={Imovel.ATRIBUTOS_IMOVEL_INSERIR, Imovel.ATRIBUTOS_IMOVEL_ATUALIZAR,Imovel.ATRIBUTOS_IMOVEL_REMOVER,Imovel.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private short quantidadeEconomias;

	/**
	 * nullable persistent field
	 */
	private Short quantidadeUnidadesPrivativas;
	
	private Short quantidadeUnidadesColetivas;
	
	private Date ultimaAlteracao;

	private Set imovelEconomias;

	/**
	 * Construtor de ImovelSubcategoria 
	 * 
	 * @param comp_id
	 * @param quantidadeEconomias
	 * @param ultimaAlteracao
	 */
	public ImovelSubcategoria(
			gsan.cadastro.imovel.ImovelSubcategoriaPK comp_id,
			short quantidadeEconomias, Date ultimaAlteracao) {
		this.comp_id = comp_id;
		this.quantidadeEconomias = quantidadeEconomias;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * default constructor
	 */
	public ImovelSubcategoria() {
	}

	/**
	 * Construtor de ImovelSubcategoria 
	 * 
	 * @param comp_id
	 * @param quantidadeEconomias
	 */
	public ImovelSubcategoria(
			gsan.cadastro.imovel.ImovelSubcategoriaPK comp_id,
			short quantidadeEconomias) {
		this.comp_id = comp_id;
		this.quantidadeEconomias = quantidadeEconomias;
	}
	
	public ImovelSubcategoria(
			gsan.cadastro.imovel.ImovelSubcategoriaPK comp_id,
			short quantidadeEconomias, Short quantidadeUnidadesPrivativas, Short quantidadeUnidadesColetivas,
			Date ultimaAlteracao) {
		this.comp_id = comp_id;
		this.quantidadeEconomias = quantidadeEconomias;
		this.quantidadeUnidadesPrivativas = quantidadeUnidadesPrivativas;
		this.quantidadeUnidadesColetivas = quantidadeUnidadesColetivas;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * Gets the comp_id attribute of the ImovelSubcategoria object
	 * 
	 * @return The comp_id value
	 */
	public gsan.cadastro.imovel.ImovelSubcategoriaPK getComp_id() {
		return this.comp_id;
	}

	/**
	 * Sets the comp_id attribute of the ImovelSubcategoria object
	 * 
	 * @param comp_id
	 *            The new comp_id value
	 */
	public void setComp_id(gsan.cadastro.imovel.ImovelSubcategoriaPK comp_id) {
		this.comp_id = comp_id;
	}

	/**
	 * Gets the quantidadeEconomias attribute of the ImovelSubcategoria object
	 * 
	 * @return The quantidadeEconomias value
	 */
	public short getQuantidadeEconomias() {
		return this.quantidadeEconomias;
	}

	/**
	 * Sets the quantidadeEconomias attribute of the ImovelSubcategoria object
	 * 
	 * @param quantidadeEconomias
	 *            The new quantidadeEconomias value
	 */
	public void setQuantidadeEconomias(short quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	/**
	 * Gets the ultimaAlteracao attribute of the ImovelSubcategoria object
	 * 
	 * @return The ultimaAlteracao value
	 */
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	/**
	 * Sets the ultimaAlteracao attribute of the ImovelSubcategoria object
	 * 
	 * @param ultimaAlteracao
	 *            The new ultimaAlteracao value
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * Description of the Method
	 * 
	 * @return Description of the Return Value
	 */
	public String toString() {
		return new ToStringBuilder(this).append("comp_id", getComp_id())
				.toString();
	}

	/**
	 * Description of the Method
	 * 
	 * @param other
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof ImovelSubcategoria)) {
			return false;
		}
		ImovelSubcategoria castOther = (ImovelSubcategoria) other;

		// return
		// ((this.getComp_id().getSubcategoria().getId().equals(castOther.getComp_id().getSubcategoria().getId()))
		// &&
		// (this.getComp_id().getSubcategoria().getCategoria().getId().equals(castOther.getComp_id().getSubcategoria().getCategoria().getId())));
		return new EqualsBuilder().append(this.getComp_id(),
				castOther.getComp_id()).isEquals();
	}

	/**
	 * Description of the Method
	 * 
	 * @return Description of the Return Value
	 */
	public int hashCode() {
		return this.ultimaAlteracao.hashCode();
	}

	/**
	 * Gets the imovelEconomias attribute of the ImovelSubcategoria object
	 * 
	 * @return The imovelEconomias value
	 */
	public Set getImovelEconomias() {
		return imovelEconomias;
	}

	/**
	 * Sets the imovelEconomias attribute of the ImovelSubcategoria object
	 * 
	 * @param imovelEconomias
	 *            The new imovelEconomias value
	 */
	public void setImovelEconomias(Set imovelEconomias) {
		this.imovelEconomias = imovelEconomias;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = {"comp_id"};
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		Filtro filtro = new FiltroImovelSubCategoria();
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelSubCategoria.SUBCATEGORIA_CATEGORIA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelSubCategoria.SUBCATEGORIA);
		filtro.adicionarParametro(
				new ParametroSimples(FiltroImovelSubCategoria.IMOVEL_ID, this.getComp_id().getImovel().getId()));
		filtro.adicionarParametro(
				new ParametroSimples(FiltroImovelSubCategoria.SUBCATEGORIA_ID, 
						this.getComp_id().getSubcategoria().getId()));
		return filtro; 
	}
	
	public void initializeLazy(){
		if (this.getComp_id() != null){
			if(getComp_id().getSubcategoria() != null){
				getComp_id().getSubcategoria().initializeLazy();
			}
			getDescricaoParaRegistroTransacao();
		}		
	}
	
	public String getDescricaoParaRegistroTransacao(){
		if (this.getComp_id() != null && this.getComp_id().getSubcategoria() != null 
				&& this.getComp_id().getSubcategoria().getCategoria() != null){
			return this.getComp_id().getSubcategoria().getCategoria().getDescricao() + " / " 
			    + this.getComp_id().getSubcategoria().getDescricao();	
		} else {
			return "";
		}
		 	
	}

	public Short getQuantidadeUnidadesColetivas() {
		return quantidadeUnidadesColetivas;
	}

	public void setQuantidadeUnidadesColetivas(Short quantidadeUnidadesColetivas) {
		this.quantidadeUnidadesColetivas = quantidadeUnidadesColetivas;
	}

	public Short getQuantidadeUnidadesPrivativas() {
		return quantidadeUnidadesPrivativas;
	}

	public void setQuantidadeUnidadesPrivativas(Short quantidadeUnidadesPrivativas) {
		this.quantidadeUnidadesPrivativas = quantidadeUnidadesPrivativas;
	}
	
}

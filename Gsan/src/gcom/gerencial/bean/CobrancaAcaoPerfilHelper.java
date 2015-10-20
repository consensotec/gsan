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
package gcom.gerencial.bean;

import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class CobrancaAcaoPerfilHelper implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descricao;
	private Integer quantidadeDocumento;
	private BigDecimal valorDocumento;
	private Collection colecaoCobrancaAcaoPerfilIndicador;
	
	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return Retorna o campo somatorioQuantidade.
	 */


	public CobrancaAcaoPerfilHelper(Integer id, String descricao,
			Integer quantidadeDocumento, BigDecimal valorDocumento) {
		this.id = id;
		this.descricao = descricao;
		this.quantidadeDocumento = quantidadeDocumento;
		this.valorDocumento = valorDocumento;
		
	}

	public String getPercentualValor(String valorTotal) {
		String valorPercentual = Util.calcularPercentual(getValorDocumento().toString() , valorTotal);
		valorPercentual = Util.formatarMoedaReal(new BigDecimal(valorPercentual));
		return valorPercentual;
	}
	
	public String getPercentualQuantidade(String quantidadeTotal) {
		String quantidadePercentual = Util.calcularPercentual(getQuantidadeDocumento().toString(), quantidadeTotal);
		quantidadePercentual = Util.formatarMoedaReal(new BigDecimal(quantidadePercentual));
		return quantidadePercentual;
	}
	/**
	 * @return Retorna o campo quantidadeDocumento.
	 */
	public Integer getQuantidadeDocumento() {
		return quantidadeDocumento;
	}
	/**
	 * @param quantidadeDocumento O quantidadeDocumento a ser setado.
	 */
	public void setQuantidadeDocumento(Integer quantidadeDocumento) {
		this.quantidadeDocumento = quantidadeDocumento;
	}
	/**
	 * @return Retorna o campo valorDocumento.
	 */
	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}
	/**
	 * @param valorDocumento O valorDocumento a ser setado.
	 */
	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}
	
	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return Retorna o campo colecaoCobrancaAcaoPerfilIndicador.
	 */
	public Collection getColecaoCobrancaAcaoPerfilIndicador() {
		return colecaoCobrancaAcaoPerfilIndicador;
	}
	/**
	 * @param colecaoCobrancaAcaoPerfilIndicador O colecaoCobrancaAcaoPerfilIndicador a ser setado.
	 */
	public void setColecaoCobrancaAcaoPerfilIndicador(
			Collection colecaoCobrancaAcaoPerfilIndicador) {
		this.colecaoCobrancaAcaoPerfilIndicador = colecaoCobrancaAcaoPerfilIndicador;
	}
}

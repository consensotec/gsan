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
package gcom.arrecadacao.aviso.bean;

import gcom.util.Util;

import java.math.BigDecimal;



public class MovimentarPagamentosDevolucoesHelper {
	
	private Integer id;
	private String tipoDocumento;
	private String mesAnoReferencia;
	private String valor;
	private String data;
	private String tipoDebito;
	private BigDecimal valorTotal;
	
	public MovimentarPagamentosDevolucoesHelper() {
	}
	/**
	 * @return Retorna o campo data.
	 */

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}
	/**
	 * @param mesAnoReferencia O mesAnoReferencia a ser setado.
	 */
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}
	/**
	 * @return Retorna o campo tipoDebito.
	 */
	public String getTipoDebito() {
		return tipoDebito;
	}
	/**
	 * @param tipoDebito O tipoDebito a ser setado.
	 */
	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}
	/**
	 * @return Retorna o campo tipoDocumento.
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento O tipoDocumento a ser setado.
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return Retorna o campo data.
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data O data a ser setado.
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return Retorna o campo valor.
	 */
	public String getValor() {
		return valor;
	}
	
	/**
	 * @param valor O valor a ser setado.
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	/**
	 * @return Retorna o campo idAvisoBancario.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param idAvisoBancario O idAvisoBancario a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	} 	
	
	/**
	 * @return Retorna o campo valorTotal.
	 */
	public String getValorTotal() {
		return Util.formatarMoedaReal(valorTotal);
	}
	/**
	 * @param valorTotal O valorTotal a ser setado.
	 */
	public void setValorTotal(BigDecimal valor) {
		if(valorTotal == null || valorTotal.equals("")){
			valorTotal = new BigDecimal("0.00");	
		}
		this.valorTotal = valorTotal.add(valor);
	}
	
	public boolean equals(Object obj) {

		if (!(obj instanceof MovimentarPagamentosDevolucoesHelper)) {
			return false;
		} else {
			MovimentarPagamentosDevolucoesHelper resumoTemp = (MovimentarPagamentosDevolucoesHelper) obj;

			// Verificamos se todas as propriedades que identificam o objeto sao
			// iguais
			return propriedadesIguais(this.id,resumoTemp.id);

		}
	}
	
	/**
	 * Compara duas properiedades inteiras, comparando seus valores para
	 * descobrirmos se sao iguais
	 * 
	 * @param pro1
	 *            Primeira propriedade
	 * @param pro2
	 *            Segunda propriedade
	 * @return Se iguais, retorna true
	 */
	private boolean propriedadesIguais(Integer pro1, Integer pro2) {
		if (pro2 != null) {
			if (!pro2.equals(pro1)) {
				return false;
			}
		} else if (pro1 != null) {
			return false;
		}

		// Se chegou ate aqui quer dizer que as propriedades sao iguais
		return true;
	}
}

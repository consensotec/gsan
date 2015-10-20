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
package gcom.arrecadacao.bean;

import java.math.BigDecimal;
import java.util.Date;

import gcom.relatorio.RelatorioBean;


/**
 * Descri��o da classe 
 *
 * @author Ana Maria
 * @date 12/07/2007
 */
public class MovimentoArrecadadoresPorNSAHelper implements RelatorioBean {

	private String banco;

	private String formaArrecadacao;

	private Integer nsa;

	private Date dataGeracao;

	private Integer qtdeRegistros;
	
	private Long valor;
	
	private BigDecimal tarifa;
	

	public MovimentoArrecadadoresPorNSAHelper() {
	}

	/**
	 * Construtor de RelatorioAcompanhamentoMovimentoArrecadadoresPorNSABean 
	 * 
	 * @param banco
	 * @param formaArrecadacao
	 * @param nsa
	 * @param dataGeracao
	 * @param qtdeRegistros
	 * @param valor
	 * @param tarifa
	 * @param valorAPagar
	 */
	public MovimentoArrecadadoresPorNSAHelper(String banco, String formaArrecadacao, Integer nsa,
			Date dataGeracao, Integer qtdeRegistros, Long valor, BigDecimal tarifa) {
		this.banco = banco;
		this.formaArrecadacao = formaArrecadacao;
		this.nsa = nsa;
		this.dataGeracao = dataGeracao;
		this.qtdeRegistros = qtdeRegistros;
		this.valor = valor;
		this.tarifa = tarifa;
	}

	/**
	 * @return Retorna o campo banco.
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * @param banco O banco a ser setado.
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * @return Retorna o campo formaArrecadacao.
	 */
	public String getFormaArrecadacao() {
		return formaArrecadacao;
	}

	/**
	 * @param formaArrecadacao O formaArrecadacao a ser setado.
	 */
	public void setFormaArrecadacao(String formaArrecadacao) {
		this.formaArrecadacao = formaArrecadacao;
	}

	/**
	 * @return Retorna o campo dataGeracao.
	 */
	public Date getDataGeracao() {
		return dataGeracao;
	}

	/**
	 * @param dataGeracao O dataGeracao a ser setado.
	 */
	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	/**
	 * @return Retorna o campo nsa.
	 */
	public Integer getNsa() {
		return nsa;
	}

	/**
	 * @param nsa O nsa a ser setado.
	 */
	public void setNsa(Integer nsa) {
		this.nsa = nsa;
	}

	/**
	 * @return Retorna o campo qtdeRegistros.
	 */
	public Integer getQtdeRegistros() {
		return qtdeRegistros;
	}

	/**
	 * @param qtdeRegistros O qtdeRegistros a ser setado.
	 */
	public void setQtdeRegistros(Integer qtdeRegistros) {
		this.qtdeRegistros = qtdeRegistros;
	}

	/**
	 * @return Retorna o campo tarifa.
	 */
	public BigDecimal getTarifa() {
		return tarifa;
	}

	/**
	 * @param tarifa O tarifa a ser setado.
	 */
	public void setTarifa(BigDecimal tarifa) {
		this.tarifa = tarifa;
	}

	/**
	 * @return Retorna o campo valor.
	 */
	public Long getValor() {
		return valor;
	}

	/**
	 * @param valor O valor a ser setado.
	 */
	public void setValor(Long valor) {
		this.valor = valor;
	}



	
}

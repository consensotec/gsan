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
package gcom.gui.faturamento.debito;
import org.apache.struts.validator.ValidatorActionForm;
/**
 * Descri��o da classe 
 *
 * @author R�muloAur�lio
 * @date 09/03/2007
 */
public class InserirTipoDebitoActionForm  extends ValidatorActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descricao;
	private String descricaoAbreviada;
	private String lancamentoItemContabil;
	private String financiamentoTipo;
	private String indicadorGeracaoDebitoAutomatica;
	private String indicadorGeracaoDebitoConta;
	private String valorLimiteDebito;
	private String valorSugerido;		private String indicadorDebitoCartaoCredito;		private String indicadorJurosParCliente;	public String getIndicadorDebitoCartaoCredito() {		return indicadorDebitoCartaoCredito;	}	public void setIndicadorDebitoCartaoCredito(String indicadorDebitoCartaoCredito) {		this.indicadorDebitoCartaoCredito = indicadorDebitoCartaoCredito;	}	/**
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
	 * @return Retorna o campo descricaoAbreviada.
	 */
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}
	/**
	 * @param descricaoAbreviada O descricaoAbreviada a ser setado.
	 */
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}
	/**
	 * @return Retorna o campo financiamentoTipo.
	 */
	public String getFinanciamentoTipo() {
		return financiamentoTipo;
	}
	/**
	 * @param financiamentoTipo O financiamentoTipo a ser setado.
	 */
	public void setFinanciamentoTipo(String financiamentoTipo) {
		this.financiamentoTipo = financiamentoTipo;
	}
	/**
	 * @return Retorna o campo indicadorGeracaoDebitoAutomatica.
	 */
	public String getIndicadorGeracaoDebitoAutomatica() {
		return indicadorGeracaoDebitoAutomatica;
	}
	/**
	 * @param indicadorGeracaoDebitoAutomatica O indicadorGeracaoDebitoAutomatica a ser setado.
	 */
	public void setIndicadorGeracaoDebitoAutomatica(
			String indicadorGeracaoDebitoAutomatica) {
		this.indicadorGeracaoDebitoAutomatica = indicadorGeracaoDebitoAutomatica;
	}
	/**
	 * @return Retorna o campo indicadorGeracaoDebitoConta.
	 */
	public String getIndicadorGeracaoDebitoConta() {
		return indicadorGeracaoDebitoConta;
	}
	/**
	 * @param indicadorGeracaoDebitoConta O indicadorGeracaoDebitoConta a ser setado.
	 */
	public void setIndicadorGeracaoDebitoConta(String indicadorGeracaoDebitoConta) {
		this.indicadorGeracaoDebitoConta = indicadorGeracaoDebitoConta;
	}
	/**
	 * @return Retorna o campo lancamentoItemContabil.
	 */
	public String getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}
	/**
	 * @param lancamentoItemContabil O lancamentoItemContabil a ser setado.
	 */
	public void setLancamentoItemContabil(String lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}
	/**
	 * @return Retorna o campo valorLimiteDebito.
	 */
	public String getValorLimiteDebito() {
		return valorLimiteDebito;
	}
	/**
	 * @param valorLimiteDebito O valorLimiteDebito a ser setado.
	 */
	public void setValorLimiteDebito(String valorLimiteDebito) {
		this.valorLimiteDebito = valorLimiteDebito;
	}
	/**
	 * @return Retorna o campo valorSugerido.
	 */
	public String getValorSugerido() {
		return valorSugerido;
	}
	/**
	 * @param valorSugerido O valorSugerido a ser setado.
	 */
	public void setValorSugerido(String valorSugerido) {
		this.valorSugerido = valorSugerido;
	}		public String getIndicadorJurosParCliente() {		return indicadorJurosParCliente;	}	public void setIndicadorJurosParCliente(String indicadorJurosParCliente) {		this.indicadorJurosParCliente = indicadorJurosParCliente;	}
}

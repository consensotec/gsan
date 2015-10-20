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
package gcom.cobranca.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * [UC0214] Efetuar Parcelamento de D�bitos 
 *
 * @author Roberta Costa
 * @date 28/03/2006
 */
public class OpcoesParcelamentoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Short indicadorQuantidadeParcelas;
	
	private Short quantidadePrestacao;
	
	private BigDecimal valorEntradaMinima;
	
	private BigDecimal valorPrestacao;

	private BigDecimal taxaJuros;
	
	private Short indicadorValorPrestacaoMaiorValorLimite;
	
	/**
	 * Construtor de OpcoesParcelamentoHelper 
	 */
	public OpcoesParcelamentoHelper() {
	}

	/**
	 * @return Retorna o campo valorEntradaMinima.
	 */
	public BigDecimal getValorEntradaMinima() {
		return valorEntradaMinima;
	}

	/**
	 * @param valorEntradaMinima O valorEntradaMinima a ser setado.
	 */
	public void setValorEntradaMinima(BigDecimal valorEntradaMinima) {
		this.valorEntradaMinima = valorEntradaMinima;
	}

	/**
	 * @return Retorna o campo quantidadePrestacao.
	 */
	public Short getQuantidadePrestacao() {
		return quantidadePrestacao;
	}

	/**
	 * @param quantidadePrestacao O quantidadePrestacao a ser setado.
	 */
	public void setQuantidadePrestacao(Short quantidadePrestacao) {
		this.quantidadePrestacao = quantidadePrestacao;
	}

	/**
	 * @return Retorna o campo taxaJuros.
	 */
	public BigDecimal getTaxaJuros() {
		return taxaJuros;
	}

	/**
	 * @param taxaJuros O taxaJuros a ser setado.
	 */
	public void setTaxaJuros(BigDecimal taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	/**
	 * @return Retorna o campo valorPrestacao.
	 */
	public BigDecimal getValorPrestacao() {
		return valorPrestacao;
	}

	/**
	 * @param valorPrestacao O valorPrestacao a ser setado.
	 */
	public void setValorPrestacao(BigDecimal valorPrestacao) {
		this.valorPrestacao = valorPrestacao;
	}

	/**
	 * @return Retorna o campo indicadorQuantidadeParcelas.
	 */
	public Short getIndicadorQuantidadeParcelas() {
		return indicadorQuantidadeParcelas;
	}

	/**
	 * @param indicadorQuantidadeParcelas O indicadorQuantidadeParcelas a ser setado.
	 */
	public void setIndicadorQuantidadeParcelas(Short indicadorQuantidadeParcelas) {
		this.indicadorQuantidadeParcelas = indicadorQuantidadeParcelas;
	}

	/**
	 * @return Retorna o campo indicadorValorPrestacaoMaiorValorLimite.
	 */
	public Short getIndicadorValorPrestacaoMaiorValorLimite() {
		return indicadorValorPrestacaoMaiorValorLimite;
	}

	/**
	 * @param indicadorValorPrestacaoMaiorValorLimite O indicadorValorPrestacaoMaiorValorLimite a ser setado.
	 */
	public void setIndicadorValorPrestacaoMaiorValorLimite(
			Short indicadorValorPrestacaoMaiorValorLimite) {
		this.indicadorValorPrestacaoMaiorValorLimite = indicadorValorPrestacaoMaiorValorLimite;
	}
	
	
}
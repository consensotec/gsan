/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/
package gsan.cobranca.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class CalcularValorDescontoAntiguidadeDebitoHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigDecimal valorDescontoAntiguidade;
	
	private BigDecimal valorTotalAcrescimosImpontualidade;
	
	private BigDecimal maiorQuantidadeMinimaMesesAntiguidade;
	
	private Collection colecaoContasEmAntiguidade;
	
	private Collection colecaoContasParaParcelamento;
	
	//RM3453 - adicionado por Vivianne Sousa - 25/06/2012
	private BigDecimal valorAtualizacaoMonetaria;
	private BigDecimal valorJurosMora;
	private BigDecimal valorMulta;
	
	public CalcularValorDescontoAntiguidadeDebitoHelper(BigDecimal valorDescontoAntiguidade, BigDecimal valorTotalAcrescimosImpontualidade, BigDecimal maiorQuantidadeMinimaMesesAntiguidade, Collection colecaoContasEmAntiguidade, Collection colecaoContasParaParcelamento) {
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
		this.valorTotalAcrescimosImpontualidade = valorTotalAcrescimosImpontualidade;
		this.maiorQuantidadeMinimaMesesAntiguidade = maiorQuantidadeMinimaMesesAntiguidade;
		this.colecaoContasEmAntiguidade = colecaoContasEmAntiguidade;
		this.colecaoContasParaParcelamento = colecaoContasParaParcelamento;
	}
	

	public CalcularValorDescontoAntiguidadeDebitoHelper(BigDecimal valorDescontoAntiguidade, 
			BigDecimal valorTotalAcrescimosImpontualidade, BigDecimal maiorQuantidadeMinimaMesesAntiguidade,
			Collection colecaoContasEmAntiguidade, Collection colecaoContasParaParcelamento,
			BigDecimal valorAtualizacaoMonetaria, BigDecimal valorJurosMora,BigDecimal valorMulta) {
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
		this.valorTotalAcrescimosImpontualidade = valorTotalAcrescimosImpontualidade;
		this.maiorQuantidadeMinimaMesesAntiguidade = maiorQuantidadeMinimaMesesAntiguidade;
		this.colecaoContasEmAntiguidade = colecaoContasEmAntiguidade;
		this.colecaoContasParaParcelamento = colecaoContasParaParcelamento;
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
		this.valorJurosMora = valorJurosMora;
		this.valorMulta = valorMulta;
	}


	public Collection getColecaoContasEmAntiguidade() {
		return colecaoContasEmAntiguidade;
	}

	public void setColecaoContasEmAntiguidade(Collection colecaoContasEmAntiguidade) {
		this.colecaoContasEmAntiguidade = colecaoContasEmAntiguidade;
	}

	public BigDecimal getMaiorQuantidadeMinimaMesesAntiguidade() {
		return maiorQuantidadeMinimaMesesAntiguidade;
	}

	public void setMaiorQuantidadeMinimaMesesAntiguidade(
			BigDecimal maiorQuantidadeMinimaMesesAntiguidade) {
		this.maiorQuantidadeMinimaMesesAntiguidade = maiorQuantidadeMinimaMesesAntiguidade;
	}

	public BigDecimal getValorDescontoAntiguidade() {
		return valorDescontoAntiguidade;
	}

	public void setValorDescontoAntiguidade(BigDecimal valorDescontoAntiguidade) {
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
	}

	public BigDecimal getValorTotalAcrescimosImpontualidade() {
		return valorTotalAcrescimosImpontualidade;
	}

	public void setValorTotalAcrescimosImpontualidade(
			BigDecimal valorTotalAcrescimosImpontualidade) {
		this.valorTotalAcrescimosImpontualidade = valorTotalAcrescimosImpontualidade;
	}

	public Collection getColecaoContasParaParcelamento() {
		return colecaoContasParaParcelamento;
	}

	public void setColecaoContasParaParcelamento(
			Collection colecaoContasParaParcelamento) {
		this.colecaoContasParaParcelamento = colecaoContasParaParcelamento;
	}

	public BigDecimal getValorAtualizacaoMonetaria() {
		return valorAtualizacaoMonetaria;
	}

	public void setValorAtualizacaoMonetaria(BigDecimal valorAtualizacaoMonetaria) {
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
	}

	public BigDecimal getValorJurosMora() {
		return valorJurosMora;
	}

	public void setValorJurosMora(BigDecimal valorJurosMora) {
		this.valorJurosMora = valorJurosMora;
	}

	public BigDecimal getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}
	
}

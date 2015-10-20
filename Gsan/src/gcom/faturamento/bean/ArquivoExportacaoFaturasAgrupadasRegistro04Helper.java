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
package gcom.faturamento.bean;

import gcom.util.Util;
import gcom.util.serializacao.Campo;

import java.math.BigDecimal;
import java.util.List;

/**
 * [UC1684] - Gerar Arquivo Exportação Faturas
 * Totais da Fatura
 * 
 * @author André Miranda
 * @date 09/06/2015
 */
public class ArquivoExportacaoFaturasAgrupadasRegistro04Helper {	
	private Integer quantidadeContas;
	private BigDecimal valorAgua;
	private BigDecimal valorEsgoto;
	private BigDecimal valorDebito;
	private BigDecimal valorCredito;
	private BigDecimal valorTotalFatura;
	private BigDecimal retencaoImpostos;
	private BigDecimal retencaoImpostosPercentual;
	private BigDecimal valorTotal;

	public ArquivoExportacaoFaturasAgrupadasRegistro04Helper() { }

	public ArquivoExportacaoFaturasAgrupadasRegistro04Helper(List<ArquivoExportacaoFaturasAgrupadasRegistro03Helper> itens) {
		quantidadeContas = itens.size();

		valorAgua = BigDecimal.ZERO;
		valorEsgoto = BigDecimal.ZERO;
		valorDebito = BigDecimal.ZERO;
		valorCredito = BigDecimal.ZERO;
		retencaoImpostos = BigDecimal.ZERO;
		retencaoImpostosPercentual =  BigDecimal.ZERO;
		valorTotal = BigDecimal.ZERO;

		for (ArquivoExportacaoFaturasAgrupadasRegistro03Helper item : itens) {
			valorAgua = valorAgua.add(item.getValorAgua());
			valorEsgoto = valorEsgoto.add(item.getValorEsgoto());
			valorDebito = valorDebito.add(item.getValorDebito());
			valorCredito = valorCredito.add(item.getValorCredito());
			retencaoImpostos = retencaoImpostos.add(item.getRetencaoImpostos());
			valorTotal = valorTotal.add(item.getValorTotal());
		}

		valorTotalFatura = valorAgua.add(valorEsgoto).add(valorDebito).subtract(valorCredito);

		retencaoImpostosPercentual = Util.calcularPercentualBigDecimal(retencaoImpostos, valorTotalFatura);
	}

	@Campo(posicao = 1)
	public String getTipoRegistro() {
		return "04";
	}

	@Campo(posicao = 2)
	public Integer getQuantidadeContas() {
		return quantidadeContas;
	}

	public void setQuantidadeContas(Integer quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}

	@Campo(posicao = 3, formato = Campo.FORMATO_MOEDA)
	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

	@Campo(posicao = 4, formato = Campo.FORMATO_MOEDA)
	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	@Campo(posicao = 5, formato = Campo.FORMATO_MOEDA)
	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	@Campo(posicao = 6, formato = Campo.FORMATO_MOEDA)
	public BigDecimal getValorCredito() {
		return valorCredito;
	}

	public void setValorCredito(BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
	}

	@Campo(posicao = 7, formato = Campo.FORMATO_MOEDA)
	public BigDecimal getValorTotalFatura() {
		return valorTotalFatura;
	}

	public void setValorTotalFatura(BigDecimal valorTotalFatura) {
		this.valorTotalFatura = valorTotalFatura;
	}

	@Campo(posicao = 8, formato = Campo.FORMATO_MOEDA)
	public BigDecimal getRetencaoImpostosPercentual() {
		return retencaoImpostosPercentual;
	}

	public void setRetencaoImpostosPercentual(BigDecimal retencaoImpostosPercentual) {
		this.retencaoImpostosPercentual = retencaoImpostosPercentual;
	}

	@Campo(posicao = 9, formato = Campo.FORMATO_MOEDA)
	public BigDecimal getRetencaoImpostos() {
		return retencaoImpostos;
	}

	public void setRetencaoImpostos(BigDecimal retencaoImpostos) {
		this.retencaoImpostos = retencaoImpostos;
	}

	@Campo(posicao = 10, formato = Campo.FORMATO_MOEDA)
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
}

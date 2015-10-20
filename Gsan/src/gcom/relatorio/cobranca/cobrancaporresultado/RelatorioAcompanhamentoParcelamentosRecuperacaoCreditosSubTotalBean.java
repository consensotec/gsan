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
* Genival Soares Barbosa Filho
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
package gcom.relatorio.cobranca.cobrancaporresultado;


import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
 * 
 * @author Hugo Azevedo
 * @date 19/10/2011
 * 
 */
public class RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean implements RelatorioBean,Comparable<RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean> {	

	private Integer idFaixaContas;
	private String faixaContas;
	private Integer qtdFaturasNegociadas;
	private BigDecimal valorNegociado;
	private BigDecimal valorPago;
	private BigDecimal saldoAberto;
	private Integer qtdParcelas;
	private Integer qtdParcelasAberto;
	private String msgTotal;
	
	
	public String getFaixaContas() {
		return faixaContas;
	}
	public void setFaixaContas(String faixaContas) {
		this.faixaContas = faixaContas;
	}
	public Integer getQtdFaturasNegociadas() {
		return qtdFaturasNegociadas;
	}
	public void setQtdFaturasNegociadas(Integer qtdFaturasNegociadas) {
		this.qtdFaturasNegociadas = qtdFaturasNegociadas;
	}
	public BigDecimal getValorNegociado() {
		return valorNegociado;
	}
	public void setValorNegociado(BigDecimal valorNegociado) {
		this.valorNegociado = valorNegociado;
	}
	public BigDecimal getValorPago() {
		return valorPago;
	}
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
	public BigDecimal getSaldoAberto() {
		return saldoAberto;
	}
	public void setSaldoAberto(BigDecimal saldoAberto) {
		this.saldoAberto = saldoAberto;
	}
	public Integer getQtdParcelas() {
		return qtdParcelas;
	}
	public void setQtdParcelas(Integer qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}
	public Integer getQtdParcelasAberto() {
		return qtdParcelasAberto;
	}
	public void setQtdParcelasAberto(Integer qtdParcelasAberto) {
		this.qtdParcelasAberto = qtdParcelasAberto;
	}
	
	
	public Integer getIdFaixaContas() {
		return idFaixaContas;
	}
	public void setIdFaixaContas(Integer idFaixaContas) {
		this.idFaixaContas = idFaixaContas;
	}
	
	public int compareTo(RelatorioAcompanhamentoParcelamentosRecuperacaoCreditosSubTotalBean r1){
		return r1.getIdFaixaContas().intValue() > this.getIdFaixaContas().intValue() ? -1 : (r1.getIdFaixaContas().intValue() < this.getIdFaixaContas().intValue() ? +1 : 0);
	}
	public String getMsgTotal() {
		return msgTotal;
	}
	public void setMsgTotal(String msgTotal) {
		this.msgTotal = msgTotal;
	}
	
	
}

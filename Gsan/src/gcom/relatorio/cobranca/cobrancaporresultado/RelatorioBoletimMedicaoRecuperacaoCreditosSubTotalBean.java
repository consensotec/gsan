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
* Genival Soares Barbosa Filho
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
package gcom.relatorio.cobranca.cobrancaporresultado;


import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC1237] Gerar Relat�rio de Boletim de Medi��o e Acompanhamento
 * 
 * @author Hugo Azevedo
 * @date 19/10/2011
 * 
 */
public class RelatorioBoletimMedicaoRecuperacaoCreditosSubTotalBean implements RelatorioBean,Comparable<RelatorioBoletimMedicaoRecuperacaoCreditosSubTotalBean> {	

	
	private Integer idFaixaContas;
	private String faixaContas;
	private Integer qtdImoveis;
	private Integer qtdFaturasNegociadas;
	private BigDecimal valorPagamentoAVista;
	private BigDecimal valorPagamentoParcelado;
	private BigDecimal valorPagamentoTotal;
	private BigDecimal percentualFaixa;
	private BigDecimal valorPagamentoAVistaPagPrestadora;
	private BigDecimal valorPagamentoParceladoPagPrestadora;
	private BigDecimal valorPagamentoTotalPagPrestadora;
	private BigDecimal valorDesconto;
	private String gerenciaRegional;
	private String regiao;
	private String msgTotal;
	


	public Integer getIdFaixaContas() {
		return idFaixaContas;
	}
	public void setIdFaixaContas(Integer idFaixaContas) {
		this.idFaixaContas = idFaixaContas;
	}
	
	public String getFaixaContas() {
		return faixaContas;
	}
	public void setFaixaContas(String faixaContas) {
		this.faixaContas = faixaContas;
	}
	public Integer getQtdImoveis() {
		return qtdImoveis;
	}
	public void setQtdImoveis(Integer qtdImoveis) {
		this.qtdImoveis = qtdImoveis;
	}
	public Integer getQtdFaturasNegociadas() {
		return qtdFaturasNegociadas;
	}
	public void setQtdFaturasNegociadas(Integer qtdFaturasNegociadas) {
		this.qtdFaturasNegociadas = qtdFaturasNegociadas;
	}
	public BigDecimal getValorPagamentoAVista() {
		return valorPagamentoAVista;
	}
	public void setValorPagamentoAVista(BigDecimal valorPagamentoAVista) {
		this.valorPagamentoAVista = valorPagamentoAVista;
	}
	public BigDecimal getValorPagamentoParcelado() {
		return valorPagamentoParcelado;
	}
	public void setValorPagamentoParcelado(BigDecimal valorPagamentoParcelado) {
		this.valorPagamentoParcelado = valorPagamentoParcelado;
	}
	public BigDecimal getValorPagamentoTotal() {
		return valorPagamentoTotal;
	}
	public void setValorPagamentoTotal(BigDecimal valorPagamentoTotal) {
		this.valorPagamentoTotal = valorPagamentoTotal;
	}
	public BigDecimal getPercentualFaixa() {
		return percentualFaixa;
	}
	public void setPercentualFaixa(BigDecimal percentualFaixa) {
		this.percentualFaixa = percentualFaixa;
	}
	public BigDecimal getValorPagamentoAVistaPagPrestadora() {
		return valorPagamentoAVistaPagPrestadora;
	}
	public void setValorPagamentoAVistaPagPrestadora(
			BigDecimal valorPagamentoAVistaPagPrestadora) {
		this.valorPagamentoAVistaPagPrestadora = valorPagamentoAVistaPagPrestadora;
	}
	public BigDecimal getValorPagamentoParceladoPagPrestadora() {
		return valorPagamentoParceladoPagPrestadora;
	}
	public void setValorPagamentoParceladoPagPrestadora(
			BigDecimal valorPagamentoParceladoPagPrestadora) {
		this.valorPagamentoParceladoPagPrestadora = valorPagamentoParceladoPagPrestadora;
	}
	public BigDecimal getValorPagamentoTotalPagPrestadora() {
		return valorPagamentoTotalPagPrestadora;
	}
	public void setValorPagamentoTotalPagPrestadora(
			BigDecimal valorPagamentoTotalPagPrestadora) {
		this.valorPagamentoTotalPagPrestadora = valorPagamentoTotalPagPrestadora;
	}
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public int compareTo(RelatorioBoletimMedicaoRecuperacaoCreditosSubTotalBean r1){
		return r1.getIdFaixaContas().intValue() > this.getIdFaixaContas().intValue() ? -1 : (r1.getIdFaixaContas().intValue() < this.getIdFaixaContas().intValue() ? +1 : 0);
	}
	public String getMsgTotal() {
		return msgTotal;
	}
	public void setMsgTotal(String msgTotal) {
		this.msgTotal = msgTotal;
	}
	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	
		
	
}

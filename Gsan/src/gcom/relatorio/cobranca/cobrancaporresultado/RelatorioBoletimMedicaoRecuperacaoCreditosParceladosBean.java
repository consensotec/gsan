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
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
 * 
 * @author Hugo Azevedo
 * @date 19/10/2011
 * 
 */
public class RelatorioBoletimMedicaoRecuperacaoCreditosParceladosBean implements RelatorioBean {	

	private Integer idLocalidade;
	private String descricaoLocalidade;
	private Integer idMunicipio;
	private String descricaoMunicipio;
	private String faixaContas;
	private Integer qtdImoveis;
	private Integer qtdFaturasNegociadas;
	private BigDecimal valorPagamentoParcelado;
	private BigDecimal porcentagemPagoPrestadora;
	private BigDecimal valorNegociado;
	private String gerenciaRegional;
	private String regiao;
	private JRBeanCollectionDataSource arrayJRFaixasContasTotalGerencia;
	private JRBeanCollectionDataSource arrayJRFaixasContasTotal;
	
	public JRBeanCollectionDataSource getArrayJRFaixasContasTotalGerencia() {
		return arrayJRFaixasContasTotalGerencia;
	}
	
	public void setArrayJRFaixasContasTotalGerencia(List<RelatorioBoletimMedicaoRecuperacaoCreditosParceladosSubTotalBean> colecao) {
		this.arrayJRFaixasContasTotalGerencia = new JRBeanCollectionDataSource(colecao);
	}
	
	public JRBeanCollectionDataSource getArrayJRFaixasContasTotal() {
		return arrayJRFaixasContasTotal;
	}
	
	public void setArrayJRFaixasContasTotal(List<RelatorioBoletimMedicaoRecuperacaoCreditosParceladosSubTotalBean> colecao) {
		this.arrayJRFaixasContasTotal = new JRBeanCollectionDataSource(colecao);
	}
	
	
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public Integer getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}
	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
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
	public BigDecimal getValorPagamentoParcelado() {
		return valorPagamentoParcelado;
	}
	public void setValorPagamentoParcelado(BigDecimal valorPagamentoParcelado) {
		this.valorPagamentoParcelado = valorPagamentoParcelado;
	}
	public BigDecimal getPorcentagemPagoPrestadora() {
		return porcentagemPagoPrestadora;
	}
	public void setPorcentagemPagoPrestadora(BigDecimal porcentagemPagoPrestadora) {
		this.porcentagemPagoPrestadora = porcentagemPagoPrestadora;
	}
	public BigDecimal getValorNegociado() {
		return valorNegociado;
	}
	public void setValorNegociado(BigDecimal valorNegociado) {
		this.valorNegociado = valorNegociado;
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
	
	
	
	
	
}

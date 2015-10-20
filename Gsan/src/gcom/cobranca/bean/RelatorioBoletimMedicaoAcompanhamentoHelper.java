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
 * [UC1237] Gerar Relat�rio de Boletim de Medi��o e Acompanhamento
 *
 * @author Hugo Azevedo
 * @date 17/10/2011
 */
public class RelatorioBoletimMedicaoAcompanhamentoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer gerenciaRegional;
	private Integer idLocalidade;
	private String descricaoLocalidade;
	private Integer idFaixaContas;
	private String descricaoFaixaContas;
	private Integer regiao;
	private Integer idMunicipio;
	private String descricaoMunicipio;
	private String empresa;
	private String contrato;
	private String mesAnoReferencia;
	private Integer qtdFaturasNegociadas;
	private BigDecimal valorPagamentoAVista;
	private BigDecimal valorPagamentoAVistaPorPagoPrest;
	private BigDecimal valorPagamentoParcelado;
	private BigDecimal valorPagamentoParceladoPorPagoPrest;
	private BigDecimal valorTotalPago;
	private BigDecimal valorTotalPagoPorPagoPrest;
	private BigDecimal percentualFaixa;
	private Integer qtdImoveis;
	private String nomeCLiente;
	private Integer idImovel;
	private BigDecimal valorNegociado;
	private String nomeGerenciaRegional;
	private String nomeRegiao;
	private Integer parcelasPaga;
	private BigDecimal valorPago;
	private Integer quantidadeParcelasEmAberto;
	private Integer quantidadeParcelasEmAbertoHist;
	private BigDecimal saldoEmAberto;
	private BigDecimal saldoEmAbertoHist;
	private Integer parcelaAtraso;
	private BigDecimal ValorEmAtraso;
	private BigDecimal valorDesconto;

	
	public Integer getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(Integer gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
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
	public Integer getIdFaixaContas() {
		return idFaixaContas;
	}
	public void setIdFaixaContas(Integer idFaixaContas) {
		this.idFaixaContas = idFaixaContas;
	}
	public String getDescricaoFaixaContas() {
		return descricaoFaixaContas;
	}
	public void setDescricaoFaixaContas(String descricaoFaixaContas) {
		this.descricaoFaixaContas = descricaoFaixaContas;
	}
	public Integer getRegiao() {
		return regiao;
	}
	public void setRegiao(Integer regiao) {
		this.regiao = regiao;
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
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getContrato() {
		return contrato;
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
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
	public BigDecimal getValorPagamentoAVistaPorPagoPrest() {
		return valorPagamentoAVistaPorPagoPrest;
	}
	public void setValorPagamentoAVistaPorPagoPrest(
			BigDecimal valorPagamentoAVistaPorPagoPrest) {
		this.valorPagamentoAVistaPorPagoPrest = valorPagamentoAVistaPorPagoPrest;
	}
	public BigDecimal getValorPagamentoParcelado() {
		return valorPagamentoParcelado;
	}
	public void setValorPagamentoParcelado(BigDecimal valorPagamentoParcelado) {
		this.valorPagamentoParcelado = valorPagamentoParcelado;
	}
	public BigDecimal getValorPagamentoParceladoPorPagoPrest() {
		return valorPagamentoParceladoPorPagoPrest;
	}
	public void setValorPagamentoParceladoPorPagoPrest(
			BigDecimal valorPagamentoParceladoPorPagoPrest) {
		this.valorPagamentoParceladoPorPagoPrest = valorPagamentoParceladoPorPagoPrest;
	}
	public BigDecimal getValorTotalPago() {
		return valorTotalPago;
	}
	public void setValorTotalPago(BigDecimal valorTotalPago) {
		this.valorTotalPago = valorTotalPago;
	}
	public BigDecimal getValorTotalPagoPorPagoPrest() {
		return valorTotalPagoPorPagoPrest;
	}
	public void setValorTotalPagoPorPagoPrest(BigDecimal valorTotalPagoPorPagoPrest) {
		this.valorTotalPagoPorPagoPrest = valorTotalPagoPorPagoPrest;
	}
	public BigDecimal getPercentualFaixa() {
		return percentualFaixa;
	}
	public void setPercentualFaixa(BigDecimal percentualFaixa) {
		this.percentualFaixa = percentualFaixa;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getQtdImoveis() {
		return qtdImoveis;
	}
	public void setQtdImoveis(Integer qtdImoveis) {
		this.qtdImoveis = qtdImoveis;
	}
	public String getNomeCLiente() {
		return nomeCLiente;
	}
	public void setNomeCLiente(String nomeCLiente) {
		this.nomeCLiente = nomeCLiente;
	}
	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	public BigDecimal getValorNegociado() {
		return valorNegociado;
	}
	public void setValorNegociado(BigDecimal valorNegociado) {
		this.valorNegociado = valorNegociado;
	}
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	public String getNomeRegiao() {
		return nomeRegiao;
	}
	public void setNomeRegiao(String nomeRegiao) {
		this.nomeRegiao = nomeRegiao;
	}
	public Integer getParcelasPaga() {
		return parcelasPaga;
	}
	public void setParcelasPaga(Integer parcelasPaga) {
		this.parcelasPaga = parcelasPaga;
	}
	public BigDecimal getValorPago() {
		return valorPago;
	}
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
	public Integer getQuantidadeParcelasEmAberto() {
		return quantidadeParcelasEmAberto;
	}
	public void setQuantidadeParcelasEmAberto(Integer quantidadeParcelasEmAberto) {
		this.quantidadeParcelasEmAberto = quantidadeParcelasEmAberto;
	}
	public Integer getQuantidadeParcelasEmAbertoHist() {
		return quantidadeParcelasEmAbertoHist;
	}
	public void setQuantidadeParcelasEmAbertoHist(
			Integer quantidadeParcelasEmAbertoHist) {
		this.quantidadeParcelasEmAbertoHist = quantidadeParcelasEmAbertoHist;
	}
	public BigDecimal getSaldoEmAberto() {
		return saldoEmAberto;
	}
	public void setSaldoEmAberto(BigDecimal saldoEmAberto) {
		this.saldoEmAberto = saldoEmAberto;
	}
	public BigDecimal getSaldoEmAbertoHist() {
		return saldoEmAbertoHist;
	}
	public void setSaldoEmAbertoHist(BigDecimal saldoEmAbertoHist) {
		this.saldoEmAbertoHist = saldoEmAbertoHist;
	}
	public Integer getParcelaAtraso() {
		return parcelaAtraso;
	}
	public void setParcelaAtraso(Integer parcelaAtraso) {
		this.parcelaAtraso = parcelaAtraso;
	}
	public BigDecimal getValorEmAtraso() {
		return ValorEmAtraso;
	}
	public void setValorEmAtraso(BigDecimal valorEmAtraso) {
		ValorEmAtraso = valorEmAtraso;
	}
	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	
	


	}
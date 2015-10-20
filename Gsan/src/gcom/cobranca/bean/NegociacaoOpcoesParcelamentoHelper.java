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

import gcom.cobranca.parcelamento.ParcelamentoPerfil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * [UC0214] Efetuar Parcelamento de D�bitos 
 *
 * @author Roberta Costa
 * @date 28/03/2006
 */
public class NegociacaoOpcoesParcelamentoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public NegociacaoOpcoesParcelamentoHelper() {
	}

	private Collection<OpcoesParcelamentoHelper> opcoesParcelamento;
	
	private BigDecimal valorEntradaMinima;
	
	private BigDecimal valorDescontoAcrecismosImpotualidade;

	private BigDecimal valorDescontoInatividade;

	private BigDecimal valorDescontoAntiguidade;
	
	private BigDecimal percentualDescontoAcrescimosImpontualidade;
	
	private BigDecimal percentualDescontoAntiguidadeDebito;
	
	private BigDecimal percentualDescontoInatividadeLigacaoAgua;
	
	private ParcelamentoPerfil parcelamentoPerfil;
	
	private BigDecimal valorDescontoSancoesRDEspecial;
	
	private BigDecimal valorDescontoTarifaSocialRDEspecial;
	
	private BigDecimal valorTotalDescontoPagamentoAVista;
	
	private Collection colecaoContasEmAntiguidade;
	
	private Collection colecaoContasParaParcelamento;

	private Short indicadorExisteParcelamentoEmAndamento;
	
	private BigDecimal valorDebitoDesconto;
	
	/**
	 * Valor min�mo da presta��o para usar na aba de negocia��o
	 */
	private BigDecimal valorMinimoPrestacao;
	
	private BigDecimal percentualDescontoInatividadeAvistaLigacaoAgua;
	
	private BigDecimal valorDescontoSobreDebitoTotal;
	
	private BigDecimal valorDescontoDebitoTipo;
	private BigDecimal valorDescontoMulta;
	private BigDecimal valorDescontoJuros;
	private BigDecimal valorDescontoAtualizacaoMonetaria;
	private Short entradaEhExtratoDebito;
	private BigDecimal valorDescontoEntrada;
	private BigDecimal valorEntradaMinimaPermitida;
	private Short entradaPodeSerGuia;
	private String indicadorNaoPermiteParc;
	
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

	public Short getEntradaPodeSerGuia() {
		return entradaPodeSerGuia;
	}

	public void setEntradaPodeSerGuia(Short entradaPodeSerGuia) {
		this.entradaPodeSerGuia = entradaPodeSerGuia;
	}

	/**
	 * @return Retorna o campo valorDescontoAcrecismosImpotualidade.
	 */
	public BigDecimal getValorDescontoAcrecismosImpotualidade() {
		return valorDescontoAcrecismosImpotualidade;
	}

	/**
	 * @param valorDescontoAcrecismosImpotualidade O valorDescontoAcrecismosImpotualidade a ser setado.
	 */
	public void setValorDescontoAcrecismosImpotualidade(
			BigDecimal valorDescontoAcrecismosImpotualidade) {
		this.valorDescontoAcrecismosImpotualidade = valorDescontoAcrecismosImpotualidade;
	}

	/**
	 * @return Retorna o campo valorDescontoAntiguidade.
	 */
	public BigDecimal getValorDescontoAntiguidade() {
		return valorDescontoAntiguidade;
	}

	/**
	 * @param valorDescontoAntiguidade O valorDescontoAntiguidade a ser setado.
	 */
	public void setValorDescontoAntiguidade(BigDecimal valorDescontoAntiguidade) {
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
	}

	/**
	 * @return Retorna o campo valorDescontoInatividade.
	 */
	public BigDecimal getValorDescontoInatividade() {
		return valorDescontoInatividade;
	}

	/**
	 * @param valorDescontoInatividade O valorDescontoInatividade a ser setado.
	 */
	public void setValorDescontoInatividade(BigDecimal valorDescontoInatividade) {
		this.valorDescontoInatividade = valorDescontoInatividade;
	}

	/**
	 * @return Retorna o campo opcoesParcelamento.
	 */
	public Collection<OpcoesParcelamentoHelper> getOpcoesParcelamento() {
		return opcoesParcelamento;
	}

	/**
	 * @param opcoesParcelamento O opcoesParcelamento a ser setado.
	 */
	public void setOpcoesParcelamento(
			Collection<OpcoesParcelamentoHelper> opcoesParcelamento) {
		this.opcoesParcelamento = opcoesParcelamento;
	}

	/**
	 * @return Retorna o campo parcelamentoPerfil.
	 */
	public ParcelamentoPerfil getParcelamentoPerfil() {
		return parcelamentoPerfil;
	}

	/**
	 * @param parcelamentoPerfil O parcelamentoPerfil a ser setado.
	 */
	public void setParcelamentoPerfil(ParcelamentoPerfil parcelamentoPerfil) {
		this.parcelamentoPerfil = parcelamentoPerfil;
	}

	/**
	 * @return Retorna o campo percentualDescontoAcrescimosImpontualidade.
	 */
	public BigDecimal getPercentualDescontoAcrescimosImpontualidade() {
		return percentualDescontoAcrescimosImpontualidade;
	}

	/**
	 * @param percentualDescontoAcrescimosImpontualidade O percentualDescontoAcrescimosImpontualidade a ser setado.
	 */
	public void setPercentualDescontoAcrescimosImpontualidade(
			BigDecimal percentualDescontoAcrescimosImpontualidade) {
		this.percentualDescontoAcrescimosImpontualidade = percentualDescontoAcrescimosImpontualidade;
	}

	/**
	 * @return Retorna o campo percentualDescontoAntiguidadeDebito.
	 */
	public BigDecimal getPercentualDescontoAntiguidadeDebito() {
		return percentualDescontoAntiguidadeDebito;
	}

	/**
	 * @param percentualDescontoAntiguidadeDebito O percentualDescontoAntiguidadeDebito a ser setado.
	 */
	public void setPercentualDescontoAntiguidadeDebito(
			BigDecimal percentualDescontoAntiguidadeDebito) {
		this.percentualDescontoAntiguidadeDebito = percentualDescontoAntiguidadeDebito;
	}

	/**
	 * @return Retorna o campo percentualDescontoInatividadeLigacaoAgua.
	 */
	public BigDecimal getPercentualDescontoInatividadeLigacaoAgua() {
		return percentualDescontoInatividadeLigacaoAgua;
	}

	/**
	 * @param percentualDescontoInatividadeLigacaoAgua O percentualDescontoInatividadeLigacaoAgua a ser setado.
	 */
	public void setPercentualDescontoInatividadeLigacaoAgua(
			BigDecimal percentualDescontoInatividadeLigacaoAgua) {
		this.percentualDescontoInatividadeLigacaoAgua = percentualDescontoInatividadeLigacaoAgua;
	}
	
	/**
	 * @return Retorna o campo valorMinimoPrestacao.
	 */
	public BigDecimal getValorMinimoPrestacao() {
		return valorMinimoPrestacao;
	}

	/**
	 * @param valorMinimoPrestacao O valorMinimoPrestacao a ser setado.
	 */
	public void setValorMinimoPrestacao(BigDecimal valorMinimoPrestacao) {
		this.valorMinimoPrestacao = valorMinimoPrestacao;
	}

	public BigDecimal getValorDescontoSancoesRDEspecial() {
		return valorDescontoSancoesRDEspecial;
	}

	public void setValorDescontoSancoesRDEspecial(
			BigDecimal valorDescontoSancoesRDEspecial) {
		this.valorDescontoSancoesRDEspecial = valorDescontoSancoesRDEspecial;
	}

	/**
	 * @return Retorna o campo valorDescontoTarifaSocialRDEspecial.
	 */
	public BigDecimal getValorDescontoTarifaSocialRDEspecial() {
		return valorDescontoTarifaSocialRDEspecial;
	}

	/**
	 * @param valorDescontoTarifaSocialRDEspecial O valorDescontoTarifaSocialRDEspecial a ser setado.
	 */
	public void setValorDescontoTarifaSocialRDEspecial(
			BigDecimal valorDescontoTarifaSocialRDEspecial) {
		this.valorDescontoTarifaSocialRDEspecial = valorDescontoTarifaSocialRDEspecial;
	}

	public BigDecimal getValorTotalDescontoPagamentoAVista() {
		return valorTotalDescontoPagamentoAVista;
	}

	public void setValorTotalDescontoPagamentoAVista(
			BigDecimal valorTotalDescontoPagamentoAVista) {
		this.valorTotalDescontoPagamentoAVista = valorTotalDescontoPagamentoAVista;
	}

	public Collection getColecaoContasEmAntiguidade() {
		return colecaoContasEmAntiguidade;
	}

	public void setColecaoContasEmAntiguidade(Collection colecaoContasEmAntiguidade) {
		this.colecaoContasEmAntiguidade = colecaoContasEmAntiguidade;
	}

	public Collection getColecaoContasParaParcelamento() {
		return colecaoContasParaParcelamento;
	}

	public void setColecaoContasParaParcelamento(
			Collection colecaoContasParaParcelamento) {
		this.colecaoContasParaParcelamento = colecaoContasParaParcelamento;
	}

	public Short getIndicadorExisteParcelamentoEmAndamento() {
		return indicadorExisteParcelamentoEmAndamento;
	}

	public void setIndicadorExisteParcelamentoEmAndamento(
			Short indicadorExisteParcelamentoEmAndamento) {
		this.indicadorExisteParcelamentoEmAndamento = indicadorExisteParcelamentoEmAndamento;
	}

	public BigDecimal getPercentualDescontoInatividadeAvistaLigacaoAgua() {
		return percentualDescontoInatividadeAvistaLigacaoAgua;
	}

	public void setPercentualDescontoInatividadeAvistaLigacaoAgua(
			BigDecimal percentualDescontoInatividadeAvistaLigacaoAgua) {
		this.percentualDescontoInatividadeAvistaLigacaoAgua = percentualDescontoInatividadeAvistaLigacaoAgua;
	}

	public BigDecimal getValorDebitoDesconto() {
		return valorDebitoDesconto;
	}

	public void setValorDebitoDesconto(BigDecimal valorDebitoDesconto) {
		this.valorDebitoDesconto = valorDebitoDesconto;
	}

	public BigDecimal getValorDescontoSobreDebitoTotal() {
		return valorDescontoSobreDebitoTotal;
	}

	public void setValorDescontoSobreDebitoTotal(BigDecimal valorDescontoSobreDebitoTotal) {
		this.valorDescontoSobreDebitoTotal = valorDescontoSobreDebitoTotal;
	}

	public BigDecimal getValorDescontoDebitoTipo() {
		return valorDescontoDebitoTipo;
	}

	public void setValorDescontoDebitoTipo(BigDecimal valorDescontoDebitoTipo) {
		this.valorDescontoDebitoTipo = valorDescontoDebitoTipo;
	}

	public BigDecimal getValorDescontoMulta() {
		return valorDescontoMulta;
	}

	public void setValorDescontoMulta(BigDecimal valorDescontoMulta) {
		this.valorDescontoMulta = valorDescontoMulta;
	}

	public BigDecimal getValorDescontoJuros() {
		return valorDescontoJuros;
	}

	public BigDecimal getValorEntradaMinimaPermitida() {
		return valorEntradaMinimaPermitida;
	}

	public void setValorEntradaMinimaPermitida(
			BigDecimal valorEntradaMinimaPermitida) {
		this.valorEntradaMinimaPermitida = valorEntradaMinimaPermitida;
	}

	public void setValorDescontoJuros(BigDecimal valorDescontoJuros) {
		this.valorDescontoJuros = valorDescontoJuros;
	}

	public BigDecimal getValorDescontoAtualizacaoMonetaria() {
		return valorDescontoAtualizacaoMonetaria;
	}

	public void setValorDescontoAtualizacaoMonetaria(
			BigDecimal valorDescontoAtualizacaoMonetaria) {
		this.valorDescontoAtualizacaoMonetaria = valorDescontoAtualizacaoMonetaria;
	}

	public Short getEntradaEhExtratoDebito() {
		return entradaEhExtratoDebito;
	}

	public void setEntradaEhExtratoDebito(Short entradaEhExtratoDebito) {
		this.entradaEhExtratoDebito = entradaEhExtratoDebito;
	}

	public BigDecimal getValorDescontoEntrada() {
		return valorDescontoEntrada;
	}

	public void setValorDescontoEntrada(BigDecimal valorDescontoEntrada) {
		this.valorDescontoEntrada = valorDescontoEntrada;
	}

	public String getIndicadorNaoPermiteParc() {
		return indicadorNaoPermiteParc;
	}

	public void setIndicadorNaoPermiteParc(String indicadorNaoPermiteParc) {
		this.indicadorNaoPermiteParc = indicadorNaoPermiteParc;
	}

	
}
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

public class DeterminarValorDescontoAcrescimosImpontualidadeHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private ParcelamentoPerfil parcelamentoPerfil;
	
	private BigDecimal valorTotalAcrescimosImpontualidadePorAntiguidade;
	
	private BigDecimal valorAcrescimosImpontualidadeGuiaPagamento;
	
	private BigDecimal valorDescontoAcrescimosImpontualidadeRDEspecial;
	
	//RM3453 - adicionado por Vivianne Sousa - 25/06/2012
	private BigDecimal valorTotalMultaParaDesconto;
	private BigDecimal valorTotalJurosParaDesconto;
	private BigDecimal valorTotalAtualizacaoMonetariaParaDesconto;
	
	
	public ParcelamentoPerfil getParcelamentoPerfil() {
		return parcelamentoPerfil;
	}

	public void setParcelamentoPerfil(ParcelamentoPerfil parcelamentoPerfil) {
		this.parcelamentoPerfil = parcelamentoPerfil;
	}

	public BigDecimal getValorAcrescimosImpontualidadeGuiaPagamento() {
		return valorAcrescimosImpontualidadeGuiaPagamento;
	}

	public void setValorAcrescimosImpontualidadeGuiaPagamento(
			BigDecimal valorAcrescimosImpontualidadeGuiaPagamento) {
		this.valorAcrescimosImpontualidadeGuiaPagamento = valorAcrescimosImpontualidadeGuiaPagamento;
	}

	public BigDecimal getValorDescontoAcrescimosImpontualidadeRDEspecial() {
		return valorDescontoAcrescimosImpontualidadeRDEspecial;
	}

	public void setValorDescontoAcrescimosImpontualidadeRDEspecial(
			BigDecimal valorDescontoAcrescimosImpontualidadeRDEspecial) {
		this.valorDescontoAcrescimosImpontualidadeRDEspecial = valorDescontoAcrescimosImpontualidadeRDEspecial;
	}

	public BigDecimal getValorTotalAcrescimosImpontualidadePorAntiguidade() {
		return valorTotalAcrescimosImpontualidadePorAntiguidade;
	}

	public void setValorTotalAcrescimosImpontualidadePorAntiguidade(
			BigDecimal valorTotalAcrescimosImpontualidadePorAntiguidade) {
		this.valorTotalAcrescimosImpontualidadePorAntiguidade = valorTotalAcrescimosImpontualidadePorAntiguidade;
	}

	public DeterminarValorDescontoAcrescimosImpontualidadeHelper(ParcelamentoPerfil parcelamentoPerfil, BigDecimal valorTotalAcrescimosImpontualidadePorAntiguidade, BigDecimal valorAcrescimosImpontualidadeGuiaPagamento, BigDecimal valorDescontoAcrescimosImpontualidadeRDEspecial) {
		super();
		this.parcelamentoPerfil = parcelamentoPerfil;
		this.valorTotalAcrescimosImpontualidadePorAntiguidade = valorTotalAcrescimosImpontualidadePorAntiguidade;
		this.valorAcrescimosImpontualidadeGuiaPagamento = valorAcrescimosImpontualidadeGuiaPagamento;
		this.valorDescontoAcrescimosImpontualidadeRDEspecial = valorDescontoAcrescimosImpontualidadeRDEspecial;
	}

	public DeterminarValorDescontoAcrescimosImpontualidadeHelper(
			ParcelamentoPerfil parcelamentoPerfil,
			BigDecimal valorTotalAcrescimosImpontualidadePorAntiguidade,
			BigDecimal valorAcrescimosImpontualidadeGuiaPagamento,
			BigDecimal valorDescontoAcrescimosImpontualidadeRDEspecial,
			BigDecimal valorTotalMultaParaDesconto,
			BigDecimal valorTotalJurosParaDesconto,
			BigDecimal valorTotalAtualizacaoMonetariaParaDesconto) {
		super();
		this.parcelamentoPerfil = parcelamentoPerfil;
		this.valorTotalAcrescimosImpontualidadePorAntiguidade = valorTotalAcrescimosImpontualidadePorAntiguidade;
		this.valorAcrescimosImpontualidadeGuiaPagamento = valorAcrescimosImpontualidadeGuiaPagamento;
		this.valorDescontoAcrescimosImpontualidadeRDEspecial = valorDescontoAcrescimosImpontualidadeRDEspecial;
		this.valorTotalMultaParaDesconto = valorTotalMultaParaDesconto;
		this.valorTotalJurosParaDesconto = valorTotalJurosParaDesconto;
		this.valorTotalAtualizacaoMonetariaParaDesconto = valorTotalAtualizacaoMonetariaParaDesconto;
	}

	public BigDecimal getValorTotalMultaParaDesconto() {
		return valorTotalMultaParaDesconto;
	}

	public void setValorTotalMultaParaDesconto(
			BigDecimal valorTotalMultaParaDesconto) {
		this.valorTotalMultaParaDesconto = valorTotalMultaParaDesconto;
	}

	public BigDecimal getValorTotalJurosParaDesconto() {
		return valorTotalJurosParaDesconto;
	}

	public void setValorTotalJurosParaDesconto(
			BigDecimal valorTotalJurosParaDesconto) {
		this.valorTotalJurosParaDesconto = valorTotalJurosParaDesconto;
	}

	public BigDecimal getValorTotalAtualizacaoMonetariaParaDesconto() {
		return valorTotalAtualizacaoMonetariaParaDesconto;
	}

	public void setValorTotalAtualizacaoMonetariaParaDesconto(
			BigDecimal valorTotalAtualizacaoMonetariaParaDesconto) {
		this.valorTotalAtualizacaoMonetariaParaDesconto = valorTotalAtualizacaoMonetariaParaDesconto;
	}


	
}

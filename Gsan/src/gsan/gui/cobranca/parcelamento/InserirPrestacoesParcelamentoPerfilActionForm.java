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
package gsan.gui.cobranca.parcelamento;

import org.apache.struts.action.ActionForm;

public class InserirPrestacoesParcelamentoPerfilActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String quantidadeMaximaPrestacao;
	
	private String taxaJuros;
	
	private String percentualMinimoEntrada;
	
	private String percentualTarifaMinimaImovel;
	
	private String percentualValorReparcelado;
	
	private String valorMaxPercFaixaValor;

	private String percentualPercFaixaValor;

	private String quantidadeMaxPrestacaoEspecial;
	
	private String fatorQuantidadePrestacoes;
	
	private String indicadorMediaValorContas;
	
	private String indicadorValorUltimaContaEmAtraso;
	
	private String[] idsRegistros;
	
	private String indicadorGuiaPagamentoPermissaoEspecial;
	
	private String indicadorEntradaMenorPrestacaoParcelamento;
	
	public String getPercentualMinimoEntrada() {
		return percentualMinimoEntrada;
	}

	public String getPercentualPercFaixaValor() {
		return percentualPercFaixaValor;
	}

	public void setPercentualPercFaixaValor(String percentualPercFaixaValor) {
		this.percentualPercFaixaValor = percentualPercFaixaValor;
	}

	public String getValorMaxPercFaixaValor() {
		return valorMaxPercFaixaValor;
	}

	public void setValorMaxPercFaixaValor(String valorMaxPercFaixaValor) {
		this.valorMaxPercFaixaValor = valorMaxPercFaixaValor;
	}

	public void setPercentualMinimoEntrada(String percentualMinimoEntrada) {
		this.percentualMinimoEntrada = percentualMinimoEntrada;
	}

	public String getPercentualTarifaMinimaImovel() {
		return percentualTarifaMinimaImovel;
	}

	public void setPercentualTarifaMinimaImovel(String percentualTarifaMinimaImovel) {
		this.percentualTarifaMinimaImovel = percentualTarifaMinimaImovel;
	}

	public String getQuantidadeMaximaPrestacao() {
		return quantidadeMaximaPrestacao;
	}

	public void setQuantidadeMaximaPrestacao(String quantidadeMaximaPrestacao) {
		this.quantidadeMaximaPrestacao = quantidadeMaximaPrestacao;
	}

	public String getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(String taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public String getPercentualValorReparcelado() {
		return percentualValorReparcelado;
	}

	public void setPercentualValorReparcelado(String percentualValorReparcelado) {
		this.percentualValorReparcelado = percentualValorReparcelado;
	}

	public String getQuantidadeMaxPrestacaoEspecial() {
		return quantidadeMaxPrestacaoEspecial;
	}

	public void setQuantidadeMaxPrestacaoEspecial(
			String quantidadeMaxPrestacaoEspecial) {
		this.quantidadeMaxPrestacaoEspecial = quantidadeMaxPrestacaoEspecial;
	}

	public String getFatorQuantidadePrestacoes() {
		return fatorQuantidadePrestacoes;
	}

	public void setFatorQuantidadePrestacoes(String fatorQuantidadePrestacoes) {
		this.fatorQuantidadePrestacoes = fatorQuantidadePrestacoes;
	}

	public String getIndicadorMediaValorContas() {
		return indicadorMediaValorContas;
	}

	public void setIndicadorMediaValorContas(String indicadorMediaValorContas) {
		this.indicadorMediaValorContas = indicadorMediaValorContas;
	}

	public String getIndicadorValorUltimaContaEmAtraso() {
		return indicadorValorUltimaContaEmAtraso;
	}

	public void setIndicadorValorUltimaContaEmAtraso(
			String indicadorValorUltimaContaEmAtraso) {
		this.indicadorValorUltimaContaEmAtraso = indicadorValorUltimaContaEmAtraso;
	}

	public String[] getIdsRegistros() {
		return idsRegistros;
	}

	public void setIdsRegistros(String[] idsRegistros) {
		this.idsRegistros = idsRegistros;
	}

	public String getIndicadorGuiaPagamentoPermissaoEspecial() {
		return indicadorGuiaPagamentoPermissaoEspecial;
	}

	public void setIndicadorGuiaPagamentoPermissaoEspecial(
			String indicadorGuiaPagamentoPermissaoEspecial) {
		this.indicadorGuiaPagamentoPermissaoEspecial = indicadorGuiaPagamentoPermissaoEspecial;
	}

	public String getIndicadorEntradaMenorPrestacaoParcelamento() {
		return indicadorEntradaMenorPrestacaoParcelamento;
	}

	public void setIndicadorEntradaMenorPrestacaoParcelamento(
			String indicadorEntradaMenorPrestacaoParcelamento) {
		this.indicadorEntradaMenorPrestacaoParcelamento = indicadorEntradaMenorPrestacaoParcelamento;
	}

}


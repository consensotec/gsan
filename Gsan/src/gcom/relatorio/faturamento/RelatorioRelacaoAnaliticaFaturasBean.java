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
package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * Bean respons�vel de pegar os parametros que ser�o exibidos na parte de detail
 * do relat�rio.
 * 
 * @author Rafael Pinto
 * @created 27/08/2007
 */
public class RelatorioRelacaoAnaliticaFaturasBean implements RelatorioBean {
		
	private String codigoCliente;
	private String nomeCliente;
	
	private String matricula;
	private String inscricao;
	private String categoria;
	private String qtdEconomia;
	private String leituraAnterior;
	private String leituraAtual;
	private String media;
	private String consumoFaturado;
	private String dataLeitura;
	private String dataVencimento;
	private String consumoAgua;
	private String rateioAgua;
	private String valorAgua;
	private String consumoEsgoto;
	private String rateioEsgoto;
	private String valorEsgoto;
	private String debitoCobrado;
	private String creditoRealizado;
	private String totalConta;
	private String totalOrgaoPagador;
	private String endereco;
	private String valorImpostos;
	
	public RelatorioRelacaoAnaliticaFaturasBean(String codigoCliente, 
			String nomeCliente, 
			String matricula, 
			String inscricao, 
			String categoria,
			String qtdEconomia, 
			String leituraAnterior, 
			String leituraAtual, 
			String media, 
			String consumoFaturado, 
			String dataLeitura, 
			String dataVencimento, 
			String consumoAgua, 
			String rateioAgua, 
			String valorAgua, 
			String consumoEsgoto, 
			String rateioEsgoto, 
			String valorEsgoto, 
			String debitoCobrado, 
			String creditoRealizado, 
			String totalConta, 
			String totalOrgaoPagador,
			String endereco,
			String valorImpostos) {
		
		this.codigoCliente = codigoCliente;
		this.nomeCliente = nomeCliente;
		this.matricula = matricula;
		this.inscricao = inscricao;
		this.categoria = categoria;
		this.qtdEconomia = qtdEconomia;
		this.leituraAnterior = leituraAnterior;
		this.leituraAtual = leituraAtual;
		this.media = media;
		this.consumoFaturado = consumoFaturado;
		this.dataLeitura = dataLeitura;
		this.dataVencimento = dataVencimento;
		this.consumoAgua = consumoAgua;
		this.rateioAgua = rateioAgua;
		this.valorAgua = valorAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.rateioEsgoto = rateioEsgoto;
		this.valorEsgoto = valorEsgoto;
		this.debitoCobrado = debitoCobrado;
		this.creditoRealizado = creditoRealizado;
		this.totalConta = totalConta;
		this.totalOrgaoPagador = totalOrgaoPagador;
		this.endereco = endereco;
		this.valorImpostos = valorImpostos;
	}
	
	
	public String getValorImpostos() {
		return valorImpostos;
	}


	public void setValorImpostos(String valorImpostos) {
		this.valorImpostos = valorImpostos;
	}


	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getCodigoCliente() {
		return codigoCliente;
	}
	
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
	public String getConsumoAgua() {
		return consumoAgua;
	}
	
	public void setConsumoAgua(String consumoAgua) {
		this.consumoAgua = consumoAgua;
	}
	
	public String getConsumoEsgoto() {
		return consumoEsgoto;
	}
	
	public void setConsumoEsgoto(String consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}
	
	public String getConsumoFaturado() {
		return consumoFaturado;
	}
	
	public void setConsumoFaturado(String consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}
	
	public String getCreditoRealizado() {
		return creditoRealizado;
	}
	
	public void setCreditoRealizado(String creditoRealizado) {
		this.creditoRealizado = creditoRealizado;
	}
	
	public String getDataLeitura() {
		return dataLeitura;
	}
	
	public void setDataLeitura(String dataLeitura) {
		this.dataLeitura = dataLeitura;
	}
	
	public String getDataVencimento() {
		return dataVencimento;
	}
	
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public String getDebitoCobrado() {
		return debitoCobrado;
	}
	
	public void setDebitoCobrado(String debitoCobrado) {
		this.debitoCobrado = debitoCobrado;
	}
	
	public String getInscricao() {
		return inscricao;
	}
	
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	
	public String getLeituraAnterior() {
		return leituraAnterior;
	}
	
	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}
	
	public String getLeituraAtual() {
		return leituraAtual;
	}
	
	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getMedia() {
		return media;
	}
	
	public void setMedia(String media) {
		this.media = media;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public String getQtdEconomia() {
		return qtdEconomia;
	}
	
	public void setQtdEconomia(String qtdEconomia) {
		this.qtdEconomia = qtdEconomia;
	}
	
	public String getRateioAgua() {
		return rateioAgua;
	}
	
	public void setRateioAgua(String rateioAgua) {
		this.rateioAgua = rateioAgua;
	}
	
	public String getRateioEsgoto() {
		return rateioEsgoto;
	}
	
	public void setRateioEsgoto(String rateioEsgoto) {
		this.rateioEsgoto = rateioEsgoto;
	}
	
	public String getTotalConta() {
		return totalConta;
	}
	
	public void setTotalConta(String totalConta) {
		this.totalConta = totalConta;
	}
	
	public String getTotalOrgaoPagador() {
		return totalOrgaoPagador;
	}
	
	public void setTotalOrgaoPagador(String totalOrgaoPagador) {
		this.totalOrgaoPagador = totalOrgaoPagador;
	}
	
	public String getValorAgua() {
		return valorAgua;
	}
	
	public void setValorAgua(String valorAgua) {
		this.valorAgua = valorAgua;
	}
	
	public String getValorEsgoto() {
		return valorEsgoto;
	}
	
	public void setValorEsgoto(String valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
}
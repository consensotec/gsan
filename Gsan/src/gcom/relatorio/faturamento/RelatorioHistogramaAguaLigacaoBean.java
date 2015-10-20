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
 * @author S�vio Luiz
 * @created 8 de Julho de 2005
 */
public class RelatorioHistogramaAguaLigacaoBean implements RelatorioBean {

	private String opcaoTotalizacao;
	
	private String descricao;

	private String categoria;
	
	private String descricaoSubcategoria;

	private String numeroLigacoes;

	private String percentualParcialLigacao;

	private String percentualAcumuladoLigacao;
	
	private String economias;

	private String volumeMedido;

	private String volumeEstimado;

	private String volumeTotal;
	
	private String percentualParcialConsumo;

	private String percentualAcumuladoConsumo;
	
	private String mediaConsumo;

	private String valorFaturamento;
	
	private String percentualParcialFaturamento;

	private String percentualAcumuladoFaturamento;

	public RelatorioHistogramaAguaLigacaoBean(String opcaoTotalizacao, String descricao,
			String categoria, String descricaoSubcategoria, String numeroLigacoes,
			String percentualParcialLigacao, String percentualAcumuladoLigacao,
			String economias, String volumeMedido, String volumeEstimado,
			String volumeTotal, String percentualParcialConsumo,
			String percentualAcumuladoConsumo, String mediaConsumo,
			String valorFaturamento, String percentualParcialFaturamento,
			String percentualAcumuladoFaturamento) {
		this.opcaoTotalizacao = opcaoTotalizacao;
		this.descricao = descricao;
		this.categoria = categoria;
		this.descricaoSubcategoria = descricaoSubcategoria;
		this.numeroLigacoes = numeroLigacoes;
		this.percentualParcialLigacao = percentualParcialLigacao;
		this.percentualAcumuladoLigacao = percentualAcumuladoLigacao;
		this.economias = economias;
		this.volumeMedido = volumeMedido;
		this.volumeEstimado = volumeEstimado;
		this.volumeTotal = volumeTotal;
		this.percentualParcialConsumo = percentualParcialConsumo;
		this.percentualAcumuladoConsumo = percentualAcumuladoConsumo;
		this.mediaConsumo = mediaConsumo;
		this.valorFaturamento = valorFaturamento;
		this.percentualParcialFaturamento = percentualParcialFaturamento;
		this.percentualAcumuladoFaturamento = percentualAcumuladoFaturamento;
	}

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEconomias() {
		return economias;
	}

	public void setEconomias(String economias) {
		this.economias = economias;
	}

	public String getMediaConsumo() {
		return mediaConsumo;
	}

	public void setMediaConsumo(String mediaConsumo) {
		this.mediaConsumo = mediaConsumo;
	}

	public String getNumeroLigacoes() {
		return numeroLigacoes;
	}

	public void setNumeroLigacoes(String numeroLigacoes) {
		this.numeroLigacoes = numeroLigacoes;
	}

	public String getPercentualAcumuladoConsumo() {
		return percentualAcumuladoConsumo;
	}

	public void setPercentualAcumuladoConsumo(String percentualAcumuladoConsumo) {
		this.percentualAcumuladoConsumo = percentualAcumuladoConsumo;
	}

	public String getPercentualAcumuladoFaturamento() {
		return percentualAcumuladoFaturamento;
	}

	public void setPercentualAcumuladoFaturamento(
			String percentualAcumuladoFaturamento) {
		this.percentualAcumuladoFaturamento = percentualAcumuladoFaturamento;
	}

	public String getPercentualAcumuladoLigacao() {
		return percentualAcumuladoLigacao;
	}

	public void setPercentualAcumuladoLigacao(String percentualAcumuladoLigacao) {
		this.percentualAcumuladoLigacao = percentualAcumuladoLigacao;
	}

	public String getPercentualParcialConsumo() {
		return percentualParcialConsumo;
	}

	public void setPercentualParcialConsumo(String percentualParcialConsumo) {
		this.percentualParcialConsumo = percentualParcialConsumo;
	}

	public String getPercentualParcialFaturamento() {
		return percentualParcialFaturamento;
	}

	public void setPercentualParcialFaturamento(String percentualParcialFaturamento) {
		this.percentualParcialFaturamento = percentualParcialFaturamento;
	}

	public String getPercentualParcialLigacao() {
		return percentualParcialLigacao;
	}

	public void setPercentualParcialLigacao(String percentualParcialLigacao) {
		this.percentualParcialLigacao = percentualParcialLigacao;
	}

	public String getValorFaturamento() {
		return valorFaturamento;
	}

	public void setValorFaturamento(String valorFaturamento) {
		this.valorFaturamento = valorFaturamento;
	}

	public String getVolumeEstimado() {
		return volumeEstimado;
	}

	public void setVolumeEstimado(String volumeEstimado) {
		this.volumeEstimado = volumeEstimado;
	}

	public String getVolumeMedido() {
		return volumeMedido;
	}

	public void setVolumeMedido(String volumeMedido) {
		this.volumeMedido = volumeMedido;
	}

	public String getVolumeTotal() {
		return volumeTotal;
	}

	public void setVolumeTotal(String volumeTotal) {
		this.volumeTotal = volumeTotal;
	}

	public String getDescricaoSubcategoria() {
		return descricaoSubcategoria;
	}

	public void setDescricaoSubcategoria(String descricaoSubcategoria) {
		this.descricaoSubcategoria = descricaoSubcategoria;
	}


}

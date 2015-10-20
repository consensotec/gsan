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

import java.util.ArrayList;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC0532] Gerar Relat�rio de Faturamento das Liga��es com Medi��o Individualizada
 * 
 * @author Vivianne Sousa
 * @date 09/01/2007
 */
public class RelatorioFaturamentoLigacoesMedicaoIndividualizadaBean implements RelatorioBean {
	
	private String idLocalidade;
	private String nomeLocalidade; 
	private String matriculaImovel;
	private String inscricao;
	private String nomeConsumidor;
	private String qtdeEconomias;
	private String leituraAnterior;
	private String dataLeituraAnterior;
	private String leituraAtual;
	private String dataLeituraAtual;
	private String media;
	private String consumoImoveisVinculados;
	private String consumoFaturado;
	private String rateio;
	private String consumoEsgoto;
	private String anormalidade;
	private String anormalidadeConsumo;
	private String tipoConsumo;
	private String indicadorPoco;

	private String indicadorQuebraImovelCondominio;
	private String totalConsumidoresRateioMacromedidor;
	private String numeroEconomiasRateio;
	
	
	 public RelatorioFaturamentoLigacoesMedicaoIndividualizadaBean(
			 String idLocalidade,
			 String nomeLocalidade, 
			 String matriculaImovel,
			 String inscricao,
			 String nomeConsumidor,
			 String qtdeEconomias,
			 String leituraAnterior,
			 String dataLeituraAnterior,
			 String leituraAtual,
			 String dataLeituraAtual,
			 String media,
			 String consumoImoveisVinculados,
			 String consumoFaturado,
			 String rateio,
			 String consumoEsgoto,
			 String anormalidade,
			 String anormalidadeConsumo,
			 String tipoConsumo,
			 String indicadorPoco,
			 String indicadorQuebraImovelCondominio,
			 String totalConsumidoresRateioMacromedidor,
			 String numeroEconomiasRateio){
		 
		 
		 this.idLocalidade = idLocalidade;
		 this.nomeLocalidade = nomeLocalidade; 
		 this.matriculaImovel = matriculaImovel;
		 this.inscricao = inscricao;
		 this.nomeConsumidor = nomeConsumidor;
		 this.qtdeEconomias = qtdeEconomias;
		 this.leituraAnterior = leituraAnterior;
		 this.dataLeituraAnterior = dataLeituraAnterior;
		 this.leituraAtual = leituraAtual;
		 this.dataLeituraAtual = dataLeituraAtual;
		 this.media = media;
		 this.consumoImoveisVinculados = consumoImoveisVinculados;
		 this.consumoFaturado = consumoFaturado;
		 this.rateio = rateio;
		 this.consumoEsgoto = consumoEsgoto;
		 this.anormalidade = anormalidade;
		 this.anormalidadeConsumo = anormalidadeConsumo;
		 this.tipoConsumo = tipoConsumo;
		 this.indicadorPoco = indicadorPoco; 
		 this.indicadorQuebraImovelCondominio = indicadorQuebraImovelCondominio;
		 this.totalConsumidoresRateioMacromedidor = totalConsumidoresRateioMacromedidor;
		 this.numeroEconomiasRateio = numeroEconomiasRateio;
		 
	 }
		 
		
	
	private String indicadorPrimeiraPagina;
	
	private JRBeanCollectionDataSource arrayJRDetail;

	private ArrayList arrayRelatorio2ViaContaDetailBean;


	public String getAnormalidade() {
		return anormalidade;
	}

	public void setAnormalidade(String anormalidade) {
		this.anormalidade = anormalidade;
	}

	public String getAnormalidadeConsumo() {
		return anormalidadeConsumo;
	}

	public void setAnormalidadeConsumo(String anormalidadeConsumo) {
		this.anormalidadeConsumo = anormalidadeConsumo;
	}

	public JRBeanCollectionDataSource getArrayJRDetail() {
		return arrayJRDetail;
	}

	public void setArrayJRDetail(JRBeanCollectionDataSource arrayJRDetail) {
		this.arrayJRDetail = arrayJRDetail;
	}

	public ArrayList getArrayRelatorio2ViaContaDetailBean() {
		return arrayRelatorio2ViaContaDetailBean;
	}

	public void setArrayRelatorio2ViaContaDetailBean(
			ArrayList arrayRelatorio2ViaContaDetailBean) {
		this.arrayRelatorio2ViaContaDetailBean = arrayRelatorio2ViaContaDetailBean;
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

	public String getConsumoImoveisVinculados() {
		return consumoImoveisVinculados;
	}

	public void setConsumoImoveisVinculados(String consumoImoveisVinculados) {
		this.consumoImoveisVinculados = consumoImoveisVinculados;
	}

	public String getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(String dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public String getDataLeituraAtual() {
		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(String dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIndicadorPoco() {
		return indicadorPoco;
	}

	public void setIndicadorPoco(String indicadorPoco) {
		this.indicadorPoco = indicadorPoco;
	}

	public String getIndicadorPrimeiraPagina() {
		return indicadorPrimeiraPagina;
	}

	public void setIndicadorPrimeiraPagina(String indicadorPrimeiraPagina) {
		this.indicadorPrimeiraPagina = indicadorPrimeiraPagina;
	}

	public String getIndicadorQuebraImovelCondominio() {
		return indicadorQuebraImovelCondominio;
	}

	public void setIndicadorQuebraImovelCondominio(
			String indicadorQuebraImovelCondominio) {
		this.indicadorQuebraImovelCondominio = indicadorQuebraImovelCondominio;
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

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getNomeConsumidor() {
		return nomeConsumidor;
	}

	public void setNomeConsumidor(String nomeConsumidor) {
		this.nomeConsumidor = nomeConsumidor;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNumeroEconomiasRateio() {
		return numeroEconomiasRateio;
	}

	public void setNumeroEconomiasRateio(String numeroEconomiasRateio) {
		this.numeroEconomiasRateio = numeroEconomiasRateio;
	}

	public String getQtdeEconomias() {
		return qtdeEconomias;
	}

	public void setQtdeEconomias(String qtdeEconomias) {
		this.qtdeEconomias = qtdeEconomias;
	}

	public String getRateio() {
		return rateio;
	}

	public void setRateio(String rateio) {
		this.rateio = rateio;
	}

	public String getTipoConsumo() {
		return tipoConsumo;
	}

	public void setTipoConsumo(String tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}

	public String getTotalConsumidoresRateioMacromedidor() {
		return totalConsumidoresRateioMacromedidor;
	}

	public void setTotalConsumidoresRateioMacromedidor(
			String totalConsumidoresRateioMacromedidor) {
		this.totalConsumidoresRateioMacromedidor = totalConsumidoresRateioMacromedidor;
	}
	
	


}

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
package gcom.faturamento.bean;

import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.util.ConstantesSistema;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * Retornar valores para faturamento de �gua e esgoto
 *
 * @author Raphael Rossiter
 * 
 * @date 27/03/2008
 */
public class DeterminarValoresFaturamentoAguaEsgotoHelper {

	private Short indicadorFaturamentoAgua;
	
	private Integer consumoFaturadoAgua;
	
	private Short indicadorFaturamentoEsgoto;
	
	private Integer consumoFaturadoEsgoto;
	
	private int consumoMinimoLigacao;
	
	private Date dataLeituraAnterior;
	
	private Date dataLeituraAtual;
	
	private BigDecimal percentualEsgoto;
	
	private BigDecimal valorTotalAgua;
	
	private BigDecimal valorTotalEsgoto;
	
	private Integer consumoRateioAgua;
	
	private Integer consumoRateioEsgoto;
	
	private ConsumoHistorico consumoHistoricoAgua;
	
	private ConsumoHistorico consumoHistoricoEsgoto;
	
	private Collection colecaoCalcularValoresAguaEsgotoHelper;
	
	private ConsumoTipo consumoTipoAgua;
	
	private ConsumoTipo consumoTipoEsgoto;
	
	private BigDecimal percentualColetaEsgoto;
	
	public DeterminarValoresFaturamentoAguaEsgotoHelper(){
		
		this.consumoFaturadoAgua = 0;
		this.indicadorFaturamentoAgua = ConstantesSistema.SIM;
		this.consumoRateioAgua = 0;
		this.valorTotalAgua = BigDecimal.ZERO;
		
		this.consumoFaturadoEsgoto = 0;
		this.indicadorFaturamentoEsgoto = ConstantesSistema.SIM;
		this.consumoRateioEsgoto = 0;
		this.valorTotalEsgoto = BigDecimal.ZERO;
		this.percentualEsgoto = BigDecimal.ZERO;
		this.percentualColetaEsgoto = BigDecimal.ZERO;
	}

	public Integer getConsumoFaturadoAgua() {
		return consumoFaturadoAgua;
	}

	public void setConsumoFaturadoAgua(Integer consumoFaturadoAgua) {
		this.consumoFaturadoAgua = consumoFaturadoAgua;
	}

	public Integer getConsumoFaturadoEsgoto() {
		return consumoFaturadoEsgoto;
	}

	public void setConsumoFaturadoEsgoto(Integer consumoFaturadoEsgoto) {
		this.consumoFaturadoEsgoto = consumoFaturadoEsgoto;
	}

	public Short getIndicadorFaturamentoAgua() {
		return indicadorFaturamentoAgua;
	}

	public void setIndicadorFaturamentoAgua(Short indicadorFaturamentoAgua) {
		this.indicadorFaturamentoAgua = indicadorFaturamentoAgua;
	}

	public Short getIndicadorFaturamentoEsgoto() {
		return indicadorFaturamentoEsgoto;
	}

	public void setIndicadorFaturamentoEsgoto(Short indicadorFaturamentoEsgoto) {
		this.indicadorFaturamentoEsgoto = indicadorFaturamentoEsgoto;
	}

	public int getConsumoMinimoLigacao() {
		return consumoMinimoLigacao;
	}

	public void setConsumoMinimoLigacao(int consumoMinimoLigacao) {
		this.consumoMinimoLigacao = consumoMinimoLigacao;
	}

	public Date getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(Date dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public Date getDataLeituraAtual() {
		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(Date dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}

	public BigDecimal getPercentualEsgoto() {
		return percentualEsgoto;
	}

	public void setPercentualEsgoto(BigDecimal percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	public BigDecimal getValorTotalAgua() {
		return valorTotalAgua;
	}

	public void setValorTotalAgua(BigDecimal valorTotalAgua) {
		this.valorTotalAgua = valorTotalAgua;
	}

	public BigDecimal getValorTotalEsgoto() {
		return valorTotalEsgoto;
	}

	public void setValorTotalEsgoto(BigDecimal valorTotalEsgoto) {
		this.valorTotalEsgoto = valorTotalEsgoto;
	}

	public ConsumoHistorico getConsumoHistoricoAgua() {
		return consumoHistoricoAgua;
	}

	public void setConsumoHistoricoAgua(ConsumoHistorico consumoHistoricoAgua) {
		this.consumoHistoricoAgua = consumoHistoricoAgua;
	}

	public ConsumoHistorico getConsumoHistoricoEsgoto() {
		return consumoHistoricoEsgoto;
	}

	public void setConsumoHistoricoEsgoto(ConsumoHistorico consumoHistoricoEsgoto) {
		this.consumoHistoricoEsgoto = consumoHistoricoEsgoto;
	}

	public Integer getConsumoRateioAgua() {
		return consumoRateioAgua;
	}

	public void setConsumoRateioAgua(Integer consumoRateioAgua) {
		this.consumoRateioAgua = consumoRateioAgua;
	}

	public Integer getConsumoRateioEsgoto() {
		return consumoRateioEsgoto;
	}

	public void setConsumoRateioEsgoto(Integer consumoRateioEsgoto) {
		this.consumoRateioEsgoto = consumoRateioEsgoto;
	}

	public Collection getColecaoCalcularValoresAguaEsgotoHelper() {
		return colecaoCalcularValoresAguaEsgotoHelper;
	}

	public void setColecaoCalcularValoresAguaEsgotoHelper(
			Collection colecaoCalcularValoresAguaEsgotoHelper) {
		this.colecaoCalcularValoresAguaEsgotoHelper = colecaoCalcularValoresAguaEsgotoHelper;
	}

	public ConsumoTipo getConsumoTipoAgua() {
		return consumoTipoAgua;
	}

	public void setConsumoTipoAgua(ConsumoTipo consumoTipoAgua) {
		this.consumoTipoAgua = consumoTipoAgua;
	}

	public ConsumoTipo getConsumoTipoEsgoto() {
		return consumoTipoEsgoto;
	}

	public void setConsumoTipoEsgoto(ConsumoTipo consumoTipoEsgoto) {
		this.consumoTipoEsgoto = consumoTipoEsgoto;
	}

	public BigDecimal getPercentualColetaEsgoto() {
		return percentualColetaEsgoto;
	}

	public void setPercentualColetaEsgoto(BigDecimal percentualColetaEsgoto) {
		this.percentualColetaEsgoto = percentualColetaEsgoto;
	}
}

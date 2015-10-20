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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/** @author Hibernate CodeGenerator */
public class CalcularValoresAguaEsgotoHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idCategoria;
	
	private String descricaoCategoria;
	
	private Integer quantidadeEconomiasCategoria;
	
	private Integer idConsumoTarifaCategoria;

	private BigDecimal valorFaturadoAguaCategoria;

	private Integer consumoFaturadoAguaCategoria;

	private BigDecimal valorFaturadoEsgotoCategoria;

	private Integer consumoFaturadoEsgotoCategoria;

	private BigDecimal valorTarifaMinimaAguaCategoria;

	private Integer consumoMinimoAguaCategoria;

	private BigDecimal valorTarifaMinimaEsgotoCategoria;

	private Integer consumoMinimoEsgotoCategoria;

	private Collection<CalcularValoresAguaEsgotoFaixaHelper> faixaTarifaConsumo;

	public CalcularValoresAguaEsgotoHelper() {
	}
	
	public CalcularValoresAguaEsgotoHelper(Integer idCategoria, Integer idConsumoTarifaCategoria, BigDecimal valorFaturadoAguaCategoria, Integer consumoFaturadoAguaCategoria, BigDecimal valorFaturadoEsgotoCategoria, Integer consumoFaturadoEsgotoCategoria, BigDecimal valorTarifaMinimaAguaCategoria, Integer consumoMinimoAguaCategoria, BigDecimal valorTarifaMinimaEsgotoCategoria, Integer consumoMinimoEsgotoCategoria, Collection<CalcularValoresAguaEsgotoFaixaHelper> faixaTarifaConsumo) {
		this.idCategoria = idCategoria;
		this.idConsumoTarifaCategoria = idConsumoTarifaCategoria;
		this.valorFaturadoAguaCategoria = valorFaturadoAguaCategoria;
		this.consumoFaturadoAguaCategoria = consumoFaturadoAguaCategoria;
		this.valorFaturadoEsgotoCategoria = valorFaturadoEsgotoCategoria;
		this.consumoFaturadoEsgotoCategoria = consumoFaturadoEsgotoCategoria;
		this.valorTarifaMinimaAguaCategoria = valorTarifaMinimaAguaCategoria;
		this.consumoMinimoAguaCategoria = consumoMinimoAguaCategoria;
		this.valorTarifaMinimaEsgotoCategoria = valorTarifaMinimaEsgotoCategoria;
		this.consumoMinimoEsgotoCategoria = consumoMinimoEsgotoCategoria;
		this.faixaTarifaConsumo = faixaTarifaConsumo;
	}

	public Integer getConsumoFaturadoAguaCategoria() {
		return consumoFaturadoAguaCategoria;
	}

	public void setConsumoFaturadoAguaCategoria(Integer consumoFaturadoAguaCategoria) {
		this.consumoFaturadoAguaCategoria = consumoFaturadoAguaCategoria;
	}

	public Integer getConsumoFaturadoEsgotoCategoria() {
		return consumoFaturadoEsgotoCategoria;
	}

	public void setConsumoFaturadoEsgotoCategoria(
			Integer consumoFaturadoEsgotoCategoria) {
		this.consumoFaturadoEsgotoCategoria = consumoFaturadoEsgotoCategoria;
	}

	public Integer getConsumoMinimoAguaCategoria() {
		return consumoMinimoAguaCategoria;
	}

	public void setConsumoMinimoAguaCategoria(Integer consumoMinimoAguaCategoria) {
		this.consumoMinimoAguaCategoria = consumoMinimoAguaCategoria;
	}

	public Integer getConsumoMinimoEsgotoCategoria() {
		return consumoMinimoEsgotoCategoria;
	}

	public void setConsumoMinimoEsgotoCategoria(Integer consumoMinimoEsgotoCategoria) {
		this.consumoMinimoEsgotoCategoria = consumoMinimoEsgotoCategoria;
	}

	public Collection<CalcularValoresAguaEsgotoFaixaHelper> getFaixaTarifaConsumo() {
		return faixaTarifaConsumo;
	}

	public void setFaixaTarifaConsumo(
			Collection<CalcularValoresAguaEsgotoFaixaHelper> faixaTarifaConsumo) {
		this.faixaTarifaConsumo = faixaTarifaConsumo;
	}

	public BigDecimal getValorFaturadoAguaCategoria() {
		return valorFaturadoAguaCategoria;
	}

	public void setValorFaturadoAguaCategoria(BigDecimal valorFaturadoAguaCategoria) {
		this.valorFaturadoAguaCategoria = valorFaturadoAguaCategoria;
	}

	public BigDecimal getValorFaturadoEsgotoCategoria() {
		return valorFaturadoEsgotoCategoria;
	}

	public void setValorFaturadoEsgotoCategoria(
			BigDecimal valorFaturadoEsgotoCategoria) {
		this.valorFaturadoEsgotoCategoria = valorFaturadoEsgotoCategoria;
	}

	public BigDecimal getValorTarifaMinimaAguaCategoria() {
		return valorTarifaMinimaAguaCategoria;
	}

	public void setValorTarifaMinimaAguaCategoria(
			BigDecimal valorTarifaMinimaAguaCategoria) {
		this.valorTarifaMinimaAguaCategoria = valorTarifaMinimaAguaCategoria;
	}

	public BigDecimal getValorTarifaMinimaEsgotoCategoria() {
		return valorTarifaMinimaEsgotoCategoria;
	}

	public void setValorTarifaMinimaEsgotoCategoria(
			BigDecimal valorTarifaMinimaEsgotoCategoria) {
		this.valorTarifaMinimaEsgotoCategoria = valorTarifaMinimaEsgotoCategoria;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdConsumoTarifaCategoria() {
		return idConsumoTarifaCategoria;
	}

	public void setIdConsumoTarifaCategoria(Integer idConsumoTarifaCategoria) {
		this.idConsumoTarifaCategoria = idConsumoTarifaCategoria;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}
	
	public BigDecimal getValorTotalCategoria(){
		
		BigDecimal retorno = new BigDecimal("0.00");
		
		if (this.valorFaturadoAguaCategoria != null){
			retorno = retorno.add(this.valorFaturadoAguaCategoria);
		}

		if (this.valorFaturadoEsgotoCategoria != null){
			retorno = retorno.add(this.valorFaturadoEsgotoCategoria);
		}
		
		retorno.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return retorno;
	}

	public Integer getQuantidadeEconomiasCategoria() {
		return quantidadeEconomiasCategoria;
	}

	public void setQuantidadeEconomiasCategoria(Integer quantidadeEconomiasCategoria) {
		this.quantidadeEconomiasCategoria = quantidadeEconomiasCategoria;
	}

}

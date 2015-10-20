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

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Classe que ir� auxiliar no formato de entrada do relat�rio 
 * de emitir histograma de agua por economia
 *
 * @author Rafael Pinto
 * 
 * @date 14/06/2007
 */
public class EmitirHistogramaAguaEconomiaHelper {
	
	private static final long serialVersionUID = 1L;
	
	private String descricaoTarifa = null;
	private String opcaoTotalizacao = null;
	private String descricaoFaixa = null;
	private String descricaoCategoria = null;
	private String descricaoSubcategoria = null;
	
	//Parte com Hidrometro
	private Integer totalEconomiasMedido = null;
	private Integer totalVolumeConsumoMedido = null;
	private Integer totalVolumeFaturadoMedido = null; 
	private BigDecimal totalReceitaMedido = null;
	private Integer totalLigacoesMedido = null;
	
	//Parte sem Hidrometro
	private Integer totalEconomiasNaoMedido = null;
	private Integer totalVolumeConsumoNaoMedido = null;
	private Integer totalVolumeFaturadoNaoMedido = null; 
	private BigDecimal totalReceitaNaoMedido = null;
	private Integer totalLigacoesNaoMedido  = null;
	
	private Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;

	public EmitirHistogramaAguaEconomiaHelper() { }

	public String getDescricaoFaixa() {
		return descricaoFaixa;
	}

	public void setDescricaoFaixa(String descricaoFaixa) {
		this.descricaoFaixa = descricaoFaixa;
	}

	public Collection<EmitirHistogramaAguaEconomiaDetalheHelper> getColecaoEmitirHistogramaAguaEconomiaDetalhe() {
		return colecaoEmitirHistogramaAguaEconomiaDetalhe;
	}

	public void setColecaoEmitirHistogramaAguaEconomiaDetalhe(
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe) {
		this.colecaoEmitirHistogramaAguaEconomiaDetalhe = colecaoEmitirHistogramaAguaEconomiaDetalhe;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public Integer getTotalEconomiasMedido() {
		return totalEconomiasMedido;
	}

	public void setTotalEconomiasMedido(Integer totalEconomiasMedido) {
		this.totalEconomiasMedido = totalEconomiasMedido;
	}

	public Integer getTotalEconomiasNaoMedido() {
		return totalEconomiasNaoMedido;
	}

	public void setTotalEconomiasNaoMedido(Integer totalEconomiasNaoMedido) {
		this.totalEconomiasNaoMedido = totalEconomiasNaoMedido;
	}

	public BigDecimal getTotalReceitaMedido() {
		return totalReceitaMedido;
	}

	public void setTotalReceitaMedido(BigDecimal totalReceitaMedido) {
		this.totalReceitaMedido = totalReceitaMedido;
	}

	public BigDecimal getTotalReceitaNaoMedido() {
		return totalReceitaNaoMedido;
	}

	public void setTotalReceitaNaoMedido(BigDecimal totalReceitaNaoMedido) {
		this.totalReceitaNaoMedido = totalReceitaNaoMedido;
	}

	public Integer getTotalVolumeConsumoMedido() {
		return totalVolumeConsumoMedido;
	}

	public void setTotalVolumeConsumoMedido(Integer totalVolumeConsumoMedido) {
		this.totalVolumeConsumoMedido = totalVolumeConsumoMedido;
	}

	public int getTotalVolumeConsumoNaoMedido() {
		return totalVolumeConsumoNaoMedido;
	}

	public void setTotalVolumeConsumoNaoMedido(Integer totalVolumeConsumoNaoMedido) {
		this.totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido;
	}

	public int getTotalVolumeFaturadoMedido() {
		return totalVolumeFaturadoMedido;
	}

	public void setTotalVolumeFaturadoMedido(Integer totalVolumeFaturadoMedido) {
		this.totalVolumeFaturadoMedido = totalVolumeFaturadoMedido;
	}

	public Integer getTotalVolumeFaturadoNaoMedido() {
		return totalVolumeFaturadoNaoMedido;
	}

	public void setTotalVolumeFaturadoNaoMedido(Integer totalVolumeFaturadoNaoMedido) {
		this.totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido;
	}

	public String getDescricaoTarifa() {
		return descricaoTarifa;
	}

	public void setDescricaoTarifa(String descricaoTarifa) {
		this.descricaoTarifa = descricaoTarifa;
	}
	
	public Integer getTotalLigacoesMedido() {
		return totalLigacoesMedido;
	}

	public void setTotalLigacoesMedido(Integer totalLigacoesMedido) {
		this.totalLigacoesMedido = totalLigacoesMedido;
	}

	public Integer getTotalLigacoesNaoMedido() {
		return totalLigacoesNaoMedido;
	}

	public void setTotalLigacoesNaoMedido(Integer totalLigacoesNaoMedido) {
		this.totalLigacoesNaoMedido = totalLigacoesNaoMedido;
	}

	public String getDescricaoSubcategoria() {
		return descricaoSubcategoria;
	}

	public void setDescricaoSubcategoria(String descricaoSubcategoria) {
		this.descricaoSubcategoria = descricaoSubcategoria;
	}
	
}
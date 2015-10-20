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

import java.math.BigDecimal;
import java.util.Date;

public class ConsumoTarifaRelatorioHelper {
	
	private Integer idConsumoTarifa;
	private String descricaoConsumoTarifa;
	private Date dataValidadeInicial;
	private String categoria;
	private Integer faixaInicial;
	private Integer faixaFinal;
	private BigDecimal custo;
	private BigDecimal tarifaMinima;
	private Integer consumoMinimo;
	
	/**
	 * @return Retorna o campo consumoMinimo.
	 */
	public Integer getConsumoMinimo() {
		return consumoMinimo;
	}
	/**
	 * @param consumoMinimo O consumoMinimo a ser setado.
	 */
	public void setConsumoMinimo(Integer consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}
	/**
	 * @return Retorna o campo categoria.
	 */
	public String getCategoria() {
		return categoria;
	}
	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	/**
	 * @return Retorna o campo custo.
	 */
	public BigDecimal getCusto() {
		return custo;
	}
	/**
	 * @param custo O custo a ser setado.
	 */
	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}
	/**
	 * @return Retorna o campo dataValidadeInicial.
	 */
	public Date getDataValidadeInicial() {
		return dataValidadeInicial;
	}
	/**
	 * @param dataValidadeInicial O dataValidadeInicial a ser setado.
	 */
	public void setDataValidadeInicial(Date dataValidadeInicial) {
		this.dataValidadeInicial = dataValidadeInicial;
	}
	/**
	 * @return Retorna o campo descricaoConsumoTarifa.
	 */
	public String getDescricaoConsumoTarifa() {
		return descricaoConsumoTarifa;
	}
	/**
	 * @param descricaoConsumoTarifa O descricaoConsumoTarifa a ser setado.
	 */
	public void setDescricaoConsumoTarifa(String descricaoConsumoTarifa) {
		this.descricaoConsumoTarifa = descricaoConsumoTarifa;
	}
	/**
	 * @return Retorna o campo faixaFinal.
	 */
	public Integer getFaixaFinal() {
		return faixaFinal;
	}
	/**
	 * @param faixaFinal O faixaFinal a ser setado.
	 */
	public void setFaixaFinal(Integer faixaFinal) {
		this.faixaFinal = faixaFinal;
	}
	/**
	 * @return Retorna o campo faixaInicial.
	 */
	public Integer getFaixaInicial() {
		return faixaInicial;
	}
	/**
	 * @param faixaInicial O faixaInicial a ser setado.
	 */
	public void setFaixaInicial(Integer faixaInicial) {
		this.faixaInicial = faixaInicial;
	}
	/**
	 * @return Retorna o campo idConsumoTarifa.
	 */
	public Integer getIdConsumoTarifa() {
		return idConsumoTarifa;
	}
	/**
	 * @param idConsumoTarifa O idConsumoTarifa a ser setado.
	 */
	public void setIdConsumoTarifa(Integer idConsumoTarifa) {
		this.idConsumoTarifa = idConsumoTarifa;
	}
	/**
	 * @return Retorna o campo tarifaMinima.
	 */
	public BigDecimal getTarifaMinima() {
		return tarifaMinima;
	}
	/**
	 * @param tarifaMinima O tarifaMinima a ser setado.
	 */
	public void setTarifaMinima(BigDecimal tarifaMinima) {
		this.tarifaMinima = tarifaMinima;
	}
	
	

}

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
package gsan.gerencial.cobranca;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper {

	private Collection<ResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper> resumoCobrancaSituacaoEspecialConsultaLocalidadeHelper;

	private Integer idUnidadeNegocio;
	
	private String unidadeNegocioDescricaoAbreviada;
	
	private String unidadeNegocioDescricao;

	private Integer totalUnidadeNegocio;
	
	private BigDecimal totalFatEstimadoUnidadeNegocio;
	
	private Integer totalQtLigacoesUnidadeNegocio;
	
	private BigDecimal totalPercentualUnidadeNegocio;

	public Collection<ResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper> getResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper() {
		return resumoCobrancaSituacaoEspecialConsultaLocalidadeHelper;
	}

	public void setResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper(
			Collection<ResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper> resumoCobrancaSituacaoEspecialConsultaLocalidadeHelper) {
		this.resumoCobrancaSituacaoEspecialConsultaLocalidadeHelper = resumoCobrancaSituacaoEspecialConsultaLocalidadeHelper;
	}

	public ResumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper() {}
	
	public ResumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper(Integer idUnidadeNegocio, String unidadeNegocioDescricaoAbreviada, String unidadeNegocioDescricao, Integer totalUnidadeNegocio) {
		super();
		// TODO Auto-generated constructor stub
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.unidadeNegocioDescricaoAbreviada = unidadeNegocioDescricaoAbreviada;
		this.unidadeNegocioDescricao = unidadeNegocioDescricao;
		this.totalUnidadeNegocio = totalUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo totalFatEstimadoUnidadeNegocio.
	 */
	public BigDecimal getTotalFatEstimadoUnidadeNegocio() {
		return totalFatEstimadoUnidadeNegocio;
	}

	/**
	 * @param totalFatEstimadoUnidadeNegocio O totalFatEstimadoUnidadeNegocio a ser setado.
	 */
	public void setTotalFatEstimadoUnidadeNegocio(
			BigDecimal totalFatEstimadoUnidadeNegocio) {
		this.totalFatEstimadoUnidadeNegocio = totalFatEstimadoUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo totalPercentualUnidadeNegocio.
	 */
	public BigDecimal getTotalPercentualUnidadeNegocio() {
		return totalPercentualUnidadeNegocio;
	}

	/**
	 * @param totalPercentualUnidadeNegocio O totalPercentualUnidadeNegocio a ser setado.
	 */
	public void setTotalPercentualUnidadeNegocio(
			BigDecimal totalPercentualUnidadeNegocio) {
		this.totalPercentualUnidadeNegocio = totalPercentualUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo totalQtLigacoesUnidadeNegocio.
	 */
	public Integer getTotalQtLigacoesUnidadeNegocio() {
		return totalQtLigacoesUnidadeNegocio;
	}

	/**
	 * @param totalQtLigacoesUnidadeNegocio O totalQtLigacoesUnidadeNegocio a ser setado.
	 */
	public void setTotalQtLigacoesUnidadeNegocio(
			Integer totalQtLigacoesUnidadeNegocio) {
		this.totalQtLigacoesUnidadeNegocio = totalQtLigacoesUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo totalUnidadeNegocio.
	 */
	public Integer getTotalUnidadeNegocio() {
		return totalUnidadeNegocio;
	}

	/**
	 * @param totalUnidadeNegocio O totalUnidadeNegocio a ser setado.
	 */
	public void setTotalUnidadeNegocio(Integer totalUnidadeNegocio) {
		this.totalUnidadeNegocio = totalUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo unidadeNegocioDescricao.
	 */
	public String getUnidadeNegocioDescricao() {
		return unidadeNegocioDescricao;
	}

	/**
	 * @param unidadeNegocioDescricao O unidadeNegocioDescricao a ser setado.
	 */
	public void setUnidadeNegocioDescricao(String unidadeNegocioDescricao) {
		this.unidadeNegocioDescricao = unidadeNegocioDescricao;
	}

	/**
	 * @return Retorna o campo unidadeNegocioDescricaoAbreviada.
	 */
	public String getUnidadeNegocioDescricaoAbreviada() {
		return unidadeNegocioDescricaoAbreviada;
	}

	/**
	 * @param unidadeNegocioDescricaoAbreviada O unidadeNegocioDescricaoAbreviada a ser setado.
	 */
	public void setUnidadeNegocioDescricaoAbreviada(
			String unidadeNegocioDescricaoAbreviada) {
		this.unidadeNegocioDescricaoAbreviada = unidadeNegocioDescricaoAbreviada;
	}
	
	

}
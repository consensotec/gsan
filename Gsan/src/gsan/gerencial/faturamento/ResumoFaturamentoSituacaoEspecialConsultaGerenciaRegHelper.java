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
package gsan.gerencial.faturamento;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper {

	private Collection<ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper> resumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper;

	private Integer idGerenciaRegional;
	
	private String gerenciaRegionalDescricaoAbreviada;
	
	private String gerenciaRegionalDescricao;

	private Integer totalGerenciaRegional;
	
	private BigDecimal totalPercentualGerencia;
	private BigDecimal totalFatEstimadoGerencia;
	private Integer totalQtLigacoesGerencia;

	public String getGerenciaRegionalDescricao() {
		return gerenciaRegionalDescricao;
	}

	public void setGerenciaRegionalDescricao(String gerenciaRegionalDescricao) {
		this.gerenciaRegionalDescricao = gerenciaRegionalDescricao;
	}

	public String getGerenciaRegionalDescricaoAbreviada() {
		return gerenciaRegionalDescricaoAbreviada;
	}

	public void setGerenciaRegionalDescricaoAbreviada(
			String gerenciaRegionalDescricaoAbreviada) {
		this.gerenciaRegionalDescricaoAbreviada = gerenciaRegionalDescricaoAbreviada;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public Collection<ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper> getResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper() {
		return resumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper;
	}

	public void setResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper(
			Collection<ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper> resumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper) {
		this.resumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper = resumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper;
	}

	public Integer getTotalGerenciaRegional() {
		return totalGerenciaRegional;
	}

	public void setTotalGerenciaRegional(Integer totalGerenciaRegional) {
		this.totalGerenciaRegional = totalGerenciaRegional;
	}

	public ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper() {}
	
	public ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper(Integer idGerenciaRegional, String gerenciaRegionalDescricaoAbreviada, String gerenciaRegionalDescricao, Integer totalGerenciaRegional) {
		super();
		// TODO Auto-generated constructor stub
		this.idGerenciaRegional = idGerenciaRegional;
		this.gerenciaRegionalDescricaoAbreviada = gerenciaRegionalDescricaoAbreviada;
		this.gerenciaRegionalDescricao = gerenciaRegionalDescricao;
		this.totalGerenciaRegional = totalGerenciaRegional;
	}

	public BigDecimal getTotalPercentualGerencia() {
		return totalPercentualGerencia;
	}

	public void setTotalPercentualGerencia(BigDecimal totalPercentualGerencia) {
		this.totalPercentualGerencia = totalPercentualGerencia;
	}

	public BigDecimal getTotalFatEstimadoGerencia() {
		return totalFatEstimadoGerencia;
	}

	public void setTotalFatEstimadoGerencia(BigDecimal totalFatEstimadoGerencia) {
		this.totalFatEstimadoGerencia = totalFatEstimadoGerencia;
	}

	public Integer getTotalQtLigacoesGerencia() {
		return totalQtLigacoesGerencia;
	}

	public void setTotalQtLigacoesGerencia(Integer totalQtLigacoesGerencia) {
		this.totalQtLigacoesGerencia = totalQtLigacoesGerencia;
	}

	
}
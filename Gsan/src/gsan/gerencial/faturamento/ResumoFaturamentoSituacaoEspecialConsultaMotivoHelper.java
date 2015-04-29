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

import gsan.gerencial.faturamento.bean.ResumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper;

import java.math.BigDecimal;
import java.util.Collection;

/** 
 *
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper {

	private Integer idMotivo;
	private String motivoDescricao;
	private Integer anoMesInicio;
	private Integer anoMesFim;
	private Integer qtParalisada;
	private Integer qtLigacoes;
	private BigDecimal percentual;
	private BigDecimal faturamentoEstimado;
	private String valorFaturamentoEstimadoFormatado;
	private Collection< ResumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper> resumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper;
	
	public Integer getAnoMesFim() {
		return anoMesFim;
	}
	public void setAnoMesFim(Integer anoMesFim) {
		this.anoMesFim = anoMesFim;
	}
	public Integer getAnoMesInicio() {
		return anoMesInicio;
	}
	public void setAnoMesInicio(Integer anoMesInicio) {
		this.anoMesInicio = anoMesInicio;
	}
	public BigDecimal getFaturamentoEstimado() {
		return faturamentoEstimado;
	}
	public void setFaturamentoEstimado(BigDecimal faturamentoEstimado) {
		this.faturamentoEstimado = faturamentoEstimado;
	}
	public Integer getIdMotivo() {
		return idMotivo;
	}
	public void setIdMotivo(Integer idMotivo) {
		this.idMotivo = idMotivo;
	}
	public String getMotivoDescricao() {
		return motivoDescricao;
	}
	public void setMotivoDescricao(String motivoDescricao) {
		this.motivoDescricao = motivoDescricao;
	}
	public BigDecimal getPercentual() {
		return percentual;
	}
	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}
	public Integer getQtLigacoes() {
		return qtLigacoes;
	}
	public void setQtLigacoes(Integer qtLigacoes) {
		this.qtLigacoes = qtLigacoes;
	}
	public Integer getQtParalisada() {
		return qtParalisada;
	}
	public void setQtParalisada(Integer qtParalisada) {
		this.qtParalisada = qtParalisada;
	}
	
	public ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper() {}
	
	public ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper(Integer idMotivo, String motivoDescricao, Integer anoMesInicio, Integer anoMesFim, Integer qtParalisada) {
		super();
		// TODO Auto-generated constructor stub
		this.idMotivo = idMotivo;
		this.motivoDescricao = motivoDescricao;
		this.anoMesInicio = anoMesInicio;
		this.anoMesFim = anoMesFim;
		this.qtParalisada = qtParalisada;
	}
	public String getValorFaturamentoEstimadoFormatado() {
		return valorFaturamentoEstimadoFormatado;
	}
	public void setValorFaturamentoEstimadoFormatado(
			String valorFaturamentoEstimadoFormatado) {
		this.valorFaturamentoEstimadoFormatado = valorFaturamentoEstimadoFormatado;
	}
	
	public String getFormatarAnoMesParaMesAnoInicio() {

		String anoMesRecebido = "" + this.getAnoMesInicio();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatadoInicio = mes + "/" + ano;

		return anoMesFormatadoInicio.toString();
	}
	
	public String getFormatarAnoMesParaMesAnoFim() {

		String anoMesRecebido = "" + this.getAnoMesFim();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatadoFim = mes + "/" + ano;

		return anoMesFormatadoFim.toString();
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idMotivo == null) ? 0 : idMotivo.hashCode());
		result = prime * result
				+ ((motivoDescricao == null) ? 0 : motivoDescricao.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper other = (ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper) obj;
		if (idMotivo == null) {
			if (other.idMotivo != null)
				return false;
		} else if (!idMotivo.equals(other.idMotivo))
			return false;
		if (motivoDescricao == null) {
			if (other.motivoDescricao != null)
				return false;
		} else if (!motivoDescricao.equals(other.motivoDescricao))
			return false;
		return true;
	}
	public Collection< ResumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper> getResumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper() {
		return resumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper;
	}
	public void setResumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper(
			Collection< ResumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper> resumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper) {
		this.resumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper = resumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper;
	}	
}
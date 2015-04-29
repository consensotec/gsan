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
package gsan.arrecadacao.aviso;

import gsan.arrecadacao.DeducaoTipo;
import gsan.arrecadacao.FiltroAvisoAcerto;
import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AvisoDeducoes  extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	public Filtro retornaFiltro() {
		FiltroAvisoAcerto filtroAvisoAcerto = new FiltroAvisoAcerto();
        filtroAvisoAcerto.adicionarParametro(new ParametroSimples("deducaoTipo.id", this.getDeducaoTipo().getId()));
        filtroAvisoAcerto.adicionarParametro(new ParametroSimples("avisoBancario.id", this.getAvisoBancario().getId()));
        filtroAvisoAcerto.adicionarCaminhoParaCarregamentoEntidade("comp_id");
        filtroAvisoAcerto.adicionarCaminhoParaCarregamentoEntidade("avisoBancario");
		return filtroAvisoAcerto ;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] ids = {"deducaoTipo","avisoBancario"};
		return ids;
	}

	/** identifier field */
	private gsan.arrecadacao.aviso.AvisoDeducoesPK comp_id;

	/** nullable persistent field */
	private BigDecimal valorDeducao;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private DeducaoTipo deducaoTipo;

	/** nullable persistent field */
	private gsan.arrecadacao.aviso.AvisoBancario avisoBancario;

	/** full constructor */
	public AvisoDeducoes(gsan.arrecadacao.aviso.AvisoDeducoesPK comp_id,
			BigDecimal valorDeducao, Date ultimaAlteracao,
			DeducaoTipo deducaoTipo,
			gsan.arrecadacao.aviso.AvisoBancario avisoBancario) {
		this.comp_id = comp_id;
		this.valorDeducao = valorDeducao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.deducaoTipo = deducaoTipo;
		this.avisoBancario = avisoBancario;
	}

	/** default constructor */
	public AvisoDeducoes() {
	}

	/** minimal constructor */
	public AvisoDeducoes(gsan.arrecadacao.aviso.AvisoDeducoesPK comp_id) {
		this.comp_id = comp_id;
	}

	public gsan.arrecadacao.aviso.AvisoDeducoesPK getComp_id() {
		return this.comp_id;
	}

	public void setComp_id(gsan.arrecadacao.aviso.AvisoDeducoesPK comp_id) {
		this.comp_id = comp_id;
	}

	public BigDecimal getValorDeducao() {
		return this.valorDeducao;
	}

	public void setValorDeducao(BigDecimal valorDeducao) {
		this.valorDeducao = valorDeducao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public DeducaoTipo getDeducaoTipo() {
		return this.deducaoTipo;
	}

	public void setDeducaoTipo(DeducaoTipo deducaoTipo) {
		this.deducaoTipo = deducaoTipo;
	}

	public gsan.arrecadacao.aviso.AvisoBancario getAvisoBancario() {
		return this.avisoBancario;
	}

	public void setAvisoBancario(
			gsan.arrecadacao.aviso.AvisoBancario avisoBancario) {
		this.avisoBancario = avisoBancario;
	}

	public String toString() {
		return new ToStringBuilder(this).append("comp_id", getComp_id())
				.toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if (!(other instanceof AvisoDeducoes))
			return false;
		AvisoDeducoes castOther = (AvisoDeducoes) other;
		return new EqualsBuilder().append(this.getComp_id(),
				castOther.getComp_id()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getComp_id()).toHashCode();
	}

}

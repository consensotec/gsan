/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.financeiro.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ParametrosPerdasFiscaisItensHelper  {
	
	private Integer idParametrosPerdasFiscaisItens;
	
	private BigDecimal valorLimite;
	
	private Short numeroMeses;
	
	private Integer situacaoCobranca;
	
	private String descricaoSituacaoCobranca;
	
	private Date ultimaAlteracao;
	
	
	public ParametrosPerdasFiscaisItensHelper(
			String anoMesReferenciaContabil, BigDecimal valorABaixar,
			Date ultimaAlteracao, BigDecimal valorLimite, Short numeroMeses,
			Integer situacaoCobranca, String descricaoSituacaoCobranca) {
		this.valorLimite = valorLimite;
		this.numeroMeses = numeroMeses;
		this.situacaoCobranca = situacaoCobranca;
		this.descricaoSituacaoCobranca = descricaoSituacaoCobranca;;
	}
	
	public ParametrosPerdasFiscaisItensHelper(){}

	public Integer getIdParametrosPerdasFiscaisItens() {
		return idParametrosPerdasFiscaisItens;
	}

	public void setIdParametrosPerdasFiscaisItens(
			Integer idParametrosPerdasFiscaisItens) {
		this.idParametrosPerdasFiscaisItens = idParametrosPerdasFiscaisItens;
	}

	public BigDecimal getValorLimite() {
		return valorLimite;
	}

	public void setValorLimite(BigDecimal valorLimite) {
		this.valorLimite = valorLimite;
	}

	public Short getNumeroMeses() {
		return numeroMeses;
	}

	public void setNumeroMeses(Short numeroMeses) {
		this.numeroMeses = numeroMeses;
	}

	public Integer getSituacaoCobranca() {
		return situacaoCobranca;
	}

	public void setSituacaoCobranca(Integer situacaoCobranca) {
		this.situacaoCobranca = situacaoCobranca;
	}
	
	public String getDescricaoSituacaoCobranca() {
		return descricaoSituacaoCobranca;
	}

	public void setDescricaoSituacaoCobranca(String descricaoSituacaoCobranca) {
		this.descricaoSituacaoCobranca = descricaoSituacaoCobranca;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	/**
	 * < <Descrição do método>>
	 * 
	 * @param other
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ParametrosPerdasFiscaisItensHelper)) {
			return false;
		}
		ParametrosPerdasFiscaisItensHelper castOther = (ParametrosPerdasFiscaisItensHelper) other;

		return this.getValorLimite().compareTo(castOther.getValorLimite()) == 0;
	}
	
}

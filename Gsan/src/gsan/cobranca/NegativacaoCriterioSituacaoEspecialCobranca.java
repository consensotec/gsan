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

package gsan.cobranca;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativacaoCriterioSituacaoEspecialCobranca implements Serializable {
	
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gsan.cobranca.NegativacaoCriterio negativacaoCriterio;

    /** persistent field */
    private CobrancaSituacaoTipo cobrancaSituacaoTipo;

    /** full constructor */
    public NegativacaoCriterioSituacaoEspecialCobranca(
    	Integer id, Date ultimaAlteracao, gsan.cobranca.NegativacaoCriterio negativacaoCriterio, CobrancaSituacaoTipo cobrancaSituacaoTipo) {
        
    	this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativacaoCriterio = negativacaoCriterio;
        this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
    }

    /** default constructor */
    public NegativacaoCriterioSituacaoEspecialCobranca() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gsan.cobranca.NegativacaoCriterio getNegativacaoCriterio() {
        return this.negativacaoCriterio;
    }

    public void setNegativacaoCriterio(gsan.cobranca.NegativacaoCriterio negativacaoCriterio) {
        this.negativacaoCriterio = negativacaoCriterio;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    /**
     *  Descri��o do m�todo>>
     * 
     * @param other
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof NegativacaoCriterioCpfTipo)) {
            return false;
        }
        NegativacaoCriterioSituacaoEspecialCobranca castOther = (NegativacaoCriterioSituacaoEspecialCobranca) other;

        return new EqualsBuilder().append(this.getCobrancaSituacaoTipo().getId(), castOther.getCobrancaSituacaoTipo().getId()).isEquals();
    }

   
   /**
     * Description of the Method
     * 
     * @return Description of the Return Value
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getCobrancaSituacaoTipo().getId())
                .toHashCode();
    }

	public CobrancaSituacaoTipo getCobrancaSituacaoTipo() {
		return cobrancaSituacaoTipo;
	}
	
	public void setCobrancaSituacaoTipo(CobrancaSituacaoTipo cobrancaSituacaoTipo) {
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
	}

    
}

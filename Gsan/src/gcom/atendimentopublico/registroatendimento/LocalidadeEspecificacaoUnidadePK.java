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
package gcom.atendimentopublico.registroatendimento;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Descri��o da classe LocalidadeEspecificacaoUnidadePK
 * 
 * @author Hugo Leonardo
 * @date 29/11/2010
 */
public class LocalidadeEspecificacaoUnidadePK extends ObjetoGcom{
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Localidade localidade;
	
	/** identifier field */
    private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;
	
    /** identifier field */
    private UnidadeOrganizacional unidadeOrganizacional;

    /** full constructor */
    public LocalidadeEspecificacaoUnidadePK(Localidade localidade, SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao, 
    		UnidadeOrganizacional unidadeOrganizacional) {
    	
        this.localidade = localidade;
    	this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
        this.unidadeOrganizacional = unidadeOrganizacional;
    }

    /** default constructor */
    public LocalidadeEspecificacaoUnidadePK() {
    }

	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}

	public void setSolicitacaoTipoEspecificacao(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("localidade", getLocalidade())
        	.append("solicitacaoTipoEspecificacao", getSolicitacaoTipoEspecificacao())
            .append("unidadeOrganizacional", getUnidadeOrganizacional())
            .toString();
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        
        if ( !(other instanceof LocalidadeEspecificacaoUnidadePK) ) return false;
        
        LocalidadeEspecificacaoUnidadePK castOther = (LocalidadeEspecificacaoUnidadePK) other;
        
        boolean igual = this.getLocalidade().getId().equals(castOther.getLocalidade().getId()) 
        	&& this.getSolicitacaoTipoEspecificacao().getId().equals(castOther.getSolicitacaoTipoEspecificacao().getId())
            && this.getUnidadeOrganizacional().getId().equals(castOther.getUnidadeOrganizacional().getId());
        
        return igual;
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getLocalidade())
        	.append(getSolicitacaoTipoEspecificacao())
            .append(getUnidadeOrganizacional())
            .toHashCode();
    }

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[3];
		retorno[0] = "localidade";
 		retorno[1] = "solicitacaoTipoEspecificacao";
 		retorno[2] = "unidadeOrganizacional";
 		return retorno;
	}
	
	public void initializeLazy(){
		if (this.getLocalidade() != null){
			this.getLocalidade().initializeLazy();
		}
		
		if (this.getSolicitacaoTipoEspecificacao() != null){
			this.getSolicitacaoTipoEspecificacao().initializeLazy();
		}	
		
		if (this.getUnidadeOrganizacional() != null){
			this.getUnidadeOrganizacional().initializeLazy();
		}
	}
	
}

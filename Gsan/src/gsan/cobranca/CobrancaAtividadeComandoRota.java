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

import gsan.interceptor.ObjetoTransacao;
import gsan.micromedicao.Rota;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CobrancaAtividadeComandoRota extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private gsan.cobranca.CobrancaAtividadeComandoRotaPK comp_id;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Rota rota;

    /** nullable persistent field */
    private gsan.cobranca.CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;

    /** full constructor */
    public CobrancaAtividadeComandoRota(gsan.cobranca.CobrancaAtividadeComandoRotaPK comp_id, Date ultimaAlteracao, Rota rota, gsan.cobranca.CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.rota = rota;
        this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
    }

    /** default constructor */
    public CobrancaAtividadeComandoRota() {
    }

    /** minimal constructor */
    public CobrancaAtividadeComandoRota(gsan.cobranca.CobrancaAtividadeComandoRotaPK comp_id) {
        this.comp_id = comp_id;
    }

    public gsan.cobranca.CobrancaAtividadeComandoRotaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gsan.cobranca.CobrancaAtividadeComandoRotaPK comp_id) {
        this.comp_id = comp_id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Rota getRota() {
        return this.rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public gsan.cobranca.CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
        return this.cobrancaAcaoAtividadeComando;
    }

    public void setCobrancaAcaoAtividadeComando(gsan.cobranca.CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
        this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof CobrancaAtividadeComandoRota) ) return false;
        CobrancaAtividadeComandoRota castOther = (CobrancaAtividadeComandoRota) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}    
    
	public Filtro retornaFiltro(){
		FiltroCobrancaAtividadeComandoRotas filtroCobrancaAtividadeComandoRota = new FiltroCobrancaAtividadeComandoRotas();
		
		filtroCobrancaAtividadeComandoRota.adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroCobrancaAtividadeComandoRota.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoAtividadeComando");

		
		filtroCobrancaAtividadeComandoRota.adicionarParametro(
						new ParametroSimples(FiltroCobrancaAtividadeComandoRotas.COMP_ID_COBRANCA_ACAO_ATIVIDADE_COMANDO_ID, cobrancaAcaoAtividadeComando.getId()));
		
		filtroCobrancaAtividadeComandoRota.adicionarParametro(
				new ParametroSimples(FiltroCobrancaAtividadeComandoRotas.COMP_ID_ROTA_ID, rota.getId()));
		
		return filtroCobrancaAtividadeComandoRota; 
	}
    
    
}

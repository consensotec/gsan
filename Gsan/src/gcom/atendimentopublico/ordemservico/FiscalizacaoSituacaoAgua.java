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
package gcom.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FiscalizacaoSituacaoAgua implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private FiscalizacaoSituacaoAguaPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;
    
    private short indicadorConsumoFixado;

    /** nullable persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacaoByLastId;

    /** nullable persistent field */
    private FiscalizacaoSituacao fiscalizacaoSituacao;

    /** persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacaoByLastIdnova;
    
    /** persistent field */
    private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;
    
    /** persistent field */
    private Short numeroMesesFatura;
    
    public final static short INDICADOR_SIM = new Short("1");


    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }


	/**
	 * @return Retorna o campo comp_id.
	 */
	public FiscalizacaoSituacaoAguaPK getComp_id() {
		return comp_id;
	}


	/**
	 * @param comp_id O comp_id a ser setado.
	 */
	public void setComp_id(FiscalizacaoSituacaoAguaPK comp_id) {
		this.comp_id = comp_id;
	}


	/**
	 * @return Retorna o campo fiscalizacaoSituacao.
	 */
	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}


	/**
	 * @param fiscalizacaoSituacao O fiscalizacaoSituacao a ser setado.
	 */
	public void setFiscalizacaoSituacao(FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}


	/**
	 * @return Retorna o campo ligacaoAguaSituacaoByLastId.
	 */
	public LigacaoAguaSituacao getLigacaoAguaSituacaoByLastId() {
		return ligacaoAguaSituacaoByLastId;
	}


	/**
	 * @param ligacaoAguaSituacaoByLastId O ligacaoAguaSituacaoByLastId a ser setado.
	 */
	public void setLigacaoAguaSituacaoByLastId(
			LigacaoAguaSituacao ligacaoAguaSituacaoByLastId) {
		this.ligacaoAguaSituacaoByLastId = ligacaoAguaSituacaoByLastId;
	}


	/**
	 * @return Retorna o campo ligacaoAguaSituacaoByLastIdnova.
	 */
	public LigacaoAguaSituacao getLigacaoAguaSituacaoByLastIdnova() {
		return ligacaoAguaSituacaoByLastIdnova;
	}


	/**
	 * @param ligacaoAguaSituacaoByLastIdnova O ligacaoAguaSituacaoByLastIdnova a ser setado.
	 */
	public void setLigacaoAguaSituacaoByLastIdnova(
			LigacaoAguaSituacao ligacaoAguaSituacaoByLastIdnova) {
		this.ligacaoAguaSituacaoByLastIdnova = ligacaoAguaSituacaoByLastIdnova;
	}


	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	public short getIndicadorConsumoFixado() {
		return indicadorConsumoFixado;
	}


	public void setIndicadorConsumoFixado(short indicadorConsumoFixado) {
		this.indicadorConsumoFixado = indicadorConsumoFixado;
	}


	/**
	 * @return Retorna o campo solicitacaoTipoEspecificacao.
	 */
	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}


	/**
	 * @param solicitacaoTipoEspecificacao O solicitacaoTipoEspecificacao a ser setado.
	 */
	public void setSolicitacaoTipoEspecificacao(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}


	public Short getNumeroMesesFatura() {
		return numeroMesesFatura;
	}


	public void setNumeroMesesFatura(Short numeroMesesFatura) {
		this.numeroMesesFatura = numeroMesesFatura;
	}
	
	
	
	
}

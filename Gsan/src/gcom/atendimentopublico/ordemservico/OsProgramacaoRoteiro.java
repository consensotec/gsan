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

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OsProgramacaoRoteiro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private short sequencialProgramacao;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro programacaoRoteiro;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao ordemServicoProgramacao;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.OsProgramNaoEncerMotivo osProgramNaoEncerMotivo;

    /** full constructor */
    public OsProgramacaoRoteiro(short sequencialProgramacao, short indicadorUso, Date ultimaAlteracao, gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro programacaoRoteiro, gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao ordemServicoProgramacao, gcom.atendimentopublico.ordemservico.OsProgramNaoEncerMotivo osProgramNaoEncerMotivo) {
        this.sequencialProgramacao = sequencialProgramacao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.programacaoRoteiro = programacaoRoteiro;
        this.ordemServicoProgramacao = ordemServicoProgramacao;
        this.osProgramNaoEncerMotivo = osProgramNaoEncerMotivo;
    }

    /** default constructor */
    public OsProgramacaoRoteiro() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getSequencialProgramacao() {
        return this.sequencialProgramacao;
    }

    public void setSequencialProgramacao(short sequencialProgramacao) {
        this.sequencialProgramacao = sequencialProgramacao;
    }

    public short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro getProgramacaoRoteiro() {
        return this.programacaoRoteiro;
    }

    public void setProgramacaoRoteiro(gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro programacaoRoteiro) {
        this.programacaoRoteiro = programacaoRoteiro;
    }

    public gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao getOrdemServicoProgramacao() {
        return this.ordemServicoProgramacao;
    }

    public void setOrdemServicoProgramacao(gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao ordemServicoProgramacao) {
        this.ordemServicoProgramacao = ordemServicoProgramacao;
    }

    public gcom.atendimentopublico.ordemservico.OsProgramNaoEncerMotivo getOsProgramNaoEncerMotivo() {
        return this.osProgramNaoEncerMotivo;
    }

    public void setOsProgramNaoEncerMotivo(gcom.atendimentopublico.ordemservico.OsProgramNaoEncerMotivo osProgramNaoEncerMotivo) {
        this.osProgramNaoEncerMotivo = osProgramNaoEncerMotivo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}

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

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ServicoPerfilTipo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** nullable persistent field */
    private Short componentesEquipe;

    /** persistent field */
    private short indicadorVeiculoProprio;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.EquipamentosEspeciais equipamentosEspeciais;

    /** full constructor */
    public ServicoPerfilTipo(String descricao, String descricaoAbreviada, Short componentesEquipe, short indicadorVeiculoProprio, short indicadorUso, Date ultimaAlteracao, gcom.atendimentopublico.ordemservico.EquipamentosEspeciais equipamentosEspeciais) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.componentesEquipe = componentesEquipe;
        this.indicadorVeiculoProprio = indicadorVeiculoProprio;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.equipamentosEspeciais = equipamentosEspeciais;
    }

    /** default constructor */
    public ServicoPerfilTipo() {
    }

    /** minimal constructor */
    public ServicoPerfilTipo(short indicadorVeiculoProprio, short indicadorUso, Date ultimaAlteracao, gcom.atendimentopublico.ordemservico.EquipamentosEspeciais equipamentosEspeciais) {
        this.indicadorVeiculoProprio = indicadorVeiculoProprio;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.equipamentosEspeciais = equipamentosEspeciais;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public Short getComponentesEquipe() {
        return this.componentesEquipe;
    }

    public void setComponentesEquipe(Short componentesEquipe) {
        this.componentesEquipe = componentesEquipe;
    }

    public short getIndicadorVeiculoProprio() {
        return this.indicadorVeiculoProprio;
    }

    public void setIndicadorVeiculoProprio(short indicadorVeiculoProprio) {
        this.indicadorVeiculoProprio = indicadorVeiculoProprio;
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

    public gcom.atendimentopublico.ordemservico.EquipamentosEspeciais getEquipamentosEspeciais() {
        return this.equipamentosEspeciais;
    }

    public void setEquipamentosEspeciais(gcom.atendimentopublico.ordemservico.EquipamentosEspeciais equipamentosEspeciais) {
        this.equipamentosEspeciais = equipamentosEspeciais;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro() {
		FiltroServicoPerfilTipo filtroServicoPerfilTipo = new FiltroServicoPerfilTipo();
		filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(FiltroServicoPerfilTipo.ID,this.getId()));
		filtroServicoPerfilTipo.adicionarCaminhoParaCarregamentoEntidade("equipamentosEspeciais");

		return filtroServicoPerfilTipo;
	}

}

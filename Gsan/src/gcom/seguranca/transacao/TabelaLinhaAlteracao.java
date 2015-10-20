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
package gcom.seguranca.transacao;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class TabelaLinhaAlteracao implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer id1;

    /** nullable persistent field */
    private Integer id2;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private Short indicadorPrincipal;

    /** persistent field */
    private gcom.seguranca.transacao.Tabela tabela;
    
    /** persistent field */
    private gcom.seguranca.acesso.OperacaoEfetuada operacaoEfetuada;
    
    /** persistent field */
    private AlteracaoTipo alteracaoTipo;

    /** persistent field */
    private Set tabelaLinhaColunaAlteracao;
    
    public static final Short INDICADOR_TABELA_LINHA_ALTERACAO_PRINCIPAL = 1;

	/** full constructor */
    public TabelaLinhaAlteracao(Integer id, Integer id1, Integer id2, Date ultimaAlteracao, 
    		gcom.seguranca.transacao.Tabela tabela, gcom.seguranca.acesso.OperacaoEfetuada operacaoEfetuada, Set tabelaLinhaColunaAlteracao, AlteracaoTipo alteracaoTipo) {
        this.id = id;
        this.id1 = id1;
        this.id2 = id2;
        this.ultimaAlteracao = ultimaAlteracao;
        this.tabela = tabela;
        this.operacaoEfetuada = operacaoEfetuada;
        this.tabelaLinhaColunaAlteracao = tabelaLinhaColunaAlteracao;
        this.alteracaoTipo = alteracaoTipo;
    }

    /** default constructor */
    public TabelaLinhaAlteracao() {
    }

    /**
	 * @return Retorna o campo alteracaoTipo.
	 */
	public AlteracaoTipo getAlteracaoTipo() {
		return alteracaoTipo;
	}

	/**
	 * @param alteracaoTipo O alteracaoTipo a ser setado.
	 */
	public void setAlteracaoTipo(AlteracaoTipo alteracaoTipo) {
		this.alteracaoTipo = alteracaoTipo;
	}

	/** minimal constructor */
    public TabelaLinhaAlteracao(Integer id, gcom.seguranca.transacao.Tabela tabela, gcom.seguranca.acesso.OperacaoEfetuada operacaoEfetuada, Set tabelaLinhaColunaAlteracao) {
        this.id = id;
        this.tabela = tabela;
        this.operacaoEfetuada = operacaoEfetuada;
        this.tabelaLinhaColunaAlteracao = tabelaLinhaColunaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

    /**
	 * @return Retorna o campo id1.
	 */
	public Integer getId1() {
		return id1;
	}

	/**
	 * @param id1 O id1 a ser setado.
	 */
	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	/**
	 * @return Returns the id2.
	 */
	public Integer getId2() {
		return id2;
	}

	/**
	 * @param id2 The id2 to set.
	 */
	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	/**
	 * @return Returns the operacaoEfetuada.
	 */
	public gcom.seguranca.acesso.OperacaoEfetuada getOperacaoEfetuada() {
		return operacaoEfetuada;
	}

	/**
	 * @param operacaoEfetuada The operacaoEfetuada to set.
	 */
	public void setOperacaoEfetuada(
			gcom.seguranca.acesso.OperacaoEfetuada operacaoEfetuada) {
		this.operacaoEfetuada = operacaoEfetuada;
	}

	/**
	 * @return Returns the tabela.
	 */
	public gcom.seguranca.transacao.Tabela getTabela() {
		return tabela;
	}

	/**
	 * @param tabela The tabela to set.
	 */
	public void setTabela(gcom.seguranca.transacao.Tabela tabela) {
		this.tabela = tabela;
	}

	/**
	 * @return Returns the tabelaLinhaColunaAlteracao.
	 */
	public Set getTabelaLinhaColunaAlteracao() {
		return tabelaLinhaColunaAlteracao;
	}

	/**
	 * @param tabelaLinhaColunaAlteracao The tabelaLinhaColunaAlteracao to set.
	 */
	public void setTabelaLinhaColunaAlteracao(Set tabelaLinhaColunaAlteracao) {
		this.tabelaLinhaColunaAlteracao = tabelaLinhaColunaAlteracao;
	}

	/**
	 * @return Returns the ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao The ultimaAlteracao to set.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Short getIndicadorPrincipal() {
		return indicadorPrincipal;
	}

	public void setIndicadorPrincipal(Short indicadorPrincipal) {
		this.indicadorPrincipal = indicadorPrincipal;
	}
}
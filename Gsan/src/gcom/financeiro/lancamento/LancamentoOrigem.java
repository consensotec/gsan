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
package gcom.financeiro.lancamento;

import gcom.financeiro.PerdasTipo;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class LancamentoOrigem implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;
    
    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Short numeroCartao;
    
    /** nullable persistent field */
    private Short codigoTipo;
    
    /** nullable persistent field */
    private Short numeroFolha;
    
    /** nullable persistent field */
    private String codigoReferencia;
    
    /** nullable persistent field */
    private Short numeroVersao;
    
    /** nullable persistent field */
    private Short numeroCartao2;
    
    /** nullable persistent field */
    private Integer numeroHistoricoCredito;

    /** nullable persistent field */
    private Integer numeroHistoricoDebito;
    
    private Integer numeroUtilmoSequencial;
    
    public Integer getNumeroUtilmoSequencial() {
		return numeroUtilmoSequencial;
	}

	public void setNumeroUtilmoSequencial(Integer numeroUtilmoSequencial) {
		this.numeroUtilmoSequencial = numeroUtilmoSequencial;
	}

	private PerdasTipo perdasTipo;
    
    private gcom.batch.Processo processo;

    
    
    //Constantes
    public final static Integer FATURAMENTO = new Integer("1");
    
    public final static Integer ARRECADACAO = new Integer("2");
    
    public final static Integer DEVEDORES_DUVIDOSOS = new Integer("3");
    
    public final static Integer AVISO_BANCARIO = new Integer("4");

    /** full constructor */
    public LancamentoOrigem(String descricao, String descricaoAbreviada, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public LancamentoOrigem() {
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

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public String getCodigoReferencia() {
		return codigoReferencia;
	}

	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}

	public Short getCodigoTipo() {
		return codigoTipo;
	}

	public void setCodigoTipo(Short codigoTipo) {
		this.codigoTipo = codigoTipo;
	}

	public Short getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(Short numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public Short getNumeroCartao2() {
		return numeroCartao2;
	}

	public void setNumeroCartao2(Short numeroCartao2) {
		this.numeroCartao2 = numeroCartao2;
	}

	public Short getNumeroFolha() {
		return numeroFolha;
	}

	public void setNumeroFolha(Short numeroFolha) {
		this.numeroFolha = numeroFolha;
	}

	public Short getNumeroVersao() {
		return numeroVersao;
	}

	public void setNumeroVersao(Short numeroVersao) {
		this.numeroVersao = numeroVersao;
	}
	
	public Integer getNumeroHistoricoCredito() {
		return numeroHistoricoCredito;
	}

	public void setNumeroHistoricoCredito(Integer numeroHistoricoCredito) {
		this.numeroHistoricoCredito = numeroHistoricoCredito;
	}

	public Integer getNumeroHistoricoDebito() {
		return numeroHistoricoDebito;
	}

	public void setNumeroHistoricoDebito(Integer numeroHistoricoDebito) {
		this.numeroHistoricoDebito = numeroHistoricoDebito;
	}

	/**
	 * @return the perdasTipo
	 */
	public PerdasTipo getPerdasTipo() {
		return perdasTipo;
	}

	/**
	 * @param perdasTipo the perdasTipo to set
	 */
	public void setPerdasTipo(PerdasTipo perdasTipo) {
		this.perdasTipo = perdasTipo;
	}
	
	public gcom.batch.Processo getProcesso() {
        return this.processo;
    }

    public void setProcesso(gcom.batch.Processo processo) {
        this.processo = processo;
    }
	

}

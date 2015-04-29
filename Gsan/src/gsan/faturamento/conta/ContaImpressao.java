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
package gsan.faturamento.conta;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.empresa.Empresa;
import gsan.faturamento.FaturamentoGrupo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContaImpressao implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int referenciaConta;

    /** persistent field */
    private short indicadorImpressao;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private Integer sequencialImpressao;
    
    private BigDecimal valorConta;
    
    private Short indicadorFichaCompensacao;

    /** nullable persistent field */
    private gsan.faturamento.conta.ContaGeral contaGeral;

    /** persistent field */
    private FaturamentoGrupo faturamentoGrupo;
    
    private Cliente clienteResponsavel;
    
    private ContaTipo contaTipo;
    
    private Empresa empresa;

    /** full constructor */
    public ContaImpressao(Integer id, int referenciaConta, short indicadorImpressao, Date ultimaAlteracao, Integer sequencialImpressao, ContaGeral contaGeral, FaturamentoGrupo faturamentoGrupo) {
        this.id = id;
        this.referenciaConta = referenciaConta;
        this.indicadorImpressao = indicadorImpressao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.sequencialImpressao = sequencialImpressao;
        this.contaGeral = contaGeral;
        this.faturamentoGrupo = faturamentoGrupo;
    }

    /** default constructor */
    public ContaImpressao() {
    }

    /** minimal constructor */
    public ContaImpressao(Integer id, int referenciaConta, short indicadorImpressao, Date ultimaAlteracao, Integer sequencialImpressao, FaturamentoGrupo faturamentoGrupo) {
        this.id = id;
        this.referenciaConta = referenciaConta;
        this.indicadorImpressao = indicadorImpressao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.sequencialImpressao = sequencialImpressao;
        this.faturamentoGrupo = faturamentoGrupo;
    }

    
   
    public ContaTipo getContaTipo() {
		return contaTipo;
	}

	public void setContaTipo(ContaTipo contaTipo) {
		this.contaTipo = contaTipo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public ContaGeral getContaGeral() {
        return this.contaGeral;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getIndicadorImpressao() {
		return indicadorImpressao;
	}

	public void setIndicadorImpressao(short indicadorImpressao) {
		this.indicadorImpressao = indicadorImpressao;
	}

	public int getReferenciaConta() {
		return referenciaConta;
	}

	public void setReferenciaConta(int referenciaConta) {
		this.referenciaConta = referenciaConta;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public void setContaGeral(ContaGeral contaGeral) {
        this.contaGeral = contaGeral;
    }

    public FaturamentoGrupo getFaturamentoGrupo() {
        return this.faturamentoGrupo;
    }

    public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
        this.faturamentoGrupo = faturamentoGrupo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof ContaImpressao)) {
            return false;
        }
        ContaImpressao castOther = (ContaImpressao) other;

        return (this.getId().equals(castOther.getId()));
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
        .append(getId())
        .toHashCode();
    }

	public Cliente getClienteResponsavel() {
		return clienteResponsavel;
	}

	public void setClienteResponsavel(Cliente clienteResponsavel) {
		this.clienteResponsavel = clienteResponsavel;
	}

	/**
	 * @return Retorna o campo sequencialImpressao.
	 */
	public Integer getSequencialImpressao() {
		return sequencialImpressao;
	}

	/**
	 * @param sequencialImpressao O sequencialImpressao a ser setado.
	 */
	public void setSequencialImpressao(Integer sequencialImpressao) {
		this.sequencialImpressao = sequencialImpressao;
	}

    public BigDecimal getValorConta() {
        return valorConta;
    }

    public void setValorConta(BigDecimal valorConta) {
        this.valorConta = valorConta;
    }

    public Short getIndicadorFichaCompensacao() {
        return indicadorFichaCompensacao;
    }

    public void setIndicadorFichaCompensacao(Short indicadorFichaCompensacao) {
        this.indicadorFichaCompensacao = indicadorFichaCompensacao;
    }

    
    
}

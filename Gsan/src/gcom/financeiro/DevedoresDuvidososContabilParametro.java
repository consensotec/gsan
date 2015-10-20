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
package gcom.financeiro;

import gcom.cadastro.imovel.Categoria;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoTipo;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DevedoresDuvidososContabilParametro implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private String descricaoHistoricoDebito;

    /** persistent field */
    private String descricaoHistoricoCredito;

    /** persistent field */
    private LancamentoItem lancamentoItem;

    /** persistent field */
    private LancamentoTipo lancamentoTipo;

    /** persistent field */
    private Categoria categoria;

    /** persistent field */
    private gcom.financeiro.ContaContabil contaContabilCredito;

    /** persistent field */
    private gcom.financeiro.ContaContabil contaContabilDebito;

    /** persistent field */
    private LancamentoItemContabil lancamentoItemContabil;

    private PerdasTipo perdasTipo;
    
    /** full constructor */
    public DevedoresDuvidososContabilParametro(Integer id, Date ultimaAlteracao, String descricaoHistoricoDebito, String descricaoHistoricoCredito, LancamentoItem lancamentoItem, LancamentoTipo lancamentoTipo, Categoria categoria, gcom.financeiro.ContaContabil contaContabilCredito, gcom.financeiro.ContaContabil contaContabilDebito, LancamentoItemContabil lancamentoItemContabil) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.descricaoHistoricoDebito = descricaoHistoricoDebito;
        this.descricaoHistoricoCredito = descricaoHistoricoCredito;
        this.lancamentoItem = lancamentoItem;
        this.lancamentoTipo = lancamentoTipo;
        this.categoria = categoria;
        this.contaContabilCredito = contaContabilCredito;
        this.contaContabilDebito = contaContabilDebito;
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    /** default constructor */
    public DevedoresDuvidososContabilParametro() {
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

    public String getDescricaoHistoricoDebito() {
        return this.descricaoHistoricoDebito;
    }

    public void setDescricaoHistoricoDebito(String descricaoHistoricoDebito) {
        this.descricaoHistoricoDebito = descricaoHistoricoDebito;
    }

    public String getDescricaoHistoricoCredito() {
        return this.descricaoHistoricoCredito;
    }

    public void setDescricaoHistoricoCredito(String descricaoHistoricoCredito) {
        this.descricaoHistoricoCredito = descricaoHistoricoCredito;
    }

    public LancamentoItem getLancamentoItem() {
        return this.lancamentoItem;
    }

    public void setLancamentoItem(LancamentoItem lancamentoItem) {
        this.lancamentoItem = lancamentoItem;
    }

    public LancamentoTipo getLancamentoTipo() {
        return this.lancamentoTipo;
    }

    public void setLancamentoTipo(LancamentoTipo lancamentoTipo) {
        this.lancamentoTipo = lancamentoTipo;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public gcom.financeiro.ContaContabil getContaContabilCredito() {
        return this.contaContabilCredito;
    }

    public void setContaContabilCredito(gcom.financeiro.ContaContabil contaContabilCredito) {
        this.contaContabilCredito = contaContabilCredito;
    }

    public gcom.financeiro.ContaContabil getContaContabilDebito() {
        return this.contaContabilDebito;
    }

    public void setContaContabilDebito(gcom.financeiro.ContaContabil contaContabilDebito) {
        this.contaContabilDebito = contaContabilDebito;
    }

    public LancamentoItemContabil getLancamentoItemContabil() {
        return this.lancamentoItemContabil;
    }

    public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil) {
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
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
    
    

}

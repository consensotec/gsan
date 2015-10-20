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
package gcom.arrecadacao.pagamento;

import gcom.cadastro.imovel.Categoria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GuiaPagamentoItemCategoriaHistorico implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private GuiaPagamentoItemCategoriaHistoricoPK comp_id;

    /** nullable persistent field */
    private Integer quantidadeEconomia;

    /** nullable persistent field */
    private BigDecimal valorCategoria;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private GuiaPagamentoItemHistorico guiaPagamentoItemHistorico;

    /** nullable persistent field */
    private Categoria categoria;


    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }


	/**
	 * @return Retorna o campo categoria.
	 */
	public Categoria getCategoria() {
		return categoria;
	}


	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	/**
	 * @return Retorna o campo comp_id.
	 */
	public GuiaPagamentoItemCategoriaHistoricoPK getComp_id() {
		return comp_id;
	}


	/**
	 * @param comp_id O comp_id a ser setado.
	 */
	public void setComp_id(GuiaPagamentoItemCategoriaHistoricoPK comp_id) {
		this.comp_id = comp_id;
	}


	/**
	 * @return Retorna o campo guiaPagamentoHistorico.
	 */
	public GuiaPagamentoItemHistorico getGuiaPagamentoItemHistorico() {
		return guiaPagamentoItemHistorico;
	}


	/**
	 * @param guiaPagamentoHistorico O guiaPagamentoHistorico a ser setado.
	 */
	public void setGuiaPagamentoItemHistorico(
			GuiaPagamentoItemHistorico guiaPagamentoItemHistorico) {
		this.guiaPagamentoItemHistorico = guiaPagamentoItemHistorico;
	}


	/**
	 * @return Retorna o campo quantidadeEconomia.
	 */
	public Integer getQuantidadeEconomia() {
		return quantidadeEconomia;
	}


	/**
	 * @param quantidadeEconomia O quantidadeEconomia a ser setado.
	 */
	public void setQuantidadeEconomia(Integer quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
	}


	/**
	 * @return Retorna o campo ultimaAltercao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	/**
	 * @param ultimaAltercao O ultimaAltercao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	/**
	 * @return Retorna o campo valorCategoria.
	 */
	public BigDecimal getValorCategoria() {
		return valorCategoria;
	}


	/**
	 * @param valorCategoria O valorCategoria a ser setado.
	 */
	public void setValorCategoria(BigDecimal valorCategoria) {
		this.valorCategoria = valorCategoria;
	}

}

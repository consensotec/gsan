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
package gcom.faturamento.conta;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContaCategoriaConsumoFaixaHistorico implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private BigDecimal valorAgua;

    /** nullable persistent field */
    private Integer consumoAgua;

    /** nullable persistent field */
    private BigDecimal valorEsgoto;

    /** nullable persistent field */
    private Integer consumoEsgoto;

    /** nullable persistent field */
    private Integer consumoFaixaInicio;

    /** nullable persistent field */
    private Integer consumoFaixaFim;

    /** nullable persistent field */
    private BigDecimal valorTarifaFaixa;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Categoria categoria;
    
    /** persistent field */
    private ContaCategoriaHistorico contaCategoriaHistorico;

    /** persistent field */
    private Subcategoria subcategoria;
    
    /** full constructor */
    public ContaCategoriaConsumoFaixaHistorico(Integer id, BigDecimal valorAgua, Integer consumoAgua, BigDecimal valorEsgoto, Integer consumoEsgoto, Integer consumoFaixaInicio, Integer consumoFaixaFim, BigDecimal valorTarifaFaixa, Date ultimaAlteracao, Categoria categoria, ContaCategoriaHistorico contaCategoriaHistorico, Subcategoria subcategoria) {
        this.id = id;
        this.valorAgua = valorAgua;
        this.consumoAgua = consumoAgua;
        this.valorEsgoto = valorEsgoto;
        this.consumoEsgoto = consumoEsgoto;
        this.consumoFaixaInicio = consumoFaixaInicio;
        this.consumoFaixaFim = consumoFaixaFim;
        this.valorTarifaFaixa = valorTarifaFaixa;
        this.ultimaAlteracao = ultimaAlteracao;
        this.categoria = categoria;
        this.contaCategoriaHistorico = contaCategoriaHistorico;
        this.subcategoria = subcategoria;
    }

    /** default constructor */
    public ContaCategoriaConsumoFaixaHistorico() {
    }

    /** minimal constructor */
    public ContaCategoriaConsumoFaixaHistorico(Integer id, Date ultimaAlteracao, ContaCategoriaHistorico contaCategoriaHistorico) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.contaCategoriaHistorico = contaCategoriaHistorico;
    }

    
    public ContaCategoriaHistorico getContaCategoriaHistorico() {
        return this.contaCategoriaHistorico;
    }

    public void setContaCategoriaHistorico(ContaCategoriaHistorico contaCategoriaHistorico) {
        this.contaCategoriaHistorico = contaCategoriaHistorico;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public Integer getConsumoAgua() {
        return consumoAgua;
    }

    public void setConsumoAgua(Integer consumoAgua) {
        this.consumoAgua = consumoAgua;
    }

    public Integer getConsumoEsgoto() {
        return consumoEsgoto;
    }

    public void setConsumoEsgoto(Integer consumoEsgoto) {
        this.consumoEsgoto = consumoEsgoto;
    }

    public Integer getConsumoFaixaFim() {
        return consumoFaixaFim;
    }

    public void setConsumoFaixaFim(Integer consumoFaixaFim) {
        this.consumoFaixaFim = consumoFaixaFim;
    }

    public Integer getConsumoFaixaInicio() {
        return consumoFaixaInicio;
    }

    public void setConsumoFaixaInicio(Integer consumoFaixaInicio) {
        this.consumoFaixaInicio = consumoFaixaInicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public BigDecimal getValorAgua() {
        return valorAgua;
    }

    public void setValorAgua(BigDecimal valorAgua) {
        this.valorAgua = valorAgua;
    }

    public BigDecimal getValorEsgoto() {
        return valorEsgoto;
    }

    public void setValorEsgoto(BigDecimal valorEsgoto) {
        this.valorEsgoto = valorEsgoto;
    }

    public BigDecimal getValorTarifaFaixa() {
        return valorTarifaFaixa;
    }

    public void setValorTarifaFaixa(BigDecimal valorTarifaFaixa) {
        this.valorTarifaFaixa = valorTarifaFaixa;
    }
    
    /**
     * @return Retorna o campo subcategoria.
     */
    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    /**
     * @param subcategoria O subcategoria a ser setado.
     */
    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

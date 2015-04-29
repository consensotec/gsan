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
package gsan.cadastro.imovel;

import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
public class Subcategoria extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public static Integer RESIDENCIAL = new Integer(10);

    /**
     * identifier field
     */
    private Integer id;

    /**
     * persistent field
     */
    private int codigo;

    /**
     * persistent field
     */
    private String descricao;

    /**
     * nullable persistent field
     */
    private Short indicadorUso;

    /**
     * nullable persistent field
     */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private String codigoTarifaSocial;

    /** persistent field */
    private short numeroFatorFiscalizacao;
    
    /** persistent field */
    private Short indicadorTarifaConsumo;
    
    private Integer quantidadeEconomias;
    
    private String descricaoAbreviada;
    
    private Integer codigoGrupoSubcategoria;

    /**
     * persistent field
     */
    private gsan.cadastro.imovel.Categoria categoria;
    
    private String descricaoComId;
    
    private Short indicadorSazonalidade;

    public static final Subcategoria SUBCATEGORIA_ZERO;

    static {
    	SUBCATEGORIA_ZERO = new Subcategoria();
    	SUBCATEGORIA_ZERO.setId(new Integer(0));
    }
    /**
     * full constructor
     * 
     * @param codigo
     *            Descri��o do par�metro
     * @param descricao
     *            Descri��o do par�metro
     * @param indicadorUso
     *            Descri��o do par�metro
     * @param ultimaAlteracao
     *            Descri��o do par�metro
     * @param categoria
     *            Descri��o do par�metro
     */
    public Subcategoria(int codigo, String descricao, Short indicadorUso, Short indicadorSazonalidade,
    		String descricaoAbreviada, String codigoTarifaSocial, Integer codigoGrupoSubcategoria,
    		short numeroFatorFiscalizacao, Short indicadorTarifaConsumo,
            Date ultimaAlteracao, gsan.cadastro.imovel.Categoria categoria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.indicadorSazonalidade = indicadorSazonalidade;
        this.descricaoAbreviada = descricaoAbreviada;
        this.codigoTarifaSocial = codigoTarifaSocial;
        this.codigoGrupoSubcategoria = codigoGrupoSubcategoria;
        this.numeroFatorFiscalizacao = numeroFatorFiscalizacao;
        this.indicadorTarifaConsumo = indicadorTarifaConsumo;
        this.ultimaAlteracao = ultimaAlteracao;
        this.categoria = categoria;
    }

    /**
     * default constructor
     */
    public Subcategoria() {
    }

    /**
     * minimal constructor
     * 
     * @param codigo
     *            Descri��o do par�metro
     * @param descricao
     *            Descri��o do par�metro
     * @param categoria
     *            Descri��o do par�metro
     */
    public Subcategoria(int codigo, String descricao,
            gsan.cadastro.imovel.Categoria categoria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    /**
     * Retorna o valor de id
     * 
     * @return O valor de id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Seta o valor de id
     * 
     * @param id
     *            O novo valor de id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o valor de codigo
     * 
     * @return O valor de codigo
     */
    public int getCodigo() {
        return this.codigo;
    }

    /**
     * Seta o valor de codigo
     * 
     * @param codigo
     *            O novo valor de codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Retorna o valor de descricao
     * 
     * @return O valor de descricao
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Seta o valor de descricao
     * 
     * @param descricao
     *            O novo valor de descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o valor de indicadorUso
     * 
     * @return O valor de indicadorUso
     */
    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    /**
     * Seta o valor de indicadorUso
     * 
     * @param indicadorUso
     *            O novo valor de indicadorUso
     */
    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    /**
     * Retorna o valor de ultimaAlteracao
     * 
     * @return O valor de ultimaAlteracao
     */
    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    /**
     * Seta o valor de ultimaAlteracao
     * 
     * @param ultimaAlteracao
     *            O novo valor de ultimaAlteracao
     */
    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * Retorna o valor de categoria
     * 
     * @return O valor de categoria
     */
    public gsan.cadastro.imovel.Categoria getCategoria() {
        return this.categoria;
    }

    /**
     * Seta o valor de categoria
     * 
     * @param categoria
     *            O novo valor de categoria
     */
    public void setCategoria(gsan.cadastro.imovel.Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * < <Descri��o do m�todo>>
     * 
     * @param other
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof Subcategoria)) {
            return false;
        }
        Subcategoria castOther = (Subcategoria) other;

        return new EqualsBuilder().append(this.getId(), castOther.getId())
//                .append(this.getCategoria(), castOther.getCategoria())
                .isEquals();
    }

    /**
     * < <Descri��o do m�todo>>
     * 
     * @return Descri��o do retorno
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    /**
     * Description of the Method
     * 
     * @return Description of the Return Value
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).append(getCodigo())
                .append(getDescricao()).append(getIndicadorUso()).append(
                        getCategoria()).append(getUltimaAlteracao())
                .toHashCode();
    }
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
		
		filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade(FiltroSubCategoria.CATEGORIA);
		filtroSubCategoria.adicionarParametro(
				new ParametroSimples(FiltroSubCategoria.ID, this.getId()));
		return filtroSubCategoria; 
	}

	/**
	 * @return Retorna o campo codigoTarifaSocial.
	 */
	public String getCodigoTarifaSocial() {
		return codigoTarifaSocial;
	}

	/**
	 * @param codigoTarifaSocial O codigoTarifaSocial a ser setado.
	 */
	public void setCodigoTarifaSocial(String codigoTarifaSocial) {
		this.codigoTarifaSocial = codigoTarifaSocial;
	}

	/**
	 * @return Retorna o campo numeroFatorFiscalizacao.
	 */
	public short getNumeroFatorFiscalizacao() {
		return numeroFatorFiscalizacao;
	}

	/**
	 * @param numeroFatorFiscalizacao O numeroFatorFiscalizacao a ser setado.
	 */
	public void setNumeroFatorFiscalizacao(short numeroFatorFiscalizacao) {
		this.numeroFatorFiscalizacao = numeroFatorFiscalizacao;
	}

	/**
	 * @return Retorna o campo indicadorTarifaConsumo.
	 */
	public Short getIndicadorTarifaConsumo() {
		return indicadorTarifaConsumo;
	}

	/**
	 * @param indicadorTarifaConsumo O indicadorTarifaConsumo a ser setado.
	 */
	public void setIndicadorTarifaConsumo(Short indicadorTarifaConsumo) {
		this.indicadorTarifaConsumo = indicadorTarifaConsumo;
	}

	public Integer getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(Integer quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Integer getCodigoGrupoSubcategoria() {
		return codigoGrupoSubcategoria;
	}

	public void setCodigoGrupoSubcategoria(Integer codigoGrupoSubcategoria) {
		this.codigoGrupoSubcategoria = codigoGrupoSubcategoria;
	}

	/**
	 * <Breve descri��o sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 19/09/2007
	 *
	 * @return
	 */
	public String getDescricaoComId() {
		
		if(this.getId().compareTo(10) == -1){
			descricaoComId = "0" + getCodigo()+ " - " + getDescricao();
		}else{
			descricaoComId = getCodigo()+ " - " + getDescricao();
		}
		
		return descricaoComId;
	}
	
	@Override
	public void initializeLazy() {
		if (this.getCategoria() != null){
			this.getCategoria().initializeLazy();
		}
	}

	public Short getIndicadorSazonalidade() {
		return indicadorSazonalidade;
	}

	public void setIndicadorSazonalidade(Short indicadorSazonalidade) {
		this.indicadorSazonalidade = indicadorSazonalidade;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricao();
	}
}

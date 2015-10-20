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
package gcom.gerencial.cadastro.imovel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GCategoria implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricao;

    /** persistent field */
    private String descricaoAbreviada;

    /** nullable persistent field */
    private Integer consumoMinimo;

    /** nullable persistent field */
    private Integer consumoEstouro;

    /** nullable persistent field */
    private BigDecimal vezesMediaEstouro;

    /** nullable persistent field */
    private Integer mediaBaixoConsumo;

    /** nullable persistent field */
    private BigDecimal porcentagemMediaBaixoConsumo;

    /** nullable persistent field */
    private Integer consumoAlto;

    /** nullable persistent field */
    private BigDecimal vezesMediaAltoConsumo;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set unResumoColetaEsgotos;

    /** persistent field */
    private Set unResumoConsumoAguas;

    /** persistent field */
    private Set unResumoFaturamentos;

    /** persistent field */
    private Set unResumoArrecadacao;

    /** persistent field */
    private Set unResumoLigacaoEconomias;

    /** persistent field */
    private Set gerSubcategorias;

    /** persistent field */
    private Set rgResumoLigacaoEconomias;
    
    /** persistent field */
    private Set UnResumoIndicadorDesempenhoMicromedicaos;    /** CONSTANTES */
    public final static int RESIDENCIAL = 1;  	public final static int COMERCIAL = 2;  	public final static int INDUSTRIAL = 3;  	public final static int PUBLICO = 4;  	/** FIM CONSTANTES */ 	 	 	private Integer quantidadeEconomiasCategoria; 	
    /** full constructor */
    public GCategoria(String descricao, String descricaoAbreviada, Integer consumoMinimo, Integer consumoEstouro, BigDecimal vezesMediaEstouro, Integer mediaBaixoConsumo, BigDecimal porcentagemMediaBaixoConsumo, Integer consumoAlto, BigDecimal vezesMediaAltoConsumo, Short indicadorUso, Date ultimaAlteracao, Set unResumoColetaEsgotos, Set unResumoConsumoAguas, Set unResumoFaturamentos, Set unResumoArrecadacao, Set unResumoLigacaoEconomias, Set gSubcategorias, Set rgResumoLigacaoEconomias) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.consumoMinimo = consumoMinimo;
        this.consumoEstouro = consumoEstouro;
        this.vezesMediaEstouro = vezesMediaEstouro;
        this.mediaBaixoConsumo = mediaBaixoConsumo;
        this.porcentagemMediaBaixoConsumo = porcentagemMediaBaixoConsumo;
        this.consumoAlto = consumoAlto;
        this.vezesMediaAltoConsumo = vezesMediaAltoConsumo;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
        this.unResumoConsumoAguas = unResumoConsumoAguas;
        this.unResumoFaturamentos = unResumoFaturamentos;
        this.unResumoArrecadacao = unResumoArrecadacao;
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
        this.gerSubcategorias = gSubcategorias;
        this.rgResumoLigacaoEconomias = rgResumoLigacaoEconomias;
    }

    /** default constructor */
    public GCategoria() {
    }

    /** minimal constructor */
    public GCategoria(String descricao, String descricaoAbreviada, Set unResumoColetaEsgotos, Set unResumoConsumoAguas, Set unResumoFaturamentos, Set unResumoArrecadacao, Set unResumoLigacaoEconomias, Set gSubcategorias, Set rgResumoLigacaoEconomias) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
        this.unResumoConsumoAguas = unResumoConsumoAguas;
        this.unResumoFaturamentos = unResumoFaturamentos;
        this.unResumoArrecadacao = unResumoArrecadacao;
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
        this.gerSubcategorias = gSubcategorias;
        this.rgResumoLigacaoEconomias = rgResumoLigacaoEconomias;
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

    public Integer getConsumoMinimo() {
        return this.consumoMinimo;
    }

    public void setConsumoMinimo(Integer consumoMinimo) {
        this.consumoMinimo = consumoMinimo;
    }

    public Integer getConsumoEstouro() {
        return this.consumoEstouro;
    }

    public void setConsumoEstouro(Integer consumoEstouro) {
        this.consumoEstouro = consumoEstouro;
    }

    public BigDecimal getVezesMediaEstouro() {
        return this.vezesMediaEstouro;
    }

    public void setVezesMediaEstouro(BigDecimal vezesMediaEstouro) {
        this.vezesMediaEstouro = vezesMediaEstouro;
    }

    public Integer getMediaBaixoConsumo() {
        return this.mediaBaixoConsumo;
    }

    public void setMediaBaixoConsumo(Integer mediaBaixoConsumo) {
        this.mediaBaixoConsumo = mediaBaixoConsumo;
    }

    public BigDecimal getPorcentagemMediaBaixoConsumo() {
        return this.porcentagemMediaBaixoConsumo;
    }

    public void setPorcentagemMediaBaixoConsumo(BigDecimal porcentagemMediaBaixoConsumo) {
        this.porcentagemMediaBaixoConsumo = porcentagemMediaBaixoConsumo;
    }

    public Integer getConsumoAlto() {
        return this.consumoAlto;
    }

    public void setConsumoAlto(Integer consumoAlto) {
        this.consumoAlto = consumoAlto;
    }

    public BigDecimal getVezesMediaAltoConsumo() {
        return this.vezesMediaAltoConsumo;
    }

    public void setVezesMediaAltoConsumo(BigDecimal vezesMediaAltoConsumo) {
        this.vezesMediaAltoConsumo = vezesMediaAltoConsumo;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Set getUnResumoColetaEsgotos() {
        return this.unResumoColetaEsgotos;
    }

    public void setUnResumoColetaEsgotos(Set unResumoColetaEsgotos) {
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
    }

    public Set getUnResumoConsumoAguas() {
        return this.unResumoConsumoAguas;
    }

    public void setUnResumoConsumoAguas(Set unResumoConsumoAguas) {
        this.unResumoConsumoAguas = unResumoConsumoAguas;
    }

    public Set getUnResumoFaturamentos() {
        return this.unResumoFaturamentos;
    }

    public void setUnResumoFaturamentos(Set unResumoFaturamentos) {
        this.unResumoFaturamentos = unResumoFaturamentos;
    }

   

    public Set getUnResumoArrecadacao() {
		return unResumoArrecadacao;
	}

	public void setUnResumoArrecadacao(Set unResumoArrecadacao) {
		this.unResumoArrecadacao = unResumoArrecadacao;
	}

	public Set getUnResumoLigacaoEconomias() {
        return this.unResumoLigacaoEconomias;
    }

    public void setUnResumoLigacaoEconomias(Set unResumoLigacaoEconomias) {
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
    }

 

    public Set getGerSubcategorias() {
		return gerSubcategorias;
	}

	public void setGerSubcategorias(Set gerSubcategorias) {
		this.gerSubcategorias = gerSubcategorias;
	}

	public Set getRgResumoLigacaoEconomias() {
        return this.rgResumoLigacaoEconomias;
    }

    public void setRgResumoLigacaoEconomias(Set rgResumoLigacaoEconomias) {
        this.rgResumoLigacaoEconomias = rgResumoLigacaoEconomias;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }	public Integer getQuantidadeEconomiasCategoria() {		return quantidadeEconomiasCategoria;	}	public void setQuantidadeEconomiasCategoria(Integer quantidadeEconomiasCategoria) {		this.quantidadeEconomiasCategoria = quantidadeEconomiasCategoria;	}

	public Set getUnResumoIndicadorDesempenhoMicromedicaos() {
		return UnResumoIndicadorDesempenhoMicromedicaos;
	}

	public void setUnResumoIndicadorDesempenhoMicromedicaos(
			Set unResumoIndicadorDesempenhoMicromedicaos) {
		UnResumoIndicadorDesempenhoMicromedicaos = unResumoIndicadorDesempenhoMicromedicaos;
	}

}

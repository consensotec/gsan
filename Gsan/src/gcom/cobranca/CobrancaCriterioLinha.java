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
package gcom.cobranca;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CobrancaCriterioLinha extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private BigDecimal valorMinimoDebito;

    /** nullable persistent field */
    private Short quantidadeMinimaContas;

    /** nullable persistent field */
    private BigDecimal valorMaximoDebito;

    /** nullable persistent field */
    private BigDecimal valorMinimoDebitoDebitoAutomatico;

    /** nullable persistent field */
    private Short quantidadeMaximaContas;

    /** nullable persistent field */
    private Short quantidadeMinimaContasDebitoAutomatico;

    /** nullable persistent field */
    private BigDecimal valorMinimoContaMes;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private Short quantidadeMinimaContasParcelamento;

    /** persistent field */
    private gcom.cobranca.CobrancaCriterio cobrancaCriterio;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private Categoria categoria;

    /** full constructor */
    public CobrancaCriterioLinha(BigDecimal valorMinimoDebito, Short quantidadeMinimaContas, BigDecimal valorMaximoDebito, BigDecimal valorMinimoDebitoDebitoAutomatico, Short quantidadeMaximaContas, Short quantidadeMinimaContasDebitoAutomatico, BigDecimal valorMinimoContaMes, Date ultimaAlteracao, gcom.cobranca.CobrancaCriterio cobrancaCriterio, ImovelPerfil imovelPerfil, Categoria categoria) {
        this.valorMinimoDebito = valorMinimoDebito;
        this.quantidadeMinimaContas = quantidadeMinimaContas;
        this.valorMaximoDebito = valorMaximoDebito;
        this.valorMinimoDebitoDebitoAutomatico = valorMinimoDebitoDebitoAutomatico;
        this.quantidadeMaximaContas = quantidadeMaximaContas;
        this.quantidadeMinimaContasDebitoAutomatico = quantidadeMinimaContasDebitoAutomatico;
        this.valorMinimoContaMes = valorMinimoContaMes;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cobrancaCriterio = cobrancaCriterio;
        this.imovelPerfil = imovelPerfil;
        this.categoria = categoria;
    }

    /** default constructor */
    public CobrancaCriterioLinha() {
    }

    /** minimal constructor */
    public CobrancaCriterioLinha(gcom.cobranca.CobrancaCriterio cobrancaCriterio, ImovelPerfil imovelPerfil, Categoria categoria) {
        this.cobrancaCriterio = cobrancaCriterio;
        this.imovelPerfil = imovelPerfil;
        this.categoria = categoria;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorMinimoDebito() {
        return this.valorMinimoDebito;
    }

    public void setValorMinimoDebito(BigDecimal valorMinimoDebito) {
        this.valorMinimoDebito = valorMinimoDebito;
    }

    public Short getQuantidadeMinimaContas() {
        return this.quantidadeMinimaContas;
    }

    public void setQuantidadeMinimaContas(Short quantidadeMinimaContas) {
        this.quantidadeMinimaContas = quantidadeMinimaContas;
    }

    public BigDecimal getValorMaximoDebito() {
        return this.valorMaximoDebito;
    }

    public void setValorMaximoDebito(BigDecimal valorMaximoDebito) {
        this.valorMaximoDebito = valorMaximoDebito;
    }

    public BigDecimal getValorMinimoDebitoDebitoAutomatico() {
        return this.valorMinimoDebitoDebitoAutomatico;
    }

    public void setValorMinimoDebitoDebitoAutomatico(BigDecimal valorMinimoDebitoDebitoAutomatico) {
        this.valorMinimoDebitoDebitoAutomatico = valorMinimoDebitoDebitoAutomatico;
    }

    public Short getQuantidadeMaximaContas() {
        return this.quantidadeMaximaContas;
    }

    public void setQuantidadeMaximaContas(Short quantidadeMaximaContas) {
        this.quantidadeMaximaContas = quantidadeMaximaContas;
    }

    public Short getQuantidadeMinimaContasDebitoAutomatico() {
        return this.quantidadeMinimaContasDebitoAutomatico;
    }

    public void setQuantidadeMinimaContasDebitoAutomatico(Short quantidadeMinimaContasDebitoAutomatico) {
        this.quantidadeMinimaContasDebitoAutomatico = quantidadeMinimaContasDebitoAutomatico;
    }

    public BigDecimal getValorMinimoContaMes() {
        return this.valorMinimoContaMes;
    }

    public void setValorMinimoContaMes(BigDecimal valorMinimoContaMes) {
        this.valorMinimoContaMes = valorMinimoContaMes;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.CobrancaCriterio getCobrancaCriterio() {
        return this.cobrancaCriterio;
    }

    public void setCobrancaCriterio(gcom.cobranca.CobrancaCriterio cobrancaCriterio) {
        this.cobrancaCriterio = cobrancaCriterio;
    }

    public ImovelPerfil getImovelPerfil() {
        return this.imovelPerfil;
    }

    public void setImovelPerfil(ImovelPerfil imovelPerfil) {
        this.imovelPerfil = imovelPerfil;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
    
	public Filtro retornaFiltro(){
		FiltroCobrancaCriterioLinha filtroCobrancaCriterioLinha = new FiltroCobrancaCriterioLinha();

		filtroCobrancaCriterioLinha.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterioLinha.ID,
				this.getId()));
		filtroCobrancaCriterioLinha.adicionarCaminhoParaCarregamentoEntidade("categoria");
		filtroCobrancaCriterioLinha.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		filtroCobrancaCriterioLinha.adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterio");
		
		return filtroCobrancaCriterioLinha;
	}

	public Short getQuantidadeMinimaContasParcelamento() {
		return quantidadeMinimaContasParcelamento;
	}

	public void setQuantidadeMinimaContasParcelamento(
			Short quantidadeMinimaContasParcelamento) {
		this.quantidadeMinimaContasParcelamento = quantidadeMinimaContasParcelamento;
	}

}

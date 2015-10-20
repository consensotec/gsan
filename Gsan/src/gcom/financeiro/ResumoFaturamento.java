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
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoTipo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ResumoFaturamento implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer anoMesReferencia;

    /** nullable persistent field */
    private BigDecimal valorItemFaturamento;

    /** nullable persistent field */
    private Short sequenciaTipoLancamento;

    /** nullable persistent field */
    private Short sequenciaItemTipoLancamento;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private LancamentoItemContabil lancamentoItemContabil;

    /** persistent field */
    private LancamentoTipo lancamentoTipo;

    /** persistent field */
    private LancamentoItem lancamentoItem;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private GerenciaRegional gerenciaRegional;

    /** persistent field */
    private Categoria categoria;
    
    /** persistent field */
    private UnidadeNegocio unidadeNegocio;
    
    private ImovelPerfil imovelPerfil;

    public static final short FATURAMENTO_AGUA_SEQUENCIA_TIPO_LANCAMENTO = 100;
    
    public static final short FATURAMENTO_ESGOTO_SEQUENCIA_TIPO_LANCAMENTO = 200;
    
    public static final short FINANCIAMENTOS_INCLUIDOS_SEQUENCIA_TIPO_LANCAMENTO_1 = 300;
    
    public static final short FINANCIAMENTOS_INCLUIDOS_SEQUENCIA_TIPO_LANCAMENTO_2 = 400;
    
    public static final short JUROS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_1 = 410;
    
    public static final short JUROS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_2 = 420;
    
    public static final short GUIAS_PAGAMENTO_SEQUENCIA_TIPO_LANCAMENTO = 500;
    
    public static final short INCLUSOES_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO_1 = 510;
    
    public static final short INCLUSOES_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO_2 = 599;
    
    public static final short FINANCIAMENTOS_CANCELADOS_SEQUENCIA_TIPO_LANCAMENTO_1 = 700;
    
    public static final short FINANCIAMENTOS_CANCELADOS_SEQUENCIA_TIPO_LANCAMENTO_2 = 800;
    
    public static final short PARCELAMENTOS_CANCELADOS_SEQUENCIA_TIPO_LANCAMENTO_1 = 810;
    
    public static final short PARCELAMENTOS_CANCELADOS_SEQUENCIA_TIPO_LANCAMENTO_2 = 820;
    
    public static final short CANCELAMENTOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO_1 = 900;
    
    public static final short CANCELAMENTOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO_2 = 1020;
    
    public static final short CREDITOS_A_REALIZAR_POR_COBRANCA_INDEVIDA_SEQUENCIA_TIPO_LANCAMENTO = 1030;
    
    public static final short DESCONTOS_INCONDICIONAIS_INCLUIDOS_SEQUENCIA_TIPO_LANCAMENTO = 1040;
    
    public static final short GUIAS_DE_DEVOLUCOES_DE_VALORES_COBRADOS_SEQUENCIA_TIPO_LANCAMENTO = 1050;
    
    public static final short DOACOES_COBRADAS_EM_CONTA_SEQUENCIA_TIPO_LANCAMENTO = 1800;
    
    public static final short VALORES_DEVOLVIDOS_SEQUENCIA_TIPO_LANCAMENTO = 2400;
    
    public static final short IMPOSTOS_DEDUZIDOS_SEQUENCIA_TIPO_LANCAMENTO = 2150;
    
    public static final short DESCONTOS_CONCEDIDOS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_1 = 1500;
    
    public static final short DESCONTOS_CONCEDIDOS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_2 = 1600;
    
    public static final short DESCONTOS_CONCEDIDOS_PARCELAMENTO_SEQUENCIA_ITEM_TIPO_LANCAMENTO = 90;
    
    public static final short DESCONTOS_CONCEDIDOS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_3 = 1480;
    
    public static final short DESCONTOS_CONCEDIDOS_PARCELAMENTO_SEQUENCIA_ITEM_TIPO_LANCAMENTO_3 = 60;
    
    public static final short IMPOSTOS_CANCELADOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO = 2800;
    
    public static final short IMPOSTOS_INCLUIDOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO = 2900;
    
    public static final short OUTROS_CREDITOS_CANCELADOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO = 3000;
    
    public static final short OUTROS_CREDITOS_CONCEDIDOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO = 3100;
    
    public static final short CANCELAMENTOS_POR_PRESCRICAO_SEQUENCIA_TIPO_LANCAMENTO = 4200;
    
    public static final short VALORES_CREDITADOS_SEQUENCIA_TIPO_LANCAMENTO_1 = 1030;
    
    public static final short VALORES_CREDITADOS_SEQUENCIA_TIPO_LANCAMENTO_2 = 1040;
    
    public static final short VALORES_CREDITADOS_SEQUENCIA_TIPO_LANCAMENTO_3 = 1610;
    
    /** full constructor */
    public ResumoFaturamento(Integer anoMesReferencia, BigDecimal valorItemFaturamento, Short sequenciaTipoLancamento, Short sequenciaItemTipoLancamento, Date ultimaAlteracao, LancamentoItemContabil lancamentoItemContabil, LancamentoTipo lancamentoTipo, LancamentoItem lancamentoItem, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria) {
        this.anoMesReferencia = anoMesReferencia;
        this.valorItemFaturamento = valorItemFaturamento;
        this.sequenciaTipoLancamento = sequenciaTipoLancamento;
        this.sequenciaItemTipoLancamento = sequenciaItemTipoLancamento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.lancamentoTipo = lancamentoTipo;
        this.lancamentoItem = lancamentoItem;
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
    }
    
    public ResumoFaturamento(Integer anoMesReferencia, BigDecimal valorItemFaturamento, Short sequenciaTipoLancamento, Short sequenciaItemTipoLancamento, Date ultimaAlteracao, LancamentoItemContabil lancamentoItemContabil, LancamentoTipo lancamentoTipo, LancamentoItem lancamentoItem, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria, UnidadeNegocio unidadeNegocio) {
        this.anoMesReferencia = anoMesReferencia;
        this.valorItemFaturamento = valorItemFaturamento;
        this.sequenciaTipoLancamento = sequenciaTipoLancamento;
        this.sequenciaItemTipoLancamento = sequenciaItemTipoLancamento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.lancamentoTipo = lancamentoTipo;
        this.lancamentoItem = lancamentoItem;
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
        this.unidadeNegocio = unidadeNegocio;
    }

    /** default constructor */
    public ResumoFaturamento() {
    }

    /** minimal constructor */
    public ResumoFaturamento(LancamentoItemContabil lancamentoItemContabil, LancamentoTipo lancamentoTipo, LancamentoItem lancamentoItem, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria) {
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.lancamentoTipo = lancamentoTipo;
        this.lancamentoItem = lancamentoItem;
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
    }
    
    public ResumoFaturamento(LancamentoItemContabil lancamentoItemContabil, LancamentoTipo lancamentoTipo, LancamentoItem lancamentoItem, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria, UnidadeNegocio unidadeNegocio) {
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.lancamentoTipo = lancamentoTipo;
        this.lancamentoItem = lancamentoItem;
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
        this.unidadeNegocio = unidadeNegocio;
    }

    /** constructor para [UC0155] encerrar faturamento do m�s */
    public ResumoFaturamento(Integer anoMesReferencia, BigDecimal valorItemFaturamento, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria) {
        this.anoMesReferencia = anoMesReferencia;
        this.valorItemFaturamento = valorItemFaturamento;        
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
    }
    
    public ResumoFaturamento(Integer anoMesReferencia, BigDecimal valorItemFaturamento, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria, UnidadeNegocio unidadeNegocio) {
        this.anoMesReferencia = anoMesReferencia;
        this.valorItemFaturamento = valorItemFaturamento;        
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
        this.unidadeNegocio = unidadeNegocio;
    }

    /** constructor para [UC0155] encerrar faturamento do m�s */
    public ResumoFaturamento(Integer anoMesReferencia, BigDecimal valorItemFaturamento, Short sequenciaItemTipoLancamento, LancamentoItemContabil lancamentoItemContabil, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria) {
        this.anoMesReferencia = anoMesReferencia;
        this.valorItemFaturamento = valorItemFaturamento;        
        this.sequenciaItemTipoLancamento = sequenciaItemTipoLancamento;        
        this.lancamentoItemContabil = lancamentoItemContabil;              
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
    }
    
    /** constructor para [UC0155] encerrar faturamento do m�s */
    public ResumoFaturamento(Integer anoMesReferencia, BigDecimal valorItemFaturamento, 
    		Short sequenciaItemTipoLancamento, LancamentoItemContabil lancamentoItemContabil, 
    		Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria, UnidadeNegocio unidadeNegocio) {
        this.anoMesReferencia = anoMesReferencia;
        this.valorItemFaturamento = valorItemFaturamento;        
        this.sequenciaItemTipoLancamento = sequenciaItemTipoLancamento;        
        this.lancamentoItemContabil = lancamentoItemContabil;              
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
        this.unidadeNegocio = unidadeNegocio;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(Integer anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public BigDecimal getValorItemFaturamento() {
        return this.valorItemFaturamento;
    }

    public void setValorItemFaturamento(BigDecimal valorItemFaturamento) {
        this.valorItemFaturamento = valorItemFaturamento;
    }

    public Short getSequenciaTipoLancamento() {
        return this.sequenciaTipoLancamento;
    }

    public void setSequenciaTipoLancamento(Short sequenciaTipoLancamento) {
        this.sequenciaTipoLancamento = sequenciaTipoLancamento;
    }

    public Short getSequenciaItemTipoLancamento() {
        return this.sequenciaItemTipoLancamento;
    }

    public void setSequenciaItemTipoLancamento(Short sequenciaItemTipoLancamento) {
        this.sequenciaItemTipoLancamento = sequenciaItemTipoLancamento;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public LancamentoItemContabil getLancamentoItemContabil() {
        return this.lancamentoItemContabil;
    }

    public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil) {
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    public LancamentoTipo getLancamentoTipo() {
        return this.lancamentoTipo;
    }

    public void setLancamentoTipo(LancamentoTipo lancamentoTipo) {
        this.lancamentoTipo = lancamentoTipo;
    }

    public LancamentoItem getLancamentoItem() {
        return this.lancamentoItem;
    }

    public void setLancamentoItem(LancamentoItem lancamentoItem) {
        this.lancamentoItem = lancamentoItem;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public GerenciaRegional getGerenciaRegional() {
        return this.gerenciaRegional;
    }

    public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
        this.gerenciaRegional = gerenciaRegional;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}
	
	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}

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
package gsan.arrecadacao;

import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.financeiro.lancamento.LancamentoItem;
import gsan.financeiro.lancamento.LancamentoItemContabil;
import gsan.financeiro.lancamento.LancamentoTipo;

import java.math.BigDecimal;
import java.util.Date;

public class ResumoArrecadacao {

	private Integer id;
    
    private Integer anoMesReferencia;

    private Short sequenciaTipoLancamento;

    private Short sequenciaItemTipoLancamento;
    
    private Date ultimaAlteracao;
    
    private BigDecimal valorItemArrecadacao;

    private LancamentoItemContabil lancamentoItemContabil;

    private LancamentoTipo lancamentoTipo;

    private LancamentoItem lancamentoItem;

    private Localidade localidade;
    
    private GerenciaRegional gerenciaRegional;
    
    private Categoria categoria;
   
    private RecebimentoTipo recebimentoTipo;
    
    private UnidadeNegocio unidadeNegocio;
    
    public static final short RECEBIMENTOS_CLASSIFICADOS_SEQUENCIA_TIPO_LANCAMENTO_1 = 2000;
    
    public static final short RECEBIMENTOS_CLASSIFICADOS_SEQUENCIA_TIPO_LANCAMENTO_2 = 1550;
    
    public static final short IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_1 = 1200;
    public static final short IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_2 = 1300;
    public static final short IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_3 = 1400;
    public static final short IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_4 = 1500;
    
    public static final short RECEBIMENTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_1 = 5400;
    
    public static final short RECEBIMENTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_2 = 4950;
    
    public static final short IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_1 = 4600;
    public static final short IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_2 = 4700;
    public static final short IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_3 = 4800;
    public static final short IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_4 = 4900;
    
    public static final short RECEBIMENTOS_DESCONTOS_POR_CREDITO = 2470;

	/**
	 * @return Retorna o campo anoMesReferencia.
	 */
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	/**
	 * @param anoMesReferencia O anoMesReferencia a ser setado.
	 */
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
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
	 * @return Retorna o campo gerenciaRegional.
	 */
	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo lancamentoItem.
	 */
	public LancamentoItem getLancamentoItem() {
		return lancamentoItem;
	}

	/**
	 * @param lancamentoItem O lancamentoItem a ser setado.
	 */
	public void setLancamentoItem(LancamentoItem lancamentoItem) {
		this.lancamentoItem = lancamentoItem;
	}

	/**
	 * @return Retorna o campo lancamentoItemContabil.
	 */
	public LancamentoItemContabil getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}

	/**
	 * @param lancamentoItemContabil O lancamentoItemContabil a ser setado.
	 */
	public void setLancamentoItemContabil(
			LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	/**
	 * @return Retorna o campo lancamentoTipo.
	 */
	public LancamentoTipo getLancamentoTipo() {
		return lancamentoTipo;
	}

	/**
	 * @param lancamentoTipo O lancamentoTipo a ser setado.
	 */
	public void setLancamentoTipo(LancamentoTipo lancamentoTipo) {
		this.lancamentoTipo = lancamentoTipo;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public Localidade getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo recebimentoTipo.
	 */
	public RecebimentoTipo getRecebimentoTipo() {
		return recebimentoTipo;
	}

	/**
	 * @param recebimentoTipo O recebimentoTipo a ser setado.
	 */
	public void setRecebimentoTipo(RecebimentoTipo recebimentoTipo) {
		this.recebimentoTipo = recebimentoTipo;
	}

	/**
	 * @return Retorna o campo sequenciaItemTipoLancamento.
	 */
	public Short getSequenciaItemTipoLancamento() {
		return sequenciaItemTipoLancamento;
	}

	/**
	 * @param sequenciaItemTipoLancamento O sequenciaItemTipoLancamento a ser setado.
	 */
	public void setSequenciaItemTipoLancamento(Short sequenciaItemTipoLancamento) {
		this.sequenciaItemTipoLancamento = sequenciaItemTipoLancamento;
	}

	/**
	 * @return Retorna o campo sequenciaTipoLancamento.
	 */
	public Short getSequenciaTipoLancamento() {
		return sequenciaTipoLancamento;
	}

	/**
	 * @param sequenciaTipoLancamento O sequenciaTipoLancamento a ser setado.
	 */
	public void setSequenciaTipoLancamento(Short sequenciaTipoLancamento) {
		this.sequenciaTipoLancamento = sequenciaTipoLancamento;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo valorItemArrecadacao.
	 */
	public BigDecimal getValorItemArrecadacao() {
		return valorItemArrecadacao;
	}

	/**
	 * @param valorItemArrecadacao O valorItemArrecadacao a ser setado.
	 */
	public void setValorItemArrecadacao(BigDecimal valorItemArrecadacao) {
		this.valorItemArrecadacao = valorItemArrecadacao;
	}

	public ResumoArrecadacao() {
		
	}
	
	public ResumoArrecadacao(Integer id, Integer anoMesReferencia, Short sequenciaTipoLancamento, Short sequenciaItemTipoLancamento, BigDecimal valorItemArrecadacao, Date ultimaAlteracao, LancamentoItemContabil lancamentoItemContabil, LancamentoTipo lancamentoTipo, LancamentoItem lancamentoItem, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria, RecebimentoTipo recebimentoTipo) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.sequenciaTipoLancamento = sequenciaTipoLancamento;
		this.sequenciaItemTipoLancamento = sequenciaItemTipoLancamento;
		this.ultimaAlteracao = ultimaAlteracao;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.lancamentoTipo = lancamentoTipo;
		this.lancamentoItem = lancamentoItem;
		this.localidade = localidade;
		this.gerenciaRegional = gerenciaRegional;
		this.categoria = categoria;
		this.recebimentoTipo = recebimentoTipo;
		this.valorItemArrecadacao = valorItemArrecadacao;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	
}
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
package gcom.faturamento.bean;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.faturamento.ConsumoFaixaCategoria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;


/**
 * Classe que ir� auxiliar no formato de entrada da pesquisa 
 * de emitir histograma de esgoto por economia
 *
 * @author Rafael Pinto
 * 
 * @date 07/11/2007
 */
public class FiltrarEmitirHistogramaEsgotoEconomiaHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int opcaoTotalizacao;
	private Integer mesAnoFaturamento;
	
	private GerenciaRegional gerenciaRegional = null;
	private UnidadeNegocio unidadeNegocio = null;
	private Localidade eloPolo = null;
	private Localidade localidade = null;
	private SetorComercial setorComercial = null;
	private Quadra quadra = null;
	private Integer tarifa = null;
	private Integer indicadorTarifaCategoria = null;
	
	private BigDecimal percentualLigacaoEsgoto = null;
	
	private CategoriaTipo tipoCategoria = null;
	
	private Collection<Integer> colecaoCategoria = null;
	private Collection<Integer> colecaoSubcategoria = null;
	private Collection<Integer> colecaoTarifa = null;
	private Collection<Integer> colecaoPerfilImovel = null;
	private Collection<Integer> colecaoEsferaPoder = null;
	private Collection<Integer> colecaoSituacaoLigacaoEsgoto = null;
	private Collection<BigDecimal> colecaoPercentualLigacaoEsgoto = null;
	
	private Short consumo = null;
	private Short poco = null;
	private Short volumoFixoEsgoto = null;
	
	private LinkedHashMap linkedHashMapConsumoFaixaCategoria = new LinkedHashMap();
	
	//Valores variados depende da totalizacao
	private Short medicao = null;
	private String tipoGroupBy = null;
	private String tipoOrderBy = null;
	private Categoria categoria = null;
	private Subcategoria subcategoria = null;
	private ConsumoFaixaCategoria consumoFaixaCategoria = null;
	
	private Short indicadorSimulacao = null;
	
	public FiltrarEmitirHistogramaEsgotoEconomiaHelper() { }
	
	public FiltrarEmitirHistogramaEsgotoEconomiaHelper(FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) { 
		
		this.opcaoTotalizacao = filtro.getOpcaoTotalizacao();
		this.mesAnoFaturamento = filtro.getMesAnoFaturamento();
		
		this.gerenciaRegional = filtro.getGerenciaRegional();
		this.unidadeNegocio = filtro.getUnidadeNegocio();
		this.eloPolo = filtro.getEloPolo();
		this.localidade = filtro.getLocalidade();
		this.setorComercial = filtro.getSetorComercial();
		this.quadra = filtro.getQuadra();
		
		this.tipoCategoria = filtro.getTipoCategoria();
		this.percentualLigacaoEsgoto = filtro.getPercentualLigacaoEsgoto();
		
		this.colecaoCategoria = filtro.getColecaoCategoria();
		this.colecaoSubcategoria = filtro.getColecaoSubcategoria();
		this.colecaoTarifa = filtro.getColecaoTarifa();
		this.colecaoPerfilImovel = filtro.getColecaoPerfilImovel();
		this.colecaoEsferaPoder = filtro.getColecaoEsferaPoder();
		this.colecaoSituacaoLigacaoEsgoto = filtro.getColecaoSituacaoLigacaoEsgoto();
		this.colecaoPercentualLigacaoEsgoto = filtro.getColecaoPercentualLigacaoEsgoto();

		this.consumo = filtro.getConsumo();
		this.poco = filtro.getPoco();
		this.volumoFixoEsgoto = filtro.getVolumoFixoEsgoto();
		
		this.linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
		
		this.medicao = filtro.getMedicao();
		this.tipoGroupBy = filtro.getTipoGroupBy();
		this.tipoOrderBy = filtro.getTipoOrderBy();
		this.categoria = filtro.getCategoria();
		this.consumoFaixaCategoria = filtro.getConsumoFaixaCategoria();
		this.indicadorTarifaCategoria = filtro.getIndicadorTarifaCategoria();	
		this.indicadorSimulacao = filtro.getIndicadorSimulacao();
	}
	
	
	public String getTipoOrderBy() {
		return tipoOrderBy;
	}

	public void setTipoOrderBy(String tipoOrderBy) {
		this.tipoOrderBy = tipoOrderBy;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public ConsumoFaixaCategoria getConsumoFaixaCategoria() {
		return consumoFaixaCategoria;
	}

	public void setConsumoFaixaCategoria(ConsumoFaixaCategoria consumoFaixaCategoria) {
		this.consumoFaixaCategoria = consumoFaixaCategoria;
	}

	public LinkedHashMap getLinkedHashMapConsumoFaixaCategoria() {
		return linkedHashMapConsumoFaixaCategoria;
	}

	public void setLinkedHashMapConsumoFaixaCategoria(
			LinkedHashMap linkedHashMapConsumoFaixaCategoria) {
		this.linkedHashMapConsumoFaixaCategoria = linkedHashMapConsumoFaixaCategoria;
	}

	public String getTipoGroupBy() {
		return tipoGroupBy;
	}

	public void setTipoGroupBy(String tipoGroupBy) {
		this.tipoGroupBy = tipoGroupBy;
	}

	public CategoriaTipo getTipoCategoria() {
		return tipoCategoria;
	}

	public void setTipoCategoria(CategoriaTipo tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	public Collection<Integer> getColecaoCategoria() {
		return colecaoCategoria;
	}

	public void setColecaoCategoria(Collection<Integer> colecaoCategoria) {
		this.colecaoCategoria = colecaoCategoria;
	}

	public Collection<Integer> getColecaoEsferaPoder() {
		return colecaoEsferaPoder;
	}

	public void setColecaoEsferaPoder(Collection<Integer> colecaoEsferaPoder) {
		this.colecaoEsferaPoder = colecaoEsferaPoder;
	}

	public Collection<Integer> getColecaoPerfilImovel() {
		return colecaoPerfilImovel;
	}

	public void setColecaoPerfilImovel(Collection<Integer> colecaoPerfilImovel) {
		this.colecaoPerfilImovel = colecaoPerfilImovel;
	}

	public Collection<Integer> getColecaoSituacaoLigacaoEsgoto() {
		return colecaoSituacaoLigacaoEsgoto;
	}

	public void setColecaoSituacaoLigacaoEsgoto(
			Collection<Integer> colecaoSituacaoLigacaoEsgoto) {
		this.colecaoSituacaoLigacaoEsgoto = colecaoSituacaoLigacaoEsgoto;
	}

	public Collection<Integer> getColecaoTarifa() {
		return colecaoTarifa;
	}

	public void setColecaoTarifa(Collection<Integer> colecaoTarifa) {
		this.colecaoTarifa = colecaoTarifa;
	}

	public Short getConsumo() {
		return consumo;
	}

	public void setConsumo(Short consumo) {
		this.consumo = consumo;
	}

	public Localidade getEloPolo() {
		return eloPolo;
	}

	public void setEloPolo(Localidade eloPolo) {
		this.eloPolo = eloPolo;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Short getMedicao() {
		return medicao;
	}

	public void setMedicao(Short medicao) {
		this.medicao = medicao;
	}

	public Integer getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}

	public void setMesAnoFaturamento(Integer mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}

	public int getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(int opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public Short getPoco() {
		return poco;
	}

	public void setPoco(Short poco) {
		this.poco = poco;
	}

	public Quadra getQuadra() {
		return quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}


	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Short getVolumoFixoEsgoto() {
		return volumoFixoEsgoto;
	}

	public void setVolumoFixoEsgoto(Short volumoFixoEsgoto) {
		this.volumoFixoEsgoto = volumoFixoEsgoto;
	}

	public Collection<BigDecimal> getColecaoPercentualLigacaoEsgoto() {
		return colecaoPercentualLigacaoEsgoto;
	}

	public void setColecaoPercentualLigacaoEsgoto(
			Collection<BigDecimal> colecaoPercentualLigacaoEsgoto) {
		this.colecaoPercentualLigacaoEsgoto = colecaoPercentualLigacaoEsgoto;
	}

	public BigDecimal getPercentualLigacaoEsgoto() {
		return percentualLigacaoEsgoto;
	}

	public void setPercentualLigacaoEsgoto(BigDecimal percentualLigacaoEsgoto) {
		this.percentualLigacaoEsgoto = percentualLigacaoEsgoto;
	}

	public Integer getTarifa() {
		return tarifa;
	}

	public void setTarifa(Integer tarifa) {
		this.tarifa = tarifa;
	}

	public Short getIndicadorSimulacao() {
		return indicadorSimulacao;
	}

	public void setIndicadorSimulacao(Short indicadorSimulacao) {
		this.indicadorSimulacao = indicadorSimulacao;
	}

	public Integer getIndicadorTarifaCategoria() {
		return indicadorTarifaCategoria;
	}

	public void setIndicadorTarifaCategoria(Integer indicadorTarifaCategoria) {
		this.indicadorTarifaCategoria = indicadorTarifaCategoria;
	}
	
	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public Collection<Integer> getColecaoSubcategoria() {
		return colecaoSubcategoria;
	}

	public void setColecaoSubcategoria(Collection<Integer> colecaoSubcategoria) {
		this.colecaoSubcategoria = colecaoSubcategoria;
	}
	
	
	
	
}
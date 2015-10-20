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
package gcom.gui.faturamento;


import gcom.faturamento.ConsumoFaixaLigacao;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC00601] Informar Dados para Emiss�o do Histograma de �gua
 * 
 * @author Rafael Pinto
 *
 * @date 30/05/2007
 */

public class EmissaoHistogramaAguaDadosInformarActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String indicadorTarifaCategoria;
	private String opcaoTotalizacao;
	private String mesAnoFaturamento;
	private String eloPolo;
	private String nomeEloPolo;
	private String localidade;
	private String nomeLocalidade;
	private String setorComercial;
	private String nomeSetorComercial;
	private String quadra;
	private String nomeQuadra;
	private String consumo;
	private String medicao;
	private String poco;
	private String volumoFixoAgua;
	
	private String gerenciaRegional;
	private String unidadeNegocio;
	private String tipoCategoria;
	
	private String[] categoria;
	private String[] subcategoria;
	private String[] tarifa;
	private String[] perfilImovel;
	private String[] esferaPoder;
	private String[] situacaoLigacaoAgua;
	
	//Parte do Popup
	private String tituloFaixaConsumo;
	private String subTituloFaixaConsumo;
	private String limiteInferiorFaixa;
	private String limiteSuperiorFaixa;
	private String tipoConsumo;
	
	private Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = new ArrayList<ConsumoFaixaLigacao>();
	private Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = new ArrayList<ConsumoFaixaLigacao>();
	
	private String tamanhoColecaoMedido = "0";
	private String tamanhoColecaoNaoMedido = "0";
	
	
	public String getTamanhoColecaoMedido() {
		return tamanhoColecaoMedido;
	}
	
	public void setTamanhoColecaoMedido(String tamanhoColecaoMedido) {
		this.tamanhoColecaoMedido = tamanhoColecaoMedido;
	}
	
	public String getTamanhoColecaoNaoMedido() {
		return tamanhoColecaoNaoMedido;
	}
	public void setTamanhoColecaoNaoMedido(String tamanhoColecaoNaoMedido) {
		this.tamanhoColecaoNaoMedido = tamanhoColecaoNaoMedido;
	}
	public Collection<ConsumoFaixaLigacao> getColecaoConsumoFaixaLigacaoMedido() {
		return colecaoConsumoFaixaLigacaoMedido;
	}
	public void setColecaoConsumoFaixaLigacaoMedido(
			Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido) {
		this.colecaoConsumoFaixaLigacaoMedido = colecaoConsumoFaixaLigacaoMedido;
	}
	public Collection<ConsumoFaixaLigacao> getColecaoConsumoFaixaLigacaoNaoMedido() {
		return colecaoConsumoFaixaLigacaoNaoMedido;
	}
	public void setColecaoConsumoFaixaLigacaoNaoMedido(
			Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido) {
		this.colecaoConsumoFaixaLigacaoNaoMedido = colecaoConsumoFaixaLigacaoNaoMedido;
	}
	public String getLimiteInferiorFaixa() {
		return limiteInferiorFaixa;
	}
	public void setLimiteInferiorFaixa(String limiteInferiorFaixa) {
		this.limiteInferiorFaixa = limiteInferiorFaixa;
	}
	public String getLimiteSuperiorFaixa() {
		return limiteSuperiorFaixa;
	}
	public void setLimiteSuperiorFaixa(String limiteSuperiorFaixa) {
		this.limiteSuperiorFaixa = limiteSuperiorFaixa;
	}
	public String getSubTituloFaixaConsumo() {
		return subTituloFaixaConsumo;
	}
	public void setSubTituloFaixaConsumo(String subTituloFaixaConsumo) {
		this.subTituloFaixaConsumo = subTituloFaixaConsumo;
	}
	public String getTipoConsumo() {
		return tipoConsumo;
	}
	public void setTipoConsumo(String tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}
	public String getTituloFaixaConsumo() {
		return tituloFaixaConsumo;
	}
	public void setTituloFaixaConsumo(String tituloFaixaConsumo) {
		this.tituloFaixaConsumo = tituloFaixaConsumo;
	}
	public String[] getCategoria() {
		return categoria;
	}
	public void setCategoria(String[] categoria) {
		this.categoria = categoria;
	}
	public String[] getEsferaPoder() {
		return esferaPoder;
	}
	public void setEsferaPoder(String[] esferaPoder) {
		this.esferaPoder = esferaPoder;
	}
	public String[] getPerfilImovel() {
		return perfilImovel;
	}
	public void setPerfilImovel(String[] perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	public String[] getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String[] situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public String[] getTarifa() {
		return tarifa;
	}
	public void setTarifa(String[] tarifa) {
		this.tarifa = tarifa;
	}
	public String getTipoCategoria() {
		return tipoCategoria;
	}
	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}
	public String getConsumo() {
		return consumo;
	}
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	public String getEloPolo() {
		return eloPolo;
	}
	public void setEloPolo(String eloPolo) {
		this.eloPolo = eloPolo;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getMedicao() {
		return medicao;
	}
	public void setMedicao(String medicao) {
		this.medicao = medicao;
	}
	public String getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}
	public void setMesAnoFaturamento(String mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}
	public String getNomeEloPolo() {
		return nomeEloPolo;
	}
	public void setNomeEloPolo(String nomeEloPolo) {
		this.nomeEloPolo = nomeEloPolo;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getNomeQuadra() {
		return nomeQuadra;
	}
	public void setNomeQuadra(String nomeQuadra) {
		this.nomeQuadra = nomeQuadra;
	}
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}
	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}
	public String getPoco() {
		return poco;
	}
	public void setPoco(String poco) {
		this.poco = poco;
	}
	public String getQuadra() {
		return quadra;
	}
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	public String getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}
	public String getVolumoFixoAgua() {
		return volumoFixoAgua;
	}
	public void setVolumoFixoAgua(String volumoFixoAgua) {
		this.volumoFixoAgua = volumoFixoAgua;
	}
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}
	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public String getIndicadorTarifaCategoria() {
		return indicadorTarifaCategoria;
	}

	public void setIndicadorTarifaCategoria(String indicadorTarifaCategoria) {
		this.indicadorTarifaCategoria = indicadorTarifaCategoria;
	}

	public String[] getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String[] subcategoria) {
		this.subcategoria = subcategoria;
	}
	
	
	


}
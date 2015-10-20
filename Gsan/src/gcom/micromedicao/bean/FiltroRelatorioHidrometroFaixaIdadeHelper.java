/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.micromedicao.bean;

import gcom.micromedicao.hidrometro.HidrometroFaixaIdade;
import gcom.util.Util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * [UC1682] - Relatório Sintético de Hidrômetros por Faixa de Idade
 * [UC1683] - Relatório Analítico de Hidrômetros por Faixa de Idade
 * 
 * @author Vivianne Sousa
 * @date 05/06/2015
 */
public class FiltroRelatorioHidrometroFaixaIdadeHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idLocalidade;
	private Integer codigoSetorComercial;
	private Integer idRota;
	private Integer numeroQuadra;
	private Collection<Integer> idPerfilImovel;
	private Collection<Integer> idCategoria;
	private Collection<Integer> idSubcategoria;
	private Integer qtdeEconomiasInicial;
	private Integer qtdeEconomiasFinal;
	private Collection<Integer> idCapacidadeHidrometro;
	private Collection<Integer> idMarcaHidrometro;
	private Collection<Integer> idAnormalidade;
	private Integer leituraMinima;
	private Integer faixaConsumoMesInicial;
	private Integer faixaConsumoMesFinal;
	private Integer faixaConsumoMedioInicial;
	private Integer faixaConsumoMedioFinal;
	private Integer anoMes;
	private String opcaoTotalizacao;
	private Collection<HidrometroFaixaIdade> faixas;
	
	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public Integer getIdRota() {
		return idRota;
	}
	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
	public Collection<Integer> getIdPerfilImovel() {
		return idPerfilImovel;
	}
	public void setIdPerfilImovel(Collection<Integer> idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}
	public Collection<Integer> getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Collection<Integer> idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Collection<Integer> getIdSubcategoria() {
		return idSubcategoria;
	}
	public void setIdSubcategoria(Collection<Integer> idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}
	public Integer getQtdeEconomiasInicial() {
		return qtdeEconomiasInicial;
	}
	public void setQtdeEconomiasInicial(Integer qtdeEconomiasInicial) {
		this.qtdeEconomiasInicial = qtdeEconomiasInicial;
	}
	public Integer getQtdeEconomiasFinal() {
		return qtdeEconomiasFinal;
	}
	public void setQtdeEconomiasFinal(Integer qtdeEconomiasFinal) {
		this.qtdeEconomiasFinal = qtdeEconomiasFinal;
	}
	public Collection<Integer> getIdCapacidadeHidrometro() {
		return idCapacidadeHidrometro;
	}
	public void setIdCapacidadeHidrometro(Collection<Integer> idCapacidadeHidrometro) {
		this.idCapacidadeHidrometro = idCapacidadeHidrometro;
	}
	public Collection<Integer> getIdMarcaHidrometro() {
		return idMarcaHidrometro;
	}
	public void setIdMarcaHidrometro(Collection<Integer> idMarcaHidrometro) {
		this.idMarcaHidrometro = idMarcaHidrometro;
	}
	public Collection<Integer> getIdAnormalidade() {
		return idAnormalidade;
	}
	public void setIdAnormalidade(Collection<Integer> idAnormalidade) {
		this.idAnormalidade = idAnormalidade;
	}
	public Integer getLeituraMinima() {
		return leituraMinima;
	}
	public void setLeituraMinima(Integer leituraMinima) {
		this.leituraMinima = leituraMinima;
	}
	public Integer getFaixaConsumoMesInicial() {
		return faixaConsumoMesInicial;
	}
	public void setFaixaConsumoMesInicial(Integer faixaConsumoMesInicial) {
		this.faixaConsumoMesInicial = faixaConsumoMesInicial;
	}
	public Integer getFaixaConsumoMesFinal() {
		return faixaConsumoMesFinal;
	}
	public void setFaixaConsumoMesFinal(Integer faixaConsumoMesFinal) {
		this.faixaConsumoMesFinal = faixaConsumoMesFinal;
	}
	public Integer getFaixaConsumoMedioInicial() {
		return faixaConsumoMedioInicial;
	}
	public void setFaixaConsumoMedioInicial(Integer faixaConsumoMedioInicial) {
		this.faixaConsumoMedioInicial = faixaConsumoMedioInicial;
	}
	public Integer getFaixaConsumoMedioFinal() {
		return faixaConsumoMedioFinal;
	}
	public void setFaixaConsumoMedioFinal(Integer faixaConsumoMedioFinal) {
		this.faixaConsumoMedioFinal = faixaConsumoMedioFinal;
	}
	public Integer getAnoMes() {
		return anoMes;
	}
	public void setAnoMes(Integer anoMes) {
		this.anoMes = anoMes;
	}
	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}
	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}
	public Collection getFaixas() {
		return faixas;
	}
	public void setFaixas(Collection faixas) {
		this.faixas = faixas;
	}
	
	public Date getDataMaximaInstalacaoHidrometro() {
		Date dataInstalacao = Util.obterUltimaDataMes(Util.obterMes(anoMes), Util.obterAno(anoMes));  
		return dataInstalacao;
	}
}

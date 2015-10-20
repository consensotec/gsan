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
package gcom.atualizacaocadastral;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ResumoDadosFinanceirosAtualizacaoCadastralDM implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer comando;
	private Integer cadastrador;
	private Date dataGeracao;
	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idLocalidade;
	private Integer codSetorAntes;
	private Integer numQuadraAntes;
	private Integer codSetorDepois;
	private Integer numQuadraDepois;
	private Integer idLocalizacaoAntes;
	private Integer idLocalizacaoDepois;
	private Integer idTempo;
	private Integer idUsuario;
	private Integer qtdeImovelParaVisita;
	private Integer qtdeImovelAVisitar;
	private Integer qtdeImovelComPendencia;
	private Integer qtdeImovelNovo;
	
	private Integer qtdeImovelRetornarCampo;
	private Integer qtdeImovelSemAlteracao;
	private Integer qtdeImovelComAlterecao;
	private Integer qtdeAlteracaoNome;
	private Integer qtdeAlteracaoEndereco;
	private Integer qtdeAlteracaoCategoria;
	private Integer qtdeAlteracaoSubcategoria;
	private Integer qtdeAlteracaoEconomia;
	private Integer qtdeAlteracaoSituacaoAgua;
	private Integer qtdeAlteracaoSituacaoEsgoto;
	private Integer qtdeAlteracaoHidrometro;
	private Integer qtdeAlteracaoRg;
	private Integer qtdeInclusaoRg;
	private Integer qtdeAlteracaoCpfCnpj;
	private Integer qtdeInclusaoCpfCnpj;
	private Integer qtdeExclusaoCpfCnpj;
	private Integer qtdeImovelResAntes;
	private Integer qtdeImovelResDepois;
	private Integer qtdeImovelComAntes;
	private Integer qtdeImovelComDepois;
	private Integer qtdeImovelIndAntes;
	private Integer qtdeImovelIndDepois;
	private Integer qtdeImovelPubAntes;
	private Integer qtdeImovelPubDepois;
	private Integer qtdeEconomiaResAntes;
	private Integer qtdeEconomiaResDepois;
	private Integer qtdeEconomiaComAntes;
	private Integer qtdeEconomiaComDepois;
	private Integer qtdeEconomiaIndAntes;
	private Integer qtdeEconomiaIndDepois;
	private Integer qtdeEconomiaPubAntes;
	private Integer qtdeEconomiaPubDepois;
	private Integer qtdeImovelPotAguaAntes;
	private Integer qtdeImovelPotAguaDepois;
	private Integer qtdeImovelFacAguaAntes;
	private Integer qtdeImovelFacAguaDepois;
	private Integer qtdeImovelLigAguaAntes;
	private Integer qtdeImovelLigAguaDepois;
	private Integer qtdeImovelCorAguaAntes;
	private Integer qtdeImovelCorAguaDepois;
	private Integer qtdeImovelSupAguaAntes;
	private Integer qtdeImovelSupAguaDepois;
	private Integer qtdeImovelPotEsgotoAntes;
	private Integer qtdeImovelPotEsgotoDepois;
	private Integer qtdeImovelFacEsgotoAntes;
	private Integer qtdeImovelFacEsgotoDepois;
	private Integer qtdeImovelLigEsgotoAntes;
	private Integer qtdeImovelLigEsgotoDepois;
	private Integer qtdeImovelSupEsgotoAntes;
	private Integer qtdeImovelSupEsgotoDepois;
		  
	private BigDecimal valorAguaAntes;
	private BigDecimal valorAguaDepois;
	private BigDecimal valorEsgotoAntes;
	private BigDecimal valorEsgotoDepois;
	private BigDecimal valorMulta;
	private BigDecimal valorConsumoFraudado;
	
	private Date ultimaAlteracao;

	public ResumoDadosFinanceirosAtualizacaoCadastralDM() { }

	public ResumoDadosFinanceirosAtualizacaoCadastralDM(Object[] array) {
		comando = (Integer) array[0];
		cadastrador = (Integer) array[1];
		dataGeracao = (Date) array[2];
		idGerenciaRegional = (Integer) array[3];
		idUnidadeNegocio = (Integer) array[4];
		idLocalidade = (Integer) array[5];
		codSetorAntes = (Integer) array[6];
		numQuadraAntes = (Integer) array[7];
		codSetorDepois =  (Integer) array[8];
		numQuadraDepois =  (Integer) array[9];
		qtdeImovelParaVisita = (Integer) array[10];
		qtdeImovelAVisitar = (Integer) array[11];
		qtdeImovelComPendencia = (Integer) array[12];
		qtdeImovelNovo = (Integer) array[13];
		qtdeImovelRetornarCampo = (Integer) array[14];
		ultimaAlteracao = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getComando() {
		return comando;
	}

	public void setComando(Integer comando) {
		this.comando = comando;
	}

	public Integer getCadastrador() {
		return cadastrador;
	}

	public void setCadastrador(Integer cadastrador) {
		this.cadastrador = cadastrador;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

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

	public Integer getCodSetorAntes() {
		return codSetorAntes;
	}

	public void setCodSetorAntes(Integer codSetorAntes) {
		this.codSetorAntes = codSetorAntes;
	}

	public Integer getNumQuadraAntes() {
		return numQuadraAntes;
	}

	public void setNumQuadraAntes(Integer numQuadraAntes) {
		this.numQuadraAntes = numQuadraAntes;
	}

	public Integer getCodSetorDepois() {
		return codSetorDepois;
	}

	public void setCodSetorDepois(Integer codSetorDepois) {
		this.codSetorDepois = codSetorDepois;
	}

	public Integer getNumQuadraDepois() {
		return numQuadraDepois;
	}

	public void setNumQuadraDepois(Integer numQuadraDepois) {
		this.numQuadraDepois = numQuadraDepois;
	}

	public Integer getIdLocalizacaoAntes() {
		return idLocalizacaoAntes;
	}

	public void setIdLocalizacaoAntes(Integer idLocalizacaoAntes) {
		this.idLocalizacaoAntes = idLocalizacaoAntes;
	}

	public Integer getIdLocalizacaoDepois() {
		return idLocalizacaoDepois;
	}

	public void setIdLocalizacaoDepois(Integer idLocalizacaoDepois) {
		this.idLocalizacaoDepois = idLocalizacaoDepois;
	}

	public Integer getIdTempo() {
		return idTempo;
	}

	public void setIdTempo(Integer idTempo) {
		this.idTempo = idTempo;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getQtdeImovelParaVisita() {
		return qtdeImovelParaVisita;
	}

	public void setQtdeImovelParaVisita(Integer qtdeImovelParaVisita) {
		this.qtdeImovelParaVisita = qtdeImovelParaVisita;
	}

	public Integer getQtdeImovelAVisitar() {
		return qtdeImovelAVisitar;
	}

	public void setQtdeImovelAVisitar(Integer qtdeImovelAVisitar) {
		this.qtdeImovelAVisitar = qtdeImovelAVisitar;
	}

	public Integer getQtdeImovelComPendencia() {
		return qtdeImovelComPendencia;
	}

	public void setQtdeImovelComPendencia(Integer qtdeImovelComPendencia) {
		this.qtdeImovelComPendencia = qtdeImovelComPendencia;
	}

	public Integer getQtdeImovelNovo() {
		return qtdeImovelNovo;
	}

	public void setQtdeImovelNovo(Integer qtdeImovelNovo) {
		this.qtdeImovelNovo = qtdeImovelNovo;
	}

	public Integer getQtdeImovelSemAlteracao() {
		return qtdeImovelSemAlteracao;
	}

	public void setQtdeImovelSemAlteracao(Integer qtdeImovelSemAlteracao) {
		this.qtdeImovelSemAlteracao = qtdeImovelSemAlteracao;
	}

	public Integer getQtdeImovelComAlterecao() {
		return qtdeImovelComAlterecao;
	}

	public void setQtdeImovelComAlterecao(Integer qtdeImovelComAlterecao) {
		this.qtdeImovelComAlterecao = qtdeImovelComAlterecao;
	}

	public Integer getQtdeAlteracaoNome() {
		return qtdeAlteracaoNome;
	}

	public void setQtdeAlteracaoNome(Integer qtdeAlteracaoNome) {
		this.qtdeAlteracaoNome = qtdeAlteracaoNome;
	}

	public Integer getQtdeAlteracaoEndereco() {
		return qtdeAlteracaoEndereco;
	}

	public void setQtdeAlteracaoEndereco(Integer qtdeAlteracaoEndereco) {
		this.qtdeAlteracaoEndereco = qtdeAlteracaoEndereco;
	}

	public Integer getQtdeAlteracaoCategoria() {
		return qtdeAlteracaoCategoria;
	}

	public void setQtdeAlteracaoCategoria(Integer qtdeAlteracaoCategoria) {
		this.qtdeAlteracaoCategoria = qtdeAlteracaoCategoria;
	}

	public Integer getQtdeAlteracaoSubcategoria() {
		return qtdeAlteracaoSubcategoria;
	}

	public void setQtdeAlteracaoSubcategoria(Integer qtdeAlteracaoSubcategoria) {
		this.qtdeAlteracaoSubcategoria = qtdeAlteracaoSubcategoria;
	}

	public Integer getQtdeAlteracaoEconomia() {
		return qtdeAlteracaoEconomia;
	}

	public void setQtdeAlteracaoEconomia(Integer qtdeAlteracaoEconomia) {
		this.qtdeAlteracaoEconomia = qtdeAlteracaoEconomia;
	}

	public Integer getQtdeAlteracaoSituacaoAgua() {
		return qtdeAlteracaoSituacaoAgua;
	}

	public void setQtdeAlteracaoSituacaoAgua(Integer qtdeAlteracaoSituacaoAgua) {
		this.qtdeAlteracaoSituacaoAgua = qtdeAlteracaoSituacaoAgua;
	}

	public Integer getQtdeAlteracaoSituacaoEsgoto() {
		return qtdeAlteracaoSituacaoEsgoto;
	}

	public void setQtdeAlteracaoSituacaoEsgoto(Integer qtdeAlteracaoSituacaoEsgoto) {
		this.qtdeAlteracaoSituacaoEsgoto = qtdeAlteracaoSituacaoEsgoto;
	}

	public Integer getQtdeAlteracaoHidrometro() {
		return qtdeAlteracaoHidrometro;
	}

	public void setQtdeAlteracaoHidrometro(Integer qtdeAlteracaoHidrometro) {
		this.qtdeAlteracaoHidrometro = qtdeAlteracaoHidrometro;
	}

	public Integer getQtdeAlteracaoRg() {
		return qtdeAlteracaoRg;
	}

	public void setQtdeAlteracaoRg(Integer qtdeAlteracaoRg) {
		this.qtdeAlteracaoRg = qtdeAlteracaoRg;
	}

	public Integer getQtdeInclusaoRg() {
		return qtdeInclusaoRg;
	}

	public void setQtdeInclusaoRg(Integer qtdeInclusaoRg) {
		this.qtdeInclusaoRg = qtdeInclusaoRg;
	}

	public Integer getQtdeAlteracaoCpfCnpj() {
		return qtdeAlteracaoCpfCnpj;
	}

	public void setQtdeAlteracaoCpfCnpj(Integer qtdeAlteracaoCpfCnpj) {
		this.qtdeAlteracaoCpfCnpj = qtdeAlteracaoCpfCnpj;
	}

	public Integer getQtdeInclusaoCpfCnpj() {
		return qtdeInclusaoCpfCnpj;
	}

	public void setQtdeInclusaoCpfCnpj(Integer qtdeInclusaoCpfCnpj) {
		this.qtdeInclusaoCpfCnpj = qtdeInclusaoCpfCnpj;
	}

	public Integer getQtdeExclusaoCpfCnpj() {
		return qtdeExclusaoCpfCnpj;
	}

	public void setQtdeExclusaoCpfCnpj(Integer qtdeExclusaoCpfCnpj) {
		this.qtdeExclusaoCpfCnpj = qtdeExclusaoCpfCnpj;
	}

	public Integer getQtdeImovelResAntes() {
		return qtdeImovelResAntes;
	}

	public void setQtdeImovelResAntes(Integer qtdeImovelResAntes) {
		this.qtdeImovelResAntes = qtdeImovelResAntes;
	}

	public Integer getQtdeImovelResDepois() {
		return qtdeImovelResDepois;
	}

	public void setQtdeImovelResDepois(Integer qtdeImovelResDepois) {
		this.qtdeImovelResDepois = qtdeImovelResDepois;
	}

	public Integer getQtdeImovelComAntes() {
		return qtdeImovelComAntes;
	}

	public void setQtdeImovelComAntes(Integer qtdeImovelComAntes) {
		this.qtdeImovelComAntes = qtdeImovelComAntes;
	}

	public Integer getQtdeImovelComDepois() {
		return qtdeImovelComDepois;
	}

	public void setQtdeImovelComDepois(Integer qtdeImovelComDepois) {
		this.qtdeImovelComDepois = qtdeImovelComDepois;
	}

	public Integer getQtdeImovelIndAntes() {
		return qtdeImovelIndAntes;
	}

	public void setQtdeImovelIndAntes(Integer qtdeImovelIndAntes) {
		this.qtdeImovelIndAntes = qtdeImovelIndAntes;
	}

	public Integer getQtdeImovelIndDepois() {
		return qtdeImovelIndDepois;
	}

	public void setQtdeImovelIndDepois(Integer qtdeImovelIndDepois) {
		this.qtdeImovelIndDepois = qtdeImovelIndDepois;
	}

	public Integer getQtdeImovelPubAntes() {
		return qtdeImovelPubAntes;
	}

	public void setQtdeImovelPubAntes(Integer qtdeImovelPubAntes) {
		this.qtdeImovelPubAntes = qtdeImovelPubAntes;
	}

	public Integer getQtdeImovelPubDepois() {
		return qtdeImovelPubDepois;
	}

	public void setQtdeImovelPubDepois(Integer qtdeImovelPubDepois) {
		this.qtdeImovelPubDepois = qtdeImovelPubDepois;
	}

	public Integer getQtdeEconomiaResAntes() {
		return qtdeEconomiaResAntes;
	}

	public void setQtdeEconomiaResAntes(Integer qtdeEconomiaResAntes) {
		this.qtdeEconomiaResAntes = qtdeEconomiaResAntes;
	}

	public Integer getQtdeEconomiaResDepois() {
		return qtdeEconomiaResDepois;
	}

	public void setQtdeEconomiaResDepois(Integer qtdeEconomiaResDepois) {
		this.qtdeEconomiaResDepois = qtdeEconomiaResDepois;
	}

	public Integer getQtdeEconomiaComAntes() {
		return qtdeEconomiaComAntes;
	}

	public void setQtdeEconomiaComAntes(Integer qtdeEconomiaComAntes) {
		this.qtdeEconomiaComAntes = qtdeEconomiaComAntes;
	}

	public Integer getQtdeEconomiaComDepois() {
		return qtdeEconomiaComDepois;
	}

	public void setQtdeEconomiaComDepois(Integer qtdeEconomiaComDepois) {
		this.qtdeEconomiaComDepois = qtdeEconomiaComDepois;
	}

	public Integer getQtdeEconomiaIndAntes() {
		return qtdeEconomiaIndAntes;
	}

	public void setQtdeEconomiaIndAntes(Integer qtdeEconomiaIndAntes) {
		this.qtdeEconomiaIndAntes = qtdeEconomiaIndAntes;
	}

	public Integer getQtdeEconomiaIndDepois() {
		return qtdeEconomiaIndDepois;
	}

	public void setQtdeEconomiaIndDepois(Integer qtdeEconomiaIndDepois) {
		this.qtdeEconomiaIndDepois = qtdeEconomiaIndDepois;
	}

	public Integer getQtdeEconomiaPubAntes() {
		return qtdeEconomiaPubAntes;
	}

	public void setQtdeEconomiaPubAntes(Integer qtdeEconomiaPubAntes) {
		this.qtdeEconomiaPubAntes = qtdeEconomiaPubAntes;
	}

	public Integer getQtdeEconomiaPubDepois() {
		return qtdeEconomiaPubDepois;
	}

	public void setQtdeEconomiaPubDepois(Integer qtdeEconomiaPubDepois) {
		this.qtdeEconomiaPubDepois = qtdeEconomiaPubDepois;
	}

	public Integer getQtdeImovelPotAguaAntes() {
		return qtdeImovelPotAguaAntes;
	}

	public void setQtdeImovelPotAguaAntes(Integer qtdeImovelPotAguaAntes) {
		this.qtdeImovelPotAguaAntes = qtdeImovelPotAguaAntes;
	}

	public Integer getQtdeImovelPotAguaDepois() {
		return qtdeImovelPotAguaDepois;
	}

	public void setQtdeImovelPotAguaDepois(Integer qtdeImovelPotAguaDepois) {
		this.qtdeImovelPotAguaDepois = qtdeImovelPotAguaDepois;
	}

	public Integer getQtdeImovelFacAguaAntes() {
		return qtdeImovelFacAguaAntes;
	}

	public void setQtdeImovelFacAguaAntes(Integer qtdeImovelFacAguaAntes) {
		this.qtdeImovelFacAguaAntes = qtdeImovelFacAguaAntes;
	}

	public Integer getQtdeImovelFacAguaDepois() {
		return qtdeImovelFacAguaDepois;
	}

	public void setQtdeImovelFacAguaDepois(Integer qtdeImovelFacAguaDepois) {
		this.qtdeImovelFacAguaDepois = qtdeImovelFacAguaDepois;
	}

	public Integer getQtdeImovelLigAguaAntes() {
		return qtdeImovelLigAguaAntes;
	}

	public void setQtdeImovelLigAguaAntes(Integer qtdeImovelLigAguaAntes) {
		this.qtdeImovelLigAguaAntes = qtdeImovelLigAguaAntes;
	}

	public Integer getQtdeImovelLigAguaDepois() {
		return qtdeImovelLigAguaDepois;
	}

	public void setQtdeImovelLigAguaDepois(Integer qtdeImovelLigAguaDepois) {
		this.qtdeImovelLigAguaDepois = qtdeImovelLigAguaDepois;
	}

	public Integer getQtdeImovelCorAguaAntes() {
		return qtdeImovelCorAguaAntes;
	}

	public void setQtdeImovelCorAguaAntes(Integer qtdeImovelCorAguaAntes) {
		this.qtdeImovelCorAguaAntes = qtdeImovelCorAguaAntes;
	}

	public Integer getQtdeImovelCorAguaDepois() {
		return qtdeImovelCorAguaDepois;
	}

	public void setQtdeImovelCorAguaDepois(Integer qtdeImovelCorAguaDepois) {
		this.qtdeImovelCorAguaDepois = qtdeImovelCorAguaDepois;
	}

	public Integer getQtdeImovelSupAguaAntes() {
		return qtdeImovelSupAguaAntes;
	}

	public void setQtdeImovelSupAguaAntes(Integer qtdeImovelSupAguaAntes) {
		this.qtdeImovelSupAguaAntes = qtdeImovelSupAguaAntes;
	}

	public Integer getQtdeImovelSupAguaDepois() {
		return qtdeImovelSupAguaDepois;
	}

	public void setQtdeImovelSupAguaDepois(Integer qtdeImovelSupAguaDepois) {
		this.qtdeImovelSupAguaDepois = qtdeImovelSupAguaDepois;
	}

	public Integer getQtdeImovelPotEsgotoAntes() {
		return qtdeImovelPotEsgotoAntes;
	}

	public void setQtdeImovelPotEsgotoAntes(Integer qtdeImovelPotEsgotoAntes) {
		this.qtdeImovelPotEsgotoAntes = qtdeImovelPotEsgotoAntes;
	}

	public Integer getQtdeImovelPotEsgotoDepois() {
		return qtdeImovelPotEsgotoDepois;
	}

	public void setQtdeImovelPotEsgotoDepois(Integer qtdeImovelPotEsgotoDepois) {
		this.qtdeImovelPotEsgotoDepois = qtdeImovelPotEsgotoDepois;
	}

	public Integer getQtdeImovelFacEsgotoAntes() {
		return qtdeImovelFacEsgotoAntes;
	}

	public void setQtdeImovelFacEsgotoAntes(Integer qtdeImovelFacEsgotoAntes) {
		this.qtdeImovelFacEsgotoAntes = qtdeImovelFacEsgotoAntes;
	}

	public Integer getQtdeImovelFacEsgotoDepois() {
		return qtdeImovelFacEsgotoDepois;
	}

	public void setQtdeImovelFacEsgotoDepois(Integer qtdeImovelFacEsgotoDepois) {
		this.qtdeImovelFacEsgotoDepois = qtdeImovelFacEsgotoDepois;
	}

	public Integer getQtdeImovelLigEsgotoAntes() {
		return qtdeImovelLigEsgotoAntes;
	}

	public void setQtdeImovelLigEsgotoAntes(Integer qtdeImovelLigEsgotoAntes) {
		this.qtdeImovelLigEsgotoAntes = qtdeImovelLigEsgotoAntes;
	}

	public Integer getQtdeImovelLigEsgotoDepois() {
		return qtdeImovelLigEsgotoDepois;
	}

	public void setQtdeImovelLigEsgotoDepois(Integer qtdeImovelLigEsgotoDepois) {
		this.qtdeImovelLigEsgotoDepois = qtdeImovelLigEsgotoDepois;
	}

	public Integer getQtdeImovelSupEsgotoAntes() {
		return qtdeImovelSupEsgotoAntes;
	}

	public void setQtdeImovelSupEsgotoAntes(Integer qtdeImovelSupEsgotoAntes) {
		this.qtdeImovelSupEsgotoAntes = qtdeImovelSupEsgotoAntes;
	}

	public Integer getQtdeImovelSupEsgotoDepois() {
		return qtdeImovelSupEsgotoDepois;
	}

	public void setQtdeImovelSupEsgotoDepois(Integer qtdeImovelSupEsgotoDepois) {
		this.qtdeImovelSupEsgotoDepois = qtdeImovelSupEsgotoDepois;
	}

	public BigDecimal getValorAguaAntes() {
		return valorAguaAntes;
	}

	public void setValorAguaAntes(BigDecimal valorAguaAntes) {
		this.valorAguaAntes = valorAguaAntes;
	}

	public BigDecimal getValorAguaDepois() {
		return valorAguaDepois;
	}

	public void setValorAguaDepois(BigDecimal valorAguaDepois) {
		this.valorAguaDepois = valorAguaDepois;
	}

	public BigDecimal getValorEsgotoAntes() {
		return valorEsgotoAntes;
	}

	public void setValorEsgotoAntes(BigDecimal valorEsgotoAntes) {
		this.valorEsgotoAntes = valorEsgotoAntes;
	}

	public BigDecimal getValorEsgotoDepois() {
		return valorEsgotoDepois;
	}

	public void setValorEsgotoDepois(BigDecimal valorEsgotoDepois) {
		this.valorEsgotoDepois = valorEsgotoDepois;
	}

	public BigDecimal getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	public BigDecimal getValorConsumoFraudado() {
		return valorConsumoFraudado;
	}

	public void setValorConsumoFraudado(BigDecimal valorConsumoFraudado) {
		this.valorConsumoFraudado = valorConsumoFraudado;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getQtdeImovelRetornarCampo() {
		return qtdeImovelRetornarCampo;
	}

	public void setQtdeImovelRetornarCampo(Integer qtdeImovelRetornarCampo) {
		this.qtdeImovelRetornarCampo = qtdeImovelRetornarCampo;
	}

	
}

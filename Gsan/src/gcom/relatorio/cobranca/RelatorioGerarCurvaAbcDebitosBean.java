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
package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * 
 * Bean do [UC0227] Gerar Curva ABC Debitos 
 *
 * @author Ivan S�rgio
 * @date 03/09/2007
 */
public class RelatorioGerarCurvaAbcDebitosBean implements RelatorioBean {
	
	private String referenciaCobrancaInicial;
	private String referenciaCobrancaFinal;
	private String categoria;
	private String subCategoria;
	private String classificacao;
	private String situacaoAgua;
	private String intervaloMeses;
	private String idGerenciaRegional;
	private String nomeGerenciaRegional;
	private String idLocalidade;
	private String nomeLocalidade;
	private String idMunicipio;
	private String nomeMunicipio;
	private String idSetorComercial;
	private String codigoSetorComercial;
	private String nomeSetorComercial;
	
	// Parte de Debito Faixa Valoes
	private BigDecimal faixaInicial;
	private BigDecimal faixaFinal;
	private Integer idFaixa;
	
	private Integer quantidadeLigacoes = 0;
	private BigDecimal percLigacoes = new BigDecimal(0.00).setScale(2);
	private Integer ligacoesAcumuladas = 0;
	private BigDecimal percLigacoesAcumulado = new BigDecimal(0.00).setScale(2);
	
	private BigDecimal valor = new BigDecimal(0.00).setScale(2);
	private BigDecimal percValor = new BigDecimal(0.00).setScale(2);
	private BigDecimal valorAcumulado = new BigDecimal(0.00).setScale(2);
	private BigDecimal percValorAcumulado = new BigDecimal(0.00).setScale(2);
	private BigDecimal valorMedio = new BigDecimal(0.00).setScale(2);
	
	private String idClassificacao;
	
	public String getReferenciaCobrancaFinal() {
		return referenciaCobrancaFinal;
	}
	public void setReferenciaCobrancaFinal(String referenciaCobrancaFinal) {
		this.referenciaCobrancaFinal = referenciaCobrancaFinal;
	}
	public String getReferenciaCobrancaInicial() {
		return referenciaCobrancaInicial;
	}
	public void setReferenciaCobrancaInicial(String referenciaCobrancaInicial) {
		this.referenciaCobrancaInicial = referenciaCobrancaInicial;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public String getIntervaloMeses() {
		return intervaloMeses;
	}
	public void setIntervaloMeses(String intervaloMeses) {
		this.intervaloMeses = intervaloMeses;
	}
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}
	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}
	public String getSituacaoAgua() {
		return situacaoAgua;
	}
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}
	public String getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}
	
	// Formata a Situacao
	public String getSituacao() {
		String retorno = "";
		
		if (this.getSituacaoAgua() != "") {
			retorno = this.getSituacaoAgua() + " ";
		}
		
		if (this.getIntervaloMeses() != "") {
			retorno += this.getIntervaloMeses();
		}
		
		return retorno;
	}
	public BigDecimal getFaixaFinal() {
		return faixaFinal;
	}
	public void setFaixaFinal(BigDecimal faixaFinal) {
		this.faixaFinal = faixaFinal;
	}
	public BigDecimal getFaixaInicial() {
		return faixaInicial;
	}
	public void setFaixaInicial(BigDecimal faixaInicial) {
		this.faixaInicial = faixaInicial;
	}
	public Integer getIdFaixa() {
		return idFaixa;
	}
	public void setIdFaixa(Integer idFaixa) {
		this.idFaixa = idFaixa;
	}
	public Integer getLigacoesAcumuladas() {
		return ligacoesAcumuladas;
	}
	public void setLigacoesAcumuladas(Integer ligacoesAcumuladas) {
		this.ligacoesAcumuladas = ligacoesAcumuladas;
	}
	public BigDecimal getPercLigacoes() {
		return percLigacoes;
	}
	public void setPercLigacoes(BigDecimal percLigacoes) {
		this.percLigacoes = percLigacoes;
	}
	public BigDecimal getPercLigacoesAcumulado() {
		return percLigacoesAcumulado;
	}
	public void setPercLigacoesAcumulado(BigDecimal percLigacoesAcumulado) {
		this.percLigacoesAcumulado = percLigacoesAcumulado;
	}
	public BigDecimal getPercValor() {
		return percValor;
	}
	public void setPercValor(BigDecimal percValor) {
		this.percValor = percValor;
	}
	public BigDecimal getPercValorAcumulado() {
		return percValorAcumulado;
	}
	public void setPercValorAcumulado(BigDecimal percValorAcumulado) {
		this.percValorAcumulado = percValorAcumulado;
	}
	public Integer getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}
	public void setQuantidadeLigacoes(Integer quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getValorAcumulado() {
		return valorAcumulado;
	}
	public void setValorAcumulado(BigDecimal valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
	}
	public BigDecimal getValorMedio() {
		return valorMedio;
	}
	public void setValorMedio(BigDecimal valorMedio) {
		this.valorMedio = valorMedio;
	}
	public String getIdClassificacao() {
		return idClassificacao;
	}
	public void setIdClassificacao(String idClassificacao) {
		this.idClassificacao = idClassificacao;
	}
	
	// Formata a descricao da classificacao
	public String getDescricaoClassificacao() {
		String retorno = "";
		
		if (this.classificacao.trim().equalsIgnoreCase("REGIONAL")) {
			retorno = this.idGerenciaRegional + " - " + this.nomeGerenciaRegional;
		}else if (this.classificacao.trim().equalsIgnoreCase("LOCAL")){
			retorno = this.idGerenciaRegional + " - " + this.nomeGerenciaRegional + " - " +
					  this.nomeLocalidade;
		}else if (this.classificacao.trim().equals("MUNICIPIO")){
			retorno = this.idGerenciaRegional + " - " + this.nomeGerenciaRegional + " - " +
					  this.nomeMunicipio;
		}else if (this.classificacao.trim().equals("ESTADO")){
			retorno = "ESTADO";
		}else {
			retorno = this.idGerenciaRegional + " - " + this.nomeGerenciaRegional + " - " +
			  		  this.nomeLocalidade + " - " + this.codigoSetorComercial + " - " +
			  		  this.nomeSetorComercial;
		}
		
		return retorno;
	}
	
	// Formata a Referencia cobranca
	public String getReferenciaCobranca() {
		String retorno = "";
		
		if (this.getReferenciaCobrancaInicial() != null &&
				!this.getReferenciaCobrancaInicial().equals("") &&
				this.getReferenciaCobrancaFinal() != null &&
				!this.getReferenciaCobrancaInicial().equals("")) {
			
			retorno = this.getReferenciaCobrancaInicial() + " � " + this.getReferenciaCobrancaFinal();
		}

		return retorno;
	}
}
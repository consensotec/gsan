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

import java.io.Serializable;

/**
 * [UC1680] Retornar Dados Imóvel Telemetria Via WebService
 * 
 * @author André Miranda
 * @date 29/04/2015
 */
public class TelemetriaRetornarDadosImovelHelper implements Serializable {
	private static final long serialVersionUID = 1L;
		
	private String matriculaImovel;
	private String tipoLigacao;
	private String inscricaoImovel;
	private String categoriaImovel;
	private String subcategoriaImovel;
	private String grupoFaturamento;
	private String nomeGerenciaLocalidade;
	private String nomeLocalidade;
	private String numeroHidrometro;
	private String dataInstalacaoHidrometro;
	private String dataVencimentoHidrometro;
	private String mediaConsumoMensal;
	private String anoMesUltimaReferencia;
	private String leituraFaturadaUltimaReferencia;
	private String consumoFaturadoUltimaReferencia;
	private String valorUltimaConta;
	private String dataVencimentoUltimaConta;
	private String dataPagamentoUltimaConta;
	private String valorTotalDebitos;
	private String numeroContasEmDebito;
	private String erro;

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getTipoLigacao() {
		return tipoLigacao;
	}

	public void setTipoLigacao(String tipoLigacao) {
		this.tipoLigacao = tipoLigacao;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getCategoriaImovel() {
		return categoriaImovel;
	}

	public void setCategoriaImovel(String categoriaImovel) {
		this.categoriaImovel = categoriaImovel;
	}

	public String getSubcategoriaImovel() {
		return subcategoriaImovel;
	}

	public void setSubcategoriaImovel(String subcategoriaImovel) {
		this.subcategoriaImovel = subcategoriaImovel;
	}

	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public String getNomeGerenciaLocalidade() {
		return nomeGerenciaLocalidade;
	}

	public void setNomeGerenciaLocalidade(String nomeGerenciaLocalidade) {
		this.nomeGerenciaLocalidade = nomeGerenciaLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public String getDataInstalacaoHidrometro() {
		return dataInstalacaoHidrometro;
	}

	public void setDataInstalacaoHidrometro(String dataInstalacaoHidrometro) {
		this.dataInstalacaoHidrometro = dataInstalacaoHidrometro;
	}

	public String getDataVencimentoHidrometro() {
		return dataVencimentoHidrometro;
	}

	public void setDataVencimentoHidrometro(String dataVencimentoHidrometro) {
		this.dataVencimentoHidrometro = dataVencimentoHidrometro;
	}

	public String getMediaConsumoMensal() {
		return mediaConsumoMensal;
	}

	public void setMediaConsumoMensal(String mediaConsumoMensal) {
		this.mediaConsumoMensal = mediaConsumoMensal;
	}

	public String getAnoMesUltimaReferencia() {
		return anoMesUltimaReferencia;
	}

	public void setAnoMesUltimaReferencia(String anoMesUltimaReferencia) {
		this.anoMesUltimaReferencia = anoMesUltimaReferencia;
	}

	public String getLeituraFaturadaUltimaReferencia() {
		return leituraFaturadaUltimaReferencia;
	}

	public void setLeituraFaturadaUltimaReferencia(String leituraFaturadaUltimaReferencia) {
		this.leituraFaturadaUltimaReferencia = leituraFaturadaUltimaReferencia;
	}

	public String getConsumoFaturadoUltimaReferencia() {
		return consumoFaturadoUltimaReferencia;
	}

	public void setConsumoFaturadoUltimaReferencia(String consumoFaturadoUltimaReferencia) {
		this.consumoFaturadoUltimaReferencia = consumoFaturadoUltimaReferencia;
	}

	public String getValorUltimaConta() {
		return valorUltimaConta;
	}

	public void setValorUltimaConta(String valorUltimaConta) {
		this.valorUltimaConta = valorUltimaConta;
	}

	public String getDataVencimentoUltimaConta() {
		return dataVencimentoUltimaConta;
	}

	public void setDataVencimentoUltimaConta(String dataVencimentoUltimaConta) {
		this.dataVencimentoUltimaConta = dataVencimentoUltimaConta;
	}

	public String getDataPagamentoUltimaConta() {
		return dataPagamentoUltimaConta;
	}

	public void setDataPagamentoUltimaConta(String dataPagamentoUltimaConta) {
		this.dataPagamentoUltimaConta = dataPagamentoUltimaConta;
	}

	public String getValorTotalDebitos() {
		return valorTotalDebitos;
	}

	public void setValorTotalDebitos(String valorTotalDebitos) {
		this.valorTotalDebitos = valorTotalDebitos;
	}

	public String getNumeroContasEmDebito() {
		return numeroContasEmDebito;
	}

	public void setNumeroContasEmDebito(String numeroContasEmDebito) {
		this.numeroContasEmDebito = numeroContasEmDebito;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}
}

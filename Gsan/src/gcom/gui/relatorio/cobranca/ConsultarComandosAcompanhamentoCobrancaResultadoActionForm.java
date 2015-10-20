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
package gcom.gui.relatorio.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * [UC1167] Consultar Comandos de Cobran�a por Empresa
 * 
 * @author Mariana Victor
 * @since 03/05/2011
 */
public class ConsultarComandosAcompanhamentoCobrancaResultadoActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String idEmpresa;

	private String nomeEmpresa;

	private String periodoCicloInicial;

	private String periodoCicloFinal;

	private String dataExecucaoComando;

	private String dataEncerramento;

	private String idImovel;
	
	private String inscricaoImovel;

	private String idCliente;

	private String nomeCliente;
	
	private String categoria;
	
	private String idImovelPerfil;
	
	private String dsImovelPerfil;
	
	private String idGerenciaRegional;
	
	private String nomeGerenciaRegional;

	private String idUnidadeNegocio;

	private String nomeUnidadeNegocio;

	private String intervaloLocalizacaoInicial;

	private String intervaloLocalizacaoFinal;

	private String intervaloSetorComercialInicial;
	
	private String intervaloSetorComercialFinal;
	
	private String intervaloQuadraInicial;
	
	private String intervaloQuadraFinal;

	private String periodoReferenciaContasInicial;

	private String periodoReferenciaContasFinal;

	private String periodoVencimentoContasInicial;

	private String periodoVencimentoContasFinal;

	private String intervaloValorContasInicial;

	private String intervaloValorContasFinal;
	
	private Integer[] idRegistros;

	private String qtdeTotalContasCobranca;

	private String valorTotalContasCobranca;

	private String arquivoTxtGerado;
	
	private String idRegistro;

	public ConsultarComandosAcompanhamentoCobrancaResultadoActionForm() {
		super();
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getDataEncerramento() {
		return dataEncerramento;
	}


	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}


	public String getDataExecucaoComando() {
		return dataExecucaoComando;
	}


	public void setDataExecucaoComando(String dataExecucaoComando) {
		this.dataExecucaoComando = dataExecucaoComando;
	}


	public String getDsImovelPerfil() {
		return dsImovelPerfil;
	}


	public void setDsImovelPerfil(String dsImovelPerfil) {
		this.dsImovelPerfil = dsImovelPerfil;
	}


	public String getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}


	public String getIdEmpresa() {
		return idEmpresa;
	}


	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}


	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}


	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}


	public String getIdImovel() {
		return idImovel;
	}


	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}


	public String getIdImovelPerfil() {
		return idImovelPerfil;
	}


	public void setIdImovelPerfil(String idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}


	public Integer[] getIdRegistros() {
		return idRegistros;
	}


	public void setIdRegistros(Integer[] idRegistros) {
		this.idRegistros = idRegistros;
	}


	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}


	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}


	public String getInscricaoImovel() {
		return inscricaoImovel;
	}


	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}


	public String getIntervaloLocalizacaoFinal() {
		return intervaloLocalizacaoFinal;
	}


	public void setIntervaloLocalizacaoFinal(String intervaloLocalizacaoFinal) {
		this.intervaloLocalizacaoFinal = intervaloLocalizacaoFinal;
	}


	public String getIntervaloLocalizacaoInicial() {
		return intervaloLocalizacaoInicial;
	}


	public void setIntervaloLocalizacaoInicial(String intervaloLocalizacaoInicial) {
		this.intervaloLocalizacaoInicial = intervaloLocalizacaoInicial;
	}


	public String getIntervaloQuadraFinal() {
		return intervaloQuadraFinal;
	}


	public void setIntervaloQuadraFinal(String intervaloQuadraFinal) {
		this.intervaloQuadraFinal = intervaloQuadraFinal;
	}


	public String getIntervaloQuadraInicial() {
		return intervaloQuadraInicial;
	}


	public void setIntervaloQuadraInicial(String intervaloQuadraInicial) {
		this.intervaloQuadraInicial = intervaloQuadraInicial;
	}


	public String getIntervaloSetorComercialFinal() {
		return intervaloSetorComercialFinal;
	}


	public void setIntervaloSetorComercialFinal(String intervaloSetorComercialFinal) {
		this.intervaloSetorComercialFinal = intervaloSetorComercialFinal;
	}


	public String getIntervaloSetorComercialInicial() {
		return intervaloSetorComercialInicial;
	}


	public void setIntervaloSetorComercialInicial(
			String intervaloSetorComercialInicial) {
		this.intervaloSetorComercialInicial = intervaloSetorComercialInicial;
	}


	public String getIntervaloValorContasFinal() {
		return intervaloValorContasFinal;
	}


	public void setIntervaloValorContasFinal(String intervaloValorContasFinal) {
		this.intervaloValorContasFinal = intervaloValorContasFinal;
	}


	public String getIntervaloValorContasInicial() {
		return intervaloValorContasInicial;
	}


	public void setIntervaloValorContasInicial(String intervaloValorContasInicial) {
		this.intervaloValorContasInicial = intervaloValorContasInicial;
	}


	public String getNomeCliente() {
		return nomeCliente;
	}


	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


	public String getNomeEmpresa() {
		return nomeEmpresa;
	}


	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}


	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}


	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}


	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}


	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}


	public String getPeriodoCicloFinal() {
		return periodoCicloFinal;
	}


	public void setPeriodoCicloFinal(String periodoCicloFinal) {
		this.periodoCicloFinal = periodoCicloFinal;
	}


	public String getPeriodoCicloInicial() {
		return periodoCicloInicial;
	}


	public void setPeriodoCicloInicial(String periodoCicloInicial) {
		this.periodoCicloInicial = periodoCicloInicial;
	}


	public String getPeriodoReferenciaContasFinal() {
		return periodoReferenciaContasFinal;
	}


	public void setPeriodoReferenciaContasFinal(String periodoReferenciaContasFinal) {
		this.periodoReferenciaContasFinal = periodoReferenciaContasFinal;
	}


	public String getPeriodoReferenciaContasInicial() {
		return periodoReferenciaContasInicial;
	}


	public void setPeriodoReferenciaContasInicial(
			String periodoReferenciaContasInicial) {
		this.periodoReferenciaContasInicial = periodoReferenciaContasInicial;
	}


	public String getPeriodoVencimentoContasFinal() {
		return periodoVencimentoContasFinal;
	}


	public void setPeriodoVencimentoContasFinal(String periodoVencimentoContasFinal) {
		this.periodoVencimentoContasFinal = periodoVencimentoContasFinal;
	}


	public String getPeriodoVencimentoContasInicial() {
		return periodoVencimentoContasInicial;
	}


	public void setPeriodoVencimentoContasInicial(
			String periodoVencimentoContasInicial) {
		this.periodoVencimentoContasInicial = periodoVencimentoContasInicial;
	}


	public String getQtdeTotalContasCobranca() {
		return qtdeTotalContasCobranca;
	}


	public void setQtdeTotalContasCobranca(String qtdeTotalContasCobranca) {
		this.qtdeTotalContasCobranca = qtdeTotalContasCobranca;
	}


	public String getValorTotalContasCobranca() {
		return valorTotalContasCobranca;
	}


	public void setValorTotalContasCobranca(String valorTotalContasCobranca) {
		this.valorTotalContasCobranca = valorTotalContasCobranca;
	}

	public String getArquivoTxtGerado() {
		return arquivoTxtGerado;
	}

	public void setArquivoTxtGerado(String arquivoTxtGerado) {
		this.arquivoTxtGerado = arquivoTxtGerado;
	}

	public String getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}

}

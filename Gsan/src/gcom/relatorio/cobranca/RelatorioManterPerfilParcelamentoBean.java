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

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corr�a
 * @version 1.0
 */

public class RelatorioManterPerfilParcelamentoBean implements RelatorioBean {
	private String idPerfil;

	private String rd;

	private String tipoSituacaoImovel;

	private String perfilImovel;

	private String subcategoria;

	private String percentualDescontoImpontualidade;

	private String percentualTarifaMinima;

	private String idReparcelamento;

	private String qtdeReparcelamentosConsecutivos;
	
	private String idPrestacao;

	private String qtdePrestacoesParcelamento;

	private String juros;

	private String percentualEntrada;

	private String idDescontoAntiguidade;

	private String qtdeMinimaMesesDebito;

	private String descontoSemRestabelecimentoAntiguidade;

	private String descontoComRestabelecimentoAntiguidade;

	private String descontoAtivo;

	private String idDescontoInatividade;

	private String qtdeMaximaMesesInatividade;

	private String descontoSemRestabelecimentoInatividade;

	private String descontoComRestabelecimentoInatividade;


	/**
	 * Construtor da classe RelatorioManterCronogramaFaturamentoBean
	 * 
	 * @param grupo
	 *            Descri��o do par�metro
	 * @param mesAno
	 *            Descri��o do par�metro
	 * @param atividade
	 *            Descri��o do par�metro
	 * @param predecessora
	 *            Descri��o do par�metro
	 * @param obrigatoria
	 *            Descri��o do par�metro
	 * @param dataPrevista
	 *            Descri��o do par�metro
	 * @param dataRealizacao
	 *            Descri��o do par�metro
	 */

	public RelatorioManterPerfilParcelamentoBean(String idPerfil, String rd,
			String tipoSituacaoImovel, String perfilImovel,
			String subcategoria, String percentualDescontoImpontualidade,
			String percentualTarifaMinima, String idReparcelamento,
			String qtdeReparcelamentosConsecutivos, String idPrestacao,
			String qtdePrestacoesParcelamento, String juros,
			String percentualEntrada, String idDescontoAntiguidade,
			String qtdeMinimaMesesDebito,
			String descontoSemRestabelecimentoAntiguidade,
			String descontoComRestabelecimentoAntiguidade,
			String descontoAtivo, String idDescontoInatividade,
			String qtdeMaximaMesesInatividade,
			String descontoSemRestabelecimentoInatividade,
			String descontoComRestabelecimentoInatividade) {
		this.idPerfil = idPerfil;
		this.rd = rd;
		this.tipoSituacaoImovel = tipoSituacaoImovel;
		this.perfilImovel = perfilImovel;
		this.subcategoria = subcategoria;
		this.percentualDescontoImpontualidade = percentualDescontoImpontualidade;
		this.percentualTarifaMinima = percentualTarifaMinima;
		this.idReparcelamento = idReparcelamento;
		this.qtdeReparcelamentosConsecutivos = qtdeReparcelamentosConsecutivos;
		this.idPrestacao = idPrestacao;
		this.qtdePrestacoesParcelamento = qtdePrestacoesParcelamento;
		this.juros = juros;
		this.percentualEntrada = percentualEntrada;
		this.idDescontoAntiguidade = idDescontoAntiguidade;
		this.qtdeMinimaMesesDebito = qtdeMinimaMesesDebito;
		this.descontoSemRestabelecimentoAntiguidade = descontoSemRestabelecimentoAntiguidade;
		this.descontoComRestabelecimentoAntiguidade = descontoComRestabelecimentoAntiguidade;
		this.descontoAtivo = descontoAtivo;
		this.idDescontoInatividade = idDescontoInatividade;
		this.qtdeMaximaMesesInatividade = qtdeMaximaMesesInatividade;
		this.descontoSemRestabelecimentoInatividade = descontoSemRestabelecimentoInatividade;
		this.descontoComRestabelecimentoInatividade = descontoComRestabelecimentoInatividade;
	}

	public String getDescontoAtivo() {
		return descontoAtivo;
	}

	public void setDescontoAtivo(String descontoAtivo) {
		this.descontoAtivo = descontoAtivo;
	}

	public String getDescontoComRestabelecimentoAntiguidade() {
		return descontoComRestabelecimentoAntiguidade;
	}

	public void setDescontoComRestabelecimentoAntiguidade(
			String descontoComRestabelecimentoAntiguidade) {
		this.descontoComRestabelecimentoAntiguidade = descontoComRestabelecimentoAntiguidade;
	}

	public String getDescontoComRestabelecimentoInatividade() {
		return descontoComRestabelecimentoInatividade;
	}

	public void setDescontoComRestabelecimentoInatividade(
			String descontoComRestabelecimentoInatividade) {
		this.descontoComRestabelecimentoInatividade = descontoComRestabelecimentoInatividade;
	}

	public String getDescontoSemRestabelecimentoAntiguidade() {
		return descontoSemRestabelecimentoAntiguidade;
	}

	public void setDescontoSemRestabelecimentoAntiguidade(
			String descontoSemRestabelecimentoAntiguidade) {
		this.descontoSemRestabelecimentoAntiguidade = descontoSemRestabelecimentoAntiguidade;
	}

	public String getDescontoSemRestabelecimentoInatividade() {
		return descontoSemRestabelecimentoInatividade;
	}

	public void setDescontoSemRestabelecimentoInatividade(
			String descontoSemRestabelecimentoInatividade) {
		this.descontoSemRestabelecimentoInatividade = descontoSemRestabelecimentoInatividade;
	}

	public String getJuros() {
		return juros;
	}

	public void setJuros(String juros) {
		this.juros = juros;
	}

	public String getPercentualDescontoImpontualidade() {
		return percentualDescontoImpontualidade;
	}

	public void setPercentualDescontoImpontualidade(
			String percentualDescontoImpontualidade) {
		this.percentualDescontoImpontualidade = percentualDescontoImpontualidade;
	}

	public String getPercentualEntrada() {
		return percentualEntrada;
	}

	public void setPercentualEntrada(String percentualEntrada) {
		this.percentualEntrada = percentualEntrada;
	}

	public String getPercentualTarifaMinima() {
		return percentualTarifaMinima;
	}

	public void setPercentualTarifaMinima(String percentualTarifaMinima) {
		this.percentualTarifaMinima = percentualTarifaMinima;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getQtdeMaximaMesesInatividade() {
		return qtdeMaximaMesesInatividade;
	}

	public void setQtdeMaximaMesesInatividade(String qtdeMaximaMesesInatividade) {
		this.qtdeMaximaMesesInatividade = qtdeMaximaMesesInatividade;
	}

	public String getQtdeMinimaMesesDebito() {
		return qtdeMinimaMesesDebito;
	}

	public void setQtdeMinimaMesesDebito(String qtdeMinimaMesesDebito) {
		this.qtdeMinimaMesesDebito = qtdeMinimaMesesDebito;
	}

	public String getQtdePrestacoesParcelamento() {
		return qtdePrestacoesParcelamento;
	}

	public void setQtdePrestacoesParcelamento(String qtdePrestacoesParcelamento) {
		this.qtdePrestacoesParcelamento = qtdePrestacoesParcelamento;
	}

	public String getQtdeReparcelamentosConsecutivos() {
		return qtdeReparcelamentosConsecutivos;
	}

	public void setQtdeReparcelamentosConsecutivos(
			String qtdeReparcelamentosConsecutivos) {
		this.qtdeReparcelamentosConsecutivos = qtdeReparcelamentosConsecutivos;
	}

	public String getRd() {
		return rd;
	}

	public void setRd(String rd) {
		this.rd = rd;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	public String getTipoSituacaoImovel() {
		return tipoSituacaoImovel;
	}

	public void setTipoSituacaoImovel(String tipoSituacaoImovel) {
		this.tipoSituacaoImovel = tipoSituacaoImovel;
	}

	public String getIdDescontoAntiguidade() {
		return idDescontoAntiguidade;
	}

	public void setIdDescontoAntiguidade(String idDescontoAntiguidade) {
		this.idDescontoAntiguidade = idDescontoAntiguidade;
	}

	public String getIdDescontoInatividade() {
		return idDescontoInatividade;
	}

	public void setIdDescontoInatividade(String idDescontoInatividade) {
		this.idDescontoInatividade = idDescontoInatividade;
	}

	public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getIdReparcelamento() {
		return idReparcelamento;
	}

	public void setIdReparcelamento(String idReparcelamento) {
		this.idReparcelamento = idReparcelamento;
	}

	public String getIdPrestacao() {
		return idPrestacao;
	}

	public void setIdPrestacao(String idPrestacao) {
		this.idPrestacao = idPrestacao;
	}

}

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
package gsan.gui.cadastro.sistemaparametro;

import gsan.arrecadacao.banco.ContaBancaria;
import gsan.arrecadacao.banco.FiltroContaBancaria;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 09/01/2007
 */
public class ExibirInformarParametrosSistemaArrecadacaoFinanceiroAction extends
		GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("exibirInformarParametrosSistemaArrecadacaoFinanceiro");

		InformarSistemaParametrosActionForm form = (InformarSistemaParametrosActionForm) actionForm;

		HttpSession sessao = this.getSessao(httpServletRequest);

		SistemaParametro sistemaParametro = (SistemaParametro) sessao
				.getAttribute("sistemaParametro");

		// Verifica se os dados foram informados da tabela existem e joga numa colecao

		FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
		filtroContaBancaria.setCampoOrderBy(FiltroContaBancaria.ID);

		@SuppressWarnings("unchecked")
		Collection<ContaBancaria> colecaoContaBancaria = this.getFachada()
				.pesquisar(filtroContaBancaria, ContaBancaria.class.getName());

		if (colecaoContaBancaria == null || colecaoContaBancaria.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Conta Bancaria");
		}

		httpServletRequest.setAttribute("colecaoContaBancaria",
				colecaoContaBancaria);

		// Verifica se ja entrou nesse action, caso nao coloca no form os dados
		// do objeto sistemaParametro

		Integer cont;

		if (sessao.getAttribute("ArrecadacaoFinanceiro") == null) {

			cont = 1;
			sessao.setAttribute("ArrecadacaoFinanceiro", cont);

			String anoMesArrecadacao = Util
					.formatarAnoMesParaMesAno(sistemaParametro
							.getAnoMesArrecadacao().toString());

			form.setMesAnoReferenciaArrecadacao("" + anoMesArrecadacao);

			if (sistemaParametro.getCodigoEmpresaFebraban() != null) {
				form.setCodigoEmpresaFebraban(sistemaParametro
						.getCodigoEmpresaFebraban().toString());
			}

			if (sistemaParametro.getNumeroLayoutFebraban() != null) {
				form.setNumeroLayOut(sistemaParametro.getNumeroLayoutFebraban()
						.toString());
			}

			if (sistemaParametro.getContaBancaria() != null) {
				form.setIndentificadorContaDevolucao(sistemaParametro
						.getContaBancaria().getId().toString());
			}

			if (sistemaParametro.getIndicadorValorMovimentoArrecadador() != null) {
				form.setIndicadorValorMovimentoArrecadador(String
						.valueOf(sistemaParametro
								.getIndicadorValorMovimentoArrecadador()));
			}

			if (sistemaParametro.getPercentualFinanciamentoEntradaMinima() != null) {
				String valorAux = Util.formatarMoedaReal(sistemaParametro
						.getPercentualFinanciamentoEntradaMinima());

				form.setPercentualEntradaMinima(valorAux);
			}

			if (sistemaParametro.getNumeroMaximoParcelasFinanciamento() != null) {
				form.setMaximoParcelas(sistemaParametro
						.getNumeroMaximoParcelasFinanciamento().toString());
			}

			if (sistemaParametro.getPercentualMaximoAbatimento() != null) {

				String valorAux = Util.formatarMoedaReal(sistemaParametro
						.getPercentualMaximoAbatimento());

				form.setPercentualMaximoAbatimento(valorAux);
			}

			if (sistemaParametro.getPercentualTaxaJurosFinanciamento() != null) {

				String valorAux = Util.formatarMoedaReal(sistemaParametro
						.getPercentualTaxaJurosFinanciamento());

				form.setPercentualTaxaFinanciamento(valorAux);
			}

			if (sistemaParametro.getNumeroMaximoParcelaCredito() != null) {
				form.setNumeroMaximoParcelaCredito(sistemaParametro
						.getNumeroMaximoParcelaCredito().toString());
			}

			if (sistemaParametro.getPercentualMediaIndice() != null) {

				String valorAux = Util.formatarMoedaReal(sistemaParametro
						.getPercentualMediaIndice());

				form.setPercentualCalculoIndice(valorAux);
			}

			if (sistemaParametro.getNumeroModuloDigitoVerificador() != null
					&& !sistemaParametro.getNumeroModuloDigitoVerificador()
							.equals("")) {

				form.setNumeroModuloDigitoVerificador(sistemaParametro
						.getNumeroModuloDigitoVerificador().toString());

			}
			if (sistemaParametro
					.getNumeroMesesPesquisaImoveisRamaisSuprimidos() != null) {
				form.setNumeroMesesPesquisaImoveisRamaisSuprimidos(sistemaParametro
						.getNumeroMesesPesquisaImoveisRamaisSuprimidos()
						.toString());
			}
			if (sistemaParametro.getNumeroAnoQuitacao() != null) {
				form.setNumeroAnoQuitacao(sistemaParametro
						.getNumeroAnoQuitacao().toString());
			}
			if (sistemaParametro.getIndicadorContaParcelada() != null) {
				form.setIndicadorContaParcelada(sistemaParametro
						.getIndicadorContaParcelada().toString());
			}
			if (sistemaParametro.getIndicadorCobrancaJudical() != null) {
				form.setIndicadorCobrancaJudical(sistemaParametro
						.getIndicadorCobrancaJudical().toString());
			}
			if (sistemaParametro
					.getNumeroMesesAnterioresParaDeclaracaoQuitacao() != null) {
				form.setNumeroMesesAnterioresParaDeclaracaoQuitacao(sistemaParametro
						.getNumeroMesesAnterioresParaDeclaracaoQuitacao()
						.toString());
			}
			if (sistemaParametro.getCdDadosDiarios() != null) {
				form.setCdDadosDiarios(sistemaParametro.getCdDadosDiarios()
						.toString());
			}

			if (sistemaParametro.getNumeroConvenioFichaCompensacao() != null) {
				form.setNumeroConvenioFichaCompensacao(sistemaParametro
						.getNumeroConvenioFichaCompensacao().toString());
			}

			//[UC0060] - Informar Par�metros do Sistema
			if (sistemaParametro.getValorMaximoBaixado() != null) {
				String valorAux = Util.formatarMoedaReal(sistemaParametro.getValorMaximoBaixado());
				form.setValorMaximoBaixado(valorAux);
			}else{
				form.setValorMaximoBaixado(null);
			}

			if (sistemaParametro.getDiferencaMaximoBaixado() != null) {
				String valorAux = Util.formatarMoedaReal(sistemaParametro.getDiferencaMaximoBaixado());
				form.setDiferencaMaximaBaixado(valorAux);
			}else{
				form.setDiferencaMaximaBaixado(null);
			}
			
			/**
			 * RM8594 - [UC0060] Informar Sistema Parametro / [UC0061] Consultar Sistema Parametro
			 * Author: Diogo Luiz
			 * Data: 07/11/2013 
			 */
			if(sistemaParametro.getIndicadorCarteira17() != null){
				String indicadorCarteira17 = sistemaParametro.getIndicadorCarteira17().toString();
				form.setIndicadorCarteira17(indicadorCarteira17);
			}
			
		}
		return retorno;
	}
}

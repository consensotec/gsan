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
package gcom.gui.cadastro.sistemaparametro;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
public class ExibirInformarParametrosSistemaMicromedicaoCobrancaAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("exibirInformarParametrosSistemaMicromedicaoCobranca");

		HttpSession sessao = this.getSessao(httpServletRequest);

		InformarSistemaParametrosActionForm form = (InformarSistemaParametrosActionForm) actionForm;

		SistemaParametro sistemaParametro = (SistemaParametro) sessao
				.getAttribute("sistemaParametro");

		Collection colecaoHidrometroCapacidade = null;

		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		filtroHidrometroCapacidade
				.setCampoOrderBy(FiltroHidrometroCapacidade.ID);

		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
				FiltroHidrometroCapacidade.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		colecaoHidrometroCapacidade = this.getFachada().pesquisar(
				filtroHidrometroCapacidade,
				HidrometroCapacidade.class.getName());

		httpServletRequest.setAttribute("colecaoHidrometroCapacidade",
				colecaoHidrometroCapacidade);

		// Verifica se ja entrou nesse action, caso nao coloca no form os dados
		// do objeto sistemaParametro
		Integer cont;

		if (sessao.getAttribute("MicromedicaoCobranca") == null) {

			cont = 1;
			sessao.setAttribute("MicromedicaoCobranca", cont);

			if (sistemaParametro.getHidrometroCapacidade() != null) {
				form.setCodigoMenorCapacidade(sistemaParametro
						.getHidrometroCapacidade().getId().toString());
			}

			if (sistemaParametro.getIndicadorFaixaFalsa() != null) {
				form.setIndicadorGeracaoFaixaFalsa(sistemaParametro
						.getIndicadorFaixaFalsa().toString());
			}

			if (sistemaParametro.getIndicadorUsoFaixaFalsa() != null) {
				form.setIndicadorPercentualGeracaoFaixaFalsa(sistemaParametro
						.getIndicadorUsoFaixaFalsa().toString());
			}

			if (sistemaParametro.getPercentualFaixaFalsa() != null) {

				String valorAux = Util.formatarMoedaReal(sistemaParametro
						.getPercentualFaixaFalsa());

				form.setPercentualGeracaoFaixaFalsa(valorAux);
			}

			if (sistemaParametro.getIndicadorPercentualFiscalizacaoLeitura() != null) {
				form.setIndicadorPercentualGeracaoFiscalizacaoLeitura(sistemaParametro
						.getIndicadorPercentualFiscalizacaoLeitura().toString());
			}

			if (sistemaParametro.getIndicadorUsoFiscalizadorLeitura() != null) {
				form.setIndicadorGeracaoFiscalizacaoLeitura(sistemaParametro
						.getIndicadorUsoFiscalizadorLeitura().toString());
			}

			if (sistemaParametro.getPercentualFiscalizacaoLeitura() != null) {

				String valorAux = Util.formatarMoedaReal(sistemaParametro
						.getPercentualFiscalizacaoLeitura());

				form.setPercentualGeracaoFiscalizacaoLeitura(valorAux);
			}

			if (sistemaParametro.getIncrementoMaximoConsumoRateio() != null) {

				form.setIncrementoMaximoConsumo(sistemaParametro
						.getIncrementoMaximoConsumoRateio().toString());
			}

			if (sistemaParametro.getDecrementoMaximoConsumoRateio() != null) {
				form.setDecrementoMaximoConsumo(sistemaParametro
						.getDecrementoMaximoConsumoRateio().toString());
			}

			if (sistemaParametro.getPercentualToleranciaRateio() != null) {

				String valorAux = Util.formataBigDecimal(
						sistemaParametro.getPercentualToleranciaRateio(), 1,
						false);

				form.setPercentualToleranciaRateioConsumo(valorAux);
			}

			if (sistemaParametro.getNumeroDiasVencimentoCobranca() != null) {
				form.setDiasVencimentoCobranca(sistemaParametro
						.getNumeroDiasVencimentoCobranca().toString());
			}

			if (sistemaParametro.getNumeroMaximoMesesSancoes() != null) {
				form.setNumeroMaximoMesesSancoes(sistemaParametro
						.getNumeroMaximoMesesSancoes().toString());
			}

			form.setValorSegundaVia(Util.formatarMoedaReal(sistemaParametro
					.getValorSegundaVia()));

			form.setIndicadorCobrarTaxaExtrato(""
					+ sistemaParametro.getIndicadorCobrarTaxaExtrato());

			if (sistemaParametro.getCodigoPeriodicidadeNegativacao() != null) {
				form.setCodigoPeriodicidadeNegativacao(sistemaParametro
						.getCodigoPeriodicidadeNegativacao().toString());
			}

			form.setNumeroDiasCalculoAcrescimos(""
					+ sistemaParametro.getNumeroDiasCalculoAcrescimos());

			form.setNumeroDiasValidadeExtrato(sistemaParametro
					.getNumeroDiasValidadeExtrato().toString());

			form.setIndicadorParcelamentoConfirmado(""
					+ sistemaParametro.getIndicadorParcelamentoConfirmado());

			form.setIndicadorTabelaPrice(""
					+ sistemaParametro.getIndicadorTabelaPrice());

			if (sistemaParametro
					.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo() != null) {
				form.setNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo(sistemaParametro
						.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo()
						.toString());
			}

			if (sistemaParametro
					.getNumeroDiasValidadeExtratoPermissaoEspecial() != null) {
				form.setNumeroDiasValidadeExtratoPermissaoEspecial(sistemaParametro
						.getNumeroDiasValidadeExtratoPermissaoEspecial()
						.toString());
			}

			form.setNumeroDiasVencimentoEntradaParcelamento(""
					+ sistemaParametro
							.getNumeroDiasVencimentoEntradaParcelamento());
			
			if (sistemaParametro
					.getNumeroDiasVencContaEntradaParcelamento() != null) {
				form.setNumeroDiasVencContaEntradaParcelamento(sistemaParametro
						.getNumeroDiasVencContaEntradaParcelamento()
						.toString());
			}
			if (sistemaParametro
					.getNumeroDiasCancelamentoEntradaParcelamento() != null) {
				form.setNumeroDiasCancelamentoEntradaParcelamento(sistemaParametro
						.getNumeroDiasCancelamentoEntradaParcelamento()
						.toString());
			}
			
			if (sistemaParametro.getIndicadorRateioAreaComumImovelNaoFat() != null) {
				form.setIndicadorRateioAreaComumImovelNaoFat(sistemaParametro
					.getIndicadorRateioAreaComumImovelNaoFat()
						.toString());
			}

			if (sistemaParametro.getIndicadorBloqueioContasContratoParcelDebitos() != null) {
				form.setIndicadorBloqueioContasContratoParcelDebitos(sistemaParametro
						.getIndicadorBloqueioContasContratoParcelDebitos()
						.toString());
			}

			if (sistemaParametro
					.getIndicadorBloqueioContasContratoParcelManterConta() != null) {
				form.setIndicadorBloqueioContasContratoParcelManterConta(sistemaParametro
						.getIndicadorBloqueioContasContratoParcelManterConta()
						.toString());
			}
			
			
			if (sistemaParametro
					.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta() != null) {
				form.setIndicadorBloqueioGuiasOuAcresContratoParcelManterConta(sistemaParametro
						.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta()
						.toString());
			}

			if (sistemaParametro.getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito() != null) {
				form.setIndicadorBloqueioDebitoACobrarContratoParcelManterDebito(sistemaParametro
						.getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito()
						.toString());
			}
			
			if (sistemaParametro.getIndicadorCriticarConteudoRetornoMovimentoNegativacao() == null) {
				form.setIndicadorCriticarConteudoRetornoMovimentoNegativacao(sistemaParametro
						.getIndicadorCriticarConteudoRetornoMovimentoNegativacao()
						.toString());
			}
			
			if ( sistemaParametro.getIndicadorCalcAcresImpontGuiaPagamento() != null ) {
				form.setIndicadorCalcAcresImpontGuiaPagamento( sistemaParametro.getIndicadorCalcAcresImpontGuiaPagamento().toString() );
			}

			if (sistemaParametro.getIndicadorTotalDebito() != null) {
				form.setIndicadorTotalDebito(sistemaParametro
						.getIndicadorTotalDebito().toString());
			}

			if (sistemaParametro.getIndicadorCanceNegatContaVencAlter() != null) {
				form.setIndicadorCanceNegatContaVencAlter(sistemaParametro
						.getIndicadorCanceNegatContaVencAlter().toString());
			}

			if (sistemaParametro
					.getIndicadorBloqueioDebitoACobrarContratoParcelDebito() != null) {
				form.setIndicadorBloqueioDebitoACobrarContratoParcelDebito(sistemaParametro
						.getIndicadorBloqueioDebitoACobrarContratoParcelDebito()
						.toString());
			}
			
			if(sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito()!=null){
				form.setIndicadorBloqueioGuiasOuAcresContratoParcelDebito(sistemaParametro
					.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito().toString());
				
			}
			
			if(sistemaParametro.getIndicadorExcluirNegativacaoAposPgmto()!=null){
				form.setIndicadorExcluirNegativacaoAposPgmto(sistemaParametro
					.getIndicadorExcluirNegativacaoAposPgmto().toString());
				
			}
			
			/*
			 * Autor: Jonathan Marcos
			 * Data: 03/10/2013
			 * [Observacao] 1.2.2.37. Indicador para verificar
			 * a acao predecessora para os imoveis do arquivo texto
			 */
			if (sistemaParametro.getIndicadorAcaoPredecessoraImoveisArquivoTexto() != null) {
				form.setIndicadorAcaoPredecessoraImoveisArquivoTexto(sistemaParametro
						.getIndicadorAcaoPredecessoraImoveisArquivoTexto().toString());
			}

			/*
			 * Autor: Diogo Luiz
			 * Data: 23/09/2014
			 * 1.2.2.38. Indicador de Permiss�o de Altera��o de Dados ou V�nculo de Cliente Negativado 
			 */
			if(sistemaParametro.getIndicadorPermissaoAlteracaoClienteNegativado() != null){
				form.setIndicadorPermissaoAlteracaoClienteNegativado(sistemaParametro
					.getIndicadorPermissaoAlteracaoClienteNegativado().toString());
			}			
		}
			
		Collection colecaoResolucaoDiretoria = this.getFachada()
				.pesquisarResolucaoDiretoriaMaiorDataVigenciaInicio();
		httpServletRequest.setAttribute("colecaoResolucaoDiretoria",
				colecaoResolucaoDiretoria);				
		
		return retorno;
	}
}

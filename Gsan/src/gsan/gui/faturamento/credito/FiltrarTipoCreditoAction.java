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
package gsan.gui.faturamento.credito;


import gsan.faturamento.credito.FiltroCreditoTipo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.operacional.FiltroSistemaEsgoto;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ComparacaoTexto;
import gsan.util.filtro.ComparacaoTextoCompleto;
import gsan.util.filtro.MaiorQue;
import gsan.util.filtro.MenorQue;
import gsan.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Thiago Ten�rio
 * @date 05/08/2006
 */
public class FiltrarTipoCreditoAction extends GcomAction {

	/**
	 * Este caso de uso permite Pesquisar um Tipo de Servic�o
	 * 
	 * [UC0437] Pesquisar Tipo de Servi�o de Refer�ncia
	 * 
	 * 
	 * @author Thiago Ten�rio
	 * @date 31/07/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterTipoCredito");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarTipoCreditoActionForm filtrarTipoCreditoActionForm = (FiltrarTipoCreditoActionForm) actionForm;

		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();

		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String descricao = filtrarTipoCreditoActionForm.getDescricao();

		String abreviatura = filtrarTipoCreditoActionForm.getAbreviatura();

		String tipoLancamentoContabil = filtrarTipoCreditoActionForm.getTipoLancamentoContabil();

		String indicadorgeracaoAutomaica = filtrarTipoCreditoActionForm
				.getIndicadorgeracaoAutomaica();

		String indicadorUso = filtrarTipoCreditoActionForm.getIndicativoUso();

		String valorLimiteCreditoInicial = filtrarTipoCreditoActionForm.getValorLimiteCreditoInicial();

		String valorLimiteCreditoFinal = filtrarTipoCreditoActionForm.getValorLimiteCreditoFinal();
		
		String tipoPesquisa = filtrarTipoCreditoActionForm.getTipoPesquisa();

		// Verifica se o campo Descri��o da Anormalidade de Leitura foi informado
		
		// Descri��o do Sistema de Esgoto
		if (descricao != null && !descricao.equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				
				filtroCreditoTipo.adicionarParametro(new ComparacaoTextoCompleto(FiltroCreditoTipo.DESCRICAO, 
						descricao));
			} else {
				
				filtroCreditoTipo.adicionarParametro(new ComparacaoTexto(FiltroSistemaEsgoto.DESCRICAO,
						descricao));
			}
		}		
		
		// Verifica se o campo Descri��o da Anormalidade de Leitura foi informado

		if (abreviatura != null
				&& !abreviatura.trim().equalsIgnoreCase(
						"")) {

			peloMenosUmParametroInformado = true;

			filtroCreditoTipo.adicionarParametro(new ComparacaoTexto(
					FiltroCreditoTipo.DESCRICAO_ABREVIADA, abreviatura));

		}

		// Verifica se o campo LancamentoItemContabil foi informado

		if (tipoLancamentoContabil != null
				&& !tipoLancamentoContabil.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroCreditoTipo.adicionarParametro(new ParametroSimples(
					FiltroCreditoTipo.LANCAMENTO_ITEM_CONTABIL,
					tipoLancamentoContabil));

		}
		
		// Verifica se o campo LancamentoItemContabil foi informado
		if (indicadorgeracaoAutomaica != null
				&& !indicadorgeracaoAutomaica.trim().equals("3") && !indicadorgeracaoAutomaica.trim().equals("")) {

			peloMenosUmParametroInformado = true;

			filtroCreditoTipo.adicionarParametro(new ParametroSimples(
					FiltroCreditoTipo.INDICADOR_GERACAO_AUTOMATICO,
					indicadorgeracaoAutomaica));

		}
		
		// se o usu�rio informar o intervalo inicial do valor de limite
		if (valorLimiteCreditoInicial != null
				&& !valorLimiteCreditoInicial.trim().equalsIgnoreCase("")) {

			// se o usu�rio n�o informar o intervalo final do valor de
			// limite
			if (valorLimiteCreditoFinal == null
					|| valorLimiteCreditoFinal.trim().equalsIgnoreCase("")) {
				// o intervalo final do valor de limite vai receber o valor
				// do intervalo inicial
				valorLimiteCreditoFinal = valorLimiteCreditoInicial;

				// se o usu�rio informar o intervalo final do valor de
				// limite
			} else {
				// se o intervalo final do valor de limite for menor que o
				// inicial
				if ((Util
						.formatarMoedaRealparaBigDecimal(valorLimiteCreditoInicial))
						.doubleValue() > ((Util
						.formatarMoedaRealparaBigDecimal(valorLimiteCreditoFinal)))
						.doubleValue()) {
					// levanta a exce��o para a pr�xima camada
					throw new ActionServletException("atencao.valorlimitefinal.menorque");
				}
			}

			// se o usu�rio n�o informar o intervalo inicial do valor de
			// limite
		} else {
			// seta o intervalo final do valor de limite para null
			valorLimiteCreditoFinal = null;
		}

		// se o intervalo final do valor de limite n�o estiver nulo ou em
		// branco
		if (valorLimiteCreditoFinal != null
				&& !valorLimiteCreditoFinal.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			peloMenosUmParametroInformado = true;
			// seta no filtro para retornar os tipos de d�bito entre os
			// valores informados
			filtroCreditoTipo
					.adicionarParametro(new MaiorQue(
							FiltroCreditoTipo.VALOR_LIMITE_CREDITO,
							Util
									.formatarMoedaRealparaBigDecimal(valorLimiteCreditoInicial),
							ParametroSimples.CONECTOR_AND));
			filtroCreditoTipo
					.adicionarParametro(new MenorQue(
							FiltroCreditoTipo.VALOR_LIMITE_CREDITO,
							Util
									.formatarMoedaRealparaBigDecimal(valorLimiteCreditoFinal)));
		}

		if (indicadorUso != null && !indicadorUso.trim().equals("3") && !indicadorUso.trim().equals("")) {

			peloMenosUmParametroInformado = true;

			filtroCreditoTipo.adicionarParametro(new ParametroSimples(
					FiltroCreditoTipo.INDICADOR_USO, indicadorUso));

		}


		// Erro caso o usu�rio mandou Pesquisar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");

		sessao.setAttribute("filtroTipoCredito",
				filtroCreditoTipo);

		return retorno;
	}

}
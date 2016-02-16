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
package gcom.gui.faturamento.debito;

import gcom.atendimentopublico.FiscalizarParametroCalculoDebito;
import gcom.cobranca.parcelamento.FiltroParcelamentoItem;
import gcom.cobranca.parcelamento.ParcelamentoItem;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarHistorico;
import gcom.faturamento.debito.DemonstrativoFormulaCalculoHelper;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.faturamento.debito.FiltroDebitoACobrarHistorico;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ControladorException;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o da tela de consultar d�bito cobrado
 * 
 * @author Fernanda Paiva
 * @created 16 de Janeiro de 2006
 */
public class ExibirConsultarDebitoACobrarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno e seta o mapeamento para a tela de
		// consultar d�bito cobrado
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarDebitoACobrar");

		// cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// cria uma inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		// recupera o c�digo da conta do request
		String idImovel = httpServletRequest.getParameter("imovelID");

		String idDebito = httpServletRequest.getParameter("debitoID");
		String idDebitoHistorico = httpServletRequest.getParameter("debitoHistoricoID");

		String idParcelamento = httpServletRequest
				.getParameter("parcelamentoID");

		// se o c�digo n�o for nulo
//		if (idImovel != null && !idImovel.equalsIgnoreCase("")) {
//			// remove a cole��o de d�bitos a cobrar
//			sessao.removeAttribute("colecaoDebitoACobrar");
//			sessao.removeAttribute("colecaoDebitoACobrarHistorico");
//		}

		/*
		 * Pesquisando o debito a partir do id imovel
		 * =====================================================================
		 */

		if (idImovel != null && !idImovel.equalsIgnoreCase("")) {

			// cria o filtro do imovel
			String imovel = fachada.pesquisarInscricaoImovelExcluidoOuNao(new Integer(idImovel));
			String enderecoFormatado = "";
			try {
				enderecoFormatado = fachada.pesquisarEnderecoFormatado(new Integer(idImovel));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ControladorException e) {
				e.printStackTrace();
			}

			httpServletRequest.setAttribute("idImovelConsultado", idImovel);
			httpServletRequest.setAttribute("enderecoFormatado", enderecoFormatado);

			// se n�o encontrou nenhum imovel com o c�digo informado
			if (imovel == null || imovel.equalsIgnoreCase("")) {
				throw new ActionServletException("atencao.imovel.inexistente");
			}

			// seta o objeto conta na sess�o
			httpServletRequest.setAttribute("imovelId", idImovel);
		}
		// ====================================================================

		// cria a cole��o de d�bitos a cobrar
		Collection colecaoDebitoACobrarConsultar;
		Collection colecaoDebitoACobrarHistoricoConsultar;

		/*
		 * D�bitos Cobrados (Carregar cole��o)
		 * ======================================================================
		 */
		// se n�o existir a cole��o na sess�o
		if (idParcelamento != null && !idParcelamento.equals("")) {
			FiltroParcelamentoItem filtroParcelamentoItem = new FiltroParcelamentoItem();

			// seta o c�digo do imovel no filtro
			filtroParcelamentoItem.adicionarParametro(new ParametroSimples(FiltroParcelamentoItem.PARCELAMENTO, new Integer(idParcelamento)));

			filtroParcelamentoItem.adicionarParametro(new ParametroNaoNulo(FiltroParcelamentoItem.DEBITO_A_COBRAR));

			// carrega o debitoACobrar
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.debitoTipo");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.debitoCreditoSituacaoAtual");
//			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar");
			/*
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.geracaoDebito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.anoMesReferenciaDebito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.anoMesCobrancaDebito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.numeroPrestacaoCobradas");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.numeroPrestacaoDebito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.valorDebito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.valorTotal");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.percentualTaxaJurosFinanciamento");
			 */
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.registroAtendimento");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.ordemServico");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.financiamentoTipo");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.cobrancaForma");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.usuario");

			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.debitoTipo");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.debitoCreditoSituacaoAtual");

			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.registroAtendimento");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.ordemServico");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.financiamentoTipo");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.cobrancaForma");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.usuario");
			// carrega o parcelamento
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("parcelamento");
			
			// carrega o im�vel origem do d�bito a cobrar e debito a cobrar historico
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.debitoACobrarGeralOrigem.debitoACobrar.imovel");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.origem.debitoACobrarHistorico.imovel");

			Collection colecaoParcelamentoItem = fachada.pesquisar(filtroParcelamentoItem, ParcelamentoItem.class.getName());

			if (colecaoParcelamentoItem == null	|| colecaoParcelamentoItem.isEmpty()) {
				throw new ActionServletException("atencao.faturamento.debito_a_cobrar.inexistente");
			} else {
				// seta a cole��o de d�bitos cobrados na sess�o
				sessao.setAttribute("colecaoParcelamentoItem",	colecaoParcelamentoItem);
				sessao.removeAttribute("colecaoDebitoACobrarHistoricoConsultar");
				sessao.removeAttribute("colecaoDebitoACobrarConsultar");
				sessao.removeAttribute("fiscalizarParametroCalculoDebito");
				sessao.removeAttribute("demonstrativoFormulaCalculo");
			}
			
		}else if(idDebitoHistorico != null && !idDebitoHistorico.equals("")){	
			//dados de DEBITO A COBRAR HISTORICO
			
			//cria o filtro de d�bito a cobrar
			FiltroDebitoACobrarHistorico filtroDebitoACobrarHistorico = new FiltroDebitoACobrarHistorico();

			// seta o c�digo do imovel no filtro
			filtroDebitoACobrarHistorico.adicionarParametro(new ParametroSimples(FiltroDebitoACobrarHistorico.ID, new Integer(idDebitoHistorico)));
			
			if (idImovel != null) {
				filtroDebitoACobrarHistorico.adicionarParametro(new ParametroSimples(FiltroDebitoACobrarHistorico.IMOVEL_ID, new Integer(idImovel)));
			}

			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
			
			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("ordemServico");

			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");

			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");

			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("cobrancaForma");
			
			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("usuario");
			
			// carrega o im�vel origem do d�bito a cobrar
			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("origem.debitoACobrarHistorico.imovel");

			// pesquisa a cole��o de d�bitos cobrados
			colecaoDebitoACobrarHistoricoConsultar = fachada.pesquisar(filtroDebitoACobrarHistorico, DebitoACobrarHistorico.class.getName());
			if (colecaoDebitoACobrarHistoricoConsultar == null || colecaoDebitoACobrarHistoricoConsultar.isEmpty()) {
				throw new ActionServletException("atencao.faturamento.debito_a_cobrar.inexistente");
			} else {
				// seta a cole��o de d�bitos cobrados na sess�o
				sessao.setAttribute("colecaoDebitoACobrarHistoricoConsultar", colecaoDebitoACobrarHistoricoConsultar);
				sessao.removeAttribute("colecaoDebitoACobrarConsultar");
				sessao.removeAttribute("colecaoParcelamentoItem");
				sessao.removeAttribute("fiscalizarParametroCalculoDebito");
				sessao.removeAttribute("demonstrativoFormulaCalculo");
			}
			
		} else {
			//dados de DEBITO A COBRAR
			
			// cria o filtro de d�bito a cobrar
			FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();

			if (idDebito != null) {
				// seta o c�digo do imovel no filtro
				filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.ID, new Integer(idDebito)));
			}
			if (idImovel != null) {
				filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.IMOVEL_ID, new Integer(idImovel)));
			}

			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
			
			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("ordemServico");

			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");

			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");

			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("cobrancaForma");
			
			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("usuario");
			
			// carrega o im�vel origem do d�bito a cobrar
			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeralOrigem.debitoACobrar.imovel");
			
			// carrega o im�vel origem do d�bito a cobrar
			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeralOrigem.debitoACobrarHistorico.imovel");

			// pesquisa a cole��o de d�bitos cobrados
			colecaoDebitoACobrarConsultar = fachada.pesquisar(filtroDebitoACobrar, DebitoACobrar.class.getName());
			if (colecaoDebitoACobrarConsultar == null || colecaoDebitoACobrarConsultar.isEmpty()) {
				throw new ActionServletException("atencao.faturamento.debito_a_cobrar.inexistente");
			} else {
				// seta a cole��o de d�bitos cobrados na sess�o
				sessao.setAttribute("colecaoDebitoACobrarConsultar", colecaoDebitoACobrarConsultar);
				sessao.removeAttribute("colecaoParcelamentoItem");
				sessao.removeAttribute("colecaoDebitoACobrarHistoricoConsultar");
				sessao.removeAttribute("fiscalizarParametroCalculoDebito");
				//sessao.removeAttribute("demonstrativoFormulaCalculo");
			}
		}
		// ====================================================================

		// 1.12. O sistema verifica se o d�bito a cobrar tem par�metros de c�lculo de situa��o de 
        // fiscaliza��o para exibir[SB0001-Exibir Par�metros de C�lculo];
		//sessao.removeAttribute("demonstrativoFormulaCalculoHelper");
		
		if (idDebito != null){
			FiscalizarParametroCalculoDebito fiscalizarParametroCalculoDebito = null;
			fiscalizarParametroCalculoDebito = fachada.pesquisarParametroCalculoDebito(Integer.valueOf(idDebito));
			
			if (fiscalizarParametroCalculoDebito != null){
				sessao.setAttribute("fiscalizarParametroCalculoDebito", fiscalizarParametroCalculoDebito);
				
				//RM1165 - Exibir demonstrativo da f�rmula de c�lculo
				if(fiscalizarParametroCalculoDebito.getCodCalculoConsumo()!=null){
					DemonstrativoFormulaCalculoHelper demonstrativoFormulaCalculo = Fachada.getInstancia()
							.obterDemonstrativoFormulaCalculo(fiscalizarParametroCalculoDebito);
					
					if (demonstrativoFormulaCalculo!=null){
						sessao.setAttribute("demonstrativoFormulaCalculoHelper", demonstrativoFormulaCalculo);
					}
				}
			}
		} else if (idDebitoHistorico != null){
			FiscalizarParametroCalculoDebito fiscalizarParametroCalculoDebito = null;
			fiscalizarParametroCalculoDebito = fachada.pesquisarParametroCalculoDebito(Integer.valueOf(idDebitoHistorico));
			
			if (fiscalizarParametroCalculoDebito != null){
				sessao.setAttribute("fiscalizarParametroCalculoDebito", fiscalizarParametroCalculoDebito);
				
				//RM1165 - Exibir demonstrativo da f�rmula de c�lculo
				if(fiscalizarParametroCalculoDebito.getCodCalculoConsumo()!=null){
					DemonstrativoFormulaCalculoHelper demonstrativoFormulaCalculo = Fachada.getInstancia()
							.obterDemonstrativoFormulaCalculo(fiscalizarParametroCalculoDebito);
					
					if (demonstrativoFormulaCalculo!=null){
						sessao.setAttribute("demonstrativoFormulaCalculoHelper", demonstrativoFormulaCalculo);						
					}
				}
			}
		}
		
		
		// envia uma flag que ser� verificado no cliente_resultado_pesquisa.jsp
		// para saber se ir� usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaConsultaDebitoACobrar") != null) {
			sessao.setAttribute("caminhoRetornoTelaConsultaDebitoACobrar",
					httpServletRequest
							.getParameter("caminhoRetornoTelaConsultaDebitoACobrar"));
		}

		// retorna o mapeamento contido na vari�vel retorno
		return retorno;
	}
}

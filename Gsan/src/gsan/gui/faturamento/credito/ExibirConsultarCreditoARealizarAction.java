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

import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cobranca.parcelamento.FiltroParcelamentoItem;
import gsan.cobranca.parcelamento.ParcelamentoItem;
import gsan.fachada.Fachada;
import gsan.faturamento.credito.CreditoARealizar;
import gsan.faturamento.credito.CreditoARealizarHistorico;
import gsan.faturamento.credito.FiltroCreditoARealizar;
import gsan.faturamento.credito.FiltroCreditoARealizarHistorico;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.filtro.ParametroNaoNulo;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o da tela de consultar cr�dito a realizar
 * 
 * @author Fernanda Paiva
 * @created 17 de Janeiro de 2006
 */
public class ExibirConsultarCreditoARealizarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno e seta o mapeamento para a tela de
		// consultar Cr�dito realizado
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarCreditoARealizar");

		// cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// cria uma inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		// recupera o c�digo da conta do request
		String idImovel = httpServletRequest.getParameter("imovelID");
		String idCredito = httpServletRequest.getParameter("creditoID");
		String idCreditoHistorico = httpServletRequest.getParameter("creditoHistoricoID");
		String idParcelamento = httpServletRequest.getParameter("parcelamentoID");

		// se o c�digo n�o for nulo
		if (idImovel != null && !idImovel.equalsIgnoreCase("")) {
			// remove o objeto conta da sess�o
			sessao.removeAttribute("imovelConsultar");
			// remove a cole��o de cr�ditos a realizar
			sessao.removeAttribute("colecaoCreditoARealizar");
			sessao.removeAttribute("colecaoCreditoARealizarHistorico");
		}

		/*
		 * Pesquisando o cr�dito a partir do id imovel
		 * =====================================================================
		 */

		// cria o objeto imovel
		Imovel imovelConsultar = null;

		// se o c�digo da conta n�o for nulo
		if (idImovel != null && !idImovel.equalsIgnoreCase("")) {

			// cria o filtro do imovel
			FiltroImovel filtroImovel = new FiltroImovel();

			// seta o c�digo do imovel no filtro
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, idImovel));

			// pesquisa o imovel na base de dados
			Collection colecaoImovel = fachada.pesquisar(filtroImovel,
					Imovel.class.getName());

			// se n�o encontrou nenhum imovel com o c�digo informado
			if (colecaoImovel == null || colecaoImovel.isEmpty()) {
				throw new ActionServletException("atencao.imovel.inexistente");
			}

			String enderecoFormatado = "";
			try {
				enderecoFormatado = fachada
						.pesquisarEnderecoFormatado(new Integer(idImovel));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ControladorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			httpServletRequest.setAttribute("enderecoFormatado",
					enderecoFormatado);

			// recupera o objeto imovel da cole��o
			imovelConsultar = (Imovel) Util
					.retonarObjetoDeColecao(colecaoImovel);

			// seta o objeto conta na sess�o
			sessao.setAttribute("imovelConsultar", imovelConsultar);
		}
		// se j� existe uma conta na sess�o
		else if (sessao.getAttribute("imovelConsultar") != null) {
			// recupera a conta da sess�o
			imovelConsultar = (Imovel) sessao.getAttribute("imovelConsultar");
		} else {
			// levanta o erro de conta inexistente
			throw new ActionServletException("atencao.imovel.inexistente");
		}
		// ====================================================================

		/*
		 * Cr�ditos A Realizar (Carregar cole��o)
		 * ======================================================================
		 */
		// se n�o existir a cole��o na sess�o
		if (idParcelamento != null && !idParcelamento.equals("")) {
			FiltroParcelamentoItem filtroParcelamentoItem = new FiltroParcelamentoItem();

			// seta o c�digo do imovel no filtro
			filtroParcelamentoItem.adicionarParametro(new ParametroSimples(
					FiltroParcelamentoItem.PARCELAMENTO, idParcelamento));

			filtroParcelamentoItem.adicionarParametro(new ParametroNaoNulo(
					FiltroParcelamentoItem.CREDITO_A_REALIZAR));

			// carrega o debitoACobrar
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.creditoTipo");
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.debitoCreditoSituacaoAtual");
			/*
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.geracaoCredito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.anoMesReferenciaCredito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.anoMesCobrancaCredito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.numeroPrestacaoRealizada");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.numeroPrestacaoCredito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.valorCredito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.valorTotal");
			 */
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar");
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.registroAtendimento");
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.ordemServico");
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.creditoOrigem");
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.usuario");

			// carrega o parcelamento
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("parcelamento");
			
			// carrega o im�vel origem do cr�dito a realizar
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.origem.creditoARealizar.imovel");

			Collection colecaoParcelamentoItem = fachada.pesquisar(
					filtroParcelamentoItem, ParcelamentoItem.class.getName());

			if (colecaoParcelamentoItem == null
					|| colecaoParcelamentoItem.isEmpty()) {
				throw new ActionServletException(
						"atencao.faturamento.credito_a_realizar.inexistente");
			} else {
				// seta a cole��o de d�bitos cobrados na sess�o
				sessao.setAttribute("colecaoParcelamentoItem",
						colecaoParcelamentoItem);
			}
		} else if(idCreditoHistorico != null && !idCreditoHistorico.equals("")){
			// CREDITO A COBRAR HISTORICO
			
			//cria o filtro de cr�ditos a realizar historico
			FiltroCreditoARealizarHistorico filtroCreditoARealizarHistorico = new FiltroCreditoARealizarHistorico();

			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");

			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");

			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("ordemServico");

			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");

			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("creditoOrigem");
			
			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("usuario");
			
			// carrega o im�vel origem do cr�dito a realizar historico
			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("origem.creditoARealizarHistorico.imovel");

			if (idImovel != null) {
				Integer imovel = Integer.parseInt(idImovel);
				filtroCreditoARealizarHistorico.adicionarParametro(new ParametroSimples(
						FiltroCreditoARealizarHistorico.IMOVEL_ID, imovel));
			}

			// seta o c�digo do credito no filtro
			Integer creditoHistorico = Integer.parseInt(idCreditoHistorico);
			filtroCreditoARealizarHistorico.adicionarParametro(new ParametroSimples(
					FiltroCreditoARealizarHistorico.ID, creditoHistorico));

			// pesquisa a cole��o de cr�ditos a realizar historico
			Collection<CreditoARealizar> colecaoCreditoARealizarHistorico = fachada
					.pesquisar(filtroCreditoARealizarHistorico, CreditoARealizarHistorico.class.getName());

			if (colecaoCreditoARealizarHistorico == null || colecaoCreditoARealizarHistorico.isEmpty()) {
				throw new ActionServletException("atencao.faturamento.credito_a_realizar.inexistente");
			} else {
				// seta a cole��o de cr�ditos a realizar historico na sess�o
				sessao.setAttribute("colecaoCreditoARealizarHistorico", colecaoCreditoARealizarHistorico);
			}
			
			
		} else {
			// cria o filtro de cr�ditos a realizar
			FiltroCreditoARealizar filtroCreditoARealizar = new FiltroCreditoARealizar();

			filtroCreditoARealizar
					.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");

			filtroCreditoARealizar
					.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");

			filtroCreditoARealizar
					.adicionarCaminhoParaCarregamentoEntidade("ordemServico");

			filtroCreditoARealizar
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");

			filtroCreditoARealizar
					.adicionarCaminhoParaCarregamentoEntidade("creditoOrigem");
			
			filtroCreditoARealizar
					.adicionarCaminhoParaCarregamentoEntidade("usuario");
			
			// carrega o im�vel origem do cr�dito a realizar
			filtroCreditoARealizar
					.adicionarCaminhoParaCarregamentoEntidade("origem.creditoARealizar.imovel");

			if (idImovel != null) {
				Integer imovel = Integer.parseInt(idImovel);
				filtroCreditoARealizar.adicionarParametro(new ParametroSimples(
						FiltroCreditoARealizar.IMOVEL_ID, imovel));
			}

			if (idCredito != null) {
				// seta o c�digo do credito no filtro
				Integer credito = Integer.parseInt(idCredito);
				filtroCreditoARealizar.adicionarParametro(new ParametroSimples(
						FiltroCreditoARealizar.ID, credito));
			}

			// pesquisa a cole��o de cr�ditos a realizar
			Collection<CreditoARealizar> colecaoCreditoARealizar = fachada
					.pesquisar(filtroCreditoARealizar, CreditoARealizar.class
							.getName());

			if (colecaoCreditoARealizar == null
					|| colecaoCreditoARealizar.isEmpty()) {
				throw new ActionServletException(
						"atencao.faturamento.credito_a_realizar.inexistente");
			} else {
				// seta a cole��o de cr�ditos a realizar na sess�o
				sessao.setAttribute("colecaoCreditoARealizar",
						colecaoCreditoARealizar);
			}
		}
		// ====================================================================

		// envia uma flag que ser� verificado no cliente_resultado_pesquisa.jsp
		// para saber se ir� usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaConsultaCreditoARealizar") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaConsultaCreditoARealizar",
							httpServletRequest
									.getParameter("caminhoRetornoTelaConsultaCreditoARealizar"));
		}

		// retorna o mapeamento contido na vari�vel retorno
		return retorno;
	}
}

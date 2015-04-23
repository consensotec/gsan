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
package gsan.gui.cobranca.spcserasa;

import gsan.cobranca.bean.DadosConsultaNegativacaoHelper;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Ana Maria
 * @date 09/11/2006
 * 
 */
public class ExibirConsultarResumoNegativacaoPopupAction extends
		GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta a ação de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarResumoNegativacaoPopup");

		HttpSession sessao = getSessao(httpServletRequest);

		BigDecimal somatorioValorDebito = new BigDecimal(0);
		if(httpServletRequest.getParameter("valorTotal") != null){
			somatorioValorDebito = new BigDecimal(httpServletRequest.getParameter("valorTotal"));
		}
		sessao.setAttribute("somatorioValorDebito",somatorioValorDebito);
		
		String descricaoIncluidas = (String)httpServletRequest.getParameter("descricao"); 
		sessao.setAttribute("descricaoIncluidas",descricaoIncluidas);
		
		String stNegativacao = (String)httpServletRequest.getParameter("stNegociacao");
		String situacaoNegativacao = ""; 
		if(stNegativacao.equals("1")){
			situacaoNegativacao = "NEGATIVAÇÕES INCLUÍDAS";
			sessao.setAttribute("situacaoNegativacao",situacaoNegativacao);
			
		}else if(stNegativacao.equals("2")){
			situacaoNegativacao = "NEGATIVAÇÕES INCLUÍDAS E CONFORMADAS";
			sessao.setAttribute("situacaoNegativacao",situacaoNegativacao);
			
		}else if(stNegativacao.equals("3")){
			situacaoNegativacao = "NEGATIVAÇÕES INCLUÍDAS E NÃO CONFORMADAS";
			sessao.setAttribute("situacaoNegativacao",situacaoNegativacao);
			
		}
		
        //**************************************************************************************
        // Pesquisa os dados do resumo da Negociacao agrupando por ligacaoAguaSituacao
        Collection<NegativacaoHelper>colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp = new ArrayList();
        Integer idSituacaoDebito = new Integer(httpServletRequest.getParameter("idSituacaoCobranca"));
        DadosConsultaNegativacaoHelper dadosConsultaNegativacaoHelper = 
			(DadosConsultaNegativacaoHelper) sessao.getAttribute("dadosConsultaNegativacaoHelper");
        
		Collection<NegativacaoHelper> colecaoResumoNegativacaoLigacaoAguaSituacao = 
			Fachada.getInstancia().consultarResumoNegativacaoLigacaoAguaPorSituacaoDebito(
					dadosConsultaNegativacaoHelper, idSituacaoDebito);
		
		Iterator itcolecaoResumoNegativacao = colecaoResumoNegativacaoLigacaoAguaSituacao.iterator();
		BigDecimal valorDebito = new BigDecimal(0);
		BigDecimal percentualValorDebito = new BigDecimal(0);
		BigDecimal valorPotencial = new BigDecimal(0);
		Integer quantidadePotencial = new Integer(0);
		BigDecimal valorFactivel = new BigDecimal(0);
		Integer quantidadeFactivel = new Integer(0);
		BigDecimal valorLigado = new BigDecimal(0);
		Integer quantidadeLigado = new Integer(0);
		BigDecimal valorEmAnalise = new BigDecimal(0);
		Integer quantidadeEmAnalise = new Integer(0);
		BigDecimal valorCortado = new BigDecimal(0);
		Integer quantidadeCortado = new Integer(0);
		BigDecimal valorSuprimido = new BigDecimal(0);
		Integer quantidadeSuprimido = new Integer(0);
		BigDecimal valorSuprimidoParc = new BigDecimal(0);
		Integer quantidadeSuprimidoParc = new Integer(0);
		BigDecimal valorSuprimidoParcPedido = new BigDecimal(0);
		Integer quantidadeSuprimidoParcPedido = new Integer(0);
		
		Integer quantidadePendente = new Integer(0);
		Integer quantidadePago = new Integer(0);
		Integer quantidadeParcelado = new Integer(0);
		Integer quantidadeCancelado = new Integer(0);
		
		
		
		while(itcolecaoResumoNegativacao.hasNext()){
			NegativacaoHelper retumoTotais = (NegativacaoHelper)itcolecaoResumoNegativacao.next();
			valorDebito = retumoTotais.getSomatorioValorDebito();
			
			// o getIdSituacaoCobranca sera reaproveitado para armazenar o id da situacao da agua;
			if(retumoTotais.getIdSituacaoCobranca() != null && retumoTotais.getIdSituacaoCobranca().equals(1)){ // POTENCIAL
				// Valor
				
				if (idSituacaoDebito.equals(ConstantesSistema.PENDENTE)){
					
					valorPotencial = valorPotencial.add(retumoTotais.getSomatorioValorPendente());
					quantidadePendente += retumoTotais.getSomatorioQuantidadeInclusoes();
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PAGO)){
					quantidadePago += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorPotencial = valorPotencial.add(retumoTotais.getSomatorioValorPago());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PARCELADO)){
					quantidadeParcelado += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorPotencial = valorPotencial.add(retumoTotais.getSomatorioValorParcelado());
				}
				else{
					quantidadeCancelado += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorPotencial = valorPotencial.add(retumoTotais.getSomatorioValorCancelado());
				}
				
				// Quantidade
				quantidadePotencial = retumoTotais.getSomatorioQuantidadeInclusoes();
			}else if(retumoTotais.getIdSituacaoCobranca() != null && retumoTotais.getIdSituacaoCobranca().equals(2)){ // FACTIVEL
				// Valor
				
				if (idSituacaoDebito.equals(ConstantesSistema.PENDENTE)){
					quantidadePendente += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorFactivel = valorFactivel.add(retumoTotais.getSomatorioValorPendente());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PAGO)){
					quantidadePago += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorFactivel = valorFactivel.add(retumoTotais.getSomatorioValorPago());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PARCELADO)){
					quantidadeParcelado += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorFactivel = valorFactivel.add(retumoTotais.getSomatorioValorParcelado());
				}
				else{
					quantidadeCancelado += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorFactivel = valorFactivel.add(retumoTotais.getSomatorioValorCancelado());
				}
				
				// Calculo dos Percentuais
				quantidadeFactivel =  retumoTotais.getSomatorioQuantidadeInclusoes();
			}else if(retumoTotais.getIdSituacaoCobranca() != null && retumoTotais.getIdSituacaoCobranca().equals(3)){ // LIGADO
				// Valor
				
				if (idSituacaoDebito.equals(ConstantesSistema.PENDENTE)){
					quantidadePendente += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorLigado = valorLigado.add(retumoTotais.getSomatorioValorPendente());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PAGO)){
					quantidadePago += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorLigado = valorLigado.add(retumoTotais.getSomatorioValorPago());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PARCELADO)){
					quantidadeParcelado += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorLigado = valorLigado.add(retumoTotais.getSomatorioValorParcelado());
				}
				else{
					quantidadeCancelado += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorLigado = valorLigado.add(retumoTotais.getSomatorioValorCancelado());
				}
				
				// Calculo dos Percentuais
				quantidadeLigado = retumoTotais.getSomatorioQuantidadeInclusoes();
			}else if(retumoTotais.getIdSituacaoCobranca() != null && retumoTotais.getIdSituacaoCobranca().equals(4)){ // EM ANALISE
				// Valor
				
				if (idSituacaoDebito.equals(ConstantesSistema.PENDENTE)){
					quantidadePendente += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorEmAnalise = valorEmAnalise.add(retumoTotais.getSomatorioValorPendente());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PAGO)){
					quantidadePago += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorEmAnalise = valorEmAnalise.add(retumoTotais.getSomatorioValorPago());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PARCELADO)){
					quantidadeParcelado += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorEmAnalise = valorEmAnalise.add(retumoTotais.getSomatorioValorParcelado());
				}
				else{
					quantidadeCancelado += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorEmAnalise = valorEmAnalise.add(retumoTotais.getSomatorioValorCancelado());
				}
				// Calculo dos Percentuais
				quantidadeEmAnalise = retumoTotais.getSomatorioQuantidadeInclusoes();
			}else if(retumoTotais.getIdSituacaoCobranca() != null && retumoTotais.getIdSituacaoCobranca().equals(5)){ // CORTADO
				// Valor
				
				if (idSituacaoDebito.equals(ConstantesSistema.PENDENTE)){
					quantidadePendente += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorCortado = valorCortado.add(retumoTotais.getSomatorioValorPendente());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PAGO)){
					quantidadePago += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorCortado = valorCortado.add(retumoTotais.getSomatorioValorPago());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PARCELADO)){
					quantidadeParcelado += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorCortado = valorCortado.add(retumoTotais.getSomatorioValorParcelado());
				}
				else{
					quantidadeCancelado += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorCortado = valorCortado.add(retumoTotais.getSomatorioValorCancelado());
				}
				// Calculo dos Percentuais
				quantidadeCortado = retumoTotais.getSomatorioQuantidadeInclusoes();
			}else if(retumoTotais.getIdSituacaoCobranca() != null && retumoTotais.getIdSituacaoCobranca().equals(6)){ // SUPRIMIDO
				// Valor
				
				if (idSituacaoDebito.equals(ConstantesSistema.PENDENTE)){
					quantidadePendente += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorSuprimido = valorSuprimido.add(retumoTotais.getSomatorioValorPendente());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PAGO)){
					quantidadePago += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorSuprimido = valorSuprimido.add(retumoTotais.getSomatorioValorPago());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PARCELADO)){
					quantidadeParcelado += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorSuprimido = valorSuprimido.add(retumoTotais.getSomatorioValorParcelado());
				}
				else{
					quantidadeCancelado += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorSuprimido = valorSuprimido.add(retumoTotais.getSomatorioValorCancelado());
				}
				
				// Calculo dos Percentuais
				quantidadeSuprimido = retumoTotais.getSomatorioQuantidadeInclusoes();
			}else if(retumoTotais.getIdSituacaoCobranca() != null && retumoTotais.getIdSituacaoCobranca().equals(7)){ // SUPRIMIDO PARC
				// Valor
				
				if (idSituacaoDebito.equals(ConstantesSistema.PENDENTE)){
					quantidadePendente += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorSuprimidoParc = valorSuprimidoParc.add(retumoTotais.getSomatorioValorPendente());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PAGO)){
					quantidadePago += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorSuprimidoParc = valorSuprimidoParc.add(retumoTotais.getSomatorioValorPago());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PARCELADO)){
					quantidadeParcelado += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorSuprimidoParc = valorSuprimidoParc.add(retumoTotais.getSomatorioValorParcelado());
				}
				else{
					quantidadeCancelado += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorSuprimidoParc = valorSuprimidoParc.add(retumoTotais.getSomatorioValorCancelado());
				}
				
				// Calculo dos Percentuais
				quantidadeSuprimidoParc = retumoTotais.getSomatorioQuantidadeInclusoes();
			}else if(retumoTotais.getIdSituacaoCobranca() != null && retumoTotais.getIdSituacaoCobranca().equals(8)){ // SUPRIMIDO PARC A PEDIDO
				// Valor
				
				if (idSituacaoDebito.equals(ConstantesSistema.PENDENTE)){
					quantidadePendente += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorSuprimidoParcPedido = valorSuprimidoParcPedido.add(retumoTotais.getSomatorioValorPendente());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PAGO)){
					quantidadePago += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorSuprimidoParcPedido = valorSuprimidoParcPedido.add(retumoTotais.getSomatorioValorPago());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PARCELADO)){
					quantidadeParcelado += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorSuprimidoParcPedido = valorSuprimidoParcPedido.add(retumoTotais.getSomatorioValorParcelado());
				}
				else{
					quantidadeCancelado += retumoTotais.getSomatorioQuantidadeInclusoes();
					valorSuprimidoParcPedido = valorSuprimidoParcPedido.add(retumoTotais.getSomatorioValorCancelado());
				}
				
				// Calculo dos Percentuais
				quantidadeSuprimidoParcPedido = retumoTotais.getSomatorioQuantidadeInclusoes();
			}
		}

		
		Integer somatorioTotalQuantidade = 0;
		BigDecimal somatorioValor = new BigDecimal(0);
		
		//totais quantidades
		somatorioTotalQuantidade += quantidadePotencial;
		somatorioTotalQuantidade += quantidadeFactivel;
		somatorioTotalQuantidade += quantidadeLigado;
		somatorioTotalQuantidade += quantidadeEmAnalise;
		somatorioTotalQuantidade += quantidadeSuprimido;
		somatorioTotalQuantidade += quantidadeCortado;
		somatorioTotalQuantidade += quantidadeSuprimidoParc;
		somatorioTotalQuantidade += quantidadeSuprimidoParcPedido;
		
		
		// Montagem Valores Detalhe PopUp 
		////////////////  ***  VALOR PENDENTE  ***  //////////////// 
		Collection<NegativacaoHelper>colecaoResumoNegativacaoDetalhePopUp = new ArrayList();
		NegativacaoHelper retumoTotaisDetalhePopUp = new NegativacaoHelper();
		retumoTotaisDetalhePopUp.setDescricao("PENDENTE");
		BigDecimal valorPendente = new BigDecimal(0);
		if(httpServletRequest.getParameter("valorPendente") != null){
			valorPendente = new BigDecimal(httpServletRequest.getParameter("valorPendente"));
		}
		// Valor
		retumoTotaisDetalhePopUp.setValorDinamico(valorPendente);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadePendente);
		// Percentual
		retumoTotaisDetalhePopUp.setPercentualValor(new BigDecimal((valorPendente.doubleValue() * 100)/somatorioValorDebito.doubleValue()));	
		retumoTotaisDetalhePopUp.setPercentualQtd(new BigDecimal((quantidadePendente.doubleValue() * 100)/somatorioTotalQuantidade.doubleValue()));
		colecaoResumoNegativacaoDetalhePopUp.add(retumoTotaisDetalhePopUp);

		////////////////***  VALOR PAGO  ***  ////////////////			
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("PAGO");
		BigDecimal valorPago = new BigDecimal(0);		
		if(httpServletRequest.getParameter("valorPago") != null){
			valorPago = new BigDecimal(httpServletRequest.getParameter("valorPago"));
		}
		// Valor
		retumoTotaisDetalhePopUp.setValorDinamico(valorPago);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadePago);
		// Percentual
		retumoTotaisDetalhePopUp.setPercentualQtd(new BigDecimal((quantidadePago.doubleValue() * 100)/somatorioTotalQuantidade.doubleValue()));
		retumoTotaisDetalhePopUp.setPercentualValor(new BigDecimal((valorPago.doubleValue() * 100)/somatorioValorDebito.doubleValue()));		
		colecaoResumoNegativacaoDetalhePopUp.add(retumoTotaisDetalhePopUp);		

		////////////////***  VALOR PARCELADO  ***  ////////////////			
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("PARCELADO");
		BigDecimal valorParcelado = new BigDecimal(0);		
		if(httpServletRequest.getParameter("valorParcelado") != null){
			valorParcelado = new BigDecimal(httpServletRequest.getParameter("valorParcelado"));
		}
		// Valor
		retumoTotaisDetalhePopUp.setValorDinamico(valorParcelado);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadeParcelado);
		// Percentual
		retumoTotaisDetalhePopUp.setPercentualQtd(new BigDecimal((quantidadeParcelado.doubleValue() * 100)/somatorioTotalQuantidade.doubleValue()));
		retumoTotaisDetalhePopUp.setPercentualValor(new BigDecimal((valorParcelado.doubleValue() * 100)/somatorioValorDebito.doubleValue()));		
		colecaoResumoNegativacaoDetalhePopUp.add(retumoTotaisDetalhePopUp);		
		
		////////////////***  VALOR CANCELADO  ***  ////////////////			
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("CANCELADO");
		BigDecimal valorCancelado = new BigDecimal(0);		
		if(httpServletRequest.getParameter("valorCancelado") != null){
			valorCancelado = new BigDecimal(httpServletRequest.getParameter("valorCancelado"));
		}
		// Valor
		retumoTotaisDetalhePopUp.setValorDinamico(valorCancelado);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadeCancelado);
		// Percentual
		retumoTotaisDetalhePopUp.setPercentualQtd(new BigDecimal((quantidadeCancelado.doubleValue() * 100)/somatorioTotalQuantidade.doubleValue()));
		retumoTotaisDetalhePopUp.setPercentualValor(new BigDecimal((valorCancelado.doubleValue() * 100)/somatorioValorDebito.doubleValue()));		
		colecaoResumoNegativacaoDetalhePopUp.add(retumoTotaisDetalhePopUp);		
		
        sessao.setAttribute("colecaoResumoNegativacaoDetalhePopUp", colecaoResumoNegativacaoDetalhePopUp);	
        // FIM Montagem Valores Detalhe PopUp 
        ////////////////  ***  VALOR PENDENTE  ***  //////////////// 
		
		// Potencial
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("POTENCIAL");
		retumoTotaisDetalhePopUp.setValorDinamico(valorPotencial);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadePotencial);
		retumoTotaisDetalhePopUp.setPercentualQtd(new BigDecimal((quantidadePotencial.doubleValue() * 100)/somatorioTotalQuantidade.doubleValue()));
		retumoTotaisDetalhePopUp.setPercentualValor(new BigDecimal((valorPotencial.doubleValue() * 100)/somatorioValorDebito.doubleValue()));
		colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp.add(retumoTotaisDetalhePopUp);
		somatorioValor.add(valorPotencial);
		
		// Factivel
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("FACTIVEL");
		retumoTotaisDetalhePopUp.setValorDinamico(valorFactivel);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadeFactivel);
		retumoTotaisDetalhePopUp.setPercentualQtd(new BigDecimal((quantidadeFactivel.doubleValue() * 100)/somatorioTotalQuantidade.doubleValue()));
		retumoTotaisDetalhePopUp.setPercentualValor(new BigDecimal((valorFactivel.doubleValue() * 100)/somatorioValorDebito.doubleValue()));
		colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp.add(retumoTotaisDetalhePopUp);
		somatorioValor.add(valorFactivel);
		
		// Ligado
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("LIGADO");
		retumoTotaisDetalhePopUp.setValorDinamico(valorLigado);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadeLigado);
		retumoTotaisDetalhePopUp.setPercentualQtd(new BigDecimal((quantidadeLigado.doubleValue() * 100)/somatorioTotalQuantidade.doubleValue()));
		retumoTotaisDetalhePopUp.setPercentualValor(new BigDecimal((valorLigado.doubleValue() * 100)/somatorioValorDebito.doubleValue()));
		colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp.add(retumoTotaisDetalhePopUp);
		somatorioValor.add(valorLigado);

		// Em Analise
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("EM ANALISE");
		retumoTotaisDetalhePopUp.setValorDinamico(valorEmAnalise);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadeEmAnalise);
		retumoTotaisDetalhePopUp.setPercentualQtd(new BigDecimal((quantidadeEmAnalise.doubleValue() * 100)/somatorioTotalQuantidade.doubleValue()));
		retumoTotaisDetalhePopUp.setPercentualValor(new BigDecimal((valorEmAnalise.doubleValue() * 100)/somatorioValorDebito.doubleValue()));
		colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp.add(retumoTotaisDetalhePopUp);
		somatorioValor.add(valorEmAnalise);
		
		// Cortado
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("CORTADO");
		retumoTotaisDetalhePopUp.setValorDinamico(valorCortado);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadeCortado);
		retumoTotaisDetalhePopUp.setPercentualQtd(new BigDecimal((quantidadeCortado.doubleValue() * 100)/somatorioTotalQuantidade.doubleValue()));
		retumoTotaisDetalhePopUp.setPercentualValor(new BigDecimal((valorCortado.doubleValue() * 100)/somatorioValorDebito.doubleValue()));
		colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp.add(retumoTotaisDetalhePopUp);
		somatorioValor.add(valorCortado);
		
		// Suprimido
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("SUPRIMIDO");
		retumoTotaisDetalhePopUp.setValorDinamico(valorSuprimido);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadeSuprimido);
		retumoTotaisDetalhePopUp.setPercentualQtd(new BigDecimal((quantidadeSuprimido.doubleValue() * 100)/somatorioTotalQuantidade.doubleValue()));
		retumoTotaisDetalhePopUp.setPercentualValor(new BigDecimal((valorSuprimido.doubleValue() * 100)/somatorioValorDebito.doubleValue()));
		colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp.add(retumoTotaisDetalhePopUp);
		somatorioValor.add(valorSuprimido);

		// Suprimido PARC
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("SUPRIMIDO PARC");
		retumoTotaisDetalhePopUp.setValorDinamico(valorSuprimidoParc);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadeSuprimidoParc);
		retumoTotaisDetalhePopUp.setPercentualQtd(new BigDecimal((quantidadeSuprimidoParc.doubleValue() * 100)/somatorioTotalQuantidade.doubleValue()));
		retumoTotaisDetalhePopUp.setPercentualValor(new BigDecimal((valorSuprimidoParc.doubleValue() * 100)/somatorioValorDebito.doubleValue()));
		colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp.add(retumoTotaisDetalhePopUp);
		somatorioValor.add(valorSuprimidoParc);
		
		// Suprimido PARC A PEDIDO
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("SUPRIMIDO PARC A PEDIDO");
		retumoTotaisDetalhePopUp.setValorDinamico(valorSuprimidoParcPedido);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadeSuprimidoParcPedido);
		retumoTotaisDetalhePopUp.setPercentualQtd(new BigDecimal((quantidadeSuprimidoParcPedido.doubleValue() * 100)/somatorioTotalQuantidade.doubleValue()));
		retumoTotaisDetalhePopUp.setPercentualValor(new BigDecimal((valorSuprimidoParcPedido.doubleValue() * 100)/somatorioValorDebito.doubleValue()));
		colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp.add(retumoTotaisDetalhePopUp);
		somatorioValor.add(valorSuprimidoParcPedido);
		
		sessao.setAttribute("colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp", colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp);
		sessao.setAttribute("somatorioValor", somatorioValor);
		sessao.setAttribute("somatorioTotalQuantidade", somatorioTotalQuantidade);
		
		return retorno;
	}
}

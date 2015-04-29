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
package gsan.gui.cobranca;

import gsan.cobranca.bean.ContaValoresHelper;
import gsan.cobranca.bean.GuiaPagamentoValoresHelper;
import gsan.cobranca.bean.IndicadoresParcelamentoHelper;
import gsan.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gsan.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gsan.cobranca.bean.ObterOpcoesDeParcelamentoHelper;
import gsan.cobranca.parcelamento.Parcelamento;
import gsan.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gsan.cobranca.parcelamento.ParcelamentoPerfil;
import gsan.fachada.Fachada;
import gsan.faturamento.credito.CreditoARealizar;
import gsan.faturamento.debito.DebitoACobrar;
import gsan.faturamento.debito.DebitoTipo;
import gsan.financeiro.FinanciamentoTipo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.Util;

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
import org.apache.struts.action.DynaActionForm;

/**
 * Permite efetuar o parcelamento dos d�bitos de um im�vel
 * 
 * UC0214] - Efetuar Parcelamento de D�bitos
 * 
 * Pr�-processamento da segunda p�gina
 * 
 * @author Roberta Costa
 * @date 11/02/2006
 */
public class ExibirEfetuarParcelamentoDebitosProcesso2Action extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("processo2");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		DynaActionForm efetuarParcelamentoDebitosActionForm = (DynaActionForm) actionForm;
		
		// Pega dados do formul�rio
		String codigoImovel = (String)(efetuarParcelamentoDebitosActionForm.get("matriculaImovel"));
		String dataParcelamento = (String)(efetuarParcelamentoDebitosActionForm.get("dataParcelamento"));
		String resolucaoDiretoria = (String)(efetuarParcelamentoDebitosActionForm.get("resolucaoDiretoria"));
		String fimIntervaloParcelamento = (String)efetuarParcelamentoDebitosActionForm.get("fimIntervaloParcelamento");
		String inicioIntervaloParcelamento = (String)efetuarParcelamentoDebitosActionForm.get("inicioIntervaloParcelamento");
		String indicadorContasRevisao = (String) efetuarParcelamentoDebitosActionForm.get("indicadorContasRevisao");
		String indicadorGuiasPagamento = (String) efetuarParcelamentoDebitosActionForm.get("indicadorGuiasPagamento");
		String indicadorAcrescimosImpotualidade = (String) efetuarParcelamentoDebitosActionForm.get("indicadorAcrescimosImpotualidade");
		String indicadorDebitosACobrar = (String) efetuarParcelamentoDebitosActionForm.get("indicadorDebitosACobrar");
		String indicadorCreditoARealizar = (String) efetuarParcelamentoDebitosActionForm.get("indicadorCreditoARealizar");
		String indicadorDividaAtiva = (String) efetuarParcelamentoDebitosActionForm.get("indicadorDividaAtiva");
		
		Boolean indicadorContas = true;
		//se o intervalo de parcelamento estiver igual a null
		//n�o se deve levar em considera��o no parcelamento a cole�o de contas 
		if ((inicioIntervaloParcelamento == null || inicioIntervaloParcelamento.equals(""))
				&& (fimIntervaloParcelamento == null || fimIntervaloParcelamento.equals(""))){
			indicadorContas = false;
		}
		
		// Atualiza os valores da primeira aba na se��o caso seja confirmado a altera��o
		// de alguma informa��o do primeiro formul�rio
		if( httpServletRequest.getParameter("confirmado") != null && httpServletRequest.getParameter("confirmado").equals("ok") ){
	        sessao.setAttribute("codigoImovelEscolhida", codigoImovel);
			sessao.setAttribute("dataParcelamentoEscolhida", dataParcelamento);
			sessao.setAttribute("resolucaoDiretoriaEscolhida", resolucaoDiretoria);
			sessao.setAttribute("fimIntervaloParcelamentoEscolhida", fimIntervaloParcelamento);
			sessao.setAttribute("inicioIntervaloParcelamentoEscolhida", inicioIntervaloParcelamento); 
			sessao.setAttribute("indicadorContasRevisaoEscolhida", indicadorContasRevisao);
			sessao.setAttribute("indicadorGuiasPagamentoEscolhida", indicadorGuiasPagamento);
			sessao.setAttribute("indicadorAcrescimosImpotualidadeEscolhida", indicadorAcrescimosImpotualidade);
			sessao.setAttribute("indicadorDebitosACobrarEscolhida", indicadorDebitosACobrar); 
			sessao.setAttribute("indicadorCreditoARealizarEscolhida", indicadorCreditoARealizar);
			sessao.setAttribute("indicadorDividaAtivaEscolhida", indicadorDividaAtiva);

	        // Limpa a sess�o
	        sessao.removeAttribute("calcula");
	        sessao.removeAttribute("colecaoContaValores");
	        sessao.removeAttribute("colecaoGuiaPagamentoValores");
	        sessao.removeAttribute("opcoesParcelamento");
	        sessao.removeAttribute("colecaoOpcoesParcelamento");
	        sessao.removeAttribute("valorDebitoTotalAtualizado");
	        sessao.removeAttribute("valorTotalContaValores");
	        sessao.removeAttribute("valorAcrescimosImpontualidade");
	        sessao.removeAttribute("idsContaEP");
	        sessao.removeAttribute("colecaoCreditoARealizar");
	        
	        sessao.removeAttribute("colecaoContasEmAntiguidade");
	        
			sessao.removeAttribute("valorAcrescimosNB");
			sessao.removeAttribute("colecaoContaValoresSemContasNB");
        }
		
		// Verifica de onde est� vindo a chamada  para fazer o c�lculo das contas EP e NB
		// Se � do bot�o do Calcular ou do Avan�ar ou Aba
		String calcula = null;
		String verificaCalcula = null;
		if (httpServletRequest.getParameter("calcula") != null && !httpServletRequest.getParameter("calcula").equals("")) {
			calcula = httpServletRequest.getParameter("calcula");
			verificaCalcula = "request";
		}else if (sessao.getAttribute("calcula")!= null && !sessao.getAttribute("calcula").equals("")){
			if(httpServletRequest.getParameter("limpaCombo")== null 
				|| httpServletRequest.getParameter("limpaCombo").equals("")){
				 calcula = (String) sessao.getAttribute("calcula");
				 verificaCalcula = "session";
			}
		}

		if( calcula == null){
			if (codigoImovel != null && !codigoImovel.trim().equals("")) {
				String confirmado = httpServletRequest.getParameter("confirmado");
	
				if (confirmado != null && confirmado.trim().equalsIgnoreCase("ok")) {
			        // Limpa o formul�rio
			        efetuarParcelamentoDebitosActionForm.reset(actionMapping,httpServletRequest);
			        
			        // Limpa os bot�es de r�dio da EP e NB da lista de contas
			        if( sessao.getAttribute("colecaoContaValores") != null
			        		&& !sessao.getAttribute("colecaoContaValores").equals("")){
				        Collection colecaoContas = (Collection)sessao.getAttribute("colecaoContaValores");
						Iterator colecaoContasIterator = colecaoContas.iterator();
						colecaoContasIterator = colecaoContas.iterator();
						while(colecaoContasIterator.hasNext()){
							ContaValoresHelper contaValoresHelper = (ContaValoresHelper)colecaoContasIterator.next();
							contaValoresHelper.setIndicadorContasDebito(null);
						}
			        }
			        
					// Limpa a op��o de parcelamento
			        if( sessao.getAttribute("colecaoContaValoresNegociacao") != null
			        		&& !sessao.getAttribute("colecaoContaValoresNegociacao").equals("")){
				        Collection colecaoContaValoresNegociacao = (Collection)sessao.getAttribute("colecaoContaValoresNegociacao");
						Iterator contaValores = colecaoContaValoresNegociacao.iterator();
						while(contaValores.hasNext()) {
							ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();
							if( contaValoresHelper.getIndicadorContasDebito() != null
									&& contaValoresHelper.getIndicadorContasDebito().equals(1) ){
								contaValoresHelper.setIndicadorContasDebito(null);
							}
						}
			        }	
	
			        // Limpando a sess�o
					sessao.removeAttribute("colecaoGuiaPagamentoValores");
			        sessao.removeAttribute("colecaoDebitoACobrar");
			        sessao.removeAttribute("colecaoCreditoARealizar");
			        sessao.removeAttribute("calcula");
			        sessao.removeAttribute("colecaoContaValores");
			        sessao.removeAttribute("colecaoOpcoesParcelamento");
			        
			        sessao.removeAttribute("colecaoContasEmAntiguidade");
					sessao.removeAttribute("valorAcrescimosNB");
					sessao.removeAttribute("colecaoContaValoresSemContasNB");
			        
				}
				
				//Caso o periodo inicial do intervalo do parcelamento n�o seja informado
				if (inicioIntervaloParcelamento == null || inicioIntervaloParcelamento.equals("")){
					inicioIntervaloParcelamento = "01/0001";
				}
				
				// Caso o periodo final do intervalo do parcelamento n�o seja informado
				if( fimIntervaloParcelamento == null || fimIntervaloParcelamento.equals("")){
					fimIntervaloParcelamento = "12/9999";
				}
				
				// Obter todo o d�bito do im�vel para exibi��o
				ObterDebitoImovelOuClienteHelper colecaoDebitoCliente = fachada
						.obterDebitoImovelOuCliente(
								1, // Indicador de d�bito do im�vel
								codigoImovel, // Matr�cula do im�vel
								null, // C�digo do cliente
								null, // Tipo de rela��o cliente im�vel
								Util.formatarMesAnoParaAnoMesSemBarra(inicioIntervaloParcelamento), // Refer�ncia inicial do d�bito
								Util.formatarMesAnoParaAnoMesSemBarra(fimIntervaloParcelamento), // Fim do d�bito
								Util.converteStringParaDate("01/01/0001"), // Inicio vencimento
								Util.converteStringParaDate("31/12/9999"), // Fim vencimento
								1, // Indicador de pagamento
								new Integer(indicadorContasRevisao), //  conta em revis�o
								new Integer(indicadorDebitosACobrar), // D�bito a cobrar
								new Integer(indicadorCreditoARealizar), // cr�dito a realizar
								1, // Indicador de notas promiss�rias
								new Integer(indicadorGuiasPagamento), //guias pagamento
								new Integer(indicadorAcrescimosImpotualidade), // acr�scimos impontualidade
								indicadorContas, new Integer(indicadorDividaAtiva));
				// Para o c�lculo do D�bito Total Atualizado
				BigDecimal valorTotalContas = new BigDecimal("0.00");
				BigDecimal valorTotalAcrescimoImpontualidade = new BigDecimal("0.00");
				BigDecimal valorTotalRestanteServicosACobrar = new BigDecimal("0.00");
				BigDecimal valorTotalRestanteServicosACobrarCurtoPrazo = new BigDecimal("0.00");
				BigDecimal valorTotalRestanteServicosACobrarLongoPrazo = new BigDecimal("0.00");
				BigDecimal valorTotalRestanteParcelamentosACobrar = new BigDecimal("0.00");
				BigDecimal valorTotalRestanteParcelamentosACobrarCurtoPrazo = new BigDecimal("0.00");
				BigDecimal valorTotalRestanteParcelamentosACobrarLongoPrazo = new BigDecimal("0.00");
				BigDecimal valorTotalGuiasPagamento = new BigDecimal("0.00");
				BigDecimal valorTotalAcrescimoImpontualidadeContas = new BigDecimal("0.00");
				BigDecimal valorTotalAcrescimoImpontualidadeGuias = new BigDecimal("0.00");
				BigDecimal valorCreditoARealizar = new BigDecimal("0.00");
				BigDecimal valorRestanteACobrar = new BigDecimal("0.00");
				BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
				BigDecimal valorJurosMora = new BigDecimal("0.00");
				BigDecimal valorMulta = new BigDecimal("0.00");
				
				// Dados do D�bito do Im�vel - Contas
				Collection<ContaValoresHelper> colecaoContaValores = colecaoDebitoCliente.getColecaoContasValores();
				
				if(!Util.isVazioOrNulo(colecaoContaValores)){
					colecaoContaValores = fachada.validarContasIndicadorBloqueioMotivoRevisao(colecaoContaValores);
				}
				
				ParcelamentoPerfil parcelamentoPerfil = (ParcelamentoPerfil)sessao.getAttribute("parcelamentoPerfil");
				//[SB0011] Verificar �nica Fatura
				fachada.verificarUnicaFatura(colecaoContaValores,parcelamentoPerfil);
				
				if (colecaoContaValores != null && !colecaoContaValores.isEmpty()) {
					
					int quantidadeMinimaMesesAntiguidade = 0;
					int maiorQuantidadeMinimaMesesAntiguidade = 0;
					Iterator contaValores = colecaoContaValores.iterator();
					
					while (contaValores.hasNext()) {
						
						ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();
						
						//Colocado por Raphael Rossiter em 04/12/2008
						//=============================================================================================
						Collection<ParcelamentoDescontoAntiguidade> colecaoParcelamentoDescontoAntiguidade = fachada.
						obterParcelamentoDescontoAntiguidadeParaConta(parcelamentoPerfil,contaValoresHelper.getConta());

						ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidadeMaior = new ParcelamentoDescontoAntiguidade();

						// Caso nenhuma ocorr�ncia tenha sido selecionada passar para a pr�xima conta
						if (colecaoParcelamentoDescontoAntiguidade != null && 
							!colecaoParcelamentoDescontoAntiguidade.isEmpty()) {
							
							Iterator parcelamentoDescontoAntiguidadeValores = colecaoParcelamentoDescontoAntiguidade.iterator();

							quantidadeMinimaMesesAntiguidade = 0;
							maiorQuantidadeMinimaMesesAntiguidade = 0;

							// 2.4 Determina o percentual de desconto por antiguidade do d�bito
							while (parcelamentoDescontoAntiguidadeValores.hasNext()) {
								ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidade = (ParcelamentoDescontoAntiguidade) parcelamentoDescontoAntiguidadeValores.next();
								quantidadeMinimaMesesAntiguidade = parcelamentoDescontoAntiguidade.getQuantidadeMinimaMesesDebito();
								if (quantidadeMinimaMesesAntiguidade > maiorQuantidadeMinimaMesesAntiguidade) {
									maiorQuantidadeMinimaMesesAntiguidade = quantidadeMinimaMesesAntiguidade;
									parcelamentoDescontoAntiguidadeMaior = parcelamentoDescontoAntiguidade;
								}
							}
							
							/*
							 * Colocado por Raphael Rossiter em 03/12/2008
							 * As contas onde o perfil de parcelamento para desconto de antiguidade estiver com
							 * o motivo de revis�o informado N�O entrar�o no parcelamento.
							 */
							valorTotalContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalContas = valorTotalContas.add(contaValoresHelper.getValorTotalConta());
							
							if (contaValoresHelper.getValorAtualizacaoMonetaria() != null && !contaValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
								valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(contaValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
							}
							if (contaValoresHelper.getValorJurosMora() != null	&& !contaValoresHelper.getValorJurosMora().equals("")) {
								valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorJurosMora = valorJurosMora.add(contaValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
							}
							if (contaValoresHelper.getValorMulta() != null && !contaValoresHelper.getValorMulta().equals("")) {
								valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorMulta = valorMulta.add(contaValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
							}

							// Para c�lculo do Acrescimo de Impontualidade
							valorTotalAcrescimoImpontualidadeContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalAcrescimoImpontualidadeContas = valorTotalAcrescimoImpontualidadeContas.add(contaValoresHelper.getValorTotalContaValoresParcelamento());
							
							if (parcelamentoDescontoAntiguidadeMaior.getContaMotivoRevisao() != null){
								
								//CONTA ENTRAR� EM REVIS�O
								contaValoresHelper.setRevisao(1);
							}
						}
						else{
							
							valorTotalContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalContas = valorTotalContas.add(contaValoresHelper.getValorTotalConta());

							if (contaValoresHelper.getValorAtualizacaoMonetaria() != null && !contaValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
								valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(contaValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
							}
							if (contaValoresHelper.getValorJurosMora() != null	&& !contaValoresHelper.getValorJurosMora().equals("")) {
								valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorJurosMora = valorJurosMora.add(contaValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
							}
							if (contaValoresHelper.getValorMulta() != null && !contaValoresHelper.getValorMulta().equals("")) {
								valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorMulta = valorMulta.add(contaValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
							}

							// Para c�lculo do Acrescimo de Impontualidade
							valorTotalAcrescimoImpontualidadeContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalAcrescimoImpontualidadeContas = valorTotalAcrescimoImpontualidadeContas.add(contaValoresHelper.getValorTotalContaValoresParcelamento());
						}
						//=============================================================================================
					}
					
					efetuarParcelamentoDebitosActionForm.set("valorTotalContaValores", Util.formatarMoedaReal(valorTotalContas));
	
					sessao.setAttribute("valorTotalContaValores", valorTotalContas);
					
					// Pega os dados do D�bito do Cliente
					/*if( sessao.getAttribute("colecaoContaValores") == null ){
						sessao.setAttribute("colecaoContaValores",colecaoContaValores);
					}*/
												
					sessao.setAttribute("colecaoContaValores", colecaoContaValores);
				} 
				else {
					
					efetuarParcelamentoDebitosActionForm.set("valorTotalContaValores", "0,00");
					
					sessao.setAttribute("valorTotalContaValores", valorTotalContas);
				}
	
	
				// Guias de Pagamento
				if( indicadorGuiasPagamento.equals("1") ){
					Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = colecaoDebitoCliente.getColecaoGuiasPagamentoValores();
					if (colecaoGuiaPagamentoValores != null && !colecaoGuiaPagamentoValores.isEmpty() ){
						Iterator guiaPagamentoValores = colecaoGuiaPagamentoValores.iterator();
						while (guiaPagamentoValores.hasNext()) {
							GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) guiaPagamentoValores.next();
							valorTotalGuiasPagamento.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalGuiasPagamento = valorTotalGuiasPagamento
									.add(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
	
							if (guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria() != null && !guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
								valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
							}
							if (guiaPagamentoValoresHelper.getValorJurosMora() != null && !guiaPagamentoValoresHelper.getValorJurosMora().equals("")) {
								valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorJurosMora = valorJurosMora.add(guiaPagamentoValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
							}
							if (guiaPagamentoValoresHelper.getValorMulta() != null	&& !guiaPagamentoValoresHelper.getValorMulta().equals("")) {
								valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorMulta = valorMulta.add(guiaPagamentoValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
							}
							
							// Para c�lculo do Acrescimo de Impontualidade
							valorTotalAcrescimoImpontualidadeGuias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalAcrescimoImpontualidadeGuias = valorTotalAcrescimoImpontualidadeGuias
									.add(guiaPagamentoValoresHelper.getValorAcrescimosImpontualidade());
						}
						efetuarParcelamentoDebitosActionForm.set("valorGuiasPagamento",Util.formatarMoedaReal(valorTotalGuiasPagamento));
	
						// Pega as Guias de Pagamento em D�bito
						sessao.setAttribute("colecaoGuiaPagamentoValores",colecaoGuiaPagamentoValores);
					} else {
						efetuarParcelamentoDebitosActionForm.set("valorGuiasPagamento", "0,00");
					}
				}else{
					efetuarParcelamentoDebitosActionForm.set("valorGuiasPagamento", "0,00");
				}
							
				// Acrescimos por Impontualidade
				BigDecimal retornoSoma = new BigDecimal("0.00");
				if( indicadorAcrescimosImpotualidade.equals("1") ){
					retornoSoma.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeContas);
					retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeGuias);
	
					efetuarParcelamentoDebitosActionForm.set("valorAcrescimosImpontualidade", Util.formatarMoedaReal(retornoSoma));
					sessao.setAttribute("valorAcrescimosImpontualidade", retornoSoma);
					
				}else{
					efetuarParcelamentoDebitosActionForm.set("valorAcrescimosImpontualidade", "0,00");
					sessao.setAttribute("valorAcrescimosImpontualidade",new BigDecimal("0.00"));
					
				}
	
				efetuarParcelamentoDebitosActionForm.set("valorAtualizacaoMonetaria", Util.formatarMoedaReal(valorAtualizacaoMonetaria));
				efetuarParcelamentoDebitosActionForm.set("valorJurosMora", Util.formatarMoedaReal(valorJurosMora));
				efetuarParcelamentoDebitosActionForm.set("valorMulta", Util.formatarMoedaReal(valorMulta));
				
				// Para o c�lculo do D�bito Total Atualizado
				valorTotalAcrescimoImpontualidade = retornoSoma;
	
				// Debitos A Cobrar
				if( indicadorDebitosACobrar.equals("1") ){
					//[FS0022]-Verificar exist�ncia de juros sobre im�vel
					Collection colecaoDebitoACobrar = colecaoDebitoCliente.getColecaoDebitoACobrar();

					if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
						Iterator debitoACobrarValores = colecaoDebitoACobrar.iterator();
		
						final int indiceCurtoPrazo = 0;
						final int indiceLongoPrazo = 1;
		
						while (debitoACobrarValores.hasNext()) {
							DebitoACobrar debitoACobrar = (DebitoACobrar) debitoACobrarValores.next();
							
							//[FS0022]-Verificar exist�ncia de juros sobre im�vel
							if(debitoACobrar.getDebitoTipo().getId() != null && 
									!debitoACobrar.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){
								
								// Debitos A Cobrar - Servi�o
								if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.SERVICO_NORMAL)) {
									// [SB0001] Obter Valores de Curto e Longo Prazo
									valorRestanteACobrar = debitoACobrar.getValorTotalComBonus();
			
									BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorACobrarDeCurtoELongoPrazo(
										debitoACobrar.getNumeroPrestacaoDebito(),
										debitoACobrar.getNumeroPrestacaoCobradasMaisBonus(),
										valorRestanteACobrar);
									valorTotalRestanteServicosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
									valorTotalRestanteServicosACobrarCurtoPrazo = valorTotalRestanteServicosACobrarCurtoPrazo
											.add(valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
									
									valorTotalRestanteServicosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
									valorTotalRestanteServicosACobrarLongoPrazo = valorTotalRestanteServicosACobrarLongoPrazo
											.add(valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
								}
			
								// Debitos A Cobrar - Parcelamento
								if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_AGUA)
									|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_ESGOTO)
									|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_SERVICO)) {
									// [SB0001] Obter Valores de Curto e Longo Prazo
									valorRestanteACobrar = debitoACobrar.getValorTotalComBonus();
			
									BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorACobrarDeCurtoELongoPrazo(
											debitoACobrar.getNumeroPrestacaoDebito(),
											debitoACobrar.getNumeroPrestacaoCobradasMaisBonus(),
											valorRestanteACobrar);
									valorTotalRestanteParcelamentosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
									valorTotalRestanteParcelamentosACobrarCurtoPrazo = valorTotalRestanteParcelamentosACobrarCurtoPrazo
											.add(valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
									valorTotalRestanteParcelamentosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
									valorTotalRestanteParcelamentosACobrarLongoPrazo = valorTotalRestanteParcelamentosACobrarLongoPrazo
											.add(valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
								}
							}
						}
						sessao.setAttribute("colecaoDebitoACobrar",	colecaoDebitoACobrar);
		
						// Servi�os
						valorTotalRestanteServicosACobrar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalRestanteServicosACobrar = valorTotalRestanteServicosACobrarCurtoPrazo
							.add(valorTotalRestanteServicosACobrarLongoPrazo);
						efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServico", 
								Util.formatarMoedaReal(valorTotalRestanteServicosACobrar));
						// Parcelamentos
						valorTotalRestanteParcelamentosACobrar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalRestanteParcelamentosACobrar = valorTotalRestanteParcelamentosACobrarCurtoPrazo
							.add(valorTotalRestanteParcelamentosACobrarLongoPrazo);
						efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamento", 
								Util.formatarMoedaReal(valorTotalRestanteParcelamentosACobrar));
					}else{
						efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServico", "0,00");
						efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamento", "0,00");
						
						// Alterado por Rafael Corr�a
						// Data: 26/08/2009
						efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServicoLongoPrazo", "0,00");
						efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServicoCurtoPrazo", "0,00");
						efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamentoLongoPrazo", "0,00");
						efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamentoCurtoPrazo", "0,00");
					}
				}else{
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServico", "0,00");
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamento", "0,00");
					
					// Alterado por Rafael Corr�a
					// Data: 26/08/2009
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServicoLongoPrazo", "0,00");
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServicoCurtoPrazo", "0,00");
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamentoLongoPrazo", "0,00");
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamentoCurtoPrazo", "0,00");
				}
				
				// Cr�dito A Realizar
				if( indicadorCreditoARealizar.equals("1") ){
					Collection<CreditoARealizar> colecaoCreditoARealizar = colecaoDebitoCliente.getColecaoCreditoARealizar();
					if (colecaoCreditoARealizar != null	&& !colecaoCreditoARealizar.isEmpty() ) {
						Iterator creditoARealizarValores = colecaoCreditoARealizar.iterator();
						while (creditoARealizarValores.hasNext()) {
							CreditoARealizar creditoARealizar = (CreditoARealizar) creditoARealizarValores.next();
							valorCreditoARealizar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorCreditoARealizar = valorCreditoARealizar.add(creditoARealizar.getValorTotalComBonus());
						}
						sessao.setAttribute("colecaoCreditoARealizar",colecaoCreditoARealizar);
						efetuarParcelamentoDebitosActionForm.set("valorCreditoARealizar", Util.formatarMoedaReal(valorCreditoARealizar));
					}else{
						efetuarParcelamentoDebitosActionForm.set("valorCreditoARealizar", "0,00");
					}
				}else{
					efetuarParcelamentoDebitosActionForm.set("valorCreditoARealizar", "0,00");
				}	
				
				// D�bito Total Atualizado
				BigDecimal debitoTotalAtualizado = new BigDecimal("0.00");
				
				debitoTotalAtualizado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				
				debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalContas);
				
				debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalGuiasPagamento);
				
				debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalAcrescimoImpontualidade);
				
				debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalRestanteServicosACobrar);
				
				debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalRestanteParcelamentosACobrar);
				
				debitoTotalAtualizado = debitoTotalAtualizado.subtract(valorCreditoARealizar);
	
				if( debitoTotalAtualizado.compareTo(new BigDecimal("0.00")) == -1
					|| debitoTotalAtualizado.equals(new BigDecimal("0.00")) ){
					
					Integer countContasRevisaoIndBloqueio = fachada.obterContasEmRevisaoComIndicadorBloqueioConta(Util.converterStringParaInteger(codigoImovel));
					if(countContasRevisaoIndBloqueio != null && countContasRevisaoIndBloqueio > 0){
						throw new ActionServletException("atencao.conta_motivo_revisao_indicador_bloqueio_impedir_parcelamento");
					}else{
						throw new ActionServletException("atencao.nao.existe.debito.a.parcelar");
					}
				}
	
				efetuarParcelamentoDebitosActionForm.set("valorDebitoTotalAtualizado", Util
						.formatarMoedaReal(debitoTotalAtualizado));
				
				sessao.setAttribute("valorDebitoTotalAtualizado", debitoTotalAtualizado);
	
				// Caso o valor Total do D�bito seja negativo n�o h� d�bitos para parcelar
				if( debitoTotalAtualizado.compareTo(new BigDecimal("0.00")) == -1 ){
					throw new ActionServletException("atencao.imovel.sem.debitos", null, codigoImovel);
				}
	
				// Limpa os bot�es de r�dio da EP e NB da lista de contas
				if(httpServletRequest.getParameter("limpaCombo")!= null && !httpServletRequest.getParameter("limpaCombo").equals("")){
					Collection colecaoContas = (Collection)sessao.getAttribute("colecaoContaValores");
					
					Iterator colecaoContasIterator = colecaoContas.iterator();
					colecaoContasIterator = colecaoContas.iterator();
					while(colecaoContasIterator.hasNext()){
						ContaValoresHelper contaValoresHelper = (ContaValoresHelper)colecaoContasIterator.next();
						contaValoresHelper.setIndicadorContasDebito(null);
					}
				}
			}
		}
		else if( calcula != null && calcula.equals("1") ){
			
			// Pega vari�veis da sess�o
			BigDecimal valorTotalContaValores = (BigDecimal) sessao.getAttribute("valorTotalContaValores");
			
			BigDecimal valorAcrescimosImpontualidade = (BigDecimal) sessao.getAttribute("valorAcrescimosImpontualidade");
			
			BigDecimal valorDebitoTotalAtualizado = (BigDecimal) sessao.getAttribute("valorDebitoTotalAtualizado");

			// Atribui 1 a calcula na sess�o
			sessao.setAttribute("calcula", "1");

			Collection<ContaValoresHelper> colecaoContaValores = (Collection<ContaValoresHelper>) 
					sessao.getAttribute("colecaoContaValores");
			
			if(!Util.isVazioOrNulo(colecaoContaValores)){
				colecaoContaValores = fachada.validarContasIndicadorBloqueioMotivoRevisao(colecaoContaValores);
			}

			BigDecimal valorContaNB = new BigDecimal("0.00");
			BigDecimal valorAcrescimosNB = new BigDecimal("0.00");
			Collection<ContaValoresHelper> colecaoContaValoresSemContasNB = new ArrayList<ContaValoresHelper>();
			
			if (colecaoContaValores != null && !colecaoContaValores.isEmpty()) {
				Iterator contaValores = colecaoContaValores.iterator();
				while (contaValores.hasNext()) {
					ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();
					if (verificaCalcula != null	&& verificaCalcula.equals("request")) {
						
						if (httpServletRequest.getParameter("indicadorContasDebito"
								+contaValoresHelper.getConta().getId().toString()) != null) {
							
							String indice = httpServletRequest.getParameter("indicadorContasDebito"
									+ contaValoresHelper.getConta().getId().toString());
							
							contaValoresHelper.setIndicadorContasDebito(new Integer(indice));
							
							// Caso as contas sejam n�o baixadas(NB)
							if (indice.equals("2")) {
								// Verifica se existe conta em revis�o
								if( contaValoresHelper.getConta().getDataRevisao() != null ){
									throw new ActionServletException("atencao.conta.em.revisao");
								}
								valorContaNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorContaNB = valorContaNB.add(contaValoresHelper.getConta().getValorTotal());
								if( indicadorAcrescimosImpotualidade.equals("1") ){
									valorAcrescimosNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
									valorAcrescimosNB = valorAcrescimosNB.add(contaValoresHelper.getValorTotalContaValoresParcelamento());
								}	
							}else{
								colecaoContaValoresSemContasNB.add(contaValoresHelper);
							}
						}else{
							colecaoContaValoresSemContasNB.add(contaValoresHelper);
						}
						
					} else {
						if (contaValoresHelper.getIndicadorContasDebito() != null) {
							if (contaValoresHelper.getIndicadorContasDebito().equals(2)) {
								valorContaNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorContaNB = valorContaNB.add(contaValoresHelper.getConta().getValorTotal());
								valorAcrescimosNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorAcrescimosNB = valorAcrescimosNB.add(contaValoresHelper.getValorTotalContaValoresParcelamento());
							}else{
								colecaoContaValoresSemContasNB.add(contaValoresHelper);
							}
						}else{
							colecaoContaValoresSemContasNB.add(contaValoresHelper);
						}
					}
				}
				valorTotalContaValores.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorTotalContaValores = valorTotalContaValores.subtract(valorContaNB);
				if( indicadorAcrescimosImpotualidade.equals("1") ){
					valorAcrescimosImpontualidade.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					valorAcrescimosImpontualidade = valorAcrescimosImpontualidade.subtract(valorAcrescimosNB);
				}	

				// Calcula sempre em cima do valor do debito
				valorDebitoTotalAtualizado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.subtract(valorContaNB);
				valorDebitoTotalAtualizado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.subtract(valorAcrescimosNB);
				
				efetuarParcelamentoDebitosActionForm.set("valorTotalContaValores",
						Util.formatarMoedaReal(valorTotalContaValores));
				
				efetuarParcelamentoDebitosActionForm.set("valorAcrescimosImpontualidade",
						Util.formatarMoedaReal(valorAcrescimosImpontualidade));
				
				if( valorDebitoTotalAtualizado.compareTo(new BigDecimal("0.00")) == -1
						|| valorDebitoTotalAtualizado.equals(new BigDecimal("0.00")) ){
					throw new ActionServletException("atencao.nao.existe.debito.a.parcelar");
				}
				efetuarParcelamentoDebitosActionForm.set("valorDebitoTotalAtualizado",
						Util.formatarMoedaReal(valorDebitoTotalAtualizado));
				
				sessao.setAttribute("valorAcrescimosNB",valorAcrescimosNB);
				sessao.setAttribute("colecaoContaValoresSemContasNB",colecaoContaValoresSemContasNB);
				
			}
		}
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		IndicadoresParcelamentoHelper indicadoresParcelamentoHelper = new IndicadoresParcelamentoHelper();
		indicadoresParcelamentoHelper.setIndicadorContasRevisao(new Integer(indicadorContasRevisao));
		indicadoresParcelamentoHelper.setIndicadorDebitosACobrar(new Integer(indicadorDebitosACobrar));
		indicadoresParcelamentoHelper.setIndicadorCreditoARealizar(new Integer(indicadorCreditoARealizar));
		indicadoresParcelamentoHelper.setIndicadorGuiasPagamento(new Integer(indicadorGuiasPagamento));
		indicadoresParcelamentoHelper.setIndicadorAcrescimosImpotualidade(new Integer(indicadorAcrescimosImpotualidade));
		indicadoresParcelamentoHelper.setIndicadorDividaAtiva(new Integer(indicadorDividaAtiva));
		
		calculoPagamenteAVista(fachada, sessao,	efetuarParcelamentoDebitosActionForm, codigoImovel,
		resolucaoDiretoria, fimIntervaloParcelamento,inicioIntervaloParcelamento, indicadoresParcelamentoHelper);
		
		return retorno;
	}

	//adicionado por Vivianne Sousa - 20/08/2012
	private void calculoPagamenteAVista(Fachada fachada, HttpSession sessao,
			DynaActionForm efetuarParcelamentoDebitosActionForm,
			String codigoImovel, String resolucaoDiretoria,
			String fimIntervaloParcelamento,
			String inicioIntervaloParcelamento,
			IndicadoresParcelamentoHelper indicadoresParcelamentoHelper) {
		
		Integer situacaoAguaId = new Integer( (String) efetuarParcelamentoDebitosActionForm.get("situacaoAguaId"));
		Integer situacaoEsgotoId = new Integer( (String) efetuarParcelamentoDebitosActionForm.get("situacaoEsgotoId"));
		Integer perfilImovel = new Integer( (String) efetuarParcelamentoDebitosActionForm.get("perfilImovel"));
		Integer numeroReparcelamentoConsecutivos = new Integer( (String) efetuarParcelamentoDebitosActionForm.get("numeroReparcelamentoConsecutivos"));
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Collection<ContaValoresHelper> colecaoContaValores = (Collection<ContaValoresHelper>)
				sessao.getAttribute("colecaoContaValores");
		
		Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = (Collection<GuiaPagamentoValoresHelper>)
				sessao.getAttribute("colecaoGuiaPagamentoValores");
		
		// O indicador s� ser� usado caso a situa��o de �gua do Im�vel seja
		// SUPRIMIDO, SUPRIMIDO PARCIAL, SUPRIMIDO PARCIAL A PEDIDO
		Integer indicadorRestabelecimento = new Integer("0");
		if (efetuarParcelamentoDebitosActionForm.get("indicadorRestabelecimento") != null
			&& !efetuarParcelamentoDebitosActionForm.get("indicadorRestabelecimento").equals("")) {
			indicadorRestabelecimento = new Integer(String.valueOf(efetuarParcelamentoDebitosActionForm.get("indicadorRestabelecimento")));
		}
		
		String bloqueiaIntervaloParcelamento = (String) sessao.getAttribute("bloqueiaIntervaloParcelamento");
		Integer fimIntervaloParcelamentoFormatado = null;
		Integer inicioIntervaloParcelamentoFormatado = null;
		if (bloqueiaIntervaloParcelamento != null && bloqueiaIntervaloParcelamento.equalsIgnoreCase("nao")){	
			fimIntervaloParcelamentoFormatado = Util.formatarMesAnoComBarraParaAnoMes(fimIntervaloParcelamento);
			inicioIntervaloParcelamentoFormatado = Util.formatarMesAnoComBarraParaAnoMes(inicioIntervaloParcelamento);
		}
		
		BigDecimal valorDebitoTotalAtualizado =	new BigDecimal("0.00");
		if (!((String) efetuarParcelamentoDebitosActionForm.get("valorDebitoTotalAtualizado")).equals("")){
			valorDebitoTotalAtualizado = Util.formatarMoedaRealparaBigDecimal((String)
					efetuarParcelamentoDebitosActionForm.get("valorDebitoTotalAtualizado"));
		}
		
		BigDecimal valorCreditoARealizar =	new BigDecimal("0.00");
		if (!((String) efetuarParcelamentoDebitosActionForm.get("valorCreditoARealizar")).equals("")){
			valorCreditoARealizar = Util.formatarMoedaRealparaBigDecimal((String)
					efetuarParcelamentoDebitosActionForm.get("valorCreditoARealizar"));
		}
		
		String valorDebitoACobrarParcelamentoImovel =( (String)efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarParcelamentoImovel"));
		BigDecimal valorDebitoACobrarParcelamentoImovelBigDecimal = new BigDecimal("0.00");
		if (valorDebitoACobrarParcelamentoImovel != null){
			valorDebitoACobrarParcelamentoImovelBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorDebitoACobrarParcelamentoImovel);
		}
		
		BigDecimal valorTotalMultas = new BigDecimal("0.00");
		BigDecimal valorTotalJurosMora = new BigDecimal("0.00");
		BigDecimal valorTotalAtualizacoesMonetarias = new BigDecimal("0.00");
		if (colecaoContaValores != null && !colecaoContaValores.isEmpty()) {
			Iterator contaValoresNegociacao = colecaoContaValores.iterator();
			while (contaValoresNegociacao.hasNext()) {
				ContaValoresHelper contaValoresHelperNegociacao = (ContaValoresHelper) contaValoresNegociacao.next();

				// Caso n�o tenha marcado a conta
				if (contaValoresHelperNegociacao.getIndicadorContasDebito() == null ){
					if (contaValoresHelperNegociacao.getValorMulta() != null) {
						valorTotalMultas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalMultas = valorTotalMultas.add(contaValoresHelperNegociacao.getValorMulta());
					}
					if (contaValoresHelperNegociacao.getValorJurosMora() != null) {
						valorTotalJurosMora.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalJurosMora = valorTotalJurosMora.add(contaValoresHelperNegociacao.getValorJurosMora());
					}
					if (contaValoresHelperNegociacao.getValorAtualizacaoMonetaria() != null) {
						valorTotalAtualizacoesMonetarias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalAtualizacoesMonetarias = valorTotalAtualizacoesMonetarias
								.add(contaValoresHelperNegociacao.getValorAtualizacaoMonetaria());
					}
				} 
			}
		}
		
		//[SB0002] - Obter Op��es de Parcelamento
		//CARREGANDO O HELPER COM AS INFORMA��ES DO PARCELAMENTO
		ObterOpcoesDeParcelamentoHelper helper = new ObterOpcoesDeParcelamentoHelper(
		new Integer(resolucaoDiretoria),
		new Integer(codigoImovel), 
		BigDecimal.ZERO, //valorEntradaInformado, 
		situacaoAguaId, 
		situacaoEsgotoId, 
		perfilImovel, 
		inicioIntervaloParcelamento, 
		indicadorRestabelecimento, 
		colecaoContaValores, //colecaoContaValoresNegociacao, 
		valorDebitoTotalAtualizado,
		valorTotalMultas, 
		valorTotalJurosMora, 
		valorTotalAtualizacoesMonetarias, 
		numeroReparcelamentoConsecutivos, 
		colecaoGuiaPagamentoValores, //colecaoGuiaPagamento, 
		usuario, 
		valorDebitoACobrarParcelamentoImovelBigDecimal, 
		inicioIntervaloParcelamentoFormatado,
		fimIntervaloParcelamentoFormatado, 
		indicadoresParcelamentoHelper,
		valorCreditoARealizar,true);

		NegociacaoOpcoesParcelamentoHelper opcoesParcelamento =  fachada.obterOpcoesDeParcelamento(helper);
				
		BigDecimal descontoTotalPagamentoAVista = opcoesParcelamento.getValorTotalDescontoPagamentoAVista();
		BigDecimal valorTotalImpostosConta = obterValorImpostosDasContasDoParcelamento(colecaoContaValores);
		
		BigDecimal valorPagamentoAVista = valorDebitoTotalAtualizado.subtract(descontoTotalPagamentoAVista);
		valorPagamentoAVista = valorPagamentoAVista.subtract(valorTotalImpostosConta);
		valorPagamentoAVista.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		
		// Colocando os valores no formul�rio
		efetuarParcelamentoDebitosActionForm.set("valorDescontoPagamentoAVista",Util.formatarMoedaReal(descontoTotalPagamentoAVista));
		efetuarParcelamentoDebitosActionForm.set("valorPagamentoAVista",Util.formatarMoedaReal(valorPagamentoAVista));
		efetuarParcelamentoDebitosActionForm.set("valorTotalImpostos",Util.formatarMoedaReal(valorTotalImpostosConta));
		
		String habilitaPagamentoAVista = "1";
		if (colecaoContaValores == null || colecaoContaValores.isEmpty()) {
			habilitaPagamentoAVista = "2";
		}
		sessao.setAttribute("habilitaPagamentoAVista", habilitaPagamentoAVista);
	}
	

	private BigDecimal obterValorImpostosDasContasDoParcelamento(Collection colecaoContas){
		
		BigDecimal valorTotalImpostos = BigDecimal.ZERO;
		
		if (colecaoContas != null && !colecaoContas.isEmpty()) {
			Iterator contas = colecaoContas.iterator();

			while (contas.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contas.next();
				
				if (contaValoresHelper.getConta().getValorImposto() != null) {
					valorTotalImpostos = valorTotalImpostos.add(contaValoresHelper.getConta().getValorImposto());
				}
			}
		}
		return valorTotalImpostos;
	}
	
}
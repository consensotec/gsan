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
package gcom.gui.cobranca;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.IndicadoresParcelamentoHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.ObterOpcoesDeParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

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
 * [UC0214] Efetuar Parcelamento de D�bitos
 *
 * @author Roberta Costa
 * @date 24/01/2006
 */
public class ProcessarEfetuarParcelamentoDebitosProcesso2Action extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("processo2");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		DynaActionForm efetuarParcelamentoDebitosActionForm = (DynaActionForm) actionForm;
		
		Integer indicadorGuiasPagamento = new Integer((String) (efetuarParcelamentoDebitosActionForm.get("indicadorGuiasPagamento")));
		Integer indicadorDebitosACobrar = new Integer((String) (efetuarParcelamentoDebitosActionForm.get("indicadorDebitosACobrar")));
		Integer indicadorCreditoARealizar = new Integer((String) (efetuarParcelamentoDebitosActionForm.get("indicadorCreditoARealizar")));
		String valorDebitoACobrarParcelamentoImovel = (String)efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarParcelamentoImovel");
		String valorCreditoARealizar = (String)efetuarParcelamentoDebitosActionForm.get("valorCreditoARealizar");
		String indicadorAcrescimosImpotualidade = (String) efetuarParcelamentoDebitosActionForm.get("indicadorAcrescimosImpotualidade");
		Integer indicadorContasRevisao = new Integer((String) efetuarParcelamentoDebitosActionForm.get("indicadorContasRevisao"));
		Integer indicadorDividaAtiva = new Integer((String) efetuarParcelamentoDebitosActionForm.get("indicadorDividaAtiva"));
		
		BigDecimal valorDebitoACobrarParcelamentoImovelBigDecimal = new BigDecimal("0.00");
		
		
		String inicioIntervaloParcelamento = (String) efetuarParcelamentoDebitosActionForm.get("inicioIntervaloParcelamento");
		String fimIntervaloParcelamento = (String)efetuarParcelamentoDebitosActionForm.get("fimIntervaloParcelamento");
		Integer fimIntervaloParcelamentoFormatado = null;
		Integer inicioIntervaloParcelamentoFormatado = null;
		if(inicioIntervaloParcelamento !=null && !inicioIntervaloParcelamento.equalsIgnoreCase("")){
			fimIntervaloParcelamentoFormatado = Util.formatarMesAnoComBarraParaAnoMes(fimIntervaloParcelamento);
			inicioIntervaloParcelamentoFormatado = Util.formatarMesAnoComBarraParaAnoMes(inicioIntervaloParcelamento);
		}
		IndicadoresParcelamentoHelper indicadoresParcelamentoHelper = new IndicadoresParcelamentoHelper();
		indicadoresParcelamentoHelper.setIndicadorContasRevisao(indicadorContasRevisao);
		indicadoresParcelamentoHelper.setIndicadorDebitosACobrar(indicadorDebitosACobrar);
		indicadoresParcelamentoHelper.setIndicadorCreditoARealizar(indicadorCreditoARealizar);
		indicadoresParcelamentoHelper.setIndicadorGuiasPagamento(indicadorGuiasPagamento);
		indicadoresParcelamentoHelper.setIndicadorAcrescimosImpotualidade(new Integer(indicadorAcrescimosImpotualidade));
		indicadoresParcelamentoHelper.setIndicadorDividaAtiva(new Integer(indicadorDividaAtiva));
		
		
		if (valorDebitoACobrarParcelamentoImovel != null && !valorDebitoACobrarParcelamentoImovel.equals("") ){
			valorDebitoACobrarParcelamentoImovelBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorDebitoACobrarParcelamentoImovel);
		}
		// Cria um boolean para verificar se todos os radio buttons do jsp anterior
		// foi escolhido, caso seja ent�o n�o deixa passar para a aba 3.
		boolean verificaRadioButton = true;

		// Armazena a Cole��o de Contas
		BigDecimal valorEntradaParcelamento = new BigDecimal("0.00");
		BigDecimal valorTotalAcrescimosContasEP = new BigDecimal("0.00");
		boolean marcadaEP = false;
		boolean marcadaNB = false;

		Collection<ContaValoresHelper> colecaoContaValores = (Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValores");
		
		// Guias de Pagamento
		Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoDebito = null;
		if( indicadorGuiasPagamento.equals(new Integer("1")) ){
			colecaoGuiaPagamentoDebito = (Collection<GuiaPagamentoValoresHelper>)sessao.getAttribute("colecaoGuiaPagamentoValores");
		}
		// Debitos A Cobrar
		Collection<DebitoACobrar> colecaoDebitoACobrarDebito = null; 
		if( indicadorDebitosACobrar.equals(new Integer("1")) ){
			colecaoDebitoACobrarDebito = (Collection<DebitoACobrar>)sessao.getAttribute("colecaoContaValores");
		}	

		// Verifica se as contas foram marcadas
		Collection<Integer> idsContaEP = new ArrayList<Integer>();
		Collection<ContaValoresHelper> colecaoContaValoresSemContasNB = new ArrayList<ContaValoresHelper>();
		
		if( colecaoContaValores != null && ! colecaoContaValores.isEmpty() ){
			Iterator contaValores = colecaoContaValores.iterator();
			while(contaValores.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();
				if( httpServletRequest.getParameter("indicadorContasDebito"+
						contaValoresHelper.getConta().getId().toString()) != null ){
					String indice = httpServletRequest.getParameter("indicadorContasDebito"+contaValoresHelper.getConta().getId().toString());
					contaValoresHelper.setIndicadorContasDebito(new Integer(indice));
					
					// Verifica se a conta foi marcada como EP
					if( contaValoresHelper.getIndicadorContasDebito().equals(new Integer("1")) ){
						valorEntradaParcelamento.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorEntradaParcelamento = valorEntradaParcelamento.add(contaValoresHelper.getValorTotalConta());
						marcadaEP = true;
						idsContaEP.add(contaValoresHelper.getConta().getId());
						colecaoContaValoresSemContasNB.add(contaValoresHelper);
						valorTotalAcrescimosContasEP = valorTotalAcrescimosContasEP.add(contaValoresHelper.getValorTotalContaValores());
						
					// Verifica se as contas foram marcadas como NB	
					}else if( contaValoresHelper.getIndicadorContasDebito().equals(new Integer("2")) ){
						marcadaNB = true;
						if( contaValoresHelper.getConta().getDataRevisao() != null ){
							throw new ActionServletException("atencao.conta.em.revisao");
						}
					}
				}else{
					verificaRadioButton = false;
					contaValoresHelper.setIndicadorContasDebito(null);
					colecaoContaValoresSemContasNB.add(contaValoresHelper);
				}
			}
			
			// Verifica se tem algum d�bito a parcelar de acordo com as op��es marcadas
			if(verificaRadioButton 
				&& indicadorGuiasPagamento.equals(new Integer("1"))
				&& indicadorCreditoARealizar.equals(new Integer("1"))
				&& ( ( colecaoGuiaPagamentoDebito == null || colecaoGuiaPagamentoDebito.equals("") )
				|| ( colecaoDebitoACobrarDebito == null || colecaoDebitoACobrarDebito.equals("") ) )){
				throw new ActionServletException(
						"atencao.nao.efetuar.parcelamento");
			}
		}
		
		sessao.setAttribute("idsContaEP",idsContaEP);
		sessao.setAttribute("colecaoContaValoresSemContasNB" , colecaoContaValoresSemContasNB);
		
		// Verifica se as entradas escolhidas s�o menores que a entrada minima
		if( marcadaEP ){
			// Pega vari�veis do formul�rio
			String codigoImovel = (String)efetuarParcelamentoDebitosActionForm.get("matriculaImovel");
			Integer situacaoAguaId = new Integer( (String)efetuarParcelamentoDebitosActionForm.get("situacaoAguaId"));
			Integer situacaoEsgotoId = new Integer( (String)efetuarParcelamentoDebitosActionForm.get("situacaoEsgotoId"));
			Integer perfilImovel = new Integer( (String) efetuarParcelamentoDebitosActionForm.get("perfilImovel"));
			Integer numeroReparcelamentoConsecutivos = new Integer( (String)efetuarParcelamentoDebitosActionForm.get("numeroReparcelamentoConsecutivos"));
			String resolucaoDiretoria = (String) efetuarParcelamentoDebitosActionForm.get("resolucaoDiretoria");
			
			BigDecimal valorDebitoTotalAtualizado = (BigDecimal) sessao.getAttribute("valorDebitoTotalAtualizado");
//			BigDecimal valorDebitoTotalAtualizado =	new BigDecimal("0.00");
//			if (!((String) efetuarParcelamentoDebitosActionForm.get("valorDebitoTotalAtualizado")).equals("")){
//				valorDebitoTotalAtualizado = Util.formatarMoedaRealparaBigDecimal((String)
//						efetuarParcelamentoDebitosActionForm.get("valorDebitoTotalAtualizado"));
//			}
			
			// O indicador s� ser� usado caso a situa��o de �gua do Im�vel seja
			// SUPRIMIDO, SUPRIMIDO PARCIAL, SUPRIMIDO PARCIAL A PEDIDO
			Integer indicadorRestabelecimento = new Integer("0");
			if (efetuarParcelamentoDebitosActionForm.get("indicadorRestabelecimento") != null
					&& !efetuarParcelamentoDebitosActionForm.get("indicadorRestabelecimento").equals("")) {
				indicadorRestabelecimento = new Integer(String.valueOf(
						efetuarParcelamentoDebitosActionForm.get("indicadorRestabelecimento")));
			}
	
			BigDecimal valorTotalMultas = new BigDecimal("0.00");
			BigDecimal valorTotalJurosMora = new BigDecimal("0.00");
			BigDecimal valorTotalAtualizacoesMonetarias = new BigDecimal("0.00");
			
			Collection<GuiaPagamento> colecaoGuiaPagamento = (Collection<GuiaPagamento>) sessao
				.getAttribute("colecaoGuiaPagamentoValores");
			
			if (colecaoContaValores != null && !colecaoContaValores.isEmpty()) {
				Iterator contaValores = colecaoContaValores.iterator();
				while (contaValores.hasNext()) {
					ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();
					if (sessao.getAttribute("colecaoContaValores") != null) {
						// Caso n�o tenha marcado a conta
						if (contaValoresHelper.getIndicadorContasDebito() == null) {
							if (contaValoresHelper.getValorMulta() != null) {
								valorTotalMultas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalMultas = valorTotalMultas.add(contaValoresHelper.getValorMulta());
							}
							if (contaValoresHelper.getValorJurosMora() != null) {
								valorTotalJurosMora.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalJurosMora = valorTotalJurosMora.add(contaValoresHelper.getValorJurosMora());
							}
							if (contaValoresHelper.getValorAtualizacaoMonetaria() != null) {
								valorTotalAtualizacoesMonetarias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalAtualizacoesMonetarias = valorTotalAtualizacoesMonetarias
										.add(contaValoresHelper.getValorAtualizacaoMonetaria());
							}
						}
					}
				}
			}
			
			BigDecimal valorEntradaInformado = new BigDecimal("0.00");
			if( efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado") != null 
				&& !efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado").equals("")
				&& !efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado").equals("0.00")){
				valorEntradaInformado = Util.formatarMoedaRealparaBigDecimal(
					(String) efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado"));
			}	
			
			//[SB0002] - Obter Op��es de Parcelamento de acordo com a entrada informada
			BigDecimal valorCreditoARealizarBigDecimal = new BigDecimal("0.00");
			if (valorCreditoARealizar != null){
				valorCreditoARealizarBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorCreditoARealizar);
			}
			
			
			//CARREGANDO O HELPER COM AS INFORMA��ES DO PARCELAMENTO
			ObterOpcoesDeParcelamentoHelper helper = new ObterOpcoesDeParcelamentoHelper(
			new Integer(resolucaoDiretoria), new Integer(codigoImovel), new BigDecimal("0.00"), situacaoAguaId, 
			situacaoEsgotoId, perfilImovel, inicioIntervaloParcelamento, indicadorRestabelecimento, 
			colecaoContaValores, valorDebitoTotalAtualizado, valorTotalMultas, valorTotalJurosMora, 
			valorTotalAtualizacoesMonetarias, numeroReparcelamentoConsecutivos, colecaoGuiaPagamento, usuario, 
			valorDebitoACobrarParcelamentoImovelBigDecimal, inicioIntervaloParcelamentoFormatado,
			fimIntervaloParcelamentoFormatado, indicadoresParcelamentoHelper,valorCreditoARealizarBigDecimal,false);
			//chama o obter d�bito apenas para obter entrada m�nima
			helper.setCalcularEntradaMinima(ConstantesSistema.SIM);
			NegociacaoOpcoesParcelamentoHelper opcoesParcelamento = fachada.obterOpcoesDeParcelamento(helper);
	
//			BigDecimal valorEntradaMinima = opcoesParcelamento.getValorEntradaMinima();
			BigDecimal valorEntradaMinimaPermitida = opcoesParcelamento.getValorEntradaMinimaPermitida();
			BigDecimal valorEntradaMinima = opcoesParcelamento.getValorEntradaMinima();
			
			// Atualizando o valor do d�bito total
			efetuarParcelamentoDebitosActionForm.set("valorDebitoTotalAtualizado",Util.formatarMoedaReal(valorDebitoTotalAtualizado));
			
			// Verifica se existe valor de entrada de parcelamento marcada pelas EP
			if (valorEntradaParcelamento != null && !valorEntradaParcelamento.equals(new BigDecimal("0.00")) ){
	
				if( indicadorAcrescimosImpotualidade.equals("1") &&
						opcoesParcelamento.getEntradaEhExtratoDebito().equals(ConstantesSistema.SIM)){
					valorEntradaParcelamento = valorEntradaParcelamento.add(valorTotalAcrescimosContasEP);
				}
				BigDecimal valorDescontoEntrada = opcoesParcelamento.getValorDescontoEntrada();
				BigDecimal valorEntradaParcelamentoComDesconto = valorEntradaParcelamento;
				
				if(valorDescontoEntrada != null ){
					valorEntradaParcelamentoComDesconto = valorEntradaParcelamentoComDesconto.subtract(valorDescontoEntrada);
				}
				
				//Verificar permiss�o especial
				boolean temPermissaoValMinimoEntrada = fachada.verificarPermissaoValMinimoEntrada(usuario);
				
				if (valorEntradaParcelamentoComDesconto.compareTo(valorEntradaMinimaPermitida.setScale(2,BigDecimal.ROUND_HALF_UP)) == -1
						&& !temPermissaoValMinimoEntrada) {
					throw new ActionServletException("atencao.valor.entrada.deve.ser.maior.igual.minima", 
							null, Util.formatarMoedaReal(valorEntradaMinimaPermitida));
				}else{
					valorEntradaMinima = valorEntradaParcelamento;
				}
			}else{
				valorEntradaMinima = valorEntradaInformado;
			}
			
			// Colocando o valor da entrada na sess�o caso tenha sido marcada EP
			sessao.setAttribute("valorEntradaMinima", valorEntradaMinima);
		}else{
			// Colocando o valor da entrada na sess�o caso n�o tenha sido marcada EP
			if( ! marcadaNB ){
				sessao.setAttribute("valorEntradaMinima", new BigDecimal("0.00"));
				
				sessao.removeAttribute("valorAcrescimosNB");
				sessao.removeAttribute("colecaoContaValoresSemContasNB");
			}	
		}
		
		// Faz os c�lculos quando as contas NB forem marcadas, quando o bot�o calcular n�o for acionado
		// Apenas as NB s�o retiradas do d�bito
		String calcula = (String) sessao.getAttribute("calcula");
		String verificaCalcula = "session";

		if( marcadaNB ){
//			if(  calcula == null && marcadaNB ){
			// Pega vari�veis da sess�o
			BigDecimal valorAtualizacaoMonetaria = Util.formatarMoedaRealparaBigDecimal((String) efetuarParcelamentoDebitosActionForm.get("valorAtualizacaoMonetaria"));
			BigDecimal valorJurosMora = Util.formatarMoedaRealparaBigDecimal((String) efetuarParcelamentoDebitosActionForm.get("valorJurosMora"));
			BigDecimal valorMulta = Util.formatarMoedaRealparaBigDecimal((String) efetuarParcelamentoDebitosActionForm.get("valorMulta"));
			
			BigDecimal valorTotalContaValores = (BigDecimal) sessao.getAttribute("valorTotalContaValores");
			BigDecimal valorAcrescimosImpontualidade = (BigDecimal) sessao.getAttribute("valorAcrescimosImpontualidade");
			BigDecimal valorDebitoTotalAtualizado = (BigDecimal) sessao.getAttribute("valorDebitoTotalAtualizado");

			// Atribui 1 a calcula na sess�o
			sessao.setAttribute("calcula", "1");

			colecaoContaValores = (Collection<ContaValoresHelper>) sessao.getAttribute("colecaoContaValores");

			BigDecimal valorContaNB = new BigDecimal("0.00");
			BigDecimal valorAcrescimosNB = new BigDecimal("0.00");
			
			BigDecimal valorAtualizacaoMonetariaNB = new BigDecimal("0.00");
			BigDecimal valorJurosMoraNB = new BigDecimal("0.00");
			BigDecimal valorMultaNB = new BigDecimal("0.00");

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
							
							// Caso as contas sejam n�o baixadas
							if (indice.equals("2")) {
								valorContaNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorContaNB = valorContaNB.add(contaValoresHelper.getConta().getValorTotal());
								if( indicadorAcrescimosImpotualidade.equals("1") ){
									valorAtualizacaoMonetariaNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
									valorAtualizacaoMonetariaNB = valorAtualizacaoMonetariaNB.add(contaValoresHelper.getValorAtualizacaoMonetaria());
									
									valorJurosMoraNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
									valorJurosMoraNB = valorJurosMoraNB.add(contaValoresHelper.getValorJurosMora());
									
									valorMultaNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
									valorMultaNB = valorMultaNB.add(contaValoresHelper.getValorMulta());
									
									valorAcrescimosNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
									valorAcrescimosNB = valorAcrescimosNB.add(contaValoresHelper.getValorTotalContaValoresParcelamento());
								}	
							}
						}
					} else {
						if (contaValoresHelper.getIndicadorContasDebito() != null) {
							if (contaValoresHelper.getIndicadorContasDebito().equals(2)) {
								valorContaNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorContaNB = valorContaNB.add(contaValoresHelper.getConta().getValorTotal());
								
								valorAtualizacaoMonetariaNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorAtualizacaoMonetariaNB = valorAtualizacaoMonetariaNB.add(contaValoresHelper.getValorAtualizacaoMonetaria());
								
								valorJurosMoraNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorJurosMoraNB = valorJurosMoraNB.add(contaValoresHelper.getValorJurosMora());
								
								valorMultaNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorMultaNB = valorMultaNB.add(contaValoresHelper.getValorMulta());
								
								valorAcrescimosNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorAcrescimosNB = valorAcrescimosNB.add(contaValoresHelper.getValorTotalContaValoresParcelamento());
							}
						}
					}
				}
				valorTotalContaValores.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorTotalContaValores = valorTotalContaValores.subtract(valorContaNB);
				if( indicadorAcrescimosImpotualidade.equals("1") ){
					valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.subtract(valorAtualizacaoMonetariaNB);
					valorJurosMora = valorJurosMora.subtract(valorJurosMoraNB);
					valorMulta = valorMulta.subtract(valorMultaNB);

					valorAcrescimosImpontualidade = valorAcrescimosImpontualidade.subtract(valorAcrescimosNB);
				}	

				// Calcula sempre em cima do valor do debito
				valorDebitoTotalAtualizado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.subtract(valorContaNB);
				valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.subtract(valorAcrescimosNB);
				
				efetuarParcelamentoDebitosActionForm.set("valorTotalContaValores",Util.formatarMoedaReal(valorTotalContaValores));
				
				efetuarParcelamentoDebitosActionForm.set("valorAcrescimosImpontualidade",Util.formatarMoedaReal(valorAcrescimosImpontualidade));
				
				efetuarParcelamentoDebitosActionForm.set("valorAtualizacaoMonetaria", Util.formatarMoedaReal(valorAtualizacaoMonetaria));
				efetuarParcelamentoDebitosActionForm.set("valorJurosMora", Util.formatarMoedaReal(valorJurosMora));
				efetuarParcelamentoDebitosActionForm.set("valorMulta", Util.formatarMoedaReal(valorMulta));
				
				if( valorDebitoTotalAtualizado.compareTo(new BigDecimal("0.00")) == -1
						|| valorDebitoTotalAtualizado.equals(new BigDecimal("0.00")) ){
					throw new ActionServletException("atencao.nao.existe.debito.a.parcelar");
				}
				efetuarParcelamentoDebitosActionForm.set("valorDebitoTotalAtualizado",Util.formatarMoedaReal(valorDebitoTotalAtualizado));
			}
			sessao.setAttribute("valorAcrescimosNB",valorAcrescimosNB);
		}
		
		return retorno;
	}
}
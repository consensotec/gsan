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
package gcom.gui.arrecadacao.aviso;

import gcom.arrecadacao.FiltroDevolucao;
import gcom.arrecadacao.aviso.bean.MovimentarPagamentosDevolucoesHelper;
import gcom.arrecadacao.aviso.bean.PagamentosDevolucoesHelper;
import gcom.arrecadacao.aviso.bean.ValoresArrecadacaoDevolucaoAvisoBancarioHelper;
import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0611] Movimentar Pagamentos/ Devolu��es entre Avisos Banc�rios
 * 
 * @author Ana Maria
 * 
 * @date 11/06/2007
 */

public class ExibirMovimentarPagamentosDevolucoesAvisoBancarioAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirMovimentarPagamentosDevolucoesAvisoBancario");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		MovimentarPagamentosDevolucoesAvisoBancarioActionForm form = (MovimentarPagamentosDevolucoesAvisoBancarioActionForm) actionForm;

		if(httpServletRequest.getParameter("removerPagamentos") == null && httpServletRequest.getParameter("removerDevolucoes") == null){
			PagamentosDevolucoesHelper pagamentoHelper = null;
			PagamentosDevolucoesHelper devolucaoHelper = null;
	
			if (sessao.getAttribute("filtroPagamento") != null) {
				FiltroPagamento filtroPagamento = null;
				filtroPagamento = (FiltroPagamento) sessao.getAttribute("filtroPagamento");
	
				pagamentoHelper = fachada.filtrarPagamentos(filtroPagamento);
			}
	
			if (sessao.getAttribute("filtroDevolucao") != null) {
				FiltroDevolucao filtroDevolucao = null;
				filtroDevolucao = (FiltroDevolucao) sessao.getAttribute("filtroDevolucao");
	
				devolucaoHelper = fachada.filtrarDevolucoes(filtroDevolucao);
			}
	
			if (pagamentoHelper == null && devolucaoHelper == null) {
				throw new ActionServletException(
						"atencao.aviso_bancario_origem_sem_pagamentos_devolucoes");
			}
	
			//Aviso Banc�rio Origem
			if (sessao.getAttribute("avisoBancarioO") != null) {
				Integer idAvisoBancarioO = (Integer) sessao.getAttribute("avisoBancarioO");
				ValoresArrecadacaoDevolucaoAvisoBancarioHelper helper = fachada.pesquisarValoresAvisoBancario(idAvisoBancarioO);
	
				if (helper != null && !helper.equals("")) {
					//Situa��o Antes
					form.setArrecadacaoInformadoAntesOrigem(Util
							.formatarMoedaReal(helper.getValorArrecadacaoInformado()));
					form.setArrecadacaoCalculadoAntesOrigem(Util
							.formatarMoedaReal(helper.getValorArrecadacaoCalculado()));
					form.setDevolucaoInformadoAntesOrigem(Util
							.formatarMoedaReal(helper.getValorDevolucaoInformado()));
					form.setDevolucaoCalculadoAntesOrigem(Util
							.formatarMoedaReal(helper.getValorDevolucaoCalculado()));
					
					//Situa��o Depois
					if (pagamentoHelper != null) {
						form.setArrecadacaoInformadoDepoisOrigem(Util
							.formatarMoedaReal(helper.getValorArrecadacaoInformado().subtract(
													Util.formatarMoedaRealparaBigDecimal(pagamentoHelper.getValorTotalPagamentos()))));

						form.setArrecadacaoCalculadoDepoisOrigem(Util
							.formatarMoedaReal(helper.getValorArrecadacaoCalculado().subtract(
													Util.formatarMoedaRealparaBigDecimal(pagamentoHelper.getValorTotalPagamentos()))));
					} else {
						form.setArrecadacaoInformadoDepoisOrigem(Util
							.formatarMoedaReal(helper.getValorArrecadacaoInformado()));
						form.setArrecadacaoCalculadoDepoisOrigem(Util
							.formatarMoedaReal(helper.getValorArrecadacaoCalculado()));
					}
					if (devolucaoHelper != null) {
						form.setDevolucaoInformadoDepoisOrigem(Util
							.formatarMoedaReal(helper.getValorDevolucaoInformado().subtract(
													Util.formatarMoedaRealparaBigDecimal(devolucaoHelper.getValorTotalDevolucoes()))));

						form.setDevolucaoCalculadoDepoisOrigem(Util
							.formatarMoedaReal(helper.getValorDevolucaoCalculado().subtract(
													Util.formatarMoedaRealparaBigDecimal(devolucaoHelper.getValorTotalDevolucoes()))));
					} else {
						form.setDevolucaoInformadoDepoisOrigem(Util
							.formatarMoedaReal(helper.getValorDevolucaoInformado()));
						form.setDevolucaoCalculadoDepoisOrigem(Util
							.formatarMoedaReal(helper.getValorDevolucaoCalculado()));
					}
				}
			}
	
			//Aviso Banc�rio Destino
			if (sessao.getAttribute("avisoBancarioD") != null) {
				Integer idAvisoBancarioD = (Integer) sessao.getAttribute("avisoBancarioD");
				ValoresArrecadacaoDevolucaoAvisoBancarioHelper helper = fachada.pesquisarValoresAvisoBancario(idAvisoBancarioD);
	
				if (helper != null && !helper.equals("")) {
					//Situa��o Antes
					form.setArrecadacaoInformadoAntesDestino(Util
						    .formatarMoedaReal(helper.getValorArrecadacaoInformado()));
					form.setArrecadacaoCalculadoAntesDestino(Util
						    .formatarMoedaReal(helper.getValorArrecadacaoCalculado()));
					form.setDevolucaoInformadoAntesDestino(Util
							.formatarMoedaReal(helper.getValorDevolucaoInformado()));
					form.setDevolucaoCalculadoAntesDestino(Util
							.formatarMoedaReal(helper.getValorDevolucaoCalculado()));
					
					//Situa��o Depois
					if (pagamentoHelper != null) {
						form.setArrecadacaoInformadoDepoisDestino(Util
					        .formatarMoedaReal(helper.getValorArrecadacaoInformado().add(
												Util.formatarMoedaRealparaBigDecimal(pagamentoHelper.getValorTotalPagamentos()))));

						form.setArrecadacaoCalculadoDepoisDestino(Util
							.formatarMoedaReal(helper.getValorArrecadacaoCalculado().add(
												Util.formatarMoedaRealparaBigDecimal(pagamentoHelper.getValorTotalPagamentos()))));
					} else {
						form.setArrecadacaoInformadoDepoisDestino(Util
							.formatarMoedaReal(helper.getValorArrecadacaoInformado()));
						form.setArrecadacaoCalculadoDepoisDestino(Util
							.formatarMoedaReal(helper.getValorArrecadacaoCalculado()));
					}
					if (devolucaoHelper != null) {
						form.setDevolucaoInformadoDepoisDestino(Util
							.formatarMoedaReal(helper.getValorDevolucaoInformado().add(
												Util.formatarMoedaRealparaBigDecimal(devolucaoHelper.getValorTotalDevolucoes()))));

						form.setDevolucaoCalculadoDepoisDestino(Util.formatarMoedaReal(helper.getValorDevolucaoCalculado().add(
												Util.formatarMoedaRealparaBigDecimal(devolucaoHelper.getValorTotalDevolucoes()))));
					} else {
						form.setDevolucaoInformadoDepoisDestino(Util.formatarMoedaReal(helper.getValorDevolucaoInformado()));
						form.setDevolucaoCalculadoDepoisDestino(Util.formatarMoedaReal(helper.getValorDevolucaoCalculado()));
					}
				}
			}
	
			sessao.setAttribute("pagamentoHelper", pagamentoHelper);
			sessao.setAttribute("devolucaoHelper", devolucaoHelper);
		}else if(httpServletRequest.getParameter("removerPagamentos") != null){
			if(sessao.getAttribute("pagamentoHelper") != null){
				/*Collection<Integer> idsPagamentosRemovidos = null;
				if(sessao.getAttribute("idsPagamentosRemovidos") == null){
					idsPagamentosRemovidos = new ArrayList();
				}else{
					idsPagamentosRemovidos = (Collection<Integer>)sessao.getAttribute("idsPagamentosRemovidos");
				}*/
				PagamentosDevolucoesHelper helper = (PagamentosDevolucoesHelper)sessao.getAttribute("pagamentoHelper");
				Collection<MovimentarPagamentosDevolucoesHelper> colecaoPagamentos = helper.getColecaoMovimentarPagamentos();
				String[] ids = form.getIdRegistrosRemocaoPagamento();
				if (ids != null && ids.length != 0) {
					for (int i = 0; i < ids.length; i++) {
						String[] idsPagamentos = ids[i].split(";");
						Integer idPagamento = Integer.parseInt(idsPagamentos[0]);		
						//idsPagamentosRemovidos.add(idPagamento);
						//sessao.setAttribute("idsPagamentosRemovidos", idsPagamentosRemovidos);
						MovimentarPagamentosDevolucoesHelper movimentarPagamento = new MovimentarPagamentosDevolucoesHelper();
						movimentarPagamento.setId(idPagamento);
						colecaoPagamentos.remove(movimentarPagamento);
						Integer qtdAtual = helper.getQtdPagamentos();
						helper.setQtdPagamentos(qtdAtual - 1);
						BigDecimal valorRemocao = Util.formatarMoedaRealparaBigDecimal(idsPagamentos[1]);
						BigDecimal valorAtual = Util.formatarMoedaRealparaBigDecimal(helper.getValorTotalPagamentos()).subtract(valorRemocao);
						helper.setValorTotalPagamentos(Util.formatarMoedaReal(valorAtual));
						form.setArrecadacaoInformadoDepoisOrigem(Util.formatarMoedaReal(Util.formatarMoedaRealparaBigDecimal(form.getArrecadacaoInformadoDepoisOrigem()).add(valorRemocao)));
						form.setArrecadacaoInformadoDepoisDestino(Util.formatarMoedaReal(Util.formatarMoedaRealparaBigDecimal(form.getArrecadacaoInformadoDepoisDestino()).subtract(valorRemocao)));						
						form.setArrecadacaoCalculadoDepoisOrigem(Util.formatarMoedaReal(Util.formatarMoedaRealparaBigDecimal(form.getArrecadacaoCalculadoDepoisOrigem()).add(valorRemocao)));
						form.setArrecadacaoCalculadoDepoisDestino(Util.formatarMoedaReal(Util.formatarMoedaRealparaBigDecimal(form.getArrecadacaoCalculadoDepoisDestino()).subtract(valorRemocao)));
					}
				}
			  if(colecaoPagamentos != null && !colecaoPagamentos.isEmpty()){
				  helper.setColecaoMovimentarPagamentos(colecaoPagamentos);	  
			  }else{
				  helper = null; 
			  }
			  sessao.setAttribute("pagamentoHelper", helper);
			}			
		}else if(httpServletRequest.getParameter("removerDevolucoes") != null){
			if(sessao.getAttribute("devolucaoHelper") != null){
				/*Collection<Integer> idsDevolucaoRemovidos = null;
				if(sessao.getAttribute("idsDevolucaoRemovidos")== null){
					idsDevolucaoRemovidos = new ArrayList();
				}else{
				    idsDevolucaoRemovidos = (Collection<Integer>)sessao.getAttribute("idsDevolucaoRemovidos");
				}*/
				PagamentosDevolucoesHelper helper = (PagamentosDevolucoesHelper)sessao.getAttribute("devolucaoHelper");
				Collection<MovimentarPagamentosDevolucoesHelper> colecaoDevolucao = helper.getColecaoMovimentarDevolucoes();
				String[] ids = form.getIdRegistrosRemocaoDevolucao();
				if (ids != null && ids.length != 0) {
					for (int i = 0; i < ids.length; i++) {
						String[] idsDevolucao = ids[i].split(";");
						Integer idDevolucao = Integer.parseInt(idsDevolucao[0]);	
						//idsDevolucaoRemovidos.add(idDevolucao);
						//sessao.setAttribute("idsDevolucaoRemovidos", idsDevolucaoRemovidos);
						MovimentarPagamentosDevolucoesHelper movimentarDevolucao = new MovimentarPagamentosDevolucoesHelper();
						movimentarDevolucao.setId(idDevolucao);
						colecaoDevolucao.remove(movimentarDevolucao);
						Integer qtdAtual = helper.getQtdDevolucoes();
						helper.setQtdDevolucoes(qtdAtual - 1);						
						BigDecimal valorRemocao = Util.formatarMoedaRealparaBigDecimal(idsDevolucao[1]);
						BigDecimal valorAtual = Util.formatarMoedaRealparaBigDecimal(helper.getValorTotalDevolucoes()).subtract(valorRemocao);
						helper.setValorTotalDevolucoes(Util.formatarMoedaReal(valorAtual));						
						form.setDevolucaoInformadoDepoisOrigem(Util.formatarMoedaReal(Util.formatarMoedaRealparaBigDecimal(form.getDevolucaoInformadoDepoisOrigem()).add(valorRemocao)));
						form.setDevolucaoInformadoDepoisDestino(Util.formatarMoedaReal(Util.formatarMoedaRealparaBigDecimal(form.getDevolucaoInformadoDepoisDestino()).subtract(valorRemocao)));						
						form.setDevolucaoCalculadoDepoisOrigem(Util.formatarMoedaReal(Util.formatarMoedaRealparaBigDecimal(form.getDevolucaoCalculadoDepoisOrigem()).add(valorRemocao)));
						form.setDevolucaoCalculadoDepoisDestino(Util.formatarMoedaReal(Util.formatarMoedaRealparaBigDecimal(form.getDevolucaoCalculadoDepoisDestino()).subtract(valorRemocao)));
					}
				}
			  if(colecaoDevolucao != null && !colecaoDevolucao.isEmpty()){
				  helper.setColecaoMovimentarPagamentos(colecaoDevolucao);	  
			  }else{
				  helper = null; 
			  }
			  sessao.setAttribute("devolucaoHelper", helper);
			}			
		}
		
		return retorno;
	}

}
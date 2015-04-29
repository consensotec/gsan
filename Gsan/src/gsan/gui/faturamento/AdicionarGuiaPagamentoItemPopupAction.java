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
package gsan.gui.faturamento;

import gsan.arrecadacao.pagamento.GuiaPagamentoItem;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.conta.ContaGeral;
import gsan.faturamento.debito.DebitoTipo;
import gsan.faturamento.debito.FiltroDebitoTipo;
import gsan.financeiro.FinanciamentoTipo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Adicionar Guia Pagamento Item Popup
 * 
 * @author Fl�vio Leonardo
 * @created 25/04/2006
 */
public class AdicionarGuiaPagamentoItemPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirAdicionarGuiaPagamentoItemPopup");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Inst�ncia do formul�rio que est� sendo utilizado
		AdicionarGuiaPagamentoItemActionForm form = (AdicionarGuiaPagamentoItemActionForm) actionForm;

		String idDebitoTipo = form.getIdTipoDebito();
		
		//Criando o guia pagamento item
		GuiaPagamentoItem guiaPagamentoItem = new GuiaPagamentoItem();
		
		guiaPagamentoItem.setUltimaAlteracao(new Date());
		guiaPagamentoItem.setValorDebito(Util.formatarMoedaRealparaBigDecimal(form.getValorTotalServico()));
		

		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
        filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
        filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, idDebitoTipo));
        filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
        
		Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
		
		//String desabilitaCampo = httpServletRequest.getParameter("desabilitaIdDebito");
		
		/*if(desabilitaCampo != null && !desabilitaCampo.equals("")){
			httpServletRequest.setAttribute("desabilitaIdDebito", "sim");
		}*/
		
		if(colecaoDebitoTipo.isEmpty()){
			
			throw new ActionServletException(
			"pesquisa.registro.inexistente");
		}
		
		//[FS0021] Valor informado maior ou igual que valor da conta selecionada.
		Integer idConta = null;
		if (httpServletRequest.getParameter("conta") != null){
			
			idConta = Integer.valueOf(httpServletRequest.getParameter("conta"));
			
			Conta conta = (Conta) Util.retonarObjetoDeColecao(fachada.obterConta(idConta));
			
			ContaGeral contaGeral = new ContaGeral();
			contaGeral.setId(idConta);
			contaGeral.setConta(conta);
			
			guiaPagamentoItem.setContaGeral(contaGeral);
		}
		
		fachada.validarValorTotalServicoParaPagamentoParcial(idConta, Integer.valueOf(idDebitoTipo), 
		guiaPagamentoItem.getValorDebito());
		
		DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator().next();
		debitoTipo.setId(new Integer(idDebitoTipo));
		
		// [FS0013] - Verificar tipo de financaimento do tipo de d�bito
		if (debitoTipo.getFinanciamentoTipo().getId().intValue() != FinanciamentoTipo.SERVICO_NORMAL.intValue()
				|| debitoTipo.getIndicadorGeracaoAutomatica().shortValue() == 1) {

			throw new ActionServletException(
					"atencao.tipo_financiamento.tipo_debito.nao.permite.guia_pagamento_item",
					debitoTipo.getFinanciamentoTipo().getDescricao(),
					debitoTipo.getDescricao());
		}
		
		// [FS0024] - Verificar bloqueio do tipo do d�bito para inclus�o on-line da guia
		if (debitoTipo.getIndicadorJurosParCliente().toString().equals(ConstantesSistema.CONFIRMADA)){
			
			throw new ActionServletException(
					"atencao.tipo_financiamento.tipo_debito.nao.permite.tipo_exclusivo",
					debitoTipo.getDescricaoAbreviada());
		}
		

		guiaPagamentoItem.setDebitoTipo(debitoTipo);
		
		Collection colecaoGuiaPagamentoItemSessao = (Collection)sessao.getAttribute("colecaoGuiaDebitoTipo");
		boolean existe = false;
		
		if(colecaoGuiaPagamentoItemSessao != null){
			
			if(this.pagamentoParcialJaInformado(colecaoGuiaPagamentoItemSessao, debitoTipo)){
				
				throw new ActionServletException(
				"atencao.guia_pagamento_item_excedeu_limite");
			}
			else if(colecaoGuiaPagamentoItemSessao.size() < 25){
					
				Iterator iterator = colecaoGuiaPagamentoItemSessao.iterator();
				while(iterator.hasNext()){
					GuiaPagamentoItem guiaPagamentoItemColecao = (GuiaPagamentoItem)iterator.next();
						
					if(!debitoTipo.getFinanciamentoTipo().getId().equals(guiaPagamentoItemColecao.getDebitoTipo().getFinanciamentoTipo().getId())){
						throw new ActionServletException(
								"atencao.tipo_financiamento.tipo_debito.diferente");
					}
						
					if(guiaPagamentoItemColecao.getDebitoTipo().getId().equals(debitoTipo.getId())){
						existe = true;
					}
				}
					
				if(!existe){
					colecaoGuiaPagamentoItemSessao.add(guiaPagamentoItem);
				}
				else{
					throw new ActionServletException(
					"atencao.tipo_financiamento.tipo_debito.ja_existe_na_lista");
				}
			}
			else{
					
				throw new ActionServletException(
				"atencao.tipo_financiamento.tipo_debito.maior_que_vinte_cinco");
			}
		}
		else{
			colecaoGuiaPagamentoItemSessao = new ArrayList();
			colecaoGuiaPagamentoItemSessao.add(guiaPagamentoItem);
		}
		
		form.setIdTipoDebito("");
		form.setDescricaoTipoDebito("");
		form.setValorTotalServico("");
		form.setMatriculaImovel("");
		
		sessao.removeAttribute("colecaoContaParaPagamentoParcial");
		
		sessao.setAttribute("colecaoGuiaDebitoTipo",colecaoGuiaPagamentoItemSessao);
		
		httpServletRequest.setAttribute("desabilitaIdDebito", "sim");
		httpServletRequest.setAttribute("reloadPage",1);
		
		return retorno;
	}
	
	private boolean pagamentoParcialJaInformado(Collection colecaoGuiaPagamentoItemSessao,
			DebitoTipo debitoTipo){
		
		boolean retorno = false;
		
		if (colecaoGuiaPagamentoItemSessao.size() > 0 &&
			debitoTipo.getId().equals(DebitoTipo.PAGAMENTO_PARCIAL_CONTA)){
			
			retorno = true;
		}
		
		if (!retorno){
			
			Iterator iterator = colecaoGuiaPagamentoItemSessao.iterator();
			
			while(iterator.hasNext()){
				
				GuiaPagamentoItem guiaPagamentoItemColecao = (GuiaPagamentoItem)iterator.next();
				
				if (guiaPagamentoItemColecao.getDebitoTipo().getId()
					.equals(DebitoTipo.PAGAMENTO_PARCIAL_CONTA)){
					
					retorno = true;
					break;
				}
				
			}
		}
		
		return retorno;
	}

}

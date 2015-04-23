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
* Yara Taciane de Souza
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

import gsan.cobranca.DocumentoTipo;
import gsan.cobranca.NegativadorMovimentoReg;
import gsan.cobranca.NegativadorMovimentoRegItem;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.spcserasa.FiltroNegativadorMovimento;
import gsan.spcserasa.FiltroNegativadorMovimentoRegItem;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0674] Pesquisar Movimento Negativador
 * 
 * @author Yara Taciane
 * @date 27/12/2007
 * 
 */
public class ExibirConsultarNegativadorMovimentoRegItemPopupAction extends GcomAction {

	/**
	 * 
	 * [UC0438] Este caso de uso efetua pesquisa de Movimento do Negativador
	 * 
	 * 
	 * @author Yara Taciane
	 * @date 03/08/2006
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

		HttpSession sessao = httpServletRequest.getSession(false);	
		
		ActionForward retorno = actionMapping.findForward("negativadorMovimentoRegItemConsultarPopup");
		
		ConsultarNegativadorMovimentoRegItemPopupActionForm form = (ConsultarNegativadorMovimentoRegItemPopupActionForm) actionForm;
		
		NegativadorMovimentoReg negativadorMovimentoReg = (NegativadorMovimentoReg) sessao.getAttribute("negativadorMovimentoReg");
		
		
		String negativadorMovimentoRegID = httpServletRequest.getParameter("negativadorMovimentoReg");
		String negativador = httpServletRequest.getParameter("negativador");
		
		if (negativadorMovimentoReg == null) {
			
			FiltroNegativadorMovimento filtroNegativadorMovimento = new FiltroNegativadorMovimento();
			filtroNegativadorMovimento.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimento.ID, new Integer(negativadorMovimentoRegID)));
			filtroNegativadorMovimento.adicionarCaminhoParaCarregamentoEntidade("imovel");
			filtroNegativadorMovimento.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
			filtroNegativadorMovimento.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
			filtroNegativadorMovimento.adicionarCaminhoParaCarregamentoEntidade("negativadorMovimento");
			filtroNegativadorMovimento.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
			filtroNegativadorMovimento.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
			filtroNegativadorMovimento.adicionarCaminhoParaCarregamentoEntidade("negativadorMovimento.negativador");
			filtroNegativadorMovimento.adicionarCaminhoParaCarregamentoEntidade("negativadorMovimento.negativador.cliente");
			
			
			Collection colecaoNegativadorMovimento = Fachada.getInstancia().pesquisar(filtroNegativadorMovimento, NegativadorMovimentoReg.class.getName());
			negativadorMovimentoReg = (NegativadorMovimentoReg) Util.retonarObjetoDeColecao(colecaoNegativadorMovimento);
		}
		
		Fachada fachada = Fachada.getInstancia();
		
		
		if((negativadorMovimentoReg != null && !negativadorMovimentoReg.equals("")) ||
				negativadorMovimentoRegID != null && !negativadorMovimentoRegID.equals("")){
			
			if (negativadorMovimentoReg != null){
			  form.setNegativador(negativadorMovimentoReg.getNegativadorMovimento().getNegativador().getCliente().getNome());
			} else {
				form.setNegativador(negativador);
			}
			  if(negativadorMovimentoReg.getImovel()!= null){
				  form.setMatriculaImovel(negativadorMovimentoReg.getImovel().getId().toString()); 
				  
			  }
			  
			 if(negativadorMovimentoReg.getImovel()!= null && negativadorMovimentoReg.getImovel().getInscricaoFormatada()!= null){
				 form.setInscricao(negativadorMovimentoReg.getImovel().getInscricaoFormatada());
			 }
			 
			 if (negativadorMovimentoReg.getLigacaoAguaSituacao() != null) {
				 form.setSituacaoLigacaoAgua(negativadorMovimentoReg.getLigacaoAguaSituacao().getDescricao());
			 }
			 
			 if (negativadorMovimentoReg.getLigacaoEsgotoSituacao() != null){
				 form.setSituacaoLigacaoEsgoto(negativadorMovimentoReg.getLigacaoEsgotoSituacao().getDescricao());
			 }
			  
			 if (negativadorMovimentoReg.getDataSituacaoDebito() != null){
				 sessao.setAttribute("dataSituacaoDebito", Util.formatarData(negativadorMovimentoReg.getDataSituacaoDebito()));
			 }
			  
			  
			
			  //situacao da ligacao da agua
			  //situacao da ligacao esgoto
				
			  FiltroNegativadorMovimentoRegItem filtroNegativadorMovimentoRegItem = new FiltroNegativadorMovimentoRegItem();
			  
			  if (negativadorMovimentoReg != null){
				  filtroNegativadorMovimentoRegItem.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.NEGATIVADOR_MOVIMENTO_REG_ID,
								new Integer(negativadorMovimentoReg.getId())));
			  } else {
				  filtroNegativadorMovimentoRegItem.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.NEGATIVADOR_MOVIMENTO_REG_ID,
						new Integer(negativadorMovimentoRegID)));
			  }
			  filtroNegativadorMovimentoRegItem.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.DOCUMENTO_TIPO_ID,
					  new Integer(DocumentoTipo.CONTA)));
				  
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("negativadorMovimentoReg");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("negativadorMovimentoReg.imovel");
			  
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("guiaPagamentoGeral");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.debitoCreditoSituacaoAtual");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.debitoCreditoSituacaoAtual");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("contaGeral");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("contaGeral.conta");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("contaGeral.contaHistorico");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("cobrancaDebitoSituacao");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("cobrancaDebitoSituacaoAposExclusao");
			  
			  Collection<NegativadorMovimentoRegItem> collNegativadorMovimentoRegItem = fachada.pesquisar(filtroNegativadorMovimentoRegItem,
					  NegativadorMovimentoRegItem.class.getName());
			  
			  sessao.setAttribute("collNegativadorMovimentoRegItem", collNegativadorMovimentoRegItem);
			  Integer totalQtdContas = 0;
			  BigDecimal totalValorDebitoConta = new BigDecimal("0.00");
			  BigDecimal totalValorConta = new BigDecimal("0.00");
			  
			  if (collNegativadorMovimentoRegItem != null && !collNegativadorMovimentoRegItem.isEmpty()){
				  
				  totalQtdContas = collNegativadorMovimentoRegItem.size();
				  
				  Iterator iCollContas = collNegativadorMovimentoRegItem.iterator();
				  
				  while (iCollContas.hasNext()){
					  NegativadorMovimentoRegItem negMoviRegItem = (NegativadorMovimentoRegItem) iCollContas.next();
					  
					  totalValorDebitoConta = totalValorDebitoConta.add(negMoviRegItem.getValorDebito());
					  
					  if (negMoviRegItem.getContaGeral() != null && negMoviRegItem.getContaGeral().getConta() != null){
						  
						  totalValorConta = totalValorConta.add(negMoviRegItem.getContaGeral().getConta().getValorTotalContaBigDecimal());
						  
					  } else if (negMoviRegItem.getContaGeral() != null && negMoviRegItem.getContaGeral().getContaHistorico() != null){
						  
						  totalValorConta = totalValorConta.add(negMoviRegItem.getContaGeral().getContaHistorico().getValorTotalContaBigDecimal());
					  }
					  
				  }
				  
				  
			  }
			
			  sessao.setAttribute("totalQtdContas", totalQtdContas);
			  sessao.setAttribute("totalValorNegativadoConta", totalValorDebitoConta);
			  sessao.setAttribute("totalValorConta", totalValorConta);
			  
				//-----------------------------------------------------------------------------------------------
        		  FiltroNegativadorMovimentoRegItem filtroNMRIGuiaPagamento = new FiltroNegativadorMovimentoRegItem();
        		  
        		  if (negativadorMovimentoReg != null){
        			  filtroNMRIGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.NEGATIVADOR_MOVIMENTO_REG_ID,
    								new Integer(negativadorMovimentoReg.getId())));
    			  } else {
    				  filtroNMRIGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.NEGATIVADOR_MOVIMENTO_REG_ID,
    						new Integer(negativadorMovimentoRegID)));
    			  }				  
        		  filtroNMRIGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.DOCUMENTO_TIPO_ID,
						  DocumentoTipo.GUIA_PAGAMENTO));
					  
				  
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("negativadorMovimentoReg");
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("negativadorMovimentoReg.imovel");				  
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("guiaPagamentoGeral");				  
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("guiaPagamentoGeral.guiaPagamento");
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("guiaPagamentoGeral.guiaPagamentoHistorico");				  
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("guiaPagamentoGeral.guiaPagamento.debitoCreditoSituacaoAtual");
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral");
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.debitoCreditoSituacaoAtual");
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.debitoCreditoSituacaoAtual");


        		  Collection<NegativadorMovimentoRegItem> collNMIGuiaPagamento = fachada.pesquisar(filtroNMRIGuiaPagamento,
        				  NegativadorMovimentoRegItem.class.getName());	
        		  
        		  sessao.setAttribute("collNegativadorMovimentoRegItem2", collNMIGuiaPagamento);
        		  
        		  Integer totalQtdGuias = 0;

				  BigDecimal totalValorDebitoGuia = new BigDecimal("0.00");
				  BigDecimal totalValorGuia = new BigDecimal("0.00");
        		  
        		  if (collNMIGuiaPagamento != null && !collNMIGuiaPagamento.isEmpty()){
    				  
    				  totalQtdGuias = collNMIGuiaPagamento.size();
    				  
    				  Iterator iCollContas = collNMIGuiaPagamento.iterator();
    				  
    				  while (iCollContas.hasNext()){
    					  NegativadorMovimentoRegItem negMoviRegItem2 = (NegativadorMovimentoRegItem) iCollContas.next();
    					  
    					  totalValorDebitoGuia = totalValorDebitoGuia.add(negMoviRegItem2.getValorDebito());
    					  
    					  if (negMoviRegItem2.getGuiaPagamentoGeral() != null){
    						  
    						  if (negMoviRegItem2.getGuiaPagamentoGeral().getGuiaPagamento() != null){
    							  
    							  totalValorGuia = totalValorGuia.add(negMoviRegItem2.getGuiaPagamentoGeral().getGuiaPagamento().getValorDebito());
    						  } else if (negMoviRegItem2.getGuiaPagamentoGeral().getGuiaPagamentoHistorico() != null){
    							  
    							  totalValorGuia = totalValorGuia.add(negMoviRegItem2.getGuiaPagamentoGeral().getGuiaPagamentoHistorico().getValorDebito());
    						  }
    					  }
    					 
    					  
    				  }
    				  
    				  
    			  }
        		  sessao.setAttribute("totalValorNegativadoGuia", totalValorDebitoGuia);
				  sessao.setAttribute("totalValorGuia", totalValorGuia);
				  sessao.setAttribute("totalQtdGuias", totalQtdGuias);
				  
		}
		
		
		
		
		
		
		return retorno;
	}
	
}

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
package gcom.gui.cobranca.parcelamento;

import gcom.cobranca.parcelamento.ParcelamentoFaixaValor;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadePrestacao;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadePrestacaoHelper;
import gcom.gui.GcomAction;
import gcom.util.Util;

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
 * Action de remover um objeto do tipo PercentualFaixaValor
 * da collectionPercentualFaixaValor
 * 
 * @author Vivianne Sousa
 * @created 10/05/2006
 */
public class RemoverPercentualFaixaValorAction extends GcomAction {
	/**
	 * @author Vivianne Sousa
	 * @date 10/05/2006
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
    	
    	ActionForward retorno = actionMapping.findForward("inserirPerfilRemoverPercentualFaixaValor");	

    	atualizaColecoesNaSessao(sessao,httpServletRequest);
    	
    	String valorMaximo = httpServletRequest.getParameter("valorMaximo");

    	
    	//se tela de inserir antes do adicionar  Informa��es por Quantidade M�xima de Presta��es
        if (valorMaximo != null && !valorMaximo.equalsIgnoreCase("") && sessao.getAttribute("collectionParcelamentoFaixaValor") != null){
        	        	
        	Collection collectionParcelamentoFaixaValor = (Collection) sessao.getAttribute("collectionParcelamentoFaixaValor");
        	
    		ParcelamentoFaixaValor parcelamentoFaixaValor = null;
    		ParcelamentoFaixaValor parcelamentoFaixaValorExcluir = null;
			Iterator iterator = collectionParcelamentoFaixaValor.iterator();
						
			while (iterator.hasNext()) {
				parcelamentoFaixaValor = (ParcelamentoFaixaValor) iterator.next();
		
				//procura na cole��o o parcelamentoFaixaValor que tem a valorMaximo selecionado
				if (parcelamentoFaixaValor.getValorFaixa().toString().equals(valorMaximo)){
					parcelamentoFaixaValorExcluir =  parcelamentoFaixaValor;
				}
			}
			
			collectionParcelamentoFaixaValor.remove(parcelamentoFaixaValorExcluir);
       	 	sessao.setAttribute("collectionParcelamentoFaixaValor", 
       	 			collectionParcelamentoFaixaValor);
        	
        }
        
      //se vier do consultarrrrrrrrrrrrr   ???????????
      // tem q passar tb o identificador da cole��o de Informa��es por Quantidade M�xima de Presta��es   
        
//        String qtdeMaximaPrestacao = httpServletRequest.getParameter("qtdeMaximaPrestacao");
        
        
        
//        if (valorMaximo != null && !valorMaximo.equalsIgnoreCase("") && sessao.getAttribute("collectionParcelamentoFaixaValor") != null){
//        	
//        	Collection collectionParcelamentoFaixaValor = (Collection) sessao.getAttribute("collectionParcelamentoFaixaValor");
//        	
//        	//collectionParcelamentoFaixaValorLinhaRemovidas
//        	Collection collectionParcelamentoFaixaValorLinhaRemovidas = null;
//    		if (sessao.getAttribute("collectionParcelamentoFaixaValorLinhaRemovidas") != null
//    				&& !sessao.getAttribute("collectionParcelamentoFaixaValorLinhaRemovidas")
//    						.equals("")) {
//    			collectionParcelamentoFaixaValorLinhaRemovidas = (Collection) sessao
//    					.getAttribute("collectionParcelamentoFaixaValorLinhaRemovidas");
//    		} else {
//    			collectionParcelamentoFaixaValorLinhaRemovidas = new ArrayList();
//    		}
//        	
//    		ParcelamentoFaixaValor parcelamentoFaixaValor = null;
//    		ParcelamentoFaixaValor parcelamentoFaixaValorExcluir = null;
//			Iterator iterator = collectionParcelamentoFaixaValor.iterator();
//						
//			while (iterator.hasNext()) {
//				parcelamentoFaixaValor = (ParcelamentoFaixaValor) iterator.next();
//		
//				//procura na cole��o o parcelamentoFaixaValor que tem a valorMaximo selecionado
//				if (parcelamentoFaixaValor.getValorFaixa().toString().equals(valorMaximo)){
//					parcelamentoFaixaValorExcluir =  parcelamentoFaixaValor;
//					collectionParcelamentoFaixaValorLinhaRemovidas.add(parcelamentoFaixaValor);
//				}
//			}
//			
//			collectionParcelamentoFaixaValor.remove(parcelamentoFaixaValorExcluir);
//       	 	sessao.setAttribute("collectionParcelamentoFaixaValor", 
//       	 			collectionParcelamentoFaixaValor);
//       	 	sessao.setAttribute("collectionParcelamentoFaixaValorLinhaRemovidas", 
//       			collectionParcelamentoFaixaValorLinhaRemovidas);
//        	
//        }
        
        
       return retorno;
    }
    
    private void atualizaColecoesNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest){
    	     	
		// collectionParcelamentoQuantidadePrestacaoHelper
		if (sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper") != null
		&& !sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper").equals(
			"")) {
		
			Collection collectionParcelamentoQuantidadePrestacaoHelper = (Collection) sessao
			.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  ParcelamentoQuantidadePrestacao
			String txJuros = null;
			String percMinEntrada = null;
			String percTarMinImovel = null;
			String percVlReparcelado = null;
		
			Iterator iteratorParcelamentoQuantidadePrestacaoHelper = collectionParcelamentoQuantidadePrestacaoHelper
			.iterator();
		
			while (iteratorParcelamentoQuantidadePrestacaoHelper.hasNext()) {
				ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoHelper = 
					(ParcelamentoQuantidadePrestacaoHelper) iteratorParcelamentoQuantidadePrestacaoHelper.next();
				
				ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao = 
					parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao();
				
				long valorTempo = parcelamentoQuantidadePrestacao.getUltimaAlteracao()
					.getTime();
				
				txJuros = (String) httpServletRequest.getParameter("txJuros" + valorTempo);
				percMinEntrada = (String) httpServletRequest.getParameter("percMinEntrada" + valorTempo);
				percTarMinImovel = (String)httpServletRequest.getParameter("percTarMinImovel" + valorTempo);
				percVlReparcelado = (String)httpServletRequest.getParameter("percVlReparcelado" + valorTempo);
					
				// insere essas vari�veis no objeto ParcelamentoQuantidadePrestacao
				BigDecimal taxaJuros  = null;
				if (txJuros != null 
					&& !txJuros.equals("")) {
					taxaJuros = Util.formatarMoedaRealparaBigDecimal(txJuros);
				}
			
				BigDecimal percentualMinimoEntrada = null;
				if (percMinEntrada != null 
					&& !percMinEntrada.equals("")) {
					percentualMinimoEntrada = Util.formatarMoedaRealparaBigDecimal(percMinEntrada);
				}
					
				BigDecimal percentualTarifaMinimaImovel = null;
				if (percTarMinImovel != null 
					&& !percTarMinImovel.equals("")) {
					percentualTarifaMinimaImovel = Util.formatarMoedaRealparaBigDecimal(percTarMinImovel);
				}
				
				BigDecimal percentualValorReparcelado = null;
				if (percVlReparcelado != null 
					&& !percVlReparcelado.equals("")) {
					percentualValorReparcelado = Util.formatarMoedaRealparaBigDecimal(percVlReparcelado);
				}
							
				parcelamentoQuantidadePrestacao.setTaxaJuros(taxaJuros);
				parcelamentoQuantidadePrestacao.setPercentualMinimoEntrada(percentualMinimoEntrada);
				parcelamentoQuantidadePrestacao.setPercentualTarifaMinimaImovel(percentualTarifaMinimaImovel);
				parcelamentoQuantidadePrestacao.setPercentualValorReparcelado(percentualValorReparcelado);
				
				parcelamentoQuantidadePrestacaoHelper.setParcelamentoQuantidadePrestacao(parcelamentoQuantidadePrestacao);
				
			}
		}
		
		if(sessao.getAttribute("collectionParcelamentoFaixaValor") != null
				&& !sessao.getAttribute("collectionParcelamentoFaixaValor").equals(
				"")){
			
			Collection collectionParcelamentoFaixaValor = (Collection) sessao
			.getAttribute("collectionParcelamentoFaixaValor");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  ParcelamentoFaixaValor
			String perc = null;

			Iterator iteratorParcelamentoFaixaValor = collectionParcelamentoFaixaValor
			.iterator();
		
			while (iteratorParcelamentoFaixaValor.hasNext()) {
				ParcelamentoFaixaValor parcelamentoFaixaValor = (ParcelamentoFaixaValor) iteratorParcelamentoFaixaValor
					.next();
				long valorTempo = parcelamentoFaixaValor.getUltimaAlteracao()
					.getTime();
				
				perc = (String) httpServletRequest.getParameter("perc" + valorTempo);
				
				// insere essas vari�veis no objeto ParcelamentoFaixaValor
				BigDecimal percentual  = null;
				if (perc != null 
					&& !perc.equals("")) {
					percentual = Util.formatarMoedaRealparaBigDecimal(perc);
				}
			
				parcelamentoFaixaValor.setPercentualFaixa(percentual);
			
			}
		}
    }
    

}
 

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
package gsan.gui.cobranca.parcelamento;

import gsan.cobranca.parcelamento.ParcelamentoQuantidadePrestacao;
import gsan.cobranca.parcelamento.ParcelamentoQuantidadePrestacaoHelper;
import gsan.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamentoHelper;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
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

/**
 * Action respons�vel por adicionar na collectionParcelamentoQuantidadePrestacao
 * um ou mais objeto(s) do tipo ParcelamentoQuantidadePrestacao
 * 
 * @author Vivianne Sousa
 * @created 05/05/2006
 */
public class InserirPrestacoesParcelamentoPerfilAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclus�o de um novo perfil de parcelamento
	 * 
	 * [UC0220] Inserir Perfil de Parcelamento
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 05/05/2006
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
                .findForward("inserirPrestacoesParcelamentoPerfilAction");
    	HttpSession sessao = httpServletRequest.getSession(false);
    	
    	InserirPrestacoesParcelamentoPerfilActionForm form = (InserirPrestacoesParcelamentoPerfilActionForm) actionForm;
    	
    	atualizaColecaoNaSessao (sessao, httpServletRequest);
        Fachada fachada = Fachada.getInstancia();
        sessao.removeAttribute("collectionParcelamentoFaixaValor");
        
	    if (sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper") != null
				&& !sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper").equals(
						"")) {

			Collection collectionParcelamentoQuantidadePrestacaoHelper = (Collection) sessao
					.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  ParcelamentoDescontoInatividade
			String txJuros = null;
			String percMinEntrada = null;
			String percTarMinImovel = null;
			String percVlReparcelado = null;
			
			String fatCalculo = null;
			String indMediaConta = null;
			String indValorUltConta = null;
			String indGuiaPagPerEsp = null;
			String indEntMenPreParc = null;
			
			Iterator iteratorParcelamentoQuantidadePrestacao = collectionParcelamentoQuantidadePrestacaoHelper
					.iterator();
			
			while (iteratorParcelamentoQuantidadePrestacao.hasNext()) {
				ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoHelper = 
						(ParcelamentoQuantidadePrestacaoHelper) iteratorParcelamentoQuantidadePrestacao
						.next();
				
				ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao = 
					parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao();
				
				long valorTempo = parcelamentoQuantidadePrestacao.getUltimaAlteracao().getTime();
				txJuros = (String) httpServletRequest.getParameter("txJuros" + valorTempo);
				percMinEntrada = httpServletRequest.getParameter("percMinEntrada" + valorTempo);
				percTarMinImovel = httpServletRequest.getParameter("percTarMinImovel" + valorTempo);
				percVlReparcelado = (String)httpServletRequest.getParameter("percVlReparcelado" + valorTempo);
				
				fatCalculo = (String) httpServletRequest.getParameter("fatQtdPrest" + valorTempo);
				indMediaConta = (String) httpServletRequest.getParameter("indMedVlCnta" + valorTempo);
				indValorUltConta = (String) httpServletRequest.getParameter("indValorUltConta" + valorTempo);
				indGuiaPagPerEsp = (String) httpServletRequest.getParameter("indGuiaPagPerEsp" + valorTempo);
				indEntMenPreParc = (String) httpServletRequest.getParameter("indEntMenPreParc" + valorTempo);
				
				// insere essas vari�veis no objeto ParcelamentoDescontoInatividade
				BigDecimal taxaJuros  = null;
				if (txJuros != null 
						&& !txJuros.equals("")) {
					taxaJuros = Util.formatarMoedaRealparaBigDecimal(txJuros);
				}
				
				Integer fatorCalculo  = null;
				if (fatCalculo != null && !fatCalculo.equals("")) {
					fatorCalculo = new Integer(fatCalculo);
				}
				
				Short indicadorMediaConta  = ConstantesSistema.NAO;
				if (indMediaConta != null && !indMediaConta.equals("")) {
					indicadorMediaConta = new Short(indMediaConta);
				}
				Short indicadorValorUltConta  = ConstantesSistema.NAO;
				if (indValorUltConta!= null && !indValorUltConta.equals("")) {
					indicadorValorUltConta = new Short(indValorUltConta);
				}
				
				Short indicadorGuiaPagamentoPermissaoEspecial = ConstantesSistema.NAO;
				if (indGuiaPagPerEsp!= null && !indGuiaPagPerEsp.equals("")) {
					indicadorGuiaPagamentoPermissaoEspecial = new Short(indGuiaPagPerEsp);
				}
				
				Short indicadorEntradaMenorPrestacaoParcelamento = ConstantesSistema.NAO;
				if (indEntMenPreParc!= null && !indEntMenPreParc.equals("")) {
					indicadorEntradaMenorPrestacaoParcelamento = new Short(indEntMenPreParc);
				}
				
			    BigDecimal percentualMinimoEntrada = null;
			    BigDecimal percentualTarifaMinimaImovel = null;
			    BigDecimal percentualValorReparcelado = null;
			    
			    if ((percMinEntrada == null || percMinEntrada.equals("") || percMinEntrada.equals("0,00"))
	    		&& (percTarMinImovel == null || percTarMinImovel.equals("") || percTarMinImovel.equals("0,00"))){
			    	 percentualMinimoEntrada = new BigDecimal(0);
					 percentualTarifaMinimaImovel = new BigDecimal(0);
			    }else{
			    	
			    	if (percMinEntrada != null && !percMinEntrada.equals("")){
			    		percentualMinimoEntrada = Util.formatarMoedaRealparaBigDecimal(percMinEntrada);
			    	}else if (percTarMinImovel != null && !percTarMinImovel.equals("")){
			    		percentualTarifaMinimaImovel = Util.formatarMoedaRealparaBigDecimal(percTarMinImovel);
			    	}
			    }
			    
			    if (percVlReparcelado == null || percVlReparcelado.equals("") || percVlReparcelado.equals("0,00")){
				    percentualValorReparcelado = new BigDecimal(0);
			   }else{
					percentualValorReparcelado =Util.formatarMoedaRealparaBigDecimal(percVlReparcelado);
			   }
			 
			    
				parcelamentoQuantidadePrestacao.setTaxaJuros(taxaJuros);
				parcelamentoQuantidadePrestacao.setPercentualMinimoEntrada(percentualMinimoEntrada);
				parcelamentoQuantidadePrestacao.setPercentualTarifaMinimaImovel(percentualTarifaMinimaImovel);
				parcelamentoQuantidadePrestacao.setPercentualValorReparcelado(percentualValorReparcelado);
				parcelamentoQuantidadePrestacaoHelper.setParcelamentoQuantidadePrestacao(parcelamentoQuantidadePrestacao);
				
				parcelamentoQuantidadePrestacao.setFatorQuantidadePrestacoes(fatorCalculo);
				parcelamentoQuantidadePrestacao.setIndicadorMediaValorContas(indicadorMediaConta);
				parcelamentoQuantidadePrestacao.setIndicadorValorUltimaContaEmAtraso(indicadorValorUltConta);
				parcelamentoQuantidadePrestacao.setIndicadorGuiaPagamentoEntradaEspecial(indicadorGuiaPagamentoPermissaoEspecial);
				parcelamentoQuantidadePrestacao.setIndicadorEntradaMenorPrestacaoParcelamento(indicadorEntradaMenorPrestacaoParcelamento);
				//sessao.setAttribute("collectionParcelamentoQuantidadePrestacao",collectionParcelamentoQuantidadePrestacao);
			}
			

			validacaoFinal( collectionParcelamentoQuantidadePrestacaoHelper,fachada);			
			
	        Collection collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) sessao
	                    .getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
	        
	        Collection collectionParcelamentoQuantidadeReparcelamentoHelper1 = new ArrayList();
	                
	        Iterator iterator = collectionParcelamentoQuantidadeReparcelamentoHelper.iterator();

			while (iterator.hasNext()) {
				ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQuantidadeReparcelamentoHelper = 
					(ParcelamentoQuantidadeReparcelamentoHelper) iterator.next();

				if (parcelamentoQuantidadeReparcelamentoHelper.getQuantidadeMaximaReparcelamento().toString()
						.equals((String)sessao.getAttribute("qtdeMaxReparcelamento"))) {
					parcelamentoQuantidadeReparcelamentoHelper.
						setCollectionParcelamentoQuantidadePrestacaoHelper(collectionParcelamentoQuantidadePrestacaoHelper);
					parcelamentoQuantidadeReparcelamentoHelper.
						setInformacaoParcelamentoQtdeReparcelamento("INFORMADAS");
										
				}
				collectionParcelamentoQuantidadeReparcelamentoHelper1.add(parcelamentoQuantidadeReparcelamentoHelper);
			}
			
			sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper",
					collectionParcelamentoQuantidadeReparcelamentoHelper1);
			
			if (sessao.getAttribute("UseCase")!= null &&
	    			sessao.getAttribute("UseCase").equals("INSERIRPERFIL")){
				httpServletRequest.setAttribute("reloadPage","INSERIRPERFIL");
	    	}else{
	    		httpServletRequest.setAttribute("reloadPage","ATUALIZARPERFIL");
	    	}
						
			
        }	        	

        return retorno;
    }

    /**
     * 
     * @param sessao
     * @param httpServletRequest
     */
    private void atualizaColecaoNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest){

    	//collectionParcelamentoQuantidadePrestacao
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
			
			String fatCalculo = null;
			String indMediaConta = null;
			String indValorUltConta = null;
			String indGuiaPagPerEsp = null;
			String indEntMenPreParc = null;
			
			Iterator iterator = collectionParcelamentoQuantidadePrestacaoHelper
					.iterator();
			
			while (iterator.hasNext()) {
				ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoHelper = 
						(ParcelamentoQuantidadePrestacaoHelper) iterator.next();
				
				ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao =
					parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao();
				
				long valorTempo = parcelamentoQuantidadePrestacao.getUltimaAlteracao()
						.getTime();
				
				txJuros = (String) httpServletRequest.getParameter("txJuros" + valorTempo);
				percMinEntrada = (String) httpServletRequest.getParameter("percMinEntrada" + valorTempo);
				percTarMinImovel = (String)httpServletRequest.getParameter("percTarMinImovel" + valorTempo);
				
				fatCalculo = (String) httpServletRequest.getParameter("fatQtdPrest" + valorTempo);
				indMediaConta = (String) httpServletRequest.getParameter("indMedVlCnta" + valorTempo);
				indValorUltConta = (String) httpServletRequest.getParameter("indValorUltConta" + valorTempo);
				indGuiaPagPerEsp = (String) httpServletRequest.getParameter("indGuiaPagPerEsp" + valorTempo);
				indEntMenPreParc = (String) httpServletRequest.getParameter("indEntMenPreParc" + valorTempo);
				
				
				// insere essas vari�veis no objeto ParcelamentoQuantidadePrestacao
				BigDecimal taxaJuros  = null;
				if (txJuros != null 
						&& !txJuros.equals("")) {
					taxaJuros = Util.formatarMoedaRealparaBigDecimal(txJuros);
				}
				
				Integer fatorCalculo  = null;
				if (fatCalculo != null && !fatCalculo.equals("")) {
					fatorCalculo = new Integer(fatCalculo);
				}
				
				Short indicadorMediaConta  = ConstantesSistema.NAO;
				if (indMediaConta != null && !indMediaConta.equals("")) {
					indicadorMediaConta = new Short(indMediaConta);
				}
				
				Short indicadorValorUltConta  = ConstantesSistema.NAO;
				if (indValorUltConta!= null && !indValorUltConta.equals("")) {
					indicadorValorUltConta = new Short(indValorUltConta);
				}
				
				Short indicadorGuiaPagamentoPermissaoEspecial = ConstantesSistema.NAO;
				if (indGuiaPagPerEsp!= null && !indGuiaPagPerEsp.equals("")) {
					indicadorGuiaPagamentoPermissaoEspecial = new Short(indGuiaPagPerEsp);
				}
				
				Short indicadorEntradaMenorPrestacaoParcelamento = ConstantesSistema.NAO;
				if (indEntMenPreParc!= null && !indEntMenPreParc.equals("")) {
					indicadorEntradaMenorPrestacaoParcelamento = new Short(indEntMenPreParc);
				}
				
				
				BigDecimal percentualMinimoEntrada = null;
			    BigDecimal percentualTarifaMinimaImovel = null;
			    
			    
			    if ((percMinEntrada == null || percMinEntrada.equals("") || percMinEntrada.equals(0))
			    		&& (percTarMinImovel == null || percTarMinImovel.equals("") || percTarMinImovel.equals(0))){
			    	 percentualMinimoEntrada = new BigDecimal(0);
					 percentualTarifaMinimaImovel = new BigDecimal(0);
			    }else{
			    	
			    	if (percMinEntrada != null && !percMinEntrada.equals("")){
			    		percentualMinimoEntrada = Util.formatarMoedaRealparaBigDecimal(percMinEntrada);
			    	}else if (percTarMinImovel != null && !percTarMinImovel.equals("")){
			    		percentualTarifaMinimaImovel = Util.formatarMoedaRealparaBigDecimal(percTarMinImovel);
			    	}
			    }
								
				parcelamentoQuantidadePrestacao.setTaxaJuros(taxaJuros);
				parcelamentoQuantidadePrestacao.setPercentualMinimoEntrada(percentualMinimoEntrada);
				parcelamentoQuantidadePrestacao.setPercentualTarifaMinimaImovel(percentualTarifaMinimaImovel);
				
				parcelamentoQuantidadePrestacao.setFatorQuantidadePrestacoes(fatorCalculo);
				parcelamentoQuantidadePrestacao.setIndicadorMediaValorContas(indicadorMediaConta);
				parcelamentoQuantidadePrestacao.setIndicadorValorUltimaContaEmAtraso(indicadorValorUltConta);
				parcelamentoQuantidadePrestacao.setIndicadorGuiaPagamentoEntradaEspecial(indicadorGuiaPagamentoPermissaoEspecial);
				parcelamentoQuantidadePrestacao.setIndicadorEntradaMenorPrestacaoParcelamento(indicadorEntradaMenorPrestacaoParcelamento);
				
				parcelamentoQuantidadePrestacaoHelper.setParcelamentoQuantidadePrestacao(parcelamentoQuantidadePrestacao);
			}
        }	
    
	}
    
    /**
     * 
     * @param collectionParcelamentoQuantidadePrestacaoHelper
     * @param fachada
     */
    private void validacaoFinal(Collection collectionParcelamentoQuantidadePrestacaoHelper,
    		Fachada fachada){
    	

    	Iterator iteratorParcelamentoQuantidadePrestacaoHelper = collectionParcelamentoQuantidadePrestacaoHelper.iterator();

		 while (iteratorParcelamentoQuantidadePrestacaoHelper.hasNext()) {
			
			 ParcelamentoQuantidadePrestacaoHelper  parcelamentoQuantidadePrestacaoHelper = 
				 			(ParcelamentoQuantidadePrestacaoHelper) iteratorParcelamentoQuantidadePrestacaoHelper.next();
			 
			 ParcelamentoQuantidadePrestacao  parcelamentoQuantidadePrestacao = 
				 parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao();
			 
			if(parcelamentoQuantidadePrestacao.getQuantidadeMaximaPrestacao() == null){
				throw new ActionServletException(
						// Informe Quantidade M�xima de Presta��es
						"atencao.required", null, " Quantidade M�xima de Presta��es");	
			}
			
			if(parcelamentoQuantidadePrestacao.getTaxaJuros() == null){
				throw new ActionServletException(
						// Informe Taxa de Juros a.m
						"atencao.required", null, " Taxa de Juros a.m");	
			}
		}
    }
}

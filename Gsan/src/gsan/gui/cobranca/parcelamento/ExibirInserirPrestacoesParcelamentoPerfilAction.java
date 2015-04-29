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

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.cadastro.sistemaparametro.FiltroSistemaParametro;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.parcelamento.FiltroParcelamentoQuantidadePrestacaoSituacaoLigacaoAgua;
import gsan.cobranca.parcelamento.ParcDesctoInativVista;
import gsan.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gsan.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gsan.cobranca.parcelamento.ParcelamentoFaixaValor;
import gsan.cobranca.parcelamento.ParcelamentoQuantidadePrestacao;
import gsan.cobranca.parcelamento.ParcelamentoQuantidadePrestacaoHelper;
import gsan.cobranca.parcelamento.ParcelamentoQuantidadePrestacaoSituacaoLigacaoAgua;
import gsan.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamentoHelper;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pr�-processamento da p�gina de 
 * inserir Informa��es do Parcelamento por Quantidade de Reparcelamentos
 * 
 * @author Vivianne Sousa
 * @created 03/05/2006
 */

public class ExibirInserirPrestacoesParcelamentoPerfilAction  extends GcomAction {
	
	/**
	 * Este caso de uso permite a inclus�o de um novo perfil de parcelamento
	 * 
	 * [UC0220] Inserir Perfil de Parcelamento
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 03/05/2006
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

        ActionForward retorno = actionMapping.findForward("inserirPrestacoesParcelamentoPerfil");
        InserirPrestacoesParcelamentoPerfilActionForm inserirPrestacoesParcelamentoPerfilActionForm = (InserirPrestacoesParcelamentoPerfilActionForm) actionForm;
        HttpSession sessao = httpServletRequest.getSession(false);
        Fachada fachada = Fachada.getInstancia();
        
        sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper");
        
        String quantidadeReparcelamento = (String)httpServletRequest.getParameter("qtdeMaximaReparcelamento");
        String percentualEntradaSugerida = (String)httpServletRequest.getParameter("percentualEntradaSugerida");
        
        String readOnly = (String) httpServletRequest.getParameter("readOnly");
        httpServletRequest.setAttribute("readOnly",readOnly);
        
        Boolean percentualValorReparceladoReadOnly = false;
        
        //filtro para descobrir o percentual m�nimo de entrada permitido para financiamento
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		Collection colecaoSistemaParametros;

			colecaoSistemaParametros = fachada.pesquisar(
					filtroSistemaParametro, SistemaParametro.class.getName());
		SistemaParametro sistemaParametro = (SistemaParametro)colecaoSistemaParametros
				.iterator().next();
		String percentualFinanciamentoEntradaMinima = "" + sistemaParametro.getPercentualFinanciamentoEntradaMinima();
		httpServletRequest.setAttribute("percentualFinanciamentoEntradaMinima",percentualFinanciamentoEntradaMinima);
		
		if (httpServletRequest.getParameter("adicionarPrestacao") != null
                && httpServletRequest.getParameter("adicionarPrestacao").equalsIgnoreCase("S")
                && inserirPrestacoesParcelamentoPerfilActionForm.getQuantidadeMaximaPrestacao() != null) {
        	
        	// --------------------  bt adicionarPrestacao ----------------------
    	
        	atualizaColecaoParcelamentoQuantidadePrestacaoNaSessao(sessao,httpServletRequest);
        	
        	atualizaColecaoParcelamentoFaixaValorNaSessao( sessao, httpServletRequest);
        	
        	adicionarParcelamentoQuantidadePrestacao ( sessao, fachada,
        			inserirPrestacoesParcelamentoPerfilActionForm,percentualValorReparceladoReadOnly);        	        	
        	
        }else {
        	 if (quantidadeReparcelamento != null && !quantidadeReparcelamento.equals("")
             		&& httpServletRequest.getParameter("primeiraVez").equals("S")){
             	
             	atualizaColecoesNaSessao(sessao,httpServletRequest);

             	if (httpServletRequest.getParameter("adicionarReparcelamento") != null &&
             			httpServletRequest.getParameter("adicionarReparcelamento").equals("S")	){
             		adicionarParcelamentoQuantidadeReparcelamentoHelper(
             				quantidadeReparcelamento, sessao, percentualEntradaSugerida);
             	}
             	atualizaFormNaSessao(sessao,httpServletRequest);
             	
             	//qd entra na tela pela primeira vez
             	limparTela(sessao,inserirPrestacoesParcelamentoPerfilActionForm);
             	
             	sessao.setAttribute("qtdeMaxReparcelamento",quantidadeReparcelamento);
             	
             	 if (quantidadeReparcelamento!= null && quantidadeReparcelamento.equals("0")){
                	//s� liberar Percentual Valor Reparcelado para informa��o 
                 //caso a qtde de reparcelamentos consecutivos seja maior que zero(0)
                 	percentualValorReparceladoReadOnly = true;
                 	sessao.setAttribute("quantidadeReparcelamento",quantidadeReparcelamento);
             	 }
                 sessao.setAttribute("percentualValorReparceladoReadOnly",percentualValorReparceladoReadOnly);
//                 httpServletRequest.setAttribute("percentualValorReparceladoReadOnly","" + percentualValorReparceladoReadOnly);
                 
             	
     	    }
        	 
        	 Collection collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) sessao
             .getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
         
 			Iterator iterator = collectionParcelamentoQuantidadeReparcelamentoHelper.iterator();
 			
 			while (iterator.hasNext()) {
 				ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQuantidadeReparcelamentoHelper = 
 					(ParcelamentoQuantidadeReparcelamentoHelper) iterator.next();
 			
 				if (parcelamentoQuantidadeReparcelamentoHelper.getQuantidadeMaximaReparcelamento().toString()
 						.equals((String)sessao.getAttribute("qtdeMaxReparcelamento"))) {
 					
 					Collection collectionParcelamentoQuantidadePrestacaoHelper = null;
 					
 					if (sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper") != null){
 						//volta do RemoverParcelamentoQuantidadePrestacaoAction 
 						collectionParcelamentoQuantidadePrestacaoHelper = (Collection)sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper");
 						montarHabilitaCampos(collectionParcelamentoQuantidadePrestacaoHelper);
 					}else{
 						//chamado a partir da tela do inserir ou atualizar Perfil de Parcelamento
 						collectionParcelamentoQuantidadePrestacaoHelper = parcelamentoQuantidadeReparcelamentoHelper.getCollectionParcelamentoQuantidadePrestacaoHelper();
 						montarLigacoesAgua(collectionParcelamentoQuantidadePrestacaoHelper);
 						montarHabilitaCampos(collectionParcelamentoQuantidadePrestacaoHelper);
 					}
 					 						
 					//parcelamentoQuantidadeReparcelamentoHelper.getCollectionParcelamentoQuantidadePrestacao();

 					if (collectionParcelamentoQuantidadePrestacaoHelper == null || collectionParcelamentoQuantidadePrestacaoHelper.isEmpty()) {
 						parcelamentoQuantidadeReparcelamentoHelper.
 						setInformacaoParcelamentoQtdeReparcelamento("N�O INFORMADAS");
 					}
 					
 					httpServletRequest.setAttribute("collectionParcelamentoQuantidadePrestacaoHelper",collectionParcelamentoQuantidadePrestacaoHelper);
 					sessao.setAttribute("collectionParcelamentoQuantidadePrestacaoHelper",collectionParcelamentoQuantidadePrestacaoHelper);
 				}				
 			}
 

        }
        
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
    	filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.SIM));
    	
    	Collection colLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
    	
    	httpServletRequest.setAttribute("colLigacaoAguaSituacao", colLigacaoAguaSituacao);
    	
		if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")){
        	// limpar tela         	
        	limparTela(sessao,inserirPrestacoesParcelamentoPerfilActionForm);
        }
        
        if (httpServletRequest.getParameter("fechar") != null
                && httpServletRequest.getParameter("fechar").equalsIgnoreCase("S")){
        	//antes de fechar a tela 
        	//atualiza a colec�o collectionParcelamentoQuantidadePrestacaoHelper na sessao 
         	atualizaColecaoParcelamentoQuantidadePrestacaoNaSessao(sessao,httpServletRequest);    
         	if (sessao.getAttribute("UseCase")!= null &&
	    			sessao.getAttribute("UseCase").equals("INSERIRPERFIL")){
				httpServletRequest.setAttribute("reloadPage","FECHARINSERIR");
	    	}else{
	    		httpServletRequest.setAttribute("reloadPage","FECHARATUALIZAR");
	    	}
        }
        
        httpServletRequest.getAttribute("reloadPage");
        return retorno;
    }
    
    private void montarLigacoesAgua(Collection collectionParcelamentoQuantidadePrestacaoHelper){
    	if(!Util.isVazioOrNulo(collectionParcelamentoQuantidadePrestacaoHelper)){
    		Iterator iterator = collectionParcelamentoQuantidadePrestacaoHelper.iterator();
    		while(iterator.hasNext()){
    			ParcelamentoQuantidadePrestacaoHelper parcelamentoHelper = (ParcelamentoQuantidadePrestacaoHelper) iterator.next();
    			if(parcelamentoHelper.getLigacoesAgua() == null || parcelamentoHelper.getLigacoesAgua().equals("")){
    				String ligacoesAgua = "";
    				if(parcelamentoHelper.getParcelamentoQuantidadePrestacao() != null){
    					Integer idParcelamento = parcelamentoHelper.getParcelamentoQuantidadePrestacao().getId();
    					FiltroParcelamentoQuantidadePrestacaoSituacaoLigacaoAgua filtroParcelamentoLigacaoAgua = 
    						new FiltroParcelamentoQuantidadePrestacaoSituacaoLigacaoAgua();
    					filtroParcelamentoLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroParcelamentoQuantidadePrestacaoSituacaoLigacaoAgua.PARCELAMENTO_PRESTACAO_ID, idParcelamento));
    					filtroParcelamentoLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
    					filtroParcelamentoLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("parcelamentoQuantidadePrestacao");
    					
    					Collection colParcelamentoLigacoesAgua = getFachada().pesquisar(filtroParcelamentoLigacaoAgua, ParcelamentoQuantidadePrestacaoSituacaoLigacaoAgua.class.getName());
    					
    					if(!Util.isVazioOrNulo(colParcelamentoLigacoesAgua)){
    						Iterator iteratorAgua = colParcelamentoLigacoesAgua.iterator();
    						while(iteratorAgua.hasNext()){
    							ParcelamentoQuantidadePrestacaoSituacaoLigacaoAgua parcelamentoSituacaoAgua = (ParcelamentoQuantidadePrestacaoSituacaoLigacaoAgua) iteratorAgua.next();
    							
    							if(parcelamentoSituacaoAgua.getLigacaoAguaSituacao() != null && !parcelamentoSituacaoAgua.getLigacaoAguaSituacao().equals("")){
    								ligacoesAgua = parcelamentoSituacaoAgua.getLigacaoAguaSituacao().getDescricao() ;
    							}
    						}
    					}
    				}
    				if(ligacoesAgua.equals("")){
    					parcelamentoHelper.setLigacoesAgua("Sem Ligacoes Agua");
    				}else{
    					parcelamentoHelper.setLigacoesAgua(ligacoesAgua);
    				}
    			}
    		}
    	}
    }
    
    private void montarHabilitaCampos(Collection collectionParcelamentoQuantidadePrestacaoHelper){
    	if(!Util.isVazioOrNulo(collectionParcelamentoQuantidadePrestacaoHelper)){
    		Iterator iterator = collectionParcelamentoQuantidadePrestacaoHelper.iterator();
    		while(iterator.hasNext()){
    			ParcelamentoQuantidadePrestacaoHelper parcelamentoHelper = (ParcelamentoQuantidadePrestacaoHelper) iterator.next();
    			ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao = parcelamentoHelper.getParcelamentoQuantidadePrestacao();
    			if(parcelamentoQuantidadePrestacao != null){
    				if(String.valueOf(parcelamentoQuantidadePrestacao.getIndicadorMediaValorContas()).equals("1")
        					|| String.valueOf(parcelamentoQuantidadePrestacao.getIndicadorValorUltimaContaEmAtraso()).equals("1")){
    					parcelamentoHelper.setHabilitado("1");
    				}else{
    					parcelamentoHelper.setHabilitado("2");
    				}
    				
    				if(parcelamentoQuantidadePrestacao.getPercentualMinimoEntrada() == null){
    					parcelamentoQuantidadePrestacao.setPercentualMinimoEntrada(new BigDecimal("0"));
    				}
    				
    				if(parcelamentoQuantidadePrestacao.getPercentualTarifaMinimaImovel() == null){
    					parcelamentoQuantidadePrestacao.setPercentualTarifaMinimaImovel(new BigDecimal("0"));
    				}
    			}
    		}
    	}
    }
    
    private void limparTela(HttpSession sessao,
    		InserirPrestacoesParcelamentoPerfilActionForm inserirPrestacoesParcelamentoPerfilActionForm){
    	
    	inserirPrestacoesParcelamentoPerfilActionForm.setPercentualMinimoEntrada("");
    	inserirPrestacoesParcelamentoPerfilActionForm.setPercentualTarifaMinimaImovel("");
    	inserirPrestacoesParcelamentoPerfilActionForm.setQuantidadeMaximaPrestacao("");
    	inserirPrestacoesParcelamentoPerfilActionForm.setTaxaJuros("");
    	inserirPrestacoesParcelamentoPerfilActionForm.setPercentualValorReparcelado("");
    	inserirPrestacoesParcelamentoPerfilActionForm.setQuantidadeMaxPrestacaoEspecial("");
    	
    	inserirPrestacoesParcelamentoPerfilActionForm.setFatorQuantidadePrestacoes("");
    	inserirPrestacoesParcelamentoPerfilActionForm.setIndicadorMediaValorContas(ConstantesSistema.NAO.toString());
    	inserirPrestacoesParcelamentoPerfilActionForm.setIndicadorValorUltimaContaEmAtraso(ConstantesSistema.NAO.toString());
    	inserirPrestacoesParcelamentoPerfilActionForm.setIndicadorGuiaPagamentoPermissaoEspecial(ConstantesSistema.NAO.toString());
    	inserirPrestacoesParcelamentoPerfilActionForm.setIndicadorEntradaMenorPrestacaoParcelamento(ConstantesSistema.NAO.toString());
    	
    	sessao.removeAttribute("collectionParcelamentoQuantidadePrestacaoHelper");
    	sessao.removeAttribute("collectionParcelamentoFaixaValor");
    }
    
    private void adicionarParcelamentoQuantidadePrestacao (HttpSession sessao,Fachada fachada,
    		InserirPrestacoesParcelamentoPerfilActionForm inserirPrestacoesParcelamentoPerfilActionForm,
    		Boolean percentualValorReparceladoReadOnly){

    	if (inserirPrestacoesParcelamentoPerfilActionForm.getQuantidadeMaximaPrestacao() == null ||
    			inserirPrestacoesParcelamentoPerfilActionForm.getQuantidadeMaximaPrestacao().equalsIgnoreCase("")){
    			throw new ActionServletException(
					// Informe  Quantidade M�xima de Presta��es
					"atencao.required", null, " Quantidade M�xima de Presta��es");	
    	}else if( Util.validarValorNaoNumericoComoBigDecimal(inserirPrestacoesParcelamentoPerfilActionForm.getQuantidadeMaximaPrestacao())){
			throw new ActionServletException(
					// Quantidade M�xima de Presta��es deve ser numerico
					"atencao.integer", null, " Quantidade M�xima de Presta��es");
		 }
    	
    	Collection<ParcelamentoQuantidadePrestacaoHelper> collectionParcelamentoQuantidadePrestacaoHelper = null;
        
        if (sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper") != null) {
        	collectionParcelamentoQuantidadePrestacaoHelper = (Collection) sessao
                    .getAttribute("collectionParcelamentoQuantidadePrestacaoHelper");
        } else {
        	collectionParcelamentoQuantidadePrestacaoHelper = new ArrayList();
        }
    	
		Short qtdeMaximaPrestacao = new Short(inserirPrestacoesParcelamentoPerfilActionForm.getQuantidadeMaximaPrestacao());
		Short qtdeMaxPrestacaoPermissaoEspecial = null;
		
		if (inserirPrestacoesParcelamentoPerfilActionForm.getQuantidadeMaxPrestacaoEspecial() != null && 
				!inserirPrestacoesParcelamentoPerfilActionForm.getQuantidadeMaxPrestacaoEspecial().equals("")){
			qtdeMaxPrestacaoPermissaoEspecial = new Short(inserirPrestacoesParcelamentoPerfilActionForm.getQuantidadeMaxPrestacaoEspecial());
		}
		
		Integer fatorQuantidadePrestacoes = null;
//	    if (inserirPrestacoesParcelamentoPerfilActionForm.getFatorQuantidadePrestacoes() == null
//	    	|| inserirPrestacoesParcelamentoPerfilActionForm.getFatorQuantidadePrestacoes().equalsIgnoreCase("")){
	    	//Informe Fator C�lculo Qtd. Presta��es Parc.
//	    	throw new ActionServletException(					
//					"atencao.required", null, "  Fator C�lculo Qtd. Presta��es Parc.");	
//	    }else{	
	    	 if (inserirPrestacoesParcelamentoPerfilActionForm.getFatorQuantidadePrestacoes() != null
	    		    	&& !inserirPrestacoesParcelamentoPerfilActionForm.getFatorQuantidadePrestacoes().equalsIgnoreCase("")){
	    	
	    	fatorQuantidadePrestacoes = new Integer(inserirPrestacoesParcelamentoPerfilActionForm.getFatorQuantidadePrestacoes());
		}
	    
		BigDecimal taxaJuros = null;
	    if (inserirPrestacoesParcelamentoPerfilActionForm.getTaxaJuros()== null
	    	|| inserirPrestacoesParcelamentoPerfilActionForm.getTaxaJuros().equalsIgnoreCase("")){
	    	//Informe Taxa de Juros a.m.
	    	throw new ActionServletException(					
					"atencao.required", null, "  Taxa de Juros a.m.");	
	    }else{	
			taxaJuros = Util.formatarMoedaRealparaBigDecimal(inserirPrestacoesParcelamentoPerfilActionForm.getTaxaJuros());
		}
	    
	    Short indicadorMediaValorContas = new Short(inserirPrestacoesParcelamentoPerfilActionForm.getIndicadorMediaValorContas());
	    Short indicadorValorUltimaContaEmAtraso = new Short(inserirPrestacoesParcelamentoPerfilActionForm.getIndicadorValorUltimaContaEmAtraso());
	    Short indicadorGuiaPagamentoEntradaEspecial = new Short(inserirPrestacoesParcelamentoPerfilActionForm.getIndicadorGuiaPagamentoPermissaoEspecial());
	    Short indicadorEntradaMenorPrestacaoParcelamento = new Short(inserirPrestacoesParcelamentoPerfilActionForm.getIndicadorEntradaMenorPrestacaoParcelamento());
	    
	    BigDecimal percentualMinimoEntrada = null;
	    BigDecimal percentualTarifaMinimaImovel = null;
	    BigDecimal percentualValorReparcelado = null;
	    
	    if ((inserirPrestacoesParcelamentoPerfilActionForm.getPercentualMinimoEntrada()== null
	    	|| inserirPrestacoesParcelamentoPerfilActionForm.getPercentualMinimoEntrada().equalsIgnoreCase("")
	    	|| inserirPrestacoesParcelamentoPerfilActionForm.getPercentualMinimoEntrada().equals(0))
	    	&&(inserirPrestacoesParcelamentoPerfilActionForm.getPercentualTarifaMinimaImovel()== null
	    	    	|| inserirPrestacoesParcelamentoPerfilActionForm.getPercentualTarifaMinimaImovel().equalsIgnoreCase("")
	    	    	|| inserirPrestacoesParcelamentoPerfilActionForm.getPercentualTarifaMinimaImovel().equals(0))){
	    	
		    percentualMinimoEntrada = new BigDecimal(0);
		    percentualTarifaMinimaImovel = new BigDecimal(0);

	    }else{
	    	
	    	 if (inserirPrestacoesParcelamentoPerfilActionForm.getPercentualMinimoEntrada()!= null
	    		    	&& !inserirPrestacoesParcelamentoPerfilActionForm.getPercentualMinimoEntrada().equalsIgnoreCase("")){
	    		 
	    		 percentualMinimoEntrada = Util.formatarMoedaRealparaBigDecimal
	    		 		(inserirPrestacoesParcelamentoPerfilActionForm.getPercentualMinimoEntrada());
	    		 
	    	 }else if (inserirPrestacoesParcelamentoPerfilActionForm.getPercentualTarifaMinimaImovel()!= null
		    	    	&& !inserirPrestacoesParcelamentoPerfilActionForm.getPercentualTarifaMinimaImovel().equalsIgnoreCase("")){
	    		 
	    		 percentualTarifaMinimaImovel = Util.formatarMoedaRealparaBigDecimal
	    		 		(inserirPrestacoesParcelamentoPerfilActionForm.getPercentualTarifaMinimaImovel());
	    	 }
			
		}
		
	   if (inserirPrestacoesParcelamentoPerfilActionForm.getPercentualValorReparcelado() == null ||
		   inserirPrestacoesParcelamentoPerfilActionForm.getPercentualValorReparcelado().equals("") ||
		   inserirPrestacoesParcelamentoPerfilActionForm.getPercentualValorReparcelado().equals("0,00")){
		   percentualValorReparcelado = new BigDecimal(0);
	   }else{
			percentualValorReparcelado =Util.formatarMoedaRealparaBigDecimal(
					inserirPrestacoesParcelamentoPerfilActionForm.getPercentualValorReparcelado());
	   }
	   
	    // vivi !!!!!!! aceita zero???? 
	    // insere como null ou zero??
//		if (inserirPrestacoesParcelamentoPerfilActionForm.getPercentualValorReparcelado() != null
//				&& !inserirPrestacoesParcelamentoPerfilActionForm.getPercentualValorReparcelado().equals("")
//				&& !inserirPrestacoesParcelamentoPerfilActionForm.getPercentualValorReparcelado().equals(0)){
//			percentualValorReparcelado =Util.formatarMoedaRealparaBigDecimal(
//					inserirPrestacoesParcelamentoPerfilActionForm.getPercentualValorReparcelado());
//		}
	    
	    ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacaoNovo = 
										new ParcelamentoQuantidadePrestacao();
		 
		if (collectionParcelamentoQuantidadePrestacaoHelper != null && !collectionParcelamentoQuantidadePrestacaoHelper.isEmpty()){
			// se cole��o n�o estiver vazia
			// verificar se a qtd m�xima de presta��es do parcelamento ja foi informada	
			ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoAntigoHelper = null;
			Iterator iterator = collectionParcelamentoQuantidadePrestacaoHelper.iterator();
		
			while (iterator.hasNext()) {
				parcelamentoQuantidadePrestacaoAntigoHelper = (ParcelamentoQuantidadePrestacaoHelper) iterator.next();
				
				if (qtdeMaximaPrestacao.equals
						(parcelamentoQuantidadePrestacaoAntigoHelper.getParcelamentoQuantidadePrestacao().getQuantidadeMaximaPrestacao())){
					//Quantidade M�xima de Presta��es j� informada
					throw new ActionServletException(
					"atencao.qtde_maxima_prestacoes_ja_informado");
				}
			}
		}
		
		parcelamentoQuantidadePrestacaoNovo.setQuantidadeMaximaPrestacao(qtdeMaximaPrestacao);
		parcelamentoQuantidadePrestacaoNovo.setQuantidadeMaxPrestacaoEspecial(qtdeMaxPrestacaoPermissaoEspecial);
		parcelamentoQuantidadePrestacaoNovo.setTaxaJuros(taxaJuros);
		parcelamentoQuantidadePrestacaoNovo.setPercentualMinimoEntrada(percentualMinimoEntrada);
		parcelamentoQuantidadePrestacaoNovo.setPercentualTarifaMinimaImovel(percentualTarifaMinimaImovel);
		parcelamentoQuantidadePrestacaoNovo.setPercentualValorReparcelado(percentualValorReparcelado);
		parcelamentoQuantidadePrestacaoNovo.setUltimaAlteracao(new Date());
		
		parcelamentoQuantidadePrestacaoNovo.setFatorQuantidadePrestacoes(fatorQuantidadePrestacoes);
		parcelamentoQuantidadePrestacaoNovo.setIndicadorMediaValorContas(indicadorMediaValorContas);
		parcelamentoQuantidadePrestacaoNovo.setIndicadorValorUltimaContaEmAtraso(indicadorValorUltimaContaEmAtraso);
		parcelamentoQuantidadePrestacaoNovo.setIndicadorGuiaPagamentoEntradaEspecial(indicadorGuiaPagamentoEntradaEspecial);
		parcelamentoQuantidadePrestacaoNovo.setIndicadorEntradaMenorPrestacaoParcelamento(indicadorEntradaMenorPrestacaoParcelamento);
		
		ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoHelperNovo =
			new ParcelamentoQuantidadePrestacaoHelper();
		
		parcelamentoQuantidadePrestacaoHelperNovo.setParcelamentoQuantidadePrestacao(parcelamentoQuantidadePrestacaoNovo);
		
		String ligacoesAgua = "";
		if(inserirPrestacoesParcelamentoPerfilActionForm.getIndicadorValorUltimaContaEmAtraso().equals("1")){
			String [] ids = inserirPrestacoesParcelamentoPerfilActionForm.getIdsRegistros();
			
			if(ids != null && ids.length > 0){
				Collection<ParcelamentoQuantidadePrestacaoSituacaoLigacaoAgua> collectionParcelamentoQtdLigacaoAgua = new ArrayList<ParcelamentoQuantidadePrestacaoSituacaoLigacaoAgua>();
		
				//String[] idsRegistros = new String[ids.length];
				for(int i = 0; i < ids.length; i++){
					ParcelamentoQuantidadePrestacaoSituacaoLigacaoAgua parcelamento = new ParcelamentoQuantidadePrestacaoSituacaoLigacaoAgua();
					FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
					filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, ids[i]));
					
					Collection colLigacaoAgua = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
					
					if(!Util.isVazioOrNulo(colLigacaoAgua)){
						LigacaoAguaSituacao ligacaoAgua = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colLigacaoAgua);
						parcelamento.setLigacaoAguaSituacao(ligacaoAgua);
						parcelamento.setUltimaAlteracao(new Date());
						
						collectionParcelamentoQtdLigacaoAgua.add(parcelamento);
						
						ligacoesAgua += ligacaoAgua.getDescricao() + ";";
					}
				}
				
				if(!Util.isVazioOrNulo(collectionParcelamentoQtdLigacaoAgua)){
					parcelamentoQuantidadePrestacaoHelperNovo.setCollectionParcelamentoQtdLigacaoAgua(collectionParcelamentoQtdLigacaoAgua);
				}
			}
		}
		
		if(ligacoesAgua.equals("")){
			parcelamentoQuantidadePrestacaoHelperNovo.setLigacoesAgua("Sem Liga��es Agua");
		}else{
			parcelamentoQuantidadePrestacaoHelperNovo.setLigacoesAgua(ligacoesAgua);
		}
		
		if(indicadorMediaValorContas.toString().equals("1") || indicadorValorUltimaContaEmAtraso.toString().equals("1")){
			parcelamentoQuantidadePrestacaoHelperNovo.setHabilitado("1");
		}else{
			parcelamentoQuantidadePrestacaoHelperNovo.setHabilitado("2");
		}
		
		if (sessao.getAttribute("collectionParcelamentoFaixaValor")!= null){
			Collection collectionParcelamentoFaixaValor = (Collection) sessao.getAttribute("collectionParcelamentoFaixaValor");
			parcelamentoQuantidadePrestacaoHelperNovo.setCollectionParcelamentoFaixaValor(collectionParcelamentoFaixaValor);
			sessao.removeAttribute("collectionParcelamentoFaixaValor");
		}
		
		collectionParcelamentoQuantidadePrestacaoHelper.add(parcelamentoQuantidadePrestacaoHelperNovo);

		inserirPrestacoesParcelamentoPerfilActionForm.setQuantidadeMaximaPrestacao("");
		inserirPrestacoesParcelamentoPerfilActionForm.setQuantidadeMaxPrestacaoEspecial("");
		inserirPrestacoesParcelamentoPerfilActionForm.setTaxaJuros("");
		inserirPrestacoesParcelamentoPerfilActionForm.setPercentualMinimoEntrada("");
		inserirPrestacoesParcelamentoPerfilActionForm.setPercentualTarifaMinimaImovel("");
		inserirPrestacoesParcelamentoPerfilActionForm.setPercentualValorReparcelado("");
		
		inserirPrestacoesParcelamentoPerfilActionForm.setFatorQuantidadePrestacoes("");
    	inserirPrestacoesParcelamentoPerfilActionForm.setIndicadorMediaValorContas(ConstantesSistema.NAO.toString());
    	inserirPrestacoesParcelamentoPerfilActionForm.setIndicadorValorUltimaContaEmAtraso(ConstantesSistema.NAO.toString());
    	inserirPrestacoesParcelamentoPerfilActionForm.setIndicadorGuiaPagamentoPermissaoEspecial(ConstantesSistema.NAO.toString());
    	inserirPrestacoesParcelamentoPerfilActionForm.setIndicadorEntradaMenorPrestacaoParcelamento(ConstantesSistema.NAO.toString());
    	
		//Ordena a cole��o pela Qtde. M�xima Meses de Inatividade da Lig. de �gua
		Collections.sort((List) collectionParcelamentoQuantidadePrestacaoHelper, new Comparator() {
			public int compare(Object a, Object b) {
				Integer valorMinPrestacao1 = new Integer(((ParcelamentoQuantidadePrestacaoHelper) a).getParcelamentoQuantidadePrestacao().getQuantidadeMaximaPrestacao().toString()) ;
				Integer valorMinPrestacao2 = new Integer(((ParcelamentoQuantidadePrestacaoHelper) b).getParcelamentoQuantidadePrestacao().getQuantidadeMaximaPrestacao().toString()) ;
		
				return valorMinPrestacao1.compareTo(valorMinPrestacao2);

			}
		});
				
		 //manda para a sess�o a cole��o de ParcelamentoQuantidadePrestacao
        sessao.setAttribute("collectionParcelamentoQuantidadePrestacaoHelper", collectionParcelamentoQuantidadePrestacaoHelper);

    }
    
    private void atualizaColecaoParcelamentoQuantidadePrestacaoNaSessao(HttpSession sessao,
		HttpServletRequest httpServletRequest){

		//collectionParcelamentoQuantidadePrestacaoHelper
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
			
			String fatCalculo = null;
			String indMediaConta = null;
			String indValorUltConta = null;
			String indicadorGuiaPagamentoPermissaoEspecial = null;
			String indicadorEntradaMenorPrestacaoParcelamento = null;
			
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
				percVlReparcelado = (String)httpServletRequest.getParameter("percVlReparcelado" + valorTempo);
				
				fatCalculo = (String) httpServletRequest.getParameter("fatQtdPrest" + valorTempo);
				indMediaConta = (String) httpServletRequest.getParameter("indMedVlCnta" + valorTempo);
				indValorUltConta = (String) httpServletRequest.getParameter("indValorUltConta" + valorTempo);
				indicadorGuiaPagamentoPermissaoEspecial = (String) httpServletRequest.getParameter("indGuiaPagPerEsp" + valorTempo);
				indicadorEntradaMenorPrestacaoParcelamento = (String) httpServletRequest.getParameter("indEntMenPreParc" + valorTempo);
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
				
				if(String.valueOf(indicadorMediaConta).equals("1") || String.valueOf(indicadorValorUltConta).equals("1")){
					parcelamentoQuantidadePrestacaoHelper.setHabilitado("1");
				}else{
					parcelamentoQuantidadePrestacaoHelper.setHabilitado("2");
				}
				
				Short indicadorGuiaPagamentoEntradaEspecial  = ConstantesSistema.NAO;
				if (indicadorGuiaPagamentoPermissaoEspecial != null && !indicadorGuiaPagamentoPermissaoEspecial.equals("")) {
					indicadorGuiaPagamentoEntradaEspecial = new Short(indicadorGuiaPagamentoPermissaoEspecial);
				}
				
				Short indicadorEntradaMenorPrestParcelamento  = ConstantesSistema.NAO;
				if (indicadorEntradaMenorPrestacaoParcelamento != null && !indicadorEntradaMenorPrestacaoParcelamento.equals("")) {
					indicadorEntradaMenorPrestParcelamento = new Short(indicadorEntradaMenorPrestacaoParcelamento);
				}
				
				
				BigDecimal percentualMinimoEntrada  = null;
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
				
				parcelamentoQuantidadePrestacao.setFatorQuantidadePrestacoes(fatorCalculo);
				parcelamentoQuantidadePrestacao.setIndicadorMediaValorContas(indicadorMediaConta);
				parcelamentoQuantidadePrestacao.setIndicadorValorUltimaContaEmAtraso(indicadorValorUltConta);
				parcelamentoQuantidadePrestacao.setIndicadorGuiaPagamentoEntradaEspecial(indicadorGuiaPagamentoEntradaEspecial);
				parcelamentoQuantidadePrestacao.setIndicadorEntradaMenorPrestacaoParcelamento(indicadorEntradaMenorPrestParcelamento);
				
				parcelamentoQuantidadePrestacaoHelper.setParcelamentoQuantidadePrestacao(parcelamentoQuantidadePrestacao);
			}
	    }	
    }

    private void atualizaColecoesNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest){
    	     	
		// collectionParcelamentoDescontoAntiguidade
		if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null
		&& !sessao.getAttribute("collectionParcelamentoDescontoAntiguidade").equals(
			"")) {
		
			Collection collectionParcelamentoDescontoAntiguidade = (Collection) sessao
			.getAttribute("collectionParcelamentoDescontoAntiguidade");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  ParcelamentoDescontoAntiguidade
			String vlSemRestAntiguidade = null;
			String vlComRestAntiguidade = null;
			String vlDescontoAtivo = null;
			
		
			Iterator iteratorParcelamentoDescontoAntiguidade = collectionParcelamentoDescontoAntiguidade
			.iterator();
		
			while (iteratorParcelamentoDescontoAntiguidade.hasNext()) {
				ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidade = (ParcelamentoDescontoAntiguidade) iteratorParcelamentoDescontoAntiguidade
					.next();
				long valorTempo = parcelamentoDescontoAntiguidade.getUltimaAlteracao()
					.getTime();
				vlSemRestAntiguidade = (String) httpServletRequest.getParameter("vlSemRestAntiguidade"
					+ valorTempo);
				vlComRestAntiguidade = httpServletRequest.getParameter("vlComRestAntiguidade"
					+ valorTempo);
				vlDescontoAtivo = httpServletRequest.getParameter("vlDescontoAtivo"
					+ valorTempo);
					
				// inseri essas vari�veis no objeto ParcelamentoDescontoAntiguidade
				BigDecimal percentualDescontoSemRestabelecimentoAntiguidade  = null;
				if (vlSemRestAntiguidade != null 
					&& !vlSemRestAntiguidade.equals("")) {
					percentualDescontoSemRestabelecimentoAntiguidade = Util
						.formatarMoedaRealparaBigDecimal(vlSemRestAntiguidade);
				}
			
				BigDecimal percentualDescontoComRestabelecimentoAntiguidade = null;
				if (vlComRestAntiguidade != null 
					&& !vlComRestAntiguidade.equals("")) {
					percentualDescontoComRestabelecimentoAntiguidade = Util
						.formatarMoedaRealparaBigDecimal(vlComRestAntiguidade);
				}
					
				BigDecimal percentualDescontoAtivoAntiguidade = null;
				if (vlDescontoAtivo != null 
					&& !vlDescontoAtivo.equals("")) {
					percentualDescontoAtivoAntiguidade = Util
						.formatarMoedaRealparaBigDecimal(vlDescontoAtivo);
				}
			
				parcelamentoDescontoAntiguidade.
				setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoAntiguidade);
				parcelamentoDescontoAntiguidade.
				setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoAntiguidade);
				parcelamentoDescontoAntiguidade.
				setPercentualDescontoAtivo(percentualDescontoAtivoAntiguidade);
			}
		}
	
		//collectionParcelamentoDescontoInatividade
		if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null
		&& !sessao.getAttribute("collectionParcelamentoDescontoInatividade").equals(
			"")) {
		
			Collection collectionParcelamentoDescontoInatividade = (Collection) sessao
			.getAttribute("collectionParcelamentoDescontoInatividade");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  ParcelamentoDescontoInatividade
			String vlSemRestInatividade = null;
			String vlComRestInatividade = null;
		
			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividade
			.iterator();
			
			while (iteratorParcelamentoDescontoInatividade.hasNext()) {
				ParcelamentoDescontoInatividade parcelamentoDescontoInatividade = 
					(ParcelamentoDescontoInatividade) iteratorParcelamentoDescontoInatividade
					.next();
				long valorTempo = parcelamentoDescontoInatividade.getUltimaAlteracao()
					.getTime();
				vlSemRestInatividade = (String) httpServletRequest.getParameter("vlSemRestInatividade"
					+ valorTempo);
				vlComRestInatividade = httpServletRequest.getParameter("vlComRestInatividade"
					+ valorTempo);
				
				// insere essas vari�veis no objeto ParcelamentoDescontoInatividade
				BigDecimal percentualDescontoSemRestabelecimentoInatividade  = null;
				if (vlSemRestInatividade != null 
					&& !vlSemRestInatividade.equals("")) {
					percentualDescontoSemRestabelecimentoInatividade = Util
						.formatarMoedaRealparaBigDecimal(vlSemRestInatividade);
				}
				
				BigDecimal percentualDescontoComRestabelecimentoInatividade = null;
				if (vlComRestInatividade != null 
					&& !vlComRestInatividade.equals("")) {
					percentualDescontoComRestabelecimentoInatividade = Util
						.formatarMoedaRealparaBigDecimal(vlComRestInatividade);
				}
				
				parcelamentoDescontoInatividade.
				setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoInatividade);
				parcelamentoDescontoInatividade.
				setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoInatividade);
			}
		}	        	
		
    	
    	//collectionParcelamentoDescontoInatividadeAVista
    	if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") != null
				&& !sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista").equals("")) {
	

			Collection collectionParcelamentoDescontoInatividadeAVista = (Collection) sessao
					.getAttribute("collectionParcelamentoDescontoInatividadeAVista");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  ParcelamentoDescontoInatividade
			String vlSemRestInatividade = null;
			String vlComRestInatividade = null;

			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividadeAVista.iterator();
			
			while (iteratorParcelamentoDescontoInatividade.hasNext()) {
				ParcDesctoInativVista parcelamentoDescontoInatividade = 
						(ParcDesctoInativVista) iteratorParcelamentoDescontoInatividade.next();
				long valorTempo = parcelamentoDescontoInatividade.getUltimaAlteracao().getTime();
				vlSemRestInatividade = (String) httpServletRequest.getParameter("vlSemRestInatividadeAVista"+ valorTempo);
				vlComRestInatividade = httpServletRequest.getParameter("vlComRestInatividadeAVista"+ valorTempo);
				
				// insere essas vari�veis no objeto ParcelamentoDescontoInatividade
				BigDecimal percentualDescontoSemRestabelecimentoInatividade  = null;
				if (vlSemRestInatividade != null && !vlSemRestInatividade.equals("")) {
					percentualDescontoSemRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlSemRestInatividade);
				}
				
				BigDecimal percentualDescontoComRestabelecimentoInatividade = null;
				if (vlComRestInatividade != null && !vlComRestInatividade.equals("")) {
					percentualDescontoComRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlComRestInatividade);
				}

				parcelamentoDescontoInatividade.
					setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoInatividade);
				parcelamentoDescontoInatividade.
					setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoInatividade);


			}

        }	        	


		
    }

    private void atualizaFormNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest){
    	
    	if(sessao.getAttribute("UseCase").equals("INSERIRPERFIL") ){
    		ParcelamentoPerfilActionForm parcelamentoPerfilActionForm = (ParcelamentoPerfilActionForm)sessao.getAttribute("ParcelamentoPerfilActionForm");	  		
    		
    		parcelamentoPerfilActionForm.setResolucaoDiretoria(httpServletRequest.getParameter("resolucaoDiretoria"));
    		parcelamentoPerfilActionForm.setImovelSituacaoTipo(httpServletRequest.getParameter("imovelSituacaoTipo"));
    		parcelamentoPerfilActionForm.setImovelPerfil(httpServletRequest.getParameter("imovelPerfil"));
    		parcelamentoPerfilActionForm.setSubcategoria(httpServletRequest.getParameter("subcategoria"));
    		parcelamentoPerfilActionForm.setCategoria(httpServletRequest.getParameter("categoria"));
    		
    		parcelamentoPerfilActionForm.setPercentualDescontoAcrescimo(httpServletRequest.getParameter("percentualDescontoAcrescimo"));
    		parcelamentoPerfilActionForm.setPercentualTarifaMinimaPrestacao(httpServletRequest.getParameter("percentualTarifaMinimaPrestacao"));
    		parcelamentoPerfilActionForm.setConsumoMinimo(httpServletRequest.getParameter("consumoMinimo"));
    		parcelamentoPerfilActionForm.setPercentualVariacaoConsumoMedio(httpServletRequest.getParameter("percentualVariacaoConsumoMedio"));
    		parcelamentoPerfilActionForm.setIndicadorParcelarChequeDevolvido(httpServletRequest.getParameter("indicadorParcelarChequeDevolvido"));
    		parcelamentoPerfilActionForm.setIndicadorParcelarSancoesMaisDeUmaConta(httpServletRequest.getParameter("indicadorParcelarSancoesMaisDeUmaConta"));
    		
    		parcelamentoPerfilActionForm.setNumeroConsumoEconomia(httpServletRequest.getParameter("numeroConsumoEconomia"));
			parcelamentoPerfilActionForm.setNumeroAreaConstruida(httpServletRequest.getParameter("numeroAreaConstruida"));    
			parcelamentoPerfilActionForm.setIndicadorRetroativoTarifaSocial(httpServletRequest.getParameter("indicadorRetroativoTarifaSocial"));
			parcelamentoPerfilActionForm.setAnoMesReferenciaLimiteInferior(httpServletRequest.getParameter("anoMesReferenciaLimiteInferior"));
			parcelamentoPerfilActionForm.setAnoMesReferenciaLimiteSuperior(httpServletRequest.getParameter("anoMesReferenciaLimiteSuperior"));
			parcelamentoPerfilActionForm.setPercentualDescontoAVista(httpServletRequest.getParameter("percentualDescontoAVista")); 
			parcelamentoPerfilActionForm.setParcelaQuantidadeMinimaFatura(httpServletRequest.getParameter("parcelaQuantidadeMinimaFatura"));
			parcelamentoPerfilActionForm.setIndicadorAlertaParcelaMinima(httpServletRequest.getParameter("indicadorAlertaParcelaMinima"));
			parcelamentoPerfilActionForm.setPercentualDescontoSancao(httpServletRequest.getParameter("percentualDescontoSancao"));
			parcelamentoPerfilActionForm.setQuantidadeEconomias(httpServletRequest.getParameter("quantidadeEconomias"));
			parcelamentoPerfilActionForm.setCapacidadeHidrometro(httpServletRequest.getParameter("capacidadeHidrometro")); 
			parcelamentoPerfilActionForm.setIndicadorEntradaMinima(httpServletRequest.getParameter("indicadorEntradaMinima"));
			parcelamentoPerfilActionForm.setQuantidadeMaximaReparcelamento(httpServletRequest.getParameter("quantidadeMaximaReparcelamento"));
			parcelamentoPerfilActionForm.setDataLimiteDescontoPagamentoAVista(httpServletRequest.getParameter("dataLimiteDescontoPagamentoAVista"));
			
			parcelamentoPerfilActionForm.setPercentualDescontoAcrescimoPagamentoAVista(httpServletRequest.getParameter("percentualDescontoAcrescimoPagamentoAVista"));
			parcelamentoPerfilActionForm.setIdContaMotivoRevisao(httpServletRequest.getParameter("idContaMotivoRevisao"));
			
			parcelamentoPerfilActionForm.setPercentualDescontoDebitoPagamentoAVista(httpServletRequest.getParameter("percentualDescontoDebitoPagamentoAVista"));
			parcelamentoPerfilActionForm.setPercentualDescontoDebitoPagamentoParcelado(httpServletRequest.getParameter("percentualDescontoDebitoPagamentoParcelado"));
			parcelamentoPerfilActionForm.setLimiteVencimentoContaPagamentoAVista(httpServletRequest.getParameter("limiteVencimentoContaPagamentoAVista"));
			parcelamentoPerfilActionForm.setLimiteVencimentoContaPagamentoParcelado(httpServletRequest.getParameter("limiteVencimentoContaPagamentoParcelado"));
			
			parcelamentoPerfilActionForm.setPercentualDescontoPagamentoAVistaMulta(httpServletRequest.getParameter("percentualDescontoPagamentoAVistaMulta"));
			parcelamentoPerfilActionForm.setPercentualDescontoPagamentoAVistaJuros(httpServletRequest.getParameter("percentualDescontoPagamentoAVistaJuros"));
			parcelamentoPerfilActionForm.setPercentualDescontoPagamentoAVistaAtuMonetaria(httpServletRequest.getParameter("percentualDescontoPagamentoAVistaAtuMonetaria"));
			parcelamentoPerfilActionForm.setPercentualDescontoJuros(httpServletRequest.getParameter("percentualDescontoJuros"));
			parcelamentoPerfilActionForm.setPercentualDescontoMulta(httpServletRequest.getParameter("percentualDescontoMulta"));
			parcelamentoPerfilActionForm.setPercentualDescontoAtualizacaoMonetaria(httpServletRequest.getParameter("percentualDescontoAtualizacaoMonetaria"));
    		    		
    		sessao.setAttribute("ParcelamentoPerfilActionForm",parcelamentoPerfilActionForm);
    		
    	}else if(sessao.getAttribute("UseCase").equals("ATUALIZARPERFIL")){
    		AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm = (AtualizarParcelamentoPerfilActionForm)sessao.getAttribute("AtualizarParcelamentoPerfilActionForm");
    		
    		atualizarParcelamentoPerfilActionForm.setPercentualDescontoAcrescimo(httpServletRequest.getParameter("percentualDescontoAcrescimo"));
    		atualizarParcelamentoPerfilActionForm.setPercentualTarifaMinimaPrestacao(httpServletRequest.getParameter("percentualTarifaMinimaPrestacao"));
    		atualizarParcelamentoPerfilActionForm.setConsumoMinimo(httpServletRequest.getParameter("consumoMinimo"));
    		atualizarParcelamentoPerfilActionForm.setPercentualVariacaoConsumoMedio(httpServletRequest.getParameter("percentualVariacaoConsumoMedio"));
    		atualizarParcelamentoPerfilActionForm.setIndicadorParcelarChequeDevolvido(httpServletRequest.getParameter("indicadorParcelarChequeDevolvido"));
    		atualizarParcelamentoPerfilActionForm.setIndicadorParcelarSancoesMaisDeUmaConta(httpServletRequest.getParameter("indicadorParcelarSancoesMaisDeUmaConta"));
    		atualizarParcelamentoPerfilActionForm.setNumeroConsumoEconomia(httpServletRequest.getParameter("numeroConsumoEconomia"));
			atualizarParcelamentoPerfilActionForm.setNumeroAreaConstruida(httpServletRequest.getParameter("numeroAreaConstruida"));    
			atualizarParcelamentoPerfilActionForm.setIndicadorRetroativoTarifaSocial(httpServletRequest.getParameter("indicadorRetroativoTarifaSocial"));
			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteInferior(httpServletRequest.getParameter("anoMesReferenciaLimiteInferior"));
			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteSuperior(httpServletRequest.getParameter("anoMesReferenciaLimiteSuperior"));
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoAVista(httpServletRequest.getParameter("percentualDescontoAVista")); 
			atualizarParcelamentoPerfilActionForm.setParcelaQuantidadeMinimaFatura(httpServletRequest.getParameter("parcelaQuantidadeMinimaFatura"));
			atualizarParcelamentoPerfilActionForm.setIndicadorAlertaParcelaMinima(httpServletRequest.getParameter("indicadorAlertaParcelaMinima"));
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoSancao(httpServletRequest.getParameter("percentualDescontoSancao"));
			atualizarParcelamentoPerfilActionForm.setQuantidadeEconomias(httpServletRequest.getParameter("quantidadeEconomias"));
			atualizarParcelamentoPerfilActionForm.setCapacidadeHidrometro(httpServletRequest.getParameter("capacidadeHidrometro")); 
			atualizarParcelamentoPerfilActionForm.setIndicadorEntradaMinima(httpServletRequest.getParameter("indicadorEntradaMinima"));
			atualizarParcelamentoPerfilActionForm.setQuantidadeMaximaReparcelamento(httpServletRequest.getParameter("quantidadeMaximaReparcelamento"));
			atualizarParcelamentoPerfilActionForm.setDataLimiteDescontoPagamentoAVista(httpServletRequest.getParameter("dataLimiteDescontoPagamentoAVista"));
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoAcrescimoPagamentoAVista(httpServletRequest.getParameter("percentualDescontoAcrescimoPagamentoAVista"));
			atualizarParcelamentoPerfilActionForm.setIdContaMotivoRevisao(httpServletRequest.getParameter("idContaMotivoRevisao"));
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoDebitoPagamentoAVista(httpServletRequest.getParameter("percentualDescontoDebitoPagamentoAVista"));
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoDebitoPagamentoParcelado(httpServletRequest.getParameter("percentualDescontoDebitoPagamentoParcelado"));
			atualizarParcelamentoPerfilActionForm.setLimiteVencimentoContaPagamentoAVista(httpServletRequest.getParameter("limiteVencimentoContaPagamentoAVista"));
			atualizarParcelamentoPerfilActionForm.setLimiteVencimentoContaPagamentoParcelado(httpServletRequest.getParameter("limiteVencimentoContaPagamentoParcelado"));
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoPagamentoAVistaMulta(httpServletRequest.getParameter("percentualDescontoPagamentoAVistaMulta"));
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoPagamentoAVistaJuros(httpServletRequest.getParameter("percentualDescontoPagamentoAVistaJuros"));
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoPagamentoAVistaAtuMonetaria(httpServletRequest.getParameter("percentualDescontoPagamentoAVistaAtuMonetaria"));
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoJuros(httpServletRequest.getParameter("percentualDescontoJuros"));
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoMulta(httpServletRequest.getParameter("percentualDescontoMulta"));
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoAtualizacaoMonetaria(httpServletRequest.getParameter("percentualDescontoAtualizacaoMonetaria"));
			
			
			sessao.setAttribute("AtualizarParcelamentoPerfilActionForm",atualizarParcelamentoPerfilActionForm);
    	}
    	
    }
    
    
    private void adicionarParcelamentoQuantidadeReparcelamentoHelper(
    		String quantidadeReparcelamento,
			HttpSession sessao,
			String percentualEntradaSugerida){

		if (quantidadeReparcelamento== null || quantidadeReparcelamento.equalsIgnoreCase("")){
			//Informe Qtde. M�xima Reparcelamentos Consecutivos
			throw new ActionServletException("atencao.required", null, " Qtde. M�xima Reparcelamentos Consecutivos");	
		}else if( Util.validarValorNaoNumericoComoBigDecimal(quantidadeReparcelamento)){
			//Valor M�nimo da Presta��o deve ser numerico
			throw new ActionServletException("atencao.integer", null, "  Qtde. M�xima Reparcelamentos Consecutivos");
		}
		
		Collection<ParcelamentoQuantidadeReparcelamentoHelper> collectionParcelamentoQuantidadeReparcelamentoHelper = null;
		
		if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") != null) {
		collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
		} else {
		collectionParcelamentoQuantidadeReparcelamentoHelper = new ArrayList();
		}
		
		Short qtdeReparcelamentoShort = new Short(quantidadeReparcelamento);
		
		BigDecimal percentualEntradaSugeridaBigDecimal = null;
		if (percentualEntradaSugerida != null &&
				!percentualEntradaSugerida.equals("")){
			
			percentualEntradaSugeridaBigDecimal = Util.formatarMoedaRealparaBigDecimal(percentualEntradaSugerida);
			
			 //[FS0010]-Verificar Percentual M�ximo
		     verificarPercentualMaximo(percentualEntradaSugeridaBigDecimal);

		}
		
		ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQtdeReparcelamentoHelperNovo = 
								new ParcelamentoQuantidadeReparcelamentoHelper();
		
		if (collectionParcelamentoQuantidadeReparcelamentoHelper != null && !collectionParcelamentoQuantidadeReparcelamentoHelper.isEmpty()){
			// se cole��o n�o estiver vazia
			// verificar se a qtd m�xima de presta��es do parcelamento ja foi informada	
			ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQtdeReparcelamentoHelperAntigo = null;
			Iterator iterator = collectionParcelamentoQuantidadeReparcelamentoHelper.iterator();
			
			while (iterator.hasNext()) {
			parcelamentoQtdeReparcelamentoHelperAntigo = (ParcelamentoQuantidadeReparcelamentoHelper) iterator.next();
			
				//[FS0003] Verificar quantidade m�xima de reparcelamentos consecutivos 
				if (qtdeReparcelamentoShort.equals(parcelamentoQtdeReparcelamentoHelperAntigo.getQuantidadeMaximaReparcelamento())){
					//Quantidade M�xima de Reparcelamentos Consecutivos j� informada
					throw new ActionServletException("atencao.qtde_maxima_reparcelamento_ja_informado");
				}
			}
		}			
		
		parcelamentoQtdeReparcelamentoHelperNovo.setQuantidadeMaximaReparcelamento(qtdeReparcelamentoShort);
		parcelamentoQtdeReparcelamentoHelperNovo.setPercentualEntradaSugerida(percentualEntradaSugeridaBigDecimal);
		parcelamentoQtdeReparcelamentoHelperNovo.setInformacaoParcelamentoQtdeReparcelamento("N�O INFORMADA");
		parcelamentoQtdeReparcelamentoHelperNovo.setUltimaAlteracao(new Date());
		collectionParcelamentoQuantidadeReparcelamentoHelper.add(parcelamentoQtdeReparcelamentoHelperNovo);
		
		//Ordena a cole��o pela qunatidade de reparcelamento
		Collections.sort((List) collectionParcelamentoQuantidadeReparcelamentoHelper, new Comparator() {
			public int compare(Object a, Object b) {
				Integer valorMinPrestacao1 = new Integer(((ParcelamentoQuantidadeReparcelamentoHelper) a).getQuantidadeMaximaReparcelamento().toString()) ;
				Integer valorMinPrestacao2 = new Integer(((ParcelamentoQuantidadeReparcelamentoHelper) b).getQuantidadeMaximaReparcelamento().toString()) ;
		
				return valorMinPrestacao1.compareTo(valorMinPrestacao2);

			}
		});
		
		
		//manda para a sess�o a cole��o de ParcelamentoQuantidadeReparcelamentoHelper
		sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper", collectionParcelamentoQuantidadeReparcelamentoHelper);
		}
    
    
    private void atualizaColecaoParcelamentoFaixaValorNaSessao(HttpSession sessao,
    		HttpServletRequest httpServletRequest){

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
    //[FS0010]-Verificar Percentual M�ximo
    private void verificarPercentualMaximo(BigDecimal percentual){
    	
    	BigDecimal percentualMaximo = new BigDecimal("100");
    	
    	if (percentual.compareTo(percentualMaximo) == 1){
    		throw new ActionServletException(
					"atencao.percentual_maior_percentual_maximo");	
    	}
    	
    }
}

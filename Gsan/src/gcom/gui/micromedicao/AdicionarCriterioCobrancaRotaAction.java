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
package gcom.gui.micromedicao;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.cobranca.RotaAcaoCriterio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
 * Action form de Adicionar Crit�rio de Cobran�a da Rota
 * 
 * @author Vivianne Sousa
 * @created 25/04/06
 */
public class AdicionarCriterioCobrancaRotaAction extends GcomAction {
	
	/**
	 * Este caso de uso permite a inclus�o de um Crit�rio de Cobran�a da Rota
	 * 
	 * [UC0038] Inserir Rota 
	 * @author Vivianne Sousa
	 * @date 25/04/2006
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


        ActionForward retorno = actionMapping.findForward("inserirCriterioCobrancaRota");
        
                
   	 	AdicionarCriterioCobrancaRotaActionForm adicionarCriterioCobrancaRotaActionForm = 
			(AdicionarCriterioCobrancaRotaActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
    
        
        String idCobrancaAcao =  adicionarCriterioCobrancaRotaActionForm.getCobrancaAcao();
        String idCobrancaCriterio = adicionarCriterioCobrancaRotaActionForm.getIdCobrancaCriterio();
        
        Collection collectionRotaAcaoCriterio = null;
       
        if (sessao.getAttribute("collectionRotaAcaoCriterio") != null) {
        	collectionRotaAcaoCriterio = (Collection) sessao
                    .getAttribute("collectionRotaAcaoCriterio");
        } else {
        	collectionRotaAcaoCriterio = new ArrayList();
        }

        validacaoFinal(collectionRotaAcaoCriterio,idCobrancaAcao,idCobrancaCriterio,fachada);
        
        //inst�ncia uma rotaAcaoCriterioNova/
        RotaAcaoCriterio rotaAcaoCriterioNova = new RotaAcaoCriterio();

        // testa se a a��o de cobran�a ja foi selecionada
        if (idCobrancaAcao != null && !idCobrancaAcao.equalsIgnoreCase("")) {
       	
        	FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
        	filtroCobrancaAcao.adicionarParametro(new ParametroSimples
        			(FiltroCobrancaAcao.ID,idCobrancaAcao));
        	
        	Collection<CobrancaAcao> collectionCobrancaAcao = fachada.pesquisar(
        			filtroCobrancaAcao, CobrancaAcao.class.getName());
        	
        	CobrancaAcao cobrancaAcao = (CobrancaAcao)Util.retonarObjetoDeColecao(collectionCobrancaAcao);
        	//cobrancaAcao.setId(new Integer(idCobrancaAcao));
        	rotaAcaoCriterioNova.setCobrancaAcao(cobrancaAcao);
        }
        
        // testa se  o crit�rio de cobran�a ja foi pesquisado 
        if (idCobrancaCriterio != null && !idCobrancaCriterio.equalsIgnoreCase("")) {
        	
        	FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
        	filtroCobrancaCriterio.adicionarParametro(new ParametroSimples
        			(FiltroCobrancaCriterio.ID,idCobrancaCriterio));
        	
        	Collection<CobrancaCriterio> collectionCobrancaCriterio = fachada.pesquisar(
        			filtroCobrancaCriterio, CobrancaCriterio.class.getName());
        	
        	CobrancaCriterio cobrancaCriterio = (CobrancaCriterio)Util.retonarObjetoDeColecao(collectionCobrancaCriterio);
        	rotaAcaoCriterioNova.setCobrancaCriterio(cobrancaCriterio);
        }
        
        collectionRotaAcaoCriterio.add(rotaAcaoCriterioNova);
      
        //c�digo p verificar se j� foram adicionadas todos os crit�rios de cobran�a da rota
        //se ainda falta adicionar HABILITA o bot�o adicionar
        //sen� DESABILITA o bot�o adicionar
        FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
        Collection<CobrancaGrupo> collectionCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
        if (collectionRotaAcaoCriterio.size() != collectionCobrancaAcao.size()){
			httpServletRequest.setAttribute("adicionar","habilitado");
		}else{
			httpServletRequest.setAttribute("adicionar","desabilitado");
		}
        
        //manda para a sess�o a cole��o de RotaAcaoCriterio
        sessao.setAttribute("collectionRotaAcaoCriterio", collectionRotaAcaoCriterio);
        httpServletRequest.setAttribute("reloadPage", "OK");
		//Definindo o caso de uso que receber� o retorno
    	if (sessao.getAttribute("UseCase").equals("INSERIRROTA")){
    		httpServletRequest.setAttribute("reloadPageURL", "INSERIRROTA");
    	}
    	else if (sessao.getAttribute("UseCase").equals("ATUALIZARROTA")) {
    		httpServletRequest.setAttribute("reloadPageURL", "ATUALIZARROTA");
    		
    	} 
    	
    	sessao.removeAttribute("adicionarCriterioCobrancaRotaActionForm");
    	sessao.removeAttribute("idCobrancaAcao");        
        
        return retorno;
    }

        

    
    private void validacaoFinal (Collection collectionRotaAcaoCriterio,
							String idCobrancaAcao,String idCobrancaCriterio,
							Fachada fachada){
    	
    	// a��o de cobran�a � obrigat�rio.
		if ((idCobrancaAcao == null)
				|| (idCobrancaAcao.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			throw new ActionServletException(
					"atencao.acao_cobranca_nao_informado");
		}
		
		// crit�rio de cobran�a � obrigat�rio.
		if (idCobrancaCriterio == null || idCobrancaCriterio.equalsIgnoreCase("")) {
			throw new ActionServletException(
					"atencao.criterio_cobranca_nao_informado");
		}
		verificaExistenciaCodCobrancaCriterio( idCobrancaCriterio, fachada);
		
         
         // testa se a a��o de cobran�a ja foi selecionada
         if (idCobrancaAcao != null && !idCobrancaAcao.equalsIgnoreCase("")) {
        	 //[FS0009]verifica a��o de cobran�a ja existente
     		if (collectionRotaAcaoCriterio != null && !collectionRotaAcaoCriterio.isEmpty()){
    			
    			RotaAcaoCriterio rotaAcaoCriterio = null;
    			Iterator iterator = collectionRotaAcaoCriterio.iterator();

    			while (iterator.hasNext()) {
    				rotaAcaoCriterio = (RotaAcaoCriterio) iterator.next();
    				
    				if (rotaAcaoCriterio.getCobrancaAcao().getId().equals(new Integer(idCobrancaAcao))){
    					//Esta A��o de Cobran�a ja foi informada
    					throw new ActionServletException(
    					"atencao.acao_cobranca.ja_informada");
    				}

    			}
    			
    		}
         }
         	
    	
    }
    	
    	

    private void verificaExistenciaCodCobrancaCriterio(String idDigitadoEnterCobrancaCriterio, 
    					Fachada fachada) {

    	//Verifica se o c�digo da CobrancaCriterio foi digitado
		if (idDigitadoEnterCobrancaCriterio != null
				&& !idDigitadoEnterCobrancaCriterio.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterCobrancaCriterio) > 0) {
		
			//Recupera a CobrancaCriterio informada pelo usu�rio
			FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
			
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.ID, new Integer(
						idDigitadoEnterCobrancaCriterio)));
				
				Collection<CobrancaCriterio> cobrancaCriterios = fachada.pesquisar(
						filtroCobrancaCriterio, CobrancaCriterio.class.getName());
			
			
			if (cobrancaCriterios == null || cobrancaCriterios.isEmpty()) {
				//Crit�rio de Cobran�a inexistente.
				throw new ActionServletException(
				"atencao.pesquisa_inexistente", null, "Crit�rio de Cobran�a");
			
			}
		}

    }
    
}
 
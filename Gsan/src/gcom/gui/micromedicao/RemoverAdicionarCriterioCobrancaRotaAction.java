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

import gcom.cobranca.RotaAcaoCriterio;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de remover adicionar Criterio de Cobranca de Rota
 * 
 * @author Vivianne Sousa
 * @created 25/04/2006
 */
public class RemoverAdicionarCriterioCobrancaRotaAction extends GcomAction {
	/**
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
    	
    	HttpSession sessao = httpServletRequest.getSession(false);
    	
    	ActionForward retorno = actionMapping.findForward("atualizarRotaRemoverCriterioCobrancaRota");
    	
    	//InserirRotaActionForm inserirRotaActionForm = (InserirRotaActionForm) actionForm;
    	
    	if (sessao.getAttribute("UseCase")!= null &&
    			sessao.getAttribute("UseCase").equals("INSERIRROTA")){
    		retorno = actionMapping.findForward("inserirRotaRemoverCriterioCobrancaRota");	
    	}
    		
    	                                 
        String idCobrancaAcaoExcluir = httpServletRequest.getParameter("idCobrancaAcaoExcluir");
        
        if (idCobrancaAcaoExcluir != null && !idCobrancaAcaoExcluir.equalsIgnoreCase("") &&
        	sessao.getAttribute("collectionRotaAcaoCriterio") != null){
        	        	
        	Collection collectionRotaAcaoCriterio = (Collection) sessao
            					.getAttribute("collectionRotaAcaoCriterio");
        	
        	RotaAcaoCriterio rotaAcaoCriterio = null;
        	RotaAcaoCriterio rotaAcaoCriterioExcluir = null;
			Iterator iterator = collectionRotaAcaoCriterio.iterator();
						
			while (iterator.hasNext()) {
				rotaAcaoCriterio = (RotaAcaoCriterio) iterator.next();
		
				//procura na cole��o a rotaAcaoCriterio que tem o idCobrancaAcao selecionado
				if (rotaAcaoCriterio.getCobrancaAcao().getId().equals(new Integer(idCobrancaAcaoExcluir))){
						rotaAcaoCriterioExcluir =  rotaAcaoCriterio;
//			            //HABILITA o bot�o adicionar
//			            httpServletRequest.setAttribute("adicionar","habilitado");
				}

			}
			
			collectionRotaAcaoCriterio.remove(rotaAcaoCriterioExcluir);
       	 	sessao.setAttribute("collectionRotaAcaoCriterio", collectionRotaAcaoCriterio);
       	 	
	        Collection collectionAcaoCobranca = (Collection) sessao.getAttribute("collectionAcaoCobranca");
	        if (collectionAcaoCobranca != null && 
	        		collectionRotaAcaoCriterio.size() != collectionAcaoCobranca.size()){
	        	httpServletRequest.setAttribute("adicionar","habilitado");
	        }else{
	        	httpServletRequest.setAttribute("adicionar","desabilitado");
			}
       	 	
        	
        }
        
       return retorno;
    }
    


}
 
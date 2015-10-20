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
* Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.FiltroNegativacaoComando;
import gcom.cobranca.NegativacaoComando;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action que define o pr�-processamento da p�gina de filtrar Negativador Exclus�o Motivo
 * 
 * @author Yara Taciane de Souza
 * @created 03/01/2008
 */
public class ExibirFiltrarNegativadorResultadoSimulacaoAction extends GcomAction {
	/**
	 * Este caso de uso permite o filtro de um Negativador Exclusao Motivo 
	 * 
	 * [UC0670] Filtrar Motivo da Exlusao do Negativador
	 * 
	 * 
	 * @author Yara Taciane de Souza
	 * @date 15/05/2008
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


        ActionForward retorno = actionMapping.findForward("filtrarNegativadorResultadoSimulacao");
        
        FiltrarNegativadorResultadoSimulacaoActionForm form = (FiltrarNegativadorResultadoSimulacaoActionForm) actionForm;
        HttpSession sessao = httpServletRequest.getSession(false);

    	Fachada fachada = Fachada.getInstancia();        
         
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	//-------------- bt DESFAZER ---------------        	
        	form.setIdComando("" + ConstantesSistema.NUMERO_NAO_INFORMADO);   
      
            sessao.removeAttribute("caminhoRetornoTelaPesquisa");
        }        
     
        String idDigitado = (String) form.getIdComando();
        if(!"".equals(idDigitado) && idDigitado!= null){
        	
        	 //Exibe na Tela o nome do Cliente Negativador
        	FiltroNegativacaoComando filtro = new FiltroNegativacaoComando();
    		filtro.adicionarParametro(new ParametroSimples(FiltroNegativacaoComando.ID,idDigitado));    		
            Collection coll = fachada.pesquisar(filtro, NegativacaoComando.class.getName());
            
           NegativacaoComando negativacaoComando = null;
            Iterator it = coll.iterator();
            while(it.hasNext()){
            	negativacaoComando = (NegativacaoComando)it.next();        	 
            }
            
            if(negativacaoComando != null){
            	if (negativacaoComando.getIndicadorSimulacao() == 2){
            		throw new ActionServletException(
    				"atencao.comando_nao_corresponde_simulacao");
            	}
            	
            	if (negativacaoComando.getDataHoraRealizacao() == null){
            		throw new ActionServletException(
    				"atencao.simulacao_nao_realizada", "exibirFiltrarNegativadorResultadoSimulacaoAction.do?menu=sim", new Exception());
            	}
            	
            }
          	
        }
        
         
         
        sessao.removeAttribute("caminhoRetornoTelaPesquisa");
	
		
        return retorno;
    }
    
   

}
 
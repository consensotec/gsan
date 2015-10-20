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
package gcom.gui.faturamento;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.gui.GcomAction;

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
 * Action que remove a localidade ou o setor ou a quadra
 * do filtrar realtorio analitico faturamento
 * 
 * @author Fl�vio Cordeiro
 * @created 23 de Maio de 2007
 */
public class RemoverAnaliticoRelatorioRegistroAction extends GcomAction {

    /**
     * < <Descri��o do m�todo>>
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping
                .findForward("exibirPagina");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        
        if(httpServletRequest.getParameter("habilitaBotao") != null){
        	if(httpServletRequest.getParameter("habilitaBotao").equalsIgnoreCase("S")){
        		sessao.removeAttribute("bloqueiaSetor");
    			sessao.removeAttribute("bloqueiaQuadra");
    			sessao.removeAttribute("bloqueiaLocalidade");
        	}else if(httpServletRequest.getParameter("habilitaBotao").equalsIgnoreCase("N")){
        		sessao.setAttribute("bloqueiaSetor", "s");
    			sessao.setAttribute("bloqueiaQuadra", "s");
    			sessao.setAttribute("bloqueiaLocalidade", "s");
    			sessao.setAttribute("colecaoLocalidades",new ArrayList());
    			sessao.setAttribute("colecaoSetor",new ArrayList());
    			sessao.setAttribute("colecaoQuadras",new ArrayList());
        	}
        }

        if(httpServletRequest.getParameter("idRemocaoLocalidade") != null){
        	String idLocalidade = httpServletRequest.getParameter("idRemocaoLocalidade");
        	Collection colecaoLocalidades = (Collection) sessao.getAttribute("colecaoLocalidades");
        	Iterator iterator = colecaoLocalidades.iterator();
        	while(iterator.hasNext()){
        		Localidade localidade = (Localidade) iterator.next();
        		String id = localidade.getId().toString();
        		if(idLocalidade.equalsIgnoreCase(id)){
        			iterator.remove();
        			break;
        		}
        	}
        	sessao.setAttribute("colecaoLocalidades",colecaoLocalidades);
        	if(colecaoLocalidades.size() > 1){
    			sessao.setAttribute("bloqueiaSetor", "s");
    			sessao.setAttribute("bloqueiaQuadra", "s");
    		}else if(colecaoLocalidades.size() == 1){
    			sessao.removeAttribute("bloqueiaSetor");
    			sessao.removeAttribute("bloqueiaQuadra");
    		}
        }else
        if (httpServletRequest.getParameter("idRemocaoSetor") != null){
        	String idSetor = httpServletRequest.getParameter("idRemocaoSetor");
        	Collection colecaoSetor = (Collection) sessao.getAttribute("colecaoSetor");
        	Iterator iterator = colecaoSetor.iterator();
        	while(iterator.hasNext()){
        		SetorComercial setorComercial = (SetorComercial) iterator.next();
        		String codigo = setorComercial.getCodigo() + "";
        		if(idSetor.equalsIgnoreCase(codigo)){
        			iterator.remove();
        			break;
        		}
        	}
        	sessao.setAttribute("colecaoSetor",colecaoSetor);
        	if(colecaoSetor.size() > 1){
    			sessao.setAttribute("bloqueiaQuadra", "s");
    			sessao.setAttribute("bloqueiaLocalidade", "s");
    		}else if(colecaoSetor.size() == 1){
    			sessao.removeAttribute("bloqueiaQuadra");
    			sessao.setAttribute("bloqueiaLocalidade", "s");
    		}else if(colecaoSetor.isEmpty()){
    			sessao.removeAttribute("bloqueiaLocalidade");
    		}
        }else
        if (httpServletRequest.getParameter("idRemocaoQuadra") != null){
        	String idQuadra = httpServletRequest.getParameter("idRemocaoQuadra");
        	Collection colecaoQuadras = (Collection) sessao.getAttribute("colecaoQuadras");
        	Iterator iterator = colecaoQuadras.iterator();
        	while(iterator.hasNext()){
        		Quadra quadra = (Quadra) iterator.next();
        		String id = quadra.getNumeroQuadra() + "";
        		if(idQuadra.equalsIgnoreCase(id)){
        			iterator.remove();
        			break;
        		}
        	}
        	if(colecaoQuadras.isEmpty()){
        		sessao.removeAttribute("bloqueiaSetor");
        	}else{
        		sessao.setAttribute("bloqueiaSetor", "s");
        	}
        	sessao.setAttribute("colecaoQuadras",colecaoQuadras);
        }

        return retorno;
    }

}

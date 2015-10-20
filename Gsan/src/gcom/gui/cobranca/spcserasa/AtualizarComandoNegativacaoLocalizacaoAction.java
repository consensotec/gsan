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
package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade validar as informa��es da 4� aba do processo de inser��o
 * de um Comando de Negativa��o
 *
 * @author Ana Maria	
 * @date 06/11/2007
 */
public class AtualizarComandoNegativacaoLocalizacaoAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("");
        
        Fachada fachada = Fachada.getInstancia();
		
        AtualizarComandoNegativacaoPorCriterioActionForm form = (AtualizarComandoNegativacaoPorCriterioActionForm) actionForm;		
		
        //Pesquisa Localidade Incial
        String idLocalidadeInicial= form.getIdLocalidadeInicial();
        if(idLocalidadeInicial != null && !idLocalidadeInicial.equals("")){
        	
        	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();              
        	filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeInicial));         
            Collection colecaoLocalidade = fachada.pesquisar(
            		filtroLocalidade,Localidade.class.getName());
            
            if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
            	throw new ActionServletException("atencao.pesquisa.localidade_inicial_inexistente");         	
			} 
        }
        
        //Pesquisa Localidade Final
        String idLocalidadeFinal= form.getIdLocalidadeFinal();
        if(idLocalidadeFinal != null && !idLocalidadeFinal.equals("")){
        	
        	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();              
        	filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeFinal));            
            Collection colecaoLocalidade = fachada.pesquisar(
            		filtroLocalidade,Localidade.class.getName());
            
            if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
            	throw new ActionServletException("atencao.pesquisa.localidade_final_inexistent");               	
			}
        } 
        
      	//Pesquisa Setor Comercial inicial
        String codigoSetorComercialInicial = form.getCodigoSetorComercialInicial();
        if ((codigoSetorComercialInicial != null && !codigoSetorComercialInicial.toString().trim().equalsIgnoreCase(""))
    		&& (idLocalidadeInicial != null && !idLocalidadeInicial.toString().trim().equalsIgnoreCase(""))) {
    	
    		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();		
    		filtroSetorComercial.adicionarParametro(new ParametroSimples(
    				FiltroSetorComercial.ID_LOCALIDADE, new Integer(idLocalidadeInicial)));	
    		filtroSetorComercial.adicionarParametro(new ParametroSimples(
    				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(codigoSetorComercialInicial)));
    		
    		Collection<SetorComercial> setorComerciais = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
    				
    		if (setorComerciais == null || setorComerciais.isEmpty()) {
            	throw new ActionServletException("atencao.pesquisa.setor_inicial_inexistente");  			
    		} 
    	}
        
      	//Pesquisa Setor Comercial final
        String codigoSetorComercialFinal = form.getCodigoSetorComercialFinal();
        if ((codigoSetorComercialFinal != null && !codigoSetorComercialFinal.toString().trim().equalsIgnoreCase(""))
        		&& (idLocalidadeFinal != null && !idLocalidadeFinal.toString().trim().equalsIgnoreCase(""))) {
        	
        		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();		
        		filtroSetorComercial.adicionarParametro(new ParametroSimples(
        				FiltroSetorComercial.ID_LOCALIDADE, new Integer(idLocalidadeFinal)));	
        		filtroSetorComercial.adicionarParametro(new ParametroSimples(
        				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(codigoSetorComercialFinal)));
        		
        		Collection<SetorComercial> setorComerciais = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
        				
        		if (setorComerciais == null || setorComerciais.isEmpty()) {
                	throw new ActionServletException("atencao.pesquisa.setor_final_inexistente");  			
        		} 
        }	
	
        return retorno;
	}

}

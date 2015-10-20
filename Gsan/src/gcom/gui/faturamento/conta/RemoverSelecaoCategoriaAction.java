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
package gcom.gui.faturamento.conta;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class RemoverSelecaoCategoriaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	
    	//Define o caso de uso que receber� o resultado desta remo��o
    	String mapeamentoStruts = httpServletRequest.getParameter("mapeamento");

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward(mapeamentoStruts);

        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        String idCategoria = httpServletRequest.getParameter("idCategoria");
        
        this.removerCategoria(idCategoria, sessao);
        
        String idSubcategoria = httpServletRequest.getParameter("idSubcategoria");
        
        this.removerSubcategoria(idSubcategoria, sessao);
        
        //Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
        
        /*
		 * Colocado por Raphael Rossiter em 29/03/2007
		 * Objetivo: Manipula��o dos objetos que ser�o exibidos no formul�rio de acordo com a empresa
		 */
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		httpServletRequest.setAttribute("empresaNome", sistemaParametro.getNomeAbreviadoEmpresa().trim());
        
        
        return retorno;
    }
    
    
    
    private void removerCategoria(String idCategoria, HttpSession sessao){
    	
    	if (idCategoria != null && !idCategoria.equalsIgnoreCase("") &&
            sessao.getAttribute("colecaoCategoria") != null){
            	
            Collection colecaoCategoria = (Collection) sessao.getAttribute("colecaoCategoria");
            Categoria categoriaSelect = new Categoria();
            categoriaSelect.setId(new Integer(idCategoria));
            	
            colecaoCategoria.remove(categoriaSelect);
            if(colecaoCategoria.isEmpty() || colecaoCategoria == null){
            	sessao.setAttribute("colecao", 1);
            	sessao.removeAttribute("adicionar");
            }
            else{
            	sessao.removeAttribute("existeColecao");
            }
        }
    }
    
    
    private void removerSubcategoria(String idSubcategoria, HttpSession sessao){
    	
    	if (idSubcategoria != null && !idSubcategoria.equalsIgnoreCase("") &&
            sessao.getAttribute("colecaoSubcategoria") != null){
            	
            Collection colecaoSubcategoria = (Collection) sessao.getAttribute("colecaoSubcategoria");
            
            //Pesquisa a subcategoria selecionada para carregar os atributos.
            FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
            
            filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade("categoria");
            
            filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID,
            idSubcategoria));
            
            Collection colecaoSubCategoriaSelected = Fachada.getInstancia().pesquisar(filtroSubCategoria, 
            Subcategoria.class.getName());
            
            Subcategoria subcategoriaSelect = (Subcategoria) Util.retonarObjetoDeColecao(colecaoSubCategoriaSelected);
            	
            colecaoSubcategoria.remove(subcategoriaSelect);
            
            if(colecaoSubcategoria.isEmpty() || colecaoSubcategoria == null){
            	sessao.setAttribute("colecao", 1);
            	sessao.removeAttribute("adicionar");
            }
            else{
            	sessao.removeAttribute("existeColecao");
            }
            
            Collections.sort((List) colecaoSubcategoria, new Comparator() {
				public int compare(Object a, Object b) {
					Subcategoria subcategoria1 = (Subcategoria) a;
					Subcategoria subcategoria2 = (Subcategoria) b;
					
					int comparacaoCategoria = subcategoria1.getCategoria().getDescricao()
					.compareTo(subcategoria2.getCategoria().getDescricao());
					
					if (comparacaoCategoria == 0){
				
						return subcategoria1.getDescricao()
    					.compareTo(subcategoria2.getDescricao());
						
					}
					else{
						
						return comparacaoCategoria;
					}
				}
			});
        }
    }

}



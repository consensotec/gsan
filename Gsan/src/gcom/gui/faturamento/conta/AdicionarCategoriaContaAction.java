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
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AdicionarCategoriaContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirAdicionarCategoriaConta");

        Fachada fachada = Fachada.getInstancia();
        
        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        //Inst�ncia do formul�rio que est� sendo utilizado
        AdicionarCategoriaContaActionForm adicionarCategoriaContaActionForm = 
        (AdicionarCategoriaContaActionForm) actionForm;
        
        //Par�metros recebidos para adicionar uma categoria
        String idCategoria = adicionarCategoriaContaActionForm.getCategoriaID();
        String qtdEconomias = adicionarCategoriaContaActionForm.getQtdEconomia();
        
        
        //Valida��o dos campos recebidos
        if (idCategoria == null || idCategoria.equalsIgnoreCase("")){
        	throw new ActionServletException(
                    "atencao.campo_texto.obrigatorio", null, "categoria");
        }
        
        if (qtdEconomias == null || qtdEconomias.equalsIgnoreCase("")){
        	throw new ActionServletException(
                    "atencao.campo_texto.obrigatorio", null, "quantidade de economias");
        }
        
        
        /*
		 * Colocado por Raphael Rossiter em 14/03/2007
		 * Objetivo: Manipula��o dos objetos que ser�o exibidos no formul�rio de acordo com a empresa
		 */
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA)){
			
			String idSubcategoria = adicionarCategoriaContaActionForm.getSubcategoriaID();
		
			//Valida��o dos campos recebidos
	        if (idSubcategoria == null || idSubcategoria.equalsIgnoreCase("")){
	        	throw new ActionServletException(
	                    "atencao.campo_texto.obrigatorio", null, "subcategoria");
	        }
	        
	        this.adicionarSubCategoria(idSubcategoria, qtdEconomias, sessao, httpServletRequest);
		}
		else{
			
			this.adicionarCategoria(idCategoria, qtdEconomias, sessao, httpServletRequest);
		}
        
        
        
        
        //Limpa o formul�rio que adiciona categorias a conta.
        adicionarCategoriaContaActionForm.setCategoriaID("");
        adicionarCategoriaContaActionForm.setSubcategoriaID("");
        adicionarCategoriaContaActionForm.setQtdEconomia("");
        sessao.setAttribute("retorno","sim");
        sessao.setAttribute("adicionar","1");
        
        
        /*
		 * Colocado por Raphael Rossiter em 29/03/2007
		 * Objetivo: Manipula��o dos objetos que ser�o exibidos no formul�rio de acordo com a empresa
		 */
//		httpServletRequest.setAttribute("empresaNome", sistemaParametro.getNomeAbreviadoEmpresa().trim());
        
        httpServletRequest.setAttribute("indicadorTarifaCategoria", sistemaParametro.getIndicadorTarifaCategoria().toString());
        
        return retorno;
    }
    
    
    
    private void adicionarCategoria(String idCategoria, String qtdEconomias, HttpSession sessao,
    		HttpServletRequest httpServletRequest){
    	
    	//Pesquisa a categoria selecionada para carregar os atributos.
        FiltroCategoria filtroCategoria = new FiltroCategoria();
        
        filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO,
        idCategoria));
        
        Collection colecaoCategoriaSelected = Fachada.getInstancia().pesquisar(filtroCategoria, 
        Categoria.class.getName());
        
        Categoria categoriaSelected = (Categoria) Util.retonarObjetoDeColecao(colecaoCategoriaSelected);
        
        //Carrega a categoria com a quantidade de economias informada pelo usu�rio
        categoriaSelected.setQuantidadeEconomiasCategoria(new Integer(qtdEconomias));
        
        //Carrega o novo objeto na sess�o.
        if (sessao.getAttribute("colecaoCategoria") == null){
        	Collection colecaoCategoria = new Vector();
        	colecaoCategoria.add(categoriaSelected);
        	sessao.setAttribute("colecaoCategoria", colecaoCategoria);
        	
        	httpServletRequest.setAttribute("reloadPage", "OK");
        	
        	//Definindo o caso de uso que receber� o retorno
        	if (sessao.getAttribute("UseCase").equals("INSERIRCONTA")){
        		httpServletRequest.setAttribute("reloadPageURL", "INSERIRCONTA");
        	}
        	else if (sessao.getAttribute("UseCase").equals("RETIFICARCONTA")) {
        		httpServletRequest.setAttribute("reloadPageURL", "RETIFICARCONTA");
        		
        	} else if (sessao.getAttribute("UseCase").equals("SIMULARCALCULOCONTA")) {
        		httpServletRequest.setAttribute("reloadPageURL", "SIMULARCALCULOCONTA");
        	}
        	sessao.setAttribute("totalEconomia",categoriaSelected.getQuantidadeEconomiasCategoria());
        }
        else{
        	Collection colecaoCategoria = (Collection) sessao.getAttribute("colecaoCategoria");
        	if (!colecaoCategoria.contains(categoriaSelected)){
        		colecaoCategoria.add(categoriaSelected);
        		httpServletRequest.setAttribute("reloadPage", "OK");
        		
        		//Definindo o caso de uso que receber� o retorno
            	if (sessao.getAttribute("UseCase").equals("INSERIRCONTA")){
            		httpServletRequest.setAttribute("reloadPageURL", "INSERIRCONTA");
            	}
            	else if (sessao.getAttribute("UseCase").equals("RETIFICARCONTA")) {
            		httpServletRequest.setAttribute("reloadPageURL", "RETIFICARCONTA");
            		
            	} else if (sessao.getAttribute("UseCase").equals("SIMULARCALCULOCONTA")) {
            		httpServletRequest.setAttribute("reloadPageURL", "SIMULARCALCULOCONTA");
            	}
            	Iterator colecaoCategoriaIt = colecaoCategoria.iterator();
            	Categoria categoria;
            	String qtdPorEconomia;
        		Integer qtdEconnomia = 0;
        		while (colecaoCategoriaIt.hasNext()) {
        			categoria = (Categoria) colecaoCategoriaIt.next();

        			if (categoria.getId() != null) {

        				qtdPorEconomia = categoria.getQuantidadeEconomiasCategoria().toString();

        				qtdEconnomia = Util.somaInteiros(qtdEconnomia,new Integer(qtdPorEconomia));
        			}
        		}
        		sessao.setAttribute("totalEconomia",qtdEconnomia);
        	}
        	// [FS0009] - Verificar categoria j� existente
        	else{
        		throw new ActionServletException(
                        "atencao.adicionar_categoria_ja_existente");
        	}
        }
    }
    
    
    
    private void adicionarSubCategoria(String idSubcategoria, String qtdEconomias, HttpSession sessao,
    		HttpServletRequest httpServletRequest){
    	
    	//Pesquisa a subcategoria selecionada para carregar os atributos.
        FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
        
        filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade("categoria");
        
        filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID,
        idSubcategoria));
        
        Collection colecaoSubCategoriaSelected = Fachada.getInstancia().pesquisar(filtroSubCategoria, 
        Subcategoria.class.getName());
        
        Subcategoria subcategoriaSelected = (Subcategoria) Util.retonarObjetoDeColecao(colecaoSubCategoriaSelected);
        
        //Carrega a subcategoria com a quantidade de economias informada pelo usu�rio
        subcategoriaSelected.setQuantidadeEconomias(new Integer(qtdEconomias));
        
        //Carrega o novo objeto na sess�o.
        if (sessao.getAttribute("colecaoSubcategoria") == null){
        	Collection colecaoSubcategoria = new Vector();
        	colecaoSubcategoria.add(subcategoriaSelected);
        	sessao.setAttribute("colecaoSubcategoria", colecaoSubcategoria);
        	
        	httpServletRequest.setAttribute("reloadPage", "OK");
        	
        	//Definindo o caso de uso que receber� o retorno
        	if (sessao.getAttribute("UseCase").equals("INSERIRCONTA")){
        		httpServletRequest.setAttribute("reloadPageURL", "INSERIRCONTA");
        	}
        	else if (sessao.getAttribute("UseCase").equals("RETIFICARCONTA")) {
        		httpServletRequest.setAttribute("reloadPageURL", "RETIFICARCONTA");
        		
        	} else if (sessao.getAttribute("UseCase").equals("SIMULARCALCULOCONTA")) {
        		httpServletRequest.setAttribute("reloadPageURL", "SIMULARCALCULOCONTA");
        	}
        	sessao.setAttribute("totalEconomia",subcategoriaSelected.getQuantidadeEconomias());
        }
        else{
        	Collection colecaoSubcategoria = (Collection) sessao.getAttribute("colecaoSubcategoria");
        	if (!colecaoSubcategoria.contains(subcategoriaSelected)){
        		colecaoSubcategoria.add(subcategoriaSelected);
        		httpServletRequest.setAttribute("reloadPage", "OK");
        		
        		//Definindo o caso de uso que receber� o retorno
            	if (sessao.getAttribute("UseCase").equals("INSERIRCONTA")){
            		httpServletRequest.setAttribute("reloadPageURL", "INSERIRCONTA");
            	}
            	else if (sessao.getAttribute("UseCase").equals("RETIFICARCONTA")) {
            		httpServletRequest.setAttribute("reloadPageURL", "RETIFICARCONTA");
            		
            	} else if (sessao.getAttribute("UseCase").equals("SIMULARCALCULOCONTA")) {
            		httpServletRequest.setAttribute("reloadPageURL", "SIMULARCALCULOCONTA");
            	}
            	Iterator colecaoSubcategoriaIt = colecaoSubcategoria.iterator();
            	Subcategoria subcategoria;
            	String qtdPorEconomia;
        		Integer qtdEconnomia = 0;
        		while (colecaoSubcategoriaIt.hasNext()) {
        			subcategoria = (Subcategoria) colecaoSubcategoriaIt.next();

        			if (subcategoria.getId() != null) {

        				qtdPorEconomia = subcategoria.getQuantidadeEconomias().toString();

        				qtdEconnomia = Util.somaInteiros(qtdEconnomia,new Integer(qtdPorEconomia));
        			}
        		}
        		sessao.setAttribute("totalEconomia",qtdEconnomia);
        		
        		
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
    					return comparacaoCategoria;
    				}
    			});
        		
        	}
        	// [FS0009] - Verificar subcategoria j� existente
        	else{
        		throw new ActionServletException(
                        "atencao.adicionar_subcategoria_ja_existente");
        	}
        }
    }

}

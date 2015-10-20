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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Description of the Class
 * 
 * @author Roberta Costa
 * @created 26 de Dezembro de 2005
 */
public class FiltrarCategoriaAction extends GcomAction {
    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping
                .findForward("retornarFiltroCategoria");

        //obtem instancia da fachada
        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        //Recupera os par�metros do form
        String id = (String) pesquisarActionForm.get("id");
        String descricao = (String) pesquisarActionForm.get("descricao");
        String tipoCategoria = (String) pesquisarActionForm.get("tipoCategoria");
        String indicadorUso = (String) pesquisarActionForm.get("indicadorUso");
        String atualizar = httpServletRequest.getParameter("atualizar");

        FiltroCategoria filtroCategoria  = new FiltroCategoria (FiltroCategoria.DESCRICAO);

        boolean peloMenosUmParametroInformado = false;

        //Insere os par�metros informados no filtro
        if(id != null && !id .trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroCategoria.adicionarParametro(new ParametroSimples(
                    FiltroCategoria.CODIGO, new Integer(id)));
        }
        if(tipoCategoria != null && !tipoCategoria.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
            peloMenosUmParametroInformado = true;
            filtroCategoria.adicionarParametro(new ComparacaoTexto(
                    FiltroCategoria.TIPO_CATEGORIA, tipoCategoria));
        }
        if(descricao != null && !descricao.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroCategoria.adicionarParametro(new ComparacaoTexto(
                    FiltroCategoria.DESCRICAO, descricao));
        }
        if(indicadorUso != null && !indicadorUso.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroCategoria.adicionarParametro(new ParametroSimples(
                    FiltroCategoria.INDICADOR_USO, indicadorUso));
        }

        //Erro caso o usu�rio mandou filtrar sem nenhum par�metro
        if(!peloMenosUmParametroInformado) {
            throw new ActionServletException(
                    "atencao.filtro.nenhum_parametro_informado");
        }else{
			Collection<Categoria> categoriasRetorno = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
			
			if(categoriasRetorno.isEmpty()){
				throw new ActionServletException(
	                    "atencao.pesquisa.nenhumresultado", null, "exce��o de categoria");
	        } else {
	        	httpServletRequest.setAttribute("categoriasFiltradas", categoriasRetorno);
				if (atualizar != null && categoriasRetorno.size() == 1)
		        {
		        	Categoria categoria = (Categoria) categoriasRetorno.iterator().next();
		        	httpServletRequest.setAttribute("idRegistroAtualizacao",
							categoria.getId());
		        	
		        	retorno = actionMapping
		            	.findForward("atualizarCategoria");
		        	
		        } else {
		        	retorno = actionMapping
						.findForward("retornarFiltroCategoria");
		        }
	        }    

			// Manda o filtro pelo request para o ExibirManterCategoriaAction
	        sessao.setAttribute("filtroCategoria", filtroCategoria);
	        httpServletRequest.setAttribute("filtroCategoria", filtroCategoria);
        }

        
        return retorno;
    }
}
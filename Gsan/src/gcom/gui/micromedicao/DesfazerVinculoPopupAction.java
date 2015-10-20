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

import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Atualizar Tipo de Rateio
 * 
 * @author Rafael Santos, Magno Gouveia
 * @since 12/01/2006, 20/08/2011
 */
public class DesfazerVinculoPopupAction extends GcomAction {
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

        ActionForward retorno = actionMapping.findForward("telaSucessoPopup");

        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        DesfazerVinculoPopupActionForm form = (DesfazerVinculoPopupActionForm) actionForm;
        
        String[] ids = form.getIdImovel();
        
        Imovel imovelCondominio = null;
        if(sessao.getAttribute("imovelCondominio") != null){
        	imovelCondominio = (Imovel) sessao.getAttribute("imovelCondominio") ;
        }
        
        boolean desvincular = false;
        if(ids.length == ((Collection) sessao.getAttribute("colecaoImoveisVinculados")).size()){
        	desvincular = true;
        } else {
        	/*
			 * SB0003 - FS 4. Caso a op��o de desvincular n�o seja TODOS, o
			 * sistema verifica se o im�vel com indicador de �rea comum foi
			 * selecionado, [FS0015 - Pesquisar Im�vel �rea Comum]
			 */
        	Integer idImovelAreaComum = fachada.pesquisarImovelAreaComum(imovelCondominio.getId());
        	if (idImovelAreaComum != null) {
        		for (String idImovelParaRemover : ids) {
        			
					if (Integer.parseInt(idImovelParaRemover.trim()) == idImovelAreaComum.intValue()) {
						throw new ActionServletException("atencao.imovel_selecionado_corresponde_area_comum", idImovelParaRemover);
					}
				}
        	}
        }
        
        /** alterado por pedro alexandre dia 20/11/2006 
         * Recupera o usu�rio logado para passar no met�do de desfazer v�nculo
         * para verificar se o usu�rio tem abrang�ncia para desfazer o v�nculo do im�vel
         * informado.
         */
        Usuario usuarioLogado = (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);
        fachada.desfazerVinculo(imovelCondominio, ids, desvincular, usuarioLogado);
        //fachada.desfazerVinculo(imovel,ids,desvincular);
        
        //remove da sessao
        if(sessao.getAttribute("colecaoImoveisVinculados") != null){
        	sessao.removeAttribute("colecaoImoveisVinculados");
        }
        if(sessao.getAttribute("imovelCondominio") != null){
        	sessao.removeAttribute("imovelCondominio") ;
        }
        
        //Monta a p�gina de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucessoPopup")) {
            montarPaginaSucesso(httpServletRequest,
            		ids.length + " im�vel(is) desvinculado(s) do im�vel condom�nio " + imovelCondominio.getId()+" com sucesso." ,
                    "",
            		"");
        }        
        
       return retorno;
    }
}
 
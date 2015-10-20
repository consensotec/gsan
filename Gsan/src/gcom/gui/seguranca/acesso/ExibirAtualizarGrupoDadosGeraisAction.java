/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.seguranca.acesso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupoPermissaoEspecial;
import gcom.seguranca.acesso.GrupoPermissaoEspecial;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroPemissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action responsável pela pré-exibição da primeira página
 * do processo de atualizar grupo.  
 *
 * @author Pedro Alexandre
 * @date 03/07/2006
 */
public class ExibirAtualizarGrupoDadosGeraisAction extends GcomAction {

	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * [UC0279] - Manter Grupo
	 *
	 * @author Pedro Alexandre
	 * @date 03/07/2006
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento para a tela de informar dados gerais do grupo
        ActionForward retorno = actionMapping.findForward("atualizarGrupoDadosGerais");
        
        DynaValidatorForm form = (DynaValidatorForm) actionForm;
        
        HttpSession sessao = httpServletRequest.getSession(false);
        Fachada fachada = Fachada.getInstancia();
        
        /*RM 3892.1 - Implantar Normas de Senhas no GSAN */
        SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
        if (sistemaParametro.getIndicadorPermissaoEspecialGrupo()==1) {	    	
	        String codGrupo=null;
	        if (httpServletRequest.getParameter("idRegistroAtualizacao")!=null){
	        	codGrupo = (String) httpServletRequest.getParameter("idRegistroAtualizacao");
	        } else {
	        	codGrupo = (String) sessao.getAttribute("codGrupo"); 
	        }
	        sessao.setAttribute("codGrupo", codGrupo);
	        
	        Collection<PermissaoEspecial> colecaoPermissaoEspecial = null;
			Collection<PermissaoEspecial> colecaoPermissaoEspecialDesalibitado = null;
			String[] permissaoEspecial=null;
			
			FiltroPemissaoEspecial filtroPemissaoEspecial = new FiltroPemissaoEspecial(FiltroPemissaoEspecial.DESCRICAO);
			filtroPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroPemissaoEspecial.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO)); 
	
			colecaoPermissaoEspecial = fachada.pesquisar(filtroPemissaoEspecial,PermissaoEspecial.class.getName());
			
			FiltroGrupoPermissaoEspecial filtro = new FiltroGrupoPermissaoEspecial();
			filtro.adicionarParametro(new ParametroSimples(FiltroGrupoPermissaoEspecial.GRUPO_ID, Integer.parseInt(codGrupo)));
			Collection<GrupoPermissaoEspecial> colecaoPermissaoEspecialParaAtualizar = fachada.pesquisar(filtro, GrupoPermissaoEspecial.class.getName());
			
			if (colecaoPermissaoEspecialParaAtualizar != null
					&& !colecaoPermissaoEspecialParaAtualizar
							.isEmpty()) {
				
				Collection<PermissaoEspecial> colecaoPermissaoEspecialAux = new ArrayList<PermissaoEspecial>();
				
				Iterator<GrupoPermissaoEspecial> ite = colecaoPermissaoEspecialParaAtualizar.iterator();
				while(ite.hasNext()){
					GrupoPermissaoEspecial grupoPermissaoEspecial = (GrupoPermissaoEspecial)ite.next();
					FiltroPemissaoEspecial filtroPE = new FiltroPemissaoEspecial();
					filtroPE.adicionarParametro(new ParametroSimples(FiltroPemissaoEspecial.ID, grupoPermissaoEspecial.getComp_id().getPermissaoEspecialId()));
					Collection<PermissaoEspecial> colecaoPE = fachada.pesquisar(filtroPE, PermissaoEspecial.class.getName());
					colecaoPermissaoEspecialAux.add((PermissaoEspecial) Util.retonarObjetoDeColecao(colecaoPE));		
				}
				//seta os campos de permissão especial no form para aparecer no jsp como checado
				permissaoEspecial = Fachada
						.getInstancia()
						.retornarPermissoesMarcadas(
								colecaoPermissaoEspecialAux);
			}	
			
			form.set("permissoesEspeciais", permissaoEspecial);
					
			sessao.setAttribute("colecaoPermissaoEspecial",
					colecaoPermissaoEspecial);
			sessao.setAttribute("colecaoPermissaoEspecialDesalibitado",
					colecaoPermissaoEspecialDesalibitado);
        }
		//Fim RM 3892
		
        return retorno;
    }
}

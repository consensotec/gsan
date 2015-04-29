/**
 * 
 */
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
package gsan.gui.cobranca.spcserasa;

import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.FiltroUsuario;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 0653] Pesquisar Comando Negativa��o
 * 
 * @author K�ssia Albuquerque
 * @date 22/10/2007
 */


public class ExibirPesquisarComandoNegativacaoAction extends GcomAction {

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("pesquisarComandoNegativacao");	
			
			Fachada fachada = Fachada.getInstancia();
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			PesquisarComandoNegativacaoActionForm form = (PesquisarComandoNegativacaoActionForm) actionForm;
					
				
			// SETANDO OS CAMPOS QUE V�M MARCADO INICIALMENTE NO FORM	
			if (httpServletRequest.getParameter("menu") != null && !httpServletRequest.getParameter("menu").equals("")) {
				
				form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
				form.setIndicadorComandoSimulado(ConstantesSistema.TODOS.toString());
				
				if (httpServletRequest.getParameter("menu").equals("sim")) {
					form.setPopup(ConstantesSistema.NAO.toString());
					sessao.setAttribute("popup", ConstantesSistema.NAO.toString());
				} else if (httpServletRequest.getParameter("menu").equals("ok")) {
					
					form.setTituloComando("");
					form.setPeriodoExecucaoComandoInicial("");
					form.setPeriodoExecucaoComandoFinal("");
					form.setPeriodoGeracaoComandoInicial("");
					form.setPeriodoGeracaoComandoFinal("");
					form.setUsuarioResponsavelId("");
					form.setUsuarioResponsavelNome("");
					form.setPopup(ConstantesSistema.SIM.toString());
					sessao.setAttribute("popup", ConstantesSistema.SIM.toString());
				}
			}				
			
			if (httpServletRequest.getParameter("APAGAR")!= null){
				
				sessao.removeAttribute("collectionComandoNegativacao");
				sessao.removeAttribute("totalRegistrosPrimeiraPaginacao");
				sessao.removeAttribute("numeroPaginasPesquisaPrimeiraPaginacao");
			}
			
			
			// FILTRAR USUARIO RESPONSAVEL
			
			if (form.getUsuarioResponsavelId()!= null && !form.getUsuarioResponsavelId().equals("")){
				
				FiltroUsuario filtroUsuarioResponsavel = new FiltroUsuario();
				filtroUsuarioResponsavel.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, form.getUsuarioResponsavelId()));
				Collection colecaoUsuarioResponsavel = fachada.pesquisar(filtroUsuarioResponsavel, Usuario.class.getName());

				if (colecaoUsuarioResponsavel != null && !colecaoUsuarioResponsavel.isEmpty()) {
					
					Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuarioResponsavel);
					form.setUsuarioResponsavelNome(usuario.getNomeUsuario());
					sessao.setAttribute("usuarioResponsavelEncontrada", "true");
					
				} else {
					
					sessao.removeAttribute("usuarioResponsavelEncontrada");
					form.setUsuarioResponsavelNome("Usu�rio inexistente");
					form.setUsuarioResponsavelId("");
				}
			}
			
			//Recupera os dados do popup de usu�rio respons�vel
			if (httpServletRequest.getParameter("tipoConsulta") != null
					&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
				//Recupera os dados do popup de usu�rio		
				if (httpServletRequest.getParameter("tipoConsulta").equals(
							"usuario")) {

					form.setUsuarioResponsavelId(httpServletRequest.getParameter("idCampoEnviarDados"));
					form.setUsuarioResponsavelNome(httpServletRequest
							.getParameter("descricaoCampoEnviarDados"));	
					sessao.setAttribute("usuarioResponsavelEncontrada", "true");

				}				
			}

	
			return retorno;
	}

}

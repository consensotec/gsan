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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0503]Tramitar Conjunto de Registro de Atendimento
 * 
 * @author Ana Maria		
 * @date 10/01/2007
 */
public class ExibirRegistroAtendimentoTramitacaoPopupAction extends GcomAction {
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
		
		// Seta a a��o de retorno
		ActionForward retorno = actionMapping.findForward("registroAtendimentoTramitacaoPopup");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ConjuntoTramitacaoRaActionForm form = (ConjuntoTramitacaoRaActionForm) actionForm;
		
		// Ids do Registro de Atendimento e das Unidades Organizacionais Origem
		String[] ids = form.getIdRegistrosTramitacao();
		
		 //Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//[FS0002] Verificar as situa��es das OS associadas ao RA
		//[FS0003] Verificar se o tipo de solicita��o � Tarifa Social
		fachada.validarRATramitacao(ids, usuario.getId());
		
		String primeiraVez = (String)httpServletRequest.getParameter("primeiraVez");
		
        if(primeiraVez != null){
        	Date dataCorrente = new Date();
        	//Informa a data e a hora atual
			form.setDataTramitacao(Util.formatarData(dataCorrente));
			form.setHoraTramitacao(Util.formatarHoraSemSegundos(dataCorrente));
			httpServletRequest.setAttribute("corUsuario", "valor");
			form.setIdUsuarioResponsavel(usuario.getId().toString());
			form.setDescricaoUsuarioResponsavel(usuario.getNomeUsuario());
			httpServletRequest.setAttribute("nomeCampo", "idUsuarioResponsavel");
			
    		sessao.setAttribute("ids",	ids);	
        }
		
		//form.setIdRegistrosTramitacao(null);
		
        //[FS0004] Verificar exist�ncia da unidade organizacional
		String consultaUnidadeDestino = (String) httpServletRequest.getParameter("consultaUnidadeDestino");
		if (consultaUnidadeDestino != null
				&& !consultaUnidadeDestino.trim().equalsIgnoreCase("")
				&& (Integer.parseInt(consultaUnidadeDestino)) == 1) {
			
			// Filtro para obter o Unidade Superior ativo de id informado
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, new Integer(form.getIdUnidadeDestino())));

			// Pesquisa de acordo com os par�metros informados no filtro
			Collection colecaoUnidadeDestino = fachada.pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a cole��o
			if (colecaoUnidadeDestino != null && !colecaoUnidadeDestino.isEmpty()) {

				// Obt�m o objeto da cole��o pesquisada
				UnidadeOrganizacional unidadeDestino = (UnidadeOrganizacional) Util
						.retonarObjetoDeColecao(colecaoUnidadeDestino);

				// Exibe o c�digo e a descri��o pesquisa na p�gina
				httpServletRequest.setAttribute("corUnidadeDestino", "valor");
				form.setIdUnidadeDestino(unidadeDestino.getId().toString());
				form.setDescricaoUnidadeDestino(unidadeDestino.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeDestino");
			} else {
				// Exibe mensagem de c�digo inexiste e limpa o campo de c�digo
				httpServletRequest.setAttribute("corUnidadeDestino","exception");
				form.setIdUnidadeDestino("");
				form.setDescricaoUnidadeDestino("UNIDADE ORGANIZACIONAL INEXISTENTE");

				httpServletRequest.setAttribute("nomeCampo", "idUnidadeDestino");	
			}
		}	

        //[FS0005] Verificar exist�ncia do usu�rio
		String consultaUsuarioResponsavel = (String) httpServletRequest.getParameter("consultaUsuarioResponsavel");		
		if (consultaUsuarioResponsavel != null
				&& !consultaUsuarioResponsavel.trim().equalsIgnoreCase("")
				&& (Integer.parseInt(consultaUsuarioResponsavel)) == 1) {
			
			// Filtro para obter o Unidade Superior ativo de id informado
			FiltroUsuario filtroUsuario = new FiltroUsuario();

			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.ID, new Integer(form.getIdUsuarioResponsavel())));

			// Pesquisa de acordo com os par�metros informados no filtro
			Collection colecaoUsuarios = fachada.pesquisar(
					filtroUsuario, Usuario.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a cole��o
			if (colecaoUsuarios != null && !colecaoUsuarios.isEmpty()) {

				// Obt�m o objeto da cole��o pesquisada
				Usuario usuarioResponsavel = (Usuario) Util
						.retonarObjetoDeColecao(colecaoUsuarios);

				// Exibe o c�digo e a descri��o pesquisa na p�gina
				httpServletRequest.setAttribute("corUsuario", "valor");
				form.setIdUsuarioResponsavel(usuarioResponsavel.getId().toString());
				form.setDescricaoUsuarioResponsavel(usuarioResponsavel.getNomeUsuario());
				httpServletRequest.setAttribute("nomeCampo", "idUsuarioResponsavel");
			} else {
				// Exibe mensagem de c�digo inexiste e limpa o campo de c�digo
				httpServletRequest.setAttribute("corUsuario","exception");
				form.setIdUsuarioResponsavel("");
				form.setDescricaoUsuarioResponsavel("USU�RIO RESPONS�VEL INEXISTENTE");

				httpServletRequest.setAttribute("nomeCampo", "idUsuarioResponsavel");	
			}
		}		
		
		//Recupera os dados do popup de unidade organizacional
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			//Recupera os dados do popup de unidade organizacional
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"unidadeOrganizacional")) {

				form.setIdUnidadeDestino(httpServletRequest.getParameter("idCampoEnviarDados"));
				form.setDescricaoUnidadeDestino(httpServletRequest
						.getParameter("descricaoCampoEnviarDados"));				

			}	
			//Recupera os dados do popup de usu�rio		
			if (httpServletRequest.getParameter("tipoConsulta").equals(
						"usuario")) {

				form.setIdUsuarioResponsavel(httpServletRequest.getParameter("idCampoEnviarDados"));
				form.setDescricaoUsuarioResponsavel(httpServletRequest
						.getParameter("descricaoCampoEnviarDados"));				

			}				
		}
		return retorno;
	}
}

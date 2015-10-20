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
package gcom.gui.seguranca.acesso.usuario;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetornarUsuarioPesquisarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Inicializacoes de variaveis
		ActionForward retorno = actionMapping
				.findForward("retornoPesquisa");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Fachada fachada = Fachada.getInstancia();
		
		PesquisarUsuarioActionForm form = (PesquisarUsuarioActionForm) actionForm;
		
		String tipoUsuario = form.getTipoUsuario();
		String nomeUsuario = form.getNome();
		String matriculaFuncionario = form.getMatriculaFuncionario();
		String tipoPesquisa = (String) form.getTipoPesquisa();
		String login = form.getLogin();
		String idUnidadeOrganizacional = form.getIdUnidadeOrganizacional();
		
		//Matr�cula mair q zero 
		if (matriculaFuncionario.equals("0")){
			throw new ActionServletException("atencao.matricula_usuario_maior_zero");
		}
		
		FiltroUsuario filtroUsuario = new FiltroUsuario();		
		
		boolean peloMenosUm = false;
		
		//Pesquisa usuarioTipo
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioTipo");
		if(tipoUsuario != null && (new Integer(tipoUsuario).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO)){
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.USUARIO_TIPO_ID, tipoUsuario));
			peloMenosUm = true;
		}
		
		//Pesquisa nomeUsuario		
		if(nomeUsuario != null && !nomeUsuario.equals("")){
			peloMenosUm = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroUsuario.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroUsuario.NOME_USUARIO, nomeUsuario));
			} else {
				filtroUsuario.adicionarParametro(new ComparacaoTexto(
						FiltroUsuario.NOME_USUARIO, nomeUsuario));
			}
			
		}
		
		if(login != null && !login.equals("")){
			peloMenosUm = true;
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, login));
		}
		
		//Pesquisa matriculaFuncionario
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("funcionario");
		if(matriculaFuncionario != null && !matriculaFuncionario.equals("")){
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.FUNCIONARIO_ID, matriculaFuncionario));
			peloMenosUm = true;
		}
		
		if(idUnidadeOrganizacional != null && !idUnidadeOrganizacional.equals("")){
			peloMenosUm = true;
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.UNIDADE_ORGANIZACIONAL_ID, idUnidadeOrganizacional));
		}
		
		if (!peloMenosUm){
			throw new ActionServletException(
				"atencao.filtro.nenhum_parametro_informado");
		}
		
		//
		
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroUsuario, Usuario.class.getName());
		Collection collectionUsuario = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		//Valida��es 
		if (collectionUsuario == null || collectionUsuario.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "usuario");
		} else {
			sessao.setAttribute("collectionUsuario", collectionUsuario);

		}		
		
		String popup = (String) sessao.getAttribute("popup");
		if (popup != null && popup.equals("2")) {
			sessao.setAttribute("popup", popup);
		} else {
			sessao.removeAttribute("popup");
		}
		
		return retorno;
	}

}

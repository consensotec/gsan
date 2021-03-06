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
package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 02/05/2006
 */

public class ExibirAdicionarFuncionalidadeDependenciaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		AdicionarFuncionalidadeDependenciaActionForm adicionarFuncionalidadeDependenciaActionForm = (AdicionarFuncionalidadeDependenciaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		String parametro = null;

		String funcionalidadeID = null;
		
		if (httpServletRequest
				.getParameter("idFuncionalidade") == null || httpServletRequest
				.getParameter("idFuncionalidade").equals("")){
			funcionalidadeID = (String) sessao.getAttribute("idFuncionalidade");				
		} else {
			funcionalidadeID = (String) httpServletRequest
				.getParameter("idFuncionalidade");
			sessao.setAttribute("idFuncionalidade", funcionalidadeID);
		}
		
		adicionarFuncionalidadeDependenciaActionForm.setFuncionalidadeID(funcionalidadeID);

		//httpServletRequest.setAttribute("idFuncionalidade", funcionalidadeID);

		if (httpServletRequest.getParameter("funcionalidade") != null) {

			parametro = httpServletRequest.getParameter("funcionalidade");
			sessao.setAttribute("funcionalidade", parametro);
		}

		if (((String) sessao.getAttribute("funcionalidade"))
				.equalsIgnoreCase("inserir")) {
			retorno = actionMapping
					.findForward("inserirAdicionarFuncionalidadeDependenciaAction");
		} else {
			retorno = actionMapping
					.findForward("atualizarAdicionarFuncionalidadeDependenciaAction");
		}

		String idFuncionalidade = null;

		// Verifica se o tipo da consulta de arrecadador � de imovel
		// se for os parametros de enviarDadosParametros ser�o mandados para
		// a pagina arrecadador_pesuisar.jsp

		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& httpServletRequest.getParameter("tipoConsulta").equals(
						"funcionalidade")) {

			idFuncionalidade = httpServletRequest
					.getParameter("idCampoEnviarDados");

			// pesquisarFuncionalidadeActionFormidFuncionalidade
			// .setInscricaoImovel(httpServletRequest
			// .getParameter("descricaoCampoEnviarDados"));

			// }
			// idFuncionalidade = (String) httpServletRequest
			// .getParameter("funcionalidadeID");
		} else {

			idFuncionalidade = adicionarFuncionalidadeDependenciaActionForm
					.getComp_id();
		}

		if (idFuncionalidade != null && !idFuncionalidade.equals("")) {

			FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();

			filtroFuncionalidade.adicionarParametro(new ParametroSimples(
					FiltroFuncionalidade.ID, idFuncionalidade));

			Collection<Funcionalidade> colecaoFuncionalidade = fachada
					.pesquisar(filtroFuncionalidade, Funcionalidade.class
							.getName());

			if (colecaoFuncionalidade != null
					&& !colecaoFuncionalidade.isEmpty()) {

				// a Funcionalidade foi encontrada
				Funcionalidade funcionalidade = (Funcionalidade) Util
						.retonarObjetoDeColecao(colecaoFuncionalidade);

				adicionarFuncionalidadeDependenciaActionForm.setComp_id(String
						.valueOf(funcionalidade.getId()));
				adicionarFuncionalidadeDependenciaActionForm
						.setDescricaoFuncionalidade(funcionalidade
								.getDescricao());

				httpServletRequest.setAttribute(
						"funcionalidadeDependenciaEncontrada", "true");

				sessao.setAttribute("colecaoFuncionalidade",
						colecaoFuncionalidade);

			} else {
				// a Funcionalidade n�o foi encontrada
				adicionarFuncionalidadeDependenciaActionForm.setComp_id("");

				httpServletRequest.setAttribute(
						"funcionalidadeDependenciaNaoEncontrada", "exception");

				adicionarFuncionalidadeDependenciaActionForm
						.setDescricaoFuncionalidade("FUNCIONALIDADE INEXISTENTE");
			}

		}

		// if (httpServletRequest.getParameter("reload") != null) {
		// httpServletRequest.setAttribute("reload", true);

		// }

		return retorno;

	}

}

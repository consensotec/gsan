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
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeDependencia;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
 * @date 03/05/2006
 */
public class AdicionarFuncionalidadeDependenciaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		AdicionarFuncionalidadeDependenciaActionForm adicionarFuncionalidadeDependenciaActionForm = (AdicionarFuncionalidadeDependenciaActionForm) actionForm;

		String idFuncionalidade = adicionarFuncionalidadeDependenciaActionForm
				.getComp_id();

		Collection colecaoFuncionalidade = null;
		Collection colecaoFuncionalidadeDependencia = null;

		String funcionalidadeID = adicionarFuncionalidadeDependenciaActionForm
				.getFuncionalidadeID();

		if (sessao.getAttribute("funcionalidade").equals("inserir")) {

			retorno = actionMapping
					.findForward("inserirFuncionalidadeDependenciaAction");

			// setar no request um atributo informando qdo ele vem do inserir e
			// qdo
			// ele vem do atualizar pra seguir
			// passos diferentes

			if (sessao.getAttribute("colecaoFuncionalidadeTela") != null) {
				colecaoFuncionalidade = (Collection) sessao
						.getAttribute("colecaoFuncionalidadeTela");
			} else {
				colecaoFuncionalidade = new ArrayList();
			}

			// testa se a funcionalidade ja foi adicionada
			if (idFuncionalidade != null
					&& !idFuncionalidade.equalsIgnoreCase("")) {

				if (colecaoFuncionalidade != null
						&& !colecaoFuncionalidade.isEmpty()) {

					Funcionalidade funcionalidade = null;
					Iterator iterator = colecaoFuncionalidade.iterator();

					while (iterator.hasNext()) {

						funcionalidade = (Funcionalidade) iterator.next();

						if (funcionalidade.getId().equals(
								new Integer(idFuncionalidade))) {
							// Esta funcionalidade ja foi informada
							throw new ActionServletException(
									"atencao.funcionalidade.ja_informada");
						}

					}

				}

				FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
				filtroFuncionalidade.adicionarParametro(new ParametroSimples(
						FiltroFuncionalidade.ID, idFuncionalidade));

				colecaoFuncionalidade = fachada.pesquisar(filtroFuncionalidade,
						Funcionalidade.class.getName());
			}

			if (colecaoFuncionalidade != null
					&& !colecaoFuncionalidade.isEmpty()) {
				sessao.setAttribute("colecaoFuncionalidade",
						colecaoFuncionalidade);
				httpServletRequest.setAttribute("reload", true);
			}

		} else {

			retorno = actionMapping
					.findForward("atualizarFuncionalidadeDependenciaAction");

			httpServletRequest.setAttribute("idFuncionalidade",
					funcionalidadeID);

			if (sessao.getAttribute("colecaoFuncionalidadeTela") != null) {
				colecaoFuncionalidadeDependencia = (Collection) sessao
						.getAttribute("colecaoFuncionalidadeTela");
			} else {
				colecaoFuncionalidadeDependencia = new ArrayList();
			}

			Funcionalidade funcionalidadePrincipal = (Funcionalidade) sessao
					.getAttribute("funcionalidadeAtualizar");
 
			// testa se a funcionalidade ja foi adicionada
			if (idFuncionalidade != null
					&& !idFuncionalidade.equalsIgnoreCase("")) {

				if (funcionalidadePrincipal.getId().equals(
						new Integer(idFuncionalidade))) {
					// Esta funcionalidade � igual a principal
					throw new ActionServletException(
							"atencao.funcionalidade.igual_principal");
				}

				if (colecaoFuncionalidadeDependencia != null
						&& !colecaoFuncionalidadeDependencia.isEmpty()) {

					FuncionalidadeDependencia funcionalidade = null;
					Iterator iterator = colecaoFuncionalidadeDependencia
							.iterator();

					while (iterator.hasNext()) {

						funcionalidade = (FuncionalidadeDependencia) iterator
								.next();

						Integer idFuncionalidadeDependencia = null;

						if (funcionalidade.getComp_id() != null) {
							idFuncionalidadeDependencia = funcionalidade
									.getComp_id()
									.getFuncionalidadeDependenciaId();
						} else {
							idFuncionalidadeDependencia = funcionalidade
									.getFuncionalidadeDependencia().getId();
						}

						if (idFuncionalidadeDependencia.equals(new Integer(
								idFuncionalidade))) {
							// Esta funcionalidade ja foi informada
							throw new ActionServletException(
									"atencao.funcionalidade.ja_informada");
						}

					}

				}

				FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
				filtroFuncionalidade.adicionarParametro(new ParametroSimples(
						FiltroFuncionalidade.ID, idFuncionalidade));

				colecaoFuncionalidade = fachada.pesquisar(filtroFuncionalidade,
						Funcionalidade.class.getName());
			}

			if (colecaoFuncionalidade != null
					&& !colecaoFuncionalidade.isEmpty()) {
				FuncionalidadeDependencia funcionalidadeDependencia = new FuncionalidadeDependencia();
				funcionalidadeDependencia
						.setFuncionalidadeDependencia((Funcionalidade) colecaoFuncionalidade
								.iterator().next());
				colecaoFuncionalidadeDependencia.add(funcionalidadeDependencia);
				sessao.setAttribute("colecaoFuncionalidadeTela",
						colecaoFuncionalidadeDependencia);
				httpServletRequest.setAttribute("reload", true);
			}

		}

		sessao.setAttribute("adicionar","sim");
		
		return retorno;
	}
}

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
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pr�-processamento da p�gina de pesquisa de cliente
 * 
 * @author Thiago Ten�rio
 * @created 25 de Abril de 2005
 */
public class ExibirPesquisarUnidadeEmpresaAction extends GcomAction {
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("pesquisarUnidadeEmpresa");
		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Parte que passa as cole��es necess�rias no jsp
		//FiltroUnidadeNivel filtroUnidadeNivel = new FiltroUnidadeNivel();
		/*--<merge>--Collection colecaoUnidadesNiveis = fachada.pesquisar(
				filtroUnidadeNivel, UnidadeNivel.class.getName());*/
		Collection colecaoUnidadesNiveis = null;

		if (colecaoUnidadesNiveis != null && !colecaoUnidadesNiveis.isEmpty()) {
			sessao.setAttribute("colecaoUnidadesNiveis", colecaoUnidadesNiveis);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Unidade N�vel");
		}
		
		UnidadeEmpresaActionForm unidadeEmpresaActionForm = (UnidadeEmpresaActionForm) actionForm;
		
		String idUnidadeSuperior = unidadeEmpresaActionForm.getIdUnidadeSuperior();
		
		if (idUnidadeSuperior != null && !idUnidadeSuperior.equals("")) {
			FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();
			filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR, idUnidadeSuperior));
			Collection colecaoUnidadeSuperior = fachada.pesquisar(
					filtroUnidadeEmpresa, UnidadeOrganizacional.class.getName());
			if (colecaoUnidadeSuperior != null && !colecaoUnidadeSuperior.isEmpty()){
				UnidadeOrganizacional unidadeEmpresa = (UnidadeOrganizacional) colecaoUnidadeSuperior.iterator().next();				
				unidadeEmpresaActionForm.setDescricaoUnidadeSuperior(unidadeEmpresa.getDescricao());
			} else {
				httpServletRequest.setAttribute("unidadeSuperiorInexistente", true);
				unidadeEmpresaActionForm.setIdUnidadeSuperior("");
				unidadeEmpresaActionForm.setDescricaoUnidadeSuperior("UNIDADE INEXISTENTE");
			}
		}
		
		
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaUnidadeEmpresa") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaUnidadeEmpresa",
					httpServletRequest
							.getParameter("caminhoRetornoTelaPesquisaUnidadeEmpresa"));

		}

		if (httpServletRequest.getParameter("limpaForm") != null){
			unidadeEmpresaActionForm.setCodigoUnidade("");
			unidadeEmpresaActionForm.setIdUnidadeSuperior("");
			unidadeEmpresaActionForm.setNivelHiearquia(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString());
			unidadeEmpresaActionForm.setNomeUnidade("");
			unidadeEmpresaActionForm.setSiglaUnidade("");

		}
		
		if (httpServletRequest.getAttribute("popup") != null){
			sessao.setAttribute("popup", httpServletRequest.getAttribute("popup"));
		}

		httpServletRequest.setAttribute("nomeCampo", "codigoUnidade");
		
		return retorno;
	}
}

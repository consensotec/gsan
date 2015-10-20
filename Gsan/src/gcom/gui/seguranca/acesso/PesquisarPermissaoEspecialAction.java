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
import gcom.seguranca.acesso.FiltroPermissaoEspecial;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1046] Pesquisar Permissao Especial
 * 
 * @author Daniel Alves
 * @date 22/07/2010
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */
public class PesquisarPermissaoEspecialAction extends GcomAction {

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
				.findForward("exibirResultadoPesquisaPermissaoEspecialAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarPermissaoEspecialActionForm pesquisarPermissaoEspecialActionForm = (PesquisarPermissaoEspecialActionForm) actionForm;

		FiltroPermissaoEspecial filtroPermissaoEspecial = new FiltroPermissaoEspecial();

		Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String codigo = pesquisarPermissaoEspecialActionForm.getCodigo();

		String descricao = pesquisarPermissaoEspecialActionForm.getDescricao();

		String codigoOperacao = pesquisarPermissaoEspecialActionForm.getCodigoOperacao();
		
		String tipoPesquisa = pesquisarPermissaoEspecialActionForm.getTipoPesquisa();
		// Verifica se o campo codigo foi informado

		if (codigo != null
				&& !codigo.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroPermissaoEspecial.adicionarParametro(new ParametroSimples(
					FiltroPermissaoEspecial.ID, codigo));

		}

		// Verifica se o campo descricao foi informado

				
		
		if (descricao != null
				&& !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			
			 if (tipoPesquisa != null
						&& tipoPesquisa
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {

				    filtroPermissaoEspecial
							.adicionarParametro(new ComparacaoTextoCompleto(
									FiltroPermissaoEspecial.DESCRICAO, descricao));
				} else {

					filtroPermissaoEspecial.adicionarParametro(new ComparacaoTexto(
							FiltroPermissaoEspecial.DESCRICAO, descricao));
				}

		}

		// Verifica se o campo modulo foi informado

		if (codigoOperacao != null
				&& !codigoOperacao.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)
				&& !codigoOperacao.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroPermissaoEspecial.adicionarParametro(new ParametroSimples(
					FiltroPermissaoEspecial.OPERACAO, codigoOperacao));			
		}

		// Erro caso o usu�rio mandou Pesquisar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroPermissaoEspecial.adicionarCaminhoParaCarregamentoEntidade("operacao");
		
		Collection colecaoPermissaoEspecial = (Collection) fachada.pesquisar(
				filtroPermissaoEspecial, PermissaoEspecial.class.getName());

		if (colecaoPermissaoEspecial == null || colecaoPermissaoEspecial.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"PermissaoEspecial");
		}

		sessao.setAttribute("colecaoPermissaoEspecial", colecaoPermissaoEspecial);
		
		return retorno;

	}

}

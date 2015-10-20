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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

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
 * @date 07/11/2006
 */
public class FiltrarTipoSolicitacaoEspecificacaoAction extends GcomAction {
	/**
	 * [UC0400] Filtrar Tipo de Solicita��o com Especifica��es
	 * 
	 * Este caso de uso cria um filtro que ser� usado na pesquisa de Tipo de
	 * Solicita��o com Especifica��es
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

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterTipoSolicitacaoEspecificacaoAction");

		FiltrarTipoSolicitacaoEspecificacaoActionForm filtrarTipoSolicitacaoEspecificacaoActionForm = (FiltrarTipoSolicitacaoEspecificacaoActionForm) actionForm;

		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();

		boolean peloMenosUmParametroInformado = false;

//		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = null;
//
//		Collection colecaoSolicitacaoTipoEspecificacao = (Collection) sessao
//				.getAttribute("colecaoSolicitacaoTipoEspecificacao");
//		if (colecaoSolicitacaoTipoEspecificacao == null
//				|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
//			colecaoSolicitacaoTipoEspecificacao = new ArrayList();
//			// } else {
//			// solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao)
//			// colecaoSolicitacaoTipoEspecificacao
//			// .iterator().next();
//			// }
//			// if (solicitacaoTipoEspecificacao != null) {
//			//
//			// peloMenosUmParametroInformado = true;
//			//
//			// FiltroSolicitacaoTipoEspecificacao
//			// filtroSolicitacaoTipoEspecificacao = new
//			// FiltroSolicitacaoTipoEspecificacao();
//			//
//		}

		// Fachada fachada = Fachada.getInstancia();

		// SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
		// descri��o da solicita��o tipo
		if (filtrarTipoSolicitacaoEspecificacaoActionForm.getDescricao() != null
				&& !filtrarTipoSolicitacaoEspecificacaoActionForm
						.getDescricao().equals("")) {

			peloMenosUmParametroInformado = true;

			filtroSolicitacaoTipo.adicionarParametro(new ComparacaoTexto(
					FiltroSolicitacaoTipo.DESCRICAO,
					filtrarTipoSolicitacaoEspecificacaoActionForm
							.getDescricao()));

		}
		// id do grupo de solicita��o da descri��o selecionada
		if (filtrarTipoSolicitacaoEspecificacaoActionForm
				.getIdgrupoTipoSolicitacao() != null
				&& !filtrarTipoSolicitacaoEspecificacaoActionForm
						.getIdgrupoTipoSolicitacao().equals("")) {

			peloMenosUmParametroInformado = true;

			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipo.SOLICITACAO_TIPO_GRUPO_ID,
					filtrarTipoSolicitacaoEspecificacaoActionForm
							.getIdgrupoTipoSolicitacao()));

		}

		// indicativo de falta d'agua
		if (filtrarTipoSolicitacaoEspecificacaoActionForm
				.getIndicadorFaltaAgua() != null
				&& !filtrarTipoSolicitacaoEspecificacaoActionForm
						.getIndicadorFaltaAgua().equals("3")) {

			peloMenosUmParametroInformado = true;

			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipo.INDICADOR_FALTA_AGUA,
					filtrarTipoSolicitacaoEspecificacaoActionForm
							.getIndicadorFaltaAgua()));

		}
		// indicativo de tarifa solcial
		if (filtrarTipoSolicitacaoEspecificacaoActionForm
				.getIndicadorTarifaSocial() != null
				&& !filtrarTipoSolicitacaoEspecificacaoActionForm
						.getIndicadorTarifaSocial().equals("3")) {

			peloMenosUmParametroInformado = true;

			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipo.INDICADOR_TARIFA_SOCIAL,
					filtrarTipoSolicitacaoEspecificacaoActionForm
							.getIndicadorTarifaSocial()));

		}
		
		// indicativo de falta d'agua
		if (filtrarTipoSolicitacaoEspecificacaoActionForm.getIndicadorUso() != null
				&& !filtrarTipoSolicitacaoEspecificacaoActionForm
						.getIndicadorUso().equals("3")) {

			peloMenosUmParametroInformado = true;

			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipo.INDICADOR_USO,
					filtrarTipoSolicitacaoEspecificacaoActionForm
							.getIndicadorUso()));

		}

		// Erro caso o usu�rio mandou Pesquisar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroSolicitacaoTipo
				.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoGrupo");

		// Verifica se o checkbox Atualizar est� marcado e em caso afirmativo
		// manda pelo um request uma vari�vel para o
		// ExibirManterFuncionalidadeAction e nele verificar se ir� para o
		// atualizar ou para o manter
		if (filtrarTipoSolicitacaoEspecificacaoActionForm.getAtualizar() != null
				&& filtrarTipoSolicitacaoEspecificacaoActionForm.getAtualizar()
						.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar",
					filtrarTipoSolicitacaoEspecificacaoActionForm
							.getAtualizar());

		}

		// Manda o filtro pelo sessao para o
		// ExibirManterTipoSolicitacaoEspecificacaoAction

		sessao.setAttribute("filtroSolicitacaoTipo", filtroSolicitacaoTipo);

//		sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
//				colecaoSolicitacaoTipoEspecificacao);

		httpServletRequest.setAttribute("filtroSolicitacaoTipo",
				filtroSolicitacaoTipo);

//		httpServletRequest.setAttribute("colecaoSolicitacaoTipoEspecificacao",
//				colecaoSolicitacaoTipoEspecificacao);

		return retorno;
	}
}

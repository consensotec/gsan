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

import gcom.atendimentopublico.ordemservico.EspecificacaoServicoTipo;
import gcom.atendimentopublico.ordemservico.EspecificacaoServicoTipoPK;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela exibi��o
 * 
 * @author S�vio Luiz
 * @created 28 de Julho de 2006
 */
public class AdicionarSolicitacaoEspecificacaoTipoServicoAction extends
		GcomAction {
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

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAdicionarSolicitacaoEspecificacao");

		if (sessao.getAttribute("retornarTelaPopup") != null) {
			String retonarTelaPopup = (String) sessao
					.getAttribute("retornarTelaPopup");
			if (retonarTelaPopup.equalsIgnoreCase("atualizar")) {
				retorno = actionMapping
						.findForward("exibirAtualizarAdicionarSolicitacaoEspecificacao");
			}
		}

		AdicionarSolicitacaoEspecificacaoActionForm adicionarSolicitacaoEspecificacaoActionForm = (AdicionarSolicitacaoEspecificacaoActionForm) actionForm;

		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoEspecificacaoServicoTipo = null;
		if (sessao.getAttribute("colecaoEspecificacaoServicoTipo") == null) {
			colecaoEspecificacaoServicoTipo = new ArrayList();
		} else {
			colecaoEspecificacaoServicoTipo = (Collection) sessao
					.getAttribute("colecaoEspecificacaoServicoTipo");
		}

		// seta os campos do form no objeto SolicitacaoTipoEspecificacao
		EspecificacaoServicoTipo especificacaoServicoTipo = new EspecificacaoServicoTipo();

		EspecificacaoServicoTipoPK especificacaoServicoTipoPK = new EspecificacaoServicoTipoPK();

		// id do tipo de servico
		if (adicionarSolicitacaoEspecificacaoActionForm.getIdTipoServico() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIdTipoServico().equals("")) {
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.ID,
					adicionarSolicitacaoEspecificacaoActionForm
							.getIdTipoServico()));

			Collection servicoTipoEncontrado = fachada.pesquisar(
					filtroServicoTipo, ServicoTipo.class.getName());

			if (servicoTipoEncontrado != null
					&& !servicoTipoEncontrado.isEmpty()) {
				ServicoTipo servicoTipo = (ServicoTipo) ((List) servicoTipoEncontrado)
						.get(0);
				especificacaoServicoTipoPK
						.setIdServicoTipo(servicoTipo.getId());
				especificacaoServicoTipo.setServicoTipo(servicoTipo);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Servi�o Tipo");
			}

		} else {
			throw new ActionServletException("atencao.required", null,
					"Tipo do Servi�o");
		}

		// id do tipo de servico
		if (adicionarSolicitacaoEspecificacaoActionForm.getOrdemExecucao() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getOrdemExecucao().equals("")) {
			Short ordemExecucao = new Short(
					adicionarSolicitacaoEspecificacaoActionForm
							.getOrdemExecucao());
			// [SF0004] - Validar Valor Ordem de Servi�o 1� parte
			fachada.verificarExistenciaOrdemExecucao(
					colecaoEspecificacaoServicoTipo, ordemExecucao);

			especificacaoServicoTipo.setOrdemExecucao(ordemExecucao);
		}
		// seta o PK na especifica��o do tipo de servi�o
		especificacaoServicoTipo.setComp_id(especificacaoServicoTipoPK);

		especificacaoServicoTipo.setUltimaAlteracao(new Date());

		colecaoEspecificacaoServicoTipo.add(especificacaoServicoTipo);

		sessao.setAttribute("colecaoEspecificacaoServicoTipo",
				colecaoEspecificacaoServicoTipo);

		return retorno;
	}
}

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
package gcom.gui.faturamento;

import gcom.arrecadacao.ContratoDemanda;
import gcom.arrecadacao.ContratoMotivoCancelamento;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroContratoDemanda;
import gcom.faturamento.FiltroContratoMotivoCancelamento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
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
 * Permite atualizar um contrato de demanda [UC0513] Manter Contrato de Demanda
 * 
 * @author Rafael Corr�a
 * @since 31/03/2006
 */
public class ExibirAtualizarContratoDemandaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarContratoDemanda");

		AtualizarContratoDemandaActionForm atualizarContratoDemandaActionForm = (AtualizarContratoDemandaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroContratoMotivoCancelamento filtroContratoMotivoCancelamento = new FiltroContratoMotivoCancelamento(
				FiltroContratoMotivoCancelamento.DESCRICAO);

		Collection colecaoMotivoCancelamento = fachada.pesquisar(
				filtroContratoMotivoCancelamento,
				ContratoMotivoCancelamento.class.getName());
		
		sessao.setAttribute("colecaoMotivoCancelamento", colecaoMotivoCancelamento);

		// Recupera os valores da unidade do form
		String idImovel = atualizarContratoDemandaActionForm.getIdImovel();
		String inscricaoImovel = atualizarContratoDemandaActionForm
				.getInscricaoImovel();

		// Verifica se o usu�rio solicitou a pesquisa de unidade
		if (idImovel != null
				&& !idImovel.trim().equals("")
				&& (inscricaoImovel == null || inscricaoImovel.trim()
						.equals(""))) {

			Imovel imovel = fachada.pesquisarImovelDigitado(new Integer(
					idImovel));

			if (imovel != null) {

				atualizarContratoDemandaActionForm.setInscricaoImovel(imovel
						.getInscricaoFormatada());
				httpServletRequest.setAttribute("nomeCampo",
						"dataInicioContrato");

			} else {

				atualizarContratoDemandaActionForm
						.setInscricaoImovel("IM�VEL INEXISTENTE");
				atualizarContratoDemandaActionForm.setIdImovel("");
				httpServletRequest.setAttribute("existeImovel", "exception");
				httpServletRequest.setAttribute("nomeCampo", "idImovel");

			}

		} else if (inscricaoImovel != null
				&& !inscricaoImovel.trim().equals("")
				&& (idImovel == null || idImovel.trim().equals(""))) {
			atualizarContratoDemandaActionForm.setInscricaoImovel("");
		}

		// Verifica se o usu�rio est� entrando pela primeira vez na tela ou se
		// ele selecionou a op��o de desfazer
		if (sessao.getAttribute("contratoDemandaAtualizar") == null
				|| httpServletRequest.getParameter("desfazer") != null) {

			ContratoDemanda contratoDemanda = null;

			if (httpServletRequest.getParameter("desfazer") != null) {

				String idContratoDemanda = ((ContratoDemanda) sessao
						.getAttribute("contratoDemandaAtualizar")).getId()
						.toString();

				FiltroContratoDemanda filtroContratoDemanda = new FiltroContratoDemanda();
				filtroContratoDemanda.adicionarParametro(new ParametroSimples(
						FiltroContratoDemanda.ID, idContratoDemanda));

				filtroContratoDemanda
						.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
				filtroContratoDemanda
						.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
				filtroContratoDemanda
						.adicionarCaminhoParaCarregamentoEntidade("contratoMotivoCancelamento");

				Collection colecaoContratoDemanda = fachada.pesquisar(
						filtroContratoDemanda, ContratoDemanda.class.getName());

				if (colecaoContratoDemanda == null
						|| colecaoContratoDemanda.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				contratoDemanda = (ContratoDemanda) Util
						.retonarObjetoDeColecao(colecaoContratoDemanda);

				sessao
						.setAttribute("contratoDemandaAtualizar",
								contratoDemanda);

			} else {

				if (sessao.getAttribute("contratoDemanda") != null) {

					contratoDemanda = (ContratoDemanda) sessao
							.getAttribute("contratoDemanda");

					sessao.setAttribute("contratoDemandaAtualizar",
							contratoDemanda);
					sessao.removeAttribute("contratoDemanda");

					sessao.setAttribute("filtrar", true);

				} else {

					String idContratoDemanda = httpServletRequest
							.getParameter("contratoDemandaID");

					if (httpServletRequest.getParameter("inserir") != null) {
						sessao.setAttribute("inserir", true);
					} else {
						sessao.removeAttribute("filtrar");
						sessao.removeAttribute("inserir");
					}

					FiltroContratoDemanda filtroContratoDemanda = new FiltroContratoDemanda();
					filtroContratoDemanda
							.adicionarParametro(new ParametroSimples(
									FiltroContratoDemanda.ID, idContratoDemanda));

					filtroContratoDemanda
							.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
					filtroContratoDemanda
							.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
					filtroContratoDemanda
							.adicionarCaminhoParaCarregamentoEntidade("contratoMotivoCancelamento");

					Collection colecaoContratoDemanda = fachada.pesquisar(
							filtroContratoDemanda, ContratoDemanda.class
									.getName());

					if (colecaoContratoDemanda == null
							|| colecaoContratoDemanda.isEmpty()) {
						throw new ActionServletException(
								"atencao.atualizacao.timestamp");
					}

					contratoDemanda = (ContratoDemanda) Util
							.retonarObjetoDeColecao(colecaoContratoDemanda);

					sessao.setAttribute("contratoDemandaAtualizar",
							contratoDemanda);

				}

			}

			atualizarContratoDemandaActionForm
					.setNumeroContrato(contratoDemanda.getNumeroContrato());
			atualizarContratoDemandaActionForm.setIdImovel(contratoDemanda
					.getImovel().getId().toString());
			atualizarContratoDemandaActionForm
					.setInscricaoImovel(contratoDemanda.getImovel()
							.getInscricaoFormatada());
			atualizarContratoDemandaActionForm.setDataInicioContrato(Util
					.formatarData(contratoDemanda.getDataContratoInicio()));
			atualizarContratoDemandaActionForm.setDataFimContrato(Util
					.formatarData(contratoDemanda.getDataContratoFim()));

			if (contratoDemanda.getDataContratoEncerrado() != null) {
				atualizarContratoDemandaActionForm.setDataEncerramento(Util
						.formatarData(contratoDemanda
								.getDataContratoEncerrado()));
			} else {
				atualizarContratoDemandaActionForm.setDataEncerramento("");
			}

			if (contratoDemanda.getContratoMotivoCancelamento() != null) {
				atualizarContratoDemandaActionForm
						.setIdMotivoCancelamento(contratoDemanda
								.getContratoMotivoCancelamento().getId()
								.toString());
			} else {
				atualizarContratoDemandaActionForm.setIdMotivoCancelamento(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO);
			}

			httpServletRequest.setAttribute("nomeCampo", "numeroContrato");

		}

		return retorno;

	}

}

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
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroContratoDemanda;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarContratoDemandaAction extends GcomAction {

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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterContratoDemanda");

		Fachada fachada = Fachada.getInstancia();

		FiltrarContratoDemandaActionForm filtrarContratoDemandaActionForm = (FiltrarContratoDemandaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// Limpa todo o formul�rio para evitar "sujeiras" na tela
		String numeroContrato = filtrarContratoDemandaActionForm
				.getNumeroContrato();
		String idImovel = filtrarContratoDemandaActionForm.getIdImovel();
		String dataInicioContrato = filtrarContratoDemandaActionForm
				.getDataInicioContrato();
		String dataFimContrato = filtrarContratoDemandaActionForm
				.getDataFimContrato();

		// Cria o filtro
		FiltroContratoDemanda filtroContratoDemanda = new FiltroContratoDemanda();

		boolean peloMenosUmParametroInformado = false;

		// Neste ponto o filtro � criado com os par�metros informados na p�gina
		// de filtrar contrato de demanda para ser executada a pesquisa no
		// ExibirManterContratoDemandaAction
		if (numeroContrato != null
				&& !numeroContrato.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroContratoDemanda.adicionarParametro(new ParametroSimples(
					FiltroContratoDemanda.MUMEROCONTRATO, numeroContrato));
			
			// [FS0003] - Verificar exist�ncia do contrato de demanda
			Collection colecaoContratoDemanda = fachada.pesquisar(
					filtroContratoDemanda, ContratoDemanda.class.getName());
			
			if (colecaoContratoDemanda == null || colecaoContratoDemanda.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Contrato de Demanda");
			}
		}

		// Verifica se o im�vel existe e em caso afirmativo
		// seta-a no filtro
		if (idImovel != null && !idImovel.trim().equals("")) {

			Imovel imovel = fachada.pesquisarImovel(new Integer(idImovel));

			if (imovel == null) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Im�vel");
			} else {
				peloMenosUmParametroInformado = true;
				filtroContratoDemanda.adicionarParametro(new ParametroSimples(
						FiltroContratoDemanda.IMOVEL, idImovel));
			}

		}

		if (dataInicioContrato != null
				&& !dataInicioContrato.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			Date dataInicioFormatada = Util.converteStringParaDate(dataInicioContrato);
			
			filtroContratoDemanda.adicionarParametro(new MaiorQue(
					FiltroContratoDemanda.DATACONTRATOINICIO,
					dataInicioFormatada));
		}

		if (dataFimContrato != null
				&& !dataFimContrato.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			Date dataFimFormatada = Util.converteStringParaDate(dataFimContrato);
			
			filtroContratoDemanda.adicionarParametro(new MenorQue(
					FiltroContratoDemanda.DATACONTRATOINICIO, dataFimFormatada));
		}

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Verifica se o checkbox Atualizar est� marcado e em caso afirmativo
		// manda pela sess�o uma vari�vel para o
		// ExibirManterEquipeAction e nele verificar se ir� para o
		// atualizar ou para o manter, caso o checkbox esteja desmarcado remove
		// da sess�o
		String indicadorAtualizar = httpServletRequest
				.getParameter("atualizar");

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("atualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("atualizar");
		}

		// Manda o filtro pela sessao para o
		// ExibirManterContratoDemandaAction
		sessao.setAttribute("filtroContratoDemanda", filtroContratoDemanda);

		return retorno;
	}
}

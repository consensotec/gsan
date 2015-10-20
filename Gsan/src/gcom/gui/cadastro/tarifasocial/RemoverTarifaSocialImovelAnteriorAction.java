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
package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rafael Corr�a
 * @since 16/01/2007
 */
public class RemoverTarifaSocialImovelAnteriorAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("remover");

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		RemoverTarifaSocialImovelAnteriorActionForm removerTarifaSocialImovelAnteriorActionForm = (RemoverTarifaSocialImovelAnteriorActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		String idMotivoExclusao = removerTarifaSocialImovelAnteriorActionForm
				.getMotivoExclusao();
		
		Cliente cliente = (Cliente) sessao
		.getAttribute("clienteTarifaSocialImovelAnterior");

		String manter = httpServletRequest.getParameter("manter");
		
		if (manter == null || manter.trim().equals("")) {
			
			TarifaSocialHelper tarifaSocialHelperAtualizar = (TarifaSocialHelper) sessao
					.getAttribute("tarifaSocialHelperAtualizar");
			TarifaSocialHelper tarifaSocialHelper = (TarifaSocialHelper) sessao
					.getAttribute("tarifaSocialHelper");

			ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
			clienteRelacaoTipo.setId(ClienteRelacaoTipo.USUARIO.intValue());
			clienteRelacaoTipo.setDescricao("USUARIO");

			Date dataInicioRelacao = (Date) sessao
					.getAttribute("dataInicioRelacao");

			if (sessao.getAttribute("colecaoClienteImovel") != null) {

				ClienteImovel clienteImovel = new ClienteImovel();

				clienteImovel.setCliente(cliente);

				clienteImovel.setClienteRelacaoTipo(clienteRelacaoTipo);

				Imovel imovel = tarifaSocialHelper.getClienteImovel()
						.getImovel();

				clienteImovel.setDataInicioRelacao(dataInicioRelacao);
				clienteImovel.setImovel(imovel);

				Collection colecaoClienteImovel = (Collection) sessao
						.getAttribute("colecaoClienteImovel");

				tarifaSocialHelperAtualizar.setClienteImovel(clienteImovel);
				sessao.setAttribute("tarifaSocialHelperAtualizar",
						tarifaSocialHelperAtualizar);

				colecaoClienteImovel.add(clienteImovel);
				sessao.setAttribute("colecaoClienteImovel",
						colecaoClienteImovel);

				Collection colecaoClientesInseridos = null;

				if (tarifaSocialHelperAtualizar.getColecaoClientesInseridos() != null) {
					colecaoClientesInseridos = tarifaSocialHelperAtualizar
							.getColecaoClientesInseridos();
				} else {
					colecaoClientesInseridos = new ArrayList();
				}

				colecaoClientesInseridos.add(clienteImovel);
				tarifaSocialHelperAtualizar
						.setColecaoClientesInseridos(colecaoClientesInseridos);
				sessao.setAttribute("tarifaSocialHelperAtualizar",
						tarifaSocialHelperAtualizar);

			} else {

				ClienteImovelEconomia clienteImovelEconomia = new ClienteImovelEconomia();

				clienteImovelEconomia.setCliente(cliente);

				clienteImovelEconomia.setClienteRelacaoTipo(clienteRelacaoTipo);

				ImovelEconomia imovelEconomia = tarifaSocialHelper
						.getClienteImovelEconomia().getImovelEconomia();

				clienteImovelEconomia.setDataInicioRelacao(dataInicioRelacao);
				clienteImovelEconomia.setImovelEconomia(imovelEconomia);

				Collection colecaoClienteImovelEconomia = (Collection) sessao
						.getAttribute("colecaoClienteImovelEconomia");

				tarifaSocialHelperAtualizar
						.setClienteImovelEconomia(clienteImovelEconomia);
				sessao.setAttribute("tarifaSocialHelperAtualizar",
						tarifaSocialHelperAtualizar);

				colecaoClienteImovelEconomia.add(clienteImovelEconomia);
				sessao.setAttribute("colecaoClienteImovelEconomia",
						colecaoClienteImovelEconomia);

				Collection colecaoClientesInseridos = null;

				if (tarifaSocialHelperAtualizar
						.getColecaoClientesEconomiaInseridos() != null) {
					colecaoClientesInseridos = tarifaSocialHelperAtualizar
							.getColecaoClientesEconomiaInseridos();
				} else {
					colecaoClientesInseridos = new ArrayList();
				}

				colecaoClientesInseridos.add(clienteImovelEconomia);
				tarifaSocialHelperAtualizar
						.setColecaoClientesEconomiaInseridos(colecaoClientesInseridos);

				sessao.setAttribute("tarifaSocialHelperAtualizar",
						tarifaSocialHelperAtualizar);
			}
		
		} else {
			
			retorno = actionMapping.findForward("exibirRemover");
			httpServletRequest.setAttribute("fecharPopup", true);
			sessao.setAttribute("atualizar", true);
			
			TarifaSocialDadoEconomia tarifaSocialDadoEconomiaRecadastrar = (TarifaSocialDadoEconomia) sessao
					.getAttribute("tarifaSocialRecadastrar");
			
			Collection colecaoTarifasSociaisRecadastradas = null;

			if (sessao
					.getAttribute("colecaoTarifasSociaisRecadastradas") != null) {
				colecaoTarifasSociaisRecadastradas = (Collection) sessao
						.getAttribute("colecaoTarifasSociaisRecadastradas");
			} else {
				colecaoTarifasSociaisRecadastradas = new ArrayList();
			}
			
			colecaoTarifasSociaisRecadastradas
				.add(tarifaSocialDadoEconomiaRecadastrar);

			sessao.setAttribute("colecaoTarifasSociaisRecadastradas",
				colecaoTarifasSociaisRecadastradas);
			
			ArrayList colecaoTarifaSocialHelper = (ArrayList) sessao
				.getAttribute("colecaoTarifaSocialHelper");
			
			int i = 0;
			
			Iterator colecaoTarifaSocialHelperIterator = colecaoTarifaSocialHelper.iterator();
			
			while (colecaoTarifaSocialHelperIterator.hasNext()) {

				TarifaSocialHelper tarifaSocialHelper = (TarifaSocialHelper) colecaoTarifaSocialHelperIterator
						.next();

				if (tarifaSocialHelper.getTarifaSocialDadoEconomia().getId()
						.equals(tarifaSocialDadoEconomiaRecadastrar.getId())) {
					
					tarifaSocialHelper
							.setTarifaSocialDadoEconomia(tarifaSocialDadoEconomiaRecadastrar);
					colecaoTarifaSocialHelper.set(i, tarifaSocialHelper);

					colecaoTarifasSociaisRecadastradas
							.add(tarifaSocialDadoEconomiaRecadastrar);
					
					sessao.setAttribute("colecaoTarifaSocialHelper", colecaoTarifaSocialHelper);

					break;

				}

				i++;

			}
			
		}

		ClienteImovel clienteImovel = new ClienteImovel();

		clienteImovel.setCliente(cliente);

		TarifaSocialHelper tarifaSocialHelperImovelAnterior = new TarifaSocialHelper();

		TarifaSocialDadoEconomia tarifaSocialDadoEconomiaSessao = (TarifaSocialDadoEconomia) sessao.getAttribute("tarifaSocialDadoEconomiaImovelAnterior");
		
		TarifaSocialDadoEconomia tarifaSocialDadoEconomia = fachada.pesquisarTarifaSocial(tarifaSocialDadoEconomiaSessao.getId());
		
		tarifaSocialDadoEconomia.setTarifaSocialRevisaoMotivo(null);
		tarifaSocialDadoEconomia.setDataRevisao(null);

		TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo = new TarifaSocialExclusaoMotivo();

		tarifaSocialExclusaoMotivo.setId(new Integer(idMotivoExclusao));

		tarifaSocialDadoEconomia
				.setTarifaSocialExclusaoMotivo(tarifaSocialExclusaoMotivo);

		tarifaSocialHelperImovelAnterior
				.setTarifaSocialDadoEconomia(tarifaSocialDadoEconomia);

		tarifaSocialHelperImovelAnterior.setClienteImovel(clienteImovel);

		Collection colecaoImoveisExcluidosTarifaSocial = null;

		if (sessao.getAttribute("colecaoImoveisExcluidosTarifaSocial") != null) {
			colecaoImoveisExcluidosTarifaSocial = (Collection) sessao
					.getAttribute("colecaoImoveisExcluidosTarifaSocial");
		} else {
			colecaoImoveisExcluidosTarifaSocial = new ArrayList();
		}

		colecaoImoveisExcluidosTarifaSocial
				.add(tarifaSocialHelperImovelAnterior);

		sessao.setAttribute("colecaoImoveisExcluidosTarifaSocial",
				colecaoImoveisExcluidosTarifaSocial);
		
		sessao.setAttribute("telaLimpa", true);

		return retorno;

	}

}

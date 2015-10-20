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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
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
 * [UC0507] ATUALIZAR ARRECADADOR
 * 
 * @author Marcio Roberto
 * @date 13/02/2007
 */

public class ExibirAtualizarArrecadadorAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("atualizarArrecadador");

		AtualizarArrecadadorActionForm atualizarArrecadadorActionForm = (AtualizarArrecadadorActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String idArrecadador = httpServletRequest
				.getParameter("idRegistroAtualizacao");

		if (idArrecadador == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				idArrecadador = (String) sessao
						.getAttribute("idRegistroAtualizacao");
			} else {
				idArrecadador = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}

		} else {
			sessao.setAttribute("i", true);
		}

		Arrecadador arrecadador = null;

		String idImovel = null;

		String inscricaoImovel = null;

		String idCliente = null;

		String objetoConsulta = httpServletRequest
				.getParameter("objetoConsulta");

		if (objetoConsulta != null && !objetoConsulta.equals("")) {

			// Validamos o cliente
			if (atualizarArrecadadorActionForm.getIdCliente() != null
					&& !atualizarArrecadadorActionForm.getIdCliente().trim()
							.equals("")) {
				FiltroCliente filtro = new FiltroCliente();

				filtro.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, atualizarArrecadadorActionForm
								.getIdCliente()));

				Collection colCliente = fachada.pesquisar(filtro, Cliente.class
						.getName());

				if (colCliente == null || !colCliente.iterator().hasNext()) {
					// O cliente n�o existe
					atualizarArrecadadorActionForm.setIdCliente("");
					atualizarArrecadadorActionForm
							.setNomeCliente("Cliente inexistente");
					httpServletRequest.setAttribute("existeCliente",
							"exception");

				} else {
					Cliente cliente = (Cliente) Util
							.retonarObjetoDeColecao(colCliente);
					atualizarArrecadadorActionForm.setIdCliente(cliente.getId()
							.toString());

					atualizarArrecadadorActionForm.setNomeCliente(cliente
							.getNome());
				}
			}

			if (atualizarArrecadadorActionForm.getIdImovel() != null
					&& !atualizarArrecadadorActionForm.getIdImovel().trim()
							.equals("")) {
				// Validamos o imovel
				FiltroImovel filtroImovel = new FiltroImovel();

				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, atualizarArrecadadorActionForm
								.getIdImovel()));

				Collection colImovel = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());

				if (colImovel == null || colImovel.isEmpty()) {
					// O Imovel n�o existe
					atualizarArrecadadorActionForm.setIdImovel("");
					atualizarArrecadadorActionForm
							.setInscricaoImovel("Im�vel inexistente");

					httpServletRequest
							.setAttribute("existeImovel", "exception");

				} else {
					Imovel imovel = (Imovel) Util
							.retonarObjetoDeColecao(colImovel);

					inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel
							.getId());

//					atualizarArrecadadorActionForm.setIdImovel(arrecadador
//							.getImovel().getId().toString());
                    
                    atualizarArrecadadorActionForm.setIdImovel(imovel
                            .getId().toString());

					atualizarArrecadadorActionForm
							.setInscricaoImovel(inscricaoImovel);
				}
			}
		} else {

			if (idArrecadador != null && !idArrecadador.trim().equals("")
					&& Integer.parseInt(idArrecadador) > 0) {

				FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
				// Adiciona entidade estrangeira para carregamento do objeto
				// "CLIENTE"
				// (ou seja, em ARRECADADOR existe um atributo do tipo Cliente,
				// ent�o � preciso carregar o cliente)
				// o mesmo para Imovel.
				filtroArrecadador
						.adicionarCaminhoParaCarregamentoEntidade("cliente");
				filtroArrecadador
						.adicionarCaminhoParaCarregamentoEntidade("imovel");

				filtroArrecadador.adicionarParametro(new ParametroSimples(
						FiltroArrecadador.ID, idArrecadador));
				Collection colecaoArrecadador = fachada.pesquisar(
						filtroArrecadador, Arrecadador.class.getName());
				if (colecaoArrecadador != null && !colecaoArrecadador.isEmpty()) {
					arrecadador = (Arrecadador) Util
							.retonarObjetoDeColecao(colecaoArrecadador);
				}
			}

			if (idImovel == null || idImovel.trim().equals("")) {

				if (arrecadador.getImovel() != null
						&& !arrecadador.getImovel().getId().toString().trim()
								.equals("")) {

					idImovel = arrecadador.getImovel().getId().toString();

					// Filtro para descobrir id do Imovel
					FiltroImovel filtroImovel = new FiltroImovel();

					filtroImovel.adicionarParametro(new ParametroSimples(
							FiltroImovel.ID, idImovel));

					Collection colecaoImovel = fachada.pesquisar(filtroImovel,
							Imovel.class.getName());

					Imovel imovel = (Imovel) Util
							.retonarObjetoDeColecao(colecaoImovel);

					inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel
							.getId());

					atualizarArrecadadorActionForm.setIdImovel(arrecadador
							.getImovel().getId().toString());

					atualizarArrecadadorActionForm
							.setInscricaoImovel(inscricaoImovel);
				}
			}

			if (idCliente == null || idCliente.trim().equals("")) {

				atualizarArrecadadorActionForm.setIdCliente(arrecadador
						.getCliente().getId().toString());

				atualizarArrecadadorActionForm.setNomeCliente(arrecadador
						.getCliente().getNome());

			}
			atualizarArrecadadorActionForm.setId(idArrecadador);

			atualizarArrecadadorActionForm.setIdAgente(arrecadador
					.getCodigoAgente().toString());

			atualizarArrecadadorActionForm.setInscricaoEstadual(arrecadador
					.getNumeroInscricaoEstadual());
			
			atualizarArrecadadorActionForm.setIndicadorUso(arrecadador
					.getIndicadorUso().toString());

			sessao.setAttribute("arrecadador", arrecadador);

			if(sessao.getAttribute("colecaoArrecadador") != null){
			sessao.setAttribute("caminhoRetornoVoltar",
					"/gsan/filtrarArrecadadorAction.do");
			}else{
				sessao.setAttribute("caminhoRetornoVoltar",
				"/gsan/exibirFiltrarArrecadadorAction.do");
			}

		}

		return retorno;
	}

}

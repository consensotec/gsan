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

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
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
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os
 * par�metros para realiza��o da atualiza��o de um R.A (Aba n� 03 - Dados do
 * solicitante)
 * 
 * @author S�vio Luiz
 * @date 10/08/2006
 */
public class ExibirAtualizarRegistroAtendimentoDadosSolicitanteAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("atualizarRegistroAtendimentoDadosSolicitante");

		AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm = (AtualizarRegistroAtendimentoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// caso a escolha seja de remover o registro de atendimento solicitante
		if (httpServletRequest.getParameter("idRASolicitante") != null) {
			// recupera o id do solicitante para ser removido
			long idRASolicitante = Long.parseLong(httpServletRequest
					.getParameter("idRASolicitante"));
			// cria ou recupera a cole��o de RA solicitante removidos
			Collection colecaoRASolicitanteRemovidas = null;
			if (sessao.getAttribute("colecaoRASolicitanteRemovidas") != null) {
				colecaoRASolicitanteRemovidas = (Collection) sessao
						.getAttribute("colecaoRASolicitanteRemovidas");
			} else {
				colecaoRASolicitanteRemovidas = new ArrayList();
			}
			// recupera a cole��o de RA solicitante
			Collection colecaoRASolicitante = (Collection) sessao
					.getAttribute("colecaoRASolicitante");
			Iterator iteratorRASolicitante = colecaoRASolicitante.iterator();
			while (iteratorRASolicitante.hasNext()) {
				RegistroAtendimentoSolicitante registroAtendimentoSolicitante = (RegistroAtendimentoSolicitante) iteratorRASolicitante
						.next();
				if (registroAtendimentoSolicitante.getUltimaAlteracao()
						.getTime() == idRASolicitante) {
					if (registroAtendimentoSolicitante.getID() != null
							&& !registroAtendimentoSolicitante.getID().equals(
									"")) {
						colecaoRASolicitanteRemovidas
								.add(registroAtendimentoSolicitante);
					}
					iteratorRASolicitante.remove();
					break;
				}
			}
			sessao.setAttribute("colecaoRASolicitanteRemovidas",
					colecaoRASolicitanteRemovidas);
		}

		Fachada fachada = Fachada.getInstancia();
		// caso seja a primeira vez ent�o pesquisa a cole��o de solicitante
		if (sessao.getAttribute("colecaoRASolicitante") == null) {

			FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimentoSolicitante = new FiltroRegistroAtendimentoSolicitante();
			
			filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
			filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("funcionario");
			filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
			filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
			filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
			filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
			
			filtroRegistroAtendimentoSolicitante
					.adicionarParametro(new ParametroSimples(
							FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID,
							atualizarRegistroAtendimentoActionForm
									.getNumeroRA()));

			Collection colecaoRASolicitante = fachada.pesquisar(
					filtroRegistroAtendimentoSolicitante,
					RegistroAtendimentoSolicitante.class.getName());

			/*Collection colecaoRASolicitanteComNome = null;

			if (colecaoRASolicitante != null && !colecaoRASolicitante.isEmpty()) {

				Iterator iteRASolicitante = colecaoRASolicitante.iterator();
				colecaoRASolicitanteComNome = new ArrayList();
				while (iteRASolicitante.hasNext()) {
					RegistroAtendimentoSolicitante registroAtendimentoSolicitante = (RegistroAtendimentoSolicitante) iteRASolicitante
							.next();
					String nomeRASolicitante = fachada
							.obterNomeSolicitanteRA(registroAtendimentoSolicitante
									.getID());
					registroAtendimentoSolicitante
							.setSolicitante(nomeRASolicitante);
					colecaoRASolicitanteComNome
							.add(registroAtendimentoSolicitante);
				}
			}*/

			sessao.setAttribute("colecaoRASolicitante",
					colecaoRASolicitante);
		}

		return retorno;
	}
}

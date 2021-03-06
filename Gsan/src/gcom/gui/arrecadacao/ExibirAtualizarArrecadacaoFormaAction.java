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

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
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
 * 
 * @author Vinicius Medeiros
 * @date 10/04/2008
 */
public class ExibirAtualizarArrecadacaoFormaAction extends GcomAction {

	/**
	 * M�todo responsavel por responder a requisicao
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

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarArrecadacaoForma");

		AtualizarArrecadacaoFormaActionForm atualizarArrecadacaoFormaActionForm = (AtualizarArrecadacaoFormaActionForm) actionForm;

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando houver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		String idArrecadacaoForma = httpServletRequest
			.getParameter("idRegistroAtualizacao");

		// Verifica se veio do Manter ou do Filtrar
		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (idArrecadacaoForma == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				idArrecadacaoForma = (String) sessao
						.getAttribute("idRegistroAtualizacao");
			} else {
				idArrecadacaoForma = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();

		
		if (idArrecadacaoForma != null && !idArrecadacaoForma.trim().equals("")
				&& Integer.parseInt(idArrecadacaoForma) > 0) {

			FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
			filtroArrecadacaoForma.adicionarParametro(new ParametroSimples(
					FiltroArrecadacaoForma.CODIGO, idArrecadacaoForma));
			Collection colecaoArrecadacaoForma = fachada.pesquisar(
					filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
			if (colecaoArrecadacaoForma != null
					&& !colecaoArrecadacaoForma.isEmpty()) {
				arrecadacaoForma = (ArrecadacaoForma) Util
						.retonarObjetoDeColecao(colecaoArrecadacaoForma);
			}

			if (idArrecadacaoForma == null
					|| idArrecadacaoForma.trim().equals("")) {

				atualizarArrecadacaoFormaActionForm
						.setIdArrecadacaoForma(arrecadacaoForma.getId()
								.toString());
				atualizarArrecadacaoFormaActionForm
						.setDescricao(arrecadacaoForma.getDescricao());
				atualizarArrecadacaoFormaActionForm
						.setCodigoArrecadacaoForma(arrecadacaoForma
								.getCodigoArrecadacaoForma());

			}

			atualizarArrecadacaoFormaActionForm
					.setIdArrecadacaoForma(idArrecadacaoForma);

			atualizarArrecadacaoFormaActionForm.setDescricao(arrecadacaoForma
					.getDescricao());

			atualizarArrecadacaoFormaActionForm
					.setCodigoArrecadacaoForma(arrecadacaoForma
							.getCodigoArrecadacaoForma());

			sessao.setAttribute("arrecadacaoFormaAtualizar", arrecadacaoForma);

			if (sessao.getAttribute("colecaoArrecadacaoForma") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarArrecadacaoFormaAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarArrecadacaoFormaAction.do");
			}

		}

		return retorno;
	}
}
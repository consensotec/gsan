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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
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
 * @author Vin�cius de Melo Medeiros
 * @created 16/05/2008
 */
public class ExibirInserirHidrometroDiametroAction extends GcomAction {
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

		// Mudar quando houver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("inserirHidrometroDiametro");

		InserirHidrometroDiametroActionForm form = (InserirHidrometroDiametroActionForm) actionForm;

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		String idDebito = form.getIdDebito();
		if (idDebito != null && !idDebito.trim().equals("")) {
			pesquisarDebitoTipo(sessao, form);
		}
		// Debito Tipo
		if (form.getIdDebito() != null && 
				!form.getIdDebito().equals("") &&
				form.getDescricaoDebito() != null && 
				!form.getDescricaoDebito().equals("")) {
			sessao.setAttribute("debitoTipoEncontrado", true);
		}
		
		if (httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S")) {

			form.setDescricao("");
			form.setDescricaoAbreviada("");
			form.setNumeroOrdem("");
			
			if (form.getDescricao() == null
					|| form.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;
				FiltroHidrometroDiametro filtroHidrometroDiametro = new FiltroHidrometroDiametro();

				filtroHidrometroDiametro.setCampoOrderBy(FiltroHidrometroDiametro.ID,
														 FiltroHidrometroDiametro.DESCRICAO);
				
				colecaoPesquisa = fachada.pesquisar(filtroHidrometroDiametro,
						HidrometroDiametro.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Di�metro do Hidr�metro");
				} else {
					sessao.setAttribute("colecaoHidrometroDiametro", colecaoPesquisa);
				}

				// Cole��o de Diametro do Hidrometro
				FiltroHidrometroDiametro filtroHidrometroDiametro2 = new FiltroHidrometroDiametro();
				filtroHidrometroDiametro2.setCampoOrderBy(FiltroHidrometroDiametro.ID,
														  FiltroHidrometroDiametro.DESCRICAO);

				Collection colecaoHidrometroDiametro2 = fachada.pesquisar(filtroHidrometroDiametro2,
						HidrometroDiametro.class.getName());
				sessao.setAttribute("colecaoHidrometroDiametro2", colecaoHidrometroDiametro2);

			}

		}
		return retorno;
	}
	
	private void pesquisarDebitoTipo(HttpSession sessao, 
			InserirHidrometroDiametroActionForm form) {
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroDebitoTipo filtro = new FiltroDebitoTipo();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroDebitoTipo.ID, form.getIdDebito()));
		
		Collection pesquisa = fachada.pesquisar(filtro, DebitoTipo.class.getName());
		
		if (pesquisa != null && !pesquisa.isEmpty()) {
			DebitoTipo debitoTipo = (DebitoTipo) Util.retonarObjetoDeColecao(pesquisa);
			
			form.setIdDebito("" + debitoTipo.getId());
			form.setDescricaoDebito(debitoTipo.getDescricao());
		} else {
			form.setIdDebito("");
			form.setDescricaoDebito("Debito Tipo Inexistente");
			sessao.removeAttribute("debitoTipoEncontrado");
		}
	}
}

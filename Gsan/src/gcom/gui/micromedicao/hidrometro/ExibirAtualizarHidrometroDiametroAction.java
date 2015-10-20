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
 * 
 * @author Vinicius Medeiros
 * @date 16/05/2008
 */
public class ExibirAtualizarHidrometroDiametroAction extends GcomAction {

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

		ActionForward retorno = actionMapping
				.findForward("HidrometroDiametroAtualizar");

		AtualizarHidrometroDiametroActionForm form = (AtualizarHidrometroDiametroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		String id = httpServletRequest.getParameter("idRegistroAtualizacao");
		
		String pesquisarDebitoTipo = httpServletRequest.getParameter("pesquisarDebitoTipo");
		
		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}
		

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
			
		HidrometroDiametro hidrometroDiametro = new HidrometroDiametro();

		if (id != null && !id.trim().equals("")) {

			FiltroHidrometroDiametro filtroHidrometroDiametro = new FiltroHidrometroDiametro();
			filtroHidrometroDiametro.adicionarParametro(new ParametroSimples(
					FiltroHidrometroDiametro.ID, id));
			Collection colecaoHidrometroDiametro = fachada.pesquisar(
					filtroHidrometroDiametro, HidrometroDiametro.class.getName());
			if (colecaoHidrometroDiametro != null
					&& !colecaoHidrometroDiametro.isEmpty()) {
				hidrometroDiametro = (HidrometroDiametro) Util
						.retonarObjetoDeColecao(colecaoHidrometroDiametro);
			}

			if (id == null || id.trim().equals("")) {

				form.setId(hidrometroDiametro
						.getId().toString());

				form.setDescricao(hidrometroDiametro.getDescricao());

				form.setDescricaoAbreviada(hidrometroDiametro
					.getDescricaoAbreviada());

			}

			form.setId(id);

			form.setDescricao(hidrometroDiametro
					.getDescricao());

			form.setDescricaoAbreviada(hidrometroDiametro
					.getDescricaoAbreviada());

			form.setIndicadorUso(""
					+ hidrometroDiametro.getIndicadorUso());
			
			if (hidrometroDiametro.getNumeroOrdem() != null && !hidrometroDiametro.equals("")){
				form.setNumeroOrdem(hidrometroDiametro.getNumeroOrdem().toString());
			}
			
			if(hidrometroDiametro.getValorCobradoPorDepreciacao() != null){			
				form.setValorCobradoDepreciacao(Util.formatarMoedaReal(hidrometroDiametro.getValorCobradoPorDepreciacao()));
			}else{
				form.setValorCobradoDepreciacao("");
			}
			
			if(hidrometroDiametro.getDebitoTipo() != null){
				
				FiltroDebitoTipo filtro = new FiltroDebitoTipo();
				filtro.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, hidrometroDiametro.getDebitoTipo().getId()));
				DebitoTipo debitoTipo = (DebitoTipo) Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, DebitoTipo.class.getName()));
				
				form.setIdDebito(""+ hidrometroDiametro.getDebitoTipo().getId());	
				form.setDescricaoDebito(debitoTipo.getDescricao());
				sessao.setAttribute("debitoTipoEncontrado", true);
			}else{
				form.setIdDebito("");
				form.setDescricaoDebito("");
			}
			
			sessao.setAttribute("atualizarHidrometroDiametro", hidrometroDiametro);

			if (sessao.getAttribute("colecaoHidrometroDiametro") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarHidrometroDiametroAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarHidrometroDiametroAction.do");
			}

		}

		return retorno;
	}
	
	private void pesquisarDebitoTipo(HttpSession sessao, 
			AtualizarHidrometroDiametroActionForm form) {
		
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
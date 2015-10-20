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
package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroFonteCaptacao;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.FiltroTipoCaptacao;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.TipoCaptacao;
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
 * [UC0522] Atualizar Sistema de Abastecimento
 *
 * @author Fernando Fontelles Filho
 * @date 03/11/2009
 */

public class ExibirAtualizarSistemaAbastecimentoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("sistemaAbastecimentoAtualizar");				
		
		AtualizarSistemaAbastecimentoActionForm form = (AtualizarSistemaAbastecimentoActionForm)actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String id = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
		} else {
			//id = ((SistemaAbastecimento) sessao.getAttribute("sistemaAbastecimento")).getId().toString();
			id = ((SistemaAbastecimento)sessao.getAttribute("atualizarSistemaAbastecimento")).getId().toString();
		}
		
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
		
		SistemaAbastecimento sistemaAbastecimento = new SistemaAbastecimento();
		
		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {
		
			FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();
			
			filtroSistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
			filtroSistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao.tipoCaptacao");
			
			filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSistemaAbastecimento.ID, id));
			
			Collection colecaoSistemaAbastecimento = fachada.pesquisar
				(filtroSistemaAbastecimento,SistemaAbastecimento.class.getName());
			
			if(colecaoSistemaAbastecimento != null && !colecaoSistemaAbastecimento.isEmpty()){
			
				sistemaAbastecimento = (SistemaAbastecimento)Util.retonarObjetoDeColecao(colecaoSistemaAbastecimento);
				
			}
			
			if(id == null || id.trim().equals("")){
				
				form.setId(sistemaAbastecimento.getId().toString());
				form.setDescricao(sistemaAbastecimento.getDescricao());
				form.setDescricaoAbreviada(sistemaAbastecimento.getDescricaoAbreviada());
				form.setIndicadorUso(sistemaAbastecimento.getIndicadorUso());
				
				if(sistemaAbastecimento.getFonteCaptacao() != null){
					
					if (sistemaAbastecimento.getFonteCaptacao().getTipoCaptacao() != null){
						
						form.setTipoCaptacao(sistemaAbastecimento.getFonteCaptacao().getTipoCaptacao().getId().toString());
						form.setDescricaoTipoCaptacao(sistemaAbastecimento.getFonteCaptacao().getTipoCaptacao().getDescricao());
						
					}
					
					form.setFonteCaptacaoId(sistemaAbastecimento.getFonteCaptacao().getId().toString());
					form.setFonteCaptacaoDescricao(sistemaAbastecimento.getFonteCaptacao().getDescricao());
					
				}
				
			}
			
			form.setId(sistemaAbastecimento.getId().toString());
			form.setDescricao(sistemaAbastecimento.getDescricao());
			form.setDescricaoAbreviada(sistemaAbastecimento.getDescricaoAbreviada());
			form.setIndicadorUso(sistemaAbastecimento.getIndicadorUso());
			
			if(sistemaAbastecimento.getFonteCaptacao() != null){
				
				if (sistemaAbastecimento.getFonteCaptacao().getTipoCaptacao() != null){
					
					form.setTipoCaptacao(sistemaAbastecimento.getFonteCaptacao().getTipoCaptacao().getId().toString());
					form.setDescricaoTipoCaptacao(sistemaAbastecimento.getFonteCaptacao().
							getTipoCaptacao().getDescricao());
					
				}
				
				form.setFonteCaptacaoId(sistemaAbastecimento.getFonteCaptacao().getId().toString());
				form.setFonteCaptacaoDescricao(sistemaAbastecimento.getFonteCaptacao().getDescricao());
				
			}
			
			if (sistemaAbastecimento.getFonteCaptacao() != null 
					&& sistemaAbastecimento.getFonteCaptacao().getId() != null
					&& sistemaAbastecimento.getFonteCaptacao().getDescricao() != null
					&& !sistemaAbastecimento.getFonteCaptacao().getDescricao().equals("FONTE DE CAPTAC�O INEXISTENTE")){
				httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
			}
			
			if (sistemaAbastecimento.getFonteCaptacao() != null
					&& sistemaAbastecimento.getFonteCaptacao().getTipoCaptacao().getId() != null
					&& sistemaAbastecimento.getFonteCaptacao().getTipoCaptacao().getDescricao() != null
					&& !sistemaAbastecimento.getFonteCaptacao().getTipoCaptacao().getDescricao().equals("TIPO DE CAPTAC�O INEXISTENTE")){
				httpServletRequest.setAttribute("tipoCaptacaoEncontrado", true);
			}
			
			sessao.setAttribute("atualizarSistemaAbastecimento", sistemaAbastecimento);

			if (sessao.getAttribute("colecaoSistemaAbastecimento") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarSistemaAbastecimentoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarSistemaAbastecimentoAction.do");
			}
			
		}
		
		// pesquisa os dados do enter
		pesquisarEnter(form, httpServletRequest,fachada); 

		return retorno;
	
	}

	private void pesquisarEnter(
			AtualizarSistemaAbastecimentoActionForm form,
			HttpServletRequest httpServletRequest, Fachada fachada) {

		
		if ( httpServletRequest.getParameter("idTipoCaptacao") != null ) {
			String idTipoCaptacao = (String) httpServletRequest.getParameter("idTipoCaptacao");
			form.setTipoCaptacao(idTipoCaptacao);
		}
		
		if ( httpServletRequest.getParameter("idFonteCaptacao") != null ) {
			String idFonteCaptacao = (String) httpServletRequest.getParameter("idFonteCaptacao");
			form.setFonteCaptacaoId(idFonteCaptacao);
		}
		
//		 pesquisa enter de FONTE DE CAPTACAO sem ter realizado pesquisa de TIPO DE CAPTACAO
		if ((form.getTipoCaptacao() == null || form.getTipoCaptacao().equals("")) 
				&& form.getFonteCaptacaoId() != null
				&& !form.getFonteCaptacaoId().equals("")) {

			FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();
			
			try {
				filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
						FiltroFonteCaptacao.ID, new Integer(
								form.getFonteCaptacaoId())));
			} catch (NumberFormatException ex) {
				throw new ActionServletException(
						"atencao.campo_texto.numero_obrigatorio", null,
						"Fonte de Capta��o");
			}
			
			filtroFonteCaptacao
					.setCampoOrderBy(FiltroFonteCaptacao.DESCRICAO);
			Collection colecaoFonteCaptacao = fachada.pesquisar(
					filtroFonteCaptacao, FonteCaptacao.class.getName());

			if (colecaoFonteCaptacao != null
					&& !colecaoFonteCaptacao.isEmpty()) {
				FonteCaptacao fonteCaptacao = (FonteCaptacao) Util.retonarObjetoDeColecao(colecaoFonteCaptacao);
				form.setFonteCaptacaoDescricao(fonteCaptacao.getDescricao());
				if (form.getDescricaoTipoCaptacao() != null && form.getTipoCaptacao() != null
						&& !form.getDescricaoTipoCaptacao().equals("TIPO DE CAPTAC�O INEXISTENTE")){
					httpServletRequest.setAttribute("tipoCaptacaoEncontrado", true);
				}
				httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
			} else {
				form.setFonteCaptacaoId("");
				form.setFonteCaptacaoDescricao("FONTE DE CAPTAC�O INEXISTENTE");
				httpServletRequest.removeAttribute("fonteCaptacaoEncontrada");
			}

		}
		
		//pesquisa enter de tipo de captacao
		if (form.getTipoCaptacao() != null
				&& !form.getTipoCaptacao().equals("")) {

			FiltroTipoCaptacao filtroTipoCaptacao = new FiltroTipoCaptacao();
			
			try {
				filtroTipoCaptacao.adicionarParametro(new ParametroSimples(
						FiltroTipoCaptacao.ID, new Integer(
								form.getTipoCaptacao())));
			} catch (NumberFormatException ex) {
				throw new ActionServletException(
						"atencao.campo_texto.numero_obrigatorio", null,
						"Tipo de Capta��o");
			}
			
			filtroTipoCaptacao
					.setCampoOrderBy(FiltroFonteCaptacao.DESCRICAO);
			Collection colecaoTipoCaptacao = fachada.pesquisar(
					filtroTipoCaptacao, TipoCaptacao.class.getName());

			if (colecaoTipoCaptacao != null
					&& !colecaoTipoCaptacao.isEmpty()) {
				TipoCaptacao tipoCaptacao = (TipoCaptacao) Util.retonarObjetoDeColecao(colecaoTipoCaptacao);
				form.setDescricaoTipoCaptacao(tipoCaptacao.getDescricao());
				if (form.getFonteCaptacaoDescricao() != null && form.getFonteCaptacaoId() != null 
						&&	!form.getFonteCaptacaoDescricao().equals("FONTE DE CAPTAC�O INEXISTENTE")){
					httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
				}
				httpServletRequest.setAttribute("tipoCaptacaoEncontrado", true);
				
			} else {
				form.setTipoCaptacao("");
				form.setDescricaoTipoCaptacao("TIPO DE CAPTAC�O INEXISTENTE");
				httpServletRequest.removeAttribute("tipoCaptacaoEncontrado");
			}

		}
		
		//Pesquisar Fonte de Captacao Com Tipo de Captacao
		if (form.getTipoCaptacao() != null
				&& !form.getTipoCaptacao().equals("")
				&& form.getFonteCaptacaoId() != null
				&& !form.getFonteCaptacaoId().equals("")) {

			FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();
			
			try {
				filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
						FiltroFonteCaptacao.ID_TIPO_CAPTACAO,form.getTipoCaptacao()));
				filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
						FiltroFonteCaptacao.ID, new Integer(
								form.getFonteCaptacaoId())));
			} catch (NumberFormatException ex) {
				throw new ActionServletException(
						"atencao.campo_texto.numero_obrigatorio", null,
						"Fonte de Capta��o");
			}
			
			filtroFonteCaptacao
					.setCampoOrderBy(FiltroFonteCaptacao.DESCRICAO);
			Collection colecaoFonteCaptacao = fachada.pesquisar(
					filtroFonteCaptacao, FonteCaptacao.class.getName());

			if (colecaoFonteCaptacao != null
					&& !colecaoFonteCaptacao.isEmpty()) {
				FonteCaptacao fonteCaptacao = (FonteCaptacao) Util.retonarObjetoDeColecao(colecaoFonteCaptacao);
				form.setFonteCaptacaoDescricao(fonteCaptacao.getDescricao());
				httpServletRequest.setAttribute("tipoCaptacaoEncontrado", true);
				httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
			} else {
				form.setFonteCaptacaoId("");
				form.setFonteCaptacaoDescricao("FONTE DE CAPTAC�O INEXISTENTE");
				httpServletRequest.removeAttribute("fonteCaptacaoEncontrada");
			}

		}
		
	}
}
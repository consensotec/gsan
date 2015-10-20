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
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.FiltroZonaPressao;
import gcom.operacional.ZonaPressao;
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
 * 
 * @author Vinicius Medeiros
 * @date 21/05/2008
 */
public class ExibirAtualizarZonaPressaoAction extends GcomAction {

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

	private String distritoOperacionalID;

	private Collection colecaoPesquisa;

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("zonaPressaoAtualizar");

		AtualizarZonaPressaoActionForm atualizarZonaPressaoActionForm = (AtualizarZonaPressaoActionForm) actionForm;

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar quando houver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		ZonaPressao zonaPressao = new ZonaPressao();

		String id = httpServletRequest.getParameter("idRegistroAtualizacao");

		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");

		FiltroZonaPressao filtroZonaPressao = new FiltroZonaPressao();
		
		filtroZonaPressao.adicionarCaminhoParaCarregamentoEntidade(FiltroZonaPressao.DISTRITO_OPERACIONAL);	
		
		String zonaPressaoID = null;
		if ((objetoConsulta == null || objetoConsulta.equalsIgnoreCase(""))
				&& (httpServletRequest.getParameter("desfazer") == null)) {
			
			// Recupera o id da Zona de Pressao que vai ser atualizada
			if (httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null) {

				zonaPressaoID = httpServletRequest.getParameter("idRegistroInseridoAtualizar");

				// Define a volta do bot�o Voltar para Filtrar Zona de Press�o
				sessao.setAttribute("voltar", "filtrar");
				sessao.setAttribute("idRegistroAtualizar", zonaPressaoID);
				sessao.setAttribute("indicadorUso", "3");
			
			} else if (httpServletRequest.getParameter("idRegistroAtualizar") == null) {
				
				zonaPressaoID = (String) sessao.getAttribute("idRegistroAtualizar");
				// Define a volta do bot�o Voltar para Filtrar Zona de Press�o
				sessao.setAttribute("voltar", "filtrar");
			
			} else if (httpServletRequest.getParameter("idRegistroAtualizar") != null) {
			
				zonaPressaoID = httpServletRequest.getParameter("idRegistroAtualizar");

				// Define a volta do bot�o Voltar para Manter Zona de Press�o
				sessao.setAttribute("voltar", "manter");
				sessao.setAttribute("idRegistroAtualizar", zonaPressaoID);
			
			}

		} else {
			
			zonaPressaoID = (String) sessao.getAttribute("idRegistroAtualizar");
		
		}

		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

			// Recebe o valor do campo distritoOperacionalID do formul�rio.
			distritoOperacionalID = atualizarZonaPressaoActionForm.getDistritoOperacionalID();

			switch (Integer.parseInt(objetoConsulta)) {

			// Distrito Operacional
			case 1:

				FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

				filtroDistritoOperacional.adicionarParametro(
						new ParametroSimples(FiltroDistritoOperacional.ID,
								distritoOperacionalID));

				filtroDistritoOperacional.adicionarParametro(
						new ParametroSimples(FiltroDistritoOperacional.INDICADORUSO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna Distrito Operacional
				colecaoPesquisa = fachada.pesquisar(
						filtroDistritoOperacional,DistritoOperacional.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Distrito Operacional nao encontrado
					// Limpa o campo distritoOperacionalID do formul�rio
					atualizarZonaPressaoActionForm.setDistritoOperacionalID("");
					atualizarZonaPressaoActionForm.setDistritoOperacionalDescricao("Distrito operacional inexistente.");
					httpServletRequest.setAttribute("corDistritoOperacional","exception");

					httpServletRequest.setAttribute("nomeCampo","distritoOperacionalID");
					
				} else {
					
					DistritoOperacional objetoDistritoOperacional = 
						(DistritoOperacional) Util.retonarObjetoDeColecao(colecaoPesquisa);
					
					atualizarZonaPressaoActionForm.setDistritoOperacionalID(
							String.valueOf(objetoDistritoOperacional.getId()));
					atualizarZonaPressaoActionForm.setDistritoOperacionalDescricao(
							objetoDistritoOperacional.getDescricao());
					
					httpServletRequest.setAttribute("corDistritoOperacional","valor");
					httpServletRequest.setAttribute("nomeCampo","distritoOperacionalID");

				}

				break;

			}
		}

		// Verifica se veio do Filtrar ou do Manter
		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
				
			} else {
				
				id = (String) httpServletRequest.getAttribute("idRegistroAtualizacao").toString();
			
			}
			
		} else {
			sessao.setAttribute("i", true);
		}
		
		if (zonaPressao.getDistritoOperacional() != null) {
			
			atualizarZonaPressaoActionForm.setDistritoOperacionalID(
					zonaPressao.getDistritoOperacional().getId().toString());
			atualizarZonaPressaoActionForm.setDistritoOperacionalDescricao(
					zonaPressao.getDistritoOperacional().getDescricao());

		}

		if (zonaPressao == null) {

			if (id != null && !id.equals("")) {

				FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
				filtroDistritoOperacional.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
			
				filtroDistritoOperacional.adicionarParametro(
						new ParametroSimples(FiltroDistritoOperacional.ID, id));

				Collection colecaoZonaPressao = fachada.pesquisar(
						filtroDistritoOperacional, DistritoOperacional.class.getName());

				if (colecaoZonaPressao != null && !colecaoZonaPressao.isEmpty()) {
					
					zonaPressao = (ZonaPressao) Util.retonarObjetoDeColecao(colecaoZonaPressao);
				
				}
			}
		}

		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			filtroZonaPressao.adicionarParametro(
					new ParametroSimples(FiltroZonaPressao.ID, id));
			
			Collection colecaoZonaPressao = fachada.pesquisar(
					filtroZonaPressao, ZonaPressao.class.getName());
			
			if (colecaoZonaPressao != null && !colecaoZonaPressao.isEmpty()) {
			
				zonaPressao = (ZonaPressao) Util.retonarObjetoDeColecao(colecaoZonaPressao);
			
			}


			if (id == null || id.trim().equals("")) {

				atualizarZonaPressaoActionForm.setId(zonaPressao.getId().toString());
				atualizarZonaPressaoActionForm.setDescricao(zonaPressao.getDescricaoZonaPressao());
				atualizarZonaPressaoActionForm.setDescricaoAbreviada(zonaPressao.getDescricaoAbreviada());
				
				atualizarZonaPressaoActionForm.setDistritoOperacionalID(
						zonaPressao.getDistritoOperacional().getId().toString());
				atualizarZonaPressaoActionForm.setDistritoOperacionalDescricao(
						zonaPressao.getDistritoOperacional().getDescricao());

			}

			atualizarZonaPressaoActionForm.setId(id);
			atualizarZonaPressaoActionForm.setDescricao(zonaPressao.getDescricaoZonaPressao());
			atualizarZonaPressaoActionForm.setDescricaoAbreviada(zonaPressao.getDescricaoAbreviada());
			atualizarZonaPressaoActionForm.setIndicadorUso(""+ zonaPressao.getIndicadorUso());

			atualizarZonaPressaoActionForm.setDistritoOperacionalID(
					zonaPressao.getDistritoOperacional().getId().toString());
			atualizarZonaPressaoActionForm.setDistritoOperacionalDescricao(
					zonaPressao.getDistritoOperacional().getDescricao());

			sessao.setAttribute("atualizarZonaPressao", zonaPressao);

			if (sessao.getAttribute("colecaoZonaPressao") != null) {
				
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarZonaPressaoAction.do");
			} else {
				
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarZonaPressaoAction.do");
			
			}

		}

		return retorno;
	}
}
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

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroProducaoAgua;
import gcom.operacional.ProducaoAgua;
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
 * @date 11/06/2008
 */
public class ExibirAtualizarProducaoAguaAction extends GcomAction {

	
	private Collection colecaoPesquisa;

	
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

		// Seta o caminho do retorno
		ActionForward retorno = actionMapping.findForward("producaoAguaAtualizar");

		AtualizarProducaoAguaActionForm atualizarProducaoAguaActionForm =(AtualizarProducaoAguaActionForm) actionForm;

		// Mudar isso quando houver a implementa��o do esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		String id = httpServletRequest.getParameter("idRegistroAtualizacao");
		
		if (id == null) {
			
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") != null) {
			
				id = (String) httpServletRequest.getAttribute("idRegistroAtualizacao");
			
			}
		}
		
		FiltroProducaoAgua filtroProducaoAgua = new FiltroProducaoAgua();
		filtroProducaoAgua.adicionarCaminhoParaCarregamentoEntidade(FiltroProducaoAgua.LOCALIDADE);

		ProducaoAgua producaoAgua = new ProducaoAgua();
		
		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			filtroProducaoAgua.adicionarParametro(
				new ParametroSimples(FiltroProducaoAgua.ID, id));
			
			Collection colecaoProducaoAgua = 
				this.getFachada().pesquisar(filtroProducaoAgua, ProducaoAgua.class.getName());
			
			if (colecaoProducaoAgua != null && !colecaoProducaoAgua.isEmpty()) {
		
				producaoAgua = (ProducaoAgua) Util.retonarObjetoDeColecao(colecaoProducaoAgua);
			
			}

			if (producaoAgua != null) {

				atualizarProducaoAguaActionForm.setId(producaoAgua.getId().toString());
				atualizarProducaoAguaActionForm.setAnoMesReferencia(
					Util.formatarAnoMesParaMesAno(producaoAgua.getAnoMesReferencia()));

				atualizarProducaoAguaActionForm.setVolumeProduzido(
					Util.formataBigDecimal(producaoAgua.getVolumeProduzido(),2,true));

				atualizarProducaoAguaActionForm.setLocalidadeID(
					producaoAgua.getLocalidade().getId().toString());

				atualizarProducaoAguaActionForm.setLocalidadeDescricao(
					producaoAgua.getLocalidade().getDescricao());

			}

		}
		
		sessao.setAttribute("atualizarProducaoAgua", producaoAgua);
		
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {

			// Localidade
			case 1:

				// Recebe o valor do campo localidadeID do formul�rio.
				String localidadeID = atualizarProducaoAguaActionForm.getLocalidadeID();

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,localidadeID));

				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna Localidade
				colecaoPesquisa = 
					this.getFachada().pesquisar(filtroLocalidade,Localidade.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Localidade nao encontrado
					// Limpa o campo localidadeID do formul�rio
					atualizarProducaoAguaActionForm.setLocalidadeID("");
					atualizarProducaoAguaActionForm.setLocalidadeDescricao("Localidade inexistente.");
					
					httpServletRequest.setAttribute("corLocalidade","exception");
					httpServletRequest.setAttribute("nomeCampo","localidadeID");
			
				} else {
					
					Localidade objetoLocalidade = 
						(Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
					
					atualizarProducaoAguaActionForm.setLocalidadeID(
						String.valueOf(objetoLocalidade.getId()));
					
					atualizarProducaoAguaActionForm.setLocalidadeDescricao(
						objetoLocalidade.getDescricao());
					
					httpServletRequest.setAttribute("corLocalidade","valor");
					httpServletRequest.setAttribute("nomeCampo","localidadeID");
				}

				break;
			}
		}

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (sessao.getAttribute("colecaoProducaoAgua") != null) {
			sessao.setAttribute("caminhoRetornoVoltar","/gsan/filtrarProducaoAguaAction.do");
		} else {
			sessao.setAttribute("caminhoRetornoVoltar","/gsan/exibirFiltrarProducaoAguaAction.do");
		}


		return retorno;
	}
}
/**
 * 
 */
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

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * @author Vin�cius Medeiros
 * @date 10/06/2008
 */

public class ExibirFiltrarProducaoAguaAction extends GcomAction {

	private String localidadeID;

	private Collection colecaoPesquisa;

	/*
	 * @param actionMapping @param actionForm @param httpServletRequest @param
	 * httpServletResponse @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho do retorno
		ActionForward retorno = actionMapping.findForward("filtrarProducaoAgua");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando houver implementa��o do esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarProducaoAguaActionForm filtrarProducaoAguaActionForm = (FiltrarProducaoAguaActionForm) actionForm;

		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			
			sessao.setAttribute("indicadorAtualizar", "1");
		
		}

		if (filtrarProducaoAguaActionForm.getIndicadorAtualizar() == null) {
		
			filtrarProducaoAguaActionForm.setIndicadorAtualizar("1");
		}
		

		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");

		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

			// Recebe o valor do campo localidadeID do formul�rio.
			localidadeID = filtrarProducaoAguaActionForm.getLocalidadeID();

			switch (Integer.parseInt(objetoConsulta)) {

			// Distrito Operacional
			case 1:
				pesquisarLocalidade(
						filtrarProducaoAguaActionForm, fachada,httpServletRequest);
				break;
				
			default:
				break;
			}
		}

		if (httpServletRequest.getParameter("desfazer") != null	
				&& httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {

			filtrarProducaoAguaActionForm.setId("");
			filtrarProducaoAguaActionForm.setVolumeProduzido("");
			filtrarProducaoAguaActionForm.setAnoMesReferencia("");
			filtrarProducaoAguaActionForm.setLocalidadeID("");

		}
		return retorno;

	}

	private void pesquisarLocalidade(
			FiltrarProducaoAguaActionForm filtrarProducaoAguaActionForm,Fachada fachada, 
			HttpServletRequest httpServletRequest) {
		
		if (localidadeID == null || localidadeID.trim().equalsIgnoreCase("")) {
			
			// Limpa o campo localidadeID do formulario
			filtrarProducaoAguaActionForm.setLocalidadeDescricao("Informe Localidade");
			httpServletRequest.setAttribute("corLocalidade", "exception");
		
		} else {
		
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			// Retorna Localidade
			colecaoPesquisa = fachada.pesquisar(
					filtroLocalidade,Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Distrito Operacional nao encontrada
				// Limpa o campo DistritoOperacionalID do formul�rio
				filtrarProducaoAguaActionForm.setLocalidadeID("");
				filtrarProducaoAguaActionForm.setLocalidadeDescricao("Localidade inexistente.");
				httpServletRequest.setAttribute("corLocalidade", "exception");

				httpServletRequest.setAttribute("nomeCampo", "localidadeID");
			
			} else {
			
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				filtrarProducaoAguaActionForm.setLocalidadeID(String.valueOf(objetoLocalidade.getId()));
				filtrarProducaoAguaActionForm.setLocalidadeDescricao(objetoLocalidade.getDescricao());
				httpServletRequest.setAttribute("corLocalidade", "valor");

			}
		}
	}
}

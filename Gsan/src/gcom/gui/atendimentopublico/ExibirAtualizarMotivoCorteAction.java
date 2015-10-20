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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
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
 * @date 07/04/2008
 */
public class ExibirAtualizarMotivoCorteAction  extends GcomAction {

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
			.findForward("atualizarMotivoCorte");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando houver um esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarMotivoCorteActionForm atualizarMotivoCorteActionForm = (AtualizarMotivoCorteActionForm) actionForm;
		
		String idMotivoCorte = httpServletRequest
			.getParameter("idRegistroAtualizacao");
		
		// Verifica se veio do Filtrar ou Manter
		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}
		
		
		if (idMotivoCorte == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				idMotivoCorte = (String) sessao
						.getAttribute("idRegistroAtualizacao");
			} else {
				idMotivoCorte = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}
		
		MotivoCorte motivoCorte = new MotivoCorte();
		
		
		if (idMotivoCorte != null && !idMotivoCorte.trim().equals("")
				&& Integer.parseInt(idMotivoCorte) > 0) {

			FiltroMotivoCorte filtroMotivoCorte = new FiltroMotivoCorte();
			filtroMotivoCorte.adicionarParametro(new ParametroSimples(
					FiltroMotivoCorte.ID, idMotivoCorte));
			Collection colecaoMotivoCorte = fachada.pesquisar(
					filtroMotivoCorte, MotivoCorte.class.getName());
			if (colecaoMotivoCorte != null && !colecaoMotivoCorte.isEmpty()) {
				motivoCorte = (MotivoCorte) Util
						.retonarObjetoDeColecao(colecaoMotivoCorte);
			}
			
			if (idMotivoCorte == null || idMotivoCorte.trim().equals("")) {

				atualizarMotivoCorteActionForm.setIdMotivoCorte(motivoCorte
						.getId().toString());

				atualizarMotivoCorteActionForm.setDescricao(motivoCorte
						.getDescricao());

			}
			
			atualizarMotivoCorteActionForm.setIdMotivoCorte(idMotivoCorte);
			
			atualizarMotivoCorteActionForm.setDescricao(motivoCorte.getDescricao());

			atualizarMotivoCorteActionForm.setIndicadorUso(""+motivoCorte
					.getIndicadorUso());

			sessao.setAttribute("motivoCorteAtualizar", motivoCorte);

			if(sessao.getAttribute("colecaoMotivoCorte") != null){
			sessao.setAttribute("caminhoRetornoVoltar",
					"/gsan/filtrarMotivoCorteAction.do");
			}else{
				sessao.setAttribute("caminhoRetornoVoltar",
				"/gsan/exibirFiltrarMotivoCorteAction.do");
			}

		}
		
    	return retorno;
}
}
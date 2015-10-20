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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1108] Filtrar Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo	
 * @date 23/12/2010
 */
public class ExibirManterCustoPavimentoPorRepavimentadoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterCustoPavimentoPorRepavimentadora");

		// Obt�m a inst�ncia da fachada
		// Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Limpa o atributo se o usu�rio voltou para o manter
		if (sessao.getAttribute("colecaoCustoPavimentoRua") != null) {
		//	sessao.removeAttribute("colecaoCustoPavimentoRua");
		}
		
		if (sessao.getAttribute("colecaoCustoPavimentoCalcada") != null) {
		//	sessao.removeAttribute("colecaoCustoPavimentoCalcada");
		}

		Collection<UnidadeRepavimentadoraCustoPavimentoRua> colecaoCustoPavimentoRua = (Collection<UnidadeRepavimentadoraCustoPavimentoRua>) httpServletRequest.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua");
		
		Collection<UnidadeRepavimentadoraCustoPavimentoCalcada> colecaoCustoPavimentoCalcada = (Collection<UnidadeRepavimentadoraCustoPavimentoCalcada>) httpServletRequest.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada");
		
		// Rua
		if (colecaoCustoPavimentoRua != null && !colecaoCustoPavimentoRua.isEmpty()) {
			
			if (httpServletRequest.getParameter("atualizarRua") != null) {
				
				retorno = actionMapping.findForward("atualizarUnidadeRepavimentadoraCustoPavimentoRua");

				UnidadeRepavimentadoraCustoPavimentoRua unidadeCustoPavimentoRua = (UnidadeRepavimentadoraCustoPavimentoRua) colecaoCustoPavimentoRua.iterator().next();
				sessao.setAttribute("objetoUnidadeCustoPavimentoRua", unidadeCustoPavimentoRua);

			} else {
				
				sessao.removeAttribute("objetoUnidadeCustoPavimentoRua");
				httpServletRequest.setAttribute("colecaoCustoPavimentoRua", colecaoCustoPavimentoRua);
			}
		} else if (colecaoCustoPavimentoCalcada == null || colecaoCustoPavimentoCalcada.isEmpty()){
			// Caso a pesquisa n�o retorne nenhum objeto comunica ao usu�rio;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		// Cal�ada
		if (colecaoCustoPavimentoCalcada != null && !colecaoCustoPavimentoCalcada.isEmpty()) {
			
			if (httpServletRequest.getParameter("atualizarCalcada") != null) {
				
				retorno = actionMapping.findForward("atualizarUnidadeRepavimentadoraCustoPavimentoCalcada");

				UnidadeRepavimentadoraCustoPavimentoCalcada unidadeCustoPavimentoCalcada = (UnidadeRepavimentadoraCustoPavimentoCalcada) colecaoCustoPavimentoCalcada.iterator().next();
				sessao.setAttribute("objetoUnidadeCustoPavimentoCalcada", unidadeCustoPavimentoCalcada);

			} else {
				
				sessao.removeAttribute("objetoUnidadeCustoPavimentoCalcada");
				httpServletRequest.setAttribute("colecaoCustoPavimentoCalcada", colecaoCustoPavimentoCalcada);
			}
		} else if (colecaoCustoPavimentoRua == null || colecaoCustoPavimentoRua.isEmpty()){
			// Caso a pesquisa n�o retorne nenhum objeto comunica ao usu�rio;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}

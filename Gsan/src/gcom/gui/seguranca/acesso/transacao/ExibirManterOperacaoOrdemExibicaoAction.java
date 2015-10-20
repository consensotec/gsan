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
package gcom.gui.seguranca.acesso.transacao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.FiltroOperacaoOrdemExibicao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoOrdemExibicao;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Permite exibir uma lista com as resolu��es de diretoria retornadas do
 * FiltrarManterTipoRetornoOrdemServicoReferidaAction ou ir para o
 * ExibirManterTipoRetornoOrdemServicoReferidaAction
 * 
 * @author Thiago Ten�rio
 * @since 31/10/2006
 */
public class ExibirManterOperacaoOrdemExibicaoAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("manterOperacaoOrdemExibicao");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		String idOperacao = (String) httpServletRequest.getParameter("idOperacao");
		boolean novaOperacao = false;
				
		if (idOperacao == null) {
			idOperacao = (String) sessao.getAttribute("idOperacao");
		} else {
			novaOperacao = true;
			sessao.setAttribute("idOperacao", idOperacao);
		}
		Collection colOperOrdemExib  = new Vector();
		
		if (idOperacao != null){
			
			colOperOrdemExib = (Collection) sessao.getAttribute("colecaoOrdens");
			
			if (novaOperacao){
				FiltroOperacaoOrdemExibicao filtro = new FiltroOperacaoOrdemExibicao();
				filtro.adicionarParametro(new ParametroSimples(
						FiltroOperacaoOrdemExibicao.OPERACAO_ID, idOperacao));
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoOrdemExibicao.TABELA_COLUNA);
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoOrdemExibicao.TABELA_COLUNA_TABELA);
				colOperOrdemExib = Fachada.getInstancia().pesquisar(filtro,
						OperacaoOrdemExibicao.class.getSimpleName());
			
				FiltroOperacao filtroOper = new FiltroOperacao();
				filtroOper.adicionarParametro(new ParametroSimples(
						FiltroOperacao.ID, idOperacao));
				Collection colOperacoes = Fachada.getInstancia().pesquisar(filtroOper,
						Operacao.class.getSimpleName());
				Operacao operacao = (Operacao) Util.retonarObjetoDeColecao(colOperacoes);
				if (operacao != null){
					sessao.setAttribute("operacao", operacao);	
				} else {
					throw new ActionServletException("atencao.operacaoOrdemExibicao.nao.existente");		
				}
				
			}			
			
		}
		 
		//	Ordena a cole��o pelo valor de ordem
		Comparator object = new Comparator() {
			public int compare(Object a, Object b) {
				Integer ordem1 = ((OperacaoOrdemExibicao) a).getNumeroOrdem() ;
				Integer ordem2 = ((OperacaoOrdemExibicao) b).getNumeroOrdem() ;
		
				if (ordem1 == null){
					ordem1 = new Integer(99999);					
				}
				if (ordem2 == null){
					ordem2 = new Integer(99999);					
				}
				return ordem1.compareTo(ordem2);

			}
		};
		List list = (List) colOperOrdemExib;
		Collections.sort(list, object);
		
		sessao.setAttribute("colecaoOrdens", colOperOrdemExib);

		return retorno;

	}

}

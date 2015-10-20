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
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.OperacaoOrdemExibicao;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
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
 * @author Francisco do Nascimento
 * @since 25/02/08
 */
public class AtualizarOperacaoOrdemExibicaoAction extends GcomAction {

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

		// Seta o retorno
		ActionForward retorno;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colOperOrdemExib = (Collection) sessao.getAttribute("colecaoOrdens");
		Collection alterados = new Vector();
		
		for (Iterator iter = colOperOrdemExib.iterator(); iter.hasNext();) {
			OperacaoOrdemExibicao ordemExib = (OperacaoOrdemExibicao) iter.next();
			Integer idTabelaColuna = ordemExib.getTabelaColuna().getId();
			String novaOrdem = httpServletRequest.getParameter("ordem" + idTabelaColuna);
			if (!ordemExib.getNumeroOrdem().equals(Integer.parseInt(novaOrdem))){
				ordemExib.setNumeroOrdem(Integer.parseInt(novaOrdem));
				ordemExib.setUltimaAlteracao(new Date());
				alterados.add(ordemExib);
			}
						
		}
		
		String refreshOrdenar = httpServletRequest.getParameter("ordenar");
		if (refreshOrdenar != null){
			retorno = actionMapping.findForward("exibirManterOperacaoOrdemExibicao");
//			Ordena a cole��o pelo valor de ordem
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

		} else {
			retorno = actionMapping.findForward("telaSucesso");
			// atualizar no banco
			for (Iterator iter = alterados.iterator(); iter.hasNext();) {
				OperacaoOrdemExibicao operOrdem = (OperacaoOrdemExibicao) iter.next();
				Fachada.getInstancia().inserirOuAtualizar(operOrdem);				
			}
			
			montarPaginaSucesso(httpServletRequest,
					"Ordem de exibi��o alterada com sucesso.", 
					"Manter outra tabela e coluna",
					"exibirFiltrarTabelaColunaAction.do?menu=sim");
			
		}		
		
		return retorno;

	}

}

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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.FiltroPagamentoHistorico;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Vivianne Sousa
 * @created 10/07/2007
 */
public class ExibirConsultarPagamentoPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarPagamentoPopup");

		Fachada fachada = Fachada.getInstancia();
		
		ConsultarPagamentoPopupActionForm consultarPagamentoPopupActionForm = 
			(ConsultarPagamentoPopupActionForm) actionForm;
		

		String idPagamento = httpServletRequest.getParameter("idPagamento");
		String idPagamentoHistorico = httpServletRequest.getParameter("idPagamentoHistorico");
		
		if (idPagamento != null){
			
			FiltroPagamento filtroPagamento = new FiltroPagamento();
			filtroPagamento.adicionarParametro(new ParametroSimples(FiltroPagamento.ID, new Integer(idPagamento)));
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador.cliente");

			// Pesquisa o pagamento no sistema com os par�metros informados no
			// filtro
			Collection colecaoPagamentos = fachada.pesquisar(filtroPagamento,Pagamento.class.getName());
			
			// Caso a pesquisa tenha retornado o pagamento
			if (colecaoPagamentos != null && !colecaoPagamentos.isEmpty()) {
				// Recupera da cole��o o pagamento que vai ser atualizado
				Pagamento pagamento = (Pagamento) Util.retonarObjetoDeColecao(colecaoPagamentos);
				
				consultarPagamentoPopupActionForm.setCodigoAgenteArrecadador("" + 
						pagamento.getAvisoBancario().getArrecadador().getCodigoAgente());
				
				if (pagamento.getAvisoBancario().getArrecadador().getCliente() != null){
					
					consultarPagamentoPopupActionForm.setNomeClienteArrecadador(
							pagamento.getAvisoBancario().getArrecadador().getCliente().getNome());
				}
				consultarPagamentoPopupActionForm.setUltimaAlteracaoPagamento(Util.formatarData(pagamento.getUltimaAlteracao()));
				
			}
			
		}else if(idPagamentoHistorico != null){
			
			FiltroPagamentoHistorico filtroPagamentoHistorico = new FiltroPagamentoHistorico();
			filtroPagamentoHistorico.adicionarParametro(new ParametroSimples(FiltroPagamentoHistorico.ID, new Integer(idPagamentoHistorico)));
			filtroPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador.cliente");

			// Pesquisa o pagamento no sistema com os par�metros informados no
			// filtro
			Collection colecaoPagamentos = fachada.pesquisar(filtroPagamentoHistorico,PagamentoHistorico.class.getName());
			
			// Caso a pesquisa tenha retornado o pagamento
			if (colecaoPagamentos != null && !colecaoPagamentos.isEmpty()) {
				// Recupera da cole��o o pagamento que vai ser atualizado
				PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) Util.retonarObjetoDeColecao(colecaoPagamentos);
				
				consultarPagamentoPopupActionForm.setCodigoAgenteArrecadador("" + 
						pagamentoHistorico.getAvisoBancario().getArrecadador().getCodigoAgente());
				
				if (pagamentoHistorico.getAvisoBancario().getArrecadador().getCliente() != null){
					
					consultarPagamentoPopupActionForm.setNomeClienteArrecadador(
							pagamentoHistorico.getAvisoBancario().getArrecadador().getCliente().getNome());
				}
				consultarPagamentoPopupActionForm.setUltimaAlteracaoPagamento(Util.formatarData(pagamentoHistorico.getUltimaAlteracao()));
				
			}
			
			
		}
		
				
		return retorno;

	}

}

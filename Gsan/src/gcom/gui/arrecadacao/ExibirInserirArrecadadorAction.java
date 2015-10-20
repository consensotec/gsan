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
package gcom.gui.arrecadacao;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
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
 * Descri��o da classe
 * 
 * @author Marcio Roberto
 * @date 30/01/2007
 */
public class ExibirInserirArrecadadorAction extends GcomAction {

	/**
	 * [UC0506] Inserir Arrecadador
	 * 
	 * @author Marcio Roberto
	 * @date 30/01/2007
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

		ActionForward retorno = actionMapping.findForward("arrecadadorInserir");
		
		InserirArrecadadorActionForm arrecadadorActionForm = (InserirArrecadadorActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		String idCliente = arrecadadorActionForm.getIdCliente();
		String idImovel = arrecadadorActionForm.getIdImovel();
		
		// Verificar se o n�mero do cliente n�o est� cadastrado
		if (idCliente != null && !idCliente.trim().equals("")) {

			// Filtro para descobrir id do Cliente
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(
				new ParametroSimples(
						FiltroCliente.ID, 
						idCliente));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
	
			if (colecaoCliente == null || colecaoCliente.isEmpty()) {
				arrecadadorActionForm.setIdCliente("");
				arrecadadorActionForm.setNomeCliente("CLIENTE INEXISTENTE");
				httpServletRequest.setAttribute("existeCliente","exception");	
				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			}else{
				Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
				arrecadadorActionForm.setIdCliente(cliente.getId().toString());
				arrecadadorActionForm.setNomeCliente(cliente.getNome());
				httpServletRequest.setAttribute("nomeCampo","idCliente");
			}
		}

		// Verifica se o id do imovel n�o est� cadastrado
		if (idImovel != null && !idImovel.trim().equals("")) {

			// Filtro para descobrir id do Cliente
			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel.adicionarParametro(
				new ParametroSimples(
						FiltroImovel.ID, 
						idImovel));

			Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
	
			if (colecaoImovel == null || colecaoImovel.isEmpty()) {
				arrecadadorActionForm.setIdImovel(""); 
				arrecadadorActionForm.setInscricaoImovel("IMOVEL INEXISTENTE");
				httpServletRequest.setAttribute("existeImovel","exception");
				httpServletRequest.setAttribute("nomeCampo", "idImovel");
			}else{
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
				arrecadadorActionForm.setIdImovel(imovel.getId().toString());
				String inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel.getId()); 
				arrecadadorActionForm.setInscricaoImovel(inscricaoImovel);
				httpServletRequest.setAttribute("nomeCampo","idImovel");
			}
		}
		return retorno;
	}

}

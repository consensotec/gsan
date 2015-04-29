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
package gsan.gui.cadastro.cliente;

import gsan.cadastro.cliente.ClienteFone;
import gsan.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class RemoverClienteTelefoneAction extends GcomAction {
	/**
	 * < <Descri��o do m�todo>>
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

		// Prepara o retorno da A��o
		ActionForward retorno = null;

		if (httpServletRequest.getParameter("tela").trim().equals("inserir")) {
			retorno = actionMapping.findForward("inserirClienteTelefone");
		} else if (httpServletRequest.getParameter("tela").trim().equals(
				"atualizar")) {
			retorno = actionMapping.findForward("atualizarClienteTelefone");
		}

		// Obt�m a inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;

		Collection colecaoClienteFone = (Collection) sessao
				.getAttribute("colecaoClienteFone");

		ClienteFone clienteFone = null;

		// Obt�m os ids de remo��o
		String[] ids = (String[]) clienteActionForm.get("idRegistrosRemocao");

		if (ids != null && ids.length != 0) {
			if (colecaoClienteFone != null && !colecaoClienteFone.isEmpty()) {

				Iterator iteratorColecaoClienteFone = colecaoClienteFone
						.iterator();

				while (iteratorColecaoClienteFone.hasNext()) {
					clienteFone = (ClienteFone) iteratorColecaoClienteFone
							.next();
					for (int i = 0; i < ids.length; i++) {
						if (clienteFone.getDddTelefone().equals(ids[i])) {
							// Verifica se o clienteFone removido era o
							// principal para adicionar o indicador de principal
							// para o primeiro da lista
							if (obterTimestampIdObjeto(clienteFone) == (((Long) clienteActionForm
									.get("indicadorTelefonePadrao"))
									.longValue())
									&& colecaoClienteFone.size() > 1) {

								Iterator iteratorTemp = colecaoClienteFone
										.iterator();
								// Pega o primeiro da lista
								ClienteFone clienteFonePrimeiroDaLista = (ClienteFone) iteratorTemp
										.next();

								// Verifica se o primeiro da lista � o mesmo que
								// ser� removido
								if (clienteFonePrimeiroDaLista
										.equals(clienteFone)) {
									// Seta como principal o segundo da lista
									clienteFonePrimeiroDaLista = (ClienteFone) iteratorTemp
											.next();
								}

								// Seta o indicador no form
								clienteActionForm
										.set(
												"indicadorTelefonePadrao",
												new Long(
														obterTimestampIdObjeto(clienteFonePrimeiroDaLista)));
							}
							
							
							if (clienteActionForm.get("indicadorTelefoneSMS") != null && obterTimestampIdObjeto(clienteFone) == (((Long) clienteActionForm
									.get("indicadorTelefoneSMS")).longValue())){
								
								clienteActionForm.set("indicadorTelefoneSMS", null);
							}
							
							
							iteratorColecaoClienteFone.remove();
						}
					}

				}

			}

			clienteActionForm.set("botaoClicado", null);

			// Se a cole��o de telefones tiver apenas um item, ent�o este item
			// tem que estar selecionado
			// como telefone principal
			Iterator iterator = colecaoClienteFone.iterator();

			if (colecaoClienteFone != null && colecaoClienteFone.size() == 1) {

				clienteFone = (ClienteFone) iterator.next();

				clienteActionForm.set("indicadorTelefonePadrao", new Long(
						obterTimestampIdObjeto(clienteFone)));

			}
		}
		return retorno;
	}
}

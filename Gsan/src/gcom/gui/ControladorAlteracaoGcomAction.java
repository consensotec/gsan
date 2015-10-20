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
package gcom.gui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Respons�vel por executar a a��o de altera�ao
 * 
 * @author thiago toscano
 * @date 21/12/2005
 */
public abstract class ControladorAlteracaoGcomAction extends ControladorGcomAction {

	/**
	 * M�todo que responde pela a��o de exibi��o
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public final ActionForward exibir(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ControladorAlteracaoGcomActionForm form = (ControladorAlteracaoGcomActionForm) actionForm; 

		String[] chavesPrimarias = form.getChavePrimaria().split(ControladorGcomAction.PARAMETRO_SEPARADO_CHAVE_PRIMARIA);
		
		Object obj = this.consultarObjetoSistema(chavesPrimarias, request, form);
		
		this.montarFormulario(obj, form);
		
		ActionForward forward = this.exibirAuxiliar(actionMapping, actionForm, request, response);

		this.carregarColecao(form);

		form.setAcao(ControladorGcomAction.PARAMETRO_ACAO_PROCESSAR);

		if (forward != null) {
			return forward;
		}
		return actionMapping.findForward(ControladorGcomAction.FORWARD_EXIBIR);
	}

	/**
	 * M�todo que responde pela a��o de processamento 
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public final ActionForward processar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception{

		ControladorAlteracaoGcomActionForm form = (ControladorAlteracaoGcomActionForm) actionForm; 

		Object obj = gerarObject(form,request);

		this.atualizarObjeto(obj, request, form);
		
		ActionForward forward = this.processarAuxiliar(actionMapping, actionForm, request, response);
		
		if (forward != null) {
			return forward;
		}
		return actionMapping.findForward(ControladorGcomAction.FORWARD_PROCESSAR);
	}

	/**
	 * M�todo que auxiliar ao m�todo exibir 
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exibirAuxiliar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

	/**
	 * M�todo que auxiliar ao m�todo processar 
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward processarAuxiliar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

	/**
	 * M�todo que consulta o Objeto no sistema com o array de chaves necessaria
	 * 
	 * @param chavesPrimarias
	 * @return
	 * @throws Exception
	 */	
	public abstract Object consultarObjetoSistema(String[] chavesPrimarias, HttpServletRequest request, ControladorAlteracaoGcomActionForm actionForm) throws Exception;

	/**
	 * M�todo que atualiza o objeto no sistema
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public abstract void atualizarObjeto(Object obj, HttpServletRequest request, ControladorAlteracaoGcomActionForm actionForm) throws Exception;

	/**
	 * M�todo respons�vel por preencher o formulario de apresenta��o a partir do objeto selecionado  
	 * 
	 * @param obj
	 * @param actionForm
	 */
	public abstract void montarFormulario(Object obj, ControladorAlteracaoGcomActionForm actionForm) ;
	
	/**
	 * M�todo que gera o objeto para a manipulacao no sistema 
	 *  
	 * @param actionForm
	 * @return
	 */
	public abstract Object gerarObject(ControladorAlteracaoGcomActionForm actionForm, HttpServletRequest request);
	
	/**
	 * M�todo que carrega a colecao para a apresenta��o dos dados.
	 * 
	 * @param actionForm
	 */
	public abstract void carregarColecao(ControladorAlteracaoGcomActionForm actionForm);
}
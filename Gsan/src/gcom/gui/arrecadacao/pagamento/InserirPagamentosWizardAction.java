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

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pelo controle de processo de inserir pagamentos
 * [UC0262] Inserir Pagamentos
 * 
 * @author 	Pedro Alexandre
 * @created 16/02/2006
 */
public class InserirPagamentosWizardAction extends WizardAction {

    /**
     * Description of the Method
     */
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
    public ActionForward exibirInserirPagamentosAvisoBancarioAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new ExibirInserirPagamentosAvisoBancarioAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

    
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
    public ActionForward inserirPagamentosAvisoBancarioAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new InserirPagamentosAvisoBancarioAction().execute(actionMapping, actionForm,
        													httpServletRequest,
        													httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, actionForm,
                						httpServletRequest, 
                						httpServletResponse);
    }

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
    public ActionForward exibirInserirPagamentosTipoInclusaoCanetaAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new ExibirInserirPagamentosTipoInclusaoCanetaAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

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
    public ActionForward exibirInserirPagamentosTipoInclusaoManualAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new ExibirInserirPagamentosTipoInclusaoManualAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }
    
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
    public ActionForward exibirInserirPagamentosTipoInclusaoFichaCompensacaoAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new ExibirInserirPagamentosTipoInclusaoFichaCompensacaoAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

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
    public ActionForward inserirPagamentosTipoInclusaoCanetaAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new InserirPagamentosTipoInclusaoCanetaAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

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
    public ActionForward inserirPagamentosTipoInclusaoManualAction(
            ActionMapping actionMapping,
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new InserirPagamentosTipoInclusaoManualAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }
    
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
    public ActionForward inserirPagamentosTipoInclusaoFichaCompensacaoAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new InserirPagamentosTipoInclusaoFichaCompensacaoAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

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
    public ActionForward inserirPagamentosAction(ActionMapping actionMapping,
            ActionForm actionForm, 
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new InserirPagamentosAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

}

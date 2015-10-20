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
package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.tarifasocial.FiltroTarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ExibirManterTarifaSocialCartaoTipoAction extends GcomAction {
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

        //Define a��o de retorno
        ActionForward retorno = actionMapping
                .findForward("manterTarifaSocialCartaoTipo");

        //Obt�m a fachada
        Fachada fachada = Fachada.getInstancia();

        //Form din�mico para obter
        //DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        //Inicializa a cole��o
        Collection colecaoTarifaSocialCartaoTipo = null;

        //Mudar isso quando implementar a parte de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        //Cria��o do filtro de tarifa social cart�o tipo
        FiltroTarifaSocialCartaoTipo filtroTarifaSocialCartaoTipo = null;

        //Verifica se o filtro foi informado pela p�gina de filtragem de
        // logradouro
        if (httpServletRequest.getAttribute("filtroTarifaSocialCartaoTipo") != null) {
            filtroTarifaSocialCartaoTipo = (FiltroTarifaSocialCartaoTipo) httpServletRequest
                    .getAttribute("filtroTarifaSocialCartaoTipo");
        } else {
            //Caso o exibirManterTarifaSocialCartaoTipo n�o tenha passado por
            // algum esquema de filtro,
            //a quantidade de registros � verificada para avaliar a necessidade
            // de filtragem
            filtroTarifaSocialCartaoTipo = new FiltroTarifaSocialCartaoTipo();

            if (fachada.registroMaximo(TarifaSocialCartaoTipo.class) > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
                //Se o limite de registros foi atingido, a p�gina de filtragem
                // � chamada
                retorno = actionMapping
                        .findForward("filtrarTarifaSocialCartaoTipo");
                //limpa os parametros do form pesquisar
                sessao.removeAttribute("PesquisarActionForm");
            }
        }

        //A pesquisa de tarifa social cart�o tipo s� ser� feita se o forward
        // estiver direcionado
        //para a p�gina de manterTarifaSocialCartaoTipo
        if (retorno.getName().equalsIgnoreCase("manterTarifaSocialCartaoTipo")) {
                        
        	Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroTarifaSocialCartaoTipo, TarifaSocialCartaoTipo.class.getName());
			colecaoTarifaSocialCartaoTipo = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

            if (colecaoTarifaSocialCartaoTipo == null
                    || colecaoTarifaSocialCartaoTipo.isEmpty()) {
                //Nenhum cliente cadastrado
                throw new ActionServletException("atencao.naocadastrado", null,
                        "tipo de cart�o da tarifa social");
            }

            /*
             * if (logradouros.size() >
             * ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) { throw new
             * ActionServletException("atencao.pesquisa.muitosregistros"); }
             */
            sessao.setAttribute("colecaoTarifaSocialCartaoTipo",
                    colecaoTarifaSocialCartaoTipo);

        }
        return retorno;
    }

}

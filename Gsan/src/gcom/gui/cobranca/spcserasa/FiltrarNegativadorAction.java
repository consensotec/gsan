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
* Thiago Vieira
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
package gcom.gui.cobranca.spcserasa;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class FiltrarNegativadorAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

//    	 localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("exibirManterNegativador");
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega o form do cliente
        FiltrarNegativadorActionForm form = (FiltrarNegativadorActionForm) actionForm; 
        
        short codigoAgente = 0;
        Integer clienteId = null;
        Integer imovelId= null; 
        String inscricaoEstadual = null;
        short ativo = 0; 
        String checkAtualizar = "";
        
        if (form.getCodigoAgente() != null && !form.getCodigoAgente().equals("")){
        	codigoAgente = Short.parseShort(form.getCodigoAgente());
        }
        if (form.getCodigoCliente() != null && !form.getCodigoCliente().equals("")){
        	clienteId = new Integer(form.getCodigoCliente());
        }
        if (form.getCodigoImovel() != null && !form.getCodigoImovel().equals("")){
        	imovelId = new Integer(form.getCodigoImovel());
        }
        if (form.getInscricaoEstadual() != null && !form.getInscricaoEstadual().equals("")){
        	inscricaoEstadual = form.getInscricaoEstadual();
        }
        if (form.getAtivo() != null && !form.getAtivo().equals("")){
        	ativo = Short.parseShort(form.getAtivo());
        }
       	
        if (form.getCheckAtualizar() != null && !form.getCheckAtualizar().equals("")){
        	checkAtualizar = form.getCheckAtualizar();
        }
        
		FiltroNegativador filtroNegativador = new FiltroNegativador();
		boolean peloMenosUmParametroInformado = false; 
		
		if (form.getCodigoAgente() != null && !form.getCodigoAgente().equals("")){
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.CODIGO_AGENTE, codigoAgente));
			peloMenosUmParametroInformado = true;
		}
		
		if (form.getCodigoCliente() != null && !form.getCodigoCliente().equals("")){
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.NEGATIVADOR_CLIENTE, clienteId));
			peloMenosUmParametroInformado = true;
		}
		
		if (form.getCodigoImovel() != null && !form.getCodigoImovel().equals("")){
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.NEGATIVADOR_IMOVEL, imovelId));
			peloMenosUmParametroInformado = true;
		}
		
		if (form.getInscricaoEstadual() != null && !form.getInscricaoEstadual().equals("")){
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.INSCRICAO_ESTADUAL, inscricaoEstadual));
			peloMenosUmParametroInformado = true;
		}
		
		if (form.getAtivo() != null && !form.getAtivo().equals("")){
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.INDICADOR_USO, ativo));
			peloMenosUmParametroInformado = true;
		}

		if (!peloMenosUmParametroInformado){
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		
		sessao.setAttribute("filtroNegativador",filtroNegativador);
		sessao.setAttribute("checkAtualizar",checkAtualizar);
		
		return retorno;
        
    }
}
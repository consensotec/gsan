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
* Yara Taciane de Souza
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

import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir manter motivo de retorno do registro do negativador
 * @author Thiago Vieira
 * @created 07/01/2008
 */

public class ExibirManterNegativadorRetornoMotivoAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterNegativadorRetornoMotivo");
		HttpSession sessao = httpServletRequest.getSession(false);
        Collection collectionNegativadorRetornoMotivo = null;

        FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = null;
        
		// Verifica se o filtro foi informado pela p�gina de filtragem do Negativador
		if (sessao.getAttribute("filtroNegativadorRetornoMotivo") != null) {
			filtroNegativadorRetornoMotivo = (FiltroNegativadorRetornoMotivo) sessao.getAttribute("filtroNegativadorRetornoMotivo");
		}
		
		String checkAtualizar = "";
		
		if (sessao.getAttribute("checkAtualizar") != null) {
			checkAtualizar = (String) sessao.getAttribute("checkAtualizar");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterNegativadorRetornoMotivo"))) {
				
			filtroNegativadorRetornoMotivo.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
			filtroNegativadorRetornoMotivo.setCampoOrderBy(FiltroNegativadorRetornoMotivo.ID);

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());
			
			
			collectionNegativadorRetornoMotivo = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			// [FS0004] Nenhum registro encontrado				
			if (collectionNegativadorRetornoMotivo == null || collectionNegativadorRetornoMotivo.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			//carrega nome do negativador escolhido
			Iterator i = collectionNegativadorRetornoMotivo.iterator();
			NegativadorRetornoMotivo nrm = (NegativadorRetornoMotivo)i.next();
			String nomeNegativador = nrm.getNegativador().getCliente().getNome();
			
			if (collectionNegativadorRetornoMotivo.size()== 1 && checkAtualizar != null && !checkAtualizar.equals("")){
				// caso o resultado do filtro s� retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema n�o exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarNegativadorRetornoMotivo");
				
				NegativadorRetornoMotivo negativadorRetornoMotivo = (NegativadorRetornoMotivo)collectionNegativadorRetornoMotivo.iterator().next();
				sessao.setAttribute("idRegistroAtualizacao", new Integer (negativadorRetornoMotivo.getId()).toString());
			}else{
				sessao.setAttribute("collectionNegativadorRetornoMotivo", collectionNegativadorRetornoMotivo);
				sessao.setAttribute("nomeNegativador", nomeNegativador);
			}

		}
		
		return retorno;
        
    }

}
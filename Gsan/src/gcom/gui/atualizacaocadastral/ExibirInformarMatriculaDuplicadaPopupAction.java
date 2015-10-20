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
* Anderson Cabral do Nascimento
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
package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.bean.DadosImovelDuplicadoPreGsanHelper;
import gcom.atualizacaocadastral.bean.DadosImovelPreGsanHelper;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Arthur Carvalho
 * @date 12/06/2013
 */
public class ExibirInformarMatriculaDuplicadaPopupAction extends GcomAction {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirInformarMatriculaDuplicadaPopupAction");

		InformarMatriculaDuplicadaPupupActionForm form = (InformarMatriculaDuplicadaPupupActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<DadosImovelPreGsanHelper> colecaoDadosImovelPreGsanHelper = (Collection<DadosImovelPreGsanHelper>) sessao.getAttribute("imoveisNovosPreGsan");
		
		//recupera o im�vel que ta com o registro duplicado
		String idImovelAtlzCadastral = httpServletRequest.getParameter("idAtualizacaoCadastralDuplicado");
		form.setMatricula(idImovelAtlzCadastral);
		httpServletRequest.setAttribute("matricula", form.getMatricula());
		//percorre a lista de im�veis, recupera o im�vel selecionado e envia a colecao de matriculas duplicadas para a tela.
		if(colecaoDadosImovelPreGsanHelper != null && !colecaoDadosImovelPreGsanHelper.isEmpty()){

			Iterator<DadosImovelPreGsanHelper> iteratorHelper = colecaoDadosImovelPreGsanHelper.iterator();
			while ( iteratorHelper.hasNext() ) {
				
				DadosImovelPreGsanHelper helper = iteratorHelper.next();

				if ( helper.getIdImovelAtualizacaoCadastral().equals(idImovelAtlzCadastral) ) {
					Collection<DadosImovelDuplicadoPreGsanHelper> colecaoHelperImovelDuplicado = new ArrayList<DadosImovelDuplicadoPreGsanHelper>();
					
					Iterator iterator = helper.getColecaoMatriculaGsan().iterator();
					while(iterator.hasNext()) {
						
						Integer matricula = (Integer) iterator.next();
						DadosImovelDuplicadoPreGsanHelper he = new DadosImovelDuplicadoPreGsanHelper();
						he.setMatricula(String.valueOf(matricula));
						colecaoHelperImovelDuplicado.add(he);
						
					}
					
					httpServletRequest.setAttribute("colecaoImovelDuplicado", 	colecaoHelperImovelDuplicado);
					break;
				}
				
			}
		}
		
		return retorno;
	}
}

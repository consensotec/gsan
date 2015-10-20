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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.FoneTipo;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * adiciona o telefona ao solicitante
 * 
 * @author S�vio Luiz
 * @date 31/08/2006
 */
public class AdicionarSolicitanteFoneAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirAdicionarSolicitante");

		HttpSession sessao = httpServletRequest.getSession(false);

		AdicionarSolicitanteRegistroAtendimentoActionForm adicionarSolicitanteRegistroAtendimentoActionForm = (AdicionarSolicitanteRegistroAtendimentoActionForm) actionForm;

		Collection colecaoFonesSolicitante = null;
		if (sessao.getAttribute("caminhoRetornoTelaAdicionarFone") != null){
			colecaoFonesSolicitante = (Collection) sessao
			.getAttribute("colecaoFonesAbaSolicitante");
		}
		else{
			colecaoFonesSolicitante = (Collection) sessao
			.getAttribute("colecaoFonesSolicitante");
		}
		
		
		boolean primeiro = false;
		if (colecaoFonesSolicitante == null
				|| colecaoFonesSolicitante.isEmpty()) {
			colecaoFonesSolicitante = new ArrayList();
			primeiro = true;
		}

		ClienteFone clienteFone = new ClienteFone();
		// seta o id e a descri��o do fone tipo
		FoneTipo foneTipo = new FoneTipo();
		Object[] parmsFoneTipo = adicionarSolicitanteRegistroAtendimentoActionForm
				.getIdTipoTelefone().split(";");
		foneTipo.setId(new Integer((String) parmsFoneTipo[0]));
		foneTipo.setDescricao((String) parmsFoneTipo[1]);
		clienteFone.setFoneTipo(foneTipo);

		// seta o DDD
		clienteFone.setDdd(adicionarSolicitanteRegistroAtendimentoActionForm
				.getDddTelefone());

		// seta o n�mero do telefone
		clienteFone
				.setTelefone(adicionarSolicitanteRegistroAtendimentoActionForm
						.getNumeroTelefone());
		// seta o ramal do telefone
		clienteFone.setRamal(adicionarSolicitanteRegistroAtendimentoActionForm
				.getRamal());
		
		if (primeiro) {
			clienteFone.setIndicadorTelefonePadrao(new Short("1"));
		}
		else{
			clienteFone.setIndicadorTelefonePadrao(new Short("2"));
		}
		
		//UltimaAlteracao para facilitar a identificacao do objeto
		clienteFone.setUltimaAlteracao(new Date());
		
		colecaoFonesSolicitante.add(clienteFone);
		
		
		//URL de retorno
		if (sessao.getAttribute("caminhoRetornoTelaAdicionarFone") != null){
			retorno = actionMapping.findForward("exibirAdicionarSolicitanteProcessoRA");
			httpServletRequest.setAttribute("telaRetorno", sessao.getAttribute("caminhoRetornoTelaAdicionarFone"));
			sessao.setAttribute("colecaoFonesAbaSolicitante", colecaoFonesSolicitante);
		
		}
		else if (sessao.getAttribute("caminhoRetornoTelaAdicionarFonePopUp") != null){
			retorno = actionMapping.findForward("exibirAdicionarSolicitanteProcessoRA");
			httpServletRequest.setAttribute("telaRetornoPopUp", sessao.getAttribute("caminhoRetornoTelaAdicionarFonePopUp"));
			sessao.setAttribute("colecaoFonesSolicitante", colecaoFonesSolicitante);
		}
		else if (sessao.getAttribute("caminhoRetornoTelaAdicionarFoneReiterar") != null){
			retorno = actionMapping.findForward("exibirAdicionarSolicitanteProcessoRA");
			httpServletRequest.setAttribute("telaRetorno", sessao.getAttribute("caminhoRetornoTelaAdicionarFoneReiterar"));
			sessao.setAttribute("colecaoFonesSolicitante", colecaoFonesSolicitante);
		}
		else{
			sessao.setAttribute("colecaoFonesSolicitante", colecaoFonesSolicitante);
		}

		
		return retorno;

	}

}

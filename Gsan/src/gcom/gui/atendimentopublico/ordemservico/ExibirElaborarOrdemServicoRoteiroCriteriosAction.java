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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0456] Elaborar Roteiro de Programa��o de Ordem de Servi�o
 * 
 * @author Rafael Pinto 
 *
 * @date 04/09/2006
 */
public class ExibirElaborarOrdemServicoRoteiroCriteriosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirElaborarOrdemServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ElaborarOrdemServicoRoteiroCriteriosActionForm 
			elaborarOrdemServicoRoteiroCriteriosActionForm = 
				(ElaborarOrdemServicoRoteiroCriteriosActionForm) actionForm;
		
		String origemServicos = 
			elaborarOrdemServicoRoteiroCriteriosActionForm.getOrigemServicos();

		String criterioSelecao = 
			elaborarOrdemServicoRoteiroCriteriosActionForm.getCriterioSelecao();
		
		String servicoDiagnosticado = 
			elaborarOrdemServicoRoteiroCriteriosActionForm.getServicoDiagnosticado();

		String servicoAcompanhamento = 
			elaborarOrdemServicoRoteiroCriteriosActionForm.getServicoAcompanhamento();

		// Coloca com default a orige servi�o como (Solicitado)
		if(origemServicos == null || origemServicos.equals("")){
			origemServicos = "1";
			elaborarOrdemServicoRoteiroCriteriosActionForm.setOrigemServicos(origemServicos);
		}

		// Coloca com default o criterio de sele��o como (Tipo de Servi�o)
		if(criterioSelecao == null || criterioSelecao.equals("")){
			criterioSelecao = "1";
		}

		// Coloca com default o servi�o diagnosticado como (Todos)		
		if(servicoDiagnosticado == null || servicoDiagnosticado.equals("")){
			elaborarOrdemServicoRoteiroCriteriosActionForm.setServicoDiagnosticado(
				""+ConstantesSistema.NUMERO_NAO_INFORMADO);
		}

		// Coloca com default os servi�os acompanhados como (Todos)
		if(servicoAcompanhamento == null || servicoAcompanhamento.equals("")){
			elaborarOrdemServicoRoteiroCriteriosActionForm.setServicoAcompanhamento(
				""+ConstantesSistema.NUMERO_NAO_INFORMADO);
		}

		// Recebe a data do roteiro de [UC0455] - Exbir Calendario
		//elaborarOrdemServicoRoteiroCriteriosActionForm.setDataRoteiro("12/09/2006");
		
		// Seta o id da Unidade de Lotacao do Usuario
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		elaborarOrdemServicoRoteiroCriteriosActionForm.setUnidadeLotacao(
				""+usuario.getUnidadeOrganizacional().getId());

		// Monta a colecao de tipos Servicos
		this.pesquisarServicoDisponivel(
			httpServletRequest,new Integer(criterioSelecao),new Integer(origemServicos));
		
		return retorno;
	}
	
	/**
	 * Pesquisa Servicos Disponiveis a partir da origem do servico:(Solicitados,Seletivos e Ambos) 
	 * e partir do criterio:(Tipo de Servico,Tipo de Equipe,Unidade,Localidade,Setor e Distrito)
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param criterio,origemServico
	 *  
	 * @return Tipos de Servico:
	 *  	ServicoTipo
	 *  	ServicoPerfilTipo
	 *  	UnidadeOrganizacional
	 *  	Localidade
	 *  	SetorComercial
	 *  	DistritoOperacional
	 */
	private void pesquisarServicoDisponivel(HttpServletRequest httpServletRequest,
		int criterio,int origemServicos){
		
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoServicoDisponivel = 
			(Collection) sessao.getAttribute("colecaoServicoDisponivel"+criterio+origemServicos);
		
		if(colecaoServicoDisponivel == null){
			
			Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
			
			colecaoServicoDisponivel = 
				Fachada.getInstancia().pesquisarTipoServicoDisponivelPorCriterio(
					usuario.getUnidadeOrganizacional(),criterio,origemServicos);
			
			sessao.setAttribute("colecaoServicoDisponivel"+criterio+origemServicos,colecaoServicoDisponivel);
			
		}

		if (colecaoServicoDisponivel == null || colecaoServicoDisponivel.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Servi�o Dispon�vel");
		} else {
			httpServletRequest.setAttribute("colecaoTipoServico",colecaoServicoDisponivel);
		}
	}

}
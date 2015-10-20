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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Thiago Ten�rio
 * @date 07/08/2006
 */
public class ExibirPesquisarTipoServicoReferenciaAction extends GcomAction {

	/**
	 * Este caso de uso efetua pesquisa do Material
	 * 
	 * [UC0381] Inserir Material
	 * 
	 * 
	 * @author Thiago Ten�rio
	 * @date 07/08/2006
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

		// Mudar isso quando tiver esquema de seguran�a
		// HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("pesquisarTipoServicoReferencia");

		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarTipoServicoReferenciaActionForm pesquisarTipoServicoReferenciaActionForm = (PesquisarTipoServicoReferenciaActionForm) actionForm;

		if (httpServletRequest.getParameter("enter") == null) {

			pesquisarTipoServicoReferenciaActionForm.setDescricao("");
			pesquisarTipoServicoReferenciaActionForm.setDescricaoAbreviada("");
			pesquisarTipoServicoReferenciaActionForm
					.setDescricaoTipoServico("");
			pesquisarTipoServicoReferenciaActionForm.setIdTipoServico("");
			pesquisarTipoServicoReferenciaActionForm
					.setIndicadorExistenciaOsReferencia("");

		} else {

			String idTipoServico = pesquisarTipoServicoReferenciaActionForm
					.getIdTipoServico();

			if (idTipoServico != null && !idTipoServico.trim().equals("")) {

				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
				filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.ID, idTipoServico));

				Collection colecaoTipoServico = fachada.pesquisar(
						filtroServicoTipo, ServicoTipo.class.getName());

				if (colecaoTipoServico != null && !colecaoTipoServico.isEmpty()) {

					ServicoTipo servicoTipo = (ServicoTipo) colecaoTipoServico
							.iterator().next();
					pesquisarTipoServicoReferenciaActionForm
							.setDescricaoTipoServico(servicoTipo.getDescricao());

				} else {
					pesquisarTipoServicoReferenciaActionForm
							.setIdTipoServico("");
					httpServletRequest.setAttribute("nomeCampo",
							"idTipoServico");
					httpServletRequest.setAttribute(
							"idTipoServicoNaoEncontrado", "exception");
					pesquisarTipoServicoReferenciaActionForm
							.setDescricaoTipoServico("Tipo de Servico Inexistente");
				}
			}
		}
		
		if(httpServletRequest.getParameter("tipoConsulta") != null){
			String tipoConsulta = (String) httpServletRequest.getParameter("tipoConsulta");
			
			if(tipoConsulta.equalsIgnoreCase("tipoServico")){
			pesquisarTipoServicoReferenciaActionForm.
							setIdTipoServico(httpServletRequest.getParameter("idCampoEnviarDados"));
			pesquisarTipoServicoReferenciaActionForm.
							setDescricaoTipoServico(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
			
		}
		
//		 envia uma flag que ser� verificado no material_resultado_pesquisa.jsp
		// para saber se ir� usar o enviar dados ou o enviar dados parametros

		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaServicoTipoReferencia") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaServicoTipoReferencia",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaServicoTipoReferencia"));
		}else if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaServicoTipoReferencia") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaServicoTipoReferencia",
							httpServletRequest.getParameter("caminhoRetornoTelaPesquisaServicoTipoReferencia"));
		}	

		return retorno;
	}

}

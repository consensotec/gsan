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

import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0388] Pesquisar Tipo Perfil Servi�o
 * 
 * @author Ana Maria
 * @date 01/08/2006
 * 
 */
public class ExibirPesquisarTipoPerfilServicoAction extends GcomAction {
	/**
	 * [UC0388] Esse caso de uso efetua pesquisa do perfil de servi�o
	 * 
	 * @author Ana Maria
	 * @date 01/08/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarTipoPerfilServicoActionForm pesquisarTipoPerfilServicoActionForm = (PesquisarTipoPerfilServicoActionForm) actionForm;
		
		ActionForward retorno = actionMapping.findForward("tipoPerfilServicoPesquisar");
		HttpSession sessao = httpServletRequest.getSession(false);
		
        // verifica se o usu�rio solicitou uma consulta no popup de Equipamentos Especiais
		String idEquiapentoEspecial = httpServletRequest.getParameter("id");
		
		String descricaoEquipamentoEspecial = httpServletRequest.getParameter("descricao");
		
		// Seta no form os valores da pesquisa feita no popup de equipamento especial
		if (idEquiapentoEspecial != null && !idEquiapentoEspecial.trim().equals("")
				&& descricaoEquipamentoEspecial != null && !descricaoEquipamentoEspecial.trim().equals("")) {

			pesquisarTipoPerfilServicoActionForm.setEquipamentoEspecial(idEquiapentoEspecial.trim());
			pesquisarTipoPerfilServicoActionForm.setDescricaoEquipamentoEspecial(descricaoEquipamentoEspecial.trim());
		}
		
		// Seta o tipo de pesquisa 
		pesquisarTipoPerfilServicoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		pesquisarTipoPerfilServicoActionForm.setTipoPesquisaAbreviada(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		
		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String EquipamentoEspecial = pesquisarTipoPerfilServicoActionForm.getEquipamentoEspecial();
		
		if (EquipamentoEspecial != null && !EquipamentoEspecial.trim().equals("")) {

			// Faz a consulta do Equipamento Especial
			pesquisarEquipamentoEspecial(pesquisarTipoPerfilServicoActionForm, httpServletRequest);
		}	
        
        if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTipoPerfil") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaTipoPerfil",
							httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTipoPerfil"));
		}

        if (httpServletRequest.getParameter("limparCampos") != null) {
           pesquisarTipoPerfilServicoActionForm.reset();	
        }
		return retorno;
	}
	
	private void pesquisarEquipamentoEspecial(PesquisarTipoPerfilServicoActionForm pesquisarTipoPerfilServicoActionForm, HttpServletRequest httpServletRequest) {
		
		// Filtro para obter o Equipamento Especial do id informado
		FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();

		filtroTabelaAuxiliarAbreviada.adicionarParametro(new ParametroSimples(
						FiltroTabelaAuxiliarAbreviada.ID, new Integer(
								pesquisarTipoPerfilServicoActionForm.getEquipamentoEspecial()),
						        ParametroSimples.CONECTOR_AND));
		filtroTabelaAuxiliarAbreviada.adicionarParametro(new ParametroSimples(
						FiltroTabelaAuxiliarAbreviada.INDICADORUSO,
						        ConstantesSistema.INDICADOR_USO_ATIVO));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoEquipamentosEspeciais = Fachada.getInstancia()
				.pesquisar(filtroTabelaAuxiliarAbreviada,EquipamentosEspeciais.class.getName());
		
		
		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoEquipamentosEspeciais != null && !colecaoEquipamentosEspeciais.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			EquipamentosEspeciais equipamentosEspeciais = (EquipamentosEspeciais) Util
					.retonarObjetoDeColecao(colecaoEquipamentosEspeciais);

			pesquisarTipoPerfilServicoActionForm.setEquipamentoEspecial(equipamentosEspeciais.getId()
							.toString());
			pesquisarTipoPerfilServicoActionForm.setDescricaoEquipamentoEspecial(equipamentosEspeciais
							.getDescricao());
			httpServletRequest.setAttribute("corEquipamentoEspecial", "valor");
		} else {
			// Exibe mensagem de c�digo inexiste e limpa o campo de c�digo
			httpServletRequest.setAttribute("corEquipamentoEspecial","exception");		
			pesquisarTipoPerfilServicoActionForm.setEquipamentoEspecial("");
			pesquisarTipoPerfilServicoActionForm.setDescricaoEquipamentoEspecial("Equipamento Especial inexistente");

		}

	}
}

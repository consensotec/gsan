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
package gcom.gui.micromedicao.leitura;

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeLeitura;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pr�-processamento da p�gina de pesquisa de cliente
 * 
 * @author Thiago Ten�rio
 * @created 25 de Abril de 2005
 */
public class ExibirFiltrarAnormalidadeLeituraAction extends GcomAction {
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

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		FiltrarAnormalidadeLeituraActionForm filtrarAnormalidadeLeituraActionForm = (FiltrarAnormalidadeLeituraActionForm) actionForm;
		//	C�digo para checar ou n�o o ATUALIZAR
		
		/*if (httpServletRequest.getParameter("menu") != null) {
			filtrarAnormalidadeLeituraActionForm.setDescricao("");
			filtrarAnormalidadeLeituraActionForm
					.setIndicadorRelativoHidrometro("");
			filtrarAnormalidadeLeituraActionForm
					.setIndicadorImovelSemHidrometro("");
			filtrarAnormalidadeLeituraActionForm.setUsoRestritoSistema("");
			filtrarAnormalidadeLeituraActionForm.setPerdaTarifaSocial("");
			filtrarAnormalidadeLeituraActionForm.setOsAutomatico("");
			filtrarAnormalidadeLeituraActionForm.setTipoServico("");
			filtrarAnormalidadeLeituraActionForm
					.setConsumoLeituraNaoInformado("");
			filtrarAnormalidadeLeituraActionForm.setConsumoLeituraInformado("");
			filtrarAnormalidadeLeituraActionForm.setLeituraLeituraInformado("");
			filtrarAnormalidadeLeituraActionForm
					.setLeituraLeituraNaoturaInformado("");

		}
*/
		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarAnormalidadeLeituraActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}		

		if (filtrarAnormalidadeLeituraActionForm.getTipoServico() == null
				|| filtrarAnormalidadeLeituraActionForm.getTipoServico().equals("")) {
			Collection colecaoPesquisa = null;

			FiltroTipoServico filtroTipoServico = new FiltroTipoServico();

			filtroTipoServico.setCampoOrderBy(FiltroTipoServico.DESCRICAO);

			filtroTipoServico.adicionarParametro(new ParametroSimples(
					FiltroTipoServico.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna banco
			colecaoPesquisa = fachada.pesquisar(filtroTipoServico, ServicoTipo.class
					.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Nenhum registro na tabela localidade_porte foi encontrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"Tipo de Servico");
			} else {
				sessao.setAttribute("colecaoTipoServico", colecaoPesquisa);
			}

		} 

		FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
		filtroLeituraAnormalidadeConsumo
				.setCampoOrderBy(FiltroLeituraAnormalidadeConsumo.ID);

		Collection colecaoLeituraAnormalidadeConsumo = fachada.pesquisar(
				filtroLeituraAnormalidadeConsumo,
				LeituraAnormalidadeConsumo.class.getName());
		sessao.setAttribute("colecaoLeituraAnormalidadeConsumo",
				colecaoLeituraAnormalidadeConsumo);
		
		//cole��o anormalidade leitura

		FiltroLeituraAnormalidadeLeitura filtroLeituraAnormalidadeLeitura = new FiltroLeituraAnormalidadeLeitura();
		filtroLeituraAnormalidadeLeitura
				.setCampoOrderBy(FiltroLeituraAnormalidadeLeitura.ID);

		Collection colecaoLeituraAnormalidadeLeitura = fachada.pesquisar(
				filtroLeituraAnormalidadeLeitura,
				LeituraAnormalidadeLeitura.class.getName());
		sessao.setAttribute("colecaoLeituraAnormalidadeLeitura",
				colecaoLeituraAnormalidadeLeitura);

		//	Se voltou da tela de Atualizar Sistema de Esgoto
		if (sessao.getAttribute("voltar") != null && sessao.getAttribute("voltar").equals("filtrar")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			if(sessao.getAttribute("tipoPesquisa") != null){
				filtrarAnormalidadeLeituraActionForm.setTipoPesquisa(sessao.getAttribute("tipoPesquisa").toString());
			}
		}
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarAnormalidadeLeitura");

		return retorno;

	}
}

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

import gcom.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;

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
 * @date 05/08/2006
 */
public class FiltrarPrioridadeTipoServicoAction extends GcomAction {

	/**
	 * Este caso de uso permite Filtrar uma Prioridade do Tipo de Servi�o
	 * 
	 * [UC00540] Filtrar Prioridade do Tipo de Servi�o
	 * 
	 * 
	 *
	 * @author Thiago Ten�rio
	 * @date 18/01/2007
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

		ActionForward retorno = actionMapping
				.findForward("exibirManterPrioridadeTipoServico");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarPrioridadeTipoServicoActionForm filtrarPrioridadeTipoServicoActionForm = (FiltrarPrioridadeTipoServicoActionForm) actionForm;

		FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();

		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String codigo = filtrarPrioridadeTipoServicoActionForm.getCodigo();

		String descricao = filtrarPrioridadeTipoServicoActionForm
				.getDescricao();

		String abreviatura = filtrarPrioridadeTipoServicoActionForm
				.getAbreviatura();

		String qtdHorasInicio = filtrarPrioridadeTipoServicoActionForm
				.getQtdHorasInicio();

		String qtdHorasFim = filtrarPrioridadeTipoServicoActionForm
				.getQtdHorasFim();
		
		String tipoPesquisa = filtrarPrioridadeTipoServicoActionForm
				.getTipoPesquisa();				
		 

		// Verifica se o campo C�digo da Prioridade foi Informado

		if (codigo != null && !codigo.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroServicoTipoPrioridade.adicionarParametro(new ComparacaoTexto(
					FiltroServicoTipoPrioridade.ID, codigo));

		}

		// Descri��o do Sistema de Esgoto
		if (descricao != null && !descricao.equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				
				filtroServicoTipoPrioridade.adicionarParametro(new ComparacaoTextoCompleto(FiltroSistemaEsgoto.DESCRICAO, 
						descricao));
			} else {
				
				filtroServicoTipoPrioridade.adicionarParametro(new ComparacaoTexto(FiltroSistemaEsgoto.DESCRICAO,
						descricao));
			}
		}
		
		// Verifica se o campo descri��o abreviatura foi informado

		if (abreviatura != null && !abreviatura.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroServicoTipoPrioridade.adicionarParametro(new ComparacaoTexto(
					FiltroServicoTipoPrioridade.DESCRICAO_ABREVIADA,
					abreviatura));

		}

		// Verifica se o campo Horas para execu��o do servico foi informado

		if (qtdHorasInicio != null
				&& !qtdHorasInicio.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroServicoTipoPrioridade.adicionarParametro(new ComparacaoTexto(
					FiltroServicoTipoPrioridade.PRAZO_EXECUCAO_INICIO,
					qtdHorasInicio));

		}

		// Verifica se o campo Horas para Finaliza��o do servico foi informado

		if (qtdHorasFim != null && !qtdHorasFim.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroServicoTipoPrioridade
					.adicionarParametro(new ComparacaoTexto(
							FiltroServicoTipoPrioridade.PRAZO_EXECUCAO_FIM,
							qtdHorasFim));

		}

		// Erro caso o usu�rio mandou Pesquisar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		if (filtrarPrioridadeTipoServicoActionForm.getIndicadorAtualizar() != null	
				&& filtrarPrioridadeTipoServicoActionForm.getIndicadorAtualizar().equalsIgnoreCase("1")) {
				
			httpServletRequest.setAttribute("atualizar",filtrarPrioridadeTipoServicoActionForm.getIndicadorAtualizar());
	
		}

//		filtroServicoTipoPrioridade
//				.adicionarCaminhoParaCarregamentoEntidade("servicoTipoPrioridade");

		sessao.setAttribute("filtroPrioridadeTipoServico",
				filtroServicoTipoPrioridade);

		return retorno;
	}

}
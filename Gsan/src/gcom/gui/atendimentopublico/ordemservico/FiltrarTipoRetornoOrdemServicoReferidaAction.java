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

import gcom.atendimentopublico.ordemservico.FiltroOSReferidaRetornoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

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
public class FiltrarTipoRetornoOrdemServicoReferidaAction extends GcomAction {

	/**
	 * Este caso de uso permite Pesquisar um Tipo de Servic�o
	 * 
	 * [UC0437] Pesquisar Tipo de Servi�o de Refer�ncia
	 * 
	 * 
	 * @author Thiago Ten�rio
	 * @date 31/07/2006
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
				.findForward("exibirManterTipoRetornoOrdemServicoReferida");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarTipoRetornoOrdemServicoReferidaActionForm filtrarTipoRetornoOrdemServicoReferidaActionForm = (FiltrarTipoRetornoOrdemServicoReferidaActionForm) actionForm;

		FiltroOSReferidaRetornoTipo filtroOSReferidaRetornoTipo = new FiltroOSReferidaRetornoTipo();

		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String codigoTipoRetorno = filtrarTipoRetornoOrdemServicoReferidaActionForm
				.getCodigoTipoRetorno();

		String descricao = filtrarTipoRetornoOrdemServicoReferidaActionForm
				.getDescricao();
		
		String abreviatura = filtrarTipoRetornoOrdemServicoReferidaActionForm
		.getAbreviatura();
		
		String servicoTipoReferencia = filtrarTipoRetornoOrdemServicoReferidaActionForm
				.getServicoTipoReferencia();
		
		String deferimento = filtrarTipoRetornoOrdemServicoReferidaActionForm
		.getDeferimento();
		
		String trocaServico = filtrarTipoRetornoOrdemServicoReferidaActionForm
		.getTrocaServico();
		
		String situacao = filtrarTipoRetornoOrdemServicoReferidaActionForm
		.getSituacao();
		
		String atendimentoMotivoEncerramento = filtrarTipoRetornoOrdemServicoReferidaActionForm
		.getAtendimentoMotivoEncerramento();
		
		String indicadorUso = filtrarTipoRetornoOrdemServicoReferidaActionForm
		.getIndicadorUso();

		
		// Verifica se o campo C�digo do Tipo de Retorno

		if (codigoTipoRetorno != null && !codigoTipoRetorno.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroOSReferidaRetornoTipo.adicionarParametro(new ComparacaoTexto(
					FiltroOSReferidaRetornoTipo.ID, codigoTipoRetorno));

		}

		// Verifica se o campo Descri��o foi informado

		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroOSReferidaRetornoTipo.adicionarParametro(new ComparacaoTexto(
					FiltroOSReferidaRetornoTipo.DESCRICAO, descricao));

		}

		// Verifica se o campo descri��o abreviatura foi informado

		if (abreviatura != null
				&& !abreviatura.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroOSReferidaRetornoTipo.adicionarParametro(new ComparacaoTexto(
					FiltroOSReferidaRetornoTipo.DESCRICAO_ABREVIADA,
					abreviatura));

		}

		// Verifica se o campo Refer�ncia do Tipo de Servi�o foi informado

		if (servicoTipoReferencia != null
				&& !servicoTipoReferencia.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroOSReferidaRetornoTipo.adicionarParametro(new ParametroSimples(
					FiltroOSReferidaRetornoTipo.ID_SERVICO_TIPO_REFERENCIA,
					servicoTipoReferencia));

		}


		// Verifica se o campo Indicador de Falta D'�gua foi informado

		if (deferimento != null
				&& !deferimento.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroOSReferidaRetornoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroOSReferidaRetornoTipo.INDICADOR_DEFERIMENTO,
							deferimento));

		}
		
		// Verifica se o campo Indicador de Troca de Servi�o foi informado

		if (trocaServico != null
				&& !trocaServico.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroOSReferidaRetornoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroOSReferidaRetornoTipo.INDICADOR_TROCA_SERVICO,
							trocaServico));

		}
		
		// Verifica se o campo C�digo da Situa��o foi informado

		if (situacao != null
				&& !situacao.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroOSReferidaRetornoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroOSReferidaRetornoTipo.ID,
							situacao));

		}
		
		// Verifica se o campo Motivo de Encerramento do Atendimento foi informado

		if (atendimentoMotivoEncerramento != null
				&& !atendimentoMotivoEncerramento.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroOSReferidaRetornoTipo.adicionarParametro(new ParametroSimples(
					FiltroOSReferidaRetornoTipo.ATENDIMENTO_MOTIVO_ENCERRAMENTO_ID,
					atendimentoMotivoEncerramento));

		}
		
		// Verifica se o campo Indicador de Uso foi informado

		if (indicadorUso != null
				&& !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroOSReferidaRetornoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroOSReferidaRetornoTipo.INDICADOR_USO,
							indicadorUso));

		}

		// Erro caso o usu�rio mandou Pesquisar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		// Verifica se o checkbox Atualizar est� marcado e em caso afirmativo
		// manda pela sess�o uma vari�vel para o
		// ExibirManterEquipeAction e nele verificar se ir� para o
		// atualizar ou para o manter, caso o checkbox esteja desmarcado remove
		// da sess�o
		String indicadorAtualizar = httpServletRequest
				.getParameter("atualizar");

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("atualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("atualizar");
		}

		filtroOSReferidaRetornoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia");

		sessao.setAttribute("filtroTipoRetornoOrdemServicoReferida", filtroOSReferidaRetornoTipo);
		


		return retorno;	}

}
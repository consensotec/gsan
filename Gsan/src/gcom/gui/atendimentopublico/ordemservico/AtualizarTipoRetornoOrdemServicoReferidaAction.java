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

import java.util.Collection;

import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarTipoRetornoOrdemServicoReferidaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarTipoRetornoOrdemServicoReferidaActionForm atualizarTipoRetornoOrdemServicoReferidaActionForm = (AtualizarTipoRetornoOrdemServicoReferidaActionForm) actionForm;

		OsReferidaRetornoTipo osReferidaRetornoTipo = (OsReferidaRetornoTipo) sessao
				.getAttribute("osReferidaRetornoTipoAtualizar");

		osReferidaRetornoTipo.setId(new Integer(
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getCodigoTipoRetorno()));
		osReferidaRetornoTipo
				.setDescricao(atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getDescricao());
		osReferidaRetornoTipo
				.setDescricaoAbreviada(atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getAbreviatura());

		ServicoTipoReferencia servicoTipoReferencia = null;

		if (atualizarTipoRetornoOrdemServicoReferidaActionForm
				.getServicoTipoReferencia() != null
				&& !atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getServicoTipoReferencia().equals("")
				&& !atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getServicoTipoReferencia().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			servicoTipoReferencia = new ServicoTipoReferencia();
			servicoTipoReferencia.setId(new Integer(
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.getServicoTipoReferencia()));

		}

		osReferidaRetornoTipo.setServicoTipoReferencia(servicoTipoReferencia);
		osReferidaRetornoTipo.setIndicadorDeferimento(new Short(
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getDeferimento()));
		osReferidaRetornoTipo.setIndicadorTrocaServico(new Short(
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getTrocaServico()));
		if (atualizarTipoRetornoOrdemServicoReferidaActionForm.getSituacao() != null
				&& !atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getSituacao().equals("")) {
			osReferidaRetornoTipo.setSituacaoOsReferencia(new Short(
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.getSituacao()));
		} else {
			osReferidaRetornoTipo.setSituacaoOsReferencia(null);
		}

		osReferidaRetornoTipo.setIndicadorUso(new Short(
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getIndicadorUso()));

		//AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = null;

		if (atualizarTipoRetornoOrdemServicoReferidaActionForm
				.getAtendimentoMotivoEncerramento() != null) {

			Integer idAtendimentoMotivoEncerramento = new Integer(
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.getAtendimentoMotivoEncerramento());

			if (idAtendimentoMotivoEncerramento
					.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				osReferidaRetornoTipo.setAtendimentoMotivoEncerramento(null);
			} else {
				FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
				filtroAtendimentoMotivoEncerramento
						.adicionarParametro(new ParametroSimples(
								FiltroAtendimentoMotivoEncerramento.ID,
								atualizarTipoRetornoOrdemServicoReferidaActionForm
										.getAtendimentoMotivoEncerramento()
										.toString()));
				Collection colecaoAtendimentoMotivoEncerramento = (Collection) fachada
						.pesquisar(filtroAtendimentoMotivoEncerramento,
								AtendimentoMotivoEncerramento.class.getName());

				// setando
				osReferidaRetornoTipo
						.setAtendimentoMotivoEncerramento((AtendimentoMotivoEncerramento) colecaoAtendimentoMotivoEncerramento
								.iterator().next());
			}
		}

		fachada.atualizarTipoRetornoOrdemServicoReferida(osReferidaRetornoTipo);

		montarPaginaSucesso(httpServletRequest, "Tipo Retorno da OS_Referida "
				+ osReferidaRetornoTipo.getId().toString()
				+ " atualizado com sucesso.",
				"Realizar outra Manuten��o de Tipo Retorno da OS_Referida",
				"exibirFiltrarTipoRetornoOrdemServicoReferidaAction.do?menu=sim");
		return retorno;
	}
}

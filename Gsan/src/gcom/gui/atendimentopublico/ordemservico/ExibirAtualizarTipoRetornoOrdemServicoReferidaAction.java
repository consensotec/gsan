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

import gcom.atendimentopublico.ordemservico.FiltroOSReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * Descri��o da classe
 * 
 * @author Thiago Ten�rio
 * @date 30/10/2006
 */
public class ExibirAtualizarTipoRetornoOrdemServicoReferidaAction extends
		GcomAction {
	/**
	 * [UC0393] Atualizar Valor de Cobran�a do Servi�o
	 * 
	 * Este caso de uso permite alterar um valor de cobran�a de servi�o
	 * 
	 * @author Thiago Ten�rio
	 * @date 31/10/2006
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarTipoRetornoOrdemServicoReferida");

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarTipoRetornoOrdemServicoReferidaActionForm atualizarTipoRetornoOrdemServicoReferidaActionForm = (AtualizarTipoRetornoOrdemServicoReferidaActionForm) actionForm;

		// if (httpServletRequest.getParameter("menu") != null) {
		// atualizarTipoRetornoOrdemServicoReferidaActionForm
		// .setTrocaServico("");
		// }
		Fachada fachada = Fachada.getInstancia();

		this.getAtendimentoMotivoEncerramentoCollection(sessao);

		this.getServicoTipoReferenciaCollection(sessao, fachada);

		String id = null;

		String idOsReferidaRetornoTipo = null;

		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null
				&& !httpServletRequest.getParameter("idRegistroAtualizacao")
						.equals("")) {

			sessao.removeAttribute("objetoOsReferidaRetornoTipo");
			sessao.removeAttribute("colecaoOsReferidaRetornoTipoTela");

		}
		
//		 -------------- bt DESFAZER ---------------

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			sessao.removeAttribute("colecaoOsReferidaRetornoTipoTela");

			String osReferidaRetornoTipoID = null;

			if (sessao.getAttribute("idRegistroAtualizacao") != null
					&& !sessao.getAttribute("idRegistroAtualizacao").equals("")) {
				osReferidaRetornoTipoID = (String) sessao
						.getAttribute("idRegistroAtualizacao");
			}

			if (osReferidaRetornoTipoID.equalsIgnoreCase("")) {
				osReferidaRetornoTipoID = null;
			}

			if ((osReferidaRetornoTipoID == null) && (id == null)) {

				OsReferidaRetornoTipo osReferidaRetornoTipo = (OsReferidaRetornoTipo) sessao
						.getAttribute("objetoOsReferidaRetornoTipo");

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setCodigoTipoRetorno(osReferidaRetornoTipo.getId()
								.toString());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setDescricao(osReferidaRetornoTipo.getDescricao());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setAbreviatura(osReferidaRetornoTipo
								.getDescricaoAbreviada());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setServicoTipoReferencia(osReferidaRetornoTipo
								.getServicoTipoReferencia().getId().toString());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setDeferimento(""
								+ osReferidaRetornoTipo
										.getIndicadorDeferimento());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setTrocaServico(""
								+ osReferidaRetornoTipo
										.getIndicadorTrocaServico());

				if (osReferidaRetornoTipo.getSituacaoOsReferencia() != null) {
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.setSituacao(""
									+ osReferidaRetornoTipo
											.getSituacaoOsReferencia());
				} else {
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.setSituacao("");
				}

				if (osReferidaRetornoTipo
						.getAtendimentoMotivoEncerramento() != null) {
				
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setAtendimentoMotivoEncerramento(osReferidaRetornoTipo
								.getAtendimentoMotivoEncerramento().getId()
								.toString());
				
				} else {
					atualizarTipoRetornoOrdemServicoReferidaActionForm
					.setAtendimentoMotivoEncerramento("");
				}
				
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setIndicadorUso(""
								+ osReferidaRetornoTipo.getIndicadorUso());

				sessao.setAttribute("osReferidaRetornoTipoAtualizar",
						osReferidaRetornoTipo);
				sessao.removeAttribute("osReferidaRetornoTipo");
			}

			if ((idOsReferidaRetornoTipo == null) && (id != null)) {

				idOsReferidaRetornoTipo = id;
			}

			if (idOsReferidaRetornoTipo != null) {

				FiltroOSReferidaRetornoTipo filtroOSReferidaRetornoTipo = new FiltroOSReferidaRetornoTipo();
				filtroOSReferidaRetornoTipo
				.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia");

				filtroOSReferidaRetornoTipo
						.adicionarCaminhoParaCarregamentoEntidade("situacaoOsReferencia");

				filtroOSReferidaRetornoTipo
						.adicionarCaminhoParaCarregamentoEntidade("atendimentoMotivoEncerramento");

				filtroOSReferidaRetornoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroOSReferidaRetornoTipo.ID,
								idOsReferidaRetornoTipo));

				Collection<OsReferidaRetornoTipo> colecaoOsReferidaRetornoTipo = fachada
						.pesquisar(filtroOSReferidaRetornoTipo,
								OsReferidaRetornoTipo.class.getName());

				if (colecaoOsReferidaRetornoTipo == null
						|| colecaoOsReferidaRetornoTipo.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				httpServletRequest.setAttribute("colecaoOsReferidaRetornoTipo",
						colecaoOsReferidaRetornoTipo);

				OsReferidaRetornoTipo osReferidaRetornoTipo = (OsReferidaRetornoTipo) colecaoOsReferidaRetornoTipo
						.iterator().next();

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setCodigoTipoRetorno(osReferidaRetornoTipo.getId()
								.toString());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setDescricao(osReferidaRetornoTipo.getDescricao());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setAbreviatura(osReferidaRetornoTipo
								.getDescricaoAbreviada());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setServicoTipoReferencia(osReferidaRetornoTipo
								.getServicoTipoReferencia().getId().toString());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setDeferimento(""
								+ osReferidaRetornoTipo
										.getIndicadorDeferimento());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setTrocaServico(""
								+ osReferidaRetornoTipo
										.getIndicadorTrocaServico());

				if (osReferidaRetornoTipo.getSituacaoOsReferencia() != null) {
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.setSituacao(""
									+ osReferidaRetornoTipo
											.getSituacaoOsReferencia());
				} else {
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.setSituacao("");
				}

				if (osReferidaRetornoTipo
						.getAtendimentoMotivoEncerramento() != null) {
				
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setAtendimentoMotivoEncerramento(osReferidaRetornoTipo
								.getAtendimentoMotivoEncerramento().getId()
								.toString());
				
				} else {
					atualizarTipoRetornoOrdemServicoReferidaActionForm
					.setAtendimentoMotivoEncerramento("");
				}

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setIndicadorUso(""
								+ osReferidaRetornoTipo.getIndicadorUso());

				httpServletRequest.setAttribute("idOsReferidaRetornoTipo",
						idOsReferidaRetornoTipo);
				sessao.setAttribute("osReferidaRetornoTipoAtualizar",
						osReferidaRetornoTipo);

			}
			
			httpServletRequest.setAttribute("colecaoOsReferidaRetornoTipoTela",
					sessao.getAttribute("colecaoOsReferidaRetornoTipoValorTela"));

			return retorno;
			
		}
		// -------------- bt DESFAZER ---------------

		// Verifica se veio do filtrar ou do manter

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		// Verifica se o servicoCobrancaValor j� est� na sess�o, em caso
		// afirmativo
		// significa que o usu�rio j� entrou na tela e apenas selecionou algum
		// item que deu um reload na tela e em caso negativo significa que ele
		// est� entrando pela primeira vez

		if (sessao.getAttribute("colecaoOsReferidaRetornoTipoTela") == null) {

			if (sessao.getAttribute("objetoOsReferidaRetornoTipo") != null) {

				OsReferidaRetornoTipo osReferidaRetornoTipo = (OsReferidaRetornoTipo) sessao
						.getAttribute("objetoOsReferidaRetornoTipo");

				sessao.setAttribute("idOsReferidaRetornoTipo",
						osReferidaRetornoTipo.getId().toString());

				sessao.setAttribute("osReferidaRetornoTipo",
						osReferidaRetornoTipo);

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setCodigoTipoRetorno(osReferidaRetornoTipo.getId()
								.toString());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setDescricao(osReferidaRetornoTipo.getDescricao());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setAbreviatura(osReferidaRetornoTipo
								.getDescricaoAbreviada());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setServicoTipoReferencia(osReferidaRetornoTipo
								.getServicoTipoReferencia().getId().toString());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setDeferimento(""
								+ osReferidaRetornoTipo
										.getIndicadorDeferimento());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setTrocaServico(""
								+ osReferidaRetornoTipo
										.getIndicadorTrocaServico());

				if (osReferidaRetornoTipo.getSituacaoOsReferencia() != null) {
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.setSituacao(""
									+ osReferidaRetornoTipo
											.getSituacaoOsReferencia());
				} else {
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.setSituacao("");
				}

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setAtendimentoMotivoEncerramento(osReferidaRetornoTipo
								.getAtendimentoMotivoEncerramento().getId()
								.toString());
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setIndicadorUso(""
								+ osReferidaRetornoTipo.getIndicadorUso());

				id = osReferidaRetornoTipo.getId().toString();

				sessao.setAttribute("osReferidaRetornoTipoAtualizar",
						osReferidaRetornoTipo);
				sessao.removeAttribute("objetoOsReferidaRetornoTipo");

			} else {

				OsReferidaRetornoTipo osReferidaRetornoTipo = null;

				idOsReferidaRetornoTipo = null;

				if (httpServletRequest.getParameter("idRegistroAtualizacao") == null
						|| httpServletRequest.getParameter(
								"idRegistroAtualizacao").equals("")) {
					osReferidaRetornoTipo = (OsReferidaRetornoTipo) sessao
							.getAttribute("osReferidaRetornoTipo");
					idOsReferidaRetornoTipo = osReferidaRetornoTipo.getId().toString(); 
					
				} else {
					idOsReferidaRetornoTipo = (String) httpServletRequest
							.getParameter("idRegistroAtualizacao");
					sessao.setAttribute("idRegistroAtualizacao",
							idOsReferidaRetornoTipo);
				}

				if (idOsReferidaRetornoTipo != null) {

					id = idOsReferidaRetornoTipo;

					FiltroOSReferidaRetornoTipo filtroOSReferidaRetornoTipo = new FiltroOSReferidaRetornoTipo();
					filtroOSReferidaRetornoTipo
							.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia");

//					filtroOSReferidaRetornoTipo
//							.adicionarCaminhoParaCarregamentoEntidade("situacaoOsReferencia");

					filtroOSReferidaRetornoTipo
							.adicionarCaminhoParaCarregamentoEntidade("atendimentoMotivoEncerramento");

					filtroOSReferidaRetornoTipo
							.adicionarParametro(new ParametroSimples(
									FiltroOSReferidaRetornoTipo.ID,
									idOsReferidaRetornoTipo));

					Collection<OsReferidaRetornoTipo> colecaoOsReferidaRetornoTipo = fachada
							.pesquisar(filtroOSReferidaRetornoTipo,
									OsReferidaRetornoTipo.class.getName());

					if (colecaoOsReferidaRetornoTipo == null
							|| colecaoOsReferidaRetornoTipo.isEmpty()) {
						throw new ActionServletException(
								"atencao.atualizacao.timestamp");
					}

					httpServletRequest.setAttribute(
							"colecaoOsReferidaRetornoTipo",
							colecaoOsReferidaRetornoTipo);

					osReferidaRetornoTipo = (OsReferidaRetornoTipo) colecaoOsReferidaRetornoTipo
							.iterator().next();

				}

				if (idOsReferidaRetornoTipo == null) {
					if (sessao.getAttribute("idRegistroAtualizacao") != null) {
						idOsReferidaRetornoTipo = (String) sessao
								.getAttribute("idRegistroAtualizacao");
					} else {
						osReferidaRetornoTipo = (OsReferidaRetornoTipo) sessao
								.getAttribute("osReferidaRetornoTipo");
						idOsReferidaRetornoTipo = osReferidaRetornoTipo.getId()
								.toString();
					}
				}

				FiltroOSReferidaRetornoTipo filtroOSReferidaRetornoTipo = new FiltroOSReferidaRetornoTipo();

				filtroOSReferidaRetornoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroOSReferidaRetornoTipo.ID,
								idOsReferidaRetornoTipo));

				filtroOSReferidaRetornoTipo
						.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia");

				filtroOSReferidaRetornoTipo
						.adicionarCaminhoParaCarregamentoEntidade("atendimentoMotivoEncerramento");

				Collection colecaoOsReferidaRetornoTipo = (Collection) fachada
						.pesquisar(filtroOSReferidaRetornoTipo,
								OsReferidaRetornoTipo.class.getName());

				osReferidaRetornoTipo = (OsReferidaRetornoTipo) colecaoOsReferidaRetornoTipo
						.iterator().next();

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setCodigoTipoRetorno(osReferidaRetornoTipo.getId()
								.toString());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setDescricao(osReferidaRetornoTipo.getDescricao());

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setAbreviatura(osReferidaRetornoTipo
								.getDescricaoAbreviada());

				if (osReferidaRetornoTipo.getServicoTipoReferencia() != null) {
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.setServicoTipoReferencia(osReferidaRetornoTipo
									.getServicoTipoReferencia().getId()
									.toString());
				} else {
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.setServicoTipoReferencia("");
				}

				if (osReferidaRetornoTipo.getAtendimentoMotivoEncerramento() != null) {
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.setAtendimentoMotivoEncerramento(osReferidaRetornoTipo
									.getAtendimentoMotivoEncerramento().getId()
									.toString());
				} else {

					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.setAtendimentoMotivoEncerramento("");
				}

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setDeferimento(""
								+ osReferidaRetornoTipo
										.getIndicadorDeferimento());
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setTrocaServico(""
								+ osReferidaRetornoTipo
										.getIndicadorTrocaServico());

				if (osReferidaRetornoTipo.getSituacaoOsReferencia() != null) {
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.setSituacao(""
									+ osReferidaRetornoTipo
											.getSituacaoOsReferencia());
				} else {
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.setSituacao("");
				}

				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.setIndicadorUso(""
								+ osReferidaRetornoTipo.getIndicadorUso());

				sessao.setAttribute("osReferidaRetornoTipoAtualizar",
						osReferidaRetornoTipo);

			}
		}

		httpServletRequest.setAttribute("colecaoOsReferidaRetornoTipoTela",
				sessao.getAttribute("colecaoOsReferidaRetornoTipoValorTela"));

		return retorno;

	}

	private void getAtendimentoMotivoEncerramentoCollection(HttpSession sessao) {
		Fachada fachada = Fachada.getInstancia();
		// Filtro para o campo Perfil do Im�vel
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento
				.adicionarParametro(new ParametroSimples(
						FiltroAtendimentoMotivoEncerramento.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroAtendimentoMotivoEncerramento
				.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);

		Collection colecaoAtendimentoMotivoEncerramento = fachada.pesquisar(
				filtroAtendimentoMotivoEncerramento,
				AtendimentoMotivoEncerramento.class.getName());
		if (colecaoAtendimentoMotivoEncerramento != null
				&& !colecaoAtendimentoMotivoEncerramento.isEmpty()) {
			sessao.setAttribute("colecaoAtendimentoMotivoEncerramento",
					colecaoAtendimentoMotivoEncerramento);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Motivo de Encerramento do Atendimento");
		}
	}

	private void getServicoTipoReferenciaCollection(HttpSession sessao,
			Fachada fachada) {
		// Filtro para o campo Capacidade do Hidr�metro
		FiltroServicoTipoReferencia filtroServicoTipoReferencia = new FiltroServicoTipoReferencia();
		filtroServicoTipoReferencia.adicionarParametro(new ParametroSimples(
				FiltroServicoTipoReferencia.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroServicoTipoReferencia
				.setCampoOrderBy(FiltroServicoTipoReferencia.DESCRICAO);

		Collection colecaoServicoTipoReferencia = fachada.pesquisar(
				filtroServicoTipoReferencia, ServicoTipoReferencia.class
						.getName());
		if (colecaoServicoTipoReferencia != null
				&& !colecaoServicoTipoReferencia.isEmpty()) {
			sessao.setAttribute("colecaoServicoTipoReferencia",
					colecaoServicoTipoReferencia);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Refer�ncia do Tipo de Servi�o");
		}
	}

}

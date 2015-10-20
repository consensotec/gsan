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
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <<Descri��o da Classe>>
 * 
 * @author lms
 * @date 14/08/2006
 */
public class ExibirGerarOrdemServicoInserirRAAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		String forward = "exibirGerarOrdemServicoInserirRAPopup";

		ActionForward retorno = actionMapping.findForward(forward);
		GerarOrdemServicoActionForm form = (GerarOrdemServicoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		// Imovel
		Imovel imovel = null;

		if (httpServletRequest.getParameter("idImovel") != null) {
			Integer idImovel = Util
					.converterStringParaInteger(httpServletRequest
							.getParameter("idImovel"));
			imovel = new Imovel();
			imovel.setId(idImovel);
		}

		ServicoTipo servicoTipo = null;

		// caso venha do encerrar OS popup
		if (httpServletRequest.getParameter("caminhoRetornoOS") != null
				&& !httpServletRequest.getParameter("caminhoRetornoOS").equals(
						"")) {
			sessao.setAttribute("caminhoRetornoOS", httpServletRequest
					.getParameter("caminhoRetornoOS"));
		}
		// caso venha do encerrar os popup
		String veioEncerrarOS = (String) httpServletRequest
				.getAttribute("veioEncerrarOS");
		if (veioEncerrarOS == null) {
			// caso venha do encerrar os
			veioEncerrarOS = httpServletRequest.getParameter("veioEncerrarOS");
		}
		// caso venha do caso de uso de encerrar OS
		if (veioEncerrarOS != null && !veioEncerrarOS.equals("")) {
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.INDICADOR_FISCALIZACAO_SERVICO_TIPO_REF,
					ServicoTipoReferencia.INDICADOR_FISCALIZACAO_SIM));
			Collection colecaoServicoTipo = fachada.pesquisar(
					filtroServicoTipo, ServicoTipo.class.getName());
			if(colecaoServicoTipo == null || colecaoServicoTipo.isEmpty()){
				throw new ActionServletException(
				"atencao.servico_tipo.sem_fiscalizacao");	
			}
			sessao.setAttribute("colecaoServicosTipo", colecaoServicoTipo);
			// se vier o id da RA ent�o seta na ordem de servi�o
			// caso venha do encerrar os popup
			String numeroRA = (String) httpServletRequest
					.getAttribute("numeroRA");
			if (numeroRA == null) {
				// caso venha do encerrar os
				numeroRA = httpServletRequest.getParameter("numeroRA");
			}
			if (numeroRA != null && !numeroRA.equals("")) {
				RegistroAtendimento registroAtendimento = new RegistroAtendimento();
				registroAtendimento.setId(new Integer(numeroRA));
				// [SF0004] - Verificar unidade do usu�rio
				fachada.verificarUnidadeUsuario(registroAtendimento,
						usuarioLogado);
				form.getOrdemServico().setRegistroAtendimento(
						registroAtendimento);
			}
			// se vier o id da OS ent�o seta o id da os como OSReferencia ent�o
			// seta na ordem de servi�o
			// caso venha do encerrar os popup
			String numeroOS = (String) httpServletRequest
					.getAttribute("numeroOS");
			if (numeroOS == null) {
				// caso venha do encerrar os
				numeroOS = httpServletRequest.getParameter("numeroOS");
			}
			if (numeroOS != null && !numeroOS.equals("")) {
				// Ordem de Servi�o Refer�ncia

				Integer idOrdemServicoReferencia = Util
						.converterStringParaInteger(numeroOS);

				if (Util.validarNumeroMaiorQueZERO(idOrdemServicoReferencia)) {
					OrdemServico os = fachada
							.pesquisarOrdemServico(idOrdemServicoReferencia);
					form.setIdOrdemServicoReferencia(os.getId().toString());
					form.setDescricaoOrdemServicoReferencia(os.getServicoTipo()
							.getDescricao());
					form.getOrdemServico().setOsReferencia(os);
					form.getOrdemServico().setUnidadeAtual(
						os.getUnidadeAtual());
					sessao.setAttribute("veioEncerrarOS", "SIM");
				}
			}
		} else {

			String idTipoSolicitacao = (String) httpServletRequest
					.getParameter("idTipoSolicitacao");
			if (idTipoSolicitacao != null && !idTipoSolicitacao.equals("")) {
				boolean existe = false;
				FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
				filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
						FiltroSolicitacaoTipo.ID,
						new Integer(idTipoSolicitacao)));

				filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
						FiltroSolicitacaoTipo.SOLICITACAO_TIPO_GRUPO_ID,
						SolicitacaoTipoGrupo.ID_OPERACIONAL_AGUA_COM_DIAGNOSTICO));
				Collection colecaoSolicitacaoTipo = fachada.pesquisar(
						filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());
				if (colecaoSolicitacaoTipo != null
						&& !colecaoSolicitacaoTipo.isEmpty()) {
					FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
					filtroServicoTipo
							.adicionarParametro(new ParametroSimples(
									FiltroServicoTipo.INDICADOR_DIAGNOSTICO_SERVICO_TIPO_REF,
									ServicoTipoReferencia.INDICADOR_DIAGNOSTICO_ATIVO));
					Collection colecaoServicoTipo = fachada.pesquisar(
							filtroServicoTipo, ServicoTipo.class.getName());
					if (colecaoServicoTipo != null
							&& !colecaoServicoTipo.isEmpty()) {
						existe = true;
						Integer idServicoTipo = ((ServicoTipo) Util
								.retonarObjetoDeColecao(colecaoServicoTipo))
								.getId();

						String valorServicoOriginal = null;
						Integer idServicoTipoPrioridadeOriginal = null;
						String descricaoServicoTipoPrioridadeOriginal = null;

						servicoTipo = fachada
								.pesquisarSevicoTipo(idServicoTipo);
						form.setIdServicoTipo("" + idServicoTipo);
						String descricaoServicoTipo = servicoTipo
								.getDescricao();
						if (servicoTipo.getValor() != null) {
							valorServicoOriginal = servicoTipo.getValor()
									.toString();
						}
						if (servicoTipo.getServicoTipoPrioridade() != null) {
							idServicoTipoPrioridadeOriginal = servicoTipo
									.getServicoTipoPrioridade().getId();
							descricaoServicoTipoPrioridadeOriginal = servicoTipo
									.getServicoTipoPrioridade().getDescricao();
							ServicoTipoPrioridade servicoTipoPrioridadeOriginal = new ServicoTipoPrioridade();
							servicoTipoPrioridadeOriginal.setId(idServicoTipoPrioridadeOriginal);
							form.getOrdemServico()
									.setServicoTipoPrioridadeOriginal(
											servicoTipoPrioridadeOriginal);
						}
						form.getOrdemServico().setServicoTipo(servicoTipo);
						form.getOrdemServico().setImovel(imovel);

						form.setDescricaoServicoTipo(descricaoServicoTipo);
						form.setValorServicoOriginal(valorServicoOriginal);
						form
								.setIdPrioridadeServicoOriginal(idServicoTipoPrioridadeOriginal
										+ "");
						form
								.setDescricaoPrioridadeServicoOriginal(descricaoServicoTipoPrioridadeOriginal);

						// Servi�o Tipo Refer�ncia

						// Servi�o Tipo
						Integer idServicoTipoReferencia = Util
								.converterStringParaInteger(httpServletRequest
										.getParameter("idServicoTipo"));

						if (Util
								.validarNumeroMaiorQueZERO(idServicoTipoReferencia)) {
							ServicoTipo st = fachada
									.pesquisarSevicoTipo(idServicoTipoReferencia);
							form.setIdServicoTipoReferencia(st.getId()
									.toString());
							form.setDescricaoServicoTipoReferencia(st
									.getDescricao());
							form.getOrdemServico().setServicoTipoReferencia(st);
						}

						httpServletRequest.setAttribute(
								"solicitacaoEspecificacaoOperacionalAgua",
								"SIM");

					}

				}
				if (!existe) {
					// Servi�o Tipo
					Integer idServicoTipo = Util
							.converterStringParaInteger(httpServletRequest
									.getParameter("idServicoTipo"));
					String valorServicoOriginal = null;
					Integer idServicoTipoPrioridadeOriginal = null;
					String descricaoServicoTipoPrioridadeOriginal = null;

					servicoTipo = fachada.pesquisarSevicoTipo(idServicoTipo);
					String descricaoServicoTipo = servicoTipo.getDescricao();
					if (servicoTipo.getValor() != null) {
						valorServicoOriginal = servicoTipo.getValor()
								.toString();
					}
					if (servicoTipo.getServicoTipoPrioridade() != null) {
						idServicoTipoPrioridadeOriginal = servicoTipo
								.getServicoTipoPrioridade().getId();
						descricaoServicoTipoPrioridadeOriginal = servicoTipo
								.getServicoTipoPrioridade().getDescricao();
						ServicoTipoPrioridade servicoTipoPrioridadeOriginal = new ServicoTipoPrioridade();
						form.getOrdemServico()
								.setServicoTipoPrioridadeOriginal(
										servicoTipoPrioridadeOriginal);
					}
					form.getOrdemServico().setServicoTipo(servicoTipo);
					form.getOrdemServico().setImovel(imovel);

					form.setDescricaoServicoTipo(descricaoServicoTipo);
					form.setValorServicoOriginal(valorServicoOriginal);
					form
							.setIdPrioridadeServicoOriginal(idServicoTipoPrioridadeOriginal
									+ "");
					form
							.setDescricaoPrioridadeServicoOriginal(descricaoServicoTipoPrioridadeOriginal);

					// Ordem de Servi�o Refer�ncia

					Integer idOrdemServicoReferencia = Util
							.converterStringParaInteger(form
									.getIdOrdemServicoReferencia());

					if (Util
							.validarNumeroMaiorQueZERO(idOrdemServicoReferencia)) {
						OrdemServico os = fachada
								.pesquisarOrdemServico(idOrdemServicoReferencia);
						form.setIdOrdemServicoReferencia(os.getId().toString());
						form.setDescricaoOrdemServicoReferencia(os
								.getServicoTipo().getDescricao());
						form.getOrdemServico().setOsReferencia(os);
						form.getOrdemServico().setUnidadeAtual(
							os.getUnidadeAtual());
					}

					// Servi�o Tipo Refer�ncia

					Integer idServicoTipoReferencia = Util
							.converterStringParaInteger(form
									.getIdServicoTipoReferencia());

					if (Util.validarNumeroMaiorQueZERO(idServicoTipoReferencia)) {
						ServicoTipo st = fachada
								.pesquisarSevicoTipo(idServicoTipoReferencia);
						form.setIdServicoTipoReferencia(st.getId().toString());
						form.setDescricaoServicoTipoReferencia(st
								.getDescricao());
						form.getOrdemServico().setServicoTipoReferencia(st);
					}

				}
			} else {
				// Servi�o Tipo
				Integer idServicoTipo = null;
				if (httpServletRequest.getParameter("idServicoTipo") != null
						&& !httpServletRequest.getParameter("idServicoTipo")
								.equals("")) {
					// Servi�o Tipo
					idServicoTipo = Util
							.converterStringParaInteger(httpServletRequest
									.getParameter("idServicoTipo"));
				}
				if (form.getIdServicoTipo() != null
						&& !form.getIdServicoTipo().equals("")) {
					// Servi�o Tipo
					idServicoTipo = Util.converterStringParaInteger(form
							.getIdServicoTipo());
				}
				if (idServicoTipo != null) {
					String valorServicoOriginal = null;
					Integer idServicoTipoPrioridadeOriginal = null;
					String descricaoServicoTipoPrioridadeOriginal = null;

					servicoTipo = fachada.pesquisarSevicoTipo(idServicoTipo);
					String descricaoServicoTipo = servicoTipo.getDescricao();
					if (servicoTipo.getValor() != null) {
						valorServicoOriginal = servicoTipo.getValor()
								.toString();
					}
					if (servicoTipo.getServicoTipoPrioridade() != null) {
						idServicoTipoPrioridadeOriginal = servicoTipo
								.getServicoTipoPrioridade().getId();
						descricaoServicoTipoPrioridadeOriginal = servicoTipo
								.getServicoTipoPrioridade().getDescricao();
						ServicoTipoPrioridade servicoTipoPrioridadeOriginal = new ServicoTipoPrioridade();
						servicoTipoPrioridadeOriginal.setId(idServicoTipoPrioridadeOriginal);
						form.getOrdemServico()
								.setServicoTipoPrioridadeOriginal(
										servicoTipoPrioridadeOriginal);
					}
					form.getOrdemServico().setServicoTipo(servicoTipo);
					if (imovel != null) {
						form.getOrdemServico().setImovel(imovel);
					}

					form.setDescricaoServicoTipo(descricaoServicoTipo);
					form.setValorServicoOriginal(valorServicoOriginal);
					form
							.setIdPrioridadeServicoOriginal(idServicoTipoPrioridadeOriginal
									+ "");
					form
							.setDescricaoPrioridadeServicoOriginal(descricaoServicoTipoPrioridadeOriginal);
				}

				// Ordem de Servi�o Refer�ncia
				if (sessao.getAttribute("veioEncerrarOS") == null
						|| sessao.getAttribute("veioEncerrarOS").equals("")) {
					Integer idOrdemServicoReferencia = Util
							.converterStringParaInteger(form
									.getIdOrdemServicoReferencia());

					if (Util
							.validarNumeroMaiorQueZERO(idOrdemServicoReferencia)) {
						OrdemServico os = fachada
								.pesquisarOrdemServico(idOrdemServicoReferencia);
						form.setIdOrdemServicoReferencia(os.getId().toString());
						form.setDescricaoOrdemServicoReferencia(os
								.getServicoTipo().getDescricao());
						form.getOrdemServico().setOsReferencia(os);
						form.getOrdemServico().setUnidadeAtual(
							os.getUnidadeAtual());
					}
					// Servi�o Tipo Refer�ncia
					if (form.getIdServicoTipoReferencia() != null
							&& !form.getIdServicoTipoReferencia().equals("")) {
						Integer idServicoTipoReferencia = Util
								.converterStringParaInteger(form
										.getIdServicoTipoReferencia());

						if (Util
								.validarNumeroMaiorQueZERO(idServicoTipoReferencia)) {
							ServicoTipo st = fachada
									.pesquisarSevicoTipo(idServicoTipoReferencia);
							form.setIdServicoTipoReferencia(st.getId()
									.toString());
							form.setDescricaoServicoTipoReferencia(st
									.getDescricao());
							form.getOrdemServico().setServicoTipoReferencia(st);
						}
					}

				}
			}
		}

		FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();
		filtroServicoTipoPrioridade
				.setCampoOrderBy(FiltroCreditoTipo.DESCRICAO);
		filtroServicoTipoPrioridade.adicionarParametro(new ParametroSimples(
				FiltroCreditoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoServicoTipoPrioridade = fachada.pesquisar(
				filtroServicoTipoPrioridade, ServicoTipoPrioridade.class
						.getName());

		sessao.setAttribute("colecaoServicoTipoPrioridade",
				colecaoServicoTipoPrioridade);

		httpServletRequest.setAttribute("servicoTipo", servicoTipo);

		form.getOrdemServico().setObservacao(form.getObservacao());
		if (form.getIdPrioridadeServicoAtual() != null
				&& !form.getIdPrioridadeServicoAtual().equals("")) {
			ServicoTipoPrioridade servicoTipoPrioridade = new ServicoTipoPrioridade();
			servicoTipoPrioridade.setId(new Integer(form
					.getIdPrioridadeServicoAtual()));
			form.getOrdemServico().setServicoTipoPrioridadeAtual(
					servicoTipoPrioridade);
		} else {
			form.getOrdemServico().setServicoTipoPrioridadeAtual(null);
		}
		
		if (httpServletRequest.getParameter("close") != null) {
			if (sessao.getAttribute("caminhoRetornoOS") != null
					&& !sessao.getAttribute("caminhoRetornoOS").equals("")) {
				httpServletRequest.setAttribute("close", sessao
						.getAttribute("caminhoRetornoOS"));
			} else {
				httpServletRequest.setAttribute("close", Boolean.TRUE);
			}

			
			sessao.setAttribute("ordemServicoFiscalizacao", form.getOrdemServico());
			sessao.removeAttribute("veioEncerrarOS");
			sessao.removeAttribute("caminhoRetornoOS");
		}

		return retorno;

	}
}
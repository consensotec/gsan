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

import gcom.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.EspecificacaoServicoTipo;
import gcom.atendimentopublico.ordemservico.FiltroArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoUnidade;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoUnidade;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.bean.ObterDescricaoSituacaoOSHelper;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <<Descri��o da Classe>>
 * 
 * @author lms/S�vio
 * @date 06/09/2006-18/10/2006
 */
public class ExibirAtualizarOrdemServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarOrdemServico");
		AtualizarOrdemServicoActionForm form = (AtualizarOrdemServicoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		if (httpServletRequest.getParameter("primeiraVez") != null
				&& !httpServletRequest.getParameter("primeiraVez").equals("")) {
			if (httpServletRequest.getParameter("ehPopup") != null
					&& !httpServletRequest.getParameter("ehPopup").equals("")) {
				sessao.setAttribute("ehPopup", httpServletRequest
						.getParameter("ehPopup"));
			} 

			if (httpServletRequest.getParameter("retornoTela") != null
					&& !httpServletRequest.getParameter("retornoTela").equals(
							"")) {
				sessao.setAttribute("retornoTela", httpServletRequest
						.getParameter("retornoTela"));
			} 

			if(httpServletRequest.getParameter("veioAcompanhamentoRoteiro") != null){
				sessao.setAttribute("veioAcompanhamentoRoteiro",httpServletRequest.getParameter("veioAcompanhamentoRoteiro"));
				sessao.setAttribute("dataProgramacaoAtualizar",httpServletRequest.getParameter("dataProgramacao"));
				sessao.setAttribute("chaveArquivoAtualizar", httpServletRequest.getParameter("chaveArquivo"));
			}
			
			Integer numeroOS = Util
					.converterStringParaInteger(httpServletRequest
							.getParameter("numeroOS"));
			// [FS0001] - Verificar unidade do usu�rio
			fachada.verificarUnidadeUsuario(numeroOS, usuarioLogado);
			// Ordem de Servi�o
			OrdemServico ordemServico = pesquisarOrdemServico(numeroOS);
			form.setOrdemServico(ordemServico);
			
			if(httpServletRequest.getParameter("veioAcompanhamentoRoteiro") != null){
				//[FS0008 - Verificar sele��o do arquivo finalizado]
				String chaveArquivo = httpServletRequest.getParameter("chaveArquivo");
				ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico =  null;
				
				if (chaveArquivo != null){
					FiltroArquivoTextoAcompanhamentoServico filtroArquivoTextoAcompanhamentoServico = new FiltroArquivoTextoAcompanhamentoServico();
					filtroArquivoTextoAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAcompanhamentoServico.ID, chaveArquivo));
					
					
					Collection<?> colecaoArquivoTxtAcompanhamentoServico = fachada.pesquisar(
							filtroArquivoTextoAcompanhamentoServico,
						    ArquivoTextoAcompanhamentoServico.class.getName());
					
					arquivoTextoAcompanhamentoServico = (ArquivoTextoAcompanhamentoServico) colecaoArquivoTxtAcompanhamentoServico
						    .iterator().next();
					
					if (arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.TRANSMITIDO)){
						throw new ActionServletException("atencao.nao_possivel.atualizar_os.situacao_finalizado");
					}
				}
			}
			
			//[FS0009 - Verificar sele��o de ordem de servi�o diferente de pendente]
			if(httpServletRequest.getParameter("veioAcompanhamentoRoteiro") != null){
				if (ordemServico.getSituacao() != OrdemServico.SITUACAO_PENDENTE){
					throw new ActionServletException("atencao.situacao.diferente_pendente", "atualizar a");
				}
			}

			// limpa do campos do form
			form.resetarConsultarDadosOSPopup();

			// Dados Gerais da OS
			form.setNumeroOS(ordemServico.getId() + "");
			form.setSituacaoOSId(ordemServico.getSituacao() + "");
			// Caso de Uso [UC0454]
			ObterDescricaoSituacaoOSHelper situacaoOS = fachada
					.obterDescricaoSituacaoOS(ordemServico.getId());
			form.setSituacaoOS(situacaoOS.getDescricaoSituacao());
			if (ordemServico.getRegistroAtendimento() != null) {
				form.setNumeroRA(ordemServico.getRegistroAtendimento().getId()
						+ "");
				// Caso de Uso [UC0420]
				ObterDescricaoSituacaoRAHelper situacaoRA = fachada
						.obterDescricaoSituacaoRA(ordemServico
								.getRegistroAtendimento().getId());
				form.setSituacaoRA(situacaoRA.getDescricaoSituacao());
			}
			if (ordemServico.getCobrancaDocumento() != null) {
				form.setNumeroDocumentoCobranca(ordemServico
						.getCobrancaDocumento().getId()
						+ "");
			}
			form.setDataGeracao(Util
					.formatarData(ordemServico.getDataGeracao()));

			if (ordemServico.getServicoTipo() != null
					&& !ordemServico.getServicoTipo().equals("")) {

				form.setIdServicoTipo(ordemServico.getServicoTipo().getId()
						+ "");
				form.setDescricaoServicoTipo(ordemServico.getServicoTipo()
						.getDescricao());
				ServicoTipo servicoTipo = fachada
						.pesquisarSevicoTipo(ordemServico.getServicoTipo()
								.getId());
				sessao.setAttribute("servicoTipo", servicoTipo);

				if (ordemServico.getServicoTipoReferencia() != null
						&& !ordemServico
								.getServicoTipoReferencia().equals("")) {
					ServicoTipo st = fachada.pesquisarSevicoTipo(ordemServico.getServicoTipoReferencia()
							.getId());
					form.setIdServicoTipoReferencia(st.getId().toString());
					form.setDescricaoServicoTipoReferencia(st.getDescricao());
					form.getOrdemServico().setServicoTipoReferencia(st);
					form.setIdServicoTipoReferencia(""
							+ ordemServico
									.getServicoTipoReferencia().getId());
					form.setDescricaoServicoTipoReferencia(ordemServico.getServicoTipoReferencia()
							.getDescricao());
				}

			}

			if (ordemServico.getOsReferencia() != null) {
				OrdemServico os = fachada.pesquisarOrdemServico(ordemServico
						.getOsReferencia().getId());
				form.setIdOrdemServicoReferencia(os.getId().toString());
				form.setDescricaoOrdemServicoReferencia(os.getServicoTipo()
						.getDescricao());
				form.getOrdemServico().setOsReferencia(os);
			}

			form.setObservacao(ordemServico.getObservacao());
			form.setValorServicoOriginal(ordemServico.getValorOriginal() + "");
			form.setPrioridadeOriginal(ordemServico
					.getServicoTipoPrioridadeOriginal().getDescricao());
			form.setPrioridadeAtual(ordemServico
					.getServicoTipoPrioridadeAtual().getDescricao()
					+ "");
			OrdemServicoUnidade ordemServicoUnidade = consultarOrdemServicoUnidade(
					ordemServico.getId(),
					AtendimentoRelacaoTipo.ABRIR_REGISTRAR);
			if (ordemServicoUnidade != null) {
				form.setUnidadeGeracaoId(ordemServicoUnidade
						.getUnidadeOrganizacional().getId()
						+ "");
				form.setUnidadeGeracaoDescricao(ordemServicoUnidade
						.getUnidadeOrganizacional().getDescricao());
				form.setUsuarioGeracaoId(ordemServicoUnidade.getUsuario()
						.getId()
						+ "");
				form.setUsuarioGeracaoNome(ordemServicoUnidade.getUsuario()
						.getNomeUsuario());
			}
			if (ordemServico.getDataEmissao() != null) {
				form.setDataUltimaEmissao(Util.formatarData(ordemServico
						.getDataEmissao()));
			}
			// fim dos dados gerais

			form.setDescricaoPrioridadeServicoOriginal(ordemServico
					.getServicoTipoPrioridadeOriginal().getDescricao());

			Integer IdPrioridadeServicoAtual = Util
					.converterStringParaInteger(form
							.getIdPrioridadeServicoAtual());

			if (Util.validarNumeroMaiorQueZERO(IdPrioridadeServicoAtual)) {
				form.setIdPrioridadeServicoAtual(IdPrioridadeServicoAtual
						.toString());
			} else {
				if (ordemServico.getServicoTipoPrioridadeAtual() != null) {
					form
							.setIdPrioridadeServicoAtual(ordemServico
									.getServicoTipoPrioridadeAtual().getId()
									.toString());
				}
			}

			form.setObservacao(ordemServico.getObservacao());

			// Cole��o de Servi�os Tipo

			Collection colecaoServicosTipo = new ArrayList();
			// Registro de Atendimento
			RegistroAtendimento ra = ordemServico.getRegistroAtendimento();
			if (ra != null) {
				ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = fachada
						.obterDadosRegistroAtendimento(ra.getId());
				ra = fachada.validarRegistroAtendimento(ra.getId());
				for (Iterator iter = ra.getSolicitacaoTipoEspecificacao()
						.getEspecificacaoServicoTipos().iterator(); iter
						.hasNext();) {
					EspecificacaoServicoTipo est = (EspecificacaoServicoTipo) iter
							.next();
					colecaoServicosTipo.add(est.getServicoTipo());
				}
				fachada.verificarUnidadeUsuario(ordemServico.getId(),
						usuarioLogado);
				httpServletRequest.setAttribute("registroAtendimentoHelper",
						registroAtendimentoHelper);
				httpServletRequest.setAttribute("unidadeAtualRA", fachada
						.obterUnidadeAtualRA(ra.getId()));
			}

			sessao.setAttribute("colecaoServicosTipo", colecaoServicosTipo);

			FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();
			filtroServicoTipoPrioridade
					.setCampoOrderBy(FiltroCreditoTipo.DESCRICAO);
			filtroServicoTipoPrioridade
					.adicionarParametro(new ParametroSimples(
							FiltroCreditoTipo.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoServicoTipoPrioridade = fachada.pesquisar(
					filtroServicoTipoPrioridade, ServicoTipoPrioridade.class
							.getName());

			sessao.setAttribute("colecaoServicoTipoPrioridade",
					colecaoServicoTipoPrioridade);

		}

		// pesquisa do enter servi�o tipo
		String idServicoTipo = form.getIdServicoTipo();
		String descricaoServicoTipo = form.getDescricaoServicoTipo();
		if (idServicoTipo != null
				&& !idServicoTipo.equals("")
				&& (descricaoServicoTipo == null || descricaoServicoTipo
						.equals(""))) {

			OrdemServico ordemServico = form.getOrdemServico();
			if (ordemServico.getOsReferencia() != null
					&& !ordemServico.getOsReferencia().equals("")) {
				form.setIdOrdemServicoReferencia(""
						+ ordemServico.getOsReferencia().getId());
			}

			Integer idServicoTipoInteger = Util.converterStringParaInteger(form
					.getIdServicoTipo());

			ServicoTipo servicoTipo = fachada
					.pesquisarSevicoTipo(idServicoTipoInteger);

			if (servicoTipo != null && !servicoTipo.equals("")) {
				form.getOrdemServico().setServicoTipo(servicoTipo);
				form.setDescricaoServicoTipo(servicoTipo.getDescricao());
				form.setIdServicoTipo(servicoTipo.getId().toString());
				sessao.setAttribute("servicoTipo", servicoTipo);

			} else {
				form.setIdServicoTipo("");
				form.getOrdemServico().setServicoTipo(null);
				httpServletRequest.setAttribute("nomeCampo", "idServicoTipo");
				httpServletRequest.setAttribute("idServicoTipoNaoEncontrado",
						"exception");
				form.setDescricaoServicoTipo("Tipo Servi�o Inexistente");
			}
		}

		// pesquisa do enter Ordem de Servi�o Refer�ncia
		String idOSReferencia = form.getIdOrdemServicoReferencia();
		String descricaoOsReferencia = form
				.getDescricaoOrdemServicoReferencia();
		if (idOSReferencia != null
				&& !idOSReferencia.equals("")
				&& (descricaoOsReferencia == null || descricaoOsReferencia
						.equals(""))) {
			Integer idOrdemServicoReferencia = Util
					.converterStringParaInteger(form
							.getIdOrdemServicoReferencia());

			OrdemServico os = fachada
					.pesquisarOrdemServico(idOrdemServicoReferencia);

			if (os != null && !os.equals("")) {
				form.setIdOrdemServicoReferencia(os.getId().toString());
				form.setDescricaoOrdemServicoReferencia(os.getServicoTipo()
						.getDescricao());
				form.getOrdemServico().setOsReferencia(os);
			} else {
				form.setIdOrdemServicoReferencia("");
				form.getOrdemServico().setOsReferencia(null);

				httpServletRequest.setAttribute("nomeCampo",
						"idOrdemServicoReferencia");
				httpServletRequest.setAttribute(
						"idOrdemServicoReferenciaNaoEncontrado", "exception");
				form
						.setDescricaoServicoTipo("Ordem Servi�o Refer�ncia Inexistente");

			}
		}

		// pesquisa do enter Servi�o Tipo Refer�ncia
		String idServicoTipoReferencia = form.getIdServicoTipoReferencia();
		String descricaoServicoTipoReferencia = form
				.getDescricaoServicoTipoReferencia();
		if (idServicoTipoReferencia != null
				&& !idServicoTipoReferencia.equals("")
				&& (descricaoServicoTipoReferencia == null || descricaoServicoTipoReferencia
						.equals(""))) {

			Integer idTipoServicoReferenciaInteger = Util
					.converterStringParaInteger(form
							.getIdServicoTipoReferencia());

			ServicoTipo st = fachada
					.pesquisarSevicoTipo(idTipoServicoReferenciaInteger);
			if (st != null && !st.equals("")) {
				form.setIdServicoTipoReferencia(st.getId().toString());
				form.setDescricaoServicoTipoReferencia(st.getDescricao());
				form.getOrdemServico().setServicoTipoReferencia(st);
			} else {
				form.setIdServicoTipoReferencia("");
				form.setDescricaoServicoTipoReferencia("");
				form.getOrdemServico().setServicoTipoReferencia(null);
				httpServletRequest.setAttribute("nomeCampo",
						"idServicoTipoReferencia");
				httpServletRequest.setAttribute(
						"idServicoTipoReferenciaNaoEncontrado", "exception");
				form
						.setDescricaoServicoTipo("Servi�o Tipo Refer�ncia Inexistente");
			}
		}

		// sessao.setAttribute("servicoTipo", servicoTipo);
		// httpServletRequest.setAttribute("registroAtendimento", ra);

		return retorno;

	}

	private OrdemServico pesquisarOrdemServico(Integer id) {
		Fachada fachada = Fachada.getInstancia();
		OrdemServico retorno = fachada.consultarDadosOrdemServico(id);
		if (retorno == null) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Ordem de Servi�o");
		}
		return retorno;
	}

	/**
	 * Consulta a Ordem Servi�o Unidade pelo id do OS e Tipo (1=ABRIR/REGISTRAR
	 * e 3-ENCERRAR)
	 * 
	 * @author S�vio luiz
	 * @date 18/09/2006
	 */
	private OrdemServicoUnidade consultarOrdemServicoUnidade(Integer idOS,
			Integer idAtendimentoTipo) {

		OrdemServicoUnidade retorno = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoOrdemServicoUnidade = null;

		FiltroOrdemServicoUnidade filtroOrdemServicoUnidade = new FiltroOrdemServicoUnidade();
		filtroOrdemServicoUnidade.adicionarParametro(new ParametroSimples(
				FiltroOrdemServicoUnidade.ORDEM_SERVICO_ID, idOS));
		filtroOrdemServicoUnidade.adicionarParametro(new ParametroSimples(
				FiltroOrdemServicoUnidade.ATENDIMENTO_RELACAO_TIPO_ID,
				idAtendimentoTipo));

		filtroOrdemServicoUnidade
				.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
//		filtroOrdemServicoUnidade
//				.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroOrdemServicoUnidade
				.adicionarCaminhoParaCarregamentoEntidade("usuario");
//		filtroOrdemServicoUnidade
//				.adicionarCaminhoParaCarregamentoEntidade("usuario.nomeUsuario");

		colecaoOrdemServicoUnidade = fachada.pesquisar(
				filtroOrdemServicoUnidade, OrdemServicoUnidade.class.getName());
		if (colecaoOrdemServicoUnidade != null
				&& !colecaoOrdemServicoUnidade.isEmpty()) {
			retorno = (OrdemServicoUnidade) Util
					.retonarObjetoDeColecao(colecaoOrdemServicoUnidade);
		}

		return retorno;
	}

}
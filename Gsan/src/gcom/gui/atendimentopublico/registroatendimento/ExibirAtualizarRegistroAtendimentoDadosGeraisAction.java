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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoConta;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoConta;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os
 * par�metros para realiza��o da atualiza��o de um R.A (Aba n� 01 - Dados
 * gerais)
 * 
 * @author S�vio Luiz
 * @date 10/08/2006
 */
public class ExibirAtualizarRegistroAtendimentoDadosGeraisAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("atualizarRegistroAtendimentoDadosGerais");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("gis");
		
		// recupara o id da especifica��o para verificar se
		// ser� gerado a ordem de servi�o ou n�o dependendo da mudan�a
		// da especifica��o
		Integer idEspecificacaoBase = (Integer) sessao
				.getAttribute("idEspecificacaoBase");

		AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm = null;

		if (httpServletRequest
				.getAttribute("AtualizarRegistroAtendimentoActionForm") != null) {
			sessao
					.setAttribute(
							"AtualizarRegistroAtendimentoActionForm",
							httpServletRequest
									.getAttribute("AtualizarRegistroAtendimentoActionForm"));
			atualizarRegistroAtendimentoActionForm = (AtualizarRegistroAtendimentoActionForm) httpServletRequest
					.getAttribute("AtualizarRegistroAtendimentoActionForm");
		} else {
			atualizarRegistroAtendimentoActionForm = (AtualizarRegistroAtendimentoActionForm) actionForm;
		}

		Fachada fachada = Fachada.getInstancia();

		/*
		 * Unidade de Atendimento (Permite que o usu�rio informe ou selecione
		 * outra)
		 * 
		 * [FS0004] - Verificar exist�ncia da unidade de atendimento
		 * 
		 * [FS0033] - Verificar autoriza��o da unidade de atendimento para
		 * abertura de registro de atendimento
		 */

		String idUnidade = atualizarRegistroAtendimentoActionForm.getUnidade();
		String descricaoUnidade = atualizarRegistroAtendimentoActionForm
				.getDescricaoUnidade();

		if (idUnidade != null && !idUnidade.equalsIgnoreCase("")
				&& (descricaoUnidade == null || descricaoUnidade.equals(""))) {

			UnidadeOrganizacional unidadeOrganizacionalSelecionada = fachada
					.verificarAutorizacaoUnidadeAberturaRA(new Integer(
							idUnidade), false);

			if (unidadeOrganizacionalSelecionada != null) {
				atualizarRegistroAtendimentoActionForm
						.setUnidade(unidadeOrganizacionalSelecionada.getId()
								.toString());
				atualizarRegistroAtendimentoActionForm
						.setDescricaoUnidade(unidadeOrganizacionalSelecionada
								.getDescricao());

				if (unidadeOrganizacionalSelecionada.getMeioSolicitacao() != null) {

					atualizarRegistroAtendimentoActionForm
							.setMeioSolicitacao(unidadeOrganizacionalSelecionada
									.getMeioSolicitacao().getId().toString());
				}

				httpServletRequest.setAttribute("nomeCampo", "meioSolicitacao");

			} else {

				atualizarRegistroAtendimentoActionForm.setUnidade("");
				atualizarRegistroAtendimentoActionForm
						.setDescricaoUnidade("Unidade Inexistente");

				httpServletRequest.setAttribute("corUnidade", "exception");
				httpServletRequest.setAttribute("nomeCampo", "unidade");
			}

		}

		/*
		 * Meio de Solicita��o - Carregando a cole��o que ir� ficar dispon�vel
		 * para escolha do usu�rio
		 * 
		 * [FS0003] - Verificar exist�ncia de dados
		 */
		Collection colecaoMeioSolicitacao = (Collection) sessao
				.getAttribute("colecaoMeioSolicitacao");

		if (colecaoMeioSolicitacao == null) {

			FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao(
					FiltroMeioSolicitacao.DESCRICAO);

			filtroMeioSolicitacao.setConsultaSemLimites(true);

			filtroMeioSolicitacao.adicionarParametro(new ParametroSimples(
					FiltroMeioSolicitacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			

			colecaoMeioSolicitacao = fachada.pesquisar(filtroMeioSolicitacao,
					MeioSolicitacao.class.getName());

			if (colecaoMeioSolicitacao == null
					|| colecaoMeioSolicitacao.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"MEIO_SOLICITACAO");
			} else {
				sessao.setAttribute("colecaoMeioSolicitacao",
						colecaoMeioSolicitacao);
			}
		}

		/*
		 * Tipo de Solicita��o - Carregando a cole��o que ir� ficar dispon�vel
		 * para escolha do usu�rio
		 * 
		 * [FS0003] - Verificar exist�ncia de dados
		 */
		Collection colecaoSolicitacaoTipo = (Collection) sessao
				.getAttribute("colecaoSolicitacaoTipo");

		if (colecaoSolicitacaoTipo == null) {

			FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo(
					FiltroSolicitacaoTipo.DESCRICAO);

			filtroSolicitacaoTipo.setConsultaSemLimites(true);
			
			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA,
					SolicitacaoTipo.INDICADOR_USO_SISTEMA_NAO));

			colecaoSolicitacaoTipo = fachada.pesquisar(filtroSolicitacaoTipo,
					SolicitacaoTipo.class.getName());

			if (colecaoSolicitacaoTipo == null
					|| colecaoSolicitacaoTipo.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"SOLICITACAO_TIPO");
			} else {
				sessao.setAttribute("colecaoSolicitacaoTipo",
						colecaoSolicitacaoTipo);
			}
		}

		/*
		 * Especifica��o - Carregando a cole��o que ir� ficar dispon�vel para
		 * escolha do usu�rio
		 * 
		 * [FS0003] - Verificar exist�ncia de dados
		 */
		String pesquisarEspecificacao = httpServletRequest
				.getParameter("pesquisarEspecificacao");

		if (pesquisarEspecificacao != null
				&& !pesquisarEspecificacao.equalsIgnoreCase("")
				|| (atualizarRegistroAtendimentoActionForm.getTipoSolicitacao() != null && !atualizarRegistroAtendimentoActionForm
						.getTipoSolicitacao().equals(""))) {

			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao(
					FiltroSolicitacaoTipoEspecificacao.DESCRICAO);

			filtroSolicitacaoTipoEspecificacao
					.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID,
							new Integer(atualizarRegistroAtendimentoActionForm
									.getTipoSolicitacao())));

			filtroSolicitacaoTipoEspecificacao.setConsultaSemLimites(true);

			Collection colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
					filtroSolicitacaoTipoEspecificacao,
					SolicitacaoTipoEspecificacao.class.getName());

			if (colecaoSolicitacaoTipoEspecificacao == null
					|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
				sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
				atualizarRegistroAtendimentoActionForm.setDataPrevista("");
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"SOLICITACAO_TIPO_ESPECIFICACAO");
			} else {
				sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
						colecaoSolicitacaoTipoEspecificacao);
			}
		}

		/*
		 * Data Prevista - (exibir a data prevista calculada no SB0003 e n�o
		 * permitir altera��o).
		 * 
		 * [SB0003 - Define Data Prevista e Unidade Destino da Especifica��o]
		 * [FS0018] - Verificar exist�ncia da unidade centralizadora
		 */
		String definirDataPrevista = httpServletRequest
				.getParameter("definirDataPrevista");

		if (definirDataPrevista != null
				&& !definirDataPrevista.equalsIgnoreCase("")
				&& atualizarRegistroAtendimentoActionForm.getDataAtendimento() != null
				&& !atualizarRegistroAtendimentoActionForm.getDataAtendimento()
						.equalsIgnoreCase("")
				&& !atualizarRegistroAtendimentoActionForm
						.getEspecificacao()
						.equalsIgnoreCase(
								String
										.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

			Date dataAtendimento = Util
					.converteStringParaDate(atualizarRegistroAtendimentoActionForm
							.getDataAtendimento());

			DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper dataPrevistaUnidadeDestino = fachada
					.definirDataPrevistaUnidadeDestinoEspecificacao(
							dataAtendimento, new Integer(
									atualizarRegistroAtendimentoActionForm
											.getEspecificacao()));

			if (dataPrevistaUnidadeDestino.getDataPrevista() != null) {
				atualizarRegistroAtendimentoActionForm.setDataPrevista(Util
						.formatarData(dataPrevistaUnidadeDestino
								.getDataPrevista()));
			}

			atualizarRegistroAtendimentoActionForm
					.setIndFaltaAgua(dataPrevistaUnidadeDestino
							.getIndFaltaAgua());

			atualizarRegistroAtendimentoActionForm
					.setIndMatricula(dataPrevistaUnidadeDestino
							.getIndMatricula());

			atualizarRegistroAtendimentoActionForm
					.setImovelObrigatorio(dataPrevistaUnidadeDestino
							.getImovelObrigatorio());

			atualizarRegistroAtendimentoActionForm
					.setPavimentoRuaObrigatorio(dataPrevistaUnidadeDestino
							.getPavimentoRuaObrigatorio());

			atualizarRegistroAtendimentoActionForm
					.setPavimentoCalcadaObrigatorio(dataPrevistaUnidadeDestino
							.getPavimentoCalcadaObrigatorio());

			// Identificar tipo de gera��o da ordem de servi�o (AUTOM�TICA,
			// OPCIONAL ou N�O GERAR)
			if ((fachada
					.gerarOrdemServicoAutomatica(Util
							.converterStringParaInteger(atualizarRegistroAtendimentoActionForm
									.getEspecificacao())))
					&& (!idEspecificacaoBase.equals(new Integer(
							atualizarRegistroAtendimentoActionForm
									.getEspecificacao())))) {
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
				
				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.ID,
								atualizarRegistroAtendimentoActionForm.getEspecificacao()));

				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroNaoNulo(
								FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO_ID));
				
				filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
						FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
								filtroSolicitacaoTipoEspecificacao,
								SolicitacaoTipoEspecificacao.class.getName());

				Integer idServicoTipo = ((SolicitacaoTipoEspecificacao)
				colecaoSolicitacaoTipoEspecificacao.iterator().next()).getServicoTipo().getId();
				
				sessao.setAttribute("servicoTipo", idServicoTipo);
			
				
				sessao.setAttribute("gerarOSAutomativa", "OK");
			} else {
				sessao.removeAttribute("gerarOSAutomativa");
				sessao.removeAttribute("servicoTipo");
			}

			httpServletRequest.setAttribute("nomeCampo", "observacao");
		}
		/*
		 * Caso o Tempo de Espera Final esteja desabilitado e o Tempo de Espera
		 * Inicial para Atendimento esteja preenchido, atribuir o valor
		 * correspondente � hora corrente e n�o permitir altera��o
		 */
		String tempoEsperaFinalDesabilitado = httpServletRequest
				.getParameter("tempoEsperaFinalDesabilitado");

		if (tempoEsperaFinalDesabilitado != null
				&& !tempoEsperaFinalDesabilitado.equalsIgnoreCase("")) {
			this
					.atribuirHoraCorrenteTempoEsperaFinal(atualizarRegistroAtendimentoActionForm);
			httpServletRequest.setAttribute("nomeCampo", "unidade");
		}
		
		// [FS0045] - Verificar exist�ncia de Contas Associadas
		// Mariana Victor - 31/01/2011
		if (atualizarRegistroAtendimentoActionForm.getTipoSolicitacao() != null
				&& !atualizarRegistroAtendimentoActionForm.getTipoSolicitacao().equals("")
				&& atualizarRegistroAtendimentoActionForm.getEspecificacao() != null
				&& !atualizarRegistroAtendimentoActionForm.getEspecificacao().equals("")) {
			FiltroRegistroAtendimentoConta filtroRegistroAtendimentoConta = new FiltroRegistroAtendimentoConta();
			filtroRegistroAtendimentoConta.adicionarParametro(
					new ParametroSimples(FiltroRegistroAtendimentoConta.REGISTRO_ATENDIMENTO_ID, atualizarRegistroAtendimentoActionForm.getNumeroRA()));
			Collection colecaoRAConta = fachada.pesquisar(filtroRegistroAtendimentoConta, RegistroAtendimentoConta.class.getName());
			
			if (colecaoRAConta != null && !colecaoRAConta.isEmpty()) {
				sessao.setAttribute("contasAssociadas", true);
			} else {
				sessao.removeAttribute("contasAssociadas");
			}
		}

		return retorno;
	}

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Atribui o valor correspondente � hora corrente
	 * 
	 * @author S�vio Luiz
	 * @date 10/08/2006
	 * 
	 * @param AtualizarRegistroAtendimentoActionForm
	 * @return void
	 */
	private void atribuirHoraCorrenteTempoEsperaFinal(
			AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm) {

		Date dataCorrente = new Date();

		atualizarRegistroAtendimentoActionForm.setTempoEsperaFinal(Util
				.formatarHoraSemSegundos(dataCorrente));
	}

}

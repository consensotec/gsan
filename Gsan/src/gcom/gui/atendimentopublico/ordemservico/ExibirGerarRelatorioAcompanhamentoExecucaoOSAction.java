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

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0450] Pesquisar Ordem Servico - Exibir
 * 
 * @author Leonardo Regis
 * 
 * @date 04/09/2006
 */
public class ExibirGerarRelatorioAcompanhamentoExecucaoOSAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAcompanhamentoExecucaoOS");

		HttpSession sessao = httpServletRequest.getSession(false);

		// Form
		GerarRelatorioAcompanhamentoExecucaoOSActionForm gerarRelatorioAcompanhamentoExecucaoOSActionForm = (GerarRelatorioAcompanhamentoExecucaoOSActionForm) actionForm;

		if (httpServletRequest.getParameter("menu") != null) {
			gerarRelatorioAcompanhamentoExecucaoOSActionForm
					.setSituacaoOrdemServico("");
			gerarRelatorioAcompanhamentoExecucaoOSActionForm
					.setOrigemServico(ConstantesSistema.ORIGEM_SERVICO_SOLICITADO
							.toString());
			gerarRelatorioAcompanhamentoExecucaoOSActionForm
					.setTipoOrdenacao("1");
			Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
			if (usuario != null) {
				gerarRelatorioAcompanhamentoExecucaoOSActionForm
						.setIdUnidadeAtual(usuario.getUnidadeOrganizacional()
								.getId().toString());
				gerarRelatorioAcompanhamentoExecucaoOSActionForm
						.setDescricaoUnidadeAtual(usuario
								.getUnidadeOrganizacional().getDescricao());
			}
		}

		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest
				.getParameter("objetoConsulta");

		// [UC0376] - Pesquisar Unidade
		if ((objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta
				.trim().equals("1"))
				|| (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta
						.trim().equals("2"))
				|| (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta
						.trim().equals("3"))) {

			// Faz a consulta de Cliente
			this.pesquisarUnidadeOrganizacional(
					gerarRelatorioAcompanhamentoExecucaoOSActionForm,
					objetoConsulta);
		}

		// [UC0075] - Pesquisar Equipe
		if ((objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta
				.trim().equals("4"))
				|| (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta
						.trim().equals("5"))) {

			// Faz a consulta de Equipe
			this.pesquisarEquipe(
					gerarRelatorioAcompanhamentoExecucaoOSActionForm,
					objetoConsulta);
		}

		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {

			String id = httpServletRequest.getParameter("idCampoEnviarDados");
			String descricao = httpServletRequest
					.getParameter("descricaoCampoEnviarDados");

			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"unidadeOrganizacional")) {

				if (sessao.getAttribute("tipoUnidade").equals(
						"unidadeAtendimento")) {
					gerarRelatorioAcompanhamentoExecucaoOSActionForm
							.setIdUnidadeAtendimento(id);
					gerarRelatorioAcompanhamentoExecucaoOSActionForm
							.setDescricaoUnidadeAtendimento(descricao);

				} else if (sessao.getAttribute("tipoUnidade").equals(
						"unidadeAtual")) {
					gerarRelatorioAcompanhamentoExecucaoOSActionForm
							.setIdUnidadeAtual(id);
					gerarRelatorioAcompanhamentoExecucaoOSActionForm
							.setDescricaoUnidadeAtual(descricao);

				} else {
					gerarRelatorioAcompanhamentoExecucaoOSActionForm
							.setIdUnidadeEncerramento(id);
					gerarRelatorioAcompanhamentoExecucaoOSActionForm
							.setDescricaoUnidadeEncerramento(descricao);
				}

			} else if (httpServletRequest.getParameter("tipoConsulta").equals(
					"equipe")) {

				if (sessao.getAttribute("equipe").equals("equipeProgramacao")) {
					gerarRelatorioAcompanhamentoExecucaoOSActionForm
							.setIdEquipeProgramacao(id);
					gerarRelatorioAcompanhamentoExecucaoOSActionForm
							.setDescricaoEquipeProgramacao(descricao);
				} else {
					gerarRelatorioAcompanhamentoExecucaoOSActionForm
							.setIdEquipeExecucao(id);
					gerarRelatorioAcompanhamentoExecucaoOSActionForm
							.setDescricaoEquipeExecucao(descricao);
				}
			}
		}

		Collection colecaoTipoServicoSelecionados = null;
		
		if(gerarRelatorioAcompanhamentoExecucaoOSActionForm.getTipoServicoSelecionados() != null){
			
			String[] aux = gerarRelatorioAcompanhamentoExecucaoOSActionForm
			.getTipoServicoSelecionados();
			
			List aux1 = Arrays.asList(aux);
			colecaoTipoServicoSelecionados = aux1;
			
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			
			filtroServicoTipo.adicionarParametro(new ParametroSimplesIn(FiltroServicoTipo.ID, colecaoTipoServicoSelecionados));
			
			filtroServicoTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);
			// Pesquisa de acordo com os par�metros informados no filtro
			colecaoTipoServicoSelecionados = Fachada.getInstancia().pesquisar(
					filtroServicoTipo, ServicoTipo.class.getName());
			
			
			// Verifica se a pesquisa retornou algum objeto para a cole��o
			if (colecaoTipoServicoSelecionados != null && !colecaoTipoServicoSelecionados.isEmpty()) {
				httpServletRequest.setAttribute("colecaoTipoServicoSelecionados", colecaoTipoServicoSelecionados);
				httpServletRequest.setAttribute("existeColecaoTipoServicoSelecionados", colecaoTipoServicoSelecionados);
			}
		}
		
		// Monta a colecao de tipos Servicos
		this.pesquisarTipoServico(httpServletRequest, colecaoTipoServicoSelecionados);
		
		// Seta os request�s encontrados
		this.setaRequest(httpServletRequest,
				gerarRelatorioAcompanhamentoExecucaoOSActionForm);

		// if
		// (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaOrdemServico")
		// != null) {
		//			
		// sessao.setAttribute("caminhoRetornoTelaPesquisaOrdemServico",
		// httpServletRequest.getParameter("caminhoRetornoTelaPesquisaOrdemServico"));
		//			
		// }
		
		httpServletRequest.setAttribute("origemServico",
				gerarRelatorioAcompanhamentoExecucaoOSActionForm
						.getOrigemServico());
		httpServletRequest.setAttribute("situacaoOS",
				gerarRelatorioAcompanhamentoExecucaoOSActionForm
						.getSituacaoOrdemServico());

		return retorno;
	}

	/**
	 * Pesquisa Unidade Organizacional
	 * 
	 * @author Rafael Corr�a
	 * @date 30/10/2006
	 */
	private void pesquisarUnidadeOrganizacional(
			GerarRelatorioAcompanhamentoExecucaoOSActionForm form,
			String objetoConsulta) {

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		Integer idUnidade = null;

		if (objetoConsulta.equals("1")) {
			idUnidade = new Integer(form.getIdUnidadeAtendimento());
		} else if (objetoConsulta.equals("2")) {
			idUnidade = new Integer(form.getIdUnidadeAtual());
		} else {
			idUnidade = new Integer(form.getIdUnidadeEncerramento());
		}

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, idUnidade));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoUnidade = Fachada.getInstancia().pesquisar(
				filtroUnidadeOrganizacional,
				UnidadeOrganizacional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util
					.retonarObjetoDeColecao(colecaoUnidade);

			if (objetoConsulta.equals("1")) {

				form.setIdUnidadeAtendimento(unidadeOrganizacional.getId()
						.toString());
				form.setDescricaoUnidadeAtendimento(unidadeOrganizacional
						.getDescricao());

			} else if (objetoConsulta.equals("2")) {

				form
						.setIdUnidadeAtual(unidadeOrganizacional.getId()
								.toString());
				form.setDescricaoUnidadeAtual(unidadeOrganizacional
						.getDescricao());

			} else {

				form.setIdUnidadeEncerramento(unidadeOrganizacional.getId()
						.toString());
				form.setDescricaoUnidadeEncerramento(unidadeOrganizacional
						.getDescricao());

			}

		} else {
			if (objetoConsulta.equals("1")) {

				form.setIdUnidadeAtendimento("");
				form
						.setDescricaoUnidadeAtendimento("Unidade de Atendimento inexistente");

			} else if (objetoConsulta.equals("2")) {

				form.setIdUnidadeAtual("");
				form.setDescricaoUnidadeAtual("Unidade Atual inexistente");

			} else {

				form.setIdUnidadeEncerramento("");
				form
						.setDescricaoUnidadeEncerramento("Unidade Encerramento inexistente");

			}
		}
	}

	/**
	 * Pesquisa Unidade Organizacional
	 * 
	 * @author Rafael Corr�a
	 * @date 30/10/2006
	 */
	private void pesquisarEquipe(
			GerarRelatorioAcompanhamentoExecucaoOSActionForm form,
			String objetoConsulta) {

		FiltroEquipe filtroEquipe = new FiltroEquipe();

		Integer idEquipe = null;

		if (objetoConsulta.equals("4")) {
			idEquipe = new Integer(form.getIdEquipeProgramacao());
		} else {
			idEquipe = new Integer(form.getIdEquipeExecucao());
		}

		filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID,
				idEquipe));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoEquipe = Fachada.getInstancia().pesquisar(
				filtroEquipe, Equipe.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoEquipe != null && !colecaoEquipe.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Equipe equipe = (Equipe) Util.retonarObjetoDeColecao(colecaoEquipe);

			if (objetoConsulta.equals("4")) {

				form.setIdEquipeProgramacao(equipe.getId().toString());
				form.setDescricaoEquipeProgramacao(equipe.getNome());

			} else {

				form.setIdEquipeExecucao(equipe.getId().toString());
				form.setDescricaoEquipeExecucao(equipe.getNome());

			}

		} else {
			if (objetoConsulta.equals("4")) {

				form.setIdEquipeProgramacao("");
				form
						.setDescricaoEquipeProgramacao("Equipe de Programa��o inexistente");

			} else {

				form.setIdEquipeExecucao("");
				form
						.setDescricaoEquipeExecucao("Equipe de Execucao inexistente");

			}
		}
	}

	/**
	 * Pesquisa Tipo Servico
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 */
	private void pesquisarTipoServico(HttpServletRequest httpServletRequest, Collection colecaoTipoServicoSelecionados) {

		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

		filtroServicoTipo.setConsultaSemLimites(true);
		filtroServicoTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);

		Collection colecaoTipoServico = Fachada.getInstancia().pesquisar(
				filtroServicoTipo, ServicoTipo.class.getName());

		if (colecaoTipoServico == null || colecaoTipoServico.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Tipo de Servi�o");
		} else {
			if(colecaoTipoServicoSelecionados == null ){
				httpServletRequest.setAttribute("colecaoTipoServico",
						colecaoTipoServico);
			}else{
				for (Iterator iteratorTipoServico = colecaoTipoServico.iterator(); iteratorTipoServico.hasNext();){
			
					ServicoTipo servicoTipo = (ServicoTipo) iteratorTipoServico.next();
					for (Iterator iteratorTipoServicoSelecionados = colecaoTipoServicoSelecionados.iterator(); 
							iteratorTipoServicoSelecionados.hasNext();){
						
						ServicoTipo servicoTipoSelecionado = (ServicoTipo) iteratorTipoServicoSelecionados.next();
						
						if(servicoTipo.getId().compareTo(servicoTipoSelecionado.getId()) == 0){
							iteratorTipoServico.remove();
						}
					}
				}

				httpServletRequest.setAttribute("colecaoTipoServico", colecaoTipoServico);
			}
		}
	}

	/**
	 * Seta os request com os id encontrados
	 * 
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			GerarRelatorioAcompanhamentoExecucaoOSActionForm form) {

		// Unidade Atendimento
		if (form.getIdUnidadeAtendimento() != null
				&& !form.getIdUnidadeAtendimento().equals("")
				&& form.getDescricaoUnidadeAtendimento() != null
				&& !form.getDescricaoUnidadeAtendimento().equals("")) {

			httpServletRequest.setAttribute("unidadeAtendimentoEncontrada",
					"true");
		}

		// Unidade Atual
		if (form.getIdUnidadeAtual() != null
				&& !form.getIdUnidadeAtual().equals("")
				&& form.getDescricaoUnidadeAtual() != null
				&& !form.getDescricaoUnidadeAtual().equals("")) {

			httpServletRequest.setAttribute("unidadeAtualEncontrada", "true");
		}

		// Unidade Encerramento
		if (form.getIdUnidadeEncerramento() != null
				&& !form.getIdUnidadeEncerramento().equals("")
				&& form.getDescricaoUnidadeEncerramento() != null
				&& !form.getDescricaoUnidadeEncerramento().equals("")) {

			httpServletRequest.setAttribute("unidadeEncerramentoEncontrada",
					"true");
		}

		// Equipe Programa��o
		if (form.getIdEquipeProgramacao() != null
				&& !form.getIdEquipeProgramacao().equals("")
				&& form.getDescricaoEquipeProgramacao() != null
				&& !form.getDescricaoEquipeProgramacao().equals("")) {

			httpServletRequest.setAttribute("equipeProgramacaoEncontrada",
					"true");
		}

		// Equipe Execu��o
		if (form.getIdEquipeExecucao() != null
				&& !form.getIdEquipeExecucao().equals("")
				&& form.getDescricaoEquipeExecucao() != null
				&& !form.getDescricaoEquipeExecucao().equals("")) {

			httpServletRequest.setAttribute("equipeExecucaoEncontrada", "true");
		}

	}

}
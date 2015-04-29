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
package gsan.gui.atendimentopublico.registroatendimento;

import gsan.atendimentopublico.registroatendimento.ArquivoProcedimentoOperacionalPadrao;
import gsan.atendimentopublico.registroatendimento.FiltroArquivoProcedimentoOperacionalPadrao;
import gsan.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gsan.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoGrupo;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo;
import gsan.atendimentopublico.registroatendimento.bean.SolicitacaoEspecificacaoHelper;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

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
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 07/11/2006
 */
public class ExibirAtualizarTipoSolicitacaoEspecificacaoAction extends
		GcomAction {
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

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarTipoSolicitacaoEspecificacao");

		AtualizarTipoSolicitacaoEspecificacaoActionForm atualizarTipoSolicitacaoEspecificacaoActionForm = (AtualizarTipoSolicitacaoEspecificacaoActionForm) actionForm;

		String id = null;

		String idSolicitacaoTipo = null;

		if (httpServletRequest.getParameter("idTipoSolicitacao") != null
				&& !httpServletRequest.getParameter("idTipoSolicitacao")
						.equals("")) {

			if (sessao.getAttribute("adicionar") != null) {

				sessao.removeAttribute("objetoTipoSolicitacao");
				sessao.removeAttribute("adicionar");

			} else {
				sessao.removeAttribute("objetoTipoSolicitacao");
				sessao.removeAttribute("colecaoTipoSolicitacao");
			}

		}

		// Verifica se veio do filtrar ou do manter

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		Fachada fachada = Fachada.getInstancia();
		
		// Verificar as permiss�o especial para alterar o indicador de uso do sistema 
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		boolean temPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao = fachada
				.verificarPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao(usuario);
		if (temPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao) {
			httpServletRequest.setAttribute("temPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao",
				temPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao);
		}

		FiltroSolicitacaoTipoGrupo filtroSolicitacaoTipoGrupo = new FiltroSolicitacaoTipoGrupo();
		filtroSolicitacaoTipoGrupo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoGrupo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoSolicitacaoTipoGrupo = fachada.pesquisar(
				filtroSolicitacaoTipoGrupo, SolicitacaoTipoGrupo.class
						.getName());
		httpServletRequest.setAttribute("colecaoSolicitacaoTipoGrupo",
				colecaoSolicitacaoTipoGrupo);

		// Verifica se o servicoCobrancaValor j� est� na sess�o, em caso
		// afirmativo
		// significa que o usu�rio j� entrou na tela e apenas selecionou algum
		// item que deu um reload na tela e em caso negativo significa que ele
		// est� entrando pela primeira vez
		Collection colecaoSolicitacaoTipoEspecificacao = null;
		
		if (sessao.getAttribute("colecaoTipoSolicitacaoTela") == null) {

			if (sessao.getAttribute("objetoSolicitacaoTipo") != null) {

				SolicitacaoTipo solicitacaoTipo = (SolicitacaoTipo) sessao
						.getAttribute("objetoSolicitacaoTipo");

				sessao.setAttribute("idTipoSolicitacao", solicitacaoTipo
						.getId().toString());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIdTipoSolicitacao(solicitacaoTipo.getId()
								.toString());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setDescricao(solicitacaoTipo.getDescricao());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIdgrupoTipoSolicitacao(solicitacaoTipo
								.getSolicitacaoTipoGrupo().getId().toString());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorFaltaAgua(""
								+ solicitacaoTipo.getIndicadorFaltaAgua());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorTarifaSocial(""
								+ solicitacaoTipo.getIndicadorTarifaSocial());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorUso("" + solicitacaoTipo.getIndicadorUso());
				
				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorUsoSistema(""
								+ solicitacaoTipo.getIndicadorUsoSistema());

				id = solicitacaoTipo.getId().toString();

				sessao.setAttribute("idSolicitacaoTipo", solicitacaoTipo
						.getId().toString());

				sessao
						.setAttribute("solicitacaoTipoAtualizar",
								solicitacaoTipo);
				sessao.removeAttribute("objetoSolicitacaoTipo");

				/*
				 * Faz o filtro pesquisando o tipo de especifica��o da
				 * solicita��o
				 */
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,
								solicitacaoTipo.getId().toString()));

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipo");
				
				filtroSolicitacaoTipoEspecificacao
				.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("especificacaoImovelSituacao");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("especificacaoServicoTipos");
				
				filtroSolicitacaoTipoEspecificacao
				.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ESPECIFICACAO_NOVO_RA);

				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,
								solicitacaoTipo.getId().toString()));

				filtroSolicitacaoTipoEspecificacao
				.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO);
				
				colecaoSolicitacaoTipoEspecificacao = fachada
						.pesquisar(filtroSolicitacaoTipoEspecificacao,
								SolicitacaoTipoEspecificacao.class.getName());
				
								
				if (colecaoSolicitacaoTipoEspecificacao == null
						|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
					colecaoSolicitacaoTipoEspecificacao = new ArrayList();

				}

				sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
						colecaoSolicitacaoTipoEspecificacao);

			} else {

				SolicitacaoTipo solicitacaoTipo = null;

				// idSolicitacaoTipo = null;

				if (httpServletRequest.getParameter("idTipoSolicitacao") == null
						|| httpServletRequest.getParameter("idTipoSolicitacao")
								.equals("")) {
					solicitacaoTipo = (SolicitacaoTipo) sessao
							.getAttribute("objetoSolicitacaoTipo");
				} else {
					idSolicitacaoTipo = (String) httpServletRequest
							.getParameter("idTipoSolicitacao");
					sessao.setAttribute("idTipoSolicitacao", idSolicitacaoTipo);
				}

				httpServletRequest.setAttribute("idTipoSolicitacao",
						idSolicitacaoTipo);

				if (idSolicitacaoTipo != null) {

					id = idSolicitacaoTipo;

					FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();

					filtroSolicitacaoTipo
							.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoGrupo");

					filtroSolicitacaoTipo
							.adicionarParametro(new ParametroSimples(
									FiltroSolicitacaoTipo.ID, idSolicitacaoTipo));

					Collection<SolicitacaoTipo> colecaoSolicitacaoTipo = fachada
							.pesquisar(filtroSolicitacaoTipo,
									SolicitacaoTipo.class.getName());

					if (colecaoSolicitacaoTipo == null
							|| colecaoSolicitacaoTipo.isEmpty()) {
						throw new ActionServletException(
								"atencao.atualizacao.timestamp");
					}

					httpServletRequest.setAttribute("colecaoSolicitacaoTipo",
							colecaoSolicitacaoTipo);

					solicitacaoTipo = (SolicitacaoTipo) colecaoSolicitacaoTipo
							.iterator().next();

				}

				if (solicitacaoTipo == null) {

					FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
					filtroSolicitacaoTipo
							.adicionarParametro(new ParametroSimples(
									FiltroSolicitacaoTipo.ID, sessao
											.getAttribute("idTipoSolicitacao")));

					Collection colecaoSolicitacaoTipo = fachada.pesquisar(
							filtroSolicitacaoTipo, SolicitacaoTipo.class
									.getName());

					solicitacaoTipo = (SolicitacaoTipo) colecaoSolicitacaoTipo
							.iterator().next();

				}

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIdTipoSolicitacao(solicitacaoTipo.getId()
								.toString());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setDescricao(solicitacaoTipo.getDescricao());

				if (solicitacaoTipo.getSolicitacaoTipoGrupo() != null) {
					atualizarTipoSolicitacaoEspecificacaoActionForm
							.setIdgrupoTipoSolicitacao(solicitacaoTipo
									.getSolicitacaoTipoGrupo().getId()
									.toString());
				} else {
					atualizarTipoSolicitacaoEspecificacaoActionForm
							.setIdgrupoTipoSolicitacao("");
				}

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorFaltaAgua(""
								+ solicitacaoTipo.getIndicadorFaltaAgua());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorTarifaSocial(""
								+ solicitacaoTipo.getIndicadorTarifaSocial());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorUso("" + solicitacaoTipo.getIndicadorUso());
				
				atualizarTipoSolicitacaoEspecificacaoActionForm
				.setIndicadorUsoSistema(""
						+ solicitacaoTipo.getIndicadorUsoSistema());

				sessao.setAttribute("idSolicitacaoTipo", solicitacaoTipo
						.getId().toString());

				sessao
						.setAttribute("solicitacaoTipoAtualizar",
								solicitacaoTipo);

				colecaoSolicitacaoTipoEspecificacao = (Collection) sessao
						.getAttribute("colecaoSolicitacaoTipoEspecificacao");

				if (colecaoSolicitacaoTipoEspecificacao == null
						|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {

					/*
					 * Faz o filtro pesquisando o tipo de especifica��o da
					 * solicita��o
					 */
					FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
					filtroSolicitacaoTipoEspecificacao
							.adicionarParametro(new ParametroSimples(
									FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,
									solicitacaoTipo.getId().toString()));

					filtroSolicitacaoTipoEspecificacao
							.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipo");
					
					filtroSolicitacaoTipoEspecificacao
					.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

					filtroSolicitacaoTipoEspecificacao
							.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");

					filtroSolicitacaoTipoEspecificacao
							.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");

					filtroSolicitacaoTipoEspecificacao
							.adicionarCaminhoParaCarregamentoEntidade("especificacaoImovelSituacao");

					filtroSolicitacaoTipoEspecificacao
							.adicionarCaminhoParaCarregamentoEntidade("especificacaoServicoTipos");
                    
                    filtroSolicitacaoTipoEspecificacao
                        .adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacaoNovoRA.solicitacaoTipo");

					filtroSolicitacaoTipoEspecificacao
							.adicionarParametro(new ParametroSimples(
									FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,
									solicitacaoTipo.getId().toString()));

					colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
							filtroSolicitacaoTipoEspecificacao,
							SolicitacaoTipoEspecificacao.class.getName());

					if (colecaoSolicitacaoTipoEspecificacao == null
							|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
						colecaoSolicitacaoTipoEspecificacao = new ArrayList();

					}

					sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
							colecaoSolicitacaoTipoEspecificacao);

				}
			}
			
			//RM 5924 Adicionar arquivo de procedimentos operacionais padr�es POPs
			//Am�lia Pessoa - 15/12/2011
			String abrir = (String) sessao.getAttribute("abrir"); 
			if (abrir!=null){
				Collection<SolicitacaoTipoEspecificacao> colecao = colecaoSolicitacaoTipoEspecificacao;
				Collection<ArquivoProcedimentoOperacionalPadrao> colecaoPOP=null;
				FiltroArquivoProcedimentoOperacionalPadrao filtro = null;
				SolicitacaoEspecificacaoHelper helper;
				Collection<SolicitacaoEspecificacaoHelper> colecaoHelpers = new ArrayList<SolicitacaoEspecificacaoHelper>();
				int indice=0;
				for (SolicitacaoTipoEspecificacao ste : colecao){
					filtro = (FiltroArquivoProcedimentoOperacionalPadrao) (new ArquivoProcedimentoOperacionalPadrao()).retornaFiltro();
					filtro.limparListaParametros();
					filtro.adicionarParametro(new ParametroSimples(FiltroArquivoProcedimentoOperacionalPadrao.SOLICITACAO_TIPO_ESPECIFICACAO_ID, ste.getId()));
					colecaoPOP = fachada.pesquisar(filtro,ArquivoProcedimentoOperacionalPadrao.class.getName());
					if (colecaoPOP.size()>0){
					indice=0;
					for (ArquivoProcedimentoOperacionalPadrao pop:colecaoPOP){
						pop.setPosicao(indice++);
					}
					helper = new SolicitacaoEspecificacaoHelper();
					helper.setIdHelper(ste.getId());
					helper.setColecaoArquivos(colecaoPOP);
					colecaoHelpers.add(helper);
					//ste.setColecaoArquivosPOP(colecaoArquivosPOP)
					} 
				}
				sessao.removeAttribute("abrir");
				sessao.setAttribute("colecaoHelpers", colecaoHelpers);
			}
		}

		// Verifica se o usu�rio removeu um componente e em caso afirmativo
		// remove o componente da cole��o
		if (httpServletRequest.getParameter("deleteComponente") != null
				&& !httpServletRequest.getParameter("deleteComponente").equals(
						"")) {

			Collection colecaoSolicitacaoTipoEspecificacaoRemovidos = null;

			colecaoSolicitacaoTipoEspecificacao = (Collection) sessao
					.getAttribute("colecaoSolicitacaoTipoEspecificacao");

			if (colecaoSolicitacaoTipoEspecificacao != null
					&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {

				int posicaoComponente = new Integer(httpServletRequest
						.getParameter("deleteComponente")).intValue();

				int index = 0;

				Iterator colecaoSolicitacaoTipoEspecificacaoIterator = colecaoSolicitacaoTipoEspecificacao
						.iterator();

				while (colecaoSolicitacaoTipoEspecificacaoIterator.hasNext()) {

					index++;

					SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) colecaoSolicitacaoTipoEspecificacaoIterator
							.next();

					if (index == posicaoComponente) {

						if (solicitacaoTipoEspecificacao.getId() != null) {

							if (sessao
									.getAttribute("colecaoSolicitacaoTipoEspecificacaoRemovidos") == null
									|| Util.isVazioOrNulo(colecaoSolicitacaoTipoEspecificacaoRemovidos)) {
								
								colecaoSolicitacaoTipoEspecificacaoRemovidos = new ArrayList();
							} else {
								colecaoSolicitacaoTipoEspecificacaoRemovidos = (Collection) sessao
										.getAttribute("colecaoSolicitacaoTipoEspecificacaoRemovidos");
							}

							colecaoSolicitacaoTipoEspecificacaoRemovidos
									.add(solicitacaoTipoEspecificacao);
							sessao
									.setAttribute(
											"colecaoSolicitacaoTipoEspecificacaoRemovidos",
											colecaoSolicitacaoTipoEspecificacaoRemovidos);
						}

						colecaoSolicitacaoTipoEspecificacao
								.remove(solicitacaoTipoEspecificacao);

						atualizarTipoSolicitacaoEspecificacaoActionForm
								.setTamanhoColecao(""
										+ colecaoSolicitacaoTipoEspecificacao
												.size());

						sessao.setAttribute(
								"colecaoSolicitacaoTipoEspecificacao",
								colecaoSolicitacaoTipoEspecificacao);

						break;
					}
				}
			}
		}

		// -------------- bt DESFAZER ---------------

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			sessao.removeAttribute("colecaoSolicitacaoTipoTela");

			String solicitacaoTipoID = null;

			if (httpServletRequest.getParameter("idTipoSolicitacao") == null
					|| httpServletRequest.getParameter("idTipoSolicitacao")
							.equals("")) {
				solicitacaoTipoID = (String) sessao
						.getAttribute("idTipoSolicitacao");
			} else {
				solicitacaoTipoID = (String) httpServletRequest
						.getParameter("idTipoSolicitacao");
				sessao.setAttribute("idTipoSolicitacao", solicitacaoTipoID);
			}

			if (solicitacaoTipoID.equalsIgnoreCase("")) {
				solicitacaoTipoID = null;
			}

			if ((solicitacaoTipoID == null) && (id == null)) {

				SolicitacaoTipo solicitacaoTipo = (SolicitacaoTipo) sessao
						.getAttribute("objetoSolicitacaoTipo");

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIdTipoSolicitacao(solicitacaoTipo.getId()
								.toString());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setDescricao(solicitacaoTipo.getDescricao());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorFaltaAgua(""
								+ solicitacaoTipo.getIndicadorFaltaAgua());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorTarifaSocial(""
								+ solicitacaoTipo.getIndicadorTarifaSocial());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorUso("" + solicitacaoTipo.getIndicadorUso());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIdgrupoTipoSolicitacao(solicitacaoTipo
								.getSolicitacaoTipoGrupo().getId().toString());

				sessao.setAttribute("idSolicitacaoTipo", solicitacaoTipo
						.getId().toString());

				sessao
						.setAttribute("solicitacaoTipoAtualizar",
								solicitacaoTipo);
				sessao.removeAttribute("solicitacaoTipo");

				/*
				 * Faz o filtro pesquisando o tipo de especifica��o da
				 * solicita��o
				 */
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,
								solicitacaoTipo.getId().toString()));

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipo");
				
				filtroSolicitacaoTipoEspecificacao
				.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("especificacaoImovelSituacao");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("especificacaoServicoTipos");
				
				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ESPECIFICACAO_NOVO_RA);

				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,
								solicitacaoTipo.getId().toString()));

				colecaoSolicitacaoTipoEspecificacao = fachada
						.pesquisar(filtroSolicitacaoTipoEspecificacao,
								SolicitacaoTipoEspecificacao.class.getName());

				if (colecaoSolicitacaoTipoEspecificacao == null
						|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
					colecaoSolicitacaoTipoEspecificacao = new ArrayList();

				}

				sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
						colecaoSolicitacaoTipoEspecificacao);

			}

			if ((solicitacaoTipoID == null) && (id != null)) {

				solicitacaoTipoID = id;
			}

			if (solicitacaoTipoID != null) {

				FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();

				filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
						FiltroSolicitacaoTipo.ID, solicitacaoTipoID));

				Collection<SolicitacaoTipo> colecaoSolicitacaoTipo = fachada
						.pesquisar(filtroSolicitacaoTipo, SolicitacaoTipo.class
								.getName());

				if (colecaoSolicitacaoTipo == null
						|| colecaoSolicitacaoTipo.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				httpServletRequest.setAttribute("colecaoSolicitacaoTipo",
						colecaoSolicitacaoTipo);

				SolicitacaoTipo solicitacaoTipo = (SolicitacaoTipo) colecaoSolicitacaoTipo
						.iterator().next();

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIdTipoSolicitacao(solicitacaoTipo.getId()
								.toString());

				sessao.setAttribute("idSolicitacaoTipo", solicitacaoTipo
						.getId().toString());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIdgrupoTipoSolicitacao(solicitacaoTipo
								.getSolicitacaoTipoGrupo().getId().toString());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setDescricao(solicitacaoTipo.getDescricao());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorFaltaAgua(""
								+ solicitacaoTipo.getIndicadorFaltaAgua());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorTarifaSocial(""
								+ solicitacaoTipo.getIndicadorTarifaSocial());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorUso("" + solicitacaoTipo.getIndicadorUso());
				
				atualizarTipoSolicitacaoEspecificacaoActionForm
				.setIndicadorUsoSistema(""
						+ solicitacaoTipo.getIndicadorUsoSistema());

				httpServletRequest.setAttribute("idSolicitacaoTipo",
						solicitacaoTipoID);
				sessao
						.setAttribute("solicitacaoTipoAtualizar",
								solicitacaoTipo);

				// if
				// (sessao.getAttribute("colecaoSolicitacaoTipoEspecificacao")
				// == null) {

				/*
				 * Faz o filtro pesquisando o tipo de especifica��o da
				 * solicita��o
				 */
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,
								solicitacaoTipo.getId().toString()));

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipo");
				
				filtroSolicitacaoTipoEspecificacao
				.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("especificacaoImovelSituacao");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("especificacaoServicoTipos");
				
				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ESPECIFICACAO_NOVO_RA);

				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,
								solicitacaoTipo.getId().toString()));

				colecaoSolicitacaoTipoEspecificacao = fachada
						.pesquisar(filtroSolicitacaoTipoEspecificacao,
								SolicitacaoTipoEspecificacao.class.getName());

				if (colecaoSolicitacaoTipoEspecificacao == null
						|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
					colecaoSolicitacaoTipoEspecificacao = new ArrayList();

				}

				sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
						colecaoSolicitacaoTipoEspecificacao);

				// }
			}
		}
		// -------------- bt DESFAZER ---------------

		httpServletRequest.setAttribute("colecaoSolicitacaoTipoTela", sessao
				.getAttribute("colecaoSolicitacaoTipoTela"));

		return retorno;
	}

}
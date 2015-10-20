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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Quadra;import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ConectorAnd;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
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
 * @author R�mulo Aur�lio
 * @date 20/03/2007
 */
public class ExibirAlterarSituacaoLigacaoAction extends GcomAction {
	/**
	 * [UC0555] Alterar Situacao da Ligacao
	 * 
	 * Este caso de uso permite alterar a situacao da ligacao de agua e/ou
	 * esgoto de acordo com o indicadorde rede e Ordem de Servico gerada.
	 * 
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

		ActionForward retorno = actionMapping
				.findForward("alterarSituacaoLigacao");

		AlterarSituacaoLigacaoActionForm form = (AlterarSituacaoLigacaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario usuarioLogado = (Usuario)
		// sessao.getAttribute("usuarioLogado");

		String indicadorRedeAgua = null;

		String indicadorRedeEsgoto = null;

		String idOrdemServico = null;

		idOrdemServico = form.getIdOrdemServico();

		this.pesquisarSelectObrigatorio(httpServletRequest, form,
				indicadorRedeAgua, indicadorRedeEsgoto);

		/*
		 * // Verifica se o id da Ordem de servico vem da sessao. if
		 * (sessao.getAttribute("idOrdemServico") != null) { idOrdemServico =
		 * (String) sessao.getAttribute("idOrdemServico"); } else {
		 * idOrdemServico = efetuarSupressaoLigacaoAguaActionForm
		 * .getIdOrdemServico(); }
		 */

		OrdemServico ordemServico = null;

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			ordemServico = fachada.recuperaOSPorId(new Integer(idOrdemServico));

			if (ordemServico != null) {
				 boolean  veioEncerrarOS = false;
				
				 fachada.validarOrdemServicoAlterarSituacaoLigacao(ordemServico,
				 veioEncerrarOS);
				 

				form.setIdOrdemServico(idOrdemServico);

				form.setNomeOrdemServico(ordemServico.getServicoTipo()
						.getDescricao());

				sessao.setAttribute("ordemServico", ordemServico);

				// Comentado por Raphael Rossiter em 28/02/2007
				// Imovel imovel =
				// ordemServico.getRegistroAtendimento().getImovel();
				Imovel imovel = ordemServico.getImovel();

				String matriculaImovel = imovel.getId().toString();
				form.setMatriculaImovel("" + matriculaImovel);

				/*-------------- In�cio dados do Im�vel---------------*/

				// Comentado por Raphael Rossiter em 28/02/2007
				// sessao.setAttribute("imovel",
				// ordemServico.getRegistroAtendimento().getImovel());
				sessao.setAttribute("imovel", ordemServico.getImovel());

				if (imovel != null) {

					// Matricula Im�vel
					form.setMatriculaImovel(imovel.getId().toString());

					// Inscri��o Im�vel
					String inscricaoImovel = fachada
							.pesquisarInscricaoImovel(imovel.getId());
					form.setInscricaoImovel(inscricaoImovel);

					// Situa��o da Liga��o de Agua

					String situacaoLigacaoAgua = imovel
							.getLigacaoAguaSituacao().getDescricao();
					form.setSituacaoLigacaoAguaAtual(situacaoLigacaoAgua);
					/*
					 * [SB0004] - Obter Indicador da Rede de �gua e Esgoto
					 * 
					 * Integra��o com o conceito de face da quadra
					 * Raphael Rossiter em 22/05/2009
					 */
					IntegracaoQuadraFaceHelper integracao = fachada.integracaoQuadraFace(imovel.getId());
					
					indicadorRedeAgua = integracao.getIndicadorRedeAgua().toString();

					// Situa��o da Liga��o de Esgoto
					String situacaoLigacaoEsgoto = imovel
							.getLigacaoEsgotoSituacao().getDescricao();
					form.setSituacaoLigacaoEsgotoAtual(situacaoLigacaoEsgoto);
					/*
					 * [SB0004] - Obter Indicador da Rede de �gua e Esgoto
					 * 
					 * Integra��o com o conceito de face da quadra
					 * Raphael Rossiter em 22/05/2009
					 */
					indicadorRedeEsgoto = integracao.getIndicadorRedeEsgoto().toString();

					this.pesquisarSelectObrigatorio(httpServletRequest, form,
							indicadorRedeAgua, indicadorRedeEsgoto);

					this.pesquisarCliente(form, new Integer(matriculaImovel));
				}

			} else {

				httpServletRequest
						.setAttribute("OrdemServicoInexistente", true);
				form.setIdOrdemServico("");
				form.setNomeOrdemServico("ORDEM DE SERVI�O INEXISTENTE");

			}
		}

		return retorno;
	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Pinto
	 * @date 22/08/2006
	 */
	private void pesquisarCliente(AlterarSituacaoLigacaoActionForm form,
			Integer matriculaImovel) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, matriculaImovel));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
				new Integer(ClienteRelacaoTipo.USUARIO)));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.DATA_FIM_RELACAO));

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoClienteImovel = Fachada.getInstancia().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());

		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel
					.iterator().next();

			Cliente cliente = clienteImovel.getCliente();

			String documento = "";

			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				documento = cliente.getCpfFormatado();
			} else {
				documento = cliente.getCnpjFormatado();
			}
			// Cliente Nome/CPF-CNPJ
			form.setClienteUsuario(cliente.getNome());
			form.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}

	// Dados da Gera��o de D�bito
	/*
	 * private void pesquisarDadosGeracaoDebito(
	 * EfetuarSupressaoLigacaoAguaActionForm
	 * efetuarSupressaoLigacaoAguaActionForm, OrdemServico ordemServico) { }
	 */

	/**
	 * Pesquisa o local de instala��o Pesquisa hidrometro prote��o
	 */
	private void pesquisarSelectObrigatorio(
			HttpServletRequest httpServletRequest,
			AlterarSituacaoLigacaoActionForm form, String indicadorRedeAgua,
			String indicadorRedeEsgoto) {

		// Mudar isso quando tiver esquema de seguran�a
		// HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// ************ Tipo Supressao***************

		// Vericacao dequal o tipo de supressao informada
		// pelo usuario para
		// habilitar no combo box as opcoes correspondentes

		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();

		httpServletRequest.setAttribute("colecaoLigacaoAguaSituacao",
				new ArrayList());

		httpServletRequest.setAttribute("colecaoLigacaoEsgotoSituacao",
				new ArrayList());

		if (form.getIndicadorTipoLigacao() != null) {

			if (form.getIndicadorTipoLigacao().equals("1")
					|| form.getIndicadorTipoLigacao().equals("3")) {
				if (indicadorRedeAgua != null) {

					// Combo vira obrigatorio
					httpServletRequest.setAttribute("comboLigacaoAgua", "sim");

					if (indicadorRedeAgua
							.equalsIgnoreCase("" + Quadra.SEM_REDE)) {

						filtroLigacaoAguaSituacao
								.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);

						filtroLigacaoAguaSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoAguaSituacao.ID,
										LigacaoAguaSituacao.POTENCIAL));

						Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = fachada
								.pesquisar(filtroLigacaoAguaSituacao,
										LigacaoAguaSituacao.class.getName());

						if (colecaoLigacaoAguaSituacao == null
								|| colecaoLigacaoAguaSituacao.isEmpty()) {
							throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",
									null, "Ligacao Agua Situacao ");
						}

						// form.setIndicadorTipoLigacao("1");
						httpServletRequest.setAttribute(
								"colecaoLigacaoAguaSituacao",
								colecaoLigacaoAguaSituacao);

					}
					if (indicadorRedeAgua
							.equalsIgnoreCase("" + Quadra.COM_REDE)) {

						filtroLigacaoAguaSituacao
								.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);

						filtroLigacaoAguaSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoAguaSituacao.ID,
										LigacaoAguaSituacao.FACTIVEL));
						
						filtroLigacaoAguaSituacao
								.adicionarParametro( new ParametroSimples(
										FiltroLigacaoAguaSituacao.INDICADOR_FATURAMENTO,
										ConstantesSistema.NAO ) );						

						Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = fachada
								.pesquisar(filtroLigacaoAguaSituacao,
										LigacaoAguaSituacao.class.getName());

						if (colecaoLigacaoAguaSituacao == null
								|| colecaoLigacaoAguaSituacao.isEmpty()) {
							throw new ActionServletException(
									"atencao.entidade_sem_dados_para_selecao",
									null, "Ligacao Agua Situacao ");
						}

						// form.setIndicadorTipoLigacao("1");
						httpServletRequest.setAttribute(
								"colecaoLigacaoAguaSituacao",
								colecaoLigacaoAguaSituacao);

					}
					if (indicadorRedeAgua.equalsIgnoreCase(""
							+ Quadra.REDE_PARCIAL)) {

						filtroLigacaoAguaSituacao
								.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);

						filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
								FiltroLigacaoAguaSituacao.ID,
								LigacaoAguaSituacao.POTENCIAL,
							    ConectorOr.CONECTOR_OR ));
						
						filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
								FiltroLigacaoAguaSituacao.ID,
								LigacaoAguaSituacao.FACTIVEL,
							    ConectorAnd.CONECTOR_AND, 2));
						
						filtroLigacaoAguaSituacao
						.adicionarParametro( new ParametroSimples(
								FiltroLigacaoAguaSituacao.INDICADOR_FATURAMENTO,
								ConstantesSistema.NAO ) );							

						Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = fachada
								.pesquisar(filtroLigacaoAguaSituacao,
										LigacaoAguaSituacao.class.getName());

						if (colecaoLigacaoAguaSituacao == null
								|| colecaoLigacaoAguaSituacao.isEmpty()) {
							throw new ActionServletException(
									"atencao.entidade_sem_dados_para_selecao",
									null, "Tabela Ligacao Agua Situacao ");
						}

						// form.setIndicadorTipoLigacao("1");
						httpServletRequest.setAttribute(
								"colecaoLigacaoAguaSituacao",
								colecaoLigacaoAguaSituacao);

					}
				}
			}

			if (form.getIndicadorTipoLigacao().equals("2")
					|| form.getIndicadorTipoLigacao().equals("3")) {
				if (indicadorRedeEsgoto != null) {

					// Combo vira obrigatorio
					httpServletRequest
							.setAttribute("comboLigacaoEsgoto", "sim");

					if (indicadorRedeEsgoto.equalsIgnoreCase(""
							+ Quadra.SEM_REDE)) {

						filtroLigacaoEsgotoSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoEsgotoSituacao.ID,
										LigacaoEsgotoSituacao.POTENCIAL));

						Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = fachada
								.pesquisar(filtroLigacaoEsgotoSituacao,
										LigacaoEsgotoSituacao.class.getName());

						if (colecaoLigacaoEsgotoSituacao == null
								|| colecaoLigacaoEsgotoSituacao.isEmpty()) {
							throw new ActionServletException(
									"atencao.entidade_sem_dados_para_selecao",
									null, "Ligacao Esgoto Situacao");
						}
						// form.setIndicadorTipoLigacao("2");
						httpServletRequest.setAttribute(
								"colecaoLigacaoEsgotoSituacao",
								colecaoLigacaoEsgotoSituacao);

					}

					if (indicadorRedeEsgoto.equalsIgnoreCase(""
							+ Quadra.COM_REDE)) {

						filtroLigacaoEsgotoSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoEsgotoSituacao.ID,
										LigacaoEsgotoSituacao.FACTIVEL));
						
						filtroLigacaoEsgotoSituacao
						.adicionarParametro( new ParametroSimples(
								FiltroLigacaoEsgotoSituacao.INDICADORFATURAMENTOSITUACAO,
								ConstantesSistema.NAO ) );							

						Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = fachada
								.pesquisar(filtroLigacaoEsgotoSituacao,
										LigacaoEsgotoSituacao.class.getName());

						if (colecaoLigacaoEsgotoSituacao == null
								|| colecaoLigacaoEsgotoSituacao.isEmpty()) {
							throw new ActionServletException(
									"atencao.entidade_sem_dados_para_selecao",
									null, "Ligacao Esgoto Situacao");
						}
						// form.setIndicadorTipoLigacao("2");
						httpServletRequest.setAttribute(
								"colecaoLigacaoEsgotoSituacao",
								colecaoLigacaoEsgotoSituacao);

					}

					if (indicadorRedeEsgoto.equalsIgnoreCase(""
							+ Quadra.REDE_PARCIAL)) {
						
						filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
								FiltroLigacaoEsgotoSituacao.ID,
								LigacaoEsgotoSituacao.POTENCIAL,
							    ConectorOr.CONECTOR_OR ));
						
						filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
								FiltroLigacaoEsgotoSituacao.ID,
								LigacaoEsgotoSituacao.FACTIVEL,
								ConectorAnd.CONECTOR_AND, 2));
						
						filtroLigacaoEsgotoSituacao
						.adicionarParametro( new ParametroSimples(
								FiltroLigacaoEsgotoSituacao.INDICADORFATURAMENTOSITUACAO,
								ConstantesSistema.NAO));
						Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = fachada
								.pesquisar(filtroLigacaoEsgotoSituacao,
										LigacaoEsgotoSituacao.class.getName());

						if (colecaoLigacaoEsgotoSituacao == null
								|| colecaoLigacaoEsgotoSituacao.isEmpty()) {
							throw new ActionServletException(
									"atencao.entidade_sem_dados_para_selecao",
									null, "Ligacao Esgoto Situacao");
						}
						// form.setIndicadorTipoLigacao("2");
						httpServletRequest.setAttribute(
								"colecaoLigacaoEsgotoSituacao",
								colecaoLigacaoEsgotoSituacao);

					}
				}

			}
		}
	}
}

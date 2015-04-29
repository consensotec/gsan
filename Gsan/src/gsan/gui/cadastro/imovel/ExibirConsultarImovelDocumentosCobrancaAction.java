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
package gsan.gui.cadastro.imovel;

import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.bean.ObterDescricaoSituacaoOSHelper;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaDocumento;
import gsan.cobranca.bean.CobrancaDocumentoHelper;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 8� Aba - Dopcumento de Cobran�a e ordens de Servi�oi de Cobran�a Emitidos
 * para o Im�vel
 * 
 * @author Rafael Santos
 * @since 05/09/2006
 */
public class ExibirConsultarImovelDocumentosCobrancaAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("consultarImovelDocumentosCobranca");

		// Obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;
		
		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// id do imovel da aba documento de cobranca
		String idImovelDocumentosCobranca = consultarImovelActionForm
				.getIdImovelDocumentosCobranca();
		String limparForm = httpServletRequest.getParameter("limparForm");
		String indicadorNovo = httpServletRequest.getParameter("indicadorNovo");
		String idImovelPrincipalAba = null;
		if (sessao.getAttribute("idImovelPrincipalAba") != null) {
			idImovelPrincipalAba = (String) sessao
					.getAttribute("idImovelPrincipalAba");
		}
		
		// pesquisar os par�metros do sistema
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		//Indicador Valida CPF/CNPJ
		if(sistemaParametro.getIndicadorValidaCpfCnpj() != null){
			consultarImovelActionForm.setIndicadorValidaCPFCNPJ(String.valueOf(sistemaParametro.getIndicadorValidaCpfCnpj()));
		}

		if (limparForm != null && !limparForm.equals("")) {
			// limpar os dados
			httpServletRequest.setAttribute(
					"idImovelDocumentosCobrancaNaoEncontrado", null);
			
			sessao.removeAttribute("imovelClientes");

			consultarImovelActionForm.setIdImovelDadosComplementares(null);
			consultarImovelActionForm.setIdImovelDadosCadastrais(null);
			consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
			consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
			consultarImovelActionForm.setIdImovelDebitos(null);
			consultarImovelActionForm.setIdImovelPagamentos(null);
			consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
			consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
			consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
			consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
			consultarImovelActionForm.setImovIdAnt(null);

			sessao.removeAttribute("imovelDocumentosCobranca");
			sessao.removeAttribute("colecaoDocumentoCobranca");
			sessao.removeAttribute("idImovelPrincipalAba");
			consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
			consultarImovelActionForm
					.setMatriculaImovelDocumentosCobranca(null);
			consultarImovelActionForm.setSituacaoAguaDocumentosCobranca(null);
			consultarImovelActionForm.setSituacaoEsgotoDocumentosCobranca(null);
			consultarImovelActionForm.setIndicadorValidaCPFCNPJ("2");
			consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado("2");
			
			// }else if(idImovelDocumentosCobranca != null &&
			// !idImovelDocumentosCobranca.equalsIgnoreCase("")){
		} else if ((idImovelDocumentosCobranca != null && !idImovelDocumentosCobranca
				.equalsIgnoreCase(""))
				|| (idImovelPrincipalAba != null && !idImovelPrincipalAba
						.equalsIgnoreCase(""))) {

			if (idImovelDocumentosCobranca != null
					&& !idImovelDocumentosCobranca.equalsIgnoreCase("")) {
				
			
				if (idImovelPrincipalAba != null
						&& !idImovelPrincipalAba.equalsIgnoreCase("")) {

					if (indicadorNovo != null && !indicadorNovo.equals("")) {
						consultarImovelActionForm
								.setIdImovelDocumentosCobranca(idImovelDocumentosCobranca);
						
					} else if (!(idImovelDocumentosCobranca
							.equals(idImovelPrincipalAba))) {
						consultarImovelActionForm
								.setIdImovelDocumentosCobranca(idImovelPrincipalAba);
						idImovelDocumentosCobranca = idImovelPrincipalAba;
					}

				}
			} else if (idImovelPrincipalAba != null
					&& !idImovelPrincipalAba.equalsIgnoreCase("")) {
				consultarImovelActionForm
						.setIdImovelRegistroAtendimento(idImovelPrincipalAba);
				idImovelDocumentosCobranca = idImovelPrincipalAba;
			}

			Imovel imovel = null;
			// verifica se o objeto imovel � o mesmo ja pesquisado, para n�o
			// precisar pesquisar mas.
			boolean imovelNovoPesquisado = false;
			if (sessao.getAttribute("imovelDocumentosCobranca") != null) {
				imovel = (Imovel) sessao
						.getAttribute("imovelDocumentosCobranca");
				if (!(imovel.getId().toString()
						.equals(idImovelDocumentosCobranca.trim()))) {
					imovel = fachada
							.consultarImovelHistoricoFaturamento(new Integer(
									idImovelDocumentosCobranca.trim()));
					imovelNovoPesquisado = true;
				}
			} else {
				imovel = fachada
						.consultarImovelHistoricoFaturamento(new Integer(
								idImovelDocumentosCobranca.trim()));
				imovelNovoPesquisado = true;
			}

			if (imovel != null) {
				sessao.setAttribute("imovelDocumentosCobranca", imovel);
				sessao.setAttribute("idImovelPrincipalAba", imovel.getId()
						.toString());
				consultarImovelActionForm.setIdImovelDocumentosCobranca(imovel
						.getId().toString());
				
				if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}
				
				Cliente clienteUsuario = fachada.pesquisarClienteUsuarioImovelExcluidoOuNao( imovel.getId() );
				
				if(clienteUsuario != null){
					consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado(String.valueOf(clienteUsuario.getIndicadorValidaCpfCnpj()));
				}else{
					consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado("2");
				}

				// caso o imovel pesquisado seja diferente do pesquisado
				// anterior ou seja a primeira vez que se esteja pesquisando
				if (imovelNovoPesquisado) {
					// seta na tela a inscri��o do imovel
					httpServletRequest.setAttribute(
							"idImovelDocumentosCobrancaNaoEncontrado", null);

					consultarImovelActionForm
							.setMatriculaImovelDocumentosCobranca(fachada
									.pesquisarInscricaoImovelExcluidoOuNao(new Integer(
											idImovelDocumentosCobranca.trim())));

					// seta a situa��o de agua
					if (imovel.getLigacaoAguaSituacao() != null) {
						consultarImovelActionForm
								.setSituacaoAguaDocumentosCobranca(imovel
										.getLigacaoAguaSituacao()
										.getDescricao());
					}
					// seta a situa��o de esgoto
					if (imovel.getLigacaoEsgotoSituacao() != null) {
						consultarImovelActionForm
								.setSituacaoEsgotoDocumentosCobranca(imovel
										.getLigacaoEsgotoSituacao()
										.getDescricao());
					}

					// 1� Passo - Pegar o total de registros atrav�s de um count
					// da consulta que aparecer� na tela
					// int totalRegistros = fachada
					// .consultarQuantidadeImovelDocumentosCobranca(new
					// Integer(idImovelDocumentosCobranca.trim()));

					// 2� Passo - Chamar a fun��o de Pagina��o passando o total
					// de registros
					// retorno = this.controlarPaginacao(httpServletRequest,
					// retorno,
					// totalRegistros);

					// 3� Passo - Obter a cole��o da consulta que aparecer� na
					// tela passando o numero de paginas
					// da pesquisa que est� no request
					Collection colecaoDocumentoCobranca = fachada
							.consultarImovelDocumentosCobranca(new Integer(
									idImovelDocumentosCobranca.trim()), 0);

					/*
					 * if (colecaoDocumentoCobranca == null ||
					 * colecaoDocumentoCobranca.isEmpty()) {
					 * httpServletRequest.setAttribute(
					 * "idImovelDocumentosCobrancaNaoEncontrado", null);
					 * 
					 * sessao.removeAttribute("imovelDocumentosCobranca");
					 * sessao.removeAttribute("colecaoDocumentoCobranca");
					 * sessao.removeAttribute("idImovelPrincipalAba");
					 * consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
					 * consultarImovelActionForm.setMatriculaImovelDocumentosCobranca(null);
					 * consultarImovelActionForm.setSituacaoAguaDocumentosCobranca(null);
					 * consultarImovelActionForm.setSituacaoEsgotoDocumentosCobranca(null); //
					 * [FS0010] Nenhum registro encontrado throw new
					 * ActionServletException(
					 * "atencao.pesquisa.nenhumresultado", null, ""); }
					 */

					if (colecaoDocumentoCobranca != null
							&& !colecaoDocumentoCobranca.isEmpty()) {

						Iterator colecaoDocumentoCobrancaIterator = colecaoDocumentoCobranca
								.iterator();
						CobrancaDocumentoHelper cobrancaDocumentoHelper = null;
						CobrancaDocumento cobrancaDocumento = null;
						Collection colecaoCobrancaDocumentoHelper = new ArrayList();

						while (colecaoDocumentoCobrancaIterator.hasNext()) {
							cobrancaDocumento = (CobrancaDocumento) colecaoDocumentoCobrancaIterator
									.next();
							cobrancaDocumentoHelper = new CobrancaDocumentoHelper();
							cobrancaDocumentoHelper
									.setCobrancaDocumento(cobrancaDocumento);

							Object[] dadosOrdemServico = fachada
									.pesquisarDadosOrdemServicoDocumentoCobranca(cobrancaDocumento
											.getId());
							if (dadosOrdemServico != null) {
								if (dadosOrdemServico[0] != null) {
									cobrancaDocumentoHelper
											.setIdOrdemServico((Integer) dadosOrdemServico[0]);
								}
								if (dadosOrdemServico[1] != null) {
									Short situacaoOS = (Short) dadosOrdemServico[1];
									if (situacaoOS
											.equals(OrdemServico.SITUACAO_PENDENTE)) {
										cobrancaDocumentoHelper
												.setSituacaoOrdemServico(OrdemServico.SITUACAO_DESCRICAO_PENDENTE);
									}
									if (situacaoOS
											.equals(OrdemServico.SITUACAO_ENCERRADO)) {
										ObterDescricaoSituacaoOSHelper obterDescricaoSituacaoOSHelper = fachada.obterDescricaoSituacaoOS((Integer) dadosOrdemServico[0]);
										cobrancaDocumentoHelper
												.setSituacaoOrdemServico(obterDescricaoSituacaoOSHelper.getDescricaoSituacao());
									}
									if (situacaoOS
											.equals(OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO)) {
										cobrancaDocumentoHelper
												.setSituacaoOrdemServico(OrdemServico.SITUACAO_DESC_ABREV_EXECUCAO_EM_ANDAMENTO);
									}
									if (situacaoOS
											.equals(OrdemServico.SITUACAO_AGUARDANDO_LIBERACAO)) {
										cobrancaDocumentoHelper
												.setSituacaoOrdemServico(OrdemServico.SITUACAO_DESC_ABREV_AGUARDANDO_LIBERACAO);
									}
								}
							}

							Integer quantidade = fachada
									.consultarQuantidadeImovelDocumentosItemCobranca(cobrancaDocumento
											.getId());

							cobrancaDocumentoHelper
									.setQuantidadeItensCobrancaDocumento(quantidade);

							colecaoCobrancaDocumentoHelper
									.add(cobrancaDocumentoHelper);
						}
						
						//Track No. 619 : Ordenar por data de emiss�o
						Collections.sort((List) colecaoCobrancaDocumentoHelper, new Comparator() {
							public int compare(Object a, Object b) {
								String data1 = ((CobrancaDocumentoHelper) a).getCobrancaDocumento().getEmissao().toString();
								String data2 = ((CobrancaDocumentoHelper) b).getCobrancaDocumento().getEmissao().toString();
								
								data1 = data1.substring(0, 4) + data1.substring(5, 7) + data1.substring(8, 10);
								data2 = data2.substring(0, 4) + data2.substring(5, 7) + data2.substring(8, 10);
								
								Integer dtEmissao1 = Integer.decode(data1);
								Integer dtEmissao2 = Integer.decode(data2);
								
								//String dtEmissao1 = ((CobrancaDocumentoHelper) a).getCobrancaDocumento().getEmissao().toString();
								//String dtEmissao2 = ((CobrancaDocumentoHelper) b).getCobrancaDocumento().getEmissao().toString();

								return dtEmissao2.compareTo(dtEmissao1);
							}
						});
						
						sessao.setAttribute("colecaoDocumentoCobranca",
								colecaoCobrancaDocumentoHelper);

					}else{
						sessao.removeAttribute("colecaoDocumentoCobranca");
					}
				}
			} else {
				httpServletRequest.setAttribute(
						"idImovelDocumentosCobrancaNaoEncontrado", "true");
				consultarImovelActionForm
						.setMatriculaImovelDocumentosCobranca("IM�VEL INEXISTENTE");

				// limpar os dados pesquisados
				sessao.removeAttribute("imovelDocumentosCobranca");
				sessao.removeAttribute("colecaoDocumentoCobranca");
				sessao.removeAttribute("idImovelPrincipalAba");
				consultarImovelActionForm.setIdImovelDadosComplementares(null);
				consultarImovelActionForm.setIdImovelDadosCadastrais(null);
				consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
				consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
				consultarImovelActionForm.setIdImovelDebitos(null);
				consultarImovelActionForm.setIdImovelPagamentos(null);
				consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
				consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
				consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
				consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
				consultarImovelActionForm.setImovIdAnt(null);
				consultarImovelActionForm.setSituacaoAguaDocumentosCobranca(null);
				consultarImovelActionForm.setSituacaoEsgotoDocumentosCobranca(null);

			}
		} else {
			consultarImovelActionForm
					.setIdImovelDocumentosCobranca(idImovelDocumentosCobranca);

			httpServletRequest.setAttribute(
					"idImovelDocumentosCobrancaNaoEncontrado", null);

			sessao.removeAttribute("imovelDocumentosCobranca");
			sessao.removeAttribute("colecaoDocumentoCobranca");
			sessao.removeAttribute("idImovelPrincipalAba");
			consultarImovelActionForm
					.setMatriculaImovelDocumentosCobranca(null);
			consultarImovelActionForm.setSituacaoAguaDocumentosCobranca(null);
			consultarImovelActionForm.setSituacaoEsgotoDocumentosCobranca(null);

		}

		return retorno;
	}

}

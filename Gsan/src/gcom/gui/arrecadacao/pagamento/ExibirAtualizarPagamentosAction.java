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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.FiltroGuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que inicializa a p�gina de atualizar pagamentoa
 * 
 * @author Pedro Alexandre
 * @date 22/03/2006
 */
public class ExibirAtualizarPagamentosAction extends GcomAction {

	/**
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descri��o sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descri��o sobre o fluxo secund�rio>
	 * 
	 * <Identificador e nome do fluxo secund�rio>
	 * 
	 * @author Pedro Alexandre
	 * @date 22/03/2006
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

		// Seta o mapeamento de retorno para a p�gina de atualizar pagamento
		ActionForward retorno = actionMapping.findForward("atualizarPagamento");

		// Cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Cria uma inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCorrente = new GregorianCalendar();

		// Data Corrente
		httpServletRequest.setAttribute("dataAtual", formatoData
				.format(dataCorrente.getTime()));

		// Recupera o c�digo do pagamento que vai ser atualizado
		// String codigoPagamento =
		// httpServletRequest.getParameter("idRegistroAtualizacao");

		// Cria a vari�vel que vai armazenar o pagamento para ser atualizado
		// Pagamento pagamento = (Pagamento) sessao.getAttribute("pagamento");

		// recupera o form de manter pagamentos
		ManterPagamentoActionForm manterPagamentoActionForm = (ManterPagamentoActionForm) actionForm;

		// Cria o filtro de tipo de documento, e seta no filtro quais os tipo de
		// documentos necess�rios
		// para pesquisar os tipos de documento de conta, guia de pagamento e
		// d�bito a cobrar
		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.CONTA,
				ParametroSimples.CONECTOR_OR));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.GUIA_PAGAMENTO,
				ParametroSimples.CONECTOR_OR));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.DEBITO_A_COBRAR));
		Collection<DocumentoTipo> colecaoDocumentoTipo = fachada.pesquisar(
				filtroDocumentoTipo, DocumentoTipo.class.getName());

		// [FS0002] - Verificar exist�ncia de dados
		// Caso a cole��o de tipo de documento estiver nula ou vazia, levanta
		// uma
		// exce��o para o usu�rio indicando que nenhum tipo de documento est�
		// cadastrado
		// Caso contr�rio manda os tipos de documentos pesquisados pela sess�o
		if (colecaoDocumentoTipo == null || colecaoDocumentoTipo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Tipo de Documento");
		} 
		
		sessao.setAttribute("colecaoDocumentoTipo", colecaoDocumentoTipo);

		String idPagamento = null;
		if (httpServletRequest.getParameter("reloadPage") == null
				|| httpServletRequest.getParameter("reloadPage").equals("")) {
			// Recupera o id de Pagamento que vai ser atualizado
			if (httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null) {
				idPagamento = httpServletRequest
						.getParameter("idRegistroInseridoAtualizar");
				// Definindo a volta do bot�o Voltar p Filtrar Pagamento
				sessao.setAttribute("voltar", "filtrar");
				sessao.setAttribute("idRegistroAtualizacao", idPagamento);
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") == null) {
				idPagamento = "" + sessao.getAttribute("idRegistroAtualizacao");
				// Definindo a volta do bot�o Voltar p Filtrar Pagamento
				sessao.setAttribute("voltar", "filtrar");
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				idPagamento = httpServletRequest
						.getParameter("idRegistroAtualizacao");
				// Definindo a volta do bot�o Voltar p Manter Pagamento
				sessao.setAttribute("voltar", "manter");
				sessao.setAttribute("idRegistroAtualizacao", idPagamento);
			}

			exibirPagamento(idPagamento, manterPagamentoActionForm, fachada,
					sessao, httpServletRequest);

		} else {
			idPagamento = (String) sessao.getAttribute("idRegistroAtualizacao");
		}
		
		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			// -------------- bt DESFAZER ---------------
			exibirPagamento(idPagamento, manterPagamentoActionForm, fachada,
					sessao, httpServletRequest);

		} else {

		// -------Parte que trata do c�digo quando o usu�rio tecla enter
		if (httpServletRequest.getParameter("reloadPage") != null
				&& !httpServletRequest.getParameter("reloadPage").equals("")) {

			// [FS0003] - Verificar exist�ncia da localidade
			// Recupera o c�digo da localidade digitado pelo usu�rio
			String codigoLocalidadeDigitadoEnter = manterPagamentoActionForm.getIdLocalidade();

			// Caso o c�digo da localidade informado n�o estiver vazio
			if (codigoLocalidadeDigitadoEnter != null
					&& !codigoLocalidadeDigitadoEnter.trim().equalsIgnoreCase(
							"")) {

				// Recupera a localidade informada pelo usu�rio
				Localidade localidadeEncontrada = fachada
						.pesquisarLocalidadeDigitada(new Integer(
								codigoLocalidadeDigitadoEnter));

				// Caso a localidade informada pelo usu�rio esteja cadastrada no
				// sistema
				// Seta os dados da localidade no form
				// Caso contr�rio seta as informa��es da localidade para vazio
				// e indica ao usu�rio que a localidade n�o existe
				if (localidadeEncontrada != null) {
					manterPagamentoActionForm.setIdLocalidade(""
							+ localidadeEncontrada.getId());
					manterPagamentoActionForm
							.setDescricaoLocalidade(localidadeEncontrada
									.getDescricao());
					httpServletRequest.setAttribute(
							"idLocalidadeNaoEncontrada", "true");

				} else {
					manterPagamentoActionForm.setIdLocalidade("");
					httpServletRequest.setAttribute(
							"idLocalidadeNaoEncontrada", "exception");
					manterPagamentoActionForm
							.setDescricaoLocalidade("Localidade inexistente");

				}
			}

			// [FS0004] - Verificar exist�ncia da matr�cula do im�vel
			String codigoImovelDigitadoEnter = manterPagamentoActionForm.getIdImovel();

			// Caso o c�digo do im�vel informado n�o estiver vazio
			if (codigoImovelDigitadoEnter != null
					&& !codigoImovelDigitadoEnter.trim().equalsIgnoreCase("")) {

				// Recupera o im�vel informado pelo usu�rio
				Imovel imovelEncontrado = fachada
						.pesquisarImovelDigitado(new Integer(
								codigoImovelDigitadoEnter));

				// Caso o im�vel informado pelo usu�rio esteja cadastrado no
				// sistema
				// Seta os dados o im�vel no form
				// Caso contr�rio seta as informa��es o im�vel para vazio
				// e indica ao usu�rio que o im�vel informado n�o existe
				if (imovelEncontrado != null) {
					manterPagamentoActionForm.setIdImovel(""
							+ imovelEncontrado.getId());
					manterPagamentoActionForm.setDescricaoImovel(""
							+ imovelEncontrado.getInscricaoFormatada());
					httpServletRequest.setAttribute("idImovelNaoEncontrado",
							"true");

					// Recupera a localidade do im�vel,caso o mesmo exista na
					// base
					Localidade localidadeImovel = imovelEncontrado
							.getLocalidade();

					// Caso o usu�rio tenha informado a localidade
					if (codigoLocalidadeDigitadoEnter != null
							&& !codigoLocalidadeDigitadoEnter.trim()
									.equalsIgnoreCase("")) {
						// [FS0005] - Verificar localidade da matr�cula do
						// im�vel
						if (!fachada
								.verificarLocalidadeMatriculaImovel(
										codigoLocalidadeDigitadoEnter,
										imovelEncontrado)) {
							manterPagamentoActionForm.setIdImovel("");
							httpServletRequest.setAttribute(
									"idImovelNaoEncontrado", "exception");
							manterPagamentoActionForm
									.setDescricaoImovel("A Localidade da Matr�cula "
											+ localidadeImovel.getId()
											+ " � diferente da localidade informada "
											+ codigoLocalidadeDigitadoEnter);
						}
					} else {
						manterPagamentoActionForm.setIdLocalidade(""
								+ localidadeImovel.getId());
						manterPagamentoActionForm
								.setDescricaoLocalidade(localidadeImovel
										.getDescricao());
						httpServletRequest.setAttribute(
								"idLocalidadeNaoEncontrada", "true");
					}
				} else {
					manterPagamentoActionForm.setIdImovel("");
					httpServletRequest.setAttribute("idImovelNaoEncontrado",
							"exception");
					manterPagamentoActionForm
							.setDescricaoImovel("Matr�cula inexistente");
				}
			}

			// [FS0007] - Verificar exist�ncia do c�digo do cliente
			String codigoClienteDigitadoEnter = manterPagamentoActionForm.getIdCliente();

			// Recupera a metr�cula do im�vel e o c�digo do cliente do form de
			// manter pagamento
			String codigoImovel = codigoImovelDigitadoEnter;
			String codigoCliente = manterPagamentoActionForm.getIdCliente();

			// Caso o usu�rio tenha informado o c�digo do cliente
			if (codigoClienteDigitadoEnter != null
					&& !codigoClienteDigitadoEnter.trim().equalsIgnoreCase("")) {

				// Recupera o cliente ,caso o mesmo exista na base
				Cliente clienteEncontrado = fachada
						.pesquisarClienteDigitado(new Integer(
								codigoClienteDigitadoEnter));

				// Caso o cliente esteja cadastrado no sistema
				// Seta no form todos os dados do cliente
				// Caso contr�rio seta os dados do cliente para vazio e informa
				// que
				// o cliente n�o existe
				if (clienteEncontrado != null) {
					manterPagamentoActionForm.setIdCliente(""
							+ clienteEncontrado.getId());
					manterPagamentoActionForm.setNomeCliente(clienteEncontrado
							.getNome());
					httpServletRequest.setAttribute("idClienteNaoEncontrado",
							"true");

				} else {
					manterPagamentoActionForm.setIdCliente("");
					httpServletRequest.setAttribute("idClienteNaoEncontrado",
							"exception");
					manterPagamentoActionForm
							.setNomeCliente("C�digo inexistente");
				}
			}

			// [FS0008] - Verificar exist�ncia da conta
			String referenciaContaDigitadoEnter = manterPagamentoActionForm.getReferenciaConta();

			// Caso o usu�rio tenha informado a refer�ncia da conta
			if (referenciaContaDigitadoEnter != null
					&& !referenciaContaDigitadoEnter.trim()
							.equalsIgnoreCase("")) {

				// Caso o usu�rio n�o tenha informado a matr�cula do im�vel
				// Levanta uma exce��o para o usu�rio indicado que ele n�o
				// informou
				// a matr�cula do im�vel
				if (codigoImovel == null
						|| codigoImovel.trim().equalsIgnoreCase("")) {

					throw new ActionServletException("atencao.naoinformado",
							null, "Im�vel");
				}

				// Recupera a conta do im�vel com a refer�ncia informada
				Conta contaEncontrada = fachada.pesquisarContaDigitada(
						codigoImovel, referenciaContaDigitadoEnter);

				// Caso a conta esteja cadastrada no sistema
				// Seta todas as informa��es da conta no form
				// Caso contr�rio seta as informa��es da conta para nulo
				// e indica ao usu�rio que n�o existe conta para o im�el
				// informadocom a refer�ncia indicada
				if (contaEncontrada != null) {
					manterPagamentoActionForm.setReferenciaConta(""
							+ referenciaContaDigitadoEnter);
					manterPagamentoActionForm.setDescricaoReferenciaConta(""
							+ contaEncontrada.getValorTotalConta());
					manterPagamentoActionForm.setValorPagamento(""
							+ contaEncontrada.getValorTotalConta());
					httpServletRequest.setAttribute(
							"referenciaContaNaoEncontrada", "true");

				} else {
					manterPagamentoActionForm.setReferenciaConta(""
							+ referenciaContaDigitadoEnter);
					httpServletRequest.setAttribute(
							"referenciaContaNaoEncontrada", "exception");
					manterPagamentoActionForm
							.setDescricaoReferenciaConta("N�o h� Conta com a refer�ncia "
									+ referenciaContaDigitadoEnter
									+ " para o im�vel " + codigoImovel);
					manterPagamentoActionForm.setValorPagamento("");
				}
			}

			// [FS0022] - Verificar exist�ncia da guia de pagamento
			String codigoGuiaPagamentoDigitadoEnter = manterPagamentoActionForm.getIdGuiaPagamento();

			// Caso o usu�rio tenha informado o c�digo da guia de pagamento
			if (codigoGuiaPagamentoDigitadoEnter != null
					&& !codigoGuiaPagamentoDigitadoEnter.trim()
							.equalsIgnoreCase("")) {

				// Caso o usu�rio n�o tenha informado a matr�cula do im�vel
				// Levanta uma exce��o para o usu�rio indicado que ele n�o
				// informou
				// a matr�cula do im�vel
				if ( !Util.verificarNaoVazio(codigoImovel)
						&& !Util.verificarNaoVazio(codigoCliente)) {
					throw new ActionServletException("atencao.naoinformado",
							null, "Im�vel ou Cliente");
				}

				// Pesquisa a guia de pagamento para o im�vel informado
				GuiaPagamento guiaPagamentoEncontrada = fachada
						.pesquisarGuiaPagamentoDigitada(codigoImovel,
								codigoCliente, codigoGuiaPagamentoDigitadoEnter);

				// Caso a guia de pagamento esteja cadastrada no sistema
				// Seta os dados da guia de pagamento no form
				// Caso contr�rio seta os dados da guia para nulo e informa ao
				// usu�rio que n�o existe
				// guia de pagamento cadastrada no sistema
				if (guiaPagamentoEncontrada != null) {
					manterPagamentoActionForm.setIdGuiaPagamento(""
							+ guiaPagamentoEncontrada.getId());
					manterPagamentoActionForm.setDescricaoGuiaPagamento(""
							+ guiaPagamentoEncontrada.getDebitoTipo()
									.getDescricao());
					manterPagamentoActionForm.setValorGuiaPagamento(""
							+ guiaPagamentoEncontrada.getValorDebito());
					httpServletRequest.setAttribute(
							"idGuiaPagamentoNaoEncontrado", "true");

				} else {
					manterPagamentoActionForm.setIdGuiaPagamento("");
					manterPagamentoActionForm
							.setDescricaoGuiaPagamento("Guia de Pagamento inexistente");
					manterPagamentoActionForm.setValorGuiaPagamento("");
					httpServletRequest.setAttribute(
							"idGuiaPagamentoNaoEncontrado", "exception");
				}
			}

			// [FS0024] - Verificar exist�ncia do d�bito a cobrar
			String codigoDebitoACobrarDigitadoEnter = manterPagamentoActionForm.getIdDebitoACobrar();

			// Caso o usu�rio tenha informado o c�digo do d�bito a cobrar
			if (codigoDebitoACobrarDigitadoEnter != null
					&& !codigoDebitoACobrarDigitadoEnter.trim()
							.equalsIgnoreCase("")) {

				// Caso o usu�rio n�o tenha informado a matr�cula do im�vel
				// Levanta uma exce��o para o usu�rio indicado que ele n�o
				// informou
				// a matr�cula do im�vel
				if (codigoImovel == null
						|| codigoImovel.trim().equalsIgnoreCase("")) {
					throw new ActionServletException("atencao.naoinformado",
							null, "Im�vel");
				}

				// Pesquisa o d�bito a cobrar para o im�vel informado
			/*	DebitoACobrar debitoACobrarEncontrado = fachada
						.pesquisarDebitoACobrarDigitado(codigoImovel,
								codigoDebitoACobrarDigitadoEnter);*/
				
				// Pesquisa o d�bito a cobrar para o im�vel informado
				DebitoACobrarGeral debitoACobrarGeralEncontrado = fachada
						.pesquisarDebitoACobrarGeralDigitado(codigoImovel,
								codigoDebitoACobrarDigitadoEnter);
				
				

				// Caso o d�bito a cobrar esteja cadastrado no sistema
				// Seta os dados do d�bito a cobrar no form
				// Caso contr�rio seta os dados do d�bito para nulo e informa ao
				// usu�rio que n�o existe
				// d�bito a cobrar cadastrado no sistema
				if (debitoACobrarGeralEncontrado != null) {
					
					if(debitoACobrarGeralEncontrado.getDebitoACobrar()!=null){
					
					manterPagamentoActionForm.setIdDebitoACobrar(""
							+ debitoACobrarGeralEncontrado.getDebitoACobrar().getId());
					manterPagamentoActionForm.setDescricaoDebitoACobrar(""
							+ debitoACobrarGeralEncontrado.getDebitoACobrar().getDebitoTipo()
									.getDescricao());
					manterPagamentoActionForm.setValorDebitoACobrar(""
							+ debitoACobrarGeralEncontrado.getDebitoACobrar().getValorDebito());
					httpServletRequest.setAttribute(
							"idDebitoACobrarNaoEncontrado", "true");
					}else{
						manterPagamentoActionForm.setIdDebitoACobrar(""
								+ debitoACobrarGeralEncontrado.getDebitoACobrarHistorico().getId());
						manterPagamentoActionForm.setDescricaoDebitoACobrar(""
								+ debitoACobrarGeralEncontrado.getDebitoACobrarHistorico().getDebitoTipo()
										.getDescricao());
						manterPagamentoActionForm.setValorDebitoACobrar(""
								+ debitoACobrarGeralEncontrado.getDebitoACobrarHistorico().getValorDebito());
						httpServletRequest.setAttribute(
								"idDebitoACobrarNaoEncontrado", "true");
					}

				} else {
					manterPagamentoActionForm.setIdDebitoACobrar("");
					manterPagamentoActionForm
							.setDescricaoDebitoACobrar("D�bito a Cobrar inexistente");
					manterPagamentoActionForm.setDescricaoDebitoACobrar("");
					httpServletRequest.setAttribute(
							"idDebitoACobrarNaoEncontrado", "exception");
				}
			}

			// }

			// [FS0020] - Verificar exist�ncia do tipo de d�bito
			String codigoTipoDebitoDigitadoEnter = manterPagamentoActionForm.getIdTipoDebito();

			// Caso o usu�rio tenha informado o c�digo do tipo de d�bito
			if (codigoTipoDebitoDigitadoEnter != null
					&& !codigoTipoDebitoDigitadoEnter.trim().equalsIgnoreCase(
							"")) {

				// Recupera o tipo de d�bito ,caso o mesmo exista na base
				DebitoTipo tipoDebitoEncontrado = fachada
						.pesquisarTipoDebitoDigitado(new Integer(
								codigoTipoDebitoDigitadoEnter));

				// Caso o tipo de d�bito esteja cadastrado no sistema
				// Seta no form todos os dados do tipo de d�bito
				// Caso contr�rio seta os dados do tipo de d�bito para vazio e
				// informa que o tipo de d�bito n�o existe
				if (tipoDebitoEncontrado != null) {
					manterPagamentoActionForm.setIdTipoDebito(""
							+ tipoDebitoEncontrado.getId());
					manterPagamentoActionForm
							.setDescricaoTipoDebito(tipoDebitoEncontrado
									.getDescricao());
					httpServletRequest.setAttribute(
							"idTipoDebitoNaoEncontrado", "true");

				} else {
					manterPagamentoActionForm.setIdTipoDebito("");
					httpServletRequest.setAttribute(
							"idTipoDebitoNaoEncontrado", "exception");
					manterPagamentoActionForm
							.setDescricaoTipoDebito("Tipo de D�bito inexistente");
				}
			}

		}
		}
		// -------Parte que trata do c�digo quando o usu�rio tecla enter

		// Seta na sess�o o form de pagamento e o pagamento que vai ser
		// atualizado
		sessao.setAttribute("ManterPagamentoActionForm",
				manterPagamentoActionForm);
		// sessao.setAttribute("pagamento", pagamento);

		// Retorna o mapeamento contido na vari�vel retorno
		return retorno;
	}

	private void exibirPagamento(String idPagamento,
			ManterPagamentoActionForm manterPagamentoActionForm,
			Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest) {

		Pagamento pagamento = null;

		PagamentoSituacao pagamentoSituacaoAtual = null;

		if (idPagamento != null && !idPagamento.equalsIgnoreCase("")) {

			// Cria o filtro de pagamento e seta o c�digo do pagamento para ser
			// atualizado no filtro
			// e indicxa quais objetos devem ser retornados pela pesquisa
			FiltroPagamento filtroPagamento = new FiltroPagamento();
			filtroPagamento.adicionarParametro(new ParametroSimples(
					FiltroPagamento.ID, idPagamento));
			
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("avisoBancario");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("arrecadacaoForma");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("pagamentoSituacaoAtual");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("pagamentoSituacaoAnterior");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador.cliente");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("contaGeral");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("contaGeral.conta");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("guiaPagamento");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico");

			// Pesquisa o pagamento no sistema com os par�metros informados no
			// filtro
			Collection<Pagamento> colecaoPagamentos = fachada.pesquisar(filtroPagamento,
					Pagamento.class.getName());

			// Caso a pesquisa tenha retornado o pagamento
			if (colecaoPagamentos != null && !colecaoPagamentos.isEmpty()) {
				// Recupera da cole��o o pagamento que vai ser atualizado
				pagamento = (Pagamento) Util
						.retonarObjetoDeColecao(colecaoPagamentos);

				// Cria a vari�vel que vai armazenar a cole��o de situa��es
				// atuais de pagamento
				Collection<PagamentoSituacao> colecaoSituacaoAtualPagamento = null;

				// Recupera a situa��o atual e anterior do pagamento
				pagamentoSituacaoAtual = pagamento.getPagamentoSituacaoAtual();

				// Caso a situa��o atual do pagamento esteja preenchida
				if (pagamentoSituacaoAtual != null) {
					// Caso a situa��o atual do pagamento seja igual a "Fatura
					// Inexistente" ou
					// "Pagamento em Duplicidade" ou igual a valor em excesso
					if (pagamentoSituacaoAtual.getId().equals(
							PagamentoSituacao.FATURA_INEXISTENTE)
							|| pagamentoSituacaoAtual.getId().equals(
									PagamentoSituacao.PAGAMENTO_EM_DUPLICIDADE)
							|| pagamentoSituacaoAtual.getId().equals(
									PagamentoSituacao.VALOR_EM_EXCESSO)
						
                            /* Alterado dia 12/09/2008
                               Author: Bruno Barros
                               Descri��o:
                                   Altera��o do caso de uso para que quando a situa��o atual do pagamento for igual a
                                   �Valor N�o Confere� e valor excedente do pagamento com valor maior que zero */ 
                            || ( pagamentoSituacaoAtual.getId().equals(
                                    PagamentoSituacao.VALOR_NAO_CONFERE ) && 
                                    pagamento.getValorExcedente() != null && 
                                    pagamento.getValorExcedente().floatValue() > 0 ) ) {
                            // Fim altera��o Bruno Barros
                        
						// Cria o filtro de situa��o de pagamento e seta no
						// filtro para retornar somente a situa��o
						// igual a valor a baixar
						FiltroPagamentoSituacao filtroPagamentoSituacao = new FiltroPagamentoSituacao();
						filtroPagamentoSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroPagamentoSituacao.CODIGO,
										PagamentoSituacao.VALOR_A_BAIXAR));
						colecaoSituacaoAtualPagamento = fachada.pesquisar(
								filtroPagamentoSituacao,
								PagamentoSituacao.class.getName());

						// [FS0002] - Verificar exist�ncia de dados
						// Caso a situa��o de valor a baixar n�o esteja
						// cadastrada no sistema
						// levante uma exce��o para o usu�rio indicando que a
						// situa��o n�o est� cadastrada
						if (colecaoSituacaoAtualPagamento == null
								|| colecaoSituacaoAtualPagamento.isEmpty()) {
							throw new ActionServletException(
									"atencao.naocadastrado", null,
									"Situa��o de Pagamento");
						}
					}
				}

				// Seta na sess�oa cole��o de situa��o de pagamentos, para o
				// campo de situa��o de pagamento atual
				sessao.setAttribute("colecaoSituacaoAtualPagamento",
						colecaoSituacaoAtualPagamento);

				// Seta no form os dados de aviso banc�rio
				manterPagamentoActionForm.setIdAvisoBancario(""
						+ pagamento.getAvisoBancario().getId());
				manterPagamentoActionForm.setCodigoAgenteArrecadador(""
						+ pagamento.getAvisoBancario().getArrecadador()
								.getCodigoAgente());
				manterPagamentoActionForm.setDataLancamentoAviso(Util
						.formatarData(pagamento.getAvisoBancario()
								.getDataLancamento()));
				manterPagamentoActionForm.setNumeroSequencialAviso(""
						+ pagamento.getAvisoBancario().getNumeroSequencial());
				
//				 Seta no form os dados de arrecada��o
				if (pagamento.getArrecadacaoForma() != null){
					manterPagamentoActionForm.setIdFormaArrecadacao(""
							+ pagamento.getArrecadacaoForma().getId());
					manterPagamentoActionForm.setDescricaoFormaArrecadacao(""
							+ pagamento.getArrecadacaoForma().getDescricao());
				}

				// Seta no form os dados de tipo de documento
				manterPagamentoActionForm.setIdTipoDocumento(""
						+ pagamento.getDocumentoTipo().getId());

				// Seta no form os dados de localidade
				if (pagamento.getLocalidade() != null) {
					manterPagamentoActionForm.setIdLocalidade(""
							+ pagamento.getLocalidade().getId());
					manterPagamentoActionForm.setDescricaoLocalidade(pagamento
							.getLocalidade().getDescricao());
				}

				// Seta no form os dados de im�vel
				if (pagamento.getImovel() != null) {
					manterPagamentoActionForm.setIdImovel(""
							+ pagamento.getImovel().getId());
					manterPagamentoActionForm.setDescricaoImovel(""
							+ pagamento.getImovel().getInscricaoFormatada());
				}

				// Seta no form os dados de cliente
				if (pagamento.getCliente() != null) {
					manterPagamentoActionForm.setIdCliente(""
							+ pagamento.getCliente().getId());
					manterPagamentoActionForm.setNomeCliente(pagamento
							.getCliente().getNome());
				}

				// Seta no form os dados de refer�ncia da conta
				if (pagamento.getAnoMesReferenciaPagamento() != null
						&& pagamento.getAnoMesReferenciaPagamento() != 0) {
					manterPagamentoActionForm.setReferenciaConta(Util
							.formatarAnoMesParaMesAno(pagamento
									.getAnoMesReferenciaPagamento()));
				}

				// Seta no form os dados da guia de pagamento
				if (pagamento.getGuiaPagamento() != null) {
					
					FiltroGuiaPagamento filtroGuia = new FiltroGuiaPagamento();
					filtroGuia.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID, pagamento.getGuiaPagamento().getId()));
					filtroGuia.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
					Collection colecaoGuia = fachada.pesquisar(filtroGuia, GuiaPagamento.class.getName());
					
					if(!Util.isVazioOrNulo(colecaoGuia)){
						GuiaPagamento guiaPagamento = (GuiaPagamento) colecaoGuia.iterator().next();					
					
						manterPagamentoActionForm.setIdGuiaPagamento(""+ guiaPagamento.getId());
						
						if (guiaPagamento.getDebitoTipo() != null) {
							manterPagamentoActionForm.setDescricaoGuiaPagamento(""+ guiaPagamento.getDebitoTipo().getDescricao());
						}						
						manterPagamentoActionForm.setValorGuiaPagamento(""+ Util.formatarMoedaReal(guiaPagamento.getValorDebito()));
					}else{
						
						FiltroGuiaPagamentoHistorico filtroGuiaHist = new FiltroGuiaPagamentoHistorico();
						filtroGuiaHist.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoHistorico.ID, pagamento.getGuiaPagamento().getId()));
						filtroGuiaHist.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
						Collection colecaoHistorico = fachada.pesquisar(filtroGuiaHist, GuiaPagamentoHistorico.class.getName());
						
						if(!Util.isVazioOrNulo(colecaoHistorico)){
							GuiaPagamentoHistorico guiaHistorico = (GuiaPagamentoHistorico) colecaoHistorico.iterator().next();
							
							manterPagamentoActionForm.setIdGuiaPagamento(""+ guiaHistorico.getId());
							
							if (guiaHistorico.getDebitoTipo() != null) {
								manterPagamentoActionForm.setDescricaoGuiaPagamento(""+ guiaHistorico.getDebitoTipo().getDescricao());
							}						
							manterPagamentoActionForm.setValorGuiaPagamento(""+ Util.formatarMoedaReal(guiaHistorico.getValorDebito()));
						}					
						
					}
				}

				// Seta no form os dados do d�bito a cobrar
				if (pagamento.getDebitoACobrarGeral() != null) {
					
					manterPagamentoActionForm.setIdDebitoACobrar(""+pagamento.getDebitoACobrarGeral().getId());
					
					if ( pagamento.getDebitoACobrarGeral().getDebitoACobrar() != null ){
                    
                        if ( pagamento.getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo() != null) {
    						manterPagamentoActionForm.setDescricaoDebitoACobrar(""
    							+ pagamento.getDebitoACobrarGeral().getDebitoACobrar().
    								getDebitoTipo().getDescricao());
    					}
    					manterPagamentoActionForm.setValorDebitoACobrar("" + 
    						Util.formatarMoedaReal(pagamento.getDebitoACobrarGeral().getDebitoACobrar().getValorDebito()));
                    }else{
                    	 if ( pagamento.getDebitoACobrarGeral().getDebitoACobrarHistorico().getDebitoTipo() != null) {
     						manterPagamentoActionForm.setDescricaoDebitoACobrar(""
     							+ pagamento.getDebitoACobrarGeral().getDebitoACobrarHistorico().
     								getDebitoTipo().getDescricao());
     					}
     					manterPagamentoActionForm.setValorDebitoACobrar("" + Util.formatarMoedaReal(pagamento.getDebitoACobrarGeral().getDebitoACobrarHistorico().getValorDebito()));
                    }
				}

				if (pagamento.getGuiaPagamento() == null && 
					pagamento.getDebitoACobrarGeral() == null && pagamento.getAnoMesReferenciaPagamento() == null) {
					
					if (pagamento.getDebitoTipo() != null) {
						
						manterPagamentoActionForm.setIdTipoDebito(""+ pagamento.getDebitoTipo().getId());
						manterPagamentoActionForm.setDescricaoTipoDebito(pagamento.getDebitoTipo().getDescricao());
					}
				}

				// Seta no form a data de pagamento
				manterPagamentoActionForm.setDataPagamento(Util
						.formatarData(pagamento.getDataPagamento()));

				// Seta no form o valor de pagamento
				manterPagamentoActionForm.setValorPagamento(""
						+ pagamento.getValorPagamento());

				// Seta no form os dados da situa��o atual do pagamento
				if (pagamento.getPagamentoSituacaoAtual() != null) {
					manterPagamentoActionForm.setIdSituacaoAtualPagamento(""
							+ pagamento.getPagamentoSituacaoAtual().getId());
				}
				
				if (pagamento.getAvisoBancario().getArrecadador() != null &&
						pagamento.getAvisoBancario().getArrecadador().getCliente() != null){
					
					manterPagamentoActionForm.setNomeClienteArrecadador(
							pagamento.getAvisoBancario().getArrecadador().getCliente().getNome());
				}
				manterPagamentoActionForm.setUltimaAlteracaoPagamento(Util.formatarData(pagamento.getUltimaAlteracao()));
				
			}
		}

		// Cria a flag que vai indicar se o campo de valor de pagamento vai
		// estar habilitado ou n�o
		String habilitarValorPagamento = null;

		// Caso a situa��o atual e a anterior da guia n�o estiverem preenchidas
		// indica na flag que o campo do valor de pagamento vai estar habilitado
		// na p�gina de atualizar
		// Caso contr�rio indica na flag que o campo do valor de pagamento N�O
		// vai estar habilitado na p�gina de atualizar
		//Retirado por ordem de aryed,por S�vio Luiz data:22/03/2007
		/*if ((pagamentoSituacaoAtual == null)
				&& (pagamentoSituacaoAnterior == null)) {
		*/	habilitarValorPagamento = "true";
		/*} else {
			habilitarValorPagamento = "false";
		}*/

		// Seta no request a flag que indica se o campo de valor de pagamento
		// vai estar habilitado ou n�o
		sessao.setAttribute("habilitarValorPagamento", habilitarValorPagamento);

		sessao.setAttribute("pagamento", pagamento); // ?????

	}

}

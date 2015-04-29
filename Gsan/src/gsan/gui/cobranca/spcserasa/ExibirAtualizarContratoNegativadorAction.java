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
 * Yara Taciane de Souza
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
package gsan.gui.cobranca.spcserasa;

import gsan.arrecadacao.ContratoMotivoCancelamento;
import gsan.cobranca.NegativadorContrato;
import gsan.fachada.Fachada;
import gsan.faturamento.FiltroContratoMotivoCancelamento;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.spcserasa.FiltroNegativadorContrato;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de Exibir Atualizar Contrato Negativador
 * 
 * @author Yara Taciane 
 * @created 27/12/2007
 */

public class ExibirAtualizarContratoNegativadorAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Inicializando Variaveis
		ActionForward retorno = actionMapping
				.findForward("atualizarContratoNegativador");
		AtualizarContratoNegativadorActionForm atualizarContratoNegativadorActionForm = (AtualizarContratoNegativadorActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		// volta da msg de Contrato do Negativador j� utilizado, n�o pode ser
		// alterado nem exclu�do.
		String confirmado = httpServletRequest.getParameter("confirmado");

		String idContratoNegativador = null;
		if (httpServletRequest.getParameter("reload") == null
				|| httpServletRequest.getParameter("reload").equalsIgnoreCase(
						"") && (confirmado == null || confirmado.equals(""))) {
			// Recupera o id do PerfilParcelamento que vai ser atualizado

			if (httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null) {
				idContratoNegativador = httpServletRequest
						.getParameter("idRegistroInseridoAtualizar");
				// Definindo a volta do bot�o Voltar p Filtrar Perfil de
				// Parcelamento
				httpServletRequest.setAttribute("voltar", "filtrar");
				sessao.setAttribute("idRegistroAtualizacao",
						idContratoNegativador);
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") == null) {
				idContratoNegativador = (String) sessao
						.getAttribute("idRegistroAtualizacao");
				// Definindo a volta do bot�o Voltar p Filtrar Perfil de
				// Parcelamento
				httpServletRequest.setAttribute("voltar", "filtrar");
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				idContratoNegativador = httpServletRequest
						.getParameter("idRegistroAtualizacao");
				// Definindo a volta do bot�o Voltar p Manter Perfil de
				// Parcelamento
				httpServletRequest.setAttribute("voltar", "manter");
				sessao.setAttribute("idRegistroAtualizacao",
						idContratoNegativador);
			}
		} else {
			idContratoNegativador = (String) sessao
					.getAttribute("idRegistroAtualizacao");
		}

		Collection colecaoContratoMotivoCancelamento = (Collection) sessao
				.getAttribute("colecaoContratoMotivoCancelamento");

		if (colecaoContratoMotivoCancelamento == null) {

			FiltroContratoMotivoCancelamento filtroContratoMotivoCancelamento = new FiltroContratoMotivoCancelamento();
			filtroContratoMotivoCancelamento.setConsultaSemLimites(true);

			colecaoContratoMotivoCancelamento = fachada.pesquisar(
					filtroContratoMotivoCancelamento,
					ContratoMotivoCancelamento.class.getName());

			if (colecaoContratoMotivoCancelamento == null
					|| colecaoContratoMotivoCancelamento.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"CONTRATO_MOTIVO_CANCELAMENTO");
			} else {
				sessao.setAttribute("colecaoContratoMotivoCancelamento",
						colecaoContratoMotivoCancelamento);
			}
		}

		// Verifica se o usu�rio est� selecionando o Perfil de Parcelamento da
		// p�gina de manter
		// Caso contr�rio o usu�rio est� teclando enter na p�gina de atualizar
		if ((idContratoNegativador != null && !idContratoNegativador.equals(""))
				&& (httpServletRequest.getParameter("desfazer") == null)
				&& (httpServletRequest.getParameter("reload") == null || httpServletRequest
						.getParameter("reload").equalsIgnoreCase(""))) {
			exibirContratoNegativador(idContratoNegativador,
					atualizarContratoNegativadorActionForm, fachada, sessao,
					httpServletRequest);

		}

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			// -------------- bt DESFAZER ---------------

			exibirContratoNegativador(idContratoNegativador,
					atualizarContratoNegativadorActionForm, fachada, sessao,
					httpServletRequest);

		}

		return retorno;

	}

	private void exibirContratoNegativador(
			String idNegativadorContrato,
			AtualizarContratoNegativadorActionForm atualizarContratoNegativadorActionForm,
			Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest) {

		// Cria a vari�vel que vai armazenar o ParcelamentoPerfil para ser
		// atualizado
		NegativadorContrato negativadorContrato = null;

		// Cria o filtro de NegativadorContrato e seta o id do
		// NegativadorContrato para ser atualizado no filtro
		// e indica quais objetos devem ser retornados pela pesquisa
		FiltroNegativadorContrato filtroNegativadorContrato = new FiltroNegativadorContrato();
		filtroNegativadorContrato.adicionarParametro(new ParametroSimples(
				FiltroNegativadorContrato.ID, idNegativadorContrato));

		filtroNegativadorContrato
				.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");

		Collection<NegativadorContrato> collectionNegativadorContrato = fachada
				.pesquisar(filtroNegativadorContrato, NegativadorContrato.class
						.getName());

		// Caso a pesquisa tenha retornado o NegativadorContrato
		if (collectionNegativadorContrato != null
				&& !collectionNegativadorContrato.isEmpty()) {

			// Recupera da cole��o o NegativadorContrato que vai ser atualizado
			negativadorContrato = (NegativadorContrato) Util
					.retonarObjetoDeColecao(collectionNegativadorContrato);

			// dataFim menor que data atual
			Date dataAtual = new Date();
			if (Util.compararData(negativadorContrato.getDataContratoFim(),
					dataAtual) == -1
					|| negativadorContrato.getDataContratoEncerramento() != null) {

				atualizarContratoNegativadorActionForm.setVigente("false");

			} else {
				atualizarContratoNegativadorActionForm.setVigente("true");
			}

			// Seta no form os dados de NegativadorContrato
			if (negativadorContrato.getNegativador() != null
					&& !negativadorContrato.getNegativador().equals("")) {

				atualizarContratoNegativadorActionForm.setIdNegativador(""
						+ negativadorContrato.getNegativador());

				atualizarContratoNegativadorActionForm.setNegativadorCliente(""
						+ negativadorContrato.getNegativador().getCliente()
								.getNome());
			} else {
				atualizarContratoNegativadorActionForm
						.setNegativadorCliente("");
			}

			if (negativadorContrato.getNumeroInclusoesEnviadas() != null
					&& !negativadorContrato.getNumeroInclusoesEnviadas()
							.equals("")) {

				atualizarContratoNegativadorActionForm
						.setNumeroInclusoesEnviadas(""
								+ negativadorContrato
										.getNumeroInclusoesEnviadas());
			} else {
				atualizarContratoNegativadorActionForm
						.setNumeroInclusoesEnviadas("");
			}

			if (negativadorContrato.getNumeroInclusoesContratadas() != null
					&& !negativadorContrato.getNumeroInclusoesContratadas()
							.equals("")) {

				atualizarContratoNegativadorActionForm
						.setNumeroInclusoesContratadas(""
								+ negativadorContrato
										.getNumeroInclusoesContratadas());
			} else {
				atualizarContratoNegativadorActionForm
						.setNumeroInclusoesContratadas("");
			}

			if (negativadorContrato.getNumeroExclusoesEnviadas() != null
					&& !negativadorContrato.getNumeroExclusoesEnviadas()
							.equals("")) {

				atualizarContratoNegativadorActionForm
						.setNumeroExclusoesEnviadas(""
								+ negativadorContrato
										.getNumeroExclusoesEnviadas());
			} else {
				atualizarContratoNegativadorActionForm
						.setNumeroExclusoesEnviadas("");
			}

			if (negativadorContrato.getNumeroContrato() != null
					&& !negativadorContrato.getNumeroContrato().equals("")) {

				atualizarContratoNegativadorActionForm.setNumeroContrato(""
						+ negativadorContrato.getNumeroContrato());
			} else {
				atualizarContratoNegativadorActionForm.setNumeroContrato("");
			}
			
			if (negativadorContrato.getNumeroEntidade() != null
					&& !negativadorContrato.getNumeroEntidade().equals("")) {

				atualizarContratoNegativadorActionForm.setNumeroEntidade(""
						+ negativadorContrato.getNumeroEntidade());
			} else {
				atualizarContratoNegativadorActionForm.setNumeroEntidade("");
			}
			
			if (negativadorContrato.getNumeroAssociado() != null
					&& !negativadorContrato.getNumeroAssociado().equals("")) {

				atualizarContratoNegativadorActionForm.setNumeroAssociado(""
						+ negativadorContrato.getNumeroAssociado());
			} else {
				atualizarContratoNegativadorActionForm.setNumeroAssociado("");
			}
			//indicador
			if(negativadorContrato.getIndicadorObriControSequeRetorno() != null){
				atualizarContratoNegativadorActionForm.setIndicadorObriControSequeRetorno(
						negativadorContrato.getIndicadorObriControSequeRetorno());
				
			}
			

			if (negativadorContrato.getDescricaoEmailEnvioArquivo() != null
					&& !negativadorContrato.getDescricaoEmailEnvioArquivo()
							.equals("")) {

				atualizarContratoNegativadorActionForm
						.setDescricaoEmailEnvioArquivo(""
								+ negativadorContrato
										.getDescricaoEmailEnvioArquivo());
			} else {
				atualizarContratoNegativadorActionForm
						.setDescricaoEmailEnvioArquivo("");
			}

			if (negativadorContrato.getCodigoConvenio() != null
					&& !negativadorContrato.getCodigoConvenio().equals("")) {

				atualizarContratoNegativadorActionForm.setCodigoConvenio(""
						+ negativadorContrato.getCodigoConvenio());
			} else {
				atualizarContratoNegativadorActionForm.setCodigoConvenio("");
			}

			if (negativadorContrato.getValorContrato() != null
					&& !negativadorContrato.getValorContrato().equals("")) {

				atualizarContratoNegativadorActionForm.setValorContrato(""
						+ negativadorContrato.getValorContrato());
			} else {
				atualizarContratoNegativadorActionForm.setValorContrato("");
			}

			if (negativadorContrato.getValorTarifaInclusao() != null
					&& !negativadorContrato.getValorTarifaInclusao().equals("")) {

				atualizarContratoNegativadorActionForm
						.setValorTarifaInclusao(""
								+ negativadorContrato.getValorTarifaInclusao());
			} else {
				atualizarContratoNegativadorActionForm
						.setValorTarifaInclusao("");
			}

			if (negativadorContrato.getNumeroPrazoInclusao() != 0) {
				atualizarContratoNegativadorActionForm
						.setNumeroPrazoInclusao(""
								+ negativadorContrato.getNumeroPrazoInclusao());
			} else {
				atualizarContratoNegativadorActionForm
						.setNumeroPrazoInclusao("");
			}

			// Carregar a data corrente do sistema
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

			if (negativadorContrato.getDataContratoInicio() != null
					&& !negativadorContrato.getDataContratoInicio().equals("")) {

				atualizarContratoNegativadorActionForm.setDataContratoInicio(""
						+ formatoData.format(negativadorContrato
								.getDataContratoInicio().getTime()));

			} else {
				atualizarContratoNegativadorActionForm
						.setDataContratoInicio("");
			}

			if (negativadorContrato.getDataContratoFim() != null
					&& !negativadorContrato.getDataContratoFim().equals("")) {

				atualizarContratoNegativadorActionForm.setDataContratoFim(""
						+ formatoData.format(negativadorContrato
								.getDataContratoFim().getTime()));

			} else {
				atualizarContratoNegativadorActionForm.setDataContratoFim("");
			}

			if (negativadorContrato.getDataContratoEncerramento() != null
					&& !negativadorContrato.getDataContratoEncerramento()
							.equals("")) {

				atualizarContratoNegativadorActionForm
						.setDataContratoEncerramento(""
								+ formatoData.format(negativadorContrato
										.getDataContratoEncerramento()
										.getTime()));
			} else {
				atualizarContratoNegativadorActionForm
						.setDataContratoEncerramento("");
			}

			if (negativadorContrato.getContratoMotivoCancelamento() != null
					&& !negativadorContrato.getContratoMotivoCancelamento()
							.equals("")) {

				atualizarContratoNegativadorActionForm
						.setIdContratoMotivoCancelamento(""
								+ negativadorContrato
										.getContratoMotivoCancelamento()
										.getId());
			} else {
				atualizarContratoNegativadorActionForm
						.setIdContratoMotivoCancelamento("");
			}

			atualizarContratoNegativadorActionForm.setTime(Long
					.toString(negativadorContrato.getUltimaAlteracao()
							.getTime()));

			sessao.setAttribute("negativadorContrato", negativadorContrato);

		}

	}

}
/*
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
package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComando;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0253] Pesquisar Comando de A��o de Cobran�a
 * 
 * @author Rafael Santos
 * @since 24/01/2006
 */
public class PesquisarComandoAcaoCobrancaAction extends GcomAction {

	/**
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
				.findForward("retornarPesquisarComandoAcaoCobranca");

		HttpSession sessao = httpServletRequest.getSession(false);

		// Fachada fachada = Fachada.getInstancia();

		PesquisarComandoAcaoCobrancaActionForm pesquisarComandoAcaoCobrancaActionForm = (PesquisarComandoAcaoCobrancaActionForm) actionForm;

		String periodoGeracaoComandoInicial = pesquisarComandoAcaoCobrancaActionForm
				.getPeriodoGeracaoComandoInicial();
		String periodoGeracaoComandoFinal = pesquisarComandoAcaoCobrancaActionForm
				.getPeriodoGeracaoComandoFinal();

		String periodoExecucaoComandoInicial = pesquisarComandoAcaoCobrancaActionForm
				.getPeriodoExecucaoComandoInicial();
		String periodoExecucaoComandoFinal = pesquisarComandoAcaoCobrancaActionForm
				.getPeriodoExecucaoComandoFinal();

		// [FS0003] - Verificar data final menor que data inicial
		if ((periodoGeracaoComandoInicial != null && !periodoGeracaoComandoInicial
				.equals(""))
				&& (periodoGeracaoComandoFinal != null && !periodoGeracaoComandoFinal
						.equals(""))) {

			// inicial
			boolean valida = Util
					.validarDiaMesAno(periodoGeracaoComandoInicial);
			if (valida) {
				throw new ActionServletException(
						"atencao.data_incial_comando.invalida");
			}

			// final
			String anoFinal = periodoGeracaoComandoFinal.substring(6, 10);
			String mesFinal = periodoGeracaoComandoFinal.substring(3, 5);
			String diaFinal = periodoGeracaoComandoFinal.substring(0, 2);

			boolean validaFinal = Util
					.validarDiaMesAno(periodoGeracaoComandoFinal);
			if (validaFinal) {
				throw new ActionServletException(
						"atencao.data_final_comando.invalida");
			}

			Calendar periodoFinal = new GregorianCalendar();
			periodoFinal.set(Calendar.DATE, new Integer(diaFinal).intValue());
			periodoFinal.set(Calendar.MONTH,
					new Integer(mesFinal).intValue() - 1);
			periodoFinal.set(Calendar.YEAR, new Integer(anoFinal).intValue());

			if (periodoFinal.compareTo(new GregorianCalendar()) > 0) {
				throw new ActionServletException(
				// Data Final do Per�odo de Gera��o do Comando posterior � data
				// corrente
						"atencao.data_final_comando.maior.data_corrente");
			}

			Calendar calendarPeriodoGeracaoComandoInicial = new GregorianCalendar();
			calendarPeriodoGeracaoComandoInicial.setTime(Util
					.converteStringParaDate(periodoGeracaoComandoInicial));

			Calendar calendarPeriodoGeracaoComandoFinal = new GregorianCalendar();
			calendarPeriodoGeracaoComandoFinal.setTime(Util
					.converteStringParaDate(periodoGeracaoComandoFinal));

			if (calendarPeriodoGeracaoComandoInicial
					.compareTo(calendarPeriodoGeracaoComandoFinal) > 0) {
				httpServletRequest.setAttribute("nomeCampo",
						"periodoGeracaoComandoFinal");
				throw new ActionServletException(
				// Data Final do Per�odo de Gera��o do Comando anterior � Data
				// Inicial
						"atencao.geracao.data_inicial_comando.maior_data_final_comando");
			}
		}

		// [FS0003] - Verificar data final menor que data inicial
		if ((periodoExecucaoComandoInicial != null && !periodoExecucaoComandoInicial
				.equals(""))
				&& (periodoExecucaoComandoFinal != null && !periodoExecucaoComandoFinal
						.equals(""))) {

			// inicial
			boolean valida = Util
					.validarDiaMesAno(periodoExecucaoComandoInicial);
			if (valida) {
				throw new ActionServletException(
						"atencao.data_inicial_execucao.invalida");
			}

			// inicial
			boolean validaFinal = Util
					.validarDiaMesAno(periodoExecucaoComandoFinal);
			if (validaFinal) {
				throw new ActionServletException(
						"atencao.data_final_execucao.invalida");
			}

			// final
			String anoFinal = periodoExecucaoComandoFinal.substring(6, 10);
			String mesFinal = periodoExecucaoComandoFinal.substring(3, 5);
			String diaFinal = periodoExecucaoComandoFinal.substring(0, 2);

			Calendar periodoFinal = new GregorianCalendar();
			periodoFinal.set(Calendar.DATE, new Integer(diaFinal).intValue());
			periodoFinal.set(Calendar.MONTH,
					new Integer(mesFinal).intValue() - 1);
			periodoFinal.set(Calendar.YEAR, new Integer(anoFinal).intValue());

			if (periodoFinal.compareTo(new GregorianCalendar()) > 0) {
				throw new ActionServletException(
				// Data Final do Per�odo de Execu��o do Comando posterior � data
				// corrente
						"atencao.data_final_execucao.maior.data_corrente");
			}

			Calendar calendarPeriodoExecucaoComandoInicial = new GregorianCalendar();

			calendarPeriodoExecucaoComandoInicial.setTime(Util
					.converteStringParaDate(periodoExecucaoComandoInicial));

			Calendar calendarPeriodoExecucaoComandoFinal = new GregorianCalendar();
			calendarPeriodoExecucaoComandoFinal.setTime(Util
					.converteStringParaDate(periodoExecucaoComandoFinal));

			if (calendarPeriodoExecucaoComandoInicial
					.compareTo(calendarPeriodoExecucaoComandoFinal) > 0) {
				httpServletRequest.setAttribute("nomeCampo",
						"periodoExecucaoComandoFinal");
				throw new ActionServletException(// Data Final do Per�odo de
													// Execu��o do Comando
													// anterior � Data Inicial
						"atencao.execucao.data_inicial_comando.maior_data_final_comando");
			}
		}

		// A consulta de Filtro j� limite a quantidade m�xima de 50 registros
		// [FS0005] - Muitos Registros Encontrados
		// if (fachada.registroMaximo(CobrancaAcaoAtividadeComando.class) >
		// ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
		// throw new ActionServletException(
		// "atencao.pesquisa.muitosregistros");
		// }

		// Consultar Cobranca Acao Atividade Comando
		FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando = new FiltroCobrancaAcaoAtividadeComando();

		// id da cobran�a a��o
		if (pesquisarComandoAcaoCobrancaActionForm.getCobrancaAcao() != null
				&& !pesquisarComandoAcaoCobrancaActionForm.getCobrancaAcao()
						.equals("")
				&& !pesquisarComandoAcaoCobrancaActionForm.getCobrancaAcao()
						.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
			filtroCobrancaAcaoAtividadeComando
					.adicionarParametro(new ParametroSimples(
							FiltroCobrancaAcaoAtividadeComando.ID_COBRANCA_ACAO,
							pesquisarComandoAcaoCobrancaActionForm
									.getCobrancaAcao()));
		}

		// id da cobran�a atividade
		if (pesquisarComandoAcaoCobrancaActionForm.getCobrancaAtividade() != null
				&& !pesquisarComandoAcaoCobrancaActionForm
						.getCobrancaAtividade().equals("")
				&& !pesquisarComandoAcaoCobrancaActionForm
						.getCobrancaAtividade().equals(
								ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
			filtroCobrancaAcaoAtividadeComando
					.adicionarParametro(new ParametroSimples(
							FiltroCobrancaAcaoAtividadeComando.ID_COBRANCA_ATIVIDADE,
							pesquisarComandoAcaoCobrancaActionForm
									.getCobrancaAtividade()));
		}

		// per�odo de gera��o do comando
		if ((periodoGeracaoComandoInicial != null && !periodoGeracaoComandoInicial
				.equals(""))
				&& (periodoGeracaoComandoFinal != null && !periodoGeracaoComandoFinal
						.equals(""))) {
			Intervalo intervalo = new Intervalo(
					FiltroCobrancaAcaoAtividadeComando.COMANDO,
					Util
							.converteStringParaDateHora(periodoGeracaoComandoInicial
									+ " 00:00:00"),
					Util.converteStringParaDateHora(periodoGeracaoComandoFinal
							+ " 23:59:59"));
			filtroCobrancaAcaoAtividadeComando.adicionarParametro(intervalo);
		}

		// per�odo de execu��o do comando
		if ((periodoExecucaoComandoInicial != null && !periodoExecucaoComandoInicial
				.equals(""))
				&& (periodoExecucaoComandoFinal != null && !periodoExecucaoComandoFinal
						.equals(""))) {
			Intervalo intervalo = new Intervalo(
					FiltroCobrancaAcaoAtividadeComando.REALIZACAO,
					Util
							.converteStringParaDateHora(periodoExecucaoComandoInicial
									+ " 00:00:00"),
					Util.converteStringParaDateHora(periodoExecucaoComandoFinal
							+ " 23:59:59"));
			filtroCobrancaAcaoAtividadeComando.adicionarParametro(intervalo);
		}

		// id do usuario
		if (pesquisarComandoAcaoCobrancaActionForm.getIdUsuario() != null
				&& !pesquisarComandoAcaoCobrancaActionForm.getIdUsuario()
						.equals("")) {

			filtroCobrancaAcaoAtividadeComando
					.adicionarParametro(new ParametroSimples(
							FiltroCobrancaAcaoAtividadeComando.ID_USUARIO,
							pesquisarComandoAcaoCobrancaActionForm
									.getIdUsuario()));
		}
		String tituloComando = pesquisarComandoAcaoCobrancaActionForm
				.getTituloComando();
		String tipoPesquisa = (String) pesquisarComandoAcaoCobrancaActionForm
				.getTipoPesquisa();
		if (tituloComando != null && !tituloComando.trim().equalsIgnoreCase("")) {
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroCobrancaAcaoAtividadeComando
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroCobrancaAcaoAtividadeComando.DESCRICAO_TITULO,
								tituloComando));
			} else {
				filtroCobrancaAcaoAtividadeComando
						.adicionarParametro(new ComparacaoTexto(
								FiltroCobrancaAcaoAtividadeComando.DESCRICAO_TITULO,
								tituloComando));
			}
		}

		filtroCobrancaAcaoAtividadeComando
				.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.COBRANCA_ACAO);
		filtroCobrancaAcaoAtividadeComando
				.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.COBRANCA_ATIVIDADE);
		filtroCobrancaAcaoAtividadeComando
				.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.USUARIO);

		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroCobrancaAcaoAtividadeComando,
				CobrancaAcaoAtividadeComando.class.getName());
		Collection colecaoCobrancaAcaoAtividadeComando = (Collection) resultado
				.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");

		sessao.setAttribute("colecaoCobrancaAcaoAtividadeComando",
				colecaoCobrancaAcaoAtividadeComando);

		// [FS0006] - Nenhum registro encontrado
		if (colecaoCobrancaAcaoAtividadeComando == null
				|| colecaoCobrancaAcaoAtividadeComando.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		if (sessao.getAttribute("colecaoCobrancaAcao") != null) {
			sessao.removeAttribute("colecaoCobrancaAcao");
		}

		if (sessao.getAttribute("colecaoCobrancaAtividade") != null) {
			sessao.removeAttribute("colecaoCobrancaAtividade");
		}

		return retorno;
	}

}

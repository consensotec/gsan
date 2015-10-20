 /* 
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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
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
 * @date 20/09/2006
 */
public class ExibirCalendarioElaboracaoAcompanhamentoRoteiroAction extends
		GcomAction {

	/**
	 * Este caso de uso elabora ou mant�m uma programa��o de roteiro
	 * 
	 * 
	 * [UC0455] Exibir Calend�rio para Elabora��o ou Acompanhamento de Roteiro
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 20/09/2006
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

		ActionForward retorno = actionMapping
				.findForward("calendarioElaboracaoAcompanhamentoRoteiroAction");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		ExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm = (ExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm) actionForm;

		sessao.removeAttribute("elaboracao");
		sessao.removeAttribute("acompanhamento");

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String mesAnoReferencia = (String) httpServletRequest
				.getParameter("mesAnoReferencia");

		// Recebe o ano e o mes
		String ano = null;

		String mes = null;

		String anoCalendario = (String) httpServletRequest.getParameter("ano");

		String mesCalendario = (String) httpServletRequest.getParameter("mes");

		//String anoMesReferencia = null;

		// Verifica se o mes/ano foram mudados no calendario, senao continua o
		// fluxo verificando se o mesanoReferencia foi informado, caso
		// contrario, assume o corrente.
		if (anoCalendario != null && !anoCalendario.equalsIgnoreCase("")
				&& mesCalendario != null && !mesCalendario.equalsIgnoreCase("")) {

			Integer aux = new Integer(mesCalendario);

			aux = aux + 1;

			if (aux < 10) {

				mesCalendario = "0" + aux.toString();
			} else {
				mesCalendario = aux.toString();
			}

			// [FS0001] Validar m�s e ano de referencia

			String dataAuxiliar = null;

			dataAuxiliar = "01/" + mesCalendario + "/" + anoCalendario;

			Date dataValidacao = Util.converteStringParaDate(dataAuxiliar);

			Date dataAtual = new Date();

			if (httpServletRequest.getParameter("elaboracao") != null) {

				Calendar dataAtualVerificacao = new GregorianCalendar();
				dataAtualVerificacao.setTime(dataAtual);

				Calendar dataValidacaoVerificacao = new GregorianCalendar();
				dataValidacaoVerificacao.setTime(dataValidacao);

				if (mesCalendario != null && anoCalendario != null) {

					String anoMesCalendario = anoCalendario + mesCalendario;

					Date dataCorrente = new Date();
					String dataCorrenteTexto = Util.formatarData(dataCorrente);
					ano = dataCorrenteTexto.substring(6, 10);
					mes = dataCorrenteTexto.substring(3, 5);

					String anoMesAtual = ano + mes;

					if (Util.compararAnoMesReferencia(new Integer(
							anoMesCalendario), new Integer(anoMesAtual), "<")) {
						throw new ActionServletException(
								"atencao.ano_mes_referencia.invalida");
					}

				} else {

					if ((dataValidacaoVerificacao
							.compareTo(dataAtualVerificacao)) < 0) {
						throw new ActionServletException(
								"atencao.ano_mes_referencia.invalida");
					}
				}
				/*
				 * Integer difMeses = Util.dataDiff(dataAtual, dataValidacao);
				 * 
				 * if (difMeses > 0) { throw new ActionServletException(
				 * "atencao.ano_mes_referencia.invalida"); }
				 */
			}

			httpServletRequest.setAttribute("ano", anoCalendario);
			httpServletRequest.setAttribute("mes", mesCalendario);

			ano = anoCalendario;
			mes = mesCalendario;

			//anoMesReferencia = ano + mes;

			// boolean mesAnoValido = Util.validarMesAno(mesAnoReferencia);
			//			
			// if (mesAnoValido == false) {
			// throw new ActionServletException(
			// "atencao.ano_mes_referencia.invalida");
			// }

		} else if (mesAnoReferencia == null || mesAnoReferencia.equals("")) {

			// Recebe mesAnoReferecencia (mm/aaaa) e retorna
			// anoMesReferencia(aaaamm), caso o mesAno nao seja informado,
			// assume o
			// corrente

			Date dataCorrente = new Date();
			String dataCorrenteTexto = Util.formatarData(dataCorrente);
			ano = dataCorrenteTexto.substring(6, 10);
			mes = dataCorrenteTexto.substring(3, 5);
			//String anoMesCorrente = ano + mes;
			//anoMesReferencia = anoMesCorrente;

		} else {

			boolean mesAnoValido = Util.validarMesAno(mesAnoReferencia);

			if (mesAnoValido == false) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia.invalida");
			}

			// [FS0001] Validar m�s e ano de referencia
			String dataAuxiliar = null;

			dataAuxiliar = "01/" + mesAnoReferencia;

			Date dataValidacao = Util.converteStringParaDate(dataAuxiliar);

			Date dataAtual = new Date();

			if (httpServletRequest.getParameter("elaboracao") != null) {

				Integer difMeses = Util.dataDiff(dataAtual, dataValidacao);

				if (difMeses > 0) {
					throw new ActionServletException(
							"atencao.ano_mes_referencia.invalida");

				}
			}

			mes = mesAnoReferencia.substring(0, 2);
			ano = mesAnoReferencia.substring(3, 7);

			//anoMesReferencia = ano + mes;

		}

		Calendar calendario = Calendar.getInstance();
		int ultimoDia = 0;

		httpServletRequest.setAttribute("ano", ano);
		httpServletRequest.setAttribute("mes", new Integer(mes) - 1);

		calendario.set(new Integer(ano), new Integer(mes), 1);
		// ultimoDia = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);

		ultimoDia = 31;
		Integer[] vetorEstilos = new Integer[ultimoDia];

		mesAnoReferencia = null;

		mesAnoReferencia = mes + "/" + ano;

		// Pesquisa a Programacao de Roteiro
		Collection colecaoProgramacaoRoteiro = fachada
				.consultarProgramacaoRoteiro(mesAnoReferencia, usuario
						.getUnidadeOrganizacional().getId());

		if (httpServletRequest.getParameter("elaboracao") != null) {
			sessao.setAttribute("elaboracao", "elaboracao");
			exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm
					.setCabecalho("Elabora��o");
			exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm
					.setTitulo("elabora��o");
		} else {
			sessao.setAttribute("acompanhamento", "acompanhamento");
			exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm
					.setCabecalho("Acompanhamento");
			exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm
					.setTitulo("acompanhamento");
		}

		if (colecaoProgramacaoRoteiro != null) {

			Iterator iteratorRoteiro = colecaoProgramacaoRoteiro.iterator();
			Date dataRoteiro;
			ProgramacaoRoteiro programacaoRoteiro = null;

			// Integer indicador

			while (iteratorRoteiro.hasNext()) {

				programacaoRoteiro = (ProgramacaoRoteiro) iteratorRoteiro
						.next();

				dataRoteiro = programacaoRoteiro.getDataRoteiro();

				/*
				 * Caso exista um intervalo maior do que um dia no
				 * abastecimento, verifica a qtde de dias entre o inicio e o fim
				 * as e jogo num vetor informando o estilo deste dia para o
				 * calend�rio
				 * 
				 */

				Calendar obterDia = Calendar.getInstance();

				// FiltroOrdemServicoProgramacao filtroOrdemServicoProgramacao =
				// new FiltroOrdemServicoProgramacao();

				// Collection colecaoOrdemServicoProgramacao = null;

				int i = 0;

				// Date dataAtual = new Date();

				if (sessao.getAttribute("elaboracao") != null) {

					exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm
							.setCabecalho("Elabora��o");
					exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm
							.setTitulo("elabora��o");
					while (i < ultimoDia + 1) {

						/*
						 * Inicialmente todos os dias recebem o id da situacao
						 * em aberto, caso a situacao nao seja em aberto,
						 * situacao � alterada
						 */
						// vetorEstilos[i] =
						// ConstantesSistema.SITUACAO_ROTEIRO_ELABORACAO_ABERTO;
						/*
						 * Caso o dia possua Roteiro e Identificador de
						 * fechamento esteja preenchido.
						 */
						/*
						 * filtroOrdemServicoProgramacao .adicionarParametro(new
						 * ParametroSimples(
						 * FiltroOrdemServicoProgramacao.PROGRAMACAO_ROTEIRO,
						 * programacaoRoteiro.getId()));
						 * 
						 * filtroOrdemServicoProgramacao .adicionarParametro(new
						 * ParametroSimples(
						 * FiltroOrdemServicoProgramacao.INDICADOR_ATIVO,
						 * OrdemServicoProgramacao.INDICADOR_ATIVO));
						 * 
						 * colecaoOrdemServicoProgramacao = fachada.pesquisar(
						 * filtroOrdemServicoProgramacao,
						 * OrdemServicoProgramacao.class.getName());
						 */

						Integer qtdOrdemServicoProgramacao = fachada
								.verificarExistenciaOSProgramacao(programacaoRoteiro
										.getId());

						if (qtdOrdemServicoProgramacao == 0) {

							obterDia.setTime(dataRoteiro);
							vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] = ConstantesSistema.SITUACAO_ROTEIRO_ELABORACAO_FECHAMENTO_NULO;

						} else {

							obterDia.setTime(dataRoteiro);
							vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] = ConstantesSistema.SITUACAO_ROTEIRO_ELABORACAO_FECHAMENTO_PREENCHIDO;
						}

						/*
						 * if (colecaoOrdemServicoProgramacao == null) {
						 * 
						 * obterDia.setTime(dataRoteiro);
						 * vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] =
						 * ConstantesSistema.SITUACAO_ROTEIRO_ELABORACAO_FECHAMENTO_NULO; }
						 * 
						 * if (colecaoOrdemServicoProgramacao != null) {
						 * 
						 * obterDia.setTime(dataRoteiro);
						 * vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] =
						 * ConstantesSistema.SITUACAO_ROTEIRO_ELABORACAO_FECHAMENTO_PREENCHIDO; }
						 */

						i++;
					}

				} else {
					exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm
							.setCabecalho("Acompanhamento");
					exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm
							.setTitulo("acompanhamento");
					while (i < ultimoDia) {

						/*
						 * Inicialmente todos os dias recebem o id da situacao
						 * em aberto, caso a situacao nao seja em aberto,
						 * situacao � alterada
						 */
						// vetorEstilos[i] =
						// ConstantesSistema.SITUACAO_ROTEIRO_ACOMPANHAMENTO_ABERTO;
						/*
						 * Caso o dia possua Roteiro e Identificador de
						 * fechamento esteja preenchido.
						 */
						/*
						 * filtroOrdemServicoProgramacao .adicionarParametro(new
						 * ParametroSimples(
						 * FiltroOrdemServicoProgramacao.PROGRAMACAO_ROTEIRO,
						 * programacaoRoteiro.getId()));
						 * 
						 * filtroOrdemServicoProgramacao .adicionarParametro(new
						 * ParametroSimples(
						 * FiltroOrdemServicoProgramacao.INDICADOR_ATIVO,
						 * OrdemServicoProgramacao.INDICADOR_ATIVO));
						 * 
						 * colecaoOrdemServicoProgramacao = fachada.pesquisar(
						 * filtroOrdemServicoProgramacao,
						 * OrdemServicoProgramacao.class.getName());
						 */

						Integer qtdOrdemServicoProgramacao = fachada
								.verificarExistenciaOSProgramacao(programacaoRoteiro
										.getId());

						if (qtdOrdemServicoProgramacao == 0) {

							obterDia.setTime(dataRoteiro);
							vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] = ConstantesSistema.SITUACAO_ROTEIRO_ACOMPANHAMENTO_FECHAMENTO_NULO;

						} else {

							obterDia.setTime(dataRoteiro);
							vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] = ConstantesSistema.SITUACAO_ROTEIRO_ACOMPANHAMENTO_FECHAMENTO_PREENCHIDO;
						}

						/*
						 * if (colecaoOrdemServicoProgramacao.isEmpty()) {
						 * 
						 * obterDia.setTime(dataRoteiro);
						 * vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] =
						 * ConstantesSistema.SITUACAO_ROTEIRO_ACOMPANHAMENTO_FECHAMENTO_NULO; }
						 * 
						 * if (!colecaoOrdemServicoProgramacao.isEmpty()) {
						 * 
						 * obterDia.setTime(dataRoteiro);
						 * vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] =
						 * ConstantesSistema.SITUACAO_ROTEIRO_ACOMPANHAMENTO_FECHAMENTO_PREENCHIDO; }
						 */

						i++;
					}

				}
			}

		}

		int cont = 0;

		Date dataAtual = new Date();

		Integer dia = Util.getDiaMes(dataAtual);

		if (mesCalendario != null && anoCalendario != null && httpServletRequest.getParameter("elaboracao") != null) {

			String anoMesCalendario = anoCalendario + mesCalendario;

			Date dataCorrente = new Date();
			String dataCorrenteTexto = Util.formatarData(dataCorrente);
			ano = dataCorrenteTexto.substring(6, 10);
			mes = dataCorrenteTexto.substring(3, 5);

			String anoMesAtual = ano + mes;

			if (!Util.compararAnoMesReferencia(new Integer(anoMesCalendario),
					new Integer(anoMesAtual), "=")) {
				
				String diaAux = "01";
				dia = new Integer(diaAux);
			}

		}

		String situacoes = "";
		while (cont < vetorEstilos.length) {

			if ((cont + 2 <= dia.intValue())
					&& (sessao.getAttribute("elaboracao") != null)) {

				vetorEstilos[cont] = ConstantesSistema.SITUACAO_ROTEIRO_ACOMPANHAMENTO_ABERTO;

			}

			if ((vetorEstilos[cont] == null)
					&& (sessao.getAttribute("elaboracao") == null)) {

				vetorEstilos[cont] = ConstantesSistema.SITUACAO_ROTEIRO_ACOMPANHAMENTO_ABERTO;

			}

			if ((vetorEstilos[cont] == null)
					&& (sessao.getAttribute("elaboracao") != null)) {

				vetorEstilos[cont] = ConstantesSistema.SITUACAO_ROTEIRO_ELABORACAO_ABERTO;

			}

			if (situacoes.length() < 1) {
				situacoes = vetorEstilos[cont].toString();
			} else {
				situacoes = situacoes + "," + vetorEstilos[cont].toString();
			}

			cont++;
		}

		httpServletRequest.setAttribute("situacoes", situacoes);

		return retorno;

	}
}

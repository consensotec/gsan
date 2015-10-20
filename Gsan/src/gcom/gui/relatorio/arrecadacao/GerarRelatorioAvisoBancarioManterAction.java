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
package gcom.gui.relatorio.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.aviso.bean.AvisoBancarioHelper;
import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.fachada.Fachada;
import gcom.gui.arrecadacao.aviso.FiltrarAvisoBancarioActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioManterAvisoBancario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza o filtro do aviso bancario de acordo com os par�metros informados
 * 
 * @author Vivianne Sousa
 * @created 13/03/2006
 */

public class GerarRelatorioAvisoBancarioManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		/**
		 * @author Rafael Corr�a
		 * @date 05/09/2006
		 * 
		 * @param actionMapping
		 * @param actionForm
		 * @param httpServletRequest
		 * @param httpServletResponse
		 * @return
		 */

		ActionForward retorno = null;

		Fachada fachada = Fachada.getInstancia();

		FiltrarAvisoBancarioActionForm filtrarAvisoBancarioActionForm = (FiltrarAvisoBancarioActionForm) actionForm;

		String arrecadadorCodAgente = filtrarAvisoBancarioActionForm
				.getArrecadadorCodAgente();
		String dataLancamentoInicioString = filtrarAvisoBancarioActionForm
				.getDataLancamentoInicio();
		String dataLancamentoFimString = filtrarAvisoBancarioActionForm
				.getDataLancamentoFim();
		String tipoAviso = filtrarAvisoBancarioActionForm.getTipoAviso();
		String idContaBancaria = filtrarAvisoBancarioActionForm
				.getIdContaBancaria();
		String idBancoConta = filtrarAvisoBancarioActionForm.getIdBanco();
		String codigoAgencia = filtrarAvisoBancarioActionForm.getCodAgencia();
		String numeroConta = filtrarAvisoBancarioActionForm.getNumeroConta();
		String idMovimento = filtrarAvisoBancarioActionForm.getIdMovimento();
		String codigoBancoArrecadador = filtrarAvisoBancarioActionForm
				.getCodigoBanco();
		String codigoRemessa = filtrarAvisoBancarioActionForm
				.getCodigoRemessa();
		String identificacaoServico = filtrarAvisoBancarioActionForm
				.getIdentificacaoServico();
		String numeroSequencial = filtrarAvisoBancarioActionForm
				.getNumeroSequencial();
		String periodoArrecadacaoInicio = filtrarAvisoBancarioActionForm
				.getPeriodoArrecadacaoInicio();
		String periodoArrecadacaoFim = filtrarAvisoBancarioActionForm
				.getPeriodoArrecadacaoFim();
		String dataPrevisaoCreditoDebitoInicioString = filtrarAvisoBancarioActionForm
				.getDataPrevisaoCreditoDebitoInicio();
		String dataPrevisaoCreditoDebitoFimString = filtrarAvisoBancarioActionForm
				.getDataPrevisaoCreditoDebitoFim();
		String dataRealizacaoCreditoDebitoInicioString = filtrarAvisoBancarioActionForm
				.getDataRealizacaoCreditoDebitoInicio();
		String dataRealizacaoCreditoDebitoFimString = filtrarAvisoBancarioActionForm
				.getDataRealizacaoCreditoDebitoFim();
		String intervaloValorRealizadoInicio = filtrarAvisoBancarioActionForm
				.getIntervaloValorRealizadoInicio();
		String intervaloValorRealizadoFim = filtrarAvisoBancarioActionForm
				.getIntervaloValorRealizadoFim();
		String avisoAbertoFechado = filtrarAvisoBancarioActionForm.getAviso();

		AvisoBancarioHelper avisoBancarioHelper = new AvisoBancarioHelper();

		AvisoBancarioHelper avisoBancarioHelperParametros = new AvisoBancarioHelper();

		Arrecadador arrecadadorParametros = null;

		ContaBancaria contaBancariaParametros = null;

		ArrecadadorMovimento arrecadadorMovimentoParametros = null;

		// Passando os Par�metros para os filtros...

		// Arrecadador

		if ((arrecadadorCodAgente != null)
				&& (!arrecadadorCodAgente.trim().equals(""))) {

			avisoBancarioHelper.setCodigoAgenteArrecadador(new Short(
					arrecadadorCodAgente));

			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.CODIGO_AGENTE, arrecadadorCodAgente));

			filtroArrecadador
					.adicionarCaminhoParaCarregamentoEntidade("cliente");

			Collection<Arrecadador> colecaoArrecadadores = fachada.pesquisar(
					filtroArrecadador, Arrecadador.class.getName());

			if (colecaoArrecadadores != null && !colecaoArrecadadores.isEmpty()) {
				arrecadadorParametros = colecaoArrecadadores.iterator().next();
			}
		}

		// Per�do Lan�amento

		if ((dataLancamentoInicioString != null)
				&& (!dataLancamentoInicioString.equals(""))) {

			Date dataLancamentoInicio = null;
			Date dataLancamentoFim = null;

			if ((dataLancamentoFimString == null)
					|| (dataLancamentoFimString.equals(""))) {
				dataLancamentoFimString = dataLancamentoInicioString;
			}

			dataLancamentoInicio = Util
					.converteStringParaDate(dataLancamentoInicioString);
			dataLancamentoFim = Util
					.converteStringParaDate(dataLancamentoFimString);

			avisoBancarioHelper.setDataLancamentoInicial(dataLancamentoInicio);
			avisoBancarioHelper.setDataLancamentoFinal(dataLancamentoFim);

			avisoBancarioHelperParametros
					.setDataLancamentoInicial(dataLancamentoInicio);
			avisoBancarioHelperParametros
					.setDataLancamentoFinal(dataLancamentoFim);
		}

		// Tipo Aviso

		if ((tipoAviso != null) && (!tipoAviso.trim().equals(""))
				&& (!tipoAviso.trim().equals("3"))) {
			avisoBancarioHelper.setIndicadorCreditoDebito(new Short(tipoAviso));

			avisoBancarioHelperParametros.setTipoAviso(tipoAviso);
		}

		// Conta Banc�ria

		if ((idContaBancaria != null) && (!idContaBancaria.trim().equals(""))) {
			avisoBancarioHelper
					.setIdContaBancaria(new Integer(idContaBancaria));

			Banco banco = new Banco();
			Agencia agencia = new Agencia();
			contaBancariaParametros = new ContaBancaria();

			banco.setId(new Integer(idBancoConta));
			agencia.setCodigoAgencia(new String(codigoAgencia));
			agencia.setBanco(banco);

			contaBancariaParametros.setNumeroConta(numeroConta);
			contaBancariaParametros.setAgencia(agencia);

		}

		// Movimento

		if ((idMovimento != null) && (!idMovimento.trim().equals(""))) {
			avisoBancarioHelper.setIdMovimentoArrecadador(new Integer(
					idMovimento));

			arrecadadorMovimentoParametros = new ArrecadadorMovimento();
			arrecadadorMovimentoParametros.setCodigoBanco(new Short(
					codigoBancoArrecadador));
			arrecadadorMovimentoParametros.setCodigoRemessa(new Short(
					codigoRemessa));
			arrecadadorMovimentoParametros
					.setDescricaoIdentificacaoServico(identificacaoServico);
			arrecadadorMovimentoParametros
					.setNumeroSequencialArquivo(new Integer(numeroSequencial));

		}

		// Per�odo Previs�o Cr�dito/D�bito

		if ((dataPrevisaoCreditoDebitoInicioString != null)
				&& (!dataPrevisaoCreditoDebitoInicioString.trim().equals(""))) {

			Date dataPrevisaoCreditoDebitoInicio = null;
			Date dataPrevisaoCreditoDebitoFim = null;

			if ((dataPrevisaoCreditoDebitoFimString == null)
					|| (dataPrevisaoCreditoDebitoFimString.equals(""))) {
				dataPrevisaoCreditoDebitoFimString = dataPrevisaoCreditoDebitoInicioString;
			}

			dataPrevisaoCreditoDebitoInicio = Util
					.converteStringParaDate(dataPrevisaoCreditoDebitoInicioString);
			dataPrevisaoCreditoDebitoFim = Util
					.converteStringParaDate(dataPrevisaoCreditoDebitoFimString);

			avisoBancarioHelper
					.setDataPrevistaInicial(dataPrevisaoCreditoDebitoInicio);
			avisoBancarioHelper
					.setDataPrevistaFinal(dataPrevisaoCreditoDebitoFim);

			avisoBancarioHelperParametros
					.setDataPrevistaInicial(dataPrevisaoCreditoDebitoInicio);
			avisoBancarioHelperParametros
					.setDataPrevistaFinal(dataPrevisaoCreditoDebitoFim);
		}

		// Per�odo Realiza��o Cr�dito/D�bito

		if ((dataRealizacaoCreditoDebitoInicioString != null)
				&& (!dataRealizacaoCreditoDebitoInicioString.trim().equals(""))) {

			Date dataRealizacaoCreditoDebitoInicio = null;
			Date dataRealizacaoCreditoDebitoFim = null;

			if ((dataRealizacaoCreditoDebitoFimString == null)
					|| (dataRealizacaoCreditoDebitoFimString.equals(""))) {
				dataRealizacaoCreditoDebitoFimString = dataRealizacaoCreditoDebitoInicioString;
			}

			dataRealizacaoCreditoDebitoInicio = Util
					.converteStringParaDate(dataRealizacaoCreditoDebitoInicioString);
			dataRealizacaoCreditoDebitoFim = Util
					.converteStringParaDate(dataRealizacaoCreditoDebitoFimString);

			avisoBancarioHelper
					.setDataRealizadaInicial(dataRealizacaoCreditoDebitoInicio);
			avisoBancarioHelper
					.setDataRealizadaFinal(dataRealizacaoCreditoDebitoFim);

			avisoBancarioHelperParametros
					.setDataRealizadaInicial(dataRealizacaoCreditoDebitoInicio);
			avisoBancarioHelperParametros
					.setDataRealizadaFinal(dataRealizacaoCreditoDebitoFim);
		}

		// Per�do Arrecada��o

		if ((periodoArrecadacaoInicio != null)
				&& (!periodoArrecadacaoInicio.trim().equals(""))) {

			int periodoArrecadacaoInicioTratado = Integer
					.parseInt(Util
							.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoInicio));

			int periodoArrecadacaoFimTratado = Integer.parseInt(Util
					.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoFim));

			avisoBancarioHelper
					.setAnoMesReferenciaArrecadacaoInicial(periodoArrecadacaoInicioTratado);
			avisoBancarioHelper
					.setAnoMesReferenciaArrecadacaoFinal(periodoArrecadacaoFimTratado);

			avisoBancarioHelperParametros
					.setAnoMesReferenciaArrecadacaoInicial(periodoArrecadacaoInicioTratado);
			avisoBancarioHelperParametros
					.setAnoMesReferenciaArrecadacaoFinal(periodoArrecadacaoFimTratado);
		}

		// Intervalo Valor Realizado

		if ((intervaloValorRealizadoInicio != null)
				&& (!intervaloValorRealizadoInicio.trim().equals(""))) {

			avisoBancarioHelper
					.setValorRealizadoInicial(Util
							.formatarMoedaRealparaBigDecimal(intervaloValorRealizadoInicio));
			avisoBancarioHelper
					.setValorRealizadoFinal(Util
							.formatarMoedaRealparaBigDecimal(intervaloValorRealizadoFim));

			avisoBancarioHelperParametros
					.setValorRealizadoInicial(Util
							.formatarMoedaRealparaBigDecimal(intervaloValorRealizadoInicio));
			avisoBancarioHelperParametros
					.setValorRealizadoFinal(Util
							.formatarMoedaRealparaBigDecimal(intervaloValorRealizadoFim));
		}

		// Aviso Abertos/Fechados

		if (avisoAbertoFechado != null && (!avisoAbertoFechado.equals("-1"))) {
			avisoBancarioHelper.setTipoAviso(avisoAbertoFechado);

			avisoBancarioHelperParametros.setTipoAviso(avisoAbertoFechado);
		}

		// cria uma inst�ncia da classe do relat�rio
		RelatorioManterAvisoBancario relatorioManterAvisoBancario = new RelatorioManterAvisoBancario(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterAvisoBancario.addParametro("avisoBancarioHelper",
				avisoBancarioHelper);
		relatorioManterAvisoBancario.addParametro(
				"avisoBancarioHelperParametros", avisoBancarioHelperParametros);
		relatorioManterAvisoBancario.addParametro("arrecadadorParametros",
				arrecadadorParametros);
		relatorioManterAvisoBancario.addParametro("contaBancariaParametros",
				contaBancariaParametros);
		relatorioManterAvisoBancario.addParametro(
				"arrecadadorMovimentoParametros",
				arrecadadorMovimentoParametros);

		// chama o met�do de gerar relat�rio passando o c�digo da analise
		// como par�metro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterAvisoBancario.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterAvisoBancario,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}
}

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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
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
 * Description of the Class
 * 
 * @author Tiago Moreno
 * @create 16/02/2006
 * 
 */
public class PesquisarAvisoBancarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("pesquisarAvisoBancarioResultado");

		// Obt�m a inst�ncia da fachada
		//Fachada fachada = Fachada.getInstancia();

		// cria sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
		boolean peloMenosUmParametroInformado = false;

		PesquisarAvisoBancarioActionForm pesquisarAvisoBancarioActionForm = (PesquisarAvisoBancarioActionForm) actionForm;

		String idArrecadador = (String) pesquisarAvisoBancarioActionForm.getIdArrecadador();
		String dataLancamentoInicio = (String) pesquisarAvisoBancarioActionForm.getDataLancamentoInicio();
		String dataLancamentoFim = (String) pesquisarAvisoBancarioActionForm.getDataLancamentoFim();
		String tipoAviso = (String) pesquisarAvisoBancarioActionForm.getTipoAviso();
		
		String idContaBancaria = (String)pesquisarAvisoBancarioActionForm.getIdConta();
		String idMovimento = (String) pesquisarAvisoBancarioActionForm.getIdMovimento();
		
		String periodoArrecadacaoInicio = (String) pesquisarAvisoBancarioActionForm.getPeriodoArrecadacaoInicio();
		String periodoArrecadacaoFim = (String) pesquisarAvisoBancarioActionForm.getPeriodoArrecadacaoFim();
		String dataPrevisaoCreditoDebitoInicio = (String) pesquisarAvisoBancarioActionForm.getDataPrevisaoCreditoDebitoInicio();
		String dataPrevisaoCreditoDebitoFim = (String) pesquisarAvisoBancarioActionForm.getDataPrevisaoCreditoDebitoFim();
		/*String intervaloValorPrevistoInicio = (String) pesquisarAvisoBancarioActionForm.getIntervaloValorPrevistoInicio();
		String intervaloValorPrevistoFim = (String) pesquisarAvisoBancarioActionForm.getIntervaloValorPrevistoFim();*/
		String dataRealizacaoCreditoDebitoInicio = (String) pesquisarAvisoBancarioActionForm.getDataRealizacaoCreditoDebitoInicio();
		String dataRealizacaoCreditoDebitoFim = (String) pesquisarAvisoBancarioActionForm.getDataRealizacaoCreditoDebitoFim();
		String intervaloValorRealizadoInicio = (String) pesquisarAvisoBancarioActionForm.getIntervaloValorRealizadoInicio();
		String intervaloValorRealizadoFim = (String) pesquisarAvisoBancarioActionForm.getIntervaloValorRealizadoFim();

		// Validando datas dos campos dataLancamento Inicio e Fim
		
		if ((dataLancamentoFim == null)|| (dataLancamentoFim.equals(""))){
			dataLancamentoFim = dataLancamentoInicio;				
		}
		
		if ((dataLancamentoInicio.trim().length() == 10)
				&& (dataLancamentoFim.trim().length() == 10)) {

			Calendar calendarInicio = new GregorianCalendar();
			Calendar calendarFim = new GregorianCalendar();

			calendarInicio.set(Calendar.DAY_OF_MONTH, new Integer(
					dataLancamentoInicio.substring(0, 2)).intValue());
			calendarInicio.set(Calendar.MONTH, new Integer(dataLancamentoInicio
					.substring(3, 5)).intValue());
			calendarInicio.set(Calendar.YEAR, new Integer(dataLancamentoInicio
					.substring(6, 10)).intValue());

			calendarFim.set(Calendar.DAY_OF_MONTH, new Integer(
					dataLancamentoFim.substring(0, 2)).intValue());
			calendarFim.set(Calendar.MONTH, new Integer(dataLancamentoFim
					.substring(3, 5)).intValue());
			calendarFim.set(Calendar.YEAR, new Integer(dataLancamentoFim
					.substring(6, 10)).intValue());
			// joga exess�o
			if (calendarFim.compareTo(calendarInicio) < 0) {
				throw new ActionServletException(
						"atencao.data_fim_menor_inicio");
			}
		}

		// Validando datas dos campos dataPrevisaoCreditoDebito Inicio e Fim
		if ((dataPrevisaoCreditoDebitoFim == null)|| (dataPrevisaoCreditoDebitoFim.equals(""))){
			dataPrevisaoCreditoDebitoFim = dataPrevisaoCreditoDebitoInicio;				
		}
		
		if ((dataPrevisaoCreditoDebitoInicio.trim().length() == 10)
				&& (dataPrevisaoCreditoDebitoFim.trim().length() == 10)) {

			Calendar calendarInicio = new GregorianCalendar();
			Calendar calendarFim = new GregorianCalendar();

			calendarInicio
					.set(Calendar.DAY_OF_MONTH, new Integer(
							dataPrevisaoCreditoDebitoInicio.substring(0, 2))
							.intValue());
			calendarInicio
					.set(Calendar.MONTH, new Integer(
							dataPrevisaoCreditoDebitoInicio.substring(3, 5))
							.intValue());
			calendarInicio.set(Calendar.YEAR, new Integer(
					dataPrevisaoCreditoDebitoInicio.substring(6, 10))
					.intValue());

			calendarFim.set(Calendar.DAY_OF_MONTH, new Integer(
					dataPrevisaoCreditoDebitoFim.substring(0, 2)).intValue());
			calendarFim.set(Calendar.MONTH, new Integer(
					dataPrevisaoCreditoDebitoFim.substring(3, 5)).intValue());
			calendarFim.set(Calendar.YEAR, new Integer(
					dataPrevisaoCreditoDebitoFim.substring(6, 10)).intValue());
			// joga exess�o
			if (calendarFim.compareTo(calendarInicio) < 0) {
				throw new ActionServletException(
						"atencao.data_fim_menor_inicio");
			}
		}

		// Validando  Intervalo de Valor Previsto
		// retirado depois da altera��o na tabela de aviso bancario
		/*if (intervaloValorPrevistoInicio != null && intervaloValorPrevistoFim != null) {
			if (!intervaloValorPrevistoInicio.equals("") && !intervaloValorPrevistoFim.equals("")) {
				BigDecimal intervaloValorPrevistoInicioTratado = new BigDecimal(intervaloValorPrevistoInicio.replace(",", "."));
				BigDecimal intervaloValorPrevistoFimTratado = new BigDecimal(intervaloValorPrevistoFim.replace(",", "."));
				if (intervaloValorPrevistoInicioTratado.compareTo(intervaloValorPrevistoFimTratado) == 1) {
					throw new ActionServletException("atencao.valorprevistofinal.menorque");
				}
			}
		}*/
		
		// Validando datas dos campos dataRealizacaoCreditoDebito Inicio e Fim
		if ((dataRealizacaoCreditoDebitoFim == null)|| (dataRealizacaoCreditoDebitoFim.equals(""))){
			dataRealizacaoCreditoDebitoFim = dataRealizacaoCreditoDebitoInicio;	
		}
		
		if ((dataRealizacaoCreditoDebitoInicio.trim().length() == 10)
				&& (dataRealizacaoCreditoDebitoFim.trim().length() == 10)) {

			Calendar calendarInicio = new GregorianCalendar();
			Calendar calendarFim = new GregorianCalendar();

			calendarInicio.set(Calendar.DAY_OF_MONTH, new Integer(
					dataRealizacaoCreditoDebitoInicio.substring(0, 2))
					.intValue());
			calendarInicio.set(Calendar.MONTH, new Integer(
					dataRealizacaoCreditoDebitoInicio.substring(3, 5))
					.intValue());
			calendarInicio.set(Calendar.YEAR, new Integer(
					dataRealizacaoCreditoDebitoInicio.substring(6, 10))
					.intValue());

			calendarFim.set(Calendar.DAY_OF_MONTH, new Integer(
					dataRealizacaoCreditoDebitoFim.substring(0, 2)).intValue());
			calendarFim.set(Calendar.MONTH, new Integer(
					dataRealizacaoCreditoDebitoFim.substring(3, 5)).intValue());
			calendarFim
					.set(Calendar.YEAR, new Integer(
							dataRealizacaoCreditoDebitoFim.substring(6, 10))
							.intValue());
			// joga exess�o
			if (calendarFim.compareTo(calendarInicio) < 0) {
				throw new ActionServletException(
						"atencao.data_fim_menor_inicio");
			}
		}

		// Validando  Intervalo de Valor Realizado
		if (intervaloValorRealizadoInicio != null && intervaloValorRealizadoFim != null) {
			if (!intervaloValorRealizadoInicio.equals("") && !intervaloValorRealizadoFim.equals("")) {

//				BigDecimal intervaloValorRealizadoInicioTratado = new BigDecimal(intervaloValorRealizadoInicio.replace(",", "."));
//				BigDecimal intervaloValorRealizadoFimTratado = new BigDecimal(intervaloValorRealizadoFim.replace(",", "."));
				
				intervaloValorRealizadoInicio = intervaloValorRealizadoInicio.replace(".", "");
				BigDecimal intervaloValorRealizadoInicioTratado = new BigDecimal(intervaloValorRealizadoInicio.replace(",", "."));
				intervaloValorRealizadoFim = intervaloValorRealizadoFim.replace(".", "");
				BigDecimal intervaloValorRealizadoFimTratado = new BigDecimal(intervaloValorRealizadoFim.replace(",", "."));
				
				if (intervaloValorRealizadoInicioTratado.compareTo(intervaloValorRealizadoFimTratado) == 1) {
					throw new ActionServletException("atencao.valorrealizadofinal.menorque");
				}
			}
		}
		
		
		// Passando os Parametros pros filtros...
		if ((idArrecadador != null) && (!idArrecadador.trim().equals(""))) {
			filtroAvisoBancario.adicionarParametro(new ParametroSimples(
					FiltroAvisoBancario.ARRECADADOR_CODIGO_AGENTE, idArrecadador));
			peloMenosUmParametroInformado = true;
		}
		if ((dataLancamentoInicio != null)
				&& (!dataLancamentoInicio.trim().equals(""))) {
			filtroAvisoBancario.adicionarParametro(new Intervalo(
					FiltroAvisoBancario.DATA_LANCAMENTO, Util.converteStringParaDate(dataLancamentoInicio),
					Util.converteStringParaDate(dataLancamentoFim)));
			peloMenosUmParametroInformado = true;
		}
		
		
		filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade("contaBancaria");
		if ((idContaBancaria != null) && (!idContaBancaria.trim().equals(""))) {
			filtroAvisoBancario.adicionarParametro(new ParametroSimples(
					FiltroAvisoBancario.CONTA_BANCARIA_ID, idContaBancaria));
			peloMenosUmParametroInformado = true;
		}
		
		filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade("arrecadadorMovimento");
		if ((idMovimento != null) && (!idMovimento.trim().equals(""))) {
			filtroAvisoBancario.adicionarParametro(new ParametroSimples(
					FiltroAvisoBancario.ARRECADADOR_MOVIMENTO_ID, idMovimento));
			peloMenosUmParametroInformado = true;
		}


		if ((dataPrevisaoCreditoDebitoInicio != null)
				&& (!dataPrevisaoCreditoDebitoInicio.trim().equals(""))) {
			filtroAvisoBancario.adicionarParametro(new Intervalo(
					FiltroAvisoBancario.DATA_PREVISTA,
					Util.converteStringParaDate(dataPrevisaoCreditoDebitoInicio),
					Util.converteStringParaDate(dataPrevisaoCreditoDebitoFim)));
			peloMenosUmParametroInformado = true;
		}
		if ((dataRealizacaoCreditoDebitoInicio != null)
				&& (!dataRealizacaoCreditoDebitoInicio.trim().equals(""))) {
			
			filtroAvisoBancario.adicionarParametro(new Intervalo(
					FiltroAvisoBancario.DATA_REALIZADA,
					Util.converteStringParaDate(dataRealizacaoCreditoDebitoInicio),
					Util.converteStringParaDate(dataRealizacaoCreditoDebitoFim)));
			peloMenosUmParametroInformado = true;
		}

		// Tratando os periodos (mes/ano) para que possam ser feitos os filtros
		if ((periodoArrecadacaoInicio != null)
				&& (!periodoArrecadacaoInicio.trim().equals(""))) {

			int periodoArrecadacaoInicioTratado = Integer.parseInt(Util.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoInicio)) ;

			int periodoArrecadacaoFimTratado = Integer.parseInt(Util.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoFim));

			filtroAvisoBancario.adicionarParametro(new Intervalo(
					FiltroAvisoBancario.ANO_MES_REFERENCIA_ARRECADACAO,
					periodoArrecadacaoInicioTratado,
					periodoArrecadacaoFimTratado));
			peloMenosUmParametroInformado = true;
		}

		if ((intervaloValorRealizadoInicio != null)
				&& (!intervaloValorRealizadoInicio.trim().equals(""))) {
			filtroAvisoBancario.adicionarParametro(new Intervalo(
					FiltroAvisoBancario.VALOR_REALIZADO,
					new BigDecimal(intervaloValorRealizadoInicio.replace(",", ".")),
					new BigDecimal(intervaloValorRealizadoFim.replace(",", "."))));
			peloMenosUmParametroInformado = true;
		}

		// retirado depois da altera��o na tabela de aviso bancario
		/*if ((intervaloValorPrevistoInicio != null)
				&& (!intervaloValorPrevistoInicio.trim().equals(""))) {
			filtroAvisoBancario.adicionarParametro(new Intervalo(
					FiltroAvisoBancario.VALOR_PREVISTO,
					new BigDecimal(intervaloValorPrevistoInicio.replace(",", ".")),
					new BigDecimal(intervaloValorPrevistoFim.replace(",", "."))));
			peloMenosUmParametroInformado = true;
		}*/
		


		if ((tipoAviso != null) && (!tipoAviso.trim().equals(""))) {
			filtroAvisoBancario.adicionarParametro(new ParametroSimples(
					FiltroAvisoBancario.INDICADOR_CREDITO_DEBITO, tipoAviso));
			peloMenosUmParametroInformado = true;
		}
		
		// [FS0006] Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade("arrecadador.cliente");

		Collection<AvisoBancario> colecaoAvisoBancario = null;
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroAvisoBancario, AvisoBancario.class.getName());
		colecaoAvisoBancario= (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");

		// valida se a colecao esta vazia
		if (colecaoAvisoBancario == null) {
			throw new ActionServletException("atencao.colecao_vazia");
		} else {
			if (!colecaoAvisoBancario.isEmpty()) {
				// joga a colecao na sess�o
				sessao.setAttribute("colecaoAvisoBancario",
						colecaoAvisoBancario);
			} else {
				throw new ActionServletException("atencao.colecao_vazia");
			}
		}

		return retorno;
	}
}
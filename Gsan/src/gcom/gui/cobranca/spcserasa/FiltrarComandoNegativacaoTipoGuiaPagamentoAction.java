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
 * Thiago Vieira
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.bean.ComandoNegativacaoTipoGuiaPagamentoHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class FiltrarComandoNegativacaoTipoGuiaPagamentoAction extends
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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("exibirResultadoConsultarGuiaPagamento");
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm form = (FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm) actionForm;

		ComandoNegativacaoTipoGuiaPagamentoHelper comandoNegativacaoTipoGuiaPagamentoHelper = new ComandoNegativacaoTipoGuiaPagamentoHelper();

		if (form.getTitulo() != null && !form.getTitulo().equals("")) {
			comandoNegativacaoTipoGuiaPagamentoHelper.setTitulo(form
					.getTitulo());
		}

		if (form.getIdNegativador() != null
				&& !form.getIdNegativador().equals("-1")) {
			comandoNegativacaoTipoGuiaPagamentoHelper
					.setIdNegativador(new Integer(form.getIdNegativador()));
		}

		if (form.getTipoPesquisaTitulo() != null
				&& !form.getTipoPesquisaTitulo().equals("")) {
			comandoNegativacaoTipoGuiaPagamentoHelper
					.setTipoPesquisaTitulo(form.getTipoPesquisaTitulo());
		}

		if (form.getComandoSimulado() != null
				&& !form.getComandoSimulado().equals("")) {
			comandoNegativacaoTipoGuiaPagamentoHelper.setComandoSimulado(Short
					.parseShort(form.getComandoSimulado()));
		}

		if (form.getCodigoCliente() != null
				&& !form.getCodigoCliente().equals("")) {
			comandoNegativacaoTipoGuiaPagamentoHelper
					.setCodigoCliente(new Integer(form.getCodigoCliente()));
		}

		if ((form.getGeracaoComandoDataInicial() != null && !form
				.getGeracaoComandoDataInicial().equals(""))
				&& (form.getGeracaoComandoDataFinal() != null && !form
						.getGeracaoComandoDataFinal().equals(""))) {

			Date geracaoComandoDataInicial = Util.converteStringParaDate(form
					.getGeracaoComandoDataInicial());
			Date geracaoComandoDataFinal = Util.converteStringParaDate(form
					.getGeracaoComandoDataFinal());
			// Se data inicio maior que data fim
			if (Util.compararData(geracaoComandoDataInicial,
					geracaoComandoDataFinal) == 1) {
				String dataInicio = Util
						.formatarData(geracaoComandoDataInicial);
				String dataFim = Util.formatarData(geracaoComandoDataFinal);
				throw new ActionServletException(
						"atencao.data_inicial_maior_data_final", dataInicio,
						dataFim);
			}

			comandoNegativacaoTipoGuiaPagamentoHelper
					.setGeracaoComandoDataInicial(Util
							.converteStringParaDate(form
									.getGeracaoComandoDataInicial()));
			comandoNegativacaoTipoGuiaPagamentoHelper
					.setGeracaoComandoDataFinal(Util
							.converteStringParaDate(form
									.getGeracaoComandoDataFinal()));
		}

		if ((form.getExecucaoComandoDataInicial() != null && !form
				.getExecucaoComandoDataInicial().equals(""))
				&& (form.getExecucaoComandoDataFinal() != null && !form
						.getExecucaoComandoDataFinal().equals(""))) {

			Date execucaoComandoDataInicial = Util.converteStringParaDate(form
					.getExecucaoComandoDataInicial());
			Date execucaoComandoDataFinal = Util.converteStringParaDate(form
					.getExecucaoComandoDataFinal());
			// Se data inicio maior que data fim
			if (Util.compararData(execucaoComandoDataInicial,
					execucaoComandoDataFinal) == 1) {
				String dataInicio = Util
						.formatarData(execucaoComandoDataInicial);
				String dataFim = Util.formatarData(execucaoComandoDataFinal);
				throw new ActionServletException(
						"atencao.data_inicial_maior_data_final", dataInicio,
						dataFim);
			}

			comandoNegativacaoTipoGuiaPagamentoHelper
					.setExecucaoComandoDataInicial(Util
							.converteStringParaDate(form
									.getExecucaoComandoDataInicial()));
			comandoNegativacaoTipoGuiaPagamentoHelper
					.setExecucaoComandoDataFinal(Util
							.converteStringParaDate(form
									.getExecucaoComandoDataFinal()));
		}

		if ((form.getReferenciaDebitoDataInicial() != null && !form
				.getReferenciaDebitoDataInicial().equals(""))
				&& (form.getReferenciaDebitoDataFinal() != null && !form
						.getReferenciaDebitoDataFinal().equals(""))) {

			Integer referenciaDebitoDataInicial = Util
					.formatarMesAnoComBarraParaAnoMes(form
							.getReferenciaDebitoDataInicial());
			Integer referenciaDebitoDataFinal = Util
					.formatarMesAnoComBarraParaAnoMes(form
							.getReferenciaDebitoDataFinal());

			if (referenciaDebitoDataFinal < referenciaDebitoDataInicial) {
				throw new ActionServletException(
						"atencao.referencia_final_menor_referencia_inicial");
			}

			comandoNegativacaoTipoGuiaPagamentoHelper
					.setReferenciaDebitoDataInicial(referenciaDebitoDataInicial);
			comandoNegativacaoTipoGuiaPagamentoHelper
					.setReferenciaDebitoDataFinal(referenciaDebitoDataFinal);

		}

		if ((form.getVencimentoDebitoDataInicial() != null && !form
				.getVencimentoDebitoDataInicial().equals(""))
				&& (form.getVencimentoDebitoDataFinal() != null && !form
						.getVencimentoDebitoDataFinal().equals(""))) {

			Date vencimentoDebitoDataInicial = Util.converteStringParaDate(form
					.getVencimentoDebitoDataInicial());
			Date vencimentoDebitoDataFinal = Util.converteStringParaDate(form
					.getVencimentoDebitoDataFinal());
			// Se data inicio maior que data fim
			if (Util.compararData(vencimentoDebitoDataInicial,
					vencimentoDebitoDataFinal) == 1) {
				String dataInicio = Util
						.formatarData(vencimentoDebitoDataInicial);
				String dataFim = Util.formatarData(vencimentoDebitoDataFinal);
				throw new ActionServletException(
						"atencao.data_inicial_maior_data_final", dataInicio,
						dataFim);
			}

			comandoNegativacaoTipoGuiaPagamentoHelper
					.setVencimentoDebitoDataInicial(Util
							.converteStringParaDate(form
									.getVencimentoDebitoDataInicial()));
			comandoNegativacaoTipoGuiaPagamentoHelper
					.setVencimentoDebitoDataFinal(Util
							.converteStringParaDate(form
									.getVencimentoDebitoDataFinal()));
		}

		if (form.getValorDebitoInicial() != null
				&& !form.getValorDebitoInicial().equals("")) {
			String value = form.getValorDebitoInicial().replace(".", "");
			value = value.replaceAll(",", ".");
			
			comandoNegativacaoTipoGuiaPagamentoHelper
					.setValorDebitoInicial(new BigDecimal(value));
		}

		if (form.getValorDebitoFinal() != null
				&& !form.getValorDebitoFinal().equals("")) {
			String value = form.getValorDebitoFinal().replace(".", "");
			value = value.replaceAll(",", ".");
			
			comandoNegativacaoTipoGuiaPagamentoHelper
					.setValorDebitoFinal(new BigDecimal(value));
		}

		if (form.getSituacaoComando() != null
				&& !form.getSituacaoComando().equals("")) {
			comandoNegativacaoTipoGuiaPagamentoHelper
					.setSituacaoComando(new Integer(form.getSituacaoComando()));
		}

		if (form.getIndicadorContaNomeCliente() != null) {
			comandoNegativacaoTipoGuiaPagamentoHelper
					.setIndicadorContaNomeCliente(new Short(form
							.getIndicadorContaNomeCliente()));
		}

		sessao.setAttribute("comandoNegativacaoTipoGuiaPagamentoHelper",
				comandoNegativacaoTipoGuiaPagamentoHelper);
		// sessao.setAttribute("comandoNegativacaoTipoCriterioHelper",
		// comandoNegativacaoTipoCriterioHelper);

		return retorno;

	}
}
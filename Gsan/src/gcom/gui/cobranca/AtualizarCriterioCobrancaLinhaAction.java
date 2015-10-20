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

import gcom.cobranca.CobrancaCriterioLinha;
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
 * Processamento para atualizar a linha do criterio da cobran�a
 * 
 * @author S�vio Luiz
 * @date 05/06/2006
 */
public class AtualizarCriterioCobrancaLinhaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarCriterioCobrancaLinha");

		CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		CobrancaCriterioLinha cobrancaCriterioLinha = (CobrancaCriterioLinha) sessao
				.getAttribute("cobrancaCriteriolinha");

		// atualiza cobranca criterio linha para ser exibido na tela de
		// inserir ou atualizar
		// verifica se o valor maximo � menor que o m�nimo
		BigDecimal valorDebitoMinimo = null;
		if (criterioCobrancaActionForm.getValorDebitoMinimo() != null
				&& !criterioCobrancaActionForm.getValorDebitoMinimo()
						.equals("")) {
			valorDebitoMinimo = Util
					.formatarMoedaRealparaBigDecimal(criterioCobrancaActionForm
							.getValorDebitoMinimo());
		}
		BigDecimal valorDebitoMaximo = null;
		if (criterioCobrancaActionForm.getValorDebitoMaximo() != null
				&& !criterioCobrancaActionForm.getValorDebitoMaximo()
						.equals("")) {
			valorDebitoMaximo = Util
					.formatarMoedaRealparaBigDecimal(criterioCobrancaActionForm
							.getValorDebitoMaximo());
		}
		if (valorDebitoMinimo != null && valorDebitoMaximo != null) {
			if (valorDebitoMinimo.compareTo(valorDebitoMaximo) == 1) {
				throw new ActionServletException(
						"atencao.valor.maximo.debito.menor.valor.minimo.debito");
			}
		}

		cobrancaCriterioLinha.setValorMinimoDebito(valorDebitoMinimo);
		cobrancaCriterioLinha.setValorMaximoDebito(valorDebitoMaximo);

		Short qtdContasMinima = null;
		if (criterioCobrancaActionForm.getQtdContasMinima() != null
				&& !criterioCobrancaActionForm.getQtdContasMinima().equals("")) {
			qtdContasMinima = new Short(criterioCobrancaActionForm
					.getQtdContasMinima());
		}
		Short qtdContasMaxima = null;
		if (criterioCobrancaActionForm.getQtdContasMaxima() != null
				&& !criterioCobrancaActionForm.getQtdContasMaxima().equals("")) {
			qtdContasMaxima = new Short(criterioCobrancaActionForm
					.getQtdContasMaxima());
		}
		if (qtdContasMinima != null && qtdContasMaxima != null) {
			if (qtdContasMinima > qtdContasMaxima) {
				throw new ActionServletException(
						"atencao.quantidade.maxima.contas.menor.quantidade.minima.contas");
			}
		}
		cobrancaCriterioLinha.setQuantidadeMinimaContas(qtdContasMinima);
		cobrancaCriterioLinha.setQuantidadeMaximaContas(qtdContasMaxima);

		if (criterioCobrancaActionForm.getVlMinimoDebitoCliente() != null
				&& !criterioCobrancaActionForm.getVlMinimoDebitoCliente()
						.equals("")) {
			cobrancaCriterioLinha.setValorMinimoDebitoDebitoAutomatico(Util
					.formatarMoedaRealparaBigDecimal(criterioCobrancaActionForm
							.getVlMinimoDebitoCliente()));
		}
		if (criterioCobrancaActionForm.getQtdMinContasCliente() != null
				&& !criterioCobrancaActionForm.getQtdMinContasCliente().equals(
						"")) {
			cobrancaCriterioLinha
					.setQuantidadeMinimaContasDebitoAutomatico(new Short(
							criterioCobrancaActionForm.getQtdMinContasCliente()));
		}
		if (criterioCobrancaActionForm.getVlMinimoContasMes() != null
				&& !criterioCobrancaActionForm.getVlMinimoContasMes()
						.equals("")) {
			cobrancaCriterioLinha.setValorMinimoContaMes(Util
					.formatarMoedaRealparaBigDecimal(criterioCobrancaActionForm
							.getVlMinimoContasMes()));
		}
		if (criterioCobrancaActionForm.getQuantidadeMinimaParcelasAtraso() != null
				&& !criterioCobrancaActionForm
						.getQuantidadeMinimaParcelasAtraso().equals("")) {
			cobrancaCriterioLinha
					.setQuantidadeMinimaContasParcelamento(new Short(
							criterioCobrancaActionForm
									.getQuantidadeMinimaParcelasAtraso()));

		} else {
			cobrancaCriterioLinha
					.setQuantidadeMinimaContasParcelamento(new Short("0"));
		}
		cobrancaCriterioLinha.setUltimaAlteracao(new Date());

		httpServletRequest.setAttribute("fechaPopup", "true");
		sessao.removeAttribute("cobrancaCriteriolinha");

		return retorno;
	}
}

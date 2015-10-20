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
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para adicionar a linha do criterio da cobran�a
 * 
 * @author S�vio Luiz
 * @date 02/06/2006
 */
public class ExibirAtualizarCriterioCobrancaLinhaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarCriterioCobrancaLinha");

		CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// par�metro respons�vel pelo redirecionamento do adicionar criterio
		// cobran�a linha
		// se retornarTela estiver o valor inserir retorna para o action de
		// inserir
		// se retornarTela estiver o valor atualizar retorna para o action de
		// atualizar
		String retornarTela = httpServletRequest.getParameter("retornarTela");
		sessao.setAttribute("retornarTela", retornarTela);

		String parmsImovelPerfil = httpServletRequest
				.getParameter("parmsImovelPerfilCobranca");
		String[] arrayImovelPerfilCategoria = parmsImovelPerfil.split(",");

		Integer idImovelPerfil = new Integer(arrayImovelPerfilCategoria[0]);
		Integer idCategoria = new Integer(arrayImovelPerfilCategoria[1]);

		if (sessao.getAttribute("colecaoCobrancaCriterioLinha") != null
				&& !sessao.getAttribute("colecaoCobrancaCriterioLinha").equals(
						"")) {
			Collection colecaoCobrancaCriterioLinha = (Collection) sessao
					.getAttribute("colecaoCobrancaCriterioLinha");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto
			// cobran�a crit�rio linha
			String vlMinDebito = "";
			String vlMaxDebito = "";
			String qtdMinContas = "";
			String qtdMaxContas = "";
			String vlMinDebCliente = "";
			String qtdMinConCliente = "";
			String vlMinConMes = "";
			String qdtParcelasMinimas = "";

			Iterator iteratorCobrancaCriterioLinha = colecaoCobrancaCriterioLinha
					.iterator();
			while (iteratorCobrancaCriterioLinha.hasNext()) {
				CobrancaCriterioLinha cobrancaCriterioLinha = (CobrancaCriterioLinha) iteratorCobrancaCriterioLinha
						.next();
				// se o id do imovel perfil que vem do request for igual ao id
				// imovel perfil a cole��o de criterio linha e o id da categoria
				// que vem do request for igual ao id categoria a cole��o de
				// criterio linha
				if (idImovelPerfil != null
						&& idImovelPerfil.equals(cobrancaCriterioLinha
								.getImovelPerfil().getId())) {
					if (idCategoria != null
							&& idCategoria.equals(cobrancaCriterioLinha
									.getCategoria().getId())) {
						// formata os valores para jogar no form
						if (cobrancaCriterioLinha.getValorMinimoDebito() != null
								&& !cobrancaCriterioLinha
										.getValorMinimoDebito().equals("")) {
							vlMinDebito = Util
									.formatarMoedaReal(cobrancaCriterioLinha
											.getValorMinimoDebito());
						}
						if (cobrancaCriterioLinha.getValorMaximoDebito() != null
								&& !cobrancaCriterioLinha
										.getValorMaximoDebito().equals("")) {
							vlMaxDebito = Util
									.formatarMoedaReal(cobrancaCriterioLinha
											.getValorMaximoDebito());
						}
						if (cobrancaCriterioLinha.getQuantidadeMinimaContas() != null
								&& !cobrancaCriterioLinha
										.getQuantidadeMinimaContas().equals("")) {
							qtdMinContas = ""
									+ cobrancaCriterioLinha
											.getQuantidadeMinimaContas();
						}
						if (cobrancaCriterioLinha.getQuantidadeMaximaContas() != null
								&& !cobrancaCriterioLinha
										.getQuantidadeMaximaContas().equals("")) {
							qtdMaxContas = ""
									+ cobrancaCriterioLinha
											.getQuantidadeMaximaContas();
						}
						if (cobrancaCriterioLinha
								.getValorMinimoDebitoDebitoAutomatico() != null
								&& !cobrancaCriterioLinha
										.getValorMinimoDebitoDebitoAutomatico()
										.equals("")) {
							vlMinDebCliente = Util
									.formatarMoedaReal(cobrancaCriterioLinha
											.getValorMinimoDebitoDebitoAutomatico());
						}
						if (cobrancaCriterioLinha
								.getQuantidadeMinimaContasDebitoAutomatico() != null
								&& !cobrancaCriterioLinha
										.getQuantidadeMinimaContasDebitoAutomatico()
										.equals("")) {
							qtdMinConCliente = ""
									+ cobrancaCriterioLinha
											.getQuantidadeMinimaContasDebitoAutomatico();
						}
						if (cobrancaCriterioLinha.getValorMinimoContaMes() != null
								&& !cobrancaCriterioLinha
										.getValorMinimoContaMes().equals("")) {
							vlMinConMes = Util
									.formatarMoedaReal(cobrancaCriterioLinha
											.getValorMinimoContaMes());
						}
						if (cobrancaCriterioLinha
								.getQuantidadeMinimaContasParcelamento() != null
								&& !cobrancaCriterioLinha
										.getQuantidadeMinimaContasParcelamento()
										.equals("")) {
							qdtParcelasMinimas = cobrancaCriterioLinha
									.getQuantidadeMinimaContasParcelamento()
									.toString();

						} else {
							qdtParcelasMinimas = "0";
						}
						// seta os valores da ultima linha da cobran�a criterio
						criterioCobrancaActionForm
								.setValorDebitoMinimo(vlMinDebito);
						criterioCobrancaActionForm
								.setValorDebitoMaximo(vlMaxDebito);
						criterioCobrancaActionForm
								.setQtdContasMinima(qtdMinContas);
						criterioCobrancaActionForm
								.setQtdContasMaxima(qtdMaxContas);
						criterioCobrancaActionForm
								.setVlMinimoDebitoCliente(vlMinDebCliente);
						criterioCobrancaActionForm
								.setQtdMinContasCliente(qtdMinConCliente);
						criterioCobrancaActionForm
								.setVlMinimoContasMes(vlMinConMes);
						criterioCobrancaActionForm
								.setDescricaoImovelPerfil(cobrancaCriterioLinha
										.getImovelPerfil().getDescricao());
						criterioCobrancaActionForm
								.setDescricaoCategoria(cobrancaCriterioLinha
										.getCategoria().getDescricao());
						criterioCobrancaActionForm
								.setQuantidadeMinimaParcelasAtraso(qdtParcelasMinimas);

						sessao.setAttribute("cobrancaCriteriolinha",
								cobrancaCriterioLinha);
					}

				}

			}

			httpServletRequest.setAttribute("fechaPopup", "false");
		}

		return retorno;
	}
}

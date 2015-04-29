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
package gsan.gui.cobranca;

import gsan.cobranca.FiltroCobrancaCriterio;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ComparacaoTexto;
import gsan.util.filtro.ParametroSimples;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * processamento para filtrar o criterio da cobran�a
 * 
 * @author S�vio Luiz
 * @date 05/05/2006
 */
public class FiltrarCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterCriterioCobranca");

		HttpSession sessao = httpServletRequest.getSession(false);

		CriterioCobrancaFiltrarActionForm criterioCobrancaFiltrarActionForm = (CriterioCobrancaFiltrarActionForm) actionForm;

		// Recupera os par�metros do form
		String descricaoCriterioCobranca = criterioCobrancaFiltrarActionForm
				.getDescricaoCriterio();
		String dataInicioVigencia = criterioCobrancaFiltrarActionForm
				.getDataInicioVigencia();
		String numeroAnosContaAntiga = criterioCobrancaFiltrarActionForm
				.getNumeroAnoContaAntiga();
		String opcaoAcaoImovelSitEspecial = criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoImovelSitEspecial();
		String opcaoAcaoImovelSit = criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoImovelSit();
		String opcaoContasRevisao = criterioCobrancaFiltrarActionForm
				.getOpcaoContasRevisao();
		String opcaoAcaoImovelDebitoMesConta = criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoImovelDebitoMesConta();
		String opcaoAcaoInquilinoDebitoMesConta = criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoInquilinoDebitoMesConta();
		String opcaoAcaoImovelDebitoContasAntigas = criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoImovelDebitoContasAntigas();
		String indicadorUso = criterioCobrancaFiltrarActionForm
				.getIndicadorUso();
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");

		if (indicadorAtualizar == null) {
			criterioCobrancaFiltrarActionForm.setIndicadorAtualizar("2");
		} else {
			criterioCobrancaFiltrarActionForm
					.setIndicadorAtualizar(indicadorAtualizar);
		}
		
		
		FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio(
				FiltroCobrancaCriterio.ID);

		boolean peloMenosUmParametroInformado = false;

		// Insere os par�metros informados no filtro

		if (descricaoCriterioCobranca != null
				&& !descricaoCriterioCobranca.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaCriterio.adicionarParametro(new ComparacaoTexto(
					FiltroCobrancaCriterio.DESCRICAO_COBRANCA_CRITERIO,
					descricaoCriterioCobranca));
		}
		if (dataInicioVigencia != null
				&& !dataInicioVigencia.trim().equalsIgnoreCase("")) {
			Date dataVigencia = Util.converteStringParaDate(dataInicioVigencia);
			peloMenosUmParametroInformado = true;
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.DATA_INICIO_VIGENCIA, dataVigencia));
		}
		if (numeroAnosContaAntiga != null
				&& !numeroAnosContaAntiga.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.NUMERO_ANOS_CONTA_ANTIGA,
					numeroAnosContaAntiga));
		}

		if (opcaoAcaoImovelSitEspecial != null
				&& !opcaoAcaoImovelSitEspecial.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoAcaoImovelSitEspecial.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.INDICADOR_IMOVEL_PARALISACAO,
						opcaoAcaoImovelSitEspecial));
			}
		}
		if (opcaoAcaoImovelSit != null
				&& !opcaoAcaoImovelSit.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoAcaoImovelSit.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio
						.adicionarParametro(new ParametroSimples(
								FiltroCobrancaCriterio.INDICADOR_IMOVEL_SITUACAO_COBRANCA,
								opcaoAcaoImovelSit));
			}
		}
		if (opcaoContasRevisao != null
				&& !opcaoContasRevisao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoContasRevisao.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.INDICADOR_CONTA_REVISAO,
						opcaoContasRevisao));
			}
		}
		if (opcaoAcaoImovelDebitoMesConta != null
				&& !opcaoAcaoImovelDebitoMesConta.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoAcaoImovelDebitoMesConta.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.INDICADOR_DEBITO_CONTA_MES,
						opcaoAcaoImovelDebitoMesConta));
			}
		}
		if (opcaoAcaoInquilinoDebitoMesConta != null
				&& !opcaoAcaoInquilinoDebitoMesConta.trim()
						.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoAcaoInquilinoDebitoMesConta.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio
						.adicionarParametro(new ParametroSimples(
								FiltroCobrancaCriterio.INDICADOR_INQUILINO_DEBITO_CONTA_MES,
								opcaoAcaoInquilinoDebitoMesConta));
			}
		}
		if (opcaoAcaoImovelDebitoContasAntigas != null
				&& !opcaoAcaoImovelDebitoContasAntigas.trim().equalsIgnoreCase(
						"")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoAcaoImovelDebitoContasAntigas.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.INDICADOR_DEBITO_CONTA_ANTIGA,
						opcaoAcaoImovelDebitoContasAntigas));
			}
		}

		if ((indicadorUso != null && !indicadorUso.equals(""
				+ ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (!indicadorUso.equals("3"))) {

			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.INDICADOR_USO, indicadorUso));

			peloMenosUmParametroInformado = true;
		}

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Manda o filtro pelo request para o ExibirManterClienteAction
		sessao.setAttribute("filtroCobrancaCriterio", filtroCobrancaCriterio);
		sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		return retorno;

	}
}

/*
 * Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
 * Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioPagamentoEntidadesBeneficentesAnalitico;
import gcom.relatorio.arrecadacao.RelatorioPagamentoEntidadesBeneficentesSintetico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Gera��o do relat�rio [UC0978] Gerar Relat�rio de Pagamento para Entidades
 * Beneficentes
 * 
 * @author Daniel Alves
 */

public class GerarRelatorioPagamentoEntidadesBeneficentesAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);

		GerarRelatorioPagamentoEntidadesBeneficentesActionForm form = (GerarRelatorioPagamentoEntidadesBeneficentesActionForm) actionForm;

		String idEntidadeBeneficente = form.getIdEntidadeBeneficente();

		String mesAnoInicial = form.getMesAnoInicial();

		String mesAnoFinal = form.getMesAnoFinal();

		String tipo = form.getTipo();

		String idGerenciaRegional = form.getIdGerenciaRegional();

		String idUnidadeNegocio = form.getIdUnidadeNegocio();

		String idLocalidade = form.getIdLocalidade();

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		

		int opcaoTotalizacao = form.getOpcaoTotalizacao();

		if (tipoRelatorio == null || tipoRelatorio.equalsIgnoreCase("")) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			throw new ActionServletException("atencao.required", null,
					"Op��o de Totaliza��o ");
		}
		

		// verifica a data de consulta de pagamento
		if ((mesAnoInicial == null || mesAnoInicial.equals(""))
				&& (mesAnoFinal == null || mesAnoFinal.equals(""))) {
			throw new ActionServletException("atencao.required", null,
					"M�s/Ano de Pagamento");
		}

		// Verifica se a Data Final � maior que a Inicial
		if (mesAnoFinal != null && !mesAnoFinal.equals("")
				&& mesAnoInicial != null && !mesAnoInicial.equals("")) {

			Integer dtInicial = new Integer(Util
					.formatarMesAnoParaAnoMesSemBarra(mesAnoInicial));
			Integer dtFinal = new Integer(Util
					.formatarMesAnoParaAnoMesSemBarra(mesAnoFinal));

			if (dtFinal.compareTo(dtInicial) < 0) {

				throw new ActionServletException(
						"atencao.data.intervalo.invalido", null,
						"Data Invalida");

			}

		}

		// Parte que vai mandar o relat�rio para a tela

		if (tipo.equals("analitico")) {
			// cria uma inst�ncia da classe do relat�rio
			RelatorioPagamentoEntidadesBeneficentesAnalitico relatorioPagamentoEntidadesBeneficentes = new RelatorioPagamentoEntidadesBeneficentesAnalitico(
					(Usuario) (httpServletRequest.getSession(false))
							.getAttribute("usuarioLogado"));

			relatorioPagamentoEntidadesBeneficentes.addParametro("mesAnoInicial", mesAnoInicial);
			relatorioPagamentoEntidadesBeneficentes.addParametro("mesAnoFinal",
					mesAnoFinal);
			relatorioPagamentoEntidadesBeneficentes.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));

			relatorioPagamentoEntidadesBeneficentes.addParametro("opcaoTotalizacao", opcaoTotalizacao);

			relatorioPagamentoEntidadesBeneficentes.addParametro("idEntidadeBeneficente", idEntidadeBeneficente);
			relatorioPagamentoEntidadesBeneficentes.addParametro("idGerenciaRegional", idGerenciaRegional);
			relatorioPagamentoEntidadesBeneficentes.addParametro("idUnidadeNegocio", idUnidadeNegocio);
			relatorioPagamentoEntidadesBeneficentes.addParametro("idLocalidade", idLocalidade);

			try {
				retorno = processarExibicaoRelatorio(
						relatorioPagamentoEntidadesBeneficentes, tipoRelatorio,
						httpServletRequest, httpServletResponse, actionMapping);
			} catch (RelatorioVazioException ex) {
				// manda o erro para a p�gina no request atual
				reportarErros(httpServletRequest, "atencao.relatorio.vazio");

				// seta o mapeamento de retorno para a tela de aten��o de popup
				retorno = actionMapping.findForward("telaAtencaoPopup");
			}

		}else if (tipo.equals("sintetico")) {
			// cria uma inst�ncia da classe do relat�rio
			RelatorioPagamentoEntidadesBeneficentesSintetico relatorioPagamentoEntidadesBeneficentes = new RelatorioPagamentoEntidadesBeneficentesSintetico(
					(Usuario) (httpServletRequest.getSession(false))
							.getAttribute("usuarioLogado"));

			relatorioPagamentoEntidadesBeneficentes.addParametro("mesAnoInicial", mesAnoInicial);
			relatorioPagamentoEntidadesBeneficentes.addParametro("mesAnoFinal",mesAnoFinal);
			relatorioPagamentoEntidadesBeneficentes.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));

			relatorioPagamentoEntidadesBeneficentes.addParametro("opcaoTotalizacao", opcaoTotalizacao);

			relatorioPagamentoEntidadesBeneficentes.addParametro("idEntidadeBeneficente", idEntidadeBeneficente);
			relatorioPagamentoEntidadesBeneficentes.addParametro("idGerenciaRegional", idGerenciaRegional);
			relatorioPagamentoEntidadesBeneficentes.addParametro("idUnidadeNegocio", idUnidadeNegocio);
			relatorioPagamentoEntidadesBeneficentes.addParametro("idLocalidade", idLocalidade);

			try {
				retorno = processarExibicaoRelatorio(
						relatorioPagamentoEntidadesBeneficentes, tipoRelatorio,
						httpServletRequest, httpServletResponse, actionMapping);
			} catch (RelatorioVazioException ex) {
				// manda o erro para a p�gina no request atual
				reportarErros(httpServletRequest, "atencao.relatorio.vazio");

				// seta o mapeamento de retorno para a tela de aten��o de popup
				retorno = actionMapping.findForward("telaAtencaoPopup");
			}

		}
		
		
		return retorno;
	}

}
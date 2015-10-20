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
package gcom.gui.relatorio.faturamento;

import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioFaturamentoLigacoesMedicaoIndividualizada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0532] Gerar Relat�rio de Faturamento das Liga��es com Medi��o Individualizada
 * 
 * @author Vivianne Sousa
 * @date 09/01/2007
 */

public class GerarRelatorioFaturamentoLigacoesMedicaoIndividualizadaAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql = new FiltroMedicaoHistoricoSql();
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo)sessao.getAttribute("faturamentoGrupo");
		String mesAnoPesquisa = (String) sessao.getAttribute("mesAnoPesquisa");
		
		if (sessao.getAttribute("filtroMedicaoHistoricoSql") != null) {
			filtroMedicaoHistoricoSql = (FiltroMedicaoHistoricoSql) sessao.getAttribute("filtroMedicaoHistoricoSql");
		}
		
		
//		Collection colecaoFaturamentoLigacoesMedicaoIndividualizadaHelper = null; 
//			
//		if (colecaoImoveisGerarRelatorio != null
//				&& !colecaoImoveisGerarRelatorio.isEmpty()) {
//			fachada
//					.pesquisarFaturamentoLigacoesMedicaoIndividualizadaRelatorio(
//							colecaoImoveisGerarRelatorio, ""
//									+ faturamentoGrupo.getAnoMesReferencia());
//		} else {
//			fachada
//					.pesquisarFaturamentoLigacoesMedicaoIndividualizadaRelatorio(
//							filtroMedicaoHistoricoSql, ""
//									+ faturamentoGrupo.getAnoMesReferencia());
//		}
		
		// Parte que vai mandar o relat�rio para a tela
		// cria uma inst�ncia da classe do relat�rio
		RelatorioFaturamentoLigacoesMedicaoIndividualizada relatorioFaturamentoLigacoesMedicaoIndividualizada = new RelatorioFaturamentoLigacoesMedicaoIndividualizada((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
//		relatorioFaturamentoLigacoesMedicaoIndividualizada.addParametro("colecaoFaturamentoLigacoesMedicaoIndividualizadaHelper",colecaoFaturamentoLigacoesMedicaoIndividualizadaHelper);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioFaturamentoLigacoesMedicaoIndividualizada.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorioFaturamentoLigacoesMedicaoIndividualizada.addParametro("anoMesFaturamentoGrupo" ,Util.formatarAnoMesParaMesAno(faturamentoGrupo.getAnoMesReferencia()));
		relatorioFaturamentoLigacoesMedicaoIndividualizada.addParametro("idGrupoFaturamento","" + faturamentoGrupo.getId());
		
		relatorioFaturamentoLigacoesMedicaoIndividualizada.addParametro("faturamentoGrupo",faturamentoGrupo);
		relatorioFaturamentoLigacoesMedicaoIndividualizada.addParametro("filtroMedicaoHistoricoSql",filtroMedicaoHistoricoSql);
		relatorioFaturamentoLigacoesMedicaoIndividualizada.addParametro("mesAnoPesquisa",mesAnoPesquisa);
		try {
			retorno = processarExibicaoRelatorio(relatorioFaturamentoLigacoesMedicaoIndividualizada,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
		} catch (RelatorioVazioException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}

}

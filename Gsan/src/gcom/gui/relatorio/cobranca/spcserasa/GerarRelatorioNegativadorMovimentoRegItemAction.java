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
package gcom.gui.relatorio.cobranca.spcserasa;

import gcom.gui.cobranca.spcserasa.ConsultarNegativadorMovimentoRegItemPopupActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.spcserasa.RelatorioNegativadorMovimentoRegItem;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0684]
 * 
 * @author Rodrigo Cabral
 * @created 18/09/2014
 */
public class GerarRelatorioNegativadorMovimentoRegItemAction extends
		ExibidorProcessamentoTarefaRelatorio {

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarNegativadorMovimentoRegItemPopupActionForm form = (ConsultarNegativadorMovimentoRegItemPopupActionForm) actionForm;

		RelatorioNegativadorMovimentoRegItem relatorioImoveisRelacionadosCliente = criarRelatorioComParametros(
				form, sessao, tipoRelatorio);

		ActionForward retorno = null;

		try {
			retorno = processarExibicaoRelatorio(relatorioImoveisRelacionadosCliente,
					tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

		} catch (SistemaException ex) {
			reportarErros(httpServletRequest, "erro.sistema");
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			reportarErros(httpServletRequest, "erro.relatorio.vazio");
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}
	
	/**
	 *Esse m�todo cria o objeto RelatorioNegativadorMovimentoRegItem,
	 *adciona os parametros necess�rios ao seu funcionamento e o retorna.
	 *
	 *@since 19/09/2014
	 *@author Rodrigo Cabral
	 */
	private RelatorioNegativadorMovimentoRegItem criarRelatorioComParametros(ConsultarNegativadorMovimentoRegItemPopupActionForm form,
			HttpSession sessao, String tipoRelatorio) {
		
		RelatorioNegativadorMovimentoRegItem relatorio = new RelatorioNegativadorMovimentoRegItem((Usuario)sessao.getAttribute("usuarioLogado"));
		
		relatorio.addParametro("totalQtdContas",sessao.getAttribute("totalQtdContas"));
		relatorio.addParametro("totalValorNegativadoConta",sessao.getAttribute("totalValorNegativadoConta"));
		relatorio.addParametro("totalValorConta",sessao.getAttribute("totalValorConta"));
		relatorio.addParametro("totalValorNegativadoGuia",sessao.getAttribute("totalValorNegativadoGuia"));
		relatorio.addParametro("totalValorGuia",sessao.getAttribute("totalValorGuia"));
		relatorio.addParametro("totalQtdGuias",sessao.getAttribute("totalQtdGuias"));
		relatorio.addParametro("collNegativadorMovimentoRegItemContas",sessao.getAttribute("collNegativadorMovimentoRegItem"));
		relatorio.addParametro("collNegativadorMovimentoRegItemGuias",sessao.getAttribute("collNegativadorMovimentoRegItem2"));
		relatorio.addParametro("consultarNegativadorMovimentoRegItemPopupActionForm",form);
		relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("dataSituacaoDebito", sessao.getAttribute("dataSituacaoDebito"));
		relatorio.addParametro("totalValorNegativadoConta", sessao.getAttribute("totalValorNegativadoConta"));
		relatorio.addParametro("totalValorNegativadoGuia", sessao.getAttribute("totalValorNegativadoGuia"));
		
		return relatorio;
	}

}

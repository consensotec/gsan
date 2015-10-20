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
package gcom.gui.relatorio.cadastro.micromedicao;


import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.micromedicao.RelatorioResumoLigacoesCapacidadeHidrometro;
import gcom.relatorio.cadastro.micromedicao.RelatorioResumoLigacoesCapacidadeHidrometroHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0997] Gerar Resumo de Liga��es por Capacidade de Hidr�metro
 * 
 * @author Hugo Leonardo
 *
 * @date 29/03/2010
 */

public class GerarRelatorioResumoLigacoesCapacidadeHidrometroAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		   
		// Form
		GerarRelatorioResumoLigacoesCapacidadeHidrometroActionForm form = 
			(GerarRelatorioResumoLigacoesCapacidadeHidrometroActionForm) actionForm;
		
		RelatorioResumoLigacoesCapacidadeHidrometroHelper helper = 
			new RelatorioResumoLigacoesCapacidadeHidrometroHelper();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		// mesAno Faturamento 
		SistemaParametro sistemaParametro = this.getSistemaParametro();
		
		Integer anoMesFaturamento =  sistemaParametro.getAnoMesFaturamento();
		
		// Subtrai 1 m�s do AnoMesFaturamentoReferencia
		Integer anoMesFaturamentoAnterior = Util.subtrairMesDoAnoMes(anoMesFaturamento, 1);
		String mesAno = Util.formatarAnoMesParaMesAno(anoMesFaturamentoAnterior.toString());
		
		int mes = Util.obterMes(anoMesFaturamentoAnterior);
		int ano = Util.obterAno(anoMesFaturamentoAnterior);
		
		// obt�m a Data do �ltimo dia do anoMes Faturamento Anterior
		Integer dia = new Integer(Util.obterUltimoDiaMes(mes, ano));
		
		helper.setMesAnoReferencia(Util.criarData(dia, mes, ano));
		
		helper.setAnoMesReferenciaAnterior(anoMesFaturamentoAnterior);
		
		// opcao Totalizacao
		if(form.getOpcaoTotalizacao() != null && !form.getOpcaoTotalizacao().equals("-1")){
			helper.setOpcaoTotalizacao(form.getOpcaoTotalizacao());
		}
		
		// Gerencia Regional
		if(form.getRegional() != null && !form.getRegional().equals("-1")){
			helper.setIdGerenciaRegional( new Integer(form.getRegional()));
		}
		
		// Unidade de Neg�cio
		if(form.getUnidadeNegocio() != null && !form.getUnidadeNegocio().equals("-1")){
			helper.setIdUnidadeNegocio( new Integer(form.getUnidadeNegocio()));
		}
		
		//Localidade
		if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
			helper.setIdLocalidade(new Integer(form.getIdLocalidade()));
		}
		
		TarefaRelatorio relatorio = null;
	
		relatorio = new RelatorioResumoLigacoesCapacidadeHidrometro((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			 
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		relatorio.addParametro("relatorioResumoLigacoesCapacidadeHidrometroHelper", helper);
		
		relatorio.addParametro("mesAno", mesAno);
		
		try {	
			
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
	
		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
			
			return retorno;
		}
	
}
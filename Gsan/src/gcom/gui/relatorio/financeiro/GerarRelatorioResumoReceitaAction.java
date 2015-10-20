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
package gcom.gui.relatorio.financeiro;

import gcom.gui.financeiro.GerarRelatorioResumoReceitaActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.financeiro.RelatorioResumoReceita;
import gcom.relatorio.financeiro.RelatorioResumoReceitaAnalitico;
import gcom.relatorio.financeiro.ResumoReceitaHelper;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Flavio Leonardo
 *
 * @date 16/04/2010
 */

public class GerarRelatorioResumoReceitaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		
		// Form
		GerarRelatorioResumoReceitaActionForm form = 
			(GerarRelatorioResumoReceitaActionForm) actionForm;
		 
//		FiltroResumoReceita filtro = 
//			new FiltroResumoReceita();
		
		ResumoReceitaHelper resumoReceitaHelper = new ResumoReceitaHelper();
		
		//Ger�ncia Regional
		if (form.getMesAnoReferencial() != null && 
			!form.getMesAnoReferencial().equals("") ) {
			
//			filtro.adicionarParametro(new ParametroSimples(FiltroResumoReceita.ANO_MES_REFERENCIA,
//					Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencial())));
			
			resumoReceitaHelper.setAnoMes(Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencial())+"");
			
			
		}
		
		// Ger�ncia Regional
		if (form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			resumoReceitaHelper.setGerenciaRegionalId(new Integer(form.getGerenciaRegional()));
//			filtro.adicionarParametro(new ParametroSimples(FiltroResumoReceita.GERENCIA_REGIONAL,form.getGerenciaRegional()));
		}
			
		// Localidade Inicial
		if (form.getLocalidadeInicial() != null && 
			!form.getLocalidadeInicial().equals("") ) {
			if(form.getLocalidadeFinal() != null && !form.getLocalidadeFinal().equals("")){
				resumoReceitaHelper.setLocalidadeInicial(form.getLocalidadeInicial());
				resumoReceitaHelper.setLocalidadeFinal(form.getLocalidadeFinal());
//				filtro.adicionarParametro(new MaiorQue(FiltroResumoReceita.LOCALIDADE, form.getLocalidadeInicial()));
//				filtro.adicionarParametro(new MenorQue(FiltroResumoReceita.LOCALIDADE, form.getLocalidadeFinal()));
			}else{
//				filtro.adicionarParametro(new ParametroSimples(FiltroResumoReceita.LOCALIDADE, form.getLocalidadeInicial()));
				resumoReceitaHelper.setLocalidadeInicial(form.getLocalidadeInicial());
			}
		}
		
		//Ger�ncia Regional
		if (form.getCategoria() != null && 
			!form.getCategoria().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			resumoReceitaHelper.setCategoriaId(new Integer(form.getCategoria()));
			
//			filtro.adicionarParametro(new ParametroSimples(FiltroResumoReceita.CATEGORIA_ID,form.getCategoria()));
		}

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if(form.getTipoRelatorio() != null && form.getTipoRelatorio().equals("1")){
			RelatorioResumoReceitaAnalitico relatorio = 
				new RelatorioResumoReceitaAnalitico(this.getUsuarioLogado(httpServletRequest));
			
			relatorio.addParametro("resumoReceitaHelper", resumoReceitaHelper);
			relatorio.addParametro("mesAno", form.getMesAnoReferencial());
			relatorio.addParametro("gerenciaRegional", form.getGerenciaRegional());
			relatorio.addParametro("localidadeInicial", form.getLocalidadeInicial());
			relatorio.addParametro("localidadeFinal", form.getLocalidadeFinal());
			relatorio.addParametro("categoria", form.getCategoria());
			
			if (tipoRelatorio  == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
	
			relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
			
			retorno = 
				processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
					httpServletResponse, actionMapping);
		}else{
			RelatorioResumoReceita relatorio = 
				new RelatorioResumoReceita(this.getUsuarioLogado(httpServletRequest));
			
			relatorio.addParametro("resumoReceitaHelper", resumoReceitaHelper);
			relatorio.addParametro("mesAno", form.getMesAnoReferencial());
			relatorio.addParametro("gerenciaRegional", form.getGerenciaRegional());
			relatorio.addParametro("localidadeInicial", form.getLocalidadeInicial());
			relatorio.addParametro("localidadeFinal", form.getLocalidadeFinal());
			relatorio.addParametro("categoria", form.getCategoria());
			
			if (tipoRelatorio  == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
	
			relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
			
			retorno = 
				processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
					httpServletResponse, actionMapping);
		}
		
		

		return retorno;
	}
	
}
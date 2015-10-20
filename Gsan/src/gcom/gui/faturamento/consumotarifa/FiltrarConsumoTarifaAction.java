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
package gcom.gui.faturamento.consumotarifa;

import gcom.faturamento.consumotarifa.FiltroConsumoTarifaVigencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Filtra as tarifas de consumo
 * 
 * @author Tiago Moreno,Rafael Santos
 */
public class FiltrarConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterConsumoTarifa");

		FiltrarConsumoTarifaActionForm filtrarConsumoTarifaActionForm = (FiltrarConsumoTarifaActionForm) actionForm;

		//Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio

		String descTarifa = filtrarConsumoTarifaActionForm.getDescTarifa();
		String dataVigenciaInicial = filtrarConsumoTarifaActionForm.getDataVigencia();
		String dataVigenciaFinal = filtrarConsumoTarifaActionForm
				.getDataVigenciaFim();
		String atualizar = (String)httpServletRequest.getParameter("atualizarFiltro");
		
		//valida o intervalo de datas
		if ((dataVigenciaInicial.trim().length() == 10)
				&& (dataVigenciaFinal.trim().length() == 10)) {

			Calendar calendarInicio = new GregorianCalendar();
			Calendar calendarFim = new GregorianCalendar();

			calendarInicio.set(Calendar.DAY_OF_MONTH, new Integer(dataVigenciaInicial
					.substring(0, 2)).intValue());
			calendarInicio.set(Calendar.MONTH, new Integer(dataVigenciaInicial
					.substring(3, 5)).intValue());
			calendarInicio.set(Calendar.YEAR, new Integer(dataVigenciaInicial
					.substring(6, 10)).intValue());

			calendarFim.set(Calendar.DAY_OF_MONTH, new Integer(dataVigenciaFinal
					.substring(0, 2)).intValue());
			calendarFim.set(Calendar.MONTH, new Integer(dataVigenciaFinal
					.substring(3, 5)).intValue());
			calendarFim.set(Calendar.YEAR, new Integer(dataVigenciaFinal
					.substring(6, 10)).intValue());
			// joga exess�o
			if (calendarFim.compareTo(calendarInicio) < 0) {
				throw new ActionServletException(
						"atencao.data_vigencia_final.menor.data_vigencia_inicial");
			}
		}

		FiltroConsumoTarifaVigencia filtroConsumoTarifaVigencia = new FiltroConsumoTarifaVigencia();
		/*filtroConsumoTarifaVigencia
				.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoTarifaVigencia.CONSUMO_TARIFA_DESCRICAO);*/
        filtroConsumoTarifaVigencia.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoTarifaVigencia.CONSUMO_TARIFA);
        
		boolean parametroPesquisaInformando = false;
		
		//Descricao da Tarifa 
		if (descTarifa != null
			&& !descTarifa.trim().equalsIgnoreCase("")) {
				
				filtroConsumoTarifaVigencia.adicionarParametro(new ComparacaoTexto(
				FiltroConsumoTarifaVigencia.CONSUMO_TARIFA_DESCRICAO,
				descTarifa));
				parametroPesquisaInformando = true;
		}
		
		//data de Vigencia Inicial
		if (dataVigenciaInicial != null
			&& !dataVigenciaInicial.trim().equalsIgnoreCase("")) {
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			Date dataInicial = null;
		
			try {
				dataInicial = formatoData.parse(dataVigenciaInicial);
			} catch (ParseException e) {
				dataInicial = null;
			}
		
			filtroConsumoTarifaVigencia.adicionarParametro(new MaiorQue(
				FiltroConsumoTarifaVigencia.DATA_VIGENCIA, dataInicial));
			parametroPesquisaInformando = true;
		}
		
		//data de Vigencia final
		if (dataVigenciaFinal != null
			&& !dataVigenciaFinal.trim().equalsIgnoreCase("")) {
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			Date dataFinal = null;
		
			try {
				dataFinal = formatoData.parse(dataVigenciaFinal);
			} catch (ParseException e) {
				dataFinal = null;
			}
		
			filtroConsumoTarifaVigencia.adicionarParametro(new MenorQue(
				FiltroConsumoTarifaVigencia.DATA_VIGENCIA, dataFinal));
			parametroPesquisaInformando = true;
		}
		
		if(!parametroPesquisaInformando){
			// Exception que nao foi digitado nada!!!
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}


		filtroConsumoTarifaVigencia.setCampoOrderBy(
				FiltroConsumoTarifaVigencia.CONSUMO_TARIFA_DESCRICAO,
				FiltroConsumoTarifaVigencia.DATA_VIGENCIA);
		
		sessao.setAttribute("filtroConsumoTarifaVigencia", filtroConsumoTarifaVigencia);
		sessao.setAttribute("indicadorAtualizar", atualizar);	
		
		return retorno;
	}
}
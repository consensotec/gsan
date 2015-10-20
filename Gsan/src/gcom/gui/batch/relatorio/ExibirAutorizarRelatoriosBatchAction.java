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
* Genival Soaers Barbosa Filho
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
package gcom.gui.batch.relatorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import gcom.batch.FiltroProcessoIniciado;
import gcom.batch.ProcessoIniciado;
import gcom.batch.ProcessoSituacao;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import gcom.util.filtro.ConectorAnd;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Classe resps�vel por montar a apresenta��o dos relat�rios armazenados em
 * batch
 * 
 * 
 * @author Genival Barbosa
 * @date 03/07/2009
 */
public class ExibirAutorizarRelatoriosBatchAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("autorizarRelatorio");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		
		/*
		 * -------Iniciar datas (mes atual, mes anterior)--------
		 */
		if (httpServletRequest.getParameter("dataInicialSelecionado")== null || httpServletRequest.getParameter("dataFinalSelecionado") == null 
				||httpServletRequest.getParameter("dataInicialSelecionado").equals("") || httpServletRequest.getParameter("dataFinalSelecionado").equals("")) {
			
			Date dataInicial = new Date(System.currentTimeMillis()); 
			
			Calendar calendarData = Calendar.getInstance();  
			calendarData.setTime(dataInicial);  
			calendarData.add(Calendar.MONTH,-1);  
			dataInicial = calendarData.getTime(); 
			   
			SimpleDateFormat formatarDateInicial = new SimpleDateFormat("dd/MM/yyyy");    
			httpServletRequest.setAttribute("dataInicial", formatarDateInicial.format(dataInicial));
			
			
			Date dataFinal = new Date(System.currentTimeMillis());    
			SimpleDateFormat formatarDateFinal = new SimpleDateFormat("dd/MM/yyyy");    
			httpServletRequest.setAttribute("dataFinal", formatarDateFinal.format(dataFinal));
		} else {
			httpServletRequest.setAttribute("dataInicial",httpServletRequest.getParameter("dataInicialSelecionado"));
			httpServletRequest.setAttribute("dataFinal",httpServletRequest.getParameter("dataFinalSelecionado"));
		}
		
		/*
		 * ------------------------------------------------------
		 */
		
		//limitar pesquisa por no maximo 30 dias
		Date dataInicialDate = converterDataHora(httpServletRequest.getAttribute("dataInicial").toString(),"00:00:00");
		Date dataFinalDate = converterDataHora(httpServletRequest.getAttribute("dataFinal").toString(),"23:59:59");
		
		Calendar calendDataInic = Calendar.getInstance();
		calendDataInic.setTime(dataInicialDate);
		
		Calendar calendDataFinal = Calendar.getInstance();
		calendDataFinal.setTime(dataFinalDate);
		
		long diferenca = calendDataFinal.getTimeInMillis() - calendDataInic.getTimeInMillis();
		
		int tempoDia = 1000 * 60 * 60 * 24;
		
		long diasDiferenca = diferenca/tempoDia;
		
		if(diasDiferenca<=31){
			if (diasDiferenca>=0){
		
				FiltroProcessoIniciado filtroProcessoIniciado = new FiltroProcessoIniciado();
				
				filtroProcessoIniciado.adicionarCaminhoParaCarregamentoEntidade(FiltroProcessoIniciado.PROCESSO);
		        filtroProcessoIniciado.adicionarCaminhoParaCarregamentoEntidade(FiltroProcessoIniciado.USUARIO);
		        filtroProcessoIniciado.adicionarCaminhoParaCarregamentoEntidade(FiltroProcessoIniciado.PROCESSO_SITUACAO);
		  
		        
				filtroProcessoIniciado.adicionarParametro(new ParametroSimples(FiltroProcessoIniciado.PROCESSO_SITUACAO_ID,ProcessoSituacao.AGUARDANDO_AUTORIZACAO,ConectorAnd.CONECTOR_AND));
				filtroProcessoIniciado.adicionarParametro(new Intervalo(FiltroProcessoIniciado.DATA_HORA_AGENDAMENTO,
						dataInicialDate,dataFinalDate ));
				
				Collection colecaoProcessosIniciados = null;
				
				Map resultado = controlarPaginacao(httpServletRequest, retorno,
						filtroProcessoIniciado, ProcessoIniciado.class.getName());
				colecaoProcessosIniciados = (Collection) resultado.get("colecaoRetorno");
				retorno = (ActionForward) resultado.get("destinoActionForward");
				
		
				sessao.setAttribute("collProcessoIniciado", colecaoProcessosIniciados);
			}else{
				throw new ActionServletException(
	                    "atencao.processo_iniciado.datafinal_menor_datainicial", null, "");
			}
		}else{
			throw new ActionServletException(
                    "atencao.processo_iniciado.limite_trinta_dias", null, ""); 
		}
		
        return retorno;	
	}
	
private Date converterDataHora(String data, String hora) {
		
		SimpleDateFormat formatoDataHora = new SimpleDateFormat("dd/MM/yyyy k:mm:ss");
		try {
			return formatoDataHora.parse(data + " " + hora);
		} catch (ParseException e) {
			throw new ActionServletException("erro.sistema");

		}

	}

}
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
package gcom.gui.arrecadacao.aviso;

import gcom.arrecadacao.aviso.bean.AvisoBancarioHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza o filtro do aviso bancario de acordo com os par�metros informados
 * 
 * @author Vivianne Sousa
 * @created 13/03/2006
 */

public class FiltrarAvisoBancarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		/**
		 * Este caso de uso cria um filtro q ser� usado na pesquisa de aviso banc�rio
		 * 
		 * [UC0239] Filtrar Aviso Banc�rio
		 * 
		 * 
		 * @author Vivianne Sousa
		 * @date 13/03/2006
		 * 
		 * @param actionMapping
		 * @param actionForm
		 * @param httpServletRequest
		 * @param httpServletResponse
		 * @return
		 */

		ActionForward retorno = null;

		FiltrarAvisoBancarioActionForm filtrarAvisoBancarioActionForm = (FiltrarAvisoBancarioActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		boolean peloMenosUmParametroInformado = false;
		
		String atualizar = httpServletRequest.getParameter("atualizar");

		validacaoFinal(filtrarAvisoBancarioActionForm);

		String arrecadadorCodAgente = filtrarAvisoBancarioActionForm.getArrecadadorCodAgente();
		String dataLancamentoInicioString = filtrarAvisoBancarioActionForm.getDataLancamentoInicio();
		String dataLancamentoFimString = filtrarAvisoBancarioActionForm.getDataLancamentoFim();
		String tipoAviso = filtrarAvisoBancarioActionForm.getTipoAviso();
		String idContaBancaria = filtrarAvisoBancarioActionForm.getIdContaBancaria();
		String idMovimento = filtrarAvisoBancarioActionForm.getIdMovimento();
		String periodoArrecadacaoInicio = filtrarAvisoBancarioActionForm.getPeriodoArrecadacaoInicio();
		String periodoArrecadacaoFim = filtrarAvisoBancarioActionForm.getPeriodoArrecadacaoFim();
		String dataPrevisaoCreditoDebitoInicioString = filtrarAvisoBancarioActionForm.getDataPrevisaoCreditoDebitoInicio();
		String dataPrevisaoCreditoDebitoFimString = filtrarAvisoBancarioActionForm.getDataPrevisaoCreditoDebitoFim();
		String dataRealizacaoCreditoDebitoInicioString = filtrarAvisoBancarioActionForm.getDataRealizacaoCreditoDebitoInicio();
		String dataRealizacaoCreditoDebitoFimString = filtrarAvisoBancarioActionForm.getDataRealizacaoCreditoDebitoFim();
		String intervaloValorRealizadoInicio = filtrarAvisoBancarioActionForm.getIntervaloValorRealizadoInicio();
		String intervaloValorRealizadoFim = filtrarAvisoBancarioActionForm.getIntervaloValorRealizadoFim();
		String avisoAbertoFechado = filtrarAvisoBancarioActionForm.getAviso();

      
		//Cria o formato da data
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
		
		AvisoBancarioHelper avisoBancarioHelper = new AvisoBancarioHelper();
	
		
		// Passando os Par�metros para os filtros...

		if ((arrecadadorCodAgente != null)
				&& (!arrecadadorCodAgente.trim().equals(""))) {
			avisoBancarioHelper.setCodigoAgenteArrecadador(new Short(arrecadadorCodAgente));	
			peloMenosUmParametroInformado = true;
		}

		if ((dataLancamentoInicioString != null)
				&& (!dataLancamentoInicioString.equals(""))) {
			
			Date dataLancamentoInicio = null;
			Date dataLancamentoFim = null;
			
			if ((dataLancamentoFimString == null)|| (dataLancamentoFimString.equals(""))){
				dataLancamentoFimString = dataLancamentoInicioString;				
			}


				try {
					dataLancamentoInicio = formato.parse(dataLancamentoInicioString);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
			
			try {
				dataLancamentoFim = formato.parse(dataLancamentoFimString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			avisoBancarioHelper.setDataLancamentoInicial(dataLancamentoInicio);
			avisoBancarioHelper.setDataLancamentoFinal(dataLancamentoFim);
			peloMenosUmParametroInformado = true;
		}
		
		if ((tipoAviso != null) && (!tipoAviso.trim().equals(""))
				&& (!tipoAviso.trim().equals("3"))) {
			avisoBancarioHelper.setIndicadorCreditoDebito(new Short(tipoAviso));
			peloMenosUmParametroInformado = true;
		}

		
		if ((idContaBancaria != null) && (!idContaBancaria.trim().equals(""))) {
			avisoBancarioHelper.setIdContaBancaria(new Integer(idContaBancaria));
			peloMenosUmParametroInformado = true;
		}

		if ((idMovimento != null) && (!idMovimento.trim().equals(""))) {
			avisoBancarioHelper.setIdMovimentoArrecadador(new Integer(idMovimento));
			peloMenosUmParametroInformado = true;
		}

		if ((dataPrevisaoCreditoDebitoInicioString != null)
				&& (!dataPrevisaoCreditoDebitoInicioString.trim().equals(""))) {
			
			Date dataPrevisaoCreditoDebitoInicio = null;
			Date dataPrevisaoCreditoDebitoFim = null;
			
			if ((dataPrevisaoCreditoDebitoFimString == null)|| (dataPrevisaoCreditoDebitoFimString.equals(""))){
				dataPrevisaoCreditoDebitoFimString = dataPrevisaoCreditoDebitoInicioString;				
			}
				dataPrevisaoCreditoDebitoInicio = Util.converteStringParaDate(dataPrevisaoCreditoDebitoInicioString);
				dataPrevisaoCreditoDebitoFim = Util.converteStringParaDate(dataPrevisaoCreditoDebitoFimString);

				avisoBancarioHelper.setDataPrevistaInicial(dataPrevisaoCreditoDebitoInicio);
				avisoBancarioHelper.setDataPrevistaFinal(dataPrevisaoCreditoDebitoFim);
				peloMenosUmParametroInformado = true;
		}

		if ((dataRealizacaoCreditoDebitoInicioString != null)
				&& (!dataRealizacaoCreditoDebitoInicioString.trim().equals(""))) {
			
			Date dataRealizacaoCreditoDebitoInicio = null;
			Date dataRealizacaoCreditoDebitoFim = null;
			
			if ((dataRealizacaoCreditoDebitoFimString == null)|| (dataRealizacaoCreditoDebitoFimString.equals(""))){
				dataRealizacaoCreditoDebitoFimString = dataRealizacaoCreditoDebitoInicioString;				
			}

			try {
				dataRealizacaoCreditoDebitoInicio = formato.parse(dataRealizacaoCreditoDebitoInicioString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			try {
				dataRealizacaoCreditoDebitoFim = formato.parse(dataRealizacaoCreditoDebitoFimString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			avisoBancarioHelper.setDataRealizadaInicial(dataRealizacaoCreditoDebitoInicio);
			avisoBancarioHelper.setDataRealizadaFinal(dataRealizacaoCreditoDebitoFim);
			peloMenosUmParametroInformado = true;
		}

		// Tratando os periodos (mes/ano) para que possam ser feitos os filtros
		if ((periodoArrecadacaoInicio != null)
				&& (!periodoArrecadacaoInicio.trim().equals(""))) {

			int periodoArrecadacaoInicioTratado = Integer.parseInt(Util.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoInicio)) ;

			int periodoArrecadacaoFimTratado = Integer.parseInt(Util.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoFim));

			avisoBancarioHelper.setAnoMesReferenciaArrecadacaoInicial(periodoArrecadacaoInicioTratado);
			avisoBancarioHelper.setAnoMesReferenciaArrecadacaoFinal(periodoArrecadacaoFimTratado);
			peloMenosUmParametroInformado = true;
		}

		if ((intervaloValorRealizadoInicio != null)
				&& (!intervaloValorRealizadoInicio.trim().equals(""))) {
			
			avisoBancarioHelper.setValorRealizadoInicial(Util.formatarMoedaRealparaBigDecimal(intervaloValorRealizadoInicio));
			avisoBancarioHelper.setValorRealizadoFinal(Util.formatarMoedaRealparaBigDecimal(intervaloValorRealizadoFim));
			peloMenosUmParametroInformado = true;
		}

		// [FS0006] Verificar preenchimento dos campos
		if ((!peloMenosUmParametroInformado) && (avisoAbertoFechado.equals("-1"))) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		if (avisoAbertoFechado != null && (!avisoAbertoFechado.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& !avisoAbertoFechado.equals("1")){
			avisoBancarioHelper.setTipoAviso(avisoAbertoFechado);
		}
			
		// Filtrando os dados...
		Collection collectionAvisoBancario =  fachada.filtrarAvisoBancarioAbertoFechado(avisoBancarioHelper);
//		AvisoBancarioHelper objetoAvisoBancario = null;
//		AvisoBancarioHelper avisoBancarioHelperNovo = null;
		Integer totalRegistros = 0; 
//		int count = 0;
		if(collectionAvisoBancario != null && !collectionAvisoBancario.isEmpty()){
//			Iterator iterator = collectionAvisoBancario.iterator();
//		
//			while (iterator.hasNext()) {
//				objetoAvisoBancario = (AvisoBancarioHelper) iterator.next();
//				avisoBancarioHelperNovo = new AvisoBancarioHelper();
//		
//				avisoBancarioHelperNovo
//					.setAvisoBancario(objetoAvisoBancario.getAvisoBancario());
//				avisoBancarioHelperNovo
//					.setTipoAviso(avisoBancarioHelper.getTipoAviso());
//				count++;
//				
//			}
//			totalRegistros = count;
			totalRegistros = collectionAvisoBancario.size();
			sessao.setAttribute("totalRegistros",totalRegistros);
		}
			
		if (collectionAvisoBancario == null
				|| collectionAvisoBancario.isEmpty()) {
			// [FS0009] Nenhum registro encontrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "aviso banc�rio");
		}
		else {
			if (atualizar != null && totalRegistros == 1)
			{
				AvisoBancarioHelper avisoBancario2Helper = (AvisoBancarioHelper) collectionAvisoBancario.iterator().next();
				httpServletRequest.setAttribute("idAvisoBancario",
						avisoBancario2Helper.getAvisoBancario().getId());
				if(sessao.getAttribute("acao") != null){
					retorno = actionMapping
						.findForward("efetuarAnaliseAvisoBancario");
				}else{
					retorno = actionMapping
						.findForward("atualizarAvisoBancario");
				}
        	
			} else {
		        	retorno = actionMapping.findForward("manter");
		    }
	        
			sessao.setAttribute("collectionAvisoBancario",
					collectionAvisoBancario);
			
			sessao.setAttribute("filtroAvisoBancarioHelper", avisoBancarioHelper);
			
			sessao.setAttribute("filtrar_manter", "filtrar_manter");
		}
		
		return retorno;
	}

	private void validacaoFinal(FiltrarAvisoBancarioActionForm form) {

		Date dataLancamentoInicio = null;
		Date dataLancamentoFim = null;
		Integer periodoArrecadacaoInicio = null;
		Integer periodoArrecadacaoFim = null;
		Date dataPrevisaoCreditoDebitoInicio = null;
		Date dataPrevisaoCreditoDebitoFim = null;
		BigDecimal intervaloValorPrevistoInicio = null;
		BigDecimal intervaloValorPrevistoFim = null;
		Date dataRealizacaoCreditoDebitoInicio = null;
		Date dataRealizacaoCreditoDebitoFim = null;
		BigDecimal intervaloValorRealizadoInicio = null;
		BigDecimal intervaloValorRealizadoFim = null;

		
		Fachada fachada = Fachada.getInstancia();
		
		// per�odo de lan�amento do aviso inicial
		// per�odo de lan�amento do aviso final
		if (form.getDataLancamentoInicio() != null
				&& form.getDataLancamentoFim() != null) {
			if (!form.getDataLancamentoInicio().equals("")
					&& !form.getDataLancamentoFim().equals("")) {

				dataLancamentoInicio = Util.converteStringParaDate(form
						.getDataLancamentoInicio());
				dataLancamentoFim = Util.converteStringParaDate(form
						.getDataLancamentoFim());

			}
		}

		// per�odo de refer�ncia de arrecada��o inicial
		// per�odo de refer�ncia de arrecada��o final
		if (form.getPeriodoArrecadacaoInicio() != null
				&& form.getPeriodoArrecadacaoFim() != null) {
			if (!form.getPeriodoArrecadacaoInicio().equals("")
					&& !form.getPeriodoArrecadacaoFim().equals("")) {

				periodoArrecadacaoInicio = Integer.parseInt(Util
						.formatarMesAnoParaAnoMesSemBarra(form
								.getPeriodoArrecadacaoInicio()));
				periodoArrecadacaoFim = Integer.parseInt(Util
						.formatarMesAnoParaAnoMesSemBarra(form
								.getPeriodoArrecadacaoFim()));

			}
		}

		// per�odo de previs�o do cr�dito/d�bito inicial
		// per�odo de previs�o do cr�dito/d�bito final
		if (form.getDataPrevisaoCreditoDebitoInicio() != null
				&& form.getDataPrevisaoCreditoDebitoFim() != null) {
			if (!form.getDataPrevisaoCreditoDebitoInicio().equals("")
					&& !form.getDataPrevisaoCreditoDebitoFim().equals("")) {

				dataPrevisaoCreditoDebitoInicio = Util
						.converteStringParaDate(form
								.getDataPrevisaoCreditoDebitoInicio());
				dataPrevisaoCreditoDebitoFim = Util.converteStringParaDate(form
						.getDataPrevisaoCreditoDebitoFim());

			}
		}

		// intervalo de valor previsto inicial
		// intervalo de valor previsto final
		if (form.getIntervaloValorPrevistoInicio() != null
				&& form.getIntervaloValorPrevistoFim() != null) {
			if (!form.getIntervaloValorPrevistoInicio().equals("")
					&& !form.getIntervaloValorPrevistoFim().equals("")) {
	 
				intervaloValorPrevistoInicio = Util.formatarMoedaRealparaBigDecimal(form
						.getIntervaloValorPrevistoInicio());
				intervaloValorPrevistoFim = Util.formatarMoedaRealparaBigDecimal(form
						.getIntervaloValorPrevistoFim());

			}
		}

		// per�odo de realiza��o do cr�dito/d�bito inicial
		// per�odo de previs�o do cr�dito/d�bito final
		if (form.getDataRealizacaoCreditoDebitoInicio() != null
				&& form.getDataRealizacaoCreditoDebitoFim() != null) {
			if (!form.getDataRealizacaoCreditoDebitoInicio().equals("")
					&& !form.getDataRealizacaoCreditoDebitoFim().equals("")) {

				dataRealizacaoCreditoDebitoInicio = Util
						.converteStringParaDate(form
								.getDataRealizacaoCreditoDebitoInicio());
				dataRealizacaoCreditoDebitoFim = Util
						.converteStringParaDate(form
								.getDataRealizacaoCreditoDebitoFim());

			}
		}

		// intervalo de valor realizado inicial
		// intervalo de valor realizado final
		if (form.getIntervaloValorRealizadoInicio() != null
				&& form.getIntervaloValorRealizadoFim() != null) {
			if (!form.getIntervaloValorRealizadoInicio().equals("")
					&& !form.getIntervaloValorRealizadoFim().equals("")) {
				
				intervaloValorRealizadoInicio = Util.formatarMoedaRealparaBigDecimal(form
						.getIntervaloValorRealizadoInicio());
				intervaloValorRealizadoFim = Util.formatarMoedaRealparaBigDecimal(form
						.getIntervaloValorRealizadoFim());

			}
		}
		fachada.validacaoFinal(dataLancamentoInicio, dataLancamentoFim,
				periodoArrecadacaoInicio, periodoArrecadacaoFim,
				dataPrevisaoCreditoDebitoInicio, dataPrevisaoCreditoDebitoFim,
				intervaloValorPrevistoInicio, intervaloValorPrevistoFim,
				dataRealizacaoCreditoDebitoInicio,
				dataRealizacaoCreditoDebitoFim, intervaloValorRealizadoInicio,
				intervaloValorRealizadoFim);
		

	}

}

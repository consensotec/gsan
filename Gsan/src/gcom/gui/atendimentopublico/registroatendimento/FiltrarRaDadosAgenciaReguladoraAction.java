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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroRaDadosAgenciaReguladora;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0538] Filtrar RAs na Agencia Reguladora
 *
 * @author K�ssia Albuquerque
 * @date 02/05/2007
 */

public class FiltrarRaDadosAgenciaReguladoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
			ActionForward retorno = actionMapping.findForward("exibirListarRaDadosAgenciaReguladora");
			
			FiltrarRaDadosAgenciaReguladoraActionForm form =(FiltrarRaDadosAgenciaReguladoraActionForm) actionForm;
			
			HttpSession sessao = httpServletRequest.getSession(false);		
			
			String numeroRA = form.getNumeroRa();
			String motivoReclamacao = form.getMotivoReclamacao();
			String motivoEncerramentoAtendimento = form.getMotivoEncerramentoAtendimento();
			String indicadorSituacaoAgencia = form.getIndicadorSituacaoAgencia();
			String indicadorSituacaoRA = form.getIndicadorSituacaoRA();
			String periodoReclamacaoInicio = form.getPeriodoReclamacaoInicio();
			String periodoReclamacaoFim = form.getPeriodoReclamacaoFim();
			String periodoRetornoInicio = form.getPeriodoRetornoInicio();
			String periodoRetornoFim = form.getPeriodoRetornoFim();
			String motivoRetornoAgencia = form.getMotivoRetornoAgencia();
			
			FiltroRaDadosAgenciaReguladora filtroRaDadosAgenciaReguladora = new FiltroRaDadosAgenciaReguladora();
			
			filtroRaDadosAgenciaReguladora.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
			filtroRaDadosAgenciaReguladora.adicionarCaminhoParaCarregamentoEntidade("agenciaReguladoraMotReclamacao");
			filtroRaDadosAgenciaReguladora.adicionarCaminhoParaCarregamentoEntidade("atendimentoMotivoEncerramento");
			filtroRaDadosAgenciaReguladora.adicionarCaminhoParaCarregamentoEntidade("agenciaReguladoraMotRetorno");
			
			
			boolean peloMenosUmParametroInformado = false;
			
			
			// N�mero do RA
			
			if (numeroRA != null && !numeroRA.equals("")){
				
				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples
										(FiltroRaDadosAgenciaReguladora.REGISTRO_ATENDIMENTO_ID, numeroRA));
			}
			
			
			//	Motivo Reclama��o da Ag�ncia
			
			if (motivoReclamacao != null && !motivoReclamacao.trim().equalsIgnoreCase(String.valueOf
					(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples
										(FiltroRaDadosAgenciaReguladora.MOTIVO_RECLAMACAO_ID, motivoReclamacao));
			}
			
			
			//	Motivo Encerramento do Atendimento
			
			if (motivoEncerramentoAtendimento != null && !motivoEncerramentoAtendimento.trim().equalsIgnoreCase(String.valueOf
					(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples
										(FiltroRaDadosAgenciaReguladora.MOTIVO_ENCERRAMENTO_ID, motivoEncerramentoAtendimento));
			}
			
			
			//	Indicador da Situacao da Agencia Reguladora
			
			if (indicadorSituacaoAgencia != null && !indicadorSituacaoAgencia.equalsIgnoreCase("")&&
					!indicadorSituacaoAgencia.trim().equalsIgnoreCase("" + ConstantesSistema.SITUACAO_AGENCIA_TODOS)) {
				
				peloMenosUmParametroInformado = true;
					
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples(FiltroRaDadosAgenciaReguladora.INDICADOR_SITUACAO_AGENCIA,
							indicadorSituacaoAgencia));
				
			}
			
			
			//	Indicador da Situacao do RA antes da Agencia Reguladora
			
			if (indicadorSituacaoRA != null && !indicadorSituacaoRA.equalsIgnoreCase("")
					&& !indicadorSituacaoRA.trim().equalsIgnoreCase("" + ConstantesSistema.SITUACAO_RA_AGENCIA_TODOS)) {
				
				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples(FiltroRaDadosAgenciaReguladora.INDICADOR_SITUACAO_RA,
							indicadorSituacaoRA));
			}
			
			
			//  Per�odo da Reclama��o
			
			if (periodoReclamacaoInicio != null && !periodoReclamacaoInicio.trim().equals("") 
									&& periodoReclamacaoFim != null && !periodoReclamacaoFim.trim().equals("")) {
				
				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new Intervalo(FiltroRaDadosAgenciaReguladora.DATA_RECLAMACAO, 
							Util.converteStringParaDate(periodoReclamacaoInicio),Util.converteStringParaDate(periodoReclamacaoFim)));
				
			}
			
			
			//  Per�odo do Retorno
			
			if (periodoRetornoInicio != null && !periodoRetornoInicio.trim().equals("") 
									&& periodoRetornoFim != null && !periodoRetornoFim.trim().equals("")) {
				
				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new Intervalo(FiltroRaDadosAgenciaReguladora.DATA_RETORNO, 
							Util.converteStringParaDate(periodoRetornoInicio),Util.converteStringParaDate(periodoRetornoFim)));
				
			}	
			
			
			//	Motivo do Retorno para Ag�ncia Reguladora
			
			if (motivoRetornoAgencia != null && !motivoRetornoAgencia.trim().equalsIgnoreCase(String.valueOf
					(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples
										(FiltroRaDadosAgenciaReguladora.MOTIVO_RETORNO_ID, motivoRetornoAgencia));
			}
			
			
			
			if (!peloMenosUmParametroInformado) {
				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			}
			
			sessao.setAttribute("filtroRaDadosAgenciaReguladora", filtroRaDadosAgenciaReguladora);
			
			return retorno;
			}
}
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
package gcom.gui.cobranca;

import gcom.cobranca.CobrancaSituacaoHistorico;
import gcom.cobranca.FiltroCobrancaSituacaoHistorico;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroFaturamentoSituacaoHistorico;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Rafael Corr�a
 * @created 22/09/2008
 */
public class ExibirConsultarSituacaoEspecialCobrancaPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarSituacaoEspecialCobrancaPopup");

		Fachada fachada = Fachada.getInstancia();
		
		ConsultarSituacaoEspecialCobrancaPopupActionForm consultarSituacaoEspecialCobrancaPopupActionForm = 
			(ConsultarSituacaoEspecialCobrancaPopupActionForm) actionForm;
		

		String idCobrancaSituacaoHistorico = httpServletRequest.getParameter("idCobrancaSituacaoHistorico");
		
		if (idCobrancaSituacaoHistorico != null){
			
			FiltroCobrancaSituacaoHistorico filtroCobrancaSituacaoHistorico = new FiltroCobrancaSituacaoHistorico();
			filtroCobrancaSituacaoHistorico.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoHistorico.ID, new Integer(idCobrancaSituacaoHistorico)));
			filtroCobrancaSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacaoTipo");
			filtroCobrancaSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacaoMotivo");

			Collection colecaoCobrancaSituacaoHistorico = fachada.pesquisar(filtroCobrancaSituacaoHistorico, CobrancaSituacaoHistorico.class.getName());
			
			if (colecaoCobrancaSituacaoHistorico != null && !colecaoCobrancaSituacaoHistorico.isEmpty()) {

				CobrancaSituacaoHistorico cobrancaSituacaoHistorico = (CobrancaSituacaoHistorico) Util.retonarObjetoDeColecao(colecaoCobrancaSituacaoHistorico);
				
				if (cobrancaSituacaoHistorico.getImovel() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setIdImovel(cobrancaSituacaoHistorico.getImovel().getId().toString());
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setIdImovel("");
				}

				if (cobrancaSituacaoHistorico.getCobrancaSituacaoTipo() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setTipo(cobrancaSituacaoHistorico.getCobrancaSituacaoTipo().getDescricao());
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setTipo("");
				}
				
				if (cobrancaSituacaoHistorico.getCobrancaSituacaoMotivo() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMotivo(cobrancaSituacaoHistorico.getCobrancaSituacaoMotivo().getDescricao());
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMotivo("");
				}
				
				if (cobrancaSituacaoHistorico.getAnoMesCobrancaSituacaoInicio() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaCobrancaInicial(Util.formatarAnoMesParaMesAno(cobrancaSituacaoHistorico.getAnoMesCobrancaSituacaoInicio()));
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaCobrancaInicial("");
				}
				
				if (cobrancaSituacaoHistorico.getAnoMesCobrancaSituacaoFim() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaCobrancaFinal(Util.formatarAnoMesParaMesAno(cobrancaSituacaoHistorico.getAnoMesCobrancaSituacaoFim()));
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaCobrancaFinal("");
				}
				
				if (cobrancaSituacaoHistorico.getAnoMesCobrancaRetirada() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaRetirada(Util.formatarAnoMesParaMesAno(cobrancaSituacaoHistorico.getAnoMesCobrancaRetirada()));
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaRetirada("");
				}
				
				if (cobrancaSituacaoHistorico.getObservacaoInforma() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setObservacaoInforma(cobrancaSituacaoHistorico.getObservacaoInforma());
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setObservacaoInforma("");
				}
				
				if (cobrancaSituacaoHistorico.getObservacaoRetira() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setObservacaoRetira(cobrancaSituacaoHistorico.getObservacaoRetira());
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setObservacaoRetira("");
				}
				
			}
			
		}
				
		return retorno;

	}

}

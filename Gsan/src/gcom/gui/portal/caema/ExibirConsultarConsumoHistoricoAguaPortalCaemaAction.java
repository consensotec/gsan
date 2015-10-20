/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.portal.caema;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Consultar Consumo Histórico Portal Caema
 * 
 * @author Nathalia Santos
 * @since 02/02/2012
 * 
 */

public class ExibirConsultarConsumoHistoricoAguaPortalCaemaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);
		
		httpServletRequest.setAttribute("voltarServicos", true);

		ConsultarConsumoHistoricoAguaPortalCaemaActionForm consultarConsumoHistoricoAguaPortalCaemaActionForm = (ConsultarConsumoHistoricoAguaPortalCaemaActionForm) actionForm;
		
		Integer matricula = (Integer) sessao.getAttribute("matricula");
		
		String ip = httpServletRequest.getRemoteAddr(); 
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.CONSULTAR_CONSUMO_HISTORICO, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
		
		Collection<ImovelMicromedicao> imoveisMicromedicao = 
			Fachada.getInstancia().carregarDadosConsumo(
					matricula,true);

		if ( !Util.isVazioOrNulo(imoveisMicromedicao)) {				
			Collections.sort((List<ImovelMicromedicao>) imoveisMicromedicao, new ComparatorImovelMicromedicao());

			
			httpServletRequest.setAttribute("imoveisMicromedicao",imoveisMicromedicao);
		}else {
			httpServletRequest.removeAttribute("imoveisMicromedicao");
			
		}
		
			return actionMapping.findForward("exibirConsultarConsumoHistoricoAguaPortalCaemaAction");
	}
	
	class ComparatorImovelMicromedicao implements Comparator<ImovelMicromedicao>{
		public int compare(ImovelMicromedicao a, ImovelMicromedicao b) {
			
			int retorno = 0;
			Integer anoMesReferencia1 = a.getConsumoHistorico().getReferenciaFaturamento();
			Integer anoMesReferencia2 = b.getConsumoHistorico().getReferenciaFaturamento();

			if(anoMesReferencia1.compareTo(anoMesReferencia2) == 1){
				retorno = -1;
			}else if(anoMesReferencia1.compareTo(anoMesReferencia2) == -1){
				retorno = 1;
			}			
			return retorno;
		}
	}
}

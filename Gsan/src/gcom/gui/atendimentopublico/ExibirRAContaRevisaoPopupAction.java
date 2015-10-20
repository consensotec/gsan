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
package gcom.gui.atendimentopublico;

import gcom.cadastro.imovel.bean.ImovelRaContaMotivoRevisaoPopupHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Arthur Carvalho
 * @date 25/11/2013
 */
public class ExibirRAContaRevisaoPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,		ActionForm actionForm, HttpServletRequest httpServletRequest,		HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping.findForward("exibirRAContaRevisaoPopup");		
//		HttpSession sessao = getSessao(httpServletRequest);
		
		if ( httpServletRequest.getParameter("idImovel") != null && !httpServletRequest.getParameter("idImovel").equals("") ) {
			
			Integer idImovel = Integer.valueOf(httpServletRequest.getParameter("idImovel"));
			
		
			Collection<ImovelRaContaMotivoRevisaoPopupHelper>  imovelRAHelper = Fachada.getInstancia().pesquisarRegistroAtendimentoConsultarImovel(idImovel);
			
			if (imovelRAHelper != null && !imovelRAHelper.isEmpty() ) {
				httpServletRequest.setAttribute("colecaoImovelRAHelper", imovelRAHelper);
				Integer quantidadeTotalRA = Integer.valueOf(0);
				
				Iterator<ImovelRaContaMotivoRevisaoPopupHelper> iteratorHelper = imovelRAHelper.iterator();
				while ( iteratorHelper.hasNext() ) {
					ImovelRaContaMotivoRevisaoPopupHelper helper = (ImovelRaContaMotivoRevisaoPopupHelper) iteratorHelper.next();
					
					quantidadeTotalRA = quantidadeTotalRA + helper.getImovelRaPopupHelper().size();
				}
				ImovelRaContaMotivoRevisaoPopupHelper helperTotalRA = new ImovelRaContaMotivoRevisaoPopupHelper();
				helperTotalRA.setCountRATotal(String.valueOf(quantidadeTotalRA));
				
				Collection<ImovelRaContaMotivoRevisaoPopupHelper> colecaoTotalRA = new ArrayList<ImovelRaContaMotivoRevisaoPopupHelper>();
				colecaoTotalRA.add(helperTotalRA);
				httpServletRequest.setAttribute("colecaoTotalRA", colecaoTotalRA);
				
				
			}
			
			Collection<ImovelRaContaMotivoRevisaoPopupHelper>  imovelContaHelper = Fachada.getInstancia().pesquisarContasMotivoRevisaoConsultarImovel(idImovel);
			
			if ( imovelContaHelper != null && !imovelContaHelper.isEmpty() ) {
				
				httpServletRequest.setAttribute("colecaoImovelContaHelper", imovelContaHelper);
				
				BigDecimal valorContaTotal = BigDecimal.ZERO;
				Integer quantidadeTotalContas = Integer.valueOf(0);
				
				Iterator<ImovelRaContaMotivoRevisaoPopupHelper> iteratorHelper = imovelContaHelper.iterator();
				while ( iteratorHelper.hasNext() ) {
					ImovelRaContaMotivoRevisaoPopupHelper helper = (ImovelRaContaMotivoRevisaoPopupHelper) iteratorHelper.next();
					
					quantidadeTotalContas ++;
					valorContaTotal = valorContaTotal.add(Util.formatarMoedaRealparaBigDecimal(helper.getValorConta()));
				}
				ImovelRaContaMotivoRevisaoPopupHelper helperTotalConta = new ImovelRaContaMotivoRevisaoPopupHelper();
				helperTotalConta.setValorContaTotal(Util.formatarMoedaReal(valorContaTotal));
				helperTotalConta.setCountContaTotal(String.valueOf(quantidadeTotalContas));
				
				Collection<ImovelRaContaMotivoRevisaoPopupHelper> colecaoTotalConta = new ArrayList<ImovelRaContaMotivoRevisaoPopupHelper>();
				colecaoTotalConta.add(helperTotalConta);
				httpServletRequest.setAttribute("colecaoTotalConta", colecaoTotalConta);
			}
		}
		
				
		return retorno;	}
		
}

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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoRetornoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1020] Exibir Ordens de Repavimenta��o em Processo de Aceite.
 * 
 * @author Hugo Leonardo		
 * @date 17/05/2010
 */
public class ExibirAtualizarOrdemRepavimentacaoProcessoAceitePopupAction extends GcomAction {
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta a a��o de retorno
		ActionForward retorno = actionMapping.findForward("exibirAtualizarOrdemRepavimentacaoProcessoAceitePopUp");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarOrdemRepavimentacaoProcessoAceiteActionForm form = (FiltrarOrdemRepavimentacaoProcessoAceiteActionForm) actionForm;
		
		if ( sessao.getAttribute("numeroPaginasPesquisa") != null ) {
    		 
    		String paginaAtual = (String) sessao.getAttribute("numeroPaginasPesquisa"); 
    		form.setManterPaginaAux(  paginaAtual );
		}

		form.setDataAceite("");
		form.setIndicadorSituacaoAceite("");
		form.setMotivo("");
	
	    Collection collOrdemServicoPavimento = (Collection) sessao.getAttribute("collOrdemServicoPavimentoAceite");	    
	  
	   	// Perguntar se posso encontrar apenas pelo numero da Os ?
       	String numeroOS = null;
       	Integer idOrdemServico = null;
        if (httpServletRequest.getParameter("numeroOS") != null) {
        	numeroOS = httpServletRequest.getParameter("numeroOS"); 
        	idOrdemServico= Util.converterStringParaInteger(numeroOS);       	
        	
        	if ( idOrdemServico != null ) {
	        	FiltroOrdemServicoPavimento filtroOrdemServicoPavimento = new FiltroOrdemServicoPavimento();
				filtroOrdemServicoPavimento.adicionarParametro(new ParametroSimples( FiltroOrdemServicoPavimento.ORDEM_SERVICO_ID,
							idOrdemServico));
		
				Collection colecaoOrdemServicoPavimento = fachada.pesquisar(filtroOrdemServicoPavimento, 
						OrdemServicoPavimento.class.getName());
				OrdemServicoPavimento ordemServicoPavimento = (OrdemServicoPavimento) 
						Util.retonarObjetoDeColecao(colecaoOrdemServicoPavimento);
				
				//SB0001 
				//1.1
				if ( ordemServicoPavimento.getIndicadorAceite() != null ) {
					form.setIndicadorSituacaoAceite("2");
					form.setDataAceite(Util.formatarData(ordemServicoPavimento.getDataAceite()) );
					form.setMotivo(ordemServicoPavimento.getDescricaoMotivoAceite());
				
				} else {
					
					form.setIndicadorSituacaoAceite("1");
					form.setDataAceite(Util.formatarData(new Date()) );
				}
        	
        	} 
			
        }
     
        if (httpServletRequest.getParameter("acao") != null && 
        		(httpServletRequest.getParameter("acao").equals("link") 
        		|| httpServletRequest.getParameter("acao").equals("botao"))) {
       
        	OrdemServico os = fachada.pesquisarOrdemServico(idOrdemServico);
        
        	OrdemServicoPavimento ordemServicoPavimento = null;
        
			if (sessao.getAttribute("collOrdemServicoPavimentoAceite") != null
				&& !sessao.getAttribute("collOrdemServicoPavimentoAceite").equals("")) {
				
					OSPavimentoRetornoHelper oSPavimentoRetornoHelper = null;
		
					Iterator iterator = collOrdemServicoPavimento.iterator();
					
					while (iterator.hasNext()) {
					
						oSPavimentoRetornoHelper = (OSPavimentoRetornoHelper) iterator.next();
		
						if(oSPavimentoRetornoHelper.getOrdemServico().getId().equals(os.getId())){	
							
							ordemServicoPavimento = oSPavimentoRetornoHelper.getOrdemServicoPavimento();	
							
							httpServletRequest.setAttribute("ordemServicoPavimentoAtualizar", ordemServicoPavimento);
							sessao.setAttribute("ordemServicoPavimentoAtualizar", ordemServicoPavimento);						
						}
					}	
			}	 
				
        	sessao.setAttribute("acao", "link");       	
		}else if (httpServletRequest.getParameter("acao") != null && 
        		httpServletRequest.getParameter("acao").equals("aceitarOSConvergente") ) {
			
			httpServletRequest.setAttribute("ordemServicoPavimentoAceitarOSConvergente", true);
			httpServletRequest.setAttribute("OSConvergente", "true");
			sessao.setAttribute("ordemServicoPavimentoAceitarOSConvergente", true);
		}
        
		if (httpServletRequest.getParameter("page.offset") != null 
				&& !httpServletRequest.getParameter("page.offset").equals("")) {
			
        	String numeroPagina = httpServletRequest.getParameter("page.offset");   
        	form.setManterPaginaAux(numeroPagina);
		}

		return retorno;
	}
}

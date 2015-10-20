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
* Yara Taciane de Souza
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

import gcom.atendimentopublico.ordemservico.bean.OrdemRepavimentacaoProcessoAceiteHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1019] - Filtrar Ordens de Repavimentacao em Processo de Aceite;
 *  
 * Realiza o filtro de Ordens de Repavimenta��o em Processo de Aceite de acordo com os par�metros informados.
 * 
 * @author Hugo Leonardo
 * @created 14/05/2010
 */
public class FiltrarOrdemRepavimentacaoProcessoAceiteAction extends GcomAction {
	/**
	 * 
	 * @author Hugo Leonardo
	 * @date 14/05/2010
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	Fachada fachada = Fachada.getInstancia();
    	
        ActionForward retorno = actionMapping.findForward("retornarFiltroOrdemRepavimentacaoProcessoAceite");
        
        FiltrarOrdemRepavimentacaoProcessoAceiteActionForm form = (FiltrarOrdemRepavimentacaoProcessoAceiteActionForm) actionForm;
       
        HttpSession sessao = httpServletRequest.getSession(false);
        
        String idUnidadeResponsavel = null;
		if ( form.getIdUnidadeResponsavel() != null && !form.getIdUnidadeResponsavel().equals("-1") ) {			
			
			idUnidadeResponsavel= form.getIdUnidadeResponsavel();
		} else {
			throw new ActionServletException("atencao.required", null,
					"Unidade Responsavel");
		}
     
		String situacaoAceite = null;
		if (!form.getSituacaoAceite().equals("-1") && form.getSituacaoAceite() != null) {			
			situacaoAceite= form.getSituacaoAceite();
		} else {
				throw new ActionServletException("atencao.required", null,
						"Situa��o Retorno");
		}
		
		String periodoAceiteServicoInicial = null;
		String periodoAceiteServicoFinal = null;
		
		String periodoRetornoServicoInicial = null;
		String periodoRetornoServicoFinal = null;
		
		if(form.getPeriodoAceiteServicoInicial() != null && !form.getPeriodoAceiteServicoInicial().equals("") 
				&& form.getPeriodoAceiteServicoFinal() != null && !form.getPeriodoAceiteServicoFinal().equals("") ){				
			periodoAceiteServicoInicial= form.getPeriodoAceiteServicoInicial();						
			periodoAceiteServicoFinal= form.getPeriodoAceiteServicoFinal();	
				
		}else if(form.getPeriodoRetornoServicoInicial()!= null && !form.getPeriodoRetornoServicoInicial().equals("") 
				&& form.getPeriodoRetornoServicoFinal() != null && !form.getPeriodoRetornoServicoFinal().equals("")){
			
			periodoRetornoServicoInicial= form.getPeriodoRetornoServicoInicial();		
			periodoRetornoServicoFinal= form.getPeriodoRetornoServicoFinal();		
		}
		
		Date pRetornoServicoInicial = null;
		Date pRetornoServicoFinal = null; 
	
		if(periodoAceiteServicoInicial == null && periodoAceiteServicoFinal == null){
			
			if(periodoRetornoServicoInicial != null && periodoRetornoServicoFinal != null){
	        	
	        	String inicio = Util.formatarData(periodoRetornoServicoInicial);
			    String fim = Util.formatarData(periodoRetornoServicoFinal);
			    
			    pRetornoServicoInicial = Util.converteStringParaDate(periodoRetornoServicoInicial);
			    pRetornoServicoFinal = Util.converteStringParaDate(periodoRetornoServicoFinal);
			    
			    //Se data inicio maior que data fim
	    		if(Util.compararData(pRetornoServicoInicial, pRetornoServicoFinal) == 1){
	    			 
	    			throw new ActionServletException(
	    					"atencao.data_inicial_maior_data_final", new Exception(),inicio,fim);
	    		}	
	    				
				Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
				
				if(Util.compararData(pRetornoServicoInicial, dataAtualSemHora) == 1){
					String dataAtual = Util.formatarData(new Date());
					throw new ActionServletException(
							"atencao.data.superior.data.corrente", null, dataAtual);					
				}	
				
				if(Util.compararData(pRetornoServicoFinal, dataAtualSemHora) == 1){
					String dataAtual = Util.formatarData(new Date());
					throw new ActionServletException(
							"atencao.data.superior.data.corrente", null, dataAtual);					
				}	
			}
		}
		
		Date pEncerramentoOsInicial = null;
		Date pEncerramentoOsFinal = null; 
	
		if(periodoRetornoServicoInicial == null && periodoRetornoServicoFinal == null){
			
			if(periodoAceiteServicoInicial != null && periodoAceiteServicoFinal != null){
	        	
	        	String inicio = Util.formatarData(periodoAceiteServicoInicial);
			    String fim = Util.formatarData(periodoAceiteServicoFinal);
			    
			    pEncerramentoOsInicial = Util.converteStringParaDate(periodoAceiteServicoInicial);
			    pEncerramentoOsFinal = Util.converteStringParaDate(periodoAceiteServicoFinal);
			    
			    //Se data inicio maior que data fim
	    		if(Util.compararData(pEncerramentoOsInicial, pEncerramentoOsFinal) == 1){
	    			 
	    			throw new ActionServletException(
	    					"atencao.data_inicial_maior_data_final", new Exception(),inicio,fim);
	    		}		
	    		
				Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
				
				if(Util.compararData(pEncerramentoOsInicial, dataAtualSemHora) == 1){
					String dataAtual = Util.formatarData(new Date());
					throw new ActionServletException(
							"atencao.data.superior.data.corrente", null, dataAtual);					
				}	
				
				if(Util.compararData(pEncerramentoOsFinal, dataAtualSemHora) == 1){
					String dataAtual = Util.formatarData(new Date());
					throw new ActionServletException(
							"atencao.data.superior.data.corrente", null, dataAtual);					
				}
	    		
			}else{
				pEncerramentoOsInicial = Util.formatarDataSemHora( new Date());
				pEncerramentoOsFinal =  Util.formatarDataSemHora( new Date()); 
			}
			
		}
		
		Integer idUnidadeOrganizacional = null;
		
		
		if(form.getIdUnidadeOrganizacional() != null && !form.getIdUnidadeOrganizacional().equals("")) {				
			idUnidadeOrganizacional = new Integer(form.getIdUnidadeOrganizacional());	
				
		}
		
		OrdemRepavimentacaoProcessoAceiteHelper ordemRepavimentacaoProcessoAceiteHelper = new OrdemRepavimentacaoProcessoAceiteHelper();
		
		ordemRepavimentacaoProcessoAceiteHelper.setIdUnidadeResponsavel(new Integer(idUnidadeResponsavel));
		
		if (situacaoAceite != null && situacaoAceite.equals("TODAS") ) {
			situacaoAceite = "4";
		}
		ordemRepavimentacaoProcessoAceiteHelper.setSituacaoAceite(new Integer(situacaoAceite));
		
		ordemRepavimentacaoProcessoAceiteHelper.setPeriodoAceiteServicoInicial(periodoAceiteServicoInicial);
		ordemRepavimentacaoProcessoAceiteHelper.setPeriodoAceiteServicoFinal(periodoAceiteServicoFinal);	
		
		ordemRepavimentacaoProcessoAceiteHelper.setPeriodoRetornoServicoInicial(periodoRetornoServicoInicial);
		ordemRepavimentacaoProcessoAceiteHelper.setPeriodoRetornoServicoFinal(periodoRetornoServicoFinal);		
		
		ordemRepavimentacaoProcessoAceiteHelper.setIdUnidadeOrganizacional(idUnidadeOrganizacional);
		
		Integer totalOrdemRepavimentacaoProcessoAceite = fachada.
			pesquisarOrdemRepavimentacaoProcessoAceiteCount(ordemRepavimentacaoProcessoAceiteHelper);
		
		if(totalOrdemRepavimentacaoProcessoAceite > 1000){
			throw new ActionServletException(
					"atencao.pesquisa.muitosregistros", null,"");	
		}
		
		sessao.setAttribute("osPavimentoAceiteHelper",ordemRepavimentacaoProcessoAceiteHelper);
		
       return retorno;
    }
   
}
 
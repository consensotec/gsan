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
package gcom.gui.arrecadacao;


import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os par�metros para realiza��o
 * da filtragem dos movimentos dos arrecadadores 
 *
 * @author Raphael Rossiter, Pedro Alexandre
 * @date 23/02/2006, 04/07/2007
 */
public class ExibirFiltrarMovimentoArrecadadoresAction extends GcomAction {
    
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, 
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirFiltrarMovimentoArrecadadores");
        
        Fachada fachada = Fachada.getInstancia();
        
        FiltrarMovimentoArrecadadoresActionForm filtrarMovimentoArrecadadoresActionForm = (FiltrarMovimentoArrecadadoresActionForm) actionForm;
        
        
        //Carregar a data corrente do sistema
        //====================================
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        String indicadorRelatorio = httpServletRequest.getParameter("relatorio");
        
        if(filtrarMovimentoArrecadadoresActionForm.getIndicadorRelatorio() == null || filtrarMovimentoArrecadadoresActionForm.getIndicadorRelatorio().equals("")){
	        if(indicadorRelatorio != null && indicadorRelatorio.equals("sim")){
	        	//httpServletRequest.setAttribute("relatorio",ConstantesSistema.SIM);
	        	filtrarMovimentoArrecadadoresActionForm.setIndicadorRelatorio(""+ConstantesSistema.SIM);
	        	filtrarMovimentoArrecadadoresActionForm.setRemessa(""+ConstantesSistema.CODIGO_RETORNO);
	        }else{
	        	filtrarMovimentoArrecadadoresActionForm.setIndicadorRelatorio(""+ConstantesSistema.NAO);
	        	//httpServletRequest.setAttribute("relatorio",ConstantesSistema.NAO);
	        }
        }
        
        //Data Corrente
        httpServletRequest.setAttribute("dataAtual", formatoData.format(dataCorrente.getTime()));
        
        FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
        Collection colecaoArrecadacaoForma = fachada.pesquisar(filtroArrecadacaoForma,ArrecadacaoForma.class.getName());
        httpServletRequest.setAttribute("colecaoArrecadacaoForma",colecaoArrecadacaoForma);
        
        
        if (filtrarMovimentoArrecadadoresActionForm.getBanco() != null &&
            	!filtrarMovimentoArrecadadoresActionForm.getBanco().equals("")){	
        
        	FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

        	filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
        	
        	filtroArrecadador.adicionarParametro(new ParametroSimples(
        	FiltroArrecadador.CODIGO_AGENTE, filtrarMovimentoArrecadadoresActionForm.getBanco()));

            
            Collection colecaoArrecadador = fachada.pesquisar(filtroArrecadador,
            Arrecadador.class.getName());

            if (colecaoArrecadador == null || colecaoArrecadador.isEmpty()) {
                
            	httpServletRequest.setAttribute("corBanco", "exception");
                
            	filtrarMovimentoArrecadadoresActionForm.setBanco("");
            	
            	filtrarMovimentoArrecadadoresActionForm.setDescricaoBanco("ARRECADADOR INEXISTENTE");
                
            	httpServletRequest.setAttribute("nomeCampo", "banco");
            	
            } else {
                Arrecadador arrecadador = (Arrecadador) Util.retonarObjetoDeColecao(colecaoArrecadador);
                
                filtrarMovimentoArrecadadoresActionForm.setBanco(String.valueOf(arrecadador.getCodigoAgente()));
                
                filtrarMovimentoArrecadadoresActionForm.setDescricaoBanco(arrecadador.getCliente().getNome());
                
                httpServletRequest.setAttribute("corBanco", "valor");
                
                httpServletRequest.setAttribute("nomeCampo", "identificacaoServico");
            }
        }
      
        if(httpServletRequest.getAttribute("nomeCampo") == null)
        {
        	httpServletRequest.setAttribute("nomeCampo", "banco");
        }
        return retorno;
    }
}


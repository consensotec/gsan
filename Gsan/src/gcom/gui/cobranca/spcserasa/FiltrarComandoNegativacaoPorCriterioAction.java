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
* Thiago Vieira
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.bean.ComandoNegativacaoHelper;
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
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class FiltrarComandoNegativacaoPorCriterioAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

//    	 localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("exibirManterComandoNegativacao");
		HttpSession sessao = httpServletRequest.getSession(false);

        FiltrarComandoNegativacaoPorCriterioActionForm form = (FiltrarComandoNegativacaoPorCriterioActionForm) actionForm; 

        String tituloComando = "";
    	Short tipoPesquisaTituloComando = null;
    	Short indicadorComandoSimulado = null;
    	Date geracaoComandoInicio = null;
    	Date geracaoComandoFim = null;
    	Integer idUsuarioResponsavel = null;
    	String checkAtualizar = "";
    	Integer idNegativador = null;
    	
        if (!form.getTituloComando().equals("") && form.getTituloComando() != null){
        	tituloComando = form.getTituloComando();
        }
        if (!form.getTipoBuscaTituloComando().equals("") && form.getTipoBuscaTituloComando() != null){
        	tipoPesquisaTituloComando = Short.parseShort(form.getTipoBuscaTituloComando());
        }
        if (!form.getComandoSimulado().equals("") && form.getComandoSimulado() != null){
        	indicadorComandoSimulado = Short.parseShort(form.getComandoSimulado());
        }
   
        if((!form.getDataGeracaoComandoInicial().equals("") && form.getDataGeracaoComandoInicial() != null) &&  (!form.getDataGeracaoComandoFinal().equals("") && form.getDataGeracaoComandoFinal() != null)){
        	Date dataInicial = Util.converteStringParaDate(form.getDataGeracaoComandoInicial()); 
        	Date dataFinal =  Util.converteStringParaDate(form.getDataGeracaoComandoFinal()); 
        	
			//Se data inicio maior que data fim
    		if(Util.compararData(dataInicial, dataFinal) == 1) {
            	String inicio = Util.formatarData(dataInicial);
            	String fim = Util.formatarData(dataFinal);

    			throw new ActionServletException(
    					"atencao.data_inicial_maior_data_final", inicio,fim);
    		}

    		geracaoComandoInicio = Util.formatarDataInicial(dataInicial);
    		geracaoComandoFim = Util.formatarDataFinal(dataFinal);
        }

        if (!form.getUsuarioResponsavelId().equals("") && form.getUsuarioResponsavelId() != null){
        	idUsuarioResponsavel = new Integer(form.getUsuarioResponsavelId());
        }
        if (!form.getCheckAtualizar().equals("") && form.getCheckAtualizar() != null){
        	checkAtualizar = form.getCheckAtualizar();
        }
        if (!form.getIdNegativador().equals("") && form.getIdNegativador() != null){
        	idNegativador = new Integer(form.getIdNegativador());
        }

        ComandoNegativacaoHelper comandoNegativacaoHelper = new ComandoNegativacaoHelper();
        comandoNegativacaoHelper.setTituloComando(tituloComando);
        comandoNegativacaoHelper.setTipoPesquisaTituloComando(tipoPesquisaTituloComando);
        comandoNegativacaoHelper.setIndicadorComandoSimulado(indicadorComandoSimulado);
        comandoNegativacaoHelper.setGeracaoComandoInicio(geracaoComandoInicio);
        comandoNegativacaoHelper.setGeracaoComandoFim(geracaoComandoFim);
    	comandoNegativacaoHelper.setIdUsuarioResponsavel(idUsuarioResponsavel);
    	comandoNegativacaoHelper.setIdNegativador(idNegativador);
    	
    	sessao.setAttribute("checkAtualizar",checkAtualizar);
    	sessao.setAttribute("comandoNegativacaoHelper",comandoNegativacaoHelper);
    	
		return retorno;
        
    }
}
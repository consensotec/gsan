/**
 * 
 */
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

import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.CobrancaCriterioLinha;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComando;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.cobranca.FiltroCobrancaCriterioLinha;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Exibir o Resultado a pesquisa comando de a��o de cobran�a - Parametros 
 *
 * [UC0253] Pesquisar Comando de A��o de Conbran�a
 * @author Rafael Santos
 * @since 08/03/2006
 */
public class ExibirResultadoPesquisaComandoAcaoCobrancaCriteriosAction  extends GcomAction{
	
	/**
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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirResultadoPesquisaComandoAcaoCobrancaCriteriosAction");

        HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
        
        String idCobrancaAcaoAtividadeComando = httpServletRequest.getParameter("idCobrancaAcaoAtividadeComando");
        
        Collection colecaoCobrancaCriterio = null;
        Collection colecaoCobrancaCriterioLinha = null;
        
        if(idCobrancaAcaoAtividadeComando != null && !idCobrancaAcaoAtividadeComando.equals("")){
        	
        	
        	FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando = new FiltroCobrancaAcaoAtividadeComando();
    		filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterio");
    		filtroCobrancaAcaoAtividadeComando.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoAtividadeComando.ID,idCobrancaAcaoAtividadeComando));
    		
    		Collection colecaoCobrancaAcaoAtividadeComando = fachada.pesquisar(filtroCobrancaAcaoAtividadeComando,CobrancaAcaoAtividadeComando.class.getName());

    		CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando) colecaoCobrancaAcaoAtividadeComando.iterator().next();
    		
        	FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
        	
        	filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterio.ID,cobrancaAcaoAtividadeComando.getCobrancaCriterio().getId()));
    		
        	colecaoCobrancaCriterio = fachada.pesquisar(filtroCobrancaCriterio,CobrancaCriterio.class.getName());
        	
        	if(colecaoCobrancaCriterio != null && !colecaoCobrancaCriterio.isEmpty()){
        		CobrancaCriterio cobrancaCriterio = (CobrancaCriterio) colecaoCobrancaCriterio.iterator().next();
        		
        		FiltroCobrancaCriterioLinha filtroCobrancaCriterioLinha = new FiltroCobrancaCriterioLinha();
        		filtroCobrancaCriterioLinha.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaCriterioLinha.IMOVEL_PERFIL);
        		filtroCobrancaCriterioLinha.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaCriterioLinha.CATEGORIA);
        		filtroCobrancaCriterioLinha.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterioLinha.COBRANCA_CRITERIO_ID,cobrancaCriterio.getId()));
        		
        	     Map resultado = controlarPaginacao(httpServletRequest, retorno,
        	    		 filtroCobrancaCriterioLinha, CobrancaCriterioLinha.class.getName());
        	    colecaoCobrancaCriterioLinha = (Collection) resultado.get("colecaoRetorno");
       	     	retorno = (ActionForward) resultado.get("destinoActionForward");
        		//colecaoCobrancaCriterioLinha = fachada.pesquisar(filtroCobrancaCriterioLinha,CobrancaCriterioLinha.class.getName());
        	}
    		
        }
        
        sessao.setAttribute("colecaoCobrancaCriterio",colecaoCobrancaCriterio);
        sessao.setAttribute("colecaoCobrancaCriterioLinha",colecaoCobrancaCriterioLinha);
        
        return retorno;
    }

}

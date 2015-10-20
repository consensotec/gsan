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

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o da p�gina de executar atividade de a��o de cobran�a
 * 
 * @author  pedro alexandre
 * @created 31 de Janeiro de 2006
 */
public class ExibirExecutarAtividadeAcaoCobrancaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
    	//cria a vari�vel de retorno e seta o mapeamento para a tela de executar atividade de a��o de cobran�a 
        ActionForward retorno = actionMapping.findForward("exibirExecutarAtividadeAcaoCobrancaAction");

        //cria uma inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();
                
        //pesquisa os par�metros do sistema
		SistemaParametro sistemaParametros = fachada.pesquisarParametrosDoSistema();
		
		//recupera o Ano/M�s de refer�ncia do ciclo de cobran�a
		Integer anoMesCicloCobranca = sistemaParametros.getAnoMesFaturamento();
        
		//manda o Ano/M�s de refer�ncia do ciclo de cobran�a no request
		httpServletRequest.setAttribute("anoMesCicloCobranca",anoMesCicloCobranca);
		
        /*Pesquisar as atividades de cobran�a do cronograma que foram previamente comandas
        =============================================================================== */
        //realizando a pesquisa das atividades de cobran�a de cronograma
        Collection colecaoAtividadesCobrancaCronograma = fachada.pesquisarCobrancaAcaoAtividadeCronograma();	
        
        
        /*Pesquisar as atividades de cobran�a eventuais que foram previamente comandas
        =========================================================================== */
        //realizando a pesquisa das atividades de cobran�a eventuais	
        Collection colecaoAtividadesCobrancaEventuais = fachada.pesquisarCobrancaAcaoAtividadeComando();
        
        
        //[FS0002] - Verificar a exist�ncia de atividade do cronograma comandada
        if (colecaoAtividadesCobrancaCronograma == null || colecaoAtividadesCobrancaCronograma.isEmpty()){
        	//[FS0003] - Verificar exist�ncia de atividade eventual comandada
        	if (colecaoAtividadesCobrancaEventuais == null || colecaoAtividadesCobrancaEventuais.isEmpty()){
        		throw new ActionServletException("atencao.naocadastrado", null, "Atividade(s) de a��o de cobran�a");
        	}
        }

        //manda a cole��o de atividades de cobran�a de cronograma no request para a p�gina
        httpServletRequest.setAttribute("colecaoAtividadesCobrancaCronograma",colecaoAtividadesCobrancaCronograma);
        
        //manda a cole��o de atividades de cobran�a eventuais no request para a p�gina
        httpServletRequest.setAttribute("colecaoAtividadesCobrancaEventuais",colecaoAtividadesCobrancaEventuais);
        
        //retorna o mapeamento contido na vari�vel retorno
        return retorno;
    }
}



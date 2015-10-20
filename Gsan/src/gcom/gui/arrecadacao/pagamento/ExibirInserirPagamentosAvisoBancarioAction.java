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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

/**
 * Action que inicializa a primeira p�gina do processo de inserir pagamentos
 * 
 * @author Pedro Alexandre
 * @created 16/02/2006
 */
public class ExibirInserirPagamentosAvisoBancarioAction extends GcomAction {

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
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
        //seta o mapeamento de retorno para a p�gina da primeira aba
        ActionForward retorno = actionMapping.findForward("inserirPagamentosAvisoBancario");
        
        DynaValidatorActionForm pagamentoActionForm = (DynaValidatorActionForm) actionForm;

        //cria uma inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //cria uma inst�ncia da sess�o
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //cria o filtro de forma de arrecada��o
        FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
        
        //seta a ordena��o do resultado da pesquisa
        filtroArrecadacaoForma.setCampoOrderBy(FiltroArrecadacaoForma.DESCRICAO);

        //pesquisa as formas de arrecada��o no sistema
        Collection<ArrecadacaoForma> colecaoFormaArrecadacao = fachada.pesquisar(filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
        	
        //se a cole��o de forma de arrecada��o estiver nula ou vazia 
        if(colecaoFormaArrecadacao == null || colecaoFormaArrecadacao.isEmpty()){
        	//[FS0002] levanta exce��o
        	throw new ActionServletException("atencao.naocadastrado", null,"Forma de Arrecada��o");
        }else{
        	//manda a cole��o de forma de arrecada��o para a sess�o
        	sessao.setAttribute("colecaoFormaArrecadacao", colecaoFormaArrecadacao);	
        }
        
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        //Data Corrente
        httpServletRequest.setAttribute("dataAtual", formatoData.format(dataCorrente.getTime()));
        
        String idAvisoBancario = (String) pagamentoActionForm.get("idAvisoBancario");
        
        if (idAvisoBancario != null && !idAvisoBancario.trim().equals("")) {
        	FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
        	filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);
        	filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, idAvisoBancario));
        	
        	Collection colecaoAvisoBancario = fachada.pesquisar(filtroAvisoBancario, AvisoBancario.class.getName());
        	
        	if (colecaoAvisoBancario != null && !colecaoAvisoBancario.isEmpty()) {
        		AvisoBancario avisoBancario = (AvisoBancario) Util.retonarObjetoDeColecao(colecaoAvisoBancario);
        		
        		pagamentoActionForm.set("codigoAgenteArrecadador", avisoBancario.getArrecadador().getCodigoAgente().toString());
        		pagamentoActionForm.set("dataLancamentoAviso", Util.formatarData(avisoBancario.getDataLancamento()));
        		pagamentoActionForm.set("numeroSequencialAviso", avisoBancario.getNumeroSequencial().toString());
        		
        	} else {
        		
        		pagamentoActionForm.set("idAvisoBancario", "");
        		pagamentoActionForm.set("codigoAgenteArrecadador", "");
        		pagamentoActionForm.set("dataLancamentoAviso", "AVISO INEXISTENTE");
        		pagamentoActionForm.set("numeroSequencialAviso", "");
        		
        		httpServletRequest.setAttribute("avisoInexistente", true);
        	}
        } else {
        	pagamentoActionForm.set("dataLancamentoAviso", "");
        }
        
    	
        
        //retorna o mapeamento contido na vari�vel retorno
        return retorno;
    }
}

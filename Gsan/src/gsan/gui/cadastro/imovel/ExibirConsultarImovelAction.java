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
package gsan.gui.cadastro.imovel;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import gsan.atualizacaocadastral.bean.AtualizacoesPorInconsistenciaHelper;
import gsan.atualizacaocadastral.bean.DadosMovimentoAtualizacaoCadastralDMHelper;
import gsan.gui.GcomAction;
import gsan.gui.StatusWizard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0472] Consultar Imovel
 * @author Rafael Santos
 * @since 07/09/2006
 *
 */
public class ExibirConsultarImovelAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        // localiza o action no objeto actionmapping
        ActionForward retorno = actionMapping
                .findForward("exibirConsultarImovelAction");
        
        ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm)actionForm;
        //obt�m a inst�ncia da sess�o
        HttpSession sessao = getSessao(httpServletRequest);

        // Monta o Status do Wizard
		StatusWizard statusWizard = new StatusWizard(
				"consultarImovelWizardAction", "exibirConsultarImovelAction",
				"cancelarConsultarImovelAction", 
				"",	
				"exibirConsultarImovelAction.do");        
	        
        statusWizard
            .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
            1, "DadosCadastraisPrimeiraAbaA.gif", "DadosCadastraisPrimeiraAbaD.gif",
            "exibirConsultarImovelDadosCadastraisAction",
            ""));
        statusWizard
            .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
            2, "DadosAdicionaisIntervaloAbaA.gif", "DadosAdicionaisIntervaloAbaD.gif",
            "exibirConsultarImovelDadosComplementaresAction",
            ""));

        statusWizard
        	.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
    		3, "AnaliseLigacaoConsumoIntervaloAbaA.gif", "AnaliseLigacaoConsumoIntervaloAbaD.gif",
        	"exibirConsultarImovelDadosAnaliseMedicaoConsumoAction",
            ""));
        
        statusWizard
    	    .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
    		4, "HistoricoFaturamentoIntervaloAbaA.gif", "HistoricoFaturamentoIntervaloAbaD.gif",
    	    "exibirConsultarImovelHistoricoFaturamentoAction",
            ""));

        statusWizard
	    	.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
	    	5, "DebitosImovelIntervaloAbaA.gif", "DebitosImovelIntervaloAbaD.gif",
	        "exibirConsultarImovelDebitosAction",
            ""));
        
        statusWizard
    	    .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
    	    6, "PagamentosImovelIntervaloAbaA.gif", "PagamentosImovelIntervaloAbaD.gif",
            "exibirConsultarImovelPagamentosAction",
            ""));

        statusWizard
	    	.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
	    	7, "DevolucoesImovelIntervaloAbaA.gif", "DevolucoesImovelIntervaloAbaD.gif",
            "exibirConsultarImovelDevolucoesAction",
            ""));

        statusWizard
    		.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
    	    8, "DocumentosCobracaIntervaloAbaA.gif", "DocumentosCobrancaIntervaloAbaD.gif",
            "exibirConsultarImovelDocumentosCobrancaAction",
            ""));

        statusWizard
			.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
			9, "ParcelamentoIntervaloAbaA.gif", "ParcelamentoIntervaloAbaD.gif",
            "exibirConsultarImovelParcelamentosDebitosAction",
            ""));
        
        statusWizard
			.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
			10, "RegistroAtendimentoUltimaAbaA.gif", "RegistroAtendimentoUltimaAbaD.gif",
			"exibirConsultarImovelRegistroAtendimentoAction",
			""));
        
        /*
         * Consulta o im�vel vindo
         * da tela de inconsist�ncia
         * atualiza��o cadastral
         */
        processarObjetoConsultarAtualizarDadosImoveisInconsistentes(statusWizard, 
    		   httpServletRequest, consultarImovelActionForm);
        
        //manda o statusWizard para a sessao
        sessao.setAttribute("statusWizard", statusWizard);

        return retorno;
    }
    
    /**
     * M�todo respons�vel por<br>
     * processar uma requisi��o 
     * daa tela exibirAtualizarDadosImoveisInconsistentesAction
     * @author Jonathan Marcos
     * @since 28/10/2014
     * @param statusWizard
     * @param httpServletRequest
     * @param consultarImovelActionForm
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void processarObjetoConsultarAtualizarDadosImoveisInconsistentes(StatusWizard statusWizard,
    			HttpServletRequest httpServletRequest,ConsultarImovelActionForm consultarImovelActionForm){
    	 if(httpServletRequest.getParameter("objetoConsulta") != null && 
         		httpServletRequest.getParameter("objetoConsulta").equals("consultarImovel")){
         	
         	statusWizard.setCaminhoActionVoltarFiltro("exibirAtualizarDadosImoveisInconsistentesAction");
         	
         	String objetoConsultaFiltro = httpServletRequest.getParameter("objetoConsultaFiltro");
         	String id = httpServletRequest.getParameter("id");
         	String objetoConsultaFiltroAnterior = httpServletRequest.getParameter("objetoConsultaFiltroAnterior");
         	statusWizard
			.setCaminhoActionVoltarFiltro("exibirAtualizarDadosImoveisInconsistentesAction.do?" +
					"objetoConsultaFiltro=" + objetoConsultaFiltro
					+ "&id=" + id
					+ "&objetoConsultaFiltroAnterior="+objetoConsultaFiltroAnterior
					+ "&erro=");
         	
         	Collection<DadosMovimentoAtualizacaoCadastralDMHelper> colecaoImoveisInconsistentesHelper = (Collection<DadosMovimentoAtualizacaoCadastralDMHelper>) 
     				httpServletRequest.getSession().getAttribute("colecaoImoveisInconsistentesHelper");
     		
     		
     		Iterator iteratorHelper = colecaoImoveisInconsistentesHelper.iterator();
     		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
     		while( iteratorHelper.hasNext() ) {
     			DadosMovimentoAtualizacaoCadastralDMHelper dadosMovimentoHelper = (DadosMovimentoAtualizacaoCadastralDMHelper) iteratorHelper.next();
     			Collection<AtualizacoesPorInconsistenciaHelper> colecaoAtualizacoesHelper = dadosMovimentoHelper.getColecaoAtualizacoesHelper();
     			Iterator iteratorAtualizacoesHelper = colecaoAtualizacoesHelper.iterator();
     			while( iteratorAtualizacoesHelper.hasNext() ) {
     				AtualizacoesPorInconsistenciaHelper atualizacoesHelper = (AtualizacoesPorInconsistenciaHelper) iteratorAtualizacoesHelper.next();
     				String valorAlteracao = (requestMap.get("alteracao"+ atualizacoesHelper.getIdRetornoAtlzCadastral()))[0];
     				atualizacoesHelper.setCodigoAlteracao(valorAlteracao);
     			}
     		}
         	
     		httpServletRequest.getSession().setAttribute("colecaoImoveisInconsistentesHelper", colecaoImoveisInconsistentesHelper);
         	if ( httpServletRequest.getParameter("idImovel") != null && 
             		!httpServletRequest.getParameter("idImovel").equals("") ) {
         		consultarImovelActionForm.setIdImovelDebitos(""+ httpServletRequest.getParameter("idImovel"));
         		httpServletRequest.getSession().setAttribute("atualizacaoCadastral", true);
             }
         }
    }
}
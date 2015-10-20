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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.gui.integracao.GisHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade gerar as abas que ser�o respons�veis pelo processo de inser��o de um
 * registro de atendimento
 *
 * @author Raphael Rossiter
 * @date 24/07/2006
 */
public class ExibirInserirRegistroAtendimentoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("inserirRegistroAtendimento");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Boolean obj = (Boolean)sessao.getAttribute("origemGIS");
        boolean origemGIS = false;
        if(obj != null)
        	origemGIS = obj.booleanValue();
        	
        /*
         * verifica se a chamada � originada a partir do GIS. Caso sim,
         * requisita o objeto da chamada na sess�o
         */
        GisHelper gisHelper = null;
        if(origemGIS){
        	gisHelper = (GisHelper) sessao.getAttribute("gisHelper");
        }
        
        
        //Removendo todos os objetos da sess�o 
        sessao.removeAttribute("statusWizard");
        sessao.removeAttribute("InserirRegistroAtendimentoActionForm");
        sessao.removeAttribute("colecaoMeioSolicitacao");
        sessao.removeAttribute("colecaoSolicitacaoTipo");
        sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");

        
        //Aba 02
        sessao.removeAttribute("colecaoDivisaoEsgoto");
        sessao.removeAttribute("colecaoLocalOcorrencia");
        sessao.removeAttribute("colecaoPavimentoRua");
        sessao.removeAttribute("colecaoPavimentoCalcada");
        sessao.removeAttribute("colecaoBairroArea");
        
        sessao.removeAttribute("solicitacaoTipoRelativoFaltaAgua");
        sessao.removeAttribute("solicitacaoTipoRelativoAreaEsgoto");
        
        sessao.removeAttribute("colecaoEnderecos");
        sessao.removeAttribute("habilitarAlteracaoEndereco");
        
        sessao.removeAttribute("desabilitarDivisaoEsgoto");
        sessao.removeAttribute("desabilitarPavimentoRua");
        sessao.removeAttribute("desabilitarPavimentoCalcada");
        sessao.removeAttribute("desabilitarDescricaoLocalOcorrencia");
        
        //Informar Solicitante
        sessao.removeAttribute("enderecoOcorrenciaRA");
        sessao.removeAttribute("colecaoEnderecosSolicitante");
        sessao.removeAttribute("colecaoFonesSolicitante");
        sessao.removeAttribute("desabilitarDadosSolicitanteUnidade");
		sessao.removeAttribute("desabilitarDadosSolicitanteFuncionario");
		sessao.removeAttribute("desabilitarDadosSolicitanteNome");
		sessao.removeAttribute("habilitarAlteracaoEnderecoSolicitante");
		sessao.removeAttribute("desabilitarDadosSolicitanteCliente");
		
		//Aba N�04 - Anexos
		sessao.removeAttribute("colecaoRegistroAtendimentoAnexo");
        
        //Montando o Status do Wizard (Componente respons�vel pela gera��o das abas)
		StatusWizard statusWizard = null;
		
		//Se for originada de uma requisi��o GIS, bot�o de desfazer aponta para a p�gina inicial da requisi��o
		
		if(origemGIS){
			statusWizard = new StatusWizard(
	                "inserirRegistroAtendimentoWizardAction", "concluirInserirRegistroAtendimentoAction",
	                "cancelarInserirRegistroAtendimentoAction", gisHelper.gerarURLChamada());
		}
		else{
			statusWizard = new StatusWizard(
	                "inserirRegistroAtendimentoWizardAction", "concluirInserirRegistroAtendimentoAction",
	                "cancelarInserirRegistroAtendimentoAction", "exibirInserirRegistroAtendimentoAction.do");
		}
		
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        1, "DadosGeraisPrimeiraAbaA.gif", "DadosGeraisPrimeiraAbaD.gif",
                        "exibirInserirRegistroAtendimentoDadosGeraisAction",
                        "inserirRegistroAtendimentoDadosGeraisAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        2, "LocalOcorrenciaIntervaloAbaA.gif", "LocalOcorrenciaIntervaloAbaD.gif",
                        "exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction",
                        "inserirRegistroAtendimentoDadosLocalOcorrenciaAction"));
        statusWizard
        		.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
        				3, "SolicitanteUltimaAbaA.gif", "SolicitanteUltimaAbaD.gif",
        				"exibirInserirRegistroAtendimentoDadosSolicitanteAction",
                		"inserirRegistroAtendimentoDadosSolicitanteAction"));
        
        statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						4, "Anexos02.gif", "Anexos.gif",
						"exibirInserirRegistroAtendimentoAnexosAction",
        				"inserirRegistroAtendimentoAnexosAction"));
      
        
        sessao.setAttribute("statusWizard", statusWizard);
        
        //OBTENDO PROTOCOLO DE ATENDIMENTO 
		if (sessao.getAttribute("protocoloAtendimento") == null){
			
			String protocoloAtendimento = this.getFachada().obterProtocoloAtendimento();
			sessao.setAttribute("protocoloAtendimento", protocoloAtendimento);
		}
        
		
		
		
        return retorno;
    }

}

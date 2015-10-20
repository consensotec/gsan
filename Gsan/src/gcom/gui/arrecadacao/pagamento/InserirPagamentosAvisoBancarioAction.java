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
import gcom.gui.StatusWizard;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

/**
 * Action que finaliza a primeira p�gina do processo de inserir pagamentos
 * 
 * @author 	Pedro Alexandre 
 * @created 16/02/2006
 */
public class InserirPagamentosAvisoBancarioAction extends GcomAction {

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

    	//cria a vari�vel que vai armazenar o retorno
    	ActionForward retorno = null;
    	
    	//cria uma inst�ncia da sess�o
        HttpSession sessao = httpServletRequest.getSession(false);

        //recupera o form 
        DynaValidatorActionForm pagamentoActionForm = (DynaValidatorActionForm) actionForm;

        //cria uma inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();

        //recupera os dados informados na p�gina de aviso banc�rio
        String tipoInclusao = (String) pagamentoActionForm.get("tipoInclusao");
        String dataPagamento = (String) pagamentoActionForm.get("dataPagamento");
        String idAvisoBancario = (String) pagamentoActionForm.get("idAvisoBancario");  
        String idFormaArrecadacao =(String) pagamentoActionForm.get("idFormaArrecadacao");

        //se o tipo de inclus�o n�o for informado 
        if(tipoInclusao == null || tipoInclusao.trim().equalsIgnoreCase("")){
        	//levanta a exce��o para a pr�xima camada
        	throw new ActionServletException("atencao.naoinformado",null, "Tipo de Inclus�o");
        }
        
        //se a data de pagamento n�o for informada 
        if(dataPagamento == null || dataPagamento.trim().equalsIgnoreCase("")){
        	//levanta a exce��o para a pr�xima camada
        	throw new ActionServletException("atencao.naoinformado",null, "Data de Pagamento");
        }
        
        //se o aviso banc�rio n�o for informado
        if(idAvisoBancario == null || idAvisoBancario.trim().equalsIgnoreCase("")){
        	//levanta a exce��o para a pr�xima camada
        	throw new ActionServletException("atencao.naoinformado",null, "Aviso Banc�rio");
        } else {

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
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Aviso Banc�rio");
			}

		}
        
        // se a forma de arrecada��o n�o for informada
        if(idFormaArrecadacao == null || idFormaArrecadacao.trim().equalsIgnoreCase("")){
        	//levanta a exce��o para a pr�xima camada
        	throw new ActionServletException("atencao.naoinformado",null, "Forma de Arrecada��o");
        }
//        else if (tipoInclusao.equals("ficha") && 
//        		!idFormaArrecadacao.equals(ArrecadacaoForma.FICHA_COMPENSACAO.toString())){
//        	//levanta a exce��o para a pr�xima camada
//        	throw new ActionServletException("atencao.arrecadacao_forma_invalida");
//        }
        
        //cria o formato da data
        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

        try {
        	//tenta converter a data de apagamento
            dataFormato.parse(dataPagamento);

            //se n�o conseguir converter
        } catch (ParseException ex) {
        	//levanta a exce��o para a pr�xima camada
        	throw new ActionServletException("atencao.data_pagamento_invalida");
        }
        
        //recupera o status atual do wizard
        StatusWizard statusWizard = (StatusWizard) sessao.getAttribute("statusWizard");
                
        //cria o filtro de forma de arrecada��o
        FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
        
        //seta o c�digo da forma de arrecada��o no filtro
        filtroArrecadacaoForma.adicionarParametro(new ParametroSimples(FiltroArrecadacaoForma.CODIGO, idFormaArrecadacao));
        
        //pesquisa a forma de arrecada��o no sistema
        Collection colecaoFormaArrecadacao = fachada.pesquisar(filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
        
        //se n�o existir a forma de arrecada��o no sistema
        if(colecaoFormaArrecadacao == null || colecaoFormaArrecadacao.isEmpty()){
        	//levanta a exce��o para a pr�xima camada
        	throw new ActionServletException("atencao.naocadastrado",null, "Forma de Arrecada��o");
        	
        }else{
        	//recupera a forma de arrecada��o da cole��o
        	ArrecadacaoForma formaArrecadacao =(ArrecadacaoForma) Util.retonarObjetoDeColecao(colecaoFormaArrecadacao);
        	
        	//setaa descri��o da forma de arrecada��o no form
        	pagamentoActionForm.set("descricaoFormaArrecadacao", formaArrecadacao.getDescricao());
        }
        	
        //se o tipo de inclus�o for "manual"
        if (tipoInclusao != null && tipoInclusao.equalsIgnoreCase("manual")) {

            //seta o status do wizard para a p�gina de inclus�o manual
        	statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                    2, "TipoInclusaoA.gif", "TipoInclusaoD.gif",
                    "exibirInserirPagamentosTipoInclusaoManualAction",
                    "inserirPagamentosTipoInclusaoManualAction"));
        	
        	retorno = actionMapping.findForward("inserirPagamentosTipoInclusaoManual");

            //Limpa os c�digos de barras do form da p�gina de inserir pagamento por caneta �ptica
            pagamentoActionForm.set("codigoBarraDigitadoCampo1","");
        	pagamentoActionForm.set("codigoBarraDigitadoDigitoVerificadorCampo1","");
        	pagamentoActionForm.set("codigoBarraDigitadoCampo2","");
        	pagamentoActionForm.set("codigoBarraDigitadoDigitoVerificadorCampo2","");
        	pagamentoActionForm.set("codigoBarraDigitadoCampo3","");
        	pagamentoActionForm.set("codigoBarraDigitadoDigitoVerificadorCampo3","");
        	pagamentoActionForm.set("codigoBarraDigitadoCampo4","");
        	pagamentoActionForm.set("codigoBarraDigitadoDigitoVerificadorCampo4","");
        	pagamentoActionForm.set("codigoBarraPorLeituraOtica","");  
        	//pagamentoActionForm.set("tipoLeitura","optica");
        	pagamentoActionForm.set("tipoLeitura","");
        	
          //se o tipo de inclus�o for "caneta"  
        } else if(tipoInclusao != null && tipoInclusao.equalsIgnoreCase("caneta")){
            
        	//seta o status do wizard para a p�gina de inclus�o por caneta
        	statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                    2, "TipoInclusaoA.gif", "TipoInclusaoD.gif",
                    "exibirInserirPagamentosTipoInclusaoCanetaAction",
                    "inserirPagamentosTipoInclusaoCanetaAction"));
        	
        	retorno = actionMapping.findForward("inserirPagamentosTipoInclusaoCaneta");
        	
        	//Limpa os dados do form da p�gina de inserir pagamento manual
        	pagamentoActionForm.set("valorPagamento","");
        	pagamentoActionForm.set("idTipoDocumento","");
        	pagamentoActionForm.set("idImovel","");
        	pagamentoActionForm.set("descricaoImovel","");
        	pagamentoActionForm.set("idGuiaPagamento","");
        	pagamentoActionForm.set("descricaoGuiaPagamento","");
        	pagamentoActionForm.set("valorGuiaPagamento","");
        	pagamentoActionForm.set("idDebitoACobrar","");
        	pagamentoActionForm.set("descricaoDebitoACobrar","");
        	pagamentoActionForm.set("valorDebitoACobrar","");
        	pagamentoActionForm.set("idLocalidade","");
        	pagamentoActionForm.set("descricaoLocalidade","");
        	pagamentoActionForm.set("referenciaConta","");
        	pagamentoActionForm.set("descricaoReferenciaConta","");
        	pagamentoActionForm.set("idCliente","");
        	pagamentoActionForm.set("nomeCliente","");
        	pagamentoActionForm.set("idTipoDebito","");
        	pagamentoActionForm.set("descricaoTipoDebito","");
        }
        else if(tipoInclusao != null && tipoInclusao.equalsIgnoreCase("ficha")){
            
        	//seta o status do wizard para a p�gina de inclus�o por caneta
        	statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                    2, "TipoInclusaoA.gif", "TipoInclusaoD.gif",
                    "exibirInserirPagamentosTipoInclusaoFichaCompensacaoAction",
                    "inserirPagamentosTipoInclusaoFichaCompensacaoAction"));
        	
        	retorno = actionMapping.findForward("inserirPagamentosTipoInclusaoFichaCompensacao");
        }

 
    	
        //seta o form na sess�o
        sessao.setAttribute("PagamentoActionForm",pagamentoActionForm);
        
        //seta o status do wizard na sess�o
        sessao.setAttribute("statusWizard",statusWizard);
        
        //retorna o mapeamento contido na vari�vel retorno
        return retorno;
    }
}

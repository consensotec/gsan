/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.financeiro;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Gerar integração para contabilidade.
 *
 * @author Pedro Alexandre
 * @date 28/05/2007
 */
public class GerarIntegracaoContabilidadeAction extends GcomAction {
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

		// Seta o mapeamento de retorno para a tela de sucesso.
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		GerarIntegracaoContabilidadeActionForm gerarIntegracaoContabilidadeActionForm = (GerarIntegracaoContabilidadeActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		//recupera os parâmetros informados pelo usuário.
		String idLancamentoOrigem = gerarIntegracaoContabilidadeActionForm.getIdLacamentoOrigem();
		String dataLancamento = gerarIntegracaoContabilidadeActionForm.getDataLancamento();
		String dataLancamentoInicial = gerarIntegracaoContabilidadeActionForm.getDataLancamentoInicial();
		String dataLancamentoFinal = gerarIntegracaoContabilidadeActionForm.getDataLancamentoFinal();			
		String numeroUtilmoSequencial = gerarIntegracaoContabilidadeActionForm.getNumeroUltimoSequencial();
		
		//verifica se a origem do lançamento foi informada.
		if(idLancamentoOrigem == null || idLancamentoOrigem.trim().equals("")){
			throw new ActionServletException("atencao.naoinformado",null, "Lançamento Origem");
		}	
        
        Boolean origemReceita = (Boolean) sessao.getAttribute("origemReceita");
        
        if(origemReceita != null && origemReceita){
        	
        	if(dataLancamentoInicial == null || dataLancamentoInicial.equals("")){
        		throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Data Lançamento Inicial");
        	}
        	
        	if(dataLancamentoFinal == null || dataLancamentoFinal.equals("")){
        		throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Data Lançamento Final");
        	}
        	
        	if(!Util.validarDataInvalida(dataLancamentoInicial, "dd/MM/yyyy")){
    			throw new ActionServletException("atencao.data_invalida", null, "Data Lançamento Inicial");
    		}
    		
    		if(!Util.validarDataInvalida(dataLancamentoFinal, "dd/MM/yyyy")){
    			throw new ActionServletException("atencao.data_invalida", null, "Data Lançamento Final");
    		}	
    		
    		/*
    		 * Caso a data não tenha sido informada levanta a exceção para o usuário.
    		 */
    		
    		//[FS0002 - Validar data do lançamento]
    		//cria o formato da data        
            SimpleDateFormat dataFormatoInicial = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat dataFormatoFinal = new SimpleDateFormat("dd/MM/yyyy");

            try {
            	//tenta converter a data de lançamento            
                dataFormatoInicial.parse(dataLancamentoInicial);
                dataFormatoFinal.parse(dataLancamentoFinal);

                //se não conseguir converter
            } catch (ParseException ex) {
            	//levanta a exceção para a próxima camada
            	throw new ActionServletException("atencao.data_pagamento_invalida");
            }    		
            
            String mesInicial = dataLancamentoInicial.substring(3,5);
            String anoInicial = dataLancamentoInicial.substring(6,10);
            String anoMesInicial = anoInicial + mesInicial;
            
            String mesFinal = dataLancamentoFinal.substring(3,5);
            String anoFinal = dataLancamentoFinal.substring(6,10);
            String anoMesFinal = anoFinal + mesFinal;
        	
        	fachada.gerarIntegracaoContabilidade(idLancamentoOrigem, anoMesInicial, anoMesFinal, dataLancamentoInicial, dataLancamentoFinal, numeroUtilmoSequencial);
        }else{
        	
        	if(dataLancamento == null || dataLancamento.trim().equals("")){
    			throw new ActionServletException("atencao.naoinformado",null, "Data de Lançamento");
    		}else{
    			SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
    			
    			 try {
    		        	//tenta converter a data de lançamento
    		            dataFormato.parse(dataLancamento);
    			 } catch (ParseException ex) {
    		        	//levanta a exceção para a próxima camada
    		        	throw new ActionServletException("atencao.data_pagamento_invalida");
    		        }
    			 
    			//recupera o mês e o ano informados
    			String mes = dataLancamento.substring(3, 5);
		        String ano = dataLancamento.substring(6, 10);
		        String anoMes = ano + mes;
		        
		        //chama o metódo para gerar o txt de integração para contabilidade 
	            fachada.gerarIntegracaoContabilidade(idLancamentoOrigem, anoMes, dataLancamento);
    		}      	
          
        }            		
	
		// montando página de sucesso
		montarPaginaSucesso(httpServletRequest, "Gerado a Integração para a Contabilidade Com Sucesso !",
				"Gerar Integração para a Contabilidade", "/exibirGerarIntegracaoContabilidadeAction.do");

		return retorno;
	}
}

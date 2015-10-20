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
package gcom.gui.financeiro;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Gerar integra��o para contabilidade.
 *
 * @author Fl�vio Leonardo
 * @date 13/06/2007
 */
public class GerarIntegracaoContabilidadeCaernAction extends GcomAction {
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

		//recupera os par�metros informados pelo usu�rio.
		String idLancamentoOrigem = gerarIntegracaoContabilidadeActionForm.getIdLacamentoOrigem();
		String dataLancamento = gerarIntegracaoContabilidadeActionForm.getDataLancamento();
		
		//verifica se a origem do lan�amento foi informada.
		if(idLancamentoOrigem == null || idLancamentoOrigem.trim().equals("")){
			throw new ActionServletException("atencao.naoinformado",null, "Lan�amento Origem");
		}
		
		/*
		 * Caso a data n�o tenha sido informada levanta a exce��o para o usu�rio.
		 */
		if(dataLancamento == null || dataLancamento.trim().equals("")){
			throw new ActionServletException("atencao.naoinformado",null, "Data de Lan�amento");
		}else{
			//[FS0002 - Validar data do lan�amento]
			//cria o formato da data
	        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

	        try {
	        	//tenta converter a data de lan�amento
	            dataFormato.parse(dataLancamento);

	            //se n�o conseguir converter
	        } catch (ParseException ex) {
	        	//levanta a exce��o para a pr�xima camada
	        	throw new ActionServletException("atencao.data_pagamento_invalida");
	        }
		}
		
		//recupera o m�s e o ano informados
        String mes = dataLancamento.substring(3, 5);
        String ano = dataLancamento.substring(6, 10);
        String anoMes = ano + mes;
        
        //chama o met�do para gerar o txt de integra��o para contabilidade 
        fachada.gerarIntegracaoContabilidade(idLancamentoOrigem, anoMes, dataLancamento);
		
	
		// montando p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Gerando a Integra��o para a Contabilidade.",
				"Gerar Integra��o para a Contabilidade", "/exibirGerarIntegracaoContabilidadeAction.do");

		return retorno;
	}
}

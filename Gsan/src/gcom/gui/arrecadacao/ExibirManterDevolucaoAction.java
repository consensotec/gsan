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

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.FiltroDevolucao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Fernanda Karla
 * @created 07 de Mar�o de 2006
 */
public class ExibirManterDevolucaoAction extends GcomAction {
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

		ActionForward retorno = actionMapping
				.findForward("exibirManterDevolucao");

		// Mudar isso quando implementar a parte de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoDevolucoes = null;

		if (sessao.getAttribute("colecaoImoveisDevolucoes") != null) {
			colecaoDevolucoes = (Collection) sessao
					.getAttribute("colecaoImoveisDevolucoes");
		} else if (sessao.getAttribute("colecaoClientesDevolucoes") != null) {
			colecaoDevolucoes = (Collection) sessao
					.getAttribute("colecaoClientesDevolucoes");
		} else if (sessao.getAttribute("colecaoAvisosBancariosDevolucoes") != null) {
			colecaoDevolucoes = (Collection) sessao
					.getAttribute("colecaoAvisosBancariosDevolucoes");
		}

		sessao.setAttribute("colecaoDevolucoes",colecaoDevolucoes);
		
		Collection devolucoes = null;
		//Parte da verifica��o do filtro
        FiltroDevolucao filtroDevolucao = new FiltroDevolucao();
        
		// Verifica se o filtro foi informado pela p�gina de filtragem de devolucao
        if(httpServletRequest.getAttribute("filtroDevolucao") != null){
            filtroDevolucao = (FiltroDevolucao) httpServletRequest
                    .getAttribute("filtroDevolucao");
            
        }else if(sessao.getAttribute("filtroDevolucao")!= null){
        	filtroDevolucao = (FiltroDevolucao) sessao
            		.getAttribute("filtroDevolucao");
    	}else{
            //Se o limite de registros foi atingido, a p�gina de filtragem � chamada
            retorno = actionMapping.findForward("exibirManterDevolucao");
        }
		
        if(retorno.getName().equalsIgnoreCase("exibirManterDevolucao")){
            //Seta a ordena��o desejada do filtro
            filtroDevolucao.setCampoOrderBy(FiltroDevolucao.DATA_DEVOLUCAO);
            
            //Informa ao filtro para ele buscar objetos associados a Devolucao
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.documentoTipo");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAtual");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAnterior");
            filtroDevolucao
            	.adicionarCaminhoParaCarregamentoEntidade("imovel");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.guiaPagamento.debitoTipo");
            filtroDevolucao
            	.adicionarCaminhoParaCarregamentoEntidade("cliente");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel");

            // Aciona o controle de pagina��o para que sejam pesquisados apenas
			// os registros que aparecem na p�gina
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroDevolucao, Devolucao.class.getName());
			devolucoes = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			if (devolucoes == null || devolucoes.isEmpty()) {
				//Nenhuma Devolucao cadastrada
                throw new ActionServletException("atencao.pesquisa.nenhumresultado");
            }
            
            sessao.setAttribute("colecaoDevolucoes", devolucoes);
            
            sessao.setAttribute("filtroDevolucao", filtroDevolucao);
            sessao.setAttribute("telaManter","telaManter");
        }
		sessao.removeAttribute("colecaoImoveisDevolucoes");
		sessao.removeAttribute("colecaoClientesDevolucoes");
		sessao.removeAttribute("colecaoAvisosBancariosDevolucoes");
		
		sessao.setAttribute("tela","manterDevolucao");
		
		return retorno;
	}
}
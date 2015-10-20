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
package gcom.gui.faturamento.conta;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pr�-processamento da p�gina de pesquisar contas do im�vel 
 *
 * @author Pedro Alexandre
 * @date 02/03/2006
 */
public class ExibirPesquisarContaAction extends GcomAction {
	
	/**
	 * Pesquisa as contas existentes para o im�vel 
	 *
	 * [UC0248] Pesquisar Contas do Im�vel
	 *
	 * <Breve descri��o sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descri��o sobre o fluxo secund�rio>
	 *
	 * <Identificador e nome do fluxo secund�rio> 
	 *
	 * @author Pedro Alexandre
	 * @date 02/03/2006
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								 ActionForm actionForm, 
								 HttpServletRequest httpServletRequest,
								 HttpServletResponse httpServletResponse) {

		//Seta o mapeamento de retorno para a tela de pesquisar contas do im�vel
		ActionForward retorno = actionMapping.findForward("pesquisarConta");
		
		//Cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o c�digo do im�vel do request
		String idImovel = httpServletRequest.getParameter("idImovel");
		
		//PesquisarContaActionForm pesquisarContaActionForm = (PesquisarContaActionForm) actionForm;
		
				
		//Caso o c�digo do im�vel tenha sido informado pelo caso de uso que chamou a tela de pesquisar contas,
		//Caso contr�rio levanta a exce��o para o usu�rio indicando que o im�vel n�o foi informado
		if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
			
			//Pesquisa se o im�vel informado esta cadastrado no sistema,
			//Caso contr�rio, levanta a exce��o para o usu�rio indicando que o im�vel n�o existe
			FiltroImovel filtroImovel = new FiltroImovel();
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
        	Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());

        	//Caso o im�vel informado n�o tenha sido cadastrado no sistema
        	if (colecaoImovel == null || colecaoImovel.isEmpty()){
        		throw new ActionServletException("atencao.naocadastrado", null, "Im�vel");
        	}
		}else{
			throw new ActionServletException("atencao.naoinformado",null, "Im�vel");
		}
	
		//Cria o filtro para pesquisar as situa��es de conta no sistema
		FiltroDebitoCreditoSituacao filtroDebitoCreditoSituacao = new FiltroDebitoCreditoSituacao();
		filtroDebitoCreditoSituacao.setCampoOrderBy(FiltroDebitoCreditoSituacao.DESCRICAO);
		
		
		Collection colecaoSituacaoConta = null;
		if (httpServletRequest.getParameter("situacaoConta") != null){
			// so mostras as situa��es : normal, retificada e incluida
			// utilizado a partir do inserir Pagamento (Manual)
			sessao.setAttribute("situacaoConta",httpServletRequest.getParameter("situacaoConta"));	
			
		}else{
			//Pesquisa todas as situa��es de conta cadastradas no sistema
			colecaoSituacaoConta = fachada.pesquisar(filtroDebitoCreditoSituacao, DebitoCreditoSituacao.class.getName());
			sessao.removeAttribute("situacaoConta");
		}
		
	
		//[FS0005] Caso n�o exista nenhuma situa��o de conta no sistema, levanta a exce��o para o usu�rio indicando que 
		//nenhuma situa��o de conta est� cadastrada no sistema.
		//Caso contr�rio, manda as situa��es de contas cadastradas no request e o c�digo do im�vel
        if((colecaoSituacaoConta == null || colecaoSituacaoConta.isEmpty())&& httpServletRequest.getParameter("situacaoConta") == null ){
        	throw new ActionServletException("atencao.naocadastrado",null, "Situa��o de Conta");
        }else{
        	httpServletRequest.setAttribute("colecaoSituacaoConta",colecaoSituacaoConta);
        	httpServletRequest.setAttribute("idImovel",idImovel);
        }
        
        
        if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaConta") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaConta",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaConta"));
		}
        
        
        //Retorna o mapeamento contido na vari�vel retorno
		return retorno;
	}
}

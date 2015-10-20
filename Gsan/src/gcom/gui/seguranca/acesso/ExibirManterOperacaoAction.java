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
package gcom.gui.seguranca.acesso;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Operacao;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action respons�vel pela exibi��o da p�gina de manter opera��o 
 *
 * @author Pedro Alexandre
 * @date 01/08/2006
 */
public class ExibirManterOperacaoAction extends GcomAction {

	
	/**
	 * [UC0281] - Manter Opera��o 
	 *
	 * @author Pedro Alexandre
	 * @date 05/08/2006
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

		//Seta o mapeamento de retorno para a tela de manter opera��o
        ActionForward retorno = actionMapping.findForward("exibirManterOperacao");
        
        //Cria uma inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		sessao.setAttribute("AtualizarOperacaoActionForm",null);
		
		//Cria a vari�vel que vai armazenar a cole��o de opera��es filtradas
        Collection colecaoOperacao = null;

        //Recupera o filtro da opera��o caso tenha na sess�o
        FiltroOperacao filtroOperacao = null;
		if (sessao.getAttribute("filtroOperacao") != null) {
			filtroOperacao = (FiltroOperacao) sessao.getAttribute("filtroOperacao");
		}
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("operacaoTipo");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("idOperacaoPesquisa");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("tabelaColuna");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.ARGUMENTO_PESQUISA);
		
		/*
		 * Pesquisa a cole��o de opera��es para o esquema de pagina��o
		 * e recupera o mapeamnento de retorno 
		 */
		Map resultado = controlarPaginacao(httpServletRequest, retorno,	filtroOperacao, Operacao.class.getName());
		colecaoOperacao = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
			
		/*
		 * Caso a cole��o de pesquisa esteja vazia 
		 * levanta a exce��o para o usu�rio indicando que a pesquisa 
		 * n�o retornou nenhum registro
		 */
		if (colecaoOperacao == null || colecaoOperacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		//Recupera a flag que indica que se o usu�rio quer ir direto para a tela do atualizar
		String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
		/*
		 * Caso a cloe��o de pesquisa tenha um �nico registro e a a
		 * flag do atualizar esteja marcada, recupera a opera��o 
		 * da cole��o e seta o c�digo da opera��o para ser atualizada 
		 * na sess�o. 
		 * Caso contr�rio manda a cole��o de opera��es para a 
		 * p�gina do manter.
		 */
		if (colecaoOperacao.size()== 1 && identificadorAtualizar != null){
			retorno = actionMapping.findForward("atualizarOperacao");
			Operacao operacao = (Operacao)colecaoOperacao.iterator().next();
			sessao.setAttribute("idRegistroAtualizar", new Integer (operacao.getId()).toString());
		}else{
			sessao.setAttribute("colecaoOperacao", colecaoOperacao);
		}

		//Seta o tipo da pesquisa na sess�o
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		//Retorna o mapeamento contido na vari�vel retorno 
        return retorno;
    }
}

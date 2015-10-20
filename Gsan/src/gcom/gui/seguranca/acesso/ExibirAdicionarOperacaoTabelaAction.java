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

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.seguranca.transacao.Tabela;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action respons�vel por exibir o popup de adicionar tabela no inserir opera��o e atualizar opera��o
 *
 * @author Pedro Alexandre
 * @date 05/05/2006
 */
public class ExibirAdicionarOperacaoTabelaAction extends GcomAction {

	/**
	 * Inseri uma opera��o de uma funcionalida de no sistema
	 *
	 * [UC0284] Inserir Opera��o
	 *
	 * @author Pedro Alexandre
	 * @date 05/05/2006
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
		
		//Seta o mapeamento para a o popup de adicionar tabela
		ActionForward retorno = actionMapping.findForward("exibirAdicionarOperacaoTabela");
		
		//Recupera o form de adicionar tabela 
		AdicionarOperacaoTabelaActionForm adicionarOperacaoTabelaActionForm = (AdicionarOperacaoTabelaActionForm) actionForm;
		
		//Cria uma inst�ncia da fachada 
		Fachada fachada = Fachada.getInstancia();
		
		//Cria uma inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o c�digoda tabela se ele foi digitado
		String idTabela = adicionarOperacaoTabelaActionForm.getIdTabela();
		
		//Recupera para onde a tela de popup deve retornar
		//e seta o valor na sess�o
		String retornarTela = httpServletRequest.getParameter("retornarTela");
		if(retornarTela != null){
			sessao.setAttribute("retornarTela",retornarTela);
		}
		
		//Recupera a flag que indica se � para limpar o form de adicionar tabela
		String limpaForm = httpServletRequest.getParameter("limpaForm");
		
		if (idTabela != null
				&& !idTabela.trim().equalsIgnoreCase("")
				&& httpServletRequest.getParameter("exibirPesquisarTabela") == null
				&& httpServletRequest.getParameter("limparForm") == null) {

			sessao.setAttribute("tabelaRecebida", idTabela);
		}
		
		//Caso a flag de limpar o form seja true 
		//Limpaos dados dos campos da tabela
		if(limpaForm != null && limpaForm.equalsIgnoreCase("true")){
			adicionarOperacaoTabelaActionForm.setIdTabela("");
			adicionarOperacaoTabelaActionForm.setDescricaoTabela("");
		}else{
			//Caso a flag de limpar o form n�o for true
			//e caso o c�digo da tabela tenha sido digitado 
			//pesquisa a tabela informada na base de dados
			if (idTabela != null && !idTabela.equals("")){
				
				//Cria o filtro e setao c�digo da tabela informado no filtro
				FiltroTabela filtroTabela = new FiltroTabela();
				filtroTabela.adicionarParametro(new ParametroSimples(FiltroTabela.ID,idTabela));
				
				//Pesquisa a tabela na base de dados
				Collection<Tabela> colecaoTabela = fachada.pesquisar(filtroTabela, Tabela.class.getName());
				
				//Caso a tabela tenha sido encontrada 
				//Recupera a tabela e seta as informa��es no form de adicionar
				//Caso contr�rio indica que a tabela n�o existe 
				if (colecaoTabela != null && !colecaoTabela.isEmpty()) {
					Tabela tabela = (Tabela) Util.retonarObjetoDeColecao(colecaoTabela);
					adicionarOperacaoTabelaActionForm.setIdTabela(String.valueOf(tabela.getId()));
					adicionarOperacaoTabelaActionForm.setDescricaoTabela(tabela.getDescricao());
					httpServletRequest.setAttribute("operacaoTabelaEncontrada", "true");
	
				} else {
					adicionarOperacaoTabelaActionForm.setIdTabela("");
					adicionarOperacaoTabelaActionForm.setDescricaoTabela("TABELA INEXISTENTE");
					httpServletRequest.setAttribute("operacaoTabelaNaoEncontrada","exception");
				}
			}
		}
		
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {

			String id = httpServletRequest.getParameter("idCampoEnviarDados");
			String descricao = httpServletRequest.getParameter("descricaoCampoEnviarDados");
			
			adicionarOperacaoTabelaActionForm.setIdTabela(id);
			adicionarOperacaoTabelaActionForm.setDescricaoTabela(descricao);

		}
		
		//Seta a flag para indicar que o popupvai ser fechado
		httpServletRequest.setAttribute("fechaPopup", "false");

		//Retorna o mapeamento contido na vari�vel retorno 
		return retorno;

	}

	
}

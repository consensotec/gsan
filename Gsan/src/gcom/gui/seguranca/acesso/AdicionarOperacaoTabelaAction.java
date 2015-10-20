
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

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.seguranca.transacao.Tabela;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;


/**
 * Descri��o da classe 
 *
 * @author Pedro Alexandre
 * @date 05/05/2006
 */
public class AdicionarOperacaoTabelaAction extends GcomAction{
	
	
	public ActionForward execute(ActionMapping actionMapping,
								ActionForm actionForm, 
								HttpServletRequest httpServletRequest,
								HttpServletResponse httpServletResponse) {

		//Seta o mapeamento para a p�gina de adicionar tabela 
		ActionForward retorno = actionMapping.findForward("adicionarOperacaoTabela");
	
		//Recupera o form de adicionar tabela 
		AdicionarOperacaoTabelaActionForm adicionarOperacaoTabelaActionForm = (AdicionarOperacaoTabelaActionForm) actionForm;
		
		//Cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Cria uma inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o c�digo da tabela 
		String idTabela = adicionarOperacaoTabelaActionForm.getIdTabela();
		
		//Cria a vari�vel que vai armazenar a tabela 
		Tabela tabela = null;
		
		//Cria o filtro para pesquisar a tabela e seta o c�digo da tabela informada no filtro
		FiltroTabela filtroTabela = new FiltroTabela();
		filtroTabela.adicionarParametro(new ParametroSimples(FiltroTabela.ID, idTabela));
		
		//Pesquisa a tabela de acordo com o c�digo informado
		Collection colecaoTabela = fachada.pesquisar(filtroTabela,Tabela.class.getName());
		
		//Caso a tabela n�o esteja cadastrada levanta uma exce��o para o usu�rio
		//caso contr�rio recupera a tabela pesquisada 
		if(colecaoTabela==null || colecaoTabela.isEmpty()){
			throw new ActionServletException("atencao.tabela.inexistente");
		}
		tabela = (Tabela) Util.retonarObjetoDeColecao(colecaoTabela);
		
		//Cria a vari�vel que vai armazenar as tabelas adicionadas
 		Collection<Tabela> colecaoOperacaoTabela = null;
		
 		//Caso j� exista a cole��o na sess�o recupera a cole��o
 		//caso contr�rio cria uma inst�ncia nova  
		if (sessao.getAttribute("colecaoOperacaoTabela") != null) {
			colecaoOperacaoTabela = (Collection<Tabela>) sessao.getAttribute("colecaoOperacaoTabela");
        } else {
        	colecaoOperacaoTabela = new ArrayList();
        }
		
		//Caso a cole��o n�o contenha ainda a tabela informada
		//adiciona a tabela a cole��o 
		//caso contr�rio levanta uma exce��o para o usu�rio
		if(!colecaoOperacaoTabela.contains(tabela)){
			colecaoOperacaoTabela.add(tabela);
		}else{
			throw new ActionServletException("atencao.tabela.ja.informada");
		}
		
		//Seta a cole��o de tabelas na sess�o 
		sessao.setAttribute("colecaoOperacaoTabela",colecaoOperacaoTabela);
		
		//Seta a flag que indica para fechar o popup
		httpServletRequest.setAttribute("fechaPopup", "true");
		
		//Retorna o mapeamento contido na vari�vel retorno 
		return retorno;
	}
}

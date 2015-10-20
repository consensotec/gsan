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
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaColuna;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel por inserir uma opera��o de uma funcionalidade espec�fica no sistema
 * o action inseri tamb�m o relacionamento entre a opera��o e as tabelas 
 * 
 * @author Pedro Alexandre
 * @date 05/05/2006
 */
public class InserirOperacaoAction extends GcomAction {

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

		//Cria a vari�vel de retorno, seta para a p�gina de sucesso 
		ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);
        
		//Recupera o form de inserir opera��o
		InserirOperacaoActionForm inserirOperacaoActionForm = (InserirOperacaoActionForm) actionForm;
		
		//Recupera as informa��es do form 
		String descricao = inserirOperacaoActionForm.getDescricao();
		String descricaoAbreviada = inserirOperacaoActionForm.getDescricaoAbreviada();
		String caminhoURL = inserirOperacaoActionForm.getCaminhoUrl();
		String idFuncionalidade = inserirOperacaoActionForm.getIdFuncionalidade();
		Funcionalidade funcionalidade = new Funcionalidade();
		funcionalidade.setId(new Integer(idFuncionalidade));
        
		String idTipoOperacao = inserirOperacaoActionForm.getIdTipoOperacao();
		OperacaoTipo operacaoTipo = new OperacaoTipo();
		operacaoTipo.setId(new Integer(idTipoOperacao));
		
		//Recupera o argumento de pesquisa 
		//Caso tenha sido informado cria o objeto
		String idArgumentoPesquisa = inserirOperacaoActionForm.getIdArgumentoPesquisa();
		TabelaColuna argumentoPesquisa = null;
		if(idArgumentoPesquisa != null && !idArgumentoPesquisa.equalsIgnoreCase("")){
			argumentoPesquisa = new TabelaColuna();
			argumentoPesquisa.setId(new Integer(idArgumentoPesquisa));
		}
		
		//Recupera o c�digo da opera��o de pesquisa 
		//Caso tenha sido informado cria o objeto
		String idOperacaoPesquisa = inserirOperacaoActionForm.getIdOperacaoPesquisa();
		Operacao operacaoPesquisa = null;
		if(idOperacaoPesquisa != null && !idOperacaoPesquisa.equalsIgnoreCase("")){
			operacaoPesquisa = new Operacao();
			operacaoPesquisa.setId(new Integer(idOperacaoPesquisa));
		}
		
		//Cria uma inst�ncia da fachada 
		Fachada fachada = Fachada.getInstancia();

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//Recupera a  cole��o de tabelas da sess�o  
		Collection<Tabela> colecaoTabela = (Collection<Tabela>)sessao.getAttribute("colecaoOperacaoTabela");

		//Cria o objeto opera��o que vai ser inserido
		Operacao operacao = new Operacao();
		operacao.setDescricao(descricao);
		operacao.setDescricaoAbreviada(descricaoAbreviada);
		operacao.setCaminhoUrl(caminhoURL);
		operacao.setFuncionalidade(funcionalidade);
		operacao.setOperacaoTipo(operacaoTipo);
		operacao.setIdOperacaoPesquisa(operacaoPesquisa);
		operacao.setTabelaColuna(argumentoPesquisa);
		operacao.setUltimaAlteracao(new Date());
		
		//Chamao met�do de inserir opera��o da fachada
		fachada.inserirOperacao(operacao, colecaoTabela, usuarioLogado);
		
		//Limpa a sess�o depois da inser��o 
		sessao.removeAttribute("habilitarArgumentoPesquisa");
		sessao.removeAttribute("habilitarOperacaoPesquisa");
		sessao.removeAttribute("colecaoOperacaoTabela");
		sessao.removeAttribute("InserirOperacaoActionForm");
		sessao.removeAttribute("retornarTela");
		
		//Monta a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Opera��o inserida com sucesso.",
				"Inserir outra opera��o",
				"exibirInserirOperacaoAction.do?menu=sim");


		//Retorna o mapeamento contido na vari�vel retorno 
		return retorno;
	}
}
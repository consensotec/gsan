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

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoTabela;
import gcom.seguranca.acesso.OperacaoTabelaPK;
import gcom.seguranca.acesso.OperacaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaColuna;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel por atualizar uma opera��o no sistema 
 * assim como seus relacionamentos
 *
 * @author Pedro Alexandre
 * @date 07/08/2006
 */
public class AtualizarOperacaoAction extends GcomAction {

	/**
	 * [UC0281] Manter Opera��o
	 *
	 * @author Pedro Alexandre
	 * @date 07/08/2006
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

		//Seta o mapeamento de retorno para a tela de sucesso
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		//Cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		//Cria uma inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera o usuario que est� logado na aplica��o
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//Recupera o form de atualizar opra��o
		AtualizarOperacaoActionForm atualizarOperacaoActionForm = (AtualizarOperacaoActionForm) actionForm;

		//Recupera os dados informados na p�gina de atualizar opera��o
		String idTipoOperacao = atualizarOperacaoActionForm.getIdTipoOperacao();
		String idFuncionalidade = atualizarOperacaoActionForm.getIdFuncionalidade();
		String idArgumentoPesquisa = atualizarOperacaoActionForm.getIdArgumentoPesquisa();
		String idOperacaoPesquisa = atualizarOperacaoActionForm.getIdOperacaoPesquisa();
		
		//Cria as vari�veis para armazear o tipo da opera��o a funcionalidade o
		//argumento de pesquisa e a opera��o de pesquisa
		OperacaoTipo operacaoTipo = null;
		Funcionalidade funcionalidade = null;
		TabelaColuna argumentoPesquisa = null;
		Operacao operacaoPesquisa = null;
		
		/*
		 * Caso o usu�rio tenha informado o tipo da opera��o
		 * seta o id do tipo da opera��o
		 */
		if (idTipoOperacao != null && !idTipoOperacao.trim().equals("")) {
			operacaoTipo = new OperacaoTipo();
			operacaoTipo.setId(new Integer(idTipoOperacao));
		}

		/*
		 * Caso o usu�rio tenha informado a funcionalidade da opera��o
		 * seta o id da funcionalidade
		 */
		if (idFuncionalidade != null && !idFuncionalidade.trim().equals("")) {
			funcionalidade = new Funcionalidade();
			funcionalidade.setId(new Integer(idFuncionalidade));
		}

		/*
		 * Caso o usu�rio tenha informado o argumento de pesquisa
		 * seta o id do argumento de pesquisa
		 */
		if (idArgumentoPesquisa != null && !idArgumentoPesquisa.trim().equals("")) {
			argumentoPesquisa = new TabelaColuna();
			argumentoPesquisa.setId(new Integer(idArgumentoPesquisa));
		}

		/*
		 * Caso o usu�rio tenha informado aopera��o de pesquisa
		 * seta o id da opera��o de pesquisa
		 */		
		if (idOperacaoPesquisa != null && !idOperacaoPesquisa.trim().equals("")) {
			operacaoPesquisa = new Operacao();
			operacaoPesquisa.setId(new Integer(idOperacaoPesquisa));
		}

		//Monta a opera��o que vai ser atualizada
		Operacao operacao = (Operacao) sessao.getAttribute("operacaoAtualizar");
		operacao.setDescricao(atualizarOperacaoActionForm.getDescricao());
		operacao.setDescricaoAbreviada(atualizarOperacaoActionForm.getDescricaoAbreviada());
		operacao.setCaminhoUrl(atualizarOperacaoActionForm.getCaminhoUrl());
		operacao.setOperacaoTipo(operacaoTipo);
		operacao.setFuncionalidade(funcionalidade);
//		operacao.setTabelaColuna(argumentoPesquisa);
		operacao.setArgumentoPesquisa(argumentoPesquisa);
		operacao.setIdOperacaoPesquisa(operacaoPesquisa);

		//Vari�vel que vai armazenar uma cole��o de TabelaOperacao
		Collection<OperacaoTabela> colecaoOperacaoTabela = null;

		/*
		 * Caso exista a cole��o de tabela opera��o na sess�o
		 * recuperaa cole��o e cria os relacionamentos entre 
		 * a opera��o e as tabelas informadas pelo usu�rio 
		 */
		if (sessao.getAttribute("colecaoOperacaoTabela") != null) {
			//Recupera a cole��o de tabelas da sees�o  
			Collection<Tabela> colecaoTabela = (Collection<Tabela>) sessao.getAttribute("colecaoOperacaoTabela");
			
			/*
			 * Caso a cole��o detabelas n�o esteja vazia 
			 * colocaa cole��o no iterator 
			 * para criar todos os relacionamentos entre tabela e opera��o 
			 */
			if(!colecaoTabela.isEmpty()){
				//Coloca a cole��o no iterator
				Iterator<Tabela> iteratorTabela = colecaoTabela.iterator();
				
				//Inst�ncia a cole��o
				colecaoOperacaoTabela = new ArrayList();
				
				//La�o para criar os relacionamentos entre a opera��o e as tabelas
				while (iteratorTabela.hasNext()) {
					Tabela tabela = iteratorTabela.next();
					OperacaoTabela operacaoTabela = new OperacaoTabela(new OperacaoTabelaPK(operacao.getId(),tabela.getId()));
					colecaoOperacaoTabela.add(operacaoTabela);
				}
			}
		}

		//Chamao met�do para atualizar a opera��o
		fachada.atualizarOperacao(operacao, colecaoOperacaoTabela,usuarioLogado);

		//Limpa a sess�o
		sessao.removeAttribute("colecaoOperacaoTabela");

		//Monta a tela de sucesso
		montarPaginaSucesso(httpServletRequest, "Opera��o "
				+ operacao.getId() + " atualizado com sucesso.",
				"Realizar outra Manuten��o Opera��o",
				"exibirFiltrarOperacaoAction.do?menu=sim");

		//Retorna o mapeamento de retorno 
		return retorno;
	}
}

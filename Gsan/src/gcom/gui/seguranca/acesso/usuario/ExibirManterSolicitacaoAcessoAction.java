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
package gcom.gui.seguranca.acesso.usuario;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Hugo Leonardo	
 * @date 16/11/2010
 */
public class ExibirManterSolicitacaoAcessoAction extends GcomAction {

	/**
	 * [UC1093] Manter Solicita��o Acesso.
	 * 
	 * Este caso de uso permite alterar uma Solicita��o de Acesso
	 * 
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterSolicitacaoAcessoAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String objeto = (String) sessao.getAttribute("objeto");
		
		if(objeto != null && objeto.equals("autorizar")){
			retorno = actionMapping.findForward("exibirManterAutorizarSolicitacaoAcessoAction");
		}
		
		if(objeto != null && objeto.equals("cadastrar")){
			retorno = actionMapping.findForward("exibirManterCadastrarSolicitacaoAcessoAction");
		}
		
		if(objeto != null && objeto.equals("relatorio")){
			retorno = actionMapping.findForward("exibirGerarRelatorioSolicitacaoAcessoAction");
		}

		// Limpa o atributo se o usu�rio voltou para o manter
		if (sessao.getAttribute("colecaoSolicitacaoAcesso") != null) {
			sessao.removeAttribute("colecaoSolicitacaoAcesso");
		}

		FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = 
			(FiltroSolicitacaoAcesso)sessao.getAttribute("filtroSolicitacaoAcesso");
		
		// Aciona o controle de pagina��o para que sejam pesquisados apenas
		// os registros que aparecem na p�gina
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroSolicitacaoAcesso, SolicitacaoAcesso.class.getName());
		
		Collection colecaoSolicitacaoAcesso = (Collection) resultado
				.get("colecaoRetorno");
		
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		String situacao = (String) httpServletRequest.getAttribute("situacoes");

		// Verifica se a cole��o retornada pela pesquisa � nula, em caso
		// afirmativo comunica ao usu�rio que n�o existe nenhuma Solicita��o de Acesso
		// cadastrada para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condi��es seta o retorno para o
		// ExibirAtualizarSolicitacaoAcessoAction, se n�o atender manda a
		// cole��o pelo request para ser recuperado e exibido pelo jsp.

		if (colecaoSolicitacaoAcesso != null
				&& !colecaoSolicitacaoAcesso.isEmpty()) {

			// Verifica se a cole��o cont�m apenas um objeto, se est� retornando
			// da pagina��o (devido ao esquema de pagina��o de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usu�rio selecione o link para ir para a
			// segunda p�gina ele n�o v� para tela de atualizar.
			
			if (colecaoSolicitacaoAcesso.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {

				// Verifica se o usu�rio marcou o checkbox de atualizar no jsp
				// solicitacao_acesso_filtrar. Caso todas as condi��es sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarSolicitacaoAcessoAction e em caso negativo
				// manda a cole��o pelo request.

				if (httpServletRequest.getParameter("atualizar") != null && situacao.equals("1")) {
					
					retorno = actionMapping.findForward("atualizarSolicitacaoAcesso");
					
					SolicitacaoAcesso solicitacaoAcesso = 
						(SolicitacaoAcesso) colecaoSolicitacaoAcesso.iterator().next();
					
					sessao.setAttribute("objetoSolicitacaoAcesso", solicitacaoAcesso);

				} else {
					
					sessao.removeAttribute("objetoSolicitacaoAcesso");
					
					httpServletRequest.setAttribute(
							"colecaoSolicitacaoAcesso",	colecaoSolicitacaoAcesso);
				}
			} else {
				httpServletRequest.setAttribute("colecaoSolicitacaoAcesso", colecaoSolicitacaoAcesso);
			}
		} else {
			// Nenhuma funcionalidade cadastrada
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		httpServletRequest.setAttribute("situacao", situacao);

		return retorno;
	}
}

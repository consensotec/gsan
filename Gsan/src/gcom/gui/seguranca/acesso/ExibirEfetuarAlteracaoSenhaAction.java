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

import java.util.Date;

import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Action respons�vel pela pr�-exibi��o da tela de alterar a senha do usu�rio
 *
 * @author Pedro Alexandre
 * @date 17/07/2006
 */
public class ExibirEfetuarAlteracaoSenhaAction extends GcomAction {

	/**
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * [UC0287] - Efetuar Login
	 * 
	 * @author Pedro Alexandre
	 * @date 04/07/2006
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

		// Prepara o retorno da a��o para a tela de lembrar senha
		ActionForward retorno = actionMapping.findForward("efetuarAlteracaoSenha");
		
		
		//Recupera uma inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o usu�rio que est� logado
		Usuario usuarioLogado = (Usuario)sessao.getAttribute("usuarioLogado");
		
		/*================================================================================
		Altera��o: CRC1959 - Remocao do link de Alterar
		Senha e Combo de Ultimos Acessos caso o usuario logado ainda n�o tenha realizado
		a alteracao da senha padrao recebida no cadastro do mesmo no sistema
		autor: Anderson Italo
		data:19/06/2009*/
		Date dataExpiracaoAcesso = usuarioLogado.getDataExpiracaoAcesso();
		if (dataExpiracaoAcesso != null) {
			if (dataExpiracaoAcesso.before(new Date())) {
				sessao.setAttribute("indicadorSenhaPendente", 1);
			}
		}
		
		UsuarioSituacao usuarioSituacao = usuarioLogado.getUsuarioSituacao();
		if (usuarioSituacao.getId().equals(UsuarioSituacao.PENDENTE_SENHA)) {
			sessao.setAttribute("indicadorSenhaPendente", 1);
		}
		/*========================Fim da Altera��o=======================================*/
		
		//Recupera o lembrete da senha do usu�rio
		String lembreteSenha = usuarioLogado.getLembreteSenha();
		
		//Caso o lembrete esteja nulo, seta o lembrete para uma string vazia 
		if(lembreteSenha == null){
			lembreteSenha = "";
		}
		
		String mensagem = "A nova senha deve conter de seis a oito caracteres, " +
		"dentre as quais pelo menos uma letra(A, B, C,...,a,b,c,...), " +
	    "pelo menos um n�mero(0,1,2...) ," +
	    "n�o possuir seguencia de 3 caracteres iguais(aaa,111,...). exemplo: Gsan10";
		
		httpServletRequest.setAttribute("mensagem",mensagem);
		
		//Seta o lembrete da senha do usu�rio no request
		httpServletRequest.setAttribute("lembreteSenha",lembreteSenha);
		
		//Retornao mapeamento contido na vari�vel retorno
		return retorno;
	}
}

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
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action respons�vel por exibir a tela para lembrar a senha do usu�rio
 *
 * @author Pedro Alexandre
 * @date 07/07/2006
 */
public class ExibirLembrarSenhaAction extends GcomAction {

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
		ActionForward retorno = actionMapping.findForward("lembrarSenha");
		
		//Recupera o login informado pelo usu�rio na p�gina de login
		String login = httpServletRequest.getParameter("login");
		String cpf = httpServletRequest.getParameter("cpf");
		String dataNascimentoString = httpServletRequest.getParameter("dataNascimento");
		
		if(login != null && !login.trim().equals("")){
			// [FS0001] - Verificar exist�ncia do login
			if (!this.verificarExistenciaLogin(login)) {
				throw new ActionServletException("atencao.login.inexistente",null, login);
			} else {
				// Cria o filtro e pesquisa o usu�rio com o login informado
				FiltroUsuario filtroUsuario = new FiltroUsuario();
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, login));
				Collection usuarios = Fachada.getInstancia().pesquisar(filtroUsuario,Usuario.class.getName());
	
				//Recupera o usu�rio que est� sendo logado
				Usuario usuarioLogado = (Usuario)usuarios.iterator().next();
				
				String lembreteSenha = usuarioLogado.getLembreteSenha();
				
				//[FS0006] - Validar data
				Date dataNascimento = null;
		        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
		        try {
		            dataNascimento = dataFormato.parse(dataNascimentoString);
		        } catch (ParseException ex) {
		        	throw new ActionServletException("atencao.data.invalida",null, "Data de Nascimento");
		        }

		        //[FS0007] Verificar data maior ou igual a data corrente
		        Date dataAtual = new Date();
		        if(!dataNascimento.before(dataAtual)){
		        	throw new ActionServletException("atencao.data_nascimento.anterior.dataatual",login,Util.formatarData(dataAtual));
		        }
		        
		        //[UC0008] - Verificar data de nascimento do login
				Date dataNascimentoUsuarioLogado = usuarioLogado.getDataNascimento();
				if(dataNascimento.compareTo(dataNascimentoUsuarioLogado) != 0 ){
					throw new ActionServletException("atencao.data_nascimento.incorreta.login",null,login);
				}
				
				//Recupera o CPF do usu�rio que est� logado e verifica 
				//se � o mesmo que foi informado n� p�gina
				String cpfUsuarioLogado = usuarioLogado.getCpf();
				if(cpfUsuarioLogado != null && !cpfUsuarioLogado.trim().equals("")){
					//[FS0010] - Verificar CPF do login
					if(!cpf.equals(cpfUsuarioLogado)){
						throw new ActionServletException("atencao.cpf.incorreto.login",null,login);
					}
				}
				
				//Seta no request o login e o lembrete da senha do usu�rio 
				//para ser recuperados na jsp do lembrete 
				httpServletRequest.setAttribute("lembreteSenha",lembreteSenha);
				httpServletRequest.setAttribute("login",login);
				httpServletRequest.setAttribute("dataNascimento",Util.formatarData(dataNascimento));
				httpServletRequest.setAttribute("cpf",cpf);
			}
		}
		return retorno;
	}

	/**
	 * Verifica se o login informado existe para algum usu�rio do sistema
	 * retorna true se existir caso contr�rio retorna false.
	 * 
	 * [FS0001] - Verificar exist�ncia do login
	 *
	 * @author Pedro Alexandre
	 * @date 07/07/2006
	 *
	 * @param login
	 * @return
	 */
	private boolean verificarExistenciaLogin(String login) {
		// Inicializa o retorno para falso(login n�o existe)
		boolean retorno = false;

		// Cria o filtro e pesquisa o usu�rio com o login informado
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, login));
		Collection usuarios = Fachada.getInstancia().pesquisar(filtroUsuario,Usuario.class.getName());

		// Caso exista o usu�rio com o login informado
		// seta o retorno para verdadeiro(login existe no sistema)
		if (usuarios != null && !usuarios.isEmpty()) {
			retorno = true;
		}
		// Retorna um indicador se o login informado existe ou n�o no sistema
		return retorno;
	}
}

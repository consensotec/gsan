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
* Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.FiltroNegativadorRegistroTipo;
import gcom.cobranca.NegativadorRegistroTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter Negativador Registro Tipo
 * 
 * @author Yara Taciane 
 * @created 08/01/2008
 */
public class AtualizarNegativadorRegistroTipoAction extends GcomAction {
	/**
	 * @author Yara Taciane
	 * @date 08/01/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarNegativadorRegistroTipoActionForm atualizarNegativadorRegistroTipoActionForm = (AtualizarNegativadorRegistroTipoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// ------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR_REGISTRO_TIPO,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR_REGISTRO_TIPO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSA��O ----------------

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a

		NegativadorRegistroTipo negativadorRegistroTipo = (NegativadorRegistroTipo) sessao.getAttribute("negativadorRegistroTipo");

		// Pegando os dados do Formul�rio	
		String descricaoRegistroTipo = atualizarNegativadorRegistroTipoActionForm.getDescricaoRegistroTipo();
		String codigoRegistro = atualizarNegativadorRegistroTipoActionForm.getCodigoRegistro();
		Long time = Long.parseLong(atualizarNegativadorRegistroTipoActionForm.getTime()); 
		
		// Seta os campos para serem atualizados				
		
		if (descricaoRegistroTipo!= null	&& !descricaoRegistroTipo.equals("")) {

			negativadorRegistroTipo.setDescricaoRegistroTipo(descricaoRegistroTipo);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Descri��o do Tipo do Registro");
		}	

		// -------------------------------------------------------------------------------------

		if (codigoRegistro != null 	&& !codigoRegistro.equals("")) {
			
			if(!codigoRegistro.equalsIgnoreCase("H")&& !codigoRegistro.equalsIgnoreCase("D") && !codigoRegistro.equalsIgnoreCase("T") ){
				throw new ActionServletException("atencao.codigo_tipo_registro_invalido");
			}
				
			negativadorRegistroTipo.setCodigoRegistro(codigoRegistro);
			

		} else {
			throw new ActionServletException("atencao.required", null,"C�digo do Registro");
		}
		
		//------------------------------------------------------------------------------------		
		//Check para atualiza��o realizada por outro usu�rio 
		FiltroNegativadorRegistroTipo filtroNegativadorRegistroTipo = new FiltroNegativadorRegistroTipo(); 
		filtroNegativadorRegistroTipo.adicionarParametro(new ParametroSimples(FiltroNegativadorRegistroTipo.ID, negativadorRegistroTipo.getId()));
		
		Collection collNegativadorRegistroTipo = Fachada.getInstancia().pesquisar(filtroNegativadorRegistroTipo, NegativadorRegistroTipo.class.getName());
		
		NegativadorRegistroTipo negativRegistroTipo = (NegativadorRegistroTipo)collNegativadorRegistroTipo.iterator().next();

		if (negativRegistroTipo.getUltimaAlteracao().getTime() != time){
			throw new ActionServletException("atencao.registro_remocao_nao_existente");
		}
		// ------------ REGISTRAR TRANSA��O ----------------
		negativadorRegistroTipo.setOperacaoEfetuada(operacaoEfetuada);
		negativadorRegistroTipo.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativadorRegistroTipo);
		// ------------ REGISTRAR TRANSA��O ----------------

			
		negativadorRegistroTipo.setUltimaAlteracao(new Date());
		
		// Atualiza o negativadorContrato 
		fachada.atualizar(negativadorRegistroTipo);
		
		montarPaginaSucesso(httpServletRequest, " Tipo do Registro do Negativador de c�digo "
				+ negativadorRegistroTipo.getId() + " atualizado com sucesso.",
				"Realizar outra Manuten��o do Tipo do Registro do Negativador",
				"exibirFiltrarNegativadorRegistroTipoAction.do?desfazer=S");
	
		return retorno;
	}
    
	
    
   
}
 
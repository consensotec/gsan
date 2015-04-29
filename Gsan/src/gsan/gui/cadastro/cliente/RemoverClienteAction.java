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
package gsan.gui.cadastro.cliente;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.gui.ManutencaoRegistroActionForm;
import gsan.interceptor.RegistradorOperacao;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Remove os clientes selecionados na lista da funcionalidade Manter Cliente
 * 
 * @author rodrigo
 */
public class RemoverClienteAction extends GcomAction {

    /**
     * < <Descri��o do m�todo>>
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        Fachada fachada = Fachada.getInstancia();

        // mensagem de erro quando o usu�rio tenta excluir sem ter selecionado
        // nenhum registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException(
                    "atencao.registros.nao_selecionados");
        }
		
        FiltroCliente filtroCliente = new FiltroCliente();
        
        for(int j = 0; j < ids.length; j++){
        	/**
			 * @author Fl�vio Leonardo
			 * @date 19/08/2014
			 * 
			 * @param idCliente, usuario
			 * @return boolean
			 * @throws ControladorException
			 * [FS0018  Verificar permiss�o especial para altera��o de cliente negativado]
			 */	
			if(Util.verificarNaoVazio(ids[j]) && fachada.verificarSeClienteEstaNegativado(new Integer(ids[j]), usuario)){
				throw new ActionServletException(
    					"atencao.usuario_nao_possui_permissao_alterar_cliente_negativado", ids[j]);
			}
        }
        
        for(int i = 0; i < ids.length; i++){
	        
        	// Parte de Validacao com Timestamp
			filtroCliente.limparListaParametros();
			
			// Seta o filtro para buscar o cliente na base
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, ids[i]));
			
			Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
			
			// Procura o cliente na base
			Cliente clienteNaBase = (Cliente) ((List)colecaoCliente).get(0);
        	
        	// ------------ REGISTRAR TRANSA��O ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_CLIENTE_REMOVER, new Integer(ids[i]), new Integer(ids[i]), 
					new UsuarioAcaoUsuarioHelper(usuario,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			// ------------ REGISTRAR TRANSA��O ----------------
	    	
			registradorOperacao.registrarOperacao(clienteNaBase);
			fachada.remover(clienteNaBase);
        }
     //   fachada.remover(ids, Cliente.class.getName(), operacaoEfetuada, colecaoUsuarios);

        //Monta a p�gina de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest,
            		ids.length + " Cliente(s) removido(s) com sucesso",
                    "Realizar outra Manuten��o de Cliente",
                    "exibirManterClienteAction.do?menu=sim");
        }

        return retorno;
    }
}

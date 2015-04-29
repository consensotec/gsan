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
import gsan.cadastro.cliente.ClienteTipo;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.cliente.FiltroClienteTipo;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que inicializa a primeira p�gina do processo de inserir cliente
 * 
 * @author Rodrigo
 */
public class ExibirInserirClienteNomeTipoAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("inserirClienteNomeTipo");
        
        HttpSession session = httpServletRequest.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		Fachada fachada = Fachada.getInstancia();
		DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;
		
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		String descricao = "";
		
		String descricaoAbreviada = "";
		
		if(sistemaParametro.getIndicadorUsoNMCliReceitaFantasia().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO.intValue()){
			
			session.setAttribute("indicadorNomeFantasia", true);
			
			descricao = "Nome na Receita Federal:" ;
			
			descricaoAbreviada = "Nome de Fantasia:" ;
			
		} 
		
		//Solicitado pela CAEMA em 24/10/2012 - Solicitado por Tiago Moreno.
		else if (sistemaParametro.getIndicadorUsoNMCliReceitaFantasia().intValue() == 9) { 
			descricao = "Nome: " ;
			
			descricaoAbreviada = "Nome de Fantasia: " ;
			
			session.removeAttribute("indicadorNomeFantasia");
		
		} else{
			
			descricao = "Nome: " ;
			
			descricaoAbreviada = "Nome Abreviado: " ;
			
			session.removeAttribute("indicadorNomeFantasia");
			
		}
		
		session.setAttribute("descricao", descricao);
		session.setAttribute("descricaoAbreviada", descricaoAbreviada);
		
		 if(httpServletRequest.getParameter("idCliente")!=null 
	        		&& !httpServletRequest.getParameter("idCliente").toString().equals("") ){
	        	
	        	FiltroCliente filtroCliente = new FiltroCliente();
	        	
	        	filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,httpServletRequest.getParameter("idCliente")));
	        	
	        	filtroCliente.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.CLIENTE_TIPO);
	        	
	        	Collection clientes = fachada.pesquisar(filtroCliente,Cliente.class.getName());
	        	
	        	if(clientes!=null && !clientes.isEmpty()){
	        		Cliente cliente = (Cliente) clientes.iterator().next();
	        		clienteActionForm.set("nome", cliente.getNome());
	                clienteActionForm.set("tipoPessoa", new Short(cliente.getClienteTipo().getId().toString()));
	                clienteActionForm.set("indicadorPessoaFisicaJuridica",cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().toString());
	                httpServletRequest.setAttribute("nome",cliente.getNome());
	                httpServletRequest.setAttribute("tipoPessoa",new Short(cliente.getClienteTipo().getId().toString()));
	                httpServletRequest.setAttribute("indicadorPessoaFisicaJuridica",cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica());
	            }
	        }

        //Pesquisa os Tipos de Clientes para a p�gina
		String indicadorPessoaFisicaJuridica = (String) clienteActionForm.get("indicadorPessoaFisicaJuridica");
		
        FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
        filtroClienteTipo.adicionarParametro(new ParametroSimples(
                FiltroClienteTipo.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.INDICADOR_PESSOA_FISICA_JURIDICA, indicadorPessoaFisicaJuridica));
        filtroClienteTipo.setCampoOrderBy(FiltroClienteTipo.DESCRICAO);

        httpServletRequest.setAttribute("colecaoTipoPessoa", Fachada
                .getInstancia().pesquisar(filtroClienteTipo,
                        ClienteTipo.class.getName()));
        
        /** Verificar as permiss�o especial para Visualizar Dia de Vencimento em Cliente  					 **/
		boolean temPermissaoVisualizarDiaVencimentoContaCliente = fachada.verificarPermissaoVisualizarDiaVencimentoContaCliente(usuario);
		httpServletRequest.setAttribute("temPermissaoVisualizarDiaVencimentoContaCliente",temPermissaoVisualizarDiaVencimentoContaCliente);
		/******************************************************************************************/
		
		//Verificar se o usu�rio possui permiss�o especial para negativar cliente
		FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_MANTER_CLIENTE_SEM_NEGATIVACAO));
		
		Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
		
		session.removeAttribute("permissaoEspecial");
		
		if (colecaoUsuarioPermisao != null && !colecaoUsuarioPermisao.isEmpty()) {
			session.setAttribute("permissaoEspecial", "true");
		}
		
		
		//**********************************************************************
		// CRC2103
		// Autor: Ivan Sergio
		// Data: 02/07/2009
		// Verifica se a tela deve ser exibida como um PopUp
		//**********************************************************************
		if (httpServletRequest.getParameter("POPUP") != null) {
			if (httpServletRequest.getParameter("POPUP").equals("true")) {
				session.setAttribute("POPUP", "true");
			}else {
				session.setAttribute("POPUP", "false");
			}
		}else if (session.getAttribute("POPUP") == null) {
			session.setAttribute("POPUP", "false");
		}
		//**********************************************************************
		
        return retorno;
    }
}

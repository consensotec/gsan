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
package gcom.gui.micromedicao.leitura;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.leitura.AtualizarLeituristaActionForm;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Thiago Ten�rio e Thiago Nascimento
 * @date 30/10/2006
 */
public class ExibirAtualizarLeituristaAction extends GcomAction {
	/**
	 * [UC0589] Manter Leiturista
	 * 
	 * Este caso de uso permite alterar os dados do leiturista
	 * 
	 * @author Thiago Ten�rio e Thiago Nascimento
	 * @date 31/10/2006
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
		ActionForward retorno = actionMapping
				.findForward("atualizarLeiturista");

		HttpSession sessao = httpServletRequest.getSession(false);

		sessao.setAttribute("mostrarLogin", true);
		
		AtualizarLeituristaActionForm form = (AtualizarLeituristaActionForm) actionForm;

		Integer id = null;
		
		Leiturista leiturista = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		//Limpar Funcionario
		if (httpServletRequest.getParameter("limparFuncionario") != null && httpServletRequest.getParameter("limparFuncionario").equals("sim")){
			sessao.removeAttribute("bloquearEmpresa");
			return retorno;
		}
		
		if (httpServletRequest.getParameter("pesquisa") != null
				&& (httpServletRequest.getParameter("pesquisa").toString().trim().equals("cliente")
					|| httpServletRequest.getParameter("pesquisa").toString().trim().equals("funcionario")
						|| httpServletRequest.getParameter("pesquisa").toString().trim().equals("login"))) {
			
			if ((form.getIdCliente() != null && !form.getIdCliente().trim().equals("")) && httpServletRequest.getParameter("pesquisa").toString().trim().equals("cliente")) {
				this.buscarCliente(form,fachada,httpServletRequest);
			}
			
			if(form.getIdFuncionario() != null && !form.getIdFuncionario().trim().equals("") && httpServletRequest.getParameter("pesquisa").toString().trim().equals("funcionario")){
				this.buscarFuncionario(form,fachada,httpServletRequest);
			}
			
			if(httpServletRequest.getParameter("pesquisa") != null && httpServletRequest.getParameter("pesquisa").toString().trim().equals("login")){
				this.getUsuario(form, fachada, form.getLoginUsuario(), sessao);
			}
			
		} else {
			
			//Unidade Organizacional
			if(httpServletRequest.getParameter("pesquisa") != null && httpServletRequest.getParameter("pesquisa").toString().trim().equals("unidade")){
				getUnidadeOrganizacional(form, fachada, sessao);
			}
			
			//Usu�rio
//			if (form.getLoginUsuario() != null && !form.getLoginUsuario().equals("")) {
//				this.getUsuario(form, fachada, form.getLoginUsuario(), sessao);
//			}		
			
	
			if (httpServletRequest.getParameter("idRegistroAtualizacao") != null
					&& !httpServletRequest.getParameter("idRegistroAtualizacao").toString().trim().equals("")) {
				
				id = new Integer(httpServletRequest.getParameter("idRegistroAtualizacao").toString());				
							
				if(id!=null){
					leiturista = this.buscarLeiturista(id,fachada);
					this.exibirDadosLeiturista(form,leiturista,sessao);
					sessao.setAttribute("leiturista", leiturista);
				}		
			}
			
			if (httpServletRequest.getParameter("pesquisa") == null && leiturista == null) {
				leiturista = (Leiturista) sessao.getAttribute("leiturista");
				this.exibirDadosLeiturista(form, leiturista,sessao);
			}
			
			// Bloquear o campo Empresa quando a empresa do usuario nao seja a empresa do sistema
			
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
			if (usuario.getEmpresa() == null) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Empresa do usu�rio logado.");
			}
			if (!usuario.getEmpresa().getDescricao().equals(sistemaParametro.getNomeAbreviadoEmpresa())) {
				sessao.setAttribute("bloquearEmpresa", true);
				form.setEmpresaID(usuario.getEmpresa().getId().toString());
			}
	
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
	
			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
	
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
			
			// Desfazer
			if (httpServletRequest.getParameter("desfazer") != null && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
				if (sessao.getAttribute("leiturista") != null) {
					leiturista = (Leiturista)sessao.getAttribute("leiturista");
				}else{
					leiturista = this.buscarLeiturista(new Integer(form.getId()),fachada);
				}
				 if(leiturista!=null){       	
					 this.exibirDadosLeiturista(form,leiturista,sessao);
					 sessao.setAttribute("leiturista", leiturista);
				 }
			    
			}else{
				if (sessao.getAttribute("leiturista") == null) {
					if(leiturista==null){					 
						 leiturista = this.buscarLeiturista(new Integer(form.getId()),fachada);
					}
					sessao.setAttribute("leiturista", leiturista);
				}				
			}
		}
			
		return retorno;
	}
	
	/** 
	 * Recupera a Unidade Organizacional do Leiturista
	 * 
	 * @author Davi
	 * @date 03/11/2011
	 * 
	 * @param inserirLeituristaActionForm
	 * @param fachada 
	 */
//	private void getUnidadeOrganizacional(AtualizarLeituristaActionForm form, Fachada fachada, HttpSession sessao){
//		
//		//Filtra a Unidade Organizacional
//		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
//		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, form.getUnidadeOrganizacionalId()));
//		
//		//Recupera a Unidade Organizacional
//		Collection<UnidadeOrganizacional> colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
//		
//		if(!Util.isVazioOrNulo(colecaoUnidade)){
//			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);
//			
//			sessao.setAttribute("unidadeOrganizacionalIdEncontrada","true");
//			form.setUnidadeOrganizacionalDescricao(unidadeOrganizacional.getDescricao());
//		}else{
//			sessao.removeAttribute("unidadeOrganizacionalIdEncontrada");
//			form.setUnidadeOrganizacionalId("");
//			form.setUnidadeOrganizacionalDescricao("Unidade Organizacional Inexistente");
//		}
//	}
	
	/** 
	 * Recupera a Unidade Organizacional do Leiturista
	 * 
	 * @author Nathalia Santos
	 * @date 19/01/2012
	 * 
	 * @param atualizarLeituristaActionForm
	 * @param fachada 
	 */
		private void getUnidadeOrganizacional(AtualizarLeituristaActionForm form, Fachada fachada, HttpSession sessao){
		
		//Filtra a Unidade Organizacional
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, form.getUnidadeOrganizacionalId()));
		
		//Recupera a Unidade Organizacional
		Collection<UnidadeOrganizacional> colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
			UnidadeOrganizacional unidadeOrganizacional = colecaoUnidade.iterator().next();
			
			sessao.setAttribute("unidadeOrganizacionalIdEncontrada","true");
			form.setUnidadeOrganizacionalDescricao(unidadeOrganizacional.getDescricao());
			
		}  else {

			sessao.removeAttribute("unidadeOrganizacionalIdEncontrada");
			form.setUnidadeOrganizacionalId("");
			form.setUnidadeOrganizacionalDescricao("Unidade Organizacional Inexistente");
		}
	}



	/**
	 * 
	 * Metodo auxiliar para preencher o form com os valores do leiturista
	 * 
	 * @param form
	 * @param id
	 * @param fachada
	 */
	private void exibirDadosLeiturista(AtualizarLeituristaActionForm form, Leiturista leiturista,HttpSession sessao){
			        	 	
        	form.setDdd(leiturista.getCodigoDDD());
        	
        	if(leiturista.getCliente()!=null){
        		form.setDescricaoCliente(leiturista.getCliente().getNome());
        		form.setIdCliente(leiturista.getCliente().getId().toString());
        		sessao.removeAttribute("bloquearEmpresa");
        	}else{
        		form.setDescricaoCliente("");
        		form.setIdCliente("");
        	}
        	
        	if(leiturista.getFuncionario()!=null){
        		form.setDescricaoFuncionario(leiturista.getFuncionario().getNome());
        		form.setIdFuncionario(leiturista.getFuncionario().getId().toString());
        		form.setEmpresaID(leiturista.getFuncionario().getEmpresa().getId().toString());
        		sessao.setAttribute("bloquearEmpresa", true);
        	}else{
        		form.setDescricaoFuncionario("");
        		form.setIdFuncionario("");
        	}
        	
        	if(leiturista.getUnidadeOrganizacional() != null){
        		sessao.setAttribute("unidadeOrganizacionalIdEncontrada","true");
        		form.setUnidadeOrganizacionalId(leiturista.getUnidadeOrganizacional().getId().toString());
        		form.setUnidadeOrganizacionalDescricao(leiturista.getUnidadeOrganizacional().getDescricao());
        	}else{
        		sessao.setAttribute("unidadeOrganizacionalIdEncontrada","true");
        		form.setUnidadeOrganizacionalId("");
        		form.setUnidadeOrganizacionalDescricao("");
        	}
        	
        	form.setEmpresaID(leiturista.getEmpresa().getId().toString());
        	
        	form.setId(leiturista.getId().toString());
        	
        	form.setNumeroImei(leiturista.getNumeroImei());
        	
        	form.setTelefone(leiturista.getNumeroFone());
        	
        	form.setIndicadorUso(leiturista.getIndicadorUso().toString());
        	
        	form.setIndicadorAgenteComercial(leiturista.getIndicadorAgenteComercial().toString());
        	
        	form.setIndicadorAtualizacaoCadastral(leiturista.getIndicadorAtualizacaoCadastral().toString());
        	
        	if ( leiturista.getUsuario() != null ){
    			// Filtra Usuario
        		if(leiturista.getUsuario() != null && !leiturista.getUsuario().getLogin().equals(form.getLoginUsuario())){
        			
	    			FiltroUsuario filtroUsuario = new FiltroUsuario();
	    			filtroUsuario.adicionarParametro(new ComparacaoTexto(FiltroUsuario.LOGIN, leiturista.getUsuario().getLogin() ) );		
	    			
	    			// Recupera Usu�rio
	    			Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
	    			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
	    				Usuario usuario = colecaoUsuario.iterator().next();
	    				
	    				form.setLoginUsuario( usuario.getLogin() );
	    				form.setNomeUsuario( usuario.getNomeUsuario() );
	    				sessao.setAttribute("usuarioEncontrado","SIM");
	    			}else{
	    				form.setLoginUsuario( "" );
	    				form.setNomeUsuario( "USUARIO INEXISTENTE" ); 
	    				sessao.removeAttribute("usuarioEncontrado");
	    			}
        		}else{
        			Usuario usuario = leiturista.getUsuario();
    				
    				form.setLoginUsuario( usuario.getLogin() );
    				form.setNomeUsuario( usuario.getNomeUsuario() );
    				sessao.setAttribute("usuarioEncontrado","SIM");
        		}
        	}else{
        		form.setLoginUsuario( "" );
				form.setNomeUsuario( "" ); 
				sessao.removeAttribute("usuarioEncontrado");
        	}
	}
	
	/**
	 * 
	 * M�todo Auxiliar para buscar os dados do cliente
	 * 
	 * @param form
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void buscarCliente(AtualizarLeituristaActionForm form , Fachada fachada, HttpServletRequest httpServletRequest){
			HttpSession sessao = httpServletRequest.getSession();

			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, form.getIdCliente()));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

				Cliente cliente = (Cliente) colecaoCliente.iterator().next();

				form.setIdCliente(cliente.getId().toString());
				form.setDescricaoCliente(cliente.getNome());
				sessao.removeAttribute("bloquearEmpresa");

			} else {
				httpServletRequest.setAttribute("clienteEncontrado",
						"exception");
				form.setIdCliente("");
				form.setDescricaoCliente("CLIENTE INEXISTENTE");
			}

		
	}
	
	/**
	 * 
	 * M�todo auxilixar para buscar os dados do funcion�rio
	 * 
	 * @param form
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void buscarFuncionario(AtualizarLeituristaActionForm form , Fachada fachada, HttpServletRequest httpServletRequest){
		HttpSession sessao = httpServletRequest.getSession();
		
		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

		filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.ID, form.getIdFuncionario()));
		filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("empresa");

		Collection colecaoFuncionario = fachada.pesquisar(
				filtroFuncionario, Funcionario.class.getName());

		if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

			Funcionario funcionario = (Funcionario) colecaoFuncionario
					.iterator().next();
			form.setIdFuncionario(funcionario.getId().toString());
			form.setDescricaoFuncionario(funcionario.getNome());
			form.setEmpresaID(funcionario.getEmpresa().getId().toString());
			sessao.setAttribute("bloquearEmpresa", true);
		} else {
			httpServletRequest.setAttribute("funcionarioEncontrado",
					"exception");
			form.setIdFuncionario("");
			form.setDescricaoFuncionario("FUNCIONARIO INEXISTENTE");
		}
	}
	
	/**
	 * 
	 * M�todo auxiliar para buscar um Leiturista
	 * 
	 * @param id
	 * @param fachada
	 * @return
	 */
	private Leiturista buscarLeiturista(Integer id, Fachada fachada){
		
		Leiturista leiturista = null;
		
		FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.EMPRESA);
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
        filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
        filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.USUARIO);
        filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.UNIDADE_ORGANIZACIONAL);
        filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ID,id));
        
        Collection colecao = fachada.pesquisar(filtroLeiturista,Leiturista.class.getName());
        if(colecao!=null && !colecao.isEmpty()){
        	leiturista = (Leiturista)colecao.iterator().next();
        }
        
        return leiturista;
	}
	
	/**
	 * Recupera o Usu�rio
	 *
	 * @author Bruno Barros
	 * @date 11/12/2006
	 *
	 * @param atualizarLeituristaActionForm
	 * @param fachada
	 * @param idUsuario
	 * @return Descri��o da Unidade Filtrada
	 */
	private void getUsuario(AtualizarLeituristaActionForm atualizarLeituristaActionForm, 
			Fachada fachada, String idUsuario, HttpSession sessao) {
		
		// Filtra Usuario
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, idUsuario));		
		
		// Recupera Usu�rio
		Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
			Usuario usuario = colecaoUsuario.iterator().next();
			
			sessao.setAttribute("usuarioEncontrado","true");
			atualizarLeituristaActionForm.setNomeUsuario(usuario.getNomeUsuario());
		} else {
			
			sessao.removeAttribute("usuarioEncontrado");
			atualizarLeituristaActionForm.setLoginUsuario("");
			atualizarLeituristaActionForm.setNomeUsuario("Usu�rio Inexistente");
		}
	}
}

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

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
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
 * @author Hugo Leonardo
 * @date 16/11/2010
 */
public class ExibirFiltrarSolicitacaoAcessoAction extends GcomAction {

	/**
	 * [UC0984] Filtrar tipo de d�bito vig�ncia
	 * 
	 * Este caso de uso cria um filtro que ser� usado na pesquisa do Solicita��o Acesso.
	 * 
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarSolicitacaoAcesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);	

		FiltrarSolicitacaoAcessoActionForm form = (FiltrarSolicitacaoAcessoActionForm) actionForm;
		
		String objeto = "";
		
		if(sessao.getAttribute("objeto") == null){
			objeto = httpServletRequest.getParameter("objeto");
			
			if(objeto != null && (objeto.equals("autorizar") || objeto.equals("cadastrar")
					|| objeto.equals("atualizar") || objeto.equals("relatorio"))){
				
				sessao.setAttribute("objeto", objeto);
			}
		}else{
			
			objeto = (String) sessao.getAttribute("objeto");
		}

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equals("sim")) {
			
			if(objeto.equals("atualizar")){
				form.setAtualizar("1");
			}
			
			if(usuario.getFuncionario() != null){
				
				if(objeto.equals("atualizar")){
					form.setIdFuncionarioSolicitante(""+usuario.getFuncionario().getId());
					form.setNomeFuncionarioSolicitante(usuario.getFuncionario().getNome());
				
				}else{
					if(!objeto.equals("cadastrar") && !objeto.equals("relatorio")){
						form.setIdFuncionarioSuperior(""+usuario.getFuncionario().getId());
						form.setNomeFuncionarioSuperior(usuario.getFuncionario().getNome());
					}
				}
			}else{
				
				throw new ActionServletException("atencao.acesso.solicitacao_funcionario");
			}
			
			//Pesquisar Empresa
			if(sessao.getAttribute("colecaoEmpresa") == null){
				this.pesquisarEmpresa(sessao, form);
			}
			
			// Pesquisar Situa��o
			if(sessao.getAttribute("colecaoSituacao") == null){
				this.pesquisarSolicitacaoAcessoSituacao(sessao, form);
			}
		}

		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
			
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				(objetoConsulta.trim().equals("1") || objetoConsulta.trim().equals("2")
						|| objetoConsulta.trim().equals("3"))) {

			// Pega os codigos que o usuario digitou para a pesquisa direta do funcion�rio
			if (form.getIdFuncionario() != null && !form.getIdFuncionario().trim().equals("")
					|| (form.getIdFuncionarioSuperior() != null && !form.getIdFuncionarioSuperior().trim().equals(""))
					|| (form.getIdFuncionarioSolicitante() != null && !form.getIdFuncionarioSolicitante().trim().equals(""))) {
				
				this.pesquisarFuncionario( httpServletRequest, form, objetoConsulta);
			}
		}
		
		// Pega os codigos que o usuario digitou para a pesquisa direta da lota��o
		if(form.getIdLotacao() != null && !form.getIdLotacao().trim().equals("")){
			
			this.pesquisarLotacao(httpServletRequest, form);
		}

		return retorno;
	}
	
	/**
	 * Pesquisar Empresa
	 *
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */
	private void pesquisarEmpresa(HttpSession sessao, FiltrarSolicitacaoAcessoActionForm form) {
	
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setConsultaSemLimites(true);
		filtroEmpresa.adicionarParametro(new ParametroSimples( 
				FiltroUsuarioTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		
		Collection colecaoEmpresa = this.getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());
		
		if(colecaoEmpresa == null || colecaoEmpresa.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Empresa");
		}else{
			
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}
	}
	
	/**
	 * Pesquisar Funcion�rio
	 *
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */
	private void pesquisarFuncionario(HttpServletRequest httpServletRequest, 
			FiltrarSolicitacaoAcessoActionForm form, String objetoConsulta) {

		Fachada fachada = Fachada.getInstancia();
		
		Object local = null;
		
		if(objetoConsulta.trim().equals("3")){
			local = form.getIdFuncionarioSuperior();
			
		}else if(objetoConsulta.trim().equals("2")){
			
			local = form.getIdFuncionario();
		}else if(objetoConsulta.trim().equals("1")){
			
			local = form.getIdFuncionarioSolicitante();
		}

		// Pesquisa a usu�rio na base
		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
		filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.ID, local));

		Collection<Funcionario> funcionarioPesquisado = fachada.pesquisar(
				filtroFuncionario, Funcionario.class.getName());

		// Se nenhum usu�rio for encontrado a mensagem � enviada para a p�gina
		if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
			Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);
			
			if(objetoConsulta.trim().equals("3")){
				form.setIdFuncionarioSuperior(""+funcionario.getId());
				form.setNomeFuncionarioSuperior(funcionario.getNome());
			
			}else if(objetoConsulta.trim().equals("2")){
				form.setIdFuncionario("" + funcionario.getId());
				form.setNomeFuncionario( funcionario.getNome());
			
			}else if(objetoConsulta.trim().equals("1")){
				form.setIdFuncionarioSolicitante("" + funcionario.getId());
				form.setNomeFuncionarioSolicitante( funcionario.getNome());
			}

		} else {
			
			if(objetoConsulta.trim().equals("3")){
				form.setIdFuncionarioSuperior(null);
				form.setNomeFuncionarioSuperior("FUNCION�RIO INEXISTENTE");
				httpServletRequest.setAttribute("funcionarioInexistente1","true");
	
			}else if(objetoConsulta.trim().equals("2")){
				form.setIdFuncionario(null);
				form.setNomeFuncionario("FUNCION�RIO INEXISTENTE");
				httpServletRequest.setAttribute("funcionarioInexistente","true");
	
			}else if(objetoConsulta.trim().equals("1")){
				form.setIdFuncionario(null);
				form.setNomeFuncionario("FUNCION�RIO INEXISTENTE");
				httpServletRequest.setAttribute("funcionarioInexistente2","true");
	
			}
		}
	}
	
	/**
	 * Pesquisar Lota��o
	 *
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */
	private void pesquisarLotacao(HttpServletRequest httpServletRequest, 
			FiltrarSolicitacaoAcessoActionForm form) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a usu�rio na base
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, form.getIdLotacao()));

		Collection<UnidadeOrganizacional> lotacaoPesquisada = fachada.pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		// Se nenhum usu�rio for encontrado a mensagem � enviada para a p�gina
		if (lotacaoPesquisada != null && !lotacaoPesquisada.isEmpty()) {
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(lotacaoPesquisada);
			form.setIdLotacao("" + unidadeOrganizacional.getId());
			form.setNomeLotacao( unidadeOrganizacional.getDescricao());

		} else {
			form.setIdFuncionario("");
			form.setNomeFuncionario("LOTA��O INEXISTENTE");
			httpServletRequest.setAttribute("lotacaoInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idLotacao");
		}
	}
	
	/**
	 * Pesquisar Situa��o
	 *
	 * @author Hugo Leonardo
	 * @date 16/11/2010
	 */
	private void pesquisarSolicitacaoAcessoSituacao(HttpSession sessao,	FiltrarSolicitacaoAcessoActionForm form) {
	
		FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();
		
		filtroSolicitacaoAcessoSituacao.setConsultaSemLimites(true);
		filtroSolicitacaoAcessoSituacao.adicionarParametro(new ParametroSimples( 
				FiltroSolicitacaoAcessoSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		if(sessao.getAttribute("objeto") != null){
			String objeto = (String) sessao.getAttribute("objeto");
			
			if(objeto.equals("atualizar")){
				filtroSolicitacaoAcessoSituacao.adicionarParametro(new ParametroSimples( 
						FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, SolicitacaoAcessoSituacao.AG_AUTORIZACAO_SUP));
			}else if(objeto.equals("autorizar")){
				filtroSolicitacaoAcessoSituacao.adicionarParametro(new ParametroSimples( 
						FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, SolicitacaoAcessoSituacao.AG_AUTORIZACAO_SUP));
			}else if(objeto.equals("cadastrar")){
				filtroSolicitacaoAcessoSituacao.adicionarParametro(new ParametroSimples( 
						FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, SolicitacaoAcessoSituacao.AG_CADASTRAMENTO));
			}
		}
		
		filtroSolicitacaoAcessoSituacao.setCampoOrderBy(FiltroSolicitacaoAcessoSituacao.DESCRICAO);
		
		Collection colecaoSituacao = this.getFachada().pesquisar(
				filtroSolicitacaoAcessoSituacao, SolicitacaoAcessoSituacao.class.getName());
		
		if(colecaoSituacao == null || colecaoSituacao.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Solicita��o Acesso Situa��o");
		}else{
			
			sessao.setAttribute("colecaoSituacao", colecaoSituacao);
		}
	}

}
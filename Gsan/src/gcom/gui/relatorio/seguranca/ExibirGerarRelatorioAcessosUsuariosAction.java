/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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


package gcom.gui.relatorio.seguranca;

import gcom.cadastro.geografico.FiltroRegiaoDesenvolvimento;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.FiltroModulo;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.FiltroPermissaoEspecial;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.Modulo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1040] Gerar Relat�rio de Acessos por Usu�rio
 * 
 * @author Hugo Leonardo
 *
 * @date 12/07/2010
 */

public class ExibirGerarRelatorioAcessosUsuariosAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirGerarRelatorioAcessosUsuariosAction");
		
		GerarRelatorioAcessosUsuariosActionForm form = 
			(GerarRelatorioAcessosUsuariosActionForm) actionForm;

		// Primeira vez que carrega a pagina
		if ( httpServletRequest.getParameter("menu") != null && 
				httpServletRequest.getParameter("menu").equals("sim")) {
			
			//form.setSituacaoUsuario("0");
		}
		
		this.pesquisarUnidadeOrganizacional(httpServletRequest,form);
		this.pesquisarGrupoAcesso(httpServletRequest,form);
		this.pesquisarUsuarioSituacao(httpServletRequest,form);
		
		if(form.getIdFuncionalidade() != null && !form.getIdFuncionalidade().equals("")){
			this.pesquisarOperacoes(httpServletRequest,form);
		}
		
		this.pesquisarUsuarioTipo(httpServletRequest, form);
		this.pesquisarPermissaoEspecial(httpServletRequest, form);
		this.pesquisarModulo(httpServletRequest, form);
		
		// Seta os request�s encontrados
		this.setaRequest(httpServletRequest,form);
		
		// Pega os codigos que o usuario digitou para a pesquisa direta da funcionalidade
		if (form.getIdFuncionalidade() != null && !form.getIdFuncionalidade().trim().equals("")) {
			
			this.pesquisarFuncionalidade( httpServletRequest, form);
		}
		
		// Pega os codigos que o usuario digitou para a pesquisa direta do Usuario
		if (form.getIdUsuario() != null && !form.getIdUsuario().trim().equals("")) {
			
			this.pesquisarUsuario( httpServletRequest, form);
		}
		
		return retorno;
	}

	private void setaRequest(HttpServletRequest httpServletRequest,
			GerarRelatorioAcessosUsuariosActionForm form){
		
		// Funcionalidade
		if(form.getIdFuncionalidade() != null && 
			!form.getIdFuncionalidade().equals("") && 
			form.getNomeFuncionalidade() != null && 
			!form.getNomeFuncionalidade().equals("")){
					
			httpServletRequest.setAttribute("funcionalidadeEncontrada","true");
		}
		
		// Usuario
		if(form.getIdUsuario() != null && 
			!form.getIdUsuario().equals("") && 
			form.getNomeUsuario() != null && 
			!form.getNomeUsuario().equals("")){
					
			httpServletRequest.setAttribute("usuarioEncontrado","true");
		}
		
	}
	
	private void pesquisarUnidadeOrganizacional(HttpServletRequest httpServletRequest,
			GerarRelatorioAcessosUsuariosActionForm form){
		
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		
		filtroUnidadeOrganizacional.setConsultaSemLimites(true);
		filtroUnidadeOrganizacional.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);

		filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoUnidadeOrganizacional = 
			this.getFachada().pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		if (colecaoUnidadeOrganizacional == null || colecaoUnidadeOrganizacional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Unidade Organizacional");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeOrganizacional", colecaoUnidadeOrganizacional);
		}
	}
	
	private void pesquisarGrupoAcesso(HttpServletRequest httpServletRequest,
			GerarRelatorioAcessosUsuariosActionForm form){
		
		FiltroGrupo filtroGrupo = new FiltroGrupo();
		
		filtroGrupo.setConsultaSemLimites(true);
		filtroGrupo.setCampoOrderBy(FiltroRegiaoDesenvolvimento.DESCRICAO);

		filtroGrupo.adicionarParametro(
				new ParametroSimples(FiltroGrupo.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoGrupo = 
			this.getFachada().pesquisar(filtroGrupo, Grupo.class.getName());

		if (colecaoGrupo == null || colecaoGrupo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Grupo de Usuario");
		} else {
			httpServletRequest.setAttribute("colecaoGrupoAcesso", colecaoGrupo);
		}
	}
	
	private void pesquisarModulo(HttpServletRequest httpServletRequest,
			GerarRelatorioAcessosUsuariosActionForm form){
		
		FiltroModulo filtroModulo = new FiltroModulo();
		// Verifica se os dados foram informados da tabela existem e joga numa colecao
		Collection<Modulo> colecaoModulo = this.getFachada().pesquisar(filtroModulo,
				Modulo.class.getName());

		if (colecaoModulo == null || colecaoModulo.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Modulo");
		}else{
			httpServletRequest.setAttribute("colecaoModulo", colecaoModulo);
		}	
	}

	private void pesquisarFuncionalidade(HttpServletRequest httpServletRequest, 
			GerarRelatorioAcessosUsuariosActionForm form) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a funcionalidade na base
		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarParametro(new ParametroSimples(
				FiltroFuncionalidade.ID, form.getIdFuncionalidade()));

		Collection<Funcionalidade> funcionalidadePesquisada = fachada.pesquisar(
				filtroFuncionalidade, Funcionalidade.class.getName());

		// Se nenhuma funcionalidade for encontrada a mensagem � enviada para a p�gina
		if (funcionalidadePesquisada != null && !funcionalidadePesquisada.isEmpty()) {
			Funcionalidade funcionalidade = (Funcionalidade) Util.retonarObjetoDeColecao(funcionalidadePesquisada);
			form.setIdFuncionalidade("" + funcionalidade.getId());
			form.setNomeFuncionalidade( funcionalidade.getDescricao());

		} else {
			form.setIdFuncionalidade("");
			form.setNomeFuncionalidade("FUNCIONALIDADE INEXISTENTE");
			httpServletRequest.setAttribute("funcionalidadeInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idFuncionalidade");
		}
	}
	
	private void pesquisarOperacoes(HttpServletRequest httpServletRequest,
			GerarRelatorioAcessosUsuariosActionForm form){
		
		FiltroOperacao filtroOperacao = new FiltroOperacao();
		
		filtroOperacao.setConsultaSemLimites(true);
		filtroOperacao.adicionarParametro( new ParametroSimples(
				FiltroOperacao.FUNCIONALIDADE, form.getIdFuncionalidade()));
		filtroOperacao.setCampoOrderBy(FiltroOperacao.DESCRICAO);		

		Collection colecaoOperacao = 
			this.getFachada().pesquisar(filtroOperacao, Operacao.class.getName());

		if (colecaoOperacao == null || colecaoOperacao.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Operacao");
		} else {
			httpServletRequest.setAttribute("colecaoOperacao", colecaoOperacao);
		}
	}
	
	private void pesquisarUsuarioTipo(HttpServletRequest httpServletRequest, 
			GerarRelatorioAcessosUsuariosActionForm form) {
	
		FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();
		
		filtroUsuarioTipo.setConsultaSemLimites(true);
		filtroUsuarioTipo.adicionarParametro(new ParametroSimples( FiltroUsuarioTipo.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoUsuarioTipo = 
			this.getFachada().pesquisar(filtroUsuarioTipo, UsuarioTipo.class.getName());
		
		if(colecaoUsuarioTipo == null || colecaoUsuarioTipo.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Usu�rio Tipo");
		}else{
			httpServletRequest.setAttribute("colecaoUsuarioTipo", colecaoUsuarioTipo);
		}
	}
	
	private void pesquisarUsuario(HttpServletRequest httpServletRequest, 
			GerarRelatorioAcessosUsuariosActionForm form) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a usu�rio na base
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuario.ID, form.getIdUsuario()));

		Collection<Usuario> usuarioPesquisado = fachada.pesquisar(
				filtroUsuario, Usuario.class.getName());

		// Se nenhum usu�rio for encontrado a mensagem � enviada para a p�gina
		if (usuarioPesquisado != null && !usuarioPesquisado.isEmpty()) {
			Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(usuarioPesquisado);
			form.setIdUsuario("" + usuario.getId());
			form.setNomeUsuario( usuario.getNomeUsuario());

		} else {
			form.setIdUsuario("");
			form.setNomeUsuario("USUARIO INEXISTENTE");
			httpServletRequest.setAttribute("usuarioInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idUsuario");
		}
	}
	
	private void pesquisarPermissaoEspecial(HttpServletRequest httpServletRequest,
			GerarRelatorioAcessosUsuariosActionForm form){
		
		FiltroPermissaoEspecial filtroPermissaoEspecial = new FiltroPermissaoEspecial();
		
		filtroPermissaoEspecial.setConsultaSemLimites(true);
		filtroPermissaoEspecial.setCampoOrderBy(FiltroPermissaoEspecial.DESCRICAO);

		filtroPermissaoEspecial.adicionarParametro(
				new ParametroSimples(FiltroPermissaoEspecial.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoPermissaoEspecial = 
			this.getFachada().pesquisar(filtroPermissaoEspecial, PermissaoEspecial.class.getName());

		if (colecaoPermissaoEspecial == null || colecaoPermissaoEspecial.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Permissao Especial");
		} else {
			httpServletRequest.setAttribute("colecaoPermissaoEspecial", colecaoPermissaoEspecial);
		}
	}

	private void pesquisarUsuarioSituacao(HttpServletRequest httpServletRequest, 
			GerarRelatorioAcessosUsuariosActionForm form) {
	
		FiltroUsuarioSituacao filtroUsuarioSituacao = new FiltroUsuarioSituacao();
		
		filtroUsuarioSituacao.setConsultaSemLimites(true);
		filtroUsuarioSituacao.adicionarParametro(new ParametroSimples( FiltroUsuarioSituacao.INDICADOR_DE_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoUsuarioSituacao = 
			this.getFachada().pesquisar(filtroUsuarioSituacao, UsuarioSituacao.class.getName());
		
		if(colecaoUsuarioSituacao == null || colecaoUsuarioSituacao.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Usu�rio Situa��o");
		}else{
			httpServletRequest.setAttribute("colecaoUsuarioSituacao", colecaoUsuarioSituacao);
		}
	}
}

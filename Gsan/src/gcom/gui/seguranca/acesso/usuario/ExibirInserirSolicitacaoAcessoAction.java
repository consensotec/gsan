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
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1092] Inserir Solicita��o de Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 03/11/2010
 */
public class ExibirInserirSolicitacaoAcessoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirInserirSolicitacaoAcessoAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		// Form
		ExibirInserirSolicitacaoAcessoActionForm form = 
			(ExibirInserirSolicitacaoAcessoActionForm) actionForm;
		
		retiraMsgAnteriores(form);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		
		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equals("sim")) {
			
			//RM 3892
	  		Fachada fachada = Fachada.getInstancia();
	  		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
	  		form.setDominioEmail(sistemaParametro.getDominioEmailCorporativo());
	  			  		
			if(usuario.getFuncionario() != null){
				form.setIdFuncionarioSolicitante(""+usuario.getFuncionario().getId());
				form.setNomeFuncionarioSolicitante(usuario.getFuncionario().getNome());
			}else{
				
				throw new ActionServletException("atencao.acesso.solicitacao_funcionario");
			}
			
			// Verifica se o Usu�rio pertence ao grupo "Superintendente"
			boolean superintendente = false;
			
			FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();
			
			filtroUsuarioGrupo.adicionarCaminhoParaCarregamentoEntidade(
					FiltroUsuarioGrupo.GRUPO);
			
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_ID, usuario.getId()));
			
			Collection colecaoUsuarioGrupo = this.getFachada().pesquisar(filtroUsuarioGrupo, 
					UsuarioGrupo.class.getName());
			
			if(colecaoUsuarioGrupo != null && !colecaoUsuarioGrupo.isEmpty()){
				
				Iterator colecaoIterator = colecaoUsuarioGrupo.iterator();

				while (colecaoIterator.hasNext()) {
					
					UsuarioGrupo usuarioGrupo = (UsuarioGrupo) colecaoIterator.next();
				
					if(usuarioGrupo.getGrupo().getIndicadorSuperintendencia()
							.compareTo(ConstantesSistema.INDICADOR_SUPERINTENDENCIA) == 0){
						
						superintendente = true;
						break;
					}
				}
			}
			
			// Caso Usu�rio perten�a ao grupo "Superintendente"
			if(superintendente){

				form.setIdFuncionarioSuperior(""+usuario.getFuncionario().getId());
				form.setNomeFuncionarioSuperior(usuario.getFuncionario().getNome());
				form.setIcNotificar("1");
				form.setIcSuperintendente("1");
			}else{
				form.setIcNotificar("0");
				form.setIcSuperintendente("2");
			}
			
			// Pesquisar Grupo
			this.pesquisarGrupo(sessao, form);
			
			//Pesquisar Tipo Usuario
			this.pesquisarTipoUsuario(sessao, form);

			//Pesquisar Empresa
			this.pesquisarEmpresa(sessao, form);
			
			/* RM 3892 Implantar norma de senhas no GSAN */
			FiltroUsuarioAbrangencia filtroUsuarioAbrangencia = new FiltroUsuarioAbrangencia();

			filtroUsuarioAbrangencia.adicionarParametro(new ParametroSimples(
					FiltroUsuarioAbrangencia.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUsuarioAbrangencia = Fachada.getInstancia()
					.pesquisar(filtroUsuarioAbrangencia,
							UsuarioAbrangencia.class.getSimpleName());
			sessao.setAttribute("colecaoUsuarioAbrangencia",
					colecaoUsuarioAbrangencia);

			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoGerenciaRegional = Fachada.getInstancia()
					.pesquisar(filtroGerenciaRegional,
							GerenciaRegional.class.getSimpleName());
			sessao
					.setAttribute("colecaoGerenciaRegional",
							colecaoGerenciaRegional);
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUnidadeNegocio = Fachada.getInstancia()
					.pesquisar(filtroUnidadeNegocio,
							UnidadeNegocio.class.getSimpleName());
			sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
			
			//Fim RM 3892
			
		}
		
		if(httpServletRequest.getParameter("usuarioTipo") != null && 
	        	!httpServletRequest.getParameter("usuarioTipo").equals("") ){
			
			String idUsuarioTipo = httpServletRequest.getParameter("usuarioTipo").toString();
			
			// Se n�o for "Prestador de Servi�os"
			if(!idUsuarioTipo.equals("8") && Util.verificarNaoVazio(form.getIdFuncionario())){
				
				// Pesquisa a fincion�rio
				FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
				filtroFuncionario.adicionarParametro(new ParametroSimples(
						FiltroFuncionario.ID, form.getIdFuncionario()));

				Collection<Funcionario> funcionarioPesquisado = this.getFachada().pesquisar(
						filtroFuncionario, Funcionario.class.getName());

				// Se nenhum usu�rio for encontrado a mensagem � enviada para a p�gina
				if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
					
					Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);
					
					if(funcionario.getNumeroCpf() != null){
						form.setCpf(""+funcionario.getNumeroCpf());
					}
					if(funcionario.getDataNascimento() != null){
						
						form.setDataNascimento(""+Util.formatarData(funcionario.getDataNascimento()));
					}
					
					int tamanhoMaximoCampo = 50;
					
					if (tamanhoMaximoCampo < funcionario.getNome().length()) {
						// trunca a String
						String strTruncado = funcionario.getNome().substring(0, tamanhoMaximoCampo);
						form.setNomeUsuario(strTruncado);
					}else{
						
						form.setNomeUsuario(funcionario.getNome());
					}
				}
			}else{
				
				form.setCpf("");
				form.setDataNascimento("");
				form.setNomeFuncionario("");
				form.setNomeUsuario("");
			}
		}

		httpServletRequest.setAttribute("dataAtual", Util.formatarData(new Date()));
		
		// IDADE M�NIMA
        httpServletRequest.setAttribute("idadeMinimaUsuario", "18");
		
		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
			
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				(objetoConsulta.trim().equals("1")|| objetoConsulta.trim().equals("2"))) {

			// Pega os codigos que o usuario digitou para a pesquisa direta do funcion�rio
			if (form.getIdFuncionario() != null && !form.getIdFuncionario().trim().equals("")
					|| (form.getIdFuncionarioSuperior() != null && !form.getIdFuncionarioSuperior().trim().equals(""))) {
				
				this.pesquisarFuncionario( httpServletRequest, form, objetoConsulta);
			}
		}
		
		// Pega os codigos que o usuario digitou para a pesquisa direta da lota��o
		if(form.getIdLotacao() != null && !form.getIdLotacao().trim().equals("")){
			this.pesquisarLotacao(httpServletRequest, form);
		}
		
		/* RM 3892 Implantar norma de senhas no GSAN */
		if (form.getIdElo() != null && !form.getIdElo().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, form.getIdElo()));
			Collection colecaoElo = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getSimpleName());
			if (colecaoElo != null && !colecaoElo.isEmpty()) {
				Localidade l = (Localidade) colecaoElo.iterator().next();
				if (l.getLocalidade() != null
						&& !l.getId().equals(l.getLocalidade().getId())) {
					form.setIdElo("");
					//throw new ActionServletException("atencao.localidade.nao.elo");
					form.setNomeElo("Localidade n�o � um Elo.");
					form.setEloNaoEncontrada("true");
				} else {
					form.setIdElo(l.getId().toString());
					form.setNomeElo(l.getDescricao());
					form.setEloNaoEncontrada("false");
				}
			} else {
				form.setIdElo("");
				form.setNomeElo("Elo inexistente");
				form.setEloNaoEncontrada("true");
			}
		}
		if (form.getIdLocalidade() != null
				&& !form.getIdLocalidade().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, form.getIdLocalidade()));

			Collection colecaoLocalidade = Fachada.getInstancia().pesquisar(
					filtroLocalidade, Localidade.class.getSimpleName());
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade l = (Localidade) colecaoLocalidade.iterator().next();
				form.setIdLocalidade(l.getId().toString());
				form.setIdElo("" + l.getLocalidade().getId());
				form.setIdElo(l.getLocalidade().getDescricao());
				form.setNomeLocalidade(l.getDescricao());
				form.setLocalidadeNaoEncontrada("false");
			} else {
				form.setIdLocalidade("");
				form.setNomeLocalidade("Localidade inexistente");
				form.setLocalidadeNaoEncontrada("true");
			}
		}
		//Fim RM 3892
		
		
		
		return retorno;
	}
	
	/**
	 * Pesquisar Funcion�rio
	 *
	 * @author Hugo Leonardo
	 * @date 09/11/2010
	 */
	private void pesquisarFuncionario(HttpServletRequest httpServletRequest, 
			ExibirInserirSolicitacaoAcessoActionForm form, String objetoConsulta) {

		Fachada fachada = Fachada.getInstancia();
		
		Object local = null;
		
		if(objetoConsulta.trim().equals("1")){
			local = form.getIdFuncionarioSuperior();
			
		}else if(objetoConsulta.trim().equals("2")){
			
			local = form.getIdFuncionario();
		}

		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
		filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_ORGANIZACIONAL);
		filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.ID, local));

		Collection<Funcionario> funcionarioPesquisado = fachada.pesquisar(
				filtroFuncionario, Funcionario.class.getName());

		if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
			Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);
			
			if(objetoConsulta.trim().equals("1")){
				form.setIdFuncionarioSuperior(""+funcionario.getId());
				form.setNomeFuncionarioSuperior(funcionario.getNome());
			
			}else if(objetoConsulta.trim().equals("2")){
				form.setIdFuncionario("" + funcionario.getId());
				form.setNomeFuncionario( funcionario.getNome());
				
				if(funcionario.getNumeroCpf() != null){
					form.setCpf(funcionario.getNumeroCpf());
				}
				
				if(funcionario.getDataNascimento() != null){
					form.setDataNascimento(Util.formatarData(funcionario.getDataNascimento()));
				}
				
				if(funcionario.getUnidadeOrganizacional() != null){
					form.setIdLotacao("" + funcionario.getUnidadeOrganizacional().getId());
					form.setNomeLotacao(funcionario.getUnidadeOrganizacional().getDescricao());
				}
			}

		} else {
			
			if(objetoConsulta.trim().equals("1")){
				form.setIdFuncionarioSuperior(null);
				form.setNomeFuncionarioSuperior("FUNCION�RIO INEXISTENTE");
				httpServletRequest.setAttribute("funcionarioInexistente1","true");
	
			}
			
			if(objetoConsulta.trim().equals("2")){
				form.setIdFuncionario(null);
				form.setNomeFuncionario("FUNCION�RIO INEXISTENTE");
				httpServletRequest.setAttribute("funcionarioInexistente","true");
	
			}
		}
	}
	
	/**
	 * Pesquisar Grupo
	 *
	 * @author Hugo Leonardo
	 * @date 09/11/2010
	 */
	private void pesquisarGrupo(HttpSession sessao,	ExibirInserirSolicitacaoAcessoActionForm form) {
	
		FiltroGrupo filtroGrupo = new FiltroGrupo();
		
		filtroGrupo.setConsultaSemLimites(true);
		filtroGrupo.adicionarParametro(new ParametroSimples( 
				FiltroGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroGrupo.setCampoOrderBy(FiltroGrupo.DESCRICAO);
		
		Collection colecaoGrupo = this.getFachada().pesquisar(filtroGrupo, Grupo.class.getName());
		
		if(colecaoGrupo == null || colecaoGrupo.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Grupos");
		}else{
			
			sessao.setAttribute("colecaoGrupo", colecaoGrupo);
		}
	}
	
	/**
	 * Pesquisar Usuario Tipo
	 *
	 * @author Hugo Leonardo
	 * @date 09/11/2010
	 */
	private void pesquisarTipoUsuario(HttpSession sessao, ExibirInserirSolicitacaoAcessoActionForm form) {
	
		FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();
		
		filtroUsuarioTipo.setConsultaSemLimites(true);
		filtroUsuarioTipo.adicionarParametro(new ParametroSimples( 
				FiltroUsuarioTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroUsuarioTipo.setCampoOrderBy(FiltroUsuarioTipo.DESCRICAO);
		
		Collection colecaoUsuarioTipo = this.getFachada().pesquisar(filtroUsuarioTipo, UsuarioTipo.class.getName());
		
		if(colecaoUsuarioTipo == null || colecaoUsuarioTipo.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Usu�rio Tipo");
		}else{
			
			sessao.setAttribute("colecaoUsuarioTipo", colecaoUsuarioTipo);
		}
	}
	
	/**
	 * Pesquisar Empresa
	 *
	 * @author Hugo Leonardo
	 * @date 10/11/2010
	 */
	private void pesquisarEmpresa(HttpSession sessao, ExibirInserirSolicitacaoAcessoActionForm form) {
	
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
	 * Pesquisar Lota��o
	 *
	 * @author Hugo Leonardo
	 * @date 10/11/2010
	 */
	private void pesquisarLotacao(HttpServletRequest httpServletRequest, 
			ExibirInserirSolicitacaoAcessoActionForm form) {

		Fachada fachada = Fachada.getInstancia();

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, form.getIdLotacao()));

		Collection<UnidadeOrganizacional> lotacaoPesquisada = fachada.pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		if (lotacaoPesquisada != null && !lotacaoPesquisada.isEmpty()) {
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(lotacaoPesquisada);
			form.setIdLotacao("" + unidadeOrganizacional.getId());
			form.setNomeLotacao( unidadeOrganizacional.getDescricao());

		} else {
			form.setIdLotacao("");
			form.setNomeLotacao("LOTA��O INEXISTENTE");
			httpServletRequest.setAttribute("lotacaoInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idLotacao");
		}
	}
	
	private void retiraMsgAnteriores(ExibirInserirSolicitacaoAcessoActionForm form){
		String msgFunc = "FUNCIONARIO INEXISTENTE.";
		if ((form.getNomeFuncionario()!=null && form.getNomeFuncionario().equalsIgnoreCase(msgFunc)) || 
				(form.getNomeFuncionarioSolicitante()!=null && form.getNomeFuncionarioSolicitante().equalsIgnoreCase(msgFunc))
				|| (form.getNomeFuncionarioSuperior()!=null && form.getNomeFuncionarioSuperior().equalsIgnoreCase(msgFunc))){
			form.setNomeFuncionario("");
			form.setNomeFuncionarioSolicitante("");
			form.setNomeFuncionarioSuperior("");
		}
		if (form.getNomeLotacao()!=null && form.getNomeLotacao().equalsIgnoreCase("LOTA��O INEXISTENTE.")){
			form.setNomeLotacao("");
		}
		if ((form.getNomeElo()!=null && form.getNomeElo().equalsIgnoreCase("Elo inexistente")) 
				|| (form.getNomeElo()!=null && form.getNomeElo().equalsIgnoreCase("Localidade n�o � um Elo."))){
			form.setNomeElo("");
		}
		if (form.getNomeLocalidade()!=null && form.getNomeLocalidade().equalsIgnoreCase("Localidade inexistente")){
			form.setNomeLocalidade("");
		}
	}
	
}
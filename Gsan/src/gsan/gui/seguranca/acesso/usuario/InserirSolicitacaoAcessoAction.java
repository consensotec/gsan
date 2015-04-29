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
package gsan.gui.seguranca.acesso.usuario;

import gsan.cadastro.EnvioEmail;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.funcionario.FiltroFuncionario;
import gsan.cadastro.funcionario.Funcionario;
import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.FiltroGrupo;
import gsan.seguranca.acesso.Grupo;
import gsan.seguranca.acesso.usuario.FiltroSolicitacaoAcesso;
import gsan.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gsan.seguranca.acesso.usuario.FiltroUsuario;
import gsan.seguranca.acesso.usuario.FiltroUsuarioAbrangencia;
import gsan.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gsan.seguranca.acesso.usuario.SolicitacaoAcesso;
import gsan.seguranca.acesso.usuario.SolicitacaoAcessoGrupo;
import gsan.seguranca.acesso.usuario.SolicitacaoAcessoGrupoPK;
import gsan.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAbrangencia;
import gsan.seguranca.acesso.usuario.UsuarioGrupo;
import gsan.seguranca.acesso.usuario.UsuarioTipo;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.email.ErroEmailException;
import gsan.util.email.ServicosEmail;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
 * @date 11/11/2010
 */

public class InserirSolicitacaoAcessoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);	
		
		ExibirInserirSolicitacaoAcessoActionForm form = 
			(ExibirInserirSolicitacaoAcessoActionForm) actionForm;
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Seta Objeto Solicitacao Acesso
		SolicitacaoAcesso solicitacaoAcesso = setSolicitacaoAcesso(form, sessao, usuario);
		
		FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();
		
		filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoAcesso.LOGIN, solicitacaoAcesso.getLogin()));
		
		Collection colecaoSolicitacaoAcesso = this.getFachada().pesquisar(filtroSolicitacaoAcesso, 
				SolicitacaoAcesso.class.getName());

		if(!Util.isVazioOrNulo(colecaoSolicitacaoAcesso)){
			
			throw new ActionServletException("atencao.acesso.solicitacao.existente", null, solicitacaoAcesso.getLogin());
		}
		
		Integer idSolicitacaoAcesso = (Integer) Fachada.getInstancia().inserir(solicitacaoAcesso);
		
		solicitacaoAcesso.setId(idSolicitacaoAcesso);
		
		if (form.getIdsGrupo() != null && !form.getIdsGrupo().equals("-1") ){
			
			List lista = Arrays.asList(form.getIdsGrupo());  
			Collection<Grupo> colecaoGrupo = new ArrayList();
			
			FiltroGrupo filtroGrupo = new FiltroGrupo();
			filtroGrupo.adicionarParametro(new ParametroSimplesIn( 
					FiltroGrupo.ID, lista));	
			
			colecaoGrupo = this.getFachada().pesquisar(filtroGrupo, Grupo.class.getName());
			
			Grupo grupo = null;
			SolicitacaoAcessoGrupo solicitacaoAcessoGrupo = null;
			SolicitacaoAcessoGrupoPK solicitacaoAcessoGrupoPK = null;
			
			if(colecaoGrupo != null && !colecaoGrupo.isEmpty() ){
				
				Iterator iterator = colecaoGrupo.iterator();
			
				while (iterator.hasNext()) {
					
					grupo = (Grupo) iterator.next();
					
					solicitacaoAcessoGrupoPK = new SolicitacaoAcessoGrupoPK(
							solicitacaoAcesso, grupo);
					
					solicitacaoAcessoGrupo = new SolicitacaoAcessoGrupo(
							solicitacaoAcessoGrupoPK, new Date());
					
					Fachada.getInstancia().inserir(solicitacaoAcessoGrupo);
				}
			}
		}
		
		// Enviar Email
		EnvioEmail envioEmail = Fachada.getInstancia().pesquisarEnvioEmail(
				EnvioEmail.INSERIR_SOLICITACAO_ACESSO);

		String emailRemetente = envioEmail.getEmailRemetente();

		String tituloMensagem = "Solicita��o de Acesso.";

		String emailReceptor = form.getEmail();

		String mensagem = "";
		
		if(solicitacaoAcesso.getFuncionario() != null){
			
			mensagem += " O Funcion�rio, Matr�cula: "+ solicitacaoAcesso.getFuncionario().getId()
				+ " necessita de sua libera��o de acesso.";
		}else{
		
			mensagem += " O Prestador de servi�os, CPF: "+ solicitacaoAcesso.getCpf()
				+ " necessita de sua libera��o de acesso.";
		}
		
		if(solicitacaoAcesso.getIndicadorNotificarResponsavel().compareTo(ConstantesSistema.SIM) == 0){
			
			try {
				ServicosEmail.enviarMensagem(emailRemetente, emailReceptor,
						tituloMensagem, mensagem);
				
			} catch (ErroEmailException erroEnviarEmail) {
				erroEnviarEmail.printStackTrace();
			}
		}

		
		
		// Monta a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Inserir Solicita��o de Acesso para usu�rio: " 
				+  solicitacaoAcesso.getNomeUsuario()
				+ " inserida com sucesso. ",
				"Inserir outra Solicita��o de Acesso",
				"exibirInserirSolicitacaoAcessoAction.do?menu=sim");

		return retorno;
	
	}
	
	/**
	 * Preenche objeto com informa��es vindas da tela
	 * 
	 * @author Hugo Leonardo
	 * @date 11/11/2010
	 * 
	 * @param form
	 * @return SolicitacaoAcesso
	 */
	private SolicitacaoAcesso setSolicitacaoAcesso(ExibirInserirSolicitacaoAcessoActionForm form, 
			HttpSession sessao, Usuario usuario) {		
		
		SolicitacaoAcesso solicitacaoAcesso = new SolicitacaoAcesso();
		
		Fachada fachada = Fachada.getInstancia();
		
		// funcion�rio solicitante
		if(Util.verificarNaoVazio(form.getIdFuncionarioSolicitante())){
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(form.getIdFuncionarioSolicitante()));
			solicitacaoAcesso.setFuncionarioSolicitante(funcionario);
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Funcion�rio Solicitante");
		}
		
		// funcion�rio respons�vel
		if(Util.verificarNaoVazio(form.getIdFuncionarioSuperior())){
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(form.getIdFuncionarioSuperior()));
			solicitacaoAcesso.setFuncionarioResponsavel(funcionario);
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Respons�vel pela Autoriza��o");
		}
		
		// Indicador Notificar por E-mail
		if(Util.verificarNaoVazio(form.getIcNotificar())){
			
			if(form.getIcNotificar().equals("0")){
				
				solicitacaoAcesso.setIndicadorNotificarResponsavel(new Short("1"));
			}else if(form.getIcNotificar().equals("1")){
				
				solicitacaoAcesso.setIndicadorNotificarResponsavel(new Short("2"));
			}
		}
		
		// tipo Usu�rio
		if(Util.verificarNaoVazio(form.getIdTipoUsuario())){
			UsuarioTipo usuarioTipo = new UsuarioTipo();
			usuarioTipo.setId(new Integer(form.getIdTipoUsuario()));
			solicitacaoAcesso.setUsuarioTipo(usuarioTipo);
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Tipo de Usu�rio");
		}
		
		// funcion�rio usu�rio
		if(Util.verificarNaoVazio(form.getIdFuncionario())){
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(form.getIdFuncionario()));
			solicitacaoAcesso.setFuncionario(funcionario);
		}

		// Empresa, Nome Usu�rio
		if(Util.verificarNaoVazio(form.getIdFuncionario())){
		
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade(
					FiltroFuncionario.UNIDADE_EMPRESA);
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, form.getIdFuncionario()));

			Collection<Funcionario> funcionarioPesquisado = Fachada.getInstancia().pesquisar(
					filtroFuncionario, Funcionario.class.getName());
			
			if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
				Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);
				
				solicitacaoAcesso.setEmpresa(funcionario.getEmpresa());
				
				// CPF
				if(funcionario.getNumeroCpf() != null){
					
					if(Util.validacaoCPF(funcionario.getNumeroCpf())){
						solicitacaoAcesso.setCpf(funcionario.getNumeroCpf());
						
					}else{
						throw new ActionServletException("atencao.cpf_invalido");
					}
				}
				
				// Data Nascimento
				if(funcionario.getDataNascimento() != null){
					solicitacaoAcesso.setDataNascimento(funcionario.getDataNascimento());
				}

				// Nome Usu�rio
				int tamanhoMaximoCampo = 50;
				if (tamanhoMaximoCampo < funcionario.getNome().length()) {
					// trunca a String
					String strTruncado = funcionario.getNome().substring(0, tamanhoMaximoCampo);
					solicitacaoAcesso.setNomeUsuario(strTruncado);
				}else{
					solicitacaoAcesso.setNomeUsuario(funcionario.getNome());
				}
			}
		}else if(Util.verificarNaoVazio(form.getIdEmpresa())){
			Empresa empresa = new Empresa();
			empresa.setId(new Integer(form.getIdEmpresa()));
			solicitacaoAcesso.setEmpresa(empresa);
		}
		
		// nome Usu�rio
		if(!Util.verificarNaoVazio(form.getIdFuncionario()) 
				&& Util.verificarNaoVazio(form.getNomeUsuario())){
			
			solicitacaoAcesso.setNomeUsuario(form.getNomeUsuario());
		}
		
		// CPF
		if(form.getCpf() != null 
				&& Util.verificarNaoVazio(form.getCpf())){
			
			if(Util.validacaoCPF(form.getCpf())){
				solicitacaoAcesso.setCpf(form.getCpf());
				
			}else{
				throw new ActionServletException("atencao.cpf_invalido");
			}
			
		}else if(!Util.verificarNaoVazio(form.getIdFuncionario())){
			
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "N�mero do CPF");
		}
		
		// data Nascimento
		if(solicitacaoAcesso.getDataNascimento() == null 
				&& Util.verificarNaoVazio(form.getDataNascimento())
				&& !Util.validarDiaMesAno(form.getDataNascimento())){
			
			solicitacaoAcesso.setDataNascimento(Util.converteStringParaDate(form.getDataNascimento()));
		}else if(!Util.verificarNaoVazio(form.getIdFuncionario())) {
			
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Data de Nascimento");
		}
		
		// Unidade de Lota��o
		if(Util.verificarNaoVazio(form.getIdLotacao())){
			UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
			unidadeOrganizacional.setId(new Integer(form.getIdLotacao()));
			solicitacaoAcesso.setUnidadeOrganizacional(unidadeOrganizacional);
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Unidade de Lota��o");
		}
		
		// Login
		if(Util.verificarNaoVazio(form.getLogin())){
			
			solicitacaoAcesso.setLogin(form.getLogin());
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Login");
		}
		
		// Email
		if(Util.verificarNaoVazio(form.getEmail())){
			
			solicitacaoAcesso.setEmail(form.getEmail());
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "E-Mail");
		}
		
		//Per�odo Inicial e Per�odo Final
		if (form.getDataInicial() != null && !form.getDataInicial().equals("")
				&& form.getDataFinal() != null && !form.getDataFinal().equals("")){
			
			if (!Util.validarDiaMesAno(form.getDataInicial())) {
				
				solicitacaoAcesso.setPeriodoInicial(Util.converteStringParaDate(form.getDataInicial()));
				
				if (!Util.validarDiaMesAno(form.getDataFinal())) {
					solicitacaoAcesso.setPeriodoFinal(Util.converteStringParaDate(form.getDataFinal()));
					if(Util.compararData(solicitacaoAcesso.getPeriodoInicial(),solicitacaoAcesso.getPeriodoFinal()) == 1){
						throw new ActionServletException("atencao.atencao.data_vigencia_final_menor");
					}
				}else{
					throw new ActionServletException("atencao.atencao.data_vigencia_final_invalida");
				}			
			}else{
				throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
			}
			
		}else{
			solicitacaoAcesso.setPeriodoInicial(null);
			solicitacaoAcesso.setPeriodoFinal(null);
			
		}
		
		// Solicita��o Acesso Situa��o
		FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();
		filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
				FiltroUsuarioGrupo.USUARIO_ID, usuario.getId()));
		
		filtroUsuarioGrupo.adicionarCaminhoParaCarregamentoEntidade(
				FiltroUsuarioGrupo.GRUPO);
		
		Collection colecaoUsuarioGrupo = this.getFachada().pesquisar(filtroUsuarioGrupo, 
				UsuarioGrupo.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoUsuarioGrupo)){
			
			UsuarioGrupo usuarioGrupo = null;
			Grupo grupo = null;
			Grupo grupoSuperintendencia = null;
			
			FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();
			
			Iterator iterator = colecaoUsuarioGrupo.iterator();
			
			while (iterator.hasNext()) {
				
				usuarioGrupo = (UsuarioGrupo) iterator.next();
				
				grupo = usuarioGrupo.getGrupo();
				
				if(grupo.getIndicadorUso().compareTo(ConstantesSistema.SIM) == 0 
						&& grupo.getIndicadorSuperintendencia().compareTo(
								ConstantesSistema.INDICADOR_SUPERINTENDENCIA) == 0){
					
					grupoSuperintendencia = usuarioGrupo.getGrupo();
				}
			}
			
			if(grupoSuperintendencia != null){
				
				filtroSolicitacaoAcessoSituacao.adicionarParametro(
						new ParametroSimples(FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, 
								SolicitacaoAcessoSituacao.AG_CADASTRAMENTO));
				filtroSolicitacaoAcessoSituacao.adicionarParametro(
						new ParametroSimples(FiltroSolicitacaoAcessoSituacao.INDICADOR_USO, ConstantesSistema.SIM));
				
				Collection colecaoSolicitacaoAcessoSituacao = this.getFachada().pesquisar(filtroSolicitacaoAcessoSituacao, 
						SolicitacaoAcessoSituacao.class.getName());
				
				if(!Util.isVazioOrNulo(colecaoSolicitacaoAcessoSituacao)){
					
					SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = 
						(SolicitacaoAcessoSituacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoAcessoSituacao);
					
					solicitacaoAcesso.setSolicitacaoAcessoSituacao(solicitacaoAcessoSituacao);
					solicitacaoAcesso.setDataAutorizacao(new Date());
				}
			}else{
				
				filtroSolicitacaoAcessoSituacao.adicionarParametro(
						new ParametroSimples(FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, 
								SolicitacaoAcessoSituacao.AG_AUTORIZACAO_SUP));
				filtroSolicitacaoAcessoSituacao.adicionarParametro(
						new ParametroSimples(FiltroSolicitacaoAcessoSituacao.INDICADOR_USO, ConstantesSistema.SIM));
				
				Collection colecaoSolicitacaoAcessoSituacao = this.getFachada().pesquisar(filtroSolicitacaoAcessoSituacao, 
						SolicitacaoAcessoSituacao.class.getName());
				if(!Util.isVazioOrNulo(colecaoSolicitacaoAcessoSituacao)){
					
					SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = 
						(SolicitacaoAcessoSituacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoAcessoSituacao);
					
					solicitacaoAcesso.setSolicitacaoAcessoSituacao(solicitacaoAcessoSituacao);
				}
			}
		}
		
		// Grupo
		if (form.getIdsGrupo() == null || form.getIdsGrupo().equals("-1") ){
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Grupo");
		}
		
		// Data Solicita��o
		solicitacaoAcesso.setDataSolicitacao(new Date());
		
		// Ultima Altera��o
		solicitacaoAcesso.setUltimaAlteracao(new Date());
		
		if(solicitacaoAcesso.getCpf() != null){
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();

			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.CPF, solicitacaoAcesso.getCpf()));

			Collection colecaoUsuarioComCpf = Fachada.getInstancia().pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (!colecaoUsuarioComCpf.isEmpty()) {
				Usuario usuarioComCpf = (Usuario) colecaoUsuarioComCpf
						.iterator().next();

				throw new ActionServletException(
						"atencao.cpf.usuario.ja_cadastrado", null, ""
								+ usuarioComCpf.getId());
			}
		}
		
		if(solicitacaoAcesso.getLogin() != null){
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();

			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.LOGIN, solicitacaoAcesso.getLogin()));

			Collection colecaoUsuarioComCpf = Fachada.getInstancia().pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (!colecaoUsuarioComCpf.isEmpty()) {
				Usuario usuarioComCpf = (Usuario) colecaoUsuarioComCpf
						.iterator().next();

				throw new ActionServletException(
						"atencao.usuario.login.ja.existe", null, ""
								+ usuarioComCpf.getLogin());
			}
		}
		
		if(solicitacaoAcesso.getEmail() != null){
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();

			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.EMAIL, solicitacaoAcesso.getEmail()));

			Collection colecaoUsuarioComCpf = Fachada.getInstancia().pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (!colecaoUsuarioComCpf.isEmpty()) {
				Usuario usuarioComCpf = (Usuario) colecaoUsuarioComCpf
						.iterator().next();

				throw new ActionServletException(
						"atencao.usuario.email.ja.existe", null, ""
								+ usuarioComCpf.getDescricaoEmail());
			}
		}
		
		if(solicitacaoAcesso.getFuncionario() != null){
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();

			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.FUNCIONARIO_ID, solicitacaoAcesso.getFuncionario().getId()));
			filtroUsuario.adicionarCaminhoParaCarregamentoEntidade(
					FiltroUsuario.FUNCIONARIO);

			Collection colecaoUsuarioComCpf = Fachada.getInstancia().pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (!colecaoUsuarioComCpf.isEmpty()) {
				Usuario usuarioComCpf = (Usuario) colecaoUsuarioComCpf
						.iterator().next();

				throw new ActionServletException(
						"atencao.usuario.funcionario.ja.existe", null, ""
								+ usuarioComCpf.getFuncionario().getNome());
			}
		}
		/* RM 3892 Implantar norma de senhas no GSAN */
		//Usuario Abrangencia
		if (form.getAbrangencia() != null && !form.getAbrangencia().equals("")){
			FiltroUsuarioAbrangencia filtroAbrangencia = new FiltroUsuarioAbrangencia(); 
			filtroAbrangencia.adicionarParametro(new ParametroSimples(FiltroUsuarioAbrangencia.ID, form.getAbrangencia()));
			Collection<UsuarioAbrangencia> colecaoUsuarioAbrangencia = fachada.pesquisar(
					filtroAbrangencia, UsuarioAbrangencia.class.getName());

			if (colecaoUsuarioAbrangencia != null
					&& !colecaoUsuarioAbrangencia.isEmpty()) {
				solicitacaoAcesso.setUsuarioAbrangencia((UsuarioAbrangencia) Util
						.retonarObjetoDeColecao(colecaoUsuarioAbrangencia));
			}
		}
		//Gerencia Regional
		if (form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals("")){
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, form.getGerenciaRegional()));

			Collection<GerenciaRegional> colecaoGerenciaRegional = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoGerenciaRegional != null
					&& !colecaoGerenciaRegional.isEmpty()) {
				solicitacaoAcesso.setGerenciaRegional((GerenciaRegional) Util
						.retonarObjetoDeColecao(colecaoGerenciaRegional));
			}
		}
		//Unidade de Neg�cio
		if (form.getUnidadeNegocio() != null && !form.getUnidadeNegocio().equals("")){
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.ID, form.getUnidadeNegocio()));

			Collection colecaoUnidadeNegocio = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocio != null
					&& !colecaoUnidadeNegocio.isEmpty()) {
				solicitacaoAcesso.setUnidadeNegocio((UnidadeNegocio) Util
						.retonarObjetoDeColecao(colecaoUnidadeNegocio));
			}
		}
		//Localidade
		if (form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				solicitacaoAcesso.setLocalidade((Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade));
			}
		}
		//Elo
		if (form.getIdElo()!= null && !form.getIdElo().equals("")){
			FiltroLocalidade filtroElo = new FiltroLocalidade();
			filtroElo.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdElo()));
			filtroElo.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_ELO, form.getIdElo()));
			Collection<Localidade> colecaoElo = fachada.pesquisar(filtroElo,
					Localidade.class.getName());

			if (colecaoElo != null && !colecaoElo.isEmpty()) {
				solicitacaoAcesso.setLocalidadeElo((Localidade) Util.retonarObjetoDeColecao(colecaoElo));
			}
		}
		/* FIM RM 3892 */
		
		return solicitacaoAcesso;

	}

}

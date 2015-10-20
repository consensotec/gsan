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
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.seguranca.acesso.Grupo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que exibe o menu
 * 
 * @author Administrador
 * @date 02/05/2006
 */
public class InserirUsuarioDadosGeraisAction extends GcomAction {

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

		InserirUsuarioDadosGeraisActionForm form = (InserirUsuarioDadosGeraisActionForm) actionForm;

		ActionForward retorno = actionMapping
				.findForward("gerenciadorProcesso");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Indicador de bloqueio funcionalidade usuario.
		//Recupara o indicador do sitema parametro para o bloqueio da funcionalidade do usuario.
		SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();
		  if(sistemaParametro.getIndicadorBloquearFunUsuario() != null 
					&& sistemaParametro.getIndicadorBloquearFunUsuario().equals(ConstantesSistema.INDICADOR_USO_ATIVO)){
				throw new ActionServletException(
						"atencao.usuario_nao_pode_ser_inserido_nesta_funcionalidade");
			}

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Usuario que vai ser cadastrado no sistema, usado s� nessa
		// funcionalidade
		Usuario usuarioCadastrar = (Usuario) sessao
				.getAttribute("usuarioCadastrar");

		if (usuarioCadastrar == null) {
			usuarioCadastrar = new Usuario();
		}
		
		//Campo data de nascimento n�o � obrigat�rio para usu�rios indicados como rotina batch e/ou internet
		if(form.getIndicadorUsuarioBatch() != 1 && form.getIndicadorUsuarioInternet() != 1){
			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
			
			String dataNascimento = form.getDataNascimento();
			
			Date dataNascimentoFormatada = null;
			
			try {
				dataNascimentoFormatada = dataFormatada.parse(dataNascimento);
			} catch (ParseException ex) {
				throw new ActionServletException("erro.sistema");
			}
			
			usuario.setDataNascimento(dataNascimentoFormatada);
			
			try {
				dataNascimentoFormatada = dataFormatada.parse(dataNascimento);
			} catch (ParseException ex) {
				throw new ActionServletException("erro.sistema");
			}
		}
		
		Fachada fachada = Fachada.getInstancia();
		
		
		//Campo CPF n�o � obrigat�rio para usu�rios indicados como rotina batch e/ou internet
		if(form.getIndicadorUsuarioBatch() != 1 && form.getIndicadorUsuarioInternet() != 1){
			String cpf =  form.getCpf();
			
			//O usu�rio � pessoa f�sica
			if (cpf != null && !cpf.trim().equalsIgnoreCase("")) {
				
				boolean igual = true;
				Integer i = 0;
				
//				Integer tam = cpf.length();
//				
//				while (i < tam - 1) {
//				if ((cpf.charAt(i)) == (cpf.charAt(i + 1))) {
//				i = i + 1;
//				} else {
//				igual = false;
//				}
//				i = i + 1;
//				}
				
				Integer tam = cpf.length() - 1;
				
				while ( i < tam ) {
					if ( (cpf.charAt(i)) != (cpf.charAt(i + 1)) ){
						igual = false;
						break;
					} else {
						i++;
					}
				}
				
				if (igual) {
					throw new ActionServletException("atencao.cpf_invalido");
				}
				
				FiltroUsuario filtroUsuario = new FiltroUsuario();
				
				filtroUsuario.adicionarParametro(new ParametroSimples(
						FiltroUsuario.CPF, cpf));
				
				Collection colecaoUsuarioComCpf = fachada.pesquisar(filtroUsuario,
						Usuario.class.getName());
				
				if (!colecaoUsuarioComCpf.isEmpty()) {
					Usuario usuarioComCpf = (Usuario) colecaoUsuarioComCpf
					.iterator().next();
					
					throw new ActionServletException(
							"atencao.cpf.usuario.ja_cadastrado", null, ""
							+ usuarioComCpf.getId());
				}
			}
			
		}
		
		if (!"".equals(form.getDataInicial())) {
			Date data = Util.converteStringParaDate(form.getDataInicial());
			if (data == null) {
				throw new ActionServletException("atencao.data.inicio.invalida");
			}
			if (data.getTime() > new Date(System.currentTimeMillis()).getTime()) {
				throw new ActionServletException(
						"atencao.data_inicial.posterior.hoje", null, Util
								.formatarData(new Date()));
			}
		}
		if (!"".equals(form.getDataFinal())) {
			Date data = Util.converteStringParaDate(form.getDataFinal());
			if (data == null) {
				throw new ActionServletException("atencao.data.final.invalida");
			}
			if (data.getTime() < new Date(System.currentTimeMillis()).getTime()) {
				throw new ActionServletException(
						"atencao.data_final.posterior.hoje", null, Util
								.formatarData(new Date()));
			}
		}
		if (!"".equals(form.getDataInicial())
				&& !"".equals(form.getDataFinal())) {
			Date dataInicial = Util.converteStringParaDate(form
					.getDataInicial());
			Date dataFinal = Util.converteStringParaDate(form.getDataFinal());

			if (dataFinal.getTime() < dataInicial.getTime()) {
				throw new ActionServletException(
						"atencao.data.intervalo.invalido", null, Util
								.formatarData(new Date()));
			}
			usuarioCadastrar.setDataCadastroInicio(dataInicial);
			usuarioCadastrar.setDataCadastroFim(dataFinal);
		}

		if (!"".equals(form.getLogin())
				&& !form.getLogin().equalsIgnoreCase(
						usuarioCadastrar.getLogin())) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.LOGIN, form.getLogin()));
			if (usuarioCadastrar.getId() != null) {
				filtroUsuario
						.adicionarParametro(new ParametroSimplesDiferenteDe(
								FiltroUsuario.ID, usuarioCadastrar.getId()));
			}
			Collection coll = Fachada.getInstancia().pesquisar(filtroUsuario,
					Usuario.class.getSimpleName());
			if (coll != null && !coll.isEmpty()) {
				throw new ActionServletException(
						"atencao.usuario.login.ja.existe", null, form
								.getLogin());
			}
		}

		if (!"".equals(form.getEmail())
				&& !form.getEmail().equalsIgnoreCase(
						usuarioCadastrar.getDescricaoEmail())) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.EMAIL, form.getEmail()));
			if (usuarioCadastrar.getId() != null) {
				filtroUsuario
						.adicionarParametro(new ParametroSimplesDiferenteDe(
								FiltroUsuario.ID, usuarioCadastrar.getId()));
			}
			Collection coll = Fachada.getInstancia().pesquisar(filtroUsuario,
					Usuario.class.getSimpleName());
			if (coll != null && !coll.isEmpty()) {
				throw new ActionServletException(
						"atencao.usuario.email.ja.existe", null, form
								.getEmail());
			}
		}
		
		//Verifica a exist�ncia de usuario BATCH caso necessite
		if(form.getIndicadorUsuarioBatch() == 1){
			Usuario usuarioBatch = Fachada.getInstancia().pesquisarUsuarioRotinaBatch();
			if(usuarioBatch != null){
				throw new ActionServletException(
						"atencao.usuario.rotina.batch.ja.existe", null, usuarioBatch.getNomeUsuario());
			}
		}
		
		//Verifica a exist�ncia de usuario INTERNET caso necessite
		if(form.getIndicadorUsuarioInternet() == 1){
			Usuario usuarioInternet = Fachada.getInstancia().pesquisarUsuarioInternet();
			if(usuarioInternet != null){
				throw new ActionServletException(
						"atencao.usuario.internet.ja.existe", null, usuarioInternet.getNomeUsuario());
			}
		}

		// valida os campos obrigat�rios do usuario tipo
		if (!"".equals(form.getUsuarioTipo())) {

			FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();
			filtroUsuarioTipo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioTipo.ID, form.getUsuarioTipo()));
			Collection coll = Fachada.getInstancia().pesquisar(
					filtroUsuarioTipo, UsuarioTipo.class.getSimpleName());
			if (coll != null && !coll.isEmpty()) {
				UsuarioTipo usuarioTipo = (UsuarioTipo) Util
						.retonarObjetoDeColecao(coll);

				// caso n�o seja usuario tipo adiministrador ent�o valida os
				// campos
				if (!usuarioTipo.getId().equals(
						UsuarioTipo.USUARIO_TIPO_ADMINISTRADOR)) {

					// valida os campos obrigatorios
					if (usuarioTipo.getIndicadorFuncionario() == UsuarioTipo.INDICADOR_FUNCIONARIO) {
						if (form.getEmpresa() == null
								|| form.getEmpresa().equals("")) {
							throw new ActionServletException(
									"atencao.required", null, "Empresa");
						}

						// matricula do funcion�rio � obrigat�rio
						if (form.getIdFuncionario() == null
								|| form.getIdFuncionario().equals("")) {
							throw new ActionServletException(
									"atencao.required", null,
									"Matr�cula Funcion�rio");
						} else {
							if (form.getIdFuncionario() != null){
//									&& (form.getNomeFuncionario() == null || form
//											.getNomeFuncionario().equals(""))) {
								FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
								filtroFuncionario
										.adicionarParametro(new ParametroSimples(
												FiltroFuncionario.ID, form
														.getIdFuncionario()));
								filtroFuncionario
										.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_ORGANIZACIONAL);
								Collection colecaoFuncionario = Fachada
										.getInstancia().pesquisar(
												filtroFuncionario,
												Funcionario.class
														.getSimpleName());
								if (colecaoFuncionario != null
										&& !colecaoFuncionario.isEmpty()) {
									Funcionario f = (Funcionario) colecaoFuncionario
											.iterator().next();
									
									//Verifica se o funcionario ja esta cadastrado como usu�rio
									//pela matr�cula
									FiltroUsuario filtroUsuario = new FiltroUsuario();
									filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.FUNCIONARIO_ID, f.getId()));
									Collection colecaoFuncUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
									if(!colecaoFuncUsuario.isEmpty()){
										throw new ActionServletException(
												"atencao.usuario.funcionario.ja.existe", null,
												f.getNome());
									}
									usuarioCadastrar.setFuncionario(f);
									form.setIdFuncionario(f.getId().toString());
									form.setNomeFuncionario(f.getNome());
									usuarioCadastrar
											.setNomeUsuario(f.getNome());
								} else {
									throw new ActionServletException(
											"atencao.required", null,
											"Matr�cula Funcion�rio");
								}
							}
						}
						// nome do funcionario � obrigatorio
						if (form.getNome() == null || form.getNome().equals("")) {
							throw new ActionServletException(
									"atencao.required", null, "Nome Usu�rio");
						}

						//CPF do funcionario � obrigatorio para usu�rio diferentes de rotina batch e/ou internet
						if(form.getIndicadorUsuarioBatch() != 1 && form.getIndicadorUsuarioInternet() != 1){
							if (form.getCpf() == null || form.getCpf().equals("")) {
								throw new ActionServletException(
										"atencao.required", null, "N�mero do CPF");
							}
						}
						
						//Indicador indicadorUsuarioBatch � obrigatorio
						if (form.getIndicadorUsuarioBatch() == null
								|| form.getIndicadorUsuarioBatch().equals("")) {
							throw new ActionServletException(
									"atencao.required", null,
									"Indicador para rotina batch");
						}
						//Indicador indicadorUsuarioInternet � obrigatorio
						if (form.getIndicadorUsuarioInternet() == null
								|| form.getIndicadorUsuarioInternet().equals("")) {
							throw new ActionServletException(
									"atencao.required", null,
									"Indicador para internet");
						}
						
					} else {
						if (usuarioTipo.getIndicadorFuncionario() != UsuarioTipo.INDICADOR_FUNCIONARIO) {
							// data inicio e data fim � obrigatorio
							if (form.getEmpresa() == null
									|| form.getEmpresa().equals("")) {
								throw new ActionServletException(
										"atencao.required", null, "Empresa");
							}
							// data inicio e data fim � obrigatorio
							if (form.getNome() == null
									|| form.getNome().equals("")) {
								throw new ActionServletException(
										"atencao.required", null,
										"Nome Usu�rio");
							}
							
							//CPF do funcionario � obrigatorio para usu�rio diferentes de rotina batch e/ou internet
							if(form.getIndicadorUsuarioBatch() != 1 && form.getIndicadorUsuarioInternet() != 1){
								if (form.getCpf() == null
										|| form.getCpf().equals("")) {
									throw new ActionServletException(
											"atencao.required", null,
									"N�mero do CPF");
								}
							}
							
							// data inicio e data fim � obrigatorio
							if (form.getIdLotacao() == null
									|| form.getIdLotacao().equals("")) {
								throw new ActionServletException(
										"atencao.required", null,
										"Unidade Lota��o");
							}
							// data inicio e data fim � obrigatorio
							if (form.getDataInicial() == null
									|| form.getDataInicial().equals("")
									|| form.getDataFinal() == null
									|| form.getDataFinal().equals("")) {
								throw new ActionServletException(
										"atencao.required", null,
										"Per�odo de Cadastramento");
							}
							
							//Indicador indicadorUsuarioBatch � obrigatorio
							if (form.getIndicadorUsuarioBatch() == null
									|| form.getIndicadorUsuarioBatch().equals("")) {
								throw new ActionServletException(
										"atencao.required", null,
										"Indicador para rotina batch");
							}
							//Indicador indicadorUsuarioInternet � obrigatorio
							if (form.getIndicadorUsuarioInternet() == null
									|| form.getIndicadorUsuarioInternet().equals("")) {
								throw new ActionServletException(
										"atencao.required", null,
										"Indicador para internet");
							}
							
						}
					}
				}
				usuarioCadastrar.setUsuarioTipo(usuarioTipo);
			} else {
				usuarioCadastrar.setUsuarioTipo(null);
			}

		} else {
			throw new ActionServletException("atencao.required", null,
					"Usu�rio Tipo");
		}
		if (!"".equals(form.getEmpresa())) {
			if (!(usuarioCadastrar.getEmpresa() != null
					&& usuarioCadastrar.getEmpresa().getId() != null && usuarioCadastrar
					.getEmpresa().getId().toString().equals(form.getEmpresa()))) {

				FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
				filtroEmpresa.adicionarParametro(new ParametroSimples(
						FiltroEmpresa.ID, form.getEmpresa()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroEmpresa, Empresa.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioCadastrar.setEmpresa((Empresa) coll.iterator()
							.next());
				} else {
					usuarioCadastrar.setEmpresa(null);
				}
			}
		} else {
			usuarioCadastrar.setEmpresa(null);
		}

		if (!"".equals(form.getIdFuncionario())) {
			if (!(usuarioCadastrar.getFuncionario() != null
					&& usuarioCadastrar.getFuncionario().getId() != null
					&& usuarioCadastrar.getFuncionario().getId().toString()
							.equals(form.getIdFuncionario()) && usuarioCadastrar
					.getFuncionario().getNome().equals(
							form.getNomeFuncionario()))) {

				FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
				filtroFuncionario.adicionarParametro(new ParametroSimples(
						FiltroFuncionario.ID, form.getIdFuncionario()));
				filtroFuncionario
						.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_ORGANIZACIONAL_ID);
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroFuncionario, Funcionario.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioCadastrar.setFuncionario((Funcionario) coll
							.iterator().next());
					form.setNomeFuncionario(((Funcionario) coll.iterator()
							.next()).getNome());
				} else {
					usuarioCadastrar.setFuncionario(null);
				}
			}
		} else {
			usuarioCadastrar.setFuncionario(null);
		}

		// valida a unidade de lota��o
		if (!"".equals(form.getIdLotacao())) {
			if (!(usuarioCadastrar.getUnidadeOrganizacional() != null
					&& usuarioCadastrar.getUnidadeOrganizacional().getId() != null
					&& usuarioCadastrar.getUnidadeOrganizacional().getId()
							.toString().equals(form.getIdLotacao()) && usuarioCadastrar
					.getUnidadeOrganizacional().getDescricao().equals(
							form.getNomeLotacao()))) {

				FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();
				filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
						FiltroUnidadeOrganizacional.ID, form.getIdLotacao()));
				filtroUnidadeEmpresa
						.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroUnidadeEmpresa,
						UnidadeOrganizacional.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					UnidadeOrganizacional unidadeEmpresa = (UnidadeOrganizacional) coll
							.iterator().next();

					// caso o usu�rio que esteja efetuando a inser��o n�o
					// seja
					// do grupo de administradores
					FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();

					filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
							FiltroUsuarioGrupo.USUARIO_ID, usuario.getId()));
					filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
							FiltroUsuarioGrupo.GRUPO_ID, Grupo.ADMINISTRADOR));
					Collection colecaoUsuarioGrupo = Fachada.getInstancia()
							.pesquisar(filtroUsuarioGrupo,
									UsuarioGrupo.class.getName());
					if (colecaoUsuarioGrupo == null
							|| colecaoUsuarioGrupo.isEmpty()) {
						// se a unidade de lotacao do usuario que estiver
						// efetuando seja diferente da unidade de
						// lota��o informada
						if (usuario.getUnidadeOrganizacional() != null
								&& usuario.getUnidadeOrganizacional().getId() != null
								&& !usuario
										.getUnidadeOrganizacional()
										.getId()
										.equals(
												new Integer(form.getIdLotacao()))) {
							// recupera a unidade do usu�rio
							FiltroUnidadeOrganizacional filtroUnidadeEmpresaUsuario = new FiltroUnidadeOrganizacional();
							filtroUnidadeEmpresaUsuario
									.adicionarParametro(new ParametroSimples(
											FiltroUnidadeOrganizacional.ID,
											usuario.getUnidadeOrganizacional()
													.getId()));
							filtroUnidadeEmpresaUsuario
									.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
							Collection colecaoUnidadeEmpresa = Fachada
									.getInstancia().pesquisar(
											filtroUnidadeEmpresaUsuario,
											UnidadeOrganizacional.class
													.getName());
							UnidadeOrganizacional unidadeEmpresaUsuario = null;
							if (colecaoUnidadeEmpresa != null
									&& !colecaoUnidadeEmpresa.isEmpty()) {
								unidadeEmpresaUsuario = (UnidadeOrganizacional) Util
										.retonarObjetoDeColecao(colecaoUnidadeEmpresa);
							}
							// se o nivel da unidade de lota��o do usu�rio
							// que
							// estiver efetuando a inser��o seja maior ou
							// igual
							// ao nivel de unidade de lota��o informada
							if (unidadeEmpresaUsuario != null) {
								if (unidadeEmpresaUsuario.getUnidadeTipo()
										.getNivel() != null
										&& unidadeEmpresa.getUnidadeTipo()
												.getNivel() != null) {
									if (unidadeEmpresaUsuario.getUnidadeTipo()
											.getNivel().intValue() >= unidadeEmpresa
											.getUnidadeTipo().getNivel()
											.intValue()) {
										throw new ActionServletException(
												"atencao.usuario.sem.permissao",
												usuario.getLogin(),
												unidadeEmpresa.getDescricao());
									}
								} else {
									throw new ActionServletException(
											"atencao.usuario.sem.permissao",
											usuario.getLogin(),
											unidadeEmpresa.getDescricao());
								}

								// ou a unidade superior da unidade de
								// lota��o
								// informada seja diferente da unidade de
								// lota��o do usu�rio

								// enquanto o nivel superior da unidade de
								// lota��o n�o esteja no mesmo nivel da
								// unidade
								// de lota��o do usu�rio
								boolean mesmoNivel = true;
								int idNivelUsuario = unidadeEmpresaUsuario
										.getUnidadeTipo().getNivel().intValue();
								UnidadeOrganizacional unidadeEmpresaSuperior = null;
								while (mesmoNivel) {
									Integer idUnidadeEmpresaSuperior = null;
									if (unidadeEmpresaSuperior == null) {
										if (unidadeEmpresa.getUnidadeSuperior() != null
												&& !unidadeEmpresa
														.getUnidadeSuperior()
														.equals("")) {
											idUnidadeEmpresaSuperior = unidadeEmpresa
													.getUnidadeSuperior()
													.getId();
										}
									} else {
										if (unidadeEmpresaSuperior
												.getUnidadeSuperior() != null
												&& !unidadeEmpresaSuperior
														.getUnidadeSuperior()
														.equals("")) {
											idUnidadeEmpresaSuperior = unidadeEmpresaSuperior
													.getUnidadeSuperior()
													.getId();
										}
									}
									if (idUnidadeEmpresaSuperior == null) {
										throw new ActionServletException(
												"atencao.usuario.sem.permissao",
												usuario.getLogin(),
												unidadeEmpresa.getDescricao());
									}
									// recupera a unidade do usu�rio
									FiltroUnidadeOrganizacional filtroUnidadeEmpresaSuperior = new FiltroUnidadeOrganizacional();
									filtroUnidadeEmpresaSuperior
											.adicionarParametro(new ParametroSimples(
													FiltroUnidadeOrganizacional.ID,
													idUnidadeEmpresaSuperior));
									filtroUnidadeEmpresaSuperior
											.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
									Collection colecaoUnidadeEmpresaSuperior = Fachada
											.getInstancia()
											.pesquisar(
													filtroUnidadeEmpresaSuperior,
													UnidadeOrganizacional.class
															.getName());
									if (colecaoUnidadeEmpresaSuperior != null
											&& !colecaoUnidadeEmpresaSuperior
													.isEmpty()) {
										unidadeEmpresaSuperior = (UnidadeOrganizacional) Util
												.retonarObjetoDeColecao(colecaoUnidadeEmpresaSuperior);
									}
									if (unidadeEmpresaSuperior != null) {
										if (unidadeEmpresaSuperior
												.getUnidadeTipo().getNivel() == null
												|| unidadeEmpresaSuperior
														.getUnidadeTipo()
														.getNivel().equals("")) {
											throw new ActionServletException(
													"atencao.usuario.sem.permissao",
													usuario.getLogin(),
													unidadeEmpresa.getDescricao());

										}
										// caso seja o mesmo nivel
										if (unidadeEmpresaSuperior
												.getUnidadeTipo().getNivel()
												.intValue() == idNivelUsuario) {
											mesmoNivel = false;
											// caso o id da unidade empresa
											// informado for diferente do id
											// da
											// unidade empresa do usu�rio no
											// mesmo nivel
											if (!unidadeEmpresaSuperior.getId()
													.equals(
															unidadeEmpresaUsuario
																	.getId())) {
												throw new ActionServletException(
														"atencao.usuario.sem.permissao",
														usuario.getLogin(),
														unidadeEmpresa.getDescricao());
											}

										}
									}
								}

							}

						}
					}
					short indicadorFuncionario = new Short(form
							.getIndicadorFuncionario());
					if (form.getUsuarioTipo() != null
							&& indicadorFuncionario != UsuarioTipo.INDICADOR_FUNCIONARIO) {
						usuarioCadastrar
								.setUnidadeOrganizacional(unidadeEmpresa);
					} else {
						usuarioCadastrar.setUnidadeOrganizacional(null);
					}
				} else {
					usuarioCadastrar.setUnidadeOrganizacional(null);
				}
			}
		} else {
			usuarioCadastrar.setUnidadeOrganizacional(null);
		}

		
		if (!"".equals(form.getNome())) {
			usuarioCadastrar.setNomeUsuario(form.getNome().toUpperCase());
		}
		//Adiciona CPF apenas para usu�rios diferentes de batch e/ou internet
		if (form.getIndicadorUsuarioBatch() != 1 && form.getIndicadorUsuarioInternet() != 1 && !"".equals(form.getCpf())) {
			usuarioCadastrar.setCpf(form.getCpf().toUpperCase());
		}
		//Adiciona data de nascimento apenas para usu�rios diferentes de batch e/ou internet
		if (form.getIndicadorUsuarioBatch() != 1 && form.getIndicadorUsuarioInternet() != 1 && !"".equals(form.getDataNascimento())) 
			usuarioCadastrar.setDataNascimento(Util
					.converteStringParaDate(form.getDataNascimento()));
		
		if (!"".equals(form.getDataInicial()))
			usuarioCadastrar.setDataCadastroInicio(Util
					.converteStringParaDate(form.getDataInicial()));
		if (!"".equals(form.getDataFinal()))
			usuarioCadastrar.setDataCadastroFim(Util
					.converteStringParaDate(form.getDataFinal()));
		if (!"".equals(form.getLogin()))
			usuarioCadastrar.setLogin(form.getLogin());
		if (!"".equals(form.getEmail()))
			usuarioCadastrar.setDescricaoEmail(form.getEmail());
		
		if (!"".equals(form.getIndicadorUsuarioBatch()))
			usuarioCadastrar.setIndicadorUsuarioBatch(form.getIndicadorUsuarioBatch());
		if (!"".equals(form.getIndicadorUsuarioInternet()))
			usuarioCadastrar.setIndicadorUsuarioInternet(form.getIndicadorUsuarioInternet());
		
		sessao.setAttribute("usuarioCadastrar", usuarioCadastrar);

		sessao.setAttribute("usuario", usuario);
		

		return retorno;
	}
}
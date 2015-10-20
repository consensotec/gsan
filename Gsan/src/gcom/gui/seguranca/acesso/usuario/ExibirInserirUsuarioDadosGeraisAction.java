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
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInserirUsuarioDadosGeraisAction extends GcomAction {

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

		// FachadaBatch.getInstancia().gerarResumoSituacaoEspecialFaturamento();

		ActionForward retorno = actionMapping.findForward("inserirDadosGerais");

		InserirUsuarioDadosGeraisActionForm form = (InserirUsuarioDadosGeraisActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// Usuario que vai ser cadastrado no sistema, usado s� nessa
		// funcionalidade
		Usuario usuarioCadastrar = (Usuario) sessao.getAttribute("usuarioCadastrar");
		
		
		// Caso a funcionalidade origem for Cadastrar Solicita��o de Acesso,
		// carregar a tela com os dados da solicita��o.
		String idSolicitacaoAcesso = (String) httpServletRequest.getAttribute("idSolicitacaoAcesso");
		if(idSolicitacaoAcesso != null){
			form = this.setSolicitacaoAcesso(form, sessao, idSolicitacaoAcesso);
		}
		
		if (usuarioCadastrar == null) {
			usuarioCadastrar = new Usuario();
		}

		if (usuarioCadastrar != null) {

			if ("".equals(form.getUsuarioTipo())) {

				// se o form nao tiver o usuario tipo
				if (usuarioCadastrar.getUsuarioTipo() != null && usuarioCadastrar.getUsuarioTipo().getId() != null) {
					form.setUsuarioTipo(usuarioCadastrar.getUsuarioTipo().getId().toString());
				}

			} else if(idSolicitacaoAcesso == null){

				UsuarioTipo usuarioTipo = new UsuarioTipo();
				usuarioTipo.setId(new Integer(form.getUsuarioTipo()));
				usuarioTipo.setIndicadorFuncionario(new Short(form.getIndicadorFuncionario()));
				usuarioCadastrar.setUsuarioTipo(usuarioTipo);

			}

			if (usuarioCadastrar.getEmpresa() != null && usuarioCadastrar.getEmpresa().getId() != null 
					&& idSolicitacaoAcesso == null) {
				
				form.setEmpresa(usuarioCadastrar.getEmpresa().getId().toString());
			}

			if ("".equals(form.getIdFuncionario()) ) {
				
				if (usuarioCadastrar.getFuncionario() != null && idSolicitacaoAcesso == null) {
					
					if (usuarioCadastrar.getFuncionario().getId() != null)
						
						form.setIdFuncionario(usuarioCadastrar.getFuncionario()
								.getId().toString());
					
					if (usuarioCadastrar.getFuncionario().getNome() != null)
						form.setNomeFuncionario(usuarioCadastrar.getFuncionario().getNome());
				}
			} else if (form.getIdFuncionario() != null
					&& (form.getNomeFuncionario() == null || form
							.getNomeFuncionario().equals("")) ) {

				FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
				filtroFuncionario.adicionarParametro(new ParametroSimples(
						FiltroFuncionario.ID, form.getIdFuncionario()));

				filtroFuncionario
						.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_ORGANIZACIONAL);
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroFuncionario, Funcionario.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					Funcionario f = (Funcionario) coll.iterator().next();
					usuarioCadastrar.setFuncionario(f);
					form.setIdFuncionario(f.getId().toString());
					form.setNomeFuncionario(f.getNome());
					form.setLogin(f.getId().toString());
					form.setFuncionarioNaoEncontrada("false");
				} else {
					usuarioCadastrar.setFuncionario(null);
					form.setIdFuncionario("");
					form.setNomeFuncionario("Funcionario inexistente.");
					form.setFuncionarioNaoEncontrada("true");
					form.setLogin("");
				}
			}
			

			//Verifica se o funcionario ja esta cadastrado como usu�rio
			//pela matr�cula
			if(usuarioCadastrar.getFuncionario() != null ){
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
					
					FiltroUsuario filtroUsuario = new FiltroUsuario();
					filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.FUNCIONARIO_ID, f.getId()));
					
					Collection colecaoFuncUsuario = 
						Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
					
					 
					
					if(!colecaoFuncUsuario.isEmpty()){
						
						Usuario usuarioPesquisa = 
							(Usuario) Util.retonarObjetoDeColecao(colecaoFuncUsuario);
						
						String nome = 
							f.getNome() +
							" de login " +
							usuarioPesquisa.getLogin();
						
						throw new ActionServletException(
							"atencao.usuario.funcionario.ja.existe", 
							null,
							nome);
					}
					
					usuarioCadastrar.setFuncionario(f);
					usuarioCadastrar.setNomeUsuario(f.getNome());
					
					form.setIdFuncionario(f.getId().toString());
					form.setNomeFuncionario(f.getNome());
					
				} else {
					throw new ActionServletException(
						"atencao.required", 
						null,
						"Matr�cula Funcion�rio");
				}
			}
			
			// caso for funcionario pegar os dados a partir do funcionario
			if (usuarioCadastrar != null
					&& usuarioCadastrar.getUsuarioTipo() != null
					&& usuarioCadastrar.getUsuarioTipo().getId() != null
					&& usuarioCadastrar.getUsuarioTipo()
							.getIndicadorFuncionario() == UsuarioTipo.INDICADOR_FUNCIONARIO) {

				if (usuarioCadastrar.getFuncionario() != null) {

					if (usuarioCadastrar.getFuncionario().getNome() != null)
						form.setNome(usuarioCadastrar.getFuncionario()
								.getNome());
					
					if(usuarioCadastrar.getFuncionario().getNumeroCpf() != null){
						form.setCpf(usuarioCadastrar.getFuncionario()
								.getNumeroCpf());
					}
					
					if(usuarioCadastrar.getFuncionario().getDataNascimento() != null){
						form.setDataNascimento(Util.formatarData(usuarioCadastrar.getFuncionario()
								.getDataNascimento()));
					}

					if (usuarioCadastrar.getFuncionario()
							.getUnidadeOrganizacional() != null && (form.getIdLotacao() == null || form.getIdLotacao().equals(""))) {
						if (usuarioCadastrar.getFuncionario()
								.getUnidadeOrganizacional().getId() != null)
							form.setIdLotacao(usuarioCadastrar.getFuncionario()
									.getUnidadeOrganizacional().getId()
									.toString());
						if (usuarioCadastrar.getFuncionario()
								.getUnidadeOrganizacional().getDescricao() != null)
							form.setNomeLotacao(usuarioCadastrar
									.getFuncionario()
									.getUnidadeOrganizacional().getDescricao());
					}
				}

			} else {

				if ("".equals(form.getIdLotacao())) {
					if (usuarioCadastrar.getUnidadeOrganizacional() != null) {
						if (usuarioCadastrar.getUnidadeOrganizacional().getId() != null)
							form.setIdLotacao(usuarioCadastrar
									.getUnidadeOrganizacional().getId()
									.toString());
						if (usuarioCadastrar.getUnidadeOrganizacional()
								.getDescricao() != null)
							form.setNomeLotacao(usuarioCadastrar
									.getUnidadeOrganizacional().getDescricao());
					}
				} else if (form.getIdLotacao() != null
						&& !form.getIdLotacao().equals("")
						&& (usuarioCadastrar.getUnidadeOrganizacional() == null || (usuarioCadastrar
								.getUnidadeOrganizacional() != null
								&& usuarioCadastrar.getUnidadeOrganizacional()
										.getId() != null && !form
								.getIdLotacao().equals(
										usuarioCadastrar
												.getUnidadeOrganizacional()
												.getId().toString())))) {

					FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();
					filtroUnidadeEmpresa
							.adicionarParametro(new ParametroSimples(
									FiltroUnidadeOrganizacional.ID, form
											.getIdLotacao()));
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

						filtroUsuarioGrupo
								.adicionarParametro(new ParametroSimples(
										FiltroUsuarioGrupo.USUARIO_ID, usuario
												.getId()));
						filtroUsuarioGrupo
								.adicionarParametro(new ParametroSimples(
										FiltroUsuarioGrupo.GRUPO_ID,
										Grupo.ADMINISTRADOR));
						Collection colecaoUsuarioGrupo = Fachada.getInstancia()
								.pesquisar(filtroUsuarioGrupo,
										UsuarioGrupo.class.getName());
						if (colecaoUsuarioGrupo == null
								|| colecaoUsuarioGrupo.isEmpty()) {
							// se a unidade de lotacao do usuario que estiver
							// efetuando seja diferente da unidade de
							// lota��o informada
							if (usuario.getUnidadeOrganizacional() != null
									&& usuario.getUnidadeOrganizacional()
											.getId() != null
									&& !usuario.getUnidadeOrganizacional()
											.getId().equals(
													new Integer(form
															.getIdLotacao()))) {
								// recupera a unidade do usu�rio
								FiltroUnidadeOrganizacional filtroUnidadeEmpresaUsuario = new FiltroUnidadeOrganizacional();
								filtroUnidadeEmpresaUsuario
										.adicionarParametro(new ParametroSimples(
												FiltroUnidadeOrganizacional.ID,
												usuario
														.getUnidadeOrganizacional()
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
										if (unidadeEmpresaUsuario
												.getUnidadeTipo().getNivel()
												.intValue() >= unidadeEmpresa
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
											.getUnidadeTipo().getNivel()
											.intValue();
									UnidadeOrganizacional unidadeEmpresaSuperior = null;
									while (mesmoNivel) {
										Integer idUnidadeEmpresaSuperior = null;
										if (unidadeEmpresaSuperior == null) {
											if (unidadeEmpresa
													.getUnidadeSuperior() != null
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
													.getUnidadeTipo()
													.getNivel() == null
													|| unidadeEmpresaSuperior
															.getUnidadeTipo()
															.getNivel().equals(
																	"")) {
												throw new ActionServletException(
														"atencao.usuario.sem.permissao",
														usuario.getLogin(),
														unidadeEmpresa.getDescricao());

											}
											// caso seja o mesmo nivel
											if (unidadeEmpresaSuperior
													.getUnidadeTipo()
													.getNivel().intValue() == idNivelUsuario) {
												mesmoNivel = false;
												// caso o id da unidade empresa
												// informado for diferente do id
												// da
												// unidade empresa do usu�rio no
												// mesmo nivel
												if (!unidadeEmpresaSuperior
														.getId()
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

						usuarioCadastrar
								.setUnidadeOrganizacional(unidadeEmpresa);
						form.setIdLotacao(unidadeEmpresa.getId().toString());
						form.setNomeLotacao(unidadeEmpresa.getDescricao());
						form.setLotacaoNaoEncontrada("false");
					} else {
						usuarioCadastrar.setUnidadeOrganizacional(null);
						form.setIdLotacao("");
						form.setNomeLotacao("Lota��o inexistente.");
						form.setLotacaoNaoEncontrada("true");
					}
				}
				if (usuarioCadastrar.getNomeUsuario() != null)
					form.setNome(usuarioCadastrar.getNomeUsuario());
				if (usuarioCadastrar.getDataCadastroInicio() != null)
					form.setDataInicial(Util.formatarData(usuarioCadastrar
							.getDataCadastroInicio()));
				if (usuarioCadastrar.getDataCadastroFim() != null)
					form.setDataFinal(Util.formatarData(usuarioCadastrar
							.getDataCadastroFim()));

			}

			if (usuarioCadastrar.getLogin() != null)
				form.setLogin(usuarioCadastrar.getLogin());
			if (usuarioCadastrar.getDescricaoEmail() != null)
				form.setEmail(usuarioCadastrar.getDescricaoEmail());
		}

		if (sessao.getAttribute("collEmpresa") == null
				|| !sessao.getAttribute("collEmpresa").equals("")) {

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection coll = Fachada.getInstancia().pesquisar(filtroEmpresa,
					Empresa.class.getSimpleName());
			if (coll == null || coll.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						" Empresa est� ");
			}

			sessao.setAttribute("collEmpresa", coll);
		}

		if (sessao.getAttribute("collUsuarioTipo") == null
				|| !sessao.getAttribute("collUsuarioTipo").equals("")) {

			FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();

			// caso o usu�rio n�o seja administrador ent�o
			if (!usuario.getUsuarioTipo().getId().equals(
					UsuarioTipo.USUARIO_TIPO_ADMINISTRADOR)) {
				// n�o seta na cole��o de usu�rio tipo o tipo administrador
				filtroUsuarioTipo
						.adicionarParametro(new ParametroSimplesDiferenteDe(
								FiltroUsuarioTipo.ID,
								UsuarioTipo.USUARIO_TIPO_ADMINISTRADOR));
			}

			filtroUsuarioTipo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection coll = Fachada.getInstancia().pesquisar(
					filtroUsuarioTipo, UsuarioTipo.class.getSimpleName());
			if (coll == null || coll.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						" Tipo do Us�ario est� ");
			}

			sessao.setAttribute("collUsuarioTipo", coll);
		}

		sessao.setAttribute("usuarioCadastrar", usuarioCadastrar);

		sessao.setAttribute("usuario", usuario);
		
		sessao.setAttribute("idSolicitacaoAcesso", idSolicitacaoAcesso);
		
		//DATA CORRENTE
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        httpServletRequest.setAttribute("dataAtual", formatoData
        .format(dataCorrente.getTime()));
        
        //IDADE M�NIMA
        httpServletRequest.setAttribute("idadeMinimaFuncionario", ConstantesSistema.IDADE_MINIMA_FUNCIONARIO);
        httpServletRequest.setAttribute("idadeMinimaUsuario", ConstantesSistema.IDADE_MINIMA_USUARIO);

		return retorno;
	}
	
	/**
	 * @author Hugo Leonardo
	 * @date 22/11/2010
	 * 
	 * @param InserirUsuarioDadosGeraisActionForm, idSolicitacaoAcesso
	 * @return InserirUsuarioDadosGeraisActionForm
	 */
	private InserirUsuarioDadosGeraisActionForm setSolicitacaoAcesso(InserirUsuarioDadosGeraisActionForm form, 
			HttpSession sessao, String idSolicitacaoAcesso) {
		
		SolicitacaoAcesso solicitacaoAcesso = null;
		
		FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();
		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.SOLICITACAO_ACESSO_SITUACAO);
		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.FUNCIONARIO_RESPONSAVEL);
		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.FUNCIONARIO_SOLICITANTE);
		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.UNIDADE_ORGANIZACIONAL);
		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.EMPRESA);
		
		filtroSolicitacaoAcesso.adicionarParametro(
					new ParametroSimples(FiltroSolicitacaoAcesso.ID, new Integer(idSolicitacaoAcesso)));
		
		solicitacaoAcesso = (SolicitacaoAcesso) Fachada.getInstancia().pesquisar(filtroSolicitacaoAcesso, 
				SolicitacaoAcesso.class.getName()).iterator().next();
		
		// pesquisar Funcion�rio
		short indicadorFuncionario = ConstantesSistema.NAO;
		if(solicitacaoAcesso.getFuncionario() != null){
			
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, solicitacaoAcesso.getFuncionario().getId()));

			Collection<Funcionario> funcionarioPesquisado = Fachada.getInstancia().pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (funcionarioPesquisado != null && !funcionarioPesquisado.isEmpty()) {
				Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(funcionarioPesquisado);
				
				form.setIdFuncionario(funcionario.getId().toString());
				form.setNomeFuncionario(funcionario.getNome());
				indicadorFuncionario = ConstantesSistema.SIM;
			}
		}
		
		// Unidade de Lota��o
		if(solicitacaoAcesso.getUnidadeOrganizacional() != null){
			
			form.setIdLotacao(solicitacaoAcesso.getUnidadeOrganizacional().getId().toString());
			form.setNomeLotacao(solicitacaoAcesso.getUnidadeOrganizacional().getDescricao());
		}
		
		// Empresa
		form.setEmpresa(solicitacaoAcesso.getEmpresa().getId().toString());
		
		// Tipo de Usu�rio
		form.setUsuarioTipo(solicitacaoAcesso.getUsuarioTipo().getId().toString());
		
		// Tipo de Usu�rio
		form.setParmsUsuarioTipo(solicitacaoAcesso.getUsuarioTipo().getId().toString()+";"+indicadorFuncionario);
		
		form.setIndicadorFuncionario(""+indicadorFuncionario);

		// Nome Usu�rio
		form.setNome(solicitacaoAcesso.getNomeUsuario());
		
		// Login
		form.setLogin(solicitacaoAcesso.getLogin());
		
		// Email
		form.setEmail(solicitacaoAcesso.getEmail());
		
		// Confirma��o Email
		form.setConfirmacaoEmail(solicitacaoAcesso.getEmail());
		
		// Data Nascimento
		if(solicitacaoAcesso.getDataNascimento() != null){
			form.setDataNascimento( Util.formatarData(solicitacaoAcesso.getDataNascimento()));
		}
		
		// CPF
		if(Util.verificarNaoVazio(solicitacaoAcesso.getCpf())){
			form.setCpf(solicitacaoAcesso.getCpf());
		}
		
		// Per�odo de Cadastramento
		if(solicitacaoAcesso.getPeriodoInicial() != null
				&& solicitacaoAcesso.getPeriodoFinal() != null){
			
			form.setDataInicial( Util.formatarData(solicitacaoAcesso.getPeriodoInicial()));
			form.setDataFinal( Util.formatarData(solicitacaoAcesso.getPeriodoFinal()));
		}
		
		// Grupos
		FiltroSolicitacaoAcessoGrupo filtroSolicitacaoAcessoGrupo = new FiltroSolicitacaoAcessoGrupo();
		
		filtroSolicitacaoAcessoGrupo.setConsultaSemLimites(true);
		filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcessoGrupo.GRUPO);
		filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples( 
				FiltroSolicitacaoAcessoGrupo.SOLICITACAO_ACESSO_ID, solicitacaoAcesso.getId()) );
			
		Collection colecaoSolicitacaoAcessoGrupo = 
			Fachada.getInstancia().pesquisar(
					filtroSolicitacaoAcessoGrupo, SolicitacaoAcessoGrupo.class.getName());

		Collection<Integer> colecaoIdsGrupos = new ArrayList();
		
		if (colecaoSolicitacaoAcessoGrupo != null && !colecaoSolicitacaoAcessoGrupo.isEmpty()) {
			
			Iterator iterator = colecaoSolicitacaoAcessoGrupo.iterator();
			
			SolicitacaoAcessoGrupo solicitacaoAcessoGrupo = null;
			
			while (iterator.hasNext()) {
			
				solicitacaoAcessoGrupo = (SolicitacaoAcessoGrupo) iterator.next();
				
				colecaoIdsGrupos.add(solicitacaoAcessoGrupo.getComp_id().getGrupo().getId());
			}
		}
		
		if(!colecaoIdsGrupos.isEmpty()){
			
			Object[] array = colecaoIdsGrupos.toArray();
			String[] array2 = new String[array.length];
			
			for (int i = 0; i < array.length; i++){
				array2[i] = array[i].toString();
	        }
			
			sessao.setAttribute("grupo", array2);
		}
		
		return form;
	}
}
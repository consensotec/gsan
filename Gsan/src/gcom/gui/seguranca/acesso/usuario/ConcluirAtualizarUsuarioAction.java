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

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.seguranca.acesso.Grupo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
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
 * Action conclui o Manter Usuario
 * 
 * @author S�vio Luiz
 * @date 09/05/2006
 */
public class ConcluirAtualizarUsuarioAction extends GcomAction {

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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarUsuarioDadosGeraisActionForm form = (AtualizarUsuarioDadosGeraisActionForm) actionForm;

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		String processo = null;
		
		if(sessao.getAttribute("processo")!= null){
			processo = (String) sessao.getAttribute("processo");
		}

		// Usuario que vai ser cadastrado no sistema, usado s� nessa
		// funcionalidade
		Usuario usuarioParaAtualizar = (Usuario) sessao
				.getAttribute("usuarioParaAtualizar");


		// valida os campos obrigat�rios do usuario tipo
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
						// matricula do funcion�rio � obrigat�rio
						if (form.getIdFuncionario() == null
								|| form.getIdFuncionario().equals("")) {
							throw new ActionServletException(
									"atencao.required", null,
									"Matr�cula Funcion�rio");
						}
						FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
						filtroFuncionario
								.adicionarParametro(new ParametroSimples(
										FiltroFuncionario.ID, form
												.getIdFuncionario()));
						filtroFuncionario
							.adicionarCaminhoParaCarregamentoEntidade("empresa");
						filtroFuncionario
								.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_ORGANIZACIONAL);
						Collection colecaoFuncionario = Fachada
								.getInstancia().pesquisar(
										filtroFuncionario,
										Funcionario.class.getSimpleName());
						if (colecaoFuncionario != null
								&& !colecaoFuncionario.isEmpty()) {
							Funcionario f = (Funcionario) colecaoFuncionario.iterator()
									.next();
							usuarioParaAtualizar.setFuncionario(f);
							form.setIdFuncionario(f.getId().toString());
							form.setNomeFuncionario(f.getNome());
							usuarioParaAtualizar
									.setNomeUsuario(f.getNome());
						} else {
							throw new ActionServletException(
									"atencao.required", null,
									"Matr�cula Funcion�rio");
						}
						// nome do funcionario � obrigatorio
						if (form.getNome() == null || form.getNome().equals("")) {
							throw new ActionServletException(
									"atencao.required", null, "Nome Usu�rio");
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
						}
					}
				}
				usuarioParaAtualizar.setUsuarioTipo(usuarioTipo);
			} else {
				usuarioParaAtualizar.setUsuarioTipo(null);
			}

		}

		if (form.getAbrangencia() != null && !form.getAbrangencia().equals("")) {
			if (form.getAbrangencia().equals(
					UsuarioAbrangencia.GERENCIA_REGIONAL)) {
				if (form.getGerenciaRegional() == null
						|| form.getGerenciaRegional().equals("")) {
					throw new ActionServletException("atencao.required", null,
							"Ger�ncia Regional");
				}
			}
			if (form.getAbrangencia().equals(
					UsuarioAbrangencia.UNIDADE_NEGOCIO_INT)) {
				if (form.getUnidadeNegocio() == null
						|| form.getUnidadeNegocio().equals("")) {
					throw new ActionServletException("atencao.required", null,
							"Unidade Neg�cio");
				}
			}
			if (form.getAbrangencia().equals(UsuarioAbrangencia.ELO_POLO)) {
				if (form.getIdElo() == null || form.getIdElo().equals("")) {
					throw new ActionServletException("atencao.required", null,
							"Elo P�lo");
				}
			}
			if (form.getAbrangencia().equals(UsuarioAbrangencia.LOCALIDADE)) {
				if (form.getIdLocalidade() == null
						|| form.getIdLocalidade().equals("")) {
					throw new ActionServletException("atencao.required", null,
							"Localidade");
				}
			}
		} else {
			throw new ActionServletException("atencao.required", null,
					"Abrang�ncia do Acesso");
		}
		// valida a unidade de lota��o
		if (!"".equals(form.getIdLotacao())) {

			 if (usuarioParaAtualizar.getUnidadeOrganizacional() == null 
				|| (usuarioParaAtualizar.getUnidadeOrganizacional() != null
				&& usuarioParaAtualizar.getUnidadeOrganizacional().getId() != null 
				&& !form.getIdLotacao().equals(
						usuarioParaAtualizar.getUnidadeOrganizacional().getId().toString()))
						|| (usuarioParaAtualizar.getUsuarioTipo().getIndicadorFuncionario() == UsuarioTipo.INDICADOR_FUNCIONARIO &&
								usuarioParaAtualizar.getUnidadeOrganizacional() != null
								&& usuarioParaAtualizar.getUnidadeOrganizacional().getId() != null 
								&& !form.getIdLotacao().equals(
										usuarioParaAtualizar.getFuncionario().getUnidadeOrganizacional().getId().toString()))) {
			
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
					usuarioParaAtualizar
							.setUnidadeOrganizacional(unidadeEmpresa);
					
//					Alterado por Vivianne Sousa  13/02/2007
					//solicitado por Leonardo Vieira
					if (usuarioParaAtualizar.getUsuarioTipo().getIndicadorFuncionario() == UsuarioTipo.INDICADOR_FUNCIONARIO){
						Funcionario funcionario = usuarioParaAtualizar.getFuncionario();
						funcionario.setUnidadeOrganizacional(unidadeEmpresa);
						usuarioParaAtualizar.setFuncionario(funcionario);
					}
					
				} else {
					usuarioParaAtualizar.setUnidadeOrganizacional(null);
				}
			}
		}

		String[] grupo = (String[]) sessao.getAttribute("grupo");

		Integer[] idGrupos = null;
		if (grupo != null) {
			idGrupos = new Integer[grupo.length];
			for (int i = 0; i < idGrupos.length; i++) {
				idGrupos[i] = new Integer(grupo[i]);
			}
		}
		
		if (form.getSituacao() != null && !form.getSituacao().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			UsuarioSituacao usuarioSituacao = new UsuarioSituacao();
			usuarioSituacao.setId(new Integer(form.getSituacao()));
			
			usuarioParaAtualizar.setUsuarioSituacao(usuarioSituacao);
		} else {
			throw new ActionServletException("atencao.required", null,
			"Situa��o");
		}

		this.getFachada().atualizarUsuario(usuarioParaAtualizar, idGrupos,processo, usuario);

		if (usuario.getId().equals(usuarioParaAtualizar.getId())) {
			sessao.setAttribute("usuarioLogado", usuarioParaAtualizar);
		}

		montarPaginaSucesso(httpServletRequest, "Usu�rio de Login "
				+ usuarioParaAtualizar.getLogin() + " atualizado com sucesso.",
				"Realizar outra Manuten��o de Usu�rio",
				"exibirFiltrarUsuarioAction.do?menu=sim");

		sessao.removeAttribute("usuario");
		sessao.removeAttribute("grupo");

		return retorno;
	}
}
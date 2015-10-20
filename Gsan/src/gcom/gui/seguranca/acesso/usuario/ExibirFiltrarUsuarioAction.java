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
import gcom.util.Util;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarUsuarioAction extends GcomAction {

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

		ActionForward retorno = actionMapping.findForward("filtrarUsuario");

		FiltrarUsuarioActionForm form = (FiltrarUsuarioActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Indicador de bloqueio funcionalidade usuario.
		//Recupara o indicador do sitema parametro para o bloqueio da funcionalidade do usuario.
		
		SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();
		  if(sistemaParametro.getIndicadorBloquearFunUsuario() != null 
					&& sistemaParametro.getIndicadorBloquearFunUsuario().equals(ConstantesSistema.INDICADOR_USO_ATIVO)){
				throw new ActionServletException(
						"atencao.usuario_nao_pode_ser_atualizado_nesta_funcionalidade");
			}
		  
		  
		// caso venha da tela atualizar sem ter passado pelo filtro
		// (no caso vim da tela de sucesso do inserir)
		if (sessao.getAttribute("indicadorAtualizar") == null) {
			sessao.setAttribute("indicadorAtualizar", "1");
		}
	
		
		String limpar = (String) httpServletRequest.getParameter("limpar");
		if (limpar != null && !limpar.equals("")) {

			form.setParmsUsuarioTipo("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			form.setUsuarioTipo("");
			form.setEmpresa("");
			form.setIdFuncionario("");
			form.setNomeFuncionario("");
			form.setNome("");
			form.setFuncionarioNaoEncontrada("false");
			form.setIdLotacao("");
			form.setNomeLotacao("");
			form.setLotacaoNaoEncontrada("false");
			form.setDataCadastramentoInicial("");
			form.setDataCadastramentoFinal("");
			form.setDataExpiracaoInicial("");
			form.setDataExpiracaoFinal("");
			form.setGrupo(null);
			form.setAbrangencia("");
			form.setEloNaoEncontrada("false");
			form.setGerenciaRegional("");
			form.setIdElo("");
			form.setNomeElo("");
			form.setLocalidadeNaoEncontrada("false");
			form.setIdLocalidade("");
			form.setNomeLocalidade("");
			form.setIdEmpresaFuncionario("");
			form.setDataCadastramentoInicial("");
			form.setDataNascimento("");
			form.setCpf("");
			form.setLoginUsuario("");
			sessao.setAttribute("indicadorAtualizar", "1");
			httpServletRequest.setAttribute("nomeCampo", "usuarioTipo");
			form.setUsuarioSituacao("" + ConstantesSistema.NUMERO_NAO_INFORMADO);

		}
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			httpServletRequest.setAttribute("nomeCampo", "usuarioTipo");

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoEmpresa = Fachada.getInstancia().pesquisar(
					filtroEmpresa, Empresa.class.getSimpleName());
			if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						" Empresa est� ");
			}
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);

			FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();

			filtroUsuarioTipo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUsuarioTipo = Fachada.getInstancia().pesquisar(
					filtroUsuarioTipo, UsuarioTipo.class.getSimpleName());
			if (colecaoUsuarioTipo == null || colecaoUsuarioTipo.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						" Tipo do Us�ario est� ");
			}
			sessao.setAttribute("colecaoUsuarioTipo", colecaoUsuarioTipo);

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

			FiltroGrupo filtroGrupo = new FiltroGrupo();
			filtroGrupo.setCampoOrderBy(FiltroGrupo.DESCRICAO);
			filtroGrupo.adicionarParametro(new ParametroSimples(
					FiltroGrupo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoGrupo = Fachada.getInstancia().pesquisar(
					filtroGrupo, Grupo.class.getSimpleName());
			sessao.setAttribute("colecaoGrupo", colecaoGrupo);

			Collection colecaoUsuarioSituacao = Fachada.getInstancia()
					.pesquisar(new FiltroUsuarioSituacao(),
							UsuarioSituacao.class.getSimpleName());
			sessao.setAttribute("colecaoUsuarioSituacao", colecaoUsuarioSituacao);

			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUnidadeNegocio = Fachada.getInstancia()
					.pesquisar(filtroUnidadeNegocio,
							UnidadeNegocio.class.getSimpleName());
			sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);

		}

		if (form.getIdFuncionario() != null
				&& !form.getIdFuncionario().equals("")) {
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, form.getIdFuncionario()));
			filtroFuncionario
					.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_ORGANIZACIONAL);
			Collection coll = Fachada.getInstancia().pesquisar(
					filtroFuncionario, Funcionario.class.getSimpleName());
			if (coll != null && !coll.isEmpty()) {
				Funcionario f = (Funcionario) coll.iterator().next();
				form.setIdFuncionario(f.getId().toString());
				form.setNomeFuncionario(f.getNome());
				form.setNome(f.getNome());
				form.setCpf(f.getNumeroCpf());
				form.setDataNascimento(Util.formatarData(f.getDataNascimento()));
				form.setFuncionarioNaoEncontrada("false");
			} else {
				form.setIdFuncionario("");
				form.setNomeFuncionario("Funcionario inexistente.");
				form.setFuncionarioNaoEncontrada("true");
			}
		}

		if (form.getIdLotacao() != null && !form.getIdLotacao().equals("")) {

			FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();
			filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, form.getIdLotacao()));
			Collection colecaoLotacao = Fachada.getInstancia().pesquisar(
					filtroUnidadeEmpresa,
					UnidadeOrganizacional.class.getSimpleName());
			if (colecaoLotacao != null && !colecaoLotacao.isEmpty()) {
				UnidadeOrganizacional unidadeEmpresa = (UnidadeOrganizacional) colecaoLotacao
						.iterator().next();
				form.setIdLotacao(unidadeEmpresa.getId().toString());
				form.setNomeLotacao(unidadeEmpresa.getDescricao());
				form.setLotacaoNaoEncontrada("false");
			} else {
				form.setIdLotacao("");
				form.setNomeLotacao("Lota��o inexistente.");
				form.setLotacaoNaoEncontrada("true");
			}
		}

		if (form.getIdElo() != null && !form.getIdElo().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, form.getIdElo()));
			Collection colecaoElo = Fachada.getInstancia().pesquisar(
					filtroLocalidade, Localidade.class.getSimpleName());
			if (colecaoElo != null && !colecaoElo.isEmpty()) {
				Localidade l = (Localidade) colecaoElo.iterator().next();
				if (l.getLocalidade() != null
						&& !l.getId().equals(l.getLocalidade().getId())) {
					throw new ActionServletException(
							"atencao.localidade.nao.elo");
				}
				form.setIdElo(l.getId().toString());
				form.setNomeElo(l.getDescricao());
				form.setEloNaoEncontrada("false");
			} else {
				form.setIdElo("");
				form.setNomeElo("Elo inexistente.");
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
				form.setNomeLocalidade(" Localidade inexistente.");
				form.setLocalidadeNaoEncontrada("true");
			}
		}
		
		
		sessao.getAttribute("indicadorAtualizar");
		return retorno;
		
		
	}
}
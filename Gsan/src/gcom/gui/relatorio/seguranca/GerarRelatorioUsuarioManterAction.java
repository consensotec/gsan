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
package gcom.gui.relatorio.seguranca;

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
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.seguranca.acesso.usuario.FiltrarUsuarioActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.seguranca.RelatorioManterUsuario;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o do relat�rio de bairro manter
 * 
 * @author Arthur Carvalho
 * @created 09/04/2008
 */
public class GerarRelatorioUsuarioManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		// cria a vari�vel de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarUsuarioActionForm filtrarUsuarioActionForm = (FiltrarUsuarioActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		FiltroUsuarioGrupo filtroUsuarioGrupo = (FiltroUsuarioGrupo) sessao
				.getAttribute("filtroUsuarioGrupo");

		// Inicio da parte que vai mandar os parametros para o relat�rio
		
		// Tipo do Usu�rio
		String idTipoUsuario = filtrarUsuarioActionForm.getUsuarioTipo();

		UsuarioTipo usuarioTipo = null;

		if (idTipoUsuario != null
				&& !idTipoUsuario.equals("")) {

			FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();

			filtroUsuarioTipo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioTipo.ID, idTipoUsuario));

			Collection colecaoTiposUsuario = fachada.pesquisar(
					filtroUsuarioTipo, UsuarioTipo.class.getName());

			if (colecaoTiposUsuario != null && !colecaoTiposUsuario.isEmpty()) {
				// O tipo do usu�rio foi encontrado
				usuarioTipo = (UsuarioTipo) Util
						.retonarObjetoDeColecao(colecaoTiposUsuario);

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Tipo Usu�rio");
			}

		}

		// Empresa
		String idEmpresa = filtrarUsuarioActionForm.getEmpresa();
		
		Empresa empresa = null;

		if (idEmpresa != null
				&& !idEmpresa.equals("")) {
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, idEmpresa));

			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());

			if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {

				empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Empresa");

			}

		}
		
		// Funcion�rio
		String idFuncionario = filtrarUsuarioActionForm.getIdFuncionario();

		Funcionario funcionario = null;

		if (idFuncionario != null && !idFuncionario.trim().equals("")) {

			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, idFuncionario));

			Collection colecaoFuncionario = fachada.pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {
				funcionario = (Funcionario) Util
						.retonarObjetoDeColecao(colecaoFuncionario);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Funcion�rio");
			}

		}
		
		// Unidade de Lota��o
		String idUnidadeLotacao = filtrarUsuarioActionForm.getIdLotacao();

		UnidadeOrganizacional unidadeLotacao = null;

		if (idUnidadeLotacao != null && !idUnidadeLotacao.trim().equals("")) {

			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID, idUnidadeLotacao));

			Collection colecaoUnidadeOrganizacional = fachada.pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class
							.getName());

			if (colecaoUnidadeOrganizacional != null
					&& !colecaoUnidadeOrganizacional.isEmpty()) {

				unidadeLotacao = (UnidadeOrganizacional) Util
						.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Unidade Lota��o");
			}
		}

		// Situa��o do Usu�rio
		String idUsuarioSituacao = filtrarUsuarioActionForm
				.getUsuarioSituacao();

		UsuarioSituacao usuarioSituacao = null;

		if (idUsuarioSituacao != null && !idUsuarioSituacao.equals("")) {

			FiltroUsuarioSituacao filtroUsuarioSituacao = new FiltroUsuarioSituacao();

			filtroUsuarioSituacao.adicionarParametro(new ParametroSimples(
					FiltroUsuarioSituacao.ID, idUsuarioSituacao));

			Collection colecaoUsuarioSituacao = fachada.pesquisar(
					filtroUsuarioSituacao, UsuarioSituacao.class.getName());

			if (colecaoUsuarioSituacao != null
					&& !colecaoUsuarioSituacao.isEmpty()) {
				usuarioSituacao = (UsuarioSituacao) Util
						.retonarObjetoDeColecao(colecaoUsuarioSituacao);

			} else {
				throw new ActionServletException("atencao._inexistente", null,
						"Situa��o do Usu�rio");
			}

		}

		// Abrang�ncia do Acesso
		String idAbrangencia = filtrarUsuarioActionForm.getAbrangencia();

		UsuarioAbrangencia usuarioAbrangencia = null;

		if (idAbrangencia != null && !idAbrangencia.equals("")) {

			FiltroUsuarioAbrangencia filtroUsuarioAbrangencia = new FiltroUsuarioAbrangencia();

			filtroUsuarioAbrangencia.adicionarParametro(new ParametroSimples(
					FiltroUsuarioAbrangencia.ID, idAbrangencia));

			Collection colecaoUsuarioAbrangencia = fachada.pesquisar(
					filtroUsuarioAbrangencia, UsuarioAbrangencia.class
							.getName());

			if (colecaoUsuarioAbrangencia != null
					&& !colecaoUsuarioAbrangencia.isEmpty()) {

				usuarioAbrangencia = (UsuarioAbrangencia) Util
						.retonarObjetoDeColecao(colecaoUsuarioAbrangencia);

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Abrang�ncia Acesso");
			}
		}
		

		// Ger�ncia Regional
		String idGerenciaRegional = filtrarUsuarioActionForm
				.getGerenciaRegional();

		GerenciaRegional gerenciaRegional = null;

		if (idGerenciaRegional != null && !idGerenciaRegional.equals("")) {

			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, idGerenciaRegional));

			Collection colecaoGerenciaRegional = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoGerenciaRegional != null
					&& !colecaoGerenciaRegional.isEmpty()) {
				gerenciaRegional = (GerenciaRegional) Util
						.retonarObjetoDeColecao(colecaoGerenciaRegional);

			} else {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Ger�ncia Regional");
			}

		}

		// Unidade de Neg�cio
		String idUnidadeNegocio = filtrarUsuarioActionForm.getUnidadeNegocio();

		UnidadeNegocio unidadeNegocio = null;

		if (idUnidadeNegocio != null && !idUnidadeNegocio.equals("")) {

			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.ID, idUnidadeNegocio));

			Collection colecaoUnidadeNegocio = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocio != null
					&& !colecaoUnidadeNegocio.isEmpty()) {
				unidadeNegocio = (UnidadeNegocio) Util
						.retonarObjetoDeColecao(colecaoUnidadeNegocio);

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Unidade Neg�cio");
			}
		}

		// Elo P�lo
		String idElo = filtrarUsuarioActionForm.getIdElo();

		Localidade elo = null;

		if (idElo != null && !idElo.trim().equals("")) {

			FiltroLocalidade filtroElo = new FiltroLocalidade();

			filtroElo.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idElo));
			filtroElo.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID_ELO, idElo));

			Collection colecaoElo = fachada.pesquisar(filtroElo,
					Localidade.class.getName());

			if (colecaoElo != null && !colecaoElo.isEmpty()) {
				elo = (Localidade) Util.retonarObjetoDeColecao(colecaoElo);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Elo P�lo");
			}
		}
		
		// Localidade
		String idLocalidade = filtrarUsuarioActionForm.getIdLocalidade();

		Localidade localidade = null;

		if (idLocalidade != null && !idLocalidade.trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidade));

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Localidade");
			}
		}

		// Grupo
		String[] idsGrupos = filtrarUsuarioActionForm.getGrupo();
		
		Collection<Grupo> colecaoGruposParametro = new ArrayList<Grupo>();

		if (idsGrupos != null) {
			
			for (int i = 0; i < idsGrupos.length; i++) {
				
				if (!idsGrupos[i].equals("")) {
					FiltroGrupo filtroGrupo = new FiltroGrupo();
					filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.ID, idsGrupos[i]));
					
					Collection colecaoGrupo = fachada.pesquisar(filtroGrupo, Grupo.class.getName());
					
					if (colecaoGrupo != null && !colecaoGrupo.isEmpty()) {
						Grupo grupo = (Grupo) Util.retonarObjetoDeColecao(colecaoGrupo);
						colecaoGruposParametro.add(grupo);
					}
				}
			}
				
		}
		
		// Per�odo Cadastramento Acesso
		String periodoCadastramentoInicial = filtrarUsuarioActionForm.getDataCadastramentoInicial();
		String periodoCadastramentoFinal = filtrarUsuarioActionForm.getDataCadastramentoFinal();
		
		Date dataCadastramentoInicial = null;
		Date dataCadastramentoFinal = null;
		
		if (periodoCadastramentoInicial != null && !periodoCadastramentoInicial.trim().equals("")) {
			dataCadastramentoInicial = Util.converteStringParaDate(periodoCadastramentoInicial);
		}
		
		if (periodoCadastramentoFinal != null && !periodoCadastramentoFinal.trim().equals("")) {
			dataCadastramentoFinal = Util.converteStringParaDate(periodoCadastramentoFinal);
		}

		// Per�odo Expira��o
		String periodoExpiracaoInicial = filtrarUsuarioActionForm.getDataExpiracaoInicial();
		String periodoExpiracaoFinal = filtrarUsuarioActionForm.getDataExpiracaoFinal();
		
		Date dataExpiracaoInicial = null;
		Date dataExpiracaoFinal = null;
		
		if (periodoExpiracaoInicial != null && !periodoExpiracaoInicial.trim().equals("")) {
			dataExpiracaoInicial = Util.converteStringParaDate(periodoExpiracaoInicial);
		}
		
		if (periodoExpiracaoFinal != null && !periodoExpiracaoFinal.trim().equals("")) {
			dataExpiracaoFinal = Util.converteStringParaDate(periodoExpiracaoFinal);
		}

		// seta os parametros que ser�o mostrados no relat�rio

		// Fim da parte que vai mandar os parametros para o relat�rio

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioManterUsuario relatorio = new RelatorioManterUsuario(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		
		relatorio.addParametro("filtroUsuarioGrupo", filtroUsuarioGrupo);
		relatorio.addParametro("usuarioTipo", usuarioTipo);
		relatorio.addParametro("empresa", empresa);
		relatorio.addParametro("funcionario", funcionario);
		relatorio.addParametro("nomeUsuario", filtrarUsuarioActionForm.getNome());
		relatorio.addParametro("unidadeLotacao", unidadeLotacao);
		relatorio.addParametro("usuarioSituacao", usuarioSituacao);
		relatorio.addParametro("login", filtrarUsuarioActionForm.getLoginUsuario());
		relatorio.addParametro("usuarioAbrangencia", usuarioAbrangencia);
		relatorio.addParametro("gerenciaRegional", gerenciaRegional);
		relatorio.addParametro("unidadeNegocio", unidadeNegocio);
		relatorio.addParametro("elo", elo);
		relatorio.addParametro("localidade", localidade);
		relatorio.addParametro("colecaoGruposParametro", colecaoGruposParametro);
		relatorio.addParametro("dataCadastramentoInicial", dataCadastramentoInicial);
		relatorio.addParametro("dataCadastramentoFinal", dataCadastramentoFinal);
		relatorio.addParametro("dataExpiracaoInicial", dataExpiracaoInicial);
		relatorio.addParametro("dataExpiracaoFinal", dataExpiracaoFinal);
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}

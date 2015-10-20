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
package gcom.gui.gerencial.cobranca;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaEventualHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade receber os par�metros que servir�o para
 * informar os dados para gera��o de relat�rio/consulta
 * 
 * @author S�vio Luiz
 * @date 25/06/2007
 */
public class InformarDadosGeracaoResumoAcaoConsultaEventualAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("consultarResumoAcaoCobrancaEventualParametros");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// Monta o Status do Wizard
		StatusWizard statusWizard = new StatusWizard(
				"consultarResumoAcaoCobrancaEventualWizardAction",
				"exibirInformarDadosGeracaoResumoAcaoConsultaEventualAction",
				"cancelarConsultarResumoAcaoCobrancaAction",
				"exibirInformarDadosGeracaoResumoAcaoConsultaEventualAction",
				"informarDadosGeracaoResumoAcaoConsultaEventualAction.do");

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						1, "ParametrosPrimeiraAbaA.gif",
						"ParametrosPrimeiraAbaD.gif",
						"exibirDadosGeracaoConsultaEventualAction", ""));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						2, "ResumoUltimaAbaA.gif", "ResumoUltimaAbaD.gif",
						"exibirConsultarResumoAcaoCobrancaEventualAction", ""));

		// manda o statusWizard para a sess�o
		sessao.setAttribute("statusWizard", statusWizard);

		InformarDadosGeracaoResumoAcaoConsultaEventualActionForm informarDadosGeracaoResumoAcaoConsultaEventualActionForm = (InformarDadosGeracaoResumoAcaoConsultaEventualActionForm) actionForm;

		String dataInicialEmissao = informarDadosGeracaoResumoAcaoConsultaEventualActionForm
				.getDataInicialEmissao();
		String dataFinalEmissao = informarDadosGeracaoResumoAcaoConsultaEventualActionForm
				.getDataFinalEmissao();

		String idCobrancaAcaoAtividadeComando = informarDadosGeracaoResumoAcaoConsultaEventualActionForm
				.getIdCobrancaAcaoAtividadeComando();
		String tituloCobrancaAcaoAtividadeComando = informarDadosGeracaoResumoAcaoConsultaEventualActionForm
		.getTituloCobrancaAcaoAtividadeComando();

		Integer idEloPolo = null;
		if (informarDadosGeracaoResumoAcaoConsultaEventualActionForm
				.getEloPolo() != null
				&& !informarDadosGeracaoResumoAcaoConsultaEventualActionForm
						.getEloPolo().equals("")) {

			idEloPolo = new Integer(
					informarDadosGeracaoResumoAcaoConsultaEventualActionForm
							.getEloPolo());
		}

		Integer idLocalidade = null;
		if (informarDadosGeracaoResumoAcaoConsultaEventualActionForm
				.getLocalidade() != null
				&& !informarDadosGeracaoResumoAcaoConsultaEventualActionForm
						.getLocalidade().equals("")) {

			idLocalidade = new Integer(
					informarDadosGeracaoResumoAcaoConsultaEventualActionForm
							.getLocalidade());
		}

		Integer idSetorComercial = null;
		if (informarDadosGeracaoResumoAcaoConsultaEventualActionForm
				.getSetorComercial() != null
				&& !informarDadosGeracaoResumoAcaoConsultaEventualActionForm
						.getSetorComercial().equals("")) {

			if (informarDadosGeracaoResumoAcaoConsultaEventualActionForm
					.getIdSetorComercial() != null
					&& !informarDadosGeracaoResumoAcaoConsultaEventualActionForm
							.getIdSetorComercial().equals("")) {

				idSetorComercial = new Integer(
						informarDadosGeracaoResumoAcaoConsultaEventualActionForm
								.getIdSetorComercial());
			} else {

				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						informarDadosGeracaoResumoAcaoConsultaEventualActionForm
								.getSetorComercial()));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoPesquisa = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Setor Comercial");
				}

				SetorComercial setorComercial = (SetorComercial) Util
						.retonarObjetoDeColecao(colecaoPesquisa);

				idSetorComercial = setorComercial.getId();
			}

		}

		Integer nmQuadra = null;
		if (informarDadosGeracaoResumoAcaoConsultaEventualActionForm
				.getQuadra() != null
				&& !informarDadosGeracaoResumoAcaoConsultaEventualActionForm
						.getQuadra().equals("")) {

			FiltroQuadra filtroQuadra = new FiltroQuadra();
			// filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_SETORCOMERCIAL, idSetorComercial));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.NUMERO_QUADRA,
					informarDadosGeracaoResumoAcaoConsultaEventualActionForm
							.getQuadra()));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoPesquisa = fachada.pesquisar(filtroQuadra,
					Quadra.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Quadra");
			}

			nmQuadra = new Integer(
					informarDadosGeracaoResumoAcaoConsultaEventualActionForm
							.getQuadra());
		}

		String[] cobrancaGrupo = informarDadosGeracaoResumoAcaoConsultaEventualActionForm
				.getGrupoCobranca();
		String[] gerenciaRegional = informarDadosGeracaoResumoAcaoConsultaEventualActionForm
				.getGerencialRegional();
		String[] unidadeNegocio = informarDadosGeracaoResumoAcaoConsultaEventualActionForm
				.getUnidadeNegocio();
		String[] imovelPerfil = informarDadosGeracaoResumoAcaoConsultaEventualActionForm
				.getPerfilImovel();
		String[] ligacaoAguaSituacao = informarDadosGeracaoResumoAcaoConsultaEventualActionForm
				.getSituacaoLigacaoAgua();
		String[] ligacaoEsgotoSituacao = informarDadosGeracaoResumoAcaoConsultaEventualActionForm
				.getSituacaoLigacaoEsgoto();
		String[] categoria = informarDadosGeracaoResumoAcaoConsultaEventualActionForm
				.getCategoria();
		String[] esferaPoder = informarDadosGeracaoResumoAcaoConsultaEventualActionForm
				.getEsferaPoder();
		String[] empresa = informarDadosGeracaoResumoAcaoConsultaEventualActionForm
				.getEmpresa();

		// [UC0304] Informar Dados para Gera��o de Relat�rio ou Consulta
		InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper = fachada
				.informarDadosGeracaoResumoAcaoConsultaEventual(
						dataInicialEmissao, dataFinalEmissao,
						idCobrancaAcaoAtividadeComando,tituloCobrancaAcaoAtividadeComando, cobrancaGrupo,
						gerenciaRegional, idEloPolo, idLocalidade,
						idSetorComercial, nmQuadra, imovelPerfil,
						ligacaoAguaSituacao, ligacaoEsgotoSituacao, categoria,
						esferaPoder, empresa, unidadeNegocio);

		sessao.setAttribute("informarDadosGeracaoResumoAcaoConsultaEventualHelper",
				informarDadosGeracaoResumoAcaoConsultaEventualHelper);

		GerenciaRegional gerenciaRegionalColecao = new GerenciaRegional();
		gerenciaRegionalColecao.setId(-1);

		Collection colecaoGerenciaRegional = new ArrayList();

		int i = 0;

		if (gerenciaRegional != null) {
			gerenciaRegionalColecao.setNome("OP��ES SELECIONADAS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

			for (i = 0; i < gerenciaRegional.length; i++) {
				if (!gerenciaRegional[i].equals("")
						&& !gerenciaRegional[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < gerenciaRegional.length) {
						filtroGerenciaRegional
								.adicionarParametro(new ParametroSimples(
										FiltroGerenciaRegional.ID,
										gerenciaRegional[i],
										ConectorOr.CONECTOR_OR,
										gerenciaRegional.length));

					} else {
						filtroGerenciaRegional
								.adicionarParametro(new ParametroSimples(
										FiltroGerenciaRegional.ID,
										gerenciaRegional[i]));
					}
				}
			}

			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);

			Collection colecaoGerenciaRegionalPesquisa = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoGerenciaRegionalPesquisa != null
					&& !colecaoGerenciaRegionalPesquisa.isEmpty()) {
				colecaoGerenciaRegional.addAll(colecaoGerenciaRegionalPesquisa);
			}
		} else {
			gerenciaRegionalColecao.setNome("TODOS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
		}

		sessao.setAttribute("colecaoGerenciaRegionalResultado",
				colecaoGerenciaRegional);

		//---------------------------------------------------------------------------------
		UnidadeNegocio unidadeNegocioColecao = new UnidadeNegocio();
		unidadeNegocioColecao.setId(-1);

		Collection colecaoUnidadeNegocio = new ArrayList();

		i = 0;
		
		if (unidadeNegocio != null) {
			unidadeNegocioColecao.setNome("OP��ES SELECIONADAS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

			for (i = 0; i < unidadeNegocio.length; i++) {
				if (!unidadeNegocio[i].equals("")
						&& !unidadeNegocio[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < unidadeNegocio.length) {
						filtroUnidadeNegocio
								.adicionarParametro(new ParametroSimples(
										FiltroUnidadeNegocio.ID, unidadeNegocio[i],
										ConectorOr.CONECTOR_OR,
										unidadeNegocio.length));

					} else {
						filtroUnidadeNegocio
								.adicionarParametro(new ParametroSimples(
										FiltroUnidadeNegocio.ID, unidadeNegocio[i]));
					}
				}
			}

			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);

			Collection colecaoUnidadeNegocioPesquisa = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocioPesquisa != null
					&& !colecaoUnidadeNegocioPesquisa.isEmpty()) {
				colecaoUnidadeNegocio.addAll(colecaoUnidadeNegocioPesquisa);
			}
		} else {
			unidadeNegocioColecao.setNome("TODOS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
		}

		sessao.setAttribute("colecaoUnidadeNegocioResultado",
			 	colecaoUnidadeNegocio);
						
		CobrancaGrupo cobrancaGrupoColecao = new CobrancaGrupo();
		cobrancaGrupoColecao.setId(-1);

		Collection colecaoCobrancaGrupo = new ArrayList();

		i = 0;

		if (cobrancaGrupo != null) {
			cobrancaGrupoColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoCobrancaGrupo.add(cobrancaGrupoColecao);
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();

			for (i = 0; i < cobrancaGrupo.length; i++) {
				if (!cobrancaGrupo[i].equals("")
						&& !cobrancaGrupo[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < cobrancaGrupo.length) {
						filtroCobrancaGrupo
								.adicionarParametro(new ParametroSimples(
										FiltroCobrancaGrupo.ID,
										cobrancaGrupo[i],
										ConectorOr.CONECTOR_OR,
										cobrancaGrupo.length));

					} else {
						filtroCobrancaGrupo
								.adicionarParametro(new ParametroSimples(
										FiltroCobrancaGrupo.ID,
										cobrancaGrupo[i]));
					}
				}
			}

			filtroCobrancaGrupo.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

			Collection colecaoCobrancaGrupoPesquisa = fachada.pesquisar(
					filtroCobrancaGrupo, CobrancaGrupo.class.getName());

			if (colecaoCobrancaGrupoPesquisa != null
					&& !colecaoCobrancaGrupoPesquisa.isEmpty()) {
				colecaoCobrancaGrupo.addAll(colecaoCobrancaGrupoPesquisa);
			}
		} else {
			cobrancaGrupoColecao.setDescricao("TODOS");
			colecaoCobrancaGrupo.add(cobrancaGrupoColecao);
		}

		sessao.setAttribute("colecaoCobrancaGrupoResultado",
				colecaoCobrancaGrupo);

		ImovelPerfil imovelPerfilColecao = new ImovelPerfil();
		imovelPerfilColecao.setId(-1);

		Collection colecaoImovelPerfil = new ArrayList();

		i = 0;

		if (imovelPerfil != null) {
			imovelPerfilColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

			for (i = 0; i < imovelPerfil.length; i++) {
				if (!imovelPerfil[i].equals("")
						&& !imovelPerfil[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < imovelPerfil.length) {
						filtroImovelPerfil
								.adicionarParametro(new ParametroSimples(
										FiltroImovelPerfil.ID, imovelPerfil[i],
										ConectorOr.CONECTOR_OR,
										imovelPerfil.length));

					} else {
						filtroImovelPerfil
								.adicionarParametro(new ParametroSimples(
										FiltroImovelPerfil.ID, imovelPerfil[i]));
					}
				}
			}

			filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

			Collection colecaoImovelPerfilPesquisa = fachada.pesquisar(
					filtroImovelPerfil, ImovelPerfil.class.getName());

			if (colecaoImovelPerfilPesquisa != null
					&& !colecaoImovelPerfilPesquisa.isEmpty()) {
				colecaoImovelPerfil.addAll(colecaoImovelPerfilPesquisa);
			}
		} else {
			imovelPerfilColecao.setDescricao("TODOS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
		}

		sessao
				.setAttribute("colecaoImovelPerfilResultado",
						colecaoImovelPerfil);

		LigacaoAguaSituacao ligacaoAguaSituacaoColecao = new LigacaoAguaSituacao();
		ligacaoAguaSituacaoColecao.setId(-1);

		Collection colecaoLigacaoAguaSituacao = new ArrayList();

		i = 0;

		if (ligacaoAguaSituacao != null) {
			ligacaoAguaSituacaoColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

			for (i = 0; i < ligacaoAguaSituacao.length; i++) {
				if (!ligacaoAguaSituacao[i].equals("")
						&& !ligacaoAguaSituacao[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < ligacaoAguaSituacao.length) {
						filtroLigacaoAguaSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoAguaSituacao.ID,
										ligacaoAguaSituacao[i],
										ConectorOr.CONECTOR_OR,
										ligacaoAguaSituacao.length));

					} else {
						filtroLigacaoAguaSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoAguaSituacao.ID,
										ligacaoAguaSituacao[i]));
					}
				}
			}

			filtroLigacaoAguaSituacao
					.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);

			Collection colecaoLigacaoAguaSituacaoPesquisa = fachada.pesquisar(
					filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class
							.getName());

			if (colecaoLigacaoAguaSituacaoPesquisa != null
					&& !colecaoLigacaoAguaSituacaoPesquisa.isEmpty()) {
				colecaoLigacaoAguaSituacao
						.addAll(colecaoLigacaoAguaSituacaoPesquisa);
			}
		} else {
			ligacaoAguaSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
		}

		sessao.setAttribute("colecaoLigacaoAguaSituacaoResultado",
				colecaoLigacaoAguaSituacao);

		LigacaoEsgotoSituacao ligacaoEsgotoSituacaoColecao = new LigacaoEsgotoSituacao();
		ligacaoAguaSituacaoColecao.setId(-1);

		Collection colecaoLigacaoEsgotoSituacao = new ArrayList();

		i = 0;

		if (ligacaoEsgotoSituacao != null) {
			ligacaoEsgotoSituacaoColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();

			for (i = 0; i < ligacaoEsgotoSituacao.length; i++) {
				if (!ligacaoEsgotoSituacao[i].equals("")
						&& !ligacaoEsgotoSituacao[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < ligacaoEsgotoSituacao.length) {
						filtroLigacaoEsgotoSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoEsgotoSituacao.ID,
										ligacaoEsgotoSituacao[i],
										ConectorOr.CONECTOR_OR,
										ligacaoEsgotoSituacao.length));

					} else {
						filtroLigacaoEsgotoSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoEsgotoSituacao.ID,
										ligacaoEsgotoSituacao[i]));
					}
				}
			}

			filtroLigacaoEsgotoSituacao
					.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);

			Collection colecaoLigacaoEsgotoSituacaoPesquisa = fachada
					.pesquisar(filtroLigacaoEsgotoSituacao,
							LigacaoEsgotoSituacao.class.getName());

			if (colecaoLigacaoEsgotoSituacaoPesquisa != null
					&& !colecaoLigacaoEsgotoSituacaoPesquisa.isEmpty()) {
				colecaoLigacaoEsgotoSituacao
						.addAll(colecaoLigacaoEsgotoSituacaoPesquisa);
			}
		} else {
			ligacaoEsgotoSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
		}

		sessao.setAttribute("colecaoLigacaoEsgotoSituacaoResultado",
				colecaoLigacaoEsgotoSituacao);

		Categoria categoriaColecao = new Categoria();
		categoriaColecao.setId(-1);

		Collection colecaoCategoria = new ArrayList();

		i = 0;

		if (categoria != null) {
			categoriaColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoCategoria.add(categoriaColecao);
			FiltroCategoria filtroCategoria = new FiltroCategoria();

			for (i = 0; i < categoria.length; i++) {
				if (!categoria[i].equals("")
						&& !categoria[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < categoria.length) {
						filtroCategoria
								.adicionarParametro(new ParametroSimples(
										FiltroCategoria.CODIGO, categoria[i],
										ConectorOr.CONECTOR_OR,
										categoria.length));

					} else {
						filtroCategoria
								.adicionarParametro(new ParametroSimples(
										FiltroCategoria.CODIGO, categoria[i]));
					}
				}
			}

			filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);

			Collection colecaoCategoriaPesquisa = fachada.pesquisar(
					filtroCategoria, Categoria.class.getName());

			if (colecaoCategoriaPesquisa != null
					&& !colecaoCategoriaPesquisa.isEmpty()) {
				colecaoCategoria.addAll(colecaoCategoriaPesquisa);
			}
		} else {
			categoriaColecao.setDescricao("TODOS");
			colecaoCategoria.add(categoriaColecao);
		}

		sessao.setAttribute("colecaoCategoriaResultado", colecaoCategoria);

		EsferaPoder esferaPoderColecao = new EsferaPoder();
		esferaPoderColecao.setId(-1);

		Collection colecaoEsferaPoder = new ArrayList();

		i = 0;

		if (esferaPoder != null) {
			esferaPoderColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoEsferaPoder.add(esferaPoderColecao);
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();

			for (i = 0; i < esferaPoder.length; i++) {
				if (!esferaPoder[i].equals("")
						&& !esferaPoder[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < esferaPoder.length) {
						filtroEsferaPoder
								.adicionarParametro(new ParametroSimples(
										FiltroEsferaPoder.ID, esferaPoder[i],
										ConectorOr.CONECTOR_OR,
										esferaPoder.length));

					} else {
						filtroEsferaPoder
								.adicionarParametro(new ParametroSimples(
										FiltroEsferaPoder.ID, esferaPoder[i]));
					}
				}
			}

			filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);

			Collection colecaoEsferaPoderPesquisa = fachada.pesquisar(
					filtroEsferaPoder, EsferaPoder.class.getName());

			if (colecaoEsferaPoderPesquisa != null
					&& !colecaoEsferaPoderPesquisa.isEmpty()) {
				colecaoEsferaPoder.addAll(colecaoEsferaPoderPesquisa);
			}
		} else {
			esferaPoderColecao.setDescricao("TODOS");
			colecaoEsferaPoder.add(esferaPoderColecao);
		}

		sessao.setAttribute("colecaoEsferaPoderResultado", colecaoEsferaPoder);

		Empresa empresaColecao = new Empresa();
		empresaColecao.setId(-1);

		Collection colecaoEmpresa = new ArrayList();

		i = 0;

		if (empresa != null) {
			empresaColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoEmpresa.add(empresaColecao);
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

			for (i = 0; i < empresa.length; i++) {
				if (!empresa[i].equals("")
						&& !empresa[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < empresa.length) {
						filtroEmpresa.adicionarParametro(new ParametroSimples(
								FiltroEmpresa.ID, empresa[i],
								ConectorOr.CONECTOR_OR, empresa.length));

					} else {
						filtroEmpresa.adicionarParametro(new ParametroSimples(
								FiltroEmpresa.ID, empresa[i]));
					}
				}
			}

			filtroEmpresa.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

			Collection colecaoEmpresaPesquisa = fachada.pesquisar(
					filtroEmpresa, Empresa.class.getName());

			if (colecaoEmpresaPesquisa != null
					&& !colecaoEmpresaPesquisa.isEmpty()) {
				colecaoEmpresa.addAll(colecaoEmpresaPesquisa);
			}
		} else {
			empresaColecao.setDescricao("TODOS");
			colecaoEmpresa.add(empresaColecao);
		}

		sessao.setAttribute("colecaoEmpresaResultado", colecaoEmpresa);

		return retorno;
	}

}

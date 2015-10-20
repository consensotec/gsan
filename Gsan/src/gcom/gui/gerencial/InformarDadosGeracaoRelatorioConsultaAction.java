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
package gcom.gui.gerencial;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
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
 * @author Raphael Rossiter
 * @date 19/05/2006
 */
public class InformarDadosGeracaoRelatorioConsultaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirInformarDadosGeracaoRelatorioConsulta");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		/**
		 * pega o tipo de resumo da sessao para direcionar o forward
		 */
		String tipoResumo = (String) sessao.getAttribute("tipoResumo");

		if ("ANORMALIDADE".trim().equalsIgnoreCase(tipoResumo)) {
			retorno = actionMapping
					.findForward("informarConsultarResumoAnormalidade");
		} else if ("PENDENCIA".trim().equalsIgnoreCase(tipoResumo)) {
			retorno = actionMapping
					.findForward("informarConsultarResumoPendencia");
		} else if ("LIGACAO_EC0NOMIA".trim().equalsIgnoreCase(tipoResumo)) {
			retorno = actionMapping
					.findForward("informarConsultarResumoLigacoesEconomia");
		} else if ("ANALISE".trim().equalsIgnoreCase(tipoResumo)) {
			retorno = actionMapping
					.findForward("informarConsultarResumoAnaliseFaturamento");
		} else if ("COMPARATIVORESUMOS".trim().equalsIgnoreCase(tipoResumo)) {
			retorno = actionMapping
					.findForward("consultarComparativoResumosFaturamentoArrecadacaoPendencia");
		}else if ("ACAOCOBRANCA".trim().equalsIgnoreCase(tipoResumo)){
			retorno = actionMapping.findForward("consultarResumoAcaoCobrancaParametros");
			
	        // Monta o Status do Wizard
			StatusWizard statusWizard = new StatusWizard(
					"consultarResumoAcaoCobrancaWizardAction", "exibirInformarDadosGeracaoRelatorioConsultaAction",
					"cancelarConsultarResumoAcaoCobrancaAction", 
					"exibirInformarDadosGeracaoRelatorioConsultaAction",	
					"informarDadosGeracaoRelatorioConsultaAction.do");

			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							1, "ParametrosPrimeiraAbaA.gif", "ParametrosPrimeiraAbaD.gif",
							"exibirDadosGeracaoConsultaAction",
							""));
			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							2, "UnidadeNegocioA.gif", "UnidadeNegocioD.gif",
							"exibirConsultarResumoAcaoCobrancaAction",
							""));
			
	        //manda o statusWizard para a sess�o
	        sessao.setAttribute("statusWizard", statusWizard);
		}

		InformarDadosGeracaoRelatorioConsultaActionForm informarDadosGeracaoRelatorioConsultaActionForm = (InformarDadosGeracaoRelatorioConsultaActionForm) actionForm;

		String mesAnoFaturamento = informarDadosGeracaoRelatorioConsultaActionForm
				.getMesAnoFaturamento();
		Integer opcaoTotalizacao = new Integer(
				informarDadosGeracaoRelatorioConsultaActionForm
						.getOpcaoTotalizacao());

		Integer idFaturamentoGrupo = null;
		if (informarDadosGeracaoRelatorioConsultaActionForm
				.getGrupoFaturamento() != null
				&& !informarDadosGeracaoRelatorioConsultaActionForm
						.getGrupoFaturamento().equals("")) {

			idFaturamentoGrupo = new Integer(
					informarDadosGeracaoRelatorioConsultaActionForm
							.getGrupoFaturamento());
		}
		
		Integer idCobrancaGrupo = null;
		if (informarDadosGeracaoRelatorioConsultaActionForm
				.getGrupoCobranca() != null
				&& !informarDadosGeracaoRelatorioConsultaActionForm
						.getGrupoCobranca().equals("")) {

			idCobrancaGrupo = new Integer(
					informarDadosGeracaoRelatorioConsultaActionForm
							.getGrupoCobranca());
		}		

		Integer idGerenciaRegional = null;
		if (informarDadosGeracaoRelatorioConsultaActionForm
				.getGerencialRegional() != null
				&& !informarDadosGeracaoRelatorioConsultaActionForm
						.getGerencialRegional().equals("")) {

			idGerenciaRegional = new Integer(
					informarDadosGeracaoRelatorioConsultaActionForm
							.getGerencialRegional());
		}
		
		Integer idUnidadeNegocio = null;
		if (informarDadosGeracaoRelatorioConsultaActionForm
				.getUnidadeNegocio() != null
				&& !informarDadosGeracaoRelatorioConsultaActionForm
						.getUnidadeNegocio().equals("")) {

			idUnidadeNegocio = new Integer(
					informarDadosGeracaoRelatorioConsultaActionForm
							.getUnidadeNegocio());
		}
		
		Integer idEloPolo = null;
		if (informarDadosGeracaoRelatorioConsultaActionForm.getEloPolo() != null
				&& !informarDadosGeracaoRelatorioConsultaActionForm
						.getEloPolo().equals("")) {

			idEloPolo = new Integer(
					informarDadosGeracaoRelatorioConsultaActionForm
							.getEloPolo());
		}

		Integer idLocalidade = null;
		if (informarDadosGeracaoRelatorioConsultaActionForm.getLocalidade() != null
				&& !informarDadosGeracaoRelatorioConsultaActionForm
						.getLocalidade().equals("")) {

			idLocalidade = new Integer(
					informarDadosGeracaoRelatorioConsultaActionForm
							.getLocalidade());
		}

		Integer idMunicipio = null;
		if (informarDadosGeracaoRelatorioConsultaActionForm.getMunicipio() != null
				&& !informarDadosGeracaoRelatorioConsultaActionForm
						.getMunicipio().equals("")) {

			idMunicipio = new Integer(
					informarDadosGeracaoRelatorioConsultaActionForm
							.getMunicipio());
		}
		
		Integer idSetorComercial = null;
		if (informarDadosGeracaoRelatorioConsultaActionForm.getSetorComercial() != null
				&& !informarDadosGeracaoRelatorioConsultaActionForm
						.getSetorComercial().equals("")) {

			if (informarDadosGeracaoRelatorioConsultaActionForm
					.getIdSetorComercial() != null
					&& !informarDadosGeracaoRelatorioConsultaActionForm
							.getIdSetorComercial().equals("")) {

				idSetorComercial = new Integer(
						informarDadosGeracaoRelatorioConsultaActionForm
								.getIdSetorComercial());
			} else {

				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						informarDadosGeracaoRelatorioConsultaActionForm
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
		if (informarDadosGeracaoRelatorioConsultaActionForm.getQuadra() != null
				&& !informarDadosGeracaoRelatorioConsultaActionForm.getQuadra()
						.equals("")) {

			FiltroQuadra filtroQuadra = new FiltroQuadra();
			//filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_SETORCOMERCIAL, idSetorComercial));

			filtroQuadra
					.adicionarParametro(new ParametroSimples(
							FiltroQuadra.NUMERO_QUADRA,
							informarDadosGeracaoRelatorioConsultaActionForm
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
					informarDadosGeracaoRelatorioConsultaActionForm.getQuadra());
		}

		String[] imovelPerfil = informarDadosGeracaoRelatorioConsultaActionForm
				.getPerfilImovel();
		String[] ligacaoAguaSituacao = informarDadosGeracaoRelatorioConsultaActionForm
				.getSituacaoLigacaoAgua();
		String[] ligacaoEsgotoSituacao = informarDadosGeracaoRelatorioConsultaActionForm
				.getSituacaoLigacaoEsgoto();
		String[] categoria = informarDadosGeracaoRelatorioConsultaActionForm
				.getCategoria();
		String[] esferaPoder = informarDadosGeracaoRelatorioConsultaActionForm
				.getEsferaPoder();
		
		Integer tipoAnaliseFaturamento = null;
		if (informarDadosGeracaoRelatorioConsultaActionForm
				.getTipoAnaliseFaturamento() != null
				&& !informarDadosGeracaoRelatorioConsultaActionForm
						.getTipoAnaliseFaturamento().equals("")) {

			tipoAnaliseFaturamento = new Integer(
					informarDadosGeracaoRelatorioConsultaActionForm
							.getTipoAnaliseFaturamento());
		}
		
		/** [RR2011071026]
		 * 	Autor: Paulo Diniz
		 *  Data: 21/07/2011
		 *  Resumo da An�lise do Faturamento 
		 */
		Integer idRota = null;
		if (informarDadosGeracaoRelatorioConsultaActionForm.getIdRota() != null
				&& !informarDadosGeracaoRelatorioConsultaActionForm
						.getIdRota().equals("")) {
			idRota = new Integer(
					informarDadosGeracaoRelatorioConsultaActionForm
							.getIdRota());
		}

		// Tipo de Relat�rio
		Integer tipoRelatorio = null;
		if (httpServletRequest.getAttribute("tipoRelatorio") != null) {
			tipoRelatorio = (Integer) httpServletRequest
					.getAttribute("tipoRelatorio");
		}

		// [UC0304] Informar Dados para Gera��o de Relat�rio ou Consulta
		InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = fachada
				.informarDadosGeracaoRelatorioConsulta(mesAnoFaturamento,
						opcaoTotalizacao, idFaturamentoGrupo, idCobrancaGrupo,
						idGerenciaRegional, idEloPolo, idLocalidade,
						idSetorComercial, nmQuadra, imovelPerfil,
						ligacaoAguaSituacao, ligacaoEsgotoSituacao, categoria,
						esferaPoder, tipoAnaliseFaturamento, tipoRelatorio,
						idUnidadeNegocio, idMunicipio, idRota);
		
		sessao.setAttribute("informarDadosGeracaoRelatorioConsultaHelper",
				informarDadosGeracaoRelatorioConsultaHelper);
		
		ImovelPerfil imovelPerfilColecao = new ImovelPerfil();
		imovelPerfilColecao.setId(-1);

		Collection colecaoImovelPerfil = new ArrayList();

		int i = 0;

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

		sessao.setAttribute("colecaoImovelPerfilResultado", colecaoImovelPerfil);
		
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
										FiltroLigacaoAguaSituacao.ID, ligacaoAguaSituacao[i],
										ConectorOr.CONECTOR_OR,
										ligacaoAguaSituacao.length));

					} else {
						filtroLigacaoAguaSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoAguaSituacao.ID, ligacaoAguaSituacao[i]));
					}
				}
			}

			filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);

			Collection colecaoLigacaoAguaSituacaoPesquisa = fachada.pesquisar(
					filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());

			if (colecaoLigacaoAguaSituacaoPesquisa != null
					&& !colecaoLigacaoAguaSituacaoPesquisa.isEmpty()) {
				colecaoLigacaoAguaSituacao.addAll(colecaoLigacaoAguaSituacaoPesquisa);
			}
		} else {
			ligacaoAguaSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
		}

		sessao.setAttribute("colecaoLigacaoAguaSituacaoResultado", colecaoLigacaoAguaSituacao);
		
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
										FiltroLigacaoEsgotoSituacao.ID, ligacaoEsgotoSituacao[i],
										ConectorOr.CONECTOR_OR,
										ligacaoEsgotoSituacao.length));

					} else {
						filtroLigacaoEsgotoSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoEsgotoSituacao.ID, ligacaoEsgotoSituacao[i]));
					}
				}
			}

			filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);

			Collection colecaoLigacaoEsgotoSituacaoPesquisa = fachada.pesquisar(
					filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());

			if (colecaoLigacaoEsgotoSituacaoPesquisa != null
					&& !colecaoLigacaoEsgotoSituacaoPesquisa.isEmpty()) {
				colecaoLigacaoEsgotoSituacao.addAll(colecaoLigacaoEsgotoSituacaoPesquisa);
			}
		} else {
			ligacaoEsgotoSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
		}

		sessao.setAttribute("colecaoLigacaoEsgotoSituacaoResultado", colecaoLigacaoEsgotoSituacao);
		
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

		return retorno;
	}

}

/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.gui.gerencial;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.cadastro.cliente.EsferaPoder;
import gsan.cadastro.cliente.FiltroEsferaPoder;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.FiltroCategoria;
import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.fachada.Fachada;
import gsan.gerencial.bean.InformarDadosGeracaoRelatorioConsultaPeriodoHelper;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.gui.StatusWizard;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ConectorOr;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade receber os parâmetros que servirão para
 * informar os dados para geração de relatório/consulta.
 * 
 * @author Raphael Rossiter, Davi Menezes
 * @date 19/05/2006, 30/09/2011
 */
public class InformarDadosGeracaoRelatorioConsultaPeriodoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirInformarDadosGeracaoRelatorioConsultaPeriodo");

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
					"consultarResumoAcaoCobrancaWizardAction", "exibirInformarDadosGeracaoRelatorioConsultaPeriodoAction",
					"cancelarConsultarResumoAcaoCobrancaAction", 
					"exibirInformarDadosGeracaoRelatorioConsultaPeriodoAction",	
					"informarDadosGeracaoRelatorioConsultaPeriodoAction.do");

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
			
	        //manda o statusWizard para a sessão
	        sessao.setAttribute("statusWizard", statusWizard);
		}

		InformarDadosGeracaoRelatorioConsultaPeriodoActionForm informarDadosGeracaoRelatorioConsultaPeriodoActionForm = (InformarDadosGeracaoRelatorioConsultaPeriodoActionForm) actionForm;

		String mesAnoInicioFaturamento = informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getMesAnoInicialFaturamento();
		String mesAnoFinalFaturamento  = informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getMesAnoFinalFaturamento();
		
		Integer anoMesInicial = Util.formatarMesAnoComBarraParaAnoMes(mesAnoInicioFaturamento);
		Integer anoMesFinal = Util.formatarMesAnoComBarraParaAnoMes(mesAnoFinalFaturamento);
		
		if(anoMesInicial > anoMesFinal){
			throw new ActionServletException("atencao.mes_ano_inicial_maior_mes_ano_final");
		}
		
		Integer numeroMeses = Util.retornaQuantidadeMeses(anoMesFinal, anoMesInicial);
		
		if(numeroMeses > 12){
			throw new ActionServletException("atencao.periodo_invalido");
		}
		
		Integer opcaoTotalizacao = new Integer(
				informarDadosGeracaoRelatorioConsultaPeriodoActionForm
						.getOpcaoTotalizacao());

		Integer idFaturamentoGrupo = null;
		if (informarDadosGeracaoRelatorioConsultaPeriodoActionForm
				.getGrupoFaturamento() != null
				&& !informarDadosGeracaoRelatorioConsultaPeriodoActionForm
						.getGrupoFaturamento().equals("")) {

			idFaturamentoGrupo = new Integer(
					informarDadosGeracaoRelatorioConsultaPeriodoActionForm
							.getGrupoFaturamento());
		}
		
		Integer idCobrancaGrupo = null;
		if (informarDadosGeracaoRelatorioConsultaPeriodoActionForm
				.getGrupoCobranca() != null
				&& !informarDadosGeracaoRelatorioConsultaPeriodoActionForm
						.getGrupoCobranca().equals("")) {

			idCobrancaGrupo = new Integer(
					informarDadosGeracaoRelatorioConsultaPeriodoActionForm
							.getGrupoCobranca());
		}		

		Integer idGerenciaRegional = null;
		if (informarDadosGeracaoRelatorioConsultaPeriodoActionForm
				.getGerencialRegional() != null
				&& !informarDadosGeracaoRelatorioConsultaPeriodoActionForm
						.getGerencialRegional().equals("")) {

			idGerenciaRegional = new Integer(
					informarDadosGeracaoRelatorioConsultaPeriodoActionForm
							.getGerencialRegional());
		}
		
		Integer idUnidadeNegocio = null;
		if (informarDadosGeracaoRelatorioConsultaPeriodoActionForm
				.getUnidadeNegocio() != null
				&& !informarDadosGeracaoRelatorioConsultaPeriodoActionForm
						.getUnidadeNegocio().equals("")) {

			idUnidadeNegocio = new Integer(
					informarDadosGeracaoRelatorioConsultaPeriodoActionForm
							.getUnidadeNegocio());
		}
		
		Integer idEloPolo = null;
		if (informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getEloPolo() != null
				&& !informarDadosGeracaoRelatorioConsultaPeriodoActionForm
						.getEloPolo().equals("")) {

			idEloPolo = new Integer(
					informarDadosGeracaoRelatorioConsultaPeriodoActionForm
							.getEloPolo());
		}

		Integer idLocalidade = null;
		if (informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getLocalidade() != null
				&& !informarDadosGeracaoRelatorioConsultaPeriodoActionForm
						.getLocalidade().equals("")) {

			idLocalidade = new Integer(
					informarDadosGeracaoRelatorioConsultaPeriodoActionForm
							.getLocalidade());
		}

		Integer idMunicipio = null;
		if (informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getMunicipio() != null
				&& !informarDadosGeracaoRelatorioConsultaPeriodoActionForm
						.getMunicipio().equals("")) {

			idMunicipio = new Integer(
					informarDadosGeracaoRelatorioConsultaPeriodoActionForm
							.getMunicipio());
		}
		
		Integer idSetorComercial = null;
		if (informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getSetorComercial() != null
				&& !informarDadosGeracaoRelatorioConsultaPeriodoActionForm
						.getSetorComercial().equals("")) {

			if (informarDadosGeracaoRelatorioConsultaPeriodoActionForm
					.getIdSetorComercial() != null
					&& !informarDadosGeracaoRelatorioConsultaPeriodoActionForm
							.getIdSetorComercial().equals("")) {

				idSetorComercial = new Integer(
						informarDadosGeracaoRelatorioConsultaPeriodoActionForm
								.getIdSetorComercial());
			} else {

				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						informarDadosGeracaoRelatorioConsultaPeriodoActionForm
								.getSetorComercial()));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection<Object> colecaoPesquisa = fachada.pesquisar(
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
		if (informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getQuadra() != null
				&& !informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getQuadra()
						.equals("")) {

			FiltroQuadra filtroQuadra = new FiltroQuadra();
			//filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_SETORCOMERCIAL, idSetorComercial));

			filtroQuadra
					.adicionarParametro(new ParametroSimples(
							FiltroQuadra.NUMERO_QUADRA,
							informarDadosGeracaoRelatorioConsultaPeriodoActionForm
									.getQuadra()));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection<Object> colecaoPesquisa = fachada.pesquisar(filtroQuadra,
					Quadra.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Quadra");
			}

			nmQuadra = new Integer(
					informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getQuadra());
		}

		String[] imovelPerfil = informarDadosGeracaoRelatorioConsultaPeriodoActionForm
				.getPerfilImovel();
		String[] ligacaoAguaSituacao = informarDadosGeracaoRelatorioConsultaPeriodoActionForm
				.getSituacaoLigacaoAgua();
		String[] ligacaoEsgotoSituacao = informarDadosGeracaoRelatorioConsultaPeriodoActionForm
				.getSituacaoLigacaoEsgoto();
		String[] categoria = informarDadosGeracaoRelatorioConsultaPeriodoActionForm
				.getCategoria();
		String[] esferaPoder = informarDadosGeracaoRelatorioConsultaPeriodoActionForm
				.getEsferaPoder();
		
		Integer tipoAnaliseFaturamento = null;
		if (informarDadosGeracaoRelatorioConsultaPeriodoActionForm
				.getTipoAnaliseFaturamento() != null
				&& !informarDadosGeracaoRelatorioConsultaPeriodoActionForm
						.getTipoAnaliseFaturamento().equals("")) {

			tipoAnaliseFaturamento = new Integer(
					informarDadosGeracaoRelatorioConsultaPeriodoActionForm
							.getTipoAnaliseFaturamento());
		}
		
		/** [RR2011071026]
		 * 	Autor: Paulo Diniz
		 *  Data: 21/07/2011
		 *  Resumo da Análise do Faturamento 
		 */
		Integer idRota = null;
		if (informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getIdRota() != null
				&& !informarDadosGeracaoRelatorioConsultaPeriodoActionForm
						.getIdRota().equals("")) {
			idRota = new Integer(
					informarDadosGeracaoRelatorioConsultaPeriodoActionForm
							.getIdRota());
		}

		// Tipo de Relatório
		Integer tipoRelatorio = null;
		if (httpServletRequest.getAttribute("tipoRelatorio") != null) {
			tipoRelatorio = (Integer) httpServletRequest
					.getAttribute("tipoRelatorio");
		}

		// [UC0304] Informar Dados para Geração de Relatório ou Consulta
		InformarDadosGeracaoRelatorioConsultaPeriodoHelper informarDadosGeracaoRelatorioConsultaPeriodoHelper = fachada
				.informarDadosGeracaoRelatorioConsultaPeriodo(mesAnoInicioFaturamento, mesAnoFinalFaturamento,
						opcaoTotalizacao, idFaturamentoGrupo, idCobrancaGrupo,
						idGerenciaRegional, idEloPolo, idLocalidade,
						idSetorComercial, nmQuadra, imovelPerfil,
						ligacaoAguaSituacao, ligacaoEsgotoSituacao, categoria,
						esferaPoder, tipoAnaliseFaturamento, tipoRelatorio,
						idUnidadeNegocio, idMunicipio, idRota);
		
		sessao.setAttribute("informarDadosGeracaoRelatorioConsultaPeriodoHelper",
				informarDadosGeracaoRelatorioConsultaPeriodoHelper);
		
		ImovelPerfil imovelPerfilColecao = new ImovelPerfil();
		imovelPerfilColecao.setId(-1);

		Collection<ImovelPerfil> colecaoImovelPerfil = new ArrayList<ImovelPerfil>();

		int i = 0;

		if (imovelPerfil != null) {
			imovelPerfilColecao.setDescricao("OPÇÕES SELECIONADAS");
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

			Collection<ImovelPerfil> colecaoImovelPerfilPesquisa = fachada.pesquisar(
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

		Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = new ArrayList<LigacaoAguaSituacao>();

		i = 0;

		if (ligacaoAguaSituacao != null) {
			ligacaoAguaSituacaoColecao.setDescricao("OPÇÕES SELECIONADAS");
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

			Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacaoPesquisa = fachada.pesquisar(
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

		Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = new ArrayList<LigacaoEsgotoSituacao>();

		i = 0;

		if (ligacaoEsgotoSituacao != null) {
			ligacaoEsgotoSituacaoColecao.setDescricao("OPÇÕES SELECIONADAS");
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

			Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacaoPesquisa = fachada.pesquisar(
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

		Collection<Categoria> colecaoCategoria = new ArrayList<Categoria>();

		i = 0;

		if (categoria != null) {
			categoriaColecao.setDescricao("OPÇÕES SELECIONADAS");
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

			Collection<Categoria> colecaoCategoriaPesquisa = fachada.pesquisar(
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

		Collection<EsferaPoder> colecaoEsferaPoder = new ArrayList<EsferaPoder>();

		i = 0;

		if (esferaPoder != null) {
			esferaPoderColecao.setDescricao("OPÇÕES SELECIONADAS");
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

			Collection<EsferaPoder> colecaoEsferaPoderPesquisa = fachada.pesquisar(
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

package gsan.gui.faturamento;

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
 * R�mulo Aur�lio de Melo Souza Filho
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

import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.FiltroQualidadeAgua;
import gsan.faturamento.FiltroQualidadeAguaPadrao;
import gsan.faturamento.QualidadeAgua;
import gsan.faturamento.QualidadeAguaPadrao;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.operacional.FiltroFonteCaptacao;
import gsan.operacional.FiltroSistemaAbastecimento;
import gsan.operacional.FonteCaptacao;
import gsan.operacional.SistemaAbastecimento;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
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
 * Descri��o da classe
 * 
 * @author K�ssia Albuquerque, R�mulo Aur�lio [Alteracao para abas]
 * @date 24/07/2007, 16/09/2008
 */

public class ExibirInserirQualidadeAguaDadosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirInserirQualidadeAguaDadosAction");

		InserirQualidadeAguaActionForm form = (InserirQualidadeAguaActionForm) actionForm;

		HttpSession sessao = this.getSessao(httpServletRequest);

		// SETANDO FOCO INICIAL
		if (httpServletRequest.getParameter("menu") != null && 
			!httpServletRequest.getParameter("menu").equals("")) {

			httpServletRequest.setAttribute("nomeCampo", "referencia");

			sessao.removeAttribute("inserirTodosAtivado");
			
			form.setPadraoTurbidez("");
			form.setPadraoCloroResidual("");
			form.setPadraoPH("");
			form.setPadraoCor("");
			form.setPadraoFluor("");
			form.setPadraoFerro("");
			form.setPadraoColiformesTotais("");
			form.setPadraoColiformesFecais("");
			form.setPadraoNitrato("");
			form.setPadraoColiformesTermotolerantes("");
			form.setPadraoAlcalinidade("");
			form.setDescricaoDurezaTotal("");

			// [SB0001 - EXIBI��O DOS PARAMETROS DE QUALIDADE DA �GUA]
			FiltroQualidadeAguaPadrao filtroQualidadeAguaPadrao = new FiltroQualidadeAguaPadrao();
			Collection colecaoQualidadeAguaPadrao = 
				this.getFachada().pesquisar(filtroQualidadeAguaPadrao,QualidadeAguaPadrao.class.getName());

			if (colecaoQualidadeAguaPadrao != null && 
				!colecaoQualidadeAguaPadrao.isEmpty()) {

				QualidadeAguaPadrao qualidadeAguaPadrao = 
					(QualidadeAguaPadrao) colecaoQualidadeAguaPadrao.iterator().next();

				sessao.setAttribute("qualidadeAguaPadraoId",qualidadeAguaPadrao.getId());
				
				if (qualidadeAguaPadrao.getDescricaoPadraoTurbidez() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoTurbidez().equals("")) {

					form.setPadraoTurbidez(qualidadeAguaPadrao.getDescricaoPadraoTurbidez());
				}
				
				if (qualidadeAguaPadrao.getDescricaoPadraoCloro() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoCloro().equals("")) {

					form.setPadraoCloroResidual(qualidadeAguaPadrao.getDescricaoPadraoCloro());
				}
				
				if (qualidadeAguaPadrao.getDescricaoPadraoPh() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoPh().equals("")) {

					form.setPadraoPH(qualidadeAguaPadrao.getDescricaoPadraoPh());
				}
				
				if (qualidadeAguaPadrao.getDescricaoPadraoCor() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoCor().equals("")) {

					form.setPadraoCor(qualidadeAguaPadrao.getDescricaoPadraoCor());
				}
				
				if (qualidadeAguaPadrao.getDescricaoPadraoFluor() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoFluor().equals("")) {

					form.setPadraoFluor(qualidadeAguaPadrao.getDescricaoPadraoFluor());
				}
				
				if (qualidadeAguaPadrao.getDescricaoPadraoFerro() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoFerro().equals("")) {

					form.setPadraoFerro(qualidadeAguaPadrao.getDescricaoPadraoFerro());
				}
				
				if (qualidadeAguaPadrao.getDescricaoPadraoColiformesTotais() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoColiformesTotais().equals("")) {

					form.setPadraoColiformesTotais(qualidadeAguaPadrao.getDescricaoPadraoColiformesTotais());
				}
				
				if (qualidadeAguaPadrao.getDescricaoPadraoColiformesFecais() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoColiformesFecais().equals("")) {

					form.setPadraoColiformesFecais(qualidadeAguaPadrao.getDescricaoPadraoColiformesFecais());
				}
				
				if (qualidadeAguaPadrao.getDescricaoNitrato() != null && 
					!qualidadeAguaPadrao.getDescricaoNitrato().equals("")) {

					form.setPadraoNitrato(qualidadeAguaPadrao.getDescricaoNitrato());
				}
				
				if (qualidadeAguaPadrao.getDescricaoPadraoColiformesTermotolerantes() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoColiformesTermotolerantes().equals("")) {

					form.setPadraoColiformesTermotolerantes(qualidadeAguaPadrao.getDescricaoPadraoColiformesTermotolerantes());
				}				
				
				if(qualidadeAguaPadrao.getDescricaoPadraoAlcalinidade() != null 
						&& !qualidadeAguaPadrao.getDescricaoPadraoAlcalinidade().equals("")){
					
					form.setPadraoAlcalinidade(qualidadeAguaPadrao.getDescricaoPadraoAlcalinidade());					
				}
				
				//Duzera Total
				if(qualidadeAguaPadrao.getDescricaoDurezaTotal() != null 
						&& !qualidadeAguaPadrao.getDescricaoDurezaTotal().equals("")){
					form.setDescricaoDurezaTotal(qualidadeAguaPadrao.getDescricaoDurezaTotal());
				}				
			}
		}

		String indicadorInserirTodos = form.getIncluirTodos();

		// [FS0001 - VALIDAR REFER�NCIA]
		if ((form.getReferencia() != null && !form.getReferencia().equals(""))
				&& (indicadorInserirTodos != null && !indicadorInserirTodos
						.equals(""))) {

			String anoMesReferencia = Util
					.formatarMesAnoParaAnoMesSemBarra(form.getReferencia());

			SistemaParametro sistemaParametro = this.getFachada()
					.pesquisarParametrosDoSistema();
			String anoMesFuturo = ""
					+ Util.somaUmMesAnoMesReferencia(sistemaParametro
							.getAnoMesFaturamento());

			if (Util.compararAnoMesReferencia(anoMesReferencia, anoMesFuturo,
					">")) {
				httpServletRequest.setAttribute("nomeCampo", "referencia");
				throw new ActionServletException("atencao.mes_ano_menor");
			}

			// [SB0002] INCLUSAO DOS PARAMETROS DA QUALIDADE AGUA
			httpServletRequest.setAttribute("inserirTodosAtivado", "1");

			sessao.setAttribute("inserirTodosAtivado", "1");

			FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();
			FiltroQualidadeAgua filtroQualidadeAguaRepetida = new FiltroQualidadeAgua();

			// [FS0009 - VERIFICAR EXISTENCIA MES ANTERIOR]
			String anoMesReferenciaAnterior = ""
					+ Util.subtrairMesDoAnoMes(new Integer(anoMesReferencia),1);

			filtroQualidadeAgua
					.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroQualidadeAgua
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");

			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
					FiltroQualidadeAgua.ANO_MES_REFERENCIA,
					anoMesReferenciaAnterior));

			Collection colecaoQualidadeAgua = this.getFachada().pesquisar(
					filtroQualidadeAgua, QualidadeAgua.class.getName());

			// ------------[VERIFICANDO A EXISTENCIA DE QUALIDADE AGUA PARA M�S
			// ATUAL]---------

			filtroQualidadeAguaRepetida
					.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroQualidadeAguaRepetida
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroQualidadeAguaRepetida
					.adicionarParametro(new ParametroSimples(
							FiltroQualidadeAgua.ANO_MES_REFERENCIA,
							anoMesReferencia));

			Collection colecaoQualidadeAguaRepetida = this.getFachada()
					.pesquisar(filtroQualidadeAguaRepetida,
							QualidadeAgua.class.getName());

			if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {
				form.setIncluirTodos("");
				sessao.removeAttribute("inserirTodosAtivado");
				throw new ActionServletException(
						"atencao.mes_anterior_inexistente");
			} else if (colecaoQualidadeAguaRepetida != null
					&& !colecaoQualidadeAguaRepetida.isEmpty()) {
				throw new ActionServletException(
						"atencao.faturamento.qualidade_agua_existente_colecao",
						null, form.getReferencia());
			} else {

				Iterator iteratorColecaoQualidadeAgua = colecaoQualidadeAgua
						.iterator();

				while (iteratorColecaoQualidadeAgua.hasNext()) {

					QualidadeAgua qualidadeAgua = (QualidadeAgua) iteratorColecaoQualidadeAgua
							.next();

					qualidadeAgua.setId(null);
					qualidadeAgua.setUltimaAlteracao(new Date());
					qualidadeAgua.setAnoMesReferencia(new Integer(
							anoMesReferencia));
				}

				sessao.setAttribute("colecaoQualidadeAgua",
						colecaoQualidadeAgua);
			}

		} else if ((indicadorInserirTodos != null && !indicadorInserirTodos
				.equals(""))
				&& (form.getReferencia() == null || form.getReferencia()
						.equals(""))) {

			throw new ActionServletException("atencao.informar_referencia");
		} else {

			sessao.removeAttribute("inserirTodosAtivado");

			// [FS0001 - VALIDAR REFER�NCIA]
			if ((form.getReferencia() != null && !form.getReferencia().equals(
					""))) {

				String anoMesReferencia = Util
						.formatarMesAnoParaAnoMesSemBarra(form.getReferencia());

				SistemaParametro sistemaParametro = this.getFachada()
						.pesquisarParametrosDoSistema();
				String anoMesFuturo = ""
						+ Util.somaUmMesAnoMesReferencia(sistemaParametro
								.getAnoMesFaturamento());

				if (Util.compararAnoMesReferencia(anoMesReferencia,
						anoMesFuturo, ">")) {

					httpServletRequest.setAttribute("nomeCampo", "referencia");
					throw new ActionServletException("atencao.mes_ano_menor");
				}
			}

			// [FS0002 - VERIFICAR EXISTENCIA DA LOCALIDADE]
			if ((form.getIdLocalidade() != null && !form.getIdLocalidade()
					.trim().equals(""))) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.limparListaParametros();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID,
						new Integer(form.getIdLocalidade())));

				Collection colecaoLocalidade = this.getFachada().pesquisar(
						filtroLocalidade, Localidade.class.getName());

				if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

					Localidade localidade = (Localidade) colecaoLocalidade
							.iterator().next();
					form.setIdLocalidade("" + localidade.getId());
					form.setLocalidadeDescricao(localidade.getDescricao());
					httpServletRequest.setAttribute("localidadeEncontrada",
							"true");
					httpServletRequest.setAttribute("nomeCampo",
							"idSetorComercial");

				} else {
					httpServletRequest.setAttribute("localidadeEncontrada",
							"exception");
					form.setIdLocalidade("");
					form.setLocalidadeDescricao("LOCALIDADE INEXISTENTE");
					form.setIdSetorComercial("");
					form.setSetorComercialDescricao("");
					httpServletRequest
							.setAttribute("nomeCampo", "idLocalidade");
				}

			}

			// [FS0003 - VERIFICAR EXISTENCIA DO SETOR COMERCIAL]
			if (form.getIdSetorComercial() != null
					&& !form.getIdSetorComercial().toString().trim()
							.equalsIgnoreCase("")) {

				if (form.getIdLocalidade() != null
						&& !form.getIdLocalidade().toString().trim()
								.equalsIgnoreCase("")) {

					Collection colecaoSetoresComerciais = this
							.pesquisarSetorComercial(new Integer(form
									.getIdLocalidade()), new Integer(form
									.getIdSetorComercial()));

					if (colecaoSetoresComerciais != null
							&& !colecaoSetoresComerciais.isEmpty()) {

						SetorComercial setorComercial = (SetorComercial) colecaoSetoresComerciais
								.iterator().next();

						form.setIdSetorComercial(""
								+ setorComercial.getCodigo());
						form.setSetorComercialDescricao(setorComercial
								.getDescricao());

						httpServletRequest.setAttribute(
								"codigoSetorComercialEncontrado", "true");
						httpServletRequest.setAttribute("nomeCampo",
								"fonteCaptacao");

					} else {
						httpServletRequest.setAttribute(
								"codigoSetorComercialEncontrado", "exception");
						httpServletRequest.setAttribute("nomeCampo",
								"idSetorComercial");

						form.setIdSetorComercial("");
						form
								.setSetorComercialDescricao("SETOR COMERCIAL INEXISTENTE");

					}
				}

			} else {
				form.setIdSetorComercial("");
				form.setSetorComercialDescricao("");
			}

		}
		
		Fachada fachada = Fachada.getInstancia();

		FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<SistemaAbastecimento> colecaoSistemaAbastecimento = fachada.pesquisar(filtroSistemaAbastecimento,
				SistemaAbastecimento.class.getName());

		if (colecaoSistemaAbastecimento == null || colecaoSistemaAbastecimento.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Sistema Abastecimento");
		}
		
		//Manda valores do Sistema de Abastecimento para Sess�o
		httpServletRequest.setAttribute("colecaoSistemaAbastecimento", colecaoSistemaAbastecimento);
		
		if((form.getIdLocalidade()!=null && !form.getIdLocalidade().equals(""))
				|| (form.getFonteCaptacao()!=null && !form.getFonteCaptacao().equals("-1"))
					|| (form.getIdSetorComercial()!=null && !form.getIdSetorComercial().equals(""))){
			httpServletRequest.setAttribute("validaCampos", "localidade");
		}

		if(form.getSistemaAbastecimento()!=null&& !form.getSistemaAbastecimento().equals("-1")){
			httpServletRequest.setAttribute("validaCampos", "sistema");
		}

		this.montaColecaoFonteCaptacao(form, httpServletRequest);

		return retorno;
	}

	/**
	 * Monta a colecao de Fontes de Captacao
	 * 
	 * @author Rafael Pinto
	 * @date 15/10/2008
	 */
	private void montaColecaoFonteCaptacao(InserirQualidadeAguaActionForm form,
			HttpServletRequest httpServletRequest) {

		String localidade = form.getIdLocalidade();
		String setorComercial = form.getIdSetorComercial();
		Collection<FonteCaptacao> colecaoFonteCaptacao = new ArrayList<FonteCaptacao>();

		// Pesquisa fontes de captacao por setor comercial
		if (localidade != null && !localidade.equals("")
				&& setorComercial != null && !setorComercial.equals("")) {

			Collection colecaoSetoresComerciais = this.pesquisarSetorComercial(
					new Integer(localidade), new Integer(setorComercial));

			if (colecaoSetoresComerciais != null
					&& !colecaoSetoresComerciais.isEmpty()) {

				SetorComercial setor = (SetorComercial) colecaoSetoresComerciais
						.iterator().next();

				Collection colecaoSetor = new ArrayList();
				colecaoSetor.add(setor);

				colecaoFonteCaptacao = this.getFachada()
						.pesquisarFonteCaptacao(colecaoSetor);
			}

		} else if (localidade != null && !localidade.equals("")) {

			Collection colecaoSetoresComerciais = this.pesquisarSetorComercial(
					new Integer(localidade), null);

			colecaoFonteCaptacao = this.getFachada().pesquisarFonteCaptacao(
					colecaoSetoresComerciais);
		} else {

			FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();

			filtroFonteCaptacao.limparListaParametros();
			filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
					FiltroFonteCaptacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoFonteCaptacao = this.getFachada().pesquisar(
					filtroFonteCaptacao, FonteCaptacao.class.getName());
		}

		if (colecaoFonteCaptacao != null && !colecaoFonteCaptacao.isEmpty()) {
			
			if(colecaoFonteCaptacao.size() == 1){
				
				FonteCaptacao fonte = (FonteCaptacao) Util.retonarObjetoDeColecao(colecaoFonteCaptacao);
				form.setFonteCaptacao(fonte.getId().toString());
			}
			
			httpServletRequest.setAttribute("colecaoFonteCaptacao",colecaoFonteCaptacao);
		}

	}

	/**
	 * Pesquisa o SetorComercial
	 * 
	 * @author Rafael Pinto
	 * @date 15/10/2008
	 */
	private Collection<SetorComercial> pesquisarSetorComercial(
			Integer localidade, Integer setorComercial) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		filtroSetorComercial.limparListaParametros();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.ID_LOCALIDADE, localidade));

		if (setorComercial != null) {

			filtroSetorComercial
					.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
							setorComercial));
		}

		return this.getFachada().pesquisar(filtroSetorComercial,
				SetorComercial.class.getName());
	}

}

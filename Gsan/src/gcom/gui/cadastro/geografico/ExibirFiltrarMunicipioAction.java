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
package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.FiltroRegiao;
import gcom.cadastro.geografico.FiltroRegiaoDesenvolvimento;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.Regiao;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0006] FILTRAR MUNICIPIO
 * 
 * @author K�ssia Albuquerque
 * @date 03/01/2007
 */

public class ExibirFiltrarMunicipioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarMunicipio");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		if (sessao.getAttribute("consulta") != null) {
			sessao.removeAttribute("consulta");
		}

		FiltrarMunicipioActionForm form = (FiltrarMunicipioActionForm) actionForm;

		// C�digo para checar ou n�o o ATUALIZAR

		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			form.setIndicadorUso("3");
			form.setOrdernarPor("1");
			form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
					.toString());

		}

		// Verificar a existencia de dados

		// REGIAO DESENVOLVIMENTO

		FiltroRegiaoDesenvolvimento filtroRegiaoDesenv = new FiltroRegiaoDesenvolvimento();

		filtroRegiaoDesenv
				.setCampoOrderBy(FiltroRegiaoDesenvolvimento.DESCRICAO);

		Collection<RegiaoDesenvolvimento> colecaoRegiaoDesenv = fachada
				.pesquisar(filtroRegiaoDesenv, RegiaoDesenvolvimento.class
						.getName());

		if (colecaoRegiaoDesenv == null || colecaoRegiaoDesenv.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Regi�o Desenvolvimento");
		}

		httpServletRequest.setAttribute("colecaoRegiaoDesenv",
				colecaoRegiaoDesenv);

		// REGIAO

		if (httpServletRequest.getParameter("carregarRegioes") != null) {
			
			Collection<Regiao> regioes = new ArrayList();

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.UNIDADE_FEDERACAO_ID, form.getUnidadeFederacao()));
			
			filtroMunicipio
			.adicionarCaminhoParaCarregamentoEntidade(FiltroMunicipio.MICRORREGICAO);
			
			filtroMunicipio
					.adicionarCaminhoParaCarregamentoEntidade(FiltroMunicipio.REGIAO);

			Collection colecaoMunicipios = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoMunicipios != null && !colecaoMunicipios.isEmpty()) {
				for (Iterator iter = colecaoMunicipios.iterator(); iter
						.hasNext();) {
					Municipio element = (Municipio) iter.next();
					if (!regioes.contains(element
							.getMicrorregiao().getRegiao())) {
						regioes.add(element.getMicrorregiao().getRegiao());
					}

				}
			}

			httpServletRequest.setAttribute("colecaoRegiao",
					regioes);
			
		} else {

			FiltroRegiao filtroRegiao = new FiltroRegiao();

			filtroRegiao.setCampoOrderBy(FiltroRegiao.DESCRICAO);

			Collection<Regiao> colecaoRegiao = fachada.pesquisar(filtroRegiao,
					Regiao.class.getName());

			if (colecaoRegiao == null || colecaoRegiao.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Regi�o");
			}
			
			httpServletRequest.setAttribute("colecaoRegiao", colecaoRegiao);
		}

		

		// MICRORREGIAO

		Collection<Microrregiao> colecaoMicrorregiao = new ArrayList();

		FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();

		if (httpServletRequest.getParameter("combo") != null
				&& !form.getRegiao().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			filtroMicrorregiao.adicionarParametro(new ParametroSimples(
					FiltroMicrorregiao.REGIAO_ID, form.getRegiao()));

			filtroMicrorregiao.setCampoOrderBy(FiltroMicrorregiao.DESCRICAO);

			colecaoMicrorregiao = fachada.pesquisar(filtroMicrorregiao,
					Microrregiao.class.getName());

			if (colecaoMicrorregiao == null || colecaoMicrorregiao.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Microrregi�o");
			}

			httpServletRequest.setAttribute("colecaoMicrorregiao",
					colecaoMicrorregiao);
		}

		// UNIDADE FEDERA��O

		if (httpServletRequest.getParameter("combo") != null
				&& !form.getRegiao().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			Collection<UnidadeFederacao> unidadesFederacao = new ArrayList();

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.REGIAO_ID, form.getRegiao()));

			filtroMunicipio
					.adicionarCaminhoParaCarregamentoEntidade(FiltroMunicipio.UNIDADE_FEDERACAO);

			Collection colecaoMunicipios = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoMunicipios != null && !colecaoMunicipios.isEmpty()) {
				for (Iterator iter = colecaoMunicipios.iterator(); iter
						.hasNext();) {
					Municipio element = (Municipio) iter.next();
					if (!unidadesFederacao.contains(element
							.getUnidadeFederacao())) {
						unidadesFederacao.add(element.getUnidadeFederacao());
					}

				}
			}

			httpServletRequest.setAttribute("colecaoUnidadeFederacao",
					unidadesFederacao);

		} else {

			FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();

			filtroUnidadeFederacao
					.setCampoOrderBy(FiltroUnidadeFederacao.DESCRICAO);

			Collection<UnidadeFederacao> colecaoUnidadeFederacao = fachada
					.pesquisar(filtroUnidadeFederacao, UnidadeFederacao.class
							.getName());

			if (colecaoUnidadeFederacao == null
					|| colecaoUnidadeFederacao.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Unidade Federa��o");
			}

			httpServletRequest.setAttribute("colecaoUnidadeFederacao",
					colecaoUnidadeFederacao);

		}

		// Se voltou da tela de Atualizar Localidade
		if (sessao.getAttribute("voltar") != null
				&& sessao.getAttribute("voltar").equals("filtrar")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			if (sessao.getAttribute("tipoPesquisa") != null) {
				form.setTipoPesquisa(sessao.getAttribute("tipoPesquisa")
						.toString());
			}
		}
		sessao.removeAttribute("voltar");
		sessao.removeAttribute("tipoPesquisa");

		return retorno;
	}
}

/**
 * 
 */
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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0???]FILTRAR Unidade Negocio
 * 
 * @author R�mulo Aur�lio
 * @date 30/09/2008
 */

public class FiltrarUnidadeNegocioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterUnidadeNegocio");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarUnidadeNegocioActionForm form = (FiltrarUnidadeNegocioActionForm) actionForm;

		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

		boolean peloMenosUmParametroInformado = false;

		String id = form.getId();
		String nome = form.getNome();
		String nomeAbreviado = form.getNomeAbreviado();
		String idCliente = form.getIdCliente();
		String idGerenciaRegional = form.getIdGerenciaRegional();
		String cnpj = form.getNumeroCnpj();
		String indicadorUso = form.getIndicadorUso();
		String tipoPesquisa = form.getTipoPesquisa();

		// ID
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
						FiltroEmpresa.ID, id));
			}
		}
		// Nome
		if (nome != null && !nome.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroUnidadeNegocio
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroUnidadeNegocio.NOME, nome));
			} else {

				filtroUnidadeNegocio.adicionarParametro(new ComparacaoTexto(
						FiltroUnidadeNegocio.NOME, nome));
			}
		}

		// Nome Abreviado
		if (nomeAbreviado != null && !nomeAbreviado.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroUnidadeNegocio
					.adicionarParametro(new ComparacaoTextoCompleto(
							FiltroUnidadeNegocio.NOME_ABREVIADO, nomeAbreviado));
		}
		// Cliente
		if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.CLIENTE_ID, idCliente));
		}

		// Gerencia Regional
		if (idGerenciaRegional != null && !idGerenciaRegional.trim().equals("")) {

			peloMenosUmParametroInformado = true;
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.ID_GERENCIA, idGerenciaRegional));

		}
		// Cnpj

		if (cnpj != null && !cnpj.trim().equals("")) {

			peloMenosUmParametroInformado = true;
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.CNPJ, cnpj));

		}

		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO, indicadorUso));
		}

		filtroUnidadeNegocio
				.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeNegocio.CLIENTE);
		filtroUnidadeNegocio
				.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeNegocio.GERENCIA);

		Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		// Verificar a existencia
		if (colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Unidade de Negocio");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}

		// Pesquisa sem registros

		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"Unidade de Negocio");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio",
					colecaoUnidadeNegocio);
			UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
			unidadeNegocio = (UnidadeNegocio) Util
					.retonarObjetoDeColecao(colecaoUnidadeNegocio);
			String idRegistroAtualizar = unidadeNegocio.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroUnidadeNegocio", filtroUnidadeNegocio);

		httpServletRequest.setAttribute("filtroUnidadeNegocio",
				filtroUnidadeNegocio);

		// Verifica se o checkbox Atualizar est� marcado e em caso afirmativo
		// manda pelo um request uma vari�vel para o
		// ExibirManterFuncionalidadeAction e nele verificar se ir� para o
		// atualizar ou para o manter
		if (form.getAtualizar() != null
				&& form.getAtualizar().equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar", form.getAtualizar());

		}

		return retorno;
	}

}

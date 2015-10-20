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
 * Ivan S�rgio Virginio da Silva J�nior
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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOSReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1213] Emitir Relatorio de Ordem de Sercico de Fiscalizacao
 * 
 * @author Paulo Diniz
 * @date 09/08/2011
 * 
 */
public class ExibirFiltrarRelatorioOrdensServicoFiscalizacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirFiltrar");

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm relatorioActionForm = (ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm) actionForm;

		// Gerencia Regional
		if (sessao.getAttribute("collGerenciaRegional") == null) {
			FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional(FiltroGerenciaRegional.NOME);
			filtroGerencia.adicionarParametro(new ParametroSimples(	FiltroGerenciaRegional.INDICADOR_USO,
																	"1"));
			Collection collGerenciaRegional = fachada.pesquisar(filtroGerencia, GerenciaRegional.class.getName());
			sessao.setAttribute("collGerenciaRegional", collGerenciaRegional);
		}

		// Unidade de Negocio
		if (sessao.getAttribute("collUnidadeNegocio") == null) {
			FiltroUnidadeNegocio filtroUnidade = new FiltroUnidadeNegocio(FiltroUnidadeNegocio.NOME);
			filtroUnidade.adicionarParametro(new ParametroSimples(	FiltroUnidadeNegocio.INDICADOR_USO,
																	"1"));
			Collection collUnidadeNegocio = fachada.pesquisar(filtroUnidade, UnidadeNegocio.class.getName());
			sessao.setAttribute("collUnidadeNegocio", collUnidadeNegocio);
		}

		// Tipo de Retorno
		if (sessao.getAttribute("collTipoRetorno") == null) {
			FiltroOSReferidaRetornoTipo filtroRetornoTipo = new FiltroOSReferidaRetornoTipo(FiltroOSReferidaRetornoTipo.DESCRICAO);
			filtroRetornoTipo.adicionarParametro(new ParametroSimples(	FiltroOSReferidaRetornoTipo.INDICADOR_USO,
																		"1"));
			Collection collTipoRetorno = fachada.pesquisar(filtroRetornoTipo, OsReferidaRetornoTipo.class.getName());
			sessao.setAttribute("collTipoRetorno", collTipoRetorno);
		}

		// Localidade Inicial
		String idLocalidadeInicio = pesquisarLocalidadeInicial(httpServletRequest, fachada, relatorioActionForm);

		// Localidade Final
		pesquisarLocalidadeFinal(httpServletRequest, fachada, relatorioActionForm, idLocalidadeInicio);

		return retorno;
	}

	private String pesquisarLocalidadeInicial(HttpServletRequest httpServletRequest, Fachada fachada,
			ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm form) {
		String idLocalidadeInicial = form.getLocalidadeInicial();
		Localidade localidadeInicial = null;

		if (idLocalidadeInicial != null && !idLocalidadeInicial.trim().equals("")) {
			FiltroLocalidade filtroLocalidadeInicial = new FiltroLocalidade();
			filtroLocalidadeInicial.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,
																			idLocalidadeInicial));

			Collection colecaoLocalidadeInicial = fachada.pesquisar(filtroLocalidadeInicial, Localidade.class.getName());

			if (colecaoLocalidadeInicial != null && !colecaoLocalidadeInicial.isEmpty()) {
				localidadeInicial = (Localidade) colecaoLocalidadeInicial.iterator().next();
				form.setDescricaoLocalidadeInicial(localidadeInicial.getDescricao());
			} else {
				httpServletRequest.setAttribute("localidadeInicialInexistente", "true");
				form.setLocalidadeInicial("");
				form.setDescricaoLocalidadeInicial("LOCALIDADE INEXISTENTE");
			}
		} else {
			form.setDescricaoLocalidadeInicial("");
		}
		return idLocalidadeInicial;
	}

	private void pesquisarLocalidadeFinal(HttpServletRequest httpServletRequest, Fachada fachada,
			ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm form, String idLocalidadeInicial) {
		String idLocalidadeFinal = form.getLocalidadeFinal();
		Localidade localidadeFinal = null;

		if (idLocalidadeFinal != null && !idLocalidadeFinal.trim().equals("")) {
			if (idLocalidadeInicial != null && !idLocalidadeInicial.trim().equals("")) {
				if ((new Integer(idLocalidadeInicial)).compareTo(new Integer(idLocalidadeFinal)) > 0) {
					throw new ActionServletException("atencao.localidade.final.maior.localidade.inicial");
				}
			}

			FiltroLocalidade filtroLocalidadeFinal = new FiltroLocalidade();
			filtroLocalidadeFinal.adicionarParametro(new ParametroSimples(	FiltroLocalidade.ID,
																			idLocalidadeFinal));

			Collection colecaoLocalidadeFinal = fachada.pesquisar(filtroLocalidadeFinal, Localidade.class.getName());

			if (colecaoLocalidadeFinal != null && !colecaoLocalidadeFinal.isEmpty()) {
				localidadeFinal = (Localidade) colecaoLocalidadeFinal.iterator().next();
				form.setDescricaoLocalidadeFinal(localidadeFinal.getDescricao());
			} else {
				httpServletRequest.setAttribute("localidadeFinalInexistente", "true");
				form.setLocalidadeFinal("");
				form.setDescricaoLocalidadeFinal("LOCALIDADE INEXISTENTE");
			}
		} else {
			form.setDescricaoLocalidadeFinal("");
		}
	}

}
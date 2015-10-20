/*
 * Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
 * Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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

package gcom.gui.relatorio.cadastro;

import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.cadastro.imovel.FiltroEntidadeBeneficente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * [UC1174] - Gerar Relatorio Imoveis com Doa��es.
 * 
 * @author Erivan Sousa
 * 
 * @date 01/06/2011,17/06/2011
 */

public class ExibirGerarRelatorioImoveisDoacoesAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioImoveisDoacoes");

		HttpSession sessao = httpServletRequest.getSession(false);

		GerarRelatorioImoveisDoacoesActionForm form = (GerarRelatorioImoveisDoacoesActionForm) actionForm;

		// Verifica se entrou apartir
		// do menu

		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").toString()
						.equalsIgnoreCase("sim")) {

			this.limpar(form, sessao);

		}

		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest
				.getParameter("objetoConsulta");
		// Pesquisar Imovel
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")) {
			if (objetoConsulta.trim().equals("1")) {
				// Faz a consulta do Imovel
				this.pesquisarImovel(form);
			}
			if (objetoConsulta.trim().equals("2")) {
				// Faz a consulta do Imovel
				this.pesquisarUsuarioAdesao(form);
			}
			if (objetoConsulta.trim().equals("3")) {
				// Faz a consulta do Imovel
				this.pesquisarUsuarioCancelamento(form);
			}
		}
		this.pesquisarEntidadeBeneficente(httpServletRequest);
		this.setaRequest(httpServletRequest, form);

		return retorno;
	}

	private void limpar(GerarRelatorioImoveisDoacoesActionForm form,
			HttpSession sessao) {

		form.setEntidadeBeneficente("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
		form.setImovel("");
		form.setPeriodoAdesaoFinal("");
		form.setPeriodoAdesaoInicial("");
		form.setPeriodoCancelamentoFinal("");
		form.setPeriodoCancelamentoInicial("");
		form.setReferenciaFimDoacaoInicial("");
		form.setReferenciaFimDoacaoFinal("");
		form.setReferenciaInicioDoacaoInicial("");
		form.setReferenciaInicioDoacaoFinal("");
		form.setOpcaoRelatorio("1");

		sessao.removeAttribute("imovelEncontrado");
		sessao.removeAttribute("usuarioAdesaoEncontrado");
		sessao.removeAttribute("usuarioCancelamentoEncontrado");
	}

	/**
	 * Pesquisa Entidade Beneficente
	 * 
	 * @author Erivan Sousa
	 * @date 01/06/2011
	 */
	private void pesquisarEntidadeBeneficente(
			HttpServletRequest httpServletRequest) {

		FiltroEntidadeBeneficente filtroEntidadeBeneficente = new FiltroEntidadeBeneficente();

		filtroEntidadeBeneficente.setConsultaSemLimites(true);
		filtroEntidadeBeneficente.setCampoOrderBy(FiltroEntidadeBeneficente.ID);
		filtroEntidadeBeneficente
				.adicionarCaminhoParaCarregamentoEntidade(FiltroEntidadeBeneficente.CLIENTE);

		Collection colecaoEntidadeBeneficente = this.getFachada().pesquisar(
				filtroEntidadeBeneficente, EntidadeBeneficente.class.getName());

		if (colecaoEntidadeBeneficente == null
				|| colecaoEntidadeBeneficente.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Entidade Beneficente");
		} else {
			httpServletRequest.setAttribute("colecaoEntidadeBeneficente",
					colecaoEntidadeBeneficente);
		}
	}

	/**
	 * Pesquisa Imovel
	 * 
	 * @author Erivan Sousa
	 * @date 24/05/2011
	 */
	private void pesquisarImovel(GerarRelatorioImoveisDoacoesActionForm form) {

		Object local = form.getImovel();

		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, local));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
		// Recupera Localidade
		Collection colecaoImovel = this.getFachada().pesquisar(filtroImovel,
				Imovel.class.getName());

		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);

			form.setImovel(imovel.getId().toString());
			form.setNomeImovel(imovel.getInscricaoFormatada());

		} else {
			form.setImovel("");
			form.setNomeImovel("Im�vel inexistente");
		}
	}

	/**
	 * Pesquisa Usuario Ades�o
	 * 
	 * @author Erivan Sousa
	 * @date 24/05/2011
	 */
	private void pesquisarUsuarioAdesao(
			GerarRelatorioImoveisDoacoesActionForm form) {

		Object local = form.getUsuarioAdesao();

		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuario.LOGIN, local));

		// Recupera Localidade
		Collection colecaoUsuario = this.getFachada().pesquisar(filtroUsuario,
				Usuario.class.getName());

		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {

			Usuario usuario = (Usuario) Util
					.retonarObjetoDeColecao(colecaoUsuario);

			form.setUsuarioAdesao(usuario.getLogin().toString());
			form.setNomeUsuarioAdesao(usuario.getNomeUsuario());

		} else {
			form.setUsuarioAdesao("");
			form.setNomeUsuarioAdesao("Usu�rio inexistente");
		}
	}

	/**
	 * Pesquisa Usuario Cancelamento
	 * 
	 * @author Erivan Sousa
	 * @date 24/05/2011
	 */
	private void pesquisarUsuarioCancelamento(
			GerarRelatorioImoveisDoacoesActionForm form) {

		Object local = form.getUsuarioCancelamento();

		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuario.LOGIN, local));

		// Recupera Localidade
		Collection colecaoUsuario = this.getFachada().pesquisar(filtroUsuario,
				Usuario.class.getName());

		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {

			Usuario usuario = (Usuario) Util
					.retonarObjetoDeColecao(colecaoUsuario);

			form.setUsuarioCancelamento(usuario.getLogin().toString());
			form.setNomeUsuarioCancelamento(usuario.getNomeUsuario());

		} else {
			form.setUsuarioCancelamento("");
			form.setNomeUsuarioCancelamento("Usu�rio inexistente");
		}
	}

	private void setaRequest(HttpServletRequest httpServletRequest,
			GerarRelatorioImoveisDoacoesActionForm form) {

		// Imovel
		if (form.getImovel() != null && !form.getImovel().equals("")) {
			httpServletRequest.setAttribute("imovelEncontrado", "true");
		}
		if (form.getUsuarioAdesao() != null
				&& !form.getUsuarioAdesao().equals("")) {
			httpServletRequest.setAttribute("usuarioAdesaoEncontrado", "true");
		}
		if (form.getUsuarioCancelamento() != null
				&& !form.getUsuarioCancelamento().equals("")) {
			httpServletRequest.setAttribute("usuarioCancelamentoEncontrado",
					"true");
		}
	}
}
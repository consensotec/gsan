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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite filtrar resolu��es de diretoria [UC0219] Filtrar Resolu��o de
 * Diretoria
 * 
 * @author Rafael Corr�a
 * @since 31/03/2006
 */
public class ExibirFiltrarRelatorioGestaoRAAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		FiltrarRelatorioGestaoRAActionForm filtrarRelatorioGestaoRAActionForm = (FiltrarRelatorioGestaoRAActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarRelatorioGestaoRA");

		if (httpServletRequest.getParameter("menu") != null) {
			filtrarRelatorioGestaoRAActionForm.setSituacaoRA("");
		}

		// Carrega as cole��es necess�rias para a exibi��o da p�gina
		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();

		filtroSolicitacaoTipo.setCampoOrderBy(FiltroSolicitacaoTipo.DESCRICAO);

		Collection<SolicitacaoTipo> colecaoSolicitacaoTipo = fachada.pesquisar(
				filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());

		if (colecaoSolicitacaoTipo != null && !colecaoSolicitacaoTipo.isEmpty()) {
			httpServletRequest.setAttribute("colecaoSolicitacaoTipo",
					colecaoSolicitacaoTipo);
		}

		// FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao
		// = new FiltroSolicitacaoTipoEspecificacao();
		//
		// Collection colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
		// filtroSolicitacaoTipoEspecificacao,
		// SolicitacaoTipoEspecificacao.class.getName());
		//
		// if (colecaoSolicitacaoTipoEspecificacao != null
		// && !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
		// httpServletRequest.setAttribute(
		// "colecaoSolicitacaoTipoEspecificacao",
		// colecaoSolicitacaoTipoEspecificacao);
		// }

		String[] solicitacaoTipo = filtrarRelatorioGestaoRAActionForm.getSolicitacaoTipo();
		if (solicitacaoTipo != null) {
			filtrarRelatorioGestaoRAActionForm
					.setSelectedSolicitacaoTipoSize("" + solicitacaoTipo.length);
			if (solicitacaoTipo.length == 1) {
				// Filtra Solicita��o Tipo Especifica��o
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID,
								solicitacaoTipo[0]));
				filtroSolicitacaoTipoEspecificacao
						.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);

				Collection<SolicitacaoTipoEspecificacao> colecaoSolicitacaoTipoEspecificacao = fachada
						.pesquisar(filtroSolicitacaoTipoEspecificacao,
								SolicitacaoTipoEspecificacao.class.getName());

				if (colecaoSolicitacaoTipoEspecificacao != null
						&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
					httpServletRequest.setAttribute(
							"colecaoSolicitacaoTipoEspecificacao",
							colecaoSolicitacaoTipoEspecificacao);
				} else {
					httpServletRequest.setAttribute(
							"colecaoSolicitacaoTipoEspecificacao",
							new ArrayList<SolicitacaoTipoEspecificacao>());
				}

			} else if (solicitacaoTipo.length == 2
					&& solicitacaoTipo[0].equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				// Filtra Solicita��o Tipo Especifica��o
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID,
								solicitacaoTipo[1]));
				filtroSolicitacaoTipoEspecificacao
						.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);

				Collection<SolicitacaoTipoEspecificacao> colecaoSolicitacaoTipoEspecificacao = fachada
						.pesquisar(filtroSolicitacaoTipoEspecificacao,
								SolicitacaoTipoEspecificacao.class.getName());

				if (colecaoSolicitacaoTipoEspecificacao != null
						&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
					httpServletRequest.setAttribute(
							"colecaoSolicitacaoTipoEspecificacao",
							colecaoSolicitacaoTipoEspecificacao);
				} else {
					httpServletRequest.setAttribute(
							"colecaoSolicitacaoTipoEspecificacao",
							new ArrayList<SolicitacaoTipoEspecificacao>());
				}
			} else {
				httpServletRequest.setAttribute("colecaoSolicitacaoTipoEspecificacao",
						new ArrayList<SolicitacaoTipoEspecificacao>());
			}
		} else {
			filtrarRelatorioGestaoRAActionForm
					.setSelectedSolicitacaoTipoSize("0");
			httpServletRequest.setAttribute("colecaoSolicitacaoTipoEspecificacao",
					new ArrayList<SolicitacaoTipoEspecificacao>());
		}

		String idUnidade = filtrarRelatorioGestaoRAActionForm.getIdUnidade();
		String nomeUnidade = filtrarRelatorioGestaoRAActionForm
				.getNomeUnidade();

		// Verifica se o usu�rio solicitou a pesquisa de unidade organizacional
		if ((idUnidade != null && !idUnidade.trim().equals(""))
				&& (nomeUnidade == null || nomeUnidade.equals(""))) {
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID, idUnidade));

			Collection<UnidadeOrganizacional> colecaoUnidade = fachada.pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class
							.getName());

			if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
				UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util
						.retonarObjetoDeColecao(colecaoUnidade);

				filtrarRelatorioGestaoRAActionForm.setNomeUnidade(unidade
						.getDescricao());
				httpServletRequest.setAttribute("nomeCampo",
						"idUnidadeSuperior");

			} else {
				filtrarRelatorioGestaoRAActionForm.setIdUnidade("");
				filtrarRelatorioGestaoRAActionForm
						.setNomeUnidade("UNIDADE INEXISTENTE");

				httpServletRequest.setAttribute("nomeCampo", "idUnidade");
				httpServletRequest.setAttribute("idUnidadeNaoEncontrado", true);
			}
		} else if ((nomeUnidade != null && !nomeUnidade.trim().equals(""))
				&& (idUnidade == null || idUnidade.equals(""))) {
			filtrarRelatorioGestaoRAActionForm.setNomeUnidade("");
		}

		String idUnidadeSuperior = filtrarRelatorioGestaoRAActionForm
				.getIdUnidadeSuperior();
		String nomeUnidadeSuperior = filtrarRelatorioGestaoRAActionForm
				.getNomeUnidadeSuperior();

		// Verifica se o usu�rio solicitou a pesquisa de unidade superior
		if ((idUnidadeSuperior != null && !idUnidadeSuperior.trim().equals(""))
				&& (nomeUnidadeSuperior == null || nomeUnidadeSuperior
						.equals(""))) {
			FiltroUnidadeOrganizacional filtroUnidadeSuperior = new FiltroUnidadeOrganizacional();

			filtroUnidadeSuperior.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR,
					idUnidadeSuperior));

			Collection<UnidadeOrganizacional> colecaoUnidadeSuperior = fachada.pesquisar(
					filtroUnidadeSuperior, UnidadeOrganizacional.class
							.getName());

			if (colecaoUnidadeSuperior != null
					&& !colecaoUnidadeSuperior.isEmpty()) {
				UnidadeOrganizacional unidadeSuperior = (UnidadeOrganizacional) Util
						.retonarObjetoDeColecao(colecaoUnidadeSuperior);

				filtrarRelatorioGestaoRAActionForm
						.setNomeUnidadeSuperior(unidadeSuperior.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "idMunicipio");

			} else {
				filtrarRelatorioGestaoRAActionForm.setIdUnidadeSuperior("");
				filtrarRelatorioGestaoRAActionForm
						.setNomeUnidadeSuperior("UNIDADE SUPERIOR INEXISTENTE");

				httpServletRequest.setAttribute("nomeCampo",
						"idUnidadeSuperior");
				httpServletRequest.setAttribute("unidadeSuperiorNaoEncontrada",
						true);
			}
		} else if ((nomeUnidadeSuperior != null && !nomeUnidadeSuperior.trim()
				.equals(""))
				&& (idUnidadeSuperior == null || idUnidadeSuperior.equals(""))) {
			filtrarRelatorioGestaoRAActionForm.setNomeUnidadeSuperior("");
		}

		String idMunicipio = filtrarRelatorioGestaoRAActionForm
				.getIdMunicipio();
		String nomeMunicipio = filtrarRelatorioGestaoRAActionForm
				.getNomeMunicipio();

		// Verifica se o usu�rio solicitou a pesquisa de munic�pio
		if ((idMunicipio != null && !idMunicipio.trim().equals(""))
				&& (nomeMunicipio == null || nomeMunicipio.equals(""))) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idMunicipio));

			Collection<Municipio> colecaoMunicipio = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
				Municipio municipio = (Municipio) Util
						.retonarObjetoDeColecao(colecaoMunicipio);

				filtrarRelatorioGestaoRAActionForm.setNomeMunicipio(municipio
						.getNome());
				httpServletRequest.setAttribute("nomeCampo", "idBairro");

			} else {
				filtrarRelatorioGestaoRAActionForm.setIdMunicipio("");
				filtrarRelatorioGestaoRAActionForm
						.setNomeMunicipio("MUNICIPIO INEXISTENTE");

				filtrarRelatorioGestaoRAActionForm.setIdBairro("");
				filtrarRelatorioGestaoRAActionForm.setNomeBairro("");

				httpServletRequest.setAttribute("nomeCampo", "idMunicipio");
				httpServletRequest.setAttribute("municipioNaoEncontrado", true);
			}
		} else if ((nomeMunicipio != null && !nomeMunicipio.trim().equals(""))
				&& (idMunicipio == null || idMunicipio.equals(""))) {
			filtrarRelatorioGestaoRAActionForm.setNomeMunicipio("");
		}

		String codigoBairro = filtrarRelatorioGestaoRAActionForm.getIdBairro();
		String nomeBairro = filtrarRelatorioGestaoRAActionForm.getNomeBairro();

		// Verifica se o usu�rio solicitou a pesquisa de bairro
		if ((codigoBairro != null && !codigoBairro.trim().equals(""))
				&& (nomeBairro == null || nomeBairro.equals(""))) {
			FiltroBairro filtroBairro = new FiltroBairro();
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, codigoBairro));
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.MUNICIPIO_ID, idMunicipio));

			Collection<Bairro> colecaoBairro = fachada.pesquisar(filtroBairro,
					Bairro.class.getName());

			if (colecaoBairro != null && !colecaoBairro.isEmpty()) {
				Bairro bairro = (Bairro) Util
						.retonarObjetoDeColecao(colecaoBairro);

				filtrarRelatorioGestaoRAActionForm.setNomeBairro(bairro
						.getNome());

			} else {
				filtrarRelatorioGestaoRAActionForm.setIdBairro("");
				filtrarRelatorioGestaoRAActionForm
						.setNomeBairro("BAIRRO INEXISTENTE");

				httpServletRequest.setAttribute("nomeCampo", "idBairro");
				httpServletRequest.setAttribute("bairroNaoEncontrado", true);
			}
		} else if ((nomeBairro != null && !nomeBairro.trim().equals(""))
				&& (codigoBairro == null || codigoBairro.equals(""))) {
			filtrarRelatorioGestaoRAActionForm.setNomeBairro("");
		}

		return retorno;

	}

}

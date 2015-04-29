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
package gsan.gui.cadastro.localidade;

import gsan.cadastro.geografico.FiltroMunicipio;
import gsan.cadastro.geografico.Municipio;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.operacional.FiltroFonteCaptacao;
import gsan.operacional.FonteCaptacao;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarSetorComercialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarSetorComercial");

		// Obt�m a sess�o
		HttpSession sessao = this.getSessao(httpServletRequest);

		PesquisarAtualizarSetorComercialActionForm form = (PesquisarAtualizarSetorComercialActionForm) actionForm;

		String setorComercialID = (String) httpServletRequest
				.getParameter("setorComercialID");

		if (setorComercialID == null) {
			if (httpServletRequest.getAttribute("setorComercialID") != null) {
				setorComercialID = (String) httpServletRequest.getAttribute(
						"setorComercialID").toString();
			}
		}

		// PERMISS�O PARA BLOQUEIO ALTERA��O DE IM�VEIS
		boolean permissaoEspecialBloqueio = this.getFachada()
				.verificarPermissaoEspecial(
						PermissaoEspecial.BLOQUEAR_ALTERACAO_IMOVEIS,
						(Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO));

		if (permissaoEspecialBloqueio) {
			httpServletRequest.setAttribute("pemissaoIndicadorBloqueio",
					SetorComercial.BLOQUEIO_INSERIR_IMOVEL_SIM.intValue());
		} else {
			httpServletRequest.setAttribute("pemissaoIndicadorBloqueio",
					SetorComercial.BLOQUEIO_INSERIR_IMOVEL_NAO.intValue());
			if (form != null) {
				if (form.getIndicadorBloqueio() != null
						&& form.getIndicadorBloqueio().equals("1")) {
					httpServletRequest.setAttribute("bloqueio", true);
				} else {
					httpServletRequest.setAttribute("bloqueio", false);
				}
			}
		}

		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");
		String acao = (String) httpServletRequest.getParameter("acao");

		Collection colecaoPesquisa = null;

		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")) {

			String localidadeID = null;

			switch (Integer.parseInt(objetoConsulta)) {

			// Localidade
			case 1:
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				// Recebe o valor do campo localidadeID do formul�rio.
				localidadeID = form.getLocalidadeID();

				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, localidadeID));

				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna localidade
				colecaoPesquisa = this.getFachada().pesquisar(filtroLocalidade,
						Localidade.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {

					// Localidade nao encontrada
					// Limpa os campos localidadeID e localidadeNome do
					// formul�rio
					httpServletRequest.setAttribute("corLocalidade",
							"exception");
					form.setLocalidadeID("");
					form.setLocalidadeNome("Localidade inexistente.");
				} else {
					httpServletRequest.setAttribute("corLocalidade", "valor");
					Localidade objetoLocalidade = (Localidade) Util
							.retonarObjetoDeColecao(colecaoPesquisa);

					form.setLocalidadeID(String.valueOf(objetoLocalidade
							.getId()));
					form.setLocalidadeNome(objetoLocalidade.getDescricao());
				}

				break;

			// Municipio
			case 2:
				FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

				// Recebe o valor do campo municipioID do formul�rio.
				String municipioID = form.getMunicipioID();

				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.ID, municipioID));

				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna municipio
				colecaoPesquisa = this.getFachada().pesquisar(filtroMunicipio,
						Municipio.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {

					// Municipio nao encontrado
					// Limpa os campos municipioID e municipioNome do formul�rio
					httpServletRequest
							.setAttribute("corMunicipio", "exception");
					form.setMunicipioID("");
					form.setMunicipioNome("Munic�pio inexistente.");
				} else {
					httpServletRequest.setAttribute("corMunicipio", "valor");

					Municipio objetoMunicipio = (Municipio) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					form.setMunicipioID(String.valueOf(objetoMunicipio.getId()));
					form.setMunicipioNome(objetoMunicipio.getNome());
				}

				break;
			case 3:

				FonteCaptacao objetoFonteCaptacao = this
						.pesquisarFonteCaptacao(form.getFonteCaptacao());

				if (objetoFonteCaptacao == null) {

					form.setFonteCaptacao("");
					form.setDescricaoFonteCaptacao("Fonte de Capta��o inexistente.");
				} else {

					form.setFonteCaptacao(String.valueOf(objetoFonteCaptacao
							.getId()));
					form.setDescricaoFonteCaptacao(objetoFonteCaptacao
							.getDescricao());

					httpServletRequest.setAttribute("fonteCaptacaoEncontrada",
							true);
				}
				break;

			default:

				break;
			}
		} else if (acao != null && !acao.trim().equalsIgnoreCase("")
				&& acao.equals("A")) {

			this.montarColecaoFonte(httpServletRequest, form);

			// form.setFonteCaptacao("");
			// form.setDescricaoFonteCaptacao("");

		} else if (acao != null && !acao.trim().equalsIgnoreCase("")
				&& acao.equals("R")) {

			String idRemover = (String) httpServletRequest
					.getParameter("idRemover");

			this.removerColecaoFonte(httpServletRequest, idRemover);

		} else {
			if (setorComercialID == null
					|| setorComercialID.equalsIgnoreCase("")) {
				// ID do setor comercial n�o informado
				throw new ActionServletException(
						"atencao.codigo_setor_comercial_nao_informado");
			} else {

				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

				// Objetos que ser�o retornados pelo hibernate
				filtroSetorComercial
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroSetorComercial
						.adicionarCaminhoParaCarregamentoEntidade("municipio");

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID, setorComercialID));

				// Retorna setor comercial
				colecaoPesquisa = this.getFachada().pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor comercial n�o cadastrado
					throw new ActionServletException(
							"atencao.processo.setorComercialNaoCadastrada");
				} else {
					SetorComercial setorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);

					// Colocando o objeto na sess�o
					sessao.setAttribute("setorComercialManter", setorComercial);

					form.setSetorComercialID(setorComercialID);
					form.setLocalidadeID(String.valueOf(setorComercial
							.getLocalidade().getId()));
					form.setLocalidadeNome(setorComercial.getLocalidade()
							.getDescricao());
					form.setSetorComercialCD(String.valueOf(setorComercial
							.getCodigo()));
					form.setSetorComercialNome(setorComercial.getDescricao());
					form.setMunicipioID(String.valueOf(setorComercial
							.getMunicipio().getId()));
					form.setMunicipioNome(setorComercial.getMunicipio()
							.getNome());
					form.setIndicadorUso(String.valueOf(setorComercial
							.getIndicadorUso()));

					form.setIndicadorBloqueio(String.valueOf(setorComercial
							.getIndicadorBloqueio()));
					if (setorComercial.getIndicadorBloqueio().intValue() == SetorComercial.BLOQUEIO_INSERIR_IMOVEL_SIM
							.intValue()) {
						httpServletRequest.setAttribute("bloqueio", true);
					} else {
						httpServletRequest.setAttribute("bloqueio", false);
					}

					form.setIndicadorSetorAlternativo(String
							.valueOf(setorComercial
									.getIndicadorSetorAlternativo()));
					form.setIndicadorAtualizacaoCadastral(String
							.valueOf(setorComercial
									.getIndicadorAtualizacaoCadastral()));

					Collection colecaoSetor = new ArrayList();
					colecaoSetor.add(setorComercial);

					Collection colecaoFonteCaptacao = this.getFachada()
							.pesquisarFonteCaptacao(colecaoSetor);

					if (colecaoFonteCaptacao != null
							&& !colecaoFonteCaptacao.isEmpty()) {
						this.getSessao(httpServletRequest).setAttribute(
								"colecaoFonteCaptacao", colecaoFonteCaptacao);
					} else {
						this.getSessao(httpServletRequest).removeAttribute(
								"colecaoFonteCaptacao");
					}

				}
			}
		}

		if (httpServletRequest.getAttribute("voltar") == null) {
			if (sessao.getAttribute("manter") == null) {
				httpServletRequest.setAttribute("voltar", "filtrarNovo");
				sessao.setAttribute("indicadorAtualizar", "1");
			} else {
				httpServletRequest.setAttribute("voltar", "manter");
			}
		}

		httpServletRequest.setAttribute("setorComercialID", setorComercialID);

		this.setaRequest(httpServletRequest, form);

		// devolve o mapeamento de retorno
		return retorno;
	}

	private FonteCaptacao pesquisarFonteCaptacao(String fonte) {

		Collection colecaoPesquisa = null;
		FonteCaptacao objetoFonteCaptacao = null;

		FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();

		filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
				FiltroFonteCaptacao.ID, fonte));

		filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
				FiltroFonteCaptacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		// Retorna a fonte de captacao
		colecaoPesquisa = this.getFachada().pesquisar(filtroFonteCaptacao,
				FonteCaptacao.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			objetoFonteCaptacao = (FonteCaptacao) Util
					.retonarObjetoDeColecao(colecaoPesquisa);

		}

		return objetoFonteCaptacao;
	}

	private void montarColecaoFonte(HttpServletRequest httpServletRequest,
			PesquisarAtualizarSetorComercialActionForm form) {

		String fonte = form.getFonteCaptacao();

		boolean limparFonte = true;

		Collection colecaoFonte = (Collection) this.getSessao(
				httpServletRequest).getAttribute("colecaoFonteCaptacao");

		if (colecaoFonte != null && !colecaoFonte.isEmpty()) {

			Iterator itera = colecaoFonte.iterator();
			boolean jaExisteFonte = false;
			while (itera.hasNext()) {
				FonteCaptacao element = (FonteCaptacao) itera.next();

				if (fonte.equals("" + element.getId())) {
					jaExisteFonte = true;
					break;
				}
			}

			if (!jaExisteFonte) {

				FonteCaptacao objetoFonteCaptacao = this
						.pesquisarFonteCaptacao(fonte);

				if (objetoFonteCaptacao != null) {
					colecaoFonte.add(objetoFonteCaptacao);
				} else {
					form.setFonteCaptacao("");
					form.setDescricaoFonteCaptacao("Fonte de Capta��o inexistente.");
					limparFonte = false;
				}
			}
		} else {
			colecaoFonte = new ArrayList();

			FonteCaptacao objetoFonteCaptacao = this
					.pesquisarFonteCaptacao(fonte);

			if (objetoFonteCaptacao != null) {
				colecaoFonte.add(objetoFonteCaptacao);

				this.getSessao(httpServletRequest).setAttribute(
						"colecaoFonteCaptacao", colecaoFonte);
			} else {
				form.setFonteCaptacao("");
				form.setDescricaoFonteCaptacao("Fonte de Capta��o inexistente.");
				limparFonte = false;
			}

			if (limparFonte) {
				form.setFonteCaptacao("");
				form.setDescricaoFonteCaptacao("");
			}

		}

	}

	private void removerColecaoFonte(HttpServletRequest httpServletRequest,
			String fonte) {

		Collection colecaoFonte = (Collection) this.getSessao(
				httpServletRequest).getAttribute("colecaoFonteCaptacao");

		Iterator itera = colecaoFonte.iterator();

		while (itera.hasNext()) {
			FonteCaptacao element = (FonteCaptacao) itera.next();

			if (fonte.equals("" + element.getId())) {
				itera.remove();
				break;
			}
		}
	}

	/**
	 * Seta os request com os id encontrados
	 * 
	 * @author Rafael Pinto
	 * @date 16/10/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			PesquisarAtualizarSetorComercialActionForm form) {

		// Fonte de Captacao
		if (form.getFonteCaptacao() != null
				&& !form.getFonteCaptacao().equals("")
				&& form.getDescricaoFonteCaptacao() != null
				&& !form.getDescricaoFonteCaptacao().equals("")) {

			httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
		}
	}
}

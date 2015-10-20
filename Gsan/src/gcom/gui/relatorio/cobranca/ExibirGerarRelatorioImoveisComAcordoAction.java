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
package gcom.gui.relatorio.cobranca;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0891]-Gerar Relat�rio de Im�veis com Acordo
 * 
 * @author R�mulo Aur�lio
 * @date 23/03/2009
 */
public class ExibirGerarRelatorioImoveisComAcordoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioImoveisComAcordoAction");

		GerarRelatorioImoveisComAcordoActionForm form = (GerarRelatorioImoveisComAcordoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").trim().equals("")) {
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples( 
					FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO.toString()));

			Collection<UnidadeNegocio> colecaoUnidadeNegocio = this
					.getFachada().pesquisar(filtroUnidadeNegocio,
							UnidadeNegocio.class.getName());

			sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);

			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples( 
					FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO.toString()));
			Collection<GerenciaRegional> colecaoGerenciaRegional = this.getFachada()
					.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());

			sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		}

		pesquisarCamposEnter(httpServletRequest, form);
		
		
		if (httpServletRequest.getParameter("quantidade") != null && !httpServletRequest.getParameter("quantidade").equals("")) {

			Date dataInicial = null;
			Date dataFinal = null;

			Integer qtde;

			if (form.getPeriodoInicialAcordo() != null
					&& !form.getPeriodoInicialAcordo().equalsIgnoreCase("")) {

				dataInicial = Util.converteStringParaDate(form
						.getPeriodoInicialAcordo());

				dataFinal = Util.converteStringParaDate(form
						.getPeriodoFinalAcordo());

				if (dataInicial.after(dataFinal)) {
					throw new ActionServletException(
							"atencao.data_final_situacao_cobranca_invalida");
				}

				qtde = Fachada
						.getInstancia()
						.pesquisarDadosGerarRelatorioImoveisComAcordoCount(
								form.getIdUnidadeNegocio() == null
										|| form.getIdUnidadeNegocio()
												.equals("-1") ? null : Integer
										.parseInt(form.getIdUnidadeNegocio()),
								form.getIdLocalidadeInicial() == null
										|| form.getIdLocalidadeInicial()
												.equals("") ? null
										: Integer.parseInt(form
												.getIdLocalidadeInicial()),
								form.getIdLocalidadeFinal() == null
										|| form.getIdLocalidadeFinal().equals(
												"") ? null : Integer
										.parseInt(form.getIdLocalidadeFinal()),
								form.getIdGerenciaRegional() == null
										|| form.getIdGerenciaRegional().equals(
												"-1") ? null : Integer
										.parseInt(form.getIdGerenciaRegional()),
								dataInicial,
								dataFinal,
								form.getRotaInicial() == null
										|| form.getRotaInicial().equals("") ? null
										: Integer.parseInt(form
												.getRotaInicial()),
								form.getRotaFinal() == null
										|| form.getRotaFinal().equals("") ? null
										: Integer.parseInt(form.getRotaFinal()),
								form.getSequencialRotaInicial() == null
										|| form.getSequencialRotaInicial()
												.equals("") ? null : Integer
										.parseInt(form
												.getSequencialRotaInicial()),
								form.getSequencialRotaFinal() == null
										|| form.getSequencialRotaFinal()
												.equals("") ? null
										: Integer.parseInt(form
												.getSequencialRotaFinal()),
								form.getSetorComercialInicial() == null
										|| form.getSetorComercialInicial()
												.equals("") ? null : Integer
										.parseInt(form
												.getSetorComercialInicial()),
								form.getSetorComercialFinal() == null
										|| form.getSetorComercialFinal()
												.equals("") ? null
										: Integer.parseInt(form
												.getSetorComercialFinal()));
				
				if (qtde > 0){
					
					form.setQuantidade("" + qtde);
					
					httpServletRequest.setAttribute("liberado",true);
					
				}else{
					form.setQuantidade("0");
					httpServletRequest.removeAttribute("liberado");
				}
				
				httpServletRequest.setAttribute("quantidade",true);
				
				
			}
		}
		

		return retorno;
	}

	@SuppressWarnings("unchecked")
	private void pesquisarCamposEnter(HttpServletRequest httpServletRequest,
			GerarRelatorioImoveisComAcordoActionForm form) {

		String idLocalidadeOrigem = form.getIdLocalidadeInicial();

		// Pesquisa a localidade inicial
		if (idLocalidadeOrigem != null && !idLocalidadeOrigem.trim().equals("")) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeOrigem));

			Collection<Localidade> colecaoLocalidade = this.getFachada()
					.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);

				form.setIdLocalidadeInicial(localidade.getId().toString());
				form.setNomeLocalidadeInicial(localidade.getDescricao());
				httpServletRequest.setAttribute("nomeCampo",
						"codigoSetorComercialOrigem");

				String codigoSetorComercialOrigem = form
						.getSetorComercialInicial();

				// Pesquisa o setor comercial inicial
				if (codigoSetorComercialOrigem != null
						&& !codigoSetorComercialOrigem.trim().equals("")) {

					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					
					filtroSetorComercial
							.adicionarParametro(new ParametroSimples(
									 FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
					                            codigoSetorComercialOrigem)));

					Collection<SetorComercial> colecaoSetorComercial = this
							.getFachada().pesquisar(filtroSetorComercial,
									SetorComercial.class.getName());

					if (colecaoSetorComercial != null
							&& !colecaoSetorComercial.isEmpty()) {
						SetorComercial setorComercial = (SetorComercial) Util
								.retonarObjetoDeColecao(colecaoSetorComercial);

						form.setSetorComercialInicial(""
								+ setorComercial.getCodigo());
						form.setNomeSetorComercialInicial(setorComercial
								.getDescricao());
						httpServletRequest.setAttribute("nomeCampo",
								"idLocalidadeDestino");
					} else {
						form.setSetorComercialInicial("");
						form
								.setNomeSetorComercialInicial("SETOR COMERCIAL INEXISTENTE");

						httpServletRequest.setAttribute(
								"setorComercialOrigemInexistente", true);
						httpServletRequest.setAttribute("nomeCampo",
								"codigoSetorComercialOrigem");
					}

				} else {
					form.setNomeSetorComercialInicial("");
				}

			} else {
				form.setIdLocalidadeInicial("");
				form.setNomeLocalidadeInicial("LOCALIDADE INEXISTENTE");

				httpServletRequest.setAttribute("localidadeOrigemInexistente",
						true);
				httpServletRequest.setAttribute("nomeCampo",
						"idLocalidadeOrigem");
			}

		} else {
			form.setNomeLocalidadeInicial("");
		}
		String idLocalidadeDestino = form.getIdLocalidadeFinal();
		// Pesquisa a localidade final
		if (idLocalidadeDestino != null
				&& !idLocalidadeDestino.trim().equals("")) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeDestino));

			Collection<Localidade> colecaoLocalidade = this.getFachada()
					.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);

				form.setIdLocalidadeFinal(localidade.getId().toString());
				form.setNomeLocalidadeFinal(localidade.getDescricao());
				httpServletRequest.setAttribute("nomeCampo",
						"codigoSetorComercialDestino");

				String codigoSetorComercialDestino = form
						.getSetorComercialFinal();

				// Pesquisa o setor comercial Final
				if (codigoSetorComercialDestino != null
						&& !codigoSetorComercialDestino.trim().equals("")) {

					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					/*filtroSetorComercial
							.adicionarParametro(new ParametroSimples(
									FiltroSetorComercial.ID_LOCALIDADE,
									localidade.getId()));*/
					filtroSetorComercial
							.adicionarParametro(new ParametroSimples(
									FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
									Integer.parseInt(codigoSetorComercialDestino)));

					Collection<SetorComercial> colecaoSetorComercial = this
							.getFachada().pesquisar(filtroSetorComercial,
									SetorComercial.class.getName());

					if (colecaoSetorComercial != null
							&& !colecaoSetorComercial.isEmpty()) {
						SetorComercial setorComercial = (SetorComercial) Util
								.retonarObjetoDeColecao(colecaoSetorComercial);

						form.setSetorComercialFinal(""
								+ setorComercial.getCodigo());
						form.setNomeSetorComercialFinal(setorComercial
								.getDescricao());
						httpServletRequest.setAttribute("nomeCampo",
								"referenciaInicial");
					} else {
						form.setSetorComercialFinal("");
						form
								.setNomeSetorComercialFinal("SETOR COMERCIAL INEXISTENTE");

						httpServletRequest.setAttribute(
								"setorComercialDestinoInexistente", true);
						httpServletRequest.setAttribute("nomeCampo",
								"codigoSetorComercialDestino");
					}

				} else {
					form.setNomeSetorComercialFinal("");
				}

			} else {
				form.setIdLocalidadeFinal("");
				form.setNomeLocalidadeFinal("LOCALIDADE INEXISTENTE");

				httpServletRequest.setAttribute("localidadeDestinoInexistente",
						true);
				httpServletRequest.setAttribute("nomeCampo",
						"idLocalidadeDestino");
			}

		} else {
			form.setNomeLocalidadeFinal("");
		}
	}

}

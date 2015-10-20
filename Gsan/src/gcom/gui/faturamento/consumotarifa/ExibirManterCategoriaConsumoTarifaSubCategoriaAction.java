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
package gcom.gui.faturamento.consumotarifa;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifaFaixa;
import gcom.gui.GcomAction;
import gcom.gui.faturamento.consumotarifa.bean.CategoriaFaixaConsumoTarifaHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterCategoriaConsumoTarifaSubCategoriaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("manterCategoriaConsumoTarifaSubCategoria");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// ----------------FILTRO CATEGORIAS DE ESTABELECIMENTO - PROPULAR
		// DROPDOWN ------
		CategoriaFaixaConsumoTarifaHelper categoriaFaixaConsumoTarifaHelperAtualizacao = null;

		if ("sim".equals(httpServletRequest.getParameter("trava"))) {
			
			httpServletRequest.setAttribute("travar", "true");
			sessao.setAttribute("trava", "sim");
			sessao.setAttribute("novaCategoria", "nao");
			
		} else if((httpServletRequest.getParameter("trava") != null) && !("sim".equals(httpServletRequest.getParameter("trava"))) ){
			httpServletRequest.setAttribute("travar", "false");
			sessao.setAttribute("novaCategoria", "sim");
		}
		
		if (sessao.getAttribute("p1") != null){
			sessao.removeAttribute("p1");
		}
		if (sessao.getAttribute("p2") != null){
			sessao.removeAttribute("p2");
		}
		if (sessao.getAttribute("p3") != null){
			sessao.removeAttribute("p3");
		}
		
		if (httpServletRequest.getParameter("posicao") != null 
				&& !httpServletRequest.getParameter("posicao").equalsIgnoreCase("")) {
			categoriaFaixaConsumoTarifaHelperAtualizacao = pesquisarPosicaoCategoria(
					Long.parseLong(httpServletRequest.getParameter("posicao")),
					(Collection) sessao.getAttribute("colecaoCategoria"));

			sessao.setAttribute("categoriaFaixaConsumoTarifaHelperAtualizacao",
					categoriaFaixaConsumoTarifaHelperAtualizacao);
			
			sessao.setAttribute("p1", httpServletRequest.getParameter("posicao"));
			sessao.setAttribute("p2", httpServletRequest.getParameter("idCategoriaEscolhida"));
			sessao.setAttribute("p3", httpServletRequest.getParameter("idSubCategoria"));
			
		}

		if (httpServletRequest.getAttribute("parametroVigencia") != null) {
			String Vigencia = (String) httpServletRequest
					.getAttribute("parametroVigencia");
			sessao.setAttribute("Vigencia", Vigencia);
		}

		FiltroCategoria filtroCategoria = new FiltroCategoria();

		//FiltroConsumoTarifaCategoria filtroConsumoTarifaCategoria = new FiltroConsumoTarifaCategoria();

		InserirCategoriaConsumoTarifaActionForm inserirCategoriaConsumoTarifaActionForm = new InserirCategoriaConsumoTarifaActionForm();
		
		
		if ((sessao.getAttribute("valorMinimo") != null)
				&& (!sessao.getAttribute("valorMinimo").equals(""))) {
			inserirCategoriaConsumoTarifaActionForm
					.setValorTarifaMinima((String) sessao
							.getAttribute("valorMinimo"));
		}

		if ((sessao.getAttribute("consumoMinimo") != null)
				&& (!sessao.getAttribute("consumoMinimo").equals(""))) {
			inserirCategoriaConsumoTarifaActionForm
					.setConsumoMinimo((String) sessao
							.getAttribute("consumoMinimo"));
		}

		filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroCategoria.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);

		if (categoriaFaixaConsumoTarifaHelperAtualizacao != null) {
			inserirCategoriaConsumoTarifaActionForm
					.setSlcCategoria(""
							+ categoriaFaixaConsumoTarifaHelperAtualizacao
									.getConsumoTarifaCategoria().getCategoria()
									.getId());
			inserirCategoriaConsumoTarifaActionForm.setConsumoMinimo(""
					+ categoriaFaixaConsumoTarifaHelperAtualizacao
							.getConsumoTarifaCategoria()
							.getNumeroConsumoMinimo());
			inserirCategoriaConsumoTarifaActionForm
					.setValorTarifaMinima(Util
							.formatarMoedaReal(categoriaFaixaConsumoTarifaHelperAtualizacao
									.getConsumoTarifaCategoria()
									.getValorTarifaMinima()));

			if (categoriaFaixaConsumoTarifaHelperAtualizacao.getColecaoFaixas() == null
					|| categoriaFaixaConsumoTarifaHelperAtualizacao
							.getColecaoFaixas().isEmpty()) {
				FiltroConsumoTarifaFaixa filtroConsumoTarifaFaixa = new FiltroConsumoTarifaFaixa();
				filtroConsumoTarifaFaixa
						.adicionarParametro(new ParametroSimples(
								FiltroConsumoTarifaFaixa.CONSUMO_TARIFA_CATEGORIA_ID,
								categoriaFaixaConsumoTarifaHelperAtualizacao
										.getConsumoTarifaCategoria().getId()));
				Collection colecaoFaixa = fachada.pesquisar(
						filtroConsumoTarifaFaixa, ConsumoTarifaFaixa.class
								.getName());

				List listColecaoFaixa = new ArrayList();
				listColecaoFaixa.addAll(colecaoFaixa);

				Collections.sort((List) listColecaoFaixa, new Comparator() {
					public int compare(Object a, Object b) {
						Integer codigo1 = ((ConsumoTarifaFaixa) a)
								.getNumeroConsumoFaixaFim();
						Integer codigo2 = ((ConsumoTarifaFaixa) b)
								.getNumeroConsumoFaixaFim();

						return codigo1.compareTo(codigo2);
					}
				});

				sessao.setAttribute("colecaoFaixa", listColecaoFaixa);

			} else {

				Collection colecaoFaixa = categoriaFaixaConsumoTarifaHelperAtualizacao
						.getColecaoFaixas();

				List listColecaoFaixa = new ArrayList();
				listColecaoFaixa.addAll(colecaoFaixa);

				Collections.sort(listColecaoFaixa, new Comparator() {
					public int compare(Object a, Object b) {
						Integer codigo1 = ((ConsumoTarifaFaixa) a)
								.getNumeroConsumoFaixaFim();
						Integer codigo2 = ((ConsumoTarifaFaixa) b)
								.getNumeroConsumoFaixaFim();

						return codigo1.compareTo(codigo2);
					}
				});

				sessao.setAttribute("colecaoFaixa", listColecaoFaixa);

			}
		}
		sessao.setAttribute("InserirCategoriaConsumoTarifaActionForm",
				inserirCategoriaConsumoTarifaActionForm);
		Collection colecaoCategoriaLista = fachada.pesquisar(filtroCategoria,
				Categoria.class.getName());

		sessao.setAttribute("colecaoCategoriaLista", colecaoCategoriaLista);
		
		String idCategoria = "";
		
		if (httpServletRequest.getParameter("idCategoriaEscolhida") != null) {
			idCategoria = httpServletRequest.getParameter("idCategoriaEscolhida");
		} else if (httpServletRequest.getParameter("idCategoria") != null){
			idCategoria = httpServletRequest.getParameter("idCategoria");
		}
		
		if (idCategoria != null && !idCategoria.equals("")){
			FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
			
			filtroSubCategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroSubCategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.CATEGORIA_ID,
					idCategoria));
			
			filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade("categoria");
			
			filtroSubCategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
			
			Collection colecaoSubCategoriaLista = fachada.pesquisar(filtroSubCategoria,
					Subcategoria.class.getName());
			sessao.setAttribute("colecaoSubCategoriaLista", colecaoSubCategoriaLista);
		} 
		if (!idCategoria.equalsIgnoreCase("")){
			inserirCategoriaConsumoTarifaActionForm.setSlcCategoria(idCategoria);
		} 
		
		if (inserirCategoriaConsumoTarifaActionForm.getSlcSubCategoria() != null &&
				!inserirCategoriaConsumoTarifaActionForm.getSlcSubCategoria().equalsIgnoreCase("-1")){
			inserirCategoriaConsumoTarifaActionForm.setSlcSubCategoria(
					inserirCategoriaConsumoTarifaActionForm.getSlcSubCategoria());
		} else if (httpServletRequest.getParameter("idSubCategoria") != null){
			inserirCategoriaConsumoTarifaActionForm.setSlcSubCategoria(
					httpServletRequest.getParameter("idSubCategoria"));
		}
		
		if ((httpServletRequest.getParameter("limpa") != null)
				&& (httpServletRequest.getParameter("limpa").equals("1"))) {
			sessao.removeAttribute("InserirCategoriaConsumoTarifaActionForm");
			sessao.removeAttribute("valorMinimo");
			sessao.removeAttribute("consumoMinimo");
			sessao.removeAttribute("colecaoFaixa");
			sessao.setAttribute("novaCategoria", "sim");
			inserirCategoriaConsumoTarifaActionForm.setSlcCategoria("-1");
			inserirCategoriaConsumoTarifaActionForm.setSlcSubCategoria("-1");
			sessao.removeAttribute("subcategoriaSelected");
			sessao.removeAttribute("categoriaSelected");
			sessao.removeAttribute("colecaoSubCategoriaLista");
		}
		
		return retorno;
	}

	private CategoriaFaixaConsumoTarifaHelper pesquisarPosicaoCategoria(
			long posicao, Collection colecaoCategoriaSessao) {

		CategoriaFaixaConsumoTarifaHelper retorno = null;

		if (colecaoCategoriaSessao != null) {

			Iterator colecaoConsumoTarifaCategoriaIterator = colecaoCategoriaSessao
					.iterator();

			while (colecaoConsumoTarifaCategoriaIterator.hasNext()) {
				CategoriaFaixaConsumoTarifaHelper categoriaFaixaConsumoTarifaHelper = (CategoriaFaixaConsumoTarifaHelper) colecaoConsumoTarifaCategoriaIterator
						.next();
				if (obterTimestampIdObjeto(categoriaFaixaConsumoTarifaHelper
						.getConsumoTarifaCategoria()) == posicao) {
					retorno = categoriaFaixaConsumoTarifaHelper;
					break;
				}
			}
		}
		
		
		
		return retorno;
	}
}

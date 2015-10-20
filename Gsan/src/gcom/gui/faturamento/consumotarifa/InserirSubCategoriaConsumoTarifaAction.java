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
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.faturamento.consumotarifa.bean.CategoriaFaixaConsumoTarifaHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class InserirSubCategoriaConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirInserirSubCategoriaConsumoTarifa");

		Fachada fachada = Fachada.getInstancia();

		InserirSubCategoriaConsumoTarifaActionForm inserirSubCategoriaConsumoTarifaActionForm = (InserirSubCategoriaConsumoTarifaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoFaixa = (Collection) sessao
				.getAttribute("colecaoFaixa");

		if (colecaoFaixa != null) {

			if (colecaoFaixa == null || colecaoFaixa.isEmpty()) {
				throw new ActionServletException(
						"atencao.faixa_categoria_consumo_tarifa");
			}

		} else {
			throw new ActionServletException(
					"atencao.faixa_categoria_consumo_tarifa");
		}

		Iterator iteratorColecaoFaixa = colecaoFaixa.iterator();

		while (iteratorColecaoFaixa.hasNext()) {
			ConsumoTarifaFaixa consumoTarifaFaixa = (ConsumoTarifaFaixa) iteratorColecaoFaixa
					.next();
			String parametroConsumoTarifaFaixa = "valorConsumoTarifa"
					+ consumoTarifaFaixa.getUltimaAlteracao().getTime();
			consumoTarifaFaixa.setValorConsumoTarifa(Util
					.formatarMoedaRealparaBigDecimal(httpServletRequest
							.getParameter(parametroConsumoTarifaFaixa)));

		}

		// ######## Colocando os dados dos Forms na SessaoCategoria

		String idCategoria = inserirSubCategoriaConsumoTarifaActionForm
				.getSlcCategoria();

		String idSubCategoria = inserirSubCategoriaConsumoTarifaActionForm
		.getSlcSubCategoria();
		
		FiltroCategoria filtroCategoria = new FiltroCategoria();

		filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroCategoria.CODIGO, idCategoria));

		Collection colecaoCategoria = fachada.pesquisar(filtroCategoria,
				Categoria.class.getName());

		Categoria categoriaSelected = (Categoria) Util
				.retonarObjetoDeColecao(colecaoCategoria);

		ConsumoTarifaCategoria consumoTarifaCategoria = new ConsumoTarifaCategoria();

		consumoTarifaCategoria.setCategoria(categoriaSelected);
		Subcategoria subCategoria = new Subcategoria();
		subCategoria.setId(new Integer(idSubCategoria));
		consumoTarifaCategoria.setSubCategoria(subCategoria);

		Collection<CategoriaFaixaConsumoTarifaHelper> colecaoConsumoTarifaCategoria = new ArrayList();
		int numeroFaixasCategoria = 0;

		if (colecaoFaixa != null) {
			numeroFaixasCategoria = colecaoFaixa.size();
		}

		if (sessao.getAttribute("colecaoConsumoTarifaCategoria") != null) {

			colecaoConsumoTarifaCategoria = (Collection) sessao
					.getAttribute("colecaoConsumoTarifaCategoria");

			Collection colecaoCategoriaSemHelper = new ArrayList();

			if (colecaoCategoriaSemHelper.contains(consumoTarifaCategoria)) {
				throw new ActionServletException(
						"atencao.consumotaria.categoria_existente");
			}

			for (CategoriaFaixaConsumoTarifaHelper categoriaFaixaConsumoTarifaHelper : colecaoConsumoTarifaCategoria) {
				colecaoCategoriaSemHelper.add(categoriaFaixaConsumoTarifaHelper
						.getConsumoTarifaCategoria());

			}

			if ((colecaoFaixa != null) && (!colecaoFaixa.isEmpty())) {
				Iterator colecaoFaixaIt = colecaoFaixa.iterator();
				boolean i = false;
				while (colecaoFaixaIt.hasNext()) {
					ConsumoTarifaFaixa consumoTarifaFaixa = (ConsumoTarifaFaixa) colecaoFaixaIt
							.next();
					if (consumoTarifaFaixa.getNumeroConsumoFaixaFim()
							.toString().equals("999999")) {
						i = true;
					}

				}
				if (!i) {
					throw new ActionServletException(
							"atencao.faixa_limite_superior");
				}
			}

			if (colecaoCategoriaSemHelper.contains(consumoTarifaCategoria)) {
				throw new ActionServletException(
						"atencao.consumotaria.categoria_existente");

			} else {

				colecaoConsumoTarifaCategoria
						.add(new CategoriaFaixaConsumoTarifaHelper(
								numeroFaixasCategoria, consumoTarifaCategoria));
			}
		} else {

			if ((colecaoFaixa != null) && (!colecaoFaixa.isEmpty())) {
				Iterator colecaoFaixaIt = colecaoFaixa.iterator();
				boolean i = false;
				while (colecaoFaixaIt.hasNext()) {
					ConsumoTarifaFaixa consumoTarifaFaixa = (ConsumoTarifaFaixa) colecaoFaixaIt
							.next();
					if (consumoTarifaFaixa.getNumeroConsumoFaixaFim()
							.toString().equals("999999")) {
						i = true;
					}

				}
				if (!i) {
					throw new ActionServletException(
							"atencao.faixa_limite_superior");
				}
			}

			colecaoConsumoTarifaCategoria
					.add(new CategoriaFaixaConsumoTarifaHelper(
							numeroFaixasCategoria, consumoTarifaCategoria));

			sessao.setAttribute("colecaoConsumoTarifaCategoria",
					colecaoConsumoTarifaCategoria);
		}

		// Categoria
		consumoTarifaCategoria.setCategoria(categoriaSelected);

		// Consumo m�nimo
		consumoTarifaCategoria.setNumeroConsumoMinimo(new Integer(
				inserirSubCategoriaConsumoTarifaActionForm.getConsumoMinimo()));

		// Tarifa m�nima
		consumoTarifaCategoria
				.setValorTarifaMinima(Util
						.formatarMoedaRealparaBigDecimal(inserirSubCategoriaConsumoTarifaActionForm
								.getValorTarifaMinima()));

		// Ultima altera��o
		consumoTarifaCategoria.setUltimaAlteracao(new Date());

		// Atribuindo a colecao faixa valores da categoria
		colecaoFaixa = (Collection) sessao.getAttribute("colecaoFaixa");

		/*Iterator colecaoFaixaIterator =*/ colecaoFaixa.iterator();

		// while (colecaoFaixaIterator.hasNext()) {
		// ConsumoTarifaFaixa consumoTarifaFaixa = (ConsumoTarifaFaixa)
		// colecaoFaixaIterator
		// .next();
		Set colecaoFaixaSet = new HashSet();
		colecaoFaixaSet.addAll(colecaoFaixa);
		consumoTarifaCategoria.setConsumoTarifaFaixas(colecaoFaixaSet);
		// }
		// fim.
		if (httpServletRequest.getParameter("testeInserir").equalsIgnoreCase(
				"true")) {
			httpServletRequest.setAttribute("testeInserir", "true");
		}

		return retorno;
	}
}
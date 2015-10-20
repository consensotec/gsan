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

import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.faturamento.consumotarifa.bean.CategoriaFaixaConsumoTarifaHelper;
import gcom.util.Util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

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
public class ManterConsumoTarifaExistenteSubCategoriaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirConsumoTarifaActionForm inserirConsumoTarifaActionForm = (InserirConsumoTarifaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio

		String descTarifa = inserirConsumoTarifaActionForm.getDescTarifa();
		String dataVigencia = inserirConsumoTarifaActionForm.getDataVigencia();

		ConsumoTarifaVigencia consumoTarifaVigencia = (ConsumoTarifaVigencia) gcom.util.Util
				.retonarObjetoDeColecao((Collection) sessao
						.getAttribute("colecaoVigencia"));

		consumoTarifaVigencia.setId(consumoTarifaVigencia.getId());
		consumoTarifaVigencia.setDataVigencia(gcom.util.Util
				.converteStringParaDate(dataVigencia));
		consumoTarifaVigencia.getConsumoTarifa().setDescricao(descTarifa);

		Collection<CategoriaFaixaConsumoTarifaHelper> colecaoCategoriaFaixaConsumoTarifaHelper = (Collection<CategoriaFaixaConsumoTarifaHelper>) sessao
			.getAttribute("colecaoCategoria");
		
		if (colecaoCategoriaFaixaConsumoTarifaHelper == null
				|| colecaoCategoriaFaixaConsumoTarifaHelper.isEmpty()) {
			throw new ActionServletException("atencao.nenhuma_categoria_tarifa");
		}
		
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		
		Iterator iteratorColecaoCategoriaFaixaConsumoTarifaHelper = colecaoCategoriaFaixaConsumoTarifaHelper.iterator();
		
		CategoriaFaixaConsumoTarifaHelper categoriaFaixaConsumoTarifaHelper= null;
		
		String consumoMinimo= null;
		String tarifaMinima = null;
		
		while (iteratorColecaoCategoriaFaixaConsumoTarifaHelper.hasNext()) {
			categoriaFaixaConsumoTarifaHelper = (CategoriaFaixaConsumoTarifaHelper) iteratorColecaoCategoriaFaixaConsumoTarifaHelper.next();
			
			
			
			//valor minimo
			if (requestMap.get("ValorConMinimo."
					+ categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().getSubCategoria().getDescricao()) != null) {

				consumoMinimo = (requestMap.get("ValorConMinimo." + categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().getSubCategoria().getDescricao()))[0];

				
				
				if (consumoMinimo == null
						|| consumoMinimo.equalsIgnoreCase("")) {
					throw new ActionServletException(
							"atencao.required", null,
							"Consumo M�nimo");
				}

				categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().setNumeroConsumoMinimo(new Integer(consumoMinimo));
			}

			
			//valor da tarifa minima
			if (requestMap.get("ValorTarMin."
					+ categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().getSubCategoria().getDescricao()) != null) {

				tarifaMinima = (requestMap.get("ValorTarMin." + categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().getSubCategoria().getDescricao()))[0];

				if (tarifaMinima == null
						|| tarifaMinima.equalsIgnoreCase("")) {
					throw new ActionServletException(
							"atencao.required", null,
							"Tarifa M�nima");
				}

				categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().setValorTarifaMinima(Util.formatarMoedaRealparaBigDecimal(tarifaMinima));
			}
			
			// Atribuindo a colecao faixa valores da categoria
			if ((categoriaFaixaConsumoTarifaHelper.getColecaoFaixas() != null) && (!categoriaFaixaConsumoTarifaHelper.getColecaoFaixas().isEmpty())) {
				Iterator colecaoFaixaIt = categoriaFaixaConsumoTarifaHelper.getColecaoFaixas().iterator();
				while (colecaoFaixaIt.hasNext()) {
					ConsumoTarifaFaixa consumoTarifaFaixa = (ConsumoTarifaFaixa) colecaoFaixaIt
							.next();
					if(new Integer(consumoMinimo).intValue() > consumoTarifaFaixa.getNumeroConsumoFaixaFim().intValue() ){
						throw new ActionServletException(
						"atencao.consumo_minimo.maior.faixa_limite_superior_menor_existe");
					}

				}
			}
			
		}
		
		String func =  "informarTarifaConsumoPorSubCategoria";
	
		String [] ids = new String[1];
		ids[0] = consumoTarifaVigencia.getId().toString();
			
		fachada.removerTarifaConsumo(ids);
		
		fachada.atualizarConsumoTarifa(consumoTarifaVigencia,
				(Collection<CategoriaFaixaConsumoTarifaHelper>) sessao
						.getAttribute("colecaoCategoria"), func);


		montarPaginaSucesso(httpServletRequest, consumoTarifaVigencia
				.getConsumoTarifa().getDescricao()
				+ " de vig�ncia "
				+ gcom.util.Util.formatarData(consumoTarifaVigencia
						.getDataVigencia()) + " atualizada com sucesso.",
				"Atualizar outra tarifa de consumo",
				"exibirFiltrarConsumoTarifaSubCategoriaAction.do?menu=sim");

		return retorno;
	}
}
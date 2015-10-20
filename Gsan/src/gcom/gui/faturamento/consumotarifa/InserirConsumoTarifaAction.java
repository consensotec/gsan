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
* Anderson Italo Felinto de Lima
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

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.fachada.Fachada;
import gcom.faturamento.TarifaTipoCalculo;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.faturamento.consumotarifa.bean.CategoriaFaixaConsumoTarifaHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Tiago Moreno
 */
public class InserirConsumoTarifaAction extends GcomAction {

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
		String slcDescTarifa = inserirConsumoTarifaActionForm
				.getSlcDescTarifa();
		String dataVigencia = inserirConsumoTarifaActionForm.getDataVigencia();
		
		String idLigacaoAguaPerfil = inserirConsumoTarifaActionForm.getIdLigacaoAguaPerfil();
		
		String idTarifaTipoCalculo = inserirConsumoTarifaActionForm.getIdTarifaTipoCalculo();

		// Carregando o objeto consumoTarifa
		ConsumoTarifa consumoTarifa = new ConsumoTarifa();

		if ((descTarifa == null) || (descTarifa.equalsIgnoreCase(""))) {
			consumoTarifa.setId(new Integer(slcDescTarifa));
		} else {
			consumoTarifa.setDescricao(descTarifa);
			consumoTarifa
					.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
			consumoTarifa.setUltimaAlteracao(new Date());
		}
		
		if (!(idLigacaoAguaPerfil==null) && !(idLigacaoAguaPerfil.equals("")) && (new Integer(idLigacaoAguaPerfil)>0)){
			LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil();
			ligacaoAguaPerfil.setId(new Integer(idLigacaoAguaPerfil));
			consumoTarifa.setLigacaoAguaPerfil(ligacaoAguaPerfil);
		}
		
		if (!(idTarifaTipoCalculo==null) && !(idTarifaTipoCalculo.equals("")) && (new Integer(idTarifaTipoCalculo)>0)){
			TarifaTipoCalculo tarifaTipoCalculo = new TarifaTipoCalculo();
			tarifaTipoCalculo.setId(new Integer(idTarifaTipoCalculo));
			consumoTarifa.setTarifaTipoCalculo(tarifaTipoCalculo);
		}

		// Carregando o objeto consumoTarifaVigencia

		// Data de vig�ncia da tarifa
		// Para auxiliar na formata��o de uma data
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

		Date dataVencimentoTarifa;

		try {
			dataVencimentoTarifa = formatoData.parse(dataVigencia);
		} catch (ParseException ex) {
			dataVencimentoTarifa = null;
		}

		ConsumoTarifaVigencia consumoTarifaVigencia = new ConsumoTarifaVigencia();

		consumoTarifaVigencia.setDataVigencia(dataVencimentoTarifa);
		if (consumoTarifa.getId() != null) {
			consumoTarifaVigencia.setConsumoTarifa(consumoTarifa);
		}
		consumoTarifaVigencia.setUltimaAlteracao(new Date());

		// OBS - O objeto ConsumoTarifa ser� carregado no controlador

		Collection<CategoriaFaixaConsumoTarifaHelper> colecaoConsumoTarifaCategoriaHelper = new ArrayList();
		Collection<ConsumoTarifaCategoria> colecaoConsumoTarifaCategoria = new ArrayList();

		if (sessao.getAttribute("colecaoConsumoTarifaCategoria") != null
				&& !((Collection) sessao
						.getAttribute("colecaoConsumoTarifaCategoria"))
						.isEmpty()) {
			colecaoConsumoTarifaCategoriaHelper = (Collection) sessao
					.getAttribute("colecaoConsumoTarifaCategoria");
			for (CategoriaFaixaConsumoTarifaHelper categoriaFaixaConsumoTarifaHelper : colecaoConsumoTarifaCategoriaHelper) {
				String descricaoCategoria = categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().getCategoria().getDescricao();
				String consumoMinimo = httpServletRequest.getParameter("ValorConMinimo."+descricaoCategoria);
				String tarifaMinimo = httpServletRequest.getParameter("ValorTarMin."+descricaoCategoria);
				if(consumoMinimo != null && !consumoMinimo.equals("")){
					categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().
					setNumeroConsumoMinimo(new Integer(consumoMinimo));
				}else{
					throw new ActionServletException(
					"atencao.consumo_minimo_vazio");
				}
				
				if(tarifaMinimo != null && !tarifaMinimo.equals("")){
					categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().
					setValorTarifaMinima(Util
							.formatarMoedaRealparaBigDecimal(tarifaMinimo));
				}else{
					throw new ActionServletException(
					"atencao.tarifa_minimo_vazio");
				}
				
				colecaoConsumoTarifaCategoria
						.add(categoriaFaixaConsumoTarifaHelper
								.getConsumoTarifaCategoria());

			}
		} else {
			throw new ActionServletException("atencao.nenhuma_categoria_tarifa");
		}

		/*Collection<ConsumoTarifaFaixa> colecaoTarifaFaixa = new ArrayList();
		if (sessao.getAttribute("colecaoFaixa") != null) {
			colecaoTarifaFaixa = (Collection) sessao
					.getAttribute("colecaoFaixa");
		}*/

		fachada.inserirConsumoTarifa(consumoTarifa, consumoTarifaVigencia,
				colecaoConsumoTarifaCategoria);

		if (consumoTarifa.getDescricao() == null) {
			String idConsumo = inserirConsumoTarifaActionForm
					.getSlcDescTarifa();
			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();

			filtroConsumoTarifa.adicionarParametro(new ParametroSimples(
					FiltroConsumoTarifa.ID, idConsumo));
			Collection colecaoConsumoSelect = (Collection) fachada.pesquisar(
					filtroConsumoTarifa, ConsumoTarifa.class.getName());

			ConsumoTarifa consumoSelect = (ConsumoTarifa) gcom.util.Util
					.retonarObjetoDeColecao(colecaoConsumoSelect);

			montarPaginaSucesso(httpServletRequest, consumoSelect
					.getDescricao()
					+ " de vig�ncia "
					+ gcom.util.Util.formatarData(consumoTarifaVigencia
							.getDataVigencia()) + " inserida com sucesso.",
					"Inserir outra Tarifa de Consumo",
					"exibirInserirConsumoTarifaAction.do?menu=sim",
					"exibirManterConsumoTarifaExistenteAction.do?idVigencia="
							+ consumoTarifaVigencia.getId().toString(),
					"Atualizar Tarifa de Consumo Inserida");

		} else {
			montarPaginaSucesso(httpServletRequest, consumoTarifa
					.getDescricao()
					+ " de vig�ncia "
					+ gcom.util.Util.formatarData(consumoTarifaVigencia
							.getDataVigencia()) + " inserida com sucesso.",
					"Inserir outra Tarifa de Consumo",
					"exibirInserirConsumoTarifaAction.do?menu=sim",
					"exibirManterConsumoTarifaExistenteAction.do?idVigencia="
							+ consumoTarifaVigencia.getId().toString(),
					"Atualizar Tarifa de Consumo Inserida");
		}

		return retorno;
	}
}
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
package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
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
 * [UC0838]FILTRAR TIPO DE SITUACAO DE FATURAMENTO
 * 
 * @author Arthur Carvalho
 * @date 20/08/08
 */

public class FiltrarFaturamentoSituacaoTipoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterFaturamentoSituacaoTipo");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarFaturamentoSituacaoTipoActionForm filtrarFaturamentoSituacaoTipoActionForm = (FiltrarFaturamentoSituacaoTipoActionForm) actionForm;

		FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarFaturamentoSituacaoTipoActionForm.getId();
		String descricao = filtrarFaturamentoSituacaoTipoActionForm.getDescricao();
		String indicadorParalisacaoFaturamento = filtrarFaturamentoSituacaoTipoActionForm.getIndicadorParalisacaoFaturamento();
		String indicadorParalisacaoLeitura = filtrarFaturamentoSituacaoTipoActionForm.getIndicadorParalisacaoLeitura();
		String indicadorValidoAgua = filtrarFaturamentoSituacaoTipoActionForm.getIndicadorValidoAgua();
		String indicadorValidoEsgoto = filtrarFaturamentoSituacaoTipoActionForm.getIndicadorValidoEsgoto();
		String indicadorInformarConsumo = filtrarFaturamentoSituacaoTipoActionForm.getIndicadorInformarConsumo();
		String indicadorInformarVolume = filtrarFaturamentoSituacaoTipoActionForm.getIndicadorInformarVolume();
		String leituraAnormalidadeLeituraComLeitura = filtrarFaturamentoSituacaoTipoActionForm.getLeituraAnormalidadeLeituraComLeitura();
		String leituraAnormalidadeLeituraSemLeitura = filtrarFaturamentoSituacaoTipoActionForm.getLeituraAnormalidadeLeituraSemLeitura();
		String leituraAnormalidadeConsumoComLeitura = filtrarFaturamentoSituacaoTipoActionForm.getLeituraAnormalidadeConsumoComLeitura();
		String leituraAnormalidadeConsumoSemLeitura = filtrarFaturamentoSituacaoTipoActionForm.getLeituraAnormalidadeConsumoSemLeitura();
		String indicadorUso = filtrarFaturamentoSituacaoTipoActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarFaturamentoSituacaoTipoActionForm.getTipoPesquisa();
		String indicadorParalisaFatAgua = filtrarFaturamentoSituacaoTipoActionForm.getIndicadorParalisaFatAgua();
		String indicadorParalisaFatEsgoto = filtrarFaturamentoSituacaoTipoActionForm.getIndicadorParalisaFatEsgoto();

		// Indicador Atualizar
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("indicadorAtualizar");
		}

		// CODIGO
		if (id != null && !id.trim().equals("")) {
				peloMenosUmParametroInformado = true;
				
				filtroFaturamentoSituacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroFaturamentoSituacaoTipo.ID, id));
			}
	
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroFaturamentoSituacaoTipo
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroFaturamentoSituacaoTipo.DESCRICAO,
								descricao));
			} else {

				filtroFaturamentoSituacaoTipo
						.adicionarParametro(new ComparacaoTexto(
								FiltroFaturamentoSituacaoTipo.DESCRICAO,
								descricao));
			}
		}

		// Indicador Paralisacao Faturamento
		if (indicadorParalisacaoFaturamento != null
				&& !indicadorParalisacaoFaturamento.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFaturamentoSituacaoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoSituacaoTipo.INDICADOR_PARALISACAO_FATURAMENTO,
							indicadorParalisacaoFaturamento));
		}

		// Indicador Paralisacao Leitura
		if (indicadorParalisacaoLeitura != null
				&& !indicadorParalisacaoLeitura.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFaturamentoSituacaoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoSituacaoTipo.INDICADOR_PARALISACAO_LEITURA,
							indicadorParalisacaoLeitura));
		}

		// Indicador Valido Agua
		if (indicadorValidoAgua != null
				&& !indicadorValidoAgua.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFaturamentoSituacaoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoSituacaoTipo.INDICADOR_VALIDO_AGUA,
							indicadorValidoAgua));
		}

		// Indicador Valido Esgoto
		if (indicadorValidoEsgoto != null
				&& !indicadorValidoEsgoto.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFaturamentoSituacaoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoSituacaoTipo.INDICADOR_VALIDO_ESGOTO,
							indicadorValidoEsgoto));
		}

		// Indicador Informar Consumo
		if (indicadorInformarConsumo != null
				&& !indicadorInformarConsumo.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFaturamentoSituacaoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoSituacaoTipo.INDICADOR_INFORMAR_CONSUMO,
							indicadorInformarConsumo));
		}

		// Indicador Informar Volume
		if (indicadorInformarVolume != null
				&& !indicadorInformarVolume.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFaturamentoSituacaoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoSituacaoTipo.INDICADOR_INFORMAR_VOLUME,
							indicadorInformarVolume));
		}
		
		//Leitura Anormalidade Leitura Com Leitura
		if (leituraAnormalidadeLeituraComLeitura!= null && 
				!leituraAnormalidadeLeituraComLeitura.trim().equals("-1")) {
				
				peloMenosUmParametroInformado = true;
				
				filtroFaturamentoSituacaoTipo.adicionarParametro(
					new ParametroSimples(FiltroFaturamentoSituacaoTipo.LEITURA_ANORMALIDADE_COM_LEITURA, 
							leituraAnormalidadeLeituraComLeitura));
			}
			
        //Leitura Anormalidade Leitura Sem Leitura
		if (leituraAnormalidadeLeituraSemLeitura!= null && 
				!leituraAnormalidadeLeituraSemLeitura.trim().equals("-1")) {
					
				peloMenosUmParametroInformado = true;
					
			    filtroFaturamentoSituacaoTipo.adicionarParametro(
					new ParametroSimples(FiltroFaturamentoSituacaoTipo.LEITURA_ANORMALIDADE_SEM_LEITURA, 
							leituraAnormalidadeLeituraSemLeitura));
			}	

		 //Leitura Anormalidade Consumo com Leitura
		if (leituraAnormalidadeConsumoComLeitura!= null && 
				!leituraAnormalidadeConsumoComLeitura.trim().equals("-1")) {
					
				peloMenosUmParametroInformado = true;
					
				filtroFaturamentoSituacaoTipo.adicionarParametro(
					new ParametroSimples(FiltroFaturamentoSituacaoTipo.LEITURA_ANORMALIDADE_CONSUMO_COM_LEITURA, 
							leituraAnormalidadeConsumoComLeitura));
			}
		
		//Leitura Anormalidade Consumo sem Leitura
		if (leituraAnormalidadeConsumoSemLeitura!= null && 
				!leituraAnormalidadeConsumoSemLeitura.trim().equals("-1")) {
					
				peloMenosUmParametroInformado = true;
					
				filtroFaturamentoSituacaoTipo.adicionarParametro(
					new ParametroSimples(FiltroFaturamentoSituacaoTipo.LEITURA_ANORMALIDADE_CONSUMO_SEM_LEITURA, 
							leituraAnormalidadeConsumoSemLeitura));
			}
		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFaturamentoSituacaoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoSituacaoTipo.INDICADOR_USO,
							indicadorUso));
		}		
		
		//Indicador Paralisa Faturamento �gua
		if(indicadorParalisaFatAgua != null && !indicadorParalisaFatAgua.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroFaturamentoSituacaoTipo.adicionarParametro(
				new ParametroSimples(FiltroFaturamentoSituacaoTipo.INDICADOR_PARALISA_FATURAMENTO_AGUA, 
					indicadorParalisaFatAgua));			
		}
		
		//Indicador Paralisa Faturamento Esgoto
		if(indicadorParalisaFatEsgoto != null && !indicadorParalisaFatEsgoto.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroFaturamentoSituacaoTipo.adicionarParametro(
				new ParametroSimples(FiltroFaturamentoSituacaoTipo.INDICADOR_PARALISA_FATURAMENTO_ESGOTO,
					indicadorParalisaFatEsgoto));
		}
		

		Collection<FaturamentoSituacaoTipo> colecaoFaturamentoSituacaoTipo = fachada
				.pesquisar(filtroFaturamentoSituacaoTipo,
						FaturamentoSituacaoTipo.class.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoFaturamentoSituacaoTipo.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Tipo de Situa��o de Faturamento");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}

		// Pesquisa sem registros
		if (colecaoFaturamentoSituacaoTipo == null
				|| colecaoFaturamentoSituacaoTipo.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"Tipo de Situa��o de Faturamento");
		} 

		httpServletRequest.setAttribute("colecaoFaturamentoSituacaoTipo",
				colecaoFaturamentoSituacaoTipo);
		sessao.setAttribute("colecaoFaturamentoSituacaoTipo", colecaoFaturamentoSituacaoTipo);
		FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
		faturamentoSituacaoTipo = (FaturamentoSituacaoTipo) Util
				.retonarObjetoDeColecao(colecaoFaturamentoSituacaoTipo);
		String idRegistroAtualizar = faturamentoSituacaoTipo.getId()
				.toString();
		sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		

		sessao.setAttribute("filtroFaturamentoSituacaoTipo",
				filtroFaturamentoSituacaoTipo);

		httpServletRequest.setAttribute("filtroFaturamentoSituacaoTipo",
				filtroFaturamentoSituacaoTipo);

		return retorno;
	}
}

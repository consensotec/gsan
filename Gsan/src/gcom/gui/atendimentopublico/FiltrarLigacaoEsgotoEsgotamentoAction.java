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
package gcom.gui.atendimentopublico;



import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.fachada.Fachada;
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
 * [UC0838]FILTRAR LIGACAO DE ESGOTO ESGOTAMENTO
 * 
 * @author Arthur Carvalho
 * @date 25/08/08
 */

public class FiltrarLigacaoEsgotoEsgotamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterLigacaoEsgotoEsgotamento");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarLigacaoEsgotoEsgotamentoActionForm filtrarLigacaoEsgotoEsgotamentoActionForm = (FiltrarLigacaoEsgotoEsgotamentoActionForm) actionForm;

		FiltroLigacaoEsgotoEsgotamento filtroLigacaoEsgotoEsgotamento = new FiltroLigacaoEsgotoEsgotamento();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarLigacaoEsgotoEsgotamentoActionForm.getId();
		String quantidadeMesesSituacaoEspecial = filtrarLigacaoEsgotoEsgotamentoActionForm.getQuantidadeMesesSituacaoEspecial();
		String descricao = filtrarLigacaoEsgotoEsgotamentoActionForm.getDescricao();
		String indicadorUso = filtrarLigacaoEsgotoEsgotamentoActionForm.getIndicadorUso();
		String faturamentoSituacaoTipo =  filtrarLigacaoEsgotoEsgotamentoActionForm.getFaturamentoSituacaoTipo();
		String tipoPesquisa = filtrarLigacaoEsgotoEsgotamentoActionForm.getTipoPesquisa();
		String faturamentoSituacaoMotivo = filtrarLigacaoEsgotoEsgotamentoActionForm.getFaturamentoSituacaoMotivo();
		
		//Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {			
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {			
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		//CODIGO
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				
				filtroLigacaoEsgotoEsgotamento.adicionarParametro(
					new ParametroSimples(
						FiltroLigacaoEsgotoEsgotamento.ID, 
						id));
			}
		}
		
		//Motivo da situacal especial de faturamento
		if (faturamentoSituacaoMotivo != null && 
			!faturamentoSituacaoMotivo.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroLigacaoEsgotoEsgotamento.adicionarParametro(
				new ParametroSimples(
					FiltroLigacaoEsgotoEsgotamento.FATURAMENTO_SITUACAO_MOTIVO_ID, 
					faturamentoSituacaoMotivo));
		}
		
		//Tipo de situacao especial de faturamento
		if (faturamentoSituacaoTipo != null && 
			!faturamentoSituacaoTipo.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroLigacaoEsgotoEsgotamento.adicionarParametro(
				new ParametroSimples(
					FiltroLigacaoEsgotoEsgotamento.FATURAMENTO_SITUACAO_TIPO_ID, 
					faturamentoSituacaoTipo));
		}
	
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroLigacaoEsgotoEsgotamento
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroLigacaoEsgotoEsgotamento.DESCRICAO, descricao));
			} else {

				filtroLigacaoEsgotoEsgotamento.adicionarParametro(new ComparacaoTexto(
						FiltroLigacaoEsgotoEsgotamento.DESCRICAO, descricao));
			}
		}
		
		//Quantidade de meses para situacao especial de faturamento 
		if (quantidadeMesesSituacaoEspecial != null && !quantidadeMesesSituacaoEspecial.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroLigacaoEsgotoEsgotamento
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroLigacaoEsgotoEsgotamento.QUANTIDADE_MESES_SITUACAO_ESPECIAL, quantidadeMesesSituacaoEspecial));
			} else {

				filtroLigacaoEsgotoEsgotamento.adicionarParametro(new ComparacaoTexto(
						FiltroLigacaoEsgotoEsgotamento.QUANTIDADE_MESES_SITUACAO_ESPECIAL, quantidadeMesesSituacaoEspecial));
			}
		}
		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroLigacaoEsgotoEsgotamento.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoEsgotamento.INDICADOR_USO, indicadorUso));
		}
		
		filtroLigacaoEsgotoEsgotamento.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoTipo");
		filtroLigacaoEsgotoEsgotamento.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoMotivo"); 
		
		Collection <LigacaoEsgotoEsgotamento> colecaoLigacaoEsgotoEsgotamento = fachada
				.pesquisar(filtroLigacaoEsgotoEsgotamento, LigacaoEsgotoEsgotamento.class
						.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoLigacaoEsgotoEsgotamento.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Liga��o de Esgoto Esgotamento");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoLigacaoEsgotoEsgotamento == null
				|| colecaoLigacaoEsgotoEsgotamento.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Liga��o de Esgoto Esgotamento");
		} else {
			httpServletRequest.setAttribute("colecaoLigacaoEsgotoEsgotamento",
					colecaoLigacaoEsgotoEsgotamento);
			LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento= new LigacaoEsgotoEsgotamento();
			ligacaoEsgotoEsgotamento = (LigacaoEsgotoEsgotamento) Util
					.retonarObjetoDeColecao(colecaoLigacaoEsgotoEsgotamento);
			String idRegistroAtualizar = ligacaoEsgotoEsgotamento.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroLigacaoEsgotoEsgotamento", filtroLigacaoEsgotoEsgotamento);

		httpServletRequest.setAttribute("filtroLigacaoEsgotoEsgotamento",
				filtroLigacaoEsgotoEsgotamento);

		
		return retorno;
	}
}

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
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoMotivo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * 
 * @author Arthur Carvalho
 * @date 14/08/2008
 */
public class ExibirAtualizarLigacaoEsgotoEsgotamentoAction extends GcomAction {

	/**
	 * M�todo responsavel por responder a requisicao
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

		ActionForward retorno = actionMapping
				.findForward("ligacaoEsgotoEsgotamentoAtualizar");

		AtualizarLigacaoEsgotoEsgotamentoActionForm atualizarLigacaoEsgotoEsgotamentoActionForm = (AtualizarLigacaoEsgotoEsgotamentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();		

		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoPesquisa = null;
		
		String id = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null){
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
		}
		else{
			id = ((LigacaoEsgotoEsgotamento) sessao.getAttribute("ligacaoEsgotoEsgotamento")).getId().toString();
		}
		

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento = new LigacaoEsgotoEsgotamento();
		
		
		if (id != null && !id.trim().equals("")) {

			FiltroLigacaoEsgotoEsgotamento filtroLigacaoEsgotoEsgotamento = new FiltroLigacaoEsgotoEsgotamento();
			filtroLigacaoEsgotoEsgotamento.adicionarParametro(
				new ParametroSimples(FiltroLigacaoEsgotoEsgotamento.ID, id));
			
			filtroLigacaoEsgotoEsgotamento.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoTipo");
			filtroLigacaoEsgotoEsgotamento.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoMotivo");
			
			Collection colecaoLigacaoEsgotoEsgotamento = fachada.pesquisar(
					filtroLigacaoEsgotoEsgotamento, LigacaoEsgotoEsgotamento.class.getName());
			
			if (colecaoLigacaoEsgotoEsgotamento != null && !colecaoLigacaoEsgotoEsgotamento.isEmpty()) {
				ligacaoEsgotoEsgotamento= (LigacaoEsgotoEsgotamento) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoEsgotamento);
			}

		
			
			if (id != null && !id.trim().equals("")) {

				atualizarLigacaoEsgotoEsgotamentoActionForm
					.setCodigo(ligacaoEsgotoEsgotamento
						.getId().toString());

				atualizarLigacaoEsgotoEsgotamentoActionForm
					.setDescricao(ligacaoEsgotoEsgotamento
						.getDescricao());
				
				if (ligacaoEsgotoEsgotamento.getFaturamentoSituacaoTipo() != null){
					
					atualizarLigacaoEsgotoEsgotamentoActionForm
					.setFaturamentoSituacaoTipo(ligacaoEsgotoEsgotamento
							.getFaturamentoSituacaoTipo().getId().toString());
			
				} 
					
				if(ligacaoEsgotoEsgotamento.getFaturamentoSituacaoMotivo() != null){
						
					atualizarLigacaoEsgotoEsgotamentoActionForm
					.setFaturamentoSituacaoMotivo(ligacaoEsgotoEsgotamento
						.getFaturamentoSituacaoMotivo().getId().toString());
				
				} 
				
				atualizarLigacaoEsgotoEsgotamentoActionForm
					.setIndicadorUso(ligacaoEsgotoEsgotamento
						.getIndicadorUso());
				
				if (ligacaoEsgotoEsgotamento.getQuantidadeMesesSituacaoEspecial() != null){
				
					atualizarLigacaoEsgotoEsgotamentoActionForm
					.setQuantidadeMesesSituacaoEspecial(ligacaoEsgotoEsgotamento
						.getQuantidadeMesesSituacaoEspecial().toString());
				}
				
				//Tipo de Situa��o Especial de Faturamento
		        FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();
		        
		        filtroFaturamentoSituacaoTipo.setCampoOrderBy(FiltroLigacaoEsgotoEsgotamento.ID);
		        
		        filtroFaturamentoSituacaoTipo.adicionarParametro(new ParametroSimples(
		        		FiltroFaturamentoSituacaoTipo.INDICADOR_USO,
		        		ConstantesSistema.INDICADOR_USO_ATIVO));
		       
		        //Retorna tipo de situacao especial de faturamento
		        colecaoPesquisa = this.getFachada().pesquisar(filtroFaturamentoSituacaoTipo,
		        		FaturamentoSituacaoTipo.class.getName());
		        
		        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		            //Nenhum registro na tabela leitura_anormalidade_consumo foi encontrado
		            throw new ActionServletException(
		                    "atencao.pesquisa.nenhum_registro_tabela", null,
		                    "Tipo de Situa��o Especial de Faturamento");
		        } else {
		            httpServletRequest.setAttribute("colecaoFaturamentoSituacaoTipo", colecaoPesquisa);
		        }
		        
		        //Motivo de Situa��o Especial de Faturamento
		        FiltroFaturamentoSituacaoMotivo filtroFaturamentoSituacaoMotivo = new FiltroFaturamentoSituacaoMotivo();
		        
		        filtroFaturamentoSituacaoMotivo.setCampoOrderBy(FiltroLigacaoEsgotoEsgotamento.ID);
		        
		        filtroFaturamentoSituacaoMotivo.adicionarParametro(new ParametroSimples(
		        		FiltroFaturamentoSituacaoMotivo.INDICADOR_USO,
		        		ConstantesSistema.INDICADOR_USO_ATIVO));
		       
		        //Retorna Motivo de situacao especial de faturamento
		        colecaoPesquisa = this.getFachada().pesquisar(filtroFaturamentoSituacaoMotivo,
		        		FaturamentoSituacaoMotivo.class.getName());
		        
		        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		            //Nenhum registro na tabela leitura_anormalidade_consumo foi encontrado
		            throw new ActionServletException(
		                    "atencao.pesquisa.nenhum_registro_tabela", null,
		                    "Motivo da Situa��o Especial de Faturamento");
		        } else {
		            httpServletRequest.setAttribute("colecaoFaturamentoSituacaoMotivo", colecaoPesquisa);
		        }
		        
			}
			
			
			
			
			sessao.setAttribute("atualizarLigacaoEsgotoEsgotamento", ligacaoEsgotoEsgotamento);

			if (sessao.getAttribute("colecaoLigacaoEsgotoEsgotamento") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarLigacaoEsgotoEsgotamentoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarLigacaoEsgotoEsgotamentoAction.do");
			}

		}
		

		return retorno;
	}
	
	
}
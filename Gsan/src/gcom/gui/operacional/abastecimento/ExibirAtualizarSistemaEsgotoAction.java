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
package gcom.gui.operacional.abastecimento;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FiltroDivisaoEsgoto;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.operacional.SistemaEsgotoTratamentoTipo;
import gcom.operacional.abastecimento.FiltroSistemaEsgotoTratamentoTipo;
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
 * [UC0525] Manter SistemaEsgoto [SB0001]Atualizar SistemaEsgoto
 *
 * @author K�ssia Albuquerque
 * @date 16/03/2007
 */

public class ExibirAtualizarSistemaEsgotoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("atualizarSistemaEsgoto");
			
			AtualizarSistemaEsgotoActionForm atualizarSistemaEsgotoActionForm = (AtualizarSistemaEsgotoActionForm)actionForm;
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Fachada fachada = Fachada.getInstancia();
			
			
			FiltroDivisaoEsgoto filtroDivisaoEsgoto = new FiltroDivisaoEsgoto();
			
			filtroDivisaoEsgoto.adicionarParametro(new ParametroSimples
					(FiltroDivisaoEsgoto.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroDivisaoEsgoto.setCampoOrderBy(FiltroDivisaoEsgoto.DESCRICAO);
			
			Collection colecaoDivisaoEsgoto = fachada.pesquisar(filtroDivisaoEsgoto, DivisaoEsgoto.class.getName());
			
			httpServletRequest.setAttribute("colecaoDivisaoEsgoto", colecaoDivisaoEsgoto);
			
			
			
			FiltroSistemaEsgotoTratamentoTipo filtroSistemaEsgotoTratamentoTipo = new FiltroSistemaEsgotoTratamentoTipo();
			
			filtroSistemaEsgotoTratamentoTipo.adicionarParametro(new ParametroSimples
					(FiltroSistemaEsgotoTratamentoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroSistemaEsgotoTratamentoTipo.setCampoOrderBy(FiltroSistemaEsgotoTratamentoTipo.NOME);
			
			Collection colecaoSistemaEsgotoTratamentoTipo = fachada.pesquisar(filtroSistemaEsgotoTratamentoTipo, SistemaEsgotoTratamentoTipo.class.getName());
			
			httpServletRequest.setAttribute("colecaoSistemaEsgotoTratamentoTipo", colecaoSistemaEsgotoTratamentoTipo);			
						
			SistemaEsgoto sistemaEsgoto = null;
			
			String idSistemaEsgoto = null;
			
			if (httpServletRequest.getParameter("idSistemaEsgoto") != null) {
				//tela do manter
				idSistemaEsgoto = (String) httpServletRequest.getParameter("idSistemaEsgoto");
				sessao.setAttribute("idSistemaEsgoto", idSistemaEsgoto);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterSistemaEsgotoAction.do");
			} else if (sessao.getAttribute("idSistemaEsgoto") != null) {
				//tela do filtrar
				idSistemaEsgoto = (String) sessao.getAttribute("idSistemaEsgoto");
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarSistemaEsgotoAction.do");
			}else if(httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null){
				//link na tela de sucesso do inserir sistema esgoto
				idSistemaEsgoto = (String)httpServletRequest.getParameter("idRegistroInseridoAtualizar");
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarSistemaEsgotoAction.do?menu=sim");
			}
			
			if (idSistemaEsgoto == null){
				
				if (sessao.getAttribute("idRegistroAtualizar") != null){
					sistemaEsgoto = (SistemaEsgoto) sessao.getAttribute("idRegistroAtualizar");
				}else{
					idSistemaEsgoto = (String) httpServletRequest.getParameter("idSistemaEsgoto").toString();
				}
			}
			
				//------Inicio da parte que verifica se vem da p�gina de sistema_esgoto_manter.jsp
			
				
				if (sistemaEsgoto == null){
				
					if (idSistemaEsgoto != null && !idSistemaEsgoto.equals("")) {
		
						FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();
						
						filtroSistemaEsgoto.adicionarCaminhoParaCarregamentoEntidade("divisaoEsgoto");
						filtroSistemaEsgoto.adicionarCaminhoParaCarregamentoEntidade("sistemaEsgotoTratamentoTipo");
						
						filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSistemaEsgoto.ID, idSistemaEsgoto));
		
						Collection colecaoSistemaEsgoto = fachada.pesquisar(filtroSistemaEsgoto, SistemaEsgoto.class.getName());
	
						if (colecaoSistemaEsgoto != null && !colecaoSistemaEsgoto.isEmpty()) {
							
							sistemaEsgoto = (SistemaEsgoto) Util.retonarObjetoDeColecao(colecaoSistemaEsgoto);
							
						}
					}
				}
				
				//  ------  O sistema de esgoto foi encontrado
				
				atualizarSistemaEsgotoActionForm.setDescricaoSistemaEsgoto(sistemaEsgoto.getDescricao());
	
				atualizarSistemaEsgotoActionForm.setDescricaoAbreviada(sistemaEsgoto.getDescricaoAbreviada());
				
				atualizarSistemaEsgotoActionForm.setDivisaoEsgoto(sistemaEsgoto.getDivisaoEsgoto().getId().toString());
				
				atualizarSistemaEsgotoActionForm.setTipoTratamento(sistemaEsgoto.getSistemaEsgotoTratamentoTipo().getId().toString());
				
				atualizarSistemaEsgotoActionForm.setIndicadorUso(""+sistemaEsgoto.getIndicadorUso());
				
				atualizarSistemaEsgotoActionForm.setUltimaAlteracao( Util.formatarDataComHora( sistemaEsgoto.getUltimaAlteracao() ) );

				sessao.setAttribute("sistemaEsgoto", sistemaEsgoto);
				
				httpServletRequest.setAttribute("idSistemaEsgoto", idSistemaEsgoto);
				
				// ------ Fim da parte que verifica se vem da p�gina de sistema_esgoto_manter.jsp
			
			
				return retorno;
	}

}
					
		



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
package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.FiltroSetorAbastecimento;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.SetorAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0522] Manter Distrito Operacional [SB0001]Atualizar Distrito Operacional
 *
 * @author Eduardo Bianchi
 * @date 09/02/2007
 */

public class ExibirAtualizarDistritoOperacionalAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarDistritoOperacional");				
		
		AtualizarDistritoOperacionalActionForm atualizarDistritoOperacionalActionForm = (AtualizarDistritoOperacionalActionForm)actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		if (httpServletRequest.getParameter("pesquisarSetor") == null) {
		
		DistritoOperacional distritoOperacional = null;
		
		String idDistritoOperacional = null;
		
		if (httpServletRequest.getParameter("idDistritoOperacional") != null) {
			//tela do manter
			idDistritoOperacional = (String) httpServletRequest.getParameter("idDistritoOperacional");
			sessao.setAttribute("idDistritoOperacional", idDistritoOperacional);
			sessao.setAttribute("caminhoRetornoVoltar",
			"/gsan/exibirManterDistritoOperacionalAction.do");
		} else if (sessao.getAttribute("idDistritoOperacional") != null) {
			//tela do filtrar
			sessao.setAttribute("caminhoRetornoVoltar",
			"/gsan/exibirFiltrarDistritoOperacionalAction.do?voltar=filtrar");
			idDistritoOperacional = (String) sessao.getAttribute("idDistritoOperacional");
		}else if(httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null){
			//link na tela de sucesso do inserir municipio
			idDistritoOperacional = (String)httpServletRequest.getParameter("idRegistroInseridoAtualizar");
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarDistritoOperacionalAction.do?menu=sim");
		}
		
		if (idDistritoOperacional == null){
			
			if (sessao.getAttribute("idRegistroAtualizar") != null){
				distritoOperacional = (DistritoOperacional) sessao.getAttribute("idRegistroAtualizar");
				sessao.setAttribute("caminhoRetornoVoltar",
				"/gsan/exibirFiltrarDistritoOperacionalAction.do?voltar=filtrar");
			}else{
				idDistritoOperacional = (String) httpServletRequest.getParameter("idDistritoOperacional").toString();
			}
		}
		
			//------Inicio da parte que verifica se vem da p�gina de distrito_Operacional_manter.jsp
			if (distritoOperacional == null){
			
				if (idDistritoOperacional != null && !idDistritoOperacional.equals("")) {
	
					FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
					
					filtroDistritoOperacional.adicionarCaminhoParaCarregamentoEntidade("setorAbastecimento.sistemaAbastecimento");
					filtroDistritoOperacional.adicionarCaminhoParaCarregamentoEntidade("zonaAbastecimento");
					
					filtroDistritoOperacional.adicionarParametro(new ParametroSimples(FiltroDistritoOperacional.ID, idDistritoOperacional));
	
					Collection colecaoDistritoOperacional = fachada.pesquisar(filtroDistritoOperacional, DistritoOperacional.class.getName());

					if (colecaoDistritoOperacional != null && !colecaoDistritoOperacional.isEmpty()) {
						distritoOperacional = (DistritoOperacional) Util.retonarObjetoDeColecao(colecaoDistritoOperacional);
					}
				}
			}
			
			//  ------  O Distrito Operacional foi encontrado
			atualizarDistritoOperacionalActionForm.setCodigoDistritoOperacional(distritoOperacional.getId().toString());
			atualizarDistritoOperacionalActionForm.setDescricao(distritoOperacional.getDescricao());
			atualizarDistritoOperacionalActionForm.setDescricaoAbreviada(distritoOperacional.getDescricaoAbreviada());
			if ( distritoOperacional.getSetorAbastecimento() != null ){
				atualizarDistritoOperacionalActionForm.setSetorAbastecimento(distritoOperacional.getSetorAbastecimento().getId().toString());
				atualizarDistritoOperacionalActionForm.setSistemaAbastecimento(distritoOperacional.getSetorAbastecimento().getSistemaAbastecimento().getId().toString());
			} else {
				atualizarDistritoOperacionalActionForm.setSetorAbastecimento(null);
				atualizarDistritoOperacionalActionForm.setSistemaAbastecimento(null);
			}
			atualizarDistritoOperacionalActionForm.setIndicadorUso(""+distritoOperacional.getIndicadorUso());
			atualizarDistritoOperacionalActionForm.setUltimaAlteracao(Util.formatarDataComHora(distritoOperacional.getUltimaAlteracao()));
			
			sessao.setAttribute("distritoOperacional", distritoOperacional);
			
		}
			
			// ------ Fim da parte que verifica se vem da p�gina de distrito_operacional_manter.jsp
		
			// Sistema Abastecimento		
			FiltroSistemaAbastecimento  filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();
			filtroSistemaAbastecimento.adicionarParametro( new ParametroSimples
					(FiltroSistemaAbastecimento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO) );		
			
			Collection colecaoSistemaAbastecimento = fachada.pesquisar(
					filtroSistemaAbastecimento, SistemaAbastecimento.class.getName());
					
			if (colecaoSistemaAbastecimento == null || colecaoSistemaAbastecimento.isEmpty()) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Sistema Abastecimento");
			}
			
			httpServletRequest.setAttribute("colecaoSistemaAbastecimento", colecaoSistemaAbastecimento);
			
			Collection colecaoSetorAbastecimento = new ArrayList();
			
			String sistemaAbastecimento = atualizarDistritoOperacionalActionForm.getSistemaAbastecimento();
			
			if (sistemaAbastecimento != null && !sistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
				// Setor Abastecimento				
				FiltroSetorAbastecimento  filtroSetorAbastecimento = new FiltroSetorAbastecimento();
				filtroSetorAbastecimento.adicionarParametro(new ParametroSimples
						(FiltroSetorAbastecimento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroSetorAbastecimento.adicionarParametro(new ParametroSimples
						(FiltroSetorAbastecimento.SISTEMA_ABASTECIMENTO_ID, sistemaAbastecimento));
				
				colecaoSetorAbastecimento = fachada.pesquisar(
						filtroSetorAbastecimento, SetorAbastecimento.class.getName());
				
			}
			
			httpServletRequest.setAttribute("colecaoSetorAbastecimento", colecaoSetorAbastecimento);
			
			return retorno;
	}

}
					
		



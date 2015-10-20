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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.FiltroEspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.FiltroEspecificacaoImovelSituacaoCriterio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0404] - Manter Especifica��o da Situa��o do Im�vel
 * [SB0001] - Atualizar Especifica��o da situa��o
 *
 * @author Rafael Pinto
 * 
 * @date 09/11/2006
 */

public class ExibirAtualizarEspecificacaoSituacaoImovelAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarEspecificacaoSituacaoImovel");

		AtualizarEspecificacaoSituacaoImovelActionForm form = 
			(AtualizarEspecificacaoSituacaoImovelActionForm) actionForm;
		
		String tipoOperacao = httpServletRequest.getParameter("tipoOperacao");
		
		if (form.getDeleteSituacaoCriterioImovel() != null && !form.getDeleteSituacaoCriterioImovel().equals("")) {
			
			this.removeSituacaoCriterioImovel(form);
		
		}else if (tipoOperacao == null || tipoOperacao.equals("")){
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Fachada fachada = Fachada.getInstancia();
			
			EspecificacaoImovelSituacao especificacaoImovelSituacao = null;
			String idEspecificacao = form.getIdEspecificacao();
			
			if (idEspecificacao == null || idEspecificacao.equals("")){
				
				if (httpServletRequest.getAttribute("idRegistroAtualizar") == null){
					idEspecificacao = (String) sessao.getAttribute("idRegistroAtualizar");
				}else{
					idEspecificacao = (String) httpServletRequest.getAttribute("idRegistroAtualizar").toString();
				}
			}
			
			if (idEspecificacao != null && !idEspecificacao.equals("")) {

				FiltroEspecificacaoImovelSituacao filtro = new FiltroEspecificacaoImovelSituacao();
				filtro.adicionarParametro(new ParametroSimples(FiltroEspecificacaoImovelSituacao.ID, idEspecificacao));

				Collection colecao = 
					fachada.pesquisar(filtro, EspecificacaoImovelSituacao.class.getName());

				if (colecao != null && !colecao.isEmpty()) {
					especificacaoImovelSituacao = (EspecificacaoImovelSituacao) Util.retonarObjetoDeColecao(colecao);
					
				}
			}
			
			FiltroEspecificacaoImovelSituacaoCriterio filtro = 
				new FiltroEspecificacaoImovelSituacaoCriterio();
			
			filtro.adicionarParametro(
				new ParametroSimples(
					FiltroEspecificacaoImovelSituacaoCriterio.ESPECIFICAO_SITUACAO_IMOVEL_ID, idEspecificacao));
			
			filtro.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
			filtro.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");

			Collection<EspecificacaoImovSitCriterio> colecaoEspecificacaoImovelSituacaoCriterio = 
				fachada.pesquisar(filtro,EspecificacaoImovSitCriterio.class.getName());
				
			form.setIdEspecificacao(""+especificacaoImovelSituacao.getId());
			form.setDescricaoEspecificacao(especificacaoImovelSituacao.getDescricao());
			form.setEspecificacaoImovelSituacaoCriterio(colecaoEspecificacaoImovelSituacaoCriterio);
			form.setTamanhoColecao(""+colecaoEspecificacaoImovelSituacaoCriterio.size());
			
			sessao.setAttribute("especificacaoImovelSituacao",especificacaoImovelSituacao);
			
		}
		
		resetPopup(form);
		
		return retorno;
	}
	
	/**
	 * Remove Situacao Criterio Imovel da Cole��o 
	 *
	 * @author Rafael Pinto
	 * @date 03/08/2006
	 *
	 * @param AtualizarEspecificacaoSituacaoImovelActionForm
	 */
	private void removeSituacaoCriterioImovel(AtualizarEspecificacaoSituacaoImovelActionForm form) {
		
		Collection newSituacaoCriterioImovelCollection = new ArrayList();
		
		int index = 0;
		
		for (Iterator iter = form.getEspecificacaoImovelSituacaoCriterio().iterator(); iter.hasNext();) {
			
			index++;
			
			EspecificacaoImovSitCriterio element = (EspecificacaoImovSitCriterio) iter.next();
			
			if (index != new Integer(form.getDeleteSituacaoCriterioImovel()).intValue()) {
				newSituacaoCriterioImovelCollection.add(element);
			}else{
				if(element.getId() != null){
					form.getEspecificacaoImovelSituacaoCriterioRemovidas().add(element);	
				}
			}
		}
		
		form.setEspecificacaoImovelSituacaoCriterio(newSituacaoCriterioImovelCollection);
		form.setTamanhoColecao(newSituacaoCriterioImovelCollection.size()+"");
		form.setDeleteSituacaoCriterioImovel(null);
		
		resetPopup(form);
	}
	
	/**
	 * Reseta informa��es vindas do popup 
	 *
	 * @author Rafael Pinto
	 * @date 03/08/2006
	 *
	 * @param inserirEspecificacaoSituacaoImovelActionForm
	 */
	private void resetPopup(AtualizarEspecificacaoSituacaoImovelActionForm form) {

		form.setIndicadorHidrometroLigacaoAgua(null);
		form.setIndicadorHidrometroPoco(null);
		form.setSituacaoLigacaoAgua(null);
		form.setSituacaoLigacaoEsgoto(null);
		
	}
	
}
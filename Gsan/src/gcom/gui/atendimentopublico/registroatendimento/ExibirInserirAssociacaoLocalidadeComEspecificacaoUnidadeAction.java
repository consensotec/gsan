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

import gcom.atendimentopublico.registroatendimento.FiltroLocalidadeEspecificacaoUnidade;
import gcom.atendimentopublico.registroatendimento.LocalidadeEspecificacaoUnidade;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1091] Informar Associa��o de Localidade com Especifica��o e Unidade
 * 
 * @author Hugo Leonardo
 *
 * @date 26/11/2010
 */
public class ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		// Form
		ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm form = 
			(ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm) actionForm;
		
		ArrayList<LocalidadeEspecificacaoUnidade> colecaoLocalidadeEspecificacaoUnidade = new ArrayList();
		
		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equals("sim")) {
			
		}
		
		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		// Pega os codigos que o usuario digitou para a pesquisa direta da lota��o
		if(form.getIdLocalidade() != null && !form.getIdLocalidade().trim().equals("") 
				&& objetoConsulta != null && objetoConsulta.equals("1")){
			
			sessao.removeAttribute("colecaoLocalidadeEspecificacaoUnidade");
			this.pesquisarLocalidade(httpServletRequest, form, sessao);
			
			this.pesquisarLocalidadeEspecificacaoUnidade(form, sessao);
		}
		
		// Remover Itens do Contrato da Colecao
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("limpar") ) {
        	
        	sessao.removeAttribute("colecaoLocalidadeEspecificacaoUnidade");
        }
        
        // Desfazer
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("desfazer") ) {
        	
        	sessao.removeAttribute("colecaoLocalidadeEspecificacaoUnidade");
			this.pesquisarLocalidade(httpServletRequest, form, sessao);
			
			this.pesquisarLocalidadeEspecificacaoUnidade(form, sessao);
        }
		
        // Remover Itens do Contrato da Colecao
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("remover") ) {
        	
        	Integer indice = new Integer(httpServletRequest.getParameter("id")).intValue();

        	if(sessao.getAttribute("colecaoLocalidadeEspecificacaoUnidade") != null){
        		colecaoLocalidadeEspecificacaoUnidade = 
        			(ArrayList<LocalidadeEspecificacaoUnidade>) sessao.getAttribute("colecaoLocalidadeEspecificacaoUnidade");
        		
        		if (colecaoLocalidadeEspecificacaoUnidade != null && 
	        			!colecaoLocalidadeEspecificacaoUnidade.isEmpty() && 
	        			colecaoLocalidadeEspecificacaoUnidade.size() > 1) {
	        		
	        		if (colecaoLocalidadeEspecificacaoUnidade.size() >= indice) {
	            		
	        			colecaoLocalidadeEspecificacaoUnidade.remove(indice-1);
	            	}
	        		
	        		if(colecaoLocalidadeEspecificacaoUnidade.isEmpty()){
	        			sessao.removeAttribute("colecaoLocalidadeEspecificacaoUnidade");
	        		}else{
	        			
	        			// o sistema classifica a lista de LocalidadeEspecificacaoUnidade
	            		Collections.sort((List) colecaoLocalidadeEspecificacaoUnidade,
	            				new Comparator() {
	            					public int compare(Object a, Object b) {
	            						String codigo1 = ((LocalidadeEspecificacaoUnidade) a)
	            								.getComp_id().getSolicitacaoTipoEspecificacao().getDescricao();
	            						String codigo2 = ((LocalidadeEspecificacaoUnidade) b)
	            							.getComp_id().getUnidadeOrganizacional().getDescricao();
	            						if (codigo1 == null || codigo1.equals("")) {
	            							return -1;
	            						} else {
	            							return codigo1.compareTo(codigo2);
	            						}
	            					}
	            				});
	        			
	        			sessao.setAttribute("colecaoLocalidadeEspecificacaoUnidade", colecaoLocalidadeEspecificacaoUnidade);
	        		}
				}
        	}
        }
        
        form.setIdTipoEspecificacao("");
		form.setIdTipoSolicitacao("");
		form.setIdUnidadeAtendimento("");
		form.setNomeUnidadeAtendimento("");
		
		return retorno;
	}
	
	/**
	 * Pesquisar Localidade
	 *
	 * @author Hugo Leonardo
	 * @date 26/11/2010
	 */
	private void pesquisarLocalidade(HttpServletRequest httpServletRequest, 
			ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm form, HttpSession sessao) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, form.getIdLocalidade()));

		Collection<Localidade> localidadePesquisada = Fachada.getInstancia().pesquisar(
				filtroLocalidade, Localidade.class.getName());

		if (localidadePesquisada != null && !localidadePesquisada.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
			form.setIdLocalidade("" + localidade.getId());
			form.setNomeLocalidade( localidade.getDescricao());
			
			sessao.setAttribute("localidadePesquisada", localidade);

		} else {
			form.setIdLocalidade("");
			form.setNomeLocalidade("LOTA��O INEXISTENTE");
			httpServletRequest.setAttribute("localidadeInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
		}
	}
	
	/**
	 * Pesquisar Collection LocalidadeEspecificacaoUnidade
	 *
	 * @author Hugo Leonardo
	 * @date 30/11/2010
	 */
	private void pesquisarLocalidadeEspecificacaoUnidade(
			ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm form, HttpSession sessao) {

		Collection<LocalidadeEspecificacaoUnidade> colecaoLocalidadeEspecificacaoUnidade = new ArrayList();
		
		FiltroLocalidadeEspecificacaoUnidade filtroLocalidadeEspecificacaoUnidade = new FiltroLocalidadeEspecificacaoUnidade();
		filtroLocalidadeEspecificacaoUnidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidadeEspecificacaoUnidade.LOCALIDADE_ID, form.getIdLocalidade()));
		
		filtroLocalidadeEspecificacaoUnidade.adicionarCaminhoParaCarregamentoEntidade(
				FiltroLocalidadeEspecificacaoUnidade.SOLICITACAO_TIPO_ESPECIFICACAO);
		filtroLocalidadeEspecificacaoUnidade.adicionarCaminhoParaCarregamentoEntidade(
				FiltroLocalidadeEspecificacaoUnidade.UNIDADE_ORGANIZACIONAL);
		
		filtroLocalidadeEspecificacaoUnidade.setCampoOrderBy(
				FiltroLocalidadeEspecificacaoUnidade.SOLICITACAO_TIPO_ESPECIFICACAO_ID);
		filtroLocalidadeEspecificacaoUnidade.setCampoOrderBy(
				FiltroLocalidadeEspecificacaoUnidade.UNIDADE_ORGANIZACIONAL_ID);
		
		colecaoLocalidadeEspecificacaoUnidade = this.getFachada().pesquisar(filtroLocalidadeEspecificacaoUnidade, 
				LocalidadeEspecificacaoUnidade.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoLocalidadeEspecificacaoUnidade)){
			sessao.setAttribute("colecaoLocalidadeEspecificacaoUnidade", colecaoLocalidadeEspecificacaoUnidade);
		}
	}
	
}
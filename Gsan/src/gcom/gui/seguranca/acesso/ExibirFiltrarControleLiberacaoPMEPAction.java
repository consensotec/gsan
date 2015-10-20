/**
 * 
 */
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
package gcom.gui.seguranca.acesso;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.ControleLiberacaoPermissaoEspecial;
import gcom.seguranca.acesso.FiltroControleLiberacaoPermissaoEspecial;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroPermissaoEspecial;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarControleLiberacaoPMEPAction extends GcomAction {

	/**
	 * Este caso de uso efetua pesquisa de Controle de Libera��o de Permiss�o Especial
	 * 
	 * Filtrar Controle de Libera��o de Permiss�o Especial
	 * 
	 * 
	 * @author Daniel Alves
	 * @date 12/08/2010
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("controleLiberacaoPMEPFiltrar");

		FiltrarControleLiberacaoPMEPActionForm filtrarControleLiberacaoPMEPActionForm = (FiltrarControleLiberacaoPMEPActionForm) actionForm;

		FiltroControleLiberacaoPermissaoEspecial filtroControleLiberacaoPermissaoEspecial = new FiltroControleLiberacaoPermissaoEspecial();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<ControleLiberacaoPermissaoEspecial> colecaoControleLiberacaoPMEP = this.getFachada().pesquisar(filtroControleLiberacaoPermissaoEspecial,
				ControleLiberacaoPermissaoEspecial.class.getName());

		if (colecaoControleLiberacaoPMEP == null || colecaoControleLiberacaoPMEP.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Controle de Libera��o de Permiss�o Especial.");
		}

		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		// Pesquisar com enter
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")) {
			
			if(objetoConsulta.trim().equals("1")){
				// Faz a consulta de Funcionalidade
				this.pesquisarFuncionalidade(filtrarControleLiberacaoPMEPActionForm);				
			}else if(objetoConsulta.trim().equals("2")){
				// Faz a consulta de PermissaoEspecial
				this.pesquisarPermissaoEspecial(filtrarControleLiberacaoPMEPActionForm);				
			} 
			
		}
		
		
		//Seta os request�s encontrados
		this.setaRequest(httpServletRequest,filtrarControleLiberacaoPMEPActionForm);
		
		if(filtrarControleLiberacaoPMEPActionForm.getIndicadorUso()==null){
			filtrarControleLiberacaoPMEPActionForm.setIndicadorUso("1");
		}
		if(filtrarControleLiberacaoPMEPActionForm.getAtualizar()==null){
			filtrarControleLiberacaoPMEPActionForm.setAtualizar("1");
		}
		
		return retorno;
		
	}
					
	
	
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 23/11/2007
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			FiltrarControleLiberacaoPMEPActionForm form){
		
		//Funcionalidade
		if(form.getIdFuncionalidade() != null && 
			!form.getIdFuncionalidade().equals("") && 
			form.getFuncionalidade() != null && 
			!form.getFuncionalidade().equals("")){
					
			httpServletRequest.setAttribute("funcionalidadeEncontrada","true");
		}
		
		//Permissao Especial
		if(form.getIdPermissaoEspecial() != null && 
			!form.getIdPermissaoEspecial().equals("") && 
			form.getPermissaoEspecial() != null && 
			!form.getPermissaoEspecial().equals("")){
					
			httpServletRequest.setAttribute("permissaoEspecialEncontrada","true");
		}
	}
	
	/**
	 * Pesquisa Funcionalidade
	 *
	 * @author Daniel Alves
	 * @date 21/07/2010
	 */
	private void pesquisarFuncionalidade(FiltrarControleLiberacaoPMEPActionForm form) {

		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarParametro(
				new ParametroSimples(FiltroFuncionalidade.ID, 
				form.getIdFuncionalidade()));
		
		// Recupera Funcionalidade
		Collection colecaoFuncionalidade = 
			this.getFachada().pesquisar(filtroFuncionalidade, Funcionalidade.class.getName());
	
		if (colecaoFuncionalidade != null && !colecaoFuncionalidade.isEmpty()) {

			Funcionalidade funcionalidade = 
				(Funcionalidade) Util.retonarObjetoDeColecao(colecaoFuncionalidade);
			
			form.setIdFuncionalidade(funcionalidade.getId().toString());
			form.setFuncionalidade(funcionalidade.getDescricao());
			
		} else {
			form.setIdFuncionalidade(null);
			form.setFuncionalidade("Funcionalidade inexistente");
		}
	}
	
	
	/**
	 * Pesquisa Permissao Especial
	 *
	 * @author Daniel Alves
	 * @date 21/07/2010
	 */
	private void pesquisarPermissaoEspecial(FiltrarControleLiberacaoPMEPActionForm form) {

		FiltroPermissaoEspecial filtroPermissaoEspecial = new FiltroPermissaoEspecial();
		filtroPermissaoEspecial.adicionarParametro(
				new ParametroSimples(FiltroPermissaoEspecial.ID, 
				form.getIdPermissaoEspecial()));
		
		// Recupera Permissao Especial
		Collection colecaoPermissaoEspecial = 
			this.getFachada().pesquisar(filtroPermissaoEspecial, PermissaoEspecial.class.getName());
	
		if (colecaoPermissaoEspecial != null && !colecaoPermissaoEspecial.isEmpty()) {

			PermissaoEspecial permissaoEspecial = 
				(PermissaoEspecial) Util.retonarObjetoDeColecao(colecaoPermissaoEspecial);
			
			form.setIdPermissaoEspecial(permissaoEspecial.getId().toString());
			form.setPermissaoEspecial(permissaoEspecial.getDescricao());
			
		} else {
			form.setIdPermissaoEspecial(null);
			form.setPermissaoEspecial("Permiss�o Especial inexistente");
		}
	}

}

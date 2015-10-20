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



import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidadeCategoria;
import gcom.seguranca.acesso.FiltroModulo;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.seguranca.acesso.Modulo;
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
 * Action que define o pr�-processamento da p�gina de pesquisa Categoria da Funcionalidade
 * 
 * @author Fernando Fontelles
 * @created 21/08/2009
 */
public class ExibirPesquisarCategoriaFuncionalidadeAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		Fachada fachada = Fachada.getInstancia();
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirPesquisarCategoriaFuncionalidade");

		
		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarCategoriaFuncionalidadeActionForm pesquisarCategoriaFuncionalidadeActionForm = 
				(PesquisarCategoriaFuncionalidadeActionForm) actionForm;

		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("2")) {

				// Faz a consulta de Categoria
				pesquisarCategoriaFuncionalidade(httpServletRequest, retorno,pesquisarCategoriaFuncionalidadeActionForm);

		}
		
		FiltroModulo filtroModulo = new FiltroModulo();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<Modulo> colecaoModulo = fachada.pesquisar(filtroModulo,
				Modulo.class.getName());

		if (colecaoModulo == null || colecaoModulo.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Modulo");
		}
		
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaCategoriaFuncionalidade") != null) {
			
			sessao.setAttribute("caminhoRetornoTelaPesquisaCategoriaFuncionalidade",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisaCategoriaFuncionalidade"));
			
		}
		
		if (httpServletRequest.getParameter("idCampoEnviarDados") != null && 
				!httpServletRequest.getParameter("idCampoEnviarDados").equals("")) {
			
			pesquisarCategoriaFuncionalidadeActionForm.setIdCategoriaSuperior(httpServletRequest.getParameter("idCampoEnviarDados"));
			pesquisarCategoriaFuncionalidadeActionForm.setDescricaoCategoriaSuperior(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			
		}
		
		if(httpServletRequest.getParameter("limparForm") != null){
			pesquisarCategoriaFuncionalidadeActionForm.setDescricao("");
			pesquisarCategoriaFuncionalidadeActionForm.setIdCategoriaSuperior("");
			pesquisarCategoriaFuncionalidadeActionForm.setDescricaoCategoriaSuperior("");
			pesquisarCategoriaFuncionalidadeActionForm.setIdModulo("");
			pesquisarCategoriaFuncionalidadeActionForm.setIndicadorUso("");
		}
		
		this.setaRequest(httpServletRequest,pesquisarCategoriaFuncionalidadeActionForm);
		
		if(pesquisarCategoriaFuncionalidadeActionForm.getTipoPesquisa() == null){
			pesquisarCategoriaFuncionalidadeActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		}
		
		httpServletRequest.setAttribute("colecaoModulo", colecaoModulo);
		
		return retorno;
	}
	
	private void pesquisarCategoriaFuncionalidade(
			HttpServletRequest httpServletRequest, ActionForward retorno,
			PesquisarCategoriaFuncionalidadeActionForm pesquisarCategoriaFuncionalidadeActionForm) {
		
		//Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();
		
		// Filtro para obter Categorida da Funcionalidade ativo de id informado
		FiltroFuncionalidadeCategoria filtroCategoriaFuncionalidade = new FiltroFuncionalidadeCategoria();

		String idCategoria = pesquisarCategoriaFuncionalidadeActionForm.getIdCategoriaSuperior();
		
		filtroCategoriaFuncionalidade.adicionarParametro(
			new ParametroSimples(FiltroFuncionalidadeCategoria.ID , idCategoria));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoCategoria = fachada.pesquisar(filtroCategoriaFuncionalidade,FuncionalidadeCategoria.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoCategoria != null && !colecaoCategoria.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			FuncionalidadeCategoria categoriaFuncionalidade = 
				(FuncionalidadeCategoria) Util.retonarObjetoDeColecao(colecaoCategoria);

			// Exibe o c�digo e a descri��o pesquisa na p�gina
			httpServletRequest.setAttribute("idCategoriaEncontrada","true");
			
			pesquisarCategoriaFuncionalidadeActionForm.setIdCategoriaSuperior(categoriaFuncionalidade.getId().toString());
			pesquisarCategoriaFuncionalidadeActionForm.setDescricaoCategoriaSuperior(categoriaFuncionalidade.getNome());
			

		} else {

			pesquisarCategoriaFuncionalidadeActionForm.setDescricaoCategoriaSuperior("FUNCIONALIDADE CATEGORIA INEXISTENTE");
			pesquisarCategoriaFuncionalidadeActionForm.setIdCategoriaSuperior("");

		}
	}
	
	private void setaRequest(HttpServletRequest httpServletRequest,
			PesquisarCategoriaFuncionalidadeActionForm pesquisarCategoriaFuncionalidadeActionForm){

		// Categoria Superior
		if(pesquisarCategoriaFuncionalidadeActionForm.getIdCategoriaSuperior() != null && 
			!pesquisarCategoriaFuncionalidadeActionForm.getIdCategoriaSuperior().equals("") && 
			pesquisarCategoriaFuncionalidadeActionForm.getDescricaoCategoriaSuperior() != null && 
			!pesquisarCategoriaFuncionalidadeActionForm.getDescricaoCategoriaSuperior().equals("")){
					
			httpServletRequest.setAttribute("idCategoriaEncontrada","true");
		}
		
	}

	
	
}

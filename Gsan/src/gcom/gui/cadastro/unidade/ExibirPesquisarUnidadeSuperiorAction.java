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
package gcom.gui.cadastro.unidade;


import gcom.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.unidade.FiltroUnidadeTipo;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
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
 * Action que define o pr�-processamento da p�gina de pesquisa unidade organizacional
 * 
 * @author Rafael Pinto
 * @created 25/07/2006
 */
public class ExibirPesquisarUnidadeSuperiorAction extends GcomAction {
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirPesquisarUnidadeSuperior");

		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarUnidadeSuperiorActionForm pesquisarUnidadeSuperiorActionForm = 
				(PesquisarUnidadeSuperiorActionForm) actionForm;

		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisa") != null && 
			httpServletRequest.getParameter("caminhoRetornoTelaPesquisa").equals("exibirPesquisarUnidadeOrganizacionalAction") ) {
			
			sessao.setAttribute("caminhoRetornoTelaPesquisa",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisa"));
			
		}else {
		
			// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
			String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
	
	
			if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("1")) {
	
				// Faz a consulta de Localidade
				pesquisarLocalidade(httpServletRequest, retorno,pesquisarUnidadeSuperiorActionForm);
	
			}
			
			// Parte que passa as cole��es necess�rias no jsp
			Collection colecaoUnidadeTipo = (Collection) sessao.getAttribute("colecaoUnidadeTipo");
			
			if(colecaoUnidadeTipo == null){
				
				FiltroUnidadeTipo filtroUnidadeTipo = new FiltroUnidadeTipo();
				
				filtroUnidadeTipo.adicionarParametro(new ParametroSimples(
						FiltroUnidadeTipo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
	
				filtroUnidadeTipo.setCampoOrderBy(FiltroUnidadeTipo.DESCRICAO);
	
				colecaoUnidadeTipo = 
					fachada.pesquisar(filtroUnidadeTipo, UnidadeTipo.class.getName());
	
				if (colecaoUnidadeTipo != null && !colecaoUnidadeTipo.isEmpty()) {
					sessao.setAttribute("colecaoUnidadeTipo", colecaoUnidadeTipo);
				} else {
					throw new ActionServletException("atencao.naocadastrado", null,
							"Unidade Tipo");
				}
			}
			
			Collection colecaoGerenciaRegional = (Collection) sessao.getAttribute("colecaoGerenciaRegional");
			
			if(colecaoGerenciaRegional == null){
				
				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
				
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
						FiltroGerenciaRegional.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
	
				filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
				
				colecaoGerenciaRegional = 
					fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
	
				if (colecaoGerenciaRegional != null && !colecaoGerenciaRegional.isEmpty()) {
					sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
				} else {
					throw new ActionServletException("atencao.naocadastrado", null,
							"Ger�ncial Regional");
				}
			}
	
			Collection colecaoMeioSolicitacao = (Collection) sessao.getAttribute("colecaoMeioSolicitacao");
			
			if(colecaoMeioSolicitacao == null){
				
				FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao();
				
				filtroMeioSolicitacao.adicionarParametro(new ParametroSimples(
						FiltroMeioSolicitacao.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
	
				filtroMeioSolicitacao.setCampoOrderBy(FiltroMeioSolicitacao.DESCRICAO);
				
				colecaoMeioSolicitacao = 
					fachada.pesquisar(filtroMeioSolicitacao, MeioSolicitacao.class.getName());
	
				if (colecaoMeioSolicitacao != null && !colecaoMeioSolicitacao.isEmpty()) {
					sessao.setAttribute("colecaoMeioSolicitacao", colecaoMeioSolicitacao);
				} else {
					throw new ActionServletException("atencao.naocadastrado", null,
							"Meio Solicita��o");
				}
			}
	
			Collection colecaoEmpresa = 
				(Collection) sessao.getAttribute("colecaoEmpresa");
			
			if(colecaoEmpresa == null){
	
				// Filtro para obter empresa ativo de id informado
				FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
	
				filtroEmpresa.adicionarParametro(new ParametroSimples(
						FiltroEmpresa.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
	
				filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
				
				colecaoEmpresa = 
					fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
	
				if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
					sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
				} else {
					throw new ActionServletException("atencao.naocadastrado", null,
							"Empresa");
				}
			}
		}
		
		this.setaRequest(httpServletRequest,pesquisarUnidadeSuperiorActionForm);
		
		return retorno;
	}
	
	private void pesquisarLocalidade(
			HttpServletRequest httpServletRequest, ActionForward retorno,
			PesquisarUnidadeSuperiorActionForm pesquisarUnidadeSuperiorActionForm) {
		
		// Filtro para obter o localidade ativo de id informado
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		filtroLocalidade.adicionarParametro(
			new ParametroSimples(FiltroLocalidade.ID, 
				new Integer(pesquisarUnidadeSuperiorActionForm.getIdLocalidadeFilho() )));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoLocalidade = Fachada.getInstancia()
				.pesquisar(filtroLocalidade,Localidade.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

			// Exibe o c�digo e a descri��o pesquisa na p�gina
			httpServletRequest.setAttribute("idLocalidadeEncontradaFilho","true");
			
			pesquisarUnidadeSuperiorActionForm.setIdLocalidadeFilho(localidade.getId().toString());
			pesquisarUnidadeSuperiorActionForm.setDescricaoLocalidadeFilho(localidade.getDescricao());

		} else {

			pesquisarUnidadeSuperiorActionForm.setDescricaoLocalidadeFilho("Localidade inexistente");
			pesquisarUnidadeSuperiorActionForm.setIdLocalidadeFilho("");

		}
	}

	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			PesquisarUnidadeSuperiorActionForm pesquisarUnidadeSuperiorActionForm){
		
		// Localidade
		if(pesquisarUnidadeSuperiorActionForm.getIdLocalidadeFilho() != null && 
			!pesquisarUnidadeSuperiorActionForm.getIdLocalidadeFilho().equals("") && 
			pesquisarUnidadeSuperiorActionForm.getDescricaoLocalidadeFilho() != null && 
			!pesquisarUnidadeSuperiorActionForm.getDescricaoLocalidadeFilho().equals("")){
					
			httpServletRequest.setAttribute("idLocalidadeEncontradaFilho","true");
		}
		
	}
	
	
}

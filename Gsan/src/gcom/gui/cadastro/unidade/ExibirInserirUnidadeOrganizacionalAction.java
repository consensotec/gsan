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
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacionalMunicipio;
import gcom.cadastro.unidade.FiltroUnidadeTipo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacionalMunicipio;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
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
 * Action que define o pr�-processamento da p�gina de inser��o unidade organizacional
 * 
 * @author Rafael Pinto
 * @created 25/07/2006
 */
public class ExibirInserirUnidadeOrganizacionalAction extends GcomAction {
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
		ActionForward retorno = actionMapping.findForward("exibirInserirUnidadeOrganizacional");

		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		InserirUnidadeOrganizacionalActionForm inserirUnidadeOrganizacionalActionForm = 
				(InserirUnidadeOrganizacionalActionForm) actionForm;
		

		//pega as colecoes
		Collection colecaoUnidadeTipo 	= this.consultarUnidadeTipo(fachada,sessao);
		Collection colecaoEmpresa 		= this.consultarEmpresa(fachada,sessao);
		Collection colecaoGerenciaRegional = this.consultarGerenciaRegional(fachada,sessao);
		Collection colecaoUnidadeNegocio = this.consultarUnidadeNegocio(fachada, sessao);
		
		
		
		//validar exibi��o da unidade tipo caso seja informada
		String idUnidadeTipo = httpServletRequest.getParameter("unidadeTipo");
		
		if(idUnidadeTipo != null && !idUnidadeTipo.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			UnidadeTipo unidadeTipo = this.retornaUnidadeTipoPorId(colecaoUnidadeTipo,new Integer(idUnidadeTipo));
			
			String cod = unidadeTipo.getCodigoTipo(); 

			if(cod != null){
				
				inserirUnidadeOrganizacionalActionForm.setCodigoUnidadeTipo(cod);
				
				if(cod.equals(UnidadeTipo.UNIDADE_TIPO_LOCALIDADE) ||
					cod.equals(UnidadeTipo.UNIDADE_TIPO_GERENCIA_REGIONAL) || 
					cod.equals(UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA) ||
					cod.equals(UnidadeTipo.UNIDADE_TIPO_UNIDADE_NEGOCIO)){
					
					Empresa empresaPrincipal = retornaEmpresaPrincipal(colecaoEmpresa);
					
					inserirUnidadeOrganizacionalActionForm.setIdEmpresa(""+empresaPrincipal.getId());
					
					if(cod.equals(UnidadeTipo.UNIDADE_TIPO_GERENCIA_REGIONAL)){
						String idGerencia = inserirUnidadeOrganizacionalActionForm.getGerenciaRegional();
						
						if(idGerencia != null && !idGerencia.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){

							GerenciaRegional gerenciaRegional = 
								retornaGerenciaRegionalPorId(colecaoGerenciaRegional,new Integer(idGerencia));
							
							inserirUnidadeOrganizacionalActionForm.setSigla(gerenciaRegional.getNomeAbreviado());
							inserirUnidadeOrganizacionalActionForm.setDescricao(gerenciaRegional.getNome());
							
						}
					}
					
					if(cod.equals(UnidadeTipo.UNIDADE_TIPO_UNIDADE_NEGOCIO)){
						String idUnidade = inserirUnidadeOrganizacionalActionForm.getUnidadeNegocio();
						
						if(idUnidade != null && !idUnidade.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){

							UnidadeNegocio unidadeNegocio = 
								retornaUnidadeNegocioPorId(colecaoUnidadeNegocio,new Integer(idUnidade));
							
							inserirUnidadeOrganizacionalActionForm.setSigla(unidadeNegocio.getNomeAbreviado());
							inserirUnidadeOrganizacionalActionForm.setDescricao(unidadeNegocio.getNome());
							
						}
					}
					
				}
			}
			
		}


		// verifica se o usu�rio solicitou uma consulta de localidade 
		String idLocalidade = httpServletRequest.getParameter("idLocalidade");
		String descricaoLocalidade = httpServletRequest.getParameter("descricaoLocalidade");

		//Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		// Seta no form os valores da pesquisa feita pela localidade
		if (idLocalidade != null && !idLocalidade.trim().equals("") && 
				descricaoLocalidade != null && !descricaoLocalidade.trim().equals("")) {

			// Indica que localidade foi encontrado
			httpServletRequest.setAttribute("idLocalidadeEncontrada","true");

			inserirUnidadeOrganizacionalActionForm.setIdLocalidade(idLocalidade.trim());
			inserirUnidadeOrganizacionalActionForm.setDescricaoLocalidade(descricaoLocalidade.trim());
		}

		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			objetoConsulta.trim().equals("1")) {

			// Faz a consulta de Localidade
			pesquisarLocalidade(httpServletRequest, retorno,inserirUnidadeOrganizacionalActionForm);

		}

		// verifica se o usu�rio solicitou uma consulta de unidade superior
		String idUnidadeSuperior = httpServletRequest.getParameter("idUnidadeSuperior");
		String descricaoUnidadeSuperior = httpServletRequest.getParameter("descricaoUnidadeSuperior");

		// Seta no form os valores da pesquisa feita
		if (idUnidadeSuperior != null && !idUnidadeSuperior.trim().equals("") && 
			descricaoUnidadeSuperior != null && !descricaoUnidadeSuperior.trim().equals("")) {

			// Indica que o local de armazenagem foi encontrado
			httpServletRequest.setAttribute("idUnidadeEncontrada","true");

			inserirUnidadeOrganizacionalActionForm.setIdUnidadeSuperior(idUnidadeSuperior.trim());
			inserirUnidadeOrganizacionalActionForm.setDescricaoUnidadeSuperior(descricaoUnidadeSuperior.trim());
		}

		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			objetoConsulta.trim().equals("2")) {

			// Faz a consulta de Unidade
			pesquisarUnidadeOrganizacional(httpServletRequest, retorno,inserirUnidadeOrganizacionalActionForm);

		}
		
		Collection colecaoMeioSolicitacao = (Collection) sessao.getAttribute("colecaoMeioSolicitacao");
		
		//faz a consulta da colecao de meio solicata��o para exibir no jsp
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
		
		Collection colecaoUnidadeCentralizadora = 
			(Collection) sessao.getAttribute("colecaoUnidadeCentralizadora");

		//faz a consulta da colecao unidade centralizador para exibir no jsp
		if(colecaoUnidadeCentralizadora == null){
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.UNIDADE_TIPO_CODIGO,
					UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA));

			filtroUnidadeOrganizacional.setCampoOrderBy(FiltroMeioSolicitacao.DESCRICAO);
			
			colecaoUnidadeCentralizadora = 
				fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

			if (colecaoUnidadeCentralizadora != null && !colecaoUnidadeCentralizadora.isEmpty()) {
				sessao.setAttribute("colecaoUnidadeCentralizadora", colecaoUnidadeCentralizadora);
			} else {
				//throw new ActionServletException("atencao.naocadastrado", null,"Unidade Centralizadora");
				sessao.setAttribute("colecaoUnidadeCentralizadora", new ArrayList());
			}
		}
		
		//...........................................................................................
		// 06/03/2008 - Altera��o solicitada por Fab�ola Ara�jo. 
		// Yara Taciane de Souza.
		//8.0 -  Inclus�o de op��o de tratamento pra Unidade Repavimentadora.
		
		Collection colecaoUnidadeRepavimentadora = 
			(Collection) sessao.getAttribute("colecaoUnidadeRepavimentadora");

		//faz a consulta da colecao unidade repavimentadora para exibir no jsp
		if(colecaoUnidadeRepavimentadora == null){
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.UNIDADE_TIPO_CODIGO,
					UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA));

			filtroUnidadeOrganizacional.setCampoOrderBy(FiltroMeioSolicitacao.DESCRICAO);
			
			colecaoUnidadeRepavimentadora = 
				fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

			if (colecaoUnidadeRepavimentadora != null && !colecaoUnidadeRepavimentadora.isEmpty()) {
				sessao.setAttribute("colecaoUnidadeRepavimentadora", colecaoUnidadeRepavimentadora);
			} else {
				//throw new ActionServletException("atencao.naocadastrado", null,"Unidade Centralizadora");
				sessao.setAttribute("colecaoUnidadeRepavimentadora", new ArrayList());
			}
		}
		//..................................................................................................
		
		//Seta no form os valores da pesquisa feita pela localidade
		String idMunicipio = (String)httpServletRequest.getParameter("idMunicipio");
		String descricaoMunicipio = (String)httpServletRequest.getParameter("descricaoMunicipio");
		if (idMunicipio != null && !idMunicipio.trim().equals("") && 
				descricaoMunicipio != null && !descricaoMunicipio.trim().equals("")) {

			// Indica que localidade foi encontrado
			httpServletRequest.setAttribute("idMunicipioEncontrado","true");

			inserirUnidadeOrganizacionalActionForm.setIdMunicipio(idMunicipio);
			inserirUnidadeOrganizacionalActionForm.setDescricaoMunicipio(descricaoMunicipio.trim());
		}

		
		Collection colecaoMunicipioSelecionado = null;
		
		colecaoMunicipioSelecionado = controlaColecaoMunicipio(inserirUnidadeOrganizacionalActionForm, fachada, 
					sessao, httpServletRequest);
		
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("3")) {

				// Faz a consulta de Localidade
				pesquisarMunicipio(idMunicipio,inserirUnidadeOrganizacionalActionForm,
						fachada, httpServletRequest);

		}
		
		
		sessao.setAttribute("colecaoMunicipioSelecionado", colecaoMunicipioSelecionado );

		return retorno;
	}
	
	
	//Consulta a colecao de unidade tipo
	private Collection consultarUnidadeTipo(Fachada fachada,HttpSession sessao){
		
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
				throw new ActionServletException("atencao.naocadastrado", null,"Unidade Tipo");
			}
			
		}

		return colecaoUnidadeTipo;
	}

	//consulta a colecao de empresa
	private Collection consultarEmpresa(Fachada fachada,HttpSession sessao){
		
		Collection colecaoEmpresa = (Collection) sessao.getAttribute("colecaoEmpresa");
		
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
				throw new ActionServletException("atencao.naocadastrado", null,"Empresa");
			}
		}
		
		return colecaoEmpresa;
	}
	
	//consulta a colecao de gerencia regional
	private Collection consultarGerenciaRegional(Fachada fachada,HttpSession sessao){

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
		return colecaoGerenciaRegional;
	}
	
	//consulta a colecao de gerencia regional
	private Collection consultarUnidadeNegocio(Fachada fachada,HttpSession sessao){

		Collection colecaoUnidadeNegocio = (Collection) sessao.getAttribute("colecaoUnidadeNegocio");
		
		if(colecaoUnidadeNegocio == null){ 
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
			
			colecaoUnidadeNegocio = 
				fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocio != null && !colecaoUnidadeNegocio.isEmpty()) {
				sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Unidade Negocio");
			}
		}
		return colecaoUnidadeNegocio;
	}

	//retorna unidadeTipo pelo id na colecao de unidade
	private UnidadeTipo retornaUnidadeTipoPorId(Collection colecaoUnidade,Integer id){
		UnidadeTipo retorno = null;
		
		if(colecaoUnidade != null && !colecaoUnidade.isEmpty()){
			Iterator itera = colecaoUnidade.iterator();
			
			while (itera.hasNext()) {
				UnidadeTipo unidadeTipo = (UnidadeTipo) itera.next();
				
				if(unidadeTipo.getId().intValue() == id.intValue()){
					retorno = unidadeTipo;
					break;
				}
			}
		}
		return retorno;
	}

	//retorna gerenciaRegional pelo id na colecao de gerencia	
	private GerenciaRegional retornaGerenciaRegionalPorId(Collection colecao,Integer id){
		
		GerenciaRegional retorno = null;
		
		if(colecao != null && !colecao.isEmpty()){
			Iterator itera = colecao.iterator();
			
			while (itera.hasNext()) {
				GerenciaRegional gerenciaRegional = (GerenciaRegional) itera.next();
				
				if(gerenciaRegional.getId().intValue() == id.intValue()){
					retorno = gerenciaRegional;
					break;
				}
			}
		}
		return retorno;
	}
	
	//retorna gerenciaRegional pelo id na colecao de gerencia	
	private UnidadeNegocio retornaUnidadeNegocioPorId(Collection colecao,Integer id){
		
		UnidadeNegocio retorno = null;
		
		if(colecao != null && !colecao.isEmpty()){
			Iterator itera = colecao.iterator();
			
			while (itera.hasNext()) {
				UnidadeNegocio unidadeNegocio = (UnidadeNegocio) itera.next();
				
				if(unidadeNegocio.getId().intValue() == id.intValue()){
					retorno = unidadeNegocio;
					break;
				}
			}
		}
		return retorno;
	}

	//retorna a empresa principal
	private Empresa retornaEmpresaPrincipal(Collection colecaoEmpresa){
		Empresa retorno = null;
		
		if(colecaoEmpresa != null && !colecaoEmpresa.isEmpty()){
			Iterator itera = colecaoEmpresa.iterator();
			
			while (itera.hasNext()) {
				Empresa empresa = (Empresa) itera.next();
				
				if(empresa.getIndicadorEmpresaPrincipal().equals(ConstantesSistema.SIM)){
					retorno = empresa;
					break;
				}
			}
		}
		return retorno;
	}

	//pesquisa a localidade pelo id
	private void pesquisarLocalidade(
			HttpServletRequest httpServletRequest, ActionForward retorno,
			InserirUnidadeOrganizacionalActionForm inserirUnidadeOrganizacionalActionForm) {
		
		// Filtro para obter o localidade ativo de id informado
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));

		filtroLocalidade.adicionarParametro(
			new ParametroSimples(FiltroLocalidade.ID, 
				new Integer(inserirUnidadeOrganizacionalActionForm.getIdLocalidade() )));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoLocalidade = Fachada.getInstancia()
				.pesquisar(filtroLocalidade,Localidade.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

			// Exibe o c�digo e a descri��o pesquisa na p�gina
			httpServletRequest.setAttribute("idLocalidadeEncontrada","true");
			
			inserirUnidadeOrganizacionalActionForm.setIdLocalidade(localidade.getId().toString());
			inserirUnidadeOrganizacionalActionForm.setDescricaoLocalidade(localidade.getDescricao());
			
			String codigo = inserirUnidadeOrganizacionalActionForm.getCodigoUnidadeTipo();
			
			if(codigo.equals(UnidadeTipo.UNIDADE_TIPO_LOCALIDADE) ||
					codigo.equals(UnidadeTipo.UNIDADE_TIPO_GERENCIA_REGIONAL)){
				
				inserirUnidadeOrganizacionalActionForm.setDescricao(localidade.getDescricao());
			}
			httpServletRequest.setAttribute("corLocalidade", "valor");	

		} else {
			httpServletRequest.setAttribute("corLocalidade","exception");
			inserirUnidadeOrganizacionalActionForm.setDescricaoLocalidade("Localidade inexistente");
			inserirUnidadeOrganizacionalActionForm.setIdLocalidade("");

		}
	}
	
	//pesquisa a unidadeOrganizacional pelo id
	private void pesquisarUnidadeOrganizacional(
			HttpServletRequest httpServletRequest, ActionForward retorno,
			InserirUnidadeOrganizacionalActionForm inserirUnidadeOrganizacionalActionForm) {
		
		// Filtro para obter unidade organizacional ativo de id informado
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		
		String idUnidade = inserirUnidadeOrganizacionalActionForm.getIdUnidadeSuperior();
		
		filtroUnidadeOrganizacional.adicionarParametro(
			new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidade));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoUnidade = Fachada.getInstancia()
				.pesquisar(filtroUnidadeOrganizacional,UnidadeOrganizacional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			UnidadeOrganizacional unidadeOrganizacional = 
				(UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);

			// Exibe o c�digo e a descri��o pesquisa na p�gina
			httpServletRequest.setAttribute("idUnidadeEncontrada","true");
			
			inserirUnidadeOrganizacionalActionForm.setIdUnidadeSuperior(unidadeOrganizacional.getId().toString());
			inserirUnidadeOrganizacionalActionForm.setDescricaoUnidadeSuperior(unidadeOrganizacional.getDescricao());

			httpServletRequest.setAttribute("corUnidadeSuperior", "valor");	
						
		} else {
			httpServletRequest.setAttribute("corUnidadeSuperior","exception");
			inserirUnidadeOrganizacionalActionForm.setDescricaoUnidadeSuperior("Unidade Organizacional inexistente");
			inserirUnidadeOrganizacionalActionForm.setIdUnidadeSuperior("");

		}
	}
	
	
	/**
	 * Pesquisa Municipio
	 * @author Arthur Carvalho
	 * @date 07-04-2010
	 * @param idMunicipioFiltro
	 */
	private void pesquisarMunicipio(String idMunicipio,
			InserirUnidadeOrganizacionalActionForm inserirUnidadeOrganizacionalActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idMunicipio));
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class
                    .getName());
		
		Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
		
		if ( municipio != null && !municipio.equals("") ) {
			// O municipio foi encontrado
			inserirUnidadeOrganizacionalActionForm.setIdMunicipio(municipio.getId().toString());
			inserirUnidadeOrganizacionalActionForm.setDescricaoMunicipio(municipio.getNome());
			httpServletRequest.setAttribute("idMunicipio", "true");

			//httpServletRequest.setAttribute("nomeCampo","idBairroFiltro");

		} else {
			inserirUnidadeOrganizacionalActionForm.setIdMunicipio("");
			httpServletRequest.setAttribute("idMunicipio", "exception");
			inserirUnidadeOrganizacionalActionForm.setDescricaoMunicipio("Munic�pio inexistente");

			httpServletRequest.setAttribute("nomeCampo","idMunicipio");

		}
	}
	
	/** 
	 * Controla as manipula��es da cole��o de Municipio da Unidade Organizacional
	 * @author Arthur Carvalho
	 * @date 07/04/2010
	 * 
	 */
	private ArrayList controlaColecaoMunicipio(InserirUnidadeOrganizacionalActionForm 
			inserirUnidadeOrganizacionalActionForm, Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest){
		
		ArrayList colecaoMunicipioSelecionado = null;
		Municipio municipio = new Municipio();
		UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
		
		//Caso a colecao ja possua municipios
        if (sessao.getAttribute("colecaoMunicipioSelecionado") != null && 
        		!sessao.getAttribute("colecaoMunicipioSelecionado").equals("")){
        	
        	colecaoMunicipioSelecionado = (ArrayList) sessao.getAttribute("colecaoMunicipioSelecionado");
        }else{
        	colecaoMunicipioSelecionado = new ArrayList();
        }
		
        //Verifica se o usuario clicou no botao adicionar
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("adicionar") ) {        	
			
	        // [FS0014] � Verificar vincula��o do munic�pio a outra unidade repavimentadora
	        FiltroUnidadeOrganizacionalMunicipio filtro = new FiltroUnidadeOrganizacionalMunicipio();
	        filtro.adicionarParametro(new ParametroSimples(
	        			FiltroUnidadeOrganizacionalMunicipio.ID_MUNICIPIO, 
	        				inserirUnidadeOrganizacionalActionForm.getIdMunicipio() ) );
	        filtro.adicionarParametro(new ParametroNulo(
	        			FiltroUnidadeOrganizacionalMunicipio.DATA_DESVINCULACAO));
	        filtro.adicionarCaminhoParaCarregamentoEntidade(
	        			FiltroUnidadeOrganizacionalMunicipio.ID_MUNICIPIO);
	        filtro.adicionarCaminhoParaCarregamentoEntidade(
	        			FiltroUnidadeOrganizacionalMunicipio.ID_UNIDADE_REPAVIMENTADORA);
	        
	        Collection<UnidadeOrganizacionalMunicipio> colecaoUnidOrgMunicipio = fachada.pesquisar(
	        		filtro, UnidadeOrganizacionalMunicipio.class.getName());
	        
	        if ( colecaoUnidOrgMunicipio != null && !colecaoUnidOrgMunicipio.isEmpty() ) {
	    
	        	UnidadeOrganizacionalMunicipio unidadeMunicipio = (UnidadeOrganizacionalMunicipio) 
    				Util.retonarObjetoDeColecao(colecaoUnidOrgMunicipio);
    	
	        	municipio = pesquisarMunicipio(unidadeMunicipio.getIdMunicipio().getId().toString(), municipio, fachada);
	        	
	        	unidadeOrganizacional = pesquisarUnidadeOrganizacional(unidadeMunicipio.getIdUnidadeRepavimentadora(), 
	        			fachada, unidadeOrganizacional );
	        	
	        	throw new ActionServletException("atencao.monicipio_reposabilidade_da_unidade", 
	        			municipio.getNome(), unidadeOrganizacional.getDescricao() );
	        } 
	        
	        //Pesquisa o Municipio a ser adicionado 
	        municipio = pesquisarMunicipio(inserirUnidadeOrganizacionalActionForm.getIdMunicipio(), municipio, fachada);
	        
	        //[FS0013] � Verificar exist�ncia do munic�pio na lista
	        Iterator iteratorMunicipio = colecaoMunicipioSelecionado.iterator();
    		while (iteratorMunicipio.hasNext()) {
    			Municipio municipioJaCadastrado = (Municipio) iteratorMunicipio.next();
    			
    			if ( municipioJaCadastrado.getId().intValue() == municipio.getId().intValue() ) {
    				
    				throw new ActionServletException("atencao.municipio_cadastrado", null, 
    						municipioJaCadastrado.getNome());
    			} 
        	
    		}
    		
    		
    		colecaoMunicipioSelecionado.add(municipio);
			
	        inserirUnidadeOrganizacionalActionForm.setIdMunicipio("");
	        inserirUnidadeOrganizacionalActionForm.setDescricaoMunicipio("");
			
        }
        
        //Remover o Contrato Tarifa da Colecao
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("remover") ) {
        	int obj = new Integer(httpServletRequest.getParameter("id")).intValue();
        	
        	if (colecaoMunicipioSelecionado.size() >= obj) {
        		colecaoMunicipioSelecionado.remove(obj-1);
        	}
        	
        }
        
        //Limpar Form
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("limparForm") ) {
        	
        	colecaoMunicipioSelecionado.removeAll(colecaoMunicipioSelecionado);
        	
        }

		return colecaoMunicipioSelecionado;
	}
	
	private Municipio pesquisarMunicipio( String idMunicipio, Municipio municipio, Fachada fachada ){
		
		FiltroMunicipio filtroMun = new FiltroMunicipio();
		filtroMun.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idMunicipio));
		Collection<Municipio> colecaoMunicipio = fachada.pesquisar(filtroMun, Municipio.class.getName()); 
		
		if ( colecaoMunicipio != null && !colecaoMunicipio.isEmpty() ) {
		
			municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
		} else {
			//[FS0012] � Verificar exist�ncia do munic�pio
			throw new ActionServletException("atencao.municipio.inexistente");
		}
		
		return municipio;
	}
	
	
	private UnidadeOrganizacional pesquisarUnidadeOrganizacional( UnidadeOrganizacional unidadeRepavimentadora, Fachada fachada,
			UnidadeOrganizacional unidadeOrganizacional){
		
		FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
		filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, unidadeRepavimentadora.getId()));
		
		Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional = fachada.pesquisar(
				filtro, UnidadeOrganizacional.class.getName()); 
		
		unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
		
		return unidadeOrganizacional;
	}
	
	
}

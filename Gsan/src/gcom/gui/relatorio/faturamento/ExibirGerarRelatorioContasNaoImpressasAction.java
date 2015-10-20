/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.relatorio.faturamento;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [RM5773] Impedimento de impressão de faturas pelo SMARTPHONE
 * [UC1263] Relatório de Contas Não Impressas
 * 
 * @analyst Sávio Cavalcante
 * @author Thúlio Araújo
 * @date 21/12/2011
 */
public class ExibirGerarRelatorioContasNaoImpressasAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioContasNaoImpressas");

		GerarRelatorioContasNaoImpressasActionForm form = 
			(GerarRelatorioContasNaoImpressasActionForm) actionForm;

		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		// Pesquisar Localidade
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			(objetoConsulta.trim().equals("1")|| objetoConsulta.trim().equals("3"))  ) {

			// Faz a consulta de Localidade
			this.pesquisarLocalidade(form,objetoConsulta);
		}
		
		// Pesquisar Setor Comercial
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			(objetoConsulta.trim().equals("2") || objetoConsulta.trim().equals("4"))  ) {

			// Faz a consulta de Setor Comercial
			this.pesquisarSetorComercial(form,objetoConsulta);
		}
		
		this.pesquisarFaturamentoGrupo(httpServletRequest);
		this.pesquisarGerenciaRegional(httpServletRequest);
		this.pesquisarUnidadeNegocio(httpServletRequest,form);
		this.pesquisarEmpresa(httpServletRequest);
		
		//Seta os request´s encontrados
		this.setaRequest(httpServletRequest,form);
		
		// manda o parametro que veio do validar enter
		// para ,se preciso, desabilitar os campos posterior ao intervalo, que
		// não
		// são iguais.
		if (httpServletRequest.getParameter("campoDesabilita") != null
				&& !httpServletRequest.getParameter("campoDesabilita").equals(
						"")) {
			httpServletRequest.setAttribute("campoDesabilita",
					httpServletRequest.getParameter("campoDesabilita"));
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisa o grupo de faturamento
	 * 
	 * @author Thúlio Araújo
	 * @since 22/12/2011
	 * @param httpServletRequest
	 */
	@SuppressWarnings("unchecked")
	private void pesquisarEmpresa(HttpServletRequest httpServletRequest){
		if(httpServletRequest.getAttribute("colecaoEmpresa") == null){
			// Parte que passa as coleções da Empresa necessárias no jsp
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			
			filtroEmpresa.adicionarParametro(
					new ParametroSimples(FiltroEmpresa.INDICADOR_LEITURA,
							ConstantesSistema.SIM));
			
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
			Collection<Empresa> colecaoEmpresa = Fachada.getInstancia().pesquisar(filtroEmpresa,
					Empresa.class.getName());
		
			if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
				httpServletRequest.setAttribute("colecaoEmpresa", colecaoEmpresa);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Empresa");
			}
		}
	}
	
	/**
	 * Pesquisa o grupo de faturamento
	 * 
	 * @author Thúlio Araújo
	 * @since 22/12/2011
	 * @param httpServletRequest
	 */
	@SuppressWarnings("unchecked")
	private void pesquisarFaturamentoGrupo(HttpServletRequest httpServletRequest){
		//  1.2  Grupo de Faturamento, o sistema deverá popular o combo-box com o nome dos grupos de faturamento ativos
		if(httpServletRequest.getAttribute("faturamentoGrupos") == null){
	        //Carrega Coleçao de Faturamento Grupos
	        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
	        filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
	                FiltroFaturamentoGrupo.INDICADOR_USO,
	                ConstantesSistema.INDICADOR_USO_ATIVO));
	        filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
	        Collection<FaturamentoGrupo> faturamentoGrupos = (Collection<FaturamentoGrupo>) Fachada.getInstancia().pesquisar(
	                filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
	
	        if (faturamentoGrupos.isEmpty()) {
	            throw new ActionServletException("atencao.naocadastrado", null,
	                    "grupo de faturamento");
	        } else {
	        	httpServletRequest.setAttribute("faturamentoGrupos", faturamentoGrupos);
	        }
        }
	}
	
	/**
	 * Pesquisa Localidade
	 *
	 * @author Thúlio Araújo
	 * @since 22/12/2011
	 */
	@SuppressWarnings("unchecked")
	private void pesquisarLocalidade(GerarRelatorioContasNaoImpressasActionForm form,
		String objetoConsulta) {

		Object local = form.getLocalidadeInicial();
		
		if(!objetoConsulta.trim().equals("1")){
			local = form.getLocalidadeFinal();
		}
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,local));
		filtroLocalidade.adicionarParametro(
			new ParametroSimples(FiltroLocalidade.INDICADORUSO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
		
		if (!form.getUnidadeNegocio().equals("-1")){
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID, form.getUnidadeNegocio()));
		}
		
		if (!form.getGerenciaRegional().equals("-1")){
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA, form.getGerenciaRegional()));
		}
		
		// Recupera Localidade
		Collection<Localidade> colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if (!form.getUnidadeNegocio().equals("-1") && !form.getGerenciaRegional().equals("-1") && colecaoLocalidade.isEmpty()){
			throw new ActionServletException("atencao.filtro_localidade_gerencia_unidade", form.getUnidadeNegocio(), form.getGerenciaRegional());
		}
		
		if (!form.getUnidadeNegocio().equals("-1") && colecaoLocalidade.isEmpty()){
			throw new ActionServletException("atencao.filtro_localidade_unidade_negocio",form.getUnidadeNegocio());
		}
		
		if (!form.getGerenciaRegional().equals("-1") && colecaoLocalidade.isEmpty()){
			throw new ActionServletException("atencao.filtro_localidade_gerencia",form.getGerenciaRegional());
		}
		
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			Localidade localidade = 
				(Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			if(objetoConsulta.trim().equals("1")){
				form.setLocalidadeInicial(localidade.getId().toString());
				form.setNomeLocalidadeInicial(localidade.getDescricao());
			}
			
			form.setLocalidadeFinal(localidade.getId().toString());
			form.setNomeLocalidadeFinal(localidade.getDescricao());

			
		} else {
			if(objetoConsulta.trim().equals("1")){
				form.setLocalidadeInicial(null);
				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal(null);
				throw new ActionServletException("atencao.localidade_inexistente","inicial");
			}else{
				form.setLocalidadeFinal(null);
				throw new ActionServletException("atencao.localidade_inexistente","final");
			}
		}
	}
	
	/**
	 * Pesquisa Setor comercial
	 *
	 * @author Thúlio Araújo
	 * @since 22/12/2011
	 */
	@SuppressWarnings("unchecked")
	private void pesquisarSetorComercial(GerarRelatorioContasNaoImpressasActionForm form,
		String objetoConsulta) {

		Object local = form.getLocalidadeInicial();
		Object setor = form.getSetorComercialInicial();
		
		if(!objetoConsulta.trim().equals("2")){
			local = form.getLocalidadeFinal();
			setor = form.getSetorComercialFinal();
		}

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,setor));
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		if (local != null && !local.equals("")){
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE,local));
		}
		
		// Recupera Setor Comercial
		Collection<SetorComercial> colecaoSetorComercial = 
			this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
	
		if (local != null && !local.equals("") && colecaoSetorComercial.isEmpty()){
			throw new ActionServletException("atencao.setor_comercial_localidade",form.getLocalidadeInicial());
		}
		
		if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {

			SetorComercial setorComercial = 
				(SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
			
			if(objetoConsulta.trim().equals("2")){
				form.setSetorComercialInicial(""+setorComercial.getCodigo());
				form.setNomeSetorComercialInicial(setorComercial.getDescricao());
			}

			form.setSetorComercialFinal(""+setorComercial.getCodigo());
			form.setNomeSetorComercialFinal(setorComercial.getDescricao());
			
		} else {

			if(objetoConsulta.trim().equals("2")){
				form.setSetorComercialInicial(null);
				form.setSetorComercialFinal(null);
				form.setNomeSetorComercialFinal(null);
				throw new ActionServletException("atencao.setor_comercial_inexistente","inicial");
			}else{
				form.setSetorComercialFinal(null);
				throw new ActionServletException("atencao.setor_comercial_inexistente","final");
			}
			
		}
	}
	
	/**
	 * Pesquisa Gerencial Regional 
	 *
	 * @author Thúlio Araújo
	 * @since 22/12/2011
	 */
	@SuppressWarnings("unchecked")
	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest){
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(
				new ParametroSimples(FiltroQuadra.INDICADORUSO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<GerenciaRegional> colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());


		if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Gerência Regional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		}
	}
	
	/**
	 * Pesquisa Unidade Negocio
	 *
	 * @author Thúlio Araújo
	 * @since 22/12/2011
	 */
	@SuppressWarnings("unchecked")
	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest,
			GerarRelatorioContasNaoImpressasActionForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		
		if(form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, 
					form.getGerenciaRegional()));		
		}

		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());


		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Unidade de Negócio");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio",colecaoUnidadeNegocio);
		}
	}
	
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Thúlio Araújo
	 * @since 22/12/2011
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			GerarRelatorioContasNaoImpressasActionForm form){
		
		//Localidade Inicial
		if(form.getLocalidadeInicial() != null && 
			!form.getLocalidadeInicial().equals("") && 
			form.getNomeLocalidadeInicial() != null && 
			!form.getNomeLocalidadeInicial().equals("")){
					
			httpServletRequest.setAttribute("localidadeInicialEncontrada","true");
			httpServletRequest.setAttribute("localidadeFinalEncontrada","true");
		}else{

			if(form.getLocalidadeFinal() != null && 
				!form.getLocalidadeFinal().equals("") && 
				form.getNomeLocalidadeFinal() != null && 
				!form.getNomeLocalidadeFinal().equals("")){
								
				httpServletRequest.setAttribute("localidadeFinalEncontrada","true");
			}
		}
		
		//Setor Comercial Inicial
		if(form.getSetorComercialInicial() != null && 
			!form.getSetorComercialInicial().equals("") && 
			form.getNomeSetorComercialInicial() != null && 
			!form.getNomeSetorComercialInicial().equals("")){
					
			httpServletRequest.setAttribute("setorComercialInicialEncontrado","true");
			httpServletRequest.setAttribute("setorComercialFinalEncontrado","true");
		}else{

			if(form.getSetorComercialFinal() != null && 
				!form.getSetorComercialFinal().equals("") && 
				form.getNomeSetorComercialFinal() != null && 
				!form.getNomeSetorComercialFinal().equals("")){
								
				httpServletRequest.setAttribute("setorComercialFinalEncontrado","true");
			}
		}		
	}
}
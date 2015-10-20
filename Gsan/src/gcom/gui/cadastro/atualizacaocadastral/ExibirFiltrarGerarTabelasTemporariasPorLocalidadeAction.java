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
* Ivan Sérgio Virginio da Silva Júnior
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
package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
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

public class ExibirFiltrarGerarTabelasTemporariasPorLocalidadeAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirFiltrarGerarTabelasTemporariasPorLocalidade");

		GerarTabelasTemporariasPorLocalidadeActionForm form = 
			(GerarTabelasTemporariasPorLocalidadeActionForm) actionForm;
		
		if (httpServletRequest.getParameter("limparForm") != null && 
			httpServletRequest.getParameter("limparForm").equalsIgnoreCase("S")){

			// Limpando o formulario
			form.setEmpresa("");
			form.setIdLocalidadeInicial("");
			form.setNomeLocalidadeInicial("");
			form.setIdLocalidadeFinal("");
			form.setNomeLocalidadeFinal("");
			form.setCodigoSetorComercialInicial("");
			form.setNomeSetorComercialInicial("");
			form.setCodigoSetorComercialFinal("");	
			form.setNomeSetorComercialFinal("");
			form.setNumeroQuadraInicial("");
			form.setNumeroQuadraFinal("");
			form.setRotaInicial("");
			form.setRotaFinal("");
			
			
		}

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
		
		// Pesquisar Setor Comercial
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			objetoConsulta.trim().equals("5")   ) {

			// Faz a consulta do Imovel
			this.pesquisarImovel(form);
		}
		
		//Pesquisar Quadra Inicial
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("6")   ) {

				// Faz a consulta do Imovel
				this.pesquisarQuadraInicial(form,httpServletRequest);
		}
		
		//Pesquisar Quadra Inicial
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") &&  objetoConsulta.trim().equals("7")   ) {

				// Faz a consulta do Imovel
				this.pesquisarQuadraFinal(form,httpServletRequest);
		}
		
		
		this.pesquisarEmpresa(httpServletRequest);
		
		
		//Seta os request´s encontrados
		this.setaRequest(httpServletRequest,form);
		
		return retorno;
	}
	
	/**
	 * Pesquisa Imovel
	 * 
	 * @author Erivan Sousa
	 * @date 24/05/2011
	 */
	private void pesquisarImovel(GerarTabelasTemporariasPorLocalidadeActionForm form) {

		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getMatriculaImovel()));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");

		// Recupera Localidade
		Collection colecaoImovel = this.getFachada().pesquisar(filtroImovel,
				Imovel.class.getName());

		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);

			form.setMatriculaImovel(imovel.getId().toString());
			form.setNomeImovel(imovel.getInscricaoFormatada());

		} else {
			form.setMatriculaImovel("");
			form.setNomeImovel("Imóvel inexistente");
		}
	}
	
	
	/**
	 * Pesquisa Empresa 
	 * @author Nathalia Santos
	 * @date 16/12/2011
	 */
	private void pesquisarEmpresa(HttpServletRequest httpServletRequest){
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setConsultaSemLimites(true);
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		filtroEmpresa.adicionarParametro(
				new ParametroSimples(FiltroQuadra.INDICADORUSO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoEmpresa = 
			this.getFachada().pesquisar(filtroEmpresa,Empresa.class.getName());


		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Empresa");
		} else {
			httpServletRequest.setAttribute("colecaoEmpresa",colecaoEmpresa);
		}
	}
	
	/**
	 * Pesquisa Localidade
	 * @author Nathalia Santos
	 * @date 16/12/2011
	 */
	private void pesquisarLocalidade(GerarTabelasTemporariasPorLocalidadeActionForm form,
		String objetoConsulta) {

		Object local = form.getIdLocalidadeInicial();
		
		if(!objetoConsulta.trim().equals("1")){
			local = form.getIdLocalidadeFinal();
		}
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(
			new ParametroSimples(FiltroLocalidade.ID,local));
		
		// Recupera Localidade
		Collection colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			Localidade localidade = 
				(Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			if(objetoConsulta.trim().equals("1")){
				form.setIdLocalidadeInicial(localidade.getId().toString());
				form.setNomeLocalidadeInicial(localidade.getDescricao());
			}
			
			form.setIdLocalidadeFinal(localidade.getId().toString());
			form.setNomeLocalidadeFinal(localidade.getDescricao());
			
		} else {
			if(objetoConsulta.trim().equals("1")){
				form.setIdLocalidadeInicial(null);
				form.setNomeLocalidadeInicial("Localidade Inicial inexistente");
				
				form.setIdLocalidadeFinal(null);
				form.setNomeLocalidadeFinal(null);
			}else{
				form.setIdLocalidadeFinal(null);
				form.setNomeLocalidadeFinal("Localidade Final inexistente");
			}
		}
	}
	
	/**
	 * Pesquisa Setor comercial
	 * @author Nathalia Santos
	 * @date 16/12/2011
	 */
	private void pesquisarSetorComercial(GerarTabelasTemporariasPorLocalidadeActionForm form,
		String objetoConsulta) {

		Object local = form.getIdLocalidadeInicial();
		Object setor = form.getCodigoSetorComercialInicial();
		
		if(!objetoConsulta.trim().equals("2")){
			local = form.getIdLocalidadeFinal();
			setor = form.getCodigoSetorComercialFinal();
		}

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,setor));
		
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.LOCALIDADE,local));
		
		// Recupera Setor Comercial
		Collection colecaoSetorComercial = 
			this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
	
		if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {

			SetorComercial setorComercial = 
				(SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
			
			if(objetoConsulta.trim().equals("2")){
				form.setCodigoSetorComercialInicial(""+setorComercial.getCodigo());
				form.setNomeSetorComercialInicial(setorComercial.getDescricao());
			}

			form.setCodigoSetorComercialFinal(""+setorComercial.getCodigo());
			form.setNomeSetorComercialFinal(setorComercial.getDescricao());
			
		} else {

			if(objetoConsulta.trim().equals("2")){
				form.setCodigoSetorComercialInicial(null);
				form.setNomeSetorComercialInicial("Setor Comercial Inicial inexistente");
				
				form.setCodigoSetorComercialFinal(null);
				form.setNomeSetorComercialFinal(null);
			}else{
				form.setCodigoSetorComercialFinal(null);
				form.setNomeSetorComercialFinal("Setor Comercial Final inexistente");
			}
			
		}
	}
	
	
	

	
	/**
	 * Seta os request com os id encontrados 
	 * @author Nathalia Santos
	 * @date 16/12/2011
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			GerarTabelasTemporariasPorLocalidadeActionForm form){
		
		//Localidade Inicial
		if(form.getIdLocalidadeInicial() != null && 
			!form.getIdLocalidadeInicial().equals("") && 
			form.getNomeLocalidadeInicial() != null && 
			!form.getNomeLocalidadeInicial().equals("")){
					
			httpServletRequest.setAttribute("localidadeInicialEncontrada","true");
			httpServletRequest.setAttribute("localidadeFinalEncontrada","true");
		}else{

			if(form.getIdLocalidadeFinal() != null && 
				!form.getIdLocalidadeFinal().equals("") && 
				form.getNomeLocalidadeFinal() != null && 
				!form.getNomeLocalidadeFinal().equals("")){
								
				httpServletRequest.setAttribute("localidadeFinalEncontrada","true");
			}
		}
		
		//Setor Comercial Inicial
		if(form.getCodigoSetorComercialInicial() != null && 
			!form.getCodigoSetorComercialInicial().equals("") && 
			form.getNomeSetorComercialInicial() != null && 
			!form.getNomeSetorComercialInicial().equals("")){
					
			httpServletRequest.setAttribute("setorComercialInicialEncontrado","true");
			httpServletRequest.setAttribute("setorComercialFinalEncontrado","true");
		}else{

			if(form.getCodigoSetorComercialFinal() != null && 
				!form.getCodigoSetorComercialFinal().equals("") && 
				form.getNomeSetorComercialFinal() != null && 
				!form.getNomeSetorComercialFinal().equals("")){
								
				httpServletRequest.setAttribute("setorComercialFinalEncontrado","true");
			}
		}		
	}
	
	/**
	 * @date 28/12/2011
	 * @author Arthur Carvalho
	 */
	private void pesquisarQuadraInicial(GerarTabelasTemporariasPorLocalidadeActionForm form,  HttpServletRequest httpServletRequest) {
		
		
		String local = form.getIdLocalidadeInicial();
		String setor = form.getCodigoSetorComercialInicial();
		String quadra = form.getNumeroQuadraInicial();
		
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(local)));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(setor)));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, new Integer(quadra)));
        filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");
			
		Collection<Quadra> quadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
		
		if (quadras != null && !quadras.isEmpty()) {

			Quadra quadraPesquisada = (Quadra) Util.retonarObjetoDeColecao(quadras);
				form.setNumeroQuadraInicial(quadraPesquisada.getNumeroQuadra() + "");
				form.setNumeroQuadraFinal(quadraPesquisada.getNumeroQuadra() + "");
				form.setRotaInicial(quadraPesquisada.getRota().getCodigo().toString());
				form.setRotaFinal(quadraPesquisada.getRota().getCodigo().toString());
				httpServletRequest.setAttribute("idQuadraNaoEncontrada", "true");
				httpServletRequest.setAttribute("nomeCampo", "lote");

		} else {
			httpServletRequest.setAttribute("idQuadraNaoEncontrada", "exception");
			form.setNumeroQuadraInicial( "");
			form.setDescricaoQuadraInicial("Quadra inexistente");
			httpServletRequest.setAttribute("nomeCampo", "idQuadra");
		}

	}

	/**
	 * @date 28/12/2011
	 * @author Arthur Carvalho
	 */
	private void pesquisarQuadraFinal(GerarTabelasTemporariasPorLocalidadeActionForm form,  HttpServletRequest httpServletRequest) {
		
		
		String local = form.getIdLocalidadeFinal();
		String setor = form.getCodigoSetorComercialFinal();
		String quadra = form.getNumeroQuadraFinal();
		
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(local)));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(setor)));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, new Integer(quadra)));
	    filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
	    filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");
			
		Collection<Quadra> quadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
		
		if (quadras != null && !quadras.isEmpty()) {
	
			Quadra quadraPesquisada = (Quadra) Util.retonarObjetoDeColecao(quadras);

			form.setNumeroQuadraFinal(quadraPesquisada.getNumeroQuadra() + "");
			form.setRotaFinal(quadraPesquisada.getRota().getCodigo().toString());
			httpServletRequest.setAttribute("idQuadraFinalNaoEncontrada", "true");
			httpServletRequest.setAttribute("nomeCampo", "lote");
	
		} else {
			httpServletRequest.setAttribute("idQuadraFinalNaoEncontrada", "exception");
			form.setNumeroQuadraFinal("");
			form.setRotaFinal("");
			form.setDescricaoQuadraFinal("Quadra inexistente");
//			httpServletRequest.setAttribute("nomeCampo", "idQuadra");
		}
	
	}
}

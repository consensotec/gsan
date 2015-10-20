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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarImovelAtualizacaoCadastralDispositivoMovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		ActionForward retorno = actionMapping
				.findForward("gerarArquivoTextoAtualizacaoCadastralDispositivoMovel");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
        GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form = (GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm) actionForm;
        
        Collection colecaoImovelFiltrado = new ArrayList();
        
        //Caso informe a inscri��o 
        if(form.getLocalidade() != null && !form.getLocalidade().equals("")){
        	 //Usu�rio logado
        	 Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        	//Localidade
        	Integer localidade = new Integer(form.getLocalidade());
			this.pesquisarLocalidade(localidade.toString(),form,fachada,httpServletRequest);
			//Setor comercial
        	String codigoSetorComercial = form.getSetorComercialCD();
        	if(codigoSetorComercial != null && !codigoSetorComercial.equals("")){
	        	if(form.getSetorComercialID() == null || form.getSetorComercialID().equals("")){        		
	        		this.pesquisarSetorComercial(localidade.toString(),codigoSetorComercial,form,fachada,httpServletRequest);       		
	        	}        	
	        	//Integer idsetor = new Integer(form.getSetorComercialID());
	        	//Quadra
	        	if(form.getQuadraInicial() != null && !form.getQuadraInicial().equals("")){
	            	String numeroQuadraInicial = form.getQuadraInicial();
	            	String numeroQuadraFinal = form.getQuadraFinal();
	          		this.pesquisarQuadraInicial(numeroQuadraInicial,codigoSetorComercial,localidade.toString(),form,fachada,httpServletRequest);
	    			if(new Integer(numeroQuadraInicial) > new Integer(numeroQuadraFinal)){				
	    				throw new ActionServletException(
	    							"atencao.quadra_final_menor", null, form.getIdImovel());	
	
	    			}else{
	    			    this.pesquisarQuadraFinal(numeroQuadraFinal,codigoSetorComercial,localidade.toString(),form,fachada,httpServletRequest);		
	    			}
	    			
	    			colecaoImovelFiltrado = fachada.obterImoveisAtualizacaoCadastral(localidade, codigoSetorComercial, new Integer(numeroQuadraInicial), 
	    											new Integer(numeroQuadraFinal), usuario.getEmpresa().getId(), null);
	    			form.setDescricaoArquivo("Loc"+localidade+"Set"+codigoSetorComercial+"QuadInic"+numeroQuadraInicial+"QuadFin"+numeroQuadraFinal);
	    			form.setCodigoRota("");
	    			form.setIdRota(null);
	        	}else if(form.getCodigoRota() != null && !form.getCodigoRota().equals("")){//Rota
	            	this.pesquisarRota(form, fachada, httpServletRequest, form.getSetorComercialID());
	            	Integer idRota = new Integer(form.getIdRota());
	    			colecaoImovelFiltrado = fachada.obterImoveisAtualizacaoCadastral(localidade, codigoSetorComercial, null, 
							null, usuario.getEmpresa().getId(), idRota);	   
	            	form.setDescricaoArquivo("Loc"+localidade+"Set"+codigoSetorComercial+"Rota"+form.getCodigoRota());
	            	form.setIdQuadraInicial(null);
	            	form.setQuadraInicial("");
	            	form.setIdQuadraFinal(null);
	            	form.setQuadraFinal("");            	
	            }else{
	        		colecaoImovelFiltrado = fachada.obterImoveisAtualizacaoCadastral(localidade, codigoSetorComercial, null, 
							null, usuario.getEmpresa().getId(), null);
	        		form.setDescricaoArquivo("Localidade"+localidade);	
	            }
        	}else{
        		colecaoImovelFiltrado = fachada.obterImoveisAtualizacaoCadastral(localidade, null, null, 
						null, usuario.getEmpresa().getId(), null);
        		form.setDescricaoArquivo("Localidade"+localidade);
        	}
			sessao.removeAttribute("informarDescricao");
			
			if (colecaoImovelFiltrado == null || colecaoImovelFiltrado.isEmpty() ) {
	        	//Nenhum Imovel cadastrado
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
        	}
			
        }else{//Caso informe os im�veis
        	Collection colecaoImovelAtualizacaoCadastral = (Collection)sessao.getAttribute("colecaoImovel");
        	Iterator iterator = colecaoImovelAtualizacaoCadastral.iterator();
        	
        	while(iterator.hasNext()){
        		
        		ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = (ImovelAtualizacaoCadastral) iterator.next();
        		colecaoImovelFiltrado.add(imovelAtualizacaoCadastral.getId());
        	}
        	form.setDescricaoArquivo("");
        	sessao.setAttribute("informarDescricao","Sim");
        	
        }
			
        sessao.setAttribute("colecaoImovelFiltrado",colecaoImovelFiltrado);

		return retorno;
	}
	
	/**
	 * Pesquisar Localidade
	 * @param filtroLocalidade
	 * @param idLocalidade
	 * @param localidades
	 * @param filtrarImovelAtualizacaoCadastralDispositivoMovelActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarLocalidade(
			String idLocalidade,
			GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		// coloca parametro no filtro
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, new Integer(idLocalidade)));

		// pesquisa
		Collection localidades = fachada.pesquisar(filtroLocalidade,
				Localidade.class.getName());
		if (localidades != null && !localidades.isEmpty()) {
			form.setLocalidade(((Localidade) ((List) localidades).get(0)).getId().toString());
			form.setNomeLocalidade(((Localidade) ((List) localidades).get(0)).getDescricao());
			
			httpServletRequest.setAttribute("localidadeNaoEncontrada","true");
			httpServletRequest.setAttribute("nomeCampo","setorComercialCD");
		} else {
			throw new ActionServletException(
					"atencao.localidade.inexistente", null, form.getIdImovel());	
		}
	}
	
	/**
	 * Pesquisar Setor Comercial
	 * @param filtroSetorComercial
	 * @param idLocalidadeFiltroFiltro
	 * @param codigoSetorComercial
	 * @param setorComerciais
	 * @param filtrarImovelAtualizacaoCadastralDispositivoMovelActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarSetorComercial( 
			String idLocalidadeFiltroFiltro,
			String codigoSetorComercial,  
			GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		if (idLocalidadeFiltroFiltro != null && !idLocalidadeFiltroFiltro.toString().trim().equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, new Integer(
							idLocalidadeFiltroFiltro)));
		}
			
		filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("municipio");
		
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
						codigoSetorComercial)));
		
		// pesquisa
		Collection setorComerciais = fachada.pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());
		if (setorComerciais != null && !setorComerciais.isEmpty()) {
			form.setSetorComercialID(""
					+ ((SetorComercial) ((List) setorComerciais).get(0))
							.getId());
			form.setSetorComercialCD(""
					+ ((SetorComercial) ((List) setorComerciais).get(0))
							.getCodigo());
			form.setNomeSetorComercial(
					((SetorComercial) ((List) setorComerciais).get(0))
							.getDescricao());
			
			httpServletRequest.setAttribute("setorComercialNaoEncontrada", "true");
			httpServletRequest.setAttribute("nomeCampo", "quadraInicial");
		} else {
			throw new ActionServletException(
					"atencao.setor_comercial.inexistente", null, form.getIdImovel());	

		}	
	}
	
	
	/**
	 * Pesquisar Quadra Inicial
	 * 
	 * @param filtroQuadra
	 * @param numeroQuadra
	 * @param codigoSetorComercial
	 * @param quadras
	 * @param GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarQuadraInicial(String numeroQuadra,
			String codigoSetorComercial, 
			String idLocalidadeFiltroFiltro,
			GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		
		FiltroQuadra filtroQuadra = new FiltroQuadra();		
		if (idLocalidadeFiltroFiltro != null && 
				!idLocalidadeFiltroFiltro.toString().trim().equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidadeFiltroFiltro)));
		}

		if (codigoSetorComercial != null
				&& !codigoSetorComercial.toString().trim().equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(codigoSetorComercial)));
		}
		filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroQuadra.NUMERO_QUADRA, new Integer(numeroQuadra)));

		// pesquisa
		Collection quadras = fachada.pesquisar(filtroQuadra, Quadra.class
				.getName());
		
		if (quadras != null && !quadras.isEmpty()) {
			// O cliente foi encontrado
			form.setQuadraInicial(""
					+ ((Quadra) ((List) quadras).get(0)).getNumeroQuadra());
			form.setIdQuadraInicial(""
					+ ((Quadra) ((List) quadras).get(0)).getId());

		} else {
			throw new ActionServletException(
					"atencao.quadra.inexistente", null, form.getIdImovel());	
			
		}			
	}
	
	/**
	 * Pesquisar Quadra Final
	 * 
	 * @param filtroQuadra
	 * @param numeroQuadra
	 * @param codigoSetorComercial
	 * @param quadras
	 * @param GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarQuadraFinal(String numeroQuadra,
			String codigoSetorComercial, 
			String idLocalidadeFiltroFiltro,
			GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		
		FiltroQuadra filtroQuadra = new FiltroQuadra();		
		if (idLocalidadeFiltroFiltro != null && 
				!idLocalidadeFiltroFiltro.toString().trim().equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidadeFiltroFiltro)));
		}

		if (codigoSetorComercial != null
				&& !codigoSetorComercial.toString().trim().equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(codigoSetorComercial)));
		}
		filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroQuadra.NUMERO_QUADRA, new Integer(numeroQuadra)));

		// pesquisa
		Collection quadras = fachada.pesquisar(filtroQuadra, Quadra.class
				.getName());
		
		if (quadras != null && !quadras.isEmpty()) {
			// O cliente foi encontrado
			form.setQuadraFinal(""
					+ ((Quadra) ((List) quadras).get(0)).getNumeroQuadra());
			form.setIdQuadraFinal(""
					+ ((Quadra) ((List) quadras).get(0)).getId());					

		} else {
			throw new ActionServletException(
					"atencao.quadra.inexistente", null, form.getIdImovel());	
		}			
	}
	
    /**
     * 
     * @param form
     * @param fachada
     * @param httpServletRequest
     */
    private void pesquisarRota(GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form,
            Fachada fachada, HttpServletRequest httpServletRequest, String setorComercialId) {
    	
    	Rota objetoRota = null;
        	
    	//Recebe o valor do campo rotaID do formul�rio.
        String rotaCodigo = form.getCodigoRota();

        FiltroRota filtroRota = new FiltroRota();

        filtroRota.adicionarParametro(new ParametroSimples(
        FiltroRota.SETOR_COMERCIAL_ID, new Integer(setorComercialId)));
        
        filtroRota.adicionarParametro(new ParametroSimples(
        FiltroRota.CODIGO_ROTA, rotaCodigo));

        filtroRota.adicionarParametro(new ParametroSimples(
        FiltroRota.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

        //Retorna Rota
        Collection colecaorotas = fachada.pesquisar(filtroRota, Rota.class
                .getName());

        if (colecaorotas == null || colecaorotas.isEmpty()) {
            //Rota nao encontrada
			throw new ActionServletException(
					"atencao.pesquisa.rota_inexistente", null, form.getIdImovel());	
            
        } else {
            objetoRota = (Rota) Util
                    .retonarObjetoDeColecao(colecaorotas);
            form.setCodigoRota(objetoRota.getCodigo().toString());
            form.setIdRota(String.valueOf(objetoRota.getId()));
            httpServletRequest.setAttribute("corRotaMensagem", "valor");                
            httpServletRequest.setAttribute("nomeCampo", "botaoSelecionar");
        }

    }

}

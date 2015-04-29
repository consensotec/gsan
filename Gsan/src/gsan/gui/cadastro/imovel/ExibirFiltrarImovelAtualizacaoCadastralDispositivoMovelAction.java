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
package gsan.gui.cadastro.imovel;

import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelAtualizacaoCadastral;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarImovelAtualizacaoCadastralDispositivoMovelAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("exibirFiltrarImovelAtualizacaoCadastralDispositivoMovel");

        //Obt�m a inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);
        
        GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form = (GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm) actionForm;

		String idLocalidade = null;
		String codigoSetorComercial = null;
		String numeroQuadraInicial = null;
		String numeroQuadraFinal = null;
		
    	idLocalidade = (String) form.getLocalidade();
    	codigoSetorComercial = (String) form.getSetorComercialCD();
    	numeroQuadraInicial = (String) form.getQuadraInicial();
    	numeroQuadraFinal = (String) form.getQuadraFinal();

		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")){
			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:
		
				this.pesquisarLocalidade(idLocalidade,form,fachada,httpServletRequest);
		
				break;
			// Setor Comercial
			case 2:
		
				this.pesquisarSetorComercial( idLocalidade,codigoSetorComercial,form,fachada,httpServletRequest);
				break;
				
			// Quadra Inicial
			case 3:				
				this.pesquisarQuadraInicial(numeroQuadraInicial,codigoSetorComercial,idLocalidade,form,fachada,httpServletRequest);		
				break;

			// Quadra Final
			case 4:		
				if(new Integer(numeroQuadraInicial) > new Integer(numeroQuadraFinal)){
					form.setQuadraFinal("");
					form.setIdQuadraFinal(null);					
		            httpServletRequest.setAttribute( "codigoQuadraFinalNaoEncontrada", "true");
					httpServletRequest.setAttribute("msgQuadra", "QUADRA FINAL MENOR QUE INICIAL");
				}else{
				    this.pesquisarQuadraFinal(numeroQuadraFinal,codigoSetorComercial,idLocalidade,form,fachada,httpServletRequest);		
				}
				break;
				
			//Rota
			case 5:
				this.pesquisarRota(form, fachada, httpServletRequest, form.getSetorComercialID());
				break;
				
			//Im�vel
			case 6:
				this.pesquisarImovel(httpServletRequest, fachada, form);			
				
			default:
				break;
			}
		}
		
		if (form.getIdRota() != null && !form.getIdRota().trim().equals("")) {
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, form.getIdRota()));
			
			Collection colecaoRota = fachada.pesquisar(filtroRota, Rota.class.getName());
			
			if (colecaoRota != null && !colecaoRota.isEmpty()) {
				Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRota);
				form.setCodigoRota(rota.getCodigo().toString());
			}
		}
		
		
		Collection colecaoImovel = (Collection)sessao.getAttribute("colecaoImovel");

		//Adicionar Im�vel
		String adicionarImovel = (String) httpServletRequest.getParameter("adicionarImovel");
		if (adicionarImovel != null && !adicionarImovel.trim().equalsIgnoreCase("")){
       	 	//Usu�rio logado
       	 	Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
       	 	ImovelAtualizacaoCadastral imovel = fachada.pesquisarImovelAtualizacaoCadastralInscricao(new Integer(form.getIdImovel().trim()), 
											usuario.getEmpresa().getId());
			if(imovel != null && !imovel.equals("")){	
				colecaoImovel = (Collection)sessao.getAttribute("colecaoImovel");
				if(colecaoImovel == null || colecaoImovel.isEmpty()){
					colecaoImovel = new ArrayList();								
			    }else{
					if(colecaoImovel.contains(imovel.getImovel().getId())){
						throw new ActionServletException(
								"atencao.imovel_ja_incluido", null, form.getIdImovel());	
					}
			    }
				colecaoImovel.add(imovel);	
				sessao.setAttribute("colecaoImovel",colecaoImovel);
				httpServletRequest.setAttribute("existeColecaoImovel","Sim");
				form.setIdImovel("");
				form.setInscricaoImovel("");
		    }else{
				form.setIdImovel("");
				form.setInscricaoImovel("Im�vel Indispon�vel");
				httpServletRequest.setAttribute("existeImovel","exception");	
		    }
		}
		
		//Remover Im�vel
		if(httpServletRequest.getParameter("idImovelRemover") != null){
			Integer idImovelRemover = new Integer(httpServletRequest.getParameter("idImovelRemover"));
			colecaoImovel = (Collection)sessao.getAttribute("colecaoImovel");			
			if(colecaoImovel != null && !colecaoImovel.isEmpty()){
				ImovelAtualizacaoCadastral imovelAtualizacao = new ImovelAtualizacaoCadastral();
				
				/*Imovel imov = new Imovel();
				imov.setId(idImovelRemover);
				
				imovelAtualizacao.setImovel(imov);*/
				
				imovelAtualizacao.setId(idImovelRemover);
				
				colecaoImovel.remove(imovelAtualizacao);	
				if(colecaoImovel != null && !colecaoImovel.isEmpty()){
				  sessao.setAttribute("colecaoImovel", colecaoImovel);
				  httpServletRequest.setAttribute("existeColecaoImovel","Sim");
				}
			}
		}
		
		if(colecaoImovel != null && !colecaoImovel.isEmpty()){
			httpServletRequest.setAttribute("existeColecaoImovel","Sim");
		}
		
        return retorno;
    }

	/**
	 * Pesquisar Im�vel
	 * @param httpServletRequest
	 * @param fachada	  
	 * @param filtrarImovelAtualizacaoCadastralDispositivoMovelActionForm
	 */
	private void pesquisarImovel(HttpServletRequest httpServletRequest, Fachada fachada, GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form) {

		String imovelEncontrado = fachada.pesquisarInscricaoImovel(new Integer(form.getIdImovel()));
		if (imovelEncontrado != null && !imovelEncontrado.equalsIgnoreCase("")) {
			
			// O imovel foi encontrado
			form.setIdImovel(form.getIdImovel());
			form.setInscricaoImovel(imovelEncontrado);
		} else {
			form.setIdImovel("");
			form.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
			httpServletRequest.setAttribute("existeImovel","exception");
		}
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
			form.setLocalidade("");
			form.setNomeLocalidade("Localidade inexistente");
			
			httpServletRequest.setAttribute("localidadeNaoEncontrada","exception");
			httpServletRequest.setAttribute("nomeCampo", "localidade");
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
		if (idLocalidadeFiltroFiltro != null
				&& !idLocalidadeFiltroFiltro.toString().trim().equalsIgnoreCase("")) {
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
			form.setSetorComercialCD( "");
			form.setNomeSetorComercial("Setor comercial inexistente");
			
			httpServletRequest.setAttribute("setorComercialNaoEncontrada", "exception");
			httpServletRequest.setAttribute("nomeCampo","setorComercialCD");

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
			httpServletRequest.setAttribute("nomeCampo", "quadraFinal");
		} else {
			form.setQuadraInicial("");
			form.setIdQuadraInicial(null);
			
            httpServletRequest.setAttribute( "codigoQuadraInicialNaoEncontrada", "true");
			httpServletRequest.setAttribute("msgQuadra", "QUADRA INICIAL INEXISTENTE");
			
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
			form.setQuadraFinal("");
			form.setIdQuadraFinal(null);
			
            httpServletRequest.setAttribute( "codigoQuadraFinalNaoEncontrada", "true");
			httpServletRequest.setAttribute("msgQuadra", "QUADRA FINAL INEXISTENTE");
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
            //Limpa o campo idRota do formul�rio
    		form.setCodigoRota("");
    		form.setIdRota("");
            httpServletRequest.setAttribute( "codigoRotaNaoEncontrada", "true");
			httpServletRequest.setAttribute("msgRota", "ROTA INEXISTENTE");
            
        } else {
            objetoRota = (Rota) Util
                    .retonarObjetoDeColecao(colecaorotas);
            form.setCodigoRota(objetoRota.getCodigo().toString());
            form.setIdRota(String.valueOf(objetoRota.getId()));
        }
    }

}

package gcom.gui.portal.caern;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.imovel.Categoria;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.portal.caema.ConsultarEstruturaTarifariaPortalCaemaHelper;
import gcom.util.Util;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1194] Consultar Estrutura Tarifária Loja Virtual
 * 
 * Classe responsável por configurar as coleções da estrutura
 * tarifária para serem exibidas na tela 
 * 
 * informacoes_estrutura_tarifaria_portal_caern_consultar.jsp
 * 
 * @author Rafael Pinto
 * @since 19/07/2013
 *
 */
public class ExibirConsultarEstruturaTarifariaPortalCaernAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirConsultarEstruturaTarifariaPortalCaernAction");
		request.setAttribute("voltarInformacoes", true);
		
		String ip = request.getRemoteAddr(); 
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.ESTRUTURA_TARIFARIA, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO); 

		
		//Consumos Medidos Residencial
		Integer idSubCategoriaMinima = new Integer(0);
		Integer idSubCategoriaNormal = new Integer(0);

		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperResidencial = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.RESIDENCIAL, idSubCategoriaMinima , idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperResidencial)){
			request.setAttribute("helperResidencial", helperResidencial);
			
		}
		
		//Consumos Medidos Residencial Popular
		idSubCategoriaMinima = new Integer(1);
		idSubCategoriaNormal = new Integer(1);

		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperResidencialPopular = 
			this.getFachada().pesquisarEstruturaTarifaria(
				Categoria.RESIDENCIAL, 
				idSubCategoriaMinima , 
				idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperResidencialPopular)){
			request.setAttribute("helperResidencialPopular", helperResidencialPopular);
		}
		
		//Consumos Medidos Residencial Popular Rural
		idSubCategoriaMinima = new Integer(62);
		idSubCategoriaNormal = new Integer(62);

		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperResidencialPopularRural = 
			this.getFachada().pesquisarEstruturaTarifaria(
				Categoria.RESIDENCIAL, 
				idSubCategoriaMinima , 
				idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperResidencialPopularRural)){
			request.setAttribute("helperResidencialPopularRural", helperResidencialPopularRural);
		}
		
		//Consumos Medidos Residencial Rural
		idSubCategoriaMinima = new Integer(63);
		idSubCategoriaNormal = new Integer(63);

		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperResidencialRural = 
			this.getFachada().pesquisarEstruturaTarifaria(
				Categoria.RESIDENCIAL, 
				idSubCategoriaMinima , 
				idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperResidencialRural)){
			request.setAttribute("helperResidencialRural", helperResidencialRural);
		}			
		
		//Consumos Medidos Residencial Social Rural
		idSubCategoriaMinima = new Integer(64);
		idSubCategoriaNormal = new Integer(64);

		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperResidencialSocialRural = 
			this.getFachada().pesquisarEstruturaTarifaria(
				Categoria.RESIDENCIAL, 
				idSubCategoriaMinima , 
				idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperResidencialSocialRural)){
			request.setAttribute("helperResidencialSocialRural", helperResidencialSocialRural);
		}			

		//Consumos Medidos Comercial
		idSubCategoriaMinima = new Integer(0);
		idSubCategoriaNormal = new Integer(0);
		
		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperComercial = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.COMERCIAL, idSubCategoriaMinima, idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperComercial)){
			request.setAttribute("helperComercial", helperComercial);
		
		}
		
		//Consumos Medidos Comercial Rural
		idSubCategoriaMinima = new Integer(65);
		idSubCategoriaNormal = new Integer(65);
		
		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperComercialRural = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.COMERCIAL, idSubCategoriaMinima, idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperComercialRural)){
			request.setAttribute("helperComercialRural", helperComercialRural);
		
		}		

		//Consumos Medidos Industrial
		idSubCategoriaMinima = new Integer(0);
		idSubCategoriaNormal = new Integer(0);

		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperIndustrial = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.INDUSTRIAL, idSubCategoriaMinima, idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperIndustrial)){
			request.setAttribute("helperIndustrial", helperIndustrial);

		}
		
		//Consumos Medidos Industrial Rural
		idSubCategoriaMinima = new Integer(66);
		idSubCategoriaNormal = new Integer(66);

		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperIndustrialRural = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.INDUSTRIAL, idSubCategoriaMinima, idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperIndustrialRural)){
			request.setAttribute("helperIndustrialRural", helperIndustrialRural);

		}

		//Consumos Medidos Público
		idSubCategoriaMinima = new Integer(0);
		idSubCategoriaNormal = new Integer(0);
		
		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperPublico = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.PUBLICO, idSubCategoriaMinima, idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperPublico)){
			request.setAttribute("helperPublico", helperPublico);
			
		}
		
		//Consumos Medidos Público Federal Rural
		idSubCategoriaMinima = new Integer(67);
		idSubCategoriaNormal = new Integer(67);
		
		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperPublicoFederalRural = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.PUBLICO, idSubCategoriaMinima, idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperPublicoFederalRural)){
			request.setAttribute("helperPublicoFederalRural", helperPublicoFederalRural);
			
		}
		
		//Consumos Medidos Público Estadual Rural
		idSubCategoriaMinima = new Integer(68);
		idSubCategoriaNormal = new Integer(68);
		
		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperPublicoEstadualRural = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.PUBLICO, idSubCategoriaMinima, idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperPublicoEstadualRural)){
			request.setAttribute("helperPublicoEstadualRural", helperPublicoEstadualRural);
			
		}
		
		//Consumos Medidos Público Municipal Rural
		idSubCategoriaMinima = new Integer(69);
		idSubCategoriaNormal = new Integer(69);
		
		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperPublicoMunicipalRural = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.PUBLICO, idSubCategoriaMinima, idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperPublicoMunicipalRural)){
			request.setAttribute("helperPublicoMunicipalRural", helperPublicoMunicipalRural);
			
		}		
		
		
		return retorno;
	}

}
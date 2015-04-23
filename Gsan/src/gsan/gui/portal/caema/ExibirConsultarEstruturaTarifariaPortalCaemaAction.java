package gsan.gui.portal.caema;

import gsan.cadastro.imovel.Categoria;
import gsan.gui.GcomAction;
import gsan.util.Util;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**[UC1194] Consultar Estrutura Tarifária Loja Virtual
 * 
 * Classe responsável por configurar as coleções da estrutura
 * tarifária para serem exibidas na tela 
 * informacoes_estrutura_tarifaria_portal_consultar.jsp
 * 
 * @author Arthur Carvalho
 * @since 20/01/2012
 *
 */
public class ExibirConsultarEstruturaTarifariaPortalCaemaAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirConsultarEstruturaTarifariaPortalCaemaAction");
		request.setAttribute("voltarInformacoes", true);
		
		
		Integer idSubCategoriaMinima = new Integer(0);
		Integer idSubCategoriaNormal = new Integer(0);
		
		//Consumos Medidos Residencial
		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperResidencial = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.RESIDENCIAL, idSubCategoriaMinima , idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperResidencial)){
			request.setAttribute("helperResidencial", helperResidencial);
			
		}
		
		idSubCategoriaMinima = new Integer(7);
		idSubCategoriaNormal = new Integer(7);
		
		//Consumos Medidos Residencial Popular
		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperResidencialPopular = 
			this.getFachada().pesquisarEstruturaTarifaria(
				Categoria.RESIDENCIAL, 
				idSubCategoriaMinima , 
				idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperResidencialPopular)){
			request.setAttribute("helperResidencialPopular", helperResidencialPopular);
			
		}
		
		idSubCategoriaMinima = new Integer(9);
		idSubCategoriaNormal = new Integer(9);
		
		//Consumos Medidos de entidades filantropicas
		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperEntidadesFilantropicas = 
			this.getFachada().pesquisarEstruturaTarifaria(
				Categoria.RESIDENCIAL, 
				idSubCategoriaMinima , 
				idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperEntidadesFilantropicas)){
			request.setAttribute("helperEntidadesFilantropicas", helperEntidadesFilantropicas);
			
		}
		
		
		idSubCategoriaMinima = new Integer(0);
		idSubCategoriaNormal = new Integer(0);

		
		//Consumos Medidos Comercial
		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperComercial = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.COMERCIAL, idSubCategoriaMinima, idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperComercial)){
			request.setAttribute("helperComercial", helperComercial);
		
		}
		
		idSubCategoriaMinima = new Integer(8);
		idSubCategoriaNormal = new Integer(8);

		
		//Consumos Medidos Comercial
		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperComercialPequenosNegocios = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.COMERCIAL, idSubCategoriaMinima, idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperComercialPequenosNegocios)){
			request.setAttribute("helperComercialPequenosNegocios", helperComercialPequenosNegocios);
		
		}
		
		idSubCategoriaMinima = new Integer(0);
		idSubCategoriaNormal = new Integer(0);
		//Consumos Medidos Industrial
		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperIndustrial = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.INDUSTRIAL, idSubCategoriaMinima, idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperIndustrial)){
			request.setAttribute("helperIndustrial", helperIndustrial);

		}
		
		//Consumos Medidos Pública
		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperPublica = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.PUBLICO, idSubCategoriaMinima, idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperPublica)){
			request.setAttribute("helperPublica", helperPublica);
			
		}
		
		idSubCategoriaMinima = new Integer(10);
		idSubCategoriaNormal = null;
		
		//Consumos Medidos Pública
		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperPrecoPorAtacado = 
			this.getFachada().pesquisarEstruturaTarifaria(Categoria.PUBLICO, idSubCategoriaMinima, idSubCategoriaNormal);
		if(!Util.isVazioOrNulo(helperPrecoPorAtacado)){
			request.setAttribute("helperPrecoPorAtacado", helperPrecoPorAtacado);
			
		}
		
		
		return retorno;
	}

}
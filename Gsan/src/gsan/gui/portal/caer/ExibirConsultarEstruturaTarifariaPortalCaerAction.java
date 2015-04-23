package gsan.gui.portal.caer;

import gsan.cadastro.imovel.Categoria;
import gsan.faturamento.consumotarifa.ConsumoTarifa;
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
public class ExibirConsultarEstruturaTarifariaPortalCaerAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirConsultarEstruturaTarifariaPortalCaerAction");
		request.setAttribute("voltarInformacoes", true);
		
		ArrayList<ConsultarEstruturaTarifariaPortalCaerHelper> helper = 
				new ArrayList<ConsultarEstruturaTarifariaPortalCaerHelper>();
		
		//Consumos Medidos Residencial
		ArrayList<ConsultarEstruturaTarifariaPortalCaerHelper> helperResidencial = 
			this.getFachada().pesquisarEstruturaTarifariaCaer(Categoria.RESIDENCIAL, ConsumoTarifa.CONSUMO_NORMAL);
		if(!Util.isVazioOrNulo(helperResidencial)){
			ConsultarEstruturaTarifariaPortalCaerHelper helpResidencial = 
					(ConsultarEstruturaTarifariaPortalCaerHelper) Util.retonarObjetoDeColecao(helperResidencial);
			helper.add(helpResidencial);
			request.setAttribute("helperResidencial", helperResidencial);
		}
		
		//Consumos Medidos Comercial
		ArrayList<ConsultarEstruturaTarifariaPortalCaerHelper> helperComercial = 
			this.getFachada().pesquisarEstruturaTarifariaCaer(Categoria.COMERCIAL, ConsumoTarifa.CONSUMO_NORMAL);
		if(!Util.isVazioOrNulo(helperComercial)){
			ConsultarEstruturaTarifariaPortalCaerHelper helpComercial = 
					(ConsultarEstruturaTarifariaPortalCaerHelper) Util.retonarObjetoDeColecao(helperComercial);
			helper.add(helpComercial);
		}
		
		//Consumos Medidos Industrial
		ArrayList<ConsultarEstruturaTarifariaPortalCaerHelper> helperIndustrial = 
			this.getFachada().pesquisarEstruturaTarifariaCaer(Categoria.INDUSTRIAL, ConsumoTarifa.CONSUMO_NORMAL);
		if(!Util.isVazioOrNulo(helperIndustrial)){
			ConsultarEstruturaTarifariaPortalCaerHelper helpIndustrial = 
					(ConsultarEstruturaTarifariaPortalCaerHelper) Util.retonarObjetoDeColecao(helperIndustrial);
			helper.add(helpIndustrial);
		}
		
		//Consumos Medidos Pública
		ArrayList<ConsultarEstruturaTarifariaPortalCaerHelper> helperPublica = 
			this.getFachada().pesquisarEstruturaTarifariaCaer(Categoria.PUBLICO, ConsumoTarifa.CONSUMO_NORMAL);
		if(!Util.isVazioOrNulo(helperPublica)){
			ConsultarEstruturaTarifariaPortalCaerHelper helpPublica = 
					(ConsultarEstruturaTarifariaPortalCaerHelper) Util.retonarObjetoDeColecao(helperPublica);
			helper.add(helpPublica);
		}
		
		if(!Util.isVazioOrNulo(helper)){
			request.setAttribute("helper", helper);
		}
		
		//Tarifa Social
		ArrayList<ConsultarEstruturaTarifariaPortalCaerHelper> helperTarifaSocial = 
			this.getFachada().pesquisarEstruturaTarifariaCaer(Categoria.RESIDENCIAL, ConsumoTarifa.CONSUMO_SOCIAL);
		if(!Util.isVazioOrNulo(helperTarifaSocial)){
			request.setAttribute("helperTarifaSocial", helperTarifaSocial);
		}
		
		return retorno;
	}

}
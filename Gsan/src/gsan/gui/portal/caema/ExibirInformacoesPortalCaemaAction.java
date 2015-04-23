package gsan.gui.portal.caema;

import gsan.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável por exibir Canais de Atendimento
 * 
 * @author Nathalia Santos 
 * @date 17/01/2012
 */
public class ExibirInformacoesPortalCaemaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		String retorno = "exibirInformacoesPortalCaemaAction";

		String method = httpServletRequest.getParameter("method");

		if (method == null) {
			method = "";
		}
		
		if (method.equalsIgnoreCase("parcelamentoDebito")) {
			retorno = "exibirInformacoesParcelamentoDebitoPortalCaemaAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);

		} else if (method.equalsIgnoreCase("estruturaTarifaria")) {
			retorno = "exibirInformacoesEstruturaTarifariaPortalCaemaAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);

		} else if (method.equalsIgnoreCase("regulamentoServicos")) {
			retorno = "exibirConsultarRegulamentoServicosPortalCaemaAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);
		
		} else if (method.equalsIgnoreCase("vivaAgua")) {
			retorno = "exibirInformacoesVivaAguaPortalCaemaAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);

		} else if (method.equalsIgnoreCase("tabelaServicos")) {
			retorno = "exibirInformacoesTabelaServicosPortalCaemaAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);

		} else if (method.equalsIgnoreCase("ligacaoAguaEsgoto")) {
			retorno = "exibirInformacoesLigacaoAguaEsgotoPortalCaemaAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);

		} else if (method.equalsIgnoreCase("debitoAutomatico")) {
			retorno = "exibirInformacoesDebitoAutomaticoPortalCaemaAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);
		
		} else if (method.equalsIgnoreCase("pagamentoFatura")) {
			retorno = "exibirConsultarPagamentoFaturaPortalCaemaAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);

		}else if (method.equalsIgnoreCase("orientacoesCliente")) {
			retorno = "exibirInformacoesOrientacoesClientePortalCaemaAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);

		}else if (method.equalsIgnoreCase("validarCertidaoNegativaDebito")) {
			retorno = "exibirValidarCertidaoNegativaDebitoPortalCaemaAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);

		}
		

		return actionMapping.findForward(retorno);
	}
	


	
}
package gcom.gui.portal.caer;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável por exibir Canais de Atendimento da loja virtual da CAER
 * 
 * @author Davi Menezes 
 * @date 10/09/2012
 */
public class ExibirInformacoesPortalCaerAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		String retorno = "exibirInformacoesPortalCaerAction";

		String method = httpServletRequest.getParameter("method");

		if (method == null) {
			method = "";
		}
		
		String ip = httpServletRequest.getRemoteAddr();
		
		if (method.equalsIgnoreCase("parcelamentoDebito")) {
			retorno = "exibirInformacoesParcelamentoDebitoPortalCaerAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);
			
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.NEGOCIACAO_DEBITO, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
			
		}else if (method.equalsIgnoreCase("validarCertidaoNegativaDebito")){
			retorno = "exibirValidarCertidaoNegativaDebitoPortalCaerAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);
			
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.VALIDAR_CERTIDAO_NEGATIVA, AcessoLojaVirtual.INDICADOR_SERVICO_NAO_EXECUTADO);
		}
		
		return actionMapping.findForward(retorno);
	}
	
}
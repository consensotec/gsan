package gcom.gui.portal.saae;

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
 * @author Cesar Medeiros
 * @date 16/09/2015
 */
public class ExibirInformacoesPortalSaaeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		String retorno = "exibirInformacoesPortalSaaeAction";

		String method = httpServletRequest.getParameter("method");

		if (method == null) {
			method = "";
		}
		
		String ip = httpServletRequest.getRemoteAddr();
		
		if (method.equalsIgnoreCase("parcelamentoDebito")) {
			retorno = "exibirInformacoesParcelamentoDebitoPortalSaaeAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);
			
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.NEGOCIACAO_DEBITO, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
			
		}else if (method.equalsIgnoreCase("validarCertidaoNegativaDebito")){
			retorno = "exibirValidarCertidaoNegativaDebitoPortalSaaeAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);
			
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.VALIDAR_CERTIDAO_NEGATIVA, AcessoLojaVirtual.INDICADOR_SERVICO_NAO_EXECUTADO);
		}
		
		return actionMapping.findForward(retorno);
	}
	
}
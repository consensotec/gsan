package gcom.gui.portal.caern;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável por exibir as Informações da loja virtual da CAERN
 * 
 * @author Rafael Pinto
 * @date 15/07/2013
 */
public class ExibirInformacoesPortalCaernAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		String retorno = "exibirInformacoesPortalCaernAction";

		String method = httpServletRequest.getParameter("method");

		if (method == null) {
			method = "";
		}
		
		String ip = httpServletRequest.getRemoteAddr(); 
		
		if (method.equalsIgnoreCase("parcelamentoDebito")) {
			retorno = "exibirInformacoesParcelamentoDebitoPortalCaernAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);
			
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.NEGOCIACAO_DEBITO, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO); 
			
		}else if (method.equalsIgnoreCase("validarCertidaoNegativaDebito")){
			retorno = "exibirValidarCertidaoNegativaDebitoPortalCaernAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);
			
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.VALIDAR_CERTIDAO_NEGATIVA, AcessoLojaVirtual.INDICADOR_SERVICO_NAO_EXECUTADO);
			
		}else if (method.equalsIgnoreCase("usoRacionalAgua")){
			retorno = "exibirUsoRacionalAguaPortalCaernAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);
			
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.VOLUME_CONSUMIDO, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);

		}else if (method.equalsIgnoreCase("orientacoesCliente")){
			retorno = "exibirOrientacoesClientePortalCaernAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);
			
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip,AcessoLojaVirtual.ORIENTACOES_CLIENTE , AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);

		}else if (method.equalsIgnoreCase("debitoAutomatico")){
			retorno = "exibirDebitoAutomaticoPortalCaernAction";
			httpServletRequest.setAttribute("voltarInformacoes", true);
			
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.DEBITO_AUTOMATICO, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
		}


		
		
		
		return actionMapping.findForward(retorno);
	}
	
}
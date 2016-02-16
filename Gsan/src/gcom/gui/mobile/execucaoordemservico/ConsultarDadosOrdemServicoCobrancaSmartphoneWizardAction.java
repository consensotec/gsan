package gcom.gui.mobile.execucaoordemservico;

import gcom.gui.WizardAction;
import gcom.gui.atendimentopublico.ordemservico.ExibirInformarRetornoOSFiscalizacaoAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConsultarDadosOrdemServicoCobrancaSmartphoneWizardAction extends WizardAction {

    public ActionForward exibirConsultarDadosOrdemServicoCobrancaSmartphoneRetornoAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new ExibirConsultarDadosOrdemServicoCobrancaSmartphoneRetornoAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

    public ActionForward consultarDadosOrdemServicoCobrancaSmartphoneRetornoAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new ConsultarDadosOrdemServicoCobrancaSmartphoneRetornoAction().execute(actionMapping, actionForm,
        													httpServletRequest,
        													httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, actionForm,
                						httpServletRequest, 
                						httpServletResponse);
    }

    public ActionForward exibirConsultarDadosOrdemServicoCobrancaSmartphoneFotosAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new ExibirConsultarDadosOrdemServicoCobrancaSmartphoneFotosAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

    public ActionForward consultarDadosOrdemServicoCobrancaSmartphoneFotosAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new ConsultarDadosOrdemServicoCobrancaSmartphoneFotosAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

    public ActionForward consultarDadosOrdemServicoCobrancaSmartphoneAction(ActionMapping actionMapping,
            ActionForm actionForm, 
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        new ConsultarDadosOrdemServicoCobrancaSmartphoneAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, 
				httpServletResponse);
        
    }
    
    /*
	 * CONCLUIR
	 */
	public ActionForward encerrarOrdemServicoIndividualCobrancaSmartphoneAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new EncerrarOrdemServicoIndividualCobrancaSmartphoneAction().execute(actionMapping,
				actionForm, httpServletRequest, httpServletResponse);
	}
	
	public ActionForward exibirInformarRetornoOSFiscalizacaoAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new ExibirInformarRetornoOSFiscalizacaoAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }
}

package gcom.gui.mobile.execucaoordemservico;



import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC-1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
 * [IT0016] Excluir Arquivo
 * 
 * @date 19/09/2013  
 * @author Bruno Barros
 *
 */
public class ExcluirArquivoTextoOrdensServicoSmartphoneAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		//Form
		ConsultarArquivoTextoOSCobrancaSmartphoneActionForm form = (ConsultarArquivoTextoOSCobrancaSmartphoneActionForm) actionForm;

		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		// Informa os ids selecionados para exclusão
		fachada.excluirArquivoTextoOrdensServicoCobranca( form.getIdsRegistros() );
		
		montarPaginaSucesso(httpServletRequest,
				"Arquivos excluidos com sucesso.",
				"Realizar outra operação no arquivos de OS de Cobrança",
				"exibirConsultarDadosArquivoTextoOSCobrancaSmartphoneAction.do?menu=sim");
		
		return retorno;

	}
}

package gcom.gui.mobile.execucaoordemservico;

import gcom.gui.GcomAction;
import gcom.util.ErroRepositorioException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
 *
 * @author Jean Varela
 * @throws ErroRepositorioException 
 * @date   18/11/2015	
 */
public class ExibirInformarMotivoFinalizacaoAction extends GcomAction {
	
	private ConsultarArquivoTextoOSCobrancaSmartphoneActionForm form;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
								 HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirInformarMotivoFinalizacaoAction");

		form = (ConsultarArquivoTextoOSCobrancaSmartphoneActionForm) actionForm;

		form.setMotivoFinalizacao("");

		return retorno;
	}
}

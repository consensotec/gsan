package gcom.gui.arrecadacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.GcomAction;

/**
 * [UC 1243 - Consultar Documento pelo Nosso n�mero]
 * 
 * @author Davi
 * @date 19/10/2011
 *
 */

public class ExibirConsultarDocumentoArrecadacaoNossoNumeroAction extends GcomAction {
	
	/**
	 * M�todo responsavel por consultar o documento de arrecada��o pelo nosso n�mero
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("consultaDocumentoNossoNumero");
		
		return retorno;
	}

}

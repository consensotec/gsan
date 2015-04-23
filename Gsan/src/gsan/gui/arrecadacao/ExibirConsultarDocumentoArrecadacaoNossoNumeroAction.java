package gsan.gui.arrecadacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.gui.GcomAction;

/**
 * [UC 1243 - Consultar Documento pelo Nosso número]
 * 
 * @author Davi
 * @date 19/10/2011
 *
 */

public class ExibirConsultarDocumentoArrecadacaoNossoNumeroAction extends GcomAction {
	
	/**
	 * Método responsavel por consultar o documento de arrecadação pelo nosso número
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("consultaDocumentoNossoNumero");
		
		return retorno;
	}

}

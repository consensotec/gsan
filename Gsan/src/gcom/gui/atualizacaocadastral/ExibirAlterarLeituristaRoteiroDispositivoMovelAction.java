package gcom.gui.atualizacaocadastral;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.GcomAction;

/**
 * [UC 1392] - Consultar Roteiro Dispositivo M�vel Atualiza��o Cadastral
 * 
 * @author Davi Menezes
 * @date 26/11/2012
 *
 */
public class ExibirAlterarLeituristaRoteiroDispositivoMovelAction extends GcomAction {

	/**
	 * 
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirAlterarLeituristaRoteiroDispositivoMovelPopup");
						
		return retorno;
	}
}

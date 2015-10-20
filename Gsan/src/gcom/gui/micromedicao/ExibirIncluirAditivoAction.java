package gcom.gui.micromedicao;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Tela de PopUp de Incluir Aditivo
 * 
 * @author Mariana Victor
 * @date 24/11/2010
 */
public class ExibirIncluirAditivoAction extends GcomAction {
	
		/**
		 * < <Descri��o do m�todo>>
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
			
			ActionForward retorno = actionMapping
				.findForward("exibirIncluirAditivo");

			HttpSession sessao = httpServletRequest.getSession(false);
			
			ExibirInformarItensContratoServicoActionForm exibirInformarItensContratoServicoActionForm = (ExibirInformarItensContratoServicoActionForm) actionForm;
			
			sessao.setAttribute("idContrato", httpServletRequest.getParameter("idContrato"));
			
			return retorno;
		}
}

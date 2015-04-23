package gsan.gui.atualizacaocadastral;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.gui.GcomAction;

public class ExibirRecepcionarDadosImoveisTabletOfflineAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
		return actionMapping.findForward("exibirRecepcionarDadosImoveisTabletOffline");
	}
}
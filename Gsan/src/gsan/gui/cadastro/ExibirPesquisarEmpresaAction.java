package gsan.gui.cadastro;

import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0777] Pesquisar Empresa
 * 
 * @see gsan.gui.cadastro.PesquisarEmpresaAction
 * @see gsan.gui.cadastro.PesquisarEmpresaActionForm
 * 
 * @author Victor Cisneiros
 * @date 19/05/2008
 */
public class ExibirPesquisarEmpresaAction extends GcomAction {
	
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		PesquisarEmpresaActionForm pesquisarEmpresaActionForm = (PesquisarEmpresaActionForm) form;
		pesquisarEmpresaActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		
		return mapping.findForward("exibirPesquisarEmpresaAction");
	}

}

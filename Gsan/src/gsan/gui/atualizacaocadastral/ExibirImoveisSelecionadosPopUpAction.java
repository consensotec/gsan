package gsan.gui.atualizacaocadastral;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.fachada.Fachada;
import gsan.gui.GcomAction;

public class ExibirImoveisSelecionadosPopUpAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
       
        ActionForward retorno = actionMapping.findForward("exibirImoveisSelecionadosPopUpAction");

        Fachada fachada = Fachada.getInstancia();
       
        String idParametro = httpServletRequest.getParameter("idParametro");
       
        //pesquisar imoveis pelo id do parametro
        if(idParametro != null && !idParametro.trim().equals("")){
            ArrayList<Integer> colecaoImoveis = (ArrayList<Integer>) fachada.pesquisarImoveisAtualizacaoCadastral(Integer.parseInt(idParametro));
           
            if ((colecaoImoveis != null) && (!colecaoImoveis.isEmpty())) {
                httpServletRequest.setAttribute("colecaoImoveis", colecaoImoveis);
            } else {
                httpServletRequest.removeAttribute("colecaoImoveis");
            }
        }
        return retorno;
    }
}

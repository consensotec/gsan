package gcom.gui.mobile.execucaoordemservico;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.mobile.execucaoordemservico.ExecucaoOSFoto;
import gcom.util.Util;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirConsultarDadosOrdemServicoCobrancaSmartphoneFotosAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
        //seta o mapeamento de retorno para a página da primeira aba
        ActionForward retorno = actionMapping.findForward("consultarDadosOrdemServicoCobrancaSmartphoneFotos");
        
        ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm form = (ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm) actionForm;

        //cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //cria uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Integer idArquivo = Integer.valueOf(form.getIdArquivo());        
        Integer idOS = Integer.valueOf(form.getOrdemServico());
        
        ArrayList<ExecucaoOSFoto> fotos = (ArrayList<ExecucaoOSFoto>) fachada.pesquisarFotosOSCobrancaSmartphone(idArquivo, idOS);
        
		if (!Util.isVazioOrNulo(fotos)){
			sessao.setAttribute("colecaoFotoOS", fotos);
			sessao.setAttribute("numeroFotos", fotos.size());
			sessao.setAttribute("idFoto", 1);
		} else {
			sessao.removeAttribute("colecaoFotoOS");
			sessao.removeAttribute("numeroFotos");
			sessao.removeAttribute("idFoto");
		}
			
		//retorna o mapeamento contido na variável retorno
        return retorno;
    }
    
}

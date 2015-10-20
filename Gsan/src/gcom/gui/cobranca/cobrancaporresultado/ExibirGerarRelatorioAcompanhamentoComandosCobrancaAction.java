package gcom.gui.cobranca.cobrancaporresultado;

import gcom.cadastro.empresa.Empresa;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1238] Gerar Relatório de Acompanhamento dos Comandos de Cobrança
 * 
 * @author Mariana Victor
 * @date 08/11/2011
 */
public class ExibirGerarRelatorioAcompanhamentoComandosCobrancaAction extends GcomAction {
    
  
    public ActionForward execute(ActionMapping actionMapping,
            					ActionForm actionForm, 
            					HttpServletRequest httpServletRequest,
            					HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioAcompanhamentoComandosCobranca");
        
        Fachada fachada = Fachada.getInstancia();
        
    	HttpSession sessao = httpServletRequest.getSession(false);
    	
        //Coleções da tela
    	//Empresa
    	Collection<Empresa> colecaoEmpresasContratadas = fachada.obterColecaoEmpresasContratadasCobranca();
    	sessao.setAttribute("colecaoEmpresasContratadas", colecaoEmpresasContratadas);
        
        return retorno;
    }

}

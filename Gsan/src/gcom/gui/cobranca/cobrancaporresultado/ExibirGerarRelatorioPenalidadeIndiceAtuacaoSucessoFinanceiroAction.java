package gcom.gui.cobranca.cobrancaporresultado;

import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1239] Gerar Relat�rio de Penalidades por �ndice de Atua��o e Sucesso Financeiro
 * 
 * @author Hugo Azevedo
 * @date 26/10/2011
 */
public class ExibirGerarRelatorioPenalidadeIndiceAtuacaoSucessoFinanceiroAction extends GcomAction {
    
  
    public ActionForward execute(ActionMapping actionMapping,
            					ActionForm actionForm, 
            					HttpServletRequest httpServletRequest,
            					HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioPenalidadeIndiceAtuacaoSucessoFinanceiro");
        
        Fachada fachada = Fachada.getInstancia();
        
    	HttpSession sessao = httpServletRequest.getSession(false);
    	
    	GerarRelatorioPenalidadeIndiceAtuacaoSucessoFinanceiroActionForm form = (GerarRelatorioPenalidadeIndiceAtuacaoSucessoFinanceiroActionForm) actionForm;
    	
        
        //Cole��es da tela
        //=======================================
        
    	//Empresa
    	Collection colecaoEmpresasContratadas = fachada.obterColecaoEmpresasContratadasCobranca();
    	sessao.setAttribute("colecaoEmpresasContratadas", colecaoEmpresasContratadas);
    	  
        
        return retorno;
    }
}

package gsan.gui.cobranca.cobrancaporresultado;

import gsan.cadastro.localidade.Localidade;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1239] Gerar Relatório de Penalidades por Índice de Atuação e Sucesso Financeiro
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
    	
        
        //Coleções da tela
        //=======================================
        
    	//Empresa
    	Collection colecaoEmpresasContratadas = fachada.obterColecaoEmpresasContratadasCobranca();
    	sessao.setAttribute("colecaoEmpresasContratadas", colecaoEmpresasContratadas);
    	  
        
        return retorno;
    }
}

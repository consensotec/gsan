package gcom.gui.relatorio.cobranca;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * Relatorio Consultar Arquivo Texto Retorno Cobranca
 * [UC????]
 * 
 * @author Joao Pedro Medeiros
 * @created 23/1!/2015
 */
public class ExibirRelatorioConsultarArquivoRetornoCobrancaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("exibirRelatorioConsultarArquivoRetornoCobrancaAction");		
		
		GerarRelatorioConsultarArquivoRetornoCobrancaActionForm consultarArquivoRetornoCobrancaActionForm = 
				(GerarRelatorioConsultarArquivoRetornoCobrancaActionForm) actionForm;
				
		String dataVencimentoInicial = consultarArquivoRetornoCobrancaActionForm.getDataVencimentoInicial();
		String dataVencimentoFinal = consultarArquivoRetornoCobrancaActionForm.getDataVencimentoFinal();
		
		if(dataVencimentoFinal != null && !dataVencimentoFinal.equals("")
				&& dataVencimentoInicial != null && !dataVencimentoInicial.equals("")){
			
			Date dtInicial = Util.converteStringParaDate(dataVencimentoInicial);
			Date dtFinal = Util.converteStringParaDate(dataVencimentoFinal);
			
			if(Util.compararData(dtInicial, dtFinal) == 1){				
				throw new ActionServletException("atencao.data.intervalo.invalido", null ,"Data Invalida" );				
			}			
		}		
		
		return retorno;
	}	
}
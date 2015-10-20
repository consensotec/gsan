package gcom.gui.relatorio.micromedicao;

import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroFaixaIdade;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverHidrometroFaixaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String descricao = httpServletRequest.getParameter("descricao");
		String valorInicial = httpServletRequest.getParameter("inicial");
		String valorFinal = httpServletRequest.getParameter("final");
		
		AdicionarHidrometroFaixaIdadeHelper helperRemover = 
			new AdicionarHidrometroFaixaIdadeHelper(
					descricao,
					new Integer(valorInicial),
					new Integer(valorFinal));		
		
		Collection colecaoHidrometroFaixas =  (Collection) sessao.getAttribute("colecaoHidrometroFaixaIdade");		
		
		Collection colecaoRetorno = removerFaixa(helperRemover,colecaoHidrometroFaixas);		
		
		sessao.setAttribute("colecaoHidrometroFaixaIdade", colecaoRetorno);
		
		return actionMapping.findForward("removerFaixaIdadeAction");
	}
	
	public Collection removerFaixa(AdicionarHidrometroFaixaIdadeHelper faixaRemover, Collection colecao){
		
		Collection colecaoRetorno = colecao;
		
		Iterator iterator = colecao.iterator();
		
		while (iterator.hasNext()) {
			HidrometroFaixaIdade faixa = (HidrometroFaixaIdade) iterator.next();
			
			if(faixaRemover.getDescricao().equals(faixa.getDescricao()) 
					&& faixaRemover.getValorInicial().equals(faixa.getValorInicial()) 
					&& faixaRemover.getValorFinal().equals(faixa.getValorFinal()) ){
				colecao.remove(faixa);
				break;				
			}			
		}
		
		return colecaoRetorno;
		
	}
}

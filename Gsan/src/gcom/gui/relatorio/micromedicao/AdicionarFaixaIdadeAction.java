package gcom.gui.relatorio.micromedicao;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroFaixaIdade;
import gcom.util.Util;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Cesar Medeiros
 * @created 09/06/2015
 */
public class AdicionarFaixaIdadeAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		AdicionarFaixaIdadeActionForm form = (AdicionarFaixaIdadeActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(!Util.verificarNaoVazio(form.getDescricao())){
			throw new ActionServletException("atencao.informe_os_campos_obrigatorios");
		}	
		if(form.getValorInicial() == null){
			throw new ActionServletException("atencao.informe_os_campos_obrigatorios");
		}
		if(form.getValorFinal() == null){
			throw new ActionServletException("atencao.informe_os_campos_obrigatorios");
		}
		
		AdicionarHidrometroFaixaIdadeHelper helper = 
			new AdicionarHidrometroFaixaIdadeHelper(
					form.getDescricao(),
					form.getValorInicial(),
					form.getValorFinal());
				
		Collection colecaoHidrometroFaixas =  (Collection) sessao.getAttribute("colecaoHidrometroFaixaIdade");

		Iterator i = colecaoHidrometroFaixas.iterator();

		while(i.hasNext()){			

			HidrometroFaixaIdade hidrometroFaixaIdade = (HidrometroFaixaIdade)i.next();

			if(verificarFaixa(helper,hidrometroFaixaIdade)){	
				
				String[] parametros = {
										helper.getValorInicial().toString(),
										helper.getValorFinal().toString(),
										hidrometroFaixaIdade.getDescricao()};
								
				throw new ActionServletException("atencao.hidrometro_faixa_idade_ja_existe",parametros);
			
			}					
			
		}			

		HidrometroFaixaIdade novaFaixa = new HidrometroFaixaIdade();
		novaFaixa.setDescricao(helper.getDescricao());
		novaFaixa.setValorInicial(helper.getValorInicial());
		novaFaixa.setValorFinal(helper.getValorFinal());
		colecaoHidrometroFaixas.add(novaFaixa);	
		
		
		List colecaoFaixasParaOrdenar = (List) colecaoHidrometroFaixas;

		Collections.sort(colecaoFaixasParaOrdenar,  
			new Comparator() {  
			public int compare(Object left, Object right) {  
				HidrometroFaixaIdade leftKey = (HidrometroFaixaIdade) left;  
				HidrometroFaixaIdade rightKey = (HidrometroFaixaIdade) right;  
				return leftKey.getValorInicial().compareTo(rightKey.getValorInicial());  
			}  
		}); 	    
		
		sessao.setAttribute("colecaoHidrometroFaixaIdade",colecaoFaixasParaOrdenar);		
		httpServletRequest.setAttribute("reload", "");	
		return actionMapping.findForward("adicionarFaixaIdadeAction");
	}
	

	/*
	 * Metodo que verifica se faixa esta contida no intervalo de outra faixa
	 */
	public boolean verificarFaixa(AdicionarHidrometroFaixaIdadeHelper helper, HidrometroFaixaIdade hidrometroFaixaIdade){
		boolean retorno = false;
		
		if(helper.getValorInicial()>=hidrometroFaixaIdade.getValorInicial()
				&& helper.getValorInicial()<=hidrometroFaixaIdade.getValorFinal()){
			retorno = true;
		}
		if(helper.getValorFinal()<=hidrometroFaixaIdade.getValorFinal()
				&& helper.getValorFinal()>=hidrometroFaixaIdade.getValorInicial()){
			retorno = true;
		}
		
		return retorno;
		
	}	
	
}

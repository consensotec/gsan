package gcom.gui.cobranca;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cobranca.parcelamento.FiltroParcelamento;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class ExibirValorDescontoAcrescimentosImpontualidadeConsultarPopupAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirValorDescontoAcrescimosImpontualidadeConsultarPopup");

		ValorDescontoAcrescimosImpontualidadeConsultarPopupActionForm form = 
				(ValorDescontoAcrescimosImpontualidadeConsultarPopupActionForm) actionForm;
	
		String codigoParcelamento = httpServletRequest.getParameter("codigoParcelamento");
		if(codigoParcelamento != null && !codigoParcelamento.equals("")){
			FiltroParcelamento filtroParcelamento = new FiltroParcelamento();
			filtroParcelamento.adicionarParametro(new ParametroSimples(FiltroParcelamento.ID, codigoParcelamento));
			
			Collection<?> colParcelamento = this.getFachada().pesquisar(filtroParcelamento, Parcelamento.class.getName());
			
			if(!Util.isVazioOrNulo(colParcelamento)){
				Parcelamento parcelamento = (Parcelamento) Util.retonarObjetoDeColecao(colParcelamento);
				
				form.setMulta(Util.formatarBigDecimalComPonto(parcelamento.getValorDescontoMulta()));
				form.setJuros(Util.formatarBigDecimalComPonto(parcelamento.getValorDescontoJuros()));
				form.setAtualizacaoMonetaria(Util.formatarBigDecimalComPonto(parcelamento.getValorDescontoAtualizacaoMonetaria()));
				
			}
		}else{
			//adicionado por Vivianne Sousa - 29/06/2012
			form.setMulta(httpServletRequest.getParameter("multa"));
			form.setJuros(httpServletRequest.getParameter("juros"));
			form.setAtualizacaoMonetaria(httpServletRequest.getParameter("atualizacao"));
			
		}
		
		return retorno;
	}
	
}
